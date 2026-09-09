package com.akranta.tpm.bean;

import java.util.List;

import com.akranta.tpm.model.PlmTlSpareconsumed;
import com.akranta.tpm.model.PlmTlSparecostactual;


public class WorkOrderDetailsLstBean {
	private String wofbStarttime;
	private String wofbEndtime;	
	private String wofbProdStarttime;
	private String wofbObservation ;
	private String wofbRemarks;
	private String obsvResponsibility ;
	private String obsvTargetDate ;
	private String pmCalendarId;
	private String fromMonth;
	private String assmbleyId;
	private String WOmstId;
	private String pmstandId;
	private String machineId;
	private String jobtype;
	private String plannedduration;
	private String WofbAction;
	private String WofbDuration;
	private String WksmWodetailid;
	private String fromDate;
	private String WofbCompletedby;
	private String cbmReading = null;
    private String cbmNextDueDate = null;
    private String cbmMinReading = null;
    private String cbmMaxReading = null;
    private String cbmAdjustedReading = null;
    
    
	private PlmTlSpareconsumed spareConsumed;
	private PlmTlSparecostactual spareCostActual;
	
	public void setWofbStarttime(String wofbStarttime) {
		this.wofbStarttime = wofbStarttime;
	}
	
	public String getWofbStarttime() {
		return wofbStarttime;
	}
	public void setWofbEndtime(String wofbEndtime) {
		this.wofbEndtime = wofbEndtime;
	}
	public String getWofbEndtime() {
		return wofbEndtime;
	}
	public void setWofbProdStarttime(String wofbProdStarttime) {
		this.wofbProdStarttime = wofbProdStarttime;
	}
	public String getWofbProdStarttime() {
		return wofbProdStarttime;
	}
	public String getPmCalendarId() {
		return pmCalendarId;
	}
	public void setPmCalendarId(String pmCalendarId) {
		this.pmCalendarId = pmCalendarId;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setAssmbleyId(String assmbleyId) {
		this.assmbleyId = assmbleyId;
	}

	public String getAssmbleyId() {
		return assmbleyId;
	}

	public String getWOmstId() {
		return WOmstId;
	}

	public void setWOmstId(String wOmstId) {
		WOmstId = wOmstId;
	}


	

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setPlannedduration(String plannedduration) {
		this.plannedduration = plannedduration;
	}

	public String getPlannedduration() {
		return plannedduration;
	}

	public void setWofbAction(String wofbAction) {
		WofbAction = wofbAction;
	}

	public String getWofbAction() {
		return WofbAction;
	}

	public void setWofbDuration(String wofbDuration) {
		WofbDuration = wofbDuration;
	}

	public String getWofbDuration() {
		return WofbDuration;
	}

	public void setWksmWodetailid(String wksmWodetailid) {
		WksmWodetailid = wksmWodetailid;
	}

	public String getWksmWodetailid() {
		return WksmWodetailid;
	}

	public void setPmstandId(String pmstandId) {
		this.pmstandId = pmstandId;
	}

	public String getPmstandId() {
		return pmstandId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setWofbCompletedby(String wofbCompletedby) {
		WofbCompletedby = wofbCompletedby;
	}

	public String getWofbCompletedby() {
		return WofbCompletedby;
	}

	public void setSpareConsumed(PlmTlSpareconsumed spareConsumed) {
		this.spareConsumed = spareConsumed;
	}

	public PlmTlSpareconsumed getSpareConsumed() {
		return spareConsumed;
	}

	public void setSpareCostActual(PlmTlSparecostactual spareCostActual) {
		this.spareCostActual = spareCostActual;
	}

	public PlmTlSparecostactual getSpareCostActual() {
		return spareCostActual;
	}

	public void setWofbObservation(String wofbObservation) {
		this.wofbObservation = wofbObservation;
	}

	public String getWofbObservation() {
		return wofbObservation;
	}

	public String getObsvResponsibility() {
		return obsvResponsibility;
	}

	public void setObsvResponsibility(String obsvResponsibility) {
		this.obsvResponsibility = obsvResponsibility;
	}

	public String getObsvTargetDate() {
		return obsvTargetDate;
	}

	public void setObsvTargetDate(String obsvTargetDate) {
		this.obsvTargetDate = obsvTargetDate;
	}

	public String getCbmReading() {
		return cbmReading;
	}

	public void setCbmReading(String cbmReading) {
		this.cbmReading = cbmReading;
	}

	public String getCbmNextDueDate() {
		return cbmNextDueDate;
	}

	public void setCbmNextDueDate(String cbmNextDueDate) {
		this.cbmNextDueDate = cbmNextDueDate;
	}

	public String getCbmMinReading() {
		return cbmMinReading;
	}

	public void setCbmMinReading(String cbmMinReading) {
		this.cbmMinReading = cbmMinReading;
	}

	public String getCbmMaxReading() {
		return cbmMaxReading;
	}

	public void setCbmMaxReading(String cbmMaxReading) {
		this.cbmMaxReading = cbmMaxReading;
	}

	public void setCbmAdjustedReading(String cbmAdjustedReading) {
		this.cbmAdjustedReading = cbmAdjustedReading;
	}

	public String getCbmAdjustedReading() {
		return cbmAdjustedReading;
	}

	public void setWofbRemarks(String wofbRemarks) {
		this.wofbRemarks = wofbRemarks;
	}

	public String getWofbRemarks() {
		return wofbRemarks;
	}

	/*public void setGridData(List<String> gridData) {
		this.gridData = gridData;
	}

	public List<String> getGridData() {
		return gridData;
	}*/

	
	
}
