<script type="text/javascript">

jQuery(document).ready(function(){

	jQuery('#submitForm').val('Master');
	
	
	 var url = jQuery('#hiddenUrl').val();
	
   viewGrid("MasterGrid_input.qp","q=2");
	jQuery("#btnnew").click(function(){
		navigateToNextForm("Master_input.qp");
	});

	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=Q-points.xls", "", "", "", "", "new");					
	});
});

	
	function viewGrid(url,filterString)
{
	
		var tableCaption = "master";
		
		processGridnew(url,filterString,"MasterGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;
		
	
} 
	function doubleClickGrid(rowid) //GRID DOUBLE CLICK function
	{ 
		
		var rowData = jQuery("#MasterGrid").jqGrid('getRowData',rowid );
		var keyid = rowData.KEYID;
		//alert(keyid);
		navigateToNextForm("Master_input.qp?keyid="+keyid);
	}
</script>	











<form name="Master" id="Master" >
<div id="wrapperRpt"  >
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;margin-top: -39px" >
<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
<!--<span style='padding-left:6px;'><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style="height: 25px; width : 102px;"/></span>-->
<span style='padding-left:6px;'><label class="notes"> Double Click on row to input/view details </label></span>
</div>
<div style="margin-top: -5px">

		<table id="MasterGrid"  ></table>
		<div id="pager"></div></div>
</div>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>