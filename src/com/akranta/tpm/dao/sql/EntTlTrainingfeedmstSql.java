package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTrainingfeedmstSql {

	public static final String TBL_ENT_TL_TRAININGFEEDMST = "ENT_TL_TRAININGFEEDMST";  

	TableFieldType [] tfmsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, title, date, duration, faculty, participantname, designation
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, flid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTfmsDbFields() {
		return tfmsDbFields;
	}

	public EntTlTrainingfeedmstSql()
	{
		tfmsDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			tfmsDbFields[ i ] = new TableFieldType();
		}
		tfmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TFMS_KEYID";
		tfmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.title.ordinal() ].fieldName = "TFMS_TITLE";
		tfmsDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.date.ordinal() ].fieldName = "TFMS_DATE";
		tfmsDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		tfmsDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "TFMS_DURATION";
		tfmsDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		tfmsDbFields[ tableFldConstants.faculty.ordinal() ].fieldName = "TFMS_FACULTY";
		tfmsDbFields[ tableFldConstants.faculty.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.participantname.ordinal() ].fieldName = "TFMS_PARTICIPANTNAME";
		tfmsDbFields[ tableFldConstants.participantname.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.designation.ordinal() ].fieldName = "TFMS_DESIGNATION";
		tfmsDbFields[ tableFldConstants.designation.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "TFMS_REMARKS";
		tfmsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TFMS_TEMPFIELD1";
		tfmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		tfmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TFMS_TEMPFIELD2";
		tfmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		tfmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TFMS_TEMPFIELD3";
		tfmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		tfmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TFMS_TEMPFIELD4";
		tfmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		tfmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TFMS_TEMPFIELD5";
		tfmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		tfmsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TFMS_FLID";
		tfmsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TFMS_ACTIVE";
		tfmsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tfmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TFMS_CREATEDBY";
		tfmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tfmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TFMS_CREATEDON";
		tfmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tfmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TFMS_MODIFIEDON";
		tfmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRAININGFEEDMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRAININGFEEDMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRAININGFEEDMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getTrainingSelectSql()
	{
        
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ENT_TL_TRAININGFEEDMST+ " where TFMS_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

}

