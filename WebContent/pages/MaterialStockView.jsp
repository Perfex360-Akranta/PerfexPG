<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<script type="text/javascript">
jQuery.noConflict();
//var Input="Input";
//var Output="Output";
jQuery(document).ready(function(){
	            jQuery('#hiddenUrl').val("materialStock_input.sapMaterialStock");
				initialiseForm('frmBudgetUtilization');
				jQuery("#submitForm").val("frmBudgetUtilization");
				var url = jQuery("#hiddenUrl").val();	
				
				//fillComboBox("frmBudgetUtilization","cmbOrderNo","combo_Orderno.sapBudget");
				//fillComboBox("frmBudgetUtilization","cmbWbsElement","combo_Wbselement.sapBudget");
				viewGrid(url,"q=2");
				disableField("frmBudgetUtilization", "txtSmatRequestno");
				disableField("frmBudgetUtilization", "txtSmatRequestno1");
				disableField("frmBudgetUtilization", "txtSapStatus");
			//	disableField("frmBudgetUtilization", "txtSapMessage");
				readOnlyFields("txtSapMessage");
				//numericTextBox("txtSmatMaterialNumber",true);
				//numericTextBox("txtSmatStorageLocation",true);
				//jQuery("#txtSmatMaterialNumber").css("text-align","left");
				//jQuery("#txtSmatStorageLocation").css("text-align","left");
});
function viewGrid(url, filterString){
    processGridnew("materialStock_input.sapMaterialStock",filterString,"Outputmaingrid", "pager", "", "doubleClick");
    
}
jQuery("#btnView").click(function() {		
	//alert("Save");
	saveForm('frmBudgetUtilization','materialview_save.sapMaterialStock?');
	
});
jQuery("#btnClear").click(function() {	
	if(jQuery('#txtSmatMaterialNumber').val().length>0 ||jQuery('#txtSmatPlant').val().length>0 ||jQuery('#txtSmatStorageLocation').val().length>0||jQuery('#txtSmatRequestno').val().length>0||jQuery('#txtSmatRequestno1').val().length>0 )
		clearFields();
	
});


jQuery("#btnRefresh").click(function() {		
	//alert("Refresh");
	var requestnumber =  jQuery('#txtSmatRequestno').val();
	//alert("req no" + SmatRequestno);
	processAjaxCalls("getStatus.sapMaterialStock", "requestnumber="+requestnumber, "statusVal_OnSuccess");
	viewGrid("materialStock_input.sapMaterialStock","q=2&reqno=" +requestnumber);
	
});

function statusVal_OnSuccess(result){	
	  var status = result.status;
	  var msg = result.mess;
	  //alert("status:" +status);	
	 // alert("msg: "+ msg); 
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

function frmBudgetUtilization_successsCallback(result)
{
	//alert("frmBudgetUtilization_successsCallback");
	//alert(result.successData.msg);
	 var reqNo =result.successData.keyId;
	 var status= result.successData.status;
	 jQuery('#txtSmatRequestno').val(reqNo);
	 jQuery('#txtSmatRequestno1').val(reqNo);
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
	
	//alert("Request No:" + reqNo);
	//clearFields();
}
function frmBudgetUtilization_beforeSubmit(){
	if((jQuery('#txtSmatMaterialNumber').val().length>0 )|| jQuery('#txtSmatPlant').val().length>0 ||jQuery('#txtSmatStorageLocation').val().length>0){		
		//popupCommonErrorMsg("Enter Order Number");
		//return false;
	}else{
		alert("Enter Material Number or Plant or Storage Location ");
		return false;
	}
	
}
function clearFields(){
	var retVal = confirm("Your Request Data will be Cleared, are you sure to continue?");			        		 		
	if( retVal == true ){
		setFieldValue("txtSmatMaterialNumber","");
		setFieldValue("txtSmatPlant","");
		setFieldValue("txtSmatStorageLocation","");
		setFieldValue("txtSmatRequestno","");
		setFieldValue("txtSmatRequestno1","");
		setFieldValue("txtSapMessage","");
		setFieldValue("txtSapStatus","");
		var SmatRequestno =  jQuery('#txtSmatRequestno').val();		
		viewGrid("materialStock_input.sapMaterialStock","q=2&reqno=" +SmatRequestno);
		//alert("req no" + SmatRequestno);
	}else{
		return false;
	}
	//jQuery("#Outputmaingrid").trigger("reloadGrid");
	//jQuery("#Outputmaingrid").clearGridData();
 }
</script>				   											 
<form id="frmBudgetUtilization" name="frmBudgetUtilization" action="" method="post">

<div style="width:50%;margin-left:10%;margin-top:4%; margin-top:-5%px; height:400px;">

<div id="tabinput" class="easyui-tabs" style="height:auto;width: 960px;margin-top:-30px;margin-left:10px; float: left;">
 <div title="Input" style="padding-left:30%;padding-top:50px;width: 800px;">
 <div style="padding-top:4px;">
<label>Request Number</label> 
 </div>
 <div style="padding-top:4px;">
 <input class="easyui-text" id="txtSmatRequestno1" name="txtSmatRequestno1"  style=" width : 220px;text-align:left;"  value="" />
 </div>
 
 
 
 <div style="padding-top:4px;">
<label>Material Number</label> 
 </div>
 <div style="padding-top:4px;">
 <input class="easyui-text" id="txtSmatMaterialNumber" name="txtSmatMaterialNumber" maxlength="18" style=" width : 220px;text-align:left;"  value="" />
 </div>
 <div style="padding-top:4px;">
 <label>Plant</label>
 </div>
<div> 
<input class="easyui-text" id="txtSmatPlant" name="txtSmatPlant" maxlength="4" style=" width : 220px;"  value="" />
</div> 

<div style="padding-top:4px;">
<label>Storage Location </label>
</div>
<div style="padding-top:4px;">
<input id="txtSmatStorageLocation" name="txtSmatStorageLocation" maxlength="4" class="easyui-text" style="width:220px;height:25px;text-align:left;" value=""  >
</div>


<div style="padding-top:10px;padding-left:0%;padding-bottom:20%">
<input type="button" class="easyui-button" id="btnView" value="Query" style="height:40px;"/>
<input type="button" class="easyui-button" id="btnClear" value="Clear" style="height:40px;"/>
</div>
 </div>
 
 <div title="Output">
 <table>
 <tr>
 
 	<td colspan="8">
			 <div style="padding-left:20px;padding-top:6px;">
			 	<label>Request Number</label>
			 	<span style="padding-left:4px;">
			 		<input class="easyui-text" id="txtSmatRequestno" name="txtSmatRequestno"  style=" width : 200px;"  value="" />
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
<input type="hidden" id="hdnSmatSapStatus" name = "hdnSmatSapStatus"  value=""/>
<input type="hidden" id="hdnSmatSapMessage" name = "hdnSmatSapMessage"  value=""/>



</form>
