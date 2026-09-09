<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">	
jQuery.noConflict();
jQuery(document).ready(function(){	
	initialiseForm('frmRevisionHistory');	   
	viewGrid('revHist_input.dcm',"?q=1");
});

function viewGrid(url,filterString)
{
	var fileId = jQuery("#hdnFileId").val();
	if(fileId != null && fileId != ' ' && fileId != '')
		filterString += '&fileId='+fileId;
	processGridnew(url,filterString,"revisionHisGrid","revisionHisPager","Revision History","","","revisionHistory");
}
function revisionHistory()
{
	if(screen.width <=1250)
	{
		jQuery( "#revisionHisGrid" ).setGridWidth(724);
	}
}
	/*jQuery('#submitForm').val('frmRevisionHistory'); 
	initialiseForm("frmRevisionHistory");
	fillComboBox("frmRevisionHistory","cmbSubjectarea","subjectArea_combo.dcm");
	fillComboBox("frmRevisionHistory","cmbCategory","category_combo.dcm");
	fillComboBox("frmRevisionHistory","cmbOwner","employee.commonFilter");
	fillComboBox("frmRevisionHistory","cmbApprovedby","employee.commonFilter");
	readOnlyFields('cmbApprovedby');  
	jQuery('#frmRevisionHistory .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmRevisionHistory textarea').css('text-transform', 'uppercase');	   

	
    var uploader = new qq.FileUploader({
	    // pass the dom node (ex. $(selector)[0] for jQuery users)
	    element:   document.getElementById("fileUploadRevHist"),
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
	});*/

/*jQuery("#btnInsertRevHist").click(function(){
	   var uploadname =jQuery(".qq-upload-list").html(); 
	   var Subjectarea = jQuery("#cmbSubjectarea").combobox('getValue');
	   
	   var category = jQuery("#cmbCategory").combobox('getValue');
	   var owner = jQuery("#cmbOwner").combobox('getValue');
	   var approvedBy = jQuery("#cmbApprovedby").combobox('getValue');
	   var selFolderVal = jQuery('#hdnSelectedFolder').val();
	
	   var selFolderValArr = selFolderVal.split(',');
	   
	   jQuery("#preLodDiv").css('display','block');
	   jQuery("#preLodDiv").addClass("tpm-loading");
	   show_winMask(1);
	   var dataStr = 'file_save.dcm?q=2&saveMode=revisionHist';
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
	   if(Subjectarea != null && Subjectarea != '' && Subjectarea != ' ')
		   dataStr += '&cmbDmdmSubjectarea='+Subjectarea;
	   if(category != null && category != '' && category != ' ')
		   dataStr += '&cmbDmdmCategory='+category;
	   if(owner != null && owner != '' && owner != ' ')
		   dataStr += '&cmbDmdmOwner='+owner;
	   if(approvedBy != null && approvedBy != '' && approvedBy != ' ')
		   dataStr += '&cmbDmdmApprovedby='+approvedBy;
	   
	
	 saveForm('frmRevisionHistory',dataStr);	
});
jQuery("#btnCancelRevHist").click(function(){
	closePopUpDialoge('divAddRevisionHistory');
});
*/
</script>
<form id="frmRevisionHistory" name="frmRevisionHistory">
<div style=""> 
	 <table id="revisionHisGrid" style="width:100%"><tr><td/></tr></table>
	 <div id="revisionHisPager"></div>
</div>
	
<!--	<div  style="padding-left:10px;padding-top:20px;" id = "fileUploadRevHist"></div>-->
<!--	<table>-->
<!--	<tr>-->
<!--		<td>-->
<!--			<div   style="padding-left:10px;">-->
<!--				<label>Title</label>                       -->
<!--			</div> -->
<!--			<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--				<input id="txtDmdmTitle" name="txtDmdmTitle" class="easyui-text" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmTitle}"/ >                       -->
<!--			</div>-->
<!--			-->
<!--			<div  style="padding-left:10px;">-->
<!--				<label>Subject Area</label>                       -->
<!--			</div> -->
<!--			<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--				<input id="cmbSubjectarea" name="cmbSubjectarea" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmSubjectarea}"/ >                       -->
<!--			</div>-->
<!--			-->
<!--			<div   style="padding-left:10px;">-->
<!--				<label class="mandatory-lbl">Keywords</label>                       -->
<!--			</div> -->
<!--			<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--				<textarea id="txtDmdmKeywords" name="txtDmdmKeywords"  style="resize:none;width:260px;height:70px;">${requestScope.dcmTlDocumentmanager.dmdmKeywords}</textarea>		                      -->
<!--			</div>-->
<!--			-->
<!--			<div  style="padding-left:10px;">-->
<!--				<label>Approved By</label>                       -->
<!--			</div> -->
<!--			<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--				<input id="cmbApprovedby" name="cmbApprovedby" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmApprovedby}"/ >                       -->
<!--			</div>-->
<!--		</td>-->
<!--		<td valign="top">-->
<!--			<div   style="padding-left:10px;margin-top:-2;">-->
<!--				<label>Category</label>                       -->
<!--			</div> -->
<!--			<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--				<input id="cmbCategory" name="cmbCategory" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmCategory}"/ >                       -->
<!--			</div>-->
<!--			<div   style="padding-left:10px;">-->
<!--				<label>Owner</label>                       -->
<!--			</div> -->
<!--			<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--				<input id="cmbOwner" name="cmbOwner" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmOwner}"/ >                       -->
<!--			</div>-->
<!--			-->
<!--			<div  style="padding-left:10px;">-->
<!--				<label class="mandatory-lbl">Changes</label>                       -->
<!--			</div> -->
<!--			<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--				<textarea id="txtDmrhChanges" name="txtDmrhChanges"  style="resize:none;width:260px;height:70px;">${requestScope.changes}</textarea>		                      -->
<!--			</div>-->
<!--		</td>-->
<!--	</tr>-->
<!--	</table>-->
<!--	<input type="hidden" id="hdnUploadedFileRevHist" name="hdnUploadedFileRevHist" value="${requestScope.dcmTlDocumentmanager.dmdmFilename}"/>-->
<!--	<div class="easyui-paddingbfpx" style="padding-left:40%;"> -->
<!--		<input id="btnInsertRevHist" class="easyui-button" name="btnInsertRevHist"  type="button" value="Insert" style="height:20px;"  />-->
<!--		<input id="btnCancelRevHist" class="easyui-button" name="btnCancelRevHist"  type="button" value="Cancel" style="height:20px;"  />-->
<!--	</div>-->
	
<input type="hidden" id="hdnFileId" name="hdnFileId" class="easyui-text" value="${requestScope.dcmTlDocumentmanager.dmdmKeyid}"/>
</form>