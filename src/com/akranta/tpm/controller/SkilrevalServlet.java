
package com.akranta.tpm.controller;



import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Date;
//import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import net.sf.json.JSONArray;

//import com.akranta.tpm.model.ComboBox;
//import com.akranta.tpm.model.ComboFilter;
//import com.akranta.tpm.bean.CompanyBean;
//import com.akranta.tpm.model.Company;
//import com.akranta.tpm.service.impl.CompanyServiceImpl;


public class SkilrevalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkilrevalServlet() {
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
		PrintWriter out = response.getWriter();
		//CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
		
		if (action.equals("skl_input.slv")) 
		{ CommonMessage.debugMsg("input the jsp");
			// there is nothing to be done
		
		}
		
		
		String dispatchUrl = null; 
		
		if (action.equals("skl_input.slv")) 
		{CommonMessage.debugMsg("Out the jsp");
			dispatchUrl = "/pages/Skillrevaluation.jsp";
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

