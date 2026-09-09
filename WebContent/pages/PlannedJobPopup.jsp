 <script type="text/javascript">
	jQuery(document).ready(function(){
		initialiseForm('frmPlannedJobPopup');
		jQuery('#submitForm').val('frmPlannedJobPopup');
		var url=jQuery('#hiddenUrl').val();
		//alert(url);
		var EmpKeyid=jQuery("#hdnempkeyid").val();
  	 	processGridnew("PJOPopupUpdate_input.plnJobD","q=2&EmpKeyid="+EmpKeyid,"PJOPopupGrid","Pager","","","");
	});

	function viewGrid(url,filterString) {
		if (validateFilterSelection(filterString)) {
			processGridnew(url, filterString, "PJOPopupGrid", "Pager","","");
			return true;
		}
	}
	
	function PJOPopupGridbtnPJO_onClick(result){	
	    var rowid=result.rowId;
		var btnid=result.btnId;
	    var FollowUpKeyid=jQuery("#PJOPopupGrid").jqGrid('getCell',rowid,"PjobKeyid");
	 //   alert(FollowUpKeyid);
	 //   var TaskObserved=getFieldValue("#txtPjobTaskObserved_PJOPopupGrid_"+rowid);
	    var TaskObserved=jQuery("#PJOPopupGrid").jqGrid('getCell',rowid,"txtPjobTaskObserved");
	   // alert(TaskObserved);
	    jQuery("#txtPjobFollowuppjono").val(FollowUpKeyid);
	    jQuery("#txtPjobFollowuptaskobserved").val(TaskObserved);
	    closePopUpDialoge("divPJOFollow");
		}

	function update_successCallBack(result){
		alert(result.successData.msg);
		jQuery('#ActionPlanGrd').trigger("reloadGrid");
	}
	
	function validateFilterSelection(filterString){
		return true;
	}
	
</script>
<form id="frmPlannedJobPopup">
	<div id="wrapperRpt" style="margin-top: 10px; margin-left: 0px">
	<table>
	<tr>
<!-- 
			<td>
				<div style="padding-left:20px; vertical-align: top;margin-top:8px;"> 
				<input type="button" class="easyui-button" id="btnRefresh" name="btnRefresh" value="Refresh" style="height: 25px;" />
			</div>
			</td> -->
			  </tr>
			  </table>
		<table id="PJOPopupGrid"></table>
		<div id="Pager"></div>
	</div>
<input type="hidden" id="hdnempkeyid" name="hdnempkeyid" value="${requestScope.empkeyid}"/>	
</form>


