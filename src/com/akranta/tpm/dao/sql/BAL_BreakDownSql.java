package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;

public class BAL_BreakDownSql {

	public static String getBreakdownModificationSql()
	{
		return " select * from " + TableNames.TBL_VW_BDM_VW_BREAKDOWNVIEW;  
	}
	
/*	public static String getBDRelatedConditionStr(CommonFilter commonFilter)
	{
		String relCondtionStr = "1=1";
		
		System.out.println(commonFilter.getFailType().getId());		
		System.out.println("rcccc+"+commonFilter.getRccchkbox());
		System.out.println("rccccSS+"+commonFilter.getDesignchkbox());
		
		//String FailType = (commonFilter.getFailType() != null ? (commonFilter.getFailType().getId()!=null? commonFilter.getFailType().getId():"{}"):"{}");
		

		relCondtionStr = relCondtionStr + ";FAILURETYPEID="+ getComboSelectionId(commonFilter.getFailType());
		
		if (commonFilter.getDefectPhenomena() != null && commonFilter.getDefectPhenomena().getId()!=null  && commonFilter.getDefectPhenomena().getId() != "{}")
			relCondtionStr = relCondtionStr + ";DEFECTPHENOMENAID="+ commonFilter.getDefectPhenomena().getId();
		if (commonFilter.getCircle() != null && commonFilter.getCircle().getId()!=null  && commonFilter.getCircle().getId() != "{}")
			relCondtionStr = relCondtionStr + ";CIRCLEID="+ commonFilter.getCircle().getId();
		if (commonFilter.getMachine() != null && commonFilter.getMachine().getId()!=null  && commonFilter.getMachine().getId() != "{}")
			relCondtionStr = relCondtionStr + ";MACHINEID="+ commonFilter.getMachine().getId();
		
		if (commonFilter.getCause() != null && commonFilter.getCause().getId()!=null  && commonFilter.getCause().getId() != "{}")
			relCondtionStr = relCondtionStr + ";CAUSEID="+ commonFilter.getCause().getId();
		if (commonFilter.getBdRootCause() != null && commonFilter.getBdRootCause().getId()!=null  && commonFilter.getBdRootCause().getId() != "{}")
			relCondtionStr = relCondtionStr + ";BDROOTVAUSEID="+ commonFilter.getBdRootCause().getId();
		if (commonFilter.getProdcngroup() != null && commonFilter.getProdcngroup().getId()!=null  && commonFilter.getProdcngroup().getId() != "{}")
			relCondtionStr = relCondtionStr + ";PRODUCTIONGROUPID="+ commonFilter.getProdcngroup().getId();
		if (commonFilter.getShiftIncharge() != null && commonFilter.getShiftIncharge().getId()!=null  && commonFilter.getShiftIncharge().getId() != "{}")
			relCondtionStr = relCondtionStr + ";SHIFTINCHARGEID="+ commonFilter.getShiftIncharge().getId();
		if (commonFilter.getEqpGroup() != null && commonFilter.getEqpGroup().getId()!=null  && commonFilter.getEqpGroup().getId() != "{}")
			relCondtionStr = relCondtionStr + ";EQUIPMENTGROUPID="+ commonFilter.getEqpGroup().getId();
		if (commonFilter.getYyy()!= null && commonFilter.getYyy().getId()!=null  && commonFilter.getYyy().getId() != "{}")
			relCondtionStr = relCondtionStr + ";WHYWHYID="+ commonFilter.getYyy().getId();
		if (commonFilter.getAssembly() != null && commonFilter.getAssembly().getId()!=null  && commonFilter.getAssembly().getId() != "{}")
			relCondtionStr = relCondtionStr + ";ASSEMBLYID="+ commonFilter.getAssembly().getId();
		if (commonFilter.getMachineRank() != null && commonFilter.getMachineRank().getId()!=null  && commonFilter.getMachineRank().getId() != "{}")
			relCondtionStr = relCondtionStr + ";EQUIPMENTRANKID="+ commonFilter.getMachineRank().getId();
		
		if (commonFilter.getOccurchbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_OCCURENCE="+ commonFilter.getOccurchbox(); 
		if (commonFilter.getTimeChBox() != null)
			relCondtionStr = relCondtionStr + ";CHK_TIME="+ commonFilter.getTimeChBox(); 
		if (commonFilter.getZeroBdChBox() != null)
			relCondtionStr = relCondtionStr + ";CHK_ZEROBREAKDOWN="+ commonFilter.getZeroBdChBox();
		if (commonFilter.getRemallchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_REMOVEBLANK="+ commonFilter.getRemallchkbox();
	
		if (commonFilter.getFachkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_FINALACTION="+ commonFilter.getFachkbox(); 
		if (commonFilter.getPillarchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_PILLAR="+ commonFilter.getPillarchkbox(); 
		if (commonFilter.getRcchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_ROOTCAUSE="+ commonFilter.getRcchkbox(); 
		if (commonFilter.getRccchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_ROOTCAUSECLASSIFICATION="+ commonFilter.getRccchkbox();
		if (commonFilter.getCmchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_COUNTERMEASURE="+ commonFilter.getCmchkbox(); 
		
		if (commonFilter.getBdType() != null)
			relCondtionStr = relCondtionStr + ";BDTYPE="+ commonFilter.getBdType();
		
		if (commonFilter.getTop() != null)
			relCondtionStr = relCondtionStr + ";TOP="+ commonFilter.getTop(); 
		if (commonFilter.getOptions() != null && commonFilter.getOptions().getId()!=null  && commonFilter.getOptions().getId() != "{}")
			relCondtionStr = relCondtionStr + ";OPTIONS="+ commonFilter.getOptions().getId(); 
		if (commonFilter.getEngineer() != null && commonFilter.getEngineer().getId()!=null  && commonFilter.getEngineer().getId() != "{}")
			relCondtionStr = relCondtionStr + ";ENGINEERID="+ commonFilter.getEngineer().getId(); 
		if (commonFilter.getSpares() != null)
			relCondtionStr = relCondtionStr + ";SPARESSELECTED="+ commonFilter.getSparesSelectBox(); 
		if (commonFilter.getJhchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_JH="+ commonFilter.getJhchkbox(); 
	
		if (commonFilter.getPmchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_PM="+ commonFilter.getPmchkbox();
		if (commonFilter.getDesignchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_DESIGN="+ commonFilter.getDesignchkbox();		
		if (commonFilter.getEtchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_ET="+ commonFilter.getEtchkbox(); 
		if (commonFilter.getIrcchkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_INCLUDERC="+ commonFilter.getIrcchkbox(); 
		if (commonFilter.getIyychkbox() != null)
			relCondtionStr = relCondtionStr + ";CHK_INCLUDEYY="+ commonFilter.getIyychkbox(); 
		if (commonFilter.getYyy() != null && commonFilter.getYyy().getId()!=null  && commonFilter.getYyy().getId() != "{}")
			relCondtionStr = relCondtionStr + ";WHYWHY="+ commonFilter.getYyy().getId();

		System.out.println("relCondtionStr="+relCondtionStr);

		
// missing  (Root Cause Classification, Included Root Cause, Included Why-Why,)		
		
		return relCondtionStr;
		
	}
*/	
	private static String getComboSelectionId(ComboFilter comboFilter){ 
		if( comboFilter != null && isValidKeyId( comboFilter.getId() ) )
			return comboFilter.getId();
		return "";
	}
	
	
	 public static boolean isValidKeyId(String keyId)
  	 {
  		
  		if( keyId != null && ! keyId.isEmpty() && ! keyId.equals("{}") && ! keyId.equals("-") && ! keyId.toLowerCase().equals("null"))
  			return true;
  		return false;
  	 }
	
}
