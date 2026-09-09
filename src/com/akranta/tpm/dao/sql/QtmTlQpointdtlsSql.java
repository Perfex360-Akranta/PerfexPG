package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.QtmTlKnowwhydtlSql.tableFldConstants;

public class QtmTlQpointdtlsSql {

	public static final String TBL_QTM_TL_QPOINTDTLS = "QTM_TL_QPOINTDTLS";  

	TableFieldType [] qptdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, qptm_keyid, qpoint, nooflocations, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public TableFieldType[] getQptdDbFields() {
		return qptdDbFields;
	}

	public QtmTlQpointdtlsSql()
	{
		qptdDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			qptdDbFields[ i ] = new TableFieldType();
		}
		qptdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QPTD_KEYID";
		qptdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qptdDbFields[ tableFldConstants.qptm_keyid.ordinal() ].fieldName = "QPTD_QPTM_KEYID";
		qptdDbFields[ tableFldConstants.qptm_keyid.ordinal() ].fieldType = 'V';

		qptdDbFields[ tableFldConstants.qpoint.ordinal() ].fieldName = "QPTD_QPOINT";
		qptdDbFields[ tableFldConstants.qpoint.ordinal() ].fieldType = 'V';

		qptdDbFields[ tableFldConstants.nooflocations.ordinal() ].fieldName = "QPTD_NOOFLOCATIONS";
		qptdDbFields[ tableFldConstants.nooflocations.ordinal() ].fieldType = 'N';

		qptdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "QPTD_TEMPFIELD1";
		qptdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		qptdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QPTD_TEMPFIELD2";
		qptdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		qptdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QPTD_TEMPFIELD3";
		qptdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		qptdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QPTD_TEMPFIELD4";
		qptdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		qptdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "QPTD_TEMPFIELD5";
		qptdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		qptdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QPTD_CREATEDBY";
		qptdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qptdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QPTD_ACTIVE";
		qptdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qptdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QPTD_CREATEDON";
		qptdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qptdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QPTD_MODIFIEDON";
		qptdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_QPOINTDTLS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_QTM_TL_QPOINTDTLS, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteForMasterSql(TableFieldType [] fieldTypeArr, String masterID)
	{
		String sql = "DELETE from " + TBL_QTM_TL_QPOINTDTLS ;
		sql += " where " + fieldTypeArr[ tableFldConstants.qptm_keyid.ordinal() ].fieldName  +
			  " = '" +  masterID + "'";
		return sql;
	}
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_QTM_TL_QPOINTDTLS );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

}

