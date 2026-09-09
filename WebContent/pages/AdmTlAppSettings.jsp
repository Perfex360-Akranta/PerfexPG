<script type="text/javascript">

jQuery(document).ready(function(){
	viewGrid("appSetting_view.conFig","?q=2");
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "ApplicationSettingReport";
		
		processGridnew(url,filterString,"grdAdmAppSettings","grdAdmAppSettingspager1",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;
		
	}
	
} 
function validateFilterSelection(filterString){
	
	return true;
}
function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#grdAdmAppSettings").jqGrid('getRowData',rowid );
	//alert(rowData.KEYID);
	//alert(rowData.LOGINID);
	navigateToNextForm("appSetting_input.conFig?&keyId="+rowData.KEYID+"&mode=U");
}
</script>


<form name="frmgrdAdmAppSettings" id="frmgrdAdmAppSettings" >
<div id="wrapperRpt" >
		<table id="grdAdmAppSettings" ></table>
		<div id="grdAdmAppSettingspager1"></div>
</div>
</form> 