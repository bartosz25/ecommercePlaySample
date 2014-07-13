package tools.views;

import java.util.List;

import models.Category;

import services.CategoryService;
import services.ServicesInstances;
import tools.web.Pager;

public abstract class CategoryHelper {

	public static List<Category> getCategories() {
		CategoryService categoryService = (CategoryService) ServicesInstances.CATEGORY_SERVICE.getService();
		return categoryService.getAll(new Pager(0, 0));
	}
	
}
