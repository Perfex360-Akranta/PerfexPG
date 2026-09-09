<script>	 
jQuery(document).ready(function(){
	jQuery('#submitForm').val('frmJHParameter');
	formatDateBox('dteJhapRevisiondate','dd-MMM-yyyy');
	fillComboBox("frmJHParameter","JhapTemplate","jhapTemplate.jhAudit");
	initialiseForm('frmJHParameter');	

	processGridnew('jhTemplate_input.jhAudit','?q=2',"JHAuditParameter","","","","","template_onLoadComplete","");
	processGridnew('jhStep_input.jhAudit','?q=2',"JHAuditSteps","","","","","","");
	processGridnew('jhEquipment_input.jhAudit','?q=2',"JHAuditEquipment","","","","","","");
	processGridnew('jhLevel_input.jhAudit','?q=2',"JHAuditAuditLevel","","","","","","");
	
jQuery("#tabJHParameter").tabs({ onLoad:function(title){ 
	 jQuery('.tabs-panels').css('height','90%%');
}
});

jQuery("#btnAddNew").click(function(){
	var row = jQuery("#JHAuditParameter").jqGrid('getDataIDs');
	addRow(row);
});

jQuery('#FileMgr').click(function(event){	

	var documentNo =jQuery('#JhapTemplate').combobox('getValue');
	
	if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"JHA","","","");
	}
	else
		alert("Template should be selected to view FileManager");
});
});

function  frmJHParameterJhapTemplate_onSelect(record){
	var textVal = record.text;
	var splitval = textVal.split("-");
	jQuery("#hdnTemplateId").val(record.id);
	processAjaxCalls("jhFillControls_select.jhAudit" ,"templateId="+record.id, "frmJHParameterFill_successsCallback","frmJHParameterFill_errorCallback");
	processGridnew('jhTemplate_input.jhAudit','?templateId='+record.id+'&q=2',"JHAuditParameter","","","","","template_onLoadComplete","");
	processGridnew('jhStep_input.jhAudit','?templateId='+record.id+'&q=2',"JHAuditSteps","","","","","","");
	processGridnew('jhEquipment_input.jhAudit','?templateId='+record.id+'&q=2',"JHAuditEquipment","","","","","","");
	processGridnew('jhLevel_input.jhAudit','?templateId='+record.id+'&q=2',"JHAuditAuditLevel","","","","","","");
}
function template_onLoadComplete()
{
	
	hideJqGridRow("JHAuditParameter","1");
	hideJqGridRow("JHAuditParameter","2");
}
function frmJHParameterFill_successsCallback(result)
{	
	
	jQuery("#txtJhapRemarks").val(result.jhaTlAuditparameter.JhapRemarks);
	jQuery("#txtJhapTemplatename").val(result.jhaTlAuditparameter.JhapTemplatename);
	jQuery("#txtJhapTemplatecode").val(result.jhaTlAuditparameter.JhapTemplatecode);
	jQuery("#txtJhapRevisionno").val(result.jhaTlAuditparameter.JhapRevisionno);
	jQuery("#dteJhapRevisiondate").datebox("setValue",result.jhaTlAuditparameter.JhapRevisiondate);
	
}
function formatterChkJHStepBox(id, options, rowObject)
{		
	var rowId = options.rowId;	
	return '<input id="jhStep_checkbox_'+rowId+'" name="jhStep_checkbox" '+ (rowObject[0]&&id=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){jhStepCheck(\''+rowId + '\');}else{jhStepCheckUnCheck(\''+ rowId +'\')}"/>';
}

function jhStepCheck(rowId)
{
	var templateId = jQuery("#hdnTemplateId").val();
	jQuery("#JHAuditSteps").jqGrid('setCell',rowId,'selectStepVal','1');
	var rowData = jQuery("#JHAuditSteps").jqGrid('getRowData',rowId);
	var jhStepId = rowData.txtLevelNo;	
	processGridnew('jhEquipment_input.jhAudit','?templateId='+templateId+'&jhLevelNo='+jhStepId,"JHAuditEquipment","","","","","","");
	radioCheckbox("JHAuditSteps","jhStep_checkbox",rowId);
}
function radioCheckbox(gridId,columnName,rowId)
{
	var rowIds = jQuery("#"+gridId).getDataIDs();
	for(var i=1;i<=rowIds.length;i++)
	{	
		if(i==rowId){
			jQuery("#"+columnName+"_"+i).attr('checked',true);			
		}
		else{
			jQuery("#"+columnName+"_"+i).attr('checked',false);
			jQuery("#JHAuditSteps").jqGrid('setCell',i,'selectStepVal','0');
		}		
	}
}
function jhStepCheckUnCheck(rowId){
	jQuery("#JHAuditSteps").jqGrid('setCell',rowId,'selectStepVal','0');	
}

function formatterChkJHEquipment(id, options, rowObject)
{		
	var rowId = options.rowId;	
	return '<input id="jhEquipment_checkbox" name="jhEquipment_checkbox" '+ (rowObject[0]&&id=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{
	jQuery("#JHAuditEquipment").jqGrid('setCell',rowId,'selectEqpVal','1');		
}
function chkboxUnCheck(rowId){
	jQuery("#JHAuditEquipment").jqGrid('setCell',rowId,'selectEqpVal','0');	
}
function formatterTxtJHLevel(id, options, rowObject)
{	
	var rowId = options.rowId;
	return '<input id="txtMaxPoint_"'+rowId+' name="txtMaxPoint_"'+rowId+'   type="text"  value="'+id+'" style="width:375px;text-align:left"/>';
}
function FormattarMaxNumeric(id, options, rowObject)
{
	if(id=="undefined" || id == undefined) 
		id = "";
	var rowId = options.rowId;
	return '<input type="text" style="text-align:right;width:80px;"  maxlength="3" onfocus=gotFocuse("numericMaxData_","'+options.pos+'","'+rowId+'"); id=numericMaxData_'+rowId +' value='+id+' >'; 	

}
function FormattarNumeric(id, options, rowObject)
{	
	if(id=="undefined" || id == undefined) 
		id = "";
	var rowId = options.rowId;	
	var gradeId;
	if(rowId==1)
		gradeId=rowObject[options.pos];
 	return '<input type="text" style="text-align:right;width:80px;"  maxlength="3" onfocus=gotFocuse("numericData_","'+options.pos+'","'+rowId+'"); gradeid='+gradeId+' id=numericData_'+rowId +'_'+options.pos+' value='+id+' >'; 	
}

function gotFocuse(keyid,columnNo, rowNo )
{
	numericTextBox("numericData_"+rowNo+"_"+columnNo);
	numericTextBox("numericMaxData_"+rowNo);

	if(columnNo == 9)
		readOnlyFieldsWithText("numericData_"+rowNo+"_"+columnNo,"0");
	
	columnNo = parseInt(columnNo)-1;
	var  txtvalue = jQuery("#numericData_"+rowNo+"_"+columnNo).val();
	var preVal;
	if(columnNo == 5)
	{
		preVal = jQuery("#numericMaxData_"+rowNo).val();
		preVal = parseInt(preVal)+1;
	}
	else if(columnNo >5)
	{	 var prvColNo = parseInt(columnNo)-1;
		 preVal = jQuery("#numericData_"+rowNo+"_"+prvColNo).val();
	}
	
	if(parseInt(txtvalue) < parseInt(preVal)){}		
	else
		jQuery("#numericData_"+rowNo+"_"+columnNo).val("");
}

function FormattarDesc(id, options, rowObject)
{	
	if(id=="undefined" || id == undefined) 
		id = "";
	
	var rowId = options.rowId;	
 	return '<input type="text" style="text-align:left;width:150px;" id=desc_'+rowId +' value='+id +' >'; 	
}

function FormattarParamName(id, options, rowObject)
{	
	if(id=="undefined" || id == undefined) 
		id = "";
	var rowId = options.rowId;	
	var colVal = escape(id);
 	return '<input type="text" style="text-align:left;width:200px;" id=paramName_'+rowId +' value='+unescape(colVal) +' >'; 	
}
function FormattarKeyId(id, options, rowObject)
{
	if(id=="undefined" || id == undefined) 
		id = "";	
	var rowId = options.rowId;	
 	return '<input type="text" style="text-align:right;width:150px;"  id=keyId_'+rowId +' value='+id +' >'; 	
}

function FormattarDelete(id, options, rowObject)
{
	var rowId = options.rowId;
 	return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
}
function removeOperator(rowId){	
	
	var keyId = jQuery("#keyId_"+rowId).val();
	
	if( keyId != null && keyId.length > 0  )	
		processAjaxCalls("parameter_remove.jhAudit", "parameterId="+keyId, 'removeParameter_successCallBack','removeParameter_errorCallBack')	;
	else
		jQuery("#JHAuditParameter").delRowData(rowId);
}
function removeParameter_successCallBack(result)
{
	alert(result.successData.msg);
	jQuery("#JHAuditParameter").trigger("reloadGrid");
}
function frmJHParameter_beforeSubmit()
{
	var gridData = '&templateId='+jQuery("#hdnTemplateId").val();
	gridData += '&equipmentGrid='+converToJsonObject("JHAuditEquipment","selectEqpVal","txtJtmlMachineid");
	gridData += '&jhStepGrid='+converToJsonObject("JHAuditSteps","selectStepVal","txtJtslJhstepid");
	gridData += '&jhAuditLevel='+converToJsonObjectData("JHAuditAuditLevel","txtJtllAuditlevelid","txtJtllMinimumpoints");
	gridData += '&auditTemplate='+converToJsonObjectParameter("JHAuditParameter");
	var paramenerGrade = converToJsonObjectGrade("JHAuditParameter");
	gridData += '&gradeTemplate='+paramenerGrade;
	
	if(paramenerGrade == false)
	{
		alert("Template Grid Should not be Empty!");
		return false;
	}
	else
		return gridData;
}

function addRow(row)
{
	
		 if ( row == null || row == '' || parseInt(row) <= 0) {
		 	var emptyItem =[{txtMrkpKeyid:" ",txtMrkpParametername:" ",txtMrkpParametername:" ",txtMrkpParametername:" "}];
			jQuery("#JHAuditParameter").jqGrid('addRowData',1, emptyItem[0]);
		 }	
		 else
		 {
			for(var i=0;i<row.length;i++)
					lastRow = row[i];
			var emptyItem =[{txtMrkpKeyid:" ",txtMrkpParametername:" ",txtMrkpParametername:" ",txtMrkpParametername:" "}];
			jQuery("#JHAuditParameter").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
		 }
}

function converToJsonObject(jqGridId,ckeckForSelColName,getCol)
{
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];		
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
				jsonArrO += '{';
			for(var colName in row) {
				var cellValue = parseJqGridCellValue(row[colName]);
			
					if(colName == getCol)
						jsonArrO += '"'+colName +'":"' + cellValue+'"';
					
			}
			jsonArrO +=  "},";
			
		}
	} 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
}

function converToJsonObjectData(jqGridId,ckeckForSelColName,getCol)
{
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];		
		if( value != null  &&  value.trim()  != ""){			
			{
				jsonArrO += '{';
						jsonArrO += '"txtJtllAuditlevelid":"' + parseJqGridCellValue(row["txtJtllAuditlevelid"])+'"';
						jsonArrO += ',"txtJtllMinimumpoints":"' + parseJqGridCellValue(row["txtJtllMinimumpoints"])+'"';
				jsonArrO +=  "},";
			
			}
	} 
	}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
}

function converToJsonObjectParameter(jqGridId)
{
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 2; i < allRows.length;i++){
		var row = allRows[i];		
		//for(var colName in row) {	
			
		jsonArrO += '{';
				jsonArrO += '"txtJautKeyid":"' + parseJqGridCellValue(row["txtJautKeyid"])+'"';
				
				jsonArrO += ',"txtJautParametername":"' + parseJqGridCellValue(row["txtJautParametername"])+'"';
				jsonArrO += ',"txtJautParameterdescription":"' + parseJqGridCellValue(row["txtJautParameterdescription"])+'"';
				jsonArrO += ',"txtJautMaximumpoints":"' + parseJqGridCellValue(row["txtJautMaximumpoints"])+'"';
		jsonArrO +=  "},";
		//}
	}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
}

function converToJsonObjectGrade(jqGridId)
{
	var flag = true;
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
	var cm = jQuery("#"+jqGridId).jqGrid("getGridParam", "colModel");	
	var jsonArrO='[';
	for( var i = 0; i < allRows.length+1;i++){
		var row = allRows[i];			
		if(i>2)
		{	//i = parseInt(i+1);
			for(var j=5;j<cm.length-2;j++)
			{				
				//colName = cm[j].name;
				
				var gradData = jQuery("#numericData_"+i+"_"+j).val()
				if(gradData == '' )
					flag = false;
				jsonArrO += '{';				
				jsonArrO += '"txtJtglTemplateid":"' + jQuery("#keyId_"+i).val()+'"';
				jsonArrO += ',"txtJtglGradeid":"' + jQuery("#numericData_1_"+j).val()+'"';				
				jsonArrO += ',"txtJtglMinimummarks":"' + jQuery("#numericData_"+i+"_"+j).val()+'"';
				jsonArrO +=  "},";
			}
		}
	}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	if(flag)
		return jsonArrO;
	else 
		return flag;
}
function frmJHParameter_beforeDelete()
{
	var templateId = jQuery('#JhapTemplate').combobox('getValue');
	return "templateId="+templateId;
}
</script>
<form id="frmJHParameter">
<div id="wrapper" style="width:100%">
<div >
<table width=100%>
<tr>
<td valign="top" width=33%>

<div style="padding-left:80px;"><label>Template Name</label></div>
<div class="easyui-paddingbfpx" style="padding-left:80px;" >
<input id="JhapTemplate" name="JhapTemplate" class="easyui-combobox"  style="width:250px;" value="" />
	</div>
	
<div style="padding-left:80px;"><label class="mandatory-lbl">Name</label></div>
<div class="easyui-paddingbfpx" style="padding-left:80px;">
<input id="txtJhapTemplatename" type="text"  name="txtJhapTemplatename" value="${requestScope.jhaTlAuditparameter.jhapTemplatename}" style="width: 250px; height : 21px;" height="10px" class="easyui-text";>
	</div>
	
<div style="padding-left:80px;"><label class="mandatory-lbl">Code</label></div>
<div class="easyui-paddingbfpx" style="padding-left:80px;">
<input id="txtJhapTemplatecode" type="text" class=easyui-text  name="txtJhapTemplatecode" value="${requestScope.jhaTlAuditparameter.jhapTemplatecode}" style="width: 250px; height : 21px;" height="10px";>
	</div>
</td>

<td valign="top" width=33%>

<div style="padding-left:100px;"><label>Remarks</label></div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<textarea rows="2"  cols="28" id="txtJhapRemarks" name="txtJhapRemarks">${requestScope.jhaTlAuditparameter.jhapRemarks}</textarea>
	</div>
	
	<div style="padding-left:100px;"><label class="mandatory-lbl">Revision No</label>
	<span style="margin-left:14px;"><label class="mandatory-lbl">Date</label></span> </div>
<div class="easyui-paddingbfpx" style="padding-left:100px;" >
<input id="txtJhapRevisionno"  type="text" class=easyui-text name="txtJhapRevisionno" value="${requestScope.jhaTlAuditparameter.jhapRevisionno}" style="width: 78px; height : 21px;" height="10px";>
<span><input id="dteJhapRevisiondate" name="dteJhapRevisiondate" class="easyui-datebox" value="${requestScope.jhaTlAuditparameter.jhapRevisiondate}"/></span>
<div>
		<span id="err_txtJhapRevisionno" class="tpm-errormsg"></span> 
		<span id="err_dteJhapRevisiondate" class="tpm-errormsg"></span>
	</div> 
</div>
	
</td>

<td valign="top" width=33%>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input type="button" value="Download Excel Format" class="easyui-button" style="height : 21px; width : 160px;" id="DownloadExcel"></div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input type="button" value="Import From Excel" class="easyui-button" style="height : 21px; width : 160px;" id="ImportExcel"></div>
<div class="easyui-paddingbfpx" style="padding-left:100px;">
<input type="button" value="File Manager" class="easyui-button" style=" height : 21px; width : 160px;" id="FileMgr"></div>
</td>
</tr>
</table>


<div id="tt" style="padding-left:0px;width : 90%;position:relative; ">
<div  id="tabJHParameter" class="easyui-tabs"  style="width:1000px;height:270px;padding-left:-72px;margin-left:8%;border-bottom:solid 1px #8DB2E3;float:left;position:absolute;" >

	<div title="Template" style="padding:10px;height:50%;">
	<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="margin-left:50%;height:23px"/>
		<table id="JHAuditParameter" style="width:100%;height:50%;">
		<tr><td/></tr></table>
		<div id="pager"></div>
	</div>
	<div title="Steps" style="padding:10px;">
		<table id="JHAuditSteps" style="width:100%">
		<tr><td/></tr></table>
		<div id="pagerSteps"></div>
	</div>
	<div title="Equipment" style="padding:10px;">
		<table id="JHAuditEquipment" style="width:100%">
		<tr><td/></tr></table>
		<div id="pagerEquipment"></div>
	</div>
	<div title="Audit Level" style="padding:10px;">
		<table id="JHAuditAuditLevel" style="width:100%">
		<tr><td/></tr></table>
		<div id="pagerAuditLevel"></div>
	</div>

</div>
</div>
</div>

<input type="hidden" id="hdnTemplateId",name="hdnTemplateId" value=""/>
<input type="hidden" id="mode" />
</div>
</form>