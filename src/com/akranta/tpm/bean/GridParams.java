package com.akranta.tpm.bean;

import java.util.List;

import com.akranta.tpm.model.ComboFilter;

public class GridParams {
	
	private List<GridFilter> gridFilters;
	private String gridSortColumn;
	private String gridSortOrder;
	private ComboFilter SortOrd;
	private String fromRow;
	private String toRow;
	private long totalRecordCnt;
	public List<GridFilter> getGridFilters() {
		return gridFilters;
	}
	public void setGridFilters(List<GridFilter> gridFilters) {
		this.gridFilters = gridFilters;
	}
	public String getGridSortColumn() {
		return gridSortColumn;
	}
	public void setGridSortColumn(String gridSortColumn) {
		this.gridSortColumn = gridSortColumn;
	}
	public String getGridSortOrder() {
		return gridSortOrder;
	}
	public void setGridSortOrder(String gridSortOrder) {
		this.gridSortOrder = gridSortOrder;
	}
	public ComboFilter getSortOrd() {
		return SortOrd;
	}
	public void setSortOrd(ComboFilter sortOrd) {
		SortOrd = sortOrd;
	}
	public String getFromRow() {
		return fromRow;
	}
	public void setFromRow(String fromRow) {
		this.fromRow = fromRow;
	}
	public String getToRow() {
		return toRow;
	}
	public void setToRow(String toRow) {
		this.toRow = toRow;
	}
	public long getTotalRecordCnt() {
		return totalRecordCnt;
	}
	public void setTotalRecordCnt(long totalRecordCnt) {
		this.totalRecordCnt = totalRecordCnt;
	}

	

}
