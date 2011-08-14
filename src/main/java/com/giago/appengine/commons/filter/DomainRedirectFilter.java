package com.giago.appengine.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.giago.appengine.commons.string.UrlParser;

public class DomainRedirectFilter implements Filter {

    private static String OLD_DOMAIN_PARAMETER = "old-domain";
    private static String NEW_DOMAIN_PARAMETER = "new-domain";
    private static final int PERMANENT_REDIRECT = 301;
    
    private static String OLD_DOMAIN = "";
    private static String NEW_DOMAIN = "";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        OLD_DOMAIN = filterConfig.getInitParameter(OLD_DOMAIN_PARAMETER);
        NEW_DOMAIN = filterConfig.getInitParameter(NEW_DOMAIN_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest)request).getRequestURL().toString();
        if(uri.contains("task") && uri.contains("keepAliveTask")) {
        	chain.doFilter(request, response);
        } else if(uri.contains("queue") && uri.contains("keepAliveQueue")) {
        	chain.doFilter(request, response);
        } else if(uri.contains("." + OLD_DOMAIN)) {
        	chain.doFilter(request, response);
        } else if(UrlParser.containsHost(uri, OLD_DOMAIN)) {
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.setStatus(PERMANENT_REDIRECT);
            resp.sendRedirect(UrlParser.changeHostIfMatch(uri, OLD_DOMAIN, NEW_DOMAIN));
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}

}
