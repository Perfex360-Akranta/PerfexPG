package com.akranta.tpm.bean;
import com.akranta.tpm.utils.FormModes;
public class GenTlConditionalappraisalBean {
	private String formActionMode;
		private FormModes formMode;
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
}
