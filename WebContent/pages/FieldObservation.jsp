<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
jQuery(document).ready(function(){
	initialiseForm('frmFieldObservation');
	jQuery('#submitForm').val('frmFieldObservation');
	var factId = jQuery("#frmFieldObservation input[id='factory']").val();
	var sectionId = jQuery("#frmFieldObservation input[id='section']").val();
	var cellId = jQuery("#frmFieldObservation input[id='cell']").val();
	var machId = jQuery("#frmFieldObservation input[id='machine']").val();
	var flid = jQuery("#frmFieldObservation input[id='flid']").val();
    var FobmKeyid=jQuery("#txtFobmKeyid").val();
   // alert("FobmKeyid:"+FobmKeyid);
	readOnlyFields('txtFobmKeyid');
	var recall=jQuery('#hdnrecall').val();
	numericTextBox("cmbFobddetectedby");
	disableField("frmFieldObservation", "cmbFobddetectedby")
	jQuery('#cmbFobddetectedby').combobox("disable");
	formatDateBox('dteFobmDetectiondate','dd-MMM-yyyy');
	   loadFunctionalLocation("NewFOLoggingfunLocation","functionalLoc.ehsb","NewFOfunLocationValues","frmFieldObservation","");

	   var Rolename=jQuery("#hdnRolename").val();
		//alert(Rolename);
		if(Rolename=="SECTION INCHARGE" || Rolename=="SHIFT INCHARGE"){
			
		}
		else{
			alert("Access Denied");
			navigateToPrevForm();
			return false;
		} 
	   
	var DetectDate = getFieldValue("dteFobmDetectiondate");
	if( DetectDate == null || DetectDate.length<1)
	{
		fillWithCurrentDate("dteFobmDetectiondate");
		formatDateBox('dteSusnDate','dd-MMM-yyyy');
	    fillWithCurrentDate("spnFobdstarttime");
		fillWithCurrentDate("spnFobdFinishtime")
	}
	   

	
	fillComboBox('frmFieldObservation','cmbFobdname','employeefield.ehsb');
	fillComboBox('frmFieldObservation','cmbFobdBadge','badge.ehsb');
	fillComboBox('frmFieldObservation','cmbFobdUnsafeact','unsafeacts.ehsb');
	fillComboBox('frmFieldObservation','cmbFobddetectedby','employee.commonFilter?id="+id');
	fillComboBox("frmFieldObservation","cmbfobmJhid","cellCombo.commonFilter");
	fillComboBox("frmFieldObservation","cmbFobmShiftid","shift.commonFilter");
	getCurrentShiftQC("cmbFobmShiftid");
	 spinnerKeyPress('spnFobdstarttime');
	 spinnerKeyPress('spnFobdstarttime');
	 spinnerKeyPress('spnFobdstarttime');
	 spinnerChange('spnFobdstarttime','detectionDateEvt');
     spinnerUp('spnFobdstarttime','detectionDateEvt');
     spinnerDown('spnFobdstarttime','detectionDateEvt');
	 spinnerKeyPress('spnFobdFinishtime');
	 spinnerKeyPress('spnFobdFinishtime');
	 spinnerKeyPress('spnFobdFinishtime');
	 spinnerChange('spnFobdFinishtime','detectionDateEvt1');
     spinnerUp('spnFobdFinishtime','detectionDateEvt1');
     spinnerDown('spnFobdFinishtime','detectionDateEvt1');
    
  
      if(FobmKeyid.length>0){
    	//alert("Key::"+FobmKeyid);
    	var DetecionDate=jQuery("#dteFobmDetectiondate").datebox("getValue");
 	    jQuery("#dteFobmDetectiondate").datebox("setValue",DetecionDate.substring(0,11));
    	var starttime=jQuery('#spnFobdstarttime').spinner('getValue');
     	jQuery("#spnFobdstarttime").spinner("setValue",starttime.substring(12,20));
        var finishtime=jQuery("#spnFobdFinishtime").spinner("getValue");
     	jQuery("#spnFobdFinishtime").spinner("setValue",finishtime.substring(12,20));
      	var Shift=jQuery("#cmbQclmShiftid").combobox("getValue")
 	    var Shift=jQuery("#cmbFobmShiftid").combobox("getValue");
 	    jQuery("#cmbFobmShiftid").combobox("setValue",Shift);
 	    disableField("frmFieldObservation","cmbFobmShiftid");
 }
      
 
   //  alert("Flidd"+flid);
    if(flid !=null)
    {	 
    	loadFunctionalLocation("NewFOLoggingfunLocation","functionalLocField.ehsb","NewFOfunLocationValues","frmFieldObservation","&flid="+flid);
    	reloadCombo("frmFieldObservation","cmbfobmJhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
    }
    else
 	   {
 	   loadFunctionalLocation("NewFOLoggingfunLocation","functionalLoc.ehsb","NewFOfunLocationValues","frmFieldObservation","");
 	   } 

	
});



var mode=jQuery("#hdnmode").val();
//alert("Mode:::"+mode);
reloadCombo("frmFieldObservation","cmbFobdname","employeefield.ehsb");
	if(mode=="view"){
		
		    disableForm("frmFieldObservation");
			readOnlyFields('txtFobmKeyid');
			readOnlyFields("cmbAbnmTradeid");
			readOnlyFields("cmbFobmShiftid");
			readOnlyFields("txtFobdremarks");
			readOnlyFields("spnFobdstarttime");
			readOnlyFields("spnFobdFinishtime");
			numericTextBox("cmbFobddetectedby");
			disableUIButton("btnSaveField");
			jQuery("#cmbFobmShiftid").attr("disabled",true);
			 setTimeout(function() {readOnlyFields('cmbFobmShiftid');},1250);
			 setTimeout(function() {readOnlyFields('cmbFobdUnsafeact');},1250);
			 setTimeout(function() {readOnlyFields('cmbfobmJhid');},1250);
			disableField("frmFieldObservation", "cmbFobmShiftid");
			disableField("frmFieldObservation", "cmbFobddetectedby");
			jQuery('#cmbFobmShiftid').combobox("disable");
			jQuery('#cmbFobddetectedby').combobox("disable");
		}    
   




readOnlyFields('dteFobmDetectiondate');
readOnlyFields('cmbFobddetectedby');






imageUpload(jQuery("#dlgImg"),'ImageUpload.commonFilter','dlgImg',"imgFieldObservation","imgQCName","380","270",false);
 var imgName=jQuery("#imgFieldObservation").val();
 var imgPath=jQuery("#hdnFieldimagepath").val();
	jQuery('#imgFieldObservation').attr('src', imgPath);
		if(imgName.length>0){
			jQuery('#imgFieldObservation').attr('src', imgPath);
		}
		else{
			imageUpload(jQuery("#dlgImg"),'ImageUpload.commonFilter','dlgImg',"imgFieldObservation","imgQCName","380","270",false);
		}
		
		 jQuery("#btnImgClear").click(function() {
				jQuery('#imgFieldObservation').attr('src', "images/EmpDefaultImg.jpg");
				jQuery('#imgQCName').val("");
				});

var SusaKeyId=jQuery("#txtSusnKeyid").val(); 
var detailsid=jQuery("#hdndetailsid").val();
processGridnew("FieldObservationUnsafeact_input.ehsb","?q=1"+"&SusaKeyId="+SusaKeyId,"UnsafeActGrd","UnsafeActGrdpager"," ","condDoubleClick","","actgridcompletecallback");
processGridnew("FieldObservationGrid_input.ehsb","?q=1"+"&SusaKeyId="+SusaKeyId,"MainActGrd","MainActGrdpager"," ","pcsdblClick","","");
processGridnew("newEHSBadgeTracker_input.ehsb","?q=1"+"&SusaKeyId="+SusaKeyId,"BadgeTrackerGrd","BadgeTrackerGrdpager"," ","","","");

function frmFieldObservationcmbFobdUnsafeact_onSelect(record){
	var fromdate = jQuery('#dteFobmDetectiondate').datebox("getValue");
	var unsafeact = jQuery('#cmbFobdUnsafeact').combobox("getValue");
	processGridnew("FieldUnsafeAct_input.ehsb","?q=1"+"&formType="+"hi"+"&unsafeact="+unsafeact+"&detailsid="+detailsid,"UnsafeActGrd","UnsafeActGrdpager"," ","condDoubleClick","","");
}	


var GblShiftID;
function getCurrentShiftQC(shiftId )
{
	GblShiftID = shiftId;
	processAjaxCalls('getCurrentShift.ehsb','',"getCurrentShiftQCSuccess");
}

function getCurrentShiftQCSuccess(result) {		
	setFieldValue(GblShiftID, result.shift);
	readOnlyFields("cmbFobmShiftid");
}



jQuery("#btnSaveField").click(function() {
var modes=jQuery("#hdnmodes").val();
var detailid=jQuery("#hdndetailid").val();
	var fromdate = jQuery('#dteFobmDetectiondate').datebox("getValue");
	var name = jQuery('#cmbFobdname').combobox("getValue");
    var unsafeact=jQuery('#cmbFobdBadge').combobox("getValue");
    var badge=jQuery('#cmbFobdUnsafeact').combobox("getValue");
    
    if(name.length==0){
    	alert("Select  the Name");
    	return false;
    }
    
    if(unsafeact.length==0){
    	alert("Select the Badge");
    	return false;
    }
    if(badge.length==0){
    	alert("Select the UnsafeAct Committed");
    	return false;
    }
    

	  var imagename =jQuery("#imgQCName").val();
	//  alert("Image Name"+imagename);
	  var image=imagename.lastIndexOf("/");
	  var imageName= imagename.substring(image+1,imagename.length);
	  var imagefullname = imageName.toUpperCase();
	  jQuery("#hdnFobdphoto").val(imagefullname);
      var paramconvertArr =convertJsonArr();
      saveForm("frmFieldObservation","FieldObservationsDtl_save.ehsb?&fromdate="+fromdate+"&paramconvertArr="+paramconvertArr+"&imagename="+imagefullname+"&name="+name+"&unsafeact="+unsafeact+"&badge="+badge+"&modes="+modes+"&detailid="+detailid);
});


function convertJsonArr(){
	var allrow = jQuery("#UnsafeActGrd").jqGrid('getRowData');
	var jsonArrO = '';
	var val = "";
	for ( var i = 0; i < allrow.length; i++) {
		var row = allrow[i];
		var rowno = parseInt(i) + 1;
		if(jQuery('#jqg_UnsafeActGrd_' + rowno).is(':disabled')==true)
			{
			continue;
			}
		if (jQuery('#jqg_UnsafeActGrd_' + rowno).is(':checked')) {
			
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

function detectionDateEvt(){
	var startdate = jQuery('#dteFobmDetectiondate').datebox('getValue') + jQuery('#spnFobdstarttime').spinner('getValue');
	var enddate=jQuery('#dteFobmDetectiondate').datebox('getValue') + jQuery('#spnFobdstarttime').spinner('getValue');
	var dataString = "&startdate="+startdate+"&enddate="+enddate;
	processAjaxCalls("getCurrentShift.ehsb?"+dataString,"","getCurrentShiftQCSuccess");
}
function detectionDateEvt1()
{
	var detectionDate = jQuery('#dteFobmDetectiondate').datebox('getValue') + jQuery('#spnSusaTime').spinner('getValue');
	var currentDate = getServerDateTime();
}


function BtnFormatterDelete(id, options, rowObject)
{					
	var rowId = options.rowId;
	var gridId = options.gid;

	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
}

function frmFieldObservation_beforeSubmit(){
      var taskType=jQuery('#hdntype').val();
      var TaskGridid= jQuery("#BadgeTrackerGrd").jqGrid('getDataIDs');
	  var gridval=getGridSelectArray('BadgeTrackerGrd');
	  var gridData= '&taskdetails='+gridval;
	  if(gridval.trim().length>0){
			return gridData;
		  }	  
}

function frmFieldObservationcmbFobdname_onSelect(record){	
	 if(jQuery("#"+cmbFobdname).val().trim().length>0)
		jQuery('#hdnIsDtlTrue').val("true");	
		var data=jQuery("hdnIsDtlTrue").val();
}

function frmFieldObservationcmbfobmJhid_onSelect(record) 	{
	var dataStr="&sectId="+record.id;
	loadFunctionalLocation("NewFOLoggingfunLocation","functionalLoc.ehsb","NewFOfunLocationValues","frmFieldObservation",dataStr);
	var cellId = jQuery("#frmFieldObservation input[id='cell']").val();
	var flid = jQuery("#frmFieldObservation input[id='flid']").val();
	reloadCombo("frmFieldObservation","cmbfobmJhid","cellCombo.commonFilter?cellId="+cellId+"&flid="+flid);
	fnRecall();
}

function fnRecall(){
	var FOJHId =getFieldValue('cmbfobmJhid');
	var FOdetcteddate = getFieldValue("dteFobmDetectiondate", "frmFieldObservation");
	var FOshift= getFieldValue("cmbFobmShiftid");
	var FOdetectedby= getFieldValue("cmbFobddetectedby");
	var ds = "?&FOJHId="+FOJHId + "&FOdetcteddate="+FOdetcteddate+"&FOshift="+FOshift+"&FOdetectedby="+FOdetectedby;
    processAjaxCalls("FieldObservationRecalling_input.ehsb",ds,"RecallingSuccessData","");
}

function RecallingSuccessData(result){
	var SusaId=result[0][0];
	//alert(SusaId);
	setFieldValue("txtFobmKeyid",result[0][0]);
	setFieldValue("cmbfobmJhid",result[0][1]);
	setFieldValue("dteFobmDetectiondate",result[0][2]);
	setFieldValue("cmbFobmShiftid",result[0][3]);
   	setFieldValue("cmbFobddetectedby",result[0][4]);
    setFieldValue("cmbFobdBadge",result[0][5]);
    setFieldValue("cmbFobdname",result[0][6]);
    setFieldValue("cmbFobdUnsafeact",result[0][7]);
    setFieldValue("txtFobdremarks",result[0][8]);
    
    processGridnew("FieldObservationGrid_input.ehsb","?q=1"+"&SusaId="+SusaId,"MainActGrd","MainActGrdpager"," ","pcsdblClick","","");
   var DetecionDate=jQuery("#dteFobmDetectiondate").datebox("getValue");
   jQuery("#dteFobmDetectiondate").datebox("setValue",DetecionDate.substring(0,11));
}

function frmFieldObservation_successsCallback(result){
	var SusaId=result.keyids;
	jQuery('#txtFobdremarks').val('');
	jQuery('#cmbFobdUnsafeact').val('');
	clearField("cmbFobdUnsafeact"); 
	jQuery('#cmbFobdUnsafeact').val('');
	clearField("cmbFobdBadge"); 
	jQuery('#cmbFobdBadge').val('');
	clearField("cmbFobdname"); 
	jQuery('#cmbFobdname').val('');
	clearField("imgFieldObservation");
	clearField("imgQCName");
	jQuery('#imgFieldObservation').attr('src', "");
	jQuery('#imgQCName').val("");
	var SusaId=result.keyids;
	var dtlid=result.dtlid;
	var image=result.image;
	jQuery("#txtSusnKeyid").val(SusaId);
	setFieldValue('txtFobmKeyid',SusaId);
    jQuery("#UnsafeActGrd").trigger("reloadGrid");
    jQuery("#BadgeTrackerGrd").trigger("reloadGrid");
    var cellid=result.cellid;
    reloadCombo("frmFieldObservation","cmbFobdname","employeefield.ehsb?&cellId="+cellid);
    processGridnew("FieldObservationGrid_input.ehsb","?q=1"+"&SusaId="+SusaId+"&image="+image+"&dtlid="+dtlid,"MainActGrd","MainActGrdpager"," ","pcsdblClick","","");
    processGridnew("newEHSBadgeTracker_input.ehsb","?q=1"+"&SusaId="+SusaId,"BadgeTrackerGrd","BadgeTrackerGrdpager"," ","","","");
}

     function actgridcompletecallback(){
     	     var mode=jQuery("#mode").val();	 
		     var row = jQuery("#UnsafeActGrd").jqGrid('getDataIDs');
			 var cm = jQuery("#UnsafeActGrd").jqGrid("getGridParam", "colModel");
			 for(var i=0;i<row.length;i++)
			 {
				 var detailsid = jQuery("#UnsafeActGrd").jqGrid('getCell',row[i],"hdnFobdKeyid");	
				   if(detailsid.trim().length>0){ 
					    jQuery('#UnsafeActGrd').setSelection(row[i], true);
					    jQuery('input:checkbox[id=jqgh_UnsafeActGrd_'+row[i]+']').attr('checked',true);
					    jQuery("#UnsafeActGrd").jqGrid('setCell',row[i],'selctVal','1');
					    jQuery("#UnsafeActGrd").jqGrid('getCell',row[i],"txtFodcDesciptionid");
					   jQuery('input:checkbox[id=jqg_UnsafeActGrd_'+row[i]+']').attr('disabled',false);
				   }
			 }	 
	}

function pcsdblClick(id){ 
 var name = jQuery("#MainActGrd").jqGrid('getCell',id,3);
 var badge = jQuery("#MainActGrd").jqGrid('getCell',id,5);
 var unsafeact = jQuery("#MainActGrd").jqGrid('getCell',id,7);
 var description = jQuery("#MainActGrd").jqGrid('getCell',id,9);
 var remarks = jQuery("#MainActGrd").jqGrid('getCell',id,10);
 var image = jQuery("#MainActGrd").jqGrid('getCell',id,11);
 //alert("Image"+image)
 var  detailsid= jQuery("#MainActGrd").jqGrid('getCell',id,1);
 var masterid= jQuery("#MainActGrd").jqGrid('getCell',id,2);
 jQuery('#hdnkeyId').val(masterid);
	
	setFieldValue('txtFobdremarks',remarks);
    jQuery('#txtFobdremarks').val(remarks);	
	setFieldValue('cmbFobdBadge',badge);
	setFieldValue('cmbFobdUnsafeact',unsafeact);
	setFieldValue('cmbFobdname',name);
	setFieldValue('txtFobmKeyid',masterid);
	processGridnew("FieldObservationUnsafeact_input.ehsb","?q=1"+"&unsafeact="+unsafeact+"&detailsid="+detailsid,"UnsafeActGrd","UnsafeActGrdpager"," ","condDoubleClick","","actgridcompletecallback");
	processAjaxCalls("get_Image.ehsb","?q=2&detailsid="+detailsid ,"getFOImgSuccess","getFOImgErr");
	jQuery('#hdnmodes').val('upd');
	setFieldValue('hdndetailid',detailsid);
	jQuery('#hdndetailid').val(detailid); 
    
     }

function getFOImgSuccess(result){ 
	if(result.empImg.Data == true && result.empImg.Data != 'undefined'){	
	jQuery('#imgFieldObservation').attr('src','');
	jQuery('#imgFieldObservation').attr('src',result.empImg.imgToimBlobimage);	
	}
	else{
		jQuery('#imgFieldObservation').attr('src', "");
		jQuery('#imgQCName').val("");
		}
}

 jQuery("#imgFieldObservation").click(function(){
	 var detail= jQuery("#hdndetailid").val();
	 LoadPopUp("FOImage","FOImagePopup_input.ehsb?q=2&detail="+detail,true,"60%","60%","20%","80px","loadSuccessCallBack","View Image",false,false,true);
	});

 function FOImage_afterClose(){
		setTimeout(function(){
	    	jQuery("#MainActGrd").trigger("reloadGrid");
	    	},10);		
	}

function frmFieldObservation_beforeDelete(){
	var SusaKeyId=jQuery("#txtSusnKeyid").val();
	var r=confirm("Are You Sure To Delete?");
	if(r){
	 return true;
	}
	else{
		return false;
	}
}

function frmFieldObservation_deleteSuccessCallback(result){
	 var keyid=result.successData.SusaId;
		var skeyid=result.successData.Keyid;
		if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined"){
	   		  navigateToPrevForm();	 	 
		   }	
		else if(skeyid!=null && skeyid !=" " && skeyid !="" && skeyid !="undefined"){
			}
}

function frmFieldObservation_FuntLocHierarchy_SuccessCallBack(keyIds){
	var factId = "";
	var sectId = keyIds.sectId;	
	var cellId=keyIds.cellId;
	if (cellId==undefined||cellId=='null'){
		enableFields("cmbfobmJhid");
	}
	else{
		readOnlyFields("cmbfobmJhid");
	}
	
	if (sectId != null){	
	reloadCombo("frmFieldObservation","cmbfobmJhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
}

if (keyIds.cellId!="null"){	
	setFieldValue('cmbfobmJhid',keyIds.cellId);
		reloadCombo("frmFieldObservation","cmbFobdname","employeefield.ehsb?&cellId="+keyIds.cellId);
}
	setFunctionalLocWidth('frmFieldObservation','625px');
}
 
 function isDtl(id){
	 if(jQuery("#"+id).val().trim().length>0)
		jQuery('#hdnIsDtlTrue').val("true");	
		var data=jQuery("hdnIsDtlTrue").val();
}

 jQuery("#btnClear").click(function(){	
	 clearField("cmbFobdname");
	 clearField("cmbFobdUnsafeact");
	 clearField("cmbFobdBadge");
	 clearField("txtFobdremarks");
	 jQuery('#imgFieldObservation').attr('src', "");
	 jQuery('#imgFOName').val("");
	 var row = jQuery("#UnsafeActGrd").jqGrid('getDataIDs');
	 jQuery('input:checkbox[id=jqg_UnsafeActGrd_'+row+']').attr('checked',false);
	});
 
jQuery("#btnBadgeTracker").click(function(){
	 var MasterId=jQuery("#txtSusnKeyid").val(); 
     var JHId=getFieldValue("cmbfobmJhid");
//     alert(JHId);
     var  shift=getFieldValue("cmbFobmShiftid");
  //   alert(shift);
     var Detectiondate=getFieldValue("dteFobmDetectiondate");
    // alert(Detectiondate);
     var detectedby=getFieldValue("cmbFobddetectedby");
    //alert(detectedby);
  var ds = "?&MasterId="+MasterId+"&shift="+shift+"&Detectiondate="+Detectiondate+"&detectedby="+detectedby+"&JHId="+JHId;
  
  if(MasterId.length>0){
	    LoadPopUp("AddBadgeTrcker","BadgeTackPopup_input.ehsb"+ds, true,"1170px","500px","5%","5%","","Badge Tracker","",false);  
  }
  else{
	  alert("Please Save Field Observation First");
	  return false;
  }
});


jQuery("#btnDelete").click(function(){
	var detailsid= jQuery("#hdndetailid").val();
	if(detailsid.length>0){
		processAjaxCalls("FieldObservationdelete.ehsb","&detailsid="+detailsid,"FOremove_successCallBack","");
	}
	else{
		alert("Please click the Grid Row");
		return false;
	}
});	

function FOremove_successCallBack(result){	
	 alert("Data Deleted Successfully");
	 jQuery("#MainActGrd").trigger("reloadGrid");
	 jQuery("#UnsafeActGrd").trigger("reloadGrid");
	 clearField("cmbFobdname");
	 clearField("cmbFobdUnsafeact");
	 clearField("cmbFobdBadge");
	 clearField("txtFobdremarks");
}


</script>
<form id="frmFieldObservation" name="frmFieldObservation">
	<div id="wrapper" > 
		<table>
			<tr>
				<td colspan="3" >
					  <div  id="frmFieldObservationFuntKeyIds">
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu} "  ></input>	
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>		
					<input type="hidden" id="section" name="cmbFobmDmtid" value="${requestScope.fieldObservationmst.fobmDmtid}"></input>
					<input type="hidden" id="cell" name="cmbFobmJhid" value="${requestScope.fieldObservationmst.fobmJhid}"  ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.mchId} "  ></input>
					<input type="hidden" id="flid" name="cmbFobmFlid" value="${requestScope.fieldObservationmst.fobmFlid}"></input>	
				</div>  
				
			 	<div id="NewFOLoggingfunLocation" style="width:123%;margin-top:-5px;"></div>
			</td>
			
			
		  <td>
		   <div style="margin-top:-4px;margin-left:-475px;">
		  <span style="margin-left:0px;" >   <label class="mandatory-lbl"> Date</label></span>	
			  <div style="margin-left:-0px;top:0px;">
			  
			  <input type="text" class="easyui-text"  id="dteFobmDetectiondate" name="dteFobmDetectiondate" maxlength="10" style=" width : 120px;height:40px; text-align:left;" value="${requestScope.fieldObservationmst.fobmDetectiondate}"/>
						  </div>
	</div>
			   </td> 
			  <td>	  
			    <div style="margin-left:-340px;margin-top:0px;" >
				  <div class="easyui-paddingbfpx" style="margin-top:0px;margin-left:0px;"><label class="">Document No.</label></div>
			   
				  <input type="text" class="easyui-text"  id="txtFobmKeyid" name="txtFobmKeyid" maxlength="10"  style=" width :220px;height:24px; text-align:left;" value="${requestScope.fieldObservationmst.fobmKeyid}"/> 
				  </div> 
			</td>
	
	  		  
		<tr>
			   <td >
		 		<div style="margin-left:0px; margin-top:10px;"><label class="mandatory-lbl"> JH</label></div>
				<div style="margin-left:0px;"  class="easyui-paddingbfpx">
				<input class="easyui-combobox" id="cmbfobmJhid" name="cmbfobmJhid"  style="width:360px;"  value="${requestScope.fieldObservationmst.fobmJhid}" />
				</div>
	 		 
	
			<div style="margin-left:400px;margin-top:-40px;"><label class="" id="lblNoofperson">Start Time</label></div>
			<div style="margin-left:400px;margin-top:-0px;"  class="easyui-paddingbfpx">
			 <input  id="spnFobdstarttime"  name="spnFobdstarttime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.fieldObservationDtl.fobdstarttime}"  style="width: 100px;" onblur="isDtl('spnFobdstarttime');"/>
             </div>
           
            
           
			 <div style="margin-left:530px;margin-top:-40px;" ><label class="" id="lblNoofperson">Finish Time</label></div>  
			<div style="margin-left:530px;margin-top:-0px;"  class="easyui-paddingbfpx">
			   <input  id="spnFobdFinishtime"  name="spnFobdFinishtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.fieldObservationDtl.fobdFinishtime}"  style="width: 100px;" onblur="isDtl('spnFobdFinishtime');" />
              </div>
             
             
              			 <div style="margin-left:660px;margin-top:-45px;">  <label id="lblShift" class="mandatory-lbl" style="margin-left:0px;">Shift</label></div>
			<div style="margin-left:660px;margin-top:-0px;"  class="easyui-paddingbfpx">
			<input class="easyui-combobox" id="cmbFobmShiftid" name="cmbFobmShiftid"  style=" width : 120px; " value="${requestScope.fieldObservationmst.fobmShiftid}" />
		
			</div>
			
			  <div style="margin-left:800px;margin-top:-45px;">     <label class="mandatory-lbl">Detected By</label> </div>  
			  <div style="margin-left:800px;margin-top:0px;"  class="easyui-paddingbfpx">
			 <input type="text" class="easyui-combobox"  id="cmbFobddetectedby" name="cmbFobddetectedby" onblur="isDtl('cmbFobddetectedby');" maxlength="10"  style=" width :220px;height:25px; text-align:left;" value="${requestScope.fieldObservationDtl.fobddetectedby}"/>					     
		</div>
		
		 <div style="margin-left:1035px;margin-top:-30px;">
<input type="button" class="easyui-button" id="btnBadgeTracker" name="btnBadgeTracker" value="BadgeTracker" style=" width :100px;height:30px;"/>
</div>	
					     
			 		      					      
			    
 		</td>
   </tr>
   </table>
      <div class="sub-header" style="text-align: left;float:left;width:1180px; width:450px\9;height:18px\9;position:relative;margin-left:5px;">
 <span style="position:absolute;">Field Observation</span></div>
   <table>
     <tr>
     <td>
      <div style="margin-left:0px;margin-top:-60px;">     <label class="mandatory-lbl">Name</label> </div>  
			  <div style="margin-left:0px;margin-top:0px;"  class="easyui-paddingbfpx"> 
			 <input type="text" class="easyui-combobox"  id="cmbFobdname" name="cmbFobdname" maxlength="10"  style=" width :250px;height:25px; text-align:left;"onblur="isDtl('cmbFobdname');" value=""/>					     
		</div>

	</td>
 	<td>
	<div id="imgEmp" style="margin-left:430px;margin-top:10px;"><img id="imgFieldObservation" name="imgFieldObservation" src="" width="100px" height="100px"/>
<span><input type="button" class="easyui-button" id="dlgImg" name="dlgImg" value="+"style="width:25px;"/></span>
	<span style="padding-left:40px " ><input type="button" class="easyui-button" id="btnImgClear" name="btnImgClear" value="-" style="width:26px;"/></span>
	</div>	
</td> 
</tr>
<tr>
<td>

		 <td>
  <div style="margin-left:-250px;margin-top:-85px;">     <label class="mandatory-lbl">UnsafeActs Commited</label> </div>  
 <div style="margin-left:-250px;margin-top:0px;"  class="easyui-paddingbfpx">
<%--  <input type="text" class="easyui-combobox"  id="cmbFobdBadge" name="cmbFobdBadge" maxlength="10"  style=" width :250px;height:25px; text-align:left;" onblur="isDtl('cmbFobdBadge');" value="${requestScope.fieldObservationDtl.fobdBadge}"/>					     
	 --%>
	  <input type="text" class="easyui-combobox"  id="cmbFobdUnsafeact" name="cmbFobdUnsafeact"  onblur="isDtl('cmbFobdUnsafeact');"maxlength="10"  style=" width :250px;height:25px; text-align:left;"  value=""/>					     
			
		</div>
		 </td> 
		 <td>
  <div style="margin-left:20px;margin-top:-135px;">	 
<label style="">Remarks</label>
<div style="" >
 <textarea maxlength="1000" rows="2" cols="80"  style="width:300px; " id="txtFobdremarks" name="txtFobdremarks" onblur="isDtl('txtFobdremarks');"></textarea>
</div>
</div>

 <div style="margin-left:20px;margin-top:25px;">
<input type="button" class="easyui-button" id="btnSaveField" name="btnSaveField" value="Save" style=" width : 70px;height:30px;margin-top:0px;"/>
</div>
 <div style="margin-left:120px;margin-top:-30px;">
<input type="button" class="easyui-button" id="btnClear" name="btnClear" value="Clear" style=" width : 70px;height:30px;"/>
</div>

 <div style="margin-left:220px;margin-top:-30px;">
<input type="button" class="easyui-button" id="btnDelete" name="btnDelete" value="Delete" style=" width : 70px;height:30px;"/>
</div>

</td>
<td>
  <div style="margin-left:-1105px;margin-top:-35px;">     <label class="mandatory-lbl">Badge</label> </div>  
			  <div style="margin-left:-1105px;margin-top:0px;"  class="easyui-paddingbfpx">
		 <input type="text" class="easyui-combobox"  id="cmbFobdBadge" name="cmbFobdBadge" maxlength="10"  style=" width :250px;height:25px; text-align:left;" onblur="isDtl('cmbFobdBadge');" value=""/>					     
							
		</div></td>

</tr> 
		   
		 
</table>

</div> 
    
<div style="margin-left:320px;margin-top:-140px;">
	<table id="UnsafeActGrd" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="UnsafeActGrdpager"></div>
	 </div> 
	 
	 <div style="margin-left:50px;margin-top:10px;overflow-x:hidden;">
	<table id="MainActGrd" style="overflow-x:hidden;"> <tr> <td> </td></tr>
	 </table>
	  <div id="MainActGrdpager"></div>
	 </div>

<input type="hidden" id="mode" name="mode" value="create">
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}">
<input type="hidden" id="hdnmodes" name="hdnmodes" value="">
<input type="hidden" id="hdndetailid" name="hdndetailid" value="">
<input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.keyId}"/>
<input type="hidden" id="hdnUserId" name="hdnUserId" value="${requestScope.roleName}">
<input type="hidden" id="txtSusnKeyid" name="txtSusnKeyid" value="${requestScope.keyId}"/>
<input type="hidden" id="hdnIsDtlTrue" name="hdnIsDtlTrue" value="" />
<input type="hidden" id="hdnuserid" name="hdnuserid" value="${requestScope.user}" />
<input type="hidden" id="userid" name="userid" value="${requestScope.id}" />
<input type="hidden" id="imgQCName" name="imgQCName" value="" />
<input type="hidden" id="hdnFobdphoto" name="hdnFobdphoto" value="${requestScope.fieldObservationDtl.fobdphoto}" />
<input type="hidden" id="hdnFieldimagepath" name="hdnFieldimagepath" value="${requestScope.fieldObservationDtl.fobdphoto}" />
<input type="hidden" id="hdnpath" name="hdnpath" value="${requestScope.fieldObservationDtl.fobdphoto}" />
<input type="hidden" id="hdnkey" name="hdnkey" value="${requestScope.fieldObservationmst.fobmKeyid}" />
<input type="hidden" id="hdndetailsid" name="hdndetailsid" value="${requestScope.detailsid}">
<input type="hidden" id="hdnFlid" name="hdnFlid" value="${requestScope.flid}">
<input type="hidden" id="hdnfieldNo" name="hdnfieldNo" value="${requestScope.fieldNo}"/>
<input type="hidden" id="hdnjhid" name="hdnjhid" value="${requestScope.jhid}"/>
<input type="hidden" id="hdnlogdate" name="hdnlogdate" value="${requestScope.logdate}"/>
<input type="hidden" id="hdnshiftid" name="hdnshiftid" value="${requestScope.shiftid}"/>
<input type="hidden" id="hdndetectedby" name="hdndetectedby" value="${requestScope.detectedby}"/>
<input type="hidden" id="hdnbadgettype" name="hdnbadgettype" value="${requestScope.badgettype}"/>
<input type="hidden" id="hdnempid" name="hdnempid" value="${requestScope.empid}"/>
<input type="hidden" id="hdnunsafeact" name="hdnunsafeact" value="${requestScope.unsafeact}"/>
<input type="hidden" id="hdnremarks" name="hdnremarks" value="${requestScope.remarks}"/>
<input type="hidden" id="hdnrecall" name="hdnrecall" value="${requestScope.recall}"/>
<input type="hidden" id="hdncurrentdate" name="hdncurrentdate" value="${requestScope.currentdate}"></input>
<input type="hidden" id="hdnRolename" name="hdnRolename" value="${requestScope.rolename}"> 
</form>
