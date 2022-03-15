package com.mab.merchantapi.service;

import java.util.List;

import com.mab.merchantapi.model.ApiUser;
import com.mab.merchantapi.model.ApplicationUser;

public interface ApplicationUserService {

	ApiUser getUserByUserId(String userId) throws Exception;

	ApiUser getUserByUserName(String userName) throws Exception;
	
	ApplicationUser getApplicationUserByUserName(String userName) throws Exception;

	List<ApiUser> getUserList() throws Exception;

	Boolean checkUserAlreadyExists(String id) throws Exception;

	int saveUser(ApplicationUser user) throws Exception;

	int updateUser(ApplicationUser user) throws Exception;

}
