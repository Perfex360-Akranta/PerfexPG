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

import com.akranta.tpm.model.GenTlControlandresponseplan;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlBestmst;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.model.QtmTlQpoint;
import com.akranta.tpm.model.QtmTlQpointdtl;
import com.akranta.tpm.model.QtmTlQpointdtls;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class QpointServiceApi {
	private final Api api;
	
	public QpointServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	public  QtmTlQpoint  saveQpoint (QtmTlQpoint  qtmTlQpoint ) 
			throws Exception {
        
		String apiUrl = "/qtm_tl_qpoint/save"; 
		
        
			String jsonPayload = insertJson(qtmTlQpoint);
		
        CommonMessage.debugMsg("Json Know Why:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        QtmTlQpoint  mst = getJson(jsonResponse);
        
        return mst;
        

}
	
	public String insertJson(QtmTlQpoint  qtmTlQpoint) 
	{
		StringBuilder str = new StringBuilder();
		
		//Master Details
		str.append("{");
		
		str.append("\"qtmTlmst\":");  
		String JsonMst = qtmTlQpoint.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		QtmTlQpointdtls dtls = qtmTlQpoint.getQtmTlQpointdtls();
		str.append("\"qtmTldtl\":");
		String JsonDtl = dtls.toJsonManual();
		str.append(JsonDtl);
		
		
		str.append("}");
		return str.toString();
      }
	
	public QtmTlQpoint getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		JSONObject mstObj = jsonObj.getJSONObject("qtmTlmst");
		JSONObject dtlObj = jsonObj.getJSONObject("qtmTldtl");
		
		QtmTlQpoint mst = QtmTlQpoint.fromJson(mstObj.toString());
		QtmTlQpointdtls dtl = QtmTlQpointdtls.fromJson(dtlObj.toString());
		
		
		
		mst.setQtmTlQpointdtls(dtl);
		
		return mst;
		
	}
	
	
	
	public List<String[]> selectData(String flid, String fromMonth, String kznBankType) throws IOException
	{   
		
		 String apiUrl = "/bestKaizen/getData";
		          
		// Send request
		 String jsonBK = compJsonBestKaizenRecall(flid, fromMonth, kznBankType);
		 
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonBK);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
	}
	
	public  QtmTlQpoint getById(String keyId) throws IOException
	{
		
		 String apiUrl = "/qtm_tl_qpoint/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		           	
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       QtmTlQpoint mst = QtmTlQpoint.fromJson(json);
       
      
       return mst;
       
//        int responseCode = res.getStatusCode();
	}
	
	public GenTlControlandresponseplan DeleteGenTlControlandresponseplan(GenTlControlandresponseplan Controlandresponseplan ) throws IOException
	{
		String keyId = Controlandresponseplan.getCarpKeyid();
		String apiUrl = "/genticontrolandresponseplan/deleteById" +"?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		          
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       
      
      
       return  Controlandresponseplan ; 
       
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
