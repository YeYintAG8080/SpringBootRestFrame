package com.mab.merchantapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser extends ApiUser implements Serializable, UserDetails {
	private static final long serialVersionUID = -3862004311274741115L;

	private List<ApiService> api_services;
		
	public List<ApiService> getApi_services() {
		return api_services;
	}

	public void setApi_services(List<ApiService> api_services) {
		this.api_services = api_services;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		return list;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUser_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}	
}
