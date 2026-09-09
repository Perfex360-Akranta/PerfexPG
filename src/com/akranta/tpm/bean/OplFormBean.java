/*Created By : Siddharth.A*/
package com.akranta.tpm.bean;
import com.akranta.tpm.utils.CommonMessage;
public class OplFormBean {

	private boolean disableChkApprovedBy;
	private boolean disableCmbApprovedBy;
	private boolean disableDteApprovedDate;
	private boolean disableChkpreparedBy;
	private boolean disableCmbDocumentNo;
	
	private String responsibility;
	private String resultAreaP;
	private String Lesson;

	private String classificationB;
	private String classificationI;
	private String classificationT;
	private String classificationS;
	private String classificationC;
	private String classificationP;

	private String formActionMode;
	private String formMode;
	private String formHeader;
	private boolean oplExcelView;
	private boolean viewMode;
	private String chkUnderstoodOpl;
	private String disableForm;
	private String yyId;
	private String docId;
	private String oplmApprovedid;
	private String oplmwhyhow;
	
	public OplFormBean()
	{
		
	}
	public OplFormBean(String mode)
	{
		if(mode.equals("Create") )
		{
			CommonMessage.debugMsg("Inside Create Mode");
			this.setFormMode("CREATE");
			this.setFormActionMode("create");
			this.disableChkApprovedBy= true;
			this.disableCmbApprovedBy= true;
			this.disableChkpreparedBy=true;
			this.disableDteApprovedDate = true; 
			this.setOplExcelView(false);
			this.setDisableForm("");
		}
		 if(mode.equals("Update"))
		{
			CommonMessage.debugMsg("Inside Update Mode");	
			this.setFormMode("UPDATE");
			this.setFormActionMode("modify");
			this.disableChkpreparedBy=false;
			this.setOplExcelView(true);
			this.setViewMode(false);
			this.setDisableForm("");
		}
		
		else if(mode.equals("Category"))
		{
			CommonMessage.debugMsg("Inside Category Mode");
			this.setFormMode("CATEGORY");
			this.setFormActionMode("category");
			this.disableChkpreparedBy=true;
			this.setOplExcelView(true);
			this.setViewMode(false);
			this.setDisableForm("");
			
		}
		else if(mode.equals("View"))
		{
			CommonMessage.debugMsg("Inside View Mode");
			this.setFormMode("VIEW");
			this.setFormActionMode("View");
			this.setViewMode(true);
			this.setOplExcelView(true);
			this.setDisableForm("true");
		}
		 
		else if(mode.equals("BD"))
		{
			CommonMessage.debugMsg("Inside View Mode");
			this.setFormActionMode("BD");
		}
			
	}

	public boolean isDisableChkApprovedBy() {
		return disableChkApprovedBy;
	}
	public void setDisableChkApprovedBy(boolean disableChkApprovedBy) {
		this.disableChkApprovedBy = disableChkApprovedBy;
	}
	public boolean isDisableCmbApprovedBy() {
		return disableCmbApprovedBy;
	}
	public void setDisableCmbApprovedBy(boolean disableCmbApprovedBy) {
		this.disableCmbApprovedBy = disableCmbApprovedBy;
	}
	public boolean isDisableDteApprovedDate() {
		return disableDteApprovedDate;
	}
	public void setDisableDteApprovedDate(boolean disableDteApprovedDate) {
		this.disableDteApprovedDate = disableDteApprovedDate;
	}
	
	/**
	 * @param disableChkpreparedBy the disableChkpreparedBy to set
	 */
	public void setDisableChkpreparedBy(boolean disableChkpreparedBy) {
		this.disableChkpreparedBy = disableChkpreparedBy;
	}

	/**
	 * @return the disableChkpreparedBy
	 */
	public boolean isDisableChkpreparedBy() {
		return disableChkpreparedBy;
	}
	
	
	/**
	 * @param responsibility the responsibility to set
	 */
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	/**
	 * @return the responsibility
	 */
	public String getResponsibility() {
		return responsibility;
	}

	/**
	 * @param formMode the formMode to set
	 */
	public void setFormActionMode(String formActionMode) {
		this.formActionMode= formActionMode;
	}
	/**
	 * @return the formMode
	 */
	public String getFormActionMode() {
		return formActionMode;
	}
	/**
	 * @param formHeader the formHeader to set
	 */
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	/**
	 * @return the formHeader
	 */
	public String getFormHeader() {
		return formHeader;
	}

	public String getResultAreaP() {
		return resultAreaP;
	}
	public void setResultAreaP(String resultAreaP) {
		this.resultAreaP = resultAreaP;
	}


	/**
	 * @param formMode the formMode to set
	 */
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	/**
	 * @return the formMode
	 */
	public String getFormMode() {
		return formMode;
	}
	/**
	 * @param trbleCases the trbleCases to set
	 */


	/**
	 * @param lesson the lesson to set
	 */
	public void setLesson(String lesson) {
		Lesson = lesson;
	}

	/**
	 * @return the lesson
	 */
	public String getLesson() {
		return Lesson;
	}

	/**
	 * @param classificationB the classificationB to set
	 */
	public void setClassificationB(String classificationB) {
		this.classificationB = classificationB;
	}

	/**
	 * @return the classificationB
	 */
	public String getClassificationB() {
		return classificationB;
	}

	/**
	 * @param classificationI the classificationI to set
	 */
	public void setClassificationI(String classificationI) {
		this.classificationI = classificationI;
	}

	/**
	 * @return the classificationI
	 */
	public String getClassificationI() {
		return classificationI;
	}

	/**
	 * @param classificationT the classificationT to set
	 */
	public void setClassificationT(String classificationT) {
		this.classificationT = classificationT;
	}

	/**
	 * @return the classificationT
	 */
	public String getClassificationT() {
		return classificationT;
	}
	
	public void setClassificationS(String classificationS) {
		this.classificationS = classificationS;
	}

	/**
	 * @return the classificationI
	 */
	public String getClassificationS() {
		return classificationS;
	}
	
	
	
	
	public void setClassificationC(String classificationC) {
		this.classificationC = classificationC;
	}

	/**
	 * @return the classificationI
	 */
	public String getClassificationC() {
		return classificationC;
	}
	
	
	public void setClassificationP(String classificationP) {
		this.classificationP = classificationP;
	}

	/**
	 * @return the classificationI
	 */
	public String getClassificationP() {
		return classificationP;
	}

	/**
	 * @param disableCmbDocumentNo the disableCmbDocumentNo to set
	 */
	public void setDisableCmbDocumentNo(boolean disableCmbDocumentNo) {
		this.disableCmbDocumentNo = disableCmbDocumentNo;
	}

	/**
	 * @return the disableCmbDocumentNo
	 */
	public boolean isDisableCmbDocumentNo() {
		return disableCmbDocumentNo;
	}

	
	/**
	 * @param oplExcelView the oplExcelView to set
	 */
	public void setOplExcelView(boolean oplExcelView) {
		this.oplExcelView = oplExcelView;
	}
	/**
	 * @return the oplExcelView
	 */
	public boolean isOplExcelView() {
		return oplExcelView;
	}
	/**
	 * @param viewMode the viewMode to set
	 */
	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}
	/**
	 * @return the viewMode
	 */
	public boolean isViewMode() {
		return viewMode;
	}
	/**
	 * @param disableForm the disableForm to set
	 */
	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}
	/**
	 * @return the disableForm
	 */
	public String getDisableForm() {
		return disableForm;
	}
	/**
	 * @param chkUnderstoodOpl the chkUnderstoodOpl to set
	 */
	public void setChkUnderstoodOpl(String chkUnderstoodOpl) {
		this.chkUnderstoodOpl = chkUnderstoodOpl;
	}
	/**
	 * @return the chkUnderstoodOpl
	 */
	public String getChkUnderstoodOpl() {
		CommonMessage.debugMsg("getChkUnderstoodOpl Bean setted");
		return chkUnderstoodOpl;
	}
	public void setYyId(String yyId) {
		this.yyId = yyId;
	}
	public String getYyId() {
		return yyId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocId() {
		return docId;
	}
	public void setOplmApprovedid(String oplmApprovedid) {
		this.oplmApprovedid = oplmApprovedid;
	}
	public String getOplmApprovedid() {
		return oplmApprovedid;
	}
	public void setOplmwhyhow(String oplmwhyhow) {
		this.oplmwhyhow = oplmwhyhow;
	}
	public String getOplmwhyhow() {
		return oplmwhyhow;
	}
	
	
}
