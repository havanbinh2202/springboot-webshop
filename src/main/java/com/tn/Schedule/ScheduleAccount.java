package com.tn.Schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class ScheduleAccount {

    // bắt đầu từ giây 02 cách 5 giây chạy 1 lần
//    @Scheduled(cron = "2/5 * * * * * ")

    // 7s 1 lần
    @Scheduled(fixedRate = 7000)
    public void printData(){
        System.out.println(new Date()+"run");
    }
}
