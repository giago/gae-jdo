
package com.giago.appengine.commons.feed.source;

import java.util.Date;
import java.util.Iterator;


import com.giago.appengine.commons.string.StringUtils;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

public class GaeFeedItemSource implements FeedItemSource {

    private static final int DEFAULT_PAGE_SIZE = 25;

    private static final String DASH = "-";

    private static final String SPACE = " ";

    private String itemKind;

    private String publishedDateProperty;

    private String titleProperty;

    private String authorProperty;

    private String descriptionProperty;

    private String urlRelativaPath;

    private DatastoreService ds;

    private int pageSize;

    private Iterator<Entity> iterator;

    private Entity currentEntity;

    public GaeFeedItemSource(DatastoreService ds, String urlRelativaPath, String itemKind,
            String publishedDateProperty, String titleProperty, String authorProperty,
            String descriptionProperty, int pageSize) {
        this.itemKind = itemKind;
        this.publishedDateProperty = publishedDateProperty;
        this.titleProperty = titleProperty;
        this.authorProperty = authorProperty;
        this.urlRelativaPath = urlRelativaPath;
        this.itemKind = itemKind;
        this.descriptionProperty = descriptionProperty;
        this.pageSize = pageSize;
        this.ds = ds;
    }

    public GaeFeedItemSource(DatastoreService ds, String urlRelativaPath, String itemKind,
            String publishedDateProperty, String titleProperty, String authorProperty,
            String idProperty, String descriptionProperty) {
        this(ds, urlRelativaPath, itemKind, publishedDateProperty, titleProperty, authorProperty,
                descriptionProperty, DEFAULT_PAGE_SIZE);
    }

    @Override
    public boolean next() {
        if (iterator == null) {
            Query q = new Query(itemKind);
            if (publishedDateProperty != null) {
                q.addSort(publishedDateProperty, SortDirection.DESCENDING);
            }
            iterator = ds.prepare(q).asIterator(FetchOptions.Builder.withLimit(pageSize));
        }
        if (iterator.hasNext()) {
            currentEntity = iterator.next();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getTitle() {
        return StringUtils.validateToXML((String)currentEntity.getProperty(titleProperty));
    }

    @Override
    public String getLink() {
        String title = (String)currentEntity.getProperty(titleProperty);
        title = title.replaceAll(SPACE, DASH);
        return urlRelativaPath + title + DASH + currentEntity.getKey().getId();
    }

    @Override
    public Date getPublishedDate() {
        return (Date)currentEntity.getProperty(publishedDateProperty);
    }

    @Override
    public String getValue() {
        return StringUtils.validateToXML((String)currentEntity.getProperty(descriptionProperty));
    }

    @Override
    public String getAuthor() {
        return (String)currentEntity.getProperty(authorProperty);
    }

}
