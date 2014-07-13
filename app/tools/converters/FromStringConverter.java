package tools.converters;

import play.Logger;

public abstract class FromStringConverter {

	public static int toInt(String source) throws Exception {
		try {
			return Integer.parseInt(source);
		} catch (Exception e) {
			Logger.error("An error occurred on parsing int ("+source+")", e);
			throw e;
		}
	}
	
	
}
