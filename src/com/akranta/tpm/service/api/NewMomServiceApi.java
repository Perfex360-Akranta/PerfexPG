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

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class NewMomServiceApi {
	private final Api api;
	
	public NewMomServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	public GenTlMommst saveMOM(GenTlMommst genTlMommst) throws Exception {
        
		String apiUrl = "/simplifiedMom"; // The Spring Boot API endpoint for insert
		genTlMommst.setMomsActive("Y");
        
		String jsonPayload = insertJson(genTlMommst);
        
        
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        //AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
        //GenTlMommst  mom = GenTlMommst.fromJson(jsonResponse);
        GenTlMommst gemTlMommst = getJson(jsonResponse);
        CommonMessage.debugMsg(gemTlMommst);
        return gemTlMommst;
        

}
	
//public GenTlMommst insertAttendance(GenTlMommst genTlMommst) throws Exception {
//        
//		CommonMessage.debugMsg("Starting attendance save ***********************");
//		String apiUrl = "/mom/attendance"; // The Spring Boot API endpoint for insert
//		genTlMommst.setMomsActive("Y");
//		CommonMessage.debugMsg("Starting attendance save ***********************");
//		String jsonPayload = insertJson(genTlMommst);
//        
//        
//        CommonMessage.debugMsg("Json Attendance:: "+jsonPayload);
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
//        GenTlMommst gemTlMommst = getJson(jsonResponse);
//        CommonMessage.debugMsg(gemTlMommst);
//        
//        return gemTlMommst;
//        
//
//}


	public String insertJson(GenTlMommst genTlMommst) 
	{
	    StringBuilder str = new StringBuilder();

	    str.append("{");

	    // ================= MASTER =================
	    str.append("\"genTlMommst\":");  
	    String JsonMst = genTlMommst.toJsonManual();
	    str.append(JsonMst);
	    str.append(",");

	    // ================= DETAILS =================
	    List<GenTlMomdtl> details = genTlMommst.getMomeetingDetail();
	    str.append("\"genTlMomdtls\":");

	    if (details == null || details.isEmpty()) {
	        str.append("[]");
	    } else {
	        String JsonDtl = GenTlMomdtl.toJsonManualList(details);
	        str.append(JsonDtl);
	    }
	    str.append(",");

	    // ================= ATTENDANCE =================
	    List<GenTlMomattendance> attendances = genTlMommst.getMomeetinMomattendances();
	    str.append("\"gentlMomAttendanceList\":");

	    if (attendances == null || attendances.isEmpty()) {
	        str.append("[]");
	    } else {
	        String Jsonatt = GenTlMomattendance.toJsonManualList(attendances);
	        str.append(Jsonatt);
	    }
	    str.append(",");

	    // ================= KPI LINKS =================
	    List<GenTlMomKpiLink> kpiLinks = genTlMommst.getMomeetingKPI();
	    str.append("\"genTlMomKpiLinks\":");

	    if (kpiLinks == null || kpiLinks.isEmpty()) {
	        str.append("[]");
	    } else {
	        String Jsonkpi = GenTlMomKpiLink.toJsonManualList(kpiLinks);

	        for (GenTlMomKpiLink link : kpiLinks) {
	            if ("{}".equals(link.getMokpKinkKeyid())) {   // correct string compare
	                CommonMessage.debugMsg("entered");
	                Jsonkpi = "[]";
	                break;
	            }
	        }

	        str.append(Jsonkpi);
	    }

	    str.append("}");
	    return str.toString();
	}
	
	public GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newGenTlActionplandtl,GenTlMommst newGenTlMommst) throws Exception {
        
		String apiUrl = "/simplifiedMom/createActionPlan"; // The Spring Boot API endpoint for insert
		
		String jsonPayload = insertJsonActionPlan(newActionplanmst, newGenTlActionplandtl, newGenTlMommst);
        
        CommonMessage.debugMsg("Json Action Plan:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        //AbnTlAbnormality abn = AbnTlAbnormality.fromJson(jsonResponse);
        //GenTlMommst  mom = GenTlMommst.fromJson(jsonResponse);
        GenTlActionplanmst mst = GenTlActionplanmst.fromJson(jsonResponse);
        CommonMessage.debugMsg(mst);
        return mst;
        

}
	
	public String insertJsonActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newGenTlActionplandtl,GenTlMommst newGenTlMommst) 
	{
		StringBuilder str = new StringBuilder();
		
		
		//Master Details
		str.append("{");
		
		str.append("\"actionPlanMst\":");  
		String JsonMst = newActionplanmst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		str.append("\"actionPlanDtl\":");  
		String JsonDtl = newGenTlActionplandtl.toJsonManual();
		str.append(JsonDtl);
		str.append(",");
		
		//Detail Table Values
		List<GenTlActionplandtl> details = newGenTlActionplandtl.getActionplanlist();
		for(GenTlActionplandtl d:details) 
		{
			d.setApldTargetdate(CommonFunctions.pg_getDateTimeFromDate(d.getApldTargetdate()));
			d.setApldModifiedon(CommonFunctions.pg_dateTimeNow());
			d.setApldCreatedon(CommonFunctions.pg_dateTimeNow());
			d.setApldCompleatedon(CommonFunctions.pg_dateTimeNow());
			d.setApldTempfiled2("X");
			d.setApldTempfiled3("X");
			d.setApldTempfiled4("X");
			d.setApldTempfiled5("X");
			d.setApldActive("Y");
			d.setApldOthers("N");
			
		}
		str.append("\"actionPlanDtls\":");
		String JsonDtls = GenTlActionplandtl.toJsonManualList(details);
		str.append(JsonDtls);
		str.append(",");
		
		str.append("\"genTlMommst\":");  
		String JsonMomMst = newGenTlMommst.toJsonManual();
		str.append(JsonMomMst);
		

		
		
		str.append("}");
		
		return str.toString();
        
		
	}
	
	public GenTlActionplanmst updateActionPlan(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl,GenTlMommst newGenTlMommst,
			String Rowid,String ActionPlanId,String ActionplanDetailId) throws Exception {
        
		String apiUrl = "/simplifiedMom/updateActionPlan"; // The Spring Boot API endpoint for insert
		
		String jsonPayload = updateJsonActionPlan(genTlActionplanmst, genTlActionplandtl, newGenTlMommst, Rowid, ActionPlanId, ActionplanDetailId);
        
        CommonMessage.debugMsg("Json Action Plan:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        GenTlActionplanmst mst = GenTlActionplanmst.fromJson(jsonResponse);
        CommonMessage.debugMsg(mst);
        return mst;
        

}
	
	
	public String updateJsonActionPlan(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl,GenTlMommst newGenTlMommst,
			String Rowid,String ActionPlanId,String ActionplanDetailId) 
	{
		StringBuilder str = new StringBuilder();
		
		
		//Master Details
		str.append("{");
		
		str.append("\"actionPlanMst\":");  
		String JsonMst = genTlActionplanmst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		str.append("\"actionPlanDtl\":");  
		String JsonDtl = genTlActionplandtl.toJsonManual();
		str.append(JsonDtl);
		str.append(",");
		
		//Detail Table Values
		List<GenTlActionplandtl> details = genTlActionplandtl.getActionplanlist();
		for(GenTlActionplandtl d:details) 
		{
			d.setApldTargetdate(CommonFunctions.pg_getDateTimeFromDate(d.getApldTargetdate()));
			d.setApldModifiedon(CommonFunctions.pg_dateTimeNow());
			d.setApldCreatedon(CommonFunctions.pg_dateTimeNow());
			d.setApldCompleatedon(CommonFunctions.pg_dateTimeNow());
			d.setApldTempfiled2("X");
			d.setApldTempfiled3("X");
			d.setApldTempfiled4("X");
			d.setApldTempfiled5("X");
			d.setApldActive("Y");
			d.setApldOthers("N");
			
		}
		str.append("\"actionPlanDtls\":");
		String JsonDtls = GenTlActionplandtl.toJsonManualList(details);
		str.append(JsonDtls);
		str.append(",");
		
		str.append("\"genTlMommst\":");  
		String JsonMomMst = newGenTlMommst.toJsonManual();
		str.append(JsonMomMst);
		str.append(",");
		
		str.append("\"rowId\":");  
		str.append("\"").append(Rowid).append("\"");
		str.append(",");
		
		str.append("\"actionPlanDetailId\":");  
		str.append("\"").append(ActionplanDetailId).append("\"");
		str.append(",");
		
		str.append("\"actionPlanMstId\":");  
		str.append("\"").append(ActionPlanId).append("\"");

		

		
		
		str.append("}");
		
		return str.toString();
        
		
	}
	
	public GenTlMommst getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		JSONObject mstObj = jsonObj.getJSONObject("genTlMommst");
		JSONArray dtlArray = null;
		JSONArray kpiArray = null;
		JSONArray attendanceArray = null;
		List<GenTlMomattendance> att = new ArrayList<>();
		List<GenTlMomKpiLink> kpi = new ArrayList<>();
		List<GenTlMomdtl> dtl = new ArrayList<>();
		
		
		if (jsonObj.has("genTlMomdtls")
		        && !jsonObj.isNullObject()
		        && !jsonObj.get("genTlMomdtls").equals(null)) {

			dtlArray = jsonObj.getJSONArray("genTlMomdtls");
		}
		
		
		if (jsonObj.has("gentlMomAttendanceList")
		        && !jsonObj.isNullObject()
		        && !jsonObj.get("gentlMomAttendanceList").equals(null)) {

			attendanceArray = jsonObj.getJSONArray("gentlMomAttendanceList");
		}
		if (jsonObj.has("genTlMomKpiLinks")
		        && !jsonObj.isNullObject()
		        && !jsonObj.get("genTlMomKpiLinks").equals(null)) {

			kpiArray = jsonObj.getJSONArray("genTlMomKpiLinks");
		}
		GenTlMommst mst = GenTlMommst.fromJson(mstObj.toString());
		if(attendanceArray != null &&!attendanceArray.isEmpty()) {
				
			 att = GenTlMomattendance.fromJsonList(attendanceArray.toString());
		}
		if(kpiArray != null &&!kpiArray.isEmpty()) {
			
			kpi = GenTlMomKpiLink.fromJsonList(kpiArray.toString());
		}
		if(dtlArray != null &&!dtlArray.isEmpty()) {
			
			dtl = GenTlMomdtl.fromJsonList(dtlArray.toString());
		}
		mst.setMomeetingKPI(kpi);
		mst.setMomeetingDetail(dtl);
		mst.setMomeettingAttence(att);
		
		return mst;
		
	}
	
	public List<String[]> selectRecalling(String shift, String mstDate,String flid, String type, String pillarid) throws IOException
	{
		
		 String apiUrl = "/mom/recallMom"
		            + "?shift=" + URLEncoder.encode(shift, StandardCharsets.UTF_8)
		            + "&mstDate=" + URLEncoder.encode(mstDate, StandardCharsets.UTF_8)
		            + "&flid=" + URLEncoder.encode(flid, StandardCharsets.UTF_8)
		            + "&type=" + URLEncoder.encode(type, StandardCharsets.UTF_8)
		            + "&pillarid=" + URLEncoder.encode(pillarid, StandardCharsets.UTF_8);
		
		
        
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
	
	
	public GenTlMommst getMomById(String keyId) throws IOException
	{
		CommonMessage.debugMsg("key id in serivce api "+keyId);
		 String apiUrl = "/simplifiedMom/getById?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		
		
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       GenTlMommst mst = GenTlMommst.fromJson(json);
       return mst;
       
//        int responseCode = res.getStatusCode();
	}
	
	public GenTlVisitors saveVisitor(GenTlVisitors genTlVisitors) throws IOException
	{
		//,KeyId,flid,momdate,shift,type,pillarid
		
		 String apiUrl = "/simplifiedMom/saveVisitor";
		 
		 String jsonPayload = genTlVisitors.toJsonManual();
		 
		 //Send Request
		 HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       GenTlVisitors visitorResult = GenTlVisitors.fromJson(json);
       
       return visitorResult;
       

       
//        int responseCode = res.getStatusCode();
	}
	
	public List<String[]> getNewMomGrid(String KeyId,String shift, String momdate,String flid, String type,String pillarid) throws IOException
	{
		//,KeyId,flid,momdate,shift,type,pillarid
		
		 String apiUrl = "/simplifiedMom/newMomGrid";
		 
		 String jsonPayload = compJsonMomGrid(KeyId, shift, momdate, flid, type,pillarid);
		 
		 //Send Request
		 HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
	}
//	public List<String[]> getNewMomGrid(String KeyId,String shift, String momdate,String flid, String type) throws IOException
//	{
//		//,KeyId,flid,momdate,shift,type,pillarid
//		
//		 String apiUrl = "/simplifiedMom/newMomGrid";
//		 
//		 String jsonPayload = compJsonMomGrid(KeyId, shift, momdate, flid, type);
//		 
//		 //Send Request
//		 HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
//
//        // Get the response code to confirm insert success, usually 201 Created
//       CommonMessage.debugMsg(res.getBody()+"Body");
//       String json = res.getBody();
//       
//       
//       
//       JSONArray jsonArray = new JSONArray(json);
//       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
//       return result;
//       
////        int responseCode = res.getStatusCode();
//	}
	
	public String getFlid(String originalId) 
	 {
		 
			CommonMessage.debugMsg("key id in serivce api "+originalId);
			 String apiUrl = "/simplifiedMom/getFlid?originalId=" 
					 				+ URLEncoder.encode(originalId, StandardCharsets.UTF_8);
			
			
	        
	        // Send request
	        HttpResponse res = null;
			try {
				res = api.makeAuthRequest(apiUrl,"GET", null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Get the response code to confirm insert success, usually 201 Created
	       CommonMessage.debugMsg(res.getBody()+"Body");
	       String json = res.getBody();
	       
	       CommonMessage.debugMsg("JSON "+json);
	       
	       return json;
		 
		 
	 }
	
	public List<String[]> momGridVisitor(String MasterKeyid, String shift, String date, String flid, String type,String pillarid,String recall) throws IOException
	{
		//,KeyId,flid,momdate,shift,type,pillarid
		
		 String apiUrl = "/simplifiedMom/gridVisitor";
		 
		 String jsonPayload = compJsonMomGridVisitor(MasterKeyid, shift, date, flid, type, pillarid, recall);
		 
		 //Send Request
		 HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
       
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
	}
	
	public List<String[]> fillagendadata(String date,String flid) throws IOException
	{
		CommonMessage.debugMsg("key id in serivce api "+date);
		 String apiUrl = "/simplifiedMom/fillagendadata?momdate=" 
				 				+ URLEncoder.encode(date, StandardCharsets.UTF_8)
				 				 + "&flid=" + URLEncoder.encode(flid, StandardCharsets.UTF_8);
		
		
        
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
	
	public void DeleteNewMomRow(String keyId,String actionPlanMstid) throws IOException
	{
		CommonMessage.debugMsg("key id in serivce api "+actionPlanMstid);
		 String apiUrl = "/simplifiedMom/deleteNewRow?keyId=" 
				 				+ URLEncoder.encode(keyId, StandardCharsets.UTF_8)
				 				 + "&actionPlanMasterId=" + URLEncoder.encode(actionPlanMstid, StandardCharsets.UTF_8);
		
		
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       CommonMessage.debugMsg("JSON "+json);
       
//        int responseCode = res.getStatusCode();
	}
	
	public void DeleteVisitor(String keyId) throws IOException
	{
		CommonMessage.debugMsg("key id in serivce api "+keyId);
		 String apiUrl = "/simplifiedMom/deleteVisitor?keyId=" 
				 				+ URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		
		
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       CommonMessage.debugMsg("JSON "+json);
       
//        int responseCode = res.getStatusCode();
	}

	
//	public String compJsonMomGrid(String KeyId,String shift, String momdate,String flid, String type) {
//	    StringBuilder sb = new StringBuilder();
//	    
//	    sb.append("{");
//	    sb.append("\"keyId\":");
//	    sb.append("\"").append(KeyId).append("\"");
//	    sb.append(",\"shift\":");
//	    sb.append("\"").append(shift).append("\"");
//	    sb.append(",\"momDate\":");
//	    sb.append(momdate);
//	    sb.append(",\"flid\":");
//	    sb.append("\"").append(flid).append("\"");
//	    sb.append(",\"type\":");
//	    sb.append("\"").append(type).append("\"");
//	    sb.append("}");
//	    CommonMessage.debugMsg(sb.toString());
//	    return sb.toString();
//	}
	
	public String compJsonMomGrid(String KeyId,String shift, String momdate,String flid, String type,String pillarid) {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("{");
	    sb.append("\"keyId\":");
	    sb.append("\"").append(KeyId).append("\"");
	    sb.append(",\"shift\":");
	    sb.append("\"").append(shift).append("\"");
	    sb.append(",\"momDate\":");
	    sb.append("\"").append(momdate).append("\"");
	    //sb.append(momdate);
	    sb.append(",\"flid\":");
	    sb.append("\"").append(flid).append("\"");
	    sb.append(",\"type\":");
	    sb.append("\"").append(type).append("\"");
	    sb.append(",\"pillarid\":");
	    sb.append("\"").append(pillarid).append("\"");
	    sb.append("}");
	    CommonMessage.debugMsg(sb.toString());
	    return sb.toString();
	}
	
	public String compJsonMomGridVisitor(String masterKeyid, String shift, String date, String flid, String type,String pillarid,String recall) {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("{");
	    sb.append("\"masterKeyid\":");
	    sb.append("\"").append(masterKeyid).append("\"");
	    sb.append(",\"shift\":");
	    sb.append("\"").append(shift).append("\"");
	    sb.append(",\"date\":");
	    sb.append(date);
	    sb.append(",\"flid\":");
	    sb.append("\"").append(flid).append("\"");
	    sb.append(",\"type\":");
	    sb.append("\"").append(type).append("\"");
	    sb.append(",\"pillarid\":");
	    sb.append("\"").append(pillarid).append("\"");
	    sb.append(",\"recall\":");
	    sb.append("\"").append(recall).append("\"");
	    sb.append("}");
	    CommonMessage.debugMsg(sb.toString());
	    return sb.toString();
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
