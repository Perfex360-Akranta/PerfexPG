<script type="text/javascript">
jQuery(document).ready(function(){	//alert('3');
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?drillFlag=f");
//alert(actionPart);
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Loss Report";
		processGridnew(url,filterString,"loss","pager",tableCaption,"doubleClickGrid");
		//alert(filterString);
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}

function loss_onProcessGridBack(){
//alert("hi");
	var url = jQuery('#hiddenUrl').val();					
	var fromDate = jQuery('#fromDate').datebox('getValue');
	var toDate = jQuery('#toDate').datebox('getValue');	
	var rowIds = jQuery("#loss").getDataIDs();
	var parentId = jQuery("#loss").jqGrid('getCell', rowIds[0], 'Keyid');		
		//alert(url);
		//alert(parentId);
	if(parentId.substr(0,3) != 'CMP')
	{		
		var dataString = '?&drillValue='+ parentId +'&drillFlag=b';
	//	var dataString = '?compid='+ compid +'&dtFromDate='+ fromDate +'&dtToDate='+ toDate  +'&drillValue='+ drillValue +'&drillFlag=b';
		
		viewGrid(url,dataString);
	}	
	if(parentId.substr(0,3) == 'CMP')
	{
		
	}		
}	
/*
function doubleClickGrid(id){ 
	var rowData = jQuery("#loss").jqGrid('getRowData',id);
	var selId = rowData.mainkeyid;
	//alert(selId);
	try{
		fillDrilFunctlocCombo(selId);
	}catch(Exception	){
	}
	
	if(selId.substr(0,3) != 'MCH'){
		var filterData ="?";
		try{
			filterData = getAllFilterValues()  ;
		}catch(Exception){}
		
		filterData += '&parentId='+ selId+'&drillFlag=f';
		var url = jQuery('#hiddenUrl').val();
		viewGrid(url,filterData);
	}
	if(selId.substr(0,3) == 'MCH'){
		
	}
}*/



</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
<div id="wrapperRpt">
<div class="clear"></div>
<table id="loss" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
</div>