package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class QtmTlCustcomplaintdtl {

	private  Object [] saveArray = null;  
	

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

	public QtmTlCustcomplaintdtl()
	{
		saveArray = new  Object [ 86 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getCucdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCucdKeyid(String cucdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cucdKeyid;
	}

	public String getCucdComplaintid() {
		return (String) saveArray[ tableFldConstants.complaintid.ordinal() ];
	}

	public void setCucdComplaintid(String cucdComplaintid) {
		saveArray[ tableFldConstants.complaintid.ordinal() ] = cucdComplaintid;
	}

	public String getCucdCustomerid() {
		return (String) saveArray[ tableFldConstants.customerid.ordinal() ];
	}

	public void setCucdCustomerid(String cucdCustomerid) {
		saveArray[ tableFldConstants.customerid.ordinal() ] = cucdCustomerid;
	}

	public String getCucdProductgroupid() {
		return (String) saveArray[ tableFldConstants.productgroupid.ordinal() ];
	}

	public void setCucdProductgroupid(String cucdProductgroupid) {
		saveArray[ tableFldConstants.productgroupid.ordinal() ] = cucdProductgroupid;
	}

	public String getCucdProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setCucdProductid(String cucdProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = cucdProductid;
	}

	public String getCucdComplaintdescription() {
		return (String) saveArray[ tableFldConstants.complaintdescription.ordinal() ];
	}

	public void setCucdComplaintdescription(String cucdComplaintdescription) {
		saveArray[ tableFldConstants.complaintdescription.ordinal() ] = cucdComplaintdescription;
	}

	public String getCucdComplainttype() {
		return (String) saveArray[ tableFldConstants.complainttype.ordinal() ];
	}

	public void setCucdComplainttype(String cucdComplainttype) {
		saveArray[ tableFldConstants.complainttype.ordinal() ] = cucdComplainttype;
	}

	public String getCucdLcnphenomenaid() {
		return (String) saveArray[ tableFldConstants.lcnphenomenaid.ordinal() ];
	}

	public void setCucdLcnphenomenaid(String cucdLcnphenomenaid) {
		saveArray[ tableFldConstants.lcnphenomenaid.ordinal() ] = cucdLcnphenomenaid;
	}

	public String getCucdDftphenomenaid() {
		return (String) saveArray[ tableFldConstants.dftphenomenaid.ordinal() ];
	}

	public void setCucdDftphenomenaid(String cucdDftphenomenaid) {
		saveArray[ tableFldConstants.dftphenomenaid.ordinal() ] = cucdDftphenomenaid;
	}

	public String getCucdReason() {
		return (String) saveArray[ tableFldConstants.reason.ordinal() ];
	}

	public void setCucdReason(String cucdReason) {
		saveArray[ tableFldConstants.reason.ordinal() ] = cucdReason;
	}

	public String getCucdDefectparameter() {
		return (String) saveArray[ tableFldConstants.defectparameter.ordinal() ];
	}

	public void setCucdDefectparameter(String cucdDefectparameter) {
		saveArray[ tableFldConstants.defectparameter.ordinal() ] = cucdDefectparameter;
	}

	public String getCucdSpecification() {
		return (String) saveArray[ tableFldConstants.specification.ordinal() ];
	}

	public void setCucdSpecification(String cucdSpecification) {
		saveArray[ tableFldConstants.specification.ordinal() ] = cucdSpecification;
	}

	public String getCucdExternaltestrpt() {
		return (String) saveArray[ tableFldConstants.externaltestrpt.ordinal() ];
	}

	public void setCucdExternaltestrpt(String cucdExternaltestrpt) {
		saveArray[ tableFldConstants.externaltestrpt.ordinal() ] = cucdExternaltestrpt;
	}

	public String getCucdInternaltestrpt() {
		return (String) saveArray[ tableFldConstants.internaltestrpt.ordinal() ];
	}

	public void setCucdInternaltestrpt(String cucdInternaltestrpt) {
		saveArray[ tableFldConstants.internaltestrpt.ordinal() ] = cucdInternaltestrpt;
	}

	public String getCucdTotalqtydespatch() {
		return (String) saveArray[ tableFldConstants.totalqtydespatch.ordinal() ];
	}

	public void setCucdTotalqtydespatch(String cucdTotalqtydespatch) {
		saveArray[ tableFldConstants.totalqtydespatch.ordinal() ] = cucdTotalqtydespatch;
	}

	public String getCucdTotaldefectdesp() {
		return (String) saveArray[ tableFldConstants.totaldefectdesp.ordinal() ];
	}

	public void setCucdTotaldefectdesp(String cucdTotaldefectdesp) {
		saveArray[ tableFldConstants.totaldefectdesp.ordinal() ] = cucdTotaldefectdesp;
	}

	public String getCucdDefectpercent() {
		return (String) saveArray[ tableFldConstants.defectpercent.ordinal() ];
	}

	public void setCucdDefectpercent(String cucdDefectpercent) {
		saveArray[ tableFldConstants.defectpercent.ordinal() ] = cucdDefectpercent;
	}

	public String getCucdRecordedby() {
		return (String) saveArray[ tableFldConstants.recordedby.ordinal() ];
	}

	public void setCucdRecordedby(String cucdRecordedby) {
		saveArray[ tableFldConstants.recordedby.ordinal() ] = cucdRecordedby;
	}

	public String getCucdInspectionid() {
		return (String) saveArray[ tableFldConstants.inspectionid.ordinal() ];
	}

	public void setCucdInspectionid(String cucdInspectionid) {
		saveArray[ tableFldConstants.inspectionid.ordinal() ] = cucdInspectionid;
	}

	public String getCucdInspectiondate() {
		return (String) saveArray[ tableFldConstants.inspectiondate.ordinal() ];
	}

	public void setCucdInspectiondate(String cucdInspectiondate) {
		saveArray[ tableFldConstants.inspectiondate.ordinal() ] = cucdInspectiondate;
	}

	public String getCucdActiontaken() {
		return (String) saveArray[ tableFldConstants.actiontaken.ordinal() ];
	}

	public void setCucdActiontaken(String cucdActiontaken) {
		saveArray[ tableFldConstants.actiontaken.ordinal() ] = cucdActiontaken;
	}

	public String getCucdResult() {
		return (String) saveArray[ tableFldConstants.result.ordinal() ];
	}

	public void setCucdResult(String cucdResult) {
		saveArray[ tableFldConstants.result.ordinal() ] = cucdResult;
	}

	public String getCucdBeforecondition() {
		return (String) saveArray[ tableFldConstants.beforecondition.ordinal() ];
	}

	public void setCucdBeforecondition(String cucdBeforecondition) {
		saveArray[ tableFldConstants.beforecondition.ordinal() ] = cucdBeforecondition;
	}

	public String getCucdAftercondition() {
		return (String) saveArray[ tableFldConstants.aftercondition.ordinal() ];
	}

	public void setCucdAftercondition(String cucdAftercondition) {
		saveArray[ tableFldConstants.aftercondition.ordinal() ] = cucdAftercondition;
	}

	public String getCucdFlowoutcause() {
		return (String) saveArray[ tableFldConstants.flowoutcause.ordinal() ];
	}

	public void setCucdFlowoutcause(String cucdFlowoutcause) {
		saveArray[ tableFldConstants.flowoutcause.ordinal() ] = cucdFlowoutcause;
	}

	public String getCucdRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setCucdRootcause(String cucdRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = cucdRootcause;
	}

	public String getCucdCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setCucdCountermeasure(String cucdCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = cucdCountermeasure;
	}

	public String getCucdIspokayokedone() {
		return (String) saveArray[ tableFldConstants.ispokayokedone.ordinal() ];
	}

	public void setCucdIspokayokedone(String cucdIspokayokedone) {
		saveArray[ tableFldConstants.ispokayokedone.ordinal() ] = cucdIspokayokedone;
	}

	public String getCucdPokayokeno() {
		return (String) saveArray[ tableFldConstants.pokayokeno.ordinal() ];
	}

	public void setCucdPokayokeno(String cucdPokayokeno) {
		saveArray[ tableFldConstants.pokayokeno.ordinal() ] = cucdPokayokeno;
	}

	public String getCucdIskaizendone() {
		return (String) saveArray[ tableFldConstants.iskaizendone.ordinal() ];
	}

	public void setCucdIskaizendone(String cucdIskaizendone) {
		saveArray[ tableFldConstants.iskaizendone.ordinal() ] = cucdIskaizendone;
	}

	public String getCucdKaizenno() {
		return (String) saveArray[ tableFldConstants.kaizenno.ordinal() ];
	}

	public void setCucdKaizenno(String cucdKaizenno) {
		saveArray[ tableFldConstants.kaizenno.ordinal() ] = cucdKaizenno;
	}

	public String getCucdIncludeactionplan() {
		return (String) saveArray[ tableFldConstants.includeactionplan.ordinal() ];
	}

	public void setCucdIncludeactionplan(String cucdIncludeactionplan) {
		saveArray[ tableFldConstants.includeactionplan.ordinal() ] = cucdIncludeactionplan;
	}

	public String getCucdYyno() {
		return (String) saveArray[ tableFldConstants.yyno.ordinal() ];
	}

	public void setCucdYyno(String cucdYyno) {
		saveArray[ tableFldConstants.yyno.ordinal() ] = cucdYyno;
	}

	public String getCucdIsyydone() {
		return (String) saveArray[ tableFldConstants.isyydone.ordinal() ];
	}

	public void setCucdIsyydone(String cucdIsyydone) {
		saveArray[ tableFldConstants.isyydone.ordinal() ] = cucdIsyydone;
	}

	public String getCucdImagedescription() {
		return (String) saveArray[ tableFldConstants.imagedescription.ordinal() ];
	}

	public void setCucdImagedescription(String cucdImagedescription) {
		saveArray[ tableFldConstants.imagedescription.ordinal() ] = cucdImagedescription;
	}

	public String getCucdIsdefectimageavl() {
		return (String) saveArray[ tableFldConstants.isdefectimageavl.ordinal() ];
	}

	public void setCucdIsdefectimageavl(String cucdIsdefectimageavl) {
		saveArray[ tableFldConstants.isdefectimageavl.ordinal() ] = cucdIsdefectimageavl;
	}

	public String getCucdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setCucdRemarks(String cucdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = cucdRemarks;
	}

	public String getCucdProcessid() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setCucdProcessid(String cucdProcessid) {
		saveArray[ tableFldConstants.processid.ordinal() ] = cucdProcessid;
	}

	public String getCucdCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setCucdCauseid(String cucdCauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = cucdCauseid;
	}

	public String getCucdClosedon() {
		return (String) saveArray[ tableFldConstants.closedon.ordinal() ];
	}

	public void setCucdClosedon(String cucdClosedon) {
		saveArray[ tableFldConstants.closedon.ordinal() ] = cucdClosedon;
	}


	public String getCucdShipToParty() {
		return (String) saveArray[ tableFldConstants.ship_to_party.ordinal() ];
	}

	public void setCucdShipToParty(String cucdShipToParty) {
		saveArray[ tableFldConstants.ship_to_party.ordinal() ] = cucdShipToParty;
	}

	public String getCucdSoldToParty() {
		return (String) saveArray[ tableFldConstants.sold_to_party.ordinal() ];
	}

	public void setCucdSoldToParty(String cucdSoldToParty) {
		saveArray[ tableFldConstants.sold_to_party.ordinal() ] = cucdSoldToParty;
	}

	public String getCucdTransactionType() {
		return (String) saveArray[ tableFldConstants.transaction_type.ordinal() ];
	}

	public void setCucdTransactionType(String cucdTransactionType) {
		saveArray[ tableFldConstants.transaction_type.ordinal() ] = cucdTransactionType;
	}

	public String getCucdTransactionDesc() {
		return (String) saveArray[ tableFldConstants.transaction_desc.ordinal() ];
	}

	public void setCucdTransactionDesc(String cucdTransactionDesc) {
		saveArray[ tableFldConstants.transaction_desc.ordinal() ] = cucdTransactionDesc;
	}

	public String getCucdRefDocumentDate() {
		return (String) saveArray[ tableFldConstants.ref_document_date.ordinal() ];
	}

	public void setCucdRefDocumentDate(String cucdRefDocumentDate) {
		saveArray[ tableFldConstants.ref_document_date.ordinal() ] = cucdRefDocumentDate;
	}

	public String getCucdExternalReference() {
		return (String) saveArray[ tableFldConstants.external_reference.ordinal() ];
	}

	public void setCucdExternalReference(String cucdExternalReference) {
		saveArray[ tableFldConstants.external_reference.ordinal() ] = cucdExternalReference;
	}

	public String getCucdCrmSalesOffice() {
		return (String) saveArray[ tableFldConstants.crm_sales_office.ordinal() ];
	}

	public void setCucdCrmSalesOffice(String cucdCrmSalesOffice) {
		saveArray[ tableFldConstants.crm_sales_office.ordinal() ] = cucdCrmSalesOffice;
	}

	public String getCucdBillingDocument() {
		return (String) saveArray[ tableFldConstants.billing_document.ordinal() ];
	}

	public void setCucdBillingDocument(String cucdBillingDocument) {
		saveArray[ tableFldConstants.billing_document.ordinal() ] = cucdBillingDocument;
	}

	public String getCucdBatchBillingItem() {
		return (String) saveArray[ tableFldConstants.batch_billing_item.ordinal() ];
	}

	public void setCucdBatchBillingItem(String cucdBatchBillingItem) {
		saveArray[ tableFldConstants.batch_billing_item.ordinal() ] = cucdBatchBillingItem;
	}

	public String getCucdMaterial() {
		return (String) saveArray[ tableFldConstants.material.ordinal() ];
	}

	public void setCucdMaterial(String cucdMaterial) {
		saveArray[ tableFldConstants.material.ordinal() ] = cucdMaterial;
	}

	public String getCucdCause() {
		return (String) saveArray[ tableFldConstants.cause.ordinal() ];
	}

	public void setCucdCause(String cucdCause) {
		saveArray[ tableFldConstants.cause.ordinal() ] = cucdCause;
	}

	public String getCucdCurrentStatus() {
		return (String) saveArray[ tableFldConstants.current_status.ordinal() ];
	}

	public void setCucdCurrentStatus(String cucdCurrentStatus) {
		saveArray[ tableFldConstants.current_status.ordinal() ] = cucdCurrentStatus;
	}

	public String getCucdCauseDescription() {
		return (String) saveArray[ tableFldConstants.cause_description.ordinal() ];
	}

	public void setCucdCauseDescription(String cucdCauseDescription) {
		saveArray[ tableFldConstants.cause_description.ordinal() ] = cucdCauseDescription;
	}

	public String getCucdInvoiceQty() {
		return (String) saveArray[ tableFldConstants.invoice_qty.ordinal() ];
	}

	public void setCucdInvoiceQty(String cucdInvoiceQty) {
		saveArray[ tableFldConstants.invoice_qty.ordinal() ] = cucdInvoiceQty;
	}

	public String getCucdInvoicePrice() {
		return (String) saveArray[ tableFldConstants.invoice_price.ordinal() ];
	}

	public void setCucdInvoicePrice(String cucdInvoicePrice) {
		saveArray[ tableFldConstants.invoice_price.ordinal() ] = cucdInvoicePrice;
	}

	public String getCucdComplaintQty() {
		return (String) saveArray[ tableFldConstants.complaint_qty.ordinal() ];
	}

	public void setCucdComplaintQty(String cucdComplaintQty) {
		saveArray[ tableFldConstants.complaint_qty.ordinal() ] = cucdComplaintQty;
	}

	public String getCucdComplaintPrice() {
		return (String) saveArray[ tableFldConstants.complaint_price.ordinal() ];
	}

	public void setCucdComplaintPrice(String cucdComplaintPrice) {
		saveArray[ tableFldConstants.complaint_price.ordinal() ] = cucdComplaintPrice;
	}

	public String getCucdProvisionValue() {
		return (String) saveArray[ tableFldConstants.provision_value.ordinal() ];
	}

	public void setCucdProvisionValue(String cucdProvisionValue) {
		saveArray[ tableFldConstants.provision_value.ordinal() ] = cucdProvisionValue;
	}

	public String getCucdSampleReceivedDate() {
		return (String) saveArray[ tableFldConstants.sample_received_date.ordinal() ];
	}

	public void setCucdSampleReceivedDate(String cucdSampleReceivedDate) {
		saveArray[ tableFldConstants.sample_received_date.ordinal() ] = cucdSampleReceivedDate;
	}

	public String getCucdSampleSentDate() {
		return (String) saveArray[ tableFldConstants.sample_sent_date.ordinal() ];
	}

	public void setCucdSampleSentDate(String cucdSampleSentDate) {
		saveArray[ tableFldConstants.sample_sent_date.ordinal() ] = cucdSampleSentDate;
	}

	public String getCucdCategory() {
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setCucdCategory(String cucdCategory) {
		saveArray[ tableFldConstants.category.ordinal() ] = cucdCategory;
	}

	public String getCucdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setCucdStatus(String cucdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = cucdStatus;
	}

	public String getCucdRequestStart() {
		return (String) saveArray[ tableFldConstants.request_start.ordinal() ];
	}

	public void setCucdRequestStart(String cucdRequestStart) {
		saveArray[ tableFldConstants.request_start.ordinal() ] = cucdRequestStart;
	}

	public String getCucdRequestEnd() {
		return (String) saveArray[ tableFldConstants.request_end.ordinal() ];
	}

	public void setCucdRequestEnd(String cucdRequestEnd) {
		saveArray[ tableFldConstants.request_end.ordinal() ] = cucdRequestEnd;
	}

	public String getCucdPayer() {
		return (String) saveArray[ tableFldConstants.payer.ordinal() ];
	}

	public void setCucdPayer(String cucdPayer) {
		saveArray[ tableFldConstants.payer.ordinal() ] = cucdPayer;
	}

	public String getCucdBillToParty() {
		return (String) saveArray[ tableFldConstants.bill_to_party.ordinal() ];
	}

	public void setCucdBillToParty(String cucdBillToParty) {
		saveArray[ tableFldConstants.bill_to_party.ordinal() ] = cucdBillToParty;
	}

	public String getCucdBillingStatus() {
		return (String) saveArray[ tableFldConstants.billing_status.ordinal() ];
	}

	public void setCucdBillingStatus(String cucdBillingStatus) {
		saveArray[ tableFldConstants.billing_status.ordinal() ] = cucdBillingStatus;
	}

	public String getCucdSalesOrganization() {
		return (String) saveArray[ tableFldConstants.sales_organization.ordinal() ];
	}

	public void setCucdSalesOrganization(String cucdSalesOrganization) {
		saveArray[ tableFldConstants.sales_organization.ordinal() ] = cucdSalesOrganization;
	}

	public String getCucdDistributerChannel() {
		return (String) saveArray[ tableFldConstants.distributer_channel.ordinal() ];
	}

	public void setCucdDistributerChannel(String cucdDistributerChannel) {
		saveArray[ tableFldConstants.distributer_channel.ordinal() ] = cucdDistributerChannel;
	}

	public String getCucdDivision() {
		return (String) saveArray[ tableFldConstants.division.ordinal() ];
	}

	public void setCucdDivision(String cucdDivision) {
		saveArray[ tableFldConstants.division.ordinal() ] = cucdDivision;
	}

	public String getCucdSalesOffice() {
		return (String) saveArray[ tableFldConstants.sales_office.ordinal() ];
	}

	public void setCucdSalesOffice(String cucdSalesOffice) {
		saveArray[ tableFldConstants.sales_office.ordinal() ] = cucdSalesOffice;
	}

	public String getCucdSalesGroup() {
		return (String) saveArray[ tableFldConstants.sales_group.ordinal() ];
	}

	public void setCucdSalesGroup(String cucdSalesGroup) {
		saveArray[ tableFldConstants.sales_group.ordinal() ] = cucdSalesGroup;
	}

	public String getCucdOrgUnit() {
		return (String) saveArray[ tableFldConstants.org_unit.ordinal() ];
	}

	public void setCucdOrgUnit(String cucdOrgUnit) {
		saveArray[ tableFldConstants.org_unit.ordinal() ] = cucdOrgUnit;
	}

	public String getCucdServiceOrgUnit() {
		return (String) saveArray[ tableFldConstants.service_org_unit.ordinal() ];
	}

	public void setCucdServiceOrgUnit(String cucdServiceOrgUnit) {
		saveArray[ tableFldConstants.service_org_unit.ordinal() ] = cucdServiceOrgUnit;
	}

	public String getCucdFlag() {
		return (String) saveArray[ tableFldConstants.flag.ordinal() ];
	}

	public void setCucdFlag(String cucdFlag) {
		saveArray[ tableFldConstants.flag.ordinal() ] = cucdFlag;
	}

	public String getCucdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCucdTempfield1(String cucdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cucdTempfield1;
	}

	public String getCucdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCucdTempfield2(String cucdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cucdTempfield2;
	}

	public String getCucdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCucdTempfield3(String cucdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cucdTempfield3;
	}

	public String getCucdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCucdTempfield4(String cucdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = cucdTempfield4;
	}

	public String getCucdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCucdTempfield5(String cucdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = cucdTempfield5;
	}

	public String getCucdTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setCucdTempfield6(String cucdTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = cucdTempfield6;
	}

	public String getCucdTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setCucdTempfield7(String cucdTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = cucdTempfield7;
	}

	public String getCucdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCucdActive(String cucdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cucdActive;
	}

	public String getCucdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCucdCreatedby(String cucdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cucdCreatedby;
	}

	public String getCucdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCucdCreatedon(String cucdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cucdCreatedon;
	}

	public String getCucdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCucdModifiedon(String cucdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cucdModifiedon;
	}

}

