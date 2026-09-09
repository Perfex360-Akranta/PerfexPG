
<html>
<head>
<script>
jQuery(document).ready(function(){
	initialiseForm('frmConditionGrid');
	viewGrid("","q=2");
	
	jQuery ("#btnNew").click(function(){
		var mode = getFieldValue('hdnfrommode');
		navigateToNextForm("ConditionalAppraisalForm_input.condapp?filterButton=false&grid=false&mode="+mode);
	});	
	var btnName = jQuery("#hdnBtnName").val();
	//alert("btnName"+btnName);
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function(){
		
		processAjaxCalls("openFile.file?fileName=Condition_Appraisal.xls", "", "", "", "", "viewTemplate");		

	});

});
function viewGrid(url,filterString)
{
	var mode = getFieldValue('hdnfrommode');
	filterString+= "&type="+mode;
	//alert(filterString);
	processGridnew("ConditionalAppraisal_input.condapp",filterString,"conditionalgrid","CondGridpager","","docDoubleClick");
	return true;
	
}
function docDoubleClick(id){
	var rowData = jQuery("#conditionalgrid").jqGrid('getRowData',id);
	var date=rowData.CDATE;
	var flId=rowData.FNLNKEYID;
	var keyId=rowData.KEYID;
	
	var mode = getFieldValue('hdnfrommode');
	var ds ="?filterButton=false&new=F&keyId="+keyId+"&flid="+flId+"&date="+date+"&mode="+mode+"&type=REPORT";
	//alert(ds);
	navigateToNextForm("ConditionalAppraisalForm_input.condapp"+ds);
}
</script>
</head>
<body>
<form action="" id="frmConditionGrid" method="post">
	<div id='wrapperRpt' style="width:100%">
		<table>
			<tr>
				<td width="10%">
					<div>
						<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" />
						<!--<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style=" width : 90px;margin-left:10px;"/>-->
					</div>
				</td>
				<td align="left">
					<div>
						<label>Double Click on row to view details</label>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table id='conditionalgrid'>
						<tr>
							<td></td>
						</tr>
					</table>
					<div id='CondGridpager'></div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden"  id="hdnfrommode"	name="hdnfrommode" value="${requestScope.mode}" />
	<!--<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />-->
</form>

