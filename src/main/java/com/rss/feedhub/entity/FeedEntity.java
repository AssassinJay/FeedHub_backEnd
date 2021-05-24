package com.rss.feedhub.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/5/9 15:14
 * @Description:
 */
@Entity
@Table(name = "feeds", schema = "FeedHub", catalog = "")
@IdClass(FeedEntityPK.class)
public class FeedEntity {
    private int id;
    private Integer sourceId;
    private String feedUrl;
    private Integer itemNum;
    private String feedName;
    private Timestamp updateTime;
    private Integer switcher;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "source_id", nullable = true)
    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    @Id
    @Column(name = "feed_url", nullable = false, length = 255)
    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    @Basic
    @Column(name = "item_num", nullable = true)
    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    @Basic
    @Column(name = "feed_name", nullable = false, length = 255)
    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "switcher", nullable = true)
    public Integer getSwitcher() {
        return switcher;
    }

    public void setSwitcher(Integer switcher) {
        this.switcher = switcher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedEntity that = (FeedEntity) o;
        return id == that.id &&
                Objects.equals(sourceId, that.sourceId) &&
                Objects.equals(feedUrl, that.feedUrl) &&
                Objects.equals(itemNum, that.itemNum) &&
                Objects.equals(feedName, that.feedName) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(switcher, that.switcher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceId, feedUrl, itemNum, feedName, updateTime, switcher);
    }
}
