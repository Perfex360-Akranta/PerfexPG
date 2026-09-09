package com.akranta.tpm.service.api;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class ActionPlanServiceApi {

	private final Api api;
	
	public ActionPlanServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public GenTlActionplanmst save(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl) throws Exception {
        String apiUrl = "/actionplan"; // The Spring Boot API endpoint for insert
        
        // Convert object to JSON
        String jsonPayload = saveJson(genTlActionplanmst,genTlActionplandtl);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
  	    JSONObject obj = JSONObject.fromObject(jsonResponse);
        
  	    genTlActionplanmst = GenTlActionplanmst.fromJson(obj.getString("actionPlanMst"));
  	    
  	    //genTlActionplandtl = GenTlActionplandtl.fromJsonList(obj.getString("actionPlanDtls"));
        
        return genTlActionplanmst;
        

}
	
	public void saveCompletion(List<GenTlActionplandtl> genActionPlanList ) throws Exception {
        String apiUrl = "/actionplan/compSave"; // The Spring Boot API endpoint for insert
        
        // Convert object to JSON
        String jsonPayload = completionJsonList(genActionPlanList);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
  	    //JSONObject obj = JSONObject.fromObject(jsonResponse);
        
  	    //genTlActionplanmst = GenTlActionplanmst.fromJson(obj.getString("actionPlanMst"));
  	    
  	    //genTlActionplandtl = GenTlActionplandtl.fromJsonList(obj.getString("actionPlanDtls"));
        
        //return genTlActionplanmst;
        

}
	
	public GenTlActionplandtl deleteActionPlan(GenTlActionplandtl genTlActionplandtl) throws Exception {
        String apiUrl = "/actionplan/delete"; // The Spring Boot API endpoint for insert
        
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyid\":");
	    if (!CommonFunctions.isValidKeyId(genTlActionplandtl.getApldKeyid())) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(genTlActionplandtl.getApldKeyid()).append("\"");
        }
	    sb.append(",\"masterKeyid\":");
	    if (!CommonFunctions.isValidKeyId(genTlActionplandtl.getApldAplmKeyid())) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(genTlActionplandtl.getApldAplmKeyid()).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
    
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

      
        CommonMessage.debugMsg(res.getStatusCode());
        
        

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        
        return genTlActionplandtl;
        

}
	
	public static String saveJson(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"actionPlanMst\":");
	    sb.append(genTlActionplanmst.toJsonManual());
	    
	    
	    List<GenTlActionplandtl> genTlActionplandtls= getActionPlanDetailsList(genTlActionplandtl);
		
	    sb.append(",\"actionPlanDtls\":");
	    sb.append(GenTlActionplandtl.toJsonManualList(genTlActionplandtls));
	    sb.append("}");
	    return sb.toString();
	}
	
	
	public static List<GenTlActionplandtl> getActionPlanDetailsList(GenTlActionplandtl genTlActionplandtl) {
	
	    
	    
	    List<GenTlActionplandtl> genTlActionplandtls= genTlActionplandtl.getActionplanlist();
	    
	    List<GenTlActionplandtl> newGenTlActionplandtls= new ArrayList<>(); 
		
		if( genTlActionplandtls !=null && genTlActionplandtls.size()>0){
			
	            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
				{
	            	//genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,13,"APLD", "YY", "Y"));
	            	//genTlActionplandtlss.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
	            	genTlActionplandtlss.setApldHowtodo(genTlActionplandtl.getApldHowtodo());
	            	genTlActionplandtlss.setApldActionplan(genTlActionplandtl.getApldActionplan());
	            	genTlActionplandtlss.setApldTradeid(genTlActionplandtl.getApldTradeid());
	            	genTlActionplandtlss.setApldStatus(genTlActionplandtl.getApldStatus());
	            	genTlActionplandtlss.setApldTargetdate(genTlActionplandtl.getApldTargetdate());
	            	genTlActionplandtlss.setApldCompleatedon(genTlActionplandtl.getApldCompleatedon());
	            	genTlActionplandtlss.setApldCompletedby(genTlActionplandtl.getApldCompletedby());
	            	genTlActionplandtlss.setApldCountermeasure(genTlActionplandtl.getApldCountermeasure());
	            	genTlActionplandtlss.setApldCreatedby(genTlActionplandtl.getApldCreatedby());
	            	genTlActionplandtlss.setApldCreatedon(genTlActionplandtl.getApldCreatedon());
	            	genTlActionplandtlss.setApldRemarks(genTlActionplandtl.getApldRemarks());
	            	genTlActionplandtlss.setApldOthers(genTlActionplandtl.getApldOthers());
	            	genTlActionplandtlss.setApldTempfiled2(genTlActionplandtl.getApldTempfiled2());
	            	genTlActionplandtlss.setApldTempfiled3(genTlActionplandtl.getApldTempfiled3());
	            	genTlActionplandtlss.setApldTempfiled4(genTlActionplandtl.getApldTempfiled4());
	            	genTlActionplandtlss.setApldTempfiled5(genTlActionplandtl.getApldTempfiled5());
	            	genTlActionplandtlss.setApldActive(genTlActionplandtl.getApldActive());
	            	genTlActionplandtlss.setApldModifiedon(genTlActionplandtl.getApldModifiedon());
	            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
	            	
	            	newGenTlActionplandtls.add(genTlActionplandtlss);
					//sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
				} 
            }
		 else{
//		   if (!CommonFunctions.isValidKeyId(genTlActionplandtl.getApldKeyid())) {
//			  
//		   }
		   newGenTlActionplandtls.add(genTlActionplandtl);
		   }
	   
	    return newGenTlActionplandtls;
	}
	
	
	public static String completionJson(GenTlActionplandtl genTlActionplandtl) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyid\":");
	    sb.append("\"").append(genTlActionplandtl.getApldKeyid()).append("\"");
	    sb.append(",\"aplmkeyid\":");
	    sb.append("\"").append(genTlActionplandtl.getApldAplmKeyid()).append("\"");
	    sb.append(",\"responsibility\":");
	    sb.append("\"").append(genTlActionplandtl.getApldResponsibility()).append("\"");
	    sb.append(",\"countermeasure\":");
	    sb.append("\"").append(genTlActionplandtl.getApldCountermeasure()).append("\"");
	    sb.append(",\"status\":");
	    sb.append("\"").append(genTlActionplandtl.getApldStatus()).append("\"");
	    sb.append(",\"completedby\":");
	    sb.append("\"").append(genTlActionplandtl.getApldCompletedby()).append("\"");
	    sb.append(",\"remarks\":");
	    sb.append("\"").append(genTlActionplandtl.getApldRemarks()).append("\"");
	    sb.append(",\"compleatedon\":");
	    sb.append("\"").append(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompleatedon()) ? com.akranta.tpm.utils.CommonFunctions.pg_getDateTimeFromDate(genTlActionplandtl.getApldCompleatedon())  : Constants.pgFutureNullDateTime).append("\"");//genTlActionplandtl.getApldCompleatedon()).append("\"");
	    sb.append(",\"targetdate\":");
	    sb.append("\"").append(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldTargetdate()) ? com.akranta.tpm.utils.CommonFunctions.pg_getDateTimeFromDate(genTlActionplandtl.getApldTargetdate())  : Constants.pgFutureNullDateTime).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
	public static String completionJsonList(List<GenTlActionplandtl> list) {

	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(completionJson(list.get(i)));
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
}
