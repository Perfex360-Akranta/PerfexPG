<script>
jQuery(document).ready(function(){
	initialiseForm('frmfschklistPop');
	jQuery('#submitForm').val('frmfschklistPop');
	var keyid = jQuery("#hdnkeyid").val();
	var flid = jQuery("#hdnflid").val();
	
	processGridnew("FireSaftyInsppopup_input.fsad","keyid="+keyid+"&flid="+flid,"fschklistpopgrid","pager","","docDoubleClick","","ongridloadcomplete");
	
});


function ongridloadcomplete()
{
	
	 var row = jQuery("#fschklistpopgrid").jqGrid('getDataIDs');
	
	 for(var i=1;i<=row.length;i++){		
		
		var keyid = jQuery("#fschklistpopgrid").jqGrid('getCell',i,"txtFsipKeyid"); 
		
		if (keyid != null && keyid.length > 1 )
		{ 
			
			jQuery('#fschklistpopgrid').setSelection(i, true);
			jQuery("#jqg_fschklistpopgrid_"+i).prop('checked', true);
		}
		else
			{jQuery("#jqg_fschklistpopgrid_"+i).prop('checked', false);}
	 }
}
function frmfschklistPop_beforeSubmit()
{   
	var Griddata ="&fschklistdata="+getGridSelectArray('fschklistpopgrid') ;	
	
	return Griddata;
}
function chklistpopgridActionPlan_onClick(result)
{  
	
	var status = getFieldValue('txtFsipStatus_fschklistpopgrid_'+ result.rowId);
	var refDocDtl = jQuery("#fschklistpopgrid").jqGrid('getCell',result.rowId,"txtFsipChlmkeyid"); 
	var refDocId = jQuery("#fschklistpopgrid").jqGrid('getCell',result.rowId,"txtFsipkeyid"); 
	var mom = "FSAD";
	var flid = jQuery("#frmfschklistPop input[id='flid']").val();
    
	if(refDocDtl != null && refDocDtl !=  undefined && status != "Y" )
	{  
		openActionPlan("Actionplane",refDocId,mom,flid);
	} 	
}
function frmfschklistPop_successsCallback(response){
	
	jQuery("#fschklistpopgrid").jqGrid().trigger("reloadGrid");
	//processGridnew("chklistpopup_input.chklst","?keyid="+keyid,"chklistpopgrid","pager","","docDoubleClick","","ongridloadcomplete");
}


</script>
<form id="frmfschklistPop">
	<div>
		<table id='fschklistpopgrid'>
			<tr>
				<td></td>
			</tr>
	</table>
	</div>
<input type="hidden" id="hdnform" value="${requestScope.form}"/>
<input type="hidden" id="hdnflid" value="${requestScope.flid}"/>
<input type="hidden" id="hdnkeyid" value="${requestScope.keyid}"/>
<input type="hidden" id="mode" value="create"/>

</form>