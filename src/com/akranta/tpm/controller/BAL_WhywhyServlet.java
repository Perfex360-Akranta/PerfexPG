package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.service.BAL_WhyWhyAnalysisService;
import com.akranta.tpm.service.BAL_WhywhyStdService;
//import com.akranta.tpm.service.impl.WOPrintServiceImpl;
import com.akranta.tpm.service.impl.BAL_WhyWhyAnalysisServiceImpl;
import com.akranta.tpm.service.impl.WhywhyStdServiceImpl;

public class BAL_WhywhyServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BAL_WhyWhyAnalysisService  whywhyService; 
	public BAL_WhywhyServlet()
	{
		/*try {
			whywhyService = new WhyWhyAnalysisServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PrintWriter out = response.getWriter();
		
		String action = UIUtils.getActionPart(request);
		String dispatchUrl =null;

		try {
			whywhyService = (BAL_WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request,"WhyWhyAnalysisServiceImpl");
		} catch (ServiceObjectCreationException e) {
			com.akranta.tpm.utils.CommonFunctions.debugMsg(e);
		}

		if( action.equals("kznwhywhyanalysis_input.why")){
			
			setFormKaizenWhywhy(request,response);
		}
		else if( action.equals("whywhyanalysis_getCol.why")){
			String colHeader = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyanalysisGridColModel", "kznWhyWhyAnalysis");
			out.println(colHeader);
		}
		else if( action.equals("whywhyanalysis_getData.why")){
			HttpSession httpSession = request.getSession(false);
			String wwwsKeyid = (String)httpSession.getAttribute("wwwsKeyid");
			List<Object> whywhyDetails =  whywhyService.getWWDT(wwwsKeyid);
			JSONArray jqGridTblData = UIUtils.fromTpmModelList(whywhyDetails);
			out.println(jqGridTblData);
		}
		
		
		else if(action.equals("whywhyReport_input.why")){
			
			UIUtils.forwardRequest(request, response, "/pages/WhyWhyStandardReport.jsp");
		}
		else if(action.equals("whywhyReport_getCol.why"))
		{
			out.println(getColumnModel());
		}
		else if( action.equals("whywhyReport_getData.why") )
		{
			try
			{
				CommonFilter  commonFilter = 	getFilterValues(request);			
								
				List< String[]> whywhyList  = whywhyService.getAllwhywhyStd(commonFilter);

				System.out.println("size " + whywhyList.size());
				JSONObject whywhy = UIUtils.convertToJqGridTableObject(whywhyList,request,0,0); 
				
				System.out.println(whywhy);
				
  			 	out.println(whywhy);

		    }catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	private JSONObject getColumnModel()
	{
		String [][] colNames = {
				{"Key Id","keyid" ,"120","true"},
								{"Ref Doc No","RefDocNo" ,"120","false"},
								{"Date","Date","120","false"},
								{"Shift in Charge","ShiftinCharge","120","false"},
								{"Machine Name No","MachineNameNo","120","false"},
								 {"Section Name","SecName","120","false"},
								 {"Line Name","LineName","120","false"},
								 {"Station Name","StationName","120","false"},
								 {"Phenomena","Phenomena","120","false"},
								 {"Cause","Cause","120","false"}, 
								 {"Problem","Problem","120","false"},
								 {"Final Action","FinalAction","120","false"},
								 {"Ctr Measure","CtrMeasure","120","false"},
								 {"Root Cause","RootCause","120","false"},
								 {"Spares rePlaced","SparesrePlaced","120","false"},
								 {"Check Made","CheckMade","120","false"},
								 {"You did Not","YoudidNot","120","false"},
								 {"Symptom Before","SymptomBefore","120","false"},
								 {"Kaizen Idea","KaizenIdea","120","false"},  
								 {"Pillar","Pillar","120","false"},
								 {"Counter","Counter","120","false"}
								};
		
		return UIUtils.getColumnType(colNames) ;
	}
	
	
	private CommonFilter getFilterValues(HttpServletRequest request)
	{
		
		System.out.println("Inside the Filter values");
		
				
		String keyId = request.getParameter("cmbkeyId");
		/*ComboFilter cmbkeyId=new ComboFilter();
		cmbkeyId.setId(KeyId != null ? KeyId:"{}");
		*/
		String finalTrade = request.getParameter("cmbfinalTrade");				
		ComboFilter cmbfinalTrade = new ComboFilter();
		cmbfinalTrade.setId(finalTrade != null ? finalTrade:"{}");
		
		String factoryId = request.getParameter("cmbFact");
		ComboFilter cmbFact=new ComboFilter();
		cmbFact.setId(factoryId != null ? factoryId:"{}");
		
		String cellId = request.getParameter("cmbCell");
		ComboFilter cmbCell=new ComboFilter();
		cmbCell.setId(cellId!=null? cellId:"{}");
		
		String costCenterId = request.getParameter("cmbCostCenter");		
		ComboFilter cmbCostCenter=new ComboFilter();
		cmbCostCenter.setId(costCenterId == null ? costCenterId:"{}");		
		
		String jhSetp=request.getParameter("cmbjhStep");
		ComboFilter cmbjhStep =new ComboFilter();
		cmbjhStep.setId(cmbjhStep == null ? jhSetp:"{}");
		
		
		String JHStep = request.getParameter("cmbmachineRank");				
		ComboFilter cmbmachineRank =new ComboFilter();
		cmbmachineRank.setId(JHStep!=null?JHStep:"{}");
		
		String machineId = request.getParameter("cmbMachine");				
		ComboFilter cmbMachine = new ComboFilter();
		cmbMachine.setId(machineId != null ? machineId:"{}");
		
		String Circle = request.getParameter("cmbCircle");
		ComboFilter cmbCircle=new ComboFilter();
		cmbCircle.setId(Circle == null ? Circle:"{}");
		System.out.println("Circle:"+Circle);
		
		String assembly = request.getParameter("cmbassembly");				
		ComboFilter cmbassembly = new ComboFilter();
		cmbassembly.setId(assembly!=null?assembly:"{}");
		
		String shopId = request.getParameter("cmbShop");
		ComboFilter cmbShop=new ComboFilter();
		cmbShop.setId(shopId!=null? shopId:"{}");	
		
		String Phenomena = request.getParameter("cmbPhenomena");
		ComboFilter cmbPhenomena=new ComboFilter();
		cmbPhenomena.setId(Phenomena!=null? Phenomena:"{}");
		
		String FinalCause = request.getParameter("cmbfinalCause");
		ComboFilter cmbfinalCause=new ComboFilter();
		cmbfinalCause.setId(FinalCause!=null? FinalCause:"{}");
		
		String MaintainCharegeId = request.getParameter("cmbmaintainChargeId");
		ComboFilter cmbmaintainChargeId =new ComboFilter();
		cmbmaintainChargeId.setId(MaintainCharegeId!=null?MaintainCharegeId:"{}");
		
		String eqpGroupId = request.getParameter("cmbeqpGroup");
		ComboFilter cmbeqpGroup =new ComboFilter();
		cmbeqpGroup.setId(eqpGroupId!=null?eqpGroupId:"{}");
		
			
		
		String fromDate = request.getParameter("dtFromDate");
		String toDate = request.getParameter("dtToDate");	
		System.out.println("fromDate " + fromDate );
		System.out.println("toDate " + toDate );
		
		if( UIUtils.isValidDate(fromDate) && UIUtils.isValidDate(toDate) )
		{	
			fromDate = CommonFunctions.convertToDisplayFormat(fromDate, "MM/dd/yyyy");
			toDate = CommonFunctions.convertToDisplayFormat(toDate, "MM/dd/yyyy");
		}

		CommonFilter commonFilter = new CommonFilter();	
		
		commonFilter.setKey(keyId);
		commonFilter.setFinalTrade(cmbfinalTrade);
		commonFilter.setFactory(cmbFact);
		commonFilter.setCell(cmbCell);
		commonFilter.setCostCenter(cmbCostCenter);
		commonFilter.setJhStep(cmbjhStep);
		commonFilter.setMachineRank(cmbmachineRank);
		commonFilter.setMachineId(machineId);
		commonFilter.setCircle(cmbCircle);
		commonFilter.setAssembly(cmbassembly);
		commonFilter.setSection(cmbShop);
		commonFilter.setPhenomena(cmbPhenomena);
		commonFilter.setFinalCause(cmbfinalCause);
		commonFilter.setMaintainChargeId(cmbmaintainChargeId);
		commonFilter.setEqpGroup(cmbeqpGroup);
		
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate); 
		
		System.out.println("CommonFilter:"+commonFilter);
		return commonFilter;

	}
	
	private void setFormKaizenWhywhy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		BAL_BdmTlWhywhymst kznTlWhywhymst = fillWhyWhymst(request); 
		String kznKeyid = request.getParameter("");
		HttpSession httpSession = request.getSession();
		request.setAttribute("kznWhyWhyMst", kznTlWhywhymst);
		httpSession.setAttribute("kznWhyWhyMst", kznTlWhywhymst);
		
		UIUtils.forwardRequest(request, response, "/pages/whywhyAnalysis.jsp");
	}
	
	private BAL_BdmTlWhywhymst fillWhyWhymst(HttpServletRequest request)
	{
		
		HttpSession httpSession = request.getSession(false); 
		
		BAL_BdmTlWhywhymst bdmTlWhywhymst = new BAL_BdmTlWhywhymst();
		String kaizenKeyid = request.getParameter("kznKeyid");
		
		KznTlMst kznTlMst =( KznTlMst) httpSession.getAttribute(kaizenKeyid);
		if( kznTlMst != null )
		{
			bdmTlWhywhymst.setWwmsFactoryid(kznTlMst.getKznmFactoryid());
			bdmTlWhywhymst.setWwmsSectionid(kznTlMst.getKznmSectionid());
			bdmTlWhywhymst.setWwmsCellid(kznTlMst.getKznmCellid());
			bdmTlWhywhymst.setWwmsMachineid(kznTlMst.getKznmMachineid());

			bdmTlWhywhymst.setWwmsAssemblyid(kznTlMst.getKznmAssemblyid());

			bdmTlWhywhymst.setWwmsTargetpillarid(kznTlMst.getKznmTpmpillarid());

			bdmTlWhywhymst.setWwmsRefdoctype("KZN");

			bdmTlWhywhymst.setWwmsRefdocno(kznTlMst.getKznmKeyid());
			
			bdmTlWhywhymst.setWwmsPhenomenaid(kznTlMst.getKznmPhenomenaid());

			bdmTlWhywhymst.setWwmsRootcause(kznTlMst.getKznmRootcause());
			
			
		}	
		return bdmTlWhywhymst;
	}
}
				
				