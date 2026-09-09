<script type="text/javascript">

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid("UnplannedMaintRpt_input.UPMRPT","?q=1&firstClick=Y");
	else{
		viewGrid(unescape(prevDataUrl),"q=1");
	}	

	
	jQuery('#btnGraph').click(function(){
	 	var rowid = jQuery("#UnPlanGrid").jqGrid('getGridParam','selrow');
	 	alert(rowid);
	 	if(rowid !=null){
			if(checkForZeroes("UnPlanGrid",rowid,3)){	
	 
				var url = "chart.UPMRPT?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
				showGraphData(url);
		  	 }
		
			 else 
		 		alert("No Record to View Graph");
		 
		}
	 	// else 
		  		//alert("Select Row to View Graph");
		else {
			var url = "chart.UPMRPT?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);
		}
	});
	 
	 jQuery("#bdbtn").click(function(rowid){
	 	var rowid = jQuery("#UnPlanGrid").jqGrid('getGridParam','selrow');
	  	if( rowid == null)
			alert("Select on Row to View BreakDown ");
		else{
		 	var selid = jQuery("#hiddenrowid").val();
		 	var url = jQuery("#UnPlanGrid").jqGrid('getGridParam', 'url');
			url = url.replace("UnplannedMaintRpt_getData.UPMRPT","UnplannedMaintRpt_input.UPMRPT");
			url = escape(url);
			 
			if(checkForZeroes("UnPlanGrid",rowid,3)){	
			if(selid != "codeFiel")
				navigateToNextForm("unplanned_view.brdn?"+getParamName(rowid)+"="+rowid+"&selid="+selid,"Unplanned Maintanance View",null,{"filterString":url});
			else
				navigateToNextForm("unplanned_view.brdn?"+getParamName(rowid)+"="+rowid,"Unplanned Maintanance View",null,{"filterString":url});
			}
			else
				alert("No Data to View");
		}
		
	 
	});
	 	
});
 
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
	
		var tableCaption = "EquipmentDownTime Report";
		filterString += '&drillFlag=f';
		
		processGridnew(url,filterString,"UnPlanGrid","pager",tableCaption,"doubleClickGrid","","UnPlanGrid_loadComplete");
		
		
		return true;
	}
	return false;	
}
function UnPlanGrid_loadComplete(){
 
 
	var rowIds = jQuery("#UnPlanGrid").getDataIDs();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#UnPlanGrid").jqGrid('getCell', rowIds[0], 'keyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);
				
			}
		}
		jQuery("#eqpnote").css('display','block');	
		jQuery("#eqpfnote").css('display','block');	
		//hideJqGridRow("UnPlanGrid","UnPlanGridghead_0");
		setTotalRowColorForGroupby("UnPlanGrid");
	 
 
		  jQuery("#UnPlanGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			  var grid = jQuery('#UnPlanGrid');
			  var sel_id = grid.jqGrid('getGridParam', 'selrow');
			  var CellData = grid.jqGrid('getCell', sel_id,iCol );
				if(CellData =='0')
					alert("No Data to view");
			  breakdonView(rowid,iCol,cellcontent);
		 
			}	
		});
 }
function breakdonView(rowid,iCol,cellcontent){
	 
	var colm = jQuery("#UnPlanGrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;
	 
	var dateflag = selId.substring(0, 1);
	if(dateflag == '0' || dateflag == '1' || dateflag == '2' ||dateflag == '3' )
		selId = selId.substring(0,11);
	else
		selId = selId.substring(0,8);
	
	 jQuery("#hiddenrowid").val(selId);
	 
}
function doubleClickGrid(id ){ 
	var rowData = jQuery("#UnPlanGrid").jqGrid('getRowData',id);
	
	var selId = rowData.keyid;
	if(checkForZeroes("UnPlanGrid",selId,3)){
	
		if(selId.substr(0,3) != 'PHM'){
			var filterData ="?";
		 	filterData += '&parentId='+ selId+'&drillFlag=f';
			var url = jQuery('#hiddenUrl').val();
			
			processGridnew(url,filterData,"UnPlanGrid","pager",'',"doubleClickGrid","","UnPlanGrid_loadComplete");	
		}
	 
		if(selId.substr(0,3) == 'PHM'){
			//jQuery('#cmbFact').combobox('setValue',selId);
		}
	}
	else
		alert("No Records to View");
}

function UnPlanGrid_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#UnPlanGrid").getDataIDs();

	var parentId =  jQuery("#UnPlanGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var dataString = 'drillFlag=b';		
	 
	processGridnew(url,dataString,"UnPlanGrid","pager",'',"doubleClickGrid","","UnPlanGrid_loadComplete");			
}

function getParamName(rowid)
{
	if(rowid.substring(0,3)=='CMP')
		return 'cmbCompid';
	else if(rowid.substring(0,3)=='LCN')
		return 'cmbLocnid';
	else if(rowid.substring(0,3)=='FCT')
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
/*	var frmmnth =new Date().getMonth();
	var currmnth = getMonthStringFromInt(frmmnth);
	currmnth+="-"+new Date().getFullYear();
	
	var selmnth = jQuery('#fromDate').val();
	//alert("sel   "+selmnth);
	if( filterString.length != 0)
	{
		 if( ! checkFilterValueExist(filterString, "cmbMchid"))
		{
			//alert("SELECT EQUIPMENT");
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Enter  MONTH");
			return false;
		}
			return true;
	//	}
		// return false;			
	//}	
	

}	*/	
return true;
}	
	 


</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->

<form name="frmEdtreport" id="frmEdtreport" >


<div id="wrapperRpt">
<div>
<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/> 
<input type="button" id="bdbtn" onclick="" class="easyui-button" value="View Unplanned" style=" width : 99px;"/>
<label id="eqpnote" class="lossnotes" style="font-weight: bold; display: none;" >Double Click on Company/Unit/Section/Line/Equipment/Assembly/Phenomena-Cause to Drilldown</label>
<span><label id="eqpfnote" class="lossnotes" style="font-weight: bold;padding-left:40px;padding-right:20px;display: none;">${requestScope.eqpgraph}</label>
</span>
</div>

<div id="divGraphContainer" ></div>	
<!--<div class="clear"></div>-->
<table id="UnPlanGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenid" value="sdsd" />
<input type="hidden" id="hiddenrowid"  />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hiddencellContent"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>
</form>
	