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

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.KznTlEvaluationmstBean;
import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlEvaluationdtl;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.service.KaizenEvaluationService;
import com.akranta.tpm.service.impl.KaizenEvaluationServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class KaizenNameServlet
 */

public class KaizenEvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	KaizenEvaluationService kaizenNameService;
	public KaizenEvaluationServlet() {
		super();
	}
public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
	try {
		process(request, response);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
	try {
		process(request, response);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		kaizenNameService = (KaizenEvaluationServiceImpl) UIUtils.getServiceObject(
				request, "KaizenEvaluationServiceImpl");
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg(" action " + action);
		CommonFilter commonFilter = new CommonFilter();
		if (action.equals("KaizenEvaluation_input.kazev")) {
			RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenEvaluation.jsp");
			PrintWriter out = response.getWriter();
			List<String[]> kaizenMaster = null;
			commonFilter = populateCommonFilter(request,"KaizenEvaluationFilter",true);
			String flid = request.getParameter("flid");
			String kaizenId = request.getParameter("kaizenId");
			KznTlEvaluationmst kznTlEvaluationmst = new KznTlEvaluationmst();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	CommonMessage.debugMsg(kaizenId+"   Flid   "+request.getParameter("flid"));
			/*if (UIUtils.isValidKeyId(request.getParameter("flid"))){
				commonFilter.setFlid(request.getParameter("flid"));
				kznTlEvaluationmst =kaizenNameService.selectmst(commonFilter);
				CommonMessage.debugMsg(kznTlEvaluationmst.getKevaEmployeeid()+" employee  kznTlEvaluationmst   "+kznTlEvaluationmst.getKevaKeyid());
				request.setAttribute("kznTlEvaluationmst", kznTlEvaluationmst);
	    		httpSession.setAttribute("kznTlEvaluationmst", kznTlEvaluationmst);
			}*/
			request.setAttribute("flid", flid);
			request.setAttribute("kaizenId", kaizenId);
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);

		}else if (action.equals("KaizenEvaluation_getCol.kazev")) {
			CommonMessage.debugMsg("wherther the data is enter or not");
			PrintWriter out = response.getWriter();
			List<String[]> kaizengrid = null;
			 commonFilter = populateCommonFilter(request,"KaizenEvaluationFilter",true);
			 commonFilter.setFlid(request.getParameter("flid"));
			 commonFilter.setRange(request.getParameter("Rank"));
			 commonFilter.setKAIZEN(request.getParameter("kaizenId"));
			 commonFilter.setDefaultDate(request.getParameter("kznMonth"));
			 CommonMessage.debugMsg("Rank servlet   "+commonFilter.getRange());
			try {
				kaizengrid = kaizenNameService.getKaizenData(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			
			
			gridColModel.setHeaderNum(2);
			
			String [] colHeader1 = kaizengrid.get(2);
			//String [] colHeader2 = kaizengrid.get(3);	
			String [] colHeader3 = kaizengrid.get(3);	
			String [] colHeaderCond = kaizengrid.get(1);
			int count=0;
			for(int i=7;i<colHeader3.length;i++){
				if(i==7){
					count++;
				}
				else if(i==8){
					count++;
				}
				else if(i>10 && i<colHeader3.length-2){
					count++;
					i++;
				}
				/*else if(i==colHeader3.length-1){
					count++;
				}*/
			}
			String[] formatVal  = new String[count] ;
			int countDy=0;
			for(int i=7;i<colHeader3.length;i++){
				if(i==7){
					formatVal[countDy]="dte"+(colHeader3[i].replace(" ", ""))+"_"+i+"#"+(i+1);
					countDy++;
				}
				else if(i==8){
					formatVal[countDy]="cmb"+(colHeader3[i].replace(" ", ""))+"_"+i+"#"+(i+1);
					countDy++;
				}
				else if(i>10 && i<colHeader3.length-2){
					formatVal[countDy]="txt"+(colHeader3[i].replace(" ", ""))+"_"+i+"#"+(i+1);
					countDy++;
					i++;
				}/*else if(i==colHeader3.length-1){
					formatVal[countDy]="btn"+(colHeader3[i].replace(" ", ""))+"_"+i+"#"+(i+1);
					countDy++;
				}*/
			}
			
			
			for(int i=0;i<formatVal.length;i++)
				CommonMessage.debugMsg(colHeader3.length+" formatVal value "+formatVal[i]);
			String[] formatterval  = new String[formatVal.length] ;
			formatterval=formatVal;
			jqGridTableModel.setFormatterIndex(formatterval);
			List<String> formattorFromList =  new ArrayList<String>();
			List<String> formattorToList =  new ArrayList<String>();
			List<String> formattorList =  new ArrayList<String>();
			
			formattorList.add("chkLoadFormatter");
			/*formattorList.add("dteEvaDateFormatter");
			formattorList.add("cmbformatter");
			formattorList.add("TxtJHLevel");*/
			formattorList.add("btnformatter");
			
			formattorFromList.add("2");
			/*formattorFromList.add("7");
			formattorFromList.add("8");
			formattorFromList.add("10");*/
			formattorFromList.add(String.valueOf(colHeader1.length-1));
			
			formattorToList.add(String.valueOf("2"));
			/*formattorToList.add(String.valueOf("7"));
			formattorToList.add(String.valueOf("8"));
			formattorToList.add(String.valueOf(colHeader1.length-3));*/
			formattorToList.add(String.valueOf(colHeader1.length-1));
			
			gridColModel.setMultiformatter(formattorList);
			gridColModel.setMultiformattorFromCol(formattorFromList);
			gridColModel.setMultiformattorToCol(formattorToList);
			
			/*gridColModel.setFormatter("chkLoadFormatter");
			gridColModel.setFormattorFromCol("2");
			gridColModel.setFormattorToCol("2");*/
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			//headers.add(colHeader2);
			headers.add(colHeader3);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "51%%");
			jsonObject.put("tableWidth", "106%%");
			out.println(jsonObject);
		} else if (action.equals("KaizenEvaluation_getData.kazev")) {
			CommonMessage.debugMsg("get data method");

			try {
				
				commonFilter = populateCommonFilter(request,"KaizenEvaluationFilter",true);
				commonFilter.setFlid(request.getParameter("flid"));
				commonFilter.setRange(request.getParameter("Rank"));
				commonFilter.setKAIZEN(request.getParameter("kaizenId"));
				commonFilter.setDefaultDate(request.getParameter("kznMonth"));
				List<String[]> kaizenGrid = kaizenNameService.getKaizenData(commonFilter);
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("get data method1");
				for(int i=5;i<kaizenGrid.size();i++){
					String [] totalArry=kaizenGrid.get(i);
					for(int j=totalArry.length-2;j<totalArry.length-1;j++){
						if(Float.parseFloat(totalArry[j])==0){
							CommonMessage.debugMsg("totalArry[j]     "+totalArry[j]);
							totalArry[j]="";
						}
					}
				}
				JSONObject Kaizengrid = UIUtils.convertToJqGridTableObject(kaizenGrid, request, 4, 0, commonFilter.getTotalRecordCnt());
				out.println(Kaizengrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}
		else if(action.equals("KaizenEvaluation_modify.kazev")){
			 try {
				    PrintWriter out = response.getWriter();
					String flid = request.getParameter("flid");
					CommonMessage.debugMsg(" In Kaizen Evaluation flid  :  "+flid);
					List<String []> condReclData  = kaizenNameService.FillJhLeader(flid);
					out.print( JSONArray.fromCollection(condReclData));
	   			} catch (Exception e) {

				}
			
		}
		else if( action.equals("functionalLoc.kazev"))
		{  CommonMessage.debugMsg("functionalLoc.kazev");
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSection("Section");
			functLocFieldNameBean.setCell("Cell");
			functLocFieldNameBean.setMachine("Machine");
			functLocFieldNameBean.setFunctionalLocId("Flid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
			
			String funlocdisable = (String) httpSession.getAttribute("fromWorkorderDisableFunloc");
			if( funlocdisable != null && "true".equals(funlocdisable))
				formModes = FormModes.view;
			if( formModes == FormModes.completion)
				formModes = FormModes.view;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		
		}
		else if(action.equals("KaizenEvaluation_save.kazev")){
			saveKaizen(request,response);
		}
		else if(action.equals("KaizenEvaluation_delete.kazev")){
			
			DeleteKaizen(request,response);
		}

	}
	 private void DeleteKaizen(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	String kaizenMstString=request.getParameter("kaizenMasterData");
	    	String flid="";
	    	KznTlEvaluationmst kznTlEvaluationmst =new KznTlEvaluationmst();
	    	JSONArray kznMstJsonArr = JSONArray.fromString(kaizenMstString);
    		List<KznTlEvaluationmst> kznTlEvmstList = null;   	
			kznTlEvmstList=(List<KznTlEvaluationmst>)UIUtils.convertJSONArrToList(kznTlEvaluationmst, kznMstJsonArr);
			flid=kznTlEvmstList.get(0).getKevaFlid();
			for(int i=0;i<kznTlEvmstList.size();i++){
				KznTlEvaluationdtl kznTlEvaluationdtl1 =new KznTlEvaluationdtl();
    			String Test= (kznTlEvmstList.get(i).getKevaDetail()).replace("TXTKEDLKEVAKEYID","txtKedlKevaKeyid");
    			Test=Test.replace("TXTKEDLKZNCRETRIAID", "txtKedlKzncretriaid");
    			Test=Test.replace("TXTKEDLKZNCRITERIAVAL", "txtKedlKzncriteriaval");
    			Test=Test.replace("TXTKEDLKZNMKEYID", "txtKedlKznmKeyid");
    			Test=Test.replace("TXTKEDLKEYID", "txtKedlKeyid");
				JSONArray kznDtlJsonArr1= JSONArray.fromString(Test);
				List<KznTlEvaluationdtl> kznTlEvdtlList1 = null;  
    			kznTlEvdtlList1=(List<KznTlEvaluationdtl>)UIUtils.convertJSONArrToList(kznTlEvaluationdtl1, kznDtlJsonArr1);
    			kznTlEvmstList.get(i).setKznTlEvaluationdtl(kznTlEvdtlList1);
			}
			
			List<KznTlEvaluationmst> existKznTlEvaluationmst = (List<KznTlEvaluationmst>)httpSession.getAttribute("newSession");
	    	KznTlEvaluationmst newKznTlEvaluationmst = new KznTlEvaluationmst ();
	    	newKznTlEvaluationmst=(KznTlEvaluationmst)UIUtils.setBeanProperties((Object)newKznTlEvaluationmst,request);
	    	existKznTlEvaluationmst = kaizenNameService.delete(kznTlEvmstList);
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			successData.put("flid",flid);
			
			JSONObject returnData = new JSONObject();
			//returnData.put("formClear",true);
			returnData.put("successData", successData);				
			
			out.print(returnData.toString());
		}

		private void saveKaizen(HttpServletRequest request,HttpServletResponse response) throws IOException
	    {
	    	
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream outt = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String kaizenMstString=request.getParameter("kaizenMasterData");
	    	CommonMessage.debugMsg("kaizenMstdata    "+kaizenMstString);
	    	String flid="";
	    	ServletOutputStream out = response.getOutputStream();
	    	KznTlEvaluationmst kznTlEvaluationmst =new KznTlEvaluationmst();
	    	
	    	
	    	if( httpSession != null && user != null)
	    	{
	    		JSONArray kznMstJsonArr = JSONArray.fromString(kaizenMstString);
	    		List<KznTlEvaluationmst> kznTlEvmstList = null;   	
    			kznTlEvmstList=(List<KznTlEvaluationmst>)UIUtils.convertJSONArrToList(kznTlEvaluationmst, kznMstJsonArr);
    			flid=kznTlEvmstList.get(0).getKevaFlid();
    			for(int i=0;i<kznTlEvmstList.size();i++){
    				KznTlEvaluationdtl kznTlEvaluationdtl1 =new KznTlEvaluationdtl();
	    			String Test= (kznTlEvmstList.get(i).getKevaDetail()).replace("TXTKEDLKEVAKEYID","txtKedlKevaKeyid");
	    			Test=Test.replace("TXTKEDLKZNCRETRIAID", "txtKedlKzncretriaid");
	    			Test=Test.replace("TXTKEDLKZNCRITERIAVAL", "txtKedlKzncriteriaval");
	    			Test=Test.replace("TXTKEDLKZNMKEYID", "txtKedlKznmKeyid");
	    			Test=Test.replace("TXTKEDLKEYID", "txtKedlKeyid");
    				JSONArray kznDtlJsonArr1= JSONArray.fromString(Test);
    				List<KznTlEvaluationdtl> kznTlEvdtlList1 = null;  
	    			kznTlEvdtlList1=(List<KznTlEvaluationdtl>)UIUtils.convertJSONArrToList(kznTlEvaluationdtl1, kznDtlJsonArr1);
	    			kznTlEvmstList.get(i).setKevaCreatedby(user.getUsrm_ccno());
	    			kznTlEvmstList.get(i).setKznTlEvaluationdtl(kznTlEvdtlList1);
    			}
	    			CommonMessage.debugMsg(" Critirial value   "+kznTlEvmstList.get(0).getKznTlEvaluationdtl().get(0).getKedlKzncretriaid());
	    		List<KznTlEvaluationmst> existKznTlEvaluationmst =  (List<KznTlEvaluationmst>)httpSession.getAttribute("kznTlEvaluationmst");
		    	try{
			    		String saveMsg = null ;
		
						
			    		kznTlEvmstList=(List<KznTlEvaluationmst>)UIUtils.setBeanProperties((Object)kznTlEvmstList,request);
			    		
						if( kznTlEvmstList!= null )
						{	
							
							existKznTlEvaluationmst = kaizenNameService.create(kznTlEvmstList);
							 saveMsg = "Data Saved Successfully";
						}	
						else
						{
							//existKznTlEvaluationmst = kaizenNameService.update(kznTlEvmstList);
							 saveMsg = "Data Updated Successfully";
						}
						httpSession.setAttribute("kaizenEvaluationServlet", existKznTlEvaluationmst);				
						JSONObject returnData = new JSONObject();
						
						JSONObject successData = new JSONObject();
						
						successData.put("msg", saveMsg);
						successData.put("flid",flid);
						returnData.put("successData",successData);
						returnData.put("formClear",false);
						out.print(returnData.toString());
						
						Boolean clrVal=true;
		    	}/*catch (ValidationExceptions e) {
					CommonMessage.debugMsg("Inside ValidationExceptions");
					net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "KevaEvaluationValidation");
					e.printStackTrace();
					out.print(errMessage.toString());
		    	}*/
		    	catch(Exception e)
				{
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					outt.print(err.toString());
					e.printStackTrace();
				}
	    	}
	    }
	private JSONObject getTableModel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] row = headers.get(1);
		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(1);
		String[] colHeader2 = headers.get(2);
		String[] tempCol = new String[row.length];
		jqGridTableModel.getRowHeaders().add(tempCol);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(10000);
		jqGridTableModel.setTableWidth(800);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(tempCol[i] = "");
			jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("right");
			jqGridColModel.setEditable(false);
			if(i==0 || i==1)
				jqGridColModel.setHidden(true);
			if ((i == 3)) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if ((i == 2)) {
				jqGridColModel.setWidth(410);
				jqGridColModel.setAlign("left");
			}
			
			if ((i == 6) ||(i==5)||(i == 4)||(i==7)||(i==8)) {
				jqGridColModel.setAlign("left");
			}
			if(i>3){
				jqGridColModel.setWidth(70);
				jqGridColModel.setFormatter("TxtJHLevel");
			}
			if ((i == 10)) {
				jqGridColModel.setAlign("center");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "45%%");
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
