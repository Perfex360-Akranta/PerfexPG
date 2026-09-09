<script type="text/javascript">
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	//setLoadFormCallBackFrmId("frmProdRejRpt");
	
	viewGrid(url,"?q=2");
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		processGridnew(url,filterString,"prodRejSubGrid","prodRejSubGridPager","","","","prodRejSubGridComplete");
		return true;
	}
	return false;
}
function frmProdRejRpt_afterLoadCallBack(){
	//var machineId = jQuery("#hdnmchId").val();
	//if( machineId == null || machineId.length <= 0 )
		toggleCommonFilter();	
	
}
function prodRejSubGridComplete()
{
	
}

function validateFilterSelection(filterString){
	
	
  return  true;
}

function frmFilter_enableDisableSuccessCallBack()
{	
	
}

</script>
<form id="frmRejDetailedRpt">
	<div id="wrapperRpt" style="max-width: 1210px;">
		<div style="margin-top:-20px;">
			<table id="prodRejSubGrid" style="width:100%">
			<tr><td/></tr></table>
			<div id="prodRejSubGridPager"></div>
		</div>
	</div>
</form>

