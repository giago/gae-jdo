package com.giago.appengine.commons.string;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class TaggerTest {
	
	@Test
	public void shouldGetMeaningfulWordWithNullSource() {
		String source = null;
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void shouldGetMeaningfulWordWithEmptySource() {
		String source = "";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
	}

	@Test
	public void shouldGetMeaningfulWord1() {
		String source = "unlocking android is and the lock";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals("unlocking", result.get(0));
		Assert.assertEquals("android", result.get(1));
		Assert.assertEquals("lock", result.get(2));
	}
	
	@Test
	public void shouldGetMeaningfulWord2() {
		String source = "modify it under the terms of the GNU Lesser General Public";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(7, result.size());
	}
	
	@Test
	public void shouldGetAllLowerCaseWords() {
		String source = "Modify uNDer GNU";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals("modify", result.get(0));
		Assert.assertEquals("under", result.get(1));
		Assert.assertEquals("gnu", result.get(2));
	}
	
	@Test
	public void shouldGetOnlyUniqueTag() {
		String source = "modify under modify uNder";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("modify", result.get(0));
		Assert.assertEquals("under", result.get(1));
	}

	@Test
	public void shouldRemoveThisExclamationQuestionAndOtherChars() {
		String source = "modify? under! archimede- -eustacchio:";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(4, result.size());
		Assert.assertEquals("modify", result.get(0));
		Assert.assertEquals("under", result.get(1));
		Assert.assertEquals("archimede", result.get(2));
		Assert.assertEquals("eustacchio", result.get(3));
	}

	@Test
	public void shouldCleanOutSingleQuotes() {
		String source = "The 'Virtual Unwrapping' ";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("virtual", result.get(0));
		Assert.assertEquals("unwrapping", result.get(1));
	}

	@Test
	public void shouldCleanOutDoubleQuotes() {
		String source = "\"God is up\" ";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("god", result.get(0));
		Assert.assertEquals("up", result.get(1));
	}

	@Test
	public void shouldCleanOutAllTheBrackets() {
		String source = "(Zawiet [el]-Meitin) {ok}";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals("zawiet", result.get(0));
		Assert.assertEquals("elmeitin", result.get(1));
		Assert.assertEquals("ok", result.get(2));
	}

	@Test
	public void shouldCleanOutSlashAndBackSlash() {
		String source = "UCL/SCA excavations/ test\\test1";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(5, result.size());
		Assert.assertEquals("ucl", result.get(0));
		Assert.assertEquals("sca", result.get(1));
		Assert.assertEquals("excavations", result.get(2));
		Assert.assertEquals("test", result.get(3));
		Assert.assertEquals("test1", result.get(4));
	}

	@Test
	public void shouldCleanOutSomeOtherChar() {
		String source = "Cataract, the. Sudan:";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("cataract", result.get(0));
		Assert.assertEquals("sudan", result.get(1));
	}

	@Test
	public void shouldRemoveGenitive() {
		String source = "Duke Alexander's sarcophagi Alexanders";
		ArrayList<String> result = Tagger.tag(source);
		Assert.assertNotNull(result);
		Assert.assertEquals(4, result.size());
		Assert.assertEquals("duke", result.get(0));
		Assert.assertEquals("alexander", result.get(1));
		Assert.assertEquals("sarcophagi", result.get(2));
		Assert.assertEquals("alexanders", result.get(3));
	}

	@Test
	public void shouldGetALimitedNumberOfTag() {
		String source = "one two tre four five";
		checkLimit(source, 1);
		checkLimit(source, 2);
		checkLimit(source, 3);
		checkLimit(source, 4);
		checkLimit(source, 5);
	}

	@Test
	public void shouldGetALimitedExcludingNotConsideredKeys() {
		String source = "one in two";
		checkLimit(source, 2);
	}
	
	private void checkLimit(String textToTag, int limit) {
		ArrayList<String> result = Tagger.tag(textToTag, limit);
		Assert.assertNotNull(result);
		Assert.assertEquals(limit, result.size());
	}
	

}
