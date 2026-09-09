package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMomdtl.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class GenTlEmployeedtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, birthdate, address, cityid, stateid, countryid, phone
		, image, remarks, currentexperience, otherexperience, totalexperience
		, qualification, discipline, active, createdby, createdon, modifiedon
	}

	public GenTlEmployeedtl()
	{
		saveArray = new  Object [ 18 ];
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
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEmpdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEmpdKeyid(String empdkeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = empdkeyid;
	}

	public String getEmpdBirthdate() {
		return (String) saveArray[ tableFldConstants.birthdate.ordinal() ];
	}

	public void setEmpdBirthdate(String empdbirthdate) {
		saveArray[ tableFldConstants.birthdate.ordinal() ] = empdbirthdate;
	}

	public String getEmpdAddress() {
		return (String) saveArray[ tableFldConstants.address.ordinal() ];
	}

	public void setEmpdAddress(String empdaddress) {
		saveArray[ tableFldConstants.address.ordinal() ] = empdaddress;
	}

	public String getEmpdCityid() {
		return (String) saveArray[ tableFldConstants.cityid.ordinal() ];
	}

	public void setEmpdCityid(String empdcityid) {
		saveArray[ tableFldConstants.cityid.ordinal() ] = empdcityid;
	}

	public String getEmpdStateid() {
		return (String) saveArray[ tableFldConstants.stateid.ordinal() ];
	}

	public void setEmpdStateid(String empdstateid) {
		saveArray[ tableFldConstants.stateid.ordinal() ] = empdstateid;
	}

	public String getEmpdCountryid() {
		return (String) saveArray[ tableFldConstants.countryid.ordinal() ];
	}

	public void setEmpdCountryid(String empdcountryid) {
		saveArray[ tableFldConstants.countryid.ordinal() ] = empdcountryid;
	}

	public String getEmpdPhone() {
		return (String) saveArray[ tableFldConstants.phone.ordinal() ];
	}

	public void setEmpdPhone(String empdphone) {
		saveArray[ tableFldConstants.phone.ordinal() ] = empdphone;
	}

	public String getEmpdImage() {
		return (String) saveArray[ tableFldConstants.image.ordinal() ];
	}

	public void setEmpdImage(String empdimage) {
		saveArray[ tableFldConstants.image.ordinal() ] = empdimage;
	}

	public String getEmpdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEmpdRemarks(String empdremarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = empdremarks;
	}

	public String getEmpdCurrentexperience() {
		return (String) saveArray[ tableFldConstants.currentexperience.ordinal() ];
	}

	public void setEmpdCurrentexperience(String empdcurrentexperience) {
		saveArray[ tableFldConstants.currentexperience.ordinal() ] = empdcurrentexperience;
	}

	public String getEmpdOtherexperience() {
		return (String) saveArray[ tableFldConstants.otherexperience.ordinal() ];
	}

	public void setEmpdOtherexperience(String empdotherexperience) {
		saveArray[ tableFldConstants.otherexperience.ordinal() ] = empdotherexperience;
	}

	public String getEmpdTotalexperience() {
		return (String) saveArray[ tableFldConstants.totalexperience.ordinal() ];
	}

	public void setEmpdTotalexperience(String empdtotalexperience) {
		saveArray[ tableFldConstants.totalexperience.ordinal() ] = empdtotalexperience;
	}

	public String getEmpdQualification() {
		return (String) saveArray[ tableFldConstants.qualification.ordinal() ];
	}

	public void setEmpdQualification(String empdqualification) {
		saveArray[ tableFldConstants.qualification.ordinal() ] = empdqualification;
	}

	public String getEmpdDiscipline() {
		return (String) saveArray[ tableFldConstants.discipline.ordinal() ];
	}

	public void setEmpdDiscipline(String empddiscipline) {
		saveArray[ tableFldConstants.discipline.ordinal() ] = empddiscipline;
	}

	public String getEmpdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEmpdActive(String empdactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = empdactive;
	}

	public String getEmpdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEmpdCreatedby(String empdcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = empdcreatedby;
	}

	public String getEmpdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEmpdCreatedon(String empdcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = empdcreatedon;
	}

	public String getEmpdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEmpdModifiedon(String empdmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = empdmodifiedon;
	}
	
	public String toJsonManual() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		boolean first = true;
		for (tableFldConstants field : tableFldConstants.values()) {
			int index = field.ordinal();
			if (index < saveArray.length) {
				if (!first)
					sb.append(",");
				sb.append("\"").append(field.name()).append("\":");
				Object val = saveArray[index];
				if (field.name() == "keyid" && val == null) {
					sb.append("null");
				} else if (val == null) {
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

	public static GenTlEmployeedtl fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		GenTlEmployeedtl dtl = new GenTlEmployeedtl();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			
			   if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
			dtl.setValue(field, val);

		}
		return dtl;
	}

	public static List<GenTlEmployeedtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<GenTlEmployeedtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			GenTlEmployeedtl dtl = new GenTlEmployeedtl();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

				dtl.setValue(field, val);
			}

			list.add(dtl);
		}

		return list;
	}
	
	public static String toJsonManualList(List<GenTlEmployeedtl> list) {
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

