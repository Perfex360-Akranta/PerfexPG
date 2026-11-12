<script type="text/javascript">	
jQuery(document).ready(function(){	

	initialiseForm('frmIncidentEmpPopUp');	    	  
    jQuery('#submitForm').val('frmIncidentEmpPopUp'); 

	//jQuery('#frmIncidentEmpPopUp .easyui-text').css('text-transform', 'uppercase');
	//jQuery('#frmIncidentEmpPopUp textarea').css('text-transform', 'uppercase');	
	 
	 
	fillComboBox("frmIncidentEmpPopUp","cmbSineEmpmKeyid","employee.commonFilter?empType=R");
	setTimeout(function() { fillComboBox("frmIncidentEmpPopUp","cmbSineContractEmpmKeyid","employeeFilter.commonFilter?empType=C");},1250);
	setTimeout(function() { fillComboBox("frmIncidentEmpPopUp","cmbSineBodypart","BodyPartCombo.ehsaccIncRpt");},1250);
	setTimeout(function() { fillComboBox("frmIncidentEmpPopUp","cmbSineInjuryTypeid","TypeCombo.ehsaccIncRpt");},1250);
	setTimeout(function() { fillComboBox("frmIncidentEmpPopUp","cmbSineInjuryModeid","InjuryCombo.ehsaccIncRpt");},1250);
	
	
	
	
	var incidentKey=jQuery('#hdnInckeyId').val();
	if(incidentKey != null && incidentKey != ' ' && incidentKey != '')
		jQuery('#hdnsinmKeyid').val(incidentKey);
	 readOnlyFields('cmbSineContractEmpmKeyid');	
	 readOnlyFields('txtSineCompany');	
	//alert(incidentKey);
	//processGridnew("SheEmployeeAdd_input.accIncRpt","?q=2&incidentKey="+incidentKey,"empGrid","emppager2","","","","empGridComplete");
	loadActionGrid("empGrid","emppager2","colModelEmployeeDtlsPop","SheEmployeeAdd_input.ehsaccIncRpt","empdblClk","hdnInckeyId","","empGridComplete");
});
jQuery('#btnAddEmployee').click(function()
{
	var incId=jQuery('#hdnInckeyId').val();
	alert("incId"+incId);
	 processAjaxCalls("safteyincident_empcheck.ehsaccIncRpt" ,"&keyid="+incId, "frmAccidentIncidentemp_successsCallback","frmAccidentIncidentemp_errorCallback");
			
	
	saveForm('frmAccidentIncident','safteyincident_save.ehsaccIncRpt?q=2&saveMode=ADDEMP&frst=Y');
	
	
});


jQuery('#btnemployeesave').click(function()
		{
			var incId=jQuery('#hdnInckeyId').val();
			
			var rowData = jQuery("#empGrid").jqGrid('getRowData');		
			alert("incId"+rowData);
			alert("incId"+rowData.length);
			 if(incId.length!=0)
				 {  if(rowData.length!=0)
					 {
			    processAjaxCalls("safteyincident_email.ehsaccIncRpt" ,"&keyid="+incId, "frmAccidentIncident_successsCallback","frmAccidentIncident_errorCallback");
					 }
				 else{
					 alert("Save EMployee");
					 return false;
				 }
			     }
			  //saveForm('frmAccidentIncident','safteyincident_save.ehsaccIncRpt?q=2&saveMode=ADDEMP');
			
		});

jQuery('#chkContract').click(function() {
	if(jQuery('#chkContract').is(':checked') == true)
	{
		//fillComboBox("frmIncidentEmpPopUp","cmbContractEmployee","employee.commonFilter");
		//?q=2&empType=C
		enableFields('cmbSineContractEmpmKeyid');
		enableFields('txtSineCompany');
		jQuery('#lblContEmp').addClass('mandatory-lbl');
		jQuery('#lblContComp').addClass('mandatory-lbl');
		jQuery('#lblempPopEmployee').removeClass('mandatory-lbl');
		jQuery('#cmbSineEmpmKeyid').combobox('clear');
		readOnlyFields('cmbSineEmpmKeyid');		
	}
	else
	{
		enableFields('cmbSineEmpmKeyid');
		readOnlyFields('cmbSineContractEmpmKeyid');
		readOnlyFields('txtSineCompany');
		jQuery('#lblContEmp').removeClass('mandatory-lbl');
		jQuery('#lblContComp').removeClass('mandatory-lbl');
		jQuery('#lblempPopEmployee').addClass('mandatory-lbl');		
		jQuery('#cmbSineContractEmpmKeyid').combobox('clear');
		jQuery('#txtSineCompany').val('');		
	}		
});


function removeEmp(rowId){	
	
	var rowData = jQuery("#empGrid").jqGrid('getRowData',rowId);
	var keyId = rowData.txtSineKeyid;
	
	//alert("keyid"+keyId);
	var conFdelete = confirm("Do You Want To Delete");
//	alert("keyid"+conFdelete);
	if(conFdelete){
		if( keyId != null && keyId.length > 0  ){	
			processAjaxCalls("SheEmployeeAdd_Delete.ehsaccIncRpt","&incEmpId="+keyId+"&rowId="+rowId , 'empDelete_successCallBack','empDelete_errorCallBack');
			//jQuery("#batchGrid").delRowData(rowId);
		}
		 setTimeout(function() {
			  	jQuery("#empGrid").trigger("reloadGrid");
			  	},200);
	}
		 
}
function empDelete_successCallBack(result){
	if(result.tpmException){
		var rowId=result.rowId;
		alert(result.tpmException);
		//var rowData = jQuery("#batchGrid").jqGrid('getRowData',rowId);
		jQuery("#empGrid").delRowData(rowId);	
		jQuery("#empdtlsGrid").delRowData(rowId);		
	}
}
function frmIncidentEmpPopUp_successsCallback(result)
{	
	setTimeout(function(){
		jQuery("#empGrid").trigger("reloadGrid");
	},100);
	
	jQuery('#hdnsinmKeyid').val(result.IncidentKey);
	jQuery('#txtsinmKeyid').val(result.IncidentKey);
	jQuery('#cmbSineEmpmKeyid').combobox('clear');
	jQuery('#cmbSineInjuryModeid').combobox('clear');
	jQuery('#cmbSineInjuryTypeid').combobox('clear');
	jQuery('#cmbSineBodypart').combobox('clear');
	jQuery("#hdnSineKeyid").val("");
	readOnlyFields('cmbSineContractEmpmKeyid');	
	readOnlyFields('txtSineCompany');	
	jQuery('#cmbSineContractEmpmKeyid').combobox('clear');
	jQuery('#txtSineCompany').val(' ');		
	jQuery('#txtSineMode').val('');
	jQuery('#txtSineType').val('');
	jQuery('#txtSineLocation').val('');
	jQuery('input:checkbox[name=chkContract]').attr('checked',false);
	enableFields('cmbSineEmpmKeyid');
	
	reloadCombo("frmIncidentEmpPopUp","cmbSineEmpmKeyid","employee.commonFilter");
	//loadActionGrid("empGrid","pager2","colModelEmployeeDtls","SheEmployeeAdd_input.accIncRpt","","hdnsincKeyid");

	//jQuery("#empGrid").trigger("reloadGrid");
	//closePopUpDialoge("divAddEmployee");
	//jQuery('#empGrid').trigger("reloadGrid");
	//
	//alert("result.IncidentKey :"+result.IncidentKey);
	//jQuery("#empGrid").trigger("reloadGrid");
setTimeout(function() {
	processGridnew("SheEmployeeAdd_input.ehsaccIncRpt","?q=2&incidentKey="+result.IncidentKey+"&gridType=colModelEmployeeDtlsPop","empGrid","emppager2","","empdblClk","","empGridComplete");
},300);
}


/*function closePopUpDialoge()
{
	alert(12);
	closePopUpDialoge("divAddEmployee",true);
}*/

function empdblClk(id) 
{
	var rowData = jQuery("#empGrid").jqGrid('getRowData',id);	
	var selId = rowData.txtSineKeyid;
	var dataString = "keyId="+	selId;
	processAjaxCalls("SheEmployeeAdd_reCall.ehsaccIncRpt" ,dataString, "Recall_successsCallback","Recall_errorCallback");
	
}

function empGridComplete()
{
	if(screen.width <=1250)
	{
		jQuery( "#empGrid" ).setGridWidth(680);
		jQuery( "#empGrid" ).setGridHeight(250);
	}
}
function  frmIncidentEmpPopUpcmbSineEmpmKeyid_onSelect(record)
{	
	//fill(record.id);
} 
function  frmIncidentEmpPopUpcmbSineInjuryModeid_onSelect(record)
{	
	
} 
/*function  frmIncidentEmpPopUpcmbSineInjuryModeid_onSelect(record)
{	
	
} 
function fill(id){
	
	//var siemEmpmKeyid = jQuery("#cmbSineEmpmKeyid").combobox("getValue");  
	var dataString = "?q=2&siemEmpmKeyid="+id;
	processGridnew("SheEmployeeAdd_input.accIncRpt",dataString,"empGrid","pager2","","");
}*/
</script>
<form id="frmIncidentEmpPopUp" >
<div>
	<div style="padding-left: 10px;">
		<label id="lblempPopEmployee" class="mandatory-lbl">Employee</label>
		<span style="padding-left:170px;"><label id="lblContEmp" style="padding-left: 15px;">Contract Employee</label></span>
		<input id="chkContract" name="chkContract" value="Y" type="checkbox"/>
		<label id="lblContComp" class="lblempContComp">Contract Company</label>
		
	</div>
		
	<div style="padding-bottom:10px;padding-left: 10px;" >	
		<span>
			<input id="cmbSineEmpmKeyid" type="text" class="easyui-combobox"  name="cmbSineEmpmKeyid" style="width:250px;">
		</span>
		<input id="cmbSineContractEmpmKeyid" type="text" class="easyui-combobox"  name="cmbSineContractEmpmKeyid" style="width:250px;" disabled="disabled" value="">
		<span style="position: absolute;margin-left:20px"> 
			
		</span>
		<span style="margin-left:1px;">
			<input id="txtSineCompany" type="text" class="easyui-text"  name="txtSineCompany" style="width:150px;">
		</span>
		
		
		
		
	</div>
	<div  style="padding-left: 10px;">
			<span id="err_cmbSineEmpmKeyid" class="tpm-errormsg"></span>
			
						
	</div>
	<div style="padding-left: 10px;" >	
		<label style="padding-left: 0px;" class="mandatory-lbl">Injury type</label>
		<label style="padding-left: 190px;">Type of Accident</label>	
		<label style="padding-left: 169px;" class="lblempPopBodyPart mandatory-lbl">Injury Location</label>
	</div>
	<div style="padding-bottom:10px;padding-left: 10px;" >
		
<!-- 		<input id="cmbContractEmployee"  class="easyui-combobox"  name="cmbContractEmployee" style="width:235px;"> -->
		<span style="margin-left:1px;">
			<input id="cmbSineInjuryModeid" type="text" class="easyui-combobox" name="cmbSineInjuryModeid" style="width:250px;">
			
		</span>
		<span style="margin-left:1px;">
			<input id="cmbSineInjuryTypeid" type="text" class="easyui-combobox" name="cmbSineInjuryTypeid" style="width:250px;">

		</span>
		
		<span style="margin-left:1px;">
		<input id="cmbSineBodypart" type="text" class="easyui-combobox"  name="cmbSineBodypart" style="width:150px;">
		</span>
			
	</div>
	
	<table>
	<tr>
	<td>
	<span id="err_cmbSineInjuryModeid" class="tpm-errormsg "></span>
	</td>
	<td>
	<span id="err_cmbSineInjuryTypeid" class="tpm-errormsg "></span>
	</td>
	<td>
	<span id="err_cmbSineBodypart" class="tpm-errormsg " style="margin-left:404px;"></span>
	</td>
	</tr>
	</table>
	
	<div style="padding-left: 10px;" >	
		<label style="padding-left: 0px;" >Injury type(If Others)</label>
		<label style="padding-left: 150px;">Type of Accident(If Others)</label>	
		<label style="padding-left: 110px;">Injury Location(If Others)</label>
	</div>
	<div style="padding-left: 10px; padding-top:5px;" >
		
<!-- 		<input id="cmbContractEmployee"  class="easyui-combobox"  name="cmbContractEmployee" style="width:235px;"> -->
		<span style="margin-left:1px;">
			
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  rows="2" cols="" maxlength="490" style=" width : 250px;height:50px;" id="txtSineMode" name="txtSineMode" ></textarea>
		</span>
		<span style="margin-left:1px;">
			
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  rows="2" cols="" maxlength="490" style=" width : 250px;height:50px;" id="txtSineType" name="txtSineType" ></textarea>
		</span>
		<span style="margin-left:1px;">
		
		<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  rows="2" cols="" maxlength="490" style=" width : 150px;height:50px;" id="txtSineLocation" name="txtSineLocation" ></textarea>
		</span>
		<span style=" padding-left:40px;">
			<input id="btnAddEmployee" name="btnAddEmployee" type="button" class="easyui-button"  value="Save"  style="width:50px; height:22px;"/>
	       <input id="btnemployeesave" name="btnemployeesave" type="button" class="easyui-button"  value="Save&Submit"  style="width:90px;height:22px;"/>
	       	
		</span>
		
		
	</div>
	
	<div  style="padding-left: 10px;">
			<span id="err_cmbSineContractEmpmKeyid" class="tpm-errormsg"></span>
			<span id="err_txtSineCompany" class="tpm-errormsg lblempPopInjModeErrMsg"></span>			
			
	</div>
<!--	<div  class="easyui-paddingbfpx" style="padding-left: 30px;"></div>-->
<!--	<div style="padding-bottom:0px; padding-left: 30px;" >	-->
<!--		-->
<!--	</div>-->
<!--	<div  class="easyui-paddingbfpx" style="padding-left: 30px;"></div>-->
<!--	<div style="padding-bottom:0px; padding-left: 30px;">	-->
<!--		-->
<!--	</div>-->
<!--	<div class="easyui-paddingbfpx" style="">-->
<!--		-->
<!--	</div>	-->
	<div  style="float:left;padding-left: 10px;padding-top:15px;">		
		<table align="left" id="empGrid" >
			<tr><td><td/></tr>
		</table>
		<div id="emppager2"></div>
	</div>	
</div>
	<input type="hidden" id="hdnInckeyId" name="hdnInckeyId" value="${requestScope.incidentId}"/>
	<input type = "hidden" id="hdnSineKeyid" name="hdnSineKeyid" value="" />
	<input type = "hidden" id="hdnSineSincKeyid" name="hdnSineSincKeyid" value="" />
</form>