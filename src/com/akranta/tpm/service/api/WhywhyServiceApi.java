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
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlYyeffectivemst;
import com.akranta.tpm.model.BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BdmTlYydonebymst;
import com.akranta.tpm.model.BdmTlYyproblemattbymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class WhywhyServiceApi {
	private final Api api;
	
	public WhywhyServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	 public BdmTlWhywhymst insertRecord(BdmTlWhywhymst bdmTlWhywhymst) throws Exception {
	        
	        String apiUrl = "/whywhy/save";
	        bdmTlWhywhymst.setWwmsActive("Y");
	        
	        String jsonPayload = insertJson(bdmTlWhywhymst);
	        
	        CommonMessage.debugMsg("Json :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        CommonMessage.debugMsg(res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        BdmTlWhywhymst whywhymst = getJson(jsonResponse);
	        CommonMessage.debugMsg(whywhymst);
	        return whywhymst;
	    }

	 public BdmTlYyeffectivemst insertEffectiveness(BdmTlYyeffectivemst newBdmTlYyeffectivemst	) throws Exception{

	        
	        CommonMessage.debugMsg("Starting effectiveness save ***********************");
	        String apiUrl = "/whywhy/save-effectiveness";
	        newBdmTlYyeffectivemst.setYyefActive("Y");
	        
	        CommonMessage.debugMsg("Starting effectiveness save ***********************");
	        String jsonPayload = insertEffectivenessJson(newBdmTlYyeffectivemst);
	        
	        CommonMessage.debugMsg("Json Effectiveness:: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	        CommonMessage.debugMsg(res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        BdmTlYyeffectivemst effectivemst = getEffectivenessJson(jsonResponse);

	
	        
	        CommonMessage.debugMsg(effectivemst);
	        
	        return effectivemst;
	    }

	    public String insertJson(BdmTlWhywhymst bdmTlWhywhymst) {
	        StringBuilder str = new StringBuilder();
	        
	        // Master Details
	        str.append("{");
	        
	        str.append("\"master\":");  
	        String JsonMst = bdmTlWhywhymst.toJsonManual();
	        str.append(JsonMst);
	        str.append(",");
	        
	        // Detail Table Values
	        List<BdmTlWhywhydtl> details = bdmTlWhywhymst.getBdmTlWhywhydtl();
	        str.append("\"details\":");
	        String JsonDtl = BdmTlWhywhydtl.toJsonManualList(details);
	        str.append(JsonDtl);
	        str.append(",");
	        
	        // DoneBy List
	        List<BdmTlYydonebymst> doneByList = bdmTlWhywhymst.getBdmTlYydonebymst();
	        str.append("\"doneByList\":");
	        String JsonDoneBy = BdmTlYydonebymst.toJsonManualList(doneByList);
	        str.append(JsonDoneBy);
	        str.append(",");
	        
	        // ProblemAttendedBy List
	        List<BdmTlYyproblemattbymst> problemAttByList = bdmTlWhywhymst.getBdmTlYyproblemattbymst();
	        str.append("\"problemAttendedByList\":");
	        String JsonProblemAtt = BdmTlYyproblemattbymst.toJsonManualList(problemAttByList);
	        str.append(JsonProblemAtt);
	        
	        str.append("}");
	        
	        return str.toString();
	    }
	    
	    public String insertEffectivenessJson(BdmTlYyeffectivemst bdmTlYyeffectivemst) {
	        StringBuilder str = new StringBuilder();
	        str.append("{");
	        
	        // Add master object with the wwms_keyid
	        str.append("\"master\":");
	        str.append("{\"keyid\":\"").append(bdmTlYyeffectivemst.getYyefWwmsKeyid()).append("\"}");
	        str.append(",");
	        
	        // Effective Master List
	        str.append("\"effectiveMaster\":");
	        List<BdmTlYyeffectivemst> effectiveMasters = bdmTlYyeffectivemst.getBdmTlYyeffectivemst();
	        String JsonEffMst = BdmTlYyeffectivemst.toJsonManualList(effectiveMasters);
	        str.append(JsonEffMst);
	        str.append(",");
	        
	        // Effective Details
	        List<BdmTlYyeffectivedtl> effectiveDetails = bdmTlYyeffectivemst.getBdmTlYyeffectivedtl();
	        str.append("\"effectiveDetails\":");
	        String JsonEffDtl = BdmTlYyeffectivedtl.toJsonManualList(effectiveDetails);
	      
	        str.append(JsonEffDtl);
	        
	        str.append("}");
	        return str.toString();
	    }
	    
	    public BdmTlWhywhymst getJson(String json) {
	        JSONObject jsonObj = new JSONObject(json);
	        JSONObject mstObj = jsonObj.getJSONObject("master");
	        JSONArray dtlArray = jsonObj.optJSONArray("details");
	        JSONArray doneByArray = jsonObj.optJSONArray("doneByList");
	        JSONArray problemAttByArray = jsonObj.optJSONArray("problemAttendedByList");
	        
	        BdmTlWhywhymst mst = BdmTlWhywhymst.fromJson(mstObj.toString());
	      
	        
	        
	        List<BdmTlWhywhydtl> dtl = new ArrayList<>();
	        if (dtlArray != null && !dtlArray.isEmpty()) {
	            dtl = BdmTlWhywhydtl.fromJsonList(dtlArray.toString());
	        }
	        
	        List<BdmTlYydonebymst> doneBy = new ArrayList<>();
	        if (doneByArray != null && !doneByArray.isEmpty()) {
	            doneBy = BdmTlYydonebymst.fromJsonList(doneByArray.toString());
	        }
	        
	        List<BdmTlYyproblemattbymst> problemAttBy = new ArrayList<>();
	        if (problemAttByArray != null && !problemAttByArray.isEmpty()) {
	            problemAttBy = BdmTlYyproblemattbymst.fromJsonList(problemAttByArray.toString());
	        }
	        
	        
	        mst.setBdmTlWhywhydtl(dtl);
	        mst.setBdmTlYydonebymst(doneBy);
	        mst.setBdmTlYyproblemattbymst(problemAttBy);
	        
	        return mst;
	    }
	    
	    public BdmTlYyeffectivemst getEffectivenessJson(String json) {
	        JSONObject jsonObj = new JSONObject(json);
	    

	        
	        // Handle effectiveMaster as an array
	        JSONArray effMstArray = jsonObj.optJSONArray("effectiveMaster");
	        JSONArray effDtlArray = jsonObj.optJSONArray("effectiveDetails");
	        
	        BdmTlYyeffectivemst effMst = null;
	        if (effMstArray != null && effMstArray.length() > 0) {
	            JSONObject effMstObj = effMstArray.getJSONObject(0);
	            effMst = BdmTlYyeffectivemst.fromJson(effMstObj.toString());
	            
	            // Set the list back to the object
	            List<BdmTlYyeffectivemst> mstList = new ArrayList<>();
	            mstList.add(effMst);
	            effMst.setBdmTlYyeffectivemst(mstList);
	        }
	        
	        List<BdmTlYyeffectivedtl> effDtl = new ArrayList<>();
	        if (effDtlArray != null && !effDtlArray.isEmpty()) {
	            effDtl = BdmTlYyeffectivedtl.fromJsonList(effDtlArray.toString());
	            
	        }
	        
	        if (effMst != null) {
	            effMst.setBdmTlYyeffectivedtl(effDtl);
	        }
	        
	        return effMst;
	    }
	    public BdmTlWhywhymst getCompleteWhyWhyData(String masterKeyid) throws IOException {
	        String apiUrl = "/whywhy/complete/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

	        CommonMessage.debugMsg(res.getBody() + "Body");
	        String json = res.getBody();
	        
	        BdmTlWhywhymst whywhymst = getJson(json);
	        return whywhymst;
	    }
	    
public BdmTlWhywhymst updateApprovalAI(BdmTlWhywhymst bdmTlWhywhymst) throws Exception {
	        
	        String apiUrl = "/whywhy/update-approvalAI";
	        
	        // Build JSON payload for approval update
	        String jsonPayload = updateApprovalAIJson(bdmTlWhywhymst);
	        
	        CommonMessage.debugMsg("Approval Update JSON :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse response
	        BdmTlWhywhymst updatedWhywhymst = BdmTlWhywhymst.fromJson(jsonResponse);
	        
	        CommonMessage.debugMsg("Updated WhyWhy Master: " + updatedWhywhymst);
	        
	        return updatedWhywhymst;
	    }

	    public BdmTlWhywhymst updateApproval(BdmTlWhywhymst bdmTlWhywhymst) throws Exception {
	        
	        String apiUrl = "/whywhy/update-approval";
	        
	        // Build JSON payload for approval update
	        String jsonPayload = updateApprovalJson(bdmTlWhywhymst);
	        
	        CommonMessage.debugMsg("Approval Update JSON :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse response
	        BdmTlWhywhymst updatedWhywhymst = BdmTlWhywhymst.fromJson(jsonResponse);
	        
	        CommonMessage.debugMsg("Updated WhyWhy Master: " + updatedWhywhymst);
	        
	        return updatedWhywhymst;
	    }
	    
	    
	    private String updateApprovalAIJson(BdmTlWhywhymst bdmTlWhywhymst) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("{");
	        
	        // Required field - keyid
	        sb.append("\"keyid\":");
	        sb.append("\"").append(bdmTlWhywhymst.getWwmsKeyid()).append("\"");
	        
	        // Approval Role ID
	        sb.append(",\"apprRoleid\":");
	        if (bdmTlWhywhymst.getWwmsApprRoleid() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsApprRoleid()).append("\"");
	        } else {
	            sb.append("\"\"");
	        }
	        
	        // Approved By
	        sb.append(",\"approvedBy\":");
	        if (bdmTlWhywhymst.getWwmsApprovedBy() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsApprovedBy()).append("\"");
	        } else {
	            sb.append("\"\"");
	        }
	        
	        // Approved On Date
	        sb.append(",\"approvedon\":");
	        if (bdmTlWhywhymst.getWwmsApprvedOn() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsApprvedOn()).append("\"");
	        } else {
	            sb.append("\"").append(CommonFunctions.getDate()).append("\"");
	        }
	        
	        // Approval Status
	        sb.append(",\"appStatus\":");
	        if (bdmTlWhywhymst.getWwmsAppStatus() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsAppStatus()).append("\"");
	        } else {
	        	sb.append("null");  
	        }
	        
	        // Approval Remarks
	        sb.append(",\"appRemarks\":");
	        if (bdmTlWhywhymst.getWwmsAppRemarks() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsAppRemarks()).append("\"");
	        } else {
	            sb.append("\"\"");
	        }
	        
	        sb.append(",\"iscobd\":");
	        if (bdmTlWhywhymst.getWwmsIscobd() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsIscobd()).append("\"");
	        } else {
	            sb.append("\"\"");
	        }
	        
	        sb.append(",\"cobdvalue\":");
	        if (bdmTlWhywhymst.getWwmsCobdvalue() != null) {
	            sb.append(bdmTlWhywhymst.getWwmsCobdvalue());
	        } else {
	            sb.append(0);
	        }
	        
	        sb.append(",\"cobdhours\":");
	        if (bdmTlWhywhymst.getWwmsCobdhours() != null) {
	            sb.append(bdmTlWhywhymst.getWwmsCobdhours());
	        } else {
	            sb.append(0);
	        }
	        
	        sb.append("}");
	        
	        return sb.toString();
	    }

	    private String updateApprovalJson(BdmTlWhywhymst bdmTlWhywhymst) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("{");
	        
	        // Required field - keyid
	        sb.append("\"keyid\":");
	        sb.append("\"").append(bdmTlWhywhymst.getWwmsKeyid()).append("\"");
	        
	        // Approval Role ID
	        sb.append(",\"apprRoleid\":");
	        if (bdmTlWhywhymst.getWwmsApprRoleid() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsApprRoleid()).append("\"");
	        } else {
	            sb.append("\"\"");
	        }
	        
	        // Approved By
	        sb.append(",\"approvedBy\":");
	        if (bdmTlWhywhymst.getWwmsApprovedBy() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsApprovedBy()).append("\"");
	        } else {
	            sb.append("\"\"");
	        }
	        
	        // Approved On Date
	        sb.append(",\"approvedon\":");
	        if (bdmTlWhywhymst.getWwmsApprvedOn() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsApprvedOn()).append("\"");
	        } else {
	            sb.append("\"").append(CommonFunctions.getDate()).append("\"");
	        }
	        
	        // Approval Status
	        sb.append(",\"appStatus\":");
	        if (bdmTlWhywhymst.getWwmsAppStatus() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsAppStatus()).append("\"");
	        } else {
	        	sb.append("null");  
	        }
	        
	        // Approval Remarks
	        sb.append(",\"appRemarks\":");
	        if (bdmTlWhywhymst.getWwmsAppRemarks() != null) {
	            sb.append("\"").append(bdmTlWhywhymst.getWwmsAppRemarks()).append("\"");
	        } else {
	            sb.append("\"\"");
	        }
	        
	        sb.append("}");
	        
	        return sb.toString();
	    }
	    public List<String[]> getSpentTime(String keyId) throws Exception {
	        String apiUrl = "/whywhy/spent-time/" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
	        
	        CommonMessage.debugMsg("Fetching spent time for keyId: " + keyId);
	        
	        // Send GET request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	        
	        if (jsonArray == null || jsonArray.isEmpty()) {
	            CommonMessage.debugMsg("No spent time data found for keyId: " + keyId);
	            return new ArrayList<>();
	        }
	        
	        // Convert JSON array to List of String arrays
	        List<String[]> dataList = convertSpentTimeJsonToList(jsonArray);
	        
	        CommonMessage.debugMsg("Spent time data retrieved successfully. Rows: " + dataList.size());
	        
	        return dataList;
	    }

	    /**
	     * Convert spent time JSON array to List of String arrays
	     * Expected JSON format: [{"problem": "...", "area": "...", "timespent": "..."}]
	     */
	    private List<String[]> convertSpentTimeJsonToList(JSONArray jsonArray) {
	        List<String[]> list = new ArrayList<>();
	        
	        // Define the expected column order for spent time data
	        String[] headers = {"problem", "area", "timespent"};
	        
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject obj = jsonArray.getJSONObject(i);
	            String[] row = new String[headers.length];
	            
	            for (int j = 0; j < headers.length; j++) {
	                Object value = obj.opt(headers[j]);
	                row[j] = (value == null || "null".equals(value.toString())) ? "" : value.toString();
	            }
	            
	            list.add(row);
	            CommonMessage.debugMsg("Row " + i + ": " + Arrays.toString(row));
	        }
	        
	        return list;
	    }
	    
	    
	    
	    /**
	     * Get YY Done By list for a master keyid
	     */
	    public List<String[]> getYyDoneby(String masterKeyid) throws Exception {
	        
	    	String apiUrl = "/whywhy/yy-doneby?masterKeyid="+ URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);

	        
	        CommonMessage.debugMsg("Fetching YY Done By for masterKeyid: " + masterKeyid);
	        
	        // Send GET request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	        
	        if (jsonArray == null || jsonArray.isEmpty()) {
	            CommonMessage.debugMsg("No YY Done By data found for masterKeyid: " + masterKeyid);
	            return new ArrayList<>();
	        }
	        
	        // Convert JSON array to List of String arrays with column order
	        List<String[]> dataList = convertJsonArrayToListwithcolumnorder(jsonArray);
	        
	        CommonMessage.debugMsg("YY Done By data retrieved successfully. Rows: " + dataList.size());
	        
	        return dataList;
	    }

	    /**
	     * Get Problem Attended By list for a master keyid
	     */
	    public List<String[]> getProbAttby(String masterKeyid) throws Exception 
	    {
	        String apiUrl = "/whywhy/prob-attby?masterKeyid=" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);
	        
	       
	        
	        CommonMessage.debugMsg("Fetching Problem Attended By for masterKeyid: " + masterKeyid);
	        
	        // Send GET request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	        
	        if (jsonArray == null || jsonArray.isEmpty()) {
	            CommonMessage.debugMsg("No Problem Attended By data found for masterKeyid: " + masterKeyid);
	            return new ArrayList<>();
	        }
	        
	        // Convert JSON array to List of String arrays with column order
	        List<String[]> dataList = convertJsonArrayToListwithcolumnorder(jsonArray);
	        
	        CommonMessage.debugMsg("Problem Attended By data retrieved successfully. Rows: " + dataList.size());
	        
	        return dataList;
	    }

	    /**
	     * Convert YY Done By JSON array to List of String arrays
	     * Expected JSON format: [{"keyid": "...", "wwmsKeyid": "...", "employee": "..."}]
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
		    String[] headerRow = new String[orderList.size()];
		    int hColIndex = 0;
		    for (Map.Entry<String, Integer> entry : orderList) {
	            String key = entry.getKey();
	            headerRow[hColIndex++] = key != null ? key.toString() : "";
	        }
		    
		    list.add(headerRow);

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
	    private List<String[]> convertYyDonebyJsonToList(JSONArray jsonArray) {
	        List<String[]> list = new ArrayList<>();
	        
	        if (jsonArray.length() < 1) {
	            return list;
	        }
	        
	        // Get the first object to determine column order
	        JSONObject firstObj = jsonArray.getJSONObject(0);
	        
	        // Build ordered list of keys
	        List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
	        Iterator<String> it = firstObj.keys();
	        int index = 0;
	        
	        // Define the expected order: keyid, wwmsKeyid, employee
	        String[] expectedOrder = {"keyid", "wwmsKeyid", "employee"};
	        
	        for (String key : expectedOrder) {
	            if (firstObj.has(key)) {
	                orderList.add(new AbstractMap.SimpleEntry<>(key, index++));
	            }
	        }
	        
	        // Add any additional keys not in expected order
	        it = firstObj.keys();
	        while (it.hasNext()) {
	            String key = it.next();
	            boolean found = false;
	            for (String expectedKey : expectedOrder) {
	                if (expectedKey.equals(key)) {
	                    found = true;
	                    break;
	                }
	            }
	            if (!found) {
	                orderList.add(new AbstractMap.SimpleEntry<>(key, index++));
	            }
	        }
	        
	        // Sort by order
	        orderList.sort(Comparator.comparingInt(Map.Entry::getValue));
	        
	        // Process each row
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject obj = jsonArray.getJSONObject(i);
	            String[] row = new String[orderList.size()];
	            
	            int colIndex = 0;
	            for (Map.Entry<String, Integer> entry : orderList) {
	                String key = entry.getKey();
	                Object value = obj.opt(key);
	                row[colIndex++] = (value == null || "null".equals(value.toString())) ? "" : value.toString();
	            }
	            
	            list.add(row);
	            CommonMessage.debugMsg("YY Done By Row " + i + ": " + Arrays.toString(row));
	        }
	        
	        return list;
	    }

	    /**
	     * Convert Problem Attended By JSON array to List of String arrays
	     * Expected JSON format: [{"keyid": "...", "wwmsKeyid": "...", "employee": "..."}]
	     */
	    private List<String[]> convertProbAttbyJsonToList(JSONArray jsonArray) {
	        List<String[]> list = new ArrayList<>();
	        
	        if (jsonArray.length() < 1) {
	            return list;
	        }
	        
	        // Get the first object to determine column order
	        JSONObject firstObj = jsonArray.getJSONObject(0);
	        
	        // Build ordered list of keys
	        List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
	        Iterator<String> it = firstObj.keys();
	        int index = 0;
	        
	        // Define the expected order: keyid, wwmsKeyid, employee
	        String[] expectedOrder = {"keyid", "wwmsKeyid", "employee"};
	        
	        for (String key : expectedOrder) {
	            if (firstObj.has(key)) {
	                orderList.add(new AbstractMap.SimpleEntry<>(key, index++));
	            }
	        }
	        
	        // Add any additional keys not in expected order
	        it = firstObj.keys();
	        while (it.hasNext()) {
	            String key = it.next();
	            boolean found = false;
	            for (String expectedKey : expectedOrder) {
	                if (expectedKey.equals(key)) {
	                    found = true;
	                    break;
	                }
	            }
	            if (!found) {
	                orderList.add(new AbstractMap.SimpleEntry<>(key, index++));
	            }
	        }
	        
	        // Sort by order
	        orderList.sort(Comparator.comparingInt(Map.Entry::getValue));
	        
	        // Process each row
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject obj = jsonArray.getJSONObject(i);
	            String[] row = new String[orderList.size()];
	            
	            int colIndex = 0;
	            for (Map.Entry<String, Integer> entry : orderList) {
	                String key = entry.getKey();
	                Object value = obj.opt(key);
	                row[colIndex++] = (value == null || "null".equals(value.toString())) ? "" : value.toString();
	            }
	            
	            list.add(row);
	            CommonMessage.debugMsg("Problem Attended By Row " + i + ": " + Arrays.toString(row));
	        }
	        
	        return list;
	    }
	    
	    /**
	     * Get WhyWhy Done By list
	     */
	    
	    /**
	     * Create ProblemAttBy records - uses the save endpoint
	     * Builds a complete WhyWhy request with the problemAttBy data
	     */
	    public BdmTlYyproblemattbymst yyProbAttCreate(BdmTlYyproblemattbymst newBdmTlYyproblemattbymst) throws Exception {
	        String apiUrl = "/whywhy/save";
	        
	        // Get the master keyid from the problemAttBy object
	        String masterKeyid = newBdmTlYyproblemattbymst.getWwpaWwmsKeyid();
	        
	        if (masterKeyid == null || masterKeyid.trim().isEmpty()) {
	            throw new RuntimeException("Master keyid is required for ProblemAttBy creation");
	        }
	        
	        // Build JSON with master keyid and problemAttendedByList
	        StringBuilder str = new StringBuilder();
	        str.append("{");
	        
	        // Add master with keyid
	        str.append("\"master\":{");
	        str.append("\"keyid\":\"").append(masterKeyid).append("\"");
	        str.append("},");
	        
	        // Add empty details array
	        str.append("\"details\":[],");
	        
	        // Add empty doneByList array
	        str.append("\"doneByList\":[],");
	        
	        // Add problemAttendedByList with the new record
	        str.append("\"problemAttendedByList\":[");
	        str.append(newBdmTlYyproblemattbymst.toJsonManual());
	        str.append("]");
	        
	        str.append("}");
	        
	        String jsonPayload = str.toString();
	        CommonMessage.debugMsg("ProblemAttBy Create JSON :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse the response
	        JSONObject jsonObj = new JSONObject(jsonResponse);
	        JSONArray problemAttByArray = jsonObj.optJSONArray("problemAttendedByList");
	        
	        if (problemAttByArray != null && problemAttByArray.length() > 0) {
	            // Get the first (and should be only) problemAttBy record from response
	            JSONObject problemAttByObj = problemAttByArray.getJSONObject(0);
	            BdmTlYyproblemattbymst savedProblemAttBy = BdmTlYyproblemattbymst.fromJson(problemAttByObj.toString());
	            return savedProblemAttBy;
	        }
	        
	        throw new RuntimeException("Failed to create ProblemAttBy record");
	    }
	    /**
	     * Create DoneBy records - uses the save endpoint
	     * Builds a complete WhyWhy request with the doneBy data
	     */
	    public BdmTlYydonebymst yyDonebyCreate(BdmTlYydonebymst newBdmTlYydonebymst) throws Exception {
	        String apiUrl = "/whywhy/save";
	        
	        // Get the master keyid from the doneBy object
	        String masterKeyid = newBdmTlYydonebymst.getWwdbWwmsKeyid();
	        
	        if (masterKeyid == null || masterKeyid.trim().isEmpty()) {
	            throw new RuntimeException("Master keyid is required for DoneBy creation");
	        }
	        
	        // Build JSON with master keyid and doneByList
	        StringBuilder str = new StringBuilder();
	        str.append("{");
	        
	        // Add master with keyid
	        str.append("\"master\":{");
	        str.append("\"keyid\":\"").append(masterKeyid).append("\"");
	        str.append("},");
	        
	        // Add empty details array
	        str.append("\"details\":[],");
	        
	        // Add doneByList with the new record
	        str.append("\"doneByList\":[");
	        str.append(newBdmTlYydonebymst.toJsonManual());
	        str.append("],");
	        
	        // Add empty problemAttendedByList array
	        str.append("\"problemAttendedByList\":[]");
	        
	        str.append("}");
	        
	        String jsonPayload = str.toString();
	        CommonMessage.debugMsg("DoneBy Create JSON :: " + jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse the response
	        JSONObject jsonObj = new JSONObject(jsonResponse);
	        JSONArray doneByArray = jsonObj.optJSONArray("doneByList");
	        
	        if (doneByArray != null && doneByArray.length() > 0) {
	            // Get the first (and should be only) doneBy record from response
	            JSONObject doneByObj = doneByArray.getJSONObject(0);
	            BdmTlYydonebymst savedDoneBy = BdmTlYydonebymst.fromJson(doneByObj.toString());
	            return savedDoneBy;
	        }
	        
	        throw new RuntimeException("Failed to create DoneBy record");
	    }
	    /**
	     * Get analysis grid data for WhyWhy details
	     * Returns List<String[]> with column order maintained
	     */
	    public List<String[]> getAnalysis(String masdetkeyid) throws Exception {
	        String apiUrl = "/whywhy/analysis?masdetkeyid=" + URLEncoder.encode(masdetkeyid, StandardCharsets.UTF_8);
	        
	        	        
	        CommonMessage.debugMsg("Fetching analysis data for masdetkeyid: " + masdetkeyid);
	        
	        // Send GET request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	        
	        if (jsonArray == null || jsonArray.isEmpty()) {
	            CommonMessage.debugMsg("No analysis data found for masdetkeyid: " + masdetkeyid);
	            return new ArrayList<>();
	        }
	        
	        // Convert JSON array to List of String arrays with column order
	        List<String[]> dataList = convertJsonArrayToListwithcolumnorder(jsonArray);
	        
	        CommonMessage.debugMsg("Analysis data retrieved successfully. Rows: " + dataList.size());
	        
	        return dataList;
	    }

	    /**
	     * Convert analysis JSON array to List of String arrays
	     * First row contains column order mapping (1,2,3,4), subsequent rows contain actual data
	     */
	    private List<String[]> convertAnalysisJsonToList(JSONArray jsonArray) {
	        List<String[]> list = new ArrayList<>();
	        
	        if (jsonArray.length() < 1) {
	            return list;
	        }
	        
	        // Row 0 = column order mapping
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
	        
	        // Process each row (skip first row as it's the column order mapping)
	        for (int i = 0; i < jsonArray.length(); i++) {
	            if (i == 0) {
	                continue; // Skip header row
	            }
	            
	            JSONObject obj = jsonArray.getJSONObject(i);
	            String[] row = new String[orderList.size()];
	            
	            int colIndex = 0;
	            for (Map.Entry<String, Integer> entry : orderList) {
	                String key = entry.getKey();
	                Object value = obj.opt(key);
	                row[colIndex++] = (value == null || "null".equals(value.toString())) ? "" : value.toString();
	            }
	            
	            list.add(row);
	            CommonMessage.debugMsg("Analysis Row " + (i - 1) + ": " + Arrays.toString(row));
	        }
	        
	        return list;
	    }
	    
	    
	    
	    /**
	     * Delete WhyWhy detail by keyid
	     * @param detailId - the keyid of the detail to delete
	     * @return true if deletion was successful
	     * @throws Exception if deletion fails
	     */
	    /**
	     * Delete WhyWhy detail by keyid
	     * @param detailId - the keyid of the detail to delete
	     * @return BdmTlWhywhydtl object with the deleted keyid
	     * @throws Exception if deletion fails
	     */
	    public BdmTlWhywhydtl deleteWhyWhyDetail(String detailId) throws Exception {
	        String apiUrl = "/whywhy/delete-detail/" + URLEncoder.encode(detailId, StandardCharsets.UTF_8);
	        
	        CommonMessage.debugMsg("Deleting WhyWhy detail with keyid: " + detailId);
	        
	        // Send DELETE request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
	        
	        boolean success = jsonObj.optBoolean("success", false);
	        String message = jsonObj.optString("message", "");
	        String deletedId = jsonObj.optString("deletedId", "");
	        
	        if (success) {
	            CommonMessage.debugMsg("WhyWhy detail deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
	            
	            // Create and return a BdmTlWhywhydtl object with the deleted keyid
	            BdmTlWhywhydtl deletedDetail = new BdmTlWhywhydtl();
	            deletedDetail.setWwdtKeyid(deletedId);
	            
	            return deletedDetail;
	        } else {
	            System.err.println("Failed to delete WhyWhy detail. Message: " + message);
	            throw new RuntimeException("Failed to delete WhyWhy detail: " + message);
	        }
	    }
	    
	    /**
	     * Delete Problem Attended By record by keyid
	     * @param keyId - the keyid of the problem attended by record to delete
	     * @return String - the deleted keyid
	     * @throws Exception if deletion fails
	     */
	    public String deleteYYProbAttBy(String keyId) throws Exception {
	        String apiUrl = "/whywhy/delete-prob-attby/" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
	        
	        CommonMessage.debugMsg("Deleting Problem Attended By record with keyid: " + keyId);
	        
	        // Send DELETE request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
	        
	        boolean success = jsonObj.optBoolean("success", false);
	        String message = jsonObj.optString("message", "");
	        String deletedId = jsonObj.optString("deletedId", "");
	        
	        if (success) {
	            CommonMessage.debugMsg("Problem Attended By record deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
	            return deletedId;
	        } else {
	            System.err.println("Failed to delete Problem Attended By record. Message: " + message);
	            throw new RuntimeException("Failed to delete Problem Attended By record: " + message);
	        }
	    }
	    
	    
	    /**
	     * Delete YY Done By record by keyid
	     * @param keyId - the keyid of the YY Done By record to delete
	     * @return String - the deleted keyid
	     * @throws Exception if deletion fails
	     */
	    public String deleteYYDoneBy(String keyId) throws Exception {
	        String apiUrl = "/whywhy/delete-yy-doneby/" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
	        
	        CommonMessage.debugMsg("Deleting YY Done By record with keyid: " + keyId);
	        
	        // Send DELETE request
	        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
	        
	        boolean success = jsonObj.optBoolean("success", false);
	        String message = jsonObj.optString("message", "");
	        String deletedId = jsonObj.optString("deletedId", "");
	        
	        if (success) {
	            CommonMessage.debugMsg("YY Done By record deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
	            return deletedId;
	        } else {
	            System.err.println("Failed to delete YY Done By record. Message: " + message);
	            throw new RuntimeException("Failed to delete YY Done By record: " + message);
	        }
	    }
//	public GenTlMommst insertAttendance(GenTlMommst genTlMommst) throws Exception {
//        
//		String apiUrl = "/mom"; // The Spring Boot API endpoint for insert
//		genTlMommst.setMomsActive("Y");
//        
//		List<GenTlMomattendance> attendances = genTlMommst.getMomeetinMomattendances();
//        // Convert object to JSON
//		
//		for(GenTlMomattendance attendance : attendances) 
//		{
//			String JsonPayload = attendance.toJsonManual();
//		}
//      
//        CommonMessage.debugMsg("Json :: "+jsonPayload);
//        
//        // Send request
//        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
//
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(res.getStatusCode());
////        int responseCode = res.getStatusCode();
//        
//        
//
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        //AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
//        GenTlMommst  mom = GenTlMommst.fromJson(jsonResponse);
//        
//        return mom;
//        
//
//}
//	public AbnTlAbnormality insertRecord(AbnTlAbnormality abnTlAbnormality) throws Exception {
//        String apiUrl = "http://localhost:9090/api/abnormality"; // The Spring Boot API endpoint for insert
//        HttpURLConnection connection = null;
//
//        URL url = new URL(apiUrl);
//        connection = (HttpURLConnection) url.openConnection();
//
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setDoOutput(true);
//        connection.setRequestProperty("Accept", "application/json");
//        abnTlAbnormality.setAbnmActive("Y");
//        
//        // Convert object to JSON
//     
//        String jsonPayload = abnTlAbnormality.toJsonManual();
//        CommonMessage.debugMsg("Json :: "+jsonPayload);
//        
//        // Send request
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//            os.flush();
//        }
//
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(connection.getResponseCode());
//        int responseCode = connection.getResponseCode();
//        
//        StringBuilder response = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
//            String responseLine;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//        }
//
//        String jsonResponse = response.toString();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
//        
//        return abn;
//        
//
//}
	
	public AbnTlAbnormality updateAbnComp(AbnTlAbnormality abnTlAbnormality) throws Exception {
        String apiUrl = "/abnormality/update"; // The Spring Boot API endpoint for insert
        
//        String dateTime = CommonFunctions.pg_dateTimeNow();
		String currentDate = CommonFunctions.pg_getDate();
		CommonMessage.debugMsg("Inside fill Valueslgfkjgfj");

		CommonMessage.debugMsg("fill values:");
		
		String jsonPayload1 = compJsonManual(abnTlAbnormality);
        CommonMessage.debugMsg("Json1 :: "+jsonPayload1);
		if( abnTlAbnormality.getAbnmWoendtime() == null )
			abnTlAbnormality.setAbnmWoendtime(currentDate);
		else
		{
			abnTlAbnormality.setAbnmWoendtime(CommonFunctions.pg_getDate(abnTlAbnormality.getAbnmWoendtime()));
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
	
//	public AbnTlAbnormality updateAbnComp(AbnTlAbnormality abnTlAbnormality) throws Exception {
//        String apiUrl = "http://localhost:9090/api/abnormality/update"; // The Spring Boot API endpoint for insert
//        HttpURLConnection connection = null;
////        String dateTime = CommonFunctions.pg_dateTimeNow();
//		String currentDate = CommonFunctions.pg_getDate();
//		CommonMessage.debugMsg("Inside fill Valueslgfkjgfj");
//
//		CommonMessage.debugMsg("fill values:");
//		
//		String jsonPayload1 = compJsonManual(abnTlAbnormality);
//        CommonMessage.debugMsg("Json1 :: "+jsonPayload1);
//		if( abnTlAbnormality.getAbnmWoendtime() == null )
//			abnTlAbnormality.setAbnmWoendtime(currentDate);
//		else
//		{
//			abnTlAbnormality.setAbnmWoendtime(CommonFunctions.pg_getDate(abnTlAbnormality.getAbnmWoendtime()));
//		}
//		String jsonPayload = compJsonManual(abnTlAbnormality);
//        CommonMessage.debugMsg("Json1 :: "+jsonPayload);
//
//        URL url = URI.create(apiUrl).toURL();
//        connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST"); 
//        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setDoOutput(true);
//        
//        
//        // Send request
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//            os.flush();
//        }
//
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(connection.getResponseCode());
//        int responseCode = connection.getResponseCode();
//        
//        StringBuilder response = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
//            String responseLine;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//        }
//
//        String jsonResponse = response.toString();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
//        
//        return abn;
//}
	
//	public List<String[]> callFunction(String functionName, List<String> paramValues) throws Exception
//	{
//		String apiUrl = "/abnormality/callFunction/"+functionName; // The Spring Boot API endpoint for insert
////        HttpURLConnection connection = null;
//        
//		String jsonPayload = fnJson(paramValues);
//        CommonMessage.debugMsg("Json :: "+jsonPayload);
//
//     // Send request
//        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
//
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(res.getStatusCode());
////        int responseCode = res.getStatusCode();
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        JSONObject obj = JSONObject.fromObject(jsonResponse);
//        
//        CommonMessage.debugMsg("Total Row Count :"+obj.get("totalcnt"));
//        CommonMessage.debugMsg("Total Row  :"+obj.get("curabn"));
//        JSONArray arr = obj.getJSONArray("curabn");
//        String[] headers = AbnormalityFunctionHeaders.getHeaders(functionName);
//        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr,headers);
//        CommonMessage.debugMsg("List of String Array  :"+datalist);
//               
//        return  datalist;
//	}
	
//	public List<String[]> callFunction(String functionName, List<String> paramValues) throws Exception
//	{
//		String apiUrl = "http://localhost:9090/api/abnormality/callFunction/"+functionName; // The Spring Boot API endpoint for insert
//        HttpURLConnection connection = null;
//        
//		String jsonPayload = fnJson(paramValues);
//        CommonMessage.debugMsg("Json :: "+jsonPayload);
//
//        URL url = URI.create(apiUrl).toURL();
//        connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST"); 
//        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setDoOutput(true);
//        
//        
//        // Send request
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//            os.flush();
//        }
//
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(connection.getResponseCode());
//        int responseCode = connection.getResponseCode();
//        
//        StringBuilder response = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
//            String responseLine;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//        }
//
//        String jsonResponse = response.toString();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        JSONObject obj = JSONObject.fromObject(jsonResponse);
//        
//        CommonMessage.debugMsg("Total Row Count :"+obj.get("totalcnt"));
//        CommonMessage.debugMsg("Total Row  :"+obj.get("curabn"));
//        JSONArray arr = obj.getJSONArray("curabn");
//        String[] headers = AbnormalityFunctionHeaders.getHeaders(functionName);
//        List<String[]> datalist = convertJsonArrayToListwithheadersorder(arr,headers);
//        CommonMessage.debugMsg("List of String Array  :"+datalist);
//        
//        
//        return  datalist;
//	}
	
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
	
	 public  List<String[]> convertJsonArrayToList(JSONArray jsonArray) {
	        List<String[]> list = new ArrayList<>();

	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject obj = jsonArray.getJSONObject(i);

	            // Get all keys (dynamic, no hardcoding)
	            Iterator<String> keys = obj.keys();
	            List<String> row = new ArrayList<>();

	            while (keys.hasNext()) {
	                String key = keys.next();
	                Object value = obj.opt(key);
	                row.add(value != null ? value.toString() : "");
	            }

	            list.add(row.toArray(new String[0]));
	        }

	        return list;
	    }
	 
	 public  List<String[]> convertJsonArrayToListwithheadersorder(JSONArray jsonArray, String[] headers) {
	        List<String[]> list = new ArrayList<>();

	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject obj = jsonArray.getJSONObject(i);

	            // Get all keys (dynamic, no hardcoding)
//	            Iterator<String> keys = obj.keys();
//	            List<String> row = new ArrayList<>();
//
//	            while (keys.hasNext()) {
//	                String key = keys.next();
//	                Object value = obj.opt(key);
//	                row.add(value != null ? value.toString() : "");
//	            }
//
//	            list.add(row.toArray(new String[0]));
	            String[] row = new String[headers.length];
	            for (int j = 0; j < headers.length; j++) {
	                Object val = obj.opt(headers[j]);
	                row[j] = (val == null || "null".equals(val.toString())) ? "" : val.toString();
	            }
	            list.add(row);
	        }
	        for (String[] row : list) {
	            CommonMessage.debugMsg(java.util.Arrays.toString(row));
	        }

	        return list;
	    }
	 
	 public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray, String[] headers) {
		    List<String[]> list = new ArrayList<>();

		    if (jsonArray.length() < 3) {
		        throw new IllegalArgumentException("JSON array must have at least 3 rows (config, header, column order).");
		    }

		    // Row 3 = column order mapping
		    JSONObject orderObj = jsonArray.getJSONObject(2);

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
//		    	if( i == 2) {
//		    		continue;
//		    	}
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
	 
	 /**
	  * Get counter measure data for a WhyWhy record
	  * Returns List<String[]> with column order maintained
	  */
	 public List<String[]> getCounterMeasure(CommonFilter commonFilter) throws Exception {
	     String yyno = commonFilter.getYyNo();
	     
	     // Validate yyno parameter
	     if (yyno == null || yyno.trim().isEmpty()) {
	         System.err.println("YY No is null or empty in CommonFilter");
	         System.err.println("CommonFilter details: " + commonFilter);
	         return new ArrayList<>();
	     }
	     
	     String apiUrl = "/whywhy/counter-measure?yyno=" + URLEncoder.encode(yyno, StandardCharsets.UTF_8);
	     
	     CommonMessage.debugMsg("Fetching counter measure data for yyno: " + yyno);
	     CommonMessage.debugMsg("API URL: " + apiUrl);
	     
	     // Send GET request
	     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	     
	     CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	     
	     String jsonResponse = res.getBody();
	     CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	     
	     // Handle non-200 responses gracefully
	     if (res.getStatusCode() != 200) {
	         System.err.println("Error Response (" + res.getStatusCode() + "): " + jsonResponse);
	         // Return empty list instead of throwing exception
	         return new ArrayList<>();
	     }
	     
	     // Validate JSON response
	     if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
	         CommonMessage.debugMsg("Empty response received for yyno: " + yyno);
	         return new ArrayList<>();
	     }
	     
	     // Check if it's a valid JSON array
	     String trimmedResponse = jsonResponse.trim();
	     if (!trimmedResponse.startsWith("[")) {
	         System.err.println("Invalid JSON response (not an array): " + jsonResponse);
	         return new ArrayList<>();
	     }
	     
	     // Parse JSON response
	     JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	     
	     if (jsonArray == null || jsonArray.isEmpty()) {
	         CommonMessage.debugMsg("No counter measure data found for yyno: " + yyno);
	         return new ArrayList<>();
	     }
	     
	     // Convert JSON array to List of String arrays with column order
	     List<String[]> dataList = convertJsonArrayToListwithcolumnorder(jsonArray);
	     
	     CommonMessage.debugMsg("Counter measure data retrieved successfully. Rows: " + dataList.size());
	     
	     return dataList;
	 }

//	 public  List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray, String[] headers) {
//	        List<String[]> list = new ArrayList<>();
//
//	        for (int i = 0; i < jsonArray.length(); i++) {
//	            JSONObject obj = jsonArray.getJSONObject(i);
//
//	            // Get all keys (dynamic, no hardcoding)
//	            Iterator<String> keys = obj.keys();
//	            List<String> row = new ArrayList<>();
//
//	            while (keys.hasNext()) {
//	                String key = keys.next();
//	                Object value = obj.opt(key);
//	                row.add(value != null ? value.toString() : "");
//	            }
//
//	            list.add(row.toArray(new String[0]));
//	            
//	        }
//	        for (String[] row : list) {
//	            CommonMessage.debugMsg(java.util.Arrays.toString(row));
//	        }
//
//	        return list;
//	    }
	 
	 
	 
	 /**
	  * Get Root Cause data by openMode
	  * @param openMode - the mode type (e.g., "BDM", "SAFETY", etc.)
	  * @return List<String[]> with root cause data
	  * @throws Exception if retrieval fails
	  */
	 public List<String[]> getRootCause(String openMode) throws Exception {
	     // Build URL with query parameter
	     StringBuilder apiUrlBuilder = new StringBuilder("/whywhy/root-cause");
	     
	     // Add openMode as query parameter if provided
	     if (openMode != null && !openMode.trim().isEmpty()) {
	         apiUrlBuilder.append("?openMode=")
	                      .append(URLEncoder.encode(openMode, StandardCharsets.UTF_8));
	     }
	     
	     String apiUrl = apiUrlBuilder.toString();
	     
	     CommonMessage.debugMsg("Fetching root cause data for openMode: " + openMode);
	     CommonMessage.debugMsg("API URL: " + apiUrl);
	     
	     // Send GET request
	     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	     
	     CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	     
	     String jsonResponse = res.getBody();
	     CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	     
	     // Handle non-200 responses gracefully
	     if (res.getStatusCode() != 200) {
	         System.err.println("Error Response (" + res.getStatusCode() + "): " + jsonResponse);
	         return new ArrayList<>();
	     }
	     
	     // Validate JSON response
	     if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
	         CommonMessage.debugMsg("Empty response received for openMode: " + openMode);
	         return new ArrayList<>();
	     }
	     
	     // Check if it's a valid JSON array
	     String trimmedResponse = jsonResponse.trim();
	     if (!trimmedResponse.startsWith("[")) {
	         System.err.println("Invalid JSON response (not an array): " + jsonResponse);
	         return new ArrayList<>();
	     }
	     
	     // Parse JSON response
	     JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	     
	     if (jsonArray == null || jsonArray.isEmpty()) {
	         CommonMessage.debugMsg("No root cause data found for openMode: " + openMode);
	         return new ArrayList<>();
	     }
	     
	     // Convert JSON array to List of String arrays
	     List<String[]> dataList = convertRootCauseJsonToList(jsonArray);
	     
	     CommonMessage.debugMsg("Root cause data retrieved successfully. Rows: " + dataList.size());
	     
	     return dataList;
	 }

	
	 private List<String[]> convertRootCauseJsonToList(JSONArray jsonArray) {
	     List<String[]> list = new ArrayList<>();
	     
	     // Expected columns from the query: wrcm_keyid, '', wrcm_name
	     String[] expectedColumns = {"wrcm_keyid", "column2", "wrcm_name"};
	     
	     for (int i = 0; i < jsonArray.length(); i++) {
	         JSONObject obj = jsonArray.getJSONObject(i);
	         String[] row = new String[3]; // Fixed 3 columns as per SQL query
	         
	         // Column 1: wrcm_keyid
	         Object keyid = obj.opt("wrcm_keyid");
	         row[0] = (keyid == null || "null".equals(keyid.toString())) ? "" : keyid.toString();
	         
	         // Column 2: Empty string (from SQL SELECT '')
	         row[1] = "";
	         
	         // Column 3: wrcm_name
	         Object name = obj.opt("wrcm_name");
	         row[2] = (name == null || "null".equals(name.toString())) ? "" : name.toString();
	         
	         list.add(row);
	         CommonMessage.debugMsg("Root Cause Row " + i + ": " + Arrays.toString(row));
	     }
	     
	     return list;
	 }
	 
	 public List<String[]> getCounterMeasureData(CommonFilter commonFilter) throws Exception {
		    String yyno = null;
		    
		    // Safely extract yyno from CommonFilter
		    if (commonFilter != null) {
		        yyno = commonFilter.getYyNo();
		    }
		    
		    // Validate yyno parameter
		    if (yyno == null || yyno.trim().isEmpty()) {
		        System.err.println("YY No is null or empty in CommonFilter");
		        if (commonFilter != null) {
		            System.err.println("CommonFilter details: " + commonFilter);
		        }
		        
		        // Return proper structure with 3 header rows to match expected format
		        return getEmptyCounterMeasureStructure();
		    }
		    
		    String apiUrl = "/whywhy/counter-measure?yyno=" + URLEncoder.encode(yyno, StandardCharsets.UTF_8);
		    
		    CommonMessage.debugMsg("Fetching counter measure data for yyno: " + yyno);
		    CommonMessage.debugMsg("API URL: " + apiUrl);
		    
		    try {
		        // Send GET request
		        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
		        
		        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
		        
		        String jsonResponse = res.getBody();
		        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
		        
		        // Handle non-200 responses gracefully
		        if (res.getStatusCode() != 200) {
		            System.err.println("Error Response (" + res.getStatusCode() + "): " + jsonResponse);
		            return getEmptyCounterMeasureStructure();
		        }
		        
		        // Validate JSON response
		        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
		            CommonMessage.debugMsg("Empty response received for yyno: " + yyno);
		            return getEmptyCounterMeasureStructure();
		        }
		        
		        // Check if it's a valid JSON array
		        String trimmedResponse = jsonResponse.trim();
		        if (!trimmedResponse.startsWith("[")) {
		            System.err.println("Invalid JSON response (not an array): " + jsonResponse);
		            return getEmptyCounterMeasureStructure();
		        }
		        
		        // Parse JSON response
		        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
		        
		        if (jsonArray == null || jsonArray.isEmpty()) {
		            CommonMessage.debugMsg("No counter measure data found for yyno: " + yyno);
		            return getEmptyCounterMeasureStructure();
		        }
		        
		        // Convert JSON array to List of String arrays with column order
		        List<String[]> dataList = convertJsonArrayToListwithcolumnorder(jsonArray);
		        
		        // Ensure we have at least 3 rows (header structure)
		        if (dataList == null || dataList.size() < 3) {
		            System.err.println("Invalid data structure returned from API. Expected at least 3 rows.");
		            return getEmptyCounterMeasureStructure();
		        }
		        
		        CommonMessage.debugMsg("Counter measure data retrieved successfully. Rows: " + dataList.size());
		        
		        return dataList;
		        
		    } catch (Exception e) {
		        System.err.println("Exception occurred while fetching counter measure data: " + e.getMessage());
		        e.printStackTrace();
		        return getEmptyCounterMeasureStructure();
		    }
		}

		/**
		 * Returns empty counter measure structure with proper header rows
		 * This matches the expected format from the SQL query which has 3 header rows
		 */
		private List<String[]> getEmptyCounterMeasureStructure() {
		    List<String[]> emptyResult = new ArrayList<>();
		    
		    // Row 0: Column keys (from SQL query columns)
		    emptyResult.add(new String[]{
		        "type", "keyid", "btn", "respon", "dte", "status", "theme", "dataorder"
		    });
		    
		    // Row 1: Display headers (what user sees)
		    emptyResult.add(new String[]{
		        "Proposed preventive counter measures given below", 
		        "", 
		        "", 
		        "Responsibility", 
		        "Date", 
		        "Status", 
		        "Theme", 
		        ""
		    });
		    
		    // Row 2: Column order/config (based on your convertJsonArrayToListwithcolumnorder pattern)
		    emptyResult.add(new String[]{
		        "0", "1", "2", "3", "4", "5", "6", "7"
		    });
		    
		    CommonMessage.debugMsg("Returning empty counter measure structure with " + emptyResult.size() + " header rows");
		    
		    return emptyResult;
		}
}
