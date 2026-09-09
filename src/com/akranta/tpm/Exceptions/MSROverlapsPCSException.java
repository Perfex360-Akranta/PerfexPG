package com.akranta.tpm.Exceptions;

public class MSROverlapsPCSException extends Exception{

	public MSROverlapsPCSException(String msg){
		super(msg);
	}
	public MSROverlapsPCSException(String msg, Throwable cause){
		super(msg,cause);
	}	
}

