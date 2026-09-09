package com.akranta.tpm.dao.sql;

public class GenTlSusamstNewSql {
	public static final String TBL_GEN_TL_SUSAMSTNEW = "GEN_TL_SUSAMSTNEW";  
	TableFieldType [] susnDbFields = null;
	public enum   tableFldConstants{
		keyid,flid,date,discussiontype,discussionno,preparedby,personrole,
		discussionsumm,otherparticipants,susadoneJh,susadoneby,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}
  
	public TableFieldType[] getSusnDbFields() {
		return susnDbFields;
	}

	public GenTlSusamstNewSql()
	{
		susnDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			susnDbFields[ i ] = new TableFieldType();
		}
		susnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SUSN_KEYID";
		susnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SUSN_FLID";
		susnDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		
		susnDbFields[ tableFldConstants.date.ordinal() ].fieldName = "SUSN_DATE";
		susnDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		susnDbFields[ tableFldConstants.discussiontype.ordinal() ].fieldName = "SUSN_DISCUSSIONTYPE";
		susnDbFields[ tableFldConstants.discussiontype.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.discussionno.ordinal() ].fieldName = "SUSN_DISCUSSIONNO";
		susnDbFields[ tableFldConstants.discussionno.ordinal() ].fieldType = 'N';
		
		susnDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "SUSN_PREPAREDBY";
		susnDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.personrole.ordinal() ].fieldName = "SUSN_PERSONROLE";
		susnDbFields[ tableFldConstants.personrole.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.discussionsumm.ordinal() ].fieldName = "SUSN_DISCUSSIONSUMM";
		susnDbFields[ tableFldConstants.discussionsumm.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.otherparticipants.ordinal() ].fieldName ="SUSN_OTHERPARTICIPANTS";
		susnDbFields[ tableFldConstants.otherparticipants.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.susadoneJh.ordinal() ].fieldName = "SUSN_SUSADONEJH";
		susnDbFields[ tableFldConstants.susadoneJh.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.susadoneby.ordinal() ].fieldName = "SUSN_SUSADONEBY";
		susnDbFields[ tableFldConstants.susadoneby.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SUSN_TEMPFIELD3";
		susnDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SUSN_TEMPFIELD4";
		susnDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
        
		susnDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SUSN_TEMPFIELD5";
		susnDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
		
		susnDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SUSN_CREATEDBY";
		susnDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		susnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SUSN_ACTIVE";
		susnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		susnDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SUSN_CREATEDON";
		susnDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		susnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SUSN_MODIFIEDON";
		susnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SUSAMSTNEW, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SUSAMSTNEW, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SUSAMSTNEW ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
  public static String getMasterDataSql()throws Exception{
	  return "SELECT * FROM " + TBL_GEN_TL_SUSAMSTNEW + " WHERE SUSN_KEYID= ?";
  }
}

