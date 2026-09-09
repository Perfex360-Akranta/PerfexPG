<script  type="text/javascript">
jQuery(document).ready(function() {
	
	processGridnew("trngcalendar_input.tcl", "q=2", "grdtrainingCalendar", "grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "");
	var btnName = jQuery("#hdnBtnName").val();	
	jQuery("#btnReport").val(btnName);
	jQuery('#btnReport').click(function(){
		//alert('type:');	
		processAjaxCalls("openFile.file?fileName=TrainingCalendar.xls", "", "", "", "", "new");										
	});	
	/*jQuery('#btnAdd').click(function(){
		navigateToNextForm("trngcalendarEntry_input.tcl?q=2&mode=create","Training Calendar");
	});	*/	
	
});
function doubleclickGrid(){
	 //navigateToNextForm("trngcalendarEntry_input.tcl?q=2&mode=modify","Training Calendar");
}



</script>
<form id="frmtrainingCalendar" name="frmtrainingCalendar">
<div id='wrapperRpt'>



	<div>
<!--	 	<input type="button" class="easyui-button" id="btnAdd" name="btnAdd" value="New"  style="width:80px;"/>-->
			<input class="easyui-button" type="button" value="Report"
				id="btnReport" name="btnReport" style="height: 21px;"  /> 
			</div>
			
			<table id="grdtrainingCalendar" ></table>
			<div id="grdtrainingCalendarpager" ></div> 				
	
	</div>
	
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
</form>