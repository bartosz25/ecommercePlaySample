package tools.web;

import java.util.regex.Pattern;

public abstract class UrlNormalizer {

	
	public static String normalizeToUrl(String text) {
		return text.replaceAll("[^\\dA-Za-z\\-]", "-").replaceAll("\\-{2,}", "");
	}
	
}
