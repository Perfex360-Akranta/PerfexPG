<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmEmpActiveInactiveGrd');
		jQuery('#submitForm').val('frmEmpActiveInactiveGrd');
		formatDateBox('dteUsrm_validtill','dd-MMM-yyyy');
		fillComboBox("frmEmployee","cmbEmployeeInactive","employee.commonFilter" )
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
				
			    jsonArrO += empKeyid +";";
			}			
	
		}				
		saveForm('frmEmpActiveInactiveGrd','employeeActive_save.eupl?jsonArrO='+jsonArrO);
		
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
			saveForm('frmEmpActiveInactiveGrd','employeeInActive_save.eupl?empKeyid='+empKeyid+"&ValidTillDate="+ValidTillDate);

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
	
	jQuery("#btnViewEmp").click(function() {	

		var EmpId=jQuery("#cmbEmployeeInactive").combobox("getValue");
	//	alert(""+EmpId);
		 processGridnew("EmployeeInactive_input.appm","q=2&EmpId="+EmpId,"grdUser","grdUserPager","","","","");	
	 	 
		
	});
	function grdUserbtnInactive_onClick(result){
		var rowid=result.rowId;
		var btnid=result.btnId;
		
	 	 var EmployeeId=jQuery("#grdUser").jqGrid('getCell',rowid,"hdnQcpaKeyid");
	     var LWDate=getFieldValue("dteQcwfDate_grdUser_"+rowid);
		 var Remarks=getFieldValue("txtQcwfRemarks_grdUser_"+rowid);

	 	 if(EmployeeId<=0){
	 		popupCommonErrorMsg("Please Select Employee");
	 		return false;
	 	 }
	 	 else{
	 		
saveForm("frmEmpActiveInactiveGrd","UserInactive_save.appm?&LWDate="+LWDate+"&EmployeeId="+EmployeeId+"&Remarks="+Remarks);	

	 		 
	 		 }
	}
	 function frmEmpActiveInactiveGrd_successsCallback(result){
		 
		 jQuery("#grdUser").trigger("reloadGrid");
	 }
	 
	 function grdUser_selectRow(rowId){

		//	alert("checked");
		 fillWithCurrentDate("dteQcwfDate_grdUser_"+rowId);
			
				}

</script>
<form id="frmEmpActiveInactiveGrd">
	<div id="wrapperRpt" style="margin-top: 10px; margin-left: 90px">
		<div>
		 <table>
				 <tr>
				 	<td>
				<div style="margin-left:-80px; margin-top:-20px;"><label>Employee Name</label></div>
				  <div style="margin-left:-80px;">
				  <input id="cmbEmployeeInactive" name="cmbEmployeeInactive" class="easyui-combobox" style="width:220px;"  value="${requestScope.entTlTragcalmst.etcmCompletedBy}"/>
				  </div>
				</td>
				<td>
				<div style="padding-left: 10px; vertical-align: top;margin-top:-8px;"> 
				<input type="button" class="easyui-button" id="btnViewEmp" name="btnViewEmp" value="View" style="height: 20px;" />
			</div>
			</td>
				</tr>
				 
				 
				 </table>
			
		</div>
			<div style="margin-left:-80px;">
		
		</div>
	</div>
	<table id="grdUser"></table>
		<div id="grdUserPager"></div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
