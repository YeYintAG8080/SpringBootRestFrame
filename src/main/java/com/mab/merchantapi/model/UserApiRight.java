package com.mab.merchantapi.model;

import java.io.Serializable;

public class UserApiRight implements Serializable{
	
	private static final long serialVersionUID = -3000907174170804567L;
	
	private long syskey;
	private String user_syskey;
	private String service_syskey;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	
	public long getSyskey() {
		return syskey;
	}
	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}
	public String getUser_syskey() {
		return user_syskey;
	}
	public void setUser_syskey(String user_syskey) {
		this.user_syskey = user_syskey;
	}
	public String getService_syskey() {
		return service_syskey;
	}
	public void setService_syskey(String service_syskey) {
		this.service_syskey = service_syskey;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getT2() {
		return t2;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
	public String getT3() {
		return t3;
	}
	public void setT3(String t3) {
		this.t3 = t3;
	}
	public String getT4() {
		return t4;
	}
	public void setT4(String t4) {
		this.t4 = t4;
	}
	public String getT5() {
		return t5;
	}
	public void setT5(String t5) {
		this.t5 = t5;
	}
}
