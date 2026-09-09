package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.PrjConstants;


public class SqlUtils {

	public static String getInsertSql(String tableName,TableFieldType [] fieldTypeArr, Object[] dataArr )
	{
		StringBuffer sql = 	new StringBuffer();
		StringBuffer dataSql = new StringBuffer();
		
		sql.append(" insert into " + tableName + "(");
		int i = 0;
		for( TableFieldType tableFieldType :fieldTypeArr)
		{
			sql.append( tableFieldType.fieldName + ",");
			dataSql.append(formatFieldValue( dataArr[i++],tableFieldType.fieldType ));
			dataSql.append(",");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(") values( ");
		dataSql.deleteCharAt(dataSql.lastIndexOf(","));
		dataSql.append(")");
		CommonMessage.debugMsg("  dataSql    "+dataSql);
		sql.append(dataSql);
		return sql.toString();
		
	}
	
		
	public static String getSingleUpdt(String tableName,String Pldetailsid, TableFieldType [] fieldTypeArr, String lossId, String lossMapId, String lossColName,String lossValue)
	{
		StringBuffer sql = 	new StringBuffer();
		CommonMessage.debugMsg("inside getsingleupdat");
		sql.append(" update  " + tableName + " set ");
		
		String lossVal ;
		 // ---- VIGNESH
		//lossVal =" ( SELECT DECODE(SUM(PLRK_MINUTES),0,NULL,SUM(PLRK_MINUTES)) FROM PCS_TL_LOSSREASONLINK " ;
	//	lossVal+= " WHERE PLRK_PLDETAILID ='" + Pldetailsid + "' AND PLRK_LOSSID ='" + lossId + "') ";

		lossVal  = " ( SELECT NULLIF(SUM(PLRK_MINUTES), 0) FROM PCS_TL_LOSSREASONLINK ";
		lossVal += "   WHERE PLRK_PLDETAILID = '" + Pldetailsid + "' AND PLRK_LOSSID = '" + lossId + "' ) ";

		// OPTIONAL
		
	//	lossVal  = " ( SELECT NULLIF(SUM(PLRK_MINUTES::numeric), 0) FROM PCS_TL_LOSSREASONLINK ";
	//	lossVal += "   WHERE PLRK_PLDETAILID = '" + Pldetailsid + "' AND PLRK_LOSSID = '" + lossId + "' ) ";

		//---------- VIGNESH -------//
		CommonMessage.debugMsg("lossVal"+lossVal);
		
		for( TableFieldType tableFieldType :fieldTypeArr)
		{			
			CommonMessage.debugMsg("tableFieldType.fieldName=="+tableFieldType.fieldName+"inside for sd lossid"+lossMapId);
			if  (tableFieldType.fieldName.equals(lossMapId))
			{
				CommonMessage.debugMsg("lossVal2"+lossVal);
				//sql.append( tableFieldType.fieldName + " = " + formatFieldValue( lossValue,tableFieldType.fieldType ) );
				sql.append( tableFieldType.fieldName + " = " + lossVal  );
				//sql.append(",");
			}
			else if(lossMapId.equals("{}"))
			{
				CommonMessage.debugMsg("losssnameeee=="+lossColName);
	
				String  fName = tableFieldType.fieldName.toUpperCase();
				CommonMessage.debugMsg("fname=="+fName);
				if (lossColName.contains(fName)) {
					sql.append( tableFieldType.fieldName + " = " + lossVal);//formatFieldValue( lossValue,tableFieldType.fieldType ) );
					//sql.append( tableFieldType.fieldName + " = " + lossVal );
				}						
			}				
		}
		//sql.deleteCharAt(sql.lastIndexOf(","));
		CommonMessage.debugMsg("sql.toString()"+sql.toString());
		return sql.toString();
	}

	public static String getUpdatePcsSql(String tableName,TableFieldType [] fieldTypeArr, Object[] dataArr )
	{
		StringBuffer sql = 	new StringBuffer();
		
		sql.append(" update  " + tableName + " set ");
		int i = 0;
		for( TableFieldType tableFieldType :fieldTypeArr)
		{ 
			if (tableFieldType.fieldName.equals("CALENDARTIME") || tableFieldType.fieldName.equals("NOPLANINMINS") ||
					tableFieldType.fieldName.equals("PLANNEDQTY") || tableFieldType.fieldName.equals("ACTUALCYCLETIME") ||
					tableFieldType.fieldName.equals("PRODUCEDQTY") || tableFieldType.fieldName.equals("UNACCOUNTEDTIME") ||
					tableFieldType.fieldName.equals("LOADINGTIME") || tableFieldType.fieldName.equals("PRODUCTIONTIME") ||
					tableFieldType.fieldName.equals("REMARKS") || 
					tableFieldType.fieldName.equals("REJECTEDQTY") ||
					tableFieldType.fieldName.equals("CAVITYAVAILABLE") || tableFieldType.fieldName.equals("CAVITYUSED") ||
					tableFieldType.fieldName.equals("REMARKS") || tableFieldType.fieldName.equals("REMARKS") ||
					tableFieldType.fieldName.equals("CAVITYAVAILABLE") || tableFieldType.fieldName.equals("BACKLOGQTY") ||
					tableFieldType.fieldName.equals("THEORITICALCYCLETIME") || tableFieldType.fieldName.equals("ACTUALCYCLETIME") ||
					tableFieldType.fieldName.equals("PRODUCTID") || tableFieldType.fieldName.equals("WNO") ||
					tableFieldType.fieldName.equals("RAWMATERIALTYPE") || tableFieldType.fieldName.equals("WEIGHT") ||
					tableFieldType.fieldName.equals("TRIMMINGQTY") || tableFieldType.fieldName.equals("EXPANSIONQTY") )
				
			{ 
				CommonMessage.debugMsg("tableFieldType.fieldName"+tableFieldType.fieldName);
				CommonMessage.debugMsg("dataArr[i],tableFieldType.fieldType"+dataArr[i]);
				sql.append( tableFieldType.fieldName + " = " + formatFieldValue( dataArr[i++],tableFieldType.fieldType ) );
				sql.append(",");
			}
			else
				i++;
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		return sql.toString();
	}

	public static String getUpdateSql(String tableName,TableFieldType [] fieldTypeArr, Object[] dataArr )
	{
		StringBuffer sql = 	new StringBuffer();
		
		sql.append(" update  " + tableName + " set ");
		int i = 0;
		for( TableFieldType tableFieldType :fieldTypeArr)
		{ 
				sql.append( tableFieldType.fieldName + " = " + formatFieldValue( dataArr[i++],tableFieldType.fieldType ) );
				sql.append(",");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		return sql.toString();
	}
	
	/*
	 * private static String formatFieldValue(Object value, int dataType) {
	 * CommonMessage.debugMsg(" value " + value);
	 * 
	 * if( dataType == TableColType.Date) return " to_date( '" + (String) value +
	 * "','dd-Mon-yyyy hh24:mi:ss')" ; else if( dataType == TableColType.BLOB)
	 * return (String) value; else return "'" + (value != null
	 * ?((String)value).trim() : "") +"'"; }
	 */
	
	// -------- Vignesh  24NOv2025 -----------//
		private static String formatFieldValue(Object value, int dataType)
		{
			CommonMessage.debugMsg(" value " + value);
			
			if( dataType == TableColType.Date)
				return  " to_date( '" + (String) value + "','dd-Mon-yyyy hh24:mi:ss')" ;
			else if( dataType == TableColType.BLOB)
				return (String) value;
			//---------- vIGNESH
			else if( dataType == TableColType.NUMERIC)
				return  (value != null ?((String)value).trim() : "0") ;
			else
				return "'" + (value != null ?((String)value).trim() : "") +"'";
		}	
		// -------- Vignesh  24NOv2025 -----------//
	
	public static String getEntMailInfoSql()
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select cnfm_code,cnfm_settingvalue from ADM_TL_CONFIGURATIONMST where cnfm_code in(");
		sql.append("'"+PrjConstants.ENT_MAIL_USERNAME_CODE+"','"+PrjConstants.ENT_MAIL_PWD_CODE+"',");
		sql.append("'"+PrjConstants.ENT_MAIL_HOST_CODE+"','"+PrjConstants.ENT_MAIL_PORT_CODE+"','"+PrjConstants.ENT_MAIL_FROM_ADDRESS+"')");
		CommonMessage.debugMsg(sql.toString());
		return sql.toString();
	}
}
