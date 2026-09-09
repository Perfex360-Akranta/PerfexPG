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

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.KaizenServices;
import com.akranta.tpm.service.impl.KaizenServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/**
 * Servlet implementation class KaizenApproval
 */

public class KaizenApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private KaizenServices  kaizenServices ;
    public KaizenApprovalServlet() {
        super();
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
    
   
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("Action in KaizenApprovalServlet........"+action);
		try {
			kaizenServices = (KaizenServiceImpl)UIUtils.getServiceObject(request,"KaizenServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		if(action.equals("KaizenApproval_input.kaiapp")) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenApprovalGrid.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
		}else if (action.equals("KaizenApproval_getCol.kaiapp")) {
			List<String[]> kznApproval = null;
			PrintWriter out = response.getWriter();
			
	        
			try {
				CommonFilter commonFilter = new CommonFilter();
				kznApproval = kaizenServices.getAllKaizenApproval(commonFilter);
				CommonMessage.debugMsg("before call getTableModelApprovalReport");
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject jsonObject = getTableModelApprovalReport(kznApproval);
			out.println(jsonObject);
			
			
		} else if (action.equals("KaizenApproval_getData.kaiapp")) {
			CommonMessage.debugMsg("get data method");
	try {

				CommonFilter commonFilter = new CommonFilter();
				List<String[]> kznApprovalData = kaizenServices.getAllKaizenApproval(commonFilter);
				
				CommonMessage.debugMsg("Data size" + kznApprovalData.size());
				PrintWriter out = response.getWriter();
				JSONObject kznApproval= UIUtils.convertToJqGridTableObject(kznApprovalData, request, 1, 0);
				out.println(kznApproval);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}	
		else if (action.equals("KaizenApprovalmodify_input.kaiapp")) {
			String kaizenApproval="true";
			request.setAttribute("kaizenApproval", kaizenApproval);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ImprovementProject.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
		}
	}
    private JSONObject getTableModelApprovalReport(List<String[]> headers) {
    	CommonMessage.debugMsg("inside getTableModelApprovalReport::::"+headers.size());
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(500);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setAlign("left");
			jqGridColModel.setWidth(125);
			if(i==1){
				jqGridColModel.setWidth(600);
			}
			
			if(i==0 || i==4){
				CommonMessage.debugMsg("inside ["+i+"]"+colHeader[i]);
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(100);
				
			}
			;
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "85%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}
    
   
	
}


