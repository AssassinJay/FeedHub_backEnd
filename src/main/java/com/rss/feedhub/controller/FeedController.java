package com.rss.feedhub.controller;

import com.alibaba.fastjson.JSONObject;
import com.rss.feedhub.annotation.UserLoginToken;
import com.rss.feedhub.service.FeedService;
import com.rss.feedhub.utils.RSSparser;
import com.rss.feedhub.utils.TokenUtil;
import com.rss.feedhub.vo.FeedVo;
import com.rss.feedhub.vo.JsonResult;
import com.sun.syndication.feed.synd.SyndEntry;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/4/5 16:06
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("feed")
public class FeedController {
    @Autowired
    FeedService feedService;
    @Autowired
    TokenUtil tokenUtil;
//    @RequestMapping(value = "/insert",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//    public JsonResult handle_login(@RequestParam String url, @RequestParam int source_id, HttpServletRequest request) throws Exception {
//        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
//        FeedVo feed = RSSparser.getFeed(url);
//        feedService.addFeedTemp(feed,url,source_id,user_name);
//        return new JsonResult(feed);
//    }
    @UserLoginToken
    @RequestMapping(value = "/getFeed",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult get_feed(HttpServletRequest request) throws Exception {
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        JSONObject res = new JSONObject();
        res.put("feeds",feedService.getFeedByUsername(user_name));
        return new JsonResult(res);
    }

    @UserLoginToken
    @RequestMapping(value = "/getRSSList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getRSSList(HttpServletRequest request) throws Exception {
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        JSONObject res = new JSONObject();
        res.put("RSSList",feedService.getRSSList(user_name));
        return new JsonResult(res);
    }
    @UserLoginToken
    @RequestMapping(value = "/getFeedByUrl",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getFeedByUrl(HttpServletRequest request,
                                   @RequestParam String feedUrl,
                                   @RequestParam int isRSSHub) throws Exception {
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        JSONObject res = new JSONObject();
        res.put("FeedItems",feedService.getFeedItems(feedUrl,isRSSHub));
        return new JsonResult(res);
    }

    @UserLoginToken
    @RequestMapping(value = "/getSubs",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getSubs(HttpServletRequest request){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        JSONObject res = new JSONObject();
        res.put("Subscriptions",feedService.getAllSubs(user_name));
        return new JsonResult(res);
    }

    @UserLoginToken
    @RequestMapping(value = "/subToggler",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult subToggler(HttpServletRequest request,@RequestParam int feed_id,@RequestParam boolean toStatus){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        feedService.changeStatus(user_name,feed_id,toStatus);
        return new JsonResult();
    }

    @UserLoginToken
    @RequestMapping(value = "/deleteSub",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult deleteSub(HttpServletRequest request,@RequestParam int feed_id){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        feedService.deleteSub(user_name,feed_id);
        return new JsonResult();
    }

    @UserLoginToken
    @RequestMapping(value = "/getChannelType",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getChannelType(HttpServletRequest request){
        JSONObject res = new JSONObject();
        res.put("SourceTypeList",feedService.getSourceType());
        return new JsonResult(res);
    }

    @UserLoginToken
    @RequestMapping(value = "/addFeed",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult addFeed(HttpServletRequest request,
                              @RequestParam String feed_url,
                              @RequestParam String feed_name,
                              @RequestParam int type_id,
                              @RequestParam String channel_name,
                              @RequestParam String isFromRSSHub){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        feedService.addFeed(user_name,feed_url,feed_name,type_id,channel_name,isFromRSSHub);
        return new JsonResult();
    }

    @UserLoginToken
    @RequestMapping(value = "/getFeedList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getFeedList(HttpServletRequest request,@RequestParam int type_id){
        JSONObject res = new JSONObject();
        res.put("feedList",feedService.getFeedListByTypeid(type_id));
        return new JsonResult(res);
    }

    @UserLoginToken
    @RequestMapping(value = "/getSubidList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getSubidList(HttpServletRequest request){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        JSONObject res = new JSONObject();
        res.put("SubidList",feedService.getSubidList(user_name));
        return new JsonResult(res);
    }

    @UserLoginToken
    @RequestMapping(value = "/addSubscribe",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult addSubscribe(HttpServletRequest request,@RequestParam int feed_id){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        feedService.addSub(user_name,feed_id);
        return new JsonResult();
    }

    @UserLoginToken
    @RequestMapping(value = "/addAccountItems",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult addAccountItems(HttpServletRequest request,
                                      @RequestParam String feed_url){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        feedService.addAccountItem(user_name,feed_url);
        return new JsonResult();
    }

    @UserLoginToken
    @RequestMapping(value = "/deleteAccountItems",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult deleteAccountItems(HttpServletRequest request,
                                      @RequestParam String feed_url){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        feedService.deleteAccountItem(user_name,feed_url);
        return new JsonResult();
    }

    @UserLoginToken
    @RequestMapping(value = "/getDoneUrl",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JsonResult getDoneUrl(HttpServletRequest request){
        String user_name = (String)tokenUtil.parseJwtToken(request.getHeader("access_token")).get("username");
        JSONObject res = new JSONObject();
        res.put("doneUrlList",feedService.getDoneUrl(user_name));
        return new JsonResult(res);
    }
}

