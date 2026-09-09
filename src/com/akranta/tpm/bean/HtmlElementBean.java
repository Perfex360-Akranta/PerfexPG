
package com.akranta.tpm.bean;
public class HtmlElementBean {
	
	private String name;
	private String id;
	private String value;
	private boolean mandatory;
	private boolean disable;
	private String caption;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public boolean isDisable() {
		return disable;
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getCaption() {
		return caption;
	}

}
