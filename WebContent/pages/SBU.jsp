<script>
jQuery(document).ready(function(){
	initialiseForm('frmLine');
	jQuery("#submitForm").val("frmLine");

	formatDateBox('dtedate','dd-MMM-yyyy');   

	fillComboBox("frmLine", "cmbSBU", "sbuCombo.commonFilter");
	fillComboBox("frmLine", "cmbCompany", "companyCombo.commonFilter");
	fillComboBox("frmLine", "cmbLocation", "location.commonFilter");
	disableField("frmLine", "cmbSBU");
	disableField("frmLine", "cmbCompany");
	disableField("frmLine", "cmbLocation");
});

</script>
	
<form id="frmLine" >
<div id="wrapper" style="width:90%;">
	<div align="center">	
<table align="center">
	<tr>
							<td>
								<div class="easyui-paddingbfpx">
									<label>SBU</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input  class="easyui-combobox" style="width:350px;" id="cmbSBU" name="cmbSBU" value="${requestScope.online.olqmTopicid}"/>
								</div>
								
									<div class="easyui-paddingbfpx">
									<label class ="mandatory-lbl">Company</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input  class="easyui-combobox" style="width:350px;" id="cmbCompany" name="cmbCompany" value="${requestScope.online.olqmTopicid}"/>
								</div>
								
						
									<div class="easyui-paddingbfpx">
									<label class ="mandatory-lbl">Location</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input  class="easyui-combobox" style="width:350px;" id="cmbLocation" name="cmbLocation" value="${requestScope.online.olqmTopicid}"/>
								</div>		
								
									<div class="easyui-paddingbfpx">
									<label class ="mandatory-lbl">Name</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input id="txtFactName" name="txtFactName" type="text" class="easyui-text"  maxlength="95"  style="width: 350px; height: 21px;" value="" />
								</div>	
								
									<div class="easyui-paddingbfpx">
									<label>Code</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input id="txtFactName" name="txtFactName" type="text" class="easyui-text"  maxlength="95"  style="width: 350px; height: 21px;" value="" />
								</div>			
								
									<div class="easyui-paddingbfpx">
									<label>Remarks</label>
							</div>
								<div class="easyui-paddingbfpx">
									<textarea rows="2" cols="80" style=" width : 350px; height : 65px;" id="txtRamtWhathazards" name="txtRamtWhathazards" >${requestScope.newgenTlRiskassessment.ramtWhathazards}</textarea>
								</div>			
								
						
</td></tr>

</table>
</div>
</div>							
	</form>