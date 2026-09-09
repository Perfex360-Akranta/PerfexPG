package com.akranta.tpm.service.api;

import java.net.HttpURLConnection;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FunctionCallApi {
	
private final Api api;
	
	public FunctionCallApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

//	public List<String[]> callFunction(String functionName, List<String> paramValues) throws Exception
//	{
//		String apiUrl = "/db/callFunction/"+functionName; // The Spring Boot API endpoint for insert
//   
//		//System.out.flush();
//		String jsonPayload = fnJson(paramValues);
//        CommonMessage.debugMsg("Json :: "+jsonPayload);
//        //System.out.flush();
//
//     // Send request
//        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
//
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(res.getStatusCode());
//        //System.out.flush();
////        int responseCode = res.getStatusCode();
//        
//        String jsonResponse = res.getBody();
//        //CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        JSONObject obj = JSONObject.fromObject(jsonResponse);
//        
//        CommonMessage.debugMsg("Total Row Count :"+obj.get("totalcnt"));
//        //System.out.flush();
////        CommonMessage.debugMsg("Total Row  :"+obj.get("cur"));
//        JSONArray arr = obj.getJSONArray("cur");
//        
//        paramValues.add(0, String.valueOf(obj.get("totalcnt")));
//
//        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
//        CommonMessage.debugMsg("List of String Array  :"+datalist);
//               
//        return  datalist;
//	}
	
	public List<String[]> callFunction(String functionName, List<String> paramValues,int colOrderNo,boolean isDataOrder) throws Exception
	{
		String apiUrl = "/db/callFunction/"+functionName; // The Spring Boot API endpoint for insert
   
		//System.out.flush();
		String jsonPayload = fnJson(paramValues);
        CommonMessage.debugMsg(functionName+" :: Json :: "+jsonPayload);
        //System.out.flush();

     // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
       // CommonMessage.debugMsg(res.getStatusCode());
        //System.out.flush();
//        int responseCode = res.getStatusCode();
        
        String jsonResponse = res.getBody();
        //CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONObject obj = JSONObject.fromObject(jsonResponse);
        
        CommonMessage.debugMsg("Total Row Count :"+obj.get("totalcnt"));
        //System.out.flush();
//        CommonMessage.debugMsg("Total Row  :"+obj.get("cur"));
        JSONArray arr = obj.getJSONArray("cur");
        
        paramValues.add(0, String.valueOf(obj.get("totalcnt")));
       // CommonMessage.debugMsg(" before convert :"+ CommonFunctions.dateTimeNow());
        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr,colOrderNo,isDataOrder);
       // CommonMessage.debugMsg(" After convert :"+ CommonFunctions.dateTimeNow());
      //  CommonMessage.debugMsg("List of String Array  :"+datalist);
               
        return  datalist;
	}
	
	
	public List<String[]> callFunctionWithHeaders(String functionName, List<String> paramValues,int colOrderNo,boolean isDataOrder) throws Exception
	{
		String apiUrl = "/db/callFunction/"+functionName; // The Spring Boot API endpoint for insert
   
		//System.out.flush();
		String jsonPayload = fnJson(paramValues);
       // CommonMessage.debugMsg("Json :: "+jsonPayload);
        //System.out.flush();

     // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        //System.out.flush();
//        int responseCode = res.getStatusCode();
        
        String jsonResponse = res.getBody();
        //CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONObject obj = JSONObject.fromObject(jsonResponse);
        
        CommonMessage.debugMsg("Total Row Count :"+obj.get("totalcnt"));
        //System.out.flush();
//        CommonMessage.debugMsg("Total Row  :"+obj.get("cur"));
        JSONArray arr = obj.getJSONArray("cur");
        
        paramValues.add(0, String.valueOf(obj.get("totalcnt")));

        List<String[]> datalist = convertJsonArrayToListwitHeader(arr,colOrderNo,isDataOrder);
        //CommonMessage.debugMsg("List of String Array  :"+datalist);
               
        return  datalist;
        
	}
	
	public List<String[]> callMultiParamFunction(String functionName, List<String> paramValues,int colOrderNo,boolean isDataOrder) throws Exception
	{
		String apiUrl = "/db/callMultiParamFunction/"+functionName; // The Spring Boot API endpoint for insert
   
		//System.out.flush();
		String jsonPayload = fnMultiParamJson(paramValues);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        //System.out.flush();

     // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        //System.out.flush();
//        int responseCode = res.getStatusCode();
        
        String jsonResponse = res.getBody();
        //CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONObject obj = JSONObject.fromObject(jsonResponse);
        
        CommonMessage.debugMsg("Total Row Count :"+obj.get("totalcnt"));
        CommonMessage.debugMsg("dataList :"+obj.get("cur"));
       //System.out.flush();
//        CommonMessage.debugMsg("Total Row  :"+obj.get("cur"));
        JSONArray arr = obj.getJSONArray("cur");
        
        paramValues.add(0, String.valueOf(obj.get("totalcnt")));

        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr,colOrderNo,isDataOrder);
        CommonMessage.debugMsg("List of String Array  :"+datalist);
               
        return  datalist;
	}
	
	public String fnJson( List<String> paramValues) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"vconditionparam\":");
	    sb.append("\"").append(paramValues.get(0)).append("\"");
	    sb.append(",\"vcommonparam\":");
	    sb.append("\"").append(paramValues.get(1)).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
	public String fnMultiParamJson(List<String> paramValues) {
	    StringBuilder sb = new StringBuilder("{");

	    for (int i = 0; i < paramValues.size(); i++) {
	        sb.append("\"").append(i + 1).append("\":");
	        if(CommonFunctions.isValidKeyId(paramValues.get(i))) {
	        	sb.append("\"").append(paramValues.get(i)).append("\"");
	        }else {
	        	sb.append("null");
	        }
	        
	        if (i < paramValues.size() - 1) {
	            sb.append(",");
	        }
	    }

	    sb.append("}");
	    return sb.toString();
	}
	
	public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray,int colOrderNo,boolean isDataOrder) {
	    List<String[]> list = new ArrayList<>();

	    if (jsonArray.length() < colOrderNo) {
	        throw new IllegalArgumentException("JSON array must have at least colOrderNo rows (config, header, column order).");
	    }

	    // Row 3 = column order mapping
	    JSONObject orderObj = jsonArray.getJSONObject(colOrderNo-1);

	    // Build ordered list of keys based on orderObj values
	    List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
	    Iterator<String> it = orderObj.keys();
	    while (it.hasNext()) {
	        String key = it.next();
	        CommonMessage.debugMsg(key);
	        try {
	            int order = Integer.parseInt(orderObj.getString(key));
	            orderList.add(new AbstractMap.SimpleEntry<>(key, order));
	        } catch (NumberFormatException e) {
	            orderList.add(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
	        }
	    }
	    
	    if (isDataOrder) {
	        orderList.removeIf(entry ->
	                "dataorder".equalsIgnoreCase(entry.getKey()));
	    }

	    // Sort keys by numeric order
	    orderList.sort(Comparator.comparingInt(Map.Entry::getValue));
	    
	    

	    // Process each row (from row 1 onwards if you want headers, or from row 3 for data only)
	    for (int i = 0; i < jsonArray.length(); i++) {
	    	if( i == colOrderNo-1) {
	    		continue;
	    	}
	        JSONObject obj = jsonArray.getJSONObject(i);
	        String[] row = new String[orderList.size()];

	        int colIndex = 0;
	        for (Map.Entry<String, Integer> entry : orderList) {
	            String key = entry.getKey();
	            Object value = obj.opt(key);
	           // row[colIndex++] = value != null ? value.toString() : "";
	            row[colIndex++] = (value != null && !"null".equals(value.toString())) ? value.toString() : "";
	        }

	        list.add(row);
	    }

//	    
//	    for (String[] row : list) {
//	        CommonMessage.debugMsg(Arrays.toString(row));
//	    }

	    return list;
	}
	
	public List<String[]> convertJsonArrayToListwitHeader(JSONArray jsonArray,int colOrderNo,boolean isDataOrder) {
	    List<String[]> list = new ArrayList<>();

	    if (jsonArray.length() < colOrderNo) {
	        throw new IllegalArgumentException("JSON array must have at least colOrderNo rows (config, header, column order).");
	    }

	    // Row 3 = column order mapping
	    JSONObject orderObj = jsonArray.getJSONObject(colOrderNo-1);

	    // Build ordered list of keys based on orderObj values
	    List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
	    Iterator<String> it = orderObj.keys();
	    while (it.hasNext()) {
	        String key = it.next();
	        //CommonMessage.debugMsg(key);
	        try {
	            int order = Integer.parseInt(orderObj.getString(key));
	            orderList.add(new AbstractMap.SimpleEntry<>(key, order));
	        } catch (NumberFormatException e) {
	            orderList.add(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
	        }
	    }
	    
	    if (isDataOrder) {
	        orderList.removeIf(entry ->
	                "dataorder".equalsIgnoreCase(entry.getKey()));
	    }

	    // Sort keys by numeric order
	    orderList.sort(Comparator.comparingInt(Map.Entry::getValue));
	    
	    String[] header = new String[orderList.size()];
	    
	    int colHeaderIndex = 0;
	    for (Map.Entry<String, Integer> entry : orderList) {
            String value = entry.getKey();
            header[colHeaderIndex++] = (value != null && !"null".equals(value.toString())) ? value.toString() : "";
        }
	    list.add(header);
	    
	    
	    

	    // Process each row (from row 1 onwards if you want headers, or from row 3 for data only)
	    for (int i = 0; i < jsonArray.length(); i++) {
	    	if( i == colOrderNo-1) {
	    		continue;
	    	}
	        JSONObject obj = jsonArray.getJSONObject(i);
	        String[] row = new String[orderList.size()];
	        
	        

	        int colIndex = 0;
	        for (Map.Entry<String, Integer> entry : orderList) {
	            String key = entry.getKey();
	            Object value = obj.opt(key);
	           // row[colIndex++] = value != null ? value.toString() : "";
	            row[colIndex++] = (value != null && !"null".equals(value.toString())) ? value.toString() : "";
	        }

	        list.add(row);
	    }

	    // Debug
//	    for (String[] row : list) {
//	        CommonMessage.debugMsg(Arrays.toString(row));
//	    }

	    return list;
	}

}
