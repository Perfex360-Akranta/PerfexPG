<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){	
	var url = "safetyPopup_input.safetyinspect";
	var uri = "safetyPopupall_input.safetyinspect";
	initialiseForm('frmPlantPopup');	
	jQuery('#submitForm').val('frmPlantPopup');
	formatDateBox('dteTargetDate','DD-MON-YYY');
	viewEmpGrid(url,"&q=2");
    viewGrid(uri,"&q=2");
    

});

function chkbox_Employee(id, options, rowObject)
{ 
	var rowId = options.rowId;	
	//alert(rowObject[2]);
	
	return '<input id="employee_checkbox" name="employee_checkbox" '+ (rowObject[2]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{

	jQuery("#grdAddEmplink").jqGrid('setCell',rowId,'checkempvalue','1');	
}
function chkboxUnCheck(rowId)
{

	jQuery("#grdAddEmplink").jqGrid('setCell',rowId,'checkempvalue','0');
}

function viewEmpGrid(url,filterstring){
processGridnew(url,filterstring,"empgrid","emppager","","","","Load_Complete","","");
}
function viewGrid(uri,filterstring){
	processGridnew(uri,filterstring,"empteamgrid","empteampager","","","","Loadteam_Complete","","");
	}	
</script>
<form name="frmPlantPopup" id="frmPlantPopup">	
	<div style="padding-left:20px;">
		<div id="divRoleMapping">			
			<div style="float:left;position:relative;width:890px;">	
				<div style="float:left; margin-left:2px;width:auto;">	
				<div>
					<div class="easyui-paddingbfpx" >
					    <label>Rsponsibility</label>
						<span style="padding-left:145px;"><label>TargetDate</label></span>
					</div>
					<div class="easyui-paddingbfpx"> 
					    <input class="easyui-combo" id="cmbResponsibility" name="cmbResponsibility" style="width:200px;"/>
						<span style="padding-left:18px;">
						    <input class="easyui-datebox" id="dteTargetDate" name="dteTargetDate" style=""/>
						</span>
					</div>
		 		</div>
					
					<div>
						<span style="padding-left:10px;"><input type="button" title="Save Sub Process" value="Save"  class="easyui-button" style="height : 20px; width : 35px;" id="btnSave"></span>				
					</div>
					<div>						
						<table id='empteamgrid'></table>
						<div id='empteampager'>	</div>						
					</div>
				</div>
				
				<div id="divAddRoleEmp">				
					<div style="float:left;margin-left:1px;" >				
						<div id="divR" style="margin-top:240px;">		
							<div class="easyui-paddingbfpx" style="margin-left:2px;margin-top:10px;">
		 						<span><input type="button" title="Add Employee" value="<<" class="easyui-button" style="height : 21px; width : 30px;" id="btnAdd"> </span>
							</div>	
							<div class="easyui-paddingbfpx" style="margin-left:2px;margin-top:10px;">
		 						<span><input type="button" title="Remove Employee" value=">>" class="easyui-button" style="height : 21px; width : 30px;" id="btnRemove"></span> 
							</div>	
							
						</div>	
					</div>				
					<div style="float:left;padding-left:10px;">				
					<div id="tabRoleTm" class="easyui-tabs" style="width:360px; height : auto;">					  
		   					
		   				<div title="Employee" style="padding:4px;">
		   					<div>
								<table id='empgrid'></table>
								<div id='emppager'>	</div>
							</div>		
		   				</div>
					</div>
				</div>
			</div>
		</div>						
	</div>
	  </div>
	<input type="hidden" id="mode" name="mode" value="" />	
</form>