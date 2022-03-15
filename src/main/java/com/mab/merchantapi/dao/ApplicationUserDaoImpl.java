package com.mab.merchantapi.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mab.merchantapi.model.ApiService;
import com.mab.merchantapi.model.ApiUser;
import com.mab.merchantapi.model.ApplicationUser;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

	private Logger log = LoggerFactory.getLogger(ApplicationUserDaoImpl.class);
	
	@Autowired 
	@Qualifier(value = "uat_namedJdbcTemplate")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public ApiUser getUserByUserId(String userId) throws Exception {
		String query = " SELECT * FROM API_USER WHERE user_id = :userId";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		
		List<ApiUser> users = namedJdbcTemplate.query(query, params, BeanPropertyRowMapper.newInstance(ApiUser.class));
		return users.get(0);
	}

	@Override
	public ApiUser getUserByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApiUser> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkUserAlreadyExists(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveUser(ApplicationUser user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(ApplicationUser user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ApiService> getPermissionOfUser(String userId) throws Exception {
		String query = 
				 "SELECT B.* " + 
		         "FROM API_USER U " +
		         "INNER JOIN USER_API_RIGHT R ON U.SYSKEY = R.USER_SYSKEY " + 
		         "INNER JOIN API_SERVICE B ON R.SERVICE_SYSKEY = B.SYSKEY " +
		         "WHERE U.USER_ID = 'USER004' ";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		
		List<ApiService> services = namedJdbcTemplate.query(query, params, BeanPropertyRowMapper.newInstance(ApiService.class));
		return services;
	}
}
