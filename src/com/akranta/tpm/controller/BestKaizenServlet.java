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

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlBestmst;
import com.akranta.tpm.model.KznTlEvaluationdtl;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.service.BestKaizenService;
import com.akranta.tpm.service.impl.BestKaizenServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class BestKaizenServlet
 */

public class BestKaizenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BestKaizenService bestKaizenService;
    public BestKaizenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		bestKaizenService = (BestKaizenServiceImpl)UIUtils.getServiceObject(request, "BestKaizenServiceImpl");
		bestKaizenService.BestKaizenServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		CommonFilter commonFilter = new CommonFilter();
		// String dispatchUrl = null;
		
		if (action.equals("bestkaizenlevel_input.bzlv")) {

			KznTlBestmst kznTlBestmst =new KznTlBestmst();
			String btnnew = request.getParameter("btnnew");
			String keyid = request.getParameter("Keyid");
			String month = request.getParameter("month");
			String level = request.getParameter("level");
			CommonMessage.debugMsg("in servlet  commonFilter Flid "+commonFilter.getFlid()+"   Month  "+ commonFilter.getFromMonth());
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			if(UIUtils.isValidKeyId(keyid) ) {
				kznTlBestmst=bestKaizenService.selectmstData( keyid);
				kznTlBestmst.setKzbmDate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlBestmst.getKzbmDate()));//Added This line
			}
				
			
			kznTlBestmst.setKzbmLevel(request.getParameter("level"));
			//kznTlBestmst.setKzbmEmployeeid(user.getUsrm_ccno());
			
			CommonMessage.debugMsg("btnnewbtnnewbtnnew    " +btnnew);
			request.setAttribute("btnnew", btnnew);
			request.setAttribute("kznTlBestmst", kznTlBestmst);
			//httpSession.setAttribute("kznTlBestmst", kznTlBestmst);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BestKaizenMain.jsp");
			rd.forward(request, response);
		}
		else if (action.equals("bestkaizenlevel_recall.bzlv")) {
			PrintWriter out = response.getWriter();
			String flid = request.getParameter("flid");
			String month = request.getParameter("month");
			String level = request.getParameter("level");
			commonFilter.setFlid(flid);
			commonFilter.setFromMonth(month);
			commonFilter.setKznBankType(level);
			CommonMessage.debugMsg("in servlet  commonFilter Flid "+commonFilter.getFlid()+"   Month  "+ commonFilter.getFromMonth());
				List<String []> bestKznRecal  = bestKaizenService.selectData( commonFilter);
				out.print( JSONArray.fromCollection(bestKznRecal));				
		}else if(action.equals("level_Combo.bzlv")){
			try{
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("property file "+UIUtils.getPropertyValue("com.akranta.tpm.resources.BestKznLevel","level"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BestKznLevel","level"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if (action.equals("bestkaizenJHlevel_input.bzlv") ||  action.equals("bestkaizenDMTlevel_input.bzlv")) {

			RequestDispatcher rd = request.getRequestDispatcher("/pages/BestKaizenGrid.jsp");
			KznTlBestmst kznTlBestmst =new KznTlBestmst();
			if (action.equals("bestkaizenJHlevel_input.bzlv"))
				kznTlBestmst.setKzbmLevel("J");
			else if (action.equals("bestkaizenDMTlevel_input.bzlv"))
				kznTlBestmst.setKzbmLevel("D");
			CommonMessage.debugMsg(kznTlBestmst.getKzbmKeyid()+"    kznTlBestmst.getKzbmLevel()   "+kznTlBestmst.getKzbmLevel());
			httpSession.setAttribute("kznTlBestmst", kznTlBestmst);
			request.setAttribute("kznTlBestmst",kznTlBestmst);
			rd.forward(request, response);
		} else if (action.equals("bestkaizenJHlevel_getCol.bzlv") ||  action.equals("bestkaizenDMTlevel_getCol.bzlv")) {
			PrintWriter out = response.getWriter();
			List<String[]> kaizengrid = null;
			 commonFilter = populateCommonFilter(request,"KaizenEvaluationFilter",true);
			 commonFilter.setFlid(request.getParameter("flid"));
			try {
				commonFilter.setIsGetCol("Y");
				kaizengrid = bestKaizenService.getBestKzngrdData(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			gridColModel.setHeaderNum(1);
			
			//String [] colHeader1 = kaizengrid.get(2);
			//String [] colHeaderCond = kaizengrid.get(1);
			
			String [] colHeader1 = kaizengrid.get(1);
			String [] colHeaderCond = kaizengrid.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "84%%");
			jsonObject.put("tableWidth", "106%%");
			out.println(jsonObject);
		} else if (action.equals("bestkaizenJHlevel_getData.bzlv") ||  action.equals("bestkaizenDMTlevel_getData.bzlv")) {
			CommonMessage.debugMsg("get data method");
			PrintWriter out = response.getWriter();
			String flid = request.getParameter("flid");
			commonFilter = populateCommonFilter(request,"AchievementFilter",true);
			try {
				if (action.equals("bestkaizenJHlevel_getData.bzlv"))
					commonFilter.setKznBankType("J");
				else if (action.equals("bestkaizenDMTlevel_getData.bzlv"))
					commonFilter.setKznBankType("D");
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
					commonFilter.setIsGetCol("N");
				List<String[]> achievement = bestKaizenService.getBestKzngrdData(commonFilter);
				JSONObject Achievements = UIUtils.convertToJqGridTableObject(achievement, request, 2, 0,commonFilter.getTotalRecordCnt()+3);
				out.println(Achievements);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (action.equals("bestkaizenlevel_getCol.bzlv")) {
			    PrintWriter out = response.getWriter();
			     httpSession=request.getSession(false);
			    List<String[]> kaizengrid = null;
			   commonFilter = populateCommonFilter(request,"kaizenCommonFilterVal",true);
			    
			    String flid = request.getParameter("flid");
				String month = request.getParameter("month");
				
				if(!UIUtils.isValidKeyId(month))
					commonFilter.setFromMonth(Constants.passNullMonth);
				String level = request.getParameter("level");
				//CommonMessage.debugMsg(flid+" flid servlet   "+month+"  in servltt"+level);
			try {
				commonFilter.setFlid(flid);
				commonFilter.setFromMonth(month);
				CommonMessage.debugMsg("month "+month);
				commonFilter.setKznBankType(level);
				kaizengrid =bestKaizenService.getBestKaizen(commonFilter);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setSortable(true);
			jqGridTableModel.setPaginate(true);
			jqGridTableModel.setEnableFilter(true);

			gridColModel.setHeaderNum(1);
			
			//gridColModel.setFormatter("btnKznformatter");
			//gridColModel.setFormattorFromCol("14");
			//gridColModel.setFormattorToCol("14");
			

			//gridColModel.setFormatter("formatterChkKaizen");
			//gridColModel.setFormattorFromCol("2");
			//gridColModel.setFormattorToCol("2");
			
			String[] formatterval  = {"btnformatter#15"};
			jqGridTableModel.setFormatterIndex(formatterval);
			
			
			String [] colHeader1 = kaizengrid.get(1);
			String [] colHeaderCond = kaizengrid.get(0);
			
			//jqGridTableModel.setFormatterIndex(formatterval);
			List<String> formattorFromList =  new ArrayList<String>();
			List<String> formattorToList =  new ArrayList<String>();
			List<String> formattorList =  new ArrayList<String>();
			
			formattorList.add("formatterChkKaizen");
			formattorList.add("btnKznformatter");
			
			formattorFromList.add("2");
			formattorFromList.add("15");
			/*formattorFromList.add("7");
			formattorFromList.add("8");
			formattorFromList.add("10");*/
			//formattorFromList.add(String.valueOf(colHeader1.length-1));
			
			formattorToList.add(String.valueOf("2"));
			formattorToList.add(String.valueOf("15"));
			/*formattorToList.add(String.valueOf("7"));
			formattorToList.add(String.valueOf("8"));
			formattorToList.add(String.valueOf(colHeader1.length-3));*/
//			/formattorToList.add(String.valueOf(colHeader1.length-1));
			
			gridColModel.setMultiformatter(formattorList);
			gridColModel.setMultiformattorFromCol(formattorFromList);
			gridColModel.setMultiformattorToCol(formattorToList);
			
			CommonMessage.debugMsg(gridColModel.getFormatter() +"  formatter  ");
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "55%%");
			jsonObject.put("tableWidth", "100%%");
			
			httpSession.removeAttribute("bestkaizenColModel");
			//httpSession.setAttribute("KaizenSummaryColModel", colModel);
			httpSession.setAttribute("bestkaizenColModel", jsonObject);

			httpSession.removeAttribute("kaizenCommonFilterVal");
			httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
			out.println(jsonObject);
			
		} else if (action.equals("bestkaizenlevel_getData.bzlv")) {
			CommonMessage.debugMsg("get data method");
			HttpSession httpSession1=request.getSession(false);
			PrintWriter out = response.getWriter();
			String flid = request.getParameter("flid");
			String month = request.getParameter("month");
			String level = request.getParameter("level");
			 commonFilter = populateCommonFilter(request,"kaizenCommonFilterVal",true);

			try {
				commonFilter.setFlid(flid);
				commonFilter.setFromMonth(month);
				commonFilter.setKznBankType(level);
				List<String[]> achievement = bestKaizenService.getBestKaizen(commonFilter);
				
				JSONObject Achievements = UIUtils.convertToJqGridTableObject(achievement, request, 2, 0,commonFilter.getTotalRecordCnt());
				//JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 3, 0,commonFilter.getTotalRecordCnt() );
				
				out.println(Achievements);
				commonFilter.setViewClick('Y');
				httpSession1.removeAttribute("kaizenCommonFilterVal");
				httpSession1.setAttribute("kaizenCommonFilterVal", commonFilter);
				
				
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
        else if(action.equals("bestkaizenlevel_getExcel.bzlv")){
			
			httpSession = request.getSession(false);
			commonFilter = populateCommonFilter(request,"KaizenEvaluationFilter",true);
			
			String flid = request.getParameter("flid");
			String month = request.getParameter("month");
			String level = request.getParameter("level");
			
			commonFilter.setFlid(flid);
			commonFilter.setFromMonth(month);
			commonFilter.setKznBankType(level);
			
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("bestkaizenColModel");
			//JSONObject colmodel = UIUtils.getXlColModel(request, response);
			colmodel.put("title","Best Kaizen Jh Level Report");
            String format = ExcelUtils.getFormat(request);
            //String emppillar=request.getParameter("Emppillar");
            //CommonMessage.debugMsg(" Inside Servlet emppillar "+emppillar);  ,emppillar
            
			Workbook wb = bestKaizenService.getBestKaizenJhExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "BestKaizenExcelReport", format);
			
			
		}
		
		else if (action.equals("bestkaizenJHlevel_getExcel.bzlv") ||  action.equals("bestkaizenDMTlevel_getExcel.bzlv"))
		{
		httpSession = request.getSession(false);
		commonFilter = populateCommonFilter(request,"AchievementFilter",false);
		try {
			if (action.equals("bestkaizenJHlevel_getExcel.bzlv"))
				commonFilter.setKznBankType("J");
			else if (action.equals("bestkaizenDMTlevel_getExcel.bzlv"))
				commonFilter.setKznBankType("D");
		JSONObject colmodel = UIUtils.getXlColModel(request, response);
		colmodel.put("title","BestKaizen  Report");
		String ExcelName="BestKaizen.xlsx";
        String format = ExcelUtils.getFormat(request);	
        CommonMessage.debugMsg("common");
		Workbook wb = bestKaizenService.getbestkaizenExcel(colmodel,format,commonFilter);
		ExcelUtils.writeToResponse(response,wb,ExcelName,format);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 


}

		else if(action.equals("functionalLoc.bzlv"))
		{
			CommonMessage.debugMsg("inside    "+action);
			String level = request.getParameter("level");
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setFactory("cmbKzbmFactory");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbKzbmSection");
			functLocFieldNameBean.setCell("cmbKzbmCell");
			functLocFieldNameBean.setMachine("cmbKzbmMachine");
			functLocFieldNameBean.setFunctionalLocId("cmbKzbmFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			/*functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);*/
				functLocFieldNameBean.setMachDisable(true);
			if(UIUtils.isValidKeyId(level)){
				if("J".equals(level)){
					CommonMessage.debugMsg(level+"::::JJJJ::level:::::::"+level);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(true);
				}else if("D".equals(level)){
					CommonMessage.debugMsg(level+":::DDDD:::level:::::::"+level);
					functLocFieldNameBean.setSectMandatory(true);
					functLocFieldNameBean.setCellMandatory(false);
					functLocFieldNameBean.setCellDisable(true);
				}
			}
			CommonMessage.debugMsg(functLocFieldNameBean.isSectMandatory()+" SECTION MANDATORY   "+functLocFieldNameBean.isCellMandatory());
			/*String disableFuncLoc = request.getParameter("disableFuncLoc");
			
			if("true".equals(disableFuncLoc)){
				functLocFieldNameBean.setFactDisable(true);
				functLocFieldNameBean.setSbuDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
				functLocFieldNameBean.setFactDisable(true);
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
			}*/
			
			FormModes formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}else if(action.equals("bestkaizenlevel_save.bzlv")){
			saveBestKaizen(request,response);
		}else if(action.equals("bestkaizenlevel_delete.bzlv")){
			deleteBestKaizen(request,response);
		}
	}
	private void deleteBestKaizen(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	KznTlBestmst kznTlBestmst = new KznTlBestmst ();
    	KznTlBestmst existkznTlBestmst = new KznTlBestmst ();
    	kznTlBestmst=(KznTlBestmst)UIUtils.setBeanProperties((Object)kznTlBestmst,request);
    	existkznTlBestmst = bestKaizenService.delete(kznTlBestmst);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",false);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
		
	}

	private void saveBestKaizen(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out=response.getWriter();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String kaizenDtlString=request.getParameter("kaizenDetailData");
    	String kznDltDtlString=request.getParameter("kaizenDltDtl");
    	CommonMessage.debugMsg(kznDltDtlString+"  kaizenMstdata    "+kaizenDtlString);
    	String flid="";
    	KznTlBestdtl kznTlBestdtl =new KznTlBestdtl();
    	KznTlBestmst kznTlBestmst =new KznTlBestmst();
    	KznTlBestmst dltkznTlBestmst =new KznTlBestmst();
    	
    	
    	if( httpSession != null && user != null)
    	{
    		if(UIUtils.isValidKeyId(kaizenDtlString)  && kaizenDtlString.length()>10){
    			CommonMessage.debugMsg("in side kaizenDtlString");
	    		JSONArray kznDtlJsonArr = JSONArray.fromString(kaizenDtlString);
	    		List<KznTlBestdtl> kznTlBestdtlList = null;   	
	    		kznTlBestdtlList=(List<KznTlBestdtl>)UIUtils.convertJSONArrToList(kznTlBestdtl, kznDtlJsonArr);
	    		if(kznTlBestdtlList!=null){
					kznTlBestmst.setKznTlBestdtl(kznTlBestdtlList);
				}
    		}
    		if(UIUtils.isValidKeyId(kznDltDtlString) &&  kznDltDtlString.length()>3){
    			CommonMessage.debugMsg("in side kznDltDtlString");
	    		JSONArray dltDtlJsonArr = JSONArray.fromString(kznDltDtlString);
	    		List<KznTlBestdtl> dltBestdtlList = null;   	
	    		dltBestdtlList=(List<KznTlBestdtl>)UIUtils.convertJSONArrToList(kznTlBestdtl, dltDtlJsonArr);
	    		if(dltBestdtlList!=null){
					dltkznTlBestmst.setKznTlBestdtl(dltBestdtlList);
				}
    		}
    		KznTlBestmst existKznTlBestmst =  (KznTlBestmst)httpSession.getAttribute("kznTlBestmst");
	    	try{
		    		String saveMsg = null ;
		    		kznTlBestmst.setKzbmCreatedby(user.getUsrm_ccno());
					
					
		    		kznTlBestmst=(KznTlBestmst)UIUtils.setBeanProperties((Object)kznTlBestmst,request);
		    		CommonMessage.debugMsg(kznTlBestmst.getKzbmKeyid()+"      Analyzed by    "+kznTlBestmst.getKzbmEmployeeid());
					if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmKeyid()))
					{	
						
						existKznTlBestmst = bestKaizenService.create(kznTlBestmst,dltkznTlBestmst);
						 saveMsg = "Data Saved Successfully";
					}	
					else
					{
						existKznTlBestmst = bestKaizenService.update(kznTlBestmst,dltkznTlBestmst);
						 saveMsg = "Data Updated Successfully";
					}
					httpSession.setAttribute("existKznTlBestmst", existKznTlBestmst);				
					JSONObject returnData = new JSONObject();
					
					JSONObject successData = new JSONObject();
					
					successData.put("msg", saveMsg);
					successData.put("flid",kznTlBestmst.getKzbmFlid());
					successData.put("keyid",kznTlBestmst.getKzbmKeyid());
					successData.put("date",kznTlBestmst.getKzbmDate());
					successData.put("month",kznTlBestmst.getKzbmMonth());
					successData.put("level",kznTlBestmst.getKzbmLevel());
					successData.put("level",kznTlBestmst.getKzbmLevel());
					returnData.put("successData",successData);
					//returnData.put("formClear",true);
					CommonMessage.debugMsg("successData   "+successData.toString());
					out.print(returnData.toString());
					
					Boolean clrVal=true;
	    	}catch (ValidationExceptions e) {
				CommonMessage.debugMsg("Inside ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "KznTlBestmstValid");
				e.printStackTrace();
				out.print(errMessage.toString());
	    	}
	    	catch(Exception e)
			{
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
				e.printStackTrace();
			}
    	}
		
	}

	private JSONObject getTableModel(List<String[]> headers) {

		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if (i == 0) {
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(103);
				jqGridColModel.setFormatter("formatterChkKaizen");
			}
			if (i == 1) {
				jqGridColModel.setWidth(660);
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "60%%");
		tableModel.set("tableWidth", "108%%");
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
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}

}
