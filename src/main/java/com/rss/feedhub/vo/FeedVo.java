package com.rss.feedhub.vo;

import java.util.Date;
import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/7 16:24
 * @Description:
 */
public class FeedVo {
    private List<FeedItem> items;
    private String title;
    private String pubDate;

    public List<FeedItem> getItems() {
        return items;
    }

    public void setItems(List<FeedItem> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
