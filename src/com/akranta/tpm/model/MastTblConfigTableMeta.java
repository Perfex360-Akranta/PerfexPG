package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

public class MastTblConfigTableMeta {
	
	private List<MastTblConfigColMeta> mastTblConfigCols =null; 
	private String tableName ;
	private String createdBy;
	private boolean insert;
	
	private boolean userRightForInsert;
	private boolean userRightForUpdate;
	private boolean userRightForDelete;
	private boolean userRightForView;
	
	
	public MastTblConfigTableMeta(){
		
		mastTblConfigCols = new ArrayList<MastTblConfigColMeta>();
		
	}

	public List<MastTblConfigColMeta> getMastTblConfigCols() {
		return mastTblConfigCols;
	}

	public void setMastTblConfigCols(List<MastTblConfigColMeta> mastTblConfigCols) {
		this.mastTblConfigCols = mastTblConfigCols;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param insert the insert to set
	 */
	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	/**
	 * @return the insert
	 */
	public boolean isInsert() {
		return insert;
	}
	
	public boolean isUserRightForInsert() {
		return userRightForInsert;
	}

	public void setUserRightForInsert(boolean userRightForInsert) {
		this.userRightForInsert = userRightForInsert;
	}

	public boolean isUserRightForUpdate() {
		return userRightForUpdate;
	}

	public void setUserRightForUpdate(boolean userRightForUpdate) {
		this.userRightForUpdate = userRightForUpdate;
	}

	public boolean isUserRightForDelete() {
		return userRightForDelete;
	}

	public void setUserRightForDelete(boolean userRightForDelete) {
		this.userRightForDelete = userRightForDelete;
	}

	public boolean isUserRightForView() {
		return userRightForView;
	}

	public void setUserRightForView(boolean userRightForView) {
		this.userRightForView = userRightForView;
	}

}
