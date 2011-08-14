package com.giago.appengine.commons.xmpp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.giago.appengine.commons.xmpp.util.JidUtils;
import com.google.appengine.api.xmpp.Presence;
import com.google.appengine.api.xmpp.PresenceType;

public abstract class XmppPresenceServlet extends XmppServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void handle(HttpServletRequest req) throws IOException {
		PresenceType type = getPresenceType();
		logger.info("presence change : " + type.toString());
		Presence presence = xmpp.parsePresence(req);
		String from = getFrom(presence);
		String jidString = JidUtils.getJid(from);
		informClient(jidString);
	}
	
	private void informClient(String jidString) {
	    informClient(jidString, getPresenceType().toString());
	}

	protected abstract PresenceType getPresenceType();

}
