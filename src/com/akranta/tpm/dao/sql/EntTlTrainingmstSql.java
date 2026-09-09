package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTrainingmstSql {

	public static final String TBL_ENT_TL_TRAININGMST = "ENT_TL_TRAININGMST";  

	TableFieldType [] tmstDbFields = null;

	public enum   tableFldConstants
	{
		keyid, title, date, duration, faculty, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, flid, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTmstDbFields() {
		return tmstDbFields;
	}

	public EntTlTrainingmstSql()
	{
		tmstDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			tmstDbFields[ i ] = new TableFieldType();
		}
		tmstDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TMST_KEYID";
		tmstDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tmstDbFields[ tableFldConstants.title.ordinal() ].fieldName = "TMST_TITLE";
		tmstDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		tmstDbFields[ tableFldConstants.date.ordinal() ].fieldName = "TMST_DATE";
		tmstDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		tmstDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "TMST_DURATION";
		tmstDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		tmstDbFields[ tableFldConstants.faculty.ordinal() ].fieldName = "TMST_FACULTY";
		tmstDbFields[ tableFldConstants.faculty.ordinal() ].fieldType = 'V';

		tmstDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TMST_TEMPFIELD1";
		tmstDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		tmstDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TMST_TEMPFIELD2";
		tmstDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		tmstDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TMST_TEMPFIELD3";
		tmstDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		tmstDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TMST_TEMPFIELD4";
		tmstDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		tmstDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TMST_TEMPFIELD5";
		tmstDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		tmstDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "TMST_TEMPFIELD6";
		tmstDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		tmstDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TMST_FLID";
		tmstDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		tmstDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TMST_ACTIVE";
		tmstDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tmstDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TMST_CREATEDBY";
		tmstDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tmstDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TMST_CREATEDON";
		tmstDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tmstDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TMST_MODIFIEDON";
		tmstDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRAININGMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRAININGMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRAININGMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String TrainingGrid(CommonFilter commonFilter) 
	{
		CommonMessage.debugMsg("in momGrid ");
		String sql=" select 'Course Content' ,'Poor','Fair','Good','VeryGood','Excellent' From DUAL ";
		sql+=" UNION  ALL  select 'Using the scale, please rate how effective this training was in reference to the following:','1','2','3','4','5' From Dual";
	    sql+=" UNION ALL select FBPM_DESCRIPTION ,'','','','','' from ENT_TL_FEEDBACKPARAMMST where FBPM_TYPE='CP'";
	    CommonMessage.debugMsg("IN TrainingGrid::"+sql);     
		return sql.toString();
	}

	public static String getTrainingGridIn(CommonFilter commonFilter) 
	{CommonMessage.debugMsg("in momGrid ");
	String sql=" select 'Instructor�s Performance' ,'Poor','Fair','Good','VeryGood','Excellent' From DUAL ";
	sql+=" UNION  ALL  select 'Using the scale, please rate how effective instructor performance was in reference to the following:','1','2','3','4','5' From Dual";
    sql+=" UNION ALL select FBPM_DESCRIPTION ,'','','','','' from ENT_TL_FEEDBACKPARAMMST where FBPM_TYPE='IP'";
    CommonMessage.debugMsg("IN TrainingGrid::"+sql);     
	return sql.toString();
	}

	public static String getTrainingSelectSql()
	{
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ENT_TL_TRAININGMST + " where TMST_KEYID= ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

}

