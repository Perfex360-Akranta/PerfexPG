<!-- Created By: Roopa -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script>	 

jQuery.noConflict();
jQuery(document).ready(function()
{			
	jQuery('#submitForm').val('frmAssessment'); // set the id of form to submit	
	initialiseForm('frmAssessment');	
	jQuery('#frmAssessment .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmAssessment textarea').css('text-transform', 'uppercase');
	formatDateBox('dteAsmmEvaluationDate','dd-MMM-yyyy');
	var mode=jQuery('#mode').val();		
	
	if (mode=="insert")
		fillWithCurrentDate("dteAsmmEvaluationDate");	
	var asmmTrarKeyid=jQuery('#txtAsmmTrarKeyid').val();
	var asmmRoleKeyid=jQuery('#txtAsmmRoleKeyid').val();
	var asmmEmpmKeyid=jQuery('#txtAsmmEmpmKeyid').val();
	var asmmEvlNo=jQuery('#txtAsmmEvaluationNo').val();	
	var evalType=getFieldValue('cmbAsmmEvaluationType');	
	var keyid=jQuery('#txtAsmmKeyid').val();
	var evalDesc=jQuery('#txtAsmmEvaluationDesc').val();

	if(evalDesc != null && evalDesc != '' && evalDesc != ' ')
	{
		if(evalDesc.indexOf('[') > 0)
		{
			var desc = evalDesc.substring(0,evalDesc.indexOf('['));
			//alert(desc);
			jQuery('#txtAsmmEvaluationDesc').val(desc);
		}
	}
	
	
	fillComboBox("frmAssessment","cmbAsmdBachKeyid","combo_Batch.postass?q=2&employee="+asmmEmpmKeyid);	
	//fillComboBox("frmAssessment","cmbAsmdProgKeyid","combo_Program.postass?q=2&role="+asmmRoleKeyid);
	fillComboBox("frmAssessment","cmbAsmdProgKeyid","combo_Program.postass?q=2&employee="+asmmEmpmKeyid);			
	fillComboBox("frmAssessment","cmbAsmdTopiKeyid","combo_Topic.postass" );
	fillComboBox("frmAssessment","cmbAsmdResult","combo_Result.postass");
	fillComboBox("frmAssessment","cmbAsmmEvaluationType","combo_EvaluationType.postass");	
	//fillComboBox("frmAssessment","txtAsmmTrarKeyid","combo_TrainingArea.postass");	
	numericTextBox('txtAsmdScore');
	numericTextBox('txtAsmdCutoff');
	
	if(evalType=="PRE"){		
		disableField('frmAssessment','cmbAsmdBachKeyid');
		disableField('frmAssessment','cmbAsmdProgKeyid');
	}
	else{
		enableFormFields('frmAssessment','cmbAsmdBachKeyid');
		enableFormFields('frmAssessment','cmbAsmdProgKeyid');
	}
	
	//processAjaxCalls("getTrainingAreaPath.postass?q=2&trarKeyId="+trarKeyId,"","trainingAreaSuccess","trainingAreaError");
	processGridnew('AssessmentEvl_input.postass','?q=2&AsmmRoleKeyid='+asmmRoleKeyid+"&asmmEmpmKeyid="+asmmEmpmKeyid + "&keyid="+keyid + "&asmmEvlNo=" + asmmEvlNo+ "&evlType=" + evalType+ "&asmmTrarKeyid=" + asmmTrarKeyid,"list","pager","","docDoubleClick","","GridLoadCompleted");
	disable();
	jQuery("#list").setGridParam({multiselect: ( false )});	
	jQuery("#btnAdd").click(function(){
		//alert("add");txtAsmdScore 
		var cutoffval = jQuery('#txtAsmdCutoff').val();
		var score     = jQuery('#txtAsmdScore').val();
		var res       = getFieldValue('cmbAsmdResult','frmAssessment');
		var evalType = getFieldValue('cmbAsmmEvaluationType');	
		var evalDate = getFieldValue('dteAsmmEvaluationDate');
		var empKeyid = jQuery('#txtAsmmEmpmKeyid').val();
		/*for setting value to row */
		var progKey = jQuery("#cmbAsmdProgKeyid").combobox("getValue");
		var progName = jQuery("#cmbAsmdProgKeyid").combobox("getText");
		var batchKey = jQuery("#cmbAsmdBachKeyid").combobox("getValue");
		var batchName = jQuery("#cmbAsmdBachKeyid").combobox("getText");
		var result = null;
		if(res == 'P')
			result = "PASS";
		else
			result = "FAIL";
		jQuery('#hdnrowData').val(score+","+result+","+progKey+","+progName+","+batchKey+","+batchName);
		//var dataStr = "&evalType="+evalType+"&evalDate="+evalDate+"&empKeyid="+empKeyid;
		//processAjaxCalls("checkCurrentDate.postass?q=2&dataStr="+dataStr,"","getcurrentDateSuccess","getcurrentDateError");
		
		if(res == ' ' || res == '' || res == null){
			alert('Select Pass/Fail');
			return false;
		} 
		if(res == 'P'){
			if(score == '' || score == ' ' || score == '0' || score == null)
				jQuery('#txtAsmdScore').val(cutoffval);
			
		}
		else
			jQuery('#hdnAsmdCurrentRate').val('');
		var url="AssessmentEvl_save.postass?access=true";	
		setTimeout(function() {saveForm("frmAssessment",url);},1250);	
		return true;
	});
	
	jQuery("#btnDel").click(function(){
		//alert("add");
		if(confirm("Do You Want To Delete This Assessment?") == true){		
			var amsmKeyid=jQuery('#txtAsmmKeyid').val();
			var amsdKeyid=jQuery('#txtAsmdKeyid').val();
			//alert(amsmKeyid +  " : " + amsdKeyid);
			var url="AssessmentEvl_delete.postass?q=2&amsmKeyid=" + amsmKeyid + "&amsdKeyid=" + amsdKeyid;	
			deleteRecord("frmAssessment",url);	
			return true;
		}
	});
	disableUIButton("btnChk");
	jQuery("#btnChk").click(function(){	
		var size=0;			
		/*var previousTopicId=getFieldValue('txtTopiId','frmAssessment');
		var currentTopicId=getFieldValue('cmbAsmdTopiKeyid','frmAssessment');
		
		//alert("previousTopicId:" + previousTopicId + "currentTopicId:" +  currentTopicId);
		//alert(parseInt(jQuery('#txtChkListSize').val()));
		//alert(previousTopicId);		
		if (previousTopicId!=currentTopicId || (currentTopicId!=null && previousTopicId==null)){			
			jQuery('#txtChkListSize').val('');
			getCheckList();
			setFieldValue('txtTopiId',currentTopicId,'frmAssessment');
		}
		else{*/
			size=jQuery('#txtChkListSize').val();		
			//alert(size);		
			if (parseInt(size)>0){
				/*jQuery('#checkList').css("display","block");
				jQuery('#checkList').addClass('loadExpToExcel');		
				jQuery('#checkList').show;		
				return true;	*/
				opencheckListdialog();
			}	
			else {	
				alert("Check List Does Not Exists");
			}
		//}			
	});		


	 /*for file manager*///AssmntFilemgr

		jQuery("#btnFilManageAssmnt").click(function(){
			var documentNo =jQuery('#hdnAsmmKeyid').val();	
	 		
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"ASSM","","","");
				
			}
			else
	 			alert("Assesment not Avilable to  FileManager");
			
		});
		 setTimeout(function() {setFocusOnField('txtAsmmEvaluationDesc');},550);
		 var keyid=getFieldValue('hdnAsmmKeyid','frmAssessment');	
		 fileManagerPopUp(keyid,"ASSM","frmAssessment","btnFilManageAssmnt","AssmntFilemgr");
});
/*for file manager*/
function btnFilManageAssmnt_click(){
	 
		var documentNo =getFieldValue('hdnAsmmKeyid','frmAssessment');	
		 
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"ASSM","","","");
		}
		else
			alert("Program should be selected to  FileManager");
		
	} 
function checkMaxScore(){
	var score = jQuery('#txtAsmdScore').val();
	if(parseInt(score) >100)
	{
		 alert("Maximum score is 100");
		 jQuery('#txtAsmdScore').val('');
		// jQuery("#txtAsmdScore").focus();
		 setTimeout(function() {setFocusOnField('txtAsmdScore');},550);
		 clearField("cmbAsmdResult");
		 enableField("cmbAsmdResult");
		// setFocusOnField('txtAsmmEvaluationDesc');
		
	}
	
}
function opencheckListdialog(){

	jQuery('#mstfrm_div').addClass('popup-mask');	
	jQuery('#mstfrm_div').show();
	jQuery('#checkList').addClass('custom-popup');		
    jQuery('#checkList').show();		  
    jQuery('#checkList').css("display","block");
	jQuery('#checkList').addClass('loadExpToExcel');	
    jQuery('#checkList').css('z-index',100);
    jQuery('#checkList').css('top','9');
   
    jQuery('.popup-mask').css('z-index',3); 
}
function checkCheckedList(){
	var previousTopicId=getFieldValue('txtTopiId','frmAssessment');
	var currentTopicId=getFieldValue('cmbAsmdTopiKeyid','frmAssessment');
	var size=0;	
	//alert("previousTopicId:" + previousTopicId + "currentTopicId:" +  currentTopicId);
	//alert(parseInt(jQuery('#txtChkListSize').val()));
	//alert(previousTopicId);		
	if (previousTopicId!=currentTopicId || (currentTopicId!=null && previousTopicId==null)){			
		jQuery('#txtChkListSize').val('');
		getCheckList();
		setFieldValue('txtTopiId',currentTopicId,'frmAssessment');
	}
	else{
		size=jQuery('#txtChkListSize').val();
		if (parseInt(size)>0){
			enableUIButton("btnChk");			
		}	
		else {	
			disableUIButton("btnChk");	
		}
	}	
	setTimeout(function() {setFocusOnField('txtAsmdScore');},550);		
}

function trainingAreaSuccess(){
	//alert("Success");
}

function trainingAreaError(){
	//alert("error");
}

function frmAssessmentcmbAsmdBachKeyid_onLoadSuccess(){}
function frmAssessmentcmbAsmdBachKeyid_onSelect(record){
	processAjaxCalls("getProgBasedOnBatch.postass?q=2&bachKeyid="+record.id,"","getProgSuccess","getProgError");
}
function frmAssessmentcmbAsmdProgKeyid_onSelect(record){
	var asmmEmpmKeyid=jQuery('#txtAsmmEmpmKeyid').val();
	reloadCombo("frmAssessment","cmbAsmdBachKeyid","combo_Batch.postass?q=2&progKey="+record.id+"&employee="+asmmEmpmKeyid);
}
function frmAssessmentcmbAsmdProgKeyid_onLoadSuccess(){}
function frmAssessmenttxtAsmmTrarKeyid_onLoadSuccess(){}
function dteAsmmEvaluationDate_onChange(){}
function frmAssessmentcmbAsmdTopiKeyid_onLoadSuccess(){}

function getProgSuccess(result){
	setFieldValue('cmbAsmdProgKeyid',result.progKey,'frmAssessment');
}
function getProgError(result){
	
}
function disable(){	
	disableField('frmAssessment','cmbAsmmEvaluationType');
	disableField('frmAssessment','txtAsmmTrarKeyid');
	disableField('frmAssessment','cmbAsmdTopiKeyid');
	disableField('frmAssessment','txtAsmdCutoff');
	disableUIButton("btnAdd");
	disableUIButton("btnDel");
	disableUIButton("btnFilManageAssmnt");
}

function resetControls(){	
	jQuery('#cmbAsmdResult').combobox('clear');
	jQuery('#cmbAsmdBachKeyid').combobox('clear');
	jQuery('#cmbAsmdProgKeyid').combobox('clear');
	jQuery('#cmbAsmdTopiKeyid').combobox('clear');	
	jQuery('#txtAsmdCutoff').val('');
	jQuery('#txtAsmdScore').val('');	
}

function GridLoadCompleted(id)
{		
	//alert('GridLoadCompleted');	
	
	//
	var rowId = jQuery("#list").jqGrid('getDataIDs');
	
	docDoubleClick(rowId[0]);//for selecting first rowdata on load
	jQuery("#list tr[id="+ rowId[0] +"]").find(' td:first').addClass("ui-state-highlight");
	var rowData = jQuery("#list").jqGrid('getRowData',rowId);
	
	jQuery('#hdnTotalNoRows').val(rowId.length);
	jQuery('#hdnAsmmKeyid').val(rowData.ASMD_KEYID);
	if(rowData.ASMD_KEYID.trim().length>0)
		enableUIButton("btnFilManageAssmnt");
}

function frmAssessment_successsCallback(result){	
	//alert("success");	
	if( result.keyid )
	{
		//jQuery("#list").trigger("reloadGrid");	
		jQuery('#txtAsmmKeyid').val(result.keyid);
		resetControls();			
		disableUIButton("btnAdd");
		disableUIButton("btnDel");
		/*added for looping to next row --- 31-01-2013*/
		var prevRowid = jQuery('#hdnDblClickGrdId').val();
		var currRowid=parseInt(prevRowid )+1;
		var totalRowLength =jQuery('#hdnTotalNoRows').val();
		var rowData = jQuery('#hdnrowData').val();
		var colData =rowData.split(',');
		
		if(prevRowid <= totalRowLength)	{
			if(prevRowid != totalRowLength)
				docDoubleClick(currRowid);//alert(prevRowid+" -- "+ currRowid+" -- "+ totalRowLength);
			jQuery("#list").jqGrid('setCell',prevRowid,"ASMD_KEYID",result.Assmntdtlkeyid);
			jQuery("#list").jqGrid('setCell',prevRowid,"ASMD_SCORE",colData[0]);
			
			
			jQuery("#list").jqGrid('setCell',prevRowid,"ASMD_RESULT",colData[1]);
			if(colData[2].trim().length>0){
				jQuery("#list").jqGrid('setCell',prevRowid,"PROG_KEYID",colData[2]);
				jQuery("#list").jqGrid('setCell',prevRowid,"PROG_NAME",colData[3]);
			}
			if(colData[4].trim().length>0){
				jQuery("#list").jqGrid('setCell',prevRowid,"BACH_KEYID",colData[4]);
				jQuery("#list").jqGrid('setCell',prevRowid,"BACH_NAME",colData[5]);
			}
			var rowObject = jQuery("#list").getRowData(prevRowid);
			jQuery("#list").setRowData(prevRowid,rowObject,true);
			//jQuery("#list").jqGrid('setSelection', currRowid, false);
			
				jQuery("#list tr[id="+ prevRowid +"]").find(' td').removeClass("ui-state-highlight");
				jQuery("#list tr[id="+ currRowid +"]").find(' td:first').addClass("ui-state-highlight");
				if(prevRowid == totalRowLength)
				jQuery("#list tr[id="+ currRowid +"]").find(' td:first').addClass("ui-state-highlight");
			
		}
	}
}

function  frmAssessmentcmbAsmdTopiKeyid_onSelect(record){
	getCheckList();
}

function getCheckList(){
	//alert('getdetials');
	setTimeout(function() {var curRating = jQuery('#hdnAsmdCurrentRate').val();
	var topicId=getFieldValue("cmbAsmdTopiKeyid");	
	var asmmTrarKeyid=jQuery('#txtAsmmTrarKeyid').val();
	var asmmRoleKeyid=jQuery('#txtAsmmRoleKeyid').val();
	var asmmEmpmKeyid=jQuery('#txtAsmmEmpmKeyid').val();
	var asmdKeyid=jQuery('#txtAsmdKeyid').val();
	var asmmEvlNo=jQuery('#txtAsmmEvaluationNo').val();	
	var evalType=getFieldValue('cmbAsmmEvaluationType');
	var keyid=jQuery('#txtAsmmKeyid').val();
	var params="&topicId=" + topicId + "&AsmmRoleKeyid="+asmmRoleKeyid+"&asmmEmpmKeyid=" +asmmEmpmKeyid+"&asmdKeyid="+asmdKeyid+"&curRatting="+curRating ;
	params=params+ "&keyid="+keyid + "&asmmEvlNo=" + asmmEvlNo+ "&evlType=" + evalType+ "&asmmTrarKeyid=" + asmmTrarKeyid;
	processAjaxCalls("getCheckList.postass?q=2" + params,"","getDetailsSuccess","getDetailsError");},1250);		
}

function getDetailsSuccess(result){
	//alert("success");
	convertJsonListToControl(result);	
}

function frmAssessmenttxtAsmmTrarKeyid_onSelect(){}

function frmAssessment_beforeSubmit(){
	//alert("beforeSubmit");
	var flg=false;	
	var cols = jQuery('#txtChkListSize').val();
	//alert("beforeSubmit "+cols);
	for( var i = 1; i <=cols;i++){			
		var select=jQuery('#chkSelect' +i).attr('checked');
		var keyid=jQuery('#keyId' + i).val();
		var chkKeyId=jQuery('#chkKeyId' + i).val();
		if (select=='checked' || keyid!=""){
			flg=true;
		}
	}
	//alert(flg);	
	if (flg==true){
		var gridData  = '&multiplemethods='+convertControlsToJSONString();
		//alert(gridData);		
		return gridData; 
	}			
}

function convertControlsToJSONString(){
	var cols = jQuery('#txtChkListSize').val();
	var jsonArrO='[';
	//alert("convertControlsToJSONString cols " + cols);
	var topiKeyId=getFieldValue('cmbAsmdTopiKeyid','frmAssessment');
	//alert(topiKeyId);
	for( var i = 1; i <=cols;i++){		
			
		var select=jQuery('#chkSelect' +i).attr('checked');
		var keyid=jQuery('#keyId' + i).val();
		var chkKeyId=jQuery('#chkKeyId' + i).val();
		
		if (select=='checked'){			
			jsonArrO += '{';
			jsonArrO += '"txtAsclKeyid":"' + keyid +'",'; 
			jsonArrO += '"txtAsclTopiKeyid":"' + topiKeyId +'",';
			jsonArrO += '"txtAsclChekKeyid":"' + chkKeyId +'",'; 
			jsonArrO += '"txtAsclActive":"Y",'; 
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 				
		}
		else {			
			if(keyid!=null && keyid!="" && keyid!="undefined" && keyid!="null"){				
				jsonArrO += '{';
				jsonArrO += '"txtAsclKeyid":"' + keyid +'",'; 
				jsonArrO += '"txtAsclTopiKeyid":"' + topiKeyId +'",';
				jsonArrO += '"txtAsclChekKeyid":"' + chkKeyId +'",'; 
				jsonArrO += '"txtAsclActive":"N",'; 
				jsonArrO = jsonArrO.slice(0, -1) + "},"; 
			}
		}		
	}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	return jsonArrO; 	
}

function calCheckCount(){
	var cols = jQuery('#txtChkListSize').val();
	var count=0;
	for( var i = 1; i <=cols;i++){	
		var select=jQuery('#chkSelect' +i).attr('checked');		
		if (select=='checked'){	
			count=parseInt(count)+1;	
		}		
	}	
	return count; 	
}
//chkSelectNo

function convertJsonListToControl(result){
	var size=result.size;
	if(size=="0" || size==0){	
		jQuery('#checkList').html('');		
		jQuery('#checkList').css("display","none");	
		jQuery('#txtChkListSize').val(0);
		disableUIButton("btnChk");		
		//alert("Check List Does Not Exists");
		return false;
	}
	var totalCol =result.checklist.length;	
	if (totalCol==0){		
		jQuery('#txtChkListSize').val(0);
		disableUIButton("btnChk");	
		return false;
	}
	jQuery('#txtChkListSize').val(totalCol);
	enableUIButton("btnChk");
	var controlsHtml='';
	controlsHtml=controlsHtml+'<div  class="sub-header" style="width:100%;"><label >Check Point</label> </div>';
	controlsHtml=controlsHtml+'<div style="height:260px;overflow:auto;padding-bottom:5px;">';
	controlsHtml=controlsHtml+'<table align="center" rule="all" style="border:solid 1px#c1c1c1">';	
	controlsHtml=controlsHtml +'<tr style="background-color:#AFD6FE;">';
	controlsHtml=controlsHtml +'<td valign="top" align="center" style="border-bottom:solid 1px #c1c1c1;border-left:solid 1px #c1c1c1;width:50px;">';
	controlsHtml=controlsHtml +'<div class="floatleft" style="width:50px;font-size:12px;font-weight:bold;background-color:#AFD6FE;"><span id="ys" style="margin-right:10px;border-right:solid 2px #F9FCFE;">Yes </span><span id="n">No</span>';	
	controlsHtml=controlsHtml +'</div>';	
	controlsHtml=controlsHtml +'</td>';
	controlsHtml=controlsHtml +'<td valign="top"  align="center" style="border-bottom:solid 1px #c1c1c1;">';
	controlsHtml=controlsHtml +'<div class="floatleft" style="width:335px; font-size:12px;font-weight:bold;background-color:#AFD6FE;"><span>Check Point</span>';	
	controlsHtml=controlsHtml +'</div>';	
	controlsHtml=controlsHtml +'</td>';
	controlsHtml=controlsHtml +'<td valign="top" align="center" style="border-bottom:solid 1px #c1c1c1;border-left:solid 1px #c1c1c1;">';
	controlsHtml=controlsHtml +'<div class="floatleft" style="width:338px;font-size:12px;font-weight:bold;background-color:#AFD6FE;"><span>Remarks</span>';	
	controlsHtml=controlsHtml +'</div>';	
	controlsHtml=controlsHtml +'</td></tr>';			
	for( var i = 0; i <result.checklist.length;i++){
		var check=false;
		var select=result.checklist[i][0];
		var keyId=result.checklist[i][1];		
		var chkKeyId=result.checklist[i][2];		
		var name=result.checklist[i][3];
		var remarks=result.checklist[i][4];
		//alert(remarks);
		if(keyId=='0' || keyId==null || keyId=='null' || keyId=="")
			keyId="";
		else
			check=true;
		
		controlsHtml=controlsHtml +'<tr><td valign="top" style="border-bottom:solid 1px #c1c1c1;" >';
		controlsHtml=controlsHtml +'<div class="floatleft" style="width:24px;">';
		controlsHtml=controlsHtml + CreateSelect(i+1,select,check);
		controlsHtml=controlsHtml + '</div>';
		controlsHtml=controlsHtml +'<div class="floatleft" style="width:15px;margin-top:3px;">';	
		controlsHtml=controlsHtml + CreateSelectNo(i+1,select,check);	
		controlsHtml=controlsHtml + '</div>';
		controlsHtml=controlsHtml + CreateKeyId(i+1,keyId);
		controlsHtml=controlsHtml + CreateChkKeyId(i+1,chkKeyId);
		controlsHtml=controlsHtml +'</td><td valign="top"  style="border-bottom:solid 1px #c1c1c1;">';
		controlsHtml=controlsHtml +'<div class="floatleft" style="width:330px;font-size:11px;">';			
		controlsHtml=controlsHtml + CreateLbl(i+1,name);
		controlsHtml=controlsHtml + '</div>';	
		controlsHtml=controlsHtml +'</td><td valign="top"  style="border-bottom:solid 1px #c1c1c1;border-left:solid 1px #c1c1c1;">';
		controlsHtml=controlsHtml +'<div class="floatleft" style="width:338px;font-size:11px;">';	
		controlsHtml=controlsHtml + CreateLbl(i+1,remarks);
		controlsHtml=controlsHtml + '</div>';	
		controlsHtml=controlsHtml +'</td></tr>';
	}	
	controlsHtml=controlsHtml +'</table></div>';
	controlsHtml=controlsHtml +'<div class="floatleft" style="padding-left:289px;">';
	controlsHtml=controlsHtml +'<input type="button" class="easyui-button" style="height: 20px;width:70px;" id="btnOk" name="btnOk" value="Ok">';
	controlsHtml=controlsHtml +'</div>';
	controlsHtml=controlsHtml +'<div class="floatleft" style="padding-left:3px;">';
	controlsHtml=controlsHtml +'<input type="button" class="easyui-button" style="height: 20px;width:70px;" id="btnCancel" name="btnCancel" value="Cancel">';
	controlsHtml=controlsHtml +'</div>';
	controlsHtml=controlsHtml +'<div class="floatleft" style="padding-left:50px;">';	     	
	controlsHtml=controlsHtml +'<label>CutOff:</label>  ';
	controlsHtml=controlsHtml +'<span id="spnScore">0</span>';
	controlsHtml=controlsHtml +'</div>';
	controlsHtml=controlsHtml +'<div class="floatleft" style="padding-left:50px;">';	  
	controlsHtml=controlsHtml +'<label>Score:</label>  ';
	controlsHtml=controlsHtml +'<span id="spnPercentage">0</span>';
	controlsHtml=controlsHtml +'</div>';
	//alert(controlsHtml);
	if(screen.width <= 1280){
		jQuery('#checkList').css('width','62%');
	}
	else
		jQuery('#checkList').css('width','58%');
	jQuery('#checkList').html(controlsHtml);	
	jQuery('#checkList').css("display","none");
	jQuery('#checkList').css('margin-left','5%');     
    jQuery('#checkList').css('z-index',100);     
    
	jQuery('#checkList').css('height','auto');
	//jQuery('#checkList').css("display","block");
	jQuery('#checkList').addClass('loadExpToExcel');		
	jQuery('#checkList').show;
			
	var cols = jQuery('#txtChkListSize').val();
	//alert(cols);
	for( var i = 1; i <=cols;i++){
		var keyId=jQuery('#keyId' + i).val();
		if(keyId!="" && keyId!=null && keyId!="null"){
			jQuery('#chkSelect' + i).attr('checked',true);
		}
	}
	getCheckScorePercentage();
		
	jQuery("#btnOk").click(function(){
		getCheckScoreCnt();
		 jQuery( '#checkList' ).hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#checkList' ).removeClass('custom-popup');
	});
	
	jQuery("#btnCancel").click(function(){
		setFieldValue('txtTopiId','','frmAssessment');		
		getCheckScoreCnt();
		 jQuery( '#checkList' ).hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#checkList' ).removeClass('custom-popup');
	});
}
function getCheckScoreCnt(){
	jQuery('#checkList').css("display","none");		
	jQuery('#checkList').removeClass('loadExpToExcel');		
	var count=parseInt(calCheckCount());		
	var cols = jQuery('#txtChkListSize').val();
	if (parseInt(cols)>0 && parseInt(count)>0)
	{
		var percentage=parseInt(count)*100;
		percentage=parseInt(percentage)/cols;			
		jQuery('#txtAsmdScore').val(parseInt(percentage));
		calResult();	
		return true;
	}
	else{
		jQuery('#txtAsmdScore').val(0);
	}		
}

function getCheckScorePercentage(){
	//alert("getCheckScorePercentage");
	var count=parseInt(calCheckCount());		
	var cols = jQuery('#txtChkListSize').val();
	if (parseInt(cols)>0 && parseInt(count)>0)
	{
		var percentage=parseInt(count)*100;
		percentage=parseInt(percentage)/cols;			
		jQuery('#txtAsmdScore').val(parseInt(percentage));
		jQuery('#spnScore').html(parseInt(jQuery('#txtAsmdCutoff').val()));
		jQuery('#spnPercentage').html(parseInt(percentage));
		calResult();	
		return true;
	}
	else{
		jQuery('#txtAsmdScore').val(0);
		jQuery('#spnScore').html(0);
		jQuery('#spnPercentage').html(0);
	}
}

function CreateSelect(col,name,check){	
	return '<span style="padding-right:10px;"><input type="checkbox" onclick="chkOnClick('+ col +');"   style="width:15px" id="chkSelect'+col+'" /></span>';			
}
function CreateSelectNo(col,name,check){	
	return '<span><input type="checkbox" onclick="chkOnClickNo('+ col +');"   style="width:15px" id="chkSelectNo'+col+'" /></span>';			
}

function CreateLbl(col,name){	
	return '<span style="padding-left:3px;"><label style="word-wrap:break-word;">'+ name+ '</label></span>';	 
}

function CreateChkKeyId(col,name){	
	return '<input type="hidden" id="chkKeyId'+col+'" name="chkKeyId'+col+'" value="'+name+'"/>';	 
}

function CreateKeyId(col,name){	
	return '<input type="hidden" id="keyId'+col+'" name="keyId'+col+'" value="'+name+'" />';	 
}

function getDetailsError(){}

function frmAssessment_deleteSuccessCallback(result){
	alert(result.successData.msg);
	//alert(result.keyid);	
	if(result.keyid!=null && result.keyid!='null' && result.keyid!='undefined')
	{
		setFieldValue('txtAsmmKeyid',result.keyid);
		resetControls();
		jQuery("#list").trigger("reloadGrid");	
		disableUIButton("btnAdd");
		disableUIButton("btnDel");	
	}
	else{		
		navigateToPrevForm(result);
	}	
}

function chkOnClick(col){
	/*if(jQuery('#chkSelect' +col).is(':checked') == true)			
		jQuery('#chkSelect' +col).attr('checked',true);	
	else		
		jQuery('#chkSelect' +col).attr('checked',false);
	*/
	if(jQuery('#chkSelect' +col).is(':checked') == true){					
		jQuery('#chkSelect' +col).attr('checked',true);
		jQuery('#chkSelectNo' +col).attr('checked',false);	
	}
	else{		
		jQuery('#chkSelect' +col).attr('checked',false);
		//jQuery('#chkSelectNo' +col).attr('checked',true);
	}	
	getCheckScorePercentage();	
}

function chkOnClickNo(col){
	if(jQuery('#chkSelectNo' +col).is(':checked') == true){					
		jQuery('#chkSelectNo' +col).attr('checked',true);
		jQuery('#chkSelect' +col).attr('checked',false);	
	}
	else{		
		jQuery('#chkSelectNo' +col).attr('checked',false);
		//jQuery('#chkSelect' +col).attr('checked',true);
	}
	getCheckScorePercentage();	
}

jQuery("#txtAsmdScore").change(function(){	
	calResult();	
});

function frmAssessmentcmbAsmdResult_onLoadSuccess(){}

function calResult(result){
	var cutoff=jQuery('#txtAsmdCutoff').val();
	var score=jQuery('#txtAsmdScore').val();
	//alert(cutoff+" "+ parseInt(score) +" -- "+score );
	var checkZero = score.substring(0,1);
	var checkdecimal = score.substring(1,2);
	if(checkZero=="0"){
		if(checkdecimal!=".")
			score=score.substring(1);
	}
	//alert(checkZero +" ~~ "+ score);
	enableFormFields('frmAssessment','cmbAsmdResult');
	setFieldValue('cmbAsmdResult','','frmAssessment');
	//alert(cutoff+" "+ parseInt(score) +" -- "+score );
	if(parseInt(score)==0 || score==null || score=="" || score=='null'){
		if(parseInt(score)==0 ){
		setFieldValue('cmbAsmdResult',"F",'frmAssessment');
		disableField('frmAssessment','cmbAsmdResult');
		}
	}
	else if (parseInt(score)>=parseInt(cutoff)){		
		setFieldValue('cmbAsmdResult','P','frmAssessment');
		disableField('frmAssessment','cmbAsmdResult');
	}
	else if (parseInt(score)<parseInt(cutoff)){		
		setFieldValue('cmbAsmdResult','F','frmAssessment');
		disableField('frmAssessment','cmbAsmdResult');
	}
	else if(result.trim().length>0){
		var res;
		if(result.trim() == "PASS")
			res ='P';
		else
			res='F';
		
		setFieldValue('cmbAsmdResult',res,'frmAssessment');

		}
	
}

function docDoubleClick(id)
{	//alert('rowid '+id);
	jQuery('#hdnDblClickGrdId').val(id);
	var rowData = jQuery("#list").jqGrid('getRowData',id);	
	var keyId = rowData.ASMD_KEYID;
	var topicId = rowData.TOPI_KEYID;
	var spokeId = rowData.SPOK_KEYID;	
	var cutOff = rowData.RTRL_CUTOFF;	
	var progId = rowData.PROG_KEYID;	
	var result = rowData.ASMD_RESULT;	
	var Score = rowData.ASMD_SCORE;	
	var bachKeyId = rowData.BACH_KEYID;
	
	var assEmpKeyId = jQuery('#txtAsmmEmpmKeyid').val();
	var assRoleKeyId = jQuery('#txtAsmmRoleKeyid').val();
	var evalType = getFieldValue('cmbAsmmEvaluationType');	
	var mode =jQuery("#mode").val();
	var trarkey = jQuery('#txtAsmmTrarKeyid').val();
	var dataStr = ' ';
	//alert(keyId +" -- "+ mode);
	
	
	if(keyId != ' ' && keyId != '' && keyId != null){
		mode = "modify";
		dataStr += "&asmdKeyId="+keyId;	
	}
	if(result != ' ' && result != '' && result != null){
		dataStr += "&result="+result;
		
	}
	dataStr += '&assEmpKeyId='+assEmpKeyId+'&topicId='+topicId+'&assRoleKeyId='+assRoleKeyId+'&evalType='+evalType+"&mode="+mode+"&trarkey="+trarkey;
	processAjaxCalls("getCutOff.postass?q=2&dataStr="+dataStr,"","CutOffSuccess","CutOffError");
	
	if (mode=="modify" || mode=="insert"){
		enableUIButton("btnAdd");
		//alert(keyId);
		if(keyId!="" && keyId!=null && keyId!="null" && keyId!=" " && keyId!="undefined")
			enableUIButton("btnDel");
		else
			disableUIButton("btnDel");
	}
	
	setFieldValue('cmbAsmdTopiKeyid',topicId,'frmAssessment');	
		
	jQuery('#txtAsmdKeyid').val(keyId);	
	jQuery('#txtAsmdCutoff').val(cutOff);
	jQuery('#txtAsmdSpokKeyid').val(spokeId);
	jQuery('#txtAsmdScore').val(Score.trim());	
	
	if(progId!="" && progId!=null && progId!="null" && progId!=" " && progId!="undefined")	
		setFieldValue('cmbAsmdProgKeyid',progId,'frmAssessment');
	else
		clearField("cmbAsmdProgKeyid");	
	
	if(bachKeyId!="" && bachKeyId!=null && bachKeyId!="null" && bachKeyId!=" " && bachKeyId!="undefined")	
		setFieldValue('cmbAsmdBachKeyid',bachKeyId,'frmAssessment');	
	
	calResult(result);	
	checkCheckedList();		
}
function CutOffSuccess(result){
 
	if(result.minCutOff != '0'){
		var Cutof = result.minCutOff.split("/");
	    jQuery('#txtAsmdCutoff').val(Cutof[0]);
		jQuery('#hdnAsmdCurrentRate').val(Cutof[1]);
		
	}
	
	else
		jQuery('#txtAsmdCutoff').val(result.minCutOff);
	if( result.tpmException ){     		
		jQuery('#dispErr').html('<h5> ' +result.tpmException +'</h5>');
		div_err();
	}
}
function CutOffError(result){

}
</script>

	
	
<form name="frmAssessment" id="frmAssessment">	
	<div id="wrapperRpt" style="margin-top: 1%\9;">
	 <table style="width:125%;width:110%\9;" >
	  	<tr>
	  		<td >
	  			<div  class="floatleft">             	
            		<div class="floatleft" >
	                    <div  class="easyui-paddingbfpx">
	                        <label>Training Area - Role - Employee</label>                  
	                    </div> 
	                    <div id="" style="font-weight: bold;font-size: 11px;border:solid 1px  #008BC2;background-color:#fff;width:849px;height:19px;">
							<span id="divTrainFuncLoc" style="padding-left:5px;">${requestScope.trainingPath}</span>
						</div>
               		</div>
               	</div>
	  		</td>
	  	</tr>
        <tr>
             <td >
             	<div  class="floatleft">
	               	
	             	<div class="floatleft" style="padding-left:0px;">
		                <div  class="easyui-paddingbfpx">
		                    <label class="mandatory-lbl">Evaluation Desc</label>                  
	                    </div> 
	                    <div class="easyui-paddingbfpx">                         
	                        <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtAsmmEvaluationDesc" name="txtAsmmEvaluationDesc" maxlength="500" style="resize:none;width:730px;height:45px" <c:out value = "${requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>>${requestScope.entTlAssessmentmst.asmmEvaluationDesc}</textarea>                       
	                 	</div>                   
	                </div>
           		
	                <div  class="floatleft" style="padding-left:20px;">                    
	                    <div  class="">
	                    	<label class="mandatory-lbl">Evaluation Date</label>
	                    </div>
	                    <div class="easyui-paddingbfpx"  style="padding-top: 5px;"> 
	                        <input id="dteAsmmEvaluationDate" name="dteAsmmEvaluationDate" class="easyui-datebox"  style="width:110px;"  value="${requestScope.entTlAssessmentmst.asmmEvaluationDate}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
						</div>
					</div>
					 
	                <div  class="floatleft" style="padding-left:20px;">    
	                   	 <div  class="">
	                    	<label>Evaluation No</label>
	                    </div>
	                     
	                    <div class="easyui-paddingbfpx" style="padding-top: 6px;"> 
	                        <input id="txtAsmmEvaluationNo" name="txtAsmmEvaluationNo" value="${requestScope.entTlAssessmentmst.asmmEvaluationNo}" style="width:70px;height:22px;text-align:right; " maxlength="3" class="easyui-text"  />                       
	                 	</div>	                 			                
		        	</div>
		        	<div  class="floatleft" style="padding-left:20px;">	
		        		 <div  class="">
	                    	<label>Evaluation Type</label>
	                    </div>
		        		<div class="easyui-paddingbfpx" style="margin-top:4px;">
	                        <input id="cmbAsmmEvaluationType" name="cmbAsmmEvaluationType" class="easyui-combobox"  style="width:120px"  value="${requestScope.entTlAssessmentmst.asmmEvaluationType}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/> >                       
	                    </div>	
	               </div>
	               <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/> 
        			<input type="hidden" id="txtAsmmKeyid" name="txtAsmmKeyid" value="${requestScope.entTlAssessmentmst.asmmKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
        			<input type="hidden" id="txtAsmmTrarKeyid" name="txtAsmmTrarKeyid" value="${requestScope.entTlAssessmentmst.asmmTrarKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
	        	</div>
            </td>
            
        </tr>        
        <tr>
        	<td> 
        		<div>
					<label class="notes"   style="font-weight: bold;">Assessment Details</label> 
					 <span id="AssmntFilemgr" style="position:absolute; left:1020px;"></span>
					 <!-- <div id="AssmntFilemgr" style=""> 
                     <input class="easyui-button" id="btnFilManageAssmnt" type="button" value="File Manager" style=" height : 21px;"> </div>-->
       			    
       			</div>
       					
       			<div class="floatleft" style="padding-top:0px;">	       			
	               	<div class="floatleft" >
	                    <div  class="easyui-paddingbfpx">
	                        <label class="mandatory-lbl">Topic</label>                  
	                    </div> 
	                    <div class="floatleft easyui-paddingbfpx">
	                        <input id="cmbAsmdTopiKeyid" name="cmbAsmdTopiKeyid" class="easyui-combobox"  style="width:250px"  value="${requestScope.entTlAssessmentdtl.asmdTopiKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/> >                       
	                    </div>
	               	</div>
	               	<div class="floatleft" style="padding-left:20px;">
	                    <div  class="easyui-paddingbfpx">
	                        <label >Cut Off</label>                  
	                    </div> 
	                    <div class="floatleft easyui-paddingbfpx">
	                    	<input id="txtAsmdCutoff" name="txtAsmdCutoff" value="${requestScope.entTlAssessmentdtl.asmdCutoff}" style="width:50px;height:22px;text-align:right" maxlength="2" class="easyui-text"  <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
	                    </div>
       				</div>
       				<div class="floatleft" style="padding-left:20px; padding-top:20px;padding-top:23px\9;">
	                    <div class="floatleft easyui-paddingbfpx">
	                    	<input type="button" class="easyui-button" style="height: 23px;width:70px;" id="btnChk" name="btnChk" value="...">
	                    </div>
	                    <div class="floatleft easyui-paddingbfpx" style="padding-left:5px;">
	                    	<div id ="checkList" class="flPopUpBox">
	                    	</div>
       					</div>
       				</div>
       				    
	               	<div class="floatleft" style="padding-left:20px;">
	                    <div  class="easyui-paddingbfpx">
	                        <label>Score</label>                  
	                    </div> 
	                    <div class="floatleft easyui-paddingbfpx" >
	                    	<input id="txtAsmdScore" name="txtAsmdScore" onblur="checkMaxScore();" value="${requestScope.entTlAssessmentdtl.asmdScore}" style="width:50px;height:22px;text-align:right;" maxlength="5" class="easyui-text"  <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
	                    </div>
       				</div>       				
     				<div class="floatleft" style="padding-left:20px;">
	                    <div  class="easyui-paddingbfpx">
	                        <label class="mandatory-lbl">Pass/Fail</label>                  
	                    </div> 
	                    <div class="easyui-paddingbfpx">
	                        <input id="cmbAsmdResult" name="cmbAsmdResult" class="easyui-combobox"  style="width:60px"  value="${requestScope.entTlAssessmentdtl.asmdResult}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/> >                       
	                    </div>	                    
       				</div>       				
       				<div class="floatleft" style="padding-left:20px;">
	                	<div  class="easyui-paddingbfpx">
	                        <label>Program</label>                  
	                    </div> 
	                    <div class="easyui-paddingbfpx">
	                        <input id="cmbAsmdProgKeyid" name="cmbAsmdProgKeyid" class="easyui-combobox"  style="width:166px"  value="${requestScope.entTlAssessmentdtl.asmdProgKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/> >                       
	                    </div>
	                </div>   
	                
	                <div class="floatleft" style="padding-left:20px;">
	                	<div  class="easyui-paddingbfpx">
	                 		<label>Batch</label>       						
	                    </div> 
	                    <div class="easyui-paddingbfpx"> 
	                       <input id="cmbAsmdBachKeyid" name="cmbAsmdBachKeyid" class="easyui-combobox"  style="width:166px"  value="${requestScope.entTlAssessmentdtl.asmdBachKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/> > 
	                    </div>   
                	</div>
                	
                	<div class="floatleft" style="padding-left:20px; padding-top:20px;">
	                    <div class="floatleft easyui-paddingbfpx" align="center">
	                    	<input type="button" class="easyui-button" style="height: 20px;width:70px;" id="btnAdd" name="btnAdd" value="Add">
	                    	<input type="button" class="easyui-button" style="height: 20px;width:70px;" id="btnDel" name="btnDel" value="Delete">
	                    </div>	                    
       				</div>
       				<input type="hidden" id="txtAsmdKeyid" name="txtAsmdKeyid" value="${requestScope.entTlAssessmentdtl.asmdKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
       				<input type="hidden" id="txtAsmdSpokKeyid" name="txtAsmdSpokKeyid" value="${requestScope.entTlAssessmentdtl.asmdSpokKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
       			</div>        		 
        	</td>
        </tr>
    </table>
    
	<div class="clear"></div>
	<div style="width: 100%;">
		<label class="notes"   style="font-weight: bold; padding-left:0px; " > ${requestScope.DoubleClick}</label>
		<table id="list" ></table>
		<div id="pager"></div>
	</div>	
	</div>
	<input type="hidden" id="txtAsmmRoleKeyid" name="txtAsmmRoleKeyid" value="${requestScope.entTlAssessmentmst.asmmRoleKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
	<input type="hidden" id="txtAsmmEmpmKeyid" name="txtAsmmEmpmKeyid" value="${requestScope.entTlAssessmentmst.asmmEmpmKeyid}" <c:out value = "${ requestScope.entTlAssessmentmstBean.disableForm == true ? ' disabled':''}"/>/>
	<input type="hidden" id="txtChkListSize" name="txtChkListSize" value="0" >  
	<input type="hidden" id="txtTopiId" name="txtTopiId" value="" >  
	<input type="hidden" id="hdnAsmmKeyid" name="hdnAsmmKeyid" value="${requestScope.asmmKeyid}" />
	<input type="hidden" id="hdnAsmdCurrentRate" name ="hdnAsmdCurrentRate" />
	<input type="hidden" id="hdnDblClickGrdId" name ="hdnDblClickGrdId" />
	<input type="hidden" id="hdnTotalNoRows" name ="hdnTotalNoRows" />
	<input type="hidden" id="hdnrowData" name ="hdnrowData" />
	
	 
</form>
	
