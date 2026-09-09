<script type="text/javascript">

jQuery(document).ready(function(){
	var url = getSubmitFormUrl(); 	
	viewGrid(url,"?q=1");
	
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "PPMatrix Advanced Report";
		
		processGridnew(url,filterString,"PPMatAdvGrid","pager",tableCaption,"doubleClickGrid","","PPMatAdvGrid_loadComplete");
	
		return true;
	}
	return false;	
}
function PPMatAdvGrid_loadComplete(){
	
		hideJqGridRow("PPMatAdvGrid","PPMatAdvGridghead_0");
		setTotalRowCss("PPMatAdvGrid");

	
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);

	jQuery('#chkMonthwise').click(function(){
 		if(jQuery('#chkMonthwise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 					
		}
 			
	});
	
}
function validateFilterSelection(filterString){
	
	if(filterString == "?q=1" )
		return true;
	else
	{
		if(!filterMonthnDateDifference(filterString,30,48))
			return false;
		return  true;
	}
 
	
	
		return true;
	
			
}		

		

function checkForZeroes(tableId,selId,colNo)
{
	var Col = colNo+1;
 
	while(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != null)
	{	
		if(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != '0')
			return true;
		Col++;
	}
	 
	return false;
		
}	

</script>
<form name="frmPPMatrixAdvanced" id="frmPPMatrixAdvanced"> 
<div id="wrapperRpt" style= margin-top:2px;>
<div style="margin-top:-1px;" >
	<table id="PPMatAdvGrid" ></table>
	<div id="pager"></div>
</div>  	
	<input type="hidden" id="hiddenStr" value="sdsd" />
</div>

</form>	