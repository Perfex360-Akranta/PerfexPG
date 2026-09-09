<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script type="text/javascript">	

jQuery(document).ready(function(){
	initialiseForm('frmStdWorkSheetGrid');
	jQuery('#submitForm').val('frmStdWorkSheetGrid');
	processGridnew("STDWorkSheet_input.stdwosh","?q=1","StdWoSheetGrid","StdWoSheetPager","StandardizedWorkSheet", "doubleclick");
	var vnew= "No";
	jQuery("#btnSWSNew").click(function(){
		vnew="Yes"; 
		//alert('STDWorkSheetForm_input.stdwosh');
		navigateToNextForm('STDWorkSheetForm_input.stdwosh'+'?q=1&new='+vnew,"Standardized Work Sheet");

	});
});
function doubleclick(id){
	var vnew= "No";
	/*
	var rowData = jQuery('#list').jqGrid("getDataIDs",id);
	var Process = rowData.Process;
	var TypeOfManpower = rowData.TypeOfManpower;
	var FunctionalLocation = rowData.FunctionalLocation;
	var MajorSteps = rowData.MajorSteps;
	var DateTime = rowData.DateTime;
	var PreparedBy = rowData.PreparedBy;
	var ApprovedBy = rowData.ApprovedBy;
	navigateToNextForm('STDWorkSheetForm_input.stdwosh'+'?q=1&Process='+Process+'&TypeOfManpower='+TypeOfManpower+'&FunctionalLocation='+FunctionalLocation+'&MajorSteps='+MajorSteps+'&DateTime='+DateTime+'&PreparedBy='+PreparedBy+'&ApprovedBy='+ApprovedBy,"Standardized Work Sheet");
	*/
	navigateToNextForm('STDWorkSheetForm_input.stdwosh'+'?q=1&new='+vnew,"Standardized Work Sheet");
	
}
</script>
<form id="frmStdWorkSheetGrid" name="frmStdWorkSheetGrid">
	<div id="wrapper"  style="width:100%">
		<div>
		<input type="button" class="easyui-button" value ="New" name="btnSWSNew" id="btnSWSNew" style="height: 23px; margin-left: 900px; "/>
		</div>
		<div  style=" margin-top:1%;">
			<div>
				<table id="StdWoSheetGrid" ></table> 
			</div>
			<div id="StdWoSheetPager"></div>
		</div> 
	</div>
</form>