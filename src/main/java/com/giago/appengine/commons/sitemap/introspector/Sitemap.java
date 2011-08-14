package com.giago.appengine.commons.sitemap.introspector;

import com.giago.appengine.commons.http.url.EncodedAndLimitedUrlGenerator;
import com.giago.appengine.commons.http.url.UrlGenerator;

@java.lang.annotation.Target(value={java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Sitemap {

    String doNotUse = "";
    
    String createDate = "createdDate";
    
    String title = "title";

    String relativeUrlPrefix = "/";
    
    String createdDate() default createDate;
    
    String title() default title;

    String relativeUrlPrefix() default relativeUrlPrefix;

    Class<? extends UrlGenerator> urlGeneratorClass() default EncodedAndLimitedUrlGenerator.class;
    
}
