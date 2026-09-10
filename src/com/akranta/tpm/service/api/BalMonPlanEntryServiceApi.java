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

import com.akranta.tpm.bean.BalWorkOrderDetailsBean;
import com.akranta.tpm.bean.BalWorkOrderDetailsLstBean;
import com.akranta.tpm.bean.WorkOrderDetailsBean;
import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.BalPlmTlMultipleResp;
import com.akranta.tpm.model.BalPlmTlSpareconsumed;
import com.akranta.tpm.model.BalPlmTlWofeedback;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.HttpResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;

public class BalMonPlanEntryServiceApi {
	private final Api api;
	
	public BalMonPlanEntryServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public String saveWorkOrderDetailsGrid(
	        List<BalPlmTlWofeedback> newPlmTlWofeedbackList,
	        BalWorkOrderDetailsBean workOrderDetailsBean,
	        BalPlmTlWofeedback nwPlmTlWofeedback) {

	    String apiUrl = "/workOrder/saveWodGrd";

	    String saveJsonPayload = insertJson(
	            newPlmTlWofeedbackList,
	            workOrderDetailsBean,
	            nwPlmTlWofeedback
	    );

	    CommonMessage.debugMsg("JSON :: " + saveJsonPayload);

	    try {

	        // Send JSON to Spring Boot
	        HttpResponse res = api.makeAuthRequest(
	                apiUrl,
	                "POST",
	                saveJsonPayload
	        );

	        CommonMessage.debugMsg(
	                "Response Code :: " + res.getStatusCode()
	        );

	        String jsonResponse = res.getBody();

	        CommonMessage.debugMsg(
	                "JSON Response :: " + jsonResponse
	        );
	        
	        

	        return "Success";

	    } catch (IOException e) {

	        e.printStackTrace();

	        return "Failed";
	    }
	}
	
	public String updateCompletedWorkOrder(
	        String wowoDetailId,String completedBy	) {

	    String apiUrl = "/workOrder/modifyCompWo";
	    
	    String datetime = CommonFunctions.pg_dateTimeNow();
	    

	    String saveJsonPayload = inserJsonUpdate(
	    		wowoDetailId,
	    		completedBy,
	    		datetime
	    );

	    CommonMessage.debugMsg("JSON :: " + saveJsonPayload);

	    try {

	        // Send JSON to Spring Boot
	        HttpResponse res = api.makeAuthRequest(
	                apiUrl,
	                "POST",
	                saveJsonPayload
	        );

	        CommonMessage.debugMsg(
	                "Response Code :: " + res.getStatusCode()
	        );

	        String jsonResponse = res.getBody();

	        CommonMessage.debugMsg(
	                "JSON Response :: " + jsonResponse
	        );
	        
	        

	        return "Success";

	    } catch (IOException e) {

	        e.printStackTrace();

	        return "Failed";
	    }
	}
	
	private String inserJsonUpdate(String woDetailId, String completedBy, String datetime) {

	    StringBuilder str = new StringBuilder();

	    str.append("{");

	    str.append("\"woDetailId\":")
	       .append(woDetailId == null ? "null" : "\"" + woDetailId + "\"")
	       .append(",");

	    str.append("\"completedBy\":")
	       .append(completedBy == null ? "null" : "\"" + completedBy + "\"")
	       .append(",");

	    str.append("\"completedDate\":")
	       .append(datetime == null ? "null" : "\"" + datetime + "\"");

	    str.append("}");

	    System.out.println("Final String " + str.toString());

	    return str.toString();
	}

	public String insertJson(List<BalPlmTlWofeedback> newPlmTlWofeedbackList, BalWorkOrderDetailsBean workOrderDetailsBean, BalPlmTlWofeedback nwPlmTlWofeedback) {

	    StringBuilder str = new StringBuilder();
	    str.append("{");

	    // FEEDBACK LIST
	    str.append("\"feedbackList\":").append("[");
	    if (newPlmTlWofeedbackList != null && !newPlmTlWofeedbackList.isEmpty()) {
	        for (int i = 0; i < newPlmTlWofeedbackList.size(); i++) {
	            BalPlmTlWofeedback feedback = newPlmTlWofeedbackList.get(i);
	            str.append("{");
	            str.append("\"feedback\":").append(feedback == null ? "null" : feedback.toJsonManual()).append(",");
	            str.append("\"spareconsumed\":").append(feedback.getSpareConsumed() == null ? "null" : feedback.getSpareConsumed().toJsonManual()).append(",");
	            str.append("\"sparecostactual\":").append(feedback.getSpareCostActual() == null ? "null" : feedback.getSpareCostActual().toJsonManual()).append(",");
	            str.append("\"obsvTargetDate\":").append(feedback.getObsvTargetDate() == null ? Constants.pgPassNullDateTime : "\"" + feedback.getObsvTargetDate() + "\"").append(",");
	            str.append("\"obsvResponsibility\":").append(feedback.getObsvResponsibility() == null ? "null" : "\"" + feedback.getObsvResponsibility() + "\"").append(",");
	            str.append("\"cbmReading\":").append(feedback.getCbmReading() == null ? "null" : "\"" + feedback.getCbmReading() + "\"").append(",");
	            str.append("\"cbmNextDueDate\":").append(feedback.getCbmNextDueDate() == null ? "null" : "\"" + feedback.getCbmNextDueDate() + "\"").append(",");
	            str.append("\"cbmMinReading\":").append(feedback.getCbmMinReading() == null ? "null" : "\"" + feedback.getCbmMinReading() + "\"").append(",");
	            str.append("\"cbmMaxReading\":").append(feedback.getCbmMaxReading() == null ? "null" : "\"" + feedback.getCbmMaxReading() + "\"").append(",");
	            str.append("\"cbmAdjustedReading\":").append(feedback.getCbmAdjustedReading() == null ? "null" : "\"" + feedback.getCbmAdjustedReading() + "\"").append(",");
	            str.append("\"pmstandId\":").append(feedback.getPmstandId() == null ? "null" : "\"" + feedback.getPmstandId() + "\"").append(",");
	            str.append("\"pmCalendarId\":").append(feedback.getPmCalendarId() == null ? "null" : "\"" + feedback.getPmCalendarId() + "\"");
		            
	            
	            
	            
	            
	            str.append("}");
	            if (i < newPlmTlWofeedbackList.size() - 1) str.append(",");
	        }
	    }
	    str.append("]").append(",");

	    // FEEDBACK RESPONSIBILITY
	    str.append("\"pmStdId\":").append(UIUtils.isValidKeyId(nwPlmTlWofeedback.getPmstandId())? "{}" :nwPlmTlWofeedback.getPmstandId() ).append(",");

	    // WORK ORDER DETAILS
	    str.append("\"workOrderDetailsLstBean\":");
	    List<BalWorkOrderDetailsLstBean> gridfara = (workOrderDetailsBean == null) ? null : workOrderDetailsBean.getGrdWoBean();
	    if (gridfara == null || gridfara.isEmpty()) {
	        str.append("[]");
	    } else {
	        str.append("[");
	        for (int i = 0; i < gridfara.size(); i++) {
	            BalWorkOrderDetailsLstBean bean = gridfara.get(i);
	            str.append("{");
	            String pmCalendarId = (bean == null) ? null : bean.getPmCalendarId();
	            str.append("\"pmCalendarId\":").append(pmCalendarId == null ? "null" : "\"" + pmCalendarId.replace("\"", "\\\"") + "\"");
	            str.append("}");
	            if (i < gridfara.size() - 1) str.append(",");
	        }
	        str.append("]");
	    }
	    str.append(",");

	    // MULTIPLE RESPONSIBILITY
	    str.append("\"multipleResps\":").append(nwPlmTlWofeedback.getplmTlMultipleResp() == null ? "[]" : BalPlmTlMultipleResp.toJsonManualList(nwPlmTlWofeedback.getplmTlMultipleResp()));

	    str.append("}");

	    System.out.println("Final String " + str.toString());
	    return str.toString();
	}
	
	
	
	
	
	
	

}
