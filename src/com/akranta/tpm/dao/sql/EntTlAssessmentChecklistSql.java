package com.akranta.tpm.dao.sql;

public class EntTlAssessmentChecklistSql { 

		TableFieldType [] asclDbFields = null;

		public enum   tableFldConstants
		{
			keyid, asmd_keyid, topi_keyid, chek_keyid, status, tempfield1
			, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
			, createdon, modifiedon
		}

		public TableFieldType[] getAsclDbFields() {
			return asclDbFields;
		}

		public EntTlAssessmentChecklistSql()
		{
			asclDbFields = new TableFieldType[ 14 ];
			for(int i = 0;i < 14; i++)
			{	
				asclDbFields[ i ] = new TableFieldType();
			}
			asclDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ASCL_KEYID";
			asclDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

			asclDbFields[ tableFldConstants.asmd_keyid.ordinal() ].fieldName = "ASCL_ASMD_KEYID";
			asclDbFields[ tableFldConstants.asmd_keyid.ordinal() ].fieldType = 'V';

			asclDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldName = "ASCL_TOPI_KEYID";
			asclDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldType = 'V';

			asclDbFields[ tableFldConstants.chek_keyid.ordinal() ].fieldName = "ASCL_CHEK_KEYID";
			asclDbFields[ tableFldConstants.chek_keyid.ordinal() ].fieldType = 'V';

			asclDbFields[ tableFldConstants.status.ordinal() ].fieldName = "ASCL_STATUS";
			asclDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

			asclDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ASCL_TEMPFIELD1";
			asclDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

			asclDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ASCL_TEMPFIELD2";
			asclDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

			asclDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ASCL_TEMPFIELD3";
			asclDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

			asclDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ASCL_TEMPFIELD4";
			asclDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

			asclDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ASCL_TEMPFIELD5";
			asclDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

			asclDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ASCL_ACTIVE";
			asclDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

			asclDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ASCL_CREATEDBY";
			asclDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

			asclDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ASCL_CREATEDON";
			asclDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

			asclDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ASCL_MODIFIEDON";
			asclDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TableNames.TBL_ENT_TL_ASSESSMENT_CHECKLIST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TableNames.TBL_ENT_TL_ASSESSMENT_CHECKLIST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TableNames.TBL_ENT_TL_ASSESSMENT_CHECKLIST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

