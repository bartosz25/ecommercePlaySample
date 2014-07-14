package services;

import java.util.List;

import javax.persistence.Query;

import exception.ProductNotFoundException;
import play.db.jpa.JPA;
import models.Product;
import tools.web.Pager;

public class ProductService {

	public List<Product> getAll(Pager pager) {
		Query query = JPA.em().createQuery("SELECT p FROM Product p")	;
		if (pager.applyLimit()) {
			query.setFirstResult(pager.getSqlStart());
			query.setMaxResults(pager.getSqlLimit());
		}
		return query.getResultList();
	}

	
	public Product getById(int productId) throws ProductNotFoundException {
		Query query = JPA.em().createQuery("SELECT p FROM Product p WHERE p.id = :id");
		query.setParameter("id", productId);
		Product product = (Product) query.getSingleResult();
		if (product == null) {
			throw new ProductNotFoundException("Product was not found with given id ("+productId+")");
		}
		return product;
	}

	public List<Product> getByCategory(int categoryId, Pager pager) {
		Query query = JPA.em().createQuery("SELECT p FROM Product p WHERE p.category.id = :id");
		query.setParameter("id", categoryId);
		if (pager.applyLimit()) {
			query.setFirstResult(pager.getSqlStart());
			query.setMaxResults(pager.getSqlLimit());
		}
		return query.getResultList();
	}
}
