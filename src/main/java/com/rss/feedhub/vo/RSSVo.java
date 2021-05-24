package com.rss.feedhub.vo;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/9 13:15
 * @Description:
 */
public class RSSVo {
    private String feedUrl,feedName,souceName;

    public RSSVo(String feedUrl, String feedName, String souceName) {
        this.feedUrl = feedUrl;
        this.feedName = feedName;
        this.souceName = souceName;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getSouceName() {
        return souceName;
    }

    public void setSouceName(String souceName) {
        this.souceName = souceName;
    }
}
