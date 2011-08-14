package com.giago.appengine.commons.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Controller extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controller.class.getSimpleName());
    
    private static final long serialVersionUID = 1L;
    
    private static Map<String, Action> ACTION_REGISTRY; 

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ACTION_REGISTRY = new HashMap<String, Action>(); 
        initActionRegistry(ACTION_REGISTRY);
    }
    
    /**
     * example ACTION_REGISTRY.put("/token", new TokenAction());
     * @param registry
     */
    protected abstract void initActionRegistry(Map<String, Action> registry);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }
    
    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context(req, resp);
        Action action = getAction(context.getUri());
        action.executeRequest(context);
    }
    
    public Action getAction(String uri) {
        if (ACTION_REGISTRY.containsKey(uri)) {
            Action action = ACTION_REGISTRY.get(uri);
            logger.info("Executing action : " + action.getClass().getSimpleName());
            return action;
        } else {
            throw new RuntimeException("No action registered for the request : " + uri);
        }
    }

}
