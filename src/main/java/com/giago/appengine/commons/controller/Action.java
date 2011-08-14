package com.giago.appengine.commons.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Logger;


public abstract class Action {

	private static final String CONTENT_TYPE = "text/plain";

	private static final String ENCODING = "UTF-8";

	protected static final Logger logger = Logger.getLogger(Action.class.getName());

	public void executeRequest(Context context) {
		execute(context);
	}

	protected abstract void execute(Context context);

	protected void returnContent(String content, Context context) {
		context.getHttpServletResponse().setContentType(CONTENT_TYPE);
		context.getHttpServletResponse().setContentLength(content.length());
		PrintWriter out = null;
		try {
			out = context.getHttpServletResponse().getWriter();
		} catch (IOException e) {
			throw new RuntimeException("Problem sending back the result");
		}
		out.println(content);
		out.close();
		out.flush();
	}

	protected void sendRedirect(Context context, String redirectUrl) {
		try {
			context.getHttpServletResponse().sendRedirect(redirectUrl);
		} catch (IOException e) {
			throw new RuntimeException("Problem executing redirect for "
					+ redirectUrl);
		}
	}

	protected String getContent(Context context) {
		try {
			BufferedReader dis;
			StringBuffer source = new StringBuffer();
			String line;
			dis = new BufferedReader(new InputStreamReader(context
					.getHttpServletRequest().getInputStream(), ENCODING));
			while ((line = dis.readLine()) != null) {
				source.append(line);
			}
			dis.close();
			return source.toString();
		} catch (IOException e) {
			throw new RuntimeException(
					"Problem with get source in the html client.", e);
		}
	}

}
