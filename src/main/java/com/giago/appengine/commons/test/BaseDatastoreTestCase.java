package com.giago.appengine.commons.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class BaseDatastoreTestCase {

	private LocalServiceTestHelper helper = new LocalServiceTestHelper(
		new LocalDatastoreServiceTestConfig(), new LocalMemcacheServiceTestConfig()
	);
	
	protected DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	@Before
	public void setUp() {
		helper.setUp();
	}
	
	@After
	public void tearDown() {
		helper.tearDown();
	}
	
    protected void assertEqualsFromFile(Class<?> clazz, String fileName, String actual) {
        String expected = IOUtils.getClassResourceContent(clazz, fileName);
        IOUtils.assertEqualsSkippingSpacesAndReturnCarriage(expected, actual);
    }
    
    protected void assertEqualsFromFileSpaceCheck(Class<?> clazz, String fileName, String actual) {
        String expected = IOUtils.getClassResourceContent(clazz, fileName);
        Assert.assertEquals(expected, actual);
    }
	
}
