<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmLocationTransfer');
		jQuery('#submitForm').val('frmLocationTransfer');
		 fillComboBox("frmNewTraCal","cmbEmployee","employee.commonFilter?");
		 fillComboBox("frmEmployee","cmbLocation","combo_location.emp" );
		formatDateBox('dteUsrm_validtill','dd-MMM-yyyy');
		fillWithCurrentDate('dteUsrm_validtill');
		var url = jQuery('#hiddenUrl').val();		
		var dataString = "?q=1";
		viewGrid(url, dataString);
	
	});
	
	function LocationGridbtnLocnSave_onClick(result){
		//alert("inside")
		var rowid=result.rowId;
		var btnid=result.btnId;
	 	var TargetLocation=jQuery("#cmbEmpLocation_LocationGrid_"+rowid).combobox("getValue");
	 	// alert(TargetLocation);
	 	 var EmployeeId=jQuery("#LocationGrid").jqGrid('getCell',rowid,"hdnEmpmKeyid");
	 	 if(TargetLocation<=0){
	 		popupCommonErrorMsg("Please Select Target Location");
	 		return false;
	 	 }
	 	 else{
	 		 var ExistLocn=jQuery("#LocationGrid").jqGrid('getCell',rowid,"EmpmLocationId");
	 		// alert("ExistLocn"+ExistLocn);
	 		 if(ExistLocn==TargetLocation){
	 			popupCommonErrorMsg("Target Location & Existing Location Could not be Same");
	 			return false
	 		 }
	 		 else{
            saveForm("frmLocationTransfer","LocationTransfer_save.appm?&TargetLocation="+TargetLocation+"&EmployeeId="+EmployeeId);	

	 		 }
	 		 }
	 	 
	}

	jQuery("#btnViewEmployee").click(function(){	
		
		var EmpId=jQuery("#cmbEmployee").combobox("getValue");
		//alert("EmpId "+EmpId);
		var Location=jQuery("#cmbLocation").combobox("getValue");
		//alert("Location "+Location);
		 processGridnew("Locationtransfer_input.appm","q=2&EmpId="+EmpId+"&Location="+Location,"LocationGrid","LocationGridPager","","","","");
		 
	});
	
	function viewGrid(url, filterString){
		if (validateFilterSelection(filterString)) {
		
			processGridnew(url, filterString, "LocationGrid", "LocationGridPager","","");
			return true;
		}
	}
	
	function validateFilterSelection(filterString) {
		return true;
	}
	
	 function frmLocationTransfer_successsCallback(result){
		 
		 jQuery("#LocationGrid").trigger("reloadGrid");
	 }
	
	 jQuery("#btnRefresh").click(function(){
		//var url = jQuery('#hiddenUrl').val();
		//processGridnew(url,"q=2","LocationGrid","Pager","", "");
	    jQuery("#cmbEmployee").combobox("clear");
	    jQuery("#cmbLocation").combobox("clear");
	    
	});

	
</script>
<form id="frmLocationTransfer">
<div id="wrapperRpt" style="margin-top: 10px; margin-left: 90px">
<div>	
		<!-- 
			<span style="vertical-align: top;"> 
					<input type="button" class="easyui-button" id="btnSave" name="btnActive" value="Active" style="height: 20px;" />
				</span>
				 -->
				 <table>
				 <tr>
				 <td>
				  <div style="margin-left:-80px; margin-top:-20px;"><label class="mandatory-lbl">Location</label></div> 
				<div class="easyui-paddingbfpx" style="margin-left:-80px;" > 
				<input id="cmbLocation" name="cmbLocation" class="easyui-combobox"   style="width:155px;" value="${requestScope.genTlEmployeemst.empmLocation}"  >
				        </div>
				        </td>
				 	<td>
				<div style="margin-left:20px; margin-top:-20px;"><label>Employee Name</label></div>
				  <div style="margin-left:0px;">
				  <input id="cmbEmployee" name="cmbEmployee" class="easyui-combobox" style="width:220px;"  value="${requestScope.entTlTragcalmst.etcmCompletedBy}"/>
				  </div>
				</td>
				<td>
				<div style="padding-left: 10px; vertical-align: top;margin-top:-8px;"> 
				<input type="button" class="easyui-button" id="btnViewEmployee" name="btnViewEmployee" value="View" style="height: 20px;" />
			</div>
			</td>
			<td>
				<div style="padding-left:20px; vertical-align: top;margin-top:-8px;"> 
				<input type="button" class="easyui-button" id="btnRefresh" name="btnRefresh" value="Refresh" style="height: 20px;" />
			</div>
			</td>
				</tr>
				 
				 
				 </table>
		
				
				
		</div>
		<div style="margin-left:-80px;">
		<table id="LocationGrid"></table>
		<div id="LocationGridPager"></div>
		</div>
	</div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
