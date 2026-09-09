package com.akranta.tpm.service.api;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlKkprojectprioritydtl;
import com.akranta.tpm.model.KznTlKkprojectprioritymst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONObject;

public class ProjectPriorityServiceApi {

private final Api api;
	
	public ProjectPriorityServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public KznTlKkprojectprioritymst saveRecord(KznTlKkprojectprioritymst projectPriority) throws Exception {
        String apiUrl = "/fipp"; 
   

        String jsonPayload = buildSaveJson(projectPriority);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        JSONObject obj = JSONObject.fromObject(jsonResponse);

        List<KznTlKkprojectprioritymst> mstList =  KznTlKkprojectprioritymst.fromJsonList(obj.getString("mstList"));
        List<KznTlKkprojectprioritydtl> dtlList =  KznTlKkprojectprioritydtl.fromJsonList(obj.getString("dtlList"));
        
        projectPriority.setMaster(mstList);
        projectPriority.setDetail(dtlList);
        
        return projectPriority;
        

}
	
	public static String buildSaveJson(KznTlKkprojectprioritymst projectPriority) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"mstList\":"); 
	    sb.append(getMstJson(projectPriority));
	    sb.append(",\"dtlList\":");
	    sb.append(getDtlJson(projectPriority));
	    sb.append("}");
	    return sb.toString();
	}
	
	private static String getMstJson(KznTlKkprojectprioritymst projectPriority) {
		List<KznTlKkprojectprioritymst> mstList = projectPriority.getMaster();
		
		List<KznTlKkprojectprioritymst> newMstList = new ArrayList<>();
		for(KznTlKkprojectprioritymst mst : mstList) {
			mst.setKppmCreatedby(projectPriority.getKppmCreatedby());
			fillvalues(mst);
			newMstList.add(mst);
		}
		
		return KznTlKkprojectprioritymst.toJsonManualList(newMstList);
	}
	
	private static String getDtlJson(KznTlKkprojectprioritymst projectPriority) {
		List<KznTlKkprojectprioritydtl> dtlList = projectPriority.getDetail();
		
		List<KznTlKkprojectprioritydtl> newDtlList = new ArrayList<>();
		for(KznTlKkprojectprioritydtl dtl : dtlList) {
			dtl.setKppdCreatedby(projectPriority.getKppmCreatedby());
			fillvalues(dtl);
			newDtlList.add(dtl);
		}
		
		return KznTlKkprojectprioritydtl.toJsonManualList(newDtlList);
	}
	
	
	
	private static void fillvalues(KznTlKkprojectprioritydtl newKznTlKkprojectprioritydtl) {
		String dateTime = CommonFunctions.pg_dateTimeNow();

		if(newKznTlKkprojectprioritydtl.getKppdActive() == null){
			newKznTlKkprojectprioritydtl.setKppdActive("Y");
		}
		if(newKznTlKkprojectprioritydtl.getKppdCreatedon() == null){
			newKznTlKkprojectprioritydtl.setKppdCreatedon(dateTime);
		}
		if(newKznTlKkprojectprioritydtl.getKppdModifiedon() == null){
			newKznTlKkprojectprioritydtl.setKppdModifiedon(dateTime);
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield1() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield1("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield2() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield2("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield3() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield3("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield4() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield4("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield5() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield5("-");
		}		
	}
	
	private static void fillvalues(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst) {
		String dateTime = CommonFunctions.pg_dateTimeNow();

		if(newKznTlKkprojectprioritymst.getKppmActive() == null){
			newKznTlKkprojectprioritymst.setKppmActive("Y");
		}
		if(newKznTlKkprojectprioritymst.getKppmCreatedon() == null){
			newKznTlKkprojectprioritymst.setKppmCreatedon(dateTime);
		}
		if(newKznTlKkprojectprioritymst.getKppmModifiedon() == null){
			newKznTlKkprojectprioritymst.setKppmModifiedon(dateTime);
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield1() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield1("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield2() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield2("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield3() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield3("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield4() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield4("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmRank() == null){
			newKznTlKkprojectprioritymst.setKppmRank("-");
		}
	}
}
