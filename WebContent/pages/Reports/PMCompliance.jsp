
<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");
	
	
});
function PMCompliance_loadComplete()
{	
	var rowIds = jQuery("#PMCompliance").getDataIDs();	
	var circle = jQuery("#hiddencircle").val();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#PMCompliance").jqGrid('getCell', rowIds[0], 'keyId');					
			hideShowBack(true);
			if(parentId.substr(0,3) == 'CMP')			
				hideShowBack(false);	
			if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);			
		}
	
	var row = jQuery("#PMCompliance").jqGrid('getDataIDs');		
	jQuery("#PMCompliance").jqGrid('setRowData', row.length, false );//{color:'blue','font-weight':'bold'}
	
	jQuery('.ui-jqgrid-labels').css('border-color','#AFD6FE');
	
	setTotalRowCss("PMCompliance");
}

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "Improvement Vs Completed";
		processGridnew(url,filterString,"PMCompliance","pager",tableCaption,"doubleClickGrid","","PMCompliance_loadComplete");		
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}

function PMCompliance_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#PMCompliance").getDataIDs();
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	
	var parentId =  jQuery("#PMCompliance").jqGrid('getCell', rowIds[0], 'keyId');	
	
			var dataString  ="";		
			if(parentId.substr(0,3) != 'CMP')
			{		
				dataString = 'drillFlag=b';
				dataString += "&chkRemoveBlank="+removeBlank;
				processGridnew(url,dataString,"PMCompliance","pager",'',"doubleClickGrid","","PMCompliance_loadComplete");
			}	
			//if(parentId.substr(0,3) != 'LIN'){
				//dataString = 'drillFlag=b';
							
			//}
}		
					
function doubleClickGrid(id){ 
	
	var rowData = jQuery("#PMCompliance").jqGrid('getRowData',id);
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	var circle = jQuery("#hiddencircle").val();
	var selId = rowData.keyId;
	var rowid = rowData.rnum0;
		//alert('selId:'+selId);
		if(checkForZeroes("PMCompliance",id,4))
			{			
				if(selId.substr(0,3) != 'MCH'){
					var filterData ="?";		
					filterData += '&flid='+ selId+'&drillFlag=f';
					filterData += "&chkRemoveBlank="+removeBlank;
					var url = jQuery('#hiddenUrl').val();
					processGridnew(url,filterData,"PMCompliance","pager",'',"doubleClickGrid","","PMCompliance_loadComplete");	
				}
				if(selId.substr(0,3) == 'MCH' && circle.substr(0,3) == "CRC"){
					
				}
			}
			else 
				alert("No Record to View Graph");
}
</script>

<div id="wrapperRpt">

	
	<label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label>
	<div class="clear"></div>
	<table id="PMCompliance"></table>
	<div id="pager"></div>

	<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenRemoveBlank" value=""/>
<input type="hidden" id="hiddencircle" value=""  />
</div>





