
package com.giago.appengine.commons.feed.source;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.giago.appengine.commons.test.BaseDatastoreTestCase;
import com.giago.appengine.commons.util.GaeEntitySampleGenerator;

public class GaeFeedItemSourceTest extends BaseDatastoreTestCase {

    @Test
    public void shouldGetListOfItem() {
    	GaeEntitySampleGenerator.article(ds);
        
        GaeFeedItemSource itemSource = new GaeFeedItemSource(ds, "prefix/article/", "Article",
                "createdDate", "title", "author", "id", "description");
        
        int counter = 0;
        while (itemSource.next()) {
            assertEquals("luigi", itemSource.getAuthor());
            assertEquals("Gwt and maven", itemSource.getTitle());
            assertEquals("prefix/article/Gwt-and-maven-1", itemSource.getLink());
            assertEquals(new Date(0), itemSource.getPublishedDate());
            assertEquals("How to add support for get to the build", itemSource.getValue());
            counter++;
        }
        assertEquals(1, counter);
    }
    
    @Test
    public void shouldGetListOfItemLimited() {
    	GaeEntitySampleGenerator.articles(ds);
        
        GaeFeedItemSource itemSource = new GaeFeedItemSource(ds, "prefix/article/", "Article",
                "createdDate", "title", "author", "description", 1);
        
        int counter = 0;
        while (itemSource.next()) {
            counter++;
        }
        assertEquals(1, counter);
    }
    
    @Test
    public void shouldBeOrderedByDate() {
    	GaeEntitySampleGenerator.articles(ds);
        
        GaeFeedItemSource itemSource = new GaeFeedItemSource(ds, "prefix/article/", "Article",
                "createdDate", "title", "author", "description", 2);

        itemSource.next();
        assertEquals("pippo", itemSource.getAuthor());
        itemSource.next();
        assertEquals("luigi", itemSource.getAuthor());
    }
    
    @Test
    public void shouldBeOrderedByDateAndLimited() {
    	GaeEntitySampleGenerator.articles(ds);
        
        GaeFeedItemSource itemSource = new GaeFeedItemSource(ds, "prefix/article/", "Article",
                "createdDate", "title", "author", "description", 1);

        itemSource.next();
        assertEquals("pippo", itemSource.getAuthor());
    }

}
