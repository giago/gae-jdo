package com.giago.appengine.commons.sample;

import org.junit.Test;

import com.giago.appengine.commons.test.BaseDatastoreTestCase;
import com.google.appengine.api.datastore.EntityNotFoundException;

/**
 * @author Luigi Agosti <luigi.agosti@gmail.com>
 */
public class GuineaPigDaoImplTest extends BaseDatastoreTestCase {
	
	//private GuineaPigDao dao = new GuineaPigDaoImpl();
	
	//@Ignore // I can't test because I need to setup the all datanucleous enhancement process
	@Test
	public void shouldAddAGuineaPig() throws EntityNotFoundException {
//		GuineaPig model = new GuineaPig();
//		model.setName("arturo");
//		dao.save(model);
//		
//		Map<String, Object> properties = new HashMap<String, Object>();
//		properties.put("name", "arturo");
//		
//		DatastoreAssert.assertEntintySaved(ds, GuineaPig.class.getSimpleName(), model.getId(), properties);
	}

}