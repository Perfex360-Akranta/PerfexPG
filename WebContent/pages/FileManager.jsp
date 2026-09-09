<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">

	jQuery(document).ready(function(){
		//alert(1);
		initialiseForm('frmfile');	
		jQuery('#frmfile textarea').css('text-transform', 'uppercase');
		processGridnew("file_input.file","?q=2","file","pager","File Manager","","","file_loadComlete");
		 								
		
	    
	jQuery("#btnFileManagerInsert").click(function(rowId){

		
	    var fileType = jQuery('#cboFile').val();
  		var keyid = jQuery('#hdnkeyid').val();
		var description = jQuery('#txtdesc').val();
		var  createdon= jQuery('#hdncreatedon').val();
		var filename = jQuery("#hdnfilename").val();
		var rowData = jQuery("#file").jqGrid('getRowData',rowId);
		var filenames = rowData.txtFilename;
		var uploadname =jQuery(".qq-upload-list").html(); 
		
		var dataString = '?q=2&fileType='+fileType+'&description='+description+'&keyid='+keyid+'&filename='+filename+'&createdon='+createdon;
		 
	   	if(uploadname != null  &&  uploadname !="" && uploadname !=" " &&uploadname !="null"){
		   	
			    jQuery("#preLodDiv").css('display','block');
				jQuery("#preLodDiv").addClass("tpm-loading");
				show_winMask(1);
				 
			processAjaxCalls('commfile_save.file',dataString,'getInsertS','getInsertErr');
	   	}
		  else
			  processAjaxCalls('commfile_save.file',dataString,'getNotInsertS','getInsertErr');
		 /* jQuery("#preLodDiv").css('display','block');
			jQuery("#preLodDiv").addClass("tpm-loading");
			show_winMask(1); */
	 //var dlgimage =  jQuery("#btnInsert");
	 //	imageUpload(dlgimage,'ImageUpload.commonFilter','btnInsert'," ");	  
	//  }
	   	disableUIButton("btnFileManagerInsert");
	   	
	});
	
jQuery("#btnClr").click(function(rowId){

	disableUIButton("btnFileManagerInsert");
	jQuery("#txtdesc").val(''); 
	jQuery('#cboFile').val('OTH');
//	jQuery("#fileUpload").val(''); 
	jQuery(".qq-upload-list").html('');
});

	var uploader = new qq.FileUploader({
	    // pass the dom node (ex. $(selector)[0] for jQuery users)
	    element:   document.getElementById("fileUpload"),
	    // path to server-side upload script
	    action: 'file_upload.file',
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
			disableUIButton("btnFileManagerInsert");
			if( jQuery(".qq-upload-list").length>0)
				jQuery(".qq-upload-list").html('');
		},
		onProgress: function(id, fileName, loaded, total){},
		onComplete: function(id, fileName, responseJSON){ enableUIButton("btnFileManagerInsert");},
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
	disableUIButton("btnFileManagerInsert");
});
function file_loadComlete(){
	var frmName = jQuery('#hdnformName').val();
	var buttonId = jQuery('#hdnbuttonId').val();
	var row = jQuery("#file").jqGrid('getDataIDs');
	//alert(jQuery("#fileCount").html());
	//jQuery("#"+frmName + 'input[id=' +buttonId + '] ').parent("span").html(row.length);
	jQuery("#fileCount").html(row.length);
	
}
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
	 window.open("file_download.file?filename="+filename," ", "height=200, width=200");
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
  
	  jQuery('#txtdesc').val(descriptionData);
	  var filename = rowData.txtFilename;
	  
	  jQuery('#hdnfilename').val(filename);
	 // jQuery(".qq-upload-list").html(filename);
	 if(filename.length>30){
	  var  filename1 = filename.substring(0, 5)+"....."+ filename.substring(filename.length-8);
	  jQuery(".qq-upload-list").html(filename1);
	 }else
		 jQuery(".qq-upload-list").html(filename);
	  //jQuery(".qq-upload-list").css('word-wrap','break-word');
	  jQuery(".qq-upload-list").css('font-size','12px');
	  jQuery(".qq-upload-list").css('line-height','8px');
	 // alert("fil "+filename+ " :: "+  jQuery('#fileUpload').val(filename));
	 // jQuery('#fileUpload').val(filename);
	  var keyid = rowData.hdnKeyid;
	  jQuery('#hdnkeyid').val(keyid);

	  var createdon =rowData.dteCreatedon;
	  jQuery('#hdncreatedon').val(createdon);
	  enableUIButton("btnFileManagerInsert");
	 // processAjaxCalls('commfile_save.file',dataString,'getInsertS','getInsertErr');
}
function deleteimg(rowId)
{
	 	
	 var rowData = jQuery("#file").jqGrid('getRowData',rowId);
	 var keyid = rowData.hdnKeyid;
	 var filename = rowData.txtFilename;
	 //alert(filename);
	 var delFilename =filename;
	 if(filename.length>30){
		 delFilename = filename.substring(0, 5)+"....."+filename.substring(filename.length-8);
	 }
	 
	 var dataString = '?q=2&keyid='+keyid+'&filename='+filename;	
	 if(confirm("Do you want to delete this  " +delFilename ) == true){	
		processAjaxCalls('commfile_delete.file',dataString,'getdelete','getdeleteErr');
	} 
}
function getdelete(result)
{
	alert(result.successData.msg);
	//if(confirm(result.successData.approvalMsg) == true){
	jQuery("#file").trigger("reloadGrid");
	jQuery("#txtdesc").val(''); 
	jQuery('#cboFile').val('OTH');
	jQuery("#hdnfilename").val('');
	//jQuery("#fileUpload").val('');
	jQuery(".qq-upload-list").html(''); 
	jQuery('#hdnkeyid').val('');
	 //}

	disableUIButton("btnFileManagerInsert");
		
}
 
function getInsertS(result)
{
	if( result.successData != null ){
		alert(result.successData.msg);	
		
					
		jQuery("#file").trigger("reloadGrid");
		jQuery("#txtdesc").val(''); 
		jQuery('#cboFile').val('OTH');
	//	jQuery("#fileUpload").val(''); 
		jQuery(".qq-upload-list").html('');
		jQuery('#hdnkeyid').val('');
		jQuery("#hdnfilename").val('');
		show_winMask(0);
		jQuery("#preLodDiv").removeClass("tpm-loading");
		jQuery("#preLodDiv").css('display','none');
	}
	else if( result.tpmException || result.exception  ){
	   	enableUIButton("btnFileManagerInsert");		
	}	
	
}
function getNotInsertS(result)
{
	if( result.successData != null ){
		alert(result.successData.msg);
	}	
	else if( result.tpmException || result.exception  ){
	   	enableUIButton("btnFileManagerInsert");		
	}	
				
}
function getInsertErr(result)
{	
	 
}

</script>
<form name="frmfile" id="frmfile" >
<div id="preLodDiv" class="" style="width:100%;display:none;"></div>
<div >
<!--	   <div class="main-header">File Manager</div>-->
   <table>
	   <tr>
	   <td>
	    <div  class="easyui-paddingbfpx">
		<label>File Type</label>                       
  		</div> 
		<div class="easyui-paddingbtpx"> 
   		<select id="cboFile" class="easyui-combobox" name="cboFile" style="width:120px;" >
<!--   		<option value="Others"> Others</option>-->
<!--        <option value="Block Diagram">Block Diagram</option>-->
<!--        <option value="Document Type"> Document Type </option>-->
<!--		<option value="Image"> Image </option>-->
<!--		<option value="Map"> Map </option>-->
<!--	    <option value="Video"> Video</option>-->
		

        <option value="BLK">Block Diagram</option>
        <option value="DPC"> Documents</option>
		<option value="IMG"> Image </option>
		<option value="MAP"> Map </option>
	    <option value="VID"> Video</option>
	    <option value="MNU"> Manual </option>
	    <option value="SPC"> Specification </option>
	    <option value="OTH"> Others</option>
		
		</select>  
        </div>
	   <div class="easyui-paddingbfpx" style="margin-top: 0px;"> <label>Description</label>  </div>
       <div class="easyui-paddingbfpx"><span><textarea id="txtdesc" name="txtdesc"  cols="50" rows="4" maxlength=500></textarea>
       </span>
	   	</div>
       </td>
       <td>
       <td>
       <div  style="padding-left:0px;padding-top:60px;" id = "fileUpload"></div>
       
        <div style="margin-top: 0px">
	   	<input id="btnFileManagerInsert" class="easyui-button" name="btnInsert"  type="button" value="Insert" style="height:20px;"/><span style ="padding-left:10px">	<input id="btnClr" class="easyui-button"  type="button" value="Clear" style="height:20px;"/></span>
	   	</div>
	   </td>	
	   	</tr>
	   	</table>
	   	<div style ="padding-right:20px;"> 
  	<table id="file" ></table>
	<div id="pager"></div></div>
   </div>  
<input type="hidden" id="hdnkeyid"  />
<input type="hidden" id="hdncreatedon"  />
<input type="hidden" id="hdnfilename"  />
<input type="hidden" id="hdnbuttonId" value="${requestScope.buttonId}"  />
<input type="hidden" id="hdnformName" value="${requestScope.formName}"  />

</form>