package com.akranta.tpm.service.api;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlProjectChecklistLink;
import com.akranta.tpm.model.KznTlProjectKaizenLink;
import com.akranta.tpm.model.KznTlProjectKpiLink;
import com.akranta.tpm.model.KznTlProjectResourceLink;
import com.akranta.tpm.model.KznTlProjectcreationmst;
import com.akranta.tpm.model.KznTlProjectmaicMileDtl;
import com.akranta.tpm.model.KznTlProjectmaicMileMst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class ProjectServiceApi {

	private final Api api;
	
	public ProjectServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public KznTlProjectcreationmst insertRecord(KznTlProjectcreationmst project) throws Exception {
        String apiUrl = "/fip"; 
   

        String jsonPayload = insertJson(project);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONObject obj = JSONObject.fromObject(jsonResponse);

        
        KznTlProjectcreationmst pcm = KznTlProjectcreationmst.fromJson(obj.getString("projectCreation"));
        
        return pcm;
        

}
	
	public KznTlProjectcreationmst getRecall(String KeyId) throws Exception {
        String apiUrl = "/fip/"+KeyId;
        
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET",null);

   
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        KznTlProjectcreationmst pcm = KznTlProjectcreationmst.fromJson(jsonResponse);
        
        return pcm;
        

}
	
	public KznTlProjectcreationmst updateRecord(KznTlProjectcreationmst project) throws Exception {
        String apiUrl = "/fip/update"; 
   

        String jsonPayload = updateJson(project);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        KznTlProjectcreationmst pcm = KznTlProjectcreationmst.fromJson(jsonResponse);
        
        return pcm;
        

}
	
	public KznTlProjectcreationmst updateMAICStatus(KznTlProjectcreationmst kznTlProjectcreationmst,String stage) throws Exception {
        String apiUrl = "/fip/update/maic";
           
      
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyid\":");
	    if (!CommonFunctions.isValidKeyId(kznTlProjectcreationmst.getKzpmKeyid())) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(kznTlProjectcreationmst.getKzpmKeyid()).append("\"");
        }
	    sb.append(",\"stage\":");
	    if (!CommonFunctions.isValidKeyId(stage)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(stage).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        
        
        return kznTlProjectcreationmst;    
}
	
	
	public List<String[]> updateFipApprovals(String rolename,String  refId, String nxtrole, String trnscode,String lstlvl,String flId, String roleid, String status) throws Exception {
        String apiUrl = "/fip/update/fipapproval";
           
      
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"rolename\":");
	    if (!CommonFunctions.isValidKeyId(rolename)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(rolename).append("\"");
        }
	    sb.append(",\"refId\":");
	    if (!CommonFunctions.isValidKeyId(refId)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(refId).append("\"");
        }
	    sb.append(",\"nxtrole\":");
	    if (!CommonFunctions.isValidKeyId(nxtrole)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(nxtrole).append("\"");
        }
	    sb.append(",\"trnscode\":");
	    if (!CommonFunctions.isValidKeyId(trnscode)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(trnscode).append("\"");
        }
	    sb.append(",\"lstlvl\":");
	    if (!CommonFunctions.isValidKeyId(lstlvl)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(lstlvl).append("\"");
        }
	    sb.append(",\"flId\":");
	    if (!CommonFunctions.isValidKeyId(flId)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(flId).append("\"");
        }
	    sb.append(",\"roleid\":");
	    if (!CommonFunctions.isValidKeyId(roleid)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(roleid).append("\"");
        }
	    sb.append(",\"status\":");
	    if (!CommonFunctions.isValidKeyId(status)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(status).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        List<String[]> result = null;
        
        return result;    
}
	
	public String updateFipWorkflowStatus(String kznKeyId, String refType,
			String transCode,boolean isUpdate,String wfStatus) throws Exception {
        String apiUrl = "/fip/update/fipworkflowstatus";
           
      
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"kznKeyId\":");
	    if (!CommonFunctions.isValidKeyId(kznKeyId)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(kznKeyId).append("\"");
        }
	    sb.append(",\"refType\":");
	    if (!CommonFunctions.isValidKeyId(refType)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(refType).append("\"");
        }
	    sb.append(",\"isUpdate\":");
	    if (isUpdate) {
	    	sb.append("\"").append("Y").append("\""); 
        } else {
            sb.append("\"").append("N").append("\"");
        }
	    sb.append(",\"transCode\":");
	    if (!CommonFunctions.isValidKeyId(transCode)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(transCode).append("\"");
        }
	    sb.append(",\"wfStatus\":");
	    if (!CommonFunctions.isValidKeyId(wfStatus)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(wfStatus).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        
        
        return jsonResponse;    
}
	
	
	public List<String[]> getProjectCheclist(String stage,String projectId) throws Exception {
        String apiUrl = "/fip/checklist";
        
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"projectId\":");
	    if (!CommonFunctions.isValidKeyId(projectId)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(projectId).append("\"");
        }
	    sb.append(",\"stage\":");
	    if (!CommonFunctions.isValidKeyId(stage)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(stage).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);

   
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        

        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
        
        return datalist;
        

}
	
	public List<String[]> getProjectKaizen(String flid,String projectId) throws Exception {
        String apiUrl = "/fip/kaizen";
        
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"projectId\":");
	    if (!CommonFunctions.isValidKeyId(projectId)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(projectId).append("\"");
        }
	    sb.append(",\"flid\":");
	    if (!CommonFunctions.isValidKeyId(flid)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(flid).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);

   
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        

        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
        
        return datalist;
        

}
	

	
	public List<String[]> getProjectKpi(String projectId) throws Exception {
        String apiUrl = "/fip/kpi/"+projectId;
           
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET",null);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        
        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
        
        return datalist;    
}
	
	public List<String[]> getProjectMileStones(String projectId) throws Exception {
        String apiUrl = "/fip/milestone/"+projectId;
           
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET",null);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        
        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr);
        
        return datalist;    
}
	
	public KznTlProjectmaicMileMst getRecallMilestone(String keyid) throws Exception {
        String apiUrl = "/fip/milestone/recall/"+keyid;
           
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET",null);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        KznTlProjectmaicMileMst mst = KznTlProjectmaicMileMst.fromJson(jsonResponse);
        
        mst.setKmmmFromdate(com.akranta.tpm.utils.CommonFunctions.pg_getDateFromPGTimeStamp(mst.getKmmmFromdate()));
        mst.setKmmmTodate(com.akranta.tpm.utils.CommonFunctions.pg_getDateFromPGTimeStamp(mst.getKmmmTodate()));
        
        return mst;    
}
	
	public List<String[]> getAllMileStones(String keyid, String stage) throws Exception {
        String apiUrl = "/fip/milestone/all";
           
      
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyid\":");
	    if (!CommonFunctions.isValidKeyId(keyid)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(keyid).append("\"");
        }
	    sb.append(",\"stage\":");
	    if (!CommonFunctions.isValidKeyId(stage)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(stage).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONArray arr = JSONArray.fromObject(jsonResponse);
        
        List<String[]> datalist = convertJsonArrayToListwithcolumnorder(arr,1,true);
        
        return datalist;    
}
	
	
	public KznTlProjectmaicMileMst saveMilestones(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst) throws Exception {
        String apiUrl = "/fip/milestone/save";
        
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"milestoneMst\":");
	    sb.append(newKznTlProjectmaicMileMst.toJsonManual()); 
       
	    sb.append(",\"milestoneDtlList\":");
        sb.append(KznTlProjectmaicMileDtl.toJsonManualList(newKznTlProjectmaicMileMst.getMilestonedetail())); 
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);

   
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONObject obj = JSONObject.fromObject(jsonResponse);
        

        newKznTlProjectmaicMileMst = KznTlProjectmaicMileMst.fromJson(obj.getString("milestoneMst"));
        
        return newKznTlProjectmaicMileMst;
        

}
	
	public KznTlProjectKpiLink saveKpi(List<KznTlProjectKpiLink> kpiLinkList) throws Exception {
        String apiUrl = "/fip/kpi/save"; 
   

        String jsonPayload = KznTlProjectKpiLink.toJsonManualList(kpiLinkList);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        //List<KznTlProjectKpiLink> newkpilinklist = KznTlProjectKpiLink.fromJsonList(jsonResponse);
        KznTlProjectKpiLink	newkznTlProjectKpiLink= new KznTlProjectKpiLink();
        return newkznTlProjectKpiLink;
        

}
	
	public KznTlProjectKaizenLink saveKaizen(KznTlProjectKaizenLink kaizenLink) throws Exception {
        String apiUrl = "/fip/kaizen/save"; 
   

        String jsonPayload = KznTlProjectKaizenLink.toJsonManualList(kaizenLink);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        //List<KznTlProjectKpiLink> newkpilinklist = KznTlProjectKpiLink.fromJsonList(jsonResponse);
        KznTlProjectKaizenLink	newKznTlProjectKaizenLink= new KznTlProjectKaizenLink();
        return newKznTlProjectKaizenLink;
        

}
	
	public KznTlProjectResourceLink saveResourse(List<KznTlProjectResourceLink> kpiLinkList) throws Exception {
        String apiUrl = "/fip/resourse/save"; 

        String jsonPayload = KznTlProjectResourceLink.toJsonManualListSave(kpiLinkList);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        //List<KznTlProjectResourceLink> newProjectResourceLink = KznTlProjectResourceLink.fromJsonList(jsonResponse);
        KznTlProjectResourceLink	newProjectResourceLink= new KznTlProjectResourceLink();
        return newProjectResourceLink;
        

}
	
	public void deleteResourse(String keyId) throws Exception {
        String apiUrl = "/fip/resourse/"+keyId; 

      
        
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        

}
	
	public void deleteMilestoneDetail(String keyId) throws Exception {
        String apiUrl = "/fip/milestone/dtl/"+keyId; 

      
        
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        

}
	
	public KznTlProjectmaicMileMst deleteMilestone(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst) throws Exception {
        String apiUrl = "/fip/milestone/mst/"+newKznTlProjectmaicMileMst.getKmmmKeyid(); 

        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        return newKznTlProjectmaicMileMst;
}
	
	public void saveCheckList(List<KznTlProjectChecklistLink> checkListLinks) throws Exception {
        String apiUrl = "/fip/checklist/save"; 
   

        String jsonPayload = KznTlProjectChecklistLink.toJsonManualList(checkListLinks);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        //List<KznTlProjectKpiLink> newkpilinklist = KznTlProjectKpiLink.fromJsonList(jsonResponse);
//        KznTlProjectChecklistLink	newkznTlProjectKpiLink= new KznTlProjectChecklistLink();
//        return newkznTlProjectKpiLink;
        

}
	
	
	public static String insertJson(KznTlProjectcreationmst project) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"projectCreation\":");
	    sb.append(project.toJsonManual());
	    sb.append(",\"resourceLinkList\":");
	    sb.append(KznTlProjectResourceLink.toJsonManualList(project.getProjectResourceList()));
	    sb.append(",\"workFlow\":");
	    sb.append(project.getWorkFlowApp().toJsonManual());
	    sb.append(",\"elementId\":");
	    sb.append("\"").append(project.getElementid()).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
	public static String updateJson(KznTlProjectcreationmst project) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"projectCreation\":");
	    sb.append(project.toJsonManual());
	    sb.append(",\"mode\":");
	    sb.append("\"").append(project.getMode()).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray) {
		
	    List<String[]> list = new ArrayList<>();

	    if (jsonArray.length() < 1) {
	        throw new IllegalArgumentException("JSON array must have at least 1 rows (config, header, column order).");
	    }

	    
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


public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray,int colOrderNo,boolean isDataOrder) {
    List<String[]> list = new ArrayList<>();

    if (jsonArray.length() < colOrderNo) {
        throw new IllegalArgumentException("JSON array must have at least colOrderNo rows (config, header, column order).");
    }

    // Row 3 = column order mapping
    JSONObject orderObj = jsonArray.getJSONObject(colOrderNo-1);

    // Build ordered list of keys based on orderObj values
    List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
    Iterator<String> it = orderObj.keys();
    while (it.hasNext()) {
        String key = it.next();
        CommonMessage.debugMsg(key);
        try {
            int order = Integer.parseInt(orderObj.getString(key));
            orderList.add(new AbstractMap.SimpleEntry<>(key, order));
        } catch (NumberFormatException e) {
            orderList.add(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
        }
    }
    
    if (isDataOrder) {
        orderList.removeIf(entry ->
                "dataorder".equalsIgnoreCase(entry.getKey()));
    }

    // Sort keys by numeric order
    orderList.sort(Comparator.comparingInt(Map.Entry::getValue));
    
    

    // Process each row (from row 1 onwards if you want headers, or from row 3 for data only)
    for (int i = 0; i < jsonArray.length(); i++) {
    	if( i == colOrderNo-1) {
    		continue;
    	}
        JSONObject obj = jsonArray.getJSONObject(i);
        String[] row = new String[orderList.size()];

        int colIndex = 0;
        for (Map.Entry<String, Integer> entry : orderList) {
            String key = entry.getKey();
            Object value = obj.opt(key);
           // row[colIndex++] = value != null ? value.toString() : "";
            row[colIndex++] = (value != null && !"null".equals(value.toString())) ? value.toString() : "";
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
