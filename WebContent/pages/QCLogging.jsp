<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
jQuery(document).ready(function(){
	initialiseForm('frmQCLogging');
	jQuery('#submitForm').val('frmQCLogging');
	readOnlyFields("txtSusnKeyid");
	var factId = jQuery("#frmQCLogging input[id='factory']").val();
	var sectionId = jQuery("#frmQCLogging input[id='section']").val();
	var cellId = jQuery("#frmQCLogging input[id='cell']").val();
	var machId = jQuery("#frmQCLogging input[id='machine']").val();
	readOnlyFields("cmbQclmShiftid");
	numericTextBox("cmbQclddetectedby");
	disableField("frmQCLogging", "cmbQclddetectedby")
	jQuery('#cmbQclddetectedby').combobox("disable");
	formatDateBox('dteQclmDetectiondate','dd-MMM-yyyy');
	
	
	var rolename=jQuery("#hdnRolename").val();
//	alert(rolename);
	
	if(rolename=="QUALITY CONTROL"){
	   		
	}
	else{
		alert("Access Denied");
		navigateToPrevForm();
		return false;
	}
	
	var DetectDate = getFieldValue("dteQclmDetectiondate");
	if( DetectDate == null || DetectDate.length < 1)
	{
		fillWithCurrentDate("dteQclmDetectiondate");
		formatDateBox('dteSusnDate','dd-MMM-yyyy');
		fillWithCurrentDate("spnQcldstarttime");
		fillWithCurrentDate("spnQcldFinishtime")
	}
	var filterString="";
    var mode=jQuery("#hdnmode").val();
	if(mode=="view"){
		disableForm("frmQCLogging");
			readOnlyFields('txtQclmKeyid');
			readOnlyFields("cmbQclmShiftid");
			readOnlyFields("txtQcldremarks");
			readOnlyFields("spnQcldstarttime");
			readOnlyFields("spnQcldFinishtime");
			numericTextBox("cmbQclddetectedby");
			disableUIButton("btnSaveUnsafe");
			numericTextBox("cmbQclmShiftid");
			jQuery("#cmbQclmShiftid").attr("disabled",true);
			 setTimeout(function() {readOnlyFields('cmbQclmShiftid');},1250);
			 setTimeout(function() {readOnlyFields('cmbQcldUnsafeact');},1250);
			 setTimeout(function() {readOnlyFields('cmbQclmJhid');},1250);
			disableField("frmQCLogging", "cmbQclmShiftid")
			disableField("frmQCLogging", "cmbQclddetectedby")
			jQuery('#cmbQclmShiftid').combobox("disable");
			jQuery('#cmbQclddetectedby').combobox("disable");
			jQuery("#dteAbnmTargetdate").datebox("disable");
		}	
  
    var cellId = jQuery("#frmQCLogging input[id='cell']").val();
	var flid = jQuery("#frmQCLogging input[id='flid']").val();
	fillComboBox('frmQCLogging','cmbName','employee.commonFilter');
	fillComboBox('frmQCLogging','cmbbadge','employee.commonFilter');
	fillComboBox('frmQCLogging','cmbQcldEmpkeyid','employee.commonFilter');
	fillComboBox('frmQCLogging','cmbQcldUnsafeact','unsafeacts.ehsb');
	fillComboBox('frmQCLogging','cmbQclddetectedby','employee.commonFilter?user="+user');

	fillComboBox("frmQCLogging","cmbQclmJhid","cellCombo.commonFilter");
	fillComboBox("frmNewMom","cmbQclmShiftid","shift.commonFilter");
	getCurrentShiftQC("cmbQclmShiftid");
	 spinnerKeyPress('spnQcldstarttime');
	 spinnerKeyPress('spnQcldstarttime');
	 spinnerKeyPress('spnQcldstarttime');
	 spinnerChange('spnQcldstarttime','detectionDateEvt');
     spinnerUp('spnQcldstarttime','detectionDateEvt');
     spinnerDown('spnQcldstarttime','detectionDateEvt');
	 spinnerKeyPress('spnQcldFinishtime');
	 spinnerKeyPress('spnQcldFinishtime');
	 spinnerKeyPress('spnQcldFinishtime');
	 spinnerChange('spnQcldFinishtime','detectionDateEvt1');
     spinnerUp('spnQcldFinishtime','detectionDateEvt1');
     spinnerDown('spnQcldFinishtime','detectionDateEvt1');
    
     var KeyId=jQuery("#txtQclmKeyid").val();
     if(KeyId!=null && KeyId.length!=0){  		
      	var DetecionDate=jQuery("#dteQclmDetectiondate").datebox("getValue");
      	 jQuery("#dteQclmDetectiondate").datebox("setValue",DetecionDate.substring(0,11));
      	var starttime=jQuery('#spnQcldstarttime').spinner('getValue');
     	jQuery("#spnQcldstarttime").spinner("setValue",starttime.substring(12,20));
        var finishtime=jQuery("#spnQcldFinishtime").spinner("getValue");
     	jQuery("#spnQcldFinishtime").spinner("setValue",finishtime.substring(12,20));
      	 var Shift=jQuery("#cmbQclmShiftid").combobox("getValue");
      	 jQuery("#cmbQclmShiftid").combobox("setValue",Shift);
      	 disableField("frmQCLogging","cmbQclmShiftid");
  	   
  	   
     }
     
       if (flid != null)
     {	
     	   loadFunctionalLocation("NewQCLoggingfunLocation","functionalLoc.ehsb","NewQCfunLocationValues","frmQCLogging","&flid="+flid);
     	   reloadCombo("frmQCLogging","cmbQclmJhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);

     }
     else
  	   {
  	   loadFunctionalLocation("NewQCLoggingfunLocation","functionalLoc.ehsb","NewQCfunLocationValues","frmQCLogging","");
  	   }

imageUpload(jQuery("#dlgImg"),'ImageUpload.commonFilter','dlgImg',"imgQclogging","imgQCName","380","270",false);
	if(mode=="view"){
	disableForm("frmQCLogging");
		readOnlyFields('txtQclmKeyid');
		readOnlyFields("cmbQclmShiftid");
		readOnlyFields("txtQcldremarks");
		readOnlyFields("spnQcldstarttime");
		readOnlyFields("spnQcldFinishtime");
		numericTextBox("cmbQclddetectedby");
		disableUIButton("btnSaveUnsafe");
		disableField("frmQCLogging", "cmbQclddetectedby")
		jQuery('#cmbQclddetectedby').combobox("disable");
		jQuery("#dteAbnmTargetdate").datebox("disable");
	}
	
});
readOnlyFields('dteQclmDetectiondate');
readOnlyFields('cmbQclddetectedby');
var fromdate = jQuery('#dteQclmDetectiondate').datebox("getValue");
 var imgName=jQuery("#imgQclogging").val();
var imgPath=jQuery("#hdnQcimagepath").val();
		jQuery('#imgQclogging').attr('src', imgPath);
		if(imgName.length>0){
			jQuery('#imgQclogging').attr('src', imgPath);
		}
		else{
			imageUpload(jQuery("#dlgImg"),'ImageUpload.commonFilter','dlgImg',"imgQclogging","imgQCName","380","270",false);
		}
		
		   jQuery("#btnImgClear").click(function() {
				jQuery('#imgQclogging').attr('src', "");
				jQuery('#imgQCName').val("");
				});


var SusaKeyId=jQuery("#txtSusnKeyid").val(); 
var detailsid=jQuery("#hdndetailsid").val();
processGridnew("newEHSBadgeUnsafeAct_input.ehsb","?q=1"+"&detailsid="+detailsid,"UnsafeActGrd","UnsafeActGrdpager"," ","condDoubleClick","","actgridcompletecallback");
processGridnew("QCLoggingGrid_input.ehsb","?q=1"+"&SusaKeyId="+SusaKeyId,"MainActGrd","MainActGrdpager"," ","pcsdblClick","","actgridcompletecallback");
	


function frmQCLoggingcmbQcldUnsafeact_onSelect(record){
	   var fromdate = jQuery('#dteQclmDetectiondate').datebox("getValue");
	var unsafeact = jQuery('#cmbQcldUnsafeact').combobox("getValue");
	processGridnew("QCLogUnsafeAct_input.ehsb","?q=1"+"&formType="+"hi"+"&unsafeact="+unsafeact,"UnsafeActGrd","UnsafeActGrdpager"," ","condDoubleClick","","");

	//processGridnew("newEHSBadgeUnsafeAct_input.ehsb","?q=1"+"&formType="+"hi"+"&unsafeact="+unsafeact,"UnsafeActGrd","UnsafeActGrdpager"," ","condDoubleClick","","actgridcompletecallback");
}

var GblShiftID;
function getCurrentShiftQC(shiftId )
{
	GblShiftID=shiftId;
	processAjaxCalls('getCurrentShift.ehsb','',"getCurrentShiftQCSuccess");
}

function getCurrentShiftQCSuccess(result){		
	readOnlyFields("cmbQclmShiftid");
	setFieldValue(GblShiftID, result.shift);
}

 jQuery("#btnSaveUnsafe").click(function() {
 	var modes=jQuery("#hdnmodes").val();
 	var detailid=jQuery("#hdndetail").val();
  // alert(detailid);
 	var fromdate = jQuery('#dteQclmDetectiondate').datebox("getValue");
 	var unsafeact = jQuery('#cmbQcldUnsafeact').combobox("getValue");
 	var JH=jQuery("#cmbQclmJhid").combobox("getValue");	
	if(JH.length==0){	
	  alert("Select the JH");
	  return false;
	}
	
   if(unsafeact.length==0){
	   alert("Select the UnsafeActCommoited");
	   return false;
   }
    var imgpath= getFieldValue("Qcimagepath");
 	  var imagename =jQuery("#imgQCName").val();
 	//  alert("imagename:::"+imagename);
 	  var image=imagename.lastIndexOf("/");
 	  var imageName= imagename.substring(image+1,imagename.length);
 	  var imagefullname = imageName.toUpperCase();
 	 // alert("imageFullname"+imagefullname);
     var paramconvertArr =convertJsonArr();
// alert(paramconvertArr);
 saveForm("frmQCLogging","QCLoggingDtl_save.ehsb?&fromdate="+fromdate+"&paramconvertArr="+paramconvertArr+"&imagename="+imagefullname+"&modes="+modes+"&unsafeact="+unsafeact+"&detailid="+detailid);
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

function actgridcompletecallback(){
    var mode=jQuery("#mode").val();
    var row = jQuery("#UnsafeActGrd").jqGrid('getDataIDs');
	 var cm = jQuery("#UnsafeActGrd").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		 var detailsid = jQuery("#UnsafeActGrd").jqGrid('getCell',row[i],"hdnQcldKeyid");	
		   if(detailsid.trim().length>0){ 
			    jQuery('#UnsafeActGrd').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqgh_UnsafeActGrd_'+row[i]+']').attr('checked',true);
			    jQuery("#UnsafeActGrd").jqGrid('setCell',row[i],'selctVal','1');
			    jQuery("#UnsafeActGrd").jqGrid('getCell',row[i],"txtQcdcDesciption");
			   jQuery('input:checkbox[id=jqg_UnsafeActGrd_'+row[i]+']').attr('disabled',false);
		   }
	 }	 
}

function detectionDateEvt()
{
	var startdate = jQuery('#dteQclmDetectiondate').datebox('getValue') + jQuery('#spnQcldstarttime').spinner('getValue');
	var enddate=jQuery('#dteQclmDetectiondate').datebox('getValue') + jQuery('#spnQcldstarttime').spinner('getValue');
	var dataString = "&startdate="+startdate+"&enddate="+enddate;
	processAjaxCalls("getCurrentShift.ehsb?"+dataString,"","getCurrentShiftQCSuccess");
}
function detectionDateEvt1()
{
	var detectionDate = jQuery('#dteQclmDetectiondate').datebox('getValue') + jQuery('#spnSusaTime').spinner('getValue');
	var currentDate = getServerDateTime();
}


function BtnFormatterDelete(id, options, rowObject)
{					
	var rowId = options.rowId;
	var gridId = options.gid;
	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
}

function frmQCLogging_beforeSubmit(){

}

function frmQCLogging_successsCallback(result){
	setFieldValue('txtQclmKeyid',SusaId);
	jQuery('#txtQcldremarks').val('');
	jQuery('#cmbQcldUnsafeact').val('');
	clearField("cmbQcldUnsafeact"); 
	clearField("imgQclogging");
	clearField("imgQCName");
	clearField("cmbQcldEmpkeyid");
	jQuery('#imgQclogging').attr('src', "");
	jQuery('#imgQCName').val("");
	var SusaId=result.keyids;
	jQuery("#txtQclmKeyid").val(SusaId);
	var image=result.image;
    jQuery("#UnsafeActGrd").trigger("reloadGrid");
    processGridnew("QCLoggingGrid_input.ehsb","?q=1"+"&SusaId="+SusaId+"&image="+image,"MainActGrd","MainActGrdpager"," ","pcsdblClick","","actgridcompletecallback");

}

 function pcsdblClick(id){ 
	 var time = jQuery("#MainActGrd").jqGrid('getCell',id,3);
	 var unsafeact = jQuery("#MainActGrd").jqGrid('getCell',id,4);
	// alert("Unsafe act"+unsafeact)
	 var nameid = jQuery("#MainActGrd").jqGrid('getCell',id,6);
	// alert("nameid"+nameid);
	 var name = jQuery("#MainActGrd").jqGrid('getCell',id,7);
	// alert("name"+name)
	 var remarks = jQuery("#MainActGrd").jqGrid('getCell',id,9);
	// alert("remarks"+remarks)
	  var image = jQuery("#MainActGrd").jqGrid('getCell',id,10);
	// alert("remarks"+remarks)
	 var masterid = jQuery("#MainActGrd").jqGrid('getCell',id,1);
	// alert("masterid"+masterid)
	 var detailsid= jQuery("#MainActGrd").jqGrid('getCell',id,2);
	// alert("detailid"+detailsid)

		jQuery('#hdnkeyId').val(masterid);
		jQuery('#hdndetail').val(detailsid);
		setFieldValue('hdndetailid',detailsid);
		jQuery('#hdndetailid').val(detailsid)
		setFieldValue('txtQcldremarks',remarks);
	    jQuery('#txtQcldremarks').val(remarks);	
		setFieldValue('cmbQcldUnsafeact',unsafeact);
		 jQuery('#cmbQcldEmpkeyid').val(nameid);	
			setFieldValue('cmbQcldEmpkeyid',nameid);
		setFieldValue('txtQclmKeyid',masterid);
		processGridnew("newEHSBadgeUnsafeAct_input.ehsb","?q=1"+"&detailsid="+detailsid+"&unsafeact="+unsafeact,"UnsafeActGrd","UnsafeActGrdpager"," ","condDoubleClick","","actgridcompletecallback");
	    processAjaxCalls("get_Image.ehsb","?q=2&detailsid="+detailsid ,"getQCImgSuccess","getQCImgErr");
		jQuery('#hdnmodes').val('upd');
		setFieldValue('hdndetailid',detailsid);
		jQuery('#hdndetailid').val(detailsid);
	}
 
function getQCImgSuccess(result){ 
	if(result.empImg.Data == true && result.empImg.Data != 'undefined'){
	jQuery('#imgQclogging').attr('src','');
	jQuery('#imgQclogging').attr('src',result.empImg.imgToimBlobimage);	
	}
	else{
		}
}

jQuery("#imgQclogging").click(function(){
	 var imgName=jQuery("#imgQclogging").val();
	 var detail= jQuery("#hdndetailid").val();
	 LoadPopUp("QCImage","QcImagePopup_input.ehsb?q=2&detail="+detail,true,"60%","60%","20%","80px","","View Image",false,false);
		    
	});

function QCImage_afterClose(){
	setTimeout(function(){
   	jQuery("#MainActGrd").trigger("reloadGrid");
   	},10);		
}

function frmQCLoggingcmbQclmJhid_onSelect(record) 	{
	
	var dataStr="&sectId="+record.id;
	loadFunctionalLocation("NewQCLoggingfunLocation","functionalLoc.ehsb","NewQCfunLocationValues","frmQCLogging",dataStr);
	
	var cellId = jQuery("#frmQCLogging input[id='cell']").val();
	var flid = jQuery("#frmQCLogging input[id='flid']").val();
	reloadCombo("frmQCLogging","cmbPlosLossreason","combo_Phenomena.pcs?cellId="+cellId+"&flid="+flid);
	fnRecall();
}


function fnRecall(){

    var recall=jQuery('#hdnrecall').val();
 //   alert("recall"+recall);
	var mode = jQuery("#frmQCLogging input[id=mode]").val();
    var QcJHId= getFieldValue("cmbQclmJhid");
	var Qcdetcteddate=getFieldValue("dteQclmDetectiondate","frmQCLogging");
	//alert(Qcdetcteddate);
	var Qcshift= getFieldValue("cmbQclmShiftid");
	var hdndte=jQuery("#hdnmomdate").val();
	var Qcdetectedby =getFieldValue('cmbQclddetectedby');
	var ds="?&QcJHId="+QcJHId + "&Qcdetcteddate="+Qcdetcteddate+"&Qcshift="+Qcshift+"&Qcdetectedby="+Qcdetectedby;
    processAjaxCalls("QCRecalling_input.ehsb",ds,"RecallingSuccessData","");
} 



function RecallingSuccessData(result){
	var  SusaKeyId=result[0][0];
//	alert(SusaKeyId);
	setFieldValue("txtQclmKeyid",result[0][0]);
	setFieldValue("cmbFobmCellid",result[0][1]);
	setFieldValue("dteFobmDetectiondate",result[0][2]);
	setFieldValue("cmbFobmShiftid",result[0][3]);
   	setFieldValue("cmbFobddetectedby",result[0][4]);
    setFieldValue("cmbFobdBadge",result[0][5]);
    setFieldValue("cmbFobdname",result[0][6]);
    setFieldValue("cmbFobdUnsafeact",result[0][7]);
    setFieldValue("txtFobdremarks",result[0][8]);
    
    if (SusaKeyId!=null){
    //	alert("Inside if::"+SusaKeyId);
    	processGridnew("QCLoggingGrid_input.ehsb","?q=1"+"&SusaKeyId="+SusaKeyId,"MainActGrd","MainActGrdpager"," ","pcsdblClick","","actgridcompletecallback");    	
    }
    
}



function frmQCLogging_beforeDelete(){
	var SusaKeyId=jQuery("#txtSusnKeyid").val();
	var r=confirm("Are You Sure To Delete?");
	if(r){
	 return true;
	}
	else{
		return false;
	}
}


function frmQCLogging_deleteSuccessCallback(result){
	 var keyid=result.successData.SusaId;
		var skeyid=result.successData.Keyid;
		if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined"){
		      ////////alert(result.successData.msg);
	   		  navigateToPrevForm();	 	 
		   }	
		else if(skeyid!=null && skeyid !=" " && skeyid !="" && skeyid !="undefined"){
			   ////////alert(result.successData.msg);
			}
}

function frmQCLogging_FuntLocHierarchy_SuccessCallBack(keyIds){
	var factId = "";
	var sectId = keyIds.sectId;	
	var cellId=keyIds.cellId;
	if (cellId==undefined||cellId=='null'){
		enableFields("cmbQclmJhid");
	}
	else{
		readOnlyFields("cmbQclmJhid");
	}
if (sectId != null){
	reloadCombo("frmQCLogging","cmbQclmJhid","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
}
if (keyIds.cellId!="null"){
	setFieldValue('cmbQclmJhid',keyIds.cellId);
}
	setFunctionalLocWidth('frmQCLogging','625px');
}



function isDtl(id){
	 if(jQuery("#"+id).val().trim().length>0)
		jQuery('#hdnIsDtlTrue').val("true");	
		var data=jQuery("hdnIsDtlTrue").val();
}

jQuery("#btnClear").click(function(){
	 clearField('cmbQcldUnsafeact');
	 clearField('txtQcldremarks');
	 jQuery('#imgQclogging').attr('src', "");
	 jQuery('#imgQCName').val("");
	 var row = jQuery("#UnsafeActGrd").jqGrid('getDataIDs');
	 jQuery('input:checkbox[id=jqg_UnsafeActGrd_'+row+']').attr('checked',false);
	});
	
jQuery("#btnDelete").click(function(){
	var detailsid= jQuery("#hdndetailid").val();
	if(detailsid.length>0){
		processAjaxCalls("QCLoggingdelete.ehsb","&detailsid="+detailsid,"QCremove_successCallBack","");
	}
	else{
		alert("Please click the Grid Row");
		return false;
	}
});	

function QCremove_successCallBack(result){	
	 alert("Data Deleted Successfully");
	 jQuery("#MainActGrd").trigger("reloadGrid");
	 jQuery("#UnsafeActGrd").trigger("reloadGrid");
	 clearField("txtQcldremarks");
	 clearField("cmbQcldUnsafeact");
	 clearField("cmbQcldEmpkeyid");
	 jQuery('#imgQclogging').attr('src', "");
	 jQuery('#imgQCName').val("");
	 
	 
}

</script>
<form id="frmQCLogging" name="frmQCLogging">
	<div id="wrapper" > 
		<table>
			<tr>
				<td colspan="3" >
					  <div  id="frmQCLoggingFuntKeyIds">
					<input type="hidden" id="factory" name="cmbsusnFactoryid" value=" "  ></input>			
					<input type="hidden" id="section" name="cmbQclmDmtid" value="${requestScope.qcLoggingmst.qclmDmtid}"></input>
					<input type="hidden" id="cell" name="cmbQclmJhid" value="${requestScope.qcLoggingmst.qclmJhid}"  ></input>
					<input type="hidden" id="machine" name="cmbsusnEquipmentid" value=" "  ></input>
					<input type="hidden" id="flid" name="cmbQclmFlid" value="${requestScope.qcLoggingmst.qclmFlid}"></input>	
				</div>
				
			 	<div id="NewQCLoggingfunLocation" style="width:123%;margin-top:-5px;"></div>
			</td>
			
			
		  <td>
		   <div style="margin-top:-4px;margin-left:-360px;">
		  <span style="margin-left:0px;" >   <label class="mandatory-lbl"> Date</label></span>	
			  <div style="margin-left:-0px;top:0px;">
			  
			  <input type="text" class="easyui-text"  id="dteQclmDetectiondate" name="dteQclmDetectiondate" maxlength="10" style=" width : 120px;height:40px; text-align:left;" value="${requestScope.qcLoggingmst.qclmDetectiondate}"/>
						  </div>
	</div>
			   </td> 
			  <td>	  
			    <div style="margin-left:-220px;margin-top:0px;" >
				  <div class="easyui-paddingbfpx" style="margin-top:0px;margin-left:0px;"><label class="">Document No.</label></div>
			   
				  <input type="text" class="easyui-text"  id="txtQclmKeyid" name="txtQclmKeyid" disabled="disabled" maxlength="10"  style=" width :220px;height:24px; text-align:left;" value="${requestScope.qcLoggingmst.qclmKeyid}"/> 
				  </div> 
			</td>
	
	  		  
		<tr>
			   <td >
		 		<div style="margin-left:0px; margin-top:10px;"><label class="mandatory-lbl"> JH</label></div>
				<div style="margin-left:0px;"  class="easyui-paddingbfpx">
				<input class="easyui-combobox" id="cmbQclmJhid" name="cmbQclmJhid"  style="width:360px;"  value="${requestScope.qcLoggingmst.qclmJhid}" />
				</div>
	 		 
	
			<div style="margin-left:400px;margin-top:-40px;"><label class="" id="lblNoofperson">Start Time</label></div>
			<div style="margin-left:400px;margin-top:-0px;"  class="easyui-paddingbfpx">
			 <input  id="spnQcldstarttime"  name="spnQcldstarttime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.qCLoggingDtl.qcldstarttime}"  style="width: 100px;" onblur="isDtl('spnQcldstarttime');"/>
             </div>
           
            
           
			 <div style="margin-left:530px;margin-top:-40px;" ><label class="" id="lblNoofperson">Finish Time</label></div>  
			<div style="margin-left:530px;margin-top:-0px;"  class="easyui-paddingbfpx">
			   <input  id="spnQcldFinishtime"  name="spnQcldFinishtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.qCLoggingDtl.qcldstarttime}"  style="width: 100px;" onblur="isDtl('spnQcldFinishtime');" />
              </div>
             
             
              			 <div style="margin-left:660px;margin-top:-45px;">  <label id="lblShift" class="mandatory-lbl" style="margin-left:0px;">Shift</label></div>
			<div style="margin-left:660px;margin-top:-0px;"  class="easyui-paddingbfpx">
			<input class="easyui-combobox" id="cmbQclmShiftid" name="cmbQclmShiftid"  style=" width : 120px; " value="${requestScope.qcLoggingmst.qclmShiftid}" />
		
			</div>
			
			  <div style="margin-left:800px;margin-top:-45px;">     <label class="mandatory-lbl">Detected By</label> </div>  
			  <div style="margin-left:800px;margin-top:0px;"  class="easyui-paddingbfpx">
			 <input type="text" class="easyui-combobox"  id="cmbQclddetectedby" name="cmbQclddetectedby" maxlength="10"  style=" width :220px;height:25px; text-align:left;" value="${requestScope.qCLoggingDtl.qclddetectedby}"/>					     
		</div>
			
					     
			 		      					      
			    
 		</td>
   </tr>
   </table>
   <div class="sub-header" style="text-align: left;float:left;width:1200px; width:450px\9;height:18px\9;position:relative;margin-left:5px;">
 <span style="position:absolute;">Unsafe Logging By QC Team</span></div>
   <table>
     <tr>
     <td>
      

		   <div style="margin-left:0px;margin-top:-70px;">     <label class="mandatory-lbl">UnsafeActs Commited</label> </div>  
			  <div style="margin-left:0px;margin-top:0px;"  class="easyui-paddingbfpx">
			 <input type="text" class="easyui-combobox"  id="cmbQcldUnsafeact" name="cmbQcldUnsafeact" maxlength="10"  style=" width :300px;height:25px; text-align:left;" onblur="isDtl('cmbQcldUnsafeact');" value="${requestScope.qCLoggingDtl.qcldUnsafeact}"/>					     
								
		</div>
		
	</td>
	<td>
	 <div style="margin-left:-300px;margin-top:45px;">     <label>Name</label> </div>  
			  <div style="margin-left:-300px;margin-top:0px;"  class="easyui-paddingbfpx"> 
			 <input type="text" class="easyui-combobox"  id="cmbQcldEmpkeyid" name="cmbQcldEmpkeyid" maxlength="10"  style=" width :300px;height:25px; text-align:left;"onblur="isDtl('cmbQcldEmpkeyid');" value=""/>					     
		</div>
	</td>
	<td>
	<div id="imgEmp" style="margin-left:450px; margin-top:-15px;"><img id="imgQclogging" name="imgQclogging" src="" width="100px" height="100px"/>
		
	</div>	


<td>
  <div style="margin-left:-105px;margin-top:125px;"> 
<span style="padding-left:0px" ><input type="button" class="easyui-button" id="dlgImg" name="dlgImg" value="+" style="width:25px;"/></span>
		<span style="padding-left:45px" ><input type="button" class="easyui-button" id="btnImgClear" name="ImgClear" value="-" style="width:25px;"/></span>
</div>		
</td>


<%--   <div style="margin-left:0px;margin-top:-34px;">     <label class="mandatory-lbl">Badge</label> </div>  
			  <div style="margin-left:0px;margin-top:0px;"  class="easyui-paddingbfpx">
			 <input type="text" class="easyui-combobox"  id="cmbbadge" name="cmbbadge" maxlength="10"  style=" width :300px;height:25px; text-align:left;" value="${requestScope.qcLoggingmst.susnPreparedby}"/>					     
		</div>
		 </td> 
		 <td> --%>
		 <td>
<div style="margin-left:25px;margin-top:-70px;">  		 
<label style="margin-left:-0px;">Remarks</label>
<div style="margin-left:-0px;" >
 <textarea maxlength="1000" rows="2" cols="80"  style="width : 320px; " id="txtQcldremarks" name="txtQcldremarks" onblur="isDtl('txtQcldremarks');">${requestScope.qCLoggingDtl.qcldremarks}</textarea>
</div>
</div>
</td>
<td>
 <div style="margin-left:-320px;margin-top:70px;">
<input type="button" class="easyui-button" id="btnSaveUnsafe" name="btnSaveUnsafe" value="Save" style=" width : 70px;height:30px;"/>
</div>
</td>

<td>
 <div style="margin-left:-220px;margin-top:70px;">
<input type="button" class="easyui-button" id="btnClear" name="btnClear" value="Clear" style=" width : 70px;height:30px;"/>
</div>
</td>

<td>
 <div style="margin-left:-130px;margin-top:70px;">
<input type="button" class="easyui-button" id="btnDelete" name="btnDelete" value="Delete" style=" width : 70px;height:30px;"/>
</div>
</td>
</tr> 
		   
		 
</table>
</div> 

<div style="margin-left:380px;margin-top:-147px;">
	<table id="UnsafeActGrd" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="UnsafeActGrdpager"></div>
	 </div> 
	 
	 <div style="margin-left:50px;margin-top:20px;">
	<table id="MainActGrd" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="MainActGrdpager"></div>
	 </div>
	
<input type="hidden" id="hdnmodes" name="hdnmodes" value="">
<input type="hidden" id="hdndetailid" name="hdndetailid" value="">
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}">
<input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.keyId}"/>
<input type="hidden" id="hdnUserId" name="hdnUserId" value="${requestScope.roleName}">
<input type="hidden" id="txtSusnKeyid" name="txtSusnKeyid" value="${requestScope.keyId}"/>
<input type="hidden" id="hdnIsDtlTrue" name="hdnIsDtlTrue" value="" />
<input type="hidden" id="hdnuserid" name="hdnuserid" value="${requestScope.user}" />
<input type="hidden" id="imgQCName" name="imgQCName" value="${requestScope.imgName}" />
<input type="hidden" id="hdnQcimagepath" name="hdnQcimagepath" value="${requestScope.qCLoggingDtl.qcldphoto}" />
<input type="hidden" id="hdndetailsid" name="hdndetailsid" value="${requestScope.detailsid}">
<input type="hidden" id="hdncurrentdate" name="hdncurrentdate" value="${requestScope.currentdate}">
<input type="hidden" id="hdndetail" name="hdndetail" value="">
<input type="hidden" id="hdndetailid" name="hdndetailid" value="">
<input type="hidden" id="hdnrecall" name="hdnrecall" value="">
<input type="hidden" id="hdnjhid" name="hdnjhid" value="${requestScope.jhid}"/>
<input type="hidden" id="hdnlogdate" name="hdnlogdate" value="${requestScope.logdate}"/>
<input type="hidden" id="hdnshiftid" name="hdnshiftid" value="${requestScope.shiftid}"/>
<input type="hidden" id="hdndetectedby" name="hdndetectedby" value="${requestScope.detectedby}"/>
<input type="hidden" id="hdnRolename" name="hdnRolename" value="${requestScope.rolename}"/>
</form>


 