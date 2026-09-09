package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.dao.impl.CommonFunctions;

public class JhaTlFiveSAuditareamstSql {

	public static final String TBL_JHA_TL_FIVE_S_AUDITAREAMST = "JHA_TL_FIVE_S_AUDITAREAMST";  

	TableFieldType [] fvasDbFields = null;

	public enum   tableFldConstants
	{
		keyid, areaname, areacode, responsibiltyid, parentid, areatype
		, frequency, circleid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFvasDbFields() {
		return fvasDbFields;
	}

	public JhaTlFiveSAuditareamstSql()
	{
		fvasDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			fvasDbFields[ i ] = new TableFieldType();
		}
		fvasDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FVAS_KEYID";
		fvasDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fvasDbFields[ tableFldConstants.areaname.ordinal() ].fieldName = "FVAS_AREANAME";
		fvasDbFields[ tableFldConstants.areaname.ordinal() ].fieldType = 'V';

		fvasDbFields[ tableFldConstants.areacode.ordinal() ].fieldName = "FVAS_AREACODE";
		fvasDbFields[ tableFldConstants.areacode.ordinal() ].fieldType = 'V';

		fvasDbFields[ tableFldConstants.responsibiltyid.ordinal() ].fieldName = "FVAS_RESPONSIBILTYID";
		fvasDbFields[ tableFldConstants.responsibiltyid.ordinal() ].fieldType = 'V';

		fvasDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "FVAS_PARENTID";
		fvasDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		fvasDbFields[ tableFldConstants.areatype.ordinal() ].fieldName = "FVAS_AREATYPE";
		fvasDbFields[ tableFldConstants.areatype.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "FVAS_FREQUENCY";
		fvasDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.circleid.ordinal() ].fieldName = "FVAS_CIRCLEID";
		fvasDbFields[ tableFldConstants.circleid.ordinal() ].fieldType = 'V';

		fvasDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FVAS_TEMPFIELD1";
		fvasDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FVAS_TEMPFIELD2";
		fvasDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FVAS_TEMPFIELD3";
		fvasDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FVAS_TEMPFIELD4";
		fvasDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FVAS_TEMPFIELD5";
		fvasDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FVAS_ACTIVE";
		fvasDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fvasDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FVAS_CREATEDBY";
		fvasDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fvasDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FVAS_CREATEDON";
		fvasDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fvasDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FVAS_MODIFIEDON";
		fvasDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_FIVE_S_AUDITAREAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		// TODO Auto-generated method stub
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_FIVE_S_AUDITAREAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		
		return sql;
	}
		
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("getDeleteSql");
		String sql = "DELETE from " + TBL_JHA_TL_FIVE_S_AUDITAREAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		CommonMessage.debugMsg(sql);
		
		return sql;
	}


	public static String getauditdata() {
		// TODO Auto-generated method stub
		 String  sql= "select * from JHA_TL_FIVE_S_AUDITAREAMST WHERE FVAS_KEYID = ?";
			return sql;
		
	}

}

