package com.akranta.tpm.dao.sql;
public class QtmTlCapaActionmstSql{

	public static final String TBL_QTM_TL_CAPACTIONMST="QTM_TL_CAPACTIONMST";  
	TableFieldType [] qcpaDbFields = null;

	public enum   tableFldConstants
	{
		keyid,masterid,correctiveaction,rootcause,preventiveaction,fourmcondition,
        defecttype,responsibleid,targetdate,status,completedon,actionplanid,whywhwyid,suggestionid,
        tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,tempfield6,tempfield7,tempfield8,
        tempfield9,tempfield10,active,createdby,createdon,modifiedon
	}
	
	public TableFieldType[] getqcpaDbFields() {
		return qcpaDbFields;
	}

	public QtmTlCapaActionmstSql()
	{
		qcpaDbFields = new TableFieldType[ 28 ];
		for(int i = 0;i <28; i++)
		{	
			qcpaDbFields[ i ] = new TableFieldType();
		}
		qcpaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QCPA_KEYID";
		qcpaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "QCPA_QCAP_KEYID";
		qcpaDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldName = "QCPA_CORRECTIVEACTION";
		qcpaDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldType = 'v';

		qcpaDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "QCPA_ROOTCAUSE";
		qcpaDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldName = "QCPA_PREVENTIVEACTION";
		qcpaDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.fourmcondition.ordinal() ].fieldName = "QCPA_4MCONDITION";
		qcpaDbFields[ tableFldConstants.fourmcondition.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.defecttype.ordinal() ].fieldName = "QCPA_DEFECTTYPE";
		qcpaDbFields[ tableFldConstants.defecttype.ordinal() ].fieldType = 'C';

		qcpaDbFields[ tableFldConstants.responsibleid.ordinal() ].fieldName = "QCPA_RESPONSIBLEID";
		qcpaDbFields[ tableFldConstants.responsibleid.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "QCPA_TARGETDATE";
		qcpaDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		qcpaDbFields[ tableFldConstants.status.ordinal() ].fieldName = "QCPA_STATUS";
		qcpaDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.completedon.ordinal() ].fieldName = "QCPA_COMPLETEDON";
		qcpaDbFields[ tableFldConstants.completedon.ordinal() ].fieldType = 'D';
		
		qcpaDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldName = "QCPA_ACTIONPLANID";
		qcpaDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.whywhwyid.ordinal() ].fieldName = "QCPA_WHYWHYID";
		qcpaDbFields[ tableFldConstants.whywhwyid.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.suggestionid.ordinal() ].fieldName = "QCPA_SUGGESTIONID";
		qcpaDbFields[ tableFldConstants.suggestionid.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "QCPA_TEMPFIELD1";
		qcpaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QCPA_TEMPFIELD2";
		qcpaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QCPA_TEMPFIELD3";
		qcpaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QCPA_TEMPFIELD4";
		qcpaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "QCPA_TEMPFIELD5";
		qcpaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "QCPA_TEMPFIELD6";
		qcpaDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "QCPA_TEMPFIELD7";
		qcpaDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "QCPA_TEMPFIELD8";
		qcpaDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';
		
		qcpaDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "QCPA_TEMPFIELD9";
		qcpaDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "QCPA_TEMPFIELD10";
		qcpaDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QCPA_ACTIVE";
		qcpaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qcpaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QCPA_CREATEDBY";
		qcpaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qcpaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QCPA_CREATEDON";
		qcpaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qcpaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QCPA_MODIFIEDON";
		qcpaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_CAPACTIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_CAPACTIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_CAPACTIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

