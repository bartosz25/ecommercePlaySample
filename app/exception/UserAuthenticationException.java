package exception;

/**
 * Exception thrown when one user wasn't correctly authentified.
 * 
 * @author bartosz
 *
 */
public class UserAuthenticationException extends Exception {

	private static final long serialVersionUID = -3964147879736446699L;

	public UserAuthenticationException(String message) {
		super(message);
	}

}
