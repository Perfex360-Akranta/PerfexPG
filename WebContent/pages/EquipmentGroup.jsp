<!-- Created By :Siddharth.A -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">

jQuery.noConflict();
	jQuery(document).ready(function(){
		initialiseForm('frmEqpGroup');	
		jQuery('#submitForm').val('frmEqpGroup'); // set the id of form to submit

		jQuery('#frmEqpGroup textarea').css('text-transform', 'uppercase');
		fillComboBox("frmEqpGroup","cmbeqgmKeyid","equipmentgroup.eqg");
		var eqgmKeyId= jQuery('#cmbeqgmKeyid').combobox('getValue');
		viewGrid(eqgmKeyId);

		if(jQuery("#hdneqgFctId").val().trim()!=null && jQuery("#hdneqgFctId").val().trim()!="")
		{
		//	jQuery("#cmbeqgmFactoryid").combobox("setValue",jQuery("#hdneqgFctId").val());
			jQuery("#cmbeqgmFactoryid").combobox("disable");
		}
		
});

	function eqpgrpFnLocnSuccessCallback(result)
	{}

	function frmEqpGroup_successsCallback(result){
		jQuery('#EquipmentGrp').trigger("reloadGrid");
		
	}

	function frmEqpGroup_beforeSubmit(){
			
	}

	function frmEqpGroup_exceptionCallback(result)
	{
		if(result.eqgmDup!=null && result.eqgmDup.trim()!="")
			alert(result.eqgmDup);
		
	}
	function frmEqpGroup_errorCallback(result){
	}
	
	function frmEqpGroup_deleteSuccessCallback(result)
	{	
		var t=confirm("Record is refered, can not delete\! Do you want to make inactive? ");
		if(t)
		{			
			checkConfirm();
		}	
	}

	function checkConfirm()
	{
		jQuery('#hdnInactive').val("Inactive");	
		saveForm("frmEqpGroup","equipmentGroup_delete.eqg");
	}
	
	function frmEqpGroup_beforeDelete()	{}
	
	function eqpgrpFnLocnOnError(){}
	
	function frmEqpGroupcmbeqgmKeyid_onLoadSuccess(){
		fillComboBox("frmEqpGroup","cmbeqgmFactoryid","factroyCombo.commonFilter" );
	}
	
	function frmEqpGroupcmbeqgmFactoryid_onLoadSuccess(){
		fillComboBox("frmEqpGroup","cmbeqgmMaingroupid","eqpsubgroup.eqg?grpType=MGP" );
	}
	
	function frmEqpGroupcmbeqgmMaingroupid_onLoadSuccess(){
		fillComboBox("frmEqpGroup","cmbeqgmSubgroupid","eqpsubgroup.eqg?grpType=SGP" );
	}
	function frmEqpGroupcmbeqgmSubgroupid_onLoadSuccess(){
		//fillComboBox("frmEqpGroup","cmbkznmMachineid","machineCombo.commonFilter" );
	}

	function frmEqpGroupcmbeqgmKeyid_onSelect(record){
		processAjaxCalls("equipmentGroup_recall.eqg","eqpId="+record.id,"frmEqpGroup_RecallsuccessCallback","frmEqpGroup_RecallerrorCallback");
		viewGrid(record.id);
	}

	function frmEqpGroup_RecallsuccessCallback(result)
	{
		jQuery("#cmbeqgmSubgroupid").combobox("setValue",result.eqpdata.EqgmSubgroupid);
  		jQuery("#cmbeqgmMaingroupid").combobox("setValue",result.eqpdata.EqgmMaingroupid);//EqgmKeyid
  		jQuery("#cmbeqgmFactoryid").combobox("setValue",result.eqpdata.EqgmFactoryid);
  		jQuery("#txteqgmRemarks").val(result.eqpdata.EqgmRemarks);
  		jQuery('#hdneqgmName').val(result.eqpdata.EqgmName);
}
	
	function frmEqpGroup_RecallerrorCallback(result)	{}

	function frmEqpGroupcmbeqgmMaingroupid_onClear()	{}

	function viewGrid(dataValues)
	{
		processGridnew("equipmentGroupGrid_input.eqg","?eqgmKeyId="+dataValues,"EquipmentGrp","pager");
	}

function actionFormatter(cellvalue, options, rowObject) {
	var id = options.rowId;
	var path="";
	if(rowObject[3]=="EQP")
		path="images/FnLocn/machine.jpg";
	if(rowObject[3]=="ASM")
		path="images/FnLocn/assembly.jpg";
	if(rowObject[3]=="SAB")
		path="images/FnLocn/sub-assembly.jpg";
	if(rowObject[3]=="SPR")
		path="images/FnLocn/spares.jpg";
	return '<img src="images/menu-icon/imgpluse.png" name="my_picture"  id="my_picture"  style="width:26px;"   ' + ' onclick="linkOnClick(\''+ id + '\');" />';
	
	//return '<input type="button"  class="easyui-button" name="my_button"  id="my_button" value="ADD LINK" style="width:30px;"   ' + ' onclick="linkOnClick(\''+ id + '\');" />';
}

function linkOnClick(id)
{
	var rowIden=jQuery('#EquipmentGrp').getCell(id,'rowIden');
	var eqpgmKeyid=jQuery('#cmbeqgmKeyid').combobox('getValue');
	var eqpGrpId= jQuery('#cmbeqgmKeyid').combobox('getValue');
	var eqpMainGrpId=jQuery('#cmbeqgmMaingroupid').combobox('getValue');
	var factId=jQuery('#cmbeqgmFactoryid').combobox('getValue');
	var subgrpId=jQuery('#cmbeqgmSubgroupid').combobox("getText");
	if(eqpGrpId==null || eqpGrpId.trim()=="")
	{
		alert("Select Equipment Group");
		return false;
	}
	else if(eqpMainGrpId==null || eqpMainGrpId=="")
	{
		alert("Select MainGroup");
		return false;
	}		
	else if(rowIden=="EQP")
	{
	
		//navigateToNextForm("eqpgroupLink_input.eqg?formField=EQP&eqpgmKeyid="+eqpgmKeyid+"&factId="+factId);
		LoadPopUp("","eqpgroupLink_fillData.eqg?formField=EQP&eqpgmKeyid="+eqpgmKeyid+"&factId="+factId,true,"62%","85%","2%","15%");
		
	}
	else if(rowIden=="ASM")
	{
		navigateToNextForm("eqpgroupFnlocn_fillData.eqg?parentId="+factId+"&originalId="+eqpGrpId+"&eqpSubgrpId="+subgrpId+"&formId=frmEqpgrpTree");
		//eqpGrpLnkPopUp("eqpgroupLink_input.eqg?linkType=ASM&eqpgmKeyid="+eqpgmKeyid,"eqpgmKeyid="+eqpgmKeyid,"EquipmentGrpLnk","","","true","MultiSelectCancel_CallBack","ASMultiSelectOk_CallBack");
		//jQuery('#hdnformField').val("EQGASM");
		//funcnLocnPopUp("eqpgroupLink_input.eqg",eqpgmKeyid,"list","","","true","MultiSelectCancel_CallBack");	
		
		//LoadPopUp("","eqpgroupLink_fillData.eqg?formField=ASM&eqpgmKeyid="+eqpgmKeyid,true,"1200px","400px","10px","10px");
		//assmMultiSelect();
		//multiSelectPop("eqpAssmblyData_input.eqg","eqpgmKeyid="+eqpgmKeyid,"EquipmentGrp",id,"tick,name","true","multiSelectCancel_Callback","multiSelectSaveOk_Callback");
		//multiSelectSavePop("eqpAssmblyData_input.eqg","eqpgmKeyid="+eqpgmKeyid,"EquipmentGrp",id,"tick,name","true","multiSelectCancel_Callback","multiSelectSaveOk_Callback");
	}	
	else if(rowIden=="SAB")
	{
		navigateToNextForm("eqpgroupFnlocn_fillData.eqg?linkType=SAM&eqpgmKeyid="+eqpgmKeyid+"&formId=frmEqpgrpTree");
	}
	else if(rowIden=="BDPHM")
	{
		navigateToNextForm("eqpgroupFnlocn_fillData.eqg?parentId="+factId+"&originalId="+eqpgmKeyid+"&eqpSubgrpId="+subgrpId+"&formId=frmEqpgrpTree");
	}			
	else if(rowIden=="CAS")
	{
		navigateToNextForm("eqpgroupFnlocn_fillData.eqg?parentId="+factId+"&originalId="+eqpgmKeyid+"&eqpSubgrpId="+subgrpId+"&formId=frmEqpgrpTree");
	}			
	else if(rowIden=="PM")
	{
		//navigateToNextForm("eqpgroupFnlocn_fillData.eqg?linkType=PM&eqpgmKeyid="+eqpgmKeyid+"&formId=frmEqpgrpTree");
		navigateToNextForm("eqpGroupMain_input.eqpStd?linkType=PM&cmbEqpGrpid="+eqpgmKeyid);
	}	
	else if(rowIden=="SPR")
	{
		navigateToNextForm("eqpgroupFnlocn_fillData.eqg?parentId="+factId+"&originalId="+eqpgmKeyid+"&eqpSubgrpId="+subgrpId+"&formId=frmEqpgrpTree");
	}
	else if(rowIden=="ALM")
	{
		LoadPopUp("","eqpgroupLink_fillData.eqg?formField=ALM&eqpgmKeyid="+eqpgmKeyid,true,"62%","85%","2%","15%");
		
		//navigateToNextForm("eqpgroupLink_fillData.eqg?formField=ALM&eqpgmKeyid="+eqpgmKeyid);
	}		
}

function multiSelectOk_Callback(args)
{
	//alert("Oked");
	//jQuery('#hdnchildType').val('')	
}
function MultiSelectCancel_CallBack(id)
{//alert("closedd");
//	jQuery('#hdnchildType').val('')
	jQuery('#multiselectPopUpId').dialog('close');
}

function LoadPopUpDiv_onClose()
{
	setTimeout(function() {jQuery('#EquipmentGrp').trigger("reloadGrid");},100);
	return true;
}
</script>
<form id="frmEqpGroup" name="frmEqpGroup">
<div id="wrapper" style="width:92%;">
<div align="center" class="main-cntborder" style="height:90%;">
<div style="padding-left: 18%; margin-top:12px;" >
<table width="100%" align="center">
<tr>
<td width="50%" valign="top" >

<div ><label>Equipment Group</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbeqgmKeyid" name="cmbeqgmKeyid" class="easyui-combobox"  style="width:285px;" value="${requestScope.genTlEqpgroupmst.eqgmKeyid}"  ></div>
<input id="hdneqgmName" name="cmbeqgmName" type="hidden" value="${requestScope.genTlEqpgroupmst.eqgmName }"/>
<div class=""><label class="mandatory-lbl">Unit</label></div>
<div class="easyui-paddingbfpx">																								
<input id="cmbeqgmFactoryid" name="cmbeqgmFactoryid" class="easyui-combobox"  style="width:285px;" value="${requestScope.genTlEqpgroupmst.eqgmFactoryid}"<c:out value = "${ requestScope.genTlEqpgroupmstBean.disableFactory == true ? ' disabled':''}"/>  ></div>

<div ><label class="mandatory-lbl">Main Group</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbeqgmMaingroupid" name="cmbeqgmMaingroupid" class="easyui-combobox" clear="false" style="width:285px;" value="${requestScope.genTlEqpgroupmst.eqgmMaingroupid}" <c:out value = "${ requestScope.genTlEqpgroupmstBean.disableMaingroup == 'true' ? ' disabled':''}"/> ></div>
<input type="hidden" id="hdneqgmMaingroupName" name="hdneqgmMaingroupName" class="easyui-combobox" value="${requestScope.genTlEqpgroupmstBean.eqgmMaingroupName}"/>

</td>
<td width="50%" valign="top">
<div ><label>Sub Group</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbeqgmSubgroupid" name="cmbeqgmSubgroupid" class="easyui-combobox"  style="width:285px;" value="${requestScope.genTlEqpgroupmst.eqgmSubgroupid}"  ></div>
<div ><label>Remarks</label></div>
<div class="easyui-paddingbfpx">
<textarea rows="2" style="width:285px;resize:none;" cols="" id="txteqgmRemarks" name="txteqgmRemarks">${requestScope.genTlEqpgroupmst.eqgmRemarks}</textarea></div>
</td>
</tr>
</table>

</div>
	<div class="floatleft" style="padding-left: 22%;padding-left: 25%\9;">&nbsp;</div>
	<div class="floatleft" id="eqpGrpgrid">
		<table id="EquipmentGrp" style="width:100%">
		<tr><td/></tr></table>
		<div id="pager"></div>
		<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
		<input type="hidden" id="hdnchildType" name="hdnchildType"/>
		<input type="hidden" id="hdnformField" name="hdnformField" />
		<input type="hidden" id="hdnInactive" name="hdnInactive" />
		<input type="hidden" id="hdneqgFctId" name="hdneqgFctId" value="${requestScope.eqgFctId}"/>
	</div>
	<div class="floatright">
	<div id="eqpgrpFnLocnDiv" ></div>
	</div>	
</div>
</div>

</form>
