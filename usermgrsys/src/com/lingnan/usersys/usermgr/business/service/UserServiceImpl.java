package com.lingnan.usersys.usermgr.business.service;

import java.sql.Connection;

import com.lingnan.usersys.common.constant.EnumType;
import com.lingnan.usersys.common.dao.DaoFactory;
import com.lingnan.usersys.common.exception.DaoException;
import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.usermgr.business.dao.UserDao;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户service接口类的实现类
 * 
 * @author Beauty
 *
 */
public class UserServiceImpl implements UserService{
    
	/**
	 * 用户service类实例，在类内部创建唯一实例
	 */
	private static UserService UserService = new UserServiceImpl();
	
	/**
	 * 构造方法私有化
	 */
	private UserServiceImpl(){
		
	}
	
	/**
	 * 取得用户service实例
	 * 提供对外访问的方法
	 * @return 实例对象
	 */
	public static UserService getInsance() {
		return UserService;
	}
	
	/**
	 * 用户登录
	 * 
	 * @param userId 用户账号
	 * @param passWord 密码
	 * @return 用户信息
	 */
	public UserVO login(String userId, String passWord) {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		UserVO user = null;		
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的login方法，进行登录操作，结果赋值给登录结果变量
			user = userMgrDsoDao.login(userId, passWord);
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装为自定义异常并抛出
			throw new ServiceException("登录错误！", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return user;
	}
	
	

}
