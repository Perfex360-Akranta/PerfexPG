<script type="text/javascript">	
jQuery(document).ready(function(){	
	setTimeout(function() { fillComboBox("frmAccidentIncident","cmbemployee","employee.commonFilter");},1250);
	setTimeout(function() { fillComboBox("frmAccidentIncident","cmbInjurymode","BodyPartCombo.entbatch");},1250);
	setTimeout(function() { fillComboBox("frmAccidentIncident","cmbBodyPart","InjuryCombo.entbatch");},1250);
});
</script>
<form id="frmAccidentIncident" >
	<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Employee</label></div>
	<div style="padding-bottom:0px; padding-left: 30px;" >	
		<span style="margin-left:2px;">
			<input id="cmbemployee" type="text" class="easyui-combobox"  name="cmbemployee" style="width:250px;">
		</span>
	</div>
	<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Injury mode</label></div>
	<div style="padding-bottom:0px; padding-left: 30px;" >	
		<span style="margin-left:2px;">
			<input id="cmbInjurymode" type="text" class="easyui-combobox" name="cmbInjurymode" style="width:250px;">
		</span>
	</div>
	<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Body Part</label></div>
	<div style="padding-bottom:0px; padding-left: 30px;">	
		<span style="margin-left:2px;">
			<input id="cmbBodyPart" type="text" class="easyui-combobox"  name="cmbBodyPart" style="width:250px;">
		</span>
	</div>
	<div class="easyui-paddingbfpx" style="padding-top: 30px;padding-left: 30px;">
		<span><input id="btnBatchAdd" name="btnBatchAdd" type="button" class="easyui-button"  value="Save"  style="width:50px; height:25px;"/></span>
	</div>		
</form>