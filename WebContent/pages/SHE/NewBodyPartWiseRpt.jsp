

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnBodyVsAccPrevDataUrl').val();
				
			  if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid("NewBodyPartRpt_input.natr","?q=1");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=1");	
			  jQuery('#btnPieGraph').click(function(){
					
				  var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
					var rowData = jQuery("#list").jqGrid('getRowData',rowid);
					var selId = rowData.keyid;
					 var url = "Bodychart.natr?rowid=" + selId + "&rownum="+rowid ;
					showGraphData(url);
				}); 
		});
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
				url = url.replace("_getData.natr","_input.natr");
				processGridnew(url,filterString,"list","pager","Body Part Report","","","fillForm");
		
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){
			return true;
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			
			//fillWithCurrentMonth('dtetoMonth');
			//fillWithCurrentMonth('dtefromMonth');
			
			setTimeout(function() {readOnlyFields('cmbinjuryType');},1200);
			setTimeout(function() {readOnlyFields('cmbincidenttype');},1200);
			setTimeout(function() {readOnlyFields('cmbincidentno');},1200);
			setTimeout(function() {readOnlyFields('dteincdntFromDate');},1200);
			setTimeout(function() {readOnlyFields('dteincdntdToDate');},1200);
			setTimeout(function() {readOnlyFields('cboStatus');},1200);
			setTimeout(function() {readOnlyFields('chkSkipLine');},1200);
			setTimeout(function() {readOnlyFields('cmbEquipmentid');},1200);
			setTimeout(function() {readOnlyFields('cmbeqpGroup');},1200);
			setTimeout(function() {readOnlyFields('cmbCircle');},1200);
			setTimeout(function() {readOnlyFields('cmbMachineRank');},1200);
			setTimeout(function() {readOnlyFields('cmbCostCenter');},1200);
			setTimeout(function() {readOnlyFields('cmbMould');},1200);
			setTimeout(function() {readOnlyFields('chkojtbox');},1200);
			setTimeout(function() {readOnlyFields('chkpybox');},1200);
			setTimeout(function() {readOnlyFields('chkkkbox');},1200);
			setTimeout(function() {readOnlyFields('chketbox');},1200);
			setTimeout(function() {readOnlyFields('chkminbox');},1200);
			setTimeout(function() {readOnlyFields('chkmajbox');},1200);
			setTimeout(function() {readOnlyFields('cmbemployee');},1200);
			//enableFields('cmbemployee');			
		}
		function fillForm(id)
		{
			 var row = jQuery("#list").jqGrid('getDataIDs');
				//alert("row"+row);
				 var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
				// alert("cm"+Object.keys(cm));
				 for(var i=0;i<row.length;i++)
				 {
					
					    if(i == row.length-1)
						    {
					    	 for(var j=0;j<cm.length;j++)
					     	 {							
						   		jQuery("#list").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
					     	 }
						    }
						    
				 }					
				 jQuery("#trendgraph").css('display','block');
		}

		function frmbodyvsacc_onerrorCallBack()
		{
			alert("error");
		} 	
</script>
<form id = "frmbodyvsacc">
<div id="wrapperRpt"> 
<table>
               <tr>
                   <td>
                   <div class="" style="padding-right:5px;">
                   <input id="btnPieGraph" class="easyui-button"  type="button" value="Pie-Graph"/> </div>
                   <label id="trendgraph"  class=""   style="font-weight: bold; padding-left:20px;padding-top:25px; display: none;" > </label>
                   </td>
               </tr>
               <tr>
               <td colspan="1">
	               <div style=""> 
				       <table id="list" style="width:100%;"><tr><td/></tr></table>
				       <div id="pager"></div>
		           </div>
               </td>
               </tr>
</table>
	
</div>
<input type="hidden" id=hdnBodyVsAccPrevDataUrl name="hdnBodyVsAccPrevDataUrl" value="${requestScope.filterStr}" />
</form>