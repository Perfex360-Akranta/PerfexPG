<script type="text/javascript">
jQuery(document).ready(function(){	
	viewGrid("NominatnReport_input.empnom","?q=");
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Nomination Report";
		processGridnew(url,filterString,"nomtn","pager",tableCaption,"doubleClickGrid");
		
		return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	
		return true;
}
function frmFilter_enableDisableSuccessCallBack()
{

}
</script>

<div id="wrapperRpt">
	<table id="nomtn" ></table>
	<div id="pager"></div>
	<input type="hidden" id="nomintnid"/>
</div>