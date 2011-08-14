
package com.giago.appengine.commons.feed;

import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.giago.appengine.commons.feed.source.FeedSource;
import com.giago.appengine.commons.feed.source.GaeFeedItemSource;
import com.giago.appengine.commons.feed.source.StaticFeedSource;
import com.giago.appengine.commons.servlet.AbstractBaseServlet;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class FeedServlet extends AbstractBaseServlet {

    private static final Logger logger = Logger.getLogger(FeedServlet.class.getName());
    
    private static final long serialVersionUID = 1L;
    
    private DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    
    private static String feedTitle;
    
    private static String feedAuthor;
    
    private static String feedDescription;
    
    private static String feedLink;
    
    private static String feedItemLinkPrefix;
    
    private static String feedItemKind;
    
    private static String feedItemCreatedDateProperty;
    
    private static String feedItemTitleProperty;
    
    private static String feedItemAuthorProperty;
    
    private static String feedItemIdProperty;
    
    private static String feedItemDescriptionProperty;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        feedTitle = config.getInitParameter("feed-title");
        feedAuthor = config.getInitParameter("feed-author");
        feedDescription = config.getInitParameter("feed-description");
        feedLink = config.getInitParameter("feed-link");

        feedItemKind = config.getInitParameter("feedItem-kind");
        feedItemLinkPrefix = config.getInitParameter("feedItem-linkPrefix");
        feedItemCreatedDateProperty = config.getInitParameter("feedItem-createdDateProperty");
        feedItemTitleProperty = config.getInitParameter("feedItem-titleProperty");
        feedItemAuthorProperty = config.getInitParameter("feedItem-authorProperty");
        feedItemIdProperty = config.getInitParameter("feedItem-idProperty");
        feedItemDescriptionProperty = config.getInitParameter("feedItem-descriptionProperty");
    }
    
    @Override
    protected void getAndPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = ((HttpServletRequest)request).getRequestURL().toString();
            FeedSource feedSource = StaticFeedSource.Builder.init(feedTitle).author(feedAuthor)
                .description(feedDescription).link(url).modifiedDate(new Date()).build();
            
            url = url.replace(feedLink, feedItemLinkPrefix);
            
            GaeFeedItemSource itemSource = new GaeFeedItemSource(ds, feedItemLinkPrefix, feedItemKind,
                    feedItemCreatedDateProperty, feedItemTitleProperty, feedItemAuthorProperty, 
                    feedItemIdProperty, feedItemDescriptionProperty);
            
            FeedBuilder fg = new FeedBuilder(feedSource, itemSource);
            
            returnContent(fg.build(), response, AbstractBaseServlet.ContentType.atom);
        } catch (Throwable e) {
            logger.severe("Problem while generating the feed : " + e.getMessage());
            returnContent("error", response);
        }
    }

}
