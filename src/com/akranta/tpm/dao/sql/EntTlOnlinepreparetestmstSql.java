package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;

public class EntTlOnlinepreparetestmstSql {

	public static final String TBL_ENT_TL_ONLINEPREPARETESTMST = "ENT_TL_ONLINEPREPARETESTMST";  

	TableFieldType [] olpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, programid, topicid, validity, noofquestion, testnumber
		, description, totalmarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, duration
		, modifiedon
	}

	public TableFieldType[] getOlpmDbFields() {
		return olpmDbFields;
	}

	public EntTlOnlinepreparetestmstSql()
	{
		olpmDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			olpmDbFields[ i ] = new TableFieldType();
		}
		olpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OLPM_KEYID";
		olpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		olpmDbFields[ tableFldConstants.programid.ordinal() ].fieldName = "OLPM_PROGRAMID";
		olpmDbFields[ tableFldConstants.programid.ordinal() ].fieldType = 'V';

		olpmDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "OLPM_TOPICID";
		olpmDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		olpmDbFields[ tableFldConstants.validity.ordinal() ].fieldName = "OLPM_VALIDITY";
		olpmDbFields[ tableFldConstants.validity.ordinal() ].fieldType = 'D';

		olpmDbFields[ tableFldConstants.noofquestion.ordinal() ].fieldName = "OLPM_NOOFQUESTION";
		olpmDbFields[ tableFldConstants.noofquestion.ordinal() ].fieldType = 'N';

		olpmDbFields[ tableFldConstants.testnumber.ordinal() ].fieldName = "OLPM_TESTNUMBER";
		olpmDbFields[ tableFldConstants.testnumber.ordinal() ].fieldType = 'V';

		olpmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "OLPM_DESCRIPTION";
		olpmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		olpmDbFields[ tableFldConstants.totalmarks.ordinal() ].fieldName = "OLPM_TOTALMARKS";
		olpmDbFields[ tableFldConstants.totalmarks.ordinal() ].fieldType = 'N';

		olpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OLPM_TEMPFIELD1";
		olpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		olpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OLPM_TEMPFIELD2";
		olpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		olpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OLPM_TEMPFIELD3";
		olpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		olpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OLPM_TEMPFIELD4";
		olpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		olpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OLPM_TEMPFIELD5";
		olpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		olpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OLPM_CREATEDBY";
		olpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		olpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OLPM_ACTIVE";
		olpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		olpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OLPM_CREATEDON";
		olpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		olpmDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "OLPM_DURATION";
		olpmDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		olpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OLPM_MODIFIEDON";
		olpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ONLINEPREPARETESTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ONLINEPREPARETESTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ONLINEPREPARETESTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String  getAllDetailRecord(CommonFilter commonFilter) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT 'OLPM_KEYID' as olpmkeyid, 'Program' as programname, 'Topic' as topic, 'Validity' as validity, 'No Of Questions' as noofquestions,'Test No' as testno, 'Test Description' as testdescription, 'Total Marks' as totalmarks,'Duration' as duration, 1 dataorder  FROM DUAL union ");
		sql.append(" SELECT *  FROM (SELECT olpm_keyid as olpmkeyid,PROG_NAME as programname ,TOPI_NAME as topic , TO_CHAR (olpm_validity,'DD-MON-YYYY') as validity,TO_CHAR (olpm_noofquestion) as noofquestions, TO_CHAR (olpm_testnumber) as testno,olpm_description as testdescription, TO_CHAR (olpm_noofquestion) as totalmarks,to_char(OLPM_DURATION) as duration, 2 dataorder");
		sql.append(" FROM ent_tl_onlinepreparetestmst,ent_tl_topicmst,ent_tl_PROGRAMMST ,GEN_MV_FLIDHIERARCHY ");
		sql.append(" where olpm_topicid=TOPI_KEYID(+)  AND olpm_programid=PROG_KEYID(+) AND prog_trar_keyid = FLID AND INSTR(PARENTFLIDS||FLID,'"+commonFilter.getFlid()+"')>0   ) order by dataorder");
		return sql.toString();
	}

	public String getsql() {
		String sql="SELECT  E.OLPM_KEYID, E.OLPM_PROGRAMID, E.OLPM_TOPICID,to_char(E.OLPM_VALIDITY,'dd-Mon-YYYY')," +
				" E.OLPM_NOOFQUESTION, E.OLPM_TESTNUMBER,E.OLPM_DESCRIPTION, E.OLPM_TOTALMARKS, E.OLPM_TEMPFIELD1,"+ 
				"E.OLPM_TEMPFIELD2, E.OLPM_TEMPFIELD3, E.OLPM_TEMPFIELD4,E.OLPM_TEMPFIELD5," +
				" E.OLPM_CREATEDBY, E.OLPM_ACTIVE,E.OLPM_CREATEDON, E.OLPM_DURATION, E.OLPM_MODIFIEDON"
				+" FROM ENT_TL_ONLINEPREPARETESTMST E where OLPM_KEYID= ?";
		return sql;
	}

}

