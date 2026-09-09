package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlProgrammstSql {

	public static final String TBL_ENT_TL_PROGRAMMST = "ENT_TL_PROGRAMMST";  

	TableFieldType [] progDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, name, trar_keyid, purpose, benifit, remarks, is_evaluation_need
		, min_duration, max_duration, contact_info, type, spoke_keyid
		, function, uniquepos,month,materialready, tgtm_keyid,repeatedprogram,frequency
		, effectivefrom,effectivetill,elementid,tempfield1,tempfield2,tempfield3
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getProgDbFields() {
		return progDbFields;
	}

	public EntTlProgrammstSql()
	{
		progDbFields = new TableFieldType[ 30 ];
		for(int i = 0;i < 30; i++)
		{	
			progDbFields[ i ] = new TableFieldType();
		}
		progDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PROG_KEYID";
		progDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.code.ordinal() ].fieldName = "PROG_CODE";
		progDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.name.ordinal() ].fieldName = "PROG_NAME";
		progDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldName = "PROG_TRAR_KEYID";
		progDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.purpose.ordinal() ].fieldName = "PROG_PURPOSE";
		progDbFields[ tableFldConstants.purpose.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.benifit.ordinal() ].fieldName = "PROG_BENIFIT";
		progDbFields[ tableFldConstants.benifit.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "PROG_REMARKS";
		progDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.is_evaluation_need.ordinal() ].fieldName = "PROG_IS_EVALUATION_NEED";
		progDbFields[ tableFldConstants.is_evaluation_need.ordinal() ].fieldType = 'C';

		progDbFields[ tableFldConstants.min_duration.ordinal() ].fieldName = "PROG_MIN_DURATION";
		progDbFields[ tableFldConstants.min_duration.ordinal() ].fieldType = 'N';

		progDbFields[ tableFldConstants.max_duration.ordinal() ].fieldName = "PROG_MAX_DURATION";
		progDbFields[ tableFldConstants.max_duration.ordinal() ].fieldType = 'N';

		progDbFields[ tableFldConstants.contact_info.ordinal() ].fieldName = "PROG_CONTACT_INFO";
		progDbFields[ tableFldConstants.contact_info.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.type.ordinal() ].fieldName = "PROG_TYPE";
		progDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.spoke_keyid.ordinal() ].fieldName = "PROG_SPOKE_KEYID";
		progDbFields[ tableFldConstants.spoke_keyid.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.function.ordinal() ].fieldName = "PROG_FUNCTION";
		progDbFields[ tableFldConstants.function.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.uniquepos.ordinal() ].fieldName = "PROG_UNIQUEPOS";
		progDbFields[ tableFldConstants.uniquepos.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.month.ordinal() ].fieldName = "PROG_MONTH";
		progDbFields[ tableFldConstants.month.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.materialready.ordinal() ].fieldName = "PROG_MATERIALREADY";
		progDbFields[ tableFldConstants.materialready.ordinal() ].fieldType = 'C';

		progDbFields[ tableFldConstants.tgtm_keyid.ordinal() ].fieldName = "PROG_TGTM_KEYID";
		progDbFields[ tableFldConstants.tgtm_keyid.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.repeatedprogram.ordinal() ].fieldName = "PROG_REPEATEDPROGRAM";
		progDbFields[ tableFldConstants.repeatedprogram.ordinal() ].fieldType = 'C';
		
		progDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "PROG_FREQUENCY";
		progDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'V';
		
		progDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldName = "PROG_EFFECTIVEFROM";
		progDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldType = 'D';
		
		progDbFields[ tableFldConstants.effectivetill.ordinal() ].fieldName = "PROG_EFFECTIVETILL";
		progDbFields[ tableFldConstants.effectivetill.ordinal() ].fieldType = 'D';

		progDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PROG_ELEMENTID";
		progDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PROG_TEMPFIELD1";
		progDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		progDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PROG_TEMPFIELD2";
		progDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		progDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PROG_TEMPFIELD3";
		progDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';
		
		progDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PROG_ACTIVE";
		progDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		progDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PROG_CREATEDBY";
		progDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		progDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PROG_CREATEDON";
		progDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		progDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PROG_MODIFIEDON";
		progDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_PROGRAMMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_PROGRAMMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_PROGRAMMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String masterGrid() {
		// TODO Auto-generated method stub
		//String sql = "SELECT PROG_KEYID,PROG_NAME,PROG_CODE,STYP_NAME, PROG_PURPOSE,PROG_BENIFIT,PROG_MIN_DURATION,PROG_MAX_DURATION,PROG_CONTACT_INFO,PROG_REMARKS FROM "+TableNames.TBL_ENT_TL_PROGRAMMST +','+ TableNames.TBL_ENT_TL_SKILLTYPE +" WHERE  PROG_TYPE = STYP_KEYID";
		String sql = "SELECT PROG_KEYID,PROG_NAME,PROG_CODE, PROG_PURPOSE,PROG_BENIFIT,PROG_MIN_DURATION,PROG_MAX_DURATION,PROG_CONTACT_INFO,PROG_REMARKS FROM "+TableNames.TBL_ENT_TL_PROGRAMMST  ;
		CommonMessage.debugMsg("sql  "+sql);
		return sql;
	}

	public static String getFormData() {
		// TODO Auto-generated method stub
		return "select * from " +TableNames.TBL_ENT_TL_PROGRAMMST +" where PROG_KEYID = ? AND UPPER(PROG_MONTH) = ? ";
	}

}

