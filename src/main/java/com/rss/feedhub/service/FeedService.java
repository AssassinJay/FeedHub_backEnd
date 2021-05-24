package com.rss.feedhub.service;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.entity.FeedEntity;
import com.rss.feedhub.entity.ItemsEntity;
import com.rss.feedhub.entity.SourceTypeEntity;
import com.rss.feedhub.repository.AccountRepository;
import com.rss.feedhub.repository.FeedItemRepository;
import com.rss.feedhub.repository.FeedRepository;
import com.rss.feedhub.repository.SourceTypeRepository;
import com.rss.feedhub.utils.DateUtil;
import com.rss.feedhub.utils.RSSparser;
import com.rss.feedhub.vo.FeedItem;
import com.rss.feedhub.vo.FeedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/5 16:18
 * @Description:
 */
@Service
public class FeedService {
    @Autowired
    FeedRepository feedRepository;
    @Autowired
    FeedItemRepository feedItemRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SourceTypeRepository sourceTypeRepository;
//    public void addFeedTemp(FeedVo feed,String url,int source_id,String user_name) throws ParseException {
//        try{
//            System.out.println(user_name);
//            String feed_name = feed.getTitle();
//            String pub_date = feed.getPubDate();
//            pub_date = DateUtil.dealDateFormatReverse(pub_date);
//            int user_id = accountRepository.findByUserName(user_name).getId();
//            int feed_id = 0;
//            FeedEntity res = feedRepository.findByFeedName(feed_name);
//            if(res!=null){
//                feed_id = res.getId();
//            }else{
//                feedRepository.addFeed(source_id,url,feed_name);
//                feed_id = feedRepository.findByFeedName(feed_name).getId();
//            }
//            List<FeedItem> entries = feed.getItems();
//            for(FeedItem entry : entries){
//                entry.getTitle();
//                feedItemRepository.addFeed(feed_id,entry.getTitle(),entry.getDescription().toString(),pub_date,entry.getOrigin_link(),entry.getAuthor());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    public List<FeedVo> getFeedByUsername(String username) throws Exception {
        int uid = accountRepository.findByUserName(username).getId();
        List<String> urlList = feedRepository.findFeedUrl(uid);

        List<FeedVo> result = new LinkedList<>();
        for(int i=0;i<urlList.size();i++){
            String url = urlList.get(i);
            FeedVo obj = RSSparser.getFeed(url);
            List<FeedItem> items = obj.getItems();
            int pagesize = items.size()>6?6:items.size();
            List<FeedItem> new_items = items.subList(0,pagesize);
            obj.setItems(new_items);
            result.add(obj);
        }
        return result;
    }

    public List<JSONObject> getRSSList(String username){
        int uid = accountRepository.findByUserName(username).getId();
        return feedRepository.findRSSList(uid);
    }

    public JSONObject getFeedItems(String url,int isRSSHub) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(feedRepository.getUpdateTimeByFeedUrl(url));
        long update_time = date.getTime()/1000;
        long current_time = System.currentTimeMillis()/1000;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_time = sdf.format(d);
        JSONObject res = new JSONObject();
        FeedEntity feedEntity = feedRepository.getFeedEntityByFeedUrl(url);
        int feed_id = feedEntity.getId();
        if(Math.abs(current_time-update_time)>600){
            try{
                FeedVo feed = isRSSHub==1?RSSparser.getFeed(url):RSSparser.getFeedFromSource(url);
                int cache_length = 1000;
                int current_length = feedItemRepository.getCurrentLength();
                if(current_length>=cache_length){
                    feedItemRepository.DeleteTop(feed.getItems().size());
                }
                feedRepository.updateById(format_time,feed.getItems().size(),feed_id);
                for(FeedItem item : feed.getItems()){
                    feedItemRepository.addFeed(feed_id,item.getTitle(),item.getDescription(),item.getPubDate(),item.getOriginalLink(),item.getAuthor());
                }
                res.put("feeds",feed);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                int item_num = feedEntity.getItemNum();
                JSONObject temp = new JSONObject();
                List<ItemsEntity> list = feedItemRepository.getItemList(feed_id,item_num);
                Collections.reverse(list);
                temp.put("items",list);
                temp.put("title",feedEntity.getFeedName());
                res.put("feeds",temp);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }

    public List<FeedEntity> getAllSubs(String user_name){
        int user_id = accountRepository.findByUserName(user_name).getId();
        List<Integer> sub_ids = accountRepository.getSubIds(user_id);
        List<FeedEntity> sub_list = feedRepository.getAllSubs(sub_ids);
        List<Integer> ignore_list = feedRepository.getAllignore(user_id);
        for(int i=0;i<ignore_list.size();i++){
            int feed_id = ignore_list.get(i);
            for(int j=0;j<sub_list.size();j++){
                if(sub_list.get(j).getId()==feed_id){
                    sub_list.get(j).setSwitcher(0);
                }
            }
        }
        return sub_list;
    }

    public void changeStatus(String user_name,int feed_id,boolean toStatus){
        int user_id = accountRepository.findByUserName(user_name).getId();
        try{
            if(toStatus==false){
                feedRepository.addIgnore(feed_id,user_id);
            }else{
                feedRepository.deleteIgnore(user_id,feed_id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteSub(String user_name,int feed_id){
        try{
            int user_id = accountRepository.findByUserName(user_name).getId();
            feedRepository.deleteSub(user_id,feed_id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<SourceTypeEntity> getSourceType(){
        try{
            return sourceTypeRepository.getSourceType();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void addFeed(String user_name,
                        String feed_url,
                        String feed_name,
                        int type_id,
                        String channel_name,
                        String isFromRSSHub){
        try{
            int user_id = accountRepository.findByUserName(user_name).getId();
            int source_id = -1;
            source_id = feedRepository.findSource(channel_name);
            if(source_id==-1){
                feedRepository.addSource(channel_name,type_id);
                source_id = feedRepository.findSource(channel_name);
            }
            int isRSSHub = isFromRSSHub.equals("true")?1:0;
            feedRepository.addFeed(source_id,feed_url,feed_name,isRSSHub);
            int feed_id = feedRepository.getIdByFeedUrl(feed_url);
            feedRepository.addAccountFeed(feed_id,user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<JSONObject> getFeedListByTypeid(int typeid){
        return feedRepository.findFeedsByTypeId(typeid);
    }

    public List<Integer> getSubidList(String user_name){
        int user_id = accountRepository.findByUserName(user_name).getId();
        return feedRepository.findUserSubscribe(user_id);
    }

    public void addSub(String user_name, int feed_id){
        int user_id = accountRepository.findByUserName(user_name).getId();
        feedRepository.addAccountFeed(feed_id,user_id);
    }

    public void addAccountItem(String user_name, String feed_url){
        int user_id = accountRepository.findByUserName(user_name).getId();
        feedRepository.addAccountItems(user_id,feed_url);
    }

    public void deleteAccountItem(String user_name, String feed_url){
        int user_id = accountRepository.findByUserName(user_name).getId();
        feedRepository.deleteAccountItems(user_id,feed_url);
    }

    public List<String> getDoneUrl(String user_name){
        int user_id = accountRepository.findByUserName(user_name).getId();
        return feedRepository.findDoneUrl(user_id);
    }
}
