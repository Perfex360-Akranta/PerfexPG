<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<script type="text/javascript">

jQuery(document).ready(function() {
	 initialiseForm('frmAbnormality');
	 jQuery('#submitForm').val('frmAbnormality'); // set the id of form to submit
	 /* var EASYUI_COMBO = {
			    isUserAction: false,
			    isAutoSelect: false
			}; */
	 
	 formatDateBox('dteAbnmDetectiondate','dd-MMM-yyyy');
	 formatDateBox('dteAbnmTargetdate','dd-MMM-yyyy');
	 formatDateBox('dteAbnmEffectivedate','dd-MMM-yyyy');
	 formatDateBox('dteAbnmWoendtime','dd-MMM-yyyy');
	 formatDateBox('dteAbnmWostarttime','dd-MMM-yyyy');
	 formatDateBox('dteAbnmenddate','dd-MMM-yyyy');
	 formatDateBox('dteAbnhDate','dd-MMM-yyyy');
	 formatDateBox('dteAbnmAccecptDate','dd-MMM-yyyy');
	 fillWithCurrentDate("dteAbnhDate");
	 fillComboBox("frmAbnormality","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=JH");
	 //fillComboBox("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");
	 jQuery('#txtAbnmCountermeasure').attr({'readonly':true});	 
    var locationId=jQuery("#hdnlocationid").val();
    if(locationId=='LCN0000005'){
    	jQuery('#lblabncategory').addClass('mandatory-lbl');
    //	jQuery('#cmbAbnmCategoryid').addClass('mandatory-lbl');
    	
    }
    
    var isFuntLocHierarchy = false;
    var isAutoSelect = false;
    
	 var others = jQuery("#hdnOthers").val();	
	 var repOthers = jQuery("#hdnRepOthers").val();
	 if(repOthers.trim() == "Y"){
//			othersClickAction(others);
			jQuery("#chkAbnmRepOtheres").prop("checked","checked");
	
		}
	 
		if(others.trim() == "Y"){
//			othersClickAction(others);
			jQuery("#chkAbnmOthers").prop("checked","checked");
	
		}
		
		var conStr = "" ;
		if(cellId != null || cellId !=''){
			conStr = "&cellId="+cellId ;
		}
		else
			conStr = "&cellId=123";
		
		 var sat = jQuery('#chkAbnmOthers:checked').val();   
		 
		 if(sat=="Y")
			 conStr += "&others=Y";
		
		 fillComboBox("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?"+conStr);
		
		 
		 //var trade = jQuery("#cmbAbnmTradeid").val();
		 var trade = getFieldValue("cmbAbnmTradeid");   
		 if(trade.trim()!="")
			 conStr += "&trade="+trade;
			
		 var respon = jQuery("#hdnRespond").val();
		 var cmpby = getFieldValue("cmbAbnmCompletedby");
		 //conStr += "&combokey="+respon;
		 fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?&loginEmpshow=false"+conStr+"&combokey="+respon);
		 fillComboBox("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?&loginEmpshow=false"+conStr+"&combokey="+cmpby);
	var documentNo =getFieldValue('cmbAbnmKeyid');
	fileManagerPopUp(documentNo,"ABN","frmAbnormality","btnFilManage","abnFilemgr");
		
	jQuery("#divPriority").hide();
	 jQuery("#chk").hide();
	 jQuery(".completedtls").hide();
	 
	 jQuery("#comp").hide();
	 var mode = jQuery("#mode").val();
	 //alert(mode);
	 
	 if(mode=="view")
	 {
	 	jQuery("#chkAbnmAccecpted").prop("disabled",true);
	 	readOnlyFields('dteAbnmAccecptDate');
	 	//jQuery("#dteAbnmAccecptDate").attr("readonly",true);
	 }
	 var modeType = jQuery("#hdnmodeType").val();
	 var mode = jQuery("#hdnmode").val();
	 
	  if(modeType == "accecpt"){
		 var compBy = getFieldValue('cmbAbnmCompletedby');	
		 //alert(compBy);
		 //setFieldValue('cmbAbnmCompletedby',compBy);
		 var accecptDate = getFieldValue('dteAbnmAccecptDate');
		 //alert(accecptDate);
		 if(accecptDate.trim()=="")
		 	fillWithCurrentDate("dteAbnmAccecptDate");
		 jQuery("#divAccecptRequired").hide();
		 jQuery("#divAcceptance").hide();
		 completedShow();
		 if(jQuery("#chkAbnmAccecpatncerequired").is(':checked') == true)
			 {
			 
			 jQuery("#divAcceptance").show();
			 jQuery("#divAcceptance").attr("display","block");
			 }
		 readOnlyFields('chkAbnmAccecpatncerequired');
		 readOnlyFields('dteAbnmWoendtime');
		 
		 readOnlyFields('txtAbnmCountermeasure');
		 disableUIButton('btnRepAbn');
		 
	 } else if(modeType == "modify"||mode=="modify"){
		 //disableField("frmAbnormality", "cmbAbnmTagclassid");
		 setTimeout(function() {readOnlyFields('cmbAbnmTagclassid');},1250);
		 jQuery("#divAcceptance").hide();
		 jQuery("#divAccecptRequired").show();

		 var tagText = jQuery('#cmbAbnmTagclassid').combobox('getText');
		 if (tagText =="WHITE-WHITE" || tagText == "TAG000001" ) {
			 readOnlyFields("cmbAbnmTradeid");
			 enableFields('cmbAbnmStatus');	 
			 jQuery("#chkAbnmShutdownmaint").prop("checked",false);
			 readOnlyFields('chkAbnmShutdownmaint');
			 readOnlyFields('chkAbnmNotifysap');
			 jQuery('#startandendtimediv').css('display','none');
			 jQuery("#chkAbnmAccecpatncerequired").prop('checked',false);
			 readOnlyFields('chkAbnmAccecpatncerequired');
		 }
	 }
	 else if(modeType == "complete"){
		 
		 jQuery("#divAcceptance").hide();
		 jQuery("#divAccecptRequired").show();
		 readOnlyFields('chkAbnmAccecpatncerequired');
		 //jQuery("#cmbAbnmCompletedby").attr("readonly",false);
	var status = jQuery("#cmbAbnmStatus").combobox('getValue');
	
	//jQuery("#frmAbnormalityabnmfunLocationValues").attr("readonly",true);
	 var compBy = getFieldValue('cmbAbnmCompletedby');		 
	 //setFieldValue('cmbAbnmCompletedby',compBy);
		 //setTimeout(function() {
			 //jQuery("#cmbAbnmStatus").combobox('setValue','C');
			 //fillWithCurrentDate("dteAbnmWoendtime");
			 abnmStatusSelect("P") ;
		 //},1250);

		 var abnStatus = jQuery("#hdnabnStatus").val();
		 
		 enableUIButton("btnFilManage");
		 
		 if(abnStatus == "P"){

			 jQuery(".completedtls").hide();
			 enableUIButton("btnabnActionpln");
			 disableUIButton("btnAbnYYLink");
			 disableUIButton("btnRepAbn");
			jQuery("#cmbAbnmStatus").combobox('setValue','P');
			//setComboValueSilent("cmbAbnmStatus", 'P');

		 setTimeout(function() {enableFields('cmbAbnmCompletedby');},250);
		 setTimeout(function() {enableFields('cmbAbnmStatus');},1750);
		 enableFields('dteAbnmWoendtime');
		 //enableUIButton('btnAbnWOLink');
		 }
		 else
			 {
			 completedShow();
			 enableUIButton("btnAbnYYLink");
			 disableUIButton("btnabnActionpln");
			 disableUIButton("btnRepAbn");
			 }
	 }
	 else if(mode=="view"){
		 
		 readOnlyFields('dteAbnmWoendtime');
	 }
	 else{
		 //alert(1);
		 jQuery("#divAcceptance").hide();
		 enableFields('cmbAbnmCompletedby');
		 enableFields('dteAbnmWoendtime');
         var keyid=jQuery('#hdnabnkeyID').val();
		 
		 if(keyid.length==0){
			 setTimeout(function() {fillWithCurrentDate("dteAbnmDetectiondate");},500);
			 setTimeout(function() {fillWithCurrentDate("spnAbnmDetectedbytime");},250);
         }

		 setTimeout(function() {fillTargetDate();},1250);
		 
	 }
	 var mode = jQuery("#mode").val();

	 if(mode=="create")
	 {
	 	//enableFields('txtAbnmDescription');
	 	jQuery("#divImpType").hide();
	 	jQuery("#divImmAction").hide();
	 	jQuery("#divWhatCause").hide();
	 	
	 	
	 	
	 }
	 if(mode == "modify")
	 {
		 
		 //jQuery("#cmbAbnmTagclassid").attr("readonly",true);
		 //readOnlyFields('txtAbnmDescription');
	 }
	 else{
	 	readOnlyFields('dteAbnmEffectivedate');
	 	readOnlyFields('chkTar');
	 	
	 }
	 if(mode == "view"){
	//disableForm("frmAbnormality");
		disableUIButton('btnRepAbn');

	 }
	 var url = jQuery('#hiddenUrl').val();
	 /* for functionalLocation*/
		var factId = jQuery("#frmAbnormality input[id='factory']").val();
		var sectionId = jQuery("#frmAbnormality input[id='section']").val();
		var cellId = jQuery("#frmAbnormality input[id='cell']").val();
		var machId = jQuery("#frmAbnormality input[id='machine']").val();
		var flid = jQuery("#frmAbnormality input[id='flid']").val();
			
		var hdnflid = jQuery("#hdnflid").val();
		if (hdnflid!=null && hdnflid.trim().length>0 )
			flid=hdnflid;

	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	
	 loadFunctionalLocation("abnmfunLocation","functionalLoc.abnForm","abnmfunLocationValues","frmAbnormality",dataStr);
	 fillComboBox("frmAbnormality","cmbAbnmTradeid","Combo_Trade.abnForm");
	 fillComboBox("frmAbnormality","cmbAbnhChangeby","employee.commonFilter?&cellId="+cellId);	
	 fillComboBox("frmAbnormality","cmbAbnmAssemblyid","assembly.commonFilter");
	 fillComboBox("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?&cellId="+cellId);
	 
	 if(modeType != "modify"){
		 comboFillWithCell("cmbAbnmResponsibleid");
		 //comboFillWithCell("cmbAbnmCompletedby");
		 reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?&cellId=+cellId&trade=+trade");
		 
	 	//fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?~cellId="+cellId);	 	
	 }
	 else{
		 //fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");
		 reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");
		 reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter");
		 //setTimeout(function() {fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");},1250);
	 }
	 
	 fillComboBox("frmAbnormality","cmbAbnmCategoryid","Combo_Category.abnForm?frmType="+jQuery('#hdnfrmType').val());
	 
     fillComboBox("frmAbnormality","cmbAbnmTypeid","Combo_Type.abnForm");
     
    // if(modeType != "complete"){
    	// comboFillWithCell("cmbAbnmDetectedby");//*****//
    	 //fillComboBox("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&cellId="+cellId);
     //} else{
    	 //setTimeout(function() {reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");},1250);//*****//
     //}
	 fillComboBox("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter");	
	 fillComboBox("frmAbnormality","cmbAbnmStatus","Combo_Status.abnForm","",false);
	 fillComboBox("frmAbnormality","cmbAbnmImpactid","Combo_Impact.abnForm");
	 fillComboBox("frmAbnormality","cmbAbnmAfeemid","Combo_Afeem.abnForm");
	 fillComboBox("frmAbnormality","cmbAbnmPillarid","pillar.commonFilter");
	 fillComboBox("frmAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm?frmType="+jQuery('#hdnfrmType').val());
	 spinnerKeyPress('spnAbnmDetectedbytime');
	 spinnerKeyPress('spnAbnmstarttime');
	 spinnerKeyPress('spnAbnmendtime');
	 

	 var modeType = jQuery("#hdnmodeType").val();
 			
	
	 //reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?trade="+record.id);
	 
	 if((url.indexOf('AbnId'))>0)
		 tagShow(true);
	 else 
		 tagShow(false);
	 
	 	
	 jQuery("#chk").hide();
	 
	 ShowHideLinkButtons('hdnyyEnable','AbnYYSpan');
	 ShowHideLinkButtons('hdnwoEnable','AbnWOSpan');
	 
	//readOnlyFields('txtAbnmCountermeasure');
		 if(jQuery('#hdnfrmType').val()=="SHE")
	 {
		 jQuery('#cmbAbnmAfeemid').attr('readonly',false);
	 }
	 else
	 {
	 	readOnlyFields("cmbAbnmAfeemid");
	 }
		 
	spinnerChange('spnAbnmDetectedbytime','detectionDateEvt');
	spinnerUp('spnAbnmDetectedbytime','detectionDateEvt');
	spinnerDown('spnAbnmDetectedbytime','detectionDateEvt');	
	spinnerChange('spnAbnmstarttime','workStartEvt');
	spinnerUp('spnAbnmstarttime','workStartEvt');
	spinnerDown('spnAbnmstarttime','workStartEvt');
	spinnerChange('spnAbnmendtime','workEndEvt');
	spinnerUp('spnAbnmendtime','workEndEvt');
	spinnerDown('spnAbnmendtime','workEndEvt');
	
	jQuery("#dteAbnmTargetdate").datebox("disable");
	dteAbnmDetectiondate_onSelect();
	jQuery(".txtarea").attr('maxlength','499');
	
	var s=jQuery('#hdnAbndImprovementteam').val();		
	jQuery('#cboAbndImprovementteam option[value="'+s+'"]').attr("selected", "selected");
	var priority=jQuery('#hdnAbnmPriority').val();
	jQuery('#cboAbnmPriority option[value="'+priority+'"]').attr("selected", "selected");
	
// 	readOnlyFields('cmbabnmMould');
	//setDatenTimeFlag('dteAbnmWostarttime','spnAbnmstarttime');
	
	//madhan
	//var endTime = jQuery('#spnAbnmendtime').spinner('getValue');
	
	//if(endTime != null && endTime != ' ' &&  endTime != '')
	//{
		//setDatenTimeFlag('dteAbnmenddate','spnAbnmendtime');
		//setDatenTimeFlag('dteAbnmWoendtime','spnAbnmendtime');
//	}

	  if(jQuery("#relatedToCMB").val() != null && jQuery("#relatedToCMB").val() !='')
	   {		  
		   //jQuery("#cmbabnmRelatedto").combobox('setValue',jQuery("#relatedToCMB").val());	 now commented	  
	   }

//------------------------------Fill ComboBox Values--------------------------------------//

	if(jQuery('#cmbAbnmKeyid').combobox('getValue').trim()!=""	)
			fillComboBox("frmAbnormality","cmbAbnmKeyid","Combo_TagNo.abnForm?tagno="+jQuery('#cmbAbnmKeyid').combobox('getValue').trim() );
    
	var compId = getFieldValue('company','frmAbnormality');
	var locnId = getFieldValue('location','frmAbnormality');
	var factId = getFieldValue('factory','frmAbnormality');
	var sectId = getFieldValue('section','frmAbnormality');
	var cellId = getFieldValue('cell','frmAbnormality');
	var machId = getFieldValue('machine','frmAbnormality');
	
		//fillComboBox("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId);
		fillComboBox("frmAbnormality","cmbAbndCircleid","circle.commonFilter" );
		reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?cellId="+ cellId );
// 		jQuery('#frmAbnormality .easyui-text').css('text-transform', 'uppercase');
// 		jQuery('#frmAbnormality textarea').css('text-transform', 'uppercase');
		jQuery(".txtarea").attr('maxlength','499');
		
	
	var typeId = getFieldValue("cmbAbnmTypeid");
	var abnDetectDate = getFieldValue("dteAbnmDetectiondate");
	var abnTargetDate = getFieldValue("dteAbnmTargetdate");
	var abnFeedBckDate = getFieldValue("dteAbnmWoendtime");
	
	tagClassColor();
	if( typeId != null && typeId.length > 0)
	{
		fillComboBox("frmAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm?abtmKeyid="+typeId+"&frmType="+jQuery('#hdnfrmType').val());
	}	
	if( abnDetectDate == null || abnDetectDate.length < 1)
	{
		fillWithCurrentDate("dteAbnmDetectiondate");
		//jQuery("#dteAbnmDetectiondate").datebox("setValue",srvTime());
		formatDateBox('dteAbnmDetectiondate','dd-MMM-yyyy');
		fillWithCurrentDate("spnAbnmDetectedbytime");
	}
	
	if( abnFeedBckDate == null || abnFeedBckDate.length < 1)
	{
		fillWithCurrentDate("dteAbnmWoendtime");
	}	
	
	if( abnTargetDate == null || abnTargetDate.length < 1)
	{
		fillTargetDate();
	}	

	 if(jQuery('#hdnfrmType').val()=="JH")
	 {
		jQuery('#cmbabnmSafetypatrol').combobox('setValue','JH');
		//setComboValueSilent("cmbabnmSafetypatrol", 'JH');
		readOnlyFields('cmbabnmSafetypatrol');
		 setTimeout(function() {isAbnFormType();},1250);
		fillComboBox("frmAbnormality","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=JH");
		var s=jQuery('#hdnAbndImprovementteam').val();		
		jQuery('#cboAbndImprovementteam option[value="'+s+'"]').attr("selected", "selected");
	 }
	 else if( jQuery('#hdnfrmType').val()=="SHE")
	 {
		//fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");
		reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");
		reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter");
		fillComboBox("frmAbnormality","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=SHE");
		jQuery('#circleInAbn').css('display','block');
		fillComboBox("frmAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm");
		 setTimeout(function() { setComboDefaultValue("frmAbnormality","cmbAbnmTagclassid");},1250);
		jQuery('#lblMachine').removeClass("mandatory-lbl");
		isHseFormType();
		jQuery('#cmbAbnmTypeid').combobox("disable");
		var s=jQuery('#hdnAbndImprovementteam').val();
		jQuery('#cboAbndImprovementteam option[value="'+s+'"]').attr("selected", "selected");
		jQuery('#cmbabnmSafetypatrol').combobox('setValue','SHE');
		//setComboValueSilent("cmbabnmSafetypatrol", 'SHE');
	 } 
	//var status = jQuery('#cmbAbnmStatus').val();
	var status = jQuery('#cmbAbnmStatus').combobox('getValue');
	if(status != null && status != '' && status != ' ')
	{
		if(status == 'C')
		{
			enableUIButton('btnAbnYYLink');
			//enableUIButton('btnAbnWOLink');
		}
		else
		{
			disableUIButton('btnAbnYYLink');
			disableUIButton('btnAbnWOLink');
		}
	}
	 
	 var clirefdocid = jQuery('#hdnclirefDocID').val();
	 if(clirefdocid.trim().length>0) 
	 	jQuery('#txtAbnmRefdocid').val(clirefdocid);

	 var mode= jQuery('#mode').val();	 	
	 	if(mode.indexOf('removal')>=0)
		{
			enableFields('txtAbnmContaminant');
			enableFields('txtAbnmMode');
		}

 });


jQuery("#cmbAbnmDetectedby").combobox({onRequest:function(opts){		
	var cellId = jQuery("#frmAbnormality input[id='cell']").val();
	 var sat = jQuery('#chkAbnmOthers:checked').val();
	 var keyid = getFieldValue("cmbAbnmDetectedby");
	 
	 if(cellId == null && cellId.trim()=="")
		 cellId = "123";
		 
	 if(sat=="Y")
	 {
		 return "cellId="+cellId+"&others=Y";
	 }
	 else{
		 
		 return "cellId="+cellId;
	 }
	}
});

jQuery("#cmbAbnmResponsibleid").combobox({onRequest:function(opts){		
	var cellId = jQuery("#frmAbnormality input[id='cell']").val();
	//var trade = jQuery("#cmbAbnmTradeid").val();
	var trade = getFieldValue("cmbAbnmTradeid");
	 var keyid = getFieldValue("cmbAbnmDetectedby");
	
	 if(trade.trim()!="")
	 {
		 return "cellId="+cellId+"&trade="+trade;
	 }
	 else
		 return "cellId="+cellId; 		
	}
});
 jQuery("#cmbAbnmCompletedby").combobox({onRequest:function(opts){		

		var cellId = jQuery("#frmAbnormality input[id='cell']").val();
		//var trade = jQuery("#cmbAbnmTradeid").val();
		var trade = getFieldValue("cmbAbnmTradeid");
		 var keyid = getFieldValue("cmbAbnmCompletedby");
		
		 if(trade.trim()!="")
		 {
			 return "cellId="+cellId+"&trade="+trade;
		 }
		 else
			 return "cellId="+cellId; 		
		}
 });
 jQuery("#cmbAbnmEquipmentid").combobox({onRequest:function(opts){		
		var machineId = jQuery("#frmAbnormality input[id='machine']").val();
		 return "combokey="+machineId; 		
 }
	});
 
 function dteAbnmWoendtime_onSelect(date){
		
		var completeDate = jQuery('#dteAbnmWoendtime').datebox('getValue') ;
	 	var dedectedDate = jQuery('#dteAbnmDetectiondate').datebox('getValue') ;
	 	var currentDate = getCurrentDate();
 /* 	alert(completeDate);
	 	alert(dedectedDate);
	 	alert(currentDate);  */
	 	
	 	
	 	if(compareDateTime(completeDate,dedectedDate) > 0 )
	 	{
	 		popupCommonErrorMsg('Completed Date Should Not Less than Dedected Date');
	 		fillWithCurrentDate('dteAbnmWoendtime');
	 	}
	 	else if(compareDateTime(currentDate,completeDate) > 0)
	 	{
	 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
	 		fillWithCurrentDate('dteAbnmWoendtime');
	 	}
	 	else{
	 	    clearValidationErrorMsg('dteAbnmWoendtime');
	 	}
	}

function btnFilManage_click(){
	    var documentNo =jQuery("#hdnabnkeyID").val();
	   if(documentNo != null && documentNo != ''){
			 var fmgMode = "";
			 var mode = jQuery("#mode").val();
			 
			 if(mode=="view" || jQuery('#cmbAbnmStatus').combobox('getValue')=="C")
				 	fmgMode = "view";
			fileManagerPopUp(documentNo,"ABN","","","", fmgMode);
		}
	   else {    
		 saveForm('frmAbnormality','Abnormality_save.abnForm?openfilemgr=openfilemgr');
	    }		
	}

 jQuery("#btnRepAbn").click(function(){
	 var machine = jQuery("#cmbAbnmEquipmentid").combobox('getValue');
	 //var assembly = jQuery("#cmbAbnmAssemblyid").combobox('getValue');
	 var type = jQuery("#cmbAbnmTypeid").combobox('getValue');
	 var flid = jQuery("#frmAbnormality input[id='flid']").val();
	 
	 if(flid.trim()=="")
	 {
	 	alert("Select Functional Location");
	 	return false;
	 }
	 
	 var dataString = "&cmbMchid="+machine+"&cmbabntype="+type+"&flid="+flid;
	 
	 LoadPopUp("RepAbn","repeatedAbn_input.abnForm?q=2"+dataString,true,"40%","85%","1%","14%","","Last 10 Abnormalities","","",true,"setFileManagerdimension");
 });
 
 jQuery("#btnabnActionpln").click(function(){
	 var hdnabnkeyID=jQuery("#hdnabnkeyID").val();
	 if(hdnabnkeyID.trim().length>0)
     { 
		 var keyid=jQuery("#hdnabnkeyID").val();
		 var flid=getFieldValue ('flid','frmAbnormality');//jQuery("#flid").val();
		 var mainTask = jQuery("#txtAbnmDescription").val();
		 var abnDetectDate = getFieldValue("dteAbnmDetectiondate");
		 var apMode = "create";
		 if(jQuery('#cmbAbnmStatus').combobox('getValue')=="C")
			 	 apMode = "view";
		 if(jQuery('#mode').val()=="view")
		 	 apMode = "view";
		 openActionPlan("abnActionPlan",keyid,"abn",flid,mainTask,keyid,abnDetectDate,apMode);
     }else 
     {    
	 saveForm('frmAbnormality','Abnormality_save.abnForm?openactnpln=openactnpln');
     }		
	 	 });	
 jQuery("#btnAbnYYLink").click(function(){//COMMENTED FOR DEMO PURPOSES 
	 //navigateToNextForm('whywhyanalysis_input.why','Why Why Analysis');
 	 var apMode = jQuery('#mode').val();
 	 //alert(apMode);
 	 var modeType = jQuery("#hdnmodeType").val();
	 if( modeType == "accecpt")
	 	 apMode = "view";
	 
	 if(apMode=="view") {
		 var flid = jQuery("#frmAbnormality input[id='flid']").val();
		var refDocDate = jQuery('#dteAbnmWoendtime').datebox("getValue");
		var refDocId = jQuery('#cmbAbnmKeyid').val();
		var problem = jQuery('#txtAbnmDescription').val();
 		var attendedBy = getFieldValue("cmbAbnmCompletedby");
 		var formMode = jQuery("#hdnFormMode").val();
 		//alert(formMode);
 		if(jQuery('#cmbAbnmStatus').combobox('getValue')=="C")
 			formMode = "View";
 		closePopUpDialoge("loadAbnModify");
		//openWhyWhy("LoadAbnWHYWhy",true,refDocId,"ABN",flid, refDocDate, problem, formMode, null, null, attendedBy);
 		openWhyWhy("",false,refDocId,"ABN",flid, refDocDate, problem, formMode, null, null, attendedBy);

	 }
	 else
	 	saveForm('frmAbnormality','Abnormality_yy.abnForm?q=2&whywhy=Y');
	 
 });
 jQuery("#btnAbnWOLink").click(function(){//COMMENTED FOR DEMO PURPOSES
	 navigateToNextForm('workReq_input.work','Maintenance Service Request' );
	// saveForm('frmAbnormality','Abnormality_yy.abnForm?q=2&workorder=Y');
 });
 
 function loadTeamCombo() {	
		var compId = getFieldValue('company','frmAbnormality');		
	    var locnId = getFieldValue('location','frmAbnormality');
		var factId = getFieldValue('factory','frmAbnormality');
		var sectId = getFieldValue('section','frmAbnormality');
		var cellId = getFieldValue('cell','frmAbnormality');
		var machId = getFieldValue('machine','frmAbnormality');

		var empId;
		empType=jQuery('#hdnempty').val();

		if (empType=='R')
			empId=jQuery('#cmbAbnmDetectedby').combobox('getValue');
		else if (empType=='C')
			empId=jQuery('#cmbAbnmCompletedby').combobox('getValue');
		var docKeyid = jQuery('#cmbAbnmKeyid').val();
		
		var funLcnIds ='&compId='+compId+'&factId='+factId+'&sectId='+sectId+'&cellId='+cellId+'&mchId='+machId+'&empId='+empId;
		var dataStr ="?q=2&docKeyid="+docKeyid+"&empType="+empType+"&funLcnIds="+escape(funLcnIds);
		
		//LoadForm("divTeam","predivTeam","loadTeamCombo.team"+dataStr,"","DivsucessCall" );
	}  
 
	function DivsucessCall(){
		
	}
 function ShowHideLinkButtons(txtField,spanId){
 
	 var isEnable = jQuery('#'+txtField).val();
	 if(isEnable != null && isEnable != ' ' && isEnable != '')
	 {
		 if(isEnable == "Y")
			 jQuery('#'+spanId).show();
		 else
			 jQuery('#'+spanId).hide();			 
	 }
	 else
		 jQuery('#'+spanId).hide();
	 
 }
 function tagClassColor()
 {
	 var tagClass = jQuery("#hdnTagClass").val();
	 
	// jQuery("#lblTagClass").css("background","White");
	 if(tagClass == "RED")
	 {
		jQuery("#lblTagClass").css("background","red");
		 jQuery("#lblTagClass").html("RED TAG");
		 jQuery("#lblTagClass").css("color","black");
		 jQuery("#lblTrade").addClass('mandatory-lbl');
		 
		 var modeType = jQuery("#hdnmodeType").val();
		 if(modeType == "create" || modeType == "")
		 	enableFields("cmbAbnmTradeid"); 
	 }
	 else if(tagClass == "WHITE")
	 {
		 jQuery("#lblTagClass").css("background","White");
		 jQuery("#lblTagClass").html("WHITE TAG");
		 jQuery("#lblTagClass").css("color","black");
		 jQuery("#lblTrade").removeClass('mandatory-lbl');
		 
		 readOnlyFields("cmbAbnmTradeid");
	 }
	 else
	 { 
		 jQuery("#hdnTagClass").hide();
		 jQuery("#lblTagClass").html("");
		 jQuery("#lblTagClass").removeClass("easyui-text");
		 jQuery("#lblTrade").removeClass('mandatory-lbl');
	 }
	
 }
 function fillTagClassColor(id)
 {
	 var color = id.substring(0,1);
	if(color == "W")
	{
		jQuery("#lblTagClass").css("background","White");
		 jQuery("#lblTagClass").html("WHITE TAG");
		 jQuery("#lblTagClass").css("color","black");
		 jQuery('.incompletion').removeClass('mandatory-lbl');
		 //var loginid = jQuery("#cmbAbnmDetectedby").combobox('getId');
		 var loginid = getFieldValue('cmbAbnmDetectedby');
		 jQuery("#lblTrade").removeClass('mandatory-lbl');
		 setFieldValue('cmbAbnmResponsibleid',loginid);
	}
	else if(color == "R")
		{
		jQuery("#lblTagClass").css("background","red");
		 jQuery("#lblTagClass").html("RED TAG");
		 jQuery("#lblTagClass").css("color","black");
		 if(jQuery('#cmbAbnmStatus').combobox('getValue')=="C")
			 jQuery('.incompletion').addClass('mandatory-lbl');
		 setFieldValue('cmbAbnmResponsibleid',"");
		 jQuery("#lblTrade").addClass('mandatory-lbl');
		}
 }


function frmAbnormalitycmbAbnmTradeid_onSelect(record)
{	
	//var location = jQuery("#frmAbnormality input[id='location']").val();
	//var cellId = jQuery("#frmAbnormality input[id='cell']").val();
	comboFillWithTrade("RED-RED",record.id);
	//reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?trade="+record.id+"&locnId="+location+"&cellId="+cellId);
	
}

 function  frmAbnormalitycmbAbnmStatus_onSelect(record)
 {
	 abnmStatusSelect(record.id) ;
	 //comboFillWithCell('cmbAbnmCompletedby');
	 
	 var compkey = getFieldValue("cmbAbnmCompletedby");
	 var cellId = jQuery("#frmAbnormality input[id='cell']").val();
	 reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?&cellId="+cellId+"&combokey="+compkey);
	 
	 if("C" == record.id)
	 	enableFields('dteAbnmWoendtime');
	else if("P"==record.id)
		jQuery('#txtAbnmCountermeasure').val('');
 }

 function abnmStatusSelect(id) {
	 
	 var tagClass = jQuery("#cmbAbnmTagclassid").combobox('getText');
	 
	 var tagFlag = jQuery('#hdnPendComp').val();
	 var showCompletedDate = jQuery('#hdnShowCompDate').val();
	 if(tagFlag != null && tagFlag != '' && tagFlag != ' ')
	 {	 
		if(tagFlag == "N")
			jQuery('#startandendtimediv').css('display','none');
	 }
			
	if(id=='C')
	{
		jQuery('#hdnempty').val("C");
		//loadTeamCombo();		
		jQuery("#pend").hide();
		jQuery("#comp").show();		
		
		jQuery(".completedtls").show();
		

		disableUIButton("btnAbnWOLink");
		disableUIButton("btnabnActionpln");
		//disableUIButton("btnFilManage");
		disableUIButton("btnRepAbn");
		
		
		
		jQuery('#txtAbnmCountermeasure').attr({'readonly':false});
		if(tagClass.trim().substring(0,3)== "RED")
		{
				jQuery('.incompletion').addClass('mandatory-lbl');
				if(showCompletedDate != null && showCompletedDate != '' && showCompletedDate != ' ' && showCompletedDate)
				{
					if(showCompletedDate == 'Y')
					{
						jQuery('#startandendtimediv').css('display','none');						
					}
					else
					{
					//	jQuery('#lblCompDate').css('display','none');
						//jQuery('#divCompDate').css('display','none');
					}
				}
				else
				{
					jQuery('#lblCompDate').css('display','none');
					jQuery('#divCompDate').css('display','none');
				}
		}
		else
		{
			jQuery('#startandendtimediv').css('display','none');	
		}
		enableUIButton('btnAbnYYLink');
		//enableUIButton('btnAbnWOLink');
	}					
	else if(id=='P')
	{	
		jQuery('#hdnempty').val("R");
		//loadTeamCombo();
		jQuery(".completedtls").hide();
		jQuery("#pend").show();	
		jQuery("#comp").hide();				
		jQuery('.incompletion').removeClass('mandatory-lbl');
//		jQuery('#txtAbnmCountermeasure').val("");
		jQuery('#txtAbnmCountermeasure').attr({'readonly':true});
		disableUIButton('btnAbnYYLink');
		disableUIButton('btnAbnWOLink');
		
		enableUIButton("btnabnActionpln");
		enableUIButton("btnFilManage");
		enableUIButton("btnRepAbn");
	}

 }

 function  frmAbnormalitycmbAbnmTypeid_onSelect(record)
 {
	var check = false;
	 reloadCombo("frmAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm?abtmKeyid="+record.id+"&frmType="+jQuery('#hdnfrmType').val());
		jQuery("#cmbAbnmSubtype").combobox('clear');
		/* if(jQuery('#hdnfrmType').val().trim()!="" && jQuery('#hdnfrmType').val().trim()== "JH" ) 
			processAjaxCalls("getAbnType.abnForm?abtmKeyid="+record.id,"","abnTypeOnsuccessCallback");
		if(record.text.indexOf('UNSAFE') >= 0)
		{
			//setTimeout(function() {	 jQuery('#cboAbndImprovementteam').attr({"readonly":false});},1250);
		}
		else
			setTimeout(function() {jQuery('#cboAbndImprovementteam').attr({"readonly":true});},1250);
		
		checkRepAbn(); */
		
 }
 function checkRepAbn()
 {
	 var machine = jQuery("#cmbAbnmEquipmentid").combobox('getValue');
	 var type = jQuery("#cmbAbnmTypeid").combobox('getValue');
	 var flid = jQuery("#frmAbnormality input[id='flid']").val();
	 if(machine.trim()!="" && type.trim()!=""){
	 	var dataString = "&cmbMchid="+machine+"&cmbabntype="+type+"&flid="+flid;
		processAjaxCalls("repeatedAbn_getData.abnForm?"+dataString,"","repAbnSuccessCallBack");
	 }
 }
 function repAbnSuccessCallBack(result)
	{	
		if(result.rows.length>0)
		{
			var machine = jQuery("#cmbAbnmEquipmentid").combobox('getValue');
			 var type = jQuery("#cmbAbnmTypeid").combobox('getValue');
			 var flid = jQuery("#frmAbnormality input[id='flid']").val();
			 
			 var dataString = "&cmbMchid="+machine+"&cmbabntype="+type+"&flid="+flid;
		 	LoadPopUp("RepAbn","repeatedAbn_input.abnForm?q=2"+dataString,true,"40%","85%","1%","14%","","Last 10 Abnormalities","","",true,"setFileManagerdimension");
		}
		
	}
 
 function formatDate1(d) {
     let dd = String(d.getDate()).padStart(2, '0');
     let mm = String(d.getMonth() + 1).padStart(2, '0');
     let yyyy = d.getFullYear();
	 const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
     return dd + '-' + mm + '-' + yyyy; // match your datebox format
 }
 
 function formatDate(date){
		let newDate = new Date(date);
			   

		const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

		// Format DD-MMM-YYYY
	    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
					            months[newDate.getMonth()] + '-' +
					            newDate.getFullYear();
		return formatted;
	 }
 
 function popup_OnSaveForm(){
		//alert("inside opl");
		saveForm('frmAbnormality','Abnormality_save.abnForm');
	}
 
function dteAbnmAccecptDate_onSelect(date){
	//accecptDateDateEvt();
	var accecptDate =formatDate(date);// jQuery('#dteAbnmAccecptDate').datebox('getValue') ;
	 	var completeDate = jQuery('#dteAbnmWoendtime').datebox('getValue') ;
	 	var currentDate = getCurrentDate();
	 	//if(convertStringToDate(accecptDate) < convertStringToDate(completeDate) )
	 	if(compareDateTime(accecptDate,completeDate) > 0 )
	 	{
	 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Less than Detected Date');
	 		popupCommonErrorMsg('Accecpted Date Should Not Less than Completed Date');
	 		//fillWithCurrentDate('dteAbnmAccecptDate');
	 		setTimeout(function () {
	 			fillWithCurrentDate('dteAbnmAccecptDate');
	        }, 10);
	 	}
	 	//else if(convertStringToDate(accecptDate) > currentDate)
	 	else if(compareDateTime(accecptDate,currentDate) < 0 )
	 	{
	 		//showValidationErrorMsg('dteAbnmEffectivedate','Should Not Exceed Current Date/Time');
	 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
	 		//fillWithCurrentDate('dteAbnmAccecptDate');
	 		setTimeout(function () {
	 			fillWithCurrentDate('dteAbnmAccecptDate');
	        }, 10);
	 	}
	 	else{
	 	    clearValidationErrorMsg('dteAbnmAccecptDate');
	 	}
}
 function dteAbnmTargetdate_onSelect(date) {
  		//targetDateDateEvt();
	 var targetDate = formatDate(date);// jQuery('#dteAbnmTargetdate').datebox('getValue') ;
	// alert(targetDate);
	 	var detectoinDate = jQuery('#dteAbnmDetectiondate').datebox('getValue') ;
	 	var currentDate = getServerDateTime();
	 	
	 	/*if(convertStringToDate(targetDate) > currentDate)
	 	{
	 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Exceed Current Date/Time');
	 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
	 		fillWithCurrentDate('dteAbnmTargetdate');
	 	}*/
	 	
	 	 if(convertStringToDate(targetDate) < convertStringToDate(detectoinDate) )
	 	{
	 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Less than Detected Date');
	 		popupCommonErrorMsg('Should Not Less than Detected Date');
	 		setTimeout(function () {
	 			fillWithCurrentDate('dteAbnmTargetdate');
	        }, 10);
	 		//fillWithCurrentDate('dteAbnmTargetdate');
	 	}
	 	else
	 	    clearValidationErrorMsg('dteAbnmTargetdate');
} 

 function dteAbnmEffectivedate_onSelect(date) {
	 //effectiveDateEvt();
	 var effectiveDate = formatDate(date);// jQuery('#dteAbnmEffectivedate').datebox('getValue') ;
	 	var detectoinDate = jQuery('#dteAbnmDetectiondate').datebox('getValue') ;
	 	var currentDate = getServerDateTime();
	 	
	 	if(convertStringToDate(effectiveDate) > currentDate)
	 	{
	 		//showValidationErrorMsg('dteAbnmEffectivedate','Should Not Exceed Current Date/Time');
	 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
	 		//fillWithCurrentDate('dteAbnmEffectivedate');
	 		setTimeout(function () {
	 			fillWithCurrentDate('dteAbnmEffectivedate');
	        }, 10);
	 	}
	 	
	 	else if(convertStringToDate(effectiveDate) < convertStringToDate(detectoinDate) )
	 	{
	 		//showValidationErrorMsg('dteAbnmEffectivedate','Should Not Less than Detected Date');
	 		popupCommonErrorMsg('Should Not Less than Detected Date');
	 		//fillWithCurrentDate('dteAbnmEffectivedate');
	 		setTimeout(function () {
	 			fillWithCurrentDate('dteAbnmEffectivedate');
	        }, 10);
	 	}
	 	else
	 	    clearValidationErrorMsg('dteAbnmEffectivedate');
} 
  
/*  jQuery('#dteAbnmDetectiondate').datebox({  	   
 	onSelect:function(recordid)
		{
  		var targetDate = jQuery('#dteAbnmDetectiondate').datebox("getValue");
  		fillTargetDate(targetDate);
  		detectionDateEvt();
		} 
 });   */
 
 function dteAbnmDetectiondate_onSelect(date)
 {
	 //fillTargetDate(formatDate(date));
		detectionDateEvt(date);
	 
  /*  jQuery('#dteAbnmDetectiondate').datebox({  	   
    	onSelect:function(recordid)
 		{
     		var targetDate = jQuery('#dteAbnmDetectiondate').datebox("getValue");
     		fillTargetDate(targetDate);
     		detectionDateEvt();
 		} 
    }); */  
 
  
 }
 function frmAbnormalitycmbAbndCircleid_onLoadSuccess()	
 {
		setComboDefaultValue("frmAbnormality", "cmbAbndCircleid");		
 }
 function frmAbnormalitycmbAbndResponsiblity_onSelect(record)
 {	 
	 processAjaxCalls("getTrade.abnForm?abnmRespons="+record.id,"","getAbnmTradeValues");
 }
 function getAbnmTradeValues(result)
 {	 
	 jQuery('#cmbAbnmTradeid').combobox('setValue',result.abnTrade);
	 //setComboValueSilent("cmbAbnmTradeid", result.abnTrade);
 }
 function frmAbnormalitycmbAbndCircleid_onSelect(record)
 {
	 //jQuery('#cmbAbnmEquipmentid').combobox('clear');
	 //reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?circleId="+record.id);
 }
 function frmAbnormalitycmbAbnmTagclassid_onSelect(record)
 {
	 var targetDate = jQuery('#dteAbnmDetectiondate').datebox("getValue");
	 fillTagClassColor(record.text);
	 fillTargetDate(targetDate,record.text);
	 var tagClass = jQuery('#hdnPendComp').val();
	 //var tagText = jQuery('#cmbAbnmTagclassid').combobox('getText');
	 var tagText = record.text;
	 
	 var locationId=jQuery("#hdnlocationid").val();
	    if(locationId=='LCN0000005' && tagText.trim() == 'RED-RED' ){
	    	 jQuery("#cmbAbnmTradeid").combobox('setValue','TDE00800007');
	    	 //setComboValueSilent("cmbAbnmTradeid", 'TDE00800007');
	    	 processAjaxCalls("Abnservice_getCount.abnForm?","","ServiceCntSuccessCallBack");
	    }

	 if(tagClass != null && tagClass != '' && tagClass != ' ')
	 {	 
		 if(tagClass == "Y")
		 {
			if(tagText == 'RED-RED')
			{
				 jQuery(".completedtls").hide();
				 enableFields('chkAbnmShutdownmaint');
// 				 enableFields('chkAbnmNotifysap');
				 jQuery("#cmbAbnmStatus").combobox('setValue','P');
				 //setComboValueSilent("cmbAbnmStatus", 'P');
				 jQuery("#pend").show();	
				 jQuery("#comp").hide();	
				 readOnlyFields('cmbAbnmStatus');
				 disableUIButton('btnAbnYYLink');
				 disableUIButton('btnAbnWOLink');
				 
				 var modeType = jQuery("#hdnmodeType").val();
				 if(modeType == "create" || modeType == "")
				 	enableFields("cmbAbnmTradeid");
				 
				 clearField("cmbAbnmCompletedby");
				 comboFillWithTrade("RED-RED",null);
				 
				 //comboFillWithTrade('cmbAbnmCompletedby');
				/*var trade = jQuery("#cmbAbnmTradeid").combobox("getValue");
				if(trade != null || trade.trim() != "")
					setTimeout(function() {reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?trade="+trade);},1250);
				*/

				enableFields('chkAbnmAccecpatncerequired');
			}
			 else
			{
				// readOnlyFields("cmbAbnmTradeid");
				enableFields("cmbAbnmTradeid");
				 enableFields('cmbAbnmStatus');	
				 jQuery("#chkAbnmShutdownmaint").attr("checked",false);
				// readOnlyFields('chkAbnmShutdownmaint');
				 //readOnlyFields('chkAbnmNotifysap');
				 enableFields('chkAbnmShutdownmaint');
				 enableFields('cmbAbnmStatus');
				 jQuery('#startandendtimediv').css('display','none');
				 //jQuery('#lblTrade').addClass('Mandatory-lbl');
				 var cellId = jQuery("#frmAbnormality input[id='cell']").val();
				 //alert('cellId'+cellId);
				 //comboFillWithCell("cmbAbnmCompletedby");
				 reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?&cellId="+cellId);
				 var respid=jQuery("#cmbAbnmResponsibleid").combobox('getValue');
				// alert('respid'+respid);
				 if(respid.length!=0)
					 {
                       processAjaxCalls("getTrade.abnForm?abnmRespons="+respid,"","getAbnmTradeValues");
					 }
				/// reloadCombo("frmAbnormality","cmbAbnmTradeid","Combo_Trade.abnForm?&combokey="+respid);
				// fillComboBox("frmAbnormality","cmbAbnmTradeid","Combo_Trade.abnForm");
				 comboFillWithTrade("WHITE-WHITE",null);
				 jQuery("#chkAbnmAccecpatncerequired").prop('checked',false);
				 readOnlyFields('chkAbnmAccecpatncerequired');
			}
		 }
		 else if(tagClass == "N")
		 {
			 jQuery('#startandendtimediv').css('display','none');
		 }
	 }
	 else
	 {
		 if(tagText == 'RED-RED')
		 {
				 jQuery(".completedtls").hide();
			//	 jQuery("#cmbAbnmStatus").combobox('setValue','P');
				 jQuery("#pend").show();	
				 jQuery("#comp").hide();	
				 var showCompletedDate = jQuery('#hdnShowCompDate').val();	
				 if(showCompletedDate != null && showCompletedDate != '' && showCompletedDate != ' ' && showCompletedDate)
				 {
					if(showCompletedDate == 'Y')
					{
						jQuery('#lblCompDate').css('display','block');
						jQuery('#divCompDate').css('display','block');	
						jQuery('#startandendtimediv').css('display','none');							
				    }
					else
					{
						jQuery('#lblCompDate').css('display','none');
						jQuery('#divCompDate').css('display','none');	
						jQuery('#startandendtimediv').css('display','block');	
					}
				}
				else
				{
					jQuery('#lblCompDate').css('display','none');
					jQuery('#divCompDate').css('display','none');	
					jQuery('#startandendtimediv').css('display','block');	
				}
				 disableUIButton('btnAbnYYLink');
				 disableUIButton('btnAbnWOLink');
				 
		  }
		  else
		  {
			  
				jQuery('#startandendtimediv').css('display','none');
				jQuery('#lblCompDate').css('display','block');
				jQuery('#divCompDate').css('display','block');
		  }
	 }
 }
 
 
 function ServiceCntSuccessCallBack(result)
 {
	var cnt=result.servicecnt;
	//alert(cnt);
    if(cnt!="2")
    	{
    	jQuery("#cmbAbnmResponsibleid").combobox('setValue',cnt);
    	//setComboValueSilent("cmbAbnmResponsibleid", cnt);
    	jQuery("#cmbAbnmResponsibleid").combobox("disable");
    	jQuery("#cmbAbnmTradeid").combobox("disable");
    	}
    else{
    return true;
    }
 }

 function fillTargetDate(targetDate,tagClass)
 {
	 processAjaxCalls("Datebox_FillTargetDate.abnForm?row=0","&targetDate="+targetDate+"&tagClass="+tagClass,"TargetDateonSuccessCallback");
 }

function TargetDateonSuccessCallback(result)
{
	setFieldValue('dteAbnmTargetdate',result.date);
}
function comboFillWithCell(comboId)
{
	
	var location = jQuery("#frmAbnormality input[id='location']").val();
	var cellId = jQuery("#frmAbnormality input[id='cell']").val();
	
	reloadCombo("frmAbnormality",comboId,"employee.commonFilter");
}
function comboFillWithTrade(tag,trade)
 {
	 var location = jQuery("#frmAbnormality input[id='location']").val();
	 trade =  trade == null ? jQuery("#cmbAbnmTradeid").combobox("getValue") : trade;
	 var cellId = jQuery("#frmAbnormality input[id='cell']").val();
	// var trade = getFieldValue("cmbAbnmTradeid");	 
	 var rep = jQuery('#chkAbnmRepOtheres:checked').val();
	 //jQuery("#cmbAbnmResponsibleid").combobox('setValue',"");
	 var locnId = jQuery("#frmAbnormality input[id='location']").val();
 	
	 if(cellId == null || cellId == ''){
		 cellId = "123";
	 }
	 
	 if(trade==null || trade=='' || trade==' ')
		 trade = "others";
	  
	 if(tag=="RED-RED"){
		 var mode = jQuery("#mode").val();
		 var respon = jQuery("#hdnRespond").val();
		 if(mode != "complete" && mode != "view")
		 	enableFields("chkAbnmRepOtheres");
		 if(rep=="Y"){
			 //if (( locnId!=null && locnId!='LCN0000002'))
			   //   jQuery("#lblResp").addClass('mandatory-lbl');

			 //jQuery("#cmbAbnmResponsibleid").combobox('clear');
			 //var compkey = getFieldValue("cmbAbnmCompletedby");
			 jQuery("#cmbAbnmCompletedby").combobox('clear');
			 var compkey = getFieldValue("cmbAbnmCompletedby");	
			 var reskey = getFieldValue("cmbAbnmResponsibleid");
             setTimeout(function() {
                 reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?loginEmpshow=false&trade="+trade+"&combokey="+compkey+"&others="+rep);
     	         reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?loginEmpshow=false&trade="+trade+"&combokey="+reskey+"&others=Y");
			 },1250);
		  }
		 else{
			 //if (( locnId!=null && locnId!='LCN0000002'))
				// jQuery("#lblResp").removeClass('mandatory-lbl');
			 //jQuery("#cmbAbnmResponsibleid").combobox('clear');
			 //jQuery("#cmbAbnmCompletedby").combobox('clear');
			 var compkey = getFieldValue("cmbAbnmCompletedby");	
			 var reskey = getFieldValue("cmbAbnmResponsibleid");
			 setTimeout(function() {
			 	reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?loginEmpshow=false&cellId="+ cellId+"&trade="+trade+"&combokey="+reskey);
			 	reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?loginEmpshow=false&cellId="+ cellId+"&trade="+trade+"&combokey="+compkey);
			 },1250);
		 }
		}
	 else{
		 jQuery("#chkAbnmRepOtheres").prop("checked",false);
		 readOnlyFields("chkAbnmRepOtheres");
		 var respon = jQuery("#hdnRespond").val();
		 var compid=getFieldValue("cmbAbnmCompletedby");
		 //alert(" respon :: "+respon+" compid :: "+compid);
		 jQuery("#cmbAbnmTradeid").combobox("setValue","");
		 //setComboValueSilent("cmbAbnmTradeid", "");
		 reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?cellId="+ cellId+"&combokey="+respon);
		 setTimeout(function() {reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?cellId="+ cellId+"&combokey="+compid);},2250);
	 }
	 	
 }
 function  frmAbnormalitycmbAbnmSubtype_onSelect(record)
 {
	 //reloadCombo("frmAbnormality","cmbAbnmTypeid", "Combo_Type.abnForm?abtmType="+record.id);
 }	

 
/* function  frmAbnormalitycmbFactory_onSelect(record)
 {
 	jQuery("#cmbAbnmSectionid").combobox('clear');
 	jQuery("#cmbAbnmCellid").combobox('clear');
 	jQuery("#cmbCostcenter").combobox('clear');
 	//jQuery("#cmbAbnmEquipmentid").combobox('clear');
 	reloadCombo("frmAbnormality","cmbAbnmSectionid","sectionCombo.commonFilter?factId="+record.id);
 	reloadCombo("frmAbnormality","cmbAbnmCellid","cellCombo.commonFilter?factId="+record.id  );
 	reloadCombo("frmAbnormality","cmbCostcenter","costCenter.commonFilter?factId="+record.id  );
 	//reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?factId="+ record.id   );
 	
 }*/

/* function  frmAbnormalitycmbAbnmSectionid_onSelect(record)
 {
 	jQuery("#cmbAbnmCellid").combobox('clear');
 	jQuery("#cmbCostcenter").combobox('clear');
 	//jQuery("#cmbAbnmEquipmentid").combobox('clear');
 	reloadCombo("frmAbnormality","cmbAbnmCellid","cellCombo.commonFilter?sectId="+record.id  );
 	reloadCombo("frmAbnormality","cmbCostcenter","costCenter.commonFilter?sectId="+record.id  );
 	//reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?sectId="+ record.id );
 	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbFactory");
 }
*/
 /*function  frmAbnormalitycmbAbnmCellid_onSelect(record)
 {
	jQuery("#cmbAbnmEquipmentid").combobox('clear');
 	jQuery("#cmbCostcenter").combobox('clear');
 	reloadCombo("frmAbnormality","cmbCostcenter","costCenter.commonFilter?cellId="+record.id  );
 	//reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?cellId="+ record.id );
 	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbAbnmSectionid","cmbFactory");
 }
*/
/* function  frmAbnormalitycmbCostcenter_onSelect(record)
 {
	// alert("record" +record.id);
	//reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?cellId="+ record.id );
 }
 */
 function  frmAbnormalitycmbAbnmEquipmentid_onSelect(record){
	//alert(record.id);
 	//fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbAbnmCellid","cmbAbnmSectionid","cmbFactory");
 	//alert(isAutoSelect);
 	/* 	if(isAutoSelect){
 			isAutoSelect=false;
 			return;
 		}else{
 			isAutoSelect=true;
 		}
 	isFuntLocHierarchy = false; */
 	//isAutoSelect=true;
 	loadFunctionalLocation("abnmfunLocation","functionalLoc.abnForm","abnmfunLocationValues","frmAbnormality","&machId="+record.id);
 	checkRepAbn();
	var para="";
	//jQuery("#cmbAbnmAssemblyid").combobox('clear');
	/*  setTimeout(function() {
	
			if(jQuery('#relatedToCMB').val() == "MLD")
				para="&relatedto=MLD";
			else
				para="";
	
 	reloadCombo("frmAbnormality","cmbAbnmAssemblyid","assembly.commonFilter?machineId="+ record.id +""+para );
 	},1250);*/
 	//jQuery("#cmbabnmMould").combobox('clear');
 	
 	//reloadCombo("frmAbnormality","cmbabnmMould","mould.commonFilter?q=2&mchId="+record.id );
 }

 function frmAbnormalitycmbAbnmTradeid_onClear()
 {
	 //comboFillWithCell("cmbAbnmResponsibleid");
	 //reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");
	 var tag = jQuery("#cmbAbnmTagclassid").combobox('getText');
	 //alert(tag);
	 comboFillWithTrade(tag,null);
 }
function frmAbnormalitycmbAbnmEquipmentid_onClear()
{
	loadFunctionalLocation("abnmfunLocation","functionalLoc.abnForm","abnmfunLocationValues","frmAbnormality","&cellId="+jQuery("#frmAbnormality input[id='cell']").val());
	//fillComboBox("frmAbnormality","cmbAbnmAssemblyid","assembly.commonFilter?relatedto=MLD&machId="+jQuery('#cmbAbnmEquipmentid').combobox('getValue'));
	jQuery("#frmAbnormality input[id='machine']").val('');
// 	 setTimeout(function() {jQuery('#cmbAbnmEquipmentid').combobox('clear');},1250);
	 //reloadCombo("frmAbnormality","cmbabnmMould","mould.commonFilter" );	 
;}

 function  frmAbnormalitycmbAbnmAssemblyid_onSelect(record){	
	 var eqpVal=jQuery("#cmbAbnmEquipmentid").combobox('getValue');

	 if(jQuery('#relatedToCMB').val()=="MCH")
	 {
		 if(eqpVal==null || eqpVal=='')
	 	{	 
			 alert("Select Equipment");		
		 	//jQuery('#cmbAbnmEquipmentid').focus();		
			  jQuery("#cmbAbnmAssemblyid").combobox('clear');
		      jQuery("#cmbAbnmEquipmentid").focus();
	 	}
	 	else
			 return true;
	 }
  }

 function  frmAbnormalitycmbabnmMould_onSelect(record){

	 //reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?mouldId="+ record.id ); 
 }
//-------------------Click Others--------------------------------//
 jQuery('#chkAbnmOthers').click(function() {
	 var sat = jQuery('#chkAbnmOthers:checked').val();
	 jQuery("#cmbAbnmDetectedby").combobox('setValue',"");
	 //setComboValueSilent("cmbAbnmDetectedby", "");
	 othersClickAction(sat);
	 //reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");
	});
 
 jQuery('#chkAbnmRepOtheres').click(function() {
	 	
	 var tag = jQuery("#cmbAbnmTagclassid").combobox('getText');
	 comboFillWithTrade(tag,null);
	 //othersClickAction(sat);
	 //reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");
	});
function othersClickAction(sat)
{
	//reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");
	 var cellId = jQuery("#frmAbnormality input[id='cell']").val();
	 var flid = jQuery("#frmAbnormality input[id='flid']").val();
	 var keyid = getFieldValue("cmbAbnmDetectedby");
	 //alert(keyid);
	 //alert(sat);
	 if(cellId == null || cellId == ''){
		 cellId = "123";
	 }
	 if(sat=="Y")
	 {
		
		//setTimeout(function() {setFieldValue("cmbAbnmDetectedby",keyid);},2250);
		  reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&cellId="+cellId+"&others=Y");
		 
		  //reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&combokey="+keyid+"&cellId="+cellId+"&others=Y");
		// fillComboBox("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&cellId="+cellId+"&others=Y","",true);
	 }
	 else
	 {  
		 reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&cellId="+cellId);
		 //comboFillWithCell("cmbAbnmDetectedby");
		 //reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");
		 //reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&cellId="+cellId);
	 }	
	 
}
//--------------------Target Date Manitory or Not-----------------------------------//
 jQuery('#tar').click(function() {
	 var sat = jQuery('#tar:checked').val();

	 if(sat=="on"){
		 jQuery("#chk").show();
		 jQuery("#unchk").hide();
		 jQuery("#dteAbnmTargetdate").datebox("enable");
		 }
	 else
	 { jQuery("#unchk").show();	
	 	jQuery("#chk").hide(); 
		 jQuery("#dteAbnmTargetdate").datebox("disable");
	 }
	
	});

 function frmAbnormalitycmbAbnmKeyid_onLoadSuccess(){}
 function frmAbnormalitycmbFactory_onLoadSuccess(){
	 fillComboBox("frmAbnormality","cmbAbnmSectionid","sectionCombo.commonFilter");	
	 }
 function frmAbnormalitycmbAbnmSectionid_onLoadSuccess(){
	 fillComboBox("frmAbnormality","cmbAbnmCellid","cellCombo.commonFilter");
	}
 function frmAbnormalitycmbAbnmCellid_onLoadSuccess(){
	 jQuery("#cmbCostcenter").combobox('clear');
		fillComboBox("frmAbnormality","cmbCostcenter","costCenter.commonFilter" );	
	 }
 function frmAbnormalitycmbAbnmEquipmentid_onLoadSuccess(){
	 setComboDefaultValue("frmAbnormality","cmbCostcenter");
	 //fillComboBox("frmAbnormality","cmbAbnmDetectedby","Combo_DetectedBy.abnForm");	
	 //loadTeamCombo();
 }
 function frmAbnormalitycmbCostcenter_onLoadSuccess(){
	if(jQuery('#relatedToCMB').val() == "MLD")
	{
		fillComboBox("frmAbnormality","cmbAbnmAssemblyid","assembly.commonFilter?relatedto=MLD&machId="+jQuery('#cmbAbnmEquipmentid').combobox('getValue'));
	}
    else
	{
    	//jQuery("#cmbAbnmAssemblyid").combobox('clear');
		fillComboBox("frmAbnormality","cmbAbnmAssemblyid","assembly.commonFilter?machineId="+ jQuery('#cmbAbnmEquipmentid').combobox('getValue'));
	}
 }

 function frmAbnormalitycmbAbnmAssemblyid_onLoadSuccess(){

		 //fillComboBox("frmAbnormality","cmbAbnmTypeid","Combo_Type.abnForm");
	// reloadCombo("frmAbnormality","cmbAbnmAssemblyid","assembly.commonFilter?machineId="+ record.id );
 }

 function frmAbnormalitycmbAbnmTagclassid_onLoadSuccess(){
	 	var disableTag = jQuery('#hdndisableTag').val();	
	   if(disableTag != null && disableTag != ' ' && disableTag != '')
	   {
		  readOnlyFields('cmbAbnmTagclassid');		
	   }
		
		
	 }
 function frmAbnormalitycmbAbnmDetectedby_onLoadSuccess(){
	 //fillComboBox("frmAbnormality","cmbAbnmTradeid","Combo_Trade.abnForm");
	 //alert(2);
 }
 function frmAbnormalitycmbAbnmDetectedby_onSelect(record){
 	//loadTeamCombo();
 }
 
 function frmAbnormalitycmbAbnmTradeid_onLoadSuccess(){
	 reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");
	 reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter");
	 //fillComboBox("frmAbnormality","cmbAbnmCategoryid","Combo_Category.abnForm?frmType="+jQuery('#hdnfrmType').val());
 }
 function frmAbnormalitycmbAbnmCategoryid_onLoadSuccess(){
		//fillComboBox("frmAbnormality","cmbAbnmImpactid","Combo_Impact.abnForm?frmType="+jQuery('#hdnfrmType').val());
	// fillComboBox("frmAbnormality","cmbAbnmImpactid","Combo_Impact.abnForm");
	}
 function frmAbnormalitycmbAbnmImpactid_onLoadSuccess(){
	
 }
 function frmAbnormalitycmbAbnmTypeid_onLoadSuccess(){

	
	// fillComboBox("frmAbnormality","cmbAbnmSubtype","Combo_SubType.abnForm");
	 }
 function frmAbnormalitycmbAbnmSubtype_onLoadSuccess(){
	 
	 }
 function frmAbnormalitycmbAbnmStatus_onLoadSuccess(){
	 
			 	
	 }
 function frmAbnormalitycmbAbnmIssueNo_onLoadSuccess(){

	 }
 function frmAbnormalitycmbAbnmCompletedby_onLoadSuccess(){
	 
	 //var cellId = jQuery("#cell").val();
	 //fillComboBox("frmAbnormality","cmbCostcenter","costCenter.commonFilter?cellId="+cellId  );
}
 function frmAbnormalitycmbAbnmCompletedby_onSelect(record){
	 	//loadTeamCombo();
 }
function frmAbnormality_FuntLocHierarchy_SuccessCallBack(keyIds)
{ 
	//if(isFuntLocHierarchy) return;
	
	var tag = jQuery("#cmbAbnmTagclassid").combobox('getText');
  
	var modeType = jQuery("#hdnmodeType").val();
	//setFieldValue('cmbAbnmEquipmentid',keyIds.machId);
	//jQuery("#cmbAbnmEquipmentid").combobox("disable");
	//var cellId = jQuery("#cell").val();
	var abnEqpid = jQuery('#cmbAbnmEquipmentid').combobox('getValue');
	//alert("abnEqpid:"+keyIds.machId);
	//if (abnEqpid =='' || abnEqpid=='' || abnEqpid ==null) 
	//{ 
		
		if( keyIds.machId != undefined && keyIds.machId.trim().length>0){
			
			reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?combokey="+ keyIds.machId+"&cellId="+ keyIds.cellId );	
		}
		else if( keyIds.cellId != undefined && keyIds.cellId.trim().length>0){
			reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?cellId="+ keyIds.cellId);
		}
	//}
	//else 
		//setFieldValue('cmbAbnmEquipmentid',keyIds.machId);
	
		//isFuntLocHierarchy = true;
		//isAutoSelect = true
	
	
	 
var others = jQuery("#hdnOthers").val();	
var sat = jQuery('#chkAbnmOthers:checked').val();

	if(sat == 'Y'){
		//othersClickAction(others);
		jQuery("#chkAbnmOthers").prop("checked","checked");
	}
	else{
		var cellid = keyIds.cellId;
	if(keyIds.cellId != null && keyIds.cellId != '' )
		cellid = keyIds.cellId;
	else
		cellid = "123";
		
	reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?cellId="+ cellid);
		//reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?cellId=123");
	}
	 
	
	 /*var trade = jQuery("#cmbAbnmTradeid").val();
	 
	 if(trade == '' || trade== ' '){
		 
		 reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?cellId="+ keyIds.cellId);
	 }
	 else{
		 reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?cellId="+ keyIds.cellId+"&trade="+trade);
	 }*/
	 
	 
	 
	var enableComplete = jQuery('#hdnenableComplete').val();	
	var mode = jQuery('#mode').val();	
	var tagClass = jQuery("#cmbAbnmTagclassid").combobox('getText');
	
	
	//alert(tagClass);
	if(enableComplete != null && enableComplete != ' ' && enableComplete != '')
	{			
		if(tagClass.trim() == 'RED-RED' || tagClass.trim()=='TAG000002' && mode.trim() == 'modify')
		{			
			readOnlyFields('cmbAbnmStatus');
		}
		else if(mode.trim() != 'modify'){
			readOnlyFields('cmbAbnmStatus');
		}
	}

	//reloadMachine("frmAbnormality",'cmbAbnmEquipmentid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	//jQuery("#cmbCostcenter").combobox('clear');
	//reloadCombo("frmAbnormality","cmbCostcenter","costCenter.commonFilter?cellId="+keyIds.cellId  );
	//reloadCombo("frmAbnormality","cmbAbndCircleid","circle.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  +"&sectId="+keyIds.sectId  );
	if( jQuery('#hdnfrmType').val()=="SHE")
	{
		
		jQuery("#btnabnActionpln").attr("disabled","disabled");
		jQuery("#btnAbnYYLink").attr("disabled","disabled");
		jQuery("#btnRepAbn").attr("disabled","disabled");
		jQuery("#btnFilManage").attr("disabled","disabled");
		
		readOnlyFields("cmbAbnmTradeid");
		readOnlyFields("txtAbnmCountermeasure");
		readOnlyFields("chkAbnmRepOtheres");
		readOnlyFields("chkAbnmAccecpatncerequired");
		jQuery("#cmbAbndCircleid").combobox('clear');
		jQuery('#frmAbnormalityFuntKeyIds').append('<div id="divhide1" style="position:absolute;top:0;left:0;width:90%;z-index:2;opacity:0.4;height:30%;"> </div>');
	//	reloadCombo("frmAbnormality","cmbAbndCircleid","circle.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  +"&sectId="+keyIds.sectId  );
	}
	

		
	
	 if(modeType != "modify"){
		 	//fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?~cellId="+cellId);
		 	//comboFillWithCell("cmbAbnmResponsibleid");
		 	var cellid = keyIds.cellId;
			if(keyIds.cellId != null && keyIds.cellId != '' )
				cellid = keyIds.cellId;
			else
				cellid = "123";
			var respon = jQuery("#hdnRespond").val();
			var compid=getFieldValue("cmbAbnmCompletedby");
			//alert(compid+" compid "+respon);
   			setTimeout(function() {reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?&cellId="+cellid+"&combokey="+respon);
			reloadCombo("frmAbnormality","cmbAbnmCompletedby","employee.commonFilter?&cellId="+cellid+"&combokey="+compid);
   			},1500);
		 }
		 else{
	//jQuery("#cmbAbnmTagclassid").attr("readonly",true);
			readOnlyFields('cmbAbnmTagclassid');
			 
			 //fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");
			 //setTimeout(function() {fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter");},1250);
		 }
	 
		  if(modeType != "complete"){
			  //comboFillWithCell("cmbAbnmResponsibleid");  
			 
			 //reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&cellId="+keyIds.cellId);
	    	 //fillComboBox("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter?&cellId="+cellId);
	     }
	     else{
	
	    	 //fillComboBox("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");
	    	// reloadCombo("frmAbnormality","cmbAbnmDetectedby","employee.commonFilter");
	    	 var id = jQuery("#cmbAbnmDetectedby").combobox("getValue");
	    	 //setFieldValue('cmbAbnmDetectedby',id);
	    	 //jQuery("#dispFunctionalLoc").attr('readonly', true);
// 	    	 jQuery('#abnmfunLocation').append('<div style="position:absolute;top:0;left:0;width:100%;z-index:2;opacity:0.4;height:20%;"> </div>');
// 	    	 jQuery('#frmAbnormalityabnmfunLocationValues').append('<div style="position:absolute;top:0;left:0;width:100%;z-index:2;opacity:0.9;height:20%;"> </div>');
	    	 jQuery('#frmAbnormalityFuntKeyIds').append('<div id="divhide1" style="position:absolute;top:0;left:0;width:60%;z-index:2;opacity:0.4;height:10%;"> </div>');
	    	 //jQuery("#frmAbnormalityFuntKeyIds").attr('readonly',true);
	    	 //jQuery("#frmAbnormality div[id=frmAbnormalityFuntKeyIds]:input")attr('readonly', true);
	    	 //jQuery("#frmAbnormality div[id=dispFunctionalLoccap]:input").attr('readonly', true);
	     }
		  	
// var abnStatus = jQuery("#hdnabnStatus").val();
	
 var abnStatus = jQuery("#cmbAbnmStatus").combobox('getValue');
 
if(abnStatus == 'C'){
	jQuery('#txtAbnmCountermeasure').attr("readonly",true);
	var mode1 = jQuery("#mode").val();
	if(mode=="view")	
		jQuery(".completedtls").show();
}

var mode1 = jQuery("#mode").val();
if(mode1=="view")
{

readOnlyFields('cmbAbnmTradeid');
setTimeout(function() {readOnlyFields('txtAbnmCountermeasure');},1250);
setTimeout(function() {readOnlyFields('chkAbnmAccecpatncerequired');},1250);
readOnlyFields('chkAbnmRepOtheres');
//disableForm("frmAbnormality");
jQuery('#frmAbnormalityFuntKeyIds').append('<div id="divhide1" style="position:absolute;top:0;left:0;width:60%;z-index:2;opacity:0.4;height:10%;"> </div>');
}
	 
		  //comboFillWithTrade('cmbAbnmResponsibleid');
	/*var trade = jQuery("#cmbAbnmTradeid").combobox("getValue");
	if(trade != null || trade.trim() != "")
		setTimeout(function() {reloadCombo("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?trade="+trade);},1250);
	*/

	
	var locnId = jQuery("#frmAbnormality input[id='location']").val();
 	
 	//if (( locnId!=null && locnId=='LCN0000002')) {
 	//	jQuery("#lblResp").addClass('mandatory-lbl');
   // }

 	comboFillWithTrade(tag,null);
 	
}
 function frmAbnormality_successsCallback(result)
 {
	 var mode = jQuery("#hdnmodeType").val();
	 //alert("loadAbnModify"+mode);
	 //alert(mode);
	 //alert(mode.length);
	 //alert(result.successData.msg+" "+result.successData.keyId);
	 //jQuery("#hdnabnkeyID").val(keyId);
	 var detectedby=result.successData.detectedby;
	 var flid=result.successData.flid;
	 var cellId = jQuery("#frmAbnormality input[id='cellid']").val();
	 //for Visual Control Checklist
	 var refDoctype = jQuery('#hdnRefDoctype').val();
	 if(refDoctype =='VCC')
		 navigateToPrevForm();
	 
	setFieldValue('cmbAbnmFlid',flid);
	setFieldValue('cmbAbnmDetectedby',detectedby);
	var backTo = result.BACKTO;
			 
	 var openactnpln = result.successData.openactnpln;
	 var openfilemgr = result.successData.openfilemgr;
	 //alert("openactnpln:openactnpln"+openactnpln);
	 if(openactnpln==true )
	 {//alert("Inside success");
		 
		 var keyid = result.successData.keyId;
		 var maintask = getFieldValue("txtAbnmDescription");
		 setFieldValue('cmbAbnmKeyid',keyid);
		 var abnDetectDate = getFieldValue("dteAbnmDetectiondate");
		 openActionPlan("abnActionPlan",keyid,"abn",flid,maintask,keyid,abnDetectDate,"create");
		 
		 //fileManagerPopUp(keyid,"abn","","",""); 
		 
		 
	 }
	 else if (openfilemgr==true ) {
		 var keyid = result.successData.keyId;
		 setFieldValue('cmbAbnmKeyid',keyid);
		 fileManagerPopUp(keyid,"ABN","","","", "");

	} 
	
/* 	 jQuery("#btnFilManage").click(function(){
		    
		    var documentNo =result.successData.keyId;
			
		    if(jQuery('#cmbAbnmStatus').combobox('getValue')=="C")
			 	var fmgMode = "view";
			alert('1==='+fmgMode);
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"ABN","","","");
			}
	 });
 */	 
	 
 
 else if(mode != null && mode != "undefined" && mode != '' && mode != ' ') 
	 {
		  if(mode == "YY")	
	 	  {
			 var persistentData = result.persistentData;
			 var forwardData = result.forwardData;	
			 //var refDocId =  result.successData.keyId;
			 //var refDocId =  result.successData.keyId;
			 	var flid = jQuery("#frmAbnormality input[id='flid']").val();
 				var refDocDate = jQuery('#dteAbnmWoendtime').datebox("getValue");
 				var refDocId = jQuery('#cmbAbnmKeyid').val();
 				var problem = jQuery('#txtAbnmDescription').val();
 		 		var attendedBy = getFieldValue("cmbAbnmCompletedby");
 		 		var formMode = jQuery("#hdnFormMode").val();
				openWhyWhy("",false,refDocId,"ABN",flid, refDocDate, problem, formMode, null, null, attendedBy);

			 return false;
			 //navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData );
	 	  }
		  else if(mode == "WO")	
		  {
			  var persistentData = result.persistentData;
			  var forwardData = result.forwardData;											
			  navigateToNextForm('workReq_input.work','Maintenance Service Request',forwardData,persistentData );
		  }
	 }
	else
	 {
		
		//fillWithCurrentDate("dteAbnmTargetdate");
	/*tagClassColor();
		fillWithCurrentDate("dteAbnmWoendtime");
			fillWithCurrentDate("dteAbnmDetectiondate");
			fillWithCurrentDate("spnAbnmDetectedbytime");
			fillWithCurrentDate("dteAbnmTargetdate");
			//jQuery("#cmbabnmRelatedto").combobox('setValue','MCH'); now cmmented
			jQuery('#cmbAbnmStatus').combobox('setValue',"P");
			jQuery('#lblCompDate').css('display','none');
			jQuery('#divCompDate').css('display','none');	
			jQuery('#startandendtimediv').css('display','none');	
			jQuery(".completedtls").hide();
			jQuery('#cmbabnmMould').combobox('disable');
			jQuery('#cmbAbnmDetectedby').combobox('setValue',result.detectedby);  
			//loadFunctionalLocation("abnmfunLocation","functionalLoc.abnForm","abnmfunLocationValues","frmAbnormality","");
			jQuery("#hdnfrmType").val(result.successData.formType);
	
			
			fillWithCurrentDate("dteAbnmWoendtime");
			fillWithCurrentDate("dteAbnmDetectiondate");
			fillWithCurrentDate("spnAbnmDetectedbytime");
			fillWithCurrentDate("dteAbnmTargetdate");
			//jQuery("#cmbabnmRelatedto").combobox('setValue','MCH'); now cmmented
			jQuery('#cmbAbnmStatus').combobox('setValue',"P");
			jQuery('#lblCompDate').css('display','none');
			jQuery('#divCompDate').css('display','none');	
			jQuery('#startandendtimediv').css('display','none');	
			jQuery(".completedtls").hide();
			jQuery('#cmbabnmMould').combobox('disable');
			loadFunctionalLocation("abnmfunLocation","functionalLoc.abnForm","abnmfunLocationValues","frmAbnormality","");
			jQuery("#hdnfrmType").val(result.successData.formType);
		*/	 var backTo = result.BACKTO;
			if(result.successData.formType=="SHE")
			{
				fillComboBox("frmAbnormality","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=SHE");
				setTimeout(function() { setComboDefaultValue("frmAbnormality","cmbAbnmTagclassid");},1250);
				fillComboBox("frmAbnormality","cmbAbnmTypeid","Combo_Type.abnForm?abtmType=UP");
				setTimeout(function() { setComboDefaultValue("frmAbnormality","cmbAbnmTypeid");},1250);
			}
			 
			if(result.successData.mode=="Modify")
			{
				//alert(12344);
				//navigateToPrevForm("AbnModify_input.abnForm");
				AbnormalityRowUpdate(result.successData.rowId,result.successData.keyId,result.updatedRow);
				 closePopUpDialoge("loadAbnModify");
			}
		
			else if(result.successData.mode=="Removal")
			{
				if(backTo != null && backTo != '' && backTo != ' ')
			 		popFormNavigation();
				navigateToPrevForm();
			}
			else{
				refreshForm();
			}
			if(result.successData.prevForm=="Y")
			{
				if(backTo != null && backTo != '' && backTo != ' ')
				 	popFormNavigation();
				navigateToPrevForm();
			}

			
		 }
 	//enableFields("cmbAbnmStatus");
 	//comboFillWithCell("cmbAbnmResponsibleid");
	 //fillComboBox("frmAbnormality","cmbAbnmResponsibleid","employee.commonFilter?~cellId="+cellId);
	
 }

 function frmAbnormality_deleteSuccessCallback(result)
 {
	if(result.successData.mode=="Modify")
	{
		navigateToPrevForm("AbnModify_input.abnForm");
	}
	
 }

 jQuery( "#btnCancel" ).click(function() {		
		closeRemarksDialog('abnHistory');
	});

 jQuery("#btnok" ).click(function() {
	 	closeRemarksDialog('abnHistory');
		var url = jQuery('#hiddenUrl').val();
		saveForm("frmAbnormality",url,"");
		jQuery("#hdnCheckSave").val("1");
	});
 
 function openRemarksDialog(){
		jQuery('#mstfrm_div').addClass('popup-mask');	
		jQuery('#mstfrm_div').show();
		jQuery('#abnHistory').addClass('custom-popup');		
		jQuery('#abnHistory').show();		  
		jQuery('#abnHistory').css('border','1px solid #F1F5FB');
		jQuery('#abnHistory').css('z-index',100); 
	}

	function closeRemarksDialog(dlgId)
	{
		 jQuery( '#'+dlgId ).hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#'+dlgId ).removeClass('custom-popup');
	}


 function frmAbnormality_exceptionCallback() { }

 function frmAbnormality_beforeSubmit()
 {
	 //var old = jQuery("#hdnChkTagClassId").val();
	 var news = jQuery("#dteAbnmTargetdate").datebox('getValue');
	 var old = jQuery("#hdnTargetDate").val();
	 var rep = jQuery('#chkAbnmRepOtheres:checked').val();
	 var responId = getFieldValue("cmbAbnmResponsibleid");
	 var locnId = jQuery("#frmAbnormality input[id='location']").val();

	 if(responId.trim().length==0 || responId.trim().length=="0"){
		 popupCommonErrorMsg(" Select Responsiblity ");
		 return false;
	 }
	 /* if (( locnId!=null && locnId=='LCN0000002')){
		 if(responId.trim().length==0 || responId.trim().length=="0"){
			 popupCommonErrorMsg(" Select Responsiblity ");
			 return false;
		 }
	 } */
	  var abncategory=getFieldValue("cmbAbnmCategoryid");
	  var locationId=jQuery("#hdnlocationid").val();
	  if(locationId=='LCN0000005' && abncategory.trim().length==0){
		  popupCommonErrorMsg("Select Abnormality Category");
			 return false;
	  }
	 	 
	 if(rep=="Y")
	 {
	 	if(responId == null  || responId == '' )
 		{
	 		showValidationErrorMsg("cmbAbnmResponsibleid","Select Responsblity");
 			return false;
 		}
	 	else
	 		clearValidationErrorMsg("cmbAbnmResponsibleid");
	 		
	 }
	 var discription = jQuery('#txtAbnmDescription').val();
	 var remarks = jQuery('#txtAbnmRemarks').val();
	 var counterMeasure = jQuery('#txtAbnmCountermeasure').val();
	// alert(txtValue);
	// alert(hasSpecialCharacters(txtValue));
	 if(hasSpecialCharacters(discription)) {
		 popupCommonErrorMsg('Special Characters Are Not Allowed In Discription.');
		 return false;
	 }
	 if(hasSpecialCharacters(remarks)) {
		 popupCommonErrorMsg('Special Characters Are Not Allowed In Remarks .');
		 return false;
	 }
	 if(hasSpecialCharacters(counterMeasure)) {
		 popupCommonErrorMsg('Special Characters Are Not Allowed In CounterMeasure.');
		 return false;
	 }
	 //var news = jQuery("#cmbAbnmTagclassid").combobox('getValue');
	 var checkVal = jQuery("#hdnCheckSave").val();
	 if(checkVal == "0"){
		 if(old.trim()!="" && news.trim() != ""){
		 if(old != news)
		{		 
			// openRemarksDialog();
			 //return false;
		}
		 }
	 }
	 else
		 return true;
	if( jQuery('#hdnfrmType').val()=="SHE")
	 {
		if(jQuery("#frmAbnormality input[id='factory']").val().trim()=="")
		{
			jQuery('#txtFct').html("Select Factory");
			jQuery('#txtFct').css("display","block");
		}
		else
		{
			jQuery('#txtFct').html(" ");
		}
	 }		
	 
	 var targetDate = jQuery('#dteAbnmTargetdate').datebox('getValue') ;
	 var sat = jQuery('#tar:checked').val();

	 if(sat=="on" && (targetDate =='' || targetDate==' ')) {
		 popupCommonErrorMsg('Select Target Date');
		 return false;
	 }

 }
 
 /* function hasSpecialCharacters(str) {
		//alert(1);
	  const regex = /[^a-zA-Z0-9 _.\-()/%,]/; 
	  return regex.test(str);
	}  */
 
 /* function frmAbnormality_successsCallback(result)
 {
	 //alert("after SuccessCallback");
	 //alert(result);
	 var rowId = jQuery('#hdnrowid').val();
	 AbnormalityRowUpdate(result.successData.rowId,result.successData.keyId,result.updatedRow);
	 closePopUpDialoge("loadAbnModify");
	 
 } */


 function frmAbnormality_beforeDelete()
 {
	 	var delActivity = jQuery('#hdndelABNMode').val();				
		if(delActivity == 'Y')
		{
			alert('Activity Cannot be deleted');
			return false;
		}
	 var msr = jQuery('#txtAbnmRefdocid').val();
	 if(msr != null && msr != '' && msr != ' ')
	 {
		 var msrdelMsg = "MSR Exists for This Abnormality ("+jQuery('#cmbAbnmKeyid').combobox('getValue')+").Do You Want To Proceed?";
		 if(confirm(msrdelMsg) == false)
		 {
					return false;
		 }
	 }
	 else
	 {
		var delMsg = "Do You Want To Reject This Abnormality ("+jQuery('#cmbAbnmKeyid').combobox('getValue')+") ?";
		if(confirm(delMsg) == false)
		{
				return false;
		}
	 }
 }
 function abnTypeOnsuccessCallback(result)
 {
	 
	 
	if(result.abnType=="H")
	{
		jQuery('#cmbAbnmSubtype').combobox('enable');
		jQuery('#lblVal').html('HTA Type');
		jQuery('#lblAbnDesc').html('Abnormality Description(Difficulty)');
		jQuery('#lblAbnHappend').html('Why it is difficult (Why Abnormality Happened?)');
		isNotSOC();
		isAbnFormType();
		enableFields('cmbAbnmAfeemid');
		
	}
	else if(result.abnType=="S")
	{	
		jQuery('#cmbAbnmSubtype').combobox('enable');
		jQuery('#lblVal').html('SOC Type');
		jQuery('#txtAbnmContaminant').attr({'readonly':false});
		jQuery('#txtAbnmMode').attr({'readonly':false});
		jQuery('.socType').addClass('mandatory-lbl');
		jQuery('#lblAbnDesc').html('Abnormality Description(Contamination)');
		jQuery('#lblAbnHappend').html('Source');
		enableFields('cmbAbnmAfeemid');
		isAbnFormType();
	}
	else if(result.abnType=="U")
	{
		
		isHseFormType();
		enableHseForm();
		
	}
	else
	{	
		jQuery('#lblVal').html('Sub Type');
		//jQuery('#cmbAbnmSubtype').combobox('disable');
		jQuery('#lblAbnHappend').html('Why Abnormality Happened?');
		jQuery('#lblAbnDesc').html('Abnormality Description');
		isNotSOC();
		isAbnFormType();
		readOnlyFields("cmbAbnmAfeemid");
	}
	var prvData = jQuery("#hdnAbnType").val();
	var abntype = "N";
	if(result.abnType != "U")
	{
		abntype = "N";
		if(prvData != abntype)
			reloadCombo("frmAbnormality","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType="+jQuery('#hdnfrmType').val());
		jQuery("#hdnAbnType").val(abntype);
	}
	else 
	{
		abntype = "U";
		if(prvData != abntype)
			reloadCombo("frmAbnormality","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");
		jQuery("#hdnAbnType").val(abntype);
	}
 }

 function isNotSOC()
 {
	jQuery('#txtAbnmContaminant').attr({'readonly':true});
	jQuery('#txtAbnmMode').attr({'readonly':true});
	jQuery('#txtAbnmContaminant').val('');
	jQuery('#txtAbnmMode').val('');
	jQuery('.socType').removeClass('mandatory-lbl');
 }

 function isAbnFormType()
 {
	 var type = jQuery('#cmbAbnmTypeid').combobox('getText');
	
	if(type != null && type != '' && type != ' ')
	{
		if(type.indexOf('UNSAFE')>=0)
			jQuery('#cboAbndImprovementteam').attr({"readonly":false});
		else
			jQuery('#cboAbndImprovementteam').attr({"readonly":true});
	}
	else
		jQuery('#cboAbndImprovementteam').attr({"readonly":true});
		jQuery('#txtAbndHirarefno').attr({"readonly":true});
		jQuery('#txtAbndImmediateaction').attr({"readonly":true});
		jQuery('#txtAbndEffectleadsto').attr({"readonly":true});
		jQuery('#txtAbndAvoidrecurrence').attr({"readonly":true});
		//jQuery('#cmbAbnmResponsibleid').combobox("disable");
		jQuery('#txtAbndPokayokeid').attr({"readonly":true});
		jQuery('#chkisPokayoke').prop({"readonly":true});
		jQuery('#lblabnType').html("Abnormality Type");
		jQuery('#lblImprvType').removeClass('mandatory-lbl');
 }

 function isHseFormType(){
 	    enableFields('cmbAbnmAfeemid');
	    jQuery("#cmbAbnmAfeemid").attr({"readonly":false});
		jQuery('#txtAbnmMode').attr({"readonly":true});
		jQuery('#txtAbnmContaminant').attr({"readonly":true});
		jQuery('#lblabnType').html("Main Type");
		jQuery('#lblVal').html("Sub Type");
 }

 function enableHseForm(){
		jQuery('#cboAbndImprovementteam').attr({"readonly":false});
		jQuery('#txtAbndHirarefno').attr({"readonly":false});
		jQuery('#txtAbndImmediateaction').attr({"readonly":false});
		jQuery('#txtAbndEffectleadsto').attr({"readonly":false});
		jQuery('#txtAbndAvoidrecurrence').attr({"readonly":false});
		jQuery('#cmbAbnmResponsibleid').combobox("enable");
		jQuery('#txtAbndPokayokeid').attr({"readonly":false});
		jQuery('#chkisPokayoke').prop({"readonly":false});
 }
 
 jQuery('#btnAssembly').click(function(){
	 if(jQuery('#cmbAbnmEquipmentid').combobox('getValue').trim()=="")
		 alert('Select Equipment');
	 else
		 navigateToNextForm("assembly_input.asb");
 });

 jQuery("#btnCategory").click(function(){
	if( jQuery('#hdnfrmType').val()=="SHE")
	 	openMasterForm('loadmst_grid.gnms?q=2&menuCaption=AbnormalityCategory&menuName=MNUMASSFTABNCATEGORY&isMMC=Y&loadFormArg=MNUMASSFTABNCATEGORY',frmMode.create,'frmAbnormality','','mstFrm');
	else if( jQuery('#hdnfrmType').val()=="JH")
	{
			openMasterForm('loadmst_grid.gnms?q=2&menuCaption=AbnormalityCategory&menuName=MNUMASABNCATEGORY&isMMC=Y&loadFormArg=MNUMASABNCATEGORY',frmMode.create,'frmAbnormality','','mstFrm');
	}
	});

  function frmAbnormality_beforeCloseCurrentForm()
  {
	  reloadCombo("frmAbnormality","cmbAbnmCategoryid","Combo_Category.abnForm?frmType="+jQuery('#hdnfrmType').val());
  }
 
 function tagShow(para,tagClass)
 {
	
	 if(para==true)
	{
		var setTagclass=jQuery('#cmbAbnmTagclassid').val();
		jQuery('#tagclass').val(tagClass);
		jQuery('#status').show();
		jQuery('#tagclass').show();
	//	jQuery('#btnFileMngr').hide();
	//	jQuery('#cmbAbnmIssueno').hide();
		
	}
	 else
	{
		jQuery('#status').hide();
	    jQuery('#tagclass').hide();
	    //jQuery('#cmbAbnmIssueno').hide();
	    jQuery('#abnFind').css({"padding-bottom": "31px"});
	    jQuery('#abnCauses').css({"padding-bottom": "31px"});
	//	jQuery('#btnFileMngr').hide();
	} 
		
 }
	 

 function completedShow()
 {
    var showVal=jQuery('#hdnDisForm').val();
  
	if(showVal=='true')
	{ var tagClass = jQuery("#hdnTagClass").val();
		jQuery("#comp").show();								
		jQuery(".completedtls").show();
		jQuery('#pend').hide();
		if(tagClass=="RED")
			jQuery('.incompletion').addClass('mandatory-lbl');
	}
	else
	{
		jQuery("#comp").hide();		
		jQuery('#pend').show();						
		jQuery(".completedtls").hide();
		jQuery('.incompletion').removeClass('mandatory-lbl');
		
	}	

	var enableComplete = jQuery('#hdnenableComplete').val();
	if(enableComplete != null && enableComplete != ' ' && enableComplete != '')
	{
		var status = jQuery('#cmbAbnmStatus').combobox('getValue');
		if(status != 'P')
		{
			 
			jQuery("#comp").show();								
			jQuery(".completedtls").show();
		
			jQuery('#pend').hide();
			jQuery('.incompletion').addClass('mandatory-lbl');		
			var tagClass = jQuery("#hdnTagClass").val();
			 var showCompletedDate = jQuery('#hdnShowCompDate').val();	
			 
			if(tagClass != "RED")
				jQuery('#startandendtimediv').css('display','none');
			else
			{
				if(showCompletedDate != null && showCompletedDate != '' && showCompletedDate != ' ' && showCompletedDate)
				{
					if(showCompletedDate == 'Y')
						jQuery('#startandendtimediv').css('display','none');
					else
					{
						jQuery('#lblCompDate').css('display','none');
						jQuery('#divCompDate').css('display','none');
					}
				}
				else
				{
					jQuery('#lblCompDate').css('display','none');
					jQuery('#divCompDate').css('display','none');
				}
			}	
		}
	}
	
		
	
 }	
 function setDatenTimeFlag(dateId,timeId)
 {
 	var dateFlag = jQuery('#'+dateId).datebox('getValue');
 	var allDate = jQuery('#hdnAllottedDate').val();
 	if(dateFlag != null && dateFlag != '' && dateFlag != ' ')
 	{ 
 		
 		if(dateFlag == '01-Jan-1801 00:00:00')
 	 	{
 			
 			jQuery('#'+dateId).datebox('clear');
 			if(dateId=='dteAbnmWostarttime')
 	 	 	{
 	 	 	 	if(allDate != null && allDate != ' ' && allDate != '')
 	 	 	 	{
 	 	 	 	 jQuery('#'+timeId).spinner('setValue',allDate.substring(dateFlag.indexOf(' ')+1,dateFlag.indexOf(' ')+6));
 	 			 jQuery('#'+dateId).datebox('setValue',allDate.substring(0,dateFlag.indexOf(' ')));
 	 	 	 	}
 	 	 	 	else
 	 	 	 	{
 	 	 	 		fillWithCurrentDate(dateId);
	 				fillWithCurrentDate(timeId);
 	 	 	 	}
 	 	 	}
 	 		else
 	 	 	{
	 			fillWithCurrentDate(dateId);
	 			fillWithCurrentDate(timeId);
 	 	 	}
 	 	}
 		else if(dateFlag == '31-Dec-2100 00:00:00')
 	 	{
 			jQuery('#'+dateId).datebox('clear'); 	
 			if(dateId=='dteAbnmWostarttime')
 	 	 	{
 	 	 	 	if(allDate != null && allDate != ' ' && allDate != '')
 	 	 	 	{
 	 	 	 	 jQuery('#'+timeId).spinner('setValue',allDate.substring(dateFlag.indexOf(' ')+1,dateFlag.indexOf(' ')+6));
 	 			 jQuery('#'+dateId).datebox('setValue',allDate.substring(0,dateFlag.indexOf(' ')));
 	 	 	 	}
 	 	 	 	else
 	 	 	 	{
 	 	 	 		fillWithCurrentDate(dateId);
	 				fillWithCurrentDate(timeId);
 	 	 	 	}
 	 	 	}
 	 		else
 	 	 	{
	 			fillWithCurrentDate(dateId);
	 			fillWithCurrentDate(timeId);
 	 	 	}
 	 	}	
 		else
 			{
 			 jQuery('#'+timeId).spinner('setValue',dateFlag.substring(dateFlag.indexOf(' ')+1,dateFlag.indexOf(' ')+6));
 			 jQuery('#'+dateId).datebox('setValue',dateFlag.substring(0,dateFlag.indexOf(' ')));
 			}
 	}
 	else
 	 	{
 	 		fillWithCurrentDate(dateId);
	 		fillWithCurrentDate(timeId);
 	 	}
 	
 }
 function accecptDateDateEvt()
 {
		var accecptDate = jQuery('#dteAbnmAccecptDate').datebox('getValue') ;
	 	var completeDate = jQuery('#dteAbnmWoendtime').datebox('getValue') ;
	 	var currentDate = getCurrentDate();
	 	//if(convertStringToDate(accecptDate) < convertStringToDate(completeDate) )
	 	if(compareDateTime(accecptDate,completeDate) > 0 )
	 	{
	 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Less than Detected Date');
	 		popupCommonErrorMsg('Accecpted Date Should Not Less than Completed Date');
	 		fillWithCurrentDate('dteAbnmAccecptDate');
	 	}
	 	//else if(convertStringToDate(accecptDate) > currentDate)
	 	else if(compareDateTime(accecptDate,currentDate) < 0 )
	 	{
	 		//showValidationErrorMsg('dteAbnmEffectivedate','Should Not Exceed Current Date/Time');
	 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
	 		fillWithCurrentDate('dteAbnmAccecptDate');
	 	}
	 	else{
	 	    clearValidationErrorMsg('dteAbnmAccecptDate');
	 	}
	 	
	 	
 }
 function targetDateDateEvt()
 {
	var targetDate = jQuery('#dteAbnmTargetdate').datebox('getValue') ;
 	var detectoinDate = jQuery('#dteAbnmDetectiondate').datebox('getValue') ;
 	var currentDate = getServerDateTime();
 	
 	/*if(convertStringToDate(targetDate) > currentDate)
 	{
 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Exceed Current Date/Time');
 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
 		fillWithCurrentDate('dteAbnmTargetdate');
 	}*/
 	
 	 if(convertStringToDate(targetDate) < convertStringToDate(detectoinDate) )
 	{
 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Less than Detected Date');
 		popupCommonErrorMsg('Should Not Less than Detected Date');
 		fillWithCurrentDate('dteAbnmTargetdate');
 	}
 	else
 	    clearValidationErrorMsg('dteAbnmTargetdate');	
 }

 function effectiveDateEvt()
 {
	var effectiveDate = jQuery('#dteAbnmEffectivedate').datebox('getValue') ;
 	var detectoinDate = jQuery('#dteAbnmDetectiondate').datebox('getValue') ;
 	var currentDate = getServerDateTime();
 	
 	if(convertStringToDate(effectiveDate) > currentDate)
 	{
 		//showValidationErrorMsg('dteAbnmEffectivedate','Should Not Exceed Current Date/Time');
 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
 		fillWithCurrentDate('dteAbnmEffectivedate');
 	}
 	
 	else if(convertStringToDate(effectiveDate) < convertStringToDate(detectoinDate) )
 	{
 		//showValidationErrorMsg('dteAbnmEffectivedate','Should Not Less than Detected Date');
 		popupCommonErrorMsg('Should Not Less than Detected Date');
 		fillWithCurrentDate('dteAbnmEffectivedate');
 	}
 	else
 	    clearValidationErrorMsg('dteAbnmEffectivedate');	
 }

 function detectionDateEvt(date)
{
	//var detectionDate = jQuery('#dteAbnmDetectiondate').datebox('getValue') + jQuery('#spnAbnmDetectedbytime').spinner('getValue');
	var detectionDate = formatDate(date) + jQuery('#spnAbnmDetectedbytime').spinner('getValue');
	var currentDate = getServerDateTime();
	if(convertStringToDate(detectionDate) > currentDate)
	{
		//showValidationErrorMsg('dteAbnmDetectiondate','Should Not Exceed Current Date/Time');
		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
		setTimeout(function () { fillWithCurrentDate('dteAbnmDetectiondate'); }, 250);
		setTimeout(function () { fillWithCurrentDate('spnAbnmDetectedbytime'); }, 250);
		//fillWithCurrentDate('dteAbnmDetectiondate');
		//fillWithCurrentDate('spnAbnmDetectedbytime');
		fillTargetDate(currentDate);
	}
	else{
		clearValidationErrorMsg('dteAbnmDetectiondate');
		fillTargetDate(formatDate(date));
	}
	    	
}
 function dteAbnmWostarttime_onSelect(date)
 {   	
 	compareDates('dteAbnmDetectiondate','spnAbnmDetectedbytime','dteAbnmWostarttime','spnAbnmstarttime','Work Start Date should not be lesser than Detection Date');
 }
 function dteAbnmenddate_onSelect(date)
 {   	
//  	compareDates('dteAbnmWostarttime','spnAbnmstarttime','dteAbnmenddate','spnAbnmendtime','Work End Date should not be lesser than Work Start Date');
 }
 function workStartEvt()
 {
	 compareDates('dteAbnmDetectiondate','spnAbnmDetectedbytime','dteAbnmWostarttime','spnAbnmstarttime','Work Start Date should not be lesser than Detection Date');
 }
 function workEndEvt()
 {
	 compareDates('dteAbnmWostarttime','spnAbnmstarttime','dteAbnmenddate','spnAbnmendtime','Work End Date should not be lesser than Work Start Date');
 }
 function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
 {
     
     var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
     var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
	 var currentDate = getServerDateTime();
  	if( compareDateTime(toDate,fromDate) > 0 )
     {
     	//showValidationErrorMsg(toDateId,errMsg);
     	popupCommonErrorMsg(errMsg);
     	jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));
 		jQuery('#'+toTimeId).spinner('setValue',jQuery('#'+fromTimeId).spinner('getValue'));
     }
  	if(toDateId == 'dteAbnmenddate')
	{
	if(convertStringToDate(toDate) > currentDate)
	{
		//showValidationErrorMsg('dteAbnmenddate','Should Not Exceed Current Date/Time');
		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
		jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));
 		jQuery('#'+toTimeId).spinner('setValue',jQuery('#'+fromTimeId).spinner('getValue'));
	}
	}
     else
     	clearValidationErrorMsg(toDateId);	
 }

 function teamCombo_Onselect(teamIds) {
	 //alert('teamIds-abn'+teamIds); 
 }
 
 var repAbn = jQuery("#hdnAbnmRepeatedabn").val(); 

 if( repAbn != null && repAbn.trim() != ""){
	 jQuery("#chkRepAbn").prop("checked",true);
 }
 else
	 jQuery("#chkRepAbn").prop("checked",false);
 
 </script>

<form name="frmAbnormality" id="frmAbnormality" action="" method="post">
<input type="hidden" id="hdnempty" name ="hdnempty" value="R"/>
<div id="wrapper" style="width:100%;">

<!-- <label>Last Modified Person:</label> <label>Babu</label>  <label>On:</label> <label>01-May-2014</label> -->
<div class="main-cntborder" style="height: 500px;width:106%;">
<table  style="width:1100px;" >
<tr style=" height : 50px; position: relative;" >

<td colspan="3" valign="top" style=" left:80px;  top : 25px;">
	
			<div  id="frmAbnormalityFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">
			<input type="hidden" id="factory" name="cmbAbnmFactoryid" value="${requestScope.abnormalityBean.factory}"  ></input>			
			<input type="hidden" id="section" name="cmbAbnmSectionid" value="${requestScope.abnTlAbnormality.abnmSectionid}"  ></input>
			<input type="hidden" id="cell" name="cmbAbnmCellid" value="${requestScope.abnTlAbnormality.abnmCellid}"  ></input>
			<input type="hidden" id="machine" name="cmbAbnmEquipmentid1" value="${requestScope.abnTlAbnormality.abnmEquipmentid}"  ></input>
			<input type="hidden" id="flid" name="cmbAbnmFlid" value="${requestScope.abnTlAbnormality.abnmFlid}"  ></input>
			
			</div>
			
			<div id="abnmfunLocation" style="width: 50%; "></div>
			
			<table>
			<tr>
			<td>	
			<span id="err_abnmfunLocation" class="tpm-errormsg"></span>
			</td>
			</tr>
			</table>
			<div id="abnactnplan" style="margin-left:815px;  margin-top: -35px;margin-top: -26px\9;">
			<input type="button" id="btnabnActionpln" name="btnabnActionpln" class="easyui-button" style="width:80px;height:22px;" value="Action Plan" />
				 <span id="AbnYYSpan" style="padding-top: 0px; padding-left:5px;">
				 <input type="button" class="easyui-button"  value="Why Why" id="btnAbnYYLink" style="height: 22px;width:90px;"/>
				 </span>
			</div>

			 
			 <div style="position:relative;">
			 <span  id="abnFilemgr" style="right:-22px;top:-24px;position:absolute;right:140px\9;top: -28px\9;" >

     		
             </span> 
            </div> 
			<div class="clear"></div>
			</div>
</td>
</tr>
<tr><td colspan="3" >

</td>
</tr>
<tr style=" top:80px" >		
<td valign="top" style="padding-left: 30px; width : 360px;">
<!-- <div class="sub-header"><b><label >Basic Details</label></b></div> -->
			<div   style="padding-left: 0px;">
			<label>Tag No </label>
			<span style="padding-left:104px;"><label>Ref.DocID</label></span>
			</div>
			
			<div class="easyui-paddingbfpx" >
				<input id="cmbAbnmKeyid" name="cmbAbnmKeyid" class="easyui-combobox"  style="width:140px;" value="${requestScope.abnTlAbnormality.abnmKeyid}" readonly="${requestScope.abnormalityBean.disableabnmKeyid}" /> 
					<input type="hidden" id="hdnAbndKeyid" name="hdnAbndKeyid" value="${requestScope.abnTlDtl.abndKeyid}" />
					<span style="margin-left:2px;">
					<input id="txtAbnmRefdocid" type="text" class="easyui-text" size="15" name="txtAbnmRefdocid" value="${requestScope.abnTlAbnormality.abnmRefdocid}" readonly="readonly">
					</span>
				<span style="margin-left:0px;"><span class="easyui-text" id = "lblTagClass"  style="display:inline-block;width:100px; font-size:13px;font-weight:bold; text-align:center;">  </span></span>
			</div>
	
	
	<div >
	<label>Detected Date</label> 
	<div class="easyui-paddingbfpx">
	<span><input id="dteAbnmDetectiondate" name="dteAbnmDetectiondate" class="easyui-datebox"  value="${requestScope.abnTlAbnormality.abnmDetectiondate}" style="width:85px;"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} /></span>
	<span class="spinner"><input  id="spnAbnmDetectedbytime" name="spnAbnmDetectedbytime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.abnormalityBean.abnmDetectedbytime}"  style="width: 60px;"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/></span>
	
	</div>
	<div>
		<span id="err_dteAbnmDetectiondate" class="tpm-errormsg"></span>
	</div>
	<div >
		<label class="mandatory-lbl">Detected By </label>
		<input type="checkbox" id="chkAbnmOthers" name="chkAbnmOthers" value="Y"  ${ requestScope.abnormalityBean.disableForm == true ? 'disabled':''}/><label> Others</label>
	</div>
	<div class="easyui-paddingbfpx">
		<span><input id="cmbAbnmDetectedby" name="cmbAbnmDetectedby" class="easyui-combobox"  style="width:305px; clear:both; " value="${requestScope.abnTlAbnormality.abnmDetectedby}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} ></span> 			
	</div>
	
		<div style="margin-top: 5px;" ><label id="lblMachine"  >Equipment </label></div>
	<div class="easyui-paddingbfpx"><input id="cmbAbnmEquipmentid" name="cmbAbnmEquipmentid" class="easyui-combobox"  style="width:305px;" value="${requestScope.abnTlAbnormality.abnmEquipmentid}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/>  
	</div>
	
<!-- 	<div ><label id="lblAssembly" >Assembly/Station / असेंब्ली /स्टेशन</label></div> -->
<%-- 	<div class="easyui-paddingbfpx"><input id="cmbAbnmAssemblyid" name="cmbAbnmAssemblyid" class="easyui-combobox" tabindex="7"  style="width:305px;" value="${requestScope.abnTlAbnormality.abnmAssemblyid}"  "${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}" >  --%>
									<!-- <input type="button" id="btnAssembly" name="btnAssembly" class="easyui-button" value="..."/  "${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}" > --> 
	</div>
	<div  class="mandatory-lbl" ><label id="lblAbnDesc">Abnormality Description</label></div>		
	<div class="easyui-paddingbfpx">
		<textarea maxlength="490" rows="1" style="width:305px;resize:none; height: 50px;" cols="" id="txtAbnmDescription" name="txtAbnmDescription"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}>${requestScope.abnTlAbnormality.abnmDescription}</textarea>
	</div>
	<div style="padding-left:200px;"><label style="display:none;">Tentative Date</label></div>
	<div>
		<input type="checkbox" id="chkAbnmShutdownmaint" name="chkAbnmShutdownmaint"  value="Y"  ${ requestScope.abnTlAbnormality.abnmShutdownmaint == 'Y' ? ' checked':''}  ${ requestScope.abnormalityBean.disableForm == true ? ' disabled':''}/> 
		<input type="hidden" id="hdnAbnmShutdownmaint" name="hdnAbnmShutdownmaint" value="${ requestScope.abnTlAbnormality.abnmShutdownmaint == 'Y' ? 'Y':''}"    ${ requestScope.abnormalityBean.disableForm == true ? ' ':'disabled'} />
		
		<span><label>Include in Shutdown Maintenance </label></span>
		<span><input id="dteAbnmTentativrDate" name="dteAbnmTentativrDate" class="easyui-datebox" style="display:none;" value="${requestScope.abnTlAbnormality.abnmTentativrDate}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} /></span>
	</div>

</div>
	</td>
	
	<td valign="top" style=" width : 360px; ">
	<div class="mandatory-lbl" style="margin-top: 3px;" ><label id="lblabnType">Abnormality Type </label></div>
	<div class="easyui-paddingbfpx">
		<input  id="cmbAbnmTypeid" name="cmbAbnmTypeid" class="easyui-combobox" style="width:305px;" value="${requestScope.abnTlAbnormality.abnmTypeid}"   ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/>
		 
	</div>
	
	<div ><label  style="margin-top: 3px;">Sub Type</label></div>
	<div class="easyui-paddingbfpx">
		<input id="cmbAbnmSubtype" name="cmbAbnmSubtype" class="easyui-combobox"   style="width:305px;" value="${requestScope.abnTlAbnormality.abnmSubtype}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/>  
	</div>
	
	<div style="padding-top:0px;">
<!-- <div class="sub-header" style="margin-top: 5px;width: 350px;"  ><b><label>Causes</label></b></div> -->


		
<div  ><label class="mandatory-lbl">Tag Class</label></div>
	<div class="easyui-paddingbfpx">
		<input id="cmbAbnmTagclassid" name="cmbAbnmTagclassid"  class="easyui-combobox"  style="width:220px;" value="${requestScope.abnTlAbnormality.abnmTagclassid}"   ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/>
<%-- 		 "${ requestScope.abnormalityBean.disableTagclass == true ? ' readonly':''}" --%>
		<span id="AbnWOSpan"><input type="button" class="easyui-button"  value="Work Order" id="btnAbnWOLink" style="height: 22px;width:80px;" readonly="readonly"/></span> 
	</div>



	
<div ><label  class="mandatory-lbl">Abnormality Impact</label>
<span id="spnAFFEMLBL" style="padding-left:44px"><label>AFEEM</label></span>
</div>
	<div class="easyui-paddingbfpx">
		<input id="cmbAbnmImpactid" name="cmbAbnmImpactid" class="easyui-combobox" style="width:150px;" value="${requestScope.abnTlAbnormality.abnmImpactid}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} > 
		
	<span id="spnAFFEM">
		<input id="cmbAbnmAfeemid" name="cmbAbnmAfeemid" class="easyui-combobox" readonly="readonly" value="${requestScope.abnTlAbnormality.abnmAfeemid}"  >
	</span>
	</div>
</div>	



<!-- <div class="sub-header" style="width: 350px;" ><b><label>Status and Target Details</label></b></div> -->

<div id="chk"  ><label class="mandatory-lbl">Status</label>
<span style="padding-left:75px;"  ><label class="mandatory-lbl">Expected Date</label>
</span>
<span style="padding-left:25px;"  ><label>Proposed Target</label>
</span></div>

<div id="unchk"  ><label class="mandatory-lbl">Status</label>
<span style="padding-left:75px;"  ><label>Expected Date</label>
</span>
<span style="padding-left:25px;"  ><label>Proposed Target</label>
</span></div>

	<div class="easyui-paddingbfpx">
<input id="cmbAbnmStatus" name="cmbAbnmStatus" class="easyui-combobox"   style="width:100px;" value="${requestScope.abnormalityBean.status}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}  ${ requestScope.abnTlAbnormality.abnmShutdownmaint == 'Y' ? ' readonly':''}  >
	<span><input type="checkbox" id="tar"   ${ requestScope.abnormalityBean.disableForm == true ? 'disabled':''} /></span>
<%--  <c:out value = "${ requestScope.modify == true ? ' readonly':''}"/>  --%>
		 <span><input id="dteAbnmTargetdate" name="dteAbnmTargetdate" class="easyui-datebox"  style="width:85px;" value="${requestScope.abnTlAbnormality.abnmTargetdate}"  ></span>
		 <span><input type="checkbox" id="chkTar" disabled="disabled"   ${ requestScope.abnormalityBean.disableComptdDtls == true ? ' disabled':''}/></span>
		 <span><input id="dteAbnmEffectivedate" name="dteAbnmEffectivedate" readonly="readonly" class="easyui-datebox"  style="width:85px;" value="${requestScope.abnTlAbnormality.abnmEffectivedate}"   /></span>
	</div>
	
	<div id="divAccecptRequired">
<%-- 	<input id="cmbAbnmPriority" name="cmbAbnmPriority" tabindex="21" class="easyui-combobox"  style="width:305px;" value="${requestScope.abnTlAbnormality.abnmPriority}" <c:out value = "${ requestScope.abnormalityBean.disableComptdDtls == true ? ' readonly':''}"/> <c:out value = "${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}"/>> --%>
	<div id="divPriority">
	<div>
		<label>Priority</label>
	</div>
	<div>
	<select id="cboAbnmPriority" name="cboAbnmPriority" class="easyui-combobox"  style="width:135px;font-size: 12px"    ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} >  
                        	<option title="L" value="L">Low</option>
                        	<option title="M" value="M">Medium</option>
                        	<option title="H" value="H">High</option>                         	
    </select>
    </div>
    </div>
    
		<input type="checkbox" name="chkAbnmAccecpatncerequired" id="chkAbnmAccecpatncerequired"    value="Y"  ${requestScope.abnTlAbnormality.abnmAccecpatncerequired == 'Y' ? 'checked':''} /> <label> Required  Acceptance</label>
	    <input type="hidden" id="hdnAbnmAccecpatncerequired" name="hdnAbnmAccecpatncerequired" value="${ requestScope.abnTlAbnormality.abnmAccecpatncerequired == 'Y' ? 'Y':''}"   />
	
	</div>
	<div class="completedtls">
		<div ><label class="mandatory-lbl">Completed By</label> </div>	
		<div class="easyui-paddingbfpx">
			<input id="cmbAbnmCompletedby" name="cmbAbnmCompletedby"  class="easyui-combobox"  style="width:305px;" value="${requestScope.abnTlAbnormality.abnmCompletedby}"  ${ requestScope.abnormalityBean.disableComptdDtls == true ? ' readonly':''} ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}> 
		</div>
<!-- 		id="divCompDate"    id="lblCompDate" -->
		<div><span><label class="mandatory-lbl" >Completed Date</label></span></div>
		<div class="easyui-paddingbfpx" >
			<span> <input id="dteAbnmWoendtime" name="dteAbnmWoendtime"  class="easyui-datebox" readonly="readonly" style="width:125px;" value="${requestScope.abnTlAbnormality.abnmWoendtime}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} />  </span>
		</div>
		
		<div id="divAcceptance">
	<div style="padding-left:20px;">
		<label >Accecpted Date</label>
	</div>
		<input type="checkbox" id="chkAbnmAccecpted" name="chkAbnmAccecpted"  value="Y"  ${requestScope.abnTlAbnormality.abnmAccecpted=='Y' ? 'checked':''}/>
		<input id="dteAbnmAccecptDate" name="dteAbnmAccecptDate" class="easyui-datebox"  style="width:85px;" value="${requestScope.abnTlAbnormality.abnmAccecptDate}"  />
	</div>
	

	</div>
	
	
	
				
</td>

<td valign="top" style=" width : 360px; ">
<div id="RepAbn"></div>
	<div class="easyui-paddingbfpx">
	<input type="checkbox" id="chkRepAbn" name="chkRepAbn"  disabled="disabled"><label style="padding-left:10px;">Repeated Abnormality</label>
	<span><input type="button" id="btnRepAbn" name="btnRepAbn" style="height:21px" class="easyui-button" value="View"/></span>
	</div>
	
	<div class="easyui-paddingbfpx">
	<input class="easyui-paddingbfpx" type="checkbox" id="chkAbnmNotifysap" name="chkAbnmNotifysap" disabled="disabled" value="Y" value="${requestScope.abnTlAbnormality.abnmNotifysap}"  ${ requestScope.abnTlAbnormality.abnmNotifysap == 'Y' ? 'checked':''}  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/><label style="padding-left:10px;">Create Notification in SAP</label>
	</div>
<!-- </td> -->

		<div><label id="lblTrade" >Maintenance Section</label></div>
	<div id="tradeDiv" class="easyui-paddingbfpx"><input id="cmbAbnmTradeid" name="cmbAbnmTradeid"  class="easyui-combobox"  style="width:305px;" value="${requestScope.abnTlAbnormality.abnmTradeid}"  ${ requestScope.abnormalityBean.disableComptdDtls == true ? ' readonly':''} ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/> 
	</div>
	
	<div>
		<label id="lblResp" class="safetyLabels mandatory-lbl">Responsibility</label>
		<input type="checkbox" id="chkAbnmRepOtheres" name="chkAbnmRepotheres" value="Y"  ${ requestScope.abnormalityBean.disableForm == true ? 'disabled':''}/><label>Others</label>
	</div>
	<div id="hseResponsibility" class="easyui-paddingbfpx">
	<input id="cmbAbnmResponsibleid" name="cmbAbnmResponsibleid"  class="easyui-combobox"  style="width:305px;" value="${requestScope.abnTlAbnormality.abnmResponsibleid}"  ${ requestScope.abnormalityBean.disableComptdDtls == true ? ' readonly':''}  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/>
<%-- 	  "${ requestScope.modify == true ? ' readonly':''}"  --%>
	</div>
	
		
	
	<div id="comp" ><label class="mandatory-lbl" >Countermeasure</label></div>
	<div id="pend" ><label>Countermeasure</label></div>
	<div class="easyui-paddingbfpx">
	<textarea class="" maxlength="490" rows="1"   style="width:305px;resize:none; height:50px;" cols="" id="txtAbnmCountermeasure" name="txtAbnmCountermeasure"  ${ requestScope.abnormalityBean.disableComptdDtls == true ? ' readonly':''}>${requestScope.abnTlAbnormality.abnmCountermeasure}</textarea>
	</div>	
	
	<div ><label>Remarks</label></div>
	<div class="easyui-paddingbfpx">
	<textarea class="" maxlength="490"  rows="1" style="width:305px;resize:none; height:50px;" cols="" id="txtAbnmRemarks" name="txtAbnmRemarks"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}>${requestScope.abnTlAbnormality.abnmRemarks}</textarea>
</div>
<div ><label  class="" id="lblabncategory">Abnormality Category </label></div>
	<div class="easyui-paddingbfpx">
		<input id="cmbAbnmCategoryid" name="cmbAbnmCategoryid" class="easyui-combobox"  style="width:270px;" value="${requestScope.abnTlAbnormality.abnmCategoryid}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} > 
		<input type="button" id="btnCategory" value="..."  class="easyui-button" style="display:none" tabindex="16"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}/>
	</div>
	
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
<input type="hidden" id="hdnDisForm" value="${requestScope.abnormalityBean.disableForm}" /> 
<input type="hidden" id="hdnFormMode" value="${requestScope.abnormalityBean.formActionMode}"/>
</td>
</tr>

<tr>
<td colspan="3">
<!-- <div class="sub-header"  style="width:95%"><b><label >Additional Details</label></b></div> -->
</td>
</tr>

<tr>
<td style="padding-left: 30px; width : 360px;" valign="top">
	<div id="divWhatCause" style="display:none;">
	<div><label >What Cause</label></div>
	<div class="easyui-paddingbfpx">
		<textarea class="txtarea" maxlength="500" rows="1" tabindex="26" style="width:305px;resize:none; height:50px;" cols="" id="txtAbnmWhatcause" name="txtAbnmWhatcause"  ${ requestScope.abnormalityBean.disableComptdDtls == true ? ' readonly':''} ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} >${requestScope.abnTlAbnormality.abnmWhatcause}</textarea>
	</div>
	</div>
	
	
</td>
<td valign="top">
<div id="divImmAction" style="display:none;">
	<div><label class="safetyLabels">Immediate Action</label></div>
	<div class="easyui-paddingbfpx">
		<textarea class="txtarea" maxlength="500" rows="1" tabindex="24" style="width:305px;resize:none; height: 50px;" cols="" id="txtAbndImmediateaction" name="txtAbndImmediateaction"  ${ requestScope.abnormalityBean.disableComptdDtls == true ? ' readonly':''}  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''}> ${requestScope.abnTlDtl.abndImmediateaction}</textarea>
	</div>
</div>
	
</td>
<td style=" width : 360px;" valign="top">	
<div id="divImpType" style="display:none;">
<div><label id="lblImprvType"  >Improvement Type</label>
<span style="padding-left: 50px;"><label id="lblHirarefno" >HIRA Ref No</label></span>
</div>
<div class="easyui-paddingbfpx">
	<select id="cboAbndImprovementteam" name="cboAbndImprovementteam" class="easyui-combobox"  style="width:135px;font-size: 12px"    ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} >  
                        	<option value=""> </option>
                        	<option title="C" value="C">CIT</option>
                        	<option title="O" value="O">OIT</option>
                        	<option title="M" value="M">MP</option> 
                        	<option title="I" value="I">MI</option>
    </select>
    <span style="padding-left: 20px;">
    	<input id="txtAbndHirarefno" name="txtAbndHirarefno" class="easyui-text" style="width:105px;" value="${requestScope.abnTlDtl.abndHirarefno}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} >
    </span>
    <input type="hidden" id="hdnAbndImprovementteam" name="hdnAbndImprovementteam" value="${requestScope.abnTlDtl.abndImprovementteam}"/>
    <input type="hidden" id="hdnAbnmPriority" name="hdnPriority" value="${requestScope.abnTlAbnormality.abnmPriority}"/>
    
		
		<div id="pkyDiv">
		<div ><span style="padding-right: 0px;"></span><label id="lbl" >Poka Yoke Provided</label></div>
			<div class="easyui-paddingbfpx">
				<input type="checkbox" id="chkisPokayoke"  ${ requestScope.abnormalityBean.disableForm == true ? ' disabled':''} />
				<input id="txtAbndPokayokeid" name="txtAbndPokayokeid" class="easyui-text" style="width:288px;" value="${requestScope.abnTlDtl.abndPokayokeid}"  ${ requestScope.abnormalityBean.disableForm == true ? ' readonly':''} > 
		</div>
		</div>	
</div>
</div>
</td>
</tr>

</table>
</div>
<input type="hidden" id="hdnAllottedDate" name="hdnAllottedDate" value="${requestScope.allottedDateMSR}"/>
<input type="hidden" id="hdnTagClass" name="hdnTagClass" value="${requestScope.tagClass}"/>
<input type="hidden" id="hdnAbnmWomasterid" name="hdnAbnmWomasterid" value="${requestScope.abnTlAbnormality.abnmWomasterid}"/>
<input type="hidden" id="hdnfrmType" name="hdnfrmType" value="${requestScope.frmType}"/>
<input type="hidden" id="hdnenableComplete" name="hdnenableComplete" value="${requestScope.enableComplete}"/>
<input type="hidden" id="hdnPendComp" name="hdnPendComp" value="${requestScope.pendingFlag}"/>
<input type="hidden" id="hdndisableTag" name="hdndisableTag" value="${requestScope.disableTag}"/>
<input type="hidden" id="hdnShowCompDate" name="hdnShowCompDate" value="${requestScope.showCompDate}"/>
<input type="hidden" id="hdnyyEnable" name="hdnyyEnable" value="${requestScope.yyEnable}"/>
<input type="hidden" id="hdnwoEnable" name="hdnwoEnable" value="${requestScope.woEnable}"/>
<input type="hidden" id="hdndelABNMode" name="hdndelABNMode" value="${requestScope.delActivity}">
<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="N">
 <input type="hidden" id="hdnclirefDocID" name="hdnclirefDocID" value="${requestScope.clirefDocID}"/>
 <input type="hidden" id="hdnabnkeyID" name="hdnabnkeyID" value="${requestScope.AbnkeyId}" />
 <input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}" />
 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}" />
 <input type="hidden" id="hdnAbnmRepeatedabn" name="hdnAbnmRepeatedabn" value="${requestScope.abnTlAbnormality.abnmRepeatedabn}" />
 <input type="hidden" id="hdnOthers" name="hdnOthers" value="${requestScope.abnTlAbnormality.abnmOthers}" />
 <input type="hidden" id="hdnRepOthers" name="hdnRepOthers" value="${requestScope.abnTlAbnormality.abnmRepOthers}" />
 <input type="hidden" id="hdnChkTagClassId" name="hdnChkTagClassId" value="${requestScope.abnTlAbnormality.abnmTagclassid}" />
 
 <input type="hidden" id="hdnChkTagClassId" name="hdnChkTagClassId" value="${requestScope.abnTlAbnormality.abnmTagclassid}" />
 <input type="hidden" id="hdnCheckSave" name="hdnCheckSave" value="0" />
</div>

<!-- <div id="" style="display:none;"> id="abnHistory"-->
<div title="Remarks"  name="abnHistory" class="flPopUpBox" style="margin-top:5%;display:none;width:30%;height:50%;">
	  
	<div class="sub-header" style="width:95%"><b><label >Abnormaility History Details</label></b> </div>
	  <div align="center">
     <table align="center">
       <tr>
        <td>
         <div class="easyui-paddingbfpx">
         	<label>Abnormality Id</label>
         	<span style="padding-left:105px;"><label>Date</label></span>
         </div>
         <div>
          <input type="text" class="easyui-text"  id="txtAbnhAbnmKeyid" name="txtAbnhAbnmKeyid" maxlength="10"   style=" width : 180px;height:20px; text-align:left;" readonly="readonly" value="${requestScope.abnTlAbnormality.abnmKeyid}"/>
          <span>
          	<input type="text" class="easyui-text"  id="dteAbnhDate" name="dteAbnhDate" maxlength="10"   style=" width : 100px;height:18px; text-align:left;" readonly="readonly" value=""/>
          </span>
          </div>
     </td>
     </tr>
     <tr>
     <td>
         <div class="easyui-paddingbfpx"><label>Modify By</label></div>
         <div>
<!--           <input class="easyui-combobox"  id="cmbAbnhChangeby" name="cmbAbnhChangeby" maxlength="10"   style=" width : 180px;height:20px; text-align:left;" value=""/> -->
          		<input id="cmbAbnhChangeby" name="cmbAbnhChangeby"  class="easyui-combobox"  style="width:284px;" value="${requestScope.loginUser}" readonly="readonly"/> 
          </div>
     </td>
     </tr>
     
     <tr>
     <td>
         <div class="easyui-paddingbfpx"><label>Reason</label></div>
         <div>
          <textarea maxlength="200" rows="2" cols="80" style="width:280px; height : 50px;" id="txtAbnhReasons" name="txtAbnhReasons"></textarea>
          </div>
     </td>
     </tr>
     
     <tr>
     <td>
     <input type="button" class="easyui-button" id="btnok" name="btnok"style="width:50px;height:21px;" value="Ok" />
      <span style="padding-left: 60px">
     <input type="button" class="easyui-button" id="btncancel" name="btncancel"style="width:70px;height:21px;" value="Cancel" /></span>
     </td>
     </tr>
     
     </table>

 </div>
    <input type="hidden" name="hdnmode" id="hdnmode" value="${requestScope.mode}" />
	<input type="hidden" name="hdnmodeType" id="hdnmodeType" value="${requestScope.modeType}" />
	<input type="hidden" name="hdnabnStatus" id="hdnabnStatus" value="${requestScope.abnStatus}" />
	<input type="hidden" name="hdnTargetDate" id="hdnTargetDate" value="${requestScope.abnTlAbnormality.abnmTargetdate}" />
	<input type="hidden" name="hdnabnmAccecpatncerequired1" id="hdnabnmAccecpatncerequired1" value="${requestScope.abnTlAbnormality.abnmAccecpatncerequired}" />
	<input type="hidden" name="hdnRespond" id="hdnRespond" value="${requestScope.abnTlDtl.abndResponsiblity}" />
	<input type="hidden" name="hdnlocationid" id="hdnlocationid" value="${requestScope.locationid}">
	<input type="hidden" name="hdnrowid" id="hdnrowid" value="${requestScope.rowId}">
</div>
</form>
<input type="hidden" id="hdnRefDoctype" name="hdnRefDoctype" value="${requestScope.refDoctype}"/>
 <input type="hidden" id="hdnRefDocId" name="hdnRefDocId" value="${requestScope.refDocId}"/>
 