package com.giago.appengine.commons.http.url;

public interface UrlGenerator {
	
	String dash = "-";
	
	String space = " ";
	
	String slash = "/";
	
	String generateRelativeUrl(String title, Long id);

}
