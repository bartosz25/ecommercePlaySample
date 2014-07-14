package models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name="shopping_carts")
public class ShoppingCart {

	public static final String SESSION_ID_KEY = "cartId";
	public static final String SESSION_ACCESS_KEY = "accessKey";
	private int id;
	private String accessKey;
	private Date createdTime;
	private List<ShoppingCartProduct> products;

    @Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	public int getId() {
		return this.id;
	}
	@Column(name="access_key")
	public String getAccessKey() {
		return this.accessKey;
	}
	@Temporal(TIMESTAMP)
	@Column(name="created")
	public Date getCreatedTime() {
		return this.createdTime;
	}
	@OneToMany(mappedBy = "shoppingCart", cascade=CascadeType.ALL)
	public List<ShoppingCartProduct> getProducts() {
		if (this.products == null) {
			setProducts(new ArrayList<ShoppingCartProduct>());
		}
		return this.products;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public void setProducts(List<ShoppingCartProduct> products) {
		this.products = products;
	}
	
	/**
	 * Checks if shopping cart contains given product.
	 * 
	 * @param product Product to check
	 * @return True if product is already in the shopping cart. False otherwise.
	 */
	public boolean containsProduct(Product product) {
		for (ShoppingCartProduct cartProduct : getProducts()) {
			if (cartProduct.getProduct() != null && cartProduct.getProduct().getId() == product.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Changes the quantity of products in the shopping cart.
	 * 
	 * @param product Product which quantity must be changed.
	 * @param value Value: can be positive (addition) or negative (subtraction)
	 */
	public void changeProductQuantity(Product product, int value) {
		for (ShoppingCartProduct cartProduct : getProducts()) {
			if (cartProduct.getProduct() != null && cartProduct.getProduct().getId() == product.getId()) {
				cartProduct.setQuantity(cartProduct.getQuantity() + value);
				break;
			}
		}
	}

	public ShoppingCart addProduct(Product product) {
		getProducts().add(new ShoppingCartProduct(this, product, 1));
		return this;
	}

	public static String generateAccessKey() {
		// TODO : change on dynamic generation mechanism
		return "AFDDFSDFD3";
	}
	
	@Transient
	public boolean isEmpty() {
		return getProducts().size() == 0;
	}
	
	@Override
	public String toString() {
		return "ShoppingCart {created: "+this.createdTime+", products: "+this.products+"}";
	}

}
