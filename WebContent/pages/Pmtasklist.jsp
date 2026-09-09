<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmPMTaskListGrid');
	jQuery('#submitForm').val('frmPmTaskGrid');
	processGridnew("pmtasklist_input.task","?q=1&tasktype=TAS","PmtaskGrid","pager"," ", "doubleclick");


jQuery ("#btnNEW").click(function()
		{
	navigateToNextForm("pmtasklistalter_modify.task");	
	
		});

});

function checkBox(id, options, rowObject) {
	   
	var id = options.rowId;
	return '<input id="equipment_checkbox_'+ id +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+id + '\');}else{checkBoxUnChecked(\''+ id +'\')}"/>';
}

function chkboxCheck(rowId)
{
	jQuery("#PmtaskGrid").jqGrid('setCell',rowId,'selectVal','1');		
}

function checkBoxUnChecked(rowId){
	jQuery("#PmtaskGrid").jqGrid('setCell',rowId,'selectVal','0');	
}


function doubleclick(id)
{	

    //alert("sgh");
	var rowData = jQuery("#PmtaskGrid").jqGrid('getRowData',id );
	var keyid = rowData.KEYID;
	var TaskGroup=rowData.TASKGROUP;
	navigateToNextForm("pmtasklistalter_modify.task?keyid="+ keyid+"&TaskGroup="+TaskGroup);
	//alert(keyid);
    
}
	</script>
	
	
	<form id="frmPmTaskGrid">

    <div class="pmtask" id='wrapperRpt'>
    <div style="width:120%;margin-top: -28px"><input class="easyui-button" type="button" id="btnNEW" name="btnNew" value="New" style=" width : 49px;height:22px;">
   
    <span style="margin-left:10px;">Double Click on row to input/view details</span></div>
		<table id='PmtaskGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>
		
	
	</div>
	<input type="hidden" id="mode" /> 
</form>