package com.akranta.tpm.bean;


import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.WOConstants;
import com.akranta.tpm.utils.CommonMessage;
public class WOFormBean {
	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String woMode;
	private String MandFieldFlag;
	private String shift;
	private FormModes formModes;
	private boolean disableForm ;
	private boolean disableShiftdate;
	private boolean disableShifttime;
	private boolean disableShiftid;
	private boolean disableBookedby;
	private boolean disableOccurreddate;
	private boolean disableOccuredtime;
	private boolean disableReporteddate;
	private boolean disableReportedtime;
	private boolean disablePriority;
	private boolean disableAssemblyid;
	private boolean disableActivitytype;
	private boolean disableWorkCenterid;
	private boolean disablePlannergroup;
	private boolean disableMachinecondition;
	private boolean disableRelatedto;
	private boolean disableMouldid;
	private boolean disableProblem;
	private boolean disableBookingremarks;
	private boolean disableAlarmno;
	private boolean disableSubassemblyid;
	private boolean disablePartlocation;
	private boolean disableSpareid;
	private boolean disableFailuretypeid;
	private boolean disableLocation;
	private boolean disablePhenomenaid;
	private boolean disableCauseid;
	private boolean disableTradeid;
//	private boolean disableTempfield10;
	
	private boolean disablePropstartdate;
	private boolean disablePropstarttime;
	private boolean disablePropenddate;
	private boolean disablePropendtime;
	private boolean disableRequestapproved;
	private boolean disableRequestcancel;
	private boolean disableRequestapproveddate;
	private boolean disableApprovaltime;
	private boolean disableRequestapprovedby;
	private boolean disableRequestapprovremarks;
	
	private boolean disableAcceptedflag;
	private boolean disableAccepteddate;
	private boolean disableAcceptedtime;
	private boolean disableAcceptedby;
	private boolean disableProductionapproval;
	private boolean disableProposedstflag;
	private boolean disableProposedstartdate;
	private boolean disableProposedstarttime;
	private boolean disableProposedendflag;
	private boolean disableProposedenddate;
	private boolean disableProposedendtime;
	private boolean disablePermitReqd;
	private boolean disablePermitNotReqd;	
	private boolean disableSafetypermitsrequried;
	private boolean disableAcceptedremarks;
	private boolean disableRescheduledate;
	private boolean disableRescheduletime;
	private boolean disableRescheduleby;
	private boolean disablePermitaccept;
	private boolean disablePermitreschedule;
	private boolean disableRescheduledstflag;
	private boolean disableReschedulestartdate;
	private boolean disableReschedulestarttime;
	private boolean disableRescheduledendflag;
	private boolean disableRescheduleenddate;
	private boolean disableRescheduleendtime;
	private boolean disableRescheduleremarks;
	private boolean disableAllottedto;
	private boolean disableAllottedflag;
	private boolean disableAllotteddate;
	private boolean disableAllottedtime;
	private boolean disableallottedBy;
	private boolean disableAllottedremarks;
	private boolean disableDoneby;	
	private boolean disableFinalactivity;
	private boolean disableActivityid;
	private boolean disableWorkstartflag;
	private boolean disableWorkstartdate;
	private boolean disableWorkstarttime;
	private boolean disableIntorextequip;
	private boolean disableIntorextequipdesc;
	private boolean disableWorkendflag;
	private boolean disableWorkenddate;
	private boolean disableWorkendtime;
	private boolean disableWoapprovalflag;
	private boolean disableWoapprovaldate;
	private boolean disableWoapprovaltime;
	private boolean disableWoapprovalby;
	private boolean disableMachinereleaseflag;
	private boolean disableMachinereleasedate;
	private boolean disableMachinereleasetime;
	private boolean disableMachinereleaseby;
	private boolean disableExceptedreturndate;
	private boolean disableExceptedreturntime;
	private boolean disableRemarks;
	private boolean disableFinalaction;
	private boolean disableCountermeasure;
	private boolean disableRootcause;
	private boolean disableProductionby;
	private boolean disableProductionstartflag;
	private boolean disableProductionstartdate;
	private boolean disableProductionstarttime;
	private boolean disableProductionremarks;
	private boolean disableSafetypermitsignoff;
	
	private boolean disableMachineid;
	private boolean disableCostcenterid;
	private boolean disableStatus;
	private boolean disableProcess;

	private String Shiftdate;
	private String Shifttime;
	private String Shiftid;
	private String womsBookedby;
	private String womsOccuredtime;
	private String womsReportedtime;	
	private String womsApprovaltime;
	private String womsAcceptedtime;	
	private String womsProposedstarttime;
	private String womsProposedendtime;	
	private String womsRescheduletime;
	private String womsReschedulestarttime;
	private String womsRescheduleendtime;
	private String womsAllottedtime;
	private String womsRelateto;
	private String womsActtype;
	private String womsExceptedreturntime;
	private String womsWoapprovaltime;
	private String womsMachinereleasetime;
	private String womsWorkendtime;
	private String womsWorkstarttime;
	private String womsFinalaction;
	private String womsCountermeasure;
	private String womsRootcause;
	private String womsProductionstarttime;
	
	private String Reporteddateflag;
	private String womsRequestapproveddate;
	private String Requestapproveddateflag;
	private String womsAccepteddate;
	private String Accepteddateflag;
	private String womsRescheduledate;
	private String Rescheduledateflag;
	private String womsAllotteddate;
	private String allotteddateflag;
	private String WomsWorkstartdate;
	private String Completeddateflag;
	private String Allotdateflag;
	private String WStdateflag;
	private String WomsProductionstartdate;
	private String allottedBy;
	private String occuredDateTime;
	private String reportedDateTime;
	private String allottedDateTime;
	private String acceptedDateTime;
	
	private String requiredStartTime;
	private String requiredEndTime;
	
	private String womsCompleteiontime;
	private String womsTechnicomptime;
	private String womsBusiComptime;
	
	public WOFormBean()
	{
		this.formMode = "CREATE";
	}
	public WOFormBean(FormModes mode,String woMode)
	{
		formModeSettings(mode,woMode);
	}

	private void formModeSettings(FormModes mode,String woMode){
		this.formModes = mode;
		CommonMessage.debugMsg("Bean : "+mode);
		if( mode == FormModes.view ){
			CommonMessage.debugMsg("Bean   WOMode: "+woMode);
			if(woMode.equals(WOConstants.woRequest))
			{
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disableOccurreddate = this.disableOccuredtime =  this.disableReporteddate = this.disableReportedtime = true;
				this.disablePriority = this.disableAssemblyid =  this.disableMachinecondition = this.disableRelatedto = true;
				this.disableMouldid = this.disableProblem =  this.disableAlarmno = this.disableSubassemblyid = true;
				this.disablePartlocation = this.disableSpareid =  this.disableFailuretypeid = this.disableLocation = true;
				this.disablePhenomenaid = this.disableCauseid = this.disableActivitytype=true;
				this.disableMachineid = this.disableCostcenterid = this.disableStatus = this.disableBookingremarks = this.disableBookedby = true;
				this.disableTradeid =  this.disableShiftdate = this.disableShifttime = this.disableShiftid = true;
			}
			else if(woMode.equals(WOConstants.woApproval))
			{
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disableRequestapproved = this.disableRequestcancel = this.disableRequestapproveddate = this.disableApprovaltime = true;
				this.disableRequestapprovedby = this.disableRequestapprovremarks = true;
			}
			else if(woMode.equals(WOConstants.woCreation))
			{
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disableAcceptedflag = 	this.disableAccepteddate = 	this.disableAcceptedtime = true;
				this.disableAcceptedby = this.disableProductionapproval = this.disableProposedstflag = true;
				this.disableProposedstartdate = this.disableProposedstarttime = this.disableProposedendflag = true;
				this.disableProposedenddate = this.disableProposedendtime = this.disablePermitReqd = true;
				this.disablePermitNotReqd = this.disableSafetypermitsrequried = this.disableAcceptedremarks = true;
			}
			else if(woMode.equals(WOConstants.woAcceptance))
			{
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disablePropstartdate = this.disablePropstarttime = this.disablePropenddate = this.disablePropendtime = true;
				this.disableRescheduledate = this.disableRescheduletime = this.disableRescheduleby = true;
				this.disablePermitaccept = this.disablePermitreschedule = this.disableRescheduledstflag = true;
				this.disableReschedulestartdate = this.disableReschedulestarttime = this.disableRescheduledendflag = true;
				this.disableRescheduleenddate = this.disableRescheduleendtime = this.disableRescheduleremarks = true;
			}
			else if(woMode.equals(WOConstants.woAllocation))
			{
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disableRelatedto = this.disableActivitytype=true;
				this.disableAllottedto = this.disableAllottedflag = this.disableAllotteddate = true;
				this.disableAllottedtime = 	this.disableallottedBy = this.disableAllottedremarks = true;
			}
			else if(woMode.equals(WOConstants.woCompletion))
			{
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disableFinalactivity=this.disableActivityid=this.disableWorkstartflag=true;
				this.disableWorkstartdate=this.disableWorkstarttime=this.disableIntorextequip=true;
				this.disableIntorextequipdesc=this.disableWorkendflag=this.disableWorkenddate=true;
				this.disableWorkendtime=this.disableWoapprovalflag=this.disableWoapprovaldate=true;
				this.disableWoapprovaltime=	this.disableWoapprovalby=this.disableMachinereleaseflag=true;
				this.disableMachinereleasedate=	this.disableMachinereleasetime=	this.disableMachinereleaseby=true;
				this.disableDoneby = this.disableFinalaction = this.disableCountermeasure=this.disableRootcause=true;//this.disableExceptedreturndate = this.disableExceptedreturntime = true;
				//this.disableRemarks = true;			
			}
			else if(woMode.equals(WOConstants.woProdAcceptance))
			{
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disableProductionby = this.disableProductionstartflag = this.disableProductionstartdate = true;
				this.disableProductionstarttime=this.disableProductionremarks =this.disableSafetypermitsignoff =true;			
			}
		}
		else
			this.formActionMode = FormModeConsts.create;
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
	public String getWoMode() {
		return woMode;
	}
	public void setWoMode(String woMode) {
		this.woMode = woMode;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public FormModes getFormModes() {
		return formModes;
	}
	public void setFormModes(FormModes formModes) {
		this.formModes = formModes;
	}
	public String getShift() {
		return shift;
	}
	public String getShiftdate() {
		return Shiftdate;
	}
	public void setShiftdate(String shiftdate) {
		Shiftdate = shiftdate;
	}
	public String getShifttime() {
		return Shifttime;
	}
	public void setShifttime(String shifttime) {
		Shifttime = shifttime;
	}
	public String getShiftid() {
		return Shiftid;
	}
	public void setShiftid(String shiftid) {
		Shiftid = shiftid;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableOccurreddate() {
		return disableOccurreddate;
	}
	public boolean isDisableBookedby() {
		return disableBookedby;
	}
	public void setDisableBookedby(boolean disableBookedby) {
		this.disableBookedby = disableBookedby;
	}
	public void setDisableOccurreddate(boolean disableOccurreddate) {
		this.disableOccurreddate = disableOccurreddate;
	}
	public boolean isDisableShiftdate() {
		return disableShiftdate;
	}
	public void setDisableShiftdate(boolean disableShiftdate) {
		this.disableShiftdate = disableShiftdate;
	}
	public boolean isDisableShifttime() {
		return disableShifttime;
	}
	public void setDisableShifttime(boolean disableShifttime) {
		this.disableShifttime = disableShifttime;
	}
	public boolean isDisableShiftid() {
		return disableShiftid;
	}
	public void setDisableShiftid(boolean disableShiftid) {
		this.disableShiftid = disableShiftid;
	}
	public boolean isDisableOccuredtime() {
		return disableOccuredtime;
	}
	public void setDisableOccuredtime(boolean disableOccuredtime) {
		this.disableOccuredtime = disableOccuredtime;
	}
	public boolean isDisableReporteddate() {
		return disableReporteddate;
	}
	public void setDisableReporteddate(boolean disableReporteddate) {
		this.disableReporteddate = disableReporteddate;
	}
	public boolean isDisableReportedtime() {
		return disableReportedtime;
	}
	public void setDisableReportedtime(boolean disableReportedtime) {
		this.disableReportedtime = disableReportedtime;
	}
	public boolean isDisablePriority() {
		return disablePriority;
	}
	public void setDisablePriority(boolean disablePriority) {
		this.disablePriority = disablePriority;
	}
	public boolean isDisableAssemblyid() {
		return disableAssemblyid;
	}
	public void setDisableAssemblyid(boolean disableAssemblyid) {
		this.disableAssemblyid = disableAssemblyid;
	}
	public boolean isDisableActivitytype() {
		return disableActivitytype;
	}
	public void setDisableActivitytype(boolean disableActivitytype) {
		this.disableActivitytype = disableActivitytype;
	}
	public boolean isDisableMachinecondition() {
		return disableMachinecondition;
	}
	public void setDisableMachinecondition(boolean disableMachinecondition) {
		this.disableMachinecondition = disableMachinecondition;
	}
	public boolean isDisableRelatedto() {
		return disableRelatedto;
	}
	public void setDisableRelatedto(boolean disableRelatedto) {
		this.disableRelatedto = disableRelatedto;
	}
	public boolean isDisableMouldid() {
		return disableMouldid;
	}
	public void setDisableMouldid(boolean disableMouldid) {
		this.disableMouldid = disableMouldid;
	}
	public boolean isDisableProblem() {
		return disableProblem;
	}
	public void setDisableProblem(boolean disableProblem) {
		this.disableProblem = disableProblem;
	}
	public boolean isDisableAlarmno() {
		return disableAlarmno;
	}
	public void setDisableAlarmno(boolean disableAlarmno) {
		this.disableAlarmno = disableAlarmno;
	}
	public boolean isDisableSubassemblyid() {
		return disableSubassemblyid;
	}
	public void setDisableSubassemblyid(boolean disableSubassemblyid) {
		this.disableSubassemblyid = disableSubassemblyid;
	}
	public boolean isDisablePartlocation() {
		return disablePartlocation;
	}
	public void setDisablePartlocation(boolean disablePartlocation) {
		this.disablePartlocation = disablePartlocation;
	}
	public boolean isDisableSpareid() {
		return disableSpareid;
	}
	public void setDisableSpareid(boolean disableSpareid) {
		this.disableSpareid = disableSpareid;
	}
	public boolean isDisableFailuretypeid() {
		return disableFailuretypeid;
	}
	public void setDisableFailuretypeid(boolean disableFailuretypeid) {
		this.disableFailuretypeid = disableFailuretypeid;
	}
	public boolean isDisableLocation() {
		return disableLocation;
	}
	public void setDisableLocation(boolean disableLocation) {
		this.disableLocation = disableLocation;
	}
	public boolean isDisablePhenomenaid() {
		return disablePhenomenaid;
	}
	public void setDisablePhenomenaid(boolean disablePhenomenaid) {
		this.disablePhenomenaid = disablePhenomenaid;
	}
	public boolean isDisableCauseid() {
		return disableCauseid;
	}
	public void setDisableCauseid(boolean disableCauseid) {
		this.disableCauseid = disableCauseid;
	}
	public boolean isDisableTradeid() {
		return disableTradeid;
	}
	public void setDisableTradeid(boolean disableTradeid) {
		this.disableTradeid = disableTradeid;
	}
	/*public boolean isDisableTempfield10() {
		return disableTempfield10;
	}
	public void setDisableTempfield10(boolean disableTempfield10) {
		this.disableTempfield10 = disableTempfield10;
	}*/
	public boolean isDisableRequestapproved() {
		return disableRequestapproved;
	}
	public void setDisableRequestapproved(boolean disableRequestapproved) {
		this.disableRequestapproved = disableRequestapproved;
	}
	public boolean isDisableRequestcancel() {
		return disableRequestcancel;
	}
	public void setDisableRequestcancel(boolean disableRequestcancel) {
		this.disableRequestcancel = disableRequestcancel;
	}
	public boolean isDisableRequestapproveddate() {
		return disableRequestapproveddate;
	}
	public void setDisableRequestapproveddate(boolean disableRequestapproveddate) {
		this.disableRequestapproveddate = disableRequestapproveddate;
	}
	public boolean isDisableRequestapprovedby() {
		return disableRequestapprovedby;
	}
	public void setDisableRequestapprovedby(boolean disableRequestapprovedby) {
		this.disableRequestapprovedby = disableRequestapprovedby;
	}
	public boolean isDisableRequestapprovremarks() {
		return disableRequestapprovremarks;
	}
	public void setDisableRequestapprovremarks(boolean disableRequestapprovremarks) {
		this.disableRequestapprovremarks = disableRequestapprovremarks;
	}
	public boolean isDisableAcceptedflag() {
		return disableAcceptedflag;
	}
	public void setDisableAcceptedflag(boolean disableAcceptedflag) {
		this.disableAcceptedflag = disableAcceptedflag;
	}
	public boolean isDisableAccepteddate() {
		return disableAccepteddate;
	}
	public void setDisableAccepteddate(boolean disableAccepteddate) {
		this.disableAccepteddate = disableAccepteddate;
	}
	public boolean isDisableAcceptedtime() {
		return disableAcceptedtime;
	}
	public void setDisableAcceptedtime(boolean disableAcceptedtime) {
		this.disableAcceptedtime = disableAcceptedtime;
	}
	public boolean isDisableAcceptedby() {
		return disableAcceptedby;
	}
	public void setDisableAcceptedby(boolean disableAcceptedby) {
		this.disableAcceptedby = disableAcceptedby;
	}
	public boolean isDisableProductionapproval() {
		return disableProductionapproval;
	}
	public void setDisableProductionapproval(boolean disableProductionapproval) {
		this.disableProductionapproval = disableProductionapproval;
	}
	public boolean isDisableProposedstflag() {
		return disableProposedstflag;
	}
	public void setDisableProposedstflag(boolean disableProposedstflag) {
		this.disableProposedstflag = disableProposedstflag;
	}
	public boolean isDisableProposedstartdate() {
		return disableProposedstartdate;
	}
	public void setDisableProposedstartdate(boolean disableProposedstartdate) {
		this.disableProposedstartdate = disableProposedstartdate;
	}
	public boolean isDisableProposedstarttime() {
		return disableProposedstarttime;
	}
	public void setDisableProposedstarttime(boolean disableProposedstarttime) {
		this.disableProposedstarttime = disableProposedstarttime;
	}
	public boolean isDisableProposedendflag() {
		return disableProposedendflag;
	}
	public void setDisableProposedendflag(boolean disableProposedendflag) {
		this.disableProposedendflag = disableProposedendflag;
	}
	public boolean isDisableProposedenddate() {
		return disableProposedenddate;
	}
	public void setDisableProposedenddate(boolean disableProposedenddate) {
		this.disableProposedenddate = disableProposedenddate;
	}
	public boolean isDisableProposedendtime() {
		return disableProposedendtime;
	}
	public void setDisableProposedendtime(boolean disableProposedendtime) {
		this.disableProposedendtime = disableProposedendtime;
	}
	public boolean isDisablePermitReqd() {
		return disablePermitReqd;
	}
	public void setDisablePermitReqd(boolean disablePermitReqd) {
		this.disablePermitReqd = disablePermitReqd;
	}
	public boolean isDisablePermitNotReqd() {
		return disablePermitNotReqd;
	}
	public void setDisablePermitNotReqd(boolean disablePermitNotReqd) {
		this.disablePermitNotReqd = disablePermitNotReqd;
	}
	public boolean isDisableSafetypermitsrequried() {
		return disableSafetypermitsrequried;
	}
	public void setDisableSafetypermitsrequried(boolean disableSafetypermitsrequried) {
		this.disableSafetypermitsrequried = disableSafetypermitsrequried;
	}
	public boolean isDisableAcceptedremarks() {
		return disableAcceptedremarks;
	}
	public void setDisableAcceptedremarks(boolean disableAcceptedremarks) {
		this.disableAcceptedremarks = disableAcceptedremarks;
	}
	public boolean isDisableBookingremarks() {
		return disableBookingremarks;
	}
	public void setDisableBookingremarks(boolean disableBookingremarks) {
		this.disableBookingremarks = disableBookingremarks;
	}
	
	public boolean isDisableApprovaltime() {
		return disableApprovaltime;
	}
	public void setDisableApprovaltime(boolean disableApprovaltime) {
		this.disableApprovaltime = disableApprovaltime;
	}
	
	public boolean isDisablePropstartdate() {
		return disablePropstartdate;
	}
	public void setDisablePropstartdate(boolean disablePropstartdate) {
		this.disablePropstartdate = disablePropstartdate;
	}
	public boolean isDisablePropstarttime() {
		return disablePropstarttime;
	}
	public void setDisablePropstarttime(boolean disablePropstarttime) {
		this.disablePropstarttime = disablePropstarttime;
	}
	public boolean isDisablePropenddate() {
		return disablePropenddate;
	}
	public void setDisablePropenddate(boolean disablePropenddate) {
		this.disablePropenddate = disablePropenddate;
	}
	public boolean isDisablePropendtime() {
		return disablePropendtime;
	}
	public void setDisablePropendtime(boolean disablePropendtime) {
		this.disablePropendtime = disablePropendtime;
	}
	public boolean isDisableRescheduledate() {
		return disableRescheduledate;
	}
	public void setDisableRescheduledate(boolean disableRescheduledate) {
		this.disableRescheduledate = disableRescheduledate;
	}
	public boolean isDisableRescheduletime() {
		return disableRescheduletime;
	}
	public void setDisableRescheduletime(boolean disableRescheduletime) {
		this.disableRescheduletime = disableRescheduletime;
	}
	public boolean isDisableRescheduleby() {
		return disableRescheduleby;
	}
	public void setDisableRescheduleby(boolean disableRescheduleby) {
		this.disableRescheduleby = disableRescheduleby;
	}
	public boolean isDisablePermitaccept() {
		return disablePermitaccept;
	}
	public void setDisablePermitaccept(boolean disablePermitaccept) {
		this.disablePermitaccept = disablePermitaccept;
	}
	public boolean isDisablePermitreschedule() {
		return disablePermitreschedule;
	}
	public void setDisablePermitreschedule(boolean disablePermitreschedule) {
		this.disablePermitreschedule = disablePermitreschedule;
	}
	public boolean isDisableRescheduledstflag() {
		return disableRescheduledstflag;
	}
	public void setDisableRescheduledstflag(boolean disableRescheduledstflag) {
		this.disableRescheduledstflag = disableRescheduledstflag;
	}
	public boolean isDisableReschedulestartdate() {
		return disableReschedulestartdate;
	}
	public void setDisableReschedulestartdate(boolean disableReschedulestartdate) {
		this.disableReschedulestartdate = disableReschedulestartdate;
	}
	public boolean isDisableReschedulestarttime() {
		return disableReschedulestarttime;
	}
	public void setDisableReschedulestarttime(boolean disableReschedulestarttime) {
		this.disableReschedulestarttime = disableReschedulestarttime;
	}
	public boolean isDisableRescheduledendflag() {
		return disableRescheduledendflag;
	}
	public void setDisableRescheduledendflag(boolean disableRescheduledendflag) {
		this.disableRescheduledendflag = disableRescheduledendflag;
	}
	public boolean isDisableRescheduleenddate() {
		return disableRescheduleenddate;
	}
	public void setDisableRescheduleenddate(boolean disableRescheduleenddate) {
		this.disableRescheduleenddate = disableRescheduleenddate;
	}
	public boolean isDisableRescheduleendtime() {
		return disableRescheduleendtime;
	}
	public void setDisableRescheduleendtime(boolean disableRescheduleendtime) {
		this.disableRescheduleendtime = disableRescheduleendtime;
	}
	public boolean isDisableRescheduleremarks() {
		return disableRescheduleremarks;
	}
	public void setDisableRescheduleremarks(boolean disableRescheduleremarks) {
		this.disableRescheduleremarks = disableRescheduleremarks;
	}
	public boolean isDisableAllottedto() {
		return disableAllottedto;
	}
	public void setDisableAllottedto(boolean disableAllottedto) {
		this.disableAllottedto = disableAllottedto;
	}
	public boolean isDisableAllottedflag() {
		return disableAllottedflag;
	}
	public void setDisableAllottedflag(boolean disableAllottedflag) {
		this.disableAllottedflag = disableAllottedflag;
	}
	public boolean isDisableAllotteddate() {
		return disableAllotteddate;
	}
	public void setDisableAllotteddate(boolean disableAllotteddate) {
		this.disableAllotteddate = disableAllotteddate;
	}
	public boolean isDisableAllottedtime() {
		return disableAllottedtime;
	}
	public void setDisableAllottedtime(boolean disableAllottedtime) {
		this.disableAllottedtime = disableAllottedtime;
	}
	public boolean isDisableallottedBy() {
		return disableallottedBy;
	}
	public void setDisableallottedby(boolean disableallottedBy) {
		this.disableallottedBy = disableallottedBy;
	}
	public boolean isDisableAllottedremarks() {
		return disableAllottedremarks;
	}
	public void setDisableAllottedremarks(boolean disableAllottedremarks) {
		this.disableAllottedremarks = disableAllottedremarks;
	}
	public boolean isDisableDoneby() {
		return disableDoneby;
	}
	public void setDisableDoneby(boolean disableDoneby) {
		this.disableDoneby = disableDoneby;
	}
	public boolean isDisableFinalactivity() {
		return disableFinalactivity;
	}
	public void setDisableFinalactivity(boolean disableFinalactivity) {
		this.disableFinalactivity = disableFinalactivity;
	}
	public boolean isDisableActivityid() {
		return disableActivityid;
	}
	public void setDisableActivityid(boolean disableActivityid) {
		this.disableActivityid = disableActivityid;
	}
	public boolean isDisableWorkstartflag() {
		return disableWorkstartflag;
	}
	public void setDisableWorkstartflag(boolean disableWorkstartflag) {
		this.disableWorkstartflag = disableWorkstartflag;
	}
	public boolean isDisableWorkstartdate() {
		return disableWorkstartdate;
	}
	public void setDisableWorkstartdate(boolean disableWorkstartdate) {
		this.disableWorkstartdate = disableWorkstartdate;
	}
	public boolean isDisableWorkstarttime() {
		return disableWorkstarttime;
	}
	public void setDisableWorkstarttime(boolean disableWorkstarttime) {
		this.disableWorkstarttime = disableWorkstarttime;
	}
	public boolean isDisableIntorextequip() {
		return disableIntorextequip;
	}
	public void setDisableIntorextequip(boolean disableIntorextequip) {
		this.disableIntorextequip = disableIntorextequip;
	}
	public boolean isDisableIntorextequipdesc() {
		return disableIntorextequipdesc;
	}
	public void setDisableIntorextequipdesc(boolean disableIntorextequipdesc) {
		this.disableIntorextequipdesc = disableIntorextequipdesc;
	}
	public boolean isDisableWorkendflag() {
		return disableWorkendflag;
	}
	public void setDisableWorkendflag(boolean disableWorkendflag) {
		this.disableWorkendflag = disableWorkendflag;
	}
	public boolean isDisableWorkenddate() {
		return disableWorkenddate;
	}
	public void setDisableWorkenddate(boolean disableWorkenddate) {
		this.disableWorkenddate = disableWorkenddate;
	}
	public boolean isDisableWorkendtime() {
		return disableWorkendtime;
	}
	public void setDisableWorkendtime(boolean disableWorkendtime) {
		this.disableWorkendtime = disableWorkendtime;
	}
	public boolean isDisableWoapprovalflag() {
		return disableWoapprovalflag;
	}
	public void setDisableWoapprovalflag(boolean disableWoapprovalflag) {
		this.disableWoapprovalflag = disableWoapprovalflag;
	}
	public boolean isDisableWoapprovaldate() {
		return disableWoapprovaldate;
	}
	public void setDisableWoapprovaldate(boolean disableWoapprovaldate) {
		this.disableWoapprovaldate = disableWoapprovaldate;
	}
	public boolean isDisableWoapprovaltime() {
		return disableWoapprovaltime;
	}
	public void setDisableWoapprovaltime(boolean disableWoapprovaltime) {
		this.disableWoapprovaltime = disableWoapprovaltime;
	}
	public boolean isDisableWoapprovalby() {
		return disableWoapprovalby;
	}
	public void setDisableWoapprovalby(boolean disableWoapprovalby) {
		this.disableWoapprovalby = disableWoapprovalby;
	}
	public boolean isDisableMachinereleaseflag() {
		return disableMachinereleaseflag;
	}
	public void setDisableMachinereleaseflag(boolean disableMachinereleaseflag) {
		this.disableMachinereleaseflag = disableMachinereleaseflag;
	}
	public boolean isDisableMachinereleasedate() {
		return disableMachinereleasedate;
	}
	public void setDisableMachinereleasedate(boolean disableMachinereleasedate) {
		this.disableMachinereleasedate = disableMachinereleasedate;
	}
	public boolean isDisableMachinereleasetime() {
		return disableMachinereleasetime;
	}
	public void setDisableMachinereleasetime(boolean disableMachinereleasetime) {
		this.disableMachinereleasetime = disableMachinereleasetime;
	}
	public boolean isDisableMachinereleaseby() {
		return disableMachinereleaseby;
	}
	public void setDisableMachinereleaseby(boolean disableMachinereleaseby) {
		this.disableMachinereleaseby = disableMachinereleaseby;
	}
	public boolean isDisableExceptedreturndate() {
		return disableExceptedreturndate;
	}
	public void setDisableExceptedreturndate(boolean disableExceptedreturndate) {
		this.disableExceptedreturndate = disableExceptedreturndate;
	}
	public boolean isDisableExceptedreturntime() {
		return disableExceptedreturntime;
	}
	public void setDisableExceptedreturntime(boolean disableExceptedreturntime) {
		this.disableExceptedreturntime = disableExceptedreturntime;
	}
	public boolean isDisableRemarks() {
		return disableRemarks;
	}
	public void setDisableRemarks(boolean disableRemarks) {
		this.disableRemarks = disableRemarks;
	}	
	public boolean isDisableFinalaction() {
		return disableFinalaction;
	}
	public void setDisableFinalaction(boolean disableFinalaction) {
		this.disableFinalaction = disableFinalaction;
	}
	public boolean isDisableCountermeasure() {
		return disableCountermeasure;
	}
	public void setDisableCountermeasure(boolean disableCountermeasure) {
		this.disableCountermeasure = disableCountermeasure;
	}
	public boolean isDisableRootcause() {
		return disableRootcause;
	}
	public void setDisableRootcause(boolean disableRootcause) {
		this.disableRootcause = disableRootcause;
	}
	public boolean isDisableProductionby() {
		return disableProductionby;
	}
	public void setDisableProductionby(boolean disableProductionby) {
		this.disableProductionby = disableProductionby;
	}
	public boolean isDisableProductionstartflag() {
		return disableProductionstartflag;
	}
	public void setDisableProductionstartflag(boolean disableProductionstartflag) {
		this.disableProductionstartflag = disableProductionstartflag;
	}
	public boolean isDisableProductionstartdate() {
		return disableProductionstartdate;
	}
	public void setDisableProductionstartdate(boolean disableProductionstartdate) {
		this.disableProductionstartdate = disableProductionstartdate;
	}
	public boolean isDisableProductionstarttime() {
		return disableProductionstarttime;
	}
	public void setDisableProductionstarttime(boolean disableProductionstarttime) {
		this.disableProductionstarttime = disableProductionstarttime;
	}
	public boolean isDisableProductionremarks() {
		return disableProductionremarks;
	}
	public void setDisableProductionremarks(boolean disableProductionremarks) {
		this.disableProductionremarks = disableProductionremarks;
	}
	public boolean isDisableSafetypermitsignoff() {
		return disableSafetypermitsignoff;
	}
	public void setDisableSafetypermitsignoff(boolean disableSafetypermitsignoff) {
		this.disableSafetypermitsignoff = disableSafetypermitsignoff;
	}
	public boolean isDisableMachineid() {
		return disableMachineid;
	}
	public void setDisableMachineid(boolean disableMachineid) {
		this.disableMachineid = disableMachineid;
	}
	public boolean isDisableCostcenterid() {
		return disableCostcenterid;
	}
	public void setDisableCostcenterid(boolean disableCostcenterid) {
		this.disableCostcenterid = disableCostcenterid;
	}
	public boolean isDisableStatus() {
		return disableStatus;
	}
	public void setDisableStatus(boolean disableStatus) {
		this.disableStatus = disableStatus;
	}
	public String getWomsOccuredtime() {
		return womsOccuredtime;
	}
	public String getWomsBookedby() {
		return womsBookedby;
	}
	public void setWomsBookedby(String womsBookedby) {
		this.womsBookedby = womsBookedby;
	}
	public void setWomsOccuredtime(String womsOccuredtime) {
		this.womsOccuredtime = womsOccuredtime;
	}
	public String getWomsReportedtime() {
		return womsReportedtime;
	}
	public void setWomsReportedtime(String womsReportedtime) {
		this.womsReportedtime = womsReportedtime;
	}
	public String getWomsApprovaltime() {
		return womsApprovaltime;
	}
	public void setWomsApprovaltime(String womsApprovaltime) {
		this.womsApprovaltime = womsApprovaltime;
	}
	public String getWomsAcceptedtime() {
		return womsAcceptedtime;
	}
	public void setWomsAcceptedtime(String womsAcceptedtime) {
		this.womsAcceptedtime = womsAcceptedtime;
	}	
	public String getWomsProposedstarttime() {
		return womsProposedstarttime;
	}
	public void setWomsProposedstarttime(String womsProposedstarttime) {
		this.womsProposedstarttime = womsProposedstarttime;
	}
	public String getWomsProposedendtime() {
		return womsProposedendtime;
	}
	public void setWomsProposedendtime(String womsProposedendtime) {
		this.womsProposedendtime = womsProposedendtime;
	}
	public String getWomsRescheduletime() {
		return womsRescheduletime;
	}
	public void setWomsRescheduletime(String womsRescheduletime) {
		this.womsRescheduletime = womsRescheduletime;
	}
	public String getWomsReschedulestarttime() {
		return womsReschedulestarttime;
	}
	public void setWomsReschedulestarttime(String womsReschedulestarttime) {
		this.womsReschedulestarttime = womsReschedulestarttime;
	}
	public String getWomsRescheduleendtime() {
		return womsRescheduleendtime;
	}
	public void setWomsRescheduleendtime(String womsRescheduleendtime) {
		this.womsRescheduleendtime = womsRescheduleendtime;
	}
	public String getWomsAllottedtime() {
		return womsAllottedtime;
	}
	public void setWomsAllottedtime(String womsAllottedtime) {
		this.womsAllottedtime = womsAllottedtime;
	}
	public String getWomsExceptedreturntime() {
		return womsExceptedreturntime;
	}
	public void setWomsExceptedreturntime(String womsExceptedreturntime) {
		this.womsExceptedreturntime = womsExceptedreturntime;
	}
	public String getWomsWoapprovaltime() {
		return womsWoapprovaltime;
	}
	public void setWomsWoapprovaltime(String womsWoapprovaltime) {
		this.womsWoapprovaltime = womsWoapprovaltime;
	}
	public String getWomsMachinereleasetime() {
		return womsMachinereleasetime;
	}
	public void setWomsMachinereleasetime(String womsMachinereleasetime) {
		this.womsMachinereleasetime = womsMachinereleasetime;
	}
	public String getWomsWorkendtime() {
		return womsWorkendtime;
	}
	public void setWomsWorkendtime(String womsWorkendtime) {
		this.womsWorkendtime = womsWorkendtime;
	}
	public String getWomsWorkstarttime() {
		return womsWorkstarttime;
	}
	public void setWomsWorkstarttime(String womsWorkstarttime) {
		this.womsWorkstarttime = womsWorkstarttime;
	}
	public String getWomsFinalaction() {
		return womsFinalaction;
	}
	public void setWomsFinalaction(String womsFinalaction) {
		this.womsFinalaction = womsFinalaction;
	}
	public String getWomsCountermeasure() {
		return womsCountermeasure;
	}
	public void setWomsCountermeasure(String womsCountermeasure) {
		this.womsCountermeasure = womsCountermeasure;
	}
	public String getWomsRootcause() {
		return womsRootcause;
	}
	public void setWomsRootcause(String womsRootcause) {
		this.womsRootcause = womsRootcause;
	}
	public String getWomsProductionstarttime() {
		return womsProductionstarttime;
	}
	public void setWomsProductionstarttime(String womsProductionstarttime) {
		this.womsProductionstarttime = womsProductionstarttime;
	}
	public String getWomsRequestapproveddate() {
		return womsRequestapproveddate;
	}
	public void setWomsRequestapproveddate(String womsRequestapproveddate) {
		this.womsRequestapproveddate = womsRequestapproveddate;
	}
	public String getReporteddateflag() {
		return Reporteddateflag;
	}
	public void setReporteddateflag(String reporteddateflag) {
		Reporteddateflag = reporteddateflag;
	}
	public String getRequestapproveddateflag() {
		return Requestapproveddateflag;
	}
	public void setRequestapproveddateflag(String requestapproveddateflag) {
		Requestapproveddateflag = requestapproveddateflag;
	}
	public String getWomsAccepteddate() {
		return womsAccepteddate;
	}
	public void setWomsAccepteddate(String womsAccepteddate) {
		this.womsAccepteddate = womsAccepteddate;
	}
	public String getAccepteddateflag() {
		return Accepteddateflag;
	}
	public void setAccepteddateflag(String accepteddateflag) {
		Accepteddateflag = accepteddateflag;
	}
	public String getWomsRescheduledate() {
		return womsRescheduledate;
	}
	public void setWomsRescheduledate(String womsRescheduledate) {
		this.womsRescheduledate = womsRescheduledate;
	}
	public String getRescheduledateflag() {
		return Rescheduledateflag;
	}
	public void setRescheduledateflag(String rescheduledateflag) {
		Rescheduledateflag = rescheduledateflag;
	}
	public String getWomsAllotteddate() {
		return womsAllotteddate;
	}
	public void setWomsAllotteddate(String womsAllotteddate) {
		this.womsAllotteddate = womsAllotteddate;
	}
	public String getAllotteddateflag() {
		return allotteddateflag;
	}
	public void setAllotteddateflag(String allotteddateflag) {
		this.allotteddateflag = allotteddateflag;
	}
	public String getWomsRelateto() {
		return womsRelateto;
	}
	public void setWomsRelateto(String womsRelateto) {
		this.womsRelateto = womsRelateto;
	}
	public String getWomsActtype() {
		return womsActtype;
	}
	public void setWomsActtype(String womsActtype) {
		this.womsActtype = womsActtype;
	}
	public String getWomsWorkstartdate() {
		return WomsWorkstartdate;
	}
	public void setWomsWorkstartdate(String womsWorkstartdate) {
		WomsWorkstartdate = womsWorkstartdate;
	}
	public String getCompleteddateflag() {
		return Completeddateflag;
	}
	public void setCompleteddateflag(String completeddateflag) {
		Completeddateflag = completeddateflag;
	}
	public String getAllotdateflag() {
		return Allotdateflag;
	}
	public void setAllotdateflag(String allotdateflag) {
		Allotdateflag = allotdateflag;
	}
	public String getWStdateflag() {
		return WStdateflag;
	}
	public void setWStdateflag(String wStdateflag) {
		WStdateflag = wStdateflag;
	}
	public String getWomsProductionstartdate() {
		return WomsProductionstartdate;
	}
	public void setWomsProductionstartdate(String womsProductionstartdate) {
		WomsProductionstartdate = womsProductionstartdate;
	}
	public String getAllottedBy() {
		return allottedBy;
	}
	public void setAllottedBy(String allottedBy) {
		this.allottedBy = allottedBy;
	}
	public String getOccuredDateTime() {
		return occuredDateTime;
	}
	public void setOccuredDateTime(String occuredDateTime) {
		this.occuredDateTime = occuredDateTime;
	}
	public String getReportedDateTime() {
		return reportedDateTime;
	}
	public void setReportedDateTime(String reportedDateTime) {
		this.reportedDateTime = reportedDateTime;
	}
	public String getAllottedDateTime() {
		return allottedDateTime;
	}
	public void setAllottedDateTime(String allottedDateTime) {
		this.allottedDateTime = allottedDateTime;
	}
	public String getRequiredStartTime() {
		return requiredStartTime;
	}
	public void setRequiredStartTime(String requiredStartTime) {
		this.requiredStartTime = requiredStartTime;
	}
	
	public String getRequiredEndTime() {
		return requiredEndTime;
	}
	public void setRequiredEndTime(String requiredEndTime) {
		this.requiredEndTime = requiredEndTime;
	}
	public String getAcceptedDateTime() {
		return acceptedDateTime;
	}
	public void setAcceptedDateTime(String acceptedDateTime) {
		this.acceptedDateTime = acceptedDateTime;
	}
	
	public String getMandFieldFlag() {
		return MandFieldFlag;
	}
	public void setMandFieldFlag(String mandFieldFlag) {
		MandFieldFlag = mandFieldFlag;
	}
	public boolean isDisableProcess() {
		return disableProcess;
	}
	public void setDisableProcess(boolean disableProcess) {
		this.disableProcess = disableProcess;
	}
	public boolean isDisableWorkCenterid() {
		return disableWorkCenterid;
	}
	public void setDisableWorkCenterid(boolean disableWorkCenterid) {
		this.disableWorkCenterid = disableWorkCenterid;
	}
	public boolean isDisablePlannergroup() {
		return disablePlannergroup;
	}
	public void setDisablePlannergroup(boolean disablePlannergroup) {
		this.disablePlannergroup = disablePlannergroup;
	}
	
	public String getWomsCompleteiontime() {
		return womsCompleteiontime;
	}
	public void setWomsCompleteiontime(String womsCompleteiontime) {
		this.womsCompleteiontime = womsCompleteiontime;
	}
	
	public String getWomsTechnicomptime() {
		return womsTechnicomptime;
	}
	public void setWomsTechnicomptime(String womsTechnicomptime) {
		this.womsTechnicomptime = womsTechnicomptime;
	}

	public String getWomsBusiComptime() {
		return womsBusiComptime;
	}
	public void setWomsBusiComptime(String womsBusiComptime) {
		this.womsBusiComptime = womsBusiComptime;
	}

}
