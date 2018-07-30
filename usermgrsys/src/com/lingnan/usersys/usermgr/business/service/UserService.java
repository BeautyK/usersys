package com.lingnan.usersys.usermgr.business.service;

import com.lingnan.usersys.usermgr.domain.UserVO;

public interface UserService {

	/**
	 * 用户登录
	 * 
	 * @param userId 用户账号
	 * @param passWord 密码
	 * @return 用户信息
	 */
	public UserVO login(String userId, String passWord);
}
