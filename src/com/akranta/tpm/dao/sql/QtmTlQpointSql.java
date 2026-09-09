package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.QtmTlKnowwhydtlSql.tableFldConstants;

public class QtmTlQpointSql {

	public static final String TBL_QTM_TL_QPOINT = "QTM_TL_QPOINT";  

	TableFieldType [] qptmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, area, kpov, qpoint, preparedby, date
		, nooflocations, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getQptmDbFields() {
		return qptmDbFields;
	}

	public QtmTlQpointSql()
	{
		qptmDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			qptmDbFields[ i ] = new TableFieldType();
		}
		qptmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QPTM_KEYID";
		qptmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "QPTM_FLID";
		qptmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "QPTM_ELEMENTID";
		qptmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.area.ordinal() ].fieldName = "QPTM_AREA";
		qptmDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.kpov.ordinal() ].fieldName = "QPTM_KPOV";
		qptmDbFields[ tableFldConstants.kpov.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.qpoint.ordinal() ].fieldName = "QPTM_QPOINT";
		qptmDbFields[ tableFldConstants.qpoint.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "QPTM_PREPAREDBY";
		qptmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "QPTM_DATE";
		qptmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		qptmDbFields[ tableFldConstants.nooflocations.ordinal() ].fieldName = "QPTM_NOOFLOCATIONS";
		qptmDbFields[ tableFldConstants.nooflocations.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "QPTM_TEMPFIELD1";
		qptmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		qptmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QPTM_TEMPFIELD2";
		qptmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		qptmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QPTM_TEMPFIELD3";
		qptmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		qptmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QPTM_TEMPFIELD4";
		qptmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		qptmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "QPTM_TEMPFIELD5";
		qptmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		qptmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QPTM_ACTIVE";
		qptmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qptmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QPTM_CREATEDBY";
		qptmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qptmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QPTM_CREATEDON";
		qptmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qptmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QPTM_MODIFIEDON";
		qptmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_QPOINT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_QPOINT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_QPOINT ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
		String sql= " select QPTM_AREA,QPTM_KPOV,QPTD_QPOINT,QPTM_PREPAREDBY,QPTD_NOOFLOCATIONS,QPTM_DATE ";
		sql+= " FROM qtm_tl_qpoint,qtm_tl_qpointdtls  " ;
		sql+= " where QPTD_QPTM_KEYID =QPTM_KEYID and QPTD_QPTM_KEYID='"+keyid+"'";
	    return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM QTM_TL_QPOINT WHERE QPTM_KEYID = ?";
		return sql;
	}

}

