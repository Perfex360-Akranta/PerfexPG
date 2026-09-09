<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){
	initialiseForm('frmServiceAllocation');
	jQuery('#frmServiceAllocation .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmServiceAllocation textarea').css('text-transform', 'uppercase');	
	fillComboBox("frmServiceAllocation","cmbSepdSermKeyid","serviceIdCombo.serv"); 
	formatDateBox('dteSepdReceiveddate','dd-MMM-yyyy');
	formatDateBox('dteSepdTargetdate','dd-MMM-yyyy');
	formatDateBox('dteSepdAlloteddate','dd-MMM-yyyy');
	formatDateBox('dteSepdCompleteddate','dd-MMM-yyyy');
	fillWithCurrentDate('dteSepdReceiveddate');  
	fillWithCurrentDate('dteSepdTargetdate');
	fillWithCurrentDate('dteSepdAlloteddate');  
	fillWithCurrentDate('dteSepdCompleteddate');
	disableField('frmServiceAllocation', 'dteSepdReceiveddate');
	readOnlyFields('cmbSepdSermKeyid');  
	var serviceId= jQuery('#hdnServiceId').val();
	 jQuery('#submitForm').val('frmServiceAllocation'); 
	if(serviceId!=null){
		jQuery("#cmbSepdSermKeyid").combobox('setValue',serviceId);
	}
	if(jQuery('#hdnDtlStatus').val()=="C"){
		jQuery('#cboSepdStatus').val("C");
	}
	var status = jQuery('#cboSepdStatus').val();
	if(status=="A"){
		readOnlyFields('dteSepdCompleteddate');
		readOnlyFields('txtSpedCompletedby');
		readOnlyFields('txtSpedCompletedremarks');
	}

	
	var flag = jQuery('#hdndisableAllocFlag').val();
	
	if(flag != null && flag != ''&&  flag != ' ')
	{
		disableField('frmServiceAllocation', 'cmbSepdSermKeyid');
		disableField('frmServiceAllocation', 'txtSepdReceivedby');
		disableField('frmServiceAllocation', 'dteSepdReceiveddate');
		disableField('frmServiceAllocation', 'cboSepdStatus');
		disableField('frmServiceAllocation', 'txtSepdAllotedto');
		disableField('frmServiceAllocation', 'dteSepdAlloteddate');
		disableField('frmServiceAllocation', 'dteSepdTargetdate');		
		readOnlyFields('txtSepdReceivedremarks');	
		disableField('frmServiceAllocation', 'dteSepdCompleteddate');
		disableField('frmServiceAllocation', 'txtSpedCompletedby');		
		readOnlyFields('txtSpedCompletedremarks');	
	
	}
	
		
});

function fnStatusOnselect(){
	var status = jQuery('#cboSepdStatus').val();
	if(status=="A"){
		readOnlyFields('dteSepdCompleteddate');
		readOnlyFields('txtSpedCompletedby');
		readOnlyFields('txtSpedCompletedremarks');
	}else if(status=="C"){
		enableFields('dteSepdCompleteddate');
		enableFields('txtSpedCompletedby');
		enableFields('txtSpedCompletedremarks');
	}
}
function dteSepdReceiveddate_onSelect(date)
{              	
	compareDates('hdnApprovalDate','dteSepdReceiveddate','Received Date should not be lesser than Approval Date');
}
function dteSepdAlloteddate_onSelect(date)
{              	
	compareDates('dteSepdReceiveddate','dteSepdAlloteddate','Allocated Date should not be lesser than Received Date');
}
function dteSepdTargetdate_onSelect(date)
{              	
	compareDates('dteSepdAlloteddate','dteSepdTargetdate','Target Date should not be lesser than Allocated Date');
}
function dteSepdCompleteddate_onSelect(date)
{              	
	compareDates('dteSepdAlloteddate','dteSepdCompleteddate','Completed Date should not be lesser than Allocated Date');
}
function frmServiceAllocation_successsCallback(result)
{
	if(result.backToGrid != null && result.backToGrid != '' && result.backToGrid != ' ')
	{
		if(result.backToGrid == 'Y')
			jQuery('#hdnAfterSavePopup').val(result.backToGrid);
	}
	closePopUpDialoge('divAllocate');	
}
</script>
<form id="frmServiceAllocation">
  <div id="wrapper" style="float:center;width:900px;">
  	<table id="serviceAllocate" border="0" width="50%" style="padding-left:10px;vertical-align:top;" class="">
  		<tr>
  			<td style="vertical-align:top;">
  				<div>
		 			<label>Service</label>
		 		</div>
	 			<div class="easyui-paddingbfpx">
	 				<input id="cmbSepdSermKeyid" name="cmbSepdSermKeyid" type="text" class="easyui-combobox" style="width:350px;" value="${requestScope.newStpTlServicerequestmst.sermKeyid}" />
	 			</div>
  				<div>
		 				<label class="mandatory-lbl">Received Date</label>
		 				<label class="mandatory-lbl" style="padding-left:45px;">Status</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
						<input class="easyui-text" style=" width : 115px;" id="dteSepdReceiveddate" name="dteSepdReceiveddate" />
		 				<span style="padding-left:10px;"><select " id="cboSepdStatus" class="easyui-combobox" name="cboSepdStatus" style=" width : 150px;height:23px" required="true" onchange="fnStatusOnselect();" value="${requestScope.stpTlServiceprocessdtl.sepdStatus}">
							<option value="A"> Pending </option>
							<option value="C"> Completed </option>
						</select></span>
						<input type="hidden" id="hdnDtlStatus" name="hdnDtlStatus" value="${requestScope.stpTlServiceprocessdtl.sepdStatus}"/>
		 		</div>
		 		<div>
		 			<span id="err_dteSepdReceiveddate" class="tpm-errormsg"></span>
		 		</div>
		 		<div>
		 			<label class="mandatory-lbl">Received By</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			<input id="txtSepdReceivedby" name="txtSepdReceivedby" type="text" class="easyui-text"  style="width: 350px;" value="${requestScope.stpTlServiceprocessdtl.sepdReceivedby}" />
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			
		 		</div>
<!--		 		<div class="easyui-paddingbfpx">-->
		 			
<!--		 				-->
<!--		 		</div>-->
		 		<div>
		 			<label class="mandatory-lbl">Allocated To</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			<input id="txtSepdAllotedto" name="txtSepdAllotedto" type="text" class="easyui-text"  style="width: 350px;" value="${requestScope.stpTlServiceprocessdtl.sepdAllotedto}" />
		 		</div>
  			</td>
 				
  			<td style="padding-left:72px;">
  				<div>
  						<label class="mandatory-lbl">Allocated Date</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			<input class="easyui-text" style=" width : 115px;" id="dteSepdAlloteddate" name="dteSepdAlloteddate" value="${requestScope.stpTlServiceprocessdtl.sepdAlloteddate}"/>
		 		</div>			
  				
	 			<div>
		 			<label>Target Date</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			<input id="dteSepdTargetdate" name="dteSepdTargetdate" style="width : 115px;" value="${requestScope.stpTlServiceprocessdtl.sepdTargetdate}"/>
		 		</div>
		 		<div>
	 				<label>Remarks</label>
	 			</div>
	 			<div class="easyui-paddingbfpx">
	 				<textarea class="txtarea" rows="1" tabindex="18"style="width:400px;resize:none; height:100px;" cols="" id="txtSepdReceivedremarks" name="txtSepdReceivedremarks" >${requestScope.stpTlServiceprocessdtl.sepdReceivedremarks}</textarea>
	 			</div>
  			</td>
  		</tr>	
  	</table>
  	<div class="sub-header" style="text-align: left;width:100%;"><span>Service Completion</span></div>
  	<table border="0" width="50%" style="padding-left:10px;vertical-align:top;">
  		<tr>
  			<td style="vertical-align:top;">
  				<div>
  						<label>Completed Date</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			<input class="easyui-text" style=" width : 115px;" id="dteSepdCompleteddate" name="dteSepdCompleteddate" value="${requestScope.stpTlServiceprocessdtl.sepdCompleteddate}"/>
		 		</div>
		 		<div>
		 			<label>Completed By</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			<input id="txtSpedCompletedby" name="txtSpedCompletedby" type="text" class="easyui-text"  style="width: 350px;" value="${requestScope.stpTlServiceprocessdtl.spedCompletedby}" />
		 		</div>
		 		
  			</td>
  			<td style="padding-left:72px;">
  				<div>
	 				<label>Remarks</label>
	 			</div>
	 			<div class="easyui-paddingbfpx">
	 				<textarea class="txtarea" rows="1" tabindex="18"style="width:400px;resize:none; height:100px;" cols="" id="txtSpedCompletedremarks" name="txtSpedCompletedremarks" >${requestScope.stpTlServiceprocessdtl.spedCompletedremarks}</textarea>
	 			</div>
  			</td>
  		</tr>
  	</table>
  </div>
 <input type="hidden" id="hdnServiceId" value="${requestScope.serviceId}"/>
  <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
  <input type="hidden" id="txtSepdKeyid" name="txtSepdKeyid" value="${requestScope.stpTlServiceprocessdtl.sepdKeyid}"/>
  <input type="hidden" id="hdnApprovalDate" name="hdnApprovalDate" value="${requestScope.stpTlServicerequestmst.sermApproveddate}"/>
  <input type="hidden" id="hdndisableAllocFlag"  name="hdndisableAllocFlag" value="${requestScope.disableAllocationFields}"/>
</form>
