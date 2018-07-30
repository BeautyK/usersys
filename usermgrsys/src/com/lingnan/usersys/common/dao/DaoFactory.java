package com.lingnan.usersys.common.dao;

import java.sql.Connection;

import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.usermgr.business.dao.UserDaoImpl;

/**
 * 获得dao对象的工厂类
 * 
 * @author Beauty
 *
 */
public class DaoFactory {
	/**
	 * 获得用户dao对象的工厂方法
	 * 
	 * @param conn
	 * @param type
	 * @return 实例化的dao对象
	 */
	public static BaseDao getDao(Connection conn, String type) {
		//如果传入的到类型是用户user，就实例化用户dao实现类
		if ("user".equals(type)) {
			return new UserDaoImpl(conn);
		}
		//else if ("order".equals(type)) {
			//return new OrderDaoImpl(conn);
		//}
		//如果没有以上指定类型匹配的值，就提示错误信息
		else {
			throw new ServiceException("dao工厂方法出错！");
		}	
	}
}
