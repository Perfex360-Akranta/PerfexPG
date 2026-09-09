<script>
jQuery(document).ready(function(){
	processGridnew("visualcheckpoints_input.visc","q=2","VisualControlcheckpointsGrid","pagercheckpoints","","VisualctrlDoubleClick","","");
jQuery ("#btnNew").click(function(){
           
		
		navigateToNextForm("visualcheckpoints_modify.visc?grid=false","");
	});

});

function VisualctrlDoubleClick(id)
{	
	var rowData = jQuery("#VisualControlcheckpointsGrid").jqGrid('getRowData',id);	
	var keyId = rowData.Keyid;
	//alert(keyId);
	navigateToNextForm("visualcheckpoints_modify.visc?&keyid="+keyId,"");
	
}


</script>
<form id ='frmVisualControlCheckpoints'>
<div id='wrapperRpt' >
<table >
<tr>
	<td >
			<div style="vertical-align: top;margin-top: -28px">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div>
		</td>
		
		</tr>
</table>
<table id='VisualControlcheckpointsGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagercheckpoints'></div>
</div>
</form>