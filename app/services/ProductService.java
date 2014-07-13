package services;

import java.util.List;

import javax.persistence.Query;
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

	public Product getById(int productId) {
		Query query = JPA.em().createQuery("SELECT p FROM Product p WHERE p.id = :id");
		query.setParameter("id", productId);
		return (Product) query.getSingleResult();
	}
}
