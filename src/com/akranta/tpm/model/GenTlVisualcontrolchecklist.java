package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlVisualcontrolchecklist {

	private  Object [] saveArray = null;
	private String elementid;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	private List<GenTlVisualcntchecklistdtl> visualControlDetail ;

	public enum   tableFldConstants
	{
		keyid, flid, employeeid, date, title, approvedby, tempfield3
		, tempfield4, tempfield5, tempfield6, active, createdby, createdon
		, modifiedon
	}

	public GenTlVisualcontrolchecklist()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVcclKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVcclKeyid(String vcclKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vcclKeyid;
	}

	public String getVcclFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setVcclFlid(String vcclFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = vcclFlid;
	}

	public String getVcclEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setVcclEmployeeid(String vcclEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = vcclEmployeeid;
	}

	public String getVcclDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setVcclDate(String vcclDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = vcclDate;
	}

	public String getVcclTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setVcclTitle(String vcclTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = vcclTitle;
	}

	public String getVcclApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setVcclApprovedby(String vcclApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = vcclApprovedby;
	}

	public String getVcclTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVcclTempfield3(String vcclTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vcclTempfield3;
	}

	public String getVcclTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVcclTempfield4(String vcclTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = vcclTempfield4;
	}

	public String getVcclTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setVcclTempfield5(String vcclTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = vcclTempfield5;
	}

	public String getVcclTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setVcclTempfield6(String vcclTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = vcclTempfield6;
	}

	public String getVcclActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVcclActive(String vcclActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vcclActive;
	}

	public String getVcclCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVcclCreatedby(String vcclCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vcclCreatedby;
	}

	public String getVcclCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVcclCreatedon(String vcclCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vcclCreatedon;
	}

	public String getVcclModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVcclModifiedon(String vcclModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vcclModifiedon;
	}

	public void setVisualControlDetail(List<GenTlVisualcntchecklistdtl> visualControlDetail) {
		this.visualControlDetail = visualControlDetail;
	}

	public List<GenTlVisualcntchecklistdtl> getVisualControlDetail() {
		return visualControlDetail;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}
	
	
	//19-jan
	
		public Object getValue(tableFldConstants field) {
			return saveArray[field.ordinal()];
		}

		public void setValue(tableFldConstants field, Object value) {
			saveArray[field.ordinal()] = value;
		}

		/**
		 * Convert object to JSON string manually
		 * ✅ FIXED: Properly handles null and "null" string values
		 */
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
					
					// ✅ FIXED: Properly handle null and "null" string
					if (val == null || (val instanceof String && 
					    (((String)val).trim().isEmpty() || ((String)val).equalsIgnoreCase("null")))) {
						sb.append("null");  // JSON null without quotes
					} else {
						// Escape special characters for JSON
						String strVal = val.toString()
							.replace("\\", "\\\\")
							.replace("\"", "\\\"")
							.replace("\n", "\\n")
							.replace("\r", "\\r")
							.replace("\t", "\\t");
						sb.append("\"").append(strVal).append("\"");
					}
					first = false;
				}
			}

			sb.append("}");
			return sb.toString();
		}

		/**
		 * Parse JSON string to object
		 */
		public static GenTlVisualcontrolchecklist fromJson(String json) {
			CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

			JSONObject obj = JSONObject.fromObject(json);

			GenTlVisualcontrolchecklist visualControl = new GenTlVisualcontrolchecklist();
			CommonMessage.debugMsg("RAW JSON Response: " + json);

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();
				
				CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
				String val = obj.optString(field.name(), null);
				visualControl.setValue(field, val != null && val.equals("null") ? null : val);
			}
			
			return visualControl;
		}

		/**
		 * Parse JSON array to List of objects
		 * ADDED: 03-01-2026 - Required for API getAllVisualControls() method
		 */
		public static List<GenTlVisualcontrolchecklist> fromJsonList(String json) {
			CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

			JSONArray jsonArray = JSONArray.fromObject(json);
			List<GenTlVisualcontrolchecklist> list = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				GenTlVisualcontrolchecklist visualControl = new GenTlVisualcontrolchecklist();

				for (tableFldConstants field : tableFldConstants.values()) {
					String key = field.name();
					Object valueObj = obj.opt(key);
					String val = (valueObj == null || "null".equals(valueObj.toString())) 
						? null : valueObj.toString();
					visualControl.setValue(field, val);
				}

				list.add(visualControl);
			}

			return list;
		}

		/**
		 * Convert List of objects to JSON array string
		 * ADDED: 03-01-2026 - Required for API batch operations
		 */
		public static String toJsonManualList(List<GenTlVisualcontrolchecklist> list) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");

			for (int i = 0; i < list.size(); i++) {
				if (i > 0) sb.append(",");
				sb.append(list.get(i).toJsonManual());
			}

			sb.append("]");
			return sb.toString();
		}
}