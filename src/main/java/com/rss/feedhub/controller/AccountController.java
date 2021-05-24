package com.rss.feedhub.controller;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.entity.AccountEntity;
import com.rss.feedhub.service.AccountService;
import com.rss.feedhub.utils.TokenUtil;
import com.rss.feedhub.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/4 13:09
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    TokenUtil tokenUtil;
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult handle_login(@RequestParam String username,
                                   @RequestParam String password) {
        int res = accountService.handleLogin(username,password);
        if(res==0){
            return new JsonResult(1002,"");
        }else if(res==-1){
            return new JsonResult(1003,"");
        }
        JSONObject token = new JSONObject();
        token.put("access_token",tokenUtil.getToken(username));
        return new JsonResult(token);
    }
    @RequestMapping(value = "/signup",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult handle_signup(@RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String nickname) {
        AccountEntity obj = new AccountEntity();
        obj.setUserName(username);
        obj.setPassWord(password);
        obj.setNickName(nickname);
        try{
            int res = accountService.addAccount(obj);
            if(res==-1){
                return new JsonResult(1001,"");
            }
            return new JsonResult();
        }catch(Exception e){
            e.printStackTrace();
            return new JsonResult(-1,"");
        }


    }
    @GetMapping("/getMessage")
    public String getMessage(){
    return "你已通过验证";
}
}
