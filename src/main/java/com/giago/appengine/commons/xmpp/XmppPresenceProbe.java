package com.giago.appengine.commons.xmpp;

import com.google.appengine.api.xmpp.PresenceType;

public class XmppPresenceProbe extends XmppPresenceServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected PresenceType getPresenceType() {
		return PresenceType.PROBE;
	}
	
}
