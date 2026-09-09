package com.akranta.tpm.bean;

/*Author:Prasanth
 * 
 */
public class ChartYAxis {

	private Integer min;
	private Integer max;
	private ChartTitle title;
	private ChartDataLabel label;
	private Boolean opposite;
	private Integer offset;
	private String gridLineInterpolation;
	private Integer lineWidth;

	public ChartYAxis() {
		title = new ChartTitle();
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public ChartTitle getTitle() {
		return title;
	}

	public void setTitle(ChartTitle title) {
		this.title = title;
	}

	public ChartDataLabel getLabel() {
		return label;
	}

	public void setLabel(ChartDataLabel label) {
		this.label = label;
	}

	public void setOpposite(Boolean opposite) {
		this.opposite = opposite;
	}

	public Boolean getOpposite() {
		return opposite;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setGridLineInterpolation(String gridLineInterpolation) {
		this.gridLineInterpolation = gridLineInterpolation;
	}

	public String getGridLineInterpolation() {
		return gridLineInterpolation;
	}

	public void setLineWidth(Integer lineWidth) {
		this.lineWidth = lineWidth;
	}

	public Integer getLineWidth() {
		return lineWidth;
	}
	
	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}


}
