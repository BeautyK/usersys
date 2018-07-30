package com.lingnan.usersys.usermgr.view;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class NormalFrameTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		IndexFrame f = new IndexFrame();
		f.show();
	}

}
