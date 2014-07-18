package formatters;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import data.ConstantsContainer;


import play.Logger;
import play.data.format.Formatters.AnnotationFormatter;
import tools.converters.FromDateConverter;
import tools.converters.FromStringConverter;
import annotations.Birthday;

public class BirthdayFormatter extends AnnotationFormatter<Birthday, Date> {
	/**
	 * Converts form input to expected Java's object.
	 * 
	 * @return Converted Date or null if an exception occurred.
	 */
	@Override
	public Date parse(Birthday annot, String input, Locale locale) throws ParseException {
		try {
			return FromStringConverter.toDate(input, annot.format());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Converts Java object to form input.
	 * 
	 * @return String representation of Date object or empty String if an exception occurred.
	 */
	@Override
	public String print(Birthday annot, Date dateObj, Locale locale) {
		try {
			return FromDateConverter.toString(dateObj, annot.format());
		} catch (Exception e) {
			return ConstantsContainer.EMPTY_STRING;
		}
	}

}
