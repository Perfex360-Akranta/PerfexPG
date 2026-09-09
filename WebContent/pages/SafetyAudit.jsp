<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!-- Created By Dhanalakshmi.R -->
<script type="text/javascript">
	jQuery(document).ready(function()
	{
		/*-------------Form Initaialisation------------------------------------------*/
		initialiseForm('frmSafetyAudit');
		jQuery('#submitForm').val('frmSafetyAudit'); 
		/*-------------Formatting Controls------------------------------------------*/
		numericTextBox('txtSaumUnit');
		formatDateBox('dteSaumNextduedate','dd-MMM-yyyy');
		formatDateBox('dteSaumEfectivedate','dd-MMM-yyyy');
		jQuery("#txtSaumAuditnumber").attr("disabled","disabled");
		jQuery('#frmSafetyAudit textarea').css('text-transform', 'uppercase');
		jQuery('#frmSafetyAudit .easyui-text').css('text-transform', 'uppercase');
		jQuery('#frmSafetyAudit .easyui-combobox').css('text-transform', 'uppercase');
		/*----------------------Load Functional Location----------------------------*/	
		var dataStr = getFnLocnValues();
		loadFunctionalLocation("SafetyAuditfunLocation","functionalLoc.SafetyAudit","SafetyAuditfunLocationValues","frmSafetyAudit",dataStr);
		/*----------------------Filling Combo Boxes---------------------------------*/
		fillComboBox("frmSafetyAudit","cmbSaumFrequency","frequencyCombo.SafetyAudit");
		fillComboBox("frmSafetyAudit","cmbSaumPreparedby","employee.commonFilter");	
	});
	function getFnLocnValues()
	{
		var companyId =getCompany("frmSafetyAudit");
		var locnId=getLocation("frmSafetyAudit");
		var factId = getFactory("frmSafetyAudit");
		var sectionId = getSection("frmSafetyAudit");
		var cellId = getCell("frmSafetyAudit");
		var machineId=getMachine("frmSafetyAudit");	
		var dataStr = "&compId="+companyId+"&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId;
		if(machineId.trim().length>0)
		{
			dataStr+="&machId="+machineId;
		} 
		return dataStr;   
	}
	
	function getCompany(frmId)
	{
		var companyId = jQuery("#"+frmId+ "input[id='company']").val();
		return companyId;
	}
	function getLocation(frmId)
	{
		var locnId=jQuery("#"+frmId+" input[id='location']").val();
		return locnId;
	}
	function getFactory(frmId)
	{
		var factId=jQuery("#"+frmId+" input[id='factory']").val();
		return factId;
	}
	function getSection(frmId)
	{
		var sectionId = jQuery("#"+frmId+" input[id='section']").val();
		return sectionId;
	}
	function getCell(frmId)
	{
		var cellId = jQuery("#"+frmId+" input[id='cell']").val();
		return cellId;
	}
	function getMachine(frmId)
	{
		var machineId=jQuery("#"+frmId+" input[id='machine']").val();
		return machineId;
	}
	function frmSafetyAudit_successCallBack(result)
	{
	}
	function frmSafetyAudit_deleteSuccessCallback(result)
	{
	}
</script>
<form name="frmSafetyAudit" id="frmSafetyAudit" >
	<div class="easyui-paddingbfpx" style="margin-left:275px;">
		<label >Audit Number</label>
	</div> 
	<div class="easyui-paddingbfpx" style="margin-left:275px;"> 
		<input type="text" id="txtSaumAuditnumber" name="txtSaumAuditnumber" class="easyui-text"  style="width:255px;" value="${requestScope.sheTlAuditmst.saumAuditnumber}"  >
	</div>
	<div  id="frmSafetyAuditFuntKeyIds"  >
		<input type="hidden" id="location" name="cmbSaumLocationid" value="${requestScope.sheTlAuditmst.saumLocationid}"/>
		<input type="hidden" id="factory" name="cmbSaumFactoryid" value="${requestScope.sheTlAuditmst.saumFactoryid}" / >
		<input type="hidden" id="section" name="cmbSaumSectionid" value="${requestScope.sheTlAuditmst.saumSectionid}" / >
		<input type="hidden" id="cell" name="cmbSaumCellid" value="${requestScope.sheTlAuditmst.saumCellid}" / >
		<input type="hidden" id="machine" name="cmbSaumMachineid" value="${requestScope.sheTlAuditmst.saumMachineid}" / >
		<div id="SafetyAuditfunLocation" style="width:790px;margin-left:274px;"></div>
	</div>
	<div align="center">
		<table>
			<tr>
				<td>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						 <label class="mandatory-lbl" > Audit Points </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						 <textarea rows="3" cols="28" id="txtSaumAuditpoints" name="txtSaumAuditpoints" style="width:300px;" >${requestScope.sheTlAuditmst.saumAuditpoints}</textarea>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						<span><label class="mandatory-lbl">Frequency </label></span>
						<span style="margin-left:100px;"><label >Unit </label></span>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						<span><input type="text" id="cmbSaumFrequency" name="cmbSaumFrequency"  style="width:142px;" value="${requestScope.sheTlAuditmst.saumFrequency}" class="easyui-text" ></span>
						<span style="margin-left:16px;"><input type="text" id="txtSaumUnit" name="txtSaumUnit" class="easyui-text"  style="width:138px;" value="${requestScope.sheTlAuditmst.saumUnit}"  ></span>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						<label>How Much </label>
					</div> 
					<div  style="padding-left:40px;"> 
						<input type="text" id="txtSaumHowmuch" name="txtSaumHowmuch" class="easyui-text"  style="width:142px;" value="${requestScope.sheTlAuditmst.saumHowmuch}"  >
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						<span><label class="mandatory-lbl">Effective Date </label></span>
						<span style="margin-left:81px;"><label class="mandatory-lbl">Next Due date </label></span>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						<span><input id="dteSaumEfectivedate" name="dteSaumEfectivedate" type="text" class="easyui-combobox" style="width: 142px;" value="${requestScope.sheTlAuditmst.saumEfectivedate}" /></span>
						<span style="margin-left:16px;" ><input id="dteSaumNextduedate" name="dteSaumNextduedate" type="text" class="easyui-combobox" style="width:139px;" value="${requestScope.sheTlAuditmst.saumNextduedate}" /></span>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						 <label> Location </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						 <textarea rows="3" cols="28" id="txtSaumLocation" name="txtSaumLocation" style="width:300px;" >${requestScope.sheTlAuditmst.saumLocation}</textarea>
					</div>
				</td>
				<td>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						 <label> Method </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						 <textarea rows="3" cols="28" id="txtSaumMethod" name="txtSaumMethod" style="width:300px;">${requestScope.sheTlAuditmst.saumMethod}</textarea>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						 <label> Requirements </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						 <textarea rows="3" cols="28" id="txtSaumRequirements" name="txtSaumRequirements" style="width:300px;" >${requestScope.sheTlAuditmst.saumRequirements}</textarea>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						 <label> Standard </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						 <textarea rows="3" cols="28" id="txtSaumStandard" name="txtSaumStandard" style="width:300px;">${requestScope.sheTlAuditmst.saumStandard}</textarea>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;">
						 <label class="mandatory-lbl">Prepared By </label>
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:40px;"> 
						 <input type="text" id="cmbSaumPreparedby" name="cmbSaumPreparedby"  style="width:300px;" value="${requestScope.sheTlAuditmst.saumPreparedby}" class="easyui-text" >
					</div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="mode" name="mode"/>
</form>