<script>
jQuery(document).ready(function(){
	initialiseForm('frmFieldAuditSheet');
	jQuery('#submitForm').val('frmFieldAuditSheet');
	readOnlyFields("txtfasmKeyid");
	
	var url = jQuery('#hiddenUrl').val();
	var factId = jQuery("#frmFieldAuditSheet input[id='factory']").val();
	var sectionId = jQuery("#frmFieldAuditSheet input[id='section']").val();
	var cellId = jQuery("#frmFieldAuditSheet input[id='cell']").val();
	var machId = jQuery("#frmFieldAuditSheet input[id='machine']").val();
	var flid = jQuery("#frmFieldAuditSheet input[id='flid']").val();
	var locationId = jQuery("#hdnlocationId").val();
	
	numericTextBox("txtFasmNoofesp");
	numericTextBox("txtFasmViolations");
	formatDateBox('dteFasmDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteFasmDate');
	
	var filterString = "";
	var KeyId = jQuery("#hdnkeyId").val();
	
	fillComboBox('frmFieldAuditSheet','cmbFasmEvaluatedby','employee.commonFilter');
	fillComboBox("frmFieldAuditSheet","cmbFasmShift","shift.commonFilter");
	fillComboBox("frmFieldAuditSheet","cmbFasmSerprovider","ServiceProvider.fass");
	fillComboBox("frmFieldAuditSheet","cmbFasmDonejh","cellCombo.commonFilter");
	fillComboBox("frmFieldAuditSheet","cmbFasmDonedmt","sectionCombo.commonFilter");
	fillComboBox("frmFieldAuditSheet","cmbFasmTradeid","Combo_Trade.abnForm");
	
	var dataStr = "&factId=" + factId + "&sectionId=" + sectionId + "&cellId=" + cellId + "&machId=" + machId + "&flid=" + flid;
	loadFunctionalLocation("FieldAuditfunLocation","functionalLoc.fass","FieldAuditfunLocationValues","frmFieldAuditSheet", dataStr);
	
	// Initialize file manager based on mode
	var frmmode = jQuery("#mode").val();
	var keyId = jQuery("#hdnkeyId").val();
	var apMode = "create";
	
	if(frmmode == "view") {
		apMode = "view";
	}
	
	// Initialize file manager - pass keyId if available, empty string if not
	fileManagerPopUp(keyId || "", "FAS", "frmFieldAuditSheet", "btnFilManage", "newSusaFilemgr", apMode);
	
	// Get the mode from hidden field
	console.log("Form mode: " + frmmode);
	
	// Load grid with mode parameter
	var gridUrl = url;
	var gridFilter = "&q=1";
	//alert(frmmode);
	if(frmmode) {
		gridFilter += "&mode=" + frmmode;
	}
	var frmActionViewMode = frmmode;
	if(frmActionViewMode) {
		gridFilter += "&frmActionViewMode=" + frmActionViewMode;
	}
	
	processGridnew(gridUrl, gridFilter, "Fieldauditsheetgrid", "pager", "", "", "", "");	
	
	// Handle view mode - disable form and grid editing
	if(frmmode == "view"){
		disableForm("frmFieldAuditSheet");
		
		// The grid will be loaded with gridEdit:false from properties
		// Additional safety check after grid loads
		setTimeout(function(){
			jQuery("#Fieldauditsheetgrid").jqGrid('setGridParam', {
				cellEdit: false
			});
			//jQuery("#btnAddNew").hide(); // Hide Add New button in view mode
		}, 500);
	}
	
	// Handle modify mode
	if(frmmode == "modify"){
		// Grid will be loaded with gridEdit:true from properties
		jQuery("#btnAddNew").show(); // Ensure Add New button is visible
	}
});

function detectionDateEvt() {
	var detectionDate = jQuery('#dteFasmDate').datebox('getValue') + jQuery('#spnSusaTime').spinner('getValue');
	var currentDate = getServerDateTime();
}
 
function frmFieldAuditSheetcmbFasmDonejh_onSelect(record){
	var jhnval = getFieldValue('cmbFasmDonejh','frmFieldAuditSheet');
	processAjaxCalls("getjhvalue.fass?&originalval=" + jhnval, "", "flidIdSuccess");
}

function frmFieldAuditSheetcmbFasmDonedmt_onSelect(record){
	var dmt = getFieldValue('cmbFasmDonedmt','frmFieldAuditSheet');
	clearField("cmbFasmDonejh");
	processAjaxCalls("getjhvalue.fass?&originalval=" + dmt, "", "flidIdSuccess");
}

function flidIdSuccess(result){
	var section = result.successData.sect;
	var cell = result.successData.cellid;	
	var flid = result.successData.flid;
	
	if(cell.length != 0) {
		setFieldValue("cmbFasmDonedmt", section);
	}
	if(section.length != 0) {
		reloadCombo("frmFieldAuditSheet", "cmbFasmDonejh", "cellCombo.commonFilter?sectionid=" + section);
	} 
}
  
function frmFieldAuditSheet_deleteSuccessCallbacks(result){
	alert(result.successData.msg);
	jQuery("#Fieldauditsheetgrid").trigger("reloadGrid");	
}

function viewGrid(filterString){
	var frmmode = jQuery("#mode").val();
	var gridFilter = filterString;
	//alert(frmmode);
	if(frmmode) {
		gridFilter += "&mode=" + frmmode;
	}
	processGridnew("FieldAuditSheet_input.fass", gridFilter, "Fieldauditsheetgrid", "pager", "", "", "", "");
}

jQuery("#btnAddNew").click(function(){
	var frmmode = jQuery("#mode").val();
	// Only allow adding rows in modify mode
	if(frmmode == "view") {
		alert("Cannot add rows in view mode");
		return false;
	}
	
	var row = jQuery("#Fieldauditsheetgrid").jqGrid("getDataIDs");
	addRow(row);
});

function addRow(row) {
	if(row == null || row == '' || parseInt(row) <= 0) {
		var emptyItem = [{
			hdnFasdKeyid: " ",
			txtFasdEspid: " ",
			cmbFasdEspid: " ",
			txtFasdOtherEspName: " ",
			txtfasdPpeid: " ",
			cmbfasdPpeid: " ",
			cmbFasdPpecondition: " ",
			cmbFasdTools: " ",
			cmbFasdWorkpermitsafety: " ",
			cmbFasdKnowledge: " ",
			txtFasdRemarks: " "
		}];
		jQuery("#Fieldauditsheetgrid").jqGrid('addRowData', 1, emptyItem[0]);
	} else {
		for(var i = 0; i < row.length; i++)
			lastRow = row[i];
		var emptyItem = [{
			hdnFasdKeyid: " ",
			txtFasdEspid: " ",
			cmbFasdEspid: " ",
			txtFasdOtherEspName: " ",
			txtfasdPpeid: " ",
			cmbfasdPpeid: " ",
			cmbFasdPpecondition: " ",
			cmbFasdTools: " ",
			cmbFasdWorkpermitsafety: " ",
			cmbFasdKnowledge: " ",
			txtFasdRemarks: " "
		}];
		jQuery("#Fieldauditsheetgrid").jqGrid('addRowData', parseInt(lastRow) + 1, emptyItem[0]);				
	}
}

function frmFieldAuditSheet_beforeSubmit(){
	var mode = jQuery("#mode").val();
	
	// Don't allow submit in view mode
	if(mode == "view") {
		alert("Cannot save in view mode");
		return false;
	}
	
	var cellId = jQuery("#frmFieldAuditSheet input[id='cell']").val();
	if(cellId.length == 0) {
		popupCommonErrorMsg("Select JH");
		return false;
	}
	
	var gridval = getGridSelectArray('Fieldauditsheetgrid');   
	var gridData = '&FieldAuditsheetdetail=' + gridval;
	if(gridval.trim().length > 0)	
		return gridData;

	var format = /[`!@#$%^&*()_+\-=\[\]{};'':"\\|,.<>\/?~]/;
	var txtFasmNoofesp = jQuery('#txtFasmNoofesp').val();
	var txtFasmViolations = jQuery('#txtFasmViolations').val();
	var txtFasmJobdesc = jQuery('#txtFasmJobdesc').val();

	if(format.test(txtFasmJobdesc) == true || format.test(txtFasmViolations) == true || format.test(txtFasmNoofesp) == true) {
		alert("Special Characters are Not allowed!");
		return false;
	}
	
	var Evaluatedby = jQuery("#cmbFasmEvaluatedby").combobox("getValue");
	if(Evaluatedby == null || Evaluatedby.length == 0) {
		popupCommonErrorMsg("Select the Evaluated By");
		return false;
	}
	
	var Shift = jQuery("#cmbFasmShift").combobox("getValue");
	if(Shift == null || Shift.length == 0) {
		popupCommonErrorMsg("Select the Shift");
		return false;
	}
	
	var ServicePro = jQuery("#cmbFasmSerprovider").combobox("getValue");
	if(ServicePro == null || ServicePro.length == 0) {
		popupCommonErrorMsg("Select the Service Provider");
		return false;
	}

	var donejh = jQuery("#cmbFasmDonejh").combobox("getValue");
	if(donejh == null || donejh.length == 0) {
		popupCommonErrorMsg("Select the Done JH");
		return false;
	}
	
	var donedmt = jQuery("#cmbFasmDonedmt").combobox("getValue");
	if(donedmt == null || donedmt.length == 0) {
		popupCommonErrorMsg("Select the Done DMT");
		return false;			
	}
	
	var Detailofjob = jQuery("#txtFasmJobdesc").val();
	if(Detailofjob == null || Detailofjob.length == 0) {
		popupCommonErrorMsg("Enter the Details of Job");
		return false;
	}
	  
	var Violations = jQuery("#txtFasmViolations").val();
	if(Violations == null || Violations.length == 0) {
		popupCommonErrorMsg("Enter the Violations");
		return false;
	}
	
	var Trade = jQuery("#cmbFasmTradeid").combobox("getValue");
	if(Trade == null || Trade.length == 0) {
		popupCommonErrorMsg("Select the Trade");
		return false;
	}
}

function frmFieldAuditSheet_successsCallback(result){
	var keyid = result.successData.keyId;
	var frmmode = jQuery("#mode").val();
	//alert(frmmode);
	jQuery("#txtfasmKeyid").val(keyid);
	jQuery("#hdnkeyId").val(keyid);
	refreshForm();
	
	var gridFilter = "?keyid=" + keyid + "&q=1";
	if(frmmode) {
		gridFilter += "&mode=" + frmmode;
	}
	
	processGridnew("FieldAuditSheet_input.fass" + gridFilter, "&q=1", "Fieldauditsheetgrid", "pager", "", "", "", "");
	alert(result.successData.msg);
	closePopUpDialoge("DivFieldAuditSheet");
	jQuery("#FieldAuditgrid").trigger("reloadGrid"); 
}

function frmFieldAuditSheet_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth("frmFieldAuditSheet", "620px");	
}

function btnFilManage_click(){
	var documentNo = jQuery("#hdnkeyId").val();
	var txtfasmKeyid = jQuery("#txtfasmKeyid").val();
	var frmMode = jQuery('#mode').val();
	var apMode = "create";
	
	if(frmMode == "view") {
		apMode = "view";
	}
	
	// Use either hdnkeyId or txtfasmKeyid, whichever has a value
	// In create mode before first save, both might be empty - still allow file manager
	var keyToUse = documentNo || txtfasmKeyid || "";
	
	// Always open file manager - even in create mode with no document number yet
	fileManagerPopUp(keyToUse, "SUC", "", "", "", apMode);
	
	return true;
}
</script>
<form id="frmFieldAuditSheet" name="frmFieldAuditSheet">
	<div id="wrapper" > 
		<table>
			<tr>
				<td colspan="3" >
					  <div  id="frmFieldAuditSheetFuntKeyIds">
					<input type="hidden" id="factory" name="cmbsusnFactoryid" value=" "  ></input>			
					<input type="hidden" id="section" name="cmbsusnSectionid" value=" "  ></input>
					<input type="hidden" id="cell" name="cmbsusnCellid" value=" "  ></input>
					<input type="hidden" id="machine" name="cmbsusnEquipmentid" value=" "  ></input>
					<input type="hidden" id="flid" name="cmbFasmFlid" value="${requestScope.fieldAuditSheetmst.fasmFlid}"></input>	
				</div>
				
			 	<div id="FieldAuditfunLocation" style="width:123%;margin-top:-5px;"></div>
			</td>
			
			<td>	  
				  <div class="easyui-paddingbfpx" style="margin-top:-4px;margin-left:-290px;"><label class="">Field Audit Sheet No.</label></div>
			     <div style="margin-left:-20px;margin-top:-54px;" >
				  <input type="text" class="easyui-text"  id="txtfasmKeyid" name="txtfasmKeyid" maxlength="10"  style=" width :150px;height:27px; text-align:left;margin-left:-270px;margin-top:51px;" value="${requestScope.fieldAuditSheetmst.fasmKeyid}"/> 
				  </div> 
			</td>
			
			  <td>
			<div style="position: relative;margin-left:-180px;margin-top:11px;"><span id="newSusaFilemgr"
			style="position: absolute;padding-right:30px; top: -20px;">
		</span></div>  
	    </td>
	  		  
		<tr>
			<td valign="top" style=" width : 796px;">
			           <div class="easyui-paddingbfpx" style="margin-top:-1px;"><label class="mandatory-lbl">Date</label>
					  <span style="padding-left:80px;" ><label class="mandatory-lbl">Evaluated By</label></span>	  				 
			         <span style="padding-left:180px;" ><label class="mandatory-lbl" id="lblShift">Shift</label></span>	      
			         <span style="padding-left:70px;" ><label class="mandatory-lbl" id="lblNoofperson">Service Providers</label></span>   
			</div>
			<div class="easyui-paddingbfpx" >	
			<input type="text" class="easyui-text"  id="dteFasmDate" name="dteFasmDate" maxlength="10"   style=" width : 100px;/* height:25px; */ text-align:left;" value="${requestScope.fieldAuditSheetmst.fasmDate}"/>
			
					      <span style="width:250px;position:relative;padding-left: 10px"> 
					      
					     	<input type="text" class="easyui-combobox"  id="cmbFasmEvaluatedby" name="cmbFasmEvaluatedby" maxlength="10"  style=" width :250px;/* height:25px; */ text-align:left;" value="${requestScope.fieldAuditSheetmst.fasmEvaluatedby}"/>					     
					     
					      </span>     
				</div>  
		      					      
				<div class="easyui-paddingbfpx" style="margin-left:380px;margin-top:-28px;">
				<input type="text" class="easyui-combobox"  id="cmbFasmShift" name="cmbFasmShift" maxlength="10"  style=" width :90px;/* height:25px; */ text-align:left;" value="${requestScope.fieldAuditSheetmst.fasmShift}"/>					     			
				</div>  
				
				<div class="easyui-paddingbfpx" style="margin-left:490px;margin-top:-28px;">
			    <input type="text" class="easyui-combobox"  id="cmbFasmSerprovider" name="cmbFasmSerprovider" maxlength="10"  style=" width :200px;/* height:25px; */ text-align:left;" value="${requestScope.fieldAuditSheetmst.fasmSerprovider}"/>					     		
				
				
				</div>
				
				    
 		</td>
   </tr>
     <tr>
     
     
	<td>
	<div class="mandatory-lbl"><label>Details of Job</label></div> 
	<div style="margin-left:3px;">
			<span style="float:left;padding-right:0px;">					
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="500"  style="width:365px; /* height :50px; */text-transform: uppercase;" id="txtFasmJobdesc" name="txtFasmJobdesc" value="">${requestScope.fieldAuditSheetmst.fasmJobdesc}</textarea>
			</span>
			</div>
		   </td> 
		   
		<td>
	<div class="mandatory-lbl" style="margin-left:-590px;margin-top:0px;"><label>Done DMT</label></div> 
	<div class="easyui-paddingbfpx" style="margin-left:-590px;margin-top:0px;">
	<input type="text" class="easyui-combobox"  id="cmbFasmDonedmt" name="cmbFasmDonedmt" maxlength="10"  style=" width :150px;/* height:25px; */ text-align:left;" value="${requestScope.fieldAuditSheetmst.fasmDonedmt}"/>					     
	</div>
   </td>
   
   	<td>
	<div class="mandatory-lbl" style="margin-left:-440px;margin-top:0px;"><label>Done JH</label></div> 
	<div class="easyui-paddingbfpx" style="margin-left:-440px;margin-top:0px;">
	<input type="text" class="easyui-combobox"  id="cmbFasmDonejh" name="cmbFasmDonejh" maxlength="10"  style=" width :150px;/* height:25px; */ text-align:left;" value="${requestScope.fieldAuditSheetmst.fasmDonejh}"/>					     
	</div>
   </td>  	   
		   	   

		   <td>
	<div style="margin-left:-290px;margin-top:-1px;">
	<label class="mandatory-lbl">No.Of Violations</label>
	<div style="margin-left:0px;">
	<input class="easyui-text"  type="text" style="width :60px; height:32px;" id="txtFasmViolations" name="txtFasmViolations" onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" value="${requestScope.fieldAuditSheetmst.fasmViolations}"/>
	</div>
		 </div>
		 
	 <div style="margin-left:5px;margin-top:-32px;">
	<input id="btnAddNew" class="easyui-button" style="padding-top:0;" type="button" value="Add New"/>
	</div>
		   </td>
		   </tr>
   <tr>
	<td colspan="3">
	<div class="sub-header" style="text-align: left;float:left;width:1000px; width:1400px\9;height:18px\9;position:relative;margin-right:4%">
     <span style="position:absolute;">Details</span>
   </div>
   </td>
    <td>
	</td>

   </tr>  
  <tr>
						
			<tr>
		      <td>
			  <div style="margin-left:720px;margin-top:-150px;"><label>No of ESPs</label></div>
			<div style="margin-left:720px;">
			<span style="float:left;padding-right:0px;">	
			<input class="easyui-text" style="width :80px; height:25px;" id="txtFasmNoofesp" name="txtFasmNoofesp" type="text" onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" value="${requestScope.fieldAuditSheetmst.fasmNoofesp}"/>						
						
		</span>
			</div>
			</td>
		    </tr>
		    				
			<tr>
		      <td>
			  <div style="margin-left:820px;margin-top:-150px;"><label class="mandatory-lbl">Trade</label></div>
			<div style="margin-left:820px;">
			<span style="float:left;padding-right:0px;">	
	      <input type="text" class="easyui-combobox"  id="cmbFasmTradeid" name="cmbFasmTradeid" maxlength="10"  style=" width :150px;/* height:25px; */ text-align:left;" value="${requestScope.fieldAuditSheetmst.fasmTradeid}"/>					     
						
		</span>
			</div>
			</td>
		    </tr>
		    
</table>
</div>   

	<div style="margin-left:43px;margin-top:-42px;">
	<table id="Fieldauditsheetgrid" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="pager"></div>
	 </div>
	 
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.keyId}"/>
<input type="hidden" id="txtfasmKeyid" name="txtfasmKeyid" value="${requestScope.keyId}"/>
</form>