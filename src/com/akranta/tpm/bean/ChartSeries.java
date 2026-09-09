package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class ChartSeries {
	
	private String type;
	private String name;
	private List<?> data;
	private List<Integer> center;
	private Integer size;
	private Boolean showLegend;
	private Integer yAxis;
	private String pointPlacement;
	
	public ChartSeries(){
		
		data = new ArrayList();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public List<Integer> getCenter() {
		return center;
	}

	public void setCenter(List<Integer> center) {
		this.center = center;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Boolean getShowLegend() {
		return showLegend;
	}

	public void setShowLegend(Boolean showLegend) {
		this.showLegend = showLegend;
	}


	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}


	public Integer getyAxis() {
		return yAxis;
	}
	
	public String getPointPlacement() {
		return pointPlacement;
	}
	public void setPointPlacement(String pointPlacement) {
		this.pointPlacement = pointPlacement;
	}

}
