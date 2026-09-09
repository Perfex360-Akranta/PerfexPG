package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class FieldAuditSheetdtl{

		private  Object [] saveArray = null;  
		private List <FieldAuditSheetdtl> methodFieldAuditSheetdtl;
		

		public enum   tableFldConstants
		{
			keyid,masterid,espid,ppeid,ppecondition,
			tools,workpermitsafety,knowledge,remarks,espothers,tempfield2
			,tempfield3,tempfield4,tempfield5
			,active, createdby, createdon, modifiedon
		}

		public FieldAuditSheetdtl()
		{
			saveArray = new  Object [ 18 ];
			methodFieldAuditSheetdtl = new ArrayList<FieldAuditSheetdtl>();
		}
		
		public List<FieldAuditSheetdtl> getmethodEhsTlAuditdtl() 
		{
			
			return methodFieldAuditSheetdtl;
			
		}
		public void setmethodEhsTlAuditdtl(List <FieldAuditSheetdtl> methodEhsTlAuditdtl) {
			this.methodFieldAuditSheetdtl=methodEhsTlAuditdtl;
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
		public String getFasdKeyid() {
			return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
		}

		public void setFasdKeyid(String fasdKeyid) {
			saveArray[ tableFldConstants.keyid.ordinal() ] = fasdKeyid;
		}

		public String getFasdmasterid() {
			return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
		}

		public void setFasdmasterid(String fasdmasterid) {
			saveArray[ tableFldConstants.masterid.ordinal() ] =fasdmasterid;
		}

		public String getFasdEspid() {
			return (String) saveArray[ tableFldConstants.espid.ordinal() ];
		}

		public void setFasdEspid(String fasdEspid){
			saveArray[ tableFldConstants.espid.ordinal() ] =fasdEspid;
		}

		public String getFasdPpeid() {
			return (String) saveArray[ tableFldConstants.ppeid.ordinal() ];
		}

		public void setFasdPpeid(String fasdPpeid) {
			saveArray[ tableFldConstants.ppeid.ordinal() ] =fasdPpeid;
		}
		public String getFasdPpecondition() {
			return (String) saveArray[ tableFldConstants.ppecondition.ordinal() ];
		}

		public void setFasdPpecondition(String fasdPpecondition) {
			saveArray[ tableFldConstants.ppecondition.ordinal() ] =fasdPpecondition;
		}
     
         public String getFasdTools() {
			return (String) saveArray[ tableFldConstants.tools.ordinal() ];
		}

		public void setFasdTools(String fasdTools) {
			saveArray[ tableFldConstants.tools.ordinal() ] =fasdTools;
		}
   
               public String getFasdWorkpermitsafety() {
			return (String) saveArray[ tableFldConstants.workpermitsafety.ordinal() ];
		}
             

		public void setFasdWorkpermitsafety(String fasdWorkpermitsafety) {
			saveArray[ tableFldConstants.workpermitsafety.ordinal() ] =fasdWorkpermitsafety;
		}

                 public String getFasdKnowledge() {
	 		return (String) saveArray[ tableFldConstants.knowledge.ordinal() ];
		}

		public void setFasdKnowledge(String fasdKnowledge) {
			saveArray[ tableFldConstants.knowledge.ordinal() ] =fasdKnowledge;
		}
		public String getFasdRemarks() {
			return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
		}

		public void setFasdRemarks(String fasdRemarks) {
			saveArray[ tableFldConstants.remarks.ordinal() ] = fasdRemarks;
		}
		
		
		public String getFasdOtherEspName() {
			return (String) saveArray[ tableFldConstants.espothers.ordinal() ];
		}

		public void setFasdOtherEspName(String fasdOtherEspName) {
			saveArray[ tableFldConstants.espothers.ordinal() ] = fasdOtherEspName;
		}
		
		public String getFasdTempfield2() {
			return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
		}

		public void setFasdTempfield2(String ehsTempfield2) {
			saveArray[ tableFldConstants.tempfield2.ordinal() ] = ehsTempfield2;
		}
		
		public String getFasdTempfield3() {
			return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
		}

		public void setFasdTempfield3(String ehsTempfield3) {
			saveArray[ tableFldConstants.tempfield3.ordinal() ] = ehsTempfield3;
		}
		
		public String getFasdTempfield4() {
			return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
		}

		public void setFasdTempfield4(String ehsTempfield4) {
			saveArray[ tableFldConstants.tempfield4.ordinal() ] = ehsTempfield4;
		}
		
		public String getFasdTempfield5() {
			return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
		}

		public void setFasdTempfield5(String ehsTempfield5) {
			saveArray[ tableFldConstants.tempfield5.ordinal() ] = ehsTempfield5;
		}

		
		public String getFasdActive() {
			return (String) saveArray[ tableFldConstants.active.ordinal() ];
		}

		public void setFasdActive(String fasdActive) {
			saveArray[ tableFldConstants.active.ordinal() ] =fasdActive;
		}

		public String getFasdCreatedby() {
			return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
		}

		public void setFasdCreatedby(String fasdCreatedby) {
			saveArray[ tableFldConstants.createdby.ordinal() ] =fasdCreatedby;
		}

		public String getFasdCreatedon() {
			return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
		}

		public void setFasdCreatedon(String fasdCreatedon) {
			saveArray[ tableFldConstants.createdon.ordinal() ] =fasdCreatedon;
		}

		public String getFasdModifiedon() {
			return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
		}

		public void setFasdModifiedon(String fasdModifiedon) {
			saveArray[ tableFldConstants.modifiedon.ordinal() ] = fasdModifiedon;
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

		public static FieldAuditSheetdtl fromJson(String json) {
			CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

			JSONObject obj = JSONObject.fromObject(json);

			FieldAuditSheetdtl dtl = new FieldAuditSheetdtl();
			CommonMessage.debugMsg("RAW JSON Response: :" + json);

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
				String val = obj.optString(field.name(), null);
				dtl.setValue(field, val != null && val.equals("null") ? null : val);

			}
			return dtl;
		}

		public static List<FieldAuditSheetdtl> fromJsonList(String json) {

			CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

			JSONArray jsonArray = JSONArray.fromObject(json);
			List<FieldAuditSheetdtl> list = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject obj = jsonArray.getJSONObject(i);
				FieldAuditSheetdtl dtl = new FieldAuditSheetdtl();

				for (tableFldConstants field : tableFldConstants.values()) {
					String key = field.name();

					Object valueObj = obj.opt(key);
					String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
					  if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
			                val = "";
			            }

					dtl.setValue(field, val);
				}

				list.add(dtl);
			}

			return list;
		}
		
		/*
		 * public static String toJsonManualList(List<FieldAuditSheetdtl> list) {
		 * StringBuilder sb = new StringBuilder(); sb.append("[");
		 * 
		 * for (int i = 0; i < list.size(); i++) { if (i > 0) sb.append(",");
		 * sb.append(list.get(i).toJsonManual()); }
		 * 
		 * sb.append("]"); return sb.toString(); }
		 */
		public static String toJsonManualList(List<FieldAuditSheetdtl> list) {
		    StringBuilder sb = new StringBuilder();
		    sb.append("[");
		    
		    // Add null check
		    if (list != null) {
		        for (int i = 0; i < list.size(); i++) {
		            if (i > 0)
		                sb.append(",");
		            sb.append(list.get(i).toJsonManual());
		        }
		    }
		    
		    sb.append("]");
		    return sb.toString();
		}

	}