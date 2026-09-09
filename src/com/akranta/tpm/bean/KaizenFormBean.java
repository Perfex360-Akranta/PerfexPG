package com.akranta.tpm.bean;

import java.util.List;

//import com.akranta.tpm.model.EntTlBatchEmpAbsent;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.utils.FormModes;

public class KaizenFormBean {

	
	private boolean disableImprvNo;
	private boolean disablewwmsKeyid;
	private boolean disableChkApprovedBy;
	private boolean disableCmbApprovedBy;
	private boolean disableDteApprovedDate;
	private boolean disableCause;
	private String responsibility;
	private String kaizenId;
	private String costCenter;
	private String resultAreaP;
	private String resultAreaQ;
	private String resultAreaC;
	private String resultAreaD;
	private String resultAreaS;
	private String resultAreaM;
	private String resultAreaE;
	private String resultAreaSecP;
	private String resultAreaSecQ;
	private String resultAreaSecC;
	private String resultAreaSecD;
	private String resultAreaSecS;
	private String resultAreaSecM;
	private String resultAreaSecE;
	private String providing; 
	private String changing;
	private String reversible;
	private String irreversible;
	private String hdRequiredY;
	private String hdRequiredN;
	private String ideagroupindividualG;
	private String ideagroupindividualI;
	private String woRequiredY;
	private String woRequiredN;
	private boolean disableForm;
	private boolean disableComptdDtls;
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String isWhywhy;
	private String sectionId;
	private String txtKzplTpmpillarid;
	private String pillar_checkbox;
	private String txtDbMode;
	private String txtKzplKzncategoryid;
	private String kznPillarName;
	private String kznCategory;
	private String txtselectionFlag;
	private String yyId;
	private String docId;
	private String presentImage;
	private String afterImage;
	private String benefitImage;
	private String resultImage;
	private String cucdkeyId;
	private String csmValue;
	private List<KznTlKaizenbankmst> kznTlKaizenbankmst;
	
	public KaizenFormBean(){
		
	}
	
	public KaizenFormBean(FormModes mode)
	{
		this.formMode = mode;
		this.setDisableForm(false);
		if(mode == FormModes.create )
		{	
			this.formMode = mode;
			this.disableChkApprovedBy= true;
			this.disableCmbApprovedBy= true;
			this.disableDteApprovedDate = true; 
			this.setDisableForm(false);
			this.setFormActionMode("Create");
		}
		else if( mode == FormModes.modify)
		{
			this.setDisableForm(false);
			this.setFormActionMode("Modify");
		}
		else if(mode == FormModes.completion)
		{
			this.setDisableForm(true);
			this.setDisableComptdDtls(false);
			this.setFormActionMode("Completed");
		}
		else if(mode == FormModes.view)
		{
			this.setDisableForm(true);
			this.setDisableComptdDtls(true);
			this.setFormActionMode("View");
		}
		else if(mode == FormModes.approval)
		{
			this.setDisableForm(true);
			this.setDisableComptdDtls(true);
			this.setFormActionMode("Approval");
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
	 * @param costCenter the costCenter to set
	 */
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	/**
	 * @return the costCenter
	 */
	public String getCostCenter() {
		return costCenter;
	}
	/**
	 * @param circle the circle to set
	 */
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
	public String getResultAreaQ() {
		return resultAreaQ;
	}
	public void setResultAreaQ(String resultAreaQ) {
		this.resultAreaQ = resultAreaQ;
	}
	public String getResultAreaC() {
		return resultAreaC;
	}
	public void setResultAreaC(String resultAreaC) {
		this.resultAreaC = resultAreaC;
	}
	public String getResultAreaD() {
		return resultAreaD;
	}
	public void setResultAreaD(String resultAreaD) {
		this.resultAreaD = resultAreaD;
	}
	public String getResultAreaS() {
		return resultAreaS;
	}
	public void setResultAreaS(String resultAreaS) {
		this.resultAreaS = resultAreaS;
	}
	public String getResultAreaM() {
		return resultAreaM;
	}
	public void setResultAreaM(String resultAreaM) {
		this.resultAreaM = resultAreaM;
	}
	public String getResultAreaE() {
		return resultAreaE;
	}
	public void setResultAreaE(String resultAreaE) {
		this.resultAreaE = resultAreaE;
	}
	public String getProviding() {
		return providing;
	}
	public void setProviding(String providing) {
		this.providing = providing;
	}
	public String getChanging() {
		return changing;
	}
	public void setChanging(String changing) {
		this.changing = changing;
	}
	
	public String getReversible() {
		return reversible;
	}

	public void setReversible(String reversible) {
		this.reversible = reversible;
	}
	public String getIrreversible() {
		return irreversible;
	}
	public void setIrreversible(String irreversible) {
		this.irreversible = irreversible;
	}


	public String getIdeagroupindividualG() {
		return ideagroupindividualG;
	}
	public void setIdeagroupindividualG(String ideagroupindividualG) {
		this.ideagroupindividualG = ideagroupindividualG;
	}
	public String getIdeagroupindividualI() {
		return ideagroupindividualI;
	}
	public void setIdeagroupindividualI(String ideagroupindividualI) {
		this.ideagroupindividualI = ideagroupindividualI;
	}
	public String getHdRequiredY() {
		return hdRequiredY;
	}
	public void setHdRequiredY(String hdRequiredY) {
		this.hdRequiredY = hdRequiredY;
	}
	public String getHdRequiredN() {
		return hdRequiredN;
	}
	public void setHdRequiredN(String hdRequiredN) {
		this.hdRequiredN = hdRequiredN;
	}
	
	public String getWoRequiredY() {
		return woRequiredY;
	}
	public void setWoRequiredY(String woRequiredY) {
		this.woRequiredY = woRequiredY;
	}
	public String getWoRequiredN() {
		return woRequiredN;
	}
	public void setWoRequiredN(String woRequiredN) {
		this.woRequiredN = woRequiredN;
	}
	/**
	 * @param formMode the formMode to set
	 */
	public void setFormMode( FormModes formMode) {
		this.formMode = formMode;
	}
	public FormModes getFormMode() {
		return formMode;
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
	 * @param txtKzplKzncategoryid the txtKzplKzncategoryid to set
	 */
	public void setTxtKzplKzncategoryid(String txtKzplKzncategoryid) {
		this.txtKzplKzncategoryid = txtKzplKzncategoryid;
	}

	/**
	 * @return the txtKzplKzncategoryid
	 */
	public String getTxtKzplKzncategoryid() {
		return txtKzplKzncategoryid;
	}

	/**
	 * @param txtKzplTpmpillarid the txtKzplTpmpillarid to set
	 */
	public void setTxtKzplTpmpillarid(String txtKzplTpmpillarid) {
		this.txtKzplTpmpillarid = txtKzplTpmpillarid;
	}

	/**
	 * @return the txtKzplTpmpillarid
	 */
	public String getTxtKzplTpmpillarid() {
		return txtKzplTpmpillarid;
	}

	/**
	 * @param kznPillarName the kznPillarName to set
	 */
	public void setKznPillarName(String kznPillarName) {
		this.kznPillarName = kznPillarName;
	}

	/**
	 * @return the kznPillarName
	 */
	public String getKznPillarName() {
		return kznPillarName;
	}

	/**
	 * @param kznCategory the kznCategory to set
	 */
	public void setKznCategory(String kznCategory) {
		this.kznCategory = kznCategory;
	}

	/**
	 * @return the kznCategory
	 */
	public String getKznCategory() {
		return kznCategory;
	}

	/**
	 * @param pillar_checkbox the pillar_checkbox to set
	 */
	public void setPillar_checkbox(String pillar_checkbox) {
		this.pillar_checkbox = pillar_checkbox;
	}

	/**
	 * @return the pillar_checkbox
	 */
	public String getPillar_checkbox() {
		return pillar_checkbox;
	}

	/**
	 * @param txtDbMode the txtDbMode to set
	 */
	public void setTxtDbMode(String txtDbMode) {
		this.txtDbMode = txtDbMode;
	}

	/**
	 * @return the txtDbMode
	 */
	public String getTxtDbMode() {
		return txtDbMode;
	}

	/**
	 * @param txtselectionFlag the txtselectionFlag to set
	 */
	public void setTxtselectionFlag(String txtselectionFlag) {
		this.txtselectionFlag = txtselectionFlag;
	}

	/**
	 * @return the txtselectionFlag
	 */
	public String getTxtselectionFlag() {
		return txtselectionFlag;
	}

	public void setKaizenId(String kaizenId) {
		this.kaizenId = kaizenId;
	}

	public String getKaizenId() {
		return kaizenId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setDisableCause(boolean disableCause) {
		this.disableCause = disableCause;
	}

	public boolean isDisableCause() {
		return disableCause;
	}

	public void setDisableImprvNo(boolean disableImprvNo) {
		this.disableImprvNo = disableImprvNo;
	}

	public boolean isDisableImprvNo() {
		return disableImprvNo;
	}

	public void setDisablewwmsKeyid(boolean disablewwmsKeyid) {
		this.disablewwmsKeyid = disablewwmsKeyid;
	}

	public boolean isDisablewwmsKeyid() {
		return disablewwmsKeyid;
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

	public void setPresentImage(String presentImage) {
		this.presentImage = presentImage;
	}

	public String getPresentImage() {
		return presentImage;
	}

	public void setAfterImage(String afterImage) {
		this.afterImage = afterImage;
	}

	public String getAfterImage() {
		return afterImage;
	}
	public void setBenefitImage(String benefitImage) {
		this.benefitImage = benefitImage;
	}

	public String getBenefitImage() {
		return benefitImage;
	}
	public void setResultImage(String resultImage) {
		this.resultImage = resultImage;
	}

	public String getResultImage() {
		return resultImage;
	}

	public void setCucdkeyId(String cucdkeyId) {
		this.cucdkeyId = cucdkeyId;
	}

	public String getCucdkeyId() {
		return cucdkeyId;
	}
	public String getResultAreaSecP() {
		return resultAreaSecP;
	}
	public void setResultAreaSecP(String resultAreaSecP) {
		this.resultAreaSecP = resultAreaSecP;
	}
	public String getResultAreaSecQ() {
		return resultAreaSecQ;
	}
	public void setResultAreaSecQ(String resultAreaSecQ) {
		this.resultAreaSecQ = resultAreaSecQ;
	}
	public String getResultAreaSecC() {
		return resultAreaSecC;
	}
	public void setResultAreaSecC(String resultAreaSecC) {
		this.resultAreaSecC = resultAreaSecC;
	}
	public String getResultAreaSecD() {
		return resultAreaSecD;
	}
	public void setResultAreaSecD(String resultAreaSecD) {
		this.resultAreaSecD = resultAreaSecD;
	}
	public String getResultAreaSecS() {
		return resultAreaSecS;
	}
	public void setResultAreaSecS(String resultAreaSecS) {
		this.resultAreaSecS = resultAreaSecS;
	}
	public String getResultAreaSecM() {
		return resultAreaSecM;
	}
	public void setResultAreaSecM(String resultAreaSecM) {
		this.resultAreaSecM = resultAreaSecM;
	}
	public String getResultAreaSecE() {
		return resultAreaSecE;
	}
	public void setResultAreaSecE(String resultAreaSecE) {
		this.resultAreaSecE = resultAreaSecE;
		
	}
	public String getKznmIswhywhy(){
		return isWhywhy;
	}
	public void setKznmIswhywhy(String isWhywhy){
		this.isWhywhy = isWhywhy;
	}
	
	public List<KznTlKaizenbankmst> getKaizenBankList() {
		return kznTlKaizenbankmst;
	}

	public void setKaizenBankList(List<KznTlKaizenbankmst> kznTlKaizenbankmst) {
		this.kznTlKaizenbankmst = kznTlKaizenbankmst;
	}
	public String getKznmCsmValue() {
		return csmValue;
	}
	public void setKznmCsmValue(String csmValue) {
		this.csmValue = csmValue;
	}
	
	
}
