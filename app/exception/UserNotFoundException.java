package exception;

/**
 * Exception thrown when one user was not found.
 * 
 * @author bartosz
 *
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1029235031623622736L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
