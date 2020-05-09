package com.springboot.mydemo.task;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import com.springboot.mydemo.service.ScheduleService;

@Configuration
public class DynamicTask implements SchedulingConfigurer {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final Integer CRON_ID = 1;

	@Autowired
	ScheduleService service;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(() -> {
			log.info("DynamicTask\t:{}", LocalTime.now());
		}, triggerContext -> {
			String cron = getCron(CRON_ID);
			if (StringUtils.isEmpty(cron)) {

			}
			return new CronTrigger(cron).nextExecutionTime(triggerContext);
		});
	}

	private String getCron(Integer id) {
		return service.getCron(id);
	}

}
