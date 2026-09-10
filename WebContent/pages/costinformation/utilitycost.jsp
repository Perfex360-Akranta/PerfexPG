<script type="text/javascript">
jQuery(document).ready(function(){

	
	jQuery('.static-popup-clone').remove();
	STATIC_POPUP_IDS = {};
	initialiseForm('frmUtilityCostEst');
	initialiseForm('frmUtilityCostAct');
	
	fillComboBox("frmUtilityCostEst","cmbutcpUtilitymstid","combo_utilities.crt");
/*	fillComboBox("frmUtilityCostEst","cmbutcpRequestedby","employee.commonFilter" );
	fillComboBox("frmUtilityCostAct","cmbutcaUtilitymstid","combo_utilities.crt");
	fillComboBox("frmUtilityCostAct","cmbutcaRequestedby","employee.commonFilter" );
*/
	numericTextBox('txtutcpQuantity');
	numericTextBox('txtutcpMinutes');
	numericTextBox('txtutcaQuantity');
	numericTextBox('txtutcaMinutes');
		
	jQuery("#txtutcpCost").attr('readonly','readonly');
	jQuery("#txtutcpTotalvalue").attr('readonly','readonly');
	jQuery("#txtutcaCost").attr('readonly','readonly');
	jQuery("#txtutcaTotalvalue").attr('readonly','readonly');
		
	formatDateBox('dteutcaDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteutcaDate');
	var woid = jQuery('#hdnwoId').val();
	//q=2&formName=empCost&woId="+ woid		
	processGridnew('utilityCostEstimation_view.crt',"?q=2&formName=utilityCost&woId="+ woid,"utilityCostEstGrid","utilityCostEstPager","","utilityCostEstGrid_dblClick","","costGridLoad","");						
	processGridnew('utilityCostActual_view.crt',"?q=2&formName=utilityCost&woId="+ woid,"utilityCostActGrid","utilityCostActPager","","utilityCostActGrid_dblClick","","costGridLoad","");

	jQuery("#utilityCostEstDiv").hide();
	jQuery("#utilityCostActDiv").hide();
	
	fnSetPageTotal();
	
//utility Cost Estimate


// --- Utility Cost Estimate: auto-calc Total Value on Qty/Minutes blur ---
function fnCalcUtilEstValue() {
    var Qty = parseFloat(jQuery('#txtutcpQuantity').val()) || 0;
    var Cost = parseFloat(jQuery('#txtutcpCost').val()) || 0;
    var Mins = parseFloat(jQuery('#txtutcpMinutes').val()) || 0;
    var TotValue = (Qty * Cost * (Mins / 60)).toFixed(2);
    jQuery('#txtutcpTotalvalue').val(TotValue);
}
jQuery("#txtutcpQuantity").focusout(function () {
    fnCalcUtilEstValue();
});
jQuery("#txtutcpMinutes").focusout(function () {
    fnCalcUtilEstValue();
});

// --- Utility Cost Actual: auto-calc Total Value on Qty/Minutes blur ---
function fnCalcUtilActValue() {
    var Qty = parseFloat(jQuery('#txtutcaQuantity').val()) || 0;
    var Cost = parseFloat(jQuery('#txtutcaCost').val()) || 0;
    var Mins = parseFloat(jQuery('#txtutcaMinutes').val()) || 0;
    var TotValue = (Qty * Cost * (Mins / 60)).toFixed(2);
    jQuery('#txtutcaTotalvalue').val(TotValue);
}
jQuery("#txtutcaQuantity").focusout(function () {
    fnCalcUtilActValue();
});
jQuery("#txtutcaMinutes").focusout(function () {
    fnCalcUtilActValue();
});

	jQuery("#btnUtilEstNew").click(function(){
		fnUtilEstClear();			
		fnOpenUtilNewEst();			
	});
	
	jQuery('#btnUtilEstInsert').click(function(){
		 //alert("Insert clicked");	

		var TotValue = jQuery('#txtutcpQuantity').val() * jQuery('#txtutcpCost').val() * (jQuery('#txtutcpMinutes').val() / 60);
		TotValue = TotValue.toFixed(2);			 
		jQuery('#txtutcpTotalvalue').val(TotValue);

		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var url = "spareCostManPower_save.crt?q=2&Type=Estimate&FormName=UtilityCost" ;//defined in classic.jsp

		if (jQuery('#hdnEstMode').val() == "Update")
			url+= '&saveMode=Update';
		else
			url+= '&saveMode=Save';	

		var woId=jQuery('#hdnwoId').val();
		var docType=jQuery('#hdnDocType').val();
		url+= '&woId='+woId+'&docType='+docType;
				
		if(formId.length > 0 )
			saveForm(formId,url);
		
	});

	jQuery('#btnUtilEstClear').click(function(){
		//alert("Clear clicked");
		fnUtilEstClear();
	});
	
	jQuery('#btnUtilEstDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#utilityCostEstGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#utilityCostEstGrid").jqGrid('getRowData',rowId);		
		var RequestedById =rowData.cmbutcpRequestedby;
		var UtilityId =rowData.cmbutcpUtilitymstid;
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp

		var woId=jQuery('#hdnwoId').val();		
		url+= '?q=2&FormName=UtilityCost&Type=Estimate&womid='+woId+'&reqid='+RequestedById+'&utilityid='+UtilityId;

		if(formId.length > 0 )
			deleteRecord(formId,url);
				
	});

//utility Cost Actual

	jQuery("#btnUtilActNew").click(function(){
		fnUtilActClear();			
		fnOpenUtilNewAct();			
	});
	
	jQuery('#btnUtilActInsert').click(function(){
		 //alert("Insert clicked");	
			//TxtUtilityAmt = Round((Val(TxtUtilityQty) * (Val(TxtUtilityCost) * (Val(TxtUtilityMin) / 60))), 2)		
			var TotValue = jQuery('#txtutcaQuantity').val() * jQuery('#txtutcaCost').val() * (jQuery('#txtutcaMinutes').val() / 60);			
			var Remarks = jQuery('#txautcaRemarks').val(); 
			jQuery('#txtutcaTotalvalue').val(TotValue);
					
			var formId = jQuery('#submitForm').val(); //defined in classic.jsp
			var url = "spareCostManPower_save.crt?q=2&Type=Actual&FormName=UtilityCost" ;//defined in classic.jsp
				
			if (jQuery('#hdnActMode').val() == "Update")
				url+= '&saveMode=Update';
			else
				url+= '&saveMode=Save';	

			var woId=jQuery('#hdnwoId').val();
			var docType=jQuery('#hdnDocType').val();
			url+= '&woId='+woId+'&docType='+docType;
				
			if(formId.length > 0 )
				saveForm(formId,url);
	
	});

	jQuery('#btnUtilActClear').click(function(){
		//alert("Clear clicked");
		fnUtilActClear();
	});
	
	jQuery('#btnUtilActDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#utilityCostActGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#utilityCostActGrid").jqGrid('getRowData',rowId);
		//alert(rowData.cmbutcaRequestedby);
		var RequestedById =rowData.cmbutcaRequestedby;		
		var UtilityId =rowData.cmbutcaUtilitymstid;		
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp

		var woId=jQuery('#hdnwoId').val();		
		url+= '?q=2&FormName=UtilityCost&Type=Actual&womid='+woId+'&reqid='+RequestedById+'&utilityid='+UtilityId;

		if(formId.length > 0 )
			deleteRecord(formId,url);
				
	});	
	
});

/* function fnOpenUtilNewEst() {
	jQuery('#submitForm').val("frmUtilityCostEst");
	jQuery( "#utilityCostEstDiv" ).show();
	jQuery( "#utilityCostEstDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 320,
		width: 820,
		zIndex: 99999
	});				
}
function fnOpenUtilNewAct() {
	jQuery('#submitForm').val("frmUtilityCostAct");
	jQuery( "#utilityCostActDiv" ).show();
	jQuery( "#utilityCostActDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 370,
		width: 820,
		zIndex: 99999
	});				
}
 */
 var STATIC_POPUP_IDS = {};
 function registerStaticPopUp(divId) {
   STATIC_POPUP_IDS[divId] = true;
 }

 //Wrap the original closePopUpDialoge exactly once. This catches every
 //call path in one place — the popup's own generated close icon
 //(hardcoded onclick), the Esc keyup handler LoadPopUp binds, AND
 //programmatic calls from popFormNavigation/refreshHomePageForMenu
 //during ordinary tab/menu navigation — without needing to intercept
 //or rebind each one individually.
 if (typeof closePopUpDialoge === "function" && !closePopUpDialoge._staticWrapped) {
   var _originalClosePopUpDialoge = closePopUpDialoge;
   closePopUpDialoge = function (divId, direct) {
       if (STATIC_POPUP_IDS[divId]) {
           closeStaticPopUp(divId);
           return;
       }
       return _originalClosePopUpDialoge(divId, direct);
   };
   closePopUpDialoge._staticWrapped = true;
 }

 function closeStaticPopUp(divId) {
   jQuery('#' + divId + 'PopupMask').fadeOut(100, function () {
       jQuery(this).remove();
   });
   jQuery('#' + divId).fadeOut(100); // hide only — no .remove()

   var loadPopSetting = curntLoadPopSettingQ.pop();
   if (loadPopSetting != null) {
       jQuery("#submitForm").val(loadPopSetting.sbtFormId);
       setSubmitFormUrl(loadPopSetting.submitUrl);
       jQuery('#loadFormMode').val(loadPopSetting.loadFromMode);
   }

   for (var i = formNavigations.length - 1; i >= 0; i--) {
       if (formNavigations[i].divId == "loadPopUp" + divId) {
           formNavigations.splice(i, 1);
       }
   }
   clearCommonErrorMsg();
 }
 function fnOpenUtilNewEst() {
	    jQuery('#submitForm').val("frmUtilityCostEst");

	    if (!jQuery("#utilityCostEstDiv").parent().is("#mainlayout")) {
	        jQuery("#utilityCostEstDiv")
	            .detach()
	            .addClass('static-popup-clone')
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    jQuery("#utilityCostEstDiv > div.sub-header").remove();
	    jQuery("#utilityCostActDiv > a.close").remove();  

	    LoadPopUp(
	        "utilityCostEstDiv", "", true, "820px", "320px", "10%", "15%", "",
	        "Utility Cost Estimate"
	    );

	    registerStaticPopUp("utilityCostEstDiv");
	}

	function fnOpenUtilNewAct() {
	    jQuery('#submitForm').val("frmUtilityCostAct");

	    if (!jQuery("#utilityCostActDiv").parent().is("#mainlayout")) {
	        jQuery("#utilityCostActDiv")
	            .detach()
	            .addClass('static-popup-clone')
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    jQuery("#utilityCostActDiv > div.sub-header").remove();
	    jQuery("#utilityCostActDiv > a.close").remove();  

	    LoadPopUp(
	        "utilityCostActDiv", "", true, "820px", "370px", "10%", "15%", "",
	        "Utility Cost Actual"
	    );

	    registerStaticPopUp("utilityCostActDiv");
	}
var estUtilChainInitialized = false;
var actUtilChainInitialized = false;
/* function frmUtilityCostEstcmbutcpUtilitymstid_onLoadSuccess() 	{
	fillComboBox("frmUtilityCostEst","cmbutcpRequestedby","employee.commonFilter" );
	
}	 */
function frmUtilityCostEstcmbutcpUtilitymstid_onLoadSuccess() 	{
	if (!estUtilChainInitialized) {
		fillComboBox("frmUtilityCostEst","cmbutcpRequestedby","employee.commonFilter" );
	}
}	

/* function frmUtilityCostEstcmbutcpRequestedby_onLoadSuccess() 	{
	fillComboBox("frmUtilityCostAct","cmbutcaUtilitymstid","combo_utilities.crt");
	processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
} */
//mano
/* function frmUtilityCostEstcmbutcpRequestedby_onLoadSuccess() {
    fillComboBox("frmUtilityCostAct","cmbutcaUtilitymstid","combo_utilities.crt");

    // Only default to the logged-in user when nothing is selected yet
    // (i.e. fresh "Add New", not after the user has already picked someone,
    // and not on every reload triggered by re-filling the combo)
    if (jQuery('#hdnEstMode').val() != "Update" &&
        !jQuery('#cmbutcpRequestedby').combobox('getValue')) {
        processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
    }
} */

function frmUtilityCostEstcmbutcpRequestedby_onLoadSuccess() {
	if (!estUtilChainInitialized) {
		fillComboBox("frmUtilityCostAct","cmbutcaUtilitymstid","combo_utilities.crt");
		processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
	}
	estUtilChainInitialized = true;
}

function setEstUserRecallSuccess(result) {
	jQuery('#cmbutcpRequestedby').combobox('setValue',result.userId);	
}	

/* function frmUtilityCostActcmbutcaUtilitymstid_onLoadSuccess() 	{
	fillComboBox("frmUtilityCostAct","cmbutcaRequestedby","employee.commonFilter" );

	if (jQuery('#hdnGlbType').val() == "Actual")	{
		jQuery('#submitForm').val('frmUtilityCostAct');
		disableForm("frmUtilityCostEst");
		jQuery('#btnUtilEstNew').attr('readonly','readonly');	
		jQuery('#btnUtilEstDelete').attr('readonly','readonly');			
	}
	else { 
		jQuery('#submitForm').val('frmUtilityCostEst');
		disableForm("frmUtilityCostAct");
		jQuery('#btnUtilActNew').attr('readonly','readonly');	
		jQuery('#btnUtilActDelete').attr('readonly','readonly');
	}
}	 */
function frmUtilityCostActcmbutcaUtilitymstid_onLoadSuccess() 	{
	if (!actUtilChainInitialized) {
		fillComboBox("frmUtilityCostAct","cmbutcaRequestedby","employee.commonFilter" );
	}

	if (jQuery('#hdnGlbType').val() == "Actual")	{
		jQuery('#submitForm').val('frmUtilityCostAct');
		disableForm("frmUtilityCostEst");
		jQuery('#btnUtilEstNew').attr('readonly','readonly');	
		jQuery('#btnUtilEstDelete').attr('readonly','readonly');			
	}
	else { 
		jQuery('#submitForm').val('frmUtilityCostEst');
		disableForm("frmUtilityCostAct");
		jQuery('#btnUtilActNew').attr('readonly','readonly');	
		jQuery('#btnUtilActDelete').attr('readonly','readonly');
	}
}	

/* function frmUtilityCostActcmbutcaRequestedby_onLoadSuccess() 	{	
	processAjaxCalls("comb_setUser.crt","","setActUserRecallSuccess","setuserRecallError");
} */
function frmUtilityCostActcmbutcaRequestedby_onLoadSuccess() 	{	
	if (!actUtilChainInitialized) {
		processAjaxCalls("comb_setUser.crt","","setActUserRecallSuccess","setuserRecallError");
	}
	actUtilChainInitialized = true;
}
function setActUserRecallSuccess(result) {
	jQuery('#cmbutcaRequestedby').combobox('setValue',result.userId);	
}	
	
function fnUtilEstClear() {
	enableFields("cmbutcpRequestedby");
	enableFields("cmbutcpUtilitymstid");
	jQuery('#cmbutcpRequestedby').combobox('clear');
	jQuery('#cmbutcpUtilitymstid').combobox('clear');
	jQuery('#txtutcpQuantity').val("");
	jQuery('#txtutcpMinutes').val("");
	jQuery('#txtutcpCost').val("0");		 
	jQuery('#txtutcpTotalvalue').val("0");
	jQuery('#txautcpRemarks').val("");
	jQuery('#hdnEstMode').val("");
}

function fnUtilActClear() {
	enableFields("cmbutcaRequestedby");
	enableFields("cmbutcaUtilitymstid");
	jQuery('#cmbutcaRequestedby').combobox('clear');
	jQuery('#cmbutcaUtilitymstid').combobox('clear');
	jQuery('#dteutcaDate').datebox('clear');
	fillWithCurrentDate('dteutcaDate');
	jQuery('#txtutcaQuantity').val("");
	jQuery('#txtutcaMinutes').val("");
	jQuery('#txtutcaCost').val("0");		 
	jQuery('#txtutcaTotalvalue').val("0");
	jQuery('#txautcaRemarks').val("");
	jQuery('#hdnActMode').val("");
}
function utilityCostEstGrid_dblClick(id)
{
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Actual") return ;
	
	var rowData = jQuery("#utilityCostEstGrid").jqGrid('getRowData',id);	
	if (rowData.cmbutcpRequestedby != null )
		jQuery('#hdnEstMode').val("Update");
	else
		jQuery('#hdnEstMode').val("");

	readOnlyFields("cmbutcpRequestedby");
	readOnlyFields("cmbutcpUtilitymstid");
	
	jQuery('#cmbutcpRequestedby').combobox('setValue',rowData.cmbutcpRequestedby);
	jQuery('#cmbutcpUtilitymstid').combobox('setValue',rowData.cmbutcpUtilitymstid);	
	jQuery('#txtutcpQuantity').val(rowData.txtutcpQuantity);
	jQuery('#txtutcpMinutes').val(rowData.txtutcpMinutes);
	jQuery('#txtutcpCost').val(rowData.txtutcpCost);
	jQuery('#txtutcpTotalvalue').val(rowData.txtutcpTotalvalue);
	jQuery('#txautcpRemarks').val(rowData.txautcpRemarks);

	fnOpenUtilNewEst();
}

function utilityCostActGrid_dblClick(id)
{
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Estimate") 	return ;
	
	var rowData = jQuery("#utilityCostActGrid").jqGrid('getRowData',id);
	//alert(rowData.cmbutcaRequestedby);
	if (rowData.cmbutcaRequestedby!= null )
		jQuery('#hdnActMode').val("Update");
	else
		jQuery('#hdnActMode').val("");

	readOnlyFields("cmbutcaRequestedby");
	readOnlyFields("cmbutcaUtilitymstid");
	
	jQuery('#cmbutcaRequestedby').combobox('setValue',rowData.cmbutcaRequestedby);
	jQuery('#cmbutcaUtilitymstid').combobox('setValue',rowData.cmbutcaUtilitymstid);			
	jQuery('#txtutcaQuantity').val(rowData.txtutcaQuantity);
	jQuery('#txtutcaMinutes').val(rowData.txtutcaMinutes);
	jQuery('#txtutcaCost').val(rowData.txtutcaCost);
	jQuery('#txtutcaTotalvalue').val(rowData.txtutcaTotalvalue);
	jQuery('#txautcaRemarks').val(rowData.txautcaRemarks);	
	jQuery('#dteutcaDate').datebox('setValue',rowData.dteutcaDate);

	fnOpenUtilNewAct();	
		
}

function  frmUtilityCostEstcmbutcpRequestedby_onSelect(record)
{
	//alert(jQuery("#cmbutcpRequestedby").combobox('getValue'));
	processAjaxCalls("empCost_getEmpCost.crt","?q=2&empId="+record.id,"empIdRecallSuccess","empIdRecallError");
}

function  frmUtilityCostActcmbutcaRequestedby_onSelect(record)
{
	//alert(jQuery("#cmbutcaRequestedby").combobox('getValue'));
	processAjaxCalls("empCost_getEmpCost.crt","?q=2&empId="+record.id,"actualempIdRecallSuccess","empIdRecallError");
}

function empIdRecallSuccess(result)
{
	//alert(result.empDetails.empNo);
	jQuery('#hdnEmpNo').val(result.empDetails.empNo);	
	jQuery('#hdnEmpName').val(result.empDetails.empName);
	//mano
	/* if (result.empDetails.empCost ==null || result.empDetails.empCost == "" || result.empDetails.empCost == " ")
		displayText('txtutcpCost',"0");
	else
		displayText('txtutcpCost',result.empDetails.empCost);
		
	displayText('txtutcpTotalvalue',"0"); */
}

function actualempIdRecallSuccess(result)
{
	jQuery('#hdnEmpNo').val(result.empDetails.empNo);	
	jQuery('#hdnEmpName').val(result.empDetails.empName);
//mano has commented 
	/* if (result.empDetails.empNorRate == null || result.empDetails.empNorRate ==""  || result.empDetails.empNorRate ==" "  )
		displayText('txtutcaCost',"0");
	else
		displayText('txtutcaCost',result.empDetails.empNorRate);
	displayText('txtutcaTotalvalue',"0"); */
}

//utility code & name
function  frmUtilityCostEstcmbutcpUtilitymstid_onSelect(record)
{
	//alert(jQuery("#cmbutcpUtilitymstid").combobox('getValue'));
	//alert("hi");
	var utilityId =record.id;
	//alert("utilityId"+utilityId);
	var currDate = getServerDateTime();
	var effDate =  currDate.getDate()+'-'+getMonthStringFromInt(currDate.getMonth())+'-'+currDate.getFullYear();
	//alert("effDate"+effDate);
	var dataStr ="?q=2&utilityId="+utilityId+"&effDate="+effDate;
	
	processAjaxCalls("utilityCost_getUtilityCost.crt",dataStr,"estUtilityIdRecallSuccess","utilityIdRecallError");
}

function  frmUtilityCostActcmbutcaUtilitymstid_onSelect(record)
{
	//alert(jQuery("#cmbutcaUtilitymstid").combobox('getValue'));
	var utilityId =record.id;
	var effDate ;
	if (jQuery('#dteutcaDate').datebox('getValue') =="")
		effDate = Date.now();
	else
		effDate = jQuery('#dteutcaDate').datebox('getValue');
	//alert(effDate);
	var dataStr ="?q=2&utilityId="+utilityId+"&effDate="+effDate;
	
	processAjaxCalls("utilityCost_getUtilityCost.crt",dataStr,"actUtilityIdRecallSuccess","utilityIdRecallError");
}

function estUtilityIdRecallSuccess(result)
{
	//alert(result.utilityDetails.utilityName);
	jQuery('#hdnUtilityCode').val(result.utilityDetails.utilityCode);	
	jQuery('#hdnUtilityName').val(result.utilityDetails.utilityName);

	if (result.utilityDetails.utilityCost =="" || result.utilityDetails.utilityCost ==" ")
		jQuery('#txtutcpCost').val("0");
	else			
		jQuery('#txtutcpCost').val(result.utilityDetails.utilityCost);	
}

function actUtilityIdRecallSuccess(result)
{
	//alert(result.utilityDetails.utilityName);
	jQuery('#hdnUtilityCode').val(result.utilityDetails.utilityCode);	
	jQuery('#hdnUtilityName').val(result.utilityDetails.utilityName);
	if (result.utilityDetails.utilityCost =="" || result.utilityDetails.utilityCost ==" ")
		jQuery('#txtutcaCost').val("0");
	else
		jQuery('#txtutcaCost').val(result.utilityDetails.utilityCost);	
}


function frmUtilityCostEst_successsCallback() 	{	
	jQuery("#utilityCostEstGrid").jqGrid().trigger("reloadGrid");
	fnUtilEstClear();		
	fnSetPageTotal();		
	//jQuery( "#utilityCostEstDiv" ).dialog("close");
	//mano
	closeStaticPopUp("utilityCostEstDiv", true);

}
function frmUtilityCostAct_successsCallback(result) 	{
	jQuery("#utilityCostActGrid").trigger("reloadGrid");
	fnUtilActClear();
	fnSetPageTotal();
	//jQuery( "#utilityCostActDiv" ).dialog("close");
	closeStaticPopUp("utilityCostActDiv", true);
		
}
function frmUtilityCostEst_deleteSuccessCallback() 	{
	jQuery("#utilityCostEstGrid").trigger("reloadGrid");
	fnUtilEstClear();
}		 
function frmUtilityCostAct_deleteSuccessCallback() 	{
	jQuery("#utilityCostActGrid").trigger("reloadGrid");
	fnUtilActClear();
}

function fnSetPageTotal() {
	var formType = jQuery('#hdnGlbType').val();
	var woId = jQuery('#hdnwoId').val();
	processAjaxCalls("costInfo_getPageTotal.crt","?q=2&formName=utilityCost&formType="+formType+'&woId='+woId,"getTotalSuccessRecall","getTotalRecallError");	
}

function getTotalSuccessRecall(result) {
	jQuery('#txtutilityTotal').val(result.pageTotal.value);
}

function utilityIdRecallError(result)
{ 	alert("error"); }

function costGridLoad()
{	//alert("loadeddddd");
}
</script>

<div> <input type="hidden" id="hdnEstMode"></div>
<div> <input type="hidden" id="hdnActMode"></div>
<div> <input type="hidden" id="hdnEmpName"></div>
<div> <input type="hidden" id="hdnEmpNo"></div>
<div> <input type="hidden" id="hdnUtilityName"></div>
<div> <input type="hidden" id="hdnUtilityCode"></div>

<div  class="sub-header" align="left">Utility Cost Estimate</div>

   <div title="Utility Cost Estimate" style="padding:10px;" id="utilityCostEstDiv">
	<form id="frmUtilityCostEst" name="frmUtilityCostEst">
     <table>
        <tr>          
            <td class="valigncnt" valign='top' style="width:50%" >
                <div style="float:left;margin-left: 0px;">
                     <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Utilities</label></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbutcpUtilitymstid" name="cmbutcpUtilitymstid" class="easyui-combobox"  style="width:315px;" value=""  >
                        
                        <span id="err_cmbutcpUtilitymstid" class="tpm-errormsg"></span>
                    </div>
                    <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Requested By</label></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbutcpRequestedby" name="cmbutcpRequestedby" class="easyui-combobox"  style="width:315px;" value=""  >
                        
                        <span id="err_cmbutcpRequestedby" class="tpm-errormsg"></span>
                    </div>
                </div>
            </td>
            <!--top Right content-->
            <td class="valigncnt" valign='top' style="width:50%;">
                <div  style="padding-left:20px;">
                    <div  class="easyui-paddingbfpx">                     
                    <span><label class="mandatory-lbl">Quantity</label></span>
                    <span  style="margin-left: 18px;margin-left: 24px\9;"><label class="mandatory-lbl">Minutes</label></span>
                    <span  style="margin-left: 18px;margin-left: 26px\9;"><label>Cost</label></span>
                    <span  style="margin-left: 36px;margin-left: 48px\9;"><label>Total Value</label></span>
                    </div>                  
                    <div class="easyui-paddingbfpx">
                        <input id="txtutcpQuantity" name="txtutcpQuantity" type="text" class="easyui-text" size="10" style="margin-left: 0px;">                		                          
                        <input id="txtutcpMinutes" name="txtutcpMinutes" type="text" class="easyui-text" size="10" style="margin-left: 5px;">
                        <input id="txtutcpCost" name="txtutcpCost" type="text" class="easyui-text" size="10" style="margin-left: 5px;">
                        <input id="txtutcpTotalvalue" name="txtutcpTotalvalue" type="text" class="easyui-text" size="10" style="margin-left: 5px;">
          					<span id="err_txtutcpQuantity" class="tpm-errormsg" style="float:left;" ></span>
           				<span  style="padding-left:5px;float:right;" id="err_txtutcpMinutes" class="tpm-errormsg"></span>                     
                    </div>                                         
                     <div  class="easyui-paddingbfpx">
                    	<label>Remarks</label>                        
                    </div> 
                    <div class="easyui-paddingbfpx">
                        <textarea id="txautcpRemarks" name="txautcpRemarks" rows="1" cols="35" style="float: left;"></textarea>
                      </div>
                </div>	
            </td>
        </tr>
        </table>
		<div align="center" style="float: top; padding-right: 0px">
		<br><br>
			<input type="button" id="btnUtilEstInsert" name="btnUtilEstInsert" class="easyui-button" value="Insert" style="width: 80px;"> 
			<input type="button" id="btnUtilEstClear" name="btnUtilEstClear" class="easyui-button" value="Clear" style="width: 80px;">						
		</div>
	  </form>
 	</div>
 	<table width="100%">         
     <tr>
     <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
    	<div style="float: left;padding-right: 40px;_padding-right: 20px;">
    	<table id="utilityCostEstGrid" width="500px" style="float: left;"></table> </div>
			<div id="utilityCostEstPager" style="float: center;"></div>        
     </td>
     <td  align="right" width="30%">
		<div>
			<input type="button" id="btnUtilEstNew" name="btnUtilEstNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>
			<input type="button" id="btnUtilEstDelete" name="btnUtilEstDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
		</div>
	</td>      
     </tr>        
     </table>
 
 
<div  class="sub-header" align="left">Utility Cost Actual</div>
   <div title="Utility Cost Acutal" style="padding:10px;" id="utilityCostActDiv">
	<form id="frmUtilityCostAct" name="frmUtilityCostAct">
     <table>
       <tr>
           <!--top left content -->
           <td class="valigncnt" style="width:50%" >
               <div style="float:left;margin-left: px;">
                    <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Utilities</label></div> 
                   <div class="easyui-paddingbfpx"> 
                       <input id="cmbutcaUtilitymstid" name="cmbutcaUtilitymstid" class="easyui-combobox"  style="width:315px;" value=""  >
                       
                       <span id="err_cmbutcaUtilitymstid" class="tpm-errormsg"></span>                            
                   </div>
                   <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Requested By</label></div> 
                   <div class="easyui-paddingbfpx"> 
                       <input id="cmbutcaRequestedby" name="cmbutcaRequestedby" class="easyui-combobox"  style="width:315px;" value=""  >
                       
                       <span id="err_cmbutcaRequestedby" class="tpm-errormsg"></span>
                   </div>
                   <div  class="easyui-paddingbfpx">
                   <label>Date</span>
                   </div> 
                   <div class="easyui-paddingbfpx"> 
                       <input id="dteutcaDate" name="dteutcaDate" class="easyui-datebox" required="true" style="width:150px;"/>
                       <span id="err_dteutcaDate" class="tpm-errormsg"></span>
                   </div>               
               </div>
           </td>
           <!--top Right content-->
           <td class="valigncnt" valign='top' style="width:50%;">
               <div  style="padding-left:20px;">
                   <div  class="easyui-paddingbfpx">
                   <span  ><label class="mandatory-lbl">Quantity</label></span>
                   <span  style="margin-left: 17px;margin-left: 25px\9;"><label class="mandatory-lbl">Minutes</label></span>                     
                   <span  style="margin-left: 18px;margin-left: 27px\9;"><label>Rate</label></span>
                   <span  style="margin-left: 37px;margin-left: 43px\9;"><label>Cost</label></span>                        
                   </div> 
                   <div class="easyui-paddingbfpx">
                       <input id="txtutcaQuantity" name="txtutcaQuantity" type="text" class="easyui-text" size="10" style="margin-left: 0px;">                		                          
                       <input id="txtutcaMinutes" name="txtutcaMinutes" type="text" class="easyui-text" size="10" style="margin-left: 5px;">
                       <input id="txtutcaCost" name="txtutcaCost" type="text" class="easyui-text" size="10" style="margin-left: 5px;">
                       <input id="txtutcaTotalvalue" name="txtutcaTotalvalue" type="text" class="easyui-text" size="10" style="margin-left: 5px;">
				 <div  style=" ">
         					<span id="err_txtutcaQuantity" class="tpm-errormsg" style="float:left;" ></span>
          				<span  style="padding-left:5px;float:right;"id="err_txtutcaMinutes" class="tpm-errormsg"></span>
             			 </div>                                                                                                     
                   </div> 
                    <div  class="easyui-paddingbfpx">
                   <label>Remarks</label>                        
                   </div> 
                   <div class="easyui-paddingbfpx">
                       <textarea id="txautcaRemarks" name="txautcaRemarks" rows="2" cols="36" style="float: left;"></textarea>
                       <div style="width: 80px;float: left;margin-left: 3px">                         
                     </div>
               </div>	
           </td>
       </tr>
      </table>
		<div align="center" style="float: top; padding-right: 0px">
		<br><br>
			<input type="button" id="btnUtilActInsert" name="btnUtilActInsert" class="easyui-button" value="Insert" style="width: 80px;"> 
			<input type="button" id="btnUtilActClear" name="btnUtilActClear" class="easyui-button" value="Clear" style="width: 80px;">					
		</div>        
	</form>
  </div>
  
  <table width="100%">         
     <tr>
     <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
    	<div style="float: left;padding-right: 40px;_padding-right: 20px;">
    	<table id="utilityCostActGrid" width="500px" style="float: left;"></table> </div>
			<div id="utilityCostActPager" style="float: center;"></div>        
     		<div style="float: right;width: 850px;text-align: right;margin-top: 5px;margin-right:50px">
     		<label>Total Amount</label>
     <input  id="txtutilityTotal" name="txtutilityTotal" type="text" class="easyui-text" value="" readonly="readonly" size="15">
     </div>
     </td>
     <td  align="right" width="30%">
		<div>
			<input type="button" id="btnUtilActNew" name="btnUtilActNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>
			<input type="button" id="btnUtilActDelete" name="btnUtilActDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
		</div>  
	</td>     
     </tr>        
     </table>
 	
  
 