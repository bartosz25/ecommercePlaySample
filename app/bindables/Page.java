package bindables;

import java.util.Map;

import play.Logger;
import play.libs.F.Option;
import play.mvc.PathBindable;
import play.mvc.QueryStringBindable;

//TODO : it's not used now, but will be used for some time to replace several routes
public class Page implements PathBindable<Page>, QueryStringBindable<Page> {

	private int page;
	
	public Page(int page) {
		this.page = page;
	}
	
	public int getPage() {
		return this.page;
	}
	
	@Override
	public String javascriptUnbind() {
		Logger.info("Unbinding JS"+" for " + this.page);
		return "3";
	}

	@Override
	public String unbind(String key) {
		Logger.info("Unbinding "+key+" for " + this.page);
		if (this.page == 0) {
			return "";
		}
		return "page="+String.valueOf(this.page);
	}

	@Override
	public Page bind(String key, String txt) {
		Logger.info("Binding "+key+" with txt "+txt+" for " + this.page);
		return new Page(1);
	}

	@Override
	public Option<Page> bind(String key, Map<String, String[]> data) {
		Logger.info("Binding "+key+" and map "+data+" for " + this.page);
		if (data == null || data.size() == 0) {
			return Option.None();
		}
		return Option.Some(this);
	}

}
