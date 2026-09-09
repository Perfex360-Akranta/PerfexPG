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
		processGridnew("Energywaterbankmodify_input.ewib",filterString,"FieldAuditgrid","pager","","doubleClickGrid","","","","");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

function doubleClickGrid(rowid){ 

	var rowData = jQuery("#FieldAuditgrid").jqGrid('getRowData',rowid );
	var keyid = rowData.keyid;
	//alert(keyid);
	navigateToNextForm("Energywaterbank_input.ewib?q=2&mode="+frmMode+"&keyid="+keyid);
}

</script>
<form name="frmFieldAuditSheetList" id="frmFieldAuditSheetList" >
<div id="wrapperRpt"  >
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	<table id="FieldAuditgrid"></table>
	<div id="pager"></div>
</div>
<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
</form>