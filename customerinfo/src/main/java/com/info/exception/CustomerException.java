/**
 * 
 */
package com.info.exception;

/**
 * @author 387090
 *
 */
public class CustomerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomerException() {
		super();
	}
	
	public CustomerException(String msg) {
		super(msg);
	}
	
	public CustomerException(Throwable thr) {
		super(thr);
	}
	
	public CustomerException(String msg,Throwable thr) {
		super(msg, thr);
	}
}
