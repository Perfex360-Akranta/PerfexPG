package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlSubAssemblymstBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_GenTlSubAssemblymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_GenTlSubAssemblymstService;
import com.akranta.tpm.service.impl.BAL_GenTlSubAssemblymstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class Bal_SubAssemblyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BAL_GenTlSubAssemblymstService subAssemblyService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws jakarta.servlet.ServletException, IOException {
        try { process(req, res); } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws jakarta.servlet.ServletException, IOException {
        try { process(req, res); } catch (Exception e) { e.printStackTrace(); }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession httpSession = request.getSession(false);
        AdmTlUsermst user = UIUtils.getLoginUser(request);

        if (httpSession == null || user == null) return;

        String action = UIUtils.getActionPart(request);
        response.setContentType("text/json");

        try {
            subAssemblyService = (BAL_GenTlSubAssemblymstServiceImpl)
                UIUtils.getServiceObject(request, "BAL_GenTlSubAssemblymstServiceImpl");
            subAssemblyService.BAL_GenTlSubAssemblymstServiceImpl((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
        } catch (ServiceObjectCreationException e) {
            CommonFunctions.debugMsg(e);
        }

        String dispatchUrl = null;

        // ── INPUT (open form) ──────────────────────────────────────────
        if (action.equals("subassembly_input.subasm")) {
            String keyid      = request.getParameter(ReqtParamNameConst.KEYID);
            String userEvent  = request.getParameter(ReqtParamNameConst.USER_EVENT);
            String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);

            response.setContentType("text/html");
            FormModes mode = FormModes.create;

            BAL_GenTlSubAssemblymst genTlSubAssemblymst = null;

            httpSession.removeAttribute("genTlSubAssemblymstServlet");

            if ((UIUtils.isValidKeyId(keyid) && userEvent == null)
                    || (userEvent != null && !userEvent.equals("new"))) {

                String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
                if (formMode == null || formMode.equals(FormModeConsts.modify))
                    mode = FormModes.modify;
                else if (formMode.equals(FormModeConsts.view))
                    mode = FormModes.view;

                genTlSubAssemblymst = subAssemblyService.select(keyid);
                httpSession.setAttribute("genTlSubAssemblymstServlet", genTlSubAssemblymst);
                request.setAttribute("genTlSubAssemblymst", genTlSubAssemblymst);
            }

            BAL_GenTlSubAssemblymstBean genTlSubAssemblymstBean =
                new BAL_GenTlSubAssemblymstBean(mode, lockFields);

            // read assmId from URL (passed by btnSubAssmLink in breakdown form)
            String assemblyId = request.getParameter("assmId");
            System.out.println("assmId from request: " + assemblyId);
            if (UIUtils.isValidKeyId(assemblyId))
                genTlSubAssemblymstBean.setAssemblyId(assemblyId);
            
         // ADD — mirror Assembly's machId pattern exactly
            String machId = request.getParameter("machId");
            System.out.println("machId from request: " + machId);
            if (UIUtils.isValidKeyId(machId))
                genTlSubAssemblymstBean.setMachineId(machId); 

            httpSession.setAttribute("bal_genTlSubAssemblymstBean", genTlSubAssemblymstBean);
            request.setAttribute("genTlSubAssemblymstBean", genTlSubAssemblymstBean);
            request.setAttribute("genTlSubAssemblymstServlet", genTlSubAssemblymst);

            dispatchUrl = "/pages/BAL_SubAssembly.jsp";
        }

        // ── SAVE ───────────────────────────────────────────────────────
        else if (action.equals("subassembly_save.subasm")) {
            BAL_GenTlSubAssemblymstBean genTlSubAssemblymstBean =
                (BAL_GenTlSubAssemblymstBean) httpSession.getAttribute("bal_genTlSubAssemblymstBean");
            saveSubAssm(request, response, genTlSubAssemblymstBean);
        }

        // ── DELETE / INACTIVE ──────────────────────────────────────────
        else if (action.equals("subassembly_delete.subasm")) {
            BAL_GenTlSubAssemblymstBean genTlSubAssemblymstBean =
                (BAL_GenTlSubAssemblymstBean) httpSession.getAttribute("bal_genTlSubAssemblymstBean");
            deleteSubAssm(request, response, genTlSubAssemblymstBean);
        }
        
        else if (action.equals("subassembly_getCol.subasm")) {
            getSubAssemblyColModel(request, response);
        }
        
        else if (action.equals("subassembly_getData.subasm")) {
            getSubAssemblyTblData(request, response);
        }

        // ── RECALL (populate form from existing record) ────────────────
        else if (action.equals("subassembly_recall.subasm")) {
            ServletOutputStream out = response.getOutputStream();
            String sbamKeyid = request.getParameter(ReqtParamNameConst.KEYID);
            
            System.out.println("recall sbamKeyid=[" + sbamKeyid + "]");
            
            System.out.println("ReqtParamNameConst.KEYID=[" + ReqtParamNameConst.KEYID + "]");
            System.out.println("recall sbamKeyid=[" + sbamKeyid + "]");

            BAL_GenTlSubAssemblymstBean genTlSubAssemblymstBean =
                new BAL_GenTlSubAssemblymstBean(FormModes.modify);

            httpSession.removeAttribute("genTlSubAssemblymstBean");
            httpSession.setAttribute("genTlSubAssemblymstBean", genTlSubAssemblymstBean);

            httpSession.removeAttribute("genTlSubAssemblymstServlet");
            BAL_GenTlSubAssemblymst genTlSubAssemblymst = subAssemblyService.select(sbamKeyid);
            
            String machineId = subAssemblyService.getMachineIdByAssembly(sbamKeyid);
            httpSession.setAttribute("machineId", machineId);
            
            //JSONObject sbamData = UIUtils.fromTpmModel(genTlSubAssemblymst);

            httpSession.setAttribute("genTlSubAssemblymstServlet", genTlSubAssemblymst);

            JSONObject sbamData   = UIUtils.fromTpmModel(genTlSubAssemblymst);
            JSONObject returnData = new JSONObject();
            returnData.put("sbamdata", sbamData);
            returnData.put("machineId", machineId);
            out.print(returnData.toString());
        }

        if (dispatchUrl != null)
            UIUtils.forwardRequest(request, response, dispatchUrl);
    }

    // ── SAVE HELPER ────────────────────────────────────────────────────
	/*
	 * private void saveSubAssm(HttpServletRequest request, HttpServletResponse
	 * response, BAL_GenTlSubAssemblymstBean genTlSubAssemblymstBean) throws
	 * IOException { HttpSession httpSession = request.getSession(false);
	 * AdmTlUsermst user = UIUtils.getLoginUser(request); if (httpSession == null ||
	 * user == null) return;
	 * 
	 * if (genTlSubAssemblymstBean == null) genTlSubAssemblymstBean = new
	 * BAL_GenTlSubAssemblymstBean(FormModes.create);
	 * 
	 * BAL_GenTlSubAssemblymst newGenTlSubAssemblymst = new
	 * BAL_GenTlSubAssemblymst();
	 * newGenTlSubAssemblymst.setSbamCreatedby(user.getUsrm_ccno());
	 * newGenTlSubAssemblymst = (BAL_GenTlSubAssemblymst)
	 * UIUtils.setBeanProperties(newGenTlSubAssemblymst, request);
	 * genTlSubAssemblymstBean = (BAL_GenTlSubAssemblymstBean)
	 * UIUtils.setBeanProperties(genTlSubAssemblymstBean, request);
	 * 
	 * String assemblyId = request.getParameter("hdnAssemblyId");
	 * System.out.println("hdnAssemblyId from request: " + assemblyId); if
	 * (UIUtils.isValidKeyId(assemblyId))
	 * newGenTlSubAssemblymst.setSbamAssemblyid(assemblyId);
	 * 
	 * BAL_GenTlSubAssemblymst existGenTlSubAssemblymst = (BAL_GenTlSubAssemblymst)
	 * httpSession.getAttribute("bal_genTlSubAssemblymstServlet");
	 * 
	 * PrintWriter out = response.getWriter(); try { boolean insert =
	 * !UIUtils.isValidKeyId(newGenTlSubAssemblymst.getSbamKeyid());
	 * existGenTlSubAssemblymst = insert ?
	 * subAssemblyService.create(newGenTlSubAssemblymst, existGenTlSubAssemblymst,
	 * genTlSubAssemblymstBean) : subAssemblyService.update(newGenTlSubAssemblymst,
	 * existGenTlSubAssemblymst, genTlSubAssemblymstBean);
	 * 
	 * String msgKey = insert ? "success-save" : "success-update"; JSONObject
	 * successData = new JSONObject(); successData.put("msg",
	 * UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",
	 * msgKey)); successData.put("SbamKeyid",
	 * existGenTlSubAssemblymst.getSbamKeyid());
	 * 
	 * JSONObject returnData = new JSONObject(); returnData.put("successData",
	 * successData);
	 * 
	 * httpSession.removeAttribute("bal_genTlSubAssemblymstServlet");
	 * httpSession.removeAttribute("bal_genTlSubAssemblymstBean");
	 * 
	 * out.print(returnData.toString());
	 * 
	 * } catch (ValidationExceptions e) { JSONObject errMessage =
	 * UIUtils.validationExceptions(e.toString(), "subassemblyCreation");
	 * errMessage.put("formActionMode",
	 * genTlSubAssemblymstBean.getFormActionMode());
	 * out.print(errMessage.toString()); } catch (BusinessApplicationExceptions e) {
	 * out.print(UIUtils.businessValidationExceptions(e.toString(),
	 * "subassemblyCreation").toString()); } catch (Exception e) {
	 * e.printStackTrace(); JSONObject err = new JSONObject();
	 * err.put("tpmException", e.getMessage()); out.print(err.toString()); } }
	 */
    
    private void saveSubAssm(HttpServletRequest request, HttpServletResponse response,
            BAL_GenTlSubAssemblymstBean genTlSubAssemblymstBean) throws IOException {
    			HttpSession httpSession = request.getSession(false);
    			AdmTlUsermst user = UIUtils.getLoginUser(request);
    			if (httpSession == null || user == null) return;

    			if (genTlSubAssemblymstBean == null)
    				genTlSubAssemblymstBean = new BAL_GenTlSubAssemblymstBean(FormModes.create);

    			BAL_GenTlSubAssemblymst newGenTlSubAssemblymst = new BAL_GenTlSubAssemblymst();
    			newGenTlSubAssemblymst.setSbamCreatedby(user.getUsrm_ccno());
    			newGenTlSubAssemblymst = (BAL_GenTlSubAssemblymst) UIUtils.setBeanProperties(newGenTlSubAssemblymst, request);
    			genTlSubAssemblymstBean = (BAL_GenTlSubAssemblymstBean) UIUtils.setBeanProperties(genTlSubAssemblymstBean, request);

    			String assemblyId = request.getParameter("hdnAssemblyId");
    			System.out.println("hdnAssemblyId from request: " + assemblyId);
    			if (UIUtils.isValidKeyId(assemblyId))
    				newGenTlSubAssemblymst.setSbamAssemblyid(assemblyId);

    			// NEW: build the functional allocation link object from the parent assembly id
    			GenTlFunctionallocn genTlFunctionallocn = null;
    			if (UIUtils.isValidKeyId(assemblyId)) {
    				genTlFunctionallocn = new GenTlFunctionallocn();
    				genTlFunctionallocn.setFnlnOriginalid(assemblyId); // parent = assembly's keyid
    			}

    				BAL_GenTlSubAssemblymst existGenTlSubAssemblymst =
    						(BAL_GenTlSubAssemblymst) httpSession.getAttribute("bal_genTlSubAssemblymstServlet");

    				PrintWriter out = response.getWriter();
    				try {
    					boolean insert = !UIUtils.isValidKeyId(newGenTlSubAssemblymst.getSbamKeyid());
    					existGenTlSubAssemblymst = insert
    							? subAssemblyService.create(newGenTlSubAssemblymst, existGenTlSubAssemblymst, genTlSubAssemblymstBean, genTlFunctionallocn)
    									: subAssemblyService.update(newGenTlSubAssemblymst, existGenTlSubAssemblymst, genTlSubAssemblymstBean, genTlFunctionallocn);

    					String msgKey = insert ? "success-save" : "success-update";
    					JSONObject successData = new JSONObject();
    					successData.put("msg",
    							UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgKey));
    					successData.put("SbamKeyid", existGenTlSubAssemblymst.getSbamKeyid());

    					JSONObject returnData = new JSONObject();
    					returnData.put("successData", successData);

    					httpSession.removeAttribute("bal_genTlSubAssemblymstServlet");
    					httpSession.removeAttribute("bal_genTlSubAssemblymstBean");

    					out.print(returnData.toString());

    				} catch (ValidationExceptions e) {
    					JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "subassemblyCreation");
    					errMessage.put("formActionMode", genTlSubAssemblymstBean.getFormActionMode());
    					out.print(errMessage.toString());
    					} catch (BusinessApplicationExceptions e) {
    						out.print(UIUtils.businessValidationExceptions(e.toString(), "subassemblyCreation").toString());
    					} catch (Exception e) {
    						e.printStackTrace();
    							JSONObject err = new JSONObject();
    							err.put("tpmException", e.getMessage());
    							out.print(err.toString());
    					}
    }

    // ── DELETE HELPER ──────────────────────────────────────────────────
    private void deleteSubAssm(HttpServletRequest request, HttpServletResponse response,
                               BAL_GenTlSubAssemblymstBean genTlSubAssemblymstBean) throws IOException {
        HttpSession httpSession     = request.getSession(false);
        ServletOutputStream out     = response.getOutputStream();
        AdmTlUsermst user           = UIUtils.getLoginUser(request);
        if (httpSession == null || user == null) return;

        if (genTlSubAssemblymstBean == null)
            genTlSubAssemblymstBean = new BAL_GenTlSubAssemblymstBean(FormModes.create);

        BAL_GenTlSubAssemblymst existGenTlSubAssemblymst =
            (BAL_GenTlSubAssemblymst) httpSession.getAttribute("genTlSubAssemblymstServlet");

        BAL_GenTlSubAssemblymst newGenTlSubAssemblymst = new BAL_GenTlSubAssemblymst();
        newGenTlSubAssemblymst.setSbamCreatedby(user.getUsrm_ccno());
        newGenTlSubAssemblymst  = (BAL_GenTlSubAssemblymst) UIUtils.setBeanProperties(newGenTlSubAssemblymst, request);
        genTlSubAssemblymstBean = (BAL_GenTlSubAssemblymstBean) UIUtils.setBeanProperties(genTlSubAssemblymstBean, request);

        try {
            String inactMode = request.getParameter("hdnInactive");
            existGenTlSubAssemblymst = "Inactive".equals(inactMode)
                ? subAssemblyService.delete("I", newGenTlSubAssemblymst)
                : subAssemblyService.delete("D", newGenTlSubAssemblymst);

            existGenTlSubAssemblymst.setSbamKeyid("N");

            JSONObject successData = new JSONObject();
            successData.put("msg",
                UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "inactivated"));
            successData.put("formActionMode", genTlSubAssemblymstBean.getFormActionMode());
            successData.put("SbamKeyid", existGenTlSubAssemblymst.getSbamKeyid());

            JSONObject returnData = new JSONObject();
            returnData.put("successData", successData);

            httpSession.removeAttribute("genTlSubAssemblymstServlet");
            httpSession.removeAttribute("genTlSubAssemblymstBean");

            out.print(returnData.toString());

        } catch (ValidationExceptions e) {
            JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "subassemblyCreation");
            errMessage.put("formActionMode", genTlSubAssemblymstBean.getFormActionMode());
            out.print(errMessage.toString());
        } catch (BusinessApplicationExceptions e) {
            JSONObject successData = UIUtils.businessValidationExceptions(e.toString(), "subassemblyCreation");
            successData.put("msg", "Original Id Exists");
            JSONObject returnData = new JSONObject();
            returnData.put("formClear", false);
            returnData.put("successData", successData);
            out.print(returnData.toString());
        } catch (Exception e) {
            JSONObject err = new JSONObject();
            err.put("tpmException",
                UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "err-delete"));
            out.print(err.toString());
        }
    }
    
    private void getSubAssemblyColModel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrintWriter out = response.getWriter();
        HttpSession httpSession = request.getSession(false);

        String assemblyId = request.getParameter("assemblyId");
        try {
            CommonFilter commonFilter = populateCommonFilter(request, "sbamCommonFilter", true);
            commonFilter.setIsGetCol("Y");
            commonFilter.setChkAssm(assemblyId);   // assumes CommonFilter has setAssemblyId()

            List<String[]> sbamList = subAssemblyService.getSubAssemblyGridData(commonFilter);

            JqGridTableModel jqGridTableModel = new JqGridTableModel();
            GridColModel gridColModel = new GridColModel();

            jqGridTableModel.setRowNumbers(true);
            jqGridTableModel.setEnableFilter(true);

            gridColModel.setHeaderNum(1);

            String[] colHeader     = sbamList.get(1);
            String[] colHeaderCond = sbamList.get(0);

            List<String[]> headers = new ArrayList<>();
            headers.add(colHeader);

            JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
            jsonObject.put("tableHeight", "45%%");
            jsonObject.put("tableWidth",  "55%%");

            httpSession.setAttribute("SbamListColModel", jsonObject);
            httpSession.setAttribute("sbamCommonFilter", commonFilter);

            out.println(jsonObject);

        } catch (Exception e) {
            CommonMessage.debugMsg("subassembly_getCol ERROR: " + e.getMessage());
            e.printStackTrace();
            out.println("{}");
        }
    }
    
    private void getSubAssemblyTblData(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrintWriter out = response.getWriter();
        HttpSession httpSession = request.getSession(false);

        try {
            CommonFilter commonFilter = populateCommonFilter(request, "sbamCommonFilter", false);
            commonFilter.setIsGetCol("N");

            List<String[]> sbamList = subAssemblyService.getSubAssemblyGridData(commonFilter);

            CommonMessage.debugMsg("Rows returned = " + sbamList.size());
            CommonMessage.debugMsg("TotalRecordCnt = " + commonFilter.getTotalRecordCnt());

            System.out.println("sbamList size = " + sbamList.size());
            System.out.println("TotalRecordCnt = " + commonFilter.getTotalRecordCnt());

            JSONObject sbamData = UIUtils.convertToJqGridTableObject(
                    sbamList, request, 2, 0, commonFilter.getTotalRecordCnt());

            httpSession.removeAttribute("sbamCommonFilter");
            httpSession.setAttribute("sbamCommonFilter", commonFilter);

            out.println(sbamData);

        } catch (Exception e) {
            CommonMessage.debugMsg("subassembly_getData ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			if(beanIdentifier.equals("whywhyQtyCommonFilter"))
				commonFilter = 	FilterValues.getQuality(request, commonFilter);
			else			
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  } 

		
		return commonFilter;
	}
}