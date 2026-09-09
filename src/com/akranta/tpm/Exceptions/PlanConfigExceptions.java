package com.akranta.tpm.Exceptions;

public class PlanConfigExceptions extends Exception{
	public PlanConfigExceptions(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor with error message and root cause.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public PlanConfigExceptions(String msg, Throwable cause) {
		super( msg, cause);
	}
	
	public PlanConfigExceptions(int errorNo, Throwable cause) {
	}

}
