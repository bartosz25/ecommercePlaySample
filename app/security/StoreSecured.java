package security;

import controllers.routes;
import play.Logger;
import play.Play;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Authenticator used to protect all pages which path begins with an /user/*. This class need to be implemented with
 * @Security.Authenticated annotation, like that:
 * <pre>
 * @Security.Authenticated(StoreSecured.class)
 * </pre>
 * 
 * After, the {@link play.mvc.Security.AuthenticatedAction} evaluates this annotation by checking if getUsername() from
 * this class returns something other that null. If it's the case, we consider that user has granted access to protected 
 * resource. Otherwise we think that he isn't connected and can't access to the resource.
 * 
 * @author bartosz
 *
 */
public class StoreSecured extends Security.Authenticator {

	public static final String COOKIE_KEY_USER = "u";
	public static final String COOKIE_KEY_FINGERPRINT = "fg";
	
	@Override
    public String getUsername(Context ctx) {
		Logger.debug("Getting user from StoreSecured getUsername() method");
		String login = ctx.session().get(COOKIE_KEY_USER);
		String sessionFg = ctx.session().get(COOKIE_KEY_FINGERPRINT);
		try {
			String fingerprint = FingerprintMaker.makeFromRequest(ctx.request(), Play.application().configuration().getString("fingerprint.secret"));
			if (fingerprint != null && fingerprint.equals(sessionFg)) {
				return login;
			}
			Logger.debug("Unknown user for the login '"+login+"'. It mays be session hijacking try because fingerprings aren't the same "+
					" (in the session: "+sessionFg+", generated: "+fingerprint+")");
		} catch (Exception e) {
			Logger.error("An error occurred on getting username from session. Concerned user login is '"+login+"'", e);
		}
		return null;
    }

    @Override
    public Result onUnauthorized(Context ctx) {
    	Logger.debug("User is unathorized to access to the protected ressource. We redirect him to login page");
        return redirect(routes.UserController.login());
    }
}
