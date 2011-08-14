
package com.giago.appengine.commons.sitemap;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.giago.appengine.commons.sitemap.source.StaticSitemapSource;
import com.giago.appengine.commons.test.BaseDatastoreTestCase;

public class SitemapBuilderTest extends BaseDatastoreTestCase {

    @Test
    public void shouldGetBackTheYearLevelSiteMapEvenWithoutEntities() {
        String result = prepareBuilder().build();

        assertEqualsFromFile(SitemapBuilderTest.class, "empty-sitemap.xml", result);
    }

    @Test
    public void shouldAddStaticSitemap() {
        String result = prepareBuilder().build(Arrays.asList("/sitemap.jsp"));

        assertEqualsFromFile(SitemapBuilderTest.class, "sitemap-static-reference.xml", result);
    }

    @Test
    public void shouldGetBackTheYearLevelSiteMap() {
        StaticSitemapSource ss = new StaticSitemapSource("Article", Arrays.asList(Integer.valueOf(1990),
                Integer.valueOf(1991)));
        String result = prepareBuilder(ss).build();

        assertEqualsFromFile(SitemapBuilderTest.class, "year-sitemap.xml", result);
    }
    
    @Test
    public void shouldGetBackTheYearLevelSiteMapWithMultipleEntities() {
        StaticSitemapSource ss1 = new StaticSitemapSource("Article", Arrays.asList(Integer.valueOf(1990),
                Integer.valueOf(1991)));
        StaticSitemapSource ss2 = new StaticSitemapSource("Tag", Arrays.asList(Integer.valueOf(1992),
                Integer.valueOf(1993)));
        String result = prepareBuilder(ss1, ss2).build();

        assertEqualsFromFile(SitemapBuilderTest.class, "multiple-entity-year-sitemap.xml", result);
    }

    @Test
    public void shouldGetBackTheListOfSitemapsForASpecificMonth() {
        StaticSitemapSource ss = new StaticSitemapSource("Article") {
            @Override
            public List<Integer> days(int year, int month) {
                return Arrays.asList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(356));
            }
        };
        String result = prepareBuilder(ss).build(1900, "Article");

        assertEqualsFromFile(SitemapBuilderTest.class, "month-sitemap.xml", result);
    }

    @Test
    public void shouldGetSitemapForEntitySample() {
        StaticSitemapSource ss = new StaticSitemapSource(Arrays.asList("/article1", "/article2"),
                Arrays.asList(new Date(0), new Date(1000)), "Article");
        String result = prepareBuilder(ss).build(1969, 12, 365, "Article");

        assertEqualsFromFile(SitemapBuilderTest.class, "sitemap.xml", result);
    }

    private SitemapBuilder prepareBuilder(StaticSitemapSource... sources) {
        if (sources == null) {
            return new SitemapBuilder("http://localhost:8888",
                    Arrays.asList(new StaticSitemapSource("Article")));
        } else {
            return new SitemapBuilder("http://localhost:8888", Arrays.asList(sources));
        }
    }

}
