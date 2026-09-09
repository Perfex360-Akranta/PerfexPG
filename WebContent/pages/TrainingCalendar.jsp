  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 <script>
 var relatedTo = jQuery('#hdnRelTo').val();
 jQuery(document).ready(function(){
	 initialiseForm('frmTrngCalendar');
	 jQuery('#submitForm').val('frmTrngCalendar'); // set the id of form to submit
	 formatDateBox('dteBachTilldate','dd-MMM-yyyy');
	 formatDateBox('dteProgEffectiveFrom','dd-MMM-yyyy');
	 jQuery('#dteProgMonth').datebox({  
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	 });
	 	 	 
	 fillComboBox("frmTrngCalendar","cmbProgFunction","trade.commonFilter");
	 fillComboBox("frmTrngCalendar","cmbProgUniquepos","roleMst.commonFilter");
	 fillComboBox("frmTrngCalendar","cmbFtlkFacultyid","facultyCombo.commonFilter"); 
	 fillComboBox("frmTrngCalendar","cmbBachVenuKeyid","entVenueCombo.entbatch");
	 fillComboBox("frmTrngCalendar","cmbProgTgtmKeyid","tgtGroup.commonFilter");
	 fillComboBox("frmTrngCalendar","cmbProgFrequency","combo_pmsdwhtfreq.prv","",false);
		var factId = jQuery("#frmTrngCalendar input[id='factory']").val();
		var sectionId = jQuery("#frmTrngCalendar input[id='section']").val();
		var cellId = jQuery("#frmTrngCalendar input[id='cell']").val();
		var machId = jQuery("#frmTrngCalendar input[id='machine']").val();
		var flid = jQuery("#frmTrngCalendar input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		loadFunctionalLocation("trngCalfunLocation","functionalLoc.commonFilter","trngCalfunLocationValues","frmTrngCalendar",dataStr);
 
		fillComboBox("frmTrngCalendar","cmbProgCode","topic_fillcombo.tcl?&flId="+flid+"&relTo="+relatedTo);
		numericTextBox('txtProgMaxDuration');
		jQuery("#cmbProgCode").combobox({onRequest:function( ){
		    var flid = jQuery("#frmTrngCalendar input[id='flid']").val();
			return  "&flId="+flid+"&relTo="+relatedTo;
			
	  	}});
		jQuery("#cmbProgUniquepos").combobox({onRequest:function( ){
		    var flid = jQuery("#frmTrngCalendar input[id='flid']").val();
	   	 	var cmbProgCode =  getFieldValue("cmbProgCode"); 
			return "&topicId="+cmbProgCode+"&flid="+flid;
			
	  	}});      	
		
		jQuery('#btnFacultySave').click(function(){
			var facultyid = getFieldValue("cmbFtlkFacultyid");
			if(facultyid.trim().length>0)
				saveForm("frmTrngCalendar","trngcalendarEntry_save.tcl");
			else
				alert("Select Faculty");
				
		});
		
		jQuery('#btnBatchsave').click(function(){
			 
			var date = getFieldValue("dteBachTilldate");
			var unikpostion = getFieldValue("cmbRtlkRtalKeyid");
			
			if(unikpostion.trim().length>0)
			{
				if( date.trim().length>0  )
					saveForm("frmTrngCalendar","trngcalendarEntry_save.tcl");
				else
					alert("Select Batch Date");
			}
			else
			{
				alert("Select Unique Position");				
			}
		});
		
		//var topicId = jQuery('#txtProgTopicId').val();
		var topicId = jQuery('#txtProgKeyid').val();		
		viewGrid("?q=2&topicId="+topicId);
		
		if(jQuery('#chkProgRepeatedProgram').is(':checked') == true)
		{	
 			jQuery('#effectiveFrom').addClass('mandatory-lbl');
 			enableFields("dteProgEffectiveFrom");
		}
 		else{ 
 			disableField("frmTrngCalendar","dteProgEffectiveFrom");	
 			jQuery('#effectiveFrom').removeClass('mandatory-lbl');
 			setFieldValue("dteProgEffectiveFrom", "","frmTrngCalendar");
 			//jQuery("#"+fieldId).datebox("clear");
 			
 		}
		numericTextBox('txtBachMaxsize');

	   var freqChange=jQuery("#FreqChange").val();
	   if(freqChange.trim().length > 0 &&  false == freqChange ||"false" == freqChange ){ 
 		      if(jQuery('#chkProgRepeatedProgram').is(':checked') == true){
 		    	disableField("frmTrngCalendar","dteProgEffectiveFrom");
 		    	disableField("frmTrngCalendar","cmbProgFrequency");
 	 	    }
		       
		   }
	   else {
		      //  alert("FreqChange else block");
		       enableFields("cmbProgFrequency");
		       enableFields("dteProgEffectiveFrom");
		   }
	   

		 fillWithCurrentDate("spnBachFromTime");
		 fillWithCurrentDate("spnBachTillTime");
          
         
		spinnerKeyPress('spnBachFromTime');
		spinnerKeyPress('spnBachTillTime');
		 	
		 spinnerChange('spnBachFromTime','fillTillTime');
	     spinnerUp('spnBachFromTime','fillTillTime');
	     spinnerDown('spnBachFromTime','fillTillTime');
	     spinnerKeyPress('spnBachTillTime');	
		 spinnerChange('spnBachTillTime','getTimediff');
	     spinnerUp('spnBachTillTime','getTimediff');
	     spinnerDown('spnBachTillTime','getTimediff');
	    
		var progDate =getFieldValue('dteProgMonth');
		progDate = progDate.toUpperCase();
		//setTimeout(function(){setFieldValue("dteBachTilldate",'01-' + progDate,"frmTrngCalendar");},200);
		 
 });
 function fillTillTime(){ 
		var fromTime= getFieldValue("spnBachFromTime");
		var progDuration = jQuery('#txtProgMaxDuration').val();
		 
		if(progDuration.trim().length>0){
			var tillTime = addMinutes(fromTime+":00",progDuration);
			jQuery('#spnBachTillTime').val(tillTime);
		}
	}
	 function getTimediff(){ 
		 var fromTime= getFieldValue("spnBachFromTime");
		 var tillTime= getFieldValue("spnBachTillTime");
		 var batchMonth = getFieldValue("dteBachTilldate");
		   
		 batchMonth =  batchMonth.replace('-',' ');
		 batchMonth =  batchMonth.replace('-',',');
		 
		 var difference = (Date.parse(batchMonth+" "+tillTime) - Date.parse(batchMonth+" "+fromTime)) / 60000;
		 jQuery('#txtProgMaxDuration').val(difference);
	 }
	 function addMinutes(time, minsToAdd) {
		
		  function D(J){ return (J<10? '0':'') + J;};
		  var piece = time.split(':');
		  var mins = piece[0]*60 + +piece[1] + +minsToAdd;
		  return D(mins%(24*60)/60 | 0) + ':' + D(mins%60);  
		} 
 function btnEmpFormater(id, options, rowObject)
 {					
	 	var rowId = options.rowId;
	 	var gridId = options.gid;
	 	return '<input type="button" class="easyui-button" id="addEmployee_'+gridId+'_'+rowId+'" value="..." onclick="addBachEmployee(\''+rowId + '\',\''+gridId + '\');"/>';
	 
 }

 function frmTrngCalendar_FuntLocHierarchy_SuccessCallBack(keyIds)
 {	  
	    var flid = keyIds.flId;
	 	var cmbProgCode =  getFieldValue("cmbProgCode"); 
	 	reloadCombo("frmTrngCalendar","cmbProgCode","topic_fillcombo.tcl?&flId="+flid);
		reloadCombo("frmTrngCalendar","cmbProgUniquepos","roleMst.commonFilter?&topicId="+cmbProgCode+"&flid="+flid );
 }
 function addBachEmployee(rowId,gridId){
	 var rowData = jQuery("#batchGrd").jqGrid('getRowData',rowId);
	 var bachId =rowData.BACH_KEYID;
	 var bachDate = rowData.Session;
	 var uniquePositionId = getFieldValue("cmbProgUniquepos");
	 if(uniquePositionId.trim().length>0)
	    LoadPopUp("divAddbatchEmp","AddbatchEmp_input.tcl?&batchId="+bachId+"&uniqPosKeyid="+uniquePositionId+"&bachDate="+bachDate, true,"45%","78%","0px","20%", "multiSelectOk_Callback","Batch Employee Link");
	 else
		 alert('Select Unique Position');
 }
 
 function viewGrid(filterString){
	 	//alert(filterString);
	 	var progDate =getFieldValue('dteProgMonth');
	 	var proguniqPos = getFieldValue("cmbProgUniquepos");
	 	filterString+= "&progMonth="+progDate+"&uniqPos="+proguniqPos;
	 	processGridnew("facultyLink_input.tcl", filterString, "facultyGrd", "facultyPager"," ", "facddoubleclickGrid", "", "");
		processGridnew("batchMst_input.tcl", filterString, "batchGrd", "batchPager"," ", "bachdoubleclickGrid", "", "");
 }
 function facddoubleclickGrid(id){ 
	 var rowData = jQuery("#facultyGrd").jqGrid('getRowData',id);
	 var facultyId =rowData.FTLK_KEYID;
	 var empId =rowData.EMPM_KEYID;
	 jQuery('#txtFtlkKeyid').val(facultyId);
	 setFieldValue("cmbFtlkFacultyid",empId,"frmTrngCalendar");
 }
 function bachdoubleclickGrid(id){
	 var rowData = jQuery("#batchGrd").jqGrid('getRowData',id);
	 var bachId =rowData.BACH_KEYID;
	 var session =rowData.Session;
	 var permStrength =rowData.MAXSZE;
	 var venuid =rowData.VENUID;
	 var bachName = rowData.BACH_NAME;
	 var prtsKeyid = rowData.PRTS_KEYID;
	 setFieldValue("cmbBachVenuKeyid",venuid,"frmTrngCalendar");
	 jQuery('#txtBachName').val(bachName);
	 jQuery('#txtBachKeyid').val(bachId); 
	 jQuery('#txtBachMaxsize').val(permStrength);
	 jQuery('#txtPrtsKeyid').val(prtsKeyid);
	 setFieldValue("dteBachTilldate",session,"frmTrngCalendar");
	 
 }
 function frmTrngCalendar_successsCallback(result){
	 //alert(result.successData.keyId);
	 if(result.successData.keyId.trim().length>0){ 
		 jQuery('#txtProgKeyid').val(result.successData.keyId);
		 //jQuery('#txtProgCode').val(result.successData.keyId);
		 viewGrid("?q=2&topicId="+result.successData.keyId);
		 
		 
		 if (jQuery('#txtEcalkeyid').val().length<2)
		 	jQuery('#txtEcalkeyid').val(result.successData.calKeyid);
		 
		 var facSaved = jQuery('#txtIsFacultyLink').val( );
		 var batchSaved = jQuery('#txtIsbatch').val( );
		 if( facSaved.trim() == "Y")
		 {
			// clearField('txtIsFacultyLink');
			 clearField('cmbFtlkFacultyid');
			 clearField('txtFtlkKeyid');
			  clearField('txtIsbatch');
			  clearField('txtBachKeyid');
			  clearField('txtIsFacultyLink');

		 }
		  if(batchSaved.trim() == "Y"){
			  clearField('dteBachTilldate');
			  clearField('txtIsbatch');
			  clearField('txtBachKeyid');
			  /*
			  clearField('txtBachMaxsize');
			  clearField('cmbBachVenuKeyid');
			  clearField('txtBachKeyid');*/
		 }
	 }
 }
 function frmTrngCalendar_deleteSuccessCallback(result){
	/* alert(result.successData.msg);
	 navigateToPrevForm();*/
	 var keyid=result.successData.TmkmKeyid;
		var tkeyid=result.successData.TKeyid;
		if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined"){
			var r = confirm("Are You Sure To Delete?");
			if(r==true)
			 {
		      alert(result.successData.msg);
	   		  clearForm('frmTaskMappingKsa'); 
	          //navigateToNextForm("TaskMappingKsa_input.tmks","Task Master"); 
	   		  navigateToPrevForm();
			 }
			else{
				   return false;
				}		 	 
		   }	
		else if(tkeyid!=null && tkeyid !=" " && tkeyid !="" && tkeyid !="undefined"){
			   alert(result.successData.msg);
			}
 }
	
 function BtnFormatterDelete(id, options, rowObject)
 {					
 	var rowId = options.rowId;
 	var gridId = options.gid;

 	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
 }
function deleterec(rowid,gridId){
	var keyId;
	if("facultyGrd"==gridId)
		keyId = getGridCell(gridId,rowid,'FTLK_KEYID');
	else
		keyId = getGridCell(gridId,rowid,'BACH_KEYID');
	var progId = getFieldValue('cmbProgCode');
	processAjaxCalls('deleteDetail.tcl','&keyid='+keyId+'&gridId='+gridId+"&progId="+progId,'deleteDetail_onsuccessCallBack','deleteDetail_onerrorCallBack');
}
function deleteDetail_onsuccessCallBack(result){ 
  var r = confirm("Are You Sure To Delete?");
  if(r){
	  alert(result.msg);
      jQuery("#"+result.gridid).trigger("reloadGrid");
       //alert(result.successData.msg);
      }
  else{
	  return false;
	  } 
}
 function frmTrngCalendar_beforeSubmit(){
	 var mstKey = jQuery("#txtProgTopicId").val();
	
	 var sessionDate = getFieldValue("dteBachTilldate");
	 if( sessionDate.trim().length > 0) 
	 	jQuery('#txtIsbatch').val("Y");
	 var venueid = getFieldValue("cmbBachVenuKeyid");
	 if( venueid.trim().length > 0 ) 
	 	jQuery('#txtIsbatch').val("Y");
	 var batchSize = getFieldValue("txtBachMaxsize");
	 
	 if( sessionDate.trim().length > 0 ) 
	 { 
		 if(venueid.trim().length <= 0 )
		 { 
			 showValidationErrorMsg("cmbBachVenuKeyid","Select Venue");
			 return false;	 
		 }
	 
	 }
	 
	 if(batchSize.trim().length > 0) 
	 	jQuery('#txtIsbatch').val("Y");
	
	 
	 if(batchSize.trim().length <= 0 &&  venueid.trim().length <= 0  && sessionDate.trim().length <= 0)
		 {
		 //alert("bac");
		 
		 jQuery('#txtIsbatch').val(" ");
		 }
	 var facId = getFieldValue("cmbFtlkFacultyid");
	 if( facId.trim().length > 0){
		 jQuery('#txtIsFacultyLink').val('Y');
	 }else{
		 //alert(2);
		 jQuery('#txtIsFacultyLink').val(' ');
	 }
	 var progname = getComboBoxText('cmbProgCode');
	 jQuery('#txtProgName').val(progname);
	 if(mstKey.trim().length>0){
		 var r = confirm("Data changed, Do you want to Proceed");
		 if(r){
			 return true;
		 }else{
			 return false;
		 }
	 }
	 
	
 }
/* function frmTrngCalendar_beforeDelete(){
	 var mstKey = jQuery("#txtProgTopicId").val();
	 var r = confirm("Are you sure to Delete");
	 if(r){
		 if(mstKey.trim().length<=0){
			clearForm("frmTrngCalendar");
			return false;
		  }
		 return true;
	 }else{
		 return false;
	 }	  
 }*/
 function ucfirst(str,force){
     str=force ? str.toLowerCase() : str;
     return str.replace(/(\b)([a-zA-Z])/,
              function(firstLetter){
                 return   firstLetter.toUpperCase();
              });
}
 function dteBachTilldate_onChange(){
	  
	 var sessionDate = getFieldValue('dteBachTilldate');
	  sessionDate = sessionDate.substring(sessionDate.indexOf('-')) ;
	  sessionDate = sessionDate.substring(1);
	  sessionDate = sessionDate.toUpperCase();
	  var progDate =getFieldValue('dteProgMonth');
	  progDate = progDate.toUpperCase();
	  var progMonth=ucfirst(progDate.substring(0,3),1);
	  var sessionMonth=ucfirst(sessionDate.substring(0,3),1);
	  //alert(progMonth +"  "+sessionMonth);	   
	  if(parseInt(getIndex(sessionMonth) )< parseInt(getIndex(progMonth)))
		{
		  alert('Session Date Should Fall in Program Month ');		  
		  setTimeout(function(){jQuery("#dteBachTilldate").datebox("clear");},800);
		} 	  
	 }
  
 function dteBachTilldate_onSelect(){
	 jQuery("#venuLbl").addClass("mandatory-lbl");
 }
 function  dteProgEffectiveFrom_onSelect(){
	  
	 var effectiveDate = getFieldValue('dteProgEffectiveFrom');
	  effectiveDate = effectiveDate.substring(effectiveDate.indexOf('-')) ;
	  effectiveDate = effectiveDate.substring(1);
	  effectiveDate = effectiveDate.toUpperCase();
	  var progDate =getFieldValue('dteProgMonth');
	  progDate = progDate.toUpperCase();
	  var progMonth=ucfirst(progDate.substring(0,3),1);
	  var effectiveMonth=ucfirst(effectiveDate.substring(0,3),1);
	  //alert(progMonth +"  "+sessionMonth);	   
	  if(parseInt(getIndex(effectiveMonth)) < parseInt(getIndex(progMonth)))
		{
	      alert('Effective From Date Should Fall in Program Month ');		  
		  setTimeout(function(){jQuery("#dteProgEffectiveFrom").datebox("clear");},800);
		} 	  
	 }
 	jQuery('#chkProgRepeatedProgram').click(function(){
 		if(jQuery('#chkProgRepeatedProgram').is(':checked') == true)
		{	
 			jQuery('#effectiveFrom').addClass('mandatory-lbl');
 			enableFields("dteProgEffectiveFrom");
 			enableFields("cmbProgFrequency");
		}
 		else{
 			jQuery('#effectiveFrom').removeClass('mandatory-lbl');
 			clearField("dteProgEffectiveFrom");
 			clearField("cmbProgFrequency");
 			disableField("frmTrngCalendar","dteProgEffectiveFrom");
 			disableField("frmTrngCalendar","cmbProgFrequency");
 		}
 	});
 	jQuery('#chkProgMaterialY').click(function(){
 		if(jQuery('#chkProgMaterialY').is(':checked') == true)
		{	
 			jQuery('#chkProgMaterialN').attr('checked',false); 
		}
 		else{
 			jQuery('#chkProgMaterialN').attr('checked',true);
 		}
 	});
 	jQuery('#chkProgMaterialN').click(function(){
 		if(jQuery('#chkProgMaterialN').is(':checked') == true)
		{	
 			jQuery('#chkProgMaterialY').attr('checked',false); 
		}
 		else{
 			jQuery('#chkProgMaterialY').attr('checked',true);
 		}
 	});
 	jQuery("#dteProgMonth").datebox({onSelect:function(date){
 		  
 		 var progkey = getFieldValue("cmbProgCode");
 		 var proguniqPos = getFieldValue("cmbProgUniquepos");
 		 if((progkey.trim().length>0)&&(proguniqPos.trim().length>0))
 		   progData();
 		 var serverTime = srvTime();
 		 var currentTime = new Date(serverTime);
 		 var day = currentTime.getDate();
		var spnTillTime = getFieldValue("spnBachTillTime"); 		 
 		var sessionDate = getFieldValue("dteBachTilldate");
 		if (sessionDate.length<2) {
 			var progDate = getFieldValue("dteProgMonth");
 			setTimeout(function(){
 	 			setFieldValue("dteBachTilldate",day+'-' + progDate,"frmTrngCalendar");
 	 			setFieldValue("spnBachTillTime",addMinutes(spnTillTime+":00", 30),"frmTrngCalendar");
 	 			getTimediff();
 	 			},200);

 			
 		}
 		 // processAjaxCalls("progExistData_input.tcl?progId="+progkey+"&progMonth="+progMOnth,"","savedRecord_onsuccesscallback",'savedRecord_OnError');
 			},
 			onChange:function(date){	
 				 var sessionDate = getFieldValue('dteBachTilldate');
 				  sessionDate = sessionDate.substring(sessionDate.indexOf('-')) ;
 				  sessionDate = sessionDate.substring(1);
 				  sessionDate = sessionDate.toUpperCase();
 				  var progDate =getFieldValue('dteProgMonth');
 				  progDate = progDate.toUpperCase();
 				  var progMonth=ucfirst(progDate.substring(0,3),1);
 				  var sessionMonth=ucfirst(sessionDate.substring(0,3),1);
 				  //alert(progMonth +"  "+sessionMonth);	
 				  if(sessionDate.trim().length>0){    
 				  if(parseInt(getIndex(sessionMonth) )< parseInt(getIndex(progMonth)))
 					{
 					  alert('Session Date Should Fall in Program Month ');		  
 					  setTimeout(function(){jQuery("#dteProgMonth").datebox("clear"); jQuery("#dteBachTilldate").datebox("clear");},800);
 					}
 				  } 	  
	    } 
	});
function frmTrngCalendarcmbProgUniquepos_onSelect(record)
 {
   var progkey = getFieldValue("cmbProgCode");
   var progMonth = getFieldValue("dteProgMonth");
   if((progMonth.trim().length>0)&&(progkey.trim().length>0))
	   progData();
   // processAjaxCalls("progExistData_input.tcl?progId="+record.id+"&progMonth="+progMonth,"","savedRecord_onsuccesscallback",'savedRecord_OnError');
 }
 function frmTrngCalendarcmbProgCode_onSelect(record)
 {
   jQuery("#cmbProgCode").val(record.id);
   processAjaxCalls("trngProgSelect_Select.tcl?progId="+record.id,"","selectedReport_onsuccesscallback");
   var progMonth = getFieldValue("dteProgMonth"); 
   var proguniqPos = getFieldValue("cmbProgUniquepos");
   if((progMonth.trim().length>0)&&(proguniqPos.trim().length>0))
	   progData();
   // processAjaxCalls("progExistData_input.tcl?progId="+record.id+"&progMonth="+progMonth,"","savedRecord_onsuccesscallback",'savedRecord_OnError');
 }
 
 function progData(){ 
	 var progkey = getFieldValue("cmbProgCode");
	 var progMOnth = getFieldValue("dteProgMonth");
	 var proguniqPos = getFieldValue("cmbProgUniquepos");
	  if(jQuery('#txtProgTopicId').val().length<2) {
		 jQuery('#txtProgTopicId').val(progkey);
		 //alert(progkey);
	 }
	 //alert("progId="+progkey+"&progMonth="+progMOnth+"&uniquePos="+proguniqPos);
    	processAjaxCalls("progExistData_input.tcl?progId="+progkey+"&progMonth="+progMOnth+"&uniquePos="+proguniqPos,"","savedRecord_onsuccesscallback",'savedRecord_OnError');

 }
 function frmTrngCalendarcmbBachVenuKeyid_onSelect(record)
 {
   processAjaxCalls("getPermitedStrength_Select.tcl?venuId="+record.id,"","venuePermStrength_onsuccesscallback");
 }
 function venuePermStrength_onsuccesscallback(result){
	  
	jQuery('#txtBachMaxsize').val(result );	 
 }
function savedRecord_OnError(result){ 
	 //setFieldValue("dteProgMonth"," ","frmTrngCalendar");
	 
	 /* setFieldValue("cmbProgUniquepos"," ","frmTrngCalendar");
	 setFieldValue("txtProgMaxDuration"," ","frmTrngCalendar");
	  */ 
	 
	  setFieldValue("dteProgEffectiveFrom", " ","frmTrngCalendar");
	 setFieldValue("cmbProgFunction", " ","frmTrngCalendar");
	   
		 jQuery('input:checkbox[id=chkProgMaterialY]').attr('checked',false);
	  
		 jQuery('input:checkbox[id=chkProgMaterialN]').attr('checked',false);
	 /*progFunction,bsdlKeyid,batchmaster,progUniquepos,progTgtmKeyid,progMaxDuration,
	 progEffectiveFrom,facultyTopicLink,progTrarKeyid,progCreatedby,progMonth,progBenifit,
	 progModifiedon,progContactInfo,progPurpose,progRemarks,progType,progKeyid,progEffectiveTill,
	 progTopicId,progTargetSkills,progCreatedon,progActive,progFrequency,prtrkeyid,progSpokeKeyid,
	 progCode,progName,progIsEvaluationNeed,progMaterialReady,progMinDuration,progRepeatedProgram,
	 ecalkeyid,saveArray,entTlBatchSchedule*/
	 //jQuery('#txtProgTopicId').val(" ");
	 jQuery('#txtPrtrkeyid').val(" ");
	 jQuery('#txtEcalkeyid').val(" ");
	 jQuery('#txtProgName').val(" ");
	 jQuery('#txtBsdlKeyid').val(" ");
	 
	 viewGrid("?q=2&topicId=");
}
 function savedRecord_onsuccesscallback(result){ 

	 /* setFieldValue("dteProgMonth",result[0].progMonth,"frmTrngCalendar");
	 setFieldValue("cmbProgUniquepos",result[0].progUniquepos,"frmTrngCalendar");
	 setFieldValue("txtProgMaxDuration",result[0].progMaxDuration,"frmTrngCalendar");
	  */
	  
	 if("01-Jan-1801 00:00:00" != result[0].progEffectiveFrom)
	 setFieldValue("dteProgEffectiveFrom", result[0].progEffectiveFrom,"frmTrngCalendar");
	 setFieldValue("cmbProgFunction", result[0].progFunction,"frmTrngCalendar");
	 if(result[0].progMaterialReady=="Y") 
		 jQuery('input:checkbox[id=chkProgMaterialY]').attr('checked',true);
	 else if(result[0].progMaterialReady=="N")
		 jQuery('input:checkbox[id=chkProgMaterialN]').attr('checked',true);
	 /*progFunction,bsdlKeyid,batchmaster,progUniquepos,progTgtmKeyid,progMaxDuration,
	 progEffectiveFrom,facultyTopicLink,progTrarKeyid,progCreatedby,progMonth,progBenifit,
	 progModifiedon,progContactInfo,progPurpose,progRemarks,progType,progKeyid,progEffectiveTill,
	 progTopicId,progTargetSkills,progCreatedon,progActive,progFrequency,prtrkeyid,progSpokeKeyid,
	 progCode,progName,progIsEvaluationNeed,progMaterialReady,progMinDuration,progRepeatedProgram,
	 ecalkeyid,saveArray,entTlBatchSchedule*/
	 //jQuery('#txtProgTopicId').val(result[0].progKeyid );
	 
	 jQuery('#txtProgKeyid').val(result[0].progKeyid );
	 jQuery('#txtPrtrkeyid').val(result[0].prtrkeyid );
	 jQuery('#txtEcalkeyid').val(result[0].ecalkeyid );
	 jQuery('#txtProgName').val(result[0].progName );
	 jQuery('#txtBsdlKeyid').val(result[0].bsdlKeyid );
	
	 setTimeout(function(){viewGrid("?q=2&topicId="+result[0].progKeyid );},200);
	 
	 
 }
 function selectedReport_onsuccesscallback(result)
 { 
	 //alert("result  "+result!=null && result!="" && result!=" " );
	 /* if(result.length>0)
	     loadFunctionalLocation("trngCalfunLocation","functionalLoc.commonFilter","trngCalfunLocationValues","frmTrngCalendar","&flid="+result[0][0]);
	 else{
		 loadFunctionalLocation("trngCalfunLocation","functionalLoc.commonFilter","trngCalfunLocationValues","frmTrngCalendar","&flid=");
        } */
}
 </script>
 <form id="frmTrngCalendar">
 <div id='wrapper'>
	 <table   cellspacing="5" style="width:80%"  >
	 	<tr>
	 		<td colspan='5'>
	 			<div  id="frmtrngCalFuntKeyIds"  >
					<div style="float: left;padding-right: 20px;">
					<input type="hidden" id="factory" name="cmbFactoryid" value=" "  ></input>			
					<input type="hidden" id="section" name="cmbSectionid" value=" " ></input>
					<input type="hidden" id="cell" name="cmbCellid" value=" " ></input>
					<input type="hidden" id="elementId" name="elementId" value=" " ></input>
					<input type="hidden" id="elementType" name="elementType" value=" " ></input>
					<input type="hidden" id="machine" name="cmbEquipmentid1" value=" "></input>
					<input type="hidden" id="flid" name="cmbProgTrarKeyid" value="${requestScope.entTlProgramMst.progTrarKeyid}  "  ></input>
					</div>
				<div id="trngCalfunLocation" style="width: 90%; "></div>
				</div>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td>
	 			 
	 			<div><label  class="mandatory-lbl">Month</label></div>
 				<div class="easyui-paddingbfpx" >
			 		<input id="dteProgMonth" name="dteProgMonth" class="easyui-datebox" style="width:85px;" value="${requestScope.entTlProgramMst.progMonth} "/>
				</div>
	 		</td>
	 		<td style="position:absolute;">
		 		<div >
		 		<label id=" "  class="mandatory-lbl" >Topic</label>
		 		</div>
				<div class="easyui-paddingbfpx"  >
				<input id="cmbProgCode" name="cmbProgCode" class="easyui-combobox" style="width:205px;" value="${requestScope.entTlProgramMst.progCode}">
				<span class="tpm-errormsg" id="err_cmbProgCode" style=""></span>
				</div>
	 		</td>
	 		<%-- <td>
		 		<div>
			 		<label class="mandatory-lbl">Target Group</label>
		 		</div>
		 		<div class="easyui-paddingbfpx">
		 			<input id="cmbProgTgtmKeyid" name="cmbProgTgtmKeyid" class="easyui-combobox" style="width:205px;" value="${requestScope.entTlProgramMst.progTgtmKeyid}"/>
		 		</div>
	 	  	</td> --%>
	 		<td style="">
		 		<div><label id=" "  class="mandatory-lbl" >Unique Position</label></div>
				<div class="easyui-paddingbfpx" >
				<input id="cmbProgUniquepos" name="cmbProgUniquepos" class="easyui-combobox" style="width:220px;"  value="${requestScope.entTlProgramMst.progUniquepos}"/>
				</div>
	 		</td>
	 		<td style=" " colspan='2'>
		 		<div ><label id=" " >Function</label></div>
				<div class="easyui-paddingbfpx" >
				<input id="cmbProgFunction" name="cmbProgFunction" class="easyui-combobox" style="width:175px;"  value="${requestScope.entTlProgramMst.progFunction}" />
				</div>
	 		</td>
	 		
	 		
	 	</tr>
	 	<tr>
	 		<td>
	 			<div style=""><label>Material Ready</label></div>
	 			<div style="margin-left:3px;">
					<input type="checkbox" id="chkProgMaterialY"   name="chkProgMaterialReady"  <c:out value = "${requestScope.entTlProgramMst.progMaterialReady == 'Y' ? ' checked':' '}"/> value="Y"/><label style="margin-left:3px;">Yes</label>
					<span style="margin-left:3px;">
					<input type="checkbox" id="chkProgMaterialN" value="N" name="chkProgMaterialReady" <c:out value = "${requestScope.entTlProgramMst.progMaterialReady == 'N' ? ' checked':' '}"/> /><label style="margin-left:3px;">No</label>
				</span>
				</div>
	 		</td>
	 		<td>
	 			<div ><label id=" " >Duration(mins)</label></div>
				<div class="easyui-paddingbfpx" >
				<input id="txtProgMaxDuration" maxlength="3" name="txtProgMaxDuration" class="easyui-text"  style="width:85px;text-align:right;height:21px;" onblur="fillTillTime();" value="${requestScope.entTlProgramMst.progMaxDuration}" />
					<span style="margin-left:8px;">
					<input type="checkbox"  id="chkProgRepeatedProgram" name="chkProgRepeatedProgram" <c:out value = "${requestScope.entTlProgramMst.progRepeatedProgram == 'Y' ? ' checked':' '}"/> value="Y"/>
		 			<label>Repeated Program</label></span>
				
				</div>
	 		</td>
	 		<td>
	 		
		 		<div style=" ">
		 			<label id='effectiveFrom'>Effective From</label>
		 			<span style="margin-left:13px;"><label>Frequency</label></span>
		 		</div>
	 			<div style="margin-left:3px;position:relative; ">
			 		<input id="dteProgEffectiveFrom" name="dteProgEffectiveFrom" class="easyui-datebox" style="width:85px;" value="${requestScope.entTlProgramMst.progEffectiveFrom}" />
			 		<span style="position:absolute;margin-left:5px; ">
			 			<input id="cmbProgFrequency" name="cmbProgFrequency" class="easyui-combobox"  style="width:115px;"   value="${requestScope.entTlProgramMst.progFrequency }" > 				
	 				</span>
	 			</div>
	 		</td>
	 		
	 		<td  style="width:80;">
	 			<div ><label class=""  id='venuLbl' >Venue</label></div>
	 			<div class=" " style="margin-top:3px;">
	 				<input id="cmbBachVenuKeyid" name="cmbBachVenuKeyid" class="easyui-combobox" style="width:175px;" />
	 				<span class="tpm-errormsg" id="err_cmbBachVenuKeyid" style=""></span>
	 			</div>
	 		</td>
	 		<td  style="padding-top:1%;">
	 			<div><label class=" " >Permitted Strength </label> </div>
	 			<div class="easyui-paddingbfpx" >
		 			<input type="text" class="easyui-text" maxlength="3" id="txtBachMaxsize" name="txtBachMaxsize" style="width:105px;text-align:right;height:21px;"/>
	 			</div>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td  colspan='2'>
	 			<div ><label id=" " >Faculty</label></div>
				<div class="easyui-paddingbfpx" >
				<input id="cmbFtlkFacultyid" name="cmbFtlkFacultyid" class="easyui-combobox" style="width:245px;"  />
				<span style="margin-left:-2px;">
				 		<input type="button" class="easyui-button" value="Add" id="btnFacultySave"/>
				</span>
				</div>
	 		</td>
	 		
	 		<td style="padding-left:8%;" colspan='2'>
	 			<div>
	 				<label>Session</label>
	 				<span style="margin-left:60px;"><label>From</label></span>
	 				<span style="margin-left:40px;"><label>To</label></span>
<!-- 	 				<span style="margin-left:70px;"> -->
<!-- 				 		<label class=" " >Permitted Strength</label> -->
<!-- 				 	</span> -->
	 			</div>
	 			<div class="easyui-paddingbfpx" >
				 	<input id="dteBachTilldate" name="dteBachTilldate" class="easyui-datebox" style="width:100px;"  />
					
					<span style="padding-left:10px;">
						<input  id="spnBachFromTime" name="spnBachFromTime" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /> 
					</span>
					<span style="padding-left:10px;">
						<input  id="spnBachTillTime" name="spnBachTillTime"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /> 
					</span>

					<span style="margin-left:20px;">
						<input type="button" class="easyui-button" value="Add" id="btnBatchsave"/>
<!-- 					 		<input type="text" class="easyui-text" value=" " id="txtBachMaxsize" name="txtBachMaxsize" style="width:117px;text-align:right;"/> -->
					 </span>								 	
				</div>
	 		</td>
	 		<td>
	 			<div  class=" ">
<!-- 	 				<label class=" " >Venue</label>  -->
	 				
	 			</div>
    			<div  class=" " style=" ">
<!--      				<input id="cmbBachVenuKeyid" name="cmbBachVenuKeyid" class="easyui-combobox" style="width:245px;" /> -->
	    			<span style=" ">
					 	
					 </span>
				 </div>
	 		</td>
	 	</tr>
	 	</table>
	 	
	 	<table style='width:84%;'>
	 	<tr>
	 		<td colspan=" ">
	 			<table id="facultyGrd"><tr><td></td></tr></table>
	 			<div id="facultyPager"></div>
	 		</td>
	 		<td colspan=" ">
	 			<table id="batchGrd"><tr><td></td></tr></table>
	 			<div id="batchPager"></div>
	 		</td>
	 	</tr>
	 </table>
	  
 	</div>
 	<input type="hidden" id="mode" name="mode"   />
 	<input type="hidden" id="txtIsFacultyLink" name="txtIsFacultyLink" value=" "/>
 	<input type="hidden" id="txtIsbatch" name="txtIsbatch" value=" "/>
 	<input type="hidden" id="txtProgKeyid" name="txtProgKeyid" value="${requestScope.entTlProgramMst.progKeyid} "/>
 	<input type="hidden" id="txtProgTopicId" name="txtProgTopicId" value="${requestScope.entTlProgramMst.progCode} "/>
 	<input type="hidden" id="txtBachKeyid" name="txtBachKeyid" />
 	<input type="hidden" id="txtFtlkKeyid" name="txtFtlkKeyid" />
 	<input type="hidden" id="txtPrtrkeyid" name="txtPrtrkeyid" value="${requestScope.entTlProgramMst.prtrkeyid} "/>
 	<input type="hidden" id="txtEcalkeyid" name="txtEcalkeyid" value="${requestScope.entTlProgramMst.ecalkeyid} "/>
 	<input type="hidden" id="txtProgName" name="txtProgName" />
 	<input type="hidden" id="txtBachName" name="txtBachName" style="text-transform: capitalize;"/>
 	<input type="hidden" id="txtPrtsKeyid" name="txtPrtsKeyid" />
 	<input type="hidden" id="txtBsdlKeyid" name="txtBsdlKeyid"  value="${requestScope.entTlProgramMst.bsdlKeyid} "/>
 	<input type="hidden" id="FreqChange" name="FreqChange"  value="${requestScope.FrequeEnable}"/>
 	<input type="hidden" id='hdnRelTo' value="${requestScope.frmMode}"/>
 </form>