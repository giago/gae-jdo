package com.giago.appengine.commons.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class HtmlClientTest {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(HtmlClientTest.class);

	@Ignore
	@Test
	public void encodingTest() {
		String source = HtmlClient.getSource("http://www.facebook.com/group.php?gid=117050198325712&v=wall");
		assertFalse(source.contains("è"));
		assertFalse(source.contains("ù"));
	}
	
	@Ignore
	@Test
	public void getUrlForFacebookResource() {
		assertEquals("http://www.facebook.com/group.php?gid=117050198325712&v=wall", 
				HtmlClient.getUrl("117050198325712"));
	}

	@Ignore
	@Test
	public void getUrlForFacebookResourceForPage() {
		assertEquals("http://www.facebook.com/bbcworldnews?v=wall", 
				HtmlClient.getUrl("bbcworldnews"));
	}
	
	@Ignore
	@Test
	public void shouldGetResponseFromPost() {
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("url", "http%3A%2F%2Fwww.dev-articles.com%2F");
	    assertEquals("", HtmlClient.post("http://goo.gl/api/shorten", map));
	}
	
	//move in the user project
	
//	@Ignore
//	@Test
//	public void manualTest() {
//		String source = HtmlClient.getSource("http://www.facebook.com/FoxNews?v=wall");
//		FacebookParser fp = new HtmlFacebookParser(source);
//		Group group = fp.getGroup(Long.valueOf(1L), "1");
//		logger.debug(group);
//		List<Story> stories = fp.getStories(Long.valueOf(1L), "1");
//		for(Story story : stories) {
//			logger.debug(story + " title : " + story.getTitle());
//		}
//	}
//
//	@Test
//	public void manualTest2() {
//		String source = HtmlClient.getSource("http://www.facebook.com/Sports?v=wall");
//		FacebookParser fp = new HtmlFacebookParser(source);
//		Group group = fp.getGroup(Long.valueOf(1L), "1");
//		logger.debug(group);
//		List<Story> stories = fp.getStories(Long.valueOf(1L), "1");
//		for(Story story : stories) {
//			logger.debug(story + " title : " + story.getTitle());
//		}
//	}

}
