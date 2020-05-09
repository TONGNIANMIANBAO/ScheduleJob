package com.springboot.mydemo.bean;

import java.util.Date;

public class ScheduleJob {
	private Integer id;

	private String jobName;

	private String jobDesc;

	private String jobGroup;

	private String jobClass;

	private String jobParam;

	private Integer cronId;

	private Date runTime;

	private Date nextTime;

	private Integer valid;

	private Integer empNo;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName == null ? null : jobName.trim();
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc == null ? null : jobDesc.trim();
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup == null ? null : jobGroup.trim();
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass == null ? null : jobClass.trim();
	}

	public String getJobParam() {
		return jobParam;
	}

	public void setJobParam(String jobParam) {
		this.jobParam = jobParam == null ? null : jobParam.trim();
	}

	public Integer getCronId() {
		return cronId;
	}

	public void setCronId(Integer cronId) {
		this.cronId = cronId;
	}

	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
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
		return "ScheduleJob [id=" + id + ", jobName=" + jobName + ", jobDesc=" + jobDesc + ", jobGroup=" + jobGroup
				+ ", jobClass=" + jobClass + ", jobParam=" + jobParam + ", cronId=" + cronId + ", runTime=" + runTime
				+ ", nextTime=" + nextTime + ", valid=" + valid + ", empNo=" + empNo + ", updateTime=" + updateTime
				+ "]";
	}

}