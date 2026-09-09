package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class StdWorSheetkBean {
	
        private  String formActionMode;
		private FormModes formMode;
		private String formHeader;
		
		public void setFormActionMode(String formActionMode) {
			this.formActionMode = formActionMode;
		}
		public  String getFormActionMode() {
			return formActionMode;
		}
		public void setFormMode( FormModes formMode) {
			this.formMode = formMode;
		}
		public FormModes getFormMode() {
			return formMode;
		}
		public void setFormHeader(String formHeader) {
			this.formHeader = formHeader;
		}
		public String getFormHeader() {
			return formHeader;
		}

}
