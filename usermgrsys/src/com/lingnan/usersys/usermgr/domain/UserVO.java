package com.lingnan.usersys.usermgr.domain;

import java.util.Date;

/**
 * 封装展示的字段的类
 * 
 * @author Beauty
 *
 */
public class UserVO {
	private int id;			    //主键
	private String userId;      //用户账号
	private String name;		//用户姓名
	private String password;	//用户密码
	private String powers;		//用户类型
	private String email;		//用户邮箱
	private Date birth;			//用户生日
	private String status;		//状态
	
	/**
	 * 返回主键的方法
	 * @return 主键
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 设置主键的方法
	 * @param id 传递进来的主键
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 返回用户账号的方法
	 * 
	 * @return 返回用户账户的值
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 设置用户账号的方法
	 * 
	 * @param userId 传递进来的用户账号
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取用户姓名的方法
	 * 
	 * @return 返回用户的姓名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置用户姓名的方法
	 * 
	 * @param name 传递进来的用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 返回用户密码的方法
	 * @return 用户密码
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 设置用户密码的方法
	 * 
	 * @param password 传递进来的用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 返回用户类型的方法
	 * @return 用户类型
	 */
	public String getPowers() {
		return powers;
	}
	
	/**
	 * 设置用户类型的方法
	 * 
	 * @param powers 传递进来的用户类型
	 */
	public void setPowers(String powers) {
		this.powers = powers;
	}
	
	/**
	 * 返回用户邮箱的方法
	 * @return 用户邮箱
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置用户邮箱的方法
	 * 
	 * @param email 传递进来的用户邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 返回用户生日的方法
	 * @return 用户生日
	 */
	public Date getBirth() {
		return birth;
	}
	
	/**
	 * 设置用户生日的方法
	 * 
	 * @param birth 传递进来的用户生日
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	/**
	 * 返回用户状态的方法
	 * @return 用户状态
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置用户状态的方法
	 * 
	 * @param status 传递进来的用户状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
