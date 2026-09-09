<script type="text/javascript">
	jQuery(document).ready(function(){	
		jQuery('#submitForm').val('Master');		
		var url = jQuery('#hiddenUrl').val();		
	   	viewGrid("WorkFlow_input.workflow","q=2");
		jQuery("#btnnew").click(function(){
			navigateToNextForm("WorkFlowEntry_input.workflow");
		});
	});
	
	function viewGrid(url,filterString)
	{	
		var tableCaption = "Work Flow";		
		processGridnew(url,filterString,"MasterGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;	
	} 
	
	function doubleClickGrid(rowid) //GRID DOUBLE CLICK function
	{ 		
		var rowData = jQuery("#MasterGrid").jqGrid('getRowData',rowid );
		var keyid = rowData.Keyid;
		//alert(keyid);
		navigateToNextForm("WorkFlowEntry_input.workflow?keyid="+keyid);
	}
</script>
<form name="Master" id="Master" >
	<div id="wrapperRpt" style="margin-left:26%;" >
		<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
			<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
			<label class="notes"> Double Click on row to input/view details </label>
		</div>	
		<table id="MasterGrid"  ></table>
		<div id="pager"></div>
	</div>
</form>