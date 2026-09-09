package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMommst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlMomattendance {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, moms_keyid, flid, date, employeeid, attandance, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public GenTlMomattendance()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}


	public String getMomaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMomaKeyid(String momaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = momaKeyid;
	}

	public String getMomaMomsKeyid() {
		return (String) saveArray[ tableFldConstants.moms_keyid.ordinal() ];
	}

	public void setMomaMomsKeyid(String momaMomsKeyid) {
		saveArray[ tableFldConstants.moms_keyid.ordinal() ] = momaMomsKeyid;
	}

	public String getMomaFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMomaFlid(String momaFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = momaFlid;
	}

	public String getMomaDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setMomaDate(String momaDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = momaDate;
	}

	public String getMomaEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setMomaEmployeeid(String momaEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = momaEmployeeid;
	}

	public String getMomaAttandance() {
		return (String) saveArray[ tableFldConstants.attandance.ordinal() ];
	}

	public void setMomaAttandance(String momaAttandance) {
		saveArray[ tableFldConstants.attandance.ordinal() ] = momaAttandance;
	}

	public String getMomaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMomaTempfield1(String momaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = momaTempfield1;
	}

	public String getMomaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMomaTempfield2(String momaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = momaTempfield2;
	}

	public String getMomaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMomaTempfield3(String momaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = momaTempfield3;
	}

	public String getMomaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMomaTempfield4(String momaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = momaTempfield4;
	}

	public String getMomaTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMomaTempfield5(String momaTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = momaTempfield5;
	}

	public String getMomaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMomaActive(String momaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = momaActive;
	}

	public String getMomaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMomaCreatedby(String momaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = momaCreatedby;
	}

	public String getMomaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMomaCreatedon(String momaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = momaCreatedon;
	}

	public String getMomaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMomaModifiedon(String momaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = momaModifiedon;
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
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
	  
	  public static GenTlMomattendance fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  GenTlMomattendance att = new GenTlMomattendance();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        att.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return att;
		}
	  
	  public static List<GenTlMomattendance> fromJsonList(String json) {

			CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

			JSONArray jsonArray = JSONArray.fromObject(json);
			List<GenTlMomattendance> list = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject obj = jsonArray.getJSONObject(i);
				GenTlMomattendance att = new GenTlMomattendance();

				for (tableFldConstants field : tableFldConstants.values()) {
					String key = field.name();

					Object valueObj = obj.opt(key);
					String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

					att.setValue(field, val);
				}

				list.add(att);
			}

			return list;
		}
		
		public static String toJsonManualList(List<GenTlMomattendance> list) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");

			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					sb.append(",");
				sb.append(list.get(i).toJsonManual());
			}

			sb.append("]");
			return sb.toString();
		}


	

}

