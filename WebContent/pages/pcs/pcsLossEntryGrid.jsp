<script>
jQuery(document).ready(function(){
	initialiseForm('frmLossEntryGrid');
	//alert("alert 1");
	//processGridnew("pcsLossEntryGridmain_input.pcs","?q=2&firstClick=Y", "pcslossGrid", "pcslossPager","","pcsdblClick","","pcsloss_loadComplete");
	var url="pcsLossEntryGridmain_input.pcs";
	var filterString="?q=2";
	viewGrid(url,filterString);
});
jQuery("#btnNew").click(function(){
	navigateToNextForm('pcsLoss_input.pcs', "Loss Entry");
});
function pcsloss_loadComplete(){
	
}
function viewGrid(url,dataString){
	if(validateFilterSelection(dataString))
	{ 
		var flid = getFilterValue(dataString, 'flid');
		if (flid.trim().length>0)
			dataString += '&fliterstr=fliterstr';
	//var filterString="?q=2";
	processGridnew(url,dataString, "pcslossGrid", "pcslossPager","","pcsdblClick","","pcsloss_loadComplete");
	}
	return true;
}
function validateFilterSelection(dataString){
	return true;
}
function pcsdblClick(id) {
	var rowData = jQuery("#pcslossGrid").jqGrid('getRowData',id);
	var flid=rowData.FLID;
	var fromDate=rowData.PLOSSDATE;
	var DMT=rowData.DMT;
	var JH=rowData.JH;
	var ds='?&mode=MODIFY&'+'&DMT='+DMT+'&JH='+JH+'&flid='+flid+'&date='+fromDate;
	//alert(ds);
	navigateToNextForm('pcsLoss_input.pcs'+ds, "Loss Entry");
	
}

</script>
<form id="frmLossEntryGrid" name="frmLossEntryGrid"><!--
style=" width : 60px;height:22px;"
 --><div style="padding-left:50px;padding-top:10px;top:10px;">
 		<input type="button" class="easyui-button" id="btnNew"	name="btnNew" value="New Entry" />
 </div>
<div Style="padding-left: 50px;">
	<table id='pcslossGrid'>
	<tr><td></td></tr>
	</table>
	<div id="pcslossPager"></div>
</div>
</form>