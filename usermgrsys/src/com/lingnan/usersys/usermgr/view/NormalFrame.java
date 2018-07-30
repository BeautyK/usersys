package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.lingnan.usersys.usermgr.domain.UserVO;

public class NormalFrame extends IndexFrame {
	
	//用户对象
	UserVO user = null;
	/**
	 * 带参数的构造器，用于初始化user属性
	 * 
	 * @param user
	 */
	public NormalFrame(UserVO user) {
		this.user = user;
	}
	
	public void show() {
		//声明缓冲对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//循环操作
		while(true) {
			//用户登录和注册界面
			System.out.println("修改自己的信息---------1");
			System.out.println("查询自己的信息---------2");
			System.out.println("退出程序---------3");
			System.out.println("请输入你的选择");
			int i = -1;
			//读取用户控制台输入，如果输入值正确，中断循环，否则提示错误信息，再重新输入
			while (true) {
				//读取用户输入操作选项的数字，同时转换为int型
				try {
					i = Integer.parseInt(br.readLine());
					break;
				} catch (Exception e) {
					System.out.println("输入错误，只能输入1~3的数字。");
					System.out.println("请您重新输入");
				} 
				/**
				 * 判断用户输入值，如果值为1，进行修改自己信息操作，
				 * 如果值为2，进行查询自己信息操作，如果值为3，退出系统
				 */
				switch (i) {
				case 1:
					//this.loginShow();
					break;
				case 2:
					//this.addShow("注册");
					break;
				case 3:
					System.out.println("感谢您的使用，再会！");
					//退出当前页面
					System.exit(0);
					break;
				default://输入1~3之外的数字
					System.out.println("您的输入操作不正确，请重新输入。");
				}
			}
		}
	}
}
