
<script>
jQuery(document).ready(function(){
	initialiseForm('frmsafeworkrptgrd');
	jQuery('#submitForm').val('frmsafeworkrptgrd');
	
	processGridnew("Safeworkrpt_input.uwp","q=2","safeworkrptgrid","rptpager","","doubleclicksafeworkrpt");
		
});

function doubleclicksafeworkrpt(id)
{	
	var rowData = jQuery("#safeworkrptgrid").jqGrid('getRowData',id);	
	var keyId = rowData.KEYID;
	//alert(keyId);
	navigateToNextForm("Safeworkgrid_input.uwp?q=2&mode=view&keyId="+keyId);
	
    
}
</script>

<form id="frmsafeworkrptgrd">
	<div id='wrapperRpt' >
       <table>
         <tr>
            <td>
				<div style="padding-left:10px;margin-top: -23px" >
					<label class="notes">Double Click on row to input/view details</label>
				</div>
			</td>
         </tr>
       </table>
	</div>
	<div style="margin-top: -6px;margin-left: 40px">
		<table  id='safeworkrptgrid' >
			<tr>
				<td></td>
			</tr>
		</table>
			<div id='rptpager'></div>
		</div>
		
</form>