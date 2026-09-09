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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.jackson.Log4jXmlObjectMapper;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ApplicationMaintananceServiceApi {
	private final Api api;
	
	public ApplicationMaintananceServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
//-------------------------------------------USER AND EMPLOYEE INACTIVE---------------------------------------------------//
	public List<String[]>  getEmployeeList(String keyId) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/getEmployeeList";
		
		if (keyId != null && !keyId.trim().isEmpty()) 
		{
		    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        
        CommonMessage.debugMsg(res.getStatusCode());
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONArray jsonArray = new JSONArray(jsonResponse);
        List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
        return result;
     }
	public void  updateUserEmployee(String keyId,String remarks) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/updateUserEmp";
		
		if (keyId != null && !keyId.trim().isEmpty()) {
		    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8)
		    		+ "&remarks=" + URLEncoder.encode(remarks, StandardCharsets.UTF_8);
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
     }

	//-------------------------------------------LOCATION TRANSFER---------------------------------------------------//	
	public List<String[]>  getEmployeeLocation(String keyId,String location) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/getEmpLocation";
		boolean hasParam = false;

		if (keyId != null && !keyId.trim().isEmpty()) {
		    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		    hasParam = true;
		}

		if (location != null && !location.trim().isEmpty()) {
		    apiUrl += (hasParam ? "&" : "?") +
		              "location=" + URLEncoder.encode(location, StandardCharsets.UTF_8);
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        
        CommonMessage.debugMsg(res.getStatusCode());
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONArray jsonArray = new JSONArray(jsonResponse);
        List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
        return result;
     }
	public Object  locationTransfer(String keyId,String location) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/locationTransfer";
		
		if (keyId != null && !keyId.trim().isEmpty()) {
		    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8)
		    		+ "&location=" + URLEncoder.encode(location, StandardCharsets.UTF_8);
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        return Collections.emptyMap();
     }
	
//---------------------------EMPLOYEE ACTIVATE AND USER ACTIVATE-----------------------------------------------------------//
	public void  activateEmpUser(String EmpKeyid,String ValidTill,String remarks) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/employeeActive";
		
		
		
		if (EmpKeyid != null && !EmpKeyid.trim().isEmpty()) {
		    apiUrl += "?keyId=" + URLEncoder.encode(EmpKeyid, StandardCharsets.UTF_8)
		    		+ "&ValidTill=" + URLEncoder.encode(ValidTill, StandardCharsets.UTF_8)
		    		+ "&remarks=" + URLEncoder.encode(remarks, StandardCharsets.UTF_8);
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
      }	
	
	
	//---------------------------ABNORMALITY DELETE-----------------------------------------------------------//
	
	public String  getFlid(String keyId) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/getFlid";
		
		
		
		if (keyId != null && !keyId.trim().isEmpty()) {
		    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		    		
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String json = res.getBody();
        return json;
      }	
	
	public void  deleteAbnormality(String keyId) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/deleteAbnormality";
		
		
		
		if (keyId != null && !keyId.trim().isEmpty()) {
		    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		    		
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String json = res.getBody();
        
      }	
	
	//---------------------------SUGGESSTION DELETE-----------------------------------------------------------//
	
	
	public void  deleteSuggestion(String keyId) throws Exception 
	{
        
		String apiUrl = "/appMaintenance/deleteSuggestion";
		
		
		
		if (keyId != null && !keyId.trim().isEmpty()) {
		    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		    		
		}
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String json = res.getBody();
        
      }	
	
	//---------------------------KAIZEN DELETE-----------------------------------------------------------//
	
	
		public void  deleteKaizen(String keyId) throws Exception 
		{
	        
			String apiUrl = "/appMaintenance/deleteKaizen";
			
			
			
			if (keyId != null && !keyId.trim().isEmpty()) {
			    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
			    		
			}
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
	        CommonMessage.debugMsg(res.getStatusCode());
	        
	        String json = res.getBody();
	        
	      }	
		
		//---------------------------ACTION PLAN DELETE-----------------------------------------------------------//
		
		
			public void  deleteActionPlan(String keyId) throws Exception 
			{
		        
				String apiUrl = "/appMaintenance/deleteActionPlan";
				
				
				
				if (keyId != null && !keyId.trim().isEmpty()) {
				    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
				    		
				}
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
		        CommonMessage.debugMsg(res.getStatusCode());
		        
		        String json = res.getBody();
		        
		      }	
			
			//---------------------------WHY WHY DELETE-----------------------------------------------------------//
			
			
			public void  deleteWhyWhy(String keyId) throws Exception 
			{
		        
				String apiUrl = "/appMaintenance/deleteWhyWhy";
				
				
				
				if (keyId != null && !keyId.trim().isEmpty()) {
				    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
				    		
				}
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
		        CommonMessage.debugMsg(res.getStatusCode());
		        
		        String json = res.getBody();
		        
		      }
			
			
	//---------------------------TRAINING CALENDAR DELETE-----------------------------------------------------------//
			
			
			public void  deleteTrgCalendar(String keyId) throws Exception 
			{
		        
				String apiUrl = "/appMaintenance/deleteTrgCalendar";
				
				
				
				if (keyId != null && !keyId.trim().isEmpty()) {
				    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
				    		
				}
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
		        CommonMessage.debugMsg(res.getStatusCode());
		        
		        String json = res.getBody();
		        
		      }
	//LOSS DELETE
			
			public void  deleteLoss(String keyId) throws Exception 
			{
		        
				String apiUrl = "/appMaintenance/deleteLossEntry";
				
				
				
				if (keyId != null && !keyId.trim().isEmpty()) {
				    apiUrl += "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
				    		
				}
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
		        CommonMessage.debugMsg(res.getStatusCode());
		        
		        String json = res.getBody();
		        
		      }
			
//---------------------------------------------ABNORMALITY CLOSURE----------------------------------------------------------------------------------//
			
//			public void abnormalityClosure(String AbnmKeyid,String Status,String CounterMeasure,String CompletedDate,String CompletedBy) throws Exception
//			{
//					String apiUrl = "/appMaintenance/abnClosure";
//					
//					CompletedDate = CommonFunctions.pg_getDateTimeFromDate(CompletedDate);
//				
//					String insertJson = compJsonAbn(AbnmKeyid, Status, CounterMeasure, CompletedDate, CompletedBy);
//				
//				
//			
//		        // Send request
//		        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", insertJson);
//		        CommonMessage.debugMsg(res.getStatusCode());
//		        
//		        String json = res.getBody();
//				
//			}
//			
//			
//			public String compJsonAbn(String AbnmKeyid,String Status,String CounterMeasure,String CompletedDate,String CompletedBy) {
//			    StringBuilder sb = new StringBuilder();
//			    
//			    sb.append("{");
//			    sb.append("\"abnmKeyid\":\"").append(AbnmKeyid).append("\"");
//			    sb.append(",\"status\":\"").append(Status).append("\"");
//			    sb.append(",\"counterMeasure\":\"").append(CounterMeasure).append("\"");
//			    sb.append(",\"completedDate\":\"").append(CompletedDate).append("\"");
//			    sb.append(",\"completedBy\":\"").append(CompletedBy).append("\"");
//			    sb.append("}");
//
//			    CommonMessage.debugMsg(sb.toString());
//			    return sb.toString();
//			}
			
			public void abnormalityClosure(List<AbnTlAbnormality> abnList) throws Exception {
			    String apiUrl = "/appMaintenance/abnClosure";

			    String insertJson = compJsonAbn(abnList);
			    CommonMessage.debugMsg("Insert Json "+insertJson);
			    

			    // Send request
			    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", insertJson);
			    CommonMessage.debugMsg(res.getStatusCode());

			    String json = res.getBody();
			}

			public String compJsonAbn(List<AbnTlAbnormality> abnList) {
			    StringBuilder sb = new StringBuilder();
			    sb.append("[");
			    for (int i = 0; i < abnList.size(); i++) {
			        AbnTlAbnormality abn = abnList.get(i);
			        if (i > 0) sb.append(",");

			        //String formattedDate = CommonFunctions.pg_getDateTimeFromDate(abn.getAbnmFeedbackdate()); // confirm correct field
			       // String formattedDate = CommonFunctions.pg_getDateTimeFromDate(abn.getAbnmFeedbackdate());
			        String formattedDate = CommonFunctions.pg_getDateTimeFromDate(abn.getAbnmWoendtime());

			        sb.append("{");
			        sb.append("\"abnmKeyid\":\"").append(abn.getAbnmKeyid()).append("\"");
			        sb.append(",\"status\":\"").append(abn.getAbnmStatus()).append("\"");
			        sb.append(",\"counterMeasure\":\"").append(abn.getAbnmCountermeasure()).append("\"");
			        sb.append(",\"completedDate\":\"").append(formattedDate).append("\"");
			        sb.append(",\"completedBy\":\"").append(abn.getAbnmCompletedby()).append("\"");
			        sb.append("}");
			    }
			    sb.append("]");

			    CommonMessage.debugMsg(sb.toString());
			    return sb.toString();
			}
//-----------------------------------------------ACTION PLAN CLOSURE-------------------------------------------------------------------//
			
			public void actionPlanClosure(String ActionPlanId,String DetailId,String Status,String CompletedOn,String CompletedBy,String CounterMeasure) throws Exception
			{
					String apiUrl = "/appMaintenance/actionPlanClosure";
					
					CompletedOn = CommonFunctions.pg_getDateTimeFromDate(CompletedOn);
				
					String insertJson = compJsonActionPlan(ActionPlanId,DetailId ,Status, CompletedOn, CompletedBy, CounterMeasure);
				
				
			
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", insertJson);
		        CommonMessage.debugMsg(res.getStatusCode());
		        
		        String json = res.getBody();
				
			}
			
			public String compJsonActionPlan(String ActionPlanId,String DetailId,String Status,String CompletedOn,String CompletedBy,String CounterMeasure) {
			    StringBuilder sb = new StringBuilder();
			    
			    sb.append("{");
			    sb.append("\"actionPlanId\":\"").append(ActionPlanId).append("\"");
			    sb.append(",\"detailId\":\"").append(DetailId).append("\"");
			    sb.append(",\"status\":\"").append(Status).append("\"");
			    sb.append(",\"completedOn\":\"").append(CompletedOn).append("\"");
			    sb.append(",\"completedBy\":\"").append(CompletedBy).append("\"");
			    sb.append(",\"counterMeasure\":\"").append(CounterMeasure).append("\"");
			    sb.append("}");

			    CommonMessage.debugMsg(sb.toString());
			    return sb.toString();
			}
//---------------------------------------------KAIZEN DATE CHANGE---------------------------//			
			public void  kaizenDateChange(String kznmKeyId,String KznmDate) throws Exception 
			{
		        
				String apiUrl = "/appMaintenance/kaizenDateChange";
				
				
				KznmDate = CommonFunctions.pg_getDateTimeFromDate(KznmDate);
				
				if (KznmDate != null && !KznmDate.trim().isEmpty()) {
				    apiUrl += "?keyId=" + URLEncoder.encode(kznmKeyId, StandardCharsets.UTF_8)
				    		+ "&date=" + URLEncoder.encode(KznmDate, StandardCharsets.UTF_8);
				}
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
		        CommonMessage.debugMsg(res.getStatusCode());
		      }	
			
			//---------------------------------------------FIP DATE CHANGE---------------------------//			
			public void  fipDateChange(String fipKeyId,String KznmDate) throws Exception 
			{
		        
				String apiUrl = "/appMaintenance/fipDateChange";
				
				
				KznmDate = CommonFunctions.pg_getDateTimeFromDate(KznmDate);
				
				if (KznmDate != null && !KznmDate.trim().isEmpty()) {
				    apiUrl += "?keyId=" + URLEncoder.encode(fipKeyId, StandardCharsets.UTF_8)
				    		+ "&date=" + URLEncoder.encode(KznmDate, StandardCharsets.UTF_8);
				}
		        // Send request
		        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
		        CommonMessage.debugMsg(res.getStatusCode());
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
		
		GenTlMomdtl dtl = new GenTlMomdtl();
		GenTlMomattendance att = new GenTlMomattendance();
		
		//Master Details
		str.append("{");
		
		str.append("\"genTlMommst\":");  
		String JsonMst = genTlMommst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		List<GenTlMomdtl> details = genTlMommst.getMomeetingDetail();
		str.append("\"genTlMomdtls\":");
		String JsonDtl = GenTlMomdtl.toJsonManualList(details);
		str.append(JsonDtl);
		str.append(",");
		
		List<GenTlMomattendance> attendances = genTlMommst.getMomeetinMomattendances();
		str.append("\"gentlMomAttendanceList\":");
		String Jsonatt = GenTlMomattendance.toJsonManualList(attendances);
		str.append(Jsonatt);
		
		str.append("}");
		
		return str.toString();
        
		
	}
	
	public GenTlMommst getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		JSONObject mstObj = jsonObj.getJSONObject("genTlMommst");
		JSONArray dtlArray = jsonObj.getJSONArray("genTlMomdtls");
		JSONArray attendanceArray = null;
		List<GenTlMomattendance> att = new ArrayList<>();
		
		if (jsonObj.has("gentlMomAttendanceList")
		        && !jsonObj.isNullObject()
		        && !jsonObj.get("gentlMomAttendanceList").equals(null)) {

			attendanceArray = jsonObj.getJSONArray("gentlMomAttendanceList");
		}
		
		GenTlMommst mst = GenTlMommst.fromJson(mstObj.toString());
		
		List<GenTlMomdtl> dtl = GenTlMomdtl.fromJsonList(dtlArray.toString());
		
		if(attendanceArray != null &&!attendanceArray.isEmpty()) {
				
			 att = GenTlMomattendance.fromJsonList(attendanceArray.toString());
		}
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
	
	public GenTlVisitors saveVisitor(GenTlVisitors genTlVisitors) throws IOException
	{
		//,KeyId,flid,momdate,shift,type,pillarid
		
		 String apiUrl = "/mom/saveVisitor";
		 
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
	
	
	public List<String[]> getMomGrid(String KeyId,String shift, String momdate,String flid, String type, String pillarid) throws IOException
	{
		//,KeyId,flid,momdate,shift,type,pillarid
		
		 String apiUrl = "/mom/recallMomGrid";
		 
		 String jsonPayload = compJsonMomGrid(KeyId, shift, momdate, flid, type, pillarid);
		 
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
	
	public List<String[]> momGridVisitor(String MasterKeyid, String shift, String date, String flid, String type,String pillarid,String recall) throws IOException
	{
		//,KeyId,flid,momdate,shift,type,pillarid
		
		 String apiUrl = "/mom/gridVisitor";
		 
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

	
	public String compJsonMomGrid(String KeyId,String shift, String momdate,String flid, String type, String pillarid) {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("{");
	    sb.append("\"keyId\":");
	    sb.append("\"").append(KeyId).append("\"");
	    sb.append(",\"shift\":");
	    sb.append("\"").append(shift).append("\"");
	    sb.append(",\"momdate\":");
	    sb.append(momdate);
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
	public List<String[]> fillagendadata(String date,String flid) throws IOException
	{
		CommonMessage.debugMsg("key id in serivce api "+date);
		 String apiUrl = "/mom/fillagendadata?momdate=" 
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
}
