<script>
jQuery(document).ready(function(){
	
	initialiseForm('frmsaftytalkMst');
	jQuery('#submitForm').val('frmsaftytalkMst');	
	fnviewgrid();

	jQuery("#btnNew").click(function(){		
		navigateToNextForm("SafetyTalk_input.sftlk?keyid=");
	});
	
});


function fnviewgrid()
{
	  //var keyid = getFieldValue('hdnKeyid','frmsaftytalkMst');	 
	  processGridnew("SafetyTalkMst_input.sftlk","&q=2&keyid=","saftytalkMstGrid", "pager", "SafetyTalkMst", "fndoubleClick","","");
	  //void processGridnew(any url, any filterString, any tableId, any pagerId, any tableCaption, any   doubleClickFunction, any tableHeaderSpanCallback, any ongridcompletecallback, any selectRowFunction, any filterNeed)
}

function fndoubleClick(id)
{	
	var rowData = jQuery("#saftytalkMstGrid").jqGrid('getRowData',id);		
	var Keyid = rowData.keyid;	
	var Date=rowData.SafetyTalkDate;
	var Preparadby=rowData.Preparadby;
	var PreparadId=rowData.PreparadId;
	//alert(" PreparadId :: "+PreparadId);
	//alert(" :: Preparadby :: "+Preparadby);
	//alert(Date);
	//setFieldValue('hdnKeyid',Keyid,'frmsaftytalkMst');
	navigateToNextForm("SafetyTalk_input.sftlk?keyid="+Keyid+"&Date="+Date+"&Preparadby="+Preparadby+"&PreparadId="+Preparadby);
	
}
function fnclear(){	
	
	setFieldValue('hdnsftlKeyid','','frmsaftytalkMst');
	setFieldValue('cmbsftlPrepardby','','frmsaftytalkMst');
	setFieldValue('dtesftlDate','','frmsaftytalkMst');
	setFieldValue('txtsftlTalk','');
	
}

</script>
<form id="frmsaftytalkMst" name="frmsaftytalkMst">
<div id='wrapper'>
	<div style="width:79%; padding-top:0px;">
	<div>
		<input type="button" class="easyui-button" id="btnNew"  name="btnNew" style="width:60px;height:21px;" value="New"/>
	</div>
			<table id='saftytalkMstGrid'>
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager'></div>
		</div>
</div>
	
	<input type="hidden" id="hdnKeyid" name="hdnKeyid" value="${requestScope.Keyid}" />
	<input type="hidden" id="mode" name="mode"/>
</form>