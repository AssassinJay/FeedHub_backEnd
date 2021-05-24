package com.rss.feedhub.utils;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.vo.FeedItem;
import com.rss.feedhub.vo.FeedVo;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/1/7 14:40
 * @Description:
 */
@Component
public class RSSparser {
    private final String baseurl = "http://rss.jaydev.top:1200";
    @SuppressWarnings("unchecked")
    private FeedVo praseXml(URL url) throws Exception {

        SyndFeed feed = null;

        SyndFeedInput input = new SyndFeedInput();
        // 创建链接对象
        URLConnection conn = url.openConnection();

        String content_encoding = conn.getHeaderField("Content-Encoding");

        if (content_encoding != null && content_encoding.contains("gzip")) {

            System.out.println("conent encoding is gzip");

            GZIPInputStream gzin = new GZIPInputStream(conn.getInputStream());

            feed = input.build(new XmlReader(gzin));
        } else {
            //1.得到此链接的输入流
            //2.读取此输入流
            //3.得到此输入流的有效信息
            feed = input.build(new XmlReader(conn.getInputStream()));
        }
        // 得到XML的所有实体
        FeedVo res = new FeedVo();
        res.setTitle(feed.getTitle());
        res.setPubDate(DateUtil.dealDateFormatReverse(feed.getPublishedDate().toString()));
        List<SyndEntry> entries = feed.getEntries();
        List<FeedItem> new_entries = new LinkedList<>();
        for (SyndEntry entry:entries) {
            FeedItem obj = new FeedItem();
            obj.setTitle(entry.getTitle());
            obj.setDescription(entry.getDescription().getValue());
            obj.setOrigin_link(entry.getLink());
            obj.setAuthor(entry.getAuthor());
            obj.setPubDate(res.getPubDate());
            new_entries.add(obj);
        }
        res.setItems(new_entries);
        return res;
    }

    public static FeedVo getFeed(String url) throws Exception {
        RSSparser rssPrase = new RSSparser();

        String str = rssPrase.baseurl+url;

        return rssPrase.praseXml(new URL(str));
//        rssPrase.praseXml(new URL(str));
    }

    public static FeedVo getFeedFromSource(String url) throws Exception {
        RSSparser rssPrase = new RSSparser();

        return rssPrase.praseXml(new URL(url));
//        rssPrase.praseXml(new URL(str));
    }
}
