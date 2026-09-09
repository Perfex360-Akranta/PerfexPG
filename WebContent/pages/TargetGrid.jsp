<script>
	jQuery(document).ready(function(){
		initialiseForm('frmTargetgrid');
		jQuery('#submitForm').val('frmTargetgrid');
		processGridnew("TargetGroup_input.topi","?q=2","targetmaingrid","tgpager","","TargetDoubleClick","","");
	});

	function TargetDoubleClick(id)
	{	
		var rowData = jQuery("#targetmaingrid").jqGrid('getRowData',id);	
		var keyId = rowData.tgtm_keyid;
		//alert(keyId);
		
		navigateToNextForm("TargetGroupform_input.topi?&keyId="+keyId,"");
		
	}


</script>

<form id="frmTargetgrid">

<div id='wrapperRpt' >
<table id='targetmaingrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='tgpager'></div>
</div>





</form>