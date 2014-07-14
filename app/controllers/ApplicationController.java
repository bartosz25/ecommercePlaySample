package controllers;

import java.util.Map;

import javax.persistence.Query;

import models.Category;
import models.Product;
import models.ShoppingCart;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;
import play.*;
import play.mvc.*;
import services.CategoryService;
import services.ProductService;
import services.ServicesInstances;
import tools.converters.FromStringConverter;

import views.html.*;
import views.html.defaultpages.error;

public class ApplicationController extends Controller {

	@Transactional()
    public static Result index() {
//		Query query = JPA.em().createQuery("SELECT sc FROM ShoppingCart sc WHERE sc.id = :id");
//		query.setParameter("id", 2);
//		// if not found, create new shopping cart
//		ShoppingCart cart = (ShoppingCart) query.getSingleResult();
//		
////		ShoppingCart cart = new ShoppingCart();
//		ProductService productService = (ProductService) ServicesInstances.PRODUCT_SERVICE.getService();
//    	Product product = productService.getById(1);
////    	Product product2 = productService.getById(2);
////    	Product product3 = productService.getById(3);
////    	cart.addProduct(product).addProduct(product2).addProduct(product3);
//    	cart.setAccessKey("aaa");
////    	cart.changeProductQuantity(product, 1);
////    	Logger.debug("Cart is :"+cart);
//    	JPA.em().persist(cart);
//    	JPA.em().flush();

        return ok(index.render("Your new application is ready."));
    }

}
