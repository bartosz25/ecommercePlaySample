package controllers;

import play.mvc.Controller;
import models.Product;
import models.ShoppingCart;
import play.db.jpa.Transactional;
import play.*;
import play.libs.F;
import play.mvc.*;
import services.ProductService;
import services.ServicesInstances;
import services.ShoppingCartService;
import tools.web.UrlNormalizer;

public class ShoppingCartController extends Controller {
	
	@Transactional
	public static Result addProduct(int productId) {
		ProductService productService = (ProductService) ServicesInstances.PRODUCT_SERVICE.getService();
		ShoppingCartService shoppingCartService = (ShoppingCartService) ServicesInstances.SHOPPING_CART_SERVICE.getService();
		boolean productAdded = false;
		Product product = null;
		try {
			product = productService.getById(productId);
			productAdded = shoppingCartService.addProductToCart(product, session());
		} catch (Exception e) {
			Logger.error("An error occurred on adding product ("+productId+") to cart", e);
			return internalServerError();
		}
		// TODO : replace Result by JSON and move adding to cart to JavaScript layer
		flash("productAdded", String.valueOf(productAdded));
		return redirect(controllers.routes.ProductController.showOne(product.getCategory().getId(), UrlNormalizer.normalizeToUrl(product.getName()), productId));
	}
	
	@Transactional
	public static Result consult() {
		ShoppingCartService shoppingCartService = (ShoppingCartService) ServicesInstances.SHOPPING_CART_SERVICE.getService();
		ShoppingCart cart = shoppingCartService.getBySession(session());
		shoppingCartService.saveIfNew(cart);
		return ok(views.html.ShoppingCartController.consult.render(cart));
	}

}
