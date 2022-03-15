package com.mab.merchantapi.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mab.merchantapi.dao.ApplicationUserDao;
import com.mab.merchantapi.model.ApiService;
import com.mab.merchantapi.model.ApiUser;
import com.mab.merchantapi.model.ApplicationUser;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

	public final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationUserDao applicationUserDao;

	@Override
	public ApiUser getUserByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	public ApplicationUser getApplicationUserByUserName(String userId) throws Exception {
		ApiUser user = this.applicationUserDao.getUserByUserId(userId);
		
		ApplicationUser appUser = new ApplicationUser();
		appUser.setUser_id(user.getUser_id());
		appUser.setPassword(user.getPassword());
		appUser.setUser_name(user.getUser_name());
		appUser.setSource(user.getSource());
		appUser.setT1(user.getT1());
		appUser.setT2(user.getT2());
		appUser.setT3(user.getT3());
		appUser.setT4(user.getT4());
		appUser.setT5(user.getT5());
		
		List<ApiService> services = this.applicationUserDao.getPermissionOfUser(userId);
		if(services != null) {
			appUser.setApi_services(services);	
		}
		else {
			appUser.setApi_services(new ArrayList<>());
		}
		
		return appUser;
	}
}
