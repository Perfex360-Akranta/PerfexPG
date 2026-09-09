package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.GenTlFunctionalLocnBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.service.GenTlFunctionalLocnService;
import com.akranta.tpm.service.impl.GenTlCompanymstServiceImpl;
import com.akranta.tpm.service.impl.GenTlFunctionalLocnServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


public class GenTlFunctionalLocnServlet extends HttpServlet
{

	
	private static final long serialVersionUID = 1L;
	GenTlFunctionalLocnService genTlFunctionalLocnService;

	public GenTlFunctionalLocnServlet() throws Exception
	{	
		//genTlFunctionalLocnService = new GenTlFunctionalLocnServiceImpl();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		
		HttpSession httpSession = request.getSession(false);
		
		String action = UIUtils.getActionPart(request);
		try {
			genTlFunctionalLocnService = (GenTlFunctionalLocnServiceImpl)UIUtils.getServiceObject(request,"GenTlFunctionalLocnServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		if( action.equals("FunctionalLocn_input.fnlocn"))
		{
			try
			{	
				CommonMessage.debugMsg("Inside Functional location");
				PrintWriter out = response.getWriter();
				String parentId=(String)httpSession.getAttribute("elementId");
	    		CommonMessage.debugMsg("elementid="+parentId);
	    		GenTlFunctionallocn genTlFunctionallocn =new GenTlFunctionallocn();
	    		genTlFunctionallocn.setFnlnParentid(parentId);
	    		
	    		request.setAttribute("genTlFunctionallocn","genTlFunctionallocn");
				
				List<String[]> FuncLocnList = genTlFunctionalLocnService.getSprs();
				
				JSONObject funcLocnData = UIUtils.convertToJqGridTableObject(FuncLocnList,request,0,0);
				//List<GenTlFunctionallocn> genTlFnLocnList =	populateSprPckList(FuncLocnList);
				
			//	CommonMessage.debugMsg("genTlFnLocnList.size()"+genTlFnLocnList.size());	
				
			//	httpSession.setAttribute("genTlFunctionallocn",genTlFnLocnList);	
				//funcLocnData.put("StandardId", StandardId);
	    		
	    		out.println(funcLocnData);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Get Data Exception"+e.getMessage());
			}
		}
		 else if(action.equals("FunctionalLocn_save.fnlocn"))
		 {
			 
			 GenTlFunctionalLocnBean genTlFunctionalLocnBean = new GenTlFunctionalLocnBean();
				String funcLocns = request.getParameter("funcLocns");
	    		CommonMessage.debugMsg("funcLocns="+funcLocns);
	    	
	    		httpSession.setAttribute("funcLocnsStandardId", funcLocns);
	    		
			 saveFncLocn(request, response, genTlFunctionalLocnBean)	;
		
		 }
	}
		

		
		private void saveFncLocn(HttpServletRequest request, HttpServletResponse response,GenTlFunctionalLocnBean  genTlFunctionalLocnBean) throws Exception
	    {
	    	System.out.print("Inside Save Fn locn");
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		
	    		List<GenTlFunctionallocn> genTlFunclocnList =null;
	    		GenTlFunctionallocn genTlFunctionallocn = new GenTlFunctionallocn();
	    		String genTlFuncLocnStr =  request.getParameter("SparesPickupTbl");
	    		CommonMessage.debugMsg("genTlFuncLocnStr"+genTlFuncLocnStr);
	    		//String fnlnParentid=(String)httpSession.getAttribute("UnschedActivityServletElementId");
	    		//CommonMessage.debugMsg("fnlnParentid="+fnlnParentid);
	    		//genTlFunctionallocn.setFnlnParentid(fnlnParentid);
	    		String FlnStandardId = (String) httpSession.getAttribute("funcLocnsStandardId");
	    		JSONArray genTlFuncLocnJSON =null;
	    	
	    		List<GenTlFunctionallocn> existGenTlFunctionallocn= (List<GenTlFunctionallocn>)httpSession.getAttribute(FlnStandardId);
	    		CommonMessage.debugMsg("sessiion="+existGenTlFunctionallocn);
				try
				{		
					existGenTlFunctionallocn =	genTlFunctionalLocnService.save(existGenTlFunctionallocn);
				
					
					JSONObject successData = new JSONObject();
					successData.put("msg","Data Saved Successfully");
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					
					out.print(returnData.toString());					
				}
			
				
				catch(Exception e)
				{
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					JSONObject tpmException = new JSONObject();
					JSONObject err = new JSONObject();
					tpmException.put("msg", "Data Not Saved");
					tpmException.put("errMsg" , e.getMessage());
					err.put("tpmException",tpmException);
					out.print(err.toString());
				}
	    	}	
	    }

	
	
}
