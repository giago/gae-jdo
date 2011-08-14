
package com.giago.appengine.commons.sitemap;

import com.giago.appengine.commons.http.url.EncodedAndLimitedUrlGenerator;
import com.giago.appengine.commons.http.url.UrlGenerator;

public class SitemapEntity {

    private static final String SEPARATOR = "/";
    
    private String kind;

    private String relativeUrlPrefix;

    private String createdDateProperty;

    private String titleProperty;

    private UrlGenerator urlGenerator = new EncodedAndLimitedUrlGenerator();

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setRelativeUrlPrefix(String relativeUrlPrefix) {
        this.relativeUrlPrefix = relativeUrlPrefix;
    }

    public String getRelativeUrlPrefix() {
        if(relativeUrlPrefix == null) {
            relativeUrlPrefix = SEPARATOR + kind.toLowerCase() + SEPARATOR;
        }
        return relativeUrlPrefix;
    }

    public void setCreatedDateProperty(String createdDateProperty) {
        this.createdDateProperty = createdDateProperty;
    }

    public String getCreatedDateProperty() {
        return createdDateProperty;
    }

    public void setTitleProperty(String titleProperty) {
        this.titleProperty = titleProperty;
    }

    public String getTitleProperty() {
        return titleProperty;
    }

    public void setUrlGenerator(UrlGenerator urlGenerator) {
        this.urlGenerator = urlGenerator;
    }

    public UrlGenerator getUrlGenerator() {
        return urlGenerator;
    }

    public String buildRelativeUrl(String title, Long id) {
        return getRelativeUrlPrefix() + urlGenerator.generateRelativeUrl(title, id);
    }

}
