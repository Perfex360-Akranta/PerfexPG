<!-- added by manikandan created by babu -->

<script  type="text/javascript">
jQuery(document).ready(function() {
	 //alert(1233);
	var btnName = jQuery("#hdnBtnName").val();	
	jQuery("#btnReport").val(btnName);
	
	jQuery('#btnReport').click(function(){
		//alert('type:');	
		processAjaxCalls("openFile.file?fileName=TrainingCalendar.xls", "", "", "", "", "new");										
	});	
	/*jQuery('#btnAdd').click(function(){
		navigateToNextForm("trngcalendarEntry_input.tcl?q=2&mode=create","Training Calendar");
	});	*/	
	var url = jQuery("#hiddenUrl").val();
	
	viewGrid(url,"?q=2");
});

function viewGrid(url, filterString) {
	 //alert(url);
	processGridnew(url, filterString, "grdtrainingCalendar", "grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "loadComplete");
	return true;	
}

function loadComplete() {
	//hideJqGridRow('grdtrainingCalendar', '1');

}
function doubleclickGrid(){
	 //navigateToNextForm("trngcalendarEntry_input.tcl?q=2&mode=modify","Training Calendar");
}



</script>
<form id="frmtrainingCalendar" name="frmtrainingCalendar">
<div id='wrapperRpt'>



	<div>
<!--	 	<input type="button" class="easyui-button" id="btnAdd" name="btnAdd" value="New"  style="width:80px;"/>-->
			<input class="easyui-button" type="button" value="Report"
				id="btnReport" name="btnReport" style="height: 21px;display: none;"   /> 
			</div>
			
			<table id="grdtrainingCalendar" ></table>
			<div id="grdtrainingCalendarpager" ></div> 				
	
	</div>
	
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
</form>