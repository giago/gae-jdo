package com.giago.appengine.commons.xmpp;

import java.util.logging.Logger;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;

public class XmppReceiver extends XmppServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(XmppReceiver.class.getName());

	private static final String SEPARATOR = "#-#";
	private static final String MESSAGE_TAG = "msg:";
	
	@Override
	protected void handle(Message message) {
		JID fromJid = message.getFromJid();
		JID toJid = message.getRecipientJids()[0];
        String body = message.getBody();
        String from = getRelevantPart(fromJid);
        logger.info("Received message from : " + fromJid.getId() + " to : " + toJid + " body : " + body);
        
        informClient(getRelevantPart(toJid), MESSAGE_TAG + from + SEPARATOR + body);
	}
	
	
}
