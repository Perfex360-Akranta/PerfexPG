<script>
jQuery(document).ready(function(){
	initialiseForm('frmfiresaftyinsp');
	jQuery('#submitForm').val('frmfiresaftyinsp');

	 var factId = jQuery("#frmfiresaftyinsp input[id='factory']").val();
	 var sectionId = jQuery("#frmfiresaftyinsp input[id='section']").val();
	 var cellId = jQuery("#frmfiresaftyinsp input[id='cell']").val();
	 var machId = jQuery("#frmfiresaftyinsp input[id='machine']").val();
	 var flid = jQuery("#frmfiresaftyinsp input[id='flid']").val();
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
	 
	loadFunctionalLocation("FSADfunLocation","functionalLoc.fsad","fsadfunLocationValues","frmfiresaftyinsp",dataStr);
	processGridnew("FireSaftyAuditInspection_input.fsad","q=2","fsinspgrid","pager","","DoubleClick","","ongridloadcomplete");

	jQuery("#btnview").click(
			function() {	
				var flid = jQuery('#flid').val();		
				processGridnew("FireSaftyAuditInspectionList_input.fsad?flid="+flid,"","fsinspgrid","pager","","DoubleClick","","ongridloadcomplete");
			});
	
});


function DoubleClick()
{	
	var flid = jQuery('#flid').val();
	navigateToNextForm("FireSaftyAuditInspectionList_input.fsad?&flid="+flid,"Fire Safty Inspection popup");
}





</script>
<form id="frmfiresaftyinsp">
	<div id="wrapper" style="width:80%;">
		<table >
			<tr>
				<td colspan="3">
					<div id="frmFuntKeyIds"  >							
					<input type="hidden" id="factory" name="cmbFSADFactoryid"  value="" ></input>
					<input type="hidden" id="section" name="cmbFSADSectionid"  value=""></input>
					<input type="hidden" id="cell"    name="cmbFSADCellid"     value=""></input>
					<input type="hidden" id="machine" name="cmbFSADMachineid"  value=""></input>
					<input type="hidden" id="flid" name="cmbFSADFlid"  value="${requestScope.Flid}"></input>							
					</div>						
					<div id="FSADfunLocation" style="width:84.3%;width:82%\9;"></div>
				</td>
				<td style="padding-left:10px;" valign="bottom">
				<div style="padding-left:0%;padding-left:0%\9; ">
					<input id="btnview" name="btnview" class="easyui-button"  type="button" value="view" style="width:70px; height:24px;"/>
				</div>
			</td>	
			</tr>
		
	 </table>
	<div>
		<table id='fsinspgrid'>
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