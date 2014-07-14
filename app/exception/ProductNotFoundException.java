package exception;

/**
 * Exception thrown when a product was not found.
 * 
 * @author bartosz
 *
 */
public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1105045175631879877L;

}
