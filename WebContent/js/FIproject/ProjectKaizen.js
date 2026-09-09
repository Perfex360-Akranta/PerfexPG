
jQuery(document).ready(function(){
	initialiseForm("frmProject");
	jQuery('#submitForm').val('frmProject');
	var type = jQuery("#hdntype").val();
	
	var cStgCode =  getCurrentStageCode();
	if(cStgCode =="R")
		jQuery("#frmProject input[id='mode']").val("view");
	var cuStage = getCurrentStage();
	
/*	if( cuStage != undefined && cuStage != "")
		jQuery('#'+cuStage).css('background-color','#FFA500');
*/	
	disableUIButton("btnPrjYYLink");
	disableUIButton("btnFishBone");
	formatDateBox('dteKzpmStartdate','dd-MMM-yyyy');
	formatDateBox('dteKzpmEnddate','dd-MMM-yyyy');
	formatDateBox('dteKzpmDefinetargetdate','dd-MMM-yyyy');
	formatDateBox('dteKzpmMeasuretargetdate','dd-MMM-yyyy');
	formatDateBox('dteKzpmAnalysetargetdate','dd-MMM-yyyy');
	formatDateBox('dteKzpmImprovetargetdate','dd-MMM-yyyy');
	formatDateBox('dteKzpmControltargetdate','dd-MMM-yyyy');
	formatDateBox('dteKzpmClosuretargetdate','dd-MMM-yyyy');
	readOnlyFields("dteKzpmStartdate");
	readOnlyFields("dteKzpmDefinetargetdate");
	readOnlyFields("dteKzpmMeasuretargetdate");
	readOnlyFields("dteKzpmAnalysetargetdate");
	readOnlyFields("dteKzpmImprovetargetdate");
	readOnlyFields("dteKzpmControltargetdate");
	readOnlyFields("dteKzpmClosuretargetdate");
	numericTextBox("txtKzpmBenefits");
	numericTextBox("txtKzpmVerifiedamnt");
	numericTextBox("txtKzpmWave");
	jQuery("#fileUpld").hide();
	if(! jQuery("#chkKzpmIstangible").is(":checked"))
		disableField("frmProject","txtKzpmBenefits");
	
	var isDef =  jQuery("#hdnIsDefineStage").val();
	if( isDef == "Y"  || isDef == "y" ){
		disableUIButton("btnMaicStage");
		disableUIButton("btnClosureStage");
	}	
	
	var flid = jQuery("#frmProject input[id='flid']").val();
	
	fillComboBox("frmProject","cmbKzpmProjectchamp","employee.commonFilter?loginEmpshow=false" );

	fillComboBox("frmProject","cmbKzpmBelt","kaizenBelt.prpo");
	fillComboBox("frmProject","cmbKzpmImprCategory","kaizenCategory.commonFilter");
	fillComboBox("frmProject","cmbKzpmProjectmetrics","combo_KPIIndicator.prpo?flid="+flid);
	
	fillComboBox("frmProject","cmbKzpmCreatedby","employee.commonFilter" );
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	var factId =  "";//jQuery("#frmProject input[id='factory']").val();
	var sectionId = jQuery("#frmProject input[id='section']").val();
	var cellId = jQuery("#frmProject input[id='cell']").val();
	var machId = jQuery("#frmProject input[id='machine']").val();
	
	var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
	if( cStgCode == "R") 
		dataStr += "&disableFuncLoc=true" ;
	
	loadFunctionalLocation("projectfun","functionalLoc.kkpp","projectfunLocation","frmProject",dataStr);
	
	jQuery("#cmbKzpmProjectchamp").combobox({onRequest:function( ){	    
		var flid = jQuery("#frmProject input[id='flid']").val();
		return "&isPbuHead=Y&flid="+flid+"&loginEmpshow=false" ;
	}});

	jQuery("#cmbKzpmProjectmetrics").combobox({onRequest:function( ){
		var flid = jQuery("#frmProject input[id='flid']").val();
		return "&flid="+flid ;
	}});

	
	 var tangible= jQuery('#chkKzpmIstangible').val();
	 var amnt=jQuery('#txtKzpmBenefits').val();
	// alert(tangible +" tangible "+amnt);
	/* if(tangible=="Y" && amnt>=500000 ){
			jQuery("#fileUpld").show();
	 }*/
	
	if( jQuery('#chkKzpmIstangible').is(":checked"))
		jQuery("#lblAmount").addClass("mandatory-lbl");
	//if( jQuery('#chkKzpmIsintangible').is(":checked"))
	//	jQuery("#lblImprCategory").addClass("mandatory-lbl");
	
	jQuery('#chkKzpmIstangible').click(function() {
		
		if(jQuery(this).is(":checked")){
		//	jQuery("#fileUpld").show();		
			jQuery("#lblAmount").addClass("mandatory-lbl");
			jQuery('#chkKzpmIsintangible').attr('checked', false);
			enableFields("txtKzpmBenefits");
		}
		else{
			jQuery('#txtKzpmBenefits').val('');
			jQuery("#lblAmount").removeClass("mandatory-lbl");
			disableField("frmProject","txtKzpmBenefits");
			jQuery("#fileUpld").hide();
		}
	});	
	jQuery('#chkKzpmIsintangible').click(function() {	
		
		if(jQuery(this).is(":checked")){
			
			jQuery('#chkKzpmIstangible').prop('checked', false);
			jQuery("#lblAmount").removeClass("mandatory-lbl");
			
			jQuery('#txtKzpmBenefits').val('');
			disableField("frmProject","txtKzpmBenefits");
		}
		//else
		//	jQuery("#lblAmount").removeClass("mandatory-lbl");
	});
		
	getBenefits();
	
	jQuery("#btnInsertKpi").click(function() {
		if(jQuery("#mode").val()!="view"){
			 var pilar = "TGT003";
			 var flid = jQuery("#frmProject input[id='flid']").val();
			 if( jQuery(this).attr("disabled") != "disabled")
				 LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.prpo?from=project&pillar="+pilar+"&flid="+flid, true,"48%","75%","50px","30%", "multiSelectOk_Callback","Key Performance Indicator",false);
		}
	});
	
	jQuery("#btnAddNew").click(function(){
		if(jQuery("#mode").val()!="view"){
			//var row = jQuery("#resourcesgrid").jqGrid("getDataIDs");
			var kzpmKeyid = jQuery("#hdnKzpmKeyid").val();
			var flid = jQuery("#frmProject input[id='flid']").val();
			LoadPopUp("divAddResource","addResourcesList_input.prpo?&kzpmKeyid="+kzpmKeyid+"&flid="+flid,true,"80%","550px","0px","10%", "multiSelectOk_Callback","Resources List");
		}
		//addRow(row);
	});
	
	jQuery("#btnAddrow").click(function(){
		if(jQuery("#mode").val()!="view"){
			var projectResourcesc=saveResources();
			//alert(projectadmaic);
			//alert("projectResourcesc"+projectResourcesc);
			if(projectResourcesc!="false" && projectResourcesc!=false && projectResourcesc.trim().length>0){
				var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
				processAjaxCalls("resource_save.prpo","hdnKzpmKeyid="+hdnKzpmKeyid+projectResourcesc,"resourcesSaveSuccess");
			}
		}
		//LoadPopUp("divResourcePop", "resource_view.prpo?q=2", true,"70%","40%","20%","6%", "multiSelectOk_Callback","Resource"," ",true);
	});
	
	
	if(type == "project"){		
		jQuery("#Stagesbutton").hide();
		jQuery("#linkbutton").hide();
		//jQuery("#projectcreation").hide();
		jQuery("#projectcreation").show();
		//jQuery("#DMAIC").hide();
		jQuery("#DMAIC").show();
		enableFields("dteKzpmStartdate");
		enableFields("dteKzpmDefinetargetdate");
		enableFields("dteKzpmMeasuretargetdate");
		enableFields("dteKzpmAnalysetargetdate");
		enableFields("dteKzpmImprovetargetdate");
		enableFields("dteKzpmControltargetdate");
		enableFields("dteKzpmClosuretargetdate");
	}	
	else{
		damicOnLoadComplete();
		processGridnew("projectsproto_input.prpo","q=2&keyid="+masterkeyid,"resourcesgrid","pager","","ResouredoubleClick","","loadResource_complete");
		//processGridnew("projectsdmaic_input.prpo","q=2&keyid="+masterkeyid,"dmaicgrid","","","","","damicOnLoadComplete");
		processGridnew("projectsmile_input.prpo","q=2&keyid="+masterkeyid,"fourgrid","pagergrid","","doubleClickMile","","load_complete");
		processGridnew("projectsKpi_input.prpo","q=2&masterkeyid="+masterkeyid,"Kpigrid","pagerKpi","","","","loadComplKPI");
		
		processGridnew("projectskaizen_input.prpo","q=2&flid="+flid+"&master="+masterkeyid,"Kaizengrid","pagerkaizen","","","","load_complete");
		
		var frMode = jQuery("#mode").val();
		let fmgMode = "";
		//alert("frMode:"+frMode);
		if(frMode === "approval"){
			fmgMode = "view";
		}else if(frMode === "define"){
			fmgMode = "fipdefine";
		}
		//alert("after frMode :"+fmgMode);
		fileManagerPopUp(masterkeyid,"PRO","frmProject","btnfilemgr","abnFilemgr",fmgMode);
		//alert('flid:'+flid);
		loadDefineStage();	 
		jQuery("#btnAddMst").click(function(){
			addMileStone();
		});
		if(jQuery("#hdnIsClosure").val()=="y"){
			
			//getClosureStage();
		}	

	    var fmode = jQuery("#mode").val();		
		if ( cStgCode == "R" || cuStage != "Define" || fmode == "view" || fmode == "approval" || jQuery("#hdnKzpmClosurestage").val() == "C"){
			disableProject();
		}
		else if (jQuery("#mode").val() == "define"){
			projectDefineMandatory();
	 	}
   	
		setAuthorizationTab();
		
		
	/*	jQuery('#chkKzpmIsintangible').click(function() {
			
			if(jQuery(this).is(":checked")){
				jQuery("#lblImprCategory").addClass("mandatory-lbl");	
			}
			else
				jQuery("#lblImprCategory").removeClass("mandatory-lbl");
		}); */	
		
		//getWorkFlowStage();
		
		jQuery("#btnMaicStage").click(function(){ 
			//jQuery("#ClosureStage").hide();
			jQuery("#MaicStage").show();
			//jQuery("#divDefApproval").html("");
			//jQuery("#divMAICApproval").html("");
			//jQuery("#divClosureApproval").html("");
			if(jQuery("#hdntype").val() == "define"){
				jQuery("#DefineStage").show();
				//jQuery("#MaicStage").hide();
			}
			else if(jQuery("#hdntype").val() == "team"){
				jQuery("#DefineStage").hide();
				jQuery("#MaicStage").show();		
			}
			else if(jQuery("#hdntype").val() == "closure"){
				jQuery("#DefineStage").hide();
				jQuery("#MaicStage").show();
				//jQuery("#ClosureStage").show();		
			}
		//	getWorkFlowStage();	
		});
	}
	
	jQuery("#txtKzpmBenefits").focusout("blur", function() {
		 
	var valSavings = jQuery('#txtKzpmBenefits').val();

	var url="amountInwrds_input.kaizen";
	  LoadPopUp("KaizenBenAmt",url+'?valSavings='+valSavings , true,"45%","55%","4%","1%", "popup_callback()","Amount in Words","",false,true);	 

	   }); 
	   
	 jQuery("#txtKzpmVerifiedamnt").focusout("blur", function() {
	   	 
	   var valSavings = jQuery('#txtKzpmVerifiedamnt').val();

	   var url="amountInwrds_input.kaizen";
	     LoadPopUp("KaizenBenAmt",url+'?valSavings='+valSavings , true,"45%","55%","4%","1%", "popup_callback()","Amount in Words","",false,true);	 

	      }); 
		  
		  jQuery("#txtKzpmFinalamnt").focusout("blur", function() {
		  	 
		  var valSavings = jQuery('#txtKzpmFinalamnt').val();

		  var url="amountInwrds_input.kaizen";
		    LoadPopUp("KaizenBenAmt",url+'?valSavings='+valSavings , true,"45%","55%","4%","1%", "popup_callback()","Amount in Words","",false,true);	 

		     });
});





function loadCheckList(isPopup,role,stage,mode){
	
	var clStg =  getCurrentStage();
	//if( stage != undefined )
	//	clStag = stage;
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	var createdBy = jQuery("#txtKzpmCreatedby").val();
	var workFlowEnableEmps =	jQuery("#hdnFIPRODEFCurEmps").val();
	role = jQuery("#hdnFIPRODEFCurRole").val();
	var flid = jQuery("#frmProject input[id='flid']").val();
	var str = "?clstage="+clStg+"&projectId="+masterkeyid+"&createdBy="+createdBy+"&workFlowEnableEmps="+workFlowEnableEmps+"&mode="+mode;
	str += "&flid="+flid+"&role="+escape(role);
	var url = "prjCheckList_input.prpo"+str;
	var loadDivId = "divChecklistLoad";
	//alert(str);
	if( isPopup == true)						
		LoadPopUp(loadDivId, url, false,"50%","85%","40px","3%", "","Check List");
	else
		LoadForm(loadDivId,"",url);
		//LoadPopUp("divChecklistLoad", , false,"50%","85%","40px","3%", "","Check List");
}
function FIPRODEF_AcceptDateValidation(approvalDate,transCode){
	var sartDate = jQuery("#dteKzpmStartdate").datebox("getValue");
	var endDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	
	if(convertStringToDate(approvalDate) < convertStringToDate(sartDate))
	{
		alert(" Accept date can not be lesser than project Start Date");
		return false;
	}
	else if(convertStringToDate(approvalDate) > convertStringToDate(endDate))
	{
		alert(" Accept date can not be greater than project End Date");
		return false;
	}	
}
function FIPROMEA_AcceptDateValidation(approvalDate,transCode){

	var sartDate = getLastApprovedDate("FIPRODEF");
	
	var endDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	
	if(convertStringToDate(approvalDate) < convertStringToDate(sartDate))
	{
		alert(" Accept date can not be lesser than previous approved Date");
		return false;
		
	}
	else if(convertStringToDate(approvalDate) > convertStringToDate(endDate))
	{
		alert(" Accept date can not be greater than project End Date");
		return false;
	}	
}
function FIPROANA_AcceptDateValidation(approvalDate,transCode){
	var sartDate = getLastApprovedDate("FIPROMEA");
	var endDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	
	if(convertStringToDate(approvalDate) < convertStringToDate(sartDate))
	{
		alert(" Accept date can not be lesser than previous approved Date");
		return false;
		
	}
	else if(convertStringToDate(approvalDate) > convertStringToDate(endDate))
	{
		alert(" Accept date can not be greater than project End Date");
		return false;
	}	
}
function FIPROIMP_AcceptDateValidation(approvalDate,transCode){
	var sartDate = getLastApprovedDate("FIPROANA");
	var endDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	
	if(convertStringToDate(approvalDate) < convertStringToDate(sartDate))
	{
		alert(" Accept date can not be lesser than previous approved Date");
		return false;
		
	}
	else if(convertStringToDate(approvalDate) > convertStringToDate(endDate))
	{
		alert(" Accept date can not be greater than project End Date");
		return false;
	}	
}

function FIPROCON_AcceptDateValidation(approvalDate,transCode){
	var sartDate = getLastApprovedDate("FIPROIMP");
	var endDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	
	if(convertStringToDate(approvalDate) < convertStringToDate(sartDate))
	{
		alert(" Accept date can not be lesser than previous approved Date");
		return false;
		
	}
	else if(convertStringToDate(approvalDate) > convertStringToDate(endDate))
	{
		alert(" Accept date can not be greater than project End Date");
		return false;
	}	
}


function FIPRODEFGE5L_workFlowLoaded(currentWorkFlowEmpIds,role){

	enableVerifyamount(role);
	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Define");
	
}
function FIPRODEFGE1C_workFlowLoaded(currentWorkFlowEmpIds,role){

	enableVerifyamount(role);
	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Define");
	
}
function FIPRODEF_workFlowLoaded(currentWorkFlowEmpIds,role){
	
		enableVerifyamount(role);
		afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Define"); 
		
	}
function FIPROCLOGE1C_workFlowLoaded(currentWorkFlowEmpIds,role){

	enableFinalamount(role);
	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Closure");
	
}
function FIPROCLOGE5L_workFlowLoaded(currentWorkFlowEmpIds,role){

	enableFinalamount(role);
	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Closure");
	
}
function FIPROIMP_workFlowLoaded(currentWorkFlowEmpIds,role){

	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Improve");
}
function FIPROMEA_workFlowLoaded(currentWorkFlowEmpIds,role){

	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Measure");
}
function FIPROANA_workFlowLoaded(currentWorkFlowEmpIds,role){

	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Analyse");
}
function FIPROCON_workFlowLoaded(currentWorkFlowEmpIds,role){

	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Control");
}
function FIPROCLO_workFlowLoaded(currentWorkFlowEmpIds,role){
	enableFinalamount(role);
	//afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"Control");
}

function enableVerifyamount(role){
	//alert(role +": 1234");
	if("FINANCE" == role.trim().toUpperCase())
	{
		//var amnt = jQuery('#txtKzpmBenefits').val();
		//if( jQuery('#chkKzpmIstangible').is(":checked") &&  amnt.trim() !="" && amnt.length > 0 ){
		
			jQuery("#hdnEnableVerfyAmnt").val("Y");
			enableFields("txtKzpmVerifiedamnt");
			jQuery("#frmProject input[id='mode']").val("fin");
			
			jQuery("#lblVerifyAmount").addClass("mandatory-lbl");
		//}	
	} else if("PBU HEAD" == role.trim().toUpperCase())
		{
		var amnt = jQuery('#txtKzpmBenefits').val();
		var numericAmount = Number(amnt.replace(/,/g, '')); 
			if( jQuery('#chkKzpmIstangible').is(":checked") &&  amnt.trim() !="" && amnt.length > 0 && numericAmount < 500000 ){
			
				jQuery("#hdnEnableVerfyAmnt").val("Y");
				enableFields("txtKzpmVerifiedamnt");
				jQuery("#frmProject input[id='mode']").val("fin");
				
				jQuery("#lblVerifyAmount").addClass("mandatory-lbl");
			}	
		}	
}

function enableFinalamount(role){
	//alert(role +" 1234");
	if("FINANCE" == role.trim().toUpperCase())
	{
		//var amnt = jQuery('#txtKzpmBenefits').val();
		//if( jQuery('#chkKzpmIstangible').is(":checked") &&  amnt.trim() !="" && amnt.length > 0 ){
		
			jQuery("#hdnEnableFinalAmnt").val("Y");
			enableFields("txtKzpmFinalamnt");
			jQuery("#frmProject input[id='mode']").val("fin");
			
			jQuery("#lblFinalAmount").addClass("mandatory-lbl");
		//}	
	} else if("PBU HEAD" == role.trim().toUpperCase())
			{
			var amnt = jQuery('#txtKzpmBenefits').val();
			var numericAmount = Number(amnt.replace(/,/g, '')); 
				if( jQuery('#chkKzpmIstangible').is(":checked") &&  amnt.trim() !="" && amnt.length > 0 && numericAmount < 500000 ){
				
					jQuery("#hdnEnableFinalAmnt").val("Y");
					enableFields("txtKzpmFinalamnt");
					jQuery("#frmProject input[id='mode']").val("fin");
					
					jQuery("#lblFinalAmount").addClass("mandatory-lbl");
				}	
			}
}
function afterWorkFlowLoaded(currentWorkFlowEmpIds,role,stage){
/*alert("currentWorkFlowEmpIds:"+currentWorkFlowEmpIds);
alert("role:"+role);
alert("stage:"+stage);*/
	if(currentWorkFlowEmpIds != "" && currentWorkFlowEmpIds != undefined ){
		jQuery("#hdnFIPRODEFCurEmps").val(currentWorkFlowEmpIds);
		jQuery("#hdnFIPRODEFCurRole").val(role);
		jQuery("#hdnFIPRODEFCurStage").val(stage);
		
	}
	var sCode = getCurrentStageCode() ;
	//alert("sCode:"+sCode);
	setTimeout(function(){
	if( jQuery("#hdnIsCheckList").val()=="Y" && jQuery("#divChecklistLoad").html() == "" ){
		var mode ="";
		currentWorkFlowEmpIds = jQuery("#hdnFIPRODEFCurEmps").val();
		if(  (currentWorkFlowEmpIds == null || currentWorkFlowEmpIds.trim().length == 0 ) && sCode == "P" ){
			if( jQuery("#frmProject input[id='mode']").val() == "view" )
						mode = "view";
		}
			//mode = "view";
			var cStatus=jQuery("#hdnKzpmControlstage").val();
			var dStatus=jQuery("#hdnKzpmDefinestage").val();
			if (cStatus == "C" || dStatus == "P"){
				mode = "view";
			}

		if( jQuery("#frmProject input[id='mode']").val() == "view" )
			mode = "view";
		
		loadCheckList(false,role,stage,mode);
		jQuery("#hdnLdCkLWrkFlowClbk").val("Y");
	}}, 1500);
} 
function FIPRODEF_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){

	var troleName = roleName.replace(/ /g,'').toUpperCase();
	if( jQuery("#hdnIsCheckList").val()=="Y" && roleName!= undefined ){
		if( troleName == "KKCHAMPION"  ){ 
		/*if( status == "A" ) {
			if( ! saveCheckList("Y",status) )// Function is in prjcheckList.jsp
				return false;
		}
		/*else if( ! saveCheckList(status) )
			return false; */
			return checkListValidation(status,record);
		}
		else if( troleName == "PROJECTLEAD"){
			saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
			return false;
		}
	}
	var enVr = jQuery("#hdnEnableVerfyAmnt").val();

	//var oldVamnt = jQuery("#hdnOldVerifiedAmnt").val();
	var vrAmnt = jQuery("#txtKzpmVerifiedamnt").val();
	/*if( enVr == "Y" && ( vrAmnt.trim() == "" || oldVamnt.trim() == "" ) ){
		alert(" Save verified Amount " );
		return false;
	}
	*/	
	if( enVr == "Y" ){
		if( vrAmnt.trim() == "" ){
			alert(" Enter Verif. Amount");
			setFocusOnField("txtKzpmVerifiedamnt");
			return false;
		}
		
		saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
		//alert(" Save verified Amount " );
		return false;
	}
/*	if( enVr == "Y" && oldVamnt != vrAmnt ){
		alert(" Verified amount has changed ");
		
	}
*/	
	
}

function FIPRODEFGE5L_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){

	var troleName = roleName.replace(/ /g,'').toUpperCase();
	if( jQuery("#hdnIsCheckList").val()=="Y" && roleName!= undefined ){
		if( troleName == "KKCHAMPION"  ){ 
		/*if( status == "A" ) {
			if( ! saveCheckList("Y",status) )// Function is in prjcheckList.jsp
				return false;
		}
		/*else if( ! saveCheckList(status) )
			return false; */
			return checkListValidation(status,record);
		}
		else if( troleName == "PROJECTLEAD"){
			saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
			return false;
		}
	}
	var enVr = jQuery("#hdnEnableVerfyAmnt").val();

	//var oldVamnt = jQuery("#hdnOldVerifiedAmnt").val();
	var vrAmnt = jQuery("#txtKzpmVerifiedamnt").val();
	/*if( enVr == "Y" && ( vrAmnt.trim() == "" || oldVamnt.trim() == "" ) ){
		alert(" Save verified Amount " );
		return false;
	}
	*/	
	if( enVr == "Y" ){
		if( vrAmnt.trim() == "" ){
			alert(" Enter Verif. Amount");
			setFocusOnField("txtKzpmVerifiedamnt");
			return false;
		}
		
		saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
		//alert(" Save verified Amount " );
		return false;
	}
/*	if( enVr == "Y" && oldVamnt != vrAmnt ){
		alert(" Verified amount has changed ");
		
	}
*/	
	
}

function FIPRODEFGE1C_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){

	var troleName = roleName.replace(/ /g,'').toUpperCase();
	if( jQuery("#hdnIsCheckList").val()=="Y" && roleName!= undefined ){
		if( troleName == "KKCHAMPION"  ){ 
		/*if( status == "A" ) {
			if( ! saveCheckList("Y",status) )// Function is in prjcheckList.jsp
				return false;
		}
		/*else if( ! saveCheckList(status) )
			return false; */
			return checkListValidation(status,record);
		}
		else if( troleName == "PROJECTLEAD"){
			saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
			return false;
		}
	}
	var enVr = jQuery("#hdnEnableVerfyAmnt").val();

	//var oldVamnt = jQuery("#hdnOldVerifiedAmnt").val();
	var vrAmnt = jQuery("#txtKzpmVerifiedamnt").val();
	/*if( enVr == "Y" && ( vrAmnt.trim() == "" || oldVamnt.trim() == "" ) ){
		alert(" Save verified Amount " );
		return false;
	}
	*/	
	if( enVr == "Y" ){
		if( vrAmnt.trim() == "" ){
			alert(" Enter Verif. Amount");
			setFocusOnField("txtKzpmVerifiedamnt");
			return false;
		}
		
		saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
		//alert(" Save verified Amount " );
		return false;
	}
/*	if( enVr == "Y" && oldVamnt != vrAmnt ){
		alert(" Verified amount has changed ");
		
	}
*/	
	
}


function FIPROCLO_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){
	//alert("before submit close");
	var troleName = roleName.replace(/ /g,'').toUpperCase();
	if( jQuery("#hdnIsCheckList").val()=="Y" && roleName!= undefined ){
		if( troleName == "KKCHAMPION"  ){ 
		/*if( status == "A" ) {
			if( ! saveCheckList("Y",status) )// Function is in prjcheckList.jsp
				return false;
		}
		/*else if( ! saveCheckList(status) )
			return false; */
			return checkListValidation(status,record);
		}
		else if( troleName == "PROJECTLEAD"){
			saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
			return false;
		}
	}
	//alert("before submit close");
	var enVr = jQuery("#hdnEnableFinalAmnt").val();

	//var oldVamnt = jQuery("#hdnOldVerifiedAmnt").val();
	var vrAmnt = jQuery("#txtKzpmFinalamnt").val();
	/*if( enVr == "Y" && ( vrAmnt.trim() == "" || oldVamnt.trim() == "" ) ){
		alert(" Save verified Amount " );
		return false;
	}
	*/	
	if( enVr == "Y" ){
		if( vrAmnt.trim() == "" ){
			alert(" Enter Final Amount");
			setFocusOnField("txtKzpmFinalamnt");
			return false;
		}
		
		saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
		//alert(" Save verified Amount " );
		return false;
	}
/*	if( enVr == "Y" && oldVamnt != vrAmnt ){
		alert(" Verified amount has changed ");
		
	}
*/	
	
}

function FIPROCLOGE5L_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){

	var troleName = roleName.replace(/ /g,'').toUpperCase();
	if( jQuery("#hdnIsCheckList").val()=="Y" && roleName!= undefined ){
		if( troleName == "KKCHAMPION"  ){ 
		/*if( status == "A" ) {
			if( ! saveCheckList("Y",status) )// Function is in prjcheckList.jsp
				return false;
		}
		/*else if( ! saveCheckList(status) )
			return false; */
			return checkListValidation(status,record);
		}
		else if( troleName == "PROJECTLEAD"){
			saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
			return false;
		}
	}
	var enVr = jQuery("#hdnEnableFinalAmnt").val();

	//var oldVamnt = jQuery("#hdnOldVerifiedAmnt").val();
	var vrAmnt = jQuery("#txtKzpmFinalamnt").val();
	/*if( enVr == "Y" && ( vrAmnt.trim() == "" || oldVamnt.trim() == "" ) ){
		alert(" Save verified Amount " );
		return false;
	}
	*/	
	if( enVr == "Y" ){
		if( vrAmnt.trim() == "" ){
			alert(" Enter Final. Amount");
			setFocusOnField("txtKzpmFinalamnt");
			return false;
		}
		
		saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
		//alert(" Save verified Amount " );
		return false;
	}
/*	if( enVr == "Y" && oldVamnt != vrAmnt ){
		alert(" Verified amount has changed ");
		
	}
*/	
	
}

function FIPROCLOGE1C_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){

	var troleName = roleName.replace(/ /g,'').toUpperCase();
	if( jQuery("#hdnIsCheckList").val()=="Y" && roleName!= undefined ){
		if( troleName == "KKCHAMPION"  ){ 
		/*if( status == "A" ) {
			if( ! saveCheckList("Y",status) )// Function is in prjcheckList.jsp
				return false;
		}
		/*else if( ! saveCheckList(status) )
			return false; */
			return checkListValidation(status,record);
		}
		else if( troleName == "PROJECTLEAD"){
			saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
			return false;
		}
	}
	var enVr = jQuery("#hdnEnableFinalAmnt").val();

	//var oldVamnt = jQuery("#hdnOldVerifiedAmnt").val();
	var vrAmnt = jQuery("#txtKzpmFinalamnt").val();
	/*if( enVr == "Y" && ( vrAmnt.trim() == "" || oldVamnt.trim() == "" ) ){
		alert(" Save verified Amount " );
		return false;
	}
	*/	
	if( enVr == "Y" ){
		if( vrAmnt.trim() == "" ){
			alert(" Enter Final. Amount");
			setFocusOnField("txtKzpmFinalamnt");
			return false;
		}
		
		saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
		//alert(" Save verified Amount " );
		return false;
	}
/*	if( enVr == "Y" && oldVamnt != vrAmnt ){
		alert(" Verified amount has changed ");
		
	}
*/	
	
}

var uploader = new qq.FileUploader({
    element:   document.getElementById("fileUpload"),
    name:"Upload",
    uploadLabelName:"Select Proj. PPT",
    action: 'file_upload.dcm',
	params: {},
	numFiles:1,
	allowedExtensions: [],
	sizeLimit: 65242880, // max size
	minSizeLimit: 1, // min size
	debug: false,
	onSubmit: function(id, fileName){
		if( jQuery("#fileUpload .qq-upload-list").length>0){
			jQuery("#fileUpload .qq-upload-list").html('');
		}
	},
	onProgress: function(id, fileName, loaded, total){},
	onComplete: function(id, fileName, responseJSON){
		jQuery("#fileUpload .qq-upload-failed-text").hide();},
	onCancel: function(id, fileName){},
	messages: {
		 sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
         minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
         emptyError: "{file} is empty, please select some other file."
	},
	showMessage: function(messages){
		alert(messages);
		}
});
function FIPROMEA_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){
	/*if( jQuery("#hdnIsCheckList").val()=="Y") {// && status == "A" ) {
		if( !saveCheckList("Y",status) )// Function is in prjcheckList.jsp
			return false;
	}*/	
	return checkListValidation(status,record);
}

function FIPROANA_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){
	/*if( jQuery("#hdnIsCheckList").val()=="Y"){// && status == "A") {
		if( ! saveCheckList("Y",status) )// Function is in prjcheckList.jsp
			return false;
	}*/	
	return checkListValidation(status,record);
}
function FIPROIMP_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){
	/*(if( jQuery("#hdnIsCheckList").val()=="Y" ) { //&& status == "A") {
		if( ! saveCheckList("Y",status) ) // Function is in prjcheckList.jsp
			return false;
	}*/	
	return checkListValidation(status,record);
}

function FIPROCON_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){
	
	return checkListValidation(status,record);
}

/*function FIPROCLO_beforeSubmit(refId,empId,flId,refRoleId,status,roleName,record){
	var enVr = jQuery("#hdnEnableVerfyAmnt").val();
	
	//var oldVamnt = jQuery("#hdnOldVerifiedAmnt").val();
	var vrAmnt = jQuery("#txtKzpmVerifiedamnt").val();
	if( enVr == "Y" && ( vrAmnt.trim() == "" || oldVamnt.trim() == "" ) ){
		alert(" Save verified Amount " );
		return false;
	}
		
	if( enVr == "Y" ){
		if( vrAmnt.trim() == "" ){
			alert(" Enter Verif. Amount");
			setFocusOnField("txtKzpmVerifiedamnt");
			return false;
		}
		
		saveForm("frmProject","projectsprotoview_save.prpo?&type=workflow&gridId="+record.gridId+"&rowId="+record.rowId,"");
		//alert(" Save verified Amount " );
		return false;
	}
}*/
function checkListValidation(status,record){
	if( jQuery("#hdnIsCheckList").val()=="Y" ) { //&& status == "A") {
		/*if( ! saveCheckList("Y",status) ) // Function is in prjcheckList.jsp
			return false;
		 */
		var retVal = saveCheckList("Y",status,record);
		
		if( retVal == null){
			return false;
		}else if(! retVal){
			alert("Check Lists are not completed");
			return false;
		}else{
			/*var r=confirm("All Check points Are Completed! Click Yes to Approval Submition ");
			var chkListValid = "N";
			if(r==true)
				chkListValid ="Y"; */
			return true;
		}
	}	
}
function Define_CheckList_SaveSucsessCalback(resltObj,curStage){

	if( resltObj.apprMode != undefined){
	if(resltObj.apprMode == "S")
		checkListSuccessCallbk(resltObj,curStage);
	else 
		checkListSuccessCallbk_workflowsave(resltObj,curStage);
	}
}
function Measure_CheckList_SaveSucsessCalback(resltObj,curStage){
//	alert(" curStage ");
	checkListSuccessCallbk_workflowsave(resltObj,curStage);
}
function Analyse_CheckList_SaveSucsessCalback(resltObj,curStage){
//	alert(" curStage ");
	checkListSuccessCallbk_workflowsave(resltObj,curStage);
}
function Improve_CheckList_SaveSucsessCalback(resltObj,curStage){
//	alert(" curStage ");
	checkListSuccessCallbk_workflowsave(resltObj,curStage);
}
function Control_CheckList_SaveSucsessCalback(resltObj,curStage){
//	alert(" curStage ");
	checkListSuccessCallbk_workflowsave(resltObj,curStage);
}
function checkListSuccessCallbk(resultObj,cStage){
	
	var isAllChkYes =resultObj.isAllYes;
	var chkListValid = "N";
	if( isAllChkYes == "true" || isAllChkYes == true)
		chkListValid = "Y";
	jQuery("#hdnIsCheckListChk").val("Y");
	saveForm("frmProject","projectsprotoview_save.prpo?chkListValid="+chkListValid);
}
function checkListSuccessCallbk_workflowsave(resultObj,cStage){
	if( resultObj.record != undefined && resultObj.record != null ) {
		workFlowSubmit(resultObj.record,false);
	}
}

function addMileStone(){
	if(jQuery("#mode").val()!="view"){
		var mode =  loadStagesMaic("add","");
		//alert("mode "+ mode);
		if (mode=="add" || mode=="modify" || mode=="view"){	
			var currStage=getCurrentStage();
			//alert("currStage "+ currStage);
			var keyId="";
			if(currStage.trim().length>0){
				keyId=getCurrentStageKeyId(currStage);
				//alert("keyId "+ keyId);
			}
			LoadPopUp("divMilestonePop", "milestone_view.prpo?q=2&mode="+mode+"&Keyid="+keyId, true,"90%","80%","60px","2%", "multiSelectOk_Callback","MilesStone"," ",true);
		}
		else{
			setTimeout(function() {
				showCommonErrorMsg(mode);
			}, 200);
			div_err();
			return false;
		}
	}
}

function getCurrentStageKeyId(currStage){
	var jqGridId="fourgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var keyid ="";
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=parseInt(i)+1;
			var mileStoneStage = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Stages');	
			//alert("mileStoneStage:"+mileStoneStage);
			//alert("currStage:"+currStage);
			if(mileStoneStage==currStage){	
				keyid = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Keyid');		
				return keyid;
			}			
		}
	}
	
	return keyid;
}

function getCurrentStage(){	
	var dStatus=jQuery("#hdnKzpmDefinestage").val();
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	var xStatus=jQuery("#hdnKzpmClosurestage").val();
	var currStage="";

	if( dStatus =="-" || dStatus == "P" || dStatus == "E" || dStatus == "R"){
		currStage="Define";
		if(dStatus=="E")
			jQuery("#hdnIsDefineStage").val("Y");
	}	
	else if(mStatus=="-" || mStatus=="P"  || mStatus=="I" || mStatus == "E" ||  mStatus=="R"){
		currStage="Measure";
	}
	else if(aStatus=="-" || aStatus=="P" || aStatus=="I" || aStatus == "E" || aStatus=="R" ){
		currStage="Analyse";
	}
	else if(iStatus=="-" || iStatus=="L" || iStatus=="P" || iStatus=="I" || iStatus == "E" || iStatus=="R"){
		currStage="Improve";
	}
	else if(cStatus=="-" || cStatus=="L" ||  cStatus=="P" || cStatus=="I" || cStatus == "E" || cStatus=="R"){
		currStage="Control";
	}
	else if(cStatus == "C" && ( xStatus == "-" || xStatus=="L" || xStatus == "P" || xStatus=="I" || xStatus == "E" || iStatus=="R") )
		currStage="Closure";
	return currStage;
}

function getCurrentStageCode(){	
	var dStatus=jQuery("#hdnKzpmDefinestage").val();
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	var xStatus=jQuery("#hdnKzpmClosurestage").val();
	var currStage="";

	if( dStatus =="-" || dStatus == "P" || dStatus == "E" || dStatus == "R")
		currStage= dStatus;
	else if(mStatus=="-" || mStatus=="P"  || mStatus=="I" || mStatus == "E" ||  mStatus=="R"){
		currStage=mStatus;
	}
	else if(aStatus=="-" || aStatus=="P" || aStatus=="I" || aStatus == "E" || aStatus=="R" ){
		currStage=aStatus;
	}
	else if(iStatus=="-" || iStatus=="L" || iStatus=="P" || iStatus=="I" || iStatus == "E" || iStatus=="R"){
		currStage=iStatus;
	}
	else if(cStatus=="-" || cStatus=="L" ||  cStatus=="P" || cStatus=="I" || cStatus == "E" || cStatus=="R"){
		currStage=cStatus;
	}
	else if(cStatus == "C" && ( xStatus == "-" || xStatus=="L" || xStatus == "P" || xStatus=="I" || xStatus == "E" || iStatus=="R") )
		currStage=xStatus;
	return currStage;
}


function setAuthorizationTab(){
	var cStage = getCurrentStage();
	
	if( cStage.trim() != "" && cStage.trim().length > 0 )
		jQuery("#tabAuthorization").tabs('select',cStage);
}
function frmProject_beforeDelete(){
	return false;
}

function frmProject_beforeSubmit()
{
	var mode = jQuery("#frmProject input[id='mode']").val();
	if ( mode == "view" || mode == "approval"){
		return false;
	}	
	var fileName =jQuery("#fileUpload .qq-upload-list").html(); 
	var isDef =  jQuery("#hdnIsDefineStage").val();
	var isChkl =  jQuery("#hdnIsCheckList").val();
	var sC = jQuery("#hdnIsCheckListChk").val();
	 var tangible= jQuery('#chkKzpmIstangible').val();
	 var amnt=jQuery('#txtKzpmBenefits').val();
	// alert(tangible +" tangible "+amnt +" fileName "+fileName);
		//alert(fileName+"  fleNames ");
	/*	if(mode=="define" && tangible=="Y" && amnt>=500000 ){
			alert(12345);
		if(fileName==" "||fileName==null||fileName.length==0){
			alert("Upload the Finance Sheet  ");
			return false;
		 }
		}*/
		//alert("mode:"+mode);  
		//alert("sC:"+sC); 
		//alert("isChkl:"+isChkl); 
		//alert("isDef:"+isDef);
		//alert("Def:"+jQuery("#hdnKzpmDefinestage").val());
	if( mode != "modifycreate" && sC != "Y"  && isChkl.toUpperCase() == "Y" && isDef.toUpperCase() == "Y" && ( jQuery("#hdnKzpmDefinestage").val() == "-" || jQuery("#hdnKzpmDefinestage").val() == "E") )
	{
		
			
	  //alert("Inside Check If");
		var retVal = saveCheckList("Y","A");
		//alert("retVal:"+retVal);
		if( retVal == null){
			return false;
		}else if(!retVal){	
			return "&chkListValid=N";
		}else{
			var r=confirm("All Check points are Completed! Click Ok to Approval Submition ");
			var chkListValid = "N";
			if(r==true)
				chkListValid ="Y";
			return "&chkListValid="+chkListValid;
		}	
			
	}
	/*else if(isChkl.toUpperCase() == "Y"){
		return "&chkListValid=Y";
	}*/
	jQuery("#hdnIsCheckListChk").val(' ');
	var isDef =  jQuery("#hdnIsDefineStage").val();
	var isClo =  jQuery("#hdnIsClosure").val();
	 var tangible= jQuery('#chkKzpmIstangible').val();
	 var amnt=jQuery('#txtKzpmBenefits').val();
	 var isFinalAmt=jQuery('#hdnEnableFinalAmnt').val();
	//alert("isClo:"+isClo);
	//alert("tangible:"+tangible);
	//alert("amnt:"+amnt);
	if( isClo == "Y"  || isClo == "y" && amnt > 0 && tangible=="Y" && isFinalAmt != "Y" ){ 
		//alert(1234+" isDefisDef "+isClo);
		var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	if(fileName==" "||fileName==null||fileName.length==0 && isClo == "y"  || isClo == "Y"){
		alert("Upload the Project PPT  ");
		return false;
	 }
	else{
		var url= "file_save.dcm?q=2&notModify=N&filename="+fileName+"&docNo="+masterkeyid+"&docType=PRO&dmdmTitle=Closure Stage PPT ";
		processAjaxCalls(url,"","filecallsuccessCallBack","errorCallBack");
		return true;
	}
	}
	
	return true;
}


function disableProject(){
	readOnlyFields("txtKzpmProjectname");
	readOnlyFields("txtKzpmArea");
	readOnlyFields("txtKzpmBenefits");
	readOnlyFields("txtKzpmSavings");
	readOnlyFields("cmbKzpmProjectmetrics");
	readOnlyFields("txtKzpmProblemstatement");
	readOnlyFields("txtKzpmGoalobj");
	readOnlyFields("txtKzpmScopeconst");
	readOnlyFields("cmbKzpmProjectchamp");
	readOnlyFields("txtKzpmBusinesscase");
	readOnlyFields("dteKzpmStartdate");
	readOnlyFields("dteKzpmEnddate");
	readOnlyFields("cmbKzpmImprCategory");
	readOnlyFields("txtKzpmWave");
	readOnlyFields("chkKzpmIstangible");
	readOnlyFields("chkKzpmIsintangible");
	readOnlyFields("cmbKzpmBelt");
}

function loadComplKPI() { 
	var define=jQuery("#hdnKzpmDefinestage").val();
	if(define=="C"){
		// jQuery("#DefineStage").attr("disabled","disabled");		
		 
		 setTimeout(function() {
			 jQuery('.divDefApproval').before('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:90%;background:red;"> </div>');
		}, 500);
	 }
}

function projectDefineMandatory(){
	jQuery("#lblKzpmProjectmetrics").addClass("mandatory-lbl");
	jQuery("#lblKzpmProblemstatement").addClass("mandatory-lbl");

	jQuery("#lblKzpmBusinesscase").addClass("mandatory-lbl");
	jQuery("#lblKzpmGoalobj").addClass("mandatory-lbl");
	
	//jQuery("#lblKzpmSavings").addClass("mandatory-lbl");	
}
function resourcesgrid_selectRow(rowId)
{
	var jqGridId="resourcesgrid";	
	var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtKprlKeyid');	
	
	if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyId.trim().length>0))
	{		
		//alert("un");	
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','Y');
	}
	else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
		//alert("check");
		var hrsEstimate = getFieldValue('txtKprlHrsestimate_'+jqGridId+'_'+rowId);
		if(hrsEstimate.trim().length==0)
     		setFieldValue('txtKprlHrsestimate_'+jqGridId+'_'+rowId,"");
		jQuery("#cmbEmpName_"+jqGridId+"_"+rowId).attr('disabled','disabled');
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','N');			
	}
	else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete',' ');}
}


function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
	 	var emptyItem =[{txtKprlKeyid:" ",hdnIsDelete:"",txtKprlEmpmKeyid:" ",cmbEmpName:" ",txtKprlLeadMemb:" ",cmbLeadMemb:" ",txtKprlRoleKeyid:" ",txtKprlHrsestimate:" "}];
		jQuery("#resourcesgrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
			lastRow = row[i];

		var emptyItem =[{txtKprlKeyid:" ",hdnIsDelete:"",txtKprlEmpmKeyid:" ",cmbEmpName:" ",txtKprlLeadMemb:" ",cmbLeadMemb:" ",txtKprlRoleKeyid:" ",txtKprlHrsestimate:" "}];
		jQuery("#resourcesgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}
function saveResources()
{		  
	var flg=false;
	var errText="";
	var jqGridId="resourcesgrid";
	var gridval=getGridSelectArray('resourcesgrid');  
    var gridData  = '&resourcedtls='+gridval;	
    //alert('gridData:'+gridData);
    var gridvaldel=getGridUnselectedKeyids('resourcesgrid','txtKprlKeyid','hdnIsDelete');
    gridData += '&resourcedtlsdel='+gridvaldel;
    //alert('gridData:'+gridData); 
    if (gridval.trim().length==0 && gridvaldel.trim().length==0) {
    	setTimeout(function() {
			showCommonErrorMsg('No Resources Selected To Save');
		}, 200);
		div_err();		
        return false;
    } 
    //alert('gridval:'+gridval);
    return gridData;
}
function saveDMAICStatus(){
	var flg=false;
	var errText="";
	var jqGridId="dmaicgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=	parseInt(i)+1;
			if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
				flg=true;
			}	
		}
	}
	
	if (flg==false){
		setTimeout(function() {
			showCommonErrorMsg('Select Any DMAIC Verification Details');
		}, 200);
		div_err();		
        return false;
	}
	
	var gridval=getGridSelectArray('dmaicgrid');  
    var gridData  = '&projectDmaic='+gridval;	
    //alert('gridData:'+gridData);
    var gridvaldel=getGridUnselectedKeyids('dmaicgrid','txtKpdsKeyid','hdnIsDelete');
    gridData += '&projectDmaiceDel='+gridvaldel;
    
    if (gridval.trim().length<=0 && gridvaldel.trim().length<=0) {
    	setTimeout(function() {
			showCommonErrorMsg("Select Any Stage To Save");
		}, 200);
		div_err();		
        return false;
    } 
    //alert('gridval:'+gridval);   
    
	return gridData; 	
}

function getWorkFlowStage(){
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	//var jqGridId="dmaicgrid";
	
	if (masterkeyid.trim().length>0){	
		var fromDate = "";
		var toDate = "";	
		var mStatus=jQuery("#hdnKzpmMeasurestage").val();
		var aStatus=jQuery("#hdnKzpmAnalysestage").val();
		var iStatus=jQuery("#hdnKzpmImprovestage").val();
		var cStatus=jQuery("#hdnKzpmControlstage").val();
		var flid = jQuery("#frmProject input[id='flid']").val();
		//alert('flid:'+flid);	
		var curtStage = getCurrentStage();
		var enC = "N",enM ="N",enA ="N",enI="N";
		if( curtStage == "Control")
			enC ="Y";
		else if(curtStage == "Measure")
			enM="Y";
		else if(curtStage == "Analyse")
			enA="Y";
		else if(curtStage == "Improve")
			enI="Y";	
		
		var mode = jQuery("#frmProject input[id='mode']").val();

		if( mode == "view" ){

			enC = "N";
			enI = "N";
			enA = "N";
			enM = "N";
		}
		//var rowId = getStageRowId("Control");
	//	if(cStatus=="W" || cStatus=="R" || cStatus=="E" || cStatus=="L"){
			//fromDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'dteKpdsVerifieddate');	

			//fromDate=fromDate.replace(" 00:00:00","").trim();	
			//var stage=jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKpdsStage');
		//MADHAN	//workFlow("divConApproval",false,"FIPROCON", masterkeyid, "PROCO",flid,fromDate,toDate,enC);	
	//	}
		//	rowId = getStageRowId("Improve");
	//	else if(iStatus=="W" || iStatus=="R" || iStatus=="E" || iStatus=="L"){
			//fromDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'dteKpdsVerifieddate');					

			//fromDate=fromDate.replace(" 00:00:00","").trim();	
			
		//MADHAN	//	workFlow ("divImpApproval",false,"FIPROIMP", masterkeyid, "PROIM",flid,fromDate,toDate,enI);	
	//	}
	//	else if(aStatus=="W" || aStatus=="R" || aStatus=="E" || aStatus=="L" ){
			//rowId = getStageRowId("Analyse");
			//fromDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'dteKpdsVerifieddate');					
			//fromDate=fromDate.replace(" 00:00:00","").trim();

		//MADHAN	//	workFlow ("divAnlApproval",false,"FIPROANA", masterkeyid, "PROAN",flid,fromDate,toDate,enA);			
	//	}
	//	else if(mStatus=="W" || mStatus=="R" || mStatus=="E" || mStatus=="L" ){
			//rowId = getStageRowId("Measure");
			//fromDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'dteKpdsVerifieddate');

			//fromDate=fromDate.replace(" 00:00:00","").trim();	

		//MADHAN	//	workFlow ("divMeaApproval",false,"FIPROMEA", masterkeyid, "PROME",flid,fromDate,toDate,enM);	
	//	}
		/*else{
			workFlow ("divMAICApproval",false,"FIPRO", masterkeyid, "PRO",flid);
		}*/
	}	
}

function workFlow_Load_callbackOnSuccess(result){
	//alert("workFlow_Load_callbackOnSuccess");	
	//jQuery('#grdWorkFlowAppr').setGridWidth("100%%");
	//jQuery('#grdWorkFlowAppr').setGridHeight("20%%");
	/*setTimeout(function(){
		jQuery('#grdWorkFlowAppr').setGridWidth(1049);
		jQuery('#grdWorkFlowAppr').setGridHeight(150);
	},1150);*/
}

function frmProject_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth("frmProject",'585px');
	//jQuery("#linfrmProjectCell").remove();
	//jQuery("#frmProject div[id=dispFunctionalLoc]").css('width','465px');
	jQuery("#cmbKzpmFlid").val(result.flId);	
	reloadCombo("frmProject","cmbKzpmProjectchamp","employee.commonFilter?isPbuHead=Y&flid="+result.flId+"&loginEmpshow=false");
	reloadCombo("frmProject","cmbKzpmProjectmetrics","combo_KPIIndicator.prpo?flid="+result.flId);
}
function frmProjectcmbKzpmProjectchamp_onLoadSuccess() {
	var fromDate = jQuery("#dteKzpmStartdate").datebox("getValue");
	var Definetargetdate = jQuery("#dteKzpmDefinetargetdate").datebox("getValue");
	var Measuretargetdate = jQuery("#dteKzpmMeasuretargetdate").datebox("getValue");
	var Analysetargetdate = jQuery("#dteKzpmAnalysetargetdate").datebox("getValue");
	var Improvetargetdate = jQuery("#dteKzpmImprovetargetdate").datebox("getValue");
	var Controltargetdate = jQuery("#dteKzpmControltargetdate").datebox("getValue");
	var Closuretargetdate = jQuery("#dteKzpmClosuretargetdate").datebox("getValue");
	
	if (fromDate=='' || fromDate==' ' || fromDate==null){
		fillWithCurrentDate("dteKzpmStartdate");
		var currentDate = getCurrentDate();
		dteKzpmStartdate_onSelect(currentDate);
	}
		
	
	/*if (Definetargetdate=='' || Definetargetdate==' ' || Definetargetdate==null)
	      fillWithCurrentDate("dteKzpmDefinetargetdate");
	
	if (Measuretargetdate=='' || Measuretargetdate==' ' || Measuretargetdate==null)
	      fillWithCurrentDate("dteKzpmMeasuretargetdate");
		
	if (Analysetargetdate=='' || Analysetargetdate==' ' || Analysetargetdate==null)
	      fillWithCurrentDate("dteKzpmAnalysetargetdate");

	if (Improvetargetdate=='' || Improvetargetdate==' ' || Improvetargetdate==null)
	      fillWithCurrentDate("dteKzpmImprovetargetdate");

	if (Controltargetdate=='' || Controltargetdate==' ' || Controltargetdate==null)
	      fillWithCurrentDate("dteKzpmControltargetdate");

	if (Closuretargetdate=='' || Closuretargetdate==' ' || Closuretargetdate==null)
	      fillWithCurrentDate("dteKzpmClosuretargetdate");*/

	setComboDefaultValue("frmProject", "cmbKzpmProjectchamp");
}

 function AddDays(date,days){
	let newDate = new Date(date);
		   
	newDate.setDate(newDate.getDate() + days);

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
				            months[newDate.getMonth()] + '-' +
				            newDate.getFullYear();
	return formatted;
 }
 
 function date_difference(startDate,endDate){
	if(startDate && endDate){

	    // Convert to JS Date object
	    var sDate = new Date(startDate);
	    var eDate = new Date(endDate);

	    // Difference in milliseconds
	    var diffMs = eDate - sDate;
       //alert("diffMs:"+diffMs);
	    // Convert to days
	    var diffDays = diffMs / (1000 * 60 * 60 * 24);
		//alert("diffDays:"+diffDays);
		return diffDays;
    }
	return 0;
 }
 
/* function formatDate(d) {
     let dd = String(d.getDate()).padStart(2, '0');
     let mm = String(d.getMonth() + 1).padStart(2, '0');
     let yyyy = d.getFullYear();
	 const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
     return dd + '-' + months[mm] + '-' + yyyy; // match your datebox format
 }*/
 function formatDate(date){
 	let newDate = new Date(date);
 		   
 	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

 	// Format DD-MMM-YYYY
     let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
 				            months[newDate.getMonth()] + '-' +
 				            newDate.getFullYear();
 	return formatted;
  }
 
 function dteKzpmStartdate_onSelect(date){
 	//alert(date);
 	  let  newDate = AddDays(date,15);	
	  jQuery('#dteKzpmDefinetargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);	
 	 jQuery('#dteKzpmMeasuretargetdate').datebox('setValue', newDate);
 	 newDate = AddDays(newDate,15);
 	 jQuery('#dteKzpmAnalysetargetdate').datebox('setValue', newDate);
 	 newDate = AddDays(newDate,15);
 	 jQuery('#dteKzpmImprovetargetdate').datebox('setValue', newDate);
 	 newDate = AddDays(newDate,15);
 	 jQuery('#dteKzpmControltargetdate').datebox('setValue', newDate);
 	 newDate = AddDays(newDate,15);
 	 jQuery('#dteKzpmClosuretargetdate').datebox('setValue', newDate);
	 jQuery('#dteKzpmEnddate').datebox('setValue', newDate);
 }

function dteKzpmDefinetargetdate_onSelect(date){
	alert(date);
	 var startDate = jQuery('#dteKzpmStartdate').datebox('getValue');
	 var dTargetDate =AddDays(date,0); //jQuery('#dteKzpmDefinetargetdate').datebox('getValue');
	 alert(startDate);
	 alert(dTargetDate);
	 alert(date_difference(startDate,dTargetDate));
	 //if(compareDateTime(dTargetDate,startDate) > 0 ){
	if(date_difference(startDate,dTargetDate) < 2){
		popupCommonErrorMsg('Define Target Date should be at least two days after the  Start Date');
		date = AddDays(startDate,15);
		alert(date);
		//jQuery('#dteKzpmDefinetargetdate').datebox('setValue', date);
	setTimeout(function () {
		            jQuery('#dteKzpmDefinetargetdate')
		                .datebox('setValue', date);
		        }, 10);
		}
	  let  newDate = AddDays(date,15);		
	 jQuery('#dteKzpmMeasuretargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmAnalysetargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmImprovetargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmControltargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmClosuretargetdate').datebox('setValue', newDate);
	 jQuery('#dteKzpmEnddate').datebox('setValue', newDate);
}

function dteKzpmMeasuretargetdate_onSelect(date){
	//alert(date);
	var dTargetDate = jQuery('#dteKzpmDefinetargetdate').datebox('getValue');
	 var mTargetDate = AddDays(date,0); // jQuery('#dteKzpmMeasuretargetdate').datebox('getValue');
	 //if(compareDateTime(mTargetDate,dTargetDate) > 0 ){
		if(date_difference(dTargetDate,mTargetDate) < 2){
		popupCommonErrorMsg('Measure Target Date should be at least two days after the Define Target Date');
		date = AddDays(dTargetDate,15);
		//jQuery('#dteKzpmMeasuretargetdate').datebox('setValue', date);
		setTimeout(function () {
				            jQuery('#dteKzpmMeasuretargetdate')
				                .datebox('setValue', date);
				        }, 10);
		}
	  let  newDate = AddDays(date,15);		
	 jQuery('#dteKzpmAnalysetargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmImprovetargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmControltargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmClosuretargetdate').datebox('setValue', newDate);
	 jQuery('#dteKzpmEnddate').datebox('setValue', newDate);
}

function dteKzpmAnalysetargetdate_onSelect(date){
	//alert(date);
	
	var mTargetDate = jQuery('#dteKzpmMeasuretargetdate').datebox('getValue');
		 var aTargetDate =AddDays(date,0); //jQuery('#dteKzpmAnalysetargetdate').datebox('getValue');
		 //if(compareDateTime(aTargetDate,mTargetDate) > 0 ){
			if(date_difference(mTargetDate,aTargetDate) < 2){
			popupCommonErrorMsg('Analyse Target Date should be at least two days after the Measure Target Date');
			date = AddDays(mTargetDate,15);
			//jQuery('#dteKzpmAnalysetargetdate').datebox('setValue', date);
			setTimeout(function () {
							            jQuery('#dteKzpmAnalysetargetdate')
							                .datebox('setValue', date);
							        }, 10);
			}
	  let  newDate = AddDays(date,15);		
	 jQuery('#dteKzpmImprovetargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmControltargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmClosuretargetdate').datebox('setValue', newDate);
	 jQuery('#dteKzpmEnddate').datebox('setValue', newDate);
}

function dteKzpmImprovetargetdate_onSelect(date){
	//alert(date);
	var aTargetDate = jQuery('#dteKzpmAnalysetargetdate').datebox('getValue');
	var iTargetDate = AddDays(date,0); //jQuery('#dteKzpmImprovetargetdate').datebox('getValue');
	//if(compareDateTime(iTargetDate,aTargetDate) > 0 ){
	if(date_difference(aTargetDate,iTargetDate) < 2){
		popupCommonErrorMsg('Improve Target Date should be at least two days after the Analyse Target Date');
		date = AddDays(aTargetDate,15);
		setTimeout(function () {
									            jQuery('#dteKzpmImprovetargetdate')
									                .datebox('setValue', date);
									        }, 10);
		//jQuery('#dteKzpmImprovetargetdate').datebox('setValue', date);
	}
	  let  newDate = AddDays(date,15);		
	 jQuery('#dteKzpmControltargetdate').datebox('setValue', newDate);
	 newDate = AddDays(newDate,15);
	 jQuery('#dteKzpmClosuretargetdate').datebox('setValue', newDate);
	 jQuery('#dteKzpmEnddate').datebox('setValue', newDate);
}

function dteKzpmControltargetdate_onSelect(date){
	//alert(date);
	var iTargetDate = jQuery('#dteKzpmImprovetargetdate').datebox('getValue');
	var cTargetDate = AddDays(date,0); //jQuery('#dteKzpmControltargetdate').datebox('getValue');
		//if(compareDateTime(cTargetDate,iTargetDate) > 0 ){
		if(date_difference(iTargetDate,cTargetDate) < 2){
			popupCommonErrorMsg('Control Target Date should be at least two days after the Improve Target Date');
			date = AddDays(iTargetDate,15);
			setTimeout(function () {  jQuery('#dteKzpmControltargetdate').datebox('setValue', date); }, 10);
			//jQuery('#dteKzpmControltargetdate').datebox('setValue', date);
		}
	  let  newDate = AddDays(date,15);		
	 jQuery('#dteKzpmClosuretargetdate').datebox('setValue', newDate);
	 jQuery('#dteKzpmEnddate').datebox('setValue', newDate);
}

function dteKzpmClosuretargetdate_onSelect(date){
	var cTargetDate = jQuery('#dteKzpmControltargetdate').datebox('getValue');
	var xTargetDate = AddDays(date,0); // jQuery('#dteKzpmClosuretargetdate').datebox('getValue');
			//if(compareDateTime(xTargetDate,cTargetDate) > 0 ){
			if(date_difference(cTargetDate,xTargetDate) < 2){
				popupCommonErrorMsg('Closure Target Date should be at least two days after the Control Target Date');
				date = AddDays(cTargetDate,15);
				setTimeout(function () {  jQuery('#dteKzpmClosuretargetdate').datebox('setValue', date); }, 10);
				//jQuery('#dteKzpmClosuretargetdate').datebox('setValue', date);
			}
			let  newDate = AddDays(date,0);
			jQuery('#dteKzpmEnddate').datebox('setValue', newDate);
}

function dteKzpmEnddate_onSelect(date){
	var xTargetDate = jQuery('#dteKzpmClosuretargetdate').datebox('getValue');
	var endDate =AddDays(date,0);// jQuery('#dteKzpmEnddate').datebox('getValue');
			if(compareDateTime(endDate,xTargetDate) > 0 ){
				popupCommonErrorMsg(' End Date Should Not Less than Closure Target Date');
				//date = AddDays(cTargetDate,15);
				setTimeout(function () {  jQuery('#dteKzpmEnddate').datebox('setValue', xTargetDate); }, 10);
				//jQuery('#dteKzpmEnddate').datebox('setValue', xTargetDate);
			}
}

function frmProject_successsCallback(result)
{
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	if (masterkeyid.trim().length<=0){
		//alert("Project Number Is "+result.successData.kzpmKeyid);
		jQuery("#hdnKzpmKeyid").val(result.successData.kzpmKeyid);
		jQuery("#txtKzpmProjectno").val(result.successData.kzpmKeyid);		
	}		
	if(result.successData.Type=="fileMng"){
		var frMode = jQuery("#mode").val();
		fileManagerPopUp(result.successData.kzpmKeyid,"KZP","","","",frMode);	
	}
	else if(result.successData.Type=="workflow"){
		workFlowSubmit(result.successData.record,false);
		//clearForm("frmProject");
		//loadFunctionalLocation("projectfun","functionalLoc.kkpp","projectfunLocation","frmProject"," ");
	}
	else if(result.successData.mode=="define" && result.successData.checkList=="Y"){
		jQuery("#hdnFIPRODEFCurEmps").val(" ");
		var role = jQuery("#hdnFIPRODEFCurRole").val();
		var stage = jQuery("#hdnFIPRODEFCurStage").val();
		loadCheckList(false,role,stage,"view");
	}else if(result.successData.Type=="project"){
		navigateToPrevForm();
	}
	jQuery("#projectgrid").trigger("reloadGrid"); 
	//alert(12345);
}

function frmProject_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	clearForm("frmProject");
	loadFunctionalLocation("projectfun","functionalLoc.kkpp","projectfunLocation","frmProject"," ");
}

function dmaicgrid_selectRow(rowId)
{	
	var jqGridId="dmaicgrid";
	var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtKpdsKeyid');
	var dateCtrl="dteKpdsVerifieddate_dmaicgrid_"+rowId;
	var stage=jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKpdsStage');
	jQuery("#"+dateCtrl).datebox({  	   
	onSelect:function(recordid)
		{
			//alert("dateCtrl:"+dateCtrl+"stage:"+stage);
			isValidVerifiedDate(dateCtrl,rowId,stage);
		} 
	});
	if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyId.trim().length>0))
	{			
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','Y');
	}
	else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
		//alert('checked==true');
		jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','N');
	}
	else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete',' ');}
		
}
function isValidVerifiedDate(dateCtrl,ctrlRowId,stage){
	var verifiedDate = getFieldValue(dateCtrl);	
	var currentDate = getServerDateTime();	
	var fromDate = jQuery("#dteKzpmStartdate").datebox("getValue");
	var toDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	if (verifiedDate=="undefined" || verifiedDate=="" || verifiedDate==" ") {  
		if(convertStringToDate(verifiedDate) > currentDate)
		{
			alert('Should Not Exceed Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		}
	}
	else{
		if(convertStringToDate(verifiedDate) > currentDate)
		{
			alert('Verified Date Should Not Exceed Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		if(convertStringToDate(verifiedDate) <convertStringToDate(fromDate))
		{
			alert('Verified Date Should Not Less Than Project Start Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		/*if(convertStringToDate(verifiedDate) >convertStringToDate(toDate))
		{
			alert('Verified Date Should Not Exceed Project End Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}*/
		
	}
	
	var jqGridId="fourgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var verifiedDate = jQuery("#"+dateCtrl).datebox("getValue");
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=parseInt(i)+1;	
			
				var completedDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'CompletedDate');	
				var maileStoneStage = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Stages');		
				completedDate=completedDate.replace(" 00:00:00","").trim();	
				if(maileStoneStage==stage){		
				if(convertStringToDate(verifiedDate) < convertStringToDate(completedDate))
				{
					alert('Verified Date Should Not Less than MileStone Completed Date');
					fillWithCurrentDate(dateCtrl);
					return false;
				}
			}			
		}
	}
	
	jqGridId="dmaicgrid";	
	allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=parseInt(i)+1;			
			var completedDate = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'dteKpdsVerifieddate');	
			var maileStoneStage = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'Stages');		
			completedDate=completedDate.replace(" 00:00:00","").trim();	
			if(parseInt(ctrlRowId)>parseInt(rowId)){		
				if(convertStringToDate(verifiedDate) < convertStringToDate(completedDate))
				{
					alert('Verified Date Should Not Less than Previous Verified Date');
					fillWithCurrentDate(dateCtrl);
					return false;
				}
			}						
		}
	}	
}

function damicOnLoadComplete()
{		
	var define=jQuery("#hdnKzpmDefinestage").val();
	var closure=jQuery("#hdnKzpmClosurestage").val();
	
	//var jqGridId="dmaicgrid";	
	//var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	jQuery("#Define").css('background-color','#D9D9DB');
	jQuery("#Measure").css('background-color','#D9D9DB');
	jQuery("#Analyse").css('background-color','#D9D9DB');
	jQuery("#Improve").css('background-color','#D9D9DB');
	jQuery("#Control").css('background-color','#D9D9DB');
	jQuery("#Closure").css('background-color','#D9D9DB');

	/** added by prasanth**/
	var cuStage = getCurrentStage();
	
	if( cuStage != undefined && cuStage != "")
		jQuery('#'+cuStage).css('background-color','#FFA500');
	
	disableUIButton("btnPrjYYLink");
	disableUIButton("btnFishBone");	
	if(define=="P"){
		jQuery("#Define").css('background-color','#FAF687');
	}
	else if(define=="C"){
		jQuery("#Define").css('background-color','#95FB94');		
	}else if(define=="R" || define=="E"){
		jQuery("#Define").css('background-color','#FC6767');
	}
		
	if(closure=="P"){
		jQuery("#Closure").css('background-color','#FAF687');
	}
	else if(closure=="C"){
		jQuery("#Closure").css('background-color','#95FB94');		
	}else if(closure=="R" || closure=="E"){
		jQuery("#Closure").css('background-color','#FC6767');
	}	
	//alert("closure:"+closure);
	var dStatus=jQuery("#hdnKzpmDefinestage").val();
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	var rowId=0;
	var colName="txtKpdsStage";
	//rowId= 1;
	//rowId = getStageRowId("Define");
	
	//jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(dStatus=="C"){
		jQuery("#Define").css('background-color','#95FB94');
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if( dStatus=="W" || dStatus=="I" || dStatus=="P"){
		jQuery("#Define").css('background-color','#FAF687');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});	
	}else if(dStatus=="R" || dStatus=="E"){
		jQuery("#Define").css('background-color','#FC6767');
    //	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',true);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});
	}
	
	//rowId = getStageRowId("Measure");
	//jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(mStatus=="C"){
		jQuery("#Measure").css('background-color','#95FB94');
	//var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if( mStatus=="W" || mStatus=="I" || mStatus=="P"){
		jQuery("#Measure").css('background-color','#FAF687');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});	
	}else if(mStatus=="R" || mStatus=="E"){
		jQuery("#Measure").css('background-color','#FC6767');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',true);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});
	}
	
	//rowId=2;
	//rowId = getStageRowId("Analyse");
	//jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(aStatus=="C"){
		jQuery("#Analyse").css('background-color','#95FB94');
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if((mStatus=="C" && aStatus =="-") || aStatus=="W" || aStatus=="I" || aStatus=="P"){
		jQuery("#Analyse").css('background-color','#FAF687');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});	
		enableUIButton("btnPrjYYLink");
		enableUIButton("btnFishBone");		
	}else if(aStatus=="R" || aStatus=="E"){
		jQuery("#Analyse").css('background-color','#FC6767');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});	
		enableUIButton("btnPrjYYLink");
		enableUIButton("btnFishBone");	
	}
	//rowId=3;
	//rowId = getStageRowId("Improve");
	//jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(iStatus=="C"){
		jQuery("#Improve").css('background-color','#95FB94');
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if((aStatus=="C" && iStatus =="-") || iStatus=="W" || iStatus=="I" || iStatus=="P"){
		jQuery("#Improve").css('background-color','#FAF687');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});			
	}else if(iStatus=="R" || iStatus=="E"){
		jQuery("#Improve").css('background-color','#FC6767');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});	
	}
	//rowId=4;
	//rowId = getStageRowId("Control");
	//jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled','disabled');			
	if(cStatus=="C"){
		jQuery("#Control").css('background-color','#95FB94');
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#95FB94'});
	}
	else if((iStatus=="C" && cStatus =="-") ||cStatus=="W" || cStatus=="I" || cStatus=="P"){
		jQuery("#Control").css('background-color','#FAF687');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FAF687'});	
	}else if(cStatus=="R" || cStatus=="E"){
		jQuery("#Control").css('background-color','#FC6767');
	//	jQuery("#jqg_"+jqGridId+"_"+rowId).attr('disabled',false);								
	//	var colValue=jQuery("#"+jqGridId).jqGrid('getCell',rowId, colName);
	//	jQuery("#"+jqGridId).setCell(rowId, colName,colValue,{'background-color':'#FC6767'});	
	}

	
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if( jQuery("#hdntype").val()== "team" || jQuery("#hdntype").val()== "define"){
		if(define=="C"){
			//alert(1);
			jQuery("#DefineStage").hide();
			//jQuery("#ClosureStage").hide();
			jQuery("#MaicStage").show();
			//jQuery("#divDefApproval").html("");
			//jQuery("#divMAICApproval").html("");
			//jQuery("#divClosureApproval").html("");
			//jQuery("#DefineStage").attr('disabled','disabled');			
		}
		getWorkFlowStage();			
	}

	if( (jQuery("#hdnIsClosure").val()=="y" || jQuery("#hdnIsClosure").val() == "Y" ) || ( closure != "-"  )  ){		
		getClosureStage();
	}
				
}
function getStageRowId(stage){

	var allIds = jQuery("#dmaicgrid").jqGrid('getDataIDs');	
	for( var i = 0; i < allIds.length;i++)
	{
		 var rowid=allIds[i];
	     var cStage= jQuery("#dmaicgrid").jqGrid('getCell',rowid,"txtKpdsStage");
		
		if( cStage.toUpperCase() == stage.toUpperCase() )
			return rowid;
	}		
	return null;
}

function FIPRODEF_successCallback(result){
	//alert("PRODE_successCallback");	
	//alert("result.wfStatus"+result.wfStatus);
	refreshWorkFlowDMAIC("D",result.wfStatus);
}

function FIPRODEFGE5L_successCallback(result){
	//alert("PRODE_successCallback");	
	//alert("result.wfStatus"+result.wfStatus);
	refreshWorkFlowDMAIC("D",result.wfStatus);
}

function FIPRODEFGE1C_successCallback(result){
	//alert("PRODE_successCallback");	
	//alert("result.wfStatus"+result.wfStatus);
	refreshWorkFlowDMAIC("D",result.wfStatus);
}
function FIPROMEA_successCallback(result){
	//alert("PROME_successCallback");
	refreshWorkFlowDMAIC("M",result.wfStatus);
}
function FIPROANA_successCallback(result){
	//alert("PROAN_successCallback");
	refreshWorkFlowDMAIC("A",result.wfStatus);
}
function FIPROIMP_successCallback(result){
	refreshWorkFlowDMAIC("I",result.wfStatus);
}
function FIPROCON_successCallback(result){
	//alert("PROCO_successCallback");
	refreshWorkFlowDMAIC("C",result.wfStatus);
}
function FIPROCLO_successCallback(result){
	//alert("PROCO_successCallback");
	refreshWorkFlowDMAIC("X",result.wfStatus);
}

function FIPROCLOGE5L_successCallback(result){
	//alert("PROCO_successCallback");
	refreshWorkFlowDMAIC("X",result.wfStatus);
}

function FIPROCLOGE1C_successCallback(result){
	//alert("PROCO_successCallback");
	refreshWorkFlowDMAIC("X",result.wfStatus);
}

function refreshWorkFlowDMAIC(updateStage,wfStatus){
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	processAjaxCalls("getWorkFlowStatus.prpo","updateStage="+updateStage+"&kznKeyId="+masterkeyid+"&wfStatus="+wfStatus,"workFlowStatusSuccess","");
	//refreshForm();
}
jQuery("#MaicStage").hide();
jQuery("#DefineStage").hide();

if(jQuery("#hdntype").val() == "define"){
	jQuery("#DefineStage").show();
	jQuery("#MaicStage").hide();
}
if(jQuery("#hdntype").val() == "team"){
	jQuery("#DefineStage").show();
	//jQuery("#MaicStage").show();
}
/*jQuery("#btnDefineStage").hide();
jQuery("#btnMaicStage").hide();
jQuery("#btnFishBone").hide();
jQuery("#btnPrjYYLink").hide();*/

jQuery("#btnDefineStage").click(function(){ 
	//loadDefineStage();
	//loadComplKPI();
	 jQuery("#DefineStage").show();
	 jQuery("#MaicStage").hide();
	 //jQuery("#ClosureStage").hide();
	
});

function loadDefineStage(){	
	
	 jQuery("#DefineStage").show();
	 jQuery("#MaicStage").hide();
	 //jQuery("#ClosureStage").hide();
	 //jQuery("#divDefApproval").html("");
	 //jQuery("#divMAICApproval").html("");
//	 jQuery("#divClosureApproval").html("");
	 var define=jQuery("#hdnKzpmDefinestage").val();
	 var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	 var flid = jQuery("#frmProject input[id='flid']").val();
	 //alert('flid:'+flid);	 
	 var fromDate = "";//jQuery("#dteKzpmStartdate").datebox("getValue");
	 var toDate = jQuery("#dteKzpmEnddate").datebox("getValue");
	 var curtStage = getCurrentStage();
	 var enD = "N";
	 if( curtStage == "Define")
		enD ="Y";
	 var tangible= jQuery('#chkKzpmIstangible').val();
	 var amnt=jQuery('#txtKzpmBenefits').val();
	 //alert(tangible +" tangible "+amnt);
	 
	 var mode = jQuery("#frmProject input[id='mode']").val();
		if( mode == "view")
			enD ="N";
		if(tangible=="Y" && amnt>=500000 && amnt<10000000){
			//jQuery("#fileUpld").show();
			 workFlow ("divDefApproval",false,"FIPRODEFGE5L", masterkeyid, "PRODEGE5L",flid,fromDate,toDate,enD);

		}
		else if(tangible=="Y" && amnt>=10000000){
			//jQuery("#fileUpld").show();
			 workFlow ("divDefApproval",false,"FIPRODEFGE1C", masterkeyid, "PRODEGE1C",flid,fromDate,toDate,enD);

		}
		else{
			if(tangible=="Y" && amnt > 0){
				//jQuery("#fileUpld").show();
			}
			workFlow ("divDefApproval",false,"FIPRODEF", masterkeyid, "PRODE",flid,fromDate,toDate,enD);
		}
	      
	  
	 if(define=="C"){
		 disableUIButton("btnInsertKpi");
		 disableUIButton("btnAddNew");
		 disableUIButton("btnAddrow");
		 //alert("C");
		 setTimeout(function() {			  
			 //jQuery('input:checkbox[name=workflow_PRODE]').attr('disabled','disabled');
			// if(jQuery('#blockvf').html()==null||jQuery('#blockvf').html()=="")
			 //	jQuery('#divDefApproval').after('<div id="blockvf" style="position:absolute;top:141%;left:4%;height:30%;width:91%;z-index:2;opacity:0.4;"> </div>');		 
			 
		 }, 400);
		
	 }	
}
/*jQuery("#btnMaicStage").click(function(){ 
	//jQuery("#ClosureStage").hide();
	jQuery("#MaicStage").show();
	//jQuery("#divDefApproval").html("");
	//jQuery("#divMAICApproval").html("");
	//jQuery("#divClosureApproval").html("");
	if(jQuery("#hdntype").val() == "define"){
		jQuery("#DefineStage").show();
		//jQuery("#MaicStage").hide();
	}
	else if(jQuery("#hdntype").val() == "team"){
		jQuery("#DefineStage").hide();
		jQuery("#MaicStage").show();		
	}
	else if(jQuery("#hdntype").val() == "closure"){
		jQuery("#DefineStage").hide();
		jQuery("#MaicStage").show();
		//jQuery("#ClosureStage").show();		
	}
//	getWorkFlowStage();	
});*/

jQuery("#btnClosureStage").click(function(){ 
	getClosureStage();
});

function getClosureStage(){
	//jQuery("#divDefApproval").html("");
	//jQuery("#divMAICApproval").html("");
	//jQuery("#divClosureApproval").html("");
	//jQuery("#DefineStage").hide();
//	jQuery("#MaicStage").hide();
//	jQuery("#ClosureStage").show();

//	jQuery('#Closure').css('background-color','#FFA500');
	//alert(2);
	var xStatus=jQuery("#hdnKzpmClosurestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	var flid = jQuery("#frmProject input[id='flid']").val();
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	//alert('flid:'+flid);	
	
	 var tangible= jQuery('#chkKzpmIstangible').val();
	 var amnt=jQuery('#txtKzpmBenefits').val();
	 //alert(tangible +" IN Side getClosure tangible "+amnt);
	if(cStatus=="C"){ //} && xStatus!="C" ){ 
		//var fromDate = jQuery("#"+jqGridId).jqGrid('getCell', 4, 'dteKpdsVerifieddate');					
		//fromDate=fromDate.replace(" 00:00:00","").trim();	r
		var enW = "Y";
		var mode = jQuery("#frmProject input[id='mode']").val();
		if( mode == "view")
			enW = "N";
		
		
				
				if(tangible=="Y" && amnt>=500000 && amnt<10000000){
					 //alert(tangible +" iN SIDE 500000 "+amnt);
					jQuery("#fileUpld").show();
					 workFlow ("divClosureApproval",false,"FIPROCLOGE5L", masterkeyid, "PROCLOGE5L",flid,'','',enW);

				}
				else if(tangible=="Y" && amnt>=10000000){
					 //alert(tangible +" iN SIDE 10000000 "+amnt);

					jQuery("#fileUpld").show();
					 workFlow ("divClosureApproval",false,"FIPROCLOGE1C", masterkeyid, "PROCLOGE1C",flid,'','',enW);

				}
				else{
				    if(tangible=="Y" && amnt > 0){
						jQuery("#fileUpld").show();
					}	
					workFlow("divClosureApproval",false,"FIPROCLO", masterkeyid, "PROCL",flid,'','',enW);	
					}
	}
}




function loadStagesMaic(mode,modifyStage){
	
	var defineStage= jQuery("#hdnKzpmDefinestage").val();
	//alert("len"+stage.length);
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();	
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if(mode=="add"){
		if (defineStage=="P" ||  defineStage=="-"  || defineStage=="R" || defineStage=="E" || defineStage=="L" || defineStage.trim().length<=0){
			return "Define Stage Not Completed";
		}
		else if(defineStage=="C" && (mStatus=="-" || mStatus=="P" || mStatus=="I"  || mStatus=="R" || mStatus=="E" || mStatus=="L" || mStatus.trim().length<=0) ){
			//alert("mStatus");
			return mode;
		}
		else if(mStatus=="C" && ( aStatus=="-" || aStatus=="P" || aStatus=="I" || aStatus=="R" || aStatus=="E"  || aStatus=="L" || aStatus.trim().length<=0)  ){	
			//alert("aStatus");
			return mode;	
		}			
		else if(aStatus=="C" && ( iStatus=="-" || iStatus=="P" || iStatus=="I" || iStatus=="R" || iStatus=="E"  || iStatus=="L" || iStatus.trim().length<=0 ) ){
			//alert("iStatus");
			return mode;
		}
		else if(iStatus=="C" && ( cStatus=="-" || cStatus=="P"  || cStatus=="I"  || cStatus=="R" || cStatus=="E" || cStatus=="L" || cStatus.trim().length<=0 ) ){
			//alert("cStatus");			
			return mode;
		}
		else{
			return "Current Stage Is Waiting For Approval";			
		}
	}
	else if(mode=="modify"){
		modifyStage=modifyStage.substring(0,1);
		//alert("modifyStage"+modifyStage);
		var stageCom="";
		if(defineStage=="C" && (mStatus=="-" || mStatus=="P" || mStatus=="I" || mStatus=="R" || mStatus=="E" || mStatus=="L"  || mStatus.trim().length<=0) ){
			stageCom="M";
		}
		else if(mStatus=="C" && ( aStatus=="-" || aStatus=="P" || aStatus=="I" || aStatus=="R" || aStatus=="E" || aStatus=="L" || aStatus.trim().length<=0)  ){	
			stageCom="A";
		}			
		else if(aStatus=="C" && ( iStatus=="-" || iStatus=="P" || iStatus=="I" || iStatus=="R" || iStatus=="E" ||  iStatus=="L" || iStatus.trim().length<=0 ) ){
			stageCom="I";
		}
		else if(iStatus=="C" && ( cStatus=="-" || cStatus=="P"  || cStatus=="I" || cStatus=="R" || cStatus=="E" ||  cStatus=="L" || cStatus.trim().length<=0 ) ){
			stageCom="C";
		}
		//alert("stageCom"+stageCom);
		if(modifyStage!=stageCom){
			return "view";
		}
		else{
			return mode;			
		}
	} 	
}
jQuery("#btnAddKaizen").click(function(){	
	if(jQuery("#mode").val()!="view"){
		LoadPopUp("divKaizenPop", "kaizenList_view.prpo?", true,"800px","400px","60px","10%", "multiSelectOk_Callback","List Of Kaizen",false);
	}
});

jQuery("#btnSaveDMAIC").click(function(){
	if(jQuery("#mode").val()!="view"){
		var projectadmaic=saveDMAICStatus();
		//alert(projectadmaic);
		var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
		processAjaxCalls("projectsdmaic_save.prpo","hdnKzpmKeyid="+hdnKzpmKeyid+"&Type=dmaicStatus"+projectadmaic,"mAICSaveSuccess");
	}
});

function mAICSaveSuccess(result){
	//if(result.successData.Type=="dmaicStatus"){
	alert(result.successData.msg);
//	jQuery("#dmaicgrid").trigger("reloadGrid");
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");	
}
	
function workFlowStatusSuccess(result){
	var stage = getCurrentStage(); 
	closePopUpDialoge("loadFPI");
	
	var measureStage=result.successData.measureStage;
	var analyseStage=result.successData.analyseStage;
	var improveStage=result.successData.improveStage;
	var controlStage=result.successData.controlStage;
	var closureStage=result.successData.closureStage;
	
	//alert("measureStage:"+measureStage+",analyseStage:"+analyseStage+",improveStage:"+improveStage+",controlStage:"+controlStage);
	if(measureStage.trim().length>0 && measureStage!=undefined && measureStage!="undefined")
		jQuery("#hdnKzpmMeasurestage").val(measureStage);	
	if(analyseStage.trim().length>0 && analyseStage!=undefined && analyseStage!="undefined")
		jQuery("#hdnKzpmAnalysestage").val(analyseStage);
	if(improveStage.trim().length>0 && improveStage!=undefined && improveStage!="undefined")
		jQuery("#hdnKzpmImprovestage").val(improveStage);
	if(controlStage.trim().length>0 && controlStage!=undefined && controlStage!="undefined")
		jQuery("#hdnKzpmControlstage").val(controlStage);
	if(closureStage.trim().length>0 && closureStage!=undefined && closureStage!="undefined")
		jQuery("#hdnKzpmClosurestage").val(closureStage);
	
	var defineStage=result.successData.defineStage;	
	if(defineStage.trim().length>0)
		jQuery("#hdnKzpmDefinestage").val(defineStage);		
	
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	var isChk = jQuery("#hdnIsCheckList").val();
	
	var load = jQuery("#hdnLdCkLWrkFlowClbk").val();

	if( isChk == "Y" && load == "Y" ){
	
		jQuery("#hdnFIPRODEFCurEmps").val("");
		var role = jQuery("#hdnFIPRODEFCurRole").val();
		//var stage = jQuery("#hdnFIPRODEFCurStage").val();
		loadCheckList(false,role,stage,"view");
	}	
	jQuery("#hdnLdCkLWrkFlowClbk").val("Y");
	
	//processGridnew("projectsdmaic_input.prpo","q=2&keyid="+masterkeyid,"dmaicgrid","","","","","damicOnLoadComplete");
}

function divMilestonePop_onClose(){
	//alert("divMilestonePop_onClose");	
	/*var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");		
	processGridnew("projectsmile_input.prpo","q=2&keyid="+jQuery("#hdnKzpmKeyid").val(),"fourgrid","pagergrid","","doubleClickMile","","load_complete");
	*/
	return true;
}

function divKaizenPop_onClose(){
	//alert("divKaizenPop_onClose");
	//var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	//var flid = jQuery("#frmProject input[id='flid']").val();

	jQuery("#Kaizengrid").trigger("reloadGrid");
	//processGridnew("projectskaizen_input.prpo","q=2&flid="+flid+"&master="+masterkeyid,"Kaizengrid","pagerkaizen","","","","load_complete");
	return true;
}

function doubleClickMile(id){
	var rowData = jQuery("#fourgrid").jqGrid('getRowData',id);
	var keyId = rowData.Keyid;	
	var stage = rowData.Stages;
	var mode=loadStagesMaic("modify",stage);	
	//alert("mode"+mode);
	if (mode=="add" || mode=="modify" || mode=="view"){	
		LoadPopUp("divMilestonePop", "milestone_view.prpo?q=2&Keyid="+keyId+"&mode="+mode, true,"90%","80%","60px","7%", "multiSelectOk_Callback","MilesStone"," ",true);
	}
	else{
		setTimeout(function() {
			showCommonErrorMsg(mode);
		}, 200);
		div_err();
		return false;
	}
	
}

function ResouredoubleClick(id){
	/*var rowData = jQuery("#resourcesgrid").jqGrid('getRowData',id);
	var keyId = rowData.keyid;	
	LoadPopUp("divResourcePop", "resource_view.prpo?q=2&resourceKeyid="+keyId, true,"70%","40%","20%","6%", "multiSelectOk_Callback","Resource"," ",true);*/
}


function resourcesSaveSuccess(result){
	//if(result.successData.Type=="dmaicStatus"){
	alert(result.successData.msg);
	//alert("dmaicStatus success");
	jQuery("#resourcesgrid").trigger("reloadGrid");
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	//processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");	
}

function divResourcePop_onClose(){
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();
	processGridnew("projectsproto_input.prpo","q=2&keyid="+masterkeyid,"resourcesgrid","pager","","ResouredoubleClick","","loadResource_complete");
	return true;
}

function convertResoureDeleteToJsonString(jqGridId)//define stage for resource 
{
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');//	row get data
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	var jsonArrO='[';
	for(var i=0;i<row.length;i++){
		 var rowid=row[i];
	     var detailKeyid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"1");
	     var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval");
	     if(select=="1"){
	        var keyid;
	        if(detailKeyid.trim().length >=0){
	            keyid = detailKeyid;
	     		jsonArrO+= '{';
				jsonArrO += '"txtKprlKeyid":"'+keyid+'"';
				jsonArrO+= '},';
		     }		
	     }
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO;	
}

function loadResource_complete()
{	
	var jqGridId="resourcesgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=	parseInt(i)+1;
			var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtKprlKeyid');			
			if (keyId.trim().length>0){
				//jQuery("#jqg_"+jqGridId+"_"+rowId).setSelection(allRows[i], true);
				jQuery('#'+jqGridId).setSelection(rowId, true);	
				//jQuery('#'+jqGridId).setSelection(allRows[i], true);				
				jQuery("#jqg_"+jqGridId+"_"+rowId).attr('checked',true);
			}
		}	
	}	
}

var keyid=jQuery("#hdnKzpmKeyid").val();//jQuery("#hdnabnkeyID").val();
var flid=jQuery("#frmProject input[id='flid']").val();
jQuery("#btnPrjYYLink").click(function(){ 
	//openWhyWhy(divId,isPopup,refDocId,refDocType,flId, refDocDate, problem, yyMode)
	var pbm=jQuery("#txtKzpmProblemstatement").val();
	openWhyWhy("divProWhyWhy",false,keyid,"PRO",flid, "", pbm, jQuery("#mode").val());
});

jQuery("#btnActionPlan").click(function(){
	//divId,actPlanRefMasId,actPlanRefDocType,flId,actPlanMainTask,actPlanRefDtlId, actPlanRefDate, apMode)}
	var mainTask=jQuery("#txtKzpmProjectname").val();
	var actDate = getFieldValue("dteKzpmStartdate");
	//alert(actDate);
	openActionPlan("divProActioPlan",keyid,"PRO",flid,mainTask,keyid,actDate,"FIP-"+jQuery("#mode").val());
});

jQuery("#btnFishBone").click(function(){
	 var keyid =getFieldValue("hdnKzpmKeyid");
	 var flid=jQuery("#frmProject input[id='flid']").val();
	 navigateToNextForm('FishBone_input.fishbone?&refDocId='+keyid+"&refDocType=PRO&flid="+flid+"&mode="+jQuery("#mode").val(),'Fish Bone');
});

jQuery("#btnMOM").click(function(){
	
	//closePopUpDialoge("loadFPI");
//	openNewForm();
	var flid=jQuery("#frmProject input[id='flid']").val();
	 var mode = jQuery("#mode").val();
	 var keyid =getFieldValue("hdnKzpmKeyid");
	 
	 var stage = jQuery('#hdnstage').val();
	 var url = 'MeetingMin_input.mom';
	 if( mode == "view")
		 url = "mom_view.mom"; 
	 LoadPopUp("Kaizen",url+'?momRefDocId='+keyid+'&type=FIP&momRefDocType=PRO&flid='+flid+"&mode="+jQuery("#mode").val()+"&stage="+stage , true,"95%","90%","3%","1%", "popup_callback()","Meeting Minutes"," ",true,true);
	 //navigateToNextForm(url+'?momRefDocId='+keyid+'&type=FIP&momRefDocType=PRO&flid='+flid+"&mode="+jQuery("#mode").val()+"&stage="+stage,'Meeting Minutes');
});

function txtFormatterKaizen(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return '<input type="button" id="btnKaizen_'+rowId+'_'+colId+'" name="btnKaizen_'+rowId+'_'+colId+'" onclick="kaizen('+rowId+','+colId+')"  style="width:50px;  height:15px;height:10px;"   class="easyui-button" value="..."/>';
}

function btnDocRefFormatter(id,options,rowObject){
	var rowId = options.rowId;
	
	return '<input type="button" id="btnFileMgr_'+rowId+'" name="btnFileMgr_'+rowId+'" onclick="fileMgr('+rowId+')" style="width:50px;  height:18px;height:15px;"   class="easyui-button" value="..."/>';
}

function btnfilemgr_click()
{	
	var documentNo =getFieldValue("hdnKzpmKeyid");     
	if(documentNo != null && documentNo != '')
		{
		var frMode = jQuery("#mode").val();
		let fmgMode = "";
				//alert("frMode:"+frMode);
				if(frMode === "approval"){
					fmgMode = "view";
				}else if(frMode === "define"){
					fmgMode = "fipdefine";
				}else if(frMode === "view"){
					fmgMode = "view";
				}
				//alert("after frMode :"+fmgMode);
		fileManagerPopUp(documentNo,"PRO","","","",fmgMode);
	}
	else{
		saveForm("frmProject","projectsprotoview_save.prpo?&type=fileMng","");
	}
}

function fileMgr(id) {
	
	//http://localhost:8080/TPMToolKit/file_input.file?q=2&documentNo=1&documentType=ABN&buttonId=
	fileManagerPopUp(1,"PCS","","","");
		
}

function kaizen(id, colId){
	var kaizenId = jQuery("#Kaizengrid").jqGrid('getCell', id, 'KaizenNo.');
	LoadPopUp("Kaizen","kaizen_input.kaizen?kznKeyid="+kaizenId+"&mode=view" ,true,"90%","500px","1%","3%","","Kaizen ");
}

function divIndicatorPop_onClose(){	
	//alert("divIndicatorPop_onClose");
	jQuery("#Kpigrid").trigger("reloadGrid");
   return true;
}

function load_complete(){

}
function getBenefits(){
	var value=getFieldValue("hdnBenefits");	
	jQuery("#cboKzpmBenefits").val(value);	
}



 
		 
		 

