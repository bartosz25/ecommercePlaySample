package services;

import java.util.Date;

import javax.persistence.Query;

import play.Logger;
import play.mvc.Http;
import tools.converters.FromStringConverter;
import exception.ProductNotFoundException;
import models.Product;
import models.ShoppingCart;
import play.db.jpa.JPA;

public class ShoppingCartService {

	/**
	 * Adds new product into shopping cart.
	 * 
	 * @param product Instance of {@link models.Product} added to the shopping cart.
	 * @param session Session which contains or will be contain shopping cart data.
	 * @return True if the save was correctly made, false otherwise.
	 * @throws ProductNotFoundException Thrown at the begin of the method when they're no product with given id.
	 */
	public boolean addProductToCart(Product product, Http.Session session) throws ProductNotFoundException {
		if (product == null) {
			throw new ProductNotFoundException("Product '"+product+"' was not found in the database");
		}
		// get cart from the database
		ShoppingCart cart = getBySession(session);
		// add product or change its quantity (if already present in the cart)
		if (cart.containsProduct(product)) {
			cart.changeProductQuantity(product, 1);
		} else {
			cart.addProduct(product);
		}
		Logger.debug("Before saving cart "+cart);
		boolean saved = saveCart(cart);
		if (saved && !session.containsKey(ShoppingCart.SESSION_ID_KEY)) {
			session.put(ShoppingCart.SESSION_ID_KEY, String.valueOf(cart.getId()));
			session.put(ShoppingCart.SESSION_ACCESS_KEY, cart.getAccessKey());
		}
		return saved;
	}
	
	/**
	 * Gets shopping cart from session data (cart id and access key).
	 * 
	 * @param session Session containing cart data. If they're no data in the session, new shopping cart is created.
	 * @return {@link models.ShoppingCart} instance - never null.
	 */
	public ShoppingCart getBySession(Http.Session session) {
		String cartId = session.get(ShoppingCart.SESSION_ID_KEY);
		ShoppingCart cart = null;
		if (cartId != null) {
			try {
				Query query = JPA.em().createQuery("SELECT sc FROM ShoppingCart sc WHERE sc.id = :id AND sc.accessKey = :accessKey");
				query.setParameter("id", FromStringConverter.toInt(cartId));
				query.setParameter("accessKey", session.get(ShoppingCart.SESSION_ACCESS_KEY));
				// if not found, create new shopping cart
				cart = (ShoppingCart) query.getSingleResult();
			} catch (Exception e) {
				Logger.error("An error occurred on trying to get cart from database. Session data was: id="+cartId+", access key="+
						session.get(ShoppingCart.SESSION_ACCESS_KEY), e);
			}
		}
		if (cart == null) {
			cart = new ShoppingCart();
			cart.setAccessKey(ShoppingCart.generateAccessKey());
			cart.setCreatedTime(new Date());
		}
		return cart;
	}
	
	/**
	 * Saves shopping cart into persistent layer (database) only if the cart is newly created.
	 * 
	 * @param cart Cart to save.
	 */
	public void saveIfNew(ShoppingCart cart) {
		if (cart.getId() == 0) {
			Logger.debug("Saving new cart into database: "+cart);
			saveCart(cart);
		}
	}
	
	private boolean saveCart(ShoppingCart cart) {
		try {
			JPA.em().persist(cart);
			JPA.em().flush();
			return true;
		} catch (Exception e) {
			Logger.error("An error occurred on saving ShoppingCart ("+cart+")", e);
		}
		return false;
	}

}
