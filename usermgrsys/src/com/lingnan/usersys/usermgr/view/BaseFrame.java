package com.lingnan.usersys.usermgr.view;

public interface BaseFrame {
	
	/**
	 * 主页面显示
	 */
	public void show();
	
	/**
	 * 登录页面显示
	 */
	public void loginShow();
	
	/**
	 * 添加用户页面显示
	 * @param type 添加用户的类型
	 */
	public void addShow(String type);
}
