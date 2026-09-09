package com.akranta.tpm.service.api;

import java.net.HttpURLConnection;
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
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class AbnormalityServiceApi {
	private final Api api;
	
	public AbnormalityServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	public AbnTlAbnormality insertRecord(AbnTlAbnormality abnTlAbnormality) throws Exception {
        String apiUrl = "/abnormality"; // The Spring Boot API endpoint for insert
        abnTlAbnormality.setAbnmActive("Y");
        
        // Convert object to JSON
        String jsonPayload = abnTlAbnormality.toJsonManual();
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
        
        return abn;
        

}
	
	
	public List<AbnTlAbnormality> MultipleSave(List<AbnTlAbnormality> abnTlAbnormality) throws Exception {
        String apiUrl = "/abnormality/multipleAbnSave"; 
        
        // Convert object to JSON
        String jsonPayload = AbnTlAbnormality.toJsonManualList(abnTlAbnormality);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        List<AbnTlAbnormality> abns = AbnTlAbnormality.fromJsonList(jsonResponse);
        
        return abns;
        

}
	public AbnTlAbnormality select(String KeyId) throws Exception {
        String apiUrl = "/abnormality/"+KeyId;
        
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET",null);

   
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
        
        return abn;
        

}
	
	public List<String[]> getMultipleAbnDetail(List<String> KeyIds) throws Exception {
        String apiUrl = "/abnormality/multipleAbn";
        if (KeyIds == null) {
            KeyIds = new ArrayList<>();
        }

        // optional: add default value only if needed
        if (KeyIds.isEmpty()) {
            KeyIds.add("");
        }
        
        String json = KeyIds.stream()
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.joining(",", "[", "]"));

        CommonMessage.debugMsg(json);
        
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",json);

   
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        

        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
        
        
        return datalist;
        

}
	
	public List<AbnTlAbnormality> updateAbnAllocation(List<AbnTlAbnormality> abnTlAbnormality) throws Exception {
        String apiUrl = "/abnormality/updateAllocation"; // The Spring Boot API endpoint for insert
        

//        String jsonPayload = AbnTlAbnormality.toJsonManualList(abnTlAbnormality);
        String jsonPayload = allocationJsonList(abnTlAbnormality);
        
        CommonMessage.debugMsg("Json :: "+jsonPayload);

     // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        CommonMessage.debugMsg(res.getStatusCode());
        int responseCode = res.getStatusCode();
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        List<AbnTlAbnormality> abn = AbnTlAbnormality.fromJsonList(jsonResponse);
        
        return  abn;
}

	
	public AbnTlAbnormality updateAbnComp(AbnTlAbnormality abnTlAbnormality) throws Exception {
        String apiUrl = "/abnormality/update"; // The Spring Boot API endpoint for insert
        
//        String dateTime = CommonFunctions.pg_dateTimeNow();
		String currentDate = CommonFunctions.pg_dateTimeNow();
		CommonMessage.debugMsg("Inside fill Valueslgfkjgfj");

		CommonMessage.debugMsg("fill values:");
		
//		String jsonPayload1 = compJsonManual(abnTlAbnormality);
//        CommonMessage.debugMsg("Json1 :: "+jsonPayload1);
		if( abnTlAbnormality.getAbnmWoendtime() == null )
			abnTlAbnormality.setAbnmWoendtime(currentDate);
		else
		{
			abnTlAbnormality.setAbnmWoendtime(CommonFunctions.pg_getDateTimeFromDate(abnTlAbnormality.getAbnmWoendtime()));
		}
		String jsonPayload = compJsonManual(abnTlAbnormality);
        CommonMessage.debugMsg("Json :: "+jsonPayload);

     // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        int responseCode = res.getStatusCode();
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
        
        return abn;
}
	public static String allocationJson(AbnTlAbnormality abnTlAbnormality) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyid\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmKeyid()).append("\"");
	    sb.append(",\"responsibleid\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmResponsibleid()).append("\"");
	    sb.append(",\"tradeid\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmTradeid()).append("\"");
	    sb.append(",\"effectivedate\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmEffectivedate()).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
	public static String allocationJsonList(List<AbnTlAbnormality> list) {

	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(allocationJson(list.get(i)));
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
	
	public String compJsonManual(AbnTlAbnormality abnTlAbnormality) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyid\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmKeyid()).append("\"");
	    sb.append(",\"countermeasure\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmCountermeasure()).append("\"");
	    sb.append(",\"status\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmStatus()).append("\"");
	    sb.append(",\"completedby\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmCompletedby()).append("\"");
	    sb.append(",\"remarks\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmRemarks()).append("\"");
	    sb.append(",\"woendtime\":");
	    sb.append("\"").append(abnTlAbnormality.getAbnmWoendtime()).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
	public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray) {
		
	    List<String[]> list = new ArrayList<>();

	    if (jsonArray.length() < 1) {
	        throw new IllegalArgumentException("JSON array must have at least 1 rows (config, header, column order).");
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

	    return list;
	}
}
