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
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.BAL_SapTlMaintenanceOrderdtl;
//import com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.model.BAL_SapTlSparesreplaced;
import com.akranta.tpm.service.BAL_SAPInfoService;
import com.akranta.tpm.service.impl.BAL_SAPInfoServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_SAPInfoServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	BAL_SAPInfoService sapInfoService ;
	public BAL_SAPInfoServlet(){
		/*try {
			abnormalityService = new AbnormalityServiceImpl();
		} catch (Exception e) {
			
		//	e.printStackTrace();
		}
		*/
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		sapInfoService = (BAL_SAPInfoServiceImpl)BAL_UIUtils.getServiceObject(request, "BAL_SAPInfoServiceImpl");
		String action = BAL_UIUtils.getActionPart(request);
		CommonFunctions.debugMsg(" action " + action);
		ComboFilter comboFilter = new ComboFilter();
		comboFilter=BAL_UIUtils.fillComboFilter(request);
		
		// String dispatchUrl = null;
		HttpSession httpSession = request.getSession(false);

		
		 if(action.equals("SAPMaintenanceOrder_input.sapinfo"))
		{
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/SAPMaintenanceOrder.jsp");					
			rd.forward(request, response);
			
		}
		
		 else if(action.equals("SAPMaintOpnAndMatrl_input.sapinfo")){
				request.setAttribute("orderNo", request.getParameter("orderNo"));
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/SAPOrderOpnAndMatrl.jsp");					
				rd.forward(request, response);
			}else if(action.equals("SAPMaintOperation_getCol.sapinfo")){
				PrintWriter out = response.getWriter();
				try {
					String orderNo = request.getParameter("orderNo");
				   List<String[]> operationGrid = sapInfoService.getOperationGrid(orderNo);
				   JSONObject colmodel = getSapTableModel(operationGrid.get(0),operationGrid.get(0),'B',false,false) ;
				   colmodel.set("tableHeight", "85%%");
				   out.println(colmodel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(action.equals("SAPMaintOperation_getData.sapinfo")){
				try
				{   
					String orderNo = request.getParameter("orderNo");
		    	   List<String[]> operationGrid = sapInfoService.getOperationGrid(orderNo);
		    	  
		    	   JSONObject colmodel = BAL_UIUtils.convertToJqGridTableObject(operationGrid, request, 1, 0);
		    	   PrintWriter out = response.getWriter();	
		    	   out.println(colmodel);  

			    }catch(Exception e)
				{
					e.printStackTrace();
				}
			}else if(action.equals("SAPMaintMaterial_getCol.sapinfo")){
				PrintWriter out = response.getWriter();
				try {
					String orderNo = request.getParameter("orderNo");
				   List<String[]> materialGrid = sapInfoService.getMaterialGrid(orderNo);
				   JSONObject colmodel = getSapTableModel(materialGrid.get(0),materialGrid.get(0),'B',false,false) ;
				   colmodel.set("tableHeight", "85%%");
				   out.println(colmodel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(action.equals("SAPMaintMaterial_getData.sapinfo")){
				try
				{   
					String orderNo = request.getParameter("orderNo");
				   List<String[]> materialGrid = sapInfoService.getMaterialGrid(orderNo);
				  
				   JSONObject colmodel = BAL_UIUtils.convertToJqGridTableObject(materialGrid, request, 1, 0);
				   PrintWriter out = response.getWriter();	
				   out.println(colmodel);  

				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		 
			else if(action.equals("SAPMaintenanceOrder_getCol.sapinfo")){
			
				List<String[]> ctrlResGrid = null;
				PrintWriter out = response.getWriter();
				try {
					CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",true);
					ctrlResGrid = sapInfoService.getFillGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = ctrlResGrid.get(1);
					String [] colHeaderHead = ctrlResGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);
	
				} catch (Exception e) {
					e.printStackTrace();
				}	
			
		}
		
        else if(action.equals("SAPMaintenanceOrder_getData.sapinfo")){
        	try
			{   

				BAL_UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",false);
				
				List<String[]> UpstreamGrid   = sapInfoService.getFillGrid(commonFilter);
				
  			 	CommonFunctions.debugMsg("equipmentQueryList " + UpstreamGrid.size());
				PrintWriter out = response.getWriter();
  			 	JSONObject UpstreamData = BAL_UIUtils.convertToJqGridTableObject(UpstreamGrid,request,2,0); 
  			 	out.println(UpstreamData);  

		    }catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		
		}

        else if(action.equals("SAPMaintenanceOrder_getExcel.sapinfo")){
        	CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",false);
        	JSONObject colmodel = BAL_UIUtils.getXlColModel(request, response);
        	colmodel.put("title","Maintenance Order");
        	String format = ExcelUtils.getFormat(request);
        	Workbook wb = sapInfoService.getMaintExcel(colmodel,format,commonFilter);
        	//ExcelUtils.writeToResponse(response, wb, "MaintenanceOrder", format);
        }

		
        else if (action.equals("SAPInfo_input.sapinfo")) {
			String type = request.getParameter("type");
			//SAPInfo_input.sapinfo?type=Equip
			CommonFunctions.debugMsg("type::::"+type);
			request.setAttribute("type", type);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/SAPInfo.jsp");
			rd.forward(request, response);
			CommonFunctions.debugMsg(" response " + response);
		} 
		else if(action.equals("SAPInfo_getCol.sapinfo")){
			CommonFunctions.debugMsg("achuievenen");
			PrintWriter out = response.getWriter();
					
				CommonFilter commonFilter = populateCommonFilter(request,"SAPInfoFilter",true);
				try {
						String type = request.getParameter("type");	
						CommonFunctions.debugMsg("Sap Info Type:"+type);
						commonFilter.setType(type);
						List<String[]> SapInfoList = sapInfoService.getSAPInfoList(commonFilter);
						JSONObject jsonObject = getTableModel(SapInfoList);
						CommonFunctions.debugMsg("Table model");
						// httpSession.removeAttribute("FivesauditColmodel");
						// httpSession.setAttribute("FivesauditColmodel", colmodel);
						CommonFunctions.debugMsg("jsonObject "+jsonObject);
						out.println(jsonObject);
						
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else if(action.equals("SAPInfo_getData.sapinfo")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"SAPInfoFilter",true);
			try {
				String type = request.getParameter("type");	
				CommonFunctions.debugMsg("Sap Info Type:"+type);
				commonFilter.setType(type);
				List<String[]> SapInfoList = sapInfoService.getSAPInfoList(commonFilter);
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("get data method1");
				JSONObject QMreports = BAL_UIUtils.convertToJqGridTableObject(
						SapInfoList, request, 2, 0);
				out.println(QMreports);					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

		}
			else if(action.equals("Sapstackinformation_input.sapinfo")){
				
				String plantName = request.getParameter( "plantName");
	    	    String plantId = request.getParameter( "factId");
	    	    
	    	    request.setAttribute("plantName", plantName);
	    	    request.setAttribute("plantId", plantId);
	    	    
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SapStackInformation.jsp");					
				rd.forward(request, response);
				
			}
	       else if(action.equals("Sapstackinformation_getCol.sapinfo")){
	    	 
	    	    PrintWriter out = response.getWriter();	
				String tableModel=BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.SAPStackInformationProperty", "SapInformationRelated");
				JSONObject colmodel = JSONObject.fromString(tableModel);
				out.println(colmodel);
				
			}
	       else if(action.equals("Sapstackinformation_getData.sapinfo")){
				

				List<String[]> MachineGrid = sapInfoService.getSAPinfofillgriddata();
				CommonFunctions.debugMsg("Data is enter or not" + MachineGrid.get(0)[0]);
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("get data method1");
				JSONObject machinegrid = BAL_UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);
			}
	       else if(action.equals("Sapstackstorage_input.sapinfo")){
	    	   String plantName = request.getParameter( "plantName");
	    	    String plantId = request.getParameter( "factId");
	    	    
	    	    request.setAttribute("plantName", plantName);
	    	    request.setAttribute("plantId", plantId);
	    	   
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SapStackInformation.jsp");					
				rd.forward(request, response);
				
			}
	       else if(action.equals("Sapstackstorage_getCol.sapinfo")){
	    	   PrintWriter out = response.getWriter();	
			   String tableModel=BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.SAPStackInformationProperty", "SapInformationStorage");
			   JSONObject colmodel = JSONObject.fromString(tableModel);
			   out.println(colmodel);
				
			}
	       else if(action.equals("Sapstackstorage_getData.sapinfo")){

				List<String[]> MachineGrid = sapInfoService.getSAPStoragefillgriddata();
				CommonFunctions.debugMsg("Data is enter or not" + MachineGrid.get(0)[0]);
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("get data method1");
				JSONObject machinegrid = BAL_UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);
				
			}
	       else if(action.equals("SapstackResult_input.sapinfo")){
	    	   
	    	    String plantName = request.getParameter( "plantName");
	    	    String plantId = request.getParameter( "factId");
	    	    
	    	    request.setAttribute("plantName", plantName);
	    	    request.setAttribute("plantId", plantId);
	    	    
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SapStackInformation.jsp");					
				rd.forward(request, response);
				
			}
	       else if(action.equals("SapstackResult_getCol.sapinfo")){
	    	   PrintWriter out = response.getWriter();	
			   String tableModel=BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.SAPStackInformationProperty", "SapInformationResult");
			   JSONObject colmodel = JSONObject.fromString(tableModel);
			   out.println(colmodel);
				
			}
	       else if(action.equals("SapstackResult_getData.sapinfo")){
	    	   
	    	    String type = request.getParameter("type");
	    	    List<String[]> MachineGrid = sapInfoService.getSAPResultfillgriddata(type);

				PrintWriter out = response.getWriter();
				JSONObject machinegrid = BAL_UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid); 
			}
	       else if(action.equals("SAPRelatedData_save.sapinfo")){
	    	   saveSapRelateData(request,response);
	       }
	       else if(action.equals("sapspareInfo_save.sapinfo")){
	    	   try{
	    		   System.out.println("saveSapSpareInfo");
	    	   saveSapSpareInfo(request,response);
	    	   }
	    	   catch(Exception e){
	    		   e.printStackTrace();
	    	   }
	       }
	       else if(action.equals("crm_input.sapinfo")){
	    	   BAL_UIUtils.forwardRequest(request, response, "/pages/Sap/crmmaster.jsp");
	       }
	       else if(action.equals("crm_getCol.sapinfo")){
	    	   List<String[]> crmMasterData = sapInfoService.getAllCrmmasterData();
	    	   
	    	   JSONObject colmodel = getSapTableModel(crmMasterData.get(0),crmMasterData.get(0),'M',false,false) ;
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(colmodel);
	    	}
	       else if(action.equals("crm_getData.sapinfo")){
	    	   List<String[]> crmMasterData = sapInfoService.getAllCrmmasterData();
	    	  
	    	   JSONObject crmMaster = BAL_UIUtils.convertToJqGridTableObject(crmMasterData, request, 1, 0);
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(crmMaster);
	       }
	       else if(action.equals("crmBillItems_input.sapinfo")){
	    	   String crmNo = request.getParameter("crmNo");
	    	   request.setAttribute("crmNo", crmNo);
	    	   BAL_UIUtils.forwardRequest(request, response, "/pages/Sap/crmbillitems.jsp");
	    	   CommonFunctions.debugMsg(" crmNo " + crmNo);
	    	   
	       }
	       else if(action.equals("crmBillItems_getCol.sapinfo")){
	    	   String crmNo = request.getParameter("crmNo");
	    	   List<String[]> crmMasterData = sapInfoService.getCrmBillItemsData(crmNo);
	    	   
	    	   JSONObject colmodel = getSapTableModel(crmMasterData.get(0),crmMasterData.get(0),'B',false,false) ;
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(colmodel);
	    	}
	       else if(action.equals("crmBillItems_getData.sapinfo")){
	    	   String crmNo = request.getParameter("crmNo");
	    	   List<String[]> crmMasterData = sapInfoService.getCrmBillItemsData(crmNo);
	    	  
	    	   JSONObject crmMaster = BAL_UIUtils.convertToJqGridTableObject(crmMasterData, request, 1, 0);
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(crmMaster);
	       }
	       else if(action.equals("crmNotes_input.sapinfo")){
	    	   String crmNo = request.getParameter("crmNo");
	    	   CommonFunctions.debugMsg(" crmNo " + crmNo);
	    	   request.setAttribute("crmNo", crmNo);
	    	   BAL_UIUtils.forwardRequest(request, response, "/pages/Sap/crmNotes.jsp");
	    	   
	       }
	       else if(action.equals("crmNotes_getCol.sapinfo")){
	    	   String crmNo = request.getParameter("crmNo");
	    	   List<String[]> crmMasterData = sapInfoService.getCrmNotesData(crmNo);
	    	   
	    	   JSONObject colmodel = getSapTableModel(crmMasterData.get(0),crmMasterData.get(0),'N',false,false) ;
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(colmodel);
	    	}
	       else if(action.equals("crmNotes_getData.sapinfo")){
	    	   String crmNo = request.getParameter("crmNo");
	    	   List<String[]> crmMasterData = sapInfoService.getCrmNotesData(crmNo);
	    	  
	    	   JSONObject crmMaster = BAL_UIUtils.convertToJqGridTableObject(crmMasterData, request, 1, 0);
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(crmMaster);
	       }
		   else if(action.equals("downTime_input.sapinfo")){
	    	   BAL_UIUtils.forwardRequest(request, response, "/pages/Sap/downtime.jsp");
	    	   
	       }
		   else if(action.equals("downTime_getCol.sapinfo")){
			   CommonFilter commonFilter = populateCommonFilter(request,"SAPdownTimeCommonFilter",true);
			   List<String[]> crmMasterData = sapInfoService.getDownTimeData(commonFilter);
			   
			   JSONObject colmodel = getSapTableModel(crmMasterData.get(0),crmMasterData.get(1),'D',true,true) ;
			   PrintWriter out = response.getWriter();
			   colmodel.set("tableHeight", "80%%");
			   out.println(colmodel);
			}
			else if(action.equals("downTime_getData.sapinfo")){
			   CommonFilter commonFilter = populateCommonFilter(request,"SAPdownTimeCommonFilter",true);
			   
			   int rowCount = sapInfoService.getDownTimeCount(commonFilter);
			   Long totalCnt = (long)rowCount;
			   commonFilter.setTotalRecordCnt(totalCnt);
			   
			   List<String[]> crmMasterData = sapInfoService.getDownTimeData(commonFilter);
			   CommonFunctions.debugMsg(rowCount+".....rowCount totalCnt...."+totalCnt);
			   CommonFunctions.debugMsg("from...."+commonFilter.getFromRow()+"....to...."+commonFilter.getToRow());
			   JSONObject crmMaster = BAL_UIUtils.convertToJqGridTableObject(crmMasterData, request, 2, 0,totalCnt+2);
			   PrintWriter out = response.getWriter();	
			   out.println(crmMaster);
			}
	
	       else if(action.equals("notification_input.sapinfo")){
	    	   BAL_UIUtils.forwardRequest(request, response, "/pages/Sap/notification.jsp");
	    	   
	       }
	       else if(action.equals("notification_getCol.sapinfo")){
	    	   
	    	   List<String[]> notificationData = sapInfoService.getNotificationData();
	    	   
	    	   JSONObject colmodel = getSapTableModel(notificationData.get(0),notificationData.get(0),'D',true,true) ;
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(colmodel);
	    	}
	       else if(action.equals("notification_getData.sapinfo")){
	    	  
	    	   List<String[]> notificationData = sapInfoService.getNotificationData();
	    	  
	    	   JSONObject crmMaster = BAL_UIUtils.convertToJqGridTableObject(notificationData, request, 1, 0);
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(crmMaster);
	       }	

		 //added by Ashok on 22-May-2014
	       else if(action.equals("SAPDownTimeArea_input.sapinfo"))
			{
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/SAPDownTime.jsp");	
				rd.forward(request, response);
				
			}	
			 
	         else if(action.equals("SAPDownTimeArea_getCol.sapinfo")){
				
				List<String[]> DownTimeAreaGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"SAPDownTimeCommonFilter",true);
				    DownTimeAreaGrid = sapInfoService.getFillDownTimeGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					//jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = DownTimeAreaGrid.get(1);
					String [] colHeaderHead = DownTimeAreaGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "94%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("SAPDownTimeArea_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"SAPDownTimeCommonFilter",false);
					
					List<String[]> DownTimeAreaGrid   = sapInfoService.getFillDownTimeGrid(commonFilter);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + DownTimeAreaGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject DownTimeAreaData = BAL_UIUtils.convertToJqGridTableObject(DownTimeAreaGrid,request,2,0); 
	  			 	out.println(DownTimeAreaData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			
	       }
	       
	       else if(action.equals("SAPDownReasons_input.sapinfo"))
			{
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/SAPDownTimeReason.jsp");	
				rd.forward(request, response);
				
			}	
			 
	        else if(action.equals("SAPDownReasons_getCol.sapinfo")){
				
				List<String[]> DownReasonsGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"SAPDownTimeCommonFilter",true);
				    DownReasonsGrid = sapInfoService.getFillDownTimeReasonsGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					//jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = DownReasonsGrid.get(1);
					String [] colHeaderHead = DownReasonsGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "94%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	       else if(action.equals("SAPDownReasons_getData.sapinfo")){
	       	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"SAPDownTimeCommonFilter",false);
					
					List<String[]> DownTimeReasonGrid   = sapInfoService.getFillDownTimeReasonsGrid(commonFilter);
					
	 			 	CommonFunctions.debugMsg("equipmentQueryList " + DownTimeReasonGrid.size());
					PrintWriter out = response.getWriter();
	 			 	JSONObject DownTimeGridReasonData = BAL_UIUtils.convertToJqGridTableObject(DownTimeReasonGrid,request,2,0); 
	 			 	out.println(DownTimeGridReasonData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			
	      }//NPC_input.sapinfo,REPULP_input.sapinfo
		 
		 // TaskList_input.sapinfo,ProductionDetails_input.sapinfo
		 
		 
		 if(action.equals("TaskList_input.sapinfo"))
			{
				String mode=request.getParameter("mode");
				CommonFunctions.debugMsg(" Sbu "+mode);
				request.setAttribute("mode", mode);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/TaskList.jsp");					
				rd.forward(request, response);
			}
			
			else if(action.equals("TaskList_getCol.sapinfo")){
				
				List<String[]> hrmsGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"NPCCommonFilter",true);
				    
				    String Mode=request.getParameter("Mode");
				    CommonFunctions.debugMsg(" Inside Get Col :: Mode " +Mode);
				    hrmsGrid = sapInfoService.getTaskListFillGrid(commonFilter,Mode);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = hrmsGrid.get(1);
					String [] colHeaderHead = hrmsGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					httpSession.setAttribute("HrmsDataColmodel", colmodel);
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("TaskList_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"NPCCommonFilter",false);

					String Mode=request.getParameter("Mode");

					List<String[]> HrmsGridGrid   = sapInfoService.getTaskListFillGrid(commonFilter,Mode);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + HrmsGridGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridData = BAL_UIUtils.convertToJqGridTableObject(HrmsGridGrid,request,2,0); 
	  			 	out.println(HrmsGridData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			}

		 
	        else if(action.equals("MaterialMaster_input.sapinfo"))
			{
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/MaterialMaster.jsp");					
				rd.forward(request, response);
			}
			
			else if(action.equals("MaterialMaster_getCol.sapinfo")){
				
				List<String[]> productiondetailsGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
				
				
             try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",true);
				    
				    CommonFunctions.debugMsg(" Checking the Value ::  ");
				    
				    productiondetailsGrid = sapInfoService.getMaterialMasterFillGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					CommonFunctions.debugMsg(" Checking the Value :: 1  ");
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					CommonFunctions.debugMsg(" Checking the Value :: 2  ");
					gridColModel.setHeaderNum(1);//9
					CommonFunctions.debugMsg(" Checking the Value :: 3  ");
					String [] colHeader = productiondetailsGrid.get(1);
					String [] colHeaderHead = productiondetailsGrid.get(0);
					CommonFunctions.debugMsg(" Checking the Value :: 4  ");
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					httpSession.setAttribute("HrmsDataColmodel", colmodel);
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	
	
			}
			
	        else if(action.equals("MaterialMaster_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",false);

					List<String[]> ProductionDetailsGrid   = sapInfoService.getMaterialMasterFillGrid(commonFilter);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + ProductionDetailsGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridData = BAL_UIUtils.convertToJqGridTableObject(ProductionDetailsGrid,request,2,0); 
	  			 	out.println(HrmsGridData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			}

		 
		 
		 
		 
		 
		 
		 
		 
		 
	        else if(action.equals("ProductionDetails_input.sapinfo"))
			{
				String mode=request.getParameter("mode");
				CommonFunctions.debugMsg(" Sbu "+mode);
				request.setAttribute("mode", mode);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/ProductionDetails.jsp");					
				rd.forward(request, response);
			}
			
			else if(action.equals("ProductionDetails_getCol.sapinfo")){
				
				List<String[]> productiondetailsGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"NPCCommonFilter",true);
				    
				    String Mode=request.getParameter("Mode");
				    CommonFunctions.debugMsg(" Inside Get Col :: Mode " +Mode);
				    productiondetailsGrid = sapInfoService.getProductionDetailsFillGrid(commonFilter,Mode);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = productiondetailsGrid.get(1);
					String [] colHeaderHead = productiondetailsGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					httpSession.setAttribute("HrmsDataColmodel", colmodel);
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("ProductionDetails_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"NPCCommonFilter",false);

					String Mode=request.getParameter("Mode");

					List<String[]> ProductionDetailsGrid   = sapInfoService.getProductionDetailsFillGrid(commonFilter,Mode);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + ProductionDetailsGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridData = BAL_UIUtils.convertToJqGridTableObject(ProductionDetailsGrid,request,2,0); 
	  			 	out.println(HrmsGridData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			}

		    else if(action.equals("NPC_input.sapinfo"))
			{
				String mode=request.getParameter("mode");
				CommonFunctions.debugMsg(" Sbu "+mode);
				request.setAttribute("mode", mode);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/NPC.jsp");					
				rd.forward(request, response);
			}
			
		   
		 
			else if(action.equals("NPC_getCol.sapinfo")){
				
				List<String[]> hrmsGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"NPCCommonFilter",true);
				    
				    String Mode=request.getParameter("Mode");
				    CommonFunctions.debugMsg(" Inside Get Col :: Mode " +Mode);
				    hrmsGrid = sapInfoService.getNPCFillGrid(commonFilter,Mode);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = hrmsGrid.get(1);
					String [] colHeaderHead = hrmsGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					httpSession.setAttribute("HrmsDataColmodel", colmodel);
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("NPC_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"NPCCommonFilter",false);

					String Mode=request.getParameter("Mode");

					List<String[]> HrmsGridGrid   = sapInfoService.getNPCFillGrid(commonFilter,Mode);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + HrmsGridGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridData = BAL_UIUtils.convertToJqGridTableObject(HrmsGridGrid,request,2,0); 
	  			 	out.println(HrmsGridData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			}

		  else if(action.equals("REPULP_input.sapinfo"))
			{
				String mode=request.getParameter("mode");
				CommonFunctions.debugMsg(" Sbu "+mode);
				request.setAttribute("mode", mode);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/REPULP.jsp");					
				rd.forward(request, response);
			}
			
			else if(action.equals("REPULP_getCol.sapinfo")){
				
				List<String[]> hrmsGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"RepulpCommonFilter",true);
				    
				    String Mode=request.getParameter("Mode");
				    CommonFunctions.debugMsg(" Inside Get Col :: Mode " +Mode);
				    hrmsGrid = sapInfoService.getREPULPFillGrid(commonFilter,Mode);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = hrmsGrid.get(1);
					String [] colHeaderHead = hrmsGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					httpSession.setAttribute("HrmsDataColmodel", colmodel);
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("REPULP_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"NPCCommonFilter",false);

					String Mode=request.getParameter("Mode");

					List<String[]> HrmsGridGrid   = sapInfoService.getREPULPFillGrid(commonFilter,Mode);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + HrmsGridGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridData = BAL_UIUtils.convertToJqGridTableObject(HrmsGridGrid,request,2,0); 
	  			 	out.println(HrmsGridData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			}

		 if(action.equals("HRMS_input.sapinfo"))
			{
				String mode=request.getParameter("mode");
				CommonFunctions.debugMsg(" Sbu "+mode);
				request.setAttribute("mode", mode);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/Hrms.jsp");					
				rd.forward(request, response);
			}
			
			else if(action.equals("HRMS_getCol.sapinfo")){
				
				List<String[]> hrmsGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",true);
				    
				    String Mode=request.getParameter("Mode");
				    CommonFunctions.debugMsg(" Inside Get Col :: Mode " +Mode);
				    hrmsGrid = sapInfoService.getHRMSFillGrid(commonFilter,Mode);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = hrmsGrid.get(1);
					String [] colHeaderHead = hrmsGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					httpSession.setAttribute("HrmsDataColmodel", colmodel);
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("HRMS_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",false);

					String Mode=request.getParameter("Mode");

					List<String[]> HrmsGridGrid   = sapInfoService.getHRMSFillGrid(commonFilter,Mode);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + HrmsGridGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridData = BAL_UIUtils.convertToJqGridTableObject(HrmsGridGrid,request,2,0); 
	  			 	out.println(HrmsGridData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			}
		 
	        else if(action.equals("HRMS_getExcel.sapinfo")){
	        	try
				{   

	        		httpSession = request.getSession(false);
	        		String Mode=request.getParameter("Mode");
	        		CommonFunctions.debugMsg(" Inside CommonFunctions :: "+Mode);
	    			CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",false);
	    			JSONObject colmodel = (JSONObject) httpSession.getAttribute("HrmsDataColmodel");
	    			colmodel.put("title","HRMS Report");
	                String format = ExcelUtils.getFormat(request);
	    			
	    			Workbook wb = sapInfoService.getHrmsExportExcel(colmodel,format,commonFilter,Mode);
	    			ExcelUtils.writeToResponse(response, wb, "HRMSReport", format);

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}

	        }		
		 
	  if(action.equals("Employeemst_input.sapinfo"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/HrmsEmployee.jsp");					
				rd.forward(request, response);
			}
			
			else if(action.equals("Employeemst_getCol.sapinfo")){
				
				List<String[]> hrmsEmployeeGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",true);
				    
				    hrmsEmployeeGrid = sapInfoService.getHRMSEmployeeFillGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = hrmsEmployeeGrid.get(1);
					String [] colHeaderHead = hrmsEmployeeGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					httpSession.setAttribute("EmployeeDataColmodel", colmodel);
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("Employeemst_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"SAPMaintenanceOrderCommonFilter",false);

	                List<String[]> HrmsEmployeeGrid   = sapInfoService.getHRMSEmployeeFillGrid(commonFilter);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + HrmsEmployeeGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridEmployeeData = BAL_UIUtils.convertToJqGridTableObject(HrmsEmployeeGrid,request,2,0); 
	  			 	out.println(HrmsGridEmployeeData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			}
	  
	        else if(action.equals("Employeemst_getExcel.sapinfo")){
	        	try
				{   

	        		httpSession = request.getSession(false);
	        		CommonFilter commonFilter = populateCommonFilter(request,"ShiftMaintenanceOrderCommonFilter",false);
	    			JSONObject colmodel = (JSONObject) httpSession.getAttribute("EmployeeDataColmodel");
	    			colmodel.put("title","Employee Report");
	                String format = ExcelUtils.getFormat(request);
	    			
	    			Workbook wb = sapInfoService.getEmployeemstExportExcel(colmodel,format,commonFilter);
	    			ExcelUtils.writeToResponse(response, wb, "EmployeeReport", format);

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}

	        }		
	  
	  
			
			
			if(action.equals("Shiftmst_input.sapinfo"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Sap/HrmsShift.jsp");					
				rd.forward(request, response);
			}
			
			else if(action.equals("Shiftmst_getCol.sapinfo")){
				
				List<String[]> hrmsShiftGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();Mode
			
				try {
					
				    CommonFilter commonFilter = populateCommonFilter(request,"ShiftMaintenanceOrderCommonFilter",true);
				    
				    hrmsShiftGrid = sapInfoService.getHRMSShiftFillGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = hrmsShiftGrid.get(1);
					String [] colHeaderHead = hrmsShiftGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = BAL_UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					
					CommonFunctions.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "106%%");
					colmodel.set("tableHeight", "80%%");
					httpSession.setAttribute("ShiftColmodel", colmodel);
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}	

				
			}
			
	        else if(action.equals("Shiftmst_getData.sapinfo")){
	        	try
				{   

					BAL_UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"ShiftMaintenanceOrderCommonFilter",false);

	                List<String[]> HrmsShiftGrid   = sapInfoService.getHRMSShiftFillGrid(commonFilter);
					
	  			 	CommonFunctions.debugMsg("equipmentQueryList " + HrmsShiftGrid.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject HrmsGridShiftData = BAL_UIUtils.convertToJqGridTableObject(HrmsShiftGrid,request,2,0); 
	  			 	out.println(HrmsGridShiftData);  

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}

	        }
	        else if(action.equals("Shiftmst_getExcel.sapinfo")){
	        	try
				{   

	        		httpSession = request.getSession(false);
	        		String Mode=request.getParameter("Mode");
	        		CommonFunctions.debugMsg(" Inside CommonFunctions :: "+Mode);
	    			CommonFilter commonFilter = populateCommonFilter(request,"ShiftMaintenanceOrderCommonFilter",false);
	    			JSONObject colmodel = (JSONObject) httpSession.getAttribute("ShiftColmodel");
	    			colmodel.put("title","Shift Report");
	                String format = ExcelUtils.getFormat(request);
	    			
	    			Workbook wb = sapInfoService.getShiftExportExcel(colmodel,format,commonFilter,Mode);
	    			ExcelUtils.writeToResponse(response, wb, "ShiftReport", format);

			    }catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}

	        }
	        else if (action.equals("sapOrderType.sapinfo")){
	        	
	        	//ComboFilter comboFilter=UIUtils.fillComboFilter(request);
	    		//List<ComboBox>  orderType = sapInfoService.getOrdertype(comboFilter);
	    		//UIUtils.writeComboBox(response, orderType,comboFilter);
	        	String docType=request.getParameter("docType");
	            CommonFunctions.debugMsg("Doc Type In SAp"+docType);
	        	ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> orderType = sapInfoService.getOrdertype(docType,currentFilter);
				CommonFunctions.debugMsg(response+"......Combo Respmne........" + orderType);
				//BAL_UIUtils.writeComboBox(response,orderType,comboFilter);
				BAL_UIUtils.writeComboBox(response,orderType,currentFilter);
	    			
	        	
	        }
	        else if (action.equals("updateSparesQty.sapinfo")){
	        	
	        	updateSpareInfo(request,response);
	        	
	        }
	        else if( action.equals("SparesInfo_delete.sapinfo")){
	       /* 	     httpSession = request.getSession(false);
	            	ServletOutputStream out = response.getOutputStream();
	            	SapTlSparesreplaced newSapTlSparesreplaced = new SapTlSparesreplaced();

	        		try{
	        			String spareSAPData =request.getParameter("spareSAPData");
	        			JSONArray spareReplacedjson = null;
	        			List<SapTlSparesreplaced> sapTlSparesreplacedList  = null;
    					CommonFunctions.debugMsg("spareSAPData  "+spareSAPData);


	        			if(UIUtils.isValidKeyId(spareSAPData)){
	        			
	        					spareReplacedjson = JSONArray.fromString(spareSAPData);
	        					CommonFunctions.debugMsg("sapTlSparesreplacedList.size1=="+spareReplacedjson);
	        					sapTlSparesreplacedList=(List<SapTlSparesreplaced>)UIUtils.convertJSONArrToList(newSapTlSparesreplaced, spareReplacedjson);
	        				
	        					CommonFunctions.debugMsg("sapTlSparesreplacedList.size=="+sapTlSparesreplacedList.size());
	        					
	        					String updateStatus  = sapInfoService.delteSparesDetail(sapTlSparesreplacedList);
	        					
	        					  String msg;
	        						msg="Data Deleted Successfully";					
	        					JSONObject successData = new JSONObject();
	        					successData.put("msg",msg);
	        					JSONObject returnData = new JSONObject();
	        					returnData.put("successData", successData);
	        					returnData.put("formClear", false);
	        					
	        					out.print(returnData.toString());
	        			
	        			}
	        		}catch(Exception e){
	        			e.printStackTrace();
	        			out.print(e.getMessage());
	        		}finally{
	        			};
	        		}
	        		
	        }
	        */
	        	
	        	
	        	
				deleteSparesinfo(request,response);
				String keyId = request.getParameter("keyId");
				CommonFunctions.debugMsg("detailKeyid "+keyId);
				String deleteRecord = sapInfoService.delteSparesDetail(keyId);
				CommonFunctions.debugMsg("deleteRecord "+deleteRecord);
				PrintWriter out = response.getWriter();
				JSONObject successData = new JSONObject();
				successData.put("msg",deleteRecord);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
			}
	}
				
			

			
	 
	

		
private void deleteSparesinfo(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

/*	private void saveSpareInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			String availStock =request.getParameter("txtavailStock");
			String factory=request.getParameter("txtfactory");
			String spareNo=request.getParameter("txtspareno");
			String storagelocation=request.getParameter("txtstoragelocation");
			String stockValue=request.getParameter("txtstockValue");
			String rate=request.getParameter("txtrate");
			String spareName=request.getParameter("txtspareName");
			//System.out.println("spare date"+ spareSAPData);
			String refDocId = request.getParameter("refDocId");
			String existDocNumber = request.getParameter("existDocNumber");
			
			List<SapTlSparesreplaced> sapTlSparesreplacedList  = null;
			SapTlSparesreplaced newSapTlSparesreplaced = new SapTlSparesreplaced();
			newSapTlSparesreplaced.setSspmSpareno(spareNo);
			newSapTlSparesreplaced.setSspmStoragelocation(storagelocation);
			newSapTlSparesreplaced.set
			SapTlSparesreplaced existSapTlSparesreplaced = new SapTlSparesreplaced();
			BDFormBean bdFormBean = new BDFormBean();
			JSONArray spareReplacedjson = null;
			boolean insert=false;
			newSapTlSparesreplaced.setExistDocNumber(existDocNumber);
			newSapTlSparesreplaced.setRefDocId(refDocId);
			newSapTlSparesreplaced.setSspmCreatedby(user.getUsrm_ccno());
		  if(UIUtils.isValidKeyId(spareSAPData)){
			CommonFunctions.debugMsg("in if spares Replaced");
			spareReplacedjson = JSONArray.fromString(spareSAPData);
			sapTlSparesreplacedList=(List<SapTlSparesreplaced>)UIUtils.convertJSONArrToList(newSapTlSparesreplaced, spareReplacedjson);
			CommonFunctions.debugMsg("sapTlSparesreplacedList.size()s "+sapTlSparesreplacedList.size());
			if(sapTlSparesreplacedList.size()>=0){
			CommonFunctions.debugMsg("insidesapsapres");
			newSapTlSparesreplaced.setSparesReplaced(sapTlSparesreplacedList);
			CommonFunctions.debugMsg(sapTlSparesreplacedList.size()+"  spareSAPData  "+spareSAPData);
			//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
			} 
		}else{
			newSapTlSparesreplaced.setSparesReplaced(null);
		} 
		 insert=true;
		 existSapTlSparesreplaced  = sapInfoService.createSapSpareReplaced(newSapTlSparesreplaced,existSapTlSparesreplaced,bdFormBean );
	 
		    String msg;
			msg="Data Saved Successfully";
		/*else
			msg="Data Updated Successfully";*//*
		JSONObject successData = new JSONObject();
		successData.put("msg",msg);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);
		returnData.put("formClear", false);
		//returnData.put("Docnumber",existSapTlSparesreplaced.getExistDocNumber());
		out.print(returnData.toString());

}catch(Exception e){
	e.printStackTrace();
	out.print(e.getMessage());
 }
}
	      */  
/*	
	private void updateSpareInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	SapTlSparesreplaced sapTlSparesreplaced = new SapTlSparesreplaced();

    	SapTlSparesreplaced newSapTlSparesreplaced = new SapTlSparesreplaced();

		try{
			String spareSAPData =request.getParameter("spareSAPData");
			JSONArray spareReplacedjson = null;
			List<SapTlSparesreplaced> sapTlSparesreplacedList  = null;
			CommonFunctions.debugMsg("sapTlSparesreplacedList.size=="+sapTlSparesreplacedList.size());

			if(UIUtils.isValidKeyId(spareSAPData)){
			
					spareReplacedjson = JSONArray.fromString(spareSAPData);
					sapTlSparesreplacedList=(List<SapTlSparesreplaced>)UIUtils.convertJSONArrToList(newSapTlSparesreplaced, spareReplacedjson);
				
					CommonFunctions.debugMsg("sapTlSparesreplacedList.size=="+sapTlSparesreplacedList.size());
					
					String updateStatus  = sapInfoService.updateSparesQty(sapTlSparesreplacedList);
					
					  String msg;
						msg="Data updated Successfully";					
					JSONObject successData = new JSONObject();
					successData.put("msg",msg);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					returnData.put("formClear", false);
					
					out.print(returnData.toString());
			
			}
		}catch(Exception e){
			e.printStackTrace();
			out.print(e.getMessage());
		}
		
	} */


/*///////////////////
  private void updateSpareInfoR(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	SapTlSparesreplaced sapTlSparesreplaced = new SapTlSparesreplaced();

    	SapTlSparesreplaced newSapTlSparesreplaced = new SapTlSparesreplaced();

		try{
			String spareSAPData =request.getParameter("spareSAPData");
			JSONArray spareReplacedjson = null;
			List<SapTlSparesreplaced> sapTlSparesreplacedList  = null;


			if(UIUtils.isValidKeyId(spareSAPData)){
			
					spareReplacedjson = JSONArray.fromString(spareSAPData);
					sapTlSparesreplacedList=(List<SapTlSparesreplaced>)UIUtils.convertJSONArrToList(newSapTlSparesreplaced, spareReplacedjson);
				
					CommonFunctions.debugMsg("sapTlSparesreplacedList.size=="+sapTlSparesreplacedList.size());
					
					String updateStatus  = sapInfoService.updateSparesQty(sapTlSparesreplacedList);
					
					  String msg;
						msg="Data updated Successfully";					
					JSONObject successData = new JSONObject();
					successData.put("msg",msg);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					returnData.put("formClear", false);
					
					out.print(returnData.toString());
			
			

			}
		}catch(Exception e){
			e.printStackTrace();
			out.print(e.getMessage());
		}
		
	}
 */




@SuppressWarnings("unchecked")
private void updateSpareInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
	
	BAL_SapTlSparesreplaced newSapTlSparesreplaced = new BAL_SapTlSparesreplaced();

	try{
		String spareSAPData =request.getParameter("spareSAPData");
		JSONArray spareReplacedjson = null;
		List<BAL_SapTlSparesreplaced> sapTlSparesreplacedList  = null;
		CommonFunctions.debugMsg("test =="+spareSAPData);

		if(BAL_UIUtils.isValidKeyId(spareSAPData)){
		
				spareReplacedjson = JSONArray.fromString(spareSAPData);
				sapTlSparesreplacedList=(List<BAL_SapTlSparesreplaced>)BAL_UIUtils.convertJSONArrToList(newSapTlSparesreplaced, spareReplacedjson);
			
				CommonFunctions.debugMsg("sapTlSparesreplacedList.size=="+newSapTlSparesreplaced.getSspmKeyId());
				
				String updateStatus  = sapInfoService.updateSparesQty(sapTlSparesreplacedList);
				
				  String msg;
					msg="Data updated Successfully";					
				JSONObject successData = new JSONObject();
				successData.put("msg",msg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				
				out.print(returnData.toString());
		
		

		}
	}catch(Exception e){
		e.printStackTrace();
		out.print(e.getMessage());
	}
	
}

	private void saveSapSpareInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("spare date.......");//sapspareInfo_save.sapinfo

		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
    	BAL_SapTlSparesreplaced sapTlSparesreplaced = new BAL_SapTlSparesreplaced();
		try{
			String spareSAPData =request.getParameter("spareSAPData");
			System.out.println("spare date......."+ spareSAPData);
			String refDocId = request.getParameter("refDocId");
			CommonFunctions.debugMsg(refDocId+"Ref doc id in servlet");
			String existDocNumber = request.getParameter("existDocNumber");
			if(!BAL_UIUtils.isValidKeyId(refDocId)){
				sapTlSparesreplaced.setRefDocId("-");
				}
			else
				sapTlSparesreplaced.setRefDocId(refDocId);
				if(!BAL_UIUtils.isValidKeyId(existDocNumber)){
				sapTlSparesreplaced.setExistDocNumber("-");
			}
			else
				sapTlSparesreplaced.setExistDocNumber(existDocNumber);
			List<BAL_SapTlSparesreplaced> sapTlSparesreplacedList  = null;
			BAL_SapTlSparesreplaced newSapTlSparesreplaced = new BAL_SapTlSparesreplaced();
			BAL_SapTlSparesreplaced existSapTlSparesreplaced = new BAL_SapTlSparesreplaced();
			BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
			JSONArray spareReplacedjson = null;
			boolean insert=false;
			newSapTlSparesreplaced.setExistDocNumber(existDocNumber);
			if(!BAL_UIUtils.isValidKeyId(refDocId)){
				newSapTlSparesreplaced.setRefDocId("-"); 
			}
			else
			newSapTlSparesreplaced.setRefDocId(refDocId);
			newSapTlSparesreplaced.setSspmCreatedby(user.getUsrm_ccno());
		  if(BAL_UIUtils.isValidKeyId(spareSAPData)){
			CommonFunctions.debugMsg("in if spares Replaced");
			spareReplacedjson = JSONArray.fromString(spareSAPData);
			CommonFunctions.debugMsg("test ine servelet"+sapTlSparesreplaced.getSspmDocnumber()+   "     "+sapTlSparesreplacedList);
			sapTlSparesreplacedList=(List<BAL_SapTlSparesreplaced>)BAL_UIUtils.convertJSONArrToList(newSapTlSparesreplaced, spareReplacedjson);
			CommonFunctions.debugMsg("sapTlSparesreplacedList.size()s "+sapTlSparesreplacedList.size()+"  "+sapTlSparesreplacedList);
			if(sapTlSparesreplacedList.size()>=0){
			CommonFunctions.debugMsg("insidesapsapres");
			for(int i=0;i<sapTlSparesreplacedList.size();i++) {
				CommonFunctions.debugMsg("sapTlSparesreplaced.getRefDocId()"+sapTlSparesreplaced.getRefDocId());
				sapTlSparesreplacedList.get(i).setSspmDocnumber(sapTlSparesreplaced.getRefDocId());
				//sapTlSparesreplacedList.get(i).setSspmExistDocNumber(sapTlSparesreplaced.getExistDocNumber()));
			}
			newSapTlSparesreplaced.setSparesReplaced(sapTlSparesreplacedList);
			CommonFunctions.debugMsg(sapTlSparesreplacedList.size()+"  spareSAPData  "+spareSAPData);
			//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
			} 
		}else{
			newSapTlSparesreplaced.setSparesReplaced(null);
		} 
		 insert=true;
		 
		 existSapTlSparesreplaced  = sapInfoService.createSapSpareReplaced(newSapTlSparesreplaced,existSapTlSparesreplaced);
	 
		    String msg;
			msg="Data Saved Successfully";
		/*else
			msg="Data Updated Successfully";*/
		JSONObject successData = new JSONObject();
		successData.put("msg",msg);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);
		returnData.put("formClear", false);
		//returnData.put("Docnumber",existSapTlSparesreplaced.getExistDocNumber());
		out.print(returnData.toString());

}catch(Exception e){
	e.printStackTrace();
	//out.print(e.getMessage());
 }
}
	private void saveSapRelateData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
    	//SapTlMaintenanceorder newSapTlMaintenanceorder = new SapTlMaintenanceorder();
    	BAL_SapTlMaintenanceOrderdtl newSapTlMaintenanceOrderdtl = new BAL_SapTlMaintenanceOrderdtl();
    	BAL_SapTlMaintenanceOrderdtl existSapTlMaintenanceOrderdtl = (BAL_SapTlMaintenanceOrderdtl)httpSession.getAttribute("SapTlMaintenanceOrderdtl");
		
    	if( httpSession != null && user != null)
    	{
    		newSapTlMaintenanceOrderdtl.setModtCreatedby(user.getUsrm_ccno());
    		newSapTlMaintenanceOrderdtl =(BAL_SapTlMaintenanceOrderdtl)BAL_UIUtils.setBeanProperties((Object)newSapTlMaintenanceOrderdtl,request);
    		try{
    				CommonFunctions.debugMsg("ceatedBY  "+newSapTlMaintenanceOrderdtl.getModtCreatedby());
    			
	    			boolean insert = true;
	    			/*if(UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getRefdocid))
	    				insert = false;*/
	    			
					 BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
					existSapTlMaintenanceOrderdtl =	sapInfoService.createSapSpareinfo(newSapTlMaintenanceOrderdtl,existSapTlMaintenanceOrderdtl,bdFormBean );
					 	
					/*else
					{
						insert = false;
						existSapTlMaintenanceorder = sapInfoService.updateExtService(newSapTlMaintenanceorder,existSapTlMaintenanceorder,bdFormBean);						
					}*/
					String msg;
					if(insert)
						msg="Data Saved Successfully";
					else
						msg="Data Updated Successfully";
					JSONObject successData = new JSONObject();
					successData.put("msg",msg);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					returnData.put("formClear", false);
					returnData.put("Docnumber",existSapTlMaintenanceOrderdtl.getExistDocNumber());
					out.print(returnData.toString());

    		}catch(Exception e){
    			e.printStackTrace();
    			out.print(e.getMessage());
    		}
	
    	}

	}

	private JSONObject getTableModel(List<String[]> headers) {

		
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(1);
		
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setRowHeight(true);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if (i == 0) {//||(i==1)){
				jqGridColModel.setWidth(50);
			}
			if (i == colHeader.length-1) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(10);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = BAL_UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "90%%");
		tableModel.set("tableWidth", "106%%");
		return tableModel;
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
			// getFilterValues(request);
			
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonFunctions.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}
	
	private JSONObject getSapTableModel(String[] colIndex,String[] colHeader,char type,boolean enableFilter,boolean tableButton  ) {//List<String[]> headers) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableButton(tableButton);
		jqGridTableModel.setEnableFilter(enableFilter);
		if( type == 'M' || type == 'D'){
			jqGridTableModel.setTableHeight(500);
			jqGridTableModel.setTableWidth(500);
		}
		else if( type == 'B' || type == 'N'){
			jqGridTableModel.setTableHeight(500);
			jqGridTableModel.setTableWidth(500);
		}
		for (int i = 0; i < colIndex.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
			jqGridColModel.setName(jqGridColModel.getIndex());
			jqGridColModel.setWidth(150); 
			jqGridColModel.setAlign("left");
			if((jqGridColModel.getIndex()).toUpperCase().equals("SLNO") || (jqGridColModel.getIndex()).toUpperCase().equals("RN")  || (jqGridColModel.getIndex()).toUpperCase().equals("DATAORDER")){
				jqGridColModel.setHidden(true);
			}
			 if(  ( i == 2 || i == 3 ) && type == 'M' ){
				jqGridColModel.setFormatter("grdSapCrmView_getBillItemNotesFormater");
				jqGridColModel.setWidth(70);
				jqGridColModel.setAlign("center");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = BAL_UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "100%%");
		if( type == 'M' || type == 'D' )
			tableModel.set("tableWidth", "106%%");
		return tableModel;
	}
}
