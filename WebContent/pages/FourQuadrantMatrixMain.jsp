<script>
jQuery(document).ready(function(){
	initialiseForm('frmFourQuadrantMain');
	
	processGridnew("fourquadrantmatrix_input.fqdm","q=2","fourQuadrantgrid","pager","","doubleClick","","load_complete");
	jQuery ("#btnView").click(function(){
		navigateToNextForm("fourquadrantmatrixtraining_input.fqdm" );
	});
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		alert("Read From File");
		
		//processAjaxCalls("openFile.file?fileName= Kaizen_Format.pdf", "", "", "", "", "new");					
	});

});
function doubleClick(id)
{	var rowData = jQuery("#fourQuadrantgrid").jqGrid('getRowData',id);
	var tcount = rowData.TrainingCount;
	var Ocount = rowData.OplCount;
	navigateToNextForm("fourquadrantmatrixtraining_input.fqdm?tcount="+tcount+"&Ocount="+Ocount );
}
</script>
<form id="frmFourQuadrantMain">
<div id="wrapperRpt">
<table>
<tr>
				<td style=" ">
					<div>
						<input class="easyui-button" id="btnView" name="btnView" style="width: 70px;height: 25px;" value="New Entry"/>
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
						<table  id='fourQuadrantgrid' >
							<tr>
								<td></td>
							</tr>
						</table>
						<div id='pager'></div>
</div>		
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>