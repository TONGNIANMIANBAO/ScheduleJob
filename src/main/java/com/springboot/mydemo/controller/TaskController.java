package com.springboot.mydemo.controller;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mydemo.bean.ScheduleCron;
import com.springboot.mydemo.bean.ScheduleJob;
import com.springboot.mydemo.dto.JsonResult;
import com.springboot.mydemo.service.ScheduleService;
import com.springboot.mydemo.utils.ReflectUtils;

@RestController
@RequestMapping("/schedule")
public class TaskController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private Future<?> future;
	private static Map<Integer, Future<?>> futureMap = new HashMap<>();

	@Autowired
	ThreadPoolTaskScheduler task;

	@Autowired
	ScheduleService service;

	@RequestMapping("/getCron")
	public List<ScheduleCron> getScheduleCron(Integer id) {
		log.info("getScheduleCron...{}", id);
		ScheduleCron scheduleCron = service.getScheduleCron(id);
		// return new JsonResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
		// scheduleCron);
		List<ScheduleCron> list = new ArrayList<>();
		list.add(scheduleCron);
		return list;
	}

	@RequestMapping("/getData")
	public JsonResult getData() {
		log.info("getData");
		List<ScheduleJob> list = service.getAllScheduleJob();
		return new JsonResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list);
	}

	@RequestMapping("/saveJob")
	public JsonResult saveTask(ScheduleJob job) {
		log.info("saveTask:[{}]", job);
		job.setUpdateTime(localDateTimeToDate());
		service.insertScheduleJob(job);
		return new JsonResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), job);
	}

	/**
	 * 停止任務
	 * 
	 * @return
	 */
	@RequestMapping("/stop/{jobID}")
	public JsonResult stopTask(@PathVariable Integer jobID) {
		log.info("stopTask:[{}] ", jobID);
		future = futureMap.remove(jobID);
		if (future != null) {
			future.cancel(false);
		}
		log.info("task [{}] stop", jobID);
		return new JsonResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				String.format("task %d stop", jobID));
	}

	/**
	 * 啟動任務
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("/start/{jobID}")
	public JsonResult startTask(@PathVariable Integer jobID)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		log.info("startTask:[{}] ", jobID);
		if (futureMap.get(jobID) != null) {
			log.info("task [{}] already start", jobID);
			return new JsonResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					String.format("task %d already start", jobID));
		}

		ScheduleJob job = getJob(jobID);
		if (job == null) {
			return new JsonResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					String.format("%d job can not found", jobID));
		}

		if (job.getValid() != 1) {
			return new JsonResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					String.format("%d job is not valid", jobID));
		}

		String cron = getCron(job.getCronId());
		if (!CronExpression.isValidExpression(cron)) {
			log.info("job [{}] cron [{}] is not valid", jobID, cron);
			return new JsonResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					String.format("job cron %s is not valid", cron));
		}

		Runnable runnable = ReflectUtils.getInstance(job.getJobClass(), job.getJobParam());
		future = task.schedule(runnable, triggerContext -> {
			return new CronTrigger(cron).nextExecutionTime(triggerContext);
		});

		System.out.println(localDateTimeToDate());
		job.setRunTime(localDateTimeToDate());
		updateJob(job);
		futureMap.put(jobID, future);

		log.info("task [{}] start", jobID);
		return new JsonResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), futureMap);
	}

	/**
	 * 更改任務cron
	 * 
	 * @param cronID
	 * @return
	 */
	@RequestMapping("/change/{jobID}/{cronID}")
	public String changeTaskCron(@PathVariable Integer cronID, @PathVariable Integer jobID) {
		stopTask(jobID);

		String cron = getCron(cronID);
		if (!CronExpression.isValidExpression(cron)) {
			return cron + " is not valid";
		}
		future = task.schedule(() -> {
			System.out.println("TaskScheduler\t:" + LocalTime.now());
		}, triggerContext -> {
			return new CronTrigger(cron).nextExecutionTime(triggerContext);
		});
		System.out.println("change task cron ok");
		return "change task cron ok";
	}

	private String getCron(Integer id) {
		return service.getCron(id);
	}

	private ScheduleJob getJob(Integer id) {
		return service.getScheduleJob(id);
	}

	private Integer updateJob(ScheduleJob job) {
		return service.updateScheduleJob(job);
	}

	private Date localDateTimeToDate() {
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		return Date.from(zdt.toInstant());
	}

}
