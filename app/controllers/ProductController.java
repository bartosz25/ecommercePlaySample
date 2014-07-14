package controllers;

import models.Product;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;
import services.ServicesInstances;
import play.db.jpa.Transactional;

public class ProductController extends Controller {

	@Transactional(readOnly = true)
	public static Result showOne(int categoryId, String name, int productId) {
		Logger.debug("Received category: "+categoryId+", name: "+name+" and id: "+productId);
		try {
	    	ProductService productService = (ProductService) ServicesInstances.PRODUCT_SERVICE.getService();
	    	Product product = productService.getById(productId);
	    	return ok(views.html.ProductController.showOne.render(product, flash("productAdded")));
		} catch (Exception e) {
			Logger.error("Error on showing product (id:"+productId+")", e);
		}
		return notFound();
	}
	
}
