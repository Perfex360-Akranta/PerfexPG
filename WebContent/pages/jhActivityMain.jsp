<script>
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	 viewGrid(url,"?q=2");
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		filterString += '&firstClick=Y';		
		processGridnew(url,filterString,"jhActivityGrid","jhActivityGridPager","Jh Activity","doubleJhAct","","jhActComplete");
		return true;
	}
	return false;	
}
function validateFilterSelection(filterString){ 
	return  true;
}
function doubleJhAct(id){
	var jhFrm=jQuery("#hdnjhFrm").val();
	if(jhFrm=="entry"){
		var dataKeyid=jQuery("#jhActivityGrid").jqGrid('getCell',id,"MSTKEYID");
		var title="JH Activity Entry";
		navigateToNextForm("jhActEntryFrm_input.dashboard?q=2&MSTKEYID="+dataKeyid,title);
	}
}
</script>
<form id="frmjhActivityGrid" name="frmjhActivityGrid">
	<div>
		<div id="WrapperRpt">
			<table>
				<tr>
					<td>
						<div>
							<div>
								<table id="jhActivityGrid"></table> 
							</div>
							<div id="jhActivityGridPager"></div> 
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden"  id="hdnjhFrm" name="hdnjhFrm" value="${requestScope.jhFrm}"/>
</form>