<script>
jQuery(document).ready(function(){
	
	processGridnew("VisualControlCheckListReport_input.visc","q=2","VisualControlGrid","pager","","VisualDoubleClick","","");
	return true;
	
	
});

function viewGrid(url,filterString)
{
	
	processGridnew("VisualControlCheckListReport_input.visc",filterString,"VisualControlGrid","pager","","VisualDoubleClick","","");
	return true;
	
}


function VisualDoubleClick(id)
{	
	var rowData = jQuery("#VisualControlGrid").jqGrid('getRowData',id);	
	var keyId = rowData.keyid;
	//alert(keyId);
	navigateToNextForm("VisualControlChart_input.visc?&mode=view&keyid="+keyId,"");
	
}


</script>
<div id='wrapperRpt' style="width:85%;margin-top: -2px">
<table id='VisualControlGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>

 

