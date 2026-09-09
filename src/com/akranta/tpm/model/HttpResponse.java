package com.akranta.tpm.model;

public class HttpResponse {

	 private final int statusCode;
     private final String body;
     
     public HttpResponse(int statusCode, String body) {
         this.statusCode = statusCode;
         this.body = body;
     }
     
     public int getStatusCode() {
         return statusCode;
     }
     
     public String getBody() {
         return body;
     }
     
     public boolean isSuccess() {
         return statusCode >= 200 && statusCode < 300;
     }
     
     public boolean isClientError() {
         return statusCode >= 400 && statusCode < 500;
     }
     
     public boolean isServerError() {
         return statusCode >= 500;
     }
     
     @Override
     public String toString() {
         return String.format("HttpResponse{statusCode=%d, body='%s'}", statusCode, body);
     }

}
