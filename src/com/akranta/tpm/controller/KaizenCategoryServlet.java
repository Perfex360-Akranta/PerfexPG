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
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.dao.sql.KznTlMstSql;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.KznTlCategorymst;
import com.akranta.tpm.model.KznTlSubcategorymst;
import com.akranta.tpm.service.KznTlCategorymstService;
import com.akranta.tpm.service.impl.KznTlCategorymstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


public class KaizenCategoryServlet extends HttpServlet {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KznTlCategorymstService kznTlCategorymstService;
	public KaizenCategoryServlet() throws Exception {
	        super();
	        // TODO Auto-generated constructor stub
	     //   kznTlCategorymstService = new KznTlCategorymstServiceImpl();
	    }
	    
	    
	    /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			processRequest(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			processRequest(request, response);
		}
		
		protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException 
		{
			
				String action = UIUtils.getActionPart(request);
				try {
					kznTlCategorymstService = (KznTlCategorymstServiceImpl)UIUtils.getServiceObject(request,"KznTlCategorymstServiceImpl");
					
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}
			   HttpSession httpSession = request.getSession(false);
			   
			
				if( action.equals("kaizen_category.kcat") )
			   {	
				
					RequestDispatcher rd = request.getRequestDispatcher("/pages/kaizencategory.jsp"); 
					rd.forward(request, response); 
					
				}
			   
				else if(action.equals("kznCategory_input.kcat"))
				{
					RequestDispatcher rd = request.getRequestDispatcher("/pages/kaizencategory.jsp"); 
					rd.forward(request, response); 
				}
				else if( action.equals("kznCategory_getCol.kcat"))
				{
					try
					{	
						PrintWriter out = response.getWriter();
						out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznCategoryTable"));
					}
			   
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				
				
				else if(action.equals("kznCategory_getData.kcat"))
				{
					try
					{	
						CommonMessage.debugMsg("Inside Get data categ");
						String pillarKeyid = request.getParameter("pillarRowid");
						CommonMessage.debugMsg("pillarKeyid ="+pillarKeyid);
						PrintWriter out = response.getWriter();
						List<Object> kznCategoryList;
						if(UIUtils.isValidKeyId(pillarKeyid))
						{
						//	kznCategoryList =(List<Object>) httpSession.getAttribute("KznCategoryServlet"+pillarKeyid); // change is required
					//	CommonMessage.debugMsg("kznCategoryList size ="+kznCategoryList.size());
						
					//	if( ! (kznCategoryList != null && kznCategoryList.size() > 0) )
					//	{	
							CommonMessage.debugMsg("In servlet bfeore service"+pillarKeyid);
							kznCategoryList = kznTlCategorymstService.getKznCategories(pillarKeyid);
					//	}
						
							httpSession.setAttribute("KznCategoryServlet"+pillarKeyid, kznCategoryList);
					
						JSONObject kznCategoryData = convertToCategoryTblObject(kznCategoryList);
						CommonMessage.debugMsg("kznCategoryData ="+kznCategoryData);
						out.println(kznCategoryData);
						}
					}
					catch(Exception e)
					{
						CommonMessage.debugMsg("Kzn Category getdata servlet Exception"+e.getMessage());
					}
					
				}
				
			
				else if(action.equals("kznCategory_deleteData.kcat"))
				{
					PrintWriter out = response.getWriter();
					try
					{	
						
						KznTlCategorymst kznTlCategorymst = new KznTlCategorymst();
						UIUtils.setBeanProperties(kznTlCategorymst, request);
						String kctmTpmpillarid = kznTlCategorymst.getKctmTpmpillarid();
						CommonMessage.debugMsg("kctmTpmpillarid"+kctmTpmpillarid);
						List<KznTlCategorymst> kznTlDelCategoryList =  new ArrayList<KznTlCategorymst>();
						if( kctmTpmpillarid != null)
							kznTlDelCategoryList = (List<KznTlCategorymst>)httpSession.getAttribute("KznCategoryServlet"+kctmTpmpillarid); 
			    		
			    		String kctmKeyid = kznTlCategorymst.getKctmKeyid();
			    		
			    		kznTlCategorymst = getKznlTlCategorymstFromListTest(kznTlDelCategoryList,kctmKeyid);
						
			    		kznTlCategorymstService.deleteAllCategoryNames(kznTlCategorymst);
			    		
			    		httpSession.setAttribute("KznCategoryServlet"+kctmTpmpillarid, kznTlDelCategoryList);
			    		
		  	    		JSONObject kznCategoryData = new JSONObject();// UIUtils.fromTpmModel(kznTlCategorymst);
				
		  	    		kznCategoryData.put("kctmKeyid", kctmKeyid);
			    		
			    		out.println(kznCategoryData);
					
					}
					catch(Exception e)
					{
						CommonMessage.debugMsg("Deletion Error Kzn Category Exception"+e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Deleted");
						out.print(err.toString());
						
					}
					
				}
				
				else if(action.equals("kznPillar_input.kcat"))
				{
					
				}
				
				else if(action.equals("kznPillar_getCol.kcat"))
				{
					PrintWriter out = response.getWriter();
					String kznCategoryColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznPillarTable");
					out.print(kznCategoryColM);
				}
				
				else if(action.equals("kznPillar_getData.kcat"))
				{
					try
					{	
						PrintWriter out = response.getWriter();
						List<String []> kznPillarNameList  = kznTlCategorymstService.getAllPillarNames();
						JSONObject kznPillarData = UIUtils.convertToJqGridTableObject(kznPillarNameList,request,0,0);
						out.println(kznPillarData);
					}
					catch(Exception e)
					{
						CommonMessage.debugMsg("kzn Pillar getData Exception"+e.getMessage());
					}
				}
				
				
				else if(action.equals("subCategory_input.kcat"))
				{
					String dispatchUrl = "/pages/MultiSelectPopup.jsp";
					UIUtils.forwardRequest(request, response, dispatchUrl);
					
				}
				
				else if(action.equals("subCategory_getCol.kcat"))
				{
					PrintWriter out = response.getWriter();
					String kznSubCategoryColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznSubCategoryTable");
					CommonMessage.debugMsg("colheader ="+kznSubCategoryColM);
					out.print(kznSubCategoryColM);
					
				}
				
				else if(action.equals("subCategory_getData.kcat"))
				{
					CommonMessage.debugMsg("Inside Sub category getData");
					PrintWriter out = response.getWriter();
					
					String kscmKctmKeyId=request.getParameter("kscmKctmKeyId");
					CommonMessage.debugMsg("ksmKeyId ="+kscmKctmKeyId);
					try 
					{
						List<Object> subCategoryList  = kznTlCategorymstService.getAllSubCategory(kscmKctmKeyId);
						CommonMessage.debugMsg("subCategoryList ="+subCategoryList.size());
						if(subCategoryList.size()<=0)
						{
							String msg="No Records Found";
							JSONObject returnData= new JSONObject();
						    returnData.put("dataNotExist", msg);				
							out.print(returnData.toString());
						}
						httpSession.setAttribute("KznSubcategoryServlet"+kscmKctmKeyId, subCategoryList);
						JSONObject subCategoryData = convertToSubCategoryTblObject(subCategoryList);
						out.println(subCategoryData);
					}
					
					catch(Exception e)
					{
						CommonMessage.debugMsg("Exception in subCategory getData "+e.getMessage());
					}
					
					
				}
				
				else if(action.equals("subCategory_deleteData.kcat"))
				{
					PrintWriter out = response.getWriter();
					try
					{	
						CommonMessage.debugMsg("Inside Del Subcategory");
						KznTlSubcategorymst kznTlSubcategorymst = new KznTlSubcategorymst();
						UIUtils.setBeanProperties(kznTlSubcategorymst, request);
						String gridRowid=request.getParameter("gridRowid");
						String kscmKctmKeyid = kznTlSubcategorymst.getKscmKctmKeyid();
					
						List<KznTlSubcategorymst> kznTlDelSubcategoryList =  new ArrayList<KznTlSubcategorymst>();
						if( kscmKctmKeyid != null)
							kznTlDelSubcategoryList = (List<KznTlSubcategorymst>)httpSession.getAttribute("KznSubcategoryServlet"+kscmKctmKeyid); 
					
			    		String kscmKeyid = kznTlSubcategorymst.getKscmKeyid();
			    		CommonMessage.debugMsg("kscmKeyid ="+kscmKeyid);
			    		CommonMessage.debugMsg("kscmKctmKeyid"+kscmKctmKeyid);
			    		kznTlSubcategorymst.setKscmKctmKeyid(kscmKctmKeyid);
			    		kznTlSubcategorymst.setKscmKeyid(kscmKeyid);
			    		//kznTlSubcategorymst = getKznlTlSubcategorymstFromListTest(kznTlDelSubcategoryList,kscmKeyid);
			    		kznTlSubcategorymst =kznTlCategorymstService.deleteAllSubcategoryNames(kznTlSubcategorymst);
			    		
			    		httpSession.setAttribute("KznSubcategoryServlet"+kscmKctmKeyid, kznTlDelSubcategoryList);
			    		
		  	    		JSONObject kznCategoryData = new JSONObject();// UIUtils.fromTpmModel(kznTlCategorymst);
				
		  	    		kznCategoryData.put("kscmKeyid", kscmKeyid);
			    		
			    		out.println(kznCategoryData);
					
					}
					catch(Exception e)
					{
						CommonMessage.debugMsg("Deletion Error Kzn Category Exception"+e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Deleted");
						out.print(err.toString());
						
					}
					
				}
				
				
				else if(action.equals("createCategory_save.kcat"))
				{
					try
					{	
						KaizenFormBean kaizenFormBean = new  KaizenFormBean();
						UIUtils.displayRequestParamsValue(request);
						String gridId=request.getParameter("gridId");
						
						CommonMessage.debugMsg("gridId ="+gridId);
						
						if(gridId.equalsIgnoreCase("kznCatgryGrid"))
							saveKznCategory(request, response, kaizenFormBean);
						else if(gridId.equalsIgnoreCase("subCatGrid"))
							saveKznSubCategory(request, response, kaizenFormBean);
							
					}
					catch(Exception e)
					{
						CommonMessage.debugMsg("kzn category save  Exception"+e.getMessage());	
					}
				}
		   
		}

		private JSONObject convertToCategoryTblObject(List<Object> kznCategoryList)
	  	{
	   		
	  		JSONObject kzntableDataObject = new JSONObject();
	 		
	  		kzntableDataObject.put("page", 1); //current page
	  		kzntableDataObject.put("total",10); // total page
	  		kzntableDataObject.put("records", (kznCategoryList.size())); //total records
			
			JSONArray rowArr = new JSONArray(); 
	     
	  		for(Object tpmModel: kznCategoryList)
	  		{
	  			
	  			KznTlCategorymst kznTlCategorymst = (KznTlCategorymst)tpmModel;
		  		JSONObject rowObj =new JSONObject();
			    rowObj.put("id",+1);
		        
		        JSONArray cell = new  JSONArray();
		        cell.put(kznTlCategorymst.getKctmKeyid());
		        cell.put(kznTlCategorymst.getKctmName());
		        cell.put(kznTlCategorymst.getKctmCode());
		        rowObj.put("cell",cell);
		        
		        rowArr.put(rowObj);
			}
	  		
	  			kzntableDataObject.put("rows", rowArr);
		      
	  	        return kzntableDataObject;
	   	}
		
		private JSONObject convertToSubCategoryTblObject(List<Object> kznSubCategoryList)
	  	{
	   		
	  		JSONObject kzntableDataObject = new JSONObject();
	 		
	  		kzntableDataObject.put("page", 1); //current page
	  		kzntableDataObject.put("total",10); // total page
	  		kzntableDataObject.put("records", (kznSubCategoryList.size())); //total records
			
			JSONArray rowArr = new JSONArray(); 
	     
	  		for(Object tpmModel: kznSubCategoryList)
	  		{
	  			
	  			KznTlSubcategorymst kznTlSubcategorymst = (KznTlSubcategorymst)tpmModel;
		  		JSONObject rowObj =new JSONObject();
			    rowObj.put("id",+1);
		        
		        JSONArray cell = new  JSONArray();
		        cell.put(kznTlSubcategorymst.getKscmKeyid());
		        cell.put(kznTlSubcategorymst.getKscmSubcatname());
		        cell.put(kznTlSubcategorymst.getKscmSubcatcode());
		        rowObj.put("cell",cell);
		        
		        rowArr.put(rowObj);
			}
	  		
	  			kzntableDataObject.put("rows", rowArr);
		      
	  	        return kzntableDataObject;
	   	}
		
		
		  private void saveKznCategory(HttpServletRequest request, HttpServletResponse response,KaizenFormBean kaizenFormBean  ) throws IOException{
			    
		    	HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		KznTlCategorymst newKznTlCategorymst = new KznTlCategorymst();
		    		newKznTlCategorymst =(KznTlCategorymst)UIUtils.setBeanProperties((Object)newKznTlCategorymst,request);
		    		
		     		String kctmTpmpillarid = newKznTlCategorymst.getKctmTpmpillarid();
		    		CommonMessage.debugMsg("kctmTpmpillarid in save func ="+kctmTpmpillarid);
		    		
		    		List<Object> kznTlCategoryList = null ;
		    		
		    		if( kctmTpmpillarid != null)
		    			kznTlCategoryList = (List<Object>)httpSession.getAttribute("KznCategoryServlet"+kctmTpmpillarid); 
		    		
		    		String kctmKeyid = newKznTlCategorymst.getKctmKeyid();
		    		CommonMessage.debugMsg("kctmKeyid in save func ="+kctmKeyid);
		    		CommonMessage.debugMsg("kznTlCategoryList Size ="+kznTlCategoryList.size());
		    		KznTlCategorymst existKznTlCategorymst = null;
		    		
		    		if( kctmKeyid != null  && kznTlCategoryList != null )
		    			existKznTlCategorymst =getKznTlCategorymstFromList(kznTlCategoryList, kctmKeyid);
		    		
		    		newKznTlCategorymst.setKctmCreatedby(user.getUsrm_ccno());
					
		    		
		    		kaizenFormBean =(KaizenFormBean) UIUtils.setBeanProperties((Object)kaizenFormBean,request);
					
					try
					{
						if(!(UIUtils.isValidKeyId(newKznTlCategorymst.getKctmKeyid())))
						{	
							existKznTlCategorymst =	kznTlCategorymstService.create(newKznTlCategorymst,existKznTlCategorymst,kaizenFormBean);
						}	
						else
						{
							existKznTlCategorymst = kznTlCategorymstService.update(newKznTlCategorymst,existKznTlCategorymst,kaizenFormBean);
						}
						
						kznTlCategoryList.add(existKznTlCategorymst);
						CommonMessage.debugMsg("kctmTpmpillarid ="+kctmTpmpillarid);
						CommonMessage.debugMsg("After Save ="+kznTlCategoryList.size());
						httpSession.setAttribute("KznCategoryServlet"+kctmTpmpillarid, kznTlCategoryList);
							
						JSONObject mode = new JSONObject();
						mode.put("formMode",kaizenFormBean.getFormActionMode());
								
						//out.print()	
						
					}
					catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "OplCreationExceptions");
						errMessage.put("formMode",kaizenFormBean.getFormActionMode());
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
		  
		  
		  
		  private void saveKznSubCategory(HttpServletRequest request, HttpServletResponse response,KaizenFormBean kaizenFormBean  ) throws IOException{
			    
		    	HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		KznTlSubcategorymst newKznTlSubcategorymst = new KznTlSubcategorymst();
		    		newKznTlSubcategorymst =(KznTlSubcategorymst)UIUtils.setBeanProperties((Object)newKznTlSubcategorymst,request);
		    		
		     		String kscmKctmKeyid = newKznTlSubcategorymst.getKscmKctmKeyid();
		    		CommonMessage.debugMsg("kscmKctmKeyid in save func ="+kscmKctmKeyid);
		    		
		    		List<Object> kznTlSubcategoryList = null ;
		    		
		    		if( kscmKctmKeyid != null)
		    			kznTlSubcategoryList = (List<Object>)httpSession.getAttribute("KznSubcategoryServlet"+kscmKctmKeyid); 
		    		
		    		CommonMessage.debugMsg("kznTlSubcategoryList ="+kznTlSubcategoryList);
		
		    		String kscmKeyid = newKznTlSubcategorymst.getKscmKeyid();
		    		CommonMessage.debugMsg("kscmKeyid in save func ="+kscmKeyid);
		    	
		    		KznTlSubcategorymst existKznTlSubcategorymst = null;
		    		
		    		if( kscmKeyid != null  && kznTlSubcategoryList != null )
		    			existKznTlSubcategorymst =getKznTlSubCategoryListFromList(kznTlSubcategoryList, kscmKeyid);
		    		
		    		newKznTlSubcategorymst.setKscmCreatedby(user.getUsrm_ccno());
		    		
		    		String slno1="1";
		    		newKznTlSubcategorymst.setKscmSlno(slno1);
		    		 
		    		if(kznTlSubcategoryList!=null)
		    		{
		    			String slno=  Integer.toString(kznTlSubcategoryList.size()+1);
		    			newKznTlSubcategorymst.setKscmSlno(slno);
		    		
		    		}
		    		CommonMessage.debugMsg("newKznTlSubcategorymst.setKscmSlno()"+newKznTlSubcategorymst.getKscmSlno());
		    		CommonMessage.debugMsg(" newKznTlSubcategorymst.getKscmKeyid()"+ newKznTlSubcategorymst.getKscmKeyid());
		    		
		    		kaizenFormBean =(KaizenFormBean) UIUtils.setBeanProperties((Object)kaizenFormBean,request);
					
					try
					{
						if(( !(UIUtils.isValidKeyId(newKznTlSubcategorymst.getKscmKeyid())))||(newKznTlSubcategorymst.getKscmKeyid().equals("FALSE")))
						{	
							CommonMessage.debugMsg(" newKznTlSubcategorymst.getKscmKeyid() inside save"+ newKznTlSubcategorymst.getKscmKeyid());
						    
							existKznTlSubcategorymst =	kznTlCategorymstService.create(newKznTlSubcategorymst,existKznTlSubcategorymst,kaizenFormBean);
						}	
						else
						{
							existKznTlSubcategorymst = kznTlCategorymstService.update(newKznTlSubcategorymst,existKznTlSubcategorymst,kaizenFormBean);
						}
						
						kznTlSubcategoryList.add(existKznTlSubcategorymst);
						
						httpSession.setAttribute("KznCategoryServlet"+kscmKctmKeyid, kznTlSubcategoryList);
							
						JSONObject mode = new JSONObject();
						mode.put("formMode",kaizenFormBean.getFormActionMode());
								
						//out.print()	
						
					}
					catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "OplCreationExceptions");
						errMessage.put("formMode",kaizenFormBean.getFormActionMode());
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
		     
		     private KznTlCategorymst getKznTlCategorymstFromList(List<Object> kznCategoryList, String oplcKeyid)
		     {
		    	 int index = 0;
		    	 for( Object object:kznCategoryList)
		    	 {
		    		 
		    		 KznTlCategorymst kznTlCategorymst= (KznTlCategorymst)object;
		    		 if( kznTlCategorymst.getKctmKeyid().equals(oplcKeyid) ){
		    			 kznCategoryList.remove(index);
		    			 return kznTlCategorymst;
		    		 }	 
		    		 index++;
		    	 }
		    	 return null; 
		     }

		     private KznTlCategorymst getKznlTlCategorymstFromListTest(List<KznTlCategorymst> kznCategoryList, String oplcKeyid)
		     {
		    	 int index = 0;
		    	 for( KznTlCategorymst kznTlCategorymst:kznCategoryList)
		    	 {
		    		 if( kznTlCategorymst.getKctmKeyid().equals(oplcKeyid) ){
		    			 kznCategoryList.remove(index);
		    			 return kznTlCategorymst;
		    		 }	 
		    		 index++;
		    	 }
		    	 return null; 
		     }
		     
		     private KznTlSubcategorymst getKznlTlSubcategorymstFromListTest(List<KznTlSubcategorymst> kznSubcategoryList, String oplcKeyid)
		     {
		    	 int index = 0;
		    	 for( KznTlSubcategorymst kznTlSubcategorymst:kznSubcategoryList)
		    	 {
		    		 if( kznTlSubcategorymst.getKscmKeyid().equals(oplcKeyid) ){
		    			 kznSubcategoryList.remove(index);
		    			 return kznTlSubcategorymst;
		    		 }	 
		    		 index++;
		    	 }
		    	 return null; 
		     }
		     
		     private KznTlSubcategorymst getKznTlSubCategoryListFromList(List<Object> kznSubCategoryList, String kscmKeyid)
		     {
		    	 int index = 0;
		    	 for( Object object:kznSubCategoryList)
		    	 {
		    		 
		    		 KznTlSubcategorymst kznTlSubcategorymst= (KznTlSubcategorymst)object;
		    		 if( kznTlSubcategorymst.getKscmKeyid().equals(kscmKeyid) ){
		    			 kznSubCategoryList.remove(index);
		    			 return kznTlSubcategorymst;
		    		 }	 
		    		 index++;
		    	 }
		    	 return null; 
		     }  
		 
		
}
