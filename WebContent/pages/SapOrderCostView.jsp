<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script type="text/javascript">
jQuery.noConflict();
//var Input="Input";
//var Output="Output";
jQuery(document).ready(function(){
	            jQuery('#hiddenUrl').val("sapoOrderCost_input.sapordercost");
				initialiseForm('frmOrderCost');
				jQuery("#submitForm").val("frmOrderCost");
				var url = jQuery("#hiddenUrl").val();	
				
				viewGrid(url,"q=2");
				disableField("frmOrderCost", "txtSoctRequestno");
				disableField("frmOrderCost", "txtSoctRequestno1");
				disableField("frmOrderCost", "txtSapStatus");
				readOnlyFields("txtSapMessage");
				formatDateBox('dteSoctBasicStartDate','dd-MM-yyyy');
				formatDateBox('dteSoctBasicEndDate','dd-MM-yyyy');

				//numericTextBox("txtSbudOrderNumber",true);
				numericTextBox("txtSbudFiscalYear",true);
				//jQuery("#txtSbudOrderNumber").css("text-align","left");
				jQuery("#txtSbudFiscalYear").css("text-align","left");
});
function viewGrid(url, filterString){
    processGridnew("sapoOrderCost_input.sapordercost",filterString,"Outputmaingrid", "pager", "", "doubleClick");
    
}
jQuery("#btnView").click(function() {		
	//alert("Save");
	saveForm('frmOrderCost','orderCostInput_save.sapordercost?');
	
});
jQuery("#btnClear").click(function() {	
	if(jQuery('#txtSoctRequestno1').val().length>0 ||jQuery('#txtSoctRequestno').val().length>0 
			||jQuery('#txtSoctOrderNumber').val().length>0||jQuery('#txtSoctPlannerGroup').val().length>0||jQuery('#txtSoctMainWorkCenter').val().length>0 
			||jQuery('#txtSoctEquipment').val().length>0 ||jQuery('#txtSoctFunctionalLocation').val().length>0 ||isValidValue(getFieldValue("dteSoctBasicStartDate"))
			||isValidValue(getFieldValue("dteSoctBasicEndDate"))){
		clearFields();
		return true;
	}
		
	return false;
});


jQuery("#btnRefresh").click(function() {		
	//alert("Refresh");
	var requestnumber =  jQuery('#txtSoctRequestno').val();
	//alert("req no" + requestnumber);
	processAjaxCalls("getStatus.sapordercost", "requestnumber="+requestnumber, "statusVal_OnSuccess");
	viewGrid("sapoOrderCost_input.sapordercost","q=2&reqno=" +requestnumber);
	
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
	 jQuery('#txtSoctRequestno').val(reqNo);
	 jQuery('#txtSoctRequestno1').val(reqNo);
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
	
	if(!isValidFieldValue('txtSoctOrderNumber')){
		
		if(!isValidFieldValue('txtSoctPlannerGroup') && !isValidFieldValue('txtSoctMainWorkCenter') && !isValidFieldValue('txtSoctEquipment') && !isValidFieldValue('txtSoctFunctionalLocation')){
			
			alert("Enter Order Number Or Any Other Field With Order Start Date & Order End Date");
			
			return false;
			
		}else if(isValidFieldValue('txtSoctPlannerGroup') || isValidFieldValue('txtSoctMainWorkCenter') || isValidFieldValue('txtSoctEquipment') || isValidFieldValue('txtSoctFunctionalLocation')){
			
     		var startDate = getFieldValue("dteSoctBasicStartDate");
			
			var endDate = getFieldValue("dteSoctBasicEndDate");
			
			if(!isValidValue(startDate) || !isValidValue(endDate) ){
				
				alert("Select Order Start Date & Order End Date");
				
				return false;
			}			
		}		
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
function clearFields(){
	var retVal = confirm("Your Request Data will be Cleared, are you sure to continue?");			        		 		
	if( retVal == true ){
		setFieldValue("txtSoctOrderNumber","");
		setFieldValue("txtSoctPlannerGroup","");
		setFieldValue("txtSoctMainWorkCenter","");
		setFieldValue("txtSoctEquipment","");
		setFieldValue("txtSoctFunctionalLocation","");
		clearField("dteSoctBasicStartDate");
		clearField("dteSoctBasicEndDate");		
		setFieldValue("txtSoctRequestno","");
		setFieldValue("txtSoctRequestno1","");
		setFieldValue("txtSapMessage","");
		setFieldValue("txtSapStatus","");
		var requestnumber =  jQuery('#txtSoctRequestno').val();		
		viewGrid("sapBudgetUtilizationView_input.sapBudget","q=2&reqno=" +requestnumber);
		return true;
	}
	return false;
 }
</script>				   											 
<form id="frmOrderCost" name="frmOrderCost" action="" method="post">

<div style="width:50%;margin-left:10%;margin-top:4%; margin-top:-5%px; height:400px;">

<div id="tabinput" class="easyui-tabs" style="height:auto;width: 960px;margin-top:-30px;margin-left:10px; float: left;">
 <div title="Input" style="padding-left:30%;padding-top:50px;width: 800px;">
	 <div style="padding-top:4px;"> 
		<label>Request Number</label> 
	 </div>
	 <div style="padding-top:4px;">
	 <input class="easyui-text" id="txtSoctRequestno1" name="txtSoctRequestno1"  style=" width : 220px;text-align:left;"  value="" />
	 </div> 
 
	 <div style="padding-top:4px;">
	<label>Order Number</label> 
	 </div>
	 <div style="padding-top:4px;">
	 <input class="easyui-text" id="txtSoctOrderNumber" name="txtSoctOrderNumber" maxlength="12" style=" width : 220px;text-align:left;"  value="" />
	 </div>
	 
	 <div style="padding-top:4px;">
	 <label>Planner Group</label>
	 </div>
	<div> 
	<input class="easyui-text" id="txtSoctPlannerGroup" name="txtSoctPlannerGroup" maxlength="3" style=" width : 220px;"  value="" />
	</div> 

	<div style="padding-top:4px;">
	<label>Main Work Center</label>
	</div>
	<div style="padding-top:4px;">
	<input id= "txtSoctMainWorkCenter" name="txtSoctMainWorkCenter" maxlength="8" class="easyui-text" style="width:220px;text-align:left;" value=""  >
	</div>
	
	<div style="padding-top:4px;">
	<label>Equipment </label>
	</div>
	<div style="padding-top:4px;">
	<input id="txtSoctEquipment" name="txtSoctEquipment" maxlength="18" class="easyui-text" style="width:220px;text-align:left;" value=""  >
	</div>
	
	<div style="padding-top:4px;">
	<label>Functional Location </label>
	</div>
	<div style="padding-top:4px;">
	<input id="txtSoctFunctionalLocation"  name="txtSoctFunctionalLocation" maxlength="30" class="easyui-text" style="width:220px;text-align:left;" value=""  >
	</div>
	<div style="padding-top:4px;">
	<label>Order Start Date </label>
	</div>
	<div style="padding-top:4px;">
	<input id="dteSoctBasicStartDate" name="dteSoctBasicStartDate" class="easyui-datebox" style="width:220px;text-align:left;" value=""  >
	</div>
	<div style="padding-top:4px;">
	<label> Order End Date </label>
	</div>
	<div style="padding-top:4px;">
	<input id="dteSoctBasicEndDate" name="dteSoctBasicEndDate" class="easyui-datebox" style="width:220px;text-align:left;" value=""  >
	</div>
	
	<div style="padding-top:10px;padding-left:0%;padding-bottom:20%">
	<input type="button" class="easyui-button" id="btnView" value="Query" style="height:30px;"/>
	<input type="button" class="easyui-button" id="btnClear" value="Clear" style="height:30px;"/>
	</div>
	
 </div>
 
 <div title="Output">
 	<table>
 <tr>
 
 	<td colspan="8">
			 <div style="padding-left:20px;padding-top:6px;">
			 	<label>Request Number</label>
			 	<span style="padding-left:4px;">
			 		<input class="easyui-text" id="txtSoctRequestno" name="txtSoctRequestno"  style=" width : 200px;"  value="" />
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
<input type="hidden" id="hdnSoctSapStatus" name = "hdnSoctSapStatus"  value=""/>
<input type="hidden" id="hdnSoctSapMessage" name = "hdnSoctSapMessage"  value=""/>



</form>
