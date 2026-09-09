package com.akranta.tpm.dao.sql;

public class EntTlTtgCalEmpatScoreSql{

	public static final String TBL_ENT_TL_TRGCALEMPATSCORE = "ENT_TL_TRGCALEMPATSCORE";

	

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid,etcm_keyid,etce_empm_keyid,etce_keyid, assessmentcom,maxmarks,cutoff,type,dept
		,presentabsent,attdate ,score ,result,remarks,scoredate,filemgnid,tempfield1, tempfield2,
		 tempfield3, tempfield4,tempfield5, createdby,active,createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlTtgCalEmpatScoreSql()
	{
		ftymDbFields = new TableFieldType[ 25 ];
		for(int i = 0;i < 25; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETCA_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldName = "ETCA_ETCM_KEYID";
		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etce_empm_keyid.ordinal() ].fieldName = "ETCA_ETCE_EMPM_KEYID";
		ftymDbFields[ tableFldConstants.etce_empm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etce_keyid.ordinal() ].fieldName = "ETCA_ETCE_KEYID";
		ftymDbFields[ tableFldConstants.etce_keyid.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.assessmentcom.ordinal() ].fieldName = "ETCA_ASSESSMENTCOM";
		ftymDbFields[ tableFldConstants.assessmentcom.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.maxmarks.ordinal() ].fieldName = "ETCA_MAXMARKS";
		ftymDbFields[ tableFldConstants.maxmarks.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.cutoff.ordinal() ].fieldName = "ETCA_CUTOFF";
		ftymDbFields[ tableFldConstants.cutoff.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.type.ordinal() ].fieldName = "ETCA_TYPE";
		ftymDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.dept.ordinal() ].fieldName = "ETCA_DEPT";
		ftymDbFields[ tableFldConstants.dept.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.presentabsent.ordinal() ].fieldName = "ETCA_PRSENTABSENT";
		ftymDbFields[ tableFldConstants.presentabsent.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.attdate.ordinal() ].fieldName = "ETCA_ATTDATE";
		ftymDbFields[ tableFldConstants.attdate.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.score.ordinal() ].fieldName = "ETCA_SCORE";
		ftymDbFields[ tableFldConstants.score.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.result.ordinal() ].fieldName = "ETCA_RESULT";
		ftymDbFields[ tableFldConstants.result.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "ETCA_REMARKS";
		ftymDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.scoredate.ordinal() ].fieldName = "ETCA_SCOREDATE";
		ftymDbFields[ tableFldConstants.scoredate.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.filemgnid.ordinal() ].fieldName = "ETCA_FILEMGNID";
		ftymDbFields[ tableFldConstants.filemgnid.ordinal() ].fieldType = 'C';
        
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ETCA_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ETCA_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ETCA_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ETCA_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ETCA_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';
        
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ETCA_CREATEDBY";
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETCA_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';
	
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETCA_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCA_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRGCALEMPATSCORE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRGCALEMPATSCORE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRGCALEMPATSCORE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

