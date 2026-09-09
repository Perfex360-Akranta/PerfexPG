package com.akranta.tpm.dao.sql;


public class KznTlCategorymstSql {

	public static final String TBL_KZN_TL_CATEGORYMST = "KZN_TL_CATEGORYMST";  

	TableFieldType [] kctmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, tpmpillarid, name, code, description, remarks, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getKctmDbFields() {
		return kctmDbFields;
	}

	public KznTlCategorymstSql()
	{
		kctmDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			kctmDbFields[ i ] = new TableFieldType();
		}
		kctmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KCTM_KEYID";
		kctmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kctmDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldName = "KCTM_TPMPILLARID";
		kctmDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldType = 'V';

		kctmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "KCTM_NAME";
		kctmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		kctmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "KCTM_CODE";
		kctmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		kctmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "KCTM_DESCRIPTION";
		kctmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		kctmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "KCTM_REMARKS";
		kctmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		kctmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KCTM_ACTIVE";
		kctmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kctmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KCTM_CREATEDBY";
		kctmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kctmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KCTM_CREATEDON";
		kctmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kctmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KCTM_MODIFIEDON";
		kctmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_CATEGORYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_CATEGORYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_CATEGORYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getKznPillarSql(){
		return  "select  TPMP_KEYID,TPMP_NAME,TPMP_CODE,'False' as \"select\" from " + TableNames.TBL_OPL_TL_PLR + " WHERE TPMP_ACTIVE = 'Y' ";
	}
	
	public static String getKznCategorySql(String categoryKeyid){
		
		return  "select * from " + TableNames.TBL_KZN_TL_CATEGORYMST + " where KCTM_TPMPILLARID = '" + categoryKeyid + "'";

	}
	
	public static String deleteKznCategorySql()
	{
		return  "delete from " + TableNames.TBL_KZN_TL_CATEGORYMST + " where KCTM_KEYID = ? ";
	}
	
	public static String getKznSubCategorySql( String ksmKeyId){
		return  " SELECT DISTINCT KSCM_KEYID,KSCM_SUBCATNAME,KSCM_SUBCATCODE  FROM " + TableNames.TBL_KZN_TL_SUBCATEGORYMST + " WHERE KSCM_KCTM_KEYID = '" + ksmKeyId + "'" ;//+ " ORDER BY KSCM_SLNO ";
	}
	
	public static String updateSubcategoriesSql()
	{
		StringBuffer sql= new StringBuffer();
		sql.append("UPDATE KZN_TL_SUBCATEGORYMST SET KSCM_SLNO = KSCM_SLNO - 1 ");
		sql.append("WHERE KSCM_SLNO  > ( SELECT KSCM_SLNO SLNO  FROM KZN_TL_SUBCATEGORYMST ");
		sql.append("WHERE  KSCM_KEYID = ? ) AND KSCM_KCTM_KEYID =  ?  ");
		
		return sql.toString();
		
		//return "UPDATE KZN_TL_SUBCATEGORYMST SET KSCM_SLNO = KSCM_SLNO-1 where KSCM_SLNO> "+gridRowid+" and KSCM_KCTM_KEYID = '"+kscmKctmKeyid+"' ";
	}


}

