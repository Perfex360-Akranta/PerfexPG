package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


public class EntTlAssessmentdtlSql { 
							 
	public static final String TBL_ENT_TL_ENTTLASSESMENTDTL = "ENT_TL_ASSESSMENTDTL";  

	TableFieldType [] asmdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, asmm_keyid, spok_keyid, topi_keyid, cutoff, score, result
		, prog_keyid, bach_keyid, current_rate, previous_rate, faculty
		, type, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, tempfield8, tempfield9, tempfield10, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getAsmdDbFields() {
		return asmdDbFields;
	}
	
	public Object[] getSaveArray() {
		return asmdDbFields;
	}

	public EntTlAssessmentdtlSql()
	{
		asmdDbFields = new TableFieldType[ 25 ];
		for(int i = 0;i < 25; i++)
		{	
			asmdDbFields[ i ] = new TableFieldType();
		}
		asmdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ASMD_KEYID";
		asmdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.asmm_keyid.ordinal() ].fieldName = "ASMD_ASMM_KEYID";
		asmdDbFields[ tableFldConstants.asmm_keyid.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.spok_keyid.ordinal() ].fieldName = "ASMD_SPOK_KEYID";
		asmdDbFields[ tableFldConstants.spok_keyid.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldName = "ASMD_TOPI_KEYID";
		asmdDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.cutoff.ordinal() ].fieldName = "ASMD_CUTOFF";
		asmdDbFields[ tableFldConstants.cutoff.ordinal() ].fieldType = 'N';

		asmdDbFields[ tableFldConstants.score.ordinal() ].fieldName = "ASMD_SCORE";
		asmdDbFields[ tableFldConstants.score.ordinal() ].fieldType = 'N';

		asmdDbFields[ tableFldConstants.result.ordinal() ].fieldName = "ASMD_RESULT";
		asmdDbFields[ tableFldConstants.result.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "ASMD_PROG_KEYID";
		asmdDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldName = "ASMD_BACH_KEYID";
		asmdDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.current_rate.ordinal() ].fieldName = "ASMD_CURRENT_RATE";
		asmdDbFields[ tableFldConstants.current_rate.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.previous_rate.ordinal() ].fieldName = "ASMD_PREVIOUS_RATE";
		asmdDbFields[ tableFldConstants.previous_rate.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.faculty.ordinal() ].fieldName = "ASMD_FACULTY";
		asmdDbFields[ tableFldConstants.faculty.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.type.ordinal() ].fieldName = "ASMD_TYPE";
		asmdDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ASMD_TEMPFIELD3";
		asmdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ASMD_TEMPFIELD4";
		asmdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ASMD_TEMPFIELD5";
		asmdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "ASMD_TEMPFIELD6";
		asmdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "ASMD_TEMPFIELD7";
		asmdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "ASMD_TEMPFIELD8";
		asmdDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "ASMD_TEMPFIELD9";
		asmdDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "ASMD_TEMPFIELD10";
		asmdDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ASMD_ACTIVE";
		asmdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		asmdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ASMD_CREATEDBY";
		asmdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		asmdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ASMD_CREATEDON";
		asmdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		asmdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ASMD_MODIFIEDON";
		asmdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TableNames.TBL_ENT_TL_ASSESSMENTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TableNames.TBL_ENT_TL_ASSESSMENTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TableNames.TBL_ENT_TL_ASSESSMENTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "SELECT * from " + TableNames.TBL_ENT_TL_ASSESSMENTDTL + " where 1=1 ";
		if (CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.asmm_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.asmm_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.asmm_keyid.ordinal()] + "'";
		}
		if (CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.spok_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.spok_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.spok_keyid.ordinal()] + "'";
		}
		if (CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.topi_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.topi_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.topi_keyid.ordinal()] + "'";
		}
		if (CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}
		return sql;
	}

	public static String getDelete(String asmmKeyid) {
		
		String sql = "DELETE from " + TBL_ENT_TL_ENTTLASSESMENTDTL +" WHERE ASMD_ASMM_KEYID = '"+asmmKeyid+"'";
		return sql;
		
	}

}

