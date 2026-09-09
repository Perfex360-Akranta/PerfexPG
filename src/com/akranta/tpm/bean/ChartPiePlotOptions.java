package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
public class ChartPiePlotOptions {
	private ChartPieOptions pie ;
	
	public ChartPiePlotOptions(){
		pie = new ChartPieOptions();
	}
	public void setPie(ChartPieOptions plotOptions) {
		this.pie = plotOptions;
	}

	public ChartPieOptions getPie() {
		return pie;
	}
}
