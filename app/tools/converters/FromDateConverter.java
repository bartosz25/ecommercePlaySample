package tools.converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import play.Logger;

public abstract class FromDateConverter {

	public static String toString(Date date, String format) throws Exception {
		try {
			DateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(date);
		} catch (Exception e) {
			Logger.error("An error occurred on converting "+date+" to String with format "+format, e);
			throw e;
		}
	}
	
}
