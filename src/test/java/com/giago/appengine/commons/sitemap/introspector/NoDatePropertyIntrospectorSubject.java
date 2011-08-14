package com.giago.appengine.commons.sitemap.introspector;


import com.giago.appengine.commons.http.url.DashWithoutIdUrlGenerator;

@Sitemap(title = "tag", relativeUrlPrefix="/tag/", urlGeneratorClass = DashWithoutIdUrlGenerator.class)
public class NoDatePropertyIntrospectorSubject {

    private String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
    
}
