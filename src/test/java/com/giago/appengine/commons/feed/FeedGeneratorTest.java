
package com.giago.appengine.commons.feed;

import java.util.Date;

import org.junit.Test;

import com.giago.appengine.commons.feed.source.FeedItemSource;
import com.giago.appengine.commons.feed.source.FeedSource;
import com.giago.appengine.commons.feed.source.GaeFeedItemSource;
import com.giago.appengine.commons.feed.source.StaticFeedSource;
import com.giago.appengine.commons.test.BaseDatastoreTestCase;
import com.giago.appengine.commons.util.GaeEntitySampleGenerator;

public class FeedGeneratorTest extends BaseDatastoreTestCase {

    @Test
    public void basicFeed() {
        FeedSource feedSource = StaticFeedSource.Builder.init("title").author("author")
                .description("description").link("link").modifiedDate(new Date(0)).build();

        FeedBuilder generator = new FeedBuilder(feedSource, null);

        assertEqualsFromFile(FeedGeneratorTest.class, "1.xml", generator.build());
    }

    @Test
    public void feedWithItem() {
    	GaeEntitySampleGenerator.articles(ds);
        
        FeedSource feedSource = StaticFeedSource.Builder.init("title").author("luigi")
                .description("description").link("link").modifiedDate(new Date(0)).build();
        FeedItemSource feedItemSource = new GaeFeedItemSource(ds, "prefix/article/", "Article",
                "createdDate", "title", "author", "id", "description");

        FeedBuilder generator = new FeedBuilder(feedSource, feedItemSource);
        
        assertEqualsFromFile(FeedGeneratorTest.class, "3.xml", generator.build());
    }

}
