
<script>

jQuery(document).ready(function(){	
	 
	
    processGridnew("TaskMappingKsa_input.tmks","q=2","TaskList","pager","","docDoubleClick","");

 
	jQuery ("#btnNew").click(function()
	{
		     navigateToNextForm("TaskKsaDetails_input.tmks","Task Master");	
	});
  
	
});
	
function docDoubleClick(id)
{		
	var rowData = jQuery("#TaskList").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	var Flid =  rowData.FLN;
	navigateToNextForm('TaskKsaDetails_input.tmks?&grid=true&keyId='+keyid,"Task Master");  
}
</script>
<form id="frmTaskKsa">
  <div id="WrapperRpt">
<table>
 <tr>

	<td style="padding-left:5px;">
    <!--<div ><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">
    --><span style="padding-left:10px;"><label class="notes"> Double Click on row to input/view details </label></span>   
   </div></td>
    </tr> 
</table> 
</div> 
<div style="padding-left:45px;">
    <table id="TaskList" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="paramDiv" style="display:none;" title="param">
	</div>
</div>
	 
	
	
</form>
	
	