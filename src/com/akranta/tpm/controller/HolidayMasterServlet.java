package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EquipmentBean;
//import com.akranta.tpm.bean.GenTlAssemblymstBean;
import com.akranta.tpm.bean.GenTlHolidaymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlHolidaymst;
//import com.akranta.tpm.service.GenTlAssemblymstService;
import com.akranta.tpm.service.GenTlHolidaymstService;
import com.akranta.tpm.service.api.GenTlHolidaymstServiceApi;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
//import com.akranta.tpm.service.impl.GenTlAssemblymstServiceImpl;
import com.akranta.tpm.service.impl.GenTlHolidaymstServiceImpl;
import com.akranta.tpm.service.impl.GenTlSectionmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class HolidayMasterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
		GenTlHolidaymstService  genTlHolidaymstService ;
		GenTlHolidaymstServiceApi genTlHolidaymstServiceApi;

	
    public HolidayMasterServlet() {
        super();
        /*System.out.println(" initialising servlet ....");
        try {
        	genTlHolidaymstService = new GenTlHolidaymstServiceImpl();
			
		} catch (Exception e) {
			
		} 
		*/      
    }
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	try {
			process(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	try {
			process(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception,ValidationExceptions
	{
    	HttpSession httpSession = request.getSession(false);
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		if( httpSession != null && user != null)
    	{
			String action = UIUtils.getActionPart(request);
			try {
				genTlHolidaymstService = (GenTlHolidaymstServiceImpl)UIUtils.getServiceObject(request,"GenTlHolidaymstServiceImpl");
				genTlHolidaymstService.GenTlHolidaymstServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			} catch (ServiceObjectCreationException e) {
				CommonFunctions.debugMsg(e);
			}		
			String dispatchUrl = null; 
				
			if (action.equals("holiday_input.holiday")) 
			{
				String keyid = request.getParameter(ReqtParamNameConst.KEYID);
				String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
				
				System.out.println(" userEvent  " + userEvent);
				String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
				response.setContentType("text/html");
				FormModes mode = FormModes.create;	
			
				GenTlHolidaymst genTlHolidaymst = null;				
				httpSession.removeAttribute("genTlHolidaymstServlet");
				
				if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
					
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					
					if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
					{
						mode =  FormModes.modify;
					}else if( formMode.equals( FormModeConsts.view)){
						mode=FormModes.view;
					}
					
					genTlHolidaymst = genTlHolidaymstService.select(keyid);
					httpSession.setAttribute("genTlHolidaymstServlet" , genTlHolidaymst);
					request.setAttribute("genTlAssemblymst", genTlHolidaymst);
				}
				//mode=FormModes.view;
				
				GenTlHolidaymstBean genTlHolidaymstBean = new GenTlHolidaymstBean(mode,lockFields);
				
				httpSession.setAttribute("genTlHolidaymstBean", genTlHolidaymstBean);
				request.setAttribute("genTlHolidaymstBean", genTlHolidaymstBean);
				request.setAttribute("genTlHolidaymstServlet", genTlHolidaymst);				
			
				dispatchUrl = "/pages/HolidayMasterForm.jsp";
			}
			
			else if(action.equals("holiday_getCol.holiday"))
			{
				
					PrintWriter out = response.getWriter();
					CommonFunctions.debugMsg("getCol val");
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Holiday","Holiday"));
										
					String keyId = request.getParameter("keyId");
					String flag = request.getParameter("holidayFlag");	
					String holidaySelect = request.getParameter("holidaySelect");
					String year = request.getParameter("year");
					
					List<String[]> HolidayList = null;
					

					 GridParams gridParams = new GridParams();  
					 

					  if( gridParams == null )
	        			  gridParams = new GridParams();	        		  
	        		  FilterValues.populateGridParams(request,gridParams );
	        		  
	        		
				
					 //if(UIUtils.isValidKeyId(keyId))
							//HolidayList =   genTlHolidaymstService.selectAll(keyId,flag,year);//recall
						//else if(UIUtils.isValidKeyId(holidaySelect) && holidaySelect.equals("holiday"))
							HolidayList = genTlHolidaymstService.getAllHolidayCount(flag,year, keyId);
							
			        		
						//else
						  //HolidayList = genTlHolidaymstService.getAllHoliday();
					 
					 int  count = HolidayList.size();
					 
					 
						CommonFunctions.debugMsg("count....."+count);
						 httpSession.removeAttribute("CountData");
						 httpSession.setAttribute("CountData", count);
						 
						  
		        		  httpSession.removeAttribute("gridParams");
		        		  httpSession.setAttribute("gridParams" ,gridParams);
		        		  
		        		  httpSession.removeAttribute("keyId");
		        		  httpSession.setAttribute("keyId" ,keyId);
		        		  
		        		  httpSession.removeAttribute("flag");
		        		  httpSession.setAttribute("flag" ,flag);
		        		  
		        		  httpSession.removeAttribute("year");
		        		  httpSession.setAttribute("year" ,year);
				
			}
			else if(action.equals("holiday_getData.holiday"))
			{
				try {
					String keyId = request.getParameter("keyId");
					String flag = request.getParameter("holidayFlag");	
					String holidaySelect = request.getParameter("holidaySelect");					
					String year = request.getParameter("year");
					
					List<String[]> HolidayList = null;
					CommonFunctions.debugMsg("getData val"+flag);
					PrintWriter out = response.getWriter();					
					
					 GridParams gridParams = new GridParams();  
				
					 //if(UIUtils.isValidKeyId(keyId))
							//HolidayList =   genTlHolidaymstService.selectAll(keyId,flag,year);//recall
						//else if(UIUtils.isValidKeyId(holidaySelect) && holidaySelect.equals("holiday"))
							HolidayList = genTlHolidaymstService.getAllHolidayCount(flag,year, keyId);
						//else
						  //HolidayList = genTlHolidaymstService.getAllHoliday();
					 
					 int  count = HolidayList.size();
					 
					
					 gridParams =(GridParams) httpSession.getAttribute("gridParams");
	        		  if( gridParams == null )
	        			  gridParams = new GridParams();	        		  
	        		  FilterValues.populateGridParams(request,gridParams );
	        		  
	        		  
	        		  httpSession.removeAttribute("gridParams");
	        		  httpSession.setAttribute("gridParams" ,gridParams);
	        		  
	        		  httpSession.removeAttribute("keyId");
	        		  httpSession.setAttribute("keyId" ,keyId);
	        		  
	        		  httpSession.removeAttribute("flag");
	        		  httpSession.setAttribute("flag" ,flag);
	        		  
	        		  httpSession.removeAttribute("year");
	        		  httpSession.setAttribute("year" ,year);
	        		  
					//if(UIUtils.isValidKeyId(keyId))
						
						//HolidayList =   genTlHolidaymstService.selectHolidayFactory(keyId,flag,year,gridParams);//recall
					//else if(UIUtils.isValidKeyId(holidaySelect) && holidaySelect.equals("holiday"))
						//HolidayList = genTlHolidaymstService.getAllHoliday(flag,year,gridParams);
					//else
						//HolidayList = genTlHolidaymstService.getAllHoliday();
					
					
					JSONObject holidayData = UIUtils.convertToJqGridTableObject(HolidayList, request, 0, 1,count);
					
					 httpSession.removeAttribute("CountData");
					 httpSession.setAttribute("CountData", count);
					 
					out.println(holidayData);
					
				} catch (Exception e) {
					System.out.println("Holiday Form Exception" + e.getMessage());
				}
			}
				else if(action.equals("Combo_Year.holiday")){
					try {
						ComboFilter comboFilter = UIUtils.fillComboFilter(request);
						List<ComboBox>  year = genTlHolidaymstService.getYear("");
						UIUtils.writeComboBox(response, year,comboFilter);
					} 
				catch (Exception e) 
					{						
						e.printStackTrace();
					}
				}	
				else if( action.equals("holiday_getExcel.holiday"))
				{		
					
					String keyId = (String) httpSession.getAttribute("keyId"); 
					String flag = (String) httpSession.getAttribute("flag");					
					String year = (String) httpSession.getAttribute("year");
					if(!UIUtils.isValidKeyId(year))
					{
						int yr = Calendar.getInstance().get(Calendar.YEAR);
						year = Integer.toString(yr);
						flag = "H";
					}		
					
					String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.Holiday","Holiday");
					JSONObject tblJSONObj = JSONObject.fromString(tableModel);
					tblJSONObj.put("title", "HoliDay Report");
					String format = ExcelUtils.getFormat(request);
					
					int count =  (Integer) httpSession.getAttribute("CountData"); 					
					String cnt = Integer.toString(count);
					
					GridParams gridParams = new GridParams();
					gridParams =(GridParams) httpSession.getAttribute("gridParams");
					gridParams.setFromRow("0");
					gridParams.setToRow(cnt);
					
					Workbook wb  = genTlHolidaymstService.HolidayFactoryExportExcel(keyId,flag,year,gridParams,tblJSONObj,format);					
					ExcelUtils.writeToResponse(response, wb, "Holiday", format);
				}
			
			else if(action.equals("holiday_save.holiday"))
			{	
				GenTlHolidaymstBean genTlHolidaymstBean = (GenTlHolidaymstBean)httpSession.getAttribute("genTlHolidaymstBean");
				saveHoliday(request,response,genTlHolidaymstBean);
			}
			
			else if( action.equals("holiday_delete.holiday"))
			{	
				GenTlHolidaymstBean genTlHolidaymstBean = (GenTlHolidaymstBean)httpSession.getAttribute("genTlHolidaymstBean");
				deleteHoliday(request,response,genTlHolidaymstBean);
				
		    }
		
		   else if(action.equals("holiday_recall.holiday"))
			{	
			    ServletOutputStream out = response.getOutputStream();
				String holiday =request.getParameter(ReqtParamNameConst.KEYID);
				
				GenTlHolidaymstBean genTlHolidaymstBean = new GenTlHolidaymstBean(FormModes.modify);
				
				httpSession.removeAttribute("genTlHolidaymstBean");
				httpSession.setAttribute("genTlHolidaymstBean", genTlHolidaymstBean);
				
				httpSession.removeAttribute("genTlHolidaymstServlet");
				GenTlHolidaymst genTlHolidaymstRecal = genTlHolidaymstService.select(holiday);
				
				httpSession.setAttribute("genTlHolidaymstServlet" ,genTlHolidaymstRecal);
				JSONObject  holidayData =  UIUtils.fromTpmModel(genTlHolidaymstRecal);
				
				JSONObject returndata = new JSONObject();
				returndata.put("holidayData", holidayData);
				out.print(returndata.toString());
				
			}	
			
		   else if(action.equals("holiday_recalFlag.holiday"))
			{	
			    
				String flag =request.getParameter("flag");
				PrintWriter out = response.getWriter();		
				GenTlHolidaymstBean genTlHolidaymstBean = new GenTlHolidaymstBean(FormModes.modify);
				
				httpSession.removeAttribute("genTlHolidaymstBean");
				httpSession.setAttribute("genTlHolidaymstBean", genTlHolidaymstBean);
				
				httpSession.removeAttribute("genTlHolidaymstServlet");
				GenTlHolidaymst genTlHolidaymstRecal =  genTlHolidaymstService.flagSelect(flag);
				
				httpSession.setAttribute("genTlHolidaymstServlet" ,genTlHolidaymstRecal);
				JSONObject  holidayData =  UIUtils.fromTpmModel(genTlHolidaymstRecal);
				
				JSONObject returndata = new JSONObject();
				returndata.put("holidayData", holidayData);
				out.print(returndata.toString());
				
			}	
		
			if (dispatchUrl != null)
			{
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
    	}
	}	
    private void saveHoliday(HttpServletRequest request, HttpServletResponse response,GenTlHolidaymstBean genTlHolidaymstBean  ) throws IOException{
		
    	UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);
    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		if( genTlHolidaymstBean == null)
    			genTlHolidaymstBean = new GenTlHolidaymstBean(FormModes.create);
    		
    		GenTlHolidaymst newGenTlHolidaymst = new GenTlHolidaymst();
    		newGenTlHolidaymst.setHolmCreatedby(user.getUsrm_ccno());
			
    		newGenTlHolidaymst =(GenTlHolidaymst)UIUtils.setBeanProperties((Object)newGenTlHolidaymst,request);
    		

    		GenTlHolidaymst existGenTlAssemblymst = (GenTlHolidaymst)httpSession.getAttribute("genTlHolidaymstServlet");
			
			genTlHolidaymstBean = (GenTlHolidaymstBean) UIUtils.setBeanProperties((Object) genTlHolidaymstBean, request);
			
			try{
				boolean insert = true;
				if( ! UIUtils.isValidKeyId( newGenTlHolidaymst.getHolmKeyid() ))
				{	
					existGenTlAssemblymst = genTlHolidaymstService.create(newGenTlHolidaymst,existGenTlAssemblymst,genTlHolidaymstBean);
				}
				else{
					CommonFunctions.debugMsg("Update............");
					insert = false;
					existGenTlAssemblymst = genTlHolidaymstService.update(newGenTlHolidaymst,existGenTlAssemblymst,genTlHolidaymstBean);
				}				
			
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("HolmKeyid",existGenTlAssemblymst.getHolmKeyid() );
				
				JSONObject successData = new JSONObject();
			    String msgPropertyIdnt;
			 
			 if( insert){
				msgPropertyIdnt = "success-save";
			 }else
				msgPropertyIdnt = "success-update";
			 
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
		
			
			successData.put("HolmKeyid", existGenTlAssemblymst.getHolmKeyid());
			JSONObject returnData = new JSONObject();
			returnData.put("formClear",false);
			returnData.put("successData", successData);		
			returnData.put("flag",existGenTlAssemblymst.getHolmHolidayflag());	
			returnData.put("date",existGenTlAssemblymst.getHolmDate());	
			httpSession.removeAttribute("genTlHolidaymstServlet");
			//httpSession.setAttribute( "genTlHolidaymstServlet", existGenTlAssemblymst);
			httpSession.removeAttribute("genTlHolidaymstBean");
			
	    	PrintWriter  out = response.getWriter();
			out.print(returnData.toString());
			out.close();
			
			
		}catch(ValidationExceptions e)
		{			
			PrintWriter  out = response.getWriter();
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"HolidayCreation");
			errMessage.put("formActionMode",genTlHolidaymstBean.getFormActionMode());
			out.print(errMessage.toString());
		}catch(Exception e)
		{	
			PrintWriter  out = response.getWriter();
			JSONObject err = new JSONObject();
			//err.put("tpmException", "Data Not Saved");
			err.put("tpmException","Holiday already Exists For Selected Date And Factory");
			
			JSONObject returnData = new JSONObject();
			returnData.put("successData", "alerady Exists");
			returnData.put("successData", returnData);
			
			out.print(err.toString());
		}
    }	
 }
  

	private void deleteHoliday(HttpServletRequest request,HttpServletResponse response, GenTlHolidaymstBean genTlHolidaymstBean) throws IOException
	{
		UIUtils.displayRequestParamsValue(request);
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		if( genTlHolidaymstBean == null)
    			genTlHolidaymstBean = new GenTlHolidaymstBean(FormModes.create);
    		
    		GenTlHolidaymst existGenTlHolidaymst = (GenTlHolidaymst)httpSession.getAttribute("genTlHolidaymstServlet"); 
    		GenTlHolidaymst newgenTlHolidaymst = new GenTlHolidaymst();
    		newgenTlHolidaymst.setHolmCreatedby(user.getUsrm_ccno());
    	
    		newgenTlHolidaymst =(GenTlHolidaymst)UIUtils.setBeanProperties((Object)newgenTlHolidaymst,request);
    		genTlHolidaymstBean =(GenTlHolidaymstBean)UIUtils.setBeanProperties((Object)genTlHolidaymstBean,request);
    		//genTlHolidaymstBean =(GenTlHolidaymstBean) UIUtils.setBeanProperties((Object)genTlHolidaymstBean,request);
    		
		try{
			
			existGenTlHolidaymst = genTlHolidaymstService.delete(newgenTlHolidaymst,genTlHolidaymstBean);
			
			existGenTlHolidaymst.setHolmKeyid("N");			
			JSONObject mode = new JSONObject();
		
			JSONObject persistentData = new JSONObject(); 
			persistentData.put("HolmKeyid",existGenTlHolidaymst.getHolmKeyid());
			//persistentData.put("fromBean", formBeanIdentifier);
			
			JSONObject forwardData = new JSONObject();
			forwardData.put("HolmKeyid",existGenTlHolidaymst.getHolmKeyid());
			//System.out.println("servlet out put:" + forwardData);
			mode.put("forwardData", forwardData);
			mode.put("persistentData", persistentData);
			
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));

			successData.put("formActionMode",genTlHolidaymstBean.getFormActionMode() );
			successData.put("HolmKeyid", existGenTlHolidaymst.getHolmKeyid());
			JSONObject returnData = new JSONObject();		
			
			httpSession.removeAttribute("genTlHolidaymstServlet");
			httpSession.removeAttribute("genTlHolidaymstBean");
			
			returnData.put("formClear",false);
			returnData.put("successData", successData);		
			out.print(returnData.toString());
				
		}catch(ValidationExceptions e)
		{
			CommonFunctions.debugMsg("test...");
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"holidayCreation");
			errMessage.put("formActionMode",genTlHolidaymstBean.getFormActionMode());
			out.print(errMessage.toString());
			CommonFunctions.debugMsg("test..."+errMessage);
		}catch(BusinessApplicationExceptions e)
		{ 
			System.out.println("BusinessApplicationExcepions"  );
			JSONObject successData = UIUtils.businessValidationExceptions(e.toString(),"holidayCreation");
			//out.print(errMessage.toString());
			successData.put("msg", "Original Id Exists");
			JSONObject returnData = new JSONObject();	
			
			returnData.put("formClear",false);
			returnData.put("successData", successData);	
						
		}catch(Exception e)
		{
			System.out.println("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			//err.put("tpmException", "Data Not Deleted");
			err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
			out.print(err.toString());
		}
    }
	
}
	

}
