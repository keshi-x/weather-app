package com.weather.jobs;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureQuartzJob {

    @Value("${scheduler}")
    private String scheduler;


    @Bean
    public JobDetail jobDetails() {
        return JobBuilder.newJob(LoadDataJob.class).withIdentity("LoadDataJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger jobTrigger(JobDetail jobDetails) {

        return TriggerBuilder.newTrigger().forJob(jobDetails)
                .withIdentity("TriggerWeather")
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduler))
                .build();
    }

}