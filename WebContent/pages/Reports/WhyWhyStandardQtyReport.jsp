<script type="text/javascript">
jQuery(document).ready(function(){	

	viewGrid("whywhyQtyReport_input.why","?q=2");	

});
	jQuery("#bdbtn").click(function(id){
		var row = jQuery("#why").jqGrid('getGridParam','selrow');
		 if(row!=null){
			var rowData = jQuery("#why").jqGrid('getRowData',id);
			
			var rowid = jQuery("#why").jqGrid('getGridParam','selrow');
			var keyid = jQuery("#why").jqGrid('getCell',rowid,3);
		 
			navigateToNextForm("brkdown_input.brdn?BDKeyid="+keyid,"Breakdown Analysis View");
		 }else
			 alert("Click on Row To View Breakdown Details");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		//alert("filter");
		var tableCaption = "Why why Standard";
		processGridnew(url,filterString,"Qtywhy","Qtyypager",tableCaption,"openYYExcel","","yyQtyGrid_loadComplete");
		return true;
	}	
	return false;
}
function yyGrid_loadComplete(){
jQuery("#yynote").css('display','block');	
jQuery("#yydetnote").css('display','block');	
}
function validateFilterSelection(filterString){
	
	return  true;
}
function frmFilter_enableDisableSuccessCallBack()
{  
	/*jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
	jQuery("#dtefromDate").datebox('disable');
	jQuery("#dtetoDate").datebox('disable');
	jQuery("#dtefromDate").datebox("setValue","");
	jQuery("#dtetoDate").datebox("setValue","");
	jQuery('#chkDatewise').click(function(){
 		if(jQuery('#chkDatewise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}*/

		fillWithCurrentMonth('dtetoMonth');
		monthDiff(5,"dtefromMonth");
		
		enableFields('chkDatewise');
		enableFields('chkMonthwise');
		enableFields('dtetoMonth');
		enableFields('dtefromMonth');
		enableFields('dtefromDate');
		enableFields('dtetoDate');
 			
	//});
	
}

function openYYExcel(id){ 
	
	var rowData = jQuery("#Qtywhy").jqGrid('getRowData',id);

	var rowId = rowData.DocNo3;

  // alert(rowId);
	window.open("whywhyQtyReport_view.why?rowId="+rowId);
	
		
}
</script>
 <form id="Excelview" method="post" action="whywhyReport_view.why"> 	
<!--	<table id="list" ></table>-->
<!--<form name="frmwhyreport" id="frmwhyreport" >-->
<div id="wrapperRpt" style= margin-top:2px;>
<!--<div class=" grdGraphBtnPos"><input type="button" id="bdbtn" onclick="" style="padding-top:3px " class="easyui-button" value="View BD"/></div>-->
<label id="yynote" class="lossnotes" style="font-weight: bold;padding-left:20px;padding-right:20px;display: none;">${requestScope.yyrep}</label>
<label id="yydetnote" class="lossnotes" style="font-weight: bold;padding-left:20px;padding-right:20px;display: none;">${requestScope.yydetails}</label>
<label id="lbl" class="lossnotes" style="font-weight: bold;padding-right:20px;display: block;margin-top:4px;">Click on Data Row to view why why Details</label>
<table id="Qtywhy" ></table>
<div id="Qtyypager"></div>

<input type="hidden" id="hiddenStr" value="sdsd" />
</div></form>

			