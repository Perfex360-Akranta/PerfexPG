package com.akranta.tpm.bean;
/*Author:Prasanth
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class ChartXAxis {
	
	private List<String> categories = null;
	private ChartTitle title;
	private ChartDataLabel labels;
	private String tickmarkPlacement;
	private Integer lineWidth;
	
	public ChartXAxis(){
		categories = new ArrayList<String>();
		title = new ChartTitle();
	}

	public String getTickmarkPlacement() {
		return tickmarkPlacement;
	}

	public void setTickmarkPlacement(String tickmarkPlacement) {
		this.tickmarkPlacement = tickmarkPlacement;
	}

	public Integer getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public ChartTitle getTitle() {
		return title;
	}

	public void setTitle(ChartTitle title) {
		this.title = title;
	}

	public void setLabels(ChartDataLabel labels) {
		this.labels = labels;
	}

	public ChartDataLabel getLabels() {
		return labels;
	}
	
	

}
