package com.mab.merchantapi.model;

import java.io.Serializable;

public class Login implements Serializable{
	private static final long serialVersionUID = 5659169718264848220L;
	
	private String user_id;
	private String password;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
