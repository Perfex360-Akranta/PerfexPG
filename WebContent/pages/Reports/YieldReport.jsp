<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();	
	
	viewGrid(actionPart,"?q=2&firstClick=Y");
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		filterString +='&drillFlag=f';
		filterString += '&firstClick=Y';
		var tableCaption = "Yield Report";		
		processGridnew(url,filterString,"yield","pager",tableCaption,"yield_doubleClickGrid","","load_Complete");
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}

function load_Complete()
{
	setTotalRowCss('internal');
	var url = jQuery('#hiddenUrl').val();
	var rowIds = jQuery("#yield").getDataIDs();
	var parentId = jQuery("#yield").jqGrid('getCell', rowIds[4], 'KEYID');

}

function frmFilter_enableDisableSuccessCallBack()
{
	enableDisableDatenMonthFilter();	
}


</script>

<div id="wrapperRpt" style="margin-top: 10px;">
	<div class="clear"></div>
	<div style="margin-top: -28px">
	<div id="divGraphContainer" ></div><br>
	<table id="yield" ></table>
	<div id="pager"></div></div>
	
</div>
