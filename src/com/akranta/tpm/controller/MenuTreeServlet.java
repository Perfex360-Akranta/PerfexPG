package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MenuTree;
import com.akranta.tpm.service.MenuTreeServices;
import com.akranta.tpm.service.impl.MenuTreeServicesImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

//@WebServlet(name = "menuTreeServlet", urlPatterns = {"*.menuTree"})
public class MenuTreeServlet extends HttpServlet {

	/**
	 * 
	 */
	private MenuTreeServices menuTreeServices;

	public MenuTreeServlet() throws Exception {
		super();

	}

	private static final long serialVersionUID = -8070953412956046800L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = UIUtils.getActionPart(request);
		ComboFilter comboFilter = new ComboFilter();
		try {
			menuTreeServices = (MenuTreeServicesImpl) UIUtils.getServiceObject(
					request, "MenuTreeServicesImpl");
		} catch (ServiceObjectCreationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		if (action.equals("load.menuTree")) {
			String parentNumber = request.getParameter("id");
			parentNumber = parentNumber.equals("0") ? "0" : parentNumber;

			response.setContentType("text/html;charset=UTF-8");
			CommonMessage.debugMsg(" menuList.size()  menuList.size() ");

			PrintWriter out = response.getWriter();
			try {

				MenuTree menuTree = new MenuTree();

				menuTree.setParentNumber(parentNumber);
				AdmTlUsermst loginUser = UIUtils.getLoginUser(request);
				List<MenuTree> menuList = menuTreeServices.getAllMneus(	menuTree, loginUser.getUsrm_keyid().trim());
				// CommonMessage.debugMsg(menuList.size());
				JSONArray jSONArray = new JSONArray();
				// JSONObject jsonAttr = new JSONObject();
				// JSONObject jSONObject = new JSONObject();
CommonMessage.debugMsg(" menuList.size()  menuList.size() "+menuList.size());
				for (int i = 0; i < menuList.size(); i++) {

					// if(menuList.get(i).getMenuLevel().equals("1"))
					// {
					JSONObject jSONObject = new JSONObject();

					jSONObject.put("data", menuList.get(i).getMenuCaption());
					jSONObject.put("state", "close");

					JSONObject jsonAttr = new JSONObject();
					jsonAttr.put("reportFileName", menuList.get(i)
							.getReportFileName());
					jsonAttr.put("id", menuList.get(i).getMenuNumber());
					jsonAttr.put("menuName", menuList.get(i).getMenuName());
					jsonAttr.put("menuCaption", menuList.get(i)
							.getMenuCaption());
					jsonAttr.put("isParent", menuList.get(i).isParent());
					jsonAttr.put("formName", menuList.get(i).getFormName());
					jsonAttr.put("isMaster", menuList.get(i).isMaster());
					jsonAttr.put("relatedFilter",
							menuList.get(i).getRelatedFilter().replace("{", "")
									.replace("}", ""));
					jsonAttr.put("isFilterNeed", menuList.get(i)
							.getFilterNeed());
				
					jsonAttr.put("href", menuList.get(i).getMenuName());
					jSONObject.put("attr", jsonAttr);
					jsonAttr = null;
					if (menuList.get(i).isParent())
						jSONObject.put("children", "[{}]");

					jSONArray.put(jSONObject);
					jSONObject = null;
				}
				// CommonMessage.debugMsg(jSONArray);
				CommonMessage.debugMsg("jSONArrayjSONArrayjSONArrayjSONArrayjSONArray "+jSONArray);
				out.print(jSONArray);
				jSONArray = null;

			} catch (Exception e) {
				CommonMessage.debugMsg(e);
			} finally {
				out.close();
			}
		} else if (action.equals("QlinkMenus.menuTree")) {

			MenuTree menuTree = new MenuTree();

			// menuTree.setParentNumber(parentNumber);
			AdmTlUsermst loginUser = UIUtils.getLoginUser(request);
			try {
				List<String[]> menuQlinkList = menuTreeServices
						.getAllQlinkList(menuTree, loginUser.getUsrm_keyid());
				// CommonMessage.debugMsg("menu list   :"+menuQlinkList.size());

				// CommonMessage.debugMsg(menuList.size());
				JSONArray jSONArray = new JSONArray();
				// JSONArray menuData = new JSONArray();
				JSONObject menuD = new JSONObject();

				// for(int i=0;i<menuList.size();i++){
				jSONArray = jSONArray.fromCollection(menuQlinkList);
				// }
				menuD.put("menuData", jSONArray);
				// JSONObject jsonAttr = new JSONObject();
				// JSONObject jSONObject = new JSONObject();
				PrintWriter out = response.getWriter();
				/*
				 * Commented For Demo purpose for(int i=0;
				 * i<menuQlinkList.size(); i++){ JSONObject jSONObject = new
				 * JSONObject();
				 * 
				 * jSONObject.put("data",menuQlinkList.get(i).getMenuCaption());
				 * jSONObject.put("state","close");
				 * 
				 * JSONObject jsonAttr = new JSONObject();
				 * jsonAttr.put("tablename", menuQlinkList.get(i)[0]);
				 * jsonAttr.put("loadforArgument", menuQlinkList.get(i)[1]);
				 * 
				 * jsonAttr.put("masterSql",menuQlinkList.get(i)[2]);
				 * jsonAttr.put("masterIntegrateSql",menuQlinkList.get(i)[3]);
				 * jsonAttr.put("similarColumn",menuQlinkList.get(i)[4]);
				 * jsonAttr.put("ismaster",menuQlinkList.get(i)[5]);
				 * jSONObject.put("attr", jsonAttr);
				 * 
				 * jSONArray.put(jsonAttr); // jSONObject=null; }
				 */
				// CommonMessage.debugMsg(jSONArray);
				out.print(jSONArray);
				jSONArray = null;

			} catch (Exception e) {
				CommonMessage.debugMsg(e);
			}

		} else if (action.equals("search.menuTree")) {

			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String prevSearchstr = UIUtils.getCookieValue(request,
					"menuSearchStr");
			String currentSearchStr = request.getParameter("search_str");

			String countStr = "0";
			List<String[]> searchList = null;
			response.setContentType("text/html");
			try {
				if (currentSearchStr.equals(prevSearchstr)) {
					countStr = UIUtils.getCookieValue(request,"menuSearchStr_cnt");

					searchList = (List<String[]>) httpSession.getAttribute("menuSearchList");

				}
				if (searchList == null) {
					if (currentSearchStr.indexOf("id=") < 0)
						searchList = menuTreeServices.getChildPath(
								currentSearchStr, user.getUsrm_keyid());
					else {
						String[] searchS = currentSearchStr.split("=");

						searchList = menuTreeServices
								.getMenuChildPathByMenuNumber(searchS[1],
										user.getUsrm_keyid());
					}
					httpSession.setAttribute("menuSearchList", searchList);
				}
				int searchCnt = Integer.parseInt(countStr);
				JSONArray jSONArray = null;
				//CommonMessage.debugMsg("searchCnt:"+searchCnt);
				//CommonMessage.debugMsg("searchList.size():"+searchList.size());
				if (searchCnt < searchList.size()) {
					String parentId = searchList.get(searchCnt)[0];
					parentId = "#node_1" + parentId;
					parentId = parentId.replaceAll("/", "-#");

					String[] searchNode = parentId.split("-");

					jSONArray = JSONArray.fromArray(searchNode);
				} else {
					searchCnt = -1;
				}
				Cookie searchStrCookie = new Cookie("menuSearchStr",
						currentSearchStr);
				searchStrCookie.setHttpOnly(true); //madhan
				searchStrCookie.setPath("/");//madhan
				response.addCookie(searchStrCookie);
				Cookie searchCntCookie = new Cookie("menuSearchStr_cnt",
						(searchCnt + 1) + "");
				searchCntCookie.setHttpOnly(true); //madhan
				searchCntCookie.setPath("/");//madhan
				response.addCookie(searchCntCookie);
				out.println(jSONArray);
				out.flush();
				out.close();

			} catch (Exception e) {

			}
		} else if (action.equals("menu_rights.menuTree")) {
			String role = request.getParameter("roleId");
			String openFrom = request.getParameter("openFrom");
			if (UIUtils.isValidKeyId(role))
				request.setAttribute("roleKey", role);
			if (UIUtils.isValidKeyId(openFrom))
				request.setAttribute("openFrom", openFrom);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/menurights.jsp");
			rd.forward(request, response);
		} else if (action.equals("load_menurights.menuTree")) {
			String parentNumber = request.getParameter("id");
			String role = request.getParameter("role");
			CommonMessage.debugMsg("Role : " + role);
			if (!UIUtils.isValidKeyId(parentNumber))
				parentNumber = "0";
			else {
				if (parentNumber.indexOf("_") > 0)
					parentNumber = parentNumber.substring(parentNumber
							.indexOf("_") + 1);
			}
			parentNumber = parentNumber.equals("0") ? "0" : parentNumber;

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			try {
				MenuTree menuTree = new MenuTree();
				menuTree.setParentNumber(parentNumber);
				AdmTlUsermst loginUser = UIUtils.getLoginUser(request);
				List<MenuTree> menuList = menuTreeServices.getAllMneus(
						menuTree, loginUser.getUsrm_keyid());
				// List <MenuTree> menuList =
				// menuTreeServices.getUserRoleMenus(menuTree,loginUser.getUsrm_keyid());
				JSONArray jSONArray = new JSONArray();
				for (int i = 0; i < menuList.size(); i++) {
					JSONObject jSONObject = new JSONObject();
					JSONObject jsonAttr = new JSONObject();
					if (UIUtils.isValidKeyId(role)) {
						String checkMenu = menuTreeServices.checkMenuRoleExist(
								role, menuList.get(i).getMenuNumber());
						CommonMessage.debugMsg("checkMenu : " + checkMenu
								+ "->" + menuList.get(i).getMenuNumber());
						if (Integer.parseInt(checkMenu) > 0) {
							jsonAttr.put("class", "jstree-checked");
							jsonAttr.put("elementType", "assigned");
						}
						/*
						 * else { jsonAttr.put("elementType","Assigned"); }
						 */
					}

					jSONObject.put("data", menuList.get(i).getMenuCaption());
					jSONObject.put("state", "close");

					jsonAttr.put("id", "Menu_"
							+ menuList.get(i).getMenuNumber());
					jsonAttr.put("parentId", menuList.get(i).getParentNumber());
					// jsonAttr.put("elementType",
					// toolpickupList.get(i).getElementtype());
					// jsonAttr.put("displayCode",
					// toolpickupList.get(i).getDisplaycode());
					jsonAttr.put("menuName", menuList.get(i).getMenuName());
					jsonAttr.put("menuNo", menuList.get(i).getMenuNumber());
					jsonAttr.put("menuCaption", menuList.get(i)
							.getMenuCaption());
					jsonAttr.put("isParent", menuList.get(i).isParent());

					// jsonAttr.put("formName",menuList.get(i).getFormName());
					// jsonAttr.put("isMaster",menuList.get(i).isMaster());
					// jsonAttr.put("relatedFilter",menuList.get(i).getRelatedFilter().replace("{","").replace("}",""));
					// jsonAttr.put("isFilterNeed",menuList.get(i).getFilterNeed()
					// );
					jSONObject.put("attr", jsonAttr);
					jsonAttr = null;
					// if( menuList.get(i).isParent())
					jSONObject.put("children", "[{}]");

					jSONArray.put(jSONObject);
					jSONObject = null;
				}

				out.print(jSONArray);
				jSONArray = null;

			} catch (Exception e) {
				CommonMessage.debugMsg(e);
			} finally {
				out.close();
			}
		} else if (action.equals("save_rolerights.menuTree")) {
			saveMenuRoleRights(request, response);
		} else if (action.equals("role_combo.menuTree")) {
			try {

				List<ComboBox> category = menuTreeServices.getComboRole("");
				UIUtils.writeComboBox(response, category, comboFilter);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		else if (action.equals("menu_Viewsdata.menuTree")){
			UIUtils.forwardRequest(request, response,"/pages/menurightsview.jsp");
		} 
		else if (action.equals("menuViewsdata_getCol.menuTree")){
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MenuContent", "menuview");
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromString(tableModel);
			httpSession.removeAttribute("menucalmodel");
			httpSession.setAttribute("menucalmodel", jsonObject);
			out.println(jsonObject);
		}
		
		else if (action.equals("menuViewsdata_getData.menuTree")){

			try {
				 PrintWriter out=null;
				 HttpSession httpSession = request.getSession(false);
				 CommonMessage.debugMsg("The Filter getdata");
				 CommonFilter commonFilter = populateCommonFilter(request,"Menurightsview", true);
			     GridParams gridparams=new GridParams();
				 FilterValues.populateGridParams(request, gridparams);
				 String flid = request.getParameter("flid");
				 String actionKeyId = request.getParameter("roleId");
				 String VisualKeyId = request.getParameter("userId");
				if (UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if (UIUtils.isValidKeyId(actionKeyId))
					commonFilter.setActionKeyId(actionKeyId);
				if (UIUtils.isValidKeyId(VisualKeyId))
					commonFilter.setVisualKeyId(VisualKeyId);
				List<String[]> addmenu = menuTreeServices.addmenuview1(commonFilter,gridparams);
				CommonMessage.debugMsg("The grid data" + addmenu.size());
				net.sf.json.JSONObject  menuData = UIUtils.convertToJqGridTableObject(addmenu, request,0,1,gridparams.getTotalRecordCnt());
				out = response.getWriter();
				out.println(menuData);
				httpSession.removeAttribute("Menurightsview");
				httpSession.setAttribute("Menurightsview", commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		else if (action.equals("menuViewsdata_getExcel.menuTree")){
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"Menurightsview", false);
				net.sf.json.JSONObject  tblJSONObj =(net.sf.json.JSONObject)httpSession.getAttribute("menucalmodel");
				tblJSONObj.put("title", "Menu Rights Report");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = menuTreeServices.getmenuRightsExcel(commonFilter,tblJSONObj, format);
				ExcelUtils.writeToResponse(response, wb, "Menu Rights Report",format);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	

		else if (action.equals("functionalLoc.menuTree")){
			CommonMessage.debugMsg("function location ");
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setSection("cmbSmsgSectionid");
			functLocFieldNameBean.setCell("cmbSmsgCellid");
			functLocFieldNameBean.setMachine("cmbSmsgMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbSmsgFlid");
			functLocFieldNameBean.setSbu("cmbSmsgSbu");
			functLocFieldNameBean.setPbu("cmbSmsgPbu");
			functLocFieldNameBean.setLocnMandatory(true);
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			CommonMessage.debugMsg("functionlocation null ");
			FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response,functLocFieldNameBean, formModes);
		}
		
		else if (action.equals("menu_ViewsdataReport.menuTree")){

			UIUtils.forwardRequest(request, response,"/pages/rolemenureport.jsp");
		}
		else if (action.equals("menuViewsdataReport_getCol.menuTree")) {
			PrintWriter out=response.getWriter();
			HttpSession httpSession = request.getSession(false);
			out = response.getWriter();
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MenuContent","menuviewReport");
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromString(tableModel);
			httpSession.removeAttribute("menucalReportmodel");
			httpSession.setAttribute("menucalReportmodel", jsonObject);
			out.println(jsonObject);
		} 

		 else if (action.equals("menuViewsdataReport_getData.menuTree")){
				try {
					 PrintWriter out=null;
					 HttpSession httpSession=request.getSession(false); 
					 CommonFilter commonfilter = populateCommonFilter(request,"MenurightsviewReport",true);
					 GridParams gridParams=new GridParams();
					 FilterValues.populateGridParams(request, gridParams);
					/* String flid = request.getParameter("flid");
					 CommonMessage.debugMsg("The flid value"+flid);
					 String roleKeyId = request.getParameter("roleKeyId");
					 CommonMessage.debugMsg("The roleLevel"+roleKeyId);*/
					 String mainkeyid = request.getParameter("menuid");
					CommonMessage.debugMsg("The mainkeyid"+mainkeyid);
					/* if (UIUtils.isValidKeyId(flid))
						commonfilter.setFlid(flid);
					 if(UIUtils.isValidKeyId(roleKeyId))
						commonfilter.setRoleLevel(roleKeyId);*/
				     if(UIUtils.isValidKeyId(mainkeyid)) 
				      commonfilter.setMainkeyid(mainkeyid);
					 List<String[]> addmenuviewReport =menuTreeServices.addmenureport(commonfilter,gridParams);
					 CommonMessage.debugMsg("The Grid Data Report"+ addmenuviewReport.size());
					 net.sf.json.JSONObject menuData = UIUtils.convertToJqGridTableObject(addmenuviewReport, request, 0,1,gridParams.getTotalRecordCnt());
					 out=response.getWriter();
					 out.println(menuData);
					 httpSession.removeAttribute("MenurightsviewReport");
					 httpSession.setAttribute("MenurightsviewReport",commonfilter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		 else if (action.equals("menuViewsdataReport_getExcel.menuTree")){
				try {
					HttpSession httpSession=request.getSession(false);
					CommonFilter commonfilter = populateCommonFilter(request,"MenurightsviewReport",false);
					net.sf.json.JSONObject jsonobj =(net.sf.json.JSONObject)httpSession.getAttribute("menucalReportmodel");
					jsonobj.put("title", "Menu Report");
					String formats = ExcelUtils.getFormat(request);
					Workbook wb = menuTreeServices.getmenurightreportExcel(commonfilter, jsonobj, formats);
					ExcelUtils.writeToResponse(response, wb, "Menu Report",formats);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		     else if(action.equals("menuComboValue.menuTree")){
			 try{
			    ComboFilter combofilter=UIUtils.fillComboFilter(request);
			    List<ComboBox>menuid=menuTreeServices.getmenu(combofilter);
			    UIUtils.writeComboBox(response,menuid,combofilter);			    
			  }
			 catch(Exception e){
				 e.printStackTrace();
			 }
			 }
			
		else {
			// RequestDispatcher rd =
			// request.getRequestDispatcher("/pages/utils/jsTreeTest.jsp");
			// rd.forward(request, response);
		}
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String string, boolean b) {
	       
 		    	 HttpSession httpSession = request.getSession(false);

 		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(string);
 		if( commonFilter != null && ! b ){
 			FilterValues.setPaginationParams(request,commonFilter);
 		}	
 		else{
 			commonFilter =  new CommonFilter();
 			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
 			commonFilter = 	FilterValues.getRoleViewFilters(request, commonFilter);
 			httpSession.removeAttribute(string);
 			httpSession.setAttribute(string, commonFilter);
 		}
 		
 		return commonFilter;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

	private void saveMenuRoleRights(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			AdmTlRoleMenuLink existAdmTlRoleMenuLink = (AdmTlRoleMenuLink) httpSession
					.getAttribute("admTlRoleMenuLink");
			AdmTlRoleMenuLink admTlRoleMenuLink = new AdmTlRoleMenuLink();
			List<AdmTlRoleMenuLink> menuList = new ArrayList<AdmTlRoleMenuLink>();
			String selMenu = request.getParameter("selectedMenus");
			String role = request.getParameter("role");
			if (UIUtils.isValidKeyId(selMenu)) {
				String dateTime = CommonFunctions.dateTimeNow();
				if (selMenu.indexOf(",") > 0) {
					CommonMessage.debugMsg("Menu : " + selMenu);
					String[] selMenuArr = selMenu.split(",");
					CommonMessage.debugMsg("Length Menu : "
							+ selMenuArr.length);
					for (int i = 0; i < selMenuArr.length; i++) {
						AdmTlRoleMenuLink newAdmTlRoleMenuLink = new AdmTlRoleMenuLink();
						String menuNo = selMenuArr[i];
						if (menuNo.indexOf("_") > 0)
							menuNo = menuNo.substring(menuNo.indexOf("_") + 1);
						newAdmTlRoleMenuLink.setArmlMenuid(menuNo);
						newAdmTlRoleMenuLink.setArmlRoleid(role);
						newAdmTlRoleMenuLink.setArmlActive("Y");
						newAdmTlRoleMenuLink.setArmlCreatedby(user
								.getUsrm_ccno());
						newAdmTlRoleMenuLink.setArmlCreatedon(dateTime);
						newAdmTlRoleMenuLink.setArmlModifiedon(dateTime);
						// newAdmTlRoleMenuLink.getAdmRoleMenuLink().add(newAdmTlRoleMenuLink);
						menuList.add(newAdmTlRoleMenuLink);

					}

				} else {
					AdmTlRoleMenuLink newAdmTlRoleMenuLink = new AdmTlRoleMenuLink();
					String menuNo = selMenu;
					if (menuNo.indexOf("_") > 0)
						menuNo = menuNo.substring(menuNo.indexOf("_") + 1);
					newAdmTlRoleMenuLink.setArmlMenuid(menuNo);
					newAdmTlRoleMenuLink.setArmlRoleid(role);
					newAdmTlRoleMenuLink.setArmlActive("Y");
					newAdmTlRoleMenuLink.setArmlCreatedby(user.getUsrm_ccno());
					newAdmTlRoleMenuLink.setArmlCreatedon(dateTime);
					newAdmTlRoleMenuLink.setArmlModifiedon(dateTime);
					// newAdmTlRoleMenuLink.getAdmRoleMenuLink().add(newAdmTlRoleMenuLink);
					menuList.add(newAdmTlRoleMenuLink);
				}
			}

			if (menuList != null)
				admTlRoleMenuLink.setAdmRoleMenuLink(menuList);

			try {
				existAdmTlRoleMenuLink = menuTreeServices.create(
						admTlRoleMenuLink, existAdmTlRoleMenuLink);

				// httpSession.setAttribute(existAdmTlRoleMenuLink.getArmlMenuid(),
				// existAdmTlRoleMenuLink);
				httpSession.setAttribute("admTlRoleMenuLink",
						existAdmTlRoleMenuLink);

				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();

				successData.put("msg", "Role Assigned Successfully");
				// successData.put("keyId",
				// existAdmTlRoleMenuLink.getArmlMenuid());
				returnData.put("formClear", false);
				returnData.put("successData", successData);

				CommonMessage.debugMsg(returnData.toString());
				out.print(returnData.toString());

			} catch (ValidationExceptions e) {
				net.sf.json.JSONObject errMessage = UIUtils
						.validationExceptions(e.toString(),
								"RoleMenuLinkException");
				out.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				net.sf.json.JSONObject errMessage = UIUtils
						.businessValidationExceptions(e.toString(),
								"RoleMenuLinkException");
				out.print(errMessage.toString());

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}

		}
	}
}
