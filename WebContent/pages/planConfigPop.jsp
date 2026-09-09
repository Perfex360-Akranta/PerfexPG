<style>
.cmbcolor{
background-color:#FEE7CB;
}
</style>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	

	initialiseForm("frmPlanConfigurationpop");
	 
	//var rowData = jQuery("#planconfig").jqGrid('getRowData',rowId);
	//var selId = rowData.keyid; 
	//jQuery('#hdnKey').val(selId);
	/*disable cmbBox*/
	jQuery('.searchLayerFuncLocn').html(' ');
	jQuery('#popHead').css('display','none');
	jQuery('#content').css('padding','0px');
	jQuery('#footer').css('height','0px');
	jQuery('#subFormPopUpId').css('padding','5px');
	
	readOnlyFields('cmbPplcMonthly');
	readOnlyFields('cmbPplcQuarterly');
	readOnlyFields('cmbPplcHalfyearly');
	//alert(jQuery('#hdnFilterVals').val());
	/*fill yearly cmb box*/
	fillComboBox("frmPlanConfiguration","cmbPplcYearly","fillYear.plnconfig");
	numericTextBox('txtPplcWeekno');
	var weekNo = jQuery('#txtPplcWeekno').val();
	if(weekNo!= 0 && weekNo != '' && weekNo != ' '&& weekNo != null ){}
	else
	jQuery('#txtPplcWeekno').val('4');

	
	var yearlyVal = jQuery("#frmPlanConfigurationpop input[id='cmbPplcYearly']").combobox("getValue");
	if(yearlyVal.trim().length>0 || yearlyVal.trim().length != null){
		fillYearHierarchy("fillcmbMnthQrtHy.plnconfig",yearlyVal,"cmbPplcMonthly","cmbPplcQuarterly","cmbPplcHalfyearly");
		loadYears();
	}
	});

jQuery('#pcSaveBtn').click(function saveToGrid(){
	  //alert('save'+ rowId+"--" +selId);
	  var url = jQuery('#hiddenUrl').val();
	  var machineChkd = jQuery('#hdnMch').val();//alert('selid  ' +selId );
	  var datastr = jQuery('#hdnFilterVals').val();
	  var start = datastr.indexOf('all=');
	  var end =  datastr.indexOf('&fctid');
	  var chkForApplyToAll =datastr.substring(start,end);
	  //alert(chkForApplyToAll);
	  jQuery('#submitForm').val('frmPlanConfiguration'); // set the id of form to submit
	  jQuery('#submitForm').val('frmPlanConfigurationpop');
	  var cmbPplcYearly  = getFieldValue('cmbPplcYearly','frmPlanConfigurationpop');
	
	  if(machineChkd != " " && machineChkd != "" && machineChkd != null)
	  datastr += '&machineChkd='+machineChkd;
	 // alert(datastr);
	  if(chkForApplyToAll != "all=N" ){
		  if(cmbPplcYearly != " " && cmbPplcYearly != "" && cmbPplcYearly != null)
			  saveForm("frmPlanConfigurationpop","planConfig_save.plnconfig?"+datastr);
			 else{
				 alert("Select Year");
			   showValidationErrorMsg("cmbPplcYearly","Select Year");   
			 }

		  }
	  else{
		  /*If cmbPplcYearly is selected*/
		 if(cmbPplcYearly != " " && cmbPplcYearly != "" && cmbPplcYearly != null){
		  alert('normal save'); 
		  saveForm("frmPlanConfigurationpop","planConfig_save.plnconfig?"+datastr);
		 }
		 else{
			 alert("Select Year");
		   showValidationErrorMsg("cmbPplcYearly","Select Year");   
		 }
	  }  
});
function chktxtVal(){
	var txtval = jQuery('#txtPplcWeekno').val();
	if(parseInt(txtval) ==0  ){
		alert('Minimum Week No is 1');
		jQuery('#txtPplcWeekno').val('');
		setTimeout(function() {setFocusOnField('txtPplcWeekno');},550);
		return false;
	}
	if( parseInt(txtval) >4 ){
		alert('Maximum Week No is 4');
		jQuery('#txtPplcWeekno').val('');
		setTimeout(function() {setFocusOnField('txtPplcWeekno');},550);
		return false;
	}
}
function frmPlanConfigurationpop_exceptionCallback(result){
	 var filterStr = jQuery('#hdnFilterVals').val();
	 var svePmPlan=confirm(result.tpmException);
	 processGridnew("planConfig_input.plnconfig","?q=2filterStr="+filterStr,"planconfig","planconfig_pager","","","","planconfig_loadComplete");
	 jQuery('#chkApplyToall').attr('checked',false);
	 jQuery('#chkApplyToallAssm').attr('checked',false);
		 jQuery( "#subformPopUpId" ).dialog('close');
		//  var x=window.confirm("Are you sure you are ok?")
		  if (svePmPlan){
			  var recvdtpmException = result.tpmException;
			 
			  if(recvdtpmException.substring(0,4)=== "Plan"){
				 var delExistRec = confirm("Current Annual Plan will be removed for Equipment , Do you want to continue?");
			 	 if(delExistRec){
				 	
				 	processAjaxCalls("delPmExist_input.plnconfig?",'&del=del','delPmPlan_OnSuccess','delPmPlan_OnError');
				 }
			  }
		   }
		  else{jQuery('#planconfig').trigger("reloadGrid");}
	
}
function delPmPlan_OnSuccess(result){
	//alert(Object.keys(result));
	
	if(result.tpmException !='' && result.tpmException !=' ' && result.tpmException != null){
		alert(result.tpmException);
		jQuery('#planconfig').trigger("reloadGrid");
	}
	
	alert(result.successData.msg);
	var woOpen = result.woException;
	
	if(woOpen != "undefined" && woOpen != undefined && woOpen != " " && woOpen != "") {
		var opnWoResp = confirm(result.woException);
		if(opnWoResp){
			 var filterStr = jQuery('#hdnFilterVals').val();
			 //alert(filterStr.substring(53));
			 subFormPop("wrkOdrResp_input.plnconfig", "500","200","360", "698","WorkOrderResponsibility",filterStr.substring(53));
			}
	}
	//jQuery( "#subformPopUpId" ).dialog('close');
	jQuery('#planconfig').trigger("reloadGrid");

}
function delPmPlan_OnError(result){
	alert('error');
}

function frmPlanConfigurationpopcmbPplcYearly_onLoadSuccess(){


	
	
}
function frmPlanConfigurationcmbPplcYearly_onSelect(record)
{
	
	//alert(record.id);//cmbPplcYearly2<div id="sdf"</div>
	fillYearHierarchy("fillcmbMnthQrtHy.plnconfig",record.id,"cmbPplcMonthly","cmbPplcQuarterly","cmbPplcHalfyearly");
	//processAjaxCalls("fillcmbMnthQrtHy.plnconfig",'selYear='+record.id,'yearLoad_OnSuccess','yearLoad_OnError');
	loadYears();
}

function loadYears(){
  
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly2","fill2Year.plnconfig");
	//reloadCombo("frmPmStandardform","cmbPmsdAssemblyid","assembly.commonFilter?q=2&machineId="+ record.id);
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly3","fill3Year.plnconfig");
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly4","fill4Year.plnconfig");
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly5","fill5Year.plnconfig");
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly6","fill6Year.plnconfig");
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly7","fill7Year.plnconfig");
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly8","fill8Year.plnconfig");
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly9","fill9Year.plnconfig");
	reloadCombo("frmPlanConfigurationpop","cmbPplcYearly10","fill10Year.plnconfig"); 
}
function frmPlanConfigurationpop_successsCallback(result){

	var woOpen = result.woException;
	if(woOpen != "undefined" && woOpen != undefined && woOpen != " " && woOpen != "") {
		var opnWoResp = confirm(result.woException);
		if(opnWoResp){
			 var filterStr = jQuery('#hdnFilterVals').val();
			 subFormPop("wrkOdrResp_input.plnconfig", "300","100","360", "698","WorkOrderResponsibility",filterStr);
			}
		else{
			//jQuery( "#subformPopUpId" ).dialog('close');
			closePopUpDialoge('divplanconfig');
			}
	}
	//jQuery( "#subformPopUpId" ).dialog('close');
	
	jQuery('#planconfig').trigger("reloadGrid");
}
</script>

<form id="frmPlanConfigurationpop" name="frmPlanConfigurationpop">

	<table border='1' width='100%'>
	<tr>
		<td style="padding:0% 4% 0% 2%;" colspan='3'>
				<div  class="easyui-paddingbfpx">
	    				<label>Week No</label>                       
	    			</div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="txtPplcWeekno" onblur="chktxtVal();" name="txtPplcWeekno" class="easyui-text" maxlength ="1" style="width:70px;" value="${requestScope.plmTlPlanconfiguration.pplcWeekno }"/ >                       
			     </div>
			</td>
			<td>
				<span id="err_cmbPplcYearly" class="tpm-errormsg" style="margin-left:5px;"></span>
			</td>
	</tr>
	<tr>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
				<div  class="easyui-paddingbfpx">
     				<label> Monthly</label>                       
     			</div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbPplcMonthly" name="cmbPplcMonthly" class="easyui-combobox" style="width:100px;" value="${requestScope.plmTlPlanconfiguration.pplcMonthly }"/ >                       
			     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
			<div  class="easyui-paddingbfpx">
		     <label> Quartely</label>                       
		      </div> 
		     <div class="easyui-paddingbfpx"> 
		         <input  id="cmbPplcQuarterly" name="cmbPplcQuarterly" class="easyui-combobox" style="width:100px;" value="${requestScope.plmTlPlanconfiguration.pplcQuarterly }"/ >                       
		     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
			<div  class="easyui-paddingbfpx">
		     <label> Half Yearly</label>                       
		      </div> 
		     <div class="easyui-paddingbfpx"> 
		         <input  id="cmbPplcHalfyearly" name="cmbPplcHalfyearly" class="easyui-combobox" style="width:100px;" value="${requestScope.plmTlPlanconfiguration.pplcHalfyearly }"/ >                       
		     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
				<div  class="easyui-paddingbfpx mandatory-lbl">
			     <label> Yearly</label>                       
			      </div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbPplcYearly" name="cmbPplcYearly" class="easyui-combobox " style="width:100px;" value="${requestScope.plmTlPlanconfiguration.pplcYearly }"/ >                       
			     </div>
			</td>
		</tr>
		
		<tr>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
				<div  class="easyui-paddingbfpx">
     				<label> 2 Years</label>                       
     			</div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbPplcYearly2" name="cmbPplcYearly2" class="easyui-combobox cmbcolor" style="width:100px;" value="${requestScope.plmTlPlanconfiguration.pplcYearly2 }"/ >                       
			     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
			<div  class="easyui-paddingbfpx">
		     <label> 3 Years</label>                       
		      </div> 
		     <div class="easyui-paddingbfpx"> 
		         <input  id="cmbPplcYearly3" name="cmbPplcYearly3" class="easyui-combobox cmbcolor" style="width:100px;" value="${requestScope.plmTlPlanconfiguration.pplcYearly3 }"/ >                       
		     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
			<div  class="easyui-paddingbfpx">
		     <label>4 Years</label>                       
		      </div> 
		     <div class="easyui-paddingbfpx"> 
		         <input  id="cmbPplcYearly4" name="cmbPplcYearly4" class="easyui-combobox cmbcolor" style="width:100px;"  value="${requestScope.plmTlPlanconfiguration.pplcYearly4 }"/ >                       
		     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
				<div  class="easyui-paddingbfpx">
			     <label>5 Years</label>                       
			      </div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbPplcYearly5" name="cmbPplcYearly5" class="easyui-combobox cmbcolor" style="width:100px;"  value="${requestScope.plmTlPlanconfiguration.pplcYearly5 }"/ >                       
			     </div>
			</td>
			
		</tr>
		<tr>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
				<div  class="easyui-paddingbfpx">
     				<label> 6 Years</label>                       
     			</div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbPplcYearly6" name="cmbPplcYearly6" class="easyui-combobox cmbcolor" style="width:100px;"  value="${requestScope.plmTlPlanconfiguration.pplcYearly6 }"/ >                       
			     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
			<div  class="easyui-paddingbfpx">
		     <label> 7 Years</label>                       
		      </div> 
		     <div class="easyui-paddingbfpx"> 
		         <input  id="cmbPplcYearly7" name="cmbPplcYearly7" class="easyui-combobox cmbcolor" style="width:100px;"  value="${requestScope.plmTlPlanconfiguration.pplcYearly7 }"/ >                       
		     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
			<div  class="easyui-paddingbfpx">
		     <label>8 Years</label>                       
		      </div> 
		     <div class="easyui-paddingbfpx"> 
		         <input  id="cmbPplcYearly8" name="cmbPplcYearly8" class="easyui-combobox cmbcolor" style="width:100px;"  value="${requestScope.plmTlPlanconfiguration.pplcYearly8 }"/ >                       
		     </div>
			</td>
			<td style="padding:0% 0% 0% 2%; width : 153px;">
				<div  class="easyui-paddingbfpx">
			     <label>9 Years</label>                       
			      </div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbPplcYearly9" name="cmbPplcYearly9" class="easyui-combobox cmbcolor" style="width:100px;"  value="${requestScope.plmTlPlanconfiguration.pplcYearly9 }"/ >                       
			     </div>
			</td>
			
		</tr>
		<tr>
		<td style="padding:0% 0% 0% 2%; width : 153px;">
				<div  class="easyui-paddingbfpx">
			     <label>10 Years</label>                       
			      </div> 
			     <div class="easyui-paddingbfpx"> 
			         <input  id="cmbPplcYearly10" name="cmbPplcYearly10" class="easyui-combobox cmbcolor" style="width:100px;"  value="${requestScope.plmTlPlanconfiguration.pplcYearly10 }"/ >                       
			     </div>
			</td>
			
		</tr>
		<tr>
			<td colspan="4">
			 <div class="easyui-paddingbfpx" style="margin-left:45%;"> 
				<input type="button" id="pcSaveBtn"  style="height:21px" class="easyui-button cmbcolor" onclick="" value="Save"/>
			</div>
			</td>
		</tr>
	</table></form>
	<input type="hidden" id="hdnMch" name="hdnMch" value="${ requestScope.rowmachId}"/>
<!--	<input type="hidden" id="asmChkd" name="asmChkd" value="${ requestScope.assmId}"/>-->
	<input type="hidden" id="hdnFilterVals" name="hdnFilterVals" value="${ requestScope.datastr}"/>