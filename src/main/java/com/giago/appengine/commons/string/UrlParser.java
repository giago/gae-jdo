package com.giago.appengine.commons.string;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class UrlParser {
	
    private static final String DASH = "-";
    private static final String SEPARATOR = "/";
    
    private static final String VERION_SUBDOMAIN = "latest.";
    
	public static Long getId(String url) {
		String possibleId = StringUtils.getLastPart(url, DASH, SEPARATOR);
		try {
			return Long.valueOf(possibleId);
		} catch(Exception e) {
			return null;
		}
	}
	
	public static final boolean containsHost(String uri, String host) {
	    if(uri.contains(host) && !uri.contains(VERION_SUBDOMAIN + host)) {
	        return true;
	    }
	    return false;
	    
	}

    public static final String changeHostIfMatch(String url, String oldDomain, String newDomain) {
        if(containsHost(url, oldDomain)) {
            return url.replace(SEPARATOR + oldDomain, SEPARATOR + newDomain);
        }
        return url;
    }
	
}
