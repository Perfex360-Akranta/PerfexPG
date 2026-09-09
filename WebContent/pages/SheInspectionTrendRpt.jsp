<script>
jQuery.noConflict();
jQuery(document).ready(function(){	
	  var url = jQuery('#hiddenUrl').val();
	  var  prevDataUrl = jQuery('#hdnAccTrendPlotPrevDataUrl').val();
	  var dataString ="";
	  var filter=jQuery('#hdnFilterString').val();
	 // alert("filter***********"+filter);
	  if(filter != null && filter.length>0)
		{
		//	alert("first grid ");
			var filterString = jQuery("#hdnFilterString").val();
			var tableCaption = "Inspection Trend Report";
			filterString += '&drillFlag=f';
			
			processGridnew(hsetrendreport_input.hseinsp,filterString,"grdInspecTrend","pager",tableCaption,"doubleClickGrid","","fillForm");		
		}
		else
			viewGrid(url,"&q=1");	
	 		  
	  jQuery('#btnGraph').click(function(){
			
			
			var hdr =jQuery('#hdnheader').val();
			 if( hdr.length == 0  || parseInt(hdr,10) < 1)
			    { 
				     alert("Select the cell and click on graph ") ;
				     return ;
			    }
			 
			 var rowid = jQuery("#grdInspecTrend").jqGrid('getGridParam','selrow');
				var rowData = jQuery("#grdInspecTrend").jqGrid('getRowData',rowid);
				var selId1 = rowData.KEYIDFIELD;
				var keyid = selId1.split("#");
				var selId = keyid[0];
			
				var indexid = jQuery("#CH1-"+hdr).html();
					
				//var s = checkForZeroes("grdInspecTrend",rowid,5);
				//if(s){								
				 	var url = "hseInspectionTrendchart.hseinsp?rowid=" + selId + "&rownum="+rowid+"&chartType=BAR&indexid="+indexid ;
					showGraphData(url);
				//}else 
				//	alert("No Record to View Graph");
		//}
		});	
		  
		  
	  jQuery('#btnLineGraph').click(function(){
		  var hdr =jQuery('#hdnheader').val();

		    if( hdr.length == 0  || parseInt(hdr,10) < 1)
		    { 
			     alert("Select the cell and click on graph ") ;
			     return ;
		    }
			   
			var indexid = jQuery("#CH1-"+hdr).html();
			
			var rowid = jQuery("#grdInspecTrend").jqGrid('getGridParam','selrow');
			var rowData = jQuery("#grdInspecTrend").jqGrid('getRowData',rowid);

			var row = jQuery("#grdInspecTrend").jqGrid('getDataIDs');
			var rowIds = jQuery("#grdInspecTrend").getDataIDs();
			var selId = rowData.KEYID;
			//alert("rowid"+rowid);
			//if(selId == null || selId =="")
			//{						
				//	alert("Select Row To View Graph");
			//}  
			//else {						
//				if(checkForZeroes("grdInspecTrend",rowid,5)){								
				 	var url = "hseInspectionTrendchart.hseinsp?rowid=" + selId + "&rownum="+rowid+"&chartType=LINE&indexid="+indexid ;
					showGraphData(url);
//				}else 
//					alert("No Record to View Graph");
			//}
		});	
	
});

function accednt(id,cellidx,cellvalue){
		var celindx=cellidx-4;
	 jQuery('#hdnheader').val(celindx);

	/* jQuery("#hiddenrowid").val(selId);*/
}

function viewGrid(url,filterString)
{
	//alert("else grid ");
	if( validateFilterSelection(filterString))
	{	
		//alert("else  grid  inside");
		//filterString +='&module=Safety';
		jQuery('#hdnFilterString').val(filterString);
		filterString += '&drillFlag=f&firstClick=Y';
		//alert("view grid....."+filterString);
		url = url.replace("_getData.hseinsp","_input.hseinsp");
		
		//processGridnew(url,filterString,"grdInspecTrend","pager","Customer Complaint Plotting","fillForm","","CustCompPlotGrid");
		processGridnew(url,filterString,"grdInspecTrend","pager","Inspection Trend Report","doubleClickGrid","","fillForm");
	
		return true;	
	}
	return false;	
}

function validateFilterSelection(filterString){
	//alert("validate filter grid ");
	return true;
}

function frmFilter_enableDisableSuccessCallBack()
{
	//fillWithCurrentMonth('dtetoMonth');
	//fillWithCurrentMonth('dtefromMonth');
	/*setTimeout(function() {readOnlyFields('dteincdntFromDate');},1200);
	setTimeout(function() {readOnlyFields('dteincdntdToDate');},1200);
	setTimeout(function() {readOnlyFields('cmbbodyPart');},1200);
	setTimeout(function() {readOnlyFields('cmbemployee');},1200);
	setTimeout(function() {readOnlyFields('cmbincidentno');},1200);	
	setTimeout(function() {readOnlyFields('cmbinjuryType');},1200);
	setTimeout(function() {readOnlyFields('cmbMould');},1200);
	setTimeout(function() {readOnlyFields('chkojtbox');},1200);
	setTimeout(function() {readOnlyFields('chkpybox');},1200);
	setTimeout(function() {readOnlyFields('chkkkbox');},1200);
	setTimeout(function() {readOnlyFields('chketbox');},1200);
	setTimeout(function() {readOnlyFields('chkminbox');},1200);
	setTimeout(function() {readOnlyFields('chkmajbox');},1200);
	//setTimeout(function() {readOnlyFields('chkSkipLine');},1200);
	*/					
}
function doubleClickGrid(id){ 
	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("grdInspecTrend","KEYIDFIELD",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
		
}
function fillForm(id)
{
	
	setDrillDownHeader("CH1-0","grdInspecTrend","KEYIDFIELD");
	setTotalRowCss('grdInspecTrend');
	jQuery("#grdInspecTrend").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			
			accednt(id,cellidx,cellvalue);
		}
		});
}
function grdInspecTrend_onProcessGridBack(){
	//alert("process grid ");
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("grdInspecTrend","KEYIDFIELD",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
	}	

</script>

<form>
<div id="wrapperRpt" style="margin-top:9px;"> 
Select on Inspection type cell and click on graph to view graph
<table>
 
<tr>
	<td>
	
	<div>
    <input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>
	<input id="btnLineGraph" class="easyui-button"  type="button" value="Line Graph"/>
	</div>
	
    <label id="trendnotes"  style="font-weight: bold; padding-left:20px;padding-top:25px; display: none;" >Double Click on Company to Drilldown </label>
	<label id="trendgraph"  style="font-weight: bold; padding-left:20px;padding-top:25px; display: none;" > ${requestScope.GraphMsg}</label>
	</td>
    
</tr>
<tr>
<td colspan="2">
<div><table id="grdInspecTrend" style="width:100%;"><tr><td/></tr></table>
			 <div id="pager"></div>
			 </div>
			 </td>
 </tr>
</table>

</div>
<input type="hidden" id="hdnAccTrendPlotPrevDataUrl" name="hdnAccTrendPlotPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
<input type="hidden" id="hdnFnlnKeyid" />
<input type="hidden" id="hdnheader"/>

</form>