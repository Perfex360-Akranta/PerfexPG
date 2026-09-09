<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
/* 	jQuery('#btnnewAudit').click(function(event){
		getAuditCreation("jhAuditCreation_input.jhAuditItc?q=2&mode=insert");
	}); */
	viewGrid("","");
});

function frmAuditUploadGrid_afterLoadCallBack(){	
	toggleCommonFilter();		
}
function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{		 
		var jhamAuditpillar=jQuery("#hdnJhamAuditpillar").val();
		var url = jQuery('#hiddenUrl').val();
		var jhamAudittype=jQuery("#hdnJhamAuditType").val();
		filterString+="&jhamAuditpillar="+jhamAuditpillar+"&jhamAudittype="+jhamAudittype;
		//alert("url:"+url+",filterString:"+filterString);
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
		heading=jhamAudittype + "  Audit Upload Creation";
	navigateToNextForm(url+"&filterButton=false&jhamAuditpillar="+jhamAuditpillar+"&jhamAudittype="+jhamAudittype,heading);
}

function validateFilterSelection(filterString){
	return  true;
}


function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#jhAudit").jqGrid('getRowData',rowid );	
	var heading="";		
	var keyId=rowData.MasterId;
	var status=rowData.Status;
	//alert('keyId:'+keyId);
	getAuditCreation("jhdmtAuditUpload_input.jhdmtauditupd?keyId="+keyId+"&status="+status+"&mode=modify");
	//navigateToNextForm("jhAuditCreation_input.jhAuditItc?keyId=1&mode=view","Jh Audit Creation");
}	

</script>
<form id="frmAuditUploadGrid">
 	<div id="wrapperRpt">
 	<div style="height:20px;margin-top: -28px">
		
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
