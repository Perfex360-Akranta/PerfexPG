<script>
function fillTillTime(){ 
	var fromTime= getFieldValue("spnsessionFromTime");
//	alert(fromTime);
	var progDuration = jQuery('#txtEtcmMaxDuration').val();
//	 alert(progDuration);
	 var hours=progDuration*60;
//	 alert("hours"+hours);
	if(progDuration.trim().length>0){
		var tillTime = addMinutes(fromTime+":00",hours);
	//	alert("tillTime"+tillTime);
		//jQuery('#spnsessionTillTime').val(tillTime);
		
		jQuery('#spnsessionTillTime').spinner('setValue',tillTime);
		
	//	alert(jQuery('#spnsessionTillTime').val());
	}
}
jQuery(document).ready(function(){
	 initialiseForm('frmNewTraCal');
	 var flag="false";
	 jQuery('#submitForm').val('frmNewTraCal');
	 formatDateBox('dteEtcmCalendarDate','DD-MMM-YYYY');
	 formatDateBox("dteEtcmCompletedDate","DD-MMM-YYYY");
	 formatDateBox('dteSessiondate','dd-MMM-yyyy');
	 fillWithCurrentDate('dteSessiondate');
     var flid = jQuery("#frmNewTraCal input[id='flid']").val();
     // removing ? 
	 fillComboBox("frmNewTraCal","cmbEtcmCompletedBy","employee.commonFilter");
	 //fillComboBox("frmNewTraCal","cmbEtcmFunction","trade.commonFilter");
	 fillComboBox("frmNewTraCal","cmbEtcmFunction","TrainingIdentified.ntrc","",false); 
	 fillComboBox("frmNewTraCal","cmbEtcmTrainingfunction","ettrade.ntrc");
	 
	 fillComboBox("frmNewTraCal","cmbEtcfFacultyId","facultyCombo.tcl");

	 
     fillComboBox("frmNewTraCal","cmbEtcmTopicid","topic_fillcombo.ntrc");
	 fillComboBox("frmNewTraCal","cmbEtcuRoleKeyid","roleMst.commonFilter");
	 fillComboBox("frmNewTraCal","cmbEtcuRoleDmt","sectionCombo.commonFilter");
	 fillComboBox("frmNewTraCal","cmbEtcuRoleJh","cellCombo.commonFilter");
	 fillComboBox("frmbtnSessionsaveNewTraCal","cmbEtcmRating","rating_combo.ntrc","",false); 
	 fillComboBox("frmNewTraCal","cmbEtcmAnchoredby","AnchoredBy_combo.ntrc","",false);
	 fillComboBox("frmNewTraCal","cmbTopiTrainingmodeNew","deliveryMode_Combo.topmst","",false);
	 fillComboBox("frmNewTraCal","cmbTopiRelatedto","TrainingType_combo.ntrc","",false);
	 
	 
	// fillComboBox("frmNewTraCal","cmbEtcmTopiccategory","combo_Topic.commonFilter","",false); 
	 
	 fillComboBox("frmNewTraCal","cmbEtcmTopiccategory","combo_Category.topi","",false);  // -- no url
	 fillComboBox("frmNewTraCal","cmbEtcmVenue","entVenueCombo.entbatch?&flId="+flid);  // -- no url
	 reloadCombo("frmNewTraCal","cmbEtcmVenue","entVenueCombo.entbatch?&flId="+flid);  // -- no url
	 
	 // jQuery("#btnEmployUnqDelete").hide();
	 var url = jQuery('#hiddenUrl').val();
	 disableField("frmNewTraCal","dteEtcmCompletedDate");
	 disableField("frmNewTraCal","cmbEtcmCompletedBy");
	 //---- Vignesh 17Mar2026
	 var _empAttenSelectAll = false;
	 var _empAttenInitializedRows = {};
	 //---- Vignesh 17Mar2026
	 
	 var locnId = jQuery("#frmNewTraCal input[id='location']").val();
	 var factId = jQuery("#frmNewTraCal input[id='factory']").val();
	 var sectionId = jQuery("#frmNewTraCal input[id='section']").val();
	 var cellId = jQuery("#frmNewTraCal input[id='cell']").val();
	 var machId = jQuery("#frmNewTraCal input[id='machine']").val();

	 var dataStr = "&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;	  
	 var userrole=jQuery("#hdnUserid").val();
	if(userrole=="ET PILLAR CHAMPION" || userrole=="ET PILLAR MEMBER" || userrole=="TPM CELL" || userrole=="DMT MEMBER")
		{
		jQuery("#btnTopicsave").attr("disabled",false).removeClass("ui-state-disabled");
		jQuery("#btnFacultyAdd").attr("disabled",false).removeClass("ui-state-disabled"); 
		}
	else{
		jQuery("#btnTopicsave").attr("disabled",true).addClass("ui-state-disabled");
		 jQuery("#btnFacultyAdd").attr("disabled",true).addClass("ui-state-disabled");
	}

	 var keyid=jQuery("#txtEtcmKeyid").val();
	   spinnerKeyPress('spnsessionFromTime');
	     spinnerKeyPress('spnsessionTillTime');	
	 if(keyid!=null&&keyid.length!=0)
		 {	
		
		   disableField("frmNewTraCal","dteEtcmCalendarDate");
		   var general=jQuery("#chkEtcmGeneral").val();
		   var calendardate=jQuery("#dteEtcmCalendarDate").datebox('getValue');
		   jQuery("#dteEtcmCalendarDate").datebox('setValue',calendardate.substring(0,11));
		   var uniqe=jQuery("#chkEtcmUniqueposition").val();
		   var MSD=jQuery("#chkEtcmMSD").val();
		    var Mark=jQuery("#hdnmark").val();
 		    var assess=jQuery("#hdnassessment").val();
		    var materail=jQuery("#hdnmaterial").val();
		    var empcnt=jQuery("#hdnEmpcnt").val();
		    var empattnd=jQuery("#hdnEmpattnd").val();
		    var iscompleted=jQuery("#chkEtcmChkCompleted").val();
		    var completeddate=jQuery("#dteEtcmCompletedDate").datebox('getValue');
		    var completedby=jQuery("#cmbEtcmCompletedBy").combobox('getValue');
			 
			 if(empattnd > 0)
				 {
				 jQuery('#chkEtcmMarksBasedY').attr('disabled',true);
		 		 jQuery('#chkEtcmMarksBasedN').attr('disabled',true);
		 		jQuery('#chkEtcmAssessmentReqY').attr('disabled', true); 
		 		jQuery('#chkEtcmAssessmentReqN').attr('disabled', true); 
				 }
		    if(general=="Y")
			   {
			   jQuery('#chkEtcmGeneral').attr('checked', true);
			   }
		   if(uniqe=="Y")
		   {
		   jQuery('#chkEtcmUniqueposition').attr('checked', true);
		   }
		   
		   if(MSD=="Y")
		   {
		   jQuery('#chkEtcmMSD').attr('checked', true);
		   }
		   
		   if(Mark=="Y")
		   {
			   jQuery('#chkEtcmMarksBasedY').attr('checked',true);
			   jQuery('#chkEtcmMarksBasedY').val("Y");
		   }
		   else{
			   jQuery('#chkEtcmMarksBasedN').attr('checked',true);
			   jQuery('#chkEtcmMarksBasedN').val("N");
		   }
		   
		   if(assess=="Y")
		   {
		   jQuery('#chkEtcmAssessmentReqY').attr('checked', true);
		   jQuery('#chkEtcmAssessmentReqY').val("Y");
		   }
		   else{
			  jQuery('#chkEtcmAssessmentReqN').attr('checked', true); 
			  jQuery('#chkEtcmAssessmentReqN').val("N");
			  jQuery("#chkEtcmMarksBasedY").attr('disabled',true);
			  jQuery("#chkEtcmMarksBasedN").attr('disabled',true);
		   }
		  
		   if(materail=="Y")
		   {
		   jQuery('#chkEtcmMaterialsReadyY').attr('checked', true);
		   jQuery('#chkEtcmMaterialsReadyY').val("Y");
		   }
		   else{
			   jQuery('#chkEtcmMaterialsReadyN').attr('checked', true); 
			   jQuery('#chkEtcmMaterialsReadyN').val("N");
		   }
		  if(iscompleted=="Y")
			  {
			  jQuery("#chkEtcmChkCompleted").attr('disabled',true);
			  jQuery("#chkEtcmChkCompleted").attr('checked',true);
			  jQuery("#dteEtcmCompletedDate").datebox('setValue',completeddate.substring(0,11));
		
			  if(completedby.length > 1)
				  {
				  jQuery("#cmbEtcmCompletedBy").combobox('setValue',completedby);
				  }
			  }
		  else{
			  clearField("dteEtcmCompletedDate");
			  clearField("cmbEtcmCompletedBy");
			  jQuery("#chkEtcmChkCompleted").val("N");
		  }
	   
		// ---- MANO FIX: disable DMT/JH on page load based on checkbox state ----
          var generalChecked = jQuery("#chkEtcmGeneral").is(':checked');
          var uniqueChecked = jQuery("#chkEtcmUniqueposition").is(':checked');
          if(generalChecked && !uniqueChecked){
              disableField("frmNewTraCal", "cmbEtcuRoleDmt");
              disableField("frmNewTraCal", "cmbEtcuRoleJh");
              disableField("frmNewTraCal", "cmbEtcuRoleKeyid");
              toggleDmtJhMandatory(false);
          } else if(uniqueChecked && !generalChecked){
              enableFields("cmbEtcuRoleDmt");
              enableFields("cmbEtcuRoleJh");
              enableFields("cmbEtcuRoleKeyid");
              toggleDmtJhMandatory(true);
          }
          // ---- END MANO FIX ----
          
		   
		 }
	 else{
		 fillWithCurrentDate('dteEtcmCalendarDate');	
		 jQuery('#chkEtcmMaterialsReadyN').attr('checked',true);
		 jQuery('#chkEtcmAssessmentReqN').attr('checked',true);
		 jQuery('#chkEtcmMarksBasedN').attr('checked',true);
		 jQuery('#chkEtcmGeneral').attr('checked', true);
		 jQuery('#chkEtcmGeneral').val("Y");
		 jQuery('#chkEtcmChkCompleted').attr('disabled', true);
		 jQuery("#chkEtcmMarksBasedY").attr('disabled',true);
		 jQuery("#chkEtcmMarksBasedN").attr('disabled',true);
	 }
	var funclocn="";
	 funclocn= "functionalLoc.ntrc";
	 if(flid.length<=0)
		 {
		 loadFunctionalLocation("NewTraingCalFunctionalLoc",funclocn,"NewTraingCalFunctionalLoc","frmNewTraCal","&flid=");
		 }
	 else{
		 loadFunctionalLocation("NewTraingCalFunctionalLoc",funclocn,"NewTraingCalFunctionalLoc","frmNewTraCal","&flid="+flid);

	 }
	 numericTextBox('txtEtcmMaxDuration');
	 numericTextBox('txtEtcaCutOff');
	 numericTextBox('txtEtcaMaxMarks');
	 numericTextBox('txtEtcmPermittedStrength');
	 jQuery('#frmNewTraCal .easyui-text').css('text-transform','uppercase');
	 jQuery('#frmNewTraCal textarea').css('text-transform','uppercase');
	 fileManagerPopUp("","TRAINING","frmNewTraCal","btnFilManage","newtrainingcalFilemgr");
	
	
	 
	 fillWithCurrentDate("sessionFromTime");
	

	 fillWithCurrentDate("sessionTillTime");
	 
//	 fillWithCurrentDate('spnsessionFromTime');
		setTimeout(function() {
			 fillWithCurrentDate('spnsessionFromTime');
		},10);
	// fillWithCurrentDate('spnsessionTillTime');
	 setTimeout(function() {
		 fillWithCurrentDate('spnsessionTillTime');
	},10);
	//     spinnerKeyPress('spnsessionFromTime');
		 spinnerChange('spnsessionFromTime','fillTillTime');
	     spinnerUp('spnsessionFromTime','fillTillTime');
	     spinnerDown('spnsessionFromTime','fillTillTime');
	//     spinnerKeyPress('spnsessionTillTime');	
		 spinnerChange('spnsessionTillTime','getTimediff');
	     spinnerUp('spnsessionTillTime','getTimediff');
	     spinnerDown('spnsessionTillTime','getTimediff');
	   //------------------------Gopi-------------------------------
		  // NEW: bind checkbox checked-state from server value (no AJAX, no DB overwrite)
			 var serverAssessCom = jQuery("#chkEtcaAssessmentCom").val();
			 if(serverAssessCom == "Y"){
			     jQuery('#chkEtcaAssessmentCom').prop('checked', true);
			     jQuery('#chkEtcaAssessmentCom').prop('disabled', true);
			 } else {
			     jQuery('#chkEtcaAssessmentCom').prop('checked', false);
			     jQuery('#chkEtcaAssessmentCom').prop('disabled', false);
			 }
			 //-----------------------------------gopi----------------------------------

});

  var mode=jQuery("#mode").val();
  if(mode=="view"){
	//	disableForm("frmNewTraCal"); // commenting for view mode -- Vignesh
		disableUIButton("btnFacultySave");
		disableUIButton("btnSessionsave");
		disableUIButton("btnUPSave");
		disableUIButton("btnUniqueAdd");
		disableUIButton("lblFilemgr");
		disableUIButton("btnEmployUnqDelete");
		disableUIButton("btnTopicsave");
		disableUIButton("btnFacultyAdd");	
 }

/* function fillTillTime(){ 
	var fromTime= getFieldValue("spnsessionFromTime");
	//alert(fromTime);
	var progDuration = jQuery('#txtEtcmMaxDuration').val();
	// alert(progDuration);
	 var hours=progDuration*60;
	// alert("hours"+hours);
	if(progDuration.trim().length>0){
		var tillTime = addMinutes(fromTime+":00",hours);
		//alert("tillTime"+tillTime);
		jQuery('#spnsessionTillTime').val(tillTime);
	}
} */

jQuery("#txtEtcmMaxDuration" ).blur(function(){
	 var keyid=jQuery("#txtEtcmMaxDuration").val();
	// alert(keyid);
	 if(keyid.trim.length<0)
		 {
		   
		   fillTillTime();
		 }
	 else{
	  fillTillTime();
	 }
	});


jQuery("#chkEtcmChkCompleted").click(function(){
	if(jQuery("#chkEtcmChkCompleted").is(":checked")==true)
		{
		  // added by vignesh 
		 fillComboBox("frmNewTraCal","cmbEtcmCompletedBy","employee.commonFilter");
	//	 var loginEmpId = jQuery("#hdnUserid").val();
	//	 jQuery("#cmbEtcmCompletedBy").combobox('setValue', loginEmpId);
		
		 
		if(jQuery("#txtEtcmKeyid").val()!=null&jQuery("#txtEtcmKeyid").val().length!=0)
			{ 
			var assess=jQuery("#chkEtcmAssessmentReqY").is(":checked");
			//alert("assess"+assess);
			var assesscom=jQuery("#chkEtcaAssessmentCom").is(":checked");
			//alert("assesscom"+assesscom);
			var Rating=jQuery("#cmbEtcmRating").combobox("getValue");
			if(assess==true)
			{
				if(assesscom==false)
					{
					   popupCommonErrorMsg("Please complete the Assessment!!");
					   return false;
					}
			}	
			var keyid=jQuery("#txtEtcmKeyid").val();
		    processAjaxCalls('chkcompleted.ntrc','&keyid='+keyid,'Chkcompleted_onsuccessCallBack','Chkcompleted_onerrorCallBack');
			}

		else{
			
			popupCommonErrorMsg("Without Enter Training Calendar, Unable to complete!!!!?");
			return false;
		    }
		
		var AnchoredBy=jQuery("#cmbEtcmAnchoredby").combobox("getValue");
		//alert(AnchoredBy);
		//alert(Rating);
		  if(AnchoredBy=="DHR" || AnchoredBy=="Corp.HR" || AnchoredBy=="Unit HR"){
			  if(Rating.length==0){
				   popupCommonErrorMsg("Please Enter the Rating");
	                return false;
			  }
		  }
		
		
		}
});
 // -- vignesh -- //
 






function btnFilManage_click(){
    var documentNo =jQuery("#txtEtcmKeyid").val();
	if(documentNo != null && documentNo != ''){
		var frmMode=jQuery('#frmMode').val();
		apMode = "create";
		if(frmMode=="View")
		   apMode = "view";
		fileManagerPopUp(documentNo,"ETC","","","","view");		
	} 
	else  
    {   var id=jQuery("#hdnFilemngr").val("Y");
		 popupCommonErrorMsg("Please save Training Calendar");
		 return false;
     }	
}
 
function formatDate(d) {
    var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    var day = d.getDate();
    var month = months[d.getMonth()];
    var year = d.getFullYear();

    return day + "-" + month + "-" + year;
}
 
function Chkcompleted_onsuccessCallBack(result)
{ 
   console.log("chkcompleted result:", result); 
   
   var chkvalue=result.iscomplete;
   var completedby=jQuery("#cmbEtcmCompletedBy").combobox('getValue');
 //  alert ("completed by " + completedby );
   if(chkvalue=="N")
	   {
	   jQuery('#chkEtcmChkCompleted').attr('checked',false);
	     popupCommonErrorMsg("Training is not Completed");
	     return false;
	   }
   else{
	   jQuery('#chkEtcmChkCompleted').attr('checked',true);
	   jQuery('#chkEtcmChkCompleted').val('Y');
	   enableFields("dteEtcmCompletedDate");
	   setTimeout(function () {fillWithCurrentDate('dteEtcmCompletedDate');},300);
	   if(completedby.length <= 1)
		  
	   {
		   // -- commented by vignesh 
		//   jQuery("#cmbEtcmCompletedBy").combobox('setValue','');
		   setTimeout(function () {
			    var data = jQuery("#cmbEtcmCompletedBy").combobox("getData");
			    if (data && data.length > 0) {
			        jQuery("#cmbEtcmCompletedBy").combobox("setValue", data[0].id);
			    }
			}, 300);
		   }
	//   alert ("completed by length " + completedby.length );
	   enableFields("cmbEtcmCompletedBy");
	   fillComboBox("frmNewTraCal","cmbEtcmCompletedBy","employee.commonFilter");
	   
   }
   
   jQuery("#dteEtcmCompletedDate").datebox({  	   
		onSelect:function(date)
			{ 
			var sysdate=jQuery("#hdncurrentdate").val();
			var currdate=jQuery("#dteEtcmCalendarDate").datebox('getValue');
			var approvalDate =formatDate(date); //jQuery("#dteEtcmCompletedDate").datebox('getValue');
			var currentDate = getServerDateTime();
		if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
				{
	

			/*if(convertStringToDate(sysdate) < convertStringToDate(approvalDate))
			{  

			      popupCommonErrorMsg('Should Not Enter the Future Date');
			       fillWithCurrentDate(dateCtrl);
					return false;
			}*/
			//else{
				if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
					{
					popupCommonErrorMsg('Should Not Exceed the Calendar Date');
					//fillWithCurrentDate(dateCtrl);
					setTimeout(function () {fillWithCurrentDate('dteEtcmCompletedDate');},100);
					return false
				//	}	
			}
				}	
			} 
		}); 
   
   
   jQuery("#dteSessiondate").datebox({  	   
		onSelect:function(recordid)
			{ 
			var sysdate=jQuery("#hdncurrentdate").val();
			var currdate=jQuery("#dteEtcmCalendarDate").datebox('getValue');
			var approvalDate =jQuery("#dteSessiondate").datebox('getValue');
			var currentDate = getServerDateTime();
		if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
				{
	

			if(convertStringToDate(sysdate) < convertStringToDate(approvalDate))
			{  
			}
			else{
				if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
					{
					popupCommonErrorMsg('Should Not Exceed the Calendar Date');
					fillWithCurrentDate(dateCtrl);
					return false
					}
				
			}
			
			
				}
			    	
			} 
		}); 
		
	
}

jQuery("#chkEtcaAssessmentCom").click(function(){
	if(jQuery("#chkEtcaAssessmentCom").is(":checked")==true)
		{
		 
		if(jQuery("#txtEtcmKeyid").val()!=null&jQuery("#txtEtcmKeyid").val().length!=0)
			{ 
			var keyid=jQuery("#txtEtcmKeyid").val();
		    processAjaxCalls('chkAssessmentCompleted.ntrc','&keyid='+keyid,'chkAssessmentCompleted_onsuccessCallBack','chkAssessmentCompleted_onerrorCallBack');
			}
		else{
			
			popupCommonErrorMsg("Without Enter Training Calendar, Unable to complete!!!!?");
			return false;
		    }
		}
	//-------------------------------gopi-----------------------------------------
	else{
		// NEW: uncheck  same reset API call as second JSP
		if(jQuery("#txtEtcmKeyid").val()!=null && jQuery("#txtEtcmKeyid").val().length!=0)
			{
			var keyid=jQuery("#txtEtcmKeyid").val();
			processAjaxCalls('resetAssessmentAM.ntrc','&keyid='+keyid,'resetAssessmentAM_onsuccessCallBack','resetAssessmentAM_onerrorCallBack');
			}
	}

	
});

function resetAssessmentAM_onsuccessCallBack(result)
{
	alert(result.msg);
	jQuery('#chkEtcaAssessmentCom').attr('checked', false);
}
function resetAssessmentAM_onerrorCallBack(result)
{
	popupCommonErrorMsg("Unable to reset assessment status");
}


function chkAssessmentCompleted_onsuccessCallBack(result)
{
	   var chkvalue=result.cnt;
	   if(chkvalue=="0")
		   {
		   jQuery('#chkEtcaAssessmentCom').attr('checked',false);
		     popupCommonErrorMsg("Assessment is not Completed");
		     return false;
		   }
	   else{
		   jQuery('#chkEtcaAssessmentCom').attr('checked',true);
		   jQuery('#chkEtcaAssessmentCom').val("Y");
	   }
}



 function getTimediff(){ 
 //alert("timedif");
	 var fromTime= getFieldValue("spnsessionFromTime");
//	 alert("fromTime" + fromTime);
	 var tillTime= getFieldValue("spnsessionTillTime");
//	 alert("tillTime" + tillTime);
	 var batchMonth = getFieldValue("dteSessiondate");
//	 alert("batchMonth" + batchMonth);
	 var progDuration=jQuery('#txtEtcmMaxDuration').val();
	// alert("progDuration" + progDuration);
	   
	 batchMonth =  batchMonth.replace('-',' ');
	 batchMonth =  batchMonth.replace('-',',');
	 
	 var difference = (Date.parse(batchMonth+" "+tillTime) - Date.parse(batchMonth+" "+fromTime)) / 60000;
	// alert("difference" + difference);
	 
	 if(parseInt(difference)>parseInt(progDuration*60))
		 {
		  popupCommonErrorMsg("Please select within Duration");
		  //jQuery('#spnsessionTillTime').val(fromTime);
		  jQuery('#spnsessionTillTime').spinner('setValue',fromTime);
		  return false;
		 }
 }

 function addMinutes(time, minsToAdd) {
		
	  function D(J){ return (J<10? '0':'') + J;};
	  var piece = time.split(':');
	  var mins = piece[0]*60 + +piece[1] + +minsToAdd;
	 // alert(mins);
	  return D(mins%(24*60)/60 | 0) + ':' + D(mins%60);  
	 // return D(mins%(24) | 0) + ':' + D(mins%60);  
	} 
 
jQuery("#tabTrainingCalendar").tabs(
 	    {
 		onSelect : function(title)
 		 {
 			if(title =="General" ){
 				 var TraKeyid = jQuery("#txtEtcmKeyid").val();
 				 jQuery("#hdntitle").val("General");
 		   processGridnew("Faculty_input.ntrc","?q=2&TraKeyid="+TraKeyid,"Gengrid","pagerid","","facultydoubleclickGrid","","");
 			}
 			else if(title =="Session/Unique Position" ){
 				jQuery("#hdntitle").val("Session/Unique Position");
 				var TraKeyid = jQuery("#txtEtcmKeyid").val();
 				if(TraKeyid.length==0)
 					{
 					  popupCommonErrorMsg("Please Save Training Calendar");
 					  return false;
 					}
 				else{
 				var uniqchk=jQuery("#chkEtcmUniqueposition").is(":checked");   
                if(uniqchk==false)
                    {
                     disableField("frmNewTraCal","chkUniquePosition");
                     disableField("frmNewTraCal","cmbEtcuRoleKeyid");
                     disableField("frmNewTraCal","btnUPSave");
                   //mano
                     disableField("frmNewTraCal", "cmbEtcuRoleDmt");   // ADD THIS
                     disableField("frmNewTraCal", "cmbEtcuRoleJh");
                   //  mano end
                    }
 				var flid = jQuery("#frmNewTraCal input[id='flid']").val();
 				if(flid.length!=0)
 					{
                   // alert(flid);
 					reloadCombo("frmNewTraCal","cmbEtcuRoleKeyid","roleMst.commonFilter?&flid="+flid+"&childFlids=Y");

 					}
 				 if(TraKeyid.length!=0)
 		 		 {
 		 		 fillWithCurrentDate("sessionFromTime");
 		 		 fillWithCurrentDate("sessionTillTime");
 		 		 fillWithCurrentDate('spnsessionFromTime');
 		 		 fillWithCurrentDate('spnsessionTillTime');
 		 		 }
 				 else{
 					 jQuery("#dteSessiondate").datebox('setValue',jQuery("#dteEtcmCalendarDate").datebox('getValue'));
 					 
 				 }
 				 processGridnew("session_input.ntrc","q=2&TraKeyid="+TraKeyid,"SesGrid","Sespagerid","","sessiondoubleclickGrid","","");
 			     processGridnew("NewuniquePositionLink_input.ntrc","q=2&TraKeyid="+TraKeyid,"UniqueGrid", "Uniquepagerid","","uniqPosdoubleclickGrid", "", "");
 			 }

 			}	
 			else if(title == "Employee" ){

 				jQuery("#hdntitle").val("Employee");
                var TraKeyid = jQuery("#txtEtcmKeyid").val();
                if(TraKeyid.length==0)
					{
                	 popupCommonErrorMsg("Please Save Training Calendar");
					  return false;
					
					}
              
                else{
                  processGridnew("NewUniquePositionSelectd_input.ntrc","q=2&TrgId="+TraKeyid,"selectedRoleGrd","rolepageremp","","uniqueempdoubleclick","","upempload_complete");
                }
                }
 			else if(title =="Employee Attendance" ){

 				  jQuery("#hdntitle").val("Employee Attendance");
 				  var TraKeyid = jQuery("#txtEtcmKeyid").val();
 				  var empattnd=jQuery("#hdnEmpattnd").val();
 				  var maxmarks=jQuery("#hdnmaxmarks").val();
 				  var cutoff=jQuery("#hdncutoff").val();
 				  var asscnt=jQuery("#hdnAssescnt").val();
 				  
 				 var type=jQuery("#hdntype").val();
 				 
 				//   alert("type insdie jsp  " + type);
 				   
 				  var mode=jQuery("#mode").val();
 				  if(mode=="view"){
 					 jQuery("#chkEtcmChkCompleted").attr('disabled',true);
 				  }
 				  
 				  else{
 					 jQuery("#chkEtcmChkCompleted").attr('disabled',false);	  
 				  }
 				// ------------------------Vignesh 17Mar2026 --------------------------------------------//	  
 				 	 
//  				  if(maxmarks!=null)
//  					  {
//  					    jQuery("#txtEtcaMaxMarks").val(maxmarks);
//  					  }
 				  
//  				 if(cutoff!=null)
// 				  {
// 				    jQuery("#txtEtcaCutOff").val(cutoff);
// 				  }
 				 
//  				 if(type!=null)
// 				  {
 					 
// 				    jQuery("#cboEtcaType").val(type);
				    
// 				  }
//  				 if(empattnd > 0)
//  					 {
//  					 // disableField("frmNewTraCal","txtEtcaCutOff");
//  					 // disableField("frmNewTraCal","txtEtcaMaxMarks");
//  					 }
   
   if (!maxmarks || maxmarks.trim() == '' || maxmarks.trim() == '0') {
    maxmarks = jQuery("#txtEtcaMaxMarks").val(); // keep what user typed
}

var cutoff = jQuery("#hdncutoff").val();
if (!cutoff || cutoff.trim() == '' || cutoff.trim() == '0') {
    cutoff = jQuery("#txtEtcaCutOff").val(); // keep what user typed
}

var type = jQuery("#hdntype").val();
if (!type || type.trim() == '') {
    type = jQuery("#cboEtcaType").val(); // keep what user selected
}

// Set visible fields only if we have a value — never overwrite with blank
if (maxmarks != null && maxmarks.trim() != '') {
    jQuery("#txtEtcaMaxMarks").val(maxmarks);
}
if (cutoff != null && cutoff.trim() != '') {
    jQuery("#txtEtcaCutOff").val(cutoff);
}
if (type != null && type.trim() != '') {
    jQuery("#cboEtcaType").val(type);
}

// Issue 2 fix: disable fields when attendance already saved (empattnd > 0)
// Works in both modify mode (empattnd from server) and create mode (after first save)
if (empattnd > 0) {
    disableField("frmNewTraCal", "txtEtcaCutOff");
    disableField("frmNewTraCal", "txtEtcaMaxMarks");
}

// ------------------------Vignesh 17Mar2026 --------------------------------------------//
 				 
 				 

 				if(TraKeyid.length==0)
				{
            	 popupCommonErrorMsg("Please Save Training Calendar");
				  return false;
				
				}
 				else{
 			processGridnew("session_input.ntrc","q=2&TraKeyid="+TraKeyid,"SesGrid","Sespagerid","","sessiondoubleclickGrid","","");	
 		            
 				var marksbsd=jQuery("#hdnmark").val();
 			    var assessment=jQuery("#hdnassessment").val();
 
 			   
 			  /* if(asscnt!=null)
		    	{
		    	  if(asscnt==1)
		    		  {
		    		 jQuery('#chkEtcaAssessmentCom').attr('disabled',true);
		    		 jQuery('#chkEtcaAssessmentCom').attr('checked', true);
		    		  }
		    	} */

 			
 				 if(TraKeyid.length!=0){
 				 			 processAjaxCalls('chkAssessmentCompleted.ntrc','&keyid='+TraKeyid,'assessmentStatus_onSuccess','assessmentStatus_onError');
 				 	}

 			   
 			  if(marksbsd=="N" && assessment=="Y")
			  {
			 disableField("frmNewTraCal","txtEtcaMaxMarks");
		     disableField("frmNewTraCal","txtEtcaCutOff");
		     
			  
			  }
 			   
 			  if(marksbsd=="N" && assessment=="N")
 				  {
 				 disableField("frmNewTraCal","txtEtcaMaxMarks");
 				 // COMMENTED BY VIGNESH
 				 //disableField("frmNewTraCal","chkEtcaAssessmentCom");
				 disableField("frmNewTraCal","txtEtcaCutOff")
 				  
 				  }
 			     
 			    processGridnew("EmpTrainingAtt_input.ntrc","q=2&TraKeyid="+TraKeyid+"&Mark="+marksbsd+"&Asse="+assessment,"EmpAttenGrid","emppagerid","","","EmpAttendanceloadComplete","");
 			}
 			}
 			else if(title=="FeedBack"){
 				jQuery("#hdntitle").val("FeedBack");
 				jQuery("#cmbEtcmRating").combobox("clear");
 				 var TraKeyid = jQuery("#txtEtcmKeyid").val();
                 if(TraKeyid.length==0){
                 	  popupCommonErrorMsg("Please Save Training Calendar");
 					  return false;
 				}
                var AnchoredBy=jQuery("#cmbEtcmAnchoredby").combobox("getValue");
 				//alert(AnchoredBy);	
 				var assess=jQuery("#chkEtcmAssessmentReqY").is(":checked"); 
 			//	alert(assess);
 				var assesscom=jQuery("#chkEtcaAssessmentCom").is(":checked");
 			//	alert(assesscom);
 				if(assess==true){
 					if(assesscom==false){
 						 popupCommonErrorMsg("Please complete the Assessment!!");
 						 return false;
 					}
 					
 				}

 				if(AnchoredBy=="DMT" || AnchoredBy=="Safety"){
 				//	alert("if");
					 jQuery('#lblRating').removeClass('mandatory-lbl');
 					
 				}
 				else{
 					// alert("Else");
					  jQuery('#lblRating').addClass('mandatory-lbl');

 					
 				}
 				
 				/*jQuery("#cmbEtcmRating").combobox("clear");
 				var assesscom=jQuery("#chkEtcaAssessmentCom").is(":checked");
 				var Assessment=jQuery("#chkEtcmAssessmentReq").is(":checked");
 				alert(assesscom);
 				alert(Assessment);
 				if(assesscom==false && Assessment==false){
 					popupCommonErrorMsg("Please complete the Assessment!!");
					   return false;
 				}*/
 			}
 			else{
 				jQuery("#hdntitle").val("General");
 			}
 		 }
 		 });
//---- added by vignesh for cell color 28 nov 2025 ---------------------------------------------------//

function EmpAttendanceloadComplete(){
     
	// Vignesh 17Mar2026
	_empAttenInitializedRows = {}; 
	// Delegated click — survives jqGrid DOM redraws, no per-row attachment needed
    jQuery("#EmpAttenGrid").off('click', 'input[id^="btnFilManage_EmpAttenGrid_"]')
        .on('click', 'input[id^="btnFilManage_EmpAttenGrid_"]', function () {
            // Extract rowId from button id: btnFilManage_EmpAttenGrid_11 → 11
            var clickedRowId = this.id.replace('btnFilManage_EmpAttenGrid_', '');
            // Read keyid live from grid at click time
            var documentNo = jQuery("#EmpAttenGrid").jqGrid('getCell', clickedRowId, "hdnEtcaKeyid");
            if (documentNo != null && documentNo != '' && documentNo.trim().length > 1) {
                var frmMode = jQuery('#frmMode').val();
                apMode = "create";
                if (frmMode == "View") apMode = "view";
                fileManagerPopUp(documentNo, "YY", "", "", "", apMode);
            } else {
                alert("Pls Save Attendance For Employee");
                return false;
            }
        });
	
    // Delegated present/absent change — works for ALL rows including select all
    jQuery("#EmpAttenGrid").off('change', 'select[id^="cmbEtcaPresentAbsent_EmpAttenGrid_"]')
        .on('change', 'select[id^="cmbEtcaPresentAbsent_EmpAttenGrid_"]', function () {
            // Extract rowId from select id: cmbEtcaPresentAbsent_EmpAttenGrid_11 → 11
            var clickedRowId = this.id.replace('cmbEtcaPresentAbsent_EmpAttenGrid_', '');
            var jqGridId     = "EmpAttenGrid";
            var presentabsent = jQuery(this).val();

            if (presentabsent == "A") {
                removeMandatoryFieldForComplete(jqGridId, clickedRowId);
                jQuery("#txtEtcaScore_"  + jqGridId + "_" + clickedRowId).val('0');
                jQuery("#cmbEtcaResult_EmpAttenGrid_" + clickedRowId).val('F');
                jQuery("#cmbEtcaResult_" + jqGridId + "_" + clickedRowId).attr('readonly', true);
            } else {
                makeMandatoryFieldForPending(jqGridId, clickedRowId);
                jQuery("#cmbEtcaResult_EmpAttenGrid_" + clickedRowId).val('P');
                jQuery("#txtEtcaScore_"  + jqGridId + "_" + clickedRowId).val("0");
                jQuery("#cmbEtcaResult_" + jqGridId + "_" + clickedRowId).attr('readonly', false);
            }
        });
    
 // Delegated score keydown — numeric only, works for all rows including select all
    jQuery("#EmpAttenGrid").off('keydown', 'input[id^="txtEtcaScore_EmpAttenGrid_"]')
        .on('keydown', 'textarea[id^="txtEtcaScore_EmpAttenGrid_"]', function () {
            this.value = this.value.replace(/[^0-9]/g, '');
        });

    // Delegated score keyup — pass/fail based on cutoff/maxmarks, works for all rows
    jQuery("#EmpAttenGrid").off('keyup', 'input[id^="txtEtcaScore_EmpAttenGrid_"]')
        .on('keyup', 'textarea[id^="txtEtcaScore_EmpAttenGrid_"]', function () {
            // Extract rowId from input id: txtEtcaScore_EmpAttenGrid_11 → 11
            var clickedRowId = this.id.replace('txtEtcaScore_EmpAttenGrid_', '');
            var jqGridId     = "EmpAttenGrid";
            var marksbsd     = jQuery("#hdnmark").val();
            var assessment   = jQuery("#hdnassessment").val();
            var maxmarks     = jQuery("#txtEtcaMaxMarks").val();
            var score        = jQuery(this).val();
            var cutoff       = jQuery("#txtEtcaCutOff").val();

            if (marksbsd == "Y" && assessment == "Y") {
                if ((cutoff.length > 0) && (maxmarks.length > 0)) {
                    if (parseInt(score) > parseInt(maxmarks)) {
                        popupCommonErrorMsg("Score is higher than Max Marks");
                        return false;
                    }
                    if (parseInt(score) <= parseInt(maxmarks) && parseInt(score) >= parseInt(cutoff)) {
                        jQuery("#cmbEtcaResult_" + jqGridId + "_" + clickedRowId).val('P');
                    } else {
                        jQuery("#cmbEtcaResult_" + jqGridId + "_" + clickedRowId).val('F');
                    }
                } else {
                    popupCommonErrorMsg("Please enter Cut Off & Max Marks");
                    jQuery(this).val('');
                    return false;
                }
            }
        });
	// Vignesh 17Mar2026
    var mode = jQuery("#mode").val();	 
 //   alert("mode:"+mode);
    if (mode == "view") {
        var row = jQuery("#EmpAttenGrid").jqGrid('getDataIDs');
        var cm  = jQuery("#EmpAttenGrid").jqGrid("getGridParam", "colModel");

        for (var i = 0; i < row.length; i++) {

            var etcakeyid         = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "hdnEtcaKeyid");	
            var presentabsent     = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "cmbEtcaPresentAbsent");
            var attendanceFileMgr = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "btnFilManage"); 
            var attendanceDate    = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "dteEtcaAddDate"); 
            var attendanceRemarks = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "txtEtcaRemarks");
            var attendanceResult  = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "cmbEtcaResult");
            var attendanceScore   = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "txtEtcaScore");

            if (etcakeyid.trim().length > 0) { 
                jQuery('#EmpAttenGrid').setSelection(row[i], true);
                jQuery('input:checkbox[id=jqg_EmpAttenGrid_' + row[i] + ']').attr('checked', true);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'selctVal', '1');
                jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'cmbEtcaPresentAbsent', presentabsent);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'btnFilManage', attendanceFileMgr); 
                jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'dteEtcaAddDate', attendanceDate);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'txtEtcaRemarks', attendanceRemarks);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'cmbEtcaResult', attendanceResult);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'txtEtcaScore', attendanceScore);
                jQuery('input:checkbox[id=jqg_EmpAttenGrid_' + row[i] + ']').attr('disabled', true);

                // ==== NEW: color logic ====
                // Attendance: PRESENT = green, ABSENT = red
                if (presentabsent && presentabsent.toUpperCase() == "PRESENT") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row[i], 'cmbEtcaPresentAbsent', '',
                        { 'background-color': 'green' }
                    );
                } else if (presentabsent && presentabsent.toUpperCase() == "ABSENT") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row[i], 'cmbEtcaPresentAbsent', '',
                        { 'background-color': 'red' }
                    );
                }

                // Result + Score: Pass = green, Fail = red
                if (attendanceResult && attendanceResult.toUpperCase() == "PASS") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row[i], 'cmbEtcaResult', '',
                        { 'background-color': 'green' }
                    );
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row[i], 'txtEtcaScore', '',
                        { 'background-color': 'green', 'text-align': 'center' }
                    );
                } else if (attendanceResult && attendanceResult.toUpperCase() == "FAIL") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row[i], 'cmbEtcaResult', '',
                        { 'background-color': 'red' }
                    );
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row[i], 'txtEtcaScore', '',
                        { 'background-color': 'red', 'text-align': 'center' }
                    );
                }
                // ==== END color logic ====
            }
        }

    } else if (mode == 'modify' || mode == 'create') {

        var row2 = jQuery("#EmpAttenGrid").jqGrid('getDataIDs');
        var cm2  = jQuery("#EmpAttenGrid").jqGrid("getGridParam", "colModel");

        for (var j = 0; j < row2.length; j++) {

            var etcakeyid2         = jQuery("#EmpAttenGrid").jqGrid('getCell', row2[j], "hdnEtcaKeyid");	
            var presentabsent2     = jQuery("#EmpAttenGrid").jqGrid('getCell', row2[j], "cmbEtcaPresentAbsent");
            var attendanceFileMgr2 = jQuery("#EmpAttenGrid").jqGrid('getCell', row2[j], "btnFilManage"); 
            var attendanceDate2    = jQuery("#EmpAttenGrid").jqGrid('getCell', row2[j], "dteEtcaAddDate"); 
            var attendanceRemarks2 = jQuery("#EmpAttenGrid").jqGrid('getCell', row2[j], "txtEtcaRemarks");
            var attendanceResult2  = jQuery("#EmpAttenGrid").jqGrid('getCell', row2[j], "cmbEtcaResult");
            var attendanceScore2   = jQuery("#EmpAttenGrid").jqGrid('getCell', row2[j], "txtEtcaScore");

            if (etcakeyid2.trim().length > 0) { 
                // jQuery('#EmpAttenGrid').setSelection(row2[j], true);

                jQuery('input:checkbox[id=jqg_EmpAttenGrid_' + row2[j] + ']').prop('checked', true);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row2[j], 'selctVal', '1');
                jQuery("#EmpAttenGrid").jqGrid('setCell', row2[j], 'cmbEtcaPresentAbsent', presentabsent2);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row2[j], 'btnFilManage', attendanceFileMgr2); 
                jQuery("#EmpAttenGrid").jqGrid('setCell', row2[j], 'dteEtcaAddDate', attendanceDate2);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row2[j], 'txtEtcaRemarks', attendanceRemarks2);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row2[j], 'cmbEtcaResult', attendanceResult2);
                jQuery("#EmpAttenGrid").jqGrid('setCell', row2[j], 'txtEtcaScore', attendanceScore2);
                jQuery('input:checkbox[id=jqg_EmpAttenGrid_' + row2[j] + ']').prop('disabled', true);

                // ==== NEW: color logic (same as above) ====
                if (presentabsent2 && presentabsent2.toUpperCase() == "PRESENT") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row2[j], 'cmbEtcaPresentAbsent', '',
                        { 'background-color': 'green' }
                    );
                } else if (presentabsent2 && presentabsent2.toUpperCase() == "ABSENT") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row2[j], 'cmbEtcaPresentAbsent', '',
                        { 'background-color': 'red' }
                    );
                }

                if (attendanceResult2 && attendanceResult2.toUpperCase() == "PASS") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row2[j], 'cmbEtcaResult', '',
                        { 'background-color': 'green' }
                    );
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row2[j], 'txtEtcaScore', '',
                        { 'background-color': 'green', 'text-align': 'center' }
                    );
                } else if (attendanceResult2 && attendanceResult2.toUpperCase() == "FAIL") {
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row2[j], 'cmbEtcaResult', '',
                        { 'background-color': 'red' }
                    );
                    jQuery("#EmpAttenGrid").jqGrid(
                        'setCell', row2[j], 'txtEtcaScore', '',
                        { 'background-color': 'red', 'text-align': 'center' }
                    );
                }
                // ==== END color logic ====
            }
        }
        
        // addedd  BY VIGNESH 09Mar2026 -- for employee attendance checkbox
        if(jQuery("#chkSelectAllEmp").is(':checked')== true){
        fnSelectAllEmp(); 
        }
    }
}



// function EmpAttendanceloadComplete(){
// 	var mode=jQuery("#mode").val();	 
// 	     if(mode=="view"){
// 		 var row = jQuery("#EmpAttenGrid").jqGrid('getDataIDs');
// 			var cm = jQuery("#EmpAttenGrid").jqGrid("getGridParam", "colModel");
// 			 for(var i=0;i<row.length;i++)
// 			 {
// 				   var etcakeyid = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"hdnEtcaKeyid");	
// 				   var presentabsent = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"cmbEtcaPresentAbsent");
// 				   var attendanceFileMgr = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"btnFilManage"); 
// 				   var attendanceDate = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"dteEtcaAddDate"); 
// 				   var attendanceRemarks=jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"txtEtcaRemarks");
// 				   var attendanceResult=jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"cmbEtcaResult");
// 				   var attendanceScore=jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"txtEtcaScore");
// 				   if(etcakeyid.trim().length>0){ 
// 					    jQuery('#EmpAttenGrid').setSelection(row[i], true);
// 					    jQuery('input:checkbox[id=jqg_EmpAttenGrid_'+row[i]+']').attr('checked',true);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'selctVal','1');
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'cmbEtcaPresentAbsent',presentabsent);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'btnFilManage',attendanceFileMgr); 
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'dteEtcaAddDate',attendanceDate);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'txtEtcaRemarks',attendanceRemarks);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'cmbEtcaResult',attendanceResult);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'txtEtcaScore',attendanceScore);
// 					    jQuery('input:checkbox[id=jqg_EmpAttenGrid_'+row[i]+']').attr('disabled',true);
// 				   }
// 			 }
// 	}
// 	   else if(mode=='modify' || mode=='create'){
// 		  var row = jQuery("#EmpAttenGrid").jqGrid('getDataIDs');
// 			var cm = jQuery("#EmpAttenGrid").jqGrid("getGridParam", "colModel");
// 			 for(var i=0;i<row.length;i++)
// 			 {
// 				   var etcakeyid = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"hdnEtcaKeyid");	
// 				   var presentabsent = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"cmbEtcaPresentAbsent");
// 				   var attendanceFileMgr = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"btnFilManage"); 
// 				   var attendanceDate = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"dteEtcaAddDate"); 
// 				   var attendanceRemarks=jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"txtEtcaRemarks");
// 				   var attendanceResult=jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"cmbEtcaResult");
// 				   var attendanceScore=jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"txtEtcaScore");
// 				   if(etcakeyid.trim().length>0){ 
// 					    //jQuery('#EmpAttenGrid').setSelection(row[i], true);
					   
// 					    jQuery('input:checkbox[id=jqg_EmpAttenGrid_'+row[i]+']').attr('checked',true);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'selctVal','1');
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'cmbEtcaPresentAbsent',presentabsent);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'btnFilManage',attendanceFileMgr); 
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'dteEtcaAddDate',attendanceDate);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'txtEtcaRemarks',attendanceRemarks);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'cmbEtcaResult',attendanceResult);
// 					    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'txtEtcaScore',attendanceScore);
// 					    jQuery('input:checkbox[id=jqg_EmpAttenGrid_'+row[i]+']').attr('disabled',true);
// 				   }
// 			 }  
// 	  }     
//  }
 			 //---- added by vignesh for cell color
 			 
 			 
 			 

function upempload_complete(){
	  var row = jQuery("#selectedRoleGrd").jqGrid('getDataIDs');
	  var cm = jQuery("#selectedRoleGrd").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		   var etcskeyid = jQuery("#selectedRoleGrd").jqGrid('getCell',row[i],"txtAttnkeyid");	
		 	if(etcskeyid.trim().length>0){ 
			    jQuery('#selectedRoleGrd').setSelection(row[i], false);
			    jQuery("#selectedRoleGrd").jqGrid('setCell',row[i],'selctVal','1');
			    jQuery('input:checkbox[id=jqg_selectedRoleGrd_'+row[i]+']').attr('disabled',true);
		   }
	 }
 }

function applyEmpAttenRowState(rowId) {
    var jqGridId = "EmpAttenGrid";
    var marksbsd  = jQuery("#hdnmark").val();
    var assessment = jQuery("#hdnassessment").val();

    if (marksbsd == "Y" && assessment == "Y") {
        // Marks-based: result is readonly (calculated from score)
        jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('readonly','readonly');
        jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).css('background-color','#ece9d8');

    } else if (marksbsd == "N" && assessment == "Y") {
        jQuery("textarea#txtEtcaScore_"+jqGridId+"_"+rowId).attr('readonly','readonly');
        jQuery("textarea#txtEtcaScore_"+jqGridId+"_"+rowId).css('background-color','#ece9d8');
        jQuery("textarea#txtEtcaScore_"+jqGridId+"_"+rowId).val('0');
    } else if (marksbsd == "N" && assessment == "N") {
        jQuery("textarea#txtEtcaScore_"+jqGridId+"_"+rowId).attr('readonly','readonly');
        jQuery("textarea#txtEtcaScore_"+jqGridId+"_"+rowId).css('background-color','#ece9d8');
        jQuery("textarea#txtEtcaScore_"+jqGridId+"_"+rowId).val("0");
    }
}


// function EmpAttenGrid_selectRow(rowId)
// {
// 	var jqGridId="EmpAttenGrid";
// 	var dateCtrl="dteEtcaAddDate_EmpAttenGrid_"+rowId;
// 	var divfilemangerkeyid="btnFilManage_EmpAttenGrid_"+rowId;
//     var keyid=jQuery("#EmpAttenGrid").jqGrid('getCell',rowId,"hdnEtcaKeyid");
// 	formatDateBox(dateCtrl,'dd-MMM-yyyy');
// 	//fillWithCurrentDate(dateCtrl);
// 		 /*var mode=jQuery("#mode").val();
// 		 if(mode=="create"){
// 			 fillWithCurrentDate(dateCtrl);
// 		 }*/
		 
// 		 var marksbsd=jQuery("#hdnmark").val();
// 		    var assessment=jQuery("#hdnassessment").val();
// 		    if(marksbsd=="Y" && assessment=="Y")
// 		    	{
		    	
// 		    	  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('readonly','readonly'); 
// 				  //jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('disabled',true); 
// 				  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).css('background-color', '#ece9d8');
// 		    	}
		    
	    	
	    	
// 	    	else if(marksbsd=="N" && assessment=="Y")
// 		    	{
// 				  jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).attr('readonly','readonly'); 
// 				  //jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).attr('disabled',true); 
// 				  jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).css('background-color', '#ece9d8');
// 				  jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).val('0');
// 		    	}
		    
// 		    else{
// 		    	if(marksbsd=="N" && assessment=="N")
// 		    		{
// 		    	jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).attr('readonly','readonly'); 
// 				  //jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).attr('disabled',true); 
// 				  jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).css('background-color', '#ece9d8');
// 				  jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).val("0");
// 		    		}
// 		    }

			    
		    
// jQuery("#"+dateCtrl).datebox({  	   
// 		onSelect:function(recordid)
// 			{ 
// 			isValidDate(dateCtrl,rowId);    	
// 			} 
// 		}); 
		  
// 		  // COMMENTED BY VIGNESH 09Mar2026 -- for attendance session date
		  
//          /* if(keyid.length==1){
// 			var sesdate=getGridCell('SesGrid','1','Session');
//            jQuery("#dteEtcaAddDate_"+jqGridId+"_"+rowId).datebox('setValue',sesdate);
// 	  } */	
		

// jQuery("#cmbEtcaPresentAbsent_EmpAttenGrid_"+rowId).combobox({  	   
// 	onSelect:function(recordid)
// 		{
// 		//alert(recordid.value) ;
//         //alert(recordid.id) ;
// 		var presentabsent=recordid.value; //  jQuery("#cmbEtcaPresentAbsent_EmpAttenGrid_"+rowId).combobox('getValue');
// 		var score=jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).val();
// 		var cutoff=jQuery("#txtEtcaCutOff").val();
// 		var maxmarks=jQuery("#txtEtcaMaxMarks").val();
// 		if(presentabsent=="A")
// 			{
// 		         removeMandatoryFieldForComplete("EmpAttenGrid",rowId);
// 			 jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).val('0');
// 			  jQuery("#cmbEtcaPresentAbsent_EmpAttenGrid_"+rowId).val('A');
// 			  jQuery("#cmbEtcaResult_EmpAttenGrid_"+rowId).val('F');
// 			  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('readonly',true); 
// 			}
// 		else {
// 			  makeMandatoryFieldForPending("EmpAttenGrid",rowId);
// 			  jQuery("#cmbEtcaResult_EmpAttenGrid_"+rowId).val('P');
// 			  jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).val("0");
// 			  jQuery("#cmbEtcaPresentAbsent_EmpAttenGrid_"+rowId).val('P');
			  
// 		} 	
// 		} 
		
// 	}); 
		
// 	   jQuery("#"+divfilemangerkeyid).click(function(){
		   
// 	       var documentNo =keyid;
// 	       if(documentNo.length!=1)
// 	    	   {
// 	   		if(documentNo != null && documentNo != ''){
// 	   			var frmMode=jQuery('#frmMode').val();
// 	   			apMode = "create";
// 	   			if(frmMode=="View")
// 	   			   apMode = "view";
// 	   			fileManagerPopUp(documentNo,"YY","","","",apMode);
// 	   		}
// 	    	   }
// 	       else{
// 	    	  alert("Pls Save Attendance For Employee");
// 	    	  return false;
// 	       }
	       
// 	    }		
// 	    		);
	   
// 	      jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).keydown(function () {
// 		  this.value = this.value.replace(/[^0-9]/g, '');
		
// 	   }
// 	   );
	   
	

//        jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).keyup(function(){  
// 		   var marksbsd=jQuery("#hdnmark").val();
// 		    var assessment=jQuery("#hdnassessment").val();
// 		    var maxmarks=jQuery("#txtEtcaMaxMarks").val();
                   
                  
// 		    var score=jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).val();
// 		    var cutoff=jQuery("#txtEtcaCutOff").val();
// 		    if(marksbsd=="Y" && assessment=="Y")
// 	    	{
// 	    	 if((cutoff.length > 0) &&  (maxmarks.length > 0)) 
// 	    	 {	
//                       if(parseInt(score)> parseInt(maxmarks))
//                        {
//                           popupCommonErrorMsg("Score is higher than Max Marks");
//                           return false;
//                        } 
	    	
// 	    	  if(parseInt(score) <= parseInt(maxmarks) && parseInt(score) >= parseInt(cutoff) )
// 	    	  {
// 	    		  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).val('P');
// 	    	  }
// 	    	  else{
	    		  
// 	    		  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).val('F');
	    		  
// 	    	  }
// 	    	 }
// 	    	 else{
// 	    		 popupCommonErrorMsg("Please enter Cut Off & Max Marks");
// 	    		 jQuery("#txtEtcaScore_"+jqGridId+"_"+rowId).val('');
// 	    		   return false;
// 	    	 }
	    	
// 	    	}	    
		  
// 	   });
       
// }
//----------------------------------- Vignesh 17Mar2026 ------------------------------------------//
// FIXED EmpAttenGrid_selectRow

function EmpAttenGrid_selectRow(rowId) {

	
    // During select all: skip heavy init — applyEmpAttenRowState handles state in bulk
    if (_empAttenSelectAll) return;

    var jqGridId          = "EmpAttenGrid";
    var dateCtrl          = "dteEtcaAddDate_EmpAttenGrid_" + rowId;
    var divfilemangerkeyid = "btnFilManage_EmpAttenGrid_" + rowId;
    var keyid = jQuery("#EmpAttenGrid").jqGrid('getCell', rowId, "hdnEtcaKeyid");

    // Always apply state on individual row click (fixes issues 1, 2, 3)
    applyEmpAttenRowState(rowId);

    // ONE-TIME widget init per row — prevents handler accumulation (fixes issue 4)
    if (!_empAttenInitializedRows[rowId]) {
        _empAttenInitializedRows[rowId] = true;

        // Datebox init — expensive, only once
        formatDateBox(dateCtrl, 'dd-MMM-yyyy');
        jQuery("#" + dateCtrl).datebox({
            onSelect: function (recordid) {
                isValidDate(dateCtrl, rowId);
            }
        });

     // Native <select> change event — not a combobox widget
       

        // File manager — .off() prevents accumulation (fixes issue 4)


        // Score input — .off() prevents accumulation
    
    }
	// Run this in browser console after clicking any attendance row
	console.log(jQuery("#cmbEtcaPresentAbsent_EmpAttenGrid_1").combobox('options'));
}
//----------------------------------- Vignesh 17Mar2026 ------------------------------------------//


function removeMandatoryFieldForComplete(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		if(colModel[k].index =="txtEtcaScore"){
			colModel[k].mandatory = false;
			removeMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}

function makeMandatoryFieldForPending(gridId,rowId){
	var colModel = jQuery("#"+gridId).jqGrid('getGridParam', 'colModel');
	for( var k =1;k<colModel.length;k++){
		if(colModel[k].index =="txtEtcaScore"){
			colModel[k].mandatory = true;
			setMandatoryCell(gridId,rowId,colModel[k].name);
		}
	}	
}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
}
function isValidDate(dateCtrl,ctrlRowId){
	var sysdate=jQuery("#hdncurrentdate").val();
	var currdate=jQuery("#dteEtcmCalendarDate").datebox('getValue');
	var approvalDate = getFieldValue(dateCtrl);
	var currentDate = getServerDateTime();
if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
		{
	/*if(convertStringToDate(sysdate) < convertStringToDate(approvalDate))
	{ 
		
        	popupCommonErrorMsg('Should Not Enter the Future Date');
  	        fillWithCurrentDate(dateCtrl);
  			return false;
      
	      
	}*/
	//else{		
		if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
			{
			popupCommonErrorMsg('Should Not Exceed the Calendar Date');
			fillWithCurrentDate(dateCtrl);
			return false
			//}
		
	}
	}
}	 
var etcmMaxDuration = document.getElementById('txtEtcmMaxDuration');

etcmMaxDuration.addEventListener('input', function (prev) {
	    return function (evt) {
	        if (!/^\d{0,9}(?:\.\d{0,2})?$/.test(this.value)) {
	          this.value = prev;
	        }
	        else {
	          prev = this.value;
	        }
	    };
	}(etcmMaxDuration.value), false);
	
var etcmPermittedStrength = document.getElementById('txtEtcmPermittedStrength');

etcmPermittedStrength.addEventListener('input', function (prev) {
	    return function (evt) {
	        if (!/^\d{0,9}(?:\.\d{0,2})?$/.test(this.value)) {
	          this.value = prev;
	        }
	        else {
	          prev = this.value;
	        }
	    };
	}(etcmPermittedStrength.value), false);

var etcaMaxMarks = document.getElementById('txtEtcaMaxMarks');
etcaMaxMarks.addEventListener('input', function (prev) {
	    return function (evt) {
	        if (!/^\d{0,9}(?:\.\d{0,2})?$/.test(this.value)) {
	          this.value = prev;
	        }
	        else {
	          prev = this.value;
	        }
	    };
	}(etcaMaxMarks.value), false);
 
var etcaCutOff = document.getElementById('txtEtcaCutOff');
etcaCutOff.addEventListener('input', function (prev) {
	    return function (evt) {
	        if (!/^\d{0,9}(?:\.\d{0,2})?$/.test(this.value)) {
	          this.value = prev;
	        }
	        else {
	          prev = this.value;
	        }
	    };
	}(etcaCutOff.value), false);
	
function facultydoubleclickGrid(id){
}

function sessiondoubleclickGrid(id){
	 
	
	 var rowData = jQuery("#SesGrid").jqGrid('getRowData',id);
	 var sessionId=rowData.etcs_keyid;
	 var frmTime = rowData.FromTime;
	 var tilTime = rowData.ToTime;
	 var sessiondte=rowData.Session;
	 
	 
	  console.log("sessiondoubleclickGrid – rowData =", rowData);
	  console.log("sessiondoubleclickGrid – resolved sessionId =", sessionId);

	 setFieldValue("dteSessiondate",sessiondte,"frmNewTraCal");
	 setFieldValue("spnsessionFromTime",frmTime,"frmNewTraCal");
	 setFieldValue("spnsessionTillTime",tilTime,"frmNewTraCal");
	 jQuery("#hdnSessionId").val(sessionId);  
	  

	    //alert(sessionId + " the session id inside doubleclick");
	
}

 /* function uniqPosdoubleclickGrid(id){
	 var rowData = jQuery("#UniqueGrid").jqGrid('getRowData',id);
	 var upId =rowData.ETCU_ROLE_KEYID;
	 var uniqukeyid=rowData.ETCU_KEYID;
	 jQuery('#txtEtcuRoleKeyid').val(upId);
	 setFieldValue("cmbEtcuRoleKeyid",upId,"frmNewTraCal");
	 var upDmt=rowData.DMT;
	 jQuery('#txtEtcuRoleDmt').val(upDmt);
	 setFieldValue("cmbEtcuRoleDmt",upDmt,"frmNewTraCal");
	 var upJh=rowData.JH;
	 jQuery('#txtEtcuRoleJh').val(upJh);
	 setFieldValue("cmbEtcuRoleJh",upJh,"frmNewTraCal");
 } */

function frmNewTraCalcmbEtcuRoleDmt_onSelect(record){
	var dmt = getFieldValue('cmbEtcuRoleDmt','frmNewTraCal');
	clearField("cmbEtcuRoleJh");
	processAjaxCalls("getjhvalue.ntrc?&originalval="+dmt,"","flidIdSuccess");
}

function frmNewTraCalcmbEtcuRoleJh_onSelect(record){
	var jhnval= getFieldValue('cmbEtcuRoleJh','frmNewTraCal');
	processAjaxCalls("getjhvalue.ntrc?&originalval="+jhnval,"","flidIdSuccess");
 }
 
function frmNewTraCalcmbEtcuRoleKeyid_onSelect(record){
	var roleid=record.id;
	//alert(roleid);
	processAjaxCalls("getjhforRole.ntrc?&roleid="+roleid,"","roleidIdSuccess");
	
}
 
function roleidIdSuccess(result)
{
	var section = result.successData.sect;
    var cell=result.successData.cell;
    setFieldValue("cmbEtcuRoleDmt",section);
    setFieldValue("cmbEtcuRoleJh",cell);
	
}

function flidIdSuccess(result){
	var section = result.successData.sect;
	var cell=result.successData.cellid;	
	var flid = result.successData.flid;
	//alert("Flid"+flid);
	 if(cell.length!=0)
		{
		 setFieldValue("cmbEtcuRoleDmt",section);
		 if(flid.length!=0)
			{
			  reloadCombo("frmNewTraCal","cmbEtcuRoleKeyid","roleMst.commonFilter?&flid="+flid+"&childFlids=N");
			}
		}
	if(section.length!=0)
	{
		reloadCombo("frmNewTraCal", "cmbEtcuRoleJh","cellCombo.commonFilter?sectionid=" +section);
		if(flid.length!=0)
		{
		  reloadCombo("frmNewTraCal","cmbEtcuRoleKeyid","roleMst.commonFilter?&flid="+flid+"&childFlids=Y");
		}
	} 
}

/* jQuery("#chkEtcmGeneral").click(function(){
	var keyid=jQuery("#txtEtcmKeyid").val();
	if(jQuery("#chkEtcmGeneral").is(':checked') == true){
		var uniq=jQuery("#chkEtcmUniqueposition").is(':checked');
		if(keyid.length!=0)
			{
		if(uniq==true)
			{
			    var s=confirm("Do You Want change a Unique position Training to General?");
			    if(s==true)
			    	{
			    	 jQuery('#chkEtcmUniqueposition').attr('checked',false);
			    	  jQuery('#chkEtcmUniqueposition').val('N');
			          jQuery("#chkEtcmGeneral").val('Y');
			   	  saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?&q=2");
			    	}
			    else{
			    	jQuery('#chkEtcmGeneral').attr('checked',false);
			    }
			}
		else{
	     jQuery('#chkEtcmUniqueposition').attr('checked',false);
		}
			}
		else{
			var uniq=jQuery("#chkEtcmUniqueposition").is(':checked');
			if(uniq==true)
				{
				 jQuery('#chkEtcmUniqueposition').attr('checked',false);
				}
		}
	}
	else{
		if(keyid.length==0)
		{
		jQuery('#chkEtcmUniqueposition').attr('checked',true);
		}
		else{
			jQuery('#chkEtcmGeneral').attr('checked',true);
		}
		
	}
}); */


/* jQuery("#chkEtcmGeneral").click(function(){
    var keyid = jQuery("#txtEtcmKeyid").val();
    if(jQuery("#chkEtcmGeneral").is(':checked') == true){
        var uniq = jQuery("#chkEtcmUniqueposition").is(':checked');
        if(keyid.length != 0){
            if(uniq == true){
                var s = confirm("Do You Want change a Unique position Training to General?");
                if(s == true){
                    // Uncheck Unique, keep General checked
                    jQuery('#chkEtcmUniqueposition').prop('checked', false);
                    jQuery('#chkEtcmUniqueposition').val('N');
                    jQuery('#chkEtcmGeneral').prop('checked', true);
                    jQuery('#chkEtcmGeneral').val('Y');
                    disableField("frmNewTraCal","cmbEtcuRoleDmt");
                    disableField("frmNewTraCal","cmbEtcuRoleJh");
                    disableField("frmNewTraCal","cmbEtcuRoleKeyid");
                    disableField("frmNewTraCal","btnUPSave");
                    saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?&q=2");
                }
                else{
                    // User cancelled - revert General to unchecked
                    jQuery('#chkEtcmGeneral').prop('checked', false);
                }
            }
            else{
                // Unique not checked - just check General, disable dropdowns
                jQuery('#chkEtcmUniqueposition').prop('checked', false);
                jQuery('#chkEtcmUniqueposition').val('N');
                disableField("frmNewTraCal","cmbEtcuRoleDmt");
                disableField("frmNewTraCal","cmbEtcuRoleJh");
                disableField("frmNewTraCal","cmbEtcuRoleKeyid");
                disableField("frmNewTraCal","btnUPSave");
            }
        }
        else{
            // New record
            jQuery('#chkEtcmUniqueposition').prop('checked', false);
            jQuery('#chkEtcmUniqueposition').val('N');
            disableField("frmNewTraCal","cmbEtcuRoleDmt");
            disableField("frmNewTraCal","cmbEtcuRoleJh");
            disableField("frmNewTraCal","cmbEtcuRoleKeyid");
            disableField("frmNewTraCal","btnUPSave");
        }
    }
    else{
        // General unchecked
        if(keyid.length == 0){
            jQuery('#chkEtcmUniqueposition').prop('checked', true);
            jQuery('#chkEtcmUniqueposition').val('Y');
            enableFields("cmbEtcuRoleDmt");
            enableFields("cmbEtcuRoleJh");
            enableFields("cmbEtcuRoleKeyid");
            enableFields("btnUPSave");
        }
        else{
            jQuery('#chkEtcmGeneral').prop('checked', true);
        }
    }
}); */

//mano
jQuery("#chkEtcmGeneral").click(function(){
    var keyid = jQuery("#txtEtcmKeyid").val();
    if(jQuery("#chkEtcmGeneral").is(':checked') == true){
        var uniq = jQuery("#chkEtcmUniqueposition").is(':checked');
        if(keyid.length != 0){
            if(uniq == true){
                var s = confirm("Do You Want change a Unique position Training to General?");
                if(s == true){
                    // Uncheck Unique, keep General checked
                    jQuery('#chkEtcmUniqueposition').prop('checked', false);
                    jQuery('#chkEtcmUniqueposition').val('N');
                    jQuery('#chkEtcmGeneral').prop('checked', true);
                    jQuery('#chkEtcmGeneral').val('Y');
                    disableField("frmNewTraCal","cmbEtcuRoleDmt");
                    disableField("frmNewTraCal","cmbEtcuRoleJh");
                    disableField("frmNewTraCal","cmbEtcuRoleKeyid");
                    disableField("frmNewTraCal","btnUPSave");
                    toggleDmtJhMandatory(false);
                    saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?&q=2");
                }
                else{
                    // User cancelled - revert General to unchecked
                    jQuery('#chkEtcmGeneral').prop('checked', false);
                }
            }
            else{
                // Unique not checked - just check General, disable dropdowns
                jQuery('#chkEtcmUniqueposition').prop('checked', false);
                jQuery('#chkEtcmUniqueposition').val('N');
                disableField("frmNewTraCal","cmbEtcuRoleDmt");
                disableField("frmNewTraCal","cmbEtcuRoleJh");
                disableField("frmNewTraCal","cmbEtcuRoleKeyid");
                disableField("frmNewTraCal","btnUPSave");
                toggleDmtJhMandatory(false);
            }
        }
        else{
            // New record
            jQuery('#chkEtcmUniqueposition').prop('checked', false);
            jQuery('#chkEtcmUniqueposition').val('N');
            disableField("frmNewTraCal","cmbEtcuRoleDmt");
            disableField("frmNewTraCal","cmbEtcuRoleJh");
            disableField("frmNewTraCal","cmbEtcuRoleKeyid");
            disableField("frmNewTraCal","btnUPSave");
            toggleDmtJhMandatory(false);
        }
    }
    else{
        // General unchecked
        if(keyid.length == 0){
            jQuery('#chkEtcmUniqueposition').prop('checked', true);
            jQuery('#chkEtcmUniqueposition').val('Y');
            enableFields("cmbEtcuRoleDmt");
            enableFields("cmbEtcuRoleJh");
            enableFields("cmbEtcuRoleKeyid");
            enableFields("btnUPSave");
            toggleDmtJhMandatory(true);
        }
        else{
            jQuery('#chkEtcmGeneral').prop('checked', true);
        }
    }
});



/* jQuery("#chkEtcmUniqueposition").click(function(){
	var keyid=jQuery("#txtEtcmKeyid").val();
	if(jQuery("#chkEtcmUniqueposition").is(':checked') == true){
		var General=jQuery("#chkEtcmGeneral").is(':checked');
		if(keyid.length!=0)
			{
		if(General==true)
			{
			  var s=confirm("Do you want to change a training from General to Unique Position?");
			  if(s==true)
				  {
			          jQuery('#chkEtcmUniqueposition').val('Y');
			    	  jQuery("#chkEtcmGeneral").val('N');
				  jQuery('#chkEtcmGeneral').attr('checked',false);
			    	saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?");
				  }
			  else{
				  jQuery('#chkEtcmUniqueposition').attr('checked',false);
			  }
			}
		
		else{
	     jQuery('#chkEtcmGeneral').attr('checked',false);
		}
			}
		
		else{
			var general=jQuery("#chkEtcmGeneral").is(':checked');
			if(general==true)
				{
				 jQuery('#chkEtcmGeneral').attr('checked',false);
				}
		}
	}
	else{
		if(keyid.length==0)
			{
		      jQuery('#chkEtcmGeneral').attr('checked',true);
			}
		else{
			 jQuery('#chkEtcmUniqueposition').attr('checked',true);
		}
	}
}); */

jQuery("#chkEtcmUniqueposition").click(function(){
    var keyid = jQuery("#txtEtcmKeyid").val();
    if(jQuery("#chkEtcmUniqueposition").is(':checked') == true){
        var General = jQuery("#chkEtcmGeneral").is(':checked');
        if(keyid.length != 0){
            if(General == true){
                var s = confirm("Do you want to change a training from General to Unique Position?");
                if(s == true){
                    // Uncheck General, keep Unique checked
                    jQuery('#chkEtcmGeneral').prop('checked', false);
                    jQuery('#chkEtcmGeneral').val('N');
                    jQuery('#chkEtcmUniqueposition').prop('checked', true);
                    jQuery('#chkEtcmUniqueposition').val('Y');
                    enableFields("cmbEtcuRoleDmt");
                    enableFields("cmbEtcuRoleJh");
                    enableFields("cmbEtcuRoleKeyid");
                    enableFields("btnUPSave");
                    toggleDmtJhMandatory(true);
                    saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?");
                }
                else{
                    // User cancelled - revert Unique to unchecked
                    jQuery('#chkEtcmUniqueposition').prop('checked', false);
                    jQuery('#chkEtcmUniqueposition').val('N');
                    disableField("frmNewTraCal","cmbEtcuRoleDmt");
                    disableField("frmNewTraCal","cmbEtcuRoleJh");
                    disableField("frmNewTraCal","cmbEtcuRoleKeyid");
                    disableField("frmNewTraCal","btnUPSave");
                    toggleDmtJhMandatory(false);
                }
            }
            else{
                // General not checked - just check Unique
                jQuery('#chkEtcmGeneral').prop('checked', false);
                jQuery('#chkEtcmGeneral').val('N');
                jQuery('#chkEtcmUniqueposition').val('Y');
                enableFields("cmbEtcuRoleDmt");
                enableFields("cmbEtcuRoleJh");
                enableFields("cmbEtcuRoleKeyid");
                enableFields("btnUPSave");
                toggleDmtJhMandatory(true);
            }
        }
        else{
            // New record
            jQuery('#chkEtcmGeneral').prop('checked', false);
            jQuery('#chkEtcmGeneral').val('N');
            jQuery('#chkEtcmUniqueposition').val('Y');
            enableFields("cmbEtcuRoleDmt");
            enableFields("cmbEtcuRoleJh");
            enableFields("cmbEtcuRoleKeyid");
            enableFields("btnUPSave");
            toggleDmtJhMandatory(true);
        }
    }
    else{
        // Unique unchecked - enable General, disable dropdowns
        jQuery('#chkEtcmUniqueposition').val('N');
        if(keyid.length == 0){
            jQuery('#chkEtcmGeneral').prop('checked', true);
            jQuery('#chkEtcmGeneral').val('Y');
        }
        disableField("frmNewTraCal","cmbEtcuRoleDmt");
        disableField("frmNewTraCal","cmbEtcuRoleJh");
        disableField("frmNewTraCal","cmbEtcuRoleKeyid");
        disableField("frmNewTraCal","btnUPSave");
        toggleDmtJhMandatory(false);
    }
});



  
  jQuery("#chkEtcmMSD").click(function(){
	 if(jQuery("#chkEtcmMSD").is(':checked') == true){
		 jQuery("#chkEtcmMSD").val("Y");
	 }
	 else{
		 jQuery("#chkEtcmMSD").val("N");
	 } 
  });
 
jQuery('#chkUniquePosition').click(function(){
	
		if (jQuery('#chkUniquePosition').is(':checked')) {
			disableField("frmTrngCalendar","cmbEtcuRoleKeyid");
			setFieldValue("cmbEtcuRoleKeyid", "ALL");
			jQuery('#cmbEtcuRoleKeyid').combobox('setText','ALL');
			
		}
		
		else {
			
			clearField("cmbEtcuRoleKeyid");
			enableFields("cmbEtcuRoleKeyid");
			
		}
		
	});


      jQuery('#chkEtcmMaterialsReadyY').click(function(){
		if(jQuery('#chkEtcmMaterialsReadyY').is(':checked') == true){
			jQuery('#chkEtcmMaterialsReadyN').attr('checked',false);	
		}
		else{
			
			jQuery('#chkEtcmMaterialsReadyN').attr('checked',true);
		}
});

     jQuery('#chkEtcmMaterialsReadyN').click(function(){
	if(jQuery('#chkEtcmMaterialsReadyN').is(':checked') == true){
		jQuery('#chkEtcmMaterialsReadyY').attr('checked',false);	
	}
	else{
		
		jQuery('#chkEtcmMaterialsReadyY').attr('checked',true);
	}
	
});
     
   
     
     jQuery('#chkEtcmMarksBasedY').click(function(){
 		if(jQuery('#chkEtcmMarksBasedY').is(':checked') == true){
 			jQuery('#chkEtcmMarksBasedN').attr('checked',false);	
 		}
 		else{
 			
 			jQuery('#chkEtcmMarksBasedN').attr('checked',true);
 		}
 });

      jQuery('#chkEtcmMarksBasedN').click(function(){
 	if(jQuery('#chkEtcmMarksBasedN').is(':checked') == true){
 		jQuery('#chkEtcmMarksBasedY').attr('checked',false);	
 	}
 	else{
 		
 		jQuery('#chkEtcmMarksBasedY').attr('checked',true);
 	}
 	
 });
      
      
      
     
       jQuery('#chkEtcmAssessmentReqY').click(function(){
   		if(jQuery('#chkEtcmAssessmentReqY').is(':checked') == true){
   			jQuery('#chkEtcmAssessmentReqN').attr('checked',false);
   			jQuery("#chkEtcmMarksBasedY").attr('disabled',false);
   			jQuery("#chkEtcmMarksBasedN").attr('disabled',false);
   		}
   		else{
   			
   			jQuery('#chkEtcmAssessmentReqN').attr('checked',true);
   			jQuery("#chkEtcmMarksBasedY").attr('disabled',true);
   			jQuery("#chkEtcmMarksBasedN").attr('disabled',true);
   		}
   });
       
      

     
     jQuery('#chkEtcmAssessmentReqN').click(function(){
 		if(jQuery('#chkEtcmAssessmentReqN').is(':checked') == true){
 			jQuery('#chkEtcmAssessmentReqY').attr('checked',false);
 			jQuery("#chkEtcmMarksBasedY").attr('disabled',true);
   			jQuery("#chkEtcmMarksBasedN").attr('disabled',true);
 		}
 		else{
 			
 			jQuery('#chkEtcmAssessmentReqN').attr('checked',true);
 			jQuery("#chkEtcmMarksBasedY").attr('disabled',false);
 		   jQuery("#chkEtcmMarksBasedN").attr('disabled',false);
 		}
 });

      jQuery('#chkProgAssessmentReqN').click(function(){
 	if(jQuery('#chkProgAssessmentReqN').is(':checked') == true){
 		jQuery('#chkProgAssessmentReqY').attr('checked',false);	
 		jQuery('#chkEtcmMarksBasedY').attr('checked',false);
 	}
 	else{
 		
 		jQuery('#chkProgAssessmentReqY').attr('checked',true);
 		jQuery("#chkEtcmMarksBasedY").attr('disabled',false);
	   jQuery("#chkEtcmMarksBasedN").attr('disabled',false);
 	}
 	
 });
     
	       jQuery('#chkUniquePosition').click(function(){
			if (jQuery('#chkUniquePosition').is(':checked')){
				disableField("frmNewTraCal","cmbEtcuRoleKeyid");
				setFieldValue("cmbRtlkRtalKeyid", "ALL");
			    jQuery('#cmbRtlkRtalKeyid').combobox('setText','ALL');
			}
			else{
				clearField("cmbRtlkRtalKeyid");
			    enableFields("cmbRtlkRtalKeyid");
			}
			
		});

      jQuery('#EtcmUniqueposition').click(function(){
    	 if(jQuery('#EtcmUniqueposition').is(':checked')==true){
    		 readOnlyFields('chkUniquePosition');
  			 readOnlyFields('cmbEtcuRoleKeyid');
    	 }
    	 else{
    		enableFields('chkUniquePosition');
  			enableFields('cmbEtcuRoleKeyid');
  			enableUIButton("btnUPSave");
    	 }
    	  
      }); 
   
     /*  jQuery("#chkEtcmUniqueposition").click(function(){
    		 if(jQuery("#chkEtcmUniqueposition").is(':checked')==true) {
    			 jQuery("#chkEtcmUniqueposition").val("Y");
    		 }
    		 else{
    			 jQuery("#chkEtcmUniqueposition").val("N");
    		 }
    		  
    	  }); */
     
     jQuery('#btnSessionsave').click(function(){
			          var date = getFieldValue("dteSessiondate");	
			          var fromTime= getFieldValue("spnsessionFromTime");
			         // alert(fromTime);
			       	  var tillTime= getFieldValue("spnsessionTillTime");
			       	// alert(tillTime)
			       	  var keyid=jQuery("#txtEtcmKeyid").val();
			       	  var sessionid=jQuery("#hdnSessionId").val();
			       	  
			       	  if(keyid.length==0)
			       		  {
			       		   popupCommonErroMsg("Please save Training Calendar");
			       		   return false;
			       		  }
			       	  
			       	  else{
			             
			       		if(fromTime==tillTime)
			       		 {
			       			//alert("inside");
			       		  popupCommonErrorMsg("Please select correct from time and till time");
			       		  return false;
			       		 }
			       		else{
			       		//	alert("inside1");
			       		  processAjaxCalls('chkSessionDate.ntrc','&keyid='+keyid+"&sedte="+date+"&frmtme="+fromTime+"&totme="+tillTime+"&sesid="+sessionid,'chkSessionDate_onsuccessCallBack','chkSessionDate_onerrorCallBack');
			       		}
			       	  }
		});
     
function frmNewTraCal_beforeDelete(){
	 var TraKeyid = jQuery("#txtEtcmKeyid").val();
	var r=confirm("Are You Sure To Delete");
	if(r){
		return true;
	}
	else
	{
	   return false;
	}
}
 
function frmNewTraCal_deleteSuccessCallback(result){
	 var keyid=result.successData.TraCalId;
		var tkeyid=result.successData.Keyid;
		if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined"){
		      alert(result.successData.msg);
	   		  navigateToPrevForm();	 	 
		   }	
		else if(tkeyid!=null && tkeyid !=" " && tkeyid !="" && tkeyid !="undefined"){
			   alert(result.successData.msg);
			}
 }
 function chkSessionDate_onsuccessCallBack(result)
 {
	 var keyid=result.keyid;
	 
	 var sescnt=result.sessioncnt;
	
	 var sessiondte=result.sessiondate;
	
	 var frmtime=result.frmtime;
	
	 var totime=result.totime;
	
	 var sessionid=result.sessionid;
	 if(sescnt==0)
		{ 
	 
	 var batchMonth = sessiondte;
	
   	 var Duration=jQuery('#txtEtcmMaxDuration').val();
   	var progDuration=60*Duration;
    batchMonth =  batchMonth.replace('-',' ');
   //alert("batchMonth"+batchMonth);
   	batchMonth =  batchMonth.replace('-',',');
   // alert("batchMonth"+batchMonth);
   //13 Apr-2021  13 Apr,2021  618328220000  618321020000 7200000
   	 var difference = (Date.parse(batchMonth+" "+totime) - Date.parse(batchMonth+" "+frmtime)) / 60000;
   	 if(parseInt(progDuration)<parseInt(difference))
    	 {
    	 popupCommonErrorMsg("Please select correct from time and till time");
   		 return false;
    	 
    	 }
   	 
		if( sessiondte.trim().length>0)
			saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?q=2&Savetype=Session&sesId="+sessionid);	
		}
		else{
			 popupCommonErrorMsg("Already Available ");
			 fillWithCurrentDate("sessionFromTime");
	 		 fillWithCurrentDate("sessionTillTime");
	 		 fillWithCurrentDate('spnsessionFromTime');
	 		 fillWithCurrentDate('spnsessionTillTime');
	 		 return false;
		}
	 
 }
  // Vignesh 13Mar2026
     
// function frmNewTraCal_FuntLocHierarchy_SuccessCallBack(keyIds)
// {
	

// 		var flid = keyIds.flId;
// 		var locnid=keyIds.locnId;
// 		var sectid=keyIds.sectId;
// 		var cellid=keyIds.cellId;
// 		//alert(flid+"flid");
// 		var cmbTopicId =getFieldValue("cmbEtcmTopicid");
// 		 var keyid=jQuery("#txtEtcmKeyid").val();
// 		 if(keyid.length==0){
// 			 reloadCombo("frmNewTraCal","cmbEtcmTopicid","topic_fillcombo.ntrc?&flId="+flid);
// 		 }
// 	 	var ds="?&topicId="+cmbTopicId+"&flid="+flid;
// 		reloadCombo("frmNewTraCal","cmbEtcuRoleKeyid","roleMst.commonFilter"+ds );
// 		reloadCombo("frmNewTraCal","cmbEtcmVenue","entVenueCombo.entbatch?&flId="+flid);
// 	    reloadCombo("frmNewTraCal","cmbEtcfFacultyId","facultyCombo.tcl?&sectId="+sectid+"&others="+"N"+"&locnid="+locnid);
// 	    clearField("cmbEtcuRoleDmt");
// 		clearField("cmbEtcuRoleJh");
// 		reloadCombo("frmNewTraCal","cmbEtcuRoleDmt","sectionCombo.commonFilter?locnId="+locnid);
// 		reloadCombo("frmNewTraCal","cmbEtcuRoleJh","cellCombo.commonFilter?locnId=" +locnid);
// 	    setFunctionalLocWidth('frmNewTraCal','650px');
// }
  
 function frmNewTraCal_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var flid = keyIds.flId;
	var locnid=keyIds.locnId;
	var sectid=keyIds.sectId;
	var cellid=keyIds.cellId;
	
	// Store for "Other" checkbox use
	jQuery("#hdnFacultySectId").val(sectid);
	jQuery("#hdnFacultyLocnId").val(locnid);
	// Reset Other checkbox when functional location changes
	jQuery("#chkEtcfOther").prop('checked', false).val("N");
	
	var cmbTopicId =getFieldValue("cmbEtcmTopicid");
	 var keyid=jQuery("#txtEtcmKeyid").val();
	 if(keyid.length==0){
		 reloadCombo("frmNewTraCal","cmbEtcmTopicid","topic_fillcombo.ntrc?&flId="+flid);
	 }
 	var ds="?&topicId="+cmbTopicId+"&flid="+flid;
	reloadCombo("frmNewTraCal","cmbEtcuRoleKeyid","roleMst.commonFilter"+ds );
	reloadCombo("frmNewTraCal","cmbEtcmVenue","entVenueCombo.entbatch?&flId="+flid);
    reloadCombo("frmNewTraCal","cmbEtcfFacultyId","facultyCombo.tcl?&sectId="+sectid+"&others=N&locnid="+locnid);
    clearField("cmbEtcuRoleDmt");
	clearField("cmbEtcuRoleJh");
	reloadCombo("frmNewTraCal","cmbEtcuRoleDmt","sectionCombo.commonFilter?locnId="+locnid);
	reloadCombo("frmNewTraCal","cmbEtcuRoleJh","cellCombo.commonFilter?locnId=" +locnid);
    setFunctionalLocWidth('frmNewTraCal','650px');
}

// "Other" faculty checkbox handler   // Vignesh 13Mar2026
jQuery("#chkEtcfOther").click(function(){
    var sectid = jQuery("#hdnFacultySectId").val();
    var locnid = jQuery("#hdnFacultyLocnId").val();
    
    // Fallback: if hidden fields empty, read from form hidden inputs
    if(!sectid || sectid.trim().length == 0){
        locnid = jQuery("#frmNewTraCal input[id='location']").val();
    }
    
    if(jQuery("#chkEtcfOther").is(':checked')){
        jQuery("#chkEtcfOther").val("Y");
        reloadCombo("frmNewTraCal","cmbEtcfFacultyId","facultyCombo.tcl?&sectId="+sectid+"&others=Y&locnid="+locnid);
    } else {
        jQuery("#chkEtcfOther").val("N");
        reloadCombo("frmNewTraCal","cmbEtcfFacultyId","facultyCombo.tcl?&sectId="+sectid+"&others=N&locnid="+locnid);
    }
});


jQuery("#btnFacultySave").click(function(){
    var MasterKeyid=jQuery("#txtEtcmKeyid").val();
	var facultyId=jQuery('#cmbEtcfFacultyId').combobox('getValue');
	if(facultyId.length==0 || facultyId==null){
		alert("Pls Add The Faculty!");
		return false;
	}
	if (MasterKeyid==null || MasterKeyid==' ' || MasterKeyid=='') {
		var ds = +"&Facultyid="+facultyId;
		//alert("ds"+ds);
		if(facultyId.trim().length>0 )
			{
			saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?ds="+ds);
		}
	}
	else{
		if(MasterKeyid!=null&&facultyId!=null)
			{
			  processAjaxCalls('FacultyCheck.ntrc','&keyid='+MasterKeyid+"&faclid="+facultyId,'FacultyCheck_onsuccessCallBack','FacultyCheck_onerrorCallBack');
			}
	}
});

function FacultyCheck_onsuccessCallBack(result)
{
	//alert("success");
	var keyid=result.keyid;
	var facultyid=result.fcltyid;
	var facultycnt=result.fcltycnt;
	
	if(facultycnt==0)
		{
		var ds = "MasterKeyid="+keyid+"&Facultyid="+facultyid+"&Savetype=Faculty";
		if(facultyid.trim().length>0)
			saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?ds="+ds);
		}
	else{
		popupCommonErrorMsg("This Faculty is Already Selected for this Training Calendar");
		jQuery('#cmbEtcfFacultyId').combobox('clear');
		return false;
	}
	
}

jQuery("#btnFacultyAdd").click(function(){
	openMasterForm('loadmst_grid.gnms?q=2&menuCaption=Faculty&menuName=MNUETFACULTY&isMMC=Y&loadFormArg=Faculty%3F',frmMode.create,'frmNewTraCal','Faculty','mstFrm');
});


 jQuery("#btnTopicsave").click(function(){
	 //function LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname){ /*** Classname added By S.SugunaDevi***/
//LoadPopUp("divUniquePosAdd","EmployeeUniqueAdd_input.ntrc?q=2&flid="+flid+"&keyid="+keyid+"&locnId="+locnId+"&uniq="+uniq,true,"1170px","590px","5px","5%", "multiSelectOk_Callback","Add Employee Link");

LoadPopUp("divTopic","TopicEntryMaster_input.topmst",true,"90%","90%","2%","10px","defectSeverity_Callback","Topic Master","",true,true);
	 
 });
function btnEmpFormater(id, options, rowObject)
{					
	 	var rowId = options.rowId;
	 	var gridId = options.gid;
	 	return '<input type="button" class="easyui-button" id="addEmployee_'+gridId+'_'+rowId+'" value="..." onclick="addBachEmployee(\''+rowId + '\',\''+gridId + '\');"/>';
	 
}

function AssDtlFileManger_formatter(cellValue, options, rowObject){
	var gridId = options.gid;
	var rowId= options.rowId;
	
	return '<input type="button" class="easyui-button" id="btnFileManger_'+rowId+'"  name="btnFileManger_'+rowId+'" value="..." onclick="addFileManager(\''+ gridId  + '\',\''+ rowId+'\');"; style="text-align:center;width:50px;height:20px"/>';
}

jQuery("#btnUniqueAdd").click(function(){
	var flid = jQuery("#frmNewTraCal input[id='flid']").val();
	 var locnId = jQuery("#frmNewTraCal input[id='location']").val();
	var keyid=jQuery("#txtEtcmKeyid").val();
	var completed=jQuery("#chkEtcmChkCompleted").val();
	var uniq=jQuery("#chkEtcmUniqueposition").is(":checked");
	if(keyid.length!=0 && keyid!=null)
		{
		LoadPopUp("divUniquePosAdd","EmployeeUniqueAdd_input.ntrc?q=2&flid="+flid+"&keyid="+keyid+"&locnId="+locnId+"&uniq="+uniq,true,"1170px","590px","5px","5%", "multiSelectOk_Callback","Add Employee Link");
		   }
	else{
		popupCommonErrorMsg("Please Save Training Calendar For Employee Add!!!");
	}
});


function divUniquePosAdd_afterClose()
{
	jQuery("#selectedRoleGrd").trigger("reloadGrid");
	
}


function upSave(){
	   var Upid = getFieldValue("cmbEtcuRoleKeyid");
	   var updmt=getFieldValue("cmbEtcuRoleDmt");
	  // alert("updmt"+updmt);
	   var upJh=getFieldValue("cmbEtcuRoleJh");
	  // alert("upJh"+upJh);
	   var keyid=jQuery("#txtEtcmKeyid").val();
	   var chkuni=jQuery("#chkEtcmUniqueposition").is(":checked");	   
	   processAjaxCalls('chkUniQupostion.ntrc','&keyid='+keyid+"&Upid="+Upid+"&chkuni="+chkuni,'chkUniQupostion_onsuccessCallBack','chkUniQupostion_onerrorCallBack');
}

function chkUniQupostion_onsuccessCallBack(result)
{
    var allUnique="N";
	var mstkeyid=result.keyid;
	var roleid=result.Upid;
	var uniqkeyid=result.uniqukeyid;
	var updmt=getFieldValue("cmbEtcuRoleDmt");
	var upJh=getFieldValue("cmbEtcuRoleJh");
	var uniqcnt=result.uniquecnt;
	 var ds="";
	 if(uniqcnt==0)
		 {
	    ds= "updmt="+updmt+"&upJh="+upJh+"&uniqid="+uniqkeyid;
	   if(roleid.trim().length>0){
			saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?ds="+ds);
			return false;
		}else{
			alert("Select Unique Position");
			return false;
		}
		 }
	/* else{
		 popupCommonErrorMsg("Already this Unique position is seleected for Training");
		 return false;
	 }*/
	    	 	  
	    	   if(mstkeyid.trim().length>0){
	    			 var r = confirm("Data changed, Do you want to Proceed");
	    			 if(r){
	    				 if(jQuery('#chkUniquePosition').is(':checked'))
	    					 allUnique = 'Y';  
	    				     // alert("if"+allUnique);//ds=+dsallUnique="+allUnique+
	    				   saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?ds="+ds+"&allUnique="+allUnique);
	    				// return "&allUniquePosition="+allUnique;
	    			 }else{
	    				 return false;
	    			 }  
		 }
}


/*function chkUniQupostion_onsuccessCallBack(result)
{
	
	var mstkeyid=result.keyid;
	var roleid=result.Upid;
	var uniqkeyid=result.uniqukeyid;
	 var updmt=getFieldValue("cmbEtcuRoleDmt");
	 var upJh=getFieldValue("cmbEtcuRoleJh");
	 var uniqcnt=result.uniquecnt;
	 if(uniqcnt==0)
		 {
	  var ds= "updmt="+updmt+"&upJh="+upJh+"&uniqid="+uniqkeyid;
	   if(roleid.trim().length>0){
			saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?ds="+ds);	
		}else{
			alert("Select Unique Position");
		}
		 }else{
			 popupCommonErrorMsg("Already this Unique position is seleected for Training");
			 return false;
		 }
}*/



function BtnFormatterDelete(id, options, rowObject)
{					
	var rowId = options.rowId;
	var gridId = options.gid;

	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
}

function deleterec(rowid,gridId){
	var mode=jQuery("#mode").val();
	if(mode=="view"){
		alert("View Mode Not Possible To Delete");
		return false;
	}
	
	var keyId;
	// --------------Vignesh added for delete 26Nov2025 ---//
	
	if("SesGrid"==gridId)
		keyId = getGridCell(gridId,rowid,'etcs_keyid');
	
	else if("Gengrid"==gridId)
		keyId = getGridCell(gridId,rowid,'etcf_keyid');
	else if("UniqueGrid"==gridId)
		keyId = getGridCell(gridId,rowid,'etcu_keyid');

	else
	    keyId = getGridCell(gridId,rowid,'etcs_keyid');
	  //  alert("alert inside new training calendar jsp " + keyId );
	    
	var session
	// --------------Vignesh added for delete 26Nov2025 ---//
	  if (gridId === "SesGrid") {
	        session = getGridCell(gridId, rowid, 'etcs_name');
	    } else {
	        session = getGridCell(gridId, rowid, 'etcs_name');
	    }
	
	if(session=="Session1")
		{
		 popupCommonErrorMsg("First session unable to delete,please modify session time");
		 return false;
		}
	 var TraKeyid = jQuery("#txtEtcmKeyid").val();
	 var r = confirm("Are You Sure To Delete?");
	  if(r==true){
	processAjaxCalls('deleteDetailRecord.ntrc','&keyid='+keyId+'&gridId='+gridId+"&TraKeyid="+TraKeyid,'deleteDetail_onsuccessCallBack','deleteDetail_onerrorCallBack');
	  }
	  else{
		  return false;
		  } 
	  }

function deleteDetail_onsuccessCallBack(result){ 
		  alert(result.msg);
	      jQuery("#"+result.gridid).trigger("reloadGrid");  
	}
  
function frmNewTraCal_beforeSubmit(){
	
	var title=jQuery("#hdntitle").val();
//	  alert("title"   +  title);
	var iscompleted=jQuery("#chkEtcmChkCompleted").val(); 
	
	// alert("iscompleted"   +  iscompleted);
	 
    var TrainingIdentified=jQuery("#cmbEtcmFunction").combobox('getValue');
//	 alert("TrainingIdentified"   +  TrainingIdentified);
	 
  //   var Rating=jQuery("#cmbEtcmRating").combobox('getValue');
  //   alert(Rating);
     
     if(TrainingIdentified.length <1 ||TrainingIdentified==null){
   	     popupCommonErrorMsg("select Training Identified By");
   	     return false;
   	     
   	  }
		if(title=="Employee Attendance"){
         var gridval=getGridSelectArray('EmpAttenGrid');  
       //  alert("gridval"   +  gridval);
           var cut=jQuery("#txtEtcaCutOff").val();
           var marks=jQuery("#txtEtcaMaxMarks").val();
           var type=jQuery("#cboEtcaType").val();
           var keyid=jQuery("#txtEtcmKeyid").val();
           var flid = jQuery("#frmNewTraCal input[id='flid']").val();
           var topicid=jQuery("#cmbEtcmTopicid").combobox('getValue');
           var locnId = jQuery("#frmNewTraCal input[id='location']").val();           
           var chkcompl=jQuery("#chkEtcmChkCompleted").is(':checked');
           if(chkcompl==true)
        	   {
        	          var completedby=jQuery("#cmbEtcmCompletedBy").combobox('getValue');
        	         // alert(completedby);
        	       //   var Rating=jQuery("#cmbEtcmRating").combobox('getValue');
        	         // alert(Rating);
        	          if(completedby.length <2 ||completedby==null){
        	        	     popupCommonErrorMsg("select completed by Employee");
        	        	     return false;
        	        	  }
        	         /*  if(Rating.length<2||Rating==null){
        	        	  popupCommonErrorMsg("select the Rating in FeedBack Tab");
        	        	  return false;
        	          } */
        	   }
		    var gridData  = '&paramJsonArrConvert='+gridval+"&CutOff="+cut+"&max="+marks+"&typ="+type+"&kid="+keyid+"&flid="+flid+"&tid="+topicid+"&lid="+locnId;
	//	    alert("gridData"   +  gridData);
		    if(gridval.trim().length>0)	
				return gridData;
		    /* else 
		    	saveForm("frmNewTraCal","NewTrainingCalender_save.ntrc?"); */
		    //return false;
		  }
		  
	 // else{
	 else  if(title!="Employee Attendance")
		  {
	//alert("! employee");
		 var mstKey = jQuery("#txtEtcmKeyid").val();
	var ms=jQuery("txtEtcsKeyid").val();
	var facId = getFieldValue("cmbEtcfFacultyId");
	if(facId.trim().length > 0){
		 jQuery('#txtIsFacultyLink').val('Y');
	}else{
		 jQuery('#txtIsFacultyLink').val(' ');
	}
	  var upid = getFieldValue("cmbEtcuRoleKeyid");
	  if(upid.trim().length > 0){
			 jQuery('#txtIsUniquePosition').val('Y');
		 }
		 else{
			jQuery('#txtIsUniquePosition').val(' ');
		 }
	var locationId =jQuery("#frmNewTraCal input[id='locnId']").val();	
	
	if(locationId.trim().length<=0){
	 popupCommonErrorMsg("Select The Location"); 
	    return false;
	}
	}
	 
	 
	 
          if(chkcompl==true){
       	      // alert("true"); 
        	   var completedby=jQuery("#cmbEtcmCompletedBy").combobox('getValue');
       	    //   alert("completedby"+completedby);
       	       var Rating=jQuery("#cmbEtcmRating").combobox('getValue');
       	       var AnchoredBy=jQuery("#cmbEtcmAnchoredby").combobox("getValue");
       	   //    alert("AnchoredBy"+AnchoredBy);
       	      // alert("Rating"+Rating+"length"+Rating.length);
       	          if(completedby.length < 2 ||completedby==null || Rating==null){
       	        	     popupCommonErrorMsg("select completed by Employee");
       	        	     return false;
       	        	  }  
       	 if(AnchoredBy=="Corp.HR" || AnchoredBy=="DHR" || AnchoredBy=="Unit HR"){
       	     if(Rating.length<2 ||Rating==null){
	        	  popupCommonErrorMsg("select the Rating");
	        	  return false;
	          }        
          }
       	 
       	     /*   if(Rating.length<2 ||Rating==null){
 	        	  popupCommonErrorMsg("select the Rating");
 	        	  return false;
 	          } */        
       	   }
          var format =/[`!@#$%^&*()_+\-=\[\]{};'':"\\|,.<>\/?~]/;
          var txtEtcmComments=jQuery('#txtEtcmComments').val();


          if(format.test(txtEtcmComments)==true){
          	alert("Special Characters are Not allowed !");
          	return false;
          }
    
	  }
		 
	 

function employeeAttendanceSave_successCallBack(result) {
	if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
		popupCommonErrorMsg(result.tpmException);
	else
		alert(result.successData.msg);
}
   
    

function frmNewTraCal_successsCallback(result){ 	 
	 var TraKeyid=result.TraCalId;
	 var attendid=result.TraCalAttId;
	 var title=jQuery("#hdntitle").val();
	 jQuery("#hdntitle").val(title);
	 var mode=result.savemode;
	 var txtkeyid=jQuery("#txtEtcmKeyid").val();
	 var  filterString="&TraKeyid="+TraKeyid;
	 jQuery("#txtEtcmKeyid").val(TraKeyid);
 	 var createDatetime=result.TraCreateTime;
 	 jQuery("#txtEtcmCreatedDateTime").val(createDatetime);
 	 if(attendid!=0)
 		 {

 		 jQuery('#chkEtcmMarksBasedY').attr('disabled',true);
 		 jQuery('#chkEtcmMarksBasedN').attr('disabled',true);
 		jQuery('#chkEtcmAssessmentReqY').attr('disabled', true); 
 		jQuery('#chkEtcmAssessmentReqN').attr('disabled', true); 
 		var marks=jQuery('#chkEtcmMarksBasedY').is(':checked');
 		var assess=jQuery('#chkEtcmAssessmentReqY').is(':checked');
 		if(marks==true)
 			{
 			  jQuery("#hdnmark").val('Y');
 			}
 		else{
 			 jQuery("#hdnmark").val('N');
 		}
 		
 		if(assess==true)
			{
 			 jQuery("#hdnassessment").val('Y');
			}
		else{
			jQuery("#hdnassessment").val('N');
		}
 		
 		}
 	 
 	 if(TraKeyid.length!=0)
 		 {
 		 fillWithCurrentDate("sessionFromTime");
 		 fillWithCurrentDate("sessionTillTime");
 		 fillWithCurrentDate('spnsessionFromTime');
 		 fillWithCurrentDate('spnsessionTillTime');
 		 } 
 	 
 	 /* var uniqpos=jQuery("#chkEtcmUniqueposition").is(":checked");
 	 if(uniqpos==false)
 		 {
 		 disableField("frmNewTraCal","chkUniquePosition");
		 disableField("frmNewTraCal","cmbEtcuRoleKeyid");
		 disableField("frmNewTraCal","btnUPSave");
 		 }
 	 else{
 		enableFields("chkUniquePosition");
 		enableFields("cmbEtcuRoleKeyid");
 		enableFields("btnUPSave");
                jQuery('#btnUPSave').prop('disabled', false);
 	 } */
 	var uniqpos=jQuery("#chkEtcmUniqueposition").is(":checked");
	 if(uniqpos==false)
		 {
		 disableField("frmNewTraCal","chkUniquePosition");
		 disableField("frmNewTraCal","cmbEtcuRoleKeyid");
		 disableField("frmNewTraCal","cmbEtcuRoleDmt");
		 disableField("frmNewTraCal","cmbEtcuRoleJh");
		 disableField("frmNewTraCal","btnUPSave");
		 }
	 else{
		enableFields("chkUniquePosition");
		enableFields("cmbEtcuRoleKeyid");
		enableFields("cmbEtcuRoleDmt");
		enableFields("cmbEtcuRoleJh");
		enableFields("btnUPSave");
       jQuery('#btnUPSave').prop('disabled', false);
	 }
	 //mano end

 	 var TrgCalDate=jQuery("#dteEtcmCalendarDate").combobox('getValue');     
 	 var facultySaved = jQuery('#txtIsFacultyLink').val();
 	 var UniquePosSaved= jQuery('#txtIsUniquePosition').val();
 	 var SessionSaved=jQuery("#txtIsSession").val();
 	 
	 if( facultySaved.trim() == "Y")
	 {
		  clearField('cmbEtcfFacultyId');
		  clearField('txtIsFacultyLink');
		  processGridnew("Faculty_input.ntrc",filterString,"Gengrid","pagerid","","facultydoubleclickGrid","",""); 

	 }
	 if(UniquePosSaved.trim() == "Y"){
		  clearField('cmbEtcuRoleKeyid');
		  clearField('txtIsUniquePosition');
		  jQuery("#UniqueGrid").trigger("reloadGrid");	
	 }
	 
	  if(mode.trim()=="Session"){
		  fillWithCurrentDate('dteSessiondate');
		  clearField('txtIsSession');
		  jQuery("#hdnSessionId").val('');
          jQuery("#SesGrid").trigger("reloadGrid");

	  }
	  //vignesh 17Mar2026
// 	  if(mode.trim()=="Employee Attendance"){
// 		  disableField("frmNewTraCal","txtEtcaCutOff");
// 		  disableField("frmNewTraCal","txtEtcaMaxMarks");
// 		 jQuery("#EmpAttenGrid").trigger("reloadGrid");
	  
// 	  }
	  if(mode.trim()=="Employee Attendance"){
		    // Sync visible field values into hidden fields after first save in create mode
		    // So subsequent tab switches read correct values from hdnmaxmarks/hdncutoff
		    jQuery("#hdnmaxmarks").val(jQuery("#txtEtcaMaxMarks").val());
		    jQuery("#hdncutoff").val(jQuery("#txtEtcaCutOff").val());
		    jQuery("#hdntype").val(jQuery("#cboEtcaType").val());
		    jQuery("#hdnEmpattnd").val("1"); // mark as saved so disable logic triggers on next tab switch
		    disableField("frmNewTraCal","txtEtcaCutOff");
		    disableField("frmNewTraCal","txtEtcaMaxMarks");
		    jQuery("#chkSelectAllEmp").prop("checked",false);
		    jQuery("#EmpAttenGrid").trigger("reloadGrid");
		}
	  
 if(jQuery("#chkEtcmChkCompleted").is(":checked")==true){
                navigateToPrevForm();
                return false;
             }
}
function viewGrid(filterString){
       processGridnew("NewuniquePositionLink_input.ntrc",filterString, "UniqueGrid", "Uniquepagerid","","uniqPosdoubleclickGrid", "", "");
	   processGridnew("Faculty_input.ntrc",filterString,"Gengrid","pagerid","","facultydoubleclickGrid","","");
	   processGridnew("session_input.ntrc",filterString,"SesGrid","Sespagerid","","sessiondoubleclickGrid","","");
     }
jQuery("#chkSelectAllEmp").click(function(){
	   show_winMask(1);
       fnSelectAllEmp();
	   show_winMask(0);
});
//----------------------------------- Vignesh 17Mar2026 ------------------------------------------//
// function  fnSelectAllEmp(){
// 		var row = jQuery("#EmpAttenGrid").jqGrid('getDataIDs');

// 		for(var i=0;i<row.length;i++)
// 		 {
// 			if(jQuery("#chkSelectAllEmp").is(':checked')== true){
// 				var etcakeyid = jQuery("#EmpAttenGrid").jqGrid('getCell',row[i],"hdnEtcaKeyid");
// 			//	alert(etcakeyid);
// 				if(etcakeyid.length>0){
// 					var jqGridId="EmpAttenGrid";
// 					 //jQuery("#dteEtcaAddDate_"+jqGridId+"_"+row[i]).attr('readonly','readonly'); 
// 					  jQuery("#cmbEtcaPresentAbsent_"+jqGridId+"_"+row).attr('disabled',true);
// 					}
// 				jQuery('#EmpAttenGrid').setSelection(row[i], true);
// 			    jQuery('input:checkbox[id=jqg_EmpAttenGrid_'+row[i]+']').attr('checked',true);
// 			    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'selctVal','1');
// 			 }
// 			 else {
// 				    jQuery('#EmpAttenGrid').setSelection(row[i], false);
// 				    jQuery('input:checkbox[id=jqg_EmpAttenGrid_'+row[i]+']').attr('checked',false);
// 				    jQuery("#EmpAttenGrid").jqGrid('setCell',row[i],'selctVal','0');
//                  //   alert("else");
// 			 }
// 		 }	
// 	}
//FIXED fnSelectAllEmp
// FIXED fnSelectAllEmp
function fnSelectAllEmp() {
    var row       = jQuery("#EmpAttenGrid").jqGrid('getDataIDs');
    var jqGridId  = "EmpAttenGrid";
    var isChecked = jQuery("#chkSelectAllEmp").is(':checked');

    _empAttenSelectAll = true; // suppress EmpAttenGrid_selectRow during bulk loop

    for (var i = 0; i < row.length; i++) {
        if (isChecked) {
            var etcakeyid = jQuery("#EmpAttenGrid").jqGrid('getCell', row[i], "hdnEtcaKeyid");
            if (etcakeyid.length > 0) {
                // BUGFIX: was row (entire array) — now correctly row[i]
                jQuery("#cmbEtcaPresentAbsent_" + jqGridId + "_" + row[i]).attr('disabled', true);
            }
            jQuery('#EmpAttenGrid').setSelection(row[i], true);
            jQuery('input:checkbox[id=jqg_EmpAttenGrid_' + row[i] + ']').attr('checked', true);
            jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'selctVal', '1');
        } else {
            jQuery('#EmpAttenGrid').setSelection(row[i], false);
            jQuery('input:checkbox[id=jqg_EmpAttenGrid_' + row[i] + ']').attr('checked', false);
            jQuery("#EmpAttenGrid").jqGrid('setCell', row[i], 'selctVal', '0');
        }
    }

    _empAttenSelectAll = false;

    // After bulk select: apply readonly/score state to all rows (fixes issues 1 & 3)
    if (isChecked) {
        for (var j = 0; j < row.length; j++) {
            applyEmpAttenRowState(row[j]);
        }
    }
 
}

//----------------------------------- Vignesh 17Mar2026 ------------------------------------------//


function selectedRoleGrd_selectRow(row){
    var EmpmKeyid=jQuery("#selectedRoleGrd").jqGrid('getCell',row,"txtetcekeyid");
   // alert(EmpmKeyid);
    var mode=jQuery("#mode").val();
    if(mode=="view"){
		alert("View Mode Not Possible To Delete");
		return false;
	}
    if(EmpmKeyid==null || EmpmKeyid=="" || EmpmKeyid=='')
      return false;
    else{
    	var r=confirm("Are you Sure Want to Delete?");
    	if(r){
    		alert("Data Deleted Successfully");
    		processAjaxCalls("entAddEmployee_delete.ntrc?&EmpmKeyid="+EmpmKeyid,"","deleteSuccessCallbacks","errorCallBack");
    		jQuery("#selectedRoleGrd").trigger("reloadGrid");
    	}
    	else{
    		return false;
    	}
    }
}

function frmNewTraCal_deleteSuccessCallbacks(reslut){
	alert(result.successData.msg);
	jQuery("#selectedRoleGrd").trigger("reloadGrid");	
}
	
/* 	function EmpChkBoxChk(rowId){
		jQuery("#selectedRoleGrd").jqGrid('setCell',rowId,'','');
		jQuery("#selectedRoleGrd").jqGrid('setCell',rowId,'selctVal','1');
	} */
	function EmpChkBoxUnChk(rowId){
		jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'txtetceEtcmKeyid'," ");
		jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'selctVal','0');
	}
	
	function frmNewTraCalcmbEtcmTopicid_onSelect(record){
		//var topicid = getFieldValue('cmbEtcmTopicid','frmNewTraCal');
		var topicid = record.id;
	//	alert(topicid);
		processAjaxCalls("TopicDetails.ntrc","&topicid="+topicid,"TopicDetails_successcallback","TopicDetails_Errorcallback");
	}

	 function TopicDetails_successcallback(result){
			
			 var Type=result.trainingtype;
		//	 alert("type"+Type);
			 var Mode=result.trainingmode;
         if(Type!=null){
		 setFieldValue("cmbTopiRelatedto",Type);	
			 }
			 
	  if(Mode!=null){
		 
		 setFieldValue("cmbTopiTrainingmodeNew",Mode);	
		 
		// setFieldValue("cmbTopiTrainingmodeNew",Mode,"frmNewTraCal");
			 }
		     }
	 
function MUnique(){
 //var JHName=jQuery("#cmbEtcuRoleJh").combobox("getValue");
 //var DMTName=jQuery("#cmbEtcuRoleDmt").combobox("getValue");
 var Calendarflid = jQuery("#frmNewTraCal input[id='flid']").val();
// alert("Flid:"+Calendarflid);
// alert(DMTName);
 var CalendarId=jQuery("#txtEtcmKeyid").val();
// alert(CalendarId);
/* if(DMTName.length==0){
  alert("Select the JH");
  return false;
  
 }*/
 //alert("Flid"+flid);
 var sectionId = jQuery("#frmNewTraCal input[id='section']").val();
 var cellId = jQuery("#frmNewTraCal input[id='cell']").val();
 //alert(sectionId);
 //alert(cellId);
LoadPopUp("DivMultiUniquePosition","MultipleUniqueAdd_input.ntrc?q=2&Calendarflid="+Calendarflid+"&CalendarId="+CalendarId+"&sectionId="+sectionId+"&cellId="+cellId,true,"800px","490px","4px","4%", "multiSelectOk_Callback","Multi Unique Position Add");

}

/* function DivMultiUniquePosition_onClose(){
	jQuery("#UniqueGrid").trigger("reloadGrid");
} */


function toggleDmtJhMandatory(isMandatory) {
    if (isMandatory) {
        jQuery("#cmbEtcuRoleDmt").closest('td').find('label').addClass('mandatory-lbl');
        jQuery("#cmbEtcuRoleJh").closest('td').find('label').addClass('mandatory-lbl');
    } else {
        jQuery("#cmbEtcuRoleDmt").closest('td').find('label').removeClass('mandatory-lbl');
        jQuery("#cmbEtcuRoleJh").closest('td').find('label').removeClass('mandatory-lbl');
    }
}

</script>
<form id="frmNewTraCal">
	<div id="wrapper" style="width:80%;">
		<table>
			<tr>
				<td colspan="3">
					<div id="frmNewTraingCalFuntKeyIds">
					<input type="hidden" id="factory"  name="cmbTraFactoryid" value=""></input>
					<input type="hidden" id="section"  name="cmbTraSectionid" value=""></input>
					<input type="hidden" id="flid"     name="cmbEtcmFlid"  value="${requestScope.entTlTragcalmst.etcmFlid}"></input>
					<input type="hidden" id="location" name="cmbEtcmLocation" value="${requestScope.entTlTragcalmst.etcmLocation}"></input> 
					<input type="hidden" id="sbu"      name="cmbTraSbu" value=""></input> 
					<input type="hidden" id="pbu"      name="cmbTraPbu" value=""></input>
					<input type="hidden" id="section"  name="cmbEtcmDmt" value="${requestScope.entTlTragcalmst.etcmDmt}"></input>
					<input type="hidden" id="cell"     name="cmbEtcmJh" value="${requestScope.entTlTragcalmst.etcmJh}"></input>
					<input type="hidden" id="cell"     name="cmbTraCellid" value="">
<!-- 					added by vignesh 13Mar2026 -->
					<input type="hidden" id="hdnFacultySectId" name="hdnFacultySectId" value=""/>
<input type="hidden" id="hdnFacultyLocnId" name="hdnFacultyLocnId" value=""/>
					</input> 
					</div>
			 		<div id="NewTraingCalFunctionalLoc"   style="width:100%;" ></div>	
			      </tr>
			      <tr>
			      	<td>
			<div style="margin-left:700px;margin-top:-46px;"><label id="" class="mandatory-lbl">Anchored By</label></div>
				  <div style="margin-left:700px;"> 
				  <input id="cmbEtcmAnchoredby" name="cmbEtcmAnchoredby" class="easyui-combobox" style="width:160px;" value="${requestScope.entTlTragcalmst.etcmAnchoredby}">
				  </div>
			      </td>
			      <td>
				  <div style="margin-left:-270px; margin-top:-45px;"><label><b>Training No</b></label></div>
				  <div style="margin-left:-270px; margin-top:0%;">
				  <input id="txtEtcmKeyid" name="txtEtcmKeyid" type="text" class="easyui-text" readonly="readonly" style="width:130px; height:25px;text-align:center; font-weight:bold" value="${requestScope.entTlTragcalmst.etcmKeyid}"/> 
				  </div>
				  </td>
				  </tr>
				 
				 <tr>
				 <td> 
				<div style="margin-left:1020px; margin-top:-48px;">
				<label><b>Create Date</b></label></div> 
				<div style="margin-left:1020px;; margin-top:0%;">
				<input class="easyui-text" id="txtEtcmCreatedDateTime" name="txtEtcmCreatedDateTime" readonly="readonly" style="width: 106px; height: 25px;" value="${requestScope.entTlTragcalmst.etcmCreatedDateTime}"/>			       
	            </div>
	            </td>

	            </tr> 
	            
			   <tr>
			<td>
				<div style="margin-left:-0%;padding-top:9px;"><label id="" class="mandatory-lbl">Title/Topic</label></div>
				  <div style="margin-left:-0%;"> 
				  <input id="cmbEtcmTopicid" name="cmbEtcmTopicid" class="easyui-combobox" style="width:550px;" value="${requestScope.entTlTragcalmst.etcmTopicid}">
				  </div>
				</td>
			</tr>
			
			<tr>
			<td>
			<div style="margin-left:50%;margin-top:-25px;">
                 <input type="button" class="easyui-button" value ="Topic Master" id="btnTopicsave" style="height:30px"/>
                 
				  </div>
			</td>
			</tr>	
			
			
			<tr>
			<td>
            <div style="margin-left:700px; margin-top:-47px;"><label class="mandatory-lbl">Topic Category</label></div>
			<div style="margin-left:700px;">
			<input id="cmbEtcmTopiccategory" name="cmbEtcmTopiccategory" class="easyui-combobox" style="width:165px;/* height:150px; */" value="${requestScope.entTlTragcalmst.etcmTopiccategory}">		
			</div>
		   </td>
	       </tr> 
		
		<tr>
		<td>
		<div  class="easyui-paddingbfpx"style="margin-left:880px;margin-top:-47px;">
						                <label>Training mode</label> 
						                </div> 
		<div class="easyui-paddingbfpx" style="margin-left:880px;">
        <input id="cmbTopiTrainingmodeNew" name="cmbTopiTrainingmodeNew" class="easyui-combobox"  style="width:130px"readonly="readonly"  value="${requestScope.entTlTopicmst.topiTrainingmode}" />                   
						                </div>
			
			</td>
			</tr>
			
	<tr>
	<td>		
   <div  class="easyui-paddingbfpx"style="margin-left:1020px;margin-top:-47px;">
						                    <label>Training Type</label> 
						                </div>
   <div class="easyui-paddingbfpx" style="margin-left:1020px;">
   <input id="cmbTopiRelatedto" name="cmbTopiRelatedto" class="easyui-combobox"  style="width:130px;"readonly="readonly"  value="${requestScope.entTlTopicmst.topiTrainingmode}" />                   
						              </div>
						  </td>
						  </tr>              

				<%-- <tr>
				<td>
                   <div style="margin-left:880px; margin-top:-47px;"><label class="">Remarks</label></div>
					<div style="margin-left:880px;">
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtEtcmRemarks" name="txtEtcmRemarks" style="resize:none;width:250px; height:60px;" maxlength="100" 
																		value="">${requestScope.entTlTragcalmst.etcmRemarks}</textarea>
		   </div>
		   </td>
	       </tr>  --%>
	       
	       
	       <tr>
		   <td>
		   <div style="margin-left:0%;margin-top:-10px;"><label class="mandatory-lbl">Date</label></div>
								<div style="margin-left:0%;">
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width :86px;" id="dteEtcmCalendarDate" name="dteEtcmCalendarDate" 
								 													value="${requestScope.entTlTragcalmst.etcmCalendarDate}"/>
							      	</span>
								</div>
		   </td> 
		   </tr>
		   <tr> 	             
 	             <td>
				 <div style="margin-left:136px; margin-top:-37px;">			  
				 <label><b>Type Of Training</b></label>	
				  </div>
				</td>
			</tr> 
		  
		    <tr> 	             
 	             <td>
				 <div style="margin-left:95px; margin-top:-20px;">			  
				 <input type="checkbox"  id="chkEtcmGeneral" name="chkEtcmGeneral"  value="${requestScope.entTlTragcalmst.etcmGeneral}"/>
				 <label>General</label>	
				  </div>
				</td>
			</tr> 
			
		
			
			    <tr> 	             
 	             <td>
				 <div style="margin-left:175px; margin-top:-20px;">			  
				 <input type="checkbox"  id="chkEtcmUniqueposition" name="chkEtcmUniqueposition"  value="${requestScope.entTlTragcalmst.etcmUniqueposition}"/>
				 <label>Unique Position</label>	
				  </div>
				</td>
			</tr>   
			
			<tr> 	             
 	             <td>
				 <div style="margin-left:291px; margin-top:-20px;">			  
				 <input type="checkbox"  id="chkEtcmMSD" name="chkEtcmMSD"  value="${requestScope.entTlTragcalmst.etcmMSD}"/>
				 <label>MSD</label>	
				  </div>
				</td>
			</tr>   
		    <tr>
			<td>
				
				  <div style="margin-left:350px; margin-top:-20px;">				  
				  	<input type="checkbox"  id="chkEtcmChkCompleted" name="chkEtcmChkCompleted"  value="${requestScope.entTlTragcalmst.etcmChkCompleted}"/>
				  </div>
				</td>
			</tr>   

	       
	       <tr>
			<td>
				<div style="margin-left:365px; margin-top:-44px;"><label>Completed Date</label></div>
				  <div style="margin-left:365px;">
				  <input class="easyui-text" style=" width :86px;" id="dteEtcmCompletedDate" name="dteEtcmCompletedDate" value="${requestScope.entTlTragcalmst.etcmCompletedDate}"/> 
				  </div>
				</td>
				</tr>
				
				<tr>
				<td>
				<div style="margin-left:462px; margin-top:-47px;"><label>Completed By</label></div>
				  <div style="margin-left:462px;">
				  <input id="cmbEtcmCompletedBy" name="cmbEtcmCompletedBy" class="easyui-combobox" style="width:220px;"  value="${requestScope.entTlTragcalmst.etcmCompletedBy}"/>
				  </div>
				</td>
				</tr>
				
				<tr>
				<td>
				 <div style="margin-top:-30px;margin-left:710px;padding-left:140px\9;">
			     <span  id="newtrainingcalFilemgr" style="">
                 </span> 
                  </div>
				</td>
				</tr>
	        
		</table>
		<table>
		<tr>
		<td colspan="3">
					<div>
						<table id="nretragrid" ></table> 
						<div id="trapages"></div>
					</div>
				</td>
		</tr>
		</table>
		
	</div>
	
	
	
	
<div id="tabTrainingCalendar" class="easyui-tabs" style="height:auto;width:1102px;margin-top:-4px;margin-left:4%; float:left;">

  <div title="General">
 <table style="margin-left:8px;margin-left:2px\9;"> 
 <tr>
				
	    <td valign="top">
         <div style="padding-top:2px;">
         <span style="margin-left:20px;">
		<label class="mandatory-lbl">Training Identified Through</label>
		</span>
		<div style="margin-left:20px;">
       <input id="cmbEtcmFunction" name="cmbEtcmFunction" class="easyui-combobox" style="width:220px;" value="${requestScope.entTlTragcalmst.etcmFunction}"/>
		</div>
		</div>
		</td>
		   
		<td valign="top">
         <div style="margin-top:2px;">
         <span style="margin-left:40px;">
		<label id="" class="mandatory-lbl">Duration(hrs)</label>
		</span>
		<div style="margin-left:45px;">
        <input id="txtEtcmMaxDuration" maxlength="6" name="txtEtcmMaxDuration" class="easyui-text" style="width:85px;text-align:right;height:21px;"  value="${requestScope.entTlTragcalmst.etcmMaxDuration}" /><span id="" style="margin-left:30px;">
		</span>
		</div>
		</div>
		</td>
	
		   <td valign="top">
         <div style="margin-top:50px;">
         <span style="margin-left:-381px;">
		
		<label id=""class="mandatory-lbl">Training Function</label>
		</span>
		<div style="margin-left:-381px;">
    <input id="cmbEtcmTrainingfunction" name="cmbEtcmTrainingfunction" class="easyui-combobox" style="width:220px;" value="${requestScope.entTlTragcalmst.etcmTrainingfunction}"/>
	
		</div>
		</div>
		</td>
				
              <td>
	 			<div style='margin-top:-35px; margin-left:-118px;'><label class="" >Permitted Strength </label></div>
	 			<div class="easyui-paddingbfpx" style="margin-left:-118px;">
		 			<input type="text" class="easyui-text" maxlength="3" id="txtEtcmPermittedStrength" name="txtEtcmPermittedStrength" value="${requestScope.entTlTragcalmst.etcmPermittedStrength}" style="width:105px;text-align:right;height:21px;"/>
	 			</div>
	 		</td>
	 		

               <td>
		 		<div style="margin-top:50px;margin-left:-115px;"><label>Material Ready?</label></div>
	 			  <div style="margin-left:-115px;">
				
				<input type="checkbox" id="chkEtcmMaterialsReadyY"   name="chkEtcmMaterialsReady"  value="Y">
				<c:out value = "${requestScope.entTlTragcalmst.etcmMaterialsReady == 'Y' ? ' checked':' '}"/>
				<label style="margin-left:3px;">Yes</label>
               <span style="margin-left:3px;">
			   <input type="checkbox" id="chkEtcmMaterialsReadyN" value="N" name="chkEtcmMaterialsReady">
			   <c:out value = "${requestScope.entTlTragcalmst.etcmMaterialsReady == 'N' ? ' checked':' '}"/>
				<label style="margin-left:3px;">No</label>	
				</span>
				</div>
	 		</td>
	 		
	 		
	 		 <td>
		 		<div style="margin-top:140px;margin-left:-120px;"><label>Marks Required?</label></div>
	 			  <div style="margin-left:-120px;">
					
					<input type="checkbox" id="chkEtcmMarksBasedY"   name="chkEtcmMarksBased" value="Y"/>
					<c:out value = "${requestScope.entTlTragcalmst.etcmMarksBased == 'Y' ? ' checked':' '}"/>
					<label style="margin-left:3px;">Yes</label>
					<span style="margin-left:3px;">					
					<input type="checkbox" id="chkEtcmMarksBasedN"   name="chkEtcmMarksBased"  value="N">
					<c:out value = "${requestScope.entTlTragcalmst.etcmMarksBased == 'N' ? ' checked':' '}"/>
					<label style="margin-left:3px;">No</label>
				</span>
				</div>
	 		</td>
	 		
	 		
	 		     <td>
		 		 <div style="margin-left:-380px;margin-top:150px;"><label>Assessment Required?</label></div>
	 			  <div style="margin-left:-380px;">
					<input type="checkbox" id="chkEtcmAssessmentReqY"   name="chkEtcmAssessmentReq"  value="Y">
				    <c:out value = "${requestScope.entTlTragcalmst.etcmAssessmentReq == 'Y' ? ' checked':' '}"/>
					<label style="margin-left:3px;">Yes</label>
					<span style="margin-left:3px;">
					<input type="checkbox" id="chkEtcmAssessmentReqN" value="N" name="chkEtcmAssessmentReq">
			        <c:out value = "${requestScope.entTlTragcalmst.etcmAssessmentReq == 'N' ? ' checked':' '}"/>
					<label style="margin-left:3px;">No</label>
				    </span>
				    </div>
	 		        </td>
	 	
		
<!-- 	commented by vignesh	    <td valign="top"> -->
<!-- 	 			<div style="margin-left:76px;margin-top:22px;"><label>Faculty</label></div> -->
<!-- 				<div class="easyui-paddingbfpx" style="width :311px;margin-left:76px;"> -->
<!-- 				<input id="cmbEtcfFacultyId" name="cmbEtcfFacultyId" class="easyui-combobox" style="width:245px;"  /> -->
<!-- 				<span style="margin-left:10px;"> -->
<!-- 				 <input type="button" class="easyui-button" value="Add" id="btnFacultySave"/> -->
<!-- 				</span> -->
<!-- 				</div> -->
<!-- 	 		</td> -->
	 		
	 		<td valign="top">
 			<div style="margin-left:76px;margin-top:22px;">
 			    <label>Faculty</label>
 			    <span style="margin-left:10px;">
 			        <input type="checkbox" id="chkEtcfOther" name="chkEtcfOther" value="N"/>
 			        <label>Other</label>
 			    </span>
 			</div>
				<div class="easyui-paddingbfpx" style="width :311px;margin-left:76px;">
				<input id="cmbEtcfFacultyId" name="cmbEtcfFacultyId" class="easyui-combobox" style="width:245px;"  />
				<span style="margin-left:10px;">
				 <input type="button" class="easyui-button" value="Add" id="btnFacultySave"/>
				</span>
				</div>
 		</td>
	 	
	 	 <td valign="top">	
	 	 <div  style="margin-top:34px;margin-left:80px;">
	     <input type="button" class="easyui-button" value ="Faculty Master" id="btnFacultyAdd" style="height:30px"/>
     	</div>
	 	</td>	 
 </tr>
 
 	    <tr>
		 <td valign="top">
         <div style="margin-top:-80px;">
         <span style="margin-left:20px;">
	<label id="">Venue</label>
		</span>
		<div style="margin-left:20px;">
         <input id="cmbEtcmVenue" name="cmbEtcmVenue" class="easyui-combobox" style="width:220px;" value="${requestScope.entTlTragcalmst.etcmVenue}"/>
	 
     	</div>
		</div>
		</td>
		</tr>

</table>
  
     <div style="margin-left:39%;margin-left:39px\9;margin-top:-180px;\0\margin-top:5px;" >

     <div style="margin-left:38%;margin-left:60%\9;">
      
     </div>
     <div style="margin-left:70px;margin-top:70px;">
	<table id="Gengrid" style=""> <tr> <td> </td></tr></table>
	 </div>
	<div style="height:10px;"></div>
	</div>
	
                         
<%--    <div  class="easyui-paddingbfpx"style="margin-left:270px;margin-top:-50px;">
						                    <label>Training Type</label> 
						                </div> 
						                <div class="easyui-paddingbfpx" style="margin-left:270px;">
						                    <input id="cmbTopiRelatedto" name="cmbTopiRelatedto" class="easyui-combobox"  style="width:180px;"disabled="disabled"  value="${requestScope.entTlTopicmst.topiTrainingmode}" />                   
						                </div>	 --%>			                
</div>
 
 <div title="Session/Unique Position">
 <table style="margin-left:30px;margin-top:10px;margin-left:50px\9"> 
 <tr>
 <td valign="top">
<td style="margin-left:5px;"  valign="top">
	 			<div>
	 				<label>Session</label>
	 				<span style="margin-left:60px;"><label>From</label></span>
	 				<span style="margin-left:40px;"><label>To</label></span>
	 			</div>
	 			<div class="easyui-paddingbfpx" style=" width : 330px;">
				 	<input id="dteSessiondate" name="dteSessiondate" class="easyui-datebox" style="width:100px;"  />
					
					<span style="margin-left:10px;">
						<input  id="spnsessionFromTime" name="spnsessionFromTime" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /> 
					</span>
					<span style="margin-left:10px;">
						<input  id="spnsessionTillTime" name="spnsessionTillTime"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /> 
					</span>

					<span style="margin-left:20px;">
						<input type="button" class="easyui-button" value="Add" id="btnSessionsave"/>
					 </span>								 	
				</div>
	 		</td>
	 		
	 		   <td style="margin-left:52px;">
		 		<div style="margin-left:168px; margin-top:-44px;"><label id="" >DMT</label></div>
				<div style="margin-left:168px;"  class="easyui-paddingbfpx">
				<input class="easyui-combobox" id="cmbEtcuRoleDmt" name="cmbEtcuRoleDmt"  style=" width : 220px;"  value="${requestScope.entTlTrgCalUnqp.etcuRoledmt}" />
				</div>
	 		</td>
	 		
	 		  <td style="margin-left:35px;">
		 		<div style="margin-left:35px; margin-top:-44px;"><label id="" >JH</label></div>
				<div style="margin-left:35px;"  class="easyui-paddingbfpx">
				<input class="easyui-combobox" id="cmbEtcuRoleJh" name="cmbEtcuRoleJh"  style=" width : 200px;"  value="${requestScope.entTlTrgCalUnqp.etcuRoleJh}" />
				</div>
	 		   </td>
	 		  
	 		   <td>
	 	    	<div style="margin-left:-460px;margin-top:50px;"><label id= "" class="mandatory-lbl" >Unique Position</label>
		 	    	<span style="margin-left:8px;">
						<input type="checkbox"  id="chkUniquePosition" name="chkUniquePosition" value=""> 
			 		<label>All</label></span>
			 		</div>
				<div style="margin-left:-460px;margin-top:5px; class="easyui-paddingbfpx" style=" width : 270px;">
				<input id="cmbEtcuRoleKeyid" name="cmbEtcuRoleKeyid" value="${requestScope.entTlTrgCalUnqp.etcuRoleKeyid}" class="easyui-combobox" style="width:220px;" />
				<span style="margin-left:20px;">
				<input type="button" class="easyui-button" value="Add" id="btnUPSave" onclick="upSave();" ></input>
				</span>
				
				<span style="margin-left:30px;">
				<input type="button" class="easyui-button" value="Multiple Unique Position" id="btnMultipleUnique" onclick="MUnique();" ></input>
				</span>
				
				</div>
	 	    </td>
 </table>


<div style="margin-left:15px; margin-left:65px\9;">
	<table id="SesGrid">
	<tr><td></td></tr>
	</table>
	<div id="Sespagerid"></div>
   </div>
   
   <div style="margin-left:530px;margin-top:-200px; margin-left:75px\9;">
	<table id="UniqueGrid">
	<tr><td></td></tr>
	</table>
     <div id="Uniquepagerid"></div>
   </div>
   
   
 </div>
 


<div title="Employee" style="padding-left:23px;padding-top:50px;width: 800px;">
    <table style="padding-left:24px;padding-top:10px;padding-left:50px\9">
    </table>
  
   <table> 
       <tr>
        <td>	
	 	 <div style="margin-top:-28px;width:60px;height:15px;margin-left:200px;">
	     <input type="button" class="easyui-button" value ="Employee Add" id="btnUniqueAdd" style="height:30px"/>
     	</div>
     	 </tr>
        </td>
        </table>
         
     
	 <div style="margin-left:4px;margin-top:-20px;">
	 <label><b>Employee Selected for Training</label></b>	
	 </div>
 
 <div style="margin-left:350px;margin-top:-20px;">
	 <label><b>Select CheckBox To Delete Record</label></b>	
	 </div>
	
 <div style="width:95%;position:relative;">
	<div style="float:left; margin-left:4px; margin-left:65px\9;">
		 <table id="selectedRoleGrd">
			<tr> <td> </td> </tr>
		 </table>
	    </div>
</div>	

</div>


<div title="Employee Attendance">
<div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">

  <table>
  
  <tr> 	             
  <td style="margin-left:2px;padding-top:-40px;">
				 <div style="margin-left:2px; margin-top:11px;">			  
				 <input type="checkbox"  id="chkSelectAllEmp" name="chkSelectAllEmp"  value=""/>
				 <label id="">Select All</label>	
				 </div>
				</td>
			</tr> 
  
  <tr> 	             
  <td style="margin-left:2px;padding-top:-40px;">
				 <div style="margin-left:84px; margin-top:-20px;">			  
				 <input type="checkbox"  id=chkEtcaAssessmentCom name="chkEtcaAssessmentCom"  value="${requestScope.entTlTtgCalEmpatScore.etcaAssessmentCom}"/>
				 <label id="">Assessment Completed?</label>	
				 </div>
				</td>
			</tr> 
 
<tr>
 <td>
<div style="margin-left:370px; margin-top:-35px;"><label id="" >Max.Marks</label></div>
<div class="easyui-paddingbfpx" style="margin-left:370px;">
<input type="text" class="easyui-text" id="txtEtcaMaxMarks" name="txtEtcaMaxMarks"  style=" width :80px;" value=""/>
</div>
</td>
</tr>
 
 <tr>
 <td>
<div style="margin-left:290px; margin-top:-42px;"><label id="" >Cut Off</label></div>
<div class="easyui-paddingbfpx" style="margin-left:290px;">
<input type="text" class="easyui-text" id="txtEtcaCutOff" name="txtEtcaCutOff"  style=" width : 56px;" value=""/>
</div>
</td>
</tr>

 <tr>
 <td>
<div style="margin-left:475px; margin-top:-45px;"><label id="" >Type</label></div>
<div class="easyui-paddingbfpx" style="margin-left:475px;">

<select class="easyui-text" id="cboEtcaType"  name="cboEtcaType" panelHeight=80px;  style="width:  100px; height: 21px;" >
							<option value='O'>ORAL</option>
							<option value='W'>WRITTEN</option>
						</select>
						</div>
</td>
</tr>


<!-- <tr>
 <td>
<div class="easyui-paddingbfpx" style="margin-left:580px;margin-top:-40px;">
<input type="button" class="easyui-button" id="btnEmpAttendance" name="btnEmpAttendance" value="Save" style=" width : 80px;" value=""/>
</div>
</td>
</tr> -->

</table>
<table id="EmpAttenGrid">
<tr><td></td></tr>
	</table>
	        <div id="emppagerid"></div>
   </div>
   </div>
   <div title="FeedBack">
<div style="margin-left:14px; margin-top:20px;margin-left:65px\9;">
  <table>
<tr>
 <td>
<div style="margin-left:50px; margin-top:-15px;"><label id="lblRating" class="mandatory-lbl">Rating</label></div>
<div class="easyui-paddingbfpx" style="margin-left:50px;">
<input id="cmbEtcmRating" name="cmbEtcmRating" value="${requestScope.entTlTragcalmst.etcmRating}" class="easyui-combobox" style="width:100px;" />
			
</div>
</td>
</tr>
 
 <tr>
 <td>
<div style="margin-left:200px; margin-top:-42px;"><label id="" >Comments</label></div>
<div class="easyui-paddingbfpx" style="margin-left:200px;">
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtEtcmComments" name="txtEtcmComments" style="resize:none;width:550px; height:80px;" maxlength="400" 	value="">${requestScope.entTlTragcalmst.etcmComments}</textarea>
				
</div>
</td>
</tr>
</table>
   </div>
  </div>
  </div>
 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
 <input type="hidden" id="txtIsFacultyLink" name="txtIsFacultyLink" value=" "/>
 <input type="hidden" id="txtIsUniquePosition" name="txtIsUniquePosition" value=" "/>
 <input type="hidden" id="txtIsSession" name="txtIsSession" value=" "/>
 <input type="hidden" id="hdnEtcmKeyid" name="hdnEtcmKeyid" value="${requestScope.entTlTragcalmst.etcmKeyid}"/>
 <input type="hidden" id="txtEtcsKeyid" name="txtEtcsKeyid" value="${requestScope.entTlTrgCalSession.etcsKeyid}"/>
 <input type="hidden" id="hdnUserid" name="hdnUserid" value="${requestScope.rolename}"/>
  <input type="hidden" id="hdnEmpcnt" name="hdnEmpcnt" value="${requestScope.empcount}"/>
   <input type="hidden" id="hdnAssescnt" name="hdnAssescnt" value="${requestScope.asscnt}"/>
  <input type="hidden" id="hdnEmpattnd" name="hdnEmpattnd" value="${requestScope.empattn}"/>
  <input type="hidden" id="hdnSessionId" name="hdnSessionId" value=""/>
    <input type="hidden" id="hdnUniqId" name="hdnUniqId" value=""/> 
    <input type="hidden" id="hdnmark" name="hdnmark" value="${requestScope.entTlTragcalmst.etcmMarksBased}"/>
     <input type="hidden" id="hdnassessment" name="hdnassessment" value="${requestScope.entTlTragcalmst.etcmAssessmentReq}"/>     
 <input type="hidden" id="hdnmaterial" name="hdnmaterial" value="${requestScope.entTlTragcalmst.etcmMaterialsReady}"/>     
  <input type="hidden" id="hdncurrentdate" name="hdncurrentdate" value="${requestScope.currentdate}"></input> 
 
    <input type="hidden" id="hdntitle" name="hdntitle" value="${requestScope.Title}"/>
      <input type="hidden" id="hdnmaxmarks" name="hdnmaxmarks" value="${requestScope.maxmarks}"/> 
        <input type="hidden" id="hdncutoff" name="hdncutoff" value="${requestScope.Cuttoff}"/>  
        
  <input type="hidden" id="hdnetcmChkCompleted" name="hdnetcmChkCompleted" value="${requestScope.etcmChkCompleted}"/>  
   
   
    <input type="hidden" id="hdncompleteddate" name="hdncompleteddate" value="${requestScope.etcmCompletedDate}"/>  
    
   
     <input type="hidden" id="loginEmpshow" name="loginEmpshow" value="Y">
     <input type="hidden" id="id" name="id" value="">
     
     <input type="hidden" id="hdntype" name="hdntype" value="${requestScope.assesType}"/>  
     
</form>