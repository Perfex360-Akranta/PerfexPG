package com.akranta.tpm.bean;

public class UtilityFormBean {
	
		private String formActionMode;
		private String formMode;
		private String formHeader;
		private String formType;

		public UtilityFormBean()
		{
			this.formMode = "CREATE";
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
		
		public String getFormType() {
			return formType;
		}

		public void setFormType(String formType) {
			this.formType = formType;
		}		
	}
