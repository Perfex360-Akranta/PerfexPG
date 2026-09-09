<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">	

jQuery.noConflict();

jQuery(document).ready(function(){	
	jQuery('#submitForm').val('docAdvSearch'); 
	initialiseForm("docAdvSearch");
	fillComboBox("docAdvSearch","cmbType","type_combo.dcm");
	fillComboBox("docAdvSearch","cmbSubjectarea","subjectArea_combo.dcm");
	fillComboBox("docAdvSearch","cmbCategory","category_combo.dcm");
	fillComboBox("docAdvSearch","cmbOwner","employee.commonFilter");
	fillComboBox("docAdvSearch","cmbApprovedby","employee.commonFilter");

	  
	jQuery('#docAdvSearch .easyui-text').css('text-transform', 'uppercase');
    jQuery('#docAdvSearch textarea').css('text-transform', 'uppercase');	   
	var uploadedFile = jQuery('#hdnUploadedFile').val();

	if(uploadedFile=="undefined" ||uploadedFile=='undefined'||uploadedFile == null || uploadedFile =='' || uploadedFile == ' ')
		 jQuery('#lblChanges').removeClass('mandatory-lbl');
	else
		 jQuery('#lblChanges').addClass('mandatory-lbl');
   
	formatDateBox('dteFromDate','dd-MMM-yyyy');
	formatDateBox('dteToDate','dd-MMM-yyyy');
	

});

jQuery("#btnSearch").click(function(){
	 advSearchFile();	
	 closePopUpDialoge('divAdvSearch');
	
});
jQuery("#btnTypeKeywords").click(function(){
	 var typeId = jQuery("#cmbType").combobox('getValue');	 
	 if(typeId != null && typeId != '' && typeId != ' ')
	 {
		 var dataStr = "?q=2&typeId="+typeId+"&showSearch=Y";	
		 LoadPopUp("divKeywordValues","docTempTypeVal_view.dcm"+dataStr, false,"68%","69%","0px","5%", "docTempKeyVal_Callback","Keywords");		 
	 }
	 else
		 alert('Select Type');
});
function docTempKeyVal_Callback(args)
{
	
}
function docAdvSearchcmbSubjectarea_onLoadSuccess(){


}
function advSearchFile()
{

	var keywords = jQuery( "#txtKeywords").val();
		
	var viewsMode = jQuery('#hdnViewsMode').val();
	var dataStr = '?q=2';

	if(viewsMode != null && viewsMode != '' && viewsMode != ' ')
		 dataStr += '&viewsMode='+viewsMode;
	
	if(keywords != null && keywords != '' && keywords != ' ')
		dataStr += '&keywords='+keywords;

	

	dataStr += '&title='+jQuery('#txtTitle').val();
	dataStr += '&category='+jQuery('#cmbCategory').combobox('getValue');
	dataStr += '&subjectArea='+jQuery('#cmbSubjectarea').combobox('getValue');
	dataStr += '&owner='+jQuery('#cmbOwner').combobox('getValue');
	dataStr += '&changes='+jQuery('#txtChanges').val();

	dataStr += '&description='+jQuery('#txtDescription').val();
	dataStr += '&approvedBy='+jQuery('#cmbApprovedby').combobox('getValue');
	dataStr += '&type='+jQuery('#cmbType').combobox('getValue');

	dataStr += '&fromDate='+jQuery('#dteFromDate').datebox('getValue');
	dataStr += '&toDate='+jQuery('#dteToDate').datebox('getValue');
		
	processAjaxCalls('getContent.dcm',dataStr,'getFiles','getFilesErr');
	
}
jQuery("#btnCancelSearch").click(function(){	
	closePopUpDialoge('divAdvSearch');
});

</script>
<form id="docAdvSearch" name="docAdvSearch">
<div style="padding-left: 17px">
	<table style="padding-top: 20px;">
	<tr>
		<td>		
			<div  style="padding-left:10px;">
				<label>Title</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="txtTitle" name="txtTitle" class="easyui-text" style="width:260px;" maxlength="20" value="${requestScope.dcmTlDocumentmanager.dmdmTitle}"/ >                       
			</div>
			
			<div  style="padding-left:10px;">
				<label>Subject Area</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbSubjectarea" name="cmbSubjectarea" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmSubjectarea}"/ >                       
			</div>
			
			<div   style="padding-left:10px;">
				<label >Keywords</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<textarea id="txtKeywords" name="txtKeywords"  style="resize:none;width:260px;height:70px;">${requestScope.dcmTlDocumentmanager.dmdmKeywords}</textarea>		                      
			</div>
			<div  style="padding-left:10px;">
				<label>Description</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<textarea id="txtDescription" name="txtDescription"  style="resize:none;width:260px;height:70px;">${requestScope.dcmTlDocumentmanager.dmdmDescription}</textarea>                       
			</div>

			
			
		</td>
		
		<td valign="top">
			<div style="padding-left:10px;margin-top:-2;">
				<label>Type</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbType" name="cmbType" class="easyui-combobox" style="width:230px;" value="${requestScope.dcmTlDocumentmanager.dmdmType}"/ >
				<input id="btnTypeKeywords" class="easyui-button" name="btnTypeKeywords"  type="button" value="..." style="height:20px;height:23px\9;"  />                       
			</div>
			<div style="padding-left:10px;">
				<label>Category</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbCategory" name="cmbCategory" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmCategory}"/ >                       
			</div>
			<div style="padding-left:10px;">
				<label>Owner</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbOwner" name="cmbOwner" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmOwner}"/ >                       
			</div>
		
			<div  style="padding-left:10px;">
				<label>Approved By</label>                       
			</div> 
			<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
				<input id="cmbApprovedby" name="cmbApprovedby" class="easyui-combobox" style="width:260px;" value="${requestScope.dcmTlDocumentmanager.dmdmApprovedby}"/ >                       
			</div>		
			<div  style="padding-left:10px; padding-top: 2px">
				<label>From Date</label>
				<span style="padding-left: 50px;">                       
					<label>To Date</label>
				</span>
			</div> 
			<div  style="padding-left:10px;">
				<span style="padding-left: px;">
		    		<input id="dteFromDate" name="dteFromDate" class="easyui-text" style="width: 100px;" value="${requestScope.fromDate}" />
		    	</span>
				<span style="padding-left: 15px;">
		    		<input id="dteToDate" name="dteToDate" class="easyui-text" style="width: 100px;" value="${requestScope.fromDate}" />
		    	</span>
			</div>    		
		
		</td>
	</tr>
	</table>
	
	<div class="easyui-paddingbfpx" style="padding-left:40%;padding-top: 30px;"> 
		<input id="btnSearch" class="easyui-button" name="btnSearch"  type="button" value="Search" style="height:20px;"  />
		<input id="btnCancelSearch" class="easyui-button" name="btnCancelSearch"  type="button" value="Cancel" style="height:20px;"  />
	</div>
	
</div>
</form>