<script  type="text/javascript">
jQuery(document).ready(function() {

	processGridnew("trngcalendar_input.tcl", "q=2", "repeatgrdtrainingCalendar", "repeatgrdtrainingCalendarpager","PCM", "doubleclickGrid", "", "GridOnCompleteload()");
	var btnName = jQuery("#hdnBtnName").val();	
	jQuery("#btnReport").val(btnName);
	jQuery('#btnReport').click(function(){
		//alert('type:');	
		processAjaxCalls("openFile.file?fileName=TrainingCalendar.xls", "", "", "", "", "new");										
	});	

	
});
function doubleclickGrid(){
	 //navigateToNextForm("trngcalendarEntry_input.tcl?q=2&mode=modify","Training Calendar");
}

function GridOnCompleteload() {
	
	 var row = jQuery("#repeatgrdtrainingCalendar").jqGrid('getDataIDs');
	 var cm = jQuery("#repeatgrdtrainingCalendar").jqGrid("getGridParam", "colModel");
	
	 for(var i=0;i<row.length;i++)
	 {
		
		 for(var j=0;j<cm.length;j++)
   		 {  
			 

		
			  if(row[i]=='1' || row[i]=='6'||row[i]=='8'||row[i]=='11'||row[i]=='15'||row[i]=='3'){
					 
					jQuery("#repeatgrdtrainingCalendar").setCell(row[i], cm[j].name.trim(), "",{'background-color':'#D2E0F2'});
	   	 	   }
	    	 
			   
			   	
   		 }
	 }
}

</script>
<form id="frmtrainingCalendar" name="frmtrainingCalendar">
<div id='wrapperRpt'>


				
			<table id="repeatgrdtrainingCalendar" ></table>
			<div id="repeatgrdtrainingCalendarpager" ></div> 				
</div>
	
	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />

</form>