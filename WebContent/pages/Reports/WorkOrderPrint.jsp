<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");	
});


function workOrderGrid_loadComplete()
{	
	var rowIds = jQuery("#workOrderGrid").getDataIDs();	
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#workOrderGrid").jqGrid('getCell', rowIds[0], 'ORDERNO');
			
			hideShowBack(true);
			if(parentId.substr(0,2) == 'MW')			
				hideShowBack(false);				
		}
	var row = jQuery("#workOrderGrid").jqGrid('getDataIDs');		
	
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "Improvement Vs Completed";
		
		processGridnew(url,filterString,"workOrderGrid","pager",tableCaption,"doubleClickGrid","","workOrderGrid_loadComplete");
		
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}

function workOrderGrid_onProcessGridBack(){

	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#workOrderGrid").getDataIDs();
	
	var parentId =  jQuery("#workOrderGrid").jqGrid('getCell', rowIds[0], 'KEYIDFIELD');
	//alert("parentId" +parentId);
	var dataString = 'drillFlag=b';		
	if( parentId != null&&parentId.length>0){
		
		if(parentId.substr(0,3) == 'CMP')
		{
			return ;
		}	
	}
	processGridnew(url,dataString,"workOrderGrid","pager",'',"doubleClickGrid","","workOrderGrid_loadComplete");
	
}			
					
function doubleClickGrid(id){ 
	var rowData = jQuery("#workOrderGrid").jqGrid('getRowData',id);
	var selId = null;
	if(rowData.WorkOrderNo != null && rowData.WorkOrderNo != 'undefined' && rowData.WorkOrderNo != '' && rowData.WorkOrderNo != ' ')
		selId =rowData.WorkOrderNo;
	var rowId = rowData.PMSD_KEYID;
	if(selId != null)
	{
	if(selId.substr(0,3) != 'PMD' ){
		var filterData ="?";
		try{

			filterData += '&woNo='+ selId+'&drillFlag=f';
			var url = "WOPrintActivity_input.woprint";
			
			processGridnew(url,filterData,"workOrderGrid","pager",'',"doubleClickGrid","","workOrderGrid_loadComplete");	
		
		}catch(Exception){}
		
		}
	else{
		
		processAjaxCalls("WoPrintExcel_excel.woprint", "", 'removeOperator_successCallBack','removeOperator_errorCallBack')	;
	}
		}
	
	else{
		window.open("WoPrintExcel_excel.woprint","errcalbak");
	}

	
}

</script>

<div id="wrapperRpt">
<label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line/Equipment to Drilldown</label>
<div class="clear"></div>
<table id="workOrderGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
	</div>