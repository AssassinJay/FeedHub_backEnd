package com.rss.feedhub.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 12:47
 * @Description:
 */
@Entity
@Table(name = "item_temp", schema = "FeedHub", catalog = "")
public class ItemTempEntity {
    private int id;
    private int feedTempId;
    private String title;
    private String description;
    private Timestamp pubDate;
    private String originalLink;
    private String author;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "feed_temp_id", nullable = false)
    public int getFeedTempId() {
        return feedTempId;
    }

    public void setFeedTempId(int feedTempId) {
        this.feedTempId = feedTempId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "pubDate", nullable = false)
    public Timestamp getPubDate() {
        return pubDate;
    }

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

    @Basic
    @Column(name = "original_link", nullable = false, length = 255)
    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    @Basic
    @Column(name = "author", nullable = false, length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTempEntity that = (ItemTempEntity) o;
        return id == that.id &&
                feedTempId == that.feedTempId &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(pubDate, that.pubDate) &&
                Objects.equals(originalLink, that.originalLink) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedTempId, title, description, pubDate, originalLink, author);
    }
}
