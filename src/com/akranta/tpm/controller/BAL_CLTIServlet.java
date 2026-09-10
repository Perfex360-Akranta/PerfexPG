package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_CLTIService;
import com.akranta.tpm.service.impl.BAL_CLTIServiceImpl;


public class BAL_CLTIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BAL_CLTIServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = UIUtils.getActionPart(request);
		System.out.println(" action " + action);

		BAL_CLTIService cltiService = (BAL_CLTIServiceImpl) UIUtils.getServiceObject(request, "CLTIServiceImpl");
			System.out.println("alert action url" + action);
		if (action.equals("CLTI_input.clti")) {
			
			String mainForm=request.getParameter("mainForm");
		
			if("true".equals(mainForm)){
				request.setAttribute("mainForm", mainForm);
				
				
			}
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/CLTIReport.jsp");
			rd.forward(request, response);
		} else if (action.equals("CLTI_getCol.clti")) {
			PrintWriter out = response.getWriter();

			JSONObject jsonObject = new JSONObject();

			List<String[]> cltiGrid = null;
			CommonFilter commonFilter = new CommonFilter();

			try {
				cltiGrid = cltiService.getCLTIReport(commonFilter);

			} catch (Exception e) {
				e.printStackTrace();
			}
		
			jsonObject = getTableModelForVisualsopReport(cltiGrid);
			jsonObject.set("tableHeight", "92%%");
			jsonObject.set("tableWidth", "104.2%%");
			out.println(jsonObject);
		} else if (action.equals("CLTI_getData.clti")) {

			// populateCommonFilter(request,"FSAuditReportCommonFilter",false);
			try {
				CommonFilter commonFilter = new CommonFilter();

				FilterValues.getCommonFilters(request, commonFilter);

				List<String[]> sopReportGrid = cltiService
						.getCLTIReport(commonFilter);

				PrintWriter out = response.getWriter();

				JSONObject cltiReportGridmod = UIUtils
						.convertToJqGridTableObject(sopReportGrid, request, 1,
								0);

				out.println(cltiReportGridmod);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}


	private JSONObject getTableModelForVisualsopReport(List<String[]> headers) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();

		String[] colHeader = headers.get(0);

		jqGridTableModel.getRowHeaders().add(colHeader);

		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(30);
		jqGridTableModel.setTableWidth(400);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			
			jqGridColModel.setWidth(90);
			jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);

			if (i == 0) {

				jqGridColModel.setWidth(300);

			}
			if (i >= 5) {

				jqGridColModel.setWidth(18);
		
			}
			if (i >= 10) {

				jqGridColModel.setWidth(26);
		
			}
			if (i == 2 ) {
				jqGridColModel.setWidth(60);

			}
			if( i == 3 ||i == 4){
				jqGridColModel.setWidth(100);
			}
				
			
			System.out.println("Index[" + i + "]" +jqGridColModel.getIndex());
			System.out.println("Name[" + i + "]" +jqGridColModel.getName());

			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		
		return tableModel;
	}

}
