package com.giago.appengine.commons.feed.source;

import java.util.Date;

public interface FeedItemSource {

    boolean next();

    String getTitle();

    String getLink();

    Date getPublishedDate();

    String getValue();

    String getAuthor();

}
