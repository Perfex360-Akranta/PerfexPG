package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.json.JSONObject;

import com.akranta.tpm.controller.UIUtils;

public class ChartPie extends ChartOptionBean {
	private ChartPiePlotOptions plotOptions;

	
	public ChartPie(){
		plotOptions = new ChartPiePlotOptions();
		this.getTooltip().setX("this.point.name");
		this.getTooltip().setY("this.percentage");
		
	}
	public void setPlotOptions(ChartPiePlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}

	public ChartPiePlotOptions getPlotOptions() {
		return plotOptions;
	}
	
	public JSONObject drawChart(){
		
		return createJSONGraphObject();
	}
	
	public JSONObject drawChart(List<ChartSeries> chartSeries, String title,
				String subTitle ){
		
		this.getTitle().setText(title);
		if(subTitle != null )
			this.getSubTitle().setText(subTitle);
		else
			this.setSubTitle(null);
		
		this.setxAxis(null);
		this.setSeries(chartSeries);
		return createJSONGraphObject();
	}

	private JSONObject createJSONGraphObject(){
		
		JSONObject chartData = new JSONObject();
		try {
			  chartData.put("chartData",   UIUtils.convertToJSON(this));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return chartData;
		
	}
}
