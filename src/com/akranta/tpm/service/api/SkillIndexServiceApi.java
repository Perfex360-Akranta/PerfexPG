package com.akranta.tpm.service.api;

import java.io.IOException;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.jackson.Log4jXmlObjectMapper;

import com.akranta.tpm.bean.IntRejEntryBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.QtmTlIntrejectiondtl;
import com.akranta.tpm.model.QtmTlIntrejectionmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class SkillIndexServiceApi {
	private final Api api;
	
	public SkillIndexServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	
	

	public EntTlSkillindexassessmst saveSkillAssessment(EntTlSkillindexassessmst entTlSkillindexassessmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) 
			throws Exception {
        
		String apiUrl = "/skillAssessment/single/save"; 
		
        
		String jsonPayload = insertJson(entTlSkillindexassessmst,lstEntTlSkillindexassessdtl);
        
		
        
        CommonMessage.debugMsg("Json internal Rejection:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        EntTlSkillindexassessmst mst = getJson(jsonResponse);
        CommonMessage.debugMsg(mst.getSiamKeyid()+"Final Key ID");
        return mst;
        

}
	
	public String insertJson(EntTlSkillindexassessmst entTlSkillindexassessmst,List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) 
	{
		StringBuilder str = new StringBuilder();
		

		
		//Master Details
		str.append("{");
		
		str.append("\"skillAssessmentmstList\":");  
		String JsonMst = entTlSkillindexassessmst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		 
		str.append("\"skillindexassessdtlsList\":");
		String JsonDtl = EntTlSkillindexassessdtl.toJsonManualList(lstEntTlSkillindexassessdtl);
		str.append(JsonDtl);
		
		
		
		
		
		str.append("}");
		
		return str.toString();
        
		
	}
	
	public EntTlSkillindexassessmst getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		JSONObject mstObj = jsonObj.getJSONObject("skillAssessmentmstList");
		//JSONArray dtlArray = jsonObj.getJSONArray("skillindexassessdtlsList");
		
				
		EntTlSkillindexassessmst mst = EntTlSkillindexassessmst.fromJson(mstObj.toString());
		
		//List<EntTlSkillindexassessdtl> dtl = EntTlSkillindexassessdtl.fromJsonList(dtlArray.toString());
		
		
		return mst;
		
	}
	
	public EntTlSkillindexassessmst saveMultipleSkillAssessment(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) 
			throws Exception {
        
		String apiUrl = "/skillAssessment/multiple/save"; 
		
        
		String jsonPayload = insertJsonMultiple(lstEntTlSkillindexassessmstmst,lstEntTlSkillindexassessdtl);
        
		
        
        CommonMessage.debugMsg("Json internal Rejection:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        EntTlSkillindexassessmst mst = getJsonMultiple(jsonResponse);
        CommonMessage.debugMsg(mst.getSiamKeyid()+"Final Key ID");
        return mst;
        

}
	
	public String insertJsonMultiple(List<EntTlSkillindexassessmst> entTlSkillindexassessmst,List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) 
	{
		StringBuilder str = new StringBuilder();
		

		
		//Master Details
		str.append("{");
		
		str.append("\"entTlSkillindexassessmsts\":");  
		String JsonMst = EntTlSkillindexassessmst.toJsonManualList(entTlSkillindexassessmst);
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		 
		str.append("\"entTlSkillindexassessdtls\":");
		String JsonDtl = EntTlSkillindexassessdtl.toJsonManualList(lstEntTlSkillindexassessdtl);
		str.append(JsonDtl);
		
		
		
		
		
		str.append("}");
		
		return str.toString();
        
		
	}
	
	public EntTlSkillindexassessmst getJsonMultiple(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
	
		JSONArray dtlArray = jsonObj.getJSONArray("entTlSkillindexassessdtls");
		JSONArray mstArray = jsonObj.getJSONArray("entTlSkillindexassessmsts");
		
				
		
		
		List<EntTlSkillindexassessdtl> dtl = EntTlSkillindexassessdtl.fromJsonList(dtlArray.toString());
		
		List<EntTlSkillindexassessmst> mst = EntTlSkillindexassessmst.fromJsonList(mstArray.toString());
		
		
		return mst.get(0);
		
	}
	

	public List<String[]> getEmpList(String flid,String uniqPosId,String reviewDate) throws IOException
	{
		
		 String apiUrl = "/skillAssessment/single/getEmpList";
		 
		 String jsonPayLoad = compJsonSkillIndexGrid(flid, uniqPosId, reviewDate);
		          
		// Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayLoad);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
	}
	
	public List<String[]> getEmpListMultiple(String flid,String uniqPosId,String reviewDate) throws IOException
	{
		
		 String apiUrl = "/skillAssessment/multiple/getEmpList";
		 
		 String jsonPayLoad = compJsonSkillIndexGrid(flid, uniqPosId, reviewDate);
		          
		// Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayLoad);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
	}
	
	public List<String[]> getSkillIndexRadarChart(String fromDate, String flid, String uniqPosId, String empIds) throws IOException
	{
		
		 String apiUrl = "/skillAssessment/single/getSkillIndexRadarChart";
		 
		 String jsonPayLoad =  compJsonSkillIndexRadarChart(fromDate, flid, uniqPosId,empIds);
		          
		// Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayLoad);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//      int responseCode = res.getStatusCode();
	}
	
	public String compJsonSkillIndexRadarChart(String fromDate, String flid, String uniqPosId, String empIds) {
	    StringBuilder sb = new StringBuilder();
	    
	    empIds = empIds.replace("'", "").trim();
	    sb.append("{");
	    sb.append("\"fromDate\":");
	    sb.append("\"").append(fromDate).append("\"");
	    sb.append(",\"flid\":");
	    sb.append("\"").append(flid).append("\"");
	    sb.append(",\"uniquePosId\":");
	    sb.append("\"").append(uniqPosId).append("\"");
	    sb.append(",\"empmKeyIds\":");
	    sb.append("\"").append(empIds).append("\"");
	    sb.append("}");
	    CommonMessage.debugMsg(sb.toString());
	    return sb.toString();
	}
	
	

	public String compJsonSkillIndexGrid(String flid,String uniqPosid,String startDate) {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("{");
	    sb.append("\"flid\":");
	    sb.append("\"").append(flid).append("\"");
	    sb.append(",\"uniqPosid\":");
	    sb.append("\"").append(uniqPosid).append("\"");
	    sb.append(",\"startDate\":");
	    sb.append("\"").append(startDate).append("\"");
	    sb.append("}");
	    CommonMessage.debugMsg(sb.toString());
	    return sb.toString();
	}
	
	public QtmTlIntrejectionmst getInternalRejectionMasterData(String id) throws IOException
	{
		
		 String apiUrl = "/internalRejection/getById"
		            + "?id=" + URLEncoder.encode(id, StandardCharsets.UTF_8);
		          
		// Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       QtmTlIntrejectionmst result = QtmTlIntrejectionmst.fromJson(json);
       return result;
//        int responseCode = res.getStatusCode();
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
