package services;

/**
 * Singleton holding the instances of all stateless services.
 * 
 * @author bartosz
 *
 */
public enum ServicesInstances {

	CATEGORY_SERVICE(new CategoryService()),
	PRODUCT_SERVICE(new ProductService()),
	SHOPPING_CART_SERVICE(new ShoppingCartService()),
	USER_SERVICE(new UserService());
	
	private Object service;
	
	private ServicesInstances(Object service) {
		this.service = service;
	}
	
	public Object getService() {
		return this.service;
	}
	
}
