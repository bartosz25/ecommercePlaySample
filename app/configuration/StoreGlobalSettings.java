package configuration;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import formatters.BirthdayFormatter;

import play.GlobalSettings;
import play.Logger;
import play.data.format.Formatters;
import play.data.format.Formatters.AnnotationFormatter;

import annotations.Birthday;

// TODO : only defined, this class'll be used after to inject the dependency with Spring Framework
public class StoreGlobalSettings extends GlobalSettings{
	static {
		Logger.debug("------------- Setting formatters ---------------");
		Formatters.register(Date.class, new BirthdayFormatter());
	};
//	@Override
//	public Handler onRouteRequest(Http.RequestHeader request) {
//		if (request.path().endsWith("/")) {
//			Logger.info("Path ends with /");
//		}
//		return super.onRouteRequest(request);
//	}
	
}
