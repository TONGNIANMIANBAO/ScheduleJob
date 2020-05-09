package com.springboot.mydemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mydemo.bean.ScheduleCron;
import com.springboot.mydemo.bean.ScheduleJob;
import com.springboot.mydemo.bean.ScheduleJobExample;
import com.springboot.mydemo.dao.ScheduleCronMapper;
import com.springboot.mydemo.dao.ScheduleJobMapper;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleCronMapper cronMapper;

	@Autowired
	ScheduleJobMapper jobMapper;

	@Override
	public Integer saveCron(ScheduleCron sc) {
		return cronMapper.insertSelective(sc);
	}

	@Override
	public String getCron(Integer id) {
		ScheduleCron cron = cronMapper.selectByPrimaryKey(id);
		return cron.getCron();
	}

	@Override
	public ScheduleJob getScheduleJob(Integer id) {
		return jobMapper.selectByPrimaryKey(id);

	}

	@Override
	public Integer updateScheduleJob(ScheduleJob job) {

		return jobMapper.updateByPrimaryKey(job);
	}

	@Override
	public Integer insertScheduleJob(ScheduleJob job) {
		return jobMapper.insertSelective(job);
	}

	@Override
	public List<ScheduleJob> getAllScheduleJob() {
		ScheduleJobExample example = new ScheduleJobExample();
		example.setOrderByClause("id asc");
		return jobMapper.selectByExample(example);
	}

	@Override
	public ScheduleCron getScheduleCron(Integer id) {
		return cronMapper.selectByPrimaryKey(id);
	}

}
