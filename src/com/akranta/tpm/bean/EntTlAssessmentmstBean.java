package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class EntTlAssessmentmstBean 
{
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String postEvaluationdate;
	private String preEvaluationdate;
	private boolean disableForm;
		
	public EntTlAssessmentmstBean()
	{
			
	}
	public EntTlAssessmentmstBean(FormModes mode)
	{
		this.setFormMode(mode);
		this.setDisableForm(false);
		if(mode == FormModes.create )
		{	
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
			this.setFormActionMode("Completed");
		}
		else if(mode == FormModes.view)
		{
			this.setDisableForm(true);
			this.setFormActionMode("View");
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
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setPostEvaluationdate(String postEvaluationdate) {
		this.postEvaluationdate = postEvaluationdate;
	}
	public String getPostEvaluationdate() {
		return postEvaluationdate;
	}
	public void setPreEvaluationdate(String preEvaluationdate) {
		this.preEvaluationdate = preEvaluationdate;
	}
	public String getPreEvaluationdate() {
		return preEvaluationdate;
	}
	
}
