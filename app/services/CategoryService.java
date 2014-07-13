package services;


import java.util.List;

import javax.persistence.Query;

import play.db.jpa.JPA;
import tools.web.Pager;
import models.Category;


public class CategoryService {

	public Category getById(int id) {
		return JPA.em().find(Category.class, id);
	}

	public List<Category> getAll(Pager pager) {
		Query query = JPA.em().createQuery("SELECT c FROM Category c");
		if (pager.applyLimit()) {
			query.setFirstResult(pager.getSqlStart());
			query.setMaxResults(pager.getSqlLimit());
		}
		return query.getResultList();
	}
	
}
