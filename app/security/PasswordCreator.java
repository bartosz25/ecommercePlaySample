package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import play.Logger;

public abstract class PasswordCreator {

	public static String sha1Password(String password, String salt) throws Exception {
	    try {
	    	MessageDigest md = MessageDigest.getInstance("SHA-1");
	        String finalPassword = password+salt;
	        byte[] passwordBytes = finalPassword.getBytes("UTF-8");
	        byte[] hash = md.digest(passwordBytes);

	        // TODO : use DigitUtils and add it to dependencies list
	        StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            return hexString.toString();
	    }
	    catch(Exception e) {
	    	// TODO : see how to output Play's logs into file
	        Logger.error("An error occurred on hashing '"+password+"' with SHA1", e);
	        throw e;
	    }
	}
	
}
