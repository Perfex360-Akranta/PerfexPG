

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			 
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl =jQuery('#hdnAccTrendPlotPrevDataUrl').val();
			  var dataString ="";
			  var filter=jQuery('#hdnFilterString').val();
			  if(filter != null && filter.length>0)
				{
					var filterString = jQuery("#hdnFilterString").val();
					var tableCaption = "Accident Trend Report";
					filterString += '&drillFlag=f';
					
					processGridnew(NewAccidentRpt_input.natr,filterString,"grdAccTrend","pager",tableCaption,"doubleClickGrid","","fillForm");		
				}
				else
					viewGrid(url,"&q=1");	

			  jQuery('#btnGraph').click(function(){
					
					
					var hdr =jQuery('#hdnheader').val();
					 if( hdr.length == 0  || parseInt(hdr,10) < 1)
					    { 
						     alert("Select Accident type cell and click on graph ") ;
						     return ;
					    }
					 
					 var rowid = jQuery("#grdAccTrend").jqGrid('getGridParam','selrow');
						var rowData = jQuery("#grdAccTrend").jqGrid('getRowData',rowid);
						var selId1 = rowData.KEYIDFIELD;
						var keyid = selId1.split("#");
						var selId = keyid[0];
					
						var indexid = jQuery("#CH1-"+hdr).html();							
						 	var url = "NewAccidentRptTrendchart.natr?rowid=" + selId + "&rownum="+rowid+"&chartType=BAR&indexid="+indexid ;
							showGraphData(url);
				});	
				  
				  
			  jQuery('#btnLineGraph').click(function(){
				  var hdr =jQuery('#hdnheader').val();

				    if( hdr.length == 0  || parseInt(hdr,10) < 1)
				    { 
					     alert("Select Accident type cell and click on graph ") ;
					     return ;
				    }
					   
					var indexid = jQuery("#CH1-"+hdr).html();
					
					var rowid = jQuery("#grdAccTrend").jqGrid('getGridParam','selrow');
					var rowData = jQuery("#grdAccTrend").jqGrid('getRowData',rowid);

					var row = jQuery("#grdAccTrend").jqGrid('getDataIDs');
					var rowIds = jQuery("#grdAccTrend").getDataIDs();
					var selId = rowData.KEYID;
					//alert("rowid"+rowid);
					//if(selId == null || selId =="")
					//{						
						//	alert("Select Row To View Graph");
					//}  
					//else {						
//						if(checkForZeroes("grdAccTrend",rowid,5)){								
						 	var url = "NewAccidentRptTrendchart.natr?rowid=" + selId + "&rownum="+rowid+"&chartType=LINE&indexid="+indexid ;
							showGraphData(url);
//						}else 
//							alert("No Record to View Graph");
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
			if( validateFilterSelection(filterString))
			{	
				//filterString +='&module=Safety';
				jQuery('#hdnFilterString').val(filterString);
				filterString += '&drillFlag=f&firstClick=Y';
				//alert("view grid....."+filterString);
				url = url.replace("_getData.natr","_input.natr");
				processGridnew(url,filterString,"grdAccTrend","pager","Accident Trend Report","doubleClickGrid","","fillForm");
						
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){
			return true;
		}
		function frmFilter_enableDisableSuccessCallBack()
		{

			setTimeout(function() {readOnlyFields('dteincdntFromDate');},1200);
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
		}
		
function doubleClickGrid(id){ 
			alert("Dobule Click");
			var keyfieldData=jQuery("#hdnFnlnKeyid").val();
			alert("keyfieldData:::::"+keyfieldData);
			keyfieldData=setDrillDoubleClick("grdAccTrend","KEYIDFIELD",id,keyfieldData);
			jQuery("#hdnFnlnKeyid").val(keyfieldData);
				
		}
		function fillForm(id)
		{
			setDrillDownHeader("CH1-0","grdAccTrend","KEYIDFIELD");
			setTotalRowCss('grdAccTrend');
			jQuery("#grdAccTrend").setGridParam({
				onCellSelect:function(id,cellidx,cellvalue) {
					
					accednt(id,cellidx,cellvalue);
				}
				});
		}
		function grdAccTrend_onProcessGridBack(){

			var keyfieldData=jQuery("#hdnFnlnKeyid").val();
			setDrillProcesGridBack("grdAccTrend","KEYIDFIELD",keyfieldData);
			jQuery("#hdnFnlnKeyid").val("");
			}	
</script>
<form>

<div id="wrapperRpt" style="margin-top:9px;"> 
Select on accident type cell and click on graph to view graph
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
<div><table id="grdAccTrend" style="width:100%;"><tr><td/></tr></table>
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