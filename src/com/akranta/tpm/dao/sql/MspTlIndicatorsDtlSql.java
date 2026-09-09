package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.MspTlIndicatorsSql.tableFldConstants;
import com.akranta.tpm.model.MspTlIndicators;
import com.akranta.tpm.model.MspTlIndicatorsDtl;
import com.akranta.tpm.model.MspTlIndicatorsMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MspTlIndicatorsDtlSql {

	public static final String TBL_MSP_TL_INDICATORS_DTL = "MSP_TL_INDICATORS_DTL";  
	public static final String MasterPlanId = "MP001";
	TableFieldType [] msidDbFields = null;

	public enum   tableFldConstants
	{
		keyid, mspi_keyid, name, code, parentid, level, sortno, remarks
		,  tempfield, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMsidDbFields() {
		return msidDbFields;
	}

	public MspTlIndicatorsDtlSql()
	{
		msidDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			msidDbFields[ i ] = new TableFieldType();
		}
		msidDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MSID_KEYID";
		msidDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		msidDbFields[ tableFldConstants.mspi_keyid.ordinal() ].fieldName = "MSID_MSPI_KEYID";
		msidDbFields[ tableFldConstants.mspi_keyid.ordinal() ].fieldType = 'V';

		msidDbFields[ tableFldConstants.name.ordinal() ].fieldName = "MSID_NAME";
		msidDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		msidDbFields[ tableFldConstants.code.ordinal() ].fieldName = "MSID_CODE";
		msidDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		msidDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "MSID_PARENTID";
		msidDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		msidDbFields[ tableFldConstants.level.ordinal() ].fieldName = "MSID_LEVEL";
		msidDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'N';

		msidDbFields[ tableFldConstants.sortno.ordinal() ].fieldName = "MSID_SORTNO";
		msidDbFields[ tableFldConstants.sortno.ordinal() ].fieldType = 'V';

		msidDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MSID_REMARKS";
		msidDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';	

		msidDbFields[ tableFldConstants.tempfield.ordinal() ].fieldName = "MSID_TEMPFIELD";
		msidDbFields[ tableFldConstants.tempfield.ordinal() ].fieldType = 'C';

		msidDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MSID_TEMPFIELD2";
		msidDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		msidDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MSID_TEMPFIELD3";
		msidDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		msidDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MSID_ACTIVE";
		msidDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		msidDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MSID_CREATEDBY";
		msidDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		msidDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MSID_CREATEDON";
		msidDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		msidDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MSID_MODIFIEDON";
		msidDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MSP_TL_INDICATORS_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MSP_TL_INDICATORS_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getUpdateParentSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "UPDATE " + TBL_MSP_TL_INDICATORS_DTL;
		sql += " SET " + fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "'";
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}	
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MSP_TL_INDICATORS_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getDeleteChildSql(TableFieldType [] fieldTypeArr, Object [] dataArray,String cellid)
	{
		String sql = "DELETE from " + TBL_MSP_TL_INDICATORS_DTL;
		
		sql += " where " + fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";		
		
		return sql;
	}
	public static String getSelectSql(String nodeId,String Flid)
	{
		String sql = "SELECT * from " + TBL_MSP_TL_INDICATORS_DTL+","+MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST ;
		sql += " where MSPI_KEYID=MSID_MSPI_KEYID(+) AND MSID_KEYID ='" +nodeId+"' AND MSPI_FLID = '"+Flid+"'";
		CommonMessage.debugMsg("................................. "+sql);
		return sql;
	}
	public static String getSelectMstSql(String nodeId,String Flid)
	{
		String sql = "SELECT * from " + MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST+","+TBL_MSP_TL_INDICATORS_DTL ;
		sql += " where MSPI_KEYID=MSID_MSPI_KEYID(+) AND MSID_KEYID ='" +nodeId+"' AND MSPI_FLID = '"+Flid+"'";
		CommonMessage.debugMsg("................................. "+sql);
		return sql;
	}
	public static String  getIndicatorsCountSql()
	{
		String sql = "SELECT count(*) from " + TBL_MSP_TL_INDICATORS_DTL +" WHERE MSID_LEVEL=3";
		return sql;
	}
	public static String  getIndicatorsCountNewSql(String pillar)
	{
		String sql = "SELECT count(*) from " + MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST +" WHERE MSPi_pillar='"+pillar+"'";
		return sql;
	}
	public static String getActivitiesForSubcategorySql(MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst)
	{
		String sql = "SELECT MSID_KEYID,MSID_NAME,MSID_CODE,MSID_PARENTID,MSID_LEVEL,MSPI_PILLAR,MSID_remarks";
		 	    sql += " FROM "+TBL_MSP_TL_INDICATORS_DTL+","+MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST+"	WHERE 1=1";
		 	    sql +="  AND UPPER(MSPI_TITLE) =UPPER('"+mspTlIndicatorsMst.getMspiTitle()+"')";
		 	   
		 	   sql += " and MSID_MSPI_KEYID=MSPI_KEYID(+) AND MSID_ACTIVE  = 'Y'";
		 	   if(CommonFunctions.isValidKeyId(mspTlIndicatorsDtl.getMsidParentid()))
		 	   {
		 		   if(mspTlIndicatorsDtl.getMsidParentid().equals(MasterPlanId))
		 			  sql +=" AND MSID_KEYID   = MSID_PARENTID";
		 		   else
		 		   {
		 			  sql +=" AND MSID_KEYID<>MSID_PARENTID";
		 			  sql +=" AND MSID_PARENTID='"+mspTlIndicatorsDtl.getMsidParentid()+"'";
		 		   }
		 	   }
		 	   if(CommonFunctions.isValidKeyId(mspTlIndicatorsMst.getMspiPillar()))
			 	 sql +=" AND MSPI_PILLAR='"+mspTlIndicatorsMst.getMspiPillar()+"'";
		 	   if(CommonFunctions.isValidKeyId(mspTlIndicatorsMst.getMspiCellid()))
		 	     sql +=" AND Mspi_Cellid='"+mspTlIndicatorsMst.getMspiCellid()+"'";
		 	   sql +=" ORDER BY MSPI_KEYID"; 
		return sql;
	}
	public static String getSearchIndicatorSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select MSID_PARENTID from "+TBL_MSP_TL_INDICATORS_DTL+" where MSID_NAME ='"+searchNode+"'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and MSID_KEYID = '"+originalId+"'");
		return sb.toString();
	}
	public static String getChildCountSql(String nodeId,String cellId)
	{
		String sql = "SELECT count(*) from " + TBL_MSP_TL_INDICATORS_DTL;
		sql += " where MSID_parentID ='" +nodeId+"'";
		return sql;
	}

	public static String getdatapopup(String indicatorId,String flag) {
		String sql=	"SELECT CAT, SUBCATEGORY, ACT, PLANACTUAL, INDICATORID, LEVL, SORTNO FROM"+
	     "  (SELECT L3.MSID_NAME AS CAT,L2.MSID_NAME AS SUBCATEGORY,L1.MSID_NAME AS ACT, L1.MSID_KEYID AS INDICATORID, " +
	    " L1.MSID_LEVEL LEVL, L1.MSID_SORTNO SORTNO"+ 
	    "  FROM MSP_TL_INDICATORS_DTL L1, MSP_TL_INDICATORS_DTL L2, MSP_TL_INDICATORS_DTL L3, MSP_TL_INDICATORS_MST"+
	    "  WHERE L1.MSID_PARENTID = L2.MSID_KEYID AND L2.MSID_PARENTID = L3.MSID_KEYID AND L1.MSID_LEVEL = 3 AND L1.MSID_MSPI_KEYID=MSPI_KEYID "+
	    "  and L1.MSID_KEYID='"+indicatorId+"'),"+  
	    "  (SELECT 'PLAN' AS PLANACTUAL FROM DUAL) " ;
		  if(UIUtils.isValidKeyId(flag))
		   {
			   if(flag.equalsIgnoreCase("ACTUAL"))
			sql= " SELECT cat, subcategory, act, planactual "+
			" FROM (SELECT l3.msid_name AS cat,l2.msid_name AS subcategory,l1.msid_name AS act"+
		              " FROM msp_tl_indicators_dtl l1, msp_tl_indicators_dtl l2,msp_tl_indicators_dtl l3,"+
		              " msp_tl_indicators_mst"+
		             " WHERE l1.msid_parentid = l2.msid_keyid"+
		           " AND l2.msid_parentid = l3.msid_keyid"+
		           " AND l1.msid_level = 3"+
		          "  AND l1.msid_mspi_keyid = mspi_keyid ),"+
		           "  (SELECT 'ACTUAL' AS PLANACTUAL FROM DUAL) " ;
		
	}
		return sql;
	}
	public static String getSelectmasterSql() {
		
		
		 String sql=" select * from " + TBL_MSP_TL_INDICATORS_DTL + " where MSID_MSPI_KEYID= ?";
			
			// TODO Auto-generated method stub
			return sql;
		
	}

	
}

