package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class UpstreamDefect {
	
		private String formActionMode;
		private FormModes formMode;
		private String aclFromTime;
		private String aclToTime;
		private boolean disableForm;
		
		public void setFormActionMode(String formActionMode) {
			this.formActionMode = formActionMode;
		}
		public String getFormActionMode() {
			return formActionMode;
		}
		public void setFormMode(FormModes formMode) {
			this.formMode = formMode;
		}
		public FormModes getFormMode() {
			return formMode;
		}
		public void setAclFromTime(String aclFromTime) {
			this.aclFromTime = aclFromTime;
		}
		public String getAclFromTime() {
			return aclFromTime;
		}
		public void setAclToTime(String aclToTime) {
			this.aclToTime = aclToTime;
		}
		public String getAclToTime() {
			return aclToTime;
		}
		public void setDisableForm(boolean disableForm) {
			this.disableForm = disableForm;
		}
		public boolean getDisableForm() {
			return disableForm;
		}
        
	    

}
