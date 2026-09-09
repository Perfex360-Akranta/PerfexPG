<script>
jQuery(document).ready(function(){
	jQuery("#submitForm").val('frmResourceMapping');
	initialiseForm("frmResourceMapping");
	fillComboBox("frmResourceMapping","cmbKprlEmpmKeyid","employee.commonFilter");
	fillComboBox("frmResourceMapping","cmbKprlLeadMemb","comboleader.prpo"," ",false);
	fillComboBox("frmResourceMapping","cmbKprlRoleKeyid","roleMst.commonFilter");
	jQuery("#hdnKprlKzpmKeyid").val(jQuery("#hdnKzpmKeyid").val());
	numericTextBox("txtKprlHrsestimate");
});
function frmResourceMapping_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	cleardata();
}
function frmResourceMapping_successsCallback(result){
	cleardata();
}
function cleardata(){
  	jQuery("#cmbKprlEmpmKeyid").combobox('setValue'," ");
  	jQuery("#cmbKprlLeadMemb").combobox('setValue'," ");
  	jQuery("#cmbKprlRoleKeyid").combobox('setValue'," ");
  	jQuery("#txtKprlHrsestimate").val(" ");
}
</script>
<form id="frmResourceMapping">
<div style="margin-top: 20px;" align="center">
<table>
	<tr>
		<td style="padding-top:10px;">
			<div><label class="mandatory-lbl">Resource</label></div>
			<div>
				<input class="easyui-combobox"  style="width:250px; height:21px;text-transform: uppercase;"  id="cmbKprlEmpmKeyid"   name="cmbKprlEmpmKeyid" value="${requestScope.resource.kprlEmpmKeyid}"size="15"/>
			</div>
		</td>
		<td style="padding-top:10px;padding-left:30px;">
			<div><label class="mandatory-lbl">Leader/Member</label></div>
			<div>
				<input class="easyui-combobox"  style="width:250px; height:21px;text-transform: uppercase;"  id="cmbKprlLeadMemb"   name="cmbKprlLeadMemb" value="${requestScope.resource.kprlLeadMemb}"size="15"/>
			</div>
		</td>
	</tr>
	<tr>
		<td style="padding-top:10px;">
			<div><label class="mandatory-lbl">Role in Project</label></div>
			<div>
				<input class="easyui-combobox"  style="width:250px; height:21px;text-transform: uppercase;"  id="cmbKprlRoleKeyid"   name="cmbKprlRoleKeyid" value="${requestScope.resource.kprlRoleKeyid}" size="15"/>
			</div>
		</td>
		<td style="padding-top:10px;padding-left:30px;">
			<div ><label class="mandatory-lbl">Hrs Estimate</label></div>
			<div >
				<input class="easyui-text" maxlength="3"  style="width:100px; height:21px;text-transform: uppercase;text-align: right;"  id="txtKprlHrsestimate"   name="txtKprlHrsestimate" value="${requestScope.resource.kprlHrsestimate}"size="15"/>
			</div>
		</td>
	</tr>
</table>
</div>
<input type="hidden" name="mode" id="mode"/>
<input type="hidden" id="hdnKprlKeyid" name="hdnKprlKeyid" value="${requestScope.resource.kprlKeyid}"/>
<input type="hidden" id="hdnKprlKzpmKeyid" name="hdnKprlKzpmKeyid" value="${requestScope.resource.kprlKzpmKeyid}"/>
</form>