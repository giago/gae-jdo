package com.giago.appengine.commons.xmpp;

import com.google.appengine.api.xmpp.PresenceType;

public class XmppPresenceAvailable extends XmppPresenceServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected PresenceType getPresenceType() {
		return PresenceType.AVAILABLE;
	}

}
