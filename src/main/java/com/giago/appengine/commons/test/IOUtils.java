package com.giago.appengine.commons.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.junit.Assert;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public abstract class IOUtils {

	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public static String getClassResourceContent(Class<?> clazz, String resourceName) {
		InputStream io = clazz.getResourceAsStream(resourceName);
		Assert.assertNotNull("File " + resourceName + " not found in the classpath of the class " + clazz, io);
		return toString(io);
	}
	
	public static String toString(Reader input) {
		try {
			StringWriter output = new StringWriter();
			char[] buffer = new char[DEFAULT_BUFFER_SIZE];
	        int count = 0;
	        int n = 0;
	        while (-1 != (n = input.read(buffer))) {
	            output.write(buffer, 0, n);
	            count += n;
	        }
	        return output.toString();
		} catch(IOException e){
			throw new RuntimeException(e);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public static String toString(InputStream stream){
		return toString(new InputStreamReader(stream));
	}
	
	public static final void toFile(String filename, String content) {
	    try {
	        new FileWriter(new File(filename)).write(content); 
	    } catch(IOException e){
	        e.printStackTrace();
            throw new RuntimeException(e);
        }
	}
	
	public static final void assertEqualsSkippingSpacesAndReturnCarriage(String expected, String actual) {
		if(actual == null) {
			Assert.fail("actual string is null");
		}
		Assert.assertEquals(removeSpacesAndReturnCarriage(expected), removeSpacesAndReturnCarriage(actual));
	}
	
	private static final String removeSpacesAndReturnCarriage(String toclean) {
		return toclean.replaceAll("\r\n", "").replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
	}
	
}
