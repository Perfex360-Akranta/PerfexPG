<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
    	jQuery(document).ready(function(){	
		initialiseForm('frmEmployeeMasterUpload');
		var url = jQuery('#hiddenUrl').val();
		disableUIButton("btnUpload");
		processGridnew(url,"&q=1","EmployeeMstUplGrid","pager","","","","");	
	}); 	
  
    	var uploadedFile = jQuery('#hdnUploadedFile').val();
		if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
		{
			 
		}
		
		var uploader = new qq.FileUploader({
		    element:   document.getElementById("fileUpload"),
		    name:"Upload",
		    uploadLabelName:"Browse",
		    action: 'file_upload.eupl',
			params: {},
			numFiles:1,
			allowedExtensions: [],
			sizeLimit: 65242880, // max size
			minSizeLimit: 1, // min size
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
		
		function viewGrid(filterString){
			
			processGridnew("EmployeeMstUpload_input.eupl",filterString,"EmployeeMstUplGrid","pager","","","","");
			
		}
    
    jQuery("#btnUploadExcel").click(function(){
    	jQuery("#hdnUploadExcel").val("save");
		var fileName=jQuery(".qq-upload-list").html();
		var fileSave="Y";
		if(fileName.length==0){
			
			alert("Select Excel File To Upload");
			return false;
		}
		else{
			
		saveForm("frmEmployeeMasterUpload","file_save.eupl?fileSave="+fileSave);
		}
    });

    
  function frmEmployeeMasterUpload_successsCallback(result){
		processGridnew("EmployeeMstUpload_input.eupl","&q=2","EmployeeMstUplGrid","pager","","","","");

  }  
  
  jQuery("#btnClear").click(function(){
	  jQuery(".qq-upload-list").html("");
	 
  });
  
  jQuery("#btnValidate").click(function(){
      var errflag="Y";   
  	processAjaxCalls("EmpoyeeUplValidateUpdate.eupl","&errflag="+errflag,"Upload_onsuccessCallBack","Upload_onerrorCallBack");  

  });
  
  function Upload_onsuccessCallBack(result){
		var count=result.count;
		if(count>0){
			alert("Validation Errors:"+ count);
			jQuery("#lblValidationmsg").text("Validation Errors:"+count);
			jQuery("#lblValidationmsg").css("font-size","30px");
			jQuery("#lblValidationmsg").css("background","Red");
		//	var row=jQuery("#TemplateUploadGrd").jqGrid('getDataIDs');
		/* 	 for(var i=0;i<=row.length;i++){		 
			 var rowId=parseInt(i);
			 var flag=jQuery("#TemplateUploadGrd").jqGrid('getCell',rowId,"hdnTtupErrflag");
			// alert(flag); 
			
			 if(flag=="Y"){
				//alert("flag");  
				var tableCaption="";
		         var filterString="q=2";
			    jQuery("#TemplateUploadGrd").jqGrid('setCell',rowId,"txtTtupErrmesg","",{'color':'#000','font-size':'12px','background-color':'green'});  
				
			    }
			 
			    } */
		
		      }
		
		
		else{
			//var msg="Validate Success No Error";
			alert("Validation Success No Error");
			jQuery("#lblValidationmsg").text("Validation Success No Error");
			jQuery("#lblValidationmsg").css("font-size","30px");
			jQuery("#lblValidationmsg").css("background","Green");
			enableUIButton("btnUpload");
			disableUIButton("btnValidate");
			disableUIButton("btnRefresh"); 
		}
	}


  
  jQuery("#btnUpload").click(function(){
	   var errorflag="N";
	   
	   var loginId="";
		var jsonArrO="";
		var usrGrdIds = jQuery("#EmployeeMstUplGrid").jqGrid('getDataIDs');	
		var cm = jQuery("#EmployeeMstUplGrid").jqGrid("getGridParam", "colModel");
		for(var i=1;i<=usrGrdIds.length;i++)	
		{	
			rowid=usrGrdIds[i-1];
			
				
				var loginId = jQuery("#EmployeeMstUplGrid").jqGrid('getCell',rowid,"txtEmpuCode");	
				//alert("loginId:::::"+loginId)
				jsonArrO += loginId +";";
					
	
		}				
	   processAjaxCalls("Employee_save.eupl","&errorflag="+errorflag+"&jsonArrO="+jsonArrO,"UploadMain_onsuccessCallBack","UploadMain_onerrorCallBack");  
  });
  
  function UploadMain_onsuccessCallBack(result){
		if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
			popupCommonErrorMsg(result.tpmException);
		 else
			alert(result.successData.msg);
		disableUIButton("btnUpload"); 
		jQuery("#lblValidationmsg").text("");
		processGridnew("EmployeeMstUpload_input.eupl","&q=2","EmployeeMstUplGrid","pager","","","","");
		
  }
  
  jQuery("#btnRefresh").click(function(){
	    var errorflag="";
	  	processAjaxCalls("EmpoyeeUplDelete.eupl","&errorflag="+errorflag,"UploadDelete_onsuccessCallBack","UploadDelete_onerrorCallBack");  
  });
  
  
 function UploadDelete_onsuccessCallBack(result){
	 if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
			popupCommonErrorMsg(result.tpmException);
		 else
			alert(result.successData.msg);
		processGridnew("EmployeeMstUpload_input.eupl","&q=2","EmployeeMstUplGrid","pager","","","","");
 }
   
</script> 
<form id="frmEmployeeMasterUpload" tableindex="0">
<input type="hidden" id="mode" value="create"/>
<input type="hidden" id="htnfilename">
<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value=""/>
<input type="hidden" id="hdnfilename"/>
<div id="" style="margin-left:30px;margin-top:30px">
<div id="fileUpload" style="width:130px;"></div>
<input type="button" id="btnUploadExcel" class="easyui-button"  name="btnUploadExcel" value="Upload Excel" style="width:100px; margin-left:160px; margin-top:-88px;"/>							
<input type="button" id="btnClear" class="easyui-button"  name="btnClear" value="Clear" style="width:80px; margin-left:30px; margin-top:-90px;"/>							
</div> 

	<div style="margin-left:30px;margin-top:-35px;">
	<table id="EmployeeMstUplGrid" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="pager"></div>
	 </div>
	
<div id="" style="margin-left:30px;margin-top:-300px">
<div id="fileUpload" style="width:130px;"></div>

<label id="lblValidationmsg" style="width:70px; margin-left:600px; margin-top:-200px;"></label>
<input type="button" id="btnValidate" class="easyui-button"  name="btnValidate" value="Validate" style="width:70px; margin-left:885px; margin-top:308px;"/>							
<input type="button" id="btnUpload" class="easyui-button"  name="btnUpload" value="Upload" style="width:60px; margin-left:970px; margin-top:-40px;"/>							
<input type="button" id="btnRefresh" class="easyui-button"  name="btnRefresh" value="Refresh" style="width:60px; margin-left:1040px; margin-top:-40px;"/>							

</div> 	
	
	 
</form>

