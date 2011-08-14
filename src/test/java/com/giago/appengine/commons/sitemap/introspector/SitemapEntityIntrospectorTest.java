package com.giago.appengine.commons.sitemap.introspector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.giago.appengine.commons.http.url.DashUrlGenerator;
import com.giago.appengine.commons.http.url.EncodedAndLimitedUrlGenerator;
import com.giago.appengine.commons.sitemap.SitemapEntity;

public class SitemapEntityIntrospectorTest {
    
    private SitemapEntityIntrospector introspector;
    
    @Before
    public void before() {
        introspector = new SitemapEntityIntrospector();
    }
    
    @Test
    public void shouldGetProperEntityOutOfClass() {
        SitemapEntity entity = introspector.inspect(IntrospectorSubject.class.getName());
        assertNotNull(entity);
    }
    
    @Test
    public void shouldKeepTheClassInformation() {
        SitemapEntity entity = introspector.inspect(IntrospectorSubject.class.getName());
        assertNotNull(entity);
    }
    
    @Test
    public void shouldHaveTheCorrectKind() {
        SitemapEntity entity = introspector.inspect(IntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals("IntrospectorSubject", entity.getKind());
    }
    
    @Test
    public void shouldHaveTheCreatedDateInformation() {
        SitemapEntity entity = introspector.inspect(IntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals("createdDate", entity.getCreatedDateProperty());
    }
    
    @Test
    public void shouldHaveTheCreatedDateInformationReturningNull() {
        SitemapEntity entity = introspector.inspect(NullPropertyIntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals(null, entity.getCreatedDateProperty());
    }
    
    @Test
    public void shouldHaveTheCreatedDateInformationFromAnnotation() {
        SitemapEntity entity = introspector.inspect(AnnotatedIntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals("modifiedDate", entity.getCreatedDateProperty());
    }
    
    @Test
    public void shouldHaveTheTitleProperty() {
        SitemapEntity entity = introspector.inspect(IntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals("title", entity.getTitleProperty());
    }
    
    @Test
    public void shouldHaveTheTitlePropertyFromAnnotation() {
        SitemapEntity entity = introspector.inspect(AnnotatedIntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals("description", entity.getTitleProperty());
    }
    
    @Test
    public void shouldHaveRelativeUrlPrefix() {
        SitemapEntity entity = introspector.inspect(IntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals("/introspectorsubject/", entity.getRelativeUrlPrefix());
    }
    
    @Test
    public void shouldHaveRelativeUrlPrefixFromAnnotation() {
        SitemapEntity entity = introspector.inspect(AnnotatedIntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertEquals("/article1/", entity.getRelativeUrlPrefix());
    }
    
    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfClassNotFound() {
        introspector.inspect("class.that.do.not.exists");
    }
    
    @Test
    public void shouldBeAbleToSetDifferentGenerator() {
        SitemapEntity entity = introspector.inspect(AnnotatedIntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertNotNull(entity.getUrlGenerator());
        assertFalse(entity.getUrlGenerator() instanceof EncodedAndLimitedUrlGenerator);
        assertTrue(entity.getUrlGenerator() instanceof DashUrlGenerator);
    }
    
    @Test
    public void shouldGetNullDateIfUsingAnEntityWithoutCreatedDateField() {
        SitemapEntity entity = introspector.inspect(NoDatePropertyIntrospectorSubject.class.getName());
        assertNotNull(entity);
        assertNull("expecting null but is " + entity.getCreatedDateProperty(), entity.getCreatedDateProperty());
    }
    
    @Test
    public void shouldGetDateFromSuperclass() {
        SitemapEntity entity = introspector.inspect(SubClassToCheckDate.class.getName());
        assertNotNull(entity);
        assertEquals("createdDate", entity.getCreatedDateProperty());
        assertEquals("/subclasstocheckdate/", entity.getRelativeUrlPrefix());
    }
    
}
