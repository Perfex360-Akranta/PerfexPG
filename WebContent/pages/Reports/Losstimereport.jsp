<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnIncidentPlotPrevDataUrl').val();
			  if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid("ProductionLossTimeRpt_input.prdloss","?q=1");
			    else
				 viewGrid(unescape(prevDataUrl),"&q=1");	
			       jQuery('#btnGraph').click(function(){
				    var rowId = jQuery("#grdLossTimegrid").jqGrid('getGridParam','selrow');
				    var rowData = jQuery("#grdLossTimegrid").jqGrid('getRowData',rowId);
					var selId = rowData.keyid;
					var url = "chartLossTime.prdloss?rowid=" + selId + "&rownum="+rowId ;
					//alert(url);
					showGraphData(url);		 			
				});						
		});  					
		
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
				url = url.replace("_getData.prdloss","_input.prdloss");
				processGridnew(url,filterString,"grdLossTimegrid","pagergird","Loss Time Report","","","fillForm");
		
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){
			return true;
		}

		function fillForm(id)
		{
			      var row = jQuery("#grdLossTimegrid").jqGrid('getDataIDs');
				 var cm = jQuery("#grdLossTimegrid").jqGrid("getGridParam", "colModel");
				 for(var i=0;i<row.length;i++)
				 {
					
					    if(i == row.length-1)
						    {
					    	 for(var j=0;j<cm.length;j++)
					     	 {							
						   		jQuery("#grdLossTimegrid").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
					     	 }
						    }
						    
				 }					
				 jQuery("#trendgraph").css('display','block');
		}

		function frmincident_onerrorCallBack()
		{
			alert("error");
		} 	
</script>
<form id="frmLoss">
<div id="wrapperRpt" style="margin-top:9px;"> 
<table>
<tr>
	<td>
	<div>
	<input id="btnGraph" class="easyui-button"  type="button" value="Bar-Graph"/>
	</div>
    <label id="trendnotes"  style="font-weight: bold; padding-left:20px;padding-top:25px; display: none;" >Double Click on Company to Drilldown </label>
	<label id="trendgraph"  style="font-weight: bold; padding-left:20px;padding-top:25px; display: none;" > ${requestScope.GraphMsg}</label>
	</td> 
</tr>
<tr>
<td colspan="2">
<div><table id="grdLossTimegrid" style="width:100%;"><tr><td/></tr></table>
			 <div id="pagergird"></div>
			 </div>
			 </td>
 </tr>
</table>
</div>
<input type="hidden" id="hdnIncidentPlotPrevDataUrl" name="hdnIncidentPlotPrevDataUrl" value="${requestScope.filterStr}" />
</form>