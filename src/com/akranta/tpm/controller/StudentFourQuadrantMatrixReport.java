package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.OplTlMstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.OplTlMstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class StudentFourQuadrantMatrixReport extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	OplTlMstService oplService;
	//CommonFilterService commonFilterService;
	
	public StudentFourQuadrantMatrixReport() {
        super();
      
	}

	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
	    	try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		/**
		 * @throws Exception 
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
	    	try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	    private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
	    	
	    	
	    	String uri = request.getRequestURI();
	    	
			String action = UIUtils.getActionPart(request);
			HttpSession httpSession = request.getSession(false);
			
			try {
				
				oplService = (OplTlMstServiceImpl)UIUtils.getServiceObject(request,"OplTlMstServiceImpl");
				
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}
			
			if (action.equals("FourQuadrantMatrix_input.sfqm")) 
			{
				String emp=request.getParameter("frm");
				request.setAttribute("emp", emp);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/StudentFourQuadrantMatrix.jsp");					
				rd.forward(request, response);
			}
			if (action.equals("FourQuadrantMatrix_getCol.sfqm")) 
			{
						PrintWriter out = response.getWriter();
						CommonFilter commonFilter = new CommonFilter();
						List<String[]> oplModifyList;
						
						String Flid =request.getParameter("flid");
						String Cellid =request.getParameter("cellId");
						String Empid =request.getParameter("Empid");
						
				try {
					commonFilter.setEmpch(Empid);
					if (UIUtils.isValidKeyId(Flid))
						commonFilter.setFlid(Flid);
					
						oplModifyList = oplService.getFourQuadrantmatrix(commonFilter,Flid,Cellid,Empid);
						JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						GridColModel gridColModel = new GridColModel();
						
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(true);
						jqGridTableModel.setTableButton(true);
						gridColModel.setHeaderNum(1);
						
						String [] colHeader = oplModifyList.get(1);		 // 2 to 1 vignesh	
						String [] colHeaderCond = oplModifyList.get(0);   // 2 to 1 vignesh	
						
						List<String[]> headers = new ArrayList<String[]>();
						
						headers.add(colHeader);
						gridColModel.setFormatter("setfourquadrantimage");
						gridColModel.setFormattorFromCol("3"); // changing 4 to 3 vignesh
						gridColModel.setFormattorToCol(""+(colHeader.length));
						
						JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
						jsonObject.put("tableHeight", "72%%");
						jsonObject.put("tableWidth", "110%%");
				      	CommonMessage.debugMsg("jsonObject ="+jsonObject);
				      	out.println(jsonObject);
			      	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

			}
			if (action.equals("FourQuadrantMatrix_getData.sfqm")) 
			{
				
					String Flid =request.getParameter("flid");
					String Cellid =request.getParameter("cellId");
					String Empid =request.getParameter("Empid");
					
					try
					{
						CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",false);
						commonFilter.setEmpch(Empid);
						if (UIUtils.isValidKeyId(Flid))
							commonFilter.setFlid(Flid);
						
						List<String []> getAllFiveSAuditarea  = oplService.getFourQuadrantmatrix(commonFilter,Flid,Cellid,Empid);
		                PrintWriter out = response.getWriter();
		                
		  			 	JSONObject FivesAudit = UIUtils.convertToJqGridTableObject(getAllFiveSAuditarea,request,2,0); 
		  			 	out.println(FivesAudit);
		  			     		 	
				    }catch(Exception e)
					{
						CommonMessage.debugMsg(e.getMessage());
					}
				
			}
			if (action.equals("FourQuadrantMatrixDateWise_input.sfqm")) 
			{
				String flid=request.getParameter("flid");
				request.setAttribute("flid", flid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/StudentFourQuadrantMatrixDateWise.jsp");					
				rd.forward(request, response);
			}
			else if (action.equals("FourQuadrantMatrixDateWise_getCol.sfqm")) 
			{

				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",true);
			//	CommonFilter commonFilter = new CommonFilter();
				List<String[]> oplModifyList;
				
				String flid =request.getParameter("flid");
				String typeval =request.getParameter("typeval");
				CommonMessage.debugMsg(" In getCol Servlet :: "+typeval);
			
		try {
			    
			    if (UIUtils.isValidKeyId(flid))
				    commonFilter.setFlid(flid);
			    
			    if(UIUtils.isValidKeyId(typeval))
					commonFilter.setType(typeval);
			
				oplModifyList = oplService.getFourQuadrantmatrixDataWise(commonFilter,flid);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = oplModifyList.get(1);			
				String [] colHeaderCond = oplModifyList.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				
				headers.add(colHeader);
				gridColModel.setFormatter("setfourquadrantimage");
				gridColModel.setFormattorFromCol("4"); // changing 5 to 4
				gridColModel.setFormattorToCol("4");  // changing 5 to 4
				//gridColModel.setFormattorToCol(""+(colHeader.length));
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "86%%");
				jsonObject.put("tableWidth", "110%%");
				httpSession.removeAttribute("studentmtrxdtewiseColModel");
				httpSession.setAttribute("studentmtrxdtewiseColModel", jsonObject);
		      	CommonMessage.debugMsg("jsonObject ="+jsonObject);
		      	out.println(jsonObject);
	      	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			}
			else if (action.equals("FourQuadrantMatrixDateWise_getData.sfqm")) 
			{
				String flid =request.getParameter("flid");
				String typeval =request.getParameter("typeval");
				CommonMessage.debugMsg(" In getCol Servlet :: "+typeval);
				try
				{
					CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",false);
					if (UIUtils.isValidKeyId(flid))
						commonFilter.setFlid(flid);
					
					if(UIUtils.isValidKeyId(typeval))
						commonFilter.setType(typeval);

					List<String []> getAllFiveSAuditarea  = oplService.getFourQuadrantmatrixDataWise(commonFilter,flid);
	                PrintWriter out = response.getWriter();
	                // vignesh 3 to 4
	  			 	JSONObject FivesAudit = UIUtils.convertToJqGridTableObject(getAllFiveSAuditarea,request,4,0); 
	  			 	out.println(FivesAudit);
	  			     		 	
			    }catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
		
			}
			else if (action.equals("FourQuadrantMatrixDateWise_getExcel.sfqm")) 
			{
				CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",false);
				CommonMessage.debugMsg("excellll");
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("studentmtrxdtewiseColModel");
				colmodel.put("title"," Four Quadrant Matrix Date Wise Report ");
				colmodel.put("rowHeight", 29.25);
				//colmodel.put("rowHeight", 29.25);
	            String format = ExcelUtils.getFormat(request);		
	            String imagepath=UIUtils.getAppPath(request);
				Workbook wb = oplService.getFourQuarExcelDatewise(colmodel,format,commonFilter,imagepath);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "FourQuadrantMatrixDateWiseReport", format);
			}
			
			if (action.equals("FourQuadrantMatrix_getExcel.sfqm")) 
			{
				   //httpSession = request.getSession(false);
		    	   CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",false);
					CommonMessage.debugMsg("excellll");
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
				   JSONObject colmodel = UIUtils.getXlColModel( request, response);
					CommonMessage.debugMsg("excellll888");
					colmodel.put("title","Four Quadrant");
					colmodel.put("rowHeight", 29.25);
		            String format = ExcelUtils.getFormat(request);		
		            String imagepath=UIUtils.getAppPath(request);
					Workbook wb = oplService.getFourQuarExcel(colmodel,format,commonFilter,imagepath);
					CommonMessage.debugMsg("excellll0000"); 
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "Four Quadrant", format);
		   }
			
			
		}
	    private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
			
			  HttpSession httpSession = request.getSession(false);
			  		
			  		
			  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
			  		if( commonFilter != null && ! createNew ){
			  			FilterValues.setPaginationParams(request,commonFilter);
			  		}	
			  		else{
			  			commonFilter =  new CommonFilter();
			  			
			  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			  			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			  			commonFilter.setViewClick('Y');
			  			httpSession.removeAttribute(beanIdentifier);
			  			httpSession.setAttribute(beanIdentifier, commonFilter);
			  		}
			  		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
			  		return commonFilter;
			  	}

}
