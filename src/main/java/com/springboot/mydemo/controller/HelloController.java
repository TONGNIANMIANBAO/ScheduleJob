package com.springboot.mydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mydemo.bean.ScheduleCron;
import com.springboot.mydemo.service.ScheduleService;

@RestController
public class HelloController {

	@Autowired
	ScheduleService service;

	@RequestMapping("/addCron")
	public String addCron() {
		String result = "error";
		ScheduleCron sc = new ScheduleCron();
		sc.setCron("0/5 * * * * ?");
		if (service.saveCron(sc) == 1) {
			result = "ok";
		}
		return result;
	}

	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}
