package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
public class ChartPieOptions {
	
	private Boolean allowPointSelect;
	private String cursor;
	private ChartDataLabel dataLabels;
	
	
	public ChartPieOptions(){
		cursor = "pointer";
		allowPointSelect = true;
	}
	
	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}
	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	public ChartDataLabel getDataLabels() {
		return dataLabels;
	}
	public void setDataLabels(ChartDataLabel dataLabels) {
		this.dataLabels = dataLabels;
	}
	
	

}
