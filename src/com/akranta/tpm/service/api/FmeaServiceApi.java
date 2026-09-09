package com.akranta.tpm.service.api;



import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.PlmTlEquipmentfmeadtl;
import com.akranta.tpm.model.PlmTlEquipmentfmeamst;
import com.akranta.tpm.model.PlmTlProcessfmeadtl;
import com.akranta.tpm.model.PlmTlProcessfmeamst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class FmeaServiceApi {
	private final Api api;

    public FmeaServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
        
      
        }
    // eqipment fmea
    public PlmTlEquipmentfmeamst insertRecord(PlmTlEquipmentfmeamst plmTlEquipmentfmeamst) throws Exception {
        String apiUrl = "/plmequipmentfmea/save"; // The Spring Boot API endpoint for insert
//        plmTlEquipmentfmeamst.setJhamActive("Y");
        
        String jsonPayload = insertJson(plmTlEquipmentfmeamst);
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        PlmTlEquipmentfmeamst audit = getJson(jsonResponse);
        CommonMessage.debugMsg(audit);
        
        return audit;
        
    }
    public String insertJson(PlmTlEquipmentfmeamst plmTlEquipmentfmeamst) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"plmtlequipmentfmeaMST\":");
        String jsonMst = plmTlEquipmentfmeamst.toJsonManual();
        str.append(jsonMst);
        str.append(",");
        
        // Detail Table Values
        List<PlmTlEquipmentfmeadtl> details = plmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl();
        str.append("\"plmtlequipmentfmeaDTL\":");
        String jsonDtl = PlmTlEquipmentfmeadtl.toJsonManualList(details);
        str.append(jsonDtl);
        
        str.append("}");
        
        return str.toString();
    }
    

	 public PlmTlEquipmentfmeamst getJson(String json) {
       JSONObject jsonObj = JSONObject.fromObject(json);
       JSONObject mstObj = jsonObj.getJSONObject("plmtlequipmentfmeaMST");
       JSONArray dtlArray = jsonObj.getJSONArray("plmtlequipmentfmeaDTL");
       
       PlmTlEquipmentfmeamst mst = PlmTlEquipmentfmeamst.fromJson(mstObj.toString());
       List<PlmTlEquipmentfmeadtl> dtl = PlmTlEquipmentfmeadtl.fromJsonList(dtlArray.toString());
       
       mst.setPlmTlEquipmentfmeadtl(dtl);
       
       return mst;
   }
	 
	 
	 //processfmea
	 
	 public PlmTlProcessfmeamst insertprocessRecords(PlmTlProcessfmeamst plmTlProcessfmeamst) throws Exception {
	        String apiUrl = "/plmtlprocessfmea/save"; // The Spring Boot API endpoint for insert
//	        plmTlEquipmentfmeamst.setJhamActive("Y");
	        
	        String jsonPayload = processinsertJson(plmTlProcessfmeamst);
	        CommonMessage.debugMsg("Json :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        // Get the response code to confirm insert success, usually 201 Created
	        CommonMessage.debugMsg(res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        PlmTlProcessfmeamst audit = processgetJson(jsonResponse);
	        CommonMessage.debugMsg(audit);
	        
	        return audit;
	        
	    }
	    public String processinsertJson(PlmTlProcessfmeamst plmTlProcessfmeamst) {
	        StringBuilder str = new StringBuilder();
	        
	        str.append("{");
	        
	        // Master Details
	        str.append("\"plmtlProcessfmeaMST\":");
	        String jsonMst = plmTlProcessfmeamst.toJsonManual();
	        str.append(jsonMst);
	        str.append(",");
	        
	        // Detail Table Values
	        List<PlmTlProcessfmeadtl> details = plmTlProcessfmeamst.getplmTlProcessfmeadtl();
	        str.append("\"plmtlProcessfmeaDTL\":");
	        String jsonDtl = PlmTlProcessfmeadtl.toJsonManualList(details);
	        str.append(jsonDtl);
	        
	        str.append("}");
	        
	        return str.toString();
	    }
	    

		 public PlmTlProcessfmeamst processgetJson(String json) {
	       JSONObject jsonObj = JSONObject.fromObject(json);
	       JSONObject mstObj = jsonObj.getJSONObject("plmtlProcessfmeaMST");
	       JSONArray dtlArray = jsonObj.getJSONArray("plmtlProcessfmeaDTL");
	       
	       PlmTlProcessfmeamst mst = PlmTlProcessfmeamst.fromJson(mstObj.toString());
	       List<PlmTlProcessfmeadtl> dtl = PlmTlProcessfmeadtl.fromJsonList(dtlArray.toString());
	       
	       mst.setPlmTlProcessfmeadtl(dtl);
	       
	       return mst;
	   }
		 
		 
//		 public List<String[]> select(String keyId, String type) throws Exception
//		 {
//		     // Build API URL with query parameters
//		     String apiUrl = "/recall"
//		             + "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8)
//		             + "&type=" + URLEncoder.encode(type, StandardCharsets.UTF_8);
//
//		     // Send GET request
//		     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//
//		     // Log response body (for debugging, same as old project)
//		     CommonMessage.debugMsg(res.getBody() + " Body");
//
//		     // Parse JSON response
//		     String json = res.getBody();
//		     JSONArray jsonArray = new JSONArray(json);
//
//		     // Convert JSON array to List<String[]> using existing utility
//		     List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
//
//		     return result;
//		 }
		 public List<String[]> select(String keyId, String type) throws Exception
		 {
		     String apiUrl = "/plmtlprocessfmea/recall/"
		             + URLEncoder.encode(keyId, StandardCharsets.UTF_8)
		             + "/"
		             + URLEncoder.encode(type, StandardCharsets.UTF_8);
		   
		     
		     // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

		        // Get the response code to confirm insert success, usually 201 Created
		       CommonMessage.debugMsg(res.getBody()+"Body");
		       String json = res.getBody();
		       
		       JSONArray jsonArray = new JSONArray(json);
		       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
		       return result;
		       
//		        int responseCode = res.getStatusCode();
		 }
		 
		 
			/*
			 * public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray
			 * jsonArray) { List<String[]> list = new ArrayList<>();
			 * 
			 * for (int i = 0; i < jsonArray.length(); i++) { Object element =
			 * jsonArray.get(i);
			 * 
			 * if (element instanceof String) { String rowStr = (String) element;
			 * 
			 * // Split CSV string into columns String[] row = rowStr.split(",", -1); // -1
			 * keeps empty values list.add(row); } }
			 * 
			 * // Debug for (String[] row : list) {
			 * CommonMessage.debugMsg(Arrays.toString(row)); }
			 * 
			 * return list; }
			 */

		 
		 public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray) {
			    List<String[]> list = new ArrayList<>();

			    if (jsonArray.length() < 0) {
			        throw new IllegalArgumentException("JSON array must have at least 3 rows (config, header, column order).");
			    }

			    // Row 3 = column order mapping
			    JSONObject orderObj = jsonArray.getJSONObject(0);

			    // Build ordered list of keys based on orderObj values
			    List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
			    Iterator<String> it = orderObj.keys();
			    while (it.hasNext()) {
			        String key = it.next();
			        try {
			            int order = Integer.parseInt(orderObj.getString(key));
			            orderList.add(new AbstractMap.SimpleEntry<>(key, order));
			        } catch (NumberFormatException e) {
			            orderList.add(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
			        }
			    }

			    // Sort keys by numeric order
			    orderList.sort(Comparator.comparingInt(Map.Entry::getValue));

			    // Process each row (from row 1 onwards if you want headers, or from row 3 for data only)
			    for (int i = 0; i < jsonArray.length(); i++) {
			    	if( i == 0) {
			    		continue;
			    	}
			        JSONObject obj = jsonArray.getJSONObject(i);
			        String[] row = new String[orderList.size()];

			        int colIndex = 0;
			        for (Map.Entry<String, Integer> entry : orderList) {
			            String key = entry.getKey();
			            Object value = obj.opt(key);
			            row[colIndex++] = value != null ? value.toString() : "";
			        }

			        list.add(row);
			    }

			    // Debug
			    for (String[] row : list) {
			        CommonMessage.debugMsg(Arrays.toString(row));
			    }

			    return list;
			}
		 public PlmTlProcessfmeamst deleteDtls(PlmTlProcessfmeamst newPlmTlProcessfmeamst)
				 throws IOException {
		        
		        String apiUrl = "/plmtlprocessfmea/deleteDtls";
		        CommonMessage.debugMsg("getElementId API URL: " + apiUrl);
		        
		        String keyid=newPlmTlProcessfmeamst.getFmpmKeyid();
		        
		     
		            List<PlmTlProcessfmeadtl> details = newPlmTlProcessfmeamst.getplmTlProcessfmeadtl();
		            
		            String jsonPayload = buildDeletePayload(keyid,details);
			        
			        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
			        CommonMessage.debugMsg("jsonPayload: " + jsonPayload);
			        
			        int statusCode = res.getStatusCode();
			        CommonMessage.debugMsg("Status Code: " + statusCode);
			        
			        String jsonResponse = res.getBody();
			        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
			        
			        // Parse JSON array response
			        
			        
			        CommonMessage.debugMsg("✅ Successfully retrieved element IDs");
			        
			       

		            
		        	
		        return newPlmTlProcessfmeamst;
		      
		    }
		 
			
		 
		 public String buildDeletePayload(String mstKeyId,List<PlmTlProcessfmeadtl> dtl) 
		 {
			 StringBuilder sb = new StringBuilder();
			 sb.append("[");
			 for(PlmTlProcessfmeadtl detail:dtl) 
			 {
				 sb.append("{");
				 sb.append("\"mstKeyid\":\"").append(mstKeyId).append("\"");
				 sb.append(",\"fmpd_keyid\":\"").append(detail.getFmpdKeyid()).append("\"");
				 sb.append(",\"fmpd_reviewby\":\"").append(detail.getFmpdReviewby()).append("\"");
				 sb.append("}");
				 
			 }
			 sb.append("]");
			 return sb.toString();
			 }
		 
		 
		 
		 
		 
		 public PlmTlEquipmentfmeamst EquipdeleteDtl(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst)
				 throws IOException {
		        
		        String apiUrl = "/plmequipmentfmea/deleteDtls";
		        CommonMessage.debugMsg("getElementId API URL: " + apiUrl);
		        
		        String keyid=newPlmTlEquipmentfmeamst.getFmeqKeyid();
		        
		     
		            List<PlmTlEquipmentfmeadtl> details = newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl();
		            
		            String jsonPayload = buildeqDeletePayload(keyid,details);
			        
			        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
			        CommonMessage.debugMsg("jsonPayload: " + jsonPayload);
			        
			        int statusCode = res.getStatusCode();
			        CommonMessage.debugMsg("Status Code: " + statusCode);
			        
			        String jsonResponse = res.getBody();
			        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
			        
			        // Parse JSON array response
			        
			        
			        CommonMessage.debugMsg("✅ Successfully retrieved element IDs");
			        
			       

		            
		        	
		        return newPlmTlEquipmentfmeamst;
		      
		    }
		 
			
		 
		 public String buildeqDeletePayload(String fmeq_keyid,List<PlmTlEquipmentfmeadtl> dtl) 
		 {
			 StringBuilder sb = new StringBuilder();
			 sb.append("[");
			 for(PlmTlEquipmentfmeadtl detail:dtl) 
			 {
				 sb.append("{");
				 sb.append("\"fmeq_keyid\":\"").append(fmeq_keyid).append("\"");
				 sb.append(",\"fmed_reviewby\":\"").append(detail.getFmedReviewby()).append("\"");
				 sb.append(",\"fmed_keyid\":\"").append(detail.getFmedKeyid()).append("\"");
				 sb.append("}");
				 
			 }
			 sb.append("]");
			 return sb.toString();
			 }
		 
		 
		 
		 

		 
		 
		 
}	 
		 


		    /**
		     * Build JSON payload manually with conditional fields
		     */
//		    public String buildqeryids(String mstKeyid, String fmpd_reviewby, String fmpd_keyid) {
//		        StringBuilder sb = new StringBuilder();
//		        
//		        sb.append("{");
//		        sb.append("\"mstKeyid\":");
//		        sb.append("\"").append(mstKeyid).append("\"");
//		        
//		        if (UIUtils.isValidKeyId(fmpd_reviewby)) {
//		            sb.append(",\"fmpd_reviewby\":");
//		            sb.append("\"").append(fmpd_reviewby).append("\"");
//		        }
//		        
//		        if (UIUtils.isValidKeyId(fmpd_keyid)) {
//		            sb.append(",\"fmpd_keyid\":");
//		            sb.append("\"").append(fmpd_keyid).append("\"");
//		        }
//		            
//		            sb.append("}");
//			        return sb.toString();
//		        }
//			


		 
		 
		 


//public String buildDeletePayload(String mstKeyid,
//        List<PlmTlProcessfmeadtl> dtlList) {
//
//StringBuilder sb = new StringBuilder();
//sb.append("[");
//
//boolean first = true;
//
//for (PlmTlProcessfmeadtl dtl : dtlList) {
//
//if (!first) sb.append(",");
//first = false;
//
//sb.append("{");
//sb.append("\"mstKeyid\":\"").append(mstKeyid).append("\"");
//
//if (UIUtils.isValidKeyId(dtl.getFmpdReviewby())) {
//sb.append(",\"fmpd_reviewby\":\"")
//.append(dtl.getFmpdReviewby()).append("\"");
//}
//
//if (UIUtils.isValidKeyId(dtl.getFmpdKeyid())) {
//sb.append(",\"fmpd_keyid\":\"")
//.append(dtl.getFmpdKeyid()).append("\"");
//}
//
//sb.append("}");
//}
//
//sb.append("]");
//return sb.toString();
//}
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
//		 public PlmTlProcessfmeamst deleteDtls(PlmTlProcessfmeamst plmTlProcessfmeamst) throws Exception {
//		        String apiUrl = "/plmtlprocessfmea/deleteDtls"; // The Spring Boot API endpoint for insert
////		        plmTlEquipmentfmeamst.setJhamActive("Y");
//		        
//		        String jsonPayload = processinsertJson(plmTlProcessfmeamst);
//		        CommonMessage.debugMsg("Json :: " + jsonPayload);
//		        
//		        // Send request
//		        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//		        
//		        // Get the response code to confirm insert success, usually 201 Created
//		        CommonMessage.debugMsg(res.getStatusCode());
//		        
//		        String jsonResponse = res.getBody();
//		        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//		        
//		        PlmTlProcessfmeamst audit = processgetJson(jsonResponse);
//		        CommonMessage.debugMsg(audit);
//		        
//		        return audit;
//		        
//		    }


		 
		 
		 
		 
//		 public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray) {
//             List<String[]> list = new ArrayList<>();
//
//             if (jsonArray.length() < 0) {
//                 throw new IllegalArgumentException("JSON array must have at least 3 rows (config, header, column order).");
//             }
//
//             // Row 3 = column order mapping
//             JSONObject orderObj = jsonArray.getJSONObject(0);
//
//             // Build ordered list of keys based on orderObj values
//             List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
//             Iterator<String> it = orderObj.keys();
//             while (it.hasNext()) {
//                 String key = it.next();
//                 try {
//                     int order = Integer.parseInt(orderObj.getString(key));
//                     orderList.add(new AbstractMap.SimpleEntry<>(key, order));
//                 } catch (NumberFormatException e) {
//                     orderList.add(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
//                 }
//             }
//
//             // Sort keys by numeric order
//             orderList.sort(Comparator.comparingInt(Map.Entry::getValue));
//
//             // Process each row (from row 1 onwards if you want headers, or from row 3 for data only)
//             for (int i = 0; i < jsonArray.length(); i++) {
//             	if( i == 0) {
//             		continue;
//             	}
//                 JSONObject obj = jsonArray.getJSONObject(i);
//                 String[] row = new String[orderList.size()];
//
//                 int colIndex = 0;
//                 for (Map.Entry<String, Integer> entry : orderList) {
//                     String key = entry.getKey();
//                     Object value = obj.opt(key);
//                     row[colIndex++] = value != null ? value.toString() : "";
//                 }
//
//                 list.add(row);
//             }
//
//             // Debug
//             for (String[] row : list) {
//                 CommonMessage.debugMsg(Arrays.toString(row));
//             }
//
//             return list;
//         }







//Build JSON payload using helper method

//String jsonPayload = buildqeryids(keyid, reviewBy, dtlKeyId);
//
//HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//CommonMessage.debugMsg("jsonPayload: " + jsonPayload);
//
//int statusCode = res.getStatusCode();
//CommonMessage.debugMsg("Status Code: " + statusCode);
//
//String jsonResponse = res.getBody();
//CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//
//// Parse JSON array response
//JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
//
//// Convert using column order method
//List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
//
//CommonMessage.debugMsg("✅ Successfully retrieved element IDs");
//return result;







/*
 * public String buildDeletePayload(String mstKeyid, String reviewBy, String
 * dtlKeyId) {
 * 
 * StringBuilder sb = new StringBuilder(); sb.append("[{");
 * sb.append("\"mstKeyid\":\"").append(mstKeyid).append("\"");
 * 
 * if (UIUtils.isValidKeyId(reviewBy)) {
 * sb.append(",\"fmpd_reviewby\":\"").append(reviewBy).append("\""); }
 * 
 * if (UIUtils.isValidKeyId(dtlKeyId)) {
 * sb.append(",\"fmpd_keyid\":\"").append(dtlKeyId).append("\""); }
 * 
 * sb.append("}]"); return sb.toString(); }
 */

