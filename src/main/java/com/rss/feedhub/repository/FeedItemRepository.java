package com.rss.feedhub.repository;

import com.rss.feedhub.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/5 16:39
 * @Description:
 */
public interface FeedItemRepository extends JpaRepository<ItemsEntity,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into items(feed_id,title,description,pub_date,original_link,author) values(?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    int addFeed(int feed_id,String title,String description,String pubDate,String original_link,String author);

    @Query(value = "select count(*) from items",nativeQuery = true)
    int getCurrentLength();

    @Transactional
    @Modifying
    @Query(value = "delete from items order by id asc limit ?1",nativeQuery = true)
    int DeleteTop(int gap);

    @Query(value = "SELECT * FROM items where feed_id = ?1 ORDER BY id desc limit ?2",nativeQuery = true)
    List<ItemsEntity> getItemList(int feed_id, int gap);

    @Query(value = "SELECT * FROM items where original_link = ?1",nativeQuery = true)
    List<ItemsEntity> getItemsEntityByOriginalLink(String link);

    @Transactional
    @Modifying
    @Query(value = "truncate table items",nativeQuery = true)
    int truncateCacheTable();

    @Query(value = "select DISTINCT feed_id from items ",nativeQuery = true)
    List<Integer> getCacheFeedId();
}
