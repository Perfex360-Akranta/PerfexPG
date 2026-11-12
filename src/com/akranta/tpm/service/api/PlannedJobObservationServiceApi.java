package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.model.SheTlPlannedjobobservation;
import com.akranta.tpm.model.SheTlPlnjobemplink;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PlannedJobObservationServiceApi {

    private final Api api;

    public PlannedJobObservationServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
        
        
    }

    /**
     * Save Planned Job Observation with employee links (handles both insert and update)
     */
    public SheTlPlannedjobobservation savePlnjobemplink(SheTlPlannedjobobservation master) throws Exception {
        String apiUrl = "/plnjobemplink/save";

        String jsonPayload = buildSaveJson(master);
        System.out.println("Plnjobemplink JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        System.out.println("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        
        System.out.println("JSON Response: " + jsonResponse);

        SheTlPlannedjobobservation result = parseResponse(jsonResponse);
        System.out.println("Parsed Result: " + result);

        return result;
    }

    /**
     * Build JSON payload for save/update operation
     */
    private String buildSaveJson(SheTlPlannedjobobservation master) {
        StringBuilder str = new StringBuilder();

        str.append("{");

        // Master
        str.append("\"master\":");
        str.append(master.toJsonManual());
        str.append(",");

        // Details array
        str.append("\"details\":");
        List<SheTlPlnjobemplink> details = master.getSheTlPlnjobemplink();
        if (details != null && !details.isEmpty()) {
            str.append(SheTlPlnjobemplink.toJsonManualList(details));
        } else {
            str.append("[]");
        }

        str.append("}");

        System.out.println("Save JSON: " + str);
        return str.toString();
    }

    /**
     * Parse JSON response into SheTlPlannedjobobservation with details
     */
    private SheTlPlannedjobobservation parseResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);

        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        SheTlPlannedjobobservation mst = SheTlPlannedjobobservation.fromJson(mstObj.toString());

        // Parse details array
        if (jsonObj.has("details") && !jsonObj.isNullObject() &&
                !jsonObj.get("details").equals(null)) {

            JSONArray dtlArray = jsonObj.getJSONArray("details");
            if (dtlArray.length() > 0) {
                List<SheTlPlnjobemplink> details =
                        SheTlPlnjobemplink.fromJsonList(dtlArray.toString());
                mst.setSheTlPlnjobemplink(details);
            }
        }

        return mst;
    }

    /**
     * Get complete Planned Job Observation data by master keyid
     */
    public SheTlPlannedjobobservation getCompletePlnjobemplinkData(String masterKeyid) throws IOException {
        String apiUrl = "/plnjobemplink/complete/" +
                URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        System.out.println("Get Complete Data Response: " + res.getBody());

        return parseResponse(res.getBody());
    }

    /**
     * Get Planned Job Observation master record by keyid
     */
    public SheTlPlannedjobobservation getPlannedJobObservationByKeyid(String keyid) throws Exception {
        String apiUrl = "/plnjobemplink/find/" +
                URLEncoder.encode(keyid, StandardCharsets.UTF_8);

        System.out.println("Fetching PlannedJobObservation with keyid: " + keyid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        System.out.println("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        System.out.println("JSON Response: " + jsonResponse);

        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            throw new RuntimeException("No record found for keyid: " + keyid);
        }

        SheTlPlannedjobobservation result = SheTlPlannedjobobservation.fromJson(jsonResponse);
        System.out.println("Parsed Result: " + result);

        return result;
    }

    /**
     * Delete a Plnjobemplink detail record by detail keyid
     */
    public SheTlPlnjobemplink deletePlnjobemplinkDetail(String detailId) throws Exception {
        String apiUrl = "/plnjobemplink/delete-detail/" +
                URLEncoder.encode(detailId, StandardCharsets.UTF_8);

        System.out.println("Deleting Plnjobemplink detail with keyid: " + detailId);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

        System.out.println("Response Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        System.out.println("JSON Response: " + jsonResponse);

        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);

        boolean success = jsonObj.optBoolean("success", false);
        String message = jsonObj.optString("message", "");
        String deletedId = jsonObj.optString("deletedId", "");

        if (success) {
            System.out.println("Plnjobemplink detail deleted successfully. DeletedId: " + deletedId);

            SheTlPlnjobemplink deletedDetail = new SheTlPlnjobemplink();
            deletedDetail.setPjelKeyid(deletedId);
            return deletedDetail;
        } else {
            System.err.println("Failed to delete Plnjobemplink detail. Message: " + message);
            throw new RuntimeException("Failed to delete Plnjobemplink detail: " + message);
        }
    }
    
    
    
    
    
    
    
    /**
     * Get employee list for a Planned Job Observation by pjobKeyId
     */
    public List<String[]> getPojEmployee(String pjobKeyId) throws Exception {
        
        // Guard: return only header row if keyid is null or empty
        if (pjobKeyId == null || pjobKeyId.trim().isEmpty()) {
            System.out.println("pjobKeyId is null or empty — returning header row only");
            List<String[]> result = new ArrayList<>();
            result.add(new String[]{"", "PJOB_EMPLOYEEID", "PJEL_KEYID", "EMPLOYEE", "Delete"});
            return result;
        }

        String apiUrl = "/plnjobemplink/employees/" +
                URLEncoder.encode(pjobKeyId, StandardCharsets.UTF_8);

        System.out.println("Fetching employee list for pjobKeyId: " + pjobKeyId);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        System.out.println("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        System.out.println("JSON Response: " + jsonResponse);

        List<String[]> result = new ArrayList<>();

        // First row = column headers
        result.add(new String[]{"", "PJOB_EMPLOYEEID", "PJEL_KEYID", "EMPLOYEE", "Delete"});

        // Return only headers if response is empty or null
        if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.trim().equals("[]")) {
            System.out.println("No employee records found for pjobKeyId: " + pjobKeyId);
            return result;
        }

        // Parse JSON array and convert each object to String[]
        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String[] row = new String[]{
                obj.optString("sno", ""),
                obj.optString("pjob_employeeid", ""),
                obj.optString("pjel_keyid", ""),
                obj.optString("employee", ""),
                obj.optString("delete_btn", "")
            };

            result.add(row);
        }

        System.out.println("Parsed Employee List Size: " + result.size());
        return result;
    }
    
    
    public int updateActionPlan(String pojKeyid, String aplmKeyid) throws Exception {
        String apiUrl = "/plnjobobservation/update-action-plan/" +
                "pojKeyid=" + URLEncoder.encode(pojKeyid, StandardCharsets.UTF_8) +
                "&aplmKeyid=" + URLEncoder.encode(aplmKeyid, StandardCharsets.UTF_8);

        System.out.println("Updating ActionPlan for pojKeyid: " + pojKeyid + " with aplmKeyid: " + aplmKeyid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", null);

        System.out.println("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        System.out.println("JSON Response: " + jsonResponse);

        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            throw new RuntimeException("No record found for pojKeyid: " + pojKeyid);
        }

        if (res.getStatusCode() == 404) {
            throw new RuntimeException("No record found for pojKeyid: " + pojKeyid);
        }

        if (res.getStatusCode() != 200) {
            throw new RuntimeException("Failed to update ActionPlan for pojKeyid: " + pojKeyid +
                    " | Status: " + res.getStatusCode());
        }

        // Parse rowsAffected from response JSON using simple string extraction
        String rowsAffectedStr = jsonResponse
                .replace("{", "")
                .replace("}", "")
                .split("\"rowsAffected\":")[1]
                .split(",")[0]
                .trim();

        int rowsAffected = Integer.parseInt(rowsAffectedStr);

        System.out.println("Rows Affected: " + rowsAffected);

        return rowsAffected;
    }
      
    
    
    /**
     * Delete a Planned Job Observation master record by keyid
     */
    
    
    
//    public SheTlPlannedjobobservation deletePlannedJobObservation(
//            SheTlPlannedjobobservation sheTlPlannedjobobservation) throws Exception {
//        try {
//            if (sheTlPlannedjobobservation == null) {
//                throw new IllegalArgumentException("PlannedJobObservation object cannot be null");
//            }
//
//            String keyid = sheTlPlannedjobobservation.getPjobKeyid();
//
//            if (keyid == null || keyid.trim().isEmpty()) {
//                throw new IllegalArgumentException("PlannedJobObservation keyid cannot be null or empty");
//            }
//
//            // ✅ FIX: Use the correct URL that matches @DeleteMapping("/delete/{keyid}")
//            // Check your controller's @RequestMapping and replace accordingly:
//            // If @RequestMapping("/plnjobobservation")     → use "/plnjobobservation/delete/"
//            // If @RequestMapping("/plnjobemplink")         → use "/plnjobemplink/delete/"
//            // If @RequestMapping("/planned-job-observation") → use "/planned-job-observation/delete/"
//            String apiUrl = "/plnjobobservation/delete/" +
//                    URLEncoder.encode(keyid, StandardCharsets.UTF_8);
//
//            System.out.println("Deleting PlannedJobObservation with keyid: " + keyid);
//            System.out.println("Calling API URL: " + apiUrl);
//
//            // ✅ Send DELETE request
//            HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
//
//            System.out.println("Delete Response Status Code: " + res.getStatusCode());
//            System.out.println("Delete Response Body: " + res.getBody());
//
//            // ✅ FIX: Parse response properly — your backend returns { success, message, deletedId }
//            if (res.getStatusCode() == 200) {
//                String responseBody = res.getBody();
//
//                JSONObject jsonResponse = JSONObject.fromObject(responseBody);
//                boolean success = jsonResponse.optBoolean("success", false);
//                String message = jsonResponse.optString("message", "");
//                String deletedId = jsonResponse.optString("deletedId", "");
//
//                if (success) {
//                    System.out.println("Successfully deleted PlannedJobObservation. DeletedId: " + deletedId);
//                    return sheTlPlannedjobobservation;
//                } else {
//                    throw new Exception("Delete failed: " + message);
//                }
//
//            } else if (res.getStatusCode() == 404) {
//                throw new Exception("PlannedJobObservation not found with keyid: " + keyid);
//
//            } else if (res.getStatusCode() == 400) {
//                throw new Exception("Bad request (400) - Invalid keyid: " + keyid);
//
//            } else if (res.getStatusCode() == 403) {
//                throw new Exception("Access denied (403) - Invalid token or wrong URL for keyid: " + keyid);
//
//            } else {
//                throw new Exception("Failed to delete PlannedJobObservation. Status code: "
//                        + res.getStatusCode() + " | Body: " + res.getBody());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new Exception("Failed to delete PlannedJobObservation: " + e.getMessage(), e);
//        }
//    }
    
    
    public SheTlPlannedjobobservation deletePlannedJobObservation(
            SheTlPlannedjobobservation sheTlPlannedjobobservation) throws Exception {
        try {
            if (sheTlPlannedjobobservation == null) {
                throw new IllegalArgumentException("PlannedJobObservation object cannot be null");
            }

            String keyid = sheTlPlannedjobobservation.getPjobKeyid();

            if (keyid == null || keyid.trim().isEmpty()) {
                throw new IllegalArgumentException("PlannedJobObservation keyid cannot be null or empty");
            }

            // ✅ Using same pattern as working deletePlnjobemplinkDetail method
            String apiUrl = "/plnjobemplink/delete/" +
                    URLEncoder.encode(keyid, StandardCharsets.UTF_8);

            System.out.println("Deleting PlannedJobObservation with keyid: " + keyid);
            System.out.println("Calling API URL: " + apiUrl);

            HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

            System.out.println("Delete Response Status Code: " + res.getStatusCode());
            System.out.println("Delete Response Body: " + res.getBody());

            // ✅ Parse response same way as deletePlnjobemplinkDetail
            if (res.getStatusCode() == 200) {
                JSONObject jsonResponse = JSONObject.fromObject(res.getBody());
                boolean success = jsonResponse.optBoolean("success", false);
                String message = jsonResponse.optString("message", "");
                String deletedId = jsonResponse.optString("deletedId", "");

                if (success) {
                    System.out.println("Successfully deleted PlannedJobObservation. DeletedId: " + deletedId);
                    return sheTlPlannedjobobservation;
                } else {
                    throw new Exception("Delete failed: " + message);
                }

            } else if (res.getStatusCode() == 404) {
                throw new Exception("PlannedJobObservation not found with keyid: " + keyid);
            } else if (res.getStatusCode() == 400) {
                throw new Exception("Bad request (400) - Invalid keyid: " + keyid);
            } else if (res.getStatusCode() == 403) {
                throw new Exception("Access denied (403) - Invalid token or wrong URL for keyid: " + keyid);
            } else {
                throw new Exception("Failed to delete PlannedJobObservation. Status code: "
                        + res.getStatusCode() + " | Body: " + res.getBody());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to delete PlannedJobObservation: " + e.getMessage(), e);
        }
    }
    
    
    
    //DElETE method for employee id:
    
    
    
    public String deleteDetail(String pjelPjobEmployeeid, String gridId) throws Exception {
        try {
            if (pjelPjobEmployeeid == null || pjelPjobEmployeeid.trim().isEmpty()) {
                throw new IllegalArgumentException("pjelPjobEmployeeid cannot be null or empty");
            }

            String apiUrl = "/plnjobemplink/delete-detail/" +
                    URLEncoder.encode(pjelPjobEmployeeid, StandardCharsets.UTF_8);

            System.out.println("Deleting PLNJOBEMPLINK detail with pjelPjobEmployeeid: " + pjelPjobEmployeeid);
            System.out.println("Calling API URL: " + apiUrl);

            HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

            System.out.println("Delete Response Status Code: " + res.getStatusCode());
            System.out.println("Delete Response Body: " + res.getBody());

            if (res.getStatusCode() == 200) {
                JSONObject jsonResponse = JSONObject.fromObject(res.getBody());
                boolean success = jsonResponse.optBoolean("success", false);
                String message = jsonResponse.optString("message", "");
                String deletedId = jsonResponse.optString("deletedId", "");

                if (success) {
                    System.out.println("Successfully deleted PLNJOBEMPLINK detail. DeletedId: " + deletedId);
                    return message;
                } else {
                    throw new Exception("Delete failed: " + message);
                }

            } else if (res.getStatusCode() == 404) {
                throw new Exception("PLNJOBEMPLINK detail not found with pjelPjobEmployeeid: " + pjelPjobEmployeeid);
            } else if (res.getStatusCode() == 400) {
                throw new Exception("Bad request (400) - Invalid pjelPjobEmployeeid: " + pjelPjobEmployeeid);
            } else if (res.getStatusCode() == 403) {
                throw new Exception("Access denied (403) - Invalid token or wrong URL for pjelPjobEmployeeid: " + pjelPjobEmployeeid);
            } else {
                throw new Exception("Failed to delete PLNJOBEMPLINK detail. Status code: "
                        + res.getStatusCode() + " | Body: " + res.getBody());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to delete PLNJOBEMPLINK detail: " + e.getMessage(), e);
        }
    }
}  
    