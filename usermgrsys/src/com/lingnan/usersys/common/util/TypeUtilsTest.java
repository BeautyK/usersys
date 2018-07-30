package com.lingnan.usersys.common.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Test;

public class TypeUtilsTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testStringToDate() {
		System.out.println("开始测试字符串转日期的方法");
		String str = "2017-09-08";
		Date date = TypeUtils.stringToDate(str);
		System.out.println(date);
		System.out.println("测试结束\n");
	}

	@Test
	public void testDateToString() {
		System.out.println("开始测试日期转字符串的方法");
		Date date = new Date();
		String str = TypeUtils.dateToString(date);
		System.out.println(str);
		System.out.println("测试结束\n");
	}

	@Test
	public void testCheckEmailFormat() {
		System.out.println("开始测试检查邮箱格式的方法");
		String str = "123@qq.com";
		if (TypeUtils.checkEmailFormat(str)) {
			System.out.println("邮箱格式正确");
		}else {
			System.out.println("邮箱格式错误");
		}
		System.out.println("测试结束\n");
	}

	@Test
	public void testCheckString() {
		System.out.println("开始测试判断是否为空的方法");
		String str = "  ";
		if (TypeUtils.checkString(str)) {
			System.out.println("字符串为空");
		}else {
			System.out.println("字符串不为空");
		}
		System.out.println("测试结束\n");
	}
	
	@Test
	public void testPasswdToMD5() {
		System.out.println("开始测试MD5密码加密的方法");
		String str1 = "12345";
		System.out.println("转码得："+TypeUtils.passwdToMD5(str1));
		System.out.println("测试结束\n");
	}

}
