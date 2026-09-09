<script type="text/javascript">

jQuery(document).ready(function(){	
	 
	var actionPart = jQuery('#hiddenUrl').val();

	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid("QmDefectProcess_input.QMDFT","?q=1&firstClick=Y");
	else{
		viewGrid(unescape(prevDataUrl),"q=1");
	}	
 
	jQuery("#btnGraph").attr("disabled", true);
	jQuery("#btnGraph").removeClass("easyui-button");
	jQuery("#btnGraph").addClass("disabledButton");
	jQuery("#bdbtn").attr("disabled", true);
	jQuery("#bdbtn").removeClass("easyui-button");
	jQuery("#bdbtn").addClass("disabledButton");
	jQuery("#hiddenIns").val("Y");
	jQuery("#hiddenQty").val("Y");
	
	 jQuery('#btnGraph').click(function(){
	 	var rowid = jQuery("#QMPRSGRID").jqGrid('getGridParam','selrow');
	 	filterString += '&drillFlag=f';
		
			var url = "chart.QMDFT?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);
		//}
	});
	 
	 jQuery("#bdbtn").click(function(rowid){
	 	var rowid = jQuery("#QMPRSGRID").jqGrid('getGridParam','selrow');
	  //	if( rowid != null){
	  		var range = jQuery('#txtTopn').val();
	  		var url = "chartPareto.QMDFT?rowid=" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:" ");
	  		if(range.length>0)
				url += "&TOPVAL=" + range;
			showGraphData(url);
		//}else
		//	alert("Select on Row to View Pareto Graph");
		 
		
	 
	}); 
	 	
});
 
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		
			
		processGridnew(url,filterString,"QMPRSGRID","pager","","doubleClickGrid","","QMPRSGRID_loadComplete");
		return true;
	}
	return false;	
}
function QMPRSGRID_loadComplete(){
 
 
	setTotalRowCss("QMPRSGRID");
	var rowIds = jQuery("#QMPRSGRID").getDataIDs();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#QMPRSGRID").jqGrid('getCell', rowIds[0], 'keyid');
			if(parentId.substr(0,3) != 'QPH'){
				hideShowBack(true);
				
			}
		}
		jQuery("#eqpnote").css('display','block');	
		jQuery("#eqpfnote").css('display','block');	
		//hideJqGridRow("QMPRSGRID","QMPRSGRIDghead_0");
		//setTotalRowColorForGroupby("QMPRSGRID");
		
 
		/*  jQuery("#QMPRSGRID").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			  var grid = jQuery('#QMPRSGRID');
			  var sel_id = grid.jqGrid('getGridParam', 'selrow');
			  var CellData = grid.jqGrid('getCell', sel_id,iCol );
				if(CellData =='0')
					alert("No Data to view");
			  breakdonView(rowid,iCol,cellcontent);
		 
			}	
		});*/
		jQuery("tr.jqgridheaderrow1").children().bind("dblclick", function(){
		 
			var cell = jQuery(this);
			 
			var index = cell.parent('tr').children().index(cell);
			//alert("1 : "+index);
			index = parseInt(index)+1;
			//alert(index);
			var colm = jQuery("#QMPRSGRID").jqGrid ('getGridParam', 'colModel');
			 
			selIdVal = colm[index].name; 
		    //alert(selIdVal);
			selId = selIdVal.substring(0, 3)+"/"+selIdVal.substring(3,selIdVal.length-3);
			//alert(" fg "+selId);
			var filterData ="?";
				//filterData += getParamName(selId)+"="+selId;
				filterData += '&parentId='+ selId+'&drillFlag=f';
				var url = jQuery('#hiddenUrl').val();
							
				processGridnew(url,filterData,"QMPRSGRID","pager",'',"","","QMPRSGRID_loadComplete");	
		});
 }
 
function doubleClickGrid(id,iCol,cellcontent ){ 
	var rowData = jQuery("#QMPRSGRID").jqGrid('getRowData',id);
	
	var selId = rowData.keyid;
	 //alert(selId);
	if(checkForZeroes("QMPRSGRID",selId,2)){
	
		if(selId.substr(0,3) != 'QCM'){
			var filterData ="?";
		 	filterData += '&drillId='+ selId+'&drillFlag=f';
			var url = jQuery('#hiddenUrl').val();
		 
			processGridnew(url,filterData,"QMPRSGRID","pager",'',"doubleClickGrid","","QMPRSGRID_loadComplete");	
		}
	 
		if(selId.substr(0,3) == 'PHM'){
			//jQuery('#cmbFact').combobox('setValue',selId);
		}
	}
	else
		alert("No Records to View");
}

function QMPRSGRID_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#QMPRSGRID").getDataIDs();

	var parentId =  jQuery("#QMPRSGRID").jqGrid('getCell', rowIds[0], 'keyid');
	var dataString = 'drillFlag=b';		
	 
	processGridnew(url,dataString,"QMPRSGRID","pager",'',"doubleClickGrid","","QMPRSGRID_loadComplete");			
}
	
function getParamName(rowid)
{
	if(rowid.substring(0,3)=='QPH')
		return 'cmbPhenomenaid';
	else
		return null;
}
function validateFilterSelection(filterString){
	
		if(getFilterValue(filterString, "chkinstance")=="1" && getFilterValue(filterString, "chkquantity")=="0")
		{	jQuery("#btnGraph").attr("disabled", false);
			jQuery("#btnGraph").addClass("easyui-button");
			jQuery("#bdbtn").attr("disabled", false);
			jQuery("#bdbtn").addClass("easyui-button");
			jQuery("#hiddenIns").val("Y");
			jQuery("#hiddenQty").val("N");		

		}
		else if(getFilterValue(filterString, "chkinstance")=="0" && getFilterValue(filterString, "chkquantity")=="1")
		{	jQuery("#btnGraph").attr("disabled", false);
			jQuery("#btnGraph").addClass("easyui-button");
			jQuery("#bdbtn").attr("disabled", false);
			jQuery("#bdbtn").addClass("easyui-button");
			jQuery("#hiddenIns").val("N");
			jQuery("#hiddenQty").val("Y");
		}
		
		else if(getFilterValue(filterString, "chkinstance")=="1" && getFilterValue(filterString, "chkquantity")=="1")
		{
			jQuery("#btnGraph").attr("disabled", true);
			jQuery("#btnGraph").removeClass("easyui-button");
			jQuery("#btnGraph").addClass("disabledButton");
			jQuery("#bdbtn").attr("disabled", true);
			jQuery("#bdbtn").removeClass("easyui-button");
			jQuery("#bdbtn").addClass("disabledButton");
			jQuery("#hiddenIns").val("Y");
			jQuery("#hiddenQty").val("Y");
			
		}	
		else
			{
			jQuery("#btnGraph").attr("disabled", true);
			jQuery("#btnGraph").removeClass("easyui-button");
			jQuery("#btnGraph").addClass("disabledButton");
			jQuery("#bdbtn").attr("disabled", true);
			jQuery("#bdbtn").removeClass("easyui-button");
			jQuery("#bdbtn").addClass("disabledButton");
			jQuery("#hiddenIns").val("N");
			jQuery("#hiddenQty").val("N");
			}		
 
return true;
}	
	 




</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->


<form name="frmQMReport" id="frmQMReport" >
<div id="wrapperRpt" >

<div class="right" style="padding-right:20px;">
<label>Enter the range:</label>
 <input type="text" id="txtTopn" name="txtTopn" class="easyui-text"style="height:22px\9;"/>
 
<input type="button" id="bdbtn" class="easyui-button" value="Pareto Graph" style=" width : 93px;"/>
<!-- <input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>  -->
</div>

<div id="divGraphContainer" ></div>	
<!--<div class="clear"></div>-->
<label id="eqpnote" class="lossnotes" style="font-weight: bold;padding-left:2px;padding-right:10px;display: none;">${requestScope.drilldownMsgs}</label>
<label id="eqpfnote" class="lossnotes" style="font-weight: bold;padding-left:2px;padding-right:10px;display: none;">${requestScope.eqpgraph}</label>

<div style="margin-top: -0px">
<table id="QMPRSGRID" ></table>
<div id="pager"></div></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenid" value="sdsd" />
<input type="hidden" id="hiddenrowid"  />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hiddenIns" value=""/>
<input type="hidden" id="hiddenQty" value=""/>
<input type="hidden" id="hiddencellContent"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>
</form>
	