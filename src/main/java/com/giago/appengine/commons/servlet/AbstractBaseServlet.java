package com.giago.appengine.commons.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractBaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public interface ContentType {
	    
	    String plainText = "text/plain";
	    
	    String xml = "text/xml";
	    
	    String atom = "application/atom+xml;charset=utf-8";
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getAndPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getAndPost(req, resp);
	}

	protected abstract void getAndPost(HttpServletRequest req, HttpServletResponse resp);

	protected void returnContent(String content, HttpServletResponse response) {
	    returnContent(content, response, ContentType.plainText);
	}
	
	protected void returnContent(String content, HttpServletResponse response, String contentType) {
        response.setContentType(contentType);
        response.setContentLength(content.length());
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException("Problem sending back the result");
        }
        out.println(content);
        out.close();
        out.flush();
    }
	
}
