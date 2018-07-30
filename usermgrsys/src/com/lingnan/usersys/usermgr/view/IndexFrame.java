package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

public class IndexFrame implements BaseFrame{

	
	public void show() {
		//声明缓冲对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//循环操作
		while(true) {
			//用户登录和注册界面
			System.out.println("欢迎使用用户管理系统");
			System.out.println("**************");
			System.out.println("用户登录---------1");
			System.out.println("用户注册---------2");
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
			}
			/**
			 * 判断用户输入值，如果值为1，进行用户登录操作，
			 * 如果值为2，进行用户注册操作，如果值为3，退出系统
			 */
			switch (i) {
			case 1:
				this.loginShow();
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

	public void loginShow() {
	    //声明缓冲处理对象，用于接收控制台输入的数据	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("用户登录界面");
		System.out.println("***************");
		System.out.println("请输入您的用户名:");
		try {
			//以行为单位，读取输入的各个值，赋值给用户对象各个属性
			String userId = br.readLine();
			System.out.println("请输入您的密码:");
			String passWord = br.readLine();
			//调用控制台中的doLogin方法，进行用户登录操作
			UserController uc = new UserController();
			UserVO user =  uc.doLogin(userId, passWord);
			if(user != null) {
				System.out.println("登录成功~");
				//调用用主界面
				AdminFrame af = new AdminFrame(user);
				af.loginSuccShow();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void addShow(String type) {
		
		
	}

}
