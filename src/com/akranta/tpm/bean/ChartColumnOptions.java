package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
public class ChartColumnOptions {
	
	private Float pointPadding;
	private Float borderWidth;
	
	public ChartColumnOptions(){
		this.pointPadding = 0.2F;
		this.borderWidth = 0F;
	}
	
	public Float getPointPadding() {
		return pointPadding;
	}
	public void setPointPadding(Float pointPadding) {
		this.pointPadding = pointPadding;
	}
	public Float getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(Float borderWidth) {
		this.borderWidth = borderWidth;
	}
	

}
