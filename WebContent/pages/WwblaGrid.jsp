<script>
jQuery(document).ready(function(){

	processGridnew("WwblaModify_input.wwbla","q=2","Wwbla","pager","","docDoubleClick");
	jQuery ("#btnNew").click(function(){
		
		navigateToNextForm("Wwblagrid_input.revmst?grid=false" );
	});	
});

function docDoubleClick(id)

{
var rowData = jQuery("#Wwbla").jqGrid('getRowData',id);
var keyid = rowData.KEYID;
var flid = rowData.FLID;
	navigateToNextForm("WwblaView_input.wwbla?keyid="+keyid+"&flid"+flid);
}

</script>

<form>
<div id='wrapperRpt' style="width:85%">


<div>
<table>
	<tr>
	<td >
			<div style="padding-left:0%;padding-left:0%\9;margin-top: -28px ;display:none">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div>
		</td>
		<td>

		</td>
		<td >
			<div style="width : 332px;width: 325px\9;margin-top: -23px" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
		
	</tr>
</table>

</div>
<div style="margin-top: -5px">
<table id='Wwbla'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</div>