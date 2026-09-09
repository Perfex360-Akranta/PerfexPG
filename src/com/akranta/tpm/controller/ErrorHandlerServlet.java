package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorHandlerServlet extends HttpServlet {
	public void init(){
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 Throwable throwable = (Throwable)
	      request.getAttribute("jakarta.servlet.error.exception");
	      Integer statusCode = (Integer)
	      request.getAttribute("jakarta.servlet.error.status_code");
	      String servletName = (String)
	      request.getAttribute("jakarta.servlet.error.servlet_name");
	      if (servletName == null){
	         servletName = "Unknown";
	      }
	      String requestUri = (String)
	      request.getAttribute("jakarta.servlet.error.request_uri");
	      if (requestUri == null){
	         requestUri = "Unknown";
	      }

	      // Set response content type
	      response.setContentType("text/html");
	 
	      PrintWriter out = response.getWriter();
		  String title = "Error/Exception Information";
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	        "<html>\n" +
	        "<head><title>" + title + "</title></head>\n" +
	        "<LINK REL='SHORTCUT ICON'      HREF='images/faviconTPM.png'> ");
	      out.print("<style type='text/css'> \n .body-error{ margin: 7% auto 0; max-width: 390px; min-height: 180px; padding: 30px 0 15px;bgcolor:cyan;}.errMsg{left: 380px;position: absolute;top: 350px;z-index: 3;}</style>");
	      out.print("\n<body class='body-error' >\n");
	      
	      out.println("<div >");
	      if (throwable == null && statusCode == null){
	         out.println("<h2>Error information is missing</h2>");
	         //out.println("Please return to the <a href=\"" + 
	          // response.encodeURL("http://localhost:8080/") + 
	           //"\">Home Page</a>.");
	      }else if (statusCode != null){
	    	 //out.println("<img src='images/tpm-logo1.jpg' />");
	    	 out.println("<img src='images/error404--3.jpg' />");
	         out.println("<span class='errMsg'>" +  statusCode +": This is an ERROR <br>");
	         if( statusCode == 404)
	        	 out.println("<br>Requested URL is not found in this Server.");
	      }else{
	         out.println("<h2>Error information</h2></span>");
	         //out.println("Servlet Name : " + servletName + 
	          //                   "</br></br>");
	         //out.println("Exception Type : " + 
	          //                   throwable.getClass( ).getName( ) + 
	           //                  "</br></br>");
	         //out.println("The request URI: " + requestUri + 
	          //                   "<br><br>");
	         //out.println("The exception message: " + 
	          //                       throwable.getMessage( ));
	      }
	      out.println("</div >");
	      out.println("</body>");
	      out.println("</html>");
	}	
	
}
