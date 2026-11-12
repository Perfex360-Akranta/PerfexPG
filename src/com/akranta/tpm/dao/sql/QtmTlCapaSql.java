package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class QtmTlCapaSql {
	public static final String TBL_QTM_TL_CAPA = "QTM_TL_CAPA";  

	TableFieldType [] qcapDbFields = null;

	public enum   tableFldConstants
	{
	        keyid,flid,date,qualityissue,quantityeffect,defect,uom,
            todmt,tojh,complaintraisedby,todmtleaader,areaincharge,type,
            tempfield2,tempfield3,tempfield4,tempfield5, active, createdby,
            createdon,modifiedon
	}
	
	
	public TableFieldType[] getQcapDbFields() {
		return qcapDbFields;
	}

	public QtmTlCapaSql()
	{
		qcapDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			qcapDbFields[ i ] = new TableFieldType();
		}
		qcapDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QCAP_KEYID";
		qcapDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "QCAP_FLID";
		qcapDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.date.ordinal() ].fieldName = "QCAP_DATE";
		qcapDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		qcapDbFields[ tableFldConstants.qualityissue.ordinal() ].fieldName = "QCAP_QUALITYISSUE";
		qcapDbFields[ tableFldConstants.qualityissue.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.quantityeffect.ordinal() ].fieldName = "QCAP_QUANTITYEFFECT";
		qcapDbFields[ tableFldConstants.quantityeffect.ordinal() ].fieldType = 'N';

		qcapDbFields[ tableFldConstants.defect.ordinal() ].fieldName = "QCAP_DEFECT";
		qcapDbFields[ tableFldConstants.defect.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.uom.ordinal() ].fieldName = "QCAP_UOM";
		qcapDbFields[ tableFldConstants.uom.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.todmt.ordinal() ].fieldName = "QCAP_TODMT";
		qcapDbFields[ tableFldConstants.todmt.ordinal() ].fieldType = 'V';
		
		qcapDbFields[ tableFldConstants.tojh.ordinal() ].fieldName = "QCAP_TOJH";
		qcapDbFields[ tableFldConstants.tojh.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.complaintraisedby.ordinal() ].fieldName = "QCAP_COMPLANTRAISEDBY";
		qcapDbFields[ tableFldConstants.complaintraisedby.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.todmtleaader.ordinal() ].fieldName = "QCAP_TODMTLEADER";
		qcapDbFields[ tableFldConstants.todmtleaader.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.areaincharge.ordinal() ].fieldName = "QCAP_AREAINCHARGE";
		qcapDbFields[ tableFldConstants.areaincharge.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.type.ordinal() ].fieldName = "QCAP_COMPLAINTTYPE";
		qcapDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QCAP_TEMPFIELD2";
		qcapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QCAP_TEMPFIELD3";
		qcapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QCAP_TEMPFIELD4";
		qcapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "QCAP_TEMPFIELD5";
		qcapDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QCAP_ACTIVE";
		qcapDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qcapDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QCAP_CREATEDBY";
		qcapDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qcapDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QCAP_CREATEDON";
		qcapDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qcapDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QCAP_MODIFIEDON";
		qcapDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_CAPA, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_CAPA, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_CAPA ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getCAPAEmpMailIdSql(String complaintNo) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		
		//MODIFY ON 12-apr-2022 DB
		/*SELECT EMPM_NAME,QCAP_TODMTLEADER FROM GEN_TL_EMPLOYEEMST A ,QTM_TL_CAPA WHERE A.EMPM_KEYID=QCAP_TODMTLEADER AND QCAP_KEYID='CAPA00000054'
		UNION 
		SELECT EMPM_NAME,QCAP_TODMTLEADER FROM GEN_TL_EMPLOYEEMST B ,QTM_TL_CAPA WHERE B.EMPM_KEYID=QCAP_AREAINCHARGE AND QCAP_KEYID='CAPA00000054'
		UNION 
		SELECT EMPM_NAME,QCAP_TODMTLEADER FROM GEN_TL_EMPLOYEEMST C ,QTM_TL_CAPA WHERE C.EMPM_KEYID=QCAP_COMPLANTRAISEDBY AND QCAP_KEYID='CAPA00000054'
	*/
		sql.append(" select A.EMPM_EMAIL,QCAP_TODMTLEADER FROM GEN_TL_EMPLOYEEMST A ,QTM_TL_CAPA WHERE A.EMPM_KEYID=QCAP_TODMTLEADER AND QCAP_KEYID='"+complaintNo+"'");
		sql.append(" UNION  ");
		sql.append(" SELECT B.EMPM_EMAIL,QCAP_AREAINCHARGE FROM GEN_TL_EMPLOYEEMST B ,QTM_TL_CAPA WHERE B.EMPM_KEYID=QCAP_AREAINCHARGE AND QCAP_KEYID='"+complaintNo+"'");
		sql.append(" UNION  ");
		sql.append("SELECT C.EMPM_EMAIL,QCAP_COMPLANTRAISEDBY FROM GEN_TL_EMPLOYEEMST C ,QTM_TL_CAPA WHERE C.EMPM_KEYID=QCAP_COMPLANTRAISEDBY AND QCAP_KEYID='"+complaintNo+"'");
	
		// Modified on 10-Feb-15 for mail to be send based on configuration
		//sql.append("  and empm_keyid in ( select empm_keyid from gen_tl_employeemst where empm_enableemail='Y') ");
		
		System.out.println("getEmpAttendanceEmpMailIdSql=="+sql.toString());
		
		return sql.toString();
	}

}

