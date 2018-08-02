package com.lingnan.usersys.usermgr.business.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;






import java.sql.Statement;
import java.util.Vector;

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
			throw new DaoException("sql语句出错:"+e.getMessage(), e);
		}finally{
			DBUtils.closeStatement(rs, prep);
		}
		return user;
	}
	
	/**
	 * 注册或添加用户的方法
	 * 
	 * @param user 用户信息
	 * @return 成功放回true，失败返回false
	 */
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
			throw new DaoException("SQL语句出错:"+e.getMessage(), e);
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
			throw new DaoException("sql语句出错:"+e.getMessage(), e);
		} finally{
			DBUtils.closeStatement(rs, stmt);
		}	
		return maxId;
	}

	/**
	 * 根据id查询用户
	 * @param id
	 * @return 返回该id用户的信息
	 */
	public UserVO findUserById(String id) {
		PreparedStatement prep = null;
		ResultSet rs = null;
		UserVO user = new UserVO();
		try {
			String sql = "select * from t_users where ID = ?";
			prep = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			prep.setString(1, id);
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
				user = null;
				//throw new ServiceException("没有此ID的用户！");
			}		
		} catch (SQLException e) {
			throw new DaoException("sql语句出错"+e.getMessage(), e);
		}finally{
			DBUtils.closeStatement(rs, prep);
		}
		return user;
	}


	/**
	 * 修改用户信息的方法
	 * 
	 * @param user 修改后的用户信息
	 * @return 修改成功返回true，失败返回false
	 */
	public boolean updateUser(UserVO user) {
		Boolean flag = false;
		int count = 0;
		PreparedStatement prep = null;
		try {
			String sql = "update t_users set name=?,mail=?,birth=?,password=?,powers=? where id=?";
			prep = conn.prepareStatement(sql);			
			prep.setString(1, user.getName());
			prep.setString(2, user.getEmail());
			prep.setDate(3, (Date) user.getBirth());
			prep.setString(4, user.getPassword());
			prep.setString(5, user.getPowers());
			prep.setInt(6, user.getId());
			count = prep.executeUpdate();
			if (count > 0) {
				flag = true ;
			}
		} catch (SQLException e) {
			throw new DaoException("SQL语句出错:"+e.getMessage(), e);
		} finally {
			DBUtils.closeStatement(prep);
		}	
		return flag;
	}


	/**
	 * 根据id删除用户信息
	 * @param id 需要删除的用户的id
	 * @return 删除成功返回true，删除失败返回false
	 */
	public boolean deleteUser(String id) {
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		String sql = "delete from t_users where id='"+id+"'";
		try {
			stmt = conn.createStatement();
			if (stmt.executeUpdate(sql) > 0) {
				flag = true;
			} 			
		} catch (SQLException e) {
			throw new DaoException("SQL语句出错:"+e.getMessage(), e);
		} finally {
			DBUtils.closeStatement(stmt);
		}
		return flag;
	}

	/**
	 * 查询所有用户
	 * @return 结果集
	 */
	public Vector<UserVO> findAll() {
		Statement stmt = null;
		ResultSet rs = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			String sql = "select * from t_users";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setId(Integer.parseInt(rs.getString(1)));
				user.setUserId(rs.getString(2));
				user.setName(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setPowers(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setBirth(rs.getDate(7));
				user.setStatus(rs.getString(8));
				v.add(user);
			}	
		} catch (SQLException e) {
			throw new DaoException("sql语句出错"+e.getMessage(), e);
		}finally{
			DBUtils.closeStatement(rs, stmt);
		}
		return v;
	}

	/**
	 * 根据名字查询用户
	 * @param name 需要根据name查找的关键字符串
	 * @return 返回名字包含该字符串的用户信息
	 */
	public Vector<UserVO> findUserByName(String name) {
		Statement stmt = null;
		ResultSet rs = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			String sql = "select * from t_users where name like '%"+name+"%'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setId(Integer.parseInt(rs.getString(1)));
				user.setUserId(rs.getString(2));
				user.setName(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setPowers(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setBirth(rs.getDate(7));
				user.setStatus(rs.getString(8));
				v.add(user);
			}	
		} catch (SQLException e) {
			throw new DaoException("sql语句出错"+e.getMessage(), e);
		}finally{
			DBUtils.closeStatement(rs, stmt);
		}
		return v;
	}


	/**
	 * 分页查询全部用户
	 * @return 返回指定页的用户信息
	 */
	public Vector<UserVO> findUsers(int page) {
		Statement stmt = null;
		ResultSet rs = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			String sql = "select * from (select rownum rn,A.* from "
					+ "(select *  from t_users order by id)A "
					+ ")B where B.rn>("+page+"-1)*5 and B.rn<= "+page+"*5";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setId(Integer.parseInt(rs.getString(2)));
				user.setUserId(rs.getString(3));
				user.setName(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setPowers(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setBirth(rs.getDate(8));
				user.setStatus(rs.getString(9));
				v.add(user);
			}
			if (v.size() == 0) {
				v=null;
			}
		} catch (SQLException e) {
			throw new DaoException("sql语句出错"+e.getMessage(), e);
		}finally{
			DBUtils.closeStatement(rs, stmt);
		}
		return v;
	}
	
}
