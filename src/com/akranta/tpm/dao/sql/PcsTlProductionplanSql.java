package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class PcsTlProductionplanSql {

	public static final String TBL_PCS_TL_PRODUCTIONPLAN = "PCS_TL_PRODUCTIONPLAN";  

	TableFieldType [] prplDbFields = null;

	public enum   tableFldConstants
	{
		keyid, entrydate, factoryid, sectionid, cellid, machineid, productid
		, plandate, planqty, revisionno, revisiondate, plannedby, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getPrplDbFields() {
		return prplDbFields;
	}

	public PcsTlProductionplanSql()
	{
		prplDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			prplDbFields[ i ] = new TableFieldType();
		}
		prplDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PRPL_KEYID";
		prplDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.entrydate.ordinal() ].fieldName = "PRPL_ENTRYDATE";
		prplDbFields[ tableFldConstants.entrydate.ordinal() ].fieldType = 'D';

		prplDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PRPL_FACTORYID";
		prplDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "PRPL_SECTIONID";
		prplDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PRPL_CELLID";
		prplDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PRPL_MACHINEID";
		prplDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "PRPL_PRODUCTID";
		prplDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.plandate.ordinal() ].fieldName = "PRPL_PLANDATE";
		prplDbFields[ tableFldConstants.plandate.ordinal() ].fieldType = 'D';

		prplDbFields[ tableFldConstants.planqty.ordinal() ].fieldName = "PRPL_PLANQTY";
		prplDbFields[ tableFldConstants.planqty.ordinal() ].fieldType = 'N';

		prplDbFields[ tableFldConstants.revisionno.ordinal() ].fieldName = "PRPL_REVISIONNO";
		prplDbFields[ tableFldConstants.revisionno.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.revisiondate.ordinal() ].fieldName = "PRPL_REVISIONDATE";
		prplDbFields[ tableFldConstants.revisiondate.ordinal() ].fieldType = 'D';

		prplDbFields[ tableFldConstants.plannedby.ordinal() ].fieldName = "PRPL_PLANNEDBY";
		prplDbFields[ tableFldConstants.plannedby.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PRPL_TEMPFIELD1";
		prplDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PRPL_TEMPFIELD2";
		prplDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PRPL_TEMPFIELD3";
		prplDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PRPL_TEMPFIELD4";
		prplDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PRPL_TEMPFIELD5";
		prplDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PRPL_ACTIVE";
		prplDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		prplDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PRPL_CREATEDBY";
		prplDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		prplDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PRPL_CREATEDON";
		prplDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		prplDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PRPL_MODIFIEDON";
		prplDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_PRODUCTIONPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_PRODUCTIONPLAN, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_PRODUCTIONPLAN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getPlanEntrySql(String machineId,String entryDate,String productId,CommonFilter commonFilter,boolean forTotal)
	{
		String sql = "SELECT DISTINCT PRPL_KEYID,PRDM_KEYID,PRDM_NAME||'-'||PRDM_CODE,PRPL_PLANQTY,PRPL_REVISIONNO ";
		sql += " FROM PCS_TL_PRODUCTMST,PCS_TL_CYCLETIMEMST,Pcs_Tl_Productionplan";		
		sql +=" WHERE CYTM_PRODUCTID = PRDM_KEYID and PRDM_KEYID = PRPL_PRODUCTID(+) AND CYTM_MACHINEID='" + machineId + "' AND PRPL_MACHINEID(+)='"+ machineId + "'";
		if(CommonFunctions.isValidKeyId(productId))
			sql +=" AND PRDM_KEYID='"+ productId + "'";
		if(CommonFunctions.isValidKeyId(entryDate))
			sql +=" AND '"+entryDate+"' BETWEEN CYTM_FROMDATE AND CYTM_TILLDATE";
		sql +=" ORDER BY PRDM_KEYID";
		CommonMessage.debugMsg("SQL f : "+sql);
		String dataSql = "SELECT  * from ";
		String filtSql = "( select ROWNUM as slno,a.* from ( select  * from (";
		if(forTotal)
			dataSql = "SELECT  COUNT(*) from ";		
		filtSql = dataSql+filtSql;
		filtSql += sql + "))a)";
		if(!forTotal)
			filtSql += "where slno >= '"+commonFilter.getFromRow()+"' and slno <= '"+commonFilter.getToRow()+"'";
		CommonMessage.debugMsg("SQL : "+filtSql);
		return filtSql;
	}

}

