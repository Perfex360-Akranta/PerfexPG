package com.akranta.tpm.bean;

public class DeptFuncLinkBean {
	
		private String formActionMode;
		private String formMode;
		private String formHeader;
		private String erdlKeyid;		
		private String erslKeyid;
		private String ersrKeyid;
		
		public DeptFuncLinkBean()
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

		public String getErdlKeyid() {
			return erdlKeyid;
		}
		
		public void setErdlKeyid(String erdlKeyid) {
			this.erdlKeyid = erdlKeyid;
		}	
		
		public String getErslKeyid() {
			return erslKeyid;
		}
		
		public void setErslKeyid(String erslKeyid) {
			this.erslKeyid = erslKeyid;
		}	

		public String getErsrKeyid() {
			return ersrKeyid;
		}
		
		public void setErsrKeyid(String ersrKeyid) {
			this.ersrKeyid = ersrKeyid;
		}	

}
