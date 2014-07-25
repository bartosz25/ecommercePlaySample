package actions;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controllers.routes;
import models.Product;
import play.Logger;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
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
public class OutOfStockAction extends Action<OutOfStockAction>  {

	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		Logger.debug("Invoking OutOfStock before adding a product to the shopping cart");
		String[] parts = Context.current().request().path().split("/");
		final int productId = FromStringConverter.toInt(parts[parts.length-1]);
		try {
			// We can't use service even with @Transactional annotation; an exception "no EntityManager is bound"
			// is thrown every time. JPA.withTransaction is possible workaround.
			Boolean inStock = JPA.withTransaction(new F.Function0<Boolean>() {
				@Override
				public Boolean apply() throws Throwable {
					Query inStockQuery = JPA.em().createQuery("SELECT p FROM Product p WHERE p.id = :productId AND p.inStock = :inStock");
					inStockQuery.setParameter("productId", productId);
					inStockQuery.setParameter("inStock", Product.IN_STOCK);
					Product product = null;
					try {
						product = (Product) inStockQuery.getSingleResult();
					} catch (NoResultException nre) {
					} catch (Exception e) {
						Logger.error("An error occurred on checking product ("+productId+") availability", e);
					}
					return product != null;
				}
			});
			if (inStock.booleanValue() == true) {
				return delegate.call(ctx);
			}
		} catch (Exception e) {
			Logger.error("An error occurred on checking if product is in stock for path "+Context.current().request().path(), e);
		}
		return F.Promise.pure(redirect(routes.ProductController.productUnavailable(productId)));
	}
}
