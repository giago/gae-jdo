package com.giago.appengine.commons.sample;

import com.giago.appengine.commons.dao.jdo.BaseDaoImpl;

public class GuineaPigDaoImpl extends BaseDaoImpl<GuineaPig> implements GuineaPigDao {

	public GuineaPigDaoImpl() {
		super(GuineaPig.class);
	}

}
