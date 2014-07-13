package configuration;

import play.GlobalSettings;
import play.Logger;
import play.api.mvc.Handler;
import play.mvc.Http;

// TODO : only defined, this class'll be used after to inject the dependency with Spring Framework
public class StoreGlobalSettings extends GlobalSettings{

//	@Override
//	public Handler onRouteRequest(Http.RequestHeader request) {
//		if (request.path().endsWith("/")) {
//			Logger.info("Path ends with /");
//		}
//		return super.onRouteRequest(request);
//	}
	
}
