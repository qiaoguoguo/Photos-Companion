package com.tencent.wxcloudrun.cron;

import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;

public class CaipiaoCron {


    /** 每天自动查询所有用彩票中奖情况，自动推送 */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void configureTasks8() {

    }


}
