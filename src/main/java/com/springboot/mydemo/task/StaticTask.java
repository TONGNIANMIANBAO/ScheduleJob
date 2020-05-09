package com.springboot.mydemo.task;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class StaticTask {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "0/5 * * * * ?")
	public void task() {
		log.info("StaticTask \t:{}", LocalTime.now());
	}
}
