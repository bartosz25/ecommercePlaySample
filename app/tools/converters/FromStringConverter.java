package tools.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public static Date toDate(String source, String format) throws Exception {
		try {
	      DateFormat df = new SimpleDateFormat(format);
	      return df.parse(source);
	    } catch(Exception e) {
	    	Logger.error("An exception occurred on parsing date ("+source+")", e);
	    	throw e;
	    }
	}
	
	
}
