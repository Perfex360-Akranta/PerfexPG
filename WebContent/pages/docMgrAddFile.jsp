<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">	

jQuery.noConflict();

jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmAddFile'); 
	initialiseForm("frmAddFile");
	fillComboBox("frmAddFile","cmbDmdmSubjectarea","subjectArea_combo.dcm");
	fillComboBox("frmAddFile","cmbDmdmType","type_combo.dcm");
	fillComboBox("frmAddFile","cmbDmdmCategory","category_combo.dcm");
	fillComboBox("frmAddFile","cmbDmdmOwner","employee.commonFilter");
	fillComboBox("frmAddFile","cmbDmdmApprovedby","employee.commonFilter");
	readOnlyFields('cmbDmdmApprovedby');  
	jQuery('#frmAddFile .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmAddFile textarea').css('text-transform', 'uppercase');	   
	var uploadedFile = jQuery('#hdnUploadedFile').val();
	if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
	{
		 disableUIButton('btnGetTypeKeywords');
		 jQuery('#lblChanges').removeClass('mandatory-lbl');
	}
	else
	{
		 enableUIButton('btnGetTypeKeywords');
		 jQuery('#lblChanges').addClass('mandatory-lbl');
	}

	var uploader = new qq.FileUploader({
	    // pass the dom node (ex. $(selector)[0] for jQuery users)
	    element:   document.getElementById("fileUploadDocMgr"),
	    // path to server-side upload script
	    action: 'file_upload.dcm',
		// additional data to send, name-value pairs
		params: {},
		numFiles:1,
		// validation
		// ex. ['jpg', 'jpeg', 'png', 'gif'] or []
		allowedExtensions: [],
		// each file size limit in bytes
		// this option isn't supported in all browsers
		sizeLimit: 5242880, // max size
		minSizeLimit: 1, // min size
		// set to true to output server response to console
		debug: false,
		// events
		// you can return false to abort submit
		onSubmit: function(id, fileName){
			if( jQuery(".qq-upload-list").length>0)
				jQuery(".qq-upload-list").html('');
		},
		onProgress: function(id, fileName, loaded, total){},
		onComplete: function(id, fileName, responseJSON){},
		onCancel: function(id, fileName){},
		messages: {
			 sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
	         minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
	         emptyError: "{file} is empty, please select some other file."
		      
		    //error messages, see qq.FileUploaderBasic for content
		},
		showMessage: function(messages){
			alert(messages);
			}
	});
});

jQuery("#btnInsertFile").click(function(){
	   var uploadname =jQuery(".qq-upload-list").html(); 
	   var viewsMode = jQuery('#hdnViewsMode').val();
	   var selFolderVal = jQuery('#hdnSelectedFolder').val();	   
	   var userFolderRights = jQuery('#hdnUserFolderRights').val();
	   
	  // if(selFolderVal != null && selFolderVal != '' && selFolderVal != ' ')
	   		var selFolderValArr = selFolderVal.split(',');
	  
	  // if(userFolderRights != null && userFolderRights != '' && userFolderRights != ' ')
	   		var userFolderRightsArr = userFolderRights.split(',');
	  
	   jQuery("#preLodDiv").css('display','block');
	   jQuery("#preLodDiv").addClass("tpm-loading");
	   show_winMask(1);
	   var dataStr = 'file_save.dcm?q=2';
	   if(uploadname != null && uploadname != '' && uploadname != ' ')
		   dataStr += '&filename='+uploadname;
	   if(selFolderValArr[0] != null && selFolderValArr[0] != '' && selFolderValArr[0] != ' ')
		   dataStr += '&id='+selFolderValArr[0];
	   if(selFolderValArr[1] != null && selFolderValArr[1] != '' && selFolderValArr[1] != ' ')
		   dataStr += '&levelNo='+selFolderValArr[1];
	   if(selFolderValArr[2] != null && selFolderValArr[2] != '' && selFolderValArr[2] != ' ')
		   dataStr += '&folderName='+selFolderValArr[2];
	   if(selFolderValArr[3] != null && selFolderValArr[3] != '' && selFolderValArr[3] != ' ')
		   dataStr += '&elemId='+selFolderValArr[3];
	   if(selFolderValArr[4] != null && selFolderValArr[4] != '' && selFolderValArr[4] != ' ')
		   dataStr += '&parentId='+selFolderValArr[4];
	  
		if(userFolderRightsArr[0] != null && userFolderRightsArr[0] != '' && userFolderRightsArr[0] != ' ')
			dataStr += '&allowModify='+userFolderRightsArr[0];
		if(userFolderRightsArr[1] != null && userFolderRightsArr[1] != '' && userFolderRightsArr[1] != ' ')
			dataStr += '&allowDel='+userFolderRightsArr[1];
		if(userFolderRightsArr[2] != null && userFolderRightsArr[2] != '' && userFolderRightsArr[2] != ' ')
			dataStr += '&allowDownload='+userFolderRightsArr[2];
		if(userFolderRightsArr[3] != null && userFolderRightsArr[3] != '' && userFolderRightsArr[3] != ' ')
			dataStr += '&allowRights='+userFolderRightsArr[3];			
	   if(viewsMode != null && viewsMode != '' && viewsMode != ' ')
		   dataStr += '&viewsMode='+viewsMode;

	 
	
	 saveForm('frmAddFile',dataStr);
	 closeRtClkMenu('#dlgModifyFile');
		//processAjaxCalls('file_save.dcm','?q=2','InsertFile','InsertFileErr');
});
jQuery("#btnCancelModFile").click(function(){
	closeRtClkMenu('#dlgModifyFile');
	closePopUpDialoge('divAddFile');
});
jQuery("#btnGetTypeKeywords").click(function(){
	 var typeId = jQuery("#cmbDmdmType").combobox('getValue');	
	 var fileId = jQuery("#hdnFileIdToModify").val();
	 if((typeId != null && typeId != '' && typeId != ' ') && (fileId != null && fileId != '' && fileId != ' '))
	 {
		 var dataStr = "?q=2&typeId="+typeId+"&fileId="+fileId;		
		 LoadPopUp("divTypeKeywords","docTempTypeVal_view.dcm"+dataStr, true,"80%","410px","0px","5%", "docTempTypeVal_Callback","Keywords",true,true);		 
 	 }
	 else
		 alert('Select Type');
});
function frmAddFile_beforeSubmit()
{
	var uploadedFile = jQuery('#hdnUploadedFile').val();
	if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
	{
		return 'notModify=Y';
	}
}
</script>
<form id="frmAddFile" name="frmAddFile">	
	<input type="hidden" id="hdnFileIdToModify" name="hdnFileIdToModify" value="${requestScope.dcmTlDocumentmanager.dmdmKeyid}"/>
	<div style="padding-left: 18px; padding-left: 12px\9;">	
	<div  style="padding-left:10px;padding-top:0px;" id = "fileUploadDocMgr"></div>

	<table>
	<tr>
		<td>
			<div style="padding-left:10px;">
				<label>Title</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="txtDmdmTitle" name="txtDmdmTitle" class="easyui-text" style="width:260px;" maxlength="20" value="${requestScope.dcmTlDocumentmanager.dmdmTitle}"/ >                       
			</div>
			
			<div  style="padding-left:10px;">
				<label>Subject Area</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbDmdmSubjectarea" name="cmbDmdmSubjectarea" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmSubjectarea}"/ >                       
			</div>
			
			<div   style="padding-left:10px;">
				<label class="mandatory-lbl">Keywords</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<textarea id="txtDmdmKeywords" name="txtDmdmKeywords"  maxlength="200" style="resize:none;width:260px;height:70px;">${requestScope.dcmTlDocumentmanager.dmdmKeywords}</textarea>		                      
			</div>
			
			<div  style="padding-left:10px;">
				<label class="mandatory-lbl">Description</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<textarea id="txtDmdmDescription" name="txtDmdmDescription"  maxlength="200" style="resize:none;width:260px;height:70px;">${requestScope.dcmTlDocumentmanager.dmdmDescription}</textarea>		                      
			</div>
		</td>
		<td valign="top">
			<div   style="padding-left:10px;margin-top:-2;">
				<label>Type</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbDmdmType" name="cmbDmdmType" class="easyui-combobox" style="width:230px;" value="${requestScope.dcmTlDocumentmanager.dmdmType}"/ >
				<input id="btnGetTypeKeywords" class="easyui-button" name="btnGetTypeKeywords"  type="button" value="..." style="height:20px;"  />                       
			</div>
			<div   style="padding-left:10px;">
				<label>Category</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbDmdmCategory" name="cmbDmdmCategory" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmCategory}"/ >                       
			</div>
			
			
			<div  style="padding-left:10px;">
				<label id="lblChanges" class="mandatory-lbl">Changes</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<textarea id="txtDmrhChanges" name="txtDmrhChanges"  style="resize:none;width:260px;height:70px;">${requestScope.changes}</textarea>		                      
			</div>
			<div   style="padding-left:10px;">
				<label>Owner</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbDmdmOwner" name="cmbDmdmOwner" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmOwner}"/ >                       
			</div>
			<div  style="padding-left:10px;">
				<label>Approved By</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbDmdmApprovedby" name="cmbDmdmApprovedby" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmApprovedby}"/ >                       
			</div>
			
		</td>
	</tr>
	</table>
	</div>
	<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value="${requestScope.dcmTlDocumentmanager.dmdmFilename}"/>
	<div class="easyui-paddingbfpx" style="padding-left:40%;"> 
		<input id="btnInsertFile" class="easyui-button" name="btnInsertFile"  type="button" value="Insert" style="height:20px;"  />
		<input id="btnCancelModFile" class="easyui-button" name="btnCancelModFile"  type="button" value="Cancel" style="height:20px;"  />
	</div>
	

</form>