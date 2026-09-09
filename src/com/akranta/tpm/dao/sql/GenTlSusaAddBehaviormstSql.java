package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlSusaAddBehaviormstSql {

	public static final String TBL_GEN_TL_SUSAADDBEHAVIOURMST = "GEN_TL_SUSAADDBEHAVIOURMST";  

	TableFieldType [] suabDbFields = null;

	public enum   tableFldConstants
	{  
		keyid,susn_keyid,flid,type,behavcategory,behavior,cause,action,probability,consequence,
		remarks,image,actionplanid,tempfield1,tempfield2,tempfield3,tempfield4,
		tempfield5,createdby,active,createdon,modifiedon
	}

	public TableFieldType[] getsuabDbFields() {
		return suabDbFields;
	}

	public GenTlSusaAddBehaviormstSql()
	{
		suabDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			suabDbFields[ i ] = new TableFieldType();
		}
		suabDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SUAB_KEYID";
		suabDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.susn_keyid.ordinal() ].fieldName = "SUAB_SUSN_KEYID";
		suabDbFields[ tableFldConstants.susn_keyid.ordinal() ].fieldType = 'V';
		
		suabDbFields[ tableFldConstants.type.ordinal() ].fieldName = "SUAB_TYPE";
		suabDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';
		
		suabDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SUAB_FLID";
		suabDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'C';
        
		suabDbFields[ tableFldConstants.behavcategory.ordinal() ].fieldName = "SUAB_BEHAVCATEGORY";
		suabDbFields[ tableFldConstants.behavcategory.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.behavior.ordinal() ].fieldName = "SUAB_BEHAVIOUR";
		suabDbFields[ tableFldConstants.behavior.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.cause.ordinal() ].fieldName = "SUAB_CAUSE";
		suabDbFields[ tableFldConstants.cause.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.action.ordinal() ].fieldName = "SUAB_ACTION";
		suabDbFields[ tableFldConstants.action.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.probability.ordinal() ].fieldName = "SUAB_PROBABILITY";
		suabDbFields[ tableFldConstants.probability.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.consequence.ordinal() ].fieldName = "SUAB_CONSEQUENCE";
		suabDbFields[ tableFldConstants.consequence.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SUAB_REMARKS";
		suabDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.image.ordinal() ].fieldName = "SUAB_IMAGE";
		suabDbFields[ tableFldConstants.image.ordinal() ].fieldType = 'V';
		
		suabDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldName = "SUAB_ACTIONPLAN_ID";
		suabDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SUAB_TEMPFIELD1";
		suabDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SUAB_TEMPFIELD2";
		suabDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SUAB_TEMPFIELD3";
		suabDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SUAB_TEMPFIELD4";
		suabDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SUAB_TEMPFIELD5";
		suabDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
        
		suabDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SUAB_CREATEDBY";
		suabDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		suabDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SUAB_ACTIVE";
		suabDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		suabDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SUAB_CREATEDON";
		suabDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		suabDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SUAB_MODIFIEDON";
		suabDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SUSAADDBEHAVIOURMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SUSAADDBEHAVIOURMST, fieldTypeArr, dataArray);
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SUSAADDBEHAVIOURMST;
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String DeleteSusaBehaviour(String keyid)
	{
		// TODO Auto-generated method stub
		String sql=" DELETE FROM " + TBL_GEN_TL_SUSAADDBEHAVIOURMST + " WHERE SUAB_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM TBL_GEN_TL_SUSAADDBEHAVIOURMST WHERE SUSB_KEYID = ?";
		return sql;
	}
    public static String UpdateActionplan(String susaid,String aplKeyid) throws Exception{
      return "UPDATE GEN_TL_SUSAADDBEHAVIOURMST SET SUAB_ACTIONPLAN_ID='"+aplKeyid+"' WHERE SUAB_SUSN_KEYID='"+susaid+"' ";	
    }
}

