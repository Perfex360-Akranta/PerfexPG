package com.akranta.tpm.dao.sql;

public class GenTlMmcmstSql {

	public static final String TBL_GEN_TL_MMCMST = "GEN_TL_MMCMST";  

	TableFieldType [] mmcnDbFields = null;

	public enum   tableFldConstants
	{
		keyid, menuid, formname, ismultipletable, module, isfunctionaltable
		, option, remarks, groupbyfield, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMmcnDbFields() {
		return mmcnDbFields;
	}

	public GenTlMmcmstSql()
	{
		mmcnDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			mmcnDbFields[ i ] = new TableFieldType();
		}
		mmcnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MMCN_KEYID";
		mmcnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mmcnDbFields[ tableFldConstants.menuid.ordinal() ].fieldName = "MMCN_MENUID";
		mmcnDbFields[ tableFldConstants.menuid.ordinal() ].fieldType = 'V';

		mmcnDbFields[ tableFldConstants.formname.ordinal() ].fieldName = "MMCN_FORMNAME";
		mmcnDbFields[ tableFldConstants.formname.ordinal() ].fieldType = 'V';

		mmcnDbFields[ tableFldConstants.ismultipletable.ordinal() ].fieldName = "MMCN_ISMULTIPLETABLE";
		mmcnDbFields[ tableFldConstants.ismultipletable.ordinal() ].fieldType = 'C';

		mmcnDbFields[ tableFldConstants.module.ordinal() ].fieldName = "MMCN_MODULE";
		mmcnDbFields[ tableFldConstants.module.ordinal() ].fieldType = 'V';

		mmcnDbFields[ tableFldConstants.isfunctionaltable.ordinal() ].fieldName = "MMCN_ISFUNCTIONALTABLE";
		mmcnDbFields[ tableFldConstants.isfunctionaltable.ordinal() ].fieldType = 'C';

		mmcnDbFields[ tableFldConstants.option.ordinal() ].fieldName = "MMCN_OPTION";
		mmcnDbFields[ tableFldConstants.option.ordinal() ].fieldType = 'V';

		mmcnDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MMCN_REMARKS";
		mmcnDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		mmcnDbFields[ tableFldConstants.groupbyfield.ordinal() ].fieldName = "MMCN_GROUPBYFIELD";
		mmcnDbFields[ tableFldConstants.groupbyfield.ordinal() ].fieldType = 'V';

		mmcnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MMCN_ACTIVE";
		mmcnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mmcnDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MMCN_CREATEDBY";
		mmcnDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mmcnDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MMCN_CREATEDON";
		mmcnDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mmcnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MMCN_MODIFIEDON";
		mmcnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MMCMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MMCMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MMCMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSelectSqlForMenu(){
		return "SELECT * FROM " + TBL_GEN_TL_MMCMST + " WHERE MMCN_MENUID = ? " ;
	}
	
}

