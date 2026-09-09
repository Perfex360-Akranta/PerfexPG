/*Created By: Siddharth.A*/
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.OplTlCategorymst;
import com.akranta.tpm.service.OplTlCategorymstService;
import com.akranta.tpm.service.impl.OplCummulativeServiceImpl;
import com.akranta.tpm.service.impl.OplTlCategorymstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class OplCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	OplTlCategorymstService oplTlCategorymstService;
	
    public OplCategoryServlet() {
        super();
        /*CommonMessage.debugMsg(" initialising Opl Category servlet ....");
        try 
        {
        	oplTlCategorymstService = new OplTlCategorymstServiceImpl();
			
		}
        catch (Exception e) 
		{
			e.printStackTrace();
		}
        */
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
    	String action = UIUtils.getActionPart(request);
		try {
			oplTlCategorymstService = (OplTlCategorymstServiceImpl)UIUtils.getServiceObject(request,"OplTlCategorymstServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
	
		//AdmTlUsermst user = UIUtils.getLoginUser(request);
	
		
		HttpSession httpSession = request.getSession(false);
		
		if (action.equals("createCategory_input.oplcat")) 
		{ 
			if( httpSession != null )
			{	
				OplTlCategorymst oplTlCategorymst = new OplTlCategorymst();
			
				List<OplTlCategorymst> oplCategoryList = (List<OplTlCategorymst>)httpSession.getAttribute("oplCategoryList");
				String mode = "Create";
				String oplcatKeyid = request.getParameter("rowid");
				CommonMessage.debugMsg(" oplcatKeyid in serv "+oplcatKeyid);
				
				if( oplcatKeyid != null)
				{	
					oplTlCategorymst = (OplTlCategorymst)httpSession.getAttribute(oplcatKeyid);
					//List<OplTlCategorymst> oplCategoryList = (List<OplTlCategorymst>)httpSession.getAttribute("oplCategoryList");
					try
						{
							
						}
						catch(Exception e)
						{
							CommonMessage.debugMsg("Error While selecting oplcatKeyid :"+e.getMessage());
						}
					httpSession.setAttribute("oplt", oplTlCategorymst);	
				}
				
				if( oplTlCategorymst == null)
				{
					oplTlCategorymst = new  OplTlCategorymst();
				}
				else
				{
					mode = "Update";
				}
				//oplTlCategorymst.setOplcCreatedby(user.getUsrm_ccno());
			
				//request.setAttribute("oplTlCategorymst", oplTlCategorymst);

				//saveOplCategory(request, response, oplFormBean);
			}	
    	}
		else if(action.equals("oplCategory_input.oplcat"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/OplCategory.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("oplCategory_getCol.oplcat"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "oplCategoryTable"));
			}
	   
			catch (Exception e) 
			{
					e.printStackTrace();
			}
		}
		
		
		else if(action.equals("oplCategory_getData.oplcat"))
		{
			try
			{	
				
				String pillarKeyid = request.getParameter("pillarRowid");
				List<Object> oplCategoryList;
				oplCategoryList =(List<Object>) httpSession.getAttribute("OplCategoryServlet"+pillarKeyid); // change is required
				PrintWriter out = response.getWriter();
				if( ! (oplCategoryList != null && oplCategoryList.size() > 0) )
				{	
					oplCategoryList = oplTlCategorymstService.getOplCategories(pillarKeyid);
				}
				
				httpSession.setAttribute("OplCategoryServlet"+pillarKeyid, oplCategoryList);
				
				JSONObject oplCategoryData = convertToCategoryTblObject(oplCategoryList);
				
				out.println(oplCategoryData);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Opl Category getdata Exception"+e.getMessage());
			}
			
		}
		
	
		else if(action.equals("oplCategory_deleteData.oplcat"))
		{
			PrintWriter out=response.getWriter();
			
			try
			{	

				OplTlCategorymst oplTlCategorymst = new OplTlCategorymst();
				
				UIUtils.setBeanProperties(oplTlCategorymst, request);
				
				String oplcPillarId = oplTlCategorymst.getOplcTpmpillarid();
				CommonMessage.debugMsg("oplcPillarId"+oplcPillarId);
				List<OplTlCategorymst> oplTlDelCategoryList =  new ArrayList<OplTlCategorymst>();
				if( oplcPillarId != null)
					oplTlDelCategoryList = (List<OplTlCategorymst>)httpSession.getAttribute("OplCategoryServlet"+oplcPillarId); 
	    		
	    		String oplKeyid = oplTlCategorymst.getOplcKeyid();
	    		
	    		oplTlCategorymst = getOplTlCategorymstFromListTest(oplTlDelCategoryList,oplKeyid);
				
	    		oplTlCategorymstService.deleteAllCategoryNames(oplTlCategorymst);
	    		
	    		httpSession.setAttribute("OplCategoryServlet"+oplcPillarId, oplTlDelCategoryList);
	    		
  	    		JSONObject oplCategoryData = new JSONObject();// UIUtils.fromTpmModel(oplTlCategorymst);
		
	    		oplCategoryData.put("oplcCategoryid", oplKeyid);
	    		
	    		out.println(oplCategoryData);
			
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Deletion Error Opl Category Exception"+e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
				
			}
			
		}
		
		else if(action.equals("oplPillar_input.oplcat"))
		{
			
		}
		
		else if(action.equals("oplPillar_getCol.oplcat"))
		{
			PrintWriter out = response.getWriter();
		
			String oplCategoryColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "oplPillarTable");
			out.print(oplCategoryColM);
		}
		
		else if(action.equals("oplPillar_getData.oplcat"))
		{
			try
			{	
				CommonFilter  commonFilter = getFilterValues1(request);	
				
				PrintWriter out = response.getWriter();
				List<String []> oplPillarNameList  = oplTlCategorymstService.getAllPillarNames(commonFilter);
				JSONObject oplPillarData = UIUtils.convertToJqGridTableObject(oplPillarNameList,request,0,0);
				out.println(oplPillarData);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Opl Category getData Exception"+e.getMessage());
			}
		}
		
		else if(action.equals("createCategory_save.oplcat"))
		{
			try
			{	
				OplFormBean oplFormBean = new  OplFormBean();
				UIUtils.displayRequestParamsValue(request);
				
				saveOplCategory(request, response, oplFormBean);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("opl category save  Exception"+e.getMessage());	
			}
		}
		
		 
		if (action.equals("createCategory_input.oplcat")) 
		{
				String dispatchUrl = "/pages/OplCategory.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
				rd.forward(request, response); 
		}
		 
	}
    
    private CommonFilter getFilterValues1(HttpServletRequest request)
    {
    	CommonFilter commonFilter = new CommonFilter();
    	
    	return commonFilter;
    }
    
  	private JSONObject convertToCategoryTblObject(List<Object> oplCategoryList)
  	{
   		
  		JSONObject opltableDataObject = new JSONObject();
 		
  		opltableDataObject.put("page", 1); //current page
  		opltableDataObject.put("total",10); // total page
  		opltableDataObject.put("records", (oplCategoryList.size())); //total records
		
		JSONArray rowArr = new JSONArray(); 
     
  		for(Object tpmModel: oplCategoryList)
  		{
  			
  			OplTlCategorymst oplTlCategorymst = (OplTlCategorymst)tpmModel;
	  		JSONObject rowObj =new JSONObject();
		    rowObj.put("id",+1);
	        
	        JSONArray cell = new  JSONArray();
	        cell.put(oplTlCategorymst.getOplcKeyid());
	        cell.put(oplTlCategorymst.getOplcName());
	        cell.put(oplTlCategorymst.getOplcCode());
	        rowObj.put("cell",cell);
	        
	        rowArr.put(rowObj);
		}
  		
  			opltableDataObject.put("rows", rowArr);
	      
  	        return opltableDataObject;
   	}
    
  	
     private void saveOplCategory(HttpServletRequest request, HttpServletResponse response,OplFormBean oplFormBean  ) throws IOException{
    
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		OplTlCategorymst newOplTlCategorymst = new OplTlCategorymst();
    		newOplTlCategorymst =(OplTlCategorymst)UIUtils.setBeanProperties((Object)newOplTlCategorymst,request);
    		
    		String oplcPillarId = newOplTlCategorymst.getOplcTpmpillarid();
    		List<Object> oplTlCategoryList = null ;
    		
    		if( oplcPillarId != null)
    			oplTlCategoryList = (List<Object>)httpSession.getAttribute("OplCategoryServlet"+oplcPillarId); 
    		
    		String oplKeyid = newOplTlCategorymst.getOplcKeyid();
    		OplTlCategorymst existOplTlCategorymst = null;
    		
    		if( oplKeyid != null  && oplTlCategoryList != null )
    			existOplTlCategorymst =getOplTlCategorymstFromList(oplTlCategoryList, oplKeyid);
    		
    		newOplTlCategorymst.setOplcCreatedby(user.getUsrm_ccno());
			
    		
    		oplFormBean =(OplFormBean) UIUtils.setBeanProperties((Object)oplFormBean,request);
			
			try
			{
				if( newOplTlCategorymst.getOplcKeyid() == null )
				{	
					existOplTlCategorymst =	oplTlCategorymstService.create(newOplTlCategorymst,existOplTlCategorymst,oplFormBean);
				}	
				else
				{
					existOplTlCategorymst = oplTlCategorymstService.update(newOplTlCategorymst,existOplTlCategorymst,oplFormBean);
				}
				
				oplTlCategoryList.add(existOplTlCategorymst);
				
				httpSession.setAttribute("OplCategoryServlet"+oplcPillarId, oplTlCategoryList);
					
				JSONObject mode = new JSONObject();
				mode.put("formMode",oplFormBean.getFormActionMode());
						
				//out.print()	
				
			}
			catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "OplCreationExceptions");
				errMessage.put("formMode",oplFormBean.getFormActionMode());
				errMessage.put("tpmException", "Data Not Saved");
				out.print(errMessage.toString());
				
			}
			
			catch(Exception e)
			{
				CommonMessage.debugMsg(" in save gete. " + e.getMessage());
				CommonMessage.debugMsg(" in save gete. " + e.getStackTrace());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}	
    }
     
     private OplTlCategorymst getOplTlCategorymstFromList(List<Object> oplCategoryList, String oplcKeyid)
     {
    	 int index = 0;
    	 for( Object object:oplCategoryList)
    	 {
    		 
    		 OplTlCategorymst oplTlCategorymst= (OplTlCategorymst)object;
    		 if( oplTlCategorymst.getOplcKeyid().equals(oplcKeyid) ){
    			 oplCategoryList.remove(index);
    			 return oplTlCategorymst;
    		 }	 
    		 index++;
    	 }
    	 return null; 
     }

     private OplTlCategorymst getOplTlCategorymstFromListTest(List<OplTlCategorymst> oplCategoryList, String oplcKeyid)
     {
    	 int index = 0;
    	 for( OplTlCategorymst oplTlCategorymst:oplCategoryList)
    	 {
    		 if( oplTlCategorymst.getOplcKeyid().equals(oplcKeyid) ){
    			 oplCategoryList.remove(index);
    			 return oplTlCategorymst;
    		 }	 
    		 index++;
    	 }
    	 return null; 
     }
     

    
}