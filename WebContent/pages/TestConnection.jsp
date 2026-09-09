
<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmTestConnection');
	jQuery('#submitForm').val('frmTestConnection');
});

</script>




<form id="frmTestConnection" name="frmTestConnection">
<div style="margin-left: 400px;margin-top: 40px">

 <select class="easyui-combobox" id="cmbSystem" name="cmbSystem" value=""  style="width: 300px; height: 25px;">
							<option value='SAP'>SAP</option>
							<option value='Optivision'>Optivision</option>
							<option value='QCS'>QCS</option>
							<option value='DCS'>DCS</option>
							<option value='Lotus Notes'>Lotus Notes</option>
							<option value='HRMS'>HRMS</option>
					</select>
</div>

<div style="margin-left: 400px;margin-top:20px">
<div class="easyui-paddingbfpx"><label>Server Name / Host</label></div>
 <input id="txtServername" name="txtServername" type="text" class="easyui-text" value=" " maxlength="50" style="width: 300px; }"/>

</div>

<div style="margin-left: 400px;margin-top:20px" >
<div class="easyui-paddingbfpx"><label>Port</label></div>
 <input id="txtPort" name="txtPort" type="text" class="easyui-text" value=" " maxlength="50" style="width: 100px; }"/>


</div>

<div style="margin-left: 400px;margin-top:20px">
<div class="easyui-paddingbfpx"><label>User Name</label></div>
 <input id="txtUsername" name="txtUsername" type="text" class="easyui-text" value=" " maxlength="50" style="width: 200px; }"/>

</div>

<div style="margin-left: 400px;margin-top:20px">
<div class="easyui-paddingbfpx"><label>Password</label></div>
 <input id="txtPassword" name="txtPassword" type="text" class="easyui-text" value=" " maxlength="50" style="width: 200px; }"/>

</div>

<div style="margin-top:40px;margin-left: 400px">
<input id="btntest"  name="btntest" type="button" class="easyui-button" value="Test" style="width: 150px;height: 29px;"/>
<span style="margin-left: 20px">
<input id="btnsave"  name="btnsave" type="button" class="easyui-button" value="Save" style="width: 150px;height: 29px;"/>
</span>
</div>
</form>