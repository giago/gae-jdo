package com.giago.appengine.commons.xmpp.util;

import com.google.appengine.api.xmpp.JID;

public class JidUtils {
	
	private static final String APP_DOMAIN = "@docs-sample.appspotchat.com";
	private static final String SEPARATOR = "@";
	private static final String JID_SEPARATOR = "/";
	
	public static final String getJid(String email) {
		int index = email.indexOf(SEPARATOR);
		String toReplace = email.substring(index, email.length());
		String finalEmail = email.replace(toReplace, APP_DOMAIN);
		return finalEmail;
	}
	
	public String getRelevantPart(JID jid) {
		return jid.getId().split(JID_SEPARATOR)[0];
	}
	
	public String getRelevantPart(String jidId) {
		return jidId.split(JID_SEPARATOR)[0];
	}

}
