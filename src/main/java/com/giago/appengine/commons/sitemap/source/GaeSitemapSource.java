
package com.giago.appengine.commons.sitemap.source;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.giago.appengine.commons.sitemap.SitemapEntity;
import com.giago.appengine.commons.util.DateUtils;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

public class GaeSitemapSource implements SitemapSource {

    private DatastoreService ds;

    private Iterator<Entity> entityIterator;

    private Entity currentEntity;

    private SitemapEntity sitemapEntity;

    public GaeSitemapSource(DatastoreService ds, SitemapEntity sitemapEntity) {
        this.ds = ds;
        this.sitemapEntity = sitemapEntity;
    }

    @Override
    public List<Integer> days(int year, int month) {
        List<Integer> days = new ArrayList<Integer>();

        for (int i = 1; i <= DateUtils.getLastDayOf(year, month); i++) {
            Query q = new Query(sitemapEntity.getKind());
            addBetweenFilter(q, sitemapEntity.getCreatedDateProperty(),
                    DateUtils.getBeginningOfDay(year, month, i),
                    DateUtils.getEndOfDay(year, month, i));
            PreparedQuery pq = ds.prepare(q);
            List<Entity> e = pq.asList(FetchOptions.Builder.withLimit(1));
            if (e.size() == 1) {
                days.add(Integer.valueOf(i));
            }
        }

        return days;
    }

    @Override
    public List<Integer> months(int year) {
        List<Integer> months = new ArrayList<Integer>();

        for (int i = 1; i <= 12; i++) {
            Query q = new Query(sitemapEntity.getKind());
            addBetweenFilter(q, sitemapEntity.getCreatedDateProperty(),
                    DateUtils.getBeginningOfMonth(year, i), DateUtils.getEndOfMonth(year, i));
            List<Entity> e = ds.prepare(q).asList(FetchOptions.Builder.withLimit(1));
            if (e.size() == 1) {
                months.add(Integer.valueOf(i));
            }
        }

        return months;
    }

    @Override
    public List<Integer> years() {
        List<Integer> years = new ArrayList<Integer>();

        Query q = new Query(sitemapEntity.getKind());
        q.addSort(sitemapEntity.getCreatedDateProperty(), SortDirection.ASCENDING);
        List<Entity> e = ds.prepare(q).asList(FetchOptions.Builder.withLimit(1));
        int start;
        if (e.size() == 1) {
            start = DateUtils.getYear((Date)e.get(0).getProperty(
                    sitemapEntity.getCreatedDateProperty()));
        } else {
            return years;
        }

        q = new Query(sitemapEntity.getKind());
        q.addSort(sitemapEntity.getCreatedDateProperty(), SortDirection.DESCENDING);
        e = ds.prepare(q).asList(FetchOptions.Builder.withLimit(1));

        int end = DateUtils.getYear((Date)e.get(0).getProperty(
                sitemapEntity.getCreatedDateProperty()));

        for (int i = start; i <= end; i++) {
            years.add(i);
        }
        return years;
    }

    @Override
    public void prepare(int year, int month, int day) {
        Query q = new Query(sitemapEntity.getKind());
        addBetweenFilter(q, sitemapEntity.getCreatedDateProperty(),
                DateUtils.getBeginningOfDay(year, month, day),
                DateUtils.getEndOfDay(year, month, day));
        PreparedQuery pq = ds.prepare(q);
        entityIterator = pq.asIterator();
    }

    @Override
    public Date getCreatedDate() {
        return (Date)currentEntity.getProperty(sitemapEntity.getCreatedDateProperty());
    }

    @Override
    public String getRelativeUrl() {
        return sitemapEntity.buildRelativeUrl((String)currentEntity.getProperty(sitemapEntity.getTitleProperty()), currentEntity.getKey().getId());
    }

    @Override
    public boolean next() {
        if (entityIterator.hasNext()) {
            currentEntity = entityIterator.next();
            return true;
        }
        return false;
    }

    private void addBetweenFilter(Query q, String property, Date start, Date end) {
        q.addFilter(property, FilterOperator.GREATER_THAN_OR_EQUAL, start);
        q.addFilter(property, FilterOperator.LESS_THAN_OR_EQUAL, end);
    }

    @Override
    public String getKind() {
        return sitemapEntity.getKind();
    }

    @Override
    public boolean isCreatedDateDefined() {
        if(sitemapEntity.getCreatedDateProperty() == null) {
            return false;
        }
        return true;
    }

    @Override
    public void prepare() {
        Query q = new Query(sitemapEntity.getKind());
        PreparedQuery pq = ds.prepare(q);
        entityIterator = pq.asIterator();
    }

}
