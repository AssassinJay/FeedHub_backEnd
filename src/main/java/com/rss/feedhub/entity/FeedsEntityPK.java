package com.rss.feedhub.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/5/9 15:11
 * @Description:
 */
public class FeedsEntityPK implements Serializable {
    private int id;
    private String feedUrl;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "feed_url", nullable = false, length = 255)
    @Id
    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedsEntityPK that = (FeedsEntityPK) o;
        return id == that.id &&
                Objects.equals(feedUrl, that.feedUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedUrl);
    }
}
