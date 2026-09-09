package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.JhaTlAudittemplate.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlTemplatelevellink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		templateid, auditlevelid, minimumpoints
		,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,  active, createdby, createdon
		, modifiedon
	}

	public JhaTlTemplatelevellink()
	{
		saveArray = new  Object [ 12 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}
	public String getJtllTemplateid() {
		return (String) saveArray[ tableFldConstants.templateid.ordinal() ];
	}

	public void setJtllTemplateid(String jtllTemplateid) {
		saveArray[ tableFldConstants.templateid.ordinal() ] = jtllTemplateid;
	}

	public String getJtllAuditlevelid() {
		return (String) saveArray[ tableFldConstants.auditlevelid.ordinal() ];
	}

	public void setJtllAuditlevelid(String jtllAuditlevelid) {
		saveArray[ tableFldConstants.auditlevelid.ordinal() ] = jtllAuditlevelid;
	}
	

	public String getJtllMinimumpoints() {
		return (String) saveArray[ tableFldConstants.minimumpoints.ordinal() ];
	}

	public void setJtllMinimumpoints(String jtllMinimumpoints) {
		saveArray[ tableFldConstants.minimumpoints.ordinal() ] = jtllMinimumpoints;
	}
	
	public String getJtllTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setJtllTempfield1(String jtllTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = jtllTempfield1;
	}
	
	public String getJtllTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setJtllTempfield2(String jtllTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = jtllTempfield2;
	}
	
	public String getJtllTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setJtllTempfield3(String jtllTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = jtllTempfield3;
	}
	
	public String getJtllTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setJtllTempfield4(String jtllTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = jtllTempfield4;
	}
	
	public String getJtllTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setJtllTempfield5(String jtllTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = jtllTempfield5;
	}

	public String getJtllActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJtllActive(String jtllActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jtllActive;
	}

	public String getJtllCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJtllCreatedby(String jtllCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jtllCreatedby;
	}

	public String getJtllCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJtllCreatedon(String jtllCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jtllCreatedon;
	}

	public String getJtllModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJtllModifiedon(String jtllModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jtllModifiedon;
	}
	
	
	// ========================================================================
		// JSON CONVERSION METHODS - Added: 10-01-2026
		// ========================================================================

		/**
		 * Get value by field enum
		 */
		public Object getValue(tableFldConstants field) {
			return saveArray[field.ordinal()];
		}

		/**
		 * Set value by field enum
		 */
		public void setValue(tableFldConstants field, Object value) {
			saveArray[field.ordinal()] = value;
		}

		/**
		 * Convert object to JSON string manually
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
					
					if (field.name().equals("templateid") && val == null) {
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

		/**
		 * Parse JSON string to object
		 */
		public static JhaTlTemplatelevellink fromJson(String json) {
			CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

			JSONObject obj = JSONObject.fromObject(json);
			JhaTlTemplatelevellink link = new JhaTlTemplatelevellink();
			
			CommonMessage.debugMsg("Parsing Template Level Link JSON: " + json);

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();
				CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
				
				String val = obj.optString(field.name(), null);
				link.setValue(field, val != null && val.equals("null") ? null : val);
			}

			return link;
		}

		/**
		 * Parse JSON array to List of objects
		 * ADDED: 10-01-2026 - Required for API operations
		 */
		public static List<JhaTlTemplatelevellink> fromJsonList(String json) {
			CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

			JSONArray jsonArray = JSONArray.fromObject(json);
			List<JhaTlTemplatelevellink> list = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				JhaTlTemplatelevellink link = new JhaTlTemplatelevellink();

				for (tableFldConstants field : tableFldConstants.values()) {
					String key = field.name();
					Object valueObj = obj.opt(key);
					String val = (valueObj == null || "null".equals(valueObj.toString())) 
						? null : valueObj.toString();
					link.setValue(field, val);
				}

				list.add(link);
			}

			return list;
		}

		/**
		 * Convert List of objects to JSON array string
		 * ADDED: 10-01-2026 - Required for API batch operations
		 */
		public static String toJsonManualList(List<JhaTlTemplatelevellink> list) {
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



