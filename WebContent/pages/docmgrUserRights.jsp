<script type="text/javascript">	
jQuery.noConflict();
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmAddDocUserRights'); 
	initialiseForm("frmAddDocUserRights");
	fillComboBox("frmAddDocUserRights","cmbRlriRoleKeyid","role_combo.dcm");
	viewGrid('userRights_input.dcm',"?q=1");	
});
jQuery("#btnAddUserRights").click(function(){

	 var dataStr = 'userRights_save.dcm?q=2';
	 saveForm('frmAddDocUserRights',dataStr);
});
function viewGrid(url,filterString)
{
	var folderId = jQuery("#hdnRlriDocid").val();
	if(folderId != null && folderId != ' ' && folderId != '')
		filterString += '&folderId='+folderId;
	processGridnew(url,filterString,"userRightsGrid","userRightsPager","User Rights","fillUserRights","","userRights_Complete");
}
function viewFormatter(id, options, rowObject)
{
	var id = options.rowId;
	
	if(rowObject[3] == '1')
	{
		 var formatStr  = '<span id="view_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >&#10003';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
		// return '<input  type="checkbox" checked="true" onclick="if(this.checked){selectData(\''+id + '\',view);}else{unselectData(\''+id + '\',view);}"/>';
	}
  		//
  	else
  	{
  		var formatStr  = '<span id="view_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\',view);}else{unselectData(\''+id + '\',view);}"/>';
  	}
  		//return '<input  type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\',view);}else{unselectData(\''+id + '\',view);}"/>';
}
function modifyFormatter(id, options, rowObject)
{
	var id = options.rowId;
	if(rowObject[4] == '2')
	{
		 var formatStr  = '<span id="modify_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >&#10003';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  checked="true" onclick="if(this.checked){selectData(\''+id + '\',modify);}else{unselectData(\''+id + '\',modify);}"/>';
	}
  	else
  	{
  		 var formatStr  = '<span id="modify_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\',modify);}else{unselectData(\''+id + '\',modify);}"/>';
  	}
  	
}

function deleteFormatter(id, options, rowObject)
{
	var id = options.rowId;
	if(rowObject[5] == '4')
	{
		 var formatStr  = '<span id="deleteRights_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >&#10003';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  checked="true" onclick="if(this.checked){selectData(\''+id + '\',deleteRights);}else{unselectData(\''+id + '\',deleteRights);}"/>';
	}
  	else
  	{
  		 var formatStr  = '<span id="deleteRights_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\',deleteRights);}else{unselectData(\''+id + '\',deleteRights);}"/>';
  	}
  	
}

function dlFormatter(id, options, rowObject)
{
	var id = options.rowId;
	if(rowObject[6] == '8')
	{
		 var formatStr  = '<span id="downloadRights_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >&#10003';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  checked="true" onclick="if(this.checked){selectData(\''+id + '\',downloadRights);}else{unselectData(\''+id + '\',downloadRights);}"/>';
	}
  	else
  	{
  		 var formatStr  = '<span id="downloadRights_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\',downloadRights);}else{unselectData(\''+id + '\',downloadRights);}"/>';
  	}
  	
}
function userRightsFormatter(id, options, rowObject)
{
	var id = options.rowId;
	if(rowObject[7] == '16')
	{
		 var formatStr  = '<span id="userRights_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >&#10003';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  checked="true" onclick="if(this.checked){selectData(\''+id + '\',userRights);}else{unselectData(\''+id + '\',userRights);}"/>';
	}
  	else
  	{
  		 var formatStr  = '<span id="userRights_'+id+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;" >';// tick 
		 formatStr  +=  '</span>';
		 return formatStr;
  		//return '<input  type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\',userRights);}else{unselectData(\''+id + '\',userRights);}"/>';
  	}
  	
}


function checkData(Id){
	var val = jQuery('#'+Id).html();
	if(val != null && val != '' && val != ' ')
		jQuery('#'+Id).html('');
	else
		jQuery('#'+Id).html('&#10003');
}
function selectData(rowId,colName){

	jQuery("#userRightsGrid").setCell(rowId, colName,"1");
}

function unselectData(id,colName){
	jQuery("#userRightsGrid").setCell(id, colName," ");	
}
function userRights_Complete()
{
	
	if(screen.width <1300)
	{
		jQuery( "#userRightsGrid" ).setGridWidth(675);
	}

	jQuery("#userRightsGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) { 
		
			if(cellidx == 4)
			{		
				var val = jQuery("#view_"+id).html();
				if(val != null && val != '' && val != ' ')
					jQuery("#view_"+id).html('');
				else
					jQuery("#view_"+id).html("&#10003");	
			}
			else if(cellidx == 5)
			{
				var val = jQuery("#modify_"+id).html();
				if(val != null && val != '' && val != ' ')
					jQuery("#modify_"+id).html('');
				else
					jQuery("#modify_"+id).html("&#10003");	
			}
			else if(cellidx == 6)
			{
				var val = jQuery("#deleteRights_"+id).html();
				if(val != null && val != '' && val != ' ')
					jQuery("#deleteRights_"+id).html('');
				else
					jQuery("#deleteRights_"+id).html("&#10003");	
			}
			else if(cellidx == 7)
			{
				var val = jQuery("#downloadRights_"+id).html();
				if(val != null && val != '' && val != ' ')
					jQuery("#downloadRights_"+id).html('');
				else
					jQuery("#downloadRights_"+id).html("&#10003");	
			}
			else if(cellidx == 8)
			{
				var val = jQuery("#userRights_"+id).html();
				if(val != null && val != '' && val != ' ')
					jQuery("#userRights_"+id).html('');
				else
					jQuery("#userRights_"+id).html("&#10003");	
			}
		}
	});
	
}
function fillUserRights(id)
{
	var rowData = jQuery("#userRightsGrid").jqGrid('getRowData',id);
	var keyId = rowData.keyId;
	var roleId = rowData.roleId;
	/*if(rowData.view.indexOf('true') >= 0)
		jQuery('#chbView').attr('checked',true);
	if(rowData.modify.indexOf('true') >= 0)
		jQuery('#chbModify').attr('checked',true);
	if(rowData.deleteRights.indexOf('true') >= 0)
		jQuery('#chbDelete').attr('checked',true);
	if(rowData.downloadRights.indexOf('true') >= 0)
		jQuery('#chbDownload').attr('checked',true);
	if(rowData.userRights.indexOf('true') >= 0)
		jQuery('#chbUserrights').attr('checked',true);*/

	if(jQuery('#view_'+id).html() != null && jQuery('#view_'+id).html() != '' && jQuery('#view_'+id).html() != ' ')
		jQuery('#chbView').attr('checked',true);
	if(jQuery('#modify_'+id).html() != null && jQuery('#modify_'+id).html() != '' && jQuery('#modify_'+id).html() != ' ')
		jQuery('#chbModify').attr('checked',true);
	if(jQuery('#deleteRights_'+id).html() != null && jQuery('#deleteRights_'+id).html() != '' && jQuery('#deleteRights_'+id).html() != ' ')
		jQuery('#chbDelete').attr('checked',true);
	if(jQuery('#downloadRights_'+id).html() != null && jQuery('#downloadRights_'+id).html() != '' && jQuery('#downloadRights_'+id).html() != ' ')
		jQuery('#chbDownload').attr('checked',true);
	if(jQuery('#userRights_'+id).html() != null && jQuery('#userRights_'+id).html() != '' && jQuery('#userRights_'+id).html() != ' ')
		jQuery('#chbUserrights').attr('checked',true);

	if(keyId != null && keyId != ' ' && keyId != '')
		jQuery('#hdnRlriKeyid').val(keyId);

	if(roleId != null && roleId != ' ' && roleId != '')
		jQuery('#cmbRlriRoleKeyid').combobox('setValue',roleId);
}
</script>
<form id="frmAddDocUserRights" name="frmAddDocUserRights">
<div style="padding-left: 10px;">
	<div  style="padding-left:10px;">
		<label class="mandatory-lbl">Role</label>   
<!--		<label class="lblUserRightsDocMgr">User Rights</label>       -->
	</div> 
	<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
		<input id="cmbRlriRoleKeyid" name="cmbRlriRoleKeyid" class="easyui-text" style="width:260px;width:205px\9;" maxlength="20" value="${requestScope.dcmTlRoleRights.rlriRoleKeyid}"/ >
		<span style="padding-left:2%">
			<label>View</label>
			<input type="checkbox" id="chbView"  name="chbView" value="1"/>
			<span style="margin-left:1%"><label>Modify</label>
			<input type="checkbox" id="chbModify"  name="chbModify" value="2"/></span>
			<span style="margin-left:1%"><label>Delete</label>
			<input type="checkbox" id="chbDelete"  name="chbDelete" value="4"/></span>
			<span style="margin-left:1%"><label>Download</label>
			<input type="checkbox" id="chbDownload"  name="chbDownload" value="8"/></span>
			<span style="margin-left:1%"><label>Access Rights</label>
			<input type="checkbox" id="chbUserrights"  name="chbUserrights" value="16"/></span>
			<span style="margin-left:2%">
			<input id="btnAddUserRights" class="easyui-button" name="btnAddUserRights"  type="button" value="Add" style="height:20px;"  />
			</span>
		</span>                       
	</div>	
<!--	<div class="easyui-paddingbfpx" style="padding-left:10px;">-->
<!--		<label>User Rights</label>                       -->
<!--	</div> -->
<!--	<div class="easyui-paddingbfpx" style="padding-left:10px;"> -->
<!--		<label>View</label>-->
<!--		<input type="checkbox" id="chbView"  name="chbView" value="1"/>-->
<!--		<span style="margin-left:1%"><label>Modify</label>-->
<!--		<input type="checkbox" id="chbModify"  name="chbModify" value="2"/></span>-->
<!--		<span style="margin-left:1%"><label>Delete</label>-->
<!--		<input type="checkbox" id="chbDelete"  name="chbDelete" value="4"/></span>-->
<!--		<span style="margin-left:1%"><label>Download</label>-->
<!--		<input type="checkbox" id="chbDownload"  name="chbDownload" value="8"/></span>-->
<!--		<span style="margin-left:1%"><label>User Rights</label>-->
<!--		<input type="checkbox" id="chbUserrights"  name="chbUserrights" value="16"/></span>-->
<!--		<span style="margin-left:4%">-->
<!--		<input id="btnAddUserRights" class="easyui-button" name="btnAddUserRights"  type="button" value="Add" style="height:20px;"  />-->
<!--		</span>-->
<!--	</div>-->
	<div style="float:left;padding-left:10px;"> 
	 <table id="userRightsGrid" style="width:100%"><tr><td/></tr></table>
	 <div id="userRightsPager"></div>
	</div>
	<input type="hidden" id="hdnRlriDocid" name="hdnRlriDocid" value="${requestScope.folderId}"/>
	<input type="hidden" id="hdnRlriKeyid" name="hdnRlriKeyid">
	</div>
</form>