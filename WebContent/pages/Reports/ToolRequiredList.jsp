

<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"q=1");

	/*jQuery('#btnGraph').click(function(){
		var rowid = jQuery("#mttrGrid").jqGrid('getGridParam','selrow');
			if((rowid == null) || (rowid =="")){
			alert("Select Row For View Graph");
		}
			else{
			//	alert("aaaaaaaa");
	
		//alert(rowid);
		var url = "chart.MTTR?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
	//	alert("url" + url);
		showGraphData(url);
		}
	
	});*/
	

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "DrillDown (MTTR) Report";
		processGridnew(url,filterString,"mttrGrid","pager",tableCaption,"doubleClickGrid","","mttrGrid_loadComplete");
		//alert(filterString);
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}
function mttrGrid_loadComplete(){
	//hideJqGridRow("mttrGrid","1");
	//setTotalRowCss('mttrGrid');
var rowIds = jQuery("#mttrGrid").getDataIDs();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#mttrGrid").jqGrid('getCell', rowIds[0], 'KEYFIELD0');
			//alert(parentId);
			if(parentId.substr(0,3) != 'CMP'){
				//alert(parentId);
				hideShowBack(true);
				
				
			}		
		}
		jQuery("#mttrnotes").css('display','block');	
		jQuery("#mttrnotesgraph").css('display','block');
}
function mttrGrid_onProcessGridBack(){
//alert("hi");
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#mttrGrid").getDataIDs();
	
	var parentId =  jQuery("#mttrGrid").jqGrid('getCell', rowIds[0], 'KEYFIELD0');
	//alert("parentId" +parentId);
	var dataString  ="";		
	
	//	if(parentId.substr(0,3) != 'CMP')
//	{		
			dataString = 'drillFlag=b';
			//alert(dataString);
	//	}	
	
	

	processGridnew(url,dataString,"mttrGrid","pager",'',"doubleClickGrid","","mttrGrid_loadComplete");			
}			
					
function doubleClickGrid(id){ 
	var rowData = jQuery("#mttrGrid").jqGrid('getRowData',id);

	var selId = rowData.KEYFIELD0;
	//alert(selId);

	
	if(selId.substr(0,3) != 'MCH'){
		var filterData ="?";
		
		filterData += '&parentId='+ selId+'&drillFlag=f';
		var url = jQuery('#hiddenUrl').val();
		//viewGrid(url,filterData);
		processGridnew(url,filterData,"mttrGrid","pager",'',"doubleClickGrid","","mttrGrid_loadComplete");	
	}
	if(selId.substr(0,3) == 'MCH'){
		
	}
}



</script>
<div id="wrapperRpt" style="margin-top:50px;">
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
<!--<div  style="margin-left:1080px;margin-left:1048px\9;"><input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>   </div> <br><br>-->
<div id="divGraphContainer" ></div>	
<label id="mttrnotes"  class="notes"   style="font-weight: bold; padding-left:20px;padding-right:20px; display: none;" > ${requestScope.drilldownMsg}</label>
<label id="mttrnotesgraph"  class="notes"   style="font-weight: bold; padding-left:20px;padding-right:20px; display: none;" > ${requestScope.GraphMsg}</label>
<div style="margin-top:-30px;">
<table id="mttrGrid" ><tr><td></td></tr></table>
<div id="pager"></div>
</div>
<input type="hidden" id="hiddenStr" value="sdsd" />
</div>	


