package com.lingnan.usersys.usermgr.controller;

import java.util.Vector;

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
	 * @param userId 登录的用户ID
	 * @param passWord 登录的密码
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
	
	/**
	 * 注册或添加用户的方法
	 * 
	 * @param user 用户信息
	 * @return 成功放回true，失败返回false
	 */
	public boolean doAddUser(UserVO user) {
		boolean flag = false;
		try {
			//调用用户service接口中的addUser方法，进行注册或添加用户操作
			flag = userMgrService.addUser(user);
		} catch (Exception e) {
			// 注册或添加用户异常信息
			System.out.println("注册或添加用户的时候出错"+e.getMessage());
		}
		return flag;
	}

	/**
	 * 取得数据库用户表ID列的最大值
	 * @return 返回ID的最大值
	 */
	public int doFindMaxId() {
		int maxId = 0;
		try {
			//调用用户service接口中的findMaxId方法，取得数据库用户表ID列的最大值
			maxId = userMgrService.findMaxId();
		} catch (Exception e) {
			// 登录异常信息
			System.out.println("取得数据库用户表ID列的最大值的时候出错"+e.getMessage());
		}
		return maxId;
	}
	
	/**
	 * 修改用户信息的方法
	 * 
	 * @param user 修改后的用户信息
	 * @return 修改成功返回true，失败返回false
	 */
	public boolean doUpdateUser(UserVO user) {
		boolean flag = false;
		try {
			//调用用户service接口中的updateUser方法，进行修改用户信息操作
			flag = userMgrService.updateUser(user);
		} catch (Exception e) {
			// 修改用户异常信息
			System.out.println("修改用户信息的时候出错"+e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 根据id查询用户
	 * @param id 需要查询的id
	 * @return 返回该id用户的信息
	 */
	public UserVO doFindUserById(String id) {
		UserVO user = null;
		try {
			//调用用户service接口中的findUserById方法，进行按id查找用户操作
			user = userMgrService.findUserById(id);
		} catch (Exception e) {
			// 按照id查找用户的异常信息
			System.out.println("按id查找用户出错："+e.getMessage());
		}
		return user;
	}
	
	/**
	 * 根据id删除用户信息
	 * @param id 需要删除的用户的id
	 * @return 删除成功返回true，删除失败返回false
	 */
	public boolean doDeleteUser(String id) {
		boolean flag = false;
		try {
			//调用用户service接口中的updateUser方法，进行修改用户信息操作
			flag = userMgrService.deleteUser(id);
		} catch (Exception e) {
			// 修改用户异常信息
			System.out.println("删除用户信息的时候出错"+e.getMessage());
		}
		return flag;		
	}
	
	/**
	 * 查询所有用户
	 * @return 结果集
	 */
	public Vector<UserVO> doFindAll() {
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用用户service接口中的findAll方法，进行查询全部用户信息
			v = userMgrService.findAll();
		} catch (Exception e) {
			// 修改用户异常信息
			System.out.println("查询全部用户时出错："+e.getMessage());
		}
		return v;
	}
	
	/**
	 * 根据名字查询用户
	 * @param name 需要根据name查找的关键字符串
	 * @return 返回名字包含该字符串的用户信息
	 */
	public Vector<UserVO> doFindUserByName(String name) {
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用用户service接口中的findUserByName方法，进行根据name查询用户操作
			v = userMgrService.findUserByName(name);
		} catch (Exception e) {
			// 修改用户异常信息
			System.out.println("根据name查找用户时出错："+e.getMessage());
		}
		return v;
	}
	
	/**
	 * 分页查询全部用户
	 * @param page 页码
	 * @return 返回指定页的用户信息
	 */
	public Vector<UserVO> doFindUsers(int page) {
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用用户service接口中的findAll方法，进行查询全部用户信息
			v = userMgrService.findUsers(page);
		} catch (Exception e) {
			// 修改用户异常信息
			System.out.println("分页查询全部用户时出错："+e.getMessage());
		}
		return v;
	}
}
