/** Author: Siddharth . A  **/
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GenTlFnlnrolemapBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlGraphdata;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.KznTlLosslink;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.KznTlPillarlink;
import com.akranta.tpm.service.KaizenServices;
import com.akranta.tpm.service.api.KznTlMstServiceApi;
import com.akranta.tpm.service.impl.KaizenServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

	public class KaizenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
		KaizenServices  kaizenServices ;
		KznTlMstServiceApi kznTlMstServiceApi; 
		DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

		DateTimeFormatter outputFmtDt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter outputFmtDtTm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		 

		//CommonFilterService commonFilterService;
	    public KaizenServlet() {
	        super();
	        /*try {
				kaizenServices = new KaizenServiceImpl();
				commonFilterService = new CommonFilterServiceImpl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	        // TODO Auto-generated constructor stub
	         * 
	         */
	    }
	/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
	    	try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	    
	    private void initilizeInputMode(FormModes mode,HttpServletRequest request, HttpSession httpSession)throws Exception
	    {    	
	    	
	    	String kznKeyid = request.getParameter("kznKeyid");
	    	String kznbKeyid = request.getParameter("KZNBKeyid");
	    	CommonMessage.debugMsg("The kznbKeyid"+kznbKeyid);
	    	String kznbFlid = request.getParameter("KZNBFlid");
	    	String kznStatus=request.getParameter("kznStatus");
	    	String hdStatus=request.getParameter("HdStatus");
	    	String hdKeyid=request.getParameter("HdKeyid");
           CommonMessage.debugMsg(kznbKeyid+"  kznbKeyid kznStatus ="+kznStatus);
	    	CommonMessage.debugMsg("MODE ="+mode);
	    	String bdmmode = request.getParameter("bdmmode");
	    	
	    	
	    	httpSession.removeAttribute("bdmmode");
			if(UIUtils.isValidKeyId(bdmmode)){//For Breakdown
				CommonMessage.debugMsg("bdmmode ="+bdmmode);
				request.setAttribute("bdmmode", bdmmode);
				httpSession.setAttribute("bdmmode",bdmmode);
			}
			String factId = request.getParameter("factoryId");
			String sectId = request.getParameter("sectionId");
			String mchId = request.getParameter("machineID");
			String cellId = request.getParameter("cellId");
			String yyId= request.getParameter("yyId");
			String cucdKeyid=request.getParameter("cucdKeyid");
			String cucmCellid =request.getParameter("cucmCellid");
			String docId = request.getParameter("docId");
			httpSession.removeAttribute("qtmmode");
			if(UIUtils.isValidKeyId(cucdKeyid))//For Quality
			{
				request.setAttribute("qtmmode", true);
				httpSession.setAttribute("qtmmode",cucdKeyid);
			}
			
			httpSession.removeAttribute("yyIdKZN");
			httpSession.removeAttribute("docUpdatesId");
			if(UIUtils.isValidKeyId(docId))
			{
				httpSession.setAttribute("docUpdatesId",docId);
				//cmbStatus.setId("A");
				//commonFilter.setStatuss(cmbStatus);
			}
			if(UIUtils.isValidKeyId(yyId))
			{
				CommonMessage.debugMsg("yyId Inside="+yyId);
				httpSession.setAttribute("yyIdKZN",yyId);
			}
			
			
		 	AdmTlUsermst user = UIUtils.getLoginUser(request);
			KaizenFormBean kaizenFormBean = new KaizenFormBean(mode);
			KznTlMst  kznTlMst=null;
			CommonFilter commonFilter=new CommonFilter();
			//List<KznTlKaizenbankmst> kznTlKaizenbankmst= new KznTlKaizenbankmst();
			CommonMessage.debugMsg("mode in Servlet ="+mode);
			if(kznKeyid==null)
			{
				kaizenFormBean = new KaizenFormBean(FormModes.create);
				kaizenFormBean.setFormActionMode("Create");
			}
			else 
			{
				if(UIUtils.isValidKeyId(kznStatus))
				{
					if(kznStatus.equalsIgnoreCase("COMPLETED"))
						kaizenFormBean = new KaizenFormBean(FormModes.completion);
				}
			}
			if(UIUtils.isValidKeyId(kznbKeyid))
				kznKeyid=kaizenServices.selectKznb(kznbKeyid); 
			//kznKeyid=kaizenServices.selectKznb(kznbKeyid); 

			
			CommonMessage.debugMsg("mode "+mode);
			httpSession.removeAttribute("KaizenFormMode");
			httpSession.removeAttribute("kaizenFormBean");
			httpSession.removeAttribute("hdCommomFilter");
			httpSession.setAttribute("KaizenFormMode", mode);
			request.setAttribute("mode", mode);
			kaizenFormBean.setKaizenId(kznKeyid);
			commonFilter.setMainkeyid(hdKeyid);
			commonFilter.setStatus(hdStatus);
			CommonMessage.debugMsg("Form mode="+kaizenFormBean.getFormMode());
			httpSession.setAttribute("KaizenFormMode", kaizenFormBean.getFormMode());
			httpSession.setAttribute("kaizenFormBean", kaizenFormBean);
			httpSession.setAttribute("hdCommomFilter", commonFilter);
			if(UIUtils.isValidKeyId(kznKeyid ))
			{
				kznTlMst=kaizenServices.select(kznKeyid); 
				kaizenFormBean.setFormMode(mode);
				
				
				kznTlMst.setKznmDate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmDate()));
				kznTlMst.setKznmStartdate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmStartdate()));
				kznTlMst.setKznmEnddate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmEnddate()));
				
				kznTlMst.setKznmPrepareddate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmPrepareddate()));
				kznTlMst.setKznmApproveddate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmApproveddate()));
				kznTlMst.setKznmCompleteddate  (CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmCompleteddate()));
				
				//kznTlMst.setKznmCreatedon(kznTlMst.setKznmStartdate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmCreatedon()));
				//kznTlMst.setKznmEnddate(CommonFunctions.pg_getDateFromPGTimeStamp(kznTlMst.getKznmEnddate()));
				
				
				CommonMessage.debugMsg(kznTlMst.getKznmStartdate() +"Start Date ");				
				//String keyid=kaizenServices.selectKznb(kznbKeyid);				
				//CommonMessage.debugMsg(keyid+"  keyid");
				CommonMessage.debugMsg(" Getting value for KPI "+kznTlMst.getKznmKpiid());
				
				String filePath = UIUtils.getImagePath(request);
				
				try
				{	 
					 CommonMessage.debugMsg("Inside image service");
					 String fileName=UIUtils.TPM_TEMPIMG_DIR;
					 kznTlMst = kaizenServices.getkznImage(fileName,filePath,kznTlMst);
			    }
				catch(Exception e)
				{
					CommonMessage.debugMsg("Exception in while Selecting Image");   
				}
				 CommonMessage.debugMsg("kznTlMst.getKznmCreatedon()  "+kznTlMst.getKznmCreatedon());
				 if(UIUtils.isValidKeyId(kznbFlid))
					 kznTlMst.setKznmFlid(kznbFlid);
				
				 if(UIUtils.isValidKeyId(kznbKeyid))
					 kznTlMst.setKznmKzbnkeyid(kznbKeyid);
				fillCheckboxes(kaizenFormBean,kznTlMst);
				fillKaizenData(kaizenFormBean,kznTlMst);
				
				httpSession.removeAttribute("KaizenkznKeyid");
				httpSession.setAttribute("KaizenkznKeyid",kznKeyid);
				httpSession.removeAttribute("kznTlMst");
				httpSession.setAttribute("kznTlMst"+kznKeyid, kznTlMst);
			}
			else 
			{
				kznTlMst= new KznTlMst();
				kznTlMst.setKznmPreparedid(user.getUsrm_ccno());
				kaizenFormBean.setResponsibility(user.getUsrm_ccno());
			}
			/*if(UIUtils.isValidKeyId(kznbKeyid)){
				kznTlMst=kaizenServices.selectKznb(kznbKeyid); 
				kaizenFormBean.setFormMode(mode);
				
				fillCheckboxes(kaizenFormBean,kznTlMst);
				fillKaizenData(kaizenFormBean,kznTlMst);
				
				httpSession.removeAttribute("KaizenkznKeyid");
				httpSession.setAttribute("KaizenkznKeyid",kznTlMst.getKznmKeyid());
				httpSession.removeAttribute("kznTlMst");
				httpSession.setAttribute("kznTlMst"+kznTlMst.getKznmKeyid(), kznTlMst);
			}*/
			
			if(UIUtils.isValidKeyId(factId))
				kznTlMst.setKznmFactoryid(factId);
		
			if(UIUtils.isValidKeyId(sectId))
				kznTlMst.setKznmSectionid(sectId);
			
			if(UIUtils.isValidKeyId(cellId))
				kznTlMst.setKznmCellid(cellId);
			
			if(UIUtils.isValidKeyId(mchId))
			{
				kznTlMst.setKznmMachineid(mchId);
			}
			
			if (UIUtils.isValidKeyId(kznTlMst.getKznmKeyid()) 
					&& ( "A".equals( kznTlMst.getKznmStatus()) || "C".equals( kznTlMst.getKznmStatus())))
				kaizenFormBean.setFormMode(FormModes.approval);
			
			if(UIUtils.isValidKeyId(cucmCellid))
				kznTlMst.setKznmCellid(cucmCellid);
			 if(UIUtils.isValidKeyId(kznbFlid))
				 kznTlMst.setKznmFlid(kznbFlid);
			 if(UIUtils.isValidKeyId(kznbKeyid))
				 kznTlMst.setKznmKzbnkeyid(kznbKeyid);
			 CommonMessage.debugMsg(kznbKeyid+  "  before kaizen theme ");
			 String kznTheme="";
				
		    	if(UIUtils.isValidKeyId(kznbKeyid)){
		    		kznTheme=kaizenServices.getkaizenTheme(kznbKeyid);
		    		CommonMessage.debugMsg(kznTheme+"   kznbKeyid  theme");
		    		//kznTlMst.setKznmTheme(kznTheme);
		    		kznTlMst.setKznmIdea(kznTheme);
		    	}
		    	if(UIUtils.isValidKeyId(kznbKeyid)){
		    		String benefit=kaizenServices.getkaizenBenefit(kznbKeyid);
		    		String pcdqsme=kaizenServices.getkaizenPcdqsme(kznbKeyid);
		    		String themename=kaizenServices.getThemename(benefit);
		    		CommonMessage.debugMsg(kznTheme+"   kznbKeyid  theme");
		    		//kznTlMst.setKznmTheme(kznTheme);
		    		request.setAttribute("benefit",benefit);
		    		request.setAttribute("pcdqsme",pcdqsme);
		    		request.setAttribute("themename",themename);
		    	}
			request.setAttribute("kznTlMst", kznTlMst);
			request.setAttribute("kaizenFormBean", kaizenFormBean);
	    }
	    
	    private void initilizeSimplifyInputMode(FormModes mode,HttpServletRequest request, HttpSession httpSession)throws Exception
	    {    	
	    	
	    	String kznKeyid = request.getParameter("kznKeyid");
	    	String kznbKeyid = request.getParameter("KZNBKeyid");
	    	String kznbFlid = request.getParameter("KZNBFlid");
	    	String kznStatus=request.getParameter("kznStatus");
	    	String hdStatus=request.getParameter("HdStatus");
	    	String hdKeyid=request.getParameter("HdKeyid");
           CommonMessage.debugMsg(kznbKeyid+"  kznbKeyid kznStatus ="+kznStatus);
	    	CommonMessage.debugMsg("MODE ="+mode);
	    	String bdmmode = request.getParameter("bdmmode");
	    	
	    	
	    	httpSession.removeAttribute("bdmmode");
			if(UIUtils.isValidKeyId(bdmmode)){//For Breakdown
				CommonMessage.debugMsg("bdmmode ="+bdmmode);
				request.setAttribute("bdmmode", bdmmode);
				httpSession.setAttribute("bdmmode",bdmmode);
			}
			String factId = request.getParameter("factoryId");
			String sectId = request.getParameter("sectionId");
			String mchId = request.getParameter("machineID");
			String cellId = request.getParameter("cellId");
			String yyId= request.getParameter("yyId");
			String cucdKeyid=request.getParameter("cucdKeyid");
			String cucmCellid =request.getParameter("cucmCellid");
			String docId = request.getParameter("docId");
			httpSession.removeAttribute("qtmmode");
			if(UIUtils.isValidKeyId(cucdKeyid))//For Quality
			{
				request.setAttribute("qtmmode", true);
				httpSession.setAttribute("qtmmode",cucdKeyid);
			}
			
			httpSession.removeAttribute("yyIdKZN");
			httpSession.removeAttribute("docUpdatesId");
			if(UIUtils.isValidKeyId(docId))
			{
				httpSession.setAttribute("docUpdatesId",docId);
				//cmbStatus.setId("A");
				//commonFilter.setStatuss(cmbStatus);
			}
			if(UIUtils.isValidKeyId(yyId))
			{
				CommonMessage.debugMsg("yyId Inside="+yyId);
				httpSession.setAttribute("yyIdKZN",yyId);
			}
			
			
		 	AdmTlUsermst user = UIUtils.getLoginUser(request);
			KaizenFormBean kaizenFormBean = new KaizenFormBean(mode);
			KznTlMst  kznTlMst=null;
			CommonFilter commonFilter=new CommonFilter();
			//List<KznTlKaizenbankmst> kznTlKaizenbankmst= new KznTlKaizenbankmst();
			CommonMessage.debugMsg("mode in Servlet ="+mode);
			if(kznKeyid==null)
			{
				kaizenFormBean = new KaizenFormBean(FormModes.create);
				kaizenFormBean.setFormActionMode("Create");
			}
			else 
			{
				if(UIUtils.isValidKeyId(kznStatus))
				{
					if(kznStatus.equalsIgnoreCase("COMPLETED"))
						kaizenFormBean = new KaizenFormBean(FormModes.completion);
				}
			}
			if(UIUtils.isValidKeyId(kznbKeyid))
				kznKeyid=kaizenServices.selectKznb(kznbKeyid); 
			
			
			CommonMessage.debugMsg("mode "+mode);
			httpSession.removeAttribute("KaizenFormMode");
			httpSession.removeAttribute("kaizenFormBean");
			httpSession.removeAttribute("hdCommomFilter");
			httpSession.setAttribute("KaizenFormMode", mode);
			request.setAttribute("mode", mode);
			kaizenFormBean.setKaizenId(kznKeyid);
			commonFilter.setMainkeyid(hdKeyid);
			commonFilter.setStatus(hdStatus);
			CommonMessage.debugMsg("Form mode="+kaizenFormBean.getFormMode());
			httpSession.setAttribute("KaizenFormMode", kaizenFormBean.getFormMode());
			httpSession.setAttribute("kaizenFormBean", kaizenFormBean);
			httpSession.setAttribute("hdCommomFilter", commonFilter);
			CommonMessage.debugMsg(kznKeyid +"  kznKeyidkznKeyidkznKeyid");
			if(UIUtils.isValidKeyId(kznKeyid ))
			{
				kznTlMst=kaizenServices.select(kznKeyid);
				
				
				
				CommonMessage.debugMsg(kznTlMst.getKznmDate()+" kazien dates "+kznTlMst.getKznmStartdate()+""+kznTlMst.getKznmEnddate());
				kaizenFormBean.setFormMode(mode);
				
				CommonMessage.debugMsg(" Getting value for KPI "+kznTlMst.getKznmKpiid());
				
				String filePath = UIUtils.getImagePath(request);
				
				try
				{	 
					 CommonMessage.debugMsg("Inside image service");
					 String fileName=UIUtils.TPM_TEMPIMG_DIR;
					 kznTlMst = kaizenServices.getkznImage(fileName,filePath,kznTlMst);
			    }
				catch(Exception e)
				{
					CommonMessage.debugMsg("Exception in while Selecting Image");   
				}
				 CommonMessage.debugMsg("kznTlMst.getKznmCreatedon()  "+kznTlMst.getKznmCreatedon());
				 if(UIUtils.isValidKeyId(kznbFlid))
					 kznTlMst.setKznmFlid(kznbFlid);
				
				 if(UIUtils.isValidKeyId(kznbKeyid))
					 kznTlMst.setKznmKzbnkeyid(kznbKeyid);
				fillCheckboxes(kaizenFormBean,kznTlMst);
				fillKaizenData(kaizenFormBean,kznTlMst);
				
				httpSession.removeAttribute("KaizenkznKeyid");
				httpSession.setAttribute("KaizenkznKeyid",kznKeyid);
				httpSession.removeAttribute("kznTlMst");
				httpSession.setAttribute("kznTlMst"+kznKeyid, kznTlMst);
			}
			else 
			{
				kznTlMst= new KznTlMst();
				kznTlMst.setKznmPreparedid(user.getUsrm_ccno());
				kaizenFormBean.setResponsibility(user.getUsrm_ccno());
			}
			/*if(UIUtils.isValidKeyId(kznbKeyid)){
				kznTlMst=kaizenServices.selectKznb(kznbKeyid); 
				kaizenFormBean.setFormMode(mode);
				
				fillCheckboxes(kaizenFormBean,kznTlMst);
				fillKaizenData(kaizenFormBean,kznTlMst);
				
				httpSession.removeAttribute("KaizenkznKeyid");
				httpSession.setAttribute("KaizenkznKeyid",kznTlMst.getKznmKeyid());
				httpSession.removeAttribute("kznTlMst");
				httpSession.setAttribute("kznTlMst"+kznTlMst.getKznmKeyid(), kznTlMst);
			}*/
			
			if(UIUtils.isValidKeyId(factId))
				kznTlMst.setKznmFactoryid(factId);
		
			if(UIUtils.isValidKeyId(sectId))
				kznTlMst.setKznmSectionid(sectId);
			
			if(UIUtils.isValidKeyId(cellId))
				kznTlMst.setKznmCellid(cellId);
			
			if(UIUtils.isValidKeyId(mchId))
			{
				kznTlMst.setKznmMachineid(mchId);
			}
			
			if (UIUtils.isValidKeyId(kznTlMst.getKznmKeyid()) 
					&& ( "A".equals( kznTlMst.getKznmStatus()) || "C".equals( kznTlMst.getKznmStatus())))
				kaizenFormBean.setFormMode(FormModes.approval);
			
			if(UIUtils.isValidKeyId(cucmCellid))
				kznTlMst.setKznmCellid(cucmCellid);
			 if(UIUtils.isValidKeyId(kznbFlid))
				 kznTlMst.setKznmFlid(kznbFlid);
			 if(UIUtils.isValidKeyId(kznbKeyid))
				 kznTlMst.setKznmKzbnkeyid(kznbKeyid);
			 CommonMessage.debugMsg(kznbKeyid+  "  before kaizen theme ");
			 String kznTheme="";
				
		    	if(UIUtils.isValidKeyId(kznbKeyid)){
		    		kznTheme=kaizenServices.getkaizenTheme(kznbKeyid);
		    		CommonMessage.debugMsg(kznTheme+"   kznbKeyid  theme");
		    		//kznTlMst.setKznmTheme(kznTheme);
		    		kznTlMst.setKznmIdea(kznTheme);
		    	}
		    	if(UIUtils.isValidKeyId(kznbKeyid)){
		    		String benefit=kaizenServices.getkaizenBenefit(kznbKeyid);
		    		String pcdqsme=kaizenServices.getkaizenPcdqsme(kznbKeyid);
		    		String themename=kaizenServices.getThemename(benefit);
		    		CommonMessage.debugMsg(kznTheme+"   kznbKeyid  theme");
		    		//kznTlMst.setKznmTheme(kznTheme);
		    		request.setAttribute("benefit",benefit);
		    		request.setAttribute("pcdqsme",pcdqsme);
		    		request.setAttribute("themename",themename);
		    	}
			request.setAttribute("kznTlMst", kznTlMst);
			request.setAttribute("kaizenFormBean", kaizenFormBean);
			CommonMessage.debugMsg("kznTlMst=afterimage-= After "+ kznTlMst.getKznmAfterimage());
			CommonMessage.debugMsg("kznTlMst=afterimage-= Result "+ kznTlMst.getKznmResultimage());
			CommonMessage.debugMsg("kznTlMst=afterimage-= Present "+ kznTlMst.getKznmPresentimage());
			CommonMessage.debugMsg("kznTlMst=benefitimage-= Benefit "+ kznTlMst.getKznmBenefitsimage());
		
	    }
   
	    
	    
	    	   
	    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
	    	String dispatchUrl = null;
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			HttpSession httpSession = request.getSession(false);
			ComboFilter currentFilter = new ComboFilter();
			ComboFilter comboFilter = new ComboFilter();
			
			if( user == null)
				return ;
			String action = UIUtils.getActionPart(request);
			try {
				kaizenServices = (KaizenServiceImpl)UIUtils.getServiceObject(request,"KaizenServiceImpl");
				//commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
				kaizenServices.KaizenFormServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")));

			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
				e.printStackTrace();
			}

			if(action.equals("kaizen_input.kaizen")) 
			{
				
	  			String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
				String kznKeyid= request.getParameter("kznKeyid");
				String hdKeyid= request.getParameter("HdKeyid");//@IYYAPPAN
				String EMPillar=request.getParameter("EMPILLAR");
				String Suggestedby=request.getParameter("Suggestedby");
				CommonMessage.debugMsg("The Suggestedby"+Suggestedby);
				String flId=request.getParameter("flId");
				CommonMessage.debugMsg("The Flid Is:"+flId);
				String KZNBFlid=request.getParameter("KZNBFlid");
                String Mode=request.getParameter("Mode");
                String Suggestnid=request.getParameter("Suggestnid");
                CommonMessage.debugMsg("The Suggestnid"+Suggestnid);
                String frmName=request.getParameter("frmName");
                String KEType=request.getParameter("KEType");
                //String 
                String ThemeCategory=request.getParameter("ThemeCat");
                CommonMessage.debugMsg("Theme Category Id is>>"+ThemeCategory);
				String benFitArea=request.getParameter("benFitArea");
				String themeName=request.getParameter("themeName");

                String Kaizen=request.getParameter("Kaizen");
                CommonMessage.debugMsg("Kaizen>>"+Kaizen);
                CommonMessage.debugMsg("Kaizen>>>>>"+Kaizen);

                /*if(Kaizen!=null){
                Kaizen=Kaizen.replaceAll("_", "#");
                }
                else
                {
                	 Kaizen=Kaizen;
                }*/
                CommonMessage.debugMsg("Kaizen>>"+Kaizen);
                String DKaizen=request.getParameter("DKaizen");
                String DirectKaizen=request.getParameter("DirectKaizen");
                CommonMessage.debugMsg("The Direct Kaizen"+DKaizen);
                String BenefitArea=request.getParameter("BenefitArea");
                CommonMessage.debugMsg("The BenefitArea"+BenefitArea);
                String fileName= null;
                		CommonMessage.debugMsg(" Inside SErvlet Mode "+Mode+","+frmMode+","+kznKeyid+","+hdKeyid+","+EMPillar+Suggestedby+flId+KZNBFlid+frmName);
                if(UIUtils.isValidKeyId(kznKeyid)){
                	fileName=getFileName(kznKeyid);
                }
                CommonMessage.debugMsg(fileName +" EMPILLAR    :  "+EMPillar);
                CommonMessage.debugMsg(" Inside Input action "+kznKeyid);
				String cellId=request.getParameter("cellId");
				CommonMessage.debugMsg(" Tea ::cellId :: "+cellId);
				
				String refDocId = request.getParameter("refDocId");
				String refdocType = request.getParameter("refdocType");
				String elementId=CommonFunctions.getLoginElementId(request);
				CommonMessage.debugMsg("ElementID"+elementId);
				String locationId="";
				if(elementId.length()>10){
					locationId=elementId.substring(11,21);
				}
				FormModes mode = FormModes.create;
				CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
				if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.view) )
					mode = FormModes.view;
				else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.modify) )
					mode = FormModes.modify;
				else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.approval) )
					mode = FormModes.approval;
			
				initilizeInputMode(mode, request, httpSession);	
								
				request.setAttribute("EMPillar", EMPillar);
				
				request.setAttribute("ThemeCategory", ThemeCategory);
				
				request.setAttribute("kznKeyid", kznKeyid);
				request.setAttribute("frmMode", frmMode);
				request.setAttribute("cellId", cellId);
				request.setAttribute("refDocId", refDocId);
				request.setAttribute("refdocType", refdocType);
				request.setAttribute("Suggestedby", Suggestedby);
				request.setAttribute("flId", flId);
				request.setAttribute("KZNBFlid", KZNBFlid); 
				request.setAttribute("Mode", Mode);
				request.setAttribute("mode", Mode);
				request.setAttribute("hdKeyid", hdKeyid);
				request.setAttribute("Suggestnid", Suggestnid);
				request.setAttribute("frmName", frmName);
				request.setAttribute("KEType",KEType);
				request.setAttribute("locationId",locationId);
				request.setAttribute("Kaizen",Kaizen);
				request.setAttribute("BenefitArea",BenefitArea);
				request.setAttribute("fileNames", fileName);
				request.setAttribute("benFitArea",benFitArea);
				request.setAttribute("themeName", themeName);
				request.setAttribute("rowId", request.getParameter("rowId"));

			//dispatchUrl = "/pages/ImprovementProject.jsp";
				CommonMessage.debugMsg("System File Name "+fileName);
				
				if(DKaizen!=null){
					request.setAttribute("DirectKaizen",DirectKaizen );
					
					CommonMessage.debugMsg("Inside the DirectKaizen");
					dispatchUrl = "/pages/DirectKaizenIdeaSheetNew.jsp";
				}
				else{
					dispatchUrl = "/pages/KaizenIdeaSheetNew.jsp";	
				}
				//dispatchUrl = "/pages/KaizenIdeaSheetImprovemnt.jsp";
			}
			
			else if(action.equals("simplifykaizen_input.kaizen")) 
			{
				String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
				String kznKeyid= request.getParameter("kznKeyid");
				String hdKeyid= request.getParameter("HdKeyid");//@IYYAPPAN
				String EMPillar=request.getParameter("EMPILLAR");
				String Suggestedby=request.getParameter("Suggestedby");
				String flId=request.getParameter("flId");
				String KZNBFlid=request.getParameter("KZNBFlid");
                String Mode=request.getParameter("Mode");
                String Suggestnid=request.getParameter("Suggestnid");
                String frmName=request.getParameter("frmName");
                String KEType=request.getParameter("KEType");
                //String 
                CommonMessage.debugMsg(" Inside SErvlet Mode "+Mode+","+frmMode+","+kznKeyid+","+hdKeyid+","+EMPillar+Suggestedby+flId+KZNBFlid+frmName);
                
                CommonMessage.debugMsg("EMPILLAR    :  "+EMPillar);
                CommonMessage.debugMsg(" Inside Input action "+kznKeyid);
				String cellId=request.getParameter("cellId");
				CommonMessage.debugMsg(" Tea ::cellId :: "+cellId);
				
				String refDocId = request.getParameter("refDocId");
				String refdocType = request.getParameter("refdocType");
				String elementId=CommonFunctions.getLoginElementId(request);
				CommonMessage.debugMsg("ElementID"+elementId);
				String locationId="";
				if(elementId.length()>10){
					locationId=elementId.substring(11,21);
				}
				FormModes mode = FormModes.create;
				CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
				if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.view) )
					mode = FormModes.view;
				else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.modify) )
					mode = FormModes.modify;
				else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.approval) )
					mode = FormModes.approval;
			
				initilizeSimplifyInputMode(mode, request, httpSession);	
								
				request.setAttribute("EMPillar", EMPillar);
				request.setAttribute("kznKeyid", kznKeyid);
				request.setAttribute("frmMode", frmMode);
				request.setAttribute("cellId", cellId);
				request.setAttribute("refDocId", refDocId);
				request.setAttribute("refdocType", refdocType);
				request.setAttribute("Suggestedby", Suggestedby);
				request.setAttribute("flId", flId);
				request.setAttribute("KZNBFlid", KZNBFlid);
				request.setAttribute("Mode", Mode);
				request.setAttribute("hdKeyid", hdKeyid);
				request.setAttribute("Suggestnid", Suggestnid);
				request.setAttribute("frmName", frmName);
				request.setAttribute("KEType",KEType);
				request.setAttribute("locationId",locationId);
				dispatchUrl = "/pages/SimplifyKaizenIdeaSheetNew.jsp";
			}
			
			
			
			
			else if(action.equals("kaizen_modify.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String keyid = request.getParameter("keyid");
				CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
				List<String []> condReclData  = kaizenServices.FillTeamControlData(keyid);
				out.print( JSONArray.fromCollection(condReclData));
			}
			else if (action.equals("kaizen_recall.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String keyid = request.getParameter("KEYID");
				CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
				List<String []> kaizenData  = kaizenServices.FillControlData(keyid);
				out.print( JSONArray.fromCollection(kaizenData));
			}
			else if (action.equals("kaizenCategory_recall.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String keyid = request.getParameter("KEYID");
				CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
				List<String []> kaizenData  = kaizenServices.FillCategoryData(keyid);
				out.print( JSONArray.fromCollection(kaizenData));
			}
			else if (action.equals("kaizenCategory_update.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String keyid = request.getParameter("KEYID");
				String kzbnkeyid = request.getParameter("KzbnkznmKeyid");
				String kznmBenefit = request.getParameter("chkValue");
				CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
			String	kaizenData  = kaizenServices.updateCategory(keyid,kzbnkeyid,kznmBenefit);
				//return kaizenData;
			}
			
			else if (action.equals("empillar_input.kaizen"))
			{
				request.setAttribute("EmPillar", "EmPillar");
				dispatchUrl = "/pages/EmPillarKaizenIdeaSheet.jsp";
			}
			/*else if (action.equals("empillar_getCol.kaizen"))
			{			
				PrintWriter out = response.getWriter();		
		        CommonFilter commonFilter = populateCommonFilter(request,"EMPillarCommonFilter",true);		   
				List<String[]> emPillarGrid = kaizenServices.getAllKaizenReport(commonFilter);				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				gridColModel.setHeaderNum(1);
			
				String [] colHeader = emPillarGrid.get(2);			
				String [] colHeaderCond = emPillarGrid.get(1);
				
				List<String[]> headers = new ArrayList<String[]>();
				
				headers.add(colHeader);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableWidth", "105%%");
				jsonObject.put("tableHeight", "78%%");
				httpSession.removeAttribute("empillarColModel");
				httpSession.setAttribute("empillarColModel", jsonObject);
				out.println(jsonObject);
			
			} 
			else if (action.equals("empillar_getData.kaizen"))
			{
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"EMPillarCommonFilter",false);	
				List<String[]> emPillarGrid = kaizenServices.getAllKaizenReport(commonFilter);
				
				PrintWriter out = response.getWriter();
				JSONObject emPillarGridjson = UIUtils.convertToJqGridTableObject(emPillarGrid, request, 3, 0);
				out.println(emPillarGridjson);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}*/
			
			/** Added By Sugumar on 8-Aug-2016 for Kaizen Category Theme ComboBox*/
			else if (action.equals("kaizenactegoryfillcombo.kaizen"))
			{
				CommonFilter commonFilter=new CommonFilter();
				comboFilter=UIUtils.fillComboFilter(request);
				String flid = request.getParameter("flid");
				commonFilter.setFlid(flid);
				List<ComboBox>  Trade = kaizenServices.getKznThemeCategory(comboFilter, commonFilter);
				//UIUtils.writeComboBox(response, Trade);
				UIUtils.writeComboBox(response, Trade ,comboFilter);
			}
			/***********************************************************************/
			
			else if (action.equals("kaizenfillcombo.kaizen"))
			{

				comboFilter=UIUtils.fillComboFilter(request);
				String flid = request.getParameter("flId");
				List<ComboBox>  Trade = kaizenServices.getKpicombo(comboFilter, flid);
				//UIUtils.writeComboBox(response, Trade);
				UIUtils.writeComboBox(response, Trade ,comboFilter);
			}
		
			
			
			else if (action.equals("KaizenApproval_input.kaizen"))
			{
				//httpSession.setAttribute("KaizenFormMode", FormModes.approval);
				String filterString = request.getParameter("filterString");
				CommonMessage.debugMsg("KaizenApprovalGrid..........");
				request.setAttribute("mode", "APPROVAL");
				request.setAttribute("filterStr", filterString);
				dispatchUrl = "/pages/KaizenApprovalGrid.jsp";
			}
			else if (action.equals("KaizenApproval_getCol.kaizen")) {
			try {
			
				CommonMessage.debugMsg("KaizenApprovalGrid..........col");
				List<String[]> kznApproval = null;
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenApprovalCommonFilter", true);
				
				String mode = request.getParameter("mode");
				mode = "APPROVAL";
				CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
				commonFilter.setMainGroup(mode);
				commonFilter.setIsGetCol("Y");
				kznApproval = kaizenServices.getAllKaizenApproval(commonFilter);
				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				gridColModel.setFormattorFromCol("0");
				gridColModel.setFormattorToCol("0");

				String [] colHeader = kznApproval.get(1);
				String [] colHeaderHead = kznApproval.get(0);
				 
				List<String[]> headers = new ArrayList<String[]>();	
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				//JSONObject jsonObject = getTableModelApprovalReport(kznApproval);
				httpSession.setAttribute("KaizenApprovalColModel", jsonObject);
				out.println(jsonObject);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				
				
			} else if (action.equals("KaizenApproval_getData.kaizen")) {
				CommonMessage.debugMsg("KaizenApprovalGrid..........data");
				CommonMessage.debugMsg("get data method");
				
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenApprovalCommonFilter", false);

				commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
				commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
				
				String mode = request.getParameter("mode");
				mode = "APPROVAL";
				CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
				commonFilter.setMainGroup(mode);
				
		try {

			commonFilter.setIsGetCol("N");
					List<String[]> kznApprovalData = kaizenServices.getAllKaizenApproval(commonFilter);
					CommonMessage.debugMsg("Data size" + kznApprovalData.size());
					PrintWriter out = response.getWriter();
					JSONObject kznApproval= UIUtils.convertToJqGridTableObject(kznApprovalData, request, 2, 0);
					out.println(kznApproval);
				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}

			}
			
	        else if(action.equals("KaizenApproval_getExcel.kaizen")){
				
	        	CommonFilter commonFilter = populateCommonFilter(request,"KaizenApprovalCommonFilter", false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);//
				httpSession = request.getSession(false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("KaizenApprovalColModel");
				colmodel.put("title","Kaizen Approval Report");
	            String format = ExcelUtils.getFormat(request);
				Workbook wb = kaizenServices.kaizenApprovalExportExcel(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"KaizenApprovalReport", format);
			}
			
		//****************************************Simplified Approval***************************************************************//	
		/*	
	        else if( action.startsWith("SimplifiedKaizenApproval_input.kaizen")){
				workflowTrans(action,request,response);
			}
			*/
		       else if (action.equals("SimplifiedKaizenApproval_input.kaizen")){
					String loginuser = UIUtils.getLoginUser(request).getUsrm_username();
					 String loginflid=CommonFunctions.getLoginFlid(request);
			  		 String loginlevel=CommonFunctions.getLoginLevel(request);
			  		 String loginElementid = (String) httpSession.getAttribute("loginElementid");
					 String empId = null;
					AdmTlUsermst usersdetails = UIUtils.getLoginUser(request);
					empId =  usersdetails.getUsrm_ccno();
					AdmTlUserRoleLink newAdmTlUserRoleLink = new AdmTlUserRoleLink();
					newAdmTlUserRoleLink.setArulCreatedby(user.getUsrm_ccno());
					String newRole=newAdmTlUserRoleLink.getArulRoleid();
					//ComboFilter currentFilter =null;
					List<String []> getUserLoginDtl= kaizenServices.getElementId(loginflid,loginlevel, loginElementid,empId);
					String elementid = getUserLoginDtl.get(0)[0];
					String fnln = getUserLoginDtl.get(0)[1];
					String level = getUserLoginDtl.get(0)[2];
					String rolename=getUserLoginDtl.get(0)[3];
					String rolekeyid=getUserLoginDtl.get(0)[4];
					CommonMessage.debugMsg("rolename:"+rolename);					
					request.setAttribute("empId", request.getParameter("empId"));
					CommonMessage.debugMsg("dmc input work flow empId="+ request.getParameter("empId"));
					request.setAttribute("refId", request.getParameter("refId"));
					CommonMessage.debugMsg("dmc input work flow refId="+ request.getParameter("refId"));
					request.setAttribute("refType", request.getParameter("refType"));
					CommonMessage.debugMsg("dmc input work flow refType="+ request.getParameter("refType"));
					request.setAttribute("refRoleId", request.getParameter("refRoleId"));
					CommonMessage.debugMsg("dmc input work flow refRoleId="+ request.getParameter("refRoleId"));
					request.setAttribute("transCode", request.getParameter("transCode"));
					CommonMessage.debugMsg(elementid+" dmc input work flow transCode="+ request.getParameter("transCode")); 
		    	   
					String filterString = request.getParameter("filterString");
					//CommonMessage.debugMsg("KaizenApprovalGrid.........."+filterString);
					String location=elementid.substring(11, 21);
					CommonMessage.debugMsg(location+" dmc input work flow transCode="+ request.getParameter("transCode")); 

					request.setAttribute("mode", "APPROVAL");
					request.setAttribute("filterStr", filterString);
					request.setAttribute("loginuser",loginuser);
					request.setAttribute("rolename",rolename);
					request.setAttribute("location",location);
					dispatchUrl = "/pages/SimplifiedKaizenApprovalGrid.jsp";	
					
				}
				
		    	else if (action.equals("SimplifiedKaizenApproval_getCol.kaizen")) {
		    		try{
						PrintWriter out = response.getWriter();
						out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.SimplifiedApproval","MultipleApprove"));
					}catch(Exception e){
						e.printStackTrace(); 
					} 			 
				}
								
		    	else if (action.equals("SimplifiedKaizenApproval_getData.kaizen")){
					CommonFilter commonFilter = populateCommonFilter(request,"KaizenApprovalCommonFilter", false);
					commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
					commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
					String mode = request.getParameter("mode");
					mode = "APPROVAL";
					commonFilter.setMainGroup(mode);
			try { 
					    List<String[]> kznApprovalData = kaizenServices.getAllSimplifiedKaizenApproval(commonFilter);
						PrintWriter out = response.getWriter();
						JSONObject kznApproval= UIUtils.convertToJqGridTableObject(kznApprovalData, request, 2, 0);
						out.println(kznApproval);
					} catch (Exception e) {
						CommonMessage.debugMsg(e.getMessage());
					}

		    	}
		    	else if(action.equals("SimplifiedKaizenApproval_save.kaizen")){
		    	   	MultipleApprovalSave(request,response);
		    	}		
	
			else if (action.equals("KaizenApprovalmodify_input.kaizen")) {
				String kaizenApproval="true";
				request.setAttribute("kaizenApproval", kaizenApproval);
				//RequestDispatcher rd = request.getRequestDispatcher("/pages/ImprovementProject.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenIdeaSheetNew.jsp");
				//RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenIdeaSheetImprovemnt.jsp");
				
				
				rd.forward(request, response);
				CommonMessage.debugMsg(" response " + response);
			}
			
			else if(action.equals("Kaizenfillcombo.kznbnk") )
			{
						    	
			}
			else if(action.equals("Kazennoname_combo.kaizen")){
				try{
					
				    ComboFilter combofilter=UIUtils.fillComboFilter(request);
   	                List<ComboBox> kaznameno=kaizenServices.getKznNoName(combofilter); 
   	                UIUtils.writeComboBox(response, kaznameno, combofilter);
				}
		       catch(Exception e){
		    	   e.printStackTrace();
		       }		
			}	
			else if (action.equals("KaizenApprovalmodify_input.kaiapp")) {
				String kaizenApproval="true";
				request.setAttribute("kaizenApproval", kaizenApproval);
				//RequestDispatcher rd = request.getRequestDispatcher("/pages/ImprovementProject.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenIdeaSheetNew.jsp");
				//RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenIdeaSheetImprovemnt.jsp");
				
				rd.forward(request, response);
				CommonMessage.debugMsg(" response " + response);
			}
			else if (action.equals("KaizenModify_input.kaizen"))
			{ 
				CommonMessage.debugMsg("KaizenModify_input.kaizen");
				httpSession.setAttribute("KaizenFormMode", FormModes.modify);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				dispatchUrl = "/pages/KaizenReport.jsp";
				CommonMessage.debugMsg("KaizenReport.jsp");
			}
			else if (action.equals("KaizenComp_input.kaizen"))
			{
				httpSession.setAttribute("KaizenFormMode", FormModes.completion);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				dispatchUrl = "/pages/KaizenComplete.jsp";
			}
			
			else if (action.equals("amountInwrds_input.kaizen"))
			{
				httpSession.setAttribute("KaizenFormMode", FormModes.completion);
				String valSavings = request.getParameter("valSavings");
				request.setAttribute("valSavings", valSavings);
				dispatchUrl = "/pages/BenefitAmountPopUp.jsp";
			}
			else if(action.equals("KaizenView_input.kaizen"))
			{	
				String mode=request.getParameter("mode");
				String empkzn=request.getParameter("empkzn");
				String empKeyId=request.getParameter("empKeyId");
				String flid=request.getParameter("flid");
				String fromdate=request.getParameter("fromdate");
				String todate=request.getParameter("todate");
				String frommonth=request.getParameter("frommonth");
				String tomonth=request.getParameter("tomonth");
				httpSession.setAttribute("KaizenFormMode", FormModes.view);
				String filterString = request.getParameter("filterString");
				request.setAttribute("frommonth", frommonth);
				request.setAttribute("tomonth", tomonth);
				request.setAttribute("fromdate", fromdate);
				request.setAttribute("todate", todate);
				request.setAttribute("flid", flid);
				request.setAttribute("empkzn", empkzn);
				request.setAttribute("empKeyId", empKeyId);
				request.setAttribute("filterStr", filterString);
				request.setAttribute("mode", mode);
				dispatchUrl = "/pages/KaizenReport.jsp";
			}
			else if(action.equals("KaizenWorkFlow_input.kaizen"))
			{	
				httpSession.setAttribute("KaizenFormMode", FormModes.view);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				dispatchUrl = "/pages/KaizenReport.jsp";
			}
			else if( action.equals("kaizen_whywhy.kaizen"))
			{
				KaizenFormBean kaizenFormBean = new KaizenFormBean();
				kaizenFormBean.setFormActionMode("whywhy");
				kaizenFormBean.setFormHeader("Why Why Analysis - Kaizen");
				httpSession.setAttribute("kaizenFormBean",kaizenFormBean);
				saveKaizen(request,response);
			}
			else if(action.equals("KaizenViewDelete_input.kaizen"))
			{	
				CommonMessage.debugMsg("Kaizen View Delete input");
				String mode=request.getParameter("mode");
				String empkzn=request.getParameter("empkzn");
				String empKeyId=request.getParameter("empKeyId");
				String flid=request.getParameter("flid");
				String fromdate=request.getParameter("fromdate");
				String todate=request.getParameter("todate");
				String frommonth=request.getParameter("frommonth");
				String tomonth=request.getParameter("tomonth");
				httpSession.setAttribute("KaizenFormMode", FormModes.modify);
				String filterString = request.getParameter("filterString");
				request.setAttribute("frommonth", frommonth);
				request.setAttribute("tomonth", tomonth);
				request.setAttribute("fromdate", fromdate);
				request.setAttribute("todate", todate);
				request.setAttribute("flid", flid);
				request.setAttribute("empkzn", empkzn);
				request.setAttribute("empKeyId", empKeyId);
				request.setAttribute("filterStr", filterString);
				request.setAttribute("mode", mode);
				dispatchUrl = "/pages/kaizenviewdelete.jsp";
			}
           else if(action.equals("KaizenViewDelete_getCol.kaizen"))
			{
				try
				{
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", true);
						ComboFilter cmbFactory = new ComboFilter();
						ComboFilter cmbSection = new ComboFilter();
						ComboFilter cmbMachine = new ComboFilter();
						ComboFilter cmbCell = new ComboFilter();
						ComboFilter cmbStatus = new ComboFilter();
						
						commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
						String factId = request.getParameter("factoryId");
						String sectId = request.getParameter("sectionId");
						String mchId = request.getParameter("machineID");
						String cellId = request.getParameter("cellId");
						String yyId= request.getParameter("yyId");
						String flid= request.getParameter("flid");
						String empkzn= request.getParameter("empkzn");
						String fromdate= request.getParameter("fromdate");
						String todate= request.getParameter("todate");
						String frommonth= request.getParameter("frommonth");
						String tomonth= request.getParameter("tomonth");

						String docId = request.getParameter("docId");
						
						String mode = request.getParameter("mode");
						CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
						commonFilter.setMainGroup(mode);
						
						httpSession.removeAttribute("yyIdKZN");
						httpSession.removeAttribute("docUpdatesId");
						if(UIUtils.isValidKeyId(docId))
						{
							httpSession.setAttribute("docUpdatesId",docId);
							cmbStatus.setId("A");
							commonFilter.setStatuss(cmbStatus);
						}
						if(UIUtils.isValidKeyId(yyId))
						{
							CommonMessage.debugMsg("yyId Inside="+yyId);
							httpSession.setAttribute("yyIdKZN",yyId);
						}
						
						if(UIUtils.isValidKeyId(factId))
						{	
							cmbFactory.setId(factId);
							commonFilter.setFactory(cmbFactory);
						}
						if(UIUtils.isValidKeyId(sectId))
						{
							cmbSection.setId(sectId);
							commonFilter.setSection(cmbSection);
						}
						if(UIUtils.isValidKeyId(mchId))
						{
							cmbMachine.setId(mchId);
							commonFilter.setMachine(cmbMachine);
						}
						if(UIUtils.isValidKeyId(cellId))
						{
							cmbCell.setId(cellId);
							commonFilter.setCell(cmbCell);
						}
						CommonMessage.debugMsg(" From Col "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
						CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
						if(UIUtils.isValidKeyId(empkzn))
						{
							commonFilter.setFlid(flid);
							commonFilter.setKAIZEN(empkzn);
							commonFilter.setFromDate(fromdate);
							commonFilter.setToDate(todate);
							commonFilter.setFromMonth(frommonth);
							commonFilter.setToMonth(tomonth);
							
						}
						commonFilter.setAbnDetectBy(user.getUsrm_ccno());
						
						String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
						ComboFilter jhKaizenCategoryObj=new ComboFilter();
						if( jhKaizenCategoryObj != null)
							jhKaizenCategoryObj.setId(jhKaizenCategory);
						commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
				
					CommonMessage.debugMsg("Before List ");
					PrintWriter out = response.getWriter();	
					//------------------------
					String empKeyId=request.getParameter("empKeyId");
					
					CommonMessage.debugMsg("Before Inside modify "+empKeyId);
					
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					
					List< String[]>kaizenReportList  = kaizenServices.getAllKaizenDeleteRpt(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setPaginate(true);
					

					
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = kaizenReportList.get(2);			
					String [] colHeaderCond = kaizenReportList.get(1);
					
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					
					jsonObject.put("tableHeight", "90%%");
					jsonObject.put("tableWidth", "108%%");
					jsonObject.put("multiSelect",true);
					httpSession.setAttribute("KaizenColModel", jsonObject);
					out.println(jsonObject);
			
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
	  		    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("KaizenViewDelete_getData.kaizen"))
			{
				try 
				{
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", false);
					String empKeyId=request.getParameter("empKeyId");
					String flid=request.getParameter("flid");
					String empkzn= request.getParameter("empkzn");
					String fromdate= request.getParameter("fromdate");
					String todate= request.getParameter("todate");
					String frommonth= request.getParameter("frommonth");
					String tomonth= request.getParameter("tomonth");
					CommonMessage.debugMsg(" From Data "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
					CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
					if(UIUtils.isValidKeyId(empkzn))
					{
						commonFilter.setFlid(flid);
						commonFilter.setKAIZEN(empkzn);
						commonFilter.setFromDate(fromdate);
						commonFilter.setToDate(todate);
						commonFilter.setFromMonth(frommonth);
						commonFilter.setToMonth(tomonth);
					}
					
					String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
					ComboFilter jhKaizenCategoryObj=new ComboFilter();
					if( jhKaizenCategoryObj != null)
						jhKaizenCategoryObj.setId(jhKaizenCategory);
					commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
					//commonFilter = FilterValues.getCommonFilters(request, commonFilter);
					commonFilter.setAbnDetectBy(user.getUsrm_ccno());
					
					//------------------------
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					List< String[]>kaizenReportList  = kaizenServices.getAllKaizenDeleteRpt(commonFilter);
					JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList,request,3,0,commonFilter.getTotalRecordCnt());
					out.println(kaizenReportData);
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if(action.equals("Kaizen_delete.kaizen")){
				KaizenDelete(request,response);
			}
			
			else if(action.equals("KaizenDataUpdate_input.kaizen")){
				  UIUtils.forwardRequest(request, response,"/pages/KaizenDataUpdate.jsp");	
				}
				   else if (action.equals("KaizenDataUpdate_getCol.kaizen")){
						PrintWriter out = response.getWriter();			
						List<String[]> kznDateUpDateDate = null;				
						CommonFilter commonFilter  = populateCommonFilter(request,"kaizenDataFilter",true);
						try {
							kznDateUpDateDate = kaizenServices.getKaizenDataGrid(commonFilter);
							CommonMessage.debugMsg("KznDataUpdate");
							JSONObject colmodel = getKaizenDataUpdateTableModel(kznDateUpDateDate.get(1),kznDateUpDateDate.get(1),'M',true,true) ;
					       	httpSession.removeAttribute("KznDataColModel");
							httpSession.setAttribute("KznDataColModel", colmodel);
							out.println(colmodel);
		
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
				   else if (action.equals("KaizenDataUpdate_getData.kaizen")){
						   CommonFilter commonFilter  = populateCommonFilter(request,"kaizenDataFilter",false);
						   httpSession.removeAttribute("kaizenDataFilter");
			  			   httpSession.setAttribute("kaizenDataFilter", commonFilter);
						   List<String[]> kznDateUpDate;
						try {
							kznDateUpDate = kaizenServices.getKaizenDataGrid(commonFilter);
							JSONObject crmMaster = UIUtils.convertToJqGridTableObject(kznDateUpDate, request,2, 0,commonFilter.getTotalRecordCnt());
					    	   PrintWriter out = response.getWriter();	
					    	   out.println(crmMaster);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
			else if(action.equals("KaizenRejectReworkStatus_input.kaizen")){
				CommonMessage.debugMsg("Kaizen Reject Rework Status");
				String mode=request.getParameter("mode");
				String empkzn=request.getParameter("empkzn");
				String empKeyId=request.getParameter("empKeyId");
				String flid=request.getParameter("flid");
				String fromdate=request.getParameter("fromdate");
				String todate=request.getParameter("todate");
				String frommonth=request.getParameter("frommonth");
				String tomonth=request.getParameter("tomonth");
				httpSession.setAttribute("KaizenFormMode", FormModes.modify);
				String filterString = request.getParameter("filterString");
				request.setAttribute("frommonth", frommonth);
				request.setAttribute("tomonth", tomonth);
				request.setAttribute("fromdate", fromdate);
				request.setAttribute("todate", todate);
				request.setAttribute("flid", flid);
				request.setAttribute("empkzn", empkzn);
				request.setAttribute("empKeyId", empKeyId);
				request.setAttribute("filterStr", filterString);
				request.setAttribute("mode", mode);
				dispatchUrl = "/pages/kaizenRejectReworkStatus.jsp";
			}
			else if(action.equals("KaizenRejectReworkStatus_getCol.kaizen"))
			{
				try
				{
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", true);
						ComboFilter cmbFactory = new ComboFilter();
						ComboFilter cmbSection = new ComboFilter();
						ComboFilter cmbMachine = new ComboFilter();
						ComboFilter cmbCell = new ComboFilter();
						ComboFilter cmbStatus = new ComboFilter();
						
						commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
						String factId = request.getParameter("factoryId");
						String sectId = request.getParameter("sectionId");
						String mchId = request.getParameter("machineID");
						String cellId = request.getParameter("cellId");
						String yyId= request.getParameter("yyId");
						String flid= request.getParameter("flid");
						String empkzn= request.getParameter("empkzn");
						String fromdate= request.getParameter("fromdate");
						String todate= request.getParameter("todate");
						String frommonth= request.getParameter("frommonth");
						String tomonth= request.getParameter("tomonth");

						String docId = request.getParameter("docId");
						
						String mode = request.getParameter("mode");
						CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
						commonFilter.setMainGroup(mode);
						
						httpSession.removeAttribute("yyIdKZN");
						httpSession.removeAttribute("docUpdatesId");
						if(UIUtils.isValidKeyId(docId))
						{
							httpSession.setAttribute("docUpdatesId",docId);
							cmbStatus.setId("A");
							commonFilter.setStatuss(cmbStatus);
						}
						if(UIUtils.isValidKeyId(yyId))
						{
							CommonMessage.debugMsg("yyId Inside="+yyId);
							httpSession.setAttribute("yyIdKZN",yyId);
						}
						
						if(UIUtils.isValidKeyId(factId))
						{	
							cmbFactory.setId(factId);
							commonFilter.setFactory(cmbFactory);
						}
						if(UIUtils.isValidKeyId(sectId))
						{
							cmbSection.setId(sectId);
							commonFilter.setSection(cmbSection);
						}
						if(UIUtils.isValidKeyId(mchId))
						{
							cmbMachine.setId(mchId);
							commonFilter.setMachine(cmbMachine);
						}
						if(UIUtils.isValidKeyId(cellId))
						{
							cmbCell.setId(cellId);
							commonFilter.setCell(cmbCell);
						}
						CommonMessage.debugMsg(" From Col "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
						CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
						if(UIUtils.isValidKeyId(empkzn))
						{
							commonFilter.setFlid(flid);
							commonFilter.setKAIZEN(empkzn);
							commonFilter.setFromDate(fromdate);
							commonFilter.setToDate(todate);
							commonFilter.setFromMonth(frommonth);
							commonFilter.setToMonth(tomonth);
							
						}
						commonFilter.setAbnDetectBy(user.getUsrm_ccno());
						
						String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
						ComboFilter jhKaizenCategoryObj=new ComboFilter();
						if( jhKaizenCategoryObj != null)
							jhKaizenCategoryObj.setId(jhKaizenCategory);
						commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
				
					CommonMessage.debugMsg("Before List ");
					PrintWriter out = response.getWriter();	
					//------------------------
					String empKeyId=request.getParameter("empKeyId");
					
					CommonMessage.debugMsg("Before Inside modify "+empKeyId);
					
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					
					List< String[]>kaizenReportList  = kaizenServices.getAllKaizenReport(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setPaginate(true);
					

					
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = kaizenReportList.get(2);			
					String [] colHeaderCond = kaizenReportList.get(1);
					
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					
					jsonObject.put("tableHeight", "90%%");
					jsonObject.put("tableWidth", "108%%");
					jsonObject.set("multiSelect",true);	
					httpSession.setAttribute("KaizenColModel", jsonObject);
					out.println(jsonObject);
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
	  		    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
		
			else if( action.equals("KaizenRejectReworkStatus_getData.kaizen"))
			{
				try 
				{
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", false);
					String empKeyId=request.getParameter("empKeyId");
					String flid=request.getParameter("flid");
					String empkzn= request.getParameter("empkzn");
					String fromdate= request.getParameter("fromdate");
					String todate= request.getParameter("todate");
					String frommonth= request.getParameter("frommonth");
					String tomonth= request.getParameter("tomonth");
					CommonMessage.debugMsg(" From Data "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
					CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
					if(UIUtils.isValidKeyId(empkzn))
					{
						commonFilter.setFlid(flid);
						commonFilter.setKAIZEN(empkzn);
						commonFilter.setFromDate(fromdate);
						commonFilter.setToDate(todate);
						commonFilter.setFromMonth(frommonth);
						commonFilter.setToMonth(tomonth);
					}
					
					String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
					ComboFilter jhKaizenCategoryObj=new ComboFilter();
					if( jhKaizenCategoryObj != null)
						jhKaizenCategoryObj.setId(jhKaizenCategory);
					commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
					//commonFilter = FilterValues.getCommonFilters(request, commonFilter);
					commonFilter.setAbnDetectBy(user.getUsrm_ccno());
					
					//------------------------
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					List< String[]>kaizenReportList  = kaizenServices.getAllKaizenReport(commonFilter);
					JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList,request,3,0,commonFilter.getTotalRecordCnt());
					out.println(kaizenReportData);
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			
			else if( action.equals("KaizenModify_getCol.kaizen")|| action.equals("KaizenView_getCol.kaizen") || action.equals("KaizenWorkFlow_getCol.kaizen")|| action.equals("empillar_getCol.kaizen" ) ) 
			{
				try
				{
						//CommonFilter commonFilter = new CommonFilter();
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", true);
						ComboFilter cmbFactory = new ComboFilter();
						ComboFilter cmbSection = new ComboFilter();
						ComboFilter cmbMachine = new ComboFilter();
						ComboFilter cmbCell = new ComboFilter();
						ComboFilter cmbStatus = new ComboFilter();
						
						commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
						String factId = request.getParameter("factoryId");
						String sectId = request.getParameter("sectionId");
						String mchId = request.getParameter("machineID");
						String cellId = request.getParameter("cellId");
						String yyId= request.getParameter("yyId");
						String flid= request.getParameter("flid");
						String empkzn= request.getParameter("empkzn");
						String fromdate= request.getParameter("fromdate");
						String todate= request.getParameter("todate");
						String frommonth= request.getParameter("frommonth");
						String tomonth= request.getParameter("tomonth");
						
						//String docType=request.getParameter("DocType");
						//String docNo=request.getParameter("DocNo");

						String docId = request.getParameter("docId");
						
						String mode = request.getParameter("mode");
						CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
						commonFilter.setMainGroup(mode);
						
						httpSession.removeAttribute("yyIdKZN");
						httpSession.removeAttribute("docUpdatesId");
						if(UIUtils.isValidKeyId(docId))
						{
							httpSession.setAttribute("docUpdatesId",docId);
							cmbStatus.setId("A");
							commonFilter.setStatuss(cmbStatus);
						}
						if(UIUtils.isValidKeyId(yyId))
						{
							CommonMessage.debugMsg("yyId Inside="+yyId);
							httpSession.setAttribute("yyIdKZN",yyId);
						}
						
						if(UIUtils.isValidKeyId(factId))
						{	
							cmbFactory.setId(factId);
							commonFilter.setFactory(cmbFactory);
						}
						if(UIUtils.isValidKeyId(sectId))
						{
							cmbSection.setId(sectId);
							commonFilter.setSection(cmbSection);
						}
						if(UIUtils.isValidKeyId(mchId))
						{
							cmbMachine.setId(mchId);
							commonFilter.setMachine(cmbMachine);
						}
						if(UIUtils.isValidKeyId(cellId))
						{
							cmbCell.setId(cellId);
							commonFilter.setCell(cmbCell);
						}
						CommonMessage.debugMsg(" From Col "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
						CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
						if(UIUtils.isValidKeyId(empkzn))
						{
							commonFilter.setFlid(flid);
							commonFilter.setKAIZEN(empkzn);
							commonFilter.setFromDate(fromdate);
							commonFilter.setToDate(todate);
							commonFilter.setFromMonth(frommonth);
							commonFilter.setToMonth(tomonth);
							
						}
						commonFilter.setAbnDetectBy(user.getUsrm_ccno());
						
						String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
						ComboFilter jhKaizenCategoryObj=new ComboFilter();
						if( jhKaizenCategoryObj != null)
							jhKaizenCategoryObj.setId(jhKaizenCategory);
						commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
						//httpSession.removeAttribute("kaizenCommonFilterVal");
						//httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
					CommonMessage.debugMsg("Before List ");
					PrintWriter out = response.getWriter();	
					//------------------------
					String empKeyId=request.getParameter("empKeyId");
					
					CommonMessage.debugMsg("Before Inside modify "+empKeyId);
					
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					
					commonFilter.setIsGetCol("Y");
					List< String[]>kaizenReportList  = kaizenServices.getAllKaizenReport(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setPaginate(true);
					
					//jqGridTableModel.setGroupBy(true);
					//jqGridTableModel.setGroupByField("");
					
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = kaizenReportList.get(1);			
					String [] colHeaderCond = kaizenReportList.get(0);
					
					//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					
					jsonObject.put("tableHeight", "90%%");
					jsonObject.put("tableWidth", "108%%");
					httpSession.setAttribute("KaizenColModel", jsonObject);
					out.println(jsonObject);
					/*String kznImprProjectColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznModification");
					out.print(kznImprProjectColM);*/
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
	  		    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("KaizenModify_getData.kaizen")|| action.equals("KaizenView_getData.kaizen") || action.equals("KaizenWorkFlow_getData.kaizen")|| action.equals("empillar_getData.kaizen"))
			{
				try 
				{
					PrintWriter out = response.getWriter();
					//CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("kaizenCommonFilterVal");
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", false);
					String empKeyId=request.getParameter("empKeyId");
					String flid=request.getParameter("flid");
					String empkzn= request.getParameter("empkzn");
					String fromdate= request.getParameter("fromdate");
					String todate= request.getParameter("todate");
					String frommonth= request.getParameter("frommonth");
					String tomonth= request.getParameter("tomonth");
					CommonMessage.debugMsg(" From Data "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
					CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
					if(UIUtils.isValidKeyId(empkzn))
					{
						commonFilter.setFlid(flid);
						commonFilter.setKAIZEN(empkzn);
						commonFilter.setFromDate(fromdate);
						commonFilter.setToDate(todate);
						commonFilter.setFromMonth(frommonth);
						commonFilter.setToMonth(tomonth);
					}
					
					String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
					ComboFilter jhKaizenCategoryObj=new ComboFilter();
					if( jhKaizenCategoryObj != null)
						jhKaizenCategoryObj.setId(jhKaizenCategory);
					commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
					//commonFilter = FilterValues.getCommonFilters(request, commonFilter);
					commonFilter.setAbnDetectBy(user.getUsrm_ccno());
					
					//------------------------
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					///
					commonFilter.setIsGetCol("N");
					List< String[]>kaizenReportList  = kaizenServices.getAllKaizenReport(commonFilter);
					JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList,request,2,0,commonFilter.getTotalRecordCnt());
					out.println(kaizenReportData);
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("filterXmlKaizenModify_input.kaizen")||(action.equals("filterXmlKaizenView_input.kaizen")) || (action.equals("filterXmlKaizenWorkFlow_input.kaizen")) || action.equals("filterXmlempillar_input.kaizen")|| action.equals("filterXmlKaizenApproval_input.kaizen"))
			{
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/KaizenModification.xml") ;
			}
			
			
			else if(action.equals("empillar_getExcel.kaizen")){
				
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"EMPillarCommonFilter",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("empillarColModel");
				colmodel.put("title","EM Pillar Kaizen Report");
	            String format = ExcelUtils.getFormat(request);
	            String emppillar=request.getParameter("Emppillar");
	            CommonMessage.debugMsg(" Inside Servlet emppillar "+emppillar);
	            
	            if(UIUtils.isValidKeyId(emppillar))
	               commonFilter.setType(emppillar);
	            
				Workbook wb = kaizenServices.getEmPillarReportExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "EMPillarReport", format);
				
				
			}
			
		else if(action.equals("KaizenModify_getExcel.kaizen")|| action.equals("KaizenView_getExcel.kaizen"))
			{
				CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("kaizenCommonFilterVal");
				String tmpFromRow = commonFilter.getFromRow();
				
				String fileName="";
				commonFilter.setFromRow(null);
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznModification");
				
				//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenColModel");
				tblJSONObj.put("title", "Kaizen Report");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = kaizenServices.kaizenRptExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				if(action.equals("KaizenModify_getExcel.kaizen"))
					fileName="Kaizen_Modification";
				else if(action.equals("KaizenView_getExcel.kaizen"))
					fileName="Kaizen_View";
				
				
				ExcelUtils.writeToResponse(response, wb,fileName, format);
				
			}
			
	 
			//--------------------FOR COMPLETION MODE----------------------------//
	
			
			else if(action.equals("KaizenComp_getCol.kaizen"))
			{
				try
				{
					PrintWriter out = response.getWriter();
					CommonFilter  commonFilter = populateCommonFilter(request,"kaizenCommonFilterVal",true);	
					commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
					commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
					
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
					List<String[]> kaizenReportList  = kaizenServices.getKaizenCompletedDtls(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					gridColModel.setHeaderNum(1);
					gridColModel.setFormatter("cboxFormatter");
					gridColModel.setFormattorFromCol("1");
					gridColModel.setFormattorToCol("1");
					
					String [] colHeader = kaizenReportList.get(1);			
					String [] colHeaderCond = kaizenReportList.get(0);
					
					//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.put("tableHeight", "80%%");
					jsonObject.put("tableWidth", "106%%");
					out.println(jsonObject);
					/*String kznCompleteColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznCompleteTable");
					CommonMessage.debugMsg("kznCompleteColM="+kznCompleteColM);
					out.print(kznCompleteColM);*/
	  		    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if(action.equals("KaizenComp_getData.kaizen"))
			{
				CommonMessage.debugMsg("Inside getData");
				try 
				{
					PrintWriter out = response.getWriter();
					CommonFilter  commonFilter =populateCommonFilter(request,"kaizenCommonFilterVal",false);	
					
	  			 	List<String[]> kaizenReportList  = kaizenServices.getKaizenCompletedDtls(commonFilter);
	  			 	CommonMessage.debugMsg("commonFilter.getTotalRecordCnt() ="+commonFilter.getTotalRecordCnt());
	  			 	JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList,request,2,0,commonFilter.getTotalRecordCnt()); 
	  			 	out.println(kaizenReportData);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("filterXmlKaizenComp_input.kaizen"))
			{
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/KaizenCompleteDetails.xml") ;
			}
			
			else if(action.equals("KznCompletedDetails.kaizen"))
			{
				try
				{
					recallKaizenComplete(request,response);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg("Exception in Imprv Complete Details"+e.getMessage());
				}
				
			}
			else if(action.equals("KznHDComp_input.kaizen"))
			{
				
			}
			
			else if(action.equals("KznHDComp_getCol.kaizen"))
			{
				try
				{
					PrintWriter out = response.getWriter();
					CommonFilter  commonFilter = populateCommonFilter(request,"kaizenHDCommonFilterVal",true);	
					commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
					commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
					httpSession.removeAttribute("kaizenHDCommonFilterVal");
					httpSession.setAttribute("kaizenHDCommonFilterVal", commonFilter);
					String kznCompleteColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders","kznHDViewTable");
					out.print(kznCompleteColM);
	  		    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if(action.equals("KznHDComp_getData.kaizen"))
			{
				CommonMessage.debugMsg("Inside getData");
				try 
				{
					PrintWriter out = response.getWriter();
					CommonFilter  commonFilter =populateCommonFilter(request,"kaizenHDCommonFilterVal",false);	
					 
					List< String[]>kaizenReportList  = kaizenServices.getKaizenHDView(commonFilter);
					CommonMessage.debugMsg("kaizenReportList ="+kaizenReportList.size());
					JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList,request,0,1,commonFilter.getTotalRecordCnt());
					out.println(kaizenReportData);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if(action.equals("KznHDComp_getExcel.kaizen")||action.equals("KaizenComp_getExcel.kaizen"))
			{
				CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("kaizenCommonFilterVal");
				String tmpFromRow = commonFilter.getFromRow();
				String kznColModel="";
				boolean isKznHd=false;
				commonFilter.setFromRow(null);
				
				if(action.equals("KznHDComp_getExcel.kaizen"))
					{kznColModel="kznHDViewTable";isKznHd=true;}
				else if(action.equals("KaizenComp_getExcel.kaizen"))
					kznColModel="kznCompleteTable";
				
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", kznColModel);
				
				JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				((JSONObject)((JSONArray) tblJSONObj.get("colModel")).get(1)).set("hidden",true );
				
				tblJSONObj.put("title", "Kaizen Details");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = kaizenServices.kaizenCompleteRptExportExcel(commonFilter,tblJSONObj,format,isKznHd );
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "Kaizen_Report", format);
			}
			
			
			else if(action.equals("KaizenComp_delete.kaizen"))
			{
				CommonMessage.debugMsg("indsd  delete ");
		/*		KznTlMst newKznTlMst = new KznTlMst();
				
				newKznTlMst =(KznTlMst)UIUtils.setBeanProperties((Object)newKznTlMst,request);
				
				List<KznTlHdmst> HDCompleteList =null;
				KznTlHdmst kznTlHdmst = new KznTlHdmst();
	    		String kznTlHdmstStr =  request.getParameter("gridData");
	    		CommonMessage.debugMsg("kznTlHdmstStr ="+kznTlHdmstStr);
	    		JSONArray kznTlHdmstJSON =null;
	    		if( kznTlHdmstStr != null && ! kznTlHdmstStr.isEmpty())
	    		{
	    			kznTlHdmstJSON = JSONArray.fromString(kznTlHdmstStr);
	    			HDCompleteList=(List<KznTlHdmst>)UIUtils.convertJSONArrToList(kznTlHdmst, kznTlHdmstJSON);
	    		}
	    		
				if( HDCompleteList != null)
					newKznTlMst.setKznTlHdmst(kznTlHdmst);
			*/		PrintWriter out = response.getWriter();
			
				try
				{
					String khdmkeyIds = request.getParameter("kznHdDeletion");
					CommonMessage.debugMsg("khdmkeyIds ="+khdmkeyIds);
					if(khdmkeyIds!=null)
					{
						String[] results = khdmkeyIds.split( ",\\s*" ); // split on commas
						String output ="";
						for ( String khdmkeyId : results )
						{
							output += "'" + khdmkeyId + "',";
						}
						output=output.substring(0, output.length()-1);
						kaizenServices.deleteKznHd(output);
						JSONObject successData = new JSONObject();
	 					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
	 					JSONObject returnData = new JSONObject();
	 					returnData.put("successData", successData);		
	 					out.print(returnData.toString());
					}
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg("Exception while deleting hd complete"+e.getMessage());
				}
			}
			
			else if(action.equals("KznHDComp_getData.kaizen"))
			{
				CommonMessage.debugMsg("Inside HD DELTER");	
			}
			
			else if(action.equals("KznUpdateCompleteDtls.kaizen"))
			{
				CommonMessage.debugMsg("Inside Update");
				updateKaizenComplete(request, response);
			}
			
			else if(action.equals("fillKznCompletedDetails.kaizen"))
			{
				String kznKeyid = request.getParameter("kznKeyid");
				//String khdmKaizenid = request.getParameter("khdmKaizenid");
				String khdmKaizenid = request.getParameter("khdmkeyid");
				String isHdReq = request.getParameter("isHdReq");
				String selectedId= request.getParameter("selectedId");
				
				KznTlMst kznTlMst =kaizenServices.select(kznKeyid); 
				
				kznTlMst.setKznmTheme(kznTlMst.getKznmTheme().replace("<*", "").replace("*>", ""));
				kznTlMst.setKznmRemarks(kznTlMst.getKznmRemarks().replace("<*", "").replace("*>", ""));
				
				if(kznTlMst.getKznmCompleteddate().contains(Constants.pgPassNullDateTime)||
						kznTlMst.getKznmCompleteddate().contains(Constants.pgFutureNullDateTime))
					kznTlMst.setKznmCompleteddate(CommonFunctions. pg_getDate());
				
			//	kznTlMst.setKznmDate(kznTlMst.getKznmDate().substring(0,11));
				kznTlMst.setKznmDate(LocalDateTime.parse(kznTlMst.getKznmDate().substring(0,11), inputFmt).format(outputFmtDt));

				kznTlMst.setKznmCompletedid(user.getUsrm_ccno());
				kznTlMst.setKznmCompleteddate(LocalDateTime.parse(kznTlMst.getKznmCompleteddate().substring(0,11), inputFmt).format(outputFmtDt));
				
				if(isHdReq.equals("2"))
				{
					List< String[]>	kaizenHdList=kaizenServices.getKznHdCellMch(khdmKaizenid); 
					if(kaizenHdList != null && kaizenHdList.size() > 0)
					{	
						request.setAttribute("khdmCellid", kaizenHdList.get(0)[0]);
						request.setAttribute("khdmMachineid", kaizenHdList.get(0)[1]);
					}
				}
				request.setAttribute("selectedId",selectedId);
				request.setAttribute("isHdReq",isHdReq);
				request.setAttribute("disableForm", true);
				request.setAttribute("kznTlMst", kznTlMst);
				
				UIUtils.forwardRequest(request, response,"/pages/KaizenCompletionPopup.jsp");
			}
			 //---------------------------------Kaizen Individual----------------------------------------------------------//		
			else if (action.equals("IndividualKaizenModify_input.kaizen"))
			{
				httpSession.setAttribute("KaizenFormMode", FormModes.modify);
				String filterString = request.getParameter("filterString");
				AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
				String empId=userkeyid.getUsrm_ccno();
			  	CommonMessage.debugMsg("The empId::"+empId);
				request.setAttribute("filterStr", filterString);
				request.setAttribute("empId",empId);
				dispatchUrl = "/pages/IndividualKaizenReport.jsp";
			}		
			else if( action.equals("IndividualKaizenModify_getCol.kaizen")|| action.equals("IndividualKaizenView_getCol.kaizen")) 
			{
				  try
				 {
					    CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", true);
						ComboFilter cmbFactory = new ComboFilter();
						ComboFilter cmbSection = new ComboFilter();
						ComboFilter cmbMachine = new ComboFilter();
						ComboFilter cmbCell = new ComboFilter();
						ComboFilter cmbStatus = new ComboFilter();
						AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
						String empId=userkeyid.getUsrm_ccno();
					  	CommonMessage.debugMsg("The empId::"+empId);
					    commonFilter.setGetKaizenkey(empId);
						commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
						String factId = request.getParameter("factoryId");
						String sectId = request.getParameter("sectionId");
						String mchId = request.getParameter("machineID");
						String cellId = request.getParameter("cellId");
						String yyId= request.getParameter("yyId");
						String flid= request.getParameter("flid");
						String empkzn= request.getParameter("empkzn");
						String fromdate= request.getParameter("fromdate");
						String todate= request.getParameter("todate");
						String frommonth= request.getParameter("frommonth");
						String tomonth= request.getParameter("tomonth");

						String docId = request.getParameter("docId");
						
						String mode = request.getParameter("mode");
						CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
						commonFilter.setMainGroup(mode);
						
						httpSession.removeAttribute("yyIdKZN");
						httpSession.removeAttribute("docUpdatesId");
						if(UIUtils.isValidKeyId(docId))
						{
							httpSession.setAttribute("docUpdatesId",docId);
							cmbStatus.setId("A");
							commonFilter.setStatuss(cmbStatus);
						}
						if(UIUtils.isValidKeyId(yyId))
						{
							CommonMessage.debugMsg("yyId Inside="+yyId);
							httpSession.setAttribute("yyIdKZN",yyId);
						}
						
						if(UIUtils.isValidKeyId(factId))
						{	
							cmbFactory.setId(factId);
							commonFilter.setFactory(cmbFactory);
						}
						if(UIUtils.isValidKeyId(sectId))
						{
							cmbSection.setId(sectId);
							commonFilter.setSection(cmbSection);
						}
						if(UIUtils.isValidKeyId(mchId))
						{
							cmbMachine.setId(mchId);
							commonFilter.setMachine(cmbMachine);
						}
						if(UIUtils.isValidKeyId(cellId))
						{
							cmbCell.setId(cellId);
							commonFilter.setCell(cmbCell);
						}
						CommonMessage.debugMsg(" From Col "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
						CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
						if(UIUtils.isValidKeyId(empkzn))
						{
							commonFilter.setFlid(flid);
							commonFilter.setKAIZEN(empkzn);
							commonFilter.setFromDate(fromdate);
							commonFilter.setToDate(todate);
							commonFilter.setFromMonth(frommonth);
							commonFilter.setToMonth(tomonth);
							
						}
						commonFilter.setAbnDetectBy(user.getUsrm_ccno());
						
						String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
						ComboFilter jhKaizenCategoryObj=new ComboFilter();
						if( jhKaizenCategoryObj != null)
							jhKaizenCategoryObj.setId(jhKaizenCategory);
						commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
					PrintWriter out = response.getWriter();	
					//------------------------
					String empKeyId=request.getParameter("empKeyId");
					
					CommonMessage.debugMsg("Before Inside modify "+empKeyId);
					
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					
					List< String[]>kaizenReportList  = kaizenServices.getIndividualKaizenReport(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setPaginate(true);
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = kaizenReportList.get(2);			
					String [] colHeaderCond = kaizenReportList.get(1);
					
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					
					jsonObject.put("tableHeight", "90%%");
					jsonObject.put("tableWidth", "108%%");
					httpSession.setAttribute("KaizenColModel", jsonObject);
					out.println(jsonObject);
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
	  		    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("IndividualKaizenModify_getData.kaizen")||action.equals("IndividualKaizenView_getData.kaizen"))
			{
				try 
				{
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", false);
					AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
					String empId=userkeyid.getUsrm_ccno();
				  	CommonMessage.debugMsg("The empId::"+empId);
					String empKeyId=request.getParameter("empKeyId");
					String flid=request.getParameter("flid");
					String empkzn= request.getParameter("empkzn");
					String fromdate= request.getParameter("fromdate");
					String todate= request.getParameter("todate");
					String frommonth= request.getParameter("frommonth");
					String tomonth= request.getParameter("tomonth");
					CommonMessage.debugMsg(" From Data "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
					CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
					if(UIUtils.isValidKeyId(empkzn))
					{
						commonFilter.setFlid(flid);
						commonFilter.setKAIZEN(empkzn);
						commonFilter.setFromDate(fromdate);
						commonFilter.setToDate(todate);
						commonFilter.setFromMonth(frommonth);
						commonFilter.setToMonth(tomonth);
					}
					
					String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
					ComboFilter jhKaizenCategoryObj=new ComboFilter();
					if( jhKaizenCategoryObj != null)
						jhKaizenCategoryObj.setId(jhKaizenCategory);
					commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
					commonFilter.setAbnDetectBy(user.getUsrm_ccno());
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					commonFilter.setGetKaizenkey(empId);
					List< String[]>kaizenReportList  = kaizenServices.getIndividualKaizenReport(commonFilter);
					JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList,request,3,0,commonFilter.getTotalRecordCnt());
					out.println(kaizenReportData);
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
				
			else if(action.equals("IndividualKaizenModify_getExcel.kaizen") ||action.equals("IndividualKaizenView_getExcel.kaizen")){
				CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("kaizenCommonFilterVal");
				String tmpFromRow = commonFilter.getFromRow();
				AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
				String empId=userkeyid.getUsrm_ccno();
			  	CommonMessage.debugMsg("The empId::"+empId);
			  	commonFilter.setGetKaizenkey(empId);
				String fileName="";
				commonFilter.setFromRow(null);
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznModification");
				JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenColModel");
				tblJSONObj.put("title", "Individual Kaizen Report");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = kaizenServices.kaizenIndividualRptExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				if(action.equals("IndividualKaizenModify_getExcel.kaizen"))
					fileName="Kaizen_Modification";
				ExcelUtils.writeToResponse(response, wb,fileName, format);
			}
			
			else if (action.equals("IndividualKaizenView_input.kaizen"))
			{
				httpSession.setAttribute("KaizenFormMode", FormModes.modify);
				String filterString = request.getParameter("filterString");
				AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
				String empId=userkeyid.getUsrm_ccno();
			  	CommonMessage.debugMsg("The empId::"+empId);
				request.setAttribute("filterStr", filterString);
				request.setAttribute("empId",empId);
				dispatchUrl = "/pages/IndividualKaizenReport.jsp";
			}	
			
			//-------------------------------------------------------------------//
			else if(action.equals("functionalLoc.kaizen"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbkznmFactoryid");
				functLocFieldNameBean.setSection("cmbkznmSectionid");
				functLocFieldNameBean.setCell("cmbkznmCellid");
				functLocFieldNameBean.setMachine("cmbkznmMachineid1");
				functLocFieldNameBean.setFunctionalLocId("cmbkznmFlid");
				functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(true);
				functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(false);
				
				FormModes formModes = (FormModes)httpSession.getAttribute("KaizenFormMode");
				String modeReturn =(String)httpSession.getAttribute("bdmmode");
				String qtmMode =(String)httpSession.getAttribute("qtmmode");
				CommonMessage.debugMsg("modeReturn ="+modeReturn);
				boolean funcLocnView=false;
				
				if(UIUtils.isValidKeyId(modeReturn))
					funcLocnView=true;
				
				if(UIUtils.isValidKeyId(qtmMode))
					funcLocnView=true;
				
				CommonMessage.debugMsg("formModes ="+formModes+"funcLocnView "+funcLocnView);
				if(( formModes == FormModes.completion)||funcLocnView==true|| formModes ==FormModes.view)
					formModes = FormModes.view;
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			}
			
			else if( action.equals("kaizen_save.kaizen"))
			{	
				CommonMessage.debugMsg("inside save");
				saveKaizen(request,response);
		    }
			else if( action.equals("IndustryCategory.kaizen"))
			{	
				CommonMessage.debugMsg("inside Category");
				 PrintWriter out = response.getWriter(); 
				 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.KaizenIndustry4", "Industry"));
		    }
			else if(action.equals("kaizenRejStatusPopup_input.kaizen")){
				try{
					
				CommonMessage.debugMsg("Kaizen Rejstatus popup");
				String kaizenstaKeyid=request.getParameter("Keyid");
				CommonMessage.debugMsg("Kaizen kaizenstaKeyid"+kaizenstaKeyid);
				String kaizenstatus=request.getParameter("kznStatus");
				CommonMessage.debugMsg("Kaizen kaizenstatus"+kaizenstatus);
				request.setAttribute("kznKeyid",kaizenstaKeyid);
				request.setAttribute("kaizenstatu",kaizenstatus);
				RequestDispatcher rd1=request.getRequestDispatcher("/pages/KaizenRejectReworkStatusPopup.jsp");
				 rd1.forward(request,response);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			} 
			else if(action.equals("Kaizenrejectreworkststatus_save.kaizen")){
				saveKaizenRejectReworkStatus(request,response);
			}
			
			
			else if( action.equals("kaizen_delete.kaizen"))
			{	
				CommonMessage.debugMsg("inside delete");
				deleteKaizen(request,response);
		    }
			
			else if(action.equals("kznAfterImageClear.kaizen"))
			{
			/*	String imageType=request.getParameter("imageType");
				CommonMessage.debugMsg("imageType ="+imageType);
				request.setAttribute("imgClear",true);
				request.setAttribute("imageType", imageType);
				List<String> imageTypeList=new ArrayList<String>();
				imageTypeList.add(request.getParameter("presentImage"));
				imageTypeList.add(request.getParameter("afterImage"));
				imageTypeList.add(request.getParameter("resultImage"));
				
				
				httpSession.setAttribute("kznimageType", imageTypeList);
				
				UIUtils.forwardRequest(request, response, "/pages/ImprovementProject.jsp");*/
				KaizenFormBean kaizenFormBean=(KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
				kaizenFormBean.setAfterImage(request.getParameter("afterImage"));
				
				
				
				CommonMessage.debugMsg("AfterImage ="+kaizenFormBean.getAfterImage());

				CommonMessage.debugMsg("setResultImage ="+kaizenFormBean.getResultImage());
				httpSession.setAttribute("kaizenFormBean",kaizenFormBean);
			}
			else if(action.equals("kznBenefitImageClear.kaizen"))
			{
				KaizenFormBean kaizenFormBean=(KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
				kaizenFormBean.setBenefitImage(request.getParameter("benefitImage"));	
				CommonMessage.debugMsg("Benefit ="+kaizenFormBean.getBenefitImage());
                CommonMessage.debugMsg("setResultImage ="+kaizenFormBean.getResultImage());
				httpSession.setAttribute("kaizenFormBean",kaizenFormBean);
			}
			else if(action.equals("kznPresentImageClear.kaizen"))
			{
				KaizenFormBean kaizenFormBean=(KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
				kaizenFormBean.setPresentImage(request.getParameter("presentImage"));
				CommonMessage.debugMsg("AfterImage ="+kaizenFormBean.getAfterImage());

				CommonMessage.debugMsg("setResultImage ="+kaizenFormBean.getResultImage());
				httpSession.setAttribute("kaizenFormBean",kaizenFormBean);
				
			}
			
			else if(action.equals("kznResultImageClear.kaizen"))
			{
				KaizenFormBean kaizenFormBean=(KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
				kaizenFormBean.setResultImage(request.getParameter("resultImage"));
					
				CommonMessage.debugMsg("AfterImage ="+kaizenFormBean.getAfterImage());

				CommonMessage.debugMsg("setResultImage ="+kaizenFormBean.getResultImage());
				httpSession.setAttribute("kaizenFormBean",kaizenFormBean);
				
			}
			else if( action.equals("combo_improvemnetNo.kaizen"))
			{
				try 
				{
					currentFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  kznmKeyids = kaizenServices.getImprovementNoCombo("",currentFilter);
					UIUtils.writeComboBox(response, kznmKeyids,currentFilter);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				//commonFilterService.getEmployeeComboList(employee);
			}
			
			else if(action.equals("combo_whywhy.kaizen")){
				try {
					String wwmsKeyid=request.getParameter("wwmsKeyid");
					currentFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  kznmKeyids = kaizenServices.getWhyWhyCombo(wwmsKeyid,currentFilter);
					
					UIUtils.writeComboBox(response, kznmKeyids,currentFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			
			else if(action.equals("Pillar_input.kaizen"))
			{
			}
			
			else if(action.equals("Pillar_getCol.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String kznPillarColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznpillarTable");
				out.print(kznPillarColM);
			}
			
			else if(action.equals("Pillar_getData.kaizen"))
			{
				try
				{	
					String kaizenId = request.getParameter("kaizenId");
					PrintWriter out = response.getWriter();
					//KznTlMst kznTlMst =(KznTlMst) httpSession.getAttribute("kznTlMst"+kaizenId);	
					List<String[]> kznPillarList = kaizenServices.getAllPillarLink(kaizenId);
				
				//	JSONObject pillarLink = UIUtils.convertToJqGridTableObject(kznPillarList,request,0,0);
				//	out.println(pillarLink);
					
					String prevPillarid = null;
					String prevKznid = null;
					String prevCatid = null;
					String prevCat = null;
					String prevPillarKznid = null;
					String prevRowView = null;
					int rowId = 0;
					
					StringBuffer lossIds = new StringBuffer();
					StringBuffer  lossNumber = new StringBuffer ();
					StringBuffer kznIds = new StringBuffer();
					StringBuffer  kznCode = new StringBuffer ();
					StringBuffer  kznCatId = new StringBuffer ();
					JSONArray rowArr = new JSONArray();  
					String tempVal = null;
					boolean addRow = false;
					for(int i=0;i<=kznPillarList.size();i++)
					{
						if( i<kznPillarList.size()){
							String [] row = kznPillarList.get(i);
							addRow = false;
							if( (( row[0] != null && row[0].equals(prevPillarid) ) || (prevPillarid == null)) &&( (( row[13] != null && row[13].equals(prevKznid) ) || prevKznid == null ) )){
								CommonMessage.debugMsg(row[4]+"  if   "+i);
								if( UIUtils.isValidKeyId(row[10]) ){
									lossIds.append(row[10] +","); //lossId
									lossNumber.append(row[11] +",");
								}
							}
							else{
								addRow = true;
								CommonMessage.debugMsg("else"+i);
							}
							prevPillarid = row[0];
							prevKznid=row[13];
							prevCatid = row[7];
							prevCat = row[8];
							prevPillarKznid = row[5];
							prevRowView = row[4];
						}
						if( addRow ) {
							rowId++;
							JSONObject rowObj =new JSONObject();
			    	    	
				    	    rowObj.put("id",rowId);
				            JSONArray cell=new JSONArray();
				            String [] prevRow = kznPillarList.get(i-1);
				            for( int j = 0;j<prevRow.length;j++){
				            	
				            	if( j == 10 ) // loss id
				            		tempVal =lossIds.toString();
				            	else if( j == 11 ) // loss number
				            		tempVal =lossNumber.toString();
				            	/*else if( j == 5 ) // loss number
				            		tempVal =kznIds.toString();
				            	else if( j == 7 ) // loss number
				            		tempVal =kznCatId.toString();
				            	else if( j == 8 ) // loss number
				            		tempVal =kznCode.toString();*/
				            	else
				            		tempVal = prevRow[j];
				            
				            	CommonMessage.debugMsg("tempVal ="+tempVal);
				            	cell.put(tempVal );
				            }
				            rowObj.put("cell",cell);
				            rowArr.put(rowObj);
				            
				            lossIds.setLength(0);
				            lossNumber.setLength(0);
				            if( i<kznPillarList.size()){
					            String [] row = kznPillarList.get(i);
					            if( UIUtils.isValidKeyId(row[10]) ){
					            	CommonMessage.debugMsg("last in if ");
									lossIds.append(row[10] +","); //lossId
									lossNumber.append(row[11] +","); //loss number
								}	
				            }
						}
						
					}
					JSONObject kznPillarLnkData = new JSONObject();
					kznPillarLnkData.put("page", 1); //current page
					kznPillarLnkData.put("total", rowId);
					kznPillarLnkData.put("records", rowId);
					kznPillarLnkData.put("rows", rowArr);
					
					CommonMessage.debugMsg("rowArr ="+rowArr);
					
		    		out.println(kznPillarLnkData);
		    		
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg("Get Data Exception"+e.getMessage());
					e.printStackTrace();
				}
			}
			
			else if(action.equals("imprvCategory_input.kaizen"))
			{
				dispatchUrl = "/pages/MultiSelectPopup.jsp";
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
			
			else if(action.equals("imprvCategory_getCol.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String kznImprCategoryColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznimprovCategoryTable");
				CommonMessage.debugMsg("colheader ="+kznImprCategoryColM);
				out.print(kznImprCategoryColM);
			}
			
			else if(action.equals("imprvCategory_getData.kaizen"))
			{
				CommonMessage.debugMsg("Inside imprv category getData");
				PrintWriter out = response.getWriter();
				List<String> pillarId=new ArrayList<String>();
				pillarId.add(request.getParameter("pillarId"));	
				try 
				{
					List<String []> ImprCategoryList  = kaizenServices.getAllImprvCategory(pillarId);
					CommonMessage.debugMsg("ImprCategoryList ="+ImprCategoryList.size());
					if(ImprCategoryList.size()<=0)
					{
						String msg="No Records Found";
						JSONObject returnData= new JSONObject();
					    returnData.put("dataNotExist", msg);				
						out.print(returnData.toString());
					}
					
					JSONObject ImprCategoryData = UIUtils.convertToJqGridTableObject(ImprCategoryList,request,0,0);
					out.println(ImprCategoryData);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			else if(action.equals("genLoss_input.kaizen"))
			{
				dispatchUrl = "/pages/MultiSelectPopup.jsp";
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
			
			else if(action.equals("genLoss_getCol.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String kznSelectColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznLossMultiSelectTable");
				CommonMessage.debugMsg(kznSelectColM);
				out.print(kznSelectColM);
			}
			
			else if(action.equals("genLoss_getData.kaizen"))
			{
				PrintWriter out = response.getWriter();
				try 
				{
					List<String []> kznMultiSelctList  = kaizenServices.getMultiSelectLoss();
					JSONObject kznMultiSelctData = UIUtils.convertToJqGridTableObject(kznMultiSelctList,request,0,0);
					CommonMessage.debugMsg("kznMultiSelctData="+kznMultiSelctData);
					out.println(kznMultiSelctData);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			else if(action.equals("kaizenCategory.kaizen"))
			{
				KaizenFormBean kaizenFormBean = new KaizenFormBean();
				kaizenFormBean.setFormActionMode("category");
				kaizenFormBean.setFormHeader("Kaizen Category");
				httpSession.setAttribute("kaizenFormBean",kaizenFormBean);
				//saveKaizen(request,response);
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/kaizencategory.jsp"); 
				rd.forward(request, response); 
			}
			
			else if(action.equals("kznHdScan_input.kaizen"))
			{
				CommonMessage.debugMsg("Inside input");
			}
			
			else if(action.equals("kznHdScan_getCol.kaizen"))
			{
			
				PrintWriter out = response.getWriter();
				String kznSelectColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznHDScanTable");
				CommonMessage.debugMsg(kznSelectColM);
				out.print(kznSelectColM);			
			}
			
			else if(action.equals("kznHdScan_getData.kaizen"))
			{
				PrintWriter out = response.getWriter();
				try 
				{
					KaizenFormBean kaizenFormBean=(KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
					CommonFilter commonFilter=(CommonFilter)httpSession.getAttribute("hdCommomFilter");
					String kaizenId=kaizenFormBean.getKaizenId();
					List<String []> kznHDScanList  = kaizenServices.getkznHDScanTbl(kaizenId,commonFilter);
					JSONObject kznHDScanData = UIUtils.convertToJqGridTableObject(kznHDScanList,request,0,0);
					out.println(kznHDScanData);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			}
			else if(action.equals("kznBTSGrid_input.kaizen"))
			{
				CommonMessage.debugMsg("Inside kznBTSGrid_input.kaizen");
			}
			
			else if(action.equals("kznBTSGrid_getCol.kaizen"))
			{
			
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenBTSGrid",true);
				PrintWriter out = response.getWriter();
				List<String []> getKznBTSGrid  = kaizenServices.getBTSGridData(commonFilter);
				JSONObject kznBTSData = UIUtils.convertToJqGridTableObject(getKznBTSGrid,request,0,0,commonFilter.getTotalRecordCnt());
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				
				String [] colHeader = getKznBTSGrid.get(2);			
				String [] colHeaderCond = getKznBTSGrid.get(1);
				
				gridColModel.setHeaderNum(1);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "50%%");
				jsonObject.put("data", kznBTSData);
				jsonObject.set("tableHeight", "50%%");
				
				httpSession.removeAttribute("KaizenBTS");
				httpSession.setAttribute("KaizenBTS", jsonObject);
				out.println(jsonObject);		
			}
			else if(action.equals("kznBTSGrid_getData.kaizen"))
			{
				PrintWriter out = response.getWriter();
				try 
				{
					//KaizenFormBean kaizenFormBean=(KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
					//String kaizenId=kaizenFormBean.getKaizenId();
					CommonFilter commonFilter = populateCommonFilter(request,"KaizenBTSGrid",true);
					List<String []> kznBTSList  = kaizenServices.getBTSGridData(commonFilter);
					JSONObject kznBTScanData = UIUtils.convertToJqGridTableObject(kznBTSList,request,3,0);
					out.println(kznBTScanData);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			}
			else if(action.equals("horizontalDeplymnt.kaizen"))
			{	
				KaizenFormBean kaizenFormBean =(KaizenFormBean)httpSession.getAttribute("kaizenFormBean") ;
				kaizenFormBean.setFormActionMode("hdScan");
				httpSession.setAttribute("kaizenFormBean",kaizenFormBean);
				saveKaizen(request, response);
			}
			
			else if(action.equals("kznGraphData_input.kaizen"))
			{
			
			}
			
			else if(action.equals("kznGraphData_getCol.kaizen"))
			{
				 PrintWriter out = response.getWriter();
				 String kznKeyid= request.getParameter("kaizenId");//(String)httpSession.getAttribute("KaizenkznKeyid");
				 String fromMonth = request.getParameter("fromMonth");
				 String toMonth = request.getParameter("toMonth");
				 String isRequest = request.getParameter("isRequest");
				
				 if(fromMonth==null)
					 fromMonth=CommonFunctions.getDate().substring(3,11);
				 if(toMonth==null)
					 toMonth=CommonFunctions.getDate().substring(3,11);
				 if(UIUtils.isValidKeyId(kznKeyid)&& isRequest==null)
				 {
					 List< String[]> kznGphmnthsList  = kaizenServices.getAllGraphMnths( kznKeyid);
					 if(kznGphmnthsList!= null && kznGphmnthsList.size() > 0)
					 { 
						 fromMonth= kznGphmnthsList.get(0)[1].substring(3,11);toMonth=kznGphmnthsList.get(kznGphmnthsList.size()-1)[1].substring(3,11);
					 }
				 }
		
				 List< String[]> kznGraphDataList  =  kaizenServices.getAllGraphData(kznKeyid,fromMonth,toMonth);
				
				 JSONObject jsonObject = null;
				 if(kznGraphDataList!=null && kznGraphDataList.size() > 0)
		      		 jsonObject = getTableModel(kznGraphDataList);
	
		    	 out.println(jsonObject);
			}
			
			else if(action.equals("kznGraphData_getData.kaizen"))
			{
				 PrintWriter out = response.getWriter();
				 
				 String kznKeyid= request.getParameter("kaizenId");//(String)httpSession.getAttribute("KaizenkznKeyid");
				 String fromMonth = request.getParameter("fromMonth");
				 String toMonth = request.getParameter("toMonth");
				 String isRequest = request.getParameter("isRequest");
				 if(fromMonth==null)
					 fromMonth=CommonFunctions.getDate().substring(3,11);
				 if(toMonth==null)
					 toMonth=CommonFunctions.getDate().substring(3,11);
				 
				 CommonMessage.debugMsg("kznKeyid="+kznKeyid+"fromMonth="+fromMonth+"toMonth");
				 if(UIUtils.isValidKeyId(kznKeyid) && isRequest==null)
				 {
					 List< String[]> kznGphmnthsList  = kaizenServices.getAllGraphMnths( kznKeyid);
					 if(kznGphmnthsList!= null && kznGphmnthsList.size() > 0)
					 { fromMonth= kznGphmnthsList.get(0)[1].substring(3,11);toMonth=kznGphmnthsList.get(kznGphmnthsList.size()-1)[1].substring(3,11);
					 
					 CommonMessage.debugMsg("kznGphmnthsList.get(0)[1]"+kznGphmnthsList.get(0)[1].substring(3,11));
					 CommonMessage.debugMsg("kznGphmnthsList.get(1)[kznGphmnthsList.size()"+kznGphmnthsList.get(kznGphmnthsList.size()-1)[1].substring(3,11));
					 }
				 }
			
				 List< String[]> kznGraphDataList  = kaizenServices.getAllGraphData( kznKeyid,fromMonth,toMonth);
			
			
				 JSONObject kznGraphData  = null;
				// if(kznGraphDataList.get(0).length-1<3)
				 if( kznGraphDataList.size() < 2)
					 kznGraphData = addJsonRows();
				 else	 
					 kznGraphData = UIUtils.convertToJqGridTableObject( kznGraphDataList,request,1,0);
			
				 out.println(kznGraphData);
			}
			
			else if(action.equals("kznWhyWhy_input.kaizen"))
			{
			}
			
			else if(action.equals("kznWhyWhy_getCol.kaizen"))
			{
				PrintWriter out = response.getWriter();
				String kznSelectColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznWhyWhyTable");
				CommonMessage.debugMsg(kznSelectColM);
				out.print(kznSelectColM);
			}
			
			else if(action.equals("kznWhyWhy_getData.kaizen"))
			{
				PrintWriter out = response.getWriter();
				try 
				{
					String wwmsKeyid=request.getParameter("wwmsKeyid");
					if(UIUtils.isValidKeyId(wwmsKeyid))
					{	
						List<String []> kznYYList  = kaizenServices.getkznWhyWhyData(wwmsKeyid);
						long count =kznYYList != null ? kznYYList.size():0;
						JSONObject kznYYData = UIUtils.convertToJqGridTableObject(kznYYList,request,0,0,0,count);
						CommonMessage.debugMsg("kznYYData="+kznYYData);
						out.println(kznYYData);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if (action.equals("employeeWiseMonth_input.kaizen")) 
			{   
				String EmpwiseType="KZN";
				CommonMessage.debugMsg("Inside the Employee Kaizen For MonthWise");
				httpSession=request.getSession(false);
				httpSession.removeAttribute("EmpwiseType");
				httpSession.setAttribute("EmpwiseType",EmpwiseType);
				dispatchUrl ="/pages/EmployeeWiseMonthWiseKaizen.jsp";
				
			}else if (action.equals("employeeWiseMonth_getCol.kaizen")) 
			{
				String EmpwiseType="";
				CommonMessage.debugMsg("Inside the Employee Kaizen For MonthWise GetCol");
				PrintWriter out = response.getWriter();	
			//	String empKeyId=request.getParameter("empKeyId");
				httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",true);
				CommonMessage.debugMsg("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());

				/*if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
				{
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					commonFilter.setMonwise("Y");
				}*/
				CommonMessage.debugMsg("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());
				commonFilter.setEmpwiseType("KZN");
				EmpwiseType=commonFilter.getEmpwiseType();
				commonFilter.setIsGetCol("Y");
				List<String[]> employeeWiseMonthWiseKaizen  = kaizenServices.getEmployeeWiseMonthWiseKzn(commonFilter);	
				JSONObject jsonObject = getTableModelEmployeeWiseKaizen(employeeWiseMonthWiseKaizen);
				jsonObject.set("tableHeight", "80%%");
			    jsonObject.set("tableWidth", "110%%");
				httpSession.removeAttribute("ImprovementColData");
				httpSession.setAttribute("ImprovementColData", jsonObject);
				CommonMessage.debugMsg("Table Model:" + jsonObject);
				out.println(jsonObject);
		
			}else if (action.equals("employeeWiseMonth_getData.kaizen")) 
			{
				CommonMessage.debugMsg("3.getData");
				PrintWriter out = response.getWriter();	
				httpSession=request.getSession(false);
				CommonFilter commonFilter=populateCommonFilter(request, "KaizenSuggestionSummaryCommonFilter", true);
				commonFilter.setEmpwiseType("KZN");
				commonFilter.setIsGetCol("N");
				List<String[]> KaizenGridData = kaizenServices.getEmployeeWiseMonthWiseKzn(commonFilter);
				JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 2, 0,commonFilter.getTotalRecordCnt()+2);
				out.print(dataJson);
				httpSession.removeAttribute("KaizenSuggestionSummaryCommonFilter");
				httpSession.setAttribute("KaizenSuggestionSummaryCommonFilter", commonFilter);	
			}
			
		else if (action.equals("employeeWiseMonth_getExcel.kaizen")) 
			{
	    	   	httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("ImprovementColData");
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				colmodel.put("title", "Month Wise Employee Wise Kaizen Report  ");			
				String format = ExcelUtils.getFormat(request);	
				String empKeyId=request.getParameter("empKeyId");
				ComboFilter employee=new ComboFilter();
				employee.setId(empKeyId);
				commonFilter.setEmployee(employee);
				Workbook wb = kaizenServices.getEmployeeWiseMonthKaizenExcel(commonFilter,colmodel,format);			
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "MonthWiseEmployeeWiseKaizen", format);		
					
			}
			
			if (action.equals("employeeWiseMonthWise_input.kaizen")) 
			{
				CommonMessage.debugMsg("1._input");
				dispatchUrl = "/pages/EmployeeWiseMonthWiseKaizen.jsp";
				
			}else if (action.equals("employeeWiseMonthWise_getCol.kaizen")) 
			{
				CommonMessage.debugMsg("2._getCol");
				PrintWriter out = response.getWriter();	
				String empKeyId=request.getParameter("empKeyId");
				httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",true);
				CommonMessage.debugMsg("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());

				if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
				{
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					commonFilter.setMonwise("Y");
				}
				CommonMessage.debugMsg("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());
				ComboFilter employee=new ComboFilter();
				employee.setId(empKeyId);
				commonFilter.setEmployee(employee);
				List<String[]> employeeWiseMonthWiseKaizen  = kaizenServices.getKaizenEmployeeWiseMonthWise(commonFilter);	
				CommonMessage.debugMsg( "From get col :" +commonFilter.getEmployee().getId());
				//JSONObject impVscomData = UIUtils.convertToJqGridTableObject(employeeWiseMonthWiseKaizen, request, 1, 2);
				JSONObject jsonObject = getTableModelEmployeeWiseKaizen(employeeWiseMonthWiseKaizen);
				jsonObject.set("tableHeight", "80%%");
			    jsonObject.set("tableWidth", "110%%");
				httpSession.removeAttribute("ImprovementColData");
				httpSession.setAttribute("ImprovementColData", jsonObject);
				CommonMessage.debugMsg("Table Model:" + jsonObject);
				out.println(jsonObject);
				
				
				
			}else if (action.equals("employeeWiseMonthWise_getData.kaizen")) 
			{
				CommonMessage.debugMsg("3.getData");
				PrintWriter out = response.getWriter();	
				httpSession=request.getSession(false);
				CommonFilter commonFilter=populateCommonFilter(request, "KaizenSuggestionSummaryCommonFilter", false);
				String empKeyId=request.getParameter("empKeyId");
				ComboFilter employee=new ComboFilter();
				employee.setId(empKeyId);
				commonFilter.setEmployee(employee);
				List<String[]> KaizenGridData = kaizenServices.getKaizenEmployeeWiseMonthWise(commonFilter);
				CommonMessage.debugMsg( "From get data :" +commonFilter.getEmployee().getId());
				JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 3, 0,commonFilter.getTotalRecordCnt() );
				out.print(dataJson);
				httpSession.removeAttribute("KaizenSuggestionSummaryCommonFilter");
				httpSession.setAttribute("KaizenSuggestionSummaryCommonFilter", commonFilter);	
				
				
			}
			
			else if (action.equals("employeeWiseMonthWise_getExcel.kaizen")) 
			{
				CommonMessage.debugMsg("employeeWiseMonthWise_getExcel.kaizen---------->");
	    	   	httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("ImprovementColData");
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				colmodel.put("title", "Month Wise Employee Wise Kaizen Report  ");			
				String format = ExcelUtils.getFormat(request);	
				String empKeyId=request.getParameter("empKeyId");
				ComboFilter employee=new ComboFilter();
				employee.setId(empKeyId);
				commonFilter.setEmployee(employee);
				Workbook wb = kaizenServices.getMonthEmployeeWiseKaizenExcel(commonFilter,colmodel,format);			
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "MonthWiseEmployeeWiseKaizen", format);			
			}
			else if(action.equals("employeeWiseMonthWiseTotal_input.kaizen")){
				CommonMessage.debugMsg("Inside the EmployeeMonthWise Total");
				dispatchUrl = "/pages/EmployeeWiseMonthWiseKaizen.jsp";
			}
	       else if (action.equals("employeeWiseMonthWiseTotal_getCol.kaizen")) 
			{
			    PrintWriter out = response.getWriter();	
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("The empKeyId Total"+empKeyId);
				httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",true);
				CommonMessage.debugMsg("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());

				if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
				{
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					commonFilter.setMonwise("Y");
				}
				CommonMessage.debugMsg("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());
				ComboFilter employee=new ComboFilter();
				employee.setId(empKeyId);
				commonFilter.setEmployee(employee);
				List<String[]> employeeWiseMonthWiseKaizenTotal=null;  
				employeeWiseMonthWiseKaizenTotal= kaizenServices.getKaizenEmployeeWiseMonthWiseTotal(commonFilter);
				CommonMessage.debugMsg("The GetCol"+commonFilter.getEmployee().getId());
				JSONObject jsonObject = getTableModelEmployeeWiseKaizen(employeeWiseMonthWiseKaizenTotal);
				jsonObject.set("tableHeight", "80%%");
			    jsonObject.set("tableWidth", "110%%");
				httpSession.removeAttribute("ImprovementColData");
				httpSession.setAttribute("ImprovementColData", jsonObject);
				CommonMessage.debugMsg("Table Model:" + jsonObject);
				out.println(jsonObject);				
				
			}else if (action.equals("employeeWiseMonthWiseTotal_getData.kaizen")) 
			{
				CommonMessage.debugMsg("3.getData");
				PrintWriter out = response.getWriter();	
				httpSession=request.getSession(false);
				CommonFilter commonFilter=populateCommonFilter(request, "KaizenSuggestionSummaryCommonFilter", false);
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("The Total empKeyId::"+empKeyId);
				ComboFilter employee=new ComboFilter();
				employee.setId(empKeyId);
				commonFilter.setEmployee(employee);
				List<String[]> KaizenGridData=null;
				KaizenGridData=kaizenServices.getKaizenEmployeeWiseMonthWiseTotal(commonFilter);
				CommonMessage.debugMsg( "From get data :" +commonFilter.getEmployee().getId());
				JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 3, 0,commonFilter.getTotalRecordCnt() );
				out.print(dataJson);
				httpSession.removeAttribute("KaizenSuggestionSummaryCommonFilter");
				httpSession.setAttribute("KaizenSuggestionSummaryCommonFilter", commonFilter);		
			}
			else if(action.equals("employeeWiseMonthWiseTotal_getExcel.kaizen")){
				CommonMessage.debugMsg("employeeWiseMonthWiseTotal_getExcel.kaizen---------->");
	    	   	httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("ImprovementColData");
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				colmodel.put("title", "Month Wise Employee Wise Kaizen Report  ");			
				String format = ExcelUtils.getFormat(request);	
				String empKeyId=request.getParameter("empKeyId");
				ComboFilter employee=new ComboFilter();
				employee.setId(empKeyId);
				commonFilter.setEmployee(employee);
				Workbook wb = kaizenServices.getMonthEmployeeWiseTotalKaizenExcel(commonFilter,colmodel,format);			
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "MonthWiseEmployeeWiseKaizen", format);
			}
			
			
			else if (action.equals("kaizen_update.kaizen"))
			{
			  try {
				    PrintWriter out = response.getWriter();
				    KznTlMst newKznTlMst = new KznTlMst();
				    
				    String status=request.getParameter("status");
				  //  CommonMessage.debugMsg("status::123"+status);
					String keyid=request.getParameter("keyid");
					String nextLevel=request.getParameter("nextLevel");
					//CommonMessage.debugMsg("nextLevel::123"+nextLevel);
					String type=request.getParameter("type");
					//CommonMessage.debugMsg("type::123"+type);
					String approvallevel=request.getParameter("approvallevel");
					//CommonMessage.debugMsg("approvallevel::123"+approvallevel);
					String value=request.getParameter("value");
					String verifyamount=request.getParameter("veramnt");
					String mpvalue=request.getParameter("mpvalue");
				//	CommonMessage.debugMsg("mpvalue::123"+mpvalue);
					newKznTlMst=kaizenServices.updateKaizenStatus(status,keyid,nextLevel,type,value,approvallevel,mpvalue,verifyamount);
					
					CommonMessage.debugMsg(" Approval Value "+newKznTlMst.getKznmApprovLevel());
					
					JSONObject returnData = new JSONObject();
					if(!UIUtils.isValidKeyId(approvallevel)){
					
						String saveMsg = "success-save";
						returnData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",saveMsg));
					
					}
					
					out.print(returnData.toString());
					
	   			} catch (Exception e) {

				}
			}
            
			else if(action.equals("kaizenThemeReport_input.kaizen")){
				UIUtils.forwardRequest(request, response,"/pages/KaizenThemeCategoryReport.jsp");
			}
			
	         else if (action.equals("kaizenThemeReport_getCol.kaizen"))
				{
					PrintWriter out = response.getWriter();			
					List<String[]> kznDateUpDateDate = null;				
					CommonFilter commonFilter  = populateCommonFilter(request,"kaizenThemeFilter",true);
					try {
						commonFilter.setIsGetCol("Y");
						kznDateUpDateDate = kaizenServices.getKaizenThemeGridData(commonFilter);
						CommonMessage.debugMsg("KznThemeUpdate");
						JSONObject colmodel = getKaizenThemeUpdateTableModel(kznDateUpDateDate.get(1),kznDateUpDateDate.get(1),'M',true,true) ;
				       	httpSession.removeAttribute("KznThemeColModel");
						httpSession.setAttribute("KznThemeColModel", colmodel);
						out.println(colmodel);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
					
				}else if (action.equals("kaizenThemeReport_getData.kaizen"))
				{
					   CommonFilter commonFilter  = populateCommonFilter(request,"kaizenThemeFilter",false);
					   httpSession.removeAttribute("kaizenDateFilter");
		  			   httpSession.setAttribute("kaizenDateFilter", commonFilter);
					   List<String[]> kznDateUpDate;
					try {
						commonFilter.setIsGetCol("N");
						kznDateUpDate = kaizenServices.getKaizenThemeGridData(commonFilter);
						JSONObject crmMaster = UIUtils.convertToJqGridTableObject(kznDateUpDate, request,2, 0,commonFilter.getTotalRecordCnt());
				    	   PrintWriter out = response.getWriter();	
				    	   out.println(crmMaster);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (action.equals("kaizenThemeReport_getExcel.kaizen")){
					CommonFilter commonFilter  = populateCommonFilter(request,"kaizenThemeFilter",false);
		    	    List<String[]> kznThemeUpdate = null;				
		    	    kznThemeUpdate = kaizenServices.getKaizenThemeGridData(commonFilter);	
			    	JSONObject colmodel = getKaizenThemeTableModelExcel(kznThemeUpdate.get(1),kznThemeUpdate.get(1),'M',true,true,action) ;
		    	    colmodel.put("title","Kaizen Theme Category Report");	    	    
		    	  	String format = ExcelUtils.getFormat(request);
					Workbook wb;
					try 
					{
						
						wb  = kaizenServices.getKaizenThemeUpdateExcel(colmodel,format,commonFilter);
						ExcelUtils.writeToResponse(response, wb,"KaizenThemeUpdate ",format);
					} 
					catch (Exception e)
					{
							e.printStackTrace();
					}
					
				}
			
				else if(action.equals("kaizenThemeReport_save.kaizen")){
					try {					
						updateKizenTheme(request, response);
					} catch (Exception e) {
					}
				}
				else if(action.equals("KaizenDataUpdate_save.kaizen")){
					try{
						updateKaizenData(request,response);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			else if (action.equals("kaizenDateUpdateData_input.kaizen"))
			{
				CommonMessage.debugMsg("kaizenDateUpdateData_input.kaizen");
				dispatchUrl = "/pages/KaizenDateUpdate.jsp";
				
			}else if (action.equals("kaizenDateUpdateData_getCol.kaizen"))
			{
				PrintWriter out = response.getWriter();			
				List<String[]> kznDateUpDateDate = null;				
				CommonFilter commonFilter  = populateCommonFilter(request,"kaizenDateFilter",true);
				kznDateUpDateDate = kaizenServices.getKznDateUpdateData(commonFilter);	
		    	JSONObject colmodel = getKaizenDateUpdateTableModel(kznDateUpDateDate.get(1),kznDateUpDateDate.get(1),'M',true,true) ;
		       	httpSession.removeAttribute("KznDateColModel");
				httpSession.setAttribute("KznDateColModel", colmodel);
				out.println(colmodel);
				
				
			}else if (action.equals("kaizenDateUpdateData_getData.kaizen"))
			{
				   CommonFilter commonFilter  = populateCommonFilter(request,"kaizenDateFilter",false);
				   httpSession.removeAttribute("kaizenDateFilter");
	  			   httpSession.setAttribute("kaizenDateFilter", commonFilter);
				   List<String[]> kznDateUpDate =kaizenServices.getKznDateUpdateData(commonFilter);
		    	   JSONObject crmMaster = UIUtils.convertToJqGridTableObject(kznDateUpDate, request,2, 0,commonFilter.getTotalRecordCnt());
		    	   CommonMessage.debugMsg("crmMaster "+ crmMaster);
		    	   PrintWriter out = response.getWriter();	
		    	   out.println(crmMaster);
				
			}else if (action.equals("kaizenDateUpdateData_getExcel.kaizen")){
				CommonMessage.debugMsg("kaizenDateUpdateData_getExcel------------>");
	    	    //JSONObject colmodel = (JSONObject) httpSession.getAttribute("KznDateColModel");
				//JSONObject colmodel = (JSONObject) httpSession.getAttribute("KznDateColModel");
				CommonFilter commonFilter  = populateCommonFilter(request,"kaizenDateFilter",false);
	    	    List<String[]> kznDateUpDateDate = null;				
				kznDateUpDateDate = kaizenServices.getKznDateUpdateData(commonFilter);	
		    	JSONObject colmodel = getKaizenDateUpdateTableModelExcel(kznDateUpDateDate.get(1),kznDateUpDateDate.get(1),'M',true,true) ;
	    	    colmodel.put("title","Kaizen Date Update Report");	    	    
	    	  	String format = ExcelUtils.getFormat(request);
				Workbook wb;
				try 
				{
					
					wb  = kaizenServices.getKaizenDateUpdateExcel(colmodel,format,commonFilter);
					ExcelUtils.writeToResponse(response, wb,"KaizenDateUpdate ",format);
				} 
				catch (Exception e)
				{
						e.printStackTrace();
				}
				
			}

			else if(action.equals("kaizenDateUpdate_save.kaizen")){
				CommonMessage.debugMsg("Kaizen Date Update Action");
				try {					
					updateKizenDate(request, response, null);
				} catch (Exception e) {

				}
				
			}	
			else if (action.equals("KaizenHorizontalDeployment_input.kaizen"))
			{
				CommonMessage.debugMsg("KaizenHorizontalDeployment_input.kaizen");
				httpSession.setAttribute("KaizenFormMode", FormModes.modify);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				dispatchUrl = "/pages/HorizontalDeployment.jsp";
				CommonMessage.debugMsg("HorizontalDeployment.jsp");
			}
			else if( action.equals("KaizenHorizontalDeployment_getCol.kaizen")) 
			{
				try
				{
						//CommonFilter commonFilter = new CommonFilter();
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", true);
						ComboFilter cmbFactory = new ComboFilter();
						ComboFilter cmbSection = new ComboFilter();
						ComboFilter cmbMachine = new ComboFilter();
						ComboFilter cmbCell = new ComboFilter();
						ComboFilter cmbStatus = new ComboFilter();
						
						commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
						String factId = request.getParameter("factoryId");
						String sectId = request.getParameter("sectionId");
						String mchId = request.getParameter("machineID");
						String cellId = request.getParameter("cellId");
						String yyId= request.getParameter("yyId");
						String flid= request.getParameter("flid");
						String empkzn= request.getParameter("empkzn");
						String fromdate= request.getParameter("fromdate");
						String todate= request.getParameter("todate");
						String frommonth= request.getParameter("frommonth");
						String tomonth= request.getParameter("tomonth");
						
						//String docType=request.getParameter("DocType");
						//String docNo=request.getParameter("DocNo");

						String docId = request.getParameter("docId");
						
						String mode = request.getParameter("mode");
						CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
						commonFilter.setMainGroup(mode);
						
						httpSession.removeAttribute("yyIdKZN");
						httpSession.removeAttribute("docUpdatesId");
						if(UIUtils.isValidKeyId(docId))
						{
							httpSession.setAttribute("docUpdatesId",docId);
							cmbStatus.setId("A");
							commonFilter.setStatuss(cmbStatus);
						}
						if(UIUtils.isValidKeyId(yyId))
						{
							CommonMessage.debugMsg("yyId Inside="+yyId);
							httpSession.setAttribute("yyIdKZN",yyId);
						}
						
						if(UIUtils.isValidKeyId(factId))
						{	
							cmbFactory.setId(factId);
							commonFilter.setFactory(cmbFactory);
						}
						if(UIUtils.isValidKeyId(sectId))
						{
							cmbSection.setId(sectId);
							commonFilter.setSection(cmbSection);
						}
						if(UIUtils.isValidKeyId(mchId))
						{
							cmbMachine.setId(mchId);
							commonFilter.setMachine(cmbMachine);
						}
						if(UIUtils.isValidKeyId(cellId))
						{
							cmbCell.setId(cellId);
							commonFilter.setCell(cmbCell);
						}
						CommonMessage.debugMsg(" From Col "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
						CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
						if(UIUtils.isValidKeyId(empkzn))
						{
							commonFilter.setFlid(flid);
							commonFilter.setKAIZEN(empkzn);
							commonFilter.setFromDate(fromdate);
							commonFilter.setToDate(todate);
							commonFilter.setFromMonth(frommonth);
							commonFilter.setToMonth(tomonth);
							
						}
						commonFilter.setAbnDetectBy(user.getUsrm_ccno());
						
						String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
						ComboFilter jhKaizenCategoryObj=new ComboFilter();
						if( jhKaizenCategoryObj != null)
							jhKaizenCategoryObj.setId(jhKaizenCategory);
						commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
						//httpSession.removeAttribute("kaizenCommonFilterVal");
						//httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
					CommonMessage.debugMsg("Before List ");
					PrintWriter out = response.getWriter();	
					//------------------------
					String empKeyId=request.getParameter("empKeyId");
					
					CommonMessage.debugMsg("Before Inside modify "+empKeyId);
					
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					
					List< String[]>kaizenReportList  = kaizenServices.getAllHorizontalDeploy(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setPaginate(true);
					
				//	jqGridTableModel.setGroupBy(true);
				//	jqGridTableModel.setGroupByField("");
					
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = kaizenReportList.get(2);			
					String [] colHeaderCond = kaizenReportList.get(1);
					
					//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					
					jsonObject.put("tableHeight", "90%%");
					jsonObject.put("tableWidth", "108%%");
					httpSession.setAttribute("KaizenColModel", jsonObject);
					out.println(jsonObject);
					/*String kznImprProjectColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders", "kznModification");
					out.print(kznImprProjectColM);*/
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
	  		    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("KaizenHorizontalDeployment_getData.kaizen"))
			{
				try 
				{
					PrintWriter out = response.getWriter();
					//CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("kaizenCommonFilterVal");
					CommonFilter commonFilter=populateCommonFilter(request, "kaizenCommonFilterVal", false);
					String empKeyId=request.getParameter("empKeyId");
					String flid=request.getParameter("flid");
					String empkzn= request.getParameter("empkzn");
					String fromdate= request.getParameter("fromdate");
					String todate= request.getParameter("todate");
					String frommonth= request.getParameter("frommonth");
					String tomonth= request.getParameter("tomonth");
					CommonMessage.debugMsg(" From Data "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
					CommonMessage.debugMsg(frommonth+" tomonth :: "+tomonth);
					if(UIUtils.isValidKeyId(empkzn))
					{
						commonFilter.setFlid(flid);
						commonFilter.setKAIZEN(empkzn);
						commonFilter.setFromDate(fromdate);
						commonFilter.setToDate(todate);
						commonFilter.setFromMonth(frommonth);
						commonFilter.setToMonth(tomonth);
					}
					
					String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
					ComboFilter jhKaizenCategoryObj=new ComboFilter();
					if( jhKaizenCategoryObj != null)
						jhKaizenCategoryObj.setId(jhKaizenCategory);
					commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
					//commonFilter = FilterValues.getCommonFilters(request, commonFilter);
					commonFilter.setAbnDetectBy(user.getUsrm_ccno());
					
					//------------------------
					ComboFilter employee=new ComboFilter();
					employee.setId(empKeyId);
					commonFilter.setEmployee(employee);
					///-----------------
					List< String[]>kaizenReportList  = kaizenServices.getAllHorizontalDeploy(commonFilter);
					JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList,request,3,0,commonFilter.getTotalRecordCnt());
					out.println(kaizenReportData);
					httpSession.removeAttribute("kaizenCommonFilterVal");
					httpSession.setAttribute("kaizenCommonFilterVal", commonFilter);
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			/*if (action.equals("kaizen_input.kaizen")) 
			{
				//dispatchUrl = "/pages/ImprovementProject.jsp";
				dispatchUrl = "/pages/KaizenIdeaSheetNew.jsp";
				//dispatchUrl = "/pages/KaizenIdeaSheetImprovemnt.jsp";
			}*/

			if (dispatchUrl != null)
			{
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
				
		}
	    
	   private String getFileName(String kznKeyid) throws Exception {
			// TODO Auto-generated method stub
		   String fileName  = kaizenServices.getFileName(kznKeyid);
			return fileName;
		}
	private  JSONObject addJsonRows()
	   {
		   JSONArray rowArr = new JSONArray();
		   JSONObject beforeRowObj =new JSONObject();
		   beforeRowObj.put("id",1);
		   JSONArray cell=new JSONArray();
		   cell.put("L");
		   cell.put("BEFORE");
		   for(int i=0;i<=12;i++)
		   {	   
			   cell.put("0");
		   }
		   beforeRowObj.put("cell", cell);
		   rowArr.put(beforeRowObj);
		   
		   JSONObject afterRowObj =new JSONObject();
		   afterRowObj.put("id",2);
		  
		   JSONArray cell2=new JSONArray();
		   cell2.put("L");
		   cell2.put("AFTER");
		   for(int i=0;i<=12;i++)
		   {	   
			   cell2.put("0");
		   }
		   afterRowObj.put("cell", cell2);
		   rowArr.put(afterRowObj);
		   
		   JSONObject tableDataObject = new JSONObject();
		   tableDataObject.put("rows", rowArr);
		   
		   CommonMessage.debugMsg("tableDataObject NEWEWE"+tableDataObject);
		   return tableDataObject;
	   }
        private void KaizenDelete(HttpServletRequest request,HttpServletResponse response) throws Exception{
        	HttpSession httpsession=request.getSession(false);
        	ServletOutputStream out=response.getOutputStream();
        	String kaizenId=request.getParameter("kaizenId");
        	CommonMessage.debugMsg(kaizenId);
        	String KznMode=request.getParameter("kznMode");
        	KznTlMst existKznTlMst = (KznTlMst)httpsession.getAttribute("newSession");
        	KznTlMst newKznTlMst = new KznTlMst ();
        	
        	if(KznMode!=null){
        		newKznTlMst.setKznmKeyid(kaizenId);
        	}
        	
        	newKznTlMst=(KznTlMst)UIUtils.setBeanProperties((Object)newKznTlMst,request);
        	existKznTlMst = kaizenServices.deleteKzn(newKznTlMst);
        	JSONObject successData = new JSONObject();
    		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
    		JSONObject returnData = new JSONObject();
    		returnData.put("formClear",false);
    		returnData.put("successData", successData);				
    		out.print(returnData.toString());
        	
        }
		private List<KznTlPillarlink> populatePillarLinkList(List<String[]> pillarLinks)
	    {
	    	 List<KznTlPillarlink> pillarLinkList = new ArrayList<KznTlPillarlink>();
	    	 CommonMessage.debugMsg("Inside populatePillarLinkList"+pillarLinks.size());
	    	 for( String [] row : pillarLinks)
	 		 {
	    		 KznTlPillarlink kznTlPillarlink = new KznTlPillarlink();
	    		 kznTlPillarlink.setDbMode(row[6]);//1
	    		 kznTlPillarlink.setKzplKaizenid(row[5]);
	    		 kznTlPillarlink.setKzplCreatedon(row[2]);
	    		 kznTlPillarlink.setKzplTpmpillarid(row[0]);//0
	    		 kznTlPillarlink.setKzplKzncategoryid(row[7]);//2
	    		 pillarLinkList.add(kznTlPillarlink);
	 		 }
	    	 CommonMessage.debugMsg(pillarLinkList);
	    	 return pillarLinkList;
	    }
	    
	    private void fillCheckboxes( KaizenFormBean kaizenFormBean, KznTlMst kznTlMst)
	    {
	    	
	    	String isHDPossible=kznTlMst.getKznmIshdpossible();
			if(isHDPossible!=null)
			{
				if(isHDPossible.substring(0,1).equals("Y"))
					kaizenFormBean.setHdRequiredY("Y");
				else if(isHDPossible.substring(0,1).equals("N"))
					kaizenFormBean.setHdRequiredN("N");	
			}
			String isIndiviGroup=kznTlMst.getKznmIdeagroupindividual();
			if(isIndiviGroup!=null)
			{
				if(isIndiviGroup.substring(0,1).equals("G"))
					kaizenFormBean.setIdeagroupindividualG("G");
				else if(isIndiviGroup.substring(0,1).equals("I"))
					kaizenFormBean.setIdeagroupindividualI("I");	
			}
			
			
			String isWORequired=kznTlMst.getKznmIsworequired();
			if(isWORequired!=null)
			{
				if(isWORequired.substring(0,1).equals("Y"))
					kaizenFormBean.setWoRequiredY("Y");
				else if(isWORequired.substring(0,1).equals("N"))
					kaizenFormBean.setWoRequiredN("N");	
			}
			
			String isKznReversible=kznTlMst.getKznmReversibleirreversible();
			if(isKznReversible!=null)
			{
				if(isKznReversible.substring(0,1).equals("R"))
					kaizenFormBean.setReversible("R");
				else if(isKznReversible.substring(0,1).equals("I"))
					kaizenFormBean.setIrreversible("I");	
			}
			
			String isKznChanging=kznTlMst.getKznmIsprovidingchanging();
			if(isKznChanging!=null)
			{
				if(isKznChanging.substring(0,1).equals("P"))
					kaizenFormBean.setProviding("P");
				else if(isKznChanging.substring(0,1).equals("C"))
					kaizenFormBean.setChanging("C");	
			}
			
			String resultArea =kznTlMst.getKznmResultarea();
			CommonMessage.debugMsg("resultArea ="+resultArea);
			if(resultArea!=null)
			{
				if(resultArea.indexOf("Q")!=-1) //resultArea.substring(0, 1).equals("P")
					kaizenFormBean.setResultAreaP("P");
				if(resultArea.indexOf("Q")!=-1)
					kaizenFormBean.setResultAreaQ("Q");
				if(resultArea.indexOf("C")!=-1)
					kaizenFormBean.setResultAreaC("C");
				if(resultArea.indexOf("D")!=-1)
					kaizenFormBean.setResultAreaD("D");
				if(resultArea.indexOf("S")!=-1)
					kaizenFormBean.setResultAreaS("S");
				if(resultArea.indexOf("M")!=-1)
					kaizenFormBean.setResultAreaM("M");
				if(resultArea.indexOf("E")!=-1)
					kaizenFormBean.setResultAreaE("E");
			}
			
			String resultAreaSec =kznTlMst.getKznmResultareasec();
			CommonMessage.debugMsg(kznTlMst.getKznmFlid()+" resultAreaSEC ="+resultAreaSec);
			if(resultAreaSec!=null)
			{
				if(resultAreaSec.indexOf("P")!=-1)
					kaizenFormBean.setResultAreaSecP("P");
				if(resultAreaSec.indexOf("Q")!=-1)
					kaizenFormBean.setResultAreaSecQ("Q");
				if(resultAreaSec.indexOf("C")!=-1)
					kaizenFormBean.setResultAreaSecC("C");
				if(resultAreaSec.indexOf("D")!=-1)
					kaizenFormBean.setResultAreaSecD("D");
				if(resultAreaSec.indexOf("S")!=-1)
					kaizenFormBean.setResultAreaSecS("S");
				if(resultAreaSec.indexOf("M")!=-1)
					kaizenFormBean.setResultAreaSecM("M");
				if(resultAreaSec.indexOf("E")!=-1)
					kaizenFormBean.setResultAreaSecE("E");
			}
	    }
	    
	    
	    private void fillKaizenData(KaizenFormBean kaizenFormBean, KznTlMst kznTlMst)
	    {
	    	kznTlMst.setKznmBenchmark(kznTlMst.getKznmBenchmark().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmTheme(kznTlMst.getKznmTheme().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmOperations(kznTlMst.getKznmOperations().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmTeammembers(kznTlMst.getKznmTeammembers().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmRefdoctype(kznTlMst.getKznmRefdoctype().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmRefdocno(kznTlMst.getKznmRefdocno().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmPresentproblem(kznTlMst.getKznmPresentproblem().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmCountermeasure(kznTlMst.getKznmCountermeasure().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmIdea(kznTlMst.getKznmIdea().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmResultdescription(kznTlMst.getKznmResultdescription().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmHowtosustain(kznTlMst.getKznmHowtosustain().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmWhattosustain(kznTlMst.getKznmWhattosustain().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmBenefits(kznTlMst.getKznmBenefits().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmRootcause(kznTlMst.getKznmRootcause().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmRemarks(kznTlMst.getKznmRemarks().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmSustainfreq(kznTlMst.getKznmSustainfreq().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmCostcentreid(kznTlMst.getKznmCostcentreid().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmCircleid(kznTlMst.getKznmCircleid().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmMaterialcost(kznTlMst.getKznmMaterialcost().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmTotalcost(kznTlMst.getKznmTotalcost().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmLabourcost(kznTlMst.getKznmLabourcost().replace("<*", "").replace("*>", ""));
	    	kznTlMst.setKznmMachineid(kznTlMst.getKznmMachineid().replace("{", "").replace("}", ""));
	    	kznTlMst.setKznmApproveddate(kznTlMst.getKznmApproveddate().substring(0,11));
	    	kznTlMst.setKznmCompleteddate(kznTlMst.getKznmCompleteddate().substring(0,11));

	    	kznTlMst.setKznmDate(kznTlMst.getKznmDate().substring(0,11));
	    	kznTlMst.setKznmPrepareddate(kznTlMst.getKznmPrepareddate().substring(0,11));
	    	kznTlMst.setKznmStartdate(kznTlMst.getKznmStartdate().substring(0,11));
	    	kznTlMst.setKznmEnddate(kznTlMst.getKznmEnddate().substring(0,11));
	    }
	    
	    private JSONObject getTableModel(List<String[]> headers)
		{
	    	CommonMessage.debugMsg("Inside  getTableModel");
	    	JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	    	CommonMessage.debugMsg("headers ="+headers.size());
	    	CommonMessage.debugMsg("headers ="+headers.get(0)[0]);
	    	CommonMessage.debugMsg("headers ="+headers.get(0)[1]);
			String [] colHeader = headers.get(0);			
		   // colHeader[1] = caption;  
			jqGridTableModel.getRowHeaders().add(colHeader);
		
			jqGridTableModel.setTableButton(false);	
			jqGridTableModel.setTableWidth(580);
			jqGridTableModel.setTableHeight(90);
			jqGridTableModel.setCellSubmitLocal(true);
			jqGridTableModel.setCellEdit(true);
				
				//jqGridColModel = new JqGridColModel();
				for(int i =0; i < colHeader.length; i++)
				{
					JqGridColModel jqGridColModel = new JqGridColModel();
					jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
					jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
					
					jqGridColModel.setWidth( 100);				
					jqGridColModel.setAlign("right");
					jqGridColModel.setEditable(false);
					if(i==0 )
					{
						jqGridColModel.setIndex("txtkzgdCharttype");
						jqGridColModel.setName("txtkzgdCharttype");
						jqGridColModel.setHidden(true);
						jqGridColModel.setKey(false);
					}
					if(i==1)
					{
						jqGridColModel.setWidth( 150);	
						jqGridColModel.setIndex("MonthYr");
						jqGridColModel.setName("MonthYr");
						jqGridColModel.setHidden(false);
						jqGridColModel.setAlign("left");
						jqGridColModel.setEditable(false);
					}
					if(i>1)
					{
						jqGridColModel.setWidth( 120);				
						jqGridColModel.setAlign("right");
						jqGridColModel.setEditable(true);
					}
			
					jqGridTableModel.getColModel().add(jqGridColModel);
				}
				
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			
			return tableModel;
	      }
	    
		private JqGridColModel getColModel (String colIndex, int width,String allign)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex);
			jqGridColModel.setName(colIndex);
			jqGridColModel.setWidth( width);					
			jqGridColModel.setAlign(allign);
			jqGridColModel.setEditable(true);
			
			return jqGridColModel;
		}
		
	


     private void workflowTrans(String action , HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		CommonMessage.debugMsg(" The action  "+ action);
		if(action.equals("SimplifiedKaizenApproval_input.kaizen")){
			CommonMessage.debugMsg("Inside the Action");
			request.setAttribute("empId", request.getParameter("empId"));
			request.setAttribute("refId", request.getParameter("refId"));
			request.setAttribute("refType", request.getParameter("refType"));
			request.setAttribute("refRoleId", request.getParameter("refRoleId"));
			request.setAttribute("transCode", request.getParameter("transCode"));
			request.setAttribute("flId", request.getParameter("flId"));
			request.setAttribute("minDate", request.getParameter("minDate"));
			request.setAttribute("maxDate", request.getParameter("maxDate"));
			request.setAttribute("enable", request.getParameter("enable"));
			UIUtils.forwardRequest(request, response, "/pages/SimplifiedKaizenApprovalGrid.jsp");
		}
     }
	    private void saveKaizen(HttpServletRequest request, HttpServletResponse response ) throws BusinessApplicationExceptions,IOException
	    {
	  		HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String saveMode=request.getParameter("saveMode");
	    	CommonMessage.debugMsg("The Save Mode"+saveMode);
	    	String status = request.getParameter("status");
	    	String Type = request.getParameter("Type");
	    	CommonMessage.debugMsg("Type Is"+Type);
	    	String apprvallevel = request.getParameter("apprvallevel");
	    	String avdsucmsg=request.getParameter("avdsucmsg");
	    	String nextlevel = request.getParameter("Approvallevel");
	    	String hdnstatus=request.getParameter("hdnKznmStatus");
	    	String hdnaprrovlevel=request.getParameter("hdnApprovLevel");
	    	String formmode=request.getParameter("frmMode");
	    	
	    	CommonMessage.debugMsg(" status :: "+status+" apprvallevel :: "+apprvallevel+" nextlevel "+nextlevel);
	    	
	    	KaizenFormBean newkaizenFormBean =new KaizenFormBean ();
	    	if( httpSession != null && user != null)
	    	{	
	    		//KznTlMst existKznTlMst = (KznTlMst)httpSession.getAttribute("kznTlMst"); 
	    		
	    		KznTlMst newKznTlMst = new KznTlMst();
				newKznTlMst.setKznmCreatedby(user.getUsrm_ccno());
				
				newKznTlMst =(KznTlMst)UIUtils.setBeanProperties((Object)newKznTlMst,request);
				newkaizenFormBean =(KaizenFormBean) UIUtils.setBeanProperties((Object)newkaizenFormBean,request);
				CommonMessage.debugMsg("kaizen bank      "+newKznTlMst.getKznmKzbnkeyid());
				KznTlMst existKznTlMst = (KznTlMst)httpSession.getAttribute("kznTlMst"+ newKznTlMst.getKznmKeyid());    		 
				KaizenFormBean kaizenFormBean = (KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
				/**------------------ For Saving Improvement PillarLink ---------------- **/
				
				CommonMessage.debugMsg("kaizenFormBean.getKznmAnalys()11"+newKznTlMst.getKznmAnalysis());
				if(UIUtils.isValidKeyId(status)){
					if("C".equals(status))
						newKznTlMst.setKznmStatus("C");
					else if("A".equals(status))
						newKznTlMst.setKznmStatus("A");
					else if("R".equals(status))
						newKznTlMst.setKznmStatus("R");
					else 
						newKznTlMst.setKznmStatus("P");
				}
				else 
					newKznTlMst.setKznmStatus("-");
				
				if(UIUtils.isValidKeyId(nextlevel)){ 
					newKznTlMst.setKznmApprovLevel(nextlevel);
				}else {
			    	newKznTlMst.setKznmApprovLevel("-");
			    }
				
				List<KznTlPillarlink> pillarLnkList =null;
				KznTlPillarlink kznTlPillarlink = new KznTlPillarlink();
				
				String openactnpln = request.getParameter("openactnpln");
				
				String filemanager = request.getParameter("filemanager");
				
	    		String pillarlinkStr =  request.getParameter("KznPillarLink");
	    		
	    		JSONArray pillarLinkJSON =null;
	    		if( pillarlinkStr != null && ! pillarlinkStr.isEmpty())
	    		{
	    			pillarLinkJSON = JSONArray.fromString(pillarlinkStr);
	    			pillarLnkList=(List<KznTlPillarlink>)UIUtils.convertJSONArrToList(kznTlPillarlink, pillarLinkJSON);
	    		}
	    		
				if( pillarLnkList != null)
					newKznTlMst.setPillarLink(pillarLnkList);
				/** --------------------------------------------------------------------------- **/
				
				/**---------------- For Saving KAIZEN LOSSLINK----------------------------- **/
				List<KznTlLosslink> lossLinkList =null;
				KznTlLosslink kznTlLosslink = new KznTlLosslink();
	    		String losslinkStr =  request.getParameter("KznLossLink");
	    		JSONArray lossLinkJSON =null;
	    		if( losslinkStr != null && ! losslinkStr.isEmpty())
	    		{
	    			
	    			lossLinkJSON = JSONArray.fromString(losslinkStr);
	    			lossLinkList=(List<KznTlLosslink>)UIUtils.convertJSONArrToList(kznTlLosslink, lossLinkJSON);
	    		}
	    		
				if( lossLinkList != null)
					newKznTlMst.setLossLink(lossLinkList);
				
				/** ------------------------------------------------------------------------- **/
   	 			
				/**-----------------------For Saving KAIZEN GRAPH DATA-------------------------**/
				
				List<KznTlGraphdata> kznGraphDataList =null;
				KznTlGraphdata kznTlGraphdata = new KznTlGraphdata();
	    		String kznGraphdataStr =  request.getParameter("KznGraphData");
	    		CommonMessage.debugMsg("kznGraphdataStr ="+kznGraphdataStr);
	    		JSONArray kznGraphDataJSON =null;
	    		if( kznGraphdataStr != null && ! kznGraphdataStr.isEmpty())
	    		{
	    			kznGraphDataJSON = JSONArray.fromString(kznGraphdataStr);
	    			CommonMessage.debugMsg("kznGraphDataJSON ="+kznGraphDataJSON);
	    			kznGraphDataList=(List<KznTlGraphdata>)UIUtils.convertJSONArrToList(kznTlGraphdata, kznGraphDataJSON);
	    			CommonMessage.debugMsg("kznGraphDataList ="+kznGraphDataList.get(0).getKzgdDatemonthyear());
	    			CommonMessage.debugMsg("kznGraphDataList ="+kznGraphDataList.get(0).getKzgdCharttype());
	    			CommonMessage.debugMsg("kznGraphDataList ="+kznGraphDataList.get(0).getKzgdBeforedata());
	    		}
	    		
				if( kznGraphDataList != null)
					newKznTlMst.setGraphData(kznGraphDataList);
							
				/**-----------------------------------------------------------------------------**/
				
				
				/**-------------------------For Saving Image -------------------------------------**/
				
				String imgKznPresentImgFilename= request.getParameter("imgKznPresentImgFilename");
				String imgKznAfterImgFilename =request.getParameter("imgKznAfterImgFilename");
				String imgKznResultImgFilename=request.getParameter("imgKznResultImgFilename");
				String imgKznBenefitImgFilename=request.getParameter("imgKznBenefitImgFilename");
				CommonMessage.debugMsg("imgKznAfterImgFilename="+imgKznAfterImgFilename);
				CommonMessage.debugMsg("imgKznPresentImgFilename="+imgKznPresentImgFilename);
				String imagePath = UIUtils.getImagePath(request);
				
				List <GenTlAllmoduleimgfile> imgfileList =new ArrayList<GenTlAllmoduleimgfile>();
				
				if( imgKznPresentImgFilename != null){
					GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
					genTlAllmoduleimgfile.setImflBlobimage(imagePath );
					genTlAllmoduleimgfile.setImflFilename(imgKznPresentImgFilename);
					genTlAllmoduleimgfile.setImflImagetype("PRE");
					genTlAllmoduleimgfile.setImflRefdoctype("KZN");
					imgfileList.add(genTlAllmoduleimgfile);
					/*if(!imgKznPresentImgFilename.trim().equals(""))
						newKznTlMst.setKznmPresentimage(imgKznPresentImgFilename);*/
					CommonMessage.debugMsg(" genTlAllmoduleimgfile.setToimBlobimage( " + genTlAllmoduleimgfile.getImflBlobimage());
				}
				
				if( imgKznAfterImgFilename != null){
					GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
					genTlAllmoduleimgfile.setImflBlobimage(imagePath );
					genTlAllmoduleimgfile.setImflFilename(imgKznAfterImgFilename);
					genTlAllmoduleimgfile.setImflImagetype("AFT");
					genTlAllmoduleimgfile.setImflRefdoctype("KZN");
					/*if(!imgKznAfterImgFilename.trim().equals(""))
						newKznTlMst.setKznmAfterimage(imgKznAfterImgFilename);*/
					imgfileList.add(genTlAllmoduleimgfile);
				}
				
				if( imgKznBenefitImgFilename != null){
					GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
					genTlAllmoduleimgfile.setImflBlobimage(imagePath );
					genTlAllmoduleimgfile.setImflFilename(imgKznBenefitImgFilename);
					genTlAllmoduleimgfile.setImflImagetype("BEN");
					genTlAllmoduleimgfile.setImflRefdoctype("KZN");
					/*if(!imgKznAfterImgFilename.trim().equals(""))
						newKznTlMst.setKznmAfterimage(imgKznAfterImgFilename);*/
					imgfileList.add(genTlAllmoduleimgfile);
				}
				
				if( imgKznResultImgFilename != null){
					GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
					genTlAllmoduleimgfile.setImflBlobimage(imagePath );
					genTlAllmoduleimgfile.setImflFilename(imgKznResultImgFilename);
					genTlAllmoduleimgfile.setImflImagetype("RES");
					genTlAllmoduleimgfile.setImflRefdoctype("KZN");
					CommonMessage.debugMsg("imgKznResultImgFilename Inside = "+imgKznResultImgFilename);
					/*if(!imgKznResultImgFilename.trim().equals(""))
						newKznTlMst.setKznmResultimage(imgKznResultImgFilename);*/
					imgfileList.add(genTlAllmoduleimgfile);
				}
				
				newKznTlMst.setAllmoduleimgfile(imgfileList) ;	
				
				List<String> kznimageType =(List<String>)httpSession.getAttribute("kznimageType");
			
				/**---------------------------------------------------------**/
				List<String[]> chkForDuplicatesList =new ArrayList<String[]>();
				
				CommonMessage.debugMsg("FAXC"+newKznTlMst.getKznmFactoryid());
				/*try{
					//CommonMessage.debugMsg("newKznTlMst ="+newKznTlMst);
					if(UIUtils.isValidKeyId(newKznTlMst.getKznmFactoryid())&& UIUtils.isValidKeyId(newKznTlMst.getKznmMachineid())&&
							UIUtils.isValidKeyId(newKznTlMst.getKznmCellid())&&UIUtils.isValidKeyId(newKznTlMst.getKznmTheme())&&
							UIUtils.isValidKeyId(newKznTlMst.getKznmBenchmark())&&UIUtils.isValidKeyId(newKznTlMst.getKznmTarget()))
						{
						//CommonMessage.debugMsg("newKznTlMst.getKznmKeyid():"+newKznTlMst.getKznmKeyid());
						
							chkForDuplicatesList=kaizenServices.chkForDuplicates(newKznTlMst);}
				}
				catch(Exception e)
				{
					e.getMessage();
					e.printStackTrace();
				}
				*/
				
				try{
					String dup="";
					String docId=(String)httpSession.getAttribute("docUpdatesId");
					String yyId=(String)httpSession.getAttribute("yyIdKZN");
					String cucdkeyId=(String)httpSession.getAttribute("qtmmode");
					CommonMessage.debugMsg(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+docId + yyId + cucdkeyId);
					if(UIUtils.isValidKeyId(yyId))
						newkaizenFormBean.setYyId(yyId);
					
					if(UIUtils.isValidKeyId(docId))
						newkaizenFormBean.setDocId(docId);
					
					if(UIUtils.isValidKeyId(cucdkeyId))
						newkaizenFormBean.setCucdkeyId(cucdkeyId);
					
					//CommonMessage.debugMsg(" Checking Approval Data :: "+existKznTlMst.getKznmApprovLevel());
					//if(chkForDuplicatesList != null && chkForDuplicatesList.size() <= 0)
					//{	
						if(!(kaizenFormBean.getFormActionMode().equals("View")||kaizenFormBean.getFormActionMode().equals("Completed")))
						{
							CommonMessage.debugMsg("fliddd: "+newKznTlMst.getKznmFlid());
							CommonMessage.debugMsg("getKaizenId: "+newkaizenFormBean.getKaizenId());
							if( newKznTlMst.getKznmKeyid() == null )
							{	
								existKznTlMst =	kaizenServices.create(newKznTlMst,existKznTlMst,newkaizenFormBean,apprvallevel);
							}	
							else
							{
								//newKznTlMst.get
								if(formmode.equals("modify"))
								{
								   if(hdnstatus.equals("R"))
								   {
									   newKznTlMst.setKznmStatus("P");
										newKznTlMst.setKznmApprovLevel("-");
								   }
								}else if(formmode.equals("create"))
								{
										   newKznTlMst.setKznmStatus("-");
											newKznTlMst.setKznmApprovLevel("-");
									}
								else{
									if(UIUtils.isValidKeyId(hdnstatus)) {
										newKznTlMst.setKznmStatus(hdnstatus);
									}
									if(UIUtils.isValidKeyId(hdnaprrovlevel)) {
										newKznTlMst.setKznmApprovLevel(hdnaprrovlevel);
									}
								 
								 
								}
								
								existKznTlMst = kaizenServices.update(newKznTlMst,existKznTlMst,newkaizenFormBean,apprvallevel);
							}
							//if(UIUtils.isValidKeyId(yyId))
							//	{CommonMessage.debugMsg("docId ="+docId);
							//	kaizenServices.updateDocUpdates(existKznTlMst,docId,yyId);}
						}
					//}
					///els
					//{
					//	 dup=chkForDuplicatesList.get(0)[0];
					//	 dup="Similar data found in doc no :"+dup;
					//}
					
					//httpSession.setAttribute(existKznTlMst.getKznmKeyid(), existKznTlMst);
					httpSession.setAttribute("kznTlMst", existKznTlMst);
					String formBeanIdentifier = "kaizenFormBean"+kaizenFormBean.getFormActionMode();
					httpSession.setAttribute(formBeanIdentifier,kaizenFormBean);
					String msgPropertyIdnt = "success-save";
					Boolean clrVal=true;
				
					CommonMessage.debugMsg(" avdsucmsg :: Checking :: Before ::  "+avdsucmsg);
					CommonMessage.debugMsg("kaizenFormBean.getFormActionMode()"+kaizenFormBean.getFormActionMode());
					if(kaizenFormBean.getFormActionMode().equals("Modify")&&(!UIUtils.isValidKeyId(avdsucmsg)))
						msgPropertyIdnt = "success-update";
					else if(kaizenFormBean.getFormActionMode().equals("Completed")&&(!UIUtils.isValidKeyId(avdsucmsg)))
						{msgPropertyIdnt="cmp-save"; clrVal=false;}
					else if(kaizenFormBean.getFormActionMode().equals("View")&&(!UIUtils.isValidKeyId(avdsucmsg)))
						msgPropertyIdnt = "vw-save";
					else if(kaizenFormBean.getFormActionMode().equals("Create")&&(!UIUtils.isValidKeyId(avdsucmsg)))
						msgPropertyIdnt = "success-save";
					
					JSONObject returnData = new JSONObject();
										
					JSONObject successData = new JSONObject();
					successData.put("kznKeyid",newKznTlMst.getKznmKeyid() );
					//successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					
					if(dup.isEmpty())
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					else
						{throw new BusinessApplicationExceptions(dup);}
						//{successData.put("msg", dup);clrVal=false;successData.put("chkDuplicate", true);CommonMessage.debugMsg("Inside Msgs");}
					
					
                     if(UIUtils.isValidKeyId(openactnpln)){	
						successData.put("flid", newKznTlMst.getKznmFlid());
						successData.put("openactnpln", true);
						
					}else
					{
						successData.put("openactnpln", false); 
					}
					
                     
                     if(UIUtils.isValidKeyId(filemanager)){	
 						
 						successData.put("filemanager", true);
 						
 					}else
 					{
 						successData.put("filemanager", false); 
 					}
                     
                     
					if(existKznTlMst!=null)	
					{
						successData.put("mode",kaizenFormBean.getFormActionMode());
						if(UIUtils.isValidKeyId(existKznTlMst.getKznmKeyid()))
							successData.put("keyId", existKznTlMst.getKznmKeyid());
						
						JSONObject persistentData = new JSONObject(); 
						if(UIUtils.isValidKeyId(existKznTlMst.getKznmKeyid()))
							persistentData.put("kznKeyid",existKznTlMst.getKznmKeyid() );
						//persistentData.put("formBean", formBeanIdentifier);
						JSONObject forwardData = new JSONObject();
						if(kaizenFormBean.getFormActionMode().equals("whywhy"))
						{
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmKeyid()))
								forwardData.put("cmbwwmsRefdocno",existKznTlMst.getKznmKeyid());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmDate()))
								forwardData.put("dtewwmsDate",existKznTlMst.getKznmDate());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmMachineid()))
								forwardData.put("cmbwwmsMachineid",existKznTlMst.getKznmMachineid());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmAssemblyid()))
								forwardData.put("cmbwwmsAssemblyid",existKznTlMst.getKznmAssemblyid());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmFactoryid()))
								forwardData.put("cmbwwmsFactoryid",existKznTlMst.getKznmFactoryid());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmSectionid()))
								forwardData.put("cmbwwmsSectionid",existKznTlMst.getKznmSectionid());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmCellid()))
								forwardData.put("cmbwwmsCellid",existKznTlMst.getKznmCellid());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmRootcause()))
								forwardData.put("cmbwwmsRootcause",existKznTlMst.getKznmRootcause());
							//if(UIUtils.isValidKeyId(existKznTlMst.getKznmWwmsKeyid()))
							//	forwardData.put("cmbwwmsWwmsKeyid",existKznTlMst.getKznmWwmsKeyid());
							if(UIUtils.isValidKeyId(existKznTlMst.getKznmWwmsKeyid()))
								forwardData.put("txtwwmsKeyid",existKznTlMst.getKznmWwmsKeyid());
							
							forwardData.put("txtformType","Kaizen");
						}
						forwardData.put("kznKeyid",existKznTlMst.getKznmKeyid());
						
						if(kaizenFormBean.getFormActionMode().equals("hdScan"))
						{
							forwardData.put("kznAssemblyid",existKznTlMst.getKznmAssemblyid().replace("{}",""));
							forwardData.put("kznPhenomenaid",existKznTlMst.getKznmPhenomenaid().replace("{}",""));
							forwardData.put("kznCauseid",existKznTlMst.getKznmCauseid().replace("{}", ""));
						}
						
						returnData.put("persistentData",persistentData);
						returnData.put("forwardData",forwardData);
					}
					if(UIUtils.isValidKeyId(saveMode)){
						if(saveMode.contains("U"))
							clrVal=false;
					}
					clrVal=false;
					returnData.put("formClear",clrVal);
					String modeReturn =(String)httpSession.getAttribute("bdmmode");
					
					//JSONObject returnData = new JSONObject();
					//JSONObject successData = new JSONObject();
					successData.put("Type", Type);
					if( "workflow".equals(Type)){
						JSONObject record = new JSONObject();
						record.put("gridId", request.getParameter("gridId"));
						record.put("rowId", request.getParameter("rowId"));
						successData.put("record",record);
						returnData.put("displyMsg",false);
					}

					
					if(UIUtils.isValidKeyId(modeReturn))
						successData.put("successData", modeReturn);
					returnData.put("successData", successData);		
					returnData.put("saveMode", saveMode);
					
					//if(!UIUtils.isValidKeyId(avdsucmsg))
					    out.print(returnData.toString());
					//out.close();
				
					if(existKznTlMst!=null)
						kaizenServices.saveKznImg(existKznTlMst,kaizenFormBean);
					
				}
				
				catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "KaizenCreationExceptions");
					errMessage.put("fromMode",kaizenFormBean.getFormActionMode());
					out.print(errMessage.toString());
				}
				catch(BusinessApplicationExceptions e)
				{
					
					CommonMessage.debugMsg("Error Servler e -"+e.toString());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"KaizenCreationExceptions");
					if(e.getMessage().contains("UK_KZNM_KZBNKEYID")){
						errMessage.put("tpmException", "This Record is already exist");	
					}
					errMessage.put("tpmException", "This Record is already exist");
					errMessage.put("displyMsg", false);
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
	    
	    private void deleteKaizen(HttpServletRequest request, HttpServletResponse response) throws IOException
	    {
	 	    HttpSession httpSession = request.getSession(false);
	     	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	 	   	if( httpSession != null && user != null)
	 	   	{	
	 	   		KznTlMst newKznTlMst = new KznTlMst();
	 	   		newKznTlMst =(KznTlMst)UIUtils.setBeanProperties((Object)newKznTlMst,request);
	 	   		KaizenFormBean kaizenFormBean = (KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
			
	 	   		KznTlMst existKznTlMst = (KznTlMst)httpSession.getAttribute("kznTlMst"+newKznTlMst.getKznmKeyid()); 
	 	   		
	 	   
	 	  		String frmMode=kaizenFormBean.getFormActionMode();
	 	  		
	 			try
	 			{		
	 				if(UIUtils.isValidKeyId(newKznTlMst.getKznmKeyid()))
	 				{
	 					if(frmMode != null && !(frmMode.equals("View"))&& !(frmMode.equals("Completed")))
	 					{	existKznTlMst =	kaizenServices.delete(newKznTlMst);
	 						httpSession.removeAttribute("kznTlMst"+existKznTlMst.getKznmKeyid());
	 					}
	 				
	 					Boolean clrVal=true;
	 					String delMsg="success-delete";
	 					if(frmMode.equals("View"))
	 						{delMsg="vw-delete";clrVal=false;}
	 					else if (frmMode.equals("Completed"))
	 					{	delMsg = "cmp-delete"; clrVal=false;}
	 				
	 					JSONObject successData = new JSONObject();
	 					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",delMsg));
	 					successData.put("keyid", newKznTlMst.getKznmKeyid());
	 					successData.put("frmMode",frmMode);
	 					JSONObject returnData = new JSONObject();
	 					returnData.put("formClear",clrVal);
	 					returnData.put("displyMsg", true);
	 					returnData.put("successData", successData);
	 					
	 					out.print(returnData.toString());
	 				}
	 			}
	 			catch(Exception e)
	 			{
	 				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
	 				JSONObject err = new JSONObject();
	 				err.put("tpmException", "Data Not Saved");
	 				out.print(err.toString());
	 			}
	 	   	}	
	     }
	    
	    private void saveKaizenRejectReworkStatus(HttpServletRequest request,HttpServletResponse response)throws Exception{
	    	CommonMessage.debugMsg("Inside the saveKaizenRejectReworkStatus");
	    	HttpSession httpSession = request.getSession(false);
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	PrintWriter out = response.getWriter();
	    	String keyid = request.getParameter("kznKeyid");
	    	CommonMessage.debugMsg("The Kaizen Reject & Rework keyid"+keyid);
	    	String kznstatus = request.getParameter("kznStatus");
	    	CommonMessage.debugMsg("The Kaizen Status"+kznstatus);
    		KznTlMst newKznTlMst = new KznTlMst();
    		newKznTlMst.setKznmCreatedby(user.getUsrm_ccno());
	    	KaizenFormBean newkaizenFormBean =new KaizenFormBean ();

			newKznTlMst =(KznTlMst)UIUtils.setBeanProperties((Object)newKznTlMst,request);
			newkaizenFormBean =(KaizenFormBean) UIUtils.setBeanProperties((Object)newkaizenFormBean,request);
			CommonMessage.debugMsg("kaizen bank      "+newKznTlMst.getKznmKzbnkeyid());
			KznTlMst existKznTlMst = (KznTlMst)httpSession.getAttribute("kznTlMst"+ newKznTlMst.getKznmKeyid());
			newKznTlMst.setKznmKeyid(keyid);
			newKznTlMst.setKznmStatus(kznstatus);
	    	CommonMessage.debugMsg("The Kaizen KeyID"+keyid);
	    	try{
	    		if(UIUtils.isValidKeyId(keyid)){
	    			existKznTlMst=kaizenServices.updateKznRejRewStatus(newKznTlMst);
	    		//	existKznTlMst=kaizenBankService.updatekznsuggstatus(newkznTlKaizenbankmst);
	    			String msgPropertyIdnt = "success-update";
	    			JSONObject err = new JSONObject();
	    			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
	    			err.put("successData",mesg);
	    			CommonMessage.debugMsg(err.toString());
	    			out.print(err.toString());
	    		}
	    	}
	    	catch(Exception e)
	    	{
	    		CommonMessage.debugMsg("Exception: "+e);
	    		JSONObject err = new JSONObject();
	    		String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
	    		err.put("successData",mesg);
	    		CommonMessage.debugMsg(err.toString());
	    		out.print(err.toString());
	    	}
	    }
	    	
	    
	    private void recallKaizenComplete(HttpServletRequest request,HttpServletResponse response) throws Exception
	    {
				// TODO Auto-generated method stub
	    	String kznKeyid = request.getParameter("kznKeyid");
			//String khdmKaizenid = request.getParameter("khdmKaizenid");
	    	String khdmkeyid = request.getParameter("khdmkeyid");
			String isHdReq = request.getParameter("isHdReq");
			String selectedId=request.getParameter("selectedId");
			CommonMessage.debugMsg("isHdReq ="+isHdReq);
			CommonMessage.debugMsg("kznKeyid= "+kznKeyid+"khdmkeyid ="+khdmkeyid);
			FormModes  mode = FormModes.completion;
			KaizenFormBean kaizenFormBean = new KaizenFormBean(); 
			kaizenFormBean.setFormMode(mode);
			PrintWriter out = response.getWriter();
			try
			{
				if(UIUtils.isValidKeyId(kznKeyid ))
				{
					JSONObject returndata = new JSONObject();
					List<String[]> isResultPresent = kaizenServices.getResultData(kznKeyid);
					CommonMessage.debugMsg("isResultPresent ="+isResultPresent.get(0)[0]);
					CommonMessage.debugMsg("isResultPresent ="+isResultPresent.get(0)[2]);
					if(isResultPresent.get(0)[0].trim().equals("") && isResultPresent.get(0)[2].trim().equals(""))
					{
						CommonMessage.debugMsg("isResultPresent " +isResultPresent.size());
						returndata.put("resultData", true);//isResultPresent.size()
						returndata.put("msg","No Result Image or Description found Improvement cannot be Completed");
					}
					else
					{
						CommonMessage.debugMsg("ELSE   THIS>>>>>>>>>>>>>>>>");
						returndata.put("kznKeyid",kznKeyid);
						//returndata.put("khdmKaizenid",khdmKaizenid);
						returndata.put("khdmkeyid",khdmkeyid);
						returndata.put("isHdReq",isHdReq);
						returndata.put("selectedId", selectedId);
						returndata.put("resultData", false);
					}
			
						CommonMessage.debugMsg(returndata.toString());
						out.print(returndata.toString());
				}
			}
			
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
 				JSONObject err = new JSONObject();
 				err.put("tpmException", "Data Not Found");
 				out.print(err.toString());
			}
	    }
	    private void updateKaizenComplete(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    {	  
	    	KznTlMst kznTlMst = new KznTlMst();
	    	KznTlHdmst kznTlHdmst = new KznTlHdmst();
	    	kznTlMst =(KznTlMst)UIUtils.setBeanProperties((Object)kznTlMst,request);
	    	kznTlHdmst=(KznTlHdmst)UIUtils.setBeanProperties((Object)kznTlHdmst,request);
	    	PrintWriter out = response.getWriter();
			UIUtils.displayRequestParamsValue(request);
			String isHdReq = request.getParameter("hdnisHdReq");
			String khdmKeyid = request.getParameter("khdmKeyid");
			CommonMessage.debugMsg("isHdReq ="+isHdReq);
			try
			{
				CommonMessage.debugMsg("KZN KEYSID ==="+khdmKeyid);
				if(UIUtils.isValidKeyId(kznTlMst.getKznmKeyid()))	
				{
					kznTlMst= kaizenServices.updateKznCompletion(kznTlMst);
				
					if(UIUtils.isValidKeyId(khdmKeyid)&& isHdReq.equals("2"))
					{
						kznTlHdmst.setKhdmCompletedby(kznTlMst.getKznmCompletedid());
						kznTlHdmst.setKhdmCompleteddate(kznTlMst.getKznmCompleteddate());
						kznTlHdmst.setKhdmRemarks(kznTlMst.getKznmRemarks());
						kznTlHdmst.setKhdmKeyid(khdmKeyid);
						kznTlHdmst= kaizenServices.updateKznHDCompletion(kznTlHdmst);
					}
				
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update"));
					returnData.put("successData", successData);
					out.print(returnData.toString());	
				}
			}
			
			catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "KaizenCreationExceptions");
				CommonMessage.debugMsg("errMessage "+errMessage.toString());
				out.print(errMessage.toString());
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
 				JSONObject err = new JSONObject();
 				err.put("tpmException", "Data Not Saved");
 				out.print(err.toString());
			}
	    }
	    
	}
	    
	 
  @SuppressWarnings("unchecked")
  private void MultipleApprovalSave(HttpServletRequest request,HttpServletResponse response) throws IOException{
	 		// TODO Auto-generated method stub
	    HttpSession httpSession = request.getSession(false);
	    ServletOutputStream out = response.getOutputStream();
	 	AdmTlUsermst user = UIUtils.getLoginUser(request);
	 	String GridList=request.getParameter("paramJsonArrConvert");
	 	CommonMessage.debugMsg("The GridList"+GridList);
	 	String savemsg=null;
	 	String updateMsg=null;
       String RefType="KZNBTS";
	 	try{
		 	GenTlWorkflowInfo genTlWorkflowInfo = new GenTlWorkflowInfo(); 
	 		JSONArray ApprovalList=null;
	 		if(UIUtils.isValidKeyId(GridList)){
	 			ApprovalList=JSONArray.fromString(GridList);
	 		   List<GenTlWorkflowInfo> ApprovalDataList=(List<GenTlWorkflowInfo>)UIUtils.convertJSONArrToList(genTlWorkflowInfo,ApprovalList);		    		   
	 		   int i=0;
	 		   for(i=0;i<ApprovalDataList.size();i++)
	 		   {
	 			  
	 			  ApprovalDataList.get(i).setWrinEmployeeId(user.getUsrm_ccno());
	 			  ApprovalDataList.get(i).setWrinCreatedby(user.getUsrm_ccno()); 
	 			  ApprovalDataList.get(i).setWrinRefType(RefType);
	 		   }
	 		 
	 		    ApprovalDataList=kaizenServices.createMultipleApproval(ApprovalDataList);
	 		    updateMsg="Data Saved Successfully"; 
	 		    JSONObject SuccessData=new JSONObject();
	 	    	SuccessData.put("msg",updateMsg);
	 	    	JSONObject returnData=new JSONObject();
	 	    	returnData.put("formClear", false);
	 	    	returnData.put("successData", SuccessData);
	 	    	//returnData.put("savemode","Employee Attendance");
	 	    	out.print(returnData.toString());
	 		}
	 	}			
	 	catch (ValidationExceptions e) {
	 		net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "WorkFlowCreation");
	 		out.print(errMessage.toString());
	 	}
	 	catch(Exception e){
	 		e.printStackTrace();
	 		JSONObject err = new JSONObject();
	 		err.put("tpmException", "Data Not Saved");
	 		out.print(err.toString());
	 }
	 }     
	    
	    
	    
      
	    
	    private JSONObject getSimplifyKaizenTableModel(String[] colIndex,String[] colHeader,char type,boolean enableFilter,boolean tableButton  ) {//List<String[]> headers) {
	    	CommonMessage.debugMsg("colIndex :" +colIndex[0]+ "  " +colIndex[1]+ "    "+ colIndex[2]);
	    	CommonMessage.debugMsg("colHeader :" +colHeader[0]+ "  " +colHeader[1]+ "    "+ colHeader[2]);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(tableButton);
			jqGridTableModel.setEnableFilter(enableFilter);
			
			for (int i = 0; i < colIndex.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
				jqGridColModel.setName(jqGridColModel.getIndex());
				jqGridColModel.setWidth(150); 
				jqGridColModel.setAlign("left");
			    if(( i == 0 || i==10|| i==11)  ){
					 jqGridColModel.setHidden(true); 
				 }
			    if(( i == 1)  ){
					 jqGridColModel.setHidden(true); 
				 }
			    
				 if(( i == 2)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(70);
						jqGridColModel.setFormatter("checkBoxSimKzn");
			     }
				/* if(i==2){
				    jqGridColModel.setAlign("center");	
			    	jqGridColModel.setFormatter("submitBtn");
					jqGridColModel.setWidth(80);
				 }*/if(i==3||i==5||i==6){
					 jqGridColModel.setWidth(80);
				 }
				 if(i==4){
					 jqGridColModel.setWidth(150);
				 }
				 
				 
			/*	 if(( i == 9)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(160);
						jqGridColModel.setFormatter("cmbResponsbilityformatter");
					}*/

				 if(i==7){
					    jqGridColModel.setAlign("left");	
				    	jqGridColModel.setWidth(160);
				}
				 if(i==8){
					    jqGridColModel.setAlign("left");	
				    	jqGridColModel.setWidth(250);
				}
				
                    jqGridTableModel.getColModel().add(jqGridColModel);
				 
			}

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "85%%");
			
			return tableModel;
		}
	    
	    
	    private JSONObject getKaizenThemeUpdateTableModel(String[] colIndex,String[] colHeader,char type,boolean enableFilter,boolean tableButton  ) {//List<String[]> headers) {
	    	CommonMessage.debugMsg("colIndex :" +colIndex[0]+ "  " +colIndex[1]+ "    "+ colIndex[2]);
	    	CommonMessage.debugMsg("colHeader :" +colHeader[0]+ "  " +colHeader[1]+ "    "+ colHeader[2]);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(tableButton);
			jqGridTableModel.setEnableFilter(enableFilter);
//			jqGridTableModel.setPaginate(true);
			
			for (int i = 0; i < colIndex.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
				jqGridColModel.setName(jqGridColModel.getIndex());
				jqGridColModel.setWidth(150); 
				jqGridColModel.setAlign("left");
			    if(  ( i == 0)){
					 jqGridColModel.setHidden(true); 
				 }
			   /* if((  i==10|| i==11)  ){
					 jqGridColModel.setHidden(false); 
				 }*/
			   
			      
				 if(( i == 1)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(70);
						jqGridColModel.setFormatter("checkBoxKzn");
			     }
				  if(( i== 10 )){
			    	jqGridColModel.setAlign("center");
				    jqGridColModel.setWidth(250);
				    jqGridColModel.setFormatter("cmbkznmThemecategoryidformatter");
			    }
				 if(i==9){
					jqGridColModel.setHidden(false);
				    jqGridColModel.setAlign("center");	
			    	//jqGridColModel.setFormatter("submitBtn");
					jqGridColModel.setWidth(80);
				 }
				 if(i==9){
					 jqGridColModel.setWidth(160);
				 }
				 if(i==4|i==14){
					 jqGridColModel.setWidth(80);
				 }
				/* if(i==3||i==4||i==5||i==6){
					 jqGridColModel.setWidth(80);
				 }*/
				  
				 /*if(i==4||i==5||i==6){
				 jqGridColModel.setWidth(80);
			      }*/

				 
			/*	 if(( i == 10)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(160);
						jqGridColModel.setFormatter("cmbResponsbilityformatter");
					}*/

				/* if(i==7 || i==8){
					    jqGridColModel.setAlign("left");	
				    	jqGridColModel.setWidth(180);
				}*/
				 
				 if(i==14){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(100);
					 jqGridColModel.setFormatter("txtKznmResultareaformatter");
					// jqGridColModel.setpEditable(true);
					 
				 }
				 if(i==8){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(200);
				 }
				 
				 if((i==10)){
				    	
				    	jqGridColModel.setHidden(false);
				    }
				    if((i==11)){
				    	
				    	jqGridColModel.setHidden(false);
				    	jqGridColModel.setAlign("center");
					    jqGridColModel.setWidth(160);
				    }
                      /*if((i==12)){
				    	jqGridColModel.setHidden(false);
				    	jqGridColModel.setAlign("center");
					    jqGridColModel.setWidth(160);
						jqGridColModel.setFormatter("cmbkznmThemecategoryidformatter");
				    }*/
				 
				
                    jqGridTableModel.getColModel().add(jqGridColModel);
				 
			}

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "85%%");
			
			return tableModel;
		}
	
	    private JSONObject getKaizenDataUpdateTableModel(String[] colIndex,String[] colHeader,char type,boolean enableFilter,boolean tableButton) {
	    	CommonMessage.debugMsg("colIndex :" +colIndex[0]+ "  " +colIndex[1]+ "    "+ colIndex[2]);
	    	CommonMessage.debugMsg("colHeader :" +colHeader[0]+ "  " +colHeader[1]+ "    "+ colHeader[2]);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(tableButton);
			jqGridTableModel.setEnableFilter(enableFilter);
			
			for (int i = 0; i < colIndex.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
				jqGridColModel.setName(jqGridColModel.getIndex());
				jqGridColModel.setWidth(150); 
				jqGridColModel.setAlign("left");
			    if(  ( i == 0)){
					 jqGridColModel.setHidden(true); 
				 }
			   /* if((  i==10|| i==11)  ){
					 jqGridColModel.setHidden(false); 
				 }*/
				 if(( i == 1)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(70);
						jqGridColModel.setFormatter("checkBoxKzn");
			     }
				 if(( i == 2)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(70);
						jqGridColModel.setFormatter("dteKznmDateformatter");
			     }
				  if(( i== 10 )){
			    	jqGridColModel.setAlign("center");
				    jqGridColModel.setWidth(250);
				    jqGridColModel.setFormatter("cmbkznmThemecategoryidformatter");
			    }
				  
				 if(i==9){
					jqGridColModel.setHidden(false);
				    jqGridColModel.setAlign("center");	
					jqGridColModel.setWidth(80);
				 }
				 if(i==9){
					 jqGridColModel.setWidth(160);
				 }
				 if(i==4|i==14){
					 jqGridColModel.setWidth(80);
				 }
				/* if(i==3||i==4||i==5||i==6){
					 jqGridColModel.setWidth(80);
				 }*/
				  
				 /*if(i==4||i==5||i==6){
				 jqGridColModel.setWidth(80);
			      }*/

				 
			/*	 if(( i == 10)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(160);
						jqGridColModel.setFormatter("cmbResponsbilityformatter");
					}*/

				/* if(i==7 || i==8){
					    jqGridColModel.setAlign("left");	
				    	jqGridColModel.setWidth(180);
				}*/
				 
				 if(i==14){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(100);
					 jqGridColModel.setFormatter("txtKznmResultareaformatter");
				 }
				 if(i==15){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(100);
					 jqGridColModel.setFormatter("txtKznmBenefitvalueformatter");					 
				 }
				 if(i==17){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(100);
					 jqGridColModel.setpEditable(true);
					 jqGridColModel.setFormatter("cmbKznmBenefittypeformatter");
				 }
				 if(i==18){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(100);
					 jqGridColModel.setFormatter("txtKznmVerifyamountformatter");
				 }
				 if(i==19){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(200);
					 jqGridColModel.setFormatter("txtKznmBenefitsformatter");
				 }
				 if(i==20){
					jqGridColModel.setAlign("left");
					jqGridColModel.setWidth(180);
					jqGridColModel.setFormatter("txtKznmTeammembersformatter");
				 }
				 if(i==8){
					 jqGridColModel.setAlign("left");
					 jqGridColModel.setWidth(200);
				 }
				 
				 if((i==10)){
				    	
				    	jqGridColModel.setHidden(false);
				    }
				    if((i==11)){
				    	
				    	jqGridColModel.setHidden(false);
				    	jqGridColModel.setAlign("center");
					    jqGridColModel.setWidth(160);
				    }
                     /*if((i==12)){
				    	jqGridColModel.setHidden(false);
				    	jqGridColModel.setAlign("center");
					    jqGridColModel.setWidth(160);
						jqGridColModel.setFormatter("cmbkznmThemecategoryidformatter");
				    }*/
                   jqGridTableModel.getColModel().add(jqGridColModel);
				 
			}

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "85%%");
			return tableModel;
		} 
	  	  
	    
	    
	    
	    private JSONObject getTableModelEmployeeWiseKaizen(List<String[]> headers) {
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			
			String[] colHeader = headers.get(0);
			String[] colHeader1 = headers.get(1);
		/*	colHeader[3] = "DATE";
			if(caption.equals("Factory")){
				caption="PBU";
			}else if(caption.equals("Section")){
				caption="DMT";
			}else if(caption.equals("Line")){
				caption="JH";
			}*/
			String[] emptyrow = new String[colHeader.length];
			emptyrow[0] = "";
			emptyrow[1] = "";
			CommonMessage.debugMsg("test..."+colHeader.length);
			for (int i = 2; i <colHeader.length; i++) {
				emptyrow[i] = "";
			}
			jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader1);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			//colHeader1[3] = caption;  
			for (int i = 0; i <= colHeader.length-1; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
				jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
				jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
				jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);

				if (i == 0 || i == 1 || i==2) {
					jqGridColModel.setHidden(true);
					jqGridColModel.setKey(true);
				} else if (i > 3) {
					// jqGridColModel.setHidden(false);
					jqGridColModel.setKey(true);
					jqGridColModel.setAlign("right");
					jqGridColModel.setWidth(74);
				}

				else if (i == 16) {
					CommonMessage.debugMsg("Length of col:" + colHeader.length);
					jqGridColModel.setHidden(true);
					jqGridColModel.setKey(true);
				}

				jqGridTableModel.getColModel().add(jqGridColModel);
				
				//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			}
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "67%%");
			return tableModel;
		}

	 private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
			HttpSession httpSession = request.getSession(true);
			
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
			
			if( commonFilter != null && ! createNew ){
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
				FilterValues.setPaginationParams(request,commonFilter);
			}	
			else{
				commonFilter =  new CommonFilter();
				commonFilter = 	FilterValues.getCommonFilters(request,commonFilter); // getFilterValues(request);
				commonFilter = 	FilterValues.getOPLandKaizen(request,commonFilter);
				commonFilter.setFirstLevel("Y");
				//commonFilter.setViewClick('Y');
				//CommonMessage.debugMsg(" Month Checking "+commonFilter.getFromMonth());
				/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	}*/
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
  			AdmTlUsermst user = UIUtils.getLoginUser(request);
			commonFilter.setAbnDetectBy(user.getUsrm_ccno());

			return commonFilter;
		}
	    private JSONObject getTableModelApprovalReport(List<String[]> headers) {
	    	
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
			tableModel.set("tableHeight", "99%%");
			tableModel.set("tableWidth", "108%%");
			return tableModel;
		}

	    private JSONObject getKaizenDateUpdateTableModel(String[] colIndex,String[] colHeader,char type,boolean enableFilter,boolean tableButton  ) {//List<String[]> headers) {
	    	CommonMessage.debugMsg("colIndex :" +colIndex[0]+ "  " +colIndex[1]+ "    "+ colIndex[2]);
	    	CommonMessage.debugMsg("colHeader :" +colHeader[0]+ "  " +colHeader[1]+ "    "+ colHeader[2]);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(tableButton);
			jqGridTableModel.setEnableFilter(enableFilter);
			
			for (int i = 0; i < colIndex.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
				jqGridColModel.setName(jqGridColModel.getIndex());
				jqGridColModel.setWidth(150); 
				jqGridColModel.setAlign("left");
			    if(  ( i == 0 || i==10|| i==11)  ){
					 jqGridColModel.setHidden(true); 
				 }
				 if(( i == 1)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(70);
						jqGridColModel.setFormatter("checkBoxKzn");
			     }
				 if(i==2){
				    jqGridColModel.setAlign("center");	
			    	jqGridColModel.setFormatter("submitBtn");
					jqGridColModel.setWidth(80);
				 }if(i==3||i==4||i==5||i==6){
					 jqGridColModel.setWidth(80);
				 }
				 if(( i == 9)){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(160);
						jqGridColModel.setFormatter("cmbResponsbilityformatter");
					}

				 if(i==7 || i==8){
					    jqGridColModel.setAlign("left");	
				    	jqGridColModel.setWidth(180);
				}
				
                    jqGridTableModel.getColModel().add(jqGridColModel);
				 
			}

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "85%%");
			
			return tableModel;
		}
	    
	    private JSONObject getKaizenDateUpdateTableModelExcel(String[] colIndex,String[] colHeader,char type,boolean enableFilter,boolean tableButton  ) {//List<String[]> headers) {
	    	CommonMessage.debugMsg("colIndex :" +colIndex[0]+ "  " +colIndex[1]+ "    "+ colIndex[2]);
	    	CommonMessage.debugMsg("colHeader :" +colHeader[0]+ "  " +colHeader[1]+ "    "+ colHeader[2]);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(tableButton);
			jqGridTableModel.setEnableFilter(enableFilter);
			
			for (int i = 0; i < colIndex.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
				jqGridColModel.setName(jqGridColModel.getIndex());
				jqGridColModel.setWidth(150); 
				jqGridColModel.setAlign("left");
			    if(  ( i == 0 || i==1 || i==2 || i==9)  ){
					 jqGridColModel.setHidden(true); 
				 }
				 if(  ( i == 1)  ){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(70);
						jqGridColModel.setFormatter("checkBoxKzn");
					}
				 if(i==2){
				    jqGridColModel.setAlign("center");	
			    	jqGridColModel.setFormatter("submitBtn");
					jqGridColModel.setWidth(80);
				 }
				 if(i==7 || i==8){
					    jqGridColModel.setAlign("left");	
				    	jqGridColModel.setWidth(180);
				}
				

				
				jqGridTableModel.getColModel().add(jqGridColModel);
				 
			}

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "85%%");
			
			return tableModel;
		}
        
	    private JSONObject getKaizenThemeTableModelExcel(String[] colIndex,String[] colHeader,char type,boolean enableFilter,boolean tableButton,String action  ) {//List<String[]> headers) {
	    	CommonMessage.debugMsg("colIndex :" +colIndex[0]+ "  " +colIndex[1]+ "    "+ colIndex[2]);
	    	CommonMessage.debugMsg("colHeader :" +colHeader[0]+ "  " +colHeader[1]+ "    "+ colHeader[2]);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(tableButton);
			jqGridTableModel.setEnableFilter(enableFilter);
			
			for (int i = 0; i < colIndex.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
				jqGridColModel.setName(jqGridColModel.getIndex());
				jqGridColModel.setWidth(150); 
				jqGridColModel.setAlign("left");
			    if(  ( i == 0 || i==1 || i==4)  ){
					 jqGridColModel.setHidden(true); 
				 }
			    if(action != null && action.contains("getExcel")){
			    	if(i==4) 
			    	{
			    		jqGridColModel.setHidden(false); 
			    	}
			    }
				 if(  ( i == 1)  ){
					    jqGridColModel.setAlign("center");
						jqGridColModel.setWidth(70);
						jqGridColModel.setFormatter("checkBoxKzn");
					}
				 if(i==2){
				    jqGridColModel.setAlign("center");	
					jqGridColModel.setWidth(80);
				 }
				 if(i==7 || i==8){
					    jqGridColModel.setAlign("left");	
				    	jqGridColModel.setWidth(180);
				}
				

				
				jqGridTableModel.getColModel().add(jqGridColModel);
				 
			}

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "85%%");
			
			return tableModel;
		}

	    

	    private void updateKizenDate(HttpServletRequest request,HttpServletResponse response,GenTlFnlnrolemapBean genTlFnlnrolemapBean) throws IOException,
				ValidationExceptions, BusinessApplicationExceptions {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			try {
				if (httpSession != null && user != null) {
										
					String kaizenId = request.getParameter("kaizenId");
					String kznRespid = request.getParameter("kznRespid");
				
					if (UIUtils.isValidKeyId(kaizenId)) {
						    kaizenServices.updateKaizenDate(kaizenId,kznRespid);
							JSONObject successData = new JSONObject();
							successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-save"));
							//successData.put("title", title);
							JSONObject returnData = new JSONObject();
							returnData.put("successData", successData);
							returnData.put("formClear", true);
							out.print(returnData.toString());
					}
				}

			/*} catch (ValidationExceptions e) {
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
				CommonMessage.debugMsg(" e " + errMessage);*/
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}

		}


private void updateKizenTheme(HttpServletRequest request,HttpServletResponse response)throws Exception{
	HttpSession httpsession=request.getSession(false);
    ServletOutputStream out=response.getOutputStream();
    AdmTlUsermst user=UIUtils.getLoginUser(request);
    String updateMsg=null;
    CommonMessage.debugMsg("inside the servlet");
    try{
    	    if(httpsession!=null && user!=null){
    		String KznThemedetails=request.getParameter("KaizenThemeList");
    		CommonMessage.debugMsg("KznThemedetails"+KznThemedetails);
    		KznTlMst thememodel= new KznTlMst();
    		JSONArray ThemeList=null;
    		if(UIUtils.isValidKeyId(KznThemedetails)){
    		  ThemeList=JSONArray.fromString(KznThemedetails);
    		  CommonMessage.debugMsg("ThemeList"+ThemeList);
    		   List<KznTlMst> ThemecategoryList=(List<KznTlMst>)UIUtils.convertJSONArrToList(thememodel,ThemeList);
    		   CommonMessage.debugMsg("ThemecategoryList.get(2)::"+ThemecategoryList);
    		   ThemecategoryList=kaizenServices.updateTheme(ThemecategoryList);
               updateMsg="Data Updated Successfully";
    		}
    		JSONObject SuccessData=new JSONObject();
	 	    SuccessData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update"));
	    	SuccessData.put("msg",updateMsg);
	    	SuccessData.put("formClear",false);
	    	JSONObject returnData=new JSONObject();
	    	returnData.put("successData", SuccessData);		
	    	out.print(returnData.toString());
	    	out.close();
    	}
    }
    catch (Exception e) {
		e.printStackTrace();
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());
	}   
}

private void updateKaizenData(HttpServletRequest request,HttpServletResponse response)throws Exception{
	HttpSession httpsession=request.getSession(false);
    ServletOutputStream out=response.getOutputStream();
    AdmTlUsermst user=UIUtils.getLoginUser(request);
    String updateMsg=null;
    CommonMessage.debugMsg("inside the Update");
    try{
    	    if(httpsession!=null && user!=null){
    		String KznDatadetails=request.getParameter("KaizenDataList");
    		CommonMessage.debugMsg("KznDatadetails"+KznDatadetails);
    		KznTlMst thememodel= new KznTlMst();
    		JSONArray DataList=null;
    		if(UIUtils.isValidKeyId(KznDatadetails)){
    		  DataList=JSONArray.fromString(KznDatadetails);
    		  CommonMessage.debugMsg("DataList"+DataList);
    		   List<KznTlMst> KaizenDataList=(List<KznTlMst>)UIUtils.convertJSONArrToList(thememodel,DataList);
    		   CommonMessage.debugMsg("KaizenDataList.get(2)::"+KaizenDataList);
    		   KaizenDataList=kaizenServices.updateKaizenData(KaizenDataList);
               updateMsg="Data Updated Successfully";
    		}
    		JSONObject SuccessData=new JSONObject();
	 	    SuccessData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update"));
	    	SuccessData.put("msg",updateMsg);
	    	SuccessData.put("formClear",false);
	    	JSONObject returnData=new JSONObject();
	    	returnData.put("successData", SuccessData);		
	    	out.print(returnData.toString());
	    	out.close();
    	}
    }
    catch (Exception e) {
		e.printStackTrace();
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());
	}   
}



}