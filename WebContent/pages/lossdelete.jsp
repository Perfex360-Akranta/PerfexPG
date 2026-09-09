<script>
jQuery(document).ready(function(){
	initialiseForm('frmLossviewdelete');
	var url="pcsLossEntryGridmain_input.pcs";
	var filterString="?q=2";
	viewGrid(url,filterString);
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
	navigateToNextForm('pcsLoss_input.pcs'+ds, "Loss View Delete");
}

</script>
<form id="frmLossviewdelete" name="frmLossviewdelete">

<div Style="padding-left: 50px;">
	<table id='pcslossGrid'>
	<tr><td></td></tr>
	</table>
	<div id="pcslossPager"></div>
</div>
</form>