<!-- Created By:Dhanalakshmi.R -->
<script type="text/javascript">

jQuery(document).ready(function()
{
	initialiseForm('frmMchnRunHours');
	jQuery('#submitForm').val('frmMchnRunHours');
	var url = geturl();
	var dataString =""; 
	viewGrid(url,dataString);
	var dataStr = getFnLocnValues();
	loadFunctionalLocation("MchnRunfunLocation","functionalLoc.mcnRnHrs","MchnRunfunLocationValues","frmMchnRunHours",dataStr);
}
);
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "";
		processGridnew(url,filterString,"grdMchnRunHours","grdMchnRunHoursPager",tableCaption,"","","loadComplete");
		return true;
	}	
}
function getFnLocnValues()
{
	var companyId =getCompany("frmMchnRunHours");
	var locnId=getLocation("frmMchnRunHours");
	var factId = getFactory("frmMchnRunHours");
	var sectionId = getSection("frmMchnRunHours");
	var cellId = getCell("frmMchnRunHours");
	var machineId=getMachine("frmMchnRunHours");	
	var status=getConfigStatus();
	var dataStr = "&compId="+companyId+"&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId;
	//dataStr +="&configStatus="+status;
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
function geturl()
{
	return jQuery("#hiddenUrl").val();
}
function getConfigStatus()
{
	return getFieldValue("hdnConfigState","frmMchnRunHours");
}
function validateFilterSelection(filterString){
	return  true;
}
function chkbox_MachineRun(id, options, rowObject)
{ 
	var rowId = options.rowId;	
	return '<input id="machine_checkbox" name="machine_checkbox" '+ (rowObject[2]=="N" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{
	jQuery("#grdMchnRunHours").jqGrid('setCell',rowId,'chkIsDelete','N');	
}
function chkboxUnCheck(rowId)
{
	jQuery("#grdMchnRunHours").jqGrid('setCell',rowId,'chkIsDelete','Y');
}
function frmMchnRunHours_beforeSubmit()
{
	var reqColsArr=new Array("getMcrhKeyid","getMcrhFactoryid","getMcrhSectionid","getMcrhCellid","getMcrhMachineid","chkIsDelete","getMcrhEffectivefrom","getMcrhRunhrsvalue","getrefEffDate","getrefValue");
	var gridData='&mchJsonArray='+JqGridToJsonSelRowsReqCols('grdMchnRunHours','Select',reqColsArr);
	return gridData ; 
}
function frmMchnRunHours_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	setFieldValue("cmbMcrhCompanyid",keyIds.compId);
	setFieldValue("cmbMcrhLocationid",keyIds.locnId);
	setFieldValue('cmbMcrhFactoryid',keyIds.factId);
	setFieldValue('cmbMcrhSectionid',keyIds.sectId);
	setFieldValue('cmbMcrhCellid',keyIds.cellId);
	setFieldValue('cmbMcrhMachineid',keyIds.machId);
	var url = geturl();
	var dataString = "";
	var keyId="";
	var status=getConfigStatus();
	if(status=="true" ||status=="TRUE" )
	{
		if(keyIds.machId!=null)
			keyId=keyIds.machId;
		else if(keyIds.cellId!=null)
			keyId=keyIds.cellId;
		else if(keyIds.sectId!=null)
			keyId=keyIds.sectId;
	}
	else if(status=="false"||status=="FALSE")
	{	
		if(keyIds.machId!=null)
			keyId=keyIds.machId;
		 else if(keyIds.cellId!=null)
			keyId=keyIds.cellId;
	}
	if(keyId!="" && keyId.trim().length>0) 
	{	
		dataString +="?q=2&keyId="+keyId;
		dataString +="&configStatus="+status;
		viewGrid(url,dataString);
	}	
}
function datebox_effectiveDate(id,options,rowObject)
{
	var rowId = options.rowId;     
	var id='dteEffDate_'+rowId;
	var value="";
	if (rowObject[2]=="N")
		value=rowObject[9];
	return '<input id="'+id+'" name="'+id+'" type="text" class="easyui-datebox"  style="width:203px;height:25px;" value="'+value+'"/>';
	}
function textbox_mchnRunHrs(id,options,rowObject)
{
	var rowId = options.rowId;
	var value="";
	if (rowObject[2]=="N")
		value=rowObject[10];
	var id='txtValue_'+rowId;
	return '<input id="'+id+'" name="'+id+'" type="text" class="easyui-text" maxlength="10" style="width:203px;text-align:right;height:23px;" value="'+value+'"/>';
}
function loadComplete()
{
	var allRows = jQuery("#grdMchnRunHours").jqGrid('getDataIDs');
	for(var i=1;i<=allRows.length;i++)
	{
		var id='dteEffDate_'+i;
		var txtId='txtValue_'+i;
		formatDateBox(id,'dd-MMM-yyyy');	
		numericTextBox(txtId);
	}
}
function frmMchnRunHours_successsCallback()
{
	jQuery("#grdMchnRunHours").trigger("reloadGrid");
}
</script>
<form id="frmMchnRunHours" name="frmMchnRunHours">
	<div id="wrapperRpt" style= "margin-top:10px;">
		<div  id="frmMchnRunHoursFuntKeyIds"  >
			<input type="hidden" id="company" name="cmbMcrhCompanyid" value=""/  >
			<input type="hidden" id="location" name="cmbMcrhLocationid" value="" / >
			<input type="hidden" id="factory" name="cmbMcrhFactoryid" value="" / >
			<input type="hidden" id="section" name="cmbMcrhSectionid" value="" / >
			<input type="hidden" id="cell" name="cmbMcrhCellid" value="" / >
			<input type="hidden" id="machine" name="cmbMcrhMachineid" value="" / >
			<div id="MchnRunfunLocation" style="width:1136px;"></div>
		<div ></div>
		</div>
		<table style="marigin-top:10%" id="grdMchnRunHours" ></table>
		<div id="grdMchnRunHoursPager"></div>	
	</div>
	
	<input type="hidden" id="mode" name="mode" value=""/>
	<input type="hidden" id="hdnConfigState" name="hdnConfigState" value="${requestScope.configState}"/>
</form>