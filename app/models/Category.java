package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="categories")
public class Category {

	private int id;
	private String name;
	private List<Product> products;

    @Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	public int getId() {
		return this.id;
	}
	@Column(name="name")
	public String getName() {
		return this.name;
	}
	@OneToMany(mappedBy="category")
	public List<Product> getProducts() {
		return this.products;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "Category {id: "+this.id+", name: "+this.name+"}";
	}
	
}
