
package com.giago.appengine.commons.sitemap.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.giago.appengine.commons.http.url.DashWithoutIdUrlGenerator;
import com.giago.appengine.commons.sitemap.SitemapEntity;
import com.giago.appengine.commons.test.BaseDatastoreTestCase;
import com.giago.appengine.commons.util.DateUtils;
import com.giago.appengine.commons.util.GaeEntitySampleGenerator;
import com.google.appengine.api.datastore.DatastoreService;

public class GaeSitemapSourceTest extends BaseDatastoreTestCase {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH mm ss SSS");

    @Test
    public void shouldGetYearOfListOfEntity() {
        GaeEntitySampleGenerator.articles(ds);

        GaeSitemapSource source = prepareStandardSource(ds);
        assertTrue(source.isCreatedDateDefined());
        List<Integer> years = source.years();

        assertNotNull(years);
        assertEquals(Arrays.asList(Integer.valueOf(1970)), years);
    }

    @Test
    public void shouldGetYearOfListOfWithRange() {
        GaeEntitySampleGenerator.article(ds, DateUtils.getDateForYear(1970));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDateForYear(1972));

        GaeSitemapSource source = prepareStandardSource(ds);
        List<Integer> years = source.years();

        assertNotNull(years);
        assertEquals(
                Arrays.asList(Integer.valueOf(1970), Integer.valueOf(1971), Integer.valueOf(1972)),
                years);
    }

    @Test
    public void shouldGetOnlyEntitiesOfThatDay() {
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 12, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1971, 12, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 11, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 12, 24));

        GaeSitemapSource source = prepareStandardSource(ds);

        source.prepare(1970, 12, 25);
        assertTrue("no item in the list", source.next());
        assertEquals("1970 12 25 01 01 01 001", formatter.format(source.getCreatedDate()));
        assertEquals("/article/Gwt+and+maven-1", source.getRelativeUrl());
        assertFalse(source.next());
    }

    @Test
    public void shouldGetMoreThanOneEntity() {
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 12, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1971, 12, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 12, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 12, 24));

        GaeSitemapSource source = prepareStandardSource(ds);

        source.prepare(1970, 12, 25);
        assertTrue(source.next());
        assertTrue(source.next());
        assertFalse(source.next());
    }

    @Test
    public void shouldGetOnlyExistingMonths() {
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 2, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 2, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1971, 8, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 10, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 12, 24));

        GaeSitemapSource source = prepareStandardSource(ds);

        assertEquals(Arrays.asList(2, 10, 12), source.months(1970));
    }

    @Test
    public void shouldGetOnlyExistingDays() {
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 2, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1971, 3, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 3, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 2, 25));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 2, 12));
        GaeEntitySampleGenerator.article(ds, DateUtils.getDate(1970, 2, 1));

        GaeSitemapSource source = prepareStandardSource(ds);

        assertEquals(Arrays.asList(1, 12, 25), source.days(1970, 2));
    }
    
    @Test
    public void shouldGetListOfEntityThatDoNotSupportCreatedDate() {
        GaeEntitySampleGenerator.articles(ds);

        GaeSitemapSource source = prepareSourceWithNullDate(ds);
        assertFalse(source.isCreatedDateDefined());

        source.prepare();
        
        assertTrue(source.next());
        assertEquals("/article/Gwt-and-maven", source.getRelativeUrl());
        assertTrue(source.next());
        assertEquals("/article/App-Engine-maven", source.getRelativeUrl());
        assertFalse(source.next());
    }

    public static final GaeSitemapSource prepareStandardSource(DatastoreService ds) {
        SitemapEntity se = new SitemapEntity();
        se.setKind("Article");
        se.setCreatedDateProperty("createdDate");
        se.setTitleProperty("title");
        return new GaeSitemapSource(ds, se);
    }

    public static final GaeSitemapSource prepareSourceWithNullDate(DatastoreService ds) {
        SitemapEntity se = new SitemapEntity();
        se.setKind("Article");
        se.setCreatedDateProperty(null);
        se.setTitleProperty("title");
        se.setUrlGenerator(new DashWithoutIdUrlGenerator());
        return new GaeSitemapSource(ds, se);
    }
}
