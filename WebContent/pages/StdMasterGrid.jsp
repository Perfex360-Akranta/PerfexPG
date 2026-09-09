<script>
jQuery(document).ready(function(){
	initialiseForm('frmStdMasterGrid');
	//processGridnew("STDWorkSheet_input.stdwosh","q=2","StdMastergrid","pager","","docDoubleClick");
	viewGrid("STDWorkSheet_input.stdwosh","q=2");
	jQuery ("#btnNewBkng").click(function(){
		navigateToNextForm("STDWorkSheetMain_input.stdwosh" );
	});	
});

  /* function docDoubleClick(id)
{
	  
	  var roleId = jQuery("#hdnUserRole").val();
	    
	    // Validate role before allowing modification
	    if(roleId != 'AROL0003') {
	        console.log("Access DENIED - Role mismatch");
	        console.log("Expected: 'DMT LEADER' (AROL0003)");
	        
	        // Show error message
	        if(typeof popupCommonErrorMsg === 'function') {
	            popupCommonErrorMsg("Access Denied! Only DMT LEADER can modify records.");
	        } else {
	            alert("Access Denied! Only DMT LEADER can modify records.");
	        }
	        
	        return false;
	    }  
   var rowData = jQuery("#StdMastergrid").jqGrid('getRowData',id);
	var keyid = rowData.txtStwsKeyid;
	
   //alert(" keyid :: "+keyid);
   navigateToNextForm("STDWorkSheetMain_input.stdwosh?keyId="+keyid);
} */

function docDoubleClick(id) {
    var roleId = jQuery("#hdnUserRole").val();
    var rowData = jQuery("#StdMastergrid").jqGrid('getRowData', id);
    var keyid = rowData.txtStwsKeyid;
    var createdBy = rowData.txtStwsCreatedby;
    
    console.log("=== Grid Double Click Debug ===");
    console.log("KeyID:", keyid);
    console.log("Created By:", createdBy);
    console.log("Role ID:", roleId);
    
    // Pass createdBy as URL parameter to the entry page
    navigateToNextForm("STDWorkSheetMain_input.stdwosh?keyId=" + keyid + "&createdBy=" + createdBy);
}
  function frmStdMasterGrid_FuntLocHierarchy_SuccessCallBack(result) {

	var cellId=result.cellId;
    var flId=result.flId;

    //processGridnew("momattendancereport_input.mom","q=2","attendancereportgrid","pager","","docDoubleClick","","momattendanceOnCompleteload");
	viewGrid("STDWorkSheetMain_input.stdwosh",'?q=2&cellId=' + cellId+'&flid='+flid);
	          
  }
  
  function viewGrid(url,filterString)
  {    //alert(" filterString :: "+filterString);   
     if( validateFilterSelection(filterString))
  	{   
  		var flid = getFilterValue(filterString, 'flid');
  		//alert(" flid :: "+flid);
  	    filterString += '&flid='+flid;	
  		var tableCaption = "Standarad Work Sheet";
  		processGridnew(url,filterString,"StdMastergrid","pager","","docDoubleClick");		
  		return true; 
  	}
  	
  	return false;
  }

  function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
		
	    return true;
}
  
</script>
<form name="frmStdMasterGrid" id="frmStdMasterGrid" action=" " method="post">
<div id='wrapperRpt' style="width:85%">
</div>
 <div  style="padding-left:20px;">
      <input type="button" class="easyui-button" id="btnNewBkng" value="New Booking" style=""/>
 </div> 
<div style="float: left;padding-left: 20px">
<table id='StdMastergrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
<input type="hidden" id="hdnUserRole" value="${sessionScope.userRole}" />
<input type="hidden" id="hdnCurrentUser" value="${requestScope.currentUserCcno}" />
</div>
</form>
 

