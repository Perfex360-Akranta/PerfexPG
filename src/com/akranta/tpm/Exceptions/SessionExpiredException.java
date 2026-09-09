package com.akranta.tpm.Exceptions;

public class SessionExpiredException extends RuntimeException {

	public SessionExpiredException(String msg) {
        super(msg);
    }
}
