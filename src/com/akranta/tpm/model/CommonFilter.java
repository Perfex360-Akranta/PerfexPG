package com.akranta.tpm.model;

import java.util.List;

import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.utils.CommonMessage;
public class CommonFilter {
	private List<CommonFilter> BreakUpList ;
	private String emmLinkKeyId;
	private String dmt;
	private String jh;
	private String Breakup;
	private String JhTemplateId;	
	private String quality;
	private String program;
	private String topicid;
	private String uniquePos;
	private String drillLevelNo;
	private ComboFilter company;
	private ComboFilter sapLocation;
	private ComboFilter factory;
	private ComboFilter sbu;
	private String MPWorthy;
	private String UtiliseFuture;
	private ComboFilter checkType;
	private ComboFilter pbu;
	private ComboFilter section;
	private String flid;
	private String elementId;
	private String kznBankType;
	private String colIndexName;
	private String Refdocid;
	private String Dmtdetailid;
	private String empwiseType;
	private String assType;
	private ComboFilter costCenter;
	private ComboFilter location;
	private ComboFilter cell;
	private ComboFilter subcell;
	private ComboFilter machine;
	private ComboFilter mould;
	private ComboFilter circle;
	private ComboFilter trade;
	private ComboFilter supplier;
	private ComboFilter assembly;	
	private ComboFilter eqpGroup; 
	private ComboFilter eqpSubGrp;/* Added ByDhanalakshmi.R*/
	private ComboFilter production; 
	private ComboFilter jhStep; 
	private ComboFilter spare;
	private ComboFilter machineRank; 
	private ComboFilter jobType; 
	private ComboFilter purpose;
	private ComboFilter pmSource; 
	private ComboFilter completedBy;
	private ComboFilter actType;
	private ComboFilter bdstatus;
	private ComboFilter counterMeasure;
	private ComboFilter subassembly;
	private char isForTotalCnt = 'N';
	private Character rowTotal;
	private Character active;    //Machine Master Main grid Active Inactive Form
	private String fromDate;
	private String pldetailsId;
	private String toDate;
	private String Header2;
	private String amcFrom;
	private String amcTo;
	private String FREQ;
	private String BFROM;
	private String BTO;
	private String status;
	private String relatedToMchMld;//for commonFilter Related To filter
	private ComboFilter supervisor;
	private ComboFilter shift;
	private ComboFilter subCategory;
	private ComboFilter manufacture;
	private ComboFilter amcvendorId;
	private ComboFilter rawMatrial;
	private String roleLevel;
	private ComboFilter dectetedBy;// add filter to abnormality Report(Decteted By) by KarthicK.T
	private ComboFilter mechCond; //Machine Condition for PMStandard Report By KarthicK.T
	private String safetyMode;//Safety Mode for Incident Report
	private String maintMode;
	private String instalfrom;
	private String instalTo;
	private String amcrnewFrom;
	private String amcrenewTo;
	private String warrantyFrom;
	private String warrantyTo;
	private ComboFilter finalTrade;
	private ComboFilter workOrder;
	private ComboFilter skilName;
	private ComboFilter skilRating;  //Skill Rating for Skill checkList form by KarthicK.T
	private ComboFilter topic;//topic for Skill checkList form by KarthicK.T
	private String pcsDate; //created by KarthicK.T for PCS Summary report
	private ComboFilter allotedTo;
	private ComboFilter preparedBy;
	private String impact;
	private String type;
	private String spareReq;
	private ComboFilter category;
	private String qualityPhenomena;
	private String factoryId;
	private String sectionId;
	private String functionName;
	private String cellId;
	private String taskid;
	private String title;
	private String machineId;
	private ComboFilter sourceOfKPI; 
	private ComboFilter workorderNo;
	private ComboFilter unit;
	private ComboFilter subUnit;
	private ComboFilter workCenter;
	private ComboFilter reportedBy;
	private ComboFilter maintSection;
	private ComboFilter AbnImpType;
	private String abnImprovement;
	private String problem;
	private String prodAffected;
	private String occuredFrom;
	private String occuredTo;
	private String reportedFrom;
	private String reportedTo;
	private String columnId;
	private String permitReqFrom;
	private String permitReqTo;
	private String proposedFrom;
	private String proposedTo;
	private String rescheduleFrom;
	private String rescheduleTo;
	private String allotedFrom;
	private String allotedDtTo;
	private String wrkStartFrom;
	private String wrkStartTo;
	private String wrkEndFrom;
	private String wrkEndTo;
	private String wrkApprvlFrom;
	private String wrkApprvlTo;
	private String eqpReleasedFrom;
	private String eqpReleasedTo;
	private boolean assemblyDrillExist;
	private char drillFlag = 'f'; //it can be 'f'orward or 'b'ackward //
	private String firstLevel;
	private String prodFrom;
	private String OPLId;
	private boolean machineDirect;
	private String paramCode;  //Created by KarthicK.T for Production Loss Report
	private String mainGroup; // created by KarthicK.T for Horizondal Deployement Summary
	private String subGroupHD;// created by Mani for Horizondal Deployement Summary
	private String checkActivityType; // created by KarthicK.T for BDCost Report
	private String abnormalityType; //created by KarthicK.T for Abnormality,HTA,SOC Reports
	private String pcsShift; // Created by KarthicK.T for Production Loss Summary Report
	private String acceptenceRequired;
	private String groupByCircle;
	private String opltype;
	private String themelike;
	private String lessonlike;
	private String key;
	private String oplid;
	private String getKaizenkey;
	private String range;	
	private ComboFilter phenomena;
	private ComboFilter finalCause;
	private ComboFilter maintainChargeId;
	private ComboFilter subgroup;
	private Character pareto;//karthick.t for production loss analysis report
	private ComboFilter MANPOWERID;
	private ComboFilter GRADEID;
	private String paramtype;
	private String eqpActType;
	private String defaultFinYear;
	private String fromMonth;
	private String toMonth;
	private String year;
	private String ISNEEDWONO;//Production Loss Summary Rpt. Created:Karthick.T
	private String ISNEEDPROD;//Production Loss Summary Rpt. Created:Karthick.T
	private ComboFilter abnType;
	private ComboFilter abnCategory;
	private ComboFilter abnImpact;
	private ComboFilter abnStatus;
	private ComboFilter spoke;
	private ComboFilter topics;
	private ComboFilter abnJhStep;
	private ComboFilter abnClass;
	private String abnDetectBy;
	private String abnDetect;
	private String abnCause;
	private String abnCatch;
	private String abnImp;
	private String abnAllch;
	private String abnIsHSE;	
	private ComboFilter refNo;
	private ComboFilter detectedBy;
	private String dFromDate;
	private String dToDate;
	private String tgtFromDate;
	private String tgtToDate;
	private String completedFromDate;
	private String completedToDate;
	private String chkAssm;
	private String safetyPatrol;
	private String indicator;
	private String relatedtos;
	private String repeatedAbn;
	
	private String isFacultyLevel;
	// For any String field
	private String temp; 
	//Audit Related
	
	private ComboFilter cmbAuditorName;
	private ComboFilter AuditorLevel;
	private ComboFilter cboType;
	private ComboFilter cboStatus;
	private String AuditFromDate;
	private String AuditToDate;
	//BD Related
	private String bdActivity;
	private ComboFilter cmbBreakdown;	
	private ComboFilter cmbMsr;		
	private ComboFilter cmbFailureType;	
	private ComboFilter cmbdefectpheno;
	private ComboFilter cmbcause;
	private ComboFilter cmbbdRootCause;
	private ComboFilter cmbprodcngroup;
	private ComboFilter cmbstep;
	private ComboFilter cmbshiftIncharge;
	private String bdType;
	private String chkoccurchkbox;
	private String chktimeChkBox;
	private String chkzeroBdChkBox;
	private String chkallChkBox;
	private String chkremallchkbox;
	private String chkfachkbox;
	private String chkpillarchkbox;
	private String chkrcchkbox;
	private String chkrccchkbox;
	private String chkcmchkbox;
	private ComboFilter cost;
	private ComboFilter reportType;
	private String chkircchkbox;
	private String chkiyychkbox;
	private String txttop;
	private ComboFilter cbooptions;
	private ComboFilter cboparetooptions;//for breakdown pareto by manikandan
	private ComboFilter	cboactivitytype;//for general maintainence
	private String isForRowTotalRow;//for general maintainence report/graph added by mani
	private ComboFilter bdMechCond;//for activity wise rpt by KarthicK.T
	private ComboFilter activity;//for ActivityCount Report created by KarthicK.T
	private ComboFilter Responsibility;
	private String repotingType;
	private String completed;
	private String pending;
	private ComboFilter cmbEngineer;
	private ComboFilter cbosparesSelectBox;
	private ComboFilter cboselBdType;
	private ComboFilter cmbyy;
	private String chkjhchkbox;
	private String chkdesignchkbox;
	private String chkpmchkbox;
	private String chketchkbox;
	private String chktradewise;
	private String chkjobtypwise;
	private String chkemployee;
	private String chkcontract;	
	private String chkspare;	
	private String chkservice;
	private String chkutility;
	private String chkother;		
	private String chktotal;
	private String chkallparameter;	
	private String chkundefinedPP;
	private String chkrepeatedBD;
	private String woapproval;
	private String wostatus;
	private String woModeForGrid;
	private String MachineWise;
	private String EquipmentWise;
	private String chkSkipLine;
	private String chkActwise;	
	private String chkMonwise;
	private String chkInternal;	
	private String chkExternal;
	private ComboFilter cboJobType;
	private ComboFilter cboFrequency;
	private String wodetailid;
	private String chkGrpByEqp;	
	private String chkAllEqpmnt;
	
	//Calibration Related
	private ComboFilter imte;
	private ComboFilter slno;
	private String CalibFromDate;
	private String CalibToDate;
	private ComboFilter gauge;
	private ComboFilter parameter;
	private ComboFilter externalagencies;
	private ComboFilter method;
	private ComboFilter decision;
	private ComboFilter compltdby;
	private ComboFilter issuedto;
	private ComboFilter ssuedtocalib;
	private ComboFilter servicetype;
	private String exportchkbox;
	
	private String AmcRenewal;
	private String WarrantyExpries;
	
	private String jhfreq;
	private ComboFilter jhduration;
	private ComboFilter emp;
	//Man Power Utilization
	private ComboFilter team;
	private String teamId;
	private String teamLevelNo;
	private ComboFilter dept;
	private ComboFilter transtyp;
	private String minschkbox;
	private String hrschkbox;
	private String planchkbox;
	private String actualchkbox;
	private String chartType;
	//OPL & Kaizen
	private ComboFilter OplNoid;
	private String GrpByCellWise;
	private ComboFilter ImprovmntNoid;
	private ComboFilter Pillarid;
	private ComboFilter OplTypeid;
	private ComboFilter lossType;
	private String improvementDate;
	private String BK;
	private String IC;
	private String TC;
	private String DM;
	private String ET;
	private String JH;
	private String KK;
	private String OTpm;
	private String Pm;
	private String Qm;
	private String She;
	private String SectWise;
	private String CellWise;
	private String EqptWise;
	private String LossWise;
	private String PillarWise;
	private String boxResultWise;
	private String EqptGrpWise;
	private ComboFilter JHKaizenNo;
	private ComboFilter JHKaizencategory;
	private ComboFilter JhTemplate;
	//PCS Related
	private ComboFilter cmbpcssubgrp;
	private ComboFilter cmbpcsprrod;
	private String fromPcs;
	private String chkboxef;
	private String chkboxdf;
	private String chkboxar;
	private String chkboxpr;
	private String chkboxqr;
	private String chkboxoee;
	private String chkboxall; 
	private String chkboxoccurence;
	private String chkboxtime;
	private String chkboxvai;
	private String chkboxhour;
	private String chkboxday;
	private String chkboxweek;
	private String chkboxshift;
	private String chkboxMgrCal;
	private String pcsshift;
	private String RemoveBlank;
	private String subLoss;
	private String lossRptType;
	private String multiLoss;
	//pm Related
	private ComboFilter jobtype;
	private ComboFilter activities;	
	private ComboFilter tools;
	private ComboFilter sources;
	private String freq;
	private String noofdays;
	private ComboFilter pmsource;
	private ComboFilter eqpCondn;
	private String includestatus;
	private String chkActType;//PM Related by KarthicK.T
	
	//private ComboFilter pmstatus;
	private String pmstatus;
	private ComboFilter drillfor;
	private String remblank;
	private String comp;
	private String pend;	
	private String contractor;
	private String service;
	private String util;
	private String other;
	private String total;
	private String ap;
	private String tw;
	private String jtw;
	private ComboFilter pmjobtype;
	private ComboFilter param;
	private String actwise;
	private String monwise;
	private String awise;
	private String atype;
	private String mwise;
	private String summary;
	private String sect;
	private String cellch;
	private String eqpmnt;
	private String empch;
	private String Spares;
	//Quality
	private ComboFilter process;
	private ComboFilter complaintno;
	private ComboFilter complainttype;
	private ComboFilter inspectedby;
	private String qtmProductId;
	private ComboFilter custID;
	private ComboFilter product;//created by KarthicK.T for QmBase Line Rpt
	private ComboFilter defactparam;
	private ComboFilter defactPhenamena;
	private ComboFilter recordedby;
	private ComboFilter inspection;
	private ComboFilter qtmCause;
	private ComboFilter productModel;
	private ComboFilter productDesc;
	private String impdone;
	private String rejection;
	private String isMchwise;
	private String chkdvp;
	private String chkdvm;
	private String chkinstance;
	private String chkquantity;
	private String chkdpc;
	private String phenomenaId;
	private String chkjhbox;
	private String chkpmbox;
	private String chkkkbox;
	private String chketbox;
	private String chk4mbox;
	private String chkimpdonebox;
	private ComboFilter qtyystatus;
	private String skipAssm;
	private String docInsType;
	private String docType;
	//Safety Related
	private ComboFilter agno;
	private ComboFilter safetysubtype;
	private ComboFilter incidentno;
	private ComboFilter incidenttype;
	private String incdntFromDate;
	private String incdntdToDate;
	private ComboFilter improvmnt;
	private ComboFilter safetytype;
	private ComboFilter priority;
	private ComboFilter employee;
	private ComboFilter bodypart;
	private ComboFilter injurytype;
	private ComboFilter relatedto;
	private ComboFilter reptype;
	private ComboFilter workarea;
	private String instance;
	private String mdl;
	private String sftojt;
	private String sftpokayoke;
	private String sftmin;
	private String sftmaj;
	private String sftkzn;
	private String sftopl;
	//Spares Related
	private ComboFilter sparePartNo;
	private ComboFilter spareDescn;
	private ComboFilter criticality;
	private ComboFilter classifcn;
	private ComboFilter spqcategory;
	private ComboFilter uom;
	private ComboFilter make;
	private ComboFilter model;
	private ComboFilter source;
	private ComboFilter abcClass;
	private ComboFilter shelfLifeItem;
	private ComboFilter spqType;
	private ComboFilter machineSpec;
	private ComboFilter shelfLifeunt;
	private ComboFilter options;
	private String eqpwise;
	private String sprwise;
	private String bval;
	
	//Training
	private ComboFilter designation;
	private ComboFilter reason;
	private ComboFilter progm;
	private ComboFilter pgmbenefit;	
	private ComboFilter batch;
	private ComboFilter pgmno;
	private ComboFilter trainingtype;
	private ComboFilter knowavg;
	private ComboFilter skillavg;
	private ComboFilter compavg;
	private ComboFilter trainingcategory;
	private String startDate;
	private String endDate;
	private String pgmWise; 
	private String empWise;
	private String trainingArea; //added by Abinaya 
	private ComboFilter role; //added by Abinaya
	private ComboFilter skillType; //added by Abinaya 
	private String skillValue;//added by Abinaya 
	private String knowValue;//added by Abinaya 
	private String attValue;//added by Abinaya 
	private String befFromDate;//added by Abinaya 
	private String befToDate;//added by Abinaya 
	private String aftFromDate;//added by Abinaya 
	private String aftToDate;//added by Abinaya 
	
	//WorkOrder
	private String Prblm;
	private String Prdaffctd;
	private ComboFilter EqpCond;
	private ComboFilter types;
	private String loss;
	private ComboFilter statuss;
	private ComboFilter task;
	private ComboFilter frequency;
	private String dteOccuredfrm;
	private String timOccuredfrm;
	private String dteOccuredto;
	private String timOccuredto;
	private String txtDurTo;
	private String txtDurFrom;
	private String dteReportedfrm;
	private String timReportedfrm;
	private String dteReportedto;
	private String timReportedto;
	
	private String dteReqstfrm;
	private String timReqstfrm;
	private String dteReqstto;
	private String timReqstto;
	
	private String dteProposedfrm;
	private String timProposedfrm;
	private String dteProposedto;
	private String timProposedto;
	
	private String dteReschedulefrm;
	private String timReschedulefrm;
	private String dteRescheduleto;
	private String timRescheduleto;
	
	private String dteAllotedfrm;
	private String timAllotedfrm;
	private String dteAllotedto;
	private String timAllotedto;
	
	private String dteWorkStartfrm;
	private String timWorkStartfrm;
	private String dteWorkStartto;
	private String timWorkStartto;
	
	private String dteWorkEndfrm;
	private String timWorkEndfrm;
	private String dteWorkEndto;
	private String timWorkEndto;
	
	private String dteWorkApprovalfrm;
	private String timWorkApprovalfrm;
	private String dteWorkApprovalto;
	private String timWorkApprovalto;
	private String chkAbnType;//for abnormality general  report by KarthicK.T 
	private String chkMould;
	private String chkWhyWhyHappen;
	private String chkTrade;
	private String chkStatus;
	private String chkTagClass;
	private String chkHTAType;//end for  abnormality general  report by KarthicK.T 
	private String chkfrequencychkbox;
	private String chkseveritychkbox;
	private String chkOccured;
	private String chkAllotted;
	private String chkReported;
	private String chkWorkStart;
	private String chkWorkEnd;
	private String chkProdDate;
	private String dteEqpReleasedfrm;
	private String timEqpReleasedfrm;
	private String dteEqpReleasedto;
	private String timEqpReleasedto;
	private String productionGroup;
	private String dteProductionfrm;
	private String timProductionfrm;
	private String dteProductionto;
	private String timProductionto;
	private Character ISFORGRAPH;
	private Character  FORGRAPH;
	private ComboFilter SortOrd;
	private  String drillCaption; 
	private String fromRow;
	private String toRow;
	private String comboFrom;
	private char viewClick; 
	private long totalRecordCnt;
	private ComboFilter cmbLoss;
	
	private String monthly; //Created KarthicK.T for PMCompliance Report
	private String Weekly; //Created KarthicK.T for PMCompliance Report
	private String bdCategorized;
	private String repportType;//karthick for spare comsumption
	private String ABNORMALITY;
	private String KAIZEN;
	private String BREAKDOWN;
	private String OPL;
	private String PREVENTIVE;
	private String IMPROVEMENT;
	private String GENERAL;
	private String UNPLANNED;//Equipment history by KarthicK.T
	private ComboFilter machineArea;	
	private String machineNotToShown;
	private String lineNotToShown;
	private String pcsEnabled;
	private ComboFilter manager;
	private String equipmentFlag;
	private String mainkeyid;//added by mani for jhdrilldownGraph
	private String defaultDate = "N";//if it is 'Y' passnulldate,futurenulldate assingn to from,to Date 
	private String refresh; //if it is "N" call func for mtbf report 
	private String gridSortColumn;
	private String gridSortOrder;
	private List<GridFilter> gridFilter;
	private String weekno;
	private String areatype;
	private String auditRpt;
	private String istotal;
	private String isneedroleid;
	private ComboFilter cmbActivity;
	private ComboFilter cmbCategory;
	private ComboFilter cmbSubCategory;
	private ComboFilter cmbResponsibility;
	private ComboFilter cmbAssignedto;
	private ComboFilter cmbStatus;
	private String dtestart;
	private String dteend;
	private String dteActualStart;
	private String dteEnd;
	private ComboFilter cboElapsedDays;
	private String txtValue;
	private String txtnxtValue;
	private String multipleval;
	private String Multipletype;
	private String VisualKeyId;
	private String actionKeyId;
	private String yyNo;
	private String abnViewType;
	private ComboFilter effectiveness;
	private ComboFilter kznmThemecategoryid;
	private String ResultAreaP;
	private String ResultAreaQ;
	private String ResultAreaC;
	private String ResultAreaD;
	private String ResultAreaS;
	private String ResultAreaM;
	private String ResultAreaE;
	
	
	private ComboFilter mocItem;
	
	private String isGetCol;
	
	public void setIsGetCol(String isGetCol) {
		this.isGetCol = isGetCol;
    }
   public String getIsGetCol() {
	return isGetCol;
   }
   
   public ComboFilter getMocItem() {
		return mocItem;
	}
	public void setMocItem(ComboFilter mocItem) {
		this.mocItem = mocItem;
	}
	
   public void setIsFacultyLevel(String isFacultyLevel) {
		this.isFacultyLevel = isFacultyLevel;
   }
  public String getIsFacultyLevel() {
	return isFacultyLevel;
  }
  
	public void setDrillCaption(String drillCaption) {
			this.drillCaption = drillCaption;
	}
	public String getDrillCaption() {
		return drillCaption;
	}
	

	public void setRange(String range) {
			this.range = range;
		}
	public String getRange() {
		return range;
	}
		
	public String getOpltype() {
		return opltype;
	}
	public void setOpltype(String opltype) {
		this.opltype = opltype;
	}
	public String getThemelike() {
		return themelike;
	}
	public void setThemelike(String themelike) {
		this.themelike = themelike;
	}
	private String drillLevel ;
	private ComboFilter yyy;
	private String duration;
	private String colVal;
	private String lossId;
	
	public void setColValue(String colVal) {
		this.colVal = colVal;
	}
	public String getColVal() {
		return colVal;
	}
	


	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDuration() {
		return duration;
	}
	
	public ComboFilter getWorkorderNo() {
		return workorderNo;
	}
	public void setWorkorderNo(ComboFilter workorderNo) {
		this.workorderNo = workorderNo;
	}
	public ComboFilter getSkilName() {
		return skilName;
	}
	public void setSkilName(ComboFilter skilName) {
		this.skilName = skilName;
	}
	public ComboFilter getUnit() {
		return unit;
	}
	public void setUnit(ComboFilter unit) {
		this.unit = unit;
	}
	public ComboFilter getSubUnit() {
		return subUnit;
	}
	public void setSubUnit(ComboFilter subUnit) {
		this.subUnit = subUnit;
	}
	public ComboFilter getLocation() {
		return location;
	}
	public void setLocation(ComboFilter location) {
		this.location = location;
	}
	public ComboFilter getWorkCenter() {
		return workCenter;
	}
	public void setWorkCenter(ComboFilter workCenter) {
		this.workCenter = workCenter;
	}
	public ComboFilter getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(ComboFilter reportedBy) {
		this.reportedBy = reportedBy;
	}
	public ComboFilter getMaintSection() {
		return maintSection;
	}
	public void setMaintSection(ComboFilter maintSection) {
		this.maintSection = maintSection;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getProdAffected() {
		return prodAffected;
	}
	public void setProdAffected(String prodAffected) {
		this.prodAffected = prodAffected;
	}
	public String getOccuredFrom() {
		return occuredFrom;
	}
	public void setOccuredFrom(String occuredFrom) {
		this.occuredFrom = occuredFrom;
	}
	public String getOccuredTo() {
		return occuredTo;
	}
	public void setOccuredTo(String occuredTo) {
		this.occuredTo = occuredTo;
	}
	public String getReportedFrom() {
		return reportedFrom;
	}
	public void setReportedFrom(String reportedFrom) {
		this.reportedFrom = reportedFrom;
	}
	public String getReportedTo() {
		return reportedTo;
	}
	public void setReportedTo(String reportedTo) {
		this.reportedTo = reportedTo;
	}
	public String getPermitReqFrom() {
		return permitReqFrom;
	}
	public void setPermitReqFrom(String permitReqFrom) {
		this.permitReqFrom = permitReqFrom;
	}
	public String getPermitReqTo() {
		return permitReqTo;
	}
	public void setPermitReqTo(String permitReqTo) {
		this.permitReqTo = permitReqTo;
	}
	public String getProposedFrom() {
		return proposedFrom;
	}
	public void setProposedFrom(String proposedFrom) {
		this.proposedFrom = proposedFrom;
	}
	public String getProposedTo() {
		return proposedTo;
	}
	public void setProposedTo(String proposedTo) {
		this.proposedTo = proposedTo;
	}
	public String getRescheduleFrom() {
		return rescheduleFrom;
	}
	public void setRescheduleFrom(String rescheduleFrom) {
		this.rescheduleFrom = rescheduleFrom;
	}
	public String getRescheduleTo() {
		return rescheduleTo;
	}
	public void setRescheduleTo(String rescheduleTo) {
		this.rescheduleTo = rescheduleTo;
	}
	public String getAllotedFrom() {
		return allotedFrom;
	}
	public void setAllotedFrom(String allotedFrom) {
		this.allotedFrom = allotedFrom;
	}
	public String getAllotedDtTo() {
		return allotedDtTo;
	}
	public void setAllotedDtTo(String allotedDtTo) {
		this.allotedDtTo = allotedDtTo;
	}
	public String getWrkStartFrom() {
		return wrkStartFrom;
	}
	public void setWrkStartFrom(String wrkStartFrom) {
		this.wrkStartFrom = wrkStartFrom;
	}
	public String getWrkStartTo() {
		return wrkStartTo;
	}
	public void setWrkStartTo(String wrkStartTo) {
		this.wrkStartTo = wrkStartTo;
	}
	public String getWrkEndFrom() {
		return wrkEndFrom;
	}
	public void setWrkEndFrom(String wrkEndFrom) {
		this.wrkEndFrom = wrkEndFrom;
	}
	public String getWrkEndTo() {
		return wrkEndTo;
	}
	public void setWrkEndTo(String wrkEndTo) {
		this.wrkEndTo = wrkEndTo;
	}
	public String getWrkApprvlFrom() {
		return wrkApprvlFrom;
	}
	public void setWrkApprvlFrom(String wrkApprvlFrom) {
		this.wrkApprvlFrom = wrkApprvlFrom;
	}
	public String getWrkApprvlTo() {
		return wrkApprvlTo;
	}
	public void setWrkApprvlTo(String wrkApprvlTo) {
		this.wrkApprvlTo = wrkApprvlTo;
	}
	public String getEqpReleasedFrom() {
		return eqpReleasedFrom;
	}
	public void setEqpReleasedFrom(String eqpReleasedFrom) {
		this.eqpReleasedFrom = eqpReleasedFrom;
	}
	public String getEqpReleasedTo() {
		return eqpReleasedTo;
	}
	public void setEqpReleasedTo(String eqpReleasedTo) {
		this.eqpReleasedTo = eqpReleasedTo;
	}
	public String getProdFrom() {
		return prodFrom;
	}
	public void setProdFrom(String prodFrom) {
		this.prodFrom = prodFrom;
	}
	
	
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public ComboFilter getSubcell() {
		return subcell;
	}
	public void setSubcell(ComboFilter subcell) {
		this.subcell = subcell;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getDrillLevel() {
		return drillLevel;
	}
	public void setDrillLevel(String drillLevel) {
		this.drillCaption = drillLevel;
		this.drillLevel = drillLevel;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ComboFilter getCategory() {
		return category;
	}
	public void setCategory(ComboFilter category) {
		this.category = category;
	}
	public ComboFilter getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(ComboFilter subCategory) {
		this.subCategory = subCategory;
	}
	public ComboFilter getManufacture() {
		return manufacture;
	}
	public void setManufacture(ComboFilter manufacture) {
		this.manufacture = manufacture;
	}
	public String getAmcFrom() {
		return amcFrom;
	}
	public void setAmcFrom(String amcFrom) {
		this.amcFrom = amcFrom;
	}
	public String getAmcTo() {
		return amcTo;
	}
	public void setAmcTo(String amcTo) {
		this.amcTo = amcTo;
	}
	public ComboFilter getAmcvendorId() {
		return amcvendorId;
	}
	public void setAmcvendorId(ComboFilter amcvendorId) {
		this.amcvendorId = amcvendorId;
	}
	public String getInstalfrom() {
		return instalfrom;
	}
	public void setInstalfrom(String instalfrom) {
		this.instalfrom = instalfrom;
	}
	public String getInstalTo() {
		return instalTo;
	}
	public void setInstalTo(String instalTo) {
		this.instalTo = instalTo;
	}
	public String getAmcrnewFrom() {
		return amcrnewFrom;
	}
	public void setAmcrnewFrom(String amcrnewFrom) {
		this.amcrnewFrom = amcrnewFrom;
	}
	public String getAmcrenewTo() {
		return amcrenewTo;
	}
	public void setAmcrenewTo(String amcrenewTo) {
		this.amcrenewTo = amcrenewTo;
	}
	public String getWarrantyFrom() {
		return warrantyFrom;
	}
	public void setWarrantyFrom(String warrantyFrom) {
		this.warrantyFrom = warrantyFrom;
	}
	public String getWarrantyTo() {
		return warrantyTo;
	}
	public void setWarrantyTo(String warrantyTo) {
		this.warrantyTo = warrantyTo;
	}
	public ComboFilter getFinalTrade() {
		return finalTrade;
	}
	public void setFinalTrade(ComboFilter finalTrade) {
		this.finalTrade = finalTrade;
	}
	
	
	public ComboFilter getSupplier() {
		return supplier;
	}
	public void setSupplier(ComboFilter supplier) {
		this.supplier = supplier;
	}
	public ComboFilter getAssembly() {
		return assembly;
	}
	public void setAssembly(ComboFilter assembly) {
		this.assembly = assembly;
	}
	public ComboFilter getEqpGroup() {
		return eqpGroup;
	}
	public void setEqpGroup(ComboFilter eqpGroup) {
		this.eqpGroup = eqpGroup;
	}
	/* Added ByDhanalakshmi.R*/
	public ComboFilter getEqpSubGroup() {
		return eqpSubGrp;
	}
	public void setEqpSubGroup(ComboFilter eqpSubGrp) {
		this.eqpSubGrp = eqpSubGrp;
	}
	/*---------------------------*/
	public ComboFilter getProduction() {
		return production;
	}
	public void setProduction(ComboFilter production) {
		this.production = production;
	}
	public ComboFilter getJhStep() {
		return jhStep;
	}
	public void setJhStep(ComboFilter jhStep) {
		this.jhStep = jhStep;
	}
	public ComboFilter getMachineRank() {
		return machineRank;
	}
	public void setMachineRank(ComboFilter machineRank) {
		this.machineRank = machineRank;
	}
	
	public ComboFilter getJobType() {
		return jobType;
	}
	public void setJobType(ComboFilter jobType) {
		this.jobType = jobType;
	}
	public ComboFilter getPmSource() {
		return pmSource;
	}
	public void setPmSource(ComboFilter pmSource) {
		this.pmSource = pmSource;
	}
	public ComboFilter getFactory() {
		return factory;
	}
	public void setFactory(ComboFilter factory) {
		this.factory = factory;
	}
	public ComboFilter getSection() {
		return section;
	}
	public void setSection(ComboFilter section) {
		this.section = section;
	}
	public ComboFilter getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(ComboFilter costCenter) {
		this.costCenter = costCenter;
	}
	public ComboFilter getCell() {
		return cell;
	}
	public void setCell(ComboFilter cell) {
		this.cell = cell;
	}
	public ComboFilter getMachine() {
		return machine;
	}
	public void setMachine(ComboFilter machine) {
		this.machine = machine;
	}
	public ComboFilter getCircle() {
		return circle;
	}
	public void setCircle(ComboFilter circle) {
		this.circle = circle;
	}
	public ComboFilter getTrade() {
		return trade;
	}
	public void setTrade(ComboFilter trade) {
		this.trade = trade;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(ComboFilter company) {
		this.company = company;
	}
	/**
	 * @return the company
	 */
	public ComboFilter getCompany() {
		return company;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		if(toDate != null && ! toDate.trim().isEmpty()  )
			this.toDate = toDate ;
		else
			this.toDate  = Constants.futureNullDate;
		
	//	CommonMessage.debugMsg(" this.toDate " + this.toDate);
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
			
		//if(fromDate != null && ! fromDate.trim().isEmpty()  )
			this.fromDate = fromDate ;
		//else
			//this.fromDate  = Constants.passNullDate;
		
		CommonMessage.debugMsg(" this.fromDate:" + this.fromDate);
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	public ComboFilter getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(ComboFilter completedBy) {
		this.completedBy = completedBy;
	}
	public ComboFilter getActType() {
		return actType;
	}
	public void setActType(ComboFilter actType) {
		this.actType = actType;
	}
	/**
	 * @param spares the spares to set
	 */
	
	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(ComboFilter purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return the purpose
	 */
	public ComboFilter getPurpose() {
		return purpose;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param shift the shift to set
	 */
	public void setShift(ComboFilter shift) {
		this.shift = shift;
	}
	/**
	 * @return the shift
	 */
	public ComboFilter getShift() {
		return shift;
	}
	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(ComboFilter supervisor) {
		this.supervisor = supervisor;
	}
	/**
	 * @return the supervisor
	 */
	public ComboFilter getSupervisor() {
		return supervisor;
	}
	/**
	 * @param workOrder the workOrder to set
	 */
	public void setWorkOrder(ComboFilter workOrder) {
		this.workOrder = workOrder;
	}
	/**
	 * @return the workOrder
	 */
	public ComboFilter getWorkOrder() {
		return workOrder;
	}
	/**
	 * @param allotedTo the allotedTo to set
	 */
	public void setAllotedTo(ComboFilter allotedTo) {
		this.allotedTo = allotedTo;
	}
	/**
	 * @return the allotedTo
	 */
	public ComboFilter getAllotedTo() {
		return allotedTo;
	}
	/**
	 * @param preparedBy the preparedBy to set
	 */
	public void setPreparedBy(ComboFilter preparedBy) {
		this.preparedBy = preparedBy;
	}
	/**
	 * @return the preparedBy
	 */
	public ComboFilter getPreparedBy() {
		return preparedBy;
	}
	/**
	 * @param spareReq the spareReq to set
	 */
	public void setSpareReq(String spareReq) {
		this.spareReq = spareReq;
	}
	/**
	 * @return the spareReq
	 */
	public String getSpareReq() {
		return spareReq;
	}
	/**
	 * @param oPLId the oPLId to set
	 */
	public void setOPLId(String oPLId) {
		OPLId = oPLId;
	}
	/**
	 * @return the oPLId
	 */
	public String getOPLId() {
		return OPLId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOplid() {
		return oplid;
	}
	public void setOplid(String oplid) {
		this.oplid = oplid;
	}
	public String getGetKaizenkey() {
		return getKaizenkey;
	}
	public void setGetKaizenkey(String getKaizenkey) {
		this.getKaizenkey = getKaizenkey;
	}
	
	public void setPhenomena(ComboFilter phenomena) {
		this.phenomena = phenomena;
	}
	public ComboFilter getPhenomena() {
		return phenomena;
	}
	public void setFinalCause(ComboFilter finalCause) {
		this.finalCause = finalCause;
	}
	public ComboFilter getFinalCause() {
		return finalCause;
	}
	public void setMaintainChargeId(ComboFilter maintainChargeId) {
		this.maintainChargeId = maintainChargeId;
	}
	public ComboFilter getMaintainChargeId() {
		return maintainChargeId;
	}
	
	public void setMANPOWERID(ComboFilter mANPOWERID) {
		MANPOWERID = mANPOWERID;
	}
public ComboFilter getMANPOWERID() {
		return MANPOWERID;
	}
public void setGRADEID(ComboFilter gRADEID) {
		GRADEID = gRADEID;
	}
public ComboFilter getGRADEID() {
		return GRADEID;
	}
/**
 * @param subgroup the subgroup to set
 */
public void setSubgroup(ComboFilter subgroup) {
	this.subgroup = subgroup;
}
/**
 * @return the subgroup
 */
public ComboFilter getSubgroup() {
	return subgroup;
}
public void setFromMonth(String fromMonth) {
	this.fromMonth = fromMonth;
}
public String getFromMonth() {
	return fromMonth;
}
public void setToMonth(String toMonth) {
	this.toMonth = toMonth;
}
public String getToMonth() {
	return toMonth;
}
public void setYear(String year) {
	this.year = year;
}
public String getYear() {
	return year;
}
public void setDefaultFinYear(String defaultFinYear) {
	this.defaultFinYear = defaultFinYear;
}
public String getDefaultFinYear() {
	return defaultFinYear;
}
public void setAbnType(ComboFilter abnType) {
	this.abnType = abnType;
}
public ComboFilter getAbnType() {
	return abnType;
}
public void setAbnCategory(ComboFilter abnCategory) {
	this.abnCategory = abnCategory;
}
public ComboFilter getAbnCategory() {
	return abnCategory;
}
public void setAbnImpact(ComboFilter abnImpact) {
	this.abnImpact = abnImpact;
}
public ComboFilter getAbnImpact() {
	return abnImpact;
}
public void setAbnStatus(ComboFilter abnStatus) {
	this.abnStatus = abnStatus;
}
public ComboFilter getAbnStatus() {
	return abnStatus;
}
public void setAbnDetectBy(String abnDetectBy) {
	this.abnDetectBy = abnDetectBy;
}
public String getAbnDetectBy() {
	return abnDetectBy;
}
public void setAbnDetect(String abnDetect) {
	this.abnDetect = abnDetect;
}
public String getAbnDetect() {
	return abnDetect;
}
public void setAbnCause(String abnCause) {
	this.abnCause = abnCause;
}
public String getAbnCause() {
	return abnCause;
}
public void setAbnCatch(String abnCatch) {
	this.abnCatch = abnCatch;
}
public String getAbnCatch() {
	return abnCatch;
}
public void setAbnImp(String abnImp) {
	this.abnImp = abnImp;
}
public String getAbnImp() {
	return abnImp;
}
public void setAbnAllch(String abnAllch) {
	this.abnAllch = abnAllch;
}
public String getAbnAllch() {
	return abnAllch;
}

public void setAbnIsHSE(String abnIsHSE) {
	this.abnIsHSE = abnIsHSE;
}

public String getAbnIsHSE() {
	return abnIsHSE;
}

public void setRefNo(ComboFilter refNo) {
	this.refNo = refNo;
}
public ComboFilter getRefNo() {
	return refNo;
}
public void setDetectedBy(ComboFilter detectedBy) {
	this.detectedBy = detectedBy;
}
public ComboFilter getDetectedBy() {
	return detectedBy;
}
public void setdFromDate(String dFromDate) {
	this.dFromDate = dFromDate;
}
public String getdFromDate() {
	return dFromDate;
}
public void setdToDate(String dToDate) {
	this.dToDate = dToDate;
}
public String getdToDate() {
	return dToDate;
}
public void setTgtFromDate(String tgtFromDate) {
	this.tgtFromDate = tgtFromDate;
}
public String getTgtFromDate() {
	return tgtFromDate;
}
public void setTgtToDate(String tgtToDate) {
	this.tgtToDate = tgtToDate;
}
public String getTgtToDate() {
	return tgtToDate;
}

public void setCompletedFromDate(String completedFromDate) {
	this.completedFromDate = completedFromDate;
}
public String getCompletedFromDate() {
	return completedFromDate;
}
public void setCompletedToDate(String completedToDate) {
	this.completedToDate = completedToDate;
}
public String getCompletedToDate() {
	return completedToDate;
}
public void setAuditType(ComboFilter cboType) {
	this.cboType = cboType;
}
public ComboFilter getAuditType() {
	return cboType;
}
public void setAuditStatus(ComboFilter cboStatus) {
	this.cboStatus = cboStatus;
}
public ComboFilter getAuditStatus() {
	return cboStatus;
}

public void setAuditFromDate(String AuditFromDate) {
	this.AuditFromDate = AuditFromDate;
}
public String getAuditFromDate() {
	return AuditFromDate;
}


public void setAuditToDate(String AuditToDate) {
	this.AuditToDate = AuditToDate;
}
public String getAuditToDate() {
	return AuditToDate;
}

public void setBdType(String bdType) {
	this.bdType = bdType;
}
public String getBdType() {
	return bdType;
}

public void setCost(ComboFilter cost) {
	this.cost = cost;
}
public ComboFilter getCost() {
	return cost;
}

public void setReportType(ComboFilter reportType) {
	this.reportType = reportType;
}
public ComboFilter getReportType() {
	return reportType;
}



public void setImte(ComboFilter imte) {
	this.imte = imte;
}
public ComboFilter getImte() {
	return imte;
}
public void setSsuedtocalib(ComboFilter ssuedtocalib) {
	this.ssuedtocalib = ssuedtocalib;
}
public ComboFilter getSsuedtocalib() {
	return ssuedtocalib;
}
public void setExternalagencies(ComboFilter externalagencies) {
	this.externalagencies = externalagencies;
}
public ComboFilter getExternalagencies() {
	return externalagencies;
}
public void setExportchkbox(String exportchkbox) {
	this.exportchkbox = exportchkbox;
}
public String getExportchkbox() {
	return exportchkbox;
}
public void setCalibFromDate(String calibFromDate) {
	CalibFromDate = calibFromDate;
}
public String getCalibFromDate() {
	return CalibFromDate;
}
public void setIssuedto(ComboFilter issuedto) {
	this.issuedto = issuedto;
}
public ComboFilter getIssuedto() {
	return issuedto;
}
public void setServicetype(ComboFilter servicetype) {
	this.servicetype = servicetype;
}
public ComboFilter getServicetype() {
	return servicetype;
}
public void setCompltdby(ComboFilter compltdby) {
	this.compltdby = compltdby;
}
public ComboFilter getCompltdby() {
	return compltdby;
}
public void setCalibToDate(String calibToDate) {
	CalibToDate = calibToDate;
}
public String getCalibToDate() {
	return CalibToDate;
}
public void setSlno(ComboFilter slno) {
	this.slno = slno;
}
public ComboFilter getSlno() {
	return slno;
}
public void setParameter(ComboFilter parameter) {
	this.parameter = parameter;
}
public ComboFilter getParameter() {
	return parameter;
}
public void setGauge(ComboFilter gauge) {
	this.gauge = gauge;
}
public ComboFilter getGauge() {
	return gauge;
}
public void setDecision(ComboFilter decision) {
	this.decision = decision;
}
public ComboFilter getDecision() {
	return decision;
}
public void setMethod(ComboFilter method) {
	this.method = method;
}
public ComboFilter getMethod() {
	return method;
}
public void setAmcRenewal(String amcRenewal) {
	AmcRenewal = amcRenewal;
}
public String getAmcRenewal() {
	return AmcRenewal;
}
public void setWarrantyExpries(String warrantyExpries) {
	WarrantyExpries = warrantyExpries;
}
public String getWarrantyExpries() {
	return WarrantyExpries;
}
public void setJhfreq(String tempVar) {
	this.jhfreq = tempVar;
}
public String getJhfreq() {
	return jhfreq;
}
public void setJhduration(ComboFilter jhduration) {
	this.jhduration = jhduration;
}
public ComboFilter getJhduration() {
	return jhduration;
}
public void setEmp(ComboFilter emp) {
	this.emp = emp;
}
public ComboFilter getEmp() {
	return emp;
}
public void setTeam(ComboFilter team) {
	this.team = team;
}
public ComboFilter getTeam() {
	return team;
}
public void setDept(ComboFilter dept) {
	this.dept = dept;
}
public ComboFilter getDept() {
	return dept;
}
public void setTranstyp(ComboFilter transtyp) {
	this.transtyp = transtyp;
}
public ComboFilter getTranstyp() {
	return transtyp;
}
public void setMinschkbox(String minschkbox) {
	this.minschkbox = minschkbox;
}
public String getMinschkbox() {
	return minschkbox;
}
public void setHrschkbox(String hrschkbox) {
	this.hrschkbox = hrschkbox;
}
public String getHrschkbox() {
	return hrschkbox;
}
public void setPlanchkbox(String planchkbox) {
	this.planchkbox = planchkbox;
}
public String getPlanchkbox() {
	return planchkbox;
}
public void setActualchkbox(String actualchkbox) {
	this.actualchkbox = actualchkbox;
}
public String getActualchkbox() {
	return actualchkbox;
}
public void setOplNoid(ComboFilter oplNoid) {
	OplNoid = oplNoid;
}
public ComboFilter getOplNoid() {
	return OplNoid;
}

public void setOplTypeid(ComboFilter oplTypeid) {
	OplTypeid = oplTypeid;
}
public ComboFilter getOplTypeid() {
	return OplTypeid;
}
public void setImprovmntNoid(ComboFilter improvmntNoid) {
	ImprovmntNoid = improvmntNoid;
}
public ComboFilter getImprovmntNoid() {
	return ImprovmntNoid;
}
public void setPillarid(ComboFilter pillarid) {
	Pillarid = pillarid;
}
public ComboFilter getPillarid() {
	return Pillarid;
}
public void setBK(String bK) {
	BK = bK;
}
public String getBK() {
	return BK;
}
public void setEqptGrpWise(String eqptGrpWise) {
	EqptGrpWise = eqptGrpWise;
}
public String getEqptGrpWise() {
	return EqptGrpWise;
}
public void setBoxResultWise(String boxResultWise) {
	this.boxResultWise = boxResultWise;
}
public String getBoxResultWise() {
	return boxResultWise;
}
public void setTC(String tC) {
	TC = tC;
}
public String getTC() {
	return TC;
}
public void setLossWise(String lossWise) {
	LossWise = lossWise;
}
public String getLossWise() {
	return LossWise;
}
public void setJH(String jH) {
	JH = jH;
}
public String getJH() {
	return JH;
}
public void setPillarWise(String pillarWise) {
	PillarWise = pillarWise;
}
public String getPillarWise() {
	return PillarWise;
}
public void setDM(String dM) {
	DM = dM;
}
public String getDM() {
	return DM;
}
public void setSectWise(String sectWise) {
	SectWise = sectWise;
}
public String getSectWise() {
	return SectWise;
}
public void setCellWise(String cellWise) {
	CellWise = cellWise;
}
public String getCellWise() {
	return CellWise;
}
public void setEqptWise(String eqptWise) {
	EqptWise = eqptWise;
}
public String getEqptWise() {
	return EqptWise;
}
public void setQm(String qm) {
	Qm = qm;
}
public String getQm() {
	return Qm;
}
public void setOTpm(String oTpm) {
	OTpm = oTpm;
}
public String getOTpm() {
	return OTpm;
}
public void setKK(String kK) {
	KK = kK;
}
public String getKK() {
	return KK;
}
public void setShe(String she) {
	She = she;
}
public String getShe() {
	return She;
}
public void setPm(String pm) {
	Pm = pm;
}
public String getPm() {
	return Pm;
}
public void setET(String eT) {
	ET = eT;
}
public String getET() {
	return ET;
}
public void setIC(String iC) {
	IC = iC;
}
public String getIC() {
	return IC;
}
public void setCmbpcssubgrp(ComboFilter cmbpcssubgrp) {
	this.cmbpcssubgrp = cmbpcssubgrp;
}
public ComboFilter getCmbpcssubgrp() {
	return cmbpcssubgrp;
}
public void setcCmbpcsprrod(ComboFilter cmbpcsprrod) {
	this.cmbpcsprrod = cmbpcsprrod;
}
public ComboFilter getCmbpcsprrod() {
	return cmbpcsprrod;
}
public void setChkboxef(String chkboxef) {
	this.chkboxef = chkboxef;
}
public String getChkboxef() {
	return chkboxef;
}
public void setChkboxdf(String chkboxdf) {
	this.chkboxdf = chkboxdf;
}
public String getChkboxdf() {
	return chkboxdf;
}
public void setChkboxar(String chkboxar) {
	this.chkboxar = chkboxar;
}
public String getChkboxar() {
	return chkboxar;
}
public void setChkboxoccurence(String chkboxoccurence) {
	this.chkboxoccurence = chkboxoccurence;
}
public String getChkboxoccurence() {
	return chkboxoccurence;
}
public void setChkboxpr(String chkboxpr) {
	this.chkboxpr = chkboxpr;
}
public String getChkboxpr() {
	return chkboxpr;
}
public void setChkboxoee(String chkboxoee) {
	this.chkboxoee = chkboxoee;
}
public String getChkboxoee() {
	return chkboxoee;
}
public void setChkboxweek(String chkboxweek) {
	this.chkboxweek = chkboxweek;
}
public String getChkboxweek() {
	return chkboxweek;
}
public void setChkboxhour(String chkboxhour) {
	this.chkboxhour = chkboxhour;
}
public String getChkboxhour() {
	return chkboxhour;
}
public void setChkboxtime(String chkboxtime) {
	this.chkboxtime = chkboxtime;
}
public String getChkboxtime() {
	return chkboxtime;
}
public void setChkboxqr(String chkboxqr) {
	this.chkboxqr = chkboxqr;
}
public String getChkboxqr() {
	return chkboxqr;
}
public void setChkboxvai(String chkboxvai) {
	this.chkboxvai = chkboxvai;
}
public String getChkboxvai() {
	return chkboxvai;
}
public void setChkboxall(String chkboxall) {
	this.chkboxall = chkboxall;
}
public String getChkboxall() {
	return chkboxall;
}
public void setChkboxday(String chkboxday) {
	this.chkboxday = chkboxday;
}
public String getChkboxday() {
	return chkboxday;
}
public void setChkboxshift(String chkboxshift) {
	this.chkboxshift = chkboxshift;
}
public String getChkboxshift() {
	return chkboxshift;
}
public void setJobtype(ComboFilter jobtype) {
	this.jobtype = jobtype;
}
public ComboFilter getJobtype() {
	return jobtype;
}
public void setSources(ComboFilter sources) {
	this.sources = sources;
}
public ComboFilter getSources() {
	return sources;
}
public void setActivities(ComboFilter activities) {
	this.activities = activities;
}
public ComboFilter getActivities() {
	return activities;
}
public void setEqpCondn(ComboFilter eqpCondn) {
	this.eqpCondn = eqpCondn;
}
public ComboFilter getEqpCondn() {
	return eqpCondn;
}
public void setRemblank(String remblank) {
	this.remblank = remblank;
}
public String getRemblank() {
	return remblank;
}
public void setIncludestatus(String includestatus) {
	this.includestatus = includestatus;
}
public String getIncludestatus() {
	return includestatus;
}
public void setDrillfor(ComboFilter drillfor) {
	this.drillfor = drillfor;
}
public ComboFilter getDrillfor() {
	return drillfor;
}
public void setTools(ComboFilter tools) {
	this.tools = tools;
}
public ComboFilter getTools() {
	return tools;
}
public void setPmsource(ComboFilter pmsource) {
	this.pmsource = pmsource;
}
public ComboFilter getPmsource() {
	return pmsource;
}
/*public void setPmstatus(ComboFilter pmstatus) {
	this.pmstatus = pmstatus;
}
public ComboFilter getPmstatus() {
	return pmstatus;
}*/
public void setNoofdays(String noofdays) {
	this.noofdays = noofdays;
}
public String getNoofdays() {
	return noofdays;
}
public void setFreq(String cmbfreq) {
	this.freq = cmbfreq;
}
public String getFreq() {
	return freq;
}
public void setEqpmnt(String eqpmnt) {
	this.eqpmnt = eqpmnt;
}
public String getEqpmnt() {
	return eqpmnt;
}
public void setMonwise(String monwise) {
	this.monwise = monwise;
}
public String getMonwise() {
	return monwise;
}
public void setPmjobtype(ComboFilter pmjobtype) {
	this.pmjobtype = pmjobtype;
}
public ComboFilter getPmjobtype() {
	return pmjobtype;
}
public void setActwise(String actwise) {
	this.actwise = actwise;
}
public String getActwise() {
	return actwise;
}
public void setCellch(String cellch) {
	this.cellch = cellch;
}
public String getCellch() {
	return cellch;
}
public void setMwise(String mwise) {
	this.mwise = mwise;
}
public String getMwise() {
	return mwise;
}
public void setAtype(String atype) {
	this.atype = atype;
}
public String getAtype() {
	return atype;
}
public void setAwise(String awise) {
	this.awise = awise;
}
public String getAwise() {
	return awise;
}
public void setTw(String tw) {
	this.tw = tw;
}
public String getTw() {
	return tw;
}
public void setSummary(String summary) {
	this.summary = summary;
}
public String getSummary() {
	return summary;
}
public void setSect(String sect) {
	this.sect = sect;
}
public String getSect() {
	return sect;
}
public void setParam(ComboFilter param) {
	this.param = param;
}
public ComboFilter getParam() {
	return param;
}
public void setJtw(String jtw) {
	this.jtw = jtw;
}
public String getJtw() {
	return jtw;
}
public void setComp(String comp) {
	this.comp = comp;
}
public String getComp() {
	return comp;
}
public void setOther(String other) {
	this.other = other;
}
public String getOther() {
	return other;
}
public void setUtil(String util) {
	this.util = util;
}
public String getUtil() {
	return util;
}
public void setContractor(String contractor) {
	this.contractor = contractor;
}
public String getContractor() {
	return contractor;
}
public void setPend(String pend) {
	this.pend = pend;
}
public String getPend() {
	return pend;
}
public void setTotal(String total) {
	this.total = total;
}
public String getTotal() {
	return total;
}
public void setAp(String ap) {
	this.ap = ap;
}
public String getAp() {
	return ap;
}
public void setService(String service) {
	this.service = service;
}
public String getService() {
	return service;
}
public void setEmpch(String empch) {
	this.empch = empch;
}
public String getEmpch() {
	return empch;
}
public void setSpares(String spares) {
	Spares = spares;
}
public String getSpares() {
	return Spares;
}
public void setProcess(ComboFilter process) {
	this.process = process;
}
public ComboFilter getProcess() {
	return process;
}
public void setComplaintno(ComboFilter complaintno) {
	this.complaintno = complaintno;
}
public ComboFilter getComplaintno() {
	return complaintno;
}



public void setComplainttype(ComboFilter complainttype) {
	this.complainttype = complainttype;
}
public ComboFilter getComplainttype() {
	return complainttype;
}

public void setCustID(ComboFilter custID) {
	this.custID = custID;
}
public ComboFilter getCustID() {
	return custID;
}
public void setproduct(ComboFilter product) {
	this.product = product;
}
public ComboFilter getproduct() {
	return product;
}
public void setRejection(String rejection) {
	this.rejection = rejection;
}
public String getRejection() {
	return rejection;
}
public void setDefactparam(ComboFilter defactparam) {
	this.defactparam = defactparam;
}
public ComboFilter getDefactparam() {
	return defactparam;
}
public void setDefactPhenamena(ComboFilter defactPhenamena) {
	this.defactPhenamena = defactPhenamena;
}
public ComboFilter getDefactPhenamena() {
	return defactPhenamena;
}
public void setInspection(ComboFilter inspection) {
	this.inspection = inspection;
}
public ComboFilter getInspection() {
	return inspection;
}
public void setImpdone(String impdone) {
	this.impdone = impdone;
}
public String getImpdone() {
	return impdone;
}
public void setRecordedby(ComboFilter recordedby) {
	this.recordedby = recordedby;
}
public ComboFilter getRecordedby() {
	return recordedby;
}
public void setAgno(ComboFilter agno) {
	this.agno = agno;
}
public ComboFilter getAgno() {
	return agno;
}
public void setReptype(ComboFilter reptype) {
	this.reptype = reptype;
}
public ComboFilter getReptype() {
	return reptype;
}
public void setIncidentno(ComboFilter incidentno) {
	this.incidentno = incidentno;
}
public ComboFilter getIncidentno() {
	return incidentno;
}
public void setWorkarea(ComboFilter workarea) {
	this.workarea = workarea;
}
public ComboFilter getWorkarea() {
	return workarea;
}
public void setIncidenttype(ComboFilter incidenttype) {
	this.incidenttype = incidenttype;
}
public ComboFilter getIncidenttype() {
	return incidenttype;
}
public void setSafetytype(ComboFilter safetytype) {
	this.safetytype = safetytype;
}
public ComboFilter getSafetytype() {
	return safetytype;
}
public void setIncdntFromDate(String incdntFromDate) {
	this.incdntFromDate = incdntFromDate;
}
public String getIncdntFromDate() {
	return incdntFromDate;
}
public void setSafetysubtype(ComboFilter safetysubtype) {
	this.safetysubtype = safetysubtype;
}
public ComboFilter getSafetysubtype() {
	return safetysubtype;
}
public void setRelatedto(ComboFilter relatedto) {
	this.relatedto = relatedto;
}
public ComboFilter getRelatedto() {
	return relatedto;
}
public void setIncdntdToDate(String incdntdToDate) {
	this.incdntdToDate = incdntdToDate;
}
public String getIncdntdToDate() {
	return incdntdToDate;
}
public void setImprovmnt(ComboFilter improvmnt) {
	this.improvmnt = improvmnt;
}
public ComboFilter getImprovmnt() {
	return improvmnt;
}
public void setMdl(String mdl) {
	this.mdl = mdl;
}
public String getMdl() {
	return mdl;
}
public void setPriority(ComboFilter priority) {
	this.priority = priority;
}
public ComboFilter getPriority() {
	return priority;
}
public void setEmployee(ComboFilter employee) {
	this.employee = employee;
}
public ComboFilter getEmployee() {
	return employee;
}
public void setBodypart(ComboFilter bodypart) {
	this.bodypart = bodypart;
}
public ComboFilter getBodypart() {
	return bodypart;
}
public void setInjuryType(ComboFilter injurytype) {
	this.injurytype = injurytype;
}
public ComboFilter getInjuryType() {
	return injurytype;
}


public void setInstance(String instance) {
	this.instance = instance;
}
public String getInstance() {
	return instance;
}
public void setSparePartNo(ComboFilter sparePartNo) {
	this.sparePartNo = sparePartNo;
}
public ComboFilter getSparePartNo() {
	return sparePartNo;
}
public void setAbcClass(ComboFilter abcClass) {
	this.abcClass = abcClass;
}
public ComboFilter getAbcClass() {
	return abcClass;
}
public void setCriticality(ComboFilter criticality) {
	this.criticality = criticality;
}
public ComboFilter getCriticality() {
	return criticality;
}
public void setSource(ComboFilter source) {
	this.source = source;
}
public ComboFilter getSource() {
	return source;
}
public void setSpqcategory(ComboFilter spqcategory) {
	this.spqcategory = spqcategory;
}
public ComboFilter getSpqcategory() {
	return spqcategory;
}
public void setSpareDescn(ComboFilter spareDescn) {
	this.spareDescn = spareDescn;
}
public ComboFilter getSpareDescn() {
	return spareDescn;
}
public void setMake(ComboFilter make) {
	this.make = make;
}
public ComboFilter getMake() {
	return make;
}
public void setSprwise(String sprwise) {
	this.sprwise = sprwise;
}
public String getSprwise() {
	return sprwise;
}
public void setMachineSpec(ComboFilter machineSpec) {
	this.machineSpec = machineSpec;
}
public ComboFilter getMachineSpec() {
	return machineSpec;
}
public void setEqpwise(String eqpwise) {
	this.eqpwise = eqpwise;
}
public String getEqpwise() {
	return eqpwise;
}
public void setBval(String bval) {
	this.bval = bval;
}
public String getBval() {
	return bval;
}
public void setShelfLifeItem(ComboFilter shelfLifeItem) {
	this.shelfLifeItem = shelfLifeItem;
}
public ComboFilter getShelfLifeItem() {
	return shelfLifeItem;
}
public void setShelfLifeunt(ComboFilter shelfLifeunt) {
	this.shelfLifeunt = shelfLifeunt;
}
public ComboFilter getShelfLifeunt() {
	return shelfLifeunt;
}
public void setSpqType(ComboFilter spqType) {
	this.spqType = spqType;
}
public ComboFilter getSpqType() {
	return spqType;
}
public void setModel(ComboFilter model) {
	this.model = model;
}
public ComboFilter getModel() {
	return model;
}
public void setClassifcn(ComboFilter classifcn) {
	this.classifcn = classifcn;
}
public ComboFilter getClassifcn() {
	return classifcn;
}
public void setUom(ComboFilter uom) {
	this.uom = uom;
}
public ComboFilter getUom() {
	return uom;
}
public void setDesignation(ComboFilter designation) {
	this.designation = designation;
}
public ComboFilter getDesignation() {
	return designation;
}
public void setTrainingtype(ComboFilter trainingtype) {
	this.trainingtype = trainingtype;
}
public ComboFilter getTrainingtype() {
	return trainingtype;
}
public void setPgmbenefit(ComboFilter pgmbenefit) {
	this.pgmbenefit = pgmbenefit;
}
public ComboFilter getPgmbenefit() {
	return pgmbenefit;
}
public void setTrainingcategory(ComboFilter trainingcategory) {
	this.trainingcategory = trainingcategory;
}
public ComboFilter getTrainingcategory() {
	return trainingcategory;
}
public void setProgm(ComboFilter progm) {
	this.progm = progm;
}
public ComboFilter getProgm() {
	return progm;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getStartDate() {
	return startDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getEndDate() {
	return endDate;
}
public void setBatch(ComboFilter batch) {
	this.batch = batch;
}
public ComboFilter getBatch() {
	return batch;
}
public void setCompavg(ComboFilter compavg) {
	this.compavg = compavg;
}
public ComboFilter getCompavg() {
	return compavg;
}
public void setKnowavg(ComboFilter knowavg) {
	this.knowavg = knowavg;
}
public ComboFilter getKnowavg() {
	return knowavg;
}
public void setPgmno(ComboFilter pgmno) {
	this.pgmno = pgmno;
}
public ComboFilter getPgmno() {
	return pgmno;
}
public void setSkillavg(ComboFilter skillavg) {
	this.skillavg = skillavg;
}
public ComboFilter getSkillavg() {
	return skillavg;
}
public void setPrblm(String prblm) {
	Prblm = prblm;
}

public String getPrblm() {
	return Prblm;
}
public void setPgmWise(String PgmWise){
	pgmWise = PgmWise;
}
public String getPgmWise() {
	return pgmWise;
}
public void setEmpWise(String EmpWise){
	empWise = EmpWise;
}
public String getEmpWise() {
	return empWise;
}
public void setTrarId(String TrarKeyID){
	trainingArea = TrarKeyID;
}
public String getTrarId() {
	return trainingArea;
}
public void setSkilValue(String skilVal){
	skillValue = skilVal;
}
public String getSkilValue() {
	return skillValue;
}
public void setKnowValue(String knowVal){
	knowValue = knowVal;
}
public String getKnowValue() {
	return skillValue;
}
public void setAttValue(String attVal){
	attValue = attVal;
}
public String getAttValue() {
	return attValue;
}
public void setBefFromDt(String befFromDt){
	befFromDate = befFromDt;
}
public String getBefFromDt() {
	return befFromDate;
}
public void setBefToDt(String befToDt){
	befToDate = befToDt;
}
public String getBefToDt() {
	return befToDate;
}
public void setAftToDt(String aftToDt){
	aftToDate = aftToDt;
}
public String getAftToDt() {
	return aftToDate;
}
public void setAftFromDt(String aftFromDt){
	aftFromDate = aftFromDt;
}
public String getAftFromDt() {
	return aftFromDate;
}
public void setPrdaffctd(String prdaffctd) {
	Prdaffctd = prdaffctd;
}
public String getPrdaffctd() {
	return Prdaffctd;
}
public void setEqpCond(ComboFilter eqpCond) {
	EqpCond = eqpCond;
}
public ComboFilter getEqpCond() {
	return EqpCond;
}
public void setTypes(ComboFilter types) {
	this.types = types;
}
public ComboFilter getTypes() {
	return types;
}
public void setStatuss(ComboFilter statuss) {
	this.statuss = statuss;
}
public ComboFilter getStatuss() {
	return statuss;
}
public void setTask(ComboFilter task) {
	this.task = task;
}
public ComboFilter getTask() {
	return task;
}
public void setDteOccuredfrm(String dteOccuredfrm) {
	this.dteOccuredfrm = dteOccuredfrm;
}
public String getDteOccuredfrm() {
	return dteOccuredfrm;
}
public void setTimOccuredfrm(String timOccuredfrm) {
	this.timOccuredfrm = timOccuredfrm;
}
public String getTimOccuredfrm() {
	return timOccuredfrm;
}
public void setDteReqstfrm(String dteReqstfrm) {
	this.dteReqstfrm = dteReqstfrm;
}
public String getDteReqstfrm() {
	return dteReqstfrm;
}
public void setDteReportedto(String dteReportedto) {
	this.dteReportedto = dteReportedto;
}
public String getDteReportedto() {
	return dteReportedto;
}
public void setTimOccuredto(String timOccuredto) {
	this.timOccuredto = timOccuredto;
}
public String getTimOccuredto() {
	return timOccuredto;
}
public void setTimReqstto(String timReqstto) {
	this.timReqstto = timReqstto;
}
public String getTimReqstto() {
	return timReqstto;
}
public void setDteRescheduleto(String dteRescheduleto) {
	this.dteRescheduleto = dteRescheduleto;
}
public String getDteRescheduleto() {
	return dteRescheduleto;
}
public void setDteReportedfrm(String dteReportedfrm) {
	this.dteReportedfrm = dteReportedfrm;
}
public String getDteReportedfrm() {
	return dteReportedfrm;
}
public void setDteOccuredto(String dteOccuredto) {
	this.dteOccuredto = dteOccuredto;
}
public String getDteOccuredto() {
	return dteOccuredto;
}
public void setDteReqstto(String dteReqstto) {
	this.dteReqstto = dteReqstto;
}
public String getDteReqstto() {
	return dteReqstto;
}
public void setTimReportedfrm(String timReportedfrm) {
	this.timReportedfrm = timReportedfrm;
}
public String getTimReportedfrm() {
	return timReportedfrm;
}
public void setTimReportedto(String timReportedto) {
	this.timReportedto = timReportedto;
}
public String getTimReportedto() {
	return timReportedto;
}
public void setTimReqstfrm(String timReqstfrm) {
	this.timReqstfrm = timReqstfrm;
}
public String getTimReqstfrm() {
	return timReqstfrm;
}
public void setDteProposedfrm(String dteProposedfrm) {
	this.dteProposedfrm = dteProposedfrm;
}
public String getDteProposedfrm() {
	return dteProposedfrm;
}
public void setDteAllotedfrm(String dteAllotedfrm) {
	this.dteAllotedfrm = dteAllotedfrm;
}
public String getDteAllotedfrm() {
	return dteAllotedfrm;
}
public void setTimReschedulefrm(String timReschedulefrm) {
	this.timReschedulefrm = timReschedulefrm;
}
public String getTimReschedulefrm() {
	return timReschedulefrm;
}
public void setTimProposedfrm(String timProposedfrm) {
	this.timProposedfrm = timProposedfrm;
}
public String getTimProposedfrm() {
	return timProposedfrm;
}
public void setTimAllotedto(String timAllotedto) {
	this.timAllotedto = timAllotedto;
}
public String getTimAllotedto() {
	return timAllotedto;
}
public void setDteReschedulefrm(String dteReschedulefrm) {
	this.dteReschedulefrm = dteReschedulefrm;
}
public String getDteReschedulefrm() {
	return dteReschedulefrm;
}
public void setDteProposedto(String dteProposedto) {
	this.dteProposedto = dteProposedto;
}
public String getDteProposedto() {
	return dteProposedto;
}
public void setTimProposedto(String timProposedto) {
	this.timProposedto = timProposedto;
}
public String getTimProposedto() {
	return timProposedto;
}
public void setTimAllotedfrm(String timAllotedfrm) {
	this.timAllotedfrm = timAllotedfrm;
}
public String getTimAllotedfrm() {
	return timAllotedfrm;
}
public void setTimRescheduleto(String timRescheduleto) {
	this.timRescheduleto = timRescheduleto;
}
public String getTimRescheduleto() {
	return timRescheduleto;
}
public void setTimWorkStartto(String timWorkStartto) {
	this.timWorkStartto = timWorkStartto;
}
public String getTimWorkStartto() {
	return timWorkStartto;
}
public void setDteAllotedto(String dteAllotedto) {
	this.dteAllotedto = dteAllotedto;
}
public String getDteAllotedto() {
	return dteAllotedto;
}
public void setDteWorkStartfrm(String dteWorkStartfrm) {
	this.dteWorkStartfrm = dteWorkStartfrm;
}
public String getDteWorkStartfrm() {
	return dteWorkStartfrm;
}
public void setTimWorkStartfrm(String timWorkStartfrm) {
	this.timWorkStartfrm = timWorkStartfrm;
}
public String getTimWorkStartfrm() {
	return timWorkStartfrm;
}
public void setDteWorkStartto(String dteWorkStartto) {
	this.dteWorkStartto = dteWorkStartto;
}
public String getDteWorkStartto() {
	return dteWorkStartto;
}
public void setDteWorkEndfrm(String dteWorkEndfrm) {
	this.dteWorkEndfrm = dteWorkEndfrm;
}
public String getDteWorkEndfrm() {
	return dteWorkEndfrm;
}
public void setDteEqpReleasedfrm(String dteEqpReleasedfrm) {
	this.dteEqpReleasedfrm = dteEqpReleasedfrm;
}
public String getDteEqpReleasedfrm() {
	return dteEqpReleasedfrm;
}
public void setTimWorkEndto(String timWorkEndto) {
	this.timWorkEndto = timWorkEndto;
}
public String getTimWorkEndto() {
	return timWorkEndto;
}
public void setDteWorkApprovalto(String dteWorkApprovalto) {
	this.dteWorkApprovalto = dteWorkApprovalto;
}
public String getDteWorkApprovalto() {
	return dteWorkApprovalto;
}
public void setDteWorkApprovalfrm(String dteWorkApprovalfrm) {
	this.dteWorkApprovalfrm = dteWorkApprovalfrm;
}
public String getDteWorkApprovalfrm() {
	return dteWorkApprovalfrm;
}
public void setDteProductionto(String dteProductionto) {
	this.dteProductionto = dteProductionto;
}
public String getDteProductionto() {
	return dteProductionto;
}
public void setTimWorkApprovalfrm(String timWorkApprovalfrm) {
	this.timWorkApprovalfrm = timWorkApprovalfrm;
}
public String getTimWorkApprovalfrm() {
	return timWorkApprovalfrm;
}
public void setTimEqpReleasedfrm(String timEqpReleasedfrm) {
	this.timEqpReleasedfrm = timEqpReleasedfrm;
}
public String getTimEqpReleasedfrm() {
	return timEqpReleasedfrm;
}
public void setTimWorkEndfrm(String timWorkEndfrm) {
	this.timWorkEndfrm = timWorkEndfrm;
}
public String getTimWorkEndfrm() {
	return timWorkEndfrm;
}
public void setDteWorkEndto(String dteWorkEndto) {
	this.dteWorkEndto = dteWorkEndto;
}
public String getDteWorkEndto() {
	return dteWorkEndto;
}
public void setTimWorkApprovalto(String timWorkApprovalto) {
	this.timWorkApprovalto = timWorkApprovalto;
}
public String getTimWorkApprovalto() {
	return timWorkApprovalto;
}
public void setDteEqpReleasedto(String dteEqpReleasedto) {
	this.dteEqpReleasedto = dteEqpReleasedto;
}
public String getDteEqpReleasedto() {
	return dteEqpReleasedto;
}
public void setTimProductionto(String timProductionto) {
	this.timProductionto = timProductionto;
}
public String getTimProductionto() {
	return timProductionto;
}
public void setTimEqpReleasedto(String timEqpReleasedto) {
	this.timEqpReleasedto = timEqpReleasedto;
}
public String getTimEqpReleasedto() {
	return timEqpReleasedto;
}
public void setDteProductionfrm(String dteProductionfrm) {
	this.dteProductionfrm = dteProductionfrm;
}
public String getDteProductionfrm() {
	return dteProductionfrm;
}
public void setTimProductionfrm(String timProductionfrm) {
	this.timProductionfrm = timProductionfrm;
}
public String getTimProductionfrm() {
	return timProductionfrm;
}

public void setSortOrd(ComboFilter sortOrd) {
	SortOrd = sortOrd;
}
public ComboFilter getSortOrd() {
	return SortOrd;
}
/**
 * @param lessonlike the lessonlike to set
 */
public void setLessonlike(String lessonlike) {
	this.lessonlike = lessonlike;
}
/**
 * @return the lessonlike
 */
public String getLessonlike() {
	return lessonlike;
}
/**
 * @param toRow the toRow to set
 */
public void setToRow(String toRow) {
	this.toRow = toRow;
}
/**
 * @return the toRow
 */
public String getToRow() {
	return toRow;
}
/**
 * @param fromRow the fromRow to set
 */
public void setFromRow(String fromRow) {
	this.fromRow = fromRow;
}
/**
 * @return the fromRow
 */
public String getFromRow() {
	return fromRow;
}
public ComboFilter getSubassembly() {
	return subassembly;
}
public void setSubassembly(ComboFilter subassembly) {
	this.subassembly = subassembly;
}


public void setSpare(ComboFilter spare) {
	this.spare = spare;
}
public ComboFilter getSpare() {
	return spare;
}
public void setDrillFlag(char drillFlag) {
	this.drillFlag = drillFlag;
}
public char getDrillFlag() {
	return drillFlag;
}
public void setYyy(ComboFilter yyy) {
	this.yyy = yyy;
	
}
public ComboFilter getYyy() {
	return yyy;
}
public void setFreq(ComboFilter cmbfreq) {
	
	
}
public String getComboFrom() {
	return comboFrom;
}
public void setComboFrom(String comboFrom) {
	this.comboFrom = comboFrom;
}
public void setEqpActType(String eqpActType) {
	this.eqpActType = eqpActType;
}
public String getEqpActType() {
	return eqpActType;
}
public void setBdCategorized(String bdCategorized) {
	this.bdCategorized = bdCategorized;
}
public String getBdCategorized() {
	return bdCategorized;
}
public void setIsForTotalCnt(char isForTotalCnt) {
	this.isForTotalCnt = isForTotalCnt;
}
public char getIsForTotalCnt() {
	return isForTotalCnt;
}
public void setViewClick(char viewClick) {
	this.viewClick = viewClick;
}
public char getViewClick() {
	return viewClick;
}
public void setTotalRecordCnt(long totalRecordCnt) {
	this.totalRecordCnt = totalRecordCnt;
}
public long getTotalRecordCnt() {
	return totalRecordCnt;
}
/*public void setAutMaintStep(ComboFilter autMaintStep) {
	AutMaintStep = autMaintStep;
}
public ComboFilter getAutMaintStep() {
	return AutMaintStep;
}*/
public void setProductionGroup(String productionGroup) {
	this.productionGroup = productionGroup;
}
public String getProductionGroup() {
	return productionGroup;
}
public void setABNORMALITY(String ABNORMALITY) {
	this.ABNORMALITY = ABNORMALITY;
}
public String getABNORMALITY() {
	return ABNORMALITY;
}
public void setKAIZEN(String kAIZEN) {
	KAIZEN = kAIZEN;
}
public String getKAIZEN() {
	return KAIZEN;
}
public void setBREAKDOWN(String bREAKDOWN) {
	BREAKDOWN = bREAKDOWN;
}
public String getBREAKDOWN() {
	return BREAKDOWN;
}
public void setOPL(String oPL) {
	OPL = oPL;
}
public String getOPL() {
	return OPL;
}
public void setPREVENTIVE(String pREVENTIVE) {
	PREVENTIVE = pREVENTIVE;
}
public String getPREVENTIVE() {
	return PREVENTIVE;
}
public void setIMPROVEMENT(String iMPROVEMENT) {
	IMPROVEMENT = iMPROVEMENT;
}
public String getIMPROVEMENT() {
	return IMPROVEMENT;
}

public void setGENERAL(String gENERAL) {
	GENERAL = gENERAL;
}
public String getGENERAL() {
	return GENERAL;
}

public ComboFilter getAbnJhStep() {
	return abnJhStep;
}
public void setAbnJhStep(ComboFilter abnJhStep) {
	this.abnJhStep = abnJhStep;
}


public void setCmbAuditorName(ComboFilter cmbAuditorName) {
	this.cmbAuditorName = cmbAuditorName;
}
public ComboFilter getCmbAuditorName() {
	return cmbAuditorName;
}


public void setCmbAuditorLevel(ComboFilter AuditorLevel) {
	this.AuditorLevel = AuditorLevel;
}
public ComboFilter getCmbAuditorLevel() {
	return AuditorLevel;
}


public void setCmbFailureType(ComboFilter cmbFailureType) {
	this.cmbFailureType = cmbFailureType;
}
public ComboFilter getCmbFailureType() {
	return cmbFailureType;
}
public void setCmbdefectpheno(ComboFilter cmbdefectpheno) {
	this.cmbdefectpheno = cmbdefectpheno;
}
public ComboFilter getCmbdefectpheno() {
	return cmbdefectpheno;
}
public void setCmbcause(ComboFilter cmbcause) {
	this.cmbcause = cmbcause;
}
public ComboFilter getCmbcause() {
	return cmbcause;
}
public void setCmbbdRootCause(ComboFilter cmbbdRootCause) {
	this.cmbbdRootCause = cmbbdRootCause;
}
public ComboFilter getCmbbdRootCause() {
	return cmbbdRootCause;
}
public void setCmbprodcngroup(ComboFilter cmbprodcngroup) {
	this.cmbprodcngroup = cmbprodcngroup;
}
public ComboFilter getCmbprodcngroup() {
	return cmbprodcngroup;
}
public void setCmbstep(ComboFilter cmbstep) {
	this.cmbstep = cmbstep;
}
public ComboFilter getCmbstep() {
	return cmbstep;
}
public void setCmbshiftIncharge(ComboFilter cmbshiftIncharge) {
	this.cmbshiftIncharge = cmbshiftIncharge;
}
public ComboFilter getCmbshiftIncharge() {
	return cmbshiftIncharge;
}
public void setChkoccurchkbox(String chkoccurchkbox) {
	this.chkoccurchkbox = chkoccurchkbox;
}
public String getChkoccurchkbox() {
	return chkoccurchkbox;
}
public void setChktimeChkBox(String chktimeChkBox) {
	this.chktimeChkBox = chktimeChkBox;
}
public String getChktimeChkBox() {
	return chktimeChkBox;
}
public void setChkzeroBdChkBox(String chkzeroBdChkBox) {
	this.chkzeroBdChkBox = chkzeroBdChkBox;
}
public String getChkzeroBdChkBox() {
	return chkzeroBdChkBox;
}
public void setChkallChkBox(String chkallChkBox) {
	this.chkallChkBox = chkallChkBox;
}
public String getChkallChkBox() {
	return chkallChkBox;
}
public void setChkremallchkbox(String chkremallchkbox) {
	this.chkremallchkbox = chkremallchkbox;
}
public String getChkremallchkbox() {
	return chkremallchkbox;
}
public void setChkfachkbox(String chkfachkbox) {
	this.chkfachkbox = chkfachkbox;
}
public String getChkfachkbox() {
	return chkfachkbox;
}
public void setChkpillarchkbox(String chkpillarchkbox) {
	this.chkpillarchkbox = chkpillarchkbox;
}
public String getChkpillarchkbox() {
	return chkpillarchkbox;
}
public void setChkrcchkbox(String chkrcchkbox) {
	this.chkrcchkbox = chkrcchkbox;
}
public String getChkrcchkbox() {
	return chkrcchkbox;
}
public void setChkrccchkbox(String chkrccchkbox) {
	this.chkrccchkbox = chkrccchkbox;
}
public String getChkrccchkbox() {
	return chkrccchkbox;
}
public void setChkcmchkbox(String chkcmchkbox) {
	this.chkcmchkbox = chkcmchkbox;
}
public String getChkcmchkbox() {
	return chkcmchkbox;
}
public void setChkircchkbox(String chkircchkbox) {
	this.chkircchkbox = chkircchkbox;
}
public String getChkircchkbox() {
	return chkircchkbox;
}
public void setChkiyychkbox(String chkiyychkbox) {
	this.chkiyychkbox = chkiyychkbox;
}
public String getChkiyychkbox() {
	return chkiyychkbox;
}
public void setChktradewise(String chktradewise) {
	this.chktradewise = chktradewise;
}
public String getChktradewise() {
	return chktradewise;
}
public void setChkjobtypwise(String chkjobtypwise) {
	this.chkjobtypwise = chkjobtypwise;
}
public String getChkjobtypwise() {
	return chkjobtypwise;
}
public void setTxttop(String txttop) {
	this.txttop = txttop;
}
public String getTxttop() {
	return txttop;
}
public void setCbooptions(ComboFilter cbooptions) {
	this.cbooptions = cbooptions;
}
public void setcboActivitytype(ComboFilter cboactivitytype) {
	this.cboactivitytype = cboactivitytype;
}
public ComboFilter getcboActivitytype() {
	return cboactivitytype;
}
public ComboFilter getCbooptions() {
	return cbooptions;
}
public void setCmbyy(ComboFilter cmbyy) {
	this.cmbyy = cmbyy;
}
public ComboFilter getCmbyy() {
	return cmbyy;
}
public void setCmbEngineer(ComboFilter cmbEngineer) {
	this.cmbEngineer = cmbEngineer;
}
public ComboFilter getCmbEngineer() {
	return cmbEngineer;
}
public void setCbosparesSelectBox(ComboFilter cbosparesSelectBox) {
	this.cbosparesSelectBox = cbosparesSelectBox;
}
public ComboFilter getCmbsparesSelectBox() {
	return cbosparesSelectBox;
}
public void setCboselBdType(ComboFilter cboselBdType) {
	this.cboselBdType = cboselBdType;
}
public ComboFilter getCboselBdType() {
	return cboselBdType;
}
public void setChkjhchkbox(String chkjhchkbox) {
	this.chkjhchkbox = chkjhchkbox;
}
public String getChkjhchkbox() {
	return chkjhchkbox;
}
public void setChkdesignchkbox(String chkdesignchkbox) {
	this.chkdesignchkbox = chkdesignchkbox;
}
public String getChkdesignchkbox() {
	return chkdesignchkbox;
}
public void setChkpmchkbox(String chkpmchkbox) {
	this.chkpmchkbox = chkpmchkbox;
}
public String getChkpmchkbox() {
	return chkpmchkbox;
}
public void setChketchkbox(String chketchkbox) {
	this.chketchkbox = chketchkbox;
}
public String getChketchkbox() {
	return chketchkbox;
}
public void setGrpByCellWise(String grpByCellWise) {
	GrpByCellWise = grpByCellWise;
}
public String getGrpByCellWise() {
	return GrpByCellWise;
}
public boolean isMachineDirect() {
	return machineDirect;
}
public void setMachineDirect(boolean machineDirect) {
	this.machineDirect = machineDirect;
}
public void setFrequency(ComboFilter frequency) {
	this.frequency = frequency;
}
public ComboFilter getFrequency() {
	return frequency;
}
public void setTxtDurFrom(String txtDurFrom) {
	this.txtDurFrom = txtDurFrom;
}
public String getTxtDurFrom() {
	return txtDurFrom;
}
public void setTxtDurTo(String txtDurTo) {
	this.txtDurTo = txtDurTo;
}
public String getTxtDurTo() {
	return txtDurTo;
}
public String getMachineNotToShown() {
	return machineNotToShown;
}
public void setMachineNotToShown(String machineNotToShown) {
	this.machineNotToShown = machineNotToShown;
}
public String getLineNotToShown() {
	return lineNotToShown;
}
public void setLineNotToShown(String lineNotToShown) {
	this.lineNotToShown = lineNotToShown;
}
public String getPcsEnabled() {
	return pcsEnabled;
}
public void setPcsEnabled(String pcsEnabled) {
	this.pcsEnabled = pcsEnabled;
}
public void setLossId(String lossId) {
	this.lossId = lossId;
}
public String getLossId() {
	return lossId;
}

public void setColVal(String colVal) {
	this.colVal = colVal;
}
public void setRowTotal(Character rowTotal) {
	this.rowTotal = rowTotal;
}
public Character getRowTotal() {
	return rowTotal;
}
public void setMachineArea(ComboFilter machineArea) {
	this.machineArea = machineArea;
}
public ComboFilter getMachineArea() {
	return machineArea;
}
public void setRepportType(String repportType) {
	this.repportType = repportType;
}
public String getRepportType() {
	return repportType;
}

public void setMainkeyid(String mainkeyid) {
	this.mainkeyid = mainkeyid;
}
public String getMainkeyid() {
	return mainkeyid;
}

public void setChkemployee(String chkemployee) {
	this.chkemployee = chkemployee;
}
public String getChkemployee() {
	return chkemployee;
}
public void setChkcontract(String chkcontract) {
	this.chkcontract = chkcontract;
}
public String getChkcontract() {
	return chkcontract;
}
public void setChkspare(String chkspare) {
	this.chkspare = chkspare;
}
public String getChkspare() {
	return chkspare;
}
public void setChkservice(String chkservice) {
	this.chkservice = chkservice;
}
public String getChkservice() {
	return chkservice;
}
public void setChkutility(String chkutility) {
	this.chkutility = chkutility;
}
public String getChkutility() {
	return chkutility;
}
public void setChkother(String chkother) {
	this.chkother = chkother;
}
public String getChkother() {
	return chkother;
}
public void setChktotal(String chktotal) {
	this.chktotal = chktotal;
}
public String getChktotal() {
	return chktotal;
}
public void setChkallparameter(String chkallparameter) {
	this.chkallparameter = chkallparameter;
}
public String getChkallparameter() {
	return chkallparameter;
}
public String getChkundefinedPP() {
	return chkundefinedPP;
}
public void setChkundefinedPP(String chkundefinedPP) {
	this.chkundefinedPP = chkundefinedPP;
}
public void setCboparetooptions(ComboFilter cboparetooptions) {
	this.cboparetooptions = cboparetooptions;
}
public ComboFilter getCboparetooptions() {
	return cboparetooptions;
}
public void setParamCode(String paramCode) {
	this.paramCode = paramCode;
}
public String getParamCode() {
	return paramCode;
}


public void setParamtype(String paramtype) {
	this.paramtype = paramtype;
}
public String getParamtype() {
	return paramtype;
}

public void setIsForRowTotalRow(String isForRowTotalRow) {
	this.isForRowTotalRow = isForRowTotalRow;
}
public String getIsForRowTotalRow() {
	return isForRowTotalRow;
}

public void setPareto(Character pareto) {
	this.pareto = pareto;
}
public Character getPareto() {
	return pareto;
}
public void setChkActwise(String chkActwise) {
	this.chkActwise = chkActwise;
}
public String getChkActwise() {
	return chkActwise;
}
public void setChkMonwise(String chkMonwise) {
	this.chkMonwise = chkMonwise;
}
public String getChkMonwise() {
	return chkMonwise;
}
public void setChkInternal(String chkInternal) {
	this.chkInternal = chkInternal;
}
public String getChkInternal() {
	return chkInternal;
}
public void setChkExternal(String chkExternal) {
	this.chkExternal = chkExternal;
}
public String getChkExternal() {
	return chkExternal;
}
public void setCboJobType(ComboFilter cboJobType) {
	this.cboJobType = cboJobType;
}
public ComboFilter getCboJobType() {
	return cboJobType;
}
public void setCboFrequency(ComboFilter cboFrequency) {
	this.cboFrequency = cboFrequency;
}
public ComboFilter getCboFrequency() {
	return cboFrequency;
}
public void setMonthly(String monthly) {
	this.monthly = monthly;
}
public String getMonthly() {
	return monthly;
}
public void setWeekly(String weekly) {
	Weekly = weekly;
}
public String getWeekly() {
	return Weekly;
}

public ComboFilter getBdstatus() {
	return bdstatus;
}
public void setBdstatus(ComboFilter bdstatus) {
	this.bdstatus = bdstatus;
}
public ComboFilter getCounterMeasure() {
	return counterMeasure;
}
public void setCounterMeasure(ComboFilter counterMeasure) {
	this.counterMeasure = counterMeasure;
}
public String getChkrepeatedBD() {
	return chkrepeatedBD;
}
public void setChkrepeatedBD(String chkrepeatedBD) {
	this.chkrepeatedBD = chkrepeatedBD;
}

public void setLossType(ComboFilter lossType) {
	this.lossType = lossType;
}
public ComboFilter getLossType() {
	return lossType;
}
public void setImprovementDate(String improvementDate) {
	this.improvementDate = improvementDate;
}
public String getImprovementDate() {
	return improvementDate;
}
public void setISFORGRAPH(Character iSFORGRAPH) {
	ISFORGRAPH = iSFORGRAPH;
}
public Character getISFORGRAPH() {
	return ISFORGRAPH;
}
public String getWoapproval() {
	return woapproval;
}
public void setWoapproval(String woapproval) {
	this.woapproval = woapproval;
}
public String getWostatus() {
	return wostatus;
}
public void setWostatus(String wostatus) {
	this.wostatus = wostatus;
}
public String getWoModeForGrid() {
	return woModeForGrid;
}
public void setWoModeForGrid(String woModeForGrid) {
	this.woModeForGrid = woModeForGrid;
}
public void setMainGroup(String mainGroup) {
	this.mainGroup = mainGroup;
}
public String getMainGroup() {
	return mainGroup;
}
public void setActive(Character active) {
	this.active = active;
}
public Character getActive() {
	return active;
}
public void setEquipmentFlag(String equipmentFlag) {
	this.equipmentFlag = equipmentFlag;
}
public String getEquipmentFlag() {
	return equipmentFlag;
}
public void setGridSortColumn(String gridSortColumn) {
	this.gridSortColumn = gridSortColumn;
}
public String getGridSortColumn() {
	return gridSortColumn;
}
public void setGridSortOrder(String gridSortOrder) {
	this.gridSortOrder = gridSortOrder;
}
public String getGridSortOrder() {
	return gridSortOrder;
}
public void setAbnClass(ComboFilter abnClass) {
	this.abnClass = abnClass;
}
public ComboFilter getAbnClass() {
	return abnClass;
}

public String getChkfrequencychkbox() {
	return chkfrequencychkbox;
}
public void setChkfrequencychkbox(String chkfrequencychkbox) {
	this.chkfrequencychkbox = chkfrequencychkbox;
}

public String getChkseveritychkbox() {
	return chkseveritychkbox;
}
public void setChkseveritychkbox(String chkseveritychkbox) {
	this.chkseveritychkbox = chkseveritychkbox;
}


public String getChkOccured() {
	return chkOccured;
}
public void setChkOccured(String chkOccured) {
	this.chkOccured = chkOccured;
}
public String getChkAllotted() {
	return chkAllotted;
}
public void setChkAllotted(String chkAllotted) {
	this.chkAllotted = chkAllotted;
}
public String getChkReported() {
	return chkReported;
}
public void setChkReported(String chkReported) {
	this.chkReported = chkReported;
}
public String getChkWorkStart() {
	return chkWorkStart;
}
public void setChkWorkStart(String chkWorkStart) {
	this.chkWorkStart = chkWorkStart;
}
public String getChkWorkEnd() {
	return chkWorkEnd;
}
public void setChkWorkEnd(String chkWorkEnd) {
	this.chkWorkEnd = chkWorkEnd;
}
public String getChkProdDate() {
	return chkProdDate;
}
public void setChkProdDate(String chkProdDate) {
	this.chkProdDate = chkProdDate;
}
public void setChkdvp(String chkdvp) {
	this.chkdvp = chkdvp;
}
public String getChkdvp() {
	return chkdvp;
}
public void setChkdvm(String chkdvm) {
	this.chkdvm = chkdvm;
}
public String getChkdvm() {
	return chkdvm;
}
public void setChkinstance(String chkinstance) {
	this.chkinstance = chkinstance;
}
public String getChkinstance() {
	return chkinstance;
}
public void setChkquantity(String chkquantity) {
	this.chkquantity = chkquantity;
}
public String getChkquantity() {
	return chkquantity;
}
public void setChkdpc(String chkdpc) {
	this.chkdpc = chkdpc;
}
public String getChkdpc() {
	return chkdpc;
}
public void setQualityPhenomena(String qualityPhenomena) {
	this.qualityPhenomena = qualityPhenomena;
}
public String getQualityPhenomena() {
	return qualityPhenomena;
}
public void setGridFilter(List<GridFilter> gridFilter) {
	this.gridFilter = gridFilter;
}
public List<GridFilter> getGridFilter() {
	return gridFilter;
}
public void setFORGRAPH(Character fORGRAPH) {
	FORGRAPH = fORGRAPH;
}
public Character getFORGRAPH() {
	return FORGRAPH;
}
public String getMaintMode() {
	return maintMode;
}
public void setMaintMode(String maintMode) {
	this.maintMode = maintMode;
}

public void setWeekno(String weekno) {
	this.weekno = weekno;
}
public String getWeekno() {
	return weekno;
}

public void setQtmCause(ComboFilter qtmCause) {
	this.qtmCause = qtmCause;
}
public ComboFilter getQtmCause() {
	return qtmCause;
}
public void setChkjhbox(String chkjhbox) {
	this.chkjhbox = chkjhbox;
}
public String getChkjhbox() {
	return chkjhbox;
}
public void setChkpmbox(String chkpmbox) {
	this.chkpmbox = chkpmbox;
}
public String getChkpmbox() {
	return chkpmbox;
}
public void setChkkkbox(String chkkkbox) {
	this.chkkkbox = chkkkbox;
}
public String getChkkkbox() {
	return chkkkbox;
}
public void setChketbox(String chketbox) {
	this.chketbox = chketbox;
}
public String getChketbox() {
	return chketbox;
}
public void setChk4mbox(String chk4mbox) {
	this.chk4mbox = chk4mbox;
}
public String getChk4mbox() {
	return chk4mbox;
}
public void setChkimpdonebox(String chkimpdonebox) {
	this.chkimpdonebox = chkimpdonebox;
}
public String getChkimpdonebox() {
	return chkimpdonebox;
}
public void setRawMatrial(ComboFilter rawMatrial) {
	this.rawMatrial = rawMatrial;
}
public ComboFilter getRawMatrial() {
	return rawMatrial;
}
public void setChkSkipLine(String chkSkipLine) {
	this.chkSkipLine = chkSkipLine;
}
public String getChkSkipLine() {
	return chkSkipLine;
}
public void setProductModel(ComboFilter productModel) {
	this.productModel = productModel;
}
public ComboFilter getProductModel() {
	return productModel;
}
public void setISNEEDWONO(String iSNEEDWONO) {
	ISNEEDWONO = iSNEEDWONO;
}
public String getISNEEDWONO() {
	return ISNEEDWONO;
}
public void setISNEEDPROD(String iSNEEDPROD) {
	ISNEEDPROD = iSNEEDPROD;
}
public String getISNEEDPROD() {
	return ISNEEDPROD;
}
public void setOptions(ComboFilter options) {
	this.options = options;
}
public ComboFilter getOptions() {
	return options;
}
public void setAssemblyDrillExist(boolean assemblyDrillExist) {
	this.assemblyDrillExist = assemblyDrillExist;
}
public boolean isAssemblyDrillExist() {
	return assemblyDrillExist;
}
public void setWodetailid(String wodetailid) {
	this.wodetailid = wodetailid;
}
public String getWodetailid() {
	return wodetailid;
}
public void setSubGroupHD(String subGroupHD) {
	this.subGroupHD = subGroupHD;
}
public String getSubGroupHD() {
	return subGroupHD;
}
public void setUNPLANNED(String uNPLANNED) {
	UNPLANNED = uNPLANNED;
}
public String getUNPLANNED() {
	return UNPLANNED;
}
public void setMechCond(ComboFilter mechCond) {
	this.mechCond = mechCond;
}
public ComboFilter getMechCond() {
	return mechCond;
}
public void setCheckActivityType(String checkActivityType) {
	this.checkActivityType = checkActivityType;
}
public String getCheckActivityType() {
	return checkActivityType;
}
public void setIsMchwise(String isMchwise) {
	this.isMchwise = isMchwise;
}
public String getIsMchwise() {
	return isMchwise;
}
public void setSkilRating(ComboFilter skilRating) {
	this.skilRating = skilRating;
}
public ComboFilter getSkilRating() {
	return skilRating;
}
public void setRoleId(ComboFilter roleId) {
	this.role = roleId;
}
public ComboFilter getRoleId() {
	return role;
}
public void setSkillType(ComboFilter skillType) {
	this.skillType = skillType;
}
public ComboFilter getSkillType() {
	return skillType;
}
public void setTopic(ComboFilter topic) {
	this.topic = topic;
}
public ComboFilter getTopic() {
	return topic;
}
public void setProductDesc(ComboFilter productDesc) {
	this.productDesc = productDesc;
}
public ComboFilter getProductDesc() {
	return productDesc;
}
public void setMould(ComboFilter mould) {
	this.mould = mould;
}
public ComboFilter getMould() {
	return mould;
}
public void setRelatedToMchMld(String relatedToMchMld) {
	this.relatedToMchMld = relatedToMchMld;
}
public String getRelatedToMchMld() {
	return relatedToMchMld;
}
public void setAbnormalityType(String abnormalityType) {
	this.abnormalityType = abnormalityType;
}
public String getAbnormalityType() {
	return abnormalityType;
}
public void setAbnImpType(ComboFilter abnImpType) {
	AbnImpType = abnImpType;
}
public ComboFilter getAbnImpType() {
	return AbnImpType;
}
public void setAbnImprovement(String abnImprovement) {
	this.abnImprovement = abnImprovement;
}
public String getAbnImprovement() {
	return abnImprovement;
}
public void setChkGrpByEqp(String chkGrpByEqp) {
	this.chkGrpByEqp = chkGrpByEqp;
}
public String getChkGrpByEqp() {
	return chkGrpByEqp;
}
public void setChkAllEqpmnt(String chkAllEqpmnt) {
	this.chkAllEqpmnt = chkAllEqpmnt;
}
public String getChkAllEqpmnt() {
	return chkAllEqpmnt;
}
public ComboFilter getInspectedby() {
	return inspectedby;
}
public void setInspectedby(ComboFilter inspectedby) {
	this.inspectedby = inspectedby;
}
public void setPcsShift(String pcsShift) {
	this.pcsShift = pcsShift;
}
public String getPcsShift() {
	return pcsShift;
}

public ComboFilter getQtyystatus() {
	return qtyystatus;
}


public void setQtyystatus(ComboFilter qtyystatus) {
	this.qtyystatus = qtyystatus;
}
public void setCompleted(String completed) {
	this.completed = completed;
}
public String getCompleted() {
	return completed;
}
public void setPending(String pending) {
	this.pending = pending;
}
public String getPending() {
	return pending;
}
public void setPcsDate(String pcsDate) {
	this.pcsDate = pcsDate;
}
public String getPcsDate() {
	return pcsDate;
}
public String getSftojt() {
	return sftojt;
}
public void setSftojt(String sftojt) {
	this.sftojt = sftojt;
}
public String getSftpokayoke() {
	return sftpokayoke;
}
public void setSftpokayoke(String sftpokayoke) {
	this.sftpokayoke = sftpokayoke;
}
public String getSftmin() {
	return sftmin;
}
public void setSftmin(String sftmin) {
	this.sftmin = sftmin;
}
public String getSftmaj() {
	return sftmaj;
}
public void setSftmaj(String sftmaj) {
	this.sftmaj = sftmaj;
}

public String getSftkzn() {
	return sftkzn;
}
public void setSftkzn(String sftkzn) {
	this.sftkzn = sftkzn;
}
public String getSftopl() {
	return sftopl;
}
public void setSftopl(String sftopl) {
	this.sftopl = sftopl;
}
public void setBdMechCond(ComboFilter bdMechCond) {
	this.bdMechCond = bdMechCond;
}
public ComboFilter getBdMechCond() {
	return bdMechCond;
}
public void setChkAbnType(String chkAbnType) {
	this.chkAbnType = chkAbnType;
}
public String getChkAbnType() {
	return chkAbnType;
}
public void setChkMould(String chkMould) {
	this.chkMould = chkMould;
}
public String getChkMould() {
	return chkMould;
}
public void setChkWhyWhyHappen(String chkWhyWhyHappen) {
	this.chkWhyWhyHappen = chkWhyWhyHappen;
}
public String getChkWhyWhyHappen() {
	return chkWhyWhyHappen;
}
public void setChkTrade(String chkTrade) {
	this.chkTrade = chkTrade;
}
public String getChkTrade() {
	return chkTrade;
}
public void setChkStatus(String chkStatus) {
	this.chkStatus = chkStatus;
}
public String getChkStatus() {
	return chkStatus;
}
public void setChkTagClass(String chkTagClass) {
	this.chkTagClass = chkTagClass;
}
public String getChkTagClass() {
	return chkTagClass;
}
public void setChkHTAType(String chkHTAType) {
	this.chkHTAType = chkHTAType;
}
public String getChkHTAType() {
	return chkHTAType;
}
public void setActivity(ComboFilter activity) {
	this.activity = activity;
}
public ComboFilter getActivity() {
	return activity;
}
public void setRepotingType(String repotingType) {
	this.repotingType = repotingType;
}
public String getRepotingType() {
	return repotingType;
}
public void setMachineWise(String machineWise) {
	MachineWise = machineWise;
}
public String getMachineWise() {
	return MachineWise;
}
public void setEquipmentWise(String equipmentWise) {
	EquipmentWise = equipmentWise;
}
public String getEquipmentWise() {
	return EquipmentWise;
}
public String getSafetyMode() {
	return safetyMode;
}
public void setSafetyMode(String safetyMode) {
	this.safetyMode = safetyMode;
}
public void setQtmProductId(String qtmProductId) {
	this.qtmProductId = qtmProductId;
}
public String getQtmProductId() {
	return qtmProductId;
}
public void setPhenomenaId(String phenomenaId) {
	this.phenomenaId = phenomenaId;
}
public String getPhenomenaId() {
	return phenomenaId;
}
public void setPmstatus(String pmstatus) {
	this.pmstatus = pmstatus;
}
public String getPmstatus() {
	return pmstatus;
}
public ComboFilter getSpoke() {
	return spoke;
}
public void setSpoke(ComboFilter spoke) {
	this.spoke = spoke;
}
public ComboFilter getTopics() {
	return topics;
}
public void setTopics(ComboFilter topics) {
	this.topics = topics;
}
public String getPldetailsId() {
	return pldetailsId;
}
public void setPldetailsId(String pldetailsId) {
	this.pldetailsId = pldetailsId;
}
public void setSkipAssm(String skipAssm) {
	this.skipAssm = skipAssm;
}
public String getSkipAssm() {
	return skipAssm;
}
public void setChkboxMgrCal(String chkboxMgrCal) {
	this.chkboxMgrCal = chkboxMgrCal;
}
public String getChkboxMgrCal() {
	return chkboxMgrCal;
}
public void setChkAssm(String chkAssm) {
	this.chkAssm = chkAssm;
}
public String getChkAssm() {
	return chkAssm;
}
public String getLoss() {
	return loss;
}
public void setLoss(String loss) {
	this.loss = loss;
}
public void setDectetedBy(ComboFilter dectetedBy) {
	this.dectetedBy = dectetedBy;
}
public ComboFilter getDectetedBy() {
	return dectetedBy;
}
public void setResponsibility(ComboFilter responsibility) {
	Responsibility = responsibility;
}
public ComboFilter getResponsibility() {
	return Responsibility;
}
public String getSafetyPatrol() {
	return safetyPatrol;
}
public void setSafetyPatrol(String safetyPatrol) {
	this.safetyPatrol = safetyPatrol;
}
public void setFromPcs(String fromPcs) {
	this.fromPcs = fromPcs;
}
public String getFromPcs() {
	return fromPcs;
}

public String getDocInsType() {
	return docInsType;
}
public void setDocInsType(String docInsType) {
	this.docInsType = docInsType;
}

public void setGroupByCircle(String groupByCircle) {
	this.groupByCircle = groupByCircle;
}
public String getGroupByCircle() {
	return groupByCircle;
}
public String getDocType() {
	return docType;
}
public void setDocType(String docType) {
	this.docType = docType;
}
public void setRemoveBlank(String removeBlank) {
	RemoveBlank = removeBlank;
}
public String getRemoveBlank() {
	return RemoveBlank;
}
public void setBdActivity(String bdActivity) {
	this.bdActivity = bdActivity;
}
public String getBdActivity() {
	return bdActivity;
}
public void setJHKaizenNo(ComboFilter jHKaizenNo) {
	JHKaizenNo = jHKaizenNo;
}
public ComboFilter getJHKaizenNo() {
	return JHKaizenNo;
}

public  void setJHKaizenCategory(ComboFilter JHKaizenCategory)
{
	this.JHKaizencategory=JHKaizenCategory;
	
}
public ComboFilter getJHKaizenCategory()
{
	return JHKaizencategory;
}

public void setPcsshift(String pcsshift) {
	this.pcsshift = pcsshift;
}
public String getPcsshift() {
	return pcsshift;
}
public void setCmbLoss(ComboFilter cmbLoss) {
	this.cmbLoss = cmbLoss;
}
public ComboFilter getCmbLoss() {
	return cmbLoss;
}
public void setManager(ComboFilter manager) {
	this.manager = manager;
}
public ComboFilter getManager() {
	return manager;
}
public void setDefaultDate(String defaultDate) {
	this.defaultDate = defaultDate;
}
public String getDefaultDate() {
	return defaultDate;
}
public void setRefresh(String refresh) {
	this.refresh = refresh;
}
public String getRefresh() {
	return refresh;
}
public void setSubLoss(String subLoss) {
	this.subLoss = subLoss;
}
public String getSubLoss() {
	return subLoss;
}
public void setAreatype(String areatype) {
	this.areatype = areatype;
}
public String getAreatype() {
	return areatype;
}
public void setAuditRpt(String auditRpt) {
	this.auditRpt = auditRpt;
}
public String getAuditRpt() {
	return auditRpt;
}
public void setLossRptType(String lossRptType) {
	this.lossRptType = lossRptType;
}
public String getLossRptType() {
	return lossRptType;
}
public void setChartType(String chartType) {
	this.chartType = chartType;
}
public String getChartType() {
	return chartType;
}
public ComboFilter getCmbBreakdown() {
	return cmbBreakdown;
}
public void setCmbBreakdown(ComboFilter cmbBreakdown) {
	this.cmbBreakdown = cmbBreakdown;
}
public ComboFilter getCmbMsr() {
	return cmbMsr;
}
public void setCmbMsr(ComboFilter cmbMsr) {
	this.cmbMsr = cmbMsr;
}
public void setIstotal(String istotal) {
	this.istotal = istotal;
}
public String getIstotal() {
	return istotal;
}

public void setChkActType(String chkActType) {
	this.chkActType = chkActType;
}
public String getChkActType() {
	return chkActType;
}

public void setIsneedroleid(String isneedroleid) {
	this.isneedroleid = isneedroleid;
}
public String getIsneedroleid() {
	return isneedroleid;
}
public void setCmbActivity(ComboFilter cmbActivity) {
	this.cmbActivity = cmbActivity;
}
public ComboFilter getCmbActivity() {
	return cmbActivity;
}
public void setCmbCategory(ComboFilter cmbCategory) {
	this.cmbCategory = cmbCategory;
}
public ComboFilter getCmbCategory() {
	return cmbCategory;
}
public void setCmbSubCategory(ComboFilter cmbSubCategory) {
	this.cmbSubCategory = cmbSubCategory;
}
public ComboFilter getCmbSubCategory() {
	return cmbSubCategory;
}
public void setCmbResponsibility(ComboFilter cmbResponsibility) {
	this.cmbResponsibility = cmbResponsibility;
}
public ComboFilter getCmbResponsibility() {
	return cmbResponsibility;
}
public void setCmbAssignedto(ComboFilter cmbAssignedto) {
	this.cmbAssignedto = cmbAssignedto;
}
public ComboFilter getCmbAssignedto() {
	return cmbAssignedto;
}
public void setCmbStatus(ComboFilter cmbStatus) {
	this.cmbStatus = cmbStatus;
}
public ComboFilter getCmbStatus() {
	return cmbStatus;
}
public void setDtestart(String dtestart) {
	this.dtestart = dtestart;
}
public String getDtestart() {
	return dtestart;
}
public void setDteend(String dteend) {
	this.dteend = dteend;
}
public String getDteend() {
	return dteend;
}
public void setDteActualStart(String dteActualStart) {
	this.dteActualStart = dteActualStart;
}
public String getDteActualStart() {
	return dteActualStart;
}
public void setDteEnd(String dteEnd) {
	this.dteEnd = dteEnd;
}
public String getDteEnd() {
	return dteEnd;
}
public void setMultiLoss(String multiLoss) {
	this.multiLoss = multiLoss;
}
public String getMultiLoss() {
	return multiLoss;
}
public void setTeamId(String teamId) {
	this.teamId = teamId;
}
public String getTeamId() {
	return teamId;
}
public void setTeamLevelNo(String teamLevelNo) {
	this.teamLevelNo = teamLevelNo;
}
public String getTeamLevelNo() {
	return teamLevelNo;
}
public void setJhTemplate(ComboFilter jhTemplate) {
	JhTemplate = jhTemplate;
}
public ComboFilter getJhTemplate() {
	return JhTemplate;
}
public void setJhTemplateId(String JhTemplateId) {
	this.JhTemplateId = JhTemplateId;
}
public String getJhTemplateId() {
	// TODO Auto-generated method stub
	return JhTemplateId;
}
public void setBreakup(String Breakup) {
	this.Breakup = Breakup ;
}

public void setTxtValue(String txtValue) {
	this.txtValue = txtValue;
}
public String getTxtValue() {
	return txtValue;
}
public void setTxtnxtValue(String txtnxtValue) {
	this.txtnxtValue = txtnxtValue;
}
public String getTxtnxtValue() {
	return txtnxtValue;
}
public void setCboElapsedDays(ComboFilter cboElapsedDays) {
	this.cboElapsedDays = cboElapsedDays;
}
public ComboFilter getCboElapsedDays() {
	return cboElapsedDays;
}
public void setMultipleval(String multipleval) {
	this.multipleval = multipleval;
}
public String getMultipleval() {
	return multipleval;
}
public void setMultipletype(String multipletype) {
	Multipletype = multipletype;
}
public String getMultipletype() {
	return Multipletype;
}


public String getBreakup() {
	return Breakup;
}

public void setFREQ(String FREQ) {
	this.FREQ = FREQ ;
}

public String getFREQ() {
	return FREQ;
}
public void setBTO(String BTO) {
	this.BTO = BTO ;
}

public String getBTO() {
	return BTO;
}
public void setBFROM(String BFROM) {
	this.BFROM = BFROM ;
}

public String getBFROM() {
	return BFROM;
}
public void setBreakUpList(List<CommonFilter> BreakUpList) {
	this.BreakUpList = BreakUpList ;
}

public List<CommonFilter> getBreakUpList() {
	return BreakUpList;
}
public void setQuality(String quality) {
	this.quality = quality;
}
public String getQuality() {
	return quality;
}
public void setFunctionName(String functionName) {
	this.functionName = functionName;
}
public String getFunctionName() {
	return functionName;
}
public void setSbu(ComboFilter sbu) {
	this.sbu = sbu;
}
public ComboFilter getSbu() {
	return sbu;
}
public void setPbu(ComboFilter pbu) {
	this.pbu = pbu;
}
public ComboFilter getPbu() {
	return pbu;
}
public String getFlid() {
	return flid;
}
public void setFlid(String flid) {
	this.flid = flid;
}
public void setEmpwiseType(String EmpwiseType) {
	empwiseType = EmpwiseType;
}
public String getEmpwiseType() {
	return empwiseType;
}
public void setKznBankType(String KznBankType) {
	kznBankType = KznBankType;
}
public String getKznBankType() {
	return kznBankType;
}

public void setAssType(String AssType) {
	assType = AssType;
}
public String getAssType() {
	return assType;
}

public void setProgram(String program) {
	this.program = program;
}
public String getProgram() {
	return program;
}
public void setTopicid(String topicid) {
	this.topicid = topicid;
}
public String getTopicid() {
	return topicid;
}
public void setVisualKeyId(String visualKeyId) {
	VisualKeyId = visualKeyId;
}
public String getVisualKeyId() {
	return VisualKeyId;
}
public void setRefdocid(String refdocid) {
	Refdocid = refdocid;
}
public String getRefdocid() {
	return Refdocid;
}
public void setDmtdetailid(String dmtdetailid) {
	Dmtdetailid = dmtdetailid;
}
public String getDmtdetailid() {
	return Dmtdetailid;
}
public void setDrillLevelNo(String drillLevelNo) {
	this.drillLevelNo = drillLevelNo;
}
public String getDrillLevelNo() {
	return drillLevelNo;
}

public void setTaskid(String taskid) {
	this.taskid = taskid;
}
public String getTaskid() {
	return taskid;
}
public void setActionKeyId(String actionKeyId) {
	this.actionKeyId = actionKeyId;
}
public String getActionKeyId() {
	return actionKeyId;
}

public void settitle(String title) {
	this.title = title;
}
public String gettitle() {
	return title;
}



public void setDmt(String dmt) {
	this.dmt = dmt;
}
public String getDmt() {
	return dmt;
}
public void setJh(String jh) {
	this.jh = jh;
}
public String getJh() {
	return jh;
}
public void setEmmLinkKeyId(String emmLinkKeyId) {
	this.emmLinkKeyId = emmLinkKeyId;
}
public String getEmmLinkKeyId() {
	return emmLinkKeyId;
}
/**
 * @param effectiveness the effectiveness to set
 */
public void setEffectiveness(ComboFilter effectiveness) {
	this.effectiveness = effectiveness;
}
/**
 * @return the effectiveness
 */
public ComboFilter getEffectiveness() {
	return effectiveness;
}
/**
 * @param yyNo the yyNo to set
 */
public void setYyNo(String yyNo) {
	this.yyNo = yyNo;
}
/**
 * @return the yyNo
 */
public String getYyNo() {
	return yyNo;
}
/**
 * @param setAcceptenceRequired the setAcceptenceRequired to set
 */
public void setAcceptenceRequired(String acceptenceRequired) {
	this.acceptenceRequired = acceptenceRequired;
}
/**
 * @return the setAcceptenceRequired
 */
public String getAcceptenceRequired() {
	return acceptenceRequired;
}

public void setReason(ComboFilter reason) {
	this.reason = reason;
}
public ComboFilter getReason() {
	return reason;
}
/**
 * @param uniquePos the uniquePos to set
 */
public void setUniquePos(String uniquePos) {
	this.uniquePos = uniquePos;
}
/**
 * @return the uniquePos
 */
public String getUniquePos() {
	return uniquePos;
}
public ComboFilter getSourceOfKPI() {
	return sourceOfKPI;
}
public void setSourceOfKPI(ComboFilter sourceOfKPI) {
	this.sourceOfKPI = sourceOfKPI;
}
public String getIndicator() {
	return indicator;
}
public void setIndicator(String indicator) {
	this.indicator = indicator;
}
public void setMPWorthy(String MPWorthy) {
	this.MPWorthy = MPWorthy;
}
public String getMPWorthy() {
	return MPWorthy;
}

public void setUtiliseFuture(String UtiliseFuture) {
	this.UtiliseFuture = UtiliseFuture;
}
public String getUtiliseFuture() {
	return UtiliseFuture;
}

public void setCheckType(ComboFilter checkType) {
	this.checkType = checkType;
}
public ComboFilter getCheckType() {
	return checkType;
}

public String getRelatedtos() {
	return relatedtos;
}
public void setRelatedtos(String relatedtos) {
	this.relatedtos = relatedtos;
}
public String getElementId() {
	return elementId;
}
public void setElementId(String elementId) {
	this.elementId = elementId;
}
public String getRepeatedAbn() {
	return repeatedAbn;
}
public void setRepeatedAbn(String repeatedAbn) {
	this.repeatedAbn = repeatedAbn;
}
public String getRoleLevel() {
	return roleLevel;
}
public void setRoleLevel(String roleLevel) {
	this.roleLevel = roleLevel;
}
public String getHeader2() {
	return Header2;
}
public void setHeader2(String header2) {
	Header2 = header2;
}
public String getColumnId() {
	return columnId;
}
public void setColumnId(String columnId) {
	this.columnId = columnId;
}
public String getColIndexName() {
	return colIndexName;
}
public void setColIndexName(String colIndexName) {
	this.colIndexName = colIndexName;
}
public String getFirstLevel() {
	return firstLevel;
}
public void setFirstLevel(String firstLevel) {
	this.firstLevel = firstLevel;
}
public String getAbnViewType() {
	return abnViewType;
}
public void setAbnViewType(String abnViewType) {
	this.abnViewType = abnViewType;
}
public void setTemp(String temp) {
	this.temp = temp;
}
public String getTemp() {
	return temp;
}
public ComboFilter getSapLocation() {
	return sapLocation;
}
public void setSapLocation(ComboFilter sapLocation) {
	this.sapLocation = sapLocation;
}
public ComboFilter getKznmThemecategoryid() {
	return kznmThemecategoryid;
}
public void setKznmThemecategoryid(ComboFilter kznmThemecategoryid) {
	this.kznmThemecategoryid = kznmThemecategoryid;
}
public void setResultAreaP(String resultAreaP){
	ResultAreaP=resultAreaP;
}
 public String getResultAreaP(){
	 return ResultAreaP;
 }
 public void setResultAreaQ(String resultAreaQ){
		ResultAreaQ=resultAreaQ;
	}
 public String getResultAreaQ(){
	return ResultAreaQ;
	 }
 public void setResultAreaC(String resultAreaC){
		ResultAreaC=resultAreaC;
	}
public String getResultAreaC(){
	return ResultAreaC;
	 }
public void setResultAreaD(String resultAreaD){
	ResultAreaD=resultAreaD;
}
public String getResultAreaD(){
  return ResultAreaD;
 }
public void setResultAreaS(String resultAreaS){
	ResultAreaS=resultAreaS;
}
public String getResultAreaS(){
  return ResultAreaS;
 }
public void setResultAreaM(String resultAreaM){
	ResultAreaM=resultAreaM;
}
public String getResultAreaM(){
  return ResultAreaM;
 }
public void setResultAreaE(String resultAreaE){
	ResultAreaE=resultAreaE;
}
public String getResultAreaE(){
  return ResultAreaE;
 }
}