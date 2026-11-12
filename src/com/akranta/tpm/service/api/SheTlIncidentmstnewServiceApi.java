package com.akranta.tpm.service.api;

import com.akranta.tpm.model.SheTlIncidentmstnew;
import com.akranta.tpm.utils.CommonFunctions;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.akranta.tpm.bean.SheTlIncidentEmployeenewBean;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;

public class SheTlIncidentmstnewServiceApi {
	private final Api api;
	
	public SheTlIncidentmstnewServiceApi (String JwtToken) {
		this.api = new Api(JwtToken);
	}
	
	public SheTlIncidentmstnew insertRecord(SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {
        String apiUrl = "/ehsacc/save"; // The Spring Boot API endpoint for insert
       // sheTIncidentmstnew.setAbnmActive("Y");
        
        // Convert object to JSON
        String jsonPayload = sheTlIncidentmstnew.toJsonManual();
        System.out.println("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        System.out.println(res.getStatusCode());
//        int responseCode = res.getStatusCode();
        
        

        String jsonResponse = res.getBody();
        System.out.println("JSON Response: " + jsonResponse);
        
        SheTlIncidentmstnew abn = SheTlIncidentmstnew.fromJson(jsonResponse);
        
        return abn;}
        
        public SheTlIncidentEmployeenew insertEmployee(SheTlIncidentEmployeenew sheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) throws Exception {
            String apiUrl = "/ehsacc/saveEmployee"; // The Spring Boot API endpoint for insert
           // sheTIncidentmstnew.setAbnmActive("Y");
            
            // Convert object to JSON
            String jsonPayload = insertJson(sheTlIncidentEmployee, sheTlIncidentEmployeetBean);
            System.out.println("Json :: "+jsonPayload);
            
            // Send request
            HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

            // Get the response code to confirm insert success, usually 201 Created
            System.out.println(res.getStatusCode());
//            int responseCode = res.getStatusCode();
            
            

            String jsonResponse = res.getBody();
            System.out.println("JSON Response: " + jsonResponse);
            
            SheTlIncidentEmployeenew abn = SheTlIncidentEmployeenew.fromJson(jsonResponse);
            
            return abn;
            }
	
            
            public String insertJson(SheTlIncidentEmployeenew sheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) 
            {
            	StringBuilder sb = new StringBuilder();
            	sb.append("{");
           	    sb.append("\"sheTlIncidentEmployeenew\":");
           	    sb.append(sheTlIncidentEmployee.toJsonManual());
           	 sb.append(",\"contractEmp\":");
           	if (sheTlIncidentEmployeetBean.getContractEmp() != null) {
           	    sb.append("\"").append(sheTlIncidentEmployeetBean.getContractEmp()).append("\"");
           	} else {
           	    sb.append("null");
           	}

           	// contractComp
           	sb.append(",\"contractComp\":");
           	if (sheTlIncidentEmployeetBean.getContractComp() != null) {
           	    sb.append("\"").append(sheTlIncidentEmployeetBean.getContractComp()).append("\"");
           	} else {
           	    sb.append("null");
           	}

           	// contract
           	sb.append(",\"contract\":");
           	if (sheTlIncidentEmployeetBean.getContract() != null) {
           	    sb.append("\"").append(sheTlIncidentEmployeetBean.getContract()).append("\"");
           	} else {
           	    sb.append("null");
           	}
           	    sb.append("}");
           	    System.out.println(sb.toString());
           	    return sb.toString();
            }
            
            
            public SheTlIncidentmstnew getSheTlIncidentmstnewById(String keyId) throws IOException
    		{
    			System.out.println("key id in serivce api "+keyId);
    			 String apiUrl = "/ehsacc/getById?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8);
    			
    			
    	        
    	        // Send request
    	        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

    	        // Get the response code to confirm insert success, usually 201 Created
    	       System.out.println(res.getBody()+"Body");
    	       String json = res.getBody();
    	       
    	       
    	       SheTlIncidentmstnew mst = SheTlIncidentmstnew.fromJson(json);
    	       return mst;
    	       

    		}
            
            public SheTlIncidentmstnew deleteIncident(SheTlIncidentmstnew sheTlIncidentmstnew) throws IOException
    		{
            	String keyId = sheTlIncidentmstnew.getSinmKeyid();
    			System.out.println("key id in serivce api "+keyId);
    			
    			 String apiUrl = "/ehsacc/delete/"+ URLEncoder.encode(keyId, StandardCharsets.UTF_8);
    			
    			
    	        
    	        // Send request
    	        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

    	        // Get the response code to confirm insert success, usually 201 Created
    	       System.out.println(res.getBody()+"Body");
    	       String json = res.getBody();
    	       
    	       
    	       return sheTlIncidentmstnew;
    	       

    		}
            
            
            
            public String compJsonManual(SheTlIncidentmstnew incidentDto) {
                StringBuilder sb = new StringBuilder();
                sb.append("{");

                sb.append("\"keyid\":").append("\"").append(incidentDto.getSinmKeyid()).append("\"");
                sb.append(",\"exact_desc\":").append("\"").append(incidentDto.getSinmExactDesc()).append("\"");
                sb.append(",\"exact_type\":").append("\"").append(incidentDto.getSinmExactType()).append("\"");
                sb.append(",\"analysed_by\":").append("\"").append(incidentDto.getSinmAnalysedBy()).append("\"");
                
                // Format LocalDateTime as string if not null
                if (incidentDto.getSinmAnalysedDate() != null) {
                    sb.append(",\"analysed_date\":").append("\"").append(CommonFunctions.pg_getDateTimeFromTimeStamp(incidentDto.getSinmAnalysedDate().toString()+":00")).append("\"");
                } else {
                    sb.append(",\"analysed_date\":").append("\"").append("").append("\"");
                }

                sb.append(",\"analysed_shift\":").append("\"").append(incidentDto.getSinmAnalysedShift()).append("\"");
                sb.append(",\"rptRecommendations\":").append("\"").append(incidentDto.getSinmRptrecommendations()).append("\"");
                sb.append(",\"investigation\":").append("\"").append(incidentDto.getSinmInvestigation()).append("\"");
                sb.append(",\"invstremarks\":").append("\"").append(incidentDto.getSinmInvestigationRemarks()).append("\"");
                sb.append(",\"rootcausedueto\":").append("\"").append(incidentDto.getSinmRootcausedueto()).append("\"");

                sb.append("}");
                return sb.toString();
            }
            
            
            public SheTlIncidentmstnew updateIncidentRecord(SheTlIncidentmstnew incidentDto) throws Exception {
                // API endpoint for updating the incident
                String apiUrl = "/ehsacc/update";

                // Convert DTO to JSON manually
                String jsonPayload = compJsonManual(incidentDto);  // using the JSON builder we created earlier
                System.out.println("Json Payload: " + jsonPayload);

                // Send PUT request
                HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);

                // Print response code
                System.out.println("HTTP Status Code: " + res.getStatusCode());

                // Get the JSON response
                String jsonResponse = res.getBody();
                System.out.println("JSON Response: " + jsonResponse);

                // Convert JSON response back to DTO (you need a fromJson method)
                //SheTlIncidentmstnew updatedIncident = SheTlIncidentmstnew.fromJson(jsonResponse);

                return incidentDto;
            }
            public String compJsonAssignResponsibility(String keyid,String responseValue,String targetDate,String responsibilityRemarks) {
                StringBuilder sb = new StringBuilder();
                sb.append("{");

                sb.append("\"SINM_KEYID\":").append("\"").append(keyid).append("\"");
                sb.append(",\"SINM_RESPONSIBILITY\":").append("\"").append(responseValue).append("\"");
                sb.append(",\"SINM_RESPONSERMRKS\":").append("\"").append(responsibilityRemarks).append("\"");
                System.out.println("target Date "+targetDate);
                
                // Format LocalDateTime as string if not null
                if (targetDate != null) {
                    sb.append(",\"SINM_TARGETDATE\":").append("\"").append(CommonFunctions.pg_getDateTimeFromDate(targetDate)).append("\"");
                } else {
                    sb.append(",\"SINM_TARGETDATE\":").append("\"").append("").append("\"");
                }

                sb.append("}");
                return sb.toString();
            }
            
            
            
            public String assignResponsibility(String keyid, String responsevalue, String targetdate,SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {
                // API endpoint for updating the incident
                String apiUrl = "/ehsacc/assign-responsibility";

                // Convert DTO to JSON manually
                String jsonPayload = compJsonAssignResponsibility(keyid,responsevalue,targetdate,sheTlIncidentmstnew.getSinmResponsibilityRemarks());  // using the JSON builder we created earlier
                System.out.println("Json Payload: " + jsonPayload);

                // Send PUT request
                HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

                // Print response code
                System.out.println("HTTP Status Code: " + res.getStatusCode());

                // Get the JSON response
                String jsonResponse = res.getBody();
                System.out.println("JSON Response: " + jsonResponse);
                String result = "";
                // Convert JSON response back to DTO (you need a fromJson method)
                //SheTlIncidentmstnew updatedIncident = SheTlIncidentmstnew.fromJson(jsonResponse);
                if(res.getStatusCode()==200) 
                {
                	result = "Updated";
                }
                return result;
            }
            
            
            public String compJsonIncidentClosure(String keyid, String completedBy, String dateCompleted,
                                                  String revisedDate, String status, String actionTaken, String completedRemarks)
            {
            	System.out.println(revisedDate);
            	StringBuilder sb = new StringBuilder();
            	sb.append("{");

            	sb.append("\"keyId\":").append("\"").append(keyid).append("\"");
            	sb.append(",\"completedBy\":").append("\"").append(completedBy).append("\"");

            	if (dateCompleted != null) {
            	sb.append(",\"dateCompleted\":")
            	.append("\"").append(CommonFunctions.pg_getDateTimeFromDate(dateCompleted)).append("\"");
            	} else {
            		sb.append(",\"dateCompleted\":\"\"");
            	}

            	if (revisedDate != null && !revisedDate.isEmpty()) {
            		sb.append(",\"revisedDate\":")
            		.append("\"").append(CommonFunctions.pg_getDateTimeFromDate(revisedDate)).append("\"");
            	
            	}
            	sb.append(",\"status\":").append("\"").append(status).append("\"");
            	sb.append(",\"actionTaken\":").append("\"").append(actionTaken).append("\"");
            	sb.append(",\"completedRemarks\":").append("\"").append(completedRemarks).append("\"");

            	sb.append("}");

            	return sb.toString();
            }
            
            public String incidentClosure(String keyid,
                    String completedBy,
                    String dateCompleted,
                    String revisedDate,
                    String status,
                    String actionTaken,
                    SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {

					// API endpoint
					String apiUrl = "/ehsacc/incident-closure";
					
					// Convert DTO to JSON
					String jsonPayload = compJsonIncidentClosure(
					  keyid,
					  completedBy,
					  dateCompleted,
					  revisedDate,
					  status,
					  actionTaken,
					  sheTlIncidentmstnew.getSinmCompletedRemarks()
					);
					
					System.out.println("Json Payload: " + jsonPayload);
					
					// Send POST request
					HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
					
					System.out.println("HTTP Status Code: " + res.getStatusCode());
					
					String jsonResponse = res.getBody();
					System.out.println("JSON Response: " + jsonResponse);
					
					String result = "";
					
					if (res.getStatusCode() == 200) {
					result = "Updated";
					}
					
					return result;
					}
            
            public String compJsonVerifyIncident(String keyId, String verifiedBy, String dateVerified,
                    String verifyStatus, String verifyRemarks) {

				StringBuilder sb = new StringBuilder();
				sb.append("{");
				
				sb.append("\"keyId\":").append("\"").append(keyId).append("\"");
				sb.append(",\"verifiedBy\":").append("\"").append(verifiedBy).append("\"");
				
				if (dateVerified != null) {
				sb.append(",\"dateVerified\":").append("\"")
				.append(CommonFunctions.pg_getDateTimeFromDate(dateVerified)).append("\"");
				} else {
				sb.append(",\"dateVerified\":\"\"");
				}
				
				sb.append(",\"verifyStatus\":").append("\"").append(verifyStatus).append("\"");
				sb.append(",\"verifyRemarks\":").append("\"").append(verifyRemarks).append("\"");
				
				sb.append("}");
				
				return sb.toString();
				}
					
            
            public String verifyIncident(String keyId, String verifiedBy, String dateVerified,
                    String verifyStatus, SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {

					String apiUrl = "/ehsacc/verify";
					
					// Build JSON payload
					String jsonPayload = compJsonVerifyIncident(
					   keyId,
					   verifiedBy,
					   dateVerified,
					   verifyStatus,
					   sheTlIncidentmstnew.getSinmVerifiedRemarks()
					);
					
					System.out.println("Json Payload: " + jsonPayload);
					
					// Send PUT request
					HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);
					
					System.out.println("HTTP Status Code: " + res.getStatusCode());
					
					String jsonResponse = res.getBody();
					System.out.println("JSON Response: " + jsonResponse);
					
					String result = "";
					
					if (res.getStatusCode() == 200) {
					result = "Verified";
					}
					
					return result;
					}
            
            
            public String compJsonIncidentApproval(String keyId,String approvalBy,String approvalDate,
            		String approvalStatus,String approvalRemarks) {

                StringBuilder sb = new StringBuilder();
                sb.append("{");

                sb.append("\"keyId\":").append("\"").append(keyId).append("\"");
                sb.append(",\"approvalBy\":").append("\"").append(approvalBy).append("\"");

                if (approvalDate != null && !approvalDate.isEmpty()) {
                    sb.append(",\"approvalDate\":")
                      .append("\"").append(CommonFunctions.pg_getDateTimeFromDate(approvalDate)).append("\"");
                } else {
                    sb.append(",\"approvalDate\":\"\"");
                }

                sb.append(",\"approvalStatus\":").append("\"").append(approvalStatus).append("\"");

                if (approvalRemarks != null) {
                    sb.append(",\"approvalRemarks\":").append("\"").append(approvalRemarks).append("\"");
                } else {
                    sb.append(",\"approvalRemarks\":\"\"");
                }

                sb.append("}");
                return sb.toString();
            }
            
            
            public String incidentApproval(String keyId,String approvalBy,String approvalDate,String approvalStatus,
                    SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {

                // API endpoint
                String apiUrl = "/ehsacc/approve";

                // Convert DTO to JSON
                String jsonPayload = compJsonIncidentApproval(
                        keyId,
                        approvalBy,
                        approvalDate,
                        approvalStatus,
                        sheTlIncidentmstnew.getSinmApprovalRemarks()
                );

                System.out.println("Json Payload: " + jsonPayload);

                // Send POST request
                HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

                System.out.println("HTTP Status Code: " + res.getStatusCode());

                String jsonResponse = res.getBody();
                System.out.println("JSON Response: " + jsonResponse);

                String result = "";

                if (res.getStatusCode() == 200) {
                    result = "Updated";
                }

                return result;
            }
}
