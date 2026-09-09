package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonMessage;
public class WorkFlowmstSql {

	public static final String TBL_GEN_TL_WORKFLOWMST = "GEN_TL_WORKFLOWMST";  

	TableFieldType [] wrkmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, noofstage, tempfield1, tempfield2, tempfield3, tempfield4
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getWrkmDbFields() {
		return wrkmDbFields;
	}

	public WorkFlowmstSql()
	{
		wrkmDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			wrkmDbFields[ i ] = new TableFieldType();
		}
		wrkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WRKM_KEYID";
		wrkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wrkmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "WRKM_NAME";
		wrkmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		wrkmDbFields[ tableFldConstants.noofstage.ordinal() ].fieldName = "WRKM_NOOFSTAGE";
		wrkmDbFields[ tableFldConstants.noofstage.ordinal() ].fieldType = 'N';

		wrkmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WRKM_TEMPFIELD1";
		wrkmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		wrkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WRKM_TEMPFIELD2";
		wrkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		wrkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WRKM_TEMPFIELD3";
		wrkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		wrkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "WRKM_TEMPFIELD4";
		wrkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		wrkmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WRKM_ACTIVE";
		wrkmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wrkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WRKM_CREATEDBY";
		wrkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wrkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WRKM_CREATEDON";
		wrkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wrkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WRKM_MODIFIEDON";
		wrkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_WORKFLOWMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_WORKFLOWMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_WORKFLOWMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getWorkFlowmstSql() {
		// TODO Auto-generated method stub
		String sql="select * from " + TBL_GEN_TL_WORKFLOWMST + " where WRKM_KEYID= ?";
		
		CommonMessage.debugMsg("sql"+sql);
		return sql;
	}

	public static String getDetail( String mstkeyid) {
		String sql = " Select 'Stage' ,'Type' from dual ";
		if(UIUtils.isValidKeyId(mstkeyid)){
			 sql+=" union all SELECT WRKD_STAGE as Stage, WRKD_TYPE as Type " ;
		   sql+="From GEN_TL_WORKFLOWDTL,GEN_TL_WORKFLOWMST  where WRKD_WRKM_KEYID=WRKM_KEYID and WRKM_KEYID='"+mstkeyid+"'";
		}
	return sql;
	}


	


	

}

