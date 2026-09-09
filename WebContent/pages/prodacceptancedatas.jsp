<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
		var url = jQuery('#hiddenUrl').val();			
		processGridnew(url,"?q=2","prodAcceptanceGrid","prodAcceptancePager","","fillProdAcceptanceForm","","LoadProdAcc");
});

function fillProdAcceptanceForm(id)
{
		var rowData = jQuery("#prodAcceptanceGrid").jqGrid('getRowData',id);	
		var keyId = rowData.WorkOrderNo0;		
		var dataString='?q=2&woKeyId='+keyId;
		navigateToNextForm('prodAcceptance_input.work'+dataString,'Production Acceptance');
}
function LoadProdAcc()
{
	var woAllId = jQuery("#prodAcceptanceGrid").jqGrid('getDataIDs');
	 
	 for(i=0;i<woAllId.length;i++)	
	 {
			if(jQuery("#prodAcceptanceGrid").getCell(woAllId[i], 'Priority1')=="L")
				jQuery("#prodAcceptanceGrid").jqGrid('setCell',woAllId[i],"Priority1","",{'background-color':'#c0ffc0'});				
			else if(jQuery("#prodAcceptanceGrid").getCell(woAllId[i], 'Priority1')=="M")	
				jQuery("#prodAcceptanceGrid").jqGrid('setCell',woAllId[i],"Priority1","",{'background-color':'#c0c0c0'});			
			else if(jQuery("#prodAcceptanceGrid").getCell(woAllId[i], 'Priority1')=="H")	
				jQuery("#prodAcceptanceGrid").jqGrid('setCell',woAllId[i],"Priority1","",{'background-color':'#99ccff'});			
			else if(jQuery("#prodAcceptanceGrid").getCell(woAllId[i], 'Priority1')=="V")	
				jQuery("#prodAcceptanceGrid").jqGrid('setCell',woAllId[i],"Priority1","",{'background-color':'#ffff99'});			
	 }	
	 
}	
</script>

<table  style="margin-top:2px;padding-left:70px;">
	<tr>
		<td><span class="wo-priority" style="background-color:#c0ffc0"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Low</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="wo-priority" style="background-color:#c0c0c0"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Medium</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="wo-priority" style="background-color:#99ccff"></span></td>
		<td><label style="color:dark brown;font-weight: bold">High</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="wo-priority" style="background-color:#ffff99"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Very High</label></td>		
	</tr>
</table>
<div id="wrapper">
	<div style=""> 
			 <input type="text" style="display:none" id="txtformMode" name="txtformMode"/>
			 <table id="prodAcceptanceGrid" style="width:100%"><tr><td/></tr></table>
			 <div id="prodAcceptancePager"></div>
	</div>
</div>