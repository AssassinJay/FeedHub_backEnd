package com.rss.feedhub.service;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.entity.FeedEntity;
import com.rss.feedhub.entity.ItemsEntity;
import com.rss.feedhub.entity.ReadLaterEntity;
import com.rss.feedhub.repository.AccountRepository;
import com.rss.feedhub.repository.FeedItemRepository;
import com.rss.feedhub.repository.FeedRepository;
import com.rss.feedhub.repository.ReadLaterRepository;
import com.rss.feedhub.vo.ReadLaterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/11 16:05
 * @Description:
 */
@Service
public class ReadLaterService {
    @Autowired
    ReadLaterRepository readLaterRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FeedItemRepository feedItemRepository;
    @Autowired
    FeedRepository feedRepository;
    public int addReadLaterItem(String username,String link){
        int user_id = accountRepository.findByUserName(username).getId();
        List<ItemsEntity> res = feedItemRepository.getItemsEntityByOriginalLink(link);
        ItemsEntity temp = res.get(0);
        String feed_name = feedRepository.getFeedNameById(temp.getFeedId());

        return readLaterRepository.addReadLater(user_id,temp.getTitle(),temp.getDescription(),temp.getOriginalLink(),temp.getPubDate().toString(),temp.getAuthor(),feed_name);
    }

    public List<String> getLaterLinks(String user_name){
        int user_id = accountRepository.findByUserName(user_name).getId();
        return readLaterRepository.findLink(user_id);
    }

    public int deleteLaterItem(String user_name,String link){
        int user_id = accountRepository.findByUserName(user_name).getId();
        return readLaterRepository.deleteByLinkAndUserId(link,user_id);
    }

    public List<JSONObject> getReadLaterEntities(String user_name){
        int user_id = accountRepository.findByUserName(user_name).getId();
        try{
            return readLaterRepository.getReadLaterEntities(user_id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
