package com.akranta.tpm.dao.sql;

public class PcsTlLossphenfactorylinkSql {

	public static final String TBL_PCS_TL_LOSSPHENFACTORYLINK = "PCS_TL_LOSSPHENFACTORYLINK";  

	static TableFieldType [] ppflDbFields = null;

	public enum   tableFldConstants
	{
		keyid, plpm_keyid, factoryid, tempfield1, tempfield2, active
		, createdby, createdon, modifiedon
	}

	public static TableFieldType[] getPpflDbFields() {
		return ppflDbFields;
	}

	public PcsTlLossphenfactorylinkSql()
	{
		ppflDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			ppflDbFields[ i ] = new TableFieldType();
		}
		ppflDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PPFL_KEYID";
		ppflDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ppflDbFields[ tableFldConstants.plpm_keyid.ordinal() ].fieldName = "PPFL_PLPM_KEYID";
		ppflDbFields[ tableFldConstants.plpm_keyid.ordinal() ].fieldType = 'V';

		ppflDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PPFL_FACTORYID";
		ppflDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		ppflDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PPFL_TEMPFIELD1";
		ppflDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		ppflDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PPFL_TEMPFIELD2";
		ppflDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		ppflDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PPFL_ACTIVE";
		ppflDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ppflDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PPFL_CREATEDBY";
		ppflDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ppflDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PPFL_CREATEDON";
		ppflDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ppflDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PPFL_MODIFIEDON";
		ppflDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	
	/*public static String getlossphenCnt(String keyid)
	{
		return "select count(*) from PCS_TL_LOSSPHENOMENAMST where PLPM_KEYID = '"+keyid+"'";
	}*/
	
	public static String getPillarID(String pillCode)
	{
		
		return "Select TPMP_KEYID from gen_tl_tpmpillarmst where TPMP_CODE='"+pillCode+"'";
	}
	
	public static String getPhenomenaLossFactoryInsertSql(){
		return " insert into TBL_PCS_TL_LOSSPHENFACTORYLINK values(?,?,?,'-','-','Y',?,sysdate,sysdate)";
	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_LOSSPHENFACTORYLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_LOSSPHENFACTORYLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String mstKeyID)
	{
		String sql = "DELETE from " + TBL_PCS_TL_LOSSPHENFACTORYLINK ;
		sql += " where PPFL_PLPM_KEYID='" + mstKeyID +"'";
			 
		return sql;
	}

}

