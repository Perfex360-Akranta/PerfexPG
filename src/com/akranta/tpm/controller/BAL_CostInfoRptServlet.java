package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_CostInfoService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_CostInfoServiceImpl;
import com.akranta.tpm.service.impl.OplTlMstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_CostInfoRptServlet extends HttpServlet {
	
	/** Created By:Siddharth.A
	 * 
	 * 
	 * Modified : Karthick.T
	 * Date : 25-Nov-2011
	 */
	private static final long serialVersionUID = 1L;
	
	CommonFilter commonFilter;
	BAL_CostInfoService cstinfoService;
	
	
	
	public BAL_CostInfoRptServlet() throws Exception{
		super();
	
		//cstinfoService = new CostInfoServiceImpl();
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
	
		try {
			cstinfoService = (BAL_CostInfoServiceImpl)UIUtils.getServiceObject(request,"CostInfoServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		
		if(action.equals("CostInfoRpt_input.cstinforpt")) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/CostInfoRpt.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("CostInfoRpt_getCol.cstinforpt"))
		{
			System.out.println("before get col model" );
			//out.println(getColumnModel());
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoRpt", "CostInfoRpt"));
		}
		else if( action.equals("CostInfoRpt_getData.cstinforpt") )
		{
			try
			{
				//CommonFilter  commonFilter = 	getFilterValues(request);	
				
				commonFilter = FilterValues.getCommonFilters(request, commonFilter);
				
							
  			 	List<String[]> costInfoRptList  = cstinfoService.getAllCostInfo(commonFilter);
  			 	System.out.println("costInfoRptList"+costInfoRptList.size());
  			 	
  			 	JSONObject costInfoRptData = UIUtils.convertToJqGridTableObject(costInfoRptList,request,0,0); 
  				System.out.println("costInfoRptData="+costInfoRptData);
  				
  			 	out.println(costInfoRptData);

		    }catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			}
		}
		
		
	}
	
	/*private JSONObject getColumnModel()
	{
		String [][] colNames = { 
						{"Document No","DocumentNo","120","false","center"},
						{"Date","Date","120","false","center"},
						{"ShiftKeyid","ShiftKeyid","120","true"},
						{"Shift","Shift","120","false","center"},
						{"CellFactid","CellFactid","120","true"},
						{"SectKeyId","SectKeyId","120","true"},
						{"Section","Section","180","false"},
						{"CellKeyId","CellKeyId","120","true"},
						{"Cell","Cell","250","false"},
						{"MCHKeyid","MCHKeyid","120","true"},
						{"Equipment","Equipment","320","false"},
						{"AssmKeyid","AssmKeyid","120","true"},
						{"Assembly","Assembly","180","false"},
						{"PHMKeyid","PHMKeyid","120","true"},
						{"Phenomena","Phenomena","250","false"},
						{"BCSMKeyId","BCSMKeyId","120","true"},
						{"Cause","Cause","150","false"},
						{"Start Time","StartTime","180","false"}, 
						{"End Time","EndTime","180","false"},
						{"Actual-Time","ActualTime","150","false"},
						{"TRDMKeyId","TRDMKeyId","120","true"},
						{"Maint.Section","MaintSection","180","false"},
						{"Problem","Problem","350","false"},
						{"Final Action","FinalAction","150","false"},
						{"Man Power Cost","ManPowerCost","120","false","center"},
						{"Spare Cost","SpareCost","120","false","center"},
						{"Service Cost","ServiceCost","120","false","center"},
						{"Utility Cost","UtilityCost","120","false","center"},  
						{"Other Cost","OtherCost","120","false","center"},
						{"Total Cost","TotalCost","120","false","center"}
						      };
		
		JSONObject jQModel =UIUtils.getColumnType(colNames);
		jQModel.put("cellattr", "testCallattr");
		System.out.println(jQModel);
		return UIUtils.getGroupByColumnModel(colNames) ;
	}
	
	
	private CommonFilter getFilterValues(HttpServletRequest request)
	{
		String fromDate = request.getParameter("dtFromDate");
		String toDate = request.getParameter("dtToDate");
		System.out.println(" fromDate= " + fromDate+ "todate=" + toDate +"---" );
		 fromDate = UIUtils.convertToDisplayFormat(fromDate, "MM/dd/yyyy");
  		 toDate = UIUtils.convertToDisplayFormat(toDate, "MM/dd/yyyy");
  		
		System.out.println(" fromDate= " + fromDate+ "todate=" + toDate +"---" );
		
		CommonFilter commonFilter = new CommonFilter();
		  ComboFilter cmbFact=new ComboFilter();
		     ComboFilter cmbSect=new ComboFilter();
		     ComboFilter cmbCell=new ComboFilter();
		     ComboFilter cmbMach=new ComboFilter();
		 String factId = request.getParameter("factid");
	     if( factId == null || factId.isEmpty() )
	     {	 
	    	 factId = "{}";
	     }
	     cmbFact.setId(factId);
		 System.out.println("fact id="+(request.getParameter("factid")));
		 commonFilter.setFactory(cmbFact);
		 
		 String sectId = request.getParameter("cmbSectid");
		  if( sectId == null || sectId.isEmpty() )
		  {	 
			  sectId = "{}";
		  }
		 cmbSect.setId(sectId);
		 System.out.println("sect id="+(request.getParameter("cmbSectid")));
		 commonFilter.setSection(cmbSect);
		 
		 String cellId = request.getParameter("cmbCellid");
		  if( cellId == null || cellId.isEmpty() )
		  {	 
			  cellId = "{}";
		  }
		 cmbCell.setId(cellId);
		 System.out.println("cell id="+(request.getParameter("cmbCellid")));
		 commonFilter.setCell(cmbCell);
		 
		 String mchId = request.getParameter("cmbMchid");
		  if( mchId == null || mchId.isEmpty() )
		  {	 
			  mchId = "{}";
		  }
		 cmbMach.setId(mchId);
		 System.out.println("mch id="+(request.getParameter("cmbMchid")));
		 commonFilter.setMachine(cmbMach);
		
		 commonFilter.setFromDate(fromDate);
	     commonFilter.setToDate(toDate);
		
		 System.out.println("commonFilter="+commonFilter.getFactory());
		return commonFilter;
	

	}*/
}
				