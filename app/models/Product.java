package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="products")
public class Product {

	public static final int IN_STOCK = 1;
	
	private int id;
	private Category category;
	private String name;
	private String description;
	private double price;
	private int inStock;
	private List<ShoppingCartProduct> shoppingCartProducts;

    @Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categories_id")
	public Category getCategory() {
		return category;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	@Column(name="price")
	public double getPrice() {
		return price;
	}
	@Column(name="in_stock")
	public int getInStock() {
		return this.inStock;
	}
	@OneToMany(mappedBy = "product")
	public List<ShoppingCartProduct> getShoppingCartProducts() {
		return this.shoppingCartProducts;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	public void setShoppingCartProducts(List<ShoppingCartProduct> shoppingCartProducts) {
		this.shoppingCartProducts = shoppingCartProducts;
	}
	
//	@Transient
//	public boolean isInStock() {
//		return this.inStock == IN_STOCK;
//	}
	
	@Override
	public String toString() {
		return "Product {id: "+this.id+", name: "+this.name+", category: "+this.category+", price: "+this.price+", in stock: "+this.inStock+"}";
	}
	
}
