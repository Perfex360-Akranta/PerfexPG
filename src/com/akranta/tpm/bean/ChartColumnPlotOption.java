package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
public class ChartColumnPlotOption {
	private ChartColumnOptions column;

	public ChartColumnPlotOption(){
		this.column = new  ChartColumnOptions();
	}
	public void setColumn(ChartColumnOptions column) {
		this.column = column;
	}

	public ChartColumnOptions getColumn() {
		return column;
	}
	
}
