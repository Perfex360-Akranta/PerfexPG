<script>
jQuery(document).ready(function(){
	initialiseForm('frmConditionReport');
	viewGrid("ConditionalAppraisalReport_input.condapp","?q=2");
});

function docDoubleClick(id)
{
	var rowData = jQuery("#conditionalRep").jqGrid('getRowData',id);
	var date=rowData.CDATE;
	var flId=rowData.FNLNKEYID;
	var keyId=rowData.KEYID;
	var title = rowData.CDATE + " / " + rowData.DMT + " / " + rowData.JH + "  /  " +rowData.MACH ;
	var isFormat=getFieldValue("hdnIsFormat");
	var type ="REPORT";
	if (isFormat!=null && isFormat=='Y')
		type="BLANK";
	
	var ds ="?flid="+flId+"&date="+date+"&keyId="+keyId+"&title="+title+"&isFormat="+isFormat+"&type="+type;
	window.open("ConditionalAppraisalReport_ExcelView.condapp"+ds);
}

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var isFormat=getFieldValue("hdnIsFormat");
		var type ="REPORT";
		if (isFormat!=null && isFormat=='Y')
			type="BLANK";
		filterString +="&type="+type;
		processGridnew(url,filterString,"conditionalRep","CondReppager","","docDoubleClick");
		return true;
	}
	return false;
		
}
function validateFilterSelection(filterString)
{
	/* if( ! checkFilterValueExist(filterString,"cmbLocnid"))
	 {
		if(filterString==null || filterString==''||filterString=="")
		{}
		else
		{
			alert("Select Location in Functional Location");
			return false;}
		}*/
	return true;
}
</script>
<form action="" id="frmConditionReport" method="post">
	<div id='wrapperRpt' style="width:100%">
		<table>
			<tr>
				<td>
					<div>
						<label>Double Click on row to view details</label>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table id='conditionalRep'>
						<tr>
							<td></td>
						</tr>
					</table>
					<div id='CondReppager'></div>
				</td>
			</tr>
		</table>
	</div>
	
	<input type="hidden" class="easyui-button" id="hdnIsFormat"	name="hdnIsFormat" value="${requestScope.isFormat}" />
	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>

