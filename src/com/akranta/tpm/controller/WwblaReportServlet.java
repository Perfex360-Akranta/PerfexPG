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

import net.sf.json.JSONObject;

import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.FishBoneService;
import com.akranta.tpm.service.WwblaRptService;
import com.akranta.tpm.service.impl.WwblaRptServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.FishBoneServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class WwblaReportServlet
 */
@WebServlet("/WwblaReportServlet")
public class WwblaReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	WwblaRptService wwblaRptService;
	CommonFilterService commonFilterService; 
    public WwblaReportServlet() {
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
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	String dispatchUrl = null;
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action:::" + action);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		
		
		if( user == null)
			return ;
		
		try {
			wwblaRptService = (WwblaRptServiceImpl)UIUtils.getServiceObject(request,"WwblaRptServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			/*if (action.equals("filterXmlFishBoneRpt_input.fishbone") )  {
				
				UIUtils.forwardRequest(request, response,
						"/tiles/xml/FishBone.xml");
				
			}*/ 
		

}catch (Exception e) {
	CommonMessage.debugMsg(e.getMessage());
}
     if(action.equals("WwblaRpt_input.Wwbla"))
{ CommonMessage.debugMsg("WwblaRpt_input.Wwbla");
	request.setAttribute("formtype", "rpt");
	request.setAttribute("disableForRpt", "false"); 				
	RequestDispatcher rd = request.getRequestDispatcher("/pages/WwblaReportGrid.jsp"); 
	rd.forward(request, response);		
	
}
else if(action.equals("WwblaRpt_getCol.Wwbla"))
	{
		List<String[]> fishGrid = null;
		PrintWriter out = response.getWriter();
		//JSONObject jsonObject = new JSONObject();
	
		try {
		    CommonFilter commonFilter = populateCommonFilter(request,"WwblaReportCommonFilter",true);
		    fishGrid = wwblaRptService.getAllWwblaGrid(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			
			jqGridTableModel.setSortable(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setRowNumbers(true);
			gridColModel.setHeaderNum(1);//9
			String [] colHeader = fishGrid.get(1);
			String [] colHeaderHead = fishGrid.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			
			CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
			colmodel.set("tableWidth", "100%%");
			colmodel.set("tableHeight", "80%%");
			httpSession.removeAttribute("WwblaColModel");
			httpSession.setAttribute("WwblaColModel", colmodel);
			out.println(colmodel);

		} catch (Exception e) {
			e.printStackTrace();
		}			
	}else if(action.equals("WwblaRpt_getData.Wwbla"))
	{				
		try {
			CommonMessage.debugMsg("inside WWBLA dat");
			CommonFilter commonFilter = populateCommonFilter(request,"WwblaReportCommonFilter",false);
			List<String[]> WwblaGridList =  wwblaRptService.getAllWwblaGrid(commonFilter);

			PrintWriter out = response.getWriter();
			JSONObject fishGrid=UIUtils.convertToJqGridTableObject(WwblaGridList, request,2, 0,commonFilter.getTotalRecordCnt());
			// UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
			 	out.println(fishGrid);  			 	
			 	commonFilter.setViewClick('N');  			 	
			 	httpSession.removeAttribute("WwblaCommonFilter");
			 	httpSession.setAttribute("WwblaCommonFilter", commonFilter);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	else if((action.equals("Wwbla_getExcel.Wwbla")||(action.equals("WwblaRpt_getExcel.Wwbla"))))
	{
		httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"WwblaReportCommonFilter",false);
		JSONObject colmodel = (JSONObject) httpSession.getAttribute("WwblaColModel");
		colmodel.put("title","Wwbla");
        String format = ExcelUtils.getFormat(request);
		
		Workbook wb = wwblaRptService.getWwblaExcel(colmodel,format,commonFilter);
		ExcelUtils.writeToResponse(response, wb, "WWBLA REPORT", format);
		
	}
	else if (action.equals("WwblarptGrid_input.Wwbla"))
	{
		String keyid = request.getParameter("keyid");
		String title = request.getParameter("title");
		request.setAttribute("title", title);
		request.setAttribute("keyid", keyid);
		RequestDispatcher rd = request.getRequestDispatcher("/pages/WwblaRptGrid.jsp"); 
		rd.forward(request, response);
	}/*else if (action.equals("WwblarptGrid_getCol.wwbla"))
	{
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		String Keyid =request.getParameter("keyid");
		CommonMessage.debugMsg("rptkeyid" + Keyid);
		List<String[]> detailGrid = null;
		//CommonFilter commonFilter = new CommonFilter();
		try {
			detailGrid = wwblaRptService.getWwblaDetail(Keyid);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jsonObject = UIUtils.convertToJqGridTableObject(detailGrid, request, 0, 0);
		jsonObject = getTableModelForFBRpt(detailGrid);
		httpSession.removeAttribute("detailGrid");
		httpSession.setAttribute("detailGrid",jsonObject);
		CommonMessage.debugMsg("GET COL:"+jsonObject.toString());
		out.println(jsonObject);
		
	}else if (action.equals("WwblarptGrid_getData.wwbla"))
	{
		try {
			String keyid =request.getParameter("keyid");				

			CommonMessage.debugMsg("inside WWBLA dat");
			CommonFilter commonFilter = populateCommonFilter(request,"WwblaReportCommonFilter",false);
			List<String[]> StudentReportGrid = wwblaRptService.getWwblaDetail(keyid);

			PrintWriter out = response.getWriter();

			JSONObject deptreportgridDetail = UIUtils.convertToJqGridTableObject(StudentReportGrid, request,1, 0);
              CommonMessage.debugMsg("deptreportgridDetail"+deptreportgridDetail);
			out.println(deptreportgridDetail);
			

			//PrintWriter out = response.getWriter();
			//JSONObject fishGrid=UIUtils.convertToJqGridTableObject(WwblaGridList, request,2, 0,commonFilter.getTotalRecordCnt());
			// UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
			 	//out.println(fishGrid);  			 	
			 	commonFilter.setViewClick('N');  			 	
			 	httpSession.removeAttribute("WwblaCommonFilter");
			 	httpSession.setAttribute("WwblaCommonFilter", commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}*/
	//}
	else if (action.equals("WwblarptGrid_getCol.Wwbla"))
	{
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		String Keyid =request.getParameter("keyid");
		CommonMessage.debugMsg("rptkeyid" + Keyid);
		List<String[]> detailGrid = null;
		//CommonFilter commonFilter = new CommonFilter();
		try {
			detailGrid = wwblaRptService.getWwblaDetail(Keyid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject1 = UIUtils.convertToJqGridTableObject(detailGrid, request, 0, 0);
		jsonObject = getTableModelForFBRpt(detailGrid);
		httpSession.removeAttribute("detailGrid");
		 jsonObject.put("data", jsonObject1);
		httpSession.setAttribute("detailGrid",jsonObject);
		out.println(jsonObject);
		
	}else if (action.equals("WwblarptGrid_getData.Wwbla"))
	{
		try {
			String keyid =request.getParameter("keyid");				

			
			List<String[]> StudentReportGrid = wwblaRptService.getWwblaDetail(keyid);

			PrintWriter out = response.getWriter();

			JSONObject deptreportgridDetail = UIUtils.convertToJqGridTableObject(StudentReportGrid, request,1, 0);
			String colModelIdent = "deptreportgridDetail";	
			
			//out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.WwblaReportModel","colModelIdent"));
			out.println(deptreportgridDetail);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	} 
	else if (action.equals("WwblarptGrid_getExcel.Wwbla"))
	{
		//CommonFilter commonFilter = populateCommonFilter(request,"ServiceLevelAgreementCommonFilter",false);
		//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("SLAMasterReport");
		//JSONObject tblJSONObj = JSONObject.fromString(request.getParameter("colModel"));
		 JSONObject colModel = UIUtils.getXlColModel(request, response);

		String format = ExcelUtils.getFormat(request);	
		
		String keyid = request.getParameter("keyid");
		String title = request.getParameter("title");
		colModel.put("title","WWBLA Report - "+title); 
		Workbook wb = wwblaRptService.getWwblaDetailForExcel(keyid, colModel, format);
		ExcelUtils.writeToResponse(response, wb, "WWBLA", format);
	}
	/*else if( action.equals("WwblaTree_modify.wwbla") )
    {   
		//CommonMessage.debugMsg("jhaTlFiveAuditarea_modify.5saudit");
		
		String WwblaKeyId = request.getParameter("keyId");
		//String refDoctype = request.getParameter("refDoctype");
		//String refDocid = request.getParameter("refDocid");
		CommonMessage.debugMsg(" WwblaKeyId :: "+WwblaKeyId);
		String WwblaGrid=request.getParameter("grid");
		CommonMessage.debugMsg(" WwblaGrid :: "+WwblaGrid);
	//	CommonMessage.debugMsg(" Wwbladoctype:: "+refDoctype);
		if("true".equals(WwblaGrid))
		{
		CommonMessage.debugMsg(" Inside WwblaGrid "+WwblaGrid);
		BdmTlWwblamst newBdmTlWwblamst= new BdmTlWwblamst();
		newBdmTlWwblamst = wwblaRptService.getFillControl(WwblaKeyId);
		
		/*if (!UIUtils.isValidKeyId(newBdmTlWwblamst.getFismRefdocid()))
			newBdmTlWwblamst.setFismRefdocid(refDocid);
		/*if (!UIUtils.isValidKeyId(newGenTlFishbonemst.getFismRefdoctype()))*/
		//newBdmTlWwblamst.setFismRefdoctype(refDoctype);*/
		
		/*request.setAttribute("newBdmTlWwblamst", newBdmTlWwblamst);
		
	//	httpSession.setAttribute("existGenTlFishbonemst ",newGenTlFishbonemst);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/pages/WwblaList.jsp");					
		rd.forward(request, response);		
    }*/
	}
		private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
		{
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
			commonFilter.setViewClick('Y');
			CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
			return commonFilter;
		}
		private JSONObject getTableModelForFBRpt(List<String[]> headers) {

			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			String[] colHeader = headers.get(0);
			String[] colHeader1 = headers.get(0);
			String[] emptyrow = new String[colHeader.length];
			emptyrow[0] = "";
			emptyrow[1] = "";
			
			jqGridTableModel.setRowNumbers(true);
		    jqGridTableModel.setEnableFilter(true);
		    jqGridTableModel.setTableButton(true);
		   
			for (int i = 0; i < colHeader.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")); 
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");			
				jqGridColModel.setEditable(false);			
				
				jqGridTableModel.getColModel().add(jqGridColModel);
				CommonMessage.debugMsg("colHeader1["+i+"]"+colHeader1[i]);
				//CommonMessage.debugMsg("colHeader1[i]"+colHeader1[i]);
				/*if(colHeader1[i]==colHeader1[29])
				{ 
					CommonMessage.debugMsg();
					if(colHeader1[i].equals("LEVEL_30"))
					{
						CommonMessage.debugMsg();
						break;
					}
				}*/
				
			}
			jqGridTableModel.getRowHeaders().add(colHeader1);
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "87%%");
			tableModel.set("tableWidth", "107%%");
			return tableModel;
		}
		
		
}

