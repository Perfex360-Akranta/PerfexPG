<script type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();
var employeeId = jQuery('#hdnEmployeeId').val();

jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"q=2");
});

function viewGrid(url,filterString){
	if( validateFilterSelection(filterString)){
		filterString +="&frmMode="+frmMode;
		// Add employee ID to filter - THIS IS THE CRITICAL LINE
		if(employeeId && employeeId !== 'null' && employeeId !== ''){
			filterString += "&USERID=" + employeeId;
		}
		processGridnew("LOPCActionClosure_input.lopc",filterString,"LOPCActionClosureGrid","pager","","doubleClickGrid","","","","");
	    return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

function doubleClickGrid(rowid){
	var rowData = jQuery("#LOPCActionClosureGrid").jqGrid('getRowData',rowid);
	var keyid=rowData.WWBD_KEYID;
	var frmMode = jQuery('#hdnFrmMode').val();
	
	LoadPopUp("loadactionclosure","LOPCClosurepopup_input.lopc?q=2&keyid="+keyid,true,"60%","50%","30%","30%", "","LOPC Action Closure"," "," " );
}
</script>

<form name="frmLOPCList" id="frmLOPCList" >
	<div id="wrapperRpt">
		<div class="easyui-paddingbfpx" style="padding-left:0%;padding-top:-20px;">
			<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
		</div>
		<table id="LOPCActionClosureGrid"></table>
		<div id="pager"></div>
	</div>
	<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
	<input type="hidden" id='hdnEmployeeId' value="${requestScope.employeeId}"/>
</form>