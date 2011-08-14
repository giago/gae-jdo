package com.giago.appengine.commons.util;

import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;

public class GaeEntitySampleGenerator {

	public static final void articles(DatastoreService ds) {
		Entity e = new Entity("Article");
		e.setProperty("author", "luigi");
		e.setProperty("createdDate", new Date(0));
		e.setProperty("title", "Gwt and maven");
		e.setProperty("description", "How to add support for get to the build");
		e.setProperty("id", new Long(1L));
		ds.put(e);
		e = new Entity("Article");
		e.setProperty("author", "pippo");
		e.setProperty("createdDate", new Date(10000));
		e.setProperty("title", "App Engine maven");
		e.setProperty("description", "Integrate maven and app engine");
		e.setProperty("id", new Long(2L));
		ds.put(e);
	}

	public static final void article(DatastoreService ds) {
		article(ds, new Date(0));
	}
	
	public static final void article(DatastoreService ds, Date date) {
		Entity e = new Entity("Article");
		e.setProperty("author", "luigi");
		e.setProperty("createdDate", date);
		e.setProperty("title", "Gwt and maven");
		e.setProperty("description", "How to add support for get to the build");
		e.setProperty("id", new Long(1L));
		ds.put(e);
	}
	
}
