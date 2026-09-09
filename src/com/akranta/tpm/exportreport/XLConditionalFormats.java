package com.akranta.tpm.exportreport;


public class XLConditionalFormats {
		
	
	private Integer fromCol; //excel Column Index where format has to apply
	private Integer toCol; //excel Column Index where format has to apply, -1 checks all columns
	private String fontName;
	private ComparisonOperator operator;
	private String condValue; // value  to compare for formatting
	private String condFormulaStr;
	private RGB fontColor;
	private RGB bgColor;
	private Short fontHeightPoint;
	private Short fontBoldWeight;
	private String symbolStr;
	private String identfier; //should be unique in the List<>
	private String groupByLabel;
	private String imgPathName;
	private Integer dbChkColIndx; //format an excel column based on another column in the resultset.
								 //use if need to apply format for a column(fromCol , toCol) based on another column value,
								 //otherwise null 
								// Make sure the value is bounded in resultset. ie dbChkColIndx < rs.maxcol 
	
	public static final  String FONT_DEFAULT ="Arial";
	public static final  String FONT_WINGDINGS ="Wingdings";
	public static final  String FONT_WINGDINGS_2 ="Wingdings 2";
	public static final  String FONT_WEBDINGS ="Webdings";
	public static final  String FONT_ALGERIAN ="Algerian";
	public static final  String FONT_COMICSANSMS ="Comic Sans MS";
	public static final  String FONT_ARIALROUNDEDMTBOLD ="Arial Rounded MT Bold";
	public static final  String FONT_AGENCYFB ="Agency FB";	
	public static final char SYMBOL_TICK =  (char)252;
	public static final char SYMBOL_CROSS =  (char)251;
	public static final char SYMBOL_STAR = (char)233;
	public static final short FONT_BOLD =700;
	public static final String FONT_HIFIEN = "-";
	
	public Short getFontBoldWeight() {
		return fontBoldWeight;
	}
	public void setFontBoldWeight(Short fontBoldWeight) {
		this.fontBoldWeight = fontBoldWeight;
	}
	public Short getFontHeightPoint() {
		return fontHeightPoint;
	}
	public void setFontHeightPoint(Short fontHeightPoint) {
		this.fontHeightPoint= fontHeightPoint;
	}
	public Integer getFromCol() {
		return fromCol;
	}
	public void setFromCol(Integer fromCol) {
		this.fromCol = fromCol;
	}
	public Integer getToCol() {
		return toCol;
	}
	public void setToCol(Integer toCol) {
		this.toCol = toCol;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	public ComparisonOperator getOperator() {
		return operator;
	}
	public void setOperator(ComparisonOperator operator) {
		this.operator = operator;
	}
	public String getCondValue() {
		return condValue;
	}
	public void setCondValue(String condValue) {
		this.condValue = condValue;
	}
	public String getCondFormulaStr() {
		return condFormulaStr;
	}
	public void setCondFormulaStr(String condFormulaStr) {
		this.condFormulaStr = condFormulaStr;
	}
	public RGB getFontColor() {
		return fontColor;
	}
	public void setFontColor(RGB fontColor) {
		this.fontColor = fontColor;
	}
	public RGB getBgColor() {
		return bgColor;
	}
	public void setBgColor(RGB bgColor) {
		this.bgColor = bgColor;
	}
	public void setSymbolStr(String symbolStr) {
		this.symbolStr = symbolStr;
	}
	public String getSymbolStr() {
		return symbolStr;
	}
	public void setIdentfier(String identfier) {
		this.identfier = identfier;
	}
	public String getIdentfier() {
		return identfier;
	}
	public void setGroupByLabel(String groupByLabel) {
		this.groupByLabel = groupByLabel;
	}
	public String getGroupByLabel() {
		return groupByLabel;
	}
	public void setDbChkColIndx(Integer dbChkColIndx) {
		this.dbChkColIndx = dbChkColIndx;
	}
	public Integer getDbChkColIndx() {
		return dbChkColIndx;
	}
	public void setImgPathName(String imgPathName) {
		this.imgPathName = imgPathName;
	}
	public String getImgPathName() {
		return imgPathName;
	}
	
	
}
