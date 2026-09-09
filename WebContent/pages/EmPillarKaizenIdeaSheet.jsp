<script>
jQuery(document).ready(function(){
		initialiseForm('frmEmpillar');
		jQuery('#submitForm').val('frmEmpillar');
		var url = jQuery('#hiddenUrl').val();
		//alert(" url :: Kaizen"+url);
		viewGrid(url,"&q=2&chkMPWorthy=Y");
});
function doubleClick(id){
	//alert(id);
	var rowdata=jQuery('#empillargrid').getCell(id,"IMPRVNO");
	var status=jQuery('#empillargrid').getCell(id,"STATUS");
	var ThemeCat=jQuery("#empillargrid").getCell(id,'THEMECATEGORYKEYID');
    var themeName=jQuery("#empillargrid").getCell(id,"THEME");
    var benFitArea=jQuery("#empillargrid").getCell(id,"RESULTAREA");
	//alert(keyId +"Keyid");
	//alert(ThemeCat +"ThemeCat");
	//alert(benFitArea +"benFitArea");
	//alert(themeName +"themeName");
	
	navigateToNextForm("kaizen_input.kaizen?&kznKeyid="+rowdata+"&kznStatus="+status+"&benFitArea="+benFitArea+"&themeName="+themeName+"&mode=view&filterButton=false&EMPILLAR=Y","Kaizen Idea Sheet");
	
}
function viewGrid(url,filterString)
{

	
	if( validateFilterSelection(filterString))
	{
		filterString+='&chkMPWorthy=Y&Emppillar=Emppillar';
		//processGridnew(url,filterString,"pcsLossCstGrid","pcsLossCstGridpager",tableCaption,"doubleClickGrid","","lostCost_LoadComplete");
		processGridnew(url,filterString,"empillargrid", "empager","","doubleClick","","ongridcomplete");
		return true;
	}
	return false;
		
}
function validateFilterSelection(filterString)
{
	if( ! checkFilterValueExist(filterString,"cmbCompid"))
	{
		/*if(filterString==null || filterString==''||filterString=="")
		{}
		else
		{
			alert("Select Functional Location");
			return false;
		}*/
	}
	return true;
}

</script>
<form name="frmEmpillar"  id="frmEmpillar">
	<div id="wrapperRpt">
		<table>
			<!--<tr>
				<td>
					<div style="padding-left:10px;margin-top: -23px" >
						<label class="notes" >Double Click on row to view details</label>
					</div>
				</td>
			</tr>
			-->
			<tr>
				<td>
					<table  id='empillargrid' ><tr><td></td></tr></table>
					<div id='empager'></div>
				</td>
			</tr>
		</table>
	</div>
	
	<input type="hidden" id="hdnEmPillar" name="hdnEmPillar" value="${requestScope.EmPillar}" />
	
</form>
		