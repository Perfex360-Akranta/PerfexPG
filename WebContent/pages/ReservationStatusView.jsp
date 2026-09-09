<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script type="text/javascript">
jQuery.noConflict();
//var Input="Input";
//var Output="Output";
jQuery(document).ready(function(){
	            jQuery('#hiddenUrl').val("sapReservation_input.sapReservation");
				initialiseForm('frmReservation');
				jQuery("#submitForm").val("frmReservation");
				var url = jQuery("#hiddenUrl").val();	
				
				//fillComboBox("frmReservation","cmbOrderNo","combo_Orderno.sapBudget");
				//fillComboBox("frmReservation","cmbWbsElement","combo_Wbselement.sapBudget");
				viewGrid(url,"q=2");
				disableField("frmReservation", "txtSresRequestno");
				disableField("frmReservation", "txtSresRequestno1");
				disableField("frmReservation", "txtSapStatus");
				//disableField("frmReservation", "txtSapMessage");
				readOnlyFields("txtSapMessage");
				
				formatDateBox('dteSresOrderFromdate','dd-MM-yyyy');
				formatDateBox('dteSresOrderTilldate','dd-MM-yyyy');
				formatDateBox('dteSresRequirementDate','dd-MM-yyyy');


				numericTextBox("txtSresReservation",true);
				//numericTextBox("txtSbudFiscalYear",true);
				//jQuery("#txtSbudOrderNumber").css("text-align","left");
				jQuery("#txtSresReservation").css("text-align","left");
});
function viewGrid(url, filterString){
    processGridnew("sapReservation_input.sapReservation",filterString,"Outputmaingrid", "pager", "", "doubleClick");
    
}

jQuery("#btnView").click(function() {		
	//alert("Save");
	saveForm('frmReservation','sapReservation_save.sapReservation?');   
	
});
jQuery("#btnClear").click(function() {	   
	//alert("1");
	if(jQuery('#txtSresOrderNumber').val().length>0 ||isValidValue(getFieldValue("dteSresOrderFromdate")) || isValidValue(getFieldValue("dteSresOrderTilldate")) ||
			jQuery('#txtSresPlannerGroup').val().length>0||jQuery('#txtSresReservation').val().length>0||isValidValue(getFieldValue("dteSresRequirementDate")) ||jQuery('#txtSresWorkCenter').val().length>0||
			jQuery('#txtSresEquipment').val().length>0 ||jQuery('#txtSresFunctionalLocation').val().length>0||jQuery('#txtSresMaterial').val().length>0||jQuery('#txtSresRequestno').val().length>0||jQuery('#txtSresRequestno1').val().length>0 )
		clearFields();
	
});


jQuery("#btnRefresh").click(function() {		
	//alert("Refresh");
	var requestnumber =  jQuery('#txtSresRequestno').val();
	//alert("req no" + requestnumber);
	processAjaxCalls("getStatus.sapReservation", "requestnumber="+requestnumber, "statusVal_OnSuccess");
	viewGrid("sapReservation_input.sapReservation","q=2&reqno=" +requestnumber);
	
});

function statusVal_OnSuccess(result){	
	 var status = result.status;
	  var msg = result.mess;
	
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

function frmReservation_successsCallback(result)
{
	 var reqNo =result.successData.keyId;
	 var status= result.successData.status;
	 jQuery('#txtSresRequestno').val(reqNo);
	 jQuery('#txtSresRequestno1').val(reqNo);
	 if(status == "-"){
		  status = "Waiting for SAP Status";
	  }else if(status == "P"){
		  status = "Processing in Sap";
	  }else if(status == "E"){
		  status = "Error";
	  }else if(status == "S"){
		  status = "Success";
	  }
	 jQuery('#txtSapStatus').val(status);	
}  

function frmReservation_beforeSubmit(){
	///var rdate =getFieldValue("dteSresRequirementDate");
	//alert("1: " +rdate);
	if(!isValidFieldValue('txtSresOrderNumber')){
		
		if(!isValidFieldValue('txtSresPlannerGroup') && !isValidFieldValue('txtSresReservation') && !isValidFieldValue('txtSresWorkCenter') && !isValidFieldValue('txtSresEquipment')
		 && !isValidFieldValue('txtSresFunctionalLocation') && !isValidFieldValue('txtSresMaterial') && !(isValidValue(getFieldValue("dteSresRequirementDate")))){			
			alert("Enter Order Number Or Any Other Field With Order Start Date & Order End Date");			
			return false;			
		}else if(isValidFieldValue('txtSresPlannerGroup') || isValidFieldValue('txtSresReservation') || isValidFieldValue('txtSresWorkCenter') || isValidFieldValue('txtSresEquipment')
				 || isValidFieldValue('txtSresFunctionalLocation') || isValidFieldValue('txtSresMaterial') || !(isValidValue(getFieldValue("dteSresRequirementDate"))) ){
			
     		var startDate = getFieldValue("dteSresOrderFromdate");			
			var endDate = getFieldValue("dteSresOrderTilldate");			
			if(!isValidValue(startDate) || !isValidValue(endDate) ){				
				alert("Select Order Start Date & Order End Date");				
				return false;
			}			
		}		
	}	
	return true;
}

function clearFields(){
	var retVal = confirm("Your Request Data will be Clear, are you sure to continue?");			        		 		
	if( retVal == true ){
		setFieldValue("txtSresOrderNumber","");
		clearField("dteSresOrderFromdate");
		clearField("dteSresOrderTilldate");		
		setFieldValue("txtSresPlannerGroup","");
		setFieldValue("txtSresReservation","");
		clearField("dteSresRequirementDate");
		setFieldValue("txtSresWorkCenter","");
		setFieldValue("txtSresEquipment","");
		setFieldValue("txtSresFunctionalLocation","");
		setFieldValue("txtSresMaterial","");
		setFieldValue("txtSresRequestno","");
		setFieldValue("txtSresRequestno1","");
		setFieldValue("txtSresFunctionalLocation","");
		var requestnumber =  jQuery('#txtSresRequestno').val();		
		viewGrid("sapReservation_input.sapReservation","q=2&reqno=" +requestnumber);
		return true;
	}else{
		return false;
	}
	
 }
function isValidValue(val){	
	//alert("Val::" +val);
	if(val == 'undefined' || val=='' || val.length <= 0 || val == ' ' || val == null)
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
<form id="frmReservation" name="frmReservation" action="" method="post">
	
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
							 <input class="easyui-text" id="txtSresRequestno1" name="txtSresRequestno1"  style=" width : 220px;text-align:left;"  value="" />
						 </div> 
						 
						 <div style="padding-top:4px;">
							<label>Order Number </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSresOrderNumber" name="txtSresOrderNumber" maxlength="12" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
					 
						 <div style="padding-top:4px;">
							<label>Order From Date</label> 
						 </div>
					
						 <div style="padding-top:4px;">
							 <input class="easyui-datebox" id="dteSresOrderFromdate" name="dteSresOrderFromdate"  style=" width : 220px;text-align:left;"  value="" />
						 </div>
						 
						 <div style="padding-top:4px;">
							 <label>Order To Date</label>
						 </div>
						
						<div style="padding-top:4px;"> 
							<input class="easyui-datebox" id="dteSresOrderTilldate" name="dteSresOrderTilldate"  style=" width : 220px;"  value="" />
						</div> 
					
						<div style="padding-top:4px;">
							<label>Planner Group</label>
						</div>
					
						<div style="padding-top:4px;">
							<input id= "txtSresPlannerGroup" name="txtSresPlannerGroup" maxlength="3" class="easyui-text" style="width:220px;text-align:left;" value=""  >
						</div>
						
						
						<div style="padding-top:4px;">
							<label>Reservation </label>
						</div>
					
						<div style="padding-top:4px;">
							<input id="txtSresReservation" name="txtSresReservation" maxlength="10" class="easyui-text" style="width:220px;text-align:left;" value=""  >
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
								<label>Requirement Date  </label>
							</div>
						
							<div style="padding-top:4px;">
								<input id="dteSresRequirementDate" name="dteSresRequirementDate"  class="easyui-datebox" style="width:220px;text-align:left;" value=""  >
							</div>
							
							<div style="padding-top:4px;">
							
								<label>Work Center   </label>
							</div>
						
							<div style="padding-top:4px;">
								<input id="txtSresWorkCenter" name="txtSresWorkCenter" maxlength="8" class="easyui-text" style="width:220px;text-align:left;" value=""  >
							</div>
								
							<div style="padding-top:4px;">
								<label>Equipment    </label>
							</div>
						
							<div style="padding-top:4px;">
								<input id="txtSresEquipment" name="txtSresEquipment" maxlength="18" class="easyui-text" style="width:220px;text-align:left;" value=""  >
							</div>
							
							<div style="padding-top:4px;">
								<label>Functional Location    </label>
							</div>
						
							<div style="padding-top:4px;">
								<input id="txtSresFunctionalLocation" name="txtSresFunctionalLocation" maxlength="30" class="easyui-text" style="width:220px;text-align:left;" value=""  >
							</div>
							
							<div style="padding-top:4px;">
								<label>Material     </label>
							</div>
							<div style="padding-top:4px;">
								<input id="txtSresMaterial" name="txtSresMaterial" maxlength="18" class="easyui-text" style="width:220px;text-align:left;" value=""  >
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
				 		<input class="easyui-text" id="txtSresRequestno" name="txtSresRequestno"  style=" width : 200px;"  value="" />
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
	<input type="hidden" id="hdnSresSapStatus" name = "hdnSresSapStatus"  value=""/>
	<input type="hidden" id="hdnSresSapMessage" name = "hdnSresSapMessage"  value=""/>
	


</form>
