
package com.giago.appengine.commons.sitemap.introspector;

import java.util.Date;


import com.giago.appengine.commons.http.url.DashUrlGenerator;

@Sitemap(createdDate = "modifiedDate", title = "description", relativeUrlPrefix = "/article1/", urlGeneratorClass = DashUrlGenerator.class)
public class AnnotatedIntrospectorSubject {

    private Date modifiedDate;

    private String description;

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
