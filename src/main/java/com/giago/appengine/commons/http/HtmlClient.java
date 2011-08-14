package com.giago.appengine.commons.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class HtmlClient {	
	
	private static final Logger logger = Logger.getLogger(HtmlClient.class.getName());
	private static final String FACEBOOK_PREFIX = "http://www.facebook.com/";
	private static final String PREFIX = FACEBOOK_PREFIX + "group.php?gid=";
	private static final String SUFFIX = "v=wall";	
	private static final String PARAM_AND = "&";	
	private static final String PARAM_START = "?";	
	
	private static final String ENCODING = "UTF-8";
	
	public static String getSource(String urlString) {
		try {
			URL url = new URL(urlString);
		    URLConnection con = url.openConnection();
		    BufferedReader dis;
		    StringBuffer source = new StringBuffer();
		    String line;
		    dis = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODING));
	        while ((line = dis.readLine()) != null) {
	        	source.append(line);
	        }
		    dis.close();
		    return source.toString(); 
		} catch(IOException e) {
			throw new RuntimeException("Problem with get source in the html client.", e);
		}
	}
		
	public static String getFacebookResource(String id) {
		return getSource(getUrl(id));
	}
	
	protected static String getUrl(String facebookId) {
		String url = null; 
		try {
			Long.valueOf(facebookId);
			url = PREFIX + facebookId + PARAM_AND + SUFFIX;
		} catch (Exception e) {
			url = FACEBOOK_PREFIX + facebookId + PARAM_START + SUFFIX;
		}
		logger.info("using url : " + url);
		return url;
	}
    
	/**
	 * This method need to be verified!!!
	 * @param urlString
	 * @param params
	 * @return
	 */
    public static String post(String urlString, HashMap<String, String> params) {
        try {
            logger.info("calling : " + urlString);
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length", "100");
            if(params != null) {
                for(Entry<String, String> entry : params.entrySet()) {
                    con.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            BufferedReader dis;
            StringBuffer source = new StringBuffer();
            String line;
            dis = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODING));
            while ((line = dis.readLine()) != null) {
                source.append(line);
            }
            dis.close();
            String response = source.toString();
            if (response == null) {
                throw new RuntimeException("Can't procede there was a failure in the request, the response id null");
            }
            logger.info(response);
            return response; 
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

}
