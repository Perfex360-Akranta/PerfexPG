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


public class InternalRejectionSerivceApi {
	private final Api api;
	
	public InternalRejectionSerivceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	public QtmTlIntrejectiondtl saveInternalRejection(QtmTlIntrejectionmst qtmIntrejectionmst,QtmTlIntrejectiondtl qtmTlIntrejectiondtl,IntRejEntryBean intRejEntryBean) 
			throws Exception {
        
		String apiUrl = "/internalRejection/save"; 
		
        
		String jsonPayload = insertJson(qtmIntrejectionmst,qtmTlIntrejectiondtl,intRejEntryBean);
        
		
        
        CommonMessage.debugMsg("Json internal Rejection:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        QtmTlIntrejectiondtl dtl = QtmTlIntrejectiondtl.fromJson(jsonResponse);
        CommonMessage.debugMsg(dtl);
        return dtl;
        

}
	
	public String insertJson(QtmTlIntrejectionmst qTlIntrejectionmst,QtmTlIntrejectiondtl qtmTlIntrejectiondtl,IntRejEntryBean intRejEntryBean) 
	{
		StringBuilder str = new StringBuilder();
		

		
		//Master Details
		str.append("{");
		
		str.append("\"qtmTlIntrejectionmst\":");  
		String JsonMst = qTlIntrejectionmst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		
		str.append("\"qtmTlIntrejectiondtl\":");
		String JsonDtl = qtmTlIntrejectiondtl.toJsonManual();
		str.append(JsonDtl);
		str.append(",");
		
		
		str.append("\"intRejEntryDto\":");
		str.append("{");
		str.append("\"qirmKeyid\":");
		str.append("\"").append(intRejEntryBean.getQirmKeyid()).append("\"");
		str.append(",\"qirdKeyid\":");
		str.append("\"").append(intRejEntryBean.getQirdKeyid()).append("\"");
		str.append("}");
		
		str.append("}");
		
		return str.toString();
        
		
	}
	
	public List<String[]> getInternalRejectionMstGrid(String flid) throws IOException
	{
		
		 String apiUrl = "/internalRejection/grid"
		            + "?flid=" + URLEncoder.encode(flid, StandardCharsets.UTF_8);
		          
		// Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
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
