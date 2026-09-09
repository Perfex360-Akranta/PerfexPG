<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script type="text/javascript">
jQuery.noConflict();
//var Input="Input";
//var Output="Output";
jQuery(document).ready(function(){
	            jQuery('#hiddenUrl').val("vendorPayment_input.sapvendorpament");
				initialiseForm('frmOrderCost');
				jQuery("#submitForm").val("frmOrderCost");
				var url = jQuery("#hiddenUrl").val();	
				
				viewGrid(url,"q=2");
				disableField("frmOrderCost", "txtSvenRequestno");
				disableField("frmOrderCost", "txtSvenRequestno1");
				disableField("frmOrderCost", "txtSapStatus");
				readOnlyFields("txtSapMessage");
				
				formatDateBox('dteSvenOrderTilldate','dd-MM-yyyy');
				formatDateBox('dteSvenOrderFromdate','dd-MM-yyyy');

				//numericTextBox("txtSbudOrderNumber",true);
				//numericTextBox("txtSbudFiscalYear",true);
				//jQuery("#txtSbudOrderNumber").css("text-align","left");
				//jQuery("#txtSbudFiscalYear").css("text-align","left");
				//clearField("dteWomsRequiredStart");
});
function viewGrid(url, filterString){
    processGridnew(url,filterString,"Outputmaingrid", "pager", "", "doubleClick");
    
}
jQuery("#btnView").click(function() {		
	//alert("Save");
	saveForm('frmOrderCost','vendorpayment_save.sapvendorpament?');

	
});
jQuery("#btnClear").click(function() {	
	if(jQuery('#txtSvenRequestno1').val().length>0 ||getFieldValue("dteSvenOrderFromdate", "frmOrderCost").length>0 
			||getFieldValue("dteSvenOrderTilldate", "frmOrderCost").length>0||jQuery('#txtSvenPlannerGroup').val().length>0||jQuery('#txtSvenOrderNumber').val().length>0 
			||jQuery('#txtSvenPrNumber').val().length>0 ||jQuery('#txtSvenWorkCenter').val().length>0 ||jQuery('#txtSvenEquipment').val().length>0
			||jQuery('#txtSvenFunctionalLocation').val().length>0 ||jQuery('#txtSvenRequisitioner').val().length>0||jQuery('#txtSvenPurchasingGroup').val().length>0
			||jQuery('#txtSvenRequestno').val().length>0||jQuery('#txtSapStatus').val().length>0||jQuery('#txtSapMessage').val().length>0
			||jQuery('#txtSvenVendor').val().length>0||jQuery('#txtSvenPoNumber').val().length>0||jQuery('#txtSvenMaterial').val().length>0)
		clearFields();
	
});


jQuery("#btnRefresh").click(function() {		
	//alert("Refresh");
	var requestnumber =  jQuery('#txtSvenRequestno').val();
	//alert("req no" + requestnumber);
	processAjaxCalls("getStatus.sapvendorpament", "requestnumber="+requestnumber, "statusVal_OnSuccess");
	viewGrid("vendorPayment_input.sapvendorpament","q=2&reqno=" +requestnumber);
	
});

function statusVal_OnSuccess(result){	
	  var status = result.status;
	  var msg = result.mess;
	 // alert("status: "+ status); 
	  if(status == "-"){
		  status = "Waiting for SAP Status";
	  }else if(status == "P"){
		  status = "Processing in SAP";
	  }else if(status == "E"){
		  status = "Error";
	  }else if(status == "S"){
		  status = "Success";
	  }
	
	 jQuery('#txtSapStatus').val(status);
	 jQuery('#txtSapMessage').val(msg);
}

function frmOrderCost_successsCallback(result)
{
	 var reqNo =result.successData.keyId;
	 var status= result.successData.status;
	// alert("status :" +status);
	 jQuery('#txtSvenRequestno').val(reqNo);
	 jQuery('#txtSvenRequestno1').val(reqNo);
	 if(status == "-"){
		  status = "Waiting for SAP Status";
	  }else if(status == "P"){
		  status = "Processing in SAP";
	  }else if(status == "E"){
		  status = "Error";
	  }else if(status == "S"){
		  status = "Success";
	  }
	 jQuery('#txtSapStatus').val(status);
	
	
}
function frmOrderCost_beforeSubmit(){
	if(!isValidFieldValue('txtSvenOrderNumber')){
		if(!isValidFieldValue('txtSvenPlannerGroup') && !isValidFieldValue('txtSvenPrNumber') && !isValidFieldValue('txtSvenWorkCenter') && !isValidFieldValue('txtSvenEquipment')
		 && !isValidFieldValue('txtSvenFunctionalLocation') && !isValidFieldValue('txtSvenRequisitioner') && !isValidFieldValue('txtSvenPurchasingGroup') && !isValidFieldValue('txtSvenVendor')
		 && !isValidFieldValue('txtSvenPoNumber') && !isValidFieldValue('txtSvenMaterial')){			
			alert("Enter Order Number Or Any Other Field With Order Start Date & Order End Date");			
			return false;			
		}else if(isValidFieldValue('txtSvenPlannerGroup') || isValidFieldValue('txtSvenPrNumber') || isValidFieldValue('txtSvenWorkCenter') || isValidFieldValue('txtSvenEquipment')
				 || isValidFieldValue('txtSvenFunctionalLocation') || isValidFieldValue('txtSvenRequisitioner') || isValidFieldValue('txtSvenPurchasingGroup') || isValidFieldValue('txtSvenVendor')
				 || isValidFieldValue('txtSvenPoNumber') || isValidFieldValue('txtSvenMaterial')){
			
     		var startDate = getFieldValue("dteSvenOrderFromdate");			
			var endDate = getFieldValue("dteSvenOrderTilldate");			
			if(!isValidValue(startDate) || !isValidValue(endDate) ){				
				alert("Select Order Start Date & Order End Date");				
				return false;
			}			
		}		
	}	
}
function clearFields(){
	var retVal = confirm("Your Request Data will be Cleared, are you sure to continue?");			        		 		
	if( retVal == true ){
			
		setFieldValue("txtSvenRequestno1","");
		clearField("dteSvenOrderFromdate");
		clearField("dteSvenOrderTilldate");
		setFieldValue("txtSvenPlannerGroup","");
		setFieldValue("txtSvenOrderNumber","");
		setFieldValue("txtSvenPrNumber","");
		setFieldValue("txtSvenWorkCenter","");		
		setFieldValue("txtSvenEquipment","");
		setFieldValue("txtSvenFunctionalLocation","");
		setFieldValue("txtSvenRequisitioner","");
		setFieldValue("txtSvenPurchasingGroup","");  		  
		setFieldValue("txtSvenRequestno","");
		setFieldValue("txtSapStatus","");
		setFieldValue("txtSapMessage","");
		setFieldValue("txtSvenVendor","");
		setFieldValue("txtSvenMaterial","");
		setFieldValue("txtSvenPoNumber","");
		setFieldValue("txtSvenPoNumber","");		
		var requestnumber =  jQuery('#txtSvenRequestno').val();		
		viewGrid("sapBudgetUtilizationView_input.sapBudget","q=2&reqno=" +requestnumber);
		
	}else{
		return false;
	}
}
function isValidValue(val){	
	if(val == 'undefined' || val=='' || val.length <= 0 || val == ' ')
		return false;
	return true;	
}
function isValidFieldValue(arg){
	var val= jQuery('#'+ arg).val();
	if(val == 'undefined' || val=='' || val.length <= 0 || val == ' ')
		return false;
	return true;
	
}
</script>				   											 
<form id="frmOrderCost" name="frmOrderCost" action="" method="post">

<div style="width:50%;margin-left:10%;margin-top:4%; margin-top:-5%px; height:400px;">

<div id="tabinput" class="easyui-tabs" style="height:auto;width: 960px;margin-top:-30px;margin-left:10px; float: left;">
 <div title="Input" style="padding-left:22%;padding-top:50px;width: 800px;">
	<table>
	
	<tr>
	
	<td valign="top" >
			<div style="padding-left: 10px;">		
					 <div style="padding-top:4px;"> 
						<label>Request Number</label> 
					 </div>
				
					 <div style="padding-top:4px;">
						 <input class="easyui-text" id="txtSvenRequestno1" name="txtSvenRequestno1"  style=" width : 220px;text-align:left;"  value="" />
					 </div> 
				 
				 	<div style="padding-top:4px;">
						<label>Order Number </label>
					</div>
				
					<div style="padding-top:4px;">
						<input id="txtSvenOrderNumber" name="txtSvenOrderNumber" maxlength="12" class="easyui-text" style="width:220px;text-align:left;" value=""  >
					</div>
					
					 <div style="padding-top:4px;">
						<label>Order From Date</label> 
					 </div>
				
					 <div style="padding-top:4px;">
						 <input class="easyui-datebox" id="dteSvenOrderFromdate" name="dteSvenOrderFromdate"  style=" width : 220px;text-align:left;"  value="" />
					 </div>
					 
					 <div style="padding-top:4px;">
						 <label>Order To Date</label>
					 </div>
					
					<div style="padding-top:4px;"> 
						<input class="easyui-datebox" id="dteSvenOrderTilldate" name="dteSvenOrderTilldate"  style=" width : 220px;"  value="" />
					</div> 
				
					<div style="padding-top:4px;">
						<label>Planner Group</label>
					</div>
				
					<div style="padding-top:4px;">
						<input id= "txtSvenPlannerGroup" name="txtSvenPlannerGroup" maxlength="3" class="easyui-text" style="width:220px;text-align:left;" value=""  >
					</div>					
					
					
					<div style="padding-top:4px;">
						<label>PR Number </label>
					</div>
				
					<div style="padding-top:4px;">
						<input id="txtSvenPrNumber" name="txtSvenPrNumber" maxlength="10" class="easyui-text" style="width:220px;text-align:left;" value=""  >
					</div>
					
					<div style="padding-top:4px;">
						<label>PO Number </label>
					</div>
				
					<div style="padding-top:4px;">
						<input id="txtSvenPoNumber" name="txtSvenPoNumber" maxlength="10" class="easyui-text" style="width:220px;text-align:left;" value=""  >
					</div>
					
				
					<div style="padding-top:10px;padding-left:0%;padding-bottom:20%">	
						<input type="button" class="easyui-button" id="btnView" value="Query" style="height:30px;"/>
						<input type="button" class="easyui-button" id="btnClear" value="Clear" style="height:30px;"/>
					</div>
					
		</div>
			 
	</td>
	
	<td valign="top">
	
		<div  style="padding-left: 15px;">
						<div style="padding-top:4px;">
							<label>Vendor  </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSvenVendor" name="txtSvenVendor" maxlength="10" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
						
							<label>Material   </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSvenMaterial" name="txtSvenMaterial" maxlength="18" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">	
							<label>Work Center    </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSvenWorkCenter" name="txtSvenWorkCenter" maxlength="8" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Equipment    </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSvenEquipment" name="txtSvenEquipment" maxlength="18" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Functional Location    </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSvenFunctionalLocation" name="txtSvenFunctionalLocation" maxlength="30" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Requisitioner     </label>
						</div>
						<div style="padding-top:4px;">
							<input id="txtSvenRequisitioner" name="txtSvenRequisitioner" maxlength="12" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Purchasing Group    </label>
						</div>
						<div style="padding-top:4px;">
							<input id="txtSvenPurchasingGroup" name="txtSvenPurchasingGroup" maxlength="3" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						
						<div style="padding-top:10px;padding-left:0%;padding-bottom:20%">	
						
						</div>
						
		</div>				
	
	<td>
	
	</tr>
	
	</table>
	
 </div>
 
 <div title="Output">
 <table>
 <tr>
 
 	<td colspan="8">
			 <div style="padding-left:20px;padding-top:6px;">
			 	<label>Request Number</label>
			 	<span style="padding-left:4px;">
			 		<input class="easyui-text" id="txtSvenRequestno" name="txtSvenRequestno"  style=" width : 200px;"  value="" />
			 	</span>
			 	<span style="padding-left:20px;padding-top:8px;">
			 		<input type="button" class="easyui-button" id="btnRefresh" value="Refresh" style="height:20px;"/>
			 	</span>
			</div>
			<div style="padding-left:80px;padding-top:6px;"> 	
			 	<label>Status</label>
			 	<span style="padding-left:4px;">
			 		<input class="easyui-text" id="txtSapStatus" name="txtSapStatus"  style=" width : 200px;"  value="" />
			 	</span>
			 </div>
	</td>
	
	<td  valign="top">
	<div style="padding-left:20px;padding-top:6px;">
			    <span style="padding-left:0px;">
			 			<label style="padding-top:0px">Message</label>
			 	</span>
			 </div>
			 
			 
	
	</td>
	
    <td colspan="5">
			 
			 <div style="padding-left:4px;padding-top:12px;">
			 	
			 	<span style="padding-left:4px;">
			 		<textarea id="txtSapMessage" name="txtSapMessage"  rows="2"  cols="17" style=" width : 440px;height: 45px;"></textarea>
			 	</span>
			 	
			 </div>
	</td>
 </tr>
 </table>
 
 <div style="padding-left:20px;">
	 <table id="Outputmaingrid">
	 </table>
	 
	  <div id="pagerid">
</div> 
</div> 
 
</div>

</div>

</div>
<input type="hidden" id="hdnSvenSapStatus" name = "hdnSvenSapStatus"  value=""/>
<input type="hidden" id="hdnSvenSapMessage" name = "hdnSvenSapMessage"  value=""/>



</form>
