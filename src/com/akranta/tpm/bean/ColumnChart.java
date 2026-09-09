package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONObject;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class ColumnChart extends ChartOptionBean  {

	

    private ChartColumnPlotOption plotOption;
    
    public ColumnChart(){
    	plotOption = new ChartColumnPlotOption();
    	this.setChartType("column");
    	this.setLegend(new ChartLegend());
    	
    }
	
	public void setPlotOption(ChartColumnPlotOption plotOption) {
		this.plotOption = plotOption;
	}
	public ChartColumnPlotOption getPlotOption() {
		return plotOption;
	}
	
	
	public JSONObject drawChart(){
		
		return createJSONGraphObject();
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
			  if( this.getxAxis().getCategories().size() > 6)
			  {
				  ChartDataLabel label = new ChartDataLabel();
				  label.setRotation(-90);
				  label.setY(40);
				  label.setAlign("right");
				  label.getStyle().setFont("normal 10px Verdana, sans-serif");
				  this.getxAxis().setLabels(label);
				  
			  }
			  chartData.put("chartData",   UIUtils.convertToJSON(this));
			  CommonMessage.debugMsg(chartData);
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
