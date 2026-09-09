package com.akranta.tpm.bean;

import java.util.List;

import com.akranta.tpm.model.PlmTlSpareconsumed;
import com.akranta.tpm.model.PlmTlSparecostactual;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.CommonMessage;

public class WorkOrderDetailsBean {
	private String wofbStarttime;
	private String wofbEndtime;	
	private String wofbProdStarttime;
	private String wofbObservation;
	private String pmCalendarId;
	private String fromMonth;
	private String assmbleyId;
	private String WOmstId;
	private String pmstdId;
	private String directSave;
	private String jobtype;
	private String plannedduration;
	private String WofbAction;
	private String WofbDuration;
	private String WksmWodetailid;
	private String beanWofbCompletedby;
	private String formActionMode;
	private String formMode;
	private String actionmode;
	private String disableForm;
	private String upcWorkOrderNo ;
	private String upcWorkdetailid ;
	private String description;
	private String allotedTo; 
	private String modeModify;
	private PlmTlSpareconsumed spareConsumed;
	private PlmTlSparecostactual spareCostActual;
	private String DBLocation ; 
	private String WofbMachinetkeovrtime;
	/**For CBM DATA***/
	private String currentReading;
	private String adjustReading;
	private String nextdueDate;
	private String minmumReading;
	private String maximumReading;
	private String genwCellid;
	private String genwFactoryid;
	private String genwSectionid;
	private String genwMachineid;
	private String genwFlid;
	private String genwElementid;
	private String wogenTradeid;
	private String genwLocationid;
	private String wogenAssemblyid;
	/**END**/
 
	

	
	public WorkOrderDetailsBean(String mode) {
		// TODO Auto-generated constructor stub
	
		this.setActionmode(mode);
		CommonMessage.debugMsg("bean Mode  :"+mode);
		if(mode.equals("view")){
			this.setDisableForm("true");
			CommonMessage.debugMsg("WO viewdisableform"+this.getDisableForm());
		}
	}
	public WorkOrderDetailsBean() {
		this.formMode = "CREATE";
	}
	private List<String> gridData;
	private List<WorkOrderDetailsLstBean> grdWoBean;
	//private List<String> stringList;

	/*public void add(String str) {
	    stringList.add(str);
	}

	public String get(int i) {
	    return stringList.get(i);
	}

	public String[] getElements() {
	    String[] elements = null;
	    stringList.toArray(elements);        
	    return elements;
	}
*/
	
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

	public void setPmStdId(String pmstdId) {
		this.pmstdId = pmstdId;
	}

	public String getPmStdId() {
		return pmstdId;
	}

	public void setDirectSave(String directSave) {
		this.directSave = directSave;
	}

	public String getDirectSave() {
		return directSave;
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

	public void setGridData(List<String> gridData) {
		this.gridData = gridData;
	}

	public List<String> getGridData() {
		return gridData;
	}

	public void setGrdWoBean(List<WorkOrderDetailsLstBean> grdWoBean) {
		this.grdWoBean = grdWoBean;
	}

	public List<WorkOrderDetailsLstBean> getGrdWoBean() {
		return grdWoBean;
	}

	public void setBeanWofbCompletedby(String beanWofbCompletedby) {
		this.beanWofbCompletedby = beanWofbCompletedby;
	}

	public String getBeanWofbCompletedby() {
		return beanWofbCompletedby;
	}

	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}

	public String getFormActionMode() {
		return formActionMode;
	}

	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}

	public String getFormMode() {
		return formMode;
	}

	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}

	public String getActionmode() {
		return actionmode;
	}

	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}

	public String getDisableForm() {
		return disableForm;
	}
	
	public void setUpcWorkOrderNo(String upcWorkOrderNo) {
		this.upcWorkOrderNo = upcWorkOrderNo;
	}
	public String getUpcWorkOrderNo() {
		return upcWorkOrderNo;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setAllotedTo(String allotedTo) {
		this.allotedTo = allotedTo;
	}
	public String getAllotedTo() {
		return allotedTo;
	}
	public void setUpcWorkdetailid(String upcWorkdetailid) {
		this.upcWorkdetailid = upcWorkdetailid;
	}
	public String getUpcWorkdetailid() {
		return upcWorkdetailid;
	}
	public void setModeModify(String modeModify) {
		this.modeModify = modeModify;
	}
	public String getModeModify() {
		return modeModify;
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
	public void setWofbMachinetkeovrtime(String wofbMachinetkeovrtime) {
		WofbMachinetkeovrtime = wofbMachinetkeovrtime;
	}
	public String getWofbMachinetkeovrtime() {
		return WofbMachinetkeovrtime;
	}
	public void setDBLocation(String dBLocation) {
		DBLocation = dBLocation;
	}
	public String getDBLocation() {
		return DBLocation;
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
	public void setWofbObservation(String wofbObservation) {
		this.wofbObservation = wofbObservation;
	}
	public String getWofbObservation() {
		return wofbObservation;
	}
	public String getGenwCellid() {
		return genwCellid;
	}
	public void setGenwCellid(String genwCellid) {
		this.genwCellid = genwCellid;
	}
	public String getGenwFactoryid() {
		return genwFactoryid;
	}
	public void setGenwFactoryid(String genwFactoryid) {
		this.genwFactoryid = genwFactoryid;
	}
	public String getGenwSectionid() {
		return genwSectionid;
	}
	public void setGenwSectionid(String genwSectionid) {
		this.genwSectionid = genwSectionid;
	}
	public String getGenwFlid() {
		return genwFlid;
	}
	public void setGenwFlid(String genwFlid) {
		this.genwFlid = genwFlid;
	}
	public String getGenwElementid() {
		return genwElementid;
	}
	public void setGenwElementid(String genwElementid) {
		this.genwElementid = genwElementid;
	}
	public String getGenwMachineid() {
		return genwMachineid;
	}
	public void setGenwMachineid(String genwMachineid) {
		this.genwMachineid = genwMachineid;
	}
	
	public void setGenwLocationid(String genwLocationid) {
		this.genwLocationid = genwLocationid;
	}
	public String getGenwLocationid() {
		return genwLocationid;
	}
	public void setWogenTradeid(String wogenTradeid) {
		this.wogenTradeid = wogenTradeid;
	}
	public String getWogenTradeid() {
		return wogenTradeid;
	}
	public void setWogenAssemblyid(String wogenAssemblyid) {
		this.wogenAssemblyid = wogenAssemblyid;
	}
	public String getWogenAssemblyid() {
		return wogenAssemblyid;
	}

	
	
}
