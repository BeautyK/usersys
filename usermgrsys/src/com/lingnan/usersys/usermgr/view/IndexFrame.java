package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;
import com.lingnan.usersys.common.util.TypeUtils;

public class IndexFrame implements BaseFrame{

	/**
	 * 系统主页显示
	 */
	public void show() {
		//声明缓冲对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//循环操作
		while(true) {
			//用户登录和注册界面
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^\n");
			System.out.println("    欢 迎 使 用 用 户 管 理 系 统        \n");
			System.out.println("               by 陈雅茵");
			System.out.println("----------------------");
			System.out.println("                   菜 单");
			System.out.println("用户登录---------1");
			System.out.println("用户注册---------2");
			System.out.println("退出程序---------3");
			System.out.println("**********************");
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
				this.addShow("注册");
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
	
	/**
	 * 登录页面显示
	 */
	public void loginShow() {
	    //声明缓冲处理对象，用于接收控制台输入的数据	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("**********************");
		System.out.println("                   用 户 登 录    \n");
		System.out.println("请输入您的用户ID:");
		try {
			//以行为单位，读取输入的各个值，赋值给用户对象各个属性
			String userId = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(userId)) {
				System.out.println("用户ID不能为空，请重新输入：");
				userId = br.readLine();
			}
			System.out.println("请输入您的密码:");
			String passWord = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(userId)) {
				System.out.println("密码不能为空，请重新输入：");
				passWord = br.readLine();
			}
			//调用控制台中的doLogin方法，进行用户登录操作
			UserController uc = new UserController();
			UserVO user =  uc.doLogin(userId, passWord);
			if(user != null) {
				System.out.println("登录成功~");
				//调用用主界面
				AdminFrame af = new AdminFrame(user);
				af.loginSuccShow();
				//退出当前页面
				System.exit(0);
			} else {
				//登录失败，显示失败信息
				System.out.println("登录失败~");
			}
		} catch (Exception e) {
			// 将异常封装到自定义异常并抛出
			throw new ServiceException("登录出错！", e);
		}
	}

	/**
	 * 添加用户的页面显示
	 */
	public void addShow(String type) {
		//声明缓冲处理对象，用于接收控制台输入的数据	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		UserVO user = new UserVO();
		String userId = null;
		String name = null;
		String email = null;
		String birth = null;
		String passWord = null;
		if (type.equals("注册")) {
			try {
				System.out.println("用户注册界面");
				System.out.println("***************");
				//设置注册用户的Id的值
				user.setId(uc.doFindMaxId()+1);
				System.out.println("请输入您的用户ID:");	
				userId = br.readLine();
				//循环操作，若字符串为空，则提示重新输入
				while (TypeUtils.checkString(userId)) {
					System.out.println("用户ID不能为空，请重新输入：");
					userId = br.readLine();
				}
				//设置注册用户的userID的值
				user.setUserId(userId); 
				System.out.println("请输入您的用户名:");
				name = br.readLine();
				//循环操作，若字符串为空，则提示重新输入
				while (TypeUtils.checkString(name)) {
					System.out.println("用户名不能为空，请重新输入：");
					name = br.readLine();
				}
				//设置注册用户的name的值
				user.setName(name);
				System.out.println("请输入您的邮箱:");				
				email = br.readLine();
				//循环操作，若字符串为空，则提示重新输入
				while (TypeUtils.checkString(email)) {
					System.out.println("邮箱不能为空，请重新输入：");
					email = br.readLine();
				}
				while(true) {//判断邮箱格式是否正常
					//正确，设置注册用户的email的值并退出循环
					if (TypeUtils.checkEmailFormat(email)) {
						user.setEmail(email);
						break;
					} else {//错误，重新输入
						System.out.println("邮箱格式错误，请重新输入：");
						email = br.readLine();
					}
				}		
				System.out.println("请输入您的生日(yyyy-mm-dd):");
				/*
				 * 调用TypeUtils类的stringToDate方法把字
				 * 符串转为日期，并设置注册用户的birth的值
				 */
				birth = br.readLine();
				//循环操作，若字符串为空，则提示重新输入
				while (TypeUtils.checkString(birth)) {
					System.out.println("生日不能为空，请重新输入：");
					birth = br.readLine();
				}
				user.setBirth(TypeUtils.stringToDate(birth));				
				System.out.println("请输入您的密码:");
				passWord = br.readLine();
				//循环操作，若字符串为空，则提示重新输入
				while (TypeUtils.checkString(passWord)) {
					System.out.println("密码不能为空，请重新输入：");
					passWord = br.readLine();
				}
				//设置注册用户的passWord的值
				user.setPassword(passWord);
				//设置注册用户的powers的值
				user.setPowers("普通用户");
				//设置注册用户的status的值
				user.setStatus("0");
				if (uc.doAddUser(user)) {
					System.out.println("注册成功~");
				} else {
					System.out.println("注册失败~");
				}
			} catch (IOException e) {
				// 将异常封装到自定义异常并抛出
				throw new ServiceException("注册出错！", e);
			}
		} else {//若type不是注册，则为管理员添加用户操作，跳转指定方法
			new AdminFrame(null).addShow();
		}	
	}

}
