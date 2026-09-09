<script><!--	 
jQuery(document).ready(function(){
	jQuery('#submitForm').val('frmJHParameter');
	initialiseForm('frmJHParameter');	
	formatDateBox('dteJhapRevisiondate','dd-MMM-yyyy');
	fillWithCurrentDate('dteJhapRevisiondate');
	fileManagerPopUp("","JHA","frmJHParameter","btnfilemgr","JhapFilemgr");
	var pillar =  jQuery("#hdnJhapAuditpillar").val();
	var type =  getFieldValue("cmbJhapAudittype");
	fillComboBox("frmJHParameter","cmbJhapKeyid","jhapTemplate.jhAuditItc?pillar="+pillar+"&type="+type );
	fillComboBox("frmJHParameter","cmbJhapAudittype","jhAuditTypeCombo.jhAuditItc","",false);
	fillComboBox("frmJHParameter","cmbJhapAuditlevel","jhAuditLevelCombo.jhAuditItc","",false);	
	disableField('frmJHParameter','cmbJhapAudittype');
	processGridnew("jhTemplate_input.jhAuditItc","?q=2&scWidth="+screen.width,"JHAuditParameter","","","","","template_onLoadComplete","");	
	processGridnew("jhLevel_input.jhAuditItc","?q=2&scWidth="+screen.width,"JHAuditAuditLevel","","","","","","");
	
	jQuery("#chkEvidence").click(function(){
 		if(jQuery("#chkEvidence").is(":checked") == false)
		{	
 			jQuery("#JHAuditParameter").jqGrid("hideCol", ["txtJautEvidence"]);
 			jQuery("#hdnJhapEvidence").val("N");
		}
 		else{
 			jQuery("#JHAuditParameter").jqGrid("showCol", ["txtJautEvidence"]);
 			jQuery("#hdnJhapEvidence").val("Y");
 	 	}
 	}); 	
	
	jQuery("#btnAddNew").click(function(){
		var row = jQuery("#JHAuditParameter").jqGrid("getDataIDs");
		addRow(row);
	});
	if(screen.width <= 1024){
	setTimeout(function(){
		jQuery(".fileCnt").css('padding','0');
	},500);}
	else{setTimeout(function(){
		//jQuery(".fileCnt").css('padding','0');
	},500);}
	/*if(screen.width <= 1024){
		setTimeout(function(){
			//jQuery( "#JHAuditParameter" ).setGridWidth(690);
			alert(12313213213);
			jQuery( "#JHAuditParameter" ).setGridHeight(290);
			//jQuery( ".tabs-container" ).children("div").css({'width':'750','border-right':'solid 1px red'});
		 
			},1150);

	}	
	else{
		jQuery( "#JHAuditParameter" ).setGridWidth(1080);
	}*/
    
	
    jQuery("#cmbJhapKeyid").combobox({onRequest:function( ){
    	var pillar =  jQuery("#hdnJhapAuditpillar").val();
   	 	var type =  getFieldValue("cmbJhapAudittype"); 
		return "pillar="+pillar +"&type="+type ;
  	}});
  	      
});

function btnfilemgr_click()
{
    var documentNo =getFieldValue("cmbJhapKeyid"); 
    
	if(documentNo != null && documentNo != '')
		{
		fileManagerPopUp(documentNo,"JHA","","","");
	}	
}

function  frmJHParametercmbJhapKeyid_onSelect(record){
	var textVal = record.text;
	var splitval = textVal.split("-");
	jQuery("#hdnTemplateId").val(record.id);
	var auditType =  jQuery("#hdnAuditType").val();	
	processAjaxCalls("jhFillControls_select.jhAuditItc" ,"templateId="+record.id, "frmJHParameterFill_successsCallback","frmJHParameterFill_errorCallback");	
	processGridnew('jhTemplate_input.jhAuditItc','?q=2&scWidth='+screen.width+'&templateId='+record.id+'&auditType='+auditType,"JHAuditParameter","","","","","template_onLoadComplete","");
	processGridnew('jhLevel_input.jhAuditItc','?q=2&scWidth='+screen.width+'&templateId='+record.id+'&auditType='+auditType,"JHAuditAuditLevel","","","","","","");	
}

function template_onLoadComplete()
{	
	//hideJqGridRow("JHAuditParameter","1");
	//hideJqGridRow("JHAuditParameter","2");
	if(jQuery('#chkEvidence').is(':checked') == false)
	{	
		jQuery("#JHAuditParameter").jqGrid('hideCol', ["txtJautEvidence"]);
		jQuery("#hdnJhapEvidence").val("N");
	}
	else{
		jQuery("#JHAuditParameter").jqGrid('showCol', ["txtJautEvidence"]);
		jQuery("#hdnJhapEvidence").val("Y");
 	}
	
	
 	var bdGridId = jQuery("#JHAuditParameter").jqGrid('getDataIDs');
 	
 	for(var i = 1; i <= bdGridId.length; i++)
 	{
 		var rowId = bdGridId[i-1];
 		
 		// For all the different input types in your grid
 		jQuery("#desc_" + rowId).on("mousedown keydown click", function(e){
 			e.stopPropagation();
 		});
 		
 		jQuery("#evidence_" + rowId).on("mousedown keydown click", function(e){
 			e.stopPropagation();
 		});
 		
 		jQuery("#paramName_" + rowId).on("mousedown keydown click", function(e){
 			e.stopPropagation();
 		});
 		
 		jQuery("#txtJautReviewPtSlno_" + rowId).on("mousedown keydown click", function(e){
 			e.stopPropagation();
 		});
 		
 		jQuery("#txtJautCriteriaSlno_" + rowId).on("mousedown keydown click", function(e){
 			e.stopPropagation();
 		});
 		
 		jQuery("#keyId_" + rowId).on("mousedown keydown click", function(e){
 			e.stopPropagation();
 		});
 		
 		jQuery("#numericMaxData_" + rowId).on("mousedown keydown click", function(e){
 			e.stopPropagation();
 		});
 		
 	
 		var cm = jQuery("#JHAuditParameter").jqGrid("getGridParam", "colModel");
 		for(var j = 1; j <= cm.length; j++) {
 			jQuery("#numericData_" + rowId + "_" + j).on("mousedown keydown click", function(e){
 				e.stopPropagation();
 			});
 		}
 	}
	 
	 
	 
	
	
}

function frmJHParameterFill_successsCallback(result)
{	//alert(result);
	jQuery("#txtJhapRemarks").val(result.jhaTlAuditparameter.JhapRemarks);
	jQuery("#txtJhapTemplatename").val(result.jhaTlAuditparameter.JhapTemplatename);
	jQuery("#txtJhapTemplatecode").val(result.jhaTlAuditparameter.JhapTemplatecode);
	jQuery("#txtJhapRevisionno").val(result.jhaTlAuditparameter.JhapRevisionno);
	jQuery("#dteJhapRevisiondate").datebox("setValue",result.jhaTlAuditparameter.JhapRevisiondate);	
	setFieldValue("cmbJhapAudittype",result.jhaTlAuditparameter.JhapAudittype,"frmJHParameter");	
	setFieldValue("cmbJhapAuditlevel",result.jhaTlAuditparameter.JhapAuditlevel,"frmJHParameter");	
	setFieldValue("txtJhapRemarks",result.jhaTlAuditparameter.JhapRemarks,"frmJHParameter");
	setFieldValue("txtJhapCriteriamax",result.jhaTlAuditparameter.JhapCriteriamax,"frmJHParameter");	
	/*jQuery("#cmbJhapAudittype").combobox("setValue",result.jhaTlAuditparameter.JhapAudittype);	
	jQuery("#cmbJhapAuditlevel").combobox("setValue",result.jhaTlAuditparameter.JhapAuditlevel);		
	jQuery("#txtJhapRemarks").val(result.jhaTlAuditparameter.JhapRemarks);	*/
	if (result.jhaTlAuditparameter.JhapEvidence=="Y"){
		jQuery('#chkEvidence').attr('checked',true);
		jQuery("#JHAuditParameter").jqGrid('showCol', ["txtJautEvidence"]);
		jQuery("#hdnJhapEvidence").val("Y");
	}
	else if(result.jhaTlAuditparameter.JhapEvidence=="N"){		
		jQuery('#chkEvidence').attr('checked',false);
		jQuery("#JHAuditParameter").jqGrid('hideCol', ["txtJautEvidence"]);
		jQuery("#hdnJhapEvidence").val("N");
	}	
}

function formatterChkJHLevelBox(id, options, rowObject)
{		
	var rowId = options.rowId;	
	return '<input id="jhLevel_checkbox_'+rowId+'" name="jhLevel_checkbox_" '+ (rowObject[0]&&id=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){jhLevelCheck(\''+rowId + '\');}else{jhLevelCheckUnCheck(\''+ rowId +'\')}"/>';
}

function jhLevelCheck(rowId)
{
	jQuery("#JHAuditAuditLevel").jqGrid('setCell',rowId,'selectLevelVal','1');
	radioCheckbox("JHAuditAuditLevel","jhLevel_checkbox",rowId);	
}

function jhLevelCheckUnCheck(rowId){
	jQuery("#JHAuditAuditLevel").jqGrid('setCell',rowId,'selectLevelVal','0');	
}

function radioCheckbox(gridId,columnName,rowId)
{
	var rowIds = jQuery("#"+gridId).getDataIDs();
	for(var i=1;i<=rowIds.length;i++)
	{	
		if(i==rowId){
			jQuery("#"+columnName+"_"+i).attr('checked',true);			
		}
		/*else{
			jQuery("#"+columnName+"_"+i).attr('checked',false);
			jQuery("#JHAuditAuditLevel").jqGrid('setCell',i,'selectLevelVal','0');
		}*/		
	}
}

function formatterTxtJHLevel(id, options, rowObject)
{	
	var rowId = options.rowId;
	return '<input id="txtMaxPoint_"'+rowId+' name="txtMaxPoint_"'+rowId+'   type="text"  value="'+id+'" style="width:146px;height:35px;text-align:left"/>';
}

function FormattarMaxNumeric(id, options, rowObject)
{
	if(id=="undefined" || id == undefined) 
		id = "";
	var rowId = options.rowId;
	return '<input type="text" style="text-align:right;width:66px;height:35px;font-family:arial;"  maxlength="1" onkeyup=allow10("numericMaxData_'+rowId+'"); onkeydown=allow10("numericMaxData_'+rowId+'"); id=numericMaxData_'+rowId +' value='+id+' >';	
}

function FormattarReviewPtSlNo(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input id="txtJautReviewPtSlno_'+rowId+'" name="txtJautReviewPtSlno_'+rowId+'" maxlength="3"  onfocus=gotFocuse("txtJautReviewPtSlno_","'+options.pos+'","'+rowId+'"); type="text"  value="'+id+'" style="width:26px;height:35px;text-align:left"/>';
	//return '<input id="txtJautReviewPtSlno" name="txtJautReviewPtSlno" maxlength="3"   type="text"  value="'+id+'" style="width:30px;text-align:left"/>';	
}
function FormattarCriteriaSlNo(id, options, rowObject)
{
	var rowId = options.rowId;	
	return '<input id="txtJautCriteriaSlno_'+rowId+'" name="txtJautCriteriaSlno_'+rowId+'" maxlength="3" onfocus=gotFocuse("txtJautCriteriaSlno_","'+options.pos+'","'+rowId+'");  type="text"  value="'+id+'" style="width:26px;height:35px;text-align:left"/>';	
}

function FormattarNumeric(id, options, rowObject)
{	
	if(id=="undefined" || id == undefined) 
		id = "";
	var rowId = options.rowId;	
	var gradeId;
	if(rowId==1)
		gradeId=rowObject[options.pos];
 	return '<input type="text" style="text-align:right;width:76px;"  maxlength="3" onfocus=gotFocuse("numericData_","'+options.pos+'","'+rowId+'"); gradeid='+gradeId+' id=numericData_'+rowId +'_'+options.pos+' value='+id+' />'; 	
}

function gotFocuse(keyid,columnNo, rowNo )
{
	numericTextBox("numericData_"+rowNo+"_"+columnNo);
	numericTextBox("txtJautReviewPtSlno_"+rowNo);
	numericTextBox("txtJautCriteriaSlno_"+rowNo);

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

function allow10(id){
	//alert('allow10'+id);
	var val=jQuery("#"+id).val();
	if(val=="1" || val=="0"){
		jQuery("#"+id).val(val);
	}
	else{
		//alert('val'+val);

		jQuery("#"+id).val('');
	}	
} 


function FormattarDesc(id, options, rowObject)
{	
	if(id=="undefined" || id == undefined) 
		id = "";
	
	var rowId = options.rowId;	
 	//return '<input type="text" style="text-align:left;width:390px;" id=desc_'+rowId +' value='+id +' >'; 	
 	return "<input id='desc_"+rowId+"'  style='text-align:left;width:496px;height:35px;font-weight:normal;font-family:arial;' name='desc_txtbox' type='text' value ='"+id+"' />";
}

function FormattarEvidence(id, options, rowObject)
{	
	if(id=="undefined" || id == undefined) 
		id = "";
	
	var rowId = options.rowId;	
 	//return '<input type="text" style="text-align:left;width:390px;" id=desc_'+rowId +' value='+id +' >'; 	
 	return "<input id='evidence_"+rowId+"'  style='text-align:left;width:196px;height:35px;font-weight:normal;font-family:arial;' name='evidence_txtbox' type='text' value ='"+id+"' />";
}


function FormattarParamName(id, options, rowObject)
{	
	if(id=="undefined" || id == undefined) 
		id = "";
	var rowId = options.rowId;	
	var colVal = escape(id);
 	//return '<input type="text" style="text-align:left;width:350px;" id=paramName_'+rowId +' value='+unescape(colVal) +' >';
 	return "<input id='paramName_"+rowId+"'  style='text-align:left;width:296px;height:35px;font-family:arial;' name='paramName_txtbox' type='text' value ='"+unescape(colVal)+"' />";
}

function FormattarKeyId(id, options, rowObject)
{
	if(id=="undefined" || id == undefined) 
		id = "";	
	var rowId = options.rowId;	
 	return '<input type="text" style="text-align:right;width:146px;height:35px;font-family:arial;"  id=keyId_'+rowId +' value='+id +' />'; 	
}

function FormattarDelete(id, options, rowObject)
{
	var rowId = options.rowId;
 	return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
}
function removeOperator(rowId){	
	
	var keyId =  jQuery("#JHAuditParameter").jqGrid('getCell',rowId,'txtJautKeyid');// jQuery("#keyId_"+rowId).val();
	//alert(keyId);
	
	// 🔹 Before submit confirmation
    if (!confirm("Are you sure you want to delete the record(s)?")) {
        return; // stop execution if user cancels
    }
	
	if( keyId != null && keyId.length > 0  )	
		processAjaxCalls("parameter_remove.jhAuditItc", "parameterId="+keyId, 'removeParameter_successCallBack','removeParameter_errorCallBack')	;
	else
		jQuery("#JHAuditParameter").delRowData(rowId);
}

function removeParameter_successCallBack(result)
{
	//alert("MSG"+result.successData.msg);
	///alert("succ"+result.error.tpmException);
	if (result.successData.msg.length<=0){
		//alert("succ"+result.error.tpmException);
		setTimeout(function() {
			showCommonErrorMsg(result.error.tpmException);
		}, 200);
		div_err();
	}
	else{
		alert(result.successData.msg);	
		jQuery("#JHAuditParameter").trigger("reloadGrid");
	}
}

function frmJHParameter_beforeSubmit()
{
	//alert("before submit");
	var keyid = getFieldValue("cmbJhapKeyid");	
	if(keyid.length>0){	
		var r =confirm("Data have been changed, Do you want to proceed?");
		if(r){			
		}
		else
			return false;
	}	
	
	var errText="";
	var errLevel="";
	var errTemplate="";
	var errFlg=false;
	var auditLevel="";
	var auditTemplate="";
	var gridData = '&templateId='+jQuery("#hdnTemplateId").val();
	auditLevel = '&jhAuditLevel='+converToJsonObjectData("JHAuditAuditLevel","selecLevelVal","txtJtllMinimumpoints");
	//gridData += '&jhAuditLevel='+converToJsonObjectData("JHAuditAuditLevel","selecLevelVal","txtJtllMinimumpoints",errText);
	errLevel = getFilterValue(auditLevel+'&', 'errText');
	if(errLevel==null || errLevel.trim()==""){		
		gridData +=auditLevel;  
	}
	else{
		//alert('errText'+errText);
		errFlg=true;
		errText=errLevel;
	}
	//gridData += '&jhStepGrid='+converToJsonObject("JHAuditSteps","selectStepVal","txtJtslJhstepid");
	auditTemplate = '&auditTemplate='+converToJsonObjectParameter("JHAuditParameter");
	//alert("auditTemplate:"+auditTemplate);	
	errTemplate = getFilterValue(auditTemplate+'&', 'errText');
	if(errTemplate==null || errTemplate.trim()==""){
		gridData +=auditTemplate;  
	}
	else{	
		errFlg=true;
		if(errTemplate!=null && errTemplate.trim()!=""){
			errText+=",";
		}
		errText+=errTemplate;
	}
	
	//alert("convertjson"+gridData);	
	//alert("errFlg"+errFlg);	
	if(errFlg==true){
		setTimeout(function() {
			showCommonErrorMsg(errText);
		}, 200);
		div_err();
		return false;
	}
	else{
		return gridData;
	}
}

function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
	 	var emptyItem =[{txtMrkpKeyid:" ",txtJautReviewPtSlno:"1",txtMrkpReviewPoint:" ",txtJautCriteriaSlno:"1",txtMrkpCriteriaForScoring:" ",txtMrkpMaxScore:" "}];
		jQuery("#JHAuditParameter").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];

		var reviewPtSlNo=getFieldValue("txtJautReviewPtSlno_"+lastRow);
		var reviewPt=getFieldValue("paramName_"+lastRow);
		var criteriaSlNo=getFieldValue("txtJautCriteriaSlno_"+lastRow);
		criteriaSlNo=parseInt(criteriaSlNo)+1;
		//alert("reviewPt:"+reviewPt);
		var emptyItem =[{txtMrkpKeyid:" ",txtJautReviewPtSlno:""+reviewPtSlNo+"",txtMrkpReviewPoint:""+reviewPt+"",txtJautCriteriaSlno:""+criteriaSlNo+"",txtMrkpCriteriaForScoring:" ",txtMrkpMaxScore:" "}];
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
	var flg =false;
	var jsonArrO='[';
	var errText="";
	
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row["selectLevelVal"];	
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')
			{
				flg=true;
				//alert('parseJqGridCellValue(row["txtJtllMinimumpoints"]):'+parseJqGridCellValue(row["txtJtllMinimumpoints"]));
				jsonArrO += '{';
				jsonArrO += '"txtJtllAuditlevelid":"' + parseJqGridCellValue(row["txtJtllAuditlevelid"])+'"';
				jsonArrO += ',"txtJtllMinimumpoints":"' + parseJqGridCellValue(row["txtJtllMinimumpoints"])+'"';
				jsonArrO +=  "},";
			}			
		} 
	}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	//alert('converToJsonObjectData'+flg);
	
	if(flg==false){		
		errText=errText+" Select Any Approval Level";
		return jsonArrO+"&errText="+errText; 
		 //getFilterValue
	}
	else{
		return jsonArrO+"&errText="; 
	}
}

function converToJsonObjectParameter(jqGridId)
{
	var flg=false;	
	var errMsg="";	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');	
	var jsonArrO='[';
	if(allRows.length>0){
		console.log("Rows : "+allRows);
		for( var i = 0; i < allRows.length;i++){
			var errFlgRow=false;
			var errMsgRow="";
			var row = allRows[i];
			console.log("Row : "+i+" : "+row);
			var rowId=parseInt(i)+1;
			//alert(rowId);
			var valReviewPt = parseJqGridCellValue(row["txtJautParametername"]);
			var valCriteria= parseJqGridCellValue(row["txtJautParameterdescription"]);
			var valMaxPoints = parseJqGridCellValue(row["txtJautMaximumpoints"]);
			
			var valReviewPtSlNo =parseJqGridCellValue(row["txtJautReviewPtSlno"]);//getFieldValue("txtJautReviewPtSlno_"+rowId);// 
			//alert('valReviewPtSlNo:'+valReviewPtSlNo);
			var valCriteriaSlno = parseJqGridCellValue(row["txtJautCriteriaSlno"]);//getFieldValue("txtJautCriteriaSlno_"+rowId);//
			//alert('valCriteriaSlno:'+valCriteriaSlno);
			if(valReviewPtSlNo.trim().length<=0){				
				if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
				errMsgRow=errMsgRow + "Review Point Sl.No,";
				errFlgRow=true;
			}
			
			if(valReviewPt.trim().length<=0){				
				errMsgRow=errMsgRow + " Review Point,";
				errFlgRow=true;			
			}
			if( valCriteriaSlno.trim().length<=0 ){				
				if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
				errMsgRow=errMsgRow + "Criteria For Scoring Sl.No,";
				errFlgRow=true;
			}
			if(valCriteria.trim().length<=0 ){				
				if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}	
				errMsgRow=errMsgRow + "Criteria For Scoring,";		
				errFlgRow=true;			
			}
			if(valMaxPoints.trim().length<=0){				
				if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
				errMsgRow=errMsgRow + "Max Scores";
				errFlgRow=true;
			}
			if (errFlgRow==true){
				errMsg=errMsg + " Enter " + errMsgRow + " in Row " + parseInt(i+1) + "  " ;
				flg=true;
				for ( var colName in row) {
					jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#ff8040'});  //#94E031				
				}
			}
			//
			//for(var colName in row) {				
			/* jsonArrO += '{';
					jsonArrO += '"txtJautKeyid":"' + parseJqGridCellValue(row["txtJautKeyid"])+'"';		
					jsonArrO += ',"txtJautReviewPtSlno":"' +valReviewPtSlNo+'"';		
					jsonArrO += ',"txtJautParametername":"' +parseJqGridCellValue(row["txtJautParametername"])+'"';
					jsonArrO += ',"txtJautCriteriaSlno":"' +valCriteriaSlno+'"';	
					jsonArrO += ',"txtJautParameterdescription":"' + parseJqGridCellValue(row["txtJautParameterdescription"])+'"';
					jsonArrO += ',"txtJautEvidence":"' + parseJqGridCellValue(row["txtJautEvidence"])+'"';
					jsonArrO += ',"txtJautMaximumpoints":"' + parseJqGridCellValue(row["txtJautMaximumpoints"])+'"';
					//jsonArrO += ',"txtJautMaximumpoints":"' + parseJqGridCellValue(row["txtJautMaximumpoints"])+'"';
					
			jsonArrO +=  "},"; */
			
			jsonArrO += '{';
			jsonArrO += '"txtJautKeyid":"' + parseJqGridCellValue(row["txtJautKeyid"])+'"';
			jsonArrO += ',"txtJautReviewPtSlno":"' + valReviewPtSlNo +'"';
			jsonArrO += ',"txtJautParametername":"' + parseJqGridCellValue(row["txtJautParametername"]).replace(/"/g,'\\"') +'"';
			jsonArrO += ',"txtJautCriteriaSlno":"' + valCriteriaSlno +'"';
			jsonArrO += ',"txtJautParameterdescription":"' + parseJqGridCellValue(row["txtJautParameterdescription"]).replace(/"/g,'\\"') +'"';
			jsonArrO += ',"txtJautEvidence":"' + parseJqGridCellValue(row["txtJautEvidence"]).replace(/"/g,'\\"') +'"';
			jsonArrO += ',"txtJautMaximumpoints":"' + parseJqGridCellValue(row["txtJautMaximumpoints"])+'"';
			jsonArrO += '},';

			console.log(jsonArrO);
			//}
		}	
	}
	else{
		errMsg=errMsg + " Enter Template"  ;
		flg=true;
	}
		
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	console.log(" Final : "+jsonArrO);
	if(flg==true){	
		
		return encodeURIComponent(jsonArrO)+"&errText="+errMsg; 
	}
	else{
		return encodeURIComponent(jsonArrO)+"&errText="; 
	}
}

function frmJHParameter_beforeDelete()
{
	var keyid = getFieldValue("cmbJhapKeyid");
	
	if(keyid.length>0){	
		var r =confirm("Are you sure to Delete?");
		if(r){
			var templateId = jQuery('#JhapTemplate').combobox('getValue');
			return "templateId="+templateId;			
		}
		else
			return false;	
	}
	else
		return false;
	
}
function frmJHParameter_deleteSuccessCallback(result)
{
	alert(result.successData.msg);  	
	//setFieldValue("hdnJhapAuditpillar",result.successData.pillar);
	//setFieldValue("cmbJhapAudittype",result.successData.audittype);	
	clearFields();
}
function frmJHParameter_successsCallback(result)
{
	//alert("frmJHParameter_successsCallback");
	var keyid = getFieldValue("cmbJhapKeyid");	
	if(keyid.length<=0){	
		setFieldValue("hdnJhapAuditpillar",result.successData.pillar);
		setFieldValue("cmbJhapAudittype",result.successData.audittype);
		clearFields();
	}
}

function clearFields(){
	setFieldValue("cmbJhapKeyid","");
	setFieldValue("txtJhapTemplatename","");
	setFieldValue("txtJhapTemplatecode","");
	setFieldValue("txtJhapRevisionno","");
	//setFieldValue("cmbJhapAudittype","");
	setFieldValue("cmbJhapAuditlevel","");
	setFieldValue("txtJhapCriteriamax","");
	setFieldValue("txtJhapRemarks","");
	processGridnew("jhTemplate_input.jhAuditItc","?q=2&scWidth="+screen.width,"JHAuditParameter","","","","","template_onLoadComplete","");	
	processGridnew("jhLevel_input.jhAuditItc","?q=2&scWidth="+screen.width,"JHAuditAuditLevel","","","","","","");
}
</script>
<form id="frmJHParameter">
<div id="wrapper">
	
	<table>
		<tr>
		<td valign="top" >
			<div class="floatleft" style="padding-left:10px;padding-left:10px\9;">
				<div style=""><label>Template Name</label></div>
				<div class="easyui-paddingbfpx">
					<input id="cmbJhapKeyid" name="cmbJhapKeyid" class="easyui-combobox" tabindex="19"  style="width:200px;" value="" >							
				</div>
				<div style="padding-top:3px;"><label class="mandatory-lbl">Name</label></div>
				<div class="easyui-paddingbfpx" style="">
					<input id="txtJhapTemplatename" type="text"  maxlength="200" name="txtJhapTemplatename" value="${requestScope.jhaTlAuditparameter.jhapTemplatename}" style="width: 250px; height : 21px;" height="10px" class="easyui-text";>
				</div>
			</div>				
		</td>
		<td>
			<div class="floatleft" style="padding-left:10px;padding-left:10px\9;">
				<div style=""><label class="mandatory-lbl">Code</label></div>
				<div class="easyui-paddingbfpx" style="">
					<input id="txtJhapTemplatecode" type="text"  maxlength="10" class=easyui-text  name="txtJhapTemplatecode" value="${requestScope.jhaTlAuditparameter.jhapTemplatecode}" style="width: 250px; height : 21px;" height="10px";>
				</div>
				<div style="padding-top:-1px;">
					<label class="mandatory-lbl">Revision No</label>
					<span style="margin-left:14px;"><label class="mandatory-lbl">Date</label></span> 
					<span style="margin-left:67px;"><label class="mandatory-lbl">Criteria Max %</label></span> 
				</div>
				<div class="easyui-paddingbfpx" style="" >
					<input id="txtJhapRevisionno"  type="text" class=easyui-text name="txtJhapRevisionno" value="${requestScope.jhaTlAuditparameter.jhapRevisionno}" style="width: 78px; height : 21px;" height="10px";>
					<span><input id="dteJhapRevisiondate" name="dteJhapRevisiondate" class="easyui-datebox" value="${requestScope.jhaTlAuditparameter.jhapRevisiondate}"/></span>
					<span><input id="txtJhapCriteriamax" type="text"  maxlength="3" class=easyui-text  name="txtJhapCriteriamax" value="${requestScope.jhaTlAuditparameter.jhapCriteriamax}" style="width: 30px; height : 21px;" height="10px";></span>
					<div>
						<span id="err_txtJhapRevisionno" class="tpm-errormsg"></span> 
						<span id="err_dteJhapRevisiondate" class="tpm-errormsg"></span>
					</div>	
				</div>
			</div>
		</td>
		<td valign="top" >
			<div class="floatleft" style="padding-left:10px;padding-left:10px\9;">
				<div><label  class="mandatory-lbl">Audit Type</label></div>	
				<div class="easyui-paddingbfpx">
					<input id="cmbJhapAudittype" name="cmbJhapAudittype" class="easyui-combobox" tabindex="19"  style="width:200px;" value="${requestScope.jhapAudittype}" >							
				</div>
				<div style="padding-top:3px;"><label  class="mandatory-lbl">Audit Level</label></div>	
				<div class="easyui-paddingbfpx">
					<input id="cmbJhapAuditlevel" name="cmbJhapAuditlevel" class="easyui-combobox" tabindex="19"  style="width:200px;" value="${requestScope.jhaTlAuditparameter.jhapAuditlevel}" >							
				</div>
				
			</div>
		</td>
		<td valign="top" >
			<div class="floatleft" style="padding-left:10px;padding-left:10px\9;">
				<div style="padding-left:0px;"><label>Qualifying Criteria</label></div>
				<div class="easyui-paddingbfpx" style="padding-left:0px;">
					<textarea rows="2"  cols="37" id="txtJhapRemarks" maxlength="499" name="txtJhapRemarks">${requestScope.jhaTlAuditparameter.jhapRemarks}</textarea>
				</div>
			</div>
			
		</td>
	</tr>
</table>

<!--<div class="easyui-paddingbfpx" style="padding-left:60px;">
				<input type="button" value="Download Excel Format" class="easyui-button" style="height : 21px; width : 140px;" id="DownloadExcel">
			</div>
			<div class="easyui-paddingbfpx" style="padding-left:60px;">
				<input type="button" value="Import From Excel" class="easyui-button" style="height : 21px; width : 140px;" id="ImportExcel">
			</div>-->
<div class="easyui-paddingbfpx" style="padding-left:60px;">
	<span  id="JhapFilemgr" style="position:absolute;margin-top:0px;right:119px;right:115px\9;z-index:1;"></span> 
</div>	
<div id="tt" style="">
	<div  id="tabJHParameter" class="easyui-tabs"  style="width:auto;max-width:1100px;height:300px;height:310px\9;margin-left:1%;border-bottom:solid 1px #8DB2E3;float:left;" >
		<div title="Template" style="padding-left:10px;padding-top:10px;height:50%;">
			<div>
				<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="height:23px;"/>						
				<span ><input style="padding-left:10px;" type="checkbox"  id="chkEvidence" name="chkEvidence"/></span>		
				<label style='padding-left:1px;'>Evidence</label>				
			</div>			
			<div style="" >
				<table id="JHAuditParameter" style="">
				<tr><td/></tr></table>
				<div id="pager"></div>
			</div>
		</div>
		<div title="Audit Level" style="padding:10px;">
			<div style="" >
				<table id="JHAuditAuditLevel" style="">
				<tr><td/></tr></table>
				<div id="pagerAuditLevel"></div>
			</div>
		</div>	
	</div>
</div>
</div>
<input type="hidden" id="hdnJhapAuditpillar"  name="hdnJhapAuditpillar" value="${requestScope.jhapAuditpillar}"/>
<input type="hidden" id="hdnJhapEvidence" name="hdnJhapEvidence" value="${requestScope.jhaTlAuditparameter.jhapEvidence}"/>
<input type="hidden" id="hdnTemplateId" name="hdnTemplateId" value=""/>
 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
</form>