<script type="text/javascript">
jQuery(document).ready(function(){
	
	
	jQuery('.static-popup-clone').remove();
	STATIC_POPUP_IDS = {};
	initialiseForm('frmSpareCostEst');
	initialiseForm('frmSpareCostAct');	
	
	numericTextBox('txtwscpQuantity');
	numericTextBox('txtwscaQuantity');
	numericTextBox('txtwscpRate');
	numericTextBox('txtwscaRate');
	
	//jQuery("#txtwscpRate").attr('disabled','disabled');
	jQuery("#txtwscpValue").attr('readonly','readonly');
	//jQuery("#txtwscaRate").attr('disabled','disabled');
	jQuery("#txtwscaValue").attr('readonly','readonly');

	//var factoryId= jQuery('#cmbspcnFactoryid').combobox('getValue');
	var factoryId= jQuery('#factory').val();
		
	//fillComboBox("frmSpareCostEst","cmbwscpSparesid","combo_spares.crt?q=2&factoryId="+factoryId );
	/* fillComboBox("frmSpareCostEst","cmbwscpSparesid","combo_spares.crt?q=2" ); */
	fillComboBox("frmSpareCostEst","cmbwscpSparesid","combo_spares.crt" );
/*	fillComboBox("frmSpareCostEst","cmbwscpRequestedby","employee.commonFilter" );
	fillComboBox("frmSpareCostAct","cmbwscaSparesid","spareCombo.commonFilter" );						
	fillComboBox("frmSpareCostAct","cmbwscaRequestedby","employee.commonFilter" ); 
*/
	var woid = jQuery('#hdnwoId').val();
	//q=2&formName=empCost&woId="+ woid
		
	processGridnew('spareCostEstimation_view.crt',"?q=2&formName=spareCost&woId="+ woid,"spareCostEstGrid","spareCostEstPager","","spareCostEstGrid_dblClick","","costGridLoad","");						
	processGridnew('spareCostActual_view.crt',"?q=2&formName=spareCost&woId="+ woid,"spareCostActGrid","spareCostActPager","","spareCostActGrid_dblClick","","costGridLoad","");

	jQuery("#empSpareEstDiv").hide();
	jQuery("#empSpareActDiv").hide();
	
	fnSetPageTotal();
	
//Spares Cost Estimate
	jQuery("#btnEstSparesLink").click(function(){
		/* jQuery( "#empSpareEstDiv" ).dialog("close");
		jQuery("#empSpareEstDiv").hide(); */
		//mano
		closePopUpDialoge("empSpareEstDiv", true);
		//navigateToNextForm('SparesMaster_input.sprmst','Spare Master');				
		LoadPopUp("loadEstSpares", "SparesMaster_input.sprmst",  true,"98%","95%","1px","1px",  "showEstSpares_success","Spares Creation", true,true);
	});		

	jQuery("#btnActSparesLink").click(function(){	
		//navigateToNextForm('SparesMaster_input.sprmst','Spare Master');
		/* jQuery( "#empSpareActDiv" ).dialog("close");
		jQuery("#empSpareActDiv").hide();	 */	
		//mano
		closePopUpDialoge("empSpareActDiv", true);
		LoadPopUp("loadActSpares", "SparesMaster_input.sprmst",  true,"98%","95%","1px","1px",  "showActSpares_success","Spares Creation", true,true);		
	});		
	
	function loadPopUp_ErrorCallback() {
		alert("error");
	}
	
	jQuery("#btnSpareEstNew").click(function(){
		fnSpareEstClear();			
		fnOpenSpareNewEst();			
	});

	jQuery('#btnSpareEstInsert').click(function(){
		 //alert("Insert clicked");

		 	var TotValue = jQuery('#txtwscpQuantity').val() * jQuery('#txtwscpRate').val(); ;
			TotValue = TotValue.toFixed(2);
			jQuery('#txtwscpValue').val(TotValue);	
			
			var formId = jQuery('#submitForm').val(); //defined in classic.jsp
			var url = "spareCostManPower_save.crt?q=2&Type=Estimate&FormName=SpareCost" ;//defined in classic.jsp
				
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

	jQuery('#btnSpareEstClear').click(function(){
		//alert("Clear clicked");
		fnSpareEstClear();
	});
	
	jQuery('#btnSpareEstDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#spareCostEstGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#spareCostEstGrid").jqGrid('getRowData',rowId);
		var RequestedById =rowData.cmbwscpRequestedby;
		var SpareId =rowData.cmbwscpSparesid;
		var woId=jQuery('#hdnwoId').val();
		
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp
		url+= '?q=2&FormName=SpareCost&Type=Estimate&womid='+woId+'&reqid='+RequestedById+'&spareid='+SpareId;

		
		if(formId.length > 0 )
			deleteRecord(formId,url);
				
	});

//spareCOST ACTUAL

	jQuery("#btnSpareActNew").click(function(){
		fnSpareActClear();			
		fnOpenSpareNewAct();			
	});

	jQuery('#btnSpareActInsert').click(function(){
		//alert("insertclciked");
		var TotValue = jQuery('#txtwscaQuantity').val() * jQuery('#txtwscaRate').val(); ;
		TotValue = TotValue.toFixed(2);
		jQuery('#txtwscaValue').val(TotValue);	
		
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var url = "spareCostManPower_save.crt?q=2&Type=Actual&FormName=SpareCost";		

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
	
	jQuery('#btnSpareActClear').click(function(){
		//alert("Clear clicked");
		fnSpareActClear();
	});

	jQuery('#btnSpareActDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#spareCostActGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#spareCostActGrid").jqGrid('getRowData',rowId);
		var RequestedById =rowData.cmbwscaRequestedby;
		var SpareId =rowData.cmbwscaSparesid;				
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp

		var woId=jQuery('#hdnwoId').val();		
		url+= '?q=2&FormName=SpareCost&Type=Actual&womid='+woId+'&reqid='+RequestedById+'&spareid='+SpareId;
		if(formId.length > 0 )
			deleteRecord(formId,url);
				
	});

	/* jQuery("#txtmpcpNormalmins").focusout(function () {	    
		var Minutes = jQuery('#txtmpcpNormalmins').val();
		var Cost = jQuery('#txtmpcpNormalcost').val();
		var TotCost = Minutes * Cost ;
		jQuery('#txtmpcpTotalvalue').val(TotCost);
	});

	jQuery("#txtmpcsNormalmins").focusout(function () {	    
		var Minutes = jQuery('#txtmpcpNormalmins').val();
		var Cost = jQuery('#txtmpcpNormalcost').val();
		var TotCost = Minutes * Cost ;
		jQuery('#txtmpcpTotalvalue').val(TotCost);
	}); */
	//mano
	// --- Spare Cost Estimate: auto-calc Cost on Qty/Rate blur ---
	function fnCalcSpareEstValue() {
	    var Qty = parseFloat(jQuery('#txtwscpQuantity').val()) || 0;
	    var Rate = parseFloat(jQuery('#txtwscpRate').val()) || 0;
	    var TotValue = (Qty * Rate).toFixed(2);
	    jQuery('#txtwscpValue').val(TotValue);
	}
	jQuery("#txtwscpQuantity").focusout(function () {
	    fnCalcSpareEstValue();
	});
	jQuery("#txtwscpRate").focusout(function () {
	    fnCalcSpareEstValue();
	});

	// --- Spare Cost Actual: auto-calc Cost on Qty/Rate blur ---
	function fnCalcSpareActValue() {
	    var Qty = parseFloat(jQuery('#txtwscaQuantity').val()) || 0;
	    var Rate = parseFloat(jQuery('#txtwscaRate').val()) || 0;
	    var TotValue = (Qty * Rate).toFixed(2);
	    jQuery('#txtwscaValue').val(TotValue);
	}
	jQuery("#txtwscaQuantity").focusout(function () {
	    fnCalcSpareActValue();
	});
	jQuery("#txtwscaRate").focusout(function () {
	    fnCalcSpareActValue();
	});
	
});

/* function fnOpenSpareNewEst() {
	var factoryId= jQuery('#factory').val();		
	reloadCombo("frmSpareCostEst","cmbwscpSparesid","combo_spares.crt?q=2&factoryId="+factoryId );
	
	jQuery('#submitForm').val("frmSpareCostEst");
	jQuery( "#empSpareEstDiv" ).show();
	jQuery( "#empSpareEstDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 300,
		width: 410,
		//mano
		zIndex: 99999
	});				
}
function fnOpenSpareNewAct() {
	var factoryId= jQuery('#factory').val();		
	reloadCombo("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt?q=2&factoryId="+factoryId );
	
	jQuery('#submitForm').val("frmSpareCostAct");
	jQuery( "#empSpareActDiv" ).show();
	jQuery( "#empSpareActDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 320,
		width: 820,
		//mano
		zIndex: 99999
	});				
} */
/* function fnOpenSpareNewEst() {
	reloadCombo("frmSpareCostEst","cmbwscpSparesid","combo_spares.crt" );
	
	jQuery('#submitForm').val("frmSpareCostEst");
	jQuery( "#empSpareEstDiv" ).show();
	jQuery( "#empSpareEstDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 300,
		width: 410,
		zIndex: 99999
	});				
}

function fnOpenSpareNewAct() {
	reloadCombo("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt" );
	
	jQuery('#submitForm').val("frmSpareCostAct");
	jQuery( "#empSpareActDiv" ).show();
	jQuery( "#empSpareActDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 320,
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
 
 /* function fnOpenSpareNewEst() {
	    reloadCombo("frmSpareCostEst","cmbwscpSparesid","combo_spares.crt");
	    jQuery('#submitForm').val("frmSpareCostEst");

	    // LoadPopUp only detaches/repositions the div when a url is passed.
	    // This content is static (no AJAX load), so replicate that step ourselves
	    // once, so it doesn't stay trapped inside any ancestor's stacking context.
	    if (!jQuery("#empSpareEstDiv").parent().is("#mainlayout")) {
	        jQuery("#empSpareEstDiv")
	            .detach()                 // keeps bound events/data, unlike remove()
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    LoadPopUp(
	        "empSpareEstDiv",
	        "",
	        true,
	        "410px",
	        "300px",
	        "10%",
	        "15%",
	        "",
	        "Spare Cost Estimate"
	    );
	}

	function fnOpenSpareNewAct() {
	    reloadCombo("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt");
	    jQuery('#submitForm').val("frmSpareCostAct");

	    if (!jQuery("#empSpareActDiv").parent().is("#mainlayout")) {
	        jQuery("#empSpareActDiv")
	            .detach()
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    LoadPopUp(
	        "empSpareActDiv",
	        "",
	        true,
	        "820px",
	        "320px",
	        "10%",
	        "15%",
	        "",
	        "Spare Cost Actual"
	    );
	} */
	//mano 
	function fnOpenSpareNewEst() {
    reloadCombo("frmSpareCostEst","cmbwscpSparesid","combo_spares.crt");
    jQuery('#submitForm').val("frmSpareCostEst");

    if (!jQuery("#empSpareEstDiv").parent().is("#mainlayout")) {
        jQuery("#empSpareEstDiv")
            .detach()
            .addClass('static-popup-clone')
            .insertAfter("#mainlayout")
            .css('margin-top', '1%')
            .css('border', '6px solid #444444')
            .css('border-radius', '8px');
    }


    jQuery("#empSpareEstDiv > div.sub-header").remove();
    jQuery("#empSpareEstDiv > a.close").remove(); 

    LoadPopUp(
        "empSpareEstDiv", "", true, "410px", "300px", "10%", "15%", "",
        "Spare Cost Estimate"
    );

    registerStaticPopUp("empSpareEstDiv");
}

function fnOpenSpareNewAct() {
    reloadCombo("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt");
    jQuery('#submitForm').val("frmSpareCostAct");

    if (!jQuery("#empSpareActDiv").parent().is("#mainlayout")) {
        jQuery("#empSpareActDiv")
            .detach()
            .addClass('static-popup-clone')
            .insertAfter("#mainlayout")
            .css('margin-top', '1%')
            .css('border', '6px solid #444444')
            .css('border-radius', '8px');
    }

    jQuery("#empSpareActDiv > div.sub-header").remove();
    jQuery("#empSpareActDiv > a.close").remove(); 

    LoadPopUp(
        "empSpareActDiv", "", true, "820px", "320px", "10%", "15%", "",
        "Spare Cost Actual"
    );

    registerStaticPopUp("empSpareActDiv");
}
 /* function frmSpareCostEstcmbwscpSparesid_onLoadSuccess() 	{
	fillComboBox("frmSpareCostEst","cmbwscpRequestedby","employee.commonFilter" );
} */
var estChainInitialized = false;
var actChainInitialized = false;

/* function frmSpareCostEstcmbwscpSparesid_onLoadSuccess() {
	fillComboBox("frmSpareCostEst","cmbwscpRequestedby","employee.commonFilter" );
} */
/* function frmSpareCostEstcmbwscpSparesid_onLoadSuccess() {
	if (!spareCostChainInitialized) {
		fillComboBox("frmSpareCostEst","cmbwscpRequestedby","employee.commonFilter" );
	}
} */
function frmSpareCostEstcmbwscpSparesid_onLoadSuccess() {
	if (!estChainInitialized) {
		fillComboBox("frmSpareCostEst","cmbwscpRequestedby","employee.commonFilter" );
	}
}

/* function frmSpareCostEstcmbwscpRequestedby_onLoadSuccess() 	{	
	var factoryId= jQuery('#factory').val();	
	//fillComboBox("frmSpareCostAct","cmbwscaSparesid","spareCombo.commonFilter" );
	fillComboBox("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt?&factoryId="+factoryId);
	processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
} */
/* function frmSpareCostEstcmbwscpRequestedby_onLoadSuccess() 	{	
	fillComboBox("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt");
	processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
}
 */
 
 /* function frmSpareCostEstcmbwscpRequestedby_onLoadSuccess() 	{	
		if (!spareCostChainInitialized) {
			fillComboBox("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt");
			processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
		}
	}
  */
  function frmSpareCostEstcmbwscpRequestedby_onLoadSuccess() {	
		if (!estChainInitialized) {
			fillComboBox("frmSpareCostAct","cmbwscaSparesid","combo_spares.crt");
			processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
		}
		estChainInitialized = true;
	}

 function setEstUserRecallSuccess(result) {
	jQuery('#cmbwscpRequestedby').combobox('setValue',result.userId);	
}	

/* function frmSpareCostActcmbwscaSparesid_onLoadSuccess() 	{
	fillComboBox("frmSpareCostAct","cmbwscaRequestedby","employee.commonFilter" );
	
	if (jQuery('#hdnGlbType').val() == "Actual")	{
		jQuery('#submitForm').val('frmSpareCostAct');
		disableForm("frmSpareCostEst");
		jQuery('#btnSpareEstNew').attr('readonly','readonly');	
		jQuery('#btnSpareEstDelete').attr('readonly','readonly');			
			
	}
	else { 
		jQuery('#submitForm').val('frmSpareCostEst');
		disableForm("frmSpareCostAct");
		jQuery('#btnSpareActNew').attr('readonly','readonly');	
		jQuery('#btnSpareActDelete').attr('readonly','readonly');			
	}	
} */
/* function frmSpareCostActcmbwscaSparesid_onLoadSuccess() {
	// Only cascade into Requestedby + disable logic on the first pass too.
	if (!spareCostChainInitialized) {
		fillComboBox("frmSpareCostAct","cmbwscaRequestedby","employee.commonFilter" );
	}

	if (jQuery('#hdnGlbType').val() == "Actual") {
		jQuery('#submitForm').val('frmSpareCostAct');
		disableForm("frmSpareCostEst");
		jQuery('#btnSpareEstNew').attr('readonly','readonly');	
		jQuery('#btnSpareEstDelete').attr('readonly','readonly');			
	}
	else { 
		jQuery('#submitForm').val('frmSpareCostEst');
		disableForm("frmSpareCostAct");
		jQuery('#btnSpareActNew').attr('readonly','readonly');	
		jQuery('#btnSpareActDelete').attr('readonly','readonly');			
	}	
} */

function frmSpareCostActcmbwscaSparesid_onLoadSuccess() {
	if (!actChainInitialized) {
		fillComboBox("frmSpareCostAct","cmbwscaRequestedby","employee.commonFilter" );
	}

	if (jQuery('#hdnGlbType').val() == "Actual") {
		jQuery('#submitForm').val('frmSpareCostAct');
		disableForm("frmSpareCostEst");
		jQuery('#btnSpareEstNew').attr('readonly','readonly');	
		jQuery('#btnSpareEstDelete').attr('readonly','readonly');			
	}
	else { 
		jQuery('#submitForm').val('frmSpareCostEst');
		disableForm("frmSpareCostAct");
		jQuery('#btnSpareActNew').attr('readonly','readonly');	
		jQuery('#btnSpareActDelete').attr('readonly','readonly');			
	}	
}
/* function frmSpareCostActcmbwscaRequestedby_onLoadSuccess() 	{							
	processAjaxCalls("comb_setUser.crt","","setActUserRecallSuccess","setuserRecallError");
} */
/* function frmSpareCostActcmbwscaRequestedby_onLoadSuccess() {							
	processAjaxCalls("comb_setUser.crt","","setActUserRecallSuccess","setuserRecallError");
	// mark the full chain as complete after the very first end-to-end pass
	spareCostChainInitialized = true;
} */
function frmSpareCostActcmbwscaRequestedby_onLoadSuccess() {							
	if (!actChainInitialized) {
		processAjaxCalls("comb_setUser.crt","","setActUserRecallSuccess","setuserRecallError");
	}
	actChainInitialized = true;
}
function setActUserRecallSuccess(result) {
	jQuery('#cmbwscaRequestedby').combobox('setValue',result.userId);	
}	


function fnSpareEstClear() { 
	enableFields("cmbwscpRequestedby");
	enableFields("cmbwscpSparesid");
	jQuery('#cmbwscpRequestedby').combobox('clear');
	jQuery('#cmbwscpSparesid').combobox('clear');
	jQuery('#txtwscpQuantity').val("");
	jQuery('#txtwscpRate').val("0");		 
	jQuery('#txtwscpValue').val("0");
	jQuery('#hdnEstMode').val("");
}

function fnSpareActClear()	{
	enableFields("cmbwscaRequestedby");
	enableFields("cmbwscaSparesid");
	jQuery('#cmbwscaRequestedby').combobox('clear');
	jQuery('#cmbwscaSparesid').combobox('clear');
	jQuery('#txtwscaQuantity').val("");
	jQuery('#txtwscaRate').val("0");		 
	jQuery('#txtwscaValue').val("0");
	jQuery('#hdnActMode').val("");
}
function spareCostEstGrid_dblClick(id)
{
	//alert(id);
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Actual") return ;
		
	var rowData = jQuery("#spareCostEstGrid").jqGrid('getRowData',id);
	//alert(rowData.cmbwscpSparesid);
	if (rowData.cmbwscpSparesid != null )
		jQuery('#hdnEstMode').val("Update");
	else
		jQuery('#hdnEstMode').val("");

	readOnlyFields("cmbwscpRequestedby");
	readOnlyFields("cmbwscpSparesid");
		
	jQuery('#cmbwscpRequestedby').combobox('setValue',rowData.cmbwscpRequestedby);	
	jQuery('#cmbwscpSparesid').combobox('setValue',rowData.cmbwscpSparesid);
	jQuery('#txtwscpQuantity').val(rowData.txtwscpQuantity);
	jQuery('#txtwscpRate').val(rowData.txtwscpRate);
	jQuery('#txtwscpValue').val(rowData.txtwscpValue);

	fnOpenSpareNewEst();	
}

function spareCostActGrid_dblClick(id)
{
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Estimate") 	return ;
	
	//alert(id);
	var rowData = jQuery("#spareCostActGrid").jqGrid('getRowData',id);
	//alert(rowData.cmbwscaSparesid);
	if (rowData.cmbwscaSparesid != null )
		jQuery('#hdnActMode').val("Update");
	else
		jQuery('#hdnActMode').val("");

	readOnlyFields("cmbwscaRequestedby");
	readOnlyFields("cmbwscaSparesid");
	
	jQuery('#cmbwscaRequestedby').combobox('setValue',rowData.cmbwscaRequestedby);	
	jQuery('#cmbwscaSparesid').combobox('setValue',rowData.cmbwscaSparesid);
	jQuery('#txtwscaQuantity').val(rowData.txtwscaQuantity);
	jQuery('#txtwscaRate').val(rowData.txtwscaRate);
	jQuery('#txtwscaValue').val(rowData.txtwscaValue);

	 fnOpenSpareNewAct();	
}
function  frmSpareCostEstcmbwscpSparesid_onSelect(record)
{
	//alert(jQuery("#cmbwscpSparesid").combobox('getValue'));
	processAjaxCalls("spareCost_getSpareCost.crt","?q=2&spareId="+record.id,"spareIdRecallSuccess","spareIdRecallError");
}

function  frmSpareCostActcmbwscaSparesid_onSelect(record)
{
	//alert(jQuery("#cmbwscaSparesid").combobox('getValue'));
	processAjaxCalls("spareCost_getSpareCost.crt","?q=2&spareId="+record.id,"actualspareIdRecallSuccess","spareIdRecallError");
}

function spareIdRecallSuccess(result)
 {
	 //alert(result.spareDetails.spareNo);
	jQuery('#hdnSpareNo').val(result.spareDetails.spareNo);	
	jQuery('#hdnSpareName').val(result.spareDetails.spareName);	
	if (result.spareDetails.spareRate !=null )
		displayText('txtwscpRate',result.spareDetails.spareRate);
	else
		displayText('txtwscpRate',"0");
	
	displayText('txtwscpValue',"0");
 }

function actualspareIdRecallSuccess(result)
{
	 	//alert(result.spareDetails.spareNo);
	 	//alert(result.spareDetails.spareName);
		jQuery('#hdnSpareNo').val(result.spareDetails.spareNo);	
		jQuery('#hdnSpareName').val(result.spareDetails.spareName);	
		if (result.spareDetails.spareRate !=null )
			displayText('txtwscaRate',result.spareDetails.spareRate);
		else
		displayText('txtwscaRate',"0");
		displayText('txtwscaValue',"0");
}


function frmSpareCostEst_successsCallback() 	{	
	jQuery("#spareCostEstGrid").jqGrid().trigger("reloadGrid");
	fnSpareEstClear();
	fnSetPageTotal();
	//jQuery( "#empSpareEstDiv" ).dialog("close");	
	//mano
	 closeStaticPopUp("empSpareEstDiv", true);
}
function frmSpareCostAct_successsCallback(result) 	{
	jQuery("#spareCostActGrid").trigger("reloadGrid");
	fnSpareActClear();
	fnSetPageTotal();
	//jQuery( "#empSpareActDiv" ).dialog("close");
	closeStaticPopUp("empSpareActDiv", true);
}

function frmSpareCostEst_deleteSuccessCallback() 	{
	jQuery("#spareCostEstGrid").trigger("reloadGrid");
	fnSpareEstClear();
}		 
function frmSpareCostAct_deleteSuccessCallback() 	{
	jQuery("#spareCostActGrid").trigger("reloadGrid");
	fnSpareActClear();
}

function fnSetPageTotal() {
	var formType = jQuery('#hdnGlbType').val();
	var woId = jQuery('#hdnwoId').val();
	processAjaxCalls("costInfo_getPageTotal.crt","?q=2&formName=spareCost&formType="+formType+'&woId='+woId,"getTotalSuccessRecall","getTotalRecallError");	
}

function getTotalSuccessRecall(result) {
	jQuery('#txtspareTotal').val(result.pageTotal.value);
}

function empIdRecallError(result)
{ alert("error"); }

function costGridLoad()
{	//alert("loadeddddd");
}
</script>

<div> <input type="hidden" id="hdnEstMode"></div>
<div> <input type="hidden" id="hdnActMode"></div>
<div> <input type="hidden" id="hdnSpareName"></div>
<div> <input type="hidden" id="hdnSpareNo"></div>

<div  class="sub-header" align="left">Spare Cost Estimation</div>
 	<div title="Spare Cost Estimate" style="padding:10px;"  id="empSpareEstDiv">
       <form id="frmSpareCostEst" name="frmSpareCostEst">           
		<div style="float:left;padding:0px;margin-left: px;">
           <div  class="easyui-paddingbfpx" align="left"><label class="mandatory-lbl">Requested By</label></div> 
            <div class="easyui-paddingbfpx"> 
                <input id="cmbwscpRequestedby" name="cmbwscpRequestedby" class="easyui-combobox"  style="width:300px;" value=""  >
                
                <span id="err_cmbwscpRequestedby" class="tpm-errormsg"></span>
            </div>  
           <div  class="easyui-paddingbfpx" align="left"><label class="mandatory-lbl">Spares</label></div> 
            <div class="easyui-paddingbfpx"> 
                <input id="cmbwscpSparesid" name="cmbwscpSparesid" class="easyui-combobox"  style="width:300px;" value=""  >
                <input type="button" id="btnEstSparesLink" name="btnEstSparesLink" class="easyui-button" value="..." style="padding-left: 0px;height: 20px;">                
                <span id="err_cmbwscpSparesid" class="tpm-errormsg"></span>                
            </div>  
			<div  class="easyui-paddingbfpx" align="left"><label class="mandatory-lbl">Qty</label>
                <span  style="margin-left: 70px;margin-left: 80px\9;"><label>Rate</label></span>
                <span  style="margin-left: 70px;margin-left: 73px\9;"><label>Cost</label></span>           
			</div>
             <div style="float:left;margin-left: px;">
                <input id="txtwscpQuantity" name="txtwscpQuantity" type="text" class="easyui-text" size="15" >
                <span id="err_txtwscpQuantity" class="tpm-errormsg"></span>
             </div>
             <div style="float:left;margin-left: px;">
                <input id="txtwscpRate" name="txtwscpRate" type="text" class="easyui-text" size="15" style="margin-left: 16px;" value="0">
                <span id="err_txtwscpRate" class="tpm-errormsg"></span>
             </div>
             <div style="float:left;margin-left: px;">
                <input id="txtwscpValue" name="txtwscpValue" type="text" class="easyui-text" size="15" style="margin-left: 16px;" value="0">
                <span id="err_txtwscpValue" class="tpm-errormsg"></span>
             </div>
             <br>             
			<div align="center" style="float: top; padding-right: 0px">
			<br><br>
				<input type="button" id="btnSpareEstInsert" name="btnSpareEstInsert" class="easyui-button" value="Insert" style="width: 80px;"> 
				<input type="button" id="btnSpareEstClear" name="btnSpareEstClear" class="easyui-button" value="Clear" style="width: 80px;">					
			</div>
          </div> 
     	</form>
     </div>
            
	<table width="100%">
     <tr>
     <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
     	<div style="float: left;padding-right: 40px;_padding-right: 20px;">       		
      			<table id="spareCostEstGrid" width="500px" style="float: left;"></table> </div>
		<div id="spareCostEstPager" style="float: center;"></div>        	
     </td>                 
     <td align="right" width="30%">
		<div>
			<input type="button" id="btnSpareEstNew" name="btnSpareEstNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>
			<input type="button" id="btnSpareEstDelete" name="btnSpareEstDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
		</div>
	</td>
	</tr>
	</table>       		

<br>

<div class="sub-header" align="left" >Spare Cost Actual</div>
<div title="Spare Cost Actual" style="float:left;padding:10px;margin-left: 10px;" id="empSpareActDiv">
	<form id="frmSpareCostAct" name="frmSpareCostAct">
        <table>      
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 10px;">
                         <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Spares</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbwscaSparesid" name="cmbwscaSparesid" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" id="btnActSparesLink" name="btnActSparesLink" class="easyui-button" value="..." style="padding-left: 0px;height: 20px;">
                            <span id="err_cmbwscaSparesid" class="tpm-errormsg"></span>                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Requested By</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbwscaRequestedby" name="cmbwscaRequestedby" class="easyui-combobox"  style="width:315px;" value=""  >
                            
                            <span id="err_cmbwscaRequestedby" class="tpm-errormsg"></span>                            
                        </div>               
                    </div>
                </td>
                <!--top Right content-->
                <td class="valigncnt" valign='top' style="width:40%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx">
                        <label class="mandatory-lbl">Quantity</label>
                        <span  style="margin-left: 48px;margin-left: 55px\9;"><label class="mandatory-lbl">Rate</label></span>
                        <span  style="margin-left: 66px;margin-left: 75px\9;"><label>Cost</label></span>
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input id="txtwscaQuantity" name="txtwscaQuantity" type="text" class="easyui-text" size="15" >
                            <input id="txtwscaRate" name="txtwscaRate" type="text" class="easyui-text" size="15" style="margin-left: 16px;">
                            <input id="txtwscaValue" name="txtwscaValue" type="text" class="easyui-text" size="15" style="margin-left: 16px;">
                            <span id="err_txtwscaQuantity" class="tpm-errormsg"></span>
                        </div>  
                       </div>
                </td>
            </tr>
            </table>
			<div  align=center>
			<br><br>
				<input type="button" id="btnSpareActInsert" name="btnSpareActInsert" class="easyui-button" value="Insert" style="width: 80px;"> 
				<input type="button" id="btnSpareActClear" name="btnSpareActClear" class="easyui-button" value="Clear" style="width: 80px;">					
			</div>
		</form>          
	    </div>       
        
        <table border="0"  width="100%">                    
        <tr>
        <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
       	<div style="float: left;padding-right: 40px;_padding-right: 20px;">
       	<table id="spareCostActGrid" width="500px" style="float: left;"></table> </div>
		<div id="spareCostActPager" style="float: center;"></div>        
        </td>
   		<td  align="right" width="30%">
			<div>
				<input type="button" id="btnSpareActNew" name="btnSpareActNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>								
				<input type="button" id="btnSpareActDelete" name="btnSpareActDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
			</div>
		</td>                      
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <div style="float:rigth;width: 850px;text-align: right;margin-top: 5px;margin-right:50px">
       <label> Total Amount</label>
        <input id="txtspareTotal" name="txtspareTotal" type="text" class="easyui-text" value="" readonly="readonly"size="15">
        </div>
        </td>
        </tr>
        </table>

 