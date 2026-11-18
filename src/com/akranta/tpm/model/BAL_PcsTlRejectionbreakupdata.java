package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_PcsTlRejectionbreakupdata {

	private  Object [] saveArray = null;  
	

	
	public enum   tableFldConstants
	{
		keyid, pprd_keyid, type, phenomena, quantity, rejectionreason
		, defect, defectsource, equipment, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public BAL_PcsTlRejectionbreakupdata()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPrbdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPrbdKeyid(String prbdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = prbdKeyid;
	}

	public String getPrbdPprdKeyid() {
		return (String) saveArray[ tableFldConstants.pprd_keyid.ordinal() ];
	}

	public void setPrbdPprdKeyid(String prbdPprdKeyid) {
		saveArray[ tableFldConstants.pprd_keyid.ordinal() ] = prbdPprdKeyid;
	}

	public String getPrbdType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setPrbdType(String prbdType) {
		saveArray[ tableFldConstants.type.ordinal() ] = prbdType;
	}

	public String getPrbdPhenomena() {
		return (String) saveArray[ tableFldConstants.phenomena.ordinal() ];
	}

	public void setPrbdPhenomena(String prbdPhenomena) {
		saveArray[ tableFldConstants.phenomena.ordinal() ] = prbdPhenomena;
	}

	public String getPrbdQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setPrbdQuantity(String prbdQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = prbdQuantity;
	}

	public String getPrbdRejectionreason() {
		return (String) saveArray[ tableFldConstants.rejectionreason.ordinal() ];
	}

	public void setPrbdRejectionreason(String prbdRejectionreason) {
		saveArray[ tableFldConstants.rejectionreason.ordinal() ] = prbdRejectionreason;
	}

	public String getPrbdDefect() {
		return (String) saveArray[ tableFldConstants.defect.ordinal() ];
	}

	public void setPrbdDefect(String prbdDefect) {
		saveArray[ tableFldConstants.defect.ordinal() ] = prbdDefect;
	}

	public String getPrbdDefectsource() {
		return (String) saveArray[ tableFldConstants.defectsource.ordinal() ];
	}

	public void setPrbdDefectsource(String prbdDefectsource) {
		saveArray[ tableFldConstants.defectsource.ordinal() ] = prbdDefectsource;
	}

	public String getPrbdEquipment() {
		return (String) saveArray[ tableFldConstants.equipment.ordinal() ];
	}

	public void setPrbdEquipment(String prbdEquipment) {
		saveArray[ tableFldConstants.equipment.ordinal() ] = prbdEquipment;
	}

	public String getPrbdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPrbdTempfield1(String prbdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = prbdTempfield1;
	}

	public String getPrbdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPrbdTempfield2(String prbdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = prbdTempfield2;
	}

	public String getPrbdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPrbdTempfield3(String prbdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = prbdTempfield3;
	}

	public String getPrbdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPrbdTempfield4(String prbdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = prbdTempfield4;
	}

	public String getPrbdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPrbdActive(String prbdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = prbdActive;
	}

	public String getPrbdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPrbdCreatedby(String prbdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = prbdCreatedby;
	}

	public String getPrbdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPrbdCreatedon(String prbdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = prbdCreatedon;
	}

	public String getPrbdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPrbdModifiedon(String prbdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = prbdModifiedon;
	}
	
	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder("{");
	    boolean first = true;
	    for (tableFldConstants field : tableFldConstants.values()) {
	        int index = field.ordinal();
	        if (index >= saveArray.length) continue;

	        if (!first) sb.append(",");
	        first = false;

	        // lowercase key so Jackson matches the entity property
	        sb.append("\"").append(field.name().toLowerCase()).append("\":");

	        Object val = saveArray[index];
	        if (val == null) {
	            sb.append("null");                       // real JSON null, not "{}"
	        } else {
	            sb.append("\"").append(escape(val.toString())).append("\"");
	        }
	    }
	    sb.append("}");
	    return sb.toString();
	}

	private static String escape(String s) {
	    return s.replace("\\", "\\\\").replace("\"", "\\\"")
	            .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
	}

}

