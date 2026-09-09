package com.akranta.tpm.bean;

public class PcsEnableDisableFormBean 
{
	private String pelcCreatedBy;
	private String forCell;
	private String flid;
	private boolean disablepelcType;

	/**
	 * @param pelcCreatedBy the pelcCreatedBy to set
	 */
	public void setPelcCreatedBy(String pelcCreatedBy) {
		this.pelcCreatedBy = pelcCreatedBy;
	}

	/**
	 * @return the pelcCreatedBy
	 */
	public String getPelcCreatedBy() {
		return pelcCreatedBy;
	}

	/**
	 * @param disablepelcType the disablepelcType to set
	 */
	public void setDisablepelcType(boolean disablepelcType) {
		this.disablepelcType = disablepelcType;
	}

	/**
	 * @return the disablepelcType
	 */
	public boolean isDisablepelcType() {
		return disablepelcType;
	}	


	
	public void setForCell(String forCell) {
		this.forCell = forCell;
	}

	public String getForCell() {
		return forCell;
	}



	public void setFlid(String flid) {
		this.flid = flid;
	}

	public String getFlid() {
		return flid;
	}


}
