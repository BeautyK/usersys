package com.lingnan.usersys.common.exception;

/**
 * 与邮箱有关的异常类
 * @author Beauty
 *
 */
public class EmailException extends ServiceException{
	
	/**
	 * 默认构造方法
	 * 构造一个新的Dao异常，以 null作为其详细信息。
	 */
	public EmailException(){
		
	}
	
	/**
	 * 构造方法
	 * 使用arg0作为信息构造新的运行时异常。
	 * @param arg0 指定的详细消息
	 */
	public EmailException(String arg0){
		
	}
	
	/**
	 * 构造方法
	 * 构造具有指定的原因和 (arg0==null ? null : arg0.toString())
	 * 详细消息（它通常包含的 cause类和详细信息）的新的运行时异常。
	 * @param arg0 指定的原因
	 */
	public EmailException(Throwable arg0){
		super(arg0);
	}
	
	/**
	 * 构造方法
	 * 构造一个新的运行时异常与指定的详细信息（arg0）和原因（arg1）
	 * @param arg0 指定的详细信息
	 * @param arg1 指定的原因
	 */
	public EmailException(String arg0, Throwable arg1){
		super(arg0, arg1);
	}
}
