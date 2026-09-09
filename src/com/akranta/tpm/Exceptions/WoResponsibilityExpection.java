package com.akranta.tpm.Exceptions;

public class WoResponsibilityExpection extends Exception{
	public WoResponsibilityExpection(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor with error message and root cause.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public WoResponsibilityExpection(String msg, Throwable cause) {
		super( msg, cause);
	}
	
	public WoResponsibilityExpection(int errorNo, Throwable cause) {
	}
}
