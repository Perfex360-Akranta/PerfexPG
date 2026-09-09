<script type="text/javascript">

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid("PMVsBD_input.pmbd","?q=1&firstClick=Y");
	jQuery('#btnGraph').click(function(){
		
			var rowid = jQuery("#PmBdGrid").jqGrid('getGridParam','selrow');
			if(checkForZeroes("PmBdGrid",rowid,3)){
			var url = "chart.pmbd?rowid="+rowid; 		
			showGraphData(url);
			}
			else 
				alert("No Record Found");
		});
});


function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		var tableCaption = "EquipmentDownTime Report";
		filterString += '&drillFlag=f';
		
		processGridnew(url,filterString,"PmBdGrid","pager",tableCaption,"doubleClickGrid","","PmBdGrid_loadComplete");
		
		
		return true;
	}
	return false;	
}
function PmBdGrid_loadComplete(){
	//alert("hideJqGridRow");
	var rowIds = jQuery("#PmBdGrid").getDataIDs();
	var circle = jQuery("#hiddencircle").val();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#PmBdGrid").jqGrid('getCell', rowIds[0], 'keyid');
			
			//if( parentId != null && parentId != 'false' && parentId != false){	
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);				
			}
			if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);	
		}
	jQuery("#eqpnote").css('display','block');	
	//hideJqGridRow("PmBdGrid","PmBdGridghead_0");
	setTotalRowColorForGroupby("PmBdGrid");
	
}
function doubleClickGrid(id)
{
	var circle = jQuery("#hiddencircle").val();
	var rowData = jQuery("#PmBdGrid").jqGrid('getRowData',id);
	var selId = rowData.keyid;
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	
	if(selId.substr(0,3) != 'PHM' && circle.substr(0,3) != "CRC"){
		var filterData ="?";
	//	try{
	//		filterData = getAllFilterValues()  ;
	//	}catch(Exception){}
		
		filterData += '&parentId='+ selId+'&drillFlag=f';
		filterData += "&chkRemoveBlank="+removeBlank;
		var url = jQuery('#hiddenUrl').val();
		//viewGrid(url,filterData);
		processGridnew(url,filterData,"PmBdGrid","pager",'',"doubleClickGrid","","PmBdGrid_loadComplete");	
	}
	

	if(selId.substr(0,3) == 'PHM'){
		//jQuery('#cmbFact').combobox('setValue',selId);
	}
}

function PmBdGrid_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#PmBdGrid").getDataIDs();
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	var parentId =  jQuery("#PmBdGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var dataString = 'drillFlag=b';		
	dataString  += "&chkRemoveBlank="+removeBlank;
	//if(parentId.substr(0,3) == 'CMP')
	//{		
	//	return ;
		
	//}	

	processGridnew(url,dataString,"PmBdGrid","pager",'',"doubleClickGrid","","PmBdGrid_loadComplete");			
}
function validateFilterSelection(filterString){
	return true;
}	 	
			


</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
 
<form name="frmEdtreport" id="frmEdtreport" >
<div id="wrapperRpt" style="margin-top:5px;">
<table>
<tr>
<td>
<div style="width:500px;margin-left:-6px;">
<label id="eqpnote" class="notes" style="font-weight: bold;display: none;">${requestScope.drilldownMsgs}</label>
</div>
</td>
<td>
<div  style="padding-right:20px;margin-left:550px;margin-left:536px\9;"><input id="btnGraph" class="easyui-button"  type="button" value="Graph" style="width:56px;"/>   </div>
</td>
</tr>
</table>
<div id="divGraphContainer" ></div>	
<!--<div class="floatright" style="padding-right:20px;"><input type="button" id="bdbtn" onclick="" class="easyui-button" value="View BD"/></div>-->






<table id="PmBdGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenRemoveBlank" value=""/>
<input type="hidden" id="hiddencircle" value=""  />
</div>
</form>
	