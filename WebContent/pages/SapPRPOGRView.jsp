<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script type="text/javascript">
jQuery.noConflict();
//var Input="Input";
//var Output="Output";
jQuery(document).ready(function(){
	            jQuery('#hiddenUrl').val("prpogr_input.sapprpogr");
				initialiseForm('frmOrderCost');
				jQuery("#submitForm").val("frmOrderCost");
				var url = jQuery("#hiddenUrl").val();	
				
				viewGrid(url,"q=2");
				disableField("frmOrderCost", "txtSprsRequestno");
				disableField("frmOrderCost", "txtSprsRequestno1");
				disableField("frmOrderCost", "txtSapStatus");
				readOnlyFields("txtSapMessage");
				
				formatDateBox('dteSprsOrderTilldate','dd-MM-yyyy');
				formatDateBox('dteSprsOrderFromdate','dd-MM-yyyy');

				//numericTextBox("txtSbudOrderNumber",true);
				//numericTextBox("txtSbudFiscalYear",true);
				//jQuery("#txtSbudOrderNumber").css("text-align","left");
				//jQuery("#txtSbudFiscalYear").css("text-align","left");
});
function viewGrid(url, filterString){
    processGridnew(url,filterString,"Outputmaingrid", "pager", "", "doubleClick");
    
}
jQuery("#btnView").click(function() {		
	//alert("Save");
	saveForm('frmOrderCost','prpogrInput_save.sapprpogr?');

	
});
jQuery("#btnClear").click(function() {	
	if(jQuery('#txtSprsRequestno1').val().length>0 ||getFieldValue("dteSprsOrderFromdate", "frmOrderCost").length>0 
			||getFieldValue("dteSprsOrderTilldate", "frmOrderCost").length>0||jQuery('#txtSprsPlannerGroup').val().length>0||jQuery('#txtSprdOrderNumber').val().length>0 
			||jQuery('#txtSprdPrNumber').val().length>0 ||jQuery('#txtSprdWorkCenter').val().length>0 ||jQuery('#txtSprdEquipment').val().length>0
			||jQuery('#txtSprdFunctionalLocation').val().length>0 ||jQuery('#txtSprdRequisitioner').val().length>0||jQuery('#txtSprdPurchasingGroup').val().length>0
			||jQuery('#txtSprsRequestno').val().length>0||jQuery('#txtSapStatus').val().length>0||jQuery('#txtSapMessage').val().length>0
			||jQuery('#txtSprdVendor').val().length>0||jQuery('#txtSprdPoNumber').val().length>0||jQuery('#txtSprdMaterial').val().length>0)
		clearFields();
	
});


jQuery("#btnRefresh").click(function() {		
	//alert("Refresh");
	var requestnumber =  jQuery('#txtSprsRequestno').val();
	//alert("req no" + requestnumber);
	processAjaxCalls("getStatus.sapprpogr", "requestnumber="+requestnumber, "statusVal_OnSuccess");
	viewGrid("prpogr_input.sapprpogr","q=2&reqno=" +requestnumber);
	
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
	 jQuery('#txtSprsRequestno').val(reqNo);
	 jQuery('#txtSprsRequestno1').val(reqNo);
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
	//alert("1");
	if(!isValidFieldValue('txtSprdOrderNumber')){
		if(!isValidFieldValue('txtSprsPlannerGroup') && !isValidFieldValue('txtSprdPrNumber') && !isValidFieldValue('txtSprdWorkCenter') && !isValidFieldValue('txtSprdEquipment')
		 && !isValidFieldValue('txtSprdFunctionalLocation') && !isValidFieldValue('txtSprdRequisitioner') && !isValidFieldValue('txtSprdPurchasingGroup') && !isValidFieldValue('txtSprdPoNumber')
		 && !isValidFieldValue('txtSprdVendor') && !isValidFieldValue('txtSprdMaterial')){			
			alert("Enter Order Number Or Any Other Field With Order Start Date & Order End Date");			
			return false;			
		}else if(isValidFieldValue('txtSprsPlannerGroup') || isValidFieldValue('txtSprdPrNumber') || isValidFieldValue('txtSprdWorkCenter') || isValidFieldValue('txtSprdEquipment')
				 || isValidFieldValue('txtSprdFunctionalLocation') || isValidFieldValue('txtSprdRequisitioner') || isValidFieldValue('txtSprdPurchasingGroup') || isValidFieldValue('txtSprdPoNumber')
				 || isValidFieldValue('txtSprdVendor') || isValidFieldValue('txtSprdMaterial')){
			
     		var startDate = getFieldValue("dteSprsOrderFromdate");			
			var endDate = getFieldValue("dteSprsOrderTilldate");			
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
		setFieldValue("txtSprsRequestno1","");
		clearField("dteSprsOrderFromdate");
		clearField("dteSprsOrderTilldate");
		setFieldValue("txtSprsPlannerGroup","");
		setFieldValue("txtSprdOrderNumber","");
		setFieldValue("txtSprdPrNumber","");
		setFieldValue("txtSprdWorkCenter","");		
		setFieldValue("txtSprdEquipment","");
		setFieldValue("txtSprdFunctionalLocation","");
		setFieldValue("txtSprdRequisitioner","");
		setFieldValue("txtSprdPurchasingGroup","");  		  
		setFieldValue("txtSprsRequestno","");
		setFieldValue("txtSapStatus","");
		setFieldValue("txtSapMessage","");
		setFieldValue("txtSprdVendor","");
		setFieldValue("txtSprdMaterial","");
		setFieldValue("txtSprdPoNumber","");
		var requestnumber =  jQuery('#txtSprsRequestno').val();		
		viewGrid("sapBudgetUtilizationView_input.sapBudget","q=2&reqno=" +requestnumber);
		return true;
	}
	return false;	
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
						 <input class="easyui-text" id="txtSprsRequestno1" name="txtSprsRequestno1"  style=" width : 220px;text-align:left;"  value="" />
					 </div> 
					 
					 <div style="padding-top:4px;">
						<label>Order Number </label>
					</div>
				
					<div style="padding-top:4px;">
						<input id="txtSprdOrderNumber" name="txtSprdOrderNumber" maxlength="12"  class="easyui-text" style="width:220px;text-align:left;" value=""  >
					</div>
				
				 
					 <div style="padding-top:4px;">
						<label>Order From Date</label> 
					 </div>
				
					 <div style="padding-top:4px;">
						 <input class="easyui-datebox" id="dteSprsOrderFromdate" name="dteSprsOrderFromdate"  style=" width : 220px;text-align:left;"  value="" />
					 </div>
					 
					 <div style="padding-top:4px;">
						 <label>Order To Date</label>
					 </div>
					
					<div style="padding-top:4px;"> 
						<input class="easyui-datebox" id="dteSprsOrderTilldate" name="dteSprsOrderTilldate"  style=" width : 220px;"  value="" />
					</div> 
				
					<div style="padding-top:4px;">
						<label>Planner Group</label>
					</div>
				
					<div style="padding-top:4px;">
						<input id= "txtSprsPlannerGroup" name="txtSprsPlannerGroup"  maxlength="3" class="easyui-text" style="width:220px;text-align:left;" value=""  >
					</div>
					
						
					<div style="padding-top:4px;">
						<label>PR Number </label>
					</div>
				
					<div style="padding-top:4px;">
						<input id="txtSprdPrNumber" name="txtSprdPrNumber"  maxlength="10" class="easyui-text" style="width:220px;text-align:left;" value=""  >
					</div>
					
					<div style="padding-top:4px;">
						<label>PO Number </label>
					</div>
				
					<div style="padding-top:4px;">
						<input id="txtSprdPoNumber" name="txtSprdPoNumber" maxlength="10" class="easyui-text" style="width:220px;text-align:left;" value=""  >
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
							<input id="txtSprdVendor" name="txtSprdVendor" maxlength="10" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
						
							<label>Material   </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSprdMaterial" name="txtSprdMaterial" maxlength="18" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">	
							<label>Work Center    </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSprdWorkCenter" name="txtSprdWorkCenter" maxlength="8" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Equipment    </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSprdEquipment" name="txtSprdEquipment" maxlength="18" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Functional Location    </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSprdFunctionalLocation" maxlength="30" name="txtSprdFunctionalLocation" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Requisitioner     </label>
						</div>
						<div style="padding-top:4px;">
							<input id="txtSprdRequisitioner" name="txtSprdRequisitioner" maxlength="12" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						<div style="padding-top:4px;">
							<label>Purchasing Group    </label>
						</div>
						<div style="padding-top:4px;">
							<input id="txtSprdPurchasingGroup" name="txtSprdPurchasingGroup" maxlength="3" class="easyui-text" style="width:220px;text-align:left;" value=""  >
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
			 		<input class="easyui-text" id="txtSprsRequestno" name="txtSprsRequestno"  style=" width : 200px;"  value="" />
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
