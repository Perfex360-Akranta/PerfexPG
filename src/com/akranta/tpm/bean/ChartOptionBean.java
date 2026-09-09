package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONObject;


public class ChartOptionBean {
	
	private String defaultSeriesType;
	private ChartTitle title;
	private ChartTitle subTitle;
	private ChartXAxis xAxis;
	private List<ChartYAxis> yAxis;
	private ChartLegend legend;
	private String chartType; 
	private List<ChartSeries> series;
	private ChartToolTip tooltip;
	private Integer width,height;
	private Boolean polar;
	
	
	public ChartOptionBean(){
		this.title = new ChartTitle();
		this.subTitle = new ChartTitle();
		this.xAxis = new ChartXAxis();
		this.yAxis = new ArrayList<ChartYAxis>();
		this.tooltip = new ChartToolTip();
		this.tooltip.setX("this.x");
		this.tooltip.setY("this.y");
	}
	
	public void setTitle(ChartTitle title) {
		this.title = title;
	}
	public String getDefaultSeriesType() {
		return defaultSeriesType;
	}
	public void setDefaultSeriesType(String defaultSeriesType) {
		this.defaultSeriesType = defaultSeriesType;
	}
	public ChartTitle getTitle() {
		return title;
	}
	public ChartTitle getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(ChartTitle subTitle) {
		this.subTitle = subTitle;
	}

	public ChartXAxis getxAxis() {
		return xAxis;
	}
	public void setxAxis(ChartXAxis xAxis) {
		this.xAxis = xAxis;
	}
	public List<ChartYAxis> getyAxis() {
		return yAxis;
	}
	public void setyAxis(List<ChartYAxis> yAxis) {
		this.yAxis = yAxis;
	}
	public ChartLegend getLegend() {
		return legend;
	}
	public void setLegend(ChartLegend legend) {
		this.legend = legend;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getChartType() {
		return chartType;
	}
	
	public List<ChartSeries> getSeries() {
		return series;
	}
	public void setSeries(List<ChartSeries> series) {
		this.series = series;
	}
	
	public JSONObject drawChart(List<String> xAxisCategories, List<ChartSeries> chartSeries, String title,
			String subTitle, String yAxisLabel, String xAxisLabel  ){
	
		this.getTitle().setText(title);
		if(subTitle != null )
			this.getSubTitle().setText(subTitle);
		else
			this.setSubTitle(null);
			
		this.getxAxis().setCategories(xAxisCategories);
		this.getxAxis().getTitle().setText(xAxisLabel);
		ChartYAxis yAxis = new ChartYAxis(); 
		yAxis.setMin(0);
		yAxis.getTitle().setText(yAxisLabel);
		this.getyAxis().add(yAxis);
		this.setSeries(chartSeries);
		
		return createJSONGraphObject();
	}
	
	public JSONObject drawChart(List<String> xAxisCategories, List<ChartSeries> chartSeries, String title,
			String subTitle, List<ChartYAxis> yAxis){
	
		this.getTitle().setText(title);
		if(subTitle != null )
			this.getSubTitle().setText(subTitle);
		else
			this.setSubTitle(null);
			
		this.getxAxis().setCategories(xAxisCategories);
		this.setyAxis(yAxis);
		this.setSeries(chartSeries);
		
		return createJSONGraphObject();
	}
	
	private JSONObject createJSONGraphObject(){
		
		JSONObject chartData = new JSONObject();
		try {
			  chartData.put("chartData",   UIUtils.convertToJSON(this));
			  //CommonMessage.debugMsg(chartData);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return chartData;
		
	}

	public void setTooltip(ChartToolTip tooltip) {
		this.tooltip = tooltip;
	}

	public ChartToolTip getTooltip() {
		return tooltip;
	}
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setPolar(Boolean polar) {
		this.polar = polar;
	}

	public Boolean getPolar() {
		return polar;
	}

}


 