<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
    	jQuery(document).ready(function(){	
		initialiseForm('frmimagePrivillege');
		var url = jQuery('#hiddenUrl').val();
	
	
	}); 	
    	
    	
var uploadedFile = jQuery('#hdnUploadedFile').val();
if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
{	
}	 
    var uploader = new qq.FileUploader({
    // pass the dom node (ex. $(selector)[0] for jQuery users)
    element:   document.getElementById("fileUpload"),
    name:"Upload",
    uploadLabelName:"Browse",
    // path to server-side upload script
    action: 'file_upload.emr',
	// additional data to send, name-value pairs
	params: {},
	numFiles:1,
	allowedExtensions: [],
	sizeLimit: 65242880, 
	minSizeLimit: 1, 
	debug: false,
		onSubmit: function(id, fileName){
		if( jQuery(".qq-upload-list").length>0){
			jQuery(".qq-upload-list").html('');
		}		
	},
	onProgress: function(id, fileName, loaded, total){},
	onComplete: function(id, fileName, responseJSON){
		jQuery(".qq-upload-failed-text").hide();},
	onCancel: function(id, fileName){},
	messages: {
		 sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
         minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
         emptyError: "{file} is empty, please select some other file."
	   	},
	   showMessage: function(messages){
		alert(messages);
		}	
	});
    
    
    
    jQuery("#btnUploadImage").click(function(){
    	alert("Image Upload Success");
    	/*var uploadname =jQuery(".qq-upload-list").html(); 
	   if(!(uploadname.length>0 )){
	   alert("Select File To Upload");
	   return false;
	   
	 }  */ 
	//  processAjaxCalls("file_upload.emr","?q=2","","","successsCallback");
	 });	
  function successsCallback(result){
	  alert("Image Upload Successfully");
  }  
  

</script> 
<div id="wrapperRpt" style="width:100%" tabindex="0">
<div id="container" style="width:100%" >
<div id="right"  style=" float: right;padding-left:70px;padding-top:30px;">
<form id="frmimagePrivillege" tableindex="0">
<input type="hidden" id="mode" value="create"/>
<input type="hidden" id="htnfilename">
<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value=""/>
<input type="hidden" id="hdnfilename"  />
			<div id="" style="margin-left:-10px;margin-top:20px">
		 	 <div id="fileUpload" style="width:200px;"></div>
            <input type="button" id="btnUploadImage" class="easyui-button"  name="btnUploadImage" value="Upload Image" style="width:150px; margin-left:230px; margin-top:-80px;"/>							
		     </div> 
			</form>
			</div>
	</div>	
</div>