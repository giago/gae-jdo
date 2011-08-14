package com.giago.appengine.commons.http.url;

public class DashUrlWithFinalSlashGenerator implements UrlGenerator {

	@Override
	public String generateRelativeUrl(String title, Long id) {
		title.replace(space, dash);
		return title + dash + id + slash;
	}

}
