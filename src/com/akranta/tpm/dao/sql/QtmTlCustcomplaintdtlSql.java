package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlCustcomplaintdtlSql {

	public static final String TBL_QTM_TL_CUSTCOMPLAINTDTL = "QTM_TL_CUSTCOMPLAINTDTL";  

	TableFieldType [] cucdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, complaintid, customerid, productgroupid, productid, complaintdescription
		, complainttype, lcnphenomenaid, dftphenomenaid, reason, defectparameter
		, specification, externaltestrpt, internaltestrpt, totalqtydespatch
		, totaldefectdesp, defectpercent, recordedby, inspectionid, inspectiondate
		, actiontaken, result, beforecondition, aftercondition, flowoutcause
		, rootcause, countermeasure, ispokayokedone, pokayokeno, iskaizendone
		, kaizenno, includeactionplan, yyno, isyydone, imagedescription
		, isdefectimageavl, remarks, processid, causeid, closedon, ship_to_party
		, sold_to_party, transaction_type, transaction_desc, ref_document_date
		, external_reference, crm_sales_office, billing_document, batch_billing_item
		, material, cause, current_status, cause_description, invoice_qty
		, invoice_price, complaint_qty, complaint_price, provision_value
		, sample_received_date, sample_sent_date, category, status, request_start
		, request_end, payer, bill_to_party, billing_status, sales_organization
		, distributer_channel, division, sales_office, sales_group, org_unit
		, service_org_unit, flag, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCucdDbFields() {
		return cucdDbFields;
	}

	public QtmTlCustcomplaintdtlSql()
	{
		cucdDbFields = new TableFieldType[ 86 ];
		for(int i = 0;i < 86; i++)
		{	
			cucdDbFields[ i ] = new TableFieldType();
		}
		cucdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CUCD_KEYID";
		cucdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.complaintid.ordinal() ].fieldName = "CUCD_COMPLAINTID";
		cucdDbFields[ tableFldConstants.complaintid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.customerid.ordinal() ].fieldName = "CUCD_CUSTOMERID";
		cucdDbFields[ tableFldConstants.customerid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.productgroupid.ordinal() ].fieldName = "CUCD_PRODUCTGROUPID";
		cucdDbFields[ tableFldConstants.productgroupid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "CUCD_PRODUCTID";
		cucdDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.complaintdescription.ordinal() ].fieldName = "CUCD_COMPLAINTDESCRIPTION";
		cucdDbFields[ tableFldConstants.complaintdescription.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.complainttype.ordinal() ].fieldName = "CUCD_COMPLAINTTYPE";
		cucdDbFields[ tableFldConstants.complainttype.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.lcnphenomenaid.ordinal() ].fieldName = "CUCD_LCNPHENOMENAID";
		cucdDbFields[ tableFldConstants.lcnphenomenaid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.dftphenomenaid.ordinal() ].fieldName = "CUCD_DFTPHENOMENAID";
		cucdDbFields[ tableFldConstants.dftphenomenaid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.reason.ordinal() ].fieldName = "CUCD_REASON";
		cucdDbFields[ tableFldConstants.reason.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.defectparameter.ordinal() ].fieldName = "CUCD_DEFECTPARAMETER";
		cucdDbFields[ tableFldConstants.defectparameter.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.specification.ordinal() ].fieldName = "CUCD_SPECIFICATION";
		cucdDbFields[ tableFldConstants.specification.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.externaltestrpt.ordinal() ].fieldName = "CUCD_EXTERNALTESTRPT";
		cucdDbFields[ tableFldConstants.externaltestrpt.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.internaltestrpt.ordinal() ].fieldName = "CUCD_INTERNALTESTRPT";
		cucdDbFields[ tableFldConstants.internaltestrpt.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.totalqtydespatch.ordinal() ].fieldName = "CUCD_TOTALQTYDESPATCH";
		cucdDbFields[ tableFldConstants.totalqtydespatch.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.totaldefectdesp.ordinal() ].fieldName = "CUCD_TOTALDEFECTDESP";
		cucdDbFields[ tableFldConstants.totaldefectdesp.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.defectpercent.ordinal() ].fieldName = "CUCD_DEFECTPERCENT";
		cucdDbFields[ tableFldConstants.defectpercent.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.recordedby.ordinal() ].fieldName = "CUCD_RECORDEDBY";
		cucdDbFields[ tableFldConstants.recordedby.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.inspectionid.ordinal() ].fieldName = "CUCD_INSPECTIONID";
		cucdDbFields[ tableFldConstants.inspectionid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.inspectiondate.ordinal() ].fieldName = "CUCD_INSPECTIONDATE";
		cucdDbFields[ tableFldConstants.inspectiondate.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldName = "CUCD_ACTIONTAKEN";
		cucdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.result.ordinal() ].fieldName = "CUCD_RESULT";
		cucdDbFields[ tableFldConstants.result.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.beforecondition.ordinal() ].fieldName = "CUCD_BEFORECONDITION";
		cucdDbFields[ tableFldConstants.beforecondition.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.aftercondition.ordinal() ].fieldName = "CUCD_AFTERCONDITION";
		cucdDbFields[ tableFldConstants.aftercondition.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.flowoutcause.ordinal() ].fieldName = "CUCD_FLOWOUTCAUSE";
		cucdDbFields[ tableFldConstants.flowoutcause.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "CUCD_ROOTCAUSE";
		cucdDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "CUCD_COUNTERMEASURE";
		cucdDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.ispokayokedone.ordinal() ].fieldName = "CUCD_ISPOKAYOKEDONE";
		cucdDbFields[ tableFldConstants.ispokayokedone.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.pokayokeno.ordinal() ].fieldName = "CUCD_POKAYOKENO";
		cucdDbFields[ tableFldConstants.pokayokeno.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.iskaizendone.ordinal() ].fieldName = "CUCD_ISKAIZENDONE";
		cucdDbFields[ tableFldConstants.iskaizendone.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.kaizenno.ordinal() ].fieldName = "CUCD_KAIZENNO";
		cucdDbFields[ tableFldConstants.kaizenno.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.includeactionplan.ordinal() ].fieldName = "CUCD_INCLUDEACTIONPLAN";
		cucdDbFields[ tableFldConstants.includeactionplan.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.yyno.ordinal() ].fieldName = "CUCD_YYNO";
		cucdDbFields[ tableFldConstants.yyno.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.isyydone.ordinal() ].fieldName = "CUCD_ISYYDONE";
		cucdDbFields[ tableFldConstants.isyydone.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.imagedescription.ordinal() ].fieldName = "CUCD_IMAGEDESCRIPTION";
		cucdDbFields[ tableFldConstants.imagedescription.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.isdefectimageavl.ordinal() ].fieldName = "CUCD_ISDEFECTIMAGEAVL";
		cucdDbFields[ tableFldConstants.isdefectimageavl.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "CUCD_REMARKS";
		cucdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "CUCD_PROCESSID";
		cucdDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "CUCD_CAUSEID";
		cucdDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.closedon.ordinal() ].fieldName = "CUCD_CLOSEDON";
		cucdDbFields[ tableFldConstants.closedon.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.ship_to_party.ordinal() ].fieldName = "CUCD_SHIP_TO_PARTY";
		cucdDbFields[ tableFldConstants.ship_to_party.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.sold_to_party.ordinal() ].fieldName = "CUCD_SOLD_TO_PARTY";
		cucdDbFields[ tableFldConstants.sold_to_party.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.transaction_type.ordinal() ].fieldName = "CUCD_TRANSACTION_TYPE";
		cucdDbFields[ tableFldConstants.transaction_type.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.transaction_desc.ordinal() ].fieldName = "CUCD_TRANSACTION_DESC";
		cucdDbFields[ tableFldConstants.transaction_desc.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.ref_document_date.ordinal() ].fieldName = "CUCD_REF_DOCUMENT_DATE";
		cucdDbFields[ tableFldConstants.ref_document_date.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.external_reference.ordinal() ].fieldName = "CUCD_EXTERNAL_REFERENCE";
		cucdDbFields[ tableFldConstants.external_reference.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.crm_sales_office.ordinal() ].fieldName = "CUCD_CRM_SALES_OFFICE";
		cucdDbFields[ tableFldConstants.crm_sales_office.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.billing_document.ordinal() ].fieldName = "CUCD_BILLING_DOCUMENT";
		cucdDbFields[ tableFldConstants.billing_document.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.batch_billing_item.ordinal() ].fieldName = "CUCD_BATCH_BILLING_ITEM";
		cucdDbFields[ tableFldConstants.batch_billing_item.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.material.ordinal() ].fieldName = "CUCD_MATERIAL";
		cucdDbFields[ tableFldConstants.material.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.cause.ordinal() ].fieldName = "CUCD_CAUSE";
		cucdDbFields[ tableFldConstants.cause.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.current_status.ordinal() ].fieldName = "CUCD_CURRENT_STATUS";
		cucdDbFields[ tableFldConstants.current_status.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.cause_description.ordinal() ].fieldName = "CUCD_CAUSE_DESCRIPTION";
		cucdDbFields[ tableFldConstants.cause_description.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.invoice_qty.ordinal() ].fieldName = "CUCD_INVOICE_QTY";
		cucdDbFields[ tableFldConstants.invoice_qty.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.invoice_price.ordinal() ].fieldName = "CUCD_INVOICE_PRICE";
		cucdDbFields[ tableFldConstants.invoice_price.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.complaint_qty.ordinal() ].fieldName = "CUCD_COMPLAINT_QTY";
		cucdDbFields[ tableFldConstants.complaint_qty.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.complaint_price.ordinal() ].fieldName = "CUCD_COMPLAINT_PRICE";
		cucdDbFields[ tableFldConstants.complaint_price.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.provision_value.ordinal() ].fieldName = "CUCD_PROVISION_VALUE";
		cucdDbFields[ tableFldConstants.provision_value.ordinal() ].fieldType = 'N';

		cucdDbFields[ tableFldConstants.sample_received_date.ordinal() ].fieldName = "CUCD_SAMPLE_RECEIVED_DATE";
		cucdDbFields[ tableFldConstants.sample_received_date.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.sample_sent_date.ordinal() ].fieldName = "CUCD_SAMPLE_SENT_DATE";
		cucdDbFields[ tableFldConstants.sample_sent_date.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.category.ordinal() ].fieldName = "CUCD_CATEGORY";
		cucdDbFields[ tableFldConstants.category.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "CUCD_STATUS";
		cucdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.request_start.ordinal() ].fieldName = "CUCD_REQUEST_START";
		cucdDbFields[ tableFldConstants.request_start.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.request_end.ordinal() ].fieldName = "CUCD_REQUEST_END";
		cucdDbFields[ tableFldConstants.request_end.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.payer.ordinal() ].fieldName = "CUCD_PAYER";
		cucdDbFields[ tableFldConstants.payer.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.bill_to_party.ordinal() ].fieldName = "CUCD_BILL_TO_PARTY";
		cucdDbFields[ tableFldConstants.bill_to_party.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.billing_status.ordinal() ].fieldName = "CUCD_BILLING_STATUS";
		cucdDbFields[ tableFldConstants.billing_status.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.sales_organization.ordinal() ].fieldName = "CUCD_SALES_ORGANIZATION";
		cucdDbFields[ tableFldConstants.sales_organization.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.distributer_channel.ordinal() ].fieldName = "CUCD_DISTRIBUTER_CHANNEL";
		cucdDbFields[ tableFldConstants.distributer_channel.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.division.ordinal() ].fieldName = "CUCD_DIVISION";
		cucdDbFields[ tableFldConstants.division.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.sales_office.ordinal() ].fieldName = "CUCD_SALES_OFFICE";
		cucdDbFields[ tableFldConstants.sales_office.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.sales_group.ordinal() ].fieldName = "CUCD_SALES_GROUP";
		cucdDbFields[ tableFldConstants.sales_group.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.org_unit.ordinal() ].fieldName = "CUCD_ORG_UNIT";
		cucdDbFields[ tableFldConstants.org_unit.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.service_org_unit.ordinal() ].fieldName = "CUCD_SERVICE_ORG_UNIT";
		cucdDbFields[ tableFldConstants.service_org_unit.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.flag.ordinal() ].fieldName = "CUCD_FLAG";
		cucdDbFields[ tableFldConstants.flag.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CUCD_TEMPFIELD1";
		cucdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CUCD_TEMPFIELD2";
		cucdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CUCD_TEMPFIELD3";
		cucdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CUCD_TEMPFIELD4";
		cucdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CUCD_TEMPFIELD5";
		cucdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "CUCD_TEMPFIELD6";
		cucdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "CUCD_TEMPFIELD7";
		cucdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';
		
		cucdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CUCD_ACTIVE";
		cucdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cucdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CUCD_CREATEDBY";
		cucdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cucdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CUCD_CREATEDON";
		cucdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cucdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CUCD_MODIFIEDON";
		cucdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_CUSTCOMPLAINTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_CUSTCOMPLAINTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String cucdComplaintid)
	{
		String sql = "DELETE from " + TBL_QTM_TL_CUSTCOMPLAINTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.complaintid.ordinal()].fieldName  +
			  " = '" + cucdComplaintid + "'";
		return sql;
	}

	public static String getCustomerDtls() 
	{
		String sql;
		sql="SELECT * from " + TBL_QTM_TL_CUSTCOMPLAINTDTL + " where CUCD_COMPLAINTID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;

	}

	public static String getUpdateKznNoSql(TableFieldType[] fieldTypeArr,Object[] dataArray) {
		
		return " UPDATE "+TBL_QTM_TL_CUSTCOMPLAINTDTL+" SET "+
			fieldTypeArr[tableFldConstants.kaizenno.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.kaizenno.ordinal() ] + "', "+ 
		  	fieldTypeArr[tableFldConstants.iskaizendone.ordinal()].fieldName  +
		  	" = '" +  (String)dataArray[ tableFldConstants.iskaizendone.ordinal() ] + "'" +
		  	" where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
	}

	public static String getUpdateYYNoSql(TableFieldType[] fieldTypeArr,Object[] dataArray) {
		// TODO Auto-generated method stub
		return " UPDATE "+TBL_QTM_TL_CUSTCOMPLAINTDTL+" SET "+
		fieldTypeArr[tableFldConstants.yyno.ordinal()].fieldName  +
	  " = '" +  (String)dataArray[ tableFldConstants.yyno.ordinal() ] + "', "+ 
	  	fieldTypeArr[tableFldConstants.isyydone.ordinal()].fieldName  +
	  	" = '" +  (String)dataArray[ tableFldConstants.isyydone.ordinal() ] + "'" +
	  	" where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
	}

	

}

