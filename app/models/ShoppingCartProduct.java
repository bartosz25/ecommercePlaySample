package models;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shopping_carts_products")
public class ShoppingCartProduct {

	private ShoppingCartProductPK primaryKey;
	private int quantity;
	private ShoppingCart shoppingCart;
	private Product product;
	
	public ShoppingCartProduct() {
		// must be defined to avoid Hibernate's exceptions
		// Caused by: org.hibernate.InstantiationException: No default constructor for entity: models.ShoppingCartProduct
	}
	
	public ShoppingCartProduct(ShoppingCart shoppingCart, Product product, int quantity) {
		this.shoppingCart = shoppingCart;
		this.product = product;
		this.quantity = quantity;
//		this.primaryKey = new ShoppingCartProductPK(shoppingCart.getId(), product.getId());
	}
	
	@EmbeddedId
	@AttributeOverrides({
        @AttributeOverride(name = "shoppingCartId", column = @Column(name = "shopping_carts_id", nullable = false)),
        @AttributeOverride(name = "productId", column = @Column(name = "products_id", nullable = false))
    })
	public ShoppingCartProductPK getPrimaryKey() {
		if (this.primaryKey == null) {
			this.primaryKey = new ShoppingCartProductPK(shoppingCart.getId(), product.getId());
		}
		return this.primaryKey;
	}
	@Column(name="quantity")
	public int getQuantity() {
		return this.quantity;
	}
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_carts_id", nullable = false, insertable = false, updatable = false)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id", nullable = false, insertable = false, updatable = false)
    public Product getProduct() {
        return product;
    }
    
    public void setPrimaryKey(ShoppingCartProductPK primaryKey) {
        this.primaryKey = primaryKey;
    }
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
    	this.shoppingCart = shoppingCart;
    }
    public void setProduct(Product product) {
    	this.product = product;
    }
	
	public static class ShoppingCartProductPK implements Serializable {
		private static final long serialVersionUID = 3251001402577279411L;
		private int shoppingCartId;
		private int productId;
		
		public ShoppingCartProductPK() {
			
		}
		
		public ShoppingCartProductPK(int shoppingCartId, int productId) {
			this.shoppingCartId = shoppingCartId;
			this.productId = productId;
		}
		
		public int getShoppingCartId() {
			return this.shoppingCartId;
		}
		public int getProductId() {
			return this.productId;
		}
		public void setShoppingCartId(int shoppingCartId) {
			this.shoppingCartId = shoppingCartId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		
		@Override
		public String toString() {
			return "ShoppingCartProductPK {product's id: "+this.productId+", shopping cart's id:"+this.shoppingCartId+"}";
		}
	}
	
	@Override
	public String toString() {
		return "ShoppingCartProduct {primary key: "+this.primaryKey+", quantity: "+this.quantity+"}";
	}
}
