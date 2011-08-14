package com.giago.appengine.commons.sitemap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.giago.appengine.commons.servlet.AbstractBaseServlet;
import com.giago.appengine.commons.servlet.RequestWrapper;
import com.giago.appengine.commons.sitemap.introspector.SitemapEntityIntrospector;
import com.giago.appengine.commons.sitemap.source.GaeSitemapSource;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class SitemapServlet extends AbstractBaseServlet {

    private static final Logger logger = Logger.getLogger(SitemapServlet.class.getName());
    
    private static final long serialVersionUID = 1L;
    
    private DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    
    private static final String PROTOCOL = "http://";
    
    private static final String PORT_SEPARATOR = ":";
    
    private static final String COMMA = ",";
    
    private static final String ENTITIES = "entities";
    
    private static final String SITEMAPS = "sitemaps";
    
    private static List<SitemapEntity> entities;
    
    private static List<String> sitemaps;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        entities = new ArrayList<SitemapEntity>();
        String classes = config.getInitParameter(ENTITIES);
        if(classes != null) {
            SitemapEntityIntrospector introspector = new SitemapEntityIntrospector();
            for(String entityClass : Arrays.asList(config.getInitParameter(ENTITIES).split(COMMA))) {
                entities.add(introspector.inspect(entityClass));
            }
        }
        sitemaps = Arrays.asList(config.getInitParameter(SITEMAPS).split(COMMA));        
    }
    
    @Override
    protected void getAndPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String hostname = ((HttpServletRequest)request).getServerName();
            int port = ((HttpServletRequest)request).getServerPort();
            String host = PROTOCOL + hostname;
            if(port != 80) {
            	host = host + PORT_SEPARATOR + port;
            }
            RequestManager rm = new RequestManager(request);
            String entity = rm.getEntity(); 
            int year = rm.getYear();
            int month = rm.getMonth();
            int day = rm.getDay();
            
            List<GaeSitemapSource> sources = new ArrayList<GaeSitemapSource>();
            for(SitemapEntity sitemapEntity : entities) {
                GaeSitemapSource gms = new GaeSitemapSource(ds, sitemapEntity);
                sources.add(gms);
            }
            SitemapBuilder fg = new SitemapBuilder(host, sources);
            String result = "";
            if(year == -1 && entity == null) {
            	result = fg.build(sitemaps);
            } else if (year == -1 && entity != null) {
                result = fg.build(entity);
            } else if(month == -1 || day == -1) {
            	result = fg.build(year, entity);
            } else {
            	result = fg.build(year, month, day, entity);
            }
            returnContent(result, response, AbstractBaseServlet.ContentType.xml);
        } catch (Throwable e) {
            logger.severe("Problem while generating of the sitemap : " + e.getMessage());
            returnContent("error", response);
        }
    }
    
    private class RequestManager extends RequestWrapper {
    	private static final String YEAR = "year";
    	private static final String MONTH = "month";
    	private static final String DAY = "day";
    	private static final String ENTITY = "entity";
    	public RequestManager(HttpServletRequest req) {
    		super(req);
    	}
    	public int getYear() {
    		return getParameterAsInteger(YEAR, -1);
    	}
    	public int getMonth() {
    		return getParameterAsInteger(MONTH, -1);
    	}
    	public int getDay() {
    		return getParameterAsInteger(DAY, -1);
    	}
    	public String getEntity() {
            return getParameterAsString(ENTITY, null);
        }
    }
    
}