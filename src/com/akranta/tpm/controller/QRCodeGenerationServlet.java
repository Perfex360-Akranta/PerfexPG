package com.akranta.tpm.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.QRCodeGenerationService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.QRCodeGenerationServiceImpl;
import com.akranta.tpm.service.impl.EquipmentFormServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.encoder.QRCode;
//import com.sun.javafx.iio.ImageStorage.ImageType;

/**
 * Servlet implementation class QRCodeGenrationServlet
 */
@WebServlet("/QRCodeGenerationServlet")
public class QRCodeGenerationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	QRCodeGenerationService  qrCodeGenerationService;
	CommonFilterService commonFilterService;
	CommonFilter  commonFilter;
	
	
    public QRCodeGenerationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession(false);
		
		String action = UIUtils.getActionPart(request);
	
		
		try {
			qrCodeGenerationService = (QRCodeGenerationServiceImpl)UIUtils.getServiceObject(request,"QRCodeGenerationServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
		   
			String dispatchUrl = null;
			
			 if (action.equals("QREquipmentGrid_input.qrcgn")) { 
				 System.out.println(" In side the QRcode ");
					
					RequestDispatcher rd = request.getRequestDispatcher("/pages/QRCodeGeneration.jsp"); 
				rd.forward(request, response); 
			 }
			 else if (action.equals("QREquipmentGrid_getCol.qrcgn")){
				  String cellId= request.getParameter("cellId");
					String sectId=request.getParameter("sectId");
					String factId=request.getParameter("factId");
					String mchId=request.getParameter("mchId");
					  httpSession.removeAttribute("QrCodegenCommonFilter");

					CommonFilter commonFilter = populateCommonFilter(request,"QrCodegenCommonFilter",true);	

					commonFilter.setFactoryId(factId);
					commonFilter.setSectionId(sectId);
					commonFilter.setCellId(cellId);
					if(mchId!=null || mchId!=""){
						commonFilter.setMachineId(mchId);
					}
					httpSession.setAttribute("QrCodegenCommonFilter", commonFilter);

					PrintWriter out = response.getWriter();	
					//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.QRCodeGen", "QrMainGrid"));
					
				//	System.out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.QRCodeGen", "QrMainGrid"));
				
				
					List<String[]> summaryList  = qrCodeGenerationService.getQrMasterGrid(commonFilter);	  
					JSONObject qrData = UIUtils.convertToJqGridTableObject(summaryList,request,2,0,commonFilter.getTotalRecordCnt()); 
					
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
		        
		            List<String> formattorList =  new ArrayList<String>();

					formattorList.add("SelectFormater");
					formattorList.add("imgFormater");
					
					List<String> formattorFromList =  new ArrayList<String>();

					formattorFromList.add("1");
					formattorFromList.add("2");
					List<String> formattorToList =  new ArrayList<String>();
					formattorToList.add("1");
					formattorToList.add("2");
					
					gridColModel.setMultiformatter(formattorList);
					gridColModel.setMultiformattorFromCol(formattorFromList);
					gridColModel.setMultiformattorToCol(formattorToList);
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(false);
					
					
					
					gridColModel.setHeaderNum(1);			
					String [] colHeader = summaryList.get(1);			
					String [] colHeaderCond = summaryList.get(0);
					
					//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.put("tableHeight", "47%");
					jsonObject.put("tableWidth", "107%%");
					httpSession.setAttribute("qrGridColModel", jsonObject);
					out.println(jsonObject); 
					
				}
				else if (action.equals("QREquipmentGrid_getData.qrcgn")){
					try {
						////////////////////////////////
						 
						CommonFunctions.debugMsg("In side the summary_getdATA");
						 String cellId= request.getParameter("cellId");
							String sectId=request.getParameter("sectId");
							String factId=request.getParameter("factId");
							String mchId=request.getParameter("mchId");
						PrintWriter out = response.getWriter();
						CommonFunctions.debugMsg(" type in sdie the sumary");
						 UIUtils.displayRequestParamsValue(request);
						 httpSession = request.getSession();
						 CommonFilter commonFilter = populateCommonFilter(request,"QrCodegenCommonFilter",false);
						 commonFilter.setFactoryId(factId);
							commonFilter.setSectionId(sectId);
							commonFilter.setCellId(cellId);
							if(mchId!=null || mchId!=""){
								commonFilter.setMachineId(mchId);
							}
							 httpSession.removeAttribute("QrCodegenCommonFilter");
				  			 httpSession.setAttribute("QrCodegenCommonFilter", commonFilter);
						 JSONObject jsonObject = new JSONObject();
							 List<String []> bdMasterList  = qrCodeGenerationService.getQrMasterGrid(commonFilter);
			        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
			        	 out.println(jsonObject);
						 //commonFilter.setViewClick('N');	  			 	
			  			
					}catch(Exception e)
					{
						CommonFunctions.debugMsg(e.getMessage());
				}
					
						
					
				}
				
	}catch(Exception e){
		e.printStackTrace();
	}
		
		

	
		
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getEquipmentRelated(request, commonFilter);
		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
}
