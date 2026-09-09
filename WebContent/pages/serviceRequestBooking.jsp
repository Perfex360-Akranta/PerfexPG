<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">	
jQuery(document).ready(function(){
	
	  //alert("page check"); 
	  initialiseForm('frmServiceBooking'); 
	  fillComboBox("frmServiceBooking","cmbSermReportedby","employee.commonFilter");  
	  fillComboBox("frmServiceBooking","cmbSermBookedby","employee.commonFilter");
	  fillComboBox("frmServiceBooking","cmbSermKeyid","serviceIdCombo.serv");
	  fillComboBox("frmServiceBooking","cmbSermMenu","fillCombo.mastertblconfig?controlId=wrmlmenuno&genmstmenuid=MNUWORKFLOWMENULINK&");
	  fillComboBox("frmServiceBooking","cmbSermSbmtMode","serviceSbmtMode.serv");
	  fillComboBox("frmServiceBooking","cmbSermPrbType","servicePrbType.serv");

	  formatDateBox('dteSermDate','dd-MMM-yyyy');
	  fillWithCurrentDate('dteSermDate');
	  //alert("Line error check1");
	  formatDateBox('dteSermExpTargetDate','dd-MMM-yyyy');
	  //spinnerKeyPress(spnSermTime);
  	  //alert("Line error check2");

	  jQuery('#submitForm').val('frmServiceBooking'); 
	  readOnlyFields('cmbSermBookedby');
	  readOnlyFields('dteSermDate'); 	
	  readOnlyFields('spnSermTime');
	  var mode= jQuery('#mode').val();
	  jQuery('#frmServiceBooking .easyui-text').css('text-transform', '');
	  jQuery('#frmServiceBooking textarea').css('text-transform', '');	
	  //alert("mode"+mode);
	  mode=mode.toUpperCase();
	 // alert("mode:"+mode);
	  var status =jQuery('#hdnStatus').val(); 		 
	  	if(status=="B"){			
			jQuery('#txtSermStatus').val("Booked");
		  }else if(status=="A"){
			jQuery('#txtSermStatus').val("Allocated"); 	
		  }else if(status=="R"){
			jQuery('#txtSermStatus').val("Cancelled"); 	
		  }else if(status=="C"){
			jQuery('#txtSermStatus').val("Completed"); 	
		  }else if(status=="S"){
			jQuery('#txtSermStatus').val("Approved"); 	
		  }else if(status=="O"){
			  jQuery('txtSermStatus').val("Reopen");
			  
		  }
	
		  
		  if(mode == "CREATE")
			{
			     //alert("mode:"+mode);  
	  			 jQuery('#txtSermStatus').val("Booking");	
	  			 jQuery('#btnAllocate').css('display','none');
	  			 jQuery('#btnApproval').css('display','none');
	  			 fillWithCurrentDate('dteSermDate'); 
	  			 fillWithCurrentDate('dteSermExpTargetDate');
	  			 fillWithCurrentDate('spnSermTime');
	  			 readOnlyFields('cmbSermKeyid');
	  			  jQuery("#chkSermCompOk").hide();
	  			  jQuery("#chkSermCompNotOk").hide();
	  			  jQuery("#lblComp").hide();
	  			  jQuery("#lblCompOk").hide();
	  			  jQuery("#lblCompNotOk").hide();
	  			 imageUpload(jQuery( "#dlgAddImgFile" ),'ImageUpload.commonFilter','dlgAddImgFile',"imgFile","imgFilename","314","283",false);
				 //alert("value of time"+jQuery('#spnSermTime').val());
			      
		  	}	
		  
	  		else if(mode=="EDIT" || mode=="MODIFY")
	  		  {	
	  			 		 
	  			 //alert("mode"+mode);
			  	 jQuery('#txtSermStatus').val("Booking");	
				 jQuery('#btnAllocate').css('display','none');
				 jQuery('#btnApproval').css('display','none');
				 readOnlyFields('cmbSermKeyid');
				 readOnlyFields('chkSermCompOk');
				 readOnlyFields('chkSermCompNotOk');
				 imageUpload(jQuery( "#dlgAddImgFile" ),'ImageUpload.commonFilter','dlgAddImgFile',"imgFile","imgFilename","314","283",false);
				 //alert("value of time"+jQuery('#spnSermTime').val());
	  		  }
	  		  else{
	  			  
	  		  } 
		  if(mode == "APPROVAL" )
		  {
			// alert(mode);
		    jQuery('#btnAllocate').css('display','none');
			//jQuery('#txtSermRemarks').attr("disabled",false);
			//jQuery('#txtSermDescription').attr("disabled",true);
			//jQuery('#txtSermRemarks').attr("disabled",true);
			//readOnlyFields('cmbSermPrbType');
			readOnlyFields('cmbSermMenu');
			jQuery('#txtSermFormname').attr("disabled",true);
			jQuery('#dlgAddImgFile').attr("disabled",true);
			jQuery('#btnImgFileClear').attr("disabled",true);
			readOnlyFields('cmbSermSbmtMode');
			readOnlyFields('cmbSermReportedby');
			//readOnlyFields('dteSermExpTargetDate');
			  jQuery("#chkSermCompOk").hide();
			  jQuery("#chkSermCompNotOk").hide();
			  jQuery("#lblComp").hide();
			  jQuery("#lblCompOk").hide();
			  jQuery("#lblCompNotOk").hide();
			  var hdndisableAllocation =jQuery('#hdndisableAllocation').val(); 
			  if(hdndisableAllocation != null && hdndisableAllocation != '' && hdndisableAllocation != ' ')
			  {
				 jQuery('#btnAllocate').css('display','none');				
			  }
		 	  readOnlyFields('cmbSermKeyid');
	
		  }
		  else
		  {
			  
		  }
		  if(mode == "COMPLETE")
		  {
			  jQuery('#btnApproval').css('display','none');
			  jQuery('#txtSermDescription').attr("disabled",true);
				jQuery('#txtSermRemarks').attr("disabled",true);
				readOnlyFields('cmbSermPrbType');
				readOnlyFields('cmbSermMenu');
				jQuery('#txtSermFormname').attr("disabled",true);
				jQuery('#dlgAddImgFile').attr("disabled",true);
				jQuery('#btnImgFileClear').attr("disabled",true);
				readOnlyFields('cmbSermSbmtMode');
				readOnlyFields('cmbSermReportedby');
				readOnlyFields('dteSermExpTargetDate');
			  jQuery("#chkSermCompOk").attr("disabled","disabled");
			  jQuery("#chkSermCompOk","#chkSermCompNotOk").hide();
			  jQuery("#chkSermCompNotOk").hide();
			  jQuery("#lblComp").hide();
			  jQuery("#lblCompOk").hide();
			  jQuery("#lblCompNotOk").hide();
			  readOnlyFields('cmbSermKeyid');
		  }
		  else
		  {
			  
		  }
	  		
		  if( mode == "COMPLETION" )
				  {
					  //alert("mode:  "+mode);
					  jQuery('#btnAllocate').css('display','none'); 
					  jQuery('#btnApproval').css('display','none');
					  jQuery('#txtSermRemarks').attr("disabled",false);
					  jQuery("#chkSermCompOk").attr("checked",true);
					  jQuery('#dlgAddImgFile').attr("disabled",true);
					jQuery('#btnImgFileClear').attr("disabled",true);
					  }
				  else
			 		 {
					  jQuery("#chkSermCompOk").hide();
					  jQuery("#chkSermCompNotOk").hide();
					  jQuery("#lblComp").hide();
					  jQuery("#lblCompOk").hide();
					  jQuery("#lblCompNotOk").hide();
					  var hdndisableAllocation =jQuery('#hdndisableAllocation').val(); 
					  if(hdndisableAllocation != null && hdndisableAllocation != '' && hdndisableAllocation != ' ')
					  {
						 jQuery('#btnAllocate').css('display','none');				
					  }
					  var hdndisableApproval =jQuery('#hdndisableApproval').val(); 
					  if(hdndisableApproval != null && hdndisableApproval != '' && hdndisableApproval != ' ')
					  {
						 jQuery('#btnApproval').css('display','none');
					  }
					
				 	  readOnlyFields('cmbSermKeyid');
			 		}
			  if( mode == "VIEW" )
			  {
				  jQuery('#btnAllocate').css('display','none'); 
				  jQuery('#btnApproval').css('display','none');
				  jQuery("#chkSermCompOk").attr("disabled","disabled");
				  jQuery("#chkSermCompOk").hide();
				  jQuery("#chkSermCompNotOk").hide();
				  jQuery("#lblComp").hide();
				  jQuery("#lblCompOk").hide();
				  jQuery("#lblCompNotOk").hide();
			  }
			  else
			  {
				  var hdndisableAllocation =jQuery('#hdndisableAllocation').val(); 
				  if(hdndisableAllocation != null && hdndisableAllocation != '' && hdndisableAllocation != ' ')
				  {
					 jQuery('#btnAllocate').css('display','none');				
				  }
				  var hdndisableApproval =jQuery('#hdndisableApproval').val(); 
				  if(hdndisableApproval != null && hdndisableApproval != '' && hdndisableApproval != ' ')
				  {
					 jQuery('#btnApproval').css('display','none');
				  }
				
			 	  readOnlyFields('cmbSermKeyid');
		 		}
			  
				
		  
	  
		 //var module = jQuery('#hdnModule').val();
		 /*if(module != null && module != '' && module != ' ')
		 {
			 //jQuery('#cboSermModule').val(module);
		 }*/
		 
		 var hdnfilemanager = jQuery('#hdnfilemanager').val();
		 if(hdnfilemanager=="N"){
			 
			// disableUIButton('btnFileManager');
		 }

		 fileManagerPopUp(getFieldValue('cmbSermKeyid','frmServiceBooking'),"SER","frmServiceBooking","btnFileManager","serviceReqFileMgr");
});	  
jQuery("#imgFile").load(function() {
	/*	if((jQuery(this).width()>415)||(jQuery(this).height()>264))
		{	jQuery('#imgOplmAfterimage').attr('src', "");
			alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
			return false;
		}*/
    });
    
jQuery("#btnFileManager").click(function(){
	

	

	/*var documentNo =getFieldValue('cmbSermKeyid','frmServiceBooking');	
	
	if(documentNo != null && documentNo != '' && documentNo != ' ')
		fileManagerPopUp(documentNo,"SER","","");
	else
		saveForm('frmServiceBooking','serviceBooking_save.serv?q=2&openFileManager=Y');
	/*if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"SER");
	}
	else{
		alert("Service should be selected to view FileManager");
	}*/	
});
function  btnFileManager_click(){
	var status = jQuery('#hdnStatus').val();
	var documentNo =getFieldValue('cmbSermKeyid','frmServiceBooking');	
	//alert("satatu"+status);
	if(documentNo != null && documentNo != '' && documentNo != ' '){
		fileManagerPopUp(documentNo,"SER","","","");
		
	}
	else{
		saveForm('frmServiceBooking','serviceBooking_save.serv?q=2&openFileManager=Y');
	}
	var mode= jQuery('#mode').val();
	//alert("satatu"+mode);
	apMode = "create";
	if(mode=="view" || mode=="approval" || mode=="completion" || mode=="complete")
	   apMode = "view";
	//fileManagerPopUp(documentNo,"SER","","","",apMode);

	if(status == 'A' || status == 'C' || status=='R' || status == 'S' || mode=="view")
	{
		var mode= jQuery('#mode').val();
		//apMode = "create";
		apMode="view";
		fileManagerPopUp(documentNo,"SER","","","",apMode);
	}

	/*if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"SER","","","");
	}
	else{
		alert("Service should be selected to view FileManager");
	}*/
} 
function frmServiceBooking_successsCallback(result)
{
	var mode= jQuery('#mode').val();
	var status = jQuery('#txtSermStatus').val();
	if(status=='B'){
		jQuery('#txtSermStatus').val('Booked');
		
	}else if(status=="A"){
		jQuery('#txtSermStatus').val("Allocated"); 	
	}else if(status=="R"){
		jQuery('#txtSermStatus').val("Cancelled"); 	
	}else if(status=="C"){
		jQuery('#txtSermStatus').val("Completed"); 	
		jQuery('#txtSermRemarks').attr("disabled",false);
	}else if(status=="S"){
		jQuery('#txtSermStatus').val("Approved"); 	
	}else if(status =="O"){
		jQuery('#txtSermStatus').val("Reopen");
	}
	var openFileManager = result.openFileManager;
	var attachFile = result.attachFile;
	var documentNo = result.successData.keyId;
	jQuery('#txtSermKeyid').val(documentNo);	
	reloadCombo("frmServiceBooking","cmbSermKeyid","serviceIdCombo.serv");
	if(openFileManager != null && openFileManager !='' && openFileManager != ' ')
	{
		if(documentNo != null && documentNo != ''){
			jQuery('#hdnSermKeyid').val(documentNo);			
			fileManagerPopUp(documentNo,"SER");
		}
		else{
			alert("Service should be selected to view FileManager");
		}
	}
	else
	{
		if(documentNo != null && documentNo != ''){			
			jQuery('#cmbSermKeyid').combobox('setValue',documentNo);
		}
	/*	if(attachFile != null && attachFile !='' && attachFile != ' ')
		{
			if(confirm("Do You Want to attach File?") == true)
			{
				if(documentNo != null && documentNo != ''){
					jQuery('#txtSermKeyid').val(documentNo);			
					fileManagerPopUp(documentNo,"SER","","","");
				}
				else{
					alert("Service should be selected to view FileManager");
				}
			}
			else
			{*/
				navigateToPrevForm();
				//processAjaxCalls("send_servicemail.serv","&serviceId="+documentNo+"&mode=Booking" , 'sendMail_successCallBack','sendMail_errorCallBack',"","serviceBooking",true);
				
			
			//}
		//else
		//	navigateToPrevForm();
	}

	 jQuery('#imgFile').attr('src', " ");
	 jQuery('#imgFile').attr('src', " ");
}
function fileManagerDivId_afterClose()
{
	//alert("closing");
	//refreshForm();
	var documentNo = jQuery('#txtSermKeyid').val();
	if(formStatus != null && formStatus != '' && formStatus != ' ')
		navigateToPrevForm();
	
/*	if(documentNo != null && documentNo != '' && documentNo !=  ' ')
		processAjaxCalls("send_servicemail.serv","&serviceId="+documentNo+"&mode=Booking" , 'sendMail_successCallBack','sendMail_errorCallBack',"","serviceBooking",true); */
}
function divApproval_afterClose()
{
	var formStatus = jQuery('#hdnAfterSavePopup').val();
	if(formStatus != null && formStatus != '' && formStatus != ' ')
		navigateToPrevForm();
}
function divAllocate_afterClose()
{
	var formStatus = jQuery('#hdnAfterSavePopup').val();
	if(formStatus != null && formStatus != '' && formStatus != ' ')
		navigateToPrevForm();
}
function sendMail_successCallBack(result)
{
	//navigateToPrevForm();
}
function frmServiceBookingcmbSermKeyid_onSelect(record){
	//alert(record.id);
	 processAjaxCalls("serviceRecall.serv?keyId="+record.id+"&mode=EDIT","cmbSermKeyid_onSelectSuccess","cmbSermKeyid_onSelectError","","serviceBooking",true);
 }
function cmbSermKeyid_onSelectSuccess(result){
 	
  	//jQuery("#txtSermStatus").combobox("setValue",result.newStpTlServicerequestmst.JhamMachineid);
	jQuery("#cmbSermReportedby").combobox("setValue",result.newStpTlServicerequestmst.SermReportedby);
	jQuery("#cmbSermBookedby").combobox("setValue",result.newStpTlServicerequestmst.SermBookedby);
	jQuery("#dteSermDate").datebox("setValue",result.newStpTlServicerequestmst.SermDate);
	jQuery("#txtSermDescription").val(result.newStpTlServicerequestmst.SermDescription);
	jQuery("#txtSermFormname").val(result.newStpTlServicerequestmst.SermFormname);
	//jQuery("#txtSermSubmodule").val(result.newStpTlServicerequestmst.SermSubmodule);
	jQuery("#txtSermRemarks").val(result.newStpTlServicerequestmst.SermRemarks);
	jQuery("#txtSermTime").val(result.newStpTlServicerequestmst.SermTime);
	jQuery("#cmbSermSbmtMode").val(result.newStpTlServicerequestmst.SermSbmtMode);
	jQuery("#cmbSermMenu").val(result.newStpTlServicerequestmst.SermMenu);
	jQuery("#cmbSermPrbType").val(result.newStpTlServicerequestmst.SermPrbType);

	//jQuery("#cboSermModule").val(result.jhAuditCreationdata.SermModule);
	var status= result.jhAuditCreationdata.SermStatus;
	if(status=="B"){
		jQuery('#txtSermStatus').val("Booking");
	}else if(status=="A"){
		jQuery('#txtSermStatus').val("Allocated"); 	
	}else if(status=="R"){
		jQuery('#txtSermStatus').val("Cancelled"); 	
	}else if(status=="C"){
		jQuery('#txtSermStatus').val("Completed"); 
		jQuery('#txtSermRemarks').attr("disabled",false);
	}else if(status=="S"){
		jQuery('#txtSermStatus').val("Approved"); 	
	}
	
	  
}
//function cmbSermKeyid_onSelectError(result){
	
//}
jQuery("#btnAllocate").click(function(){
	var serviceId =getFieldValue('cmbSermKeyid','frmServiceBooking');
	var mode= jQuery('#mode').val();
	var url = "serviceAllocate_input.serv?serviceId="+serviceId;
	var enableButton = true;
	if(mode == "view")
	{
		url += "&disableFields=Y";
		enableButton=false;
	}
	
	LoadPopUp("divAllocate",url, true,"74%","73%","0","13%", "","Service Allocation","",enableButton);
	
});
jQuery("#btnApproval").click(function(){

	var status =jQuery('#hdnStatus').val();
	var serviceId =getFieldValue('cmbSermKeyid','frmServiceBooking');
	var url = "serviceApproval_input.serv?serviceId="+serviceId;
	var enableButton = true;
	if(status != null && status != '' && status != ' ' && (status != 'B'))
	{
		url += "&disableFields=Y";
		enableButton=false;
	}
	
	
	LoadPopUp("divApproval",url, true,"50%","52%","0","13%", "Approval_Callback","Service Approval","",enableButton);
});	
jQuery( "#btnImgFileClear" ).click(function() {
	processAjaxCalls("#btnImgFileClear","&imgFile=present");
	jQuery('#imgFilename').val(" ");
	jQuery('#imgFile').attr('src', " ");
});
function frmServiceBooking_beforeSubmit()
{
	/*var status=jQuery('#txtSermStatus').val();
	if(status=="Booked" || status=="Booking"){
		jQuery('#txtSermStatus').val("B");
	}else if(status=="Allocated"){
		jQuery('#txtSermStatus').val("A"); 	
	}else if(status=="Closed"){
		jQuery('#txtSermStatus').val("D"); 	
	}else if(status=="Completed"){
		jQuery('#txtSermStatus').val("C"); 	
	}*/
	
	
	/*var imgFilename =jQuery("#hdnimgFilename").val();// jQuery("#imgKnowwhyImage").attr('src');
	alert(imagename);
	var imgFileindex=imageFilename.lastIndexOf("/");
	var imageFilenameA= imgFilename.substring(conindex+1,imgFilename.length);
	var imgFilefullname = imageFilenameA.toUpperCase();
	jQuery("#hdnimgFilename").val(imgFilefullname);
	var imgFilename = jQuery('#hdnimgFilename').val();-->*/
}
function frmServiceBooking_beforeDelete()
{
	
	var mode = jQuery("#mode").val();
	if(mode == "view" || mode == "approval" || mode=="complete" || mode=="completion"){
		popupCommonErrorMsg('Data cannot deleted in this mode.');
		return false;
	}
	var msrdelMsg = "Do You Want Delete Record?";
	 if(confirm(msrdelMsg) == false)
	 {
				return false;
	 }
	 else
		 return true;
}
function frmServiceBooking_deleteSuccessCallback(result)
{
	alert(result.successData.msg); 	
	clearForm("frmServiceBooking");
	processAjaxCalls("#btnImgFileClear","&imgFile=present");
	jQuery('#imgFilename').val(" ");
	jQuery('#imgFile').attr('src', " ");
	
}	
function dteSermExpTargetDate_onSelect(date)
{              	
	compareDates('dteSermDate','dteSermExpTargetDate','Expected Target Date should not be lesser than Booking Date');
}
function compareDates(fromDateId,toDateId,errMsg)
{
	var fromDate = null;
	if(fromDateId.substring(0,3) == 'dte')
		fromDate = jQuery('#'+fromDateId).datebox('getValue') + '00:00';
	else
		fromDate = jQuery('#'+fromDateId).val()+ '00:00';
    var toDate = jQuery('#'+toDateId).datebox('getValue') + '00:00';
	var currentDate = getServerDateTime();
	//alert("currentDate");
	if(convertStringToDate(toDate) < currentDate)
	{
		showValidationErrorMsg(toDateId,'Should Not less than Current Date/Time');
		jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));		
        //fillWithCurrentDate(toDateId);		
	}
	else if( compareDateTime(toDate,fromDate) > 0 )
    {
    	showValidationErrorMsg(toDateId,errMsg);
    	jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));		
    }
    else
    	clearValidationErrorMsg(toDateId);	
}
jQuery("#chkSermCompOk").click(function(){
	var chkVal = getFieldValue('chkSermCompOk');
	if(chkVal){
		jQuery("#chkSermCompOk").attr("checked",true);
		jQuery("#chkSermCompNotOk").attr("checked",false);
		jQuery("#lblRemarks").removeClass('mandatory-lbl');
	}
	else{
		jQuery("#chkSermCompOk").attr("checked",false);
		jQuery("#chkSermCompNotOk").attr("checked",true);
		jQuery("#lblRemarks").addClass('mandatory-lbl');
	}
});

jQuery("#chkSermCompNotOk").click(function(){
	var chkVal = getFieldValue('chkSermCompNotOk');	
	if(chkVal){
		jQuery("#chkSermCompOk").attr("checked",false);
		jQuery("#chkSermCompNotOk").attr("checked",true);
		jQuery("#lblRemarks").addClass('mandatory-lbl');
	}
	else{
		jQuery("#chkSermCompOk").attr("checked",true);
		jQuery("#chkSermCompNotOk").attr("checked",false);
		jQuery("#lblRemarks").removeClass('mandatory-lbl');
	}
});
jQuery("#chkSermCompOk").click(function(){
	completeness();	
	});
	jQuery("#chkSermCompNotOk").click(function(){
	completeness();
	});
		var imgFilename= getFieldValue("imgFilename");
		jQuery('#imgFilename').attr('src', imgFilename);
		
	/*function completeness()
	{
		var chkVal1 = getFieldValue('chkSermCompOk');
		var chkVal2 = getFieldValue('chkSermCompNotOk');
		if(jQuery("#chkSermCompOk").is(":checked")){
			enableFields("txtSermRemarks");
			jQuery("#lblRemarks").removeClass('mandatory-lbl');
		}
		else{
			jQuery("#lblRemarks").addClass('mandatory-lbl');
		}
		if(jQuery("#chkSermCompNotOk").is(":checked")){
			var rep = jQuery('#chkSermCompNotOk:checked').val();
			jQuery("#lblRemarks").addClass('mandatory-lbl');
				
			
		}
		else{
			 jQuery("#lblRemarks").removeClass('mandatory-lbl');
		}
	}*/

 </script>
 <form name="frmServiceBooking" id="frmServiceBooking" >
	 <div id="wrapper">
	 <div align="center">
		 <table id="" border="0" width="400px;" style="padding-left:30px;position:relative;" class="">
		 	<tr>
		 		<td colspan='2' width="200px" style="position:relative;width:440px;">
		 			<div class="easyui-paddingbfpx">
		 				<label  style="padding-left:0px;">Service Ticket no.</label>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<input id="cmbSermKeyid" name="cmbSermKeyid" type="text" class="easyui-combobox" style="width: 320px;" value="${requestScope.newStpTlServicerequestmst.sermKeyid}" <c:out value = "${requestScope.serviceBookingBean.disableSermKeyid == true ? ' disabled':''}"/>/>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<label class="mandatory-lbl">Date and time</label>
		 				<label style="padding-left:80px;">Status</label>
				    </div>
				    <div class="easyui-paddingbfpx">
		 				<input class="easyui-text" style=" width : 95px;" id="dteSermDate" name="dteSermDate" value="${requestScope.newStpTlServicerequestmst.sermDate}" <c:out value = "${requestScope.serviceBookingBean.disableSermDate == true ? ' disabled':''}"/>/>
		 				<span class="spinner"><input  id="spnSermTime" name="spnSermTime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.newStpTlServicerequestmst.sermTime}"  style="width: 60px;" <c:out value = "${requestScope.serviceBookingBean.disableForm == true ? ' disabled':''}"/>></span>
		 				<input id="txtSermStatus" name="txtSermStatus" type="text" class="easyui-text"  style="width:150px;background-color:transparent;padding-left:20px;text-align:center;font-weight:bold;" value="${requestScope.newStpTlServicerequestmst.sermStatus}" <c:out value = "${requestScope.serviceBookingBean.disableSermStatus == true ? ' disabled':''}"/>/>
		 			</div>
		 		
		 			<div class="easyui-paddingbfpx">
		 				<label class="mandatory-lbl">Reported By</label>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<input id="cmbSermReportedby" name="cmbSermReportedby" type="text" class="easyui-combobox" style="width: 320px;" value="${requestScope.newStpTlServicerequestmst.sermReportedby}" <c:out value = "${requestScope.serviceBookingBean.disableSermReportedby == true ? ' disabled':''}"/>/>
		 			</div>
	 		
		 		
		 			<div class="easyui-paddingbfpx">
		 				<label class="mandatory-lbl">Booked By</label>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<input id="cmbSermBookedby" name="cmbSermBookedby" type="text" class="easyui-combobox" style="width: 320px;"  value="${requestScope.newStpTlServicerequestmst.sermBookedby}" <c:out value = "${requestScope.serviceBookingBean.disableSermBookedby == true ? ' disabled':''}"/>/>
		 			</div>
					<div class="easyui-paddingbfpx">
					<label class="mandatory-lbl">Type of problem</label>
					</div>
					<div class="easyui-paddingbfpx">
		 				<input id="cmbSermPrbType" name="cmbSermPrbType" type="text" class="easyui-combobox" style="width: 320px;"  value="${requestScope.newStpTlServicerequestmst.sermPrbType}" <c:out value = "${requestScope.serviceBookingBean.disableSermPrbType == true ? ' disabled':''}"/>/>
		 			</div>

		 			<div class="easyui-paddingbfpx">
		 				<label class="mandatory-lbl" >Menu</label>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<input id="cmbSermMenu" name="cmbSermMenu" type="text" class="easyui-combobox" style="width: 320px;"  value="${requestScope.newStpTlServicerequestmst.sermMenu}" <c:out value = "${requestScope.serviceBookingBean.disableSermMenu == true ? ' disabled':''}"/>/>
		 			</div>
<!--		 			<div>-->
<!--		 				<span id="err_cboSermModule" class="tpm-errormsg"></span>-->
<!--		 			</div>-->
<!---->
		 			<div class="easyui-paddingbfpx">
		 				<label>Screen Name</label>
		 				
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<input id="txtSermFormname" name="txtSermFormname" type="text" class="easyui-text" style="width: 180px; " value="${requestScope.newStpTlServicerequestmst.sermFormname}" <c:out value = "${requestScope.serviceBookingBean.disableSermFormname == true ? ' disabled':''}"/>/>
		 				
		 			</div>
<!--		 		<div class="easyui-paddingbfpx">-->
<!--		 		</div>-->
		 				 		
		 		</td>
		 		<td  width="200px"style="float:left;padding-left:40px;width:380px;">
		 			<div class="easyui-paddingbfpx">
		 				<label class="mandatory-lbl">Description</label>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<textarea class="txtarea" rows="1" maxlength="500" tabindex="18"style="width:375px;resize:none; height:105px;" cols="" id="txtSermDescription" name="txtSermDescription" <c:out value = "${requestScope.serviceBookingBean.disableSermDescription == true ? ' disabled':''}"/>>${requestScope.newStpTlServicerequestmst.sermDescription}</textarea>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<label id=lblRemarks>Remarks</label>
		 			</div>
		 			<div class="easyui-paddingbfpx">
		 				<textarea class="txtarea" rows="1" maxlength="500" tabindex="18"style="width:375px;resize:none; height:90px;" cols="" id="txtSermRemarks" name="txtSermRemarks" <c:out value = "${requestScope.serviceBookingBean.disableSermRemarks == true ? ' disabled':''}"/>>${requestScope.newStpTlServicerequestmst.sermRemarks}</textarea>
		 			</div>
		 			
		 			<div class="easyui-paddingbfpx">
					<label class="mandatory-lbl">Submit mode</label>
					
					</div>
					<div class="easyui-paddingbfpx">
					<select id="cmbSermSbmtMode" name="cmbSermSbmtMode"  class="easyui-combobox" style="width: 300px;" disabled>
					<option value="A" selected>Need Approval</option>
					<option value="D">Direct submission</option>
					</select>
		 				<!--<input id="cmbSermSbmtMode" name="cmbSermSbmtMode" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.newStpTlServicerequestmst.sermSbmtMode}" <c:out value = "${requestScope.serviceBookingBean.disableSermSbmtMode == true ? ' disabled':''}"/>/>
		 			
		 			--></div>
		 			<div class="easyui-paddingbfpx">
		 				<label  style="padding-left:0px;">Expected Target date</label>
		 				<label style="padding-left:50px;" id=lblComp>Completion status</label>
		 			</div>
		 			<div class="easyui-paddingbfpx">
						<span style="padding-left:0px;"><input class="easyui-text" style=" width : 126px;" id="dteSermExpTargetDate" name="dteSermExpTargetDate" value="${requestScope.newStpTlServicerequestmst.sermExpTargetDate}" <c:out value = "${requestScope.serviceBookingBean.disableSermExpTargetDate == true ? ' disabled':''}"/>/></span>
			 			<span style="padding-left:40px;"><input type="checkbox" id="chkSermCompOk" name="chkSermCompOk" value="Y" />
			 			<label  style="padding-left:0px;" id=lblCompOk>Ok</label>
						<span style="padding-left:10px;"><input type="checkbox" id="chkSermCompNotOk" name="chkSermCompNotOk" value="N" /> 
						<label  style="padding-left:0px;" id=lblCompNotOk>Not ok</label>
		 			</div>
		 		</td>
		 		<td width="200px"style="padding-left:40px;width:380px;">
		 			
		 			<div id="divImgFile" class="easyui-paddingbfpx">
						<div style="width: 310px;height: 280px;margin-top:0px; padding-left:-0px;border: 1px solid #a4a4a4; ">
						<img alt=""  id="imgFile"  name="imgFile" src="${requestScope.newStpTlServicerequestmst.sermImgfile}"  >
						</div>
					</div>
					<div class="easyui-paddingbfpx" style="float:left;" >
					<span id="serviceReqFileMgr" style="position:absolute;padding-top:19px;padding-left:20px;">
<!--		 			<input id="btnFileManager" name="btnFileManager" type="button" class="easyui-button"  value="Attach File"  style="width:140px; height:25px;"/> -->
		 			</span>	
					</div>
					<div class="easyui-paddingbfpx" style="float:right;padding-top:19px;">
		 				<input id="btnApproval" name="btnApproval" type="button" class="easyui-button"  value="Approval/Cancel"  style="width:140px; height:25px;"/>
		 				<input id="btnAllocate" name="btnAllocate" type="button" class="easyui-button"  value="Allocate/Complete"  style="width:140px; height:25px;"/>
		 			</div>
				</td>	
				<td>
					<div>
		 			<input class="easyui-button" type="button" value ="+" id="dlgAddImgFile"style="height:20px;" onclick="" <c:out value = "${ requestScope.ServiceBookingBean.disableForm == true ? ' disabled':''}"/>/>
					</div>
				    <div style="margin-top:10px;">   
					<input class="easyui-button" type="button" value ="-" id="btnImgFileClear"style="height:20px;width:30px;" onclick="" <c:out value = "${ requestScope.ServiceBookingBean.disableForm == true ? ' disabled':''}"/>/>
					</div>
		 		</td>
		 	</tr>
		 	
		 </table>
		 </div>
	 </div>
	
	<!--  <input type="hidden" id="hdndisableSermSbmtMode" name="hdndisableSermSbmtMode" value="${requestScope.disableSermSbmtMode}"/>-->
	<input type="hidden" id="chkSermCompOk" name="chkSermCompOk" value=""/>
	<input type="hidden" id="chkSermCompNotOk" name="chkSermCompNotOk" value=""/>
	<input type="hidden" id="imgFilename" name="imgFilename" value=""/>
	<input type="hidden" id="hdndisableAllocation" name="hdndisableAllocation" value="${requestScope.disableAllocation}"/>
	<input type="hidden" id="hdndisableApproval" name="hdndisableApproval" value="${requestScope.disableApproval}"/> 
	<input type="hidden" id="hdnfilemanager" name="hdnfilemanager" value="${requestScope.filemanager}"/> 
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdnStatus" name="hdnStatus" value="${requestScope.newStpTlServicerequestmst.sermStatus}"/>
	<input type="hidden" id="hdnSermKeyid" name="hdnSermKeyid"/>
	<input type="hidden" id="txtSermKeyid" name="txtSermKeyid"/>
	<input type="hidden" id="spnSermTime" name="spnSermTime" value="${requestScope.disableSermTime}"/>
	<input type="hidden" id="hdnAfterSavePopup" name="hdnAfterSavePopup"/>
	  
 </form>