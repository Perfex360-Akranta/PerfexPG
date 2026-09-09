package com.akranta.tpm.dao.sql;

public class GenTlDmcfipworkflowSql {

	public static final String TBL_GEN_TL_DMCFIPWORKFLOW = "GEN_TL_DMCFIPWORKFLOW";  

	TableFieldType [] dfiwDbFields = null;

	public enum   tableFldConstants
	{
		keyid, fipno, fipdate, projectleader, pbuhead, kkchampion, financehead
		, fipstage, approvalstatus, statusmessage, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedby
		, modifiedon
	}

	public TableFieldType[] getDfiwDbFields() {
		return dfiwDbFields;
	}

	public GenTlDmcfipworkflowSql()
	{
		dfiwDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			dfiwDbFields[ i ] = new TableFieldType();
		}
		dfiwDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DFIW_KEYID";
		dfiwDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.fipno.ordinal() ].fieldName = "DFIW_FIPNO";
		dfiwDbFields[ tableFldConstants.fipno.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.fipdate.ordinal() ].fieldName = "DFIW_FIPDATE";
		dfiwDbFields[ tableFldConstants.fipdate.ordinal() ].fieldType = 'D';

		dfiwDbFields[ tableFldConstants.projectleader.ordinal() ].fieldName = "DFIW_PROJECTLEADER";
		dfiwDbFields[ tableFldConstants.projectleader.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.pbuhead.ordinal() ].fieldName = "DFIW_PBUHEAD";
		dfiwDbFields[ tableFldConstants.pbuhead.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.kkchampion.ordinal() ].fieldName = "DFIW_KKCHAMPION";
		dfiwDbFields[ tableFldConstants.kkchampion.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.financehead.ordinal() ].fieldName = "DFIW_FINANCEHEAD";
		dfiwDbFields[ tableFldConstants.financehead.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.fipstage.ordinal() ].fieldName = "DFIW_FIPSTAGE";
		dfiwDbFields[ tableFldConstants.fipstage.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.approvalstatus.ordinal() ].fieldName = "DFIW_APPROVALSTATUS";
		dfiwDbFields[ tableFldConstants.approvalstatus.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.statusmessage.ordinal() ].fieldName = "DFIW_STATUSMESSAGE";
		dfiwDbFields[ tableFldConstants.statusmessage.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "DFIW_TEMPFIELD1";
		dfiwDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DFIW_TEMPFIELD2";
		dfiwDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DFIW_TEMPFIELD3";
		dfiwDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DFIW_TEMPFIELD4";
		dfiwDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DFIW_ACTIVE";
		dfiwDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DFIW_CREATEDBY";
		dfiwDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DFIW_CREATEDON";
		dfiwDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dfiwDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "DFIW_MODIFIEDBY";
		dfiwDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		dfiwDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DFIW_MODIFIEDON";
		dfiwDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_DMCFIPWORKFLOW, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_DMCFIPWORKFLOW, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_DMCFIPWORKFLOW ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getwrkflpRecall() {
		StringBuilder sql = new StringBuilder(" select dfiw_keyid,dfiw_fipno,dfiw_fipdate,dfiw_projectleader,dfiw_pbuhead,dfiw_kkchampion,dfiw_financehead,dfiw_fipstage, ");
		sql.append(" dfiw_approvalstatus,dfiw_statusmessage,dfiw_tempfield1,dfiw_tempfield2,dfiw_tempfield3,dfiw_tempfield4, ");
		sql.append(" DFIW_ACTIVE,DFIW_CREATEDBY,DFIW_CREATEDON,DFIW_MODIFIEDBY,DFIW_MODIFIEDON FROM GEN_TL_DMCFIPWORKFLOW WHERE dfiw_keyid= ? ");
		return sql.toString();
	}
	public String getdmcwrkflpRecall() {
		StringBuilder sql = new StringBuilder(" select dfiw_keyid,dfiw_fipno,dfiw_fipdate,dfiw_projectleader,dfiw_pbuhead,dfiw_kkchampion,dfiw_financehead,dfiw_fipstage, ");
		sql.append(" dfiw_approvalstatus,dfiw_statusmessage,dfiw_tempfield1,dfiw_tempfield2,dfiw_tempfield3,dfiw_tempfield4, ");
		sql.append(" DFIW_ACTIVE,DFIW_CREATEDBY,DFIW_CREATEDON,DFIW_MODIFIEDBY,DFIW_MODIFIEDON FROM GEN_TL_DMCFIPWORKFLOW WHERE dfiw_fipno= ? ");
		return sql.toString();
	}

}

