package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class AbnormalityBean  {

    private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String factory;
	private String costcenter;
	private String Location;
	private String status;
	private String tagStatus;
	private String abnmDetectedbytime;
	private String abnmendtime;
	private String abnmEnddate;
	private String abnmenddate;
	private String abnmstarttime;
	private boolean disableabnmKeyid;
	private boolean disableForm;
	private boolean disableComptdDtls;
	private boolean disableTargetDate;
	private String pillarName;
	private boolean disableTagclass;
	private boolean disableAbnType;
	private String abnwostartdatetime;
	private String woallotteddatetime;
	private String ShowCompDate;
	public AbnormalityBean(){
		
	}
	public AbnormalityBean(FormModes mode)
	{
		this.formMode = mode;
		this.setDisableForm(false);
		if(mode == FormModes.create )
		{	
			this.setStatus("P");
			this.setDisableForm(false);
			this.setFormActionMode("Create");
		}
		else if( mode == FormModes.modify)
		{
			this.setStatus("P");
			this.setDisableForm(false);
			this.setFormActionMode("Modify");
			
		}
		else if(mode == FormModes.completion)
		{
			this.setStatus("C");
			this.setDisableForm(true);
			this.setDisableComptdDtls(true);
			this.setFormActionMode("Completed");
		}
		else if(mode == FormModes.view)
		{
			this.setStatus("C");
			this.setDisableForm(true);
			this.setDisableComptdDtls(true);
			this.setFormActionMode("View");
		}
		else if(mode==FormModes.removal)
		{
			this.setStatus("C");
			this.setDisableForm(true);
			this.setDisableComptdDtls(false);
			this.setFormActionMode("Removal");
		}
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode( FormModes formMode) {
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
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactory() {
		return factory;
	}
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}
	public String getCostcenter() {
		return costcenter;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getLocation() {
		return Location;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param disableabnmKeyid the disableabnmKeyid to set
	 */
	public void setDisableabnmKeyid(boolean disableabnmKeyid) {
		this.disableabnmKeyid = disableabnmKeyid;
	}
	/**
	 * @return the disableabnmKeyid
	 */
	public boolean isDisableabnmKeyid() {
		return disableabnmKeyid;
	}
	/**
	 * @param disableForm the disableForm to set
	 */
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	/**
	 * @return the disableForm
	 */
	public boolean isDisableForm() {
		return disableForm;
	}
	/**
	 * @param disableComptdDtls the disableComptdDtls to set
	 */
	public void setDisableComptdDtls(boolean disableComptdDtls) {
		this.disableComptdDtls = disableComptdDtls;
	}
	/**
	 * @return the disableComptdDtls
	 */
	public boolean isDisableComptdDtls() {
		return disableComptdDtls;
	}
	/**
	 * @param tagStatus the tagStatus to set
	 */
	public void setTagStatus(String tagStatus) {
		this.tagStatus = tagStatus;
	}
	/**
	 * @return the tagStatus
	 */
	public String getTagStatus() {
		return tagStatus;
	}
	/**
	 * @param disableTargetDate the disableTargetDate to set
	 */
	public void setDisableTargetDate(boolean disableTargetDate) {
		this.disableTargetDate = disableTargetDate;
	}
	/**
	 * @return the disableTargetDate
	 */
	public boolean isDisableTargetDate() {
		return disableTargetDate;
	}
	public void setAbnmDetectedbytime(String abnmDetectedbytime) {
		this.abnmDetectedbytime = abnmDetectedbytime;
	}
	public String getAbnmDetectedbytime() {
		return abnmDetectedbytime;
	}
	public void setPillarName(String pillarName) {
		this.pillarName = pillarName;
	}
	public String getPillarName() {
		return pillarName;
	}
	public void setDisableTagclass(boolean disableTagclass) {
		this.disableTagclass = disableTagclass;
	}
	public boolean isDisableTagclass() {
		return disableTagclass;
	}
	public void setDisableAbnType(boolean disableAbnType) {
		this.disableAbnType = disableAbnType;
	}
	public boolean isDisableAbnType() {
		return disableAbnType;
	}
	public String getAbnmendtime() {
		return abnmendtime;
	}
	public void setAbnmendtime(String abnmendtime) {
		this.abnmendtime = abnmendtime;
	}
	public String getAbnmstarttime() {
		return abnmstarttime;
	}
	public void setAbnmstarttime(String abnmstarttime) {
		this.abnmstarttime = abnmstarttime;
	}
	public String getAbnmEnddate() {
		return abnmEnddate;
	}
	public void setAbnmEnddate(String abnmEnddate) {
		this.abnmEnddate = abnmEnddate;
	}
	public String getAbnwostartdatetime() {
		return abnwostartdatetime;
	}
	public void setAbnwostartdatetime(String abnwostartdatetime) {
		this.abnwostartdatetime = abnwostartdatetime;
	}
	public String getWoallotteddatetime() {
		return woallotteddatetime;
	}
	public void setWoallotteddatetime(String woallotteddatetime) {
		this.woallotteddatetime = woallotteddatetime;
	}
	public String getAbnmenddate() {
		return abnmenddate;
	}
	public void setAbnmenddate(String abnmenddate) {
		this.abnmenddate = abnmenddate;
	}
	public String getShowCompDate() {
		return ShowCompDate;
	}
	public void setShowCompDate(String showCompDate) {
		ShowCompDate = showCompDate;
	}
	

}
