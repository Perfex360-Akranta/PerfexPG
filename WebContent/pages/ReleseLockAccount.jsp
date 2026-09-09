<script>
jQuery(document).ready(function(){
		
	var url = jQuery('#hiddenUrl').val();
	jQuery('#submitForm').val('frmReleaselockaccnt');
	initialiseForm('frmReleaselockaccnt');	

	var statusType = '';
	if(url == "ReleaseLockAcc_input.ReleaseAcc")
		statusType = 'U';
	
	viewGrid(url,"?q=2",statusType);	


	
	jQuery("#btnChangepasswrd").click(function(){
		 LoadPopUp("PasswordDiv","ReleaseChangePasswrd.ReleaseAcc", true,"380px","240px","14px","30%", "multiSelectOk_Callback","Change Password");
		 });
	jQuery("#chksetdefaultpaswrd").click(function(){
		
		jQuery("#hdnChk").val("True");
		
	});
	if(jQuery("#chkNoAudit").attr('checked')== false){
		//alert(1);
		jQuery("#hdnChk").val("False");
		}

});

function viewGrid(url,filterString,statusType)
{	jQuery.cookie("filterString",filterString);

if(statusType == 'undefined' ||statusType == undefined){
	statusType = getFieldValue('cboStatus');
}
filterString += '&statusType='+statusType;

	processGridnew(url,filterString,"Releaselockaccnt","Releaselockaccntpager","","","","ReleaselockaccntLoadComplete","selectRowFunction");			

	return true;	
}
/*function chkbox_ReleaseLockaccnt(id, options, rowObject)
{
	var row;Id = options.rowId;	  	
	return '<input id="ReleaseLockaccnt_checkbox" name="ReleaseLockaccnt_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}*/
function ReleaselockaccntLoadComplete(){
	jQuery("#hdnChk").val("False");
	var row=jQuery("#Releaselockaccnt").jqGrid('getDataIDs');
	var col=jQuery("#Releaselockaccnt").jqGrid ('getGridParam','colModel');
	for(var i=0;i<row.length;i++){
		
		for(j=0;j<col.length;j++){
			
			 var colValue = jQuery("#Releaselockaccnt").jqGrid('getCell',row[i],"Status");
			if(colValue=="InActive")
				jQuery("#Releaselockaccnt").jqGrid('setCell',row[i],"Status","InActive",{'background-color':'#FCA29C'});
			}

		}
}
function selectRowFunction_selectAll(id,status){
	
	for(var i=0; i<id.length; i++){
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}
function selectRowFunction_selectRow(id){
	//alert("selectRow Function "+id);
if(jQuery('#jqg_Releaselockaccnt_'+id).is(':checked'))
	chkboxCheck(id);
else
	chkboxUnCheck(id);
}
function chkboxCheck(rowId)
{
	jQuery("#Releaselockaccnt").jqGrid('setCell',rowId,'checkReleaseaccntvalue','1');	
}
function chkboxUnCheck(rowId)
{
	jQuery("#Releaselockaccnt").jqGrid('setCell',rowId,'checkReleaseaccntvalue','0');
}

function frmReleaselockaccnt_beforeSubmit(){
	var status=jQuery("#hdnStatus").val();
	var data=getSelectdRowsRelease('Releaselockaccnt','ReleaseLockaccnt_checkbox','checkReleaseaccntvalue');
	var check=jQuery("#hdnChk").val();
	
	if(status==' ' || status=='' || status==null){
		status='U';
		}
	if(data=='' || data==' ' || data==null){
		alert("Select User");
		return false;
	}
	else if(status=='U' && check=="False"){
		var gridData = "";
		
		} 
	else {
	 gridData = '&selectedReleaseLockaccnts='+getSelectdRowsRelease('Releaselockaccnt','ReleaseLockaccnt_checkbox','checkReleaseaccntvalue');
		
	gridData += '&status='+status;
	}
	  return gridData ;
}
function frmReleaselockaccnt_successsCallback(result){
	 jQuery("#Releaselockaccnt").trigger("reloadGrid");
}
function getSelectdRowsRelease(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#Releaselockaccnt").jqGrid('getRowData');
	
	 var jsonArrO='[';
		for( var i = 0; i<allRows.length;i++){
			
			var row = allRows[i];
			var value = row[ckeckForSelColName];
		
			if( value != null  &&  value.trim()  != ""){			
				if(value == '1')				
				{
					//jQuery("#hdnValue").val(value);
						jsonArrO += '{';
					for(var colName in row) {
						
						var cellValue = parseJqGridCellValue(row[colName]);	
						
							if(colName == 'txtUsrm_keyid')
								{
								jsonArrO += '"'+colName +'":"' + cellValue+'"';
								}
					}
					jsonArrO +=  "},";
					
				}
				
			} 
			
		}
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		
		return jsonArrO; 
			
}
function openGrid(){
	var filtStr=jQuery.cookie("filterString");
	var statusType = jQuery("#cboStatus").val();
	//alert(statusType);
	jQuery("#hdnStatus").val(statusType);
	viewGrid("ReleaseLockAcc_input.ReleaseAcc",filtStr,statusType);
	
}
</script>
<form id="frmReleaselockaccnt">
<input type="hidden" id="hdnStatus" name="hdnStatus" value=""/>
<input type="hidden" id="hdnValue" name="hdnValue" value=""/>
<input type="hidden" id="hdnChk" name="hdnChk" value=""/>
<div id="wrapperRpt">
 	<div class="easyui-paddingbfpx" id="divtop" style="margin-top:0.5%;float: left">
              <span style="padding-left:s">
					<label  style="font-weight: 100;">Set Default Password </label>
					<input id="chksetdefaultpaswrd" type="checkbox" value="" name="chksetdefaultpaswrd" style="cursor: default;">
					
				</span>
					
			</div>
			<div id="Status" style="padding-left:16%;width: 93%">
			<select id="cboStatus"  name="cboStatus" onchange="openGrid();" style="width:100px;">
								<option value="U">All</option>	
								<option value="A">Active</option>	
								<option value="I">In Active</option>
								
								
			</select>
 	
 	
	<span style="width: 540px;" ><input id="btnChangepasswrd" class="easyui-button btn-HeightSmall" style="float: right;width:160px;" type="button" value="Change Default Password" /></span>
		</div>
		<table id="Releaselockaccnt" ></table>
			<div id="Releaselockaccntpager"></div>

	</div>
 <input type="hidden" id="mode" name="mode" value=""/>
</form>