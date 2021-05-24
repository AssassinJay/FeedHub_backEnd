package com.rss.feedhub.scheduler;

import com.rss.feedhub.repository.FeedItemRepository;
import com.rss.feedhub.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZhangKaijie
 * @Date: 2021/5/24 21:47
 * @Description:
 */
@Component
public class SchedulingTask {
    @Autowired
    FeedItemRepository feedItemRepository;
    @Autowired
    FeedRepository feedRepository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(cron="0 0 0 * * ?")
    public void clearCacheTable(){
        long current_time = System.currentTimeMillis();
        long update_time = current_time-601000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_time = sdf.format(update_time);
        feedRepository.updateFeedUpdateTime(format_time);
        feedItemRepository.truncateCacheTable();
    }

//    @Scheduled(fixedRate = 6000)
//    public void TEST(){
//        System.out.println("现在时间：" + dateFormat.format(new Date()));
//    }
}
