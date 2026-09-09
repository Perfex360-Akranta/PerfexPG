<script>
jQuery(document).ready(function(){
	

    
    var roleId = jQuery("#hdnUserRole").val();
	if(roleId != 'AROL0055') {
	    jQuery('#frmComplaintGallery').hide();
	    if(typeof popupCommonErrorMsg === 'function') {
	        popupCommonErrorMsg("Please select the QM PILLAR MEMBER");
	    } else {
	        alert("Please select the QM PILLAR MEMBER");
	    }
	    setTimeout(function() {
	        navigateToPrevForm();
	    }, 5000);
	    return false;
	}
    

    
    // If role check passes, continue with form initialization
	initialiseForm('frmComplaintGallery');
	jQuery('#submitForm').val('frmComplaintGallery');
	enableFields('cmbCmgaCustomerid');
	jQuery("#Lblcus").addClass("mandatory-lbl");
	fillComboBox("frmComplaintGallery","cmbCmgaCustomerid","customer.commonFilter");
	//fillComboBox("frmComplaintGallery","cmbCmgaGradespecification","productAll.commonFilter");
	var flid = jQuery("#frmComplaintGallery input[id='flid']").val();
   	fillComboBox("frmComplaintGallery","cmbCmgaGradespecification","comboGradeSpec.compg?flid="+flid);
	//fillComboBox("frmComplaintGallery","cmbCmgaDefectid","Phenomena_DefectForm.compg?");

	numericTextBox('txtCmgaDefectqty');
	//readOnlyFields('cmbCmgaCustomerid');	
	formatDateBox("dteCmgaManufacturedate",'dd-MMM-yyyy');
	formatDateBox("dteCmgaComplaintdate",'dd-MMM-yyyy');
	//fillWithCurrentDate('dteCmgaManufacturedate');
	jQuery('#frmComplaintGallery .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmComplaintGallery textarea').css('text-transform', 'uppercase');
	var factId = jQuery("#frmComplaintGallery input[id='factory']").val();
    var sectionId = jQuery("#frmComplaintGallery input[id='section']").val();
    var cellId = jQuery("#frmComplaintGallery input[id='cell']").val();
	var machId = jQuery("#frmComplaintGallery input[id='machine']").val();
    var flid = jQuery("#frmComplaintGallery input[id='flid']").val(); 
    var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+ flid;
	//loadFunctionalLocation("CmplaintfunLocation", "functionalLoc.condapp", "CmplaintfunLocation", "frmComplaintGallery",dataStr);
	loadFunctionalLocation("CmplaintfunLocation", "functionalLoc_lossCapture.pcs","CmplaintfunLocation", "frmComplaintGallery", dataStr);
	
	var keyid=jQuery("#txtCmgaKeyid").val();
	var imgName=jQuery("#imgCustomerName").val();
	var imgPath=jQuery("#imgCustomerPath").val();
	if(!(keyid.length>0)){
		fillWithCurrentDate("dteCmgaComplaintdate");
		fillWithCurrentDate('dteCmgaManufacturedate');
		//fillWithCurrentDate('dteCmgaManufacturedate');
	}

	var source = jQuery('#hdnSource').val();
	if (source=="E") {
		jQuery("input:radio[name=chkSource][value ='E']").prop('checked', true);
		fnClickExternal();
	}
	else
		jQuery("input:radio[name=chkSource][value ='I']").prop('checked', true);
	
	fileManagerPopUp("","CGL","frmComplaintGallery","btnfilemgr","CompGalFilemgr"); 
	
	if(imgName.length>0){
		jQuery('#imgCustomer').attr('src', imgPath);
	}else
		jQuery('#imgCustomer').attr('src', "images/EmpDefaultImg.jpg");
	imageUpload(jQuery( "#btnimgEmpAdd" ),'ImageUpload.commonFilter','btnimgEmpAdd',"imgCustomer","imgCustomerName");
	jQuery("#imgCustomer").load(function() {
		if((jQuery(this).width()>415)||(jQuery(this).height()>264))
		{	jQuery('#imgCustomer').attr('src', "");
			alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
			return false;
		}
    });
	jQuery( "#btnimgEmpDel" ).click(function() {
		jQuery('#imgCustomer').attr('src', "images/EmpDefaultImg.jpg");
		jQuery('#imgCustomerName').val("");
	});
	
	//jQuery("input:radio[name=chkSource][value ='I']").prop('checked', true);
	/*jQuery('#dteCmgaComplaintdate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			completedDateEvt();
			} 
	   });
	 

	jQuery('#dteCmgaManufacturedate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			completedDateEvent();
			} 
	   });

	*/
		
	
	jQuery("#rdExternal").click(function(){
			
		fnClickExternal();	
		
	});

	jQuery("#rdInternal").click(function(){
		
		var factId = jQuery("#frmComplaintGallery input[id='factory']").val();
	    var sectionId = jQuery("#frmComplaintGallery input[id='section']").val();
	    var cellId = jQuery("#frmComplaintGallery input[id='cell']").val();
		var machId = jQuery("#frmComplaintGallery input[id='machine']").val();
	    var flid = jQuery("#frmComplaintGallery input[id='flid']").val(); 
	    var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+ flid;
		//loadFunctionalLocation("CmplaintfunLocation", "functionalLoc.condapp", "CmplaintfunLocation", "frmComplaintGallery",dataStr);
		loadFunctionalLocation("CmplaintfunLocation", "functionalLoc_lossCapture.pcs","CmplaintfunLocation", "frmComplaintGallery", dataStr);
		reloadCombo("frmComplaintGallery","cmbCmgaCustomerid","customer.commonFilter");		
		setFieldValue("txtCmgaSource","I");
		clearField('cmbCmgaCustomerid');	
		//readOnlyFields('cmbCmgaCustomerid');
		//jQuery("#Lblcus").removeClass("mandatory-lbl");
		enableFields('cmbCmgaCustomerid');
	    jQuery("#Lblcus").addClass("mandatory-lbl");
		
	});	
	
});


/* function frmComplaintGallery_FuntLocHierarchy_SuccessCallBack(keyIds)
{ 
jQuery("#cmbCmgaGradespecification").val(keyIds.flId);
reloadCombo("frmComplaintGallery","cmbCmgaGradespecification","comboGradeSpec.compg?flid="+flId);
} */

function frmComplaintGallery_FuntLocHierarchy_SuccessCallBack(keyIds) {
    var roleId     = jQuery("#hdnUserRole").val();
    var gradeMode  = (roleId === "AROL0055") ? "QM" : "DEFAULT";
    var defectMode = (roleId === "AROL0055") ? "QM" : "DEFAULT";

    // reset checkboxes on location change
    jQuery("#chkEtcfOther").prop("checked", false);
    jQuery("#chkCmgaDefectOther").prop("checked", false);  // reset defect checkbox too

    //jQuery("#cmbCmgaGradespecification").val(keyIds.flId);
    var Gradespecification = jQuery("#cmbCmgaGradespecification").combobox('getValue');
    reloadCombo("frmComplaintGallery", "cmbCmgaGradespecification",
        "comboGradeSpec.compg?combokey="+Gradespecification+"&flid=" + keyIds.flId
        + "&gradeMode=" + gradeMode);

    var cmbCmgaDefectid = jQuery("#cmbCmgaDefectid").combobox('getValue');
    reloadCombo("frmComplaintGallery", "cmbCmgaDefectid",
        "Phenomena_DefectForm.compg?combokey="+cmbCmgaDefectid+"&flid=" + keyIds.flId
        + "&sectionId=" + keyIds.sectId
        + "&defectMode=" + defectMode
        + "&callingModule=COMPLAINT_GALLERY");
}

function fnClickExternal() {
	var factId = jQuery("#frmComplaintGallery input[id='factory']").val();
    var sectionId = jQuery("#frmComplaintGallery input[id='section']").val();
    var cellId = jQuery("#frmComplaintGallery input[id='cell']").val();
	var machId = jQuery("#frmComplaintGallery input[id='machine']").val();
    var flid = jQuery("#frmComplaintGallery input[id='flid']").val(); 
    var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+ flid;
	//loadFunctionalLocation("CmplaintfunLocation", "functionalLoc.condapp", "CmplaintfunLocation", "frmComplaintGallery",dataStr);
	loadFunctionalLocation("CmplaintfunLocation", "functionalLoc_lossCapture.pcs","CmplaintfunLocation", "frmComplaintGallery", dataStr);
	reloadCombo("frmComplaintGallery","cmbCmgaCustomerid","customer.commonFilter");
	setFieldValue("txtCmgaSource","E");
	//clearField('cmbCmgaCustomerid');
	enableFields('cmbCmgaCustomerid');
	jQuery("#Lblcus").addClass("mandatory-lbl");

}

function dteCmgaComplaintdate_onSelect(record) {
	completedDateEvt();
} 


function dteCmgaManufacturedate_onSelect(record) {
	completedDateEvent();
} 


function completedDateEvent(){
	
	var currentDate = getServerDateTime();
	var Manufacturedate=jQuery('#dteCmgaManufacturedate').datebox("getValue");
	var complaintDate = jQuery('#dteCmgaComplaintdate').datebox("getValue");
	
	if(convertStringToDate(Manufacturedate )> currentDate)
	{
		alert('Mfg Date Should Not Exceed Current Date');
		fillWithCurrentDate('dteCmgaManufacturedate');
	}
	else if(convertStringToDate(Manufacturedate )> convertStringToDate(complaintDate))
	{
		alert('Mfg Date Should Not Greater than Complaint Date ');
		jQuery("#dteCmgaManufacturedate").datebox('setValue',complaintDate);
	}
	
}

function completedDateEvt()
{
	var currentDate = getServerDateTime();
	var complaintDate = jQuery('#dteCmgaComplaintdate').datebox("getValue");
	var Manufacturedate=jQuery('#dteCmgaManufacturedate').datebox("getValue");
	
	if(convertStringToDate(Manufacturedate )> convertStringToDate(complaintDate))
	{
		alert('Complaint Date Should Not Lesser than Mfg Date');
		fillWithCurrentDate('dteCmgaComplaintdate');
	}

	if(convertStringToDate(complaintDate)> currentDate)
	{
		alert('Complaint Date Should Not Exceed Current Date');
		fillWithCurrentDate('dteCmgaComplaintdate');
	}
}

/* function frmComplaintGallery_beforeSubmit(){

	//jQuery("input:radio[name=chkSlamSource]").val();
	var source = getFieldValue("txtCmgaSource");
	//alert(source);
	if(source == "E")
	{
		var cus = getFieldValue("cmbCmgaCustomerid");
		//alert(cus);
		if(cus.trim().length == 0)
		{
				alert("Select Customer");
				setFocusOnField("cmbCmgaCustomerid");
				return false;
		}
	}
	
} */

function frmComplaintGallery_beforeSubmit(){

	//jQuery("input:radio[name=chkSlamSource]").val();
	var source = getFieldValue("txtCmgaSource");
	//alert(source);
	var cus = getFieldValue("cmbCmgaCustomerid");
		//alert(cus);
		if(cus.trim().length == 0)
		{
				alert("Select Customer");
				setFocusOnField("cmbCmgaCustomerid");
				return false;
		}
	
	}
function frmComplaintGallery_successsCallback(result){
	clearForm("frmComplaintGallery");
	navigateToPrevForm();
	jQuery("#txtCmgaKeyid").val(result.keyid);
	var keyid=jQuery("#txtCmgaKeyid").val();

	  if(result.successData.mode=="Modify")
		{
		  closePopUpDialoge("ComplaintGalleryView");
		navigateToPrevForm("ComplaintGallery_input.compg");
		return;
		}	
	  
	if (result.openFileMgr=="true") { 
		if(keyid != null && keyid != ''){
			closePopUpDialoge("ComplaintGalleryView");
			fileManagerPopUp(result.keyid,"CGL","","","");
			clearForm("frmComplaintGallery");
			jQuery('#imgCustomer').attr('src', "images/EmpDefaultImg.jpg");
			jQuery('#imgCustomerName').val("");
			fillWithCurrentDate("dteCmgaComplaintdate");
		    fillWithCurrentDate("dteCmgaManufacturedate");
	    }	
		return ;
	}
	clearForm("frmComplaintGallery");
	if(!(keyid.length>0)){
		jQuery('#imgCustomer').attr('src', "images/EmpDefaultImg.jpg");
		jQuery('#imgCustomerName').val("");
		//fillWithCurrentDate("dteCmgaComplaintdate");
	    var flid = jQuery("#flid").val(); 
		loadFunctionalLocation("CmplaintfunLocation", "functionalLoc.condapp", "CmplaintfunLocation", "frmComplaintGallery","&flid="+flid);
	}else
	{
	    jQuery('#imgCustomer').attr('src', "images/EmpDefaultImg.jpg");
	    jQuery('#imgCustomerName').val("");
	    fillWithCurrentDate("dteCmgaComplaintdate");
	    fillWithCurrentDate("dteCmgaManufacturedate");
	}
	  closePopUpDialoge("ComplaintGalleryView");	
}

function frmComplaintGallery_deleteSuccessCallback(result){
	alert(result.successData.msg);
	clearForm("frmComplaintGallery");
	jQuery('#imgCustomer').attr('src', "images/EmpDefaultImg.jpg");
	jQuery('#imgCustomerName').val("");
	closePopUpDialoge("ComplaintGalleryView");
}
function btnfilemgr_click()
{
	var url ="ComplaintGalleryView_save.compg?&openFileMgr=true"; 
	var formId = jQuery('#submitForm').val(); 
	saveForm(formId,url);
	
	var KeyId=jQuery("#txtCarpKeyid").val();
    if(KeyId.trim().length<=0){
  		saveForm('frmCtrlRes','controlandresponse_save.conres?filemanger=filemanger');
  	}else if(KeyId != null && KeyId != ''){
			fileManagerPopUp(KeyId,"CRP","","","");
	    }
	
}

//added hari 12may
function toggleGradeSpec(checkbox) {
    var flid = jQuery("#frmComplaintGallery input[id='flid']").val();
    var gradeMode = jQuery(checkbox).is(':checked') ? "OTHERS" : "QM";
    
    clearField('cmbCmgaGradespecification');
    reloadCombo("frmComplaintGallery", "cmbCmgaGradespecification", 
        "comboGradeSpec.compg?flid=" + flid + "&gradeMode=" + gradeMode);
}
function toggleDefectPhenomena(checkbox) {
    var flid      = jQuery("#frmComplaintGallery input[id='flid']").val();
    var sectionId = jQuery("#frmComplaintGallery input[id='section']").val(); 
    var defectMode = jQuery(checkbox).is(':checked') ? "OTHERS" : "QM";
    
    console.log("toggle defectMode: " + defectMode);
    console.log("toggle sectionId: " + sectionId); 
    
    clearField('cmbCmgaDefectid');
    reloadCombo("frmComplaintGallery", "cmbCmgaDefectid",
        "Phenomena_DefectForm.compg?flid=" + flid
        + "&sectionId=" + sectionId
        + "&defectMode=" + defectMode
        + "&callingModule=COMPLAINT_GALLERY");  // THIS WAS MISSING
}


</script>
<form id="frmComplaintGallery" name="frmComplaintGallery" >
	<div>
		<div class="main-cntborder" style="width:850px;height:610px;margin-top:10px;margin-left:6%;">
						
		<table style="margin-top:0.5%;margin-left:0.5%;">
			<tr>
				<td>
					<div id="frmCmplaintFuntKeyIds">
						<input type="hidden" id="section" name=cmbSectionid value=""></input> 
						<input type="hidden" id="cell"    name="cmbCellid" value=""></input> 
						<input type="hidden" id="machine" name="cmbMachineid" value=""></input>
						<input type="hidden" id="flid"    name="cmbCmgaFlid" value="${requestScope.qtmTlComplaintgallery.cmgaFlid}"></input>
						<input type="hidden" id="elementId"    name="cmbelementid" value="${requestScope.qtmTlComplaintgallery.elementid}"></input>
						  
					</div>
					<div  class="easyui-paddingbfpx" id="CmplaintfunLocation"  ></div>
				</td>
			</tr>
			<tr>
			<td>
				<div class="easyui-paddingbfpx">
					<label class="mandatory-lbl">Source</label>					
				</div>
				<div class="easyui-paddingbfpx">
					<span style="border: solid; border-width: 1px"><input type="radio" 
					name="chkSource" id="rdInternal"  value="I" />
					<label>Internal</label>
					<input type="radio" name="chkSource"  id="rdExternal" value="E"/>
					<label>External</label>
					</span> 
					<span  id="CompGalFilemgr" style="position:absolute;margin-left:12px;margin-top:-6px;" >
									</span>
				</div>
				</td>
			
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td colspan="2" width="50%">
								<div>
									<div   class="easyui-paddingbfpx">
										<label class=""  id='Lblcus' >Customer Name</label>
									</div>		
									<div class="easyui-paddingbfpx" >
										<input type="text" class="easyui-combobox" id="cmbCmgaCustomerid" name="cmbCmgaCustomerid" style="width: 255px; /* height:21px; */ " value="${requestScope.qtmTlComplaintgallery.cmgaCustomerid }"/>
									</div>
								</div>
							</td>
							<td colspan="4" rowspan="4">
								<div style="position:relative; padding-left: 10px ">
									
									<div>
										<img id="imgCustomer" name="imgCustomer" src="images/EmpDefaultImg.jpg" width="225px" height="225px"/>
									</div>
									<div>
										<span><input type="button" class="easyui-button" id="btnimgEmpAdd" name="imgEmpAdd" value="+" style="width:25px;"/></span>
										<span style="padding-left: 170px " ><input type="button" class="easyui-button" id="btnimgEmpDel" name="imgEmpDel" value="-" style="width:25px;"/></span>
									</div>
								</div>
								
								
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div>
									<!-- <div  class="mandatory-lbl" class="easyui-paddingbfpx">
										<label>Grade Specification</label>
									</div> -->	
									<div class="easyui-paddingbfpx">
    <label class="mandatory-lbl">Grade Specification</label>
    <span style="margin-left:10px;">
        <input type="checkbox" id="chkEtcfOther" name="chkEtcfOther" value="N" onchange="toggleGradeSpec(this)"/>
        <label>Others</label>
    </span>
</div>	
									<div class="easyui-paddingbfpx" >
										<input type="text" class="easyui-combobox" id="cmbCmgaGradespecification" name="cmbCmgaGradespecification" style="width: 255px; /* height:21px; */ " value="${requestScope.qtmTlComplaintgallery.cmgaGradespecification}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div>
									<!-- <div  class="mandatory-lbl" class="easyui-paddingbfpx">
										<label>Defect Phenomena </label>
																			</div>	 -->	
																			
								<div class="easyui-paddingbfpx">
    <label class="mandatory-lbl">Defect Phenomena</label>
    <span style="margin-left:10px;">
        <input type="checkbox" 
               id="chkCmgaDefectOther" 
               name="chkCmgaDefectOther" 
               value="N" 
               onchange="toggleDefectPhenomena(this)"/>
        <label>Others</label>
    </span>
</div>		
									<div class="easyui-paddingbfpx" >
										<input type="text" class="easyui-combobox" id="cmbCmgaDefectid"
										 name="cmbCmgaDefectid" style="width: 255px;/*  height:21px; */ " 
										 value="${requestScope.qtmTlComplaintgallery.cmgaDefectid}"/>
										
									</div>
								</div>
							</td>
							
							<td >
								<div style="padding-left: 10px ">
								<div  class="mandatory-lbl" class="easyui-paddingbfpx">
										
											<label>Defect Qty</label>
										
									</div> 
									
									<div class="easyui-paddingbfpx" >									
											<input class="easyui-text" id="txtCmgaDefectqty" name="txtCmgaDefectqty" 
												maxlength="3"    
												value="${requestScope.qtmTlComplaintgallery.cmgaDefectqty}"  />										
									</div>
								</div>
							</td>
						</tr> 
						<tr>
							<td>
								
								<div>
									<div class="easyui-paddingbfpx" >
										<label>Date of Mfg</label>
									</div>	
									<div class="easyui-paddingbfpx">
										<input class="easyui-datebox" id="dteCmgaManufacturedate" name="dteCmgaManufacturedate" value="${requestScope.qtmTlComplaintgallery.cmgaManufacturedate }" />	
					  				</div>
								</div>
							</td>
							<td>
								<div style="padding-left: 10px ">
									<div  class="mandatory-lbl" class="easyui-paddingbfpx" >
										<label>Complaint Date</label>
									</div>	
									<div class="easyui-paddingbfpx" >
										<input class="easyui-datebox" id="dteCmgaComplaintdate" name="dteCmgaComplaintdate" style="width:100px;"  value="${requestScope.qtmTlComplaintgallery.cmgaComplaintdate }"/>
					  				</div>
								</div>
								
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div>
									<div class="easyui-paddingbfpx" >
										<label>Complaint Description</label>
									</div>		
									<div class="easyui-paddingbfpx" >
										<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  rows="2" cols="80" maxlength="495" id="txtcmgaComplaintdescription" name="txtcmgaComplaintdescription" style="width: 500px;height:60px; ">${requestScope.qtmTlComplaintgallery.cmgaComplaintdescription }</textarea>	
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div class="sub-header" style="width:620px;"> Analysis </div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div>	
									<div class="easyui-paddingbfpx">
										<label>Corrective Action</label>
									</div>	
									<div class="easyui-paddingbfpx" >		
										<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  rows="2" cols="80" maxlength="495" id="txtcmgaCorrectiveaction" name="txtcmgaCorrectiveaction" style="width: 255px;height:70px; ">${requestScope.qtmTlComplaintgallery.cmgaCorrectiveaction }</textarea>
									</div>
								</div>
							</td>
							<td>
								<div>
									<div class="easyui-paddingbfpx">
										<label>Preventive  Action</label>
									</div>		
									<div class="easyui-paddingbfpx" >					
										<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  rows="2" cols="80" maxlength="495" id="txtcmgaPreventiveaction" name="txtcmgaPreventiveaction" style="width: 255px;height:70px; ">${requestScope.qtmTlComplaintgallery.cmgaPreventiveaction}</textarea>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
			
		</div>
		<div>
		    <input type="hidden" id="txtCmgaSource" name="txtCmgaSource" value="${requestScope.cmgaSource}"/>
			<input type="hidden" id="mode" name="mode" />
			<input type="hidden" id="txtCmgaKeyid" name="txtCmgaKeyid" value="${requestScope.qtmTlComplaintgallery.cmgaKeyid}" />
			<input type="hidden" id="imgCustomerName" name="imgCustomerName" value="${requestScope.imgName}" />
			<input type="hidden" id="imgCustomerPath" name="imgCustomerPath" value="${requestScope.imgPath}" />
			<input type="hidden" id="hdnSource" name="hdnSource" value="${requestScope.qtmTlComplaintgallery.cmgaSource}" />	
			<input type="hidden" id='hdnroleName' value="${requestScope.rolename}"/>
<input type="hidden" id='hdnroleId' value="${requestScope.rolekeyid}"/>		
		</div>
		
	</div>
</form>


