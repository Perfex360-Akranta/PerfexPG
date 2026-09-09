<script>
jQuery(document).ready(function(){
	initialiseForm('frmKaizenBankPop');
	jQuery('#submitForm').val('frmKaizenBankPop');
	var frmType=jQuery("#hdnType").val();
	jQuery("#targetdate").hide();
	jQuery("#responsibility").hide();
	jQuery("#chkAccRej").hide();
	if(frmType=="A"){
		jQuery("#chkA").attr('checked',true);
		jQuery("#targetdate").show();
		jQuery("#responsibility").show();
		//jQuery("#hdnKzbnStatus").val("A");
		jQuery("#hdnKzbnStatus").val("C");
		frmTypeEnable("AccRej","AccRejRemarks","Accepted By","Accepted On","AccDate");
		jQuery("#chkAccRej").show();
		fillComboBox("frmKaizenBankPop", "cmbKzbnAcrejby", "employee.commonFilter");
		fillComboBox("frmKaizenBankPop", "cmbKzbnResponsibility", "employee.commonFilter");
		formatDateBox('dteKzbnTargetdate','dd-MMM-yyyy');
		formatDateBox('dteKzbnAcceptrejon','dd-MMM-yyyy');
		fillWithCurrentDate("dteKzbnAcceptrejon");
	}
	else if(frmType=="I"){
		jQuery("#hdnKzbnStatus").val("I");
		frmTypeEnable("Implement","ImplementRemarks",'Implemented By','Implemented On',"ImpDate");
		fillComboBox("frmKaizenBankPop", "cmbKzbnImplementedby", "employee.commonFilter");
		formatDateBox('dteKzbnImplementedon','dd-MMM-yyyy');
		fillWithCurrentDate("dteKzbnImplementedon");
	}
	else if(frmType=="C"){
		jQuery("#hdnKzbnStatus").val("C");
		frmTypeEnable("Complete","CompleteRemarks",'Completed By','Completed On',"CompDate");
		fillComboBox("frmKaizenBankPop", "cmbKzbnCompletedby", "employee.commonFilter");
		formatDateBox('dteKzbnCompletedon','dd-MMM-yyyy');
		fillWithCurrentDate("dteKzbnCompletedon");
	}
	else if(frmType=="V"){
		jQuery("#hdnKzbnStatus").val("V");
		frmTypeEnable("Verified","VerifyRemarks",'Verified By','Verified On',"VerDate");
		fillComboBox("frmKaizenBankPop", "cmbKzbnVerifiedby", "employee.commonFilter");
		formatDateBox('dteKzbnVerifiedon','dd-MMM-yyyy');
		fillWithCurrentDate("dteKzbnVerifiedon");
	}
	jQuery('#chkA').click(function(){
		if(jQuery("#chkA").is(':checked') == true)		
		{
			jQuery("#chkR").attr('checked',false);
			//jQuery("#hdnKzbnStatus").val("A");
			jQuery("#hdnKzbnStatus").val("C");
			jQuery('#typelbl').html('Accepted By');
			jQuery('#lbldate').html('Accepted On');
			jQuery("#chkA").attr('checked',true);
			jQuery("#targetdate").show();
			jQuery("#responsibility").show();
		}else{
			jQuery("#chkR").attr('checked',true);
			jQuery("#hdnKzbnStatus").val("R");
			jQuery('#typelbl').html('Rejected By');
			jQuery('#lbldate').html('Rejected On');
			jQuery("#targetdate").hide();
			jQuery("#responsibility").hide();
		}
	 });
	jQuery('#chkR').click(function(){
		if(jQuery("#chkR").is(':checked') == true)		
		{
			jQuery("#chkA").attr('checked',false);
			jQuery('#typelbl').html('Rejected By');
			jQuery('#lbldate').html('Rejected On');
			jQuery("#hdnKzbnStatus").val("R");
			jQuery("#targetdate").hide();
			jQuery("#responsibility").hide();
		}else{
			jQuery("#chkA").attr('checked',true);
			jQuery('#typelbl').html('Accepted By');
			jQuery('#lbldate').html('Accepted On');
			//jQuery("#hdnKzbnStatus").val("A");
			jQuery("#hdnKzbnStatus").val("C");
			jQuery("#targetdate").show();
			jQuery("#responsibility").show();
		}
		
	 });
});

function frmKaizenBankPop_beforeSubmit()
{
	  var status=jQuery("#hdnKzbnStatus").val();
	  if(status=='R'){
		  var rejBy=getFieldValue("cmbKzbnAcrejby");
		  var rejOn=getFieldValue("dteKzbnAcceptrejon");
		  if(rejOn=="" || rejOn==null){
			  showValidationErrorMsg("dteKzbnAcceptrejon","Select Rejected on");
			  if(rejBy=="" || rejBy==null)
				  showValidationErrorMsg("cmbKzbnAcrejby","Select Rejected by");
			  return false;
		  }
		  else if(rejBy=="" || rejBy==null){
			  showValidationErrorMsg("cmbKzbnAcrejby","Select Rejected by");
			  if(rejOn=="" || rejOn==null)
				  showValidationErrorMsg("dteKzbnAcceptrejon","Select Rejected on");
			  return false;
		  }else{
				clearValidationErrorMsg('dteKzbnAcceptrejon');
				clearValidationErrorMsg('cmbKzbnAcrejby');	
				return true;
		  }
	  	 
	  }
}
function frmKaizenBankPop_successsCallback(result) 	{
	setTimeout(function(){
		closePopUpDialoge("divKaizenBankPop");
	},100);
		
}
function frmTypeEnable(cmbtype,remarkstype,lblBy,lbldate,dateType){
	jQuery("#AccRej").hide();
	jQuery("#Implement").hide();
	jQuery("#Complete").hide();
	jQuery("#Verified").hide();
	jQuery("#"+cmbtype).show();
	jQuery("#AccRejRemarks").hide();
	jQuery("#ImplementRemarks").hide();
	jQuery("#CompleteRemarks").hide();
	jQuery("#VerifyRemarks").hide();
	jQuery("#"+remarkstype).show();
	jQuery("#AccDate").hide();
	jQuery("#ImpDate").hide();
	jQuery("#CompDate").hide();
	jQuery("#VerDate").hide();
	jQuery("#"+dateType).show();
	jQuery('#typelbl').html(lblBy);
	jQuery('#lbldate').html(lbldate);
}
</script>
<form id="frmKaizenBankPop" name="frmKaizenBankPop">
	<div>
		<table style="padding-left:2%;margin-top:5%;">
			<tr>
				<td>
					<div class="easyui-paddingbfpx"><label id="lbldate" class="mandatory-lbl">Accepted on</label></div>
					<div id="AccDate">
						<div class="easyui-paddingbfpx">
							<input id="dteKzbnAcceptrejon"  name="dteKzbnAcceptrejon" clear="false" class="easyui-datebox" value="${requestScope.kaizenbank.kzbnAcceptrejon}"  style="width:110px;height:21px;"  />
						</div>
					</div>
					<div id="ImpDate">
						<div class="easyui-paddingbfpx">
							<input id="dteKzbnImplementedon"  name="dteKzbnImplementedon" clear="false" class="easyui-datebox" value="${requestScope.kaizenbank.kzbnImplementedon}"  style="width:110px;height:21px;"  />
						</div>
					</div>
					<div id="CompDate">
						<div class="easyui-paddingbfpx">
							<input id="dteKzbnCompletedon"  name="dteKzbnCompletedon" clear="false" class="easyui-datebox" value="${requestScope.kaizenbank.kzbnCompletedon}"  style="width:110px;height:21px;"  />
						</div>
					</div>
					<div id="VerDate">
						<div class="easyui-paddingbfpx">
							<input id="dteKzbnVerifiedon"  name="dteKzbnVerifiedon" clear="false" class="easyui-datebox" value="${requestScope.kaizenbank.kzbnVerifiedon}"  style="width:110px;height:21px;"  />
						</div>
					</div>
				</td>
				<td valign="top">
					<div id="chkAccRej" style="margin-top:15px;">
						<div class="easyui-paddingbfpx" style="border:1px solid;width:95%;padding-left:5px;">
							<span style="margin-top:5px;"><input type="checkbox" id="chkA" name="chkA"/><label style="padding-left:5px;">Accept</label></span>
							<span><input type="checkbox" id="chkR" name="chkR"/><label style="padding-left:5px;">Reject</label></span>
						</div>
					</div>
				</td>
				<td style="padding-left:5%;">
					<div id="targetdate">
						<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Target Date </label></div>
						<div class="easyui-paddingbfpx">
							<input id="dteKzbnTargetdate"  name="dteKzbnTargetdate" clear="false" class="easyui-datebox" value="${requestScope.kaizenbank.kzbnTargetdate }"  style="width:110px;height:21px;"  />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="width:250px" id="err_dteKzbnAcceptrejon" class="tpm-errormsg" ></div>
				</td>
				<td colspan="2">
					<div style="margin-left:30px" id="err_dteKzbnTargetdate" class="tpm-errormsg"></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					
					<div  class="easyui-paddingbfpx"><label id="typelbl"  class="mandatory-lbl">Accepted By</label></div>
					<div id="AccRej">
						<div class="easyui-paddingbfpx">
							<input class="easyui-combobox" id="cmbKzbnAcrejby" name="cmbKzbnAcrejby" style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnAcrejby}"/>
						</div>
					</div>
					<div id="Implement">
						<div class="easyui-paddingbfpx">
							<input class="easyui-combobox" id="cmbKzbnImplementedby" name="cmbKzbnImplementedby" style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnImplementedby}"/>
						</div>
					</div>
					<div id="Complete">
						<div class="easyui-paddingbfpx">
							<input class="easyui-combobox" id="cmbKzbnCompletedby" name="cmbKzbnCompletedby" style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnCompletedby }"/>
						</div>
					</div>
					<div id="Verified">
						<div class="easyui-paddingbfpx">
							<input class="easyui-combobox" id="cmbKzbnVerifiedby" name="cmbKzbnVerifiedby" style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnVerifiedby}"/>
						</div>
					</div>
				</td>
				<td style="padding-left:5%;">
					<div id="responsibility">
						<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Responsibility</label></div>
						<div class="easyui-paddingbfpx">
							<input class="easyui-combobox" id="cmbKzbnResponsibility" name="cmbKzbnResponsibility" style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnResponsibility }"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					
					<div class="easyui-paddingbfpx"><label>Remarks</label></div>
				   <div id="AccRejRemarks">
                    	<div class="easyui-paddingbfpx">
							<textarea rows="2" cols="90" style="width: 250px; height : 65px;" id="txtKzbnAccrejremarks" name="txtKzbnAccrejremarks" >${requestScope.kaizenbank.kzbnAccrejremarks}</textarea>
						</div>
					</div>
					<div id="ImplementRemarks">
						<div class="easyui-paddingbfpx">
							<textarea rows="2" cols="90" style="width: 250px; height : 65px;" id="txtKzbnImpremarks" name="txtKzbnImpremarks" >${requestScope.kaizenbank.kzbnAccrejremarks}</textarea>
						</div>
					</div>
					<div id="CompleteRemarks">
						<div class="easyui-paddingbfpx">
							<textarea rows="2" cols="90" style="width: 250px; height : 65px;" id="txtKzbnCompremarks" name="txtKzbnCompremarks" >${requestScope.kaizenbank.kzbnAccrejremarks}</textarea>
						</div>
					</div>
					<div id="VerifyRemarks">
						<div class="easyui-paddingbfpx">
							<textarea rows="2" cols="90" style="width: 250px; height : 65px;" id="txtKzbnVerifyremarks" name="txtKzbnVerifyremarks" >${requestScope.kaizenbank.kzbnAccrejremarks}</textarea>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="hdnType" name="hdnType" value="${requestScope.frmType}"/>
	<input type="hidden" id="hdnKzbnStatus" name="hdnKzbnStatus" />
	<input type="hidden" id="mode"/>
</form>