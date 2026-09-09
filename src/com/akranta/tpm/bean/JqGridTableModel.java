package com.akranta.tpm.bean;

import java.util.ArrayList;
import java.util.List;

public class JqGridTableModel {
	
	private List<String[]> rowHeaders;
	private boolean paginate ; 
	private List<JqGridColModel> colModel;
	private int tableWidth;
	private int tableHeight;
	private String groupByField;
	private boolean cellEdit;
	private boolean enableFilter;
	private boolean loadOnce;
	private boolean tableButton;
	private boolean rowNumbers;
	private boolean isGroupBy;
	private String tableCaption;
	private boolean groupSummary;
	private boolean sortable;
	private boolean cellSubmitLocal;
	private String groupText;
	private boolean subGrid;
	private boolean rowSpan;
	private List<String> rowSpanCol;
	private List<Integer> rotationRows;
	private boolean rowHeight;
	private String[] formatterIndex;
	private boolean gridEdit;
	private boolean multiSelect;
	
	public boolean isGridEdit() {
		return gridEdit;
	}

	public void setGridEdit(boolean gridEdit) {
		this.gridEdit = gridEdit;
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public boolean isCellSubmitLocal() {
		return cellSubmitLocal;
	}

	public void setCellSubmitLocal(boolean cellSubmitLocal) {
		this.cellSubmitLocal = cellSubmitLocal;
	}

	public JqGridTableModel()
	{
		rowHeaders = new ArrayList<String[]>();
		colModel = new ArrayList<JqGridColModel>();
	}

	public List<String[]> getRowHeaders() {
		return rowHeaders;
	}

	public void setRowHeaders(List<String[]> rowHeaders) {
		this.rowHeaders = rowHeaders;
	}
	
	public int getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(int tableWidth) {
		this.tableWidth = tableWidth;
	}

	public int getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(int tableHeight) {
		this.tableHeight = tableHeight;
	}

	public String getGroupByField() {
		return groupByField;
	}

	public void setGroupByField(String groupByField) {
		this.groupByField = groupByField;
	}

	public boolean isCellEdit() {
		return cellEdit;
	}

	public void setCellEdit(boolean cellEdit) {
		this.cellEdit = cellEdit;
	}

	public boolean isLoadOnce() {
		return loadOnce;
	}

	public void setLoadOnce(boolean loadOnce) {
		this.loadOnce = loadOnce;
	}

	public boolean isTableButton() {
		return tableButton;
	}

	public void setTableButton(boolean tableButton) {
		this.tableButton = tableButton;
	}
	public boolean isRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(boolean rowSpan) {
		this.rowSpan = rowSpan;
	}

	public List<String> getRowSpanCol() {
		return rowSpanCol;
	}

	public void setRowSpanCol(List<String> rowSpanCol) {
		this.rowSpanCol = rowSpanCol;
	}

	public boolean isRowNumbers() {
		return rowNumbers;
	}

	public void setRowNumbers(boolean rowNumbers) {
		this.rowNumbers = rowNumbers;
	}

	public boolean isGroupBy() {
		return isGroupBy;
	}

	public void setGroupBy(boolean isGroupBy) {
		this.isGroupBy = isGroupBy;
	}

	public String getTableCaption() {
		return tableCaption;
	}

	public void setTableCaption(String tableCaption) {
		this.tableCaption = tableCaption;
	}

	public boolean isGroupSummary() {
		return groupSummary;
	}

	public void setGroupSummary(boolean groupSummary) {
		this.groupSummary = groupSummary;
	}

	public List<JqGridColModel> getColModel() {
		return colModel;
	}

	public void setColModel(List<JqGridColModel> colModel) {
		this.colModel = colModel;
	}

	public void setGroupText(String groupText) {
		this.groupText = groupText;
	}

	public String getGroupText() {
		return groupText;
	}

	public void setSubGrid(boolean subGrid) {
		this.subGrid = subGrid;
	}

	public boolean isSubGrid() {
		return subGrid;
	}

	public void setRotationRows(List<Integer> rotationRows) {
		this.rotationRows = rotationRows;
	}

	public List<Integer> getRotationRows() {
		return rotationRows;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setEnableFilter(boolean enableFilter) {
		this.enableFilter = enableFilter;
	}

	public boolean isEnableFilter() {
		return enableFilter;
	}

	public void setRowHeight(boolean rowHeight) {
		this.rowHeight = rowHeight;
	}

	public boolean isRowHeight() {
		return rowHeight;
	}

	public String[] getFormatterIndex() {
		return formatterIndex;
	}

	public void setFormatterIndex(String[] formatterIndex) {
		this.formatterIndex = formatterIndex;
	}

	public boolean isPaginate() {
		return paginate;
	}

	public void setPaginate(boolean paginate) {
		this.paginate = paginate;
	}

}
