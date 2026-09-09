
<script type="text/javascript">
	jQuery(document).ready(function() {
		//initialiseForm('frmCtrlResReport');
		var url = jQuery("#hiddenUrl").val();
		viewGrid(url, "q=2");


		var btnName = jQuery("#hdnBtnName").val();
		//alert("btnName"+btnName);
		jQuery("#btnViewTemplate").val(btnName);
		jQuery("#btnViewTemplate").click(function(){
			
			processAjaxCalls("openFile.file?fileName= ControlRespPlan.xls", "", "", "", "", "viewTemplate");		

		});
		
	jQuery("#btnNewDet").click(function() {
	navigateToNextForm("controlandresponse_modify.conres");
	//"jhaTlFiveAuditarea_modify.5saudit","Audit Area master"
	});

	 
});
	function docDoubleClick(id) {
		var rowData = jQuery("#ctrlReslGrid").jqGrid('getRowData', id);
		var keyId = rowData.KEYID;
		//alert("keyId"+keyId);   //?&grid=true&keyId="+keyId,
		navigateToNextForm("controlandresponse_modify.conres?&grid=true&keyId="+keyId,"");
	}
	function viewGrid(url, filterString) {
		//alert("Inside"+url);
		processGridnew(url, filterString, "ctrlReslGrid", "pager", "","docDoubleClick");
	}
</script>


	<form name="frmCtrlResReport" id="frmCtrlResReport" action=" " method="post">

	<div id='wrapperRpt'>
				<div style=" padding-left:0%;margin-top: -18px">
			<!-- <input id="btnNewDet" name="btnNewDet" class="easyui-button" style="width: 75px;height:24px;"
				type="button" value="New Detail"/> -->
<!--				<span style="padding-left:10px;"><input type="button" class="easyui-button" id="btnViewTemplate" name="btnViewTemplate" value="View Report" style=" width : 90px;height:24px;"/></span>-->
				<span style="padding-left:10px;">Double Click on row to input / view details</span></div>


			<table id=ctrlReslGrid>
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager'></div>
		</div>
		 <input type="hidden" id="txtDeptKeyid"	name="txtDeptKeyid" value="${requestScope.clearfrom}" />
	     <input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
	</form>
