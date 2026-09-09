package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntBatchMstSql {

	public static final String TBL_ENT_TL_BATCHMST = "ENT_TL_BATCHMST";  

	TableFieldType [] bachDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, name, fact_keyid, prog_keyid, venu_keyid, fromdate
		, tilldate, minsize, maxsize, duration, remarks, status, completedate
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getBachDbFields() {
		return bachDbFields;
	}

	public EntBatchMstSql()
	{
		bachDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			bachDbFields[ i ] = new TableFieldType();
		}
		bachDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BACH_KEYID";
		bachDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.code.ordinal() ].fieldName = "BACH_CODE";
		bachDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.name.ordinal() ].fieldName = "BACH_NAME";
		bachDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldName = "BACH_FACT_KEYID";
		bachDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "BACH_PROG_KEYID";
		bachDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.venu_keyid.ordinal() ].fieldName = "BACH_VENU_KEYID";
		bachDbFields[ tableFldConstants.venu_keyid.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "BACH_FROMDATE";
		bachDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		bachDbFields[ tableFldConstants.tilldate.ordinal() ].fieldName = "BACH_TILLDATE";
		bachDbFields[ tableFldConstants.tilldate.ordinal() ].fieldType = 'D';

		bachDbFields[ tableFldConstants.minsize.ordinal() ].fieldName = "BACH_MINSIZE";
		bachDbFields[ tableFldConstants.minsize.ordinal() ].fieldType = 'N';

		bachDbFields[ tableFldConstants.maxsize.ordinal() ].fieldName = "BACH_MAXSIZE";
		bachDbFields[ tableFldConstants.maxsize.ordinal() ].fieldType = 'N';

		bachDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "BACH_DURATION";
		bachDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		bachDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "BACH_REMARKS";
		bachDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.status.ordinal() ].fieldName = "BACH_STATUS";
		bachDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		bachDbFields[ tableFldConstants.completedate.ordinal() ].fieldName = "BACH_COMPLETEDATE";
		bachDbFields[ tableFldConstants.completedate.ordinal() ].fieldType = 'D';

		bachDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "BACH_TEMPFIELD2";
		bachDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		bachDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "BACH_TEMPFIELD3";
		bachDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		bachDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "BACH_TEMPFIELD4";
		bachDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		bachDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "BACH_TEMPFIELD5";
		bachDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		bachDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BACH_ACTIVE";
		bachDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bachDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BACH_CREATEDBY";
		bachDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bachDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BACH_CREATEDON";
		bachDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bachDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BACH_MODIFIEDON";
		bachDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BATCHMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BATCHMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BATCHMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getBatchMstSql() {
		return " SELECT * from " + TBL_ENT_TL_BATCHMST + " where BACH_KEYID= ? ";
		
	}

	

	public String getDelete(String ENTKeyid) {
		return "UPDATE  "+TBL_ENT_TL_BATCHMST+" SET BACH_ACTIVE='N' WHERE BACH_KEYID='"+ENTKeyid+"'";
		
	}

	public static Object getSaveArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getBatchQuery1() {
		// TODO Auto-generated method stub
		return " SELECT BACH_KEYID,BACH_NAME,BACH_CODE,PROG_NAME,VENU_NAME,TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY') as BACH_FROMDATE,TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') as BACH_TILLDATE,BACH_MINSIZE,BACH_MAXSIZE  from  ENT_TL_BATCHMST,ENT_TL_PROGRAMMST,ENT_TL_VENUEMST  WHERE ENT_TL_BATCHMST.BACH_PROG_KEYID=ENT_TL_PROGRAMMST.PROG_KEYID  and ENT_TL_BATCHMST.BACH_VENU_KEYID=ENT_TL_VENUEMST.VENU_KEYID";
	} 
	
	public static String getBatchQuery(String Progid, String frmMonth) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside SQL  "+frmMonth);
		if(UIUtils.isValidKeyId(Progid)){
			CommonMessage.debugMsg("inside IF  "+frmMonth);
		return " SELECT BACH_KEYID,BACH_PROG_KEYID,BACH_NAME,BACH_CODE,PROG_NAME,VENU_NAME,TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') as BACH_TILLDATE,TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') as BACH_TILLDATE,BACH_MINSIZE,BACH_MAXSIZE  from  ENT_TL_BATCHMST,ENT_TL_PROGRAMMST,ENT_TL_VENUEMST  WHERE ENT_TL_BATCHMST.BACH_PROG_KEYID=ENT_TL_PROGRAMMST.PROG_KEYID and   ENT_TL_BATCHMST.BACH_VENU_KEYID=ENT_TL_VENUEMST.VENU_KEYID and BACH_PROG_KEYID='"+Progid+"' AND TO_CHAR (BACH_TILLDATE, 'MON-YYYY')  = UPPER( '"+ frmMonth +"')";
		}
		else
			return " SELECT BACH_KEYID,BACH_PROG_KEYID,BACH_NAME,BACH_CODE,PROG_NAME,VENU_NAME,TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') as BACH_TILLDATE,TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') as BACH_TILLDATE,BACH_MINSIZE,BACH_MAXSIZE  from  ENT_TL_BATCHMST,ENT_TL_PROGRAMMST,ENT_TL_VENUEMST  WHERE ENT_TL_BATCHMST.BACH_PROG_KEYID=ENT_TL_PROGRAMMST.PROG_KEYID and ENT_TL_BATCHMST.BACH_VENU_KEYID=ENT_TL_VENUEMST.VENU_KEYID";
	} 

}






