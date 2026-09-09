package com.akranta.tpm.controller;

import java.io.IOException;
import java.util.Enumeration;

import com.akranta.tpm.utils.CommonMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MultiSelectServlet
 */

public class MultiSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	 
    	String action = UIUtils.getActionPart(request);
    	 CommonMessage.debugMsg("inside multiselect servlet  :" + action);
    	if( action.equals("mutliSelect_input.mselect") ){
    		Enumeration<String> params = request.getParameterNames();
    		while(params.hasMoreElements())
    		{
    			String paramName = params.nextElement();
    			String value = request.getParameter(paramName);
    			CommonMessage.debugMsg(" paramName " + paramName + " - " +  value);
    			request.setAttribute(paramName, value );
    		}
    		UIUtils.forwardRequest(request, response, "pages/MultiSelectPopup.jsp");
    	}
    	/**pop for ToolsPickUp pop for JHCLIT STANDARD***/
    	 else if( action.equals("toolpop_input.mselect") ){
    		Enumeration<String> params = request.getParameterNames();
    		while(params.hasMoreElements())
    		{
    			String paramName = params.nextElement();
    			String value = request.getParameter(paramName);
    			CommonMessage.debugMsg(" paramName " + paramName + " - " +  value);
    			request.setAttribute(paramName, value );
    		}
    		UIUtils.forwardRequest(request, response, "pages/jhclit/ToolsPickUp.jsp");
    	}
    	/**pop for subform pop for Common ***/
    	 else  if( action.equals("subForm_input.mselect") ){
    		Enumeration<String> params = request.getParameterNames();
    		while(params.hasMoreElements())
    		{
    			String paramName = params.nextElement();
    			String value = request.getParameter(paramName);
    			CommonMessage.debugMsg(" paramName " + paramName + " - " +  value);
    			request.setAttribute(paramName, value );
    		}
    		UIUtils.forwardRequest(request, response, "pages/SubFormPopup.jsp");
    	}
    	
    	 else  if( action.equals("funcnLocn_input.mselect") ){
     		Enumeration<String> params = request.getParameterNames();
     		while(params.hasMoreElements())
     		{
     			String paramName = params.nextElement();
     			String value = request.getParameter(paramName);
     			CommonMessage.debugMsg(" paramName " + paramName + " - " +  value);
     			request.setAttribute(paramName, value );
     		}
     		UIUtils.forwardRequest(request, response, "pages/funcnLocnPopup.jsp");
     	}
     
   	 else if( action.equals("multiSelectSave_input.mselect") ){
   		Enumeration<String> params = request.getParameterNames();
   		while(params.hasMoreElements())
   		{
   			String paramName = params.nextElement();
   			String value = request.getParameter(paramName);
   			CommonMessage.debugMsg(" MultiSelectSave paramName " + paramName + " - " +  value);
   			request.setAttribute(paramName, value );
   		}
   		UIUtils.forwardRequest(request, response, "pages/multiSelectSave.jsp");
   	}     	
    	
    	
    	 else  if( action.equals("subFormMenu_input.mselect") ){
     		Enumeration<String> params = request.getParameterNames();
     		UIUtils.forwardRequest(request, response, "tiles/subformbanner.jsp");
     	}    	
    }
}
