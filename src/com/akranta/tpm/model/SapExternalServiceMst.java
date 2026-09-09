package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SapExternalServiceMst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, notificationno, orderno, date, shift, flid, equipment
		, operation_qty, price, material_group, purchase_group, agreement
		, recipient, requisitioner, plan_deliverytime, ext_sub_contract
		, sortterm, per, cost_element, vendor, info_record, unload_point
		, track_no, fw_order, type, active, createdby, createdon, modifiedon,taskid,purchaseOrg
	}

	public SapExternalServiceMst()
	{
		saveArray = new  Object [ 31 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getExtmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setExtmKeyid(String extmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = extmKeyid;
	}

	public String getExtmNotificationno() {
		return (String) saveArray[ tableFldConstants.notificationno.ordinal() ];
	}

	public void setExtmNotificationno(String extmNotificationno) {
		saveArray[ tableFldConstants.notificationno.ordinal() ] = extmNotificationno;
	}

	public String getExtmOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setExtmOrderno(String extmOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = extmOrderno;
	}

	public String getExtmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setExtmDate(String extmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = extmDate;
	}

	public String getExtmShift() {
		return (String) saveArray[ tableFldConstants.shift.ordinal() ];
	}

	public void setExtmShift(String extmShift) {
		saveArray[ tableFldConstants.shift.ordinal() ] = extmShift;
	}

	public String getExtmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setExtmFlid(String extmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = extmFlid;
	}

	public String getExtmEquipment() {
		return (String) saveArray[ tableFldConstants.equipment.ordinal() ];
	}

	public void setExtmEquipment(String extmEquipment) {
		saveArray[ tableFldConstants.equipment.ordinal() ] = extmEquipment;
	}

	public String getExtmOperationQty() {
		return (String) saveArray[ tableFldConstants.operation_qty.ordinal() ];
	}

	public void setExtmOperationQty(String extmOperationQty) {
		saveArray[ tableFldConstants.operation_qty.ordinal() ] = extmOperationQty;
	}

	public String getExtmPrice() {
		return (String) saveArray[ tableFldConstants.price.ordinal() ];
	}

	public void setExtmPrice(String extmPrice) {
		saveArray[ tableFldConstants.price.ordinal() ] = extmPrice;
	}

	public String getExtmMaterialGroup() {
		return (String) saveArray[ tableFldConstants.material_group.ordinal() ];
	}

	public void setExtmMaterialGroup(String extmMaterialGroup) {
		saveArray[ tableFldConstants.material_group.ordinal() ] = extmMaterialGroup;
	}

	public String getExtmPurchaseGroup() {
		return (String) saveArray[ tableFldConstants.purchase_group.ordinal() ];
	}

	public void setExtmPurchaseGroup(String extmPurchaseGroup) {
		saveArray[ tableFldConstants.purchase_group.ordinal() ] = extmPurchaseGroup;
	}

	public String getExtmAgreement() {
		return (String) saveArray[ tableFldConstants.agreement.ordinal() ];
	}

	public void setExtmAgreement(String extmAgreement) {
		saveArray[ tableFldConstants.agreement.ordinal() ] = extmAgreement;
	}

	public String getExtmRecipient() {
		return (String) saveArray[ tableFldConstants.recipient.ordinal() ];
	}

	public void setExtmRecipient(String extmRecipient) {
		saveArray[ tableFldConstants.recipient.ordinal() ] = extmRecipient;
	}

	public String getExtmRequisitioner() {
		return (String) saveArray[ tableFldConstants.requisitioner.ordinal() ];
	}

	public void setExtmRequisitioner(String extmRequisitioner) {
		saveArray[ tableFldConstants.requisitioner.ordinal() ] = extmRequisitioner;
	}

	public String getExtmPlanDeliverytime() {
		return (String) saveArray[ tableFldConstants.plan_deliverytime.ordinal() ];
	}

	public void setExtmPlanDeliverytime(String extmPlanDeliverytime) {
		saveArray[ tableFldConstants.plan_deliverytime.ordinal() ] = extmPlanDeliverytime;
	}

	public String getExtmExtSubContract() {
		return (String) saveArray[ tableFldConstants.ext_sub_contract.ordinal() ];
	}

	public void setExtmExtSubContract(String extmExtSubContract) {
		saveArray[ tableFldConstants.ext_sub_contract.ordinal() ] = extmExtSubContract;
	}

	public String getExtmSortterm() {
		return (String) saveArray[ tableFldConstants.sortterm.ordinal() ];
	}

	public void setExtmSortterm(String extmSortterm) {
		saveArray[ tableFldConstants.sortterm.ordinal() ] = extmSortterm;
	}

	public String getExtmPer() {
		return (String) saveArray[ tableFldConstants.per.ordinal() ];
	}

	public void setExtmPer(String extmPer) {
		saveArray[ tableFldConstants.per.ordinal() ] = extmPer;
	}

	public String getExtmCostElement() {
		return (String) saveArray[ tableFldConstants.cost_element.ordinal() ];
	}

	public void setExtmCostElement(String extmCostElement) {
		saveArray[ tableFldConstants.cost_element.ordinal() ] = extmCostElement;
	}

	public String getExtmVendor() {
		return (String) saveArray[ tableFldConstants.vendor.ordinal() ];
	}

	public void setExtmVendor(String extmVendor) {
		saveArray[ tableFldConstants.vendor.ordinal() ] = extmVendor;
	}

	public String getExtmInfoRecord() {
		return (String) saveArray[ tableFldConstants.info_record.ordinal() ];
	}

	public void setExtmInfoRecord(String extmInfoRecord) {
		saveArray[ tableFldConstants.info_record.ordinal() ] = extmInfoRecord;
	}

	public String getExtmUnloadPoint() {
		return (String) saveArray[ tableFldConstants.unload_point.ordinal() ];
	}

	public void setExtmUnloadPoint(String extmUnloadPoint) {
		saveArray[ tableFldConstants.unload_point.ordinal() ] = extmUnloadPoint;
	}

	public String getExtmTrackNo() {
		return (String) saveArray[ tableFldConstants.track_no.ordinal() ];
	}

	public void setExtmTrackNo(String extmTrackNo) {
		saveArray[ tableFldConstants.track_no.ordinal() ] = extmTrackNo;
	}

	public String getExtmFwOrder() {
		return (String) saveArray[ tableFldConstants.fw_order.ordinal() ];
	}

	public void setExtmFwOrder(String extmFwOrder) {
		saveArray[ tableFldConstants.fw_order.ordinal() ] = extmFwOrder;
	}

	public String getExtmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setExtmType(String extmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = extmType;
	}

	public String getExtmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setExtmActive(String extmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = extmActive;
	}

	public String getExtmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setExtmCreatedby(String extmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = extmCreatedby;
	}

	public String getExtmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setExtmCreatedon(String extmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = extmCreatedon;
	}

	public String getExtmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setExtmModifiedon(String extmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = extmModifiedon;
	}
	
	public String getExtmTaskId() {
		return (String) saveArray[ tableFldConstants.taskid.ordinal() ];
	}

	public void setExtmTaskId(String extmTaskId) {
		saveArray[ tableFldConstants.taskid.ordinal() ] = extmTaskId;
	}
	public String getExtmPurchaseOrg() {
		return (String) saveArray[ tableFldConstants.purchaseOrg.ordinal() ];
	}

	public void setExtmPurchaseOrg(String extmPurchaseOrg) {
		saveArray[ tableFldConstants.purchaseOrg.ordinal() ] = extmPurchaseOrg;
	}

	
	public void setSaveArray(Object[] dataArr) {
		// TODO Auto-generated method stub
		this.saveArray = dataArr ;
	}

}

