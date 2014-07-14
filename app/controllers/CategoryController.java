package controllers;

import java.util.List;

import models.Category;
import models.Product;

import play.db.jpa.Transactional;
import play.*;
import play.mvc.*;
import services.CategoryService;
import services.ProductService;
import services.ServicesInstances;
import tools.web.Pager;

public class CategoryController extends Controller {

	@Transactional(readOnly = true)
	public static Result showOne(int categoryId, int page) {
    	CategoryService categoryService = (CategoryService) ServicesInstances.CATEGORY_SERVICE.getService();
    	ProductService productService = (ProductService) ServicesInstances.PRODUCT_SERVICE.getService();
    	Category category = categoryService.getById(categoryId);
    	Logger.debug("Found category: "+category);
    	List<Product> products = productService.getByCategory(categoryId, new Pager(page, 
    			Play.application().configuration().getInt("pager.perPage")));
    	Logger.debug("Found products: "+products);
    	// TODO : add pagination to view
    	return ok(views.html.CategoryController.showOne.render(category, products));
	}

}
