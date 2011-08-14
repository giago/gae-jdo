package com.giago.appengine.commons.feed;

import java.util.ArrayList;
import java.util.List;


import com.giago.appengine.commons.feed.source.FeedItemSource;
import com.giago.appengine.commons.feed.source.FeedSource;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

public class FeedBuilder {

    private FeedSource feedSource;
    
    private FeedItemSource feedItemSource;
    
    public FeedBuilder(FeedSource feedSource, FeedItemSource feedItemSource) {
        if(feedSource == null) {
            throw new RuntimeException("Problem generating the feed the feedSource can't be null");
        }
        this.feedSource = feedSource;
        this.feedItemSource = feedItemSource;
    }

    public String build() {
        try {
            SyndFeed feed = new SyndFeedImpl();
            setFeedMetadata(feed);
            setFeedItems(feed);
            SyndFeedOutput sfo = new SyndFeedOutput();
            return sfo.outputString(feed);
        } catch (Exception e) {
            throw new RuntimeException("Problem generating the feed " + e.getMessage());
        }
    }

    private void setFeedMetadata(SyndFeed feed) {
        feed.setFeedType(feedSource.getType());
        feed.setTitle(feedSource.getTitle());
        feed.setDescription(feedSource.getDescription());
        feed.setLink(feedSource.getLink());
        feed.setAuthor(feedSource.getAuthor());
        feed.setPublishedDate(feedSource.getModifiedDate());
    }

    private void setFeedItems(SyndFeed feed) {
        if(feedItemSource == null) {
            return;
        }
        List<SyndEntry> entries = new ArrayList<SyndEntry>();
        SyndEntry entry;
        SyndContent description;
        while(feedItemSource.next()) {
            entry = new SyndEntryImpl();
            entry.setTitle(feedItemSource.getTitle());
            entry.setLink(feedItemSource.getLink());
            entry.setPublishedDate(feedItemSource.getPublishedDate());
            entry.setAuthor(feedItemSource.getAuthor());
            description = new SyndContentImpl();
            //description.setType(feedItemSource.getType());
            description.setValue(feedItemSource.getValue());
            entry.setDescription(description);
            entries.add(entry);
        }
        feed.setEntries(entries);
    }
    
}
