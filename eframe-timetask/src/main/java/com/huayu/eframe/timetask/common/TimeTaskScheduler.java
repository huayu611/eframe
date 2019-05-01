package com.huayu.eframe.timetask.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by Leo on 2019/4/15.
 */
@Configuration
public class TimeTaskScheduler
{
    @Bean
    public TaskScheduler scheduledExecutorService()
    {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        return scheduler;
    }
}
