<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<style>
.padding
{
  padding-left:74px;
  padding-left:80px\9;
}
</style>

 <script type="text/javascript">	
  jQuery(document).ready(function(){
	  
	  initialiseForm('frmAuditCreation');  
	  jQuery('#submitForm').val('frmAuditCreation'); 
	  var documentNo =jQuery("#hdnJhamKeyid").val();
	  fileManagerPopUp(documentNo,"JHM","frmAuditCreation","btnFilManage","JhamFilemgr");	
	  var pillar =  jQuery("#hdnJhamAuditpillar").val();
	  var type =  getFieldValue("cmbJhamAudittype");
	  fillComboBox("frmAuditCreation","cmbJhamAuditteamid","jhapTemplate.jhAuditItc?pillar="+pillar +"&type="+type );
	 // fillComboBox("frmAuditCreation","cmbJhamAuditteamid","jhapTemplate.jhAuditItc?jhapAuditpillar="+jhapAuditpillar+"&jhapAudittype="+jhapAudittype);
	  fillComboBox("frmAuditCreation","cmbJhamAudittype","jhAuditTypeCombo.jhAuditItc","",false);
	  fillComboBox("frmAuditCreation","cmbJhamAuditlevel","jhAuditLevelCombo.jhAuditItc","",false);
	  fillComboBox("frmAuditCreation","cmbJhamJhstepid","JHTeamCombo.jhAuditItc");
	  formatDateBox('dteJhamAuditdate','dd-MMM-yyyy');
	  fillWithCurrentDate('dteJhamAuditdate'); 
	  var url = "jhAuditCreation_input.jhAuditItc";		  
	  var flId = jQuery("#hdnJhamFlid").val();
	  //jQuery("#frmAuditCreation input[id='flid']").val();
	  //alert('flId:'+flId);
	  var jhamKeyID=jQuery('#hdnJhamKeyid').val();
	  jQuery("#txtJhamTotalpoints").attr('readonly','readonly');
	  jQuery("#txtStatus").attr('readonly','readonly');
	  jQuery("#txtminmarks").attr('readonly','readonly');
	  //readOnlyFields('cmbJhamJhstepid');
	  //readOnlyFields('cmbJhamAuditlevel');
	  disableField('frmAuditCreation','cmbJhamAudittype');	  
	  var ketId =jQuery("#hdnJhamKeyid").val();
	  if (ketId.trim().length>0){
		 // readOnlyFields('cmbJhamJhstepid');
	  }
	  
	  if(flId != null && flId != ''){
		 //alert('flId:'+flId);	
		 viewGrid(url,"&s=1&flId="+flId+"&jhamKeyID="+jhamKeyID);
		 loadFunctionalLocation("auditCreationfunLocation","functionalLoc.commonFilter","auditCreationfunLocationValues","frmAuditCreation","&q=2&flid="+flId);
		 //LoadJHParameter(record.id);
		 //setMaxPointStatus();
		 //getStatus();
	  }else{	
		// alert('else flId:'+flId);		  
	  	 viewGrid(url,"&s=1");
	  	 var compId = jQuery("#frmAuditCreation input[id='company']").val();
		 var locnId = jQuery("#frmAuditCreation input[id='location']").val();
		 var factId = jQuery("#frmAuditCreation input[id='factory']").val();
		 var sectionId = jQuery("#frmAuditCreation input[id='seciton']").val();
		 var cellId = jQuery("#frmAuditCreation input[id='cell']").val();
		 var machId = jQuery("#frmAuditCreation input[id='machine']").val();		
		 // var flId = jQuery("#frmAuditCreation input[id='flid']").val();	 
	     var dataStr = "&compId="+compId +"&locnId="+locnId +"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId +"&machId="+machId;//+"&flid="+flId;
	     loadFunctionalLocation("auditCreationfunLocation","functionalLoc.commonFilter","auditCreationfunLocationValues","frmAuditCreation",dataStr);
	  }	
	  
	 
	  
	 /* jQuery("#btnCheckList").click(function(){
		  navigateToNextForm("VisualControlCheckList_input.visc","Visual Control Check List");
	  });
	  jQuery("#btnPiechart").click(function(){
		  showGraphData("jhAuditCreation_pieChart.jhAuditItc");
	  });*/
	  
	  jQuery("#cmbJhamAuditteamid").combobox({onRequest:function( ){
		  var pillar =  jQuery("#hdnJhamAuditpillar").val();
		  var type =  getFieldValue("cmbJhamAudittype");
	   	  return "pillar="+pillar +"&type="+type ;
  	  }});
	  
  });
  
  function setMaxPointStatus(){
	  var points =jQuery('#txtminmarks').val(); 
	  if(points==null || points=='')
		  points='0';	 	 
	  var totalPoints=jQuery('#txtJhamTotalpoints').val();
	  if(totalPoints==null || totalPoints=='')
		 totalPoints='0';	  		 
	  points = parseInt(points);
	  totalPoints = parseInt(totalPoints);
	 
	  if(totalPoints<points){
		jQuery('#hdnJhamStatus').val('F');
	  }else{
		jQuery('#hdnJhamStatus').val('P');
	  }
  }	
  
  function setStatus(){
	 
	  var status =  jQuery("#hdnJhamStatus").val();	  
	  if(status=='P'){
		 jQuery("#txtStatus").val('Cleared');
		 jQuery("#txtStatus").css('background-color','#72a98b');
	  }else if(status=='F'){
		 jQuery("#txtStatus").val('Not Cleared'); 
		 jQuery("#txtStatus").css('background-color','#ff9999');
	  }
  } 
  
  function btnFilManage_click(){
	    
	    var documentNo =jQuery("#hdnJhamKeyid").val();
		
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"JHM","","","");
		}
		
	}
  function frmAuditCreation_FuntLocHierarchy_SuccessCallBack(keyIds){
	  //alert('flid:'+keyIds.flid);	
	  var flid=jQuery("#hdnJhamFlid").val(); 
	  if (flid.trim().length<=0){
		  //alert('flid:'+keyIds.flid);	
		  clearFields();	
	  	  jQuery("#hdnJhamFlid").val(keyIds.flid);
	  	  processAjaxCalls("jhAuditCreation_recall.jhAuditItc?q=2&flId="+keyIds.flid+"&mode=EDIT","cmbJhamAudittype_onSelectSuccess","cmbJhamAudittype_onSelectError");
	  }	  
  }
  
  function btnActionPlan(cellValue, options, rowObject){
	  var rowId = options.rowId;
	  var colId = options.pos;	  
	  return '<input type="button" id="btnActionplan_'+rowId+'" keyid="'+rowObject[11]+'" name="btnActionplan_'+rowId+'" onclick="openactionplan('+rowId+')"  style="width:50px;  height:15px;"   class="easyui-button" value="..."/>';
	  //return '<span><span id="ActionbtnN_'+rowId+'"></span><span id="ActionbtnY_'+rowId+'" style="display:none; "><input type="button" id="btnActionplan_'+rowId+'_'+colId+'" name="btnActionplanGrid_'+rowId+'_'+colId+'" onclick="Actionplane('+rowId+')"  style="width:50px;  height:15px;"   class="easyui-button" value="..."/></span></span>';
	  
  }
  
  function openactionplan(rowid){
	var rowid=rowid;	
	var actPlanRefMasId = jQuery("#hdnJhamKeyid").val();	
	var actPlanMainTask =jQuery("#jhNewAudit").jqGrid('getCell', rowid, "Desc");// getFieldValue("momGridtxtmomdtldiscssion_"+rowid);
	var actPlanRefDocType = "JHA";
	var flid = jQuery("#frmAuditCreation input[id='flid']").val(); 
	var actPlanRefDtlId=jQuery("#jhNewAudit").jqGrid('getCell', rowid, "txtJhadKeyid");;	
	//alert(actPlanRefMasId);
	
	//if(actPlanRefDtlId.trim().length>0)
	//	openActionPlan("divJHAActionPlan",actPlanRefMasId,actPlanRefDocType,flid,actPlanMainTask,actPlanRefDtlId);
	//else
		saveForm("frmAuditCreation","jhAuditCreation_save.jhAuditItc?actplan=true&rowid="+rowid,"");
		//alert('Save Audit');	
  }
  
  function txtbox_pointsScored(cellValue, options, rowObject){
	var rowId = options.rowId;	
	if(cellValue==null || cellValue=='' || cellValue==undefined){
		return "<input id='pointsScored_txtbox"+rowId+"' style='text-align:right;width:70px;height:20px;font-family:arial;' name='pointsScored_txtbox' type='text'  onchange='{fillgrade(\""+rowId + "\");}' onkeypress='return isNumberKey(event);'  />";
	}else{
		return "<input id='pointsScored_txtbox"+rowId+"' maxlength='3' style='text-align:right;width:70px;height:20px;font-family:arial;' name='pointsScored_txtbox' type='text'  onchange='{fillgrade(\""+rowId + "\");}' onkeypress='return isNumberKey(event);' value ='"+cellValue+"' />";
	}	
  }	
  
  function viewGrid(url,filterString){  	
  	  	var jhamAuditpillar =  jQuery("#hdnJhamAuditpillar").val();
  	    processGridnew(url,filterString+"&jhamAuditpillar="+jhamAuditpillar,"jhNewAudit","pagerJHA","","","","auditGrid_Loadcallback");  		
  }
  
  function fillgrade(rowId){
	  var rowData = jQuery("#jhNewAudit").jqGrid('getRowData',rowId);
	  var keyID = rowData.txtJhadParameterid;
	  var parameterId = rowData.parameterid;
	  var maxPoints =rowData.txtJhadMaximumpoints;	 
	  var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs');		
	  disableUIButton("btnActionplan_"+rowId);
	  disableField("frmAuditCreation", rowId+"_txtJhadNcremarks");
	  var total =  jQuery("#txtJhamTotalpoints").val();
	  
	  if(total == null || total == '' || total == ' ')
		  total ='0';
	  var point = jQuery("#jhNewAudit").jqGrid('getCell',rowId,"txtJhadPointsscored");
	  if(point.substring(0,6) == '<input')
	  {
		 var pointScored = jQuery("#pointsScored_txtbox"+rowId).val(); 
		 var totalpoint='0';
		 if(parseInt(pointScored)>parseInt(maxPoints))
		 {
			jQuery("#pointsScored_txtbox"+rowId).val('');				
			return false;
		 }
		
		 for(var i=1;i<=jhNewAudit.length;i++)	
		 {
		    var row=jhNewAudit[i-1];
			var pointScore=jQuery("#pointsScored_txtbox"+row).val();
			if(pointScore==null || pointScore==''||pointScore==' '){
				pointScore="0";
			}else{	
				totalpoint=parseInt(totalpoint)+parseInt(pointScore);
			}	
		 }
		 jQuery("#txtJhamTotalpoints").val(totalpoint);
		 
		pointScored = jQuery("#pointsScored_txtbox"+rowId).val();
	  	
		if(pointScored==null || pointScored=='' || pointScored == ' '){
		  	pointScored=jQuery("#pointsScored_txtbox"+rowId).val();
		 	var pointvalue = parseInt(total) + parseInt(pointScored);
	  		//jQuery("#txtJhamTotalpoints").val(pointvalue);
	  		jQuery("#jhNewAudit").setCell(rowId, 'txtJhadRemarks',' ',{'background-color':'#FFFFFF'});
	  		//processAjaxCalls("gradeid_recall.jhAuditItc","?q=2&point="+pointScored+"&keyID="+keyID+"&rowId="+rowId,"gradeRecallSuccess","gradeRecallError");
		}else {	
			if(parseInt(pointScored)<parseInt(maxPoints))
			 {
				enableUIButton("btnActionplan_"+rowId);
				enableFields(rowId+"_txtJhadNcremarks");								
			 }			
  	  		//processAjaxCalls("gradeid_recall.jhAuditItc","?q=2&point="+pointScored+"&keyID="+keyID+"&rowId="+rowId,"gradeRecallSuccess","gradeRecallError");  	  		
  	  	}	 	 
  	}
  	getStatus();
  }

 /* function gradeRecallSuccess(result){	  
	 if(result!=null || result!=undefined){
		 var keyid=result.keyid;		
		 var grade=result.name;
		 var rowid = result.rowid;
		 jQuery("#jhNewAudit").jqGrid('setCell',rowid,"txtJhadGradeid",keyid);
		 jQuery("#jhNewAudit").jqGrid('setCell',rowid,"txtGrade",grade);
	 }
  }
  
  function gradeRecallError(result){
  }
  */
  function auditGrid_Loadcallback(){
	 // alert('auditGrid_Loadcallback');	
	var row = jQuery("#jhNewAudit").jqGrid('getDataIDs');
	var rowIds = jQuery("#jhNewAudit").getDataIDs();
	var parameterId =  jQuery("#jhNewAudit").jqGrid('getCell', rowIds[0], 'parameterid');
	jQuery('#hdnParamId').val(parameterId);	
	var row=0;	
	jQuery("#jhNewAudit").setCell(rowIds[0], '', 'parameterid');	 
	 var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs');
	 
	 for(i=1;i<=jhNewAudit.length;i++)	
	 {		 
		var rowId=parseInt(i);
		//alert(rowId);
		disableUIButton("btnActionplan_"+rowId);
		disableField("frmAuditCreation", rowId+"_txtJhadNcremarks");
		var maxPoints=jQuery("#jhNewAudit").jqGrid('getCell', jhNewAudit[i-1], 'txtJhadMaximumpoints');
		var pointsEntered=getFieldValue('pointsScored_txtbox'+rowId);
		//alert('maxPoints:'+maxPoints+',pointsEntered:'+pointsEntered);
		maxPoints=maxPoints|0;
		pointsEntered=pointsEntered|0;
		
		if(parseInt(pointsEntered)<parseInt(maxPoints)){
			enableUIButton("btnActionplan_"+rowId);
			enableFields(rowId+"_txtJhadNcremarks"); 
		}
	 }	

	 setMaxPointStatus();
	 getStatus();
  }
 
 /* function frmAuditCreationcmbJhamTeam_onSelect(record)
  {
	  var url = "jhAuditCreation_input.jhAuditItc";
	  viewGrid(url,"&s=1&machineId="+record.id);	 
	  loadFunctionalLocation("frmAuditCreationfunLocation","functionalLoc.commonFilter","frmAuditCreationfunLocationfunLocationValues","frmAuditCreation","&machId="+record.id);
	  processAjaxCalls("jhStepFill.jhAuditItc",'&machineId='+record.id ,'filljhStep_OnSuccess','filljhStep_OnError');
  }*/
  function  frmAuditCreationcmbJhamAuditteamid_onSelect(record){
	var textVal = record.text;
	var splitval = textVal.split("-");
	//alert(record.id);	
	LoadJHParameter(record.id);		
  }
  
  function LoadJHParameter(templateId){
	jQuery("#hdnTemplateId").val(templateId);
	var auditType =  jQuery("#hdnAuditType").val();	
	var flId =  jQuery("#hdnJhamFlid").val();	
	processAjaxCalls("jhFillControls_select.jhAuditItc" ,"templateId="+templateId, "JHParameterFill_successsCallback","JHParameterFill_errorCallback");	
	var url = "jhAuditCreation_input.jhAuditItc";
	viewGrid(url,"&s=1&templateId="+templateId);	
	//processAjaxCalls("jhStepFill.jhAuditItc",'&flId='+flId ,'filljhStep_OnSuccess','filljhStep_OnError');	 	
  }
  
  function JHParameterFill_successsCallback(result)
  {
	  	//alert('levelid:'+result.jhaTlTemplatelevellink.jtllAuditlevelid);
	  	//alert('step levelid:'+result.jhStepID.jhStepkey);
		//alert('minmarks:'+result.jhaTlTemplatelevellink.jtllMinimumpoints);
		setFieldValue("cmbJhamAudittype",result.jhaTlAuditparameter.JhapAudittype,"frmAuditCreation");	
		setFieldValue("cmbJhamAuditlevel",result.jhaTlAuditparameter.JhapAuditlevel,"frmAuditCreation");	
		setFieldValue("cmbJhamJhstepid",result.jhaTlTemplatelevellink.jtllAuditlevelid,"frmAuditCreation");
		/*
		jQuery("#cmbJhamAudittype").combobox("setValue",result.jhaTlAuditparameter.JhapAudittype);	
		jQuery("#cmbJhamAuditlevel").combobox("setValue",result.jhaTlAuditparameter.JhapAuditlevel);	
		jQuery("#cmbJhamJhstepid").combobox("setValue",result.jhaTlTemplatelevellink.jtllAuditlevelid);	
		*/
		jQuery("#txtminmarks").val(result.jhaTlTemplatelevellink.jtllMinimumpoints);
  } 
  
 /* function filljhStep_OnSuccess(result){
	  	alert(result);
		var machineId=result.jhStepID.equipment;
	
		if(result.jhStepID.jhStepkey==null || result.jhStepID.jhStepkey==''){
			jQuery("#cmbJhamAuditlevel").combobox('setValue','');
		}else{
			jQuery("#cmbJhamAuditlevel").combobox('setValue',result.jhStepID.jhStepkey);
		}	
		if((result.jhStepID.auditL)==null || (result.jhStepID.auditL=='')){
			jQuery("#cmbJhamJhstepid").combobox('setValue','');
			
		}else{
			jQuery("#cmbJhamJhstepid").combobox('setValue',result.jhStepID.auditL);
		}
		if((result.jhStepID.msg)==null || (result.jhStepID.msg)=='' || (result.jhStepID.msg)==undefined){
		}else{
			alert(result.jhStepID.msg);
			jQuery('#cmbJhamJhstepid').val('');
			return false;
		}
		if((result.jhStepID.minMarks)==null || (result.jhStepID.minMarks=='')){
			jQuery("#txtminmarks").val('');
		}else{
			jQuery("#txtminmarks").val(result.jhStepID.minMarks);
		}
  }*/
  function filljhStep_OnError(){

  }	
  /*function frmAuditCreationcmbJhamAudittype_onSelect(record){
	 processAjaxCalls("jhAuditCreation_recall.jhAuditItc?keyId="+record.id+"&mode=EDIT","cmbJhamAudittype_onSelectSuccess","cmbJhamAudittype_onSelectError");
  }*/
  /*function cmbJhamAudittype_onSelectSuccess(result){
	  alert("cmbJhamAudittype_onSelectSuccess");
	 // jQuery("#cmbJhamAuditlevel").combobox("setValue",result.auditLevel);
	  setFieldValue("cmbJhamAuditlevel",result.auditLevel,"frmAuditCreation");	
	  var pillar =  jQuery("#hdnJhamAuditpillar").val();
	  var type =  getFieldValue("cmbJhamAudittype");
	  reloadCombo("frmAuditCreation","cmbJhamAuditteamid","jhapTemplate.jhAuditItc?pillar="+pillar +"&type="+type+"&levelId="+result.auditLevel);
	  //reloadCombo("frmAuditCreation","cmbJhamAuditteamid","jhapTemplate.jhAuditItc?q=2&jhapAuditpillar="+jhapAuditpillar+"&levelId="+result.auditLevel); 
	  	
  }*/
  function cmbJhamAudittype_onSelectError(result){
	    //alert("auditLevel:"+result.auditLevel);
		//jQuery("#cmbJhamAuditlevel").combobox("setValue",result.auditLevel);
		setFieldValue("cmbJhamAuditlevel",result.auditLevel,"frmAuditCreation");	
		 var pillar =  jQuery("#hdnJhamAuditpillar").val();
		 var type =  getFieldValue("cmbJhamAudittype");
		 reloadCombo("frmAuditCreation","cmbJhamAuditteamid","jhapTemplate.jhAuditItc?pillar="+pillar +"&type="+type+"&levelId="+result.auditLevel);
		/*jQuery("#txtJhamAuditorname").val(result.jhAuditCreationdata.JhamAuditorname);
		jQuery("#dteJhamAuditdate").datebox("setValue",result.jhAuditCreationdata.JhamAuditdate);
		jQuery("#txtJhamLeadername").val(result.jhAuditCreationdata.JhamLeadername);
		jQuery("#txtJhamTotalpoints").val(result.jhAuditCreationdata.JhamTotalpoints);
		jQuery("#hdnJhamStatus").val(result.jhAuditCreationdata.JhamStatus);
		
		jQuery("#cboJhamAuditortype").val(result.jhAuditCreationdata.JhamAuditortype);
		var url = "jhAuditCreation_input.jhAuditItc";
		viewGrid(url,"&s=1&flId="+result.jhAuditCreationdata.JhamFlid+"&jhamKeyID="+result.keyid);
		loadFunctionalLocation("frmAuditCreationfunLocation","functionalLoc.commonFilter","frmAuditCreationfunLocationfunLocationValues","frmAuditCreation","&machId="+result.jhAuditCreationdata.JhamMachineid);
		jQuery("#cmbJhamJhstepid").combobox("setValue",result.jhAuditCreationdata.JhamAuditteamid);
	 	  if(status='P'){
			  jQuery("#hdnJhamStatus").val('Cleared');
		  }else if(status='F'){
			  jQuery("#hdnJhamStatus").val('Not Cleared'); 
		  }*/
		  
  }
  function frmAuditCreationcmbJhamJhstepid_onSelect(record)
  {
	  /*var machineId = "";jQuery('#cmbJhamMachineid').combobox("getValue");
	  if(machineId==null || machineId=='' || machineId==' '){
	  }else{
	   processAjaxCalls("jhStepFill.jhAuditItc",'&machineId='+machineId ,'filljhStep_OnSuccess','filljhStep_OnError');
	  }*/
	  var parameter=jQuery('#hdnParamId').val();
	  processAjaxCalls("fillStatus_recall.jhAuditItc","?q=2&parameter="+parameter+"&auditTeam="+record.id,"minPointsRecallSuccess","minPointsRecallError");
	  //processAjaxCalls("fillStatus_recall.jhAuditItc","?q=2&parameter="+parameter+"&auditTeam="+record.id+"&rowId="+rowId,"minPointsRecallSuccess","minPointsRecallError");
  }
  function minPointsRecallSuccess(result){
	  if(result!=null || result!=undefined){
		var points=result.point;
		jQuery('#hdnMinpoints').val(points);
	  }
  }
 
  function frmAuditCreation_beforeDelete()
  {  
  	var keyid= getFieldValue("hdnJhamKeyid");
  	
  	if(keyid.length>0){	
  		var r =confirm("Are you sure to Delete?");
  		if(r){			
  		}
  		else
  			return false;	
  	}
  	else
  		return false;
  }
  
  function frmAuditCreation_beforeSubmit()
  {
	  //alert('frmAuditCreation_beforeSubmit');
 	var keyid = getFieldValue("hdnJhamKeyid");	
	if(keyid.length>0){	
		/* var r =confirm("Data have been changed, Do you want to proceed?");
		if(r){			
		}
		else
			return false; */
  	}
	  var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs');
	  var flId = jQuery('#hdnJhamFlid').val();	  
	  if (flId==null || flId=='' || flId==' '){
	  }else{
		  if(jhNewAudit.length==0){
			alert('No parameters for this Functional Location');
			return false;
		  }
	  }
	 for(i=1;i<=jhNewAudit.length;i++)	
	 {
		var rowid=jhNewAudit[i-1];
		pointScored=jQuery("#pointsScored_txtbox"+rowid).val();
		if(pointScored== null || pointScored=='' || pointScored==' '){
			alert('Enter Scored Point for SI.No :'+rowid);
			return false;
		}
	 }	
	
	 setStatus();
	 
	 var gridData ='';
	 gridData += '&audit='+convertJHGridToJSONArr('jhNewAudit');
	
	 return gridData ; 
  }	

  function getStatus(){
	 //alert('getStatus');
  	 var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs'); 	  
  	 setMaxPointStatus();
  	 setStatus();
	 var reviewCnt=0;
	 var maxPass=4;
	 var startSlNo=1;
	 
	 //alert(jhNewAudit.length);
	 
	 for(var i=1;i<=jhNewAudit.length;i++)	
	 {
		var rowid=jhNewAudit[i-1];
		//alert('i:'+i);	
		var reviewPtSlNo =jQuery("#jhNewAudit").jqGrid('getCell', i, "reviewPtSlNo");	
		var pointScored=jQuery("#pointsScored_txtbox"+rowid).val();
		pointScored=pointScored|0;
		//alert('reviewPtSlNo'+reviewPtSlNo);
		//alert('pointScored'+pointScored);
		if (parseInt(reviewPtSlNo)==parseInt(startSlNo)){
			reviewCnt=parseInt(reviewCnt)+1;
		}
		else{
			startSlNo=parseInt(startSlNo)+1;
			reviewCnt=1;
		}
		//alert('reviewCnt'+reviewCnt);
		if (parseInt(reviewCnt)<=maxPass && parseInt(pointScored)==0){			
			jQuery("#hdnJhamStatus").val("F");
			setStatus();
			/*alert('reviewCnt'+reviewCnt);
			alert('reviewPtSlNo'+reviewPtSlNo);
			alert('pointScored'+pointScored);*/
			return false;
		}
		/*if(pointScored== null || pointScored=='' || pointScored==' '){
			alert('Enter Scored Point for SI.No :'+rowid);
			return false;
		}*/
	 }	
		 
  }
   	
    
  function convertJHGridToJSONArr(jqGridId){
	    //alert("convertJHGridToJSONArr");
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
		var jsonArrO='[';
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			jsonArrO += '{';
			
			for(var colName in row) {
				
				if(colName != 'Item' && colName != 'Desc' && colName != 'Evidense' 
						&& colName != 'Parameter' && colName != 'parameterid' && colName != 'btnActionPlan' )
				{
					if(row[colName].substring(0,6)!='<input')
					{						
						jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
						//alert('colName:'+colName+',jsonArrO:'+row[colName]);
					}
					else
					{
						var x=row[colName].indexOf("id=")+4;
						var y=row[colName].substring(x);
						var z = y.indexOf('"');
						//alert('colName:'+colName+',jsonArrOS:'+jQuery('#'+y.substring(0,z)).val());
						/*if(colName == 'txtJhadNcactionplan') {
							//alert('y.substring(0,z):'+y.substring(0,z));
							//alert('keyid'+jQuery('#'+y.substring(0,z)).attr('keyid'));
							if(jQuery('#'+y.substring(0,z)).attr('keyid') == '...') 
								jsonArrO += '"'+colName +'":"",'; 
							else	
								jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).attr('keyid') +'",'; 
						}
						else{*/
							jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).val() +'",'; 
						//}
					}
				}				
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
		
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");		
		//alert('jsonArrO:'+jsonArrO);
		return jsonArrO; 
		
  }
  
  function isNumberKey(evt)
  {
     var charCode = (evt.which) ? evt.which : event.keyCode;
     if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
     return true;
  }

  function frmAuditCreation_exceptionCallback(result){
  }
  
  function frmAuditCreation_deleteSuccessCallback(result){
	 //alert(result.successData.msg);
	 alert(result.successData.msg);  
	 //setFieldValue("hdnJhamAuditpillar",result.successData.pillar);
	 //setFieldValue("cmbJhamAudittype",result.successData.audittype);	
	 clearFields();
  }
  function divJHAActionPlan_onClose(result){
	  //alert('divJHAActionPlan_onClose');
	  var jhamKeyID=jQuery('#hdnJhamKeyid').val();
	  var flId = jQuery("#hdnJhamFlid").val();
	  //alert('jhamKeyID:'+jhamKeyID+',flId='+flId);
	  var url = "jhAuditCreation_input.jhAuditItc";
	  viewGrid(url,"&s=1&flId="+flId+"&jhamKeyID="+jhamKeyID);
	  //jQuery("#jhNewAudit").trigger("reloadGrid"); 
	  return true;
  }
  
  function frmAuditCreation_successsCallback(result)
  {
	  //alert('frmAuditCreation_successsCallback');
	  //alert('actplan:'+result.successData.actplan);	 
	 var actplan=result.successData.actplan;
	 
	 if(actplan==true){
		var rowId=result.successData.rowid;
		var actPlanRefDtlId=result.successData.dtlkeyid;
		var actPlanRefMasId=result.successData.keyId;
		setFieldValue("hdnJhamAuditpillar",result.successData.pillar);
		setFieldValue("cmbJhamAudittype",result.successData.audittype);
		setFieldValue("hdnJhamKeyid",actPlanRefMasId);
		//jQuery("#jhNewAudit").trigger("reloadGrid"); 		
		var actPlanMainTask =jQuery("#jhNewAudit").jqGrid('getCell', rowId, "Desc");		
		var actPlanRefDocType = "JHA";
		var flid = jQuery("#frmAuditCreation input[id='flid']").val(); 		
		
		var actPlanRefDate = jQuery('#dteJhamAuditdate').datebox('getValue');
		if(actPlanRefDtlId.trim().length>0)
			openActionPlan("divJHAActionPlan",actPlanRefMasId,actPlanRefDocType,flid,actPlanMainTask,actPlanRefDtlId, actPlanRefDate);		  	
  	}	
	 
  	else{  
		var keyid= getFieldValue("hdnJhamKeyid");	
		if(keyid.length<=0){	
		  	setFieldValue("hdnJhamAuditpillar",result.successData.pillar);
			setFieldValue("cmbJhamAudittype",result.successData.audittype);	
			clearFields();
		}
  	}
  }
  
  function clearFields(){
	setFieldValue("cmbJhamAuditteamid","");
	setFieldValue("hdnJhamKeyid","");
	setFieldValue("cmbJhamJhstepid","");
	setFieldValue("txtJhamAuditorname","");
	setFieldValue("txtJhamLeadername","");
	setFieldValue("hdnParamId","");
	setFieldValue("hdnJhamFlid","");
	setFieldValue("hdnMinpoints","");
	setFieldValue("txtJhamTotalpoints","");
	setFieldValue("txtminmarks","");
	setFieldValue("hdnJhamStatus","F");	
	jQuery("#txtStatus").css('background-color','');
	jQuery("#txtStatus").val('');
	jQuery("#jhNewAudit").clearGridData();
 }
	
  
 </script>
 
 <form name="frmAuditCreation" id="frmAuditCreation" >
 <div id="wrapperRpt">
 	<table id=" " border="0" width="80% " style=" " class="">
		<tr>
			<td colspan='3'>
				<div  id="frmAuditCreationFuntKeyIds">
					<input type="hidden" id="factory" name="cmbJhamFactoryid" value=" "  ></input>			
					<input type="hidden" id="section" name="cmbJhamSectionid" value=" "  ></input>
					<input type="hidden" id="cell" name="cmbJhamCellid" value=" "  ></input>
					<input type="hidden" id="machine" name="cmbJhamEquipmentid" value=" "  ></input>
					<input type="hidden" id="flid" name="cmbJhamFlid" value=" "></input>	
				</div>
			 	<div id="auditCreationfunLocation" style="width:123%;margin-top: 17px;"></div>
			</td>
			<td >
				<div style="padding-left:10px;margin-top:20px;position:relative;">
<!--					 <span  id="JhamFilemgr" style="position:absolute;top:-4px;" ></span> -->
					 <span id="JhamFilemgr" style="position:absolute;top:-4px;"></span>
             	</div>	
			</td>
		</tr>
		
	 	<tr>	 	
			<td style="">
		 		<div   style=" width : 320px;">
			 	<div  class=""><label class="mandatory-lbl">JH Template</label><br></div>
			    <div  class="" style="width:280px;"><input id="cmbJhamAuditteamid" name="cmbJhamAuditteamid" type="text" class="easyui-combobox" style="width:300px;"  value="${requestScope.jhaTlAuditmst.jhamAuditteamid}"/></div>
			 	<div  class=""><label >Audit Type</label></div>
			 	<div  class="" style="width:280px;"><input id="cmbJhamAudittype" name="cmbJhamAudittype" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.jhamAudittype}"/> </div>
			 	<div  class=""><label>Audit Level</label></div>
			 	<div  class="" style="width:280px;"><input id="cmbJhamAuditlevel" name="cmbJhamAuditlevel" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.jhaTlAuditparameter.jhapAuditlevel}" /></div>
			 	</div>
		 	</td>
	 		<td valign="top" style=""  >
	 		<div   style=" width : 269px;">
			 	<div  class=""><label class="mandatory-lbl" >Approval Level</label><br></div>
			    <div  class=""><input id="cmbJhamJhstepid" name="cmbJhamJhstepid" type="text" class="easyui-combobox" style="width: 255px;" value="${requestScope.jhaTlAuditmst.jhamJhstepid}" /></div>
			    <div class=""><label class="mandatory-lbl">Auditor Name</label><br></div>
				 <div class=""><input id="txtJhamAuditorname" name="txtJhamAuditorname" type="text" class="easyui-text" style="width: 255px;" maxlength="200" value="${requestScope.jhaTlAuditmst.jhamAuditorname}"/></div>
			    <table>
			    <tr>
			    <td  class=""><label class="mandatory-lbl" >Audit Date</label>
			    <label class="mandatory-lbl" style="padding-left:58px;">Audit Type</label><br>
			   </td>
			     <tr>
			     <tr>
			    <td style="padding-left:0px;width:60%;">
			    <span style="float:left;padding-right:0px;">
					 <!-- here the changes have to made -->
					 	<input class="easyui-text" style=" width : 87px;" id="dteJhamAuditdate" name="dteJhamAuditdate" value="${requestScope.jhaTlAuditmst.jhamAuditdate}"/>
				      </span>
			   
			     <span style="margin-left:28px;margin-right:00%;">
				    <select  id="cboJhamAuditortype" class="easyui-combobox" name="cboJhamAuditortype" style=" width : 88px;height:23px" required="true" >
							<option value="I"> Internal </option>
							<option value="E"> External </option>							
					 </select>
					 </span>
					 </td>
				 </tr>	
			 </table>
			</div>	  
			</td>
			<td >
				<div style="  width : 253px;">
					<div  class=""><label class="mandatory-lbl" >Leader</label><br></div>
				    <div  class=""><input id="txtJhamLeadername" name="txtJhamLeadername" maxlength="200" type="text" class="easyui-text" style="width: 200px;" value="${requestScope.jhaTlAuditmst.jhamLeadername}" /></div>
				    <div  class=""><label class="mandatory-lbl" >Total Points</label><br></div>
				    <div class=""><input id="txtJhamTotalpoints" name="txtJhamTotalpoints" type="text" class="easyui-text" style="width: 100px;text-align:right;" value="${requestScope.jhaTlAuditmst.jhamTotalpoints}" /></div>
				    <div class="">
				    	<label>Min. Marks to Clear</label>
				    	<label style="padding-left:16px;">Status</label><br>
				    </div>
				    <div style="width:250px;">
				    	<input id="txtminmarks" name="txtminmark" type="text"  class="easyui-text" style="width: 100px; background-color:transparent ;text-align:right;" value="${requestScope.minMarks}"/>				    	
				    	<span  style="padding-left:18px;">
				    	<input id="txtStatus" name="txtStatus" type="text" class="easyui-text"  
				    	style="width:100px;background-color:transparent;" 
				    	value="${requestScope.jhaTlAuditmst.jhamStatus}" />
				    	</span>
				    </div>
				 </div>
			</td>
			<td valign="bottom" align="right">
				<!--<div class="easyui-paddingbfpx" style="padding-left:0px;">
					<input type="button" value="Check List" class="easyui-button" style="height : 21px; width : 80px;" id="btnCheckList">
				</div>
				-->
			</td>
			<td valign="bottom" >
				<div class="easyui-paddingbfpx" style="padding-left:10px;">
<!-- 				<input type="button" value="Pie Chart" class="easyui-button" style="height : 21px; width : 80px;" id="btnPiechart"> -->
				</div>
			</td>
		 </tr>
 	</table>
 	<div style="height:3px;float:left;margin-left:0%;">
 		<table id="jhNewAudit" style=" ">
			<tr>
				<td>
				<td/>
			</tr>
		</table>		
		<div id="pagerJHA"></div>
	</div>
</div> 
 <input type="hidden" id="hdnParamId" name="hdnParamId" value="${requestScope.jhamParamid}" />
 <input type="hidden" id="hdnJhamStatus" name="hdnJhamStatus" value="${requestScope.jhamStatus}" />
 <input type="hidden" id="hdnJhamKeyid" name="hdnJhamKeyid" value="${requestScope.jhaTlAuditmst.jhamKeyid}" />
 <input type="hidden" id="hdnJhamFlid" name="hdnJhamFlid" value="${requestScope.jhaTlAuditmst.jhamFlid}" />
 <input type="hidden" id="hdnJhamAuditpillar" name="hdnJhamAuditpillar" value="${requestScope.jhamAuditpillar}"/>
 <input type="hidden" id="hdnMinpoints" name="hdnMinpoints" />
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
 </form>	
 	