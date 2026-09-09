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
import java.util.stream.Collectors;

import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlBestmst;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class BestKaizenServiceApi {
	private final Api api;
	
	public BestKaizenServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	public KznTlBestmst saveBestKaizen(KznTlBestmst kznTlBestmst,KznTlBestmst existKznTlBestmst) 
			throws Exception {
        
		String apiUrl = "/bestKaizen/save"; 
		
        
		String jsonPayload = insertJson(kznTlBestmst,existKznTlBestmst);
        CommonMessage.debugMsg("Json internal Rejection:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        KznTlBestmst mst = getJson(jsonResponse);
        CommonMessage.debugMsg(mst.getKzbmKeyid());
        return mst;
        

}
	
	public String insertJson(KznTlBestmst kznTlBestmst,KznTlBestmst exiKznTlBestmst) 
	{
		List<String> keyIds = new ArrayList<>();
		if(exiKznTlBestmst != null && exiKznTlBestmst.getKznTlBestdtl() != null) 
		{
			keyIds = exiKznTlBestmst.getKznTlBestdtl().stream().map(KznTlBestdtl::getKzbdKeyid).toList();
			
		}
		String keyIdsJson = keyIds.stream()
		        .map(id -> "\"" + id + "\"")
		        .collect(Collectors.joining(",", "[", "]"));
		StringBuilder str = new StringBuilder();
		
		
		
		
		//Master Details
		str.append("{");
		
		str.append("\"kznTlBestmst\":");  
		String JsonMst = kznTlBestmst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		List<KznTlBestdtl> dtls = kznTlBestmst.getKznTlBestdtl();
		str.append("\"bestdtls\":");
		String JsonDtl = KznTlBestdtl.toJsonManualList(dtls);
		str.append(JsonDtl);
		str.append(",");
		
		str.append("\"keyIds\":");
		str.append(keyIdsJson);
		
		
		
		
		
		str.append("}");
		return str.toString();
      }
	
	public KznTlBestmst getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		JSONObject mstObj = jsonObj.getJSONObject("kznTlBestmst");
		JSONArray dtlArray = jsonObj.getJSONArray("bestdtls");
		
		KznTlBestmst mst = KznTlBestmst.fromJson(mstObj.toString());
		
		List<KznTlBestdtl> dtl = KznTlBestdtl.fromJsonList(dtlArray.toString());
		
		mst.setKznTlBestdtl(dtl);
		
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
	
	public KznTlBestmst getById(String keyId) throws IOException
	{
		
		 String apiUrl = "/bestKaizen/getById" +"?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		          
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       KznTlBestmst mst = KznTlBestmst.fromJson(json);
       
      
       return mst;
       
//        int responseCode = res.getStatusCode();
	}
	
	public KznTlBestmst DeleteBestKaizen(KznTlBestmst kznTlBestmst) throws IOException
	{
		String keyId = kznTlBestmst.getKzbmKeyid();
		 String apiUrl = "/bestKaizen/delete" +"?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		          
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       
      
      
       return kznTlBestmst;
       
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
