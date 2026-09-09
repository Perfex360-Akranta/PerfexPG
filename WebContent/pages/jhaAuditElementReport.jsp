<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script>
	jQuery(document).ready(function(){
		initialiseForm('frmAuditElementUpload');
		jQuery('#submitForm').val('frmAuditElementUpload');
		formatDateBox('dteAurmDate', 'DD-MMM-YYYY');
		fillWithCurrentDate("dteAurmDate");
		fillComboBox("frmAuditElementUpload","cmbAurmPreparedby","employee.commonFilter");
		fillComboBox("frmAuditElementUpload","cmbAurmUploadedby","employee.commonFilter");
		var filterstr="";
		var keyid=jQuery("#hdnAurmKeyid").val();
		if(keyid.length>0){
			filterstr="?q=2&mstKeyid="+keyid;
			jQuery("#btnBrowse").hide();
			jQuery("#divClr").hide();
			jQuery("#divupload").hide();
			jQuery("#divDelete").show();
		}else{
			jQuery("#divDelete").hide();
			jQuery("#btnBrowse").show();
			jQuery("#divClr").show();
			jQuery("#divupload").show();
		}
		var factId = jQuery("#frmAuditElementUpload input[id='factory']").val();
		var sectionId = jQuery("#frmAuditElementUpload input[id='section']").val();
		var cellId = jQuery("#frmAuditElementUpload input[id='cell']").val();
		var machId = jQuery("#frmAuditElementUpload input[id='machine']").val();
		var flid = jQuery("#frmAuditElementUpload input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		loadFunctionalLocation("AuditEleUplfunLocation","functionalLoc.jhAuditItc","AuditEleUplfunLocationValues","frmAuditElementUpload",dataStr);
		processGridnew("jhAuditElementReport_input.jhAuditItc",filterstr,"auditElementRepGrid","auditElementRepPager");
		var uploadedFile = jQuery('#hdnUploadedFile').val();
		if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
		{
			 
		}
		var uploader = new qq.FileUploader({
		    element:   document.getElementById("fileUpload"),
		    name:"Upload",
		    uploadLabelName:"Browse",
		    action: 'file_upload.jhAuditItc',
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
		jQuery("#btnDelete").click(function( ){
			jQuery("#hdnDeleteClick").val("Y");
			deleteRecord("frmAuditElementUpload","jhAuditElementExcel_delete.jhAuditItc?");
		});
		jQuery("#btnClear").click(function( ){
			clearValidationErrorMessages("frmAuditElementUpload","");
			jQuery(".qq-upload-list").html('');
			jQuery("#fileUpload").val("");
		});
		jQuery("#btnUploadExcel").click(function(){	
			//clearValidationErrorMessages("frmAuditElementUpload","");
			var fileName=jQuery(".qq-upload-list").html();
			jQuery("#hdnButtonClick").val("Y");
			var fileSave="N";
			if(fileName.length>0)
				fileSave="Y";
			saveForm("frmAuditElementUpload","file_save.jhAuditItc?fileSave="+fileSave);
		});
	});
	function frmAuditElementUpload_successsCallback(result){
		clearValidationErrorMessages("frmAuditElementUpload","");
		jQuery(".qq-upload-list").html('');
		jQuery("#fileUpload").val("");
		jQuery("#hdnButtonClick").val("N");
		var keyid =jQuery("#hdnAurmKeyid").val();
		if(!keyid.trim().length>0){
			var flid = jQuery("#flid").val();
			loadFunctionalLocation("AuditEleUplfunLocation","functionalLoc.jhAuditItc","AuditEleUplfunLocationValues","frmAuditElementUpload","&flid"+flid);
			jQuery("#auditElementRepGrid").trigger('reloadGrid');
		}
		else
			jQuery("#auditElementRepGrid").setGridParam({url:"jhAuditElementReport_getData.jhAuditItc?q=2&mstKeyid="+keyid}).trigger('reloadGrid');
	}
	function frmAuditElementUpload_beforeDelete(){
		var keyid =jQuery("#hdnAurmKeyid").val();
		if(!keyid.trim().length>0)
			return false;
		else{
			var r=confirm("Are You Sure to Delete?");
			if(r){
				var btnClick=jQuery("#hdnDeleteClick").val();
				if(btnClick.trim()=="Y"){
					var gridData=getGridSelectArray("auditElementRepGrid");
					if(gridData.length>0)
						return "&auditReportdata="+gridData;
					else{
						alert("Select Data to Delete");
						return false;
					}
				}else
					return true;
			}
			else
				return false;
		}
	}
	function frmAuditElementUpload_beforeSubmit(){
		var keyid =jQuery("#hdnAurmKeyid").val();
		var btnClick=jQuery("#hdnButtonClick").val();
		
		if(!(keyid.trim().length>0)){
			if(btnClick.trim()=="N"){
				return false;
			}
		}
		else{
			var gridData=getGridSelectArray("auditElementRepGrid");
			if(gridData.length>0){
				return "&auditReportdata="+gridData;
			}
			else
			{
				alert("Select Data to Update");
				return false;
			}
		}
	}
	function frmAuditElementUpload_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);
		var delVal=result.reload;
		if(delVal.trim()=="Y")
			jQuery("#auditElementRepGrid").trigger('reloadGrid');
		else
			navigateToPrevForm();
	}
</script>
<form id="frmAuditElementUpload">
	<div id="wrapper" style="width:90%;margin-top:-0%">
		<div>
			<table>
				<tr>
					<td colspan="3">
						<div  id="frmAuditElementUploadFuntKeyIds" >
							<div style="float: left;padding-right: 20px;">
								<input type="hidden" id="factory" name="factory"></input>			
								<input type="hidden" id="section" name="section"></input>
								<input type="hidden" id="cell" name="cell"></input>
								<input type="hidden" id="machine" name="machine"></input>
								<input type="hidden" id="flid" name="cmbAurmFlid" value="${requestScope.jhaTlAuditreportmst.aurmFlid}"></input>
							</div>
							<div id="AuditEleUplfunLocation"></div>
						</div>		
					</td>
					<td colspan="2">
						<div id="btnBrowse">
							<div id="fileUpload" style="width:200px;"></div>
							<div id="err_txtAurmUploadedfile" class="tpm-errormsg"></div>
						</div>	
					</td>
					
				</tr>
				<tr>
					<td valign="top" width="14%"  >
						<div>
							<div>
								<label class="mandatory-lbl">Date</label>
							</div>
							<div>
								<input id="dteAurmDate" name="dteAurmDate" class="easyui-datebox" value="${requestScope.jhaTlAuditreportmst.aurmDate}"/>
							</div>
						</div>
					</td>
					<td valign="top" width="24%">
						<div>
							<div>
								<label class="mandatory-lbl">Prepared By</label>
							</div>
							<div>
								<input id="cmbAurmPreparedby" name="cmbAurmPreparedby" class="easyui-combobox" style="width:260px;" value="${requestScope.jhaTlAuditreportmst.aurmPreparedby}"/>
							</div>
						</div>
					</td>
					<td valign="top" width="20%">
						<div>
							<div>
								<label class="mandatory-lbl">Uploaded By</label>
							</div>
							<div>
								<input id="cmbAurmUploadedby" name="cmbAurmUploadedby" class="easyui-combobox" style="width:260px;" value="${requestScope.jhaTlAuditreportmst.aurmUploadedby}"/>
							</div>
						</div>
					</td>
					<td width="8%">
						<div id="divupload" style="margin-left:20px;">	
					  		 <div>
					  		 	<input id="btnUploadExcel" class="easyui-button" name="btnUploadExcel"  type="button" value="Upload" style="height:20px;"/>
				  		 	</div>
					   </div>
					   <div id="divDelete" style="margin-left:20px;">	
					  		 <div>
					  		 	<input id="btnDelete" class="easyui-button" name="btnDelete"  type="button" value="Delete" style="height:20px;"/>
				  		 	</div>
					   </div>
					</td>
					<td width="10%">
						<div id="divClr" style="margin-left:20px;">	
			  				<span style ="padding-left:10px;top:5px">	
			   					<input id="btnClear" class="easyui-button"  type="button" value="Clear" style="height:20px;"/>
						   	</span>
					   </div>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div>
							<table id="auditElementRepGrid"><tr><td></td></tr></table>
							<div id="auditElementRepPager"></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<input type="hidden" id="mode" name="mode" />
			<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value=""/>
			<input type="hidden" id="hdnButtonClick" name="hdnButtonClick" value="N"/>
			<input type="hidden" id="hdnDeleteClick" name="hdnDeleteClick" value="N"/>
			<input type="hidden" id="hdnAurmKeyid" name="hdnAurmKeyid" value="${requestScope.jhaTlAuditreportmst.aurmKeyid}"/>
			<input type="hidden" id="hdnAurmCreatedon" name="hdnAurmCreatedon" value="${requestScope.jhaTlAuditreportmst.aurmCreatedon}"/>
			<input type="hidden" id="hdnAurmUploadedfile" name="hdnAurmUploadedfile" value="${requestScope.jhaTlAuditreportmst.aurmUploadedfile}"/>
		</div>
	</div>
</form>