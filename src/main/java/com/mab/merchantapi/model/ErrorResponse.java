package com.mab.merchantapi.model;

import java.io.Serializable;

public class ErrorResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8803993989279284205L;
	
	private Integer status;
	private String message;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
