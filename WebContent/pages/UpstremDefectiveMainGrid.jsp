
<script>

jQuery(document).ready(function(){	
    jQuery("#btnNEW").hide();
	var mainForm = jQuery("#mainFormValClti").val();
	if (mainForm !=true)
		 {
		
		jQuery(".clitdiv").attr('id', 'wrapperRpt');
		
	}
	processGridnew("UpstreamDefect_input.upd","?q=1","UpstreamMainGrid","pager"," ","docDoubleClick");
	jQuery ("#btnNEW").click(function()
			{
		navigateToNextForm("UpstreamDefectForm_input.upd","Upstream Defect");	
		
});
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnView").val(btnName);
	jQuery("#btnView").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=UpstreamDefects.xlsx", "", "", "", "", "new");					
	});
});

function docDoubleClick(id)
{	
	var rowData = jQuery("#UpstreamMainGrid").jqGrid('getRowData',id);
	var keyId=rowData.KEYID;
	var flId=rowData.FNLNKEYID;
    var date=rowData.UPSTREAMDATE;
    //alert(" keyId :: "+keyId);
    //navigateToNextForm("ConditionalAppraisalForm_input.condapp?new=F&flid="+flId+"&date="+date);
	navigateToNextForm("UpstreamDefectForm_input.upd?&grid=true&new=F&Date="+date+"&flId="+flId+"&keyId="+keyId,"Upstream Defect");
    
}

</script>
<form>
    <div class="clitdiv">
    <div style="margin-top: -28px"><input class="easyui-button" type="button" id="btnNEW" name="btnNew" value="New" style=" width : 49px;height:22px;">
<!--    <span style="padding-left:10px;"><input class="easyui-button" type="button" id="btnView" name="btnView"  style='height:22px' value="View Report"></span>-->
    <span style="padding-left:10px;">Double Click on row to input/view details</span>
    </div>
		<table id='UpstreamMainGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>
	
	</div>
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
	<input type="hidden" id="hdnBtnName" value="View Report"/> 
</form>