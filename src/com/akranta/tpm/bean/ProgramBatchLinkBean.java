package com.akranta.tpm.bean;

public class ProgramBatchLinkBean {
	
		private String formActionMode;
		private String formMode;
		private String formHeader;
		private String bsdlKeyid;
		private String date;		
		private String duration;
		private String fromTime;
		private String tillTime;
		private String toContinue;
		private String endDate;
		
		public ProgramBatchLinkBean()
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

		public String getDate() {
			return date;
		}
		
		public void setDate(String date) {
			this.date = date;
		}	
		
		public String getDuration() {
			return duration;
		}

		public void setBsdlKeyid(String bsdlKeyid) {
			this.bsdlKeyid = bsdlKeyid;
		}	
		
		public String getBsdlKeyid() {
			return bsdlKeyid;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}	

		public String getFromTime() {
			return fromTime;
		}
		
		public void setFromTime(String fromTime) {
			this.fromTime = fromTime;
		}	

		public String getTillTime() {
			return tillTime;
		}
		
		public void setTillTime(String tillTime) {
			this.tillTime = tillTime;
		}	
		
		public String getEndDate() {
			return endDate;
		}
		
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getToContinue() {
			return toContinue;
		}
		
		public void setToContinue(String toContinue) {
			this.toContinue = toContinue;
		}
}

