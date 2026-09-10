package com.akranta.tpm.bean;

import java.util.List;

public class BAL_KznTlMailIdsBean {
			
		
		
		public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

		public List<BAL_KznTlMailIdsBean> getTeamMembers(){
			return mailids;
		}
		
		public void setTeamMenebers(List<BAL_KznTlMailIdsBean> mailidsList) {
			this.mailids = mailidsList;
		}
		private List<BAL_KznTlMailIdsBean> mailids;
		//private String 
		private String keyid;
		private String empEmail ;	
		private String empName;
		
		
		

	}


