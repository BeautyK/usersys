package com.lingnan.usersys.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lingnan.usersys.common.exception.DateException;
import com.lingnan.usersys.common.exception.EmailException;
import com.lingnan.usersys.common.exception.ServiceException;



/**
 * 类型转换类
 * 
 * @author Beauty
 *
 */
public class TypeUtils {
	
	/**
	 * 字符串转日期方法
	 * 
	 * @param str 需要转换的字符串
	 * @return date 转换后的结果
	 */
	public static java.sql.Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.sql.Date sqldate = null;
		try {
			//把字符串转换为util的date类型
			date = sdf.parse(str);
			//把util的date类型转换为SQL的date类型
			sqldate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			//System.out.println("字符串转日期方法出错！");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DateException("字符串转日期方法出错！", e);
		}
		return sqldate;
	}
	
	/**
	 * 日期转字符串方法
	 * 
	 * @param date 需要转换的日期
	 * @return str 返回转换后的结果
	 */
	public static String dateToString(Date date) {
		
		String str = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			str = sdf.format(date);
		} catch (Exception e) {
			//System.out.println("日期转字符串方法出错！");
			//e.printStackTrace();
			//将异常封装为自定义异常类
			throw new DateException("日期转字符串方法出错！", e);
		}	
		return str;
	}
	
	/**
	 * 检查邮箱格式是否正确的方法
	 * 
	 * @param email 需要检查的邮箱地址
	 * @return 格式正确返回true，格式错误返回false
	 */
	public static boolean checkEmailFormat(String email) {
		try {
			String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
			//正则表达式的模式
	        Pattern p = Pattern.compile(RULE_EMAIL);
	        //正则表达式的匹配器
	        Matcher m = p.matcher(email);
	        //进行正则匹配
	        return m.matches();
		} catch (Exception e) {
			//将异常封装为自定义异常类
			throw new EmailException("邮箱格式出错！", e);
		}
		
	}
	
	/**
	 * 判断字符串是否为空的方法
	 * 
	 * @param str 需要判断的字符串
	 * @return 空返回true，非空返回false
	 */
	public static boolean checkString(String str) {
		if (str=="null" || str.equals("")) {
			return true;
		}
		return false;
	}
	
	public static String passwdToMD5(String str) {
		byte[] secretBytes = null;
		String md5code = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
            md5code = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
        } catch (NoSuchAlgorithmException e) {
        	//将异常封装为自定义异常类
            throw new ServiceException("没有这个md5算法！", e);
        }     
        return md5code;
    }
	
}
