
<script>

jQuery(document).ready(function(){	
	initialiseForm('frmtrainingFeedMain');
	var mainForm = jQuery("#mainFormValClti").val();
	   
					if (mainForm !=true) 
					{
						jQuery(".clitdiv").attr('id', 'wrapperRpt');						
					}
	
	var FromDate=null;
	var ToDate = null;	
	var url = jQuery('#hiddenUrl').val();	
	var mode = "VIEW";	
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;
	dataString += "&FORM_MODE="+mode + "&ViewClicked=Y";
	
	var filterStr = jQuery("#hdnFilterStr").val();
	
	if( filterStr != null && filterStr.length > 0 )
	{
		var dataString = jQuery("#hdnFilterStr").val();
		var tableCaption = "Equipment Query";
		processGridnew(url,dataString,"list","pager",tableCaption," ");
	}
	else{
		
		viewGrid(url,"q=2");
	}
	jQuery ("#btnNew").click(function()
	{
		     navigateToNextForm("TrainingFeedbackForm_input.trfb","Training Feedback Form");	
	});
   // var btnName = jQuery("#hdnBtnName").val();
	//jQuery("#btnView").val(btnName);
	/*jQuery("#btnView").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=QMMOMAttendance.xls", "", "", "", "", "new");					
	});*/
	
});
	function viewGrid(url,dataString)
	{
		processGridnew("TrainingFeedbackMain_input.trfb",dataString,"list","pager","","docDoubleClick");
	}
	



function docDoubleClick(id)
{	
	//alert(id);
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	//alert("keyid  " +keyid);
	var Flid =  rowData.FLN;
	
	//alert("keyID  "+keyid);
	//var filter = jQuery("#hdnFilterStr").val();
	//var jsonstr = '{"filter":"'+ filter+'"}';
	//var perstData = jQuery.parseJSON(jsonstr);
	navigateToNextForm('TrainingFeedbackForm_input.trfb?&grid=true&keyId='+keyid,"Training Feedback Form");
    
}

</script>
<form id="frmtrainingFeedMain">
<div id="WrapperRpt" style="width:100%">
<table>
 <tr>

	<td>
    <div ><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">
<!--    <span style="padding-left:10px;"><input class="easyui-button" type="button" id="btnView" name="btnView" value="View Report"></span>-->
    <span style="padding-left:10px;"><label class="notes"> Double Click on row to input/view details </label></span>
    
   </div></td>
    </tr> 
</table> 
</div> 
<div style="padding-left:45px;">
    <table id="list" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="paramDiv" style="display:none;" title="param">
	</div>
</div>

	    <input type="hidden" id="hdnFilterStr" value="${requestScope.filter}"/>
		<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
		<input type="hidden" id="hdnBtnName" value="View Report"/> 
	
</form>
	
	