package com.akranta.tpm.dao.sql;


import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlMomKpiLinkSql {

	public static final String TBL_GEN_TL_MOM_KPI_LINK = "GEN_TL_MOM_KPI_LINK";  

	TableFieldType [] mokpDbFields = null;

	public enum   tableFldConstants
	{
		keyid, moms_keyid, momd_keyid, kink_keyid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getMokpDbFields() {
		return mokpDbFields;
	}

	public GenTlMomKpiLinkSql()
	{
		mokpDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			mokpDbFields[ i ] = new TableFieldType();
		}
		mokpDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOKP_KEYID";
		mokpDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.moms_keyid.ordinal() ].fieldName = "MOKP_MOMS_KEYID";
		mokpDbFields[ tableFldConstants.moms_keyid.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.momd_keyid.ordinal() ].fieldName = "MOKP_MOMD_KEYID";
		mokpDbFields[ tableFldConstants.momd_keyid.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.kink_keyid.ordinal() ].fieldName = "MOKP_KINK_KEYID";
		mokpDbFields[ tableFldConstants.kink_keyid.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOKP_TEMPFIELD1";
		mokpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOKP_TEMPFIELD2";
		mokpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOKP_TEMPFIELD3";
		mokpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOKP_TEMPFIELD4";
		mokpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOKP_TEMPFIELD5";
		mokpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOKP_ACTIVE";
		mokpDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mokpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOKP_CREATEDBY";
		mokpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mokpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOKP_CREATEDON";
		mokpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mokpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOKP_MODIFIEDON";
		mokpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MOM_KPI_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MOM_KPI_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MOM_KPI_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteSqlKpi(String momdKeyid) {
		// TODO Auto-generated method stub
        String sql = "DELETE from " + TBL_GEN_TL_MOM_KPI_LINK ;
		
		sql += " where MOKP_MOMD_KEYID='" +momdKeyid+"'";
		return sql;    
	}

	public static String DeleteMomRow(String keyid) {
		// TODO Auto-generated method stub
		String sql=" DELETE FROM " + TBL_GEN_TL_MOM_KPI_LINK  + " WHERE MOKP_MOMD_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql KPI : "+sql);
		return sql;
	}

}

