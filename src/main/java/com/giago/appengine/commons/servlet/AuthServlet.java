package com.giago.appengine.commons.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthServlet extends AbstractBaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void getAndPost(HttpServletRequest req, HttpServletResponse resp) {
		UserService userService = UserServiceFactory.getUserService();
		if(userService.isUserLoggedIn()) {
			returnContent("{\"status\":\"yes\"}", resp);
		}
		returnContent("{\"status\":\"no\"}", resp);
	}

}
