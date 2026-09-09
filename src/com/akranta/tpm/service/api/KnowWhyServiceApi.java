package com.akranta.tpm.service.api;

import java.io.IOException;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URI;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlBestmst;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlKnowwhymst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class KnowWhyServiceApi {
	private final Api api;
	
	public KnowWhyServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	public QtmTlKnowwhymst saveKnowWhy(QtmTlKnowwhymst qtmTlKnowwhymst) 
			throws Exception {
        
		String apiUrl = "/knowWhy/save"; 
		
        
		String jsonPayload = insertJson(qtmTlKnowwhymst);
        CommonMessage.debugMsg("Json Know Why:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        QtmTlKnowwhymst mst = getJson(jsonResponse);
        CommonMessage.debugMsg(mst.getKnwmKeyid());
        return mst;
        

}
	
	public QtmTlKnowwhymst saveKnowWhyMst(QtmTlKnowwhymst qtmTlKnowwhymst) 
			throws Exception {
        
		String apiUrl = "/knowWhy/saveKnowWhyMst"; 
		
        
		String jsonPayload = qtmTlKnowwhymst.toJsonManual();
        CommonMessage.debugMsg("Json Know Why:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        QtmTlKnowwhymst mst = QtmTlKnowwhymst.fromJson(jsonResponse);
        CommonMessage.debugMsg(mst.getKnwmKeyid());
        return mst;
        

}
	
	public String insertJson(QtmTlKnowwhymst qtmTlKnowwhymst) 
	{
		StringBuilder str = new StringBuilder();
		
		//Master Details
		str.append("{");
		
		str.append("\"qtmTlKnowwhymst\":");  
		String JsonMst = qtmTlKnowwhymst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		QtmTlKnowwhydtl dtls = qtmTlKnowwhymst.getqtmTlKnowwhydtl();
		str.append("\"qtmTlKnowwhydtl\":");
		String JsonDtl = dtls.toJsonManual();
		str.append(JsonDtl);
		
		
		str.append("}");
		return str.toString();
      }
	
	public QtmTlKnowwhymst getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		JSONObject mstObj = jsonObj.getJSONObject("qtmTlKnowwhymst");
		JSONObject dtlObj = jsonObj.getJSONObject("qtmTlKnowwhydtl");
		
		QtmTlKnowwhymst mst = QtmTlKnowwhymst.fromJson(mstObj.toString());
		QtmTlKnowwhydtl dtl = QtmTlKnowwhydtl.fromJson(dtlObj.toString());
		
		
		
		mst.setQtmTlKnowwhydtl(dtl);
		
		return mst;
		
	}
	
	
	
	
	
	public QtmTlKnowwhymst getById(String keyId) throws IOException
	{
		
		 String apiUrl = "/knowWhy/getKnowWhy" +"?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		          
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       QtmTlKnowwhymst mst = QtmTlKnowwhymst.fromJson(json);
       
      
       return mst;
       
//        int responseCode = res.getStatusCode();
	}
	
	public String saveKnowWhyApproval(String keyId) throws IOException
	{
		
		 String apiUrl = "/knowWhy/saveKnowWhyApproval" +"?appKeyid=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		          
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
      
       return json;
       
//        int responseCode = res.getStatusCode();
	}
	
	public QtmTlKnowwhymst DeleteKnowWhy(QtmTlKnowwhymst qtmTlKnowwhymst) throws IOException
	{
		String keyId = qtmTlKnowwhymst.getKnwmKeyid();
		 String apiUrl = "/knowWhy/deleteKnowWhy";
		         
		 String json = qtmTlKnowwhymst.toJsonManual();
		 
		 CommonMessage.debugMsg("Json response "+json);
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", json);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       
      
      
       return qtmTlKnowwhymst;
       
//        int responseCode = res.getStatusCode();
	}
	
	public String compJsonBestKaizenRecall(String flid, String fromMonth, String kznBankType) {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("{");
	    sb.append("\"flid\":");
	    sb.append("\"").append(flid).append("\"");
	    sb.append(",\"kznBankType\":");
	    sb.append("\"").append(kznBankType).append("\"");
	    sb.append(",\"fromMonth\":");
	    sb.append("\"").append(fromMonth).append("\"");
	    sb.append("}");
	    CommonMessage.debugMsg(sb.toString());
	    return sb.toString();
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
	
	
	

	
	
	
	
}
