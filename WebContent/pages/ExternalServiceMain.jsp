



<script>
jQuery(document).ready(function(){

	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		processAjaxCalls("openFile.file?fileName=External Service and Repair.xls", "", "", "", "", "new");					
	}); 
	processGridnew("externalserviceandrepair_input.esar","q=2","externalgrid","pager","","docDoubleClick");
	jQuery ("#btnNew").click(function(){
		navigateToNextForm("externalservice_input.esar?grid=false" );
	});	
});


function docDoubleClick(id)
{	
	var rowData = jQuery("#externalgrid").jqGrid('getRowData',id);
	navigateToNextForm("externalservice_input.esar" );
}
</script>




<div id='wrapperRpt' style="width:85%">


<div>
<table>
	<tr>
	<td >
			<div style="padding-left:0%;padding-left:0%\9; ">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div>
		</td>
		<td>
		<div><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div>
		</td>
		<td >
			<div style="width : 332px;width: 325px\9;" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
		
	</tr>
</table>

</div>
<table id='externalgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>

<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</div>

 

