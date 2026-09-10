<script type="text/javascript">
jQuery(document).ready(function(){

	
	jQuery('.static-popup-clone').remove();
	STATIC_POPUP_IDS = {};
	initialiseForm('frmOtherCostEst');
	initialiseForm('frmOtherCostAct');	
		
	fillComboBox("frmOtherCostEst","cmbotcpOthercostmstid","combo_expense.crt");
/*	fillComboBox("frmOtherCostEst","cmbotcpRequestedby","employee.commonFilter" );		
	fillComboBox("frmOtherCostAct","cmbotcdOthercostmstid","combo_expense.crt");
	fillComboBox("frmOtherCostAct","cmbotcdRequestedby","employee.commonFilter" );		
*/
	formatDateBox('dteotcdDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteotcdDate');
	numericTextBox('txtotcpAmount');
	numericTextBox('txtotcdAmount');	

	var woid = jQuery('#hdnwoId').val();
	//q=2&formName=empCost&woId="+ woid			
	processGridnew('otherCostEstimation_view.crt',"?q=2&formName=otherCost&woId="+ woid,"otherCostEstGrid","otherCostEstPager","","otherCostEstGrid_dblClick","","costGridLoad","");						
	processGridnew('otherCostActual_view.crt',"?q=2&formName=otherCost&woId="+ woid,"otherCostActGrid","otherCostActPager","","otherCostActGrid_dblClick","","costGridLoad","");

	jQuery("#otherCostEstDiv").hide();
	jQuery("#otherCostActDiv").hide();	
	fnSetPageTotal();

//Other Cost Estimate
	jQuery("#btnOtherEstNew").click(function(){
		fnOtherEstClear();			
		fnOpenOtherNewEst();			
	});
	
	jQuery('#btnOtherEstInsert').click(function(){
		 //alert("Insert clicked");	
			var formId = jQuery('#submitForm').val(); //defined in classic.jsp
			var url = "spareCostManPower_save.crt?q=2&Type=Estimate&FormName=OtherCost" ;//defined in classic.jsp

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

	jQuery('#btnOtherEstClear').click(function(){
			fnOtherEstClear();
	});
	
	jQuery('#btnOtherEstDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#otherCostEstGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#otherCostEstGrid").jqGrid('getRowData',rowId);
		var RequestedById =rowData.cmbotcpRequestedby;
		var OtherId =rowData.cmbotcpOthercostmstid;
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp

		var woId=jQuery('#hdnwoId').val();		
		url+= '?q=2&FormName=OtherCost&Type=Estimate&womid='+woId+'&reqid='+RequestedById+'&otherid='+OtherId;

		if(formId.length > 0 )
			deleteRecord(formId,url);
				
	});

//other Cost Actual	
	jQuery("#btnOtherActNew").click(function(){
		fnOtherActClear();			
		fnOpenOtherNewAct();			
	});
	
	jQuery('#btnOtherActInsert').click(function(){
		 //alert("Insert clicked");	
	
			var formId = jQuery('#submitForm').val(); //defined in classic.jsp
			var url = "spareCostManPower_save.crt?q=2&Type=Actual&FormName=OtherCost" ;//defined in classic.jsp
				
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

	jQuery('#btnOtherActClear').click(function(){
		//alert("Clear clicked");
		fnOtherActClear();
	});
	
	jQuery('#btnOtherActDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#otherCostActGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#otherCostActGrid").jqGrid('getRowData',rowId);
		var RequestedById =rowData.cmbotcdRequestedby;
		var OtherId =rowData.cmbotcdOthercostmstid;
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp

		var woId=jQuery('#hdnwoId').val();		
		url+= '?q=2&FormName=OtherCost&Type=Actual&womid='+woId+'&reqid='+RequestedById+'&otherid='+OtherId;

		if(formId.length > 0 )
			deleteRecord(formId,url);
						 
	});
		
});


/* function fnOpenOtherNewEst() {
	jQuery('#submitForm').val("frmOtherCostEst");
	jQuery( "#otherCostEstDiv" ).show();
	jQuery( "#otherCostEstDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 320,
		width: 820,
		zIndex: 99999
	});				
}
function fnOpenOtherNewAct() {
	jQuery('#submitForm').val("frmOtherCostAct");
	jQuery( "#otherCostActDiv" ).show();
	jQuery( "#otherCostActDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 320,
		width: 820,
		zIndex: 99999
	});				
}

 */
 
 
 //mano 
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
 function fnOpenOtherNewEst() {
	    jQuery('#submitForm').val("frmOtherCostEst");

	    if (!jQuery("#otherCostEstDiv").parent().is("#mainlayout")) {
	        jQuery("#otherCostEstDiv")
	            .detach()
	            .addClass('static-popup-clone')
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    jQuery("#otherCostEstDiv > div.sub-header").remove();
	    jQuery("#otherCostEstDiv > a.close").remove(); 


	    LoadPopUp( "otherCostEstDiv", "", true, "820px", "320px", "10%", "15%", "", "Other Cost Estimate" );

	    registerStaticPopUp("otherCostEstDiv");
	}

	function fnOpenOtherNewAct() {
	    jQuery('#submitForm').val("frmOtherCostAct");

	    if (!jQuery("#otherCostActDiv").parent().is("#mainlayout")) {
	        jQuery("#otherCostActDiv")
	            .detach()
	            .addClass('static-popup-clone')
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    jQuery("#otherCostActDiv > div.sub-header").remove();
	    jQuery("#otherCostActDiv > a.close").remove(); 


	    LoadPopUp(
	        "otherCostActDiv", "", true, "820px", "320px", "10%", "15%", "",
	        "Other Cost Actual"
	    );

	    registerStaticPopUp("otherCostActDiv");
	}
var estOtherChainInitialized = false;
var actOtherChainInitialized = false;

/* function frmOtherCostEstcmbotcpOthercostmstid_onLoadSuccess() 	{
	fillComboBox("frmOtherCostEst","cmbotcpRequestedby","employee.commonFilter" );
}	 */
function frmOtherCostEstcmbotcpOthercostmstid_onLoadSuccess() 	{
	if (!estOtherChainInitialized) {
		fillComboBox("frmOtherCostEst","cmbotcpRequestedby","employee.commonFilter" );
	}
}	
/* function frmOtherCostEstcmbotcpRequestedby_onLoadSuccess() 	{
	fillComboBox("frmOtherCostAct","cmbotcdOthercostmstid","combo_expense.crt");	
	processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
}
 */
/*  function frmOtherCostEstcmbotcpRequestedby_onLoadSuccess() {
		fillComboBox("frmOtherCostAct","cmbotcdOthercostmstid","combo_expense.crt");

		// Only auto-default to the logged-in user on a fresh "Add New" row —
		// not on every combo reload, and not if the user already picked someone,
		// otherwise this silently overwrites their selection.
		if (jQuery('#hdnEstMode').val() != "Update" &&
			!jQuery('#cmbotcpRequestedby').combobox('getValue')) {
			processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
		}
	} */
	function frmOtherCostEstcmbotcpRequestedby_onLoadSuccess() {
		if (!estOtherChainInitialized) {
			fillComboBox("frmOtherCostAct","cmbotcdOthercostmstid","combo_expense.crt");
			processAjaxCalls("comb_setUser.crt","","setEstUserRecallSuccess","setuserRecallError");
		}
		estOtherChainInitialized = true;
	}
 function setEstUserRecallSuccess(result) {
	jQuery('#cmbotcpRequestedby').combobox('setValue',result.userId);	
}
	
/* function frmOtherCostActcmbotcdOthercostmstid_onLoadSuccess() 	{
	fillComboBox("frmOtherCostAct","cmbotcdRequestedby","employee.commonFilter" );
	
	if (jQuery('#hdnGlbType').val() == "Actual")	{
		jQuery('#submitForm').val('frmOtherCostAct');
		disableForm("frmOtherCostEst");
		jQuery('#btnOtherEstNew').attr('readonly','readonly');	
		jQuery('#btnOtherEstDelete').attr('readonly','readonly');			
			
	}
	else { 
		jQuery('#submitForm').val('frmOtherCostEst');
		disableForm("frmOtherCostAct");
		jQuery('#btnOtherActNew').attr('readonly','readonly');	
		jQuery('#btnOtherActDelete').attr('readonly','readonly');			
		
	}	
}			 */
function frmOtherCostActcmbotcdOthercostmstid_onLoadSuccess() 	{
	if (!actOtherChainInitialized) {
		fillComboBox("frmOtherCostAct","cmbotcdRequestedby","employee.commonFilter" );
	}
	
	if (jQuery('#hdnGlbType').val() == "Actual")	{
		jQuery('#submitForm').val('frmOtherCostAct');
		disableForm("frmOtherCostEst");
		jQuery('#btnOtherEstNew').attr('readonly','readonly');	
		jQuery('#btnOtherEstDelete').attr('readonly','readonly');			
			
	}
	else { 
		jQuery('#submitForm').val('frmOtherCostEst');
		disableForm("frmOtherCostAct");
		jQuery('#btnOtherActNew').attr('readonly','readonly');	
		jQuery('#btnOtherActDelete').attr('readonly','readonly');			
		
	}	
}			
/* function frmOtherCostActcmbotcdRequestedby_onLoadSuccess() 	{
	processAjaxCalls("comb_setUser.crt","","setActUserRecallSuccess","setuserRecallError");
} */
function frmOtherCostActcmbotcdRequestedby_onLoadSuccess() 	{
	if (!actOtherChainInitialized) {
		processAjaxCalls("comb_setUser.crt","","setActUserRecallSuccess","setuserRecallError");
	}
	actOtherChainInitialized = true;
}
function setActUserRecallSuccess(result) {
	jQuery('#cmbotcdRequestedby').combobox('setValue',result.userId);	
}

function fnOtherEstClear() {	
	enableFields("cmbotcpRequestedby");
	enableFields("cmbotcpOthercostmstid");			
	jQuery('#cmbotcpRequestedby').combobox('clear');
	jQuery('#cmbotcpOthercostmstid').combobox('clear');
	jQuery('#txtotcpAmount').val("");
	jQuery('#txaotcpRemarks').val("");
	jQuery('#hdnEstMode').val("");
}

function fnOtherActClear() {
	enableFields("cmbotcdRequestedby");
	enableFields("cmbotcdOthercostmstid");		
	jQuery('#cmbotcdRequestedby').combobox('clear');
	jQuery('#cmbotcdOthercostmstid').combobox('clear');
	jQuery('#dteotcdDate').datebox('clear');
	fillWithCurrentDate('dteotcdDate');
	jQuery('#txtotcdAmount').val("");
	jQuery('#txaotcdRemarks').val("");
	jQuery('#hdnActMode').val("");
}
function otherCostEstGrid_dblClick(id)
{
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Actual") return ;
	
	var rowData = jQuery("#otherCostEstGrid").jqGrid('getRowData',id);	
	if (rowData.cmbotcpRequestedby != null )
		jQuery('#hdnEstMode').val("Update");
	else
		jQuery('#hdnEstMode').val("");

	readOnlyFields("cmbotcpRequestedby");
	readOnlyFields("cmbotcpOthercostmstid");
	
	jQuery('#cmbotcpRequestedby').combobox('setValue',rowData.cmbotcpRequestedby);
	jQuery('#cmbotcpOthercostmstid').combobox('setValue',rowData.cmbotcpOthercostmstid);	
	jQuery('#txtotcpAmount').val(rowData.txtotcpAmount);
	jQuery('#txaotcpRemarks').val(rowData.txaotcpRemarks);

	fnOpenOtherNewEst();
		
}

function otherCostActGrid_dblClick(id)
{
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Estimate") 	return ;
	
	var rowData = jQuery("#otherCostActGrid").jqGrid('getRowData',id);
	if (rowData.cmbotcdRequestedby != null )
		jQuery('#hdnActMode').val("Update");
	else
		jQuery('#hdnActMode').val("");

	readOnlyFields("cmbotcdRequestedby");
	readOnlyFields("cmbotcdOthercostmstid");
	
	jQuery('#cmbotcdRequestedby').combobox('setValue',rowData.cmbotcdRequestedby);
	jQuery('#cmbotcdOthercostmstid').combobox('setValue',rowData.cmbotcdOthercostmstid);			
	jQuery('#txtotcdAmount').val(rowData.txtotcdAmount);
	jQuery('#txaotcdRemarks').val(rowData.txaotcdRemarks);	
	jQuery('#dteotcdDate').datebox('setValue',rowData.dteotcdDate);

	fnOpenOtherNewAct();		
}

function  frmOtherCostEstcmbotcpRequestedby_onSelect(record)
{
	//alert(jQuery("#cmbotcpRequestedby").combobox('getValue'));
	processAjaxCalls("empCost_getEmpCost.crt","?q=2&empId="+record.id,"empIdRecallSuccess","empIdRecallError");
}

function  frmOtherCostActcmbotcdRequestedby_onSelect(record)
{
	//alert(jQuery("#cmbotcdRequestedby").combobox('getValue'));
	processAjaxCalls("empCost_getEmpCost.crt","?q=2&empId="+record.id,"empIdRecallSuccess","empIdRecallError");
}

function empIdRecallSuccess(result)
{
	//alert(result.empDetails.empNo);
	jQuery('#hdnEmpNo').val(result.empDetails.empNo);	
	jQuery('#hdnEmpName').val(result.empDetails.empName);
}

//other code & name
function  frmOtherCostEstcmbotcpOthercostmstid_onSelect(record)
{
	//alert(jQuery("#cmbotcpOthercostmstid").combobox('getValue'));
	processAjaxCalls("otherCost_getOtherCost.crt","?q=2&otherId="+record.id,"otherIdRecallSuccess","otherIdRecallError");
}

function  frmOtherCostActcmbotcdOthercostmstid_onSelect(record)
{
	//alert(jQuery("#cmbotcdOthercostmstid").combobox('getValue'));
	processAjaxCalls("otherCost_getOtherCost.crt","?q=2&otherId="+record.id,"otherIdRecallSuccess","otherIdRecallError");
}

function otherIdRecallSuccess(result)
{
	//alert(result.otherDetails.otherName);
	jQuery('#hdnOtherCode').val(result.otherDetails.otherCode);	
	jQuery('#hdnOtherName').val(result.otherDetails.otherName);
}


function frmOtherCostEst_successsCallback() 	{	
	jQuery("#otherCostEstGrid").jqGrid().trigger("reloadGrid");
	fnOtherEstClear();		
	fnSetPageTotal();
	//jQuery( "#otherCostEstDiv" ).dialog("close");
	//mano
	 closeStaticPopUp("otherCostEstDiv", true);
	
}
function frmOtherCostAct_successsCallback(result) 	{
	jQuery("#otherCostActGrid").trigger("reloadGrid");
	fnOtherActClear();
	fnSetPageTotal();
	//jQuery( "#otherCostActDiv" ).dialog("close");
	//mano
	closeStaticPopUp("otherCostActDiv", true);
}

function frmOtherCostEst_deleteSuccessCallback() 	{
	jQuery("#otherCostEstGrid").trigger("reloadGrid");
	fnOtherEstClear();
}		 
function frmOtherCostAct_deleteSuccessCallback() 	{
	jQuery("#otherCostActGrid").trigger("reloadGrid");
	fnOtherActClear();
}

function fnSetPageTotal() {
	var formType = jQuery('#hdnGlbType').val();
	var woId = jQuery('#hdnwoId').val();
	processAjaxCalls("costInfo_getPageTotal.crt","?q=2&formName=otherCost&formType="+formType+'&woId='+woId,"getTotalSuccessRecall","getTotalRecallError");
}

function getTotalSuccessRecall(result) {
	jQuery('#txtotherTotal').val(result.pageTotal.value);
}

function otherIdRecallError(result)
{ 	alert("error");  }

function costGridLoad()
{	//alert("loaddd");
}
</script>

<div> <input type="hidden" id="hdnEstMode"></div>
<div> <input type="hidden" id="hdnActMode"></div>
<div> <input type="hidden" id="hdnEmpName"></div>
<div> <input type="hidden" id="hdnEmpNo"></div>
<div> <input type="hidden" id="hdnOtherName"></div>
<div> <input type="hidden" id="hdnOtherCode"></div>

<div  class="sub-header" align="left">Other Cost Estimate</div>

<div title="Other Cost Estimate" style="padding:10px;" id="otherCostEstDiv">
<form id="frmOtherCostEst" name="frmOtherCostEst">
	 <table>  
      <tr>
          <td class="valigncnt" valign='top' style="width:50%" >
              <div style="float:left;margin-left: px;">
                   <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Expense</label></div> 
                  <div class="easyui-paddingbfpx"> 
                      <input id="cmbotcpOthercostmstid" name="cmbotcpOthercostmstid" class="easyui-combobox"  style="width:315px;" value="" >
                      
                      <span id="err_cmbotcpOthercostmstid" class="tpm-errormsg"></span>                            
                  </div>
                  <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Requested By</label></div> 
                  <div class="easyui-paddingbfpx"> 
                      <input id="cmbotcpRequestedby" name="cmbotcpRequestedby" class="easyui-combobox"  style="width:315px;" value=""  >
                       
                       <span id="err_cmbotcpRequestedby" class="tpm-errormsg"></span>                      
                  </div>
              </div>
          </td>
          <!--top Right content-->
          <td class="valigncnt" valign='top' style="width:50%;">
              <div  style="padding-left:40px;">
                  <div  class="easyui-paddingbfpx">
                  <label class="mandatory-lbl">Cost</label>
                  </div> 
                  <div class="easyui-paddingbfpx">
                     <input id="txtotcpAmount" name="txtotcpAmount" type="text" class="easyui-text" size="10" style="margin-left: 0px; width : 86px; height : 22px;">
                     <span id="err_txtotcpAmount" class="tpm-errormsg"></span>                     
                  </div> 
                  <div  class="easyui-paddingbfpx">
                      <label>Remarks</label>                        
                  </div> 
                  <div class="easyui-paddingbfpx">
                      <textarea id="txaotcpRemarks" name="txaotcpRemarks" rows="1" cols="35" style="margin-left: px;float: left;"> </textarea>
                  </div>
              </div>	
          </td>
      </tr>
      </table>
		<div align="center" style="float: top; padding-right: 0px">
		<br><br>
			<input type="button" id="btnOtherEstInsert" name="btnOtherEstInsert" class="easyui-button" value="Insert" style="width: 80px;"> 
			<input type="button" id="btnOtherEstClear" name="btnOtherEstClear" class="easyui-button" value="Clear" style="width: 80px;">					
		</div>
  </form>
</div>
	<table width="100%">
      <tr>
      <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
     	<div style="float: left;padding-right: 40px;_padding-right: 20px;">
     	<table id="otherCostEstGrid" width="500px" style="float: left;"></table> </div>
			<div id="otherCostEstPager" style="float: center;"></div>       
      </td>
   	  <td  align="right" width="30%">
		<div>
			<input type="button" id="btnOtherEstNew" name="btnOtherEstNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>								
			<input type="button" id="btnOtherEstDelete" name="btnOtherEstDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
		</div>
     </td>
      </tr>        
    </table>


<div  class="sub-header" align="left">Other Cost Actual</div>
<div title="Other Cost Actual" style="padding:10px;" id="otherCostActDiv">
<form id="frmOtherCostAct" name="frmOtherCostAct">
	 <table>  
      <tr>
          <td class="valigncnt" valign='top' style="width:50%" >
              <div style="float:left;margin-left: px;">
                   <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Expense</label></div> 
                  <div class="easyui-paddingbfpx"> 
                      <input id="cmbotcdOthercostmstid" name="cmbotcdOthercostmstid" class="easyui-combobox"  style="width:315px;" value="" >
                      
                      <span id="err_cmbotcdOthercostmstid" class="tpm-errormsg"></span>                            
                  </div>
                  <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Requested By</label></div> 
                  <div class="easyui-paddingbfpx"> 
                      <input id="cmbotcdRequestedby" name="cmbotcdRequestedby" class="easyui-combobox"  style="width:315px;" value=""  >
                       
                       <span id="err_cmbotcdRequestedby" class="tpm-errormsg"></span>                      
                  </div>
              </div>
          </td>
          <!--top Right content-->
          <td class="valigncnt" valign='top' style="width:50%;">
              <div  style="padding-left:20px;">
                  <div  class="easyui-paddingbfpx">
                  <label class="mandatory-lbl" >Date</label>
                  <span style="margin-left: 134px;"><label class="mandatory-lbl">Cost</label></span>
                  </div> 
                  <div class="easyui-paddingbfpx"> 
                         <input id="dteotcdDate" name="dteotcdDate" class="easyui-datebox" required="true" style="width:150px;"/>
                         <input id="txtotcdAmount" name="txtotcdAmount" type="text" class="easyui-text" size="15" style="margin-left: 10px; width : 86px; height : 22px;">
						 <div>
           					<span id="err_dteotcdDate" class="tpm-errormsg" style="float:left;" ></span>
            				<span  style="padding-left:5px;float:right;"; " id="err_txtotcdAmount" class="tpm-errormsg"></span>
               			 </div>                                                                                                     
                         
                     </div>               
                  <div  class="easyui-paddingbfpx">
                      <label>Remarks</label>                        
                  </div> 
                  <div class="easyui-paddingbfpx">
                      <textarea id="txaotcdRemarks" name="txaotcdRemarks" rows="1" cols="35" style="margin-left: px;float: left;"> </textarea>
                  </div>
              </div>	
          </td>
      </tr>
      </table>
		<div align="center" style="float: top; padding-right: 0px">
			<br><br>
			<input type="button" id="btnOtherActInsert" name="btnOtherActInsert" class="easyui-button" value="Insert" style="width: 80px;"> 
			<input type="button" id="btnOtherActClear" name="btnOtherActClear" class="easyui-button" value="Clear" style="width: 80px;">					
		</div>
	</form>  
</div>
	
      <table width="100%">
      <tr>
	      <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
     	<div style="float: left;padding-right: 40px;_padding-right: 20px;">
	     	<table id="otherCostActGrid" width="500px" style="float: left;"></table> </div>
			<div id="otherCostActPager" style="float: center;"></div>
			<div style="float: right;width: 850px;text-align: right;margin-top: 5px;margin-right:50px">
	     	<label>Total Amount</label>
	     	<input id="txtotherTotal" name="txtotherTotal" type="text" class="easyui-text" value="" readonly="readonly" size="15">
	     </div>
      </td>
       	<td align="right" width="30%">
			<div>
				<input type="button" id="btnOtherActNew" name="btnOtherActNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>									
				<input type="button" id="btnOtherActDelete" name="btnOtherActDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
			</div>
		</td>                
            
      </tr>        
    </table>


 