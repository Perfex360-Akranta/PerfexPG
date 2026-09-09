package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlIndicators;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MspTlIndicatorsSql {

	public static final String TBL_MSP_TL_INDICATORS = "MSP_TL_INDICATORS";
	public static final String MasterPlanId = "MP001";

	TableFieldType [] mspiDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, code, parentid, factoryid, sectionid, cellid, level
		, sortno, remarks,pillar,title,tempfield,tempfield2,tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMspiDbFields() {
		return mspiDbFields;
	}

	public MspTlIndicatorsSql()
	{
		mspiDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			mspiDbFields[ i ] = new TableFieldType();
		}
		mspiDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MSPI_KEYID";
		mspiDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.name.ordinal() ].fieldName = "MSPI_NAME";
		mspiDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.code.ordinal() ].fieldName = "MSPI_CODE";
		mspiDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "MSPI_PARENTID";
		mspiDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "MSPI_FACTORYID";
		mspiDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "MSPI_SECTIONID";
		mspiDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "MSPI_CELLID";
		mspiDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.level.ordinal() ].fieldName = "MSPI_LEVEL";
		mspiDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'N';

		mspiDbFields[ tableFldConstants.sortno.ordinal() ].fieldName = "MSPI_SORTNO";
		mspiDbFields[ tableFldConstants.sortno.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MSPI_REMARKS";
		mspiDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.pillar.ordinal() ].fieldName = "MSPI_PILLAR";
		mspiDbFields[ tableFldConstants.pillar.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.title.ordinal() ].fieldName = "MSPI_TITLE";
		mspiDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.tempfield.ordinal() ].fieldName = "MSPI_TEMPFIELD";
		mspiDbFields[ tableFldConstants.tempfield.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MSPI_TEMPFIELD2";
		mspiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MSPI_TEMPFIELD3";
		mspiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MSPI_ACTIVE";
		mspiDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mspiDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MSPI_CREATEDBY";
		mspiDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MSPI_CREATEDON";
		mspiDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mspiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MSPI_MODIFIEDON";
		mspiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MSP_TL_INDICATORS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MSP_TL_INDICATORS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getUpdateParentSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "UPDATE " + TBL_MSP_TL_INDICATORS;
		sql += " SET " + fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "'";
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}	
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MSP_TL_INDICATORS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getDeleteChildSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MSP_TL_INDICATORS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.cellid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.cellid.ordinal()] + "'";
		return sql;
	}
	public static String getActivitiesForSubcategorySql(MspTlIndicators mspTlIndicators)
	{
		String sql = "SELECT MSPI_KEYID,MSPI_NAME,MSPI_CODE,MSPI_PARENTID,MSPI_LEVEL,MSPI_PILLAR,MSPI_remarks";
		 	   sql += " FROM MSP_TL_INDICATORS	WHERE 1=1 AND MSPI_ACTIVE  = 'Y'";// AND MSPI_KEYID  <>MSPI_PARENTID";
		 	   if(CommonFunctions.isValidKeyId(mspTlIndicators.getMspiParentid()))
		 	   {
		 		   if(mspTlIndicators.getMspiParentid().equals("MP001"))
		 			  sql +=" AND MSPI_KEYID   = MSPI_PARENTID";
		 		   else
		 		   {
		 			  sql +=" AND MSPI_KEYID<>MSPI_PARENTID";
		 			  sql +=" AND MSPI_PARENTID='"+mspTlIndicators.getMspiParentid()+"'";
		 		   }
		 	   }
		 	   if(CommonFunctions.isValidKeyId(mspTlIndicators.getMspiPillar()))
			 	 sql +=" AND MSPI_PILLAR='"+mspTlIndicators.getMspiPillar()+"'";
		 	   if(CommonFunctions.isValidKeyId(mspTlIndicators.getMspiCellid()))
		 	     sql +=" AND Mspi_Cellid='"+mspTlIndicators.getMspiCellid()+"'";
		 	   sql +=" ORDER BY MSPI_KEYID"; 
		return sql;
	}
	public static String getTitleSql(String cellId)
	{
		String sql = "SELECT MSPI_TITLE from " + TBL_MSP_TL_INDICATORS ;
		sql += " where MSPI_CELLID = '"+cellId+"'";
		CommonMessage.debugMsg("................................. "+sql);
		return sql;
	}
	public static String getSelectSql(String nodeId,String cellId)
	{
		String sql = "SELECT * from " + TBL_MSP_TL_INDICATORS ;
		sql += " where MSPI_KEYID ='" +nodeId+"' AND MSPI_CELLID = '"+cellId+"'";
		CommonMessage.debugMsg("................................. "+sql);
		return sql;
	}
	public static String getChildCountSql(String nodeId,String cellId)
	{
		String sql = "SELECT count(*) from " + TBL_MSP_TL_INDICATORS ;
		sql += " where MSPI_parentID ='" +nodeId+"' AND MSPI_CELLID = '"+cellId+"'";
		return sql;
	}
	public static String getSearchIndicatorSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select MSPI_PARENTID from "+TBL_MSP_TL_INDICATORS+" where MSPI_NAME ='"+searchNode+"'");//like '%"+searchNode+"%'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and MSPI_KEYID = '"+originalId+"'");
		return sb.toString();
	}
	public static String getIndicatorsSql(CommonFilter commonFilter,String isPagination){
		StringBuffer sb = new StringBuffer(); 
		
		sb.append("SELECT MSPI_KEYID,Mspi_Factoryid,Mspi_Sectionid,MSPI_CELLID,Fact_Name || '\\' || Sect_Name || '\\' || Cell_Code ||' - ' ||Cell_Name AS  Location,");
		sb.append("MSPI_TITLE,MSPI_NAME,MSPI_CODE,MSPI_LEVEL,MSPI_SORTNO");
		sb.append(" FROM Msp_Tl_Indicators,Gen_Tl_Factorymst,Gen_Tl_Sectionmst,Gen_Tl_Cellmst");
		sb.append(" Where Mspi_Factoryid=Fact_Keyid(+) And Mspi_Sectionid=Sect_Keyid(+)");
		sb.append(" AND MSPI_CELLID=CELL_KEYID(+) AND MSPI_LEVEL=3 ORDER BY MSPI_CELLID,MSPI_SORTNO");
		String sql = sb.toString();
		if(UIUtils.isValidKeyId(isPagination))
		{
			if(isPagination.equals("Y"))
			{
				sql =  "SELECT  * from ( select ROWNUM as slno, a.* from ("+sb.toString()+" ) a ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.getToRow();
			}
		}
			
		return sql;
	}
	public static String  getIndicatorsCountSql()
	{
		String sql = "SELECT count(*) from " + TBL_MSP_TL_INDICATORS +" WHERE MSPI_LEVEL=3";
		return sql;
	}

}

