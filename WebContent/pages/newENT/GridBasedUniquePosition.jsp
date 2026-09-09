
<script type='text/javascript'>

function fillTillTime(){ 
	var fromTime= getFieldValue("spnsessionFromTime");
	//alert(fromTime);
	var progDuration = jQuery('#hdnTrngDuration').val();
	// alert(progDuration);
	 var hours=progDuration*60;
	// alert("hours"+hours);
	if(progDuration.trim().length>0){
		var tillTime = addMinutes(fromTime+":00",hours);
		//alert("tillTime"+tillTime);
	//	jQuery('#spnsessionTillTime').val(tillTime);
		jQuery('#spnsessionTillTime').spinner('setValue',tillTime);
	}
}

jQuery(document).ready(function(){
	initialiseForm('frmGrdUniqPos');
	jQuery('#submitForm').val('frmGrdUniqPos');

	var flid = jQuery("#frmGrdBsdTrgCal input[id='flid']").val();
     formatDateBox('dteFromDate','DD-MMM-YYYY');
	 formatDateBox('dteToDate','DD-MMM-YYYY');
	 formatDateBox('dteSessiondate','dd-MMM-yyyy');
	 fillWithCurrentDate('dteSessiondate');
	
	   fillComboBox('frmGrdUniqPos','cmbEtcmSectionid','sectionCombo.commonFilter' );
	   fillComboBox('frmGrdUniqPos','cmbEtcmJHId','cellCombo.commonFilter' );
	   fillComboBox('frmGrdUniqPos','cmbEtcfFacultyId','facultyCombo.tcl' );
	   fillComboBox('frmGrdUniqPos','cmbetcuRoleKeyid','roleMst.commonFilter');
		 var hdnKeyid=jQuery('#hdnKeyid').val();
		 var trgDate=jQuery("#hdnTrngDate").val();
		 jQuery('#dteSessiondate').datebox('setValue',trgDate);
	//	 alert(' hdnKeyid '+hdnKeyid);
	
	
	// fileManagerPopUp("","TRAINING","frmNewTraCal","btnFilManage","newtrainingcalFilemgr");
	 fillWithCurrentDate("sessionFromTime");
	 fillWithCurrentDate("sessionTillTime");
	 
		setTimeout(function() {
			 fillWithCurrentDate('spnsessionFromTime');
		},10);
	// fillWithCurrentDate('spnsessionTillTime');
	 setTimeout(function() {
		 fillWithCurrentDate('spnsessionTillTime');
	},10);
	 
//	 fillWithCurrentDate('spnsessionFromTime');
//	 fillWithCurrentDate('spnsessionTillTime');
	 
	     spinnerKeyPress('spnsessionFromTime');
		 spinnerChange('spnsessionFromTime','fillTillTime');
	     spinnerUp('spnsessionFromTime','fillTillTime');
	     spinnerDown('spnsessionFromTime','fillTillTime');
	     spinnerKeyPress('spnsessionTillTime');	
		 spinnerChange('spnsessionTillTime','getTimediff');
	     spinnerUp('spnsessionTillTime','getTimediff');
	     spinnerDown('spnsessionTillTime','getTimediff');
	 
	     
	var trngType=jQuery('#hdnTrngType').val();
	//alert(trngType+"123");
	if(trngType!="UQ"){
		readOnlyFields("cmbEtcmSectionid");
		readOnlyFields("cmbEtcmJHId");
		readOnlyFields("cmbetcuRoleKeyid");
		jQuery("#btnUPSave").attr("disabled",true).addClass("ui-state-disabled");
		jQuery("#btnMultipleUnique").attr("disabled",true).addClass("ui-state-disabled");
		jQuery("#btnUniquePosAdd").attr("disabled",true).addClass("ui-state-disabled");

		/* readOnlyFields("btnUPSave");
		readOnlyFields("btnMultipleUnique");
		readOnlyFields("btnUniquePosAdd"); */
	}
	 var url='';
	
	   var ds='?q=2';
	   viewGrid(url,ds);
	  var mode=jQuery('#hdnMode').val();
	  if(mode=="view"){
		  disableForm("frmGrdUniqPos");
	  }
	  
	  var userRoleName=jQuery('#hdnRoleName').val();
	  if(userRoleName=="ET PILLAR CHAMPION" || userRoleName=="ET PILLAR MEMBER" || userRoleName=="TPM CELL" || userRoleName=="DMT MEMBER")
	  {
	  jQuery("#btnUniquePosAdd").attr("disabled",false).removeClass("ui-state-disabled");
	  jQuery("#btnFacultyAdd").attr("disabled",false).removeClass("ui-state-disabled");

	  }
	  else{
	  jQuery("#btnUniquePosAdd").attr("disabled",true).addClass("ui-state-disabled");
	  jQuery("#btnFacultyAdd").attr("disabled",true).addClass("ui-state-disabled");

	  }
	  
	  jQuery('#btnSessionsave').click(function(){
		    var date = getFieldValue('dteSessiondate');	
		    var fromTime= getFieldValue('spnsessionFromTime');
		   // alert(fromTime);
		 	  var tillTime= getFieldValue('spnsessionTillTime');
		 	// alert(tillTime);
		 	  var keyid=jQuery('#hdnKeyid').val();//jQuery('#txtEtcmKeyid').val('ETC000000000797');
		 	  var sessionid=jQuery('#hdnSessionId').val();
		 	 // alert(keyid+'  '+sessionid);
		 	  if(keyid.length==0)
		 		  {
		 		   popupCommonErroMsg('Please save Training Calendar');
		 		   return false;
		 		  }
		 	  
		 	  else{
		      // alert(1236);
		 		if(fromTime==tillTime)
		 		 {
		 		//	alert('inside');
		 		  popupCommonErrorMsg('Please select correct from time and till time');
		 		  return false;
		 		 }
		 		else{
		 		//	alert('inside1');
		 		  processAjaxCalls('chkSessionDate.gbtc','&keyid='+keyid+'&sedte='+date+'&frmtme='+fromTime+'&totme='+tillTime+'&sesid='+sessionid,'chkSessionDate_onsuccessCallBack','chkSessionDate_onerrorCallBack');
		 		}
		 	  }
		 	 jQuery("#trgCalendarGrid").trigger("reloadGrid");
		});
	  jQuery('#btnFacultySave').click(function(){
		    var MasterKeyid=jQuery('#hdnKeyid').val();
		  //  alert(MasterKeyid +'Master Id');
			var facultyId=jQuery('#cmbEtcfFacultyId').combobox('getValue');
			if(facultyId.length==0 || facultyId==null){
				alert('Pls Add The Faculty!');
				return false;
			}
			if (MasterKeyid==null || MasterKeyid==' ' || MasterKeyid=='') {
				var ds = +'&Facultyid='+facultyId;
				//alert('ds'+ds);
				if(facultyId.trim().length>0 )
					{
					saveForm('frmGrdUniqPos','GridbasedFaculty_save.gbtc?ds='+ds,'','');
					
					 setTimeout(function(){
						 jQuery("#facultyGrid").trigger("reloadGrid");
							},850); 
					   
						
						
			//	jQuery('facultyGrid').trigger('reloadGrid');
					
					


				}
			}
			else{
				if(MasterKeyid!=null&&facultyId!=null)
					{
					  processAjaxCalls('FacultyCheck.gbtc','&keyid='+MasterKeyid+'&faclid='+facultyId,'FacultyCheck_onsuccessCallBack','FacultyCheck_onerrorCallBack');
					}
			}
		});
	  
	  
});

var sectId=jQuery('#hdnSectKeyid').val();
var flid=jQuery('#hdnFlid').val();
//alert(flid+" flid "+sectId);
if(sectId!=null||sectId!=""||sectId!="undefined"){
	jQuery('#cmbEtcmSectionid').combobox('setValue',sectId);
	reloadCombo("frmGrdUniqPos","cmbEtcmJHId","cellCombo.commonFilter?&sectionid="+sectId);
	reloadCombo("frmGrdUniqPos","cmbetcuRoleKeyid","roleMst.commonFilter?&flid="+flid);

}
 function getTimediff(){ 
	 var fromTime= getFieldValue("spnsessionFromTime");
	 var tillTime= getFieldValue("spnsessionTillTime");
	 var batchMonth = getFieldValue("dteSessiondate");
	 var progDuration=jQuery('#hdnTrngDuration').val();
	   
	 batchMonth =  batchMonth.replace('-',' ');
	 batchMonth =  batchMonth.replace('-',',');
	 
	 var difference = (Date.parse(batchMonth+" "+tillTime) - Date.parse(batchMonth+" "+fromTime)) / 60000;
	 if(parseInt(difference)>parseInt(progDuration*60)) //vignesh
		 {
		  popupCommonErrorMsg("Please select within Duration");
	//	  jQuery('#spnsessionTillTime').val(fromTime);
		  jQuery('#spnsessionTillTime').spinner('setValue',fromTime);
		  return false;
		 }
} 
// function fillTillTime(){ 
// 	var fromTime= getFieldValue("spnsessionFromTime");
// 	//alert(fromTime);
// 	var progDuration = jQuery('#hdnTrngDuration').val();
// 	// alert(progDuration);
// 	 var hours=progDuration*60;
// 	// alert("hours"+hours);
// 	if(progDuration.trim().length>0){
// 		var tillTime = addMinutes(fromTime+":00",hours);
// 		//alert("tillTime"+tillTime);
// 	//	jQuery('#spnsessionTillTime').val(tillTime);
// 		jQuery('#spnsessionTillTime').spinner('setValue',tillTime);
// 	}
// }

function addMinutes(time, minsToAdd) {
		
	  function D(J){ return (J<10? '0':'') + J;};
	  var piece = time.split(':');
	  var mins = piece[0]*60 + +piece[1] + +minsToAdd;
	 // alert(mins);
	  return D(mins%(24*60)/60 | 0) + ':' + D(mins%60);  
	 // return D(mins%(24) | 0) + ':' + D(mins%60);  
	} 
 
function  frmGrdUniqPoscmbEtcmSectionid_onSelect(record)
{
	var sectId=record.id;//jQuery("#cmbEtcmSectionid").combobox('getValue');
//	jQuery('#cmbEtcmSectionid').combobox('setValue',sectId);

	jQuery("#cmbEtcmJHId").combobox('clear');
	jQuery("#cmbetcuRoleKeyid").combobox('clear');    		
	reloadCombo("frmGrdUniqPos","cmbEtcmJHId","cellCombo.commonFilter?&sectionid="+sectId);
	reloadCombo("frmGrdUniqPos","cmbetcuRoleKeyid","uniquePostn.gbtc?&sectionid="+sectId);
}


function frmGrdUniqPoscmbEtcmJHId_onSelect(record){ 
		var cellId=record.id;		
		jQuery("#cmbetcuRoleKeyid").combobox('clear');    		

		reloadCombo("frmGrdUniqPos","cmbetcuRoleKeyid","uniquePostn.gbtc?&cellId="+cellId);
	
		}
		
function   viewGrid(url,ds) {
	/*
	var keyid = jQuery('#hdncalendar').val();
	var uniq=jQuery('#hdnuniq').val();
	//alert('The TrainingID::'+keyid);
    var flid=jQuery('#hdnflid').val();
	*/
	 var hdnKeyid=jQuery('#hdnKeyid').val();
	ds+="&trgCalId="+hdnKeyid;
	//alert(hdnKeyid+"  hdnKeyid  "+ds);
	
	// ds+="&refDocId="+hdnKeyid;
	  //  alert(ds+"fnLocationVal");

	//processGridnew("EmployeeAdd_input.gbtc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");

	processGridnew('GrdBsdsession_input.gbtc',ds,'SessionGrid','pagerSess','','sessiondoubleclickGrid','','');
	processGridnew('Faculty_input.gbtc',ds,'facultyGrid','pagerFac','','','','SessionEmploadComplete','');
    processGridnew("trngCalUniquePosition_input.gbtc",ds,"calUniquePosGrid", "pager","","","","SessionEmploadComplete"," ");


}


function sessiondoubleclickGrid(id){
	 var rowData = jQuery("#SessionGrid").jqGrid('getRowData',id);
	 var sessionId=rowData.ETCS_KEYID;
	 var frmTime = rowData.FromTime;
	 var tilTime = rowData.ToTime;
	 var sessiondte=rowData.Session;
	 setFieldValue("dteSessiondate",sessiondte,"frmGrdUniqPos");
	 setFieldValue("spnsessionFromTime",frmTime,"frmGrdUniqPos");
	 setFieldValue("spnsessionTillTime",tilTime,"frmGrdUniqPos");
	 jQuery("#hdnSessionId").val(sessionId);  
	
}
function SessionEmploadComplete(){
	//alert(12345);
}

//

// jQuery('#btnSessionsave').click(function(){
//     var date = getFieldValue('dteSessiondate');	
//     var fromTime= getFieldValue('spnsessionFromTime');
//    // alert(fromTime);
//  	  var tillTime= getFieldValue('spnsessionTillTime');
//  	// alert(tillTime);
//  	  var keyid=jQuery('#hdnKeyid').val();//jQuery('#txtEtcmKeyid').val('ETC000000000797');
//  	  var sessionid=jQuery('#hdnSessionId').val();
//  	 // alert(keyid+'  '+sessionid);
//  	  if(keyid.length==0)
//  		  {
//  		   popupCommonErroMsg('Please save Training Calendar');
//  		   return false;
//  		  }
 	  
//  	  else{
//       // alert(1236);
//  		if(fromTime==tillTime)
//  		 {
//  		//	alert('inside');
//  		  popupCommonErrorMsg('Please select correct from time and till time');
//  		  return false;
//  		 }
//  		else{
//  		//	alert('inside1');
//  		  processAjaxCalls('chkSessionDate.gbtc','&keyid='+keyid+'&sedte='+date+'&frmtme='+fromTime+'&totme='+tillTime+'&sesid='+sessionid,'chkSessionDate_onsuccessCallBack','chkSessionDate_onerrorCallBack');
//  		}
//  	  }
//  	 jQuery("#trgCalendarGrid").trigger("reloadGrid");
// });

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
	// alert(result.keyid+''+sessiondte+''+frmtime+''+totime+''+sessionid +'   123');
	
  	 var Duration=jQuery('#txtEtcmMaxDuration').val();
  	var progDuration=60*Duration;
   batchMonth =  batchMonth.replace('-',' ');
  //alert('batchMonth'+batchMonth);
  	batchMonth =  batchMonth.replace('-',',');
  // alert('batchMonth'+batchMonth);
  //13 Apr-2021  13 Apr,2021  618328220000  618321020000 7200000
  	 var difference = (Date.parse(batchMonth+' '+totime) - Date.parse(batchMonth+' '+frmtime)) / 60000;
  	 if(parseInt(progDuration)<parseInt(difference))
   	 {
   	 popupCommonErrorMsg('Please select correct from time and till time');
  		 return false;
   	 
   	 }
  	 
  	 
		if( sessiondte.trim().length>0)
			saveForm('frmGrdUniqPos','GridBasedCalSession_save.gbtc?q=2&Savetype=Session&txtEtcmKeyid='+keyid+'&dteSessiondate='+sessiondte+'&spnsessionFromTime='+frmtime+'&spnsessionTillTime='+totime,'','');	
		 setTimeout(function(){
		jQuery('#SessionGrid').trigger('reloadGrid');
		},850); 
   
		

		}
		else{
			 popupCommonErrorMsg(' Session Already Available ');
			 fillWithCurrentDate('sessionFromTime');
	 		 fillWithCurrentDate('sessionTillTime');
	 		 fillWithCurrentDate('spnsessionFromTime');
	 		 fillWithCurrentDate('spnsessionTillTime');
	 		 return false;
		}
	 
}

function upSave(){
	//alert(123);
	   var updmt=getFieldValue('cmbEtcmSectionid');
	   
	   var upJh=getFieldValue('cmbEtcmJHId');
	   
	  // var keyid=jQuery('#txtEtcmKeyid').val('ETC000000000797');
	   var keyid=jQuery('#hdnKeyid').val();
	  
	   var Upid =jQuery('#cmbetcuRoleKeyid').combobox('getValue');//combobox('getValue');// getFieldValue('cmbEtcuRoleKeyid');
	   //alert(Upid);
	  // var chkuni=jQuery('#chkEtcmUniqueposition').is(':checked');	   
	   processAjaxCalls('chkUniQupostion.gbtc','?q=2&keyid='+keyid+'&Upid='+Upid+'&updmt='+updmt+'&upJh='+upJh,'chkUniQupostion_onsuccessCallBack','chkUniQupostion_onerrorCallBack');
//alert(1236);
//&keyid='+keyid+'&Upid='+Upid+'&updmt='+updmt+'&upJh='+upJh
}
function chkUniQupostion_onerrorCallBack(result){
	
}
function chkUniQupostion_onsuccessCallBack(result)
{
 var allUnique='N';
	//var mstkeyid=result.keyid;
	var mstkeyid=jQuery('#hdnKeyid').val();
	var roleid=result.Upid;
	var uniqkeyid=result.uniqukeyid;
	var updmt=getFieldValue('cmbEtcmSectionid');
	var upJh=getFieldValue('cmbEtcmJHId');
	var Upid = jQuery('#cmbetcuRoleKeyid').combobox('getValue');//getFieldValue('cmbEtcuRoleKeyid');
	var uniqcnt=result.uniquecnt;
	 var ds='';
	 if(uniqcnt==0)
		 {
	    ds= '?&updmt='+updmt+'&upJh='+upJh+'&uniqid='+Upid+'&keyid='+mstkeyid;
	  //  alert(ds+'...');
	   if(roleid.trim().length>0){
			saveForm('frmGrdUniqPos','GridBasedUniquePosition_save.gbtc?&updmt='+updmt+'&upJh='+upJh+'&uniqid='+Upid+'&keyid='+mstkeyid);
			setTimeout(function(){
				jQuery('#calUniquePosGrid').trigger('reloadGrid');
				jQuery('#cmbetcuRoleKeyid').combobox('clear');
				jQuery('#trgCalendarGrid').trigger('reloadGrid');

				},850);
			 
			return false;
		}else{
			alert('Select Unique Position');
			return false;
		}
		 }
	/* else{
		 popupCommonErrorMsg('Already this Unique position is seleected for Training');
		 return false;
	 }*/
	    	 	  
	    	   if(mstkeyid.trim().length>0){
	    			 var r = confirm('Data changed, Do you want to Proceed');
	    			 if(r){
	    				 if(jQuery('#chkUniquePosition').is(':checked'))
	    					 allUnique = 'Y';  
	    				     // alert('if'+allUnique);//ds=+dsallUnique='+allUnique+
	    				   saveForm('frmGrdUniqPos','GridBasedUniquePosition_save.gbtc?ds='+ds+'&allUnique='+allUnique);
	    				// return '&allUniquePosition='+allUnique;
	    				jQuery('#cmbetcuRoleKeyid').combobox('clear');
	    				//   jQuery('#SessionGrid').trigger('reloadGrid');
	    			 }else{
	    				 return false;
	    			 }  
		 }
}

var hdnKeyid=jQuery('#hdnKeyid').val();
//processGridnew('Faculty_input.gbtc','q=2&TraKeyid='+hdnKeyid,'facultyGrid','pager','','','','SessionEmploadComplete',' ');
//processGridnew('uniquePositionLink_input.gbtc','q=2&TraKeyid='+hdnKeyid,'UniquePosGrid', '','','','','');

// jQuery('#btnFacultySave').click(function(){
//     var MasterKeyid=jQuery('#hdnKeyid').val();
//   //  alert(MasterKeyid +'Master Id');
// 	var facultyId=jQuery('#cmbEtcfFacultyId').combobox('getValue');
// 	if(facultyId.length==0 || facultyId==null){
// 		alert('Pls Add The Faculty!');
// 		return false;
// 	}
// 	if (MasterKeyid==null || MasterKeyid==' ' || MasterKeyid=='') {
// 		var ds = +'&Facultyid='+facultyId;
// 		//alert('ds'+ds);
// 		if(facultyId.trim().length>0 )
// 			{
// 			saveForm('frmGrdUniqPos','GridbasedFaculty_save.gbtc?ds='+ds,'','');
			
// 			 setTimeout(function(){
// 				 jQuery("#facultyGrid").trigger("reloadGrid");
// 					},850); 
			   
					
// 	//	jQuery('facultyGrid').trigger('reloadGrid');
			
		
// 		}
// 	}
// 	else{
// 		if(MasterKeyid!=null&&facultyId!=null)
// 			{
// 			  processAjaxCalls('FacultyCheck.gbtc','&keyid='+MasterKeyid+'&faclid='+facultyId,'FacultyCheck_onsuccessCallBack','FacultyCheck_onerrorCallBack');
// 			}
// 	}
// });

function FacultyCheck_onsuccessCallBack(result)
{
	//alert('success');
	//var keyid=result.keyid;
	var facultyid=result.fcltyid;
	var facultycnt=result.fcltycnt;
    var keyid=jQuery('#hdnKeyid').val();
//alert(keyid +'keyid');
	if(facultycnt==0)
		{
		var ds = '&MasterKeyid='+keyid+'&Facultyid='+facultyid+'&Savetype=Faculty';
		if(facultyid.trim().length>0)
			saveForm('frmGrdUniqPos','GridbasedFaculty_save.gbtc?ds='+ds,'','');
		jQuery('#cmbEtcfFacultyId').combobox('clear');
		 setTimeout(function(){
			 jQuery("#facultyGrid").trigger("reloadGrid");
				},550); 
		   
		 
				
		}
	else{
		popupCommonErrorMsg('This Faculty is Already Selected for this Training Calendar');
		jQuery('#cmbEtcfFacultyId').combobox('clear');
		return false;
	}
	
}

function BtnFormatterDelete(id, options, rowObject)
{			
	
	var rowId = options.rowId;
	var gridId = options.gid;

	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
}
function deleterec(rowid,gridId){
	var mode=jQuery("#hdnMode").val();
	if(mode=="view"){
		alert("View Mode Not Possible To Delete");
		return false;
	}
	var keyId;
	if("facultyGrid"==gridId)
		keyId = getGridCell(gridId,rowid,'ETCF_KEYID');
	else if("calUniquePosGrid"==gridId)
		keyId = getGridCell(gridId,rowid,'ETCU_KEYID');
	else
	keyId = getGridCell(gridId,rowid,'ETCS_KEYID');
	var session=getGridCell(gridId,rowid,'ETCS_NAME');
	if(session=="Session 1")
		{
		 popupCommonErrorMsg("First session unable to delete,please modify session time");
		 return false;
		}
	 var TraKeyid = jQuery("#hdnKeyid").val();
	 var r = confirm("Are You Sure To Delete?");
	  if(r==true){
	processAjaxCalls('detailsFacUniSess_Delete.gbtc','&keyid='+keyId+'&gridId='+gridId+"&TraKeyid="+TraKeyid,'deleteDetail_onsuccessCallBack','deleteDetail_onerrorCallBack');
	  }
	  else{
		  return false;
		  } 
	  }

function deleteDetail_onsuccessCallBack(result){ 
		  alert(result.msg);
	      jQuery("#"+result.gridid).trigger("reloadGrid");  
	}
function MUnique(){
	 
	 var Calendarflid = jQuery("#frmNewTraCal input[id='flid']").val();
	
	 var CalendarId=jQuery("#hdnKeyid").val();
	 var sectionId =  jQuery('#cmbEtcmSectionid').combobox('getValue');//jQuery("#frmNewTraCal input[id='section']").val();
	 var cellId = jQuery('#cmbEtcmJHId').combobox('getValue');//jQuery("#frmNewTraCal input[id='cell']").val();
	 var Calendarflid=jQuery('#hdnFlid').val();
        //jQuery('#cmbEtcmSectionid').combobox('setValue',sectId);
		//jQuery("#cmbEtcmJHId").combobox('clear');
		//jQuery("#cmbetcuRoleKeyid").combobox('clear'); 
	//alert(sectionId);
	 //alert(cellId);
	LoadPopUp("DivMultiUniquePosition","MultipleUniqueAdd_input.gbtc?q=2&Calendarflid="+Calendarflid+"&CalendarId="+CalendarId+"&sectionId="+sectionId+"&cellId="+cellId,true,"800px","490px","4px","4%", "multiSelectOk_Callback","Multi Unique Position Add");

	}
jQuery("#btnFacultyAdd").click(function(){
	openMasterForm('loadmst_grid.gnms?q=2&menuCaption=Faculty&menuName=MNUETFACULTY&isMMC=Y&loadFormArg=Faculty%3F',frmMode.create,'frmNewTraCal','Faculty','mstFrm');
});

jQuery("#btnUniquePosAdd").click(function(){
	
	var sectionId =  jQuery('#cmbEtcmSectionid').combobox('getValue');//jQuery("#frmNewTraCal input[id='section']").val();
	 var cellId = jQuery('#cmbEtcmJHId').combobox('getValue');
	var flid=jQuery('#hdnFlid').val();
	//navigateToNextForm("UniquePositionform_input.topi","");

	//("DivMultiUniquePosition","niquePositionform_input.topi?q=2",true,"800px","490px","4px","4%", "multiSelectOk_Callback","Multi Unique Position Add");

	//"&menuCaption=Faculty&menuName=MNUETFACULTY&isMMC=Y&loadFormArg=Faculty%3F'

openMasterForm('UniquePositionform_input.topi?q=2&flid='+flid,frmMode.create,'frmGrdUniqPos','Unique Postion','mstFrm');
});

</script>

<form id='frmGrdUniqPos'>
<div>

<td>
	 			<div class='sub-header' style='margin-top:30px; margin-left:10px; width : 350px;'>
	 			<span   style=' width : 400px; margin-bottom:10px; margin-left:10px;'>	<label>Session</label> </span>
	 				</div>
	 				</td>
	 				<td>	 				
	 				<div class='sub-header' style='margin-top:-30px; margin-left:400px; width :350px;'>
	 			     <span  style=' width : 400px; margin-bottom:10px; margin-left:10px;'>	<label>Faculty</label> </span>
	 				</div>
	 				</td>
	 				<td>
	 				<div class='sub-header' style='margin-top:-30px; margin-left:780px; width : 450px;'>
	 			      <span  style=' width : 360px; margin-bottom:10px; margin-left:10px;'>	<label>Unique Position</label> </span>
	 				</div>
	 				</td>
	 				
	 				<div style='margin-left:10px;margin-top:10px;'>
	 				<label class='mandatory-lbl'>Session Date</label>
	 				<span style='margin-left:60px;'><label>From</label></span>
	 				<span style='margin-left:35px;'><label>To</label></span>
	 				<span style='margin-left:180px;'><label id= '' class='mandatory-lbl' >Faculty</label> </span>
	 				<span style='margin-left:320px;'><label id= '' class='mandatory-lbl' >DMT</label> </span>
	 				<span style='margin-left:230px;'><label id= '' >JH</label> </span>
	 				
	 			</div>
	 			<div class='easyui-paddingbfpx' style='margin-left:10px;'>
				 	<input id='dteSessiondate' name='dteSessiondate' class='easyui-datebox' style='width:90px;'  />
					
					<span style='margin-left:10px;'>
						<input  id='spnsessionFromTime' name='spnsessionFromTime' class='easyui-timespinner spinner-text validatebox-text' value=''  style='width: 60px;' /> 
					</span>
					<span style='margin-left:10px;'>
						<input  id='spnsessionTillTime' name='spnsessionTillTime'  class='easyui-timespinner spinner-text validatebox-text' value=''  style='width:60px;' /> 
					</span>

					<span style='margin-left:10px;'>
						<input type='button' class='easyui-button' value='Add' id='btnSessionsave'/>
					  </span>
					<!--	
				<span style='margin-left:220; margin-top:-20px;'> -->
		 	    	
			 		
			 		<span style='margin-left:110px;'>
						<input id='cmbEtcfFacultyId' name='cmbEtcfFacultyId' value='${requestScope.entTlTrgCalUnqp.etcuRoleKeyid}' class='easyui-combobox' style='width:220px;' />
			 		</span> 
			 		<span style='margin-left:20px;'>
				<input type='button' class='easyui-button' value='Add' id='btnFacultySave' ></input>
				</span>
				
				<span style='margin-left:95px;'>
						<input id='cmbEtcmSectionid' name='cmbEtcmSectionid' value='${requestScope.entTlTrgCalUnqp.etcuRoleKeyid}' class='easyui-combobox' style='width:230px;' />
			 		</span>
			 		<span style='margin-left:20px;'>
						<input id='cmbEtcmJHId' name='cmbEtcmJHId' value='${requestScope.entTlTrgCalUnqp.etcuRoleKeyid}' class='easyui-combobox' style='width:200px;' />
			 		</span>
			 		</div>
			 		<div style='margin-left:685px;' class='easyui-paddingbfpx'>
			 		<span style='margin-left:100px;'><label id= '' class='mandatory-lbl' >Unique Position</label> </span>
			 		
			 		</div>
				
				<div id="Unique" style='margin-left:400px;' class='easyui-paddingbfpx'>
				<span style='margin-left:0px;'>
				<input type='button' class='easyui-button' value='Faculty Master' id='btnFacultyAdd' ></input>
				</span>
				<span style='margin-left:140px;'>
				<input type='button' class='easyui-button' value='Uniuqe Pos. Master' id='btnUniquePosAdd' ></input>
				</span>
				<span style='margin-left:20px;'> 
						<input id='cmbetcuRoleKeyid' name='cmbetcuRoleKeyid' class='easyui-combobox' style='width:240px;' />
			 		</span>
				
				<span style='margin-left:20px;'>
				<input type='button' class='easyui-button' value='Add' id='btnUPSave' onclick='upSave();' ></input>
				</span>
				<span style='margin-left:20px;'>
				<input type='button' class='easyui-button' value='Multiple Unique Position' id='btnMultipleUnique' onclick='MUnique();' ></input>
				</span>
				
				</div>
		
	<div style='margin-top:10px;'>	
	<table>
	<tr>
	<td>		
		 		 <div style='margin-left:10px;'>
	<table id='SessionGrid'>
	<tr><td></td></tr>
	
	</table>
	<div id="pagerSess"></div>	
	</div>
	</td><td>   
     <div style='margin-left:40px;'> 
	<table id='facultyGrid'> 
	
	
	</table>
				
			<div id="pagerFac"></div>			
   </div>  
   </td>
   <td>
 <div style='margin-left:50px;'> 
	<table id='calUniquePosGrid'>
	
	
	</table>
			<div id="pager"></div>
			
   </div>
   </td>
   </tr></table>
    
			   
   </div>
 <input type='hidden' id='hdnKeyid' name='hdnKeyid' value='${requestScope.trgCalId}'>
  <input type='hidden' id='hdnFlid' name='hdnFlid' value='${requestScope.flid}'> 
 <input type='hidden' id='hdnLocationId' name='hdnLocationId' value='${requestScope.locationId}'>
 <input type='hidden' id='hdnSectKeyid' name='hdnSectKeyid' value='${requestScope.sectionId}'>
 <input type='hidden' id='hdnCellKeyid' name='hdnCellKeyid' value='${requestScope.cellId}'>
 <input type='hidden' id='hdnMode' name='hdnMode' value='${requestScope.mode}'>
 <input type='hidden' id='hdnTrngType' name='hdnTrngType' value='${requestScope.trngType}'>
  <input type="hidden" id="hdnRoleName" name="hdnRoleName" value="${requestScope.rolename}"/>
  <input type="hidden" id="hdnTrngDuration" name="hdnTrngDuration" value="${requestScope.trngDuration}"/>
  <input type="hidden" id="hdnTrngDate" name="hdnTrngDate" value="${requestScope.trngDate}"/> 

 

</div>

</form>



