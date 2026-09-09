<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmEMPActive');
		jQuery('#submitForm').val('frmEMPActive');	
		var url = jQuery('#hiddenUrl').val();		
		var dataString = "?q=1";
		viewGrid(url, dataString);
	
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
	
	function checkBoxUser(id, options, rowObject){
		
		return '<input type="checkbox" id="chkBox_'+options.rowId+'" name="chkBox_'+options.rowId+'" onclick="editUser('+options.rowId+')"  style="width:50px;  height:23px;"   class="easyui-button" value=""/>';
	}
	
	function editUser(id){
	
	}
	
	function frmEMPActive_successsCallback(){
		jQuery('#grdUser').trigger("reloadGrid");
	}
	
	function grdUserbtnActiveSave_onClick(result){	
        var rowid=result.rowId;
    	var btnid=result.btnId;
        var EmpType=jQuery("#grdUser").jqGrid('getCell',rowid,"EMPTYPE");
        //alert(EmpType);
        var EmpKeyid=jQuery("#grdUser").jqGrid('getCell',rowid,"hdnEmpKeyid");
    	//alert(EmpKeyid);
    	var ValidTill=getFieldValue("dteUsrm_validtill_grdUser_"+rowid);
    	//alert(ValidTill);
    	var remarks=getFieldValue("txtUsrm_remarks_grdUser_"+rowid);
    	//alert(remarks);
        processAjaxCalls("EmployeeActiveUpdate_save.appm","EmpKeyid="+EmpKeyid+"&ValidTill="+ValidTill+"&remarks="+remarks+"&EmpType="+EmpType,'update_successCallBack','remove_errorCallBack');

		}
	
	function update_successCallBack(result){
		alert(result.successData.msg);
		jQuery('#grdUser').trigger("reloadGrid");
	}

</script>
<form id="frmEMPActive">
	<div id="wrapperRpt" style="margin-top: 10px; margin-left:50px"></div>
	<table id="grdUser"></table>
	<div id="grdUserPager"></div>
	
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
