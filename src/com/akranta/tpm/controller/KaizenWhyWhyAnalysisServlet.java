package com.akranta.tpm.controller;

import java.io.IOException;
import java.util.Date;

import com.akranta.tpm.utils.CommonMessage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class KaizenWhyWhyAnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KaizenWhyWhyAnalysisServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	process(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	process(request, response); 
	}
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		
		String uri = request.getRequestURI();
		/* * uri is in this form: /contextName/resourceName, * for example: /app01a/Product_input.action. * However, in the case of a default context, the * context name is empty, and uri has this form * /resourceName, e.g.: /Product_input.action */
		int lastIndex = uri.lastIndexOf("/"); 
		String action = uri.substring(lastIndex + 1);
		CommonMessage.debugMsg(" action " + action);
		
		
		if (action.equals("KaizenWhyWhyAnalysis_input.KaizWhyAlys")) 
		{ CommonMessage.debugMsg("input the jsp");
			// there is nothing to be done
		
		}
		else if( action.equals("KaizenWhyWhyAnalysis_view.KaizWhyAlys"))
		{	
			
	    }
		
		String dispatchUrl = null; 
		
		if (action.equals("KaizenWhyWhyAnalysis_input.KaizWhyAlys")) 
		{CommonMessage.debugMsg("Out the jsp");
			dispatchUrl = "/pages/whywhyAnalysis.jsp";
		}
		else if(action.equals("KaizenWhyWhyAnalysis_view.KaizWhyAlys"))
		{	
			
		}
		
		
		CommonMessage.debugMsg(" dispatchUrl " + dispatchUrl);
		if (dispatchUrl != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
			CommonMessage.debugMsg(" response " + response); 
		}
			
	}

}
