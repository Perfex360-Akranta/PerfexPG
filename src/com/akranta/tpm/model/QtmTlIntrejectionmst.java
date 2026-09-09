package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMommst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlIntrejectionmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, plmasterid, pldetailid
		, prodgroupid, productid, cavity, inspectiondate, inspectionid
		, totalproduction, testingscrap, batchno, inspectionqty, acceptedqty
		, backlogqty, qaholdqty, qaholdpercentage, shiftdate, shiftid
		, entryby, productiondate, qualityentryby, qualityentrydate, approvalby
		, approvaldate, status, backlogflag, referencekeyid, remarks
		, mrbqty, inspectedshiftid, balanceqty, linkmasterid, actualproduced
		, virtualproduced, parentmasterid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5,elementid,flid, active, createdby, createdon, modifiedon
	}

	public QtmTlIntrejectionmst()
	{
		saveArray = new  Object [ 50 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public String getQirmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQirmKeyid(String qirmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qirmKeyid;
	}

	public String getQirmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setQirmFactoryid(String qirmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = qirmFactoryid;
	}

	public String getQirmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setQirmSectionid(String qirmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = qirmSectionid;
	}

	public String getQirmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setQirmCellid(String qirmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = qirmCellid;
	}

	public String getQirmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setQirmMachineid(String qirmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = qirmMachineid;
	}

	public String getQirmPlmasterid() {
		return (String) saveArray[ tableFldConstants.plmasterid.ordinal() ];
	}

	public void setQirmPlmasterid(String qirmPlmasterid) {
		saveArray[ tableFldConstants.plmasterid.ordinal() ] = qirmPlmasterid;
	}

	public String getQirmPldetailid() {
		return (String) saveArray[ tableFldConstants.pldetailid.ordinal() ];
	}

	public void setQirmPldetailid(String qirmPldetailid) {
		saveArray[ tableFldConstants.pldetailid.ordinal() ] = qirmPldetailid;
	}

	public String getQirmProdgroupid() {
		return (String) saveArray[ tableFldConstants.prodgroupid.ordinal() ];
	}

	public void setQirmProdgroupid(String qirmProdgroupid) {
		saveArray[ tableFldConstants.prodgroupid.ordinal() ] = qirmProdgroupid;
	}

	public String getQirmProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setQirmProductid(String qirmProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = qirmProductid;
	}

	public String getQirmCavity() {
		return (String) saveArray[ tableFldConstants.cavity.ordinal() ];
	}

	public void setQirmCavity(String qirmCavity) {
		saveArray[ tableFldConstants.cavity.ordinal() ] = qirmCavity;
	}

	public String getQirmInspectiondate() {
		return (String) saveArray[ tableFldConstants.inspectiondate.ordinal() ];
	}

	public void setQirmInspectiondate(String qirmInspectiondate) {
		saveArray[ tableFldConstants.inspectiondate.ordinal() ] = qirmInspectiondate;
	}

	public String getQirmInspectionid() {
		return (String) saveArray[ tableFldConstants.inspectionid.ordinal() ];
	}

	public void setQirmInspectionid(String qirmInspectionid) {
		saveArray[ tableFldConstants.inspectionid.ordinal() ] = qirmInspectionid;
	}

	public String getQirmTotalproduction() {
		return (String) saveArray[ tableFldConstants.totalproduction.ordinal() ];
	}

	public void setQirmTotalproduction(String qirmTotalproduction) {
		saveArray[ tableFldConstants.totalproduction.ordinal() ] = qirmTotalproduction;
	}

	public String getQirmTestingscrap() {
		return (String) saveArray[ tableFldConstants.testingscrap.ordinal() ];
	}

	public void setQirmTestingscrap(String qirmTestingscrap) {
		saveArray[ tableFldConstants.testingscrap.ordinal() ] = qirmTestingscrap;
	}

	public String getQirmBatchno() {
		return (String) saveArray[ tableFldConstants.batchno.ordinal() ];
	}

	public void setQirmBatchno(String qirmBatchno) {
		saveArray[ tableFldConstants.batchno.ordinal() ] = qirmBatchno;
	}

	public String getQirmInspectionqty() {
		return (String) saveArray[ tableFldConstants.inspectionqty.ordinal() ];
	}

	public void setQirmInspectionqty(String qirmInspectionqty) {
		saveArray[ tableFldConstants.inspectionqty.ordinal() ] = qirmInspectionqty;
	}

	public String getQirmAcceptedqty() {
		return (String) saveArray[ tableFldConstants.acceptedqty.ordinal() ];
	}

	public void setQirmAcceptedqty(String qirmAcceptedqty) {
		saveArray[ tableFldConstants.acceptedqty.ordinal() ] = qirmAcceptedqty;
	}

	public String getQirmBacklogqty() {
		return (String) saveArray[ tableFldConstants.backlogqty.ordinal() ];
	}

	public void setQirmBacklogqty(String qirmBacklogqty) {
		saveArray[ tableFldConstants.backlogqty.ordinal() ] = qirmBacklogqty;
	}

	public String getQirmQaholdqty() {
		return (String) saveArray[ tableFldConstants.qaholdqty.ordinal() ];
	}

	public void setQirmQaholdqty(String qirmQaholdqty) {
		saveArray[ tableFldConstants.qaholdqty.ordinal() ] = qirmQaholdqty;
	}

	public String getQirmQaholdpercentage() {
		return (String) saveArray[ tableFldConstants.qaholdpercentage.ordinal() ];
	}

	public void setQirmQaholdpercentage(String qirmQaholdpercentage) {
		saveArray[ tableFldConstants.qaholdpercentage.ordinal() ] = qirmQaholdpercentage;
	}

	public String getQirmShiftdate() {
		return (String) saveArray[ tableFldConstants.shiftdate.ordinal() ];
	}

	public void setQirmShiftdate(String qirmShiftdate) {
		saveArray[ tableFldConstants.shiftdate.ordinal() ] = qirmShiftdate;
	}

	public String getQirmShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setQirmShiftid(String qirmShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = qirmShiftid;
	}

	public String getQirmEntryby() {
		return (String) saveArray[ tableFldConstants.entryby.ordinal() ];
	}

	public void setQirmEntryby(String qirmEntryby) {
		saveArray[ tableFldConstants.entryby.ordinal() ] = qirmEntryby;
	}

	public String getQirmProductiondate() {
		return (String) saveArray[ tableFldConstants.productiondate.ordinal() ];
	}

	public void setQirmProductiondate(String qirmProductiondate) {
		saveArray[ tableFldConstants.productiondate.ordinal() ] = qirmProductiondate;
	}

	public String getQirmQualityentryby() {
		return (String) saveArray[ tableFldConstants.qualityentryby.ordinal() ];
	}

	public void setQirmQualityentryby(String qirmQualityentryby) {
		saveArray[ tableFldConstants.qualityentryby.ordinal() ] = qirmQualityentryby;
	}

	public String getQirmQualityentrydate() {
		return (String) saveArray[ tableFldConstants.qualityentrydate.ordinal() ];
	}

	public void setQirmQualityentrydate(String qirmQualityentrydate) {
		saveArray[ tableFldConstants.qualityentrydate.ordinal() ] = qirmQualityentrydate;
	}

	public String getQirmApprovalby() {
		return (String) saveArray[ tableFldConstants.approvalby.ordinal() ];
	}

	public void setQirmApprovalby(String qirmApprovalby) {
		saveArray[ tableFldConstants.approvalby.ordinal() ] = qirmApprovalby;
	}

	public String getQirmApprovaldate() {
		return (String) saveArray[ tableFldConstants.approvaldate.ordinal() ];
	}

	public void setQirmApprovaldate(String qirmApprovaldate) {
		saveArray[ tableFldConstants.approvaldate.ordinal() ] = qirmApprovaldate;
	}

	public String getQirmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setQirmStatus(String qirmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = qirmStatus;
	}

	public String getQirmBacklogflag() {
		return (String) saveArray[ tableFldConstants.backlogflag.ordinal() ];
	}

	public void setQirmBacklogflag(String qirmBacklogflag) {
		saveArray[ tableFldConstants.backlogflag.ordinal() ] = qirmBacklogflag;
	}

	public String getQirmReferencekeyid() {
		return (String) saveArray[ tableFldConstants.referencekeyid.ordinal() ];
	}

	public void setQirmReferencekeyid(String qirmReferencekeyid) {
		saveArray[ tableFldConstants.referencekeyid.ordinal() ] = qirmReferencekeyid;
	}

	public String getQirmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setQirmRemarks(String qirmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = qirmRemarks;
	}

	public String getQirmMrbqty() {
		return (String) saveArray[ tableFldConstants.mrbqty.ordinal() ];
	}

	public void setQirmMrbqty(String qirmMrbqty) {
		saveArray[ tableFldConstants.mrbqty.ordinal() ] = qirmMrbqty;
	}

	public String getQirmInspectedshiftid() {
		return (String) saveArray[ tableFldConstants.inspectedshiftid.ordinal() ];
	}

	public void setQirmInspectedshiftid(String qirmInspectedshiftid) {
		saveArray[ tableFldConstants.inspectedshiftid.ordinal() ] = qirmInspectedshiftid;
	}

	public String getQirmBalanceqty() {
		return (String) saveArray[ tableFldConstants.balanceqty.ordinal() ];
	}

	public void setQirmBalanceqty(String qirmBalanceqty) {
		saveArray[ tableFldConstants.balanceqty.ordinal() ] = qirmBalanceqty;
	}

	public String getQirmLinkmasterid() {
		return (String) saveArray[ tableFldConstants.linkmasterid.ordinal() ];
	}

	public void setQirmLinkmasterid(String qirmLinkmasterid) {
		saveArray[ tableFldConstants.linkmasterid.ordinal() ] = qirmLinkmasterid;
	}

	public String getQirmActualproduced() {
		return (String) saveArray[ tableFldConstants.actualproduced.ordinal() ];
	}

	public void setQirmActualproduced(String qirmActualproduced) {
		saveArray[ tableFldConstants.actualproduced.ordinal() ] = qirmActualproduced;
	}

	public String getQirmVirtualproduced() {
		return (String) saveArray[ tableFldConstants.virtualproduced.ordinal() ];
	}

	public void setQirmVirtualproduced(String qirmVirtualproduced) {
		saveArray[ tableFldConstants.virtualproduced.ordinal() ] = qirmVirtualproduced;
	}

	public String getQirmParentmasterid() {
		return (String) saveArray[ tableFldConstants.parentmasterid.ordinal() ];
	}

	public void setQirmParentmasterid(String qirmParentmasterid) {
		saveArray[ tableFldConstants.parentmasterid.ordinal() ] = qirmParentmasterid;
	}

	public String getQirmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setQirmTempfield1(String qirmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = qirmTempfield1;
	}

	public String getQirmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQirmTempfield2(String qirmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qirmTempfield2;
	}

	public String getQirmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQirmTempfield3(String qirmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qirmTempfield3;
	}

	public String getQirmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQirmTempfield4(String qirmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qirmTempfield4;
	}

	public String getQirmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setQirmTempfield5(String qirmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = qirmTempfield5;
	}

	public String getQirmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQirmActive(String qirmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qirmActive;
	}

	public String getQirmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQirmCreatedby(String qirmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qirmCreatedby;
	}

	public String getQirmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQirmCreatedon(String qirmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qirmCreatedon;
	}

	public String getQirmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQirmModifiedon(String qirmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qirmModifiedon;
	}

	public String getQirmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setQirmElementid(String qirmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = qirmElementid;
		
	}

	public String getQirmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setQirmFlid(String qirmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = qirmFlid;
		
	}

	public void setSaveArray(Object[] dataArr) {
		this.saveArray = dataArr;
		
	}
	

	  public String toJsonManual() {
		    StringBuilder sb = new StringBuilder();
		    sb.append("{");
		


		    boolean first = true;
		    for (tableFldConstants field : tableFldConstants.values()) {
		        int index = field.ordinal();
		        if (index < saveArray.length) {
		            if (!first) sb.append(",");
		            sb.append("\"").append(field.name()).append("\":");
		            Object val = saveArray[index];
		            if(field.name() == "keyid" && val == null) {
		            	sb.append("null");
		            }else if (val == null) {
		                sb.append("\"{}\"");
		            } else {
		                sb.append("\"").append(val.toString()).append("\"");
		            }
		            first = false;
		        }
		    }

		   
		    sb.append("}");
		    return sb.toString();
		}
	  
	  public static QtmTlIntrejectionmst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  QtmTlIntrejectionmst mst = new QtmTlIntrejectionmst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        mst.setValue(field, val);
		    	
		    	
		    }
		    return mst;
		}

}

