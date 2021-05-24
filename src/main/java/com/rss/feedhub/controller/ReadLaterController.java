package com.rss.feedhub.controller;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.service.ReadLaterService;
import com.rss.feedhub.utils.TokenUtil;
import com.rss.feedhub.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/11 16:01
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("readLater")
public class ReadLaterController {
    @Autowired
    ReadLaterService readLaterService;
    @Autowired
    TokenUtil tokenUtil;
    @RequestMapping(value = "/add_item",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult addItem(HttpServletRequest request,@RequestParam String link){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        try{
            readLaterService.addReadLaterItem(user_name,link);
            return new JsonResult();
        }catch(Exception e){
            e.printStackTrace();
            return new JsonResult(-1,"");
        }
    }

    @RequestMapping(value = "/getLinks",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getLinks(HttpServletRequest request){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        try{
            return new JsonResult(readLaterService.getLaterLinks(user_name));
        }catch(Exception e){
            return new JsonResult(-1,"");
        }
    }

    @RequestMapping(value = "/deleteItem",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult deleteItem(HttpServletRequest request,@RequestParam String link){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        try{
            readLaterService.deleteLaterItem(user_name,link);
            return new JsonResult();
        }catch(Exception e){
            return new JsonResult(-1,"");
        }
    }

    @RequestMapping(value = "/getItems",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getItems(HttpServletRequest request){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        try{
            JSONObject res = new JSONObject();
            res.put("laterItems",readLaterService.getReadLaterEntities(user_name));
            return new JsonResult(res);
        }catch(Exception e){
            return new JsonResult(-1,"");
        }
    }
}
