
<script type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"q=2");
});

function viewGrid(url,filterString){
	if( validateFilterSelection(filterString))
	{
		filterString +="&frmMode="+frmMode;
		// Added doubleClickGrid as the callback function for double-click event
		processGridnew("FieldAuditSheetReport_input.fass",filterString,"FieldAuditgrid","pager","","doubleClickGrid","","","","");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

// Added double-click function to open record in view mode
/* function doubleClickGrid(rowid){
	var rowData = jQuery("#FieldAuditgrid").jqGrid('getRowData',rowid);
	var keyid = rowData.keyid;

	// Check if keyid exists
	if(keyid == null || keyid == '' || keyid == undefined){
		alert("No record selected or Key ID not found");
		return false;
	}

	// Open popup in view mode with lowercase 'view' to match the condition: if(frmmode=="view")
	LoadPopUp("DivFieldAuditSheet","FieldAuditSheet_input.fass?q=2&mode=view&keyid="+keyid,true,"1100px","550px","4px","4%", "","Field Audit Sheet - View",false,true);
}
 */
 function doubleClickGrid(rowid){
	    var rowData = jQuery("#FieldAuditgrid").jqGrid('getRowData', rowid);
	    
	    
	    var keyid = rowData.KEYID || rowData.keyid || rowData.KeyId;
	    
	    if(!keyid || keyid == ''){
	       
	        keyid = jQuery("#FieldAuditgrid").jqGrid('getCell', rowid, 'KEYID');
	    }
	    
	    if(!keyid || keyid == ''){
	        alert("No record selected or Key ID not found");
	        return false;
	    }

	    LoadPopUp("DivFieldAuditSheet", "FieldAuditSheet_input.fass?q=2&mode=view&keyid=" + keyid,
	              true, "1100px", "550px", "4px", "4%", "", "Field Audit Sheet - View", false, true);
	}
</script>
<form name="frmFieldAuditSheetList" id="frmFieldAuditSheetList" >
<div id="wrapperRpt">
<div class="easyui-paddingbfpx" style="padding-left:0%;padding-top:10px;">
<label class="notes" style="padding-left:10px;"> Double Click on row to view details </label>
	</div>
	<table id="FieldAuditgrid"></table>
	<div id="pager"></div>
</div>
<input type="hidden" id="mode" name="mode" value="view">
<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
</form>