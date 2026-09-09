package com.akranta.tpm.bean;
import com.akranta.tpm.utils.CommonMessage;
public class JHKaizenBean {

	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String actionmode;
	private String disableForm;
	private String oplmpresentImage;
	private String oplmafterImage;
	
	public JHKaizenBean()
	{
		this.formMode = "CREATE";
	}
	public JHKaizenBean(String mode) {
		
		this.setActionmode(mode);
		CommonMessage.debugMsg("bean Mode  :"+mode);
		if(mode.equals("view")){
			this.setDisableForm("true");			
		}
		else if(mode.equals("create")){
			this.setDisableForm("true");			
		}
		else{
			this.setDisableForm("false");
			}			
	}
	
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	public String getFormMode() {
		return formMode;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}
	public String getDisableForm() {
		return disableForm;
	}
	public void setOplmpresentImage(String oplmpresentImage) {
		this.oplmpresentImage = oplmpresentImage;
	}
	public String getOplmpresentImage() {
		return oplmpresentImage;
	}
	public void setOplmafterImage(String oplmafterImage) {
		this.oplmafterImage = oplmafterImage;
	}
	public String getOplmafterImage() {
		return oplmafterImage;
	}
	
}
