package com.rss.feedhub.repository;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.entity.FeedEntity;
import com.rss.feedhub.entity.SourceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/5 16:18
 * @Description:
 */
public interface FeedRepository extends JpaRepository<FeedEntity,Integer> {
//    @Transactional
//    @Modifying
//    @Query(value = "insert into feeds(source_id,feed_url,feed_name) values(?1,?2,?3)",nativeQuery = true)
//    int addFeed(int source_id,String feed_url,String feed_name);

//    FeedEntity findByFeedName(String feed_name);

    @Query(value = "select feed_url from account_feed a,feeds f where a.feed_id = f.id and a.user_id = ?1",nativeQuery = true)
    List<String> findFeedUrl(int user_id);

    @Query(value = "select feed_url, feed_name, souce_name ,isRSSHub " +
            "from account_feed a,feeds f,source s " +
            "where a.feed_id = f.id " +
            "and f.source_id = s.id " +
            "and a.user_id = ?1 " +
            "and f.id not in " +
            "( select feed_id from sub_toggler where user_id = ?1 )",nativeQuery = true)
    List<JSONObject> findRSSList(int user_id);

    @Query(value = "select update_time from feeds where feed_url = ?1",nativeQuery = true)
    String getUpdateTimeByFeedUrl(String FeedUrl);

    FeedEntity getFeedEntityByFeedUrl(String FeedUrl);

    @Query(value = "select id from feeds where feed_url = ?1",nativeQuery = true)
    int getIdByFeedUrl(String Url);

    @Transactional
    @Modifying
    @Query(value = "update feeds set update_time = ?1,item_num = ?2 where id = ?3",nativeQuery = true)
    int updateById(String updateTime,int item_num,int id);

    @Query(value = "select item_num from feeds where id = ?1",nativeQuery = true)
    int getItemNumById(int id);

    @Query(value = "select * from feeds where id in ?1",nativeQuery = true)
    List<FeedEntity> getAllSubs(List<Integer> sub_ids);

    @Query(value = "select feed_id from sub_toggler where user_id = ?1",nativeQuery = true)
    List<Integer> getAllignore(int user_id);

    @Transactional
    @Modifying
    @Query(value = "insert into sub_toggler(feed_id, user_id) values (?1, ?2) ",nativeQuery = true)
    int addIgnore(int feed_id,int user_id);

    @Transactional
    @Modifying
    @Query(value = "delete from sub_toggler where user_id=?1 and feed_id = ?2",nativeQuery = true)
    int deleteIgnore(int user_id,int feed_id);

    @Transactional
    @Modifying
    @Query(value = "delete from account_feed where user_id=?1 and feed_id = ?2",nativeQuery = true)
    int deleteSub(int user_id,int feed_id);

    @Transactional
    @Modifying
    @Query(value = "insert into source(souce_name,type_id) values (?1,?2) ",nativeQuery = true)
    int addSource(String souce_name,int type_id);

    @Query(value = "select id from source where souce_name = ?1",nativeQuery = true)
    Integer findSource(String source_name);

    @Transactional
    @Modifying
    @Query(value = "insert into feeds(source_id, feed_url, feed_name, isRSSHub, update_time) values (?1,?2,?3,?4,?5) ",nativeQuery = true)
    int addFeed(int source_id,String feed_url,String feed_name, int isRSSHub,String update_time);

    @Transactional
    @Modifying
    @Query(value = "insert into account_feed(feed_id, user_id) values (?1,?2) ",nativeQuery = true)
    int addAccountFeed(int feed_id,int user_id);

    @Query(value = "select feeds.id,feeds.feed_name,feeds.feed_url,source.souce_name from feeds,source_type,source " +
            "where source_type.id = ?1 " +
            "and source.type_id = source_type.id " +
            "and feeds.source_id = source.id",nativeQuery = true)
    List<JSONObject> findFeedsByTypeId(int typeid);

    @Query(value = "select feed_id from account_feed where user_id = ?1",nativeQuery = true)
    List<Integer> findUserSubscribe(int user_id);

    @Transactional
    @Modifying
    @Query(value = "insert into account_items(user_id, feed_url) values (?1,?2) ",nativeQuery = true)
    int addAccountItems(int user_id,String feed_url);

    @Transactional
    @Modifying
    @Query(value = "delete from account_items where user_id = ?1 and feed_url = ?2 ",nativeQuery = true)
    int deleteAccountItems(int user_id,String feed_url);

    @Query(value = "select feed_url from account_items where user_id = ?1",nativeQuery = true)
    List<String> findDoneUrl(int user_id);

    @Query(value = "select feed_name from feeds where id = ?1",nativeQuery = true)
    String getFeedNameById(int id);

    @Transactional
    @Modifying
    @Query(value = "update feeds set update_time = ?1",nativeQuery = true)
    int updateFeedUpdateTime(String update_time);
}
