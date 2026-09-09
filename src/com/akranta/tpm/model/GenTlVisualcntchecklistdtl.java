package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.utils.CommonMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlVisualcntchecklistdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, vccl_keyid, vccd_keyid, criteriaval, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public GenTlVisualcntchecklistdtl()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVcdtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVcdtKeyid(String vcdtKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vcdtKeyid;
	}

	public String getVcdtVcclKeyid() {
		return (String) saveArray[ tableFldConstants.vccl_keyid.ordinal() ];
	}

	public void setVcdtVcclKeyid(String vcdtVcclKeyid) {
		saveArray[ tableFldConstants.vccl_keyid.ordinal() ] = vcdtVcclKeyid;
	}

	public String getVcdtVccdKeyid() {
		return (String) saveArray[ tableFldConstants.vccd_keyid.ordinal() ];
	}

	public void setVcdtVccdKeyid(String vcdtVccdkeyid) {
		saveArray[ tableFldConstants.vccd_keyid.ordinal() ] = vcdtVccdkeyid;
	}

	public String getVcdtCriteriaval() {
		return (String) saveArray[ tableFldConstants.criteriaval.ordinal() ];
	}

	public void setVcdtCriteriaval(String vcdtCriteriaval) {
		saveArray[ tableFldConstants.criteriaval.ordinal() ] = vcdtCriteriaval;
	}

	public String getVcdtTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setVcdtTempfield1(String vcdtTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = vcdtTempfield1;
	}

	public String getVcdtTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setVcdtTempfield2(String vcdtTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = vcdtTempfield2;
	}

	public String getVcdtTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVcdtTempfield3(String vcdtTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vcdtTempfield3;
	}

	public String getVcdtTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVcdtTempfield4(String vcdtTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = vcdtTempfield4;
	}

	public String getVcdtTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setVcdtTempfield5(String vcdtTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = vcdtTempfield5;
	}

	public String getVcdtTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setVcdtTempfield6(String vcdtTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = vcdtTempfield6;
	}

	public String getVcdtActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVcdtActive(String vcdtActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vcdtActive;
	}

	public String getVcdtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVcdtCreatedby(String vcdtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vcdtCreatedby;
	}

	public String getVcdtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVcdtCreatedon(String vcdtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vcdtCreatedon;
	}

	public String getVcdtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVcdtModifiedon(String vcdtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vcdtModifiedon;
	}

	//19-jan
	
		// JSON serialization methods - Added 03-01-2026
		
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
			public static GenTlVisualcntchecklistdtl fromJson(String json) {
				CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

				JSONObject obj = JSONObject.fromObject(json);
				GenTlVisualcntchecklistdtl dtl = new GenTlVisualcntchecklistdtl();
				CommonMessage.debugMsg("RAW JSON Response: :" + json);

				for (tableFldConstants field : tableFldConstants.values()) {
					String key = field.name();
					CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
					String val = obj.optString(field.name(), null);
					dtl.setValue(field, val != null && val.equals("null") ? null : val);
				}

				return dtl;
			}

			/**
			 * Parse JSON array to List of objects
			 * ADDED: 03-01-2026 - Required for API operations
			 */
			public static List<GenTlVisualcntchecklistdtl> fromJsonList(String json) {
				CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

				JSONArray jsonArray = JSONArray.fromObject(json);
				List<GenTlVisualcntchecklistdtl> list = new ArrayList<>();

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					GenTlVisualcntchecklistdtl dtl = new GenTlVisualcntchecklistdtl();

					for (tableFldConstants field : tableFldConstants.values()) {
						String key = field.name();
						Object valueObj = obj.opt(key);
						String val = (valueObj == null || "null".equals(valueObj.toString())) 
							? null : valueObj.toString();
						dtl.setValue(field, val);
					}

					list.add(dtl);
				}

				return list;
			}

			/**
			 * Convert List of objects to JSON array string
			 * ADDED: 03-01-2026 - Required for API batch operations
			 */
			public static String toJsonManualList(List<GenTlVisualcntchecklistdtl> list) {
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

