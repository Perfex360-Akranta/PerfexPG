
<script>

jQuery(document).ready(function()
 {	
	 
	var mainForm = jQuery("#mainFormValClti").val();
	   
	if (mainForm !=true) 
	{
		
		
		jQuery(".clitdiv").attr('id', 'wrapperRpt');
		
	}
	processGridnew("DmtNotebookFormat_input.dmtn","?q=1","DmtNotebookMainGrid","pager"," ","docDoubleClick","","loadComplete","","");
	jQuery ("#btnNEW").click(function()
			{
		
		     navigateToNextForm("DmtNotebookFormatForm_input.dmtn");	
		
			});
        var btnName = jQuery("#hdnBtnName").val();
	
			jQuery("#btnView").val(btnName);
			jQuery("#btnView").click(function()
			{

			//alert("Read From File");
			
			processAjaxCalls("openFile.file?fileName=DMTNotebook.xls", "", "", "", "", "new");					

	        });
	
 });

			function docDoubleClick(id)
			{	
				/*var rowData = jQuery("#UpstreamMainGrid").jqGrid('getRowData',id);
				var JHdata= rowData.JH;
				var Liquid= rowData.Liquid;
				var RawMaterial = rowData.RawMaterial;
				var defect = rowData.Defect;
				var informto = rowData.InformTo;
				var corrective = rowData.CorrectiveAction;
			
				alert("sa  "+JHdata);*/
				//alert(" Inside docDoubleClick ");
				var rowData = jQuery("#DmtNotebookMainGrid").jqGrid('getRowData',id );
				//alert(" Inside docDoubleClick "+rowData);
				var keyid = rowData.KEYID;
				//alert(" Inside docDoubleClick "+keyid);
				navigateToNextForm("DmtNotebookFormatForm_input.dmtn?&grid=true&keyid="+keyid);  

				//?&grid=true&keyId="+keyId  "jhaTlFiveAuditarea_modify.5saudit?&grid=true&keyId="+keyId,
			    
			}

</script>
<form>

    <div class="clitdiv">
    <div style="padding-left:0%;padding-left:0%\9;width:120%;100%\9;"><input class="easyui-button" type="button" id="btnNEW" name="btnNew" value="New" style=" width : 49px;height:22px;">
    <span style="padding-left:10px;"><input class="easyui-button" type="button" id="btnView" name="btnView" style=" height:22px;" value="View Report"></span>
    <span style="padding-left:10px;">Double Click on row to input/view details</span></div>
		<table id='DmtNotebookMainGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>
		<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
	
	</div>
	<input type="hidden" id="hdnBtnName" value="View Report"/> 
</form>