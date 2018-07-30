package com.lingnan.usersys.common.util;


import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class DBUtilsTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetConnection() {
		Connection conn = DBUtils.getConnection();
		System.out.println(conn);
	}

}
