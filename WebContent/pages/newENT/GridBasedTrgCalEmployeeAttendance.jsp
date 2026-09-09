<script type="text/javascript">
jQuery(document).ready(function() {
	
	var seci =jQuery("#hdnSectionId").val();
	
  //alert(13 + " section id  " + seci );
	initialiseForm("frmEmplAtted");
	jQuery("#submitForm").val("frmEmplAtted");
	var url="";
	var ds="q=2";
	
	readOnlyFields("txtEtcaCutOff");
	readOnlyFields("txtEtcaMaxMarks");
	readOnlyFields("chkEtcaAssessmentCom");
	 numericTextBox('txtEtcaCutOff');
	 numericTextBox('txtEtcaMaxMarks');
	 fileManagerPopUp("","TRAINING","frmEmplAtted","btnFilManage","newtrainingcalFilemgr");

	//alert(14);
	// var asseMntCheck=jQuery("#chkEtcaAssessmentCom").val();
	 var cutOffMrk=jQuery("#txtEtcaCutOff").val();
	 var maxMark=jQuery("#txtEtcaMaxMarks").val();
	
	// 12March2026 Vignesh
	 var assesType = jQuery("#hdnAssesType").val();
	
	var mrkReq=jQuery("#hdnMarksReqd").val();
	//alert(mrkReq);
	var hdnAssCompl=jQuery("#hdnAssCompl").val();
	 if(mrkReq=="Y"){
		enableFields("txtEtcaCutOff");
		enableFields("txtEtcaMaxMarks");
	}
	else{
		readOnlyFields("txtEtcaCutOff");
		readOnlyFields("txtEtcaMaxMarks");
	}
	 if(hdnAssCompl=="Y"){
		jQuery("#chkEtcaAssessmentCom").prop("checked", true);
	}
	else{
		jQuery("#chkEtcaAssessmentCom").prop("checked", false);
    	} 
	 var frmMode=jQuery('#hdnMode').val();
		var cutofMark= jQuery("#hdnCuttoff").val();
		var maxMark= jQuery("#hdnMaxMarks").val();

		// alert(frmMode+" frmMode "+cutofMark+" maxMark "+maxMark);
		 // commented by vignesh
 		 if(frmMode.trim()==="modify"){
			 

			 jQuery("#txtEtcaCutOff").val(cutofMark);
			 jQuery("#txtEtcaMaxMarks").val(maxMark);
			 jQuery("#cboEtcaType").val(assesType);
 			 readOnlyFields("txtEtcaCutOff");
 			 readOnlyFields("txtEtcaMaxMarks");
 		 }
		 
		 if(frmMode=="view"){
			 disableForm('frmEmplAtted');
		 }
	
	
	viewGrid(url,ds);
});


 // --------- vignesh 02 dec2025 -------------------------//
// function viewGrid(url,ds){
// 	 var sectionId=jQuery("#hdnSectionId").val();
// 	 var flid=jQuery("#hdnflid").val();
//  	 var locationId=jQuery("#hdnlocaid").val();
//   	 var keyId=jQuery("#hdncalendar").val();
//   	// var cellId="";
//   	// - vignesh
//   	var cellId = jQuery("#hdnJhId").val() || "";



// 	ds+="&SectionId="+sectionId+"&cellId="+cellId+"&locationId="+locationId+"&TraKeyid="+keyId;

// 		processGridnew("EmpTrainingAtt_input.gbtc",ds,"grdEmplAttGrid","pager","","","","EmpAttendanceloadComplete","");

// }
 

	
	
	
function viewGrid(url, ds) {
	  // Use the hidden values the servlet forwards
	  var sectionId  = jQuery("#hdnSectionId").val();
	  var cellId     = jQuery("#hdnJhId").val();
	  var flid       = jQuery("#hdnflid").val();
	  // IMPORTANT: your JSP renders "locnid" (not "locaid")
	  var locationId = jQuery("#hdnlocnid").val() || jQuery("#hdnlocaid").val() || "";
	  var keyId      = jQuery("#hdncalendar").val();

	  // If section is still blank at company level, send "{}" so the UI path doesn't bail
	  if (!sectionId || jQuery.trim(sectionId) === "") sectionId = "{}";
	  // Cell can be blank; it isn't used by the server in attendance data
	  if (!cellId) cellId = "";

	  ds += "&SectionId="  + encodeURIComponent(sectionId)
	     + "&cellId="      + encodeURIComponent(cellId)
	     + "&locationId="  + encodeURIComponent(locationId)
	     + "&TraKeyid="    + encodeURIComponent(keyId)
	     + "&flid="        + encodeURIComponent(flid);

	  processGridnew(
	    "EmpTrainingAtt_input.gbtc",
	    ds,
	    "grdEmplAttGrid",    // keep your actual grid id
	    "pager",
	    "", "", "", "EmpAttendanceloadComplete", " "
	  );
	}
	
function viewGrid(url, ds) {
	  // Read the hiddens the servlet already sets on the page
	  var sectionId  = jQuery("#hdnSectionId").val();
	  var cellId     = jQuery("#hdnJhId").val();               // was hard-coded ""
	  var flid       = jQuery("#hdnflid").val();

	  // IMPORTANT: in this JSP the hidden is "hdnlocnid" (requestScope.locnid).
	  // keep hdnlocaid as a fallback (it’s loginLocnId).
	  var locationId = jQuery("#hdnlocnid").val() || jQuery("#hdnlocaid").val() || "";

	  var keyId      = jQuery("#hdncalendar").val();

	  // Company-level often leaves Section blank. Send a harmless token instead of empty.
	  if (!sectionId || jQuery.trim(sectionId) === "") sectionId = "{}";
	  if (!cellId) cellId = ""; // fine to be blank

	  ds += "&SectionId="  + encodeURIComponent(sectionId)
	     +  "&cellId="     + encodeURIComponent(cellId)
	     +  "&locationId=" + encodeURIComponent(locationId)
	     +  "&TraKeyid="   + encodeURIComponent(keyId)
	     +  "&flid="       + encodeURIComponent(flid);

	  processGridnew(
	    "EmpTrainingAtt_input.gbtc",
	    ds,
	    "grdEmplAttGrid",
	    "pager",
	    "", "", "", "EmpAttendanceloadComplete", " "
	  );
	}



//--------- vignesh 02 dec2025 -------------------------//
function EmpAttendanceloadComplete(){
	var mode=jQuery("#hdnMode").val();	
	
	    
	     var mode1="modify";
	     var mode2="create";
		   if(mode.trim() === mode1.trim() || mode.trim() === mode2)
			  {
			    
		  var row = jQuery("#grdEmplAttGrid").jqGrid('getDataIDs');
			var cm = jQuery("#grdEmplAttGrid").jqGrid("getGridParam", "colModel");
			 for(var i=0;i<row.length;i++)
			 {
				   var etcakeyid = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"hdnEtcaKeyid");	
				   var presentabsent = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"cmbEtcaPresentAbsent");
				   var attendanceFileMgr = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"btnFilManage"); 
				   var attendanceDate = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"dteEtcaAddDate"); 
				   var attendanceRemarks=jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"txtEtcaRemarks");
				   var attendanceResult=jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"cmbEtcaResult");
				   var attendanceScore=jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"txtEtcaScore");
				   if(etcakeyid.trim().length>0){ 
					    //jQuery('#grdEmplAttGrid').setSelection(row[i], true);
					   
					    jQuery('input:checkbox[id=jqg_grdEmplAttGrid_'+row[i]+']').attr('checked',true);
					    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'selctVal','1');
					    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'cmbEtcaPresentAbsent',presentabsent);
					    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'btnFilManage',attendanceFileMgr); 
					    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'dteEtcaAddDate',attendanceDate);
					    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'txtEtcaRemarks',attendanceRemarks);
					    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'cmbEtcaResult',attendanceResult);
					    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'txtEtcaScore',attendanceScore);
					    jQuery('input:checkbox[id=jqg_grdEmplAttGrid_'+row[i]+']').attr('disabled',true);
				   }
			 }  
		   }
		   else if(mode.trim()==="view"){
			   disableUIButton("btnEmpAttSave");
			 var row = jQuery("#grdEmplAttGrid").jqGrid('getDataIDs');
				var cm = jQuery("#grdEmplAttGrid").jqGrid("getGridParam", "colModel");
				 for(var i=0;i<row.length;i++)
				 {
					   var etcakeyid = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"hdnEtcaKeyid");	
					   var presentabsent = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"cmbEtcaPresentAbsent");
					   var attendanceFileMgr = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"btnFilManage"); 
					   var attendanceDate = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"dteEtcaAddDate"); 
					   var attendanceRemarks=jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"txtEtcaRemarks");
					   var attendanceResult=jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"cmbEtcaResult");
					   var attendanceScore=jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"txtEtcaScore");
					   if(etcakeyid.trim().length>0){ 
						    jQuery('#grdEmplAttGrid').setSelection(row[i], true);
						    jQuery('input:checkbox[id=jqg_grdEmplAttGrid_'+row[i]+']').attr('checked',true);
						    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'selctVal','1');
						    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'cmbEtcaPresentAbsent',presentabsent);
						    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'btnFilManage',attendanceFileMgr); 
						    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'dteEtcaAddDate',attendanceDate);
						    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'txtEtcaRemarks',attendanceRemarks);
						    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'cmbEtcaResult',attendanceResult);
						    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'txtEtcaScore',attendanceScore);
						    jQuery('input:checkbox[id=jqg_grdEmplAttGrid_'+row[i]+']').attr('disabled',true);
					   }
				 }
		}
		
  
}
////////////////

   // ------ vignesh - save ------------ 03dec2025 ----------------------------------------------------------------//
 jQuery('#btnEmpAttSave').click(function () {

    var locationid = jQuery("#hdnlocnid").val();
    var flid       = jQuery("#hdnflid").val();
    var topicId    = jQuery("#hdnTopicId").val();

    var griId  = "grdEmplAttGrid";
    var gridval = getGridSelectArray(griId) || "[]";   // ensure something valid

    var cutOffMrk = jQuery("#txtEtcaCutOff").val();
    var maxMark   = jQuery("#txtEtcaMaxMarks").val();
    var type      = jQuery("#cboEtcaType").val();
    var keyId     = jQuery("#hdncalendar").val();

    var assCom = "";
    if (jQuery("#chkEtcaAssessmentCom").is(':checked')) {
        assCom = "Y";
    } else {
        assCom = "N";
    }

    // ✅ Encode the JSON so [, ], {, }, ", spaces, etc. don’t break the URL
    var paramconvertArrParam = encodeURIComponent(gridval);

    // ✅ Also encode all dynamic values to be safe
    var url = "EmployeeAttendance_save.gbtc"
        + "?keyId="      + encodeURIComponent(keyId       || "")
        + "&paramconvertArr=" + paramconvertArrParam
        + "&flid="       + encodeURIComponent(flid        || "")
        + "&locationid=" + encodeURIComponent(locationid  || "")
        + "&topicId="    + encodeURIComponent(topicId     || "")
        + "&assCom="     + encodeURIComponent(assCom      || "")
        + "&type="       + encodeURIComponent(type        || "")
        + "&cutOffMrk="  + encodeURIComponent(cutOffMrk   || "")
        + "&maxMark="    + encodeURIComponent(maxMark     || "");

    // Your existing helper – unchanged
    saveForm("frmEmplAtted", url);
});

	
// jQuery('#btnEmpAttSave').click(function(){
// //alert(123);
// 	var locationid=jQuery("#hdnlocnid").val();
// 	var flid=jQuery("#hdnflid").val();
// 	var topicId=jQuery("#hdnTopicId").val();
	 

// 	var griId="grdEmplAttGrid";
// 	 // alert(griId +"123");
//     var gridval= getGridSelectArray(griId);  
//  //  alert(gridval +"123");
// 	 var cutOffMrk=jQuery("#txtEtcaCutOff").val();
// 	 var maxMark=jQuery("#txtEtcaMaxMarks").val();
// 	 var type=jQuery("#cboEtcaType").val();
// 	 var keyId=jQuery("#hdncalendar").val();
// 	 var assCom="";
// 	  if(jQuery("#chkEtcaAssessmentCom").is(':checked',true)){
// 		  assCom="Y";
// 	  }
// 	  else{
// 		  assCom="N";
// 	  } 

//      //var assmnType=jQuery("#cboEtcaType").combobox("getValue");
//     // alert(3623);
//  // alert(locationid +"  locationid  "+ flid +"  flid  "+assCom +" asseMntCheck "+cutOffMrk +" cutOffMrk "+cutOffMrk+"maxMark " +maxMark +" assmnType" );

// 	  	     saveForm("frmEmplAtted","EmployeeAttendance_save.gbtc?&keyId="+keyId+"&paramconvertArr="+gridval+"&flid="+flid+"&locationid="+locationid+"&topicId="+topicId+"&assCom="+assCom+"&type="+type+"&cutOffMrk="+cutOffMrk+"&maxMark="+maxMark);
			
			
// 	  });	
   
   // ------ vignesh - save ------------ 03dec2025 ----------------------------------------------------------------//
function frmEmplAtted_successsCallback(result){

		 jQuery('#grdEmplAttGrid').trigger('reloadGrid');
		/* alert("unique Successcallback");
		var compl=getFieldValue("chkEtcmChkCompleted","frmNewTraCal");
		alert("compl"+compl);
		enableFormFields("frmNewTraCal","chkEtcmChkCompleted") ;*/
		var keyid = jQuery('#hdncalendar').val();
		    var flid=jQuery("#hdnflid").val();
		   
	}
jQuery("#btnClose").click(function(){
	  closePopUpDialoge("divEmployeeAtt");
		jQuery('#trgCalendarGrid').trigger('reloadGrid');

});
jQuery("#chkSelectAllEmp").click(function(){
	   show_winMask(1);
    fnSelectAllEmp();
	   show_winMask(0);
});

function  fnSelectAllEmp(){
		var row = jQuery("#grdEmplAttGrid").jqGrid('getDataIDs');

		for(var i=0;i<row.length;i++)
		 {
			if(jQuery("#chkSelectAllEmp").is(':checked')== true){
				var etcakeyid = jQuery("#grdEmplAttGrid").jqGrid('getCell',row[i],"hdnEtcaKeyid");
			//	alert(etcakeyid);
				if(etcakeyid.length>0){
					var jqGridId="grdEmplAttGrid";
					 //jQuery("#dteEtcaAddDate_"+jqGridId+"_"+row[i]).attr('readonly','readonly'); 
					  jQuery("#cmbEtcaPresentAbsent_"+jqGridId+"_"+row).attr('disabled',true);
					}
				jQuery('#grdEmplAttGrid').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_grdEmplAttGrid_'+row[i]+']').attr('checked',true);
			    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'selctVal','1');
			 }
			 else {
				    jQuery('#grdEmplAttGrid').setSelection(row[i], false);
				    jQuery('input:checkbox[id=jqg_grdEmplAttGrid_'+row[i]+']').attr('checked',false);
				    jQuery("#grdEmplAttGrid").jqGrid('setCell',row[i],'selctVal','0');
              //   alert("else");
			 }
		 }	
	}

function convertJsonArr(){
	var allrow = jQuery("#grdEmplAttGrid").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_grdEmplAttGrid_' + rowno).is(':disabled')==true)
			{
			continue;
			}
           
            
		
		if (jQuery('#jqg_grdEmplAttGrid_' + rowno).is(':checked')) {
			
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
//------------------------------

function grdEmplAttGrid_selectRow(rowId)
{
	var jqGridId="grdEmplAttGrid";
	var dateCtrl="dteEtcaAddDate_grdEmplAttGrid_"+rowId;
   //  var dateCtrl="grdEmplAttGrid_txtEtcaSession_"+rowId;

	var divfilemangerkeyid="btnFilManage_grdEmplAttGrid_"+rowId;
    var keyid=jQuery("#grdEmplAttGrid").jqGrid('getCell',rowId,"hdnEtcaKeyid");
	 numericTextBox("txtEtcaScore_grdEmplAttGrid_"+rowId);
	formatDateBox(dateCtrl,'dd-MMM-yyyy');
	//fillWithCurrentDate(dateCtrl);
		 /*var mode=jQuery("#mode").val();
		 if(mode=="create"){
			 fillWithCurrentDate(dateCtrl);
		 }*/
		 
		 /* var marksbsd=jQuery("#hdnMarksReqd").val();
		    var assessment=jQuery("#hdnAssCompl").val();
		    if(marksbsd=="Y" && assessment=="Y")
		    	{
		    	
		    	  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('readonly','readonly'); 
				  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('disabled',true); 
				  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).css('background-color', '#ece9d8');
		    	}
		    
	    	
	    	
	    	else if(marksbsd=="N" && assessment=="Y")
		    	{
		         removeMandatoryFieldForComplete("grdEmplAttGrid",rowId);

				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('readonly','readonly'); 
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('disabled',true); 
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).css('background-color', '#ece9d8');
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val('0');
		    	}
		    
		    else{
		    	if(marksbsd=="N" && assessment=="N")
		    		{
			         removeMandatoryFieldForComplete("grdEmplAttGrid",rowId);

		    	jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('readonly','readonly'); 
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('disabled',true); 
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).css('background-color', '#ece9d8');
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val('0');
		    		}
		    } */

		    var marksbsd=jQuery("#hdnMarksReqd").val();
		    var assessment=jQuery("#hdnAssCompl").val();
		    if(marksbsd=="Y" )
		    	{
		    	
		    	  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('readonly','readonly'); 
				  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).attr('readonly',true); 
				  jQuery("#cmbEtcaResult_"+jqGridId+"_"+rowId).css('background-color', '#ece9d8');
		    	}
		    
	    	
	    	
	    	/* else if(marksbsd=="N" && assessment=="Y")
		    	{
		         removeMandatoryFieldForComplete("grdEmplAttGrid",rowId);

				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('readonly','readonly'); 
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('disabled',true); 
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).css('background-color', '#ece9d8');
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val('0');
		    	} */
		    
		    else{
		    	if(marksbsd=="N")
		    		{
			         removeMandatoryFieldForComplete("grdEmplAttGrid",rowId);

		    	jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('readonly','readonly'); 
			//	  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).attr('disabled',true); 
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).css('background-color', '#ece9d8');
				  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val('0');
		    		}
		    }   
		    
jQuery("#"+dateCtrl).datebox({  	   
		onSelect:function(recordid)
			{ 
			isValidDate(dateCtrl,rowId);    	
			} 
		}); 
         if(keyid.length==1){
			//var sesdate=getGridCell('SesGrid','1','Session'); 
			var sesdate =jQuery("#grdEmplAttGrid").jqGrid('getCell',rowId,"txtEtcaSession").substr(0, 11); //  +jqGridId+"_"+rowId).datebox('setValue'
					//formatDateBox("#dteEtcaAddDate_grdEmplAttGrid_"+rowId,"DD-MMM-YYYY");

					/*  if(keyid.length==1){
							var sesdate=getGridCell('SesGrid','1','Session');
				           jQuery("#dteEtcaAddDate_"+jqGridId+"_"+rowId).datebox('setValue',sesdate);
					 */
           jQuery("#dteEtcaAddDate_grdEmplAttGrid_"+rowId).datebox('setValue',sesdate);
					 
	  }	
		

jQuery("#cmbEtcaPresentAbsent_grdEmplAttGrid_"+rowId).combobox({  	   
	onSelect:function(recordid)
		{     // commneted by vignesh
		var presentabsent=recordid.value; //jQuery("#cmbEtcaPresentAbsent_grdEmplAttGrid_"+rowId).combobox('getValue');
		var score=jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val();
		var cutoff=jQuery("#txtEtcaCutOff").val();
		var maxmarks=jQuery("#txtEtcaMaxMarks").val();
		if(presentabsent=="A")
			{			        

		      removeMandatoryFieldForComplete("grdEmplAttGrid",rowId);
			  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val("0");
			  jQuery("#cmbEtcaPresentAbsent_grdEmplAttGrid_"+rowId).val("A");
			  jQuery("#cmbEtcaResult_grdEmplAttGrid_"+rowId).val('F');
			  jQuery("#cmbEtcaResult_grdEmplAttGrid_"+rowId).attr("disabled",true); 
			}
		else {
			  makeMandatoryFieldForPending("grdEmplAttGrid",rowId);
			  jQuery("#cmbEtcaResult_grdEmplAttGrid_"+rowId).val("P");
			  jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val("0");
			  jQuery("#cmbEtcaPresentAbsent_grdEmplAttGrid_"+rowId).val("P");
			  
		} 	
		} 
		
	}); 
		

		
	
	  /*  jQuery("#"+divfilemangerkeyid).click(function(){
		   
	       var documentNo =keyid;
	       if(documentNo.length!=1)
	    	   {
	   		if(documentNo != null && documentNo != ''){
	   			var frmMode=jQuery('#frmMode').val();
	   			apMode = "create";
	   			if(frmMode=="View")
	   			   apMode = "view";
	   			fileManagerPopUp(documentNo,"YY","","","",apMode);
	   		}
	    	   }
	       else{
	    	  alert("Pls Save Attendance For Employee");
	    	  return false;
	       }
	       
	    }		
	    		); */
	   
	      jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).keydown(function () {
		  this.value = this.value.replace(/[^0-9]/g, '');
		  
		
	   }
	   );
	   
       jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).keyup(function(){  
    	 
		   var marksbsd=jQuery("#hdnMarksReqd").val();
		    var assessment=jQuery("#hdnAssCompl").val();
		    var maxmarks=jQuery("#txtEtcaMaxMarks").val();
                   //alert(1234 +"  "+maxmarks);
                  
		    var score=jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val();
		    var cutoff=jQuery("#txtEtcaCutOff").val();
		   
		    /* if(marksbsd=="Y" && assessment=="Y") 
	    	{ */
	    	 if((cutoff.length > 0) &&  (maxmarks.length > 0)) 
	    	 {	
                      if(parseInt(score)> parseInt(maxmarks))
                       {
                          popupCommonErrorMsg("Score is higher than Max Marks");
                          return false;
                       } 
	    	
	    	  if(parseInt(score) <= parseInt(maxmarks) && parseInt(score) >= parseInt(cutoff) )
	    	  {
	    		  
	    	
	    		  jQuery("#cmbEtcaResult_grdEmplAttGrid_"+rowId).val('P');
				  jQuery("#cmbEtcaResult_grdEmplAttGrid_"+rowId).attr("disabled",true); 

	    	  }
	    	  else{
	    		  
	    		  jQuery("#cmbEtcaResult_grdEmplAttGrid_"+rowId).val('F');
	    		  
	    	  }
	    	 }
	    	 else{
	    		 popupCommonErrorMsg("Please enter Cut Off & Max Marks");
	    		 jQuery("#txtEtcaScore_grdEmplAttGrid_"+rowId).val("");
	    		   return false;
	    	 }
	    	
	    		    
		  
	   });
       
}
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


/*  function frmEmplAtted_successCallBack(result) {
	if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
		popupCommonErrorMsg(result.tpmException);
	else
		alert(result.returnData.msg);
	// jQuery("#NewUniqueGrid").trigger("reloadGrid");
	jQuery('#grdEmplAttGrid').trigger('reloadGrid');
} */
  
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
}
var etcaMaxMarks = document.getElementById("txtEtcaMaxMarks");
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
 
var etcaCutOff = document.getElementById("txtEtcaCutOff");
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
	
     
</script>


<form id="frmEmplAtted" name="frmEmplAtted">
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
				 <div style="margin-left:84px; margin-top:-20px; display:none;">			  
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
<span>
<select class="easyui-text" id="cboEtcaType" name="cboEtcaType" panelHeight=80px;  style="width:  100px; height: 21px;" >
							<option value='O'>ORAL</option>
							<option value='W'>WRITTEN</option>
						</select>
						</span>
						
					<span style="margin-left:10px;">
						<input type="button" class="easyui-button" value="Save" id="btnEmpAttSave"/>
					  </span>
					  
					   <span style="margin-left:25px;">
							<input type="button" class="easyui-button" value="Close" id="btnClose"/>
						 </span>
						</div>
</td>
</tr>
</table>
</div>
<div style="margin-top:20px; margin-left:9px;">
<table id="grdEmplAttGrid">
</table>
<div id="pager"></div>
</div>


<input type="hidden" id="mode" name="mode" value="create" />
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}" />
<input type="hidden" id="hdnlocnid" name="hdnlocnid" value="${requestScope.locnid}" />
<input type="hidden" id="hdnlocaid" name="hdnlocaid" value="${loginLocnId}" />
<input type="hidden" id="hdncalendar" name="hdncalendar" value="${requestScope.keyid}" />
<input type="hidden" id="hdnSectionId" name="hdnSectionId" value="${requestScope.sectionId}" />
<input type="hidden" id="hdnJhId" name="hdnJhId" value="${requestScope.cellId}" />
<input type="hidden" id="hdnMarksReqd" name="hdnMarksReqd" value="${requestScope.markReqrd}" /> 
<input type="hidden" id="hdnAssCompl" name="hdnAssCompl" value="${requestScope.assmCompl}" />
<input type="hidden" id="hdnTopicId" name="hdnTopicId" value="${requestScope.topicId}" />
<input type="hidden" id="hdnCuttoff" name="hdnCuttoff" value="${requestScope.Cuttoff}" />
<input type="hidden" id="hdnMaxMarks" name="hdnMaxMarks" value="${requestScope.maxmarks}" />
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}" />


<input type="hidden" id="hdnAssesType" name="hdnAssesType" value="${requestScope.assesType}" />
<!-- request.setAttribute("flid",flid);
					request.setAttribute("cellId",cellId);
					request.setAttribute("sectionId",sectionId);
					request.setAttribute("locnid",locationId);
					request.setAttribute("keyid",Calendarkeyid);
					request.setAttribute("markReqrd",markReqrd);
					request.setAttribute("assmCompl",assmCompl);
	         request.setAttribute("maxmarks" , maxmarks);
				 	request.setAttribute("Cuttoff" , Cuttoff); -->

</form>