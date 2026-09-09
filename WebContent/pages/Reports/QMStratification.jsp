<script type="text/javascript">

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid("QMStratification_input.QMSTR","?q=1&firstClick=Y");
	else{
		viewGrid(unescape(prevDataUrl),"q=1");
	}	

	
	jQuery('#btnGraph').click(function(){
	 	var id = jQuery("#EDTGrid").jqGrid('getGridParam','selrow');
	 	
	 	var rowData = jQuery("#EDTGrid").jqGrid('getRowData',id);																								
			var rowid = rowData.KEYID;
			
	 	if(rowid !=null){
			if(checkForZeroes("EDTGrid",id,4)){	
	 		
				var url = "chart.QMSTR?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
				showGraphData(url);
		  	 }
		
			 else 
		  		alert("No Record to View Graph");
		 
		}
		else {
			alert("Select Row to View Graph");
			//var url = "chart.QMSTR?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			//showGraphData(url);
		}
	});
	 
	 jQuery("#btnStratGraph").click(function(rowid){
	 	var rowid = jQuery("#EDTGrid").jqGrid('getGridParam','selrow');
	 	if(checkForZeroes("EDTGrid",rowid,4)){	
	 		var url = "chart.QMSTR?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);
	 	 }		
		 else 
	  		alert("No Record to View Graph");
		
	 
	});
	 	
});
 
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
	
		var tableCaption = "EquipmentDownTime Report";
		filterString += '&drillFlag=f';
		
		processGridnew(url,filterString,"EDTGrid","pager",tableCaption,"doubleClickGrid","","EDTGrid_loadComplete");
		
		
		return true;
	}
	return false;	
}

function frmFilter_enableDisableSuccessCallBack()
{
	jQuery("#chkMonthwise").attr('checked',true);
	enableFields("chkMonthwise");
	enableFields("chkDatewise");
	enableFields("dtetoDate");
	enableFields("dtefromDate");
} 
function EDTGrid_loadComplete(){
 
 
	 var rowIds = jQuery("#EDTGrid").getDataIDs();
	if(rowIds.length>=0)
	{
		var parentId =  jQuery("#EDTGrid").jqGrid('getCell', rowIds[0], 'KEYID');
		if(parentId.substr(0,3) != 'CMP'){
			hideShowBack(true);
			
		}
	}
			
		
		jQuery("#eqpnote").css('display','block');	
		jQuery("#eqpfnote").css('display','block');	
		hideJqGridRow("EDTGrid","EDTGridghead_0");
		setTotalRowColorForGroupby("EDTGrid");
	 
 
		 /* jQuery("#EDTGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			  var grid = jQuery('#EDTGrid');
			  var sel_id = grid.jqGrid('getGridParam', 'selrow');
			  var CellData = grid.jqGrid('getCell', sel_id,iCol );
				if(CellData =='0')
					alert("No Data to view");
			  breakdonView(rowid,iCol,cellcontent);
		 
			}	
		});*/
 }
function breakdonView(rowid,iCol,cellcontent){
	 
	var colm = jQuery("#EDTGrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;
	 
	var dateflag = selId.substring(0, 1);
	if(dateflag == '0' || dateflag == '1' || dateflag == '2' ||dateflag == '3' )
		selId = selId.substring(0,11);
	else
		selId = selId.substring(0,8);
	
	 jQuery("#hiddenrowid").val(selId);
	 
}
function doubleClickGrid(id ){ 
	var rowData = jQuery("#EDTGrid").jqGrid('getRowData',id);
	
	var selId = rowData.KEYID;
	
	if(checkForZeroes("EDTGrid",selId,3)){
	 
		if(selId.substr(0,3) != 'ASM'){
			var filterData ="?";
		 	filterData += '&parentId='+ selId+'&drillFlag=f';
			var url = jQuery('#hiddenUrl').val();
		 
			processGridnew(url,filterData,"EDTGrid","pager",'',"doubleClickGrid","","EDTGrid_loadComplete");	
		}
	 
		if(selId.substr(0,3) == 'ASM'){
			//jQuery('#cmbFact').combobox('setValue',selId);
		}
	}
	else
		alert("No Records to View");
}

function EDTGrid_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#EDTGrid").getDataIDs();

	var parentId =  jQuery("#EDTGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var dataString = 'drillFlag=b';		
	 
	processGridnew(url,dataString,"EDTGrid","pager",'',"doubleClickGrid","","EDTGrid_loadComplete");			
}

function getParamName(rowid)
{
/*	if(rowid.substring(0,3)=='CMP')
		return 'cmbCompid';
	else if(rowid.substring(0,3)=='LCN')
		return 'cmbLocnid';
*/  if(rowid.substring(0,3)=='FCT')
		return 'cmbFactid';
	else if(rowid.substring(0,3)=='LIN')
		return 'cmbSectid';
	else if(rowid.substring(0,3)=='CEL')
		return 'cmbCellid';
	else if(rowid.substring(0,3)=='MCH')
		return 'cmbMchid';
	else
		return null;
}
function validateFilterSelection(filterString){
	
return true;
}	
	 


</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->

<form name="frmEdtreport" id="frmEdtreport" >
<div id="wrapperRpt" style="">
<div class="floatright">
<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>   
<input id="btnStratGraph" class="easyui-button"  type="button" value="Stratification Graph"/> 
</div>
<div id="divGraphContainer" ></div>	
<!--<div class="clear"></div>-->
<div style="margin-top: -28px">
<label id="eqpnote" class="lossnotes" style="font-weight: bold;padding-left:1px;padding-right:20px;display: none;">${requestScope.drilldownMsgs}</label>
<label id="eqpfnote" class="lossnotes" style="font-weight: bold;padding-left:1px;padding-right:20px;display: none;">${requestScope.eqpgraph}</label>
<table id="EDTGrid" ></table>
<div id="pager"></div></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenid" value="sdsd" />
<input type="hidden" id="hiddenrowid"  />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hiddencellContent"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>
</form>
	