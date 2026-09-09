
<script>


	jQuery(document).ready(function()
	{	
		var mainForm = jQuery("#mainFormValClti").val();
		if (mainForm !=true) {
			jQuery(".clitdiv").attr('id', 'wrapperRpt');
		}
		var url = jQuery('#hiddenUrl').val();
		dataString="q=2";
		viewGrid(url,dataString);
		jQuery ("#btnNEW").click(function()
		{
			navigateToNextForm("I_MRControlForm_modify.IMR?grid=false","I-MR Control Chart");	
		});
		var btnName = jQuery("#hdnBtnName").val();
		jQuery("#btnView").val(btnName);
		jQuery("#btnView").click(function()
		{
			processAjaxCalls("openFile.file?fileName=I-MRControlChart.xls", "", "", "", "", "new");					
		});
	});
	function viewGrid(url,dataString)
	{
		processGridnew(url,dataString,"IMRDetailsGrid","pager"," ","docDoubleClick");
	}
	

	function docDoubleClick(id)
	{	
		var rowData = jQuery("#IMRDetailsGrid").jqGrid('getRowData',id);
		var flid=rowData.FLID;
		var prodId=rowData.PRODUCTID;
		var charId=rowData.CHARACTERISTICSID;
		navigateToNextForm("I_MRControlForm_modify.IMR?grid=true&flid="+flid+"&prodId="+prodId+"&charId="+charId );
	    
	}

</script>
<form>
	
	
    <div id="wrapper" style="width:120%;width:100%\9"class="clitdiv">
    <div style="padding-left:0%;padding-left:0%\9;width:114%;width:99%\9;margin-top: -28px"><input class="easyui-button" type="button" id="btnNEW" name="btnNew" value="New" style=" width : 49px;height:22px;">
<!--    <span style="padding-left:10px;"><input class="easyui-button" type="button" id="btnView" name="btnView" style="height:22px;" value="View Report"></span>-->
    <span style="padding-left:10px;"> Double Click on row to input/view details</span></div>
		<table id='IMRDetailsGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>
	
	</div>
	<input type="hidden" id="hdnFilterStr" value=""/>
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
	<input type="hidden" id="hdnBtnName" value="View Report"/> 
</form>