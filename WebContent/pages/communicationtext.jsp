<script type="text/javascript">
jQuery(document).ready(function(){
	  var commTxtMode = jQuery('#commTxtMode').val();
	  if(commTxtMode != null && commTxtMode != '' && commTxtMode != ' ')
	  {
		if(commTxtMode=='view')
		{
			 readOnlyFields('txawcmlCommunicationtext');	
			 disableUIButton('btnInsert');			
		}
	  }
		
});
jQuery("#btnInsert").click(function(){
	var commMode = jQuery("#hdncommMode").val();	
	if(commMode != 'pm')	
	
	{
		var comBy = getComBy(commMode);		
		var woNo =  (commMode == 'bd'?jQuery('#txtbdmsWno').val():jQuery('#hdnwomsKeyid').val());	//jQuery("#cmbbdmsKeyid").combobox('getValue')
		
		if(jQuery("#txawcmlCommunicationtext").val() == null || jQuery("#txawcmlCommunicationtext").val() == '')
			showValidationErrorMsg('txawcmlCommunicationtext','Enter Communication Text');
		else if(woNo == null || woNo == '' || woNo == ' ')
			alert("Work Order No is Not Available");
		else
		{
			clearValidationErrorMsg('txawcmlCommunicationtext');			
			var dataString = '?q=2&comTxt='+jQuery("#txawcmlCommunicationtext").val()+'&comBy='+comBy+'&woNo='+woNo;		
			processAjaxCalls('comm_save.brdn',dataString,'getCom','getComErr');
		}
	}
	else{
		
		var dataString = '?q=2&comTxt='+jQuery("#txawcmlCommunicationtext").val()+'&comBy='+jQuery('#woemployeeId').val()+'&woNo='+jQuery('#workorderNo').val();		
		processAjaxCalls('comm_save.brdn',dataString,'getCom','getComErr');
		}
});
function commComplete()
{
	if(screen.width >=1250)
	{
	   jQuery("#CommnGrid").setGridWidth(775);
		//jQuery( "#yyGrid" ).jqGrid('setColProp','amount',{width:new_width});
	}
}
function getCom(result)
{	
	alert(result.successData.msg);				
	jQuery("#CommnGrid").trigger("reloadGrid");
	jQuery("#txawcmlCommunicationtext").val(''); 
}
function getComBy(mode)
{
	var comBy = '';
	if(mode == 'bd')
		comBy = jQuery("#cmbbdmsBookedby").combobox('getValue');
	else if(mode == 'WOApproval')
		comBy = jQuery("#cmbwomsRequestapprovedby").combobox('getValue');
	else if(mode == 'WOCreation')
		comBy = jQuery("#cmbwomsAcceptedby").combobox('getValue');
	else if(mode == 'WOAcceptance')
		comBy = jQuery("#cmbwomsRescheduleby").combobox('getValue');
	else if(mode == 'WOAllocation')
		comBy = jQuery("#cmballottedBy").combobox('getValue');
	else if(mode == 'WOCompletion')
		comBy = jQuery("#cmbwomsDoneby").combobox('getValue');
	else if(mode == 'ProdAcc')
		comBy = jQuery("#cmbwomsProductionby").combobox('getValue');
	
	
	return comBy;
	
}

</script>
<div class="floatleft">
      <div><label>Communication</label></div>
      <div class="easyui-paddingbfpx">
		<span style="padding-right: 20px">
            <textarea style="width : 620px; height : 70px;resize:none;text-transform:uppercase;" name="txawcmlCommunicationtext" id="txawcmlCommunicationtext" ></textarea>
        </span>  	
		<span style="padding-right: 12px"><input type="button" value="Insert" id="btnInsert" class="easyui-button"  style="width:120px;height: 22px;"/></span>
		<span id="err_txawcmlCommunicationtext" class="tpm-errormsg"></span>
      </div>
</div>
<div class="clear"></div>
<div class="floatleft">
       <table id="CommnGrid" width="400px"></table> 
</div>
<div id="CommnPager" style="float: left;"></div> 
<input type="hidden" id="hdncommMode" name="hdncommMode" value="${requestScope.commMode}"/>	
<input type="hidden" id="commTxtMode" name="commTxtMode" value="${requestScope.formModeFlag}"/>
<input type="hidden" id="workorderNo" name="workorderNo" value="${requestScope.workorderNo}"/>
<input type="hidden" id="woemployeeId" name="woemployeeId" value="${requestScope.employeeId}"/>
