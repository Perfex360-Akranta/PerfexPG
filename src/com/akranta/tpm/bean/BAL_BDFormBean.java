package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class BAL_BDFormBean {
	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	
	private FormModes formModes;
	private boolean disableForm ;
	
	
	private String bdmsReportedtime;
	private String bdmsReceivedtime;
	private String bdmsWostart;
	private String bdmsWoend;
	private String bdmsProdacceptime;
	private String bdmsphenName;	
	private String bdmsCompletedtime;

	
	private String bdmsActualworktime;

	private String bphmBddetails;
	private String bphmAssemblyid;
	private String bphmMachineid;
	private String bphmFactoryid;
	private String bphmSectionid;
	private String bphmCellid;	
	private String finalAction;
	
	private String woendtime;
	private String chkCompletedBy;

	private String issparesY;
	private String issparesN;
	private String issparesW;
	
	private boolean disablebdmsKeyid;
	private boolean disablebdmsEntrydate;
	private boolean disablebdmsShiftid;
	private boolean disablebdmsWno;
	private boolean disablebdanErppoststatus;
	private boolean disablebdanCostcentre;
	private boolean disablebdmsMachineid;
	private boolean disablebdmsRemarks;
	private boolean disablebdmsRelatedto;
	private boolean disablebdmsMould;
	private boolean disablebdmsRepeatedbdflag;
	private boolean disablebdmsRepeatedbdno;
	private boolean disableReporteddate;
	private boolean disablebdmsReporteddate;
	private boolean disablebdmsReportedtime;
	private boolean disablebdmsActualworktime;
	private boolean disableReceiveddate;
	private boolean disablebdmsReceiveddate;
	private boolean disablebdmsReceivedtime;
	private boolean disablebdmsDowntime;
	private boolean disableWostarttime;
	private boolean disablebdmsWostarttime;
	private boolean disablebdmsWostart;
	private boolean disablebdmsBreaktime;
	private boolean disableWoendtime;
	private boolean disablebdmsWoendtime;
	private boolean disablebdmsWoend;
	private boolean disablebdanProblemseverity;
	private boolean disableProdaccepdate;
	private boolean disablebdmsProdaccepdate;
	private boolean disablebdmsProdacceptime;
	private boolean disablebdmsPriority;
	private boolean disablebdmsAlarmdescription;
	private boolean disablebdmsAssemblyid;
	private boolean disablebdmsSubassemblyid;
	private boolean disableIssparesY;
	private boolean disableIssparesN;
	private boolean disableIssparesW;
	private boolean bdanFailuretype;
	private boolean disableBdanFailuretype;
	private boolean bdmsPartlocationid;
	private boolean disablebdmsSpareid;
	private boolean disablebdmsFinalphenomena;
	private boolean disablebdmsFinalcause;
	private boolean disablebdanClassificationid;
	private boolean disablebdmsProblemdescription;
	private boolean disablebdanFinalaction;
	private boolean disablebdanCountermeasure;
	private boolean disablebdanRootcause;
	private boolean disablebdmsBookedby;
	private boolean disablebdanCompletedby;
	private boolean disablebdanWwno;
	private boolean disablewcmlCommunicationtext;
	private boolean disablebdmsBookedtrade = false;
	
	
//For SAP Table
	private String ExistDocNumber;
	
	public BAL_BDFormBean()
	{
		this.formMode = "CREATE";
	}
	public BAL_BDFormBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}

	private void formModeSettings(FormModes mode){
		this.formModes = mode;
		if( mode == FormModes.view ){
			this.setDisableForm(true);
			this.formActionMode = FormModeConsts.view;
			this.disablebdmsKeyid=this.disablebdmsEntrydate=this.disablebdmsShiftid=this.disablebdmsWno=true;
			this.disablebdanErppoststatus=this.disablebdanCostcentre=this.disablebdmsMachineid=	this.disablebdmsRemarks=true;
			this.disablebdmsRelatedto=this.disablebdmsMould=this.disablebdmsRepeatedbdflag=	this.disablebdmsRepeatedbdno=true;
			this.disableReporteddate=this.disablebdmsReporteddate=this.disablebdmsReportedtime=this.disablebdmsActualworktime=true;
			this.disablebdmsReceiveddate=this.disablebdmsReceivedtime=	this.disablebdmsDowntime=true;
			this.disableWostarttime=this.disablebdmsWostart=this.disablebdmsBreaktime=true;
			this.disablebdmsWoendtime=this.disablebdmsWoend=this.disablebdanProblemseverity=true;
			this.disablebdmsProdaccepdate=this.disablebdmsProdacceptime=this.disablebdmsPriority=true;
			this.disablebdmsAlarmdescription=this.disablebdmsAssemblyid=this.disablebdmsSubassemblyid=this.disableIssparesY=true;
			this.disableIssparesN=this.disableIssparesW=this.setDisableBdanFailuretype(this.bdmsPartlocationid=this.disablebdmsSpareid=true);
			this.disablebdmsFinalphenomena=this.disablebdmsFinalcause=this.disablebdanClassificationid=this.disablebdmsProblemdescription=true;
			this.disablebdanFinalaction=this.disablebdanCountermeasure=this.disablebdanRootcause=this.disablebdmsBookedby=true;
			this.disablebdanCompletedby=this.disablebdanWwno=this.disablewcmlCommunicationtext=true;
			this.disablebdmsBookedtrade = true;
		}
		else if( mode == FormModes.complete ){
			this.formActionMode = FormModeConsts.complete;
			this.disablebdmsMachineid= this.disablebdmsEntrydate = this.disableReporteddate =this.disablebdmsReporteddate =true;
			this.disablebdmsReportedtime = this.disableWostarttime=this.disablebdmsWostart=this.disablebdmsBreaktime=true;
			this.disablebdmsWoendtime=this.disablebdmsWoend=true;
			this.disablebdmsProdaccepdate=this.disablebdmsProdacceptime=true;
		}
	/*	else if(mode == FormModes.modify){
			//this.disableForm = false;
			this.formActionMode = FormModeConsts.modify;
		}
		else
			this.formActionMode = FormModeConsts.create;*/
		
	}
	public String getFormActionMode() {
		return formActionMode;
	}


	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}


	public String getFormMode() {
		return formMode;
	}


	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}


	public String getFormHeader() {
		return formHeader;
	}


	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	
	public String getBdmsReportedtime() {
		return bdmsReportedtime;
	}


	public void setBdmsReportedtime(String bdmsReportedtime) {		
		this.bdmsReportedtime = bdmsReportedtime;
	}


	public String getBdmsReceivedtime() {
		return bdmsReceivedtime;
	}


	public void setBdmsReceivedtime(String bdmsReceivedtime) {
		this.bdmsReceivedtime = bdmsReceivedtime;
	}


	public String getBdmsWostart() {
		return bdmsWostart;
	}


	public void setBdmsWostart(String bdmsWostart) {
		this.bdmsWostart = bdmsWostart;
	}


	public String getBdmsWoend() {
		return bdmsWoend;
	}


	public void setBdmsWoend(String bdmsWoend) {
		this.bdmsWoend = bdmsWoend;
	}


	public String getBdmsProdacceptime() {
		return bdmsProdacceptime;
	}


	public void setBdmsProdacceptime(String bdmsProdacceptime) {
		this.bdmsProdacceptime = bdmsProdacceptime;
	}
	
	public String getBdmsphenName() {
		return bdmsphenName;
	}


	public void setBdmsphenName(String bdmsphenName) {
		this.bdmsphenName = bdmsphenName;
	}

	public String getBphmBddetails() {
		return bphmBddetails;
	}


	public void setBphmBddetails(String bphmBddetails) {
		this.bphmBddetails = bphmBddetails;
	}


	public String getBphmAssemblyid() {
		return bphmAssemblyid;
	}


	public void setBphmAssemblyid(String bphmAssemblyid) {
		this.bphmAssemblyid = bphmAssemblyid;
	}


	public String getBphmMachineid() {
		return bphmMachineid;
	}


	public void setBphmMachineid(String bphmMachineid) {
		this.bphmMachineid = bphmMachineid;
	}


	public String getBphmFactoryid() {
		return bphmFactoryid;
	}


	public void setBphmFactoryid(String bphmFactoryid) {
		this.bphmFactoryid = bphmFactoryid;
	}


	public String getBphmSectionid() {
		return bphmSectionid;
	}


	public void setBphmSectionid(String bphmSectionid) {
		this.bphmSectionid = bphmSectionid;
	}


	public String getBphmCellid() {
		return bphmCellid;
	}


	public void setBphmCellid(String bphmCellid) {
		this.bphmCellid = bphmCellid;
	}


	

public String getIssparesY() {
		return issparesY;
	}


	public void setIssparesY(String issparesY) {
		System.out.println("Y Spares :: "+issparesY);
		this.issparesY = issparesY;
	}


	public String getIssparesN() {
		return issparesN;
	}


	public void setIssparesN(String issparesN) {
		this.issparesN = issparesN;
	}


	public String getIssparesW() {
		return issparesW;
	}
	public void setIssparesW(String issparesW) {
		this.issparesW = issparesW;
	}


	public String getFinalAction() {
		return finalAction;
	}


	public void setFinalAction(String finalAction) {
		this.finalAction = finalAction;
	}

	public String getBdmsActualworktime() {
		return bdmsActualworktime;
	}

	public void setbdmsActualworktime(String bdmsActualworktime) {
		this.bdmsActualworktime = bdmsActualworktime;
	}


	public String getWoendtime() {
		return woendtime;
	}


	public void setWoendtime(String woendtime) {
		this.woendtime = woendtime;
	}


	public FormModes getFormModes() {
		return formModes;
	}
	public void setFormModes(FormModes formModes) {
		this.formModes = formModes;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisablebdmsKeyid() {
		return disablebdmsKeyid;
	}


	public void setDisablebdmsKeyid(boolean disablebdmsKeyid) {
		this.disablebdmsKeyid = disablebdmsKeyid;
	}


	public boolean isDisablebdmsEntrydate() {
		return disablebdmsEntrydate;
	}


	public void setDisablebdmsEntrydate(boolean disablebdmsEntrydate) {
		this.disablebdmsEntrydate = disablebdmsEntrydate;
	}


	public boolean isDisablebdmsShiftid() {
		return disablebdmsShiftid;
	}


	public void setDisablebdmsShiftid(boolean disablebdmsShiftid) {
		this.disablebdmsShiftid = disablebdmsShiftid;
	}


	public boolean isDisablebdmsWno() {
		return disablebdmsWno;
	}


	public void setDisablebdmsWno(boolean disablebdmsWno) {
		this.disablebdmsWno = disablebdmsWno;
	}


	public boolean isDisablebdanErppoststatus() {
		return disablebdanErppoststatus;
	}


	public void setDisablebdanErppoststatus(boolean disablebdanErppoststatus) {
		this.disablebdanErppoststatus = disablebdanErppoststatus;
	}


	public boolean isDisablebdanCostcentre() {
		return disablebdanCostcentre;
	}


	public void setDisablebdanCostcentre(boolean disablebdanCostcentre) {
		this.disablebdanCostcentre = disablebdanCostcentre;
	}


	public boolean isDisablebdmsMachineid() {
		return disablebdmsMachineid;
	}


	public void setDisablebdmsMachineid(boolean disablebdmsMachineid) {
		this.disablebdmsMachineid = disablebdmsMachineid;
	}


	public boolean isDisablebdmsRemarks() {
		return disablebdmsRemarks;
	}


	public void setDisablebdmsRemarks(boolean disablebdmsRemarks) {
		this.disablebdmsRemarks = disablebdmsRemarks;
	}


	public boolean isDisablebdmsRelatedto() {
		return disablebdmsRelatedto;
	}


	public void setDisablebdmsRelatedto(boolean disablebdmsRelatedto) {
		this.disablebdmsRelatedto = disablebdmsRelatedto;
	}


	public boolean isDisablebdmsMould() {
		return disablebdmsMould;
	}


	public void setDisablebdmsMould(boolean disablebdmsMould) {
		this.disablebdmsMould = disablebdmsMould;
	}


	public boolean isDisablebdmsRepeatedbdflag() {
		return disablebdmsRepeatedbdflag;
	}


	public void setDisablebdmsRepeatedbdflag(boolean disablebdmsRepeatedbdflag) {
		this.disablebdmsRepeatedbdflag = disablebdmsRepeatedbdflag;
	}


	public boolean isDisablebdmsRepeatedbdno() {
		return disablebdmsRepeatedbdno;
	}


	public void setDisablebdmsRepeatedbdno(boolean disablebdmsRepeatedbdno) {
		this.disablebdmsRepeatedbdno = disablebdmsRepeatedbdno;
	}


	public boolean isDisableReporteddate() {
		return disableReporteddate;
	}


	public void setDisableReporteddate(boolean disableReporteddate) {
		this.disableReporteddate = disableReporteddate;
	}


	public boolean isDisablebdmsReporteddate() {
		return disablebdmsReporteddate;
	}


	public void setDisablebdmsReporteddate(boolean disablebdmsReporteddate) {
		this.disablebdmsReporteddate = disablebdmsReporteddate;
	}


	public boolean isDisablebdmsReportedtime() {
		return disablebdmsReportedtime;
	}


	public void setDisablebdmsReportedtime(boolean disablebdmsReportedtime) {
		this.disablebdmsReportedtime = disablebdmsReportedtime;
	}


	public boolean isDisablebdmsActualworktime() {
		return disablebdmsActualworktime;
	}


	public void setDisablebdmsActualworktime(boolean disablebdmsActualworktime) {
		this.disablebdmsActualworktime = disablebdmsActualworktime;
	}


	public boolean isDisableReceiveddate() {
		return disableReceiveddate;
	}


	public void setDisableReceiveddate(boolean disableReceiveddate) {
		this.disableReceiveddate = disableReceiveddate;
	}


	public boolean isDisablebdmsReceiveddate() {
		return disablebdmsReceiveddate;
	}


	public void setDisablebdmsReceiveddate(boolean disablebdmsReceiveddate) {
		this.disablebdmsReceiveddate = disablebdmsReceiveddate;
	}


	public boolean isDisablebdmsReceivedtime() {
		return disablebdmsReceivedtime;
	}


	public void setDisablebdmsReceivedtime(boolean disablebdmsReceivedtime) {
		this.disablebdmsReceivedtime = disablebdmsReceivedtime;
	}


	public boolean isDisablebdmsDowntime() {
		return disablebdmsDowntime;
	}


	public void setDisablebdmsDowntime(boolean disablebdmsDowntime) {
		this.disablebdmsDowntime = disablebdmsDowntime;
	}


	public boolean isDisableWostarttime() {
		return disableWostarttime;
	}


	public void setDisableWostarttime(boolean disableWostarttime) {
		this.disableWostarttime = disableWostarttime;
	}


	public boolean isDisablebdmsWostarttime() {
		return disablebdmsWostarttime;
	}


	public void setDisablebdmsWostarttime(boolean disablebdmsWostarttime) {
		this.disablebdmsWostarttime = disablebdmsWostarttime;
	}


	public boolean isDisablebdmsWostart() {
		return disablebdmsWostart;
	}


	public void setDisablebdmsWostart(boolean disablebdmsWostart) {
		this.disablebdmsWostart = disablebdmsWostart;
	}


	public boolean isDisablebdmsBreaktime() {
		return disablebdmsBreaktime;
	}


	public void setDisablebdmsBreaktime(boolean disablebdmsBreaktime) {
		this.disablebdmsBreaktime = disablebdmsBreaktime;
	}


	public boolean isDisableWoendtime() {
		return disableWoendtime;
	}


	public void setDisableWoendtime(boolean disableWoendtime) {
		this.disableWoendtime = disableWoendtime;
	}


	public boolean isDisablebdmsWoendtime() {
		return disablebdmsWoendtime;
	}


	public void setDisablebdmsWoendtime(boolean disablebdmsWoendtime) {
		this.disablebdmsWoendtime = disablebdmsWoendtime;
	}


	public boolean isDisablebdmsWoend() {
		return disablebdmsWoend;
	}


	public void setDisablebdmsWoend(boolean disablebdmsWoend) {
		this.disablebdmsWoend = disablebdmsWoend;
	}


	public boolean isDisablebdanProblemseverity() {
		return disablebdanProblemseverity;
	}


	public void setDisablebdanProblemseverity(boolean disablebdanProblemseverity) {
		this.disablebdanProblemseverity = disablebdanProblemseverity;
	}


	public boolean isDisableProdaccepdate() {
		return disableProdaccepdate;
	}


	public void setDisableProdaccepdate(boolean disableProdaccepdate) {
		this.disableProdaccepdate = disableProdaccepdate;
	}


	public boolean isDisablebdmsProdaccepdate() {
		return disablebdmsProdaccepdate;
	}


	public void setDisablebdmsProdaccepdate(boolean disablebdmsProdaccepdate) {
		this.disablebdmsProdaccepdate = disablebdmsProdaccepdate;
	}


	public boolean isDisablebdmsProdacceptime() {
		return disablebdmsProdacceptime;
	}


	public void setDisablebdmsProdacceptime(boolean disablebdmsProdacceptime) {
		this.disablebdmsProdacceptime = disablebdmsProdacceptime;
	}


	public boolean isDisablebdmsPriority() {
		return disablebdmsPriority;
	}


	public void setDisablebdmsPriority(boolean disablebdmsPriority) {
		this.disablebdmsPriority = disablebdmsPriority;
	}


	public boolean isDisablebdmsAlarmdescription() {
		return disablebdmsAlarmdescription;
	}


	public void setDisablebdmsAlarmdescription(boolean disablebdmsAlarmdescription) {
		this.disablebdmsAlarmdescription = disablebdmsAlarmdescription;
	}


	public boolean isDisablebdmsAssemblyid() {
		return disablebdmsAssemblyid;
	}


	public void setDisablebdmsAssemblyid(boolean disablebdmsAssemblyid) {
		this.disablebdmsAssemblyid = disablebdmsAssemblyid;
	}


	public boolean isDisablebdmsSubassemblyid() {
		return disablebdmsSubassemblyid;
	}


	public void setDisablebdmsSubassemblyid(boolean disablebdmsSubassemblyid) {
		this.disablebdmsSubassemblyid = disablebdmsSubassemblyid;
	}


	public boolean isDisableIssparesY() {
		return disableIssparesY;
	}


	public void setDisableIssparesY(boolean disableIssparesY) {
		this.disableIssparesY = disableIssparesY;
	}


	public boolean isDisableIssparesN() {
		return disableIssparesN;
	}


	public void setDisableIssparesN(boolean disableIssparesN) {
		this.disableIssparesN = disableIssparesN;
	}


	public boolean isDisableIssparesW() {
		return disableIssparesW;
	}


	public void setDisableIssparesW(boolean disableIssparesW) {
		this.disableIssparesW = disableIssparesW;
	}


	public boolean isDisablebdmsSpareid() {
		return disablebdmsSpareid;
	}


	public void setDisablebdmsSpareid(boolean disablebdmsSpareid) {
		this.disablebdmsSpareid = disablebdmsSpareid;
	}


	public boolean isDisablebdmsFinalphenomena() {
		return disablebdmsFinalphenomena;
	}


	public void setDisablebdmsFinalphenomena(boolean disablebdmsFinalphenomena) {
		this.disablebdmsFinalphenomena = disablebdmsFinalphenomena;
	}


	public boolean isDisablebdmsFinalcause() {
		return disablebdmsFinalcause;
	}


	public void setDisablebdmsFinalcause(boolean disablebdmsFinalcause) {
		this.disablebdmsFinalcause = disablebdmsFinalcause;
	}


	public boolean isDisablebdanClassificationid() {
		return disablebdanClassificationid;
	}


	public void setDisablebdanClassificationid(boolean disablebdanClassificationid) {
		this.disablebdanClassificationid = disablebdanClassificationid;
	}


	public boolean isDisablebdmsProblemdescription() {
		return disablebdmsProblemdescription;
	}


	public void setDisablebdmsProblemdescription(
			boolean disablebdmsProblemdescription) {
		this.disablebdmsProblemdescription = disablebdmsProblemdescription;
	}


	public boolean isDisablebdanFinalaction() {
		return disablebdanFinalaction;
	}


	public void setDisablebdanFinalaction(boolean disablebdanFinalaction) {
		this.disablebdanFinalaction = disablebdanFinalaction;
	}


	public boolean isDisablebdanCountermeasure() {
		return disablebdanCountermeasure;
	}


	public void setDisablebdanCountermeasure(boolean disablebdanCountermeasure) {
		this.disablebdanCountermeasure = disablebdanCountermeasure;
	}


	public boolean isDisablebdanRootcause() {
		return disablebdanRootcause;
	}


	public void setDisablebdanRootcause(boolean disablebdanRootcause) {
		this.disablebdanRootcause = disablebdanRootcause;
	}


	public boolean isDisablebdmsBookedby() {
		return disablebdmsBookedby;
	}


	public void setDisablebdmsBookedby(boolean disablebdmsBookedby) {
		this.disablebdmsBookedby = disablebdmsBookedby;
	}


	public boolean isDisablebdanCompletedby() {
		return disablebdanCompletedby;
	}


	public void setDisablebdanCompletedby(boolean disablebdanCompletedby) {
		this.disablebdanCompletedby = disablebdanCompletedby;
	}


	public boolean isDisablebdanWwno() {
		return disablebdanWwno;
	}


	public void setDisablebdanWwno(boolean disablebdanWwno) {
		this.disablebdanWwno = disablebdanWwno;
	}


	public boolean isDisablewcmlCommunicationtext() {
		return disablewcmlCommunicationtext;
	}


	public void setDisablewcmlCommunicationtext(boolean disablewcmlCommunicationtext) {
		this.disablewcmlCommunicationtext = disablewcmlCommunicationtext;
	}
	public String getExistDocNumber() {
		return ExistDocNumber;
	}
	public void setExistDocNumber(String existDocNumber) {
		ExistDocNumber = existDocNumber;
	}
	

	public String getBdmsCompletedtime() {
		return bdmsCompletedtime;
	}
	public void setBdmsCompletedtime(String bdmsCompletedtime) {
		this.bdmsCompletedtime = bdmsCompletedtime;
	}
	public String getChkCompletedBy() {
		return chkCompletedBy;
	}
	public void setChkCompletedBy(String chkCompletedBy) {
		this.chkCompletedBy = chkCompletedBy;
	}
	public boolean setDisableBdanFailuretype(boolean disableBdanFailuretype) {
		this.disableBdanFailuretype = disableBdanFailuretype;
		return disableBdanFailuretype;
	}
	public boolean isDisableBdanFailuretype() {
		return disableBdanFailuretype;
	}
	public boolean isBdanFailuretype() {
		return bdanFailuretype;
	}
	public void setBdanFailuretype(boolean bdanFailuretype) {
		this.bdanFailuretype = bdanFailuretype;
	}
	public boolean isBdmsPartlocationid() {
		return bdmsPartlocationid;
	}
	public void setBdmsPartlocationid(boolean bdmsPartlocationid) {
		this.bdmsPartlocationid = bdmsPartlocationid;
	}
	public boolean isDisablebdmsBookedtrade() {
		return disablebdmsBookedtrade;
	}
	public void setDisablebdmsBookedtrade(boolean disablebdmsBookedtrade) {
		this.disablebdmsBookedtrade = disablebdmsBookedtrade;
	}
	public void setBdmsActualworktime(String bdmsActualworktime) {
		this.bdmsActualworktime = bdmsActualworktime;
	}

}
