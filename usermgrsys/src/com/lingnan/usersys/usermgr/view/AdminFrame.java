package com.lingnan.usersys.usermgr.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
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
	
	/**
	 * 登录成功页面显示
	 * 根据权限跳转不同的页面
	 */
	public void loginSuccShow() {
		System.out.println("**********************");
		System.out.println("           欢 迎 登 录 主 窗 口");
		System.out.println("\n      "+user.getName()+" 您好，您是"+user.getPowers());
		if (user.getPowers().equals("管理员")) {
			this.show();
		} else {
			super.show();
		}
	}
	
	/**
	 * 管理员登录后的页面显示
	 */
	public void show() {
		//声明缓冲对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//循环操作
		while(true) {
			//用户登录和注册界面
			System.out.println("**********************");
			System.out.println("                   菜 单");
			System.out.println("添加用户---------1");
			System.out.println("删除用户---------2");
			System.out.println("修改用户---------3");
			System.out.println("查询用户---------4");
			System.out.println("程序退出---------5");
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
					System.out.println("输入错误，只能输入1~5的数字。");
					System.out.println("请您重新输入");
				}
			}
				/**
				 * 判断用户输入值，如果值为1，进行添加用户操作；如果值为2，
				 * 进行删除用户操作；如果值为3，进行修改用户操作；如果值为4，
				 * 进行查询用户操作；如果值为5，退出系统
				 */			
				switch (i) {
				case 1:
					this.addShow("添加用户");
					break;
				case 2:
					this.delShow();
					break;
				case 3:
					this.updateShow();
					break;
				case 4:
					this.searchShow();
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

	/**
	 * 管理员添加用户的页面显示
	 */
	public void addShow() {
		//声明缓冲处理对象，用于接收控制台输入的数据	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		UserVO user = new UserVO();
		String userId = null;
		String name = null;
		String email = null;
		String birth = null;
		String passWord = null;
		String powers = null;
		System.out.println("**********************");
		System.out.println("                   添 加 用 户    \n");
		try {
			//设置注册用户的Id的值
			user.setId(uc.doFindMaxId()+1);
			System.out.println("请输入新用户ID:");
			userId = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(userId)) {
				System.out.println("用户ID不能为空，请重新输入：");
				userId = br.readLine();
			}
			//设置注册用户的userID的值
			user.setUserId(userId);
			System.out.println("请输入新用户的用户名:");
			name = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(name)) {
				System.out.println("用户名不能为空，请重新输入：");
				name = br.readLine();
			}
			//设置注册用户的name的值
			user.setName(name);
			System.out.println("请输入新用户的邮箱:");				
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
			System.out.println("请输入新用户的生日(yyyy-mm-dd):");
			/**
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
			System.out.println("请输入新用户的密码:");
			passWord = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(passWord)) {
				System.out.println("密码不能为空，请重新输入：");
				passWord = br.readLine();
			}
			//设置注册用户的passWord的值
			user.setPassword(passWord);
			System.out.println("请输入新用户的权限（管理员/普通用户）:");
			powers= br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(powers)) {
				System.out.println("权限不能为空，请重新输入：");
				powers = br.readLine();
			}
			//设置注册用户的powers的值
			user.setPowers(powers);
			//设置注册用户的status的值
			user.setStatus("0");
			if (uc.doAddUser(user)) {
				System.out.println("添加成功~");
			} else {
				System.out.println("添加失败~");
			}
		} catch (IOException e) {
			// 将异常封装到自定义异常并抛出
			throw new ServiceException("添加出错！", e);
		}	
	}

	/**
	 * 管理员修改个人信息的页面显示
	 */
	public void updateShow() {
		UserVO user = new UserVO();
		//声明缓冲处理对象，用于接收控制台输入的数据	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		String name = null;
		String email = null;
		String birth = null;
		String passWord = null;
		String powers = null;
		System.out.println("**********************");
		System.out.println("                   修 改 用 户    \n");
		try { 
			System.out.println("请输入要修改用户信息的ID：");
			user.setId(Integer.parseInt(br.readLine()));
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
			/**
			* 调用TypeUtils类的stringToDate方法把字
			* 符串转为日期，并设置修改后用户的birth的值
			*/
			birth = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(birth)) {
				System.out.println("生日不能为空，请重新输入：");
				birth = br.readLine();
			}
			user.setBirth(TypeUtils.stringToDate(birth));				
			System.out.println("请输入要修改的密码:");
			//设置修改后用户的passWord的值
			passWord = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(passWord)) {
				System.out.println("密码不能为空，请重新输入：");
				passWord = br.readLine();
			}
			//设置注册用户的passWord的值
			user.setPassword(passWord);
			System.out.println("请输入要修改的用户权限权限（管理员/普通用户）:");
			powers= br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(powers)) {
				System.out.println("权限不能为空，请重新输入：");
				powers = br.readLine();
			}
			user.setPowers(powers);
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

	public void delShow() {
		//声明缓冲处理对象，用于接收控制台输入的数据	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		System.out.println("**********************");
		System.out.println("                   删 除 用 户    \n");
		System.out.println("请输入要删除用户的ID：");
		try {
			String  id = br.readLine();
			//循环操作，若字符串为空，则提示重新输入
			while (TypeUtils.checkString(id)) {
				System.out.println("ID不能为空，请重新输入：");
				id = br.readLine();
			}
			if (uc.doDeleteUser(id)) {
				System.out.println("删除成功~");
			} else {
				System.out.println("删除失败~");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchShow() {
		//声明缓冲对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		Vector<UserVO> v = new Vector<UserVO>();
		//用户登录和注册界面
		System.out.println("**********************");
		System.out.println("                   查 询 用 户    \n");
		System.out.println("查询全部用户---------1");
		System.out.println("根据id查询用户---------2");
		System.out.println("根据姓名查询用户---------3");
		System.out.println("**********************");
		System.out.println("请您输入:");
		int i = -1;
		//读取用户控制台输入，如果输入值正确，中断循环，否则提示错误信息，再重新输入
		while (true) {
		//读取用户输入操作选项的数字，同时转换为int型
			try {
				i = Integer.parseInt(br.readLine());
				break;
			} catch (Exception e) {
				System.out.println("输入错误，只能输入1~3的数字。");
				System.out.println("请您重新输入:");
			}
		}
		/**
		* 判断用户输入值，如果值为1，进行添加用户操作；如果值为2，
		* 进行删除用户操作；如果值为3，退出系统
		*/			
		switch (i) {
			case 1:
				boolean flag = true;
				int page = 1;
				v = uc.doFindUsers(page);
				this.showRecord(v);
				try {
					while (flag) {
						System.out.println("\n下一页--1\t\t上一页--2\t\t退出--3");
						//读取用户输入操作选项的数字，同时转换为int型
						i = Integer.parseInt(br.readLine());
						switch (i) {
						case 1:
							page = page+1;
							v = uc.doFindUsers(page);
							this.showRecord(v);
							break;
						case 2:
							page = page+1;
							v = uc.doFindUsers(page);
							this.showRecord(v);
							break;
						case 3:
							flag = false;
							break;
						}						
					} 	
				}catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					System.out.println("请输入需要查询的id：");
					String id = br.readLine();
					while (TypeUtils.checkString(id)) {
						System.out.println("ID不能为空，请重新输入：");
						id = br.readLine();
					}
					UserVO user = uc.doFindUserById(id);
					if (user == null) {
						v = null;
					}else {
						v.add(user);
					}					
					this.showRecord(v);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					System.out.println("请输入需要查询的name：");
					String name = br.readLine();
					v = uc.doFindUserByName(name);
					this.showRecord(v);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default://输入1~3之外的数字
				System.out.println("您的输入操作不正确，请重新输入。");
		}
	}
	
	public void showRecord(Vector<UserVO> v) {
		System.out.println("**********************");
		System.out.println("                   结 果 如 下    \n");
		if(v == null){
			System.out.println("没有记录~");
		} else {
			System.out.println("ID\t用户ID\t用户名\t密码\t邮箱\t\t生日\t\t用户类型");
			for(UserVO u : v){
				System.out.println
					(u.getId()+"\t"+u.getUserId()+"\t"+u.getName()+"\t"+u.getPassword()+"\t"+u.getEmail()
						+"\t"+u.getBirth()+"\t"+u.getPowers());
			}
		}
	}
}
