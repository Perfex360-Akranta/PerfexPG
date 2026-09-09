
<script>
jQuery(document).ready(function(){
	initialiseForm('frmsafeworkgrd');
	jQuery('#submitForm').val('frmsafeworkgrd');
	
	processGridnew("Safework_input.uwp","q=2","safeworkgrid","pager","","doubleclicksafework");
	jQuery ("#btnNewsafe").click(function(){
		navigateToNextForm("Safeworkgrid_input.uwp");
		
		
	});	
});

function doubleclicksafework(id)
{	
	var rowData = jQuery("#safeworkgrid").jqGrid('getRowData',id);	
	var keyId = rowData.KEYID;
	//alert(keyId);
	navigateToNextForm("Safeworkgrid_input.uwp?&keyId="+keyId);
	
    
}
</script>

<form id="frmsafeworkgrd">
<div id='wrapperRpt' >



<div>
<table>
	<tr>
	    <td>
			<div  style="margin-top: -28px">
			<input id="btnNewsafe" name="btnNewsafe" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div>
		</td>
		<td>
<!--		<div><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div>-->
		</td>
		<td>
			<div style="padding-left:10px;margin-top: -23px" >
			<label class="notes">Double Click on row to input/view details</label>
			</div>
		</td>
		
	</tr>
</table>

</div>

	<div style="margin-top: -8px">
<table  id='safeworkgrid' >
			<tr>
				<td></td>
			</tr>
		</table>
	
		<div id='pager'></div></div>
		</div>
		
</form>