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
import com.akranta.tpm.model.JhaTlAuditdtl;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlVisualsopdtl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class UpstreamdefectServiceApi {

    private final Api api;

    public UpstreamdefectServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
    
    public GenTlUpstreamdefectMst insertRecord(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,
			GenTlUpstreamdefectMst existGenTlUpstreamdefectMst, GenTlUpstreamdefectDet newGenTlUpstreamdefectDet,
			UpstreamDefect upstreamDefect)  throws Exception {
    	
    	CommonMessage.debugMsg(newGenTlUpstreamdefectDet.getUpsdRawmaterial());
    	CommonMessage.debugMsg(newGenTlUpstreamdefectDet.getUpsdPreventiveaction());
    	String apiUrl = "/upstreamdefect/createorupdate"; // The Spring Boot API endpoint for insert
    	newGenTlUpstreamdefectMst.setUpsmActive("Y");
        
        String jsonPayload = insertJson(newGenTlUpstreamdefectMst,newGenTlUpstreamdefectDet);
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        GenTlUpstreamdefectMst audit = getJson(jsonResponse);
        CommonMessage.debugMsg(audit);
        
        return audit;
	}
    
//    public String insertJson(GenTlUpstreamdefectMst genTlUpstreamdefectMst) {
//        StringBuilder str = new StringBuilder();
//        
//        str.append("{");
//        
//        // Master Details
//        str.append("\"genTlUpstreamdefectMst\":");
//        String jsonMst = genTlUpstreamdefectMst.toJsonManual();
//        str.append(jsonMst);
//        str.append(",");
//        
//        // Detail Table Values
//        GenTlUpstreamdefectDet details = genTlUpstreamdefectMst.getUpstreamdefect();
//        str.append("\"genTlUpstreamdefectDet\":");
//        String jsonDtl = GenTlUpstreamdefectDet.toJsonManual(details);
//        str.append(jsonDtl);
//        
//        str.append("}");
//        
//        return str.toString();
//    }
    
    
    
    public String insertJson(GenTlUpstreamdefectMst genTlUpstreamdefectMst,GenTlUpstreamdefectDet newGenTlUpstreamdefectDet) {
        StringBuilder str = new StringBuilder();

        str.append("{");

        // Master Details
        str.append("\"upstreamdefectmst\":");
        String jsonMst = genTlUpstreamdefectMst.toJsonManual();
        str.append(jsonMst);
        str.append(",");

        // Detail Table Values - Single object wrapped in array
        //GenTlUpstreamdefectDet details = genTlUpstreamdefectMst.getUpstreamdefect();
        str.append("\"upstreamdefectDet\":");
        
        // *** WRAP SINGLE OBJECT IN ARRAY to match Spring Boot expectation ***
        //str.append("[");  // Start array
        if (newGenTlUpstreamdefectDet != null) 
        {
            String jsonDtl = newGenTlUpstreamdefectDet.toJsonManual();
            str.append(jsonDtl);
        }
        //str.append("]");  // End array

        str.append("}");

        return str.toString();
    }
    
    public GenTlUpstreamdefectMst getJson(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        JSONObject mstObj = jsonObj.getJSONObject("upstreamdefectmst");
        JSONObject dtlObj = jsonObj.getJSONObject("upstreamdefectDet");
        
        GenTlUpstreamdefectMst mst = GenTlUpstreamdefectMst.fromJson(mstObj.toString());
        GenTlUpstreamdefectDet dtl = GenTlUpstreamdefectDet.fromJson(dtlObj.toString());
        
        mst.setUpstreamdefect(dtl);
        
        return mst;
    }
    
    
    
//    public Integer getElementId(String loginflid, double loginlevel, 
//            String loginElementid, String empId) throws Exception {
//
//	    if (loginflid == null ||  loginElementid == null || empId == null  ) {
//	        return 0;
//	    }
//
//	    StringBuilder apiUrl = new StringBuilder("/jhaudit/minimumpoints");
//	    apiUrl.append("?auditLevel=").append(auditLevel);
//	    apiUrl.append("&auditTemplate=").append(auditTemplate);
//
//	    CommonMessage.debugMsg("Min Points API URL: " + apiUrl);
//
//	    HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
//	    CommonMessage.debugMsg("Min Points Status Code: " + res.getStatusCode());
//
//	    String jsonResponse = res.getBody();
//	    CommonMessage.debugMsg("Min Points Response: " + jsonResponse);
//
//	    if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
//	        return 0;
//	    }
//
//	    try {
//	        return Integer.parseInt(jsonResponse.trim());
//	    } catch (NumberFormatException e) {
//	        System.err.println("Error parsing min points: " + e.getMessage());
//	        e.printStackTrace();
//	        return 0;
//	    }
//	}
    
    public List<String[]> getElementId(String loginflid, String loginlevel, 
            String loginElementid, String empId) throws Exception {

StringBuilder apiUrl = new StringBuilder("/upstreamdefect/getelementid");

// Build query parameters
apiUrl.append("?loginlevel=").append(loginlevel);
apiUrl.append("&empId=").append(empId);

if (loginflid != null && !loginflid.trim().isEmpty()) {
apiUrl.append("&loginflid=").append(loginflid);
}

if (loginElementid != null && !loginElementid.trim().isEmpty()) {
apiUrl.append("&loginElementid=").append(loginElementid);
}

CommonMessage.debugMsg("API URL: " + apiUrl.toString());

HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
CommonMessage.debugMsg("Status Code: " + res.getStatusCode());

String jsonResponse = res.getBody();
CommonMessage.debugMsg("JSON Response: " + jsonResponse);

// Parse JSON array response
net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(jsonResponse);

// Define headers for element data (5 columns based on SQL SELECT)
String[] headers = {
"fnlnElementid",    // FNLN_ELEMENTID
"fnlnKeyid",        // FNLN_KEYID
"roleLevel",        // ROLE_LEVEL
"roleName",         // ROLE_NAME
"roleKeyid"         // ROLE_KEYID
};

List<String[]> elementData = convertJsonArrayToList(jsonArray, headers);
CommonMessage.debugMsg("Element Data: " + elementData);

return elementData;
}

// ==================== Helper Methods ====================

/**
* Convert JSON array to List with specific header order
* Helper method for getElementId and similar methods
*/
private List<String[]> convertJsonArrayToList(net.sf.json.JSONArray jsonArray, String[] headers) {
List<String[]> list = new java.util.ArrayList<>();

for (int i = 0; i < jsonArray.length(); i++) {
net.sf.json.JSONObject obj = jsonArray.getJSONObject(i);

String[] row = new String[headers.length];
for (int j = 0; j < headers.length; j++) {
Object val = obj.opt(headers[j]);
row[j] = (val == null || "null".equals(val.toString())) ? "" : val.toString();
}
list.add(row);
}

return list;
}

//get master table by upsmkeyid

public GenTlUpstreamdefectMst getById(String FnlnId, String date,String keyId) throws IOException
{
	
	 String apiUrl = "/upstreamdefect/findmst/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
	          
	
    HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

    // Get the response code to confirm insert success, usually 201 Created
   CommonMessage.debugMsg(res.getBody()+"Body");
   String json = res.getBody();
   
   GenTlUpstreamdefectMst mst = GenTlUpstreamdefectMst.fromJson(json);
   
  
   return mst;
   
//    int responseCode = res.getStatusCode();
}


//fectching the details by dettail table
//
//public List<String[]> getByDetailId(String keyId) throws Exception {
//
//    String apiUrl = "/upstreamdefect/finddet/"
//            + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
//
//    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//
//    CommonMessage.debugMsg("Response Body: " + res.getBody());
//
//    String json = res.getBody();
//
//    // Convert JSON array → List<String[]>
//    JSONArray jsonArray = JSONArray.fromObject(json);
//
//    List<String[]> result = new ArrayList<>();
//    
//    
//
//    for (int i = 0; i < jsonArray.length(); i++) {
//
//        JSONArray row = jsonArray.getJSONArray(i);
//
//        String[] data = new String[row.length()];
//
//        for (int j = 0; j < row.length(); j++) {
//            data[j] = row.getString(j);
//        }
//
//        result.add(data);
//    }
//    return result;
//}


public List<String[]> getByDetailId(String keyId) throws Exception {

    String apiUrl = "/upstreamdefect/finddet/"
            + URLEncoder.encode(keyId, StandardCharsets.UTF_8);

    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

    String json = res.getBody();

   JSONArray jsonArray = new JSONArray(json);
   List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
    return result;
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


//deleting the records in master and detail by keyid
public GenTlUpstreamdefectMst deleteNewUpstreamDefect(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst) throws IOException
{
	
	String keyId = newGenTlUpstreamdefectMst.getUpsmKeyid();
	
	 String apiUrl = "/upstreamdefect/delete/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
	          
	
    HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

    // Get the response code to confirm insert success, usually 201 Created
   CommonMessage.debugMsg(res.getBody()+"Body");
   String json = res.getBody();
   
//   JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
   
  
   return newGenTlUpstreamdefectMst;
}

// delete the detail table record
public GenTlUpstreamdefectDet deleteNewUpstreamDefectDetails(GenTlUpstreamdefectDet newGenTlUpstreamdefectDet) throws IOException
{
	
	String keyId = newGenTlUpstreamdefectDet.getUpsdKeyid();
	
	 String apiUrl = "/upstreamdefect/deletedt/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
	          
	
    HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

    // Get the response code to confirm insert success, usually 201 Created
   CommonMessage.debugMsg(res.getBody()+"Body");
   String json = res.getBody();
   
//   JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
   
  
   return newGenTlUpstreamdefectDet;
}



}

//public List<String[]> getByDetailId(String keyId) throws IOException
//{
//	
//	 String apiUrl = "/upstreamdefect/finddet/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
//	          
//	
//    HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
//
//    // Get the response code to confirm insert success, usually 201 Created
//   CommonMessage.debugMsg(res.getBody()+"Body");
//   String json = res.getBody();
//   
//   GenTlUpstreamdefectMst mst = GenTlUpstreamdefectMst.fromJson(json);
//   
//  
//   return mst;
//   
////    int responseCode = res.getStatusCode();
//}

	

