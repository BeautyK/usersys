package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 普通用户显示的页面
 * 
 * @author Beauty
 *
 */
public class NormalFrame extends IndexFrame {
	
	/**
	 * 用户对象
	 */
	UserVO user = null;
	/**
	 * 带参数的构造器，用于初始化user属性
	 * 
	 * @param user
	 */
	public NormalFrame(UserVO user) {
		this.user = user;
	}
	
	/**
	 * 普通用户登录后的页面显示
	 */
	public void show() {
		//声明缓冲对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//循环操作
		while(true) {
			//普通用户功能菜单界面
			System.out.println("**********************");
			System.out.println("                   菜 单");
			System.out.println("修改自己的信息---------1");
			System.out.println("查询自己的信息---------2");
			System.out.println("退 出 程 序------------3");
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
				/*
				 * 判断用户输入值，如果值为1，进行修改自己信息操作，
				 * 如果值为2，进行查询自己信息操作，如果值为3，退出系统
				 */
				switch (i) {
				case 1:
					this.updateShow(user);
					break;
				case 2:
					this.searchShow(user);
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
	 * 普通用户修改个人信息的页面显示
	 * @param user 修改信息前的用户信息
	 */
	public void updateShow(UserVO user) {
		//声明缓冲处理对象，用于接收控制台输入的数据	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		String name = null;
		String email = null;
		String birth = null;
		String passWord = null;
		System.out.println("**********************");
		System.out.println("您现在的信息是：\n");
		System.out.println("ID\t用户ID\t用户名\t密码\t邮箱\t\t生日\t\t用户类型");
		System.out.println(user.getId()+"\t"+user.getUserId()+"\t"+user.getName()+"\t"+user.getPassword()+"\t"+user.getEmail()+"\t"+user.getBirth()+"\t"+user.getPowers());
		System.out.println("\n***************");
		try { 
			System.out.println("请输入要修改的名字:");
			name = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(name)) {
				System.out.println("用户名不能为空，请重新输入：");
				name = br.readLine();
			}
			//设置修改后用户的name的值
			user.setName(name);
			System.out.println("请输入要修改的邮箱:");				
			email = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(email)) {
				System.out.println("邮箱不能为空，请重新输入：");
				email = br.readLine();
			}
			while(true) {//判断邮箱格式是否正常
				//正确，设置修改后用户的email的值并退出循环
				if (TypeUtils.checkEmailFormat(email)) {
					user.setEmail(email);
					break;
				} else {//错误，重新输入
					System.out.println("邮箱格式错误，请重新输入：");
					email = br.readLine();
				}
			}		
			System.out.println("请输入要修改的生日(yyyy-mm-dd):");
			/*
			* 调用TypeUtils类的stringToDate方法把字
			* 符串转为日期，并设置修改后用户的birth的值
			*/
			birth = br.readLine();
			while (TypeUtils.checkString(birth)) {
				System.out.println("生日不能为空，请重新输入：");
				birth = br.readLine();
			}
			user.setBirth(TypeUtils.stringToDate(birth));				
			System.out.println("请输入要修改的密码:");
			passWord = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(passWord)) {
				System.out.println("密码不能为空，请重新输入：");
				passWord = br.readLine();
			}
			//设置修改后用户的passWord的值
			user.setPassword(passWord);
			user.setPowers("普通用户");
			if (uc.doUpdateUser(user)) {
				System.out.println("修改成功~");
			} else {
				System.out.println("修改失败~");
			}
		} catch (IOException e) {
			// 将异常封装到自定义异常并抛出
			throw new ServiceException("修改出错！", e);
		}		
	}
	
	/**
	 * 普通用户查询个人信息的页面显示
	 * @param user 需要显示的用户信息
	 */
	public void searchShow(UserVO user) {
		System.out.println("**********************");
		System.out.println("    您的个人信息     \n");
		System.out.println(" ID："+user.getId());
		System.out.println(" 用户ID："+user.getUserId());
		System.out.println(" 用户名："+user.getName());
		System.out.println(" 用户邮箱："+user.getEmail());
		System.out.println(" 用户生日："+user.getBirth());
		System.out.println(" 用户权限："+user.getPowers());
		System.out.println(" 用户密码："+user.getPassword());
		System.out.println("\n**********************");
	}
}
