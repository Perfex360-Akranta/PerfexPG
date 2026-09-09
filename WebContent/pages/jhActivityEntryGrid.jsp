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
		processGridnew(url,filterString,"jhActivityEntryGrid","jhActivityEntryGridPager","Jh Activity","doubleJhAct","","jhActComplete");
		return true;
	}
	return false;	
}
function validateFilterSelection(filterString){ 
	return  true;
}
function doubleJhAct(id){
	var dataKeyid=jQuery("#jhActivityEntryGrid").jqGrid('getCell',id,"MSTKEYID");
	var title="JH Activity Entry";
	navigateToNextForm("jhActEntryFrm_input.dashboard?q=2&MSTKEYID="+dataKeyid,title);
}
</script>
<form id="frmjhActivityEntryGrid" name="frmjhActivityEntryGrid">
	<div>
		<div id="WrapperRpt">
			<table>
				<tr>
					<td>
						<div>
							<div>
								<table id="jhActivityEntryGrid"></table> 
							</div>
							<div id="jhActivityEntryGridPager"></div> 
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<input type="hidden"  id="hdnjhFrm" name="hdnjhFrm" value="${requestScope.jhFrm}"/>
</form>