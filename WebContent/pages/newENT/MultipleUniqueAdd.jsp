<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"><!--
	jQuery(document).ready(function(){
		initialiseForm("frmMultipleUniquePosAdd");
		jQuery("#submitForm").val("frmMultipleUniquePosAdd");
		var url = jQuery("#hiddenUrl").val();
		viewGrid(url, "q=2");
	});

function viewGrid(){		
	     var Calendarflid=jQuery("#hdnCalendarflid").val();
	    var  CalendarId=jQuery("#hdnCalendarId").val();
	    //alert(CalendarId);
		var ds ="?q=2&Calendarflid="+Calendarflid+"&CalendarId="+CalendarId;
		processGridnew("MultipleUniqueAdd_input.ntrc",ds,"MultipleUniqueGrd","pager","","","","UniqueloadComplete"," ");
}  

// 	  jQuery("#btnSave").click(function(){
// 		 var CalendarId=jQuery('#hdnCalendarId').val(); 
// 		 var SectionId=jQuery("#hdnSectionId").val();
// 		 alert(SectionId);
// 		 var CellId=jQuery("#hdncellId").val();

// 		 alert(DMTId);
// 		 if(CalendarId.length!=0 && CalendarId!=null){
// 		    var paramconvertArr =convertJsonArr();
// 		   // alert("paramconvertArr"+paramconvertArr);
// 	  	    saveForm("frmMultipleUniquePosAdd","MultipleUniquePosition_save.gbtc?&CalendarId="+CalendarId+"&SectionId="+SectionId+"&CellId="+CellId+"&paramconvertArr="+paramconvertArr);
	  	     
// 			 }
// 	  });
	   // --- using this  changed by vignesh 
	  jQuery("#btnSave").click(function () {
    var CalendarId = jQuery("#hdnCalendarId").val();
    var SectionId  = jQuery("#hdnSectionId").val();
    var CellId     = jQuery("#hdncellId").val();

    // Optional debug
    // alert("SectionId: " + SectionId);
    // alert("CellId: " + CellId);

    if (CalendarId && CalendarId.length !== 0) {
        // This should return a plain JSON string or similar
        var paramconvertArr = convertJsonArr();

        // Encode each parameter value
        var url =
            "MultipleUniquePosition_save.gbtc"
            + "?CalendarId="      + encodeURIComponent(CalendarId)
            + "&SectionId="       + encodeURIComponent(SectionId)
            + "&CellId="          + encodeURIComponent(CellId)
            + "&paramconvertArr=" + encodeURIComponent(paramconvertArr);

        // Optional: check the final URL
        // console.log("Final URL:", url);

        saveForm("frmMultipleUniquePosAdd", url);
    }
});

	  
	  
	  jQuery("#btnClose").click(function(){
		  
		  closePopUpDialoge("DivMultiUniquePosition");
		  jQuery("#UniqueGrid").trigger("reloadGrid");
		  
	  });
	  
  function DivMultiUniquePosition_onClose(){
			jQuery("#UniqueGrid").trigger("reloadGrid");
			return true;
		}
	  
	  function UniqueloadComplete(){
	      var row = jQuery("#MultipleUniqueGrd").jqGrid('getDataIDs');
		  var cm = jQuery("#MultipleUniqueGrd").jqGrid("getGridParam", "colModel");
		 for(var i=0;i<row.length;i++){
			   var etcskeyid = jQuery("#MultipleUniqueGrd").jqGrid('getCell',row[i],"hdnEtcuKeyid");	
			  // alert("etcskeyid"+etcskeyid);	 
			   if(etcskeyid.trim().length>0){ 
				    jQuery('#MultipleUniqueGrd').setSelection(row[i], true);
				    jQuery('input:checkbox[id=jqg_MultipleUniqueGrd_'+row[i]+']').attr('checked',true);
				    jQuery("#MultipleUniqueGrd").jqGrid('setCell',row[i],'selctVal','1');
				    jQuery('input:checkbox[id=jqg_MultipleUniqueGrd_'+row[i]+']').attr('disabled',true);
			   }
		 }
	   }
	  
		function convertJsonArr(){
			var allrow = jQuery("#MultipleUniqueGrd").jqGrid('getRowData');
			var jsonArrO = '';
			var val = "";
			for ( var i = 0; i < allrow.length; i++) {
				var row = allrow[i];
				var rowno = parseInt(i) + 1;
				if(jQuery('#jqg_MultipleUniqueGrd_' + rowno).is(':disabled')==true)
					{
					continue;
					}
				if (jQuery('#jqg_MultipleUniqueGrd_' + rowno).is(':checked')) {
					
					jsonArrO += '{';
					for ( var colName in row) {
						if (row[colName].substring(0, 6) != '<input') {
							jsonArrO += '"' + colName + '":"' + row[colName] + '",';
						} else {
							var x = row[colName].indexOf("id=") + 4;
							var y = row[colName].substring(x);
							var z = y.indexOf('"');
							var cellId = y.substring(0, z);
							if (jQuery("#" + cellId).attr("type") == "checkbox"){
								val = jQuery('#' + cellId).is(':checked') ? 'Y'
										:'N';					  
							} else {
								val = jQuery('#' + cellId).val();
							}
							jsonArrO += '"' + colName + '":"' + val + '",';
						}
					}
					jsonArrO = jsonArrO.slice(0, -1) + '},';
				}
			}
			
			return '[' + jsonArrO.slice(0, -1) + ']';
		}
	  	 

   
</script>
<form id="frmMultipleUniquePosAdd" name="frmMultipleUniquePosAdd">
<tr>
       
	 	<div style="margin-top:8px; margin-left:9px;">
		<table id="MultipleUniqueGrd">
		</table>
		<div id="pager"></div>
	</div>  
  
</tr> 

<div style="margin-left:250px;margin-top:10px;">
<input type="button" class="easyui-button" value="Save" id="btnSave"/>
<input type="button" class="easyui-button" value="Close" id="btnClose"/>
 </div>
 
<input type="hidden" id="mode" name="mode" value="create" />
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}" />
<input type="hidden" id="hdnlocnid" name="hdnlocnid" value="${requestScope.locnid}" />
<input type="hidden" id="hdnJHFlid" name="hdnJHFlid" value="${JHFlid}" />
<input type="hidden" id="hdnCalendarflid" name="hdnCalendarflid" value="${requestScope.Calendarflid}">
<input type="hidden" id="hdnCalendarId" name="hdnCalendarId" value="${requestScope.CalendarId}" />
<input type="hidden" id="hdnDMTName" name="hdnDMTName" value="${requestScope.DMTName}" />
<input type="hidden" id="hdnSectionId" name="hdnSectionId" value="${requestScope.SectionId}" />
<input type="hidden" id="hdncellId" name="hdncellId" value="${requestScope.cellId}" />
<input type="hidden" id="hdnuniq" name="hdnuniq" value="${requestScope.uniq}" />
</form>