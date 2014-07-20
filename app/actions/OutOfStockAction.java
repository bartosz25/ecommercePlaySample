package actions;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import controllers.routes;
import models.Product;
import play.Logger;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import services.ProductService;
import services.ServicesInstances;
import tools.converters.FromStringConverter;
import play.db.jpa.JPA;

import play.db.jpa.Transactional;
/**
 * Action which checks if a product is out-of-stock. If it's the case, it returns appropriated view. It's invoked when user
 * tries to add a product to his shopping cart.
 * 
 * @author bartosz
 *
 */
public class OutOfStockAction extends Action.Simple {

	@Override
	@Transactional
	public Promise<Result> call(Context ctx) throws Throwable {
		Logger.debug("Invoking OutOfStock before adding a product to the shopping cart");
		String[] parts = ctx.current().request().path().split("/");
		Product product = null;
		try {
			final int productId = FromStringConverter.toInt(parts[parts.length-1]);
			// We can't use service even with @Transactional annotation; an exception "no EntityManager is bound"
			// is thrown every time. JPA.withTransaction is possible workaround.
			Boolean inStock = JPA.withTransaction(new F.Function0<Boolean>() {
				@Override
				public Boolean apply() throws Throwable {
					ProductService productService = (ProductService) ServicesInstances.PRODUCT_SERVICE.getService();
					Query inStockQuery = JPA.em().createQuery("SELECT p FROM Product p WHERE p.id = :productId AND p.inStock = :inStock");
					inStockQuery.setParameter("productId", productId);
					inStockQuery.setParameter("inStock", Product.IN_STOCK);
					Product product = null;
					try {
						product = (Product) inStockQuery.getSingleResult();
					} catch (Exception e) {
					}
					return product != null;
				}
			});
			if (inStock.booleanValue()) {
				Logger.debug("Product is in stock, we continue @With execution chain");
				delegate.call(ctx);
			}
			Logger.debug("Product is out of stock, we abort @With execution chain");
			product = JPA.withTransaction(new F.Function0<Product>() {
				@Override
				public Product apply() throws Throwable {
					Query query = JPA.em().createQuery("SELECT p FROM Product p WHERE p.id = :productId");
					query.setParameter("productId", productId);
					try {
						return (Product) query.getSingleResult();
					} catch (Exception e) {
						
					}
					return null;
				}
			});
			
		} catch (Exception e) {
			Logger.error("An error occurred on checking if product is in stock for path "+ctx.current().request().path(), e);
		}
		Logger.debug("Return redirect for product "+product);
		return F.Promise.pure(redirect(routes.ProductController.productUnavailable(product.getId())));
	}

}
