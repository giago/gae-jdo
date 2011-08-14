package com.giago.appengine.commons.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Context {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private String uri;

    public Context(String uri) {
        this.uri = uri;
    }

    public Context(HttpServletRequest request, HttpServletResponse response) {
        this(request.getRequestURI());
        this.request = request;
        this.response = response;
    }
    
    public String getUri() {
        return uri;
    }

    public HttpServletResponse getHttpServletResponse() {
        return response;
    }

    public HttpServletRequest getHttpServletRequest() {
        return request;
    }

    public String getParameter(String name) {
        return request.getParameter(name);
    }

}
