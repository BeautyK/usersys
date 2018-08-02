package com.lingnan.usersys.usermgr.business.service;

import java.sql.Connection;
import java.util.Vector;

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

	/**
	 * 注册或添加用户的方法
	 * 
	 * @param user 用户信息
	 * @return 成功放回true，失败返回false
	 */
	public boolean addUser(UserVO user) {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		boolean flag = false;
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用数据库工具类的beginTransaction方法,开启事务
			DBUtils.beginTransaction(conn);
			//调用dao中的addUser方法，进行注册或添加用户操作，结果赋值给Boolean变量
			flag =  userMgrDsoDao.addUser(user);
			//调用数据库工具类的commit方法,提交事务
			DBUtils.commit(conn);
		//操作过程中出现异常，调用数据库的rollback方法，回滚事务
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			DBUtils.rollbacK(conn);
			//将异常封装为自定义异常并抛出
			throw new ServiceException("注册或添加用户出错！", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 取得数据库用户表ID列的最大值
	 * @return 返回ID的最大值
	 */
	public int findMaxId() {
		Connection conn = null;
		int maxId = 0;
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的findMaxId方法，取得数据库用户表ID列的最大值，结果赋值给变量maxId
			maxId =  userMgrDsoDao.findMaxId();
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装为自定义异常并抛出
			throw new ServiceException("注册或添加用户出错！", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return maxId;
	}

	/**
	 * 修改用户信息的方法
	 * 
	 * @param user 修改后的用户信息
	 * @return 修改成功返回true，失败返回false
	 */
	public boolean updateUser(UserVO user) {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		boolean flag = false;
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用数据库工具类的beginTransaction方法,开启事务
			DBUtils.beginTransaction(conn);
			//调用dao中的addUser方法，进行注册或添加用户操作，结果赋值给Boolean变量
			flag =  userMgrDsoDao.updateUser(user);
			//调用数据库工具类的commit方法,提交事务
			DBUtils.commit(conn);
		//操作过程中出现异常，调用数据库的rollback方法，回滚事务
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			DBUtils.rollbacK(conn);
			//将异常封装为自定义异常并抛出
			throw new ServiceException("修改用户信息出错！", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * 根据id查询用户
	 * @param id 需要查询的id
	 * @return 返回该id用户的信息
	 */
	public UserVO findUserById(String id) {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		UserVO user = null;		
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的findUserById方法，进行按id查找某用户的操作，结果赋值给登录结果变量
			user = userMgrDsoDao.findUserById(id);
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装为自定义异常并抛出
			throw new ServiceException("按ID查找用户出错！", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return user;		
	}
	
	/**
	 * 根据id删除用户信息
	 * @param id 需要删除的用户的id
	 * @return 删除成功返回true，删除失败返回false
	 */
	public boolean deleteUser(String id) {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		boolean flag = false;
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用数据库工具类的beginTransaction方法,开启事务
			DBUtils.beginTransaction(conn);
			//调用dao中的deleteUser方法，进行删除指定id用户的操作，结果赋值给Boolean变量
			flag =  userMgrDsoDao.deleteUser(id);
			//调用数据库工具类的commit方法,提交事务
			DBUtils.commit(conn);
		//操作过程中出现异常，调用数据库的rollback方法，回滚事务
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			DBUtils.rollbacK(conn);
			//将异常封装为自定义异常并抛出
			throw new ServiceException("删除用户出错！", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;		
	}

	/**
	 * 查询所有用户
	 * @return 结果集
	 */
	public Vector<UserVO> findAll() {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的findAll方法，进行查询全部用户的操作，结果赋值给结果集变量
			v =  userMgrDsoDao.findAll();
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装为自定义异常并抛出
			throw new ServiceException("查询全部用户出错", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return v;
	}

	/**
	 * 根据名字查询用户
	 * @param name 需要根据name查找的关键字符串
	 * @return 返回名字包含该字符串的用户信息
	 */
	public Vector<UserVO> findUserByName(String name) {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的findUserByName方法，进行根据name查询用户信息的操作，结果赋值给结果集变量
			v =  userMgrDsoDao.findUserByName(name);
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装为自定义异常并抛出
			throw new ServiceException("根据name查询用户出错", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return v;
	}

	/**
	 * 分页查询全部用户
	 * @return 返回指定页的用户信息
	 */
	public Vector<UserVO> findUsers(int page) {
		//声明数据库对象，并用于保存数据库连接对象
		Connection conn = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDsoDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao中的findAll方法，进行查询指定页用户的全部用户信息的操作，结果赋值给结果集变量
			v =  userMgrDsoDao.findUsers(page);
		} catch (DaoException e) {
			//将自定义异常抛出
			throw e;
		} catch (Exception e) {
			//将异常封装为自定义异常并抛出
			throw new ServiceException("分页查询全部用户出错", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return v;
	}
}
