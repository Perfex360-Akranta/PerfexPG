<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"><!--
	jQuery(document).ready(function(){
		initialiseForm("frmGbtcMultipleUniqueAdd");
		jQuery("#submitForm").val("frmGbtcMultipleUniqueAdd");
		var url = jQuery("#hiddenUrl").val();
		viewGrid(url, "q=2");
	});

function viewGrid(){		
	     var Calendarflid=jQuery("#hdnCalendarflid").val();
	    var  CalendarId=jQuery("#hdnCalendarId").val();   
	    //alert(CalendarId);
	    var  SectionId=jQuery("#hdnSectionId").val();
	    var  cellId=jQuery("#hdncellId").val();
		var ds ="?q=2&Calendarflid="+Calendarflid+"&CalendarId="+CalendarId+"&SectionId="+SectionId+"&cellId="+cellId;
		processGridnew("MultipleUniqueAdd_input.gbtc",ds,"MultipleUniqueGrd","pager","","","","UniqueloadComplete"," ");
}  

// 	  jQuery('#btnSaveGbtc').click(function(){
// 		 		// alert(123);
// 		 var CalendarId=jQuery('#hdnCalendarId').val(); 
// 		 var SectionId=jQuery("#hdnSectionId").val();
// 		// alert(SectionId);
// 		 var CellId=jQuery("#hdncellId").val();

// 		// alert(DMTId);
// 		 if(CalendarId.length!=0 && CalendarId!=null){
// 		    var paramconvertArr =convertJsonArr();
// 		   // alert("paramconvertArr"+paramconvertArr);
// 		   //sriram 19-Nov-2025
		   

		
// 	  	    saveForm("frmGbtcMultipleUniqueAdd","MultipleUniquePosition_save.gbtc?&CalendarId="+CalendarId+"&SectionId="+SectionId+"&CellId="+CellId+"&paramconvertArr="+paramconvertArr);
	  	     
// 			 }
// 	  });
	  
	  // changed by vignesh 
	  
	  jQuery('#btnSaveGbtc').click(function () {
		    var CalendarId = jQuery('#hdnCalendarId').val();
		    var SectionId  = jQuery("#hdnSectionId").val();
		    var CellId     = jQuery("#hdncellId").val();

		    if (CalendarId && CalendarId.length !== 0) {
		        // Whatever convertJsonArr() returns (JSON or JSON-like string)
		        var paramconvertArr = convertJsonArr();

		        // Optional: clean trailing spaces if any
		        // paramconvertArr = jQuery.trim(paramconvertArr);

		        // Encode each value properly for use in query string
		        var encodedCalendarId   = encodeURIComponent(CalendarId);
		        var encodedSectionId    = encodeURIComponent(SectionId);
		        var encodedCellId       = encodeURIComponent(CellId);
		        var encodedParamconvert = encodeURIComponent(paramconvertArr);

		        var url =
		            "MultipleUniquePosition_save.gbtc"
		            + "?CalendarId="    + encodedCalendarId
		            + "&SectionId="     + encodedSectionId
		            + "&CellId="        + encodedCellId
		            + "&paramconvertArr=" + encodedParamconvert;

		        // Now call your existing helper
		        saveForm("frmGbtcMultipleUniqueAdd", url);
		    }
		});

	  
	  jQuery("#btnClose").click(function(){
		  
		  closePopUpDialoge("DivMultiUniquePosition");
		  jQuery("#calUniquePosGrid").trigger("reloadGrid");
		  
	  });
	  
  function DivMultiUniquePosition_onClose(){
			jQuery("#calUniquePosGrid").trigger("reloadGrid");
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
<form id="frmGbtcMultipleUniqueAdd" name="frmGbtcMultipleUniqueAdd">
<tr>
       
	 	<div style="margin-top:8px; margin-left:9px;">
		<table id="MultipleUniqueGrd">
		</table>
		<div id="pager"></div>
	</div>  
  
</tr> 

<div style="margin-left:250px;margin-top:10px;">
<input type="button" class="easyui-button" value="Save" id="btnSaveGbtc"/>
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