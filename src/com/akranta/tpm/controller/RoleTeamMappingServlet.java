package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.GenTlFnlnrolemapBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFnlnrolemap;
import com.akranta.tpm.model.GenTlTeamtradelink;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.FunctionalLocnServices;
import com.akranta.tpm.service.impl.FunctionalLocnServicesImpl;
import com.akranta.tpm.model.GenTlFnlnrolemap;
import com.akranta.tpm.model.GenTlFnlnroleteam;
import com.akranta.tpm.service.GenTlFnlnrolemapService;
import com.akranta.tpm.service.impl.GenTlFnlnrolemapServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import net.sf.json.JSONArray;

public class RoleTeamMappingServlet extends HttpServlet {

	FunctionalLocnServices functionalLocnServices;
	GenTlFnlnrolemapService genTlFnlnrolemapService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		String dispatchUrl = null;

		CommonMessage.debugMsg("action :" + action);
		// Rolemapping.roleteam
		functionalLocnServices = (FunctionalLocnServicesImpl) UIUtils
				.getServiceObject(request, "FunctionalLocnServicesImpl");
		genTlFnlnrolemapService = (GenTlFnlnrolemapServiceImpl) UIUtils
				.getServiceObject(request, "GenTlFnlnrolemapServiceImpl");
		
		
		if (action.equals("rolemapping_input.roleteam")) {
			// dispatchUrl = "/pages/TeamStructure/RoleMapMainGrid.jsp";
			// dispatchUrl = "/pages/TeamStructure/RoleTeamAll.jsp";//
			
			dispatchUrl = "/pages/TeamStructure/RoleTeamAllGrid.jsp";//
		} else if (action.equals("roleteamall_input.roleteam")) {
			String FlId = request.getParameter("flid");
			String level = request.getParameter("level");
			CommonMessage.debugMsg("flid:" + FlId);
			CommonMessage.debugMsg("level:" + level);
			
			if (CommonFunctions.isValidKeyId(FlId)) {
				String fnlnName = genTlFnlnrolemapService.SelectFnlnName(FlId);
				CommonMessage.debugMsg(fnlnName);
				request.setAttribute("OriginalId", fnlnName);
				String location = genTlFnlnrolemapService.SelectLocation(FlId);
				request.setAttribute("location", location);
			}
			request.setAttribute("level", level);
			CommonMessage.debugMsg("value of level set in servlet "  + level);
			request.setAttribute("FlId", FlId);
			dispatchUrl = "/pages/TeamStructure/RoleTeamAll.jsp";//
		} else if (action.equals("rolemappingentry_input.roleteam")) {
			String FlId = request.getParameter("flid");
			CommonMessage.debugMsg("flid:" + FlId);
			if (CommonFunctions.isValidKeyId(FlId)) {
				String fnlnName = genTlFnlnrolemapService.SelectFnlnName(FlId);
				CommonMessage.debugMsg(fnlnName);
				String location = genTlFnlnrolemapService.SelectLocation(FlId);
				request.setAttribute("OriginalId", fnlnName);
				request.setAttribute("location", location);
			}
			request.setAttribute("FlId", FlId);
			dispatchUrl = "/pages/TeamStructure/RoleTeamMapping.jsp";
		}

		else if (action.equals("roleteam_input.roleteam")) {
			dispatchUrl = "/pages/TeamStructure/RoleTeamMainGrid.jsp";
		}

		else if (action.equals("roleteamentry_input.roleteam")) {
			String FlId = request.getParameter("flid");
			CommonMessage.debugMsg("flid:" + FlId);
			if (CommonFunctions.isValidKeyId(FlId)) {
				String fnlnName = genTlFnlnrolemapService.SelectFnlnName(FlId);
				CommonMessage.debugMsg(fnlnName);
				request.setAttribute("OriginalId", fnlnName);
				String location = genTlFnlnrolemapService.SelectLocation(FlId);
				request.setAttribute("location", location);
			}
			request.setAttribute("FlId", FlId);
			dispatchUrl = "/pages/TeamStructure/RoleTeam.jsp";
		}
		/*
		 * else if( action.equals("searchflid.roleteam") ){ String
		 * FlId=request.getParameter("flid");
		 * if(CommonFunctions.isValidKeyId(FlId)){ String
		 * parentId=genTlFnlnrolemapService.SelectOriginalId(FlId);
		 * CommonMessage.debugMsg(parentId); parentId = "#node_1-FL001-" +
		 * parentId.replaceAll("/", "_"); parentId = "-" +
		 * parentId.replaceAll("/", "_"); CommonMessage.debugMsg(parentId);
		 * parentId = parentId.replaceAll("-", "-#"); parentId =
		 * "#node_1-#node_2" + parentId; CommonMessage.debugMsg(parentId);
		 * String[] searchNode = parentId.split("-"); JSONArray jSONArray =
		 * JSONArray.fromArray(searchNode); out.println(jSONArray); }
		 * request.setAttribute("FlId", FlId); }
		 */
		else if (action.equals("roleteamemp_input.roleteam")) {
			CommonMessage.debugMsg("roleteamemp_input.roleteam");

			String roleid = request.getParameter("roleid");
			String rolename = request.getParameter("rolename");
			CommonMessage.debugMsg("roleid  " + roleid);
			CommonMessage.debugMsg("rolename  " + rolename);
			request.setAttribute("RoleId", roleid);
			request.setAttribute("RoleName", rolename);
			// dispatchUrl = "/pages/TeamStructure/RoleTeamEmpList.jsp";
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/TeamStructure/RoleTeamEmpList.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("roleteamempsingle_input.roleteam")) {
			loadRoleTeamEmpSingle(request, response, httpSession);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/TeamStructure/RoleTeamEmpSingle.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("loadval.roleteam")) {
			loadVal(request, response, httpSession);
		}

		else if (action.equals("rolemapping_getCol.roleteam")) {
			getColRoleMapping(request, response, httpSession);
		}

		else if (action.equals("rolemapping_getData.roleteam")) {
			getDataRoleMapping(request, response, httpSession);
		}
		
		else if (action.equals("trade_combo.roleteam")) {
		    try {
		        ComboFilter comboFilter = UIUtils.fillComboFilter(request);
		        List<ComboBox> tradeComboList = genTlFnlnrolemapService.getTradeComboListforrole(comboFilter);
		        UIUtils.writeComboBox(response, tradeComboList, comboFilter);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		else if (action.equals("role_combo.roleteam")) {
		    String tradeId = request.getParameter("tradeId");
		    StringBuffer cond = new StringBuffer();
		    // commented by priyanka
		    //if (CommonFunctions.isValidKeyId(tradeId)) {
		        //cond.append(" AND ROLE_KEYID IN (SELECT GTRL_ROLEID FROM GEN_TL_TRADE_ROLE_LINK WHERE GTRL_TRADEID='" + tradeId + "') ");
		    //}
		    // end
		    try {
		        ComboFilter comboFilter = UIUtils.fillComboFilter(request);
		        comboFilter.setCondSql(cond.toString());
		        List<ComboBox> roleComboList = genTlFnlnrolemapService.getRoleComboListfortrade(comboFilter);
		        UIUtils.writeComboBox(response, roleComboList, comboFilter);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
else if (action.equals("pillar_combo.roleteam")) {
		    try {
		        ComboFilter comboFilter = UIUtils.fillComboFilter(request);
		        List<ComboBox> pillarComboList = genTlFnlnrolemapService.getPillarComboListforrole(comboFilter);
		        UIUtils.writeComboBox(response, pillarComboList, comboFilter);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}		

else if (action.equals("roleLinkTabs_input.roleteam")) {
		    CommonMessage.debugMsg("roleLinkTabs_input.roleteam");
		    RequestDispatcher rd = request
		            .getRequestDispatcher("/pages/RoleLinkTabs.jsp");
		    rd.forward(request, response);
		}
		
else if (action.equals("tradeRoleLink_getCol.roleteam")) {
		    getColTradeRoleLink(request, response, httpSession);
		}

else if (action.equals("tradeRoleLink_getData.roleteam")) {
		    getDataTradeRoleLink(request, response, httpSession);
		}		
		

else if (action.equals("rolepillarmapping_getCol.roleteam")) {
		    getColPillarRoleLink(request, response, httpSession);
		}

		else if (action.equals("rolepillarmapping_getData.roleteam")) {
		    getDataPillarRoleLink(request, response, httpSession);
		}
		
		//else if (action.equals("tradeRoleLink_save.roleteam")) {
		else if (action.equals("roletrademapping_save.roleteam")) {
		//else if (action.equals("roleLinkTabs_save.roleteam")) {
		    saveTradeRoleLink(request, response, httpSession);
		}
		
		else if (action.equals("roletrademapping_delete.roleteam")) {
		    deleteTradeRoleLink(request, response, httpSession);
		}
		
		
		else if (action.equals("rolepillarmapping_save.roleteam")) {
		//else if (action.equals("roleLinkTabs_save.roleteam")) {	
			savePillarRoleLink(request, response);
		}
		
		else if (action.equals("rolepillarmapping_delete.roleteam")) {
			deletePillarRoleLink(request, response, httpSession);
		}

		else if (action.equals("Rolemappinggrid_getCol.roleteam")) {
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(" form getCol");
			String form = request.getParameter("form");
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg(" form getCol::::: " + form);
			JSONObject jsonObject = new JSONObject();
			List<String[]> roleMapGrid = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setFlid(flid);
				FilterValues.getCommonFilters(request, commonFilter);
				roleMapGrid = genTlFnlnrolemapService
						.getRoleMappingGrid(commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

			JSONObject roleMapGridData = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 0, 0,
					commonFilter.getTotalRecordCnt());
			// CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			gridColModel.setHeaderNum(1);
			gridColModel.setFormatter("formatterChkRoleMap");
			gridColModel.setFormattorFromCol("2");
			gridColModel.setFormattorToCol("2");

			jqGridTableModel.setSortable(false);
			jqGridTableModel.setTableButton(false);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setRowNumbers(true);

			String[] formatterval = { "cmbNoofpersons#8" };
			jqGridTableModel.setFormatterIndex(formatterval);

			String[] colHeaderHead = roleMapGrid.get(0);
			String[] colHeader = roleMapGrid.get(1);

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);

			jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
					jqGridTableModel, gridColModel);
			jsonObject.put("data", roleMapGridData);
			jsonObject.set("tableWidth", "43%%");
			jsonObject.set("tableHeight", "78%%");
			httpSession.removeAttribute("roleMapColModel");
			httpSession.setAttribute("roleMapColModel", jsonObject);
			CommonMessage.debugMsg("jsonObject " + jsonObject);
			out.println(jsonObject);

			/*
			 * String colModel =
			 * UIUtils.getPropertyValue("com.akranta.tpm.resources.RoleTeamMapping"
			 * , "colModelRoleMappingGrid");
			 * httpSession.removeAttribute("colModelRoleMapping");
			 * httpSession.setAttribute("colModelRoleMapping",colModel);
			 * out.println
			 * (UIUtils.getPropertyValue("com.akranta.tpm.resources.RoleTeamMapping"
			 * , "colModelRoleMappingGrid"));
			 */
		}

		else if (action.equals("Rolemappinggrid_getData.roleteam")) {
			String form = request.getParameter("form");
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg(" form getData:::: " + form);

			try {
				CommonFilter commonFilter = new CommonFilter();
				commonFilter.setFlid(flid);
				FilterValues.getCommonFilters(request, commonFilter);
				List<String[]> roleMapGrid = genTlFnlnrolemapService
						.getRoleMappingGrid(commonFilter);
				PrintWriter out2 = response.getWriter();
				JSONObject studentreportgrid = UIUtils
						.convertToJqGridTableObject(roleMapGrid, request, 2, 0);
				out2.println(studentreportgrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("RoleCombo.roleteam")) {
			String flid = request.getParameter("flid");

			ComboFilter roleFilterComboFilter = new ComboFilter();
			CommonMessage.debugMsg("---------------------> " + flid);
			StringBuffer cond = new StringBuffer();

			if (CommonFunctions.isValidKeyId(flid)) {
				// cond.append(" AND  ROLE_KEYID IN (SELECT  FRL_ROLE_KEYID FROM GEN_TL_FNLNROLEMAP ");
				// cond.append(" WHERE FRL_FNLN_KEYID = '" + flid + "' ) ");
				cond.append(" AND  ROLE_KEYID IN (SELECT  FRL_ROLE_KEYID FROM GEN_TL_FNLNROLEMAP ");
				cond.append("		 WHERE FRL_LEVEL IN ( ");
				cond.append("	  SELECT DECODE(FNLN_ELEMENTTYPE,'CMP','1','LCN','2','F','3','SBU','4','PBU','5','L','6','C','7','M','8') levelv ");
				cond.append(" FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID='"
						+ flid + "')) ");
			}
			try {
				roleFilterComboFilter = UIUtils.fillComboFilter(request);
				roleFilterComboFilter.setCondSql(cond.toString());
				List<ComboBox> rolefilcomboList = genTlFnlnrolemapService
						.getRoleComboList(roleFilterComboFilter);
				UIUtils.writeComboBox(response, rolefilcomboList,
						roleFilterComboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("roleteam_getCol.roleteam")) {
			getColRoleTeamMainGrid(request, response, httpSession);
		}

		else if (action.equals("roleteam_getData.roleteam")) {
			getDataRoleTeamMainGrid(request, response);
		}

		else if (action.equals("Roleteamgrid_getCol.roleteam")) {
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(" form getCol");
			String form = request.getParameter("form");
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg(" form getCol::::: " + form);
			JSONObject jsonObject = new JSONObject();
			List<String[]> roleMapGrid = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setFlid(flid);
				FilterValues.getCommonFilters(request, commonFilter);
				roleMapGrid = genTlFnlnrolemapService
						.getRoleTeamGrid(commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

			JSONObject roleMapGridData = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 0, 0,
					commonFilter.getTotalRecordCnt());
			// CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			gridColModel.setHeaderNum(1);
			gridColModel.setFormatter("FormattarDelete");
			gridColModel.setFormattorFromCol("8");
			gridColModel.setFormattorToCol("8");
			jqGridTableModel.setSortable(false);
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("role_name");
			jqGridTableModel.setTableButton(false);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setRowNumbers(true);

			String[] colHeaderHead = roleMapGrid.get(0);
			String[] colHeader = roleMapGrid.get(1);

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);

			jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
					jqGridTableModel, gridColModel);
			jsonObject.put("data", roleMapGridData);
			jsonObject.set("tableWidth", "32%%");
			jsonObject.set("tableHeight", "60%%");
			httpSession.removeAttribute("roleMapColModel");
			httpSession.setAttribute("roleMapColModel", jsonObject);
			CommonMessage.debugMsg("jsonObject " + jsonObject);
			out.println(jsonObject);
		}

		else if (action.equals("Roleteamgrid_getData.roleteam")) {
			String form = request.getParameter("form");
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg(" form getData:::: " + form);

			try {
				CommonFilter commonFilter = new CommonFilter();
				commonFilter.setFlid(flid);
				FilterValues.getCommonFilters(request, commonFilter);
				List<String[]> roleMapGrid = genTlFnlnrolemapService
						.getRoleTeamGrid(commonFilter);
				PrintWriter out2 = response.getWriter();
				JSONObject studentreportgrid = UIUtils
						.convertToJqGridTableObject(roleMapGrid, request, 2, 0);
				out2.println(studentreportgrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("noOfEmpCombo.roleteam")) {
			// CommonMessage.debugMsg("inside jhAuditLevelCombo.jhAuditItc");
			PrintWriter outl = response.getWriter();
			outl.println(UIUtils
					.getPropertyValue(
							"com.akranta.tpm.resources.RoleTeamMapping",
							"comboNoOfEmp"));
			CommonMessage.debugMsg("SELECT   :"
					+ UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.RoleTeamMapping",
							"comboNoOfEmp"));
		}

		else if (action.equals("rolemappingentry_save.roleteam")) {
			try {
				CommonMessage.debugMsg("saveRoleMapping");
				saveRoleMapping(request, response, null);
			} catch (Exception e) {

			}
		}

		else if (action.equals("rolemappingentry_delete.roleteam")) {
			try {
				CommonMessage.debugMsg("deleteRoleMapping");
				deleteRoleMapping(request, response, null);
			} catch (Exception e) {

			}
		} else if (action.equals("getrolelevel.roleteam")) {
			try {

				getlevelrole(request, response);
			} catch (Exception e) {

			}
		}
		else if (action.equals("getlocation.roleteam"))
		{
		  try {
			    PrintWriter out = response.getWriter();
				String flid = request.getParameter("flid");
				CommonMessage.debugMsg("keyid   keyid  :  "+flid);
				List<String []> locationData  = genTlFnlnrolemapService.getlocation(flid);
				out.print( JSONArray.fromCollection(locationData));
   			} catch (Exception e) {

			}
		}

		else if (action.equals("roleteamentry_load.roleteam")) {
			PrintWriter out = response.getWriter();
			String form = request.getParameter("form");
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg(" form roleteamentry_load.roleteam:::: "
					+ form);

			try {
				CommonFilter commonFilter = new CommonFilter();
				commonFilter.setFlid(flid);
				FilterValues.getCommonFilters(request, commonFilter);
				List<String[]> roleMapGrid = genTlFnlnrolemapService
						.getRoleTeamCntrlGrid(commonFilter);
				String strControls = genFormControls(roleMapGrid, flid);
				httpSession.removeAttribute("strControls");
				httpSession.setAttribute("strControls", strControls);
				request.setAttribute("mode", FormModes.create);
				response.setContentType("text/html");
				out.print(strControls);
				out.flush();
				out.close();

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("roleteamentry_save.roleteam")) {
			try {
				CommonMessage.debugMsg("roleteamentry_save");
				saveRoleTeamEmp(request, response, null);
			} catch (Exception e) {

			}
		}

		else if (action.equals("roleteamemp_save.roleteam")) {
			try {
				CommonMessage.debugMsg("roleteamemp_save");
				saveRoleTeamEmp(request, response, null);
			} catch (Exception e) {

			}
		}

		else if (action.equals("roleteamempsingle_save.roleteam")) {
			try {
				CommonMessage.debugMsg("roleteamempsingle_save.roleteam");
				saveRoleTeamEmp(request, response, null);
			} catch (Exception e) {

			}
		}

		

		else if (action.equals("roleteamemp_getCol.roleteam")) {
			getColRoleTeamEmp(request, response, httpSession);
		}

		else if (action.equals("roleteamemp_getData.roleteam")) {
			getDataRoleTeamEmp(request, response, httpSession);
		} else if (action.equals("roleTeamEmp_delete.roleteam")) {
			try {
				CommonMessage.debugMsg("delete  roleTeamEmp");
				deleteRoleTeamEmpSingle(request, response, null);
			} catch (Exception e) {

			}
		}

		else if (action.equals("getTradeType.roleteam")) {
			PrintWriter out = response.getWriter();
			String trade = request.getParameter("trade");
			String row = request.getParameter("row");
			String type = genTlFnlnrolemapService.getTradeType(trade);
			CommonMessage.debugMsg("tradeType" + type);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("row", row);
			jsonObject.put("tradeType", type);
			jsonObject.put("trade", trade);
			out.println(jsonObject);
		}

		else if (action.equals("processCombo.roleteam")) {
			// String trade = request.getParameter("trade");
			// CommonMessage.debugMsg("---------------------> "+trade);
			StringBuffer cond = new StringBuffer();
			try {
				ComboFilter filterComboFilter = UIUtils
						.fillComboFilter(request);
				filterComboFilter.setCondSql(cond.toString());
				List<ComboBox> filcomboList = genTlFnlnrolemapService
						.getProcessComboList(filterComboFilter);
				UIUtils.writeComboBox(response, filcomboList, filterComboFilter);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("subProcessCombo.roleteam")) {
			StringBuffer cond = new StringBuffer();
			String processId = request.getParameter("processId");
			// CommonMessage.debugMsg("---------------------> "+processId);

			try {

				if (CommonFunctions.isValidKeyId(processId)
						&& !processId.equals(",")) {
					processId = processId.replace(",", "','");
					cond.append(" AND  SUBP_PROCESSID IN ('" + processId
							+ "') ");
				}
				ComboFilter filterComboFilter = UIUtils
						.fillComboFilter(request);
				filterComboFilter.setCondSql(cond.toString());
				List<ComboBox> filcomboList = genTlFnlnrolemapService
						.getSubProcessComboList(filterComboFilter);
				UIUtils.writeComboBox(response, filcomboList, filterComboFilter);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("subSubProcessCombo.roleteam")) {
			loadSubSubProcessCombo(request, response);
		}

		else if (action.equals("searchnode.roleteam")) {
			searchNode(request, response, httpSession);
		}
		else if(action.equals("roleteamfnln_getCol.roleteam")) {
			getColRoleTeam(request, response, httpSession);
		}

		else if (action.equals("roleteamfnln_getData.roleteam")) {
			getDataRoleTeam(request, response);
		}

		else if (action.equals("empteam_getCol.roleteam")) {
			getColEmpTeam(request, response, httpSession);
		}

		else if (action.equals("empteam_getData.roleteam")) {
			getDataEmpTeam(request, response);
		} else if (action.equals("roleall_getCol.roleteam")) {
			getColRoleAll(request, response, httpSession);
		}

		else if (action.equals("roleall_getData.roleteam")) {
			getDataRoleAll(request, response);
		}

		else if (action.equals("empall_getCol.roleteam")) {
			CommonMessage.debugMsg(" empall_getCol.roleteam ");
			getColEmpAll(request, response, httpSession);
		}

		else if (action.equals("empall_getData.roleteam")) {
			getDataEmpAll(request, response);
		}

		else if (action.equals("roleteamallgrid_getCol.roleteam")) {
			CommonMessage.debugMsg(" roleteamallgrid_getCol.roleteam ");
			getColRoleTeamAllGrid(request, response, httpSession);
		}

		else if (action.equals("roleteamallgrid_getData.roleteam")) {
			getDataRoleTeamAllGrid(request, response);
		} else if (action.equals("roleteamallgrid_getExcel.roleteam")) {
			getExlRoleTeamAllGrid(request, response, httpSession);
			
		} else if (action.equals("displayRoleView_input.roleteam")) {
			String loginLocationId=CommonFunctions.getLoginLocaton(request);
			//String factoryId=CommonFunctions.getLoginLocaton(request);
			
			 String location=UIUtils.getlocation(request);
			 AdmTlUsermst user=UIUtils.getLoginUser(request);
			
			CommonMessage.debugMsg("loginLocationId :" +loginLocationId);
			CommonMessage.debugMsg("Location :" +location);
			 
			request.setAttribute("loginLocationId:", loginLocationId);
			CommonMessage.debugMsg("Login Location :" +loginLocationId);
			RequestDispatcher rd = request.getRequestDispatcher("pages/RoleMappingView.jsp");
			rd.forward(request, response);

		}
		
		
		else if(action.equals("displayRoleView_getCol.roleteam")){
		
			
			httpSession=request.getSession(false);
			PrintWriter out = response.getWriter();
			
			CommonFilter commonFilter=populateCommonFilter(request, "RoleBean", true);
			String colModelIdent = "employeeRoleMapView";
			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RoleTeamMapping",colModelIdent));
			httpSession.removeAttribute("RoleBean");
			httpSession.setAttribute("RoleBean", commonFilter);
			
		}
		
		
	
		
		
	
		
		// =========== VIGNESH 01NOV2025 =================================================//
		
//		else if(action.equals("displayRoleView_getData.roleteam")){			
//			PrintWriter out = response.getWriter();
//			String ActiveYN=request.getParameter("ActiveYN");
//		CommonMessage.debugMsg("Active:"+ActiveYN);
//			CommonFilter commonFilter=populateCommonFilter(request, "RoleBean", true);
//			commonFilter.setActionKeyId(ActiveYN);
//			List<String[]> masterGrid = genTlFnlnrolemapService.getEmployeeGridData(commonFilter);
//			JSONObject dataJson = UIUtils.convertToJqGridTableObject(masterGrid,request, 1, 1,commonFilter.getTotalRecordCnt() );
//			out.print(dataJson);
//			httpSession.removeAttribute("RoleBean");
//			httpSession.setAttribute("RoleBean", commonFilter);
//			
//			
//			
//		}
		
		else if (action.equals("displayRoleView_getData.roleteam")) {
		    PrintWriter out = response.getWriter();

		    String ActiveYN = request.getParameter("ActiveYN");
		    //String ActiveYN = "Y";
		    CommonMessage.debugMsg("Active:" + ActiveYN);

		    CommonFilter commonFilter = populateCommonFilter(request, "RoleBean", true);
		    commonFilter.setActionKeyId(ActiveYN);
		    String Empmtype=request.getParameter("txtEmpwiseType");
		    commonFilter.setEmpwiseType(Empmtype);
		    List<String[]> masterGrid = genTlFnlnrolemapService.getEmployeeGridData(commonFilter);

		    // ====== FIX: remove the unexpected "employeetype" column (inline, no helper) ======
			/*
			 * if (masterGrid != null && !masterGrid.isEmpty()) { // Default position of
			 * employeetype in your function: // rn(0), rolename(1), empmname(2),
			 * empmcode(3), employeetype(4), tradename(5), ... int dropIdx = 4;
			 * 
			 * // Try to detect by header name in row 0 (since you're using
			 * processFunctionCallsWithColHeaders) String[] headerRow = masterGrid.get(0);
			 * if (headerRow != null) { for (int i = 0; i < headerRow.length; i++) { String
			 * h = headerRow[i]; if (h != null) { String hn = h.trim().toLowerCase(); if
			 * ("employeetype".equals(hn) || "employee type".equals(hn)) { dropIdx = i; //
			 * use the exact header position if found break; } } } }
			 * 
			 * // Remove that column from every row (including header) so lengths stay
			 * consistent for (int r = 0; r < masterGrid.size(); r++) { String[] row =
			 * masterGrid.get(r); if (row == null || dropIdx >= row.length) continue;
			 * 
			 * String[] trimmed = new String[row.length - 1]; int p = 0; for (int c = 0; c <
			 * row.length; c++) { if (c == dropIdx) continue; trimmed[p++] = row[c]; }
			 * masterGrid.set(r, trimmed); } }
			 */
		    // ====== /FIX ======

		    JSONObject dataJson = UIUtils.convertToJqGridTableObject(
		            masterGrid, request, /*rowStart*/ 1, /*colStart*/ 1, commonFilter.getTotalRecordCnt());

		    out.print(dataJson);
		    httpSession.removeAttribute("RoleBean");
		    httpSession.setAttribute("RoleBean", commonFilter);
		}

		
		
		
		// =========== VIGNESH 01NOV2025 =================================================//
		else if(action.equals("Combo_ActiveInactive.roleteam")){
			try{
				 response.setContentType("text/html;charset=UTF-8");
				 response.setContentType("json");
			 	 PrintWriter out = response.getWriter();
			     out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RoleTeamMapping", "comboActiveInactive"));
				 out.close();
			} 
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		else if( action.equals("displayRoleView_getExcel.roleteam")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"RoleBean",false);
			commonFilter.setFromRow(null);
			String colModelIdent = "employeeRoleMapView";
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			
			tblJSONObj.put("title", "Employee Role and Location View"); 
			
			String format = ExcelUtils.getFormat(request);
			String ExcelName="Employee.xlsx";
			Workbook wb = genTlFnlnrolemapService.getEmployeeRoleLocationExportExcel(commonFilter,tblJSONObj,format);
			//Workbook wb = productionLossService.getLossExportExcel(commonFilter,tblJSONObj,format);
			ExcelUtils.writeToResponse(response, wb,ExcelName, format);
			
		}
		else if (action.equals("transactionSummary_input.roleteam")) {
		
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/transactionSummary.jsp");
			rd.forward(request, response);

		}else if(action.equals("transactionSummary_getCol.roleteam")){			
			PrintWriter out = response.getWriter();			
			CommonFilter commonFilter = populateCommonFilterForTransactionSummary(request,"transactionSummaryCommonFilter",true);
			commonFilter.setIsGetCol("Y");
			List<String[]> transactionGridData  = genTlFnlnrolemapService.getTransactionSummaryGridData(commonFilter);
			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = transactionGridData.get(2);
			String[] colHeaderCond = transactionGridData.get(1);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "90%%");
			colModel.set("tableWidth", "110%%");
			httpSession.removeAttribute("transactionSummaryColModel");
			httpSession.setAttribute("transactionSummaryColModel", colModel);
			
			httpSession.removeAttribute("transactionSummaryCommonFilter");
			httpSession.setAttribute("transactionSummaryCommonFilter", commonFilter);
			
			out.println(colModel);
			
			
			
		}else if(action.equals("transactionSummary_getData.roleteam")){			
			PrintWriter out = response.getWriter();			
			CommonFilter commonFilter=populateCommonFilterForTransactionSummary(request, "transactionSummaryCommonFilter", false);
			
			commonFilter.setIsGetCol("N");
			List<String[]> transactionGridData = genTlFnlnrolemapService.getTransactionSummaryGridData(commonFilter);
			CommonMessage.debugMsg("transactionGridData.size==="+transactionGridData.size());
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(transactionGridData,request, 3, 0,commonFilter.getTotalRecordCnt() );
			//JSONObject dataJson = UIUtils.convertToJqGridTableObject(transactionGridData,request, 3, 0,transactionGridData.size() );
			//JSONObject dataJson = UIUtils.convertToJqGridTableObject(transactionGridData,request, 3, 0);
			out.print(dataJson);
			httpSession.removeAttribute("transactionSummaryCommonFilter");
			httpSession.setAttribute("transactionSummaryCommonFilter", commonFilter);
			
		}else if( action.equals("transactionSummary_getExcel.roleteam")){
			CommonFilter commonFilter = populateCommonFilterForTransactionSummary(request,"transactionSummaryCommonFilter",false);
			//CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("transactionSummaryCommonFilter");
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("transactionSummaryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String dateVal="";
			CommonMessage.debugMsg("commonFilter.getMonwise"+commonFilter.getMonwise());
			if(commonFilter.getMonwise().equals("Y")){
				dateVal = commonFilter.getFromMonth() + " to " + commonFilter.getToMonth();
			}else {
				dateVal = commonFilter.getFromDate() + " to " + commonFilter.getToDate();
			}
			if(dateVal.equals("Jan-1801 to Dec-2100")){
				tblJSONObj.put("title", "Transaction Summary Report " );
			}else{
				tblJSONObj.put("title", "Transaction Summary Report - " + dateVal);
			}
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = genTlFnlnrolemapService.getTransactionSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);
			//Workbook wb = productionLossService.getLossExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "TransactionSummaryReport", format);
			
			
		}

		
		
		if (dispatchUrl != null) {
			CommonMessage.debugMsg(" dispatchUrl " + dispatchUrl);
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
		}

	}
	

	private void exportRoleMapEmployee(HttpServletRequest request,
			HttpServletResponse response) throws NoDataFoundException,
			Exception {
		JSONObject colmodel = UIUtils.getXlColModel(request, response);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String mode = request.getParameter("mode");
		colmodel.put("title", "Action Plans Employeewise");
	//	ActionPlanParams actionPlanParams = getActionplanFilterParms(request);

		//actionPlanParams.setToRow("-1");
		//String format = ExcelUtils.getFormat(request);

		//Workbook wb = genTlFnlnrolemapService.employeWiseActionPlanExportExcel(	actionPlanParams, colmodel, format);

		//ExcelUtils.writeToResponse(response, wb,	"ActionPlanPendingReportEmployeewise", format);
	}
	
	
	private void getColTradeRoleLink(HttpServletRequest request,
	        HttpServletResponse response, HttpSession httpSession) throws IOException {

	    PrintWriter out = response.getWriter();
	    JSONObject jsonObject = new JSONObject();
	    CommonFilter commonFilter = new CommonFilter();

	    try {
	        FilterValues.getCommonFilters(request, commonFilter);

	        String tradeId = request.getParameter("tradeId");
	        commonFilter.setKey(tradeId);

	        CommonMessage.debugMsg("tradeRoleLink_getCol tradeId=[" + tradeId + "]");

	        GenTlFnlnrolemapService genTlFnlnrolemapService =
	                (GenTlFnlnrolemapService) UIUtils.getServiceObject(request, "GenTlFnlnrolemapServiceImpl");
	        genTlFnlnrolemapService.GenTlFnlnrolemapServiceImplJwt(
	                (String)(httpSession.getAttribute("tpmjwttoken") == null
	                        ? "" : httpSession.getAttribute("tpmjwttoken")));

	        List<String[]> gridList = genTlFnlnrolemapService.getTradeRoleLinkGrid(commonFilter);

	        JqGridTableModel jqGridTableModel = new JqGridTableModel();
	        GridColModel gridColModel = new GridColModel();
	        gridColModel.setHeaderNum(1);
	        jqGridTableModel.setSortable(false);
	        jqGridTableModel.setTableButton(false);
	        jqGridTableModel.setEnableFilter(false);
	        jqGridTableModel.setTableHeight(400);  
	 		jqGridTableModel.setTableWidth(550);
	        jqGridTableModel.setRowNumbers(true);

	        String[] colHeaderHead = gridList.get(0); // header conditions row
	        String[] colHeader     = gridList.get(1); // Trade, Role
	        List<String[]> headers = new ArrayList<>();
	        headers.add(colHeader);

	        jsonObject = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);

	        httpSession.removeAttribute("tradeRoleLinkCommonFilter");
	        httpSession.setAttribute("tradeRoleLinkCommonFilter", commonFilter);

	        httpSession.removeAttribute("tradeRoleLinkColModel");
	        httpSession.setAttribute("tradeRoleLinkColModel", jsonObject);

//	        jsonObject.set("tableWidth", "100%");
//	        jsonObject.set("tableHeight", "100%");
	        
	        jsonObject.set("tableHeight", "50%%");
	        jsonObject.set("tableWidth", "60%%");

	    } catch (Exception e) {
	        CommonMessage.debugMsg("tradeRoleLink_getCol error: " + e.getMessage());
	    }

	    out.println(jsonObject);
	}

	private void getDataTradeRoleLink(HttpServletRequest request,
	        HttpServletResponse response, HttpSession httpSession) throws IOException {

	    try {
	        CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("tradeRoleLinkCommonFilter");
	        if (commonFilter == null) {
	            commonFilter = new CommonFilter();
	        }

	        FilterValues.setPaginationParams(request, commonFilter);

	        String tradeId = request.getParameter("tradeId");
	        if (tradeId != null && !tradeId.trim().isEmpty()) {
	            commonFilter.setKey(tradeId);
	        }
	        commonFilter.setViewClick('Y');

	        CommonMessage.debugMsg("tradeRoleLink_getData tradeId=[" + commonFilter.getKey() + "]");

	        GenTlFnlnrolemapService genTlFnlnrolemapService =
	                (GenTlFnlnrolemapService) UIUtils.getServiceObject(request, "GenTlFnlnrolemapServiceImpl");
	        genTlFnlnrolemapService.GenTlFnlnrolemapServiceImplJwt(
	                (String)(httpSession.getAttribute("tpmjwttoken") == null
	                        ? "" : httpSession.getAttribute("tpmjwttoken")));

	        List<String[]> gridList = genTlFnlnrolemapService.getTradeRoleLinkGrid(commonFilter);

	        PrintWriter out = response.getWriter();
	        JSONObject result = UIUtils.convertToJqGridTableObject(
	                gridList, request, 2, 0, commonFilter.getTotalRecordCnt());
	        out.println(result);

	    } catch (Exception e) {
	        CommonMessage.debugMsg("tradeRoleLink_getData error: " + e.getMessage());
	    }
	}

private void saveTradeRoleLink(HttpServletRequest request,
	        HttpServletResponse response, HttpSession httpSession) throws IOException {

	    PrintWriter out = response.getWriter();
	    AdmTlUsermst user = UIUtils.getLoginUser(request);

	    try {
	        String tradeId = request.getParameter("cmbTrlTradeKeyid");
	        String roleId  = request.getParameter("cmbTrlRoleKeyid");

	        if (tradeId == null || tradeId.trim().isEmpty()
	                || roleId == null || roleId.trim().isEmpty()) {
	            JSONObject err = new JSONObject();
	            err.put("tpmException", "Trade and Role are required");
	            out.print(err.toString());
	            return;
	        }

	        GenTlFnlnrolemapService genTlFnlnrolemapService =
	                (GenTlFnlnrolemapService) UIUtils.getServiceObject(request, "GenTlFnlnrolemapServiceImpl");
	        genTlFnlnrolemapService.GenTlFnlnrolemapServiceImplJwt(
	                (String)(httpSession.getAttribute("tpmjwttoken") == null
	                        ? "" : httpSession.getAttribute("tpmjwttoken")));

	        genTlFnlnrolemapService.saveTradeRoleLink(tradeId, roleId, user.getUsrm_ccno());

	        JSONObject returnData  = new JSONObject();
	        JSONObject successData = new JSONObject();
	        successData.put("msg", "Data Saved Successfully");
	        returnData.put("successData", successData);
	        out.print(returnData.toString());

	    } catch (Exception e) {
	        JSONObject err = new JSONObject();
	        err.put("tpmException", e.getMessage() != null ? e.getMessage() : "Data Not Saved");
	        out.print(err.toString());
	        CommonMessage.debugMsg("roletrademapping_save error: " + e.getMessage());
	    }
	}
	
	private void deleteTradeRoleLink(HttpServletRequest request,
	        HttpServletResponse response, HttpSession httpSession) throws IOException {

	    PrintWriter out = response.getWriter();

	    try {
	    	// commented and added by priyanka on 22/07/2026
	        //String tradeId = request.getParameter("getTrlTradeKeyid");
	        //String roleId  = request.getParameter("getTrlRoleKeyid");

	        String tradeId = request.getParameter("cmbTrlTradeKeyid");
	        String roleId  = request.getParameter("cmbTrlRoleKeyid");
	        
	        // end 
	        
	        CommonMessage.debugMsg("roletrademapping_delete tradeId=[" + tradeId + "] roleId=[" + roleId + "]");

	        if (tradeId == null || tradeId.trim().isEmpty()
	                || roleId == null || roleId.trim().isEmpty()) {
	            JSONObject err = new JSONObject();
	            err.put("tpmException", "Trade and Role are required to delete");
	            out.print(err.toString());
	            return;
	        }

	        GenTlFnlnrolemapService genTlFnlnrolemapService =
	                (GenTlFnlnrolemapService) UIUtils.getServiceObject(request, "GenTlFnlnrolemapServiceImpl");
	        genTlFnlnrolemapService.GenTlFnlnrolemapServiceImplJwt(
	                (String)(httpSession.getAttribute("tpmjwttoken") == null
	                        ? "" : httpSession.getAttribute("tpmjwttoken")));

	        genTlFnlnrolemapService.deleteTradeRoleLink(tradeId, roleId);

	        JSONObject returnData  = new JSONObject();
	        JSONObject successData = new JSONObject();
	        successData.put("msg", "Data Deleted Successfully");
	        returnData.put("successData", successData);
	        out.print(returnData.toString());

	    } catch (Exception e) {
	        JSONObject err = new JSONObject();
	        err.put("tpmException", e.getMessage() != null ? e.getMessage() : "Data Not Deleted");
	        out.print(err.toString());
	        CommonMessage.debugMsg("roletrademapping_delete error: " + e.getMessage());
	    }
	}



	private void getColPillarRoleLink(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
	        throws Exception {

	    PrintWriter out = response.getWriter();

	    try {
	        CommonFilter commonFilter = populateCommonFilter(request, "pillarRoleLinkCommonFilter", true);
	        commonFilter.setIsGetCol("Y");

	        String pillarId = request.getParameter("pillarId");
	        String roleId   = request.getParameter("roleId");

	        List<String[]> pillarRoleLinkList = genTlFnlnrolemapService.getPillarRoleLinkGridData(commonFilter, pillarId, roleId);

	        JqGridTableModel jqGridTableModel = new JqGridTableModel();
	        GridColModel gridColModel = new GridColModel();

	        jqGridTableModel.setRowNumbers(true);
	        jqGridTableModel.setEnableFilter(false);

	        gridColModel.setHeaderNum(1);

	        String[] colHeader     = pillarRoleLinkList.get(1);
	        String[] colHeaderCond = pillarRoleLinkList.get(0);

	        List<String[]> headers = new ArrayList<String[]>();
	        headers.add(colHeader);

	        JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
	        jsonObject.put("tableHeight", "45%%");
	        jsonObject.put("tableWidth",  "55%%");

	        httpSession.setAttribute("PillarRoleLinkColModel", jsonObject);
	        httpSession.setAttribute("pillarRoleLinkCommonFilter", commonFilter);

	        out.println(jsonObject);

	    } catch (Exception e) {
	        CommonMessage.debugMsg("pillarRoleLink_getCol ERROR: " + e.getMessage());
	        e.printStackTrace();
	        out.println("{}");
	    }
	}
	
	private void getDataPillarRoleLink(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
	        throws Exception {

	    PrintWriter out = response.getWriter();

	    try {
	        CommonFilter commonFilter = populateCommonFilter(request, "pillarRoleLinkCommonFilter", false);
	        commonFilter.setIsGetCol("N");

	        String pillarId = request.getParameter("pillarId");
	        String roleId   = request.getParameter("roleId");

	        List<String[]> pillarRoleLinkList = genTlFnlnrolemapService.getPillarRoleLinkGridData(commonFilter, pillarId, roleId);

	        CommonMessage.debugMsg("Rows returned = " + pillarRoleLinkList.size());
	        CommonMessage.debugMsg("TotalRecordCnt = " + commonFilter.getTotalRecordCnt());

	        JSONObject pillarRoleLinkData = UIUtils.convertToJqGridTableObject(
	                pillarRoleLinkList, request, 2, 0, commonFilter.getTotalRecordCnt());

	        httpSession.removeAttribute("pillarRoleLinkCommonFilter");
	        httpSession.setAttribute("pillarRoleLinkCommonFilter", commonFilter);

	        out.println(pillarRoleLinkData);

	    } catch (Exception e) {
	        CommonMessage.debugMsg("pillarRoleLink_getData ERROR: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	private void savePillarRoleLink(HttpServletRequest request, HttpServletResponse response) throws IOException {

	    HttpSession httpSession = request.getSession(false);
	    AdmTlUsermst user = UIUtils.getLoginUser(request);
	    if (httpSession == null || user == null) return;

	    PrintWriter out = response.getWriter();

	    try {
	        String pillarId = request.getParameter("cmbPrlPillarKeyid");
	        String roleId    = request.getParameter("cmbPrlRoleKeyid");

	        if (pillarId == null || pillarId.trim().isEmpty()
	                || roleId == null || roleId.trim().isEmpty()) {
	            JSONObject err = new JSONObject();
	            err.put("tpmException", "Pillar and Role are required");
	            out.print(err.toString());
	            return;
	        }

	        GenTlFnlnrolemapService genTlFnlnrolemapService =
	                (GenTlFnlnrolemapService) UIUtils.getServiceObject(request, "GenTlFnlnrolemapServiceImpl");

	        genTlFnlnrolemapService.savePillarRoleLink(pillarId, roleId, user.getUsrm_ccno());

	        JSONObject successData = new JSONObject();
	        successData.put("msg",
	                UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));

	        JSONObject returnData = new JSONObject();
	        returnData.put("successData", successData);

	        out.print(returnData.toString());

	    } catch (ValidationExceptions e) {
	        JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "pillarRoleLinkCreation");
	        out.print(errMessage.toString());
	    } catch (BusinessApplicationExceptions e) {
	        out.print(UIUtils.businessValidationExceptions(e.toString(), "pillarRoleLinkCreation").toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	        JSONObject err = new JSONObject();
	        err.put("tpmException", e.getMessage() != null ? e.getMessage() : "Data Not Saved");
	        out.print(err.toString());
	        CommonMessage.debugMsg("rolepillarmapping_save error: " + e.getMessage());
	    }
	}
	
	private void deletePillarRoleLink(HttpServletRequest request,
	        HttpServletResponse response, HttpSession httpSession) throws IOException {

	    PrintWriter out = response.getWriter();

	    try {
	        String pillarId = request.getParameter("cmbPrlPillarKeyid");
	        String roleId    = request.getParameter("cmbPrlRoleKeyid");

	        CommonMessage.debugMsg("rolepillarmapping_delete pillarId=[" + pillarId + "] roleId=[" + roleId + "]");

	        if (pillarId == null || pillarId.trim().isEmpty()
	                || roleId == null || roleId.trim().isEmpty()) {
	            JSONObject err = new JSONObject();
	            err.put("tpmException", "Pillar and Role are required to delete");
	            out.print(err.toString());
	            return;
	        }

	        GenTlFnlnrolemapService genTlFnlnrolemapService =
	                (GenTlFnlnrolemapService) UIUtils.getServiceObject(request, "GenTlFnlnrolemapServiceImpl");

	        genTlFnlnrolemapService.deletePillarRoleLink(pillarId, roleId);

	        JSONObject returnData  = new JSONObject();
	        JSONObject successData = new JSONObject();
	        successData.put("msg", "Data Deleted Successfully");
	        returnData.put("successData", successData);
	        out.print(returnData.toString());

	    } catch (Exception e) {
	        JSONObject err = new JSONObject();
	        err.put("tpmException", e.getMessage() != null ? e.getMessage() : "Data Not Deleted");
	        out.print(err.toString());
	        CommonMessage.debugMsg("rolepillarmapping_delete error: " + e.getMessage());
	    }
	}
	
	private void loadSubSubProcessCombo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer cond = new StringBuffer();
		String subProcessId = request.getParameter("subProcessId");
		// String processId = request.getParameter("processId");
		// CommonMessage.debugMsg("---------------------> "+processId);
		// CommonMessage.debugMsg("---------------------> "+subProcessId);

		try {
			/*
			 * if (CommonFunctions.isValidKeyId(processId)){
			 * processId=processId.replace(",","','"); cond.append(
			 * " AND  SBSP_SUBPROCESSID IN (SELECT SUBP_KEYID FROM QTM_TL_SUBPROCESSMST "
			 * ); cond.append(" WHERE SUBP_PROCESSID IN ('" + processId +
			 * "')) "); }
			 */
			if (CommonFunctions.isValidKeyId(subProcessId)
					&& !subProcessId.equals(",")) {
				subProcessId = subProcessId.replace(",", "','");
				cond.append(" AND  SBSP_SUBPROCESSID IN ('" + subProcessId
						+ "') ");
			}
			// if (CommonFunctions.isValidKeyId(cond.toString())){// ||
			// type.equals("P")){
			ComboFilter filterComboFilter = UIUtils.fillComboFilter(request);
			filterComboFilter.setCondSql(cond.toString());
			List<ComboBox> filcomboList = genTlFnlnrolemapService
					.getSubSubProcComboList(filterComboFilter);
			UIUtils.writeComboBox(response, filcomboList, filterComboFilter);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchNode(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws Exception {
		CommonMessage.debugMsg("searchnode.roleteam");
		PrintWriter out = response.getWriter();
		Enumeration<String> params = request.getParameterNames();

		String prevSearchstr = UIUtils
				.getCookieValue(request, "fnlnsearch_str");
		String currentSearchStr = request.getParameter("search_str");

		String countStr = "0";
		List<String[]> searchList = null;
		searchList = functionalLocnServices.getSearchNode(currentSearchStr);
		httpSession.setAttribute("fnlnSearchList", searchList);
		int searchCnt = Integer.parseInt(countStr);
		JSONArray jSONArray = null;
		if (searchCnt < searchList.size()) {
			String parentId = searchList.get(searchCnt)[0];
			CommonMessage.debugMsg(parentId);
			parentId = "#node_1-FL001-" + parentId.replaceAll("/", "_");
			parentId = "-" + parentId.replaceAll("/", "_");
			CommonMessage.debugMsg(parentId);
			parentId = parentId.replaceAll("-", "-#");
			parentId = "#node_1-#node_2" + parentId;
			CommonMessage.debugMsg(parentId);
			String[] searchNode = parentId.split("-");
			jSONArray = JSONArray.fromArray(searchNode);
		} else {
			searchCnt = -1;
		}

		Cookie searchStrCookie = new Cookie("fnlnsearch_str", currentSearchStr);
		searchStrCookie.setHttpOnly(true);
		searchStrCookie.setPath("/");
		searchStrCookie.setMaxAge(60 * 60);
		response.addCookie(searchStrCookie);
		Cookie searchCntCookie = new Cookie("fnlnsearch_str_cnt",
				(searchCnt + 1) + "");
		searchCntCookie.setHttpOnly(true);
		searchCntCookie.setPath("/");
		searchCntCookie.setMaxAge(60 * 60);
		response.addCookie(searchCntCookie);

		out.println(jSONArray);
	}

	public String getImageUrl(String elementType) {
		// CommonMessage.debugMsg("Inside getImage " +elementType);
		String imgUrl = null;
		if (elementType.equals("CMP"))
			imgUrl = "images/companyy.jpg";
		else if (elementType.equals("LCN"))
			imgUrl = "images/FnLocn/location.jpg";
		else if (elementType.equals("F"))
			imgUrl = "images/factory_inside.jpg";
		else if (elementType.equals("L"))
			imgUrl = "images/f.jpg";
		else if (elementType.equals("C"))
			imgUrl = "images/sect.jpg";
		else if (elementType.equals("M"))
			imgUrl = "images/mach.jpg";
		else if (elementType.equals("A"))
			imgUrl = "images/asb.jpg";
		else if (elementType.equals("SPR"))
			imgUrl = "images/spares6.jpg";
		else if (elementType.equals("SSN"))
			imgUrl = "images/5s.jpg";
		else if (elementType.equals("IMT"))
			imgUrl = "images/wire.jpg";
		else if (elementType.equals("FL"))
			imgUrl = "images/companyy.jpg";

		return imgUrl;
	}

	public String getLevel(String elementType) {
		// CommonMessage.debugMsg("Inside getIcon " +elementType);
		String level = "0";
		if (elementType.equals("CMP"))
			level = "1";
		else if (elementType.equals("LCN"))
			level = "2";
		else if (elementType.equals("F"))
			level = "3";
		else if (elementType.equals("SBU"))
			level = "4";
		else if (elementType.equals("PBU"))
			level = "5";
		else if (elementType.equals("L"))
			level = "6";
		else if (elementType.equals("C"))
			level = "7";
		else if (elementType.equals("M"))
			level = "8";

		return level;

	}

	public String getIconImage(String elementType) {
		// CommonMessage.debugMsg("Inside getIcon " +elementType);
		String imgUrl = null;
		if (elementType.equals("CMP"))
			imgUrl = "images/FnLocn/company.jpg";
		else if (elementType.equals("LCN"))
			imgUrl = "images/FnLocn/location.jpg";
		else if (elementType.equals("F"))
			imgUrl = "images/FnLocn/factory.jpg";
		else if (elementType.equals("L"))
			imgUrl = "images/FnLocn/unit.jpg";
		else if (elementType.equals("C"))
			imgUrl = "images/FnLocn/section.jpg";
		else if (elementType.equals("M"))
			imgUrl = "images/FnLocn/machine.jpg";
		else if (elementType.equals("A"))
			imgUrl = "images/FnLocn/assembly.jpg";
		else if (elementType.equals("SPR"))
			imgUrl = "images/FnLocn/spare.png";
		else if (elementType.equals("SSN"))
			imgUrl = "images/FnLocn/sub-section.jpg";
		else if (elementType.equals("IMT"))
			imgUrl = "images/FnLocn/instrument.jpg";
		else if (elementType.equals("FL"))
			imgUrl = "images/FnLocn/fc.png";

		return imgUrl;

	}

	private void saveRoleMapping(HttpServletRequest request,
			HttpServletResponse response,
			GenTlFnlnrolemapBean genTlFnlnrolemapBean) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				GenTlFnlnrolemap existGenTlFnlnrolemap = (GenTlFnlnrolemap) httpSession
						.getAttribute("GenTlFnlnrolemap");
				GenTlFnlnrolemap newGenTlFnlnrolemap = new GenTlFnlnrolemap();
				String rolemap = request.getParameter("rolemap");
				// String flid = request.getParameter("flid");
				String level = request.getParameter("level");
				String title = request.getParameter("title");
				newGenTlFnlnrolemap = (GenTlFnlnrolemap) UIUtils
						.setBeanProperties((Object) newGenTlFnlnrolemap,
								request);
				List<GenTlFnlnrolemap> genTlFnlnrolemapList = null;
				JSONArray genTlFnlnrolemapListjson = null;
				// CommonMessage.debugMsg("flid:"+flid);
				CommonMessage.debugMsg("level inside saveRoleMapping " + level);
				// CommonMessage.debugMsg("rolemap "+rolemap);
				if (UIUtils.isValidKeyId(rolemap)) {
					genTlFnlnrolemapListjson = JSONArray.fromString(rolemap);
					genTlFnlnrolemapList = (List<GenTlFnlnrolemap>) UIUtils
							.convertJSONArrToList(newGenTlFnlnrolemap,
									genTlFnlnrolemapListjson);

					if (genTlFnlnrolemapList != null) {
						CommonMessage.debugMsg(" genTlFnlnrolemapList 2");
						for (int i = 0; i <= genTlFnlnrolemapList.size() - 1; i++) {
							genTlFnlnrolemapList.get(i).setFrlUserid(
									user.getUsrm_ccno());
							// genTlFnlnrolemapList.get(i).setFrlFnlnKeyid(flid);
							genTlFnlnrolemapList.get(i).setFrlLevel(level);
						}
						genTlFnlnrolemapList = genTlFnlnrolemapService
								.create(genTlFnlnrolemapList);

						httpSession.setAttribute("genTlFnlnrolemapList",
								genTlFnlnrolemapList);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue(
								"com.akranta.tpm.resources.CommonMessages",
								"success-save"));
						successData.put("title", title);
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
					}
				}
			}

		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
					e.toString(), "GenTlFnlnrolemap");
			out.print(errMessage.toString());
		}

		catch (BusinessApplicationExceptions e) {
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(
					e.toString(), "GenTlFnlnrolemap");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}

	}

	private void getlevelrole(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {

				String flid = request.getParameter("flid");
				CommonMessage.debugMsg("flid" + flid);
				List<String[]> data = genTlFnlnrolemapService
						.getlevelrole(flid);

				if (data != null) {
					JSONObject successData = new JSONObject();
					successData.put("level", data.get(0)[1]);
					successData.put("location", data.get(0)[2]);
					CommonMessage.debugMsg(data.get(0)[2]);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					out.print(returnData.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());
		}
	}

	private void deleteRoleMapping(HttpServletRequest request,
			HttpServletResponse response,
			GenTlFnlnrolemapBean genTlFnlnrolemapBean) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {

		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				GenTlFnlnrolemap existGenTlFnlnrolemap = (GenTlFnlnrolemap) httpSession
						.getAttribute("GenTlFnlnrolemap");
				GenTlFnlnrolemap newGenTlFnlnrolemap = new GenTlFnlnrolemap();
				// String rolemap = request.getParameter("rolemap");
				String keyid = request.getParameter("keyid");
				newGenTlFnlnrolemap = (GenTlFnlnrolemap) UIUtils
						.setBeanProperties((Object) newGenTlFnlnrolemap,
								request);
				// List<GenTlFnlnrolemap> genTlFnlnrolemapList = null;
				// JSONArray genTlFnlnrolemapListjson = null;
				CommonMessage.debugMsg("rolemap Xml" + keyid);
				if (UIUtils.isValidKeyId(keyid)) {
				
					keyid = "" + keyid.replace(",", "','") + "";
					newGenTlFnlnrolemap.setFrlKeyid(keyid);
					newGenTlFnlnrolemap = genTlFnlnrolemapService
							.delete(newGenTlFnlnrolemap);
					JSONObject successData = new JSONObject();
					successData.put("msg", UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"success-delete"));
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					returnData.put("formClear", true);
					out.print(returnData.toString());
					// }
				}
			}

		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
					e.toString(), "GenTlFnlnrolemap");
			out.print(errMessage.toString());
		}

		catch (BusinessApplicationExceptions e) {
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(
					e.toString(), "GenTlFnlnrolemap");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());
		}
	}

	private void saveRoleTeamEmp(HttpServletRequest request,
			HttpServletResponse response,
			GenTlFnlnrolemapBean genTlFnlnrolemapBean) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		// CommonMessage.debugMsg("saveRoleTeamEmp");
		String[] processIdList = null;
		String[] subProcessIdList = null;
		String[] subSubProcessList = null;
		try {
			if (httpSession != null && user != null) {
				GenTlFnlnroleteam existGenTlFnlnrolemap = (GenTlFnlnroleteam) httpSession
						.getAttribute("GenTlFnlnroleteam");
				GenTlFnlnroleteam newGenTlFnlnroleteam = new GenTlFnlnroleteam();
				GenTlTeamtradelink newGenTlTeamtradelink = new GenTlTeamtradelink();
				String flid = request.getParameter("flid");
				String roleid = request.getParameter("roleid");
				String rolemap = request.getParameter("teamRoleEmp");
				CommonMessage.debugMsg("printing roleemployee inside servlet  "  + rolemap);
				String title = request.getParameter("title");
				String frlkeyid = request.getParameter("frlkeyid");
				// String teamTradeLink = request.getParameter("teamTradeLink");
				newGenTlFnlnroleteam = (GenTlFnlnroleteam) UIUtils
						.setBeanProperties((Object) newGenTlFnlnroleteam,
								request);
				newGenTlTeamtradelink = (GenTlTeamtradelink) UIUtils
						.setBeanProperties((Object) newGenTlTeamtradelink,
								request);

				List<GenTlFnlnroleteam> genTlFnlnroleteamList = null;
				JSONArray genTlFnlnroleteamListjson = null;

				CommonMessage.debugMsg("save GenTlFnlnroleteam Xml");
				CommonMessage.debugMsg("rolemap GenTlFnlnroleteam Xml"
						+ rolemap);
				if (UIUtils.isValidKeyId(rolemap)) {
					genTlFnlnroleteamListjson = JSONArray.fromString(rolemap);
					genTlFnlnroleteamList = (List<GenTlFnlnroleteam>) UIUtils
							.convertJSONArrToList(newGenTlFnlnroleteam,
									genTlFnlnroleteamListjson);

					if (genTlFnlnroleteamList != null) {
						CommonMessage.debugMsg(" genTlFnlnrolemapList 2");
						for (int i = 0; i <= genTlFnlnroleteamList.size() - 1; i++) {
							String trade = genTlFnlnroleteamList.get(i)
									.getTrade();
							genTlFnlnroleteamList.get(i).setFrtCreatedby(
									user.getUsrm_ccno());
							genTlFnlnroleteamList.get(i).setFrtFnlnKeyid(flid);
							genTlFnlnroleteamList.get(i)
									.setFrtRoleKeyid(roleid);
							if (!CommonFunctions.isValidKeyId(trade)) {
								genTlFnlnroleteamList.get(i).setFrtFrlKeyid(
										frlkeyid);
							}
							CommonFunctions
									.debugMsg("genTlFnlnroleteamList.get(i).getFrtFrlKeyid():"
											+ genTlFnlnroleteamList.get(i)
													.getFrtFrlKeyid());
							/*
							 * CommonMessage.debugMsg(
							 * "genTlFnlnroleteamList.get(i).getTeamTrade():"
							 * +genTlFnlnroleteamList.get(i).getTrade());
							 * CommonMessage.debugMsg(
							 * "genTlFnlnroleteamList.get(i).getProcessId():"
							 * +genTlFnlnroleteamList.get(i).getProcessId());
							 * CommonMessage.debugMsg(
							 * "genTlFnlnroleteamList.get(i).getSubProcessId():"
							 * +genTlFnlnroleteamList.get(i).getSubProcessId());
							 * CommonMessage.debugMsg(
							 * "genTlFnlnroleteamList.get(i).getSubSubProcessId():"
							 * +
							 * genTlFnlnroleteamList.get(i).getSubSubProcessId()
							 * );
							 */
							List<GenTlTeamtradelink> genTlTeamtradelinkList = new ArrayList<GenTlTeamtradelink>();

							if (CommonFunctions
									.isValidKeyId(genTlFnlnroleteamList.get(i)
											.getProcessId()))
								processIdList = genTlFnlnroleteamList.get(i)
										.getProcessId().split(",");
							if (CommonFunctions
									.isValidKeyId(genTlFnlnroleteamList.get(i)
											.getSubProcessId()))
								subProcessIdList = genTlFnlnroleteamList.get(i)
										.getSubProcessId().split(",");
							if (CommonFunctions
									.isValidKeyId(genTlFnlnroleteamList.get(i)
											.getSubSubProcessId()))
								subSubProcessList = genTlFnlnroleteamList
										.get(i).getSubSubProcessId().split(",");
							GenTlTeamtradelink genTlTeamtradelink = null;
							boolean tradeFlg = false;

							if (CommonFunctions
									.isValidKeyId(genTlFnlnroleteamList.get(i)
											.getSubSubProcessId())) {
								for (int subsubpro = 0; subsubpro < subSubProcessList.length; subsubpro++) {
									tradeFlg = true;
									if (CommonFunctions
											.isValidKeyId(subSubProcessList[subsubpro])) {
										String subProcessId = genTlFnlnrolemapService
												.getSubProcessId(subSubProcessList[subsubpro]);
										String processId = genTlFnlnrolemapService
												.getProcessId(subProcessId);
										// CommonMessage.debugMsg("processId:"+processId+":subProcessId:"+subProcessId);
										genTlTeamtradelink = new GenTlTeamtradelink();
										genTlTeamtradelink.setFrpTradeid(trade);
										genTlTeamtradelink
												.setFrpProcessid(processId);
										genTlTeamtradelink
												.setFrpSubprocessid(subProcessId);
										genTlTeamtradelink
												.setFrpSubsubprocessid(subSubProcessList[subsubpro]);
										genTlTeamtradelinkList
												.add(genTlTeamtradelink);
									}
								}
							}
							if (CommonFunctions
									.isValidKeyId(genTlFnlnroleteamList.get(i)
											.getSubProcessId())) {
								for (int subpro = 0; subpro < subProcessIdList.length; subpro++) {
									tradeFlg = true;
									if (CommonFunctions
											.isValidKeyId(subProcessIdList[subpro])) {
										if (!IsProcessExists(
												genTlTeamtradelinkList,
												"subprocess",
												subProcessIdList[subpro])) {
											String processId = genTlFnlnrolemapService
													.getProcessId(subProcessIdList[subpro]);
											genTlTeamtradelink = new GenTlTeamtradelink();
											genTlTeamtradelink
													.setFrpTradeid(trade);
											genTlTeamtradelink
													.setFrpProcessid(processId);
											genTlTeamtradelink
													.setFrpSubprocessid(subProcessIdList[subpro]);
											genTlTeamtradelink
													.setFrpSubsubprocessid("{}");
											genTlTeamtradelinkList
													.add(genTlTeamtradelink);
										}
									}
								}
							}
							if (CommonFunctions
									.isValidKeyId(genTlFnlnroleteamList.get(i)
											.getProcessId())) {
								for (int pro = 0; pro < processIdList.length; pro++) {
									tradeFlg = true;
									if (CommonFunctions
											.isValidKeyId(processIdList[pro])) {
										if (!IsProcessExists(
												genTlTeamtradelinkList,
												"process", processIdList[pro])) {
											genTlTeamtradelink = new GenTlTeamtradelink();
											genTlTeamtradelink
													.setFrpTradeid(trade);
											genTlTeamtradelink
													.setFrpProcessid(processIdList[pro]);
											genTlTeamtradelink
													.setFrpSubprocessid("{}");
											genTlTeamtradelink
													.setFrpSubsubprocessid("{}");
											genTlTeamtradelinkList
													.add(genTlTeamtradelink);
										}
									}
								}
							}
							if (tradeFlg == false) {
								// CommonMessage.debugMsg("genTlTeamtradelinkList.size():"+genTlTeamtradelinkList.size());
								if (CommonFunctions.isValidKeyId(trade)) {
									genTlTeamtradelink = new GenTlTeamtradelink();
									genTlTeamtradelink.setFrpTradeid(trade);
									genTlTeamtradelink.setFrpProcessid("{}");
									genTlTeamtradelink.setFrpSubprocessid("{}");
									genTlTeamtradelink
											.setFrpSubsubprocessid("{}");
									genTlTeamtradelinkList
											.add(genTlTeamtradelink);
								}
							}
							if (genTlTeamtradelinkList.size() > 0) {
								genTlFnlnroleteamList.get(i).setTeamtradelink(
										genTlTeamtradelinkList);
							}
						}
						genTlFnlnroleteamList = genTlFnlnrolemapService
								.createTeam(genTlFnlnroleteamList);
						httpSession.setAttribute("genTlFnlnroleteamList",
								genTlFnlnroleteamList);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue(
								"com.akranta.tpm.resources.CommonMessages",
								"success-save"));
						successData.put("title", title);
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", false);
						out.print(returnData.toString());
					}
				}
			}

		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
					e.toString(), "GenTlFnlnrolemap");
			out.print(errMessage.toString());
		}

		catch (BusinessApplicationExceptions e) {
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(
					e.toString(), "GenTlFnlnrolemap");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}

	}

	private void deleteRoleTeamEmpSingle(HttpServletRequest request,
			HttpServletResponse response,
			GenTlFnlnrolemapBean genTlFnlnrolemapBean) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				GenTlFnlnroleteam existGenTlFnlnrolemap = (GenTlFnlnroleteam) httpSession
						.getAttribute("GenTlFnlnroleteam");
				GenTlFnlnroleteam newGenTlFnlnroleteam = new GenTlFnlnroleteam();
				String keyid = request.getParameter("keyid");
				newGenTlFnlnroleteam = (GenTlFnlnroleteam) UIUtils
						.setBeanProperties((Object) newGenTlFnlnroleteam,
								request);
				keyid = "" + keyid.replace(",", "','") + "";
				newGenTlFnlnroleteam.setFrtKeyid(keyid);
				CommonMessage.debugMsg("newGenTlFnlnroleteam keyid:" + keyid);
				if (UIUtils.isValidKeyId(keyid)) {
					newGenTlFnlnroleteam = genTlFnlnrolemapService
							.deleteTeam(newGenTlFnlnroleteam);
					httpSession.setAttribute("newGenTlFnlnroleteam",
							newGenTlFnlnroleteam);
					JSONObject successData = new JSONObject();
					successData.put("msg", UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"success-delete"));
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					// returnData.put("formClear",true);
					out.print(returnData.toString());
				}
			}

		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
					e.toString(), "GenTlFnlnrolemap");
			out.print(errMessage.toString());
		}
		
		catch (BusinessApplicationExceptions e) {
			e.printStackTrace();
			CommonMessage.debugMsg("BusinessApplicationExceptions"+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "GenTlFnlnrolemap");
			out.print(errMessage.toString());
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception"+e.getMessage());
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "" + e.getMessage());
			out.print(err.toString());
		}
	}

	private boolean IsProcessExists(
			List<GenTlTeamtradelink> genTlTeamtradelinkList,
			String processType, String processName) {
		boolean isExists = false;
		String process = "";
		for (int i = 0; i <= genTlTeamtradelinkList.size() - 1; i++) {
			if (processType.equals("process"))
				process = genTlTeamtradelinkList.get(i).getFrpProcessid();
			else if (processType.equals("subprocess"))
				process = genTlTeamtradelinkList.get(i).getFrpSubprocessid();
			else if (processType.equals("subsubprocess"))
				process = genTlTeamtradelinkList.get(i).getFrpSubsubprocessid();

			if (processName.equals(process)) {
				isExists = true;
			}
		}
		return isExists;
	}

	private String genFormControls(List<String[]> roleEmpList, String flId) {
		StringBuffer form = new StringBuffer();
		StringBuffer script = new StringBuffer();
		StringBuffer btnScript = new StringBuffer();
		String elementHtml = "";
		String label = "";
		int multipleCnt = 0;
		int SingleCnt = 0;
		script.append("<script type='text/javascript'>");
		script.append(" jQuery(document).ready(function(){");
		script.append(" initialiseForm('frmRoleTeam');");
		script.append(" initialiseDateFields('frmRoleTeam');");
		script.append(" jQuery('#submitForm').val('frmRoleTeam');");
		script.append(" jQuery('#frmRoleTeam .easyui-text').css('text-transform', 'uppercase');");
		script.append(" jQuery('#frmRoleTeam textarea').css('text-transform', 'uppercase');");

		script.append("jQuery('#btnSelect').click(function(){");
		script.append("		var roleKeyid=jQuery('#cmbRole').combobox('getValue');");
		script.append("		var roleName=jQuery('#cmbRole').combobox('getText');");
		// script.append("	alert('role:'+roleKeyid+',roleName'+roleName);");
		script.append("		if( roleKeyid == null || roleKeyid.length ==0  ){");
		script.append("			alert('Select Role');");
		script.append("			return false;");
		script.append("		}");
		script.append("		var url='roleteamemp_input.roleteam?q=2&roleid='+escape(roleKeyid)+'&rolename='+escape(roleName);");
		// script.append("	processGridnew('Roleteamgrid_input.roleteam','?q=2&roleid='+roleKeyid,'empgrid','emppager','','','','','','');");
		// --------------------------------------------------------------width,height,top,left
		script.append("		LoadPopUp('divRoleTeamEmpPopup', url, true,'82%','70%','15%','9%', 'loadEmpSuccess_Callback','Employee List',false,true);");
		script.append("  });");
		script.append("});");
		script.append("function empLoadComplete(){ jQuery('.ui-pg-table').css('margin-top','4');jQuery('.ui-pg-table').css('font-size','12');}");
		script.append(" function RoleTeamLoadSucess(){");
		script.append(" var flid=jQuery('#txtFrlFnlnKeyid').val();");
		form.append("<table cellspacing='5' style='height:91%;padding-left:45px;'> ");
		form.append("<tr>");

		String controlId = null;
		if (roleEmpList.size() > 0) {

			for (String[] row : roleEmpList) {
				String roleKeyid = row[2];
				String roleName = row[3];
				String NoOfEmp = row[4];
				String empmKeyid = row[5];
				String frtKeyid = row[6];
				roleKeyid = roleKeyid.replace("_", "");

				if (NoOfEmp.substring(0, 1).equals("S")) {
					SingleCnt = SingleCnt + 1;
					if (SingleCnt == 1) {
						form.append("<td valign='top'>");
						form.append("<div id='divSingleEmp' style='float:left;margin-top:0px;padding:10px;height:96%;overflow:auto;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;'>");
						form.append("<div class='sub-header' style='width:295px;margin-top:5px;padding-bottom:0px;' >Assign Single Employee</div>");
					}
					label = UIUtils.getEasyUILabelHtml(roleName, true);
					form.append("<div>");
					form.append(label);
					form.append("</div>");
					controlId = "cmb" + roleKeyid;

					elementHtml = getEasyUIComboboxHtml(controlId, true, "265");
					script.append("fillComboBox('frmRoleTeam','" + controlId
							+ "','employeeFilter.commonFilter?roleId="
							+ roleKeyid + "&flid=" + flId + "');");
					form.append("<div class='easyui-paddingbfpx'>");
					form.append(elementHtml);
					elementHtml = getHiddenTextHtml("hdn" + roleKeyid, frtKeyid);
					script.append("setFieldValue('" + controlId + "', '"
							+ empmKeyid + "','frmRoleTeam');");
					// script.append("jQuery('#"+controlId+"').combobox('setValue','"+empmKeyid+"');");
					form.append(elementHtml);
					elementHtml = getHiddenTextHtml("hdnName" + roleKeyid,
							roleName);
					form.append(elementHtml);
					form.append("<span style='margin-left:5px;'><input class='easyui-button' type='button' id='btn"
							+ roleKeyid
							+ "' name='btn"
							+ roleKeyid
							+ "' value='...'></span>");
					form.append("</div>");
					btnScript.append("jQuery('#btn" + roleKeyid
							+ "').click(function(){");
					// btnScript.append("		alert('"+roleKeyid+"');");
					btnScript.append("		var keyid=jQuery('#" + "hdn"
							+ roleKeyid + "').val();");
					btnScript.append("		var empmkeyid=jQuery('#" + controlId
							+ "').combobox('getValue');");
					// btnScript.append("		var url='roleteamempsingle_input.roleteam?q=2&roleid="+roleKeyid+"';");
					btnScript
							.append("		var url='roleteamempsingle_input.roleteam?q=2&roleid="
									+ roleKeyid
									+ "&empmkeyid='+empmkeyid+'&keyid='+keyid+'&flid="
									+ flId + "';");
					// -----------------------------------------------------------------width,height,top,left
					btnScript
							.append("		LoadPopUp('divRoleTeamEmpSinglePopup', url, true,'52%','60%','15%','25%', 'loadEmpSuccess_Callback','Assign Trade/Process',false,true);");
					btnScript.append("});");
				} else if (NoOfEmp.substring(0, 1).equals("M")) {
					multipleCnt = multipleCnt + 1;
				}
			}
			if (SingleCnt > 1) {
				form.append("</div>");
				form.append("</td>");
			}
			// CommonMessage.debugMsg("multipleCnt:"+multipleCnt);
			if (multipleCnt > 0) {
				form.append("<td valign='top'>");
				form.append("<div id='divMultipleEmp' style='float:left;margin-top:0px;padding:10px;height:96%;overflow:auto;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;'>");
				form.append("<div class='sub-header' style='width:295px;margin-top:5px;padding-bottom:0px;'>Assign Multiple Employee</div>");
				label = UIUtils.getEasyUILabelHtml("Role", true);
				form.append("<div style='float:left;'>");

				form.append("<div style='float:left;'>");
				form.append("<div>");
				form.append(label);
				form.append("</div>");
				controlId = "cmbRole";
				elementHtml = getEasyUIComboboxHtml(controlId, true, "200");
				script.append("fillComboBox('frmRoleTeam','" + controlId
						+ "','RoleCombo.roleteam?flid=" + flId + "');");
				form.append("<div class='easyui-paddingbfpx'>");
				form.append(elementHtml);
				form.append("</div>");
				form.append("</div>");

				form.append("<div style='float:left;'>");

				form.append("<div class='easyui-paddingbfpx' style='margin-left:70%;margin-top:-28px;'>");
				form.append("<input type='button' value='Select Employee' class='easyui-button' style='height : 21px; width : 100px;' id='btnSelect'>");
				form.append("</div>");

				form.append("</div>");

				form.append("</div>");
				script.append("processGridnew('Roleteamgrid_input.roleteam','?q=2&flid="
						+ flId
						+ "&roleId=','empgrid','emppager','','','','empLoadComplete','','');");
				form.append("<div style='padding-top:36px;'>");
				form.append("<table id='empgrid'>");
				form.append("</table>");
				form.append("<div id='emppager'></div>");
				form.append("</div>");

				form.append("</div>");
				form.append("</td>");
			}
		} else {
			label = UIUtils.getEasyUILabelHtml(
					"Assign Role Map For This Functional Location", true);
			form.append("<div>");
			form.append(label);
			form.append("</div>");
		}

		form.append("</tr>");
		form.append("</table>");
		script.append("} ");
		script.append(btnScript);
		script.append("</script> ");
		script.append(form);
		CommonMessage.debugMsg(script.toString());
		return script.toString();
	}

	private String getHiddenTextHtml(String id, String Value) {
		StringBuffer form = new StringBuffer();
		form.append("<input id='");
		form.append(id);
		form.append("' name='");
		form.append(id);
		form.append("' value='");
		form.append(Value);
		form.append("' type='hidden' />");
		return form.toString();
	}

	private String getEasyUIComboboxHtml(String id, boolean clearGotFocus,
			String width) {
		StringBuffer form = new StringBuffer();

		form.append("<input id='");
		form.append(id);
		form.append("' name='");
		form.append(id);
		if (!clearGotFocus)
			form.append("' clear='false");

		form.append("' type='text' class='easyui-combobox' style='width:"
				+ width + "px;'/>");

		return form.toString();
	}

	//----------------Vignesh 24Oct2025 ------------------------------------------------------------------------------//
	
	private void getColRoleTeam(HttpServletRequest request,
	        HttpServletResponse response, HttpSession httpSession)
	        throws IOException {

	    PrintWriter out = response.getWriter();
	    String flid = request.getParameter("flid");
	    String level = request.getParameter("level");

	    JSONObject jsonObject = new JSONObject();
	    List<String[]> roleMapGrid = null;
	    try {
	        CommonFilter commonFilter = new CommonFilter();
	        commonFilter.setFlid(flid);
	        commonFilter.setKey(level);
	        FilterValues.getCommonFilters(request, commonFilter);
	        roleMapGrid = genTlFnlnrolemapService.getRoleTeamList(commonFilter);
	    } catch (Exception e) {
	        CommonMessage.debugMsg(e.getMessage());
	    }

	    JqGridTableModel jqGridTableModel = new JqGridTableModel();
	    GridColModel gridColModel = new GridColModel();

	    gridColModel.setHeaderNum(1);

	    // Apply the checkbox formatter to the real checkbox column: index 1
	    gridColModel.setFormatter("formatterChkRoleMap");
	    gridColModel.setFormattorFromCol("1");
	    gridColModel.setFormattorToCol("1");

	    jqGridTableModel.setSortable(false);
	    jqGridTableModel.setTableButton(false);
	    jqGridTableModel.setEnableFilter(false);
	    jqGridTableModel.setRowNumbers(true);

	    // IMPORTANT: pass meta row (0) as conditions, display row (1) as headers
	    String[] colHeaderCond = roleMapGrid.get(0); // has AL=..., WI=..., HD=...
	    String[] colHeader     = roleMapGrid.get(1); // human-readable headers

	    // (Optional) change header text from "check" to "Select" without touching the result set
	    colHeader = colHeader.clone();
	    colHeader[1] = "Select";

	    List<String[]> headers = new ArrayList<>();
	    headers.add(colHeader);

	    jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);

	    // minor typo: single % is correct
	    jsonObject.set("tableWidth", "38%");
	    jsonObject.set("tableHeight", "33%");

	    httpSession.removeAttribute("roleMapColModel");
	    httpSession.setAttribute("roleMapColModel", jsonObject);

	    out.println(jsonObject);
	}

	
	private void getDataRoleTeam(HttpServletRequest request,
	        HttpServletResponse response) {
	    String flid = request.getParameter("flid");
	    String level = request.getParameter("level");
	    try {
	        CommonFilter commonFilter = new CommonFilter();
	        commonFilter.setFlid(flid);
	        commonFilter.setKey(level);
	        FilterValues.getCommonFilters(request, commonFilter);
	        List<String[]> roleMapGrid = genTlFnlnrolemapService.getRoleTeamList(commonFilter);

	        PrintWriter out = response.getWriter();

	        // Skip the two header rows; drop 1 column from the right (dataorder)
	        long total = Math.max(0, roleMapGrid.size() - 2);
	        JSONObject grid = UIUtils.convertToJqGridTableObject(
	                roleMapGrid, request,
	                /*rowStart*/ 2,
	                /*colStart*/ 0,
	                /*colSub*/   1,
	                /*totalRecords*/ total);

	        out.println(grid);
	    } catch (Exception e) {
	        CommonMessage.debugMsg(e.getMessage());
	    }
	}

	
	
	
	
//	private void getColRoleTeam(HttpServletRequest request,
//			HttpServletResponse response, HttpSession httpSession)
//			throws IOException {
//		CommonMessage.debugMsg(" form getCol");
//		PrintWriter out = response.getWriter();
//		String flid = request.getParameter("flid");
//		String level = request.getParameter("level");
//		CommonMessage.debugMsg("level " + level);
//		JSONObject jsonObject = new JSONObject();
//		List<String[]> roleMapGrid = null;
//		try {
//			CommonFilter commonFilter = new CommonFilter();
//			commonFilter.setFlid(flid);
//			commonFilter.setKey(level);
//			FilterValues.getCommonFilters(request, commonFilter);
//			roleMapGrid = genTlFnlnrolemapService.getRoleTeamList(commonFilter);
//		} catch (Exception e) {
//			CommonMessage.debugMsg(e.getMessage());
//		}
//		JqGridTableModel jqGridTableModel = new JqGridTableModel();
//		GridColModel gridColModel = new GridColModel();
//		// changed  1 to 0
//		gridColModel.setHeaderNum(1);
//		gridColModel.setFormatter("formatterChkRoleMap");
//		gridColModel.setFormattorFromCol("2");
//		gridColModel.setFormattorToCol("2");
//
//		jqGridTableModel.setSortable(false);
//		jqGridTableModel.setTableButton(false);
//		jqGridTableModel.setEnableFilter(false);
//		jqGridTableModel.setRowNumbers(true);
//  // --------- Vignesh -- Grid -- Correctcion -- 23Oct2025 1 and 2 to 0 and 1--- //
//		String[] colHeaderHead = roleMapGrid.get(0);
//		String[] colHeader = roleMapGrid.get(1);
//
//		List<String[]> headers = new ArrayList<String[]>();
//		headers.add(colHeader);
//
//		jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
//				jqGridTableModel, gridColModel);
//		jsonObject.set("tableWidth", "38%%");
//		jsonObject.set("tableHeight", "33%%");
//		httpSession.removeAttribute("roleMapColModel");
//		httpSession.setAttribute("roleMapColModel", jsonObject);
//		CommonMessage.debugMsg("jsonObject " + jsonObject);
//		out.println(jsonObject);
//	}
//
//	private void getDataRoleTeam(HttpServletRequest request,
//			HttpServletResponse response) {
//		String flid = request.getParameter("flid");
//		String level = request.getParameter("level");
//		try {
//			CommonFilter commonFilter = new CommonFilter();
//			commonFilter.setFlid(flid);
//			commonFilter.setKey(level);
//			FilterValues.getCommonFilters(request, commonFilter);
//			List<String[]> roleMapGrid = genTlFnlnrolemapService
//					.getRoleTeamList(commonFilter);
//			PrintWriter out = response.getWriter();
//			
//			
//			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
//					roleMapGrid, request, 2, 0);
//			out.println(studentreportgrid);
//		} catch (Exception e) {
//			CommonMessage.debugMsg(e.getMessage());
//		}
//	}

	//----------------Vignesh 24Oct2025 ------------------------------------------------------------------------------//
	private void getColEmpTeam(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		CommonMessage.debugMsg(" form getCol");
		PrintWriter out = response.getWriter();
		String flid = request.getParameter("flid");
		String roleid = request.getParameter("roleid");
		String frlkeyid = request.getParameter("frlkeyid");
		String level = request.getParameter("level");
		JSONObject jsonObject = new JSONObject();
		List<String[]> roleMapGrid = null;
		try {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			commonFilter.setKey(level);
			commonFilter.setKK(frlkeyid);
			commonFilter.setType(roleid);
			FilterValues.getCommonFilters(request, commonFilter);
			roleMapGrid = genTlFnlnrolemapService.getEmpTeamList(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		GridColModel gridColModel = new GridColModel();
		gridColModel.setHeaderNum(1);
		gridColModel.setFormatter("formatterChkRoleEmp");
		gridColModel.setFormattorFromCol("2");
		gridColModel.setFormattorToCol("2");

		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);

		String[] formatterval = { "cmbTrade#10", "cmbProcess#12",
				"cmbSubProc#14", "cmbSubSubProc#16" };
		jqGridTableModel.setFormatterIndex(formatterval);

		String[] colHeaderHead = roleMapGrid.get(0);
		String[] colHeader = roleMapGrid.get(1);

		List<String[]> headers = new ArrayList<String[]>();
		headers.add(colHeader);

		jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
				jqGridTableModel, gridColModel);
		jsonObject.set("tableWidth", "38%%");
		jsonObject.set("tableHeight", "42%%");
		httpSession.removeAttribute("roleMapColModel");
		httpSession.setAttribute("roleMapColModel", jsonObject);
		CommonMessage.debugMsg("jsonObject " + jsonObject);
		out.println(jsonObject);
	}

	private void getDataEmpTeam(HttpServletRequest request,
			HttpServletResponse response) {
		String flid = request.getParameter("flid");
		String roleid = request.getParameter("roleid");
		String frlkeyid = request.getParameter("frlkeyid");
		String level = request.getParameter("level");
		try {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			commonFilter.setKey(level);
			commonFilter.setKK(frlkeyid);
			commonFilter.setType(roleid);
			FilterValues.getCommonFilters(request, commonFilter);
			List<String[]> roleMapGrid = genTlFnlnrolemapService
					.getEmpTeamList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
			out.println(studentreportgrid);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	private void getDataEmpAll(HttpServletRequest request,
			HttpServletResponse response) {
		String flid = request.getParameter("flid");
		String roleid = request.getParameter("roleid");
		String frlkeyid = request.getParameter("frlkeyid");
		String level = request.getParameter("level");
		String locationId = request.getParameter("location");

		try {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			commonFilter.setKey(level);
			commonFilter.setKK(frlkeyid);
			commonFilter.setType(roleid);
			FilterValues.getCommonFilters(request, commonFilter);
			commonFilter.setFactoryId(locationId);
			List<String[]> roleMapGrid = genTlFnlnrolemapService
					.getEmpAllList(commonFilter);
			PrintWriter out2 = response.getWriter();
			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
			out2.println(studentreportgrid);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	
	private void getColEmpAll(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.print(UIUtils.getPropertyValue(
				"com.akranta.tpm.resources.RoleTeamMapping", "empallgrid"));
		CommonMessage.debugMsg(UIUtils.getPropertyValue(
				"com.akranta.tpm.resources.RoleTeamMapping", "empallgrid"));
	}

	private void getDataRoleAll(HttpServletRequest request,
			HttpServletResponse response) {
		String flid = request.getParameter("flid");
		String level = request.getParameter("level");
		try {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			commonFilter.setKey(level);
			FilterValues.getCommonFilters(request, commonFilter);
			List<String[]> roleMapGrid = genTlFnlnrolemapService
					.getRoleAllList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 2, 0);
			out.println(studentreportgrid);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	private void getColRoleAll(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		CommonMessage.debugMsg("  getColRoleAll");
		PrintWriter out = response.getWriter();
		out.print(UIUtils.getPropertyValue(
				"com.akranta.tpm.resources.RoleTeamMapping", "roleallgrid"));
		CommonMessage.debugMsg(UIUtils.getPropertyValue(
				"com.akranta.tpm.resources.RoleTeamMapping", "roleallgrid"));

	}

	private void loadRoleTeamEmpSingle(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) {
		CommonMessage.debugMsg("roleteamempsingle_input.roleteam");
		String FrpTradeid = "";
		String FrpProcessid = "";
		String FrpSubprocessid = "";
		String FrpSubsubprocessid = "";

		String roleid = request.getParameter("roleid");
		String empmkeyid = request.getParameter("empmkeyid");
		String keyid = request.getParameter("keyid");
		String flid = request.getParameter("flid");

		CommonMessage.debugMsg("roleid  " + roleid);
		CommonMessage.debugMsg("empmkeyid  " + empmkeyid);
		CommonMessage.debugMsg("keyid  " + keyid);

		request.setAttribute("RoleId", roleid);
		request.setAttribute("EmpmKeyId", empmkeyid);
		request.setAttribute("KeyId", keyid);

		List<String[]> roleMapGrid = null;
		CommonFilter commonFilter = new CommonFilter();
		try {
			commonFilter.setFlid(flid);
			commonFilter.setType(roleid);
			commonFilter.setKey(empmkeyid);
			FilterValues.getCommonFilters(request, commonFilter);
			roleMapGrid = genTlFnlnrolemapService
					.getRoleTeamEmpGrid(commonFilter);
			CommonMessage.debugMsg("roleMapGrid" + roleMapGrid);
			if (roleMapGrid.size() > 0) {
				for (String[] row : roleMapGrid) {
					FrpTradeid = row[6];
					FrpProcessid = row[8];
					FrpSubprocessid = row[10];
					FrpSubsubprocessid = row[12];
				}
				request.setAttribute("FrpTradeid", FrpTradeid);
				request.setAttribute("FrpProcessid", FrpProcessid);
				request.setAttribute("FrpSubprocessid", FrpSubprocessid);
				request.setAttribute("FrpSubsubprocessid", FrpSubsubprocessid);

				CommonMessage.debugMsg("FrpTradeid  " + FrpTradeid);
				CommonMessage.debugMsg("FrpProcessid  " + FrpProcessid);
				CommonMessage.debugMsg("FrpSubprocessid  " + FrpSubprocessid);
				CommonMessage.debugMsg("FrpSubsubprocessid  "
						+ FrpSubsubprocessid);
			}

		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}

		// dispatchUrl = "/pages/TeamStructure/RoleTeamEmpList.jsp";

	}

	private void loadVal(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		PrintWriter out = response.getWriter();
		String parentNumber = null;
		String parentId = null;
		String elementType = null;

		parentNumber = request.getParameter("elementId");
		parentId = request.getParameter("parentId");
		elementType = request.getParameter("elementType");

		CommonMessage.debugMsg("parentNumber" + parentNumber);
		CommonMessage.debugMsg("parentId" + parentId);
		CommonMessage.debugMsg("elementType" + elementType);

		parentNumber = parentNumber.equals("0") ? "0" : parentNumber;
		CommonMessage.debugMsg("ID : " + request.getParameter("id"));
		response.setContentType("text/html;charset=UTF-8");
		try {
			JSONArray jSONArray = new JSONArray();
			if (request.getParameter("id").equals("0")) {
				JSONObject jSONObject = new JSONObject();
				JSONObject data = new JSONObject();
				JSONObject jsonAttr = new JSONObject();
				JSONObject metadata = new JSONObject();
				jsonAttr.put("id", "FL001");
				jsonAttr.put("originalId", "1");
				jsonAttr.put("flid", "1");
				jsonAttr.put("elementId", "1");
				jsonAttr.put("parentId", "1");
				jsonAttr.put("elementType", "FL");
				jsonAttr.put("location", "LCN");
				jsonAttr.put("displayCode", "Functional Location");
				jsonAttr.put("imgUrl", getImageUrl("FL"));
				jsonAttr.put("href", "#");
				data.put("title", "Functional Location");
				data.put("icon", "");
				jSONObject.put("data", data);
				jSONObject.put("attr", jsonAttr);

				if (request.getParameter("search_str") != null)
					jSONObject.put("state", "open");
				else
					jSONObject.put("state", "closed");
				metadata.put("id", "1");
				jSONObject.put("metadata", metadata);
				jSONObject.put("icon", getIconImage("FL"));
				jsonAttr = null;
				jSONObject.put("children", "[{}]");
				jSONArray.put(jSONObject);
				jSONObject = null;
			} else {
				CommonMessage.debugMsg("parentNumber" + parentNumber);
				CommonMessage.debugMsg("parentId" + parentId);
				CommonMessage.debugMsg("elementType" + elementType);
				if (!CommonFunctions.isValidKeyId(parentId)) {
					parentId = parentNumber;
					CommonMessage.debugMsg("IN parentId" + parentId);
				}
				FunctionalLocn functionalLocn = new FunctionalLocn();
				functionalLocn.setParentNumber(parentNumber);
				functionalLocn.setParentId(parentId);
				functionalLocn.setElementType(elementType);
				List<FunctionalLocn> locnList = genTlFnlnrolemapService
						.getAllfnLocation(functionalLocn);
				for (int i = 0; i < locnList.size(); i++) {
					JSONObject jSONObject = new JSONObject();
					JSONObject data = new JSONObject();
					JSONObject jsonAttr = new JSONObject();
					JSONObject metadata = new JSONObject();
					// CommonMessage.debugMsg(locnList.get(i).getOriginalId());
					// CommonMessage.debugMsg(locnList.get(i).getElementId());
					CommonMessage.debugMsg("ParentId"
							+ locnList.get(i).getParentId());
					// CommonMessage.debugMsg(locnList.get(i).getElementType());
					jsonAttr.put("id",
							locnList.get(i).getOriginalId().replace("/", "_"));
					jsonAttr.put("originalId", locnList.get(i).getOriginalId());
					jsonAttr.put("flid", locnList.get(i).getTempParentId());
					jsonAttr.put("elementId", locnList.get(i).getElementId());
					jsonAttr.put("parentId", locnList.get(i).getParentId());
					jsonAttr.put("elementType", locnList.get(i)
							.getElementType());
					jsonAttr.put("location", locnList.get(i).getCellOrder());
					jsonAttr.put("displayCode", locnList.get(i)
							.getDisplayCode());
					jsonAttr.put("imgUrl", getImageUrl(locnList.get(i)
							.getElementType()));
					jsonAttr.put(
							"title",
							UIUtils.getTitle(locnList.get(i).getOriginalId()
									.substring(0, 3)));
					jsonAttr.put("href", "#");
					data.put("title", locnList.get(i).getDisplayCode());
					// data.put("attr", jsonAttr);
					data.put("icon", "");
					jSONObject.put("data", data);
					jSONObject.put("attr", jsonAttr);
					if (request.getParameter("search_str") != null) {
						jSONObject.put("state", "open");
					} else
						jSONObject.put("state", "closed");

					metadata.put("id", i);
					jSONObject.put("metadata", metadata);
					jSONObject.put("icon", getIconImage(locnList.get(i)
							.getElementType()));
					// jSONObject.put("imgUrl",getImageUrl(locnList.get(i).getElementType()));
					// jSONObject.put("icon","../images/fav.png");
					jsonAttr = null;
					jSONObject.put("children", "[{}]");
					jSONArray.put(jSONObject);
					jSONObject = null;
				}
			}
			// CommonMessage.debugMsg(jSONArray);
			out.print(jSONArray);
			jSONArray = null;
		} catch (Exception e) {
			// CommonMessage.debugMsg(e);
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	private void getColRoleMapping(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		PrintWriter out = response.getWriter();
		CommonMessage.debugMsg(" getColRoleMapping getCol");
		String flid = request.getParameter("flid");
		JSONObject jsonObject = new JSONObject();
		List<String[]> roleMapGrid = null;
		CommonFilter commonFilter = new CommonFilter();
		try {
			commonFilter.setFlid(flid);
			FilterValues.getCommonFilters(request, commonFilter);
			roleMapGrid = genTlFnlnrolemapService
					.getRoleMappingMainGrid(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}

		JSONObject roleMapGridData = UIUtils.convertToJqGridTableObject(
				roleMapGrid, request, 0, 0, commonFilter.getTotalRecordCnt());
		// CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		GridColModel gridColModel = new GridColModel();
		gridColModel.setHeaderNum(1);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);
		String[] colHeaderHead = roleMapGrid.get(0);
		String[] colHeader = roleMapGrid.get(1);

		List<String[]> headers = new ArrayList<String[]>();
		headers.add(colHeader);

		jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
				jqGridTableModel, gridColModel);
		jsonObject.put("data", roleMapGridData);
		jsonObject.set("tableWidth", "105%%");
		jsonObject.set("tableHeight", "68%%");
		httpSession.removeAttribute("roleMapColModel");
		httpSession.setAttribute("roleMapColModel", jsonObject);
		CommonMessage.debugMsg("jsonObject " + jsonObject);
		out.println(jsonObject);
	}

	private void getDataRoleMapping(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) {
		String flid = request.getParameter("flid");

		try {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			FilterValues.getCommonFilters(request, commonFilter);
			List<String[]> roleMapGrid = genTlFnlnrolemapService
					.getRoleMappingMainGrid(commonFilter);
			PrintWriter out2 = response.getWriter();
			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 2, 0);
			out2.println(studentreportgrid);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	private void getColRoleTeamEmp(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		PrintWriter out = response.getWriter();
		CommonMessage.debugMsg(" form getCol");
		String form = request.getParameter("form");
		String flid = request.getParameter("flid");
		String roleid = request.getParameter("roleid");
		CommonMessage.debugMsg(" form getCol::::: " + form);
		JSONObject jsonObject = new JSONObject();
		List<String[]> roleMapGrid = null;
		CommonFilter commonFilter = new CommonFilter();
		try {
			commonFilter.setFlid(flid);
			commonFilter.setType(roleid);
			FilterValues.getCommonFilters(request, commonFilter);
			roleMapGrid = genTlFnlnrolemapService
					.getRoleTeamEmpGrid(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}

		JSONObject roleMapGridData = UIUtils.convertToJqGridTableObject(
				roleMapGrid, request, 0, 0, commonFilter.getTotalRecordCnt());
		// CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		GridColModel gridColModel = new GridColModel();
		gridColModel.setHeaderNum(1);
		gridColModel.setFormatter("formatterChkRoleEmp");
		gridColModel.setFormattorFromCol("2");
		gridColModel.setFormattorToCol("2");

		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);
		String[] formatterval = { "cmbTrade#8", "cmbProcess#10",
				"cmbSubProc#12", "cmbSubSubProc#14" };
		jqGridTableModel.setFormatterIndex(formatterval);

		String[] colHeaderHead = roleMapGrid.get(0);
		String[] colHeader = roleMapGrid.get(1);

		List<String[]> headers = new ArrayList<String[]>();
		headers.add(colHeader);

		jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
				jqGridTableModel, gridColModel);
		jsonObject.put("data", roleMapGridData);
		jsonObject.set("tableWidth", "100%%");
		jsonObject.set("tableHeight", "48%%");
		httpSession.removeAttribute("roleteamempColModel");
		httpSession.setAttribute("roleteamempColModel", jsonObject);
		CommonMessage.debugMsg("jsonObject " + jsonObject);
		out.println(jsonObject);

		/*
		 * String colModel =
		 * UIUtils.getPropertyValue("com.akranta.tpm.resources.RoleTeamMapping",
		 * "colModelRoleMappingGrid");
		 * httpSession.removeAttribute("colModelRoleMapping");
		 * httpSession.setAttribute("colModelRoleMapping",colModel);
		 * out.println(
		 * UIUtils.getPropertyValue("com.akranta.tpm.resources.RoleTeamMapping",
		 * "colModelRoleMappingGrid"));
		 */
	}

	private void getDataRoleTeamEmp(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) {
		String flid = request.getParameter("flid");
		String roleid = request.getParameter("roleid");
		try {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			commonFilter.setType(roleid);
			FilterValues.getCommonFilters(request, commonFilter);
			List<String[]> roleMapGrid = genTlFnlnrolemapService
					.getRoleTeamEmpGrid(commonFilter);
			PrintWriter out2 = response.getWriter();
			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 2, 0);
			out2.println(studentreportgrid);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	private void getColRoleTeamMainGrid(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		PrintWriter out = response.getWriter();

		String flid = request.getParameter("flid");
		JSONObject jsonObject = new JSONObject();
		List<String[]> roleMapGrid = null;
		CommonFilter commonFilter = new CommonFilter();
		try {
			commonFilter.setFlid(flid);
			FilterValues.getCommonFilters(request, commonFilter);
			roleMapGrid = genTlFnlnrolemapService
					.getRoleTeamMainGrid(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}

		JSONObject roleMapGridData = UIUtils.convertToJqGridTableObject(
				roleMapGrid, request, 0, 0, commonFilter.getTotalRecordCnt());
		// CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		GridColModel gridColModel = new GridColModel();
		gridColModel.setHeaderNum(1);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);

		String[] colHeaderHead = roleMapGrid.get(0);
		String[] colHeader = roleMapGrid.get(1);

		List<String[]> headers = new ArrayList<String[]>();
		headers.add(colHeader);

		jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
				jqGridTableModel, gridColModel);
		jsonObject.put("data", roleMapGridData);
		jsonObject.set("tableWidth", "105%%");
		jsonObject.set("tableHeight", "68%%");
		httpSession.removeAttribute("roleMapColModel");
		httpSession.setAttribute("roleMapColModel", jsonObject);
		CommonMessage.debugMsg("jsonObject " + jsonObject);
		out.println(jsonObject);
	}

	private void getDataRoleTeamMainGrid(HttpServletRequest request,
			HttpServletResponse response) {
		String flid = request.getParameter("flid");
		try {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			FilterValues.getCommonFilters(request, commonFilter);
			List<String[]> roleMapGrid = genTlFnlnrolemapService
					.getRoleTeamAllMainGrid(commonFilter);
			PrintWriter out2 = response.getWriter();
			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 2, 0);
			out2.println(studentreportgrid);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	private void getColRoleTeamAllGrid(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		PrintWriter out = response.getWriter();

		List<String[]> roleMapGrid = null;
		JSONObject jsonObject = new JSONObject();
		CommonFilter commonFilter = new CommonFilter();
		String flid = request.getParameter("flid");
		String level = request.getParameter("level");
		try {
			commonFilter.setFlid(flid);
			commonFilter.setKey(level);
			FilterValues.getCommonFilters(request, commonFilter);
			roleMapGrid = genTlFnlnrolemapService
					.getRoleTeamAllMainGrid(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}

		JSONObject roleMapGridData = UIUtils.convertToJqGridTableObject(
				roleMapGrid, request, 0, 0, commonFilter.getTotalRecordCnt());
		// CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		GridColModel gridColModel = new GridColModel();
		gridColModel.setHeaderNum(1);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);

		String[] colHeaderHead = roleMapGrid.get(0);
		String[] colHeader = roleMapGrid.get(1);

		List<String[]> headers = new ArrayList<String[]>();
		headers.add(colHeader);

		jsonObject = UIUtils.getTableModel(headers, colHeaderHead,
				jqGridTableModel, gridColModel);
		jsonObject.put("data", roleMapGridData);
		jsonObject.set("tableWidth", "107%%");
		jsonObject.set("tableHeight", "85%%");
		httpSession.removeAttribute("roleTeamAllColModel");
		httpSession.setAttribute("roleTeamAllColModel", jsonObject);
		CommonMessage.debugMsg("jsonObject " + jsonObject);
		out.println(jsonObject);
	}

	private void getDataRoleTeamAllGrid(HttpServletRequest request,
			HttpServletResponse response) {
		String flid = request.getParameter("flid");
		String level = request.getParameter("level");
		try {
			CommonFilter commonFilter = populateCommonFilter(request,
					"roleTeamAllGridCommonFilter", true);
			commonFilter.setFlid(flid);
			commonFilter.setKey(level);
			FilterValues.getCommonFilters(request, commonFilter);
			List<String[]> roleMapGrid = genTlFnlnrolemapService
					.getRoleTeamAllMainGrid(commonFilter);
			PrintWriter out2 = response.getWriter();
			JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(
					roleMapGrid, request, 2, 0);
			out2.println(studentreportgrid);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	private void getExlRoleTeamAllGrid(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws Exception {
		CommonFilter commonFilter = populateCommonFilter(request,
				"roleTeamAllGridCommonFilter", false);
		commonFilter.setViewClick('Y');
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		String flid = request.getParameter("flid");
		String level = request.getParameter("level");
		commonFilter.setFlid(flid);
		commonFilter.setKey(level);
		JSONObject tblJSONObj = (JSONObject) httpSession
				.getAttribute("roleTeamAllColModel");
		tblJSONObj.put("title", "Role and Team List");
		String format = ExcelUtils.getFormat(request);
		Workbook wb = genTlFnlnrolemapService.getRoleTeamAllExportExcel(
				commonFilter, tblJSONObj, format);
		commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "RoleTeamReport", format);
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession
				.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getRoleViewFilters(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
			 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
			 commonFilter.setToDate(CommonFunctions.getDate());
		 }	 
		  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  }

		  CommonMessage.debugMsg("commonfileter.getTotla=="+commonFilter.getTotalRecordCnt());
		  
		return commonFilter;
	}
	private CommonFilter populateCommonFilterForTransactionSummary(HttpServletRequest request,
			String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession
				.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getRoleViewFilters(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		/*
		 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
			 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
			 commonFilter.setToDate(CommonFunctions.getDate());
		 }	 
		  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  }
*/
		  CommonMessage.debugMsg("commonfileter.getTotla=="+commonFilter.getTotalRecordCnt());
		  
		return commonFilter;
	}
	
}
