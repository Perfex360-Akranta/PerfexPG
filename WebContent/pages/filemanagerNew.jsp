<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">	
var fmgMode="";
jQuery.noConflict();
jQuery(document).ready(function(){	
	//alert(1);
	jQuery('#submitForm').val('frmFileMangr');
	//alert(2);
	initialiseForm("frmFileMangr");
	//alert(3);
	//fillComboBox("frmFileMangr","cmbDmdmSubjectarea","subjectArea_combo.dcm");
	//fillComboBox("frmFileMangr","cmbDmdmType","type_combo.dcm");
	fillComboBox("frmFileMangr","cmbDmdmCategory","category_combo.dcm");
	fillComboBox("frmFileMangr","cmbDmdmOwner","employee.commonFilter");
	fillComboBox("frmFileMangr","cmbDmdmApprovedby","employee.commonFilter"); 
	//readOnlyFields('cmbDmdmApprovedby');
	//alert('file ');
	var docType = jQuery('#hdnDmdmRefdoctype').val();
	var docNo   = getFieldValue('hdnDmdmRefdocno','frmFileMangr');// jQuery('#hdnDmdmRefdocno').val();
	//alert('docType :'+docType);
	//alert('docNo '+docNo);
	processGridnew("DocGrid_input.dcm","?q=2&docType="+docType+"&docNo="+docNo,"file","pager","File Manager","","","file_loadComplete");
//	jQuery('#frmFileMangr.easyui-text').css('text-transform', 'uppercase');
 //   jQuery('#frmFileMangr textarea').css('text-transform', 'uppercase');	   
	var uploadedFile = jQuery('#hdnUploadedFile').val();
	if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
	{
		 //disableUIButton('btnGetTypeKeywords');
		 jQuery('#lblChanges').removeClass('mandatory-lbl');
	}
	else
	{
		 enableUIButton('btnGetTypeKeywords');
		 jQuery('#lblChanges').addClass('mandatory-lbl');
	}

	//alert(jQuery('#hdnformName').val());
	//var vSizelimit =  512000;
	var vSizelimit = 10120000;
	if (jQuery('#hdnformName').val()=="frmProject")
		vSizelimit = 5242880;
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
		//allowedExtensions: [],
		 allowedExtensions: ['jpg','jpeg','png','pdf','doc','docx','xls','xlsx','ppt','pptx','txt'],
		// each file size limit in bytes
		// this option isn't supported in all browsers
		sizeLimit: vSizelimit, // max size
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
	         emptyError: "{file} is empty, please select some other file.",
	        	  typeError: "{file} has invalid extension. Allowed extensions are: {'jpg','jpeg','png','pdf','doc','docx','xls','xlsx','ppt','pptx','txt'}."
		    //error messages, see qq.FileUploaderBasic for content
		},
		showMessage: function(messages){
			alert(messages);
			}
	});

	
   	fmgMode = jQuery('#hdnFmgMode').val();
 //alert("fmgMode FF :"+fmgMode);
	if (fmgMode=="view") {
		// alert("fmgMode FF inside :"+fmgMode);
		disableField("frmFileMangr","btnInsertFile");
		disableForm("frmFileMangr");	
		jQuery('.loadpopuptoolbar').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:50%;"> </div>');
	}

	var userId = jQuery('#hdnLoginUserid').val();
	var ownerId = getFieldValue('cmbDmdmOwner');
	if (ownerId=='' || ownerId ==' ')
		setFieldValue('cmbDmdmOwner',userId);

	var typeMstid = jQuery('#hdnTypeMstid').val();
	var typeId = getFieldValue('cmbDmdmType');
	if (typeId=='' || typeId ==' ')
		setFieldValue('cmbDmdmType',typeMstid);
	
});

function imgFormatDelete(id, options, rowObject)
{			
	var rowId = options.rowId;
	 				 
  	return '<input type="button" id="remov" class="grddeleteimg"  onclick="deleteimg(\''+rowId + '\');"/>';
}
function imgFormatDownload(id, options, rowObject)
{			
	var rowId = options.rowId;
	 			 
  	return '<input type="button" id="remov" class="grddownloadimg"  onclick="grddownloadimg(\''+rowId + '\');"/>';
}
function imgFormatEdit(id, options, rowObject)
{			
	var rowId = options.rowId;
	 				 
  	return '<input type="button" id="remov" class="grdeditimg"  onclick="imageEdit(\''+rowId + '\');"/>';
}
function grddownloadimg(rowId)
{
	 var rowData = jQuery("#file").jqGrid('getRowData',rowId);	
	 var keyid = rowData.hdnKeyid;
	 var filename = rowData.txtFilename;
	 var dataString = '?q=2&keyid='+keyid+'&filename='+filename;
	// alert(dataString);
	//  filename = filename.substring(0, 5)+"....."+filename.substring(filename.length-8);
	 //processAjaxCalls('file_download.file',dataString,'getdelete','getdeleteErr');
	 window.open("download_file.dcm?filename="+filename+"&id="+keyid," ", "height=200, width=200");
}
function imageEdit(rowId)
{
	 
	var rowData = jQuery("#file").jqGrid('getRowData',rowId);
	var filetypeData = rowData.cmbDoctype;
	//alert("filetypeData "+filetypeData);
	//jQuery('#cboFile').val(filetypeData);
	if(filetypeData =='OTHER')
	     jQuery('#cboFile').val('OTH');
	else if(filetypeData=='BLOCK DIAGRAM')	
		 jQuery('#cboFile').val('BLK');
		 
	else if(filetypeData=='DOCUMENTS')
		 jQuery('#cboFile').val('DPC');

	else if(filetypeData=='IMAGE')
		 jQuery('#cboFile').val('IMG');
	 
	else if(filetypeData== 'MAP')
		 jQuery('#cboFile').val('MAP');
		 
    else if(filetypeData== 'VIDEO')
		 jQuery('#cboFile').val('VID');

    else if(filetypeData== 'MANUAL')
		 jQuery('#cboFile').val('MNU');
    else if(filetypeData== 'SPECIFICATION')
		 jQuery('#cboFile').val('SPC'); 
	 
	 var descriptionData = rowData.txtDescription;	
  
	  jQuery('#txtDmdmDescription').val(descriptionData);
	  var filename = rowData.txtFilename;
	  
	  jQuery('#hdnUploadedFile').val(filename);
	 if(filename.length>30){
	  var  filename1 = filename.substring(0, 5)+"....."+ filename.substring(filename.length-8);
	  jQuery(".qq-upload-list").html(filename1);
	 }else
		 jQuery(".qq-upload-list").html(filename);
	  jQuery(".qq-upload-list").css('font-size','12px');
	  jQuery(".qq-upload-list").css('line-height','8px');
	  var keyid = rowData.hdnKeyid;
	  jQuery('#hdnDmdmKeyid').val(keyid);
	  var createdon =rowData.dteCreatedon;
	  jQuery('#hdncreatedon').val(createdon);
	 
	  setFieldValue('hdnDmdmRefdoctype',rowData.DMDM_REFDOCTYPE);
	  setFieldValue('hdnDmdmRefdocno',rowData.DMDM_REFDOCNO);
	  setFieldValue('txtDmdmTitle',rowData.DMDM_TITLE);
	  setFieldValue('cmbDmdmType',rowData.DMDM_TYPE);
	  setFieldValue('cmbDmdmSubjectarea',rowData.DMDM_SUBJECTAREA);
	  setFieldValue('txtDmdmKeywords',rowData.DMDM_KEYWORDS);
	  setFieldValue('txtDmrhChanges',rowData.cmbDoctype);
	  setFieldValue('cmbDmdmCategory',rowData.DMDM_CATEGORY);
	  setFieldValue('cmbDmdmOwner',rowData.DMDM_OWNER);
	  setFieldValue('cmbDmdmApprovedby',rowData.DMDM_APPROVEDBY);
	  //enableUIButton("btnFileManagerInsert");
}
function deleteimg(rowId)
{
	fmgMode = jQuery('#hdnFmgMode').val(); 	
	if (fmgMode=="view" || fmgMode=="fipdefine") 
		return;
	
	 var rowData = jQuery("#file").jqGrid('getRowData',rowId);
	 var keyid = rowData.hdnKeyid;
	 var filename = rowData.txtFilename;
	 //alert(filename);
	 var delFilename =filename;
	 if(filename.length>30){
		 delFilename = filename.substring(0, 5)+"....."+filename.substring(filename.length-8);
	 }
	 
	 var dataString = '?q=2&fileId='+keyid+'&filename='+filename;	
	 if(confirm("Do you want to delete this  " +delFilename ) == true){	
		processAjaxCalls('file_del.dcm',dataString,'getdelete','getdeleteErr');
	} 
}
function getdelete(){
	jQuery("#file").trigger("reloadGrid");
	jQuery("#txtDmdmDescription").val(''); 
	jQuery("#hdnfilename").val('');
	jQuery(".qq-upload-list").html(''); 
	jQuery('#hdnDmdmKeyid').val('');
	jQuery('#txtDmdmKeywords').val('');
}

jQuery("#btnInsertFile").click(function(){
	   var uploadname =jQuery(".qq-upload-list").html(); 
	  // var viewsMode = jQuery('#hdnViewsMode').val();
	   //var selFolderVal = jQuery('#hdnSelectedFolder').val();	   
	   //var userFolderRights = jQuery('#hdnUserFolderRights').val();
	   
	  // if(selFolderVal != null && selFolderVal != '' && selFolderVal != ' ')
	   		//var selFolderValArr = selFolderVal.split(',');
	   		
	  // if(userFolderRights != null && userFolderRights != '' && userFolderRights != ' ')
	   		//var userFolderRightsArr = userFolderRights.split(',');
		
	   jQuery("#preLodDiv").css('display','block');
	   jQuery("#preLodDiv").addClass("tpm-loading");
	   show_winMask(1);
	   //Added By Manikandan
	    var docType = jQuery('#hdnDmdmRefdoctype').val();
		var docNo   = getFieldValue('hdnDmdmRefdocno','frmFileMangr');// jQuery('#hdnDmdmRefdocno').val();  
		
	   /*END*/
	   var dataStr = 'file_save.dcm?q=2&notModify=N';
	   if(uploadname != null && uploadname != '' && uploadname != ' ')
		   dataStr += '&filename='+uploadname+"&docNo="+docNo+"&docType="+docType;
	   /*if(selFolderValArr[0] != null && selFolderValArr[0] != '' && selFolderValArr[0] != ' ')
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
			dataStr += '&allowRights='+userFolderRightsArr[3];	  */		
	  /* if(viewsMode != null && viewsMode != '' && viewsMode != ' ')
		   dataStr += '&viewsMode='+viewsMode;
*/
	 
	 saveForm('frmFileMangr',dataStr);
	 closeRtClkMenu('#dlgModifyFile');
		//processAjaxCalls('file_save.dcm','?q=2','InsertFile','InsertFileErr');
});

function frmFileMangr_successsCallback(result){
	//alert(" Sucess :: ");
	jQuery("#file").trigger("reloadGrid");
	//jQuery("#hdnDmdmRefdocno").val(result.successData.documentNo);
	//jQuery("#hdnDmdmRefdoctype").val(result.successData.docType);
	setFieldValue('hdnDmdmRefdocno', result.successData.documentNo,'frmFileMangr');
	setFieldValue('hdnDmdmRefdoctype', result.successData.docType,'frmFileMangr')
	jQuery("#fileUploadDocMgr").val("");
	jQuery(".qq-upload-file").html('');
	jQuery(".qq-upload-size").html('');
	jQuery(".qq-upload-list").html('');

}

jQuery("#btnClear").click(function( ){
	//disableUIButton("btnFileManagerInsert");
	jQuery("#txtDmdmDescription").val(''); 
	clearField('cmbDmdmCategory');
	clearField('txtDmdmTitle');
	clearField('cmbDmdmType');
	clearField('cmbDmdmSubjectarea');
	clearField('txtDmdmKeywords');
	clearField('txtDmrhChanges');
	clearField('cmbDmdmCategory');
	clearField('cmbDmdmOwner');
	clearField('cmbDmdmApprovedby');
	clearField('hdncreatedon');
	clearField('hdnDmdmKeyid');
	jQuery(".qq-upload-list").html('');
});

jQuery("#btnGetTypeKeywords").click(function(){
	 var typeId = jQuery("#cmbDmdmType").combobox('getValue');	
	 var fileId = 'DMM1311003';//jQuery("#hdnFileIdToModify").val();
	  
	 if((typeId != null && typeId != '' && typeId != ' ') && (fileId != null && fileId != '' && fileId != ' '))
	 {
		 var dataStr = "?q=2&typeId="+typeId+"&fileId="+fileId;		
		 LoadPopUp("divTypeKeywords","docTempTypeVal_view.dcm"+dataStr, true,"60%","410px","0px","5%", "docTempTypeVal_Callback","Keywords",true,true);		 
 	 }
	 else
		 alert('Select Type');
});
function frmFileMangr_beforeSubmit()
{
	var uploadedFile = jQuery('#hdnUploadedFile').val();
	if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
	{
		return 'notModify=Y';
	}
}
</script>
<form id="frmFileMangr" name="frmFileMangr" >
<div style="margin-top: -15px">	
	<input type="hidden" id="hdnFileIdToModify" name="hdnFileIdToModify" value="${requestScope.dcmTlDocumentmanager.dmdmKeyid}"/>
<div>	
	<table>
		<tr>
			<td colspan="6">
				<div style="margin-top:-10px;margin-left:10px;height:90px;width:230px;">					
					<span  style="padding-left:20px; width:220px;height:20px;"  id = "fileUploadDocMgr"></span>
				</div>
				
				<div>
				
			</td>
		</tr>
		<tr >
			<td>
				<div style="padding-left:10px;">
					<label>Title</label>                       
				</div> 
			</td>
			<td>
				<div   style="padding-left:10px;margin-top:-2;">
					<label>Type</label>                       
				</div> 
			</td>
			<td>
				<div  style="padding-left:10px;">
					<label>Subject Area</label>                       
				</div> 
			</td>
		</tr>
		<tr>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<input id="txtDmdmTitle" name="txtDmdmTitle" class="easyui-text" style="width:260px;" maxlength="50" value="${requestScope.title}" />                       
				</div>
			</td>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<input id="cmbDmdmType" name="cmbDmdmType" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmType}" />
					<!-- <input id="btnGetTypeKeywords" class="easyui-button" name="btnGetTypeKeywords"  type="button" value="..." style="height:20px;"  /> -->                       
				</div>
			</td>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<input id="cmbDmdmSubjectarea" name="cmbDmdmSubjectarea" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmSubjectarea}" />                       
				</div>
			</td>
		</tr>
		<tr>
			<td>
			
				<div   style="padding-left:10px;">
					<label >Keywords</label>                       
				</div> 
			</td>
			<td>
				<div  style="padding-left:10px;">
					<label >Description</label>                       
				</div> 
				
			</td>
			<td>
				<div  style="padding-left:10px;">
					<label id="lblChanges" class="mandatory-lbl">Changes</label>                       
				</div> 
				
			</td>
		</tr>
		<tr>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<textarea id="txtDmdmKeywords" class="limit-length" name="txtDmdmKeywords"  maxlength="200" style="resize:none;width:260px;height:70px;">${requestScope.keywords}</textarea>		                      
				</div>
			</td>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<textarea id="txtDmdmDescription" name="txtDmdmDescription"  class="limit-length" maxlength="200" style="resize:none;width:260px;height:70px;">${requestScope.description}</textarea>		                      
				</div>
			</td>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<textarea id="txtDmrhChanges" name="txtDmrhChanges"  class="limit-length"  maxlength="200"  style="resize:none;width:260px;height:70px;">${requestScope.changes}</textarea>		                      
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div   style="padding-left:10px;">
					<label>Category</label>                       
				</div> 
				
			</td>
			<td>
				<div   style="padding-left:10px;">
					<label>Owner</label>                       
				</div> 
				
			</td>
			<td>
				<div  style="padding-left:10px;">
					<label>Approved By</label>                       
				</div> 
				
			</td>
		</tr>
		<tr>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<input id="cmbDmdmCategory" name="cmbDmdmCategory" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmCategory}" />                       
				</div>
			</td>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<input id="cmbDmdmOwner" name="cmbDmdmOwner" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmOwner}" />                       
				</div>
			</td>
			<td>
				<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
					<input id="cmbDmdmApprovedby" name="cmbDmdmApprovedby" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmApprovedby}" />                       
				</div>
			</td>
		</tr>
	</table>
	</div>
	<div class="easyui-paddingbfpx" style="padding-left:67%;padding-left:68%\9;"> 
		<input id="btnInsertFile" class="easyui-button" name="btnInsertFile"  type="button" value="Insert" style="height:20px;"  />
		<input id="btnClear" class="easyui-button" name="btnClear"  type="button" value="Clear" style="height:20px;"  />
	</div>
	<div style ="margin-left:10px;padding-right:1%;padding-right:0%\9;"> 
  	<table id="file" ></table>
	<div id="pager"></div></div>
	
	<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value="${requestScope.dcmTlDocumentmanager.dmdmFilename}"/>
	<input type="hidden" id="hdnDmdmKeyid" name="hdnDmdmKeyid"  />
	<input type="hidden" id="hdncreatedon" name="hdncreatedon"   />
<input type="hidden" id="hdnkeyid"  />
<input type="hidden" id="hdnfilename"  />
<input type="hidden" id="hdnbuttonId" value="${requestScope.buttonId}"  />
<input type="hidden" id="hdnformName" value="${requestScope.formName}"  />
<input type="hidden" id="hdnDmdmRefdocno" name="hdnDmdmRefdocno" value="${requestScope.documentNo}"  />

<input type="hidden" id="hdnLoginUserid" name="hdnLoginUserid" value="${requestScope.loginUserid}"  />
<input type="hidden" id="hdnTypeMstid" name="hdnTypeMstid" value="${requestScope.typeMstid}"  />

<input type="hidden" id="hdnDmdmRefdoctype" name="hdnDmdmRefdoctype" value="${requestScope.documentType}"  />
<input type="hidden" id="hdnDmdmIsodoctype" name="hdnDmdmIsodoctype" value="${requestScope.docLayoutid}"  />
<input type="hidden" id="hdnFmgMode" value="${requestScope.fmgMode}"  />

</div>
</form>