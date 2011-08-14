package com.giago.appengine.commons.xmpp;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.Presence;
import com.google.appengine.api.xmpp.PresenceShow;
import com.google.appengine.api.xmpp.PresenceType;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public abstract class XmppServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected static final XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	
	protected static final Logger logger = Logger.getLogger(XmppPresenceAvailable.class.getName());	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req);
	}
	
	protected void handle(HttpServletRequest req) throws IOException {
		Message message = xmpp.parseMessage(req);
        handle(message);
	}

	protected void handle(Message message) {
	}
	
	protected void changePresence(String jid, PresenceType type, PresenceShow show, String status) {
		xmpp.sendPresence(new JID(jid), PresenceType.AVAILABLE, show, status);
	}
	
	protected String getFrom(Presence presence) {
		return getRelevantPart(presence.getFromJid());
	}
	
	protected String getRelevantPart(JID jid) {
		return jid.getId().split("/")[0];
	}
	
	protected void informClient(String jidString, String message) {
		logger.info("Sending presence to client with jid : " + jidString + " msg : " + message);
		ChannelService channelService = ChannelServiceFactory.getChannelService();
	    channelService.sendMessage(new ChannelMessage(jidString, message));
	}
	
}
