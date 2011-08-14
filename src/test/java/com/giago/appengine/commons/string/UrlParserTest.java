
package com.giago.appengine.commons.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class UrlParserTest {

    @Test
    public void shouldGetTheIdFromCorrectUrl() {
        assertEquals(Long.valueOf(120), UrlParser.getId("http://www.test.co.uk/test-120/"));
    }

    @Test
    public void shouldGetTheIdFromStrangeUrl1() {
        assertEquals(Long.valueOf(120), UrlParser.getId("http://www.test.co.uk/test-120/?test=1"));
    }

    @Test
    public void shouldGetTheIdFromStrangeUrl2() {
        assertEquals(Long.valueOf(120), UrlParser.getId("http://www.test.co.uk/test-120/test.jsp"));
    }

    @Test
    public void shouldGetIdReturnNullIfsomethingIsWrong() {
        assertNull(UrlParser.getId("http://www.test.co.uk/test-12c0/test.jsp"));
    }

    @Test
    public void shouldGetIdReturnTheLastId() {
        assertEquals(Long.valueOf(120),
                UrlParser.getId("http://www.test.co.uk/test-32/test-120/test.jsp"));
    }

    @Test
    public void shouldGetIdOnQueryStringToo() {
        assertEquals(Long.valueOf(118179),
                UrlParser.getId("http://www.test.co.uk/story/Can-money-buy-happiness?--118179/"));
        assertEquals(Long.valueOf(728008),
                UrlParser.getId("http://facebook-pipes.appspot.com/story/C6-or-X6..??-728008/"));
        assertEquals(
                Long.valueOf(145065),
                UrlParser
                        .getId("http://facebook-pipes.appspot.com/story/A-sign-of-things-to-come?-145065/"));
        assertEquals(
                Long.valueOf(94224),
                UrlParser
                        .getId("http://facebook-pipes.appspot.com/story/Are-you-a-DB2-Expert?-Par-94224/"));
    }

    @Test
    public void shouldGetIdOnlyFromQueryStringToo() {
        assertEquals(Long.valueOf(118179), UrlParser.getId("--118179/"));
    }

    @Test
    public void shouldReturnFalseForVersions() {
        assertFalse(UrlParser.containsHost("http://15.latest.dev-articles.appspot.com",
                "dev-articles.appspot.com"));
    }

    @Test
    public void shouldReturnFalseIfContainsTheDomain() {
        assertFalse(UrlParser.containsHost("http://localhost:8888", "dev-articles.appspot.com"));
    }

    @Test
    public void shouldNotChangeTheHostIfUsingSubdomain() {
        assertEquals("http://15.latest.dev-articles.appspot.com", UrlParser.changeHostIfMatch(
                "http://15.latest.dev-articles.appspot.com", "dev-articles.appspot.com",
                "www.dev-article.com"));
    }

    @Test
    public void shouldChangeTheHost() {
        assertEquals("http://www.dev-article.com", UrlParser.changeHostIfMatch(
                "http://dev-articles.appspot.com", "dev-articles.appspot.com",
                "www.dev-article.com"));
    }
    
    @Test
    public void shouldChangeTheHostAndLeaveTheRestOfUrlUnchanged() {
        assertEquals("http://www.dev-article.com/tag/gwt", UrlParser.changeHostIfMatch(
                "http://dev-articles.appspot.com/tag/gwt", "dev-articles.appspot.com",
                "www.dev-article.com"));
    }

}
