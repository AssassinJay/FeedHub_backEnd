package com.rss.feedhub.service;

import com.rss.feedhub.entity.AccountEntity;
import com.rss.feedhub.repository.AccountRepository;
import com.rss.feedhub.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 12:48
 * @Description:
 */
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    public AccountEntity findByAccount(String account_str){
        return accountRepository.findByUserName(account_str);
    }

    public int addAccount(AccountEntity account){
        if(findByAccount(account.getUserName())!=null){
            return -1;
        }
        account.setPassWord(Md5Util.inputPassToDbPass(account.getPassWord(),account.getUserName()));
        return accountRepository.addAccount(account.getUserName(),account.getPassWord(),account.getNickName());
    }

    public int handleLogin(String username,String password){
        AccountEntity account = findByAccount(username);
        if(account==null) return 0;
        if(account.getPassWord().equals(Md5Util.inputPassToDbPass(password,username))){
            return 1;
        }
        return -1;
    }
}
