<script>
jQuery(document).ready(function(){
	initialiseForm('frmVisualControlCheck');
	jQuery ("#btnNew").click(function(){	
		navigateToNextForm("VisualControlChart_input.visc?grid=false"+"&filterButton=false","");
	});	
	
	viewGrid('VisualControlCheckList_input.visc', '?q=2'); 
	/*var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=VisualControlChecklist.doc", "", "", "", "", "new");					
	}); */
});

function viewGrid(url, filterStr) {
	processGridnew('VisualControlCheckList_input.visc',filterStr,"visualcontrolgrid","pagervisual","","doubleClickVisual","","","");
	return true;
	
}
function doubleClickVisual(id)
{	

	var rowData = jQuery("#visualcontrolgrid").jqGrid('getRowData',id);	
	//6-feb
	var keyId = rowData.Keyid;
	//alert(keyId);
	navigateToNextForm("VisualControlChart_input.visc?&keyid="+keyId+"&filterButton=false","");
	}
</script>
<form id ='frmVisualControlCheck'>
<div id='wrapperRpt'>
<div>
<table>
	<tr>
	<td >
			<div style="padding-left:0%;padding-left:0%\9;margin-top: -28px ">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div>
		</td>
		
		
		
	</tr>
</table>
</div>
	<table id ='visualcontrolgrid'>
		<tr>
			<td/>
		</tr>
	</table>
	<div id='pagervisual'></div>
</div>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>