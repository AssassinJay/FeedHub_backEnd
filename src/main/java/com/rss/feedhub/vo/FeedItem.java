package com.rss.feedhub.vo;

import com.sun.syndication.feed.synd.SyndContent;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/7 16:13
 * @Description:
 */
public class FeedItem {
    private String title,author,originalLink;
    private String description;
    private String pubDate;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public FeedItem() {
    }

    public FeedItem(String title, String author, String origin_link, String description) {
        this.title = title;
        this.author = author;
        this.originalLink = origin_link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOrigin_link(String origin_link) {
        this.originalLink = origin_link;
    }

}
