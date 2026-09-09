<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){		
	var url = jQuery('#hiddenUrl').val();
	var dataString ="";
	var auditType=jQuery("#hdnAuditType").val();
	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"&s=1");
	//viewGrid(url,"");
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{			
		processGridnew(url,filterString,"jhAudit","pager","","doubleClickGrid","","");
		return true;
	}
	return false;
}

function frmFilter_enableDisableSuccessCallBack()
{
	fillWithCurrentMonth('dtetoMonth');
	disableField('frmFilter','dtetoMonth');
}

function validateFilterSelection(filterString){
		return  true;
}
function doubleClickGrid(rowid) 
{
	/*var rowData = jQuery("#jhAudit").jqGrid('getRowData',rowid );
	var heading="";		
	getAuditCreation("jhAuditCreation_input.jhAuditItc?keyId=1&mode=view");*/
	//navigateToNextForm("jhAuditCreation_input.jhAuditItc?keyId=1&mode=view","Jh Audit Creation");
}	
	
</script>
<form id="frmAuditGrid">
 <div id="wrapperRpt">
 	<div style="width:100%;margin-left:4%;">
		<table id="jhAudit" style="width:100%">
		<tr><td/></tr></table>
		<div id="pager"></div>
	</div>
</div>
<input type="hidden" id="hdnAuditType" name="hdnAuditType" value="${requestScope.auditType}"/>
</form>
