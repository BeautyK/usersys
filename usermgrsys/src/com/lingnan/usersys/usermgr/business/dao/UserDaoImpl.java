package com.lingnan.usersys.usermgr.business.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;






import java.sql.Statement;

import com.lingnan.usersys.common.exception.DaoException;
import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.usermgr.domain.UserVO;

public class UserDaoImpl implements UserDao{

	/**
	 * 数据库连接对象
	 */
	private Connection conn;
	
	/**
	 * 构造方法
	 * 
	 * @param conn 数据库连接
	 */
	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	
	/**
	 * 根据用户账号、用户密码登录系统的方法
	 * 
	 * @param userId 用户账号
	 * @param passWord 用户密码
	 * @return 登录成功返回该用户的userVo对象，否则返回null
	 */
	public UserVO login(String userId, String passWord) {
		PreparedStatement prep = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		try {
			String sql = "select * from t_users where userID = ? and password = ? ";
			prep = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			prep.setString(1, userId);
			prep.setString(2, passWord);
			rs = prep.executeQuery();
			if(rs.first()){
				user.setId(Integer.parseInt(rs.getString(1)));
				user.setUserId(rs.getString(2));
				user.setName(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setPowers(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setBirth(rs.getDate(7));
				user.setStatus(rs.getString(8));
			}else {
				throw new ServiceException("登录失败，用户ID或密码错误");
			}		
		} catch (SQLException e) {
			throw new DaoException("sql语句出错", e);
		}finally{
			DBUtils.closeStatement(rs, prep);
		}
		return user;
	}
	
	public boolean addUser(UserVO user){
		Boolean flag = false;
		int count = 0;
		PreparedStatement prep = null;
		try {
			String sql = "insert into t_users values(?, ?, ?, ?, ?, ?, ?, ?)";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, user.getId());
			prep.setString(2, user.getUserId());
			prep.setString(3, user.getName());
			prep.setString(4, user.getPassword());
			prep.setString(5, user.getPowers());
			prep.setString(6, user.getEmail());
			prep.setDate(7, (Date) user.getBirth());
			prep.setString(8, user.getStatus());
			count = prep.executeUpdate();
			if (count > 0) {
				flag = true ;
			}
		} catch (SQLException e) {
			throw new DaoException("SQL语句出错!", e);
		} finally {
			DBUtils.closeStatement(prep);
		}	
		return flag;
	}


	/**
	 * 取得数据库用户表ID列的最大值
	 * @return 返回ID的最大值
	 */
	public int findMaxId() {
		Statement stmt = null;
		ResultSet rs = null;
		int maxId = 0;
		String sql = "select max(id) from t_users";
		try {			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			maxId = rs.getInt(1);
		} catch (SQLException e) {
			throw new DaoException("sql语句出错！", e);
		}	
		return maxId;
	}


	@Override
	public UserVO findUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean updateUser(UserVO user) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
