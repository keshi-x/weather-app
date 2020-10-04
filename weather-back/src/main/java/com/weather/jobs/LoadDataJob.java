package com.weather.jobs;

import com.weather.controllers.WeatherController;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class LoadDataJob implements Job {

    @Autowired
    private WeatherController weatherController;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException  {
        System.out.println("Job ** "+context.getJobDetail().getKey().getName()+" ** fired " + context.getFireTime());
        weatherController.loadWeatherData();
        System.out.println("Next job scheduled:" + context.getNextFireTime());
    }
}
