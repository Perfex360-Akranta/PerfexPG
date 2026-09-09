package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.CommonFiltersServlet;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.CommonFilterTraining;
//import com.akranta.tpm.upload.Validate;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class FilterCondSql {

	public static String getComboSelectionId(ComboFilter comboFilter){ 		
		if( comboFilter != null && isValidKeyId( comboFilter.getId() ) )
			return comboFilter.getId();
		return "";
	}
	
	public static String getComboSelectionId(ComboFilter comboFilter,String identifier){ 		
		if( comboFilter != null && isValidKeyId( comboFilter.getId() ) )
			return identifier + "="+comboFilter.getId()+";";
		return "";
	}


	 public static boolean isValidKeyId(String keyId)
	 {		
		if( keyId != null && ! keyId.isEmpty() && ! keyId.equals("{}") && ! keyId.equals("-") && ! keyId.toLowerCase().equals("null"))
			return true;
		return false;
	 }

	 public static String getFieldValue(String str)
	 {		
		if( isValidKeyId(str ) ){
			return str;
		}
		return "";
	 }
	 
	 public static String getFieldValue(String str,String identifier)
	 {		
		if( isValidKeyId(str ) ){
			return identifier + "=" + str +";";
		}
		return "";
	 }

	 
	 private static StringBuffer getCommonRelatedCondStr(CommonFilter commonFilter)
	 {
	 	StringBuffer str = new StringBuffer();
	 	/*str.append("COMPANYID="+getComboSelectionId(commonFilter.getCompany()));
	 	str.append(";LOCATIONID="+getComboSelectionId(commonFilter.getLocation()));
	 	str.append(";FACTORYID="+getComboSelectionId(commonFilter.getFactory()));
	 	str.append(";SECTIONID="+ getComboSelectionId(commonFilter.getSection()));
	 	str.append(";COSTCENTREID="+getComboSelectionId(commonFilter.getCostCenter()));
	 	str.append(";CELLID="+ getComboSelectionId(commonFilter.getCell()));
	 	str.append(";MACHINEID="+ getComboSelectionId(commonFilter.getMachine()));
		str.append(";FROMDATE="+ getFieldValue(commonFilter.getFromDate()));
		str.append(";TODATE="+ getFieldValue(commonFilter.getToDate()));		
		str.append(";FROMMONTH="+ getFieldValue(commonFilter.getFromMonth()));
		CommonMessage.debugMsg("commonFilter.getFromMonth()mmmm"+commonFilter.getFromMonth());
		str.append(";TOMONTH="+ getFieldValue(commonFilter.getToMonth()));	
		str.append(";ISMONTHWISE="+ getFieldValue(commonFilter.getMonwise()));	
		str.append(";DRILLLEVEL="+ getFieldValue(commonFilter.getDrillLevel()));
		str.append(";ASSEMBLYID="+getComboSelectionId(commonFilter.getAssembly()));
	 	str.append(";PHENOMENAID="+ getComboSelectionId(commonFilter.getPhenomena()));	 	
	 	str.append(";");*/
	 	
	 	str.append(getFieldValue(commonFilter.getFlid(),"FLID"));
	 	str.append(getComboSelectionId(commonFilter.getCompany(),"COMPANYID"));
	 	str.append(getComboSelectionId(commonFilter.getLocation(),"LOCATIONID"));
	 	//str.append(getComboSelectionId(commonFilter.getFactory(),"FACTORYID"));
		/////////////////////////////////////////////////////////////////////
	 	//SUGUMAR CHANGES
	 	str.append(getComboSelectionId(commonFilter.getSbu(),"SBUID"));
	 	str.append(getComboSelectionId(commonFilter.getPbu(),"PBUID"));
	 	//////////////////////////////////////////////////////////////////////////
	 	str.append(getComboSelectionId(commonFilter.getSection(),"SECTIONID"));
	 	str.append(getComboSelectionId(commonFilter.getCostCenter(),"COSTCENTREID"));
	 	str.append(getComboSelectionId(commonFilter.getCell(),"CELLID"));
	 	str.append(getComboSelectionId(commonFilter.getMachine(),"MACHINEID"));
	 	str.append(getComboSelectionId(commonFilter.getAssembly(),"ASSEMBLYID"));
		str.append( getComboSelectionId(commonFilter.getProcess(),"PROCESSID"));
	 	str.append(getComboSelectionId(commonFilter.getPhenomena(),"PHENOMENAID"));	 
	 	str.append(getComboSelectionId(commonFilter.getCmbcause(),"CAUSEID"));
		str.append(getFieldValue(commonFilter.getFromDate(),"FROMDATE"));
		str.append(getFieldValue(commonFilter.getToDate(),"TODATE"));		
		str.append(getFieldValue(commonFilter.getFromMonth(),"FROMMONTH"));
		str.append(getFieldValue(commonFilter.getToMonth(),"TOMONTH"));	
		str.append(getFieldValue(commonFilter.getMonwise(),"ISMONTHWISE"));	
		str.append(getFieldValue(commonFilter.getDrillLevel(),"DRILLLEVEL"));		
		str.append(getFieldValue(""+commonFilter.getDrillFlag(),"DRILLFLAG"));
		str.append(getComboSelectionId(commonFilter.getMould(),"MOULDID"));
	 	str.append(getFieldValue(commonFilter.getRelatedToMchMld(),"RELATEDTO"));
	 	str.append(getFieldValue(commonFilter.getYear(),"YEAR"));//addeed By KarthicK.T for Skill Level Inventory Rpt
		str.append(getFieldValue(commonFilter.getMultipleval(),"MULTIPLEVAL"));	
		str.append(getFieldValue(commonFilter.getMultipletype(),"MULTIPLETYPE"));	
		str.append(getFieldValue(commonFilter.getBreakup(),"BREAKUP"));
		
		str.append(getFieldValue(commonFilter.getElementId(),"ELEMENTID"));
		str.append(  getFieldValue(commonFilter.getRoleLevel(),"ROLELEVELNO"));
		str.append(  getFieldValue(commonFilter.getAbnDetectBy(),"CHK_DETECTBY"));
		
		str.append(  getFieldValue(commonFilter.getRefdocid(),"REFDOCID"));
		str.append(  getFieldValue(commonFilter.getDocType(),"REFDOCTYPE"));
		str.append(  getFieldValue(commonFilter.getFirstLevel(),"FIRSTLEVEL"));
		str.append(  getFieldValue(commonFilter.getKey(),"CONDFLID"));
		
	 	return str;
	 }
	public static String getEquipmentRelatedCondStr(CommonFilter commonFilter)
	{
	    StringBuffer str = new StringBuffer();		
		
		str.append( getCommonRelatedCondStr(commonFilter));
		/*str.append( "CIRCLEID="+ getComboSelectionId(commonFilter.getCircle()));
		str.append( ";EQPGRPID="+ getComboSelectionId(commonFilter.getEqpGroup()));
		str.append( ";TRADEID="+ getComboSelectionId(commonFilter.getTrade()));
		str.append( ";ASSEMBLY="+ getComboSelectionId(commonFilter.getAssembly()));
		str.append( ";JHSTEP="+ getComboSelectionId(commonFilter.getJhStep()));
		str.append( ";PURPOSEID="+ getComboSelectionId(commonFilter.getPurpose()));
		str.append( ";CATEGID="+ getComboSelectionId(commonFilter.getCategory()));
		str.append( ";SUBCATID="+ getComboSelectionId(commonFilter.getSubCategory()));
		str.append( ";MANUFACTURID="+ getComboSelectionId(commonFilter.getManufacture()));
		str.append( ";SUPPLIERID="+ getComboSelectionId(commonFilter.getSupplier()));
		str.append( ";AMCVENDORID="+ getComboSelectionId(commonFilter.getAmcvendorId()));
		str.append( ";MCHMRANK="+ getComboSelectionId(commonFilter.getMachineRank()));
		str.append( ";SHIFTID="+getComboSelectionId(commonFilter.getShift()));
		str.append( ";INSTALFROM="+ getFieldValue(commonFilter.getInstalfrom()));
		str.append( ";INSTALTO="+ getFieldValue(commonFilter.getInstalTo()));
		str.append( ";AMCFROM="+ getFieldValue(commonFilter.getAmcFrom()));
		str.append( ";AMCTO="+ getFieldValue(commonFilter.getAmcTo()));
		str.append( ";AMCRENEWFROM="+ getFieldValue(commonFilter.getAmcrnewFrom()));
		str.append( ";AMCRENEWTO="+ getFieldValue(commonFilter.getAmcrenewTo()));
		str.append( ";WARRANTYFROM="+ getFieldValue(commonFilter.getWarrantyFrom()));
		str.append( ";WARRANTYTO="+ getFieldValue(commonFilter.getWarrantyTo()));
		str.append( ";BDCATEGORY="+ getFieldValue(commonFilter.getBdCategorized()));
		str.append( ";RELATEDCON="+ getComboSelectionId(commonFilter.getRelatedto()));
		str.append( ";SUPERVISOR="+getComboSelectionId(commonFilter.getSupervisor()));
		str.append( ";MACHINEID="+getComboSelectionId(commonFilter.getMachine()));*/
		
		str.append(  getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
		str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
		str.append( getComboSelectionId(commonFilter.getTrade(),"TRADEID" ));
		str.append(  getComboSelectionId(commonFilter.getAssembly(),"ASSEMBLY"));
		str.append(  getComboSelectionId(commonFilter.getJhStep(),"JHSTEP"));
		str.append(  getComboSelectionId(commonFilter.getPurpose(),"PURPOSEID"));
		str.append(  getComboSelectionId(commonFilter.getCategory(),"CATEGID"));
		str.append(  getComboSelectionId(commonFilter.getSubCategory(),"SUBCATID"));
		str.append(  getComboSelectionId(commonFilter.getManufacture(),"MANUFACTURID"));
		str.append(  getComboSelectionId(commonFilter.getSupplier(),"SUPPLIERID"));
		str.append(  getComboSelectionId(commonFilter.getAmcvendorId(),"AMCVENDORID"));
		str.append(  getComboSelectionId(commonFilter.getMachineRank(),"MCHMRANK"));
		str.append( getComboSelectionId(commonFilter.getShift(),"SHIFTID"));
		str.append(  getFieldValue(commonFilter.getInstalfrom(),"INSTALFROM"));
		str.append(  getFieldValue(commonFilter.getInstalTo(),"INSTALTO"));
		str.append(  getFieldValue(commonFilter.getAmcFrom(),"AMCFROM"));
		str.append(  getFieldValue(commonFilter.getAmcTo(),"AMCTO"));
		str.append(  getFieldValue(commonFilter.getAmcrnewFrom(),"AMCRENEWFROM"));
		str.append(  getFieldValue(commonFilter.getAmcrenewTo(),"AMCRENEWTO"));
		str.append(  getFieldValue(commonFilter.getWarrantyFrom(),"WARRANTYFROM"));
		str.append(  getFieldValue(commonFilter.getWarrantyTo(),"WARRANTYTO"));
		str.append(  getFieldValue(commonFilter.getBdCategorized(),"BDCATEGORY"));
		str.append(  getComboSelectionId(commonFilter.getRelatedto(),"RELATEDCON"));
		str.append( getComboSelectionId(commonFilter.getSupervisor(),"SUPERVISOR"));
		str.append( getComboSelectionId(commonFilter.getMachine(),"MACHINEID"));
		
		StringBuffer tempStr = new StringBuffer();		
		
		CommonMessage.debugMsg("abonorjkfg value"+commonFilter.getABNORMALITY());
		
		if (getFieldValue( commonFilter.getABNORMALITY() ).equals("1"))
			tempStr.append("'ABNORMALITY',");				
		if (getFieldValue( commonFilter.getBREAKDOWN() ).equals("1"))
			tempStr.append("'BREAKDOWN',");
		if (getFieldValue( commonFilter.getKAIZEN() ).equals("1"))
			tempStr.append("'KAIZEN',");
		if (getFieldValue( commonFilter.getOPL() ).equals("1"))
			tempStr.append("'OPL',");
		if (getFieldValue( commonFilter.getPREVENTIVE() ).equals("1"))			
			tempStr.append("'PREVENTIVE',");		
		if (getFieldValue(commonFilter.getIMPROVEMENT() ).equals("1"))
			tempStr.append("'KAIZEN',");
		if (getFieldValue(commonFilter.getGENERAL()).equals("1"))
			tempStr.append("'MACHINE ACTIVITY',");
		if (getFieldValue(commonFilter.getUNPLANNED()).equals("1"))
			tempStr.append("'UNPLANNED',");
	
		
		
		if (tempStr.length() > 1) {

				str.append("ACTIVITYTYPE="+tempStr.substring(0,tempStr.length()-1)+";");
		}
		else
					str.append("ACTIVITYTYPE=;");
		
		//str.append(";");
		
		CommonMessage.debugMsg("String Sql:"+str.toString());
		return str.toString();
	}

	
	public static String getBDRelatedConditionStr(CommonFilter commonFilter)
	{

		StringBuffer str = new StringBuffer();
		StringBuffer tempStr = new StringBuffer();
		
		if( isValidKeyId(commonFilter.getISFORGRAPH()+"") )
			str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");
		
		str.append( getCommonRelatedCondStr(commonFilter));
		str.append(  getComboSelectionId(commonFilter.getCmbBreakdown(),"BDMSKEYID"));
		str.append(  getComboSelectionId(commonFilter.getCmbMsr(),"MSRID"));
		str.append(  getComboSelectionId(commonFilter.getCmbFailureType(),"FAILURETYPEID"));
		str.append(  getComboSelectionId(commonFilter.getTrade(),"TRADEID"));
		str.append( getComboSelectionId(commonFilter.getPhenomena(),"DEFECTPHENOMENAID"));
		str.append(  getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
		str.append(  getComboSelectionId(commonFilter.getCmbcause(),"CAUSEID"));
		str.append(  getComboSelectionId(commonFilter.getCmbbdRootCause(),"BDROOTCAUSEID"));
		str.append(  getComboSelectionId(commonFilter.getCmbprodcngroup(),"PRODUCTIONGROUPID"));
		str.append(  getComboSelectionId(commonFilter.getCmbshiftIncharge(),"SHIFTINCHARGEID"));
		str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQUIPMENTGROUPID"));
		str.append(  getComboSelectionId(commonFilter.getCmbyy(),"WHYWHYID"));
		str.append(  getComboSelectionId(commonFilter.getAssembly(),"ASSEMBLYID"));
		str.append(  getComboSelectionId(commonFilter.getMachineRank(),"MCHMRANK"));
		str.append(  getFieldValue(commonFilter.getChkoccurchkbox(),"CHK_OCCURENCE")); 
		
		if (getFieldValue( commonFilter.getChkfrequencychkbox() ).equals("1"))
			str.append( "TYPE=FREQUENCY;");
		else
			str.append( "TYPE=SEVERITY;");
		
		str.append( getFieldValue( commonFilter.getChktimeChkBox(),"CHK_TIME")); 
		str.append(  getFieldValue(commonFilter.getChkzeroBdChkBox(),"CHK_ZEROBREAKDOWN"));
		str.append(  getFieldValue(commonFilter.getChkremallchkbox(),"CHK_REMOVEBLANK"));
		str.append(  getFieldValue(commonFilter.getChkfachkbox(),"CHK_FINALACTION")); 
		str.append(  getFieldValue(commonFilter.getChkpillarchkbox(),"CHK_PILLAR")); 
		str.append(  getFieldValue(commonFilter.getChkrcchkbox(),"CHK_ROOTCAUSE")); 
	 	str.append( getFieldValue(commonFilter.getChkrccchkbox(),"CHK_ROOTCAUSECLASSIFICATION"));
		str.append( getFieldValue(commonFilter.getChkcmchkbox(), "CHK_COUNTERMEASURE")); 
		str.append(  getFieldValue(commonFilter.getBdType(),"BDTYPE"));
		str.append(  getComboSelectionId(commonFilter.getCmbstep(),"JHSTEP"));
		str.append( getFieldValue(commonFilter.getRange(),"TOP"));
		str.append( getFieldValue(commonFilter.getTxttop(),"TOPN"));//modified by manikandan 19.3.12 for bdpareto
		str.append(  getComboSelectionId(commonFilter.getCboselBdType(),"BDTYPE")); 
		str.append(  getComboSelectionId(commonFilter.getCbooptions(),"OPTIONS")); 
		str.append(  getComboSelectionId(commonFilter.getCounterMeasure(),"COUNTERMEASURE"));
		str.append(  getComboSelectionId(commonFilter.getBdstatus(),"STATUS"));
		str.append(  getComboSelectionId(commonFilter.getCboparetooptions(),"PARETOOPTIONS")); 
		str.append(  getComboSelectionId(commonFilter.getcboActivitytype(),"ACTIVITYTYPE")); 
		str.append(  getComboSelectionId(commonFilter.getCmbEngineer(),"ENGINEERID"));
		str.append( getComboSelectionId( commonFilter.getCmbsparesSelectBox(),"SPARESSELECTED")); 		
		str.append(  getFieldValue(commonFilter.getChkjhchkbox(),"CHK_JH")); 
		str.append(  getFieldValue(commonFilter.getChkpmchkbox(),"CHK_PM"));
		str.append(  getFieldValue(commonFilter.getChkdesignchkbox(),"CHK_DESIGN"));
		str.append(  getFieldValue(commonFilter.getChketchkbox(),"CHK_ET")); 	
		str.append(  getFieldValue(commonFilter.getChkircchkbox(),"CHK_INCLUDERC"));
		str.append(  getFieldValue(commonFilter.getChkiyychkbox(),"CHK_INCLUDEYY"));
		str.append(  getFieldValue(commonFilter.getChkundefinedPP(),"CHK_UNDEFINEDPP"));
		str.append(  getFieldValue(commonFilter.getChkrepeatedBD(),"CHK_REPEATEDBD"));
		str.append(  getFieldValue(commonFilter.getChkActwise(),"CHK_ACTIVITY"));
		str.append(  getFieldValue(commonFilter.getChkMonwise(),"CHK_MONTH"));
		
		str.append(  getComboSelectionId(commonFilter.getCboFrequency(),"FREQUENCY"));
		str.append(  getComboSelectionId(commonFilter.getCboJobType(),"PMDTYPE"));
		str.append( getFieldValue(commonFilter.getRepotingType(),"REPORTTYPE"));
		
		str.append(  getFieldValue(commonFilter.getChkGrpByEqp(),"GROUPBYMCH"));
		str.append(  getFieldValue(commonFilter.getChkAllEqpmnt(),"ALLMACHINES"));
		str.append(  getFieldValue(commonFilter.getChktradewise(),"CHK_TRADEWISE"));
		str.append( getFieldValue(commonFilter.getChkjobtypwise(),"CHK_JOBTYPE"));
		str.append( getFieldValue(commonFilter.getBdActivity(),"BDACTWISE"));
		
		if(isValidKeyId(commonFilter.getParamtype()))
			str.append("PARAMTYPE="+commonFilter.getParamtype()+";");
		else
		{
		if (getFieldValue( commonFilter.getChkemployee() ).equals("1"))
			tempStr.append("1");
		if (getFieldValue( commonFilter.getChkcontract() ).equals("1"))
			tempStr.append("2");
		if (getFieldValue( commonFilter.getChkspare() ).equals("1"))
			tempStr.append("3");
		if (getFieldValue( commonFilter.getChkservice() ).equals("1"))
			tempStr.append("4");
		if (getFieldValue( commonFilter.getChkutility() ).equals("1"))
			tempStr.append("5");
		if (getFieldValue( commonFilter.getChkother() ).equals("1"))
			tempStr.append("6");
		if (getFieldValue( commonFilter.getChktotal() ).equals("1"))
			tempStr.append("7");
		//if (getFieldValue( commonFilter.getShe() ).equals("1"))
		//	tempStr.append("'SHE',");
		
		if (tempStr.length() > 0) 
			str.append("PARAMTYPE="+tempStr+";");
		else
			str.append("PARAMTYPE=7;");
		}
	
/*		if(commonFilter.getIsGraphRequired().equals("Y"))
			str.append(";ISFORGRAPH=Y");
		else
			str.append(";ISFORGRAPH=N");
*/			

		if (getFieldValue( commonFilter.getChkjobtypwise() ).equals("1"))
			str.append( "CHK_RPTTYPE=J;");
		else
			str.append( "CHK_RPTTYPE=T;");
		
		StringBuffer tempString = new StringBuffer();		
			
		if (getFieldValue( commonFilter.getChkInternal() ).equals("1"))
			str.append("INTERNAL=Y;");
		
		if (getFieldValue( commonFilter.getChkExternal() ).equals("1"))
			str.append("EXTERNAL=Y;");
		
		str.append(getFieldValue(commonFilter.getCompleted(),"BDCOMPLETED") );
		str.append(getFieldValue(commonFilter.getPending(),"BDPENDING") );
		str.append(getComboSelectionId(commonFilter.getBdMechCond(),"MACHINECOND"));
		
		CommonMessage.debugMsg("test remove blank..............."+commonFilter.getRemoveBlank());
		str.append(getFieldValue(commonFilter.getRemoveBlank(),"REMOVEBLANK"));
		
	//	CommonMessage.debugMsg("commonFilter.getCboJobType() "+getComboSelectionId(commonFilter.getCboJobType()) );
	/*	if (getComboSelectionId( commonFilter.getCboJobType() ).equals("CBM"))
			str.append(";PMDTYPE=CBM");	
		else if (getComboSelectionId( commonFilter.getCboJobType() ).equals("TBM"))
			str.append(";PMDTYPE=TBM");	
		else if (getComboSelectionId( commonFilter.getCboJobType() ).equals("PRM"))
			str.append(";PMDTYPE=PRM");	
		else if (getComboSelectionId( commonFilter.getCboJobType() ).equals("MBR"))
			str.append(";PMDTYPE=MBR");	
		else if (getComboSelectionId( commonFilter.getCboJobType() ).equals("RBM"))
			str.append(";PMDTYPE=RBM");	
		else if (getComboSelectionId( commonFilter.getCboJobType() ).equals("CAL"))
			str.append(";PMDTYPE=CAL");	
		else if (getComboSelectionId( commonFilter.getCboJobType() ).equals("SDM"))
			str.append(";PMDTYPE=SDM");
		else
			str.append(";PMDTYPE=");
		
	*/	

		//str.append(";");
		
		// missing  (Root Cause Classification, Included Root Cause, Included Why-Why,)		
		
		return str.toString();
		
	}
	
	public static String getGridCommonParams(CommonFilter commonFilter)
	{
		CommonMessage.debugMsg("row total:"+commonFilter.getToRow());
		StringBuffer str = new StringBuffer();
		str.append("FILTERCOND="+";");
		str.append("ISTOTALCNT=" +commonFilter.getViewClick()+";");
		//CommonMessage.debugMsg("ISTOTALCNT............."+commonFilter.getViewClick());
		if( isValidKeyId(commonFilter.getRowTotal()+"") )
			str.append("ISFORTOTALROW=" + commonFilter.getRowTotal()+";");		
		if(isValidKeyId(commonFilter.getFromRow()) && isValidKeyId(commonFilter.getToRow()+";"))
			str.append("FROMTOROW="+commonFilter.getFromRow() +" AND "+commonFilter.getToRow()+";");
			//str.append("FROMTOROW="+commonFilter.getFromRow() +" AND 1000;");
		//str.append(";ORDERCOL=" );
		CommonMessage.debugMsg("sort...."+commonFilter.getGridSortColumn()+commonFilter.getGridSortOrder());
		if(  isValidKeyId(commonFilter.getGridSortColumn()) && isValidKeyId(commonFilter.getGridSortOrder()))
			str.append("ORDERCOL=" + commonFilter.getGridSortColumn()  +" "+ commonFilter.getGridSortOrder()+";");
		str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		
		if( isValidKeyId(commonFilter.getIsGetCol()) )
			str.append("ISGETCOL=" + commonFilter.getIsGetCol()+";");
		
		//str.append(";");
		return str.toString().replace("\u0000", "");
	
	}
	
	public static String getGridActionPlanParams(ActionPlanParams actionPlanParam)
	{
		CommonMessage.debugMsg("row total:"+actionPlanParam.getToRow());
		StringBuffer str = new StringBuffer();
		//CommonMessage.debugMsg("ISTOTALCNT............."+commonFilter.getViewClick());	
		if(isValidKeyId(actionPlanParam.getFromRow()) && isValidKeyId(actionPlanParam.getToRow()+";"))
			str.append("FROMTOROW="+actionPlanParam.getFromRow() +" AND "+actionPlanParam.getToRow()+";");
			//str.append("FROMTOROW="+commonFilter.getFromRow() +" AND 1000;");
		//str.append(";ORDERCOL=" );
		CommonMessage.debugMsg("sort...."+actionPlanParam.getGridSortColumn()+actionPlanParam.getGridSortOrder());
		if(  isValidKeyId(actionPlanParam.getGridSortColumn()) && isValidKeyId(actionPlanParam.getGridSortOrder()))
			str.append("ORDERCOL=" + actionPlanParam.getGridSortColumn()  +" "+ actionPlanParam.getGridSortOrder()+";");
		
		str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(actionPlanParam.getGridFilters())+";");
		
		
		//str.append(";");
		return str.toString().replace("\u0000", "");
	
	}

	public static String getGridActionPlanParamsOnly(ActionPlanParams actionPlanParam)
	{
		CommonMessage.debugMsg("row total:"+actionPlanParam.getToRow());
		StringBuffer str = new StringBuffer();
		//CommonMessage.debugMsg("ISTOTALCNT............."+commonFilter.getViewClick());	
		
		str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(actionPlanParam.getGridFilters())+";");
		
		
		//str.append(";");
		return str.toString().replace("\u0000", "");
	
	}
	public static String getAbnRelatedConditionStr(CommonFilter commonFilter)
	{
		StringBuffer str = new StringBuffer();
		str.append( getCommonRelatedCondStr(commonFilter));				
		str.append(  getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
		str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQUIPMENTGROUP"));
		str.append(  getComboSelectionId(commonFilter.getMachine(),"EQUIPMENTID"));
		str.append(  getComboSelectionId(commonFilter.getMachineRank(),"MACHINERANK"));
		str.append(  getComboSelectionId(commonFilter.getAbnJhStep(),"JHSTEP"));
		str.append(  getComboSelectionId(commonFilter.getTrade(),"TRADEID"));
		str.append(  getComboSelectionId(commonFilter.getAbnType(),"TYPEID"));
		str.append(  getComboSelectionId(commonFilter.getAbnCategory(),"CATEGORYID"));
		str.append(  getComboSelectionId(commonFilter.getAbnImpact(),"IMPACTID"));
		str.append(  getComboSelectionId(commonFilter.getProduction(),"PRODUCTIONID"));		
		str.append(  getComboSelectionId(commonFilter.getAbnClass(),"TAGID"));		
		str.append(  getComboSelectionId(commonFilter.getAbnStatus(),"STATUS"));
		str.append(  getComboSelectionId(commonFilter.getAbnImpType(),"ABNIMPTYPE"));
		str.append(  getFieldValue(commonFilter.getAbnAllch(),"CHK_ALL"));//.equals("checked")?"1":"0")); 
		str.append(  getFieldValue(commonFilter.getAbnDetect(),"CHK_DETECTDATE"));//.equals("checked")?"1":"0")); 
		str.append(  getFieldValue(commonFilter.getAbnCause(),"CHK_CAUSE"));//.equals("checked")?"1":"0")); 
		str.append(  getFieldValue(commonFilter.getAbnDetectBy(),"CHK_DETECTBY"));//.equals("checked")?"1":"0")); 
		str.append(  getFieldValue(commonFilter.getAbnCatch(),"CHK_ABNCATEGORY"));//.equals("checked")?"1":"0")); 
		str.append(  getFieldValue(commonFilter.getAbnImp(),"CHK_ABNIMPACT"));//.equals("checked")?"1":"0")); 
		str.append(  getFieldValue(commonFilter.getAbnormalityType(),"ABNTYPE"));
		str.append(  getFieldValue(commonFilter.getAbnImprovement(),"ABNIMPROVEMENTTYPE"));
		str.append(  getFieldValue(commonFilter.getChkAbnType(),"CHKABNTYPE"));
		str.append(  getFieldValue(commonFilter.getChkMould(),"CHKMOULD"));
		str.append(  getFieldValue(commonFilter.getChkWhyWhyHappen(),"CHKWHYHAPPEN"));
		str.append(  getFieldValue(commonFilter.getChkTrade(),"CHKTRADE"));
		str.append(  getFieldValue(commonFilter.getChkStatus(),"CHKSTATUS"));
		str.append(  getFieldValue(commonFilter.getChkTagClass(),"CHKTAGCLASS"));
		str.append(  getFieldValue(commonFilter.getChkHTAType(),"CHKHTATYPE"));
		str.append(  getFieldValue(commonFilter.getLoss(),"AFFECTLOSS"));
		str.append(  getFieldValue(commonFilter.getGroupByCircle(),"ISCIRCLEWISE"));
		str.append(  getFieldValue(commonFilter.getAuditRpt(),"AUDITRPT"));
		str.append(  getFieldValue(commonFilter.getAbnViewType(),"ABNVIEWTYPE"));
		if( isValidKeyId(commonFilter.getAbnormalityType()) && commonFilter.getAbnormalityType().equals("SHE"))
				str.append(  getFieldValue(commonFilter.getSafetyPatrol(),"SAFETYPATROL"));
		else
			str.append(  getFieldValue(commonFilter.getSafetyPatrol(),"SFTPATROL"));
		str.append(  getComboSelectionId(commonFilter.getDectetedBy(),"DETECTEDBY"));
		str.append(getComboSelectionId( commonFilter.getResponsibility(),"RESPONSIBILITY"));
		str.append(getFieldValue( commonFilter.getTeamId(),"TEAMID"));
		str.append( getFieldValue(commonFilter.getRemoveBlank(),"REMOVEBLANK"));
		str.append( getFieldValue(commonFilter.getRepeatedAbn(),"REPEATEDABN"));
		str.append(  getFieldValue(commonFilter.getChktradewise(),"CHKTRADEWISE"));
		if( isValidKeyId(commonFilter.getISFORGRAPH()+"") )
			str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");
		return str.toString();
	}
	
	public static String getActionPlanRelatedConditionStr(ActionPlanParams actionPlanParam)
	{
		StringBuffer str = new StringBuffer();
		//str.append( getCommonRelatedCondStr(actionPlanParam));
		str.append(getFieldValue(actionPlanParam.getDtFromDate(),"FROMDATE"));
		str.append(getFieldValue(actionPlanParam.getDtToDate(),"TODATE"));		
		str.append(getFieldValue(actionPlanParam.getDtFromMonth(),"FROMMONTH"));
		str.append(getFieldValue(actionPlanParam.getDtToMonth(),"TOMONTH"));
		if(CommonFunctions.isValidKeyId(actionPlanParam.getKeyid()) && "Y".equals(actionPlanParam.getKeyid()) ) {
			str.append(getFieldValue(actionPlanParam.getFlid(),"FLID"));
		}
		if(actionPlanParam.MODE_VIEW.equals(actionPlanParam.getMode())) {
			str.append(getFieldValue(actionPlanParam.getFlid(),"FLID"));
		}
		
		str.append(getFieldValue(actionPlanParam.getEmployeeId(),"EMPLOYEEID"));
		if(CommonFunctions.isValidKeyId(actionPlanParam.getDtFromMonth()) &&  CommonFunctions.isValidKeyId(actionPlanParam.getDtToMonth())) {
			str.append(getFieldValue("Y","ISMONTHWISE"));
		}
		//str.append(getFieldValue(actionPlanParam.get,"ISMONTHWISE"));	

		return str.toString();
	}

	public static String getOPLRelatedCondSql(CommonFilter commonFilter)
	{
		StringBuffer str = new StringBuffer();
		
		str.append( getCommonRelatedCondStr(commonFilter));
		str.append(getFieldValue(commonFilter.getFromDate(),"OPFROMDT"));
		str.append(getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
		str.append(getComboSelectionId(commonFilter.getJhStep(),"JHSTEPID"));
		str.append(getComboSelectionId(commonFilter.getMachineRank(),"MACHINERANK"));
		str.append(getComboSelectionId(commonFilter.getOplTypeid(),"OPLTYPE"));
		CommonMessage.debugMsg("commonFilter.getOplTypeid()"+commonFilter.getOplTypeid());
		str.append(getComboSelectionId(commonFilter.getOplNoid(),"OPLNO"));
		CommonMessage.debugMsg("commonFilter.getOplNoid()"+commonFilter.getOplNoid());
		str.append(getComboSelectionId(commonFilter.getJHKaizenCategory(),"JHKAIZENCATEGORY"));
		str.append(getFieldValue(commonFilter.getThemelike(),"THEMELIKE"));
		str.append(getFieldValue(commonFilter.getLessonlike(),"LESSONLIKE"));
		str.append(getFieldValue(commonFilter.getFromRow(),"FROMROW"));
		str.append(getFieldValue(commonFilter.getToRow(),"TOROW"));
		str.append(getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
		str.append(getFieldValue(commonFilter.getToDate(),"OPLTILLDT"));
		
		str.append( getFieldValue( commonFilter.getBK(),"CHK_BASICKON"));
		CommonMessage.debugMsg("commonFilter.getBK()"+commonFilter.getBK());
		str.append( getFieldValue( commonFilter.getIC(),"CHK_IMPROVCASE")); 
		CommonMessage.debugMsg("commonFilter.getIC()"+commonFilter.getIC());
		str.append( getFieldValue( commonFilter.getTC(),"CHK_TROUBLECASE"));
		CommonMessage.debugMsg("commonFilter.getTC()"+commonFilter.getTC());
		str.append( getFieldValue( commonFilter.getRemoveBlank(),"REMOVEBLANK"));
		str.append( getFieldValue( commonFilter.getMPWorthy(),"MPWORTHY"));
		str.append( getFieldValue( commonFilter.getUtiliseFuture() ,"UTILISEFUTURE"));
	
		/*str.append( ";CHK_DEVMANAGE="+getFieldValue( commonFilter.getDM()));
		str.append( ";CHK_EDUTRAINING="+getFieldValue( commonFilter.getET()));
		str.append( ";CHK_JISHUHOZEN="+getFieldValue( commonFilter.getJH()));
		str.append( ";CHK_KKAIZEN="+getFieldValue( commonFilter.getKK()));
		str.append( ";CHK_OFFTPM="+getFieldValue( commonFilter.getOTpm()));
		str.append( ";CHK_PLANNEDMAIN="+getFieldValue( commonFilter.getPm()));
		str.append( ";CHK_QUALITYMAIN="+getFieldValue( commonFilter.getQm()));
		str.append( ";CHK_SAFTEYHELMAIN="+getFieldValue( commonFilter.getShe()));
		*/
		StringBuffer tempStr = new StringBuffer();
		
	//	CommonMessage.debugMsg("djkdjf dfjkdj djf fdj "+getFieldValue( commonFilter.getOTpm()) );
		
		if (getFieldValue( commonFilter.getDM() ).equals("1"))
			tempStr.append("'DM',");
		if (getFieldValue( commonFilter.getET() ).equals("1"))
			tempStr.append("'ET',");
		if (getFieldValue( commonFilter.getJH() ).equals("1"))
			tempStr.append("'JH',");
		if (getFieldValue( commonFilter.getKK() ).equals("1"))
			tempStr.append("'KK',");
		if (getFieldValue( commonFilter.getOTpm() ).equals("1"))
			tempStr.append("'OTPM',");
		if (getFieldValue( commonFilter.getPm() ).equals("1"))
			tempStr.append("'PM',");
		if (getFieldValue( commonFilter.getQm() ).equals("1"))
			tempStr.append("'QM',");
		if (getFieldValue( commonFilter.getShe() ).equals("1"))
			tempStr.append("'SHE',");
		
		CommonMessage.debugMsg("lenght "+tempStr.toString());
		
		if (tempStr.length() > 1) {
				CommonMessage.debugMsg("subs strj"+tempStr.substring(0,tempStr.length()-1));
				str.append("CHK_SELPILLAR="+tempStr.substring(0,tempStr.length()-1)+";");
		}
		else
					str.append("CHK_SELPILLAR="+";");
		//str.append(";");
		
		return  str.toString();
	}

	public static String getSparesRelatedCondStr(CommonFilter commonFilter)
	{
		StringBuffer str = new StringBuffer();		
		
		str.append( getCommonRelatedCondStr(commonFilter));		
		str.append(  getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
		str.append( getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
		str.append(  getComboSelectionId(commonFilter.getTrade(),"TRADEID"));
		str.append(  getComboSelectionId(commonFilter.getAssembly(),"ASSEMBLY"));
		str.append(  getComboSelectionId(commonFilter.getSparePartNo(),"SPAREID"));
		str.append(  getComboSelectionId(commonFilter.getSpareDescn(),"PARTNAMELIKE"));
		str.append(  getComboSelectionId(commonFilter.getOptions(),"OPTIONS"));
		str.append(  getComboSelectionId(commonFilter.getCriticality(),"CRITICALITY"));
		str.append(  getComboSelectionId(commonFilter.getClassifcn(),"CLASSIFN"));
		str.append(  getComboSelectionId(commonFilter.getSpqcategory(),"CATEGORY"));
		str.append(  getComboSelectionId(commonFilter.getSubCategory(),"SUBCAT"));
		str.append(  getComboSelectionId(commonFilter.getUom(),"UOM"));
		str.append(  getComboSelectionId(commonFilter.getMake(),"MAKE"));
		str.append(  getComboSelectionId(commonFilter.getModel(),"MODEL"));
		str.append(  getComboSelectionId(commonFilter.getAbcClass(),"ABCCLASS"));
		str.append(  getComboSelectionId(commonFilter.getSources(),"SOURCE"));
		str.append(  getComboSelectionId(commonFilter.getSpqType(),"SPRTYPE"));
		str.append(  getComboSelectionId(commonFilter.getMachineSpec(),"ISMCHSPEC"));
		str.append(  getComboSelectionId(commonFilter.getShelfLifeunt(),"SHELFLIFEUNIT"));
		str.append(  getComboSelectionId(commonFilter.getShelfLifeItem(),"SHELFLIFEITEM"));		
		str.append( getComboSelectionId(commonFilter.getCmbprodcngroup(),"PRODUCTIONGRP"));
		str.append( getComboSelectionId(commonFilter.getMachineRank(),"MACHINERANK"));
		str.append( getComboSelectionId(commonFilter.getJhStep(),"JHSTEPID"));
		str.append(  getComboSelectionId(commonFilter.getSupplier(),"SUPPLIERID"));
		str.append( getComboSelectionId(commonFilter.getJobtype(),"JOBTYPE"));
		str.append( getComboSelectionId(commonFilter.getFrequency(),"FREQUNIT"));
		str.append( getComboSelectionId(commonFilter.getSupplier(),"SUPPLIER"));
		str.append( getFieldValue(commonFilter.getRepportType(),"REPORTTYPE"));
		str.append( getComboSelectionId(commonFilter.getActType(),"ACTIVITYTYPE"));
		//str.append( "REPORTTYPE=ACTTYPE;");
		//str.append(";");
		CommonMessage.debugMsg("Cond str="+str.toString());
		return str.toString();
	}

public static String getJHCLITRelatedCondStr(CommonFilter commonFilter)
{
	StringBuffer str = new StringBuffer();	
	str.append( getCommonRelatedCondStr(commonFilter));
	str.append( getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));	
	str.append( getComboSelectionId(commonFilter.getJhStep(),"JHSTEPID"));
	str.append( getComboSelectionId(commonFilter.getMachineRank(),"MACHINERANK"));
	str.append( getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));	
	str.append( getComboSelectionId(commonFilter.getFrequency(),"FREQUENCY"));
	str.append( getComboSelectionId(commonFilter.getJhduration(),"DURATION"));
	str.append( getFieldValue(commonFilter.getTxtDurFrom(),"DURATIONFROM"));
	str.append( getFieldValue(commonFilter.getTxtDurTo(),"DURATIONTO"));
	str.append( getComboSelectionId(commonFilter.getCmbprodcngroup(),"PRODUCTIONGRP"));
	str.append( getFieldValue(commonFilter.getMainkeyid(),"MAINKEYID"));
	str.append( getFieldValue( commonFilter.getRemoveBlank(),"REMOVEBLANK"));
	//str.append(";");
	//CommonMessage.debugMsg("Cond Str:"+str.toString());
	
	return str.toString();
}

public static String getKAIZENRelatedCondStr(CommonFilter commonFilter)
{
	StringBuffer str = new StringBuffer();	
	str.append( getCommonRelatedCondStr(commonFilter));
	str.append( getComboSelectionId(commonFilter.getImprovmntNoid(),"IMPROVENOID"));
	str.append( getFieldValue(commonFilter.getImprovementDate(),"IMPRVDATE"));
	str.append( getComboSelectionId(commonFilter.getPillarid(),"PILLERID"));
	str.append( getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
	str.append( getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));	
	str.append( getComboSelectionId(commonFilter.getProduction(),"PRODUCTIONID"));
	str.append( getComboSelectionId(commonFilter.getLossType(),"LOSSTYPE"));
	str.append( getComboSelectionId(commonFilter.getStatuss(),"KZNSTATUS"));
	str.append( getComboSelectionId(commonFilter.getKznmThemecategoryid(),"KZNMTHEMECATEGORYID"));    
    str.append( getComboSelectionId(commonFilter.getResponsibility(),"KZNMRESULTAREA"));    
	str.append( getFieldValue(commonFilter.getColVal(),"COLVAL"));	
	str.append( getFieldValue( commonFilter.getRemoveBlank(),"REMOVEBLANK"));
	str.append( getFieldValue( commonFilter.getMPWorthy(),"MPWORTHY"));
	str.append( getFieldValue( commonFilter.getUtiliseFuture() ,"UTILISEFUTURE"));
	str.append(getFieldValue(commonFilter.getResultAreaP(),"CHK_RESULTAREAP"));
	str.append(getFieldValue(commonFilter.getResultAreaQ(),"CHK_RESULTAREAQ"));
	str.append(getFieldValue(commonFilter.getResultAreaC(),"CHK_RESULTAREAC"));
	str.append(getFieldValue(commonFilter.getResultAreaD(),"CHK_RESULTAREAD"));
	str.append(getFieldValue(commonFilter.getResultAreaS(),"CHK_RESULTAREAS"));
	str.append(getFieldValue(commonFilter.getResultAreaM(),"CHK_RESULTAREAM"));
	str.append(getFieldValue(commonFilter.getResultAreaE(),"CHK_RESULTAREAE"));
	if(commonFilter.getMainGroup()==null)
		str.append( "MAINGROUP=SECTION");
	str.append( "MAINGROUP="+getFieldValue(commonFilter.getMainGroup())+";");
	str.append( "SUBGROUP="+getFieldValue(commonFilter.getSubGroupHD())+";");
	//str.append(";");	
	return str.toString();
}


/*******************************Employee Role Related*********************************/

public static String getEmployeeRoleRelatedCondStr(CommonFilter commonFilter)
{
	StringBuffer str = new StringBuffer();	
	str.append( getCommonRelatedCondStr(commonFilter));
	str.append(getComboSelectionId(commonFilter.getRoleId(),"ROLEKEYID"));
	str.append(getComboSelectionId(commonFilter.getEmployee(),"EMPMKEYID"));
	str.append(getFieldValue(commonFilter.getTemp(),"FNLNORIGINALID"));
	CommonMessage.debugMsg("My Param:" +str.toString());
	return str.toString();
}
/*
private static StringBuffer getEmployeeRoleRelatedCondStr(CommonFilter commonFilter)
{
	StringBuffer str = new StringBuffer();
	str.append(getComboSelectionId(commonFilter.getRoleId(),"ROLEKEYID"));
	str.append(getComboSelectionId(commonFilter.getLocation(),"FNLNORIGINALID"));
	str.append(getComboSelectionId(commonFilter.getEmployee(),"EMPMKEYID"));
	//str.append(getComboSelectionId(commonFilter.getRoleId(),"ROLEKEYID"));
	
	
	return str;
}*/

public static String getPCSRelatedCondStr(CommonFilter commonFilter)
{
	StringBuffer str = new StringBuffer();	
	str.append( getCommonRelatedCondStr(commonFilter));
	
	str.append( getFieldValue(commonFilter.getParamCode(),"PARAMCODE"));
	if( isValidKeyId(commonFilter.getPareto()+"") )
		str.append("ISFORPARETO="+commonFilter.getPareto()+";");
	
	if( isValidKeyId(commonFilter.getISFORGRAPH()+"") )
		str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");

	str.append(getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
	str.append(  getComboSelectionId(commonFilter.getCmbpcssubgrp(),"SUBGROUPID"));
	str.append(  getComboSelectionId(commonFilter.getCmbpcsprrod(),"PRODUCTID"));
	str.append(  getFieldValue( commonFilter.getChkboxef(),"CHK_ENTRYFORMAT")); 
	str.append(  getFieldValue( commonFilter.getChkboxdf(),"CHK_DETAILFORMAT")); 
	str.append(  getFieldValue( commonFilter.getChkboxar(),"CHK_AR")); 
	str.append(  getFieldValue( commonFilter.getChkboxpr(),"CHK_PR")); 
	str.append(  getFieldValue( commonFilter.getChkboxqr(),"CHK_QR")); 
	str.append(  getFieldValue( commonFilter.getChkboxoee(),"CHK_OEE")); 
	str.append(  getFieldValue( commonFilter.getChkboxall(),"CHK_ALL")); 
	str.append(  getFieldValue( commonFilter.getChkboxoccurence(),"CHK_OCCURENCE")); 
	str.append(  getFieldValue( commonFilter.getChkboxtime(),"CHK_DOWNTIME")); 
	//str.append( ";CHK_VIEWADDINF="+ getFieldValue( commonFilter.getChkboxvai())); 
	str.append(  getFieldValue( commonFilter.getChkboxhour(),"CHK_HOUR")); 
	str.append(  getFieldValue( commonFilter.getChkboxshift(),"CHK_SHIFT")); 
	str.append(  getFieldValue( commonFilter.getChkboxday(),"CHK_DAY")); 
	str.append(  getFieldValue( commonFilter.getChkboxweek(),"CHK_WEEK")); 
	str.append( getComboSelectionId(commonFilter.getLossType(),"LOSSTYPE"));
	str.append( getFieldValue(commonFilter.getMultiLoss(),"LOSSTYPE"));
	str.append( getFieldValue(commonFilter.getLossId(),"LOSSID"));
	str.append( getComboSelectionId(commonFilter.getRawMatrial(),"RAWMATERIALTYPE"));
	str.append( getFieldValue(commonFilter.getISNEEDWONO(),"ISNEEDWONO"));
	str.append( getFieldValue(commonFilter.getISNEEDPROD(),"ISNEEDPRODUCT"));
	str.append( getFieldValue(commonFilter.getPcsShift(),"ISSHIFTWISE"));
	str.append(getFieldValue(commonFilter.getChkboxMgrCal(),"ISMANAGEMENT"));	
	str.append( getFieldValue(commonFilter.getRemoveBlank(),"REMOVEBLANK"));
	str.append( getFieldValue(commonFilter.getPcsShift(),"SHIFT"));
	str.append( getFieldValue(commonFilter.getSubLoss(),"SUBLOSS"));
	str.append(getFieldValue(commonFilter.getLossRptType(),"REPORTTYPE"));
	
	//str.append(";");
	CommonMessage.debugMsg("is need prod check.."+getFieldValue( commonFilter.getISNEEDPROD()));
	return str.toString();
}

public static String getPMRelatedCondStr(CommonFilter commonFilter)
{
	CommonMessage.debugMsg("filter Cond:"+commonFilter.getJobtype());
	StringBuffer str = new StringBuffer();	
	str.append( getCommonRelatedCondStr(commonFilter));
	str.append(  getComboSelectionId(commonFilter.getTrade(),"TRADEID"));
	str.append( getComboSelectionId(commonFilter.getAssembly(), "ASSEMBLYID"));
	str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
	str.append( getComboSelectionId(commonFilter.getProduction(),"PRODUCTIONID"));
	str.append( getComboSelectionId(commonFilter.getJhStep(),"JHSTEP"));
	str.append( getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
	str.append( getComboSelectionId(commonFilter.getMachineRank(),"MACHINERANK"));
	str.append( getComboSelectionId(commonFilter.getJobtype(),"JOBTYPE"));	
	str.append( getComboSelectionId(commonFilter.getFrequency(),"FREQUNIT"));	
	str.append( getComboSelectionId(commonFilter.getSources(),"SOURCE"));
	str.append( getFieldValue(commonFilter.getDuration(),"DURATION"));
	str.append( getFieldValue(commonFilter.getPmstatus(),"STATUS"));
	str.append( getFieldValue(commonFilter.getMonthly(),"ISFORMONTHLY"));
	//str.append( getFieldValue(commonFilter.getMonwise(),"ISMONTHWISE"));	
	str.append( getFieldValue(commonFilter.getEquipmentFlag(),"EQUIPMENTFLAG"));
	str.append( getComboSelectionId(commonFilter.getMechCond(),"MACHINECOND"));
	str.append( getFieldValue(commonFilter.getWodetailid(),"WORKORDERNO"));
	str.append( getFieldValue(commonFilter.getRemoveBlank(),"REMOVEBLANK"));
	str.append(getFieldValue(commonFilter.getChkActType(),"JOBTYPE"));
	//str.append( ";");
	
	return str.toString();
}
public static String getWORelatedCondStr(CommonFilter commonFilter)
{

	CommonMessage.debugMsg("Act type : "+getComboSelectionId(commonFilter.getTypes()));
	StringBuffer str = new StringBuffer();	
	str.append( getCommonRelatedCondStr(commonFilter));
	if( isValidKeyId(commonFilter.getISFORGRAPH()+"") )
		str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");
	str.append(  getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
	str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQUIPMENTGROUPID"));
	str.append( getComboSelectionId(commonFilter.getMachineRank(),"MCHMRANK"));
	str.append(  getComboSelectionId(commonFilter.getTrade(),"TRADEID"));
	str.append(  getFieldValue(commonFilter.getPrblm(),"TXTPROBLM"));
	str.append(  getComboSelectionId(commonFilter.getCmbLoss(),"LOSS"));
	str.append(  getComboSelectionId(commonFilter.getTypes(),"ACTIVITYTYPE"));
	str.append( getComboSelectionId(commonFilter.getPriority(),"PRIORITY"));
	if(isValidKeyId(commonFilter.getChkOccured()))
		str.append( getFieldValue(commonFilter.getChkOccured().replace("1", "Y"),"CHKOCCDT"));
	
	
	str.append( getFieldValue(commonFilter.getDteOccuredfrm(),"OCCFROMDT"));
	str.append( getFieldValue(commonFilter.getDteOccuredto(),"OCCTODT"));
	
	if(isValidKeyId(commonFilter.getChkReported()))
		str.append( getFieldValue(commonFilter.getChkReported().replace("1", "Y"),"CHKREPORTDDT"));	
	str.append( getFieldValue(commonFilter.getReportedFrom(),"REPORTDFROMDT"));	
	str.append( getFieldValue(commonFilter.getReportedTo(),"REPORTDTODT"));
	
	if (isValidKeyId(commonFilter.getChkAllotted()))
		str.append( getFieldValue(commonFilter.getChkAllotted().replace("1", "Y"),"CHKALLTTD"));
	str.append( getFieldValue(commonFilter.getDteAllotedfrm(),"ALLOTTEDFROMDT"));
	str.append( getFieldValue(commonFilter.getDteAllotedto(),"ALLOTTEDTODT"));
	
	if (isValidKeyId(commonFilter.getChkWorkStart()))
		str.append( getFieldValue(commonFilter.getChkWorkStart().replace("1", "Y"),"CHKWRKSTRT"));
	str.append( getFieldValue(commonFilter.getDteWorkStartfrm(),"WRKSTFROMDT"));
	str.append( getFieldValue(commonFilter.getDteWorkStartto(),"WRKSTTODT"));
	
	if (isValidKeyId(commonFilter.getChkWorkEnd()))
		str.append( getFieldValue(commonFilter.getChkWorkEnd().replace("1", "Y"),"CHKWRKENDFRM"));
	str.append( getFieldValue(commonFilter.getDteWorkEndfrm(),"WRKENDFROMDT"));
	str.append( getFieldValue(commonFilter.getDteWorkEndto(),"WRKENDTODT"));
	
	if (isValidKeyId(commonFilter.getChkProdDate()))
		str.append( getFieldValue(commonFilter.getChkProdDate().replace("1", "Y"),"CHKPRODSTRTFRM"));
	str.append( getFieldValue(commonFilter.getDteProductionfrm(),"PRDSTFROMDT"));
	str.append( getFieldValue(commonFilter.getDteProductionto(),"PRDSTTODT"));
	str.append(  getComboSelectionId(commonFilter.getCmbMsr(),"MSRID"));
	//str.append( getComboSelectionId(commonFilter.getStatuss(),"CMBWOSTATUS"));
	//str.append( ";");
	CommonMessage.debugMsg("filter Cond: " +str.toString());
	return str.toString();
}


public static String getPCSRelatedCondStr(String rowId) {
	// TODO Auto-generated method stub
	return null;
}
public static String getQualityRelatedStr(CommonFilter commonFilter)
{
	CommonMessage.debugMsg("Act type : "+getComboSelectionId(commonFilter.getReportType()));
	StringBuffer str = new StringBuffer();	
	str.append( getCommonRelatedCondStr(commonFilter));
	str.append( getFieldValue(commonFilter.getParamCode(),"PARAMCODE"));
	CommonMessage.debugMsg(" o`````````````` 111111111  "+commonFilter.getPareto() +" ::: "+ commonFilter.getISFORGRAPH());
	if( isValidKeyId(commonFilter.getPareto()+"") )
		str.append("ISFORPARETO="+commonFilter.getPareto()+";");
	
	if( isValidKeyId(commonFilter.getISFORGRAPH()+"") )
		str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");

	str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
	str.append( getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
	str.append( getComboSelectionId(commonFilter.getProcess(),"PROCESSID"));
	str.append(  getComboSelectionId(commonFilter.getComplaintno(),"COMPLAINTNO"));
	str.append(  getComboSelectionId(commonFilter.getComplainttype(),"COMPLAINTTYPE"));
	str.append(  getComboSelectionId(commonFilter.getCustID(),"CUSTOMERID"));
	str.append( getComboSelectionId(commonFilter.getDefactparam(),"DEFECTPARAMETER"));
	str.append( getComboSelectionId(commonFilter.getDefactPhenamena(),"DEFECTPHENOMENA"));
	str.append( getComboSelectionId(commonFilter.getproduct(),"PRODUCTID"));
	str.append( getComboSelectionId(commonFilter.getQtmCause(),"CAUSEID"));
	str.append( getComboSelectionId(commonFilter.getRecordedby(),"RECORDEDBY"));
	str.append( getComboSelectionId(commonFilter.getInspectedby(),"INSPECTEDBY"));
	str.append( getComboSelectionId(commonFilter.getInspection(),"INSPECTION"));
	str.append( getComboSelectionId(commonFilter.getReportType(),"REPORTTYPE"));
	str.append( getComboSelectionId(commonFilter.getProductDesc(),"PRODUCTDESC"));
	str.append( getComboSelectionId(commonFilter.getShift(),"SHIFTID"));
	str.append( getFieldValue(commonFilter.getQtmProductId(),"PRODUCTID"));
	str.append( getFieldValue(commonFilter.getPm(),"PM"));
	str.append( getFieldValue(commonFilter.getKK(),"KK"));	
	str.append( getFieldValue(commonFilter.getET(),"ET"));	
	str.append( getFieldValue(commonFilter.getQm(),"QM"));
	str.append( getComboSelectionId(commonFilter.getQtyystatus(),"STATUS"));
	str.append( getFieldValue(commonFilter.getImpdone(),"IMPLEMENTATIONDONE"));
	str.append( getFieldValue(commonFilter.getRejection(),"REJECTION"));
	str.append( getFieldValue(commonFilter.getMachineId(),"MACHINEID"));
	str.append( getFieldValue(commonFilter.getPhenomenaId(),"vPHENOMENAID"));
	str.append(getFieldValue(commonFilter.getChkjhbox(), "CHKOJT"));
	str.append( getFieldValue(commonFilter.getChkpmbox(),"CHKSOP"));
	str.append( getFieldValue(commonFilter.getChkkkbox(),"CHKKK"));
	str.append( getFieldValue(commonFilter.getChketbox(),"CHKET"));
	str.append( getFieldValue(commonFilter.getChk4mbox(),"CHK4M"));
	str.append( getFieldValue(commonFilter.getChkimpdonebox(),"ChkImpDone"));
	//str.append( getFieldValue(commonFilter.getTxttop(),"TopVal"));
	str.append( "GetQty=Y;");
	str.append( getFieldValue(commonFilter.getTxttop(),"TopVal"));
	str.append( getFieldValue(commonFilter.getDocType(),"DOCTYPE"));
	str.append(  getComboSelectionId(commonFilter.getCboparetooptions(),"PARETOOPTIONS")); 
	str.append( getFieldValue(commonFilter.getRejection(),"ISFORPERCENT"));
	str.append(getFieldValue(commonFilter.getChkboxMgrCal(),"ISMANAGEMENT"));
	if ( getFieldValue(commonFilter.getIsMchwise()).equals("1"))
		str.append( "MACHINEWISEVIEW=Y;");
	else 
		str.append( "MACHINEWISEVIEW=N;");
	 
	
	if ( getFieldValue(commonFilter.getChkinstance()).equals("0")){
		str.append( "CHKINSTANCE=N;");
		str.append( "CHKQUANTITY=Y;");
 
	} 
	if ( getFieldValue(commonFilter.getChkquantity()).equals("0")){
		str.append( "CHKQUANTITY=N;");
		str.append( "CHKINSTANCE=Y;"); 
	 
	} 
	
	if (getFieldValue( commonFilter.getChkdpc()).equals("1")){
	
		 if (getFieldValue(commonFilter.getChkdvp()).equals("1")){
	
				str.append("DISPPHNCOL=Y;");
				str.append("MCH=N;");
			}
		 if ( getFieldValue(commonFilter.getChkdvm()).equals("1")){
	
				str.append("DISPPHNCOL=Y;");
				str.append("MCH=Y;");
			} 
	}else{
		CommonMessage.debugMsg("Process ="+getFieldValue(commonFilter.getChkdvp()));
		CommonMessage.debugMsg("Machinme ="+getFieldValue(commonFilter.getChkdvm()));
		 if (getFieldValue(commonFilter.getChkdvp()).equals("1")){
			 CommonMessage.debugMsg("Check Defect process..");
				str.append("DISPPHNCOL=N;");
				str.append("MCH=N;");
		 }
		  if (getFieldValue( commonFilter.getChkdvm()).equals("1")){
			 CommonMessage.debugMsg("Check Machine..");
				str.append("DISPPHNCOL=N;");
				str.append("MCH=Y;");
			} 
		
	}
	 
	//str.append( ";");
	return str.toString();
	}
public static String getETRelatedStr(CommonFilter commonFilter)
{
	CommonMessage.debugMsg("TAGcommonFilter.getTrarId()");
	StringBuffer str = new StringBuffer();	
	CommonFilterTraining commonFilterTraining = new CommonFilterTraining();
	CommonMessage.debugMsg("commonFilterTraining"+commonFilterTraining.gettrarparentid1());
	str.append( getCommonRelatedCondStr(commonFilter));
	str.append( getFieldValue(commonFilter.getParamCode(),"PARAMCODE"));
	if(isValidKeyId(commonFilter.getTrarId())){
		CommonMessage.debugMsg("commonFilter.getTrarId()"+commonFilter.getTrarId());
		str.append( "TRARKEYID="+commonFilter.getTrarId()+";");
	}
	if(isValidKeyId(commonFilter.getPareto()+"") )
		str.append("ISFORPARETO="+commonFilter.getPareto()+";");
	
	if( isValidKeyId(commonFilter.getISFORGRAPH()+"") ){
		str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");
	}
	if(isValidKeyId(commonFilter.getAttValue())){
		str.append( "COMPVALUE="+commonFilter.getAttValue()+";");
	}
	if(isValidKeyId(commonFilter.getSkilValue())){
		str.append( "SKILLVALUE="+commonFilter.getSkilValue()+";");
	}
	if(isValidKeyId(commonFilter.getKnowValue())){
		str.append( "KNOWVALUE="+commonFilter.getKnowValue()+";");
	}
	str.append(  getComboSelectionId(commonFilter.getIncidenttype(),"ACCIDENTTYPEID"));
	str.append( getComboSelectionId(commonFilter.getIncidentno(),"ACCIDENTNO"));
	str.append( getComboSelectionId(commonFilter.getBatch(),"BATCHID"));
	str.append(getComboSelectionId(commonFilter.getSpoke(),"SPOKEID"));
	str.append(getComboSelectionId(commonFilter.getTopics(),"TOPICID"));
	str.append(  getComboSelectionId(commonFilter.getPgmno(),"PROGRAMNO"));
	str.append(  getComboSelectionId(commonFilter.getPgmbenefit(),"PGMBENIFIT"));
	str.append( getComboSelectionId(commonFilter.getSkillavg(),"SKILLAVG"));
	str.append( getComboSelectionId(commonFilter.getKnowavg(),"KNOWAVG"));
	str.append( getComboSelectionId(commonFilter.getCompavg(),"COMPAVG"));
	str.append( getComboSelectionId(commonFilter.getReportType(),"REPORTTYPE"));
	str.append( getComboSelectionId(commonFilter.getTrainingtype(),"TRAININGTYPE"));
	str.append( getComboSelectionId(commonFilter.getTrainingcategory(),"TRNCATEGORY"));
	str.append( getComboSelectionId(commonFilter.getSkillType(),"SKILLTYPE"));
	str.append( getComboSelectionId(commonFilter.getProgm(),"PROGRAMID"));
	str.append( getComboSelectionId(commonFilter.getEmployee(),"EMPLOYEE"));
	str.append( getComboSelectionId(commonFilter.getDesignation(),"DESGID"));
	str.append(getFieldValue(commonFilter.getBefFromDt(),"BEFFROMDATE"));
	str.append(getFieldValue(commonFilter.getBefToDt(),"BEFTODATE"));	
	str.append(getFieldValue(commonFilter.getAftFromDt(),"AFTFROMDATE"));
	str.append(getFieldValue(commonFilter.getAftToDt(),"AFTTODATE"));
		
	CommonMessage.debugMsg("commonFilter.getBefFromDt()"+commonFilter.getBefFromDt());
	CommonMessage.debugMsg("commonFilter.getBefToDt()"+commonFilter.getBefToDt());
	CommonMessage.debugMsg("commonFilter.getAftFromDt()"+commonFilter.getAftFromDt());
	CommonMessage.debugMsg("commonFilter.getAftToDt()"+commonFilter.getAftToDt());
		
	if (getFieldValue( commonFilter.getPgmWise()).equals("1")){
		
		 if (getFieldValue(commonFilter.getPgmWise()).equals("1")){
	
				str.append("ISPROGRAMWISE=Y;");
			}
		
	}else{
	
		 if (getFieldValue(commonFilter.getEmpWise()).equals("1")){
	 
				str.append("ISPROGRAMWISE=N;");
		 }
	}
	
	return str.toString();
	}



	public static String getSafetyRelatedStr(CommonFilter commonFilter)
	{
	
		StringBuffer str = new StringBuffer();	
		str.append( getCommonRelatedCondStr(commonFilter));
		str.append( getFieldValue(commonFilter.getParamCode(),"PARAMCODE"));
		if( isValidKeyId(commonFilter.getISFORGRAPH()+"") )
			str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");
	
		
		str.append( getComboSelectionId(commonFilter.getAgno(),"TAGNO"));
		CommonMessage.debugMsg("TAG"+getComboSelectionId(commonFilter.getAgno(),"TAGNO"));
		str.append( getComboSelectionId(commonFilter.getIncidenttype(),"INCIDENTTYPE"));
		str.append( getComboSelectionId(commonFilter.getSafetysubtype(),"SAFETYSUBTYPE"));
		str.append(  getComboSelectionId(commonFilter.getIncidentno(),"INCIDENTNO"));
		str.append( getComboSelectionId(commonFilter.getEmployee(),"EMPLOYEE"));
		str.append( getComboSelectionId(commonFilter.getInjuryType(),"INJURYTYPE"));
		str.append( getComboSelectionId(commonFilter.getBodypart(),"BODYPART"));
		CommonMessage.debugMsg("TAG"+getComboSelectionId(commonFilter.getBodypart(),"BODYPART"));
		CommonMessage.debugMsg("str"+str.toString());
		
		str.append( getComboSelectionId(commonFilter.getImprovmnt(),"IMPROVMNTYPE"));
		str.append( getComboSelectionId(commonFilter.getSafetytype(),"SAFTEYTYPE"));
		str.append( getComboSelectionId(commonFilter.getPriority(),"PRIORITY"));
		str.append( getComboSelectionId(commonFilter.getRelatedto(),"RELATEDTO"));
		str.append( getComboSelectionId(commonFilter.getReportType(),"REPORTTYPE"));
		str.append( getComboSelectionId(commonFilter.getWorkarea(),"WORKAREA"));
		str.append( getComboSelectionId(commonFilter.getQtyystatus(),"STATUS"));
		str.append(  getComboSelectionId(commonFilter.getMachineRank(),"MCHMRANK"));
		str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
		str.append(  getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
		str.append( getFieldValue(commonFilter.getTotal(),"TOTAL"));
		str.append( getFieldValue(commonFilter.getSftopl(),"OPL"));
		str.append( getFieldValue(commonFilter.getSftojt(),"OJT"));	
		str.append( getFieldValue(commonFilter.getSftkzn(),"KZN"));	
		CommonMessage.debugMsg("KZN"+commonFilter.getSftkzn());
		str.append( getFieldValue(commonFilter.getSftpokayoke(),"POKAYOKE"));
		str.append( getFieldValue(commonFilter.getSftmin(),"MIN"));
		CommonMessage.debugMsg("MIN******"+commonFilter.getSftmin());
		str.append( getFieldValue(commonFilter.getSftmaj(),"MAJ"));
		CommonMessage.debugMsg("MAJ******"+commonFilter.getSftmaj());
		if (getFieldValue( commonFilter.getInstance()).equals("1")){	
			 
					str.append("INSTANCE=Y;");
				
			
		}else{
		
			 if (getFieldValue(commonFilter.getMdl()).equals("1")){
		 
					str.append("MDL=Y;");
			 }
		}
		CommonMessage.debugMsg("END    str"+str.toString());
		return str.toString();
	}
	
	public static String getAuditRelatedStr(CommonFilter commonFilter)
	{
	CommonMessage.debugMsg("Audit related ....................");
		StringBuffer str = new StringBuffer();	
		str.append( getCommonRelatedCondStr(commonFilter));
		str.append( getFieldValue(commonFilter.getParamCode(),"PARAMCODE"));
		if( isValidKeyId(commonFilter.getISFORGRAPH()+"") )
			str.append( "ISFORGRAPH="+commonFilter.getISFORGRAPH()+";");
	
		str.append(  getComboSelectionId(commonFilter.getMachineRank(),"MCHMRANK"));
		str.append(  getComboSelectionId(commonFilter.getEqpGroup(),"EQPGRPID"));
		str.append(  getComboSelectionId(commonFilter.getCircle(),"CIRCLEID"));
		str.append( getComboSelectionId(commonFilter.getCmbAuditorName(),"AUDITORNAME"));
		CommonMessage.debugMsg("AUDITORNAME"+getComboSelectionId(commonFilter.getCmbAuditorName(),"AUDITORNAME"));
		str.append( getComboSelectionId(commonFilter.getAuditType(),"AUDITTYPE"));
		//str.append( getFieldValue(commonFilter.getAuditToDate(),"AUDITFRMDATE"));
		//str.append(  getFieldValue(commonFilter.getAuditToDate(),"AUDITTODATE"));
		str.append( getComboSelectionId(commonFilter.getAuditStatus(),"AUDITSTATUS"));
		str.append( getComboSelectionId(commonFilter.getCmbAuditorLevel(),"AUDITLEVEL"));
		
		
		CommonMessage.debugMsg("str"+str.toString());
		
			 
		
		CommonMessage.debugMsg("END    str"+str.toString());
		return str.toString();
	}

	public static String getMasterPlanRelated(CommonFilter commonFilter)
	{
		StringBuffer str = new StringBuffer();	
		str.append( getCommonRelatedCondStr(commonFilter));
		str.append( getComboSelectionId(commonFilter.getCmbActivity(),"ACTIVITY"));
		str.append( getComboSelectionId(commonFilter.getCmbCategory(),"CATEGORY"));
		str.append( getComboSelectionId(commonFilter.getCmbSubCategory(),"SUBCATEGORY"));
		str.append( getComboSelectionId(commonFilter.getCmbAssignedto(),"ASSIGNEDTO"));
		str.append( getComboSelectionId(commonFilter.getCmbResponsibility(),"RESPONSIBILITY"));
		str.append( getComboSelectionId(commonFilter.getCmbStatus(),"STATUS"));
		str.append( getFieldValue(commonFilter.getDtestart(),"PLANFROMDATE"));
		str.append( getFieldValue(commonFilter.getDteend(),"PLANTODATE"));
		str.append( getFieldValue(commonFilter.getDteActualStart(),"ACTUALFROMDATE"));
		CommonMessage.debugMsg("actual date::"+commonFilter.getDteActualStart());
		str.append( getFieldValue(commonFilter.getDteEnd(),"ACTUALTODATE"));
		
		CommonMessage.debugMsg("str"+str.toString());
		
		CommonMessage.debugMsg("END    str"+str.toString());
		
		return str.toString();
	}
	public static String makeGridFilterCond(List<GridFilter> condList){
		//CommonMessage.debugMsg("condList:"+condList);
		StringBuffer condSql = new StringBuffer();
		condSql.append("");
		if( condList != null ){
			for(GridFilter gridFilter : condList){
				condSql.append(" AND upper(");
				condSql.append( gridFilter.getField());
				condSql.append( ") like '" + gridFilter.getData().toUpperCase().replaceAll("\\*", "%") + "'" );
			}
		}	
		
		return condSql.toString();
	}

	public static String getActionPlan(CommonFilter commonFilter)
	{
		StringBuffer str = new StringBuffer();	
		str.append( getCommonRelatedCondStr(commonFilter));
		str.append( getComboSelectionId(commonFilter.getRefNo(),"REFNO"));
		str.append( getComboSelectionId(commonFilter.getDetectedBy(),"DETECTEDBY"));
		str.append( getComboSelectionId(commonFilter.getCboElapsedDays(),"ELAPSEDDAYS"));
		
		str.append( getFieldValue(commonFilter.getTxtValue(),"VALUE"));
		str.append( getFieldValue(commonFilter.getTxtnxtValue(),"NEXTVALUE"));
		
		
		CommonMessage.debugMsg("str"+str.toString());
		
		CommonMessage.debugMsg("END    str"+str.toString());
		return str.toString();
	}
	public static String getBreakup(List<CommonFilter> breakUpArray) {
		//CommonFilter commonFilter = null;
		String str = "" ;
		if (breakUpArray!= null && breakUpArray.size() > 0) 
		{
			for(int i=0;i<breakUpArray.size();i++)
			{
				String Freq= breakUpArray.get(i).getFREQ();
				str += "FREQ:"+Freq;
				String Bfrom= breakUpArray.get(i).getBFROM();
				str += "#BFROM:"+Bfrom;
				String Bto= breakUpArray.get(i).getBTO();
				str += "#BTO:"+Bto;
				if (i<(breakUpArray.size()-1)) 
					str += "$";
			}
		}
		 CommonMessage.debugMsg("String--"+str);
		return str;

	}
	
	
	/*******************************<b>Tool</b> Change Related Related*********************************/

	 public static String getToolChangeRelated(CommonFilter commonFilter ){
		 StringBuilder str = new StringBuilder();
			str.append( getCommonRelatedCondStr(commonFilter));
//			str.append( getComboSelectionId(commonFilter.getToolid(),"TOOLID"));
//			str.append( getComboSelectionId(commonFilter.getToolCategory(),"CATEGORY"));
//			str.append( getComboSelectionId(commonFilter.getChangeType(),"CHANGETYPE"));
//			if(getFieldValue(commonFilter.getLifeEarly()).equals("1"))
//				str.append("LIFEEARLY=Y;");
//			if(getFieldValue(commonFilter.getLifeExtended()).equals("1"))
//				str.append("LIFEEXTENDED=Y;");
//			if(getFieldValue(commonFilter.getTrialTool()).equals("1"))
//				str.append("TRIALTOOL=Y;");  // commented by madhan checkQR
			//CommonFunctions.debugMsg("In side the filter Condiional sql  " +commonFilter.getToolid() +"  " + commonFilter.getCategory().toString()+"  " +commonFilter.getChangeType()+" commonFilter.getTrialTool() "+ commonFilter.getTrialTool());
		return str.toString();
		 
	 }
	 
	/*********************************QR CODE RELATED*******************************/
	 
	 public static String getQrCodeRelated(CommonFilter commonFilter ){
		 StringBuilder str = new StringBuilder();
			str.append( getCommonRelatedCondStr(commonFilter));
			str.append( getFieldValue(commonFilter.getFactoryId(),"FACTORYID"));
			str.append( getFieldValue(commonFilter.getSectionId(),"SECTIONID"));
			str.append( getFieldValue(commonFilter.getCellId(),"CELLID"));
			str.append( getFieldValue(commonFilter.getMachineId(),"MACHINEID"));
			str.append( getFieldValue(commonFilter.getWodetailid(),"WORKORDERNO"));

			//CommonFunctions.debugMsg("In side the filter Condiional sql  " +commonFilter.getToolid() +"  " + commonFilter.getCategory().toString()+"  " +commonFilter.getChangeType()+" commonFilter.getTrialTool() "+ commonFilter.getTrialTool());
		return str.toString();
	 }
}