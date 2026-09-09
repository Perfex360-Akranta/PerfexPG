
<script>

jQuery(document).ready(function(){
	
	var url = jQuery('#hiddenUrl').val();			
	viewGrid("roleteamallgrid_input.roleteam","q=2");
	
	jQuery ("#btnNew").click(function()
	{
	     navigateToNextForm("roleteamall_input.roleteam","Role & Team");	
	});
});
function viewGrid(url,dataString)
{
	processGridnew(url,dataString,"list","pager","","docDoubleClick");
}
function validateFilterSelection(filterString)
{
	return  true;
}

function docDoubleClick(id)
{
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var level = rowData.frl_level;
	var Flid =  rowData.flid;
	navigateToNextForm('roleteamall_input.roleteam?flid='+Flid+'&level='+level,"Role & Team");
}

</script>

<form>
  <div id="WrapperRpt" style="width:100%">
	<table>
	 <tr>	
		<td>
	    <div style="margin-top: -28px" ><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">	   
	    <span style="padding-left:10px;"><label class="notes"> Double Click on row to input/view details </label></span>	    
	   </div></td>
	    </tr> 
	</table> 
	</div> 
	<div style="padding-left:45px;">
	    <table id="list" ><tr><td></td></tr></table>
		<div id="pager"></div>
		<div id="paramDiv" style="display:none;" title="param">
		</div>
	</div>

	    <input type="hidden" id="hdnFilterStr" value="${requestScope.filter}"/>
		<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
		<input type="hidden" id="hdnBtnName" value="View Report"/> 
	
</form>
	
	