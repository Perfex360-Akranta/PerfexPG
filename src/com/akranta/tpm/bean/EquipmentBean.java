package com.akranta.tpm.bean;

import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.GenTlMachineskillmst;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class EquipmentBean {
	
	private String formActionMode;
	private FormModes formMode;
	private String factory;
	private String formHeader;
	private String company;
	private String mchmfact;
	private String cmbMchmSect;
	private String subsection;
	private String provider;
	private String assetNo;
	private String poPrice;
	private String section;
	private String Phase;
	private String pkeyid;
	private String Parameter;
	private String txtmplkShortval;
	private String txtmplkDescription;
	private String EqpParamMode;
	private String ReplaceText;
	private String ReplaceValue;
	private String Decode;
	private String ToChar;
	private String mchId;
	private String createdby;
	
	private boolean disableForm ;
	private String actionmode;
	private boolean disableMchmKeyid;
	private boolean disableMchmCellid;
	private boolean disableMchmSubcellid;
	private boolean disableMchmfact;
	private boolean disableMchmCostcentreid;
	private boolean disableMchmMachineno;
	private boolean disableCompany;
	private boolean disableSection;
	private boolean disableMchmEquipmentgroup;
	private boolean disableMchmMachinename;
	private boolean disableMchmControltype;
	private boolean disableMchmWorkcenter;
	private boolean disableMchmMachinerank;
	private boolean disableMchmCircleid;
	private boolean disableMchmPurpose;
	private boolean disableMchmCategory;
	private boolean disableMchmSubcategory;
	private boolean disableMchmInstalleddate;
	private boolean disableMchmEffectivedate;
	private boolean disableMchmJhstep;
	private boolean disableMchmJhstepdate;
	private boolean disableMchmPowersupply;
	private boolean disableMchmConnectedload;
	private boolean disableMchmDbno;
	private boolean disableMchmSbno;
	private boolean disableMchmPhase;
	private boolean disableMchmWires;
	private boolean disableMchmIpvolt;
	private boolean disableMchmIpvoltmin;
	private boolean disableMchmIpvoltmax;
	private boolean disableMchmIpfreq;
	private boolean disableMchmIpfreqmin;
	private boolean disableMchmIpfreqmax;
	private boolean disableMchmManufacturerid;
	private boolean disableMchmMake;
	private boolean disableMchmModel;
	private boolean disableMchmMfrslno;
	private boolean disableMchmManufactureddate;
	private boolean disableMchmMfrremarks;
	private boolean disableMchmSupplierid;
	private boolean disableMchmPono;
	private boolean disableMchmPodate;
	private boolean disableMchmPurchaseprice;
	private boolean disableMchmCurrencyid;
	private boolean disableMchmPurchasedate;
	private boolean disableMchmWarrantydate;
	private boolean disableMchmSupplierremarks;
	private boolean disableMchmAmcvendor;
	private boolean disableMchmAmcdate;
	private boolean disableMchmAmcrenewaldate;
	private boolean disableMchmAmcremarks;
	private boolean disableGrdoperator;
	private boolean disableMchmIscavityormandrel;
	private boolean disableMchmGivesfinaloutput;
	private boolean disableMchmIncludeforproduction;
	
	private GenTlMachineskillmst maintaince;
	public EquipmentBean(String mode)
	{
		this.formMode = FormModes.create;
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	
	public EquipmentBean()
	{
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.disableForm = false;
	}
	
	public EquipmentBean(FormModes mode)
	{
		formModeSettings(mode);		

	}
	
	public EquipmentBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableMchmKeyid = this.disableMchmMachinename =  this.disableMchmMachineno = true;
			this.disableMchmAmcdate = true;
			this.disableMchmAmcremarks = this.disableMchmAmcrenewaldate =true;
			this.disableMchmAmcvendor = this.disableMchmCategory = true;
			this.disableMchmCellid = this.disableMchmCircleid = true;
			this.disableMchmConnectedload = this.disableMchmControltype = true;
			this.disableMchmCostcentreid = this.disableMchmCurrencyid = true;
			this.disableMchmDbno = this.disableMchmEffectivedate = true;
			this.disableMchmEquipmentgroup = this.disableMchmfact = true;
			this.disableMchmInstalleddate = this.disableMchmIpfreq = true;
			this.disableMchmIpfreqmax = this.disableMchmIpvoltmin = true;
			this.disableMchmIpvolt = this.disableMchmIpvoltmax = true;
			this.disableMchmIpvoltmin = this.disableMchmJhstep = true;
			this.disableMchmJhstepdate = this.disableMchmMachinerank = true;
			this.disableMchmMake = this.disableMchmManufactureddate = true;
			this.disableMchmManufacturerid = this.disableMchmMfrremarks = true;
			this.disableMchmMfrslno = this.disableMchmModel = true;
			this.disableMchmPhase = this.disableMchmPodate =true;
			this.disableMchmPono = this.disableMchmPowersupply = true;
			this.disableMchmPurchasedate = this.disableMchmPurchaseprice = true;
			this.disableMchmPurpose = this.disableMchmSbno = true;
			this.disableMchmSubcategory = this.disableMchmSubcellid = true;
			this.disableMchmSupplierid = this.disableMchmSupplierremarks = true;
			this.disableMchmWarrantydate = this.disableMchmWires = true;
			this.disableMchmWorkcenter = this.disableSection = true;
			this.disableMchmIpfreqmin = this.disableGrdoperator=true;
			this.disableCompany = disableMchmIscavityormandrel = true;
			this.disableMchmGivesfinaloutput = disableMchmIncludeforproduction = true;
		}
		else if(mode == FormModes.modify){
			//this.disableForm = false;
			this.formActionMode = FormModeConsts.modify;
		}
		else
			this.formActionMode = FormModeConsts.create;
		
	}
	
	private void lockFormControls(String lockFields){
		
		if( UIUtils.isValidKeyId(lockFields))
		{	
			String [] lockFieldNames = lockFields.split(",");
			for(String fieldName :lockFieldNames){
				
				if( fieldName.equals("AMCDATE")){
					this.disableMchmAmcdate = true;
				}
				else if( fieldName.equals("AMCREMARKS")) {
					this.disableMchmAmcremarks = true;
				}
				else if( fieldName.equals("AMCRENEWALDATE")) {
					this.disableMchmAmcrenewaldate= true;
				}
				else if( fieldName.equals("AMCVENDOR")) {
					this.disableMchmAmcvendor = true;
				}
				
				else if( fieldName.equals("CATEGORY")) {
					this.disableMchmCategory = true;
				}
				else if( fieldName.equals("CELLID")) {
					this.disableMchmCellid = true;
				}
				else if( fieldName.equals("CIRCLEID")) {
					this.disableMchmCircleid = true;
				}
				else if( fieldName.equals("CONNECTEDLOAD")) {
					this.disableMchmConnectedload = true;
				}
				else if( fieldName.equals("CONTROLTYPE")) {
					this.disableMchmControltype = true;
				}
				else if( fieldName.equals("COSTCENTERID")) {
					this.disableMchmCostcentreid = true;
				}
				else if( fieldName.equals("CURRENCYID")) {
					this.disableMchmCurrencyid = true;
				}
				else if( fieldName.equals("DBNO")) {
					this.disableMchmDbno = true;
				}
				else if( fieldName.equals("EFFECTIVEDATE")) {
					this.disableMchmEffectivedate = true;
				}
				else if( fieldName.equals("EQUIPMENTGROUP")) {
					this.disableMchmEquipmentgroup = true;
				}
				else if( fieldName.equals("FACTORY")) {
					this.disableMchmfact = true;
				}
				else if( fieldName.equals("INSTALLEDDATE")) {
					this.disableMchmInstalleddate = true;
				}
				else if( fieldName.equals("IPFREQ")) {
					this.disableMchmIpfreq = true;
				}
				else if( fieldName.equals("IPFREQMAX")) {
					this.disableMchmIpfreqmax = true;
				}
				else if( fieldName.equals("IPFREQMIN")) {
					this.disableMchmIpfreqmin = true;
				}
				else if( fieldName.equals("IPVOLT")) {
					this.disableMchmIpvolt = true;
				}
				else if( fieldName.equals("IPVOLTMAX")) {
					this.disableMchmIpvoltmax = true;
				}
				else if( fieldName.equals("IPVOLTMIN")) {
					this.disableMchmIpvoltmin = true;
				}
				else if( fieldName.equals("JHSTEP")) {
					this.disableMchmJhstep = true;
				}
				else if( fieldName.equals("JHSTEPDATE")) {
					this.disableMchmJhstepdate = true;
				}
				else if( fieldName.equals("KEYID")) {
					this.disableMchmKeyid = true;
				}
				else if( fieldName.equals("MACHINENAME")) {
					this.disableMchmMachinename = true;
				}
				else if( fieldName.equals("MACHINENO")) {
					this.disableMchmMachineno = true;
				}		
				
				
				else if( fieldName.equals("MACHINERANK")) {
					this.disableMchmMachinerank = true;
				}
				else if( fieldName.equals("MAKE")) {
					this.disableMchmMake = true;
				}
				else if( fieldName.equals("MANUFACTURERDATE")) {
					this.disableMchmManufactureddate = true;
				}
				else if( fieldName.equals("MANUFACTURERID")) {
					this.disableMchmManufacturerid = true;
				}
				else if( fieldName.equals("MFRREMARKS")) {
					this.disableMchmMfrremarks = true;
				}
				else if( fieldName.equals("MFRSSLNO")) {
					this.disableMchmMfrslno = true;
				}
				
				else if( fieldName.equals("MODEL")) {
					this.disableMchmModel = true;
				}
				else if( fieldName.equals("PHASE")) {
					this.disableMchmPhase = true;
				}
				else if( fieldName.equals("PODATE")) {
					this.disableMchmPodate = true;
				}
				else if( fieldName.equals("PONO")) {
					this.disableMchmPono = true;
				}					
				
				else if( fieldName.equals("POWERSUPPLY")) {
					this.disableMchmPowersupply = true;
				}
				else if( fieldName.equals("PURCHASEDATE")) {
					this.disableMchmPurchasedate = true;
				}
				else if( fieldName.equals("PURCHASESPRICE")) {
					this.disableMchmPurchaseprice = true;
				}
				else if( fieldName.equals("PURPOSE")) {
					this.disableMchmPurpose = true;
				}
				else if( fieldName.equals("SBNO")) {
					this.disableMchmSbno = true;
				}
				else if( fieldName.equals("SUBCATEGORY")) {
					this.disableMchmSubcategory = true;
				}
				else if( fieldName.equals("SUBCELLID")) {
					this.disableMchmSubcellid = true;
				}
				else if( fieldName.equals("SUPPLIERID")) {
					this.disableMchmSupplierid = true;
				}
				
				else if( fieldName.equals("SUPPLIERREMARKS")) {
					this.disableMchmSupplierremarks = true;
				}
				else if( fieldName.equals("WARRANTYDATE")) {
					this.disableMchmWarrantydate = true;
				}
				else if( fieldName.equals("WIRES")) {
					this.disableMchmWires = true;
				}
				else if( fieldName.equals("WORKCENTER")) {
					this.disableMchmWorkcenter = true;
				}
				else if( fieldName.equals("SECTION")) {
					this.disableSection = true;
				}
				else if( fieldName.equals("GRDOPERATOR")){
					this.disableGrdoperator = true;
				}
				else if( fieldName.equals("COMPANY"))
					this.disableCompany = true;
				
				else if( fieldName.equals("ISCAVITYORMANDREL"))
					this.disableMchmIscavityormandrel = true;
				
				else if( fieldName.equals("GIVESFINALOUTPUT"))
					this.disableMchmGivesfinaloutput = true;
					
				else if( fieldName.equals("INCLUDEFORPRODUCTION"))
					this.disableMchmIncludeforproduction = true;
			}
		}
			
	}
	
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	public FormModes getFormMode() {
		return formMode;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getFormHeader() {
		return formHeader;
	}
	
	
	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}
	public String getSubsection() {
		return subsection;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProvider() {
		return provider;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getAssetNo() {
		return assetNo;
	}
	public void setPoPrice(String poPrice) {
		this.poPrice = poPrice;
	}
	public String getPoPrice() {
		return poPrice;
	}
	public void setMaintaince(GenTlMachineskillmst maintaince) {
		this.maintaince = maintaince;
	}
	public GenTlMachineskillmst getMaintaince() {
		return maintaince;
	}
	public List<GenTlMachineskillmst> getMaintainceSkillGrid() {
		
		return null;
	}
	public void setCmbMchmSect(String cmbMchmSect) {
		this.cmbMchmSect = cmbMchmSect;
	}
	public String getCmbMchmSect() {
		return cmbMchmSect;
	}
	
	public void setPkeyid(String pkeyid) {
		this.pkeyid = pkeyid;
	}
	public String getPkeyid() {
		return pkeyid;
	}
	public void setParameter(String parameter) {
		Parameter = parameter;
	}
	public String getParameter() {
		return Parameter;
	}
	public void setEqpParamMode(String eqpParamMode) {
		EqpParamMode = eqpParamMode;
	}
	public String getEqpParamMode() {
		return EqpParamMode;
	}
	public void setTxtmplkDescription(String txtmplkDescription) {
		this.txtmplkDescription = txtmplkDescription;
	}
	public String getTxtmplkDescription() {
		return txtmplkDescription;
	}
	public void setTxtmplkShortval(String txtmplkShortval) {
		this.txtmplkShortval = txtmplkShortval;
	}
	public String getTxtmplkShortval() {
		return txtmplkShortval;
	}
	public void setReplaceValue(String replaceValue) {
		ReplaceValue = replaceValue;
	}
	public String getReplaceValue() {
		return ReplaceValue;
	}
	public void setDecode(String decode) {
		Decode = decode;
	}
	public String getDecode() {
		return Decode;
	}
	public void setReplaceText(String replaceText) {
		ReplaceText = replaceText;
	}
	public String getReplaceText() {
		return ReplaceText;
	}
	public void setToChar(String toChar) {
		ToChar = toChar;
	}
	public String getToChar() {
		return ToChar;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSection() {
		return section;
	}
	public void setPhase(String phase) {
		Phase = phase;
	}
	public String getPhase() {
		return Phase;
	}
	public void setMchmfact(String mchmfact) {
		this.mchmfact = mchmfact;
	}
	public String getMchmfact() {
		return mchmfact;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactory() {
		return factory;
	}
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setDisableMchmCellid(boolean disableMchmCellid) {
		this.disableMchmCellid = disableMchmCellid;
	}
	public boolean isDisableMchmCellid() {
		return disableMchmCellid;
	}
	public void setDisableMchmKeyid(boolean disableMchmKeyid) {
		this.disableMchmKeyid = disableMchmKeyid;
	}
	public boolean isDisableMchmKeyid() {
		return disableMchmKeyid;
	}
	public void setDisableMchmSubcellid(boolean disableMchmSubcellid) {
		this.disableMchmSubcellid = disableMchmSubcellid;
	}
	public boolean isDisableMchmSubcellid() {
		return disableMchmSubcellid;
	}
	public void setDisableMchmfact(boolean disableMchmfact) {
		this.disableMchmfact = disableMchmfact;
	}
	public boolean isDisableMchmfact() {
		return disableMchmfact;
	}
	public void setDisableMchmCostcentreid(boolean disableMchmCostcentreid) {
		this.disableMchmCostcentreid = disableMchmCostcentreid;
	}
	public boolean isDisableMchmCostcentreid() {
		return disableMchmCostcentreid;
	}
	public void setDisableMchmMachineno(boolean disableMchmMachineno) {
		this.disableMchmMachineno = disableMchmMachineno;
	}
	public boolean isDisableMchmMachineno() {
		return disableMchmMachineno;
	}
	public void setDisableSection(boolean disableSection) {
		this.disableSection = disableSection;
	}
	public boolean isDisableSection() {
		return disableSection;
	}
	public void setDisableMchmEquipmentgroup(boolean disableMchmEquipmentgroup) {
		this.disableMchmEquipmentgroup = disableMchmEquipmentgroup;
	}
	public boolean isDisableMchmEquipmentgroup() {
		return disableMchmEquipmentgroup;
	}
	public void setDisableMchmMachinename(boolean disableMchmMachinename) {
		this.disableMchmMachinename = disableMchmMachinename;
	}
	public boolean isDisableMchmMachinename() {
		return disableMchmMachinename;
	}
	public void setDisableMchmControltype(boolean disableMchmControltype) {
		this.disableMchmControltype = disableMchmControltype;
	}
	public boolean isDisableMchmControltype() {
		return disableMchmControltype;
	}
	public void setDisableMchmWorkcenter(boolean disableMchmWorkcenter) {
		this.disableMchmWorkcenter = disableMchmWorkcenter;
	}
	public boolean isDisableMchmWorkcenter() {
		return disableMchmWorkcenter;
	}
	public void setDisableMchmMachinerank(boolean disableMchmMachinerank) {
		this.disableMchmMachinerank = disableMchmMachinerank;
	}
	public boolean isDisableMchmMachinerank() {
		return disableMchmMachinerank;
	}
	public void setDisableMchmCircleid(boolean disableMchmCircleid) {
		this.disableMchmCircleid = disableMchmCircleid;
	}
	public boolean isDisableMchmCircleid() {
		return disableMchmCircleid;
	}
	public void setDisableMchmPurpose(boolean disableMchmPurpose) {
		this.disableMchmPurpose = disableMchmPurpose;
	}
	public boolean isDisableMchmPurpose() {
		return disableMchmPurpose;
	}
	public void setDisableMchmCategory(boolean disableMchmCategory) {
		this.disableMchmCategory = disableMchmCategory;
	}
	public boolean isDisableMchmCategory() {
		return disableMchmCategory;
	}
	public void setDisableMchmInstalleddate(boolean disableMchmInstalleddate) {
		this.disableMchmInstalleddate = disableMchmInstalleddate;
	}
	public boolean isDisableMchmInstalleddate() {
		return disableMchmInstalleddate;
	}
	public void setDisableMchmSubcategory(boolean disableMchmSubcategory) {
		this.disableMchmSubcategory = disableMchmSubcategory;
	}
	public boolean isDisableMchmSubcategory() {
		return disableMchmSubcategory;
	}
	public void setDisableMchmEffectivedate(boolean disableMchmEffectivedate) {
		this.disableMchmEffectivedate = disableMchmEffectivedate;
	}
	public boolean isDisableMchmEffectivedate() {
		return disableMchmEffectivedate;
	}
	public void setDisableMchmJhstep(boolean disableMchmJhstep) {
		this.disableMchmJhstep = disableMchmJhstep;
	}
	public boolean isDisableMchmJhstep() {
		return disableMchmJhstep;
	}
	public void setDisableMchmJhstepdate(boolean disableMchmJhstepdate) {
		this.disableMchmJhstepdate = disableMchmJhstepdate;
	}
	public boolean isDisableMchmJhstepdate() {
		return disableMchmJhstepdate;
	}
	public void setDisableMchmPowersupply(boolean disableMchmPowersupply) {
		this.disableMchmPowersupply = disableMchmPowersupply;
	}
	public boolean isDisableMchmPowersupply() {
		return disableMchmPowersupply;
	}
	public void setDisableMchmConnectedload(boolean disableMchmConnectedload) {
		this.disableMchmConnectedload = disableMchmConnectedload;
	}
	public boolean isDisableMchmConnectedload() {
		return disableMchmConnectedload;
	}
	public void setDisableMchmDbno(boolean disableMchmDbno) {
		this.disableMchmDbno = disableMchmDbno;
	}
	public boolean isDisableMchmDbno() {
		return disableMchmDbno;
	}
	public void setDisableMchmSbno(boolean disableMchmSbno) {
		this.disableMchmSbno = disableMchmSbno;
	}
	public boolean isDisableMchmSbno() {
		return disableMchmSbno;
	}
	public void setDisableMchmPhase(boolean disableMchmPhase) {
		this.disableMchmPhase = disableMchmPhase;
	}
	public boolean isDisableMchmPhase() {
		return disableMchmPhase;
	}
	public void setDisableMchmWires(boolean disableMchmWires) {
		this.disableMchmWires = disableMchmWires;
	}
	public boolean isDisableMchmWires() {
		return disableMchmWires;
	}
	public void setDisableMchmIpvolt(boolean disableMchmIpvolt) {
		this.disableMchmIpvolt = disableMchmIpvolt;
	}
	public boolean isDisableMchmIpvolt() {
		return disableMchmIpvolt;
	}
	public void setDisableMchmIpfreqmin(boolean disableMchmIpfreqmin) {
		this.disableMchmIpfreqmin = disableMchmIpfreqmin;
	}
	public boolean isDisableMchmIpfreqmin() {
		return disableMchmIpfreqmin;
	}
	public void setDisableMchmIpvoltmax(boolean disableMchmIpvoltmax) {
		this.disableMchmIpvoltmax = disableMchmIpvoltmax;
	}
	public boolean isDisableMchmIpvoltmax() {
		return disableMchmIpvoltmax;
	}
	public void setDisableMchmManufacturerid(boolean disableMchmManufacturerid) {
		this.disableMchmManufacturerid = disableMchmManufacturerid;
	}
	public boolean isDisableMchmManufacturerid() {
		return disableMchmManufacturerid;
	}
	public void setDisableMchmIpfreq(boolean disableMchmIpfreq) {
		this.disableMchmIpfreq = disableMchmIpfreq;
	}
	public boolean isDisableMchmIpfreq() {
		return disableMchmIpfreq;
	}
	public void setDisableMchmIpvoltmin(boolean disableMchmIpvoltmin) {
		this.disableMchmIpvoltmin = disableMchmIpvoltmin;
	}
	public boolean isDisableMchmIpvoltmin() {
		return disableMchmIpvoltmin;
	}
	public void setDisableMchmIpfreqmax(boolean disableMchmIpfreqmax) {
		this.disableMchmIpfreqmax = disableMchmIpfreqmax;
	}
	public boolean isDisableMchmIpfreqmax() {
		return disableMchmIpfreqmax;
	}
	public void setDisableMchmMfrslno(boolean disableMchmMfrslno) {
		this.disableMchmMfrslno = disableMchmMfrslno;
	}
	public boolean isDisableMchmMfrslno() {
		return disableMchmMfrslno;
	}
	public void setDisableMchmAmcdate(boolean disableMchmAmcdate) {
		this.disableMchmAmcdate = disableMchmAmcdate;
	}
	public boolean isDisableMchmAmcdate() {
		return disableMchmAmcdate;
	}
	public void setDisableMchmSupplierid(boolean disableMchmSupplierid) {
		this.disableMchmSupplierid = disableMchmSupplierid;
	}
	public boolean isDisableMchmSupplierid() {
		return disableMchmSupplierid;
	}
	public void setDisableMchmManufactureddate(boolean disableMchmManufactureddate) {
		this.disableMchmManufactureddate = disableMchmManufactureddate;
	}
	public boolean isDisableMchmManufactureddate() {
		return disableMchmManufactureddate;
	}
	public void setDisableMchmMake(boolean disableMchmMake) {
		this.disableMchmMake = disableMchmMake;
	}
	public boolean isDisableMchmMake() {
		return disableMchmMake;
	}
	public void setDisableMchmModel(boolean disableMchmModel) {
		this.disableMchmModel = disableMchmModel;
	}
	public boolean isDisableMchmModel() {
		return disableMchmModel;
	}
	public void setDisableMchmMfrremarks(boolean disableMchmMfrremarks) {
		this.disableMchmMfrremarks = disableMchmMfrremarks;
	}
	public boolean isDisableMchmMfrremarks() {
		return disableMchmMfrremarks;
	}
	public void setDisableMchmPurchaseprice(boolean disableMchmPurchaseprice) {
		this.disableMchmPurchaseprice = disableMchmPurchaseprice;
	}
	public boolean isDisableMchmPurchaseprice() {
		return disableMchmPurchaseprice;
	}
	public void setDisableMchmAmcrenewaldate(boolean disableMchmAmcrenewaldate) {
		this.disableMchmAmcrenewaldate = disableMchmAmcrenewaldate;
	}
	public boolean isDisableMchmAmcrenewaldate() {
		return disableMchmAmcrenewaldate;
	}
	public void setDisableMchmAmcremarks(boolean disableMchmAmcremarks) {
		this.disableMchmAmcremarks = disableMchmAmcremarks;
	}
	public boolean isDisableMchmAmcremarks() {
		return disableMchmAmcremarks;
	}
	public void setDisableMchmAmcvendor(boolean disableMchmAmcvendor) {
		this.disableMchmAmcvendor = disableMchmAmcvendor;
	}
	public boolean isDisableMchmAmcvendor() {
		return disableMchmAmcvendor;
	}
	public void setDisableMchmWarrantydate(boolean disableMchmWarrantydate) {
		this.disableMchmWarrantydate = disableMchmWarrantydate;
	}
	public boolean isDisableMchmWarrantydate() {
		return disableMchmWarrantydate;
	}
	public void setDisableMchmCurrencyid(boolean disableMchmCurrencyid) {
		this.disableMchmCurrencyid = disableMchmCurrencyid;
	}
	public boolean isDisableMchmCurrencyid() {
		return disableMchmCurrencyid;
	}
	public void setDisableMchmSupplierremarks(boolean disableMchmSupplierremarks) {
		this.disableMchmSupplierremarks = disableMchmSupplierremarks;
	}
	public boolean isDisableMchmSupplierremarks() {
		return disableMchmSupplierremarks;
	}
	public void setDisableMchmPono(boolean disableMchmPono) {
		this.disableMchmPono = disableMchmPono;
	}
	public boolean isDisableMchmPono() {
		return disableMchmPono;
	}
	public void setDisableMchmPurchasedate(boolean disableMchmPurchasedate) {
		this.disableMchmPurchasedate = disableMchmPurchasedate;
	}
	public boolean isDisableMchmPurchasedate() {
		return disableMchmPurchasedate;
	}
	public void setDisableMchmPodate(boolean disableMchmPodate) {
		this.disableMchmPodate = disableMchmPodate;
	}
	public boolean isDisableMchmPodate() {
		return disableMchmPodate;
	}

	public void setDisableGrdoperator(boolean disableGrdoperator) {
		this.disableGrdoperator = disableGrdoperator;
	}

	public boolean isDisableGrdoperator() {
		return disableGrdoperator;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setDisableCompany(boolean disableCompany) {
		this.disableCompany = disableCompany;
	}

	public boolean isDisableCompany() {
		return disableCompany;
	}

	public void setDisableMchmIscavityormandrel(boolean disableMchmIscavityormandrel) {
		this.disableMchmIscavityormandrel = disableMchmIscavityormandrel;
	}

	public boolean isDisableMchmIscavityormandrel() {
		return disableMchmIscavityormandrel;
	}

	public void setDisableMchmGivesfinaloutput(boolean disableMchmGivesfinaloutput) {
		this.disableMchmGivesfinaloutput = disableMchmGivesfinaloutput;
	}

	public boolean isDisableMchmGivesfinaloutput() {
		return disableMchmGivesfinaloutput;
	}

	public void setDisableMchmIncludeforproduction(
			boolean disableMchmIncludeforproduction) {
		this.disableMchmIncludeforproduction = disableMchmIncludeforproduction;
	}

	public boolean isDisableMchmIncludeforproduction() {
		return disableMchmIncludeforproduction;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchId() {
		return mchId;
	}
	
	
}
