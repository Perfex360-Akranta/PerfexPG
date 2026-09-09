<script  type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();

jQuery(document).ready(function() {
	
	var flid = "";
	if( jQuery("#frmFilter input[id='flid']").length > 0 )
		flid = jQuery("#frmFilter input[id='flid']").val();
 //alert(flid +"FLiddd   ");
	viewGrid("NewTrainingCalendermodify_input.ntrc","flid="+flid);
	//var btnName = jQuery("#hdnBtnName").val();	
	//jQuery("#btnReport").val(btnName);
	/*jQuery('#btnReport').click(function(){
		//alert('type:');	
		processAjaxCalls("openFile.file?fileName=TrainingCalendar.xls", "", "", "", "", "new");										
	}); */	
	/*  jQuery('#btnAdd').click(function(){
		navigateToNextForm("trngcalendarEntry_input.tcl?q=2&mode=create&frmMode="+frmMode+"&filterButton=false","Training Calendar");
	}); */	  
	
});
function viewGrid(url,filterString){  
	if( validateFilterSelection(filterString))
	{
		filterString +="&frmMode="+frmMode;
	    processGridnew("NewTrainingCalendermodify_input.ntrc", filterString, "grdtrainingCalendar", "grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
	
/*	if(filterString=="?q=2")
		return true;

	if(getFilterValue(filterString, "flid") == "" ){
		alert(" Select Functional Location ");
		return false;
	}
	
*/	
	return true;
}

function doubleclickGrid(id){
	//var rowIds = jQuery("#"+jqGridId).jqGrid('getDataIDs');
      var rowData = jQuery("#grdtrainingCalendar").jqGrid('getRowData',id);	
      var keyid = rowData.KEYID;
    //  alert("The Keyid::"+keyid); 
	  var flid = rowData.Flid;
	  var topicid=rowData.TopicId
	//var rowObject = jQuery("#grdtrainingCalendar").getRowData(TrainingId);
	//alert("rowObject"+rowObject);
    // LoadPopUp("DivFieldAuditSheet","NewTrainingCalender_input.ntrc?q=2&mode="+frmMode+"&progid="+keyid+"&topicid="+topicid,true,"1150px","580px","4px","4%", "multiSelectOk_Callback","Training Calendar",false,true);	
	 navigateToNextForm("NewTrainingCalender_input.ntrc?q=2&mode="+frmMode+"&progid="+keyid+"&topicid="+topicid+"&filterButton=false","Training Calendar");
}



</script>
<form id="frmtrainingCalendar" name="frmtrainingCalendar">
<div id='wrapperRpt'>



	<div>
	 	<!--<input type="button" class="easyui-button" id="btnAdd" name="btnAdd" value="New Entry"  style="width:80px;height: 21px;"/>
				--><span style="display: none;"> <input class="easyui-button" type="button" value="Report" 
				id="btnReport" name="btnReport" style="height: 21px;"  /> </span> 
			</div>
			
			<table id="grdtrainingCalendar" ></table>
			<div id="grdtrainingCalendarpager" ></div> 				
	
	</div>
	<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
	<input type="hidden" id='hdnProgType' value="${requestScope.frmMode}"/>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
</form>