

package com.akranta.tpm.service.api;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.BAL_PcsEntryBean;
import com.akranta.tpm.model.BAL_PcsTlDtl;
import com.akranta.tpm.model.BAL_PcsTlLosscapture;
import com.akranta.tpm.model.BAL_PcsTlLossreasonlink;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.BAL_PcsTlProductiondata;
import com.akranta.tpm.model.BAL_PcsTlRejectionbreakupdata;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;

public class BAL_PcsEntryServiceApi {
	
private final Api api;
	
	public BAL_PcsEntryServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

	public BAL_PcsTlProductiondata insertRecord(BAL_PcsTlProductiondata pcsTlProductiondata,
            BAL_PcsTlMst pcsTlMst,
            BAL_PcsTlDtl pcsTlDtl,
            BAL_PcsEntryBean pcsEntryBean) throws Exception {
String apiUrl = "/pcsentry/save"; // The Spring Boot API endpoint for insert
pcsTlMst.setPrlmActive("Y");
pcsTlDtl.setActive("Y");
pcsTlProductiondata.setPprdActive("Y");

// Convert objects to JSON (wrapper = BalPcsEntrySaveDto: master/detail/productionData/entryBean)
String jsonPayload = "{\"master\":" + pcsTlMst.toJsonManual()
+ ",\"detail\":" + pcsTlDtl.toJsonManual()
+ ",\"productionData\":" + pcsTlProductiondata.toJsonManual()
+ ",\"entryBean\":" + pcsEntryBean.toJsonManual() + "}";
CommonMessage.debugMsg("Json :: " + jsonPayload);

// Send request
HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

CommonMessage.debugMsg(res.getStatusCode());

String jsonResponse = res.getBody();
CommonMessage.debugMsg("JSON Response: " + jsonResponse);

// (only addition to your reference) stop on 4xx/5xx so an error body is not parsed as JSON
if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
throw new IllegalStateException("BAL PCS ENTRY SAVE failed. HTTP=" + res.getStatusCode()
+ " body=[" + jsonResponse + "]");
}

BAL_PcsTlProductiondata prod = BAL_PcsTlProductiondata.fromJson(jsonResponse);

return prod;
}
	
	
	//losssave
	public BAL_PcsTlLosscapture insertLossRecord(BAL_PcsTlLosscapture pcsTlLosscapture,
	        BAL_PcsTlMst pcsTlMst,
	        BAL_PcsTlDtl pcsTlDtl,
	        BAL_PcsTlLossreasonlink pcsTlLossreasonlink,
	        BAL_PcsEntryBean pcsEntryBean) throws Exception {

	    String apiUrl = "/pcsentry/saveLoss";   // BAL_PcsLossSaveController
	    pcsTlMst.setPrlmActive("Y");
	    pcsTlDtl.setActive("Y");
	    pcsTlLosscapture.setPlosActive("Y");
	    pcsTlLossreasonlink.setPlrkActive("Y");

	    // wrapper = BalPcsLossSaveDto: master/detail/lossCapture/lossReasonLink/entryBean
	    String jsonPayload = "{\"master\":" + pcsTlMst.toJsonManual()
	            + ",\"detail\":" + pcsTlDtl.toJsonManual()
	            + ",\"lossCapture\":" + pcsTlLosscapture.toJsonManual()
	            + ",\"lossReasonLink\":" + pcsTlLossreasonlink.toJsonManual()
	            + ",\"entryBean\":" + pcsEntryBean.toJsonManual() + "}";
	    CommonMessage.debugMsg("Json :: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
	        throw new IllegalStateException("BAL PCS LOSS SAVE failed. HTTP=" + res.getStatusCode()
	                + " body=[" + jsonResponse + "]");
	    }
	    return BAL_PcsTlLosscapture.fromJson(jsonResponse);
	}
	
	//rejectionsave
	public BAL_PcsTlRejectionbreakupdata create(BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata,
	        List<BAL_PcsTlRejectionbreakupdata> rejectionGridList) throws Exception {

	    String apiUrl = "/rejection/saveorupdate";   // Bal_pcs_tl_rejectionbreakupdataController

	    pcsTlRejectionbreakupdata.setPrbdActive("Y");

	    // production key + user go as query params, rows go as the JSON array body
	    apiUrl += "?pprdKeyid=" + java.net.URLEncoder.encode(pcsTlRejectionbreakupdata.getPrbdPprdKeyid(), "UTF-8")
	            + "&createdby=" + java.net.URLEncoder.encode(pcsTlRejectionbreakupdata.getPrbdCreatedby(), "UTF-8");

	    StringBuilder jsonPayload = new StringBuilder("[");
	    for (int i = 0; i < rejectionGridList.size(); i++) {
	        BAL_PcsTlRejectionbreakupdata row = rejectionGridList.get(i);
	        row.setPrbdActive("Y");
	        if (i > 0) jsonPayload.append(",");
	        jsonPayload.append(row.toJsonManual());
	    }
	    jsonPayload.append("]");
	    CommonMessage.debugMsg("Json :: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload.toString());
	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
	        throw new IllegalStateException("BAL REJECTION SAVE failed. HTTP=" + res.getStatusCode()
	                + " body=[" + jsonResponse + "]");
	    }
	    return pcsTlRejectionbreakupdata;
	}
	
	//rejectiondelete
	public BAL_PcsTlRejectionbreakupdata deleteRejectionbreakupdata(String keyid) throws Exception {
	    String apiUrl = "/rejection/delete/" + java.net.URLEncoder.encode(keyid, "UTF-8");
	    HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", "");
	    CommonMessage.debugMsg("JSON Response: " + res.getBody());
	    if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
	        throw new IllegalStateException("BAL REJECTION DELETE failed. HTTP=" + res.getStatusCode()
	                + " body=[" + res.getBody() + "]");
	    }
	    return new BAL_PcsTlRejectionbreakupdata();
	}
	
	//to get date, shift and product
	public List<String[]> getLastShiftProduct(String factId, String cellId, String flId,
	        String entryDate, String shift) throws Exception {

	    String apiUrl = "/pcsentry/last-shift-product"
	            + "?factId=" + java.net.URLEncoder.encode(factId, "UTF-8")
	            + "&cellId=" + java.net.URLEncoder.encode(cellId, "UTF-8")
	            + "&entryDate=" + java.net.URLEncoder.encode(entryDate, "UTF-8");
	    CommonMessage.debugMsg("data entered " + apiUrl);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	    String json = res.getBody();
	    CommonMessage.debugMsg(json + "Body");

	    List<String[]> lastProduct = new ArrayList<>();
	    if (json == null || json.trim().isEmpty()) {
	        return lastProduct;
	    }

	    JSONArray rows = JSONArray.fromObject(json);
	    for (int i = 0; i < rows.length(); i++) {
	        JSONArray cols = rows.getJSONArray(i);
	        String[] row = new String[cols.length()];
	        for (int j = 0; j < cols.length(); j++) {
	            Object v = cols.opt(j);
	            row[j] = (v == null || "null".equalsIgnoreCase(v.toString())) ? "" : v.toString();
	        }
	        lastProduct.add(row);
	    }
	    return lastProduct;
	}
	
	//to get the shift satrt time and end time
	public List<String[]> getShiftStarEndTime(String shiftId) throws Exception {

	    String apiUrl = "/pcsentry/shift-start-end-time"
	            + "?shiftId=" + java.net.URLEncoder.encode(shiftId, "UTF-8");
	    CommonMessage.debugMsg("data entered " + apiUrl);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	    String json = res.getBody();
	    CommonMessage.debugMsg(json + "Body");

	    List<String[]> shiftStrEnd = new ArrayList<>();
	    if (json == null || json.trim().isEmpty()) {
	        return shiftStrEnd;
	    }

	    JSONArray rows = JSONArray.fromObject(json);
	    for (int i = 0; i < rows.length(); i++) {
	        JSONArray cols = rows.getJSONArray(i);
	        String[] row = new String[cols.length()];
	        for (int j = 0; j < cols.length(); j++) {
	            Object v = cols.opt(j);
	            row[j] = (v == null || "null".equalsIgnoreCase(v.toString())) ? "" : v.toString();
	        }
	        shiftStrEnd.add(row);
	    }
	    return shiftStrEnd;
	}
	
	//to get holiday
	public List<String[]> getHolidayDates(String factId, String entryDate) throws Exception {

	    String apiUrl = "/pcsentry/holiday-dates"
	            + "?factId=" + java.net.URLEncoder.encode(factId, "UTF-8")
	            + "&entryDate=" + java.net.URLEncoder.encode(entryDate, "UTF-8");
	    CommonMessage.debugMsg("data entered " + apiUrl);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	    String json = res.getBody();
	    CommonMessage.debugMsg(json + "Body");

	    List<String[]> holiList = new ArrayList<>();
	    if (json == null || json.trim().isEmpty()) {
	        return holiList;
	    }

	    JSONArray rows = JSONArray.fromObject(json);
	    for (int i = 0; i < rows.length(); i++) {
	        JSONArray cols = rows.getJSONArray(i);
	        String[] row = new String[cols.length()];
	        for (int j = 0; j < cols.length(); j++) {
	            Object v = cols.opt(j);
	            row[j] = (v == null || "null".equalsIgnoreCase(v.toString())) ? "" : v.toString();
	        }
	        holiList.add(row);
	    }
	    return holiList;
	}
	
	//togetalltimehour
	public List<String[]> getAllTimeforHour(String cellId, String shiftId, String entryDate,
	        String hourId, String shiftWise) throws Exception {

	    String apiUrl = "/pcsentry/all-time-for-hour"
	            + "?cellId=" + java.net.URLEncoder.encode(cellId, "UTF-8")
	            + "&shiftId=" + java.net.URLEncoder.encode(shiftId, "UTF-8")
	            + "&entryDate=" + java.net.URLEncoder.encode(entryDate, "UTF-8")
	            + "&hourId=" + java.net.URLEncoder.encode(hourId == null ? "" : hourId, "UTF-8")
	            + "&shiftWise=" + java.net.URLEncoder.encode(shiftWise, "UTF-8");
	    CommonMessage.debugMsg("data entered " + apiUrl);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	    String json = res.getBody();
	    CommonMessage.debugMsg(json + "Body");

	    List<String[]> allTime = new ArrayList<>();
	    if (json == null || json.trim().isEmpty()) {
	        return allTime;
	    }

	    JSONArray rows = JSONArray.fromObject(json);
	    for (int i = 0; i < rows.length(); i++) {
	        JSONArray cols = rows.getJSONArray(i);
	        String[] row = new String[cols.length()];
	        for (int j = 0; j < cols.length(); j++) {
	            Object v = cols.opt(j);
	            row[j] = (v == null || "null".equalsIgnoreCase(v.toString())) ? "" : v.toString();
	        }
	        allTime.add(row);
	    }
	    return allTime;
	}
	
	//to get cycle time
	public List<String[]> getCycletime(String cellId, String machineId, String productId,
	        String entryDate, String sectId) throws Exception {

	    String apiUrl = "/pcsentry/cycletime"
	            + "?productId=" + java.net.URLEncoder.encode(productId, "UTF-8");
	    CommonMessage.debugMsg("data entered " + apiUrl);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	    String json = res.getBody();
	    CommonMessage.debugMsg(json + "Body");

	    List<String[]> cycletime = new ArrayList<>();
	    if (json == null || json.trim().isEmpty()) {
	        return cycletime;
	    }

	    JSONArray rows = JSONArray.fromObject(json);
	    for (int i = 0; i < rows.length(); i++) {
	        JSONArray cols = rows.getJSONArray(i);
	        String[] row = new String[cols.length()];
	        for (int j = 0; j < cols.length(); j++) {
	            Object v = cols.opt(j);
	            row[j] = (v == null || "null".equalsIgnoreCase(v.toString())) ? "" : v.toString();
	        }
	        cycletime.add(row);
	    }
	    return cycletime;
	}
	
	//getShiftTotaltime
	public int getShiftTotaltime(String startdate, String enddate, String shiftId) throws Exception {

	    String apiUrl = "/pcsentry/getShiftTotaltime"
	            + "?startdate=" + java.net.URLEncoder.encode(startdate, "UTF-8")
	            + "&enddate=" + java.net.URLEncoder.encode(enddate, "UTF-8")
	            + "&shiftId=" + java.net.URLEncoder.encode(shiftId, "UTF-8");
	    CommonMessage.debugMsg("data entered " + apiUrl);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	    String body = res.getBody();
	    CommonMessage.debugMsg(body + "Body");

	    if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
	        throw new IllegalStateException("BAL PCS SHIFT TOTAL TIME failed. HTTP=" + res.getStatusCode()
	                + " body=[" + body + "]");
	    }
	    return Integer.parseInt(body.trim());
	}
}