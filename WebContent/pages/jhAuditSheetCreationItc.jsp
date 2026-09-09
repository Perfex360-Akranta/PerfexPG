<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){		
	var url = jQuery('#hiddenUrl').val();
	var dataString ="";
	var jhamAuditpillar=jQuery("#hdnJhamAuditpillar").val();
	var url = jQuery('#hiddenUrl').val();
	var jhamAudittype=jQuery("#hdnJhamAuditType").val();	
	viewGrid(url,"&s=1&jhamAuditpillar="+jhamAuditpillar+"&jhamAudittype="+jhamAudittype);
	jQuery('#btnnewAudit').click(function(event){
		getAuditCreation("jhAuditCreation_input.jhAuditItc?q=2&mode=insert");
	});
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

function getAuditCreation(url){
	var heading="";	
	var jhamAuditpillar=jQuery("#hdnJhamAuditpillar").val();	
	var jhamAudittype=jQuery("#hdnJhamAuditType").val();	
	//if (jhamAudittype=="JH"){
		heading=jhamAudittype + " Audit Creation";//}
	/*else if (jhamAudittype=="DMT"){heading="Dmt Audit Creation";}*/
	navigateToNextForm(url+"&jhamAuditpillar="+jhamAuditpillar+"&jhamAudittype="+jhamAudittype,heading);
}

function validateFilterSelection(filterString){
		return  true;
}
function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#jhAudit").jqGrid('getRowData',rowid );
	
	var heading="";		
	var keyId=rowData.MasterId;
	//alert('keyId:'+keyId);
	getAuditCreation("jhAuditCreation_input.jhAuditItc?keyId="+keyId+"&mode=modify");
	//navigateToNextForm("jhAuditCreation_input.jhAuditItc?keyId=1&mode=view","Jh Audit Creation");
}	

</script>
<form id="frmAuditGrid">
 	<div id="wrapperRpt">
 	<div style="height:20px;margin-top: -28px">
		<input id="btnnewAudit" class="easyui-button" type="button" value="New Audit" style="width:80px;height:21px;margin-top:0px;" />
    	<span> <label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label></span>
	</div>
	<table id="jhAudit" style="width:100%">
		<tr><td/></tr>
	</table>
	<div id="pager"></div>
</div>
 <input type="hidden" id="hdnJhamAuditpillar" name="hdnJhamAuditpillar" value="${requestScope.jhamAuditpillar}"/>
 <input type="hidden" id="hdnJhamAuditType" name=hdnJhamAuditType value="${requestScope.jhamAudittype}"/>
</form>
