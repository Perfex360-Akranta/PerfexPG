

<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){	
		var actionPart = jQuery('#hiddenUrl').val();	
		setLoadFormCallBackFrmId("frmUserAccessReviewReport");
		invokeAfterLoadFormCallBack();
		//viewGrid(actionPart, "?q=2");
	});

	function frmUserAccessReviewReport_afterLoadCallBack(){
		toggleCommonFilter();
	}

	function viewGrid(url, filterString) {
		if(validateFilterSelection(filterString)) {
			var tableCaption = "User Access Review Report";
			processGridnew("UsrAccessRpt_input.uarr", filterString, "list", "pager", tableCaption, "", "", "fillForm");
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

<form id="frmUserAccessReviewReport">
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
	<input type="hidden" id="hdnUserAccessReviewRptPrevDataUrl" name="hdnUserAccessReviewRptPrevDataUrl" value="${requestScope.filterStr}" />
</div>
</form>

