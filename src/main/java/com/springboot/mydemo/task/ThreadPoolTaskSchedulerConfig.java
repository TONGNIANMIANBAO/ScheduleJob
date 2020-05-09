package com.springboot.mydemo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.TaskUtils;

import com.springboot.mydemo.service.ScheduleService;

@Configuration
public class ThreadPoolTaskSchedulerConfig {

	@Autowired
	ScheduleService service;

	@Bean(name = "mytask")
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler task = new ThreadPoolTaskScheduler();
		task.setPoolSize(3);
		task.setErrorHandler(TaskUtils.LOG_AND_PROPAGATE_ERROR_HANDLER);
		return task;
	}

}
