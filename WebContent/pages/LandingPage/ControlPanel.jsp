<style type="text/css">
	.btnPadding{
		padding:5px
	}
</style>
		<script src="js/pcsEntry.js" type="text/javascript"></script>
<script>
	jQuery(document).ready(function(){
		//var url="pcsViewTest_input.pcs?q=2&date=05-Nov-2013&shift=SFT001&mchId=&cellId=CEL014&sectId=LIN003&factId=FCT001&mode=create";
	   // LoadForm("pcsEntryPage","preloadDIVid1",url,"dispErr",url,"navigateToNext_ErrorCalBack");
		var pcsurl ="pcsView_input.pcs?q=2&date=12-Nov-2013&shift=SFT001&mchId=MCH0002061&cellId=CEL0000066&sectId=LIN0000026&factId=FCT0000012&mode=create"
		var date = getFieldValue('dteEmpmdate', "frmEmpPage");
		var shift=  getFieldValue('cmbLPShiftid', "frmEmpPage");
		var factId = jQuery("#frmEmpPage input[id='factory']").val();
		var sectionId = jQuery("#frmEmpPage input[id='section']").val();
		var cellId = jQuery("#frmEmpPage input[id='cell']").val();
		var machId = jQuery("#frmEmpPage input[id='machine']").val();
		var flid = jQuery("#frmEmpPage input[id='flid']").val();
		date ="12-Nov-2013";
		shift = "1";
		//&date=12-Nov-2013&shift=SFT001&mchId=MCH0002061&cellId=CEL0000066&sectId=LIN0000026&factId=FCT0000012&mode=create
		//var filterString = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		var filterString = "&mchId=MCH0002061&cellId=CEL0000066&sectId=LIN0000026&factId=FCT0000012&mode=create";
		 fnValidateDate("C", date,shift,"create","150",filterString,"pcsViewTest_input.pcs");
		//fnValidateDate("C", date,shift) ;
		var pcsurl ="pcsView_input.pcs?q=2&date=12-Nov-2013&shift=SFT001&mchId=MCH0002061&cellId=CEL0000066&sectId=LIN0000026&factId=FCT0000012&mode=create"
	});
	 
</script>
<form id="frmControlPanel" name ="frmControlPanel" >

<div id="pcsEntryPage">
	 
</div><!-- End of PCSENTRY -->
<input type="hidden" id="hdnPcsDate" value=""/>
<input type="hidden" id="hdnPcsShift" value=""/>
<input type="hidden" id="hdnPcsCell" value=""/>
<input type="hidden" id="hdnPcsEquipment" value=""/>
<input type="hidden" id="hdnPcsFlid" value=""/>
</form>
