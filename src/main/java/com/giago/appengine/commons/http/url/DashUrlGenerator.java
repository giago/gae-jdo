package com.giago.appengine.commons.http.url;

public class DashUrlGenerator implements UrlGenerator {

	@Override
	public String generateRelativeUrl(String title, Long id) {
		title.replace(space, dash);
		return title + dash + id;
	}

}
