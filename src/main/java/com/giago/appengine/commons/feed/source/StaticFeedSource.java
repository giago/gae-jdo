package com.giago.appengine.commons.feed.source;

import java.util.Date;

public class StaticFeedSource implements FeedSource {

    private static final String DEFAULT_FEED_TYPE = "atom_1.0";
    
    private String type;
    
    private String author;

    private String title;

    private String description;

    private String link;
    
    private Date modifiedDate;
    
    public static class Builder {

        private StaticFeedSource feedSource;

        private Builder() {
            feedSource = new StaticFeedSource();
        }

        public static Builder init(String title) {
            Builder builder = new Builder();
            builder.title(title);
            builder.type(DEFAULT_FEED_TYPE);
            return builder;
        }

        public Builder title(String title) {
            feedSource.setTitle(title);
            return this;
        }

        public Builder description(String description) {
            feedSource.setDescription(description);
            return this;
        }

        public Builder modifiedDate(Date modifiedDate) {
            feedSource.setModifiedDate(modifiedDate);
            return this;
        }

        public Builder link(String link) {
            feedSource.setLink(link);
            return this;
        }

        public Builder author(String author) {
            feedSource.setAuthor(author);
            return this;
        }

        public Builder type(String type) {
            feedSource.setType(type);
            return this;
        }

        public StaticFeedSource build() {
            return feedSource;
        }

    }
    
    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getType() {
        return type;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setType(String type) {
        this.type = type;
    }

}
