<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
	initialiseForm('frmAppSettingsForm');
	fillComboBox("frmAppSettingsForm","cmbAppsKeyid","combo_settings.conFig");
	fillComboBox("frmAppSettingsForm","cmbAppsSettingvalue","combo_AppsSettingvalue.conFig");
	fillComboBox("frmAppSettingsForm","cmbAppsType","combo_AppsType.conFig");
	fillComboBox("frmAppSettingsForm","cmbAppsTransactionmode","combo_AppsTransactionmode.conFig");
	fillComboBox("frmAppSettingsForm","cmbAppsPillar","combo_AppsPillar.conFig");
	jQuery('#submitForm').val('frmAppSettingsForm');
	jQuery('#frmAppSettingsForm .easyui-combobox').css('text-transform', 'uppercase');
    jQuery('#frmAppSettingsForm .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmAppSettingsForm textarea').css('text-transform', 'uppercase');
	
});
</script>
<form name="frmAppSettingsForm" id="frmAppSettingsForm" >
<div id="wrapper" align="center" style="margin-top:30px">
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<label> Settings </label>
</div>
<div class="easyui-paddingbfpx" style="padding-left:450px;"> 
<input id="cmbAppsKeyid" name="cmbAppsKeyid" class="easyui-combobox"  style="width:300px;" value="${requestScope.admTlAppsettings.appsKeyid}" <c:out value = "${requestScope.disableAppsKeyid == true ? 'disabled':''}"/> />
</div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<label class="mandatory-lbl">Name</label></div>
<div class="easyui-paddingbfpx" style="padding-left:450px;"><input type="text" id="txtAppsName" name="txtAppsName"  style="width:300px;" value="${requestScope.admTlAppsettings.appsName}" class="easyui-text" >
</div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<label class="mandatory-lbl">Code</label></div>
<div class="easyui-paddingbfpx" style="padding-left:450px;"><input type="text" id="txtAppsCode" name="txtAppsCode"  style="width:170px;" value="${requestScope.admTlAppsettings.appsCode}" class="easyui-text" >
</div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<span><label class="mandatory-lbl">Setting Values</label></span>
<span style="margin-left:82px"> <label>UOM</label></span>
</div>
<div class="easyui-paddingbfpx" style="padding-left:450px;" >
<span  ><input  id="cmbAppsSettingvalue" name="cmbAppsSettingvalue"  style="width:120px;" class="easyui-combobox" value="${requestScope.admTlAppsettings.appsSettingvalue}"  panelHeight="40px"/>
</span>


<span style="margin-left:43px"><input type="text" id="txtAppsUom" name="txtAppsUom"  style="width:134px;" value="${requestScope.admTlAppsettings.appsUom}" class="easyui-text" ></span>
<!--<table  style="width:245px;">
				           	<tr><td><span id="err_cboAppsSettingvalue" class="tpm-errormsg"></span></td>
				           		</tr>            
						    </table>
--></div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<span><label class="mandatory-lbl">Type</label></span>
<span style="margin-left:137px"><label class="mandatory-lbl">Mode</label></span>
</div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<span  ><input  id="cmbAppsType" name="cmbAppsType"  style="width:120px;" class="easyui-combobox" value="${requestScope.admTlAppsettings.appsType}"   panelHeight="40px"/>

</span>
<span style="margin-left:43px">
<input  id="cmbAppsTransactionmode" name="cmbAppsTransactionmode"  style="width:130px;"  class="easyui-combobox" value="${requestScope.admTlAppsettings.appsTransactionmode}"  panelHeight="40px" />

</span>
<!--<table  style="width:245px;">
				           	<tr><td><span id="err_cboAppsType" class="tpm-errormsg"></span></td>
				           		<td><span id="err_cboAppsTransactionmode" class="tpm-errormsg"></span></td></tr>            
						    </table>
--></div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<span><label class="mandatory-lbl">Order</label></span>
<span style="margin-left:130px"><label class="mandatory-lbl">Pillar</label></span>
</div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<span ><input type="text" id="txtAppsOrder" name="txtAppsOrder"  style="width:120px;" value="${requestScope.admTlAppsettings.appsOrder}" class="easyui-text" ></span>
<span style="padding-left:43px;"><input  id="cmbAppsPillar" name="cmbAppsPillar"  style="width:130px;" class="easyui-combobox"   value="${requestScope.admTlAppsettings.appsPillar}"  panelHeight="40px"/>

</span>
<!--<table  style="width:245px;">
				           	<tr><td><span id="err_txtAppsOrder" class="tpm-errormsg"></span></td>
				           		<td><span id="err_cboAppsPillar" class="tpm-errormsg"></span></td></tr>            
						    </table>
--></div>
<span id="err_txtAppsOrder" class="tpm-errormsg" style="margin-left:450px"></span>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<span><label> Remarks </label></span></div>
<div class="easyui-paddingbfpx" style="padding-left:450px;">
<textarea rows="1" cols="28" id="txtAppsRemarks" name="txtAppsRemarks" style="width:300px;">${requestScope.admTlAppsettings.appsRemarks}</textarea>

</div>
<input type="hidden" id="mode" name="mode"/>
</div>
</form>