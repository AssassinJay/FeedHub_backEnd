package com.rss.feedhub.repository;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.entity.ReadLaterEntity;
import com.rss.feedhub.vo.ReadLaterVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/11 16:02
 * @Description:
 */
public interface ReadLaterRepository extends JpaRepository<ReadLaterEntity,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into read_later(user_id,title,description,link,pub_date,author,feed_name) values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    int addReadLater(int user_id,String title,String description,String link,String pubdate,String author,String feed_name);

    @Query(value = "select link from read_later where user_id = ?1",nativeQuery = true)
    List<String> findLink(int user_id);

    @Transactional
    @Modifying
    @Query(value = "delete from read_later where link = ?1 and user_id = ?2",nativeQuery = true)
    int deleteByLinkAndUserId(String link,int userid);

    @Query(value = "select * from read_later where user_id = ?1 order by create_time desc",nativeQuery = true)
    List<JSONObject> getReadLaterEntities(int userid);
}