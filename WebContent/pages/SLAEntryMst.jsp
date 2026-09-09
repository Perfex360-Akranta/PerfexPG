
<script>
	jQuery(document).ready(
			function() {
				initialiseForm('frmSLAEntryMasterReport');				
				
				var url = "servicelevelEntryMst_input.slam";
				var hdnfrom = jQuery("#hdnfrom").val();
				var gridUrl="";
				gridUrl = url.substring(0,url.indexOf("?"));
				
				if(gridUrl.length > 21)
				{
					
					viewGrid(gridUrl,"?q=2&from="+hdnfrom);
				}
				else 
				{
					
					viewGrid(url,"?q=2&from="+hdnfrom);
				}
				
			});
	function docDoubleClick(id) {
		/*var rowData = jQuery("#SLADetGrid").jqGrid('getRowData',id);
		var frmdptid = rowData.PRODUCTID;*/
		var mode = jQuery("#hdnmode").val();		
		navigateToNextForm("servicelevelEntryDtl_input.slam?grid=true&clearfrom=false&mode="+mode);
	}

	function viewGrid(url,filterString)
	{
		processGridnew(url, filterString,"gridSLAEntryMstRpt", "pager3", "", "docDoubleClick","AlphaNumericOnly","gridLoadComplete","","");
		
	}
</script>

	<form name="frmSLAEntryMasterReport" id="frmSLAEntryMasterReport" action=" " method="post">
		<div id='wrapperRpt'>				
			<table id='gridSLAEntryMstRpt'>
			<tr>
				<td></td>
			</tr>
			
		</table>
		<div id='pager3'></div>
	</div>
	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
	<input type="hidden" id="hdnfrom" name="hdnfrom"  value="${requestScope.from}" />
	<input type="hidden" id="hdnmode" name="hdnmode"  value="${requestScope.mode}" />
</form>
