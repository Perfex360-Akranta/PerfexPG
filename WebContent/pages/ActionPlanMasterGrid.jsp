
<script>


jQuery(document).ready(function()
{
	var url = jQuery('#hiddenUrl').val();
	dataString="q=2";
	viewGrid(url,dataString);	
	jQuery ("#btnNEW").click(function(){
		navigateToNextForm("ActionPlan_input.api?grid=false&taskid=main","Action Plan");
	});
});
function viewGrid(url,dataString)
{
	processGridnew(url,dataString,"ActionPlanGrid","pager"," ","docDoubleClick");
}


function docDoubleClick(id)
{	
	var rowData = jQuery("#ActionPlanGrid").jqGrid('getRowData',id);
	var keyId=rowData.APLM_KEYID;
	navigateToNextForm("ActionPlan_input.api?grid=true&taskid=main&actPlanKeyId="+keyId );	
}

</script>
<form>
    <div id="wrapperRpt" >
   	<div style="margin-top: -28px"> <input class="easyui-button" type="button" id="btnNEW" name="btnNew" value="New" style=" width : 49px;height:22px;"></div>
		<table id='ActionPlanGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>	
	</div>
	<input type="hidden" id="hdnFilterStr" value=""/>	
</form>