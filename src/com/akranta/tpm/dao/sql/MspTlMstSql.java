package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MspTlMstSql {

	public static final String TBL_MSP_TL_MST = "MSP_TL_MST";  

	TableFieldType [] mpmsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, indicatorid, factoryid, sectionid, cellid, planstartdate
		, plantilldate, actualstartdate, actualtilldate, planduration
		, actualduration, responsibility, status, completedby, planstartweek
		, plantillweek, actualstartweek, actualtillweek, tempfield10
		, tempfield9, tempfield8, tempfield7, tempfield6, tempfield5
		, tempfield4, tempfield3, tempfield2, tempfield1,elementid, flid, active,createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMpmsDbFields() {
		return mpmsDbFields;
	}

	public MspTlMstSql()
	{
		mpmsDbFields = new TableFieldType[ 34 ];
		for(int i = 0;i < 34; i++)
		{	
			mpmsDbFields[ i ] = new TableFieldType();
		}
		mpmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MPMS_KEYID";
		mpmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldName = "MPMS_INDICATORID";
		mpmsDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "MPMS_FACTORYID";
		mpmsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "MPMS_SECTIONID";
		mpmsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "MPMS_CELLID";
		mpmsDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.planstartdate.ordinal() ].fieldName = "MPMS_PLANSTARTDATE";
		mpmsDbFields[ tableFldConstants.planstartdate.ordinal() ].fieldType = 'D';

		mpmsDbFields[ tableFldConstants.plantilldate.ordinal() ].fieldName = "MPMS_PLANTILLDATE";
		mpmsDbFields[ tableFldConstants.plantilldate.ordinal() ].fieldType = 'D';

		mpmsDbFields[ tableFldConstants.actualstartdate.ordinal() ].fieldName = "MPMS_ACTUALSTARTDATE";
		mpmsDbFields[ tableFldConstants.actualstartdate.ordinal() ].fieldType = 'D';

		mpmsDbFields[ tableFldConstants.actualtilldate.ordinal() ].fieldName = "MPMS_ACTUALTILLDATE";
		mpmsDbFields[ tableFldConstants.actualtilldate.ordinal() ].fieldType = 'D';

		mpmsDbFields[ tableFldConstants.planduration.ordinal() ].fieldName = "MPMS_PLANDURATION";
		mpmsDbFields[ tableFldConstants.planduration.ordinal() ].fieldType = 'N';

		mpmsDbFields[ tableFldConstants.actualduration.ordinal() ].fieldName = "MPMS_ACTUALDURATION";
		mpmsDbFields[ tableFldConstants.actualduration.ordinal() ].fieldType = 'N';

		mpmsDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "MPMS_RESPONSIBILITY";
		mpmsDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.status.ordinal() ].fieldName = "MPMS_STATUS";
		mpmsDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		mpmsDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "MPMS_COMPLETEDBY";
		mpmsDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.planstartweek.ordinal() ].fieldName = "MPMS_PLANSTARTWEEK";
		mpmsDbFields[ tableFldConstants.planstartweek.ordinal() ].fieldType = 'N';

		mpmsDbFields[ tableFldConstants.plantillweek.ordinal() ].fieldName = "MPMS_PLANTILLWEEK";
		mpmsDbFields[ tableFldConstants.plantillweek.ordinal() ].fieldType = 'N';

		mpmsDbFields[ tableFldConstants.actualstartweek.ordinal() ].fieldName = "MPMS_ACTUALSTARTWEEK";
		mpmsDbFields[ tableFldConstants.actualstartweek.ordinal() ].fieldType = 'N';

		mpmsDbFields[ tableFldConstants.actualtillweek.ordinal() ].fieldName = "MPMS_ACTUALTILLWEEK";
		mpmsDbFields[ tableFldConstants.actualtillweek.ordinal() ].fieldType = 'N';

		mpmsDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "MPMS_TEMPFIELD10";
		mpmsDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "MPMS_TEMPFIELD9";
		mpmsDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "MPMS_TEMPFIELD8";
		mpmsDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "MPMS_TEMPFIELD7";
		mpmsDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MPMS_TEMPFIELD6";
		mpmsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MPMS_TEMPFIELD5";
		mpmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MPMS_TEMPFIELD4";
		mpmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MPMS_TEMPFIELD3";
		mpmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MPMS_TEMPFIELD2";
		mpmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MPMS_TEMPFIELD1";
		mpmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "MPMS_ELEMENTID";
		mpmsDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MPMS_FLID";
		mpmsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		mpmsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MPMS_ACTIVE";
		mpmsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mpmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPMS_CREATEDBY";
		mpmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mpmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPMS_CREATEDON";
		mpmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mpmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPMS_MODIFIEDON";
		mpmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("getInsertMstSql : "+SqlUtils.getInsertSql(TBL_MSP_TL_MST, fieldTypeArr, dataArray));
		return SqlUtils.getInsertSql(TBL_MSP_TL_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MSP_TL_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getUpdateMstSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "Update "+TBL_MSP_TL_MST;
		sql += " SET " + fieldTypeArr[tableFldConstants.actualstartdate.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.actualstartdate.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.actualtilldate.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.actualtilldate.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.actualduration.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.actualduration.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.status.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.status.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.completedby.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.completedby.ordinal() ] + "'";
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getUpdatePlanMstSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "Update "+TBL_MSP_TL_MST;
		sql += " SET " + fieldTypeArr[tableFldConstants.planstartdate.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.planstartdate.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.plantilldate.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.plantilldate.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.planduration.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.planduration.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.actualstartdate.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.actualstartdate.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.actualtilldate.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.actualtilldate.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.actualduration.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.actualduration.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.status.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.status.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.responsibility.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.responsibility.ordinal() ] + "'";
		sql += " , " + fieldTypeArr[tableFldConstants.completedby.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.completedby.ordinal() ] + "'";
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MSP_TL_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getTotalMilestones(String indicatorId,String cellId,String fromDate)
	{
		if(UIUtils.isValidKeyId(indicatorId) && UIUtils.isValidKeyId(cellId)&& UIUtils.isValidKeyId(fromDate))
		{
			StringBuffer s = new StringBuffer();
			s.append("Select count (*) from(SELECT MSPD_KEYID,MSPD_MILESTONE,MSPD_TARGETDATE,'',MSPD_COMPLETEDATE,MSPD_COMPLETEDBY,");
			s.append("MSPD_STATUS,MSPD_REMARKS,'','' FROM "+MspTlDtlSql.TBL_MSP_TL_DTL+" WHERE ");
			//s.append("MSPD_INDICATORID = '"+indicatorId+"' AND MSPD_CELLID = '"+cellId+"')");
			s.append("MSPD_MPMS_KEYID IN (SELECT MPMS_KEYID FROM "+MspTlMstSql.TBL_MSP_TL_MST);
			s.append(" WHERE MPMS_INDICATORID = '"+indicatorId+"' AND MPMS_CELLID = '"+cellId+"'");
			s.append(" AND '"+fromDate+"' BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE))");
			return s.toString();
		}
		return null;
	}
	public static String getAllMilestonesSql(String indicatorId,String cellId,String mode)
	{
		if(UIUtils.isValidKeyId(indicatorId) && UIUtils.isValidKeyId(cellId))
		{
			StringBuffer s = new StringBuffer();
			s.append("SELECT MSPD_KEYID,MSPD_MILESTONE,REPLACE(TO_CHAR(MSPD_TARGETDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MSPD_TARGETDATE,'' AS TICK,'' AS REVISEDTARGET,");
			if(CommonFunctions.isValidKeyId(mode))
			{
				if(mode.equalsIgnoreCase("ACTUAL"))
					s.append("REPLACE(TO_CHAR(MSPD_COMPLETEDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MSPD_COMPLETEDATE,");
			}
			s.append("REPLACE(MSPD_RESPONSIBILITY,'{}') AS MSPD_RESPONSIBILITY,");
			if(CommonFunctions.isValidKeyId(mode))
			{
				if(mode.equalsIgnoreCase("ACTUAL"))
					s.append("REPLACE(MSPD_COMPLETEDBY,'{}') AS MSPD_COMPLETEDBY,");
			}
			s.append("MSPD_STATUS,REPLACE(MSPD_REMARKS,'{}') AS MSPD_REMARKS,'' AS DELBUTTON, '' AS HISTBUTTON FROM "+MspTlDtlSql.TBL_MSP_TL_DTL+" WHERE ");
			s.append("MSPD_INDICATORID = '"+indicatorId+"' AND MSPD_CELLID = '"+cellId+"'");
			CommonMessage.debugMsg("getAllMilestonesSql : "+s.toString());
			return s.toString();
		}
		return null;
	}
	public static String selectMilestonesSql(String indicatorId,String cellId,String mode,String fromDate)
	{
		String sql="";
		if(UIUtils.isValidKeyId(indicatorId) && UIUtils.isValidKeyId(cellId)&& UIUtils.isValidKeyId(fromDate))
		{
			
			if(CommonFunctions.isValidKeyId(mode))
			{
				if(mode.equalsIgnoreCase("ACTUAL"))
					sql = getMilestonesActualSql(indicatorId, cellId, fromDate);
				else
					sql = getMilestonesPlanSql(indicatorId, cellId, fromDate);				
			}
			else
				sql = getMilestonesPlanSql(indicatorId, cellId, fromDate);
		/*	s.append("SELECT MSPD_KEYID,MSPD_MILESTONE,REPLACE(TO_CHAR(MSPD_TARGETDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MSPD_TARGETDATE,'' AS TICK,'' AS REVISEDTARGET,");
			if(CommonFunctions.isValidKeyId(mode))
			{
				if(mode.equalsIgnoreCase("ACTUAL"))
					s.append("REPLACE(TO_CHAR(MSPD_COMPLETEDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MSPD_COMPLETEDATE,");
			}
			s.append("REPLACE(MSPD_RESPONSIBILITY,'{}') AS MSPD_RESPONSIBILITY,");
			if(CommonFunctions.isValidKeyId(mode))
			{
				if(mode.equalsIgnoreCase("ACTUAL"))
					s.append("REPLACE(MSPD_COMPLETEDBY,'{}') AS MSPD_COMPLETEDBY,");
			}
			s.append("MSPD_STATUS,REPLACE(MSPD_REMARKS,'{}') AS MSPD_REMARKS,'' AS DELBUTTON, '' AS HISTBUTTON FROM "+MspTlDtlSql.TBL_MSP_TL_DTL+" WHERE ");
			//s.append("MSPD_INDICATORID = '"+indicatorId+"' AND MSPD_CELLID = '"+cellId+"'");
			s.append("MSPD_MPMS_KEYID IN (SELECT MPMS_KEYID FROM "+MspTlMstSql.TBL_MSP_TL_MST);
			s.append(" WHERE MPMS_INDICATORID = '"+indicatorId+"' AND MPMS_CELLID = '"+cellId+"'");
			if(CommonFunctions.isValidKeyId(mode))
			{
				if(mode.equalsIgnoreCase("ACTUAL"))
					s.append(" AND '"+fromDate+"' BETWEEN MPMS_ACTUALSTARTDATE AND MPMS_ACTUALTILLDATE)");
				else
					s.append(" AND '"+fromDate+"' BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE)");
			}
			else
				s.append(" AND '"+fromDate+"' BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE)");*/
			CommonMessage.debugMsg("selectMilestonesSql : "+sql);
			return sql;
		}
		return null;
	}
	public static String getMilestonesPlanSql(String indicatorId,String cellId,String fromDate)
	{
		//AND MPMS_CELLID = '"+cellId+"'
		
		StringBuffer s = new StringBuffer();
		CommonMessage.debugMsg(" Inside :: indicatorId :: ");
		CommonMessage.debugMsg(" Inside :: indicatorId :: "+indicatorId);
		s.append("SELECT MSPD_FLID,MSPD_KEYID,MSPD_MILESTONE,REPLACE(TO_CHAR(MSPD_TARGETDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MSPD_TARGETDATE,'' AS TICK,'' AS REVISEDTARGET,");
		s.append("REPLACE(MSPD_ASSIGNEDTO,'{}') AS MSPD_ASSIGNEDTO,");
		s.append("MSPD_STATUS,REPLACE(MSPD_REMARKS,'{}') AS MSPD_REMARKS,MSPD_STATUS,'' AS DELBUTTON, '' AS HISTBUTTON FROM "+MspTlDtlSql.TBL_MSP_TL_DTL+" WHERE ");
		s.append("MSPD_MPMS_KEYID IN (SELECT MPMS_KEYID FROM "+MspTlMstSql.TBL_MSP_TL_MST);
		s.append(" WHERE MPMS_INDICATORID = '"+indicatorId+"' ");
		s.append(" AND '"+fromDate+"' BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE)");
		CommonMessage.debugMsg(" CHECKING INSIDE MILESTONE 12 :: "+s.toString());
		CommonMessage.debugMsg(" CHECKING INSIDE MILESTONE 34 :: "+s);
		return s.toString();
	}
	
	public static String getMilestonesActualSql(String indicatorId,String cellId,String fromDate)
	{
		//AND MPMS_CELLID = '"+cellId+"'
		
		StringBuffer s = new StringBuffer();
		CommonMessage.debugMsg(" Inside :: indicatorId :: ");
		CommonMessage.debugMsg(" Inside :: indicatorId :: "+indicatorId);
		s.append("SELECT MSPD_FLID,MSPD_KEYID,MSPD_MILESTONE,MSPD_ASSIGNEDTO,REPLACE(TO_CHAR(MSPD_TARGETDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MSPD_TARGETDATE,");
		s.append("MSPD_STATUS,REPLACE(TO_CHAR(MSPD_COMPLETEDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MSPD_COMPLETEDATE,");
		s.append("REPLACE(MSPD_COMPLETEDBY,'{}') AS MSPD_COMPLETEDBY,");
		s.append("REPLACE(MSPD_REMARKS,'{}') AS MSPD_REMARKS,MSPD_STATUS,'' AS DELBUTTON, '' AS HISTBUTTON FROM "+MspTlDtlSql.TBL_MSP_TL_DTL+" WHERE ");
		s.append("MSPD_MPMS_KEYID IN (SELECT MPMS_KEYID FROM "+MspTlMstSql.TBL_MSP_TL_MST);
		s.append(" WHERE MPMS_INDICATORID = '"+indicatorId+"' ");
		s.append(" AND '"+fromDate+"' BETWEEN MPMS_ACTUALSTARTDATE AND MPMS_ACTUALTILLDATE)");
		CommonMessage.debugMsg(" CHECKING INSIDE MILESTONE 12 :: "+s.toString());
		CommonMessage.debugMsg(" CHECKING INSIDE MILESTONE 34 :: "+s);
		return s.toString();
	}
	public static String selectMstIDSql(String indicatorId,String cellId,String fromDate,String flag)
	{
		String sql = "SELECT MPMS_KEYID FROM "+TBL_MSP_TL_MST+" WHERE MPMS_INDICATORID ='"+indicatorId+"'";
			   sql +=" AND MPMS_CELLID = '"+cellId+"' AND '"+fromDate+"'";
			   if(UIUtils.isValidKeyId(flag))
			   {
				   if(flag.equalsIgnoreCase("ACTUAL"))
						   sql +="  BETWEEN MPMS_ACTUALSTARTDATE AND MPMS_ACTUALTILLDATE";
				   else
						 sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   }
			   else
				   sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   CommonMessage.debugMsg(sql);
		return sql;
	}
	public static String selectMstSql(String indicatorId,String cellId,String fromDate,String toDate,String flag)
	{
		
	/*String sql=	"SELECT CAT, SUBCATEGORY, ACT, PLANACTUAL, INDICATORID, LEVL, SORTNO FROM"+
     "  (SELECT L3.MSID_NAME AS CAT,L2.MSID_NAME AS SUBCATEGORY,L1.MSID_NAME AS ACT, L1.MSID_KEYID AS INDICATORID, " +
    " L1.MSID_LEVEL LEVL, L1.MSID_SORTNO SORTNO"+ 
    "  FROM MSP_TL_INDICATORS_DTL L1, MSP_TL_INDICATORS_DTL L2, MSP_TL_INDICATORS_DTL L3, MSP_TL_INDICATORS_MST"+
    "  WHERE L1.MSID_PARENTID = L2.MSID_KEYID AND L2.MSID_PARENTID = L3.MSID_KEYID AND L1.MSID_LEVEL = 3 AND L1.MSID_MSPI_KEYID=MSPI_KEYID ),"+
    " and L1.MSID_KEYID='"+indicatorId+"'"+  
    "  (SELECT 'PLAN' AS PLANACTUAL FROM DUAL UNION SELECT 'ACTUAL' AS PLANACTUAL FROM DUAL)";*/
		
		String sql = "SELECT * FROM "+TBL_MSP_TL_MST+" WHERE MPMS_INDICATORID ='"+indicatorId+"'";
			   sql +=" AND MPMS_CELLID = '"+cellId+"' AND ('"+fromDate+"'";
			   if(UIUtils.isValidKeyId(flag))
			   {
				   if(flag.equalsIgnoreCase("ACTUAL"))
						   sql +="  BETWEEN MPMS_ACTUALSTARTDATE AND MPMS_ACTUALTILLDATE";
				   else
						 sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   }
			   else
				   sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   
			   sql +=" OR '"+toDate+"'";
			   if(UIUtils.isValidKeyId(flag))
			   {
				   if(flag.equalsIgnoreCase("ACTUAL"))
						   sql +="  BETWEEN MPMS_ACTUALSTARTDATE AND MPMS_ACTUALTILLDATE)";
				   else
						 sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE)";
			   }
			   else
				   sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE)";
		  
		   CommonMessage.debugMsg("selectMstSql "+sql);
		return sql;
	}
	public static String selectMstCountSql(String indicatorId,String cellId,String fromDate,String toDate,String flag)
	{
		String sql = "SELECT Count(*) FROM "+TBL_MSP_TL_MST+" WHERE MPMS_INDICATORID ='"+indicatorId+"'";
			   sql +=" AND MPMS_CELLID = '"+cellId+"' AND ('"+fromDate+"'";
			   if(UIUtils.isValidKeyId(flag))
			   {
				   if(flag.equalsIgnoreCase("ACTUAL"))
						   sql +="  BETWEEN MPMS_ACTUALSTARTDATE AND MPMS_ACTUALTILLDATE";
				   else
						 sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   }
			   else
				   sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			 
				   sql +=" OR '"+toDate+"'";
				   if(UIUtils.isValidKeyId(flag))
				   {
					   if(flag.equalsIgnoreCase("ACTUAL"))
							   sql +="  BETWEEN MPMS_ACTUALSTARTDATE AND MPMS_ACTUALTILLDATE)";
					   else
							 sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE)";
				   }
				   else
					   sql +="  BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE)";
			  
			   CommonMessage.debugMsg("selectMstCountSql "+sql);
		return sql;
	}
	public static String checkOverlapSql(String indicatorId,String cellId,String fromDate,String toDate)
	{
		String sql = "SELECT count(*) FROM "+TBL_MSP_TL_MST+" WHERE MPMS_INDICATORID ='"+indicatorId+"'";
			   sql +=" AND MPMS_CELLID = '"+cellId+"'";
			   sql +=" AND (TO_DATE('"+fromDate+"') BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   sql +=" OR TO_DATE('"+toDate+"') BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   sql +=" OR MPMS_PLANSTARTDATE BETWEEN TO_DATE('"+fromDate+"') AND TO_DATE('"+toDate+"')";
			   sql +=" OR MPMS_PLANTILLDATE BETWEEN TO_DATE('"+fromDate+"') AND TO_DATE('"+toDate+"'))";
			   CommonMessage.debugMsg("overlap SQl "+sql);
	   return sql;
	}
	public static String getMstSql(String indicatorId,String cellId,String fromDate,String toDate)
	{
		String sql = "SELECT MPMS_KEYID FROM "+TBL_MSP_TL_MST+" WHERE MPMS_INDICATORID ='"+indicatorId+"'";
			   sql +=" AND MPMS_CELLID = '"+cellId+"'";
			   sql +=" AND (TO_DATE('"+fromDate+"') BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   sql +=" OR TO_DATE('"+toDate+"') BETWEEN MPMS_PLANSTARTDATE AND MPMS_PLANTILLDATE";
			   sql +=" OR MPMS_PLANSTARTDATE BETWEEN TO_DATE('"+fromDate+"') AND TO_DATE('"+toDate+"')";
			   sql +=" OR MPMS_PLANTILLDATE BETWEEN TO_DATE('"+fromDate+"') AND TO_DATE('"+toDate+"'))";
			   CommonMessage.debugMsg("overlap SQl "+sql);
	   return sql;
	}
	public static String selectColorsSql()
	{
		String sql = "Select CNFM_CODE,Cnfm_Settingvalue From "+TableNames.TBL_ADM_TL_CONFIGURATIONMST;
			   sql += " Where Cnfm_Code In('MSTPNAPLAN','MSTPNACOMP','MSTPNAWIP','MSTPNAPEND')";
		return sql;
	}

	public static String getStatusSql(String mpmsKeyid) {
		      String sql=" update MSP_TL_MST SET MPMS_STATUS=(SELECT DECODE(COUNT(*), " +
		      " SUM(DECODE(MSPD_STATUS,'W',1,0)),'W', "+
		      " SUM(DECODE(MSPD_STATUS,'C',1,0)),'C', "+
		      " SUM(DECODE(MSPD_STATUS,'P',1,0)),'P', "+
		      "  SUM(DECODE(MSPD_STATUS,'C',1,'W',1,0)),'C', "+ 
		      " SUM(DECODE(MSPD_STATUS,'P',1,'P',1,'W',1,0)),'W', "+
		      " SUM(DECODE(MSPD_STATUS,'W',1,'C',1,0)),'P','C') "+
              "  AS STATUS FROM MSP_TL_DTL WHERE MSPD_MPMS_KEYID ='"+mpmsKeyid+"')  where mpms_keyid='"+mpmsKeyid+"' ";
		      return sql;
		
		      /* " SUM(DECODE(MSPD_STATUS,'P',1,'W',1,0)),'P', "+ 
		      " SUM(DECODE(MSPD_STATUS,'P',1,'W',1,'W',1,0)),'P', "+*/
		
		/*String sql=" update MSP_TL_MST SET MPMS_STATUS=(SELECT DECODE(COUNT(*),SUM(DECODE(MSPD_STATUS,'C',1,0)),'C',DECODE(COUNT(*)"+
				" ,SUM(DECODE(MSPD_STATUS,'P',1,0)),'P','W')) AS STATUS FROM MSP_TL_DTL WHERE MSPD_MPMS_KEYID ='"+mpmsKeyid+"')"+
                  " WHERE MPMS_KEYID ='"+mpmsKeyid+"' ";
		return sql;*/
	}

	public static String getSelectPlandate(String nodeId,String cellId) {
		
	    String sql = "SELECT * from " + TBL_MSP_TL_MST+" ";
		
		sql += " where  MPMS_INDICATORID ='" +nodeId+"'  and MPMS_CELLID  = '"+cellId+"'";
		return sql;
	}
}
	
	//String sql = "SELECT * from " + TBL_MSP_TL_MST+","+MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST ;
	//sql += " where MSPI_KEYID=MSID_MSPI_KEYID(+) AND MSID_KEYID ='" +nodeId+"' AND MSPI_CELLID = '"+cellId+"'";
	
	



