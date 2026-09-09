<html>
<head>
<script>
jQuery(document).ready(function(){
	 var KzbnKeyid=jQuery('#hdnkzbnKeyid').val();
	 var fromMode=jQuery('#hdnfromMode').val();
	 
	initialiseForm('frmSafetySuggestionGrid');
	jQuery('#submitForm').val('frmSafetySuggestionGrid');
	var filterString="q=2";
	 viewGrid("SafetySuggestionGrid_input.kznbnk",filterString);
	
});

function safetySuggGrid_onSelect(id) {

}


function viewGrid(url,filterString)
{		
	 var KzbnKeyid=jQuery('#hdnkzbnKeyid').val();
	 var fromMode=jQuery('#hdnfromMode').val();
	 
	 filterString+="&fromMode="+fromMode+"&KzbnKeyid="+KzbnKeyid;
	 
	if( validateFilterSelection(filterString))
	{
		filterString += '&drillFlag=f&firstClick=Y';
		processGridnew(url ,filterString,"safetySuggGrid","safetySuggPager","","docDoubleClick","","loadComplete");
		
		return true;
	}
	return false;
}

function safetySuggGridbtnActPln_onClick(result){	
	 
	 var safetysuggtion = jQuery("#safetySuggGrid").jqGrid('getCell',result.rowId,"KAIZEN");
	 var date = jQuery("#safetySuggGrid").jqGrid('getCell',result.rowId,"targetDate");
	 var keyidId = jQuery("#safetySuggGrid").jqGrid('getCell',result.rowId,"txtKzbnKeyid");
	 var flid = jQuery("#safetySuggGrid").jqGrid('getCell',result.rowId,"FNLNID");
	 
     openActionPlan("safetytalkActionplan",keyidId,"SFTS",flid,safetysuggtion,keyidId);
     
}

function safetytalkActionplan_onClose(){
	jQuery("#safetySuggGrid").trigger('reloadGrid');
	return true;
}

function loadComplete(){
	var allRows = jQuery("#safetySuggGrid").jqGrid('getDataIDs');
	for(var i=0; i<allRows.length;i++){
		var parentId = jQuery("#safetySuggGrid").jqGrid('getCell', allRows[i], 'ACCEPTANCE');
		var status=jQuery("#safetySuggGrid").jqGrid('getCell', allRows[i], 'ACTIONPLANSTATUS');
		//alert(status.trim().length);
		if(parentId.toLowerCase().indexOf("not") >= 0){
			jQuery('#jqg_safetySuggGrid_'+allRows[i]).attr({'disabled':true});
		}
		if(status.trim().length<=0 ){
			jQuery('#safetySuggGrid_cmbEhsstatus'+allRows[i]).attr({'disabled':true});
		}
	}
}
function validateFilterSelection(filterString){
		return true;
}
function docDoubleClick(id){
	var rowData = jQuery("#safetySuggGrid").jqGrid('getRowData',id);
}
function frmSafetySuggestionGrid_beforeSubmit(){
	
	
	var allRows = jQuery("#safetySuggGrid").jqGrid('getDataIDs');
	var gridData=getGridSelectArray("safetySuggGrid");
	for(var i=0; i<allRows.length;i++){
		if(jQuery('#jqg_safetySuggGrid_'+allRows[i]).is(':checked') == true){	
		var status=jQuery("#safetySuggGrid").jqGrid('getCell', allRows[i], 'ACTIONPLANSTATUS');
		if(status.trim().length<=0 || status!='Completed' ){
			popupCommonErrorMsg('Action plan status is Pending.');
			return false;
		}
	}
	}
		if(gridData.length>0)
			return "&dataStatus="+gridData;
		else
		{
			return false;
		}
	
	}
	
	
//	}
function frmSafetySuggestionGrid_successsCallback()
{
	jQuery("#safetySuggGrid").trigger("reloadGrid");
}
</script>
</head>
<body>
<form id="frmSafetySuggestionGrid" >
	<div id='wrapperRpt' style="width:100%">
		<table>
			<tr>
				<td>
					<table id='safetySuggGrid'>
						<tr>
							<td></td>
						</tr>
					</table>
					<div id='safetySuggPager'></div>
				</td>
			</tr>
		</table>
	</div>
	
	<input type="hidden" id="mode" name="mode"/>
	<input type="hidden" id="hdnkzbnKeyid" name="hdnkzbnKeyid" value="${requestScope.kzbnKeyid}"/>
	<input type="hidden" id="hdnfromMode" name="hdnfromMode" value="${requestScope.fromMode}"/>
</form>

