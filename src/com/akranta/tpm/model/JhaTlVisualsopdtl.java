package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.JhaTlVisualsopmst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlVisualsopdtl {

	private  Object [] saveArray = null;  
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;

	public enum   tableFldConstants
	{
		keyid, vsom_keyid, instruction, keypoint, importanceofkeypoint
		, toolused, imgtoolused, imgppe, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public JhaTlVisualsopdtl()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVsodKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVsodKeyid(String vsodKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vsodKeyid;
	}

	public String getVsodVsomKeyid() {
		return (String) saveArray[ tableFldConstants.vsom_keyid.ordinal() ];
	}

	public void setVsodVsomKeyid(String vsodVsomKeyid) {
		saveArray[ tableFldConstants.vsom_keyid.ordinal() ] = vsodVsomKeyid;
	}

	public String getVsodInstruction() {
		return (String) saveArray[ tableFldConstants.instruction.ordinal() ];
	}

	public void setVsodInstruction(String vsodInstruction) {
		saveArray[ tableFldConstants.instruction.ordinal() ] = vsodInstruction;
	}

	public String getVsodKeypoint() {
		return (String) saveArray[ tableFldConstants.keypoint.ordinal() ];
	}

	public void setVsodKeypoint(String vsodKeypoint) {
		saveArray[ tableFldConstants.keypoint.ordinal() ] = vsodKeypoint;
	}

	public String getVsodImportanceofkeypoint() {
		return (String) saveArray[ tableFldConstants.importanceofkeypoint.ordinal() ];
	}

	public void setVsodImportanceofkeypoint(String vsodImportanceofkeypoint) {
		saveArray[ tableFldConstants.importanceofkeypoint.ordinal() ] = vsodImportanceofkeypoint;
	}

	public String getVsodToolused() {
		return (String) saveArray[ tableFldConstants.toolused.ordinal() ];
	}

	public void setVsodToolused(String vsodToolused) {
		saveArray[ tableFldConstants.toolused.ordinal() ] = vsodToolused;
	}

	public String getVsodImgtoolused() {
		return (String) saveArray[ tableFldConstants.imgtoolused.ordinal() ];
	}

	public void setVsodImgtoolused(String vsodImgtoolused) {
		saveArray[ tableFldConstants.imgtoolused.ordinal() ] = vsodImgtoolused;
	}

	public String getVsodImgppe() {
		return (String) saveArray[ tableFldConstants.imgppe.ordinal() ];
	}

	public void setVsodImgppe(String vsodImgppe) {
		saveArray[ tableFldConstants.imgppe.ordinal() ] = vsodImgppe;
	}

	public String getVsodTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setVsodTempfield1(String vsodTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = vsodTempfield1;
	}

	public String getVsodTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setVsodTempfield2(String vsodTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = vsodTempfield2;
	}

	public String getVsodTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVsodTempfield3(String vsodTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vsodTempfield3;
	}

	public String getVsodTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVsodTempfield4(String vsodTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = vsodTempfield4;
	}

	public String getVsodTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setVsodTempfield5(String vsodTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = vsodTempfield5;
	}

	public String getVsodActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVsodActive(String vsodActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vsodActive;
	}

	public String getVsodCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVsodCreatedby(String vsodCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vsodCreatedby;
	}

	public String getVsodCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVsodCreatedon(String vsodCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vsodCreatedon;
	}

	public String getVsodModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVsodModifiedon(String vsodModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vsodModifiedon;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}

	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> allmoduleimgfile) {
		this.allmoduleimgfile = allmoduleimgfile;
	}

	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
	}
	
	//getter setter
	
	public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public void setValue(tableFldConstants field, Object value) {
        saveArray[field.ordinal()] = value;
    }

	
	//tojsonmanual
	
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
                if (field.name().equals("keyid") && val == null) {
                    sb.append("null");
                } else if (val == null) {
                    sb.append("\"\"");
                } else {
                    sb.append("\"").append(val.toString().replace("\"", "\\\"")).append("\"");
                }
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }
	
	//from json
	
	public static JhaTlVisualsopdtl fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response (Master): [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        JhaTlVisualsopdtl dtl = new JhaTlVisualsopdtl();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.opt(key));
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            dtl.setValue(field, val);
        }

        return dtl;
    }

}

