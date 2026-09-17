/* Author : Siddharth .Anand */
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//import sun.reflect.generics.factory.GenericsFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_SprPckupFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.model.OplTlPillarlink;
import com.akranta.tpm.model.BAL_PlmTlSparedtl;
import com.akranta.tpm.service.BAL_PlmTlSparedtlService;
import com.akranta.tpm.service.impl.BAL_PlmTlSparedtlServiceImpl;
//import com.akranta.tpm.service.impl.BAL_SparesPullListRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.service.api.BAL_PlmTlSparedtlServiceApi;

public class BAL_SparesPickupServlet  extends HttpServlet  {

	
	private static final long serialVersionUID = 1L;
	BAL_PlmTlSparedtlService plmTlSparedtlService;
	BAL_PlmTlSparedtlServiceApi sparesapi;

	public BAL_SparesPickupServlet() throws Exception
	{
		//plmTlSparedtlService = new PlmTlSparedtlServiceImpl();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		process(request, response); 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		 
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);  
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ComboFilter comboFilter = new ComboFilter();
		
		response.setContentType("text/html");
		response.setContentType("text/json");
		try {
			plmTlSparedtlService = (BAL_PlmTlSparedtlServiceImpl)UIUtils.getServiceObject(request,"BAL_PlmTlSparedtlServiceImpl");
			plmTlSparedtlService.BAL_PlmTlSparedtlServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		/*if (action.equals("Sparepickup_input.sprpckup") && user != null) 
		{ 
			UIUtils.displayRequestParamsValue(request);
			String pspd_Standardid =request.getParameter("punsKeyid");
			String parentId=request.getParameter("elementId");
			
			System.out.println("parentId="+parentId);
			BAL_SprPckupFormBean sprPckupFormBean = new BAL_SprPckupFormBean();
			sprPckupFormBean.setStandardId(pspd_Standardid);
			sprPckupFormBean.setFnLnParentId(parentId);
			
			httpSession.setAttribute("sprPckupFormBean", sprPckupFormBean);
			request.setAttribute("sprPckupFormBean", sprPckupFormBean);
			
		}*/
		if (action.equals("Sparepickup_input.sprpckup") && user != null)
		{
		    UIUtils.displayRequestParamsValue(request);

		    // FIX: this popup is opened with ?standardId=..., not ?punsKeyid=...
		    // punsKeyid was never being sent by the caller, so this always read null.
		    String pspd_Standardid = request.getParameter("standardId");
		    String parentId=request.getParameter("elementId");

		    System.out.println("parentId="+parentId);
		    System.out.println("pspd_Standardid="+pspd_Standardid);
		    BAL_SprPckupFormBean sprPckupFormBean = new BAL_SprPckupFormBean();
		    sprPckupFormBean.setStandardId(pspd_Standardid);
		    sprPckupFormBean.setFnLnParentId(parentId);

		    httpSession.setAttribute("sprPckupFormBean", sprPckupFormBean);
		    request.setAttribute("sprPckupFormBean", sprPckupFormBean);

		}
		
		else if (action.equals("Sparepickup_input.sprpckup")) 
		{ 
			// there is nothing to be done
		}
		
		else if( action.equals("Sparepickup_getCol.sprpckup"))
		{	
			try
			{	
				PrintWriter out = response.getWriter();
				String sprpkupColM=UIUtils.getPropertyValue("com.akranta.tpm.resources.SprPickupRelatedColHeaders", "sparesPickupTable");
				System.out.println("sprpkupColM="+sprpkupColM);
				out.print(sprpkupColM);
			}																								
	   
			catch (Exception e) 
			{
				e.printStackTrace();
			}
        }
		
		/*
		 * else if( action.equals("Sparepickup_getData.sprpckup")) { try { PrintWriter
		 * out = response.getWriter();
		 * 
		 * BAL_SprPckupFormBean sprPckupFormBean =
		 * (BAL_SprPckupFormBean)httpSession.getAttribute("sprPckupFormBean");
		 * System.out.println("sprPckupFormBean.getStandardId()="+sprPckupFormBean.
		 * getStandardId()); String StandardId =sprPckupFormBean.getStandardId();
		 * 
		 * List<String[]> SprPickupList =
		 * plmTlSparedtlService.getAllSprPickup(StandardId);
		 * 
		 * JSONObject sprPckupData =
		 * UIUtils.convertToJqGridTableObject(SprPickupList,request,0,0);
		 * System.out.println("sprPckupData="+sprPckupData); List<BAL_PlmTlSparedtl>
		 * plmTlSprpckupList = populateSprPckList(SprPickupList);
		 * 
		 * System.out.println("plmTlSprpckupList.size()"+plmTlSprpckupList.size());
		 * 
		 * httpSession.setAttribute("plmTlSparedtl"+StandardId,plmTlSprpckupList);
		 * sprPckupData.put("StandardId", StandardId);
		 * 
		 * out.println(sprPckupData); } catch(Exception e) {
		 * System.out.println("Get Data Exception"+e.getMessage()); } }
		 */
		else if( action.equals("Sparepickup_getData.sprpckup"))
		{
		    try
		    {
		        PrintWriter out = response.getWriter();

		        BAL_SprPckupFormBean sprPckupFormBean = (BAL_SprPckupFormBean)httpSession.getAttribute("sprPckupFormBean");

		        // FIX: sprPckupFormBean.getStandardId() comes back null because
		        // standardId only ever arrives as a URL query param, never as a
		        // bound form field, so the bean stored in session never had it set.
		        // Fall back to the request param, and push it back onto the bean +
		        // session so subsequent calls in this popup session see it too.
		        String StandardId = sprPckupFormBean.getStandardId();
		        if (StandardId == null || StandardId.trim().isEmpty())
		        {
		            StandardId = request.getParameter("standardId");
		            if (sprPckupFormBean != null)
		            {
		                sprPckupFormBean.setStandardId(StandardId);
		                httpSession.setAttribute("sprPckupFormBean", sprPckupFormBean);
		            }
		        }

		        System.out.println("sprPckupFormBean.getStandardId()="+StandardId);
		        System.out.println("standardId="+StandardId);

		        List<String[]> SprPickupList = plmTlSparedtlService.getAllSprPickup(StandardId);

		        JSONObject sprPckupData = UIUtils.convertToJqGridTableObject(SprPickupList,request,0,0);
		        System.out.println("sprPckupData="+sprPckupData);
		        List<BAL_PlmTlSparedtl> plmTlSprpckupList =	populateSprPckList(SprPickupList);

		        System.out.println("plmTlSprpckupList.size()"+plmTlSprpckupList.size());

		        httpSession.setAttribute("plmTlSparedtl"+StandardId,plmTlSprpckupList);
		        sprPckupData.put("StandardId", StandardId);

				out.println(sprPckupData);
		    }
		    catch(Exception e)
		    {
		        System.out.println("Get Data Exception"+e.getMessage());
		    }
		}
		
		else if( action.equals("sprscheckcnt_input.sprpckup"))
		{
			PrintWriter out = response.getWriter();
			try
			{
			
				BAL_SprPckupFormBean sprPckupFormBean = (BAL_SprPckupFormBean)httpSession.getAttribute("BAL_sprPckupFormBean");
				System.out.println("Incount Parent Id="+sprPckupFormBean.getFnLnParentId());
				String fnlnParentid = sprPckupFormBean.getFnLnParentId();
				List<BAL_PlmTlSparedtl> spareCountList= (List<BAL_PlmTlSparedtl>)httpSession.getAttribute("existPlmTlSparedtl"+fnlnParentid);
		    	System.out.println("spareCountList in cnt="+spareCountList.size());
		    	//List<PlmTlSparedtl> plmTlSparedtlCount = new ArrayList<PlmTlSparedtl>();
				/*for( PlmTlSparedtl plmSpareCount: spareCountList)
				{
					sprPckupFormBean.setFnLnOriginalId(plmSpareCount.getPspdSpareid());
					System.out.println("sprPckupFormBean.get--"+sprPckupFormBean.getFnLnOriginalId());
				}
				*/
				List<String[]> getSprCountList = plmTlSparedtlService.getAllSprCount(spareCountList,fnlnParentid);
				System.out.println("getSprCountList.get(0)="+getSprCountList.get(0));
				String[] cnt=getSprCountList.get(0);
				System.out.println("cnt+"+cnt[0]);
				
				JSONObject SprCountData = UIUtils.convertToJqGridTableObject(getSprCountList, request, 0);
				
				System.out.println("SprCountData="+cnt[0]);
				JSONArray jsonObject = JSONArray.fromObject(SprCountData);
			    System.out.println("jsonObject"+jsonObject);
				JSONObject returnData= new JSONObject();
			    returnData.put("jsonObject", jsonObject);				
				out.print(returnData.toString());
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		
		
		
		else if( action.equals("text_sprName.sprpckup"))
		{
			try 
			{
				PrintWriter out = response.getWriter();
				String sprmKeyid = request.getParameter("sprmKeyid");
				System.out.println("sprmKeyid"+sprmKeyid);
				List<String []> SprNameList  = plmTlSparedtlService.getSprNameSelectSpr(sprmKeyid);
				JSONArray jsonObject = JSONArray.fromObject(SprNameList);
			    System.out.println("jsonObject"+jsonObject);
				JSONObject returnData= new JSONObject();
			    returnData.put("jsonObject", jsonObject);				
				out.print(returnData.toString());
		      
				
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if( action.equals("combo_partno.sprpckup"))
		{
			try
			{
				comboFilter= UIUtils.fillComboFilter(request);
				List<ComboBox>  sprmPartNo = plmTlSparedtlService.getPartNo("",comboFilter);
				UIUtils.writeComboBox(response, sprmPartNo,comboFilter);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		else if(action.equals("sprmultiselect_input.sprpckup"))
		{
			String dispatchUrl = "/pages/MultiSelectPopup.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
		}
		
		else if(action.equals("sprmultiselect_getCol.sprpckup"))
		{
			PrintWriter out = response.getWriter();
			String sprSelectColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.SprPickupRelatedColHeaders", "sprMultiSelectTable");
			System.out.println(sprSelectColM);
			out.print(sprSelectColM);
		}
		
		else if(action.equals("sprmultiselect_getData.sprpckup"))
		{
			
			PrintWriter out = response.getWriter();
			try 
			{
				List<String []> SprMultiSelctList  = plmTlSparedtlService.getAllMultiSelectSpr();
				JSONObject SprMultiSelctData = UIUtils.convertToJqGridTableObject(SprMultiSelctList,request,0,0);
				System.out.println("SprMultiSelctData="+SprMultiSelctData);
				out.println(SprMultiSelctData);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		else if(action.equals("sparespickup_delete.sprpckup"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				String pspKeyid=request.getParameter("pspdKeyid");
				System.out.println("sprmKeyid"+pspKeyid);
				BAL_PlmTlSparedtl plmTlSparedtl = new BAL_PlmTlSparedtl();
				plmTlSparedtl.setPspdKeyid(pspKeyid);
				plmTlSparedtlService.delete(plmTlSparedtl);
				
				JSONObject OnDelete = new JSONObject();
				OnDelete.put("msg","Data Deleted Successfully");
				OnDelete.put("DeletedPspKeyid",pspKeyid );
				JSONObject returnData = new JSONObject();
					
				returnData.put("OnDelete", OnDelete);				
					
				out.print(returnData.toString());
				
			}
			
			catch(Exception e)
			{
				System.out.println("Exception in delete sprpckup"+e.getMessage());
			}
		}
		
		else if(action.equals("Sparepickup_delete.sprpckup"))
		{
			BAL_SprPckupFormBean sprPckupFormBean = new BAL_SprPckupFormBean();
			String pspdKeyid= request.getParameter("pspdKeyid");
			System.out .println("pspdKeyid="+pspdKeyid);
			delSprPkup(request, response, sprPckupFormBean);
		}
								
		else if(action.equals("Sparepickup_save.sprpckup"))
		{
			BAL_SprPckupFormBean sprPckupFormBean = (BAL_SprPckupFormBean)httpSession.getAttribute("sprPckupFormBean") ;
			saveSprPkup(request, response, sprPckupFormBean);
		}
			
		if (action.equals("Sparepickup_input.sprpckup")) 
		{
			String dispatchUrl ="/pages/BAL_SparesPickup.jsp";
			UIUtils.forwardRequest(request, response, dispatchUrl);
		}
			
	}
	

	/*
	 * private void saveSprPkup(HttpServletRequest request, HttpServletResponse
	 * response,BAL_SprPckupFormBean sprPckupFormBean ) throws IOException {
	 * System.out.print("Inside Save"); HttpSession httpSession =
	 * request.getSession(false); ServletOutputStream out =
	 * response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request); UIUtils.displayRequestParamsValue(request);
	 * 
	 * if( httpSession != null && user != null) {
	 * 
	 * List<BAL_PlmTlSparedtl> sparesPkupList =null; BAL_PlmTlSparedtl plmTlSparedtl
	 * = new BAL_PlmTlSparedtl(); String sparesPickupStr =
	 * request.getParameter("SparesPickup");
	 * System.out.println("sparesPickupStr++^%$%^%#^--------" +sparesPickupStr);
	 * JSONArray sparesPickupJSON =null; if( sparesPickupStr != null && !
	 * sparesPickupStr.isEmpty()) { sparesPickupJSON =
	 * JSONArray.fromString(sparesPickupStr);
	 * sparesPkupList=(List<BAL_PlmTlSparedtl>)UIUtils.convertJSONArrToList(
	 * plmTlSparedtl, sparesPickupJSON);
	 * System.out.println("sparesPkupList not null ="+sparesPkupList.size());
	 * 
	 * } String standardId = sprPckupFormBean.getStandardId();
	 * httpSession.setAttribute("sprPckupstandardId",standardId);
	 * System.out.println("standardId ="+standardId);
	 * 
	 * List<BAL_PlmTlSparedtl> existPlmTlSparedtl=
	 * (List<BAL_PlmTlSparedtl>)httpSession.getAttribute("plmTlSparedtl" +
	 * standardId);
	 * 
	 * try { sprPckupFormBean.setCreatedBy(user.getUsrm_ccno()); if( sparesPkupList
	 * != null && sparesPkupList.size() > 0) { System.out.println("Savedsdsdsd");
	 * 
	 * existPlmTlSparedtl =
	 * plmTlSparedtlService.save(sparesPkupList,existPlmTlSparedtl,sprPckupFormBean)
	 * ; }
	 * 
	 * String fnlnParentid =sprPckupFormBean.getFnLnParentId(); //(String)
	 * httpSession.getAttribute("SparesPickupServletElementId");
	 * System.out.println("fnlnParentid"+fnlnParentid);
	 * 
	 * String fnlnElementid=null;
	 * httpSession.setAttribute("existPlmTlSparedtl"+fnlnParentid,existPlmTlSparedtl
	 * ); List<GenTlFunctionallocn> genTlFunctionallocns = new
	 * ArrayList<GenTlFunctionallocn>(); for( BAL_PlmTlSparedtl plmSpareDetail:
	 * existPlmTlSparedtl) { fnlnElementid =
	 * fnlnParentid+"-"+plmSpareDetail.getPspdSpareid();
	 * System.out.println("fnlnElementid"+fnlnElementid); GenTlFunctionallocn
	 * genTlFunctionallocn = new GenTlFunctionallocn();
	 * genTlFunctionallocn.setFnlnOriginalid(plmSpareDetail.getPspdSpareid());
	 * genTlFunctionallocn.setFnlnElementid(fnlnElementid);
	 * genTlFunctionallocn.setFnlnParentid(fnlnParentid);
	 * genTlFunctionallocn.setFnlnActive("Y");
	 * genTlFunctionallocn.setFnlnElementtype("SPR");
	 * genTlFunctionallocn.setFnlnDisplaycode("{}");
	 * genTlFunctionallocn.setFnlnDescription("{}");
	 * 
	 * genTlFunctionallocns.add(genTlFunctionallocn); }
	 * System.out.println("genTlFunctionallocns"+genTlFunctionallocns.size());
	 * System.out.print("standardId="+standardId);
	 * httpSession.setAttribute("genTlFunctionallocns"+standardId,
	 * genTlFunctionallocns); JSONObject successData = new JSONObject();
	 * successData.put("msg","Data Saved Successfully");
	 * successData.put("funcLocns","genTlFunctionallocns"+standardId ); JSONObject
	 * returnData = new JSONObject();
	 * 
	 * returnData.put("successData", successData);
	 * 
	 * out.print(returnData.toString()); } catch(ValidationExceptions e) {
	 * JSONObject errMessage = UIUtils.validationExceptions(e.toString(),
	 * "SprPickupCreation");
	 * System.out.println("---------errMessage--------------------"+e.toString());
	 * errMessage.put("msg","Null");
	 * 
	 * errMessage.put("formMode",sprPckupFormBean.getFormActionMode());
	 * out.print(errMessage.toString()); }
	 * 
	 * catch(Exception e) { System.out.println("Error Msg:" + e.getMessage());
	 * JSONObject tpmException = new JSONObject(); JSONObject err = new
	 * JSONObject(); tpmException.put("msg", "Data Not Saved");
	 * tpmException.put("errMsg" , e.getMessage());
	 * err.put("tpmException",tpmException); out.print(err.toString()); } } }
	 */
	
	private void saveSprPkup(HttpServletRequest request, HttpServletResponse response,BAL_SprPckupFormBean sprPckupFormBean  ) throws IOException
    {
    	System.out.print("Inside Save");
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		
    		List<BAL_PlmTlSparedtl> sparesPkupList =null;
    		BAL_PlmTlSparedtl plmTlSparedtl = new BAL_PlmTlSparedtl();
    		String sparesPickupStr =  request.getParameter("SparesPickup");
    		System.out.println("sparesPickupStr++^%$%^%#^--------" +sparesPickupStr);
    		JSONArray sparesPickupJSON =null;
    		if( sparesPickupStr != null && ! sparesPickupStr.isEmpty())
    		{
    			sparesPickupJSON = JSONArray.fromString(sparesPickupStr);
    			sparesPkupList=(List<BAL_PlmTlSparedtl>)UIUtils.convertJSONArrToList(plmTlSparedtl, sparesPickupJSON);
    			System.out.println("sparesPkupList not null ="+sparesPkupList.size());
    			
    		}

    		// FIX: standardId arrives as a URL query param (?standardId=...), not as a
    		// bound form field, so sprPckupFormBean.getStandardId() was coming back null.
    		// Fall back to reading it off the request directly, and push it back onto the
    		// bean so the service layer (which reads it from the bean) also gets the real value.
    		String standardId = sprPckupFormBean.getStandardId();
    		if( standardId == null || standardId.trim().isEmpty())
    		{
    			standardId = request.getParameter("standardId");
    			sprPckupFormBean.setStandardId(standardId);
    		}
    		httpSession.setAttribute("sprPckupstandardId",standardId);
    		System.out.println("standardId ="+standardId);
    	
    		List<BAL_PlmTlSparedtl> existPlmTlSparedtl= (List<BAL_PlmTlSparedtl>)httpSession.getAttribute("plmTlSparedtl" +  standardId);
    		
			try
			{
				sprPckupFormBean.setCreatedBy(user.getUsrm_ccno());
				if( sparesPkupList != null && sparesPkupList.size() > 0)
				{	System.out.println("Savedsdsdsd");
					
					existPlmTlSparedtl =	plmTlSparedtlService.save(sparesPkupList,existPlmTlSparedtl,sprPckupFormBean);
				}	
				
				String fnlnParentid =sprPckupFormBean.getFnLnParentId(); //(String) httpSession.getAttribute("SparesPickupServletElementId");
				System.out.println("fnlnParentid"+fnlnParentid);
				
				String fnlnElementid=null;
				httpSession.setAttribute("existPlmTlSparedtl"+fnlnParentid,existPlmTlSparedtl );
				List<GenTlFunctionallocn> genTlFunctionallocns = new ArrayList<GenTlFunctionallocn>();
				for( BAL_PlmTlSparedtl plmSpareDetail: existPlmTlSparedtl)
				{
					fnlnElementid = fnlnParentid+"-"+plmSpareDetail.getPspdSpareid();
					System.out.println("fnlnElementid"+fnlnElementid);
					GenTlFunctionallocn genTlFunctionallocn = new GenTlFunctionallocn();
					genTlFunctionallocn.setFnlnOriginalid(plmSpareDetail.getPspdSpareid());
					genTlFunctionallocn.setFnlnElementid(fnlnElementid);
					genTlFunctionallocn.setFnlnParentid(fnlnParentid);
					genTlFunctionallocn.setFnlnActive("Y");
					genTlFunctionallocn.setFnlnElementtype("SPR");
					genTlFunctionallocn.setFnlnDisplaycode("{}");
					genTlFunctionallocn.setFnlnDescription("{}");
					
					genTlFunctionallocns.add(genTlFunctionallocn);
				}
				System.out.println("genTlFunctionallocns"+genTlFunctionallocns.size());
				System.out.print("standardId="+standardId);
				httpSession.setAttribute("genTlFunctionallocns"+standardId,genTlFunctionallocns);
				JSONObject successData = new JSONObject();
				successData.put("msg","Data Saved Successfully");
				successData.put("funcLocns","genTlFunctionallocns"+standardId );
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);				
				
				out.print(returnData.toString());					
			}
			catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "SprPickupCreation");
				System.out.println("---------errMessage--------------------"+e.toString());
				errMessage.put("msg","Null");
				
				errMessage.put("formMode",sprPckupFormBean.getFormActionMode());
				out.print(errMessage.toString());
			}
			
			catch(Exception e)
			{
				System.out.println("Error Msg:" + e.getMessage());
				JSONObject tpmException = new JSONObject();
				JSONObject err = new JSONObject();
				tpmException.put("msg", "Data Not Saved");
				tpmException.put("errMsg" , e.getMessage());
				err.put("tpmException",tpmException);
				out.print(err.toString());
			}
    	}	
    }
	private void delSprPkup(HttpServletRequest request, HttpServletResponse response,BAL_SprPckupFormBean sprPckupFormBean  ) throws IOException
    {
    	System.out.print("Inside Delete");
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		
    		//PlmTlSparedtl existPlmTlSparedtl = (PlmTlSparedtl)httpSession.getAttribute("existPlmTlSparedtl"+fnlnParentid); 
    	   	
    		try
			{
    			String standardId =(String)httpSession.getAttribute("sprPckupstandardId");
    			
    			System.out.println("PSD="+standardId);
    			// plmTlSparedtlService.getDeleteAll(existPlmTlSparedtl);
				plmTlSparedtlService.getDeleteAll(standardId);
					
				JSONObject successData = new JSONObject();
				successData.put("msg","Data Deleted Successfully");
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);				
				
				out.print(returnData.toString());					
			}
			
			
			catch(Exception e)
			{
				System.out.println("Error Msg:" + e.getMessage());
				JSONObject tpmException = new JSONObject();
				JSONObject err = new JSONObject();
				tpmException.put("msg", "Data Not Saved");
				tpmException.put("errMsg" , e.getMessage());
				err.put("tpmException",tpmException);
				out.print(err.toString());
			}

    	}
    }
	
	
		private List<BAL_PlmTlSparedtl> populateSprPckList(List<String[]> sprPickupList)
		{
			List<BAL_PlmTlSparedtl> sprsTblList = new ArrayList<BAL_PlmTlSparedtl>();
		 	System.out.println("Inside populatePillarLinkList"+sprPickupList.size());
		 	for( String [] row : sprPickupList)
		 {
		 		BAL_PlmTlSparedtl plmTlSparedtl = new BAL_PlmTlSparedtl();
		 		plmTlSparedtl.setPspdQuantity(row[6]);//1
		 		plmTlSparedtl.setPspdSpareid(row[5]);
		 		plmTlSparedtl.setPspdCreatedon(row[2]);
		 		plmTlSparedtl.setPspdStandardid(row[0]);//0
		 		plmTlSparedtl.setPspdKeyid(row[7]);//2
		 		sprsTblList.add(plmTlSparedtl);
		 }
		 		System.out.println(sprsTblList);
		 		return sprsTblList;
		}
	    
}

		