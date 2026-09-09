<script type="text/javascript">

jQuery(document).ready(function(){
		initialiseForm('frmEmpCountGrid');
	jQuery('#submitForm').val('frmEmpCountGrid');
	
	
	
	viewGrid("", "");
	//processGridnew("userfnlnempount_input.creat","?q=1","EmpCountGridTable","EmpCountGridPager"," ","doubleclick");
});


//elumalai 1-jul-2026 filter option

function viewGrid(url, filterString) {
    if (validateFilterSelection(filterString)) {
        var queryStr = "?q=1";
        if (filterString && filterString !== "") {
            queryStr += "&" + filterString;
        }
        processGridnew("userfnlnempount_input.creat", queryStr, "EmpCountGridTable", "EmpCountGridPager", "", "doubleclick");
        return true;
    }
}

function validateFilterSelection(filterString) {
    return true;
}

function doubleclick(id) {
    var rowData = jQuery("#EmpCountGridTable").jqGrid('getRowData', id);
    var empId = rowData.KEYID; // or whatever the actual key column is
    var flid = jQuery('#hdnLoginFlid').val(); // if this hidden field exists on the page
    //window.open("EmpCount_Excelview.creat?&empId=" + empId + "&flid=" + flid);
    return false;
}

</script>





<form id="frmEmpCountGrid" name="frmEmpCountGrid">
  <div id="wrapper" >
  

			<div >
				<table id="EmpCountGridTable" ></table> 
			</div>
			<div id="EmpCountGridPager"></div>
  
  
  
  
  </div>

  </form>