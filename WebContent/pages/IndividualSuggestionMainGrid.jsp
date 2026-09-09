<script>
jQuery(document).ready(function(){
	var url=jQuery('#hiddenUrl').val();
	setLoadFormCallBackFrmId("frmKaizenBankSuggMainGrid");
	invokeAfterLoadFormCallBack();
	jQuery('#btnNew').click(function(){
		var hsesfty=jQuery('#hdnSftysugstn').val();
		if(hsesfty.trim().length>0)
			var title="Safety Suggestion";
		else
			var title="Kaizen Suggestion";			
		navigateToNextForm("KaizenBankSuggestion_input.kznbnk?q=2&AccSingle=N&filterButton=false"+"&hsesfty="+hsesfty,title);
		var empId=jQuery("#hdnEmpKeyid").val();
	 });
});
function frmKaizenBankSuggMainGrid_afterLoadCallBack(){
	toggleCommonFilter();
	} 

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		
		var hsesfty=jQuery('#hdnSftysugstn').val();
	      if(hsesfty.trim().length>0)
	    	  filterString +='&hsesfty=Y';
		filterString += '&firstClick=Y';	
		processGridnew(url,filterString,"KZBankSuggMainGrid","KZBankSuggMainGridPager","Suggestion","doubleclickKznSugg","","KZBankGridLoadComplete");
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){ 
	return  true;
}
function doubleclickKznSugg(id){
	var dataKeyid=jQuery("#KZBankSuggMainGrid").jqGrid('getCell',id,"KEYID");
	var hsesfty=jQuery('#hdnSftysugstn').val();
	if(hsesfty.trim().length>0)
		var title="Safety Suggestion";
	else
		var title="Kaizen Suggestion";
		navigateToNextForm("KaizenBankSuggestion_input.kznbnk?q=2&AccSingle=N&filterButton=false&Keyid="+dataKeyid+"&hsesfty="+hsesfty,title);
}
</script>
<form id="frmKaizenBankSuggMainGrid" name="frmKaizenBankSuggMainGrid">
	<div id="WrapperRpt">
		<div>
			<table>
				<tr>
					<td>
					<div style="margin-top: -28px">	
						<input type="button"  id="btnNew" name="btnNew" class="easyui-button" value="New Suggestion" style="display:none"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
					<div><label style="font-weight:bold;">Double Click Data to View Details</label></div>
						<div id="KaizenBankSuggMainGrid">
							<div>
								<table id="KZBankSuggMainGrid"></table> 
							</div>
							<div id="KZBankSuggMainGridPager"></div> 
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<input type="hidden" id="hdnSftysugstn" value="${requestScope.Sftysugstn}"/>
<input type="hidden" id="hdnEmpKeyid" name="hdnEmpKeyid" value="${requestScope.EmpKeyid}"/>	
</form>