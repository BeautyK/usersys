package com.lingnan.usersys.usermgr.business.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Test;

import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.domain.UserVO;

public class UserDaoImplTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


//	@Test
//	public void testLogin() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl udi = new UserDaoImpl(conn);
//		UserVO user = udi.login("u02", "aaa");
//		System.out.println(user.getPowers()+"\t"+user.getName());
//	}
	
//	@Test
//	public void testAddUser() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl udi = new UserDaoImpl(conn);
//		UserVO user = new UserVO();
//		user.setId(4);
//		user.setUserId("u04");
//		user.setName("ccc");
//		user.setPassword("ccc");
//		user.setPowers("普通用户");
//		user.setEmail("ccc@163.com");
//		user.setBirth(TypeUtils.stringToDate("1994-07-09"));
//		user.setStatus("0");
//		if (udi.addUser(user)) {
//			System.out.println("注册成功");
//		} else {
//			System.out.println("注册失败");
//		}
//	}
	
//	@Test
//	public void testFindMaxId() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl udi = new UserDaoImpl(conn);
//		System.out.println(udi.findMaxId());
//	}

}
