<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");	
});


function workOrderGrid_loadComplete()
{	
	var circle = jQuery("#hiddencircle").val();
	var rowIds = jQuery("#workOrderGrid").getDataIDs();	
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#workOrderGrid").jqGrid('getCell', rowIds[0], 'KEYIDFIELD');					
			hideShowBack(true);
			if(parentId.substr(0,3) == 'CMP')			
				hideShowBack(false);	
			if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);				
		}
	var row = jQuery("#workOrderGrid").jqGrid('getDataIDs');		
	//jQuery("#workOrderGrid").jqGrid('setRowData', row.length, false );//{color:'blue','font-weight':'bold'}
	/*jQuery("#workOrderGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e)
	{
		doubleClickGrid(rowid,iCol);
				
	} });	*/	
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "Improvement Vs Completed";
		
		processGridnew(url,filterString,"workOrderGrid","pager",tableCaption,"doubleClickGrid","","workOrderGrid_loadComplete");
		//alert(filterString);
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
	var selId = rowData.KEYIDFIELD;
	var circle = jQuery("#hiddencircle").val();
	if(selId.substr(0,3) != 'ASM' && circle.substr(0,3) != "CRC"){
		var filterData ="?";
		try{
	
			filterData += '&parentId='+ selId+'&drillFlag=f';
			var url = jQuery('#hiddenUrl').val();
			processGridnew(url,filterData,"workOrderGrid","pager",'',"doubleClickGrid","","workOrderGrid_loadComplete");	
		
		}catch(Exception){}
		
		}
	if(selId.substr(0,3) == 'ASM'){
		
	}

	
}

</script>

<div id="wrapperRpt">
<div style="margin-top: -28px">
<label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line/Equipment to Drilldown</label>
<div class="clear"></div>
<table id="workOrderGrid" ></table>
<div id="pager"></div>
</div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddencircle" value=""  />
	</div>