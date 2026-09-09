package com.akranta.tpm.dao.sql;

public class GenTlMmcdtlSql {

	public static final String TBL_GEN_TL_MMCDTL = "GEN_TL_MMCDTL";  

	TableFieldType [] mscnDbFields = null;

	public enum   tableFldConstants
	{
		keyid, masterid, tablename, columnname, displayname, selectiontype
		, popuptablename, popupcolumnname, popuptablekeyid, iscomboselection
		, combodisplayname, combosaveinfo, savingorder, ismandatory, nonmandatoryvalue
		, columntobedisplayed, columndisplayorder, autogenerationid, autogenerationname
		, conditiontable, conditionfield, conditionvalue, defaulttable
		, defaultcondition, defaultcheck, condfield, conditionno, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMscnDbFields() {
		return mscnDbFields;
	}

	public GenTlMmcdtlSql()
	{
		mscnDbFields = new TableFieldType[ 31 ];
		for(int i = 0;i < 31; i++)
		{	
			mscnDbFields[ i ] = new TableFieldType();
		}
		mscnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MSCN_KEYID";
		mscnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "MSCN_MASTERID";
		mscnDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.tablename.ordinal() ].fieldName = "MSCN_TABLENAME";
		mscnDbFields[ tableFldConstants.tablename.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.columnname.ordinal() ].fieldName = "MSCN_COLUMNNAME";
		mscnDbFields[ tableFldConstants.columnname.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.displayname.ordinal() ].fieldName = "MSCN_DISPLAYNAME";
		mscnDbFields[ tableFldConstants.displayname.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.selectiontype.ordinal() ].fieldName = "MSCN_SELECTIONTYPE";
		mscnDbFields[ tableFldConstants.selectiontype.ordinal() ].fieldType = 'C';

		mscnDbFields[ tableFldConstants.popuptablename.ordinal() ].fieldName = "MSCN_POPUPTABLENAME";
		mscnDbFields[ tableFldConstants.popuptablename.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.popupcolumnname.ordinal() ].fieldName = "MSCN_POPUPCOLUMNNAME";
		mscnDbFields[ tableFldConstants.popupcolumnname.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.popuptablekeyid.ordinal() ].fieldName = "MSCN_POPUPTABLEKEYID";
		mscnDbFields[ tableFldConstants.popuptablekeyid.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.iscomboselection.ordinal() ].fieldName = "MSCN_ISCOMBOSELECTION";
		mscnDbFields[ tableFldConstants.iscomboselection.ordinal() ].fieldType = 'C';

		mscnDbFields[ tableFldConstants.combodisplayname.ordinal() ].fieldName = "MSCN_COMBODISPLAYNAME";
		mscnDbFields[ tableFldConstants.combodisplayname.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.combosaveinfo.ordinal() ].fieldName = "MSCN_COMBOSAVEINFO";
		mscnDbFields[ tableFldConstants.combosaveinfo.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.savingorder.ordinal() ].fieldName = "MSCN_SAVINGORDER";
		mscnDbFields[ tableFldConstants.savingorder.ordinal() ].fieldType = 'N';

		mscnDbFields[ tableFldConstants.ismandatory.ordinal() ].fieldName = "MSCN_ISMANDATORY";
		mscnDbFields[ tableFldConstants.ismandatory.ordinal() ].fieldType = 'C';

		mscnDbFields[ tableFldConstants.nonmandatoryvalue.ordinal() ].fieldName = "MSCN_NONMANDATORYVALUE";
		mscnDbFields[ tableFldConstants.nonmandatoryvalue.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.columntobedisplayed.ordinal() ].fieldName = "MSCN_COLUMNTOBEDISPLAYED";
		mscnDbFields[ tableFldConstants.columntobedisplayed.ordinal() ].fieldType = 'C';

		mscnDbFields[ tableFldConstants.columndisplayorder.ordinal() ].fieldName = "MSCN_COLUMNDISPLAYORDER";
		mscnDbFields[ tableFldConstants.columndisplayorder.ordinal() ].fieldType = 'N';

		mscnDbFields[ tableFldConstants.autogenerationid.ordinal() ].fieldName = "MSCN_AUTOGENERATIONID";
		mscnDbFields[ tableFldConstants.autogenerationid.ordinal() ].fieldType = 'C';

		mscnDbFields[ tableFldConstants.autogenerationname.ordinal() ].fieldName = "MSCN_AUTOGENERATIONNAME";
		mscnDbFields[ tableFldConstants.autogenerationname.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.conditiontable.ordinal() ].fieldName = "MSCN_CONDITIONTABLE";
		mscnDbFields[ tableFldConstants.conditiontable.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.conditionfield.ordinal() ].fieldName = "MSCN_CONDITIONFIELD";
		mscnDbFields[ tableFldConstants.conditionfield.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.conditionvalue.ordinal() ].fieldName = "MSCN_CONDITIONVALUE";
		mscnDbFields[ tableFldConstants.conditionvalue.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.defaulttable.ordinal() ].fieldName = "MSCN_DEFAULTTABLE";
		mscnDbFields[ tableFldConstants.defaulttable.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.defaultcondition.ordinal() ].fieldName = "MSCN_DEFAULTCONDITION";
		mscnDbFields[ tableFldConstants.defaultcondition.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.defaultcheck.ordinal() ].fieldName = "MSCN_DEFAULTCHECK";
		mscnDbFields[ tableFldConstants.defaultcheck.ordinal() ].fieldType = 'C';

		mscnDbFields[ tableFldConstants.condfield.ordinal() ].fieldName = "MSCN_CONDFIELD";
		mscnDbFields[ tableFldConstants.condfield.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.conditionno.ordinal() ].fieldName = "MSCN_CONDITIONNO";
		mscnDbFields[ tableFldConstants.conditionno.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MSCN_ACTIVE";
		mscnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mscnDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MSCN_CREATEDBY";
		mscnDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mscnDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MSCN_CREATEDON";
		mscnDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mscnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MSCN_MODIFIEDON";
		mscnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MMCDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MMCDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MMCDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSelectSql(String masterId){
		return "SELECT * FROM " + TBL_GEN_TL_MMCDTL + " WHERE MSCN_MASTERID = '" + masterId + "' order by MSCN_COLUMNDISPLAYORDER " ;
	}
}

