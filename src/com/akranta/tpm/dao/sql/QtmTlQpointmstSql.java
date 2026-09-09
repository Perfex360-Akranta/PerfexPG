package com.akranta.tpm.dao.sql;

public class QtmTlQpointmstSql {

	public static final String TBL_QTM_TL_QPOINTMST = "QTM_TL_QPOINTMST";  

	TableFieldType [] qpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flnid, preparedby, approvedby, prepareddate, approveddate
		, tempfield1, tempfield2, tempfield3, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getQpmDbFields() {
		return qpmDbFields;
	}

	public QtmTlQpointmstSql()
	{
		qpmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			qpmDbFields[ i ] = new TableFieldType();
		}
		qpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QPM_KEYID";
		qpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qpmDbFields[ tableFldConstants.flnid.ordinal() ].fieldName = "QPM_FLNID";
		qpmDbFields[ tableFldConstants.flnid.ordinal() ].fieldType = 'V';

		qpmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "QPM_PREPAREDBY";
		qpmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		qpmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "QPM_APPROVEDBY";
		qpmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		qpmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldName = "QPM_PREPAREDDATE";
		qpmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldType = 'D';

		qpmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "QPM_APPROVEDDATE";
		qpmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		qpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "QPM_TEMPFIELD1";
		qpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		qpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QPM_TEMPFIELD2";
		qpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		qpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QPM_TEMPFIELD3";
		qpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		qpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QPM_TEMPFIELD4";
		qpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		qpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QPM_ACTIVE";
		qpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QPM_CREATEDBY";
		qpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QPM_CREATEDON";
		qpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QPM_MODIFIEDON";
		qpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_QPOINTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_QPOINTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_QPOINTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getQtmTlQpointmstSql() {
		// TODO Auto-generated method stub
		 String sql="select * from " + TBL_QTM_TL_QPOINTMST  + " where QPM_KEYID= ?";
			
			// TODO Auto-generated method stub
			return sql;
	}

	public String getmaster(String masterKeyid) {
		// TODO Auto-generated method stub
		String sql="select QPD_KEYID,QPD_QPMKEYID,QPD_QPARAMETER,QPD_SPECIFICATION,QPD_EFFECTOFPARAM,QPD_MEASURINGEQUIP,QPD_MONITORINGMETHOD,QPD_FOURM,DECODE(QPD_FREQUENCY,'M','MONTHLY','Q','QUATERLY','H','HALF-YERALY','Y','YEARLY') from QTM_TL_QPOINTDTL where  QPD_QPMKEYID = '"+masterKeyid+"'";
		
		return sql;
	}
	

	public String getAllSop() {
		String sql = "  select QPM_KEYID,FUNCTIONALLOC,A.EMPM_NAME ,TO_CHAR(QPM_PREPAREDDATE,'DD-mon-YYYY') , B.EMPM_NAME ,TO_CHAR(QPM_APPROVEDDATE,'DD-mon-YYYY') from  QTM_TL_QPOINTMST,GEN_TL_EMPLOYEEMST A,GEN_TL_EMPLOYEEMST B, GEN_VW_FNLN WHERE qpm_flnid = fnln_keyid (+) and QPM_PREPAREDBY=A.EMPM_KEYID(+)  AND QPM_APPROVEDBY=B.EMPM_KEYID(+) ";
		
		return sql;

	}

}

