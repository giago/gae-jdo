package com.giago.appengine.commons.sitemap.source;

import java.util.Date;
import java.util.List;

public interface SitemapSource {

	List<Integer> years();

	List<Integer> months(int year);

	List<Integer> days(int year, int month);

	void prepare(int year, int month, int day);
	
	void prepare();

	boolean next();

	String getRelativeUrl();

	Date getCreatedDate();

    String getKind();
    
    boolean isCreatedDateDefined();

}
