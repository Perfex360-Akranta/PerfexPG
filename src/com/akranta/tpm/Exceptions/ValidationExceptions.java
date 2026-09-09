package com.akranta.tpm.Exceptions;

public class ValidationExceptions extends Exception{

	/**
	 * 
	 * 
	 */
	private Object object = null;
	private static final long serialVersionUID = -7993236087487349446L;
	
	public ValidationExceptions(Object object)
	{
		this.object = object;
	}
	public ValidationExceptions(String msg,Object object){
		super(msg);
		this.setObject(object);
	}
	
	public ValidationExceptions(String msg)
	{
		super(msg);
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}
	
}
