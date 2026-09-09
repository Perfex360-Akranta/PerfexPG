package com.akranta.tpm.bean;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class CommonParams extends GridParams {

	private String flid;
	private String keyid;
	
	private String dtFromDate;
	private String dtToDate;
	private String dtFromMonth;
	private String dtToMonth;

	public CommonParams() {
		// TODO Auto-generated constructor stub
	}

	public String getDtFromDate() {
		return dtFromDate;
	}

	public void setDtFromDate(String dtFromDate) {
		this.dtFromDate = dtFromDate;
	}
	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}


	public String getDtToDate() {
		return dtToDate;
	}

	public void setDtToDate(String dtToDate) {
		this.dtToDate = dtToDate;
	}

	public String getDtFromMonth() {
		if(CommonFunctions.isValidKeyId(dtFromMonth) && !dtFromMonth.startsWith("01")){
			 dtFromMonth="01-" +dtFromMonth;
		}
		return dtFromMonth;
	}

	public void setDtFromMonth(String dtFromMonth) {
		this.dtFromMonth = dtFromMonth;
	}

	public String getDtToMonth() {

		if(CommonFunctions.isValidKeyId(dtToMonth) && !dtToMonth.startsWith("01")){
			dtToMonth="01-" +dtToMonth;
		}
		return dtToMonth;
	}

	public void setDtToMonth(String dtToMonth) {
		this.dtToMonth = dtToMonth;
	}

	public String getFlid() {
		return flid;
	}

	public void setFlid(String flid) {
		this.flid = flid;
	}

}