<!-- <script type="text/javascript" src="js/fileuploader.js"></script> -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> --%>
<!-- <link href="css/fileuploader.css" rel="stylesheet" type="text/css"/>	

 <link rel="stylesheet" type="text/css" href="css/tpm-style.css"/>
        <link rel="stylesheet" type="text/css" href="css/tpm-style-1366.css"/>
		
		<link rel="stylesheet" type="text/css" media="screen" href="css/grid/jquery-ui-1.8.2.custom.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/grid/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" href="css/classic-style.css"/> -->
<!--         <script type="text/javascript" src="js/jquery-1.6.4.js"></script> -->
        <!-- <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script src="js/jquery-migrate-1.1.1.min.js"></script>
        -->
<!--         <link rel="stylesheet" href="css/pre-loader/queryLoader.css" type="text/css" />
		<script type='text/javascript' src='js/pre-loader/queryLoader.js'></script>
		<script src="js/grid.locale-en.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="css/home-page-tab-style.css" /> -->
	<!-- 	<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script> -->
		
<!--          <script src="js/grid/core/grid.loader.js" type="text/javascript"></script>
         
        <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="js/classic.js"></script>
        <script type="text/javascript" src="js/jquery.corner.js"></script>
        <script type="text/javascript" src="js/jsTree/jquery.jstree.js"></script>
        <script type="text/javascript" src="js/jsTree/jquery.cookie.js"></script>
        <script type="text/javascript" src="js/jsTree/jquery.hotkeys.js"></script>
        <script src="js/grid/core/grid.loader.js" type="text/javascript"></script>
        
        <script src="js/ajaxupload.3.5.js" type="text/javascript"></script>
        <script src="js/grid/core/grid.loader.js" type="text/javascript"></script>
        
        <script src="jquery.jqGrid-4.1.1/plugins/jquery.contextmenu.js" type="text/javascript"></script> -->
        
  <!--       <script src="js/grid/core/language/jqDnR.js" type="text/javascript"></script>
	    <script src="js/grid/core/language/jqModal.js" type="text/javascript"></script>  -->
		
<!-- 		<script type="text/javascript" src="js/jquery.address-1.4.min.js"></script>
		<script type="text/javascript" src="js/CommonFunctions.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="css/home-page-tab-style.css" />
		<script type="text/javascript" src="js/dashboard-content.js"></script>
		<script src="js/hcharts/charts.js" type="text/javascript" ></script>
		<script src="js/hcharts/charts-more.js" type="text/javascript" ></script>
		<script src="js/jquery.printElement.min.js" type="text/javascript" ></script> -->
		

<script type="text/javascript">	
var pssrCount="";
jQuery(document).ready(function(){
	 initialiseForm('frmMocProject');
//	 alert('1236');
	 var flag="false";
	 jQuery('#submitForm').val('frmMocProject');
//	 alert('1236');
	//	var link ="?q=2&SuggestionFlid=FNL000000049&WhatifKey= &HazopKey= &Suggestion=MANAGEMENT OF CHANGE CHECKING&SuggestionId=KZBN002171&Status=RFC Initial Approval Pending&MocKeyid=MOC00000000215&Responsibility=EMP00001&psrmkey=PSR00000000051&filterButton=false";
		//processAjaxCalls("ChangeRequest_input.nmoc",link,"sectionIdRecallSuccess","");

 var factId = jQuery("#frmMocProject input[id='factory']").val();
		var sectionId = jQuery("#frmMocProject input[id='section']").val();
		var cellId = jQuery("#frmMocProject input[id='cell']").val();
		var machId = jQuery("#frmMocProject input[id='machine']").val();
		
		var flid = jQuery("#frmMocProject input[id='flid']").val();
//		var flid = jQuery("#frmMocProject input[id='flid']").val();

		////////////////////////////////////////////alert("flid"+flid);
		var WhatifKey=jQuery("#hdnWhatifKey").val();
		var nature=jQuery("#hdnnature").val();
		var type=jQuery("#hdntype").val();
		//fillComboBox("frmMocProject","cmbRfcmtype","MocType.nmoc");
		// fillComboBox("frmMocProject","cmbRfcmnature","MocNature.nmoc");
		 fillComboBox("frmMocProject","cmbRfcmempid","employee.commonFilter");
		//  var FobmKeyid=jQuery("#txtFobmKeyid").val();
	   // ////////////////////////////////////////////////////alert("FobmKeyid:"+FobmKeyid);
	 
	    var responsibility = jQuery('#hdnInitiator').val();
            
	    var userid=jQuery("#hdnuserid").val();
	  
	   var mode= jQuery('#hdnmode').val();
	// //////////////////////alert("mode:::"+mode)
	    if(mode.length<=0){
	    	////////////////////////////////////alert("IN If");
	    if(userid==responsibility){
	    	//jQuery('#hdnmode').val("create");
	    	jQuery('#hdnmode').val("view")
	    }
	    else{
	    	jQuery('#hdnmode').val("view")	;
	    }
	    }
	   var mode= jQuery('#hdnmode').val();
	
	if(mode=="view"){
		disableField("frmMocProject","cboRfcmtype");
		disableField("frmMocProject","cboRfcmnature");
		
		setTimeout(function() {readOnlyFields('dteRfcmdate');},1250);
		 setTimeout(function() {readOnlyFields('txtRfcmdetail');},1250);
		 setTimeout(function() {readOnlyFields('txtRfcmtitle');},1250);
		 setTimeout(function() {readOnlyFields('txtRfcmdescription');},1250);
		 setTimeout(function() {readOnlyFields('txtWifmFacility');},1250);
		 setTimeout(function() {readOnlyFields('txtWifmTeam');},1250);
		 setTimeout(function() {readOnlyFields('dteWifmDate');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomFacility');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomTeam');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomPidno');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomNode');},1250);
		 setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
		 setTimeout(function() {readOnlyFields('txtHzomDesignintent');},1250);
		 setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
		 setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
		 setTimeout(function() {readOnlyFields('chkSelectAllQuestions');},1250);
		 disableUIButton("btnAddNewReccommend");
			disableUIButton("btnAddHazop");
			disableUIButton("btnAddwhatif");



	}
	   
	// ////////////////////////////////////////////////////alert("responsibility"+responsibility);
	 readOnlyFields('cmbRfcmempid');
	 setTimeout(function() {readOnlyFields('cmbRfcmempid');},1250);
	 setTimeout(function() {readOnlyFields('cmbRfcmjhid');},1250);
	 setTimeout(function() {readOnlyFields('cmbrfcmdmtid');},1250);
	   var SuggestionId=jQuery('#hdnSuggestionId').val();
	  

		
		disableField("frmMocProject", "cmbRfcmempid")
	
		formatDateBox('dteRasmDate','DD-MON-YYYY');
		formatDateBox('dteRfcmdate','dd-MMM-yyyy');
		formatDateBox('dtePsrmdate','dd-MMM-yyyy');
		formatDateBox('dteWifmDate','dd-MMM-yyyy');
		formatDateBox('dteHzomDate','dd-MMM-yyyy');
		fillWithCurrentDate('dteRfcmdate');
		fillWithCurrentDate('dtePsrmdate');
		fillWithCurrentDate('dteRasmDate');
		//fillWithCurrentDate('dteHazopDate');
		fillWithCurrentDate('dteHzomDate');
		fillWithCurrentDate('dteWifmDate');
		
		 fileManagerPopUp("","MOC","frmMocProject","btnFilManage","MocfileMgr"); 
		 var SugFlid=jQuery('#hdnSuggestionFlid').val();
			////////////////////////////////////////////alert("SuggFlid"+SugFlid);
		var MocKeyid=jQuery("#txtRfcmKeyid").val();
		if(MocKeyid !=null){
			processAjaxCalls("WhatIfHazopRecommentationsCount.nmoc","&MocKeyid="+MocKeyid,"WH_successcallback","WH_Errorcallback");
			
		processAjaxCalls("PSSRRecommentationsCount.nmoc","&MocKeyid="+MocKeyid,"PSSRRecomment_successcallback","");
		processAjaxCalls("InitialApprovalCount.nmoc","&MocKeyid="+MocKeyid,"Approval_successcallback","");
		processAjaxCalls("HazopApprovalCount.nmoc","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","");
		processAjaxCalls("FinalApprovalCount.nmoc","&MocKeyid="+MocKeyid,"FinalApproval_successcallback","");
		processAjaxCalls("MOCClosureCheck.nmoc","&MocKeyid="+MocKeyid,"MOCClosure_successcallback","");
		
		jQuery("#cboRfcmnature").val(nature);
		jQuery("#cboRfcmtype").val(type);
		//  var MOCStatus=jQuery("#hdnFinalcount").val();

		}
			 if(flid !=null)
			    {	 
	//		alert("Loadfn" + flid);
			    	loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMOCfunLocationValues","frmMocProject","&flid="+flid);
			    	reloadCombo("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
			    	//reloadCombo("frprocessAjaxmMocProject","cmbrfcmdmtid","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
			 	   
			    }
			    else
			 	   {
			    	////////////////////////////////////////////////alert("ELSELoadfn");
			 	   loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMocfunLocationValues","frmMocProject","");
			 	   } 
		// loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMOCfunLocationValues","frmMocProject","");
     
     var flid = jQuery("#frmMocProject input[id='flid']").val();
     ////////////////////////////////////////////////alert("flid:::"+flid);
	// fillComboBox("frmMocProject","cmbRfcmtype","MocType.nmoc");
	// fillComboBox("frmMocProject","cmbRfcmnature","MocNature.nmoc");
	 fillComboBox("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter");
	 fillComboBox("frmMocProject","cmbrfcmdmtid","sectionCombo.commonFilter");
	 fillComboBox("frmMocProject","cmbDmdmType","type_combo.dcm");
	 fillComboBox("frmMocProject","cmbDmdmSubjectarea","subjectArea_combo.dcm");
	 fillComboBox("frmMocProject","cmbDmdmCategory","category_combo.dcm");
	 fillComboBox("frmMocProject","cmbDmdmOwner","employee.commonFilter");
	 fillComboBox("frmMocProject","cmbDmdmApprovedby","employee.commonFilter");
	 var responsibility = jQuery('#hdnResponsibility').val();
	 var mode=jQuery("#hdnmode").val();
	 
	 var initial =jQuery("#hdnInitialApproval").val();
	 var hazop=	jQuery("#hdnHazopApproval").val();


	if(initial=="Pending"||hazop=="Pending")
	{
	 setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
	 setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
	 setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
	}
});
  // -- Moving inside document ready Vignesh
//  var initial =jQuery("#hdnInitialApproval").val();
//  var hazop=	jQuery("#hdnHazopApproval").val();


// if(initial=="Pending"||hazop=="Pending")
// {
//  setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
//  setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
//  setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
// }



function btnFilManage_click(){
    var documentNo =jQuery("#txtRfcmKeyid").val();
	if(documentNo != null && documentNo != ''){
		var frmMode=jQuery('#frmMode').val();
		apMode = "create";
		if(frmMode=="View")
		   apMode = "view";
		fileManagerPopUp(documentNo,"MOC","","","","create");		
	} 
	else  
    {  
		 return false;
     }	
}
  
 
jQuery("#tabMocProject").tabs(
 	    {
 		onSelect : function(title)
 		 {
 			if(title=="MOC Workflow"){
 				 jQuery("#hdntitle").val("MOC Team");
 				var MocKeyid=jQuery("#txtRfcmKeyid").val();
			   //  processGridnew("MOCTeam_input.nmoc","q=2&SrpmKeyid="+SrpmKeyid,"TeamGrid", "Teampagerid","","", "", "");
     
 				 processGridnew("MOCTeamSusscess_input.nmoc","q=2&MocKeyid="+MocKeyid,"TeamGrid", "Teampagerid","","", "", "TeamloadComFunction");    

 			}
 			else if(title=="RequestforChange"){
 				var MocKeyid=jQuery("#txtRfcmKeyid").val();
 				//////////////////////////////////////////////alert("MOCKey"+MocKeyid);
 				 jQuery("#hdntitle").val("RequestforChange");
                 processGridnew("BasisofChange_input.nmoc","q=2&MocKeyid="+MocKeyid,"BasisGrd","BasisGrdpager","","","","Basisgridcompletecallback");
            
 			
 			}
 			
 			//processGridnew("PssrChecklist_input.ehsb","?q=2&keyid="+keyid+"&userid="+userid,"QCGrid","pagerQcLog","","","");
 			else if(title=="Questionnaire")	{
 			//	////////////////////////////////////////////////////alert("IFCond")Questionnaire
 			 jQuery("#hdntitle").val("Questionnaire");
 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 			processGridnew("Questionaire_input.nmoc","q=2&MocKeyid="+MocKeyid,"QuestionaireGrd","pager","","","","QuestionnaireloadComFunction");	
 		 }
 			else if(title=="InitialApproval")	{
 				 jQuery("#hdntitle").val("InitialApproval");
 	 		var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 	
 	 			processGridnew("InitialApproval_input.nmoc","q=2&MocKeyid="+MocKeyid,"InitialGrid","pager","","docDoubleClick","","InitialloadComFunction");	 
 		
 	 	
 	 		}
 		
 			else if(title=="WhatIf"){
 	 			//	//////////////////////////////////////////////alert("WhatIf");
 	 			jQuery("#hdntitle").val("WhatIf");
 	 			 var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			processAjaxCalls("InitialApprovalCount.nmoc","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
 	 			
 	 			 var initial =jQuery("#hdnInitialApproval").val();
 	 		   
 	 		      if(initial=="Pending"){
 	 		    	disableUIButton("btnAddwhatif");
 	 		    	disableField("frmMocProject","dteWifmDate");
 	 		    	disableField("frmMocProject","txtWifmTeam");
 	 		    	disableField("frmMocProject","txtWifmFacility");
 	 		    	 setTimeout(function() {readOnlyFields('txtWifmFacility');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtWifmTeam');},1250);
 	 				 
 	 		      }
 	 		      /*else{
 	 		    	  enableUIButton("btnAddwhatif");
 	 		    	  enableFields("dteWifmDate");
 	 		    	  enableFields("txtWifmTeam");
 	 		    	  enableFields("txtWifmFacility");
 	 		      }  */
 	 			//////////////////////////////////////////////alert(MocKeyid);
 	 			 processGridnew("WhatIFEntry_input.nmoc","q=2&MocKeyid="+MocKeyid,"WhatifGrid","Whatifpager","Whatif", "","","WhatifDtlgridLoadComplete");		 
 	 			}
 	 			else if(title=="Hazop"){
 	 				jQuery("#hdntitle").val("Hazop");
 	 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val(); 	 	 			
 	 	 			 var initial =jQuery("#hdnInitialApproval").val();
 	 	 		     // //////////////////////alert(initial);
 	 	 		      if(initial=="Pending"){
 	 	 		    	disableUIButton("btnAddHazop");
 	 	 		    	disableField("frmMocProject","dteHzomDate");
 	 	 		    	disableField("frmMocProject","txtHzomTeam");
 	 	 		    	disableField("frmMocProject","txtHzomFacility");
 	 	 		    	disableField("frmMocProject","txtHzomNode");
 	 	 		    	disableField("frmMocProject","txtHzomPidno");
 	 	 		    	disableField("frmMocProject","txtHzomDesignintent");
 	 	 		     setTimeout(function() {readOnlyFields('txtHzomFacility');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtHzomTeam');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtHzomNode');},1250);
 	 				 setTimeout(function() {readOnlyFields('txtHzomDesignintent');},1250);
 	 	 		      }
 	 	 		     /* else{  	 	 	

 	 	 		    	  enableUIButton("btnAddHazop");
 	 	 		    	  enableFields("dteHzomDate");
 	 	 		    	  enableFields("txtHzomTeam");
 	 	 		    	  enableFields("txtHzomFacility");
 	 	 		    	  enableFields("txtHzomNode");
 	 	 		    	  enableFields("txtHzomPidno");
 	 	 		    	  enableFields("txtHzomDesignintent");
 	 	 		      } */
 	 	 			//////////////////////////////////////////////alert(MocKeyid);
 	 	 			processGridnew("HazopEntry_input.nmoc","q=2&MocKeyid="+MocKeyid,"Hazopgrid","Hazoppager","Hazop", "","","HazopDtlgridLoadComplete");		 
 	 	 	 	
 	 			}
 			
 	 	
 
 	 		
 	 		else if(title=="W/H Approval")	{
 	 			//	////////////////////////////////////////////////////alert("IFCond")
 	 			 jQuery("#hdntitle").val("W/H Approval");
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			processAjaxCalls("WhatIfHazopRecommentationsCount.nmoc","&MocKeyid="+MocKeyid,"WH_successcallback","WH_Errorcallback");
 	 			processAjaxCalls("InitialApprovalCount.nmoc","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
 	 			
 	 	processGridnew("HazopApproval_input.nmoc","q=2&MocKeyid="+MocKeyid,"HazopApprovalGrid","pager","","","","HazoploadComFunction");	
 	 		//workFlow("divKznWorkFlow",false,"", "", "KZNBTS");
 	 		}
 			
else if(title=="PSSRchecklists"){
 	 			
 				var MocKeyid=jQuery("#txtRfcmKeyid").val();
 				
 				 jQuery("#hdntitle").val("Psschecklist");
 				processAjaxCalls("HazopApprovalCount.nmoc","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","ApprovalCount_Errorcallback");
 				 var initial =jQuery("#hdnInitialApproval").val();
 				
 			 	 var hazop=	jQuery("#hdnHazopApproval").val();
 			 	var mode= jQuery('#hdnmode').val();
 			 	//////////alert(mode)
 			 	 if((initial=="Pending"||hazop=="Pending"||mode=="view")||( initial=="Closed" && hazop=="Pending" && mode=="view")){
 			 	
 			 		 setTimeout(function() {readOnlyFields('txtPsrmmocdetail');},1250);
 					 setTimeout(function() {readOnlyFields('txtPsrmprocess');},1250);
 					 setTimeout(function() {readOnlyFields('dtePsrmdate');},1250);
 			 	 }
 	 			processGridnew("PssrChecklist_input.nmoc","q=2&MocKeyid="+MocKeyid,"PSSRGrid","pager","","","","PssrGridloadComFunction"); 	
 	 		 }
 		
 	 		else if(title=="PSSRRecomnd")	{
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			 jQuery("#hdntitle").val("MOCReccommend");
 	 			processAjaxCalls("PSSRRecommentationsCount.nmoc","&MocKeyid="+MocKeyid,"PSSRRecomment_successcallback","");
 	 			processAjaxCalls("InitialApprovalCount.nmoc","&MocKeyid="+MocKeyid,"Approval_successcallback","");
 	 			processAjaxCalls("HazopApprovalCount.nmoc","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","");
 	 			var WHCount=jQuery("#hdnWHCount").val();
 	 	 		//alert("WHCount"+WHCount);
 	 	 		var initial =jQuery("#hdnInitialApproval").val();
 	 	 		//alert("initial"+initial);
 	 	 		var Hazop=jQuery("#hdnHazopApproval").val();
 	 	 		//alert("Hazop"+Hazop);
 	 	 		var PssrCount=jQuery('#hdnpssrCount').val();
 	 	 		//alert("PssrCount"+PssrCount);
 	 	 		if(initial=="Closed" && Hazop=="Closed" && WHCount=="create")
 	 	 		{
 	 	 			enableUIButton("btnAddNewReccommend");	
 	 	 		}
 	 	 		else{
//alert("else");
 	 	 			 disableUIButton("btnAddNewReccommend");
 	 	 		}
 	 			
 	 		 processGridnew("PSSRRecommendations_input.nmoc","q=2&MocKeyid="+MocKeyid,"PSSRReccomendGrid","pager","RFC QUESTIONNAIRE","docDoubleClick","","ReccommendloadComFunction");	
 	 		
 	 	}
 			else if(title=="W/H Reccommend")	{
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			 jQuery("#hdntitle").val("HazopReccommend");
 	 			 processGridnew("HazopRecommendations_input.nmoc","q=2&MocKeyid="+MocKeyid,"HazopReccommendGrid","pager","RFC QUESTIONNAIRE","","","HazopReccommendloadComFunction");	
 	 		 }
	else if(title=="MOCClosure")	{ 
 	 			
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			 jQuery("#hdntitle").val("MOCCLosure");
 	 			processAjaxCalls("PSSRRecommentationsCount.nmoc","&MocKeyid="+MocKeyid,"PSSRRecomment_successcallback","PSSRRecomment_Errorcallback");
 	 			
  	 			processGridnew("MocClosure_input.nmoc","q=2&MocKeyid="+MocKeyid,"MOCCGrid","pager","","","","MOCClosureloadComFunction");	
 	 		 } 
 	 		else if(title=="FinalApprovals")	{
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();	
 processAjaxCalls("InitialApprovalCount.nmoc","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
 processAjaxCalls("HazopApprovalCount.nmoc","&MocKeyid="+MocKeyid,"HazopApproval_successcallback","ApprovalCount_Errorcallback");
 processAjaxCalls("MOCClosureCheck.nmoc","&MocKeyid="+MocKeyid,"MOCClosure_successcallback","");
		 			
 	 			 jQuery("#hdntitle").val("FinalApprovals");
 	 			var MOCStatus=jQuery("#hdnFinalMaxApprovalStatus").val();
 	 			//////////////////////////alert("MOCStatusNew"+MOCStatus);
 	 			   if(MOCStatus=="Pending"){
 	 				 //  enableFields("chkMocClosed")
 	 				   jQuery("#chkMocClosed").attr("disabled",true).removeClass("ui-state-disabled");
 	 			   }
 	 			   else{
 	 				////////////////////////////////alert("Else");
 	 				   jQuery("#chkMocClosed").attr("disabled",false).removeClass("ui-state-disabled");
 	 			   }
 	 				var Status=jQuery("#hdnStatusClose").val();
 	 	 		//////////////////////alert("Status"+Status)
 	 	 			  if(Status=="MOC CLOSED"){
 	 	 				  jQuery("#chkMocClosed").attr(":checked",true);
 	 	 					jQuery('#chkMocClosed').prop('checked', true);
 	 	 					 jQuery("#chkMocClosed").attr("disabled",true); 
 	 	 					 
 	 	 				
 	 	 				   }	
 	 	 		
 	 			var MocKeyid=jQuery("#txtRfcmKeyid").val();
 	 			jQuery("#hdntitle").val("FinalApprovals");
 	 		processGridnew("FinalApproval_input.nmoc","q=2&MocKeyid="+MocKeyid,"FinalApprovalGrid","pager","","docDoubleClick","","FinalloadComFunction");	
 	 	 	
 	 		}
 			
 		 	
 		 }
 		 });
 		 
 		 function PSSRRecomment_successcallback(result){
 			
 			 var MOCStatus=result.MocStatus;
 	//	//////////////////////alert("MOCStatus"+MOCStatus);
 			 
 			 if(MOCStatus<=0){
 				 jQuery('#hdnMocStatus').val(MOCStatus);
 			 }
 			
 			 
 	//	////////////////////////////////////////alert("Click");
 			var  pssrCount=result.pssrCount;
 			//////////////////////////alert(pssrCount);
 			 if(pssrCount>=1){
 				
 			
 				jQuery('#hdnpssrCount').val("view");	
 			 }
 			 else{
 				////////////////////////alert("Else");
 				//jQuery('#hdnmode').val("create");
 				jQuery('#hdnpssrCount').val("create");	
 			 }
 			
 			 
 		 }
 		 function WH_successcallback(result){
  			
 			 var MOCStatus=result.MocStatus;
 			//////////////////////////alert("MOCStatus"+MOCStatus);
 			 
 		
 			  WHCount=result.WHCount;
 			////////////////////alert(WHCount);
 			 if(WHCount>=1){
 				
 				//jQuery('#hdnmode').val("view");
 				jQuery('#hdnWHCount').val("view");	
 			 }
 			 else{
 			//	//////////////////////alert("Else");
 				//jQuery('#hdnmode').val("create");
 				jQuery('#hdnWHCount').val("create");	
 			 }
 			
 			 
 		 }
 		
 		 
 		function Approval_successcallback(result){
 			
	var  TotalCount=result.TotalApproval;
	if(typeof TotalCount=="undefined"||TotalCount==""){
		 Maxgroup=1; 
	   //////////////////////////////////////alert("In if"+Maxgroup)
	   jQuery('#hdncount').val(1);
	}
 		 	var	ApprovalCount=result.CompletedApproval;
 			////////////////////////////////////////alert("ApprovalCount"+ApprovalCount)
 		 			var MaxGroupNo=result.MaxGroupNo;
 		 	//	//////////////////////////////////////alert("MaxGroupNo"+MaxGroupNo);
 		 		var nextApprovalno=result.NextGroupNo;
 		 		//////////////////////////////////alert("nextApprovalno:::::::Initial"+nextApprovalno);
 		 		if(typeof nextApprovalno=="undefined"||nextApprovalno==""){
 		 		var InitialClosed="Closed";
 		 		
 		 		   jQuery('#hdnInitialApproval').val(InitialClosed);
 		 		}
 		 		else{
 		 			//////////////////////////////////alert("Else");
 		 			var InitialPending="Pending";
 		 			jQuery('#hdnInitialApproval').val(InitialPending);	
 		 		}
 		 		if(ApprovalCount < TotalCount){
 		 			Maxgroup= parseInt(MaxGroupNo)
 		 			////////////////////////////////////////alert("Maxgroup < IF"+Maxgroup)
 		 			jQuery('#hdncount').val(Maxgroup);	 
 		 		}
           if(ApprovalCount==TotalCount)
 		 			Maxgroup= parseInt(MaxGroupNo)+1;
           if(nextApprovalno<=8){
	 			Maxgroup= nextApprovalno;
	 			
	 			////////////////////////////////////////alert("In if MaxGroup"+Maxgroup);
	 			jQuery('#hdncount').val(Maxgroup);
	 			}
 		 			////////////////////////////////////////alert("Maxgroup"+Maxgroup);
 		 		//	jQuery('#hdncount').val(Maxgroup);	 
 		 		 }	
 		
 		function HazopApproval_successcallback(result){
 			
 			var  TotalCount=result.HazopTotalApproval;
 			////////////////////alert("TotalCount"+TotalCount);
 			if(typeof TotalCount=="undefined"||TotalCount==""){
	 			 Maxgroup=1; 
	 		   //////////////////////////////////////alert("In if"+Maxgroup)
	 		   jQuery('#hdnHazopcount').val(Maxgroup);
	 		}
 	        var  ApprovalCount=result.HazopCompletedApproval;
 	   //////////////////alert("ApprovalCount"+ApprovalCount);
            var MaxGroupNo=result.HazopMaxGroupNo;
        ////////////////////alert("MaxGroupNo"+MaxGroupNo);
            var nextApprovalno=result.HazopNextGroupNo;
            //////////////////alert("nextApprovalno"+nextApprovalno);
            if(typeof nextApprovalno=="undefined"||nextApprovalno==""){
 		 		var HazopClosed="Closed";
 		 		
 		 		   jQuery('#hdnHazopApproval').val(HazopClosed);
 		 		}
 		 		else{
 		 			//////////////////alert("Else");
 		 			var HazopPending="Pending";
 		 			jQuery('#hdnHazopApproval').val(HazopPending);	
 		 		}
            if(ApprovalCount < TotalCount){
    	 
    	Maxgroup= parseInt(MaxGroupNo)
 	  jQuery('#hdnHazopcount').val(Maxgroup);	 
 		 		 		
                                        }
 	       if(ApprovalCount==TotalCount)
 	    	   
 		 		 			
 		   if(nextApprovalno<=8){
 			 			Maxgroup= nextApprovalno;
 			 			////////////////////////////////////alert("Inse"+Maxgroup)
 			 			jQuery('#hdnHazopcount').val(Maxgroup);
 			 			}
 		
 		 		 		 }		 

 		function FinalApproval_successcallback(result){
 			
 			var  TotalCount=result.FinalTotalApproval;
 			var  MaxApprovalStatus=result.FinalMaxGroupStatus;
 			//////////////////////////alert("MaxApprovalStatus:::"+MaxApprovalStatus);
 			if(MaxApprovalStatus=='-'){
 				//////////////////////////alert("inside")
 				var FAStatus="Pending"
 				 jQuery('#hdnFinalMaxApprovalStatus').val(FAStatus);	
 			}
 			else{
 				var FAStatus="Completed"
 	 				 jQuery('#hdnFinalMaxApprovalStatus').val(FAStatus);	
 			}
 		 		 ////////////////////////////////////////alert("TotalCount"+TotalCount);
 		 		if(typeof TotalCount=="undefined"||TotalCount==""){
 		 			 Maxgroup=1; 
 		 		   //////////////////////////////////////alert("In if"+Maxgroup)
 		 		   jQuery('#hdnFinalcount').val(Maxgroup);
 		 		}
 		 		 	var	ApprovalCount=result.FinalCompletedApproval;
 		 			////////////////////////////////////////alert("ApprovalCount"+ApprovalCount)
 		 		 			var MaxGroupNo=result.FinalMaxGroupNo;
 		 		 	
 		 		 		var nextApprovalno=result.FinalNextGroupNo;
 		 		 	
 		 		 		if(ApprovalCount < TotalCount){
 		 		 			Maxgroup= parseInt(MaxGroupNo)
 		 		 			////////////////////////////////////////alert("Maxgroup < IF"+Maxgroup)
 		 		 			jQuery('#hdnFinalcount').val(Maxgroup);	 
 		 		 		}
 		           if(ApprovalCount==TotalCount)
 		 		 			//Maxgroup= parseInt(MaxGroupNo)+1;
 		           if(nextApprovalno<=8){
 			 			Maxgroup= nextApprovalno;
 			 			//////////////////////////////////////alert("In if MaxGroup"+Maxgroup);
 			 			jQuery('#hdnFinalcount').val(Maxgroup);
 			 			}
 		 		 			////////////////////////////////////////alert("Maxgroup"+Maxgroup);
 		 		 		//	jQuery('#hdncount').val(Maxgroup);	 
 		 		 		 }		 

	function MOCClosure_successcallback(result){
 			
 		var  ClosureCount=result.ClosureCount;
 		////////alert("ClosureCount:::"+ClosureCount);
 		
 		 if(ClosureCount >= 1){
 		jQuery('#hdnClosurecount').val("create"); 
 		}
else{
 	    jQuery('#hdnClosurecount').val("view");
 		}
 		}	
 		
function addRowPssr(row)
{
	////////////////////////////////////////////////////alert("Inside")
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnRasdKeyid:" ",txtRasdActivity:" ",txtRasdConsequence:" ",txtRasdHazard:" ",txtRasdCause:" ",
			 	txtRasdProbablityid:" ",cmbRasdProbablityid:" ",txtRasdSeviorityid:" ",cmbRasdSeviorityid:" ",txtRasdRiskval:" ",txtRasdRisklevelid:" ",
			 	cmbRasdRisklevelid:" ",txtRasdControltypeid:" ",cmbRasdControltypeid:" ",txtRasdControls:" ",hdnRasdActplan:" ",btnactplan:" " ,lblactplanstaus:" "}];
		jQuery("#PSSRReccomendGrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnRasdKeyid:" ",txtRasdActivity:" ",txtRasdConsequence:" ",txtRasdHazard:" ",txtRasdCause:" ",
		 	txtRasdProbablityid:" ",cmbRasdProbablityid:" ",txtRasdSeviorityid:" ",cmbRasdSeviorityid:" ",txtRasdRiskval:" ",txtRasdRisklevelid:" ",
		 	cmbRasdRisklevelid:" ",txtRasdControltypeid:" ",cmbRasdControltypeid:" ",txtRasdControls:" ",hdnRasdActplan:" ",btnactplan:" " ,lblactplanstaus:" "}];
		jQuery("#PSSRReccomendGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

jQuery("#btnAddHazop").click(function(){
	
	var row = jQuery("#Hazopgrid").jqGrid("getDataIDs");
	addRowHazop(row);
});	

function addRowHazop(row){

	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnMohdKeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
			 txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
			 cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",txtMohdRemarks:" "}];
		jQuery("#Hazopgrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnMohdKeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
			txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
		 	cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",txtMohdRemarks:" "}];
		jQuery("#Hazopgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);	
		//NewHazopGridLoadCom();
	 }
}

jQuery("#btnAddwhatif").click(function(){
//	//////////////////////////////////////////////alert("Click");
	var row = jQuery("#WhatifGrid").jqGrid("getDataIDs");
	addRowWhatIf(row);
});
function addRowWhatIf(row){
	
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnWifdKeyid:" ",txtWifdWhatIf:" ",txtWifdCauses:" ",txtWifdCosequeces:" ",
			 	txtWifdLikeHood1:" ",cmbWifdLikeHood1:" ",txtWifdSeverity1:" ",cmbWifdSeverity1:" ",txtWifdRisk1:" ",txtWifdWithoutSafeGuards:" ",
			 	txtWifdRecommentations:" ",txtWifdLikeHood2:" ",cmbWifdLikeHood2:" ",txtWifdSeverity2:" ",
			 	cmbWifdSeverity2:" ",txtWifdRisk2:" ",txtWifdRemarks:" "}];
		jQuery("#WhatifGrid").jqGrid('addRowData',1, emptyItem[0]);
		//NewWhatifGridLoadCom();
	 }	
	 else
	 {
		// //////////////////////alert("Inside 1");
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnWifdKeyid:" ",txtWifdWhatIf:" ",txtWifdCauses:" ",txtWifdCosequeces:" ",
		 	txtWifdLikeHood1:" ",cmbWifdLikeHood1:" ",txtWifdSeverity1:" ",cmbWifdSeverity1:" ",txtWifdRisk1:" ",txtWifdWithoutSafeGuards:" ",
		 	txtWifdRecommentations:" ",txtWifdLikeHood2:" ",cmbWifdLikeHood2:" ",txtWifdSeverity2:" ",
		 	cmbWifdSeverity2:" ",txtWifdRisk2:" " ,txtWifdRemarks:" "}];
		  jQuery("#WhatifGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
      }
		}
		
function NewHazopGridLoadCom(){
	
    var row = jQuery("#Hazopgrid").jqGrid('getDataIDs');
 ////////////////////////alert(row);
	 var cm = jQuery("#Hazopgrid").jqGrid("getGridParam", "colModel");
	////////////////////////alert("inside "+row);
	 for(var i=0;i<row.length;i++)
	 {
		
			jQuery('#Hazopgrid').setSelection(row[i], true);
			 jQuery('input:checkbox[id=jqgh_Hazopgrid_'+row[i]+']').attr('checked',true);
			    jQuery("#Hazopgrid").jqGrid('setCell',row[i],'selctVal','1');
		  }

	 }
function WhatifGrid_selectRow(rowId)
{
	var jqGridId="WhatifGrid";
	/* disableField("frmMocProject","cmbWifdRisk1_"+jqGridId+"_"+rowId);
	disableField("frmMocProject","cmbWifdRisk2_"+jqGridId+"_"+rowId); */
	jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		////////////////////////////////////////////////alert("onLoadSuccess:function");		
		
		var prob=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
	
		var calps=parseInt(prob)*parseInt(sev);
		
	}});
		
}
function cmbWifdLikeHood1_WhatifGrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	calPS(rowId);
}
function cmbWifdSeverity1_WhatifGrid_onSelect(record,rowId){
	calPS(rowId);
}

function calPS(rowId){
	var prob=getFieldValue("cmbWifdLikeHood1_WhatifGrid_"+rowId);
	////////////////////////////////////////////////alert("prob"+prob);
	var sev=getFieldValue("cmbWifdSeverity1_WhatifGrid_"+rowId);
	////////////////////////////////////////////////alert("sev"+sev);
	processAjaxCalls('getRiskLevel.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal_OnSuccess','riskVal_OnError');
}
function riskVal_OnSuccess(result){	
	////////////////////////////////////////////////alert("success");
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	////////////////////////////////////////////////alert("the riskVal"+riskVal);
	var jqGridId="WhatifGrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtWifdRisk1',riskVal);
    setFieldValue("#txtWifdRisk1_"+jqGridId+"_"+rowId,riskVal);	
	//var calps=parseInt(prob)*parseInt(sev);
}

function cmbWifdLikeHood2_WhatifGrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	callPS(rowId);
}
function cmbWifdSeverity2_WhatifGrid_onSelect(record,rowId){
	callPS(rowId);
}


function callPS(rowId){
	var jqGridId="WhatifGrid";
	
	var WhatifLikelyhood1=jQuery("#cmbWifdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
	////////////////////////////////alert(WhatifLikelyhood1)
	
	////////////////////////////////alert(WhatifSeverity1)
	var Whatiflikelyhood2=jQuery("#cmbWifdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	////////////////////////////////alert(Whatiflikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	if(Whatiflikelyhood2>=0){
	if((WhatifLikelyhood1>=Whatiflikelyhood2)){
		////////////////////////////////alert("IF");
	var prob=getFieldValue("cmbWifdSeverity1_WhatifGrid_"+rowId);
	var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
	processAjaxCalls('getRiskLevel1.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
	}
	else
	{
	////////////////////////////////alert("else");
	popupCommonErrorMsg("Likelyhood & Severity should be Less than or Equal to Likelyhood & Severity of Without Safeguards  ");
	return false;	
	}
	}
	var WhatifSeverity1=jQuery("#cmbWifdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var WhatifSeverity2=jQuery("#cmbWifdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(WhatifSeverity2>=0){
	if(WhatifSeverity1>=WhatifSeverity2){
		////////////////////////////////alert(" SevirityIF");
		var prob=getFieldValue("cmbWifdLikeHood2_WhatifGrid_"+rowId);
//////////////////////////////alert(prob)
		var sev=getFieldValue("cmbWifdSeverity2_WhatifGrid_"+rowId);
//////////////////////////////alert(sev)
		processAjaxCalls('getRiskLevel1.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal1_OnSuccess','riskVal1_OnError');
			
	} 
		 else
			{
			////////////////////////////////alert("else");
			popupCommonErrorMsg("Severity & Likelyhood  should be Less than or Equal to severity & Likelyhood of Without Safeguards ");
			return false;	
			}

	}
	
	}
function riskVal1_OnSuccess(result){	
	////////////////////////////////////////////////alert("success");
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	//////////////////////////////alert("risk"+riskVal);
	var jqGridId="WhatifGrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtWifdRisk2',riskVal);
	
   
	//var calps=parseInt(prob)*parseInt(sev);
    var WhatifRisk1=jQuery("#WhatifGrid").jqGrid('getCell',rowId,"txtWifdRisk1");
    var WhatifRisk2=jQuery("#WhatifGrid").jqGrid('getCell',rowId,"txtWifdRisk2");
    if(WhatifRisk1==WhatifRisk2){
    	//////////////////////////////alert("Risk value Should not greater that Risk Value Without Safeguard");
       // return false;
        jQuery("#txtWifdRisk2_"+jqGridId+"_"+rowId).val('');
        return false;
}
}

function QuestionaireGrd_selectRow(rowId)

{
	
	//var flag = jQuery(this).find('#QuestionaireGrd'+chkRfcqresponseY+' input[type=checkbox]').prop('checked');
	 var selected = this.jQuery.find(":checkbox").prop("checked");
	////////////////////////////////////////alert("flag"+selected)
    {
	////////////////////////////////////////alert("Inside")
	  }
	/* var Yes=getFieldValue("chkRfcqresponseY_QuestionaireGrd_"+rowId); */
	////////////////////////////////////////alert("Yes"+Yes);

}
function Hazopgrid_selectRow(rowId)
{
	var jqGridId="Hazopgrid";
	//disableField("frmMocProject","cmbMohdRisk1_"+jqGridId+"_"+rowId);
	//disableField("frmMocProject","cmbMohdRisk2_"+jqGridId+"_"+rowId);
	//var txt=1;
	jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		////////////////////////////////////////////////alert("onLoadSuccess:function");		
		
		var prob=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
	
		var calps=parseInt(prob)*parseInt(sev);
		
	}});
		
}
function cmbMohdLikeHood1_Hazopgrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	callHazopPS(rowId);
}
function cmbMohdSeverity1_Hazopgrid_onSelect(record,rowId){
	callHazopPS(rowId);
}
/* function cmbRasdRisklevelid_WhatifGrid_onSelect(record,rowId){
	
} */

function callHazopPS(rowId){
	var prob=getFieldValue("cmbMohdLikeHood1_Hazopgrid_"+rowId);
	////////////////////////////////////////////////alert("prob"+prob);
	var sev=getFieldValue("cmbMohdSeverity1_Hazopgrid_"+rowId);
	////////////////////////////////////////////////alert("sev"+sev);
	processAjaxCalls('getRiskLevel2.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal2_OnSuccess','riskVal2_OnError');
}
function riskVal2_OnSuccess(result){	
	////////////////////////////////////////////////alert("success");
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	var jqGridId="Hazopgrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtMohdRisk1',riskVal);
    setFieldValue("#txtMohdRisk1_"+jqGridId+"_"+rowId,riskVal);	
	//var calps=parseInt(prob)*parseInt(sev);
}

function cmbMohdLikeHood2_Hazopgrid_onSelect(record,rowId){		 
	////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	callHazop1PS(rowId);
}
function cmbMohdSeverity2_Hazopgrid_onSelect(record,rowId){
	callHazop1PS(rowId);
}

function callHazop1PS(rowId){
	

	var jqGridId="Hazopgrid";
	var HazopLikelyhood1=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
//////////////////////////////alert(HazopLikelyhood1);
	

	var HazopLikelyhood2=jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	//////////////////////////////alert(HazopLikelyhood2);
	
	////////////////////////////////alert(WhatifSeverity2)
	if(HazopLikelyhood2>=0){
	if((HazopLikelyhood1>=HazopLikelyhood2)){
		////////////////////////////////alert("IF");
		var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');
}
	else
	{
	////////////////////////////////alert("else");
	popupCommonErrorMsg("Severity & Likelyhood should be Less than or Equal to Severity & Likelyhood of Without Safeguards  ");
	return false;	
	}
	}
	var HazopSeverity1=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var HazopSeverity2=jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(HazopSeverity2>=0){
	if(HazopSeverity1>=HazopSeverity2){
		////////////////////////////////alert(" SevirityIF");
	var prob=getFieldValue("cmbMohdLikeHood2_Hazopgrid_"+rowId);
		
		var sev=getFieldValue("cmbMohdSeverity2_Hazopgrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');	
	} 
		 else
			{
			////////////////////////////////alert("else");
			popupCommonErrorMsg("Severity & LikelyHood should be Less than or Equal to severity  & Likelyhood of Without Safeguards ");
			return false;	
			}
	}
 
 }
function riskVal3_OnSuccess(result){	
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	////////////////////////////////////////////alert(riskVal);
	var jqGridId="Hazopgrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtMohdRisk2',riskVal);
    setFieldValue("#txtMohdRisk2_"+jqGridId+"_"+rowId,riskVal);	
	//var calps=parseInt(prob)*parseInt(sev);
	//var Risk1=getFieldValue("#txtMohdRisk2_"+jqGridId+"_"+rowId);
	var HazopRisk1=jQuery("#Hazopgrid").jqGrid('getCell',rowId,"txtMohdRisk1");
	//////////////////////////////////////////alert("Risk1Risk1"+Risk1);
	var HazopRisk2=jQuery("#Hazopgrid").jqGrid('getCell',rowId,"txtMohdRisk2");	
	//////////////////////////////////////////alert("Risk2"+Risk2);
    if(HazopRisk1==HazopRisk2){
    //////////////////////////////alert("Risk value Should not greater that Risk Value Without Safeguard");

    }
    
}
jQuery("#btnoExcelView").click(function(){

//	alert(1234);
	var MocKeyid=jQuery("#txtRfcmKeyid").val();
	var DMTId= jQuery("#cmbrfcmdmtid").combobox("getValue");
	
	var JHId=jQuery("#cmbRfcmjhid").combobox("getValue");
	
    var flid=jQuery('#hdnflid').val();
   alert("flid"+flid);
    var Suggestion=jQuery("#txtKzbnKaizen").val();
// alert("Suggestion  "+Suggestion);
    var SuggestionId=jQuery("#txtRfcmsuggestionid").val();
//  alert("SuggestionId"+SuggestionId);
    var MOCDate=jQuery("#dteRfcmdate").val();// datebox('getValue');
//   alert("MOCDate"+MOCDate);
   var Title=jQuery("#txtRfcmtitle").val();
    var Detail=jQuery("#txtRfcmdetail").val();
 //  alert(Detail+"111111");
    var PCHange=jQuery("#txtRfcmdescription").val();
  // alert(PCHange+".....");
    var Type = jQuery("#cboRfcmtype").val();
//  alert("Type"+Type);
    var Nature = jQuery("#cboRfcmnature").val();
   // //alert("Nature"+Nature);
    var plrtype = getComboBoxText("cmbRfcmempid");
    var Initiator=plrtype.substring(0,plrtype.indexOf("-"));
    //alert(Initiator);
    var Facility=jQuery("#txtWifmFacility").val();
    var Team=jQuery("#txtWifmTeam").val();
    var WhatifDate=jQuery("#dteWifmDate").val();//datebox('getValue');
//alert(569);
var HazopFacility=jQuery("#txtHzomFacility").val();
var HazopTeam=jQuery("#txtHzomTeam").val();
var HazopNode=jQuery("#txtHzomNode").val();
var HazopDesign=jQuery("#txtHzomDesignintent").val();
//alert(17895);

var HazopDate=jQuery("#dteHzomDate").val();//datebox('getValue');
var pidNo=jQuery("#txtHzomPidno").val();

var PssrFacility=jQuery("#txtPsrmprocess").val();
var MOCDetails=jQuery("#txtPsrmmocdetail").val();
//alert(7895);
	window.open("MOCExcelView_Excelview.nmoc?MocKeyid="+MocKeyid+"&DMTId="+DMTId+"&JHId="+JHId+"&flid="+flid+"&Suggestion="+Suggestion+"&MOCDate="+MOCDate+"&Title="+Title+"&Nature="+Nature+"&Initiator="+Initiator+"&SuggestionId="+SuggestionId+"&PCHange="+PCHange+"&Detail="+Detail+"&Type="+Type+"&WhatifDate="+WhatifDate+"&Team="+Team+"&Facility="+Facility+"&HazopFacility="+HazopFacility+"&HazopTeam="+HazopTeam+"&HazopNode="+HazopNode+"&HazopDesign="+HazopDesign+"&HazopDate="+HazopDate+"&pidNo="+pidNo+"&PssrFacility="+PssrFacility+"&MOCDetails="+MOCDetails);	
});	

function riskassessmentgrid_selectRow(rowId)
{
	var jqGridId="riskassessmentgrid";
	jQuery("#cmbRasdSeviorityid_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
		//////////////////////////////////////////////////////alert("onLoadSuccess:function");		
		disableField("frmRiskAssessment","cmbRasdRisklevelid_"+jqGridId+"_"+rowId);
		var prob=jQuery("#cmbRasdProbablityid_"+jqGridId+"_"+rowId).combobox('getText');//getFieldValue("cmbRasdProbablityid_"+jqGridId+"_"+rowId);
		var sev=jQuery("#cmbRasdSeviorityid_"+jqGridId+"_"+rowId).combobox('getText'); //getFieldValue("cmbRasdSeviorityid_"+jqGridId+"_"+rowId);
		//////////////////////////////////////////////////////alert("prob"+prob+"sev"+sev);		
		//if(riskLev.trim()!="ACCEPTABLE")		
		//if(riskLevel.trim()!="RIL0000001");
		disableUIButton("btnactplan_"+jqGridId+"_"+rowId);	
		var calps=parseInt(prob)*parseInt(sev);
		//////////////////////////////////////////////////////alert(calps);
		if(parseInt(calps)>=3){
			enableUIButton("btnactplan_"+jqGridId+"_"+rowId);
		}	
	}});
	//
		
}
function cmbRasdProbablityid_riskassessmentgrid_onSelect(record,rowId){		 
	//////////////////////////////////////////////////////alert("jsp   "+Object.keys(record)+""+record.text+" id "+record.id+"  "+record);
	calPS(rowId);
}
function cmbRasdSeviorityid_riskassessmentgrid_onSelect(record,rowId){
	calPS(rowId);
}
function cmbRasdRisklevelid_riskassessmentgrid_onSelect(record,rowId){
	
}
function cmbRasdControltypeid_riskassessmentgrid_onSelect(record,rowId){
	//////////////////////////////////////////////////////alert(rowId);
}
jQuery("#btnAddNewReccommend").click(function()
		 {
			////////////////////////////////////////////////////alert("Clicked")
			 
			 var row  = jQuery("#PSSRReccomendGrid").jqGrid('getDataIDs');
			////////////////////////////////////////////////////alert("ROW"+row);
			 var rowId= jQuery("#PSSRReccomendGrid").jqGrid('getRowData',row);
			 ////////////////////////////////////////////////////alert("rowId"+rowId);
			 addRowPssr(row);
		
			 
    });
    


function isValidDate(dateCtrl,ctrlRowId){

	var approvalDate = getFieldValue(dateCtrl);
	var currentDate = getServerDateTime();
	var hdndate=jQuery("#hdncurrDate").val();
	if(approvalDate!=hdndate)
		{
	var stringdate=convertStringToDate(approvalDate);
	
	if(convertStringToDate(approvalDate)== currentDate)
		{
		}

	if(convertStringToDate(hdndate) > convertStringToDate(approvalDate))
	{  
		if(stringdate==convertStringToDate(hdndate))
			{
			  clearValidationErrorMsg(dateCtrl);
		    	return false;
			}
		else{
		 popupCommonErrorMsg('Should Not Enter Past Date');
		fillWithCurrentDate(dateCtrl);
		return false;
		}
	}
}
}


function frmMocProjectcmbRfcmjhid_onSelect(record) 	{
	
	var dataStr="&sectId="+record.id;
	loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMOCfunLocationValues","frmMocProject",dataStr);
	
	var cellId = jQuery("#frmMocProject input[id='cell']").val();
	var flid = jQuery("#frmMocProject input[id='flid']").val();
	reloadCombo("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter?cellId="+cellId+"&flid="+flid);
	
}

function frmMocProjectcmbrfcmdmtid_onSelect(record) 	{
	
	var dataStr="&sectId="+record.id;
	loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMOCfunLocationValues","frmMocProject",dataStr);
	
	var sectId = jQuery("#frmMocProject input[id='sect']").val();
	var flid = jQuery("#frmMocProject input[id='flid']").val();
	reloadCombo("frmMocProject","cmbRfcmjhid","sectionCombo.commonFilter?sectId="+sectId+"&flid="+flid);
	
} 


  
function frmMocProject_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var factId = "";
var pbuId=keyIds.pbuId;
//////////////////////////////////////////////////////alert("pbuid"+pbuId);
	var sectId = keyIds.sectId;	
	////////////////////////////////////////////////////alert("sectId"+sectId)
	var cellId=keyIds.cellId;
	setFieldValue('cmbRfcmjhid',keyIds.cellId);
	setFieldValue('cmbrfcmdmtid',keyIds.sectId);
	readOnlyFields("cmbRfcmjhid");
	readOnlyFields("cmbrfcmdmtid");
	
	if (sectId != null){	
		//setFieldValue('cmbRfcmjhid',keyIds.cellId);
		//////////////////////////////////////////////////////alert("IF");
	reloadCombo("frmMocProject","cmbRfcmjhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
}
	
	if (pbuId != null){	
		reloadCombo("frmMocProject","cmbrfcmdmtid","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
	}

if (keyIds.cellId!="null"){	
	//////////////////////////////////////////////////////alert("In CellIf")
	setFieldValue('cmbRfcmjhid',keyIds.cellId);
	//	reloadCombo("frmMocProject","cmbFobdname","employeefield.ehsb?&cellId="+keyIds.cellId);
}
	setFunctionalLocWidth('frmFieldObservation','625px');
}




function HazopReccommendGridSavebutton_onClick(result){
	////////////////////////alert("click");
	var rowid=result.rowId;
	////////////////////////alert("rowid"+rowid);
	var btnid=result.btnId;
	////////////////////////alert("btnid"+btnid);
	var refDocId =jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrmasterid");
	
	var ReccomendId =jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrKeyid");
	////////////////////////alert("ReccomendId"+ReccomendId)
	
	var mainTask = jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"txtMocrrecmnd");
    var doctype = "MOCR";
    var Mstkeyid = jQuery('#hdnMocKeyId').val();
	
    var	ActionPlan = jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"txtMocrrecmnd");
    

	var detailid=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrmasterid");
	////////////////////////////////////alert("detail"+detailid);
	var RecKeyid=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"hdnMocrKeyid");
    var flid = jQuery("#frmMocProject input[id='flid']").val();
	var pasdate= getFieldValue("dteRfcmdate", "frmMocProject");
	
		 var keyid=jQuery('#hdnMocKeyId').val();
		 var Reccommendation=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowid,"txtMocrrecmnd");
	 	
	 	 var Responsiblity=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowid).combobox("getValue");
	 
	 	 var targetDate=jQuery("#dteMocrTargetDate_HazopReccommendGrid_"+rowid).datebox('getValue');
	 	
	    var ActionPlanStatus=getFieldValue("cmbMocrStatus_HazopReccommendGrid_"+rowid);
	  //////////////alert(ActionPlanStatus);
	   var ActionPlanId=jQuery("#HazopReccommendGrid").jqGrid('getCell', rowid, "txtMocrActionplanId"); 
	    if(ActionPlanStatus=="C"){
	    	//////////////alert("inside");
	    	var currentDate = getCurrentDate();
	        var compdate = jQuery("#dteMocrTargetDate_HazopReccommendGrid_"+rowid).datebox("getValue");
			
			if( compareDate(compdate,currentDate) < 0 )
			{
			
				popupCommonErrorMsg("Completed date can not be greater than current date");
				fillWithCurrentDate("dteMocrTargetDate_HazopReccommendGrid_"+rowid);
				return false;
			}

	    }	
	   
	   //   //////////////////////alert("ActionPlanId"+ActionPlanId);
 //var Reccommendconvert =  getGridSelectArray("HazopReccommendGrid");
//

var Reccommendconvert= convertJsonArrReccommend(rowid);
   // //////////////////////alert("Reccommendconvert"+Reccommendconvert); 
	var title= jQuery("#hdntitle").val("WhatIf");
 	 			 var MocKeyid=jQuery("#txtRfcmKeyid").val();
	
//	saveForm('frmMocProject','MocReccommendation_save.nmoc?&rowid='+rowid+"&MocKeyid="+MocKeyid+"&Reccommendconvert="+Reccommendconvert+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype);
  // saveForm("frmMocProject","MocReccommendation_save.nmoc?&MocKeyid="+MocKeyid+"&rowid="+rowid+"&Reccommendconvert="+Reccommendconvert+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype);		
   processAjaxCalls("MocReccommendation_save.nmoc","&MocKeyid="+MocKeyid+"&rowid="+rowid+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype+"&flid="+flid+"&Reccommendconvert="+Reccommendconvert+"&ReccomendId="+ReccomendId,"Reccommendation_successCallBack","");	
			
}
function convertJsonArrReccommend(rowid){
	var allrow = jQuery("#HazopReccommendGrid").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	var RecmdId="";
	var MocId="";
    var MasterId=""; 
    var Category=""; 
    var Reccommend="";
    var Responsibility="";
    var TargetDate="";
    var Status=""; 
 
            
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':disabled')==true)
			{
			continue;
			}
		
		if (jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':checked')&& rowno==rowid) {
			////////////////////////alert("inside"+rowid);
			jsonArrO += '{';
			RecmdId=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"hdnMocrKeyid");
			MasterId=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"hdnMocrmasterid");
			MocId=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"hdnMocrMocid");
			TargetDate=getFieldValue("dteMocrTargetDate_HazopReccommendGrid_"+rowno);
			Category=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"txtMocrCategory");
			Reccommend=jQuery("#HazopReccommendGrid").jqGrid('getCell',rowno,"txtMocrrecmnd");
			Responsibility=getFieldValue("cmbMocrResponsibility_HazopReccommendGrid_"+rowno);
		 //  Responsiblity=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowno).combobox("getValue");
		
			
			Status=getFieldValue("cmbMocrStatus_HazopReccommendGrid_"+rowno);
			// //////////////////////alert("Status:"+Status);  
				  jsonArrO += '"hdnMocrKeyid":"' + RecmdId + '",';
				  jsonArrO += '"hdnMocrmasterid":"' + MasterId + '",';
				  jsonArrO += '"hdnMocrMocid":"' + MocId + '",';
				
				  jsonArrO += '"txtMocrCategory":"' + Category + '",';
				  jsonArrO += '"txtMocrrecmnd":"' + Reccommend + '",';
				  jsonArrO += '"cmbMocrResponsibility":"' + Responsibility + '",';
				  
				  jsonArrO += '"dtePtaskTargetdate":"' + TargetDate + '",';
				 
				  
				  jsonArrO += '"cmbMocrStatus":"' + Status+ '"},';
				
				
			   //  //////////////////////alert(jsonArrO);
		}
	}
	return '[' + jsonArrO.slice(0, -1) + ']';
}

function PSSRReccomendGridbtnPssrRecSave_onClick(result){
var rowid=result.rowId;
	
	var btnid=result.btnId;
	
	var refDocId =jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrmasterid");
	
	var ReccomendId =jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrKeyid");
	var mainTask = getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowid);
	////////////alert(mainTask)
    var doctype = "PSRR";
    var Mstkeyid = jQuery('#hdnMocKeyId').val();
	
    var	ActionPlan = getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowid);
    var detailid=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrKeyid");
	var RecKeyid=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowid,"hdnPsrrKeyid");
    var PssrCategory=getFieldValue("selPsrrCategory_PSSRReccomendGrid_"+rowid);
    var flid = jQuery("#frmMocProject input[id='flid']").val();
	var pasdate= getFieldValue("dteRfcmdate", "frmMocProject");
	 var keyid=jQuery('#hdnMocKeyId').val();
	 var Reccommendation=getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowid);
	 var Responsiblity=jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowid).combobox("getValue");
	 var targetDate=jQuery("#dtePsrrTargetDate_PSSRReccomendGrid_"+rowid).datebox('getValue');
	 var ActionPlanStatus=getFieldValue("cmbPsrrStatus_PSSRReccomendGrid_"+rowid);
	 var ActionPlanId=jQuery("#PSSRReccomendGrid").jqGrid('getCell', rowid, "txtMocrActionplanId"); 
	    if(ActionPlanStatus=="C"){
	    	////////////alert("inside");
	    	var currentDate = getCurrentDate();
	        var compdate = jQuery("#dtePsrrTargetDate_PSSRReccomendGrid_"+rowid).datebox("getValue");
			
			if( compareDate(compdate,currentDate) < 0 )
			{
			
				popupCommonErrorMsg("Completed date can not be greater than current date");
				fillWithCurrentDate("dtePsrrTargetDate_PSSRReccomendGrid_"+rowid);
				return false;
			} 
			}
	    
var PSSRChkReccommendconvert= convertJsonArrPssrChkReccommend(rowid);
  ////////////alert("PSSRChkReccommendconvert"+PSSRChkReccommendconvert); 
	var title= jQuery("#hdntitle").val("WhatIf");
    var MocKeyid=jQuery("#txtRfcmKeyid").val();
	processAjaxCalls("MocPSSRChkReccommendation_save.nmoc","&MocKeyid="+MocKeyid+"&rowid="+rowid+"&title="+title+"&detailid="+detailid+"&RecKeyid="+RecKeyid+"&ActionPlanId="+ActionPlanId+"&ActionPlanStatus="+ActionPlanStatus+"&targetDate="+targetDate+"&Responsiblity="+Responsiblity+"&Reccommendation="+Reccommendation+"&doctype="+doctype+"&flid="+flid+"&PSSRChkReccommendconvert="+PSSRChkReccommendconvert+"&ReccomendId="+ReccomendId,"PssrRecChkReccommendation_successCallBack","");	

}

 
 function convertJsonArrPssrChkReccommend(rowid){
		var allrow = jQuery("#PSSRReccomendGrid").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		var RecmdId="";
		var MocId="";
	    var MasterId=""; 
	    var Category=""; 
	    var Reccommend="";
	    var Responsibility="";
	    var TargetDate="";
	    var Status=""; 
	        
		for ( var i=0; i<allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i)+1;
			if(jQuery('#jqg_PSSRReccomendGrid_'+rowno).is(':disabled')==true)
				{
				continue;
				}

			if (jQuery('#jqg_PSSRReccomendGrid_' + rowno).is(':checked')) {
				jsonArrO += '{';
				RecmdId=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"hdnPsrrKeyid");
				//////////////alert("RecmdId"+RecmdId);
				MasterId=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"hdnPsrrmasterid");
				//////////////alert("MasterId"+MasterId);
				TargetDate=getFieldValue("dtePsrrTargetDate_PSSRReccomendGrid_"+rowno);
				//////////////alert("TargetDate"+TargetDate);
				Category=getFieldValue("selPsrrCategory_PSSRReccomendGrid_"+rowno);
			//	////////////alert("Category"+Category);
				//Reccommend=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"txtPsrrrecmnd");
				Reccommend = getFieldValue("txtPsrrrecmnd_PSSRReccomendGrid_"+rowno);
			//	////////////alert("Reccommend"+Reccommend);

				Responsibility=getFieldValue("cmbPssrResponsibility_PSSRReccomendGrid_"+rowno);
				//////////////alert("Responsibility"+Responsibility);
				Status=getFieldValue("cmbPsrrStatus_PSSRReccomendGrid_"+rowno);
				//Status=jQuery("#PSSRReccomendGrid").jqGrid('getCell',rowno,"cmbPsrrStatus");
				//Status=jQuery("#cmbPsrrStatus_PSSRReccomendGrid_"+rowno).combobox("getValue");
			//	////////////alert("status"+Status);
					  jsonArrO += '"hdnPsrrKeyid":"' + RecmdId + '",';
					  jsonArrO += '"hdnPsrrmasterid":"' + MasterId + '",';
					//  jsonArrO += '"hdnMocrMocid":"' + MocId + '",';
					  jsonArrO += '"dtePsrrTargetDate":"' + TargetDate + '",';
					  jsonArrO += '"selPsrrCategory":"' + Category + '",';
					  jsonArrO += '"txtPsrrrecmnd":"' + Reccommend + '",';
					  jsonArrO += '"cmbPssrResponsibility":"' + Responsibility + '",';
					  jsonArrO += '"cmbPsrrStatus":"' + Status+ '"},';
					
					
				//    ////////////alert(jsonArrO);
			}
		}
		return '[' + jsonArrO.slice(0, -1) + ']';
	}


 
function Reccommendation_successCallBack(result) {
           //alert("Data Saved Successfully");
			var MocKeyid=jQuery("#hdnMocKeyId").val();
			 processGridnew("HazopRecommendations_input.nmoc","q=2&MocKeyid="+MocKeyid,"HazopReccommendGrid","pager","RFC QUESTIONNAIRE","","","HazopReccommendloadComFunction");	
		 	 		
}
 function PssrRecChkReccommendation_successCallBack(result) {
alert("Data Saved Successfully");
		var MocKeyid=jQuery("#hdnMocKeyId").val(); 
processGridnew("PSSRRecommendations_input.nmoc","q=2&MocKeyid="+MocKeyid,"PSSRReccomendGrid","pager","RFC QUESTIONNAIRE","docDoubleClick","","ReccommendloadComFunction");	
	 	 		 		
}

jQuery("#chkSelectAllQuestions").click(function(){
	   show_winMask(1);
    fnSelectAllQuestions();
	   show_winMask(0);
});

function  fnSelectAllQuestions(){
		var row = jQuery("#QuestionaireGrd").jqGrid('getDataIDs');
		//////////////////////alert("all");
		for(var i=0;i<row.length;i++)
		 {
			if(jQuery("#chkSelectAllQuestions").is(':checked')== true){
				var QuestionaireKey = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");
				//////////////////////alert(QuestionaireKey);
				if(QuestionaireKey.length>0){
					var jqGridId="QuestionaireGrd";
					 //jQuery("#dteEtcaAddDate_"+jqGridId+"_"+row[i]).attr('readonly','readonly'); 
					  jQuery("#cmbYes_"+jqGridId+"_"+row).attr('disabled',true);
					}
				jQuery('#QuestionaireGrd').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('checked',true);
			 //   setFieldValue("cmbYes_QuestionaireGrd_"row[i],'Y');
			
   			
			 jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','1');
			 }
			 else {
				    jQuery('#QuestionaireGrd').setSelection(row[i], false);
				    jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('checked',false);
				    jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','0');
                 
			 }
		 }	
	}



	
function HazopReccommendGrid_selectRow(rowId){
	var dateCtrl="dteMocrTargetDate_HazopReccommendGrid_"+rowId;
	formatDateBox(dateCtrl,'dd-MMM-yyyy');
	//fillWithCurrentDate(dateCtrl);
		 var jqGridId="HazopReccommendGrid";
			var whrecomment=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowId).combobox('getValue');
		//	//////////////////////alert(PssrResp); 
			var Resp=jQuery("#hdnuserid").val();
		//	//////////////////////alert("Resp"+Resp);
			if(whrecomment==Resp)
			{
			   jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
			}
			
			else{
				jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
				
			}
	jQuery("#"+dateCtrl).datebox({  	   
		onSelect:function(recordid)
			{ 
			isValidTargetDate1(dateCtrl,rowId);    	
			} 
		}); 
	
	
	
	jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowId).combobox({  	   
		onSelect:function(recordid)
			{ 
			 var jqGridId="HazopReccommendGrid";
			var whrecomment=jQuery("#cmbMocrResponsibility_HazopReccommendGrid_"+rowId).combobox('getValue');
		//	//////////////////////alert(PssrResp); 
			var Resp=jQuery("#hdnResponsibility").val();
		//	//////////////////////alert("Resp"+Resp);
			if(whrecomment==Resp)
			{
			   jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
			}
			
			else{
				jQuery("#cmbMocrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
				
			}
			} 
			
		});
	function isValidTargetDate1(dateCtrl,ctrlRowId){
		var sysdate=jQuery("#hdncurrentdate").val();
		var currdate=jQuery("#dteRfcmdate").datebox('getValue');
		var approvalDate = getFieldValue(dateCtrl);
		var currentDate = getServerDateTime();
	if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
			{	
			if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
				{
				popupCommonErrorMsg('Should Not Exceed the MOC Date');
				fillWithCurrentDate(dateCtrl);
				return false
			
		}
		
	}

	}
}

function PSSRReccomendGrid_selectRow(rowId)	{
		var dateCtrl="dtePsrrTargetDate_PSSRReccomendGrid_"+rowId;
		 var jqGridId="PSSRReccomendGrid";
			var PssrResp=jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowId).combobox('getValue');
		//	//////////////////////alert(PssrResp); 
			var Resp=jQuery("#hdnuserid").val();
		//	//////////////////////alert("Resp"+Resp);
			if(PssrResp==Resp)
			{
			   jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
			}
			
			else{
				jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
				
			}
		formatDateBox(dateCtrl,'dd-MMM-yyyy');
		//fillWithCurrentDate(dateCtrl);
		jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				isValidTargetDate(dateCtrl,rowId);    	
				} 
			}); 
			 


function isValidTargetDate(dateCtrl,ctrlRowId){
	var sysdate=jQuery("#hdncurrentdate").val();
	var currdate=jQuery("#dteRfcmdate").datebox('getValue');
	var approvalDate = getFieldValue(dateCtrl);
	var currentDate = getServerDateTime();
if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
		{	
		if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
			{
			popupCommonErrorMsg('Should Not Exceed the MOC Date');
			fillWithCurrentDate(dateCtrl);
			return false
		
	}
	
}

}

jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowId).combobox({  	   
	onSelect:function(recordid)
		{ 
		 var jqGridId="PSSRReccomendGrid";
		var PssrResp=jQuery("#cmbPssrResponsibility_PSSRReccomendGrid_"+rowId).combobox('getValue');
	//	//////////////////////alert(PssrResp); 
		var Resp=jQuery("#hdnResponsibility").val();
	//	//////////////////////alert("Resp"+Resp);
		if(PssrResp==Resp)
		{
		   jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',false); 
		}
		
		else{
			jQuery("#cmbPsrrStatus_"+jqGridId+"_"+rowId).attr('disabled',true);
			
		}
		} 
		
	});

}

function validateMandtoryforWahtif(gridSelArr){
	var selArr = JSON.parse(gridSelArr);
	for(var i = 0;i<selArr.length;i++){
		if(selArr[i].txtWifdWithoutSafeGuards.trim()==""){
			popupCommonErrorMsg("Enter the Present Safeguards");
			return false;
		}
		if(selArr[i].txtWifdRecommentations.trim()==""){
			popupCommonErrorMsg("Enter the Recommentations");
			return false;
		}	
	}
	return true;
	}

function validateMandtoryforHazop(gridSelArr){
	var selArr = JSON.parse(gridSelArr);
	for(var i = 0;i<selArr.length;i++){
		if(selArr[i].txtMohdWithoutSafeGuards.trim()==""){
			popupCommonErrorMsg("Enter the Present Safeguards");
			return false;
		}
		if(selArr[i].txtMohdRecommentations.trim()==""){
			popupCommonErrorMsg("Enter the Present Safeguards");
			return false;
		}
	}
	return true;
	}

	 
	 function frmMocProject_beforeSubmit(){
		 var title=jQuery("#hdntitle").val();
		 var Masterid=jQuery("#hdnSuggestionId").val();
	     var MocKeyid=jQuery("#hdnMocKeyId").val();
	alert("Moc Keid otside if"+MocKeyid);
	      var Nature=jQuery("#cboRfcmnature").val();
		  var Initiator=jQuery("#cmbRfcmempid").combobox("getValue");
		  var Jh=jQuery("#cmbRfcmjhid").combobox("getValue");
		  var Dmt=jQuery("#cmbrfcmdmtid").combobox("getValue");
	      var MocType=jQuery("#cboRfcmtype").val();
		  var MOCTitle=jQuery("#txtRfcmtitle").val(); 
		  var Desc=jQuery("#txtRfcmdescription").val();
	 var Detail=jQuery("#txtRfcmdetail").val();
     var flid = jQuery("#frmMocProject input[id='flid']").val();
     var mocDate=jQuery("#dteRfcmdate").datebox("getValue");
     if(mocDate.length==0){
    	 popupCommonErrorMsg("Select The MOC Date");
    	 return false;
     }
	 //////////////////////////////////////////alert(flid);
if(title=="RequestforChange"){
		//////////////////////////////////////////////alert("Title::"+title);
		////////////////////////////////////////////////////alert("Request");
				var MocKeyid=jQuery("#hdnMocKeyId").val();
	//////////////////////////////////////////////alert("MocKeyid"+MocKeyid);
		 var BasisDetails =convertJsonArr();
     return "MocKeyid="+MocKeyid+"&BasisDetails="+BasisDetails+"&title="+title+"&Nature="+Nature+"&MocType="+MocType+"&Initiator="+Initiator+"&Jh="+Jh+"&Dmt="+Dmt+"&MOCTitle="+MOCTitle+"&Desc="+Desc+"&Detail="+Detail;	
		 
	}
	else if(title=="Questionnaire"){
		  var paramconvert =convertJsonArrQuestions();
//////////////////////alert(paramconvert);
		  return "MocKeyid="+MocKeyid+"&paramconvert="+paramconvert+"&title="+title;
	}
	
	else if(title=="MOCCLosure"){
		  var paramconvertClosure =convertJsonArrClosure();
		 //////////////////////////////////////////////alert("paramconvertClosure"+paramconvertClosure);
		  return "MocKeyid="+MocKeyid+"&paramconvertClosure="+paramconvertClosure+"&title="+title;
	}
	else if(title=="Psschecklist"){
var pssrconvert =  getGridSelectArray("PSSRGrid");
		////////////////////////////////////////////alert(pssrconvert);

					return "MocKeyid="+MocKeyid+"&pssrconvert="+pssrconvert+"&title="+title;
				
	}
	

	else if(title=="WhatIf"){
		var WhatifKeyId=jQuery("#hdnWhatifKey").val();
		////////////////////alert(WhatifKeyId);
		 var mockeyid=jQuery("#txtRfcmKeyid").val();
         var kaizeid=jQuery("#txtRfcmsuggestionid").val();
         var gridWhatifval=getGridSelectArray('WhatifGrid');
         if(gridWhatifval !=""){
        	 if(validateMandtoryforWahtif(gridWhatifval))
        		 
       		  return '&whatifDetails='+gridWhatifval+"&mockeyid="+mockeyid+"&kaizeid="+kaizeid+"&title="+title+"&WhatifKeyId="+WhatifKeyId;

         }
         return false;
		  ////////////////////////////////////////////////alert(gridData);
	 	 //  if(gridWhatifval.trim().length>0)	
				//return gridData;
	}
	
	
	else if(title=="Hazop"){
		 var mockeyid=jQuery("#txtRfcmKeyid").val();
		 var HazopKeyId=jQuery("#hdnHazopKey").val();
         var kaizeid=jQuery("#txtRfcmsuggestionid").val();
		 var gridHazopval=getGridSelectArray('Hazopgrid');
		 
		 if(gridHazopval !=""){
		 
			 if(validateMandtoryforHazop(gridHazopval))
			 return '&hazopDetails='+gridHazopval+"&mockeyid="+mockeyid+"&kaizeid="+kaizeid+"&title="+title+"&HazopKeyId="+HazopKeyId;
	 	//    if(gridHazopval.trim().length>0)	
			//	return gridData;
		 }
		 return false;
	}
	
	else if(title=="FinalApprovals"){
		// var Complete=jQuery("#chkMocClosed").val();
		var MOCCompleted;
		if(jQuery("#chkMocClosed").is(':checked')==true){
			//////////////////////////alert("inside")
		MOCCompleted="Y";	
		return "MOCCompleted="+MOCCompleted+"&MocKeyid="+MocKeyid+"&title="+title;
		}
		
	}

	//else if(title=="MOC Team"){
	//var gridval=convertJsonArrTeam();
//var Rowrow = jQuery("#TeamGrid").jqGrid('getDataIDs');
/* var Process = getFieldValue("cmbAbnmDetectedby_TeamGrid_"+8); 
//var ProcessCheck=jQuery('#MomAttcheckbox_'+ rowid +'_8').is(':checked');
var Mechanical=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+9);
var Electrical=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+10);
var Instrument=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+11);
var EHSHead=getFieldValue("cmbAbnmDetectedby_TeamGrid_"+15);

if(typeof Process=="undefined"||Process==""){

      popupCommonErrorMsg("Select The Process Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof Mechanical=="undefined"||Mechanical==""){

      popupCommonErrorMsg("Select The Mechanical Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof Electrical=="undefined"||Electrical==""){

      popupCommonErrorMsg("Select The Electrical Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof Instrument=="undefined"||Instrument==""){

      popupCommonErrorMsg("Select The Instrument Area Incharge for Initial Approval");
     
      return false; 
}
else if(typeof EHSHead=="undefined"||EHSHead==""){

      popupCommonErrorMsg("Select The EHSHead for Initial Approval");
     
      return false; 
} */
/* var gridval=convertJsonArrTeam();
//alert("Grid"+gridval); 
return "gridval="+gridval+"&title="+title+"&MocKeyid="+MocKeyid+"&BasisDetails="+BasisDetails+"&flid="+flid;
	} */
	else if(title=="MOC Team"){
	    var Rowrow = jQuery("#TeamGrid").jqGrid('getDataIDs');
	    var gridval = convertJsonArrTeam();
	    var SuggestionId = jQuery("#hdnSuggestionId").val();
	    var Suggestion = jQuery("#hdnSuggestion").val();
	    alert("Moc Keid indisde if"+MocKeyid);
	    return "gridval=" + gridval + 
	           "&title=" + title + 
	           "&MocKeyid=" + MocKeyid + 
	           "&flid=" + flid + 
	           "&SuggestionId=" + SuggestionId + 
	           "&Suggestion=" + Suggestion;
	}
		 
	 }
	 
	 function getSelectdRowsAtt(jqGridId, checkBoxColName, ckeckForSelColName){
			var Rowrow = jQuery("#TeamGrid").jqGrid('getDataIDs');
			var rowid ="";
			var jsonArrO = '[';
			for ( i =0; i <Rowrow.length; i++) {
				rowid = Rowrow[i];
			  var isChecked =jQuery("#chkMctcInitial_TeamGrid_"+rowid).is(':checked');
		    }		
		}

	 function PssrGridloadComFunction(){
			
		  
		    var row = jQuery("#PSSRGrid").jqGrid('getDataIDs');
			 var cm = jQuery("#PSSRGrid").jqGrid("getGridParam", "colModel");
		 	 var initial =jQuery("#hdnInitialApproval").val();
		 	 var hazop=	jQuery("#hdnHazopApproval").val();
////////////alert("initial"+initial);
////////////alert("hazop"+hazop);
	    	 if(initial=="Pending"||hazop=="Pending"){
	    		 for(var i=0;i<row.length;i++)	{
	    			 //////////////////////////////////alert("In If");
	    			 jQuery('input:checkbox[id=jqg_PSSRGrid_'+row[i]+']').attr('disabled',true);  	 
	    		 
	    			 } 
	    		 
	    	 }
	    	 else
	    		 {
			 for(var i=0;i<row.length;i++)
			 {
				 var detailsid = jQuery("#PSSRGrid").jqGrid('getCell',row[i],"hdnPsrdmasterid");
					var mode= jQuery('#hdnmode').val();
					////////////////////////////////////////////alert("mode"+mode);
					if(mode=="view"){
						//disableGridSort("TeamGrid");
						jQuery('#PSSRGrid').setSelection(row[i], false);
						 jQuery('input:checkbox[id=jqgh_PSSRGrid_'+row[i]+']').attr('checked',false);
						   jQuery("#TeamGrid").jqGrid('setCell',row[i],'selctVal','0');
						  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
						   jQuery('input:checkbox[id=jqg_PSSRGrid_'+row[i]+']').attr('disabled',true);
					}
				   if(detailsid.trim().length>0){ 
					    jQuery('#PSSRGrid').setSelection(row[i], false);
					    jQuery('input:checkbox[id=jqgh_PSSRGrid_'+row[i]+']').attr('checked',true);
					    jQuery("#PSSRGrid").jqGrid('setCell',row[i],'selctVal','1');
					    
					   jQuery('input:checkbox[id=jqg_PSSRGrid_'+row[i]+']').attr('disabled',true);
				   }
			 }
			 }	 
		}
	 function frmMocProject_successsCallback(result){
     var MocKeyid=result.successData.keyId;  
     //var MstKeyid=jQuery("#hdnMocKeyId").val();
     
    // jQuery("#hdnMocKeyId").val(MocKeyid);
    ////////////////////////////////////////////alert(MocKeyid);
    var momactnpln =result.successData.momactnpln;
    ////////////////////////////////////////////alert("momactnpln"+momactnpln);
    jQuery('#hdnactnmode').val(momactnpln);
    var rowid=result.RowId;
    jQuery('#hdnrowid').val(rowid);
     var MocSuggestionId=result.suggestionId;
     
     var MocKeyidClosure=result.MocKeyidClosure;
     //////////////////alert("MOC Closure Keyid"+MocKeyidClosure);
   //  va title=result.title;
    // //////////////////////////////////////////////alert(title);
   var WhatifKeyId=result.successData.WifmKeyid;
  // ////////////////////////////alert("WhatifKeyId"+WhatifKeyId);
   if(WhatifKeyId!=null){
	   jQuery("#hdnWhatifKey").val(WhatifKeyId);
   
   }
   else{
	   var WfKeyId=result.successData.WhatifKeyId;
	 //  ////////////////////////////alert("WhatifKeyId:::::::::::::"+WfKeyId);
	   jQuery("#hdnWhatifKey").val(WfKeyId);
   }
   
  // jQuery("#hdnWhatifKey").val(WhatifKeyId);
   
   var HzomKeyid=result.successData.HzomKeyid;
   ////////////////////////////alert("Hazop"+HzomKeyid);
   
   if(HzomKeyid!=null){
	   jQuery("#hdnHazopKey").val(HzomKeyid); 
   }
   else{
	   var HazopKey=result.successData.HazopKey;
	   ////////////////////////////alert("HAZOP KEY"+HazopKey);
	   jQuery("#hdnHazopKey").val(HazopKey); 
   }
   
    var filterString="&MocKeyid="+MocKeyid+"&MocSuggestionId="+MocSuggestionId;
	 jQuery("#txtRfcmKeyid").val(MocKeyid);
	// var title=jQuery("#hdntitle").val();
	 //jQuery("#hdntitle").val(title);
	 processAjaxCalls("InitialApprovalCount.nmoc","&MocKeyid="+MocKeyid,"Approval_successcallback","ApprovalCount_Errorcallback");
		
	 processGridnew("BasisofChange_input.nmoc","q=2&MocKeyid="+MocKeyid,"BasisGrd","BasisGrdpager","","","","Basisgridcompletecallback");
	 processGridnew("MOCTeamSusscess_input.nmoc","q=2&MocKeyid="+MocKeyid,"TeamGrid", "Teampagerid","","", "", "TeamloadComFunction");    
     processGridnew("InitialApproval_input.nmoc","q=2&MocKeyid="+MocKeyid,"InitialGrid","pager","RFC QUESTIONNAIRE","docDoubleClick","","InitialloadComFunction");	 
	 processGridnew("FinalApproval_input.nmoc","q=2&MocKeyid="+MocKeyid,"FinalApprovalGrid","pager","","docDoubleClick","","FinalloadComFunction");	
     processGridnew("HazopApproval_input.nmoc","q=2&MocKeyid="+MocKeyid,"HazopApprovalGrid","pager","","docDoubleClick","","HazoploadComFunction");	
     processGridnew("WhatIFEntry_input.nmoc","q=2&MocKeyid="+MocKeyid,"WhatifGrid","Whatifpager","Whatif", "","","");
     processGridnew("HazopEntry_input.nmoc","q=2&MocKeyid="+MocKeyid,"Hazopgrid","Hazoppager","Hazop", "","","");
 	 processGridnew("PSSRRecommendations_input.nmoc","q=2&MocKeyid="+MocKeyid,"PSSRReccomendGrid","pager","Reccommendation","docDoubleClick","","ReccommendloadComFunction");	
	 processGridnew("MocClosure_input.nmoc","q=2&MocKeyid="+MocKeyid,"MOCCGrid","pager","","","","MOCClosureloadComFunction");	
	 processGridnew("Questionaire_input.nmoc","q=2&MocKeyid="+MocKeyid,"QuestionaireGrd","pager","","","","QuestionnaireloadComFunction");	
	 processGridnew("HazopRecommendations_input.nmoc","q=2&MocKeyid="+MocKeyid,"HazopReccommendGrid","pager","RFC QUESTIONNAIRE","","","HazopReccommendloadComFunction");	
	 				
	 }
	 
	 function WHconvertJsonArr(){
			var allrow = jQuery("#HazopReccommendGrid").jqGrid('getRowData');
			var jsonArrO = '';
			var val = "";
			for ( var i = 0; i < allrow.length; i++) {
				var row = allrow[i];
				var rowno = parseInt(i) + 1;
				if(jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':disabled')==true)
					{
					continue;
					}
		           
		            
				
				if (jQuery('#jqg_HazopReccommendGrid_' + rowno).is(':checked')) {
					
					jsonArrO += '{';
					for ( var colName in row) {
						if (row[colName].substring(0, 6) != '<input') {
							jsonArrO += '"' + colName + '":"' + row[colName] + '",';
						} else {
							var x = row[colName].indexOf("id=") + 4;
							var y = row[colName].substring(x);
							var z = y.indexOf('"');
							var cellId = y.substring(0, z);
							if (jQuery("#" + cellId).attr("type") == "checkbox"){
								val = jQuery('#' + cellId).is(':checked') ? 'Y'
										:'N';					  
							} else {
								val = jQuery('#' + cellId).val();
							}
							jsonArrO += '"' + colName + '":"' + val + '",';
						}
					}
					jsonArrO = jsonArrO.slice(0, -1) + '},';
				}
			}
			
			return '[' + jsonArrO.slice(0, -1) + ']';
		}
	 
	  function HazopReccommendloadComFunction()
		{
		      
				var rowid=jQuery('#hdnrowid').val();
				var MstKeyid=jQuery("#hdnMocKeyId").val();
			
				 var row = jQuery("#HazopReccommendGrid").jqGrid('getDataIDs');
				    ////////////////////////////////////////////////////alert("row"+row);
					 var cm = jQuery("#HazopReccommendGrid").jqGrid("getGridParam", "colModel");
				 for(var i=0;i<row.length;i++)
					 {
						 var detailsid = jQuery("#HazopReccommendGrid").jqGrid('getCell',row[i],"hdnMocrKeyid");
						//mo //////////////////////alert("Keyid"+detailsid);
						 var status = jQuery("#HazopReccommendGrid").jqGrid('getCell',row[i],"cmbMocrStatus");	
					   if(detailsid.trim().length>0){ 
						
							    jQuery('#HazopReccommendGrid').setSelection(row[i], false);
							  
							    jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('disabled',false);
							    jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('checked',true);
							    jQuery("#HazopReccommendGrid").jqGrid('setCell',row[i],'selctVal','1');
						   }
					   if(status=="C"){ 
		    			   ////////////////////////////////////////////////alert("status");
		    			    jQuery('#HazopReccommendGrid').setSelection(row[i], false);
		    			   jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('disabled',true);
		    			   jQuery('input:checkbox[id=jqg_HazopReccommendGrid_'+row[i]+']').attr('checked',false);

		    	   }
					   

					 }		
		
			
	} 

	    
	    function ReccommendloadComFunction()
		{
		        var momactnpln=jQuery('#hdnactnmode').val();
				var rowid=jQuery('#hdnrowid').val();
				var MstKeyid=jQuery("#hdnMocKeyId").val();
			
				 var row = jQuery("#PSSRReccomendGrid").jqGrid('getDataIDs');
				    ////////////////////////////////////////////////////alert("row"+row);
					 var cm = jQuery("#PSSRReccomendGrid").jqGrid("getGridParam", "colModel");
					 ////////////////////////////////////////////////////alert("cm"+cm);
					// var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
					  //////////////////////////////////////////////////////alert("////////////////////////////////////////////////////alertdetails"+detailsid);
					 for(var i=0;i<row.length;i++)
					 {
						 var detailsid = jQuery("#PSSRReccomendGrid").jqGrid('getCell',row[i],"hdnPsrrmasterid");	
					//////////////////////////////////////////////alert("Detail:::"+detailsid);
						 var status = jQuery("#PSSRReccomendGrid").jqGrid('getCell',row[i], "cmbPsrrStatus");
						// //////////////////////alert(status)
						 if(status=="C"){ 
							 jQuery('#PSSRReccomendGrid').setSelection(row[i], false);
							    jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('checked',false);
							    jQuery("#PSSRReccomendGrid").jqGrid('setCell',row[i],'selctVal','0');
							    
							   jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('disabled',true);
							 
						 }
						 else if(detailsid.trim().length>0){ 
							 //  ////////////////////alert("inside PSSR");   
							    jQuery('#PSSRReccomendGrid').setSelection(row[i], false);
							    jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('checked',false);
							    jQuery("#PSSRReccomendGrid").jqGrid('setCell',row[i],'selctVal','1');
							    
							   jQuery('input:checkbox[id=jqg_PSSRReccomendGrid_'+row[i]+']').attr('disabled',false);
						   }

					 }		
	
	}
	    function HazoploadComFunction(){
	    	
	  	  
	        var row = jQuery("#HazopApprovalGrid").jqGrid('getDataIDs');
	    	 var cm = jQuery("#HazopApprovalGrid").jqGrid("getGridParam", "colModel");
	    	 var initial =jQuery("#hdnInitialApproval").val();
	    	
	    		var WHCount=jQuery("#hdnWHCount").val();
	    	
	    	    var status = jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"selMctcHzaprovedStatus");	
	    		 var empId=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	    	     var Count=jQuery("#hdnHazopcount").val();
	    	     var rolelevel=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtGroupNo");
	    			//////////////////////////////////////alert("rolelevel"+rolelevel);
	    	     var userid=jQuery('#hdnuserid').val();
	    	 if(initial=="Pending"|| WHCount=="view"){
	    		 for(var i=0;i<row.length;i++)	{
	    		////////////////alert("In If");
	    			 jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);  	 
	    		 
	    			 } 
	    		 
	    	 }
	    	
	    	 else {
	    	 
	    	 for(var i=0;i<row.length;i++)
	    	 {
	    		 var status = jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"selMctcHzaprovedStatus");	
	    		 var empId=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	    	     var Count=jQuery("#hdnHazopcount").val();
	    	     var rolelevel=jQuery("#HazopApprovalGrid").jqGrid('getCell',row[i],"txtGroupNo");
	    			//////////////////////////////////////alert("rolelevel"+rolelevel);
	    	     var userid=jQuery('#hdnuserid').val();
	    /* 	     if(initial=="Pending"){
	    	    	
	    	    	  jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);  
	    	     }else{ */
	    		   if(status=="A"||status== "R"){ 
	    			   ////////////////////////////////////////////////alert("status");
	    			    jQuery('#HazopApprovalGrid').setSelection(row[i], false);
	    			   jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);
	    	   }
	    	
	    		   else if (Count==rolelevel && empId==userid){
				alert("Inside");
					   jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',false);
					   ////////////////////////////////////alert("after");   
				   }
	    		    else {
		 	    		alert("In else")
		 	    			   jQuery('input:checkbox[id=jqg_HazopApprovalGrid_'+row[i]+']').attr('disabled',true);    
		 	    		   } 
	    		
	    	     }
	    	   
	    	 }	
	    	
	    	 }
	    

	    function FinalloadComFunction(){
	    	
	    	   
	        var row = jQuery("#FinalApprovalGrid").jqGrid('getDataIDs');
	    	 var cm = jQuery("#FinalApprovalGrid").jqGrid("getGridParam", "colModel");
			 var initial =jQuery("#hdnInitialApproval").val();
	 //  alert("initial:::"+initial);
	    	 var Hazop=jQuery("#hdnHazopApproval").val();
	//  //////alert("Hazop:::"+Hazop);
	    	var Closurecount=jQuery("#hdnClosurecount").val();
	 //  ////alert("Closurecount"+Closurecount);
	    	   if((initial=="Closed" && Hazop=="Pending" && Closurecount=="view")||(initial=="Pending" && Hazop=="Pending" && Closurecount=="view")||(initial=="Closed" && Hazop=="Closed" && Closurecount=="view")||(initial=="Closed" && Hazop=="Pending" && Closurecount=="view")){
	    		   for(var i=0;i<row.length;i++)
	  	    	 {
	    			   
	  	 ////alert("insidee::::");
	    	    	  jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',true);  
	    	     }
	    	   }
	    	   else{
	    	 for(var i=0;i<row.length;i++)
	    	 {
	    		 var status = jQuery("#FinalApprovalGrid").jqGrid('getCell',row[i],"selMctcFaaprovedStatus");	
	    		 var empId=jQuery("#FinalApprovalGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	    		 var Count=jQuery("#hdnFinalcount").val();
	    		 var rolelevel=jQuery("#FinalApprovalGrid").jqGrid('getCell',row[i],"txtGroupNo");	
	    		 var userid=jQuery('#hdnuserid').val();
	    
	    		   if(status=="A"||status== "E"){ 
	    			   ////////////////////////////////////////////////alert("status");
	    			    jQuery('#FinalApprovalGrid').setSelection(row[i], false);
	    			   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',true);
	    		 }
	    		   
	    		   if (Count==rolelevel && empId==userid){
					  //////////////////////////////////////alert("Inside");
					   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',false);
					   
				   }
	    		 /*   else  if (empId==userid){
	    			   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',false);  
	    		   } */
	    		   else {
	    			////////////////////alert("In else")
	    			   jQuery('input:checkbox[id=jqg_FinalApprovalGrid_'+row[i]+']').attr('disabled',true);    
	    		   }
		    	     }
	    	 }	 
	    }


function InitialloadComFunction(){
	
	 ////////////////////////////////////////////////alert("Load Complete") 
    var row = jQuery("#InitialGrid").jqGrid('getDataIDs');

	 var cm = jQuery("#InitialGrid").jqGrid("getGridParam", "colModel");
	// jQuery('input:checkbox[id=jqg_InitialGrid_'+row+']').attr('disabled',true);
	 for(var i=0;i<row.length;i++)
	 {
		 var status = jQuery("#InitialGrid").jqGrid('getCell',row[i],"selMctcInaprovedStatus");	
		 ////////////////////////////////////////////////alert(status);
		 var Count=jQuery("#hdncount").val();
		
		 var rolelevel=jQuery("#InitialGrid").jqGrid('getCell',row[i],"txtGroupNo");
	
	     var empId=jQuery("#InitialGrid").jqGrid('getCell',row[i],"txtEmpmKeyid");
	
		 var userid=jQuery('#hdnuserid').val();
		 var rolelevel=jQuery("#InitialGrid").jqGrid('getCell',row[i],"txtGroupNo");
	//////////////////alert("rolelevel"+rolelevel);
		if(status=="A"||status== "R"){ 
			 
			    jQuery('#InitialGrid').setSelection(row[i], false);
			   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',true);
			
		   } 
	
		else if (Count==rolelevel && empId==userid){
				//////alert("elseif")
				   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',false);
				   ////////////////////////////////////alert("after");
				   
			   }
		else if(Count==null||Count==""){
				var Count=1;
				 if (Count==rolelevel && empId==userid){
						
					   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',false);
					   ////////////////////////////////////alert("after");
					   
				   }
			 }
		
		
	  else {
		  
			//////alert("if")
			 
			   jQuery('input:checkbox[id=jqg_InitialGrid_'+row[i]+']').attr('disabled',true);    
		   }
	 }	 
}








function viewGrid(filterString){ 
	  processGridnew("Questionaire_input.ehsb",ds,"QuestionaireGrd","pager","RFC QUESTIONNAIRE","docDoubleClick","","loadComFunction");	
	  processGridnew("RiskAssesmentEntry_input.risk?keyid="+keyid,"&q=1","riskassessmentgrid","riskassessmentpager","Risk Assessment", "","","gridLoadComplete");		 
		
}



jQuery("#btnAddnew").click(function(){
	var row = jQuery("#PrjReview").jqGrid("getDataIDs");
	addPrjRow(row);
});

function addPrjRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnSrrdKeyid:" ",hdnSrrdSrpmKeyid:" ",dteSrrdRevdate:" ",cmbSrrdReviewedby:" ",txtSrrdAction:" ",cmbSrrdResponsiblity:" ",dteSrrdTargetdate:" ",
			 chkSrrdCompleted:" ",dteSrrdComdate:" " }];
	jQuery("#PrjReview").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		// ////////////////////////////////////////////////////alert("INSIDE THE ESLE");
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		 var emptyItem =[{hdnSrrdKeyid:" ",hdnSrrdSrpmKeyid:" ",dteSrrdRevdate:" ",cmbSrrdReviewedby:" ",txtSrrdAction:" ",cmbSrrdResponsiblity:" ",dteSrrdTargetdate:" ",
	     chkSrrdCompleted:" ",dteSrrdComdate:" " }];
		jQuery("#PrjReview").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}



jQuery("#btnSaveQuestions").click(function() {
 	var modes=jQuery("#hdnmodes").val();
 	var MocKeyid=jQuery("#hdnMocKeyId").val();
//////////////////////////////////////////////////////alert("MocKeyid"+MocKeyid);

  var paramconvert =convertJsonArrQuestions();
 //////////////////////////////////////////////////////alert("Convert::"+paramconvert);
 processAjaxCalls("Questionaire_save.nmoc","&paramconvert="+paramconvert+"&MocKeyid="+MocKeyid,"Questionaries_successCallBack","");	
// saveForm("frmMocProject","Questionaire_save.nmoc?&paramconvert="+paramconvert+"&masterid="+masterid);
 });
 
function Questionaries_successCallBack(result) {
if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' ){
		popupCommonErrorMsg(result.tpmException);
	}
	else{
		var MocKeyid=jQuery("#hdnMocKeyId").val();
		processGridnew("Questionaire_input.nmoc","q=2&MocKeyid="+MocKeyid,"QuestionaireGrd","pager","","","",""," ");
	
	}
}

function TeamloadComFunction(){

    var row = jQuery("#TeamGrid").jqGrid('getDataIDs');
    ////////////////////////////////////////////////////alert("row"+row);
	 var cm = jQuery("#TeamGrid").jqGrid("getGridParam", "colModel");

	 
	 for(var i=0;i<row.length;i++)
	 {
		 var detailsid = jQuery("#TeamGrid").jqGrid('getCell',row[i],"hdnMctcKeyid");	
	//alert("Detail:::"+detailsid);
	var mode= jQuery('#hdnmode').val();
  ////////////////////////alert("mode"+mode);
	if(mode=="view"){
		//disableGridSort("TeamGrid");
		jQuery('#TeamGrid').setSelection(row[i], false);
		 jQuery('input:checkbox[id=jqgh_TeamGrid_'+row[i]+']').attr('checked',false);
		  // jQuery("#TeamGrid").jqGrid('setCell',row[i],'selctVal','0');
		  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
		   jQuery('input:checkbox[id=jqg_TeamGrid_'+row[i]+']').attr('disabled',true);
	}
	else if(mode=="create"||mode=="modify"){
		var jqGridId="TeamGrid";
		var initiator = jQuery('#hdnResponsibility').val();

		var id=1;

		var flid = jQuery("#frmMocProject input[id='section']").val();
/* alert("flid"+flid); */
		/* if(flid=="SEC0000016"){
		//BCM05205
			var Process='BCM05205';
			var Mechanical='BCM05203';
			var Inst='BCM04984';
			var Electrical='BCM05215';
			var EHSHead='EMP13892';
			//var EHSHead='EMP00001';
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,Process);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,Mechanical);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+10,Electrical);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+11,Inst);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+15,EHSHead);
		}

		else if(flid=="SEC0000014"){
			
			//BCM05205
				var id=1;
				var Process='BCM04938';
				var Mechanical='BCM05026';
				var Inst='BCM05078';
				var Electrical='BCM07043'; //BCM07043
				var EHSHead='EMP13892';
				//var EHSHead='EMP00001';
					setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,Process);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,Mechanical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+10,Electrical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+11,Inst);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+15,EHSHead);
				disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			}
			
		else if(flid=="SEC0000017"){
			
			//BCM05205
				var id=1;
				var Process='BCM05051';
				var Mechanical='BCM05843';
				var Inst='BCM05184';
				var Electrical='BCM05178';
				var EHSHead='EMP13892';
				//var EHSHead='EMP00001';
					setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,Process);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,Mechanical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+10,Electrical);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+11,Inst);
				setFieldValue("cmbAbnmDetectedby_TeamGrid_"+15,EHSHead);
				disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			}
			
			else
			{
				
				var id=1;
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
			jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator);
		//jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator);
			} */

		
		
			var JHLeader=jQuery("#hdnJHleader").val();
			var DMTLeader=jQuery("#hdnDMTLeader").val();
			var PA=jQuery("#hdnProcessArea").val();
			var MA=jQuery("#hdnMechArea").val();
				var IA=jQuery("#hdnInstrumentArea").val();
					var CA=jQuery("#hdnCivilArea").val();
						var EA=jQuery("#hdnElectricalArea").val();
							var PH=jQuery("#hdnPBUHead").val();
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+2,JHLeader);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+3,PA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+4,MA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+5,EA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+6,IA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+7,CA);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+9,PH);
			setFieldValue("cmbAbnmDetectedby_TeamGrid_"+8,DMTLeader);
		setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
		disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
		jQuery("#TeamGrid").jqGrid('setCell',row[0],'hdnMctcempid',initiator);
		jQuery("#TeamGrid").jqGrid('setCell',row[1],'hdnMctcempid',JHLeader);
		jQuery("#TeamGrid").jqGrid('setCell',row[2],'hdnMctcempid',PA);
		jQuery("#TeamGrid").jqGrid('setCell',row[3],'hdnMctcempid',MA);
		jQuery("#TeamGrid").jqGrid('setCell',row[4],'hdnMctcempid',EA);
		jQuery("#TeamGrid").jqGrid('setCell',row[5],'hdnMctcempid',IA);
		jQuery("#TeamGrid").jqGrid('setCell',row[6],'hdnMctcempid',CA);
		jQuery("#TeamGrid").jqGrid('setCell',row[7],'hdnMctcempid',DMTLeader);
		jQuery("#TeamGrid").jqGrid('setCell',row[8],'hdnMctcempid',PH);
		
			jQuery('#TeamGrid').setSelection(row[i], true);
			 jQuery('input:checkbox[id=jqgh_TeamGrid_'+row[i]+']').attr('checked',false);
			    jQuery("#TeamGrid").jqGrid('setCell',row[i],'selctVal','1');
			   // jQuery('input:checkbox[name=chkMctcHazop]').attr('disabled',true);
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[1]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[2]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[3]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[4]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[5]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[6]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[8]).attr('disabled','disable');
			   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[0]).attr('disabled','disable');
			    jQuery("#chkMctcfinal_"+jqGridId+"_"+row[1]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[2]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[3]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[4]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[5]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[6]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[0]).attr('disabled','disable');	
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[8]).attr('disabled','disable');
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[9]).attr('disabled','disable');
				 
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[0]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[1]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[2]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[3]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[4]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[5]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[6]).attr('checked',true);
				   jQuery("#chkMctcfinal_"+jqGridId+"_"+row[7]).attr('checked',true);
				   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[7]).attr('checked',true);
				   jQuery("#chkMctcHazop_"+jqGridId+"_"+row[9]).attr('checked',true);
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[7]).attr('disabled','disable');
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[8]).attr('disabled','disable');
				   jQuery("#chkMctcInitial_"+jqGridId+"_"+row[9]).attr('disabled','disable');
				  
				   
			   //  disableUIButton("txtAbnmCountermeasure_"+jqGridId+"_"+rowId);
			  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
			   jQuery('input:checkbox[id=jqg_TeamGrid_'+row[i]+']').attr('disabled',false);
		   
	}

	 }	 

}

function WhatifDtlgridLoadComplete(){	
    
    var mode= jQuery('#hdnmode').val();
    //////alert("View"+mode);
    var row = jQuery("#WhatifGrid").jqGrid('getDataIDs');
	 var cm = jQuery("#WhatifGrid").jqGrid("getGridParam", "colModel");
	for(var i=0;i<row.length;i++)
	 {
		 var detailsid = jQuery("#WhatifGrid").jqGrid('getCell',row[i],"hdnWifmKeyid");	
		 var mode= jQuery('#hdnmode').val();
			////////alert("mode"+mode);
			if(mode=="view"){
				jQuery('#WhatifGrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_WhatifGrid_'+row[i]+']').attr('checked',false);
				 
				   jQuery('input:checkbox[id=jqg_WhatifGrid_'+row[i]+']').attr('disabled',true);
			}
			else if(detailsid.trim().length>0){ 
			jQuery('#WhatifGrid').setSelection(row[i], false);
			 jQuery('input:checkbox[id=jqgh_WhatifGrid_'+row[i]+']').attr('checked',true);
			    jQuery("#WhatifGrid").jqGrid('setCell',row[i],'selctVal','1');
		   }

	 }	 
}

function HazopDtlgridLoadComplete(){
	
    var row = jQuery("#Hazopgrid").jqGrid('getDataIDs');
	 var cm = jQuery("#Hazopgrid").jqGrid("getGridParam", "colModel");
		 for(var i=0;i<row.length;i++)
	 {
		 var detailsid = jQuery("#Hazopgrid").jqGrid('getCell',row[i],"hdnMohdMohmKeyid");	
		 var mode= jQuery('#hdnmode').val();
			////////////////////////alert("mode"+mode);
			if(mode=="view"){
				//disableGridSort("TeamGrid");
				jQuery('#Hazopgrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_Hazopgrid_'+row[i]+']').attr('checked',false);
				   jQuery("#Hazopgrid").jqGrid('setCell',row[i],'selctVal','0');
				   jQuery('input:checkbox[id=jqg_Hazopgrid_'+row[i]+']').attr('disabled',true);
			}
			else  if(detailsid.trim().length>0){ 
			jQuery('#Hazopgrid').setSelection(row[i], false);
			 jQuery('input:checkbox[id=jqgh_Hazopgrid_'+row[i]+']').attr('checked',true);
			    jQuery("#Hazopgrid").jqGrid('setCell',row[i],'selctVal','1');
		   }

	 }	 
}


 function QuestionnaireloadComFunction(){
    var mode=jQuery("#mode").val();
    var row = jQuery("#QuestionaireGrd").jqGrid('getDataIDs');
    ////////////////////////////////////////////////////alert("row"+row);
	 var cm = jQuery("#QuestionaireGrd").jqGrid("getGridParam", "colModel");
	 ////////////////////////////////////////////////////alert("cm"+cm);
	// var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
	  //////////////////////////////////////////////////////alert("////////////////////////////////////////////////////alertdetails"+detailsid);
	 for(var i=0;i<row.length;i++) 
	 {
		 var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
	  ////////////////////////////////////////////////////alert("Detail:::"+detailsid);
			var mode= jQuery('#hdnmode').val();
			////////////////////////alert("mode"+mode);
			
			if(mode=="view"){
				//disableGridSort("TeamGrid");
				jQuery('#QuestionaireGrd').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqgh_QuestionaireGrd_'+row[i]+']').attr('checked',false);
				   jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','0');
				  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('disabled',true);
			}
			else if (mode=="create"||mode=="modify"){
				
				if(detailsid.trim().length>0){ 
			    jQuery('#QuestionaireGrd').setSelection(row[i], false);
			    jQuery('input:checkbox[id=jqgh_QuestionaireGrd_'+row[i]+']').attr('checked',true);
			    jQuery("#QuestionaireGrd").jqGrid('setCell',row[i],'selctVal','1');
			    jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"txtRfcqQuestions");
			   jQuery('input:checkbox[id=jqg_QuestionaireGrd_'+row[i]+']').attr('disabled',true);
		   }
			}
	 }	 
} 
 function MOCClosureloadComFunction(){
	  //  var mode=jQuery("#mode").val();
	    var row = jQuery("#MOCCGrid").jqGrid('getDataIDs');
	    ////////////////////////////////////////////////////alert("row"+row);
		 var cm = jQuery("#MOCCGrid").jqGrid("getGridParam", "colModel");
	
		var pssrCount=jQuery("#hdnpssrCount").val();
		   var WHCount=jQuery("#hdnWHCount").val();
	 		////////alert("WHCount"+WHCount);
	 		var initial =jQuery("#hdnInitialApproval").val();
	 		////////alert("initial"+initial);
	 		var Hazop=jQuery("#hdnHazopApproval").val();
	 		////////alert("Hazop"+Hazop);
	 		////////alert("PssrCount"+pssrCount);	
	 		  var MOCStatus=jQuery("#hdnMocStatus").val();
	 			//////////alert("MOCStatus"+MOCStatus);
	 			  var mode= jQuery('#hdnmode').val();
	 			////////alert("mode"+mode);
			////////////alert("pssrCount"+pssrCount);
		 for(var i=0;i<row.length;i++) 
		 {
			 var detailsid = jQuery("#MOCCGrid").jqGrid('getCell',row[i],"hdnRfccKeyid");	
		  ////////////////////////////////////////////////////alert("Detail:::"+detailsid);
			 if(detailsid.trim().length>0){ 
				    jQuery('#MOCCGrid').setSelection(row[i], false);
				    jQuery('input:checkbox[id=jqgh_MOCCGrid_'+row[i]+']').attr('checked',true);
				    jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','1');
				  //  jQuery("#MOCCGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',true);
			   }
			 else 	if(pssrCount=="view" && initial=="Pending" && Hazop=="Pending" && WHCount=="view" || mode=="view") {
		  		//////alert("if "); 
		  		jQuery('#MOCCGrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('checked',false);
				   jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','0');
				
				   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',true);		
		  	}
			
		  	else if(pssrCount=="create" && mode=="create" && initial=="Closed" && Hazop=="Closed" && WHCount=="create"){
		  		////////alert("else if "); 
		  		jQuery('#MOCCGrid').setSelection(row[i], false);
				 jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('checked',false);
				   jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','0');
				  //  jQuery("#TeamGrid").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',false);
				   }
		  	
			   else{
					jQuery('#MOCCGrid').setSelection(row[i], false);
					 jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('checked',false);
					   jQuery("#MOCCGrid").jqGrid('setCell',row[i],'selctVal','0');
					
					   jQuery('input:checkbox[id=jqg_MOCCGrid_'+row[i]+']').attr('disabled',true);	
			   }

		 }	 
	}
  
 function Basisgridcompletecallback(){
	 
	    var row = jQuery("#BasisGrd").jqGrid('getDataIDs');
	    ////////////////////////////////////////////////////alert("row"+row);
		 var cm = jQuery("#BasisGrd").jqGrid("getGridParam", "colModel");
		 ////////////////////////////////////////////////////alert("cm"+cm);
		// var detailsid = jQuery("#QuestionaireGrd").jqGrid('getCell',row[i],"hdnRfcqKeyid");	
		  //////////////////////////////////////////////////////alert("////////////////////////////////////////////////////alertdetails"+detailsid);
		 for(var i=0;i<row.length;i++)
		 {
			 var detailsid = jQuery("#BasisGrd").jqGrid('getCell',row[i],"hdnRfcbrfcid");	
		//////////////////////////////////////////////alert("Detail:::"+detailsid);
				var mode= jQuery('#hdnmode').val();
				//////////////////////////////////////////alert("mode"+mode);
				if(mode=="view"){
					 jQuery('#BasisGrd').setSelection(row[i], false);
					 jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('checked',false);
					   jQuery("#BasisGrd").jqGrid('setCell',row[i],'selctVal','0');
					  //  jQuery("#TeamGrid") .jqGrid('getCell',row[i],"txtRfcqQuestions");
					   jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('disabled',true);
					   
				}
			  if(detailsid.trim().length>0){ 
				    jQuery('#BasisGrd').setSelection(row[i], true);
				    jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('checked',true);
				    jQuery("#BasisGrd").jqGrid('setCell',row[i],'selctVal','1'); 
				//    jQuery("#BasisGrd").jqGrid('getCell',row[i],"txtRfcqQuestions");
				   jQuery('input:checkbox[id=jqg_BasisGrd_'+row[i]+']').attr('disabled',false);
			   }

		 }	 
 }

 function convertJsonArr(){
		var allrow = jQuery("#BasisGrd").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			if(jQuery('#jqg_BasisGrd_' + rowno).is(':disabled')==true)
				{
				continue;
				}
	           
	            
			
			if (jQuery('#jqg_BasisGrd_' + rowno).is(':checked')) {
				
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					}
				}
				jsonArrO = jsonArrO.slice(0, -1) + '},';
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	}
 
 

  function convertJsonArrQuestions(){
	var allrow = jQuery("#QuestionaireGrd").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_QuestionaireGrd_' + rowno).is(':disabled')==true)
			{
			continue;
			}
           
            
		
		if (jQuery('#jqg_QuestionaireGrd_' + rowno).is(':checked')) {
			
			jsonArrO += '{';
			for ( var colName in row) {
				if (row[colName].substring(0, 6) != '<input') {
					jsonArrO += '"' + colName + '":"' + row[colName] + '",';
				} else {
					var x = row[colName].indexOf("id=") + 4;
					var y = row[colName].substring(x);
					var z = y.indexOf('"');
					var cellId = y.substring(0, z);
					if (jQuery("#" + cellId).attr("type") == "checkbox"){
						val = jQuery('#' + cellId).is(':checked') ? 'Y'
								:'N';					  
					} else {
						val = jQuery('#' + cellId).val();
					}
					jsonArrO += '"' + colName + '":"' + val + '",';
				}
			}
			jsonArrO = jsonArrO.slice(0, -1) + '},';
		}
	}
	
	return '[' + jsonArrO.slice(0, -1) + ']';
}
  
 /*  function convertJsonArrClosure(){
		var allrow = jQuery("#MOCCGrid").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			if(jQuery('#jqg_MOCCGrid_' + rowno).is(':disabled')==true)
				{
				continue;
				}
	           
	            
			
			if (jQuery('#jqg_MOCCGrid_' + rowno).is(':checked')) {
				
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					}
				}
				jsonArrO = jsonArrO.slice(0, -1) + '},';
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	} */
  
  function convertJsonArrClosure(){
		var allrow = jQuery("#MOCCGrid").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			var empid=getFieldValue("selRfccresponse_"+rowno);
			//////////////////////////////////////////////alert(empid);
			if (jQuery('#jqg_MOCCGrid_' + rowno).is(':checked')) {
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					/* 	jsonArrO += '"cmbMocrcmEmpid":"' + empid+'",';
						////////////////////////////////////////////////////alert("id:"+jsonArrO); */
					}
				}
				jsonArrO=jsonArrO.replace('&',',');
				
				//jsonArrO=jsonArrO.replace('undefined','Y');
				jsonArrO = jsonArrO.slice(0, -1) + '},';
				
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	}


function convertJsonArrTeam(){
	var allrow = jQuery("#TeamGrid").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		var empid=getFieldValue("selRfccresponse_"+rowno);
		//////////////////////////////////////////////alert(empid);
		if (jQuery('#jqg_TeamGrid_' + rowno).is(':checked')) {
			jsonArrO += '{';
			for ( var colName in row) {
				if (row[colName].substring(0, 6) != '<input') {
					jsonArrO += '"' + colName + '":"' + row[colName] + '",';
				} else {
					var x = row[colName].indexOf("id=") + 4;
					var y = row[colName].substring(x);
					var z = y.indexOf('"');
					var cellId = y.substring(0, z);
					if (jQuery("#" + cellId).attr("type") == "checkbox"){
						val = jQuery('#' + cellId).is(':checked') ? 'Y'
								:'N';					  
					} else {
						val = jQuery('#' + cellId).val();
					}
					jsonArrO += '"' + colName + '":"' + val + '",';
				/* 	jsonArrO += '"cmbMocrcmEmpid":"' + empid+'",';
					////////////////////////////////////////////////////alert("id:"+jsonArrO); */
				}
			}
			jsonArrO=jsonArrO.replace('&',',');
			
			//jsonArrO=jsonArrO.replace('undefined','Y');
			jsonArrO = jsonArrO.slice(0, -1) + '},';
			
		}
	}
	
	return '[' + jsonArrO.slice(0, -1) + ']';
}


function convertJsonArrPssrCheckList(){
	var allrow = jQuery("#PSSRGrid").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_PSSRGrid_' + rowno).is(':disabled')==true)
			{
			continue;
			}
           
            
		
		if (jQuery('#jqg_PSSRGrid_' + rowno).is(':checked')) {
			
			jsonArrO += '{';
			for ( var colName in row) {
				if (row[colName].substring(0, 6) != '<input') {
					jsonArrO += '"' + colName + '":"' + row[colName] + '",';
				} else {
					var x = row[colName].indexOf("id=") + 4;
					var y = row[colName].substring(x);
					var z = y.indexOf('"');
					var cellId = y.substring(0, z);
					if (jQuery("#" + cellId).attr("type") == "checkbox"){
						val = jQuery('#' + cellId).is(':checked') ? 'Y'
								:'N';					  
					} else {
						val = jQuery('#' + cellId).val();
					}
					jsonArrO += '"' + colName + '":"' + val + '",';
				}
			}
			jsonArrO = jsonArrO.slice(0, -1) + '},';
		}
	}
	
	return '[' + jsonArrO.slice(0, -1) + ']';
}
		
/* function InitialGrid_selectRow(id){
	

	//////////////////////////////////////////alert("Inside");
	 var detecteddateCtrl=jQuery("#InitialGrid").jqGrid('getCell', rowId,"dteAbnmDetectiondate");
	// var detecteddateCtrl="dteAbnmDetectiondate_AbnMutipleGrd_"+rowId;
	var detecteddateCtrl=jQuery("#dteMctcInaprovedDte_InitialGrid_"+id);
//////////////////////////////////////////alert(detecteddateCtrl);

	fillWithCurrentDate(detecteddateCtrl);
	
	//return "MomPillarId="+MomPillarId;
		

} */
function TeamGrid_selectRow(rowId){
/* var initiator = jQuery('#hdnResponsibility').val();

	var id=1;

	setFieldValue("cmbAbnmDetectedby_TeamGrid_"+id,initiator);
	disableField('frmMocProject',"cmbAbnmDetectedby_TeamGrid_"+id,initiator);
	jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator); */
//jQuery("#TeamGrid").jqGrid('setCell',rowId[0],'hdnMctcempid',initiator);

}

function InitialGrid_selectRow(rowId){
    var kaizenDate=jQuery("#InitialGrid").jqGrid('getCell', rowId,"dteMctcInaprovedDte"); 	
    var dateCtrl="dteMctcInaprovedDte_InitialGrid_"+rowId;	
    setTimeout(function(){
			fillWithCurrentDate(dateCtrl);
			},550);

	jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				
				isValidDateInitial(dateCtrl,rowId);	
				} 
			}); 
			
}

function FinalApprovalGrid_selectRow(rowId){
	
	 //readOnlyFields(KzbndateCtrl);
	  
    var kaizenDate=jQuery("#FinalApprovalGrid").jqGrid('getCell', rowId,"dteMctcFaaprovedDte"); 	
  
    var dateCtrl="dteMctcFaaprovedDte_FinalApprovalGrid_"+rowId;	
   
    setTimeout(function(){
			fillWithCurrentDate(dateCtrl);
			},550);
   
jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				
				isValidDateFinal(dateCtrl,rowId);	
				} 
			}); 
			
}

function HazopApprovalGrid_selectRow(rowId){
    var kaizenDate=jQuery("#HazopApprovalGrid").jqGrid('getCell', rowId,"dteMctcHzaprovedDte"); 	
    var dateCtrl="dteMctcHzaprovedDte_HazopApprovalGrid_"+rowId;	
    setTimeout(function(){
			fillWithCurrentDate(dateCtrl);
			},550);

	jQuery("#"+dateCtrl).datebox({  	   
			onSelect:function(recordid)
				{ 
				
				isValidDateHazop(dateCtrl,rowId);	
				} 
			}); 
			
}
function isValidDateInitial(dateCtrl,rowId){
	var approvalDate = getFieldValue(dateCtrl);
	var minDate=jQuery("#HazopApprovalGrid").jqGrid('getCell', rowId,"dteMctcHzaprovedDte");
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");

		if(convertStringToDate(approvalDate) < currentDate)
		{
		////////////////////////////////////////alert('Date Should be greater or Equal to Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		
	}
}
function isValidDateHazop(dateCtrl,rowId){
	var approvalDate = getFieldValue(dateCtrl);
	var minDate=jQuery("#HazopApprovalGrid").jqGrid('getCell', rowId,"dteMctcHzaprovedDte");
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");
	
		if(convertStringToDate(approvalDate) < currentDate)
		{
		////////////////////////////////////////alert('Date Should be greater or Equal to Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		
	}
}
function isValidDateFinal(dateCtrl,rowId){
	////////////////////////////////////////alert("Inside")
	var approvalDate = getFieldValue(dateCtrl);
	var minDate=jQuery("#FinalApprovalGrid").jqGrid('getCell', rowId,"dteMctcFaaprovedDte");
	var currentDate =  getServerDateTime();
	var completedDate = jQuery("#"+dateCtrl).datebox("getValue");
	////////////////////////////////////////alert("Inside1")

		if(convertStringToDate(approvalDate) < currentDate)
		{
		////////////////////////////////////////alert('Date Should be greater or Equal to Current Date');
			fillWithCurrentDate(dateCtrl);
			return false;
		}
		else{
		    clearValidationErrorMsg(dateCtrl);
	    	return false;
		}
	
}

function InitialGridbtnInitialSave_onClick(result){	
	//////////////////////////////////////////////alert("button");
	var rowid=result.rowId;
	var btnid=result.btnId;
	//////////////////////////////////////////////alert("button"+btnid)

	var keyid = jQuery("#InitialGrid").jqGrid('getCell',rowid,"hdnMctcKeyid");
	//////////////////////////////////////////////alert("keyid"+keyid);
	var EmpId = jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtEmpmKeyid");
	//////////////////////////////////////////////alert("EmpId"+EmpId);

	var MocKeyId = jQuery("#InitialGrid").jqGrid('getCell',rowid,"hdnMctcmasterid");
	
	var status = getFieldValue("selMctcInaprovedStatus_InitialGrid_"+rowid);
	//////////////////////////////////////////////alert("status"+status);
	//var date =jQuery("#InitialGrid").jqGrid('getCell',rowid,"#dteMctcInaprovedDte");
	var date = getFieldValue("dteMctcInaprovedDte_InitialGrid_"+rowid);
	//////////////////////////////////////////////alert("date"+date);
	//var remarks =jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtMctcInaprovedRem");
	var remarks = getFieldValue("txtMctcInaprovedRem_InitialGrid_"+rowid);
	//////////////////////////////////////////////alert("remarks"+remarks);
    var MOCtitle=jQuery("#txtRfcmtitle").val();
	//alert(MOCtitle);
	var SuggestionNo=jQuery("#txtRfcmsuggestionid").val();
	//alert(SuggestionNo);
	var Suggestion=jQuery("#txtKzbnKaizen").val();
	//alert(Suggestion);
	saveForm("frmMocProject","InitialApproval_save.nmoc?&keyid="+keyid+"&EmpId="+EmpId+"&status="+status+"&date="+date+"&remarks="+remarks+"&MocKeyId="+MocKeyId+"&MOCtitle="+MOCtitle+"&Suggestion="+Suggestion+"&SuggestionNo="+SuggestionNo);	
	}
	
function HazopApprovalGridbtnHazopSave_onClick(result){	
	//////////////////////////////////////////////alert("button");
	var rowid=result.rowId;
	var btnid=result.btnId;
	//alert("button"+btnid);
   // alert("button"+btnId);
	var keyid = jQuery("#HazopApprovalGrid").jqGrid('getCell',rowid,"hdnMctcKeyid");
	//////////////////////////////////////////////alert("keyid"+keyid);
	var EmpId = jQuery("#HazopApprovalGrid").jqGrid('getCell',rowid,"txtEmpmKeyid");
	//////////////////////////////////////////////alert("EmpId"+EmpId);
	var MocKeyId = jQuery("#HazopApprovalGrid").jqGrid('getCell',rowid,"hdnMctcmasterid");
	
	var status = getFieldValue("selMctcHzaprovedStatus_HazopApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("status"+status);
	//var date =jQuery("#InitialGrid").jqGrid('getCell',rowid,"#dteMctcInaprovedDte");
	var date = getFieldValue("dteMctcHzaprovedDte_HazopApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("date"+date);
	//var remarks =jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtMctcInaprovedRem");
	var remarks = getFieldValue("txtMctcHzaprovedRem_HazopApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("remarks"+remarks);
	var MOCtitle=jQuery("#txtRfcmtitle").val();
	//alert(MOCtitle);
	var SuggestionNo=jQuery("#txtRfcmsuggestionid").val();
	//alert(SuggestionNo);
	var Suggestion=jQuery("#txtKzbnKaizen").val();
	//alert(Suggestion);
	var PMMech=getFieldValue("selMctcHzAddPMMech_HazopApprovalGrid_"+rowid);
	var PMElect=getFieldValue("selMctcHzAddPMElect_HazopApprovalGrid_"+rowid);
    var PMInst=getFieldValue("selMctcHzAddPMInst_HazopApprovalGrid_"+rowid);
	saveForm("frmMocProject","HazopApproval_save.nmoc?&keyid="+keyid+"&EmpId="+EmpId+"&status="+status+"&date="+date+"&remarks="+remarks+"&MocKeyId="+MocKeyId+"&MOCtitle="+MOCtitle+"&SuggestionNo="+SuggestionNo+"&Suggestion="+Suggestion+"&PMMech="+PMMech+"&PMElect="+PMElect+"&PMInst="+PMInst);	
	   	
	}

function FinalApprovalGridbtnFinalSave_onClick(result){
	
	//////////////////////////////////////////////alert("button");
	var rowid=result.rowId;
	var btnid=result.btnId;
	//////////////////////////////////////////////alert("button"+btnid)
	var keyid = jQuery("#FinalApprovalGrid").jqGrid('getCell',rowid,"hdnMctcKeyid");
	//////////////////////////////////////////////alert("keyid"+keyid);
	var EmpId = jQuery("#FinalApprovalGrid").jqGrid('getCell',rowid,"txtEmpmKeyid");
	//////////////////////////////////////////////alert("EmpId"+EmpId);
	var MocKeyId = jQuery("#FinalApprovalGrid").jqGrid('getCell',rowid,"hdnMctcmasterid");
	
	var status = getFieldValue("selMctcFaaprovedStatus_FinalApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("status"+status);
	//var date =jQuery("#InitialGrid").jqGrid('getCell',rowid,"#dteMctcInaprovedDte");
	var date = getFieldValue("dteMctcFaaprovedDte_FinalApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("date"+date);
	//var remarks =jQuery("#InitialGrid").jqGrid('getCell',rowid,"txtMctcInaprovedRem");
	var remarks = getFieldValue("txtMctcFaaprovedRem_FinalApprovalGrid_"+rowid);
	//////////////////////////////////////////////alert("remarks"+remarks);
    var MOCtitle=jQuery("#txtRfcmtitle").val();
	//alert(MOCtitle);
	var SuggestionNo=jQuery("#txtRfcmsuggestionid").val();
	//alert(SuggestionNo);
	var Suggestion=jQuery("#txtKzbnKaizen").val();
	//alert(Suggestion);
	saveForm("frmMocProject","FinalApproval_save.nmoc?&keyid="+keyid+"&EmpId="+EmpId+"&status="+status+"&date="+date+"&remarks="+remarks+"&MocKeyId="+MocKeyId+"&MOCtitle="+MOCtitle+"&SuggestionNo="+SuggestionNo+"&Suggestion="+Suggestion);		
		
	}



	
jQuery("#btnDeletehatif").click(function()
	   	{ 
		   removeWhatifRecord();
		});
		
function removeWhatifRecord(keyid) {
	var Whatifrow = jQuery("#WhatifGrid").jqGrid('getDataIDs');//	row get data
	for (i = 0; i < Whatifrow.length; i++) {
    var rowid = Whatifrow[i];
    if (jQuery('#jqg_WhatifGrid_'+Whatifrow[i]).is(':checked') == true) {//////////////////////alert(1);
	    
		keyid = jQuery("#WhatifGrid").jqGrid('getCell', rowid, "hdnWifdKeyid");

         if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
			    var r = confirm("Do You Want To Delete?");
				if (r == true) {
					processAjaxCalls("Whatif_remove.nmoc", "keyid="+ keyid, 'removeWhatif_successCallBack','remove_errorCallBack');
				} else{
					return false;
				} 
           	}else {
                var r = confirm("Do You Want To Remove Row?");
				if (r == true)
					jQuery("#WhatifGrid").trigger("reloadGrid");
				else
					return false;
			}
         }
       }	}
       
function removeWhatif_successCallBack(result) {
	////////////////////alert(result.successData);
	jQuery("#WhatifGrid").trigger("reloadGrid");
}


jQuery("#btnDeleteHazop").click(function()
	   	{ 
		   removeHazopRecord();
		});
		
function removeHazopRecord(keyid) {
	var Whatifrow = jQuery("#Hazopgrid").jqGrid('getDataIDs');//	row get data
	for (i = 0; i < Whatifrow.length; i++) {
    var rowid = Whatifrow[i];
    if (jQuery('#jqg_Hazopgrid_'+Whatifrow[i]).is(':checked') == true) {//////////////////////alert(1);
	    
		keyid = jQuery("#Hazopgrid").jqGrid('getCell', rowid, "hdnMohdKeyid");

         if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
			    var r = confirm("Do You Want To Delete?");
				if (r == true) {
					processAjaxCalls("Hazop_remove.nmoc", "keyid="+ keyid, 'removeHazop_successCallBack','remove_errorCallBack');
				} else{
					return false;
				} 
           	}else {
                var r = confirm("Do You Want To Remove Row?");
				if (r == true)
					jQuery("#Hazopgrid").trigger("reloadGrid");
				else
					return false;
			}
         }
       }	}
       
function removeHazop_successCallBack(result) {
	////////////////////alert(result.successData);
	jQuery("#Hazopgrid").trigger("reloadGrid");
}
	
	
jQuery("#btnopenpfd").click(function(){
	
	//window.open("Usermanuals/App3.pdf");
    window.open().document.write('<embed src="Usermanuals/MOC Procedure.pdf" width="1300" height="700" alt="pdf" pluginspage="http://www.adobe.com/products/acrobat/readstep2.html"');
});



</script>
<form id="frmMocProject">
	<div id="wrapper" style="width:80%;">
		<table>
			<tr>
				<td colspan="3" >
					  <div  id="frmMocProjectFuntKeyIds">
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu} "  ></input>	
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>		
					<input type="hidden" id="section" name="cmbrfcmdmtid" value="${requestScope.mocRfcmst.rfcmdmtid}"></input>
					<input type="hidden" id="cell" name="cmbRfcmjhid" value="${requestScope.mocRfcmst.rfcmjhid}"  ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.mchId} "  ></input>
					<input type="hidden" id="flid" name="cmbRfcmflid" value="${requestScope.Kaizenbankmst.kzbnFlid}"></input>	
				</div>
			<!-- 	<Vignesh Adding for functional Location > -->
				
			 	<div id="NewMocfunLocation" style="width:123%;margin-top:-5px;margin-left:-35px">
	
<!-- 	<Vignesh Adding for functional Location > -->
			</td>
			 <td>
				  <div style="margin-left:30px; margin-top:-0px;"><label><b>Suggestion No</b></label></div>
				  <div style="margin-left:30px; margin-top:0%;">
				<%--   <input id="txtRfcmsuggestionid" name="txtRfcmsuggestionid" type="text" class="easyui-text" disabled="disabled" style="width:120px; height:25px;text-align:center; font-weight:bold" value="${requestScope.mocRfcmst.rfcmsuggestionid}"/> 
				 --%> 
				 <input type="text" class="easyui-text"  id="txtRfcmsuggestionid" name="txtRfcmsuggestionid" disabled="disabled" maxlength="10"  style=" width : 120px;height:25px; text-align:left;font-weight:bold" value="${requestScope.Kaizenbankmst.kzbnKeyid}"/>
					  
				  </div>
				  </td>
				  <td>
				  <div style="padding-left:5px;" ><label class="mandatory-lbl">Date</label></div>
				   <div style="width:350px;position:relative;padding-left:0px"> 
						      <input type="text" class="easyui-datebox"  id="dteRfcmdate" name="dteRfcmdate" maxlength="10"   style=" width : 100px;/* height:60px; */ text-align:left;" value="${requestScope.mocDate}"/>
						     	  </div>
				  </td>
				  
				  <td>
				   <div style="width:350px;position:relative;margin-left:-240px;margin-top:8px;"> 
				   <input type="button" class="easyui-button" value ="MOC Procedure" id="btnopenpfd" style="height:23px;"/>	
				  
				  	</div>
				  </td>
				  
			</tr>
			</table>
		<table>
		
	<tr>
	<td>
			
   <div  style="margin-left:-40px;margin-top:-0px;">
	<label class="mandatory-lbl" >Suggestion</label>		
    </div>		
	<div style="margin-left:-40px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtKzbnKaizen" name="txtKzbnKaizen" disabled="disabled" class="limit-length" style="width:818px;text-transform: uppercase; height :40px;font-weight:bold;" 
	 value="">${requestScope.Kaizenbankmst.kzbnKaizen}</textarea>	
	</div>
	</td>
	<td>
	 <div style=" margin-left :30px;margin-top:0px;"><label>MOC No </label>
					   	<input type="text" class="easyui-text"  id="txtRfcmKeyid" name="txtRfcmKeyid" disabled="disabled" maxlength="10"  style=" width : 120px;height:25px; text-align:left;font-weight:bold" value="${requestScope.mocRfcmst.rfcmKeyid}"/>
					     
					   </div>
	</td>
	<td>
	  <div style="margin-left :0px;margin-top:-0px;"><label>Emergency No</label>
					   	<input type="text" class="easyui-text"  id="txtSusmDocno" name="txtSusmDocno" disabled="disabled"  maxlength="10"  style=" width : 100px;height:25px; text-align:left;" value="${requestScope.mocRfcmst.rfcmemergencyno}"/>
					     
					   </div> 
 <td>
				   <div style="width:350px;position:relative;margin-left:15px;margin-top:2px;"> 
				   <input type="button" class="easyui-button" value ="Excel View" id="btnoExcelView" style="height:25px;width:100px;"/>	
				  
				  	</div>
				  </td>
	</td>
	
 <td>
				   <div style="width:350px;position:relative;margin-left:15px;margin-top:2px;"> 
				   <input type="button" class="easyui-button" value ="Excel View" id="btnoExcelView" style="height:25px;width:100px;"/>	
				  
				  	</div>
				  </td>
    </tr>	 
	</table>
	</div>	
	
<div id="tabMocProject" class="easyui-tabs" style="height:500px;width:1500px;margin-top:5px;margin-left:5px; float:left;">
<div title="MOC Workflow">
<table>
 
  </table>
   
  
 
 
   <div style="margin-left:12px; margin-left:65px\9;margin-top:20px;">
	<table id="TeamGrid">
	<tr><td></td></tr>
	</table>
     <div id="Teampagerid"></div>
   </div>
   
  
</div>


<div title="RequestforChange">
 <table style="margin-left:8px;margin-left:2px\9;"> 
 <tr>
			<td valign="top" style=" width : 796px;">
				<div class="easyui-paddingbfpx">
						  			 
						  <span style="padding-left:0px;"><label class="mandatory-lbl">Name of Initiator</label></span>
						 <span style="padding-left:160px;"><label class="mandatory-lbl">Title</label></span>	
						 
						  <!-- <span style="padding-left:170px;"><label class="mandatory-lbl">JH Area</label></span>	
						  <span style="padding-left:210px;" ><label class="mandatory-lbl">Date</label></span>	 --> 			      
					</div>
					<div class="easyui-paddingbfpx" >	
						
					     
					      
					       <span style="position:relative;padding-left: 0px">
						       <input type="text" class="easyui-combobox"  id="cmbRfcmempid" name="cmbRfcmempid" maxlength="10"   style=" width : 240px;height:25px; text-align:left;" value="${requestScope.Kaizenbankmst.kzbnResponsibility}"/>
						       </span>
						     
					       </div>
					      
					       </td>
					   
		</tr>
					       
					       <tr>
					       <td>
					       	<div class="easyui-paddingbfpx">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">Department</label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbrfcmdmtid" name="cmbrfcmdmtid"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmdmtid}" />
				
						    </span>
					       </div>
					       </td>
					         <td>
					       	<div class="easyui-paddingbfpx"style="margin-left:-530px;">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">Type of Change</label></span>	</div>
					       
					    <%--    <div class="easyui-paddingbfpx"style="margin-left:-530px;" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbRfcmtype" name="cmbRfcmtype"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmtype}" />
				
						    </span>
					       </div> --%>
					       	<div class="easyui-paddingbfpx" style="margin-left:-530px;"> 
							<select id="cboRfcmtype" name="cboRfcmtype" style="width:240px;"  >
									<option value="FACILITY/PROCESS"> FACILITY/PROCESS</option>
									<option value="ORGANIZATIONAL"> ORGANIZATIONAL</option>
									<option value="PROCEDURAL/SYSTEM"> PROCEDURAL/SYSTEM</option>
									
							</select>
				        </div>
					       </td>
					       
					       </tr>
					       
					       <tr>
					       <td>
					       	<div class="easyui-paddingbfpx">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">JH Area</label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbRfcmjhid" name="cmbRfcmjhid"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmjhid}" />
				
						    </span>
					       </div>
					       </td>
					         <td>
					       	<div class="easyui-paddingbfpx"style="margin-left:-530px;">
					       <span style="padding-left:0px;"><label class="mandatory-lbl">Nature of Change</label></span>	</div>
					       
					   <%--     <div class="easyui-paddingbfpx"style="margin-left:-530px;" >
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbRfcmnature" name="cmbRfcmnature"  style="width:240px;"  value="${requestScope.mocRfcmst.rfcmnature}" />
				
						    </span>
					       </div> --%>
					       	<div class="easyui-paddingbfpx" style="margin-left:-530px;"> 
							<select id="cboRfcmnature" name="cboRfcmnature" style="width:240px;"  >
									<option value="Temporary"> Temporary</option>
									<option value="Permanent">Permanent</option>
								
									
							</select>
				        </div>
					       </td>
					       </tr>
					       <tr>
					       		<td>
		 <div class="easyui-paddingbfpx" style="margin-top:-5px;"><label class="mandatory-lbl">Detail</label></div>
					  <div>	
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="200"  style="width: 510px; height : 40px;text-transform: uppercase;" id="txtRfcmdetail" name="txtRfcmdetail" >${requestScope.mocRfcmst.rfcmdetail}</textarea>
					</div>
		</td>
					       </tr><tr>
					       	<td>
		 <div class="easyui-paddingbfpx"style="margin-left:-0px;"><label class="mandatory-lbl">Proposed Change Description & Justification</label>
					  <div>	
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="2000"  style="width: 510px; height :50px;text-transform: uppercase;" id="txtRfcmdescription" name="txtRfcmdescription" 	>${requestScope.mocRfcmst.rfcmdescription}</textarea>
					</div>
					</div>
		</td><td>
 		  <td>
			<div style="position: relative;margin-left:-285px;margin-top:-5px;"><span id="MocfileMgr" style="position: absolute;padding-right:30px;margin-top:0px;">
		</span></div>  
	    </td>
					       </tr>
 	</table>
 	  <div style="position:relative;margin-left:278px;margin-top:-268px;">
						     <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="200"  style="width: 850px; height : 30px;text-transform: uppercase;" id="txtRfcmtitle" name="txtRfcmtitle" >${requestScope.mocRfcmst.rfcmtitle}</textarea>
					 </div>
					 
	<div style="margin-left:530px;margin-top:25px;">
	<table id="BasisGrd" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="BasisGrdpager"></div>
	 </div> 				 
					 

</div>

<div title="Questionnaire">
  

<div style="margin-left:14px; margin-top:10px;margin-left:65px\9;">
 <table> <tr> 	             
  <td style="margin-left:35px;padding-top:-40px;">
				 <div style="margin-left:35px; margin-top:11px;">			  
				 <input type="checkbox"  id="chkSelectAllQuestions" name="chkSelectAllQuestions"  value=""/>
				 <label id=""><b>Select All</b></label>	
				 </div>
				</td>
			</tr> </table>
<div class="QuestionaireGrddiv">
				<table id="QuestionaireGrd" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div>
     
   </div> 
   <div title="InitialApproval">
    <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
<div class="InitialGrid">
				<table id="InitialGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div> 
   </div>
  <div title="WhatIf">
   <table>
   <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Facility/Operation/Process</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtWifmFacility" name="txtWifmFacility" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.whatifMst.wifmFacility}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:30px;">
   <div><label class="mandatory-lbl">Date</label></div>
								<div>
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 120px;" id="dteWifmDate" name="dteWifmDate" 
								 													value="${requestScope.whatifMst.wifmDate}"/>
							      	</span>
								</div></div>
   </td>
   </tr>
    <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Team</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtWifmTeam" name="txtWifmTeam" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.whatifMst.wifmTeam}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:30px;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Add Row" id="btnAddwhatif" style="height:23px;"/>	
							</div></td>
	 <td>
   <div style="margin-left:-40px;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Delete" id="btnDeletehatif" style="height:23px;"/>	
	</div></td>						
							
   </tr>
  </table> 
  <table><tr>
   <td colspan="3">
					<div>
						<table id="WhatifGrid" ></table> 
						<div id="Whatifpager"></div>
					</div>
				</td></tr>
   </table>
   </div>
   <div title="Hazop">
   <table>
   <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Facility/Operation/Process</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomFacility" name="txtHzomFacility" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;"  value="">${requestScope.hazopMst.hzomFacility}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:30px;">
   <div><label class="mandatory-lbl">Date</label></div>
								<div>
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 120px;" id="dteHzomDate" name="dteHzomDate" 
								 													value="${requestScope.hazopMst.hzomDate}"/>
							      	</span>
								</div></div>
   </td>
   <td>
    <div style=" margin-left :-165px;margin-top:0px;">
    <div>
    <label>P&ID No </label></div>
    <div>
					   	<input type="text" class="easyui-text"  id="txtHzomPidno" name="txtHzomPidno" maxlength="10"  style=" width : 160px;height:22px; text-align:left;" value="${requestScope.hazopMst.hzomPidno}"/>
					    </div> 
					   </div>
   </td>
   </tr>
    <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Team</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomTeam" name="txtHzomTeam" class="limit-length" style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomTeam}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:30px;;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Add Row" id="btnAddHazop" style="height:23px;"/>	
	</div></td>
	
	<td>
   <div style="margin-left:-125px;padding-right:0px;margin-top:5px">
	<input type="button" class="easyui-button" value ="Delete" id="btnDeleteHazop" style="height:23px;"/>	
	</div></td>
	
   </tr>
   <tr>
      <td>
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Node</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomNode" name="txtHzomNode" class="limit-length" style="width:550px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomNode}</textarea>	
	</div>
   </td>
   <td>
   <div style="margin-left:-200px;">
    <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >Design Intent</label>		
    </div>		
	<div style="margin-left:-0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000" rows="2" cols="80"  id="txtHzomDesignintent" name="txtHzomDesignintent" class="limit-length" style="width:515px;text-transform: uppercase; height :25px;" value="" >${requestScope.hazopMst.hzomDesignintent}</textarea>	
	</div></div>
   </td>
   </tr>
  </table> 
  <table><tr>
   <td>
					<div>
						<table id="Hazopgrid" ></table> 
						<div id="Hazoppager"></div>
					</div>
				</td></tr>
   </table> 
   

   
   
   </div>
    <div title="W/H Reccommend">
    <p style="color:red;margin-left:300px;margin-top:10px;"><b>Save can be done in Grid Itself.Top save can be avoided</b></p>
 <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
<div class="HazopReccommendGrid">
				<table id="HazopReccommendGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div> 
 
 
 
 </div>
 <div title="W/H Approval">
 <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
<div class="HazopApprovalGrid">
				<table id="HazopApprovalGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div> 
 
 
 
 </div>
  <div title="PSSRchecklists">
  <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
  <table>
  <tr>
   <td>
    <div  style="margin-left:-0px;margin-top:-20px;">
	<label class="mandatory-lbl" >Facility/Operation/Process</label>		
    </div>		
	<div style="margin-left:0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000"  id="txtPsrmprocess" name="txtPsrmprocess"  style="width:832px;text-transform: uppercase; height :25px;" value="" >${requestScope.mocPssrmst.psrmprocess}</textarea>	
	</div>
   </td>
  <td>
   	  <div style="margin-left:-275px;margin-top:-20px;" ><label class="mandatory-lbl">Date</label></div>
				   <div style="width:350px;position:relative;margin-left:-270px;margin-top:-0px;"> 
						      <input type="text" class="easyui-text"  id="dtePsrmdate" name="dtePsrmdate" maxlength="10"   style=" width : 100px;height:45px; text-align:left;" value="${requestScope.mocPssrmst.psrmdate}"/>
						     	  </div></td></tr>
						     	  <tr>
						     	  <td>
		  <div  style="margin-left:-0px;margin-top:-0px;">
	<label class="mandatory-lbl" >MOC Details</label>		
    </div>					     	   		
	<div style="margin-left:0px;">
	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="3000"  id="txtPsrmmocdetail" name="txtPsrmmocdetail" style="width:1125px;text-transform: uppercase; height :40px;" value="" >${requestScope.mocPssrmst.psrmmocdetail}</textarea>	
	</div>
						     	  </td>
						     	  </tr>
   </table>
  
<div class="pssrGrddiv">
				<table id="PSSRGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div>
  </div>
   <div title="PSSRRecomnd">
      <div  style="margin-top: 0px;margin-left:1000px;">
    
	       <input type="button" class="easyui-button" value ="Add" id="btnAddNewReccommend" style="height:23px;margin-top:20px;"/>
	       <span style="padding-left:8px;">
	          <input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;margin-top:20px;"/>
	       </span>
	      
     	</div>
     	<div class="QuestionaireGrddiv">
   <table id="PSSRReccomendGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div></div>
   
   </div>
   <div title="MOCClosure">
    <div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
<div class="QuestionaireGrddiv">
				<table id="MOCCGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				</div>
   
   </div> 
<div title="FinalApprovals" >

<div class="FinalApprovalGrid">
				<table id="FinalApprovalGrid" style="">
					<tr>
						<td></td>
					</tr>
				</table>
				<div id="pager"></div>
				</div>
				  <div style="margin-left:20px;margin-top:20px;">
 		<label style="color:green;"><b>MOC Completed?</b></label>
	<span><input id="chkMocClosed" name="chkMocClosed" type="checkbox" /></span>
	</div>
	
				</div> 
				
	
	
	<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value="${requestScope.dcmTlDocumentmanager.dmdmFilename}"/>
	<input type="hidden" id="hdnDmdmKeyid" name="hdnDmdmKeyid"  />
	<input type="hidden" id="hdncreatedon" name="hdncreatedon"   />
<input type="hidden" id="hdnkeyid"  />
<input type="hidden" id="hdnfilename"  />
<input type="hidden" id="txtIsProjectteamLink" name="txtIsProjectteamLink" value=" "/>
<input type="hidden" id="hdnbuttonId" value="${requestScope.buttonId}"  />
<input type="hidden" id="hdnformName" value="${requestScope.formName}"  />
<input type="hidden" id="hdnLoginUserid" name="hdnLoginUserid" value="${requestScope.loginUserid}"/>
<input type="hidden" id="hdnUserid" name="hdnUserid" value="${requestScope.userid}"  />
<input type="hidden" id="hdnFmgMode" value="${requestScope.fmgMode}"  />
</div> 
</div>
</div>
 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
 <input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}">
 <input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.keyId}">
 <input type="hidden" id="hdntitle" name="hdntitle" value="${requestScope.Title}"/>
 <input type="hidden" id="hdncurrDate" name="hdncurrDate" value="${requestScope.currDate}">
  <input type="hidden" id="hdnSuggestionId" name="hdnSuggestionId" value="${requestScope.SuggestionId}">
   <input type="hidden" id="hdnSuggestion" name="hdnSuggestion" value="${requestScope.Suggestion}">
    <input type="hidden" id="hdnSuggestionFlid" name="hdnSuggestionFlid" value="${requestScope.Sugflid}">
 	  <input type="hidden" id="hdnMocKeyId" name="hdnMocKeyId" value="${requestScope.mocRfcmst.rfcmKeyid}">
 	<input type="hidden" id="hdnResponsibility" name="hdnResponsibility" value="${requestScope.Responsibility}">
<input type="hidden" id="hdnInitiator" name="hdnInitiator" value="${requestScope.Kaizenbankmst.kzbnResponsibility}">
 	 <input type="hidden" id="hdnactnmode" name="hdnactnmode" value=""/>
 	 <input type="hidden" id="hdnuserid" name="hdnuserid" value="${requestScope.userid}">
 	   <input type="hidden" id="hdnrowid" name="hdnrowid" value=""/>
 	   <input type="hidden" id="hdncount" name="hdncount" value=""/>
 	     <input type="hidden" id="hdnMocStatus" name="hdnMocStatus" value=""/>
 	      <input type="hidden" id="hdnHazopcount" name="hdnHazopcount" value=""/>
 	        <input type="hidden" id="hdnInitialApproval" name="hdnInitialApproval" value=""/>
 	       <input type="hidden" id="hdnHazopApproval" name="hdnHazopApproval" value=""/>
 	     <input type="hidden" id="hdnStatusClose" name="hdnStatusClose" value="${requestScope.Status}"/>
 	       <input type="hidden" id="hdnFinalMaxApprovalStatus" name="hdnFinalMaxApprovalStatus" value=""/>
 	        <input type="hidden" id="hdnFinalcount" name="hdnFinalcount" value=""/>
 	       <input type="hidden" id="hdnWhatifKey" name="hdnWhatifKey" value="${requestScope.WhatifKey}">
 	       <input type="hidden" id="hdnHazopKey" name="hdnHazopKey" value="${requestScope.HazopKey}">
 	     <input type="hidden" id="hdnpssrCount" name="hdnpssrCount" value="${requestScope.pssrCount}"/>
 <input type="hidden" id="hdnWHCount" name="hdnWHCount" value="${requestScope.WHCount}"/>
<input type="hidden" id="hdnnature" name="hdnnature" value="${requestScope.nature}"/>
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>  
<input type="hidden" id="hdnClosurecount" name="hdnClosurecount" value=""> 
<input type="hidden" id="hdnJHleader" name="hdnJHleader" value="${requestScope.JHleader}"/> 
<input type="hidden" id="hdnDMTLeader" name="hdnDMTLeader" value="${requestScope.DMTLeader}"/>
<input type="hidden" id="hdnProcessArea" name="hdnProcessArea" value="${requestScope.ProcessArea}"/>
<input type="hidden" id="hdnMechArea" name="hdnMechArea" value="${requestScope.MechArea}"/>
<input type="hidden" id="hdnInstrumentArea" name="hdnInstrumentArea" value="${requestScope.InstrumentArea}"/>
<input type="hidden" id="hdnCivilArea" name="hdnCivilArea" value="${requestScope.CivilArea}"/>
<input type="hidden" id="hdnElectricalArea" name="hdnElectricalArea" value="${requestScope.ElectricalArea}"/>
<input type="hidden" id="hdnPBUHead" name="hdnPBUHead" value="${requestScope.PBUHead}"/>
<input type="hidden" id="hdnMOCDate" name="hdnMOCDate" value="${requestScope.mocDate}"/>

<input type="hidden" id="flid" name="flid" value="${requestScope.Sugflid}" />


</form>