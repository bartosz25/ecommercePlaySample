package security;

import play.mvc.Http.Request;

public class FingerprintMaker {

	public static String makeFromRequest(Request request, String secret) throws Exception {
		String token = request.remoteAddress()+secret+request.getHeader("User-Agent")+secret;
		return PasswordCreator.sha1Password(token, "");
	}
	
}
