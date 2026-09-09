<script type="text/javascript">
var mode=jQuery("#mode").val();
jQuery(document).ready(function(){
	initialiseForm('frmQCLoggingModify');
	jQuery('#submitForm').val('frmQCLoggingModify');
	var url = jQuery("#hiddenUrl").val();
	var keyid=jQuery('#hdnSusmKeyid').val();
	var userid=jQuery('#hdnuserid').val();
    
	var rolename=jQuery("#hdnUsername").val();
//	alert(rolename);
	
	if(rolename=="QUALITY CONTROL"){
	   		
	}
	else{
		alert("Access Denied");
		navigateToPrevForm();
		return false;
	}

	processGridnew("QCLoggingModify_input.ehsb","?q=2&keyid="+keyid+"&userid="+userid,"QCGrid","pagerQcLog","","susamainDoubleClick","");
});




  function viewGrid(url,filterString){  
   if(validateFilterSelection(filterString)){ 
	filterString +="&mode="+mode;	
	processGridnew(url,filterString,"QCGrid","pagerQcLog","","susamainDoubleClick","");
	return true;
}
  return false;	
}
 
function validateFilterSelection(filterString){
	return true;
}  
function susamainDoubleClick(id)
{	
	var mode=jQuery("#hdnmode").val();
	////alert(mode);
	var rowData = jQuery("#QCGrid").jqGrid('getRowData',id);	
	var keyId = rowData.QCLM_KEYID;
    navigateToNextForm("QCLogging_input.ehsb?q=2&mode="+mode+"&keyId="+keyId+"&filterButton=false","QCLogging Modification");
}

</script>
<form id ='frmQCLoggingModify'>
<div id='wrapperRpt' >
<span><label class="notes" style="font-weight: bold"> Double Click on row to input/view details </label></span>
<table >
<tr>
	<td>
	<div >
	</div>
	</td>
	</tr>
</table>
<table id='QCGrid'>
	<tr>
	<td></td>
	</tr>
</table>
<div id='pagerQcLog'></div>
</div>
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}">
<input type="hidden" id="hdnSusnKeyid" name="hdnSusnKeyid" value="${requestScope.keyId}" />
<input type="hidden" id="hdnuserid" name="hdnuserid" value="${requestScope.userid}" />
<input type="hidden" id="hdnUsername" name="hdnUsername" value="${requestScope.rolename}"/>
</form>