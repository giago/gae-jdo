package com.giago.appengine.commons.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class StringUtils {
	
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
	private static final String URL_CHARSET = "UTF-8";
	
	private static final String HREF_START = "href=\"";
	private static final String HREF_END = "\" ";

	private static final String A_END = "</a>";
	private static final String A_START = ">";
	
	private static final String HTML_TAG_PATTERN = "</?\\w+((\\s+\\w+(\\s*=\\s*(?:\\\"(.|\\n)*?\"|'(.|\\n)*?'|[^'\">\\s]+))?)+\\s*|\\s*)/?>";
	private static final String DATE_SEPARATOR = "T";
	private static final String DATE_TERMINATOR = "Z";
	private static final String DATE_SEPARATOR_TO_REPLACE = " ";

	public static String getPart(String source, String startingString, String endString) {
		int from = source.indexOf(startingString);
		int to = -1;
		if(from >= 0){
			to = source.indexOf(endString, from + startingString.length());
		} else {
			return null;
		}
		if(to > 0) {
			return source.substring(from + startingString.length(), to);
		} else {
			return null;
		}
	}

	public static String getLastPart(String source, String startingString, String endString) {
		int from = source.lastIndexOf(startingString);
		int to = -1;
		if(from >= 0){
			to = source.indexOf(endString, from + startingString.length());
		} else {
			return null;
		}
		if(to > 0) {
			return source.substring(from + startingString.length(), to);
		} else {
			return null;
		}
	}
	
	public static String getHrefContent(String source) {
		return getPart(source, HREF_START, HREF_END);
	}
	
	public static String getAContent(String source) {
		return getPart(source, A_START, A_END);
	}

	public static String getPartFrom(String parsed, String startingFrom) {
		int index = parsed.indexOf(startingFrom);
		if(index > 0) {
			return parsed.substring(index + startingFrom.length());
		} else {
			return parsed;
		}
	}
	
	public static String urlDecoding(String url) {
		try {
			if(url == null) {
				return null;
			}
			return URLDecoder.decode(url, URL_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static String decodeAndLimit(String encoded) {
		if(encoded == null) {
			return null;
		}
		encoded = encoded.replaceAll(HTML_TAG_PATTERN, DATE_SEPARATOR_TO_REPLACE);
		if(encoded.length() > 500) {
			encoded = encoded.substring(0, 500);
		}
		return StringEscapeUtils.unescapeHtml(encoded);
	}

	public static String formatDate(Date date) {
		if(date == null) {
			return null;
		}
		String formatted = DATE_FORMATTER.format(date);
		return formatted.replaceFirst(DATE_SEPARATOR_TO_REPLACE, DATE_SEPARATOR) + DATE_TERMINATOR;
	}
	
	/**
     * To promote in appengine commons
     * @param in
     * @return
     */
    public static String validateToXML(String in) {
        if (in == null) {
            return in;
        }
        int len = in.length();
        StringBuffer out = null;
        for (int i = 0; i < len; i++) {
            char c = in.charAt(i);
            if (c == 0x9 || c == 0xA || c == 0xD || (c >= 0x20 && c <= 0xD7FF) ||
                    (c >= 0xE000 && c <= 0xFFFD) || (c >= 0x10000 && c <= 0x10FFFF)) {
                if (out != null) {
                    out.append(c);
                }
            } else {
                if (out == null) {
                    out = new StringBuffer();
                    if (i > 0) {
                        out.append(in.substring(0, i));
                    }
                }
            }
        }
        return out == null ? in : out.toString();
    }

}
