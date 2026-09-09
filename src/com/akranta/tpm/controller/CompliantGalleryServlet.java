package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.QtmTlComplaintgalleryBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.QtmTlComplaintgallery;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.CompliantGalleryService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.CompliantGalleryServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.impl.DashboardServiceImpl;

import com.akranta.tpm.service.api.ComplaintGalleryServiceApi;

/**
 * Servlet implementation class CompliantGalleryServlet
 */

public class CompliantGalleryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DashboardService dashboardService;
	//QtmTlComplaintgalleryservice  qtmTlComplaintgalleryservice ;
	CompliantGalleryService compliantGalleryService;
	ComplaintGalleryServiceApi complaintgalleryserviceapi;
	private CommonFilterService commonFilterService;
	public CompliantGalleryServlet() {
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

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

   dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
		compliantGalleryService = (CompliantGalleryServiceImpl) UIUtils.getServiceObject(request, "CompliantGalleryServiceImpl");
		commonFilterService=(CommonFilterServiceImpl)UIUtils.getServiceObject(request, "CommonFilterServiceImpl");
		
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg(" action " + action);
		
		HttpSession httpSession = request.getSession(false);
		compliantGalleryService.CompliantGalleryServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
		if (action.equals("ComplaintGallery_input.compg")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/CompliantGallerygrid.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);

		}
		else if (action.equals("ComplaintGallery_getCol.compg")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter=populateCommonFilter(request, "ComplaintGalCommonFilter",true);
			List<String[]> complaintGalGrid = null;
			try {
				commonFilter.setIsGetCol("Y");
				complaintGalGrid = compliantGalleryService.getAllCompliant(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			
			String [] colHeader = complaintGalGrid.get(1);			
			String [] colHeaderCond = complaintGalGrid.get(0);
			gridColModel.setHeaderNum(1);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "70%%");
			httpSession.removeAttribute("ComplaintGal");
			httpSession.setAttribute("ComplaintGal", jsonObject);
			out.println(jsonObject);
		}
		else if (action.equals("ComplaintGallery_getData.compg")) {
			try {
				String loginFlid=(String) httpSession.getAttribute("loginFlid");
				CommonFilter commonFilter=populateCommonFilter(request, "ComplaintGalCommonFilter",false);
				if(!UIUtils.isValidKeyId(commonFilter.getFlid()))
					commonFilter.setFlid(loginFlid);
				commonFilter.setIsGetCol("N");
				List<String[]> MachineGrid = compliantGalleryService.getAllCompliant(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request,2, 0,commonFilter.getTotalRecordCnt());
				out.println(machinegrid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (action.equals("ComplaintGalleryView_input.compg")) {
		    try {
		        String stuGrid = request.getParameter("grid");
		        String Keyid = request.getParameter("Keyid");
		        
		        // Get login user details
		        String tmpFromRow = UIUtils.getLoginUser(request).getUsrm_username();
		        String loginflid = CommonFunctions.getLoginFlid(request);
		        String keyId = CommonFunctions.getLoginLevel(request);
		        String format = (String)httpSession.getAttribute("loginElementid");
		        
		        AdmTlUsermst usersdetails = UIUtils.getLoginUser(request);
		        String empId = usersdetails.getUsrm_ccno();
		        
		        CommonMessage.debugMsg("=== DEBUG START ===");
		        CommonMessage.debugMsg("loginflid: " + loginflid);
		        CommonMessage.debugMsg("keyId: " + keyId);
		        CommonMessage.debugMsg("format: " + format);
		        CommonMessage.debugMsg("empId: " + empId);
		        
		        // Get user role details
		        List<String[]> getUserLoginDtl = this.compliantGalleryService.getElementId(
		            loginflid, keyId, format, empId);
		        
		        CommonMessage.debugMsg("Query Result Size: " + (getUserLoginDtl != null ? getUserLoginDtl.size() : "NULL"));
		        
		        if(getUserLoginDtl != null && getUserLoginDtl.size() > 0) {
		            String elementid = ((String[])getUserLoginDtl.get(0))[0];
		            String fnln = ((String[])getUserLoginDtl.get(0))[1];
		            String level = ((String[])getUserLoginDtl.get(0))[2];
		            String rolename = ((String[])getUserLoginDtl.get(0))[3];
		            String rolekeyid = ((String[])getUserLoginDtl.get(0))[4];
		            
		            CommonMessage.debugMsg("Role Name: '" + rolename + "'");
		            CommonMessage.debugMsg("Role KeyId: '" + rolekeyid + "'");
		            CommonMessage.debugMsg("Role Name Length: " + rolename.length());
		            CommonMessage.debugMsg("Expected: 'QM PILLAR MEMBER' Length: 16");
		            CommonMessage.debugMsg("Match: " + "QM PILLAR MEMBER".equals(rolename.trim()));
		            CommonMessage.debugMsg("=== DEBUG END ===");
		            
		            // Set role attributes - DO THIS ALWAYS, even if access denied
		            request.setAttribute("rolekeyid", rolekeyid);
		            request.setAttribute("rolename", rolename);
		            
		            // DON'T block access here in servlet, let JavaScript handle it
		            // This way the page loads and JavaScript can show the error
		            
		        } else {
		            CommonMessage.debugMsg("ERROR: No role data found!");
		            // Set empty values so page can load
		            request.setAttribute("rolekeyid", "");
		            request.setAttribute("rolename", "");
		        }
		        
		        // Continue with rest of the code...
		        String[] imgname = new String[2];
		        QtmTlComplaintgallery qtmTlComplaintgallery = new QtmTlComplaintgallery();
		        
		        if ("true".equals(stuGrid)) {
		            CommonMessage.debugMsg("KEYID = " + Keyid);
		            
		            String fileDir = UIUtils.TPM_TEMPIMG_DIR;
		            String imagepath = UIUtils.getImagePath(request);
		            
		            imgname = compliantGalleryService.getImgName(Keyid, fileDir, imagepath);
		            qtmTlComplaintgallery = compliantGalleryService.getvalues(Keyid);
		            
		            // Format dates
		            //qtmTlComplaintgallery.setCmgaComplaintdate(UIUtils.getActualDateForm(qtmTlComplaintgallery.getCmgaComplaintdate()));
		            String date = qtmTlComplaintgallery.getCmgaComplaintdate();
			 		
	                qtmTlComplaintgallery.setCmgaComplaintdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
		            if(qtmTlComplaintgallery.getCmgaComplaintdate().equals(Constants.pgPassNullDate)) {
		                qtmTlComplaintgallery.setCmgaComplaintdate("");
		                
				 		
		            }
		            
		           // qtmTlComplaintgallery.setCmgaManufacturedate(UIUtils.getActualDateForm(qtmTlComplaintgallery.getCmgaManufacturedate()));
		            String date1 = qtmTlComplaintgallery.getCmgaManufacturedate();
			 		
	                qtmTlComplaintgallery.setCmgaManufacturedate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date1));
		            if(qtmTlComplaintgallery.getCmgaManufacturedate().equals(Constants.pgPassNullDate)) {
		                qtmTlComplaintgallery.setCmgaManufacturedate("");
		                
 
		                
		            }
		            
		            request.setAttribute("imgPath", imgname[1]);
		            request.setAttribute("imgName", imgname[0]);
		        }
		        
		        request.setAttribute("qtmTlComplaintgallery", qtmTlComplaintgallery);
		        
		        // Forward to JSP
		        RequestDispatcher rd = request.getRequestDispatcher("/pages/ComplaintGallery.jsp");
		        rd.forward(request, response);
		        
		    } catch (Exception e) {
		        CommonMessage.debugMsg("ERROR in ComplaintGalleryView_input: " + e.getMessage());
		        e.printStackTrace();
		    }
		}else if(action.equals("functionalLoc.compg"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSectionid");
			functLocFieldNameBean.setCell("cmbCellid");
			functLocFieldNameBean.setMachine("cmbMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbCdapFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setLocnMandatory(true);
			FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}
		else if(action.equals("CustomerComplaintCount_input.compg")){
			RequestDispatcher rd=request.getRequestDispatcher("pages/Reports/CustomerComplaintsCount.jsp");
			rd.forward(request, response);
		}
//		else if(action.equals("CustomerComplaintCount_getCol.compg")){
//	            httpSession = request.getSession(false);
//				PrintWriter out = response.getWriter();
//				String firstClick = request.getParameter("firstClick");
//				CommonFilter commonFilter = null;
//				commonFilter = populateCommonFilter(request,"ComplaintGalCommonFilter",true);
//		                  List<String[]> CustComplaints=compliantGalleryService.getCustCompliantCount(commonFilter);
//
//			                 JSONObject abnData = UIUtils.convertToJqGridTableObject(CustComplaints,request,1,0,commonFilter.getTotalRecordCnt());
//			                 httpSession.setAttribute("ETplanVScompServletdata", abnData);
//			                 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
//							 GridColModel gridColModel = new GridColModel();
//							jqGridTableModel.setRowNumbers(true);
//							jqGridTableModel.setEnableFilter(false)	;
//							jqGridTableModel.setTableButton(true);
//			                gridColModel.setHeaderNum(1);
//							String [] colHeader = CustComplaints.get(2);
//							String [] colHeader1 = CustComplaints.get(3);
//							String [] colHeaderCond = CustComplaints.get(1);
//							
//							List<String[]> headers = new ArrayList<String[]>();
//							
//							headers.add(colHeader);
//							headers.add(colHeader1);
//	
//							JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
//							jsonObject.put("tableWidth", "107%%");
//							jsonObject.put("tableHeight", "66.5%%%%");
//							httpSession.removeAttribute("ComplaintGal");
//							httpSession.setAttribute("ComplaintGal", jsonObject);
//							out.println(jsonObject);
//			
//		}
		
		else if(action.equals("CustomerComplaintCount_getCol.compg")){
            httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			CommonFilter commonFilter = null;
			commonFilter = populateCommonFilter(request,"ComplaintGalCommonFilter",true);
	                  List<String[]> CustComplaints=compliantGalleryService.getCustCompliantCount(commonFilter);

		                 JSONObject abnData = UIUtils.convertToJqGridTableObject(CustComplaints,request,1,0,commonFilter.getTotalRecordCnt());
		                 httpSession.setAttribute("ETplanVScompServletdata", abnData);
		                 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						 GridColModel gridColModel = new GridColModel();
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(false)	;
						jqGridTableModel.setTableButton(true);
		                gridColModel.setHeaderNum(1);
						String [] colHeader = CustComplaints.get(1);//Change here
						String [] colHeader1 = CustComplaints.get(2);//Change here
						String [] colHeaderCond = CustComplaints.get(0);//Change here
						
						List<String[]> headers = new ArrayList<String[]>();
						
						headers.add(colHeader);
						headers.add(colHeader1);

						JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
						jsonObject.put("tableWidth", "107%%");
						jsonObject.put("tableHeight", "66.5%%%%");
						httpSession.removeAttribute("ComplaintGal");
						httpSession.setAttribute("ComplaintGal", jsonObject);
						out.println(jsonObject);
		
	}
		else if(action.equals("CustomerComplaintCount_getData.compg")){
			PrintWriter out = response.getWriter();
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");	
				 CommonMessage.debugMsg("page......"+page);
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"ComplaintGalCommonFilter",false);
				JSONObject jsonObject = new JSONObject();
				List<String[]> CustComplaints=compliantGalleryService.getCustCompliantCount(commonFilter);
	        		 	jsonObject = UIUtils.convertToJqGridTableObject(CustComplaints,request,3,0,commonFilter.getTotalRecordCnt());
	        		 	out.println(jsonObject);
					 commonFilter.setViewClick('Y');	  			 	
		  			 httpSession.removeAttribute("ComplaintGal");
		  			 httpSession.setAttribute("ComplaintGal", commonFilter);

		    }
			catch(Exception e)
			{
			   e.printStackTrace();
			}
		}
		else if(action.equals("CustomerComplaintCount_getExcel.compg")){
			httpSession = request.getSession(false);
			 CommonFilter commonFilter = populateCommonFilter(request,"ComplaintGalCommonFilter",false);
			CommonMessage.debugMsg("gridFilter "+commonFilter.getGridFilter());
			JSONObject colmodel = UIUtils.getXlColModel( request, response);
			colmodel.put("title","Customer Complaints Report");	
            String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
            String format = ExcelUtils.getFormat(request); 
            Workbook wb=null;
            wb=compliantGalleryService.getCustComplintsCountExcel(commonFilter, colmodel, format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "CustomerComplaintsReport", format);
		}
		else if( action.equals("Phenomena_DefectForm.compg"))
		{
			
				CommonMessage.debugMsg(" Inside SErvlet Action 11 :: ");
				ComboFilter comboFilter = new ComboFilter();
				CommonFilter  commonFilter = new CommonFilter();
				comboFilter=UIUtils.fillComboFilter(request);
				CommonMessage.debugMsg(" Inside CommonFunctions 11 :: ");
				String flid = request.getParameter("flid");
			    if (UIUtils.isValidKeyId(flid))
			        commonFilter.setFlid(flid);

			    // read directly from request
			    String sectionId  = request.getParameter("sectionId");
			    String defectMode = request.getParameter("defectMode");

			    // piggyback into condSql - no model setter needed
			    comboFilter.setCondSql("##" + defectMode + "##" + sectionId + "##");
			    
				List<ComboBox>  UpstreamDefect = compliantGalleryService.getFillcombobox(commonFilter,comboFilter);
				UIUtils.writeComboBox(response, UpstreamDefect ,comboFilter);
				
		
			
		}
		else if (action.equals("comboGradeSpec.compg")) {
		    ComboFilter comboFilter = new ComboFilter();
		    CommonFilter commonFilter = new CommonFilter();
		    comboFilter = UIUtils.fillComboFilter(request);

		    String flid      = request.getParameter("flid");
		    String gradeMode = request.getParameter("gradeMode");

		    if (UIUtils.isValidKeyId(flid))
		        commonFilter.setFlid(flid);

		    // Piggyback gradeMode into condSql using ## marker
		    comboFilter.setCondSql("##" + gradeMode + "##");

		    List<ComboBox> gradeSpecList = compliantGalleryService.getGradeSpecComboList(commonFilter, comboFilter);
		    UIUtils.writeComboBox(response, gradeSpecList, comboFilter);
		}
		else if(action.equals("CustComplaintchartcount.compg")){
			processChart(request,response);
		}
		else if(action.equals("ComplaintGalleryView_save.compg")){
			save(request,response);
		}
		else if(action.equals("ComplaintGalleryView_delete.compg")){
			Delete(request,response);
		}
		 else if( action.equals("ComplaintGallery_getExcel.compg") )
			{
				CommonFilter commonFilter=populateCommonFilter(request, "ComplaintGalCommonFilter",false);
				JSONObject colmodel = UIUtils.getXlColModel(request, response);
				colmodel.put("title","Complaint Gallery");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = compliantGalleryService.getCompGalExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "ComplaintGallery", format);
			}
	}
	private void Delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		String imgCustomerName= request.getParameter("imgCustomerName");
		QtmTlComplaintgallery existQtmTlComplaintgallery = (QtmTlComplaintgallery)httpSession.getAttribute("newSession");
		QtmTlComplaintgallery newQtmTlComplaintgallery = new QtmTlComplaintgallery ();
		newQtmTlComplaintgallery=(QtmTlComplaintgallery)UIUtils.setBeanProperties((Object)newQtmTlComplaintgallery,request);
		existQtmTlComplaintgallery = compliantGalleryService.delete(newQtmTlComplaintgallery);
		if(UIUtils.isValidKeyId(imgCustomerName))
			compliantGalleryService.deleteImage(newQtmTlComplaintgallery.getCmgaKeyid(),"CUS","CMG");
				
		JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",true);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}

	/*private void save(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String saveMsg="";
		boolean clearFrm=true;
		if( httpSession != null && user != null)
		{	
			QtmTlComplaintgallery newQtmTlComplaintgallery = new QtmTlComplaintgallery ();
			QtmTlComplaintgalleryBean newQtmTlComplaintgalleryBean = new QtmTlComplaintgalleryBean();//3
			QtmTlComplaintgallery existQtmTlComplaintgallery =  new QtmTlComplaintgallery();
			
			String imgCustomerName= request.getParameter("imgCustomerName");
			String openFileMgr= request.getParameter("openFileMgr");
			CommonMessage.debugMsg(" CHECKING NOW ::::openFileMgr:::::="+openFileMgr);
			String imagePath = UIUtils.getImagePath(request);
			CommonMessage.debugMsg("imagePath:::::="+imagePath);
			List <GenTlAllmoduleimgfile> imgfileList =new ArrayList<GenTlAllmoduleimgfile>();
			GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
			if( imgCustomerName != null){
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(imgCustomerName);
				genTlAllmoduleimgfile.setImflImagetype("CUS");
				genTlAllmoduleimgfile.setImflRefdoctype("CMG");
				imgfileList.add(genTlAllmoduleimgfile);
			}
			
			try{	
				newQtmTlComplaintgallery=(QtmTlComplaintgallery)UIUtils.setBeanProperties((Object)newQtmTlComplaintgallery,request);
				if( newQtmTlComplaintgallery.getCmgaKeyid() == null )
				{
					existQtmTlComplaintgallery = compliantGalleryService.create(newQtmTlComplaintgallery,existQtmTlComplaintgallery,newQtmTlComplaintgalleryBean);
					CommonMessage.debugMsg("existQtmTlComplaintgallery.getCmgaKeyid()  "+existQtmTlComplaintgallery.getCmgaKeyid());
					if(UIUtils.isValidKeyId(imgCustomerName))
						commonFilterService.saveImg(imgfileList, existQtmTlComplaintgallery.getCmgaKeyid(),genTlAllmoduleimgfile.getImflRefdoctype() );
					saveMsg = "Data Saved Successfully";
				}	
				else
				{
					
					if(!UIUtils.isValidKeyId(imgCustomerName))
						compliantGalleryService.deleteImage(newQtmTlComplaintgallery.getCmgaKeyid(),"CUS","CMG");
					existQtmTlComplaintgallery = compliantGalleryService.update(newQtmTlComplaintgallery,existQtmTlComplaintgallery,newQtmTlComplaintgalleryBean);
					if(UIUtils.isValidKeyId(imgCustomerName))
						commonFilterService.saveImg(imgfileList, newQtmTlComplaintgallery.getCmgaKeyid(),genTlAllmoduleimgfile.getImflRefdoctype() );
					saveMsg = "Data Updated Successfully";
					clearFrm=false;
				}				
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				successData.put("msg", saveMsg);
				if(UIUtils.isValidKeyId(openFileMgr))
				   returnData.put("formClear",clearFrm);
				else
					//returnData.put("formClear",clearFrm);
				
				returnData.put("successData",successData);
				returnData.put("openFileMgr",openFileMgr);
				returnData.put("keyid",existQtmTlComplaintgallery.getCmgaKeyid());
				
				out.print(returnData.toString());
			}catch (ValidationExceptions e) {
				CommonMessage.debugMsg("Inside ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ComplaintGalleryValidations");
				e.printStackTrace();
				out.print(errMessage.toString());
	    	}
			catch(Exception e)
			{
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	} */
	private void save(HttpServletRequest request,HttpServletResponse response) throws IOException  /*Changes*/
	{
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String saveMsg="";
		boolean clearFrm=true;
		String mode=null;
		if( httpSession != null && user != null)
		{	
			QtmTlComplaintgallery newQtmTlComplaintgallery = new QtmTlComplaintgallery ();
			QtmTlComplaintgalleryBean newQtmTlComplaintgalleryBean = new QtmTlComplaintgalleryBean();//3
			QtmTlComplaintgallery existQtmTlComplaintgallery =  new QtmTlComplaintgallery();
			
			String imgCustomerName= request.getParameter("imgCustomerName");
			String openFileMgr= request.getParameter("openFileMgr");
			CommonMessage.debugMsg(" CHECKING NOW ::::openFileMgr:::::="+openFileMgr);
			String imagePath = UIUtils.getImagePath(request);
			CommonMessage.debugMsg("imagePath:::::="+imagePath);
			List <GenTlAllmoduleimgfile> imgfileList =new ArrayList<GenTlAllmoduleimgfile>();
			GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
			if( imgCustomerName != null){
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(imgCustomerName);
				genTlAllmoduleimgfile.setImflImagetype("CMG");
				genTlAllmoduleimgfile.setImflRefdoctype("CUS");
				imgfileList.add(genTlAllmoduleimgfile);
			}
			
			try{	
				newQtmTlComplaintgallery=(QtmTlComplaintgallery)UIUtils.setBeanProperties((Object)newQtmTlComplaintgallery,request);
				if( newQtmTlComplaintgallery.getCmgaKeyid() == null )
				{
					existQtmTlComplaintgallery = compliantGalleryService.create(newQtmTlComplaintgallery,existQtmTlComplaintgallery,newQtmTlComplaintgalleryBean);
					CommonMessage.debugMsg("existQtmTlComplaintgallery.getCmgaKeyid()  "+existQtmTlComplaintgallery.getCmgaKeyid());
					if(UIUtils.isValidKeyId(imgCustomerName))
						commonFilterService.saveImg(imgfileList, existQtmTlComplaintgallery.getCmgaKeyid(),genTlAllmoduleimgfile.getImflRefdoctype() );
					saveMsg = "Data Saved Successfully";
				}	
				else
				{
					
					if(!UIUtils.isValidKeyId(imgCustomerName))
						compliantGalleryService.deleteImage(newQtmTlComplaintgallery.getCmgaKeyid(),"CUS","CMG");
					existQtmTlComplaintgallery = compliantGalleryService.update(newQtmTlComplaintgallery,existQtmTlComplaintgallery,newQtmTlComplaintgalleryBean);
					if(UIUtils.isValidKeyId(imgCustomerName))
						commonFilterService.saveImg(imgfileList, newQtmTlComplaintgallery.getCmgaKeyid(),genTlAllmoduleimgfile.getImflRefdoctype() );
					saveMsg = "Data Updated Successfully";
					mode="Modify";
					clearFrm=false;
				}				
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				successData.put("msg", saveMsg);
				if(mode!=null)
				{
					successData.put("mode", mode);	
				}
				if(UIUtils.isValidKeyId(openFileMgr))
				   returnData.put("formClear",clearFrm);
				else
					//returnData.put("formClear",clearFrm);
				
				returnData.put("successData",successData);
				returnData.put("openFileMgr",openFileMgr);
				returnData.put("keyid",existQtmTlComplaintgallery.getCmgaKeyid());
				
				out.print(returnData.toString());
			}catch (ValidationExceptions e) {
				CommonMessage.debugMsg("Inside ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ComplaintGalleryValidations");
				e.printStackTrace();
				out.print(errMessage.toString());
	    	}
			catch(Exception e)
			{
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("ComplaintGalCommonFilter");
		
		String forDashboard = request.getParameter("dashboard");
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("ComplaintGalCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);		
		}
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		chartCommonFilter.setRowTotal('Y');
		
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  }  
		
		
		List<String[]> CustComplaints=compliantGalleryService.getCustCompliantCount(commonFilter);
		JSONObject chartObj = null;
		if(CustComplaints != null && CustComplaints.size() > 0)
		{
			
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processBarChart(lcnname,CustComplaints,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	private JSONObject processBarChart(String tn,List<String[]> CustComplaintCountlist,CommonFilter commonFilter){

		if( CustComplaintCountlist == null || CustComplaintCountlist.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String[] month =  CustComplaintCountlist.get(2);
		String[] data =  CustComplaintCountlist.get(CustComplaintCountlist.size()-1);
		String prevMonth = null;
		
		//String subTitle = data[0];
		  String subTitle="";
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
	
		StringBuilder title = new StringBuilder();
		title.append(tn+"-Customer Complaints Count").append("-"+date);
		ChartSeries timeSeries = new ChartSeries();
		List<Double> countData = new ArrayList<Double>();
		for( int i =1;i < month.length;i++ ){
			countData.add(Double.parseDouble(data[i]));

				if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(countData.size() > 0 )
		{
			timeSeries.setData(countData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName("Customer Complaints Count");
			chartSeriesList.add(timeSeries);          
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Numbers");
			chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
	
	private JSONObject getTableModel(List<String[]> headers) {

		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);

		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		jqGridTableModel.getRowHeaders().add(colHeader);

		jqGridTableModel.setRowNumbers(true);

		jqGridTableModel.setTableHeight(50000);
		jqGridTableModel.setTableWidth(800);
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
		    
		    if(i == 0)
		    {
		    	jqGridColModel.setHidden(true);
		    }
		    else if(i==1){
		    	jqGridColModel.setWidth(200);
		    }
		    else if(i==colHeader.length-1){
		    	jqGridColModel.setHidden(true);
		    }
		    
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "84%%");
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
			commonFilter = 	FilterValues.getQuality(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}
}
