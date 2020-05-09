package com.springboot.mydemo.dto;

public class JsonResult {

	/**
	 * 返回的状态码 (Same as HttpStatus.value()).
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * 返回的数据
	 */
	private Object result;

	public JsonResult() {
		super();
	}

	public JsonResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public JsonResult(Integer code, String msg, Object result) {
		super();
		this.code = code;
		this.msg = msg;
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", result=" + result + "]";
	}

}
