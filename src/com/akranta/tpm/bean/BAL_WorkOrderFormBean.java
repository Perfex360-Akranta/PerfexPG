package com.akranta.tpm.bean;
import java.util.List;

import com.akranta.tpm.model.BalPlmTlWofeedback;

public class BAL_WorkOrderFormBean {
	private String genwMachineid;
	private String WogenCostCenter;
	private String WogenTradeid;
	private String WofbCompletedby;
	private String WogenwoNo;
	private String startDate;
	private String WofbCreatedby;
	private String pmCalendarId;
	private List<Bal_WorkOrderDetailsLstBean> grdWoBean;
	private List<BalPlmTlWofeedback> plmtlFeedback;
	/**For CBM DATA***/
	private String currentReading;
	private String adjustReading;
	private String nextdueDate;
	private String minmumReading;
	private String maximumReading;
	/**END**/
	public void setGenwMachineid(String genwMachineid) {
		this.genwMachineid = genwMachineid;
	}
	public String getGenwMachineid() {
		return genwMachineid;
	}
	public void setWogenCostCenter(String wogenCostCenter) {
		WogenCostCenter = wogenCostCenter;
	}
	public String getWogenCostCenter() {
		return WogenCostCenter;
	}
	public void setWogenTradeid(String wogenTradeid) {
		WogenTradeid = wogenTradeid;
	}
	public String getWogenTradeid() {
		return WogenTradeid;
	}
	public void setWofbCompletedby(String wofbCompletedby) {
		WofbCompletedby = wofbCompletedby;
	}
	public String getWofbCompletedby() {
		return WofbCompletedby;
	}
	public void setWogenwoNo(String wogenwoNo) {
		WogenwoNo = wogenwoNo;
	}
	public String getWogenwoNo() {
		return WogenwoNo;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setGrdWoBean(List<Bal_WorkOrderDetailsLstBean> grdWoBean) {
		this.grdWoBean = grdWoBean;
	}
	public List<Bal_WorkOrderDetailsLstBean> getGrdWoBean() {
		return grdWoBean;
	}
	public void setPlmtlFeedback(List<BalPlmTlWofeedback> plmtlFeedback) {
		this.plmtlFeedback = plmtlFeedback;
	}
	public List<BalPlmTlWofeedback> getPlmtlFeedback() {
		return plmtlFeedback;
	}
	public void setWofbCreatedby(String wofbCreatedby) {
		WofbCreatedby = wofbCreatedby;
	}
	public String getWofbCreatedby() {
		return WofbCreatedby;
	}
	public void setPmCalendarId(String pmCalendarId) {
		this.pmCalendarId = pmCalendarId;
	}
	public String getPmCalendarId() {
		return pmCalendarId;
	}
	public void setCurrentReading(String currentReading) {
		this.currentReading = currentReading;
	}
	public String getCurrentReading() {
		return currentReading;
	}
	public void setAdjustReading(String adjustReading) {
		this.adjustReading = adjustReading;
	}
	public String getAdjustReading() {
		return adjustReading;
	}
	public void setNextdueDate(String nextdueDate) {
		this.nextdueDate = nextdueDate;
	}
	public String getNextdueDate() {
		return nextdueDate;
	}
	public void setMinmumReading(String minmumReading) {
		this.minmumReading = minmumReading;
	}
	public String getMinmumReading() {
		return minmumReading;
	}
	public void setMaximumReading(String maximumReading) {
		this.maximumReading = maximumReading;
	}
	public String getMaximumReading() {
		return maximumReading;
	}

}
