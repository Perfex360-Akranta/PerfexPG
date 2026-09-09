package com.akranta.tpm.dao.sql;

public class GenTlSusamstSql {

	public static final String TBL_GEN_TL_SUSAMST = "GEN_TL_SUSAMST";  

	TableFieldType [] susmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, description, preparedby, participants
		, conimage, nconimage, docno, safeornot, safe, unsafe
		, tempfield5, tempfield6, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSusmDbFields() {
		return susmDbFields;
	}

	public GenTlSusamstSql()
	{
		susmDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			susmDbFields[ i ] = new TableFieldType();
		}
		susmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SUSM_KEYID";
		susmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SUSM_FLID";
		susmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		
		susmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "SUSM_DATE";
		susmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		susmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "SUSM_DESCRIPTION";
		susmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "SUSM_PREPAREDBY";
		susmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.participants.ordinal() ].fieldName = "SUSM_PARTICIPANTS";
		susmDbFields[ tableFldConstants.participants.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.conimage.ordinal() ].fieldName = "SUSM_CONIMAGE";
		susmDbFields[ tableFldConstants.conimage.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.nconimage.ordinal() ].fieldName = "SUSM_NCONIMAGE";
		susmDbFields[ tableFldConstants.nconimage.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.docno.ordinal() ].fieldName = "SUSM_DOCNO";
		susmDbFields[ tableFldConstants.docno.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.safeornot.ordinal() ].fieldName = "SUSM_SAFEORNOT";
		susmDbFields[ tableFldConstants.safeornot.ordinal() ].fieldType = 'C';

		susmDbFields[ tableFldConstants.safe.ordinal() ].fieldName = "SUSM_SAFE";
		susmDbFields[ tableFldConstants.safe.ordinal() ].fieldType = 'C';

		susmDbFields[ tableFldConstants.unsafe.ordinal() ].fieldName = "SUSM_UNSAFE";
		susmDbFields[ tableFldConstants.unsafe.ordinal() ].fieldType = 'C';

		susmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SUSM_TEMPFIELD5";
		susmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		susmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "SUSM_TEMPFIELD6";
		susmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		susmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SUSM_ACTIVE";
		susmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		susmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SUSM_CREATEDBY";
		susmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		susmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SUSM_CREATEDON";
		susmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		susmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SUSM_MODIFIEDON";
		susmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SUSAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SUSAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SUSAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM GEN_TL_SUSAMST WHERE SUSM_KEYID = ?";
		return sql;
	}

}

