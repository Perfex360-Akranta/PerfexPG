package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_PcsTlRejectionbreakupdataSql {

	public static final String TBL_BAL_PCS_TL_REJECTIONBREAKUPDATA = "BAL_PCS_TL_REJECTIONBREAKUPDATA";


	TableFieldType [] prbdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, pprd_keyid, type, phenomena, quantity, rejectionreason
		, defect, defectsource, equipment, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPrbdDbFields() {
		return prbdDbFields;
	}

	public BAL_PcsTlRejectionbreakupdataSql()
	{
		prbdDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			prbdDbFields[ i ] = new TableFieldType();
		}
		prbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PRBD_KEYID";
		prbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.pprd_keyid.ordinal() ].fieldName = "PRBD_PPRD_KEYID";
		prbdDbFields[ tableFldConstants.pprd_keyid.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.type.ordinal() ].fieldName = "PRBD_TYPE";
		prbdDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		prbdDbFields[ tableFldConstants.phenomena.ordinal() ].fieldName = "PRBD_PHENOMENA";
		prbdDbFields[ tableFldConstants.phenomena.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "PRBD_QUANTITY";
		prbdDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		prbdDbFields[ tableFldConstants.rejectionreason.ordinal() ].fieldName = "PRBD_REJECTIONREASON";
		prbdDbFields[ tableFldConstants.rejectionreason.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.defect.ordinal() ].fieldName = "PRBD_DEFECT";
		prbdDbFields[ tableFldConstants.defect.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.defectsource.ordinal() ].fieldName = "PRBD_DEFECTSOURCE";
		prbdDbFields[ tableFldConstants.defectsource.ordinal() ].fieldType = 'C';

		prbdDbFields[ tableFldConstants.equipment.ordinal() ].fieldName = "PRBD_EQUIPMENT";
		prbdDbFields[ tableFldConstants.equipment.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PRBD_TEMPFIELD1";
		prbdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PRBD_TEMPFIELD2";
		prbdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PRBD_TEMPFIELD3";
		prbdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PRBD_TEMPFIELD4";
		prbdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PRBD_ACTIVE";
		prbdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		prbdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PRBD_CREATEDBY";
		prbdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		prbdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PRBD_CREATEDON";
		prbdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		prbdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PRBD_MODIFIEDON";
		prbdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_PCS_TL_REJECTIONBREAKUPDATA, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_BAL_PCS_TL_REJECTIONBREAKUPDATA, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_BAL_PCS_TL_REJECTIONBREAKUPDATA );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

	public static String DeleterejectionRow(String keyid) {
	
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder( "DELETE from " );
			sql.append(TBL_BAL_PCS_TL_REJECTIONBREAKUPDATA );
			sql.append( " where " );
			sql.append("PRBD_KEYID"); 
			sql.append( " = '" );
			sql.append(keyid);
			sql.append(	"'");
			CommonFunctions.debugMsg("Rejection rw sql"+sql);
			return sql.toString();
		
	}

}

