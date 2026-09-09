<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){	
		initialiseForm('frmMasterTbl');
		var url = jQuery('#mastInterUrl').val();
		var masterForm = jQuery('#masterForm').val();
		if(url.indexOf("?")>-1)
			url += '&';
		else
			url += '?';
		
		if( masterForm != 'Y') masterForm = "";
			url += 'loadContentDivId=LoadMasterTbl&preLoadContentDivId=preLoadMasterTbl&navigateNext=false&masterForm='+masterForm;
		var menuId = jQuery("#genmstmenuid").val();
		url += "&genmstmenuid="+menuId;			
		setSubmitFormUrl(url);
		processGridnew(url,"&q=1", "grdMMC","pgrGrdMMC","","grdMMC_doubleClick_CallBack","","");
		url = url.replace(url.substr(0,url.indexOf("_input")),"loadmast");	
		LoadForm("LoadMasterTbl","preLoadMasterTbl",url,"dispErr", "","LoadMasterTbl_errorCallBack");
	});

function LoadMasterTbl_errorCallBack(result){
	
}	
function frmMasterTbl_beforeDelete(){
	
	if(! confirm("Do you want to delete this record?")){
		return false;
	}
}
function frmMasterTbl_deleteExceptionCallback(result){
	if(result.tpmException != undefined && result.tpmException != null && result.tpmException.confirm!=undefined && result.tpmException.confirm != null)
	{
		alert(result.tpmException.confirm);
	}	
				
}

function grdMMC_doubleClick_CallBack(rowId){
	//alert("entering double click with the rowID " + rowId );
	
	var url = jQuery('#mastInterUrl').val();
	url = url.replace("_input","_recall"); 
	var menuId = jQuery("#genmstmenuid").val();	
	if( url.indexOf("?") > 0)
		url += "&";
	else
		url += "?";
	
	url += "genmstmenuid="+menuId;		
	clearValidationErrorMessages("frmMasterTbl","dispErr");
	jQuery("#wrapperRpt").focus();	
	processAjaxCalls(url,"&keyid="+rowId,"masterTableSuccessCallBack","masterTableErrorCallBack");
	
}

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
    action: 'file_upload.mastertblconfig',
	// additional data to send, name-value pairs
	params: {},
	numFiles:1,
	// validation
	// ex. ['jpg', 'jpeg', 'png', 'gif'] or []
	allowedExtensions: [],
	// each file size limit in bytes
	// this option isn't supported in all browsers
	sizeLimit: 65242880, // max size
	minSizeLimit: 1, // min size
	// set to true to output server response to console
	debug: false,
	// events
	// you can return false to abort submit
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
	      
	    //error messages, see qq.FileUploaderBasic for content
	},
	   showMessage: function(messages){
		alert(messages);
		}
	});	

 jQuery("#btnUploadExcel").click(function(){	
  var menuIdin=jQuery('#genmstmenuid').val();	
  var fileUp=jQuery(".qq-upload-list").html();
  if(!(fileUp.length>0)){
  alert("Select File To Upload");
  return false;
 }
 processAjaxCalls("file_save.mastertblconfig?genmstmenuid="+menuIdin,"","successcallback");
});

function frmMasterTbl_successsCallback(result){
 
jQuery("#grdMMC").trigger("reloadGrid");
}

function successcallback(result){ 

 alert("File Upload Successfully"); 
 jQuery("#grdMMC").trigger("reloadGrid");
}
    
function frmMasterTbl_deleteSuccessCallback(result){
    alert(result.successData.msg);
	clearForm('frmMasterTbl');
	jQuery("#grdMMC").trigger("reloadGrid");
}

function frmMasterTbl_exceptionCallback(result){
	
}
function frmMasterTbl_errorCallback(){
	
}

function masterTableSuccessCallBack(result){
	var values = result.mastTblValues;
	for(var i =0; i< values.length;i++ ){
		var names = Object.keys(values[i]);
		//var indx = (names[0] == "type"?0:1); 
		var indv = (names[0] == "type"?1:0);
		
		if( values[i]["type"] == "F"){ 
			
			 var ctrlid = names[indv];
			 var dataStr = '&flid='+values[i][names[indv]];
			loadFunctionalLocation(ctrlid+'funLocation','functionalLoc.commonFilter',ctrlid+'funLocation','frmMasterTbl',dataStr);
		}
		else	
			setFieldValue(names[indv],values[i][names[indv]]);
	}
}
function frmMasterTbl_FuntLocHierarchy_SuccessCallBack(keyIds){
		 setFunctionalLocWidth("frmMasterTbl","450px");
}


</script> 
<div id="wrapperRpt" style="width:100%" tabindex="0">
	<div id="container" style="width:100%" >
<!--//removed id= row to make the dat in rows-->
		<table>
			<tr>
				<td colspan="2">
					<div >
						<label style="color:dark brown;font-weight: bold">Double click on data row to Update,Delete Records</label>									
					</div>	
				</td>
			</tr>
			<tr>
				<td style="vertical-align:top">
					<div id="left">
							<table id="grdMMC" style="width:60%;"><tr><td/></tr></table>
							<div id="pgrGrdMMC"></div>
							<input type="hidden" id="mastInterUrl" value ="${requestScope.masterIntUrl}" />
							<input type="hidden" id="masterForm" value ="${requestScope.masterForm}" />
							<input type="hidden" id="hdnmenuCaption" value ="${requestScope.menuCaption}" />
							<input type="hidden" id="genmstmenuid" value ="${requestScope.genmstmenuid}" />
							<input type="hidden" id="hdntableName" value="${requestScope.tableName}" />
				</td>
				<td valign="top">
					<div id="right"  style=" float: right;padding-left:70px;padding-top:30px;">
						<form id="frmMasterTbl" tableindex="0">
							<div id ="preLoadMasterTbl"></div>
							<div id ="LoadMasterTbl" tabindex="0" ></div>
							<input type="hidden" id="mode" value="create"/>
							<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value=""/>
			<div id="" style="margin-left:-10px;margin-top:20px">
		 	 <div id="fileUpload" style="width:200px;"></div>											
            <input type="button" id="btnUploadExcel" class="easyui-button"  name="btnUploadExcel" value="Upload Excel" style="width:150px; margin-left:230px; margin-top:-80px;" />							
		                 </div>  				
						</form>
					</div>	
				</td>
			</tr>
		</table>	
	</div>	
</div>