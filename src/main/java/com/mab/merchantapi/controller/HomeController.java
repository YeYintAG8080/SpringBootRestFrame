package com.mab.merchantapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mab.merchantapi.model.ApiUser;

@RestController
@RequestMapping(value = "/HOME")
public class HomeController {

	@Autowired 
	@Qualifier(value = "uat_namedJdbcTemplate")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@GetMapping("")
	public @ResponseBody String welcome(){
		return "Welcome from API";
	}
	
	@PreAuthorize("@WebSecurity.checkUserId(authentication,#userId)")
	@GetMapping("/profile/{userId}")
	public @ResponseBody String getUserProfileById(@PathVariable(name="userId",required=true) String userId){
		return "You got user with id: "+userId;
	}
	
	@GetMapping("/article")
	public @ResponseBody List<ApiUser> article() {
		String query = " SELECT * FROM API_USER"; 
		List<ApiUser> users = namedJdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ApiUser.class));
		return users;
	}
	
	
}
