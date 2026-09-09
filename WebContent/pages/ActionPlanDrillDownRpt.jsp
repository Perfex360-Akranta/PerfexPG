<script>
	jQuery(document).ready(function ()
	{
		var url = jQuery('#hiddenUrl').val();
		var dataStr="?q=2";			
		viewGrid(url,dataStr);													
	});

	function viewGrid(url,filterString)
	{						
		if( validateFilterSelection(filterString))
		{
			filterString += '&drillFlag=f&firstClick=Y';
			processGridnew(url ,filterString,"AbnActionPlandrillGrid","AbnActionPlanpagerid","","doubleClickGrid");
			
			return true;
		}
		return false;
	}
	
	function validateFilterSelection(filterString){
			return true;
	}
	
	function doubleClickGrid(id){ 			
		var rowData = jQuery("#AbnActionPlandrillGrid").jqGrid('getRowData',id);
		
		var selId = rowData.KEYID;
		
		//alert("selId.substr(0,3)***"+selId.substr(0,3));
		if(selId.substr(0,3) == 'MCH' || selId.substr(0,3) == '-'){					
		}	
		else
			{
			var filterData ="?";	
			filterData += 'flid='+ selId+'&drillFlag=f';
			
			var url = jQuery('#hiddenUrl').val();
			//var filter=jQuery('#hdnFilterString').val();
			//filter = filter.replace("&firstClick=Y","");
			//filterData+=filter;
			//alert("filterData----"+filterData);
			processGridnew(url,filterData,"AbnActionPlandrillGrid","AbnActionPlanpagerid","Action Plan-DrillDown","doubleClickGrid","","fillForm");			
		}
	}
	
	function fillForm(id)
	{
		var row = jQuery("#AbnActionPlandrillGrid").jqGrid('getDataIDs');
		var rowIds = jQuery("#AbnActionPlandrillGrid").getDataIDs();
		if(rowIds.length>=0)
		{
			var parentId =  jQuery("#AbnActionPlandrillGrid").jqGrid('getCell', rowIds[0], 'KEYID');
			
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);
			}
		}
		jQuery("#trendnotes").css('display','block');					 				
		jQuery("#trendgraph").css('display','block');
	}
	
	function AbnActionPlandrillGrid_onProcessGridBack(id){
		var url = jQuery('#hiddenUrl').val();					
		var rowIds = jQuery("#AbnActionPlandrillGrid").getDataIDs();
		var rowData = jQuery("#AbnActionPlandrillGrid").jqGrid('getRowData',id);

		var parentId = jQuery("#AbnActionPlandrillGrid").jqGrid('getCell', rowIds[0], 'KEYID');
		var dataString = 'drillFlag=b&parentId=';	
		/*var filter=jQuery('#hdnFilterString').val();
		filter = filter.replace("&firstClick=Y","");
		dataString+=filter;	*/
		if( parentId != null&&parentId.length>0){
			
			if(parentId.substr(0,3) == 'CMP')
			{
				return;
			}	
		}
		//alert("dataString"+dataString);
		processGridnew(url,dataString,"AbnActionPlandrillGrid","AbnActionPlanpagerid","Action Plan-DrillDown","doubleClickGrid","","fillForm");			
	}
</script>
<form id="frmAbnActionPlanDrill">
	<div id="WrapperRpt" style="width:100%">
		<label id="trendnotes"  class=""   style="font-weight: bold; padding-left:1px; margin-top:0px;" >Double Click on Company/Unit/Sbu/Pbu/Section/Line to Drilldown </label>
		<table id ='AbnActionPlandrillGrid'><tr><td></td> </tr></table>
		<div id ='AbnActionPlanpagerid'></div>
	</div>
</form>

