package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMommst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class EntTlSkillindexassessmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, uniqueposid, tempfiled1, reviewdate, tempfiled2
		, tempfiled3, tempfiled4, tempfiled5, active, createdby, createdon
		, modifiedon
	}

	public EntTlSkillindexassessmst()
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

	public String getSiamKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSiamKeyid(String siamKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = siamKeyid;
	}

	public String getSiamFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSiamFlid(String siamFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = siamFlid;
	}

	public String getSiamUniqueposid() {
		return (String) saveArray[ tableFldConstants.uniqueposid.ordinal() ];
	}

	public void setSiamUniqueposid(String siamUniqueposid) {
		saveArray[ tableFldConstants.uniqueposid.ordinal() ] = siamUniqueposid;
	}

	public String getSiamtempfiled1() {
		return (String) saveArray[ tableFldConstants.tempfiled1.ordinal() ];
	}

	public void setSiamTempfiled1(String siamTempfiled1) {
		saveArray[ tableFldConstants.tempfiled1.ordinal() ] = siamTempfiled1;
	}

	public String getSiamReviewdate() {
		return (String) saveArray[ tableFldConstants.reviewdate.ordinal() ];
	}

	public void setSiamReviewdate(String siamReviewdate) {
		saveArray[ tableFldConstants.reviewdate.ordinal() ] = siamReviewdate;
	}

	public String getSiamTempfiled2() {
		return (String) saveArray[ tableFldConstants.tempfiled2.ordinal() ];
	}

	public void setSiamTempfiled2(String siamTempfiled2) {
		saveArray[ tableFldConstants.tempfiled2.ordinal() ] = siamTempfiled2;
	}

	public String getSiamTempfiled3() {
		return (String) saveArray[ tableFldConstants.tempfiled3.ordinal() ];
	}

	public void setSiamTempfiled3(String siamTempfiled3) {
		saveArray[ tableFldConstants.tempfiled3.ordinal() ] = siamTempfiled3;
	}

	public String getSiamTempfiled4() {
		return (String) saveArray[ tableFldConstants.tempfiled4.ordinal() ];
	}

	public void setSiamTempfiled4(String siamTempfiled4) {
		saveArray[ tableFldConstants.tempfiled4.ordinal() ] = siamTempfiled4;
	}

	public String getSiamTempfiled5() {
		return (String) saveArray[ tableFldConstants.tempfiled5.ordinal() ];
	}

	public void setSiamTempfiled5(String siamTempfiled5) {
		saveArray[ tableFldConstants.tempfiled5.ordinal() ] = siamTempfiled5;
	}

	public String getSiamActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSiamActive(String siamActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = siamActive;
	}

	public String getSiamCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSiamCreatedby(String siamCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = siamCreatedby;
	}

	public String getSiamCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSiamCreatedon(String siamCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = siamCreatedon;
	}

	public String getSiamModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSiamModifiedon(String siamModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = siamModifiedon;
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
	  
	  public static EntTlSkillindexassessmst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  EntTlSkillindexassessmst mst = new EntTlSkillindexassessmst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        mst.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return mst;
		}
	  
	  
		public static List<EntTlSkillindexassessmst> fromJsonList(String json) {

			CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

			JSONArray jsonArray = JSONArray.fromObject(json);
			List<EntTlSkillindexassessmst> list = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject obj = jsonArray.getJSONObject(i);
				EntTlSkillindexassessmst mst = new EntTlSkillindexassessmst();

				for (tableFldConstants field : tableFldConstants.values()) {
					String key = field.name();

					Object valueObj = obj.opt(key);
					String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

					mst.setValue(field, val);
				}

				list.add(mst);
			}

			return list;
		}
		
		public static String toJsonManualList(List<EntTlSkillindexassessmst> list) {
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

