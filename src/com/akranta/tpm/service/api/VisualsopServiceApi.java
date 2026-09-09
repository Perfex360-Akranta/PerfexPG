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

import com.akranta.tpm.bean.UpstreamDefect;
import com.akranta.tpm.model.GenTlUpstreamdefectDet;
import com.akranta.tpm.model.GenTlUpstreamdefectMst;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlVisualsopdtl;
import com.akranta.tpm.model.JhaTlVisualsopmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VisualsopServiceApi {
	
	 private final Api api;

	    public VisualsopServiceApi(String JwtToken) {
	        this.api = new Api(JwtToken);
	    }
	    
	    //inserting values in mst table
	    
	    public JhaTlVisualsopmst insertRecord(JhaTlVisualsopmst jhaTlVisualsopmst)  throws Exception {
	    	
	    	CommonMessage.debugMsg(jhaTlVisualsopmst.getElementid());
	    	
	    	String apiUrl = "/visualsop/createorupdatemst"; // The Spring Boot API endpoint for insert
	    	//jhaTlVisualsopmst.setUpsmActive("Y");
	        
	        //String jsonPayload = insertJson(jhaTlVisualsopmst);
	       
	        String jsonPayload = jhaTlVisualsopmst.toJsonManual();
	        CommonMessage.debugMsg("Json :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        // Get the response code to confirm insert success, usually 201 Created
	        CommonMessage.debugMsg(res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        JhaTlVisualsopmst audit = JhaTlVisualsopmst.fromJson(jsonResponse);
	        CommonMessage.debugMsg(audit);
	        
	        return audit;
		}
	    
	    //inserting values for detail table
	    
            public JhaTlVisualsopdtl insertdetailRecord(JhaTlVisualsopdtl jhaTlVisualsopdtl)  throws Exception {
	    	
	    	CommonMessage.debugMsg(jhaTlVisualsopdtl.getVsodActive());
	    	
	    	String apiUrl = "/visualsop/createorupdatedl"; // The Spring Boot API endpoint for insert
	    	//jhaTlVisualsopmst.setUpsmActive("Y");
	        
	        //String jsonPayload = insertJson(jhaTlVisualsopmst);
	       
	        String jsonPayload = jhaTlVisualsopdtl.toJsonManual();
	        CommonMessage.debugMsg("Json :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        // Get the response code to confirm insert success, usually 201 Created
	        CommonMessage.debugMsg(res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(jsonResponse);
	        CommonMessage.debugMsg(dtl);
	        
	        return dtl;
		}
	    
            // getting details in detail table by keyid
            
            public JhaTlVisualsopdtl getById(String keyId) throws IOException
            {
            	
            	 String apiUrl = "/visualsop/getbykeyid/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
            	          
            	
                HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

                // Get the response code to confirm insert success, usually 201 Created
               CommonMessage.debugMsg(res.getBody()+"Body");
               String json = res.getBody();
               
               JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
               
              
               return dtl;
            }
            
            //delete by id in the detail table
            
            public JhaTlVisualsopdtl deletetById(JhaTlVisualsopdtl oldjhaTlVisualsopdtl) throws IOException
            {
            	
            	String keyId = oldjhaTlVisualsopdtl.getVsodKeyid();
            	
            	 String apiUrl = "/visualsop/deletebyrefkeyid/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
            	          
            	
                HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

                // Get the response code to confirm insert success, usually 201 Created
               CommonMessage.debugMsg(res.getBody()+"Body");
               String json = res.getBody();
               
//               JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
               
              
               return oldjhaTlVisualsopdtl;
            }
            
            // getting details from mst table
            
            public JhaTlVisualsopmst getByMstKeyid(String keyId) throws IOException
            {
            	
            	 String apiUrl = "/visualsop/findByMstKeyid/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
            	          
            	
                HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

                // Get the response code to confirm insert success, usually 201 Created
               CommonMessage.debugMsg(res.getBody()+"Body");
               String json = res.getBody();
               
               JhaTlVisualsopmst mst = JhaTlVisualsopmst.fromJson(json);
               
              
               return mst;
            }
            
            // getting details from the detail table all values
            
//            public List<String[]> getdetailbyKeyid(String keyId) throws Exception {
//
//                String apiUrl = "/visualsop/findBydetkeyid/"
//                        + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
//
//                HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//
//                if (res.getStatusCode() != 200) {
//                    throw new RuntimeException("API failed: " + res.getBody());
//                }
//
//                JSONArray jsonArray = new JSONArray(res.getBody());
//                return convertJsonArrayToListwithcolumnorder(jsonArray);
//            }

            
            public List<String[]> getdetailbyKeyid(String keyId) throws Exception {
            	
            	CommonMessage.debugMsg("detail grid=========xxxxxxxxxxxxxxxxxxx======== "+keyId);

//                String apiUrl = "/visualsop/findBydetkeyid/"
//                        + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
                      //  + "?fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            	String id =CommonFunctions.isValidKeyId(keyId) ? keyId : "null";
                
                String apiUrl = "/visualsop/findBydetkeyid/"+id;
                CommonMessage.debugMsg("apiUrl:"+apiUrl);
                HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

                String json = res.getBody();
                
                CommonMessage.debugMsg("Response:"+json);

               JSONArray jsonArray = new JSONArray(json);
               List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
                return result;
            }
            
            //converting json yo list
            
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
            
            
//            public JhaTlVisualsopdtl getdetailbyKeyid(String keyId) throws IOException
//            {
//            	
//            	 String apiUrl = "/visualsop/findBydetkeyid/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
//            	          
//            	
//                HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
//
//                // Get the response code to confirm insert success, usually 201 Created
//               CommonMessage.debugMsg(res.getBody()+"Body");
//               String json = res.getBody();
//               
//               JhaTlVisualsopdtl dt = JhaTlVisualsopdtl.fromJson(json);
//               
//              
//               return dt;
//            }
            
            //deleting two tables by using master keyid
            public JhaTlVisualsopmst delete (JhaTlVisualsopmst oldjhaTlVisualsopmst) throws IOException
            {
            	
            	String keyId = oldjhaTlVisualsopmst.getVsomKeyid();
            	
            	 String apiUrl = "/visualsop/delete/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
            	          
            	
                HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

                // Get the response code to confirm insert success, usually 201 Created
               CommonMessage.debugMsg(res.getBody()+"Body");
               String json = res.getBody();
               
            //   JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
               
              
               return oldjhaTlVisualsopmst;
            }
            
            
            
	 
	    
	    

}
