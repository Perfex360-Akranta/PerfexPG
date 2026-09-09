<!--  Author ManiKandan-->

<script>
jQuery(document).ready(function(){	

	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "oee Report";
		processGridnew(url,filterString,"list","pager",tableCaption,"dblClickRow");
		return true;
	}	
	return false;
}


function validateFilterSelection(filterString){

	if( filterString != null && filterString.length != 0)
	{
		 if( ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("Enter From date");
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("Enter To date");
			return false;
		}
		
			
	}	
	return  true;
}


jQuery( "#back" ).click(function() {
	//var actionPart = document.getElementById('hiddenUrl').value;
	var url = jQuery('#hiddenUrl').val();					
	var fromDate = jQuery('#fromDate').datebox('getValue');
	var toDate = jQuery('#toDate').datebox('getValue');	
	var rowIds = jQuery("#list").getDataIDs();
	var drillValue = jQuery("#list").jqGrid('getCell', rowIds[0], 'KEYID');		
			
	if(drillValue.substr(0,3) != 'CMP')
	{		
		//var dataString = '?&drillValue='+ drillValue +'&drillFlag=b';
		var dataString = '?compid='+ compid +'&dtFromDate='+ fromDate +'&dtToDate='+ toDate  +'&drillValue='+ drillValue +'&drillFlag=b';
		
		viewGrid(url,dataString);
	}	
	if(drillValue.substr(0,3) == 'CMP')
	{
		//alert("REACHED FIRST LEVEL");
	}		
});		

function dblClickRow(id)
{
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	
	var selId = rowData.KEYID;

	if(selId.substr(0,3) != 'MCH')
	{
		var filterData = getAllFilterValues()  ;
		filterData += '&drillValue='+ selId;
		var url = jQuery('#hiddenUrl').val();
		viewGrid(url,filterData);
	}
	if(selId.substr(0,3) == 'MCH')
	{
	//	alert("LAST LEVEL REACHED");
	}
}


</script>
 
 <form>
  	<div id="wrapperRpt">
     
         <div style="float:right;padding-right:40px;padding-top:10px;">
		 	<input type="button" id="back"  class="easyui-button"  value="Back" />
		 </div>
		 <br/><br/>	
		 
   			<table id="list" border="1" rules="all" ></table>
			<div id="pager"></div>
	</div>
</form>
	