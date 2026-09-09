package com.akranta.tpm.Exceptions;

public class ServiceObjectCreationException extends Exception{

	public ServiceObjectCreationException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor with error message and root cause.
	 * 
	 * @param msg the error message associated with the exception
	 * @param cause the root cause of the exception
	 */
	public ServiceObjectCreationException(String msg, Throwable cause) {
		super( msg, cause);
	}
	public ServiceObjectCreationException(int errorNo, Throwable cause) {
	}
}
