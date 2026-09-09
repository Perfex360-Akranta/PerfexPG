package com.akranta.tpm.dao.sql;

public class DailyActivityMstSql {

	public static final String TBL_OTH_TL_DAILY_ACTIVITY_MST = "OTH_TL_DAILY_ACTIVITY_MST";  
	//OTH_TL_DAILY_ACTIVITY_MST TABLE NAmE
	//public static final String fff="hh";
	TableFieldType [] dailyDbFeilds = null;

	public enum   tableFldConstants
	{
		keyid,resource,
		code,suptype,date,tskdes,isstyp,issloc,issrespn,issstas,target,tmespnt,soln,active,creaby,creadon,midifby,modifon
	
	}

	public TableFieldType[] getDailyDbFeilds() {
		return dailyDbFeilds;
	}

	public DailyActivityMstSql()
	{
		dailyDbFeilds = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			dailyDbFeilds[ i ] = new TableFieldType();
		}
		dailyDbFeilds[ tableFldConstants.keyid.ordinal() ].fieldName = "OTH_TL_ID";//REAL TABLE COLUMNS NAMES
		dailyDbFeilds[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';// V FOR VARCHAR 

		dailyDbFeilds[ tableFldConstants.resource.ordinal() ].fieldName = "OTH_TL_RESOURCE";
		dailyDbFeilds[ tableFldConstants.resource.ordinal() ].fieldType = 'V';

		
		dailyDbFeilds[ tableFldConstants.code.ordinal() ].fieldName = "OTH_TL_CODE";
		dailyDbFeilds[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.suptype.ordinal() ].fieldName = "OTH_TL_SUPPORTTYPE";
		dailyDbFeilds[ tableFldConstants.suptype.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.date.ordinal() ].fieldName = "OTH_TL_DATE";
		dailyDbFeilds[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		dailyDbFeilds[ tableFldConstants.tskdes.ordinal() ].fieldName = "OTH_TL_TASKDSC";
		dailyDbFeilds[ tableFldConstants.tskdes.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.isstyp.ordinal() ].fieldName = "OTH_TL_ISSUETYPE";
		dailyDbFeilds[ tableFldConstants.isstyp.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.issloc.ordinal() ].fieldName = "OTH_TL_ISSUELOCN";
		dailyDbFeilds[ tableFldConstants.issloc.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.issrespn.ordinal() ].fieldName = "OTH_TL_ISSUERESPON";
		dailyDbFeilds[ tableFldConstants.issrespn.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.issstas.ordinal() ].fieldName = "OTH_TL_ISSUESTATUS";
		dailyDbFeilds[ tableFldConstants.issstas.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.target.ordinal() ].fieldName = "OTH_TL_TARGETDATE";
		dailyDbFeilds[ tableFldConstants.target.ordinal() ].fieldType = 'D';

		dailyDbFeilds[ tableFldConstants.tmespnt.ordinal() ].fieldName = "OTH_TL_TIMESPENT";
		dailyDbFeilds[ tableFldConstants.tmespnt.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.soln.ordinal() ].fieldName = "OTH_TL_SOLUTION";
		dailyDbFeilds[ tableFldConstants.soln.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.active.ordinal() ].fieldName = "OTH_TL_ACTIVE";
		dailyDbFeilds[ tableFldConstants.active.ordinal() ].fieldType = 'V';

         dailyDbFeilds[ tableFldConstants.creaby.ordinal() ].fieldName = "OTH_TL_CREATEBY";
		dailyDbFeilds[ tableFldConstants.creaby.ordinal() ].fieldType = 'V';

        dailyDbFeilds[ tableFldConstants.creadon.ordinal() ].fieldName = "OTH_TL_CREATEDON";
		dailyDbFeilds[ tableFldConstants.creadon.ordinal() ].fieldType = 'D';

		dailyDbFeilds[ tableFldConstants.midifby.ordinal() ].fieldName = "OTH_TL_MODIFIEDBY";
		dailyDbFeilds[ tableFldConstants.midifby.ordinal() ].fieldType = 'V';

		dailyDbFeilds[ tableFldConstants.modifon.ordinal() ].fieldName = "OTH_TL_MODIFIEDON";
		dailyDbFeilds[ tableFldConstants.modifon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] dailyDbFeilds, Object [] dataArray)//data means model data//dailyDbFeilds means coumns
	{
		
		return SqlUtils.getInsertSql(TBL_OTH_TL_DAILY_ACTIVITY_MST, dailyDbFeilds, dataArray);
	}


	
		public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_OTH_TL_DAILY_ACTIVITY_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SUSABEHAVIORMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.behaviorkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.behaviorkeyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		
		String sql = "SELECT * FROM GEN_TL_SUSABEHAVIORMST WHERE SUSB_KEYID = ?";
		return sql;
	}
*/
}

