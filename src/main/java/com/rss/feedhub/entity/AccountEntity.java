package com.rss.feedhub.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 12:47
 * @Description:
 */
@Entity
@Table(name = "account", schema = "FeedHub", catalog = "")
public class AccountEntity {
    private int id;
    private String userName;
    private String passWord;
    private String nickName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "pass_word", nullable = false, length = 255)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Basic
    @Column(name = "nick_name", nullable = false, length = 255)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(passWord, that.passWord) &&
                Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, passWord, nickName);
    }
}
