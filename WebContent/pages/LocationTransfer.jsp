<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmLocationTransfer');
		jQuery('#submitForm').val('frmLocationTransfer');
		 fillComboBox("frmNewTraCal","cmbEmployee","employee.commonFilter?");
		formatDateBox('dteUsrm_validtill','dd-MMM-yyyy');
		fillWithCurrentDate('dteUsrm_validtill');
		var url = jQuery('#hiddenUrl').val();		
		var dataString = "?q=1";
		viewGrid(url, dataString);
	
	});

	jQuery("#btnSave").click(function() {		
		var loginId="";
		var jsonArrO="";
		var usrGrdIds = jQuery("#grdUser").jqGrid('getDataIDs');	
		var cm = jQuery("#grdUser").jqGrid("getGridParam", "colModel");
		for(var i=1;i<=usrGrdIds.length;i++)	
		{	
			rowid=usrGrdIds[i-1];
			if(jQuery('#chkBox_'+rowid).is(':checked')){
				
				var empKeyid = jQuery("#grdUser").jqGrid('getCell',rowid,"USERKEYID");
			   // alert(empKeyid);
			    jsonArrO += empKeyid +";";
			}			
	
		}				
		saveForm('frmLocationTransfer','employeeActive_save.eupl?jsonArrO='+jsonArrO);
		
	});
	
	jQuery("#btnInactive").click(function() {		
		var loginId="";
		var jsonArrO="";
		var usrGrdIds = jQuery("#grdUser").jqGrid('getDataIDs');	
		var cm = jQuery("#grdUser").jqGrid("getGridParam", "colModel");
		var ValidTillDate=jQuery("#dteUsrm_validtill").datebox("getValue");
      //  alert(ValidTillDate);
		for(var i=1;i<=usrGrdIds.length;i++){	
			rowid=usrGrdIds[i-1];
			if(jQuery('#chkBox_'+rowid).is(':checked')){
			 if(ValidTillDate.length==0){
				alert("Select the ValidTill Date");
				return false;
			}
			}
			else{
				var empKeyid = jQuery("#grdUser").jqGrid('getCell',rowid,"USERKEYID");
			//	jsonArrO += empKeyid +";"; 
			//	alert(jsonArrO);
			}
			saveForm('frmLocationTransfer','employeeInActive_save.eupl?empKeyid='+empKeyid+"&ValidTillDate="+ValidTillDate);

		}				
		
	});
	
	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {
		
			processGridnew(url, filterString, "grdUser", "grdUserPager","","");
			return true;
		}
	}
	
	function validateFilterSelection(filterString) {
		return true;
	}
	
	function dteUsrm_validtill_onSelect(date){			
		/*var currentDate = getServerDateTime();
        alert(date+""+currentDate);
		if( date <= currentDate)
		{					
			jQuery('#dteUsrm_validtill').datebox('clear');
			showValidationErrorMsg('dteUsrm_validtill','Should Not Exceed Current Date');	
		}
		else
			clearValidationErrorMsg('dteUsrm_validtill');*/
			
			var currentDate = getServerDateTime();
			var ValidTillDate = jQuery('#dteUsrm_validtill').datebox("getValue");
			if(convertStringToDate(ValidTillDate) < currentDate)
			{
				jQuery('#dteUsrm_validtill').datebox('clear');
				showValidationErrorMsg('dteUsrm_validtill','Should Not Exceed Current Date');	
				return false;
			}
			else{
				clearValidationErrorMsg('dteUsrm_validtill');
			}
			
	}
	
	
	
	function checkBoxUser(id, options, rowObject){
		
		return '<input type="checkbox" id="chkBox_'+options.rowId+'" name="chkBox_'+options.rowId+'" onclick="editUser('+options.rowId+')"  style="width:50px;  height:23px;"   class="easyui-button" value=""/>';
	}
	
	function editUser(id){
	
	}
	function frmLocationTransfer_beforeSubmit(){
			
	}
	
	function frmLocationTransfer_successsCallback(){
		
		jQuery('#grdUser').trigger("reloadGrid");
		
		
	}
	
	
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
				<div style="margin-left:-80px; margin-top:-20px;"><label>Employee Name</label></div>
				  <div style="margin-left:-80px;">
				  <input id="cmbEmployee" name="cmbEmployee" class="easyui-combobox" style="width:220px;"  value="${requestScope.entTlTragcalmst.etcmCompletedBy}"/>
				  </div>
				</td>
				<td>
				<div style="padding-left: 10px; vertical-align: top;margin-top:-8px;"> 
				<input type="button" class="easyui-button" id="btnInactive" name="btnInactive" value="View" style="height: 20px;" />
			</div>
			</td>
				</tr>
				 
				 
				 </table>
		
				
				
		</div>
		<div style="margin-left:-80px;">
		<table id="grdUser"></table>
		<div id="grdUserPager"></div>
		</div>
	</div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
