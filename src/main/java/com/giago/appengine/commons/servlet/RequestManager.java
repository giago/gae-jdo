package com.giago.appengine.commons.servlet;

import javax.servlet.ServletRequest;

public class RequestManager {

	protected static void addParameter(ServletRequest request, String parameter, Object value) {
		if(value!= null) {
			request.setAttribute(parameter, value);
		}
	}
	
}
