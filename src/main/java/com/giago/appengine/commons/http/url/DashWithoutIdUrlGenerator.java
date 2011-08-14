package com.giago.appengine.commons.http.url;

public class DashWithoutIdUrlGenerator implements UrlGenerator {

	@Override
	public String generateRelativeUrl(String title, Long id) {
		return title.replaceAll(space, dash);
	}

}
