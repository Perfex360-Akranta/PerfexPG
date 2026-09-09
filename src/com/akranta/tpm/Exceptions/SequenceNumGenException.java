package com.akranta.tpm.Exceptions;

public class SequenceNumGenException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SequenceNumGenException(){
		super();
	}
	public SequenceNumGenException(String msg){
		super(msg);
	}
	public SequenceNumGenException(String tableName, Throwable cause){
		super(tableName, cause);
	}

}
