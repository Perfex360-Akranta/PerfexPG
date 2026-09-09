package com.akranta.tpm.dao.sql;

public class AdmTlLoginframeworkSql {

	public static final String TBL_ADM_TL_LOGINFRAMEWORK = "ADM_TL_LOGINFRAMEWORK";  

	TableFieldType [] lgfrDbFields = null;

	public enum   tableFldConstants
	{
		keyid, usernamelength, defsyspassword, minpasslength, maxpasslength
		, alphabets, numerals, passchangegap, passchangefreq, passgraceperiod
		, passintimation, passneverexpires, passhistoryremember, mincharpasschange
		, failedloginattempts, isloginaudit, ispassaudit, isprivaudit
		, createddate, ispassautogen,ispolicyactive,temp3,active,createdby, createdon, modifiedon
	}
	

	public TableFieldType[] getLgfrDbFields() {
		return lgfrDbFields;
	}

	public AdmTlLoginframeworkSql()
	{
		lgfrDbFields = new TableFieldType[ 26 ];
		for(int i = 0;i < 26; i++)
		{	
			lgfrDbFields[ i ] = new TableFieldType();
		}
		lgfrDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "LGFR_KEYID";
		lgfrDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		lgfrDbFields[ tableFldConstants.usernamelength.ordinal() ].fieldName = "LGFR_USERNAMELENGTH";
		lgfrDbFields[ tableFldConstants.usernamelength.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.defsyspassword.ordinal() ].fieldName = "LGFR_DEFSYSPASSWORD";
		lgfrDbFields[ tableFldConstants.defsyspassword.ordinal() ].fieldType = 'V';

		lgfrDbFields[ tableFldConstants.minpasslength.ordinal() ].fieldName = "LGFR_MINPASSLENGTH";
		lgfrDbFields[ tableFldConstants.minpasslength.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.maxpasslength.ordinal() ].fieldName = "LGFR_MAXPASSLENGTH";
		lgfrDbFields[ tableFldConstants.maxpasslength.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.alphabets.ordinal() ].fieldName = "LGFR_ALPHABETS";
		lgfrDbFields[ tableFldConstants.alphabets.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.numerals.ordinal() ].fieldName = "LGFR_NUMERALS";
		lgfrDbFields[ tableFldConstants.numerals.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.passchangegap.ordinal() ].fieldName = "LGFR_PASSCHANGEGAP";
		lgfrDbFields[ tableFldConstants.passchangegap.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.passchangefreq.ordinal() ].fieldName = "LGFR_PASSCHANGEFREQ";
		lgfrDbFields[ tableFldConstants.passchangefreq.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.passgraceperiod.ordinal() ].fieldName = "LGFR_PASSGRACEPERIOD";
		lgfrDbFields[ tableFldConstants.passgraceperiod.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.passintimation.ordinal() ].fieldName = "LGFR_PASSINTIMATION";
		lgfrDbFields[ tableFldConstants.passintimation.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.passneverexpires.ordinal() ].fieldName = "LGFR_PASSNEVEREXPIRES";
		lgfrDbFields[ tableFldConstants.passneverexpires.ordinal() ].fieldType = 'C';

		lgfrDbFields[ tableFldConstants.passhistoryremember.ordinal() ].fieldName = "LGFR_PASSHISTORYREMEMBER";
		lgfrDbFields[ tableFldConstants.passhistoryremember.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.mincharpasschange.ordinal() ].fieldName = "LGFR_MINCHARPASSCHANGE";
		lgfrDbFields[ tableFldConstants.mincharpasschange.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.failedloginattempts.ordinal() ].fieldName = "LGFR_FAILEDLOGINATTEMPTS";
		lgfrDbFields[ tableFldConstants.failedloginattempts.ordinal() ].fieldType = 'N';

		lgfrDbFields[ tableFldConstants.isloginaudit.ordinal() ].fieldName = "LGFR_ISLOGINAUDIT";
		lgfrDbFields[ tableFldConstants.isloginaudit.ordinal() ].fieldType = 'C';

		lgfrDbFields[ tableFldConstants.ispassaudit.ordinal() ].fieldName = "LGFR_ISPASSAUDIT";
		lgfrDbFields[ tableFldConstants.ispassaudit.ordinal() ].fieldType = 'C';

		lgfrDbFields[ tableFldConstants.isprivaudit.ordinal() ].fieldName = "LGFR_ISPRIVAUDIT";
		lgfrDbFields[ tableFldConstants.isprivaudit.ordinal() ].fieldType = 'C';

		lgfrDbFields[ tableFldConstants.createddate.ordinal() ].fieldName = "LGFR_CREATEDDATE";
		lgfrDbFields[ tableFldConstants.createddate.ordinal() ].fieldType = 'D';
		
		lgfrDbFields[ tableFldConstants.ispassautogen.ordinal() ].fieldName = "LGFR_ISPASSAUTOGEN";
		lgfrDbFields[ tableFldConstants.ispassautogen.ordinal() ].fieldType = 'C';
		
		lgfrDbFields[ tableFldConstants.ispolicyactive.ordinal() ].fieldName = "LGFR_ISPOLICYACTIVE";
		lgfrDbFields[ tableFldConstants.ispolicyactive.ordinal() ].fieldType = 'C';
		
		lgfrDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "LGFR_TEMPFIELD3";
		lgfrDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'C';
		
		lgfrDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "LGFR_TEMPFIELD3";
		lgfrDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'C';
		
		lgfrDbFields[ tableFldConstants.active.ordinal() ].fieldName = "LGFR_ACTIVE";
		lgfrDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		lgfrDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "LGFR_CREATEDBY";
		lgfrDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		lgfrDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "LGFR_CREATEDON";
		lgfrDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		lgfrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "LGFR_MODIFIEDON";
		lgfrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_LOGINFRAMEWORK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_LOGINFRAMEWORK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_LOGINFRAMEWORK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getsingledata() {
		String sql = "select *  from ADM_TL_LOGINFRAMEWORK  where LGFR_ACTIVE = ? " ;
		return sql;
	}

}

