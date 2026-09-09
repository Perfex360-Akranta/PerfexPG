package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.NewCustomerComplaintService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;
/**
 * Servlet implementation class NewCustomerComplaintServlet
 */
@WebServlet("/NewCustomerComplaintServlet")
public class NewCustomerComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewCustomerComplaintService newCustomercomplaintservice;
     List<String[]> CustComList=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCustomerComplaintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
     private void process(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	 
    	 newCustomercomplaintservice=(NewCustomerComplaintService)UIUtils.getServiceObject(request,"NewCustomerComplaintServiceImpl");
         String action=UIUtils.getActionPart(request);
         HttpSession httpSession=request.getSession(false);
         if(action.equals("NewCustomerComplaint_input.cuscom")){
        	 UIUtils.forwardRequest(request, response,"/pages/NewCustomerComplaintRpt.jsp");
         }
         else if(action.equals("NewCustomerComplaint_getCol.cuscom")){
        	   CustComList=newCustomercomplaintservice.custComList();
        	   JSONObject colmodel = getSapTableModel(CustComList.get(0),CustComList.get(0),false,true) ;
               PrintWriter out = response.getWriter();        
               httpSession.removeAttribute("custColModel");
               httpSession.setAttribute("custColModel",colmodel);
               out.println(colmodel);
        	 
         }
         else if(action.equals("NewCustomerComplaint_getData.cuscom")){
        	   CustComList = newCustomercomplaintservice.custComList();
	    	   JSONObject crmMaster = UIUtils.convertToJqGridTableObject(CustComList, request, 1, 0);
	    	   PrintWriter out = response.getWriter();	
	    	   out.println(crmMaster); 
         }
            else if(action.equals("NewCustomerComplaint_getExcel.cuscom")){
   	    	            CommonMessage.debugMsg("New Customer Complaint Excel");
            	        JSONObject colmodel = (JSONObject) httpSession.getAttribute("custColModel");
                 	    List<String[]> CustList = newCustomercomplaintservice.custComList();
         	    	    JSONObject colmodel1= getSapTableModel1(CustList.get(0),CustList.get(0),false,true) ;
         				CommonFilter commonFilter=(CommonFilter)  httpSession.getAttribute("CustomerCommonFilter");
         				String format = ExcelUtils.getFormat(request);
         				Workbook wb;
         				try 
         				{
         					wb  = newCustomercomplaintservice.getCustomerComplaintExportToExcel(colmodel1,format,commonFilter);
         					ExcelUtils.writeToResponse(response, wb,"CRMReport",format);
         				} 
         				catch (Exception e)
         				{
         						e.printStackTrace();
         				}
         	       }
         else if(action.equals("NewCustComp_input.cuscom")){
        	 String ComplaintNo=request.getParameter("complaintNo");
        	 CommonMessage.debugMsg("The Complaint No"+ComplaintNo);
        	 request.setAttribute("ComplaintNo",ComplaintNo);
        	 UIUtils.forwardRequest(request, response,"/pages/NewCustomerComplaintPopupRpt.jsp");
         }
         else if(action.equals("NewCustComp_getCol.cuscom")){
        	   String ComplaintNo=request.getParameter("complaintNo");
        	   CommonMessage.debugMsg("The Complaint NO:::"+ComplaintNo);
        	   CustComList=newCustomercomplaintservice.customerList(ComplaintNo);
        	   JSONObject colmodel = getSapTableModel(CustComList.get(0),CustComList.get(0),false,false);
      		   JSONObject tblJSONObj =(JSONObject) httpSession.getAttribute("custColModel");
               PrintWriter out = response.getWriter();
               List<String[]> coubnts=transposeListArr1(CustComList);
               tblJSONObj.put("transpose",true);
               httpSession.removeAttribute("custColModel");
               httpSession.setAttribute("custColModel",colmodel); 
               out.println(colmodel);
         }
         else if(action.equals("NewCustComp_getData.cuscom")){
        	   String ComplaintNo=request.getParameter("complaintNo");
      	       CommonMessage.debugMsg("The Complaint NO:::"+ComplaintNo);
      	       CustComList=newCustomercomplaintservice.customerList(ComplaintNo);
	    	   JSONObject crmMaster = UIUtils.convertToJqGridTableObject(CustComList, request, 1, 0);
	    	   PrintWriter out = response.getWriter();
	    	   crmMaster.put("transpose",true);
	    	   out.println(crmMaster); 
         }
     }
     
 	
 	private List<String[]> transposeListArr1(List<String[]> dataList)
 	{
 		CommonMessage.debugMsg("dataList"+dataList.size());
 		if( dataList.size() <=0 ) return null;
 		CommonMessage.debugMsg("INSIDE transposeListArr2"+dataList.size());
 		List<String[]> transposeList = new ArrayList<String[]>();		
 		for( int i =0; i<dataList.size(); i++)
 		{	
 			String [] tRow = new String [ dataList.get(0).length];
 			CommonMessage.debugMsg("tRow [ j ] : "+tRow.length);
 			for (int j=0; j<dataList.get(0).length;j++)
 			{
 				tRow [ j ]= dataList.get(i)[j];//.equals("0")?dataList.get(i)[j].replace("0", "-"):dataList.get(i)[j];
 				
 			}
 			transposeList.add(tRow);
 		}
 		return transposeList;
 	}
      
 	private JSONObject getSapTableModel(String[] colIndex,String[] colHeader,boolean enableFilter,boolean tableButton  ) {

 		JqGridTableModel jqGridTableModel = new JqGridTableModel();
 		jqGridTableModel.getRowHeaders().add(colHeader);
 		jqGridTableModel.setRowNumbers(true);
 		jqGridTableModel.setTableButton(tableButton);
 		jqGridTableModel.setEnableFilter(enableFilter);
 		
 		for (int i = 0; i < colIndex.length; i++) {
 			JqGridColModel jqGridColModel = new JqGridColModel();
 			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
 			jqGridColModel.setName(jqGridColModel.getIndex());
 			jqGridColModel.setWidth(150); 
 			jqGridColModel.setAlign("left");
 			if((jqGridColModel.getIndex()).toUpperCase().equals("SLNO") || (jqGridColModel.getIndex()).toUpperCase().equals("RN")  || (jqGridColModel.getIndex()).toUpperCase().equals("DATAORDER")){
 				jqGridColModel.setHidden(true);
 			}
 			 if(  ( i == 1 || i==0 )  ){
 					
 					jqGridColModel.setWidth(70);
 					//jqGridColModel.setAlign("center");
 				}
 			 if(i==3){
 			    jqGridColModel.setAlign("center");	
 		    	jqGridColModel.setFormatter("whywhyBtn");
 				jqGridColModel.setWidth(80);
 			 }
 			 if(i==5){
 				    jqGridColModel.setAlign("center");	
 			    	jqGridColModel.setFormatter("kaizenBtn");
 					jqGridColModel.setWidth(80);
 				 }
 			 if(i==7){
 				 jqGridColModel.setAlign("center");
 				 jqGridColModel.setFormatter("actionPlanBtn");
 				 jqGridColModel.setWidth(80);
 			 }
 			 
 			if(i==10){
				 jqGridColModel.setAlign("center");
				 jqGridColModel.setFormatter("FileMgrBtn");
				 jqGridColModel.setWidth(80);
			 }
 			 
 			 if(  ( i == 5 || i==7)  ){
 					
 					jqGridColModel.setWidth(80);
 					//jqGridColModel.setAlign("center");
 				}
 			 if(i==11){
 		
 			    
 					jqGridColModel.setWidth(300);
 					
 				 }
 			jqGridTableModel.getColModel().add(jqGridColModel);
 			 
 		}
 		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
 		tableModel.set("tableHeight", "100%%");
 	//	if( type == 'M' || type == 'D' )
 			tableModel.set("tableWidth", "106%%");
 		return tableModel;
 	}
 	
	private JSONObject getSapTableModel1(String[] colIndex,String[] colHeader,boolean enableFilter,boolean tableButton  ) {

 		JqGridTableModel jqGridTableModel = new JqGridTableModel();
 		jqGridTableModel.getRowHeaders().add(colHeader);
 		jqGridTableModel.setRowNumbers(true);
 		jqGridTableModel.setTableButton(tableButton);
 		jqGridTableModel.setEnableFilter(enableFilter);
 		
 		for (int i = 0; i < colIndex.length; i++) {
 			JqGridColModel jqGridColModel = new JqGridColModel();
 			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
 			jqGridColModel.setName(jqGridColModel.getIndex());
 			jqGridColModel.setWidth(150); 
 			jqGridColModel.setAlign("left");
 			if((jqGridColModel.getIndex()).toUpperCase().equals("SLNO") || (jqGridColModel.getIndex()).toUpperCase().equals("RN")  || (jqGridColModel.getIndex()).toUpperCase().equals("DATAORDER")){
 				jqGridColModel.setHidden(true);
 			}
 			 if(  ( i == 1 || i==0 )  ){
 					
 					jqGridColModel.setWidth(70);
 					//jqGridColModel.setAlign("center");
 				}
 			 if(i==3){
 			    jqGridColModel.setAlign("center");	
 		    	jqGridColModel.setFormatter("whywhyBtn");
 				jqGridColModel.setWidth(80);
 			 }
 			 if(i==5){
 				    jqGridColModel.setAlign("center");	
 			    	jqGridColModel.setFormatter("kaizenBtn");
 					jqGridColModel.setWidth(80);
 				 }
 			 if(i==7){
 				 jqGridColModel.setAlign("center");
 				 jqGridColModel.setFormatter("actionPlanBtn");
 				 jqGridColModel.setWidth(80);
 			 }
 			 
 			 if(  ( i == 5 || i==7)  ){
 					
 					jqGridColModel.setWidth(80);
 					//jqGridColModel.setAlign("center");
 				}
 			 if(i==11){
 		
 			    
 					jqGridColModel.setWidth(300);
 					
 				 }
 			jqGridTableModel.getColModel().add(jqGridColModel);
 			 
 		}
 		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
 		tableModel.set("tableHeight", "100%%");
 	//	if( type == 'M' || type == 'D' )
 			tableModel.set("tableWidth", "106%%");
 		return tableModel;
 	}
 	
 	
	/*private JSONObject getSapTableModel1(String[] colIndex,String[] colHeader,boolean enableFilter,boolean tableButton  ) {

 		JqGridTableModel jqGridTableModel = new JqGridTableModel();
 		jqGridTableModel.getRowHeaders().add(colHeader);
 		jqGridTableModel.setRowNumbers(true);
 		jqGridTableModel.setTableButton(tableButton);
 		jqGridTableModel.setEnableFilter(enableFilter);
 		
 		for (int i = 0; i < colIndex.length; i++) {
 			JqGridColModel jqGridColModel = new JqGridColModel();
 			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
 			jqGridColModel.setName(jqGridColModel.getIndex());
 			jqGridColModel.setWidth(150); 
 			jqGridColModel.setAlign("left");
 			if((jqGridColModel.getIndex()).toUpperCase().equals("SLNO") || (jqGridColModel.getIndex()).toUpperCase().equals("RN")  || (jqGridColModel.getIndex()).toUpperCase().equals("DATAORDER")){
 				jqGridColModel.setHidden(true);
 			}
 			 if(  ( i == 1 || i==0 )  ){
 					
 					jqGridColModel.setWidth(70);
 				}
 			 if(i==3){
 			    jqGridColModel.setAlign("center");	
 				jqGridColModel.setWidth(80);
 			 }
 			 if(i==5){
 				    jqGridColModel.setAlign("center");	
 					jqGridColModel.setWidth(80);
 				 }
 			 if(i==7){
 				 jqGridColModel.setAlign("center");
 				 jqGridColModel.setWidth(80);
 			 }
 			 
 			 if(  ( i == 5 || i==7)  ){
 					
 					jqGridColModel.setWidth(80);
 				}
 			 if(i==11){
 		
 			    
 					jqGridColModel.setWidth(300);
 					
 				 }
 			jqGridTableModel.getColModel().add(jqGridColModel);
 			 
 		}
 		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
 		tableModel.set("tableHeight", "100%%");
 	    tableModel.set("tableWidth", "106%%");
 	    tableModel.put("transpose",true); 
 		return tableModel;
 	}*/
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
			commonFilter= FilterValues.getAbnRelatedFilters(request, commonFilter);
			// getFilterValues(request);
			
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}
}
