package com.akranta.tpm.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.CommonFilterTraining;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.PrjConstants;

public class FilterValues {
	
	
	public static CommonFilter getCommonFilters(HttpServletRequest request,CommonFilter commonFilter)
	{
//		HttpSession httpSession  = request.getSession(false);
		String tempVar = null; 
		
		tempVar = request.getParameter("cmbCompid");				
		ComboFilter cmbCompany = new ComboFilter();

		if( UIUtils.isValidKeyId(tempVar))
			//cmbCompany.setId(tempVar);
			commonFilter.setCalibFromDate(tempVar);
		else
			cmbCompany= null;
			
		String loginlevel = CommonFunctions.getLoginLevel(request);
		commonFilter.setRoleLevel(loginlevel);
		
		tempVar = request.getParameter("cmbLocnid");		 
		 String loginLocnId = CommonFunctions.getLoginLocaton(request);
		if(!UIUtils.isValidKeyId(tempVar))
			tempVar = loginLocnId;
		ComboFilter cmbLocation = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbLocation.setId(tempVar);
		else
			cmbLocation=null;
		
		tempVar = request.getParameter("cmbFactid");
		CommonMessage.debugMsg("cmbFactidpopo   "+ tempVar);
		ComboFilter cmbFactory = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbFactory.setId(tempVar);
		else
			cmbFactory=null;
		/////////////////////////DONE BY SUGUMAR//////////////
		tempVar = request.getParameter("cmbSbuid");
		CommonMessage.debugMsg("cmbFactidpopo   "+ tempVar);
		ComboFilter cmbSbu = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbSbu.setId(tempVar);
		else
			cmbSbu=null;
		
		
		tempVar = request.getParameter("cmbPbuid");
		CommonMessage.debugMsg("cmbFactidpopo   "+ tempVar);
		ComboFilter cmbPbu = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbPbu.setId(tempVar);
		else
			cmbPbu=null;
		
		/////////////////////////////////////////////////////
		/*tempVar = request.getParameter("cmbFactid");
		CommonMessage.debugMsg("cmbFactidpopo   "+ tempVar);
		ComboFilter cmbFactory = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbFactory.setId(tempVar);
		else
			cmbFactory=null;
		*/
		
		tempVar = request.getParameter("cmbSectid");				
		ComboFilter cmbSection = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbSection.setId(tempVar);
		else
			cmbSection=null;
		
		
		tempVar = request.getParameter("cmbCostCenter");				
		ComboFilter cmbCostCenter = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbCostCenter.setId(tempVar);
		else
			cmbCostCenter=null;
		
		tempVar = request.getParameter("cmbCircle");				
		ComboFilter cmbCircle = new ComboFilter();		
		if( UIUtils.isValidKeyId(tempVar))
			cmbCircle.setId(tempVar);
		else
			cmbCircle= null;
		
		tempVar = request.getParameter("cmbCellid");
		
		ComboFilter cmbCell = new ComboFilter();
		if( UIUtils.isValidKeyId(tempVar))
			cmbCell.setId(tempVar);
		else
			cmbCell = null;
		
		tempVar = request.getParameter("cmbMchid");	
		ComboFilter cmbMachine = new ComboFilter();		
		if( UIUtils.isValidKeyId(tempVar)){
			cmbMachine.setId(tempVar);
			/*cmbMachine.setCode("");
			cmbMachine.setName("");
			cmbMachine.setId("");
			*/
		}
		else
			cmbMachine = null;//cmbMachine.setId(tempVar);
		
		tempVar = request.getParameter("cmbAssmbid");	
		ComboFilter cmbAssembly= new ComboFilter();		
		if( UIUtils.isValidKeyId(tempVar)){
			/*cmbAssembly.setCode("");
			cmbAssembly.setName("");
			//cmbAssembly = null;
			cmbAssembly.setId("");
			*/
			cmbAssembly.setId(tempVar);
		}
		else
			cmbAssembly = null;//cmbAssembly.setId(tempVar);

		
		tempVar = request.getParameter("cmbPhenomenaId");	
		ComboFilter cmbPhenomena= new ComboFilter();		
		if( UIUtils.isValidKeyId(tempVar)){
			cmbPhenomena.setId(tempVar);
		}
		else
			cmbPhenomena = null;//cmbAssembly.setId(tempVar);

	
		tempVar = request.getParameter("conflid");
		if( UIUtils.isValidKeyId(tempVar)){
			commonFilter.setKey(tempVar);
		}
		
		tempVar = request.getParameter("cmbEqpGrpid");	
		ComboFilter cmbEqpGroup= new ComboFilter();		
		if( !UIUtils.isValidKeyId(tempVar)){
			cmbEqpGroup.setCode("");
			cmbEqpGroup.setName("");
			//cmbEqpGroup = null;
			cmbEqpGroup.setId("");
		}
		else
			cmbEqpGroup.setId(tempVar);
		/* Added ByDhanalakshmi.R*/
		tempVar = request.getParameter("cmbEqpSubGrp");	
		ComboFilter cmbEqpSubGroup= new ComboFilter();		
		if( !UIUtils.isValidKeyId(tempVar))
		{
			cmbEqpSubGroup.setCode("");
			cmbEqpSubGroup.setName("");
			cmbEqpSubGroup.setId("");
		}
		else
			cmbEqpSubGroup.setId(tempVar);
		/*--------------------------------------*/
		tempVar = request.getParameter("cmbMchRnkid");	

		ComboFilter cmbMchRank= new ComboFilter();		
		if( !UIUtils.isValidKeyId(tempVar)){
			cmbMchRank.setId("");
			cmbMchRank.setCode("");
			cmbMchRank.setName("");
			//cmbMchRank = null;
		}
		else
			cmbMchRank.setId(tempVar);
		
		
		String relatedTo = request.getParameter("cboRelatedTo");
		if( UIUtils.isValidKeyId(relatedTo)) 
			commonFilter.setRelatedToMchMld(relatedTo);
		
		
		tempVar = request.getParameter("cmbMould");	
		ComboFilter cmbMould= new ComboFilter();		
		if( !UIUtils.isValidKeyId(tempVar)){
			cmbMould.setCode("");
			cmbMould.setName("");			
			cmbMould.setId("");
		}
		else
			cmbMould.setId(tempVar);
		
		tempVar = request.getParameter("cmbTradeid");	
		ComboFilter cmbTrade= new ComboFilter();		
		if( !UIUtils.isValidKeyId(tempVar)){
			cmbTrade.setCode("");
			cmbTrade.setName("");
			//cmbTrade = null;
			cmbTrade.setId("");
		}
		else
			cmbTrade.setId(tempVar);
		
		tempVar = request.getParameter("ViewClicked");		
		commonFilter.setViewClick('Y');
		if( UIUtils.isValidKeyId(tempVar) &&  tempVar.equals("Y")  )
			commonFilter.setViewClick('Y');
		
		
		tempVar = request.getParameter("rowTotal");
		if( UIUtils.isValidKeyId(tempVar) &&  tempVar.equals("TOTAL")  )
			commonFilter.setRowTotal('Y');
		else
			commonFilter.setRowTotal(null);
		
		String breakup = request.getParameter("BREAKUP");	
		CommonMessage.debugMsg("BREAKUP raw value = [" + breakup + "]");
		CommonFilter breakUpList =  null;
		List<CommonFilter> breakUpArray = null;
		JSONArray convertbreak = null;
		if(UIUtils.isValidKeyId(breakup)){
			if( breakup != null && ! breakup.isEmpty() && !breakup.trim().isEmpty())
    		{
				breakUpList = new CommonFilter();
				convertbreak = JSONArray.fromString(breakup);
				breakUpArray = (List<CommonFilter>)UIUtils.convertJSONArrToList(breakUpList, convertbreak);
				//commonFilter.setBreakUpList(breakUpArray);
				
				 commonFilter.setBreakup(FilterCondSql.getBreakup(breakUpArray));
    		}
		}
		
		
		/* Jqgrid Pagination */
		UIUtils.displayRequestParamsValue(request);
		
		///////--------------------------/////////////////////
		
		
		String firstClick =request.getParameter("firstClick");	
//		String firstLevel =request.getParameter("firstLevel");
		String multipleval=request.getParameter("multipleval");
		String checkedtype=request.getParameter("checkedtype");//checkedtypecheckedtype

		
		if(UIUtils.isValidKeyId(firstClick))
			commonFilter.setFirstLevel(firstClick);
		if(UIUtils.isValidKeyId(multipleval)){
			multipleval=multipleval.trim();
			multipleval=multipleval.trim().replace("`","'");
			multipleval=multipleval.trim();
		}
		
		//HttpSession httpSession  = request.getSession(false);
		String Year=request.getParameter("year");
		String range=request.getParameter("range");
		String chbval= request.getParameter("chbhfghval");
		String chkSkipLine=request.getParameter("skipLine");
		String flid=request.getParameter("flid");
		String elementid=request.getParameter("elementid");
		String loginFlid = CommonFunctions.getLoginFlid(request);
		String loginElementid = CommonFunctions.getLoginElementId(request);
		
		//CommonMessage.debugMsg("loginFlid..."+loginFlid+"..loginElementid.."+loginElementid);
		//CommonMessage.debugMsg("elementid..."+elementid+"..loginElementid.."+loginElementid);
				
		if(UIUtils.isValidKeyId(flid))
			commonFilter.setFlid(flid);
		else if(UIUtils.isValidKeyId(loginFlid)){			
			commonFilter.setFlid(loginFlid);
		}
		if(UIUtils.isValidKeyId(elementid))
			commonFilter.setElementId(elementid);
		else if(UIUtils.isValidKeyId(loginElementid)){
			commonFilter.setElementId(loginElementid);	
		}
		
		if(chkSkipLine == null && commonFilter.getChkSkipLine() == null)
			chkSkipLine = "N";
		if( chkSkipLine != null)			
			commonFilter.setChkSkipLine(chkSkipLine);
		
		
		if(cmbCompany != null )
			commonFilter.setCompany(cmbCompany);
		if(cmbLocation != null )
			commonFilter.setLocation(cmbLocation);
		if(cmbFactory != null)
			commonFilter.setFactory(cmbFactory);
		if(cmbSection != null )
			commonFilter.setSection(cmbSection);
		if( cmbCostCenter != null )
			commonFilter.setCostCenter(cmbCostCenter);
		if( cmbCircle != null)
			commonFilter.setCircle(cmbCircle);
		//CommonMessage.debugMsg("cmbCell in fil val:"+cmbCell.getId());
		if( cmbCell != null)
			commonFilter.setCell(cmbCell);
		
		if(  cmbMachine!= null )
			commonFilter.setMachine(cmbMachine);
		if( cmbAssembly!=null)
			commonFilter.setAssembly(cmbAssembly);
		if( cmbPhenomena!=null)
			commonFilter.setPhenomena(cmbPhenomena);
		if( cmbEqpGroup!=null)
			commonFilter.setEqpGroup(cmbEqpGroup);
		if(cmbEqpSubGroup!=null)
			commonFilter.setEqpSubGroup(cmbEqpSubGroup);
		if( cmbMchRank !=null )
			commonFilter.setMachineRank(cmbMchRank);
		if( cmbTrade!=null)
			commonFilter.setTrade(cmbTrade);
		
		if(cmbMould != null)
			commonFilter.setMould(cmbMould);
		
		
		
		if(UIUtils.isValidKeyId(multipleval)){
			commonFilter.setMultipleval(multipleval);
		}
		CommonMessage.debugMsg("TYPE: "+checkedtype); 
		if(UIUtils.isValidKeyId(checkedtype)){
			commonFilter.setMultipletype(checkedtype);
		}
		CommonMessage.debugMsg("TYPE: "+checkedtype+"="+commonFilter.getMultipletype()); 
		
		commonFilter.setYear(Year);
		commonFilter.setRange(range);
		commonFilter.setActwise(chbval);
		
		setPaginationParams(request,commonFilter);
		//----------------- Drill Down -------------------// 
		String parentId =request.getParameter("parentId");
		CommonMessage.debugMsg("parentId in filtervalues:"+parentId);
		String drillFlag = request.getParameter("drillFlag");
		String fromDetail = request.getParameter("fromDetail");
		
		if( UIUtils.isValidKeyId(parentId) ){
			CommonMessage.debugMsg("parentId in filtervalues1:"+parentId);
			setParentId(commonFilter, parentId);
		}
		if( UIUtils.isValidKeyId(drillFlag) ){
			
			String module = request.getParameter("module");
			CommonMessage.debugMsg("drillFlag:"+drillFlag);
			commonFilter.setDrillFlag(drillFlag.trim().charAt(0));
			CommonMessage.debugMsg("commonFilter.getDrillFlag()1:"+commonFilter.getDrillFlag());
			
			if( commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag ){
				
				if( UIUtils.isValidKeyId(parentId) && UIUtils.isValidKeyId(drillFlag) ){
					
					 if("Quality".equals(module)){
						setMainKeyidForQualityDrillDown(commonFilter,parentId);
					 }
					 else if("Safety".equals(module))
						{
						 setMainKeyidForSafetyDrillDown(commonFilter,parentId);
						}
					 else if("Training".equals(module)) /*Added by Abinaya.G*/
					 {
						 String keyId = request.getParameter("keyId");
						 setMainKeyidForTrainingDrillDown(commonFilter,parentId,keyId);
					 }/*Added by Abinaya.G*/
					 else{
						setMainKeyidForDrillDown(commonFilter,parentId);
					 }	
				}else{
					if (checkDirectLevel(commonFilter) == true) /* if select any of circle, costcenter, equipment group  filter , it has to directly display machine level */
					{
						CommonMessage.debugMsg("jhstep123.................");
						commonFilter.setMachineDirect(true);
						commonFilter.setDrillLevel(DrillLevelConstants.MACHINE);
					}
					else
						commonFilter.setDrillLevel(DrillLevelConstants.COMPANY);
				
				}
			}else if( commonFilter.getDrillFlag() == DrillLevelConstants.backwardFlag  ){
								
				CommonMessage.debugMsg("commonFilter.getDrillFlag()2:"+commonFilter.getDrillFlag());
				if( fromDetail == null ||( fromDetail != null && ! fromDetail.equals("Y")) )
					if("Quality".equals(module)){
						setBackwardQualityDrillLevel(commonFilter,parentId);
					}
					else if("Safety".equals(module))
					{
						setBackwardSafetyDrillLevel(commonFilter,parentId);
					}
					else if("Training".equals(module)){/*Added by Abinaya.G*/
						setBackwardTrainingDrillLevel(commonFilter,parentId);
					}/*Added by Abinaya.G*/
					else{	
						setBackwardDrillLevel(commonFilter, drillFlag);
					}
				
			}
				
		}
		else
			commonFilter.setDrillFlag(' ');		
	//getValues(commonFilter);	
		getMonth(request,commonFilter);
		//-----------------------------------------------//		
		return commonFilter;
	}

	private static void getMonth(HttpServletRequest request,CommonFilter commonFilter) {
		String monthwise =request.getParameter("chkMonthwise");
		String dateWise = request.getParameter("chkDatewise");
		String FromDate =request.getParameter("dtFromDate");
		String ToDate = request.getParameter("dtToDate");
		String FromMonth = request.getParameter("dtFromMonth");
		String ToMonth=request.getParameter("dtToMonth");
		String defaultdate = request.getParameter("getAllData");
		String firstClick =request.getParameter("firstClick");	
		String finyear=request.getParameter("finyear");
		
		CommonMessage.debugMsg("monthwise :"+monthwise);
		CommonMessage.debugMsg("dateWise :"+dateWise);
		CommonMessage.debugMsg("commonFilter.getMonwise() :"+commonFilter.getMonwise());
		
		
		if(UIUtils.isValidKeyId(finyear)){
			if ("Y".equals(finyear)  && !UIUtils.isValidKeyId(FromMonth))
				FromMonth=getFromMonth();
			
			if ("Y".equals(finyear) && !UIUtils.isValidKeyId(ToMonth))
				ToMonth = CommonFunctions.getDate().substring(3,11);
			
		}
		if(  UIUtils.isValidKeyId(defaultdate) && ("Y").equals(firstClick) && defaultdate.equals("Y")){
			commonFilter.setDefaultDate("Y");
		}	
		else
			commonFilter.setDefaultDate("N");
		
		if(monthwise == null && dateWise == null && commonFilter.getMonwise() == null ) {
			commonFilter.setDefaultFinYear("Y");
		}
			
		if(!UIUtils.isValidKeyId(commonFilter.getMonwise()) && ((dateWise != null  && dateWise.equals("0")) && (monthwise != null  && monthwise.equals("0")))) {
			commonFilter.setMonwise("Y");
			commonFilter.setDefaultFinYear("Y");
		}	
		else if(!UIUtils.isValidKeyId(commonFilter.getMonwise()) && ((dateWise != null  && dateWise.equals("1")) || (monthwise != null  && monthwise.equals("0"))))
			commonFilter.setMonwise("N");
		else if(UIUtils.isValidKeyId(commonFilter.getMonwise()) && ((dateWise != null  && dateWise.equals("1")) || (monthwise != null  && monthwise.equals("0"))))
			commonFilter.setMonwise("N");
		else if(!UIUtils.isValidKeyId(commonFilter.getMonwise()) && ((monthwise == null) || (monthwise != null  && monthwise.equals("1")) ))
			commonFilter.setMonwise("Y");
		else if(((monthwise == null) || (monthwise != null  && monthwise.equals("1")) ))
			commonFilter.setMonwise("Y");
		else 
			commonFilter.setMonwise("Y");
	    
	    
		
		if( ! UIUtils.isValidKeyId(FromDate) && ("Y").equals(firstClick)){
			FromDate = Constants.passNullDate;
		}		
		
		if(!UIUtils.isValidKeyId(ToDate)&& ("Y").equals(firstClick))
			ToDate = Constants.futureNullDate;
		
		if(!UIUtils.isValidKeyId(FromMonth) && ("Y").equals(firstClick))
			FromMonth =CommonFunctions.getFinancialYear(Constants.passNullDate);// Constants.passNullMonth;		
		
		if(!UIUtils.isValidKeyId(ToMonth) && ("Y").equals(firstClick) )
			ToMonth =  CommonFunctions.getDate().substring(3,11); // Constants.futureNullMonth;	
		
		
		
		if(commonFilter.getMonwise() != null && commonFilter.getMonwise().equals("Y") && ("Y").equals(firstClick))
		{
			if(Constants.passNullDate.contains(FromMonth) && ( commonFilter.getDefaultDate()==null || commonFilter.getDefaultDate().equals("N")))
			{
				FromMonth = CommonFunctions.getFirstDateofMonth(-5).substring(3,11);
				ToMonth = CommonFunctions.getDate().substring(3,11);		
			 
			} 
			else if(Constants.passNullDate.contains(FromMonth)){
				commonFilter.setMonwise("Y");
				FromMonth =CommonFunctions.getFinancialYear(Constants.passNullDate);// Constants.passNullMonth;
				ToMonth = CommonFunctions.getDate().substring(3,11);// Constants.futureNullMonth;
			}
		}
		
		if( UIUtils.isValidKeyId(FromDate) )
			commonFilter.setFromDate(FromDate);
		else if(!UIUtils.isValidKeyId(commonFilter.getFromDate()))
			commonFilter.setFromDate(Constants.passNullDate);
		
		if( UIUtils.isValidKeyId(ToDate))
			commonFilter.setToDate(ToDate);
		else if(!UIUtils.isValidKeyId(commonFilter.getToDate()))
			commonFilter.setToDate(Constants.futureNullDate);
		
		if( UIUtils.isValidKeyId(FromMonth))
			commonFilter.setFromMonth(FromMonth);
		else if(!UIUtils.isValidKeyId(commonFilter.getFromMonth()))
			commonFilter.setFromMonth(CommonFunctions.getFinancialYear(Constants.passNullDate));
		
		if( UIUtils.isValidKeyId(ToMonth))
			commonFilter.setToMonth(ToMonth);
		else if(!UIUtils.isValidKeyId(commonFilter.getToMonth()))
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
		
	}

	private static String getFromMonth() {
		String fromMonth=null;
		String currentMonth=null;
		long monthdiff=0;
		String Year=CommonFunctions.getCurrentYear();
		String monthDefault=PrjConstants.financialFromMonth;
		fromMonth=monthDefault+"-"+Year;
		String defaultFromDate="01-"+fromMonth;		
		currentMonth=CommonFunctions.getDate();//"01-Jan-"+Year;	
		CommonMessage.debugMsg("currentMonth"+currentMonth);
		monthdiff=CommonFunctions.getMonthDiff(defaultFromDate,currentMonth);
		if (monthdiff<0){
			Year=String.valueOf(Integer.parseInt(Year)-1);
			fromMonth=monthDefault+"-"+Year;
			CommonMessage.debugMsg("Year"+Year+";fromMonth"+fromMonth);
		} 
				
		return fromMonth;		
	}	
	
	public static CommonFilter getValues(CommonFilter commonFilter){
		String company = commonFilter.getCalibFromDate();
		
		
		return commonFilter;
		
	}
	public static boolean checkDirectLevel(CommonFilter commonFilter) {
		
		if( getComboSelectionId(commonFilter.getMachineRank()))				
			return true;
		else if( getComboSelectionId(commonFilter.getCircle()))				
			return true;
		else if( getComboSelectionId(commonFilter.getEqpGroup()))				
			return true;
		else if( getComboSelectionId(commonFilter.getCostCenter()))				
			return true;
		else if( getComboSelectionId(commonFilter.getAbnJhStep()))				
			return true;

		return false;
	}
	
	private static boolean getComboSelectionId(ComboFilter comboFilter){ 	
		
		if( comboFilter != null && UIUtils.isValidKeyId( comboFilter.getId() ) )
			return true;
		return false;
	}	
	public static void setSortingParams(HttpServletRequest request,CommonFilter commonFilter ){
		 
		String jqGridSortColumn = request.getParameter("sidx");
		String jqGridSortOrder = request.getParameter("sord");
	
		//if( jqGridSortOrder != null && ( jqGridSortOrder.equals("asc") || jqGridSortOrder.equals("desc")))
		//	commonFilter.setGridSortOrder(jqGridSortOrder );
		/*if(  jqGridSortColumn != null &&   UIUtils.isInteger(jqGridSortColumn) && Integer.parseInt(jqGridSortColumn) > 1)
			commonFilter.setGridSortColumn(jqGridSortColumn);*/
		//if(  jqGridSortColumn != null &&  !UIUtils.isInteger(jqGridSortColumn))
		//	commonFilter.setGridSortColumn(jqGridSortColumn);
	}
	
	public static void setPaginationParams(HttpServletRequest request,CommonFilter commonFilter ){
		String jqGridPage = request.getParameter("page");
		String selectRowCount = request.getParameter("rows");
		//String tempRowCount = request.getParameter("tempRows");// madhan pagination
		
		int fromRow = 1,toRow =PrjConstants.JQGRID_PAGINATION_ROWCOUNT;
		if( jqGridPage !=null && jqGridPage.equals("1")){
			commonFilter.setTotalRecordCnt(0);
		}
		if( UIUtils.isValidKeyId(jqGridPage) && UIUtils.isValidKeyId(selectRowCount) && UIUtils.isNumericString(jqGridPage)){
			toRow = Integer.parseInt(jqGridPage)*Integer.parseInt(selectRowCount);//Integer.parseInt(jqGridPage)*Integer.parseInt(tempRowCount);
			fromRow = toRow -  (Integer.parseInt(selectRowCount)-1);// toRow -  (Integer.parseInt(tempRowCount)-1);
			if( UIUtils.isNumericString(Long.toString(commonFilter.getTotalRecordCnt())))
			{
				if(toRow >= commonFilter.getTotalRecordCnt() && commonFilter.getTotalRecordCnt()!=0)
					toRow = (int) commonFilter.getTotalRecordCnt();			 
			}
		}
		
		commonFilter.setFromRow(Integer.toString(fromRow));
		commonFilter.setToRow(Integer.toString(toRow));
		setSortingParams(request,commonFilter);
		List<GridFilter> gridFilterList = populateGridFilters( request);
		if( gridFilterList != null){
			commonFilter.setGridFilter(gridFilterList);
		}
	}
	
	public static void populateGridParams(HttpServletRequest request, GridParams gridparams){
		if( gridparams == null)
			gridparams = new GridParams();
		
		List<GridFilter> gridFilterList = populateGridFilters( request);
		if( gridFilterList != null){
			gridparams.setGridFilters(gridFilterList);
		}
		setPaginationParams(request,gridparams);
		setSortingParams(request,gridparams);
	}
	private static void setSortingParams(HttpServletRequest request,GridParams gridparams){
		 
		String jqGridSortColumn = request.getParameter("sidx");
		String jqGridSortOrder = request.getParameter("sord");
		
		if( jqGridSortOrder != null && ( jqGridSortOrder.equals("asc") || jqGridSortOrder.equals("desc")))
			gridparams.setGridSortOrder(jqGridSortOrder);
		if( jqGridSortColumn != null && UIUtils.isInteger(jqGridSortColumn))
			gridparams.setGridSortColumn(jqGridSortColumn);
		
		
	}
	
	private static void setPaginationParams(HttpServletRequest request,GridParams gridParams){
		String jqGridPage = request.getParameter("page");
		String selectRowCount = request.getParameter("rows");
		int fromRow = 1,toRow =PrjConstants.JQGRID_PAGINATION_ROWCOUNT;
		if( jqGridPage !=null && jqGridPage.equals("1")){
			gridParams.setTotalRecordCnt(0);
		}
		if( UIUtils.isValidKeyId(jqGridPage) && UIUtils.isValidKeyId(selectRowCount) && UIUtils.isNumericString(jqGridPage)){
			toRow = Integer.parseInt(jqGridPage)*Integer.parseInt(selectRowCount);
			fromRow = toRow -  (Integer.parseInt(selectRowCount)-1);
			if( UIUtils.isNumericString(Long.toString(gridParams.getTotalRecordCnt())))
			{
				if(toRow >= gridParams.getTotalRecordCnt() && gridParams.getTotalRecordCnt()!=0)
					toRow = (int) gridParams.getTotalRecordCnt();			 
			}
		}
		
		gridParams.setFromRow(Integer.toString(fromRow));
		gridParams.setToRow(Integer.toString(toRow));
	} 
	
	private static List<GridFilter> populateGridFilters(HttpServletRequest request){
		
		String filters = request.getParameter("filters");
		
		if( UIUtils.isValidKeyId(filters)){
			
			filters = filters.replaceAll("field", "txtField").replaceAll("op", "txtOp").replaceAll("data", "txtData"); // For converting json object to java object , because it does not consider first 3 charaters
			JSONObject filterObj = JSONObject.fromString(filters);
			if( filterObj != null ){
				JSONArray condValues = filterObj.getJSONArray("rules");
				
				if( condValues != null ){
					GridFilter gridFilter = new GridFilter();
					List<GridFilter> gridFilterList = (List<GridFilter>) UIUtils.convertJSONArrToList(gridFilter,  condValues);
					return gridFilterList;
				}
			}	
		}
		return null;
		//else 
		//	commmonFilter.setGridFilter(null);
		
	}
	
	public static void setBackwardDrillLevel(CommonFilter commonFilter,String keyIdVal)
	{
			String prevDrillLevel = commonFilter.getDrillLevel();
			CommonMessage.debugMsg("DrillLevelConstants"+prevDrillLevel);	
			 if(DrillLevelConstants.QUALITYCAUSE.equals(prevDrillLevel))
			{
				commonFilter.getPhenomena().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.PHENOMENA);	
			}
			 else if(DrillLevelConstants.PHENOMENA.equals(prevDrillLevel) )
			{
				if( UIUtils.isValidKeyId(commonFilter.getSkipAssm()) && commonFilter.getSkipAssm().equals("Y") ){
					commonFilter.getMachine().setId(null);
					commonFilter.setDrillLevel(DrillLevelConstants.PROCESS);				
				}else{
					commonFilter.getAssembly().setId(null);
					commonFilter.setDrillLevel(DrillLevelConstants.ASSEMBLY);
				}			
			}
			 else if(DrillLevelConstants.PROCESS.equals(prevDrillLevel))
			{
				commonFilter.getFactory().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.FACT);	
			}
			else if(DrillLevelConstants.ASSEMBLY.equals(prevDrillLevel))
			{
				commonFilter.getMachine().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.MACHINE);	
			}
			
			
			
			//-----------------To Skip Line Drill Down For Skip To Line CheckBox BackWard-----------------//
			else if(DrillLevelConstants.MACHINE.equals(prevDrillLevel))
			{	CommonMessage.debugMsg("DDDDDDDDDDD")	;		
				if(!commonFilter.getChkSkipLine().equals("Y"))
				{
					CommonMessage.debugMsg("ccccccccccc")	;
					commonFilter.getCell().setId(null);
					commonFilter.setDrillLevel(DrillLevelConstants.CELL);
				}
				else
				{
					CommonMessage.debugMsg("elseeeeeeeeeee")	;
					commonFilter.setDrillLevel(DrillLevelConstants.SECTION);
				}
					
			}
			//-------------------End----------------------//
			/*else if(DrillLevelConstants.MACHINE.equals(prevDrillLevel)&& ! commonFilter.isMachineDirect())
			{				
				commonFilter.getCell().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.CELL);
			}*/
			else if(DrillLevelConstants.CELL.equals(prevDrillLevel))
			{
				commonFilter.getSection().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.SECTION);	
			}
			else if(DrillLevelConstants.SECTION.equals(prevDrillLevel))
			{
				//commonFilter.getFactory().setId(null);
				commonFilter.getPbu().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.PBU);
				
			}
			else if(DrillLevelConstants.PBU.equals(prevDrillLevel))
			{
				commonFilter.getSbu().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.SBU);	
			}
			else if(DrillLevelConstants.SBU.equals(prevDrillLevel))
			{
				commonFilter.getLocation().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.LOCATION);	
			}
			else if(DrillLevelConstants.FACTORY.equals(prevDrillLevel))
			{
				commonFilter.getLocation().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.LOCATION);	
				
			}
			else if(DrillLevelConstants.LOCATION.equals(prevDrillLevel))
			{
				commonFilter.getCompany().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.COMPANY);	
			}
			else if(! commonFilter.isMachineDirect())
				commonFilter.setDrillLevel(DrillLevelConstants.COMPANY);
	}
	
	/* Added by Ramya  */
	
	public static void setBackwardSafetyDrillLevel(CommonFilter commonFilter,String keyIdVal)
	{
		String prevDrillLevel = commonFilter.getDrillLevel();
			CommonMessage.debugMsg("prevDrillLevel="+prevDrillLevel);
			if(DrillLevelConstants.INJURY.equals(prevDrillLevel))
			{
				commonFilter.getSection().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.EMPLOYEE);	
			}
			else if(! commonFilter.isMachineDirect())
			{
				CommonMessage.debugMsg("DrillLevelConstants.EMPLOYEE"+DrillLevelConstants.EMPLOYEE);
				commonFilter.setDrillLevel(DrillLevelConstants.COMPANY);
			}
			
	}
	
	/* End of function */
/* Added by Abinaya.G */
	public static void setBackwardQualityDrillLevel(CommonFilter commonFilter,String keyIdVal)
	{
			String prevDrillLevel = commonFilter.getDrillLevel();
			CommonMessage.debugMsg("prevDrillLevel="+prevDrillLevel);
			if(DrillLevelConstants.CELL.equals(prevDrillLevel))
			{
				commonFilter.getSection().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.SECTION);	
			}
			else if(DrillLevelConstants.SECTION.equals(prevDrillLevel))
			{
				commonFilter.getFactory().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
				 CommonMessage.debugMsg("factory ::" + DrillLevelConstants.FACTORY);
			}
			else if(DrillLevelConstants.FACTORY.equals(prevDrillLevel))
			{
				commonFilter.getLocation().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.LOCATION);	
			}
			else if(DrillLevelConstants.LOCATION.equals(prevDrillLevel))
			{
				commonFilter.getCompany().setId(null);
				commonFilter.setDrillLevel(DrillLevelConstants.COMPANY);	
			}
			else if(DrillLevelConstants.CUSTOMER.equals(prevDrillLevel))
			{
				commonFilter.getCustID().setId(null);
				CommonMessage.debugMsg("DrillLevelConstants.CELL"+DrillLevelConstants.CELL);
				commonFilter.setDrillLevel(DrillLevelConstants.CELL);
			}
			else if(DrillLevelConstants.CAUSEFIELD.equals(prevDrillLevel))
			{
				commonFilter.setDrillLevel(DrillLevelConstants.CUSTOMER);
				
			}
			else if(! commonFilter.isMachineDirect())
			{
				CommonMessage.debugMsg("DrillLevelConstants.CELL"+DrillLevelConstants.CELL);
				commonFilter.setDrillLevel(DrillLevelConstants.COMPANY);
			}
			
	}
	public static void setBackwardTrainingDrillLevel(CommonFilter commonFilter,String keyIdVal)
	{
			CommonFilterTraining commonFilterTraining =new CommonFilterTraining();
			String prevDrillLevel = commonFilterTraining.getDrillLevel();
			CommonMessage.debugMsg("prevDrillLevel"+prevDrillLevel);
			if(DrillLevelConstants.EMPLOYEE.equals(prevDrillLevel))
			{
				commonFilterTraining.setDrillLevel(DrillLevelConstants.TRAININGPROC);	
				CommonMessage.debugMsg("commonFilter.setDrillLevel"+commonFilterTraining.getDrillLevel());
			}
			else if(DrillLevelConstants.TRAININGPROC.equals(prevDrillLevel))
			{
				//commonFilter.getFactory().setId(null);
				commonFilterTraining.setDrillLevel(DrillLevelConstants.TRAININGUNIT);
			}
			else if(DrillLevelConstants.TRAININGDEPT.equals(prevDrillLevel))
			{
				//commonFilter.getCustID().setId(null);
				commonFilterTraining.setDrillLevel(DrillLevelConstants.TRAININGCLASS);
			}
			else if(DrillLevelConstants.TRAININGFUNC.equals(prevDrillLevel))
			{
				//commonFilter.getCustID().setId(null);
				commonFilterTraining.setDrillLevel(DrillLevelConstants.TRAININGDEPT);
				
			}
			else if(DrillLevelConstants.TRAININGUNIT.equals(prevDrillLevel))
			{
				//commonFilter.getLocation().setId(null);
				commonFilterTraining.setDrillLevel(DrillLevelConstants.TRAININGCLASS);	
			}
			else if(DrillLevelConstants.TRAININGCLASS.equals(prevDrillLevel))
			{
				//commonFilter.getCompany().setId(null);
				commonFilterTraining.setDrillLevel(DrillLevelConstants.LOCATION);	
			}
			
			
	}
	
/* End of function */
	
	public static  void setDrillLevel(String hiddenId,CommonFilter commonFilter)
	{
		if(hiddenId.substring(0, 3).equals(DrillLevelConstants.COMP))
			commonFilter.setDrillLevel(DrillLevelConstants.COMPANY);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.LOCN))
			commonFilter.setDrillLevel(DrillLevelConstants.LOCATION);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.SBU))
			commonFilter.setDrillLevel(DrillLevelConstants.SBU);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.PBU))
			commonFilter.setDrillLevel(DrillLevelConstants.PBU);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.FACT))
			commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.SECT))
			commonFilter.setDrillLevel(DrillLevelConstants.SECTION);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.CEL))
			commonFilter.setDrillLevel(DrillLevelConstants.CELL);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.MCHM))
			commonFilter.setDrillLevel(DrillLevelConstants.MACHINE);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.INJURY))
			commonFilter.setDrillLevel(DrillLevelConstants.INJURY);
		else if(hiddenId.substring(0, 3).equals(DrillLevelConstants.EMPLOYEE))
			commonFilter.setDrillLevel(DrillLevelConstants.EMPLOYEE);
		
	}
	

	private static void setMainKeyidForDrillDown(CommonFilter commonFilter,String parentId ){
		
		
		String identifier = parentId.substring(0,3);
		CommonMessage.debugMsg("parentId in filtervalues in setmainkeyid fn :"+identifier);
		if(PrjConstants.IDENT_CELL.equals(identifier) && commonFilter.isAssemblyDrillExist() && commonFilter.getAssembly() != null && UIUtils.isValidKeyId(commonFilter.getAssembly().getId() ) )
		{
			parentId = commonFilter.getMachine().getId() ;
			identifier = parentId.substring(0,3);
		}
		ComboFilter  parentCombo= new ComboFilter();
		parentCombo.setId(parentId);
		
		if( identifier.equals(PrjConstants.IDENT_COMPANY) ){
			commonFilter.setCompany(parentCombo);			
			commonFilter.setDrillLevel(DrillLevelConstants.LOCATION);
			
		}
		else if( identifier.equals(PrjConstants.IDENT_LOCATION) ){
			
			commonFilter.setLocation(parentCombo);			
			commonFilter.setDrillLevel(DrillLevelConstants.SBU);
		
		}else if( identifier.equals(PrjConstants.IDENT_SBU) ){
			//commonFilter.setFactory(parentCombo);
			commonFilter.setSbu(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.PBU);
			
		}else if( identifier.equals(PrjConstants.IDENT_PBU) ){
			//commonFilter.setFactory(parentCombo);
			commonFilter.setPbu(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.SECTION);
			
		}
		 
		 //-----------To Skip Line to Skip To Line Check box Checked  Forward------------//
		else if( identifier.equals(PrjConstants.IDENT_SECTION) && commonFilter.getChkSkipLine().equals("Y")){
			
			commonFilter.setSection(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.MACHINE);
		}
		 //-------------------End----------------------------//
		 
		else if( identifier.equals(PrjConstants.IDENT_SECTION) ){
			commonFilter.setSection(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.CELL);
			
		}else if( identifier.equals(PrjConstants.IDENT_CELL)  ){
			commonFilter.setCell(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.MACHINE);
		}else if( identifier.equals(PrjConstants.IDENT_MACHINE) ){
			
			if( UIUtils.isValidKeyId(commonFilter.getSkipAssm()) && commonFilter.getSkipAssm().equals("Y") ){
				commonFilter.setMachine(parentCombo);
				commonFilter.setDrillLevel(DrillLevelConstants.PROCESS);				
			}else{
				commonFilter.setMachine(parentCombo);
				commonFilter.setDrillLevel(DrillLevelConstants.ASSEMBLY);
			}
			
		}else if( identifier.equals(PrjConstants.IDENT_ASSEMBLY) ){
			commonFilter.setAssembly(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.PHENOMENA);
			
		}else if( identifier.equals(PrjConstants.IDENT_QUALITYPROCESS) ){
			commonFilter.setProcess(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.PHENOMENA);
			CommonMessage.debugMsg("process...."+FilterCondSql.getComboSelectionId(commonFilter.getProcess()));
		}else if( identifier.equals(PrjConstants.IDENT_PHRENOMENA) ){
			commonFilter.setPhenomena(parentCombo);
			
		}else if( identifier.equals(PrjConstants.IDENT_QUALITYPHENOMENA) ){
			commonFilter.setPhenomena(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.QUALITYCAUSE);
		}else if( identifier.equals(PrjConstants.IDENT_CUSTOMER) ){
			commonFilter.setCustID(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.CUSTOMER);
		}else if( identifier.equals(PrjConstants.IDENT_CAUSEFEILD) ){
			commonFilter.setCmbcause(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.CAUSEFIELD);
		}
		 
		
	}
	/* Added by Ramya */
	
private static void setMainKeyidForSafetyDrillDown(CommonFilter commonFilter,String parentId ){
		
		
		String identifier = parentId.substring(0,3);
		CommonMessage.debugMsg("identifier"+identifier);
		ComboFilter  parentCombo= new ComboFilter();
		parentCombo.setId(parentId);
		 if( identifier.equals(PrjConstants.IDENT_INJURY) )
		 {
			commonFilter.setCompany(parentCombo);			
			commonFilter.setDrillLevel(DrillLevelConstants.EMPLOYEE);
		 }
		 else if( identifier.equals(PrjConstants.IDENT_EMPLOYEE) )
		 {
			commonFilter.setCompany(parentCombo);			
			commonFilter.setDrillLevel(DrillLevelConstants.EMPLOYEE);
		 }
	}
	/* End of Function */
	/* Added by Abinaya.G */
private static void setMainKeyidForTrainingDrillDown(CommonFilter commonFilter, String parentId ,String keyId){
	
	
	String identifier = parentId.substring(0,3);
	CommonMessage.debugMsg("identifier::"+identifier);
	
	CommonFilterTraining commonFilterTraining = new CommonFilterTraining();
	ComboFilter  parentCombo= new ComboFilter();
	parentCombo.setId(parentId);
	
	 if( identifier.equals(PrjConstants.IDENT_LOCATION) )
	 {
		commonFilter.setDrillLevel(DrillLevelConstants.TRAININGCLASS);
		CommonMessage.debugMsg("identifier::sss"+commonFilter.getDrillLevel());
	 }
	 else if( identifier.equals(PrjConstants.IDENT_CLASMANUFACTURE)) {
		//commonFilter.setCompany(parentCombo);	
		commonFilterTraining.settrarparentid2(keyId);
		commonFilter.setDrillLevel(DrillLevelConstants.TRAININGUNIT); 
		
	 }
	 else if(identifier.equals(PrjConstants.IDENT_CLASNONMANUFACTURE)){
		//commonFilter.setCompany(parentCombo);	
		commonFilterTraining.settrarparentid2(keyId);
		commonFilter.setDrillLevel(DrillLevelConstants.TRAININGDEPT); 
	 }
	 else if( identifier.equals(PrjConstants.IDENT_TRAININGDEPT)){
		 commonFilterTraining.settrarparentiddpt3 (keyId);
		 commonFilter.setDrillLevel(DrillLevelConstants.TRAININGFUNC); 
	 }
	 else if( identifier.equals(PrjConstants.IDENT_TRAININGUNIT)){
		 commonFilterTraining.settrarparentidunt3 (keyId);
		 commonFilter.setDrillLevel(DrillLevelConstants.TRAININGPROC); 
	 }
	 else if( identifier.equals(PrjConstants.IDENT_TRAININGPROC)){
		 commonFilterTraining.settrarparentidpro4 (keyId);
		 commonFilter.setDrillLevel(DrillLevelConstants.EMPLOYEE); 
	 }
	 else if( identifier.equals(PrjConstants.IDENT_TRAININGFUNC)){
			commonFilterTraining.settraparentidfun4(keyId);	
			commonFilter.setDrillLevel(DrillLevelConstants.EMPLOYEE); 
	 }
	 else if( identifier.equals(PrjConstants.IDENT_EMPLOYEE) )
	 {
		commonFilter.setDrillLevel(DrillLevelConstants.EMPLOYEE);
	 }
	 CommonMessage.debugMsg("Drill Level : "+commonFilter.getDrillLevel());
}
private static void setMainKeyidForQualityDrillDown(CommonFilter commonFilter,String parentId ){
		
		
		String identifier = parentId.substring(0,3);
		
		ComboFilter  parentCombo= new ComboFilter();
		parentCombo.setId(parentId);
		if( identifier.equals(PrjConstants.IDENT_COMPANY) ){
			commonFilter.setCompany(parentCombo);			
			commonFilter.setDrillLevel(DrillLevelConstants.LOCATION);
		}
		else if( identifier.equals(PrjConstants.IDENT_LOCATION) ){
			commonFilter.setLocation(parentCombo);			
			commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
		
			//CommonMessage.debugMsg("filter values " +DrillLevelConstants.FACTORY);
		}else if( identifier.equals(PrjConstants.IDENT_FACTORY) ){
			commonFilter.setFactory(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.SECTION);
			
		}else if( identifier.equals(PrjConstants.IDENT_SECTION) ){
			commonFilter.setSection(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.CELL);
			
		}else if( identifier.equals(PrjConstants.IDENT_CELL) ){
			commonFilter.setCell(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.CUSTOMER);
		}else if( identifier.equals(PrjConstants.IDENT_CUSTOMER) ){
			commonFilter.setCustID(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.CAUSEFIELD);
		}else if( identifier.equals(PrjConstants.IDENT_CAUSEFEILD) ){
			commonFilter.setCustID(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.PHENOMENA);
		}else if( identifier.equals(PrjConstants.IDENT_PHRENOMENA) ){
			commonFilter.setCustID(parentCombo);
			commonFilter.setDrillLevel(DrillLevelConstants.PHENOMENA);
		}
		 
		
	}
/* End of Function */
	public static String getHeader(String drillCaption) {
		

		if(drillCaption.equals(DrillLevelConstants.COMPANY))
			return DrillLevelConstants.COMPHEADER;
		else if(drillCaption.equals(DrillLevelConstants.LOCATION))
			return DrillLevelConstants.LOCNHEADER;
		else if(drillCaption.equals(DrillLevelConstants.FACTORY))
			return DrillLevelConstants.FACTHEADER;
		
		////////////////////////////////DONE BY SUGUMAR/////////////////
		else if(drillCaption.equals(DrillLevelConstants.SBU))
			return DrillLevelConstants.SBU;
		else if(drillCaption.equals(DrillLevelConstants.PBU))
			return DrillLevelConstants.PBU;
		/////////////////////////////////////////////////////
		else if(drillCaption.equals(DrillLevelConstants.SECTION))
			return DrillLevelConstants.SECTHEADER;
		else if(drillCaption.equals(DrillLevelConstants.CELL))
			return DrillLevelConstants.CELHEADER;
		else if(drillCaption.equals(DrillLevelConstants.MACHINE))
			return DrillLevelConstants.MCHMHEADER;
		else if(drillCaption.equals(DrillLevelConstants.ASSEMBLY))
			return DrillLevelConstants.ASSMHEADER;
		else if(drillCaption.equals(DrillLevelConstants.PHENOMENA))
			return DrillLevelConstants.PHENHEADER;
		else if(drillCaption.equals(DrillLevelConstants.CUSTOMER))
			return DrillLevelConstants.CUSTHEADER;
		else if(drillCaption.equals(DrillLevelConstants.CAUSEFIELD))
			return DrillLevelConstants.CAUSEFLDHEADER;
		else if(drillCaption.equals(DrillLevelConstants.PROCESSVAL))
			return DrillLevelConstants.PROCESSHEADER;
		else if(drillCaption.equals(DrillLevelConstants.QCAM))
			return DrillLevelConstants.PROCESSHEADER;
		else if(drillCaption.equals(DrillLevelConstants.QTMACHINE))
			return DrillLevelConstants.QTMACHINEHEADER;
		else if(drillCaption.equals(DrillLevelConstants.QTPHENOMENA))
			return DrillLevelConstants.QTPHENOMENAHEADER;
		else if(drillCaption.equals(DrillLevelConstants.QTCAUSE))
			return DrillLevelConstants.QTCAUSEHEADER;
		else if(drillCaption.equals(DrillLevelConstants.INJURY))
			return DrillLevelConstants.INJURY;
		else if(drillCaption.equals(DrillLevelConstants.TRAININGCLASS))
			return DrillLevelConstants.TRAININGCLASSHEADER;
		else if(drillCaption.equals(DrillLevelConstants.TRAININGUNIT))
			return DrillLevelConstants.TRAININGUNITHEADER;
		else if(drillCaption.equals(DrillLevelConstants.TRAININGDEPT))
			return DrillLevelConstants.TRAININGDEPTHEADER;
		else if(drillCaption.equals(DrillLevelConstants.TRAININGPROC))
			return DrillLevelConstants.TRAININGPROCHEADER;
		else if(drillCaption.equals(DrillLevelConstants.TRAININGFUNC))
			return DrillLevelConstants.TRAININGFUNCHEADER;
		else if(drillCaption.equals(DrillLevelConstants.EMPLOYEE))
			return DrillLevelConstants.EMPLOYEEHEADER;
		else if(drillCaption.equals(DrillLevelConstants.SPARES))
			return DrillLevelConstants.SPARESHEADER;
		
		return null;
	}
	private static void setParentId(CommonFilter commonFilter,String parentId){
		CommonMessage.debugMsg("parentId in filtervalues setparent fn:"+parentId);
		String identifier = parentId.substring(0,3);
		CommonMessage.debugMsg("parentId in filtervalues setparent fn:"+identifier);
		ComboFilter  parentCombo= new ComboFilter();
		if (!parentId.equals("CMP") && parentId.length() >3 )
			parentCombo.setId(parentId);
		
		if( identifier.equals(PrjConstants.IDENT_COMPANY) ){
			commonFilter.setCompany(parentCombo);
		}else if( identifier.equals(PrjConstants.IDENT_LOCATION) ){
			commonFilter.setLocation(parentCombo);
		
		}else if( identifier.equals(PrjConstants.IDENT_FACTORY) ){
			commonFilter.setFactory(parentCombo);
		}else if( identifier.equals(PrjConstants.IDENT_SECTION) ){
			commonFilter.setSection(parentCombo);
		}else if( identifier.equals(PrjConstants.IDENT_CELL) ){			
				commonFilter.setCell(parentCombo);
		}else if( identifier.equals(PrjConstants.IDENT_MACHINE) ){
			commonFilter.setMachine(parentCombo);
		}else if( identifier.equals(PrjConstants.IDENT_ASSEMBLY) ){
			commonFilter.setAssembly(parentCombo);
		}else if( identifier.equals(PrjConstants.IDENT_PHRENOMENA) ){
			commonFilter.setPhenomena(parentCombo);
		
	 	}else if( identifier.equals(PrjConstants.IDENT_QUALITYPROCESS) ){
			commonFilter.setProcess(parentCombo);
	 	
		}else if( identifier.equals(PrjConstants.IDENT_QUALITYPHENOMENA) ){
			commonFilter.setPhenomena(parentCombo);
		
		}else if( identifier.equals(PrjConstants.IDENT_CAUSEFEILD) ){
			commonFilter.setCmbcause(parentCombo);
	}
		
	}
	public static CommonFilter getRoleViewFilters(HttpServletRequest request,CommonFilter commonFilter){
		
		String tempVar = null; 
		
		tempVar = request.getParameter("fnLocation");				
		
		commonFilter.setTemp(tempVar);
					
		tempVar = request.getParameter("empKeyId");				
		ComboFilter employee = new ComboFilter();
		employee.setId(tempVar);
		tempVar = request.getParameter("fnLocation");
		
		
		tempVar = request.getParameter("roleKeyId");				
		ComboFilter role = new ComboFilter();
		role.setId(tempVar);
		
		
		
		
		//commonFilter.setLocation(location);
		commonFilter.setEmployee(employee);
		commonFilter.setRoleId(role);
		
		return commonFilter;

		
	}
	public static CommonFilter getAbnRelatedFilters(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar = null; 
		
		tempVar = request.getParameter("cmbabntype");		
		
		
		ComboFilter cmbAbnType = new ComboFilter();
		cmbAbnType.setId(tempVar);		
				
		tempVar = request.getParameter("cmbabncategory");				
		ComboFilter cmbAbnCategory = new ComboFilter();
		cmbAbnCategory.setId(tempVar);
		//cmbAbnCategory.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbabnimpact");				
		ComboFilter cmbAbnImpact = new ComboFilter();
		cmbAbnImpact.setId(tempVar);
		//cmbAbnImpact.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbabnjhstep");				
		ComboFilter cmbAbnmJhStep = new ComboFilter();
		cmbAbnmJhStep.setId(tempVar);
		
		tempVar = request.getParameter("cboabnstatus");				
		ComboFilter cmbAbnStatus = new ComboFilter();
		cmbAbnStatus.setId(tempVar);
		
		tempVar = request.getParameter("cmbdetectedBy");
		ComboFilter cmbDectetedBy = new ComboFilter();
		cmbDectetedBy.setId(tempVar);
		
		tempVar = request.getParameter("cmbAbnmTagclassid");				
		ComboFilter cmbAbnmTagclassid = new ComboFilter();
		cmbAbnmTagclassid.setId(tempVar);
		
		tempVar = request.getParameter("cboAbndImprovementteam");				
		ComboFilter cmbAbnImpType= new ComboFilter();
		cmbAbnImpType.setId(tempVar);
		
		tempVar = request.getParameter("cmbResponsibility");				
		ComboFilter cmbResponsibility= new ComboFilter();
		cmbResponsibility.setId(tempVar);
		
		String Loss = request.getParameter("cboLoss");		
				if(UIUtils.isValidKeyId(Loss))	{
					if(Loss.equals("U"))
						Loss = "UNPLANNED MAINTENANCE";
					else if(Loss.equals("J"))
						Loss = "JH TAG REMOVAL";
					else if(Loss.equals("M"))
						Loss = "M AND A";
				}else
					Loss="";

				String chktradewise = request.getParameter("chkTradewise");

				if( UIUtils.isValidKeyId(chktradewise))
				commonFilter.setChktradewise(chktradewise);
		String safetyPatrol = request.getParameter("cboSafetypatrol");	
		//cmbAbnStatus.setId(tempVar != null ? tempVar:"{}");
		
		String AbnDectBy = request.getParameter("chkdectbychkbox");			
		String AbnDect = request.getParameter("chkdectdtchkbox");		
		String AbnCause = request.getParameter("chkcauschkbox");		
		String AbnCatch = request.getParameter("chkabncatchkbox");	
		String chkAbnType = request.getParameter("chkAbnType");
		String Abnimp = request.getParameter("chkabnimpchkbox");		
		String AbnAllCh = request.getParameter("chkallchkbox");					
		String abnType = request.getParameter("abnType");	
		String abnInprovement = request.getParameter("cboAbndImprovementteam");
		String chkMould = request.getParameter("chkMould");
		String chkWhyWhyHappen = request.getParameter("chkWhyWhyHappen");
		String chkTrade = request.getParameter("chkTrade");
		String chkStatus = request.getParameter("chkStatus");
		String chkTagClass = request.getParameter("chkTagClass");
		String chkHTAType = request.getParameter("chkHTAType");
		String chkAssm = request.getParameter("chkAssm");
		String chkAbnViewComp = request.getParameter("chkAbnViewComp");
		String chkAbnViewIdent = request.getParameter("chkAbnViewIdent");
		String chkCircle = request.getParameter("chkCircle");
		String chkRemoveBlank = request.getParameter("chkRemoveBlank");
		String chkRepeatedAbn = request.getParameter("chkRepeatedAbn");
		
		
		String type = "I";
		if("Y".equals(chkAbnViewComp))
			type = "C";
		else if("Y".equals(chkAbnViewIdent))
			type = "I";
	CommonMessage.debugMsg("chkAbnViewComp..."+chkAbnViewComp+"...chkAbnViewIdent..."+chkAbnViewIdent+"...type..."+type);
		commonFilter.setAbnViewType(type);
		
		if( UIUtils.isValidKeyId(chkRepeatedAbn))
			commonFilter.setRepeatedAbn(chkRepeatedAbn);
		if( UIUtils.isValidKeyId(chkCircle))
			commonFilter.setGroupByCircle(chkCircle);
		if( UIUtils.isValidKeyId(chkAssm))
			commonFilter.setChkAssm(chkAssm);
		if( UIUtils.isValidKeyId(abnType))
			commonFilter.setAbnormalityType(abnType);
		
		if( UIUtils.isValidKeyId(chkWhyWhyHappen))
			commonFilter.setChkWhyWhyHappen(chkWhyWhyHappen);
		
		if( UIUtils.isValidKeyId(chkMould))
			commonFilter.setChkMould(chkMould);
		
		if( UIUtils.isValidKeyId(chkTrade))
			commonFilter.setChkTrade(chkTrade);
		
		if( UIUtils.isValidKeyId(chkStatus))
			commonFilter.setChkStatus(chkStatus);
		
		if( UIUtils.isValidKeyId(chkHTAType))
			commonFilter.setChkHTAType(chkHTAType);
		
		if( UIUtils.isValidKeyId(chkTagClass))
			commonFilter.setChkTagClass(chkTagClass);
	
		
		commonFilter.setAbnType(cmbAbnType);	
		commonFilter.setAbnCategory(cmbAbnCategory);
		commonFilter.setAbnImpact(cmbAbnImpact);
		commonFilter.setAbnJhStep(cmbAbnmJhStep);
		commonFilter.setAbnStatus(cmbAbnStatus);
		commonFilter.setAbnClass(cmbAbnmTagclassid);
		commonFilter.setAbnImpType(cmbAbnImpType);
		commonFilter.setAbnImprovement(abnInprovement);
		commonFilter.setLoss(Loss);
		commonFilter.setSafetyPatrol(safetyPatrol);
		commonFilter.setDectetedBy(cmbDectetedBy);
		commonFilter.setResponsibility(cmbResponsibility);
		commonFilter.setRemoveBlank(chkRemoveBlank);
		
		if(UIUtils.isValidKeyId(AbnDectBy))
			commonFilter.setAbnDetectBy(AbnDectBy);
	
		if(UIUtils.isValidKeyId(chkAbnType))
			commonFilter.setChkAbnType(chkAbnType);
		
		if(UIUtils.isValidKeyId(AbnDect))
			commonFilter.setAbnDetect(AbnDect);
		
		if(UIUtils.isValidKeyId(AbnCause))
			commonFilter.setAbnCause(AbnCause);
	
		if(UIUtils.isValidKeyId(AbnCatch))
			commonFilter.setAbnCatch(AbnCatch);
		
		if(UIUtils.isValidKeyId(Abnimp))
			commonFilter.setAbnImp(Abnimp);
		
		
		if(UIUtils.isValidKeyId(AbnAllCh))
			commonFilter.setAbnAllch(AbnAllCh);
		
		
		
		return commonFilter;
	}
	
	public static CommonFilter getActionPlan(HttpServletRequest request, CommonFilter commonFilter)
	{
		String tempVar = null; 
		
		tempVar = request.getParameter("cmbrefno");				
		ComboFilter cmbRefNo = new ComboFilter();
		cmbRefNo.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbdetectedby");				
		ComboFilter cmbDetectedBy = new ComboFilter();
		cmbDetectedBy.setId(tempVar != null ? tempVar:"{}");
		
		String dFromDate = request.getParameter("dtdFromDate");	
		if(!UIUtils.isValidKeyId(dFromDate))
			dFromDate = Constants.passNullDate;
		
		String dToDate = request.getParameter("dtdToDate");		
		if(!UIUtils.isValidKeyId(dToDate))
			dToDate = Constants.futureNullDate;
		
		String tgtFromDate  = request.getParameter("dttgtFromDate");	
		if(!UIUtils.isValidKeyId(tgtFromDate))
			tgtFromDate = Constants.passNullDate;
		
		String tgtToDate = request.getParameter("dttgtToDate");		
		if(!UIUtils.isValidKeyId(tgtToDate))
			tgtToDate = Constants.futureNullDate;
		
		String CompletedFromDate = request.getParameter("dtcompltdFromDate");
		if(!UIUtils.isValidKeyId(CompletedFromDate))
			CompletedFromDate = Constants.passNullDate;
		
		String CompletedToDate = request.getParameter("dtcompltdToDate");	
		if(!UIUtils.isValidKeyId(CompletedToDate))
			CompletedToDate = Constants.futureNullDate;
		
		tempVar = request.getParameter("cboElapsedDays");				
		ComboFilter cboElapsedDays = new ComboFilter();
		cboElapsedDays.setId(tempVar != null ? tempVar:"{}");
		
		String txtValue = request.getParameter("txtValue");
		String txtnxtValue = request.getParameter("txtnxtValue");
		
		commonFilter.setRefNo(cmbRefNo);
		commonFilter.setDetectedBy(cmbDetectedBy);
		commonFilter.setdFromDate(dFromDate);
		commonFilter.setdToDate(dToDate);
		commonFilter.setTgtFromDate(tgtFromDate);
		commonFilter.setTgtToDate(tgtToDate);
		commonFilter.setCompletedFromDate(CompletedFromDate);
		commonFilter.setCompletedToDate(CompletedToDate);
		commonFilter.setCboElapsedDays(cboElapsedDays);
		commonFilter.setTxtValue(txtValue);
		commonFilter.setTxtnxtValue(txtnxtValue);
		
		
		return commonFilter;
	}
	
	public static CommonFilter getBDRelated(HttpServletRequest request, CommonFilter commonFilter)
	{
		String tempVar = null; 
		
		tempVar = request.getParameter("cmbBDKeyId");				
		ComboFilter cmbBDKeyId = new ComboFilter();
		cmbBDKeyId.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbMSRKeyId");				
		ComboFilter cmbMSRKeyId = new ComboFilter();
		cmbMSRKeyId.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbfailtype");				
		ComboFilter cmbFailtype = new ComboFilter();
		cmbFailtype.setId(tempVar != null ? tempVar:"{}");
		
	/*	tempVar = request.getParameter("cmbPhenomenaId");				
		ComboFilter cmbDefectpheno = new ComboFilter();
		cmbDefectpheno.setId(tempVar != null ? tempVar:"{}");
	*/	
		tempVar = request.getParameter("cmbcause");				
		ComboFilter cmbCause = new ComboFilter();
		cmbCause.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbbdrootcause");				
		ComboFilter cmbbdRootcause = new ComboFilter();
		cmbbdRootcause.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbprodcngroup");		
		ComboFilter cmbProdcngroup = new ComboFilter();
		cmbProdcngroup.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbshiftincharge");				
		ComboFilter cmbShiftincharge = new ComboFilter();
		cmbShiftincharge.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbyyy");				
		ComboFilter cmbyyy	 = new ComboFilter();
		cmbyyy.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbstep");				
		ComboFilter cmbstep	 = new ComboFilter();
		cmbstep.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboJobType");				
		ComboFilter cboJobType = new ComboFilter();
		cboJobType.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboFrequency");				
		ComboFilter cboFrequency = new ComboFilter();
		cboFrequency.setId(tempVar != null ? tempVar:"{}");
		
		String frequencychkbox = request.getParameter("chkfrequencychkbox");
		String severitychkbox = request.getParameter("chkseveritychkbox");
		
		String occurchkbox = request.getParameter("chkoccurchkbox");	
		String timechkbox = request.getParameter("chktimechkbox");	
		String zerobdchkbox = request.getParameter("chkzerobdchkbox");	
		String allchkbox = request.getParameter("chkallchkbox");
		String remallchkbox  = request.getParameter("chkremallchkbox");	
		String fachkbox = request.getParameter("chkfachkbox");	
		String pillarchkbox = request.getParameter("chkpillarchkbox");	
		String rcchkbox = request.getParameter("chkrcchkbox");	
		String rccchkbox = request.getParameter("chkrccchkbox");
		String tradewise = request.getParameter("chktradewise");
		String jobtypwise = request.getParameter("chkjobtypwise");		
		String cmchkbox = request.getParameter("chkcmchkbox");	
		String activitywise = request.getParameter("chkActwise");
		//String monthwise = request.getParameter("chkMonwise");	
		String chkExternal = request.getParameter("chkExternal");
		String chkInternal = request.getParameter("chkInternal");	
		String employee = request.getParameter("chkemployee");	
		String contract = request.getParameter("chkcontract");	
		String spare = request.getParameter("chkspare");	
		String service = request.getParameter("chkservice");
		String utility = request.getParameter("chkutility");
		String other = request.getParameter("chkother");		
		String total = request.getParameter("chktotal");
		String allparameter = request.getParameter("chkallparameter");	
		
		
		
		tempVar = request.getParameter("cmbcost");				
		ComboFilter cmbCost = new ComboFilter();
		cmbCost.setId(tempVar != null ? tempVar:"{}");
		
		String ircchkbox = request.getParameter("chkircchkbox");
		String iyychkbox = request.getParameter("chkiyychkbox");	
		String top = request.getParameter("txttop");	
		
		tempVar = request.getParameter("cbooptions");				
		ComboFilter cboOptions = new ComboFilter();
		cboOptions.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cbostatus");				
		ComboFilter cboStatus = new ComboFilter();
		cboStatus.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cbocounter");				
		ComboFilter cboCountermeasure = new ComboFilter();
		cboCountermeasure.setId(tempVar != null ? tempVar:"{}");
		
		//for breakdown pareto by manikandan
		tempVar = request.getParameter("cboparetooptions");				
		ComboFilter cboparetooptions = new ComboFilter();
		cboparetooptions.setId(tempVar != null ? tempVar:"{}");
		
		//String engineer = request.getParameter("cmbEngineer");	
		tempVar = request.getParameter("cmbEngineer");	
		CommonMessage.debugMsg(";;;;;;;;;;;;;;;;;;; "+request.getParameter("cmbEngineer"));
		ComboFilter cmbEngineer = new ComboFilter();
		cmbEngineer.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cbosparesSelectBox");				
		ComboFilter cboSparesSelectBox = new ComboFilter();
		cboSparesSelectBox.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboselBdType");				
		ComboFilter cboselBdType = new ComboFilter();
		cboselBdType.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboactivityType");		
		CommonMessage.debugMsg("act typ   :"+tempVar);
		ComboFilter cboactivityType = new ComboFilter();
		cboactivityType.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboMchCond");
		ComboFilter cboMchCond = new ComboFilter();
		cboMchCond.setId(tempVar != null ? tempVar:"{}");
		
		String yy = request.getParameter("txtyy");	
		String jhchkbox = request.getParameter("chkjhchkbox");
		String designchkbox  = request.getParameter("chkdesignchkbox");		
		String pmchkbox = request.getParameter("chkpmchkbox");	
		String etchkbox = request.getParameter("chketchkbox");	
		String chkCompleted = request.getParameter("chkCompleted");
		String chkPending = request.getParameter("chkPending");
		String reporttype = request.getParameter("reporttype");
		String chkRemoveBlank = request.getParameter("chkRemoveBlank");
		CommonMessage.debugMsg("chkRemoveBlank...."+chkRemoveBlank);
		String chkActivity = request.getParameter("chkActivity");	
		
		
		String chkundefinedPP = request.getParameter("chkundefinedPP");	
		if(chkundefinedPP != null )
			commonFilter.setChkundefinedPP(chkundefinedPP);
		String chkrepeatedBD = request.getParameter("chkrepeatedBD");	
		if(chkrepeatedBD != null )
			commonFilter.setChkrepeatedBD(chkrepeatedBD);
		if(reporttype != null)
			commonFilter.setRepotingType(reporttype);
		
		commonFilter.setCmbFailureType(cmbFailtype);
		commonFilter.setCmbBreakdown(cmbBDKeyId);
		commonFilter.setCmbMsr(cmbMSRKeyId);
		//commonFilter.setCmbdefectpheno(cmbDefectppkplheno);
		commonFilter.setCmbcause(cmbCause);
		commonFilter.setCmbstep(cmbstep);
		if(cmbbdRootcause != null )
		commonFilter.setCmbbdRootCause(cmbbdRootcause);
		commonFilter.setCmbprodcngroup(cmbProdcngroup);
		commonFilter.setCmbshiftIncharge(cmbShiftincharge);
		if(frequencychkbox != null )
		commonFilter.setChkfrequencychkbox(frequencychkbox);
		if(severitychkbox != null )
			commonFilter.setChkseveritychkbox(severitychkbox);			
		if(occurchkbox != null )
			commonFilter.setChkoccurchkbox(occurchkbox);
		if(timechkbox != null )
			commonFilter.setChktimeChkBox(timechkbox);
		commonFilter.setChkzeroBdChkBox(zerobdchkbox);
		if(allchkbox != null )
			commonFilter.setChkallChkBox(allchkbox);
		commonFilter.setChkremallchkbox(remallchkbox);
		commonFilter.setCompleted(chkCompleted);
		commonFilter.setPending(chkPending);
		commonFilter.setChkfachkbox(fachkbox);
		commonFilter.setChkpillarchkbox(pillarchkbox);
		commonFilter.setChkrcchkbox(rcchkbox);
		commonFilter.setChkrccchkbox(rccchkbox);
		commonFilter.setChkcmchkbox(cmchkbox);
		commonFilter.setCost(cmbCost);
		commonFilter.setChkircchkbox(ircchkbox);
		commonFilter.setChkiyychkbox(iyychkbox);
		commonFilter.setRemoveBlank(chkRemoveBlank);
		commonFilter.setChkundefinedPP(chkundefinedPP);
		commonFilter.setBdActivity(chkActivity);
		
		CommonMessage.debugMsg("test remove blank..............."+commonFilter.getRemoveBlank());
		if(tradewise != null )
			commonFilter.setChktradewise(tradewise);
		if(jobtypwise != null )
			commonFilter.setChkjobtypwise(jobtypwise);
		if(activitywise != null )
			commonFilter.setChkActwise(activitywise);
		//if(monthwise != null )
			//commonFilter.setChkMonwise(monthwise);
		commonFilter.setTxttop(top);
		commonFilter.setCbooptions(cboOptions);
		commonFilter.setBdstatus(cboStatus);
		commonFilter.setCounterMeasure(cboCountermeasure);
		commonFilter.setCboparetooptions(cboparetooptions);
		commonFilter.setChkExternal(chkExternal);
		commonFilter.setChkInternal(chkInternal);		
		commonFilter.setCboFrequency(cboFrequency);
		commonFilter.setCboJobType(cboJobType);
	 	commonFilter.setCmbEngineer(cmbEngineer);
	 	CommonMessage.debugMsg("cmbEngineer :: "+ cmbEngineer);
		commonFilter.setCbosparesSelectBox(cboSparesSelectBox);
		//commonFilter.setCmbsparesSelectBox(cboSparesSelectBox);
		commonFilter.setCboselBdType(cboselBdType);
		commonFilter.setcboActivitytype(cboactivityType);
		commonFilter.setCmbyy(cmbyyy);
		commonFilter.setChkjhchkbox(jhchkbox);
		commonFilter.setChkdesignchkbox(designchkbox);
		commonFilter.setChkpmchkbox(pmchkbox);
		commonFilter.setChketchkbox(etchkbox);
		if(employee != null )
		commonFilter.setChkemployee(employee);
		if(contract != null )
		commonFilter.setChkcontract(contract);
		if(spare != null )
		commonFilter.setChkspare(spare);
		if(service != null )
		commonFilter.setChkservice(service);
		if(utility != null )
		commonFilter.setChkutility(utility);
		if(other != null )
		commonFilter.setChkother(other);
		if(total != null )
		commonFilter.setChktotal(total);
		if(allparameter != null )
		commonFilter.setChkallparameter(allparameter);
		commonFilter.setBdMechCond(cboMchCond);
		
		return commonFilter;
	}
	
	
	public static CommonFilter getCalibration(HttpServletRequest request,CommonFilter commonFilter)
	{
		
		String tempVar = null; 
		
		tempVar = request.getParameter("cmbimte");				
		ComboFilter cmbimte = new ComboFilter();
		cmbimte.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbslno");				
		ComboFilter cmbSlno = new ComboFilter();
		cmbSlno.setId(tempVar != null ? tempVar:"{}");		
		
		String CalibFromDate = request.getParameter("dtcalibFromDate");	
		if(!UIUtils.isValidKeyId(CalibFromDate))
			CalibFromDate = Constants.passNullDate;
		
		String CalibToDate = request.getParameter("dtcalibToDate");		
		if(!UIUtils.isValidKeyId(CalibToDate))
			CalibToDate = Constants.futureNullDate;
				
		tempVar = request.getParameter("cmbgauge");				
		ComboFilter cmbGauge = new ComboFilter();
		cmbGauge.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbparameter");				
		ComboFilter cmbParameter = new ComboFilter();
		cmbParameter.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbexternalagencies");				
		ComboFilter cmbExternalagencies = new ComboFilter();
		cmbExternalagencies.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbmethod");				
		ComboFilter cmbMethod = new ComboFilter();
		cmbMethod.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbdecision");				
		ComboFilter cmbDecision = new ComboFilter();
		cmbDecision.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbcompltdby");				
		ComboFilter cmbCompltdby = new ComboFilter();
		cmbCompltdby.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbissuedto");				
		ComboFilter cmbIssuedto = new ComboFilter();
		cmbIssuedto.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbssuedtocalib");				
		ComboFilter cmbIssuedtocalib = new ComboFilter();
		cmbIssuedtocalib.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbservicetype");				
		ComboFilter cmbServicetype = new ComboFilter();
		cmbServicetype.setId(tempVar != null ? tempVar:"{}");
		
		String exportchkbox = request.getParameter("chkexportchkbox");				
		
		commonFilter.setImte(cmbimte);
		commonFilter.setSlno(cmbSlno);
		commonFilter.setCalibFromDate(CalibFromDate);
		commonFilter.setCalibToDate(CalibToDate);
		commonFilter.setGauge(cmbGauge);
		commonFilter.setParameter(cmbParameter);
		commonFilter.setExternalagencies(cmbExternalagencies);
		commonFilter.setMethod(cmbMethod);
		commonFilter.setDecision(cmbDecision);
		commonFilter.setCompltdby(cmbCompltdby);
		commonFilter.setIssuedto(cmbIssuedtocalib);
		commonFilter.setSsuedtocalib(cmbIssuedtocalib);
		commonFilter.setServicetype(cmbServicetype);
		commonFilter.setExportchkbox(exportchkbox);
		
		return commonFilter;
		
	}
	
	public static CommonFilter getEquipmentRelated(HttpServletRequest request,CommonFilter commonFilter )
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbJHStep");				
		ComboFilter cmbJHStep = new ComboFilter();
		cmbJHStep.setId(tempVar);
		
		//cmbJHStep.setId(tempVar != null ? tempVar:"{}");
		
	//	tempVar = request.getParameter("cmbMachineRankid");			
	//	ComboFilter cmbMachineRankid = new ComboFilter();
	//	cmbMachineRankid.setId(tempVar);
		//cmbMachineRankid.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbShiftid");				
		ComboFilter cmbShiftid = new ComboFilter();
		cmbShiftid.setId(tempVar);
		//cmbShiftid.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbSupervisorid");		
		ComboFilter cmbSupervisorid = new ComboFilter();
		cmbSupervisorid.setId(tempVar);
		//cmbSupervisorid.setId(tempVar != null ? tempVar:"{}");
		
		
		tempVar = request.getParameter("cmbPurposeid");			
		ComboFilter cmbPurposeid = new ComboFilter();
		cmbPurposeid.setId(tempVar);
		//cmbPurposeid.setId(tempVar != null ? tempVar:"{}");
		
		
		tempVar = request.getParameter("cmbCategoryid");		
		ComboFilter cmbCategoryid = new ComboFilter();
		cmbCategoryid.setId(tempVar);
		//cmbCategoryid.setId("{}");
		
		tempVar = request.getParameter("cmbSubCategoryid");				
		ComboFilter cmbSubCategoryid = new ComboFilter();	
		cmbSubCategoryid.setId(tempVar);
		//cmbSubCategoryid.setId(tempVar != null ? tempVar:"{}");
		
		String installFrom = request.getParameter("dtInstallFromDate");	
		
		if(!UIUtils.isValidKeyId(installFrom))
			installFrom = Constants.passNullDate;
			
		
		String installTo = request.getParameter("dtInstallToDate");		

		if(!UIUtils.isValidKeyId(installTo))
			installTo = Constants.futureNullDate;
		
		String amcFromDate = request.getParameter("dtAmcFromDate");	
		if(!UIUtils.isValidKeyId(amcFromDate))
			amcFromDate = Constants.passNullDate;
		
		String amcToDate = request.getParameter("dtAmcToDate");				
		if(!UIUtils.isValidKeyId(amcToDate))
			amcToDate = Constants.futureNullDate;
		
		tempVar = request.getParameter("cmbCategorized");
		if( tempVar != null && tempVar.length() > 0)
			commonFilter.setBdCategorized(tempVar);
		
		/*ComboFilter cmbCategorized = new ComboFilter();
		cmbCategorized.setId(tempVar != null ? tempVar:"{}");
	*/	
		String AmcRenewal = request.getParameter("txtAmcRenewal");		
		String WarrantyExpries = request.getParameter("txtWarrantyExpires");		
		
		String actType = request.getParameter("actType");	
		
		
		commonFilter.setJhStep(cmbJHStep);
//		commonFilter.setMachineRank(cmbMachineRankid);
		commonFilter.setShift(cmbShiftid);
		commonFilter.setSupervisor(cmbSupervisorid);
		commonFilter.setPurpose(cmbPurposeid);
		commonFilter.setCategory(cmbCategoryid);
		commonFilter.setInstalfrom(installFrom);
		commonFilter.setInstalTo(installTo);
		commonFilter.setAmcFrom(amcFromDate);
		commonFilter.setAmcTo(amcToDate);
		//commonFilter.setCategory(cmbCategoryid);
		commonFilter.setAmcRenewal(AmcRenewal);
		commonFilter.setSubCategory(cmbSubCategoryid);
		commonFilter.setWarrantyExpries(WarrantyExpries);
		commonFilter.setEqpActType(actType);
		
		String chk_bd = request.getParameter("BREAKDOWN");	
		commonFilter.setBREAKDOWN(chk_bd);
		String chk_pm = request.getParameter("PREVENTIVE");	
		commonFilter.setPREVENTIVE(chk_pm);
		String chk_im = request.getParameter("IMPROVEMENT");	
		commonFilter.setIMPROVEMENT(chk_im);
		String chk_abn = request.getParameter("ABNORMALITY");	
		commonFilter.setABNORMALITY(chk_abn);
		String chk_opl = request.getParameter("OPL");	
		commonFilter.setOPL(chk_opl);
		String chk_kaizen = request.getParameter("KAIZEN");	
		commonFilter.setKAIZEN(chk_kaizen);
		String chk_General = request.getParameter("GENERAL");
		commonFilter.setGENERAL(chk_General);
		String chk_Unplanned = request.getParameter("UNPLANNED");//added By KarthicK.T for Equipment History report
		commonFilter.setUNPLANNED(chk_Unplanned);
		
		
		return commonFilter;
	}
	public static CommonFilter getJHCLIT(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbJHStep");		
		ComboFilter cmbJHStep = new ComboFilter();
		cmbJHStep.setId(tempVar);
		
		tempVar = request.getParameter("cmbjhfreq");	
		ComboFilter cmbjhfreq = new ComboFilter();
		cmbjhfreq.setId(tempVar);
		
		tempVar = request.getParameter("cmbjhduration");		
		ComboFilter cmbjhduration = new ComboFilter();
		cmbjhduration.setId(tempVar);
				
		
		String txtDurFrom = request.getParameter("txtDurFrom");			
		String txtDurTo = request.getParameter("txtDurTo");		
		
		tempVar = request.getParameter("cmbprodcngroup");		
		ComboFilter cmbprodcngroup = new ComboFilter();
		cmbprodcngroup.setId(tempVar);
		
		CommonMessage.debugMsg("mainKeyid  :");
		String mainKeyid = commonFilter.getMainkeyid();
		CommonMessage.debugMsg("mainKeyid  :"+mainKeyid);
		
		commonFilter.setJhStep(cmbJHStep);
		commonFilter.setFrequency(cmbjhfreq);
		commonFilter.setJhduration(cmbjhduration);
		commonFilter.setTxtDurTo(txtDurTo);
		commonFilter.setCmbprodcngroup(cmbprodcngroup);
		//commonFilter.setMainkeyid(mainKeyid);
		
		//tempVar = request.getParameter("cmbjhduration");				
		//commonFilter.setDu
		
		
		
		//commonFilter.setJhduration(cmbJhduration);
		
		return commonFilter;
	}
	
	public static CommonFilter getManPowerCost(HttpServletRequest request, CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbgrade");				
		ComboFilter cmbGrade = new ComboFilter();
		cmbGrade.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbemp");				
		ComboFilter cmbEmp = new ComboFilter();
		cmbEmp.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbcost");				
		ComboFilter cmbCost = new ComboFilter();
		cmbCost.setId(tempVar != null ? tempVar:"{}");
		
		commonFilter.setGRADEID(cmbGrade);
		commonFilter.setEmp(cmbEmp);
		commonFilter.setCost(cmbCost);
		
		return commonFilter;
	}
	
	public static CommonFilter getCostInfo(HttpServletRequest request, CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbgrade");				
		ComboFilter cmbGrade = new ComboFilter();
		cmbGrade.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbemp");				
		ComboFilter cmbEmp = new ComboFilter();
		cmbEmp.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbReportType");
		
		if (!UIUtils.isValidKeyId(tempVar) )
			tempVar = "BDM";
		
		ComboFilter cmbReportType = new ComboFilter();
		cmbReportType.setId(tempVar != null ? tempVar:"{}");
		
		commonFilter.setGRADEID(cmbGrade);
		commonFilter.setEmp(cmbEmp);		
		commonFilter.setReportType(cmbReportType);
		
		return commonFilter;
	}	
	
	public static CommonFilter getManPowerUtilization(HttpServletRequest request,CommonFilter commonFilter)
	{
		
		String tempVar=null;
		
		tempVar = request.getParameter("cmbteam");				
		ComboFilter cmbTeam = new ComboFilter();
		cmbTeam.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbdept");				
		ComboFilter cmbDept = new ComboFilter();
		cmbDept.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbtranstyp");				
		ComboFilter cmbTranstyp = new ComboFilter();
		cmbTranstyp.setId(tempVar != null ? tempVar:"{}");
		
		String minschkbox = request.getParameter("chkminschkbox");	
		String hrschkbox = request.getParameter("chkhrschkbox");
		String planchkbox = request.getParameter("chkplanchkbox");	
		String actualchkbox  = request.getParameter("chkactualchkbox");				
		
		commonFilter.setTeam(cmbTeam);
		commonFilter.setDept(cmbDept);
		commonFilter.setTranstyp(cmbTranstyp);
		commonFilter.setMinschkbox(minschkbox);
		commonFilter.setHrschkbox(hrschkbox);
		commonFilter.setPlanchkbox(planchkbox);
		commonFilter.setActualchkbox(actualchkbox);
		
		
		return commonFilter;
	}
	
	public static CommonFilter getOPLandKaizen(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar = null; 
		tempVar = request.getParameter("cmbOplNoid");				
		ComboFilter cmbOplNoid = new ComboFilter();
		cmbOplNoid.setId(tempVar != null ? tempVar:"{}");
		
		/*tempVar = request.getParameter("cmbKaizencategory");				
		ComboFilter cmbKaizencategory = new ComboFilter();
		cmbKaizencategory.setId(tempVar != null ? tempVar:"{}");*/
		
		tempVar = request.getParameter("cmbKznmThemecategoryid");				
		ComboFilter cmbKaizenThemecategory = new ComboFilter();
		cmbKaizenThemecategory.setId(tempVar != null ? tempVar:"{}");
				
		tempVar = request.getParameter("cmbImprovmntNoid");				
		ComboFilter cmbImprovmntNoid = new ComboFilter();
		cmbImprovmntNoid.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbPillarid");				
		ComboFilter cmbPillarid = new ComboFilter();
		cmbPillarid.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbLossid");				
		ComboFilter cmbLossid = new ComboFilter();
		cmbLossid.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboOplTypeid");	
		ComboFilter cmbOplTypeid = new ComboFilter();
		cmbOplTypeid.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboKznStatus");	
		ComboFilter cboKznStatus = new ComboFilter();
		cboKznStatus.setId(tempVar != null ? tempVar:"{}");
		
		String dteImprovementDate = request.getParameter("dteKznDate");	
		if(!UIUtils.isValidKeyId(dteImprovementDate))
			dteImprovementDate = Constants.passNullDate;
		
		String GrpByCellWise=request.getParameter("chkGrpByCellid");
		String BK  = request.getParameter("chkboxBK");
		String IC = request.getParameter("chkboxIC");
		String TC  = request.getParameter("chkboxTC");	
		String DM = request.getParameter("chkboxDM");
		String ET = request.getParameter("chkboxET");
		String JH = request.getParameter("chkboxJH");
		String KK = request.getParameter("chkboxKK");
		String OTpm = request.getParameter("chkboxOTpm");	
		String Pm = request.getParameter("chkboxPm");	
		String Qm  = request.getParameter("chkboxQm");	
		String She = request.getParameter("chkboxShe");	
		String SectWise = request.getParameter("chkboxSectWise");	
		String CellWise = request.getParameter("chkboxCellWise");
		String EqptWise = request.getParameter("chkboxEqptWise");
		String LossWise = request.getParameter("chkboxLossWise");	
		String PillarWise = request.getParameter("chkboxPillarWise");	
		String boxResultWise = request.getParameter("chkboxResultWise");	
		String EqptGrpWise = request.getParameter("chkboxEqptGrpWise");		
		String RemoveBlank = request.getParameter("chkRemoveBlank");
		String MPWorthy = request.getParameter("chkMPWorthy");
		String UtiliseFuture = request.getParameter("chkUtiliseFuture");
		String mainGroup = request.getParameter("mainGroup");
		String subGroupHD = request.getParameter("subGroupHD");
		String LossId = request.getParameter("LossId");
		String colValue = request.getParameter("colId");
		
		String ResultAreaP=request.getParameter("chkResultAreaP");
		String ResultAreaQ=request.getParameter("chkResultAreaQ");
		String ResultAreaC=request.getParameter("chkResultAreaC");
		String ResultAreaD=request.getParameter("chkResultAreaD");
		String ResultAreaS=request.getParameter("chkResultAreaS");
		String ResultAreaM=request.getParameter("chkResultAreaM");
		String ResultAreaE=request.getParameter("chkResultAreaE");
		commonFilter.setOplNoid(cmbOplNoid);
		//commonFilter.setJHKaizenCategory(cmbKaizencategory);
		commonFilter.setKznmThemecategoryid(cmbKaizenThemecategory);
		commonFilter.setImprovmntNoid(cmbImprovmntNoid);
		commonFilter.setPillarid(cmbPillarid);
		commonFilter.setOplTypeid(cmbOplTypeid);
		commonFilter.setLossType(cmbLossid);
		commonFilter.setStatuss(cboKznStatus);
		commonFilter.setGrpByCellWise(GrpByCellWise);
		commonFilter.setImprovementDate(dteImprovementDate);
		commonFilter.setBK(BK);
		commonFilter.setIC(IC);
		commonFilter.setTC(TC);
		commonFilter.setDM(DM);
		commonFilter.setET(ET);
		commonFilter.setJH(JH);
		commonFilter.setKK(KK);
		commonFilter.setOTpm(OTpm);
		commonFilter.setPm(Pm);
		commonFilter.setQm(Qm);
		commonFilter.setShe(She);
		commonFilter.setSectWise(SectWise);
		commonFilter.setCellWise(CellWise);
		commonFilter.setEqptWise(EqptWise);
		commonFilter.setLossWise(LossWise);
		commonFilter.setPillarWise(PillarWise);
		commonFilter.setBoxResultWise(boxResultWise);
		commonFilter.setEqptGrpWise(EqptGrpWise);
		commonFilter.setLossId(LossId);
		commonFilter.setColVal(colValue);
		commonFilter.setMainGroup(mainGroup);
		commonFilter.setSubGroupHD(subGroupHD);
		commonFilter.setRemoveBlank(RemoveBlank);
		commonFilter.setMPWorthy(MPWorthy);
		commonFilter.setUtiliseFuture(UtiliseFuture);
		commonFilter.setResultAreaP(ResultAreaP);
		commonFilter.setResultAreaQ(ResultAreaQ);
		commonFilter.setResultAreaC(ResultAreaC);
		commonFilter.setResultAreaD(ResultAreaD);
		commonFilter.setResultAreaS(ResultAreaS);
		commonFilter.setResultAreaM(ResultAreaM);
		commonFilter.setResultAreaE(ResultAreaE);
		//CommonMessage.debugMsg("removeBlank......................"+commonFilter.getRemoveBlank());
		return commonFilter;
	}
	
	public static CommonFilter getPCS(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbpcssubgrp");				
		ComboFilter cmbPcssubgrp = new ComboFilter();
		cmbPcssubgrp.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbpcsprrod");				
		ComboFilter cmbPcsprrod = new ComboFilter();
		cmbPcsprrod.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("rawMaterial");	
		CommonMessage.debugMsg("raw Material values.............."+ tempVar);
		ComboFilter cmbRawMaterial = new ComboFilter();
		cmbRawMaterial.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbLossid");				
		ComboFilter cmbLossid = new ComboFilter();
		cmbLossid.setId(tempVar != null ? tempVar:"{}");
		
		String pcsshift = request.getParameter("cmbshift");				
		
		
		commonFilter.setRawMatrial(cmbRawMaterial);
		
		String ef = request.getParameter("chkboxef");				
		String df = request.getParameter("chkboxdf");				
		String ar= request.getParameter("chkboxar");				
		String pr  = request.getParameter("chkboxpr");				
		String qr = request.getParameter("chkboxqr");
		String oee = request.getParameter("chkboxoee");				
		String all = request.getParameter("chkboxall");				
		String occurence = request.getParameter("chkboxoccurence");				
		String time = request.getParameter("chkboxtime");				
		//String vai = request.getParameter("chkboxvai");				
		String hour = request.getParameter("chkboxhour");				
		String shift = request.getParameter("chkboxshift");				
		String day = request.getParameter("chkboxday");		
		String week = request.getParameter("chkboxweek");				
		String WoNo = request.getParameter("chkboxWoNo");
		String Prod = request.getParameter("chkboxProd");
		String pcsShift = request.getParameter("chkboxPcsShift");
		String chkboxPcsDate = request.getParameter("chkboxPcsDate");
		String chkboxMgrCal = request.getParameter("chkboxMgrCal");
		String chkRemoveBlank = request.getParameter("chkRemoveBlank");
		String chkSubLoss = request.getParameter("chkSubLoss");
		String lossRptType = request.getParameter("cboLossRptType");
		String multiLoss = request.getParameter("chkMultiLoss");
		
		commonFilter.setMultiLoss(multiLoss);
		commonFilter.setChkboxMgrCal(chkboxMgrCal);
		commonFilter.setPcsDate(chkboxPcsDate);
		commonFilter.setCmbpcssubgrp(cmbPcssubgrp);
		commonFilter.setcCmbpcsprrod(cmbPcsprrod);
		commonFilter.setPcsShift(pcsShift);
		commonFilter.setChkboxef(ef);
		commonFilter.setChkboxdf(df);
		commonFilter.setSubLoss(chkSubLoss);
		if(ar != null )
		commonFilter.setChkboxar(ar);
		if(pr != null )
		commonFilter.setChkboxpr(pr);
		if(qr != null )
		commonFilter.setChkboxqr(qr);
		if(oee != null )
		commonFilter.setChkboxoee(oee);
		if(all != null )
		commonFilter.setChkboxall(all);
		commonFilter.setChkboxoccurence(occurence);
		commonFilter.setChkboxtime(time);
		//commonFilter.setChkboxvai(vai);
		commonFilter.setChkboxhour(hour);
		commonFilter.setChkboxshift(shift);
		commonFilter.setChkboxday(day);
		commonFilter.setChkboxweek(week);
		commonFilter.setISNEEDWONO(WoNo);
		commonFilter.setISNEEDPROD(Prod);
		commonFilter.setLossType(cmbLossid);
		commonFilter.setPcsShift(pcsshift);
		commonFilter.setRemoveBlank(chkRemoveBlank);
		commonFilter.setLossRptType(lossRptType);
		return commonFilter;
	}
	
	public static CommonFilter getPMRelated(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbjobtype");				
		ComboFilter cmbJobtype = new ComboFilter();
		cmbJobtype.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboMachCond");				
		ComboFilter cmbMechCond = new ComboFilter();
		cmbMechCond.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbTradeid");				
		ComboFilter cmbTradeId = new ComboFilter();
		cmbTradeId.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbactivities");				
		ComboFilter cmbActivities = new ComboFilter();
		cmbActivities.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbsupplier");				
		ComboFilter cmbsupplier = new ComboFilter();
		cmbsupplier.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbtools");				
		ComboFilter cmbtools = new ComboFilter();
		cmbtools.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbSource");	
		ComboFilter cmbsources = new ComboFilter();
		cmbsources.setId(tempVar != null ? tempVar:"{}");
		
		tempVar  = request.getParameter("cmbfreq");				
		ComboFilter cmbfreq = new ComboFilter();
		cmbfreq.setId(tempVar != null ? tempVar:"{}");
		
		String noofdays = request.getParameter("chknoofdayschkbox");				
		
		tempVar  = request.getParameter("cmbpmsource");				
		ComboFilter cmbpmsource = new ComboFilter();
		cmbpmsource.setId(tempVar != null ? tempVar:"{}");
		
		tempVar  = request.getParameter("cmbeqpCondn");				
		ComboFilter cmbeqpCondn = new ComboFilter();
		cmbeqpCondn.setId(tempVar != null ? tempVar:"{}");
		
		String includestatus = request.getParameter("chkincludestatuschkbox");				
		
		/*tempVar  = request.getParameter("cmbpmstatus");				
		ComboFilter cmbpmstatus = new ComboFilter();
		cmbpmstatus.setId(tempVar != null ? tempVar:"{}");*/
		
		String cmbpmstatus = request.getParameter("cmbpmstatus");		
		
		tempVar  = request.getParameter("cmbdrillfor");				
		ComboFilter cmbdrillfor = new ComboFilter();
		cmbdrillfor.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbJhStep");				
		ComboFilter cmbJHStep = new ComboFilter();
		cmbJHStep.setId(tempVar);
		
		tempVar = request.getParameter("cmbprodcngroup");		
		ComboFilter cmbProdcngroup = new ComboFilter();
		cmbProdcngroup.setId(tempVar != null ? tempVar:"{}");
		String trDID = request.getParameter("cmbTradeid");
		
		CommonMessage.debugMsg(" ~~AR~~ "+trDID);
		String remblank = request.getParameter("chkremblankchkbox");				
		String comp= request.getParameter("chkcompchkbox");				
		String pend = request.getParameter("chkpendchkbox");				
		String emp = request.getParameter("chkempchkbox");				
		String contractor = request.getParameter("chkcontractorchkbox");				
		String spare = request.getParameter("chksparechkbox");				
		String service = request.getParameter("chkservicechkbox");				
		String util = request.getParameter("chkutilchkbox");				
		String other = request.getParameter("chkotherchkbox");	
		String total = request.getParameter("chktotalchkbox");				
		String ap = request.getParameter("chkapchkbox");				
		String tw = request.getParameter("chktwchkbox");				
		String jtw  = request.getParameter("chkjtwchkbox");	
		
		tempVar = request.getParameter("cmbparam");			
		ComboFilter cmbparam = new ComboFilter();
		cmbparam.setId(tempVar != null ? tempVar:"{}");		
		
		String actwise = request.getParameter("chkactwisechkbox");				
		String monwise = request.getParameter("chkmonwisechkbox");				
		String awise = request.getParameter("chkawisechkbox");				
		String atype = request.getParameter("chkatypechkbox");				
		String mwise = request.getParameter("chkmwisechkbox");				
		String summary = request.getParameter("chksummarychkbox");				
		String sect = request.getParameter("chksectchkbox");				
		String cell = request.getParameter("chkcellchkbox");	
		String eqpmnt = request.getParameter("chkeqpmntchkbox");		
		String txtDuration = request.getParameter("txtDuration");	
		String chkEquipmentWise = request.getParameter("chkEquipmentWise");
		String chkMachineWise = request.getParameter("chkMachineWise");
		
		commonFilter.setMachineWise(chkEquipmentWise);		
		commonFilter.setEquipmentWise(chkMachineWise);
		String chkRemoveBlank = request.getParameter("chkRemoveBlank");
		String chkMonth = request.getParameter("chkMonth"); //Created By KarthicK.T for PMCompliance Report
		String chkWeek = request.getParameter("chkWeek"); //Created By KarthicK.T for PMCompliance Report
		String chkActType =request.getParameter("chkActTyp");
		CommonMessage.debugMsg("chkActTyp....."+chkActType);
		
		commonFilter.setJobtype(cmbJobtype);
		commonFilter.setChkActType(chkActType);
		commonFilter.setActivities(cmbActivities);
		commonFilter.setSupplier(cmbsupplier);
		commonFilter.setMechCond(cmbMechCond);
		commonFilter.setTools(cmbtools);
		commonFilter.setSources(cmbsources);
		commonFilter.setFrequency(cmbfreq);
		commonFilter.setNoofdays(noofdays);
		commonFilter.setPmsource(cmbpmsource);
		commonFilter.setEqpCondn(cmbeqpCondn);
		commonFilter.setIncludestatus(includestatus);
		commonFilter.setPmstatus(cmbpmstatus);
		commonFilter.setDrillfor(cmbdrillfor);
		commonFilter.setRemblank(remblank);
		commonFilter.setComp(comp);
		commonFilter.setPend(pend);
		commonFilter.setEmpch(emp);
		commonFilter.setContractor(contractor);
		commonFilter.setSpares(spare);
		commonFilter.setService(service);
		commonFilter.setUtil(util);
		commonFilter.setOther(other);
		commonFilter.setTotal(total);
		commonFilter.setAp(ap);
		commonFilter.setTw(tw);
		commonFilter.setJtw(jtw);	
		commonFilter.setParam(cmbparam);
		commonFilter.setActwise(actwise);
		//commonFilter.setMonwise(monwise);
		commonFilter.setAwise(awise);
		commonFilter.setAtype(atype);
		commonFilter.setMwise(mwise);
		commonFilter.setSummary(summary);
		commonFilter.setCellch(cell);
		commonFilter.setSect(sect);
		commonFilter.setEqpmnt(eqpmnt);
		commonFilter.setJhStep(cmbJHStep);
		commonFilter.setDuration(txtDuration);
		commonFilter.setMonthly(chkMonth);
		commonFilter.setWeekly(chkWeek);
		commonFilter.setEquipmentFlag("N");
		commonFilter.setTrade(cmbTradeId);
		commonFilter.setRemoveBlank(chkRemoveBlank);
		
		CommonMessage.debugMsg("test...."+FilterCondSql.getComboSelectionId(commonFilter.getSources()));
		CommonMessage.debugMsg(" ~~ARdffffffffffffffffff~~ "+commonFilter.getTrade().getId());
		return commonFilter;
	}
	
	public static CommonFilter getQuality(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;

		tempVar = request.getParameter("cmbprocess");				
		ComboFilter cmbprocess = new ComboFilter();
		cmbprocess.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbcomplaintno");				
		ComboFilter cmbcomplaintno = new ComboFilter();
		cmbcomplaintno.setId(tempVar != null ? tempVar:"{}");
		
		tempVar  = request.getParameter("cbocomplainttype");				
		ComboFilter cbocomplainttype = new ComboFilter();
		cbocomplainttype.setId(tempVar != null ? tempVar:"{}");
		
		tempVar  = request.getParameter("cmbproduct");				
		ComboFilter cmbproduct = new ComboFilter();
		cmbproduct.setId(tempVar != null ? tempVar:"{}");
		
		tempVar  = request.getParameter("cmbinspectedby");				
		ComboFilter cmbinspectedby = new ComboFilter();
		cmbinspectedby.setId(tempVar != null ? tempVar:"{}");
		
		
		tempVar = request.getParameter("cmbcustID");				
		ComboFilter cmbcustID = new ComboFilter();
		cmbcustID.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbdefactparam");				
		ComboFilter cmbdefactparam = new ComboFilter();
		cmbdefactparam.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbrecordedby");				
		ComboFilter cmbrecordedby = new ComboFilter();
		cmbrecordedby.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbinspection");				
		ComboFilter cmbinspection = new ComboFilter();
		cmbinspection.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cborptType");				
		ComboFilter cborptType = new ComboFilter();
		cborptType.setId(tempVar != null ? tempVar:"SHIFT");
		
		tempVar = request.getParameter("cmbdefphen");				
		ComboFilter cmbDefectPhn = new ComboFilter();
		cmbDefectPhn.setId(tempVar != null ? tempVar:"{}");
		

		tempVar = request.getParameter("cmbPhenomenaId");				
		ComboFilter cmbPhenomena = new ComboFilter();
		cmbPhenomena.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboparetooptions");		

		tempVar = request.getParameter("cmbCauseId");				
		ComboFilter cmbCauseId = new ComboFilter();
		cmbCauseId.setId(tempVar != null ? tempVar:"{}");

		
		tempVar = request.getParameter("cboparetooptions");			
		ComboFilter cboparetooptions = new ComboFilter();
		cboparetooptions.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboProdDesc");			
		ComboFilter cboProdDesc = new ComboFilter();
		cboProdDesc.setId(tempVar != null ? tempVar:"{}");
		
		
		tempVar = request.getParameter("cboQtyStatus");			
		ComboFilter cboQtyStatus = new ComboFilter();
		cboQtyStatus.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbshift");			
		ComboFilter cmbshift = new ComboFilter();
		cmbshift.setId(tempVar != null ? tempVar:"{}");
		
		String ojt = request.getParameter("chkojtbox");	
		String sop = request.getParameter("chksopbox");				
		String kk = request.getParameter("chkkkbox");				
		String et = request.getParameter("chketbox");				
		String qm = request.getParameter("chk4mbox");				
		String impdone= request.getParameter("chkimpdonebox");				
		String rejectionch = request.getParameter("chkrejectionchkbox");	
		CommonMessage.debugMsg("rejectionch"+rejectionch);
		String dvp = request.getParameter("chkdvp");				
		String dvm = request.getParameter("chkdvm");				
		String instance = request.getParameter("chkinstance");				
		String quantity = request.getParameter("chkquantity");				
		String dpc = request.getParameter("chkdpc");
		String top = request.getParameter("txttop");
		String docType = request.getParameter("hdnDocType");
		String isMchwise=request.getParameter("chkmacwisechkbox");
		
		String process = FilterCondSql.getComboSelectionId(commonFilter.getProcess());
		String phenomena = FilterCondSql.getComboSelectionId(commonFilter.getPhenomena());
		CommonMessage.debugMsg("test....."+process);
		if(!UIUtils.isValidKeyId(process))
			commonFilter.setProcess(cmbprocess);
		if(!UIUtils.isValidKeyId(phenomena))
			commonFilter.setPhenomena(cmbPhenomena);
		if(UIUtils.isValidKeyId(docType))
			commonFilter.setDocType(docType);
		commonFilter.setComplaintno(cmbcomplaintno);
		commonFilter.setComplainttype(cbocomplainttype);
		commonFilter.setInspectedby(cmbinspectedby);
		commonFilter.setproduct(cmbproduct);
		commonFilter.setCustID(cmbcustID);
		commonFilter.setDefactparam(cmbdefactparam);		
		commonFilter.setRecordedby(cmbrecordedby);
		commonFilter.setDefactPhenamena(cmbDefectPhn);
		commonFilter.setInspection(cmbinspection);
		commonFilter.setCboparetooptions(cboparetooptions);
		commonFilter.setProductDesc(cboProdDesc);
		commonFilter.setShift(cmbshift);
		commonFilter.setQtyystatus(cboQtyStatus);
		
		commonFilter.setTxttop(top);
		commonFilter.setChkpmbox(sop);
		commonFilter.setChkkkbox(kk);
		commonFilter.setChketbox(et);
		commonFilter.setChk4mbox(qm);
		commonFilter.setChkjhbox(ojt);
		commonFilter.setChkimpdonebox(impdone);
		commonFilter.setRejection(rejectionch);
		commonFilter.setIsMchwise(isMchwise);
		commonFilter.setChkdvp(dvp);
		commonFilter.setChkdpc(dpc);
		commonFilter.setChkdvm(dvm);
		commonFilter.setChkinstance(instance);
		commonFilter.setChkquantity(quantity);
		commonFilter.setReportType(cborptType);
		commonFilter.setQtmCause(cmbCauseId);
		return commonFilter;
	}
	
	public static CommonFilter getSafty(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbtagno");				
		ComboFilter cmbtagno = new ComboFilter();
		cmbtagno.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbsafetysubtype");				
		ComboFilter cmbsafetysubtype = new ComboFilter();
		cmbsafetysubtype.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbincidentno");				
		ComboFilter cmbincidentno = new ComboFilter();
		cmbincidentno.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbincidenttype");				
		ComboFilter cmbincidenttype = new ComboFilter();
		cmbincidenttype.setId(tempVar != null ? tempVar:"{}");
		
		String incdntFromDate = request.getParameter("dteincdntFromDate");	
		if(!UIUtils.isValidKeyId(incdntFromDate))
			incdntFromDate = Constants.passNullDate;
		
		String incdntdToDate = request.getParameter("dteincdntdToDate");		
		if(!UIUtils.isValidKeyId(incdntdToDate))
			incdntdToDate = Constants.futureNullDate;
		
		tempVar = request.getParameter("cboimprovmnt");				
		ComboFilter cmbimprovmnt = new ComboFilter();
		cmbimprovmnt.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cbosafetytype");				
		ComboFilter cmbsafetytype = new ComboFilter();
		cmbsafetytype.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cbopriority");				
		ComboFilter cmbpriority = new ComboFilter();
		cmbpriority.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cborelatedto");				
		ComboFilter cmbrelatedto = new ComboFilter();
		cmbrelatedto.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboreptype");				
		ComboFilter cmbreptype = new ComboFilter();
		cmbreptype.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboworkarea");				
		ComboFilter cmbworkarea = new ComboFilter();
		cmbworkarea.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboStatus");				
		ComboFilter cmbStatus = new ComboFilter();
		cmbStatus.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbemployee");				
		ComboFilter cmbemployee = new ComboFilter();
		cmbemployee.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbbodyPart");	
		CommonMessage.debugMsg("TESTING"+tempVar);
		ComboFilter cmbbodyPart = new ComboFilter();
		cmbbodyPart.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbinjuryType");				
		ComboFilter cmbinjuryType = new ComboFilter();
		cmbinjuryType.setId(tempVar != null ? tempVar:"{}");
		
		
		String instance = request.getParameter("chkinstancechkbox");				
		String mdl = request.getParameter("chkmdlchkbox");				
		
		String ojt = request.getParameter("chkojtbox");	
		String pokayoke = request.getParameter("chkpybox");				
		String kk = request.getParameter("chkkkbox");	
		String Tot = request.getParameter("chkTot");
		String et = request.getParameter("chketbox");
		String min = request.getParameter("chkminbox");
		String maj = request.getParameter("chkmajbox");
		
		
		commonFilter.setAgno(cmbtagno);
		commonFilter.setSafetysubtype(cmbsafetysubtype);
		commonFilter.setIncidentno(cmbincidentno);
		commonFilter.setIncidenttype(cmbincidenttype);
		commonFilter.setIncdntFromDate(incdntFromDate);
		commonFilter.setIncdntdToDate(incdntdToDate);
		commonFilter.setImprovmnt(cmbimprovmnt);
		commonFilter.setSafetytype(cmbsafetytype);
		commonFilter.setPriority(cmbpriority);
		commonFilter.setEmployee(cmbemployee);
		commonFilter.setBodypart(cmbbodyPart);
		commonFilter.setInjuryType(cmbinjuryType);
		commonFilter.setRelatedto(cmbrelatedto);
		commonFilter.setReptype(cmbreptype);
		commonFilter.setWorkarea(cmbworkarea);
		commonFilter.setQtyystatus(cmbStatus);
		commonFilter.setInstance(instance);
		commonFilter.setMdl(mdl);
		commonFilter.setSftkzn(kk);
		commonFilter.setTotal(Tot);
		commonFilter.setSftojt(ojt);
		commonFilter.setSftopl(et);
		commonFilter.setSftpokayoke(pokayoke);
		commonFilter.setSftmin(min);
		commonFilter.setSftmaj(maj);
		return commonFilter;
	}
	public static CommonFilter getAudit(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbAuditorName");				
		ComboFilter cmbAuditorName = new ComboFilter();
		cmbAuditorName.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbAuditorLevel");				
		ComboFilter cmbAuditorLevel = new ComboFilter();
		cmbAuditorLevel.setId(tempVar != null ? tempVar:"{}");
		
		
		String dteAuditFromDate = request.getParameter("dteAuditFromDate");	
		if(!UIUtils.isValidKeyId(dteAuditFromDate))
			dteAuditFromDate = Constants.passNullDate;
		
		String dteAuditToDate = request.getParameter("dteAuditToDate");		
		if(!UIUtils.isValidKeyId(dteAuditToDate))
			dteAuditToDate = Constants.futureNullDate;
		
		tempVar = request.getParameter("cboType");				
		ComboFilter cboType = new ComboFilter();
		cboType.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cboStatus");				
		ComboFilter cboStatus = new ComboFilter();
		cboStatus.setId(tempVar != null ? tempVar:"{}");
		
		
		
		commonFilter.setCmbAuditorName(cmbAuditorName);
		commonFilter.setCmbAuditorLevel(cmbAuditorLevel);
		commonFilter.setAuditFromDate(dteAuditFromDate);
		commonFilter.setAuditToDate(dteAuditToDate);
		commonFilter.setAuditType(cboType);
		commonFilter.setAuditStatus(cboStatus);
		
		return commonFilter;
	}
	public static CommonFilter getSparesRelated(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbsparePartNo");		
		
		ComboFilter cmbsparePartNo = new ComboFilter();
		cmbsparePartNo.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbspareDescn");				
		ComboFilter cmbspareDescn = new ComboFilter();
		cmbspareDescn.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbcriticality");				
		ComboFilter cmbcriticality = new ComboFilter();
		cmbcriticality.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbclassifcn");				
		ComboFilter cmbclassifcn = new ComboFilter();
		cmbclassifcn.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbspqcategory");				
		ComboFilter cmbspqcategory = new ComboFilter();
		cmbspqcategory.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbspqsubcat");				
		ComboFilter cmbspqsubcat = new ComboFilter();
		cmbspqsubcat.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbuom");				
		ComboFilter cmbuom = new ComboFilter();
		cmbuom.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbmake");				
		ComboFilter cmbmake = new ComboFilter();
		cmbmake.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbmodel");				
		ComboFilter cmbmodel = new ComboFilter();
		cmbmodel.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmboptions");				
		ComboFilter cmboptions = new ComboFilter();
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.equalsIgnoreCase("Begins"))
				cmboptions.setId("B");
			else if(tempVar.equalsIgnoreCase("Ends"))
				cmboptions.setId("E");
			else if(tempVar.equalsIgnoreCase("Contains"))
				cmboptions.setId("C");
		}
		
		
		tempVar = request.getParameter("cmbsource");				
		ComboFilter cmbsource = new ComboFilter();
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.equalsIgnoreCase("L"))
				cmbsource.setId("L");
			else if(tempVar.equalsIgnoreCase("I"))
				cmbsource.setId("I");
		}
		
		tempVar = request.getParameter("cmbsources");				
		ComboFilter cmbsources = new ComboFilter();
		cmbsources.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbabcClass");				
		ComboFilter cmbabcClass = new ComboFilter();
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.equalsIgnoreCase("A"))
				cmbabcClass.setId("A");
			else if(tempVar.equalsIgnoreCase("B"))
				cmbabcClass.setId("B");
			else if(tempVar.equalsIgnoreCase("C"))
				cmbabcClass.setId("C");
		}
		
			
		tempVar = request.getParameter("cmbspqType");				
		ComboFilter cmbspqType = new ComboFilter();
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.equalsIgnoreCase("D"))
				cmbspqType.setId("D");
			else if(tempVar.equalsIgnoreCase("R"))
				cmbspqType.setId("R");
		}
		
		tempVar = request.getParameter("cmbmachineSpec");				
		ComboFilter cmbmachineSpec = new ComboFilter();
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.equalsIgnoreCase("Y"))
				cmbmachineSpec.setId("Y");
			else if(tempVar.equalsIgnoreCase("N"))
				cmbmachineSpec.setId("N");
		}
		
		
		tempVar = request.getParameter("cmbshelfLifeunt");				
		ComboFilter cmbshelfLifeunt = new ComboFilter();
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.equalsIgnoreCase("D"))
				cmbshelfLifeunt.setId("D");
			else if(tempVar.equalsIgnoreCase("Y"))
				cmbshelfLifeunt.setId("Y");
			else if(tempVar.equalsIgnoreCase("M"))
				cmbshelfLifeunt.setId("M");
			else if(tempVar.equalsIgnoreCase("N"))
				cmbshelfLifeunt.setId("X,{}");
		}
			
		tempVar = request.getParameter("cmbshelfLifeitem");				
		ComboFilter cmbshelfLifeitem = new ComboFilter();
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.equalsIgnoreCase("Y"))
				cmbshelfLifeitem.setId("Y");
			else if(tempVar.equalsIgnoreCase("N"))
				cmbshelfLifeitem.setId("N");
		}
		
		tempVar = request.getParameter("cboActivityType");				
		ComboFilter cboActivityType = new ComboFilter();
		cboActivityType.setId(tempVar != null ? tempVar:"{}");
		
		tempVar  = request.getParameter("cmbfreq");				
		ComboFilter cmbfreq = new ComboFilter();
		cmbfreq.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbpmjobtype");				
		ComboFilter cmbJobtype = new ComboFilter();
		cmbJobtype.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbSupplier");				
		ComboFilter cmbSupplier = new ComboFilter();
		cmbSupplier.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbprodcngroup");		
		ComboFilter cmbProdcngroup = new ComboFilter();
		cmbProdcngroup.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbJhStep");				
		ComboFilter cmbJHStep = new ComboFilter();
		cmbJHStep.setId(tempVar);
		
		//cmbshelfLifeitem.setId(tempVar != null ? tempVar:"{}");
		
		String eqpwise = request.getParameter("chkeqpwisechkbox");				
		String sprwise = request.getParameter("chksprwisechkbox");		
		String bval = request.getParameter("chbval");	
		String reportType = request.getParameter("grpBy");
		String sectwise = request.getParameter("chkSectionwise");				
		String cellwise = request.getParameter("chkCellwise");	
		String mchwise = request.getParameter("chkMchwise");
		
		commonFilter.setSparePartNo(cmbsparePartNo);
		commonFilter.setSpareDescn(cmbspareDescn);
		commonFilter.setOptions(cmboptions);
		commonFilter.setCriticality(cmbcriticality);
		commonFilter.setClassifcn(cmbclassifcn);
		commonFilter.setSpqcategory(cmbspqcategory);
		commonFilter.setSubCategory(cmbspqsubcat);
		commonFilter.setUom(cmbuom);
		commonFilter.setMake(cmbmake);
		commonFilter.setModel(cmbmodel);
		commonFilter.setSource(cmbsource);
		commonFilter.setAbcClass(cmbabcClass);
		commonFilter.setSpqType(cmbspqType);
		commonFilter.setMachineSpec(cmbmachineSpec);
		commonFilter.setShelfLifeunt(cmbshelfLifeunt);
		commonFilter.setShelfLifeItem(cmbshelfLifeitem);
		commonFilter.setEqpwise(eqpwise);
		commonFilter.setSprwise(sprwise);
		commonFilter.setBval(bval);
		commonFilter.setFrequency(cmbfreq);
		commonFilter.setJobtype(cmbJobtype);
		commonFilter.setSupplier(cmbSupplier);
		commonFilter.setCmbprodcngroup(cmbProdcngroup);
		commonFilter.setSources(cmbsources);
		commonFilter.setJhStep(cmbJHStep);
		commonFilter.setRepportType(reportType);
		commonFilter.setActType(cboActivityType);
		commonFilter.setSectWise(sectwise);
		commonFilter.setCellWise(cellwise);
		commonFilter.setMachineWise(mchwise);
		
		return commonFilter;
	}
	
	public static CommonFilter getTraning(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		tempVar = request.getParameter("cmbdesignation");				
		ComboFilter cmbdesignation = new ComboFilter();
		cmbdesignation.setId(tempVar != null ? tempVar:"{}");	
		
		tempVar = request.getParameter("cmbprogm");				
		ComboFilter cmbprogm = new ComboFilter();
		cmbprogm.setId(tempVar != null ? tempVar:"{}");	
		
		tempVar = request.getParameter("cmbpgmbenefit");				
		ComboFilter cmbpgmbenefit = new ComboFilter();
		cmbpgmbenefit.setId(tempVar != null ? tempVar:"{}");
		
		String befFromDate =request.getParameter("befFromDt");
		if( ! UIUtils.isValidKeyId(befFromDate)){
			befFromDate = Constants.passNullDate;
		}
		String befToDate = request.getParameter("befToDt");
		
		if(!UIUtils.isValidKeyId(befToDate))
			befToDate = Constants.futureNullDate;
		
		String AftFromDate =request.getParameter("AftFromDt");
		if( ! UIUtils.isValidKeyId(AftFromDate)){
			AftFromDate = Constants.passNullDate;
		}
		String AftToDate = request.getParameter("AftToDt");
		
		if(!UIUtils.isValidKeyId(AftToDate))
			AftToDate = Constants.futureNullDate;
		
		tempVar = request.getParameter("cmbbatch");				
		ComboFilter cmbbatch = new ComboFilter();
		cmbbatch.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbspoke");				
		ComboFilter cmbspoke = new ComboFilter();
		cmbspoke.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbtopic");				
		ComboFilter cmbtopic = new ComboFilter();
		cmbtopic.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbemployee");				
		ComboFilter cmbemployee = new ComboFilter();
		cmbemployee.setId(tempVar != null ? tempVar:"{}");
		
		
		
		tempVar = request.getParameter("cmbpgmno");				
		ComboFilter cmbpgmno = new ComboFilter();
		cmbpgmno.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbtrainingtype");				
		ComboFilter cmbtrainingtype = new ComboFilter();
		cmbtrainingtype.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbknowavg");				
		ComboFilter cmbknowavg = new ComboFilter();
		cmbknowavg.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbskillavg");				
		ComboFilter cmbskillavg = new ComboFilter();
		cmbskillavg.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbcompavg");				
		ComboFilter cmbcompavg = new ComboFilter();
		cmbcompavg.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbtrainingcategory");				
		ComboFilter cmbtrainingcategory = new ComboFilter();
		cmbtrainingcategory.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbskillType");				
		ComboFilter cmbskillType = new ComboFilter();
		cmbskillType.setId(tempVar != null ? tempVar:"{}");
		
		String pgmWise = request.getParameter("chkprogramwise");
		String empWise =request.getParameter("chkemployeewise");
		String trnAreaId =request.getParameter("trnAreaId");
		String knowValue = request.getParameter("knowval");
		String skillValue = request.getParameter("skillval");
		String attValue = request.getParameter("attval");
		CommonMessage.debugMsg("attValue in Filter Values:"+attValue);
		commonFilter.setDesignation(cmbdesignation);
		commonFilter.setProgm(cmbprogm);
		commonFilter.setPgmbenefit(cmbpgmbenefit);
		commonFilter.setBatch(cmbbatch);
		commonFilter.setSpoke(cmbspoke);
		commonFilter.setTopics(cmbtopic);
		commonFilter.setEmployee(cmbemployee);
		commonFilter.setPgmno(cmbpgmno);
		commonFilter.setTrainingtype(cmbtrainingtype);
		commonFilter.setSkillType(cmbskillType);
		commonFilter.setKnowavg(cmbknowavg);
		commonFilter.setSkillavg(cmbskillavg);
		commonFilter.setCompavg(cmbcompavg);
		commonFilter.setTrainingcategory(cmbtrainingcategory);
		
		commonFilter.setPgmWise(pgmWise);
		commonFilter.setEmpWise(empWise);
		commonFilter.setTrarId(trnAreaId);
		commonFilter.setKnowValue(knowValue);
		commonFilter.setSkilValue(skillValue);
		commonFilter.setAttValue(attValue);
		commonFilter.setBefFromDt(befFromDate);
		commonFilter.setBefToDt(befToDate);
		commonFilter.setAftFromDt(AftFromDate);
		commonFilter.setAftToDt(AftToDate);
		
		CommonMessage.debugMsg("commonFilter.setEmpWise"+trnAreaId);

		return commonFilter;
	}
	
	public static CommonFilter getWorkOrder(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		
		String Prblm = request.getParameter("txtPrblm");				
		String Prdaffctd = request.getParameter("txtPrdaffctd");				
		
		tempVar = request.getParameter("cmbEqpCond");				
		ComboFilter cmbEqpCond = new ComboFilter();
		cmbEqpCond.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbType");	
		
		if(UIUtils.isValidKeyId(tempVar))
		{
			if(tempVar.substring(tempVar.length()-1).equals(","))
				tempVar = tempVar.substring(0,tempVar.length()-1);
			
			//tempVar = "'"+tempVar.replace(",", "','")+"'";
			
			/*if(tempVar.indexOf("A") >0)
			{
					
				CommonMessage.debugMsg("LOSS : "+Loss);
				if(UIUtils.isValidKeyId(Loss))
				{
					if(Loss.equals("U"))
						Loss = "UNPLANNED MAINTENANCE";
					else if(Loss.equals("J"))
						Loss = "JH TAG REMOVAL";
					else if(Loss.equals("M"))
						Loss = "M AND A";
				}
				else
					Loss="";
				commonFilter.setLoss(Loss);
			}*/
			
		}
	
		CommonMessage.debugMsg("TYPE : "+tempVar);
		ComboFilter cmbType = new ComboFilter();		
		cmbType.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbMSRKeyId");				
		ComboFilter cmbMSRKeyId = new ComboFilter();
		cmbMSRKeyId.setId(tempVar != null ? tempVar:"{}");
		
		String Loss = request.getParameter("cmbLoss");	
		if(UIUtils.isValidKeyId(Loss))
		{
			if(Loss.substring(Loss.length()-1).equals(","))
				Loss = Loss.substring(0,Loss.length()-1);
			
			Loss = "'"+Loss.replace(",", "','")+"'";
			
			
		}
		CommonMessage.debugMsg("Loss : "+Loss);
		ComboFilter cmbLoss = new ComboFilter();		
		cmbLoss.setId(Loss != null ? Loss:"{}");
		
		
		
		
		tempVar = request.getParameter("CmbPriority");				
		ComboFilter CmbPriority = new ComboFilter();
		CmbPriority.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("CmbStatus");				
		ComboFilter CmbStatus = new ComboFilter();
		CmbStatus.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbSource");				
		ComboFilter cmbSource = new ComboFilter();
		cmbSource.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbTask");				
		ComboFilter cmbTask = new ComboFilter();
		cmbTask.setId(tempVar != null ? tempVar:"{}");
		
		
	
		String dteOccuredfrm = request.getParameter("dteOccuredfrm");	
		if(!UIUtils.isValidKeyId(dteOccuredfrm))
			dteOccuredfrm = Constants.passNullDate;
		
		String timOccuredfrm = request.getParameter("timOccuredfrm");				
		String dteOccuredto= request.getParameter("dteOccuredto");	
		if(!UIUtils.isValidKeyId(dteOccuredto))
			dteOccuredto = Constants.futureNullDate;
		
		String timOccuredto = request.getParameter("timOccuredto");				
		
		String dteReportedfrm = request.getParameter("dteReportedfrm");	
		if(!UIUtils.isValidKeyId(dteReportedfrm))
			dteReportedfrm = Constants.passNullDate;
		
		String  timReportedfrm= request.getParameter("timReportedfrm");				
		String dteReportedto = request.getParameter("dteReportedto");	
		if(!UIUtils.isValidKeyId(dteReportedto))
			dteReportedto = Constants.futureNullDate;
		
		String timReportedto = request.getParameter("timReportedto");				
		
		String  dteReqstfrm= request.getParameter("dteReqstfrm");
		if(!UIUtils.isValidKeyId(dteReqstfrm))
			dteReqstfrm = Constants.passNullDate;
		
		String timReqstfrm = request.getParameter("timReqstfrm");				
		String dteReqstto = request.getParameter("dteReqstto");		
		if(!UIUtils.isValidKeyId(dteReqstto))
			dteReqstto = Constants.futureNullDate;
		
		String  timReqstto= request.getParameter("timReqstto");				
		
		String dteProposedfrm = request.getParameter("dteProposedfrm");	
		if(!UIUtils.isValidKeyId(dteProposedfrm))
			dteProposedfrm = Constants.passNullDate;
		
		String  timProposedfrm= request.getParameter("timProposedfrm");				
		String dteProposedto = request.getParameter("dteProposedto");	
		if(!UIUtils.isValidKeyId(dteProposedto))
			dteProposedto = Constants.futureNullDate;
		
		String timProposedto = request.getParameter("timProposedto");				
		
		String dteReschedulefrm = request.getParameter("dteReschedulefrm");	
		if(!UIUtils.isValidKeyId(dteReschedulefrm))
			dteReschedulefrm = Constants.passNullDate;
		
		String timReschedulefrm = request.getParameter("timReschedulefrm");				
		String dteRescheduleto = request.getParameter("dteRescheduleto");	
		if(!UIUtils.isValidKeyId(dteRescheduleto))
			dteRescheduleto = Constants.futureNullDate;
		
		String timRescheduleto = request.getParameter("timRescheduleto");				
		
		String dteAllotedfrm = request.getParameter("dteAllotedfrm");
		if(!UIUtils.isValidKeyId(dteAllotedfrm))
			dteAllotedfrm = Constants.passNullDate;
		
		String  timAllotedfrm= request.getParameter("timAllotedfrm");				
		String dteAllotedto = request.getParameter("dteAllotedto");	
		if(!UIUtils.isValidKeyId(dteAllotedto))
			dteAllotedto = Constants.futureNullDate;
		
		String  timAllotedto= request.getParameter("timAllotedto");				
		
		String dteWorkStartfrm = request.getParameter("dteWorkStartfrm");
		if(!UIUtils.isValidKeyId(dteWorkStartfrm))
			dteWorkStartfrm = Constants.passNullDate;
		
		String timWorkStartfrm = request.getParameter("timWorkStartfrm");				
		String  dteWorkStartto= request.getParameter("dteWorkStartto");	
		if(!UIUtils.isValidKeyId(dteWorkStartto))
			dteWorkStartto = Constants.futureNullDate;
		
		String timWorkStartto = request.getParameter("timWorkStartto");				
		
		String dteWorkEndfrm = request.getParameter("dteWorkEndfrm");
		if(!UIUtils.isValidKeyId(dteWorkEndfrm))
			dteWorkEndfrm = Constants.passNullDate;
		
		String timWorkEndfrm = request.getParameter("timWorkEndfrm");				
		String dteWorkEndto = request.getParameter("dteWorkEndto");	
		if(!UIUtils.isValidKeyId(dteWorkEndto))
			dteWorkEndto = Constants.futureNullDate;
		
		String timWorkEndto = request.getParameter("timWorkEndto");				
		
		String dteWorkApprovalfrm = request.getParameter("dteWorkApprovalfrm");	
		if(!UIUtils.isValidKeyId(dteWorkApprovalfrm))
			dteWorkApprovalfrm = Constants.passNullDate;
		
		String  timWorkApprovalfrm= request.getParameter("timWorkApprovalfrm");				
		String dteWorkApprovalto = request.getParameter("dteWorkApprovalto");	
		if(!UIUtils.isValidKeyId(dteWorkApprovalto))
			dteWorkApprovalto = Constants.futureNullDate;
		
		String  timWorkApprovalto= request.getParameter("timWorkApprovalto");				
		
		String dteEqpReleasedfrm = request.getParameter("dteEqpReleasedfrm");
		if(!UIUtils.isValidKeyId(dteEqpReleasedfrm))
			dteEqpReleasedfrm = Constants.passNullDate;
		
		String  timEqpReleasedfrm= request.getParameter("timEqpReleasedfrm");				
		String dteEqpReleasedto = request.getParameter("dteEqpReleasedto");	
		if(!UIUtils.isValidKeyId(timEqpReleasedfrm))
			timEqpReleasedfrm = Constants.futureNullDate;
		
		String timEqpReleasedto = request.getParameter("timEqpReleasedto");				
		
		String dteProductionfrm = request.getParameter("dteProductionfrm");	
		if(!UIUtils.isValidKeyId(dteProductionfrm))
			dteProductionfrm = Constants.passNullDate;
		
		String timProductionfrm = request.getParameter("timProductionfrm");				
		String dteProductionto = request.getParameter("dteProductionto");	
		if(!UIUtils.isValidKeyId(dteProductionto))
			dteProductionto = Constants.futureNullDate;
		
		String timProductionto = request.getParameter("timProductionto");				
		
		String eqpwise = request.getParameter("eqpwisechkbox");				
		String sprwise = request.getParameter("sprwisechkbox");				
		
		String chkOccuredDate = request.getParameter("chkOccuredDate");
		if(!UIUtils.isValidKeyId(chkOccuredDate))
			chkOccuredDate = "N";
		
		String chkReportedDate = request.getParameter("chkReportedDate");
		if(!UIUtils.isValidKeyId(chkReportedDate))
			chkReportedDate = "N";
		
		String chkAllottedDate = request.getParameter("chkAllottedDate");
		if(!UIUtils.isValidKeyId(chkAllottedDate))
			chkAllottedDate = "N";
		
		String chkWostartDate = request.getParameter("chkWostartDate");
		if(!UIUtils.isValidKeyId(chkWostartDate))
			chkWostartDate = "N";
		
		String chkWoendDate = request.getParameter("chkWoendDate");
		if(!UIUtils.isValidKeyId(chkWoendDate))
			chkWoendDate = "N";
		
		String chkProdDate = request.getParameter("chkProdDate");
		if(!UIUtils.isValidKeyId(chkProdDate))
			chkProdDate = "N";
		
		tempVar = request.getParameter("cmbSortOrd");				
		ComboFilter cmbSortOrd = new ComboFilter();
		cmbSortOrd.setId(tempVar != null ? tempVar:"{}");
		commonFilter.setChkOccured(chkOccuredDate);
		commonFilter.setChkReported(chkReportedDate);
		commonFilter.setChkAllotted(chkAllottedDate);
		commonFilter.setChkWorkStart(chkWostartDate);
		commonFilter.setChkWorkEnd(chkWoendDate);
		commonFilter.setChkProdDate(chkProdDate);
		commonFilter.setPrblm(Prblm);
		commonFilter.setCmbMsr(cmbMSRKeyId);
		commonFilter.setPrdaffctd(Prdaffctd);
		commonFilter.setEqpCond(cmbEqpCond);
		commonFilter.setTypes(cmbType);
		commonFilter.setCmbLoss(cmbLoss);
		commonFilter.setPriority(CmbPriority);
		commonFilter.setStatuss(CmbStatus);
		commonFilter.setSource(cmbSource);
		commonFilter.setTask(cmbTask);
		commonFilter.setDteOccuredfrm(dteOccuredfrm);
		commonFilter.setDteOccuredto(dteOccuredto);
		commonFilter.setTimOccuredfrm(timOccuredfrm);
		commonFilter.setTimOccuredto(timOccuredto);
		commonFilter.setReportedFrom(dteReportedfrm);
		commonFilter.setReportedTo(dteReportedto);
		commonFilter.setTimReportedfrm(timReportedfrm);
		commonFilter.setTimReportedto(timReportedto);
		commonFilter.setDteReqstfrm(dteReqstfrm);
		commonFilter.setDteReqstto(dteReqstto);
		commonFilter.setTimReqstfrm(timReqstfrm);
		commonFilter.setTimReqstto(timReqstto);
		commonFilter.setDteProposedfrm(dteProposedfrm);
		commonFilter.setDteProposedto(dteProposedto);
		commonFilter.setTimProposedfrm(timProposedfrm);
		commonFilter.setTimProposedto(timProposedto);
		commonFilter.setDteReschedulefrm(dteReschedulefrm);
		commonFilter.setDteRescheduleto(dteRescheduleto);
		commonFilter.setTimReschedulefrm(timReschedulefrm);
		commonFilter.setTimRescheduleto(timRescheduleto);
		commonFilter.setDteAllotedfrm(dteAllotedfrm);
		commonFilter.setDteAllotedto(dteAllotedto);
		commonFilter.setTimAllotedfrm(timAllotedfrm);
		commonFilter.setTimAllotedto(timAllotedto);
		commonFilter.setDteWorkStartfrm(dteWorkStartfrm);
		commonFilter.setDteWorkStartto(dteWorkStartto);
		commonFilter.setTimWorkStartfrm(timWorkStartfrm);
		commonFilter.setTimWorkStartto(timWorkStartto);
		commonFilter.setDteWorkEndfrm(dteWorkEndfrm);
		commonFilter.setDteWorkEndto(dteWorkEndto);
		commonFilter.setTimWorkEndfrm(timWorkEndfrm);
		commonFilter.setTimWorkEndto(timWorkEndto);
		commonFilter.setDteWorkApprovalfrm(dteWorkApprovalfrm);
		commonFilter.setDteWorkApprovalto(dteWorkApprovalto);
		commonFilter.setTimWorkApprovalfrm(timWorkApprovalfrm);
		commonFilter.setTimWorkApprovalto(timWorkApprovalto);
		commonFilter.setDteEqpReleasedfrm(dteEqpReleasedfrm);
		commonFilter.setDteEqpReleasedto(dteEqpReleasedto);
		commonFilter.setTimEqpReleasedfrm(timEqpReleasedfrm);
		commonFilter.setTimEqpReleasedto(timEqpReleasedto);
		commonFilter.setDteProductionfrm(dteProductionfrm);
		commonFilter.setDteProductionto(dteProductionto);
		commonFilter.setTimProductionfrm(timProductionfrm);
		commonFilter.setTimProductionto(timProductionto);
		commonFilter.setEqpwise(eqpwise);
		commonFilter.setSprwise(sprwise);
		commonFilter.setSortOrd(cmbSortOrd);
		
		return commonFilter;
	}
	public static CommonFilter getMasterPlan(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar=null;
		tempVar = request.getParameter("cmbActivity");				
		ComboFilter cmbActivity = new ComboFilter();
		cmbActivity.setId(tempVar != null ? tempVar:"{}");
		CommonMessage.debugMsg("cmbActivity " +tempVar);
		
		tempVar = request.getParameter("cmbCategory");				
		ComboFilter cmbCategory = new ComboFilter();
		cmbCategory.setId(tempVar != null ? tempVar:"{}");
		CommonMessage.debugMsg("cmbCategory " +tempVar);
		
		
		tempVar = request.getParameter("cmbSubCategory");				
		ComboFilter cmbSubCategory = new ComboFilter();
		cmbSubCategory.setId(tempVar != null ? tempVar:"{}");
		CommonMessage.debugMsg("cmbSubCategory " +tempVar);
		
		
		tempVar = request.getParameter("cmbResponsibility");				
		ComboFilter cmbResponsibility = new ComboFilter();
		cmbResponsibility.setId(tempVar != null ? tempVar:"{}");
		CommonMessage.debugMsg("cmbResponsibility " +tempVar);
		
		
		tempVar = request.getParameter("cmbAssignedto");				
		ComboFilter cmbAssignedto = new ComboFilter();
		cmbAssignedto.setId(tempVar != null ? tempVar:"{}");
		CommonMessage.debugMsg("cmbAssignedto " +tempVar);
		
		
		tempVar = request.getParameter("cmbStatus");				
		ComboFilter cmbStatus = new ComboFilter();
		cmbStatus.setId(tempVar != null ? tempVar:"{}");
		CommonMessage.debugMsg("cmbStatus " +tempVar);
		
		String dtestart = request.getParameter("dtestart");	
		if(!UIUtils.isValidKeyId(dtestart))
			dtestart = Constants.passNullDate;
		
		String dteend = request.getParameter("dteend");	
		if(!UIUtils.isValidKeyId(dteend))
			dteend = Constants.futureNullDate;
		
		String dteActualStart = request.getParameter("dteActualStart");	
		if(!UIUtils.isValidKeyId(dteActualStart))
			dteActualStart = Constants.passNullDate;
		
		String dteEnd = request.getParameter("dteEnd");	
		if(!UIUtils.isValidKeyId(dteEnd))
			dteEnd = Constants.futureNullDate;
		
		commonFilter.setCmbActivity(cmbActivity);
		commonFilter.setCmbCategory(cmbCategory);
		commonFilter.setCmbSubCategory(cmbSubCategory);
		commonFilter.setCmbAssignedto(cmbAssignedto);
		commonFilter.setCmbResponsibility(cmbResponsibility);
		commonFilter.setCmbStatus(cmbStatus);
		commonFilter.setDtestart(dtestart);
		commonFilter.setDteend(dteEnd);
		commonFilter.setDteActualStart(dteActualStart);
		commonFilter.setDteEnd(dteEnd);
		
		return commonFilter;
	
	}

	/*spublic static List<CommonFilter> getBreakUpRelated(HttpServletRequest request, List<CommonFilter> breakUpArray) {
		String breakup = request.getParameter("BREAKUP");

		CommonMessage.debugMsg("BREAKUP"+breakup);
		CommonFilter breakUpList =  new CommonFilter();
		JSONArray convertbreak = null;
		if(UIUtils.isValidKeyId(breakup)){
			if( breakup != null && ! breakup.isEmpty())
    		{
				convertbreak = JSONArray.fromString(breakup);
				breakUpArray = (List<CommonFilter>)UIUtils.convertJSONArrToList(breakUpList, convertbreak);
    		}
		}
		return breakUpArray;
	}*/
	
	public static String getDrillHeader(String data) {
		String [] dataArray=data.split("#");
		String [] eleTypeArray=dataArray[1].split("=");
		String elementType=eleTypeArray[1];
		String header="Company";
		if("CMP".equals(elementType))
			header="Company";
		else if("LCN".equals(elementType))
			header="Mill";
		else if("SBU".equals(elementType))
			header="SBU";
		else if("PBU".equals(elementType))
			header="PBU";
		else if("L".equals(elementType))
			header="DMT";
		else if("C".equals(elementType))
			header="JH";
		else if("M".equals(elementType))
			header="Machine";
		return header;
	}
	
	public static CommonFilter getToolsRelated(HttpServletRequest request,CommonFilter commonFilter)
	{
		String tempVar = null; 
		
		tempVar = request.getParameter("cmbToolid");	
		CommonFunctions.debugMsg(" tempVar "+tempVar);
		ComboFilter cmbToolid = new ComboFilter();
		cmbToolid.setId(tempVar != null ? tempVar:"{}");
		CommonFunctions.debugMsg(" tempVar "+cmbToolid.toString() +"  "+cmbToolid.getId());
		
		tempVar = request.getParameter("cmbToolCategory");	
		ComboFilter cmbToolCategory = new ComboFilter();
		cmbToolCategory.setId(tempVar != null ? tempVar:"{}");
		
		tempVar = request.getParameter("cmbChangeType");				
		ComboFilter cmbChangeType = new ComboFilter();
		cmbChangeType.setId(tempVar != null ? tempVar:"{}");		
		
		String extendedLife = request.getParameter("chkLifeExtended");	
		String earlyLife = request.getParameter("chkLifeEarly");
		String trialTool = request.getParameter("chkTrialTool");
		// mmadhan to checkQR
//		commonFilter.setToolid(cmbToolid);
//		commonFilter.setChangeType(cmbChangeType);
//		commonFilter.setToolCategory(cmbToolCategory);
//		commonFilter.setLifeEarly(earlyLife);
//		commonFilter.setLifeExtended(extendedLife);
//		commonFilter.setTrialTool(trialTool);
		
		CommonFunctions.debugMsg("In side the get Tools related"+ earlyLife+ " extendedLife " +extendedLife+" trialTool "+trialTool );
		
		return commonFilter;
		
	}
}