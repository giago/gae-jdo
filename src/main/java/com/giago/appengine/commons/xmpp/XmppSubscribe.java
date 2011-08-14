package com.giago.appengine.commons.xmpp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.xmpp.Subscription;

public class XmppSubscribe extends XmppServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void handle(HttpServletRequest req) throws IOException {
		Subscription sub = xmpp.parseSubscription(req);

		String from = sub.getFromJid().getId().split("/")[0];
		logger.info("Received request for subscription : " + from);
	}

}
