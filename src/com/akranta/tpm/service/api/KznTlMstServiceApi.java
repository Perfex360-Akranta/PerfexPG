package com.akranta.tpm.service.api;

	import java.io.IOException;
	//import com.fasterxml.jackson.databind.ObjectMapper;

	import java.net.URLEncoder;
	import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Comparator;
	import java.util.Iterator;
	import java.util.List;
import java.util.Locale;
import java.util.Map;
	import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.HttpResponse;
	import com.akranta.tpm.model.KznTlGraphdata;
	import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.QtmTlCustcomplaintdtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
	import net.sf.json.JSONObject;


	 public class KznTlMstServiceApi {

		
		private final Api api;
		
		public KznTlMstServiceApi(String JwtToken) {
	        this.api = new Api(JwtToken);
	    }

		public KznTlMst saveKaizen(KznTlMst kznTlMst, KznTlGraphdata kznTlGraphdata, KaizenFormBean kznFormBean) 
				throws Exception {
	        
			String apiUrl = "/kaizen/save"; 
			String jsonPayload;
	        
			 jsonPayload = insertJson(kznTlMst,kznTlGraphdata,kznFormBean);
	        
			
	        
	        CommonMessage.debugMsg("Json internal Rejection:: "+jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
	        
	        
	        // Get the response code to confirm insert success, usually 201 Created
	        CommonMessage.debugMsg(res.getStatusCode());     
	        

	        String jsonResponse = res.getBody();	        
	        
	        
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	   
	        KznTlMst dtl = KznTlMst.fromJson(jsonResponse);
	        CommonMessage.debugMsg(dtl);
	        return dtl;
	        

	}
		
		public String insertJson(KznTlMst kznTlMst, KznTlGraphdata kznTlGraphdata, KaizenFormBean kznFormBean) 
		{
			StringBuilder str = new StringBuilder();			
			//Master Details
			str.append("{");
			
			str.append("\"kznTlMst\":");  
			String JsonMst = kznTlMst.toJsonManual();
			str.append(JsonMst);
			str.append(",");
			
			//Detail Table Values
			
			str.append("\"kznTlGraphdata\":");
			String JsonGraphData = kznTlGraphdata.toJsonManual();
			str.append(JsonGraphData);
			str.append(",");			
			
			str.append("\"kznTlMstDto\":");
			str.append("{");
			str.append("\"kznmKeyid\":"); 
			str.append("\"").append(kznTlMst.getKznmKeyid()).append("\"");
			str.append(",\"kzgdKaizenid\":");
			str.append("\"").append(kznFormBean.getKaizenId()).append("\"");
			str.append("}");
			
			str.append("}");
			
			return str.toString();
	        
			
		}
		
		public List<String[]> getKaizenMstGrid(String flid) throws IOException
		{
			
			 String apiUrl = "/kaizen/grid"
			            + "?flid=" + URLEncoder.encode(flid, StandardCharsets.UTF_8);
			          
			// Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

	        // Get the response code to confirm insert success, usually 201 Created
	       CommonMessage.debugMsg(res.getBody()+"Body");
	       String json = res.getBody();
	       
	       
	       
	       JSONArray jsonArray = new JSONArray(json);
	       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
	       return result;
	       
		}
		
		public KznTlMst getKaizenMasterData(String id) throws IOException
		{
			
			 String apiUrl = "/kaizen/getById"
			            + "?id=" + URLEncoder.encode(id, StandardCharsets.UTF_8);
			          
			// Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

	        // Get the response code to confirm insert success, usually 201 Created
	       CommonMessage.debugMsg(res.getBody()+"Body");
	       String json = res.getBody();
	       
	       
	       
	       KznTlMst result = KznTlMst.fromJson(json);
	       return result;
//	        int responseCode = res.getStatusCode();
		}
		
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

		/* 
		 public KznTlMst insertRecord(KznTlMst newKznTlMst) throws IOException {

			    String apiUrl = "/kaizen";

			    // Set default values
			    newKznTlMst.setKznmActive("Y");

			    // Convert object to JSON using Jackson
			    //ObjectMapper mapper = new ObjectMapper();
			    String jsonPayload = mapper.writeValueAsString(newKznTlMst);

			    CommonMessage.debugMsg("Request JSON :: " + jsonPayload);

			    // Call API
			    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

			    int statusCode = res.getStatusCode();
			    String jsonResponse = res.getBody();

			    CommonMessage.debugMsg("Response Code :: " + statusCode);
			    CommonMessage.debugMsg("Response JSON :: " + jsonResponse);

			    // Validate response
			    if (statusCode != 200 && statusCode != 201) {
			        throw new RuntimeException("Failed to insert Kaizen. HTTP Code: " + statusCode);
			    }

			    // Convert response JSON back to object
			    return mapper.readValue(jsonResponse, KznTlMst.class);
			}
		 */
		
		 public KznTlMst insertRecord(KznTlMst newKznTlMst, BdmTlYycountermeasurelink bdmTlYycountermeasurelink,
				GenTlDocupdates genTlDocupdates, QtmTlCustcomplaintdtl qtmTlCustcomplaintdtl) throws IOException {
			 String apiUrl = "/kaizen"; // The Spring Boot API endpoint for insert
			 newKznTlMst.setKznmActive("Y");
			 
			 CommonMessage.debugMsg("In side API "+newKznTlMst.getKznmDate() );
				/*
				 * newKznTlMst.setKznmDate(CommonFunctions.pg_getDateFromPGTimeStamp(newKznTlMst
				 * .getKznmDate()));
				 * newKznTlMst.setKznmStartdate(CommonFunctions.pg_getDateFromPGTimeStamp(
				 * newKznTlMst.getKznmStartdate()));
				 * newKznTlMst.setKznmEnddate(CommonFunctions.pg_getDateFromPGTimeStamp(
				 * newKznTlMst.getKznmEnddate()));
				 */
		        // Convert object to JSON
		        String jsonPayload = newKznTlMst.toJsonManual();
		        CommonMessage.debugMsg("Json :: "+jsonPayload);
		        
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

		        // Get the response code to confirm insert success, usually 201 Created
		        CommonMessage.debugMsg(res.getStatusCode());
//		        int responseCode = res.getStatusCode();
		        
		        

		        String jsonResponse = res.getBody();
		        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
		        
		        KznTlMst kaizen = KznTlMst.fromJson(jsonResponse);
		        kaizen.setAllmoduleimgfile(newKznTlMst.getAllmoduleimgfile());
		        return kaizen;
		 }
		
		
		 
		 public  KznTlMst getById(String keyId) throws IOException
			{
				
				 String apiUrl = "/kaizen/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
				           	
				
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

		        // Get the response code to confirm insert success, usually 201 Created
		       CommonMessage.debugMsg(res.getBody()+"Body");
		       String json = res.getBody();
		       
		       KznTlMst mst = KznTlMst.fromJson(json);
		       
		      
		       return mst;
		       
//		        int responseCode = res.getStatusCode();
			}
		 
		 public  String findKeyid(String keyId) throws IOException
			{
				CommonMessage.debugMsg(keyId+" keyIdkeyIdkeyIdkeyIdkeyId");
				 String apiUrl = "/kaizen/find/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
				           	
				
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

		        // Get the response code to confirm insert success, usually 201 Created
		       CommonMessage.debugMsg(res.getBody()+"Body");
		       String json = res.getBody();
		       
		      // KznTlMst mst = KznTlMst.fromJson(json);
		      
		       return json;
		       
//		        int responseCode = res.getStatusCode();
			}

		 public KznTlMst saveKaizen(KznTlMst oldKznTlMst, BdmTlYycountermeasurelink bdmTlYycountermeasurelink,
				KaizenFormBean kaizenFormBean) throws IOException {
			 String apiUrl = "/kaizen/update"; 
				String jsonPayload;
				CommonMessage.debugMsg(oldKznTlMst.getKznmPresentproblem()+" KznmDate in side the Api");
				/*
				 * CommonMessage.debugMsg(oldKznTlMst.getKznmDate()+" KznmDate in side the Api");
				 * String kznmDate =
				 * CommonFunctions.pg_getDateTimeFromDate(oldKznTlMst.getKznmDate()); String
				 * startdate =
				 * CommonFunctions.pg_getDateTimeFromDate(oldKznTlMst.getKznmStartdate());
				 * String endDate =
				 * CommonFunctions.pg_getDateTimeFromDate(oldKznTlMst.getKznmEnddate());
				 * 
				 * oldKznTlMst.setKznmDate(kznmDate); oldKznTlMst.setKznmStartdate(startdate);
				 * oldKznTlMst.setKznmEnddate(endDate);
				 */
				/*
				 * oldKznTlMst.setKznmDate(normalizeUiDate(oldKznTlMst.getKznmDate()));
				 * oldKznTlMst.setKznmStartdate(normalizeUiDate(oldKznTlMst.getKznmStartdate()))
				 * ; oldKznTlMst.setKznmEnddate(normalizeUiDate(oldKznTlMst.getKznmEnddate()));
				 */
				/*
				 * oldKznTlMst.setKznmApproveddate(normalizeUiDate(oldKznTlMst.
				 * getKznmApproveddate()));
				 * oldKznTlMst.setKznmCompleteddate(normalizeUiDate(oldKznTlMst.
				 * getKznmCompleteddate()));
				 * oldKznTlMst.setKznmPrepareddate(normalizeUiDate(oldKznTlMst.
				 * getKznmPrepareddate()));
				 */
				
				/*
				 * oldKznTlMst.setKznmPrepareddate(normalizeda(oldKznTlMst.getKznmPrepareddate()
				 * ));
				 * oldKznTlMst.setKznmApproveddate(CommonFunctions.pg_getDateFromPGTimeStamp(
				 * oldKznTlMst.getKznmApproveddate())); oldKznTlMst.setKznmCompleteddate
				 * (CommonFunctions.pg_getDateFromPGTimeStamp(oldKznTlMst.getKznmCompleteddate()
				 * ));
				 */
				 jsonPayload = insertJson(oldKznTlMst,bdmTlYycountermeasurelink,kaizenFormBean);
		        
				
		        
		        CommonMessage.debugMsg("Json internal Rejection:: "+jsonPayload);
		        
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"PUT", jsonPayload);
		        
		        
		        // Get the response code to confirm insert success, usually 201 Created
		        CommonMessage.debugMsg(res.getStatusCode());     
		        

		        String jsonResponse = res.getBody();	        
		        
		        
		        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
		   
		        KznTlMst dtl = KznTlMst.fromJson(jsonResponse);
		        CommonMessage.debugMsg(dtl);
		        dtl.setAllmoduleimgfile(oldKznTlMst.getAllmoduleimgfile());
		        return dtl;
		 }

		 private String insertJson(KznTlMst oldKznTlMst, BdmTlYycountermeasurelink bdmTlYycountermeasurelink,
				KaizenFormBean kaizenFormBean) {
			
					StringBuilder str = new StringBuilder();			
					//Master Details
				//	str.append("{");
					
					//str.append("\"kznTlMst\":");  
					String JsonMst = oldKznTlMst.toJsonManual();
					str.append(JsonMst);
					
					
					//Detail Table Values
					if((bdmTlYycountermeasurelink)!=null) {
						str.append(",");
							str.append("\"bdmTlYycountermeasurelink\":");
					String JsonGraphData = bdmTlYycountermeasurelink.toJsonManual();
					str.append(JsonGraphData);
					//str.append(",");			
					}
					/*str.append("\"kznTlMstDto\":");
					str.append("{");
					str.append("\"kznmKeyid\":"); 
					str.append("\"").append(oldKznTlMst.getKznmKeyid()).append("\"");
					str.append(",\"kzgdKaizenid\":");
					str.append("\"").append(kaizenFormBean.getKaizenId()).append("\"");
					str.append("}");*/
					
					//str.append("}");
				//	str.append("}");
					
					return str.toString();
			        
					
				}
		 
		 public static String normalizeUiDate(String uiDate) {

				DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

				DateTimeFormatter apiFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

				LocalDate date = LocalDate.parse(uiDate, uiFmt);

				return date.atStartOfDay().format(apiFmt);
			}
		 
		 
		 public List<GenTlWorkflowInfo> createMultipleApproval(List<GenTlWorkflowInfo> genTlWorkflowInfo) throws IOException {
			 String apiUrl = "/kaizen/simplAppSave"; 
		        
		        // Convert object to JSON
		        String jsonPayload = GenTlWorkflowInfo.toJsonManualList(genTlWorkflowInfo);
		        CommonMessage.debugMsg("Json :: "+jsonPayload);
		        
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

		        // Get the response code to confirm insert success, usually 201 Created
		        CommonMessage.debugMsg(res.getStatusCode());
//		        int responseCode = res.getStatusCode();
		        
		        

		        String jsonResponse = res.getBody();
		        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
		        
		        List<GenTlWorkflowInfo> wkrflws = GenTlWorkflowInfo.fromJsonList(jsonResponse);
		        
		        return wkrflws;
		        
		 }
		 public  List<KznTlMst> updateTheme(List<KznTlMst> KaizenThemeList)
		 {
			 String apiUrl = "/kaizen/updateThemeCatogery"; 
			 String jsonPayload = compThemeJson(KaizenThemeList);
			 
			  CommonMessage.debugMsg("Json internal Rejection:: "+jsonPayload);
		        
		        // Send request
		        HttpResponse res;
				try {
					res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
					CommonMessage.debugMsg(res.getStatusCode());     
			        

			        String jsonResponse = res.getBody();
			        
			        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        
		        // Get the response code to confirm insert success, usually 201 Created
		                
		        

		   
				
				
				return KaizenThemeList;
			 
		 }

		 private String compThemeJson(List<KznTlMst> kaizenThemeList) 
		 {
			 StringBuilder sb = new StringBuilder();
			 sb.append("[");
			  for (int i = 0; i < kaizenThemeList.size(); i++) 
			  {
			        KznTlMst mst = kaizenThemeList.get(i);
			 sb.append("{");
			 
			 String kznmKeyId = mst.getKznmKeyid();
			 sb.append("\"kznmKeyId\":");
			 if(kznmKeyId == null || kznmKeyId.equals("null") || kznmKeyId.equals(""))
			        sb.append("null");
			 else
			        sb.append("\"").append(kznmKeyId).append("\"");

			 String kznmResultArea = mst.getKznmResultarea();
			 sb.append(",\"kznmResultArea\":");
			 if(kznmResultArea == null || kznmResultArea.equals(""))
			     sb.append("null");
			 else
			    sb.append("\"").append(kznmResultArea).append("\"");

			 String kznmThemeCatogeryId = mst.getKznmThemecategoryid();
			 sb.append(",\"kznmThemeCatogeryId\":");
			 if(kznmThemeCatogeryId == null || kznmThemeCatogeryId.equals(""))
			     sb.append("null");
			 else
			    sb.append("\"").append(kznmThemeCatogeryId).append("\"");


			    sb.append("}");
			    if (i < kaizenThemeList.size() - 1) {
		            sb.append(",");
		        }
			 }
			  sb.append("]");

			    CommonMessage.debugMsg("FINAL JSON = "+sb.toString());
			    return sb.toString();
		
		 }
			

		 }
		

		
		
		
		
	


