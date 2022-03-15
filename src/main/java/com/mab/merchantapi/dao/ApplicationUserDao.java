package com.mab.merchantapi.dao;

import java.util.List;

import com.mab.merchantapi.model.ApiService;
import com.mab.merchantapi.model.ApiUser;
import com.mab.merchantapi.model.ApplicationUser;


public interface ApplicationUserDao {
	ApiUser getUserByUserId(String userId) throws Exception;
	
	ApiUser getUserByUserName(String userName) throws Exception;
	
	List<ApiUser> getUserList() throws Exception;

	Boolean checkUserAlreadyExists(String id) throws Exception;

	int saveUser(ApplicationUser user) throws Exception;

	int updateUser(ApplicationUser user) throws Exception;
	
	List<ApiService> getPermissionOfUser(String userId) throws Exception;
}
