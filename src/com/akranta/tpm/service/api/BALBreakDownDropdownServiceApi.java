package com.akranta.tpm.service.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_GenTlSubAssemblymst;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlBestmst;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class BALBreakDownDropdownServiceApi {

	private final Api api;
	
	public BALBreakDownDropdownServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public GenTlAssemblymst saveAssemblyApi(GenTlAssemblymst assemblyMst,GenTlFunctionallocn functionallocn) throws IOException 
	{
		 
		String apiUrl = "/breakdown/dropdown/assembly"; 
		
        
		String jsonPayload = insertJson(assemblyMst,functionallocn);
        CommonMessage.debugMsg("Json Assembly:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        GenTlAssemblymst mst = GenTlAssemblymst.fromJson(jsonResponse);
       
        return mst;
        
	}
	
	public GenTlAssemblymst updateAssemblyApi(GenTlAssemblymst assemblyMst, GenTlFunctionallocn functionallocn) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/assemblyupdate";

	    String jsonPayload = insertJson(assemblyMst, functionallocn);
	    CommonMessage.debugMsg("Json Assembly Update:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);

	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    GenTlAssemblymst mst = GenTlAssemblymst.fromJson(jsonResponse);
	    return mst;
	}
	
	public GenTlAssemblymst selectAssemblyApi(String keyid) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/assemblyrecall/" + keyid;

	    CommonMessage.debugMsg("Fetching Assembly:: " + keyid);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    GenTlAssemblymst mst = GenTlAssemblymst.fromJson(jsonResponse);
	    return mst;
	}
	
	public GenTlAssemblymst deleteAssemblyApi(String delemode, GenTlAssemblymst genTlAssemblymst) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/assembly/delete";
	    
	    JSONObject assmJson = new JSONObject();
	    assmJson.put("keyid", genTlAssemblymst.getAssmKeyid());

	    JSONObject payload = new JSONObject();
	    payload.put("delemode", delemode);
	    payload.put("assemblymst", assmJson); 
	    //payload.put("keyid", genTlAssemblymst.getAssmKeyid());
	    String jsonPayload = payload.toString();

	    CommonMessage.debugMsg("Json Assembly Delete:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	    CommonMessage.debugMsg(res.getStatusCode());

	    // No meaningful body expected back (void response); just confirm status
	    return genTlAssemblymst;
	}
	
	/*
	 * public BAL_GenTlSubAssemblymst saveSubAssemblyApi(BAL_GenTlSubAssemblymst
	 * subAssemblymst, GenTlFunctionallocn functionallocn) throws IOException {
	 * String apiUrl = "/breakdown/dropdown/subassemblysave";
	 * 
	 * JSONObject sbamJson = new JSONObject(subAssemblymst.toJsonManual());// or
	 * however your model serializes to JSON, matching your existing pattern
	 * JSONObject fnlnJson = functionallocn != null ? new
	 * JSONObject(functionallocn.toJsonManual()) : null;
	 * 
	 * JSONObject payload = new JSONObject(); payload.put("subAssemblymst",
	 * sbamJson); if (fnlnJson != null) payload.put("genTlFunctionallocn",
	 * fnlnJson);
	 * 
	 * String jsonPayload = payload.toString();
	 * CommonMessage.debugMsg("Json SubAssembly Save:: " + jsonPayload);
	 * 
	 * HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	 * CommonMessage.debugMsg(res.getStatusCode());
	 * 
	 * String jsonResponse = res.getBody(); BAL_GenTlSubAssemblymst result =
	 * BAL_GenTlSubAssemblymst.fromJson(jsonResponse); // adjust class/method name
	 * to match your actual model return result; }
	 */
	
	public BAL_GenTlSubAssemblymst saveSubAssemblyApi(BAL_GenTlSubAssemblymst subAssemblymst, GenTlFunctionallocn functionallocn) throws IOException 
	{
		 
		String apiUrl = "/breakdown/dropdown/subassemblysave"; 
		
        
		String jsonPayload = insertJsonSubassembly(subAssemblymst,functionallocn);
        CommonMessage.debugMsg("Json SubAssembly Save:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
   
        BAL_GenTlSubAssemblymst result = BAL_GenTlSubAssemblymst.fromJson(jsonResponse);
       
        return result;
        
	}
	
	public BAL_GenTlSubAssemblymst updateSubAssemblyApi(BAL_GenTlSubAssemblymst subAssemblymst, GenTlFunctionallocn functionallocn) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/subassemblyupdate";

	    String jsonPayload = insertJsonSubassembly(subAssemblymst, functionallocn); // reuse the same helper — key names already correct
	    CommonMessage.debugMsg("Json SubAssembly Update:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);

	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    BAL_GenTlSubAssemblymst result = BAL_GenTlSubAssemblymst.fromJson(jsonResponse);
	    return result;
	}
	
	public BAL_GenTlSubAssemblymst selectSubAssemblyApi(String keyid, String[] machineIdHolder) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/subassemblyrecall/" + keyid;

	    CommonMessage.debugMsg("Fetching SubAssembly:: " + keyid);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    JSONObject responseObj = new JSONObject(jsonResponse);
	    JSONObject sbamJson = responseObj.getJSONObject("subAssemblymst");
	    String machineId = responseObj.optString("machineId", "");

	    machineIdHolder[0] = machineId;

	    BAL_GenTlSubAssemblymst result = BAL_GenTlSubAssemblymst.fromJson(sbamJson.toString());
	    return result;
	}
	
	/*
	 * public BAL_GenTlSubAssemblymst deleteSubAssemblyApi(String delemode,
	 * BAL_GenTlSubAssemblymst subAssemblymst) throws IOException { String apiUrl =
	 * "/breakdown/dropdown/subassembly/delete";
	 * 
	 * JSONObject sbamJson = new JSONObject(); sbamJson.put("keyid",
	 * subAssemblymst.getSbamKeyid());
	 * 
	 * JSONObject payload = new JSONObject(); payload.put("delemode", delemode);
	 * payload.put("subAssemblymst", sbamJson); // must match
	 * Bal_SubAssemblyMstDto's field name exactly
	 * 
	 * String jsonPayload = payload.toString();
	 * CommonMessage.debugMsg("Json SubAssembly Delete:: " + jsonPayload);
	 * 
	 * HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	 * 
	 * CommonMessage.debugMsg(res.getStatusCode());
	 * 
	 * return subAssemblymst; }
	 */
	
	public BAL_GenTlSubAssemblymst deleteSubAssemblyApi(
	        String delemode,
	        BAL_GenTlSubAssemblymst subAssemblymst) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/subassembly/delete";

	    JSONObject sbamJson = new JSONObject();
	    sbamJson.put("keyid", subAssemblymst.getSbamKeyid());

	    JSONObject payload = new JSONObject();
	    payload.put("delemode", delemode);
	    payload.put("subAssemblymst", sbamJson);

	    String jsonPayload = payload.toString();

	    CommonMessage.debugMsg(
	        "Json SubAssembly Delete:: " + jsonPayload
	    );

	    HttpResponse res =
	        api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	    int statusCode = res.getStatusCode();

	    CommonMessage.debugMsg(
	        "Subassembly delete response code: " + statusCode
	    );
	    CommonMessage.debugMsg(
	        "Subassembly delete response body: " + res.getBody()
	    );

	    if (statusCode < 200 || statusCode >= 300)
	    {
	        String errorMessage = res.getBody();

	        if (errorMessage == null ||
	            errorMessage.trim().isEmpty())
	        {
	            errorMessage =
	                "Unable to delete subassembly. HTTP status: " +
	                statusCode;
	        }

	        throw new IOException(errorMessage);
	    }

	    return subAssemblymst;
	}
	
	// added by priyanka 
	
	public BAL_BdmTlPhenomenamst savePhenomenaApi(BAL_BdmTlPhenomenamst phen) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/phenomenasave";

	    String jsonPayload = insertJsonPhenomena(phen);
	    CommonMessage.debugMsg("Json Phenomena Save:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    BAL_BdmTlPhenomenamst result = BAL_BdmTlPhenomenamst.fromJson(jsonResponse);
	    return result;
	}

	public BAL_BdmTlPhenomenamst updatePhenomenaApi(BAL_BdmTlPhenomenamst phen) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/phenomenaupdate";

	    String jsonPayload = insertJsonPhenomena(phen);
	    CommonMessage.debugMsg("Json Phenomena Update:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);
	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    BAL_BdmTlPhenomenamst result = BAL_BdmTlPhenomenamst.fromJson(jsonResponse);
	    return result;
	}
	
	public BAL_BdmTlPhenomenamst selectPhenomenaApi(String keyid) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/phenomenarecall/" + keyid;

	    CommonMessage.debugMsg("Fetching Phenomena:: " + keyid);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    BAL_BdmTlPhenomenamst result = BAL_BdmTlPhenomenamst.fromJson(jsonResponse);
	    return result;
	}
	
	public void deletePhenomenaApi(String phenId) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/phenomena/delete";

	    JSONObject payload = new JSONObject();
	    payload.put("keyid", phenId);

	    String jsonPayload = payload.toString();
	    CommonMessage.debugMsg("Json Phenomena Delete:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	    CommonMessage.debugMsg(res.getStatusCode());
	}
	
	public BAL_BdmTlCausemst saveCauseApi(BAL_BdmTlCausemst causemst) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/causesave";

	    String jsonPayload = causemst.toJsonManual();
	    CommonMessage.debugMsg("Json Cause Save:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    BAL_BdmTlCausemst result = BAL_BdmTlCausemst.fromJson(jsonResponse);
	    return result;
	}

	public BAL_BdmTlCausemst updateCauseApi(BAL_BdmTlCausemst causemst) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/causeupdate";

	    String jsonPayload = causemst.toJsonManual();
	    CommonMessage.debugMsg("Json Cause Update:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);
	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    BAL_BdmTlCausemst result = BAL_BdmTlCausemst.fromJson(jsonResponse);
	    return result;
	}
	
	public BAL_BdmTlCausemst selectCauseApi(String keyid) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/causerecall/" + keyid;

	    CommonMessage.debugMsg("Fetching Cause:: " + keyid);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);

	    BAL_BdmTlCausemst result = BAL_BdmTlCausemst.fromJson(jsonResponse);
	    return result;
	}
	
	public void deleteCauseApi(String causeId) throws IOException
	{
	    String apiUrl = "/breakdown/dropdown/cause/delete";

	    JSONObject payload = new JSONObject();
	    payload.put("keyid", causeId);

	    String jsonPayload = payload.toString();
	    CommonMessage.debugMsg("Json Cause Delete:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	    CommonMessage.debugMsg(res.getStatusCode());
	}

	/*
	 * public String insertJsonPhenomena(BAL_BdmTlPhenomenamst phen) { StringBuilder
	 * str = new StringBuilder(); str.append("{"); str.append("\"phenomenamst\":");
	 * str.append(phen.toJsonManual()); str.append("}"); return str.toString(); }
	 */
	
	public String insertJsonPhenomena(BAL_BdmTlPhenomenamst phen)
	{
	    // no wrapping needed — the phenomena JSON itself is the entire request body
	    return phen.toJsonManual();
	}
	
	public String insertJson(GenTlAssemblymst assemblyMst,GenTlFunctionallocn functionallocn) 
	{
		
		StringBuilder str = new StringBuilder();
		//Master Details
		str.append("{");
		
		str.append("\"assemblymst\":");  
		String JsonMst = assemblyMst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		String JsonFun = functionallocn.toJsonManual();
		str.append("\"genTlFunctionallocn\":");
		
		str.append(JsonFun);
		
		str.append("}");
		return str.toString();
      }
	
	public String insertJsonSubassembly(BAL_GenTlSubAssemblymst subAssemblymst, GenTlFunctionallocn functionallocn) 
	{
		
		StringBuilder str = new StringBuilder();
		//Master Details
		str.append("{");
		
		str.append("\"subAssemblymst\":");  
		String JsonMst = subAssemblymst.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		String JsonFun = functionallocn.toJsonManual();
		str.append("\"genTlFunctionallocn\":");
		
		str.append(JsonFun);
		
		str.append("}");
		return str.toString();
      }
	
	
}
