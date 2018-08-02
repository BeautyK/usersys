package com.lingnan.usersys.usermgr.business.service;

import java.util.Vector;

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
	
	/**
	 * 注册或添加用户的方法
	 * 
	 * @param user 新用户信息
	 * @return 成功放回true，失败返回false
	 */
	public boolean addUser(UserVO user);
	
	/**
	 * 取得数据库用户表ID列的最大值
	 * @return 返回ID的最大值
	 */
	public int findMaxId();
	
	/**
	 * 修改用户信息的方法
	 * 
	 * @param user 修改后的用户信息
	 * @return 成功放回true，失败返回false
	 */
	public boolean updateUser(UserVO user);
	
	/**
	 * 根据id查询用户
	 * @param id查询用户信息的id
	 * @return 返回该id用户的信息
	 */
	public UserVO findUserById(String id);
	
	/**
	 * 根据id删除用户信息
	 * @param id 需要删除的用户的id
	 * @return 删除成功返回true，删除失败返回false
	 */
	public boolean deleteUser(String id);
	
	/**
	 * 查询所有用户
	 * @return 结果集
	 */
	public Vector<UserVO> findAll();
	
	/**
	 * 根据名字查询用户
	 * @param name 需要根据name查找的关键字符串
	 * @return 返回名字包含该字符串的用户信息
	 */
	public Vector<UserVO> findUserByName(String name);
	
	/**
	 * 分页查询全部用户
	 * @param page 页码
	 * @return 返回指定页的用户信息
	 */
	public Vector<UserVO> findUsers(int page) ;
}
