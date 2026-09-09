
package com.akranta.tpm.bean;
import com.akranta.tpm.utils.CommonMessage;
public class VsopFormBean {

	
	private String disableForrpt;
	
	
	public VsopFormBean()
	{
		
	}
	public VsopFormBean(String mode)
	{
		
		 
		if(mode.equals("Report"))
		{
			CommonMessage.debugMsg("Inside Report Mode");			
			this.setDisableForRpt("true");
		
		}
		else
			this.setDisableForRpt("false");
		 
		
			
	}
	
	
	
	public void setDisableForRpt(String disableForrpt) {
		this.disableForrpt = disableForrpt;
	}
	public String getDisableForRpt() {
		return disableForrpt;
	}
	
	

	



	
}
