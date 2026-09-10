package com.akranta.tpm.bean;

public class BAL_PhenCauseBean {
	
		private String formActionMode;
		private String formMode;
		private String formHeader;
		private String formType;

		public BAL_PhenCauseBean()
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
		
		public String getCombinedId() {
			return formType;
		}

		public void setCombinedId(String formType) {
			this.formType = formType;
		}		


}
