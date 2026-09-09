<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
jQuery(document).ready(function(){	
	initialiseForm('frmAddRoll');
	fillComboBox("frmAddRoll","cmbArulRoleid","Combo_UserRoll.creat");
	if((screen.width <= 1024)||(screen.width<=1280) )
		{
		jQuery("#combo").css("margin-left","27px");
		jQuery("#grid").addClass("left");
		}
	else 
		{
		jQuery("#combo").css("margin-left","13px");
		jQuery("#grid").css("margin-left","17px");
		}
	var urlUserKeyid=jQuery('#hdnArulUserid').val();
	//alert(urlUserKeyid);
	var dataString = "?q=1"; 
	viewGrid(urlUserKeyid,dataString);
	
		
});
function viewGrid(urlUserKeyid,filterString)
{
	//alert(filterString);
	
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "";
		processGridnew("AddRoll_input.creat?userId="+urlUserKeyid+"&fromUserform=false",filterString,"userGrid","pager2","","","","loadComplete");
		return true;
	}	
}
function validateFilterSelection(filterString){
	return  true;
}
function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#userGrid").jqGrid('getRowData',rowid );
	//alert(rowData.LOGINID);
	
}
function button_AddRoll(id, options, rowObject)
{		
	//nalert("id="+id);
	var rowId = options.rowId;
	return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
}
function removeOperator(rowId){	
   //alert("rowId="+rowId);
	var rowData = jQuery("#userGrid").jqGrid('getRowData',rowId);
	var keyId = rowData.ROLLID;
	//alert(keyId);
	var conFdelete = confirm("Do You Want To Delete");
	if(conFdelete){
		var userId = jQuery("#hdnArulUserid").val();
		if( keyId != null && keyId.length > 0  ){	
			processAjaxCalls("userRoll_delete.creat","&Id="+keyId+"&rowId="+rowId +"&userId="+userId, 'UserRoll_successCallBack','UserRoll_errorCallBack');
			 //deleteRecord('frmAddRoll','userRoll_delete.creat?&Id='+keyId+'&rowId='+rowId);
			//jQuery("#batchGrid").delRowData(rowId);
		}
	}
}  
function UserRoll_successCallBack(result)
{
	//alert('in success delete');
	//jQuery("#userGrid").trigger("reloadGrid");
	var urlUserKeyid=jQuery('#hdnArulUserid').val();
	//alert(urlUserKeyid);
	var dataString = "?q=1"; 
	viewGrid(urlUserKeyid,dataString);
	alert(result.successData.msg);
	
}
	function UserRoll_errorCallBack()
	{
		}
function frmAddRoll_successsCallback(result)
{
	//alert('in success call back');
	jQuery('#hdnArulUserid').val(result.successData.keyId);
	jQuery("#userGrid").trigger("reloadGrid");
	}
function frmAddRoll_errorCallBack()
{
	
}
function frmAddRoll_deleteSuccessCallback()
{
	//alert('in success call back');
	jQuery("#userGrid").trigger("reloadGrid");
	
	}
jQuery('#btnUserAddRoll').click(function ()
		{
			//alert('on click');
			var roleId=jQuery("#cmbArulRoleid").combobox("getValue"); 
			var row= jQuery("#userGrid").jqGrid('getDataIDs');
			//alert(row.length);
			var status=true;
			for(var i=1;i<=row.length;i++){
			var rowData = jQuery("#userGrid").jqGrid('getRowData',i);
			//alert(rowData.ROLLID);
			
			if(roleId==rowData.ROLLID)
				{
				status=false;
				alert('User Role Already Exists');
				break;
				}
			}
			if(status==true)
			{
			//alert('save');
			
			//var userId=jQuery("#userId").val();
			//alert(userId);
			saveForm('frmAddRoll','userRoll_save.creat');
			}
			
			//processGridnew("entAddbatch_input.entbatch",'?q=2&bflkBachKeyid='+bflkBachKeyid,"batchGrid","pager2","","","","loadComplete");
		});

function loadComplete(){ 
jQuery(".ui-paging-info").css("font-size","10px");
if(screen.width <= 1024)
	jQuery( "#userGrid" ).setGridWidth(330);	

}

/*
jQuery('#btnMenuRoleRights').click(function(event){
	var roleId=jQuery("#cmbArulRoleid").combobox("getValue"); 
	var dataString = '?q=2&openFrom=userCreation';
	if(roleId != null && roleId != '' && roleId != ' ')
	 {
		 dataString += '&roleId='+roleId;
	 }	 
	LoadPopUp("divAddMenuRights","menu_rights.menuTree"+dataString, true,"95%","85%","20px","0", "roleMenuOk_Callback","Role Menu Rights");
});
*/
</script>
<style>
.left
{
margin-left:30px;
}
</style>
<form id="frmAddRoll" name="frmAddRoll">
	<div title="" >
		<table width="100%" align="center">
			<tr>
				<td>
					<div>					     	
				   		<div id="combo" style="float:center;">
						   	<table width="100%" align="center">
							   	<tr>
								   	<td style="">
								   		<div  class="easyui-paddingbfpx"><label class="mandatory-lbl">Role</label></div>
								   		<div  class="easyui-paddingbfpx"><span><input id="cmbArulRoleid" name="cmbArulRoleid" type="text" class="easyui-combobox" style="width:200px;" value="" /></span>
								   		
			<span><input id="btnUserAddRoll" name="btnUserAddRoll" type="button" class="easyui-button"  value="Add"  style="width:100px; height:25px;"/></span>
		<!-- 	<span><input id="btnMenuRoleRights" name="btnMenuRoleRights" type="button" class="easyui-button"  value="Menu Rights"  style="width:100px; height:25px;"/></span>  -->
			</div>
		
 									</td>
 								</tr>
								
							</table>	
						</div>
					</div>		
				</td>
			</tr>
		</table>
		
		<div  id="grid" style="width:45%;height:100px;">		
						<table id="userGrid" >
							<tr><td><td/></tr></table>
							<div id="pager2"></div>
		</div>
		<input type="hidden" id="hdnArulUserid" name="hdnArulUserid" value="${requestScope.userId}" />
	</div>

</form>