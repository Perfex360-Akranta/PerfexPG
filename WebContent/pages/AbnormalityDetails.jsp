<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");
	
});
function viewGrid(url,filterString)
{
	var urlArr = url.split('?');
	
	if( validateFilterSelection(filterString))
	{					
		
		processGridnew(urlArr[0],urlArr[1],"abnDetailsGrid","pager","","doubleClickDetailsGrid");
		
		return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	return  true;
}

function doubleClickDetailsGrid(id){ 
	
	var rowData = jQuery("#abnDetailsGrid").jqGrid('getRowData',id);
	var selId = rowData.tagNo;	
	navigateToNextForm('Abnormality_input.abnForm'+'?AbnId='+selId+'&mode=view');	
}
</script>
<div class="clear"></div>
<div id="wrapperRpt">
<table id="abnDetailsGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
	
</div>


