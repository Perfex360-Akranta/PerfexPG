
<script type="text/javascript">

jQuery(document).ready(function(){
	var url = "dailyAuditdetails_input.dailyaudit";
	//alert(jQuery('#frmmode').val());
	
	//alert(url);
	viewGrid(url,"?q=2");
	//jQuery("#btnnew").click(function(){
	//	navigateToNextForm("dailyAudit_input.dailyaudit");
	//});
});
	
function viewGrid(url,filterString)
{
	var tableCaption = "Risk Assessment List";
	//alert(url);
	processGridnew(url,filterString,"dailyauditgrid","dailyauditpager",tableCaption,"doubleClickGrid","","loadComplete","","");
    return true;
}
function loadComplete(){
	hideJqGridRow("dailyauditgrid", "1");
}


function dailyauditgrid_selectRow(rowId)
{
	var gridId="dailyauditgrid";
	 
	if(jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked'))
	 {
       
      // alert("INSIDE THE checkbox");
       //var selArray =  jQuery("#techocloseGrid").jqGrid('getGridParam', 'selarrrow');
       var Keyid =jQuery("#dailyauditgrid").jqGrid('getCell', rowId,"DTLD");
      // alert("Keyid::"+Keyid);
       
  
       LoadPopUp("loadAuditReponse","dailyAuditpopup_input.dailyaudit?q=2&Keyid="+Keyid, true,"50%","40%","30%","30%", "","Audit Responsibility"," "," " );
	 }

}

function doubleClickGrid(rowid) 
{ 
	//alert(1);
	var rowData = jQuery("#dailyauditgrid").jqGrid('getRowData',rowid );
	var keyid = rowData.keyid;
	var frmmode=jQuery('#frmmode').val();
	//alert("frmmode::"+frmmode);
	//alert("frmmode::"+frmmode.length);
	//alert("frmmode::"+frmmode.length);
	// alert(keyid);
	navigateToNextForm("dailyAudit_input.dailyaudit?keyid="+keyid+"&frmmode="+frmmode);
}
</script>
<form name="frmDailyAuditList" id="frmDailyAuditList" >
<div id="wrapperRpt"  >
	<!--<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
		<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
		<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	-->
	
	
	<table id="dailyauditgrid"></table>
	<div id="dailyauditpager"></div>
</div>
<input type="hidden" id="frmmode" name="frmmode" value="${requestScope.type}"></input>
</form>