<script>
jQuery(document).ready(function(){
	//alert("122");
	processGridnew("FMEADesigngrid_input.fmeaf","q=2","designGrid","pagerdesign","","docDoubleClick");
	jQuery ("#btnNew").click(function(){
		navigateToNextForm("FMEAdesignView_input.fmeaf?grid=false&clearfrom=true" );
	});	
	
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnfmeaView").val(btnName);
	jQuery("#btnfmeaView").click(function()
			{
		
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=Design FMEA.xls", "", "", "", "", "new");					
	}); 
});

function docDoubleClick(id)
{	var rowData = jQuery("#designGrid").jqGrid('getRowData',id);
var keyid = rowData.Keyid;
	navigateToNextForm("FMEAdesignView_input.fmeaf?grid=true&keyid="+keyid );
}



</script>
<form id="fmFmea">
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
		<div style="margin-top: -26px"><input type="button" class="easyui-button" id="btnfmeaView"	name="btnfmeaView" value="ViewFormat" style="height: 25px; width : 102px;"/></div>
		</td>
		<td >
			<div style="width : 332px;padding-left:10px;padding-left:0px\9;margin-top: -21px" >
				<label class="notes"> Double Click on row to input/view details </label>
			</div>
			
		</td>
	</tr>
</table>

</div>
<table id='designGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagerdesign'></div>


</div>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>