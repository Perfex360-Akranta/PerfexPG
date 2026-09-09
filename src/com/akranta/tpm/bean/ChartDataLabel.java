package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
public class ChartDataLabel {
	
	private String align;
	private Integer x;
	private Integer y;
	private Boolean enabled;
	private Integer rotation;
	private ChartStyle style;
//	private String color;
//	private String connectorColor;
	
	public ChartDataLabel(){
		style = new ChartStyle();
	}
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getRotation() {
		return rotation;
	}
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}
	public void setStyle(ChartStyle style) {
		this.style = style;
	}
	public ChartStyle getStyle() {
		return style;
	}

	
}
