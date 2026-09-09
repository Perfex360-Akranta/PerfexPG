package com.akranta.tpm.dao.sql;

public class SapExternalServiceMstSql {

	public static final String TBL_SAP_EXTERNAL_SERVICE_MST = "SAP_EXTERNAL_SERVICE_MST";  

	TableFieldType [] extmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, notificationno, orderno, date, shift, flid, equipment
		, operation_qty, price, material_group, purchase_group, agreement
		, recipient, requisitioner, plan_deliverytime, ext_sub_contract
		, sortterm, per, cost_element, vendor, info_record, unload_point
		, track_no, fw_order, type, active, createdby, createdon, modifiedon,taskid,purchaseOrg
	}

	public TableFieldType[] getExtmDbFields() {
		return extmDbFields;
	}

	public SapExternalServiceMstSql()
	{
		extmDbFields = new TableFieldType[ 31 ];
		for(int i = 0;i < 31; i++)
		{	
			extmDbFields[ i ] = new TableFieldType();
		}
		extmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EXTM_KEYID";
		extmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.notificationno.ordinal() ].fieldName = "EXTM_NOTIFICATIONNO";
		extmDbFields[ tableFldConstants.notificationno.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "EXTM_ORDERNO";
		extmDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "EXTM_DATE";
		extmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		extmDbFields[ tableFldConstants.shift.ordinal() ].fieldName = "EXTM_SHIFT";
		extmDbFields[ tableFldConstants.shift.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "EXTM_FLID";
		extmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.equipment.ordinal() ].fieldName = "EXTM_EQUIPMENT";
		extmDbFields[ tableFldConstants.equipment.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.operation_qty.ordinal() ].fieldName = "EXTM_OPERATION_QTY";
		extmDbFields[ tableFldConstants.operation_qty.ordinal() ].fieldType = 'N';

		extmDbFields[ tableFldConstants.price.ordinal() ].fieldName = "EXTM_PRICE";
		extmDbFields[ tableFldConstants.price.ordinal() ].fieldType = 'N';

		extmDbFields[ tableFldConstants.material_group.ordinal() ].fieldName = "EXTM_MATERIAL_GROUP";
		extmDbFields[ tableFldConstants.material_group.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.purchase_group.ordinal() ].fieldName = "EXTM_PURCHASE_GROUP";
		extmDbFields[ tableFldConstants.purchase_group.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.agreement.ordinal() ].fieldName = "EXTM_AGREEMENT";
		extmDbFields[ tableFldConstants.agreement.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.recipient.ordinal() ].fieldName = "EXTM_RECIPIENT";
		extmDbFields[ tableFldConstants.recipient.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.requisitioner.ordinal() ].fieldName = "EXTM_REQUISITIONER";
		extmDbFields[ tableFldConstants.requisitioner.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.plan_deliverytime.ordinal() ].fieldName = "EXTM_PLAN_DELIVERYTIME";
		extmDbFields[ tableFldConstants.plan_deliverytime.ordinal() ].fieldType = 'D';

		extmDbFields[ tableFldConstants.ext_sub_contract.ordinal() ].fieldName = "EXTM_EXT_SUB_CONTRACT";
		extmDbFields[ tableFldConstants.ext_sub_contract.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.sortterm.ordinal() ].fieldName = "EXTM_SORTTERM";
		extmDbFields[ tableFldConstants.sortterm.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.per.ordinal() ].fieldName = "EXTM_PER";
		extmDbFields[ tableFldConstants.per.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.cost_element.ordinal() ].fieldName = "EXTM_COST_ELEMENT";
		extmDbFields[ tableFldConstants.cost_element.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.vendor.ordinal() ].fieldName = "EXTM_VENDOR";
		extmDbFields[ tableFldConstants.vendor.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.info_record.ordinal() ].fieldName = "EXTM_INFO_RECORD";
		extmDbFields[ tableFldConstants.info_record.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.unload_point.ordinal() ].fieldName = "EXTM_UNLOAD_POINT";
		extmDbFields[ tableFldConstants.unload_point.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.track_no.ordinal() ].fieldName = "EXTM_TRACK_NO";
		extmDbFields[ tableFldConstants.track_no.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.fw_order.ordinal() ].fieldName = "EXTM_FW_ORDER";
		extmDbFields[ tableFldConstants.fw_order.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "EXTM_TYPE";
		extmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EXTM_ACTIVE";
		extmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		extmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EXTM_CREATEDBY";
		extmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EXTM_CREATEDON";
		extmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		extmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EXTM_MODIFIEDON";
		extmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		extmDbFields[ tableFldConstants.taskid.ordinal() ].fieldName = "EXTM_TASKID";
		extmDbFields[ tableFldConstants.taskid.ordinal() ].fieldType = 'V';

		extmDbFields[ tableFldConstants.purchaseOrg.ordinal() ].fieldName = "EXTM_PURCHASEORG";
		extmDbFields[ tableFldConstants.purchaseOrg.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SAP_EXTERNAL_SERVICE_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SAP_EXTERNAL_SERVICE_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SAP_EXTERNAL_SERVICE_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

