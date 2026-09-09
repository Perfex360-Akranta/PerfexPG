<script>
jQuery(document).ready(function(){
	 initialiseForm('frmAreaTransfer'); 
	 var url = jQuery('#hiddenUrl').val();
	 formatDateBox('dteFromdate','dd-MMM-yyyy');
	 formatDateBox('dteTodate','dd-MMM-yyyy');
	 fillComboBox("frmAreaTransfer","cmbDMT","sectionCombo.commonFilter");
	 fillComboBox("frmAreaTransfer","cmbJH","cellCombo.commonFilter");
	 fillComboBox("frmAreaTransfer","cmbEmployee","employee.commonFilter");
	 fillComboBox("frmAreaTransfer", "cmbRole","employeeRole.commonFilter");
	 viewGrid(url,"q=2");
	});

function viewGrid(url,dataString){  
	processGridnew(url, dataString, "AreaTransfer", "Pager", "", "");
	return true;
}

jQuery("#btnview").click(function(){
	var DMT=jQuery("#cmbDMT").combobox("getValue");
	var JH=jQuery("#cmbJH").combobox("getValue");
	var EmpId=jQuery("#cmbEmployee").combobox("getValue");
    var flid=getFieldValue('cmbDMT','frmAreaTransfer');
	processAjaxCalls("getEmprolelevel.appm?&flid="+flid,flid,'getrolelevel_successCallBack');	
	processGridnew("AreaTransfer_input.appm","?q=2&DMT="+DMT+"&JH="+JH+"&EmpId="+EmpId,"AreaTransfer","Pager","","");
});

function getrolelevel_successCallBack(result){
	
	var level=result.successData.level;
	var location=result.successData.location;	
	var flid=getFieldValue('cmbDMT','frmAreaTransfer');
	var roleid='';
    //setFieldValue("txtFrlFnlnKeyid",flid);
	//setFieldValue("txtFrlLevel",level);
	//jQuery("#txtLocation").val(location);	
	processGridnew("RoleList_input.appm","?q=2&flid="+flid+"&level="+level,"RoleList","pagers","","","","Load_Complete","","");
//	processGridnew("empteam_input.roleteam","?q=2&roleid="+roleid+"&flid="+flid+"&level="+level,"empteamgrid","","","","","empteamgrid_onComplete");
//	processGridnew("roleall_input.roleteam","?q=2&flid="+flid+"&level="+level,"rolegrid","rolepager","","","","Load_Complete","","");
//	processGridnew("empall_input.roleteam","?q=2&flid="+flid+"&roleid="+roleid+"&level="+level+"&location="+location,"empgrid","emppager","","","","Load_Complete","","");

}

function frmAreaTransfer_beforeSubmit(result){
	
	return true;
}

jQuery("#btnRefresh").click(function(){
	var url=jQuery('#hiddenUrl').val();
	processGridnew(url,"q=2", "AreaTransfer","Pager", "", "");
	jQuery("#dteFromdate").datebox("clear");
	jQuery("#dteTodate").datebox("clear");
	jQuery("#cmbDMT").combobox("clear");
	jQuery("#cmbJH").combobox("clear");
});


</script>
<form id="frmAreaTransfer">
<div id="wrapperRpt" style="margin-top:20px;">
<table style="margin-top:-16px;">
		<tbody>
		<tr>
		
		<td>
		<div>
	<label style="margin-left:1px;margin-bottom: 1px;">DMT</label>                       
	                 </div> 
			    <div style="margin-left:1px;margin-bottom: 5px;">
			    
		        <input id="cmbDMT" name="cmbDMT" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			  </div>
			 </td>
			 
			 
			 	 <td>
			 <div>
	<label style="margin-left:20px;margin-bottom: 1px;">JH</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom: 5px;">
			    
		        <input id="cmbJH" name="cmbJH" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			        </div>
			 </td>
		
<td>
	<div style="margin-left:20px; margin-top:-3px;"><label class="mandatory-lbl">Employee Name</label></div>
				  <div style="margin-left:20px;">
				  <input id="cmbEmployee" name="cmbEmployee" class="easyui-combobox" style="width:220px;"/>
				  </div>


</td>

<td>
	<div style="margin-left:20px; margin-top:-3px;"><label>Employee Role</label></div>
				  <div style="margin-left:20px;">
				  <input id="cmbRole" name="cmbRole" class="easyui-combobox" style="width:220px;"/>
				  </div>


</td>
			  
			   <td>
			    <div style="margin-left:10px;margin-bottom:-10px;">
			         <input type="button"  class="easyui-button" value="View" id="btnview" style="height: 24px; width : 87px;">
		  </div>
			  </td>
			  <td>
			    <div style="margin-left:10px;margin-bottom:-10px;">
			         <input type="button"  class="easyui-button" value="Refresh" id="btnRefresh" style="height: 24px; width : 87px;">
		  </div>
			  </td>
		  </tr>
		 </table>
	
		 
<table id="AreaTransfer"></table>
<div id="Pager"></div>

<div>
<table id="RoleList">
</table>
<div id="Pagers"></div>


</div>
</form>