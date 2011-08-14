package com.giago.appengine.commons.http.url;

import java.net.URLEncoder;

public class EncodedAndLimitedUrlGenerator implements UrlGenerator {
	
	private static final String ENCODING = "UTF-8";

	@Override
	public String generateRelativeUrl(String title, Long id) {
		if(title == null) {
			return null;
		}
		if(title.length() > 25) {
			title = title.substring(0, 25);
		}
		try {
			return URLEncoder.encode(title, ENCODING) + dash + id;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
