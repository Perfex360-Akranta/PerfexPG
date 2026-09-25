 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>


 <script type="text/javascript">
	
 jQuery(document).ready(function(){	
  		initialiseForm('frmBDMulptipleResp');
  		var filterString = "?q=2"; 
  		var machId=jQuery("#hdnmachId").val();
  		var bdKeyid=jQuery("#hdnbdkeyid").val();
  		filterString +="&machId="+machId+"&bdKeyid="+bdKeyid;
  		processGridnew('BreakdownMultipleResp_input.Bbrdn',filterString ,"employeeGrid","pager_employee","","","","sucess_callBack");
  	  		
 });
 /* jQuery("#btnMultipleRespOk").click(function(){
	 var gridData=convertJsonArr("employeeGrid");
	 jQuery("#hdnMultiresp").val(gridData); 
	 closePopUpDialoge('loadMultiple');
 }); */
 //mano 
 jQuery("#btnMultipleRespOk").click(function(){
	 var gridData = convertJsonArr("employeeGrid");
	 console.log("MultiResp payload:", gridData);   // <-- verify this isn't "[]"
	 jQuery("#hdnMultiresp").val(gridData);
	 console.log("hdnMultiresp now:", jQuery("#hdnMultiresp").val());
	 closePopUpDialoge('loadMultiple');
});
 
 // commented and added by priyanka on 18/09/2026
/* function sucess_callBack(ids)
{
	var ids =  jQuery("#employeeGrid").getDataIDs();
	  for (var i = 0; i<ids.length; i++) 
	  {		
		  var calStatus = jQuery("#employeeGrid").jqGrid('getCell',ids[i],"BDRS_ACTIVE");
		  if(calStatus == 'Y' )
			  jQuery("#jqg_employeeGrid_"+(i+1)).attr('checked',true);;
	  }
} */

function sucess_callBack(ids)
{
    var rowIds = jQuery("#employeeGrid").jqGrid('getDataIDs');
    for (var i = 0; i < rowIds.length; i++)
    {
        var rowId = rowIds[i];
        var calStatus = jQuery("#employeeGrid").jqGrid('getCell', rowId, "BDRS_ACTIVE");
        if (calStatus == 'Y') {
            jQuery("#employeeGrid").jqGrid('setSelection', rowId, false); // tells jqGrid the row is selected
            jQuery("#jqg_employeeGrid_" + rowId).prop('checked', true);   // ticks the visible checkbox
        }
    }
}

// end
 function convertJsonArr(gridId){
		var allrow = jQuery("#"+gridId).jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			if (jQuery('#jqg_employeeGrid_' + rowno).is(':checked')) {
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
 jQuery('#btnMultipleRespCancel').click(function(){
		closePopUpDialoge('loadMultiple');
	});
 </script>
 <form name="frmBDMulptipleResp" id="frmBDMulptipleResp" method="post">
 <div style="margin-left: 220px;">
 <input type="button" class="easyui-button" id="btnMultipleRespOk" value="Ok" style="height: 22px;" />
 <input type="button" class="easyui-button" id="btnMultipleRespCancel" value="Cancel" style="height: 22px;" />
 </div>
 <div>
 <table id="employeeGrid" style="width:100%"></table>
	     <div id="pager_employee">
	     </div>
</div>
<input type="hidden" id="hdnmachId" value="${requestScope.bdmachId }" />
<input type="hidden" id="hdnbdkeyid" value="${requestScope.bdemrNo}" />
 </form>