<script>
jQuery(document).ready(function(){

/*	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=achievement.xls", "", "", "", "", "new");					
	}); */
	processGridnew("SafetyAudit_input.saau","q=2","jqGridSafetyaudit","pager","","docDoubleClick");
	jQuery ("#btnNew").click(function(){
		navigateToNextForm("SafetyAuditRecall_input.saau?grid=false" );
	});	
});
function docDoubleClick(id)
{	var rowData = jQuery("#jqGridSafetyaudit").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	
	navigateToNextForm("SafetyAuditRecall_input.saau?keyid="+keyid );
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
<!--		<td>-->
<!--		<div><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div>-->
<!--		</td>-->
		<td >
			<div style="width : 332px;width: 325px\9;" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
		</td>
		
	</tr>
</table>

</div>
<table id='jqGridSafetyaudit'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>

<!--<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />-->
</div>

 

