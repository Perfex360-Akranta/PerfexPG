package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
public class ChartLegend {
	
	private String layout;
	private String backgroundColor;
	private String align;
	private String verticalAlign;
	private int x;
	private int y;
	private boolean floating;
	private boolean shadow;
	private ChartStyle itemStyle;
	
	public ChartLegend(){
		this.setLayout("vertical");
		this.setX(0);
		this.setY(70);
		this.setFloating(true);
		this.setShadow(true);
		this.setAlign("right");
		this.setVerticalAlign("top");
		this.setBackgroundColor("#FFFFFF");
	}
	
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getVerticalAlign() {
		return verticalAlign;
	}
	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isFloating() {
		return floating;
	}
	public void setFloating(boolean floating) {
		this.floating = floating;
	}
	public boolean isShadow() {
		return shadow;
	}
	public void setShadow(boolean shadow) {
		this.shadow = shadow;
	}

	public void setItemStyle(ChartStyle itemStyle) {
		this.itemStyle = itemStyle;
	}

	public ChartStyle getItemStyle() {
		return itemStyle;
	}	

	
}
