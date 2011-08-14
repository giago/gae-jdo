package com.giago.appengine.commons.feed.source;

import java.util.Date;

public interface FeedSource {
    
    String getAuthor();
    
    String getDescription();
    
    String getLink();
    
    Date getModifiedDate();
    
    String getTitle();

    String getType();

}
