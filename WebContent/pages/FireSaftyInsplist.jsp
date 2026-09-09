<script>
jQuery(document).ready(function(){
	initialiseForm('frmfiresaftyinsplist');
	jQuery('#submitForm').val('frmfiresaftyinsplist');

	 var factId = jQuery("#frmfiresaftyinsplist input[id='factory']").val();
	 var sectionId = jQuery("#frmfiresaftyinsplist input[id='section']").val();
	 var cellId = jQuery("#frmfiresaftyinsplist input[id='cell']").val();
	 var machId = jQuery("#frmfiresaftyinsplist input[id='machine']").val();
	 var flid = jQuery("#frmfiresaftyinsplist input[id='flid']").val();
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
	 
	loadFunctionalLocation("FSADfunLocation","functionalLoc.fsad","fsadfunLocationValues","frmfiresaftyinsplist",dataStr);
	processGridnew("FireSaftyAuditInspectionList_input.fsad","flid="+flid,"fsadinspgrid","pager","","DoubleClick","","ongridloadcomplete");

	jQuery("#btnnew").click(
			function() {	
				var flid = jQuery('#flid').val();					
				LoadPopUp("FireSaftyInsppopup" ,"FireSaftyInsppopup_input.fsad?&flid="+flid,true,"75%","80%","10%","5%","","Fire Safty Inspection popup","",true);				
			});
	
});


function DoubleClick(id)
{  
	var rowData = jQuery("#fsadinspgrid").jqGrid('getRowData',id);
	var keyid =  rowData.Keyid;
	var flid =  jQuery("#flid").val();
	LoadPopUp("FireSaftyInsppopup" ,"FireSaftyInsppopup_input.fsad?&flid="+flid+"&keyid="+keyid,true,"75%","80%","10%","5%","","Fire Safty Inspection popup","",true);
}





</script>
<form id="frmfiresaftyinsplist">
	<div id="wrapper" style="width:80%;">
		<table >
			<tr>
				<td colspan="3">
					<div id="frmFuntKeyIds"  >							
					<input type="hidden" id="factory" name="cmbFSADFactoryid"  value="" ></input>
					<input type="hidden" id="section" name="cmbFSADSectionid"  value=""></input>
					<input type="hidden" id="cell"    name="cmbFSADCellid"     value=""></input>
					<input type="hidden" id="machine" name="cmbFSADMachineid"  value=""></input>
					<input type="hidden" id="flid" name="cmbFSADFlid" disabled="disabled"  value="${requestScope.flid}"></input>							
					</div>						
					<div id="FSADfunLocation" style="width:84.3%;width:82%\9;"></div>
				</td>
				<td style="padding-left:10px;" valign="bottom">
				<div style="padding-left:0%;padding-left:0%\9; ">
					<input id="btnnew" name="btnnew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
				</div>
			</td>	
			</tr>
		
	 </table>
	<div>
		<table id='fsadinspgrid'>
			<tr>
				<td></td>
			</tr>
	</table>
	</div>
</div>
<input type="hidden" id="hdnform" value="${requestScope.form}"/>
<input type="hidden" id="mode" value=""/>
<input type="hidden" id="txtresponsbility" value="${requestScope.responsbility}"/>
</form>