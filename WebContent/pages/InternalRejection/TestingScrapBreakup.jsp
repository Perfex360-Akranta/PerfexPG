<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
			initialiseForm('frmTestScrapBreakup');
			jQuery('#submitForm').val('frmTestScrapBreakup');
			var url = jQuery('#hiddenUrl').val();
			var referenceId = jQuery('#hdnQsbrReferencekeyid').val();
			var rejectionId = jQuery('#hdnQsbrRejectionid').val();
			var dataString = '?q=2';
			if ( referenceId != "" && referenceId != null)
				dataString += '&referenceId='+referenceId;
			if ( rejectionId != "" && rejectionId != null)
				dataString += '&rejectionId='+rejectionId;
			processGridnew(url,dataString,"testScrapGrid","","TestingScrapBreakup","","","fillBreakup");
		});
		
function fillBreakup()
{
	jQuery("#testScrapGrid").setGridParam({
		afterEditCell: function(rowid, name, value, iRow, iCol) {
			 var inputControl = jQuery('#' + (iRow) + '_' + name);				
			 inputControl.css('text-transform', 'uppercase');			
		},
		afterSaveCell : function(rowid,name,val,iRow,iCol) 
    	{
			  var qty = jQuery("#testScrapGrid").jqGrid('getCell',rowid,'txtQsbrQuantity');
			  var remarks = jQuery("#testScrapGrid").jqGrid('getCell',rowid,'txtQsbrRemarks');
			  jQuery("#testScrapGrid").jqGrid("setCell", rowid, 'txtQsbrQuantity', qty.toUpperCase());
			  jQuery("#testScrapGrid").jqGrid("setCell", rowid, 'txtQsbrRemarks', remarks.toUpperCase());				 			 
		}
	});
}
function frmTestScrapBreakup_beforeSubmit()
{
	 return 'scrapBreakup='+convertGridToJSONArr('testScrapGrid');
}
function frmTestScrapBreakup_successsCallback(result) {
	var cont=confirm("Do you want to Close Breakup?");
	if (cont==true)	 
		closePopUpDialoge("loadTestingScrap");
}
function loadTestingScrap_onClose() {
	fnFillProcessGrid();
	return true;
}
</script>
<form id="frmTestScrapBreakup" name="frmTestScrapBreakup" >

	<div> 
		 <table id="testScrapGrid" style="width:100%"><tr><td/></tr></table>
<!--		 <div id="pager"></div>-->
	</div>
	<input type="hidden" id="hdnQsbrRejectionid" name="hdnQsbrRejectionid" value="${requestScope.rejectionId}" />
	<input type="hidden" id="hdnQsbrReferencekeyid" name="hdnQsbrReferencekeyid" value="${requestScope.referenceId}" />
	<input type="hidden" id="hdnBreakupInsQty" name="hdnBreakupInsQty" value="${requestScope.inspectedQty}" />
	<input type="hidden" id="hdnBreakupQAHold" name="hdnBreakupQAHold" value="${requestScope.QAHoldQty}" />
	<input type="hidden" id="mode" name="mode"/>

</form>