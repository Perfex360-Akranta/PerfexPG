package com.akranta.tpm.bean;

public class BAL_CostInfoBean {
	
		private String formActionMode;
		private String formMode;
		private String formHeader;
		private String docType;
		private String docNo;
		
		private String mpcsDate;
		private String startDate;
		private String endDate;

		public BAL_CostInfoBean()
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

		public String getDocType() {
			return docType;
		}

		public void setDocType(String docType) {
			this.docType = docType;
		}


		public String getDocNo() {
			return docNo;
		}

		public void setDocNo(String DocNo) {
			this.docNo = DocNo;
		}

		public String getMpcsDate() {
			return mpcsDate;
		}

		public void setMpcsDate(String MpcsDate) {
			this.mpcsDate = MpcsDate;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String StartDate) {
			this.startDate = StartDate;
		}		
		
		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String EndDate) {
			this.endDate = EndDate;
		}		
}
