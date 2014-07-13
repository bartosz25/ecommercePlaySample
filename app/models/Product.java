package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="products")
public class Product {

	private int id;
	private Category category;
	private String name;
	private String description;
	private double price;

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
	
	@Override
	public String toString() {
		return "Product {id: "+this.id+", name: "+this.name+", category: "+this.category+", price: "+this.price+"}";
	}
	
}
