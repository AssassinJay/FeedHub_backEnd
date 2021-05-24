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
@Table(name = "feed_temp", schema = "FeedHub", catalog = "")
public class FeedTempEntity {
    private int id;
    private String feedUrl;
    private String title;
    private String description;
    private String language;
    private Timestamp lastBuildDate;
    private int userId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "feed_url", nullable = false, length = 255)
    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
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
    @Column(name = "language", nullable = false, length = 255)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "lastBuildDate", nullable = false)
    public Timestamp getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(Timestamp lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedTempEntity that = (FeedTempEntity) o;
        return id == that.id &&
                Objects.equals(feedUrl, that.feedUrl) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(language, that.language) &&
                Objects.equals(lastBuildDate, that.lastBuildDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedUrl, title, description, language, lastBuildDate);
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
