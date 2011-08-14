package com.giago.appengine.commons.string;

import java.util.ArrayList;
import java.util.HashMap;

public class Tagger {
	
	private static final int MAX_NUMBER_OF_TAG = 50;
	private static final HashMap<String, String> MEANINGLESS_WORDS = new HashMap<String, String>(); 
	static {
		MEANINGLESS_WORDS.put("it", "");
		MEANINGLESS_WORDS.put("and", "");
		MEANINGLESS_WORDS.put("or", "");
		MEANINGLESS_WORDS.put("of", "");
		MEANINGLESS_WORDS.put("the", "");
		MEANINGLESS_WORDS.put("is", "");
		MEANINGLESS_WORDS.put("at", "");
	}
	
	public static ArrayList<String> tag(String source) {
		return tag(source, MAX_NUMBER_OF_TAG);
	}
	
	public static ArrayList<String> tag(String source, int limit) {
		ArrayList<String> result = new ArrayList<String>();
		if(source != null && source.length() > 1) {
			source = source.replaceAll("\\\\", " ");
			source = source.replaceAll("\\/", " ");
			String[] tokens = source.split(" ");
			for(int i = 0; i< tokens.length && i < limit; i++) {
				String token = tokens[i].toLowerCase();
				if(token.length() > 1) {
					token = token.replaceAll("\'s", "");
					token = token.replaceAll("\\?", "");
					token = token.replaceAll("\\!", "");
					token = token.replaceAll("\\-", "");
					token = token.replaceAll("\\:", "");
					token = token.replaceAll("\\.", "");
					token = token.replaceAll("\\,", "");
					token = token.replaceAll("\\'", "");
					token = token.replaceAll("\\\"", "");
					token = token.replaceAll("\\(", "");
					token = token.replaceAll("\\)", "");
					token = token.replaceAll("\\[", "");
					token = token.replaceAll("\\]", "");
					token = token.replaceAll("\\}", "");
					token = token.replaceAll("\\{", "");
					if(!MEANINGLESS_WORDS.containsKey(token) && !result.contains(token)) {
						result.add(token.toLowerCase());
					}
				}
			}
		}
		return result;
	}

}
