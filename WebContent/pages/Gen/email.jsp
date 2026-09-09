<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	

<script>
jQuery(document).ready(function(){
		initialiseForm("frmEmail"); 
		jQuery("#btnSend").click(function() { 

			var sendTo = jQuery("#txtToEmployee").val();
			var ccTo = jQuery("#txtCCEmployee").val();
			var subject = jQuery("#txtSubject").val();
			var message = jQuery("#txtContent").val();
			var fileName = jQuery(".qq-upload-file").html();
					  
			if( fileName == undefined ) fileName="";
			processAjaxCalls("email_send.email","&sendTo="+sendTo+"&ccTo="+ccTo+"&subject="+subject+"&message="+message+"&fileName="+fileName, "sendMail_successcalBack", "", "", "", "new");
		   //return false;
		});

		var uploader = new qq.FileUploader({
		    // pass the dom node (ex. $(selector)[0] for jQuery users)
		    element:   document.getElementById("fleMailAttachment"),
		    // path to server-side upload script
		    action: 'file_upload.file',
		    uploadLabelName:'^',
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
				//disableUIButton("btnFileManagerInsert");
				if( jQuery(".qq-upload-list").length>0 )
					jQuery(".qq-upload-list").html('');
			},
			onProgress: function(id, fileName, loaded, total){},
			onComplete: function(id, fileName, responseJSON){ //enableUIButton("btnFileManagerInsert");
				},
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
function sendMail_successcalBack(result){
	//if(result.isSuccss){
		alert(result.msg);
	//}
}


</script>
<form id="frmEmail">
	<div class="" style="margin-top:5px;margin-left:10px">
		<table style="margin-left:2%;margin-top:2px">
			<tr>
				<td style="width:450px; vertical-align:top">
									
					<div>
						<div><label class="mandatory-lbl">To</label></div>
						<input id="txtToEmployee" name="txtToEmployee" class="easyui-text" tabindex="6" value="" style="width:450px;" />
					</div>
			 	</td>
			 	
			 </tr>
			 <tr>
				<td style="width:570px; vertical-align:top">
									
					<div>
						<div><label class="">CC</label></div>
						<input id="txtCCEmployee" name="txtCCEmployee" class="easyui-text" tabindex="6" value="" style="width:570px;" />
					</div>
			 	</td>
			 	
			 </tr>
			 <tr>
				<td style="width:570px; vertical-align:top">
									
					<div>
						<div><label class="">Subject</label></div>
						<input id="txtSubject" name="txtSubject" class="easyui-text" tabindex="6" value="" style="width:570px;" />
					</div>
			 	</td>
			 	
			 </tr>

			 <tr>
				<td style="width:570px; vertical-align:top">
									
					<div>
						<div><label class="">Content</label></div>
						<textarea id="txtContent" name="txtContent" class="easyui-textarea" tabindex="6" rows=6 cols=5 style="width:570px;" ></textarea>
						
					</div>
			 	</td>
			 	
			 	
			 </tr>
		
		<tr>
			<td>
			<div>
			
			 <span  style="margin-left:0%;margin-top:0px;width:20px;height:20px;position:absolute;" id = "fleMailAttachment"></span>
			<input id="btnSend" class="easyui-button"  value="Send" style="margin-left:56%; width:250px;" />
			</div>
			</td>
		</tr>
		</table>	 								
	
</form>


