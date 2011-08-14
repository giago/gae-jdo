package com.giago.appengine.commons.test;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public class BaseQueueTestCase {
	
	private LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalTaskQueueTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
}
