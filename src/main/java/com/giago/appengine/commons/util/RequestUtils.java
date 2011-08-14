package com.giago.appengine.commons.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	private static final String MOBILE_PARAM = "mobile";
	private static final String USER_AGENT = "User-Agent";
	private static final String ANDROID_USER_AGENT = "android";
	private static final String IPHONE_USER_AGENT = "iphone";
	private static final String IPOD_USER_AGENT = "ipod";
	
	public boolean isMobile(HttpServletRequest request) {
		String mobile = request.getParameter(MOBILE_PARAM);
		String userAgent = request.getHeader(USER_AGENT);
		if(mobile != null) {
			return true;
		}
		if(userAgent == null) {
			return false;
		}
		userAgent = userAgent.toLowerCase();
		if(userAgent.indexOf(ANDROID_USER_AGENT) != -1) {
			return true;
		} else if(userAgent.indexOf(IPHONE_USER_AGENT) != -1) {
			return true;
		} else if(userAgent.indexOf(IPOD_USER_AGENT) != -1) {
			return true;
		}
		return false;
	}

}
