<script type="text/javascript">
jQuery(document).ready(function(){	

	viewGrid("whywhyReport_input.why","?q=");
	

});
	jQuery("#bdbtn").click(function(id){
		var row = jQuery("#why").jqGrid('getGridParam','selrow');
		 if(row!=null){
			var rowData = jQuery("#why").jqGrid('getRowData',id);
			
			var rowid = jQuery("#why").jqGrid('getGridParam','selrow');
			var keyid = jQuery("#why").jqGrid('getCell',rowid,3);
		 
			navigateToNextForm("brkdown_input.brdn?BDKeyid="+keyid+'&activity=B',"Breakdown Analysis View");
		 }else
			 alert("Click on Row To View Breakdown Details");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		//alert("filter");
		var tableCaption = "Why why Standard";
		processGridnew(url,filterString,"why","pager",tableCaption,"OpnimpexlTemp","","yyGrid_loadComplete");
		return true;
	}	
	return false;
}
function yyGrid_loadComplete(){
jQuery("#yynote").css('display','block');	
jQuery("#yydetnote").css('display','block');	
}
function validateFilterSelection(filterString){
	if(filterString == "?q=" )
		return true;
	/*if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false))
	{
		alert("Select Datewise Checkbox");
		return false;
	}*/
	if(jQuery('#chkDatewise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("Select  FromDate");
			return false;
		}
		/*else{
			 
			jQuery('#hdnfromDate').val(jQuery('#dtefromDate').datebox('getValue'));
		}*/
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("Select  ToDate");
			return false;
		}
		/*else
		jQuery('#hdntoDate').val(jQuery('#dtetoDate').datebox('getValue'));
	  }*/
	}	
	return  true;
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);

	jQuery('#chkDatewise').click(function(){
 		if(jQuery('#chkDatewise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}
 			
	});
	
}

function OpnimpexlTemp(id){ 
	
	var rowData = jQuery("#why").jqGrid('getRowData',id);
//	alert(rowData);
	var rowId = rowData.EmrNo;
	//alert("rowId "+rowId); 
   
	window.open("whywhyReport_view.why?rowId="+rowId);
	//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");
		
}
</script>
 <form id="Excelview" method="post" action="whywhyReport_view.why"> 	
<!--	<table id="list" ></table>-->
<!--<form name="frmwhyreport" id="frmwhyreport" >-->
<div id="wrapperRpt" style= margin-top:2px;>
<div class=" grdGraphBtnPos"><input type="button" id="bdbtn" onclick="" style="padding-top:3px " class="easyui-button" value="View BD"/></div>
<label id="yynote" class="lossnotes" style="font-weight: bold;padding-left:20px;padding-right:20px;display: none;">${requestScope.yyrep}</label>
<label id="yydetnote" class="lossnotes" style="font-weight: bold;padding-left:20px;padding-right:20px;display: none;">${requestScope.yydetails}</label>
<table id="why" ></table>
<div id="pager"></div>

<input type="hidden" id="hiddenStr" value="sdsd" />
</div></form>

			