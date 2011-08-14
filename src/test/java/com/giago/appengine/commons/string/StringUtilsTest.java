package com.giago.appengine.commons.string;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class StringUtilsTest {
	
	@Test
	public void shouldGetPartFrom() {
		assertEquals("yyyy", StringUtils.getPartFrom("xxxurl=yyyy", "url="));
	}
	
	@Test
	public void shouldGetLastPart() {
		assertEquals("12", StringUtils.getLastPart("test-1-12/", "-", "/"));
	}

	@Test
	public void shouldGetADecodedUrl() {
		assertEquals("http://inter.theoffside.com/files/2010/05/happy-mou-and-marco.jpg", 
				StringUtils.urlDecoding("http%3A%2F%2Finter.theoffside.com%2Ffiles%2F2010%2F05%2Fhappy-mou-and-marco.jpg"));
	}

	@Test
	public void shouldGetADecodedUrlNull() {
		assertEquals(null, StringUtils.urlDecoding(null));
	}

	@Test
	public void shouldGetADecodedUrlEmpty() {
		assertEquals("", StringUtils.urlDecoding(""));
	}

	@Test
	public void shouldGetADecoded() {
		assertEquals("L'annuncio di Perez. Ma il portoghese deve prima risolvere le questioni contrattuali con l'Inter", 
				StringUtils.decodeAndLimit("L&#039;annuncio di Perez. Ma il portoghese deve prima risolvere le questioni contrattuali con l&#039;Inter"));
	}
	
	@Test
	public void shouldFormatDateCorrectly() {
		assertEquals("1970-01-01T01:00:00Z", StringUtils.formatDate(new Date(1)));
	}
	
	@Test
	public void shouldLimitAStringTo500() {
		assertEquals(500, StringUtils.decodeAndLimit("XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-" + 
				"XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-" +
				"XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-" +
				"XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-" +
				"XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-" +
				"XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-").length());
	}
	
	@Test
	public void shouldRemoveHtmlTag() {
		assertEquals("Quem ser o vencedor da Bota de Ouro?  Wayne Rooney ?  Lionel Messi ? Deixa a tua opinio abaixo em  Sports on Facebook .", 
				StringUtils.decodeAndLimit("Quem ser o vencedor<br />da Bota de Ouro? <a href=\"/profile.php?id=9114194532\" " +
				"title=\"To tag someone, type @ and then the friend's name\">Wayne Rooney</a>? <a href=\"/profile.php?id=83705192730\" " +
				"title=\"To tag someone, type @ and then the friend's name\">Lionel Messi</a>? Deixa a tua opinio abaixo em " +
				"<a href=\"/profile.php?id=359129892456\" title=\"To tag someone, type @ and then the friend's name\">Sports on Facebook</a>."));
	}
	
	@Test
	public void shouldContains() {
		assertEquals(false, "www.dev-articles.com".contains(".dev-articles.appspot.com"));
		assertEquals(true, "26.dev-articles.appspot.com".contains(".dev-articles.appspot.com"));
	}


}
