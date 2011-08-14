package com.giago.appengine.commons.sitemap.introspector;

import com.giago.appengine.commons.sitemap.SitemapEntity;

public class SitemapEntityIntrospector {
    
    public SitemapEntity inspect(String entityClass) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(entityClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Entity with class " + entityClass + " is not in the class path");
        }
        SitemapEntity entity = new SitemapEntity();
        entity.setKind(clazz.getSimpleName());
        entity.setCreatedDateProperty(Sitemap.createDate);
        entity.setTitleProperty(Sitemap.title);
        entity.setRelativeUrlPrefix(Sitemap.relativeUrlPrefix + clazz.getSimpleName().toLowerCase() + Sitemap.relativeUrlPrefix);
        
        Sitemap sitemap = clazz.getAnnotation(Sitemap.class);
        if(sitemap != null) {       
            if(Sitemap.doNotUse.equals(sitemap.createdDate())) {
                entity.setCreatedDateProperty(null);
            } else {
                entity.setCreatedDateProperty(sitemap.createdDate());
            }
            entity.setTitleProperty(sitemap.title());            
            entity.setRelativeUrlPrefix(sitemap.relativeUrlPrefix());
            try {
                entity.setUrlGenerator(sitemap.urlGeneratorClass().newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException("Instantiation Exception " + e.getMessage());
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Illegal Access Exception " + e.getMessage());
            }
        }        
        
        if(!hasField(entity.getCreatedDateProperty(), clazz)) {
            entity.setCreatedDateProperty(null);
        }
        
        return entity;
    }

    private boolean hasField(String name, Class<?> clazz) {
        try {
            clazz.getDeclaredField(name);
            return true;
        } catch (Exception e1) {
            Class<?> superClass = clazz.getSuperclass();
            if(superClass != null) {
                return hasField(name, superClass);
            } else {
                return false;
            }
        }
    }
}
