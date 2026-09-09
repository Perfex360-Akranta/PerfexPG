<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");
	
	jQuery('#btnGraph').click(function(){
		
		var rowid = jQuery("#impVscomp").jqGrid('getGridParam','selrow');
		var rowData = jQuery("#impVscomp").jqGrid('getRowData',rowid);				
		var url = "chart.yyidenVscomp";
		
		showGraphData(url);
	});
	
});
function impVscomp_loadComplete()
{	
	var rowIds = jQuery("#impVscomp").getDataIDs();	
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#impVscomp").jqGrid('getCell', rowIds[0], 'KEYFIELD2');					
			hideShowBack(true);
			if(parentId.substr(0,3) == 'CMP')			
				hideShowBack(false);			
		}
	setTotalRowCss('impVscomp');
	var row = jQuery("#impVscomp").jqGrid('getDataIDs');		
	jQuery("#impVscomp").jqGrid('setRowData', row.length, false );//{color:'blue','font-weight':'bold'}
	
	
	
}
function frmFilter_enableDisableSuccessCallBack()
{
	/*jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);

	jQuery('#chkMonthwise').click(function(){
 		if(jQuery('#chkMonthwise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}
 			
	});*/

	fillWithCurrentMonth('dtetoMonth');
	monthDiff(5,"dtefromMonth");
	
	enableFields('chkDatewise');
	enableFields('chkMonthwise');
	enableFields('dtetoMonth');
	enableFields('dtefromMonth');
	enableFields('dtefromDate');
	enableFields('dtetoDate');
			
	
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
	/*if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
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
		
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
	}*/
	return  true;
}

function impVscomp_onProcessGridBack(){

	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#impVscomp").getDataIDs();
	
	var parentId =  jQuery("#impVscomp").jqGrid('getCell', rowIds[0], 'mainkeyid');

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
	var selId = rowData.KEYFIELD2;
	
	
		
	if(checkForZeroes("impVscomp",id,5))
	{
		
		if(selId.substr(0,3) != 'QCM')
		{
			var filterData ="?";		
			filterData += '&parentId='+ selId+'&drillFlag=f';
			//filterData += '&skipLine=Y';
			var url = jQuery('#hiddenUrl').val();
			processGridnew(url,filterData,"impVscomp","pager",'',"doubleClickGrid","","impVscomp_loadComplete");	
		}
		if(selId.substr(0,3) == 'QCM')
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

	<div  style="margin-top:4px;margin-top:10px\9;">
		
		<input id="btnGraph" class="easyui-button"  type="button" value="Graph" style="width:50px;"/>
		<label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label>   	
	</div>
	<div class="clear"></div>
	<table id="impVscomp" ></table>
	<div id="pager"></div>
	<div id="divGraphContainer" ></div>	<br><br><br>
	<input type="hidden" id="hiddenStr" value="sdsd" />

</div>



