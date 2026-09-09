package com.akranta.tpm.dao.sql;

public class GenTlSusaBehaviormstSql {

	public static final String TBL_GEN_TL_SUSABEHAVIORMST = "GEN_TL_SUSABEHAVIORMST";  

	TableFieldType [] susbDbFields = null;

	public enum   tableFldConstants
	{
		behaviorkeyid, behaviormasterkeyid, type, behavior,cause, action, probability
		, consequence, remarks, filemanagerid, tempfield1, tempfield2,
		 tempfield3, tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getsusbDbFields() {
		return susbDbFields;
	}

	public GenTlSusaBehaviormstSql()
	{
		susbDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 19; i++)
		{	
			susbDbFields[ i ] = new TableFieldType();
		}
		susbDbFields[ tableFldConstants.behaviorkeyid.ordinal() ].fieldName = "SUSB_KEYID";
		susbDbFields[ tableFldConstants.behaviorkeyid.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.behaviormasterkeyid.ordinal() ].fieldName = "SUSB_SUSN_KEYID";
		susbDbFields[ tableFldConstants.behaviormasterkeyid.ordinal() ].fieldType = 'V';

		
		susbDbFields[ tableFldConstants.type.ordinal() ].fieldName = "SUSB_TYPE";
		susbDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.behavior.ordinal() ].fieldName = "SUSB_BEHAVIOR";
		susbDbFields[ tableFldConstants.behavior.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.cause.ordinal() ].fieldName = "SUSB_CAUSE";
		susbDbFields[ tableFldConstants.cause.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.action.ordinal() ].fieldName = "SUSB_ACTION";
		susbDbFields[ tableFldConstants.action.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.probability.ordinal() ].fieldName = "SUSB_PROBABILITY";
		susbDbFields[ tableFldConstants.probability.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.consequence.ordinal() ].fieldName = "SUSB_CONSEQUENCE";
		susbDbFields[ tableFldConstants.consequence.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SUSB_REMARKS";
		susbDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.filemanagerid.ordinal() ].fieldName = "SUSB_FILEMANAGERID";
		susbDbFields[ tableFldConstants.filemanagerid.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SUSB_TEMPFIELD1";
		susbDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		susbDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SUSB_TEMPFIELD2";
		susbDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		susbDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SUSB_TEMPFIELD3";
		susbDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		susbDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SUSB_TEMPFIELD4";
		susbDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		susbDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SUSB_TEMPFIELD5";
		susbDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		susbDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SUSB_ACTIVE";
		susbDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		susbDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SUSB_CREATEDBY";
		susbDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		susbDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SUSB_CREATEDON";
		susbDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		susbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SUSB_MODIFIEDON";
		susbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SUSABEHAVIORMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SUSABEHAVIORMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.behaviorkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.behaviorkeyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SUSABEHAVIORMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.behaviorkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.behaviorkeyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM GEN_TL_SUSABEHAVIORMST WHERE SUSB_KEYID = ?";
		return sql;
	}

}

