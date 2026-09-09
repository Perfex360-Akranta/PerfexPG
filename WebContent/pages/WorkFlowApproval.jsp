<script type="text/javascript">
	jQuery(document).ready(function(){	
		jQuery('#frmWorkFlowApproval').val('Master');		
		var url = jQuery('#hiddenUrl').val();		
	   	viewGrid("approvalList_input.workflow","q=2");
	/*	jQuery("#btnnew").click(function(){
			navigateToNextForm("WorkFlowEntry_input.workflow");
		});*/
	});
	
	function viewGrid(url,filterString)
	{	
		var tableCaption = "Work Flow Approval";		
		processGridnew(url,filterString,"approvalgrid","approvalpager",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;	
	} 
	
	function doubleClickGrid(rowid) //GRID DOUBLE CLICK function
	{ 		
		var rowData = jQuery("#approvalgrid").jqGrid('getRowData',rowid );
		var keyid = rowData.Keyid;
		//alert(keyid);
		//navigateToNextForm("WorkFlowEntry_input.workflow?keyid="+keyid);
	}
	function formatterChkWorkFlow(id, options, rowObject)
	{		
		var rowId = options.rowId;	
		return '<input id="workFlow_checkbox_'+rowId+'" name="workFlow_checkbox_" '+ (rowObject[0]&&id=="2" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){workFlowCheck(\''+rowId + '\');}else{workFlowUnCheck(\''+ rowId +'\')}"/>';
	}

	function workFlowCheck(rowId)
	{
		//alert('check');	
		setFormater("workflowGrid","frmworkflow","RoleCombo.workflow?",rowId,"rolename","role","200px",false);	
		setFormater("workflowGrid","frmworkflow","typeCombo.workflow?",rowId,"typename","type","90px",false);			
		jQuery("#workflowGrid").jqGrid('setCell', rowId, 'select', '1'); 
	}

	function workFlowUnCheck(rowId){
		//alert('uncheck');		
		var roleval = jQuery("#workflowGrid").jqGrid('getCell', rowId,"role");
		var typeval = jQuery("#workflowGrid").jqGrid('getCell', rowId,"type");
		//alert('roleval:'+roleval+'typeval:'+typeval);
		removeFormater("workflowGrid","combo",rowId,"","rolename");
		removeFormater("workflowGrid","combo",rowId,"","typename");
		//alert('uncheck');	
		jQuery("#workflowGrid").jqGrid('setCell', rowId, 'select', '0');			 		
	}	
</script>
<form name="frmWorkFlowApproval" id="frmWorkFlowApproval" >
	<div id="wrapperRpt" style="margin-left:26%;" >
		<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
			<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
			<label class="notes"> Double Click on row to input/view details </label>
		</div>	
		<table id="approvalgrid"></table>
		<div id="approvalpager"></div>
	</div>
</form>