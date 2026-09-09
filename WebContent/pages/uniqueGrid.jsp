<script>
	jQuery(document).ready(function(){
		initialiseForm('frmuniquegrid');
		jQuery('#submitForm').val('frmuniquegrid');
		processGridnew("UniquePosition_input.topi","?q=2","uniquemaingrid","uniquepager","","uniqueDoubleClick","","");
	});

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
<table id='uniquemaingrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='uniquepager'></div>
</div>





</form>