<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");
	
	
});
function impVscomp_loadComplete()
{	
	var rowIds = jQuery("#impVscomp").getDataIDs();	
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#impVscomp").jqGrid('getCell', rowIds[0], 'KEYIDFIELD');					
			hideShowBack(true);
			if(parentId.substr(0,3) == 'CMP')			
				hideShowBack(false);			
		}
	
	var row = jQuery("#impVscomp").jqGrid('getDataIDs');		
	jQuery("#impVscomp").jqGrid('setRowData', row.length, false );//{color:'blue','font-weight':'bold'}
	
	
	
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
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		filterString += '&drillFlag=f&firstClick=Y';
		//filterString += '&skipLine=Y';
		var tableCaption = "Improvement Vs Completed";
		processGridnew(url,filterString,"impVscomp","pager",tableCaption,"doubleClickGrid","","impVscomp_loadComplete");		
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	if(filterString == "?q=1" )
		return true;
	if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
	{
		alert("Select Monthwise Checkbox");
		return false;
	}
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
	return  true;
}

function impVscomp_onProcessGridBack(){

	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#impVscomp").getDataIDs();
	
	var parentId =  jQuery("#impVscomp").jqGrid('getCell', rowIds[0], 'KEYIDFIELD');

	var dataString = 'drillFlag=b';		
	//dataString += '&skipLine=Y';
	if( parentId != null&&parentId.length>0){
		
		if(parentId.substr(0,3) == 'CMP')
		{
			return;
		}	
	}
	processGridnew(url,dataString,"impVscomp","pager",'',"doubleClickGrid","","impVscomp_loadComplete");		

}			
					
function doubleClickGrid(id){ 
	
	var rowData = jQuery("#impVscomp").jqGrid('getRowData',id);
	var selId = rowData.KEYIDFIELD;
	
	
		
	if(checkForZeroes("impVscomp",id,1))
	{
		if(selId.substr(0,3) != 'MCH')
		{
			var filterData ="?";		
			filterData += '&parentId='+ selId+'&drillFlag=f';
			//filterData += '&skipLine=Y';
			var url = jQuery('#hiddenUrl').val();
			processGridnew(url,filterData,"impVscomp","pager",'',"doubleClickGrid","","impVscomp_loadComplete");	
		}
		if(selId.substr(0,3) == 'MCH')
		{}
		else if(selId==null || selId.trim()=='')
		{		
			
			return false;
		
		}
	}
	else
		alert("No Records to View");
}
</script>

<div id="wrapperRpt"style= margin-top:2px;>

	
	<label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label>
	<div class="clear"></div>
	<table id="impVscomp" ></table>
	<div id="pager"></div>
	<div id="divGraphContainer" ></div>	<br><br><br>
	<input type="hidden" id="hiddenStr" value="sdsd" />

</div>



