package com.akranta.tpm.scheduler;

public class SchedulerConfigMst {

	private String reportName;
	private String title;
	private String subject;
	private String type;
	private String dbFucntionName;
	private String frequency;
	private int    xlRptStartColIndx;
	private int groupBy1ColIndx;
	private int groupBy2ColIndx;
	private int toMailColIndx;
	private int ccMailColIndx;
	private int empNameColIndx;
	private int numberOfHeader;
	private int xlRptStartRowIndx;
	
	
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDbFucntionName() {
		return dbFucntionName;
	}
	public void setDbFucntionName(String dbFucntionName) {
		this.dbFucntionName = dbFucntionName;
	}
	
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public int getXlRptStartColIndx() {
		return xlRptStartColIndx;
	}
	public void setXlRptStartColIndx(int xlRptStartColIndx) {
		this.xlRptStartColIndx = xlRptStartColIndx;
	}
	public int getGroupBy1ColIndx() {
		return groupBy1ColIndx;
	}
	public void setGroupBy1ColIndx(int groupBy1ColIndx) {
		this.groupBy1ColIndx = groupBy1ColIndx;
	}
	public int getGroupBy2ColIndx() {
		return groupBy2ColIndx;
	}
	public void setGroupBy2ColIndx(int groupBy2ColIndx) {
		this.groupBy2ColIndx = groupBy2ColIndx;
	}
	public int getToMailColIndx() {
		return toMailColIndx;
	}
	public void setToMailColIndx(int toMailColIndx) {
		this.toMailColIndx = toMailColIndx;
	}
	public int getCcMailColIndx() {
		return ccMailColIndx;
	}
	public void setCcMailColIndx(int ccMailColIndx) {
		this.ccMailColIndx = ccMailColIndx;
	}
	public int getEmpNameColIndx() {
		return empNameColIndx;
	}
	public void setEmpNameColIndx(int empNameColIndx) {
		this.empNameColIndx = empNameColIndx;
	}

	public int getNumberOfHeader() {
		return numberOfHeader;
	}
	public void setNumberOfHeader(int numberOfHeader) {
		this.numberOfHeader = numberOfHeader;
	}
	public int getXlRptStartRowIndx() {
		return xlRptStartRowIndx;
	}
	public void setXlRptStartRowIndx(int xlRptStartRowIndx) {
		this.xlRptStartRowIndx = xlRptStartRowIndx;
	}
}
