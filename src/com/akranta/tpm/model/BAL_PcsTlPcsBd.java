package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

public class BAL_PcsTlPcsBd {
	
	private  Object [] saveArray = null;
	private List<BAL_PcsTlPcsBd> pcsbd ;
	public enum   tableFldConstants
	{
	 keyid,bdmKeyid,active,createdOn,createdBy;
	}
	public BAL_PcsTlPcsBd()
	{
		setPcsTlPcsBd(new ArrayList<BAL_PcsTlPcsBd> ());
		
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public List<BAL_PcsTlPcsBd> getPcsTlPcsBd(){
		return pcsbd;
	}
	private void setPcsTlPcsBd(List<BAL_PcsTlPcsBd> pcsbd) {
		
		this.pcsbd=pcsbd;
				
	}
	public String getPcsbdKeyid(){
		return (String) saveArray[tableFldConstants.keyid.ordinal()];
	}
	public void setPcsbdKeyid(String bdKeyid){
		
		saveArray[tableFldConstants.keyid.ordinal()]=bdKeyid;
	}
	public String getPcsBdmsKeyid(){
		return(String) saveArray[tableFldConstants.bdmKeyid.ordinal()];
	}
	public void setPcsBdmsKeyid(String bdmsKeyid){
		saveArray[tableFldConstants.bdmKeyid.ordinal()]=bdmsKeyid;
	}
	public String getActive(){
		return(String) saveArray[tableFldConstants.active.ordinal()];
	}
	public void setActive(String active){
		saveArray[tableFldConstants.active.ordinal()]=active;
	}
	public String getCreatedOn(){
		return (String) saveArray[tableFldConstants.createdOn.ordinal()];
	}
	public void setCreatedOn(String createdOn){
		 saveArray[tableFldConstants.createdOn.ordinal()]=createdOn;
	}
	public String getCreatedBy(){
		 return (String) saveArray[tableFldConstants.createdBy.ordinal()];
	}
	public void setCreatedBy(String createdBy){
		saveArray[tableFldConstants.createdBy.ordinal()]=createdBy;
	}
}
