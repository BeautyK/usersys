package com.lingnan.usersys.usermgr.controller;

import com.lingnan.usersys.usermgr.business.service.UserService;
import com.lingnan.usersys.usermgr.business.service.UserServiceImpl;
import com.lingnan.usersys.usermgr.domain.UserVO;


/**
 * 用户控制层
 * @author Beauty
 *
 */
public class UserController {
	//声明用户service接口对象，用于业务处理
	UserService userMgrService = UserServiceImpl.getInsance();
	
	/**
	 * 用户登录
	 * @param userId
	 * @param passWord
	 * @return 用户信息
	 */
	public UserVO doLogin(String userId, String passWord) {
		UserVO user = null;
		try {
			//调用用户service接口中的login方法，进行用户登录操作
			user = userMgrService.login(userId, passWord);
		} catch (Exception e) {
			// 登录异常信息
			System.out.println("用户登录的时候出错"+e.getMessage());
		}
		return user;
	}
}
