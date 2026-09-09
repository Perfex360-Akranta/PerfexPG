<script>
var url = jQuery('#hiddenUrl').val();
jQuery('#submitForm').val('frmDocumentTemplate');
initialiseForm('frmDocumentTemplate');	
viewGrid(url,"?q=2");
function viewGrid(url,filterString)
{		
	processGridnew(url,filterString,"DocumentTemplate","DocumentTemplatepager","","DocTempDoubeClick");			
	return true;	
}
function DocTempDoubeClick(id){
	
	var rowData = jQuery("#DocumentTemplate").jqGrid('getRowData',id);	
	var keyId = rowData.DTPM_KEYID;
	
	 var formTitle;
	 formTitle = "Document Template";
	 navigateToNextForm("DocumentTemplate_input.dcm?&grid=true&keyId="+keyId ,formTitle);
}
</script>
<form id="frmDocumentTemplate">

 <div id="wrapperRpt">
 	
		<table id="DocumentTemplate" ></table>
			<div id="DocumentTemplatepager"></div>

	</div>

</form>