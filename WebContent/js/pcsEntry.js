jQuery(document).ready(function(){
	//alert(1);
});
var  cellId, sectId,factId,mchId,flId,shiftId,gbldate,gblmode,gblUrl ;
function fnValidateDate(calOrDat, date,shift,mode,allowDays,filterString,url) {
	//var date = date;
	var currentDate = getServerDateTime();
	var selDate = convertStringToDate(date);
	var nTotalDiff = getTimeDifference (currentDate,selDate);
	var dateDiff = nTotalDiff.convtDays;
	
	if (dateDiff>0) {
		alert("Entry Date should be less than or equal to Current Date ");		
		return ;
	}
	//var allowDays = jQuery('#hdnAllowDays').val();
	//alert(allowDays);
	//var mode = jQuery('#hdnMode').val();
	if (mode=="create" && (-dateDiff) > allowDays ) {
		alert("Entry Date should be allow only " + allowDays + " days ");		
		return ;			 
	}		
	var dataStr ="";
	  cellId =  getFilterValue(filterString,"cellId");
	  sectId =  getFilterValue(filterString,"sectId");
	  factId =  getFilterValue(filterString,"factId");
	
	
	dataStr+= "q=2&cellId="+cellId;
	dataStr+= "&date="+date;
	dataStr+= "&sectId="+sectId+"&factId="+factId;
	shiftId = shift ;
	gbldate = date;
	gblmode = mode;
	gblUrl  = url;
	//send it same format don't change
	var condParam = "";
	condParam+= "CHKCELL=Y;SECTIONID="+sectId+";CELLID="+cellId+";DATE="+date+";SHIFT="+shiftId+";FACTID="+factId+";calOrDat="+calOrDat;
	condParam+= ";CHKMACHINE=N;MACHINEID=;PCSOPTION=;";

	if (calOrDat=="C") 
		processAjaxCalls('getIsPcsEnabled.pcs','?q=2&condParam='+condParam,"pcsEnabledRecallSuccess");
	else
		processAjaxCalls('getIsPcsEnabled.pcs','?q=2&condParam='+condParam,"pcsEnableforShift");
}
function pcsEnabledRecallSuccess(result)
{		
	
	if(result.enableShift == 'N')
	{
		alert("Cannot enter for future shift");
	}
	else{
	if(result.isPcsEnabled == 'Y') {
		var rowId = jQuery("#monthGrid").jqGrid('getGridParam', 'selrow');
		var colid = "1";
		//alert(colid);
		if (colid == 0 || colid == 4 ) return ; 
		//var shiftCode = jQuery("#monthGrid").jqGrid('getCell',1,colid);			
		//var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
		//processAjaxCalls('getShiftKeyid.pcs','?q=2&factId='+factId+'&shiftCode='+shiftCode,"getShiftSuccess");
		
		//var shiftId = jQuery("#monthGrid").jqGrid('getCell',1,colid);			
		if (shiftId=="Total")
			return;
		getShiftSuccess(shiftId);		
	}
	else
		alert("PCS is Disabled for this Cell");
	}
}

function getShiftSuccess(shift) {
//function getShiftSuccess(result) {

	//var rowId = jQuery("#monthGrid").jqGrid('getGridParam', 'selrow');
	var date =gbldate ;// jQuery("#monthGrid").jqGrid('getCell',rowId,0);				 
	 date="01-Dec-2013";

	var dataStr = "";
	//alert(shift);
	//var date = jQuery('#dteEntryDate').datebox('getValue');
	//var shift = jQuery('#cmbShiftid').combobox("getValue");
	 
	dataStr+= "?q=2";
	dataStr+= "&date="+date;
	dataStr+= "&shift=SFT001&";
	//alert(shift);
	if (shift =="" || shift == " ") { 
		alert("Shift Not assign for the Factory"); 
		return;
	}
	
	/*var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
	var sectId = jQuery("#frmPcsCalendar input[id='section']").val();		
	var cellId = jQuery('#cmbCellid').combobox("getValue");		
	var mchId= jQuery('#cmbMachineid').combobox("getValue");*/
	

	var mode = gblmode;
	var formLock = "";		
	dataStr+= "&mchId=MCH0002057"+"&cellId="+cellId+"&sectId="+sectId+"&factId="+factId+"&mode="+mode;
	//dataStr="q=2&date=12-Nov-2013&shift=SFT001&mchId=MCH0002061&cellId=CEL0000066&sectId=LIN0000026&factId=FCT0000012&mode=create";
	// alert(dataStr);
	var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"mchId":mchId,"date":date,"shift":shift,"mode":mode,"formLock":formLock};
	var url = gblUrl;//jQuery("#hiddenUrl").val();

		//alert(url);
		LoadForm("pcsEntryPage","preloadDIVid1",'pcsViewTest_input.pcs?'+dataStr+"&formAuto=true","dispErr","","controlPANEL_errorCallBack");
	/*if(url.indexOf('Test') >=0)
		navigateToNextForm('pcsViewTest_input.pcs?'+dataStr,'PCS Entry',null,persistentData);			
	else
		navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS Entry',null,persistentData);*/
	/*if(url.indexOf('Test') >=0)
		LoadPopUp("divPcsView",'pcsViewTest_input.pcs?'+dataStr, true,"96%","91%","2%","10px","Pcs_View_Callback","PCS View",false,false);
	else
		LoadPopUp("divPcsView",'pcsView_input.pcs?'+dataStr, true,"97%","90%","5%","13px", "Pcs_View_Callback","PCS View",false,false);*/
}