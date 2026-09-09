<script type="text/javascript">
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	setLoadFormCallBackFrmId("frmProdRejRpt");
	invokeAfterLoadFormCallBack();
	
	//viewGrid(url,"?q=2");
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		processGridnew(url,filterString,"prodRejList","prodRejPager","","","","prodRejComplete");
		return true;
	}
	return false;
}
function frmProdRejRpt_afterLoadCallBack(){
	//var machineId = jQuery("#hdnmchId").val();
	//if( machineId == null || machineId.length <= 0 )
		toggleCommonFilter();	
	
}
function prodRejComplete()
{
	var rowIds = jQuery('#prodRejList').jqGrid().getDataIDs();
	
	for(var i=0;i<rowIds.length;i++)
	{
		var cellVal =jQuery('#prodRejList').getCell(rowIds[i],"sectName");
		jQuery(' tr#prodRejListghead_'+i).find(' td:first-child').css('background-color','#43c5dd');		
	} 		
	
}

function validateFilterSelection(filterString){
	
	if(filterString.length > 0  && ! checkFilterValueExist(filterString, "cmbSectid"))
	{
	alert("Select DMT");
	return false;
	}
  return  true;
}

function frmFilter_enableDisableSuccessCallBack()
{	
	fillWithCurrentMonth('dtetoMonth');
	monthDiff(5,"dtefromMonth");
	
	enableFields('chkMonthwise');
	enableFields('dtetoMonth');
	enableFields('dtefromMonth');
}

</script>
<form id="frmProdRejRpt">
	<div id="wrapperRpt" style="max-width: 1210px;">
		<div style="margin-top:-20px;">
			<table id="prodRejList" style="width:100%">
			<tr><td/></tr></table>
			<div id="prodRejPager"></div>
		</div>
	</div>
</form>

