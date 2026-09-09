<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){		
	initialiseForm('frmServiceApproval');
	jQuery('#submitForm').val('frmServiceApproval'); 
	jQuery('#frmServiceApproval .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmServiceApproval textarea').css('text-transform', 'uppercase');
	
	var status = jQuery('#hdnApprovalStatus').val();
	if(status == null || status == ''||  status == ' ')
	{
		jQuery('#chkApproval').attr('checked',true);
		jQuery('#hdnApprovalStatus').val('S');
	}
	fillComboBox("frmServiceApproval","cmbSermApprovedby","employee.commonFilter"); 
	formatDateBox('dteSermApproveddate','dd-MMM-yyyy');	
	disableField('frmServiceApproval', 'dteSermApproveddate');
	var appDate = jQuery('#dteSermApproveddate').datebox('getValue');
	if(appDate == null || appDate == ''||  appDate == ' ')
	{
		  fillWithCurrentDate('dteSermApproveddate'); 
	}
	var flag = jQuery('#hdndisableFlag').val();
	if(flag != null && flag != ''&&  flag != ' ')
	{
		disableField('frmServiceApproval', 'cmbSermApprovedby');
		disableField('frmServiceApproval', 'dteSermApproveddate');
		disableField('frmServiceApproval', 'chkApproval');
		disableField('frmServiceApproval', 'chkCancel');
		readOnlyFields('txtSermApprovalremarks');	
	
	}
	
});
jQuery('#chkApproval').click(function() {
	jQuery('#chkApproval').attr('checked',true);
	jQuery('#chkCancel').attr('checked',false);
	jQuery('#hdnApprovalStatus').val('S');	
	jQuery('#lblApprovalRemarks').removeClass('mandatory-lbl');
});
jQuery('#chkCancel').click(function() {
	jQuery('#chkCancel').attr('checked',true);
	jQuery('#chkApproval').attr('checked',false);
	jQuery('#hdnApprovalStatus').val('R');	
	jQuery('#lblApprovalRemarks').addClass('mandatory-lbl');
});
function frmServiceApproval_successsCallback(result)
{
	if(result.backToGrid != null && result.backToGrid != '' && result.backToGrid != ' ')
	{
		if(result.backToGrid == 'Y')
			jQuery('#hdnAfterSavePopup').val(result.backToGrid);
	}
	closePopUpDialoge('divApproval');	
}
function Approval_Callback()
{
	jQuery('.loadpopuptoolbar').width(160);
}
</script>
<form id="frmServiceApproval" name="frmServiceApproval">
	<div class="easyui-paddingbfpx" style="margin-left:10px;">
		<input type="checkbox" id="chkApproval" name="chkApproval" /> <label>Approval</label>
		<input type="checkbox" id="chkCancel" name="chkCancel" /> <label>Cancel</label>
		<input type="hidden" id="hdnApprovalStatus" name="hdnApprovalStatus" />
	</div>
	<div style="margin-left:10px;">
		 <label class="mandatory-lbl">By</label>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:10px;">
		 <input id="cmbSermApprovedby" name="cmbSermApprovedby"  value="${requestScope.stpTlServicerequestmst.sermApprovedby}" type="text" class="easyui-combobox" style="width:350px;"/>
	</div>
	<div style="margin-left:10px;">
		 <label class="mandatory-lbl">Date</label>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:10px;">
		 <input class="easyui-text" style=" width : 115px;" id="dteSermApproveddate" name="dteSermApproveddate" value="${requestScope.stpTlServicerequestmst.sermApproveddate}"/>
	</div>
	<div style="margin-left:10px;">
		 <label id="lblApprovalRemarks">Remarks</label>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:10px;">
		 <textarea class="txtarea" rows="1" tabindex="18"style="width:400px;resize:none; height:100px;" cols="" id="txtSermApprovalremarks" name="txtSermApprovalremarks" >${requestScope.stpTlServicerequestmst.sermApprovalremarks}</textarea>
	</div>
	
	<input type="hidden" id="hdnSermKeyid"  name="hdnSermKeyid" value="${requestScope.serviceId}"/>
	<input type="hidden" id="hdndisableFlag"  name="hdndisableFlag" value="${requestScope.disableFields}"/>
	<input type="hidden" id="mode" name="mode"/>
</form>