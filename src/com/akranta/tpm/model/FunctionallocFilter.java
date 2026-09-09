package com.akranta.tpm.model;

/**
 * @author Administrator
 *
 */
public class FunctionallocFilter {

	private String flid;
	private String functionallocName;
	private String elementId;
	private String elementType;
	
	
	public FunctionallocFilter(String flid,String functionallocName,String elementId,String elementType){
		this.flid = flid;
		this.functionallocName = functionallocName;
		this.elementId = elementId;
		this.elementType = elementType;
	}
	
	public String getFlid() {
		return flid;
	}
	public void setFlid(String flid) {
		this.flid = flid;
	}
	public String getFunctionallocName() {
		return functionallocName;
	}
	public void setFunctionallocName(String functionallocName) {
		this.functionallocName = functionallocName;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	
	
}
