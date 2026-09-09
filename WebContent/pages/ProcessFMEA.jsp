<script>
jQuery(document).ready(function(){
	
	processGridnew("FMEAProcessgrid_input.fmeaf","q=2","processGrid","pagerprocess","","DoubleClick");
	jQuery ("#btnNew").click(function(){
		navigateToNextForm("FMEAprocess_input.fmeaf?grid=false" );
	});	
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=Process FMEA.xls", "", "", "", "", "new");					
	});
});

function DoubleClick(id)
{	var rowData = jQuery("#processGrid").jqGrid('getRowData',id);
var keyid = rowData.Keyid;
	navigateToNextForm("FMEAprocess_input.fmeaf?grid=true&keyid="+keyid );
}
</script>

<div id='wrapperRpt' style="">


<div>
<table>
	<tr>
	<td >
			<div style="padding-left:2% ;margin-top: -28px">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry"/>
			</div>
		</td>
		<td>
		<div style="margin-top: -24px "><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div>
		</td>
		<td >
			<div style="width : 332px; padding-left:10px;padding-left:0px\9;margin-top: -20px" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
		
	</tr>
</table>

</div>
<table id='processGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagerprocess'></div>

<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</div>