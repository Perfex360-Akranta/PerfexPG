<script type="text/javascript">
jQuery(document).ready(function(){

	jQuery('.static-popup-clone').remove();
	STATIC_POPUP_IDS = {};
	initialiseForm('frmServiceCostEst');
	initialiseForm('frmServiceCostAct');

	numericTextBox('txtsvcpBillvalue');
	numericTextBox('txtsvcaBillvalue');

	fillComboBox("frmServiceCostEst","cmbsvcpServiceid","combo_service.crt");	
	//fillComboBox("frmServiceCostAct","cmbsvcaServiceid","combo_service.crt");
	
	formatDateBox('dtesvcaBilldate','dd-MMM-yyyy');	
	fillWithCurrentDate('dtesvcaBilldate');

	var woid = jQuery('#hdnwoId').val();
	//q=2&formName=empCost&woId="+ woid	
	processGridnew('serviceCostEstimation_view.crt',"?q=2&formName=serviceCost&woId="+ woid,"serviceCostEstGrid","serviceCostEstPager","","serviceCostEstGrid_dblClick","","costGridLoad","");						
	processGridnew('serviceCostActual_view.crt',"?q=2&formName=serviceCost&woId="+ woid,"serviceCostActGrid","serviceCostActPager","","serviceCostActGrid_dblClick","","costGridLoad","");


	jQuery("#serviceCostEstDiv").hide();
	jQuery("#serviceCostActDiv").hide();
	
	fnSetPageTotal();
	
//Spares Cost Estimate

	jQuery("#btnServiceEstNew").click(function(){
		fnServiceEstClear();			
		fnOpenServiceNewEst();			
	});

	jQuery('#btnServiceEstInsert').click(function(){
		 //alert("Insert clicked");	
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		
		var url = "spareCostManPower_save.crt?q=2&Type=Estimate&FormName=ServiceCost" ;//defined in classic.jsp
			
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

	jQuery('#btnServiceEstClear').click(function(){
		//alert("Clear clicked");
		fnServiceEstClear();
	});
	
	jQuery('#btnServiceEstDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#serviceCostEstGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#serviceCostEstGrid").jqGrid('getRowData',rowId);
		var SerivceId = rowData.cmbsvcpServiceid;				
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp

		var woId=jQuery('#hdnwoId').val();		
		url+= '?q=2&FormName=ServiceCost&Type=Estimate&womid='+woId+'&serviceid='+SerivceId;		

		if(formId.length > 0 )
			deleteRecord(formId,url);
				
	});	

//Spares Cost Actual

	jQuery("#btnServiceActNew").click(function(){
		fnServiceActClear();			
		fnOpenServiceNewAct();			
	});

	jQuery('#btnServiceActInsert').click(function(){
		 //alert("Insert clicked");	
		
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var url = "spareCostManPower_save.crt?q=2&Type=Actual&FormName=ServiceCost" ;//defined in classic.jsp
			
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

	jQuery('#btnServiceActClear').click(function(){
		//alert("Clear clicked");
		fnServiceActClear();
	});
	
	jQuery('#btnServiceActDelete').click(function(){
		//alert("Delete clicked");
		var formId = jQuery('#submitForm').val(); //defined in classic.jsp
		var rowId = jQuery("#serviceCostActGrid").jqGrid('getGridParam', 'selrow');

		if (rowId == null || rowId < 0) {	return; }
		
		var rowData = jQuery("#serviceCostActGrid").jqGrid('getRowData',rowId);
		var SerivceId = rowData.cmbsvcaServiceid;				
		var url = "empCostManPower_delete.crt" ;//defined in classic.jsp

		var woId=jQuery('#hdnwoId').val();		
		url+= '?q=2&FormName=ServiceCost&Type=Actual&womid='+woId+'&serviceid='+SerivceId;
				
		if(formId.length > 0 )
			deleteRecord(formId,url);
				
	});		
});

/* function fnOpenServiceNewEst() {
	jQuery('#submitForm').val("frmServiceCostEst");
	jQuery( "#serviceCostEstDiv" ).show();
	jQuery( "#serviceCostEstDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 320,
		width: 820,
		zIndex: 99999
	});				
}
function fnOpenServiceNewAct() {
	jQuery('#submitForm').val("frmServiceCostAct");
	jQuery( "#serviceCostActDiv" ).show();
	jQuery( "#serviceCostActDiv" ).dialog({
		autoOpen: false,
		modal: true,
		height: 350,
		width: 820,
		zIndex: 99999
		
	});				
}
 */
 
//Registry of popup divIds that hold static, server-rendered content
//(form + grid markup) rather than AJAX-loaded content. These must
//never be destroyed with .remove() — only hidden — or they can never
//be reopened for the rest of the page session.
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
/*  function fnOpenServiceNewEst() {
	    jQuery('#submitForm').val("frmServiceCostEst");

	    // LoadPopUp only detaches/repositions the div when a url is passed.
	    // This content is static (no AJAX load), so replicate that step ourselves
	    // once, so it doesn't stay trapped inside any ancestor's stacking context.
	    if (!jQuery("#serviceCostEstDiv").parent().is("#mainlayout")) {
	        jQuery("#serviceCostEstDiv")
	            .detach()                 // keeps bound events/data, unlike remove()
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    LoadPopUp(
	        "serviceCostEstDiv",
	        "",
	        true,
	        "820px",
	        "320px",
	        "10%",
	        "15%",
	        "",
	        "Service Cost Estimate"
	    );
	}

	function fnOpenServiceNewAct() {
	    jQuery('#submitForm').val("frmServiceCostAct");

	    if (!jQuery("#serviceCostActDiv").parent().is("#mainlayout")) {
	        jQuery("#serviceCostActDiv")
	            .detach()
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    LoadPopUp(
	        "serviceCostActDiv",
	        "",
	        true,
	        "820px",
	        "350px",
	        "10%",
	        "15%",
	        "",
	        "Service Cost Actual"
	    );
	} */
	function fnOpenServiceNewEst() {
	    jQuery('#submitForm').val("frmServiceCostEst");

	    if (!jQuery("#serviceCostEstDiv").parent().is("#mainlayout")) {
	        jQuery("#serviceCostEstDiv")
	            .detach()
	            .addClass('static-popup-clone')
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    jQuery("#serviceCostEstDiv > div.sub-header").remove();
	    jQuery("#serviceCostEstDiv > a.close").remove(); 

	    LoadPopUp(
	        "serviceCostEstDiv", "", true, "820px", "320px", "10%", "15%", "",
	        "Service Cost Estimate"
	    );

	    registerStaticPopUp("serviceCostEstDiv");
	}

	function fnOpenServiceNewAct() {
	    jQuery('#submitForm').val("frmServiceCostAct");

	    if (!jQuery("#serviceCostActDiv").parent().is("#mainlayout")) {
	        jQuery("#serviceCostActDiv")
	            .detach()
	            .addClass('static-popup-clone')
	            .insertAfter("#mainlayout")
	            .css('margin-top', '1%')
	            .css('border', '6px solid #444444')
	            .css('border-radius', '8px');
	    }

	    jQuery("#serviceCostActDiv > div.sub-header").remove();
	    jQuery("#serviceCostActDiv > a.close").remove(); 


	    LoadPopUp(
	        "serviceCostActDiv", "", true, "820px", "350px", "10%", "15%", "",
	        "Service Cost Actual"
	    );

	    registerStaticPopUp("serviceCostActDiv");
	}
function frmServiceCostEstcmbsvcpServiceid_onLoadSuccess() 	{
	fillComboBox("frmServiceCostAct","cmbsvcaServiceid","combo_service.crt");

	if (jQuery('#hdnGlbType').val() == "Actual")	{
		jQuery('#submitForm').val('frmServiceCostAct');
		disableForm("frmServiceCostEst");
		jQuery('#btnServiceEstNew').attr('readonly','readonly');	
		jQuery('#btnServiceEstDelete').attr('readonly','readonly');			
	}
	else { 
		jQuery('#submitForm').val('frmServiceCostEst');
		disableForm("frmServiceCostAct");
		jQuery('#btnServiceActNew').attr('readonly','readonly');	
		jQuery('#btnServiceActDelete').attr('readonly','readonly');
	}
		
}

function fnServiceEstClear() {
	enableFields("cmbsvcpServiceid");		
	
	jQuery('#cmbsvcpServiceid').combobox('clear');		
	jQuery('#txasvcpJobdescription').val("");
	jQuery('#txasvcpRemarks').val("");		 
	jQuery('#txtsvcpBillno').val("");
	jQuery('#txtsvcpBillvalue').val("");
	jQuery('#hdnEstMode').val("");
}

function fnServiceActClear() {
	enableFields("cmbsvcaServiceid");			
	jQuery('#cmbsvcaServiceid').combobox('clear');		
	jQuery('#txasvcaJobdescription').val("");
	jQuery('#txasvcaRemarks').val("");		 
	jQuery('#txtsvcaBillno').val("");
	jQuery('#dtesvcaBilldate').datebox('clear');
	fillWithCurrentDate('dtesvcaBilldate');
	jQuery('#txtsvcaBillvalue').val("");
	jQuery('#hdnActMode').val("");		
}
function serviceCostEstGrid_dblClick(id)
{
	//alert(id);
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Actual") return ;
	
	var rowData = jQuery("#serviceCostEstGrid").jqGrid('getRowData',id);
	//alert(rowData.cmbsvcpServiceid);
	if (rowData.cmbsvcpServiceid != null )
		jQuery('#hdnEstMode').val("Update");
	else
		jQuery('#hdnEstMode').val("");

	readOnlyFields("cmbsvcpServiceid");
	
	jQuery('#cmbsvcpServiceid').combobox('setValue',rowData.cmbsvcpServiceid);
	jQuery('#txasvcpJobdescription').val(rowData.txasvcpJobdescription);
	jQuery('#txasvcpRemarks').val(rowData.txasvcpRemarks);
	jQuery('#txtsvcpBillno').val(rowData.txtsvcpBillno);
	jQuery('#txtsvcpBillvalue').val(rowData.txtsvcpBillvalue);

	fnOpenServiceNewEst();	
}


function serviceCostActGrid_dblClick(id)
{
	//alert(id);
	var formType = jQuery('#hdnGlbType').val();
	if (formType =="Estimate") 	return ;
	
	var rowData = jQuery("#serviceCostActGrid").jqGrid('getRowData',id);
	//alert(rowData.cmbsvcaServiceid);
	if (rowData.cmbsvcaServiceid != null )
		jQuery('#hdnActMode').val("Update");
	else
		jQuery('#hdnActMode').val("");

	readOnlyFields("cmbsvcaServiceid");
	
	jQuery('#cmbsvcaServiceid').combobox('setValue',rowData.cmbsvcaServiceid);
	jQuery('#txasvcaJobdescription').val(rowData.txasvcaJobdescription);
	jQuery('#txasvcaRemarks').val(rowData.txasvcaRemarks);
	jQuery('#txtsvcaBillno').val(rowData.txtsvcaBillno);
	jQuery('#txtsvcaBillvalue').val(rowData.txtsvcaBillvalue);	
	jQuery('#dtesvcaBilldate').datebox('setValue',rowData.dtesvcaBilldate);

	fnOpenServiceNewAct();
}

function  frmServiceCostEstcmbsvcpServiceid_onSelect(record)
{
	//alert(jQuery("#cmbsvcpServiceid").combobox('getValue'));
	processAjaxCalls("serviceCost_getServiceCost.crt","?q=2&serviceId="+record.id,"serviceIdRecallSuccess","serviceIdRecallError");
}

function  frmServiceCostActcmbsvcaServiceid_onSelect(record)
{
	//alert(jQuery("#cmbsvcaServiceid").combobox('getValue'));
	processAjaxCalls("serviceCost_getServiceCost.crt","?q=2&serviceId="+record.id,"serviceIdRecallSuccess","serviceIdRecallError");
}

function serviceIdRecallSuccess(result)
{
	jQuery('#hdnServiceCode').val(result.serviceDetails.serviceCode);	
	jQuery('#hdnServiceName').val(result.serviceDetails.serviceName);	
}


function frmServiceCostEst_successsCallback() 	{	
	jQuery("#serviceCostEstGrid").jqGrid().trigger("reloadGrid");
	fnServiceEstClear();
	fnSetPageTotal();
	//jQuery( "#serviceCostEstDiv" ).dialog("close");
	//mano
	 closeStaticPopUp("serviceCostEstDiv", true);
	
}
function frmServiceCostAct_successsCallback(result) 	{
	jQuery("#serviceCostActGrid").trigger("reloadGrid");
	fnServiceActClear();
	fnSetPageTotal();
	//jQuery( "#serviceCostActDiv" ).dialog("close");
	//mano
	closeStaticPopUp("serviceCostActDiv", true);
}

function frmServiceCostEst_deleteSuccessCallback() 	{
	jQuery("#serviceCostEstGrid").trigger("reloadGrid");
	fnServiceEstClear();
}		 
function frmServiceCostAct_deleteSuccessCallback() 	{
	jQuery("#serviceCostActGrid").trigger("reloadGrid");
	fnServiceActClear();
}

function fnSetPageTotal() {	
	var formType = jQuery('#hdnGlbType').val();
	var woId = jQuery('#hdnwoId').val();
	processAjaxCalls("costInfo_getPageTotal.crt","?q=2&formName=serviceCost&formType="+formType+'&woId='+woId,"getTotalSuccessRecall","getTotalRecallError");	
}
function getTotalSuccessRecall(result) {
	jQuery('#txtserviceTotal').val(result.pageTotal.value);
}

function serviceIdRecallError(result)
{ 	alert("error"); }

function costGridLoad()
{//	alert("loadeddddd");
}
</script>

<div> <input type="hidden" id="hdnEstMode"></div>
<div> <input type="hidden" id="hdnActMode"></div>
<div> <input type="hidden" id="hdnServiceName"></div>
<div> <input type="hidden" id="hdnServiceCode"></div>

<div  class="sub-header" align="left" >Service Cost Estimate</div>

 <div title="Service Cost Estimate" style="padding:10px;" id="serviceCostEstDiv">
 	<form id="frmServiceCostEst" name="frmServiceCostEst">
       <table>        
          <tr>
              <td class="valigncnt" style="width:50%" >
                  <div style="float:left;margin-left: px;">
                       <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Service</label></div> 
                      <div class="easyui-paddingbfpx"> 
                          <input id="cmbsvcpServiceid" name="cmbsvcpServiceid" class="easyui-combobox"  style="width:345px;" value=""  >
                          
                          <span id="err_cmbsvcpServiceid" class="tpm-errormsg"></span>                            
                      </div>
                      <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Job Description</label></div> 
                      <div class="easyui-paddingbfpx"> 
                          <textarea rows="1" cols="40" id="txasvcpJobdescription" name="txasvcpJobdescription" ></textarea>
                          <span id="err_txasvcpJobdescription" class="tpm-errormsg"></span>                           
                      </div>
                  </div>
              </td>
              <!--top Right content-->
              <td valign='top' style="width:50%;">
                  <div  style="padding-left:20px;">
                      <div  class="easyui-paddingbfpx">                                                 
                      <label class="mandatory-lbl">Bill No</label>                        
                      <span  style="margin-left: 90px;margin-left: 95px\9;"><label class="mandatory-lbl">Bill Value</label></span>                        
                      </div> 
                      <div class="easyui-paddingbfpx">                             
                          <input id="txtsvcpBillno" name="txtsvcpBillno" type="text" class="easyui-text" size="15" style="margin-left: 0px;">                                                                                                                                 
                          <input id="txtsvcpBillvalue" name="txtsvcpBillvalue" type="text" class="easyui-text" size="15" style="margin-left: 45px;">
         					
            				<span  style="padding-left:5px;float:right;" id="err_txtsvcpBillvalue" class="tpm-errormsg"></span>
                          
                      </div>
                       <div  class="easyui-paddingbfpx">
                      <label>Remarks</label>                        
                      </div> 
                      <div class="easyui-paddingbfpx">
                          <textarea id="txasvcpRemarks" name="txasvcpRemarks" rows="1" cols="40" style="float: left;"></textarea>                                                                                                                                   
                      </div>
                  </div>	
              </td>
          </tr>
         </table>
		<div align="center" style="float: top; padding-right: 0px">
			<br><br>
			<input type="button" id="btnServiceEstInsert" name="btnServiceEstInsert" class="easyui-button" value="Insert" style="width: 80px;">
			<input type="button" id="btnServiceEstClear" name="btnServiceEstClear" class="easyui-button" value="Clear" style="width: 80px;">					
		</div>
	</form>       
   </div>
   
   <table width="100%">   
    <tr>
       <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
      		<div style="float: left;padding-right: 40px;_padding-right: 20px;">
      		<table id="serviceCostEstGrid" width="500px" style="float: left;"></table> </div>
			<div id="serviceCostEstPager" style="float: center;"></div>        
       </td> 
         <td class="valign"  align="right" width="30%">
		<div>
			<input type="button" id="btnServiceEstNew" name="btnServiceEstNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>							
			<input type="button" id="btnServiceEstDelete" name="btnServiceEstDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
		</div>
		</td>            
       </tr>        
       </table>
 
<div  class="sub-header" align="left">Service Cost Actual</div>

<div title="Service Cost Actual" style="padding:10px;" id="serviceCostActDiv">
	<form id="frmServiceCostAct" name="frmServiceCostAct">
       <table>        
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:40%" >
                    <div style="float:left;margin-left: px;">
                         <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Service</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbsvcaServiceid" name="cmbsvcaServiceid" class="easyui-combobox"  style="width:345px;" value=""  >
                            
                            <span id="err_cmbsvcaServiceid" class="tpm-errormsg"></span>                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Job Description</label></div> 
                        <div class="easyui-paddingbfpx"> 
                           <textarea rows="3" cols="40" id="txasvcaJobdescription" name="txasvcaJobdescription" ></textarea>
                           <span id="err_txasvcaJobdescription" class="tpm-errormsg"></span>                                                      
                        </div>                                                                                        
                    </div>
                </td>
                <!--top Right content-->
                <td valign='top' style="width:40%;">
                    <div  style="padding-left:20px;">
                        <div  class="easyui-paddingbfpx">
                        <label class="mandatory-lbl">Bill Date</label>
                        	<span  style="margin-left: 85px;"><label class="mandatory-lbl">Bill No</label></span>
                        	<span  style="margin-left: 58px;margin-left: 67px\9;"><label class="mandatory-lbl">Bill Value</label></span>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input id="dtesvcaBilldate" name="dtesvcaBilldate"class="easyui-datebox" required="true" style="width:120px;"/>                                                        
							<input id="txtsvcaBillno" name="txtsvcaBillno" type="text" class="easyui-text" size="15" style="margin-left: 10px;">
                            <input id="txtsvcaBillvalue" name="txtsvcaBillvalue" type="text" class="easyui-text" size="15" style="margin-left: 15px;"><div  style=" height : 20px;width:355px;">           					
           					<span id="err_dtesvcaBilldate" class="tpm-errormsg" style="float:left;" ></span>
            				<span  style="padding-left:5px;float:right;" id="err_txtsvcaBillvalue" class="tpm-errormsg"></span>
               			 	</div>  
                        </div>                            
                         <div  class="easyui-paddingbfpx">
                        <label>Remarks</label>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <textarea id="txasvcaRemarks" name="txasvcaRemarks" rows="3" cols="40" style="float: left;"></textarea>                                                                                                                                   
                        </div>
                    </div>	
                </td>
            </tr>
            </table>
			<div align="center" style="float: top; padding-right: 0px">
			<br><br>
				<input type="button" id="btnServiceActInsert" name="btnServiceActInsert" class="easyui-button" value="Insert" style="width: 80px;"> 
				<input type="button" id="btnServiceActClear" name="btnServiceActClear" class="easyui-button" value="Clear" style="width: 80px;">					
			</div>            
		</form>  
		</div>
		
		<table width="100%">            
        <tr>
        <td class="valigncnt" colspan="2" valign="middle" align="center" width="70%">
       		<div style="float: left;padding-right: 40px;_padding-right: 20px;">
       			<table id="serviceCostActGrid" width="500px" style="float: left;"></table> </div>
				<div id="serviceCostActPager" style="float: center;"></div>        
        		<div style="float: right;width: 850px;text-align: right;margin-top: 5px;margin-right:50px">
        				<label>Total Amount</label>
        		<input  id="txtserviceTotal" name="txtserviceTotal" type="text" class="easyui-text" value="" readonly="readonly" size="15">
        	</div>
        </td>   
        <td align="right" width="30%">    
			<div>
				<input type="button" id="btnServiceActNew" name="btnServiceActNew" class="easyui-button" value="Add New" style="width: 80px;"> <br><br>
				<input type="button" id="btnServiceActDelete" name="btnServiceActDelete" class="easyui-button" value="Delete" style="width: 80px;"> <br><br>
			</div>
		</td>                    
        </tr>        
        </table>    

 