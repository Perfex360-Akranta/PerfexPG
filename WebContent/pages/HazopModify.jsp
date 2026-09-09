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
	//	alert(filterString);
		processGridnew("HazopModify_input.hzop",filterString,"HazopModifyGrd","pager","","doubleClickGrid","","","","");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

function doubleClickGrid(rowid){
	var rowData = jQuery("#HazopModifyGrd").jqGrid('getRowData',rowid);
	var keyid=rowData.keyid;
//	alert(keyid);
	//alert("Mode:"+frmMode);
	navigateToNextForm("HazopCreate_input.hzop?q=2&mode="+frmMode+"&keyid="+keyid);
}

</script>
<form name="frmFieldAuditSheetList" id="frmFieldAuditSheetList" >
<div id="wrapperRpt"  >
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	<table id="HazopModifyGrd"></table>
	<div id="pager"></div>
</div>
<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
</form>