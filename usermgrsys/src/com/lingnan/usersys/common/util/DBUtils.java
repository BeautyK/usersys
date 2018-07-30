package com.lingnan.usersys.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lingnan.usersys.common.exception.DaoException;

/**
 * 数据库工具类
 * @author Beauty
 *
 */
public class DBUtils {
	
	/**
	 * 获取数据库连接
	 * 
	 * @return Connection 返回获取到的数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;		
		try {
			String user = "scott";//用户名
			String password = "root";//密码
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:orcl",user,password);
		} catch (ClassNotFoundException e) {
			//System.out.println("驱动加载失败！");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("驱动加载失败！", e);
		} 
		catch (SQLException e) {
			//System.out.println("数据库连接失败！");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("数据库连接失败！", e);
		}		
		return conn;
	}
	
	/**
	 * 关闭数据库连接的方法
	 * 
	 * @param conn 需要关闭的数据库连接
	 */
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			//System.out.println("连接关闭失败！");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("连接关闭失败！", e);
		}		
	}
	
	/**
	 * 开启事务的方法
	 * 
	 * @param conn 
	 */
	public static void beginTransaction(Connection conn) {
		try {
			//将事务的自动提交模式设为假
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			//System.out.println("事务开启失败");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("事务开启失败！", e);
		}
	}
	
	/**
	 * 提交事务的方法
	 * 
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			//提交事务
			conn.commit();
			//将事务的自动提交设为真
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			//System.out.println("事务提交失败！");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("事务提交失败！", e);
		}
	}
	
	/**
	 * 回滚事务的方法
	 * 
	 * @param conn
	 */
	public static void rollbacl(Connection conn) {
		try {
			//回滚事务
			conn.rollback();
			//将事务的自动提交设为真
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			//System.out.println("事务回滚失败！");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("事务回滚失败！", e);
		}
	}
	
	/**
	 * 关闭声明对象的方法
	 * 
	 * @param rs
	 * @param stmt
	 */
	public static void closeStatement(ResultSet rs, Statement stmt) {	
	    try {
	    	//如果查询结果集对象不为空，关闭该对象
	        if (rs != null) {
		        rs.close();
			}
	        //如果声明对象不为空，关闭该对象
	        if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			//System.out.println("声明对象关闭失败！");
		    //e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("声明对象关闭失败！", e);
		}		
	}
	
	/**
	 * 关闭声明对象的方法
	 * 
	 * @param stmt
	 */
	public static void closeStatement(Statement stmt) {	
	    try {
	        //如果声明对象不为空，关闭该对象
	        if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			//System.out.println("声明对象关闭失败！");
		    //e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DaoException("声明对象关闭失败！", e);
		}		
	}
}
