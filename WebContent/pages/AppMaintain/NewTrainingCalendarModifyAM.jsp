<script  type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();

jQuery(document).ready(function() {
	
	var flid = "";
	if( jQuery("#frmFilter input[id='flid']").length > 0 )
		flid = jQuery("#frmFilter input[id='flid']").val();
 //alert(flid +"FLiddd   ");
    //---------------Added by gopi------------
    
    
    
    formatDateBox('dteFromdate','dd-MMM-yyyy');
	formatDateBox('dteTodate','dd-MMM-yyyy');
	fillComboBox("frmtrainingCalendar","cmbDMT","sectionCombo.commonFilter");
	fillComboBox("frmtrainingCalendar","cmbJH","cellCombo.commonFilter");
	viewGrid("NewTrainingCalenderEditing_input.ntrc","flid="+flid);
	//-------gopi-----------------------------------------------------
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
	    processGridnew("NewTrainingCalenderEditing_input.ntrc", filterString, "grdtrainingCalendar", "grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "");
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
/*
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
}*/
function doubleclickGrid(id){
    var rowData = jQuery("#grdtrainingCalendar").jqGrid('getRowData',id);	
    var keyid = rowData.KEYID;
    var flid = rowData.Flid;
    var topicid = rowData.TopicId;
	  
    LoadPopUp("DivTrainingModify","TrainingCalendarEdit_input.ntrc?q=2&mode="+frmMode+"&progid="+keyid+"&topicid="+topicid+"&filterButton=false",
        true,"95%","90%","3%","1%", "trainingModify_Callback","Training Calendar Application maintainance",false,true);
}

function trainingModify_Callback(){
  jQuery('#grdtrainingCalendar').trigger("reloadGrid");
}
jQuery("#btnview").click(function(){

	var fromDate=jQuery("#dteFromdate").datebox("getValue");
	var toDate=jQuery("#dteTodate").datebox("getValue");
	var DMT=jQuery("#cmbDMT").combobox("getValue");
	var JH=jQuery("#cmbJH").combobox("getValue");
	if(fromDate.length==0){
		alert("Select From Date");
		return false;
	}
	if(toDate.length==0){
		alert("Select To Date");
		return false;
	}
	processGridnew("NewTrainingCalenderEditing_input.ntrc","?q=2&fromDate="+fromDate+"&toDate="+toDate+"&DMT="+DMT+"&JH="+JH+"&frmMode="+frmMode,"grdtrainingCalendar","grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "");
});

jQuery("#btnRefresh").click(function(){
	var flid = "";
	if( jQuery("#frmFilter input[id='flid']").length > 0 )
		flid = jQuery("#frmFilter input[id='flid']").val();

	processGridnew("NewTrainingCalenderEditing_input.ntrc","flid="+flid+"&frmMode="+frmMode,"grdtrainingCalendar","grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "");

	jQuery("#dteFromdate").datebox("clear");
	jQuery("#dteTodate").datebox("clear");
	jQuery("#cmbDMT").combobox("clear");
	jQuery("#cmbJH").combobox("clear");
});



</script>
<form id="frmtrainingCalendar" name="frmtrainingCalendar">
<div id='wrapperRpt'>
<table style="margin-top:-16px;">
	<tbody>
	<tr>
		 <td>
		 <div>
			<label style="margin-left:1px;margin-bottom: 1px;">DMT</label>
		 </div>
		 <div style="margin-left:1px;margin-bottom: 5px;">
			<input id="cmbDMT" name="cmbDMT" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
		 </div>
		 </td>

		 <td>
		 <div>
			<label style="margin-left:20px;margin-bottom: 1px;">JH</label>
		 </div>
		 <div style="margin-left:20px;margin-bottom: 5px;">
			<input id="cmbJH" name="cmbJH" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
		 </div>
		 </td>

		 <td>
		 <div>
			<label style="margin-left:25px;margin-bottom: 1px;">From Date</label>
		 </div>
		 <div style="margin-left:25px;margin-bottom: 5px;">
			<input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteFromdate" name="dteFromdate" value=""/>
		 </div>
		 </td>

		 <td>
			<label style="margin-left:15px;margin-bottom: 1px;">To Date</label>
		 <div style="margin-left:15px;margin-bottom: 5px;">
			<input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteTodate" name="dteTodate" value=""/>
		 </div>
		 </td>

		 <td>
		 <div style="margin-left:10px;margin-bottom:-10px;">
			<input type="button"  class="easyui-button" value="View" id="btnview" style="height: 24px; width : 87px;">
		 </div>
		 </td>

		 <td>
		 <div style="margin-left:20px;margin-bottom:-10px;">
			<input type="button"  class="easyui-button" value="Refresh" id="btnRefresh" style="height: 24px; width : 87px;">
		 </div>
		 </td>
	</tr>
	</tbody>
</table>



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