package com.giago.appengine.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class AbstractBaseFilter implements Filter {
	
	private static final long serialVersionUID = 1L;
	protected static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException { }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request,ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType(CONTENT_TYPE);
		execute(request);
        chain.doFilter(request, response);
	}
	
	protected abstract void execute(ServletRequest request);

	protected void setAttribute(ServletRequest request, String key, Object value) {
	    request.setAttribute(key, value);
	}
	
}
