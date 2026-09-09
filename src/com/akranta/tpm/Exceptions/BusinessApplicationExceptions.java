package com.akranta.tpm.Exceptions;

public class BusinessApplicationExceptions extends Exception{

	public BusinessApplicationExceptions(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor with error message and root cause.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public BusinessApplicationExceptions(String msg, Throwable cause) {
		super( msg, cause);
	}
	public BusinessApplicationExceptions(int errorNo, Throwable cause) {
	}
}
