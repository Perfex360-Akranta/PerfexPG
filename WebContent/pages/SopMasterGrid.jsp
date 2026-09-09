	
<script type="text/javascript">

jQuery(document).ready(function(){//alert(12);

	jQuery('#submitForm').val('SopMaster');
	//alert('inside');
	
	 var url = jQuery('#hiddenUrl').val();
	//alert(url);
   viewGrid("SopMasGrid_input.sop","q=2");
	jQuery("#btnnew").click(function(){
		navigateToNextForm("Sopmodify_input.sop");
	});

	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=SOP.xlsx", "", "", "", "", "new");					
	});
	
});

	
	function viewGrid(url,filterString)
{
	
		var tableCaption = "Sop";
		
		processGridnew(url,filterString,"SopMasterGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;
		
	
} 


function loadComplete(){
		hideJqGridRow("SopMasterGrid", "1");
	}
	function doubleClickGrid(rowid) //GRID DOUBLE CLICK function
	{ 
		
		var rowData = jQuery("#SopMasterGrid").jqGrid('getRowData',rowid );
		var keyid = rowData.KEYID;
		//alert(keyid);
		navigateToNextForm("Sopmodify_input.sop?keyid="+keyid);
	}
</script>	











<form name="SopMaster" id="SopMaster" >
<div id="wrapperRpt"  >
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:0px;" >
<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
<span style="padding-left:6px;"><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style=" width : 90px;"/></span>
<span style="padding-left:6px;"><label class="notes"> Double Click on row to input/view details </label></span>
</div>


		<table id="SopMasterGrid"  ></table>
		<div id="pager"></div>
</div>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>