<script type="text/javascript">
jQuery(document).ready(function(){
//var url = jQuery('#hiddenUrl').val();
//var dataString = "?q=1&appPillar=G";
//processGridnew(url,dataString,"grdConfig","grdConfigPager","Configuration","","","");

 viewGrid("apps_input.appSetting","");
 });
 function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "General";
		
		processGridnew(url,filterString,"grdApps","grdAppsPager",tableCaption,"","","pcs_loadComplete");
	
		return true;
	}
	return false;	
}  
function pcs_loadComplete(){
	//alert("hideJqGridRow");
	//alert("hideJqGridRow" +hideJqGridRow);
		// var row = jQuery("#pcs").jqGrid('getDataIDs');
		// alert(row.length);
		//hideJqGridRow("pcs","pcsghead_0");
		//setTotalRowColorForGroupby("pcs");

//	alert(setTotalRowCss);
	jQuery("#grdApps").children().removeClass("ui-jqgrid-sortable");
	
}
</script>
<form name="frmConfiguration" id="frmConfiguration" >
<div id="wrapperRpt">
<table id="grdApps" ></table>
						<div id="grdAppsPager"></div></div>
						</form>