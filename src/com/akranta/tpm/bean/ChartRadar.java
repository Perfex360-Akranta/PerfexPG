package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
import java.lang.reflect.InvocationTargetException;
import com.akranta.tpm.utils.CommonMessage;
import java.util.List;

import net.sf.json.JSONObject;

import com.akranta.tpm.controller.UIUtils;

public class ChartRadar extends ChartOptionBean {
	

	
	public ChartRadar(){
		this.getTooltip().setShared(true);
		this.setPolar(true);
		this.setChartType(ChartTypes.LINE);
	}
		
	public JSONObject drawChart(){
		
		return createJSONGraphObject();
	}
	
	public JSONObject drawChart(List<ChartSeries> chartSeries,List<String> xAxisCategories, String title){
		
		this.getTitle().setText(title);
		
		this.getxAxis().setCategories(xAxisCategories);
		this.getxAxis().setTickmarkPlacement("on");
		this.getxAxis().setLineWidth(0);
		
		ChartYAxis yAxis = new ChartYAxis();
		yAxis.setGridLineInterpolation("polygon");
		yAxis.setLineWidth(0);
		yAxis.setMin(0);
		//yAxis.setMax(3);
		this.getyAxis().add(yAxis);
		
		this.setSeries(chartSeries);
		
		return createJSONGraphObject();
	}

	public JSONObject drawChart(List<ChartSeries> chartSeries,List<String> xAxisCategories, String title,int max){
		
		CommonMessage.debugMsg("maxx=="+max);
		
		this.getTitle().setText(title);
		
		this.getxAxis().setCategories(xAxisCategories);
		this.getxAxis().setTickmarkPlacement("on");
		this.getxAxis().setLineWidth(0);
		
		ChartYAxis yAxis = new ChartYAxis();
		yAxis.setGridLineInterpolation("polygon");
		yAxis.setLineWidth(0);
		yAxis.setMin(0);
		yAxis.setMax(max);
		this.getyAxis().add(yAxis);
		
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
