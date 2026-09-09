package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMomattendance.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlMomKpiLink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, moms_keyid, momd_keyid, kink_keyid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public GenTlMomKpiLink()
	{
		saveArray = new  Object [ 13 ];
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


	public String getMokpKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMokpKeyid(String mokpKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mokpKeyid;
	}

	public String getMokpMomsKeyid() {
		return (String) saveArray[ tableFldConstants.moms_keyid.ordinal() ];
	}

	public void setMokpMomsKeyid(String mokpMomsKeyid) {
		saveArray[ tableFldConstants.moms_keyid.ordinal() ] = mokpMomsKeyid;
	}

	public String getMokpMomdKeyid() {
		return (String) saveArray[ tableFldConstants.momd_keyid.ordinal() ];
	}

	public void setMokpMomdKeyid(String mokpMomdKeyid) {
		saveArray[ tableFldConstants.momd_keyid.ordinal() ] = mokpMomdKeyid;
	}

	public String getMokpKinkKeyid() {
		return (String) saveArray[ tableFldConstants.kink_keyid.ordinal() ];
	}

	public void setMokpKinkKeyid(String mokpKinkKeyid) {
		saveArray[ tableFldConstants.kink_keyid.ordinal() ] = mokpKinkKeyid;
	}

	public String getMokpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMokpTempfield1(String mokpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mokpTempfield1;
	}

	public String getMokpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMokpTempfield2(String mokpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mokpTempfield2;
	}

	public String getMokpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMokpTempfield3(String mokpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mokpTempfield3;
	}

	public String getMokpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMokpTempfield4(String mokpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mokpTempfield4;
	}

	public String getMokpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMokpTempfield5(String mokpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mokpTempfield5;
	}

	public String getMokpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMokpActive(String mokpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mokpActive;
	}

	public String getMokpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMokpCreatedby(String mokpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mokpCreatedby;
	}

	public String getMokpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMokpCreatedon(String mokpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mokpCreatedon;
	}

	public String getMokpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMokpModifiedon(String mokpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mokpModifiedon;
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
	  
	  public static GenTlMomKpiLink fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  GenTlMomKpiLink att = new GenTlMomKpiLink();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        att.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return att;
		}
	  
	  public static List<GenTlMomKpiLink> fromJsonList(String json) {

			CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

			JSONArray jsonArray = JSONArray.fromObject(json);
			List<GenTlMomKpiLink> list = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject obj = jsonArray.getJSONObject(i);
				GenTlMomKpiLink att = new GenTlMomKpiLink();

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
		
		public static String toJsonManualList(List<GenTlMomKpiLink> list) {
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

