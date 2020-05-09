package com.springboot.mydemo.bean;

import java.util.Date;

public class ScheduleCron {
	private Integer id;

	private String cron;

	private Integer empNo;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron == null ? null : cron.trim();
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ScheduleCron [id=" + id + ", cron=" + cron + ", empNo=" + empNo + ", updateTime=" + updateTime + "]";
	}

}