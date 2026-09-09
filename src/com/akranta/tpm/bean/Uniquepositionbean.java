package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class Uniquepositionbean {
	 private  String formActionMode;
		private FormModes formMode;
		private String formHeader;
		private String roleelementType;
		
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
		public String getRoleelementType() {
			return roleelementType;
		}
		public void setRoleelementType(String roleelementType) {
			this.roleelementType = roleelementType;
		}
}
