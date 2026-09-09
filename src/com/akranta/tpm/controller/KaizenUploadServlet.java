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

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.service.KaizenServices;
import com.akranta.tpm.service.KaizenUploadService;
import com.akranta.tpm.service.impl.KaizenUploadServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

/**
 * Servlet implementation class KaizenApproval
 */

public class KaizenUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private KaizenUploadService  kaizenUploadService ;
    public KaizenUploadServlet() {
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
    private void initilizeInputMode(FormModes mode,HttpServletRequest request, HttpSession httpSession)throws Exception
    {    	
    	
    	String kznKeyid = request.getParameter("kznKeyid");
    	CommonMessage.debugMsg("The kznKeyid"+kznKeyid);  	
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
			CommonMessage.debugMsg("mode in kznKeyid ="+kznKeyid);
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
			kznKeyid=kaizenUploadService.selectKznb(kznbKeyid); 
		
		
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
			kznTlMst=kaizenUploadService.select(kznKeyid); 
			kaizenFormBean.setFormMode(mode);
			
			CommonMessage.debugMsg(" Getting value for KPI "+kznTlMst.getKznmKpiid());
			
			String filePath = UIUtils.getImagePath(request);
			
			try
			{	 
				 CommonMessage.debugMsg("Inside image service");
				 String fileName=UIUtils.TPM_TEMPIMG_DIR;
				 kznTlMst = kaizenUploadService.getkznImage(fileName,filePath,kznTlMst);
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
	    		kznTheme=kaizenUploadService.getkaizenTheme(kznbKeyid);
	    		CommonMessage.debugMsg(kznTheme+"   kznbKeyid  theme");
	    		kznTlMst.setKznmIdea(kznTheme);
	    	}
	    	if(UIUtils.isValidKeyId(kznbKeyid)){
	    		String benefit=kaizenUploadService.getkaizenBenefit(kznbKeyid);
	    		String pcdqsme=kaizenUploadService.getkaizenPcdqsme(kznbKeyid);
	    		CommonMessage.debugMsg("The pcdqsme"+pcdqsme);
	    		String themename=kaizenUploadService.getThemename(benefit);
	    		CommonMessage.debugMsg(kznTheme+"   kznbKeyid  theme");
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
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String dispatchUrl = null;
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		ComboFilter currentFilter = new ComboFilter();
		ComboFilter comboFilter = new ComboFilter();
		
		if( user == null)
			return ;
		String action = UIUtils.getActionPart(request);
    	CommonMessage.debugMsg("Action in Kaizen Upload iss"+action);
    	try{
    	kaizenUploadService=(KaizenUploadServiceImpl)UIUtils.getServiceObject(request, "KaizenUploadServiceImpl");
    	}
    	catch(ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
			e.printStackTrace();
		}
    	if(action.equals("KaizenUpload_input.kznUpd")){
    		CommonMessage.debugMsg("Action in Kaizen Upload iss"+action);
        	String kznKeyid=request.getParameter("kznKeyid");
        	String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
        	String flid=request.getParameter("flid");
        	String date=request.getParameter("date");
        	String themecategory=request.getParameter("themecategory");
        	String actPillar=request.getParameter("actPillar");
        	String Problem=request.getParameter("problem");
        	String CountMeasure=request.getParameter("countermeasure");
        	String benselval=request.getParameter("benselval");
        	String pcdqsme=request.getParameter("typebenefit");
        	String kznbankkeyid=request.getParameter("KznmKzbnkeyid");
        	String status=request.getParameter("Status");
        	String Mode=request.getParameter("Mode");
        	String  approvelevel=request.getParameter("kznmApprovellevel");
        	String frmName=request.getParameter("frmName");
        	String costhrequipment=request.getParameter("costhrequipment");
            String costhrproduct=request.getParameter("costhrproduct");
            String benefitvalue=request.getParameter("benefitvalue");
            String benefittype=request.getParameter("benefitype");
            FormModes mode = FormModes.create;
			if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.view) )
				mode = FormModes.view;
			else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.modify) )
				mode = FormModes.modify;
			else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.approval) )
				mode = FormModes.approval;
        	initilizeInputMode(mode, request, httpSession);	
        	request.setAttribute("pcdqsme", pcdqsme);
        	request.setAttribute("benselval", benselval);
        	request.setAttribute("actPillar", actPillar);
        	request.setAttribute("kznKeyid", kznKeyid);
        	request.setAttribute("flid", flid);
        	request.setAttribute("date", date);
        	request.setAttribute("themecategory",themecategory);
        	request.setAttribute("Problem", Problem);
        	request.setAttribute("CountMeasure",CountMeasure);
        	request.setAttribute("kznbankkeyid",kznbankkeyid);
        	request.setAttribute("status",status);
        	request.setAttribute("Mode",Mode);
        	request.setAttribute("approvelevel",approvelevel);
        	request.setAttribute("frmMode", frmMode);
        	request.setAttribute("frmName",frmName);
            request.setAttribute("costhrproduct",costhrproduct);
        	request.setAttribute("costhrequipment", costhrequipment);
        	request.setAttribute("benefitvalue",benefitvalue);
        	request.setAttribute("benefittype",benefittype);
        	RequestDispatcher rd=request.getRequestDispatcher("/pages/Kaizenupload.jsp");
        	rd.forward(request, response);
        }
    	else if (action.equals("KaizenUpload_save.kznUpd")){
			saveKaizenUpload(request,response);	
    	}
    	
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
				if(resultArea.substring(0, 1).equals("P"))
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

    
    private void saveKaizenUpload(HttpServletRequest request, HttpServletResponse response ) throws BusinessApplicationExceptions,IOException{
  		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String saveMode=request.getParameter("saveMode");
    	String status = request.getParameter("status");
    	String Type = request.getParameter("Type");
    	String kaizenbankid=request.getParameter("kznbankkeyid");
    	String apprvallevel = request.getParameter("applevel");
    	String avdsucmsg=request.getParameter("avdsucmsg");
    	String nextlevel = request.getParameter("approvelevel");
    	String hdnstatus=request.getParameter("hdnKznmStatus");
    	String hdnaprrovlevel=request.getParameter("hdnApprovLevel");
    	String formmode=request.getParameter("frmMode");
    	String saveMsg=null;
    	KaizenFormBean newkaizenFormBean =new KaizenFormBean ();
    	if( httpSession != null && user != null)
    	{	    		
    		KznTlMst newKznTlMst = new KznTlMst();
			newKznTlMst.setKznmCreatedby(user.getUsrm_ccno());
			newKznTlMst.setKznmPreparedid(user.getUsrm_ccno());
			newKznTlMst =(KznTlMst)UIUtils.setBeanProperties((Object)newKznTlMst,request);
			newkaizenFormBean =(KaizenFormBean) UIUtils.setBeanProperties((Object)newkaizenFormBean,request);
			KznTlMst existKznTlMst = (KznTlMst)httpSession.getAttribute("kznTlMst"+ newKznTlMst.getKznmKeyid());    		 
			KaizenFormBean kaizenFormBean = (KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
			/**------------------ For Saving Improvement PillarLink ---------------- **/
			
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
			{
				CommonMessage.debugMsg("Inside the else status"+newKznTlMst.getKznmStatus());
				newKznTlMst.setKznmStatus("-");
				CommonMessage.debugMsg("Inside the else status"+newKznTlMst.getKznmStatus());
			}
			
			if(UIUtils.isValidKeyId(nextlevel)){ 
				newKznTlMst.setKznmApprovLevel(nextlevel);
			}else {
				CommonMessage.debugMsg("Inside the else Approvallevel"+newKznTlMst.getKznmApprovLevel());
		    	newKznTlMst.setKznmApprovLevel("-");
		    }
			//String filemanager = request.getParameter("filemanager");
			//CommonMessage.debugMsg("The filemanager"+filemanager);
			if(UIUtils.isValidKeyId(kaizenbankid)){
				newKznTlMst.setKznmKzbnkeyid(kaizenbankid);
			}
			
			try{
					if(!(kaizenFormBean.getFormActionMode().equals("View")||kaizenFormBean.getFormActionMode().equals("Completed")))
					{
						if( newKznTlMst.getKznmKeyid() == null )
						{	
							existKznTlMst =	kaizenUploadService.createKaizenUpload(newKznTlMst,existKznTlMst,newkaizenFormBean,apprvallevel,status);
							saveMsg="Data Saved Successfully";
						}	
						else
						{
							if(formmode.equals("modify"))
							{
								CommonMessage.debugMsg("Inside the formmode");
							   if(hdnstatus.equals("R"))
							   {
								   newKznTlMst.setKznmStatus("P");
									newKznTlMst.setKznmApprovLevel("-");
							   }
							}
							else{
							 newKznTlMst.setKznmStatus(hdnstatus);
							 newKznTlMst.setKznmApprovLevel(hdnaprrovlevel);
							}
							CommonMessage.debugMsg("Inside the Update");
							existKznTlMst = kaizenUploadService.updateKaizenUpload(newKznTlMst,existKznTlMst,newkaizenFormBean,apprvallevel,status);
							saveMsg="Data Updated Successfully";
						}
					}
					    JSONObject SuccessData=new JSONObject();
				    	SuccessData.put("msg",saveMsg);
				    	SuccessData.put("formClear",false);
				    	JSONObject returnData=new JSONObject();
				    	returnData.put("successData", SuccessData);
				    	SuccessData.put("kznKeyid",newKznTlMst.getKznmKeyid() );
				    	returnData.put("formClear",false);
				    	out.print(returnData.toString());
				    	out.close();
            /*     if(UIUtils.isValidKeyId(filemanager)){	
						
                	 SuccessData.put("filemanager", true);
                	 SuccessData.put("formClear",false);	
					}else
					{
						SuccessData.put("filemanager", false); 
					}*/
			}

			
			
			catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "KaizenUploadCreationExceptions");
				errMessage.put("fromMode",kaizenFormBean.getFormActionMode());
				out.print(errMessage.toString());
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("Error Servler e -"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"KaizenUploadCreationExceptions");
				errMessage.put("tpmException", "This Record is already exist");
				errMessage.put("displyMsg", false);
				out.print(errMessage.toString());
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
}    

	

    
    

   
   
	

	


