<script>

jQuery(document).ready(function() {

	initialiseForm('frmEmppillar');
	jQuery('#submitForm').val('frmEmppillar');
    var url = jQuery('#hiddenUrl').val();
    //alert(" url :: "+url);
    var emppillar=jQuery('#hdnEmPillar').val();
    //alert(" emppillar :: "+emppillar);
	viewGrid(url,"&q=2");

});

function doubleClickGrid(id)
{
	var forwardData = jQuery('#hiddenUrl').val();

	var url = jQuery("#Emppillargrid").jqGrid('getGridParam', 'url');

	url = url.replace('getData','input');
	url = escape(url);

	var rowData = jQuery("#Emppillargrid").jqGrid('getRowData',id);
	var keyId = rowData.TXTOPLNO;
	
	// Get the user's role ID from the hidden field
	var userRoleId = jQuery('#hdnUserRoleId').val();
	
	// If roleId is not available, set it as empty string
	if(!userRoleId || userRoleId.trim() === '') {
		userRoleId = '';
	}
	
	// Pass roleId as parameter to create_input.opl
	navigateToNextForm("create_input.opl?oplKeyId="+keyId+"&mod=view&Emppillar=Y&filterButton=false&roleId="+userRoleId,"EM Pillar Opl",null,{"filterString":url});
}

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		filterString+='&chkMPWorthy=Y&Emppillar=Emppillar';
		processGridnew(url,filterString,"Emppillargrid","Emppillarpager","","doubleClickGrid");
		return true;
	}
	return false;
}

function validateFilterSelection(filterString)
{
	if( ! checkFilterValueExist(filterString,"cmbCompid"))
	{

	}
	return true;
}

</script>

<form id="frmEmppillar">

<div id='WrapperRpt'>
<table id='Emppillargrid'><tr><td></td></tr></table>
<div id='Emppillarpager'></div>
</div>

<input type="hidden" id="hdnEmPillar" name="hdnEmPillar" value="${requestScope.EmPillar}" />

<!-- ADD THIS: Hidden field for user's role ID from session -->
<input type="hidden" id="hdnUserRoleId" name="hdnUserRoleId" value="${sessionScope.roleId}" />

</form>