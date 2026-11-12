package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.SheTlPlantSafetydtl;
import com.akranta.tpm.model.SheTlPlantSafetymst;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SheTlPlantSafetyServiceApi {

    private final Api api;

    public SheTlPlantSafetyServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SAVE (Insert + Update in one call)
    // master.spsmKeyid == null  →  INSERT
    // master.spsmKeyid present  →  UPDATE
    // ══════════════════════════════════════════════════════════════════════════

    public SheTlPlantSafetymst savePlantSafety(SheTlPlantSafetymst master) throws Exception {
        String apiUrl = "/plant-safety/save";

        String jsonPayload = buildSaveJson(master);
        System.out.println("PlantSafety Save JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        System.out.println("Response Status Code: " + res.getStatusCode());
        System.out.println("Response Body: " + res.getBody());

        return parsePlantSafetyResponse(res.getBody());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // GET complete record by masterKeyid
    // ══════════════════════════════════════════════════════════════════════════

    public SheTlPlantSafetymst getCompletePlantSafetyData(String masterKeyid) throws IOException {
        String apiUrl = "/plant-safety/complete/"
                + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        System.out.println("Get Complete PlantSafety Response: " + res.getBody());

        return parsePlantSafetyResponse(res.getBody());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // DELETE detail row
    // ══════════════════════════════════════════════════════════════════════════

    public SheTlPlantSafetydtl deletePlantSafetyDetail(String detailId) throws Exception {
        String apiUrl = "/plant-safety/delete-detail/"
                + URLEncoder.encode(detailId, StandardCharsets.UTF_8);

        System.out.println("Deleting PlantSafety detail with keyid: " + detailId);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        System.out.println("Response Code: " + res.getStatusCode());
        System.out.println("Response Body: " + res.getBody());

        JSONObject jsonObj = JSONObject.fromObject(res.getBody());

        boolean success  = jsonObj.optBoolean("success", false);
        String  message  = jsonObj.optString("message", "");
        String  deletedId = jsonObj.optString("deletedId", "");

        if (success) {
            System.out.println("PlantSafety detail deleted. DeletedId: " + deletedId);
            SheTlPlantSafetydtl deleted = new SheTlPlantSafetydtl();
            deleted.setSpsdKeyid(deletedId);
            return deleted;
        } else {
            System.err.println("Failed to delete PlantSafety detail. Message: " + message);
            throw new RuntimeException("Failed to delete PlantSafety detail: " + message);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Private helpers
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Build JSON payload:
     * {
     *   "master": { ...masterFields },
     *   "details": [ { ...detailFields }, ... ]
     * }
     */
    private String buildSaveJson(SheTlPlantSafetymst master) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // master block
        sb.append("\"master\":").append(master.toJsonManual());
        sb.append(",");

        // details block — use the list attached to the master
        sb.append("\"details\":");
        List<SheTlPlantSafetydtl> details = master.getSafetyDetails();
        if (details != null && !details.isEmpty()) {
            sb.append(SheTlPlantSafetydtl.toJsonManualList(details));
        } else {
            sb.append("[]");
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * Parse response JSON back into SheTlPlantSafetymst with its detail list.
     */
    private SheTlPlantSafetymst parsePlantSafetyResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);

        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        SheTlPlantSafetymst mst = SheTlPlantSafetymst.fromJson(mstObj.toString());

        // Parse details array
        if (jsonObj.has("details") && !jsonObj.get("details").equals(null)) {
            JSONArray dtlArray = jsonObj.getJSONArray("details");
            List<SheTlPlantSafetydtl> detailList = new ArrayList<>();

            for (int i = 0; i < dtlArray.length(); i++) {
                JSONObject dtlObj = dtlArray.getJSONObject(i);
                detailList.add(SheTlPlantSafetydtl.fromJson(dtlObj.toString()));
            }
            mst.setSafetyDetails(detailList);
        }

        return mst;
    }
	/*
	 * public SheTlPlantSafetymst getPlantSafetyByKeyid(String masterKeyid) throws
	 * IOException { String apiUrl = "/plant-safety/" +
	 * URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);
	 * 
	 * System.out.println("Fetching PlantSafety record for keyid: " + masterKeyid);
	 * 
	 * HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	 * 
	 * System.out.println("Response Status Code: " + res.getStatusCode());
	 * System.out.println("Response Body: " + res.getBody());
	 * 
	 * return
	 * SheTlPlantSafetymst.fromJson(JSONObject.fromObject(res.getBody()).toString())
	 * ; } public String getDetailValue(String keyid) throws SQLException { try {
	 * String apiUrl = "/plant-safety/detail-value/" + URLEncoder.encode(keyid,
	 * StandardCharsets.UTF_8);
	 * System.out.println("Fetching detail value for keyid: " + keyid); HttpResponse
	 * res = api.makeAuthRequest(apiUrl, "GET", null);
	 * System.out.println("Response Status Code: " + res.getStatusCode());
	 * System.out.println("Response Body: " + res.getBody()); return res.getBody();
	 * } catch (Exception e) { throw new
	 * SQLException("Error fetching detail value for keyid: " + keyid, e); } }
	 * public SheTlPlantSafetydtl getvaluesdtl(String keyid) throws SQLException {
	 * try { String apiUrl = "/plant-safety/detail/" + URLEncoder.encode(keyid,
	 * StandardCharsets.UTF_8); System.out.println("Fetching detail for keyid: " +
	 * keyid); HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	 * System.out.println("Response Status Code: " + res.getStatusCode());
	 * System.out.println("Response Body: " + res.getBody()); JSONArray jsonArray =
	 * JSONArray.fromObject(res.getBody()); if (jsonArray != null &&
	 * jsonArray.length() > 0) { JSONObject dtlObj = jsonArray.getJSONObject(0);
	 * return SheTlPlantSafetydtl.fromJson(dtlObj.toString()); } return null; }
	 * catch (Exception e) { throw new
	 * SQLException("Error fetching detail for keyid: " + keyid, e); } }
	 */
    
    
    public SheTlPlantSafetymst getPlantSafetyByKeyid(String masterKeyid) throws IOException {
        String apiUrl = "/plant-safety/"
                + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);

        System.out.println("Fetching PlantSafety record for keyid 1: " + masterKeyid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        System.out.println("Response Status Code 1: " + res.getStatusCode());
        System.out.println("Response Body 1: " + res.getBody());

        String json = res.getBody();
        SheTlPlantSafetymst mst = SheTlPlantSafetymst.fromJson(json);
        return mst;
    }

    public String getDetailValue(String keyid) throws SQLException {
        try {
            String apiUrl = "/plant-safety/detail-value/" 
                    + URLEncoder.encode(keyid, StandardCharsets.UTF_8);

            System.out.println("Fetching detail value for keyid 2: " + keyid);

            HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

            System.out.println("Response Status Code 2: " + res.getStatusCode());
            System.out.println("Response Body2: " + res.getBody());

            return res.getBody();
        } catch (Exception e) {
            throw new SQLException("Error fetching detail value for keyid2: " + keyid, e);
        }
    }

    public SheTlPlantSafetydtl getvaluesdtl(String keyid) throws SQLException {
        try {
            String apiUrl = "/plant-safety/detail/" 
                + URLEncoder.encode(keyid, StandardCharsets.UTF_8);

            System.out.println("Fetching detail for keyid3: " + keyid);

            HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

            System.out.println("Response Status Code3: " + res.getStatusCode());
            System.out.println("Response Body:3 " + res.getBody());

            String json = res.getBody();
            JSONArray jsonArray = JSONArray.fromObject(json);

            // ✅ Fix: use .size() instead of .length() for net.sf.json.JSONArray
            if (jsonArray != null && jsonArray.length() > 0) {
                JSONObject dtlObj = jsonArray.getJSONObject(0);
                return SheTlPlantSafetydtl.fromJson(dtlObj.toString());
            }
            return null;

        } catch (Exception e) {
            throw new SQLException("Error fetching detail for keyid3: " + keyid, e);
        }
    }
 // ══════════════════════════════════════════════════════════════════════════
 // GET - Responsibility by detail keyid
 // ══════════════════════════════════════════════════════════════════════════
 public String getResponsibility(String keyid) throws SQLException {
     try {
         String apiUrl = "/plant-safety/responsibility/"
                 + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
         HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
         System.out.println("Responsibility Response: " + res.getBody());
         return res.getBody();
     } catch (Exception e) {
         throw new SQLException("Error fetching responsibility for keyid: " + keyid, e);
     }
 }

 // ══════════════════════════════════════════════════════════════════════════
 // GET - Observations by detail keyid
 // ══════════════════════════════════════════════════════════════════════════
 public String getObservations(String keyid) throws SQLException {
     try {
         String apiUrl = "/plant-safety/observations/"
                 + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
         HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
         System.out.println("Observations Response: " + res.getBody());
         return res.getBody();
     } catch (Exception e) {
         throw new SQLException("Error fetching observations for keyid: " + keyid, e);
     }
 }

 // ══════════════════════════════════════════════════════════════════════════
 // GET - Master keyid by detail keyid
 // ══════════════════════════════════════════════════════════════════════════
 public String getMasterKeyid(String keyid) throws SQLException {
     try {
         String apiUrl = "/plant-safety/master-keyid/"
                 + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
         HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
         System.out.println("Master Keyid Response: " + res.getBody());
         return res.getBody();
     } catch (Exception e) {
         throw new SQLException("Error fetching master keyid for detail keyid: " + keyid, e);
     }
 }

 // ══════════════════════════════════════════════════════════════════════════
 // GET - Response remarks by detail keyid
 // ══════════════════════════════════════════════════════════════════════════
 public String getResponseRemarks(String keyid) throws SQLException {
     try {
         String apiUrl = "/plant-safety/response-remarks/"
                 + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
         HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
         System.out.println("Response Remarks Response: " + res.getBody());
         return res.getBody();
     } catch (Exception e) {
         throw new SQLException("Error fetching response remarks for keyid: " + keyid, e);
     }
 }
//══════════════════════════════════════════════════════════════════════════
//POST - Update single responsibility
//══════════════════════════════════════════════════════════════════════════
	/*
	 * public String singleResponsiblty(String detailkeyid, String responsevalue,
	 * String targetdate, SheTlPlantSafetymst master, SheTlPlantSafetydtl detail)
	 * throws Exception { try { String apiUrl =
	 * "/plant-safety/single-responsibility" + "?detailkeyid=" +
	 * URLEncoder.encode(detailkeyid, StandardCharsets.UTF_8) + "&responsevalue=" +
	 * URLEncoder.encode(responsevalue, StandardCharsets.UTF_8) + "&targetdate=" +
	 * URLEncoder.encode(targetdate, StandardCharsets.UTF_8) + "&responRemarks=" +
	 * URLEncoder.encode( (detail.getSpsdResponRemarks() == null ||
	 * detail.getSpsdResponRemarks().length() == 0) ? "-" :
	 * detail.getSpsdResponRemarks(), StandardCharsets.UTF_8);
	 * 
	 * System.out.println("SingleResponsibility API URL: " + apiUrl);
	 * 
	 * HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);
	 * System.out.println("SingleResponsibility Response Code: " +
	 * res.getStatusCode());
	 * System.out.println("SingleResponsibility Response Body: " + res.getBody());
	 * 
	 * return res.getBody();
	 * 
	 * } catch (Exception e) { throw new
	 * Exception("Error updating single responsibility for keyid: " + detailkeyid,
	 * e); } }
	 */
 public String singleResponsiblty(String detailkeyid, String responsevalue, String targetdate,
	        SheTlPlantSafetymst master, SheTlPlantSafetydtl detail) throws Exception {
	    try {
	        // ✅ Build JSON body instead of URL params
	        String responRemarks = (detail.getSpsdResponRemarks() == null 
	                || detail.getSpsdResponRemarks().length() == 0)
	                ? "-"
	                : detail.getSpsdResponRemarks();

	        String jsonBody = "{"
	                + "\"detailkeyid\":\"" + detailkeyid + "\","
	                + "\"responsevalue\":\"" + responsevalue + "\","
	                + "\"targetdate\":\"" + targetdate + "\","
	                + "\"master\":{},"
	                + "\"details\":[{\"reponseremarks\":\"" + responRemarks + "\"}]"
	                + "}";

	        System.out.println("SingleResponsibility JSON Body: " + jsonBody);

	        // ✅ Pass jsonBody instead of null
	        HttpResponse res = api.makeAuthRequest("/plant-safety/single-responsibility", "POST", jsonBody);

	        System.out.println("SingleResponsibility Response Code: " + res.getStatusCode());
	        System.out.println("SingleResponsibility Response Body: " + res.getBody());

	        return res.getBody();

	    } catch (Exception e) {
	        throw new Exception("Error updating single responsibility for keyid: " + detailkeyid, e);
	    }
	}
 
//══════════════════════════════════════════════════════════════════════════
//POST - Verify Update (responsibility + target date)
//══════════════════════════════════════════════════════════════════════════
public List<String[]> verifyUpdate(String dtlid, String verid, String verifydate) throws Exception {
  try {
      String jsonBody = "{"
              + "\"detailkeyid\":\"" + dtlid + "\","
              + "\"responsevalue\":\"" + verid + "\","
              + "\"targetdate\":\"" + verifydate + "\","
              + "\"master\":{},"
              + "\"details\":[]"
              + "}";

      System.out.println("VerifyUpdate JSON Body: " + jsonBody);

      HttpResponse res = api.makeAuthRequest("/plant-safety/verify-update", "POST", jsonBody);

      System.out.println("VerifyUpdate Response Code: " + res.getStatusCode());
      System.out.println("VerifyUpdate Response Body: " + res.getBody());

      // Wrap the String response into List<String[]> to match service impl signature
      List<String[]> result = new ArrayList<>();
      result.add(new String[]{res.getBody()});
      return result;

  } catch (Exception e) {
      throw new Exception("Error updating verify fields for dtlid: " + dtlid, e);
  }
}

public List<String[]> resUpdate(String dtlid, String resid, String targetdate) throws Exception {
    try {
        String jsonBody = "{"
                + "\"detailkeyid\":\"" + dtlid + "\","
                + "\"responsevalue\":\"" + resid + "\","
                + "\"targetdate\":\"" + targetdate + "\","
                + "\"master\":{},"
                + "\"details\":[]"
                + "}";

        System.out.println("ResUpdate JSON Body: " + jsonBody);

        HttpResponse res = api.makeAuthRequest("/plant-safety/res-update", "POST", jsonBody);

        System.out.println("ResUpdate Response Code: " + res.getStatusCode());
        System.out.println("ResUpdate Response Body: " + res.getBody());

        List<String[]> result = new ArrayList<>();
        result.add(new String[]{res.getBody()});
        return result;

    } catch (Exception e) {
        throw new Exception("Error updating responsibility for dtlid: " + dtlid, e);
    }
}

public List<String> checkDeleteList(String detailkeyid, String apmdelete) throws Exception {
    try {
        String apiUrl = "/plant-safety/check-delete"
                + "?detailkeyid=" + URLEncoder.encode(detailkeyid, StandardCharsets.UTF_8);

        if (apmdelete != null && !apmdelete.trim().isEmpty()) {
            apiUrl += "&apmdelete=" + URLEncoder.encode(apmdelete, StandardCharsets.UTF_8);
        }

        System.out.println("CheckDelete API URL: " + apiUrl);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);

        System.out.println("CheckDelete Response Code: " + res.getStatusCode());
        System.out.println("CheckDelete Response Body: " + res.getBody());

        // Wrap String response into List<String> to match service impl signature
        List<String> result = new ArrayList<>();
        result.add(res.getBody());
        return result;

    } catch (Exception e) {
        throw new Exception("Error deleting records for detailkeyid: " + detailkeyid, e);
    }
}

public String updateVerification(String detailkeyid, String responsevalue, String targetdate,
        String verifystatus, String remarks) throws Exception {
try {
if (remarks == null || remarks.length() == 0) {
remarks = "-";
}

String jsonBody = "{"
+ "\"detailkeyid\":\"" + detailkeyid + "\","
+ "\"responsevalue\":\"" + responsevalue + "\","
+ "\"targetdate\":\"" + targetdate + "\","
+ "\"verifystatus\":\"" + verifystatus + "\","
+ "\"remarks\":\"" + remarks + "\","
+ "\"master\":{},"
+ "\"details\":[]"
+ "}";

System.out.println("UpdateVerification JSON Body: " + jsonBody);

HttpResponse res = api.makeAuthRequest("/plant-safety/update-verification", "POST", jsonBody);

System.out.println("UpdateVerification Response Code: " + res.getStatusCode());
System.out.println("UpdateVerification Response Body: " + res.getBody());

return res.getBody();

} catch (Exception e) {
throw new Exception("Error updating verification for detailkeyid: " + detailkeyid, e);
}
}

//══════════════════════════════════════════════════════════════════════════
//POST - Update Action Closure
//══════════════════════════════════════════════════════════════════════════
public String updateActionClosure(String detailkeyid, String responsevalue, String targetdate,
     String revisedate, String status, String correctiveaction,
     String remarks, String abnKeyid) throws Exception {
 try {
     if (remarks == null || remarks.length() == 0) {
         remarks = "-";
     }

     String jsonBody = "{"
             + "\"detailkeyid\":\"" + detailkeyid + "\","
             + "\"responsevalue\":\"" + responsevalue + "\","
             + "\"targetdate\":\"" + targetdate + "\","
             + "\"revisedate\":\"" + (revisedate != null ? revisedate : "") + "\","
             + "\"status\":\"" + status + "\","
             + "\"correctiveaction\":\"" + (correctiveaction != null ? correctiveaction : "") + "\","
             + "\"remarks\":\"" + remarks + "\","
             + "\"abnKeyid\":\"" + (abnKeyid != null ? abnKeyid : "") + "\","
             + "\"master\":{},"
             + "\"details\":[]"
             + "}";

     System.out.println("UpdateActionClosure JSON Body: " + jsonBody);

     HttpResponse res = api.makeAuthRequest("/plant-safety/update-action-closure", "POST", jsonBody);

     System.out.println("UpdateActionClosure Response Code: " + res.getStatusCode());
     System.out.println("UpdateActionClosure Response Body: " + res.getBody());

     return res.getBody();

 } catch (Exception e) {
     throw new Exception("Error updating action closure for detailkeyid: " + detailkeyid, e);
 }
}
    }