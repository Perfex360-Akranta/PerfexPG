package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
public class ChartTitle {
	
	private String align;
	private boolean floating;
	private String text;
	private Integer margin;
	private Integer x;
	private Integer y;
	
	public ChartTitle(){
		this.floating = true;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public boolean isFloating() {
		return floating;
	}
	public void setFloating(boolean floating) {
		this.floating = floating;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getMargin() {
		return margin;
	}
	public void setMargin(Integer margin) {
		this.margin = margin;
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

}
