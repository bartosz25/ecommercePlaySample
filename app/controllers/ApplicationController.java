package controllers;

import java.util.Map;

import models.Category;
import play.db.jpa.Transactional;
import play.*;
import play.mvc.*;
import services.CategoryService;
import services.ServicesInstances;
import tools.converters.FromStringConverter;

import views.html.*;
import views.html.defaultpages.error;

public class ApplicationController extends Controller {

	@Transactional(readOnly=true)
    public static Result index() {
		Map<String, String[]> params = ctx().request().queryString();
		if (params.containsKey("categoryId")) {
			try {
		    	CategoryService categoryService = (CategoryService) ServicesInstances.CATEGORY_SERVICE.getService();
		    	Category category = categoryService.getById(FromStringConverter.toInt(params.get("categoryId")[0]));
		    	Logger.debug("Found category: "+category);
			} catch (Exception e) {
				// TODO : redirect to error page
			}
		}
        return ok(index.render("Your new application is ready."));
    }

}
