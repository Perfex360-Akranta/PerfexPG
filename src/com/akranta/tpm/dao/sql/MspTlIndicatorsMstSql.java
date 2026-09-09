package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlIndicatorsMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MspTlIndicatorsMstSql {

	public static final String TBL_MSP_TL_INDICATORS_MST = "MSP_TL_INDICATORS_MST";  
	public static final String MasterPlanId = "MP001";
	TableFieldType [] mspiDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, title, pillar,tempfield1,flid ,elementid
		,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMspiDbFields() {
		return mspiDbFields;
	}

	public MspTlIndicatorsMstSql()
	{
		mspiDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			mspiDbFields[ i ] = new TableFieldType();
		}
		mspiDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MSPI_KEYID";
		mspiDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "MSPI_FACTORYID";
		mspiDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "MSPI_SECTIONID";
		mspiDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "MSPI_CELLID";
		mspiDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "MSPI_ELEMENTID";
		mspiDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MSPI_FLID";
		mspiDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.title.ordinal() ].fieldName = "MSPI_TITLE";
		mspiDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';
		
		mspiDbFields[ tableFldConstants.pillar.ordinal() ].fieldName = "MSPI_PILLAR";
		mspiDbFields[ tableFldConstants.pillar.ordinal() ].fieldType = 'V';

		mspiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MSPI_TEMPFIELD1";
		mspiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

	/*	mspiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MSPI_TEMPFIELD2";
		mspiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		mspiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MSPI_TEMPFIELD3";
		mspiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';*/

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
		return SqlUtils.getInsertSql(TBL_MSP_TL_INDICATORS_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MSP_TL_INDICATORS_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MSP_TL_INDICATORS_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getTitleSql(String cellId,String pillar,String title)
	{
		String sql = "SELECT MSPI_TITLE,MSPI_KEYID from " + TBL_MSP_TL_INDICATORS_MST ;
		sql += " where MSPI_CELLID = '"+cellId+"' AND MSPI_PILLAR='"+pillar+"' and upper(mspi_title)=upper('"+title+"')";
		CommonMessage.debugMsg("TBL_MSP_TL_INDICATORS_MST................................ "+sql);
		return sql;
	}
/*	public static String getIndicatorsSql(CommonFilter commonFilter,String pillar,String isPagination){
		StringBuffer sb = new StringBuffer(); 
		
		sb.append("SELECT MSPD_KEYID,MspI_Factoryid,MspI_Sectionid,MSPI_CELLID,Fact_Name || '\\' || Sect_Name || '\\' || Cell_Code ||' - ' ||Cell_Name AS  Location,");
		sb.append("MSPI_TITLE,MSPD_NAME,MSPD_CODE,MSPD_LEVEL,MSPD_SORTNO");
		sb.append(" FROM "+TBL_MSP_TL_INDICATORS_MST+","+MspTlIndicatorsDtlSql.TBL_MSP_TL_INDICATORS_DTL+",Gen_Tl_Factorymst,Gen_Tl_Sectionmst,Gen_Tl_Cellmst");
		sb.append(" Where MSPI_KEYID=MSPD_MSPI_KEYID(+) and Mspi_Factoryid=Fact_Keyid(+) And Mspi_Sectionid=Sect_Keyid(+)");
		sb.append(" AND MSPI_CELLID=CELL_KEYID(+) AND MSPD_LEVEL=3 ORDER BY MSPI_CELLID,MSPD_SORTNO");*/
	  /*	sb.append("SELECT * FROM ( SELECT MSPI_KEYID,MspI_Factoryid,MspI_Sectionid,MSPI_CELLID,Fact_Name || '\\' || Sect_Name || '\\' || Cell_Code ||' - ' ||Cell_Name AS  Location,");
		sb.append("FACT_NAME AS FACTORY ,SECT_NAME AS SECTION ,CELL_NAME AS LINE,MSPI_TITLE AS TITLE");
		sb.append(" FROM "+TBL_MSP_TL_INDICATORS_MST+",Gen_Tl_Factorymst,Gen_Tl_Sectionmst,Gen_Tl_Cellmst");
		sb.append(" Where  Mspi_Factoryid=Fact_Keyid(+) And Mspi_Sectionid=Sect_Keyid(+)");
		sb.append(" AND MSPI_CELLID=CELL_KEYID(+) AND MSPI_PILLAR='"+pillar+"' ORDER BY MSPI_KEYID DESC");
		sb.append(" ) WHERE 1=1 "+ FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );*/
		
	/*	sb.append("	select  MSPI_KEYID as keyid, Mspi_pillar as pillar,SECT_NAME   AS SECTION ,");
		sb.append(" CELL_NAME   AS LINE,MSPI_TITLE as title  ");
		sb.append(" from MSP_TL_INDICATORS_MST,  Gen_Tl_Sectionmst,Gen_Tl_Cellmst ");
		sb.append(" where   Mspi_Sectionid  =Sect_Keyid AND MSPI_CELLID  =CELL_KEYID ");
	
		
		String sql = sb.toString();
		/*if(UIUtils.isValidKeyId(isPagination))
		{
			if(isPagination.equals("Y"))
			{
				sql =  "SELECT  * from ( select ROWNUM as slno, a.* from ("+sb.toString()+" ) a ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.getToRow();
			}
		}
			
		return sql;
	}*/
	
	public static String getIndicatorsSql(CommonFilter commonFilter,String pillar,String isPagination){
		StringBuffer sb = new StringBuffer(); 
		
		/*sb.append("SELECT MSPD_KEYID,MspI_Factoryid,MspI_Sectionid,MSPI_CELLID,Fact_Name || '\\' || Sect_Name || '\\' || Cell_Code ||' - ' ||Cell_Name AS  Location,");
		sb.append("MSPI_TITLE,MSPD_NAME,MSPD_CODE,MSPD_LEVEL,MSPD_SORTNO");
		sb.append(" FROM "+TBL_MSP_TL_INDICATORS_MST+","+MspTlIndicatorsDtlSql.TBL_MSP_TL_INDICATORS_DTL+",Gen_Tl_Factorymst,Gen_Tl_Sectionmst,Gen_Tl_Cellmst");
		sb.append(" Where MSPI_KEYID=MSPD_MSPI_KEYID(+) and Mspi_Factoryid=Fact_Keyid(+) And Mspi_Sectionid=Sect_Keyid(+)");
		sb.append(" AND MSPI_CELLID=CELL_KEYID(+) AND MSPD_LEVEL=3 ORDER BY MSPI_CELLID,MSPD_SORTNO");*/
		/*sb.append("SELECT * FROM ( SELECT MSPI_KEYID,MspI_Factoryid,MspI_Sectionid,MSPI_CELLID,Fact_Name || '\\' || Sect_Name || '\\' || Cell_Code ||' - ' ||Cell_Name AS  Location,");
		sb.append("FACT_NAME AS FACTORY ,SECT_NAME AS SECTION ,CELL_NAME AS LINE,MSPI_TITLE AS TITLE");
		sb.append(" FROM "+TBL_MSP_TL_INDICATORS_MST+",Gen_Tl_Factorymst,Gen_Tl_Sectionmst,Gen_Tl_Cellmst");
		sb.append(" Where  Mspi_Factoryid=Fact_Keyid(+) And Mspi_Sectionid=Sect_Keyid(+)");
		sb.append(" AND MSPI_CELLID=CELL_KEYID(+) AND MSPI_PILLAR='"+pillar+"' ORDER BY MSPI_KEYID DESC");
		sb.append(" ) WHERE 1=1 "+ FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		String sql = sb.toString();
		if(UIUtils.isValidKeyId(isPagination))
		{
			if(isPagination.equals("Y"))
			{
				sql =  "SELECT  * from ( select ROWNUM as slno, a.* from ("+sb.toString()+" ) a ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.getToRow();
			}
		}
		*/
		sb.append("SELECT * FROM ( SELECT MSPI_KEYID,FNLN_DISPLAYCODE AS  JH,");
		sb.append(" MSPI_TITLE AS TITLE");
		sb.append(" FROM "+TBL_MSP_TL_INDICATORS_MST+",GEN_MV_FLIDHIERARCHY ");
		sb.append(" Where  Mspi_FLID=FLID ");
		if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			sb.append("AND INSTR(PARENTFLIDS||FLID,'"+commonFilter.getFlid()+"') > 0");
		sb.append(" AND MSPI_PILLAR='"+pillar+"' ORDER BY MSPI_KEYID DESC");
		//sb.append("  ORDER BY MSPI_KEYID DESC");
		sb.append(" ) WHERE 1=1 "+ FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
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

	public static String getSelectmasterSql() {
		

		 String sql=" select * from " + TBL_MSP_TL_INDICATORS_MST + " where MSPI_KEYID =  ?";
			
			// TODO Auto-generated method stub
			return sql;
	}
}

