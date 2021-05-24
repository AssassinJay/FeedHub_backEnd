package com.rss.feedhub.repository;

import com.rss.feedhub.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 12:49
 * @Description:
 */
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {
    AccountEntity findByUserName(String account);

    @Transactional
    @Modifying
    @Query(value = "insert into account(user_name,pass_word,nick_name) values(?1,?2,?3)",nativeQuery = true)
    int addAccount(String username,String password,String nickname);

    @Query(value = "select feed_id from account_feed where user_id = ?1",nativeQuery = true)
    List<Integer> getSubIds(int user_id);
}
