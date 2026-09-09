package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonMessage;
public class BdmTlWwblamstSql {

	public static final String TBL_BDM_TL_WWBLAMST = "BDM_TL_WWBLAMST";  

	TableFieldType [] wwblDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, preparedby, prepareddate, problem, phenomena, mechanism
		, lopcid, lopcempid, lopcyn, active, createdby, createdon
		, modifiedon,wwblinvestigation
	}
	 
 

	public TableFieldType[] getWwblDbFields() {
		return wwblDbFields;
	}

	public BdmTlWwblamstSql()
	{
		wwblDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			wwblDbFields[ i ] = new TableFieldType();
		}
		wwblDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WWBL_KEYID";
		wwblDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "WWBL_FLID";
		wwblDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "WWBL_PREPAREDBY";
		wwblDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldName = "WWBL_PREPAREDDATE";
		wwblDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldType = 'D';

		wwblDbFields[ tableFldConstants.problem.ordinal() ].fieldName = "WWBL_PROBLEM";
		wwblDbFields[ tableFldConstants.problem.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.phenomena.ordinal() ].fieldName = "WWBL_PHENOMENA";
		wwblDbFields[ tableFldConstants.phenomena.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.mechanism.ordinal() ].fieldName = "WWBL_MECHANISM";
		wwblDbFields[ tableFldConstants.mechanism.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.lopcid.ordinal() ].fieldName = "WWBL_LOPCID";
		wwblDbFields[ tableFldConstants.lopcid.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.lopcempid.ordinal() ].fieldName = "WWBL_LOPCEMPID";
		wwblDbFields[ tableFldConstants.lopcempid.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.lopcyn.ordinal() ].fieldName = "WWBL_LOPCYN";
		wwblDbFields[ tableFldConstants.lopcyn.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WWBL_ACTIVE";
		wwblDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wwblDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WWBL_CREATEDBY";
		wwblDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wwblDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WWBL_CREATEDON";
		wwblDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wwblDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WWBL_MODIFIEDON"; 
		wwblDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		wwblDbFields[ tableFldConstants.wwblinvestigation.ordinal() ].fieldName = "WWBL_INVESTIGATION";
		wwblDbFields[ tableFldConstants.wwblinvestigation.ordinal() ].fieldType = 'V';
	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("inside SQl");
		return SqlUtils.getInsertSql(TBL_BDM_TL_WWBLAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_WWBLAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_WWBLAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getSearchNodeSql(String searchNode, String originalId) {
		StringBuffer sb = new StringBuffer(); 
		//sb.append("select parentid from GEN_VW_FUNCLOCN where 1=1 ");
		sb.append("select CHILDPATH from vw_wwbla where 1=1 ");
		if(UIUtils.isValidKeyId(searchNode))
			sb.append(" and NAME = '"+searchNode+"'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and WWBLANO = '"+originalId+"'");
		return sb.toString();
	}
	public String getselectsql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("sql in select");
		String sql="select * from BDM_TL_WWBLAMST  where WWBL_KEYID= ?";
		CommonMessage.debugMsg("inputend");	
		
		return sql;
	}

}


