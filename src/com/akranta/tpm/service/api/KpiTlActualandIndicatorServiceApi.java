package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.KpiTlActualKk;
import com.akranta.tpm.model.KpiTlIndicator;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.model.KpiTlKpiremarks;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class KpiTlActualandIndicatorServiceApi {
	
private final Api api;
    
    public KpiTlActualandIndicatorServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

    public KpiTlIndicator insertRecord(KpiTlIndicator kpiTlIndicator) throws Exception {
        String apiUrl = "/kpi/indicator"; // The Spring Boot API endpoint for insert
        //jhaTlAuditmst.setJhamActive("Y");	
        
        String jsonPayload = kpiTlIndicator.toJsonManual();
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
       // KpiTlIndicator indi = getJson(jsonResponse);
        KpiTlIndicator indicator = KpiTlIndicator.fromJson(jsonResponse);
        CommonMessage.debugMsg(indicator);
        
        return indicator;
    }
    
    
    public KpiTlIndicator updateRecord(KpiTlIndicator newKpiTlIndicator) throws Exception {
        String apiUrl = "/kpi/indicator/update"; // The Spring Boot API endpoint for insert
        //jhaTlAuditmst.setJhamActive("Y");	
        
        String jsonPayload = newKpiTlIndicator.toJsonManual();
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
       // KpiTlIndicator indi = getJson(jsonResponse);
        KpiTlIndicator indicator = KpiTlIndicator.fromJson(jsonResponse);
        CommonMessage.debugMsg(indicator);
        
        return indicator;
    }

 
    public List<KpiTlIndicatorKk> getAllkeyInds(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception {
        String apiUrl = "/kpi/indicator/getAllkeyInd"; 
        CommonMessage.debugMsg("Inside getAllkeyInds");
        
        // Convert object to JSON
        String jsonPayload = kpiTlIndicatorKk.toJsonManual();
        CommonMessage.debugMsg("getAllkeyInd Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        
        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
        
        return fillIndicatorList(datalist);
        

}
    
    
    public List<KpiTlIndicatorKk> getKpiTlIndicatorKkValues(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception {
        String apiUrl = "/kpi/indicator/getAllkeyIndkkvalue"; 
        CommonMessage.debugMsg("Inside getKpiTlIndicatorKkValues");
        // Convert object to JSON
        String jsonPayload = kpiTlIndicatorKk.toJsonManual();
        CommonMessage.debugMsg("getAllkeyInd Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        
        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
        
        return fillIndicatorList(datalist);
        

}
    
    
    
    private List<KpiTlIndicatorKk> fillIndicatorList(List<String []> resultList) throws SQLException
	{
	   List<KpiTlIndicatorKk> menus = new ArrayList<KpiTlIndicatorKk>();
	   for( String [] row : resultList )
	   {		
		   KpiTlIndicatorKk kpiTlIndicatorKk = new KpiTlIndicatorKk();
		   kpiTlIndicatorKk.setKinkKeyid(row[0]);
		   kpiTlIndicatorKk.setKinkIndicatorname(row[1]);	
		   kpiTlIndicatorKk.setKinkIndicatorcode(row[2]);	
		   kpiTlIndicatorKk.setKinkDescription(row[3]);	
		   kpiTlIndicatorKk.setKinkParentid(row[4]);	
		   kpiTlIndicatorKk.setKinkLevelno(row[5]);
		   kpiTlIndicatorKk.setKinkSortno(row[6]);	
		   kpiTlIndicatorKk.setKinkIschild(row[7]);	
		   kpiTlIndicatorKk.setKinkInputtype(row[8]);	
		   kpiTlIndicatorKk.setKinkInputentry(row[9]);		  
		   kpiTlIndicatorKk.setKinkIdentifier(row[10]);
		   kpiTlIndicatorKk.setKinkManualcalctype(row[11]);
		   kpiTlIndicatorKk.setKinkUomid(row[12]);
		   kpiTlIndicatorKk.setKinkFrequency(row[13]);
		   kpiTlIndicatorKk.setKinkExcelname(row[14]);	
		   kpiTlIndicatorKk.setKinkDeptKeyid(row[15]);	
		   kpiTlIndicatorKk.setKinkCostarea(row[16]);
		   kpiTlIndicatorKk.setKinkTargetneed(row[17]);
		   kpiTlIndicatorKk.setKinkPillarid(row[18]);
		   kpiTlIndicatorKk.setKinkTempfield3(row[19]);
		   menus.add(kpiTlIndicatorKk);			   
	   }	    
	   return menus;
	 }
    
    public String getSortNo(KpiTlIndicator kpiTlIndicator) throws Exception {
        
        CommonMessage.debugMsg("==== getSortNo ======");
        
        String apiUrl = "/kpi/indicator/getSortNo";
        
        CommonMessage.debugMsg("POST API URL: " + apiUrl);
        
        // Convert object to JSON
        String jsonPayload = kpiTlIndicator.toJsonManual();
        CommonMessage.debugMsg("getSortNo Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Check for error status codes
        if (statusCode >= 400) {
            String errorMsg = "API request failed with status " + statusCode;
            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                errorMsg += ": " + jsonResponse;
            }
            throw new RuntimeException(errorMsg);
        }
        
        // Return response as-is without trim
        String sortNo = jsonResponse;
        
        CommonMessage.debugMsg("Sort No: " + sortNo);
        return sortNo;
    }
    
    public String getPillarKeyId(String pillarCode)throws Exception   {
    	
  	  CommonMessage.debugMsg("==== PillarKeyId ======");
      
      String apiUrl = "/kpi/indicator/findbypillcode/" + pillarCode;
      

      CommonMessage.debugMsg("API URL: " + apiUrl);

      HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);

      int statusCode = res.getStatusCode();
      CommonMessage.debugMsg("Status Code: " + statusCode);

      String jsonResponse = res.getBody();
      CommonMessage.debugMsg("JSON Response: " + jsonResponse);

     
//
//      if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.equals("null")) {
//          CommonMessage.debugMsg("Empty response, returning null");
//          return null;
//      }

      String pillarCod = jsonResponse;
//      // Remove quotes if JSON string
//      if (pillarCod.startsWith("\"") && pillarCod.endsWith("\"")) {
//    	  pillarCod = pillarCod.substring(1, pillarCod.length() - 1);
//      }

     
      return pillarCod;
  }
  
    public String getLocation(String flId) throws Exception    {
    	
    	  CommonMessage.debugMsg("==== Location ======");
        
        String apiUrl = "/kpi/indicator/findbyflid/" + flId;                       

        CommonMessage.debugMsg("API URL: " + apiUrl);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);

        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

       

//        if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.equals("null")) {
//            CommonMessage.debugMsg("Empty response, returning null");
//            return null;
//        }

        String flid = jsonResponse;
//        // Remove quotes if JSON string
//        if (flid.startsWith("\"") && flid.endsWith("\"")) {
//        	flid = flid.substring(1, flid.length() - 1);
//        }

       
        return flid;
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
    
    
	
//    public String insertJson(KpiTlIndicator kpiTlIndicator) {
//        StringBuilder str = new StringBuilder();
//        
//        str.append("{");
//        
//        // Master Details
//        str.append("\"kpiTlIndicator\":");
//        String jsonMst = kpiTlIndicator.toJsonManual();
//        str.append(jsonMst);
//        
//        str.append("}");
//        
//        return str.toString();
//    }
//    
//    public KpiTlIndicator getJson(String json) {
//        JSONObject jsonObj = JSONObject.fromObject(json);
//        JSONObject mstObj = jsonObj.getJSONObject("kpiTlIndicator");
//        
//        KpiTlIndicator mst = KpiTlIndicator.fromJson(mstObj.toString());
//        
//        return mst;
//    }
    
    
    

    public KpiTlIndicatorKk getAllkeyInd(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception {

        String keyid = kpiTlIndicatorKk.getKinkKeyid();
        String apiUrl = "/kpi/indicator/native/" + keyid;

        CommonMessage.debugMsg("Fetching KPI Indicator with keyid: " + keyid);

        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        // Check for error status codes
        if (statusCode != 200 && statusCode != 201) {
            System.err.println("ERROR: API call failed with status code: " + statusCode);
            System.err.println("Response Body: " + jsonResponse);
            throw new Exception("API call failed with status " + statusCode + ": " + jsonResponse);
        }

        // Validate response is not empty
        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            throw new Exception("API returned empty response");
        }

        // Parse response (it's an Object[] from native query)
        KpiTlIndicatorKk indicator = parseNativeQueryResponse(jsonResponse);
        CommonMessage.debugMsg("Successfully fetched KPI Indicator: " + indicator.getKinkKeyid());

        return indicator;
    }

    private KpiTlIndicatorKk parseNativeQueryResponse(String json) {
        // Parse the JSON response
        JSONArray outerArray = JSONArray.fromObject(json);

        // Check if empty
        if (outerArray.isEmpty()) {
            return null;
        }

        // Get the inner array (the actual data)
        JSONArray dataArray = outerArray.getJSONArray(0);

        // Check if inner array is empty
        if (dataArray.isEmpty()) {
            return null;
        }

        KpiTlIndicatorKk indicator = new KpiTlIndicatorKk();

        // Map array positions to entity fields
        indicator.setKinkKeyid(getStringValue(dataArray, 0));
        indicator.setKinkParentid(getStringValue(dataArray, 1));
        indicator.setKinkIndicatorname(getStringValue(dataArray, 2));
        indicator.setKinkDescription(getStringValue(dataArray, 3));
        indicator.setKinkInputentry(getStringValue(dataArray, 4));
        indicator.setKinkLevelno(getStringValue(dataArray, 5));
        indicator.setKinkSortno(getStringValue(dataArray, 6));
        indicator.setKinkIschild(getStringValue(dataArray, 7));
        indicator.setKinkTargetneed(getStringValue(dataArray, 8));
        indicator.setKinkActive(getStringValue(dataArray, 9));
        indicator.setKinkCreatedby(getStringValue(dataArray, 10));
        indicator.setKinkCreatedon(getStringValue(dataArray, 11));
        indicator.setKinkModifiedon(getStringValue(dataArray, 12));

        return indicator;
    }

    // Helper method for safe value extraction
    private String getStringValue(JSONArray array, int index) {
        try {
            Object value = array.get(index);
            if (value == null || "null".equals(String.valueOf(value))) {
                return null;
            }
            return String.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }
    
    public KpiTlIndicatorKk delete(KpiTlIndicatorKk KpiTlIndicatorKk) throws IOException {
       
        String apiUrl = "/kpi/indicator/deletekpitlindicator/" + KpiTlIndicatorKk.getKinkKeyid();
        
        CommonMessage.debugMsg("Delete API URL: " + apiUrl);
        
        // Make DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Delete Status Code: " + statusCode);
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("Delete Response: " + jsonResponse);
        
        // Check for error status codes
        if (statusCode >= 400) {
            String errorMsg = "Delete failed with status " + statusCode;
            if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                errorMsg += ": " + jsonResponse;
            }
            throw new RuntimeException(errorMsg);
        }
        
        CommonMessage.debugMsg("✅ Successfully deleted kpi ");
		return KpiTlIndicatorKk;
    }
    
    
    
//    TARGET SETTING 22222222222
    
    
//    public KpiTlActualKk select(KpiTlActualKk kpiTlActualKk) throws IOException {
//        String apiUrl = "/kpi/indicator/selectbymodel";
//        
//        // Convert the KpiTlActualKk object to JSON string
//        String requestBody = kpiTlActualKk.toJsonManual(); // or use your JSON serialization method
//        
//        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", requestBody); // Changed to POST
//        
//        CommonMessage.debugMsg(res.getBody() + "Body");
//        String json = res.getBody();
//        
//        KpiTlActualKk act = KpiTlActualKk.fromJson(json);
//        
//        return act;
//    }
    
    
//    public KpiTlActualKk select(KpiTlActualKk kpiTlActualKk) throws IOException {
//        String apiUrl = "/kpi/indicator/selectbymodel";
//
//        // Convert the KpiTlActualKk object to JSON string
//        String requestBody = kpiTlActualKk.toJsonManual();
//
//        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", requestBody);
//
//        CommonMessage.debugMsg("Response Body: " + res.getBody());
//        String json = res.getBody();
//
//        KpiTlActualKk act = KpiTlActualKk.fromJson(json);
//
//        return act;
//    }
    
    public List<KpiTlActualKk> createordelete(List<KpiTlActualKk> kpiTlActualKk) throws Exception {
        String apiUrl = "/kpi/indicator/createordeletekpiactual"; 
        
        // Convert object to JSON
        String jsonPayload = KpiTlActualKk.toJsonManualList(kpiTlActualKk);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        List<KpiTlActualKk> abns = KpiTlActualKk.fromJsonList(jsonResponse);
        
        return abns;
        

}
    
    
//    public KpiTlKpiremarks selectReamrk(KpiTlKpiremarks kpiTlKpiremarks) throws IOException
//    {
//    	
//    	String keyId = kpiTlKpiremarks.getKprmKeyid();
//    	
//    	 String apiUrl = "/kpi/indicator/kpiremarks/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
//    	          
//    	
//        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
//
//        // Get the response code to confirm insert success, usually 201 Created
//       CommonMessage.debugMsg(res.getBody()+"Body");
//       String json = res.getBody();
//       
////       JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
//       
//      
//       return kpiTlKpiremarks;
//    }
    
    
    public List<KpiTlKpiremarks> createordeleteremarks(List<KpiTlKpiremarks> kpiTlKpiremarks) throws Exception {
        String apiUrl = "/kpi/indicator/createordeletekpiremarks"; 
        
        // Convert object to JSON
        String jsonPayload = KpiTlKpiremarks.toJsonManualList(kpiTlKpiremarks);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        List<KpiTlKpiremarks> rmk = KpiTlKpiremarks.fromJsonList(jsonResponse);
        
        return rmk;
        

}
    
    
    public String getKPIDeviationCount(String flid, String year, String currDate, String currMonthYear, String frequency) throws Exception {
    	
    	  CommonMessage.debugMsg("==== getKPIDeviationCount ======");
        
        String apiUrl = "/kpi/indicator/deviationcount?flid=" + flid 
                        + "&year=" + year 
                        + "&currDate=" + currDate 
                        + "&currMonthYear=" + currMonthYear 
                        + "&frequency=" + frequency;

        CommonMessage.debugMsg("API URL: " + apiUrl);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);

        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        if (statusCode >= 400) {
            String errorMsg = "API request failed with status " + statusCode;
            if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                errorMsg += ": " + jsonResponse;
            }
            throw new RuntimeException(errorMsg);
        }

        if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.equals("null")) {
            CommonMessage.debugMsg("Empty response, returning null");
            return null;
        }

        String deviationCount = jsonResponse.trim();
        // Remove quotes if JSON string
        if (deviationCount.startsWith("\"") && deviationCount.endsWith("\"")) {
            deviationCount = deviationCount.substring(1, deviationCount.length() - 1);
        }

        CommonMessage.debugMsg("KPI Deviation Count for flid '" + flid + "', year '" + year + "', frequency '" + frequency + "': " + deviationCount);
        return deviationCount;
    }
    
    
    
    
    public KpiTlIndicatorDeptLink createPillFactLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink, String usrm_ccno,String pillCode,String drillLevel,String indicatorId,String deptId,String isIndicatorFactory )throws Exception
    {
        String apiUrl = "/kpi/indicatordeptlink/create"; // The Spring Boot API endpoint for insert
        //jhaTlAuditmst.setJhamActive("Y");	
        
        
        String jsonPayload = compJsonManual(
                kpiTlIndicatorDeptLink, 
                usrm_ccno, 
                pillCode, 
                drillLevel, 
                indicatorId, 
                deptId, 
                isIndicatorFactory
            ); 
        //String jsonPayload = kpiTlIndicatorDeptLink.toJsonManual();
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
       // KpiTlIndicator indi = getJson(jsonResponse);
        KpiTlIndicatorDeptLink FactLink = kpiTlIndicatorDeptLink.fromJson(jsonResponse);
        CommonMessage.debugMsg(FactLink);
        
        return FactLink;
    }
    

	public String compJsonManual(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink, String usrm_ccno,String pillCode,String drillLevel,String indicatorId,String deptId,String isIndicatorFactory) {
	    StringBuilder sb = new StringBuilder();
	    List <KpiTlIndicatorDeptLink> methodslist = kpiTlIndicatorDeptLink.getmethodPillarFactlink();
	    sb.append("{");
	    sb.append("\"pillCode\":");
	    sb.append("\"").append(pillCode).append("\"");
	    sb.append(",\"indicatorId\":");
	    sb.append("\"").append(indicatorId).append("\"");
	    sb.append(",\"deptId\":");
	    sb.append("\"").append(deptId).append("\"");
	    sb.append(",\"isIndicatorFactory\":");
	    sb.append("\"").append(isIndicatorFactory).append("\"");
	    sb.append(",\"drillLevel\":");
	    sb.append("\"").append(drillLevel).append("\"");
	    sb.append(",\"NewIndicatorDeptLinkRequestDto\":");
	    //sb.append("\"").append(KpiTlIndicatorDeptLink.toJsonManualList(methodslist)).append("\"");
	    sb.append(KpiTlIndicatorDeptLink.toJsonManualList(methodslist));
	    sb.append("}");
	    return sb.toString();
	}
	
	
	public String validatekeyIndLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception {

	    
		
		
        String apiUrl = "/kpi/indicatordeptlink/validate";
        
        String dateTime = CommonFunctions.pg_dateTimeNow();
        kpiTlIndicatorDeptLink.setKidlInactivedate(dateTime);
        kpiTlIndicatorDeptLink.setKidlEffectivedate(dateTime);
        kpiTlIndicatorDeptLink.setKidlModifiedon(dateTime);
        kpiTlIndicatorDeptLink.setKidlCreatedon(dateTime);
        kpiTlIndicatorDeptLink.setKidlTempfield1("-");
        kpiTlIndicatorDeptLink.setKidlTempfield2("-");
        kpiTlIndicatorDeptLink.setKidlTempfield3("-");
        kpiTlIndicatorDeptLink.setKidlTempfield4("-");
        kpiTlIndicatorDeptLink.setKidlTempfield5("-");
        
        kpiTlIndicatorDeptLink.setKidlDepttype("-");      
        kpiTlIndicatorDeptLink.setKidlPillarid("-");      
        kpiTlIndicatorDeptLink.setKidlActive("-");             
        kpiTlIndicatorDeptLink.setKidlCreatedby("-");    

        
        String jsonPayload = kpiTlIndicatorDeptLink.toJsonManual();
        CommonMessage.debugMsg("Json :: "+jsonPayload);

        
       
        CommonMessage.debugMsg("POST API URL: " + apiUrl);
        CommonMessage.debugMsg("POST Payload: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        return jsonResponse;
    }
	
	
	
//	 public int getnewkeyIndLevel(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception {
//	        
//	        CommonMessage.debugMsg("==== getkeyIndLevel ======");
//	        
//	        String apiUrl = "/kpi/indicator/get-key-ind-level";	     
//	        
//	        String jsonPayload = kpiTlIndicatorKk.toJsonManual();
//	        CommonMessage.debugMsg("getkeyIndLevel Json :: " + jsonPayload);
//	        
//	        CommonMessage.debugMsg("POST API URL: " + apiUrl);
//	        CommonMessage.debugMsg("POST Payload: " + jsonPayload);
//	        
//	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//	        
//	        int statusCode = res.getStatusCode();
//	        CommonMessage.debugMsg("Status Code: " + statusCode);
//	        
//	        String jsonResponse = res.getBody();
//	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//	        
//	        // Check for error status codes
////	      
//	        // Parse the integer response
//	        int menuLevel = Integer.parseInt(jsonResponse.trim());
//	        
//	        CommonMessage.debugMsg("Menu Level: " + menuLevel);
//	        return menuLevel;
//	    }

	
	
	  public int getnewkeyIndLevellllll(String keyId) throws Exception {
	        
	        CommonMessage.debugMsg("==== getkeyIndLevel ======");
       
	        String apiUrl = "/kpi/indicator/get-key-ind-level?keyId=" + keyId;
	        	   
	        CommonMessage.debugMsg("GET API URL: " + apiUrl);
	      
	        
	        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	        
	        int statusCode = res.getStatusCode();
	        CommonMessage.debugMsg("Status Code: " + statusCode);
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse the integer response
	        int menuLevel = Integer.parseInt(jsonResponse.trim());
	        
	        CommonMessage.debugMsg("Menu Level: " + menuLevel);
	        return menuLevel;
	    }
	 
	
	 

	    
	    /**
	     * Gets the configured key indicator level from API
	     */
	    public int getConfigkeyIndLevel() throws Exception {
	        
	        CommonMessage.debugMsg("==== getConfigkeyIndLevel ======");
	        
	        String apiUrl = "/kpi/indicator/get-config-key-ind-level";
	        
	        CommonMessage.debugMsg("POST API URL: " + apiUrl);
	        
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);
	        
	        int statusCode = res.getStatusCode();
	        CommonMessage.debugMsg("Status Code: " + statusCode);
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	        // Parse the integer response
	        int configLevel = Integer.parseInt(jsonResponse.trim());
	        
	        CommonMessage.debugMsg("Config Level: " + configLevel);
	        return configLevel;
	    }
	    
	    
	    public String getEntProgStartMonth() throws Exception {
	        CommonMessage.debugMsg("==== getEntProgStartMonth ======");
	        
	        String apiUrl = "/kpi/indicator/StartMonth"; // or your actual endpoint
	        CommonMessage.debugMsg("POST API URL: " + apiUrl);
	        
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);
	        
	        int statusCode = res.getStatusCode();
	        CommonMessage.debugMsg("Status Code: " + statusCode);
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        String startMonth = jsonResponse;
	        
	        CommonMessage.debugMsg("Start Month: " + startMonth);
	        return startMonth;
	    }
	
	    
	    public KpiTlIndicator select(KpiTlIndicator kpiTlIndicator) throws IOException {
	        
	        String apiUrl = "/kpi/indicator/indiactorss" ;
	        CommonMessage.debugMsg("select API URL: " + apiUrl);
	        
	        String indicatorname = kpiTlIndicator.getKinkIndicatorname();
	        String parentid = kpiTlIndicator.getKinkParentid();
	        String keyid = kpiTlIndicator.getKinkKeyid();
	        
	        //String jsonPayload = kpiTlIndicator.toJsonManual();
	        String jsonPayload = newcompJsonManual(
	        		keyid, 
	        		indicatorname, 
	        		parentid	              
	            ); 
	
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        CommonMessage.debugMsg(" jsonPayload Status : " + jsonPayload);
	        int statusCode = res.getStatusCode();
	        CommonMessage.debugMsg(" Status Code: " + statusCode);
	        
	        String jsonResponse = res.getBody();
	        KpiTlIndicator result= kpiTlIndicator.fromJson(jsonResponse);
	        
	        
	        CommonMessage.debugMsg("✅ Successfully  kpi ");
			return result;
	    }
	    
	    
	    public String newcompJsonManual( String keyid,String indicatorname,String parentid) {
		    StringBuilder sb = new StringBuilder();
		   
		    sb.append("{");
		    sb.append("\"keyid\":");
		    sb.append("\"").append(keyid).append("\"");
		    if(UIUtils.isValidKeyId(indicatorname)) {
		    sb.append(",\"indicatorname\":");		    
		    sb.append("\"").append(indicatorname).append("\"");
		    }
		    if(UIUtils.isValidKeyId(parentid)) {
		    sb.append(",\"parentid\":");	
		    sb.append("\"").append(parentid).append("\"");
		    }
		    sb.append("}");
		    return sb.toString();
		}
	    
	    
	    
	    /**
	     * Get element ID by login details
	     */
	    public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) 
	            throws IOException {
	        
	        String apiUrl = "/kpi/indicator/getElementId";
	        CommonMessage.debugMsg("getElementId API URL: " + apiUrl);
	        
	        // Build JSON payload using helper method
	        String jsonPayload = buildElementIdJsonManual(loginflid, loginlevel, loginElementid, empId);
	        
	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        CommonMessage.debugMsg("jsonPayload: " + jsonPayload);
	        
	        int statusCode = res.getStatusCode();
	        CommonMessage.debugMsg("Status Code: " + statusCode);
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON array response
	        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	        
	        // Convert using column order method
	        List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
	        
	        CommonMessage.debugMsg("✅ Successfully retrieved element IDs");
	        return result;
	    }

	    /**
	     * Build JSON payload manually with conditional fields
	     */
	    public String buildElementIdJsonManual(String loginflid, String loginlevel, String loginElementid, String empId) {
	        StringBuilder sb = new StringBuilder();
	        
	        sb.append("{");
	        sb.append("\"empId\":");
	        sb.append("\"").append(empId).append("\"");
	        
	        if (UIUtils.isValidKeyId(loginflid)) {
	            sb.append(",\"loginflid\":");
	            sb.append("\"").append(loginflid).append("\"");
	        }
	        
	        if (UIUtils.isValidKeyId(loginlevel)) {
	            sb.append(",\"loginlevel\":");
	            sb.append("\"").append(loginlevel).append("\"");
	        }
	        
	        if (UIUtils.isValidKeyId(loginElementid)) {
	            sb.append(",\"loginElementid\":");
	            sb.append("\"").append(loginElementid).append("\"");
	        }
	        
	        sb.append("}");
	        return sb.toString();
	    }

	    /**
	     * Convert JSON array to List with column order
	     */
	   
	    
	    
    

}
