

<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){	
		var actionPart = jQuery('#hiddenUrl').val();	
		setLoadFormCallBackFrmId("frmMasterMenuReport");
		invokeAfterLoadFormCallBack();
		//viewGrid(actionPart, "?q=2");
	});

	function frmMasterMenuReport_afterLoadCallBack(){
		toggleCommonFilter();
	}

	function viewGrid(url, filterString) {
		if(validateFilterSelection(filterString)) {
			var tableCaption = "Master Menu Report";
			processGridnew("MstAccessRpt_input.marr", filterString, "list", "pager", tableCaption, "", "", "fillForm");
			return true;
		}
		return false;	
	}

	function validateFilterSelection(filterString){
		return true;
	} 
	
	
	
	/* function validateFilterSelection(filterString){
		// Location hidden field set once user picks a location in the "Functional Location" popup and clicks Ok
		var flid = jQuery('#hdnFlid').val();   // <-- confirm actual id, see note below

		if(flid == null || jQuery.trim(flid).length == 0){
			alert("Please select the Location");
			return false;
		}
		return true;
	} */


	function fillForm(id) {
		// Add any row click handling here if needed
	}
</script>

<form id="frmMasterMenuReport">
<div id="wrapperRpt" style="margin-top:13px;">
	<table>
		<tr>
			<td colspan="2">
				<div class="clear"></div>
				<table id="list"><tr><td></td></tr></table>
				<div id="pager"></div>
			</td>
		</tr>
	</table>
	<input type="hidden" id="hdnMasterMenuRptPrevDataUrl" name="hdnMasterMenuRptPrevDataUrl" value="${requestScope.filterStr}" />
</div>
</form>


