<script type="text/javascript">
var mode=jQuery("#mode").val();
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"q=2");
});
	
function viewGrid(url,filterString){  
	if( validateFilterSelection(filterString))
	{
		
		filterString +="&mode="+mode;
		//alert(filterString);
		processGridnew("FieldAuditSheetModify_input.fass",filterString,"FieldAuditgrid","pager","","doubleClickGrid","","","","");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

function doubleClickGrid(rowid){ 
	//alert(1);
  var mode=jQuery("#mode").val();
	var rowData = jQuery("#FieldAuditgrid").jqGrid('getRowData',rowid );
	var keyid = rowData.keyid;
//	alert(mode);
	LoadPopUp("DivFieldAuditSheet","FieldAuditSheet_input.fass?q=2&mode="+mode+"&keyid="+keyid,true,"1100px","550px","4px","4%", "multiSelectOk_Callback","Field Audit Sheet Creation",false,true);	
	//navigateToNextForm("FieldAuditSheet_input.fass?q=2&mode="+frmMode+"&keyid="+keyid);
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
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
</form>