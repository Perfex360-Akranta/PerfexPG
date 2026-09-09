<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script>
	jQuery(document).ready(function(){
		initialiseForm('frmOtherLossEntry');
		jQuery('#submitForm').val('frmOtherLossEntry');
		formatDateBox('dteOlseDate', 'DD-MMM-YYYY');
		readOnlyFields("dteOlseDate");
		//fillComboBox("frmOtherLossEntry","cmbOlseLossid","loss.commonFilter");
		fillWithCurrentDate("dteOlseDate");
		fillComboBox("frmOtherLossEntry","cmbOlseLossid","loss.commonFilter?LossFrom=PCSLogConfig");
		jQuery('#dteMonth').datebox({
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); },
		 onSelect:function(date){			
				var onSelectFunctionName = 'dteMonth_onSelect';
				if( typeof eval('('+onSelectFunctionName +')') == 'function')
				{
					eval('( '+ onSelectFunctionName +'(date))');
				}
			}
	 }); 
		fillWithCurrentMonth('dteMonth');
		var factId = jQuery("#frmOtherLossEntry input[id='factory']").val();
		var sectionId = jQuery("#frmOtherLossEntry input[id='section']").val();
		var cellId = jQuery("#frmOtherLossEntry input[id='cell']").val();
		var machId = jQuery("#frmOtherLossEntry input[id='machine']").val();
		var flid = jQuery("#frmOtherLossEntry input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		loadFunctionalLocation("LossEntryfunLocation","functionalLoc.pcs","LossEntryfunLocationValues","frmOtherLossEntry",dataStr);
		var date = jQuery("#frmOtherLossEntry input[id='dteOlseDate']").val();
		var month = jQuery("#dteMonth").datebox("getValue");
		processGridnew("OtherLossEntry_input.pcs","?q=2&flid="+flid+"&date="+date+"&month="+month,"otherlossgrid","otherlosspager");

		var uploadedFile = jQuery('#hdnUploadedFile').val();
		if(uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
		{
			 
		}
		/* madhan .pcs to dcm */
		var uploader = new qq.FileUploader({
		    element:   document.getElementById("fileUpload"),
		    name:"Upload",
		    uploadLabelName:"Browse",
		    action: 'file_upload.dcm',
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
		jQuery("#btnClear").click(function( ){
			clearValidationErrorMessages("frmOtherLossEntry","");
			//processAjaxCalls("file_clear.pcs","","sucess");
			jQuery(".qq-upload-list").html('');
			jQuery("#fileUpload").val("");
			
		});
		jQuery("#btnUploadExcel").click(function(){	
			jQuery("#hdnUploadExcel").val("save");
		
			clearValidationErrorMessages("frmOtherLossEntry","");
			var fileName=jQuery(".qq-upload-list").html();
			var fileSave="N";
			var loss=getFieldValue("cmbOlseLossid");
			if(fileName.length>0)
				fileSave="Y";
			if(loss.length>0 && !(fileName.length>0)){
				showValidationErrorMsg("cmbExcelName","* Select File to Upload");
				alert("Select File to Upload");
				return false;
			}
			if(!(loss.length>0) && !(fileName.length>0)){
				showValidationErrorMsg("cmbExcelName","* Select File to Upload");
				showValidationErrorMsg("cmbOlseLossid","* Select Loss");
				alert("Select Loss and File to Upload");
				return false;
			}
			if(!(loss.length>0) && (fileName.length>0)){
				showValidationErrorMsg("cmbOlseLossid","* Select Loss");
				alert("Select Loss");
				return false;
			}
			saveForm("frmOtherLossEntry","file_save.pcs?fileSave="+fileSave);
		});
	});
	function dteMonth_onSelect(date){
		var flid = jQuery("#frmOtherLossEntry input[id='flid']").val();
		var month = jQuery("#dteMonth").datebox("getValue");
		var loss=getFieldValue("cmbOlseLossid");
		loadGridUrl("&flid="+flid+"&month="+month+"&lossId="+loss);
	}
	function frmOtherLossEntry_successsCallback(result){
		clearValidationErrorMessages("frmOtherLossEntry","");
		jQuery(".qq-upload-list").html('');
		jQuery("#fileUpload").val("");
	//	clearField("cmbOlseLossid");
		var flid = jQuery("#frmOtherLossEntry input[id='flid']").val();
		var date = jQuery("#frmOtherLossEntry input[id='dteOlseDate']").val();
		var month = jQuery("#dteMonth").datebox("getValue");
		var lossId = jQuery("#frmOtherLossEntry input[id='cmbOlseLossid']").val();
	//	alert("the lossId " + lossId );
	//	loadGridUrl("&flid="+flid+"&date="+date+"&month="+month);
		
		loadGridUrl("&flid="+flid+"&date="+date+"&lossId="+lossId);
	}
	function loadGridUrl(url){
		url="OtherLossEntry_getData.pcs?q=2"+url;
		jQuery("#otherlossgrid").setGridParam({url:url}).trigger('reloadGrid');
	}
	function frmOtherLossEntrycmbOlseLossid_onSelect(record){
     //   alert("record" + record);
    //   alert("record.id" + record.id);
		clearValidationErrorMessages("frmOtherLossEntry","");
		var flid = jQuery("#frmOtherLossEntry input[id='flid']").val();
		var date = jQuery("#frmOtherLossEntry input[id='dteOlseDate']").val();
		loadGridUrl("&flid="+flid+"&date="+date+"&lossId="+record.id);
	}
	function frmOtherLossEntry_exceptionCallback(){
		jQuery(".qq-upload-list").html('');
		jQuery("#fileUpload").val("");
	}
	function frmOtherLossEntry_beforeSubmit(){
		var uploadExc=jQuery("#hdnUploadExcel").val();
		if(!(uploadExc.length>0)){
			var jsonData =  getGridSelectArray("otherlossgrid");
			var gridData =  '&selectedrowIDs='+jsonData;
			if(jsonData.length<2){
				//alert("Select Data to Update");
				return false;
			}else
				return gridData;
		}else{
			jQuery("#hdnUploadExcel").val("");
			return true;
		}
	}
</script>
<form id="frmOtherLossEntry" name="frmOtherLossEntry">
	<div style="margin-left:3%;">
		<table>
    		<tr>
    			<td colspan="5">
					<div  id="frmOtherLossEntryFuntKeyIds" >
						<div style="float: left;padding-right: 20px;">
							<input type="hidden" id="factory" name="factory"></input>			
							<input type="hidden" id="section" name="section"></input>
							<input type="hidden" id="cell" name="cell"></input>
							<input type="hidden" id="machine" name="machine"></input>
							<input type="hidden" id="flid" name="cmbOlseFlid" value="${requestScope.flid}"></input>
							<input type="hidden" id="flid" name="cmbOlseFlid" value="${requestScope.flid}"></input>
						</div>
						<div id="LossEntryfunLocation"></div>
					</div>		
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<div>
							<label class="mandatory-lbl">Date</label>
						</div>
						<div>
							<input id="dteOlseDate" name="dteOlseDate" class="easyui-datebox" value="${requestScope.date}"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<div>
							<label>Month</label>
						</div>
						<div>
							<input class="easyui-text"id="dteMonth" name="dteMonth" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<div>
							<label class="mandatory-lbl">Loss</label>
						</div>
						<div>
							<input id="cmbOlseLossid" name="cmbOlseLossid" class="easyui-combobox" style="width:260px;"/>
						</div>
					</div>
				</td>
				<td id="divbrowse">
					<div>
						<div id="fileUpload" style="width:200px;"></div>
						<div id="err_cmbExcelName" class="tpm-errormsg"></div>
					</div>	
				</td>
				<td>
					<div id="divupload" style="margin-left:20px;">	
				  		 <div>
				  		 	<input id="btnUploadExcel" class="easyui-button" name="btnUploadExcel"  type="button" value="Upload" style="height:20px;"/>
			  		 	</div>
				   </div>
				</td>
				<td>
					<div id="divClr" style="margin-left:20px;">	
		  				<span style ="padding-left:10px;top:5px">	
		   					<input id="btnClear" class="easyui-button"  type="button" value="Clear" style="height:20px;"/>
					   	</span>
				   </div>
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<div>
					 	<table id='otherlossgrid'><tr><td></td></tr></table>
					 	<div id='otherlosspager'></div>
			 	    </div>
				</td>
			</tr>
		</table>
		<div>
			<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value=""/>
			<input type="hidden" id="hdnUploadExcel" name="hdnUploadExcel" value=""/>
			<input type="hidden" id="mode" name="mode" />
		</div>
	</div>
</form>