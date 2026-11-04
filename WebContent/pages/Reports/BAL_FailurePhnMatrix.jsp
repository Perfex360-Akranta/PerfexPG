<script type="text/javascript">

jQuery(document).ready(function(){
	viewGrid("FailurePhnMatrix_input.fpmRpt","?q=1");
	
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Failure Phenomena Report";
		
		processGridnew(url,filterString,"ppGrid","pager",tableCaption,"doubleClickGrid","","ppGrid_loadComplete");
	
		return true;
	}
	return false;	
}
function ppGrid_loadComplete(){
	//alert("hideJqGridRow");
	//alert("hideJqGridRow" +hideJqGridRow);
		// var row = jQuery("#ppGrid").jqGrid('getDataIDs');
		// alert(row.length);
		hideJqGridRow("ppGrid","ppGridghead_0");
		setTotalRowCss("ppGrid");

//	alert(setTotalRowCss);
	
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
 			//alert("Select Datewise Checkbox");			
		}
 			
	});
	
}
function validateFilterSelection(filterString){
	if(filterString == "?q=1" )
		return true;
	/*if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
	{
		alert("Select Monthwise Checkbox");
		return false;
	}*/
	if(jQuery('#chkMonthwise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Select  FromMonth");
			return false;
		}
		/*else{
			 
			jQuery('#hdnfromDate').val(jQuery('#dtefromMonth').datebox('getValue'));
		}*/
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
	}
	/*	else
		jQuery('#hdntoDate').val(jQuery('#dtetoDate').datebox('getValue'));
	  }*/

	//var dateTime = getServerDateTime();
	//var frmmnth =dateTime.getMonth();
	//var currmnth = getMonthStringFromInt(frmmnth);
	//currmnth+="-"+dateTime.getFullYear();
	//var cmbMchid = jQurey('#Machine').val(); 
	
/*	var selmnth = jQuery('#fromMonth').val();
	var selmch = jQuery('#cmbMachine').val();
	var seltomnth = jQuery('#toMonth').val();
	//alert("sel   "+selmnth);
	if( filterString.length != 0)
	{
		/* if( ! checkFilterValueExist(filterString, "cmbSectid"))
		{
			alert("SELECT SHOP");
			return false;
		}
		//else
		if( ! checkFilterValueExist(filterString, "cmbMchid"))
		{
			alert("Select  Machine");
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Select From Month");
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  To Month");
			return false;
		}*/
	
		return true;
	//}
			
}		
//----For Graph---//	
jQuery('#btnGraph').click(function(){
	showGraphData("chart.fpmRpt");
		
});
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
<form name="frmPPMatrix" id="frmPPMatrix"> 
<div id="wrapperRpt" style= margin-top:2px;>

	<div style="margin-top:0px;">
	<input type="button" class="easyui-button" id="btnGraph" name="btnGraph" value="Graph" />
	</div>
	<div id="divGraphContainer" ></div>	
	<div class="clear"></div>
	<table id="ppGrid" ></table>
	<div id="pager"></div>
	<input type="hidden" id="hiddenStr" value="sdsd" />
</div>

</form>	