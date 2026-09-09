package com.akranta.tpm.service.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
//import java.util.Map;

import com.akranta.tpm.Exceptions.SessionExpiredException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonMessage;
public class Api {
	
	
	private  String JwtToken;
	public Api(String JwtToken) {
        this.JwtToken = JwtToken;
    }

	
//	private static final Logger logger = Logger.getLogger(HttpClientWrapper.class.getName());
//    private static final int DEFAULT_TIMEOUT = 30000; // 30 seconds
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    //private static final String BASE_URL = "http://localhost:9090/api";
    
//   private static final String BASE_URL = "http://10.35.52.99:8080/perfex_sb_uat/api";
   //private static final String BASE_URL = "http://10.35.52.99:9090/perfex_sb/api";
   // private static final String BASE_URL = "http://10.35.52.99:9090/perfex_sb/api";
//   private static final String BASE_URL = "http://10.35.52.162:9090/perfex_sb/api";
//   private static final String BASE_URL = "http://10.35.52.162:9090/perfex_sb_uat/api";
    
    public HttpResponse makeRequest(String apiUrl, String method, String payload) throws IOException {
        HttpURLConnection connection = null;
        try {
        	String BASE_URL = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "BASE_URL");
            URL url = URI.create(BASE_URL+apiUrl).toURL();
            connection = (HttpURLConnection) url.openConnection();
            
            // Set basic connection properties
            connection.setRequestMethod(method);
//            connection.setConnectTimeout(connectTimeout);
//            connection.setReadTimeout(readTimeout);
            
            // Set default headers
            connection.setRequestProperty("Content-Type", DEFAULT_CONTENT_TYPE);
            connection.setRequestProperty("Accept", DEFAULT_CONTENT_TYPE);
            
            // Set output for methods that can have a body
            if ("POST".equals(method) || "PUT".equals(method) || "PATCH".equals(method)) {
                connection.setDoOutput(true);
                
                // Send payload if provided
                if (payload != null) {
                    try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                        os.flush();
                    }
                }
            }
            
            int responseCode = connection.getResponseCode();
            CommonMessage.debugMsgUrl(String.format("%s %s - Response Code: %d", method, apiUrl, responseCode));
            
            String responseBody;
            if (responseCode >= 200 && responseCode < 300) {
                responseBody = readResponse(connection.getInputStream());
                CommonMessage.debugMsgUrl("Success Response: " + responseBody);
            } else {
                responseBody = readResponse(connection.getErrorStream());
                CommonMessage.debugMsgUrl(String.format("Error Response (%d): %s", responseCode, responseBody));
            }
            
            return new HttpResponse(responseCode, responseBody);
            
        } catch (IOException e) {
            CommonMessage.debugMsgUrl("IO Exception during HTTP request: " + e.getMessage());
            throw e;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public HttpResponse makeAuthRequest(String apiUrl, String method, String payload) throws IOException  {
        HttpURLConnection connection = null;
        try {
        	String BASE_URL = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "BASE_URL");
            URL url = URI.create(BASE_URL+apiUrl).toURL();
           // URL url = URI.create(BASE_URL+apiUrl).toURL();
            connection = (HttpURLConnection) url.openConnection();
            
            // Set basic connection properties
            connection.setRequestMethod(method);
//            connection.setConnectTimeout(connectTimeout);
//            connection.setReadTimeout(readTimeout);
            
            // Set default headers
            connection.setRequestProperty("Content-Type", DEFAULT_CONTENT_TYPE);
            connection.setRequestProperty("Accept", DEFAULT_CONTENT_TYPE);
          //  CommonMessage.debugMsg("JwtToken: " + JwtToken);
            if (JwtToken != null && !JwtToken.isEmpty()) {
                connection.setRequestProperty("Authorization", "Bearer " + JwtToken);
            }
            
            // Set output for methods that can have a body
            if ("POST".equals(method) || "PUT".equals(method) || "PATCH".equals(method)) {
                connection.setDoOutput(true);
                
                // Send payload if provided
                if (payload != null) {
                	payload = payload.replace("\r", "\\r")
                            .replace("\n", "\\n");
                    try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                        os.flush();
                    }
                }
            }
            
            int responseCode = connection.getResponseCode();
            CommonMessage.debugMsgUrl(String.format("%s %s - Response Code: %d", method, apiUrl, responseCode));
            
            String responseBody;
            if (responseCode >= 200 && responseCode < 300) {
                responseBody = readResponse(connection.getInputStream());
                //CommonMessage.debugMsg("Success Response: " + responseBody);
            } else {
                responseBody = readResponse(connection.getErrorStream());
                CommonMessage.debugMsgUrl(String.format("Error Response (%d): %s", responseCode, responseBody));
            }
            //HttpURLConnection.HTTP_UNAUTHORIZED
//            if (responseCode == 403) {
//                throw new SessionExpiredException("API_SESSION_EXPIRED");
//            }
            
            return new HttpResponse(responseCode, responseBody);
            
        } catch (IOException e) {
            CommonMessage.debugMsgUrl("IO Exception during HTTP request: " + e.getMessage());
            throw e;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    
     // Reads response from input stream
    private String readResponse(java.io.InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return "";
        }
        
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }
}
