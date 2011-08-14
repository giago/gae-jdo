package com.giago.appengine.commons.sitemap.introspector;


import com.giago.appengine.commons.http.url.DashWithoutIdUrlGenerator;

@Sitemap(createdDate = Sitemap.doNotUse, title = "tag", relativeUrlPrefix="/tag/", urlGeneratorClass = DashWithoutIdUrlGenerator.class)
public class NullPropertyIntrospectorSubject {

    private String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
    
}
