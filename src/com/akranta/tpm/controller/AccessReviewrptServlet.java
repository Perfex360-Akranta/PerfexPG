package com.akranta.tpm.controller;

import java.io.IOException;
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
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.AccessReviewrptService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AccessReviewrptServlet
 */
@WebServlet("/AccessReviewrptServlet")
public class AccessReviewrptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private AccessReviewrptService accessReviewrptService;
     List<String[]> AccessReviewGrid = null;
    public AccessReviewrptServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		accessReviewrptService=(AccessReviewrptService)UIUtils.getServiceObject(request,"AccessReviewrptServiceImpl");
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);	
		if (action.equals("AccessRpt_input.arr")) {
		RequestDispatcher rd = request.getRequestDispatcher("/pages/AccessReviewrpt.jsp");
			rd.forward(request, response);
		}
	
		else if (action.equals("AccessRpt_getCol.arr")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"AccessReviewCommonFilter",true);	
			List<String[]> AccessReviewGrid = accessReviewrptService.getAccessReview(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
		
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
	
			String [] colHeader = AccessReviewGrid.get(1);			
			String [] colHeaderCond = AccessReviewGrid.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			JSONObject colModel =new JSONObject();
			colModel= UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			colModel.put("tableWidth", "110%%");
			colModel.put("tableHeight", "80%%");
			
			httpSession.setAttribute("AccessReviewColModel", colModel);
			httpSession.setAttribute("AccessReviewCommonFilter", commonFilter);	
			out.println(colModel );
		} 
		else if (action.equals("AccessRpt_getData.arr")) {
	
			try {
				UIUtils.displayRequestParamsValue(request);	
				httpSession.removeAttribute("AccessReviewCommonFilter");
				CommonFilter commonFilter = populateCommonFilter(request,"AccessReviewCommonFilter",false);
		         String mode =request.getParameter("mode");
		         CommonMessage.debugMsg("The Mode is"+mode);
				commonFilter.setMainGroup(mode);
				List<String[]> AccessReviewGrid  = accessReviewrptService.getAccessReview(commonFilter);
				PrintWriter out = response.getWriter();
				//JSONObject machinegrid = UIUtils.convertToJqGridTableObject(AccessReviewGrid, request, 3, 0);
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(AccessReviewGrid, request, 2, 0,commonFilter.getTotalRecordCnt()-1);
				out.println(machinegrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if (action.equals("AccessRpt_getExcel.arr")) {	
			{
	        	httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"AccessReviewCommonFilter",false);
				JSONObject colmodel = UIUtils.getXlColModel( request, response);
				colmodel.put("title","Access Review Report - " +commonFilter.getFromMonth()+" - "+commonFilter.getToMonth());	
	            String tmpFromRow = commonFilter.getFromRow();
	            String title="Access_Review_Report " ;
				commonFilter.setFromRow(null); 
	            String format = ExcelUtils.getFormat(request);
				Workbook wb = accessReviewrptService.getAccessReviewrptExcel(colmodel,format,commonFilter);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, title, format);	
				}
				
			}
	}	 

		 
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
	{
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			//commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');

		//String loginFlid = CommonFunctions.getLoginFlid(request);
		//if (!UIUtils.isValidKeyId(commonFilter.getFlid()))
		//commonFilter.setFlid(loginFlid);

		return commonFilter;
	}
	
}
			
		


