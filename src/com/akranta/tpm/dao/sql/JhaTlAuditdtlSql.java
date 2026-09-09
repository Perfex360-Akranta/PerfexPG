package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.JhaTlAuditmstSql.tableFldConstants;

public class JhaTlAuditdtlSql {

	public static final String TBL_JHA_TL_AUDITDTL = "JHA_TL_AUDITDTL";  

	TableFieldType [] jhadDbFields = null;

	public enum   tableFldConstants
	{
		keyid, jhauditmasterid, parameterid, maximumpoints, pointsscored, remarks
		,ncremarks,ncactionplan,ncstatus,ncclosed
		,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5
		,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getJhadDbFields() {
		return jhadDbFields;
	}

	public JhaTlAuditdtlSql()
	{
		jhadDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			jhadDbFields[ i ] = new TableFieldType();
		}
		jhadDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "JHAD_KEYID";
		jhadDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		jhadDbFields[ tableFldConstants.jhauditmasterid.ordinal() ].fieldName = "JHAD_JHAUDITMASTERID";
		jhadDbFields[ tableFldConstants.jhauditmasterid.ordinal() ].fieldType = 'V';

		jhadDbFields[ tableFldConstants.parameterid.ordinal() ].fieldName = "JHAD_PARAMETERID";
		jhadDbFields[ tableFldConstants.parameterid.ordinal() ].fieldType = 'V';

		jhadDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldName = "JHAD_MAXIMUMPOINTS";
		jhadDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldType = 'N';

		jhadDbFields[ tableFldConstants.pointsscored.ordinal() ].fieldName = "JHAD_POINTSSCORED";
		jhadDbFields[ tableFldConstants.pointsscored.ordinal() ].fieldType = 'N';

		jhadDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "JHAD_REMARKS";
		jhadDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.ncremarks.ordinal() ].fieldName = "JHAD_NCREMARKS";
		jhadDbFields[ tableFldConstants.ncremarks.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.ncactionplan.ordinal() ].fieldName = "JHAD_NCACTIONPLAN";
		jhadDbFields[ tableFldConstants.ncactionplan.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.ncstatus.ordinal() ].fieldName = "JHAD_NCSTATUS";
		jhadDbFields[ tableFldConstants.ncstatus.ordinal() ].fieldType = 'C';
		
		jhadDbFields[ tableFldConstants.ncclosed.ordinal() ].fieldName = "JHAD_NCCLOSED";
		jhadDbFields[ tableFldConstants.ncclosed.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "JHAD_TEMPFIELD1";
		jhadDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "JHAD_TEMPFIELD2";
		jhadDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "JHAD_TEMPFIELD3";
		jhadDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "JHAD_TEMPFIELD4";
		jhadDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		jhadDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "JHAD_TEMPFIELD5";
		jhadDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		jhadDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JHAD_ACTIVE";
		jhadDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jhadDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JHAD_CREATEDBY";
		jhadDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jhadDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JHAD_CREATEDON";
		jhadDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jhadDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JHAD_MODIFIEDON";
		jhadDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_AUDITDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_AUDITDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_AUDITDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.jhauditmasterid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.jhauditmasterid.ordinal()] + "'";
		return sql;
	}

}

