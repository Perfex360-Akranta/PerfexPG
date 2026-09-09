<script>
jQuery(document).ready(function(){
	
	processGridnew("FMEAEquipmentgrid_input.fmeaf","q=2","equipmentGrid","pagerequipment","","dDoubleClick");
	jQuery ("#btnNew").click(function(){
		navigateToNextForm("FMEAequipment_input.fmeaf?grid=false" );
	});	
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=Equipment FMEA.xls", "", "", "", "", "new");					
	});
});

function dDoubleClick(id)
{	var rowData = jQuery("#equipmentGrid").jqGrid('getRowData',id);
var keyid = rowData.Keyid;
	navigateToNextForm("FMEAequipment_input.fmeaf?grid=true&keyid="+keyid );
}
</script>

<div id='wrapperRpt' style="">


<div>
<table>
	<tr>
	<td >
			<div style=" margin-top: -28px">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry"/>
			</div>
		</td>
		<td>
		<div style="margin-top: -26px"><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div>
		</td>
		<td >
			<div style="width : 332px; padding-left:10px;margin-top: -21px" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
	</tr>
</table>

</div>
<table id='equipmentGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagerequipment'></div>

<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</div>