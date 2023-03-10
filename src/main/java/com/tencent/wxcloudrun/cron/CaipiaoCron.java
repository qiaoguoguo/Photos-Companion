package com.tencent.wxcloudrun.cron;

import com.tencent.wxcloudrun.service.UserCaipiaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Configuration
@EnableScheduling
@Slf4j
public class CaipiaoCron {

    @Autowired
    private UserCaipiaoService userCaipiaoService;

    /** 每天自动查询所有用彩票中奖情况，自动推送 */
    @Scheduled(cron = "0 0 15 ? * 1,3,6")
    public void configureTasks1() {
        Date date = new Date();
        System.out.println(date+"大乐透开奖");
        userCaipiaoService.kaijiang("14");
    }


}
