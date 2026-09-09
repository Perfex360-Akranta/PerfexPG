<script type="text/javascript">
var mode=jQuery("#mode").val();
jQuery(document).ready(function(){
	initialiseForm('frmFieldObservModify');
	jQuery('#submitForm').val('frmFieldObservModify');
	var url = jQuery("#hiddenUrl").val();
	var keyid=jQuery('#hdnSusmKeyid').val();
	var userid=jQuery('#hdnuserid').val();
	//alert(userid);	
	var Rolename=jQuery("#hdnRolename").val();
//	alert(Rolename);
	if(Rolename=="SECTION INCHARGE" || Rolename=="SHIFT INCHARGE"){
		
	}
	else{
		alert("Access Denied");
		navigateToPrevForm();
		return false;
	}
	
	
	processGridnew("FieldObservationModify_input.ehsb","?q=2&keyid="+keyid+"&userid="+userid,"FOGrid","pagerFOLog","","susamainDoubleClick","");
});

  function viewGrid(url,filterString){  
   if(validateFilterSelection(filterString)){ 
	filterString +="&mode="+mode;	
	processGridnew(url,filterString,"FOGrid","pagerFOLog","","susamainDoubleClick","");
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
	//////alert(mode);
	var rowData = jQuery("#FOGrid").jqGrid('getRowData',id);	
	var keyId = rowData.FOBM_KEYID;
	var detailsid=rowData.FOBD_KEYID
	var detectedby=rowData.FOBD_DETECTEDBY;
    //alert(detailsid)
    navigateToNextForm("FieldObservation_input.ehsb?q=2&mode="+mode+"&keyId="+keyId+"&detailsid="+detailsid+"&filterButton=false","Modification");
}

</script>
<form id ='frmFieldObservModify'>
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
<table id='FOGrid'>
	<tr>
	<td></td>
	</tr>
</table>
<div id='pagerFOLog'></div>
</div>
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}">
<input type="hidden" id="hdnSusnKeyid" name="hdnSusnKeyid" value="${requestScope.keyId}" />
<input type="hidden" id="hdnuserid" name="hdnuserid" value="${requestScope.userid}" />
<input type="hidden" id="hdnRolename" name="hdnRolename" value="${requestScope.rolename}">
</form>