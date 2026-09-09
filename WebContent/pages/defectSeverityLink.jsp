<script type="text/javascript">
jQuery(document).ready(function(){

	initialiseForm('frmDefectSeveirty');
	jQuery('#submitForm').val('frmDefectSeveirty'); 

	var factId = jQuery("#frmDefectSeveirty input[id='factory']").val();
	var sectionId = jQuery("#frmDefectSeveirty input[id='section']").val();
	var cellId = jQuery("#frmDefectSeveirty input[id='cell']").val();
	var machId = jQuery("#frmDefectSeveirty input[id='machine']").val();
	var flid = jQuery("#frmDefectSeveirty input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	
    fillComboBox("frmDefectSeveirty","cmbQdslCtqid","getCtq.qams?&flid="+flid);
	fillComboBox("frmDefectSeveirty","cmbQdslGradespecid","comboGradeSpec.commonFilter?&flid="+flid);
	
	
    loadFunctionalLocation("defectLinkfunLocation","functionalLoc.commonFilter","frmDefectSeveirtyfunLocationValues","frmDefectSeveirty",dataStr);

	viewDefectGrid("?q=2");
	jQuery('#btnQAMAtrix').click(function(){
		var ctqId = getFieldValue('cmbQdslCtqid');
		//alert(ctqId);
		if(ctqId.trim().length>0)
		navigateToNextForm("tenstepQaMatrix_input.tsdi?&ctqid="+ctqId,"QA Matrix",null);
	});

	jQuery("#btnCtqmst").click(function(){	
		jQuery('#hdnlinkMode').val('Classifcn');	
		//http://localhost:8080/perfexitc/loadmst_grid.gnms?q=2&menuCaption=CTQ&menuName=MNUCTQMST&isMMC=Y&loadFormArg=CTQ_input.qams%20%3F
		openMasterForm('loadmst_grid.gnms?q=2&menuCaption=CTQ&menuName=MNUCTQMST&isMMC=Y&loadFormArg=CTQ_input.qams&&closeOnSave=true',frmMode.create,'frmDefectSeveirty','CTQ','mstFrm');
	});


	jQuery("#btnDefectMas").click(function(){	
		jQuery('#hdnlinkMode').val('Classifcn');	
		//http://localhost:8080/perfexitc/loadmst_grid.gnms?q=2&menuCaption=DefectMode&menuName=MNUMASBDDEFECTMASTER&isMMC=Y&loadFormArg=%3F
		openMasterForm('loadmst_grid.gnms?q=2&menuCaption=DefectMode&menuName=MNUMASBDDEFECTMASTER&isMMC=Y&loadFormArg=%3F&closeOnSave=true',frmMode.create,'frmDefectSeveirty','CTQ','mstFrm');
	});

	jQuery("#btnAddDefect").click(function(){	
		var row  = jQuery("#grdDsl").jqGrid('getDataIDs');	
		addRow(row);
	});
	
	
});

function newMstFrm_onClose() {
	jQuery('#submitForm').val('frmDefectSeveirty'); 
	return true;
}



function frmDefectSeveirty_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	reloadCombo("frmDefectSeveirty","cmbQdslCtqid","getCtq.qams?&flid="+keyIds.flId );
	reloadCombo("frmDefectSeveirty","cmbQdslGradespecid","comboGradeSpec.commonFilter?q&flid="+keyIds.flId);
}

function viewDefectGrid(filterStr){
	var ctqId = getFieldValue("cmbQdslCtqid");
	var gradeSpec = getFieldValue("cmbQdslGradespecid");
	//alert(gradeSpec);
	filterStr = "?q=2&Ctqid="+ctqId+"&gradeSpec="+gradeSpec;
	processGridnew("DefectSeverity_input.tsdi",filterStr,"grdDsl","pagerDsl","","DslDblClkFunction","","dslComplete","");
}

function grdDsl_selectRow(id){
	jQuery("#frmDefectSeveirty input[class='easyui-text']").css('text-align','center');
	var ctqId = getFieldValue('cmbQdslCtqid');
	var flid = jQuery("#frmDefectSeveirty input[id='flid']").val();
	var ds = "?ctqId="+ctqId+"&flid="+flid;
	setTimeout(function() { 
		reloadCombo("frmDefectSeveirty","txtQPDMNAME_grdDsl_"+id,"combo_defectMode.tsdi"+ds);
	},550);
}

function frmDefectSeveirty_beforeSubmit(){
	var grid = jQuery("#grdDsl");
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
	var saveData ="";
	if( selArray.length <= 0 ) return "";
	else{ 
		saveData = getGridSelectArray("grdDsl");
		 if(saveData.trim().length>0)
	    	return "&qagridData="+saveData;
		 else{ 
			//alert("No Data To Save");
			return false;
		 }
	}
}

function frmDefectSeveirty_beforeDelete(){
	var grid = jQuery("#grdDsl");
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
	var deleteData ="";
	
	if( selArray.length <= 0 ) return "";
	else{ 
		deleteData = getGridSelectArray("grdDsl");
		 if(deleteData.trim().length>0)
	    	return "&qagridData="+deleteData;
		 else{ 
			return false;
		 }
	}
}
function frmDefectSeveirtycmbQdslCtqid_onSelect(record){
	viewDefectGrid("");
}

function frmDefectSeveirtycmbQdslGradespecid_onSelect(record){
	viewDefectGrid("");
}
function grdDsl_onBlur(record){
	var gridId = record.gridId; 
	var txtId  = record.txtId;
	var rowId  = record.rowId;
	 
	var txtBoxId = txtId+"_"+gridId+"_"+rowId;
	//var occurence = jQuery('#'+txtBoxId).val();
	var occurence = jQuery('#txtQdslOccurence_grdDsl_'+rowId).val();
	if(parseInt(occurence)>5){ 
		jQuery('#txtQdslOccurence_grdDsl_'+rowId).val(" ");
		jQuery("#grdDsl").jqGrid('setCell',rowId,"txtQdslTotalratting"," ");
		return;
	}
	if (occurence==" ") 
		occurence="0";
	//txtQpdmoccurence_grdDsl_1
	//var severity =jQuery("#"+gridId).jqGrid('getCell', rowId,"txtQdslSeverity");
	//txtQdslSeverity_grdDsl_1
	
	var severity =jQuery("#txtQdslSeverity_grdDsl_"+rowId).val();
	if (severity==" ") 
		severity="0";
	if(parseInt(severity)>5){ 
		jQuery('#txtQdslSeverity_grdDsl_'+rowId).val(" ");
		jQuery("#grdDsl").jqGrid('setCell',rowId,"txtQdslTotalratting"," ");
		return;
	}
	
	
	var mulVal = parseInt(occurence)*parseInt(severity);
    jQuery("#grdDsl").jqGrid('setCell',rowId,"txtQdslTotalratting",mulVal);
}

function frmDefectSeveirty_successsCallback(result){
	if (result.successData.msg!='Data Saved Successfully')
		popupCommonErrorMsg(result.successData.msg);
	jQuery('#grdDsl').trigger('reloadGrid');
}

function frmDefectSeveirty_deleteSuccessCallback(result){
	//if (result.successData.msg!='Data Deleted Successfully')
		alert(result.successData.msg);
	jQuery('#grdDsl').trigger('reloadGrid');
}


function addRow(row)
{
		 if ( row == null || row == '' || parseInt(row) <= 0) {			 
		 	var emptyItem =[{"txtQdslKeyid":" ","txtQdslDefectid":" ","txtQPDMNAME":" ","txtQdslSeverity":" ","txtQdslOccurence":" ","txtQdslTotalratting":" "}];
			jQuery("#grdDsl").jqGrid('addRowData',1, emptyItem[0]);
		 }	
		 else
		 {
			for(var i=0;i<row.length;i++)
					lastRow = row[i];
			var emptyItem =[{"txtQdslKeyid":" ","txtQdslDefectid":" ","txtQPDMNAME":" ","txtQdslSeverity":" ","txtQdslOccurence":" ","txtQdslTotalratting":" "}];
			jQuery("#grdDsl").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
		 }
}


</script>
<form id='frmDefectSeveirty' name='frmDefectSeveirty'>
<div id='wrapperRpt'>
<div  id="frmDefectSeveirtyFuntKeyIds"  >
	<div style="float: left;padding-right: 20px;">
		<input type="hidden" id="section" name="cmbhdnSectionid" value=""  ></input>
		<input type="hidden" id="cell" name="cmbhdnCellid" value=""  ></input>
		<input type="hidden" id="machine" name="cmbhdnEquipmentid1" value=""  ></input>
		<input type="hidden" id="flid" name="cmbhdnFlid" value="${requestScope.flid}"  ></input>
	</div>
	<div id="defectLinkfunLocation" style="width: 90%; "></div>
<div>
	<label class="mandatory-lbl">CTQ</label>
	<span style="padding-left: 200px;"> <label >Grade Spec</label> </span>
</div>
<div style="width: 120%;">
	<input class="easyui-combobox" id="cmbQdslCtqid" name="cmbQdslCtqid" value="${requestScope.ctqid}"  style='width:220px;'/>
	<span style="padding-left: 10px;">
		<input class="easyui-combobox" id="cmbQdslGradespecid" name="cmbQdslGradespecid" value="${requestScope.gradeSpec}"  style='width:220px;' value="${requestScope.productId}"/>
	</span>
	
	<span style="">
		<input type="button" class="easyui-button"  id="btnCtqmst" value="..." style="height: 22px;"/>
	</span>
	<label class='notes' style='font-style:italic;'>(Select CTQ to View Defects)</label>
	<span style="visibility: hidden;"><input type='button' class='easyui-button' value='QAMatrix' style='height:21px;' id='btnQAMAtrix'/></span>

	<span style="">
		<input type="button" class="easyui-button"  id="btnAddDefect" value="Add CTQ Rating" style="height: 22px;width: 120px;"/>
	</span>

	<span style="padding-left: 10px;">
		<input type="button" class="easyui-button"  id="btnDefectMas" value="Add New Defect Modes" style="height: 22px;"/>
	</span>
</div>
<table id="grdDsl">
<tr><td></td></tr>
</table>
<div id='pagerDsl'></div>
</div>
</div>
<input type='hidden' id ='mode'/>
</form>