package com.akranta.tpm.bean;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.CommonMessage;

public class DocMgrBean {
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String View;
	private String Modify;
	private String Delete;
	private String Download;
	private String Userrights;
	private FormModes formModes;
	private boolean disableForm ;
	private boolean disableFolder;
	private boolean disableFile;
	
	public DocMgrBean()
	{
		this.formMode = "CREATE";
	}
	public DocMgrBean(FormModes mode,String addMode)
	{
		formModeSettings(mode,addMode);
	}
	
	private void formModeSettings(FormModes mode,String addMode){
		this.formModes = mode;
		CommonMessage.debugMsg("Bean : "+mode);
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			if(addMode.equals("Folder"))
			{
				this.disableFile = true;
			}
			else if(addMode.equals("File"))
			{
				this.disableFolder = true;
			}
		}
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormMode() {
		return formMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public FormModes getFormModes() {
		return formModes;
	}
	public void setFormModes(FormModes formModes) {
		this.formModes = formModes;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableFolder() {
		return disableFolder;
	}
	public void setDisableFolder(boolean disableFolder) {
		this.disableFolder = disableFolder;
	}
	public boolean isDisableFile() {
		return disableFile;
	}
	public void setDisableFile(boolean disableFile) {
		this.disableFile = disableFile;
	}
	public String getView() {
		return View;
	}
	public void setView(String view) {
		View = view;
	}
	public String getModify() {
		return Modify;
	}
	public void setModify(String modify) {
		Modify = modify;
	}
	public String getDelete() {
		return Delete;
	}
	public void setDelete(String delete) {
		Delete = delete;
	}
	public String getDownload() {
		return Download;
	}
	public void setDownload(String download) {
		Download = download;
	}
	public String getUserrights() {
		return Userrights;
	}
	public void setUserrights(String userrights) {
		Userrights = userrights;
	}
}
