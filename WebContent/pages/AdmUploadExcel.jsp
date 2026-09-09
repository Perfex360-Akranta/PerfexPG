<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/fileuploader.js"></script>
<script type="text/javascript">

	var timer=null;
	jQuery(document).ready(function(){	
		initialiseForm('frmUploadExcel');	
		jQuery('#frmUploadExcel textarea').css('text-transform', 'uppercase');
		//alert("hdnurl:" + jQuery("#hiddenUrl").val());
		jQuery("#hdnxmlname").val(jQuery("#hiddenUrl").val());
		jQuery("#btnClr").click(function(rowId){	
			//disableUIButton("btnFileManagerInsert");			
			jQuery("#txtdesc").val(''); 
			jQuery(".qq-upload-list").html('');
			jQuery("#file").clearGridData();
			jQuery("#hdnXml").val("0");		
			jQuery("#hdnErrLogProcess").val("0");
		});
		jQuery("#btnDownload").click(function(){
			processAjaxCalls("downloadExl_Template.uploadexl","?q=2&file=" + jQuery("#hdnxmlname").val(),"exlTempOnsuccessCallback","exlTempErr");
		});
		/*
		jQuery("#btnDownload").change(function(){
			//alert("change");
			//alert(location.pathname);
			//var imagepath = "file:\\" + jQuery("#btnDownload").val();
			processAjaxCalls("downloadExl_Template.uploadexl","?q=2&file=" + jQuery("#btnDownload").val(),"exlTempOnsuccessCallback");	          
	        //alert(imagepath);
		});
		*/		
		jQuery("#btnUploadExcel").click(function(){	
			//jQuery("#hdnXml").val("1");			
			processAjaxCalls("uploadfile_start.uploadexl","?q=2","UploadStartOnsuccessCallback","UploadStartOnErr");
			//UploadProcess();
			/*//var result;	
			//result = setInterval(UploadProcess, 500);	
			//setTimeout(function() {processAjaxCalls("uploadfile_process.uploadexl","?q=2","ErrRowsOnsuccessCallback","ErrRowsOnErr");},1250);
			var i= 0;	
			var j= 0;	
			while (jQuery("#hdnXml").val()=="1"){
				
				//while(jQuery("#hdnErrLogProcess").val()=="0" ){		
					//alert("UploadProcess");
					j++;
					while(jQuery("#hdnErrLogProcess").val()=="0"){
						console.log( " while j:" + j);
						console.log( " while i:" + i++);
						UploadProcess();	
					//console.log( " after while i:" + i++);
					//alert("i=" + i);					
					//break;
					}
				//}
			}*/	
			//UploadProcess();
			/*if(jQuery("#hdnXml").val()=="0"){ 
				break;	
			}	*/
			//UploadProcess();
		});
	
		var uploader = new qq.FileUploader({
		    // pass the dom node (ex. $(selector)[0] for jQuery users)
		    element:   document.getElementById("fileUpload"),
		    // path to server-side upload script
		    action: 'file_upload.uploadexl',
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
				//alert("onsubmit");			
				if( jQuery(".qq-upload-list").length>0)
					jQuery(".qq-upload-list").html('');			
				
				//result = setInterval(UploadProcess, 500);		
			},
			onProgress: function(id, fileName, loaded, total){			
				//alert('onprogress');			
				/*if (jQuery("#hdnXml").val()!="1"){
					processAjaxCalls("uploadfile_Xml.uploadexl","?q=2","XmlOnsuccessCallback");
				}
				else{				
					UploadProcess();					
				}*/
				//UploadProcess();
				
			},
			onComplete: function(id, fileName, responseJSON){ 
				//alert("onComplete");			
				//clearInterval(result);	
				//processGridnew("Uploadfile_view.uploadexl","?q=2","file","pager","File Manager","","","file_loadComlete");
			},
			onCancel: function(id, fileName){clearInterval(result);	},
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


function exlTempOnsuccessCallback(result){
	alert("Success");	
	//alert(result);
}
function exlTempErr(result){
	alert("Error");	
}

	
function UploadStartOnsuccessCallback(result){
	//alert("UploadStartOnsuccessCallback");
	
	//jQuery("#hdnXml").val("0");
	//console.log( "UploadStartOnsuccessCallback " );	
	processGridnew("Uploadfile_view.uploadexl","?q=2","file","pager","File Manager","","","file_loadComlete");		
	/*window.clearInterval(timer);
	alert("closed" + timer);
	timer=null;
	window.clearInterval(timer);
	alert("closed" + timer);*/
	//window.clearTimeout(timer);
	//alert("closed" + timer);
}
function UploadStartOnErr(result){
	//alert("UploadStartOnErr" );
}

function sleep(milliseconds) {
	var start = new Date().getTime();
	for (var i = 0; i < 1e7; i++) {
	  if ((new Date().getTime() - start) > milliseconds){
	    break;
	  }
	}
}

function XmlOnsuccessCallback(result){	
	if (result.IsXmlStructure=='true' || result.IsXmlStructure==true){
		//alert("XmlOnsuccessCallback:" +result.IsXmlStructure );
		jQuery("#hdnXml").val("1");		
		processGridnew("Uploadfile_view.uploadexl","?q=2","file","pager","File Manager","","","file_loadComlete");		
	}
}
	
function UploadProcess() {
	//alert("UploadProcess");
	//console.log( " UploadProcess");	
	console.log( "UploadProcess");
	//jQuery("#hdnErrLogProcess").val("1");
	processAjaxCalls("uploadfile_process.uploadexl","?q=2","ErrRowsOnsuccessCallback","ErrRowsOnErr");
	console.log( "recall UploadProcess " + jQuery("#hdnXml").val());
	if (jQuery("#hdnXml").val()=="1"){		
		console.log( "recall UploadProcess ");
		UploadProcess();
	}	
	
}

function ErrRowsOnsuccessCallback(result)
{
	console.log( "ErrRowsOnsuccessCallback");
	//jQuery("#hdnErrLogProcess").val("0");	
	
	var allRows =jQuery("#file").jqGrid('getGridParam', 'records');		
	//alert(allRows);
	console.log( "UploadProcess allRows" + allRows);
	var totrows= parseInt(result.errJsonList.length);	
	if (parseInt(totrows)>0){		
		for(rowId=0;rowId<=totrows-1;rowId++)	
		{				
			var rowindex=parseInt(allRows.length) + parseInt(rowId)+1;				
			var row=result.errJsonList[rowId];			
			jQuery("#file").jqGrid('addRowData',rowindex,row);
		}
	}
	console.log( "recall UploadProcess " + jQuery("#hdnXml").val());
	if (jQuery("#hdnXml").val()=="1"){
		console.log( "recall UploadProcess ");
		UploadProcess();
	}	
}

function ErrRowsOnErr(result)
{
	//alert("ErrRows"+ result);	
	jQuery("#hdnErrLogProcess").val("0");
}

function file_loadComlete(){
	//alert("file_loadComlete");
	var frmName = jQuery('#hdnformName').val();
	var buttonId = jQuery('#hdnbuttonId').val();
	var row = jQuery("#file").jqGrid('getDataIDs');
	jQuery("#fileCount").html(row.length);	
}

</script>
<form name="frmUploadExcel" id="frmUploadExcel" >
<div id="preLodUploadExcel" class="" style="width:100%;display:none;"></div>
<div >
   <table>
   		<tr>
   		<td colspan="2">
   			<div>
		      
	      	</div>
   		</td>
   		</tr>
	   <tr>
		   <td>	
			   <div class="easyui-paddingbfpx" style="margin-top: 0px;"><label>Description</label></div>
		       <div class="easyui-paddingbfpx">
			       <span>
			       	<textarea id="txtdesc" name="txtdesc"  cols="50" rows="2" maxlength=500></textarea>
			       </span>
				</div>
	       </td>
	       <td>
		       <div style="padding-left:0px;padding-top:60px;" id = "fileUpload"></div>   
		       <div style="margin-top: 0px">
	       		 	<!--<span style="  height:20px;width:75px; position: relative;">
						<input type="file" multiple="" id="btnDownload" name="btnDownload" style="cursor: pointer;   opacity: 0;    position: absolute;height:20px;width:75px;    "  />
						<input id="btnDownload1" class="easyui-button"  type="button" value="Download" style="height:20px;"/>					
					</span>
					-->
					<span style ="padding-left:10px">	
						<input id="btnUploadExcel" class="easyui-button" name="btnUploadExcel"  type="button" value="Upload" style="height:20px;"/>
					</span>
					<span style ="padding-left:10px">	
						<input id="btnDownload" class="easyui-button" name="btnDownload"  type="button" value="Download" style="height:20px;"/>
					</span>	
					<span style ="padding-left:10px">	
				   		<input id="btnSave" class="easyui-button"  type="button" value="Save" style="height:20px;"/>
				   	</span>
					<span style ="padding-left:10px">	
				   		<input id="btnClr" class="easyui-button"  type="button" value="Clear" style="height:20px;"/>
				   	</span>
			   </div>
		   </td>	
	   	</tr>
  	</table>
  	
  	<div style ="padding-right:20px;"> 
	  	<table id="file" ></table>
		<div id="pager"></div></div>
   	</div>  
   	
	<input type="hidden" id="hdnXml" value="0"  />
	<input type="hidden" id="hdnErrLogProcess" value="0"  />
	<input type="hidden" id="hdnbuttonId" value="${requestScope.buttonId}"  />
	<input type="hidden" id="hdnformName" value="${requestScope.formName}"  />	
	<input type="hidden" id="hdnxmlname" value=""  />
</form>