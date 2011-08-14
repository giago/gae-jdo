
package com.giago.appengine.commons.sitemap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.giago.appengine.commons.sitemap.source.SitemapSource;
import com.giago.appengine.commons.string.StringUtils;

public class SitemapBuilder {

    private static final String INDEX_START = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" "
            + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";

    private static final String INDEX_END = "</sitemapindex>";

    private static final String INDEX_SITEMAP_START = "<sitemap><loc>";

    private static final String INDEX_SITEMAP_END = "</loc></sitemap>";

    private static final String SITEMAP_RELATIVE_URL = "/sitemap.xml";

    private static final String YEAR_PARAMETER = "year=";

    private static final String MONTH_PARAMETER = "&amp;month=";

    private static final String DAY_PARAMETER = "&amp;day=";

    private static final String ENTITY_PARAMETER_WITH_AND = "&amp;entity=";
    
    private static final String ENTITY_PARAMETER = "entity=";

    private static final String QUESTION_MARK = "?";

    private static final String SITEMAP_START = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";

    private static final String SITEMAP_END = "</urlset>";

    private static final String CONSTANT_PART = "<changefreq>monthly</changefreq><priority>0.7</priority></url>";

    private static final String SITEMAP_ENTRY_START = "<url><loc>";

    private static final String SITEMAP_URL_END = "</loc>";

    private static final String SITEMAP_MODIFIED_DATE_START = "<lastmod>";

    private static final String SITEMAP_MODIFIED_DATE_END = "</lastmod>";

    private StringBuilder builder;

    private List<? extends SitemapSource> sitemapSources;

    private String host;

    private String sitemapUrl;

    public SitemapBuilder(String host, List<? extends SitemapSource> sitemapSources) {
        builder = new StringBuilder();
        this.host = host;
        this.sitemapUrl = host + SITEMAP_RELATIVE_URL;
        if (sitemapSources != null) {
            this.sitemapSources = sitemapSources;
        } else {
            throw new RuntimeException(
                    "It is necessary to specify the source of the sitemap generation");
        }
    }

    public String build() {
        return build(new ArrayList<String>());
    }
    
    /**
     * @param sitemaps
     * @return
     */
    public String build(List<String> sitemaps) {
        builder.append(INDEX_START);
        if (sitemaps != null) {
            for (String sitemap : sitemaps) {
                addSitemapIndex(sitemap);
            }
        }
        for (SitemapSource sitemapSource : sitemapSources) {
            if(!sitemapSource.isCreatedDateDefined()) { 
                addSitemapIndexOnlyKind(sitemapSource.getKind());
            } else {
                for (int year : sitemapSource.years()) {
                    addSitemapIndex(year, sitemapSource.getKind());
                }
            }
        }
        builder.append(INDEX_END);
        return builder.toString();
    }
    
    /**
     * Build for a specific entity that doesn't support date
     * @param entity
     * @return
     */
    public String build(String entity) {
        builder.append(SITEMAP_START);
        SitemapSource sitemapSource = getSitemapSource(entity);
        sitemapSource.prepare();
        while (sitemapSource.next()) {
            addSitemapEntry(host + sitemapSource.getRelativeUrl());
        }
        builder.append(SITEMAP_END);
        return builder.toString();
    }

    /**
     * @param year
     * @param entity
     * @return
     */
    public String build(int year, String entity) {
        builder.append(INDEX_START);
        SitemapSource sitemapSource = getSitemapSource(entity);
        for (int month : sitemapSource.months(year)) {
            for (int day : sitemapSource.days(year, month)) {
                addSitemapIndex(year, month, day, entity);
            }
        }
        builder.append(INDEX_END);
        return builder.toString();
    }

    /**
     * @param year
     * @param month
     * @param day
     * @param entity
     * @return
     */
    public String build(int year, int month, int day, String entity) {
        builder.append(SITEMAP_START);
        SitemapSource sitemapSource = getSitemapSource(entity);
        sitemapSource.prepare(year, month, day);
        while (sitemapSource.next()) {
            addSitemapEntry(host + sitemapSource.getRelativeUrl(), sitemapSource.getCreatedDate());
        }
        builder.append(SITEMAP_END);
        return builder.toString();
    }

    private void addSitemapEntry(String relativeUrl, Date modifiedDate) {
        builder.append(SITEMAP_ENTRY_START).append(relativeUrl).append(SITEMAP_URL_END)
                .append(SITEMAP_MODIFIED_DATE_START).append(StringUtils.formatDate(modifiedDate))
                .append(SITEMAP_MODIFIED_DATE_END).append(CONSTANT_PART);
    }
    
    private void addSitemapEntry(String relativeUrl) {
        builder.append(SITEMAP_ENTRY_START).append(relativeUrl).append(SITEMAP_URL_END)
                .append(CONSTANT_PART);
    }

    private void addSitemapIndex(int year, String entity) {
        builder.append(INDEX_SITEMAP_START).append(sitemapUrl).append(QUESTION_MARK)
                .append(YEAR_PARAMETER).append(year).append(ENTITY_PARAMETER_WITH_AND).append(entity)
                .append(INDEX_SITEMAP_END);
    }
    
    private void addSitemapIndexOnlyKind(String entity) {
        builder.append(INDEX_SITEMAP_START).append(sitemapUrl).append(QUESTION_MARK)
                .append(ENTITY_PARAMETER).append(entity)
                .append(INDEX_SITEMAP_END);
    }

    private void addSitemapIndex(String relativeUrl) {
        builder.append(INDEX_SITEMAP_START).append(host).append(relativeUrl)
                .append(INDEX_SITEMAP_END);
    }

    private void addSitemapIndex(int year, int month, int day, String entity) {
        builder.append(INDEX_SITEMAP_START).append(sitemapUrl).append(QUESTION_MARK)
                .append(YEAR_PARAMETER).append(year).append(ENTITY_PARAMETER_WITH_AND).append(entity)
                .append(MONTH_PARAMETER).append(month).append(DAY_PARAMETER).append(day)
                .append(INDEX_SITEMAP_END);
    }

    private SitemapSource getSitemapSource(String entity) {
        for (SitemapSource sitemapSource : sitemapSources) {
            if (entity.equals(sitemapSource.getKind())) {
                return sitemapSource;
            }
        }
        throw new RuntimeException("Can't find any sitemap source linked to the entity : " + entity);
    }

}
