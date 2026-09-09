<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<style>
.padding
{
  padding-left:74px;
  padding-left:80px\9;
}
</style>

 <script type="text/javascript">	
  jQuery(document).ready(function(){	  
	  initialiseForm('frmAuditUpload');  
	  jQuery('#submitForm').val('frmAuditUpload'); 
	  var documentNo =jQuery("#hdnJhamKeyid").val();
	  fileManagerPopUp(documentNo,"JHM","frmAuditUpload","btnFilManage","JhamFilemgr");	
	  var pillar =  jQuery("#hdnJhamAuditpillar").val();
	  var type =  getFieldValue("cmbJhamAudittype");
	  fillComboBox("frmAuditUpload","cmbJhamAuditteamid","jhapTemplate.jhAuditItc?pillar="+pillar +"&type="+type );
	  if(type.trim()=="DMT"){
		  jQuery("#lbltemplate").html("DMT Template");
	  }else
		  jQuery("#lbltemplate").html("JH Template");
	//  fillComboBox("frmAuditUpload","cmbJhamAuditteamid","jhapTemplate.jhAuditItc?jhapAuditpillar="+jhapAuditpillar+"&jhapAudittype="+jhapAudittype);
	  fillComboBox("frmAuditUpload","cmbJhamAudittype","jhAuditTypeCombo.jhAuditItc","",false);
	  fillComboBox("frmAuditUpload","cmbJhamAuditlevel","jhAuditLevelCombo.jhAuditItc","",false);
	  disableField('frmAuditUpload','cmbJhamAudittype');
	  var templateId=getFieldValue("cmbJhamAuditteamid");
	  fillComboBox("frmAuditUpload","cmbJhamJhstepid","JHTeamCombo.jhAuditItc?q&templateId="+templateId);
	  formatDateBox('dteJhamAuditdate','dd-MMM-yyyy');
	  var auditdate=getFieldValue("dteJhamAuditdate");
	  if(auditdate=='' || auditdate=='' || auditdate==null)
	  	fillWithCurrentDate('dteJhamAuditdate'); 
	  else {
		  setFieldValue("dteJhamAuditdate",auditdate.substring(0,11));
	  }
	  var url = "jhdmtAuditUpload_input.jhdmtauditupd";		  
	  var flId = jQuery("#hdnJhamFlid").val();
	  var jhamKeyID=jQuery('#hdnJhamKeyid').val();
	
	  //jQuery("#txtJhamTotalpoints").attr('readonly','readonly');
	  numericTextBox("txtJhamTotalpoints");
	  jQuery("#txtStatus").attr('readonly','readonly');
	  jQuery("#txtminmarks").attr('readonly','readonly');
	  var ketId =jQuery("#hdnJhamKeyid").val();
	  if (ketId.trim().length>0){
		  //readOnlyFields('cmbJhamJhstepid');
	  }
	  
	  var fnlnUrl="functionalLoc.jhdmtauditupd";
	  
	  if(type.trim()=="DMT"){
		  fnlnUrl="functionalLocDMT.jhdmtauditupd";
	  }
	  if(flId != null && flId != ''){
		 //alert('flId:'+flId);	
		 viewGrid(url,"&s=1&flId="+flId+"&jhamKeyID="+jhamKeyID);
		 loadFunctionalLocation("auditCreationfunLocation",fnlnUrl,"auditCreationfunLocationValues","frmAuditUpload","&q=2&flid="+flId+"&type="+type);
	  }else{	
		// alert('else flId:'+flId);		  
	  	 viewGrid(url,"&s=1");
	  	 var compId = jQuery("#frmAuditUpload input[id='company']").val();
		 var locnId = jQuery("#frmAuditUpload input[id='location']").val();
		 var factId = jQuery("#frmAuditUpload input[id='factory']").val();
		 var sectionId = jQuery("#frmAuditUpload input[id='section']").val();
		 var cellId = jQuery("#frmAuditUpload input[id='cell']").val();
		 var machId = jQuery("#frmAuditUpload input[id='machine']").val();		
		 // var flId = jQuery("#frmAuditUpload input[id='flid']").val();	 
	     var dataStr = "&compId="+compId +"&locnId="+locnId +"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&type="+type;//+"&flid="+flId;
	     loadFunctionalLocation("auditCreationfunLocation",fnlnUrl,"auditCreationfunLocationValues","frmAuditUpload",dataStr);
	  }		  

	  jQuery("#cmbJhamAuditteamid").combobox({onRequest:function( ){
		  var pillar =  jQuery("#hdnJhamAuditpillar").val();
		  var type =  getFieldValue("cmbJhamAudittype");
	   	  return "pillar="+pillar +"&type="+type ;
  	  }});
	  
  });
  
 /* function  frmAuditUploadcmbJhamAudittype_onSelect(record){
	  alert("check");
	  var type =  getFieldValue("cmbJhamAudittype");
	  alert("The type"+type);
	  var fnlnUrl="functionalLoc.jhdmtauditupd";
	  if(type.trim()=="DMT"){
		  alert("DMT");
		  fnlnUrl="functionalLocDMT.jhdmtauditupd";
	  }
	  if(flId != null && flId != ''){
		 //alert('flId:'+flId);	
		 viewGrid(url,"&s=1&flId="+flId+"&jhamKeyID="+jhamKeyID);
		 loadFunctionalLocation("auditCreationfunLocation",fnlnUrl,"auditCreationfunLocationValues","frmAuditUpload","&q=2&flid="+flId+"&type="+type);
	  }else{	
		// alert('else flId:'+flId);		  
	  	 viewGrid(url,"&s=1");
	  	 var compId = jQuery("#frmAuditUpload input[id='company']").val();
		 var locnId = jQuery("#frmAuditUpload input[id='location']").val();
		 var factId = jQuery("#frmAuditUpload input[id='factory']").val();
		 var sectionId = jQuery("#frmAuditUpload input[id='section']").val();
		 var cellId = jQuery("#frmAuditUpload input[id='cell']").val();
		 var machId = jQuery("#frmAuditUpload input[id='machine']").val();		
		 // var flId = jQuery("#frmAuditUpload input[id='flid']").val();	 
	     var dataStr = "&compId="+compId +"&locnId="+locnId +"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&type="+type;//+"&flid="+flId;
	     loadFunctionalLocation("auditCreationfunLocation",fnlnUrl,"auditCreationfunLocationValues","frmAuditUpload",dataStr);
	  }		 
	  
  }*/
  
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
	  //alert("The status"+status);
	  if(status=='P'){
		 jQuery("#txtStatus").val('Cleared');
		 jQuery("#txtStatus").css('background-color','#72a98b');
	  }else if(status=='F'){
		 jQuery("#txtStatus").val('Not Cleared'); 
		 jQuery("#txtStatus").css('background-color','#ff9999');
	  }
  } 
  
  function btnFilManage_click(){
	    saveForm("frmAuditUpload","jhdmtAuditUpload_save.jhdmtauditupd?filemanager=true","");
	}
  function frmAuditUpload_FuntLocHierarchy_SuccessCallBack(keyIds){	
	  var flid=jQuery("#hdnJhamFlid").val(); 
	  var type =  getFieldValue("cmbJhamAudittype");
	  if(type.trim()=="DMT") { 
		  	var sectId = jQuery("#frmAuditUpload input[id='section']").val();
		  	var cellId = jQuery("#frmAuditUpload input[id='cell']").val();
		  	if (cellId.trim().length>0) {
		 		popupCommonErrorMsg("Select DMT Level");
		 		return false;
		 	}		 		 	
		 	else if (sectId.trim().length==0) {
		 		popupCommonErrorMsg("Select DMT Level");
		 		return false;
		 	}	 
	  }
	  if (flid.trim().length<=0){
		  clearFields();	
	  	  jQuery("#hdnJhamFlid").val(keyIds.flid);
	  	  processAjaxCalls("jhAuditCreation_recall.jhAuditItc?q=2&flId="+keyIds.flid+"&mode=EDIT","cmbJhamAudittype_onSelectSuccess","cmbJhamAudittype_onSelectError");
	  }	  
  }
  
  function viewGrid(url,filterString){  	
  	  	var jhamAuditpillar =  jQuery("#hdnJhamAuditpillar").val();
  	    var templateId=getFieldValue("cmbJhamAuditteamid");
  	    processGridnew(url,filterString+"&jhamAuditpillar="+jhamAuditpillar+"&templateId="+templateId,"jhNewAudit","pagerJHA","","","","auditGrid_Loadcallback");  		
  }
  
  function fillgrade(rowId){
	  var rowData = jQuery("#jhNewAudit").jqGrid('getRowData',rowId);
	  var keyID = rowData.txtJhadParameterid;
	  var parameterId = rowData.parameterid;
	  var maxPoints =rowData.txtJhadMaximumpoints;	 
	  var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs');		
	//  disableUIButton("btnActionplan_"+rowId);
	 // disableField("frmAuditUpload", rowId+"_txtJhadNcremarks");
	  var total =  jQuery("#txtJhamTotalpoints").val();
	  
	  if(total == null || total == '' || total == ' ')
		  total ='0';
	  var point = jQuery("#jhNewAudit").jqGrid('getCell',rowId,"txtJhadPointsscored");
	//  alert("point"+point);
	  jQuery('#hdnSelectRow').val(point);
	  if(point.substring(0,6) == '<input')
	  {
		 var pointScored = jQuery("#pointsScored_txtbox"+rowId).val(); 
		 var totalpoint='0';
		 if(parseInt(pointScored)>parseInt(maxPoints))
		 {
			jQuery("#pointsScored_txtbox"+rowId).val('');				
			return false;
		 }
		 var maxReviewPoint=5;
		 var reviewPoint=0;
		 for(var i=1;i<=jhNewAudit.length;i++)	
		 {
		    var row=jhNewAudit[i-1];
		    reviewPoint=parseInt(reviewPoint)+1;
		    if (parseInt(reviewPoint)>parseInt(maxReviewPoint))
			{
		    	reviewPoint=1;
			}
		    //alert('reviewPoint'+reviewPoint);
			var pointScore=jQuery("#pointsScored_txtbox"+row).val();
			pointScore=pointScore|0;
			if(pointScore==0){
				i=i+(parseInt(maxReviewPoint)-parseInt(reviewPoint));
				reviewPoint=0;
				//alert('totalpoint:'+totalpoint+',pointScore:'+pointScore);
				//alert('i'+i);
			}else{	
				totalpoint=parseInt(totalpoint)+parseInt(pointScore);
			}
		 }
		 jQuery("#txtJhamTotalpoints").val(totalpoint);
		 
		pointScored = jQuery("#pointsScored_txtbox"+rowId).val();
	 // 	alert("pointScored"+pointScored)
	  	
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
  
  function getJhAudit(){
	var flid=jQuery("#frmAuditUpload input[id='flid']").val(); 
	var templateId=getFieldValue("cmbJhamAuditteamid");
	var auditpillar=getFieldValue("hdnJhamAuditpillar");
	var audittype=getFieldValue("cmbJhamAudittype");
	var stepid=getFieldValue("cmbJhamJhstepid");
	var auditdate=getFieldValue("dteJhamAuditdate");
	var auditortype=getFieldValue("cboJhamAuditortype");
	//alert('flid:'+flid+',templateId:'+templateId+',auditpillar:'+auditpillar+',audittype:'+audittype+',stepid:'+stepid+',auditdate:'+auditdate+',auditortype:'+auditortype);
	if(flid.trim().length>0 && 
			templateId.trim().length>0 && 
			auditpillar.trim().length>0 && 
			audittype.trim().length>0 && 
			stepid.trim().length>0 && 
			auditdate.trim().length>0 && 
			auditortype.trim().length>0 ){
		//alert(111);
		 	processAjaxCalls("jhuploadaudit_recall.jhdmtauditupd","?q=2&flid="+flid+"&templateid="+templateId+"&jhamAuditpillar="+auditpillar+"&jhamAudittype="+audittype+"&stepid="+stepid+"&auditdate="+auditdate+"&auditortype="+auditortype,"jhAuditRecallSuccess","jhAuditRecallError");
	}	
  }
  
  function jhAuditRecallSuccess(result){
	  var keyId =result.successData.keyId;
	  var url = "jhdmtAuditUpload_input.jhdmtauditupd";
	  //alert('keyId:'+keyId);
	  if( keyId != undefined && keyId !='' && keyId !=' ' && keyId != null && keyId != 'undefined'  ){
		  jQuery("#hdnJhamKeyid").val(keyId);
		  setFieldValue("txtJhamTotalpoints",result.jhaTlAuditmst.jhamTotalpoints);
		  setFieldValue("hdnJhamStatus",result.jhaTlAuditmst.JhamStatus);	
		 // jQuery("#txtStatus").css('background-color','');
		  //jQuery("#txtStatus").val('');
		  //alert('result.jhaTlAuditmst.JhamTotalpoints:'+result.jhaTlAuditmst.jhamTotalpoints);
		  setStatus();
		  setFieldValue("txtJhamAuditorname",result.jhaTlAuditmst.jhamAuditorname);
		  setFieldValue("txtJhamLeadername",result.jhaTlAuditmst.jhamLeadername);
		  var flid=jQuery("#frmAuditUpload input[id='flid']").val(); 		  
	  	  viewGrid(url,"&s=1&flId="+flid+"&jhamKeyID="+keyId);
	  	  
	
	  }
	  else {
		  jQuery("#hdnJhamKeyid").val('');
		  setFieldValue("txtJhamTotalpoints",'');
		 // jQuery("#txtStatus").css('background-color','');
		  //jQuery("#txtStatus").val('');
		  //alert('result.jhaTlAuditmst.JhamTotalpoints:'+result.jhaTlAuditmst.jhamTotalpoints);
		  setStatus();
		  setFieldValue("txtJhamAuditorname",'');
		  setFieldValue("txtJhamLeadername",'');
		  var flid=jQuery("#frmAuditUpload input[id='flid']").val(); 
		  var url = "jhdmtAuditUpload_input.jhdmtauditupd";
		  //alert(flid);
		  viewGrid(url,"&s=1&flId="+flid);
	  }
  }
  
  function jhAuditRecallError(){
	  
  }
  
  
  
  
  function auditGrid_Loadcallback(){
	 // alert('auditGrid_Loadcallback');	
	 /* jQuery("#jhNewAudit").setGridParam({onCellSelect:function(id,cellidx,cellvalue) {
			//alert("cellvalue.."+cellvalue+"..id.."+id+"..cellidx.."+cellidx);
			jQuery('#hdnSelectRow').val(id);
			jQuery('#hdnSelectCol').val(cellidx);
		}
	}); */
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
		disableField("frmAuditUpload", rowId+"_txtJhadNcremarks");
		var maxPoints=jQuery("#jhNewAudit").jqGrid('getCell', jhNewAudit[i-1], 'txtJhadMaximumpoints');
		
		var pointsEntered=getFieldValue('pointsScored_txtbox'+rowId);
		//alert('maxPoints:'+maxPoints+',pointsEntered:'+pointsEntered);
		maxPoints=maxPoints|0;
		pointsEntered=pointsEntered|0;
		
		if(parseInt(pointsEntered)<parseInt(maxPoints)){
			enableUIButton("btnActionplan_"+rowId);
			enableFields(rowId+"_txtJhadNcremarks"); 
		}
	
		var actPlnId=jQuery("#jhNewAudit").jqGrid('getCell', jhNewAudit[i-1], 'txtJhadNcactionplan');
		if (actPlnId.length>1)
			readOnlyFields("pointsScored_txtbox"+rowId);

	 }	

	 setMaxPointStatus();
	 getStatus();
  }

  function  frmAuditUploadcmbJhamAuditteamid_onSelect(record){
	var textVal = record.text;
	var splitval = textVal.split("-");

	if (validateMandatory()==false) {
		jQuery('#cmbJhamAuditteamid').combobox('clear');
		return false;
	}
	var templateId=getFieldValue("cmbJhamAuditteamid");
	reloadCombo("frmAuditUpload","cmbJhamJhstepid","JHTeamCombo.jhAuditItc?q&templateId="+templateId);
	LoadJHParameter(record.id);		
  }
  
  function LoadJHParameter(templateId,stepId){
	jQuery("#hdnTemplateId").val(templateId);
	var auditType =  getFieldValue("cmbJhamAudittype");	
	var flId =jQuery("#frmAuditUpload input[id='flid']").val();
	var auditdate=getFieldValue("dteJhamAuditdate");
	//var jhstep = getFieldValue("cmbJhamJhstepid");
	var jhstep = stepId == null ? getFieldValue('cmbJhamJhstepid'):stepId;
	var ds = "templateId="+templateId+"&flId="+flId+"&date="+auditdate+"&auditType="+auditType+"&jhstep="+jhstep;
	processAjaxCalls("jhFillControls_select.jhAuditItc" , ds, "JHParameterFill_successsCallback","JHParameterFill_errorCallback");
	 var url = "jhdmtAuditUpload_input.jhdmtauditupd";
	var team = jQuery("#cmbJhamJhstepid").val();
	var flid=jQuery("#frmAuditUpload input[id='flid']").val();
	var filterStr="&s=1&templateId="+templateId;
	if(team.trim().length>0){
		filterStr+="&flId="+flid;
	}	
  }
  
  function JHParameterFill_successsCallback(result)
  {
	  
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
		if (getFieldValue("mode").trim()=="create"){
			jQuery("#hdnJhamKeyid").val("");
		}
		//alert(result.jhaTlTemplatelevellink.jtllAuditlevelid);
	  	//alert('levelid:'+result.jhaTlTemplatelevellink.jtllAuditlevelid);
	  	//alert('step levelid:'+result.jhStepID.jhStepkey);
		//alert('minmarks:'+result.jhaTlTemplatelevellink.jtllMinimumpoints);
		setFieldValue("cmbJhamAudittype",result.jhaTlAuditparameter.JhapAudittype,"frmAuditUpload");	
		setFieldValue("cmbJhamAuditlevel",result.jhaTlAuditparameter.JhapAuditlevel,"frmAuditUpload");	
		setFieldValue("cmbJhamJhstepid",result.jhaTlTemplatelevellink.jtllAuditlevelid,"frmAuditUpload");
		/*
		jQuery("#cmbJhamAudittype").combobox("setValue",result.jhaTlAuditparameter.JhapAudittype);	
		jQuery("#cmbJhamAuditlevel").combobox("setValue",result.jhaTlAuditparameter.JhapAuditlevel);	
		jQuery("#cmbJhamJhstepid").combobox("setValue",result.jhaTlTemplatelevellink.jtllAuditlevelid);	
		*/
		jQuery("#txtminmarks").val(result.jhaTlTemplatelevellink.jtllMinimumpoints);
		
		//getApprovalLevel();
			
		//alert(result.jhamKeyid);
		var templateId=getFieldValue("cmbJhamAuditteamid");
		var auditor = jQuery('#txtJhamAuditorname').val();
		var keyId=result.jhaTlAuditmst.jhamKeyid;
		if (keyId.trim().length>0){
			jQuery("#hdnJhamKeyid").val(result.jhaTlAuditmst.jhamKeyid);
			//alert("result.jhaTlAuditmst.jhamKeyid"+result.jhaTlAuditmst.jhamKeyid);
		}
		//alert(auditor);
		if (auditor=="" || auditor =='' || auditor==null)
			jQuery("#txtJhamAuditorname").val(result.jhaTlAuditmst.jhamAuditorname);
		//getJhAudit();
		setTimeout(function(){getJhAudit();},500);
		var jhamKeyID=jQuery('#hdnJhamKeyid').val();
		
		var url = "jhdmtAuditUpload_input.jhdmtauditupd";
		//alert("jhamKeyID"+jhamKeyID);
		if (jhamKeyID.length>2) { 
			var flId = jQuery("#frmAuditUpload input[id='flid']").val();
			//viewGrid(url,"&s=1&flId="+flId+"&jhamKeyID="+jhamKeyID);
		}
		else {
			//alert(url);
			var team = jQuery("#cmbJhamJhstepid").val();
			var flid=jQuery("#frmAuditUpload input[id='flid']").val();
			var filterStr="&s=1";//&templateId="+templateId;
			if(team.trim().length>0){
				filterStr+="&flId="+flid;
			}
		 	viewGrid(url,filterStr);
		}
  }
  
  function getJhAudit(){
		var flid=jQuery("#frmAuditUpload input[id='flid']").val(); 
		var templateId=getFieldValue("cmbJhamAuditteamid");
		var auditpillar=getFieldValue("hdnJhamAuditpillar");
		var audittype=getFieldValue("cmbJhamAudittype");
		var stepid=getFieldValue("cmbJhamJhstepid");
		var auditdate=getFieldValue("dteJhamAuditdate");
		var auditortype=getFieldValue("cboJhamAuditortype");
		//alert('flid:'+flid+',templateId:'+templateId+',auditpillar:'+auditpillar+',audittype:'+audittype+',stepid:'+stepid+',auditdate:'+auditdate+',auditortype:'+auditortype);
		if(flid.trim().length>0 && 
				templateId.trim().length>0 && 
				auditpillar.trim().length>0 && 
				audittype.trim().length>0 && 
				stepid.trim().length>0 && 
				auditdate.trim().length>0 && 
				auditortype.trim().length>0 ){
			//alert(111);
			 	processAjaxCalls("jhuploadaudit_recall.jhdmtauditupd","?q=2&flid="+flid+"&templateid="+templateId+"&jhamAuditpillar="+auditpillar+"&jhamAudittype="+audittype+"&stepid="+stepid+"&auditdate="+auditdate+"&auditortype="+auditortype,"jhAuditRecallSuccess","jhAuditRecallError");
		}	
	  }
	  
	  function jhAuditRecallSuccess(result){
		  var keyId =result.successData.keyId;
		  var url = "jhdmtAuditUpload_input.jhdmtauditupd";
		  //alert('keyId:'+keyId);
		  if( keyId != undefined && keyId !='' && keyId !=' ' && keyId != null && keyId != 'undefined'  ){
			  jQuery("#hdnJhamKeyid").val(keyId);
			  setFieldValue("txtJhamTotalpoints",result.jhaTlAuditmst.jhamTotalpoints);
			  setFieldValue("hdnJhamStatus",result.jhaTlAuditmst.JhamStatus);	
			 // jQuery("#txtStatus").css('background-color','');
			  //jQuery("#txtStatus").val('');
			  //alert('result.jhaTlAuditmst.JhamTotalpoints:'+result.jhaTlAuditmst.jhamTotalpoints);
			  setStatus();
			  setFieldValue("txtJhamAuditorname",result.jhaTlAuditmst.jhamAuditorname);
			  setFieldValue("txtJhamLeadername",result.jhaTlAuditmst.jhamLeadername);
			  var flid=jQuery("#frmAuditCreation input[id='flid']").val(); 		  
		  	  viewGrid(url,"&s=1&flId="+flid+"&jhamKeyID="+keyId);
		  }
		  else {
			  jQuery("#hdnJhamKeyid").val('');
			  setFieldValue("txtJhamTotalpoints",'');
			 // jQuery("#txtStatus").css('background-color','');
			  //jQuery("#txtStatus").val('');
			  //alert('result.jhaTlAuditmst.JhamTotalpoints:'+result.jhaTlAuditmst.jhamTotalpoints);
			  setStatus();
			  setFieldValue("txtJhamAuditorname",'');
			  setFieldValue("txtJhamLeadername",'');
			  var flid=jQuery("#frmAuditCreation input[id='flid']").val(); 
			  var url = "jhdmtAuditUpload_input.jhdmtauditupd";
			  //alert(flid);
			  viewGrid(url,"&s=1&flId="+flid);
		  }
	  }
	  
	  function jhAuditRecallError(){
		  
	  }
	  

  function cmbJhamAudittype_onSelectError(result){
		setFieldValue("cmbJhamAuditlevel",result.auditLevel,"frmAuditUpload");	
		 var pillar =  jQuery("#hdnJhamAuditpillar").val();
		 var type =  getFieldValue("cmbJhamAudittype");
		  
  }
	var auditDate = jQuery('#dteJhamAuditdate').datebox("getValue");
	//alert("auditDate"+auditDate);
  function dteJhamAuditdate_onSelect(record){
	  //getJhAudit();
	 
		var auditDate1 = jQuery('#dteJhamAuditdate').datebox("getValue");
	
	  var rowIndex = jQuery('#hdnSelectRow').val();
	 
	  if((rowIndex.trim().length>0)){
		
		  if(auditDate == auditDate1)
		  {
			
			return false;
			}
		  else
			var result = confirm("Do You want to save the data ?");
			if (result == true)
			{
				 saveForm("frmAuditUpload","jhAuditCreation_save.jhAuditItc","");
			
			return true;
			
			}
			
		else {
			
			  var templateId=getFieldValue("cmbJhamAuditteamid");
			  //alert(templateId);
			  LoadJHParameter(templateId);
           	}
	  
	  }
	  //fillWithCurrentDate('dteJhamAuditdate');
	  var rowIndex = jQuery('#hdnSelectRow').val('');
	  if (dateValidation()==false ) 
		  return false;
	  
	  var templateId=getFieldValue("cmbJhamAuditteamid");
	  //alert(templateId);
	  LoadJHParameter(templateId);
  }
  
	function dateValidation()
	{
		var currentDate = getServerDateTime();
		var auditDate = jQuery('#dteJhamAuditdate').datebox("getValue");
		if(convertStringToDate(auditDate) > currentDate)
		{
			alert('Should Not Exceed Current Date');
			fillWithCurrentDate('dteJhamAuditdate');
			return false;
		}
		else
			clearValidationErrorMsg('dteJhamAuditdate');
	}
	
  function frmAuditUploadcmbJhamJhstepid_onSelect(record)
  {
	  /*var machineId = "";jQuery('#cmbJhamMachineid').combobox("getValue");
	  if(machineId==null || machineId=='' || machineId==' '){
	  }else{
	   processAjaxCalls("jhStepFill.jhAuditItc",'&machineId='+machineId ,'filljhStep_OnSuccess','filljhStep_OnError');
	  }*/
	  
	  //getApprovalLevel();
	  
	 // var team = jQuery("#cmbJhamJhstepid").val();
	 var team = record.id;
		var flid=jQuery("#frmAuditUpload input[id='flid']").val();
		var templateId=getFieldValue("cmbJhamAuditteamid");
		var url = "jhdmtAuditUpload_input.jhdmtauditupd";
		var filterStr="&s=1&templateId="+templateId+"&jhstepid="+team;
		if(team.trim().length>0){
			filterStr+="&flId="+flid;
		}
	 	//viewGrid(url,filterStr);
	 	var templateId=getFieldValue("cmbJhamAuditteamid");
		 LoadJHParameter(templateId, stepId);
	  //processAjaxCalls("fillStatus_recall.jhAuditItc","?q=2&parameter="+parameter+"&auditTeam="+record.id+"&rowId="+rowId,"minPointsRecallSuccess","minPointsRecallError");
  }
  function getApprovalLevel(){
	  var parameter=getFieldValue("cmbJhamAuditteamid");//jQuery('#hdnParamId').val();
	  var flId =  jQuery("#frmAuditUpload input[id='flid']").val();	
	  var jhLeader=jQuery("#cmbJhamJhstepid").combobox('getText');//	 record.text;
	  var auditTeam=getFieldValue("cmbJhamJhstepid");//	 record.text;
	  var isJhLeader="";
	  if (jhLeader.trim().substring(0,9)=="JH LEADER")
		  isJhLeader="&isJhLeader=Y";
	  //alert("jhLeaderf:"+jhLeader+",isJhLeader:"+isJhLeader+",flId="+flId+",parameter="+parameter+"auditTeam:"+auditTeam); 
	  processAjaxCalls("fillStatus_recall.jhdmtauditupd","?q=2&flid="+flId+"&parameter="+parameter+"&auditTeam="+auditTeam+isJhLeader,"minPointsRecallSuccess","minPointsRecallError");
  }
  function minPointsRecallSuccess(result){
	  if(result!=null || result!=undefined){
		var points=result.point;
		jQuery('#hdnMinpoints').val(points);
		var jhLeader=result.jhLeader;
		/*jQuery('#txtJhamLeadername').val(jhLeader);*/
		jQuery('#txtJhamAuditorname').val(jhLeader);
	  }
	  getJhAudit();	
  }
 	
  function frmAuditUpload_beforeDelete()
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
  
  function validateMandatory() {
	  
	 var sectId = "";
	 sectId = jQuery("#frmAuditUpload input[id='section']").val();
	 var cellId = "";
	 cellId = jQuery("#frmAuditUpload input[id='cell']").val();
		
	  var type =  getFieldValue("cmbJhamAudittype");
	  if(type!=null &&  type=="DMT") { 
		  	if (cellId!=null && cellId.trim().length>1) {
		 		alert("Select DMT ");
		 		return false;
		 	}
		  	if (sectId!=null && sectId.trim().length==0) {
		 		alert("Select DMT ");
		 		return false;
		 	}		 	
		 	
	  }

	  if( (type!=null && type == "JH") && (cellId!=null && cellId.length < 2) ){
		alert(" Select JH ");
		return false;
	  }		
	  return true;
  }
  
  function frmAuditUpload_beforeSubmit()
  {
	  //alert('frmAuditUpload_beforeSubmit');
 	var keyid = getFieldValue("hdnJhamKeyid");	
 	var totalpoint=jQuery("#txtJhamTotalpoints").val();
 	//alert(totalpoint);
 	var minmarks=jQuery("#txtminmarks").val();
 	
 
 	//var format =/[`!@#$%^&*()_+\-=\[\]{};'':"\\|,.<>\/?~]/;
 	
  	var txtJhamAuditorname=jQuery("#txtJhamAuditorname").val();
  	var txtJhamLeadername=jQuery("#txtJhamLeadername").val();
  	
  	// alert(txtJhamLeadername +"     "+txtJhamAuditorname );

  /* 	if(format.test(txtJhamAuditorname)==true ||format.test(txtJhamLeadername)==true){
  		alert("Special Characters are Not allowed !");
  		return false;
  	} */
  	

 	if (hasSpecialCharacters(txtJhamLeadername) || hasSpecialCharacters(txtJhamAuditorname)) {
 	    alert("Special Characters are Not allowed !");
 	    return false;
 	}
  	
 //	alert(minmarks);
 	if(totalpoint==minmarks || totalpoint > minmarks){
 //	alert("Inside total");
 	jQuery("#hdnJhamStatus").val("P");
 	jQuery("#txtStatus").val("Cleared");
 	jQuery("#txtStatus").css('background-color','#72a98b');
 	}
 	else{
 		//alert("else status");
 		jQuery("#hdnJhamStatus").val("F");
 		jQuery("#txtStatus").val("Not Cleared");
 		jQuery("#txtStatus").css('background-color','#ff9999');
 	}
 	setStatus();
 	getStatus();
  }	

  function getStatus(){
  	 var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs'); 	  
  	 setMaxPointStatus();
  	 setStatus();
	 var reviewCnt=0;
	 var type =  getFieldValue("cmbJhamAudittype");
	 var maxPass=4;
	 if(type.trim()=="DMT")
	 	var maxPass=3;

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
   	

  function frmAuditUpload_exceptionCallback(result){
  }
  
  function frmAuditUpload_deleteSuccessCallback(result){
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
	  var url = "jhdmtAuditUpload_input.jhdmtauditupd";
	  
	  viewGrid(url,"&s=1&flId="+flId+"&jhamKeyID="+jhamKeyID);
	  //jQuery("#jhNewAudit").trigger("reloadGrid"); 
	  return true;
  }
  
  function frmAuditUpload_successsCallback(result)
  {	
	 var filemanager=result.successData.filemanager;
	 var keyid=result.successData.keyId;	 
	 setFieldValue("hdnJhamKeyid",keyid);
	 var flid = jQuery("#frmAuditUpload input[id='flid']").val(); 		
	 var url = "jhdmtAuditUpload_input.jhdmtauditupd";
	 viewGrid(url,"&s=1&flId="+flid+"&jhamKeyID="+keyid);
	 if(filemanager==true){		
		setFieldValue("hdnJhamAuditpillar",result.successData.pillar);
		setFieldValue("cmbJhamAudittype",result.successData.audittype);
		fileManagerPopUp(keyid,"JHM","","","");		
	} 
  	else{  		
		if(keyid.length<=0){	
		  	setFieldValue("hdnJhamAuditpillar",result.successData.pillar);
			setFieldValue("cmbJhamAudittype",result.successData.audittype);	
			clearFields();
		}
	
		}
	 setStatus();
	 getStatus();
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
	setStatus();
	//jQuery("#jhNewAudit").clearGridData();
 }
	
  
 </script>
 
 <form name="frmAuditUpload" id="frmAuditUpload" >
 <div id="wrapperRpt">
 	<table id=" " border="0" width="80% " style=" " class="">
		<tr>
			<td colspan='3'>
				<div  id="frmAuditUploadFuntKeyIds">
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
			 	<div  class=""><label class="mandatory-lbl" id="lbltemplate">JH Template</label><br></div>
			    <div  class="" style="width:280px;"><input id="cmbJhamAuditteamid" name="cmbJhamAuditteamid" type="text" class="easyui-combobox" style="width:300px;"  value="${requestScope.jhaTlAuditmst.jhamAuditteamid}"/></div>
			 	<div  class=""><label >Audit Type</label></div>
			 	<div  class="" style="width:280px;"><input id="cmbJhamAudittype" name="cmbJhamAudittype" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.jhamAudittype}"/> </div>
			 	<div  class=""><label>Audit Level</label></div>
			 	<div  class="" style="width:280px;"><input id="cmbJhamAuditlevel" name="cmbJhamAuditlevel" type="text" class="easyui-combobox" disabled="disabled" style="width: 300px;" value="${requestScope.jhaTlAuditparameter.jhapAuditlevel}" /></div>
			 	</div>
		 	</td>
	 		<td valign="top" style=""  >
	 		<div   style=" width : 269px;">
			 	<div  class=""><label class="mandatory-lbl" >Approval Level</label><br></div>
			    <div  class=""><input id="cmbJhamJhstepid" name="cmbJhamJhstepid" type="text" class="easyui-combobox" style="width: 255px;" value="${requestScope.jhaTlAuditmst.jhamJhstepid}" /></div>
			    <div class=""><label class="mandatory-lbl">Auditor Name</label><br></div>
				 <div class=""><input id="txtJhamAuditorname" name="txtJhamAuditorname" type="text" class="easyui-text" style="width: 255px;" maxlength="200" onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"
				  value="${requestScope.jhaTlAuditmst.jhamAuditorname}"/></div>
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
					<div  class=""><label class="mandatory-lbl" >Members</label><br></div>
				    <div  class="">
				          <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  rows="3"  cols="17" id="txtJhamLeadername" title="Maximum Length is 500" maxlength="500" name="txtJhamLeadername"  onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"
				           style="height : 110px; margin-left: 5px;text-transform: uppercase; width : 214px;"   >${requestScope.jhaTlAuditmst.jhamLeadername}</textarea>
				    </div>
				</div>
			</td>
			<td valign="top">
			 <div style="padding-top:0px;">
				 <div  class=""><label class="mandatory-lbl" >Total Points</label><br></div>
					    <div class=""><input id="txtJhamTotalpoints" name="txtJhamTotalpoints" type="text" class="easyui-text" style="height:40px;width: 100px; text-align: center; cursor: default;font-size: 24px; font-weight: bolder;" value="${requestScope.jhaTlAuditmst.jhamTotalpoints}" /></div>
 				 <div class="">
					<label>Min. Marks to Clear</label>
					<label style="padding-left:16px;">Status</label><br>
				</div> 
				<div style="width:250px;">
				<input id="txtminmarks" name="txtminmark" type="text"  class="easyui-text" style="width: 100px; height:40px;background-color:transparent ;text-align:center;font-size: 24px;font-weight: bolder;" value="${requestScope.minMarks}"/>				    	
				<span  style="padding-left:18px;">
				<input id="txtStatus" name="txtStatus" type="text" class="easyui-text"  
				style="width:120px;height:40px;background-color:transparent;font-size: 20px;" 
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
  <input type="hidden" id="hdnSelectRow" name="hdnSelectRow" value=""/>
 </form>	
 	