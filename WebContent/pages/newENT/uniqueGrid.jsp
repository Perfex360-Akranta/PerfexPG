<script>
	jQuery(document).ready(function(){
		initialiseForm('frmuniquegrid');
		jQuery('#submitForm').val('frmuniquegrid');
		viewGrid("UniquePosition_input.topi","?q=2");
		jQuery ("#btnNew").click(function()
		{
			navigateToNextForm("UniquePositionform_input.topi","Unique Position");
		});
		
	});
	
	function viewGrid(url, filterStr) {
		processGridnew(url,filterStr,"uniquemaingrid","uniquepager","","uniqueDoubleClick","","");
		return true;
	}

	function uniqueDoubleClick(id)
	{	
		var rowData = jQuery("#uniquemaingrid").jqGrid('getRowData',id);	
		var keyId = rowData.role_keyid;
		//alert(keyId);
		
		navigateToNextForm("UniquePositionform_input.topi?&keyId="+keyId,"");
		
	}


</script>

<form id="frmuniquegrid">

<div id='wrapperRpt' >
<div style="margin-top:-24px;">
<input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry" style="height:20px;">
</div>
<table id='uniquemaingrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='uniquepager'></div>
</div>





</form>