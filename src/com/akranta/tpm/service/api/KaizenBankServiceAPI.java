package com.akranta.tpm.service.api;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONObject;

public class KaizenBankServiceAPI {
	private final Api api;

    public KaizenBankServiceAPI(String JwtToken) {
        this.api = new Api(JwtToken);
    }
    
	    public  KznTlKaizenbankmst  saveKznTlKaizenBankMst(KznTlKaizenbankmst kznTlKaizenBankMst) 
				throws Exception {
	        
			String apiUrl = "/kznbnk/save"; 
			
	        
			String jsonPayload =  kznTlKaizenBankMst.toJsonManual();
			
	        CommonMessage.debugMsg("Json Know Why:: "+jsonPayload);
	        
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
	        
	        
	        // Get the response code to confirm insert success, usually 201 Created
	        CommonMessage.debugMsg(res.getStatusCode());
	
	        
	        
	
	        String jsonResponse = res.getBody();
	        
	        
	        
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	   
	        KznTlKaizenbankmst  mst =KznTlKaizenbankmst.fromJson(jsonResponse);
	        
	        return mst;
	        
	
	}
    
    public String insertJson(QtmTlKnowwhymst qtmTlKnowwhymst) 
	{
		StringBuilder str = new StringBuilder();
		
		//Master Details
		str.append("{");
		
		str.append("\"qtmTlKnowwhymst\":");  
		String JsonMst = qtmTlKnowwhymst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		QtmTlKnowwhydtl dtls = qtmTlKnowwhymst.getqtmTlKnowwhydtl();
		str.append("\"qtmTlKnowwhydtl\":");
		String JsonDtl = dtls.toJsonManual();
		str.append(JsonDtl);
		
		
		str.append("}");
		return str.toString();
      }
	
	public QtmTlKnowwhymst getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		JSONObject mstObj = jsonObj.getJSONObject("qtmTlKnowwhymst");
		JSONObject dtlObj = jsonObj.getJSONObject("qtmTlKnowwhydtl");
		
		QtmTlKnowwhymst mst = QtmTlKnowwhymst.fromJson(mstObj.toString());
		QtmTlKnowwhydtl dtl = QtmTlKnowwhydtl.fromJson(dtlObj.toString());
		
		
		
		mst.setQtmTlKnowwhydtl(dtl);
		
		return mst;
		
	}
	
	public  KznTlKaizenbankmst getById(String keyId) throws IOException
	{
		
		 String apiUrl = "/kznbnk/getById/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
		           	
		
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       KznTlKaizenbankmst mst = KznTlKaizenbankmst.fromJson(json);
       
      
       return mst;
       

	}
			public  List<String[]> selectKznData(String keyId) throws IOException
		{
			
			 String apiUrl = "/kznbnk/selectKznData/"+keyId;
			      CommonMessage.debugMsg("data entered "+apiUrl );
			
	        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);
	
	        // Get the response code to confirm insert success, usually 201 Created
	       CommonMessage.debugMsg(res.getBody()+"Body");
	       String json = res.getBody();
	       
//	       KznTlKaizenbankmst mst = KznTlKaizenbankmst.fromJson(json);
	       List<String[]> kaizen = new ArrayList<>();

			kaizen.add(new String[]{
					json	
			});
	       
	      
	       return  kaizen;
	       
	
		}
			
			
			public KznTlKaizenbankmst updateKaizenWorkflowStatus(KznTlKaizenbankmst kznTlKaizenbankmst) throws Exception {

			    String apiUrl = "/kznbnk/update/kznworkflowstatus";

			    JSONObject json = new JSONObject();
			    json.put("kznKeyId",kznTlKaizenbankmst.getKzbnKeyid());
			    json.put("wfStatus",kznTlKaizenbankmst.getKzbnStatus());
			    json.put("kaizen",kznTlKaizenbankmst.getKzbnKaizen());
			    json.put("acrejby",kznTlKaizenbankmst.getKzbnAcrejby());
			    
			    json.put(
			            "implementCost",
			            UIUtils.isValidKeyId(kznTlKaizenbankmst.getKzbnImplementcost())
			                ? kznTlKaizenbankmst.getKzbnImplementcost().toString()
			                : "0"
			        );
			    
			 
			    json.put(
			            "targetDate",
			            UIUtils.isValidKeyId(
			                kznTlKaizenbankmst.getKzbnTargetdate())
			                    ?  CommonFunctions.pg_getDateTimeFromDate(kznTlKaizenbankmst.getKzbnTargetdate())
			                    : Constants.pgPassNullDateTime
			            
			        );
			     
			    
			    json.put(
			            "responsibility",
			            UIUtils.isValidKeyId(kznTlKaizenbankmst.getKzbnResponsibility())
			                    ? kznTlKaizenbankmst.getKzbnResponsibility()
			                    :"-"
			    );


			    json.put("mocRequired",kznTlKaizenbankmst.getKzbnmocrequired());
			    json.put("mocitem",kznTlKaizenbankmst.getKzbnMocitem());

			    json.put("verifyRemarks",kznTlKaizenbankmst.getKzbnVerifyremarks());

			    String jsonPayload = json.toString();

			    CommonMessage.debugMsg("Workflow Update JSON :: " + jsonPayload);

			    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

			    CommonMessage.debugMsg("HTTP Status :: " + res.getStatusCode());

			    String jsonResponse = res.getBody();
			    CommonMessage.debugMsg("JSON Response :: " + jsonResponse);

			    return kznTlKaizenbankmst;
			}
			
			
			
			public List<KznTlKaizenbankmst> multipleSave(List<KznTlKaizenbankmst> kznTlKaizenbankmst) throws IOException {
				 
				 CommonMessage.debugMsg("In side the Service Api");
				 String apiUrl = "/kznbnk/mltplesuggestion"; 
			        
			        // Convert object to JSON
			        String jsonPayload = KznTlKaizenbankmst.toJsonManualList(kznTlKaizenbankmst);
			        CommonMessage.debugMsg("Json :: "+jsonPayload);
			        
			        // Send request
			        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

			        // Get the response code to confirm insert success, usually 201 Created
			        CommonMessage.debugMsg(res.getStatusCode());
//			        int responseCode = res.getStatusCode();
			        
			        

			        String jsonResponse = res.getBody();
			        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
			        
			        List<KznTlKaizenbankmst> kaizens = KznTlKaizenbankmst.fromJsonList(jsonResponse);
			        
			        return kaizens;
			        
			 }

			

}



//json.put("responsibility",kznTlKaizenbankmst.getKzbnResponsibility());




//json.put(
//    "implementCost",
//    UIUtils.isValidKeyId(
//        implementCost 
//    )
//    ? implementCost.toString()
//    : "0"
//);
//
//json.put(
//    "targetDate",
//    UIUtils.isValidKeyId(
//        targetDate != null ? targetDate.toString() : null
//    )
//    ? targetDate.toString()
//    : null
//);
