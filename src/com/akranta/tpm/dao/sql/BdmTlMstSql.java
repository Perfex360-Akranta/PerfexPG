package com.akranta.tpm.dao.sql;


import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class BdmTlMstSql {

	public static final String TBL_BDM_TL_MST = "BDM_TL_MST";  

	TableFieldType [] bdmsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, entrydate, shiftid, factoryid, sectionid, cellid, machineid
		, assemblyid, partlocationid, alarmdescription, bdtype, reporteddate
		, receiveddate, wostarttime, woendtime, breaktime, actualworktime
		, downtime, prodaccepdate, bookedphenomena, phenomenadescription
		, bookedcause, finalphenomena, finalcause, bookedtrade, finaltrade
		, problemdescription, isbdlocked, shiftincharge, status, bookedby
		, remarks, bookingtype, bdrelatedto, wno, spareid, priority, woallottedflag
		, wostartflag, woendflag, woprodaccepflag, subassemblyid, repeatedbdflag
		, repeatedbdno, relatedto, mould, elementid, flid, processid
		, isstandby, standbyequipment, breakdowntime, productionstop, immediateaction
		, tempfield3, tempfield4, tempfield5, tempfield6, tempfield7
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBdmsDbFields() {
		return bdmsDbFields;
	}

	public BdmTlMstSql()
	{
		bdmsDbFields = new TableFieldType[ 63 ];
		for(int i = 0;i < 63; i++)
		{	
			bdmsDbFields[ i ] = new TableFieldType();
		}
		bdmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BDMS_KEYID";
		bdmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.entrydate.ordinal() ].fieldName = "BDMS_ENTRYDATE";
		bdmsDbFields[ tableFldConstants.entrydate.ordinal() ].fieldType = 'D';

		bdmsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "BDMS_SHIFTID";
		bdmsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "BDMS_FACTORYID";
		bdmsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "BDMS_SECTIONID";
		bdmsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "BDMS_CELLID";
		bdmsDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "BDMS_MACHINEID";
		bdmsDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "BDMS_ASSEMBLYID";
		bdmsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.partlocationid.ordinal() ].fieldName = "BDMS_PARTLOCATIONID";
		bdmsDbFields[ tableFldConstants.partlocationid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.alarmdescription.ordinal() ].fieldName = "BDMS_ALARMDESCRIPTION";
		bdmsDbFields[ tableFldConstants.alarmdescription.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.bdtype.ordinal() ].fieldName = "BDMS_BDTYPE";
		bdmsDbFields[ tableFldConstants.bdtype.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.reporteddate.ordinal() ].fieldName = "BDMS_REPORTEDDATE";
		bdmsDbFields[ tableFldConstants.reporteddate.ordinal() ].fieldType = 'D';

		bdmsDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldName = "BDMS_RECEIVEDDATE";
		bdmsDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldType = 'D';

		bdmsDbFields[ tableFldConstants.wostarttime.ordinal() ].fieldName = "BDMS_WOSTARTTIME";
		bdmsDbFields[ tableFldConstants.wostarttime.ordinal() ].fieldType = 'D';

		bdmsDbFields[ tableFldConstants.woendtime.ordinal() ].fieldName = "BDMS_WOENDTIME";
		bdmsDbFields[ tableFldConstants.woendtime.ordinal() ].fieldType = 'D';

		bdmsDbFields[ tableFldConstants.breaktime.ordinal() ].fieldName = "BDMS_BREAKTIME";
		bdmsDbFields[ tableFldConstants.breaktime.ordinal() ].fieldType = 'N';

		bdmsDbFields[ tableFldConstants.actualworktime.ordinal() ].fieldName = "BDMS_ACTUALWORKTIME";
		bdmsDbFields[ tableFldConstants.actualworktime.ordinal() ].fieldType = 'N';

		bdmsDbFields[ tableFldConstants.downtime.ordinal() ].fieldName = "BDMS_DOWNTIME";
		bdmsDbFields[ tableFldConstants.downtime.ordinal() ].fieldType = 'N';

		bdmsDbFields[ tableFldConstants.prodaccepdate.ordinal() ].fieldName = "BDMS_PRODACCEPDATE";
		bdmsDbFields[ tableFldConstants.prodaccepdate.ordinal() ].fieldType = 'D';

		bdmsDbFields[ tableFldConstants.bookedphenomena.ordinal() ].fieldName = "BDMS_BOOKEDPHENOMENA";
		bdmsDbFields[ tableFldConstants.bookedphenomena.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.phenomenadescription.ordinal() ].fieldName = "BDMS_PHENOMENADESCRIPTION";
		bdmsDbFields[ tableFldConstants.phenomenadescription.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.bookedcause.ordinal() ].fieldName = "BDMS_BOOKEDCAUSE";
		bdmsDbFields[ tableFldConstants.bookedcause.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldName = "BDMS_FINALPHENOMENA";
		bdmsDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.finalcause.ordinal() ].fieldName = "BDMS_FINALCAUSE";
		bdmsDbFields[ tableFldConstants.finalcause.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.bookedtrade.ordinal() ].fieldName = "BDMS_BOOKEDTRADE";
		bdmsDbFields[ tableFldConstants.bookedtrade.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.finaltrade.ordinal() ].fieldName = "BDMS_FINALTRADE";
		bdmsDbFields[ tableFldConstants.finaltrade.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.problemdescription.ordinal() ].fieldName = "BDMS_PROBLEMDESCRIPTION";
		bdmsDbFields[ tableFldConstants.problemdescription.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.isbdlocked.ordinal() ].fieldName = "BDMS_ISBDLOCKED";
		bdmsDbFields[ tableFldConstants.isbdlocked.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldName = "BDMS_SHIFTINCHARGE";
		bdmsDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.status.ordinal() ].fieldName = "BDMS_STATUS";
		bdmsDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.bookedby.ordinal() ].fieldName = "BDMS_BOOKEDBY";
		bdmsDbFields[ tableFldConstants.bookedby.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "BDMS_REMARKS";
		bdmsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.bookingtype.ordinal() ].fieldName = "BDMS_BOOKINGTYPE";
		bdmsDbFields[ tableFldConstants.bookingtype.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.bdrelatedto.ordinal() ].fieldName = "BDMS_BDRELATEDTO";
		bdmsDbFields[ tableFldConstants.bdrelatedto.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.wno.ordinal() ].fieldName = "BDMS_WNO";
		bdmsDbFields[ tableFldConstants.wno.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.spareid.ordinal() ].fieldName = "BDMS_SPAREID";
		bdmsDbFields[ tableFldConstants.spareid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "BDMS_PRIORITY";
		bdmsDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.woallottedflag.ordinal() ].fieldName = "BDMS_WOALLOTTEDFLAG";
		bdmsDbFields[ tableFldConstants.woallottedflag.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.wostartflag.ordinal() ].fieldName = "BDMS_WOSTARTFLAG";
		bdmsDbFields[ tableFldConstants.wostartflag.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.woendflag.ordinal() ].fieldName = "BDMS_WOENDFLAG";
		bdmsDbFields[ tableFldConstants.woendflag.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.woprodaccepflag.ordinal() ].fieldName = "BDMS_WOPRODACCEPFLAG";
		bdmsDbFields[ tableFldConstants.woprodaccepflag.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldName = "BDMS_SUBASSEMBLYID";
		bdmsDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.repeatedbdflag.ordinal() ].fieldName = "BDMS_REPEATEDBDFLAG";
		bdmsDbFields[ tableFldConstants.repeatedbdflag.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.repeatedbdno.ordinal() ].fieldName = "BDMS_REPEATEDBDNO";
		bdmsDbFields[ tableFldConstants.repeatedbdno.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "BDMS_RELATEDTO";
		bdmsDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.mould.ordinal() ].fieldName = "BDMS_MOULD";
		bdmsDbFields[ tableFldConstants.mould.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "BDMS_ELEMENTID";
		bdmsDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "BDMS_FLID";
		bdmsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "BDMS_PROCESSID";
		bdmsDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.isstandby.ordinal() ].fieldName = "BDMS_ISSTANDBY";
		bdmsDbFields[ tableFldConstants.isstandby.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.standbyequipment.ordinal() ].fieldName = "BDMS_STANDBYEQUIPMENT";
		bdmsDbFields[ tableFldConstants.standbyequipment.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.breakdowntime.ordinal() ].fieldName = "BDMS_BREAKDOWNTIME";
		bdmsDbFields[ tableFldConstants.breakdowntime.ordinal() ].fieldType = 'N';

		bdmsDbFields[ tableFldConstants.productionstop.ordinal() ].fieldName = "BDMS_PRODUCTIONSTOP";
		bdmsDbFields[ tableFldConstants.productionstop.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.immediateaction.ordinal() ].fieldName = "BDMS_IMMEDIATEACTION";
		bdmsDbFields[ tableFldConstants.immediateaction.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "BDMS_TEMPFIELD3";
		bdmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "BDMS_TEMPFIELD4";
		bdmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "BDMS_TEMPFIELD5";
		bdmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "BDMS_TEMPFIELD6";
		bdmsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "BDMS_TEMPFIELD7";
		bdmsDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BDMS_ACTIVE";
		bdmsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bdmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BDMS_CREATEDBY";
		bdmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bdmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BDMS_CREATEDON";
		bdmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bdmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BDMS_MODIFIEDON";
		bdmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}


	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String selectSql()
	{
		return "SELECT * from " + TBL_BDM_TL_MST;
	}
	public static String selectBD()
	{
		return "SELECT * from BDM_tl_mst where bdms_keyid = ?";
	}
	public static String selectBdSql()
	{
		return "BDM_PC_BREAKDOWN.BDM_FN_GetActivitiesForBDA";
	}
	
	public static String getShiftFunction() {	
		
		return "BDM_FN_GetshiftForTime";		
	}
	
	public static String getDownTimeFunction() {	
		
		return "BDM_PC_BREAKDOWN.BDM_FN_FillSplitDtInSplitSpd";		
	}
	public static String getCommTextSql(String bdId) {
		
		String sql = "SELECT WCML_KEYID,WCML_WONUMBER, to_char(WCML_DATE, 'DD-MON-YYYY'),to_char(WCML_DATE,'HH24:MI'),WCML_COMMUNICATIONTEXT,DEPT_NAME,EMPM_NAME,EMPM_KEYID FROM";
		sql += " WOM_TL_COMMUNICATIONLOG,GEN_TL_EMPLOYEEMST,"+TableNames.TBL_GEN_TL_DEPARTMENTMST+" ";
		sql += " WHERE WCML_ENTEREDBY = EMPM_KEYID(+) AND WCML_LEVEL = DEPT_KEYID (+) ";
		sql += " AND WCML_WONUMBER = '"+bdId+"'  ";
		sql += " ORDER BY WCML_KEYID";
		return sql;	
		//AND WCML_WONUMBER = '"+bdId+"'
	}
	public static String getYYSql()
	{
		return "BDM_PC_BREAKDOWN.BDM_FN_GetRecallYYSpd";		
	}
	public static String getRootCauseSql()
	{
		return "SELECT WRCM_KEYID,WRCM_NAME,'','','','' FROM "+TableNames.TBL_BDM_TL_ROOTCAUSEMST;
	}
	public static String updateYYSql(String yy,String bdmsId,String classificationId,String prevMeasure,BdmTlWhywhymst bdmTlWhywhymst)
	{
		StringBuffer rc = new StringBuffer();
		/*rc.append("UPDATE BDM_TL_DTL SET BDAN_WWNO='"+bdmTlWhywhymst.getWwmsKeyid()+"',");
		rc.append("BDAN_WWREQUIRED='Y',BDAN_ROOTCAUSEID='"+bdmTlWhywhymst.getWwmsRootcauseid()+"',");
		rc.append("BDAN_ROOTCAUSE='"+bdmTlWhywhymst.getWwmsRootcause()+"',BDAN_COUNTERMEASUREID='"+bdmTlWhywhymst.getWwmsCountermeasure()+"'");	*/
		rc.append("UPDATE BDM_TL_DTL SET BDAN_WWNO='"+yy+"',BDAN_WWREQUIRED='Y'");
		if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsRootcauseid()))
		{
			rc.append(",BDAN_ROOTCAUSEID='"+ bdmTlWhywhymst.getWwmsRootcauseid()+"'");
		}
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			int actionFlag = bdmTlWhywhymst.getBdmTlWhywhydtl().size() - 1;
	    	BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(actionFlag);
	    	
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAnswer()))
				rc.append(",BDAN_ROOTCAUSE='"+ bdmTlWhywhydtl.getWwdtAnswer()+"'");
			if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAction()))
				rc.append(",BDAN_COUNTERMEASURE='"+ bdmTlWhywhydtl.getWwdtAction()+"'");			
		}
		if(CommonFunctions.isValidKeyId(classificationId))
			rc.append(",BDAN_CLASSIFICATIONID='"+ classificationId+"'");
		if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCountermeasure()))
			rc.append(",BDAN_PREVENTIVEMEASURE='"+ bdmTlWhywhymst.getWwmsCountermeasure()+"'");
		
		rc.append(" WHERE BDAN_BDMS_KEYID='"+bdmsId+"'");
		CommonMessage.debugMsg(rc.toString());
		return rc.toString();	
		//return rc.toString();
	}
	public static String updateYYKaizenSql(String yy,String bdmsId,BdmTlWhywhymst bdmTlWhywhymst)
	{
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE KZN_TL_MST SET KZNM_WWMS_KEYID='"+yy+"'");
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			int actionFlag = bdmTlWhywhymst.getBdmTlWhywhydtl().size() - 1;
	    	BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(actionFlag);
	    	
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAnswer()))
				rc.append(",KZNM_ROOTCAUSE='"+ bdmTlWhywhydtl.getWwdtAnswer()+"'");	    	
		}
		rc.append(" WHERE KZNM_KEYID='"+bdmsId+"'");
		//rc.append("BDAN_ROOTCAUSE='"+bdmTlWhywhymst.getWwmsRootcause()+"',BDAN_COUNTERMEASUREID='"+bdmTlWhywhymst.getWwmsCountermeasure()+"'");	*/
		//CommonMessage.debugMsg("UPDATE KZN_TL_MST SET KZNM_WWMS_KEYID='"+yy+"' WHERE KZNM_KEYID='"+bdmsId+"'");
		//return "UPDATE KZN_TL_MST SET KZNM_WWMS_KEYID='"+yy+"' WHERE KZNM_KEYID='"+bdmsId+"'";	
		return rc.toString();
	}
	public static String updateYYccSql(String yy,String bdmsId,BdmTlWhywhymst bdmTlWhywhymst)
	{
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE QTM_TL_CUSTCOMPLAINTDTL SET CUCD_YYNO='"+yy+"',CUCD_ISYYDONE='Y'");
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			int actionFlag = bdmTlWhywhymst.getBdmTlWhywhydtl().size() - 1;
	    	BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(actionFlag);
	    	
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAnswer()))
				rc.append(",CUCD_ROOTCAUSE='"+ bdmTlWhywhydtl.getWwdtAnswer()+"'");
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAction()))
				rc.append(",CUCD_COUNTERMEASURE='"+ bdmTlWhywhydtl.getWwdtAction()+"'");		
		}
		rc.append(" WHERE CUCD_COMPLAINTID='"+bdmsId+"'");
		return rc.toString();
	}
	public static String updateYYIMTSql(String yy,String bdmsId)
	{
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE QTM_TL_INTREJECTIONDTL SET QIRD_WWMASTERID='"+yy+"'");
		
		rc.append(" WHERE QIRD_KEYID='"+bdmsId+"'");
		return rc.toString();
	}
	public static String updateYYDockSql(String yy,String docId,BdmTlWhywhymst bdmTlWhywhymst)
	{
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE "+TableNames.TBL_QTM_TL_DOCKINSPECTIONMST+" SET DCKI_YYNO='"+yy+"',DCKI_STATUS='C'");
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			int actionFlag = bdmTlWhywhymst.getBdmTlWhywhydtl().size() - 1;
	    	BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(actionFlag);
	    	
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAnswer()))
				rc.append(",DCKI_ROOTCAUSE='"+ bdmTlWhywhydtl.getWwdtAnswer()+"'");
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAction()))
				rc.append(",DCKI_COUNTERMEASURE='"+ bdmTlWhywhydtl.getWwdtAction()+"'");		
		}
		rc.append(" WHERE DCKI_KEYID='"+docId+"'");
		return rc.toString();
	}
	public static String updateYYSafetySql(String yy,String bdmsId,BdmTlWhywhymst bdmTlWhywhymst,String rootcauseFlag)
	{
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE "+TableNames.TBL_SHE_TL_INCIDENTMST+" SET SINC_YYNO='"+yy+"',SINC_WHYWHYFLAG='C'");
		if(CommonFunctions.isValidKeyId(rootcauseFlag))
		{
			if(rootcauseFlag.equals("USC"))
				rc.append(",SINC_ROOTCAUSEDUETO='C'");
			else if(rootcauseFlag.equals("USA"))
				rc.append(",SINC_ROOTCAUSEDUETO='A'");
		}
		if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCountermeasure()))
		{
			rc.append(",SINC_COUNTERMEASURE='"+bdmTlWhywhymst.getWwmsCountermeasure()+"'");
		}
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			int actionFlag = bdmTlWhywhymst.getBdmTlWhywhydtl().size() - 1;
	    	BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(actionFlag);
	    	
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtWhy()))
				rc.append(",SINC_ROOTCAUSE='"+ bdmTlWhywhydtl.getWwdtWhy()+"'");	    			
		}
		rc.append(" WHERE SINC_KEYID='"+bdmsId+"'");
		
		CommonMessage.debugMsg(rc.toString());
		return rc.toString();
	}
	public static String updateActionPlanIncidentSql(String incidentId,String status)
	{
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE "+TableNames.TBL_SHE_TL_ACTIONPLAN+" SET SACP_STATUS='"+status+"'");
		rc.append(" WHERE SACP_SINC_KEYID='"+incidentId+"'");
		return rc.toString();
	}
	public static String updateIncidentSql(String incidentId,String status)
	{
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE "+TableNames.TBL_SHE_TL_INCIDENTMST+" SET SINC_STATUS='"+status+"'");
		rc.append(" WHERE SINC_KEYID='"+incidentId+"'");
		return rc.toString();
	}
	public static String updateYYUPMSql(String yy,String bdmsId,String classificationId,String prevMeasure,BdmTlWhywhymst bdmTlWhywhymst)
	{
		StringBuffer rc = new StringBuffer();
		/*rc.append("UPDATE BDM_TL_DTL SET BDAN_WWNO='"+bdmTlWhywhymst.getWwmsKeyid()+"',");
		rc.append("BDAN_WWREQUIRED='Y',BDAN_ROOTCAUSEID='"+bdmTlWhywhymst.getWwmsRootcauseid()+"',");
		rc.append("BDAN_ROOTCAUSE='"+bdmTlWhywhymst.getWwmsRootcause()+"',BDAN_COUNTERMEASUREID='"+bdmTlWhywhymst.getWwmsCountermeasure()+"'");	*/
		rc.append("UPDATE PLM_TL_UNPLANNEDMAINTDTL SET UPMD_WWNO='"+yy+"',UPMD_WWREQUIRED='Y'");
		if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsRootcauseid()))
		{
			rc.append(",UPMD_ROOTCAUSEID='"+ bdmTlWhywhymst.getWwmsRootcauseid()+"'");
		}
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			int actionFlag = bdmTlWhywhymst.getBdmTlWhywhydtl().size() - 1;
	    	BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(actionFlag);
	    	
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAnswer()))
				rc.append(",UPMD_ROOTCAUSE='"+ bdmTlWhywhydtl.getWwdtAnswer()+"'");
			if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAction()))
				rc.append(",UPMD_COUNTERMEASURE='"+ bdmTlWhywhydtl.getWwdtAction()+"'");			
		}
		if(CommonFunctions.isValidKeyId(classificationId))
			rc.append(",UPMD_CLASSIFICATIONID='"+ classificationId+"'");
		if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCountermeasure()))
			rc.append(",UPMD_PREVENTIVEMEASURE='"+ bdmTlWhywhymst.getWwmsCountermeasure()+"'");
		
		rc.append(" WHERE UPMD_UPMM_KEYID='"+bdmsId+"'");
		CommonMessage.debugMsg(rc.toString());
		return rc.toString();	
		//return rc.toString();
	}
	
	public static String getUpdatePhenSql(String phenId,String causeId,String bdmsId) {
		
		String[] bdmsID = bdmsId.split(">");
		String sql = "UPDATE "+TBL_BDM_TL_MST+" SET BDMS_FINALPHENOMENA = '"+phenId+"',BDMS_FINALCAUSE = '"+causeId+"' WHERE  BDMS_KEYID = '"+bdmsID[0]+"'";
		
		return sql;
		
	}
	
	public static String getPillarClassfcnSql(String pillar)
	{
		String sql = "SELECT DISTINCT BCLM_KEYID FROM BDM_VW_CLASSIFICATION WHERE BCLM_ACTIVE ='Y' AND TPMP_CODE = '"+pillar+"'";
		return sql;
	}
	
	public static String inactiveBDSql(String keyId)
	{
		String sql = "UPDATE "+TBL_BDM_TL_MST+" SET BDMS_ACTIVE = 'N' WHERE BDMS_KEYID = '"+keyId+"'";
		return sql;
	}
	public static String inactiveBDdtlSql(String keyId)
	{
		String sql = "UPDATE "+TableNames.TBL_BDM_TL_DTL+" SET BDAN_ACTIVE = 'N' WHERE BDAN_BDMS_KEYID = '"+keyId+"'";
		return sql;
	}
	public static String inactiveupmSql(String keyId)
	{
		String sql = "UPDATE plm_tl_unplannedmaintmst SET UPMM_ACTIVE = 'N' WHERE UPMM_KEYID = '"+keyId+"'";
		return sql;
	}
	public static String inactiveupmdtlSql(String keyId)
	{
		String sql = "UPDATE plm_tl_unplannedmaintDTL SET UPMD_ACTIVE = 'N' WHERE UPMD_UPMM_KEYID = '"+keyId+"'";
		return sql;
	}
	
	public static String checkPhnLinkExists(String parentId,String OriginalId)
	{
		String sql = "SELECT COUNT(*) FROM "+TableNames.TBL_BDM_TL_PHENCAUSELINK+" WHERE BPCL_PARENTID = '"+parentId+"'";
			   sql += " AND BPCL_ORIGINALID='"+OriginalId+"'";
		return sql;
	}
	public static String selectUPMSql()
	{
		return "SELECT * from PLM_TL_UNPLANNEDMAINTMST where UPMM_KEYID = ?";
	}
	public static String selectUPMDetailSql()
	{
		return "SELECT * from PLM_TL_UNPLANNEDMAINTDTL where UPMD_UPMM_KEYID = ?";
	}
	
	public static String getUpdateWoSql(String allottedDate,String id)
	{
		String pnD = Constants.passNullDate + " 00:00";
		String fnD = Constants.futureNullDate + " 00:00";
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_WOM_TL_MST+" SET WOMS_ALLOTTEDDATE =  to_date( '"+pnD+"','dd-Mon-yyyy hh24:mi'),WOMS_ALLOTTEDTO ='{}',");
		sb.append("WOMS_ALLOTTEDFLAG ='N',WOMS_ALLOTTEDREMARKS = '{}',WOMS_ALLOTTEDSOURCE = '{}',WOMS_ACTIVITYID = '{}',");
		sb.append("WOMS_PRODUCTIONSTARTFLAG ='N',WOMS_PRODUCTIONREMARKS = '{}',WOMS_PRODUCTIONBY = '{}',WOMS_PRODUCTIONSTARTDATE = to_date('"+pnD+"','dd-Mon-yyyy hh24:mi'),");
		sb.append("WOMS_WORKSTARTFLAG ='N',WOMS_WORKSTARTDATE = to_date('"+pnD+"','dd-Mon-yyyy hh24:mi'),WOMS_WORKENDFLAG ='N',WOMS_WORKENDDATE = to_date('"+fnD+"','dd-Mon-yyyy hh24:mi'),");
		sb.append("WOMS_STATUS='B', WOMS_FINALSTATUS='BOOKED',WOMS_FINALACTIVITYTYPE='X',WOMS_ACCEPTEDFLAG ='N',WOMS_ACCEPTEDDATE =  to_date( '"+pnD+"','dd-Mon-yyyy hh24:mi')");
		sb.append(" WHERE woms_keyid = '"+id+"'");	
		CommonMessage.debugMsg( sb.toString());
		return sb.toString();
	}
 public static String getUpdateWomstSql(String YYKeyid,String womsKey){
	 StringBuffer sql= new StringBuffer();
	 sql.append("update wom_tl_workorder_mst set WOMS_YYREFNO='");
	 sql.append(YYKeyid);
	 sql.append("' where woms_keyid='");
	 sql.append(womsKey);
	 sql.append("'");
	 return sql.toString();
 }
	public static String updateYYActionSql(String yy,String bdmsId,BdmTlWhywhymst bdmTlWhywhymst) {
		// TODO Auto-generated method stub
		StringBuffer rc = new StringBuffer();
		rc.append("UPDATE GEN_TL_ACTIONPLANMST SET APLM_MASTERREFID='"+yy+"'");
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			int actionFlag = bdmTlWhywhymst.getBdmTlWhywhydtl().size() - 1;
	    	BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(actionFlag);
	    	
	    	if(CommonFunctions.isValidKeyId(bdmTlWhywhydtl.getWwdtAnswer()))
				rc.append(",APLM_ROOTCAUSE='"+ bdmTlWhywhydtl.getWwdtAnswer()+"'");	    	
		}
		rc.append(" WHERE APLM_KEYID='"+bdmsId+"'");
		//rc.append("BDAN_ROOTCAUSE='"+bdmTlWhywhymst.getWwmsRootcause()+"',BDAN_COUNTERMEASUREID='"+bdmTlWhywhymst.getWwmsCountermeasure()+"'");	*/
		//CommonMessage.debugMsg("UPDATE KZN_TL_MST SET KZNM_WWMS_KEYID='"+yy+"' WHERE KZNM_KEYID='"+bdmsId+"'");
		//return "UPDATE KZN_TL_MST SET KZNM_WWMS_KEYID='"+yy+"' WHERE KZNM_KEYID='"+bdmsId+"'";	
		return rc.toString();
	}

}

