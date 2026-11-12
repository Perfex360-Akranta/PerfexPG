
<script type="text/javascript">

jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	//alert(url);
	viewGrid(url,"?q=2");
	jQuery("#btnnew").click(function(){
		navigateToNextForm("RiskAssesmentEntry_input.risk");
	});
});
	
function viewGrid(url,filterString)
{
	var tableCaption = "Risk Assessment List";
	//alert(url);
	processGridnew(url,filterString,"riskgrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
    return true;
}
function loadComplete(){
	hideJqGridRow("riskgrid", "1");
}
function doubleClickGrid(rowid) 
{ 
	//alert(1);
	var rowData = jQuery("#riskgrid").jqGrid('getRowData',rowid );
	var keyid = rowData.keyid;
	// alert(keyid);
	navigateToNextForm("RiskAssesmentEntry_input.risk?keyid="+keyid);
}
</script>
<form name="frmRiskAssessmentList" id="frmRiskAssessmentList" >
<div id="wrapperRpt"  >
	<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
		<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
		<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	<table id="riskgrid"></table>
	<div id="pager"></div>
</div>

</form>