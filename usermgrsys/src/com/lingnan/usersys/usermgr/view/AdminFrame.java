package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.lingnan.usersys.usermgr.domain.UserVO;

public class AdminFrame extends NormalFrame {
	
	/**
	 * 带参数的构造器，用于初始化user属性
	 * 
	 * @param user
	 */
	public AdminFrame(UserVO user) {
		super(user);
	}
	
	public void loginSuccShow() {
		System.out.println("**************");
		System.out.println("欢迎登录主窗口");
		System.out.println(user.getName()+" 您好，您是"+user.getPowers());
		System.out.println("**************");
		if (user.getPowers().equals("管理员")) {
			this.show();
		} else {
			super.show();
		}
	}
	
	public void show() {
		//声明缓冲对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//循环操作
		while(true) {
			//用户登录和注册界面
			System.out.println("添加用户---------1");
			System.out.println("删除用户---------2");
			System.out.println("修改用户---------3");
			System.out.println("查询用户---------4");
			System.out.println("程序退出---------5");
			System.out.println("请输入你的选择");
			int i = -1;
			//读取用户控制台输入，如果输入值正确，中断循环，否则提示错误信息，再重新输入
			while (true) {
				//读取用户输入操作选项的数字，同时转换为int型
				try {
					i = Integer.parseInt(br.readLine());
					break;
				} catch (Exception e) {
					System.out.println("输入错误，只能输入1~5的数字。");
					System.out.println("请您重新输入");
				} 
				/**
				 * 判断用户输入值，如果值为1，进行添加用户操作；如果值为2，
				 * 进行删除用户操作；如果值为3，进行修改用户操作；如果值为4，
				 * 进行查询用户操作；如果值为5，退出系统
				 */
				switch (i) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
