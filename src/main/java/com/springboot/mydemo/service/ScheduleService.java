package com.springboot.mydemo.service;

import java.util.List;

import com.springboot.mydemo.bean.ScheduleCron;
import com.springboot.mydemo.bean.ScheduleJob;

public interface ScheduleService {

	Integer saveCron(ScheduleCron sc);

	String getCron(Integer id);

	ScheduleCron getScheduleCron(Integer id);

	ScheduleJob getScheduleJob(Integer id);

	Integer updateScheduleJob(ScheduleJob job);

	Integer insertScheduleJob(ScheduleJob job);

	List<ScheduleJob> getAllScheduleJob();
}
