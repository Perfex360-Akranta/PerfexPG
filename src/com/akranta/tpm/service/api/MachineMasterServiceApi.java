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

import com.akranta.tpm.model.*;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MachineMasterServiceApi {
    private final Api api;
    
    public MachineMasterServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save or update machine master with all related data
     */
    public GenTlMachinemst saveMachineMaster(GenTlMachinemst genTlMachinemst) throws Exception {
        String apiUrl = "/machine-master/save";
        
        // Construct JSON payload
        String jsonPayload = buildMachineMasterJson(genTlMachinemst);
        
        CommonMessage.debugMsg("Machine Master JSON: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse response
        GenTlMachinemst result = parseMachineMasterResponse(jsonResponse);
        
        return result;
    }

    /**
     * Build JSON payload for machine master save/update
     */
    private String buildMachineMasterJson(GenTlMachinemst genTlMachinemst) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        
        // Master object
        json.append("\"master\":");
        json.append(genTlMachinemst.toJsonManual());
        json.append(",");
        
        // Functional Location
        json.append("\"functionalLocation\":");
        if (genTlMachinemst.getGenTlFunctionallocn() != null) {
            json.append(genTlMachinemst.getGenTlFunctionallocn().toJsonManual());
        } else {
            json.append("null");
        }
        json.append(",");
        
        // Operator Grid
        json.append("\"operatorGrid\":");
        if (genTlMachinemst.getOperatorgrid() != null && !genTlMachinemst.getOperatorgrid().isEmpty()) {
            json.append(GenTlMchemplink.toJsonManualList(genTlMachinemst.getOperatorgrid()));
        } else {
            json.append("[]");
        }
        json.append(",");
        
        // Maintenance Grid
        json.append("\"maintenanceGrid\":");
        if (genTlMachinemst.getMaintainceGrid() != null && !genTlMachinemst.getMaintainceGrid().isEmpty()) {
            json.append(GenTlMchmaintteamlink.toJsonManualList(genTlMachinemst.getMaintainceGrid()));
        } else {
            json.append("[]");
        }
        json.append(",");
        
        // Operator Skill Grid
        json.append("\"operatorSkillGrid\":");
        if (genTlMachinemst.getOperatorSkillGrid() != null && !genTlMachinemst.getOperatorSkillGrid().isEmpty()) {
            json.append(GenTlMachineskillmst.toJsonManualList(
                genTlMachinemst.getOperatorSkillGrid(), "O"));
        } else {
            json.append("[]");
        }
        json.append(",");
        
        // Maintenance Skill Grid
        json.append("\"maintenanceSkillGrid\":");
        if (genTlMachinemst.getMaintainceSkillGrid() != null && !genTlMachinemst.getMaintainceSkillGrid().isEmpty()) {
            json.append(GenTlMachineskillmst.toJsonManualList(
                genTlMachinemst.getMaintainceSkillGrid(), "M"));
        } else {
            json.append("[]");
        }
        json.append(",");
        
        // Sub Equipment Grid
        json.append("\"subEquipmentGrid\":");
        if (genTlMachinemst.getSubEquipmentGrid() != null && !genTlMachinemst.getSubEquipmentGrid().isEmpty()) {
            json.append(GenTlMchsubmchlink.toJsonManualList(genTlMachinemst.getSubEquipmentGrid()));
        } else {
            json.append("[]");
        }
        
        json.append("}");
        
        return json.toString();
    }

    /**
     * Parse machine master response from JSON
     */
    private GenTlMachinemst parseMachineMasterResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Parse master
        JSONObject masterObj = jsonObj.getJSONObject("master");
        GenTlMachinemst master = GenTlMachinemst.fromJson(masterObj.toString());
        
        // Parse functional location
        if (jsonObj.has("functionalLocation") && !jsonObj.isNullObject() 
                && !jsonObj.get("functionalLocation").equals(null)) {
            JSONObject funcLocnObj = jsonObj.getJSONObject("functionalLocation");
            GenTlFunctionallocn funcLocn = GenTlFunctionallocn.fromJson(funcLocnObj.toString());
            master.setGenTlFunctionallocn(funcLocn);
        }
        
        // Parse operator grid
        if (jsonObj.has("operatorGrid") && jsonObj.getJSONArray("operatorGrid").length()> 0) {
            JSONArray operatorArray = jsonObj.getJSONArray("operatorGrid");
            List<GenTlMchemplink> operators = GenTlMchemplink.fromJsonList(operatorArray.toString());
            master.setOperatorgrid(operators);
        }
        
        // Parse maintenance grid
        if (jsonObj.has("maintenanceGrid") && jsonObj.getJSONArray("maintenanceGrid").length()> 0) {
            JSONArray maintArray = jsonObj.getJSONArray("maintenanceGrid");
            List<GenTlMchmaintteamlink> maintenance = GenTlMchmaintteamlink.fromJsonList(maintArray.toString());
            master.setMaintainceGrid(maintenance);
        }
        
        // Parse operator skill grid
        if (jsonObj.has("operatorSkillGrid") && jsonObj.getJSONArray("operatorSkillGrid").length() > 0) {
            JSONArray opSkillArray = jsonObj.getJSONArray("operatorSkillGrid");
            List<GenTlMachineskillmst> opSkills = GenTlMachineskillmst.fromJsonList(opSkillArray.toString());
            master.setOperatorSkillGrid(opSkills);
        }
        
        // Parse maintenance skill grid
        if (jsonObj.has("maintenanceSkillGrid") && jsonObj.getJSONArray("maintenanceSkillGrid").length() > 0) {
            JSONArray maintSkillArray = jsonObj.getJSONArray("maintenanceSkillGrid");
            List<GenTlMachineskillmst> maintSkills = GenTlMachineskillmst.fromJsonList(maintSkillArray.toString());
            master.setMaintainceSkillGrid(maintSkills);
        }
        
        // Parse sub equipment grid
        if (jsonObj.has("subEquipmentGrid") && jsonObj.getJSONArray("subEquipmentGrid").length() > 0) {
            JSONArray subEquipArray = jsonObj.getJSONArray("subEquipmentGrid");
            List<GenTlMchsubmchlink> subEquipment = GenTlMchsubmchlink.fromJsonList(subEquipArray.toString());
            master.setSubEquipmentGrid(subEquipment);
        }
        
        return master;
    }
    
    
 public List<String[]> getOperatorData(String factId) throws IOException {
        
        // Build the API URL with query parameter
        String apiUrl = "/machine-master/operators";
        
        if (factId != null && !factId.trim().isEmpty()) {
            apiUrl += "?factId=" + URLEncoder.encode(factId, StandardCharsets.UTF_8);
        }
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONArray jsonArray = new JSONArray(json);
        
        // Convert to List<String[]> with column ordering
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        return result;
    }
    
 public List<String[]> getOperatorSkillData() throws IOException {
     
     String apiUrl = "/machine-master/operator-skills";
     
     CommonMessage.debugMsg("Calling API: " + apiUrl);
     
     // Send GET request
     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
     
     CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
     CommonMessage.debugMsg("Response Body: " + res.getBody());
     
     String json = res.getBody();
     
     // Parse JSON response
     JSONArray jsonArray = new JSONArray(json);
     
     // Convert to List<String[]>
     List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
     
     return result;
 }
 
 public List<String[]> getMaintenanceTeamDataForMachine() throws IOException {
     
     String apiUrl = "/machine-master/machine-teams";
     
		/*
		 * if (machineId != null && !machineId.trim().isEmpty()) { apiUrl +=
		 * "?machineId=" + URLEncoder.encode(machineId, StandardCharsets.UTF_8); }
		 */
     
     CommonMessage.debugMsg("Calling API: " + apiUrl);
     
     // Send GET request
     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
     
     CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
     CommonMessage.debugMsg("Response Body: " + res.getBody());
     
     String json = res.getBody();
     
     // Parse JSON response
     JSONArray jsonArray = new JSONArray(json);
     
     // Convert to List<String[]>
     List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
     
     return result;
 }
 
 public List<String[]> getMaintenanceSkillData() throws IOException {
     
     String apiUrl = "/machine-master/maintenance-skills";
     
     CommonMessage.debugMsg("Calling API: " + apiUrl);
     
     // Send GET request
     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
     
     CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
     CommonMessage.debugMsg("Response Body: " + res.getBody());
     
     String json = res.getBody();
     
     // Parse JSON response
     JSONArray jsonArray = new JSONArray(json);
     
     // Convert to List<String[]>
     List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
     
     return result;
 }
 public List<String[]> getEquipmentData(String equipmentNum) throws IOException {
     
     String apiUrl = "/machine-master/data?equipmentNum=" + URLEncoder.encode(equipmentNum, StandardCharsets.UTF_8);
     
     CommonMessage.debugMsg("Calling API: " + apiUrl);
     
     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
     
     CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
     CommonMessage.debugMsg("Response Body: " + res.getBody());
     
     String json = res.getBody();
     
     JSONArray jsonArray = new JSONArray(json);
     
     List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
     
     return result;
 }
 
	/*
	 * private List<String[]> convertJsonArrayToList(JSONArray jsonArray) {
	 * List<String[]> list = new ArrayList<>();
	 * 
	 * for (int i = 0; i < jsonArray.length(); i++) { JSONObject obj =
	 * jsonArray.getJSONObject(i);
	 * 
	 * String[] row = new String[9]; row[0] = obj.optString("sccv_keyid", "");
	 * row[1] = obj.optString("sccv_objectkey", ""); row[2] =
	 * obj.optString("sccv_equipmentnum", ""); row[3] =
	 * obj.optString("sccv_classtype", ""); row[4] = obj.optString("sccv_classnum",
	 * ""); row[5] = obj.optString("sccv_intclassnum", ""); row[6] =
	 * obj.optString("sccv_intcharnum", ""); row[7] = obj.optString("sccv_charname",
	 * ""); row[8] = obj.optString("sccv_charvalue", "");
	 * 
	 * list.add(row); }
	 * 
	 * CommonMessage.debugMsg("Converted " + list.size() + " equipment rows"); for
	 * (String[] row : list) { CommonMessage.debugMsg(Arrays.toString(row)); }
	 * 
	 * return list; }
	 */
    private List<String[]> convertJsonArrayToListWithColumnOrder(JSONArray jsonArray) {
        List<String[]> list = new ArrayList<>();

        if (jsonArray.length() < 1) {
            CommonMessage.debugMsg("Empty JSON array received");
            return list;
        }

        // Row 0 = column order mapping (based on your MOM pattern)
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

        // Process each data row (skip first row as it's column order)
        for (int i = 0; i < jsonArray.length(); i++) {
            if (i == 0) {
                continue; // Skip column order row
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

        // Debug output
        CommonMessage.debugMsg("Converted " + list.size() + " rows");
        for (String[] row : list) {
            CommonMessage.debugMsg(Arrays.toString(row));
        }

        return list;
    }
    
public List<String[]> getSubEquipmentData(String sectId, String eqpId) throws IOException {
        
        StringBuilder apiUrl = new StringBuilder("/machine-master/sub-equipment?");
        
        boolean hasParam = false;
        
        if (CommonFunctions.isValidKeyId(sectId)) {
            apiUrl.append("sectId=").append(URLEncoder.encode(sectId, StandardCharsets.UTF_8));
            hasParam = true;
        }
        
        if (CommonFunctions.isValidKeyId(eqpId)) {
            if (hasParam) {
                apiUrl.append("&");
            }
            apiUrl.append("eqpId=").append(URLEncoder.encode(eqpId, StandardCharsets.UTF_8));
        }
        
        CommonMessage.debugMsg("Calling API: " + apiUrl.toString());
        
        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        JSONArray jsonArray = new JSONArray(json);
        
        List<String[]> result = convertJsonArrayToList(jsonArray);
        
        return result;
    }
    
    private List<String[]> convertJsonArrayToList(JSONArray jsonArray) {
        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String[] row = new String[5];
            
            // tick (1 if linked, 0 if not)
            Object tickObj = obj.opt("tick");
            row[0] = (tickObj != null) ? tickObj.toString() : "0";
            
            // mchm_keyid
            row[1] = obj.optString("mchm_keyid", "");
            
            // empty column
            row[2] = obj.optString("empty_col", "");
            
            // mchm_machineno
            row[3] = obj.optString("mchm_machineno", "");
            
            // mchm_machinename
            row[4] = obj.optString("mchm_machinename", "");

            list.add(row);
        }

        CommonMessage.debugMsg("Converted " + list.size() + " sub equipment rows");
        for (String[] row : list) {
            CommonMessage.debugMsg(Arrays.toString(row));
        }

        return list;
    }
    
public List<String[]> getFormCircle(String mchId) throws IOException {
        
        String apiUrl = "/machine-master/form-circle?mchId=" + URLEncoder.encode(mchId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        JSONArray jsonArray = new JSONArray(json);
        
        List<String[]> result = convertJsonArrayToList1(jsonArray);
        
        return result;
    }
    
    private List<String[]> convertJsonArrayToList1(JSONArray jsonArray) {
        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String[] row = new String[2];
            row[0] = obj.optString("keyid", "");
            row[1] = obj.optString("circle", "");

            list.add(row);
        }

        CommonMessage.debugMsg("Converted " + list.size() + " circle rows");
        for (String[] row : list) {
            CommonMessage.debugMsg(Arrays.toString(row));
        }

        return list;
    }
    
    
    public GenTlMachinemst getEquipmentMasterByIdAsObject(String machineId) throws IOException {
        
        String apiUrl = "/machine-master/equipment-master/" + URLEncoder.encode(machineId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
      GenTlMachinemst result = GenTlMachinemst.fromJson(json);
        
        return result;
    }
    
    
   
    public List<String[]> recallOperatorData(String machineId) throws IOException {
        
        String apiUrl = "/machine-master/recall-operators?machineId=" + URLEncoder.encode(machineId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONArray jsonArray = new JSONArray(json);
        
        // Convert to List<String[]> with column ordering
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        return result;
    }
   
    public List<String[]> recallOperatorSkillData(String machineId) throws IOException {
        
        String apiUrl = "/machine-master/recall-operator-skills?machineId=" + URLEncoder.encode(machineId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONArray jsonArray = new JSONArray(json);
        
        // Convert to List<String[]> with column ordering
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        return result;
    }
    
   
    public List<String[]> recallMaintenanceData(String machineId) throws IOException {
        
        String apiUrl = "/machine-master/recall-maintenance?machineId=" + URLEncoder.encode(machineId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONArray jsonArray = new JSONArray(json);
        
        // Convert to List<String[]> with column ordering
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        return result;
    }
    
   
    public List<String[]> recallMaintenanceSkillData(String machineId) throws IOException {
        
        String apiUrl = "/machine-master/recall-maintenance-skills?machineId=" + URLEncoder.encode(machineId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONArray jsonArray = new JSONArray(json);
        
        // Convert to List<String[]> with column ordering
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        return result;
    }
    /**
     * Recall equipment parameter data linked to a specific machine (with column order pattern)
     */
    public List<String[]> recallEquipmentParameterData(String machineId) throws IOException {
        
        String apiUrl = "/machine-master/recall-equipment-parameters?machineId=" + URLEncoder.encode(machineId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONArray jsonArray = new JSONArray(json);
        
        // Convert to List<String[]> with column ordering
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        return result;
    }
    
    public boolean deleteOperatorSkill(String machineId, String skillDescription) throws IOException {
        
        // Build the API URL with query parameters
        String apiUrl = "/machine-master/operator-skill?machineId=" + 
                        URLEncoder.encode(machineId, StandardCharsets.UTF_8) + 
                        "&skillDescription=" + 
                        URLEncoder.encode(skillDescription, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Extract success flag
        boolean success = jsonObj.optBoolean("success", false);
        String message = jsonObj.optString("message", "");
        
        CommonMessage.debugMsg("Delete operation success: " + success);
        CommonMessage.debugMsg("Message: " + message);
        
        if (!success) {
            throw new IOException("Failed to delete operator skill: " + message);
        }
        
        return success;
    }
    
    public boolean deleteMaintenanceSkill(String machineId, String skillDescription) throws IOException {
        
        // Build the API URL with query parameters
        String apiUrl = "/machine-master/maintenance-skill?machineId=" + 
                        URLEncoder.encode(machineId, StandardCharsets.UTF_8) + 
                        "&skillDescription=" + 
                        URLEncoder.encode(skillDescription, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Extract success flag
        boolean success = jsonObj.optBoolean("success", false);
        String message = jsonObj.optString("message", "");
        
        CommonMessage.debugMsg("Delete operation success: " + success);
        CommonMessage.debugMsg("Message: " + message);
        
        if (!success) {
            throw new IOException("Failed to delete maintenance skill: " + message);
        }
        
        return success;
    }
    public boolean deleteOperatorMachineLink(String machineId, String employeeId) throws IOException {
        
        // Build the API URL with query parameters
        String apiUrl = "/machine-master/operator-link?machineId=" + 
                        URLEncoder.encode(machineId, StandardCharsets.UTF_8) + 
                        "&employeeId=" + 
                        URLEncoder.encode(employeeId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Extract success flag
        boolean success = jsonObj.optBoolean("success", false);
        String message = jsonObj.optString("message", "");
        
        CommonMessage.debugMsg("Delete operation success: " + success);
        CommonMessage.debugMsg("Message: " + message);
        
        if (!success) {
            throw new IOException("Failed to delete operator machine link: " + message);
        }
        
        return success;
    }
    public boolean deleteMaintenanceTeamMachineLink(String machineId, String maintenanceTeamId) throws IOException {
        
        // Build the API URL with query parameters
        String apiUrl = "/machine-master/maintenance-team-link?machineId=" + 
                        URLEncoder.encode(machineId, StandardCharsets.UTF_8) + 
                        "&maintenanceTeamId=" + 
                        URLEncoder.encode(maintenanceTeamId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Calling API: " + apiUrl);
        
        // Send DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        CommonMessage.debugMsg("Response Status: " + res.getStatusCode());
        CommonMessage.debugMsg("Response Body: " + res.getBody());
        
        String json = res.getBody();
        
        // Parse JSON response
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Extract success flag
        boolean success = jsonObj.optBoolean("success", false);
        String message = jsonObj.optString("message", "");
        
        CommonMessage.debugMsg("Delete operation success: " + success);
        CommonMessage.debugMsg("Message: " + message);
        
        if (!success) {
            throw new IOException("Failed to delete maintenance team machine link: " + message);
        }
        
        return success;
    }

    
}