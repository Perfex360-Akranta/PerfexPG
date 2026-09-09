
<script type="text/javascript">

	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmEmpActiveInactiveGrd');
		jQuery('#submitForm').val('frmEmpActiveInactiveGrd');
		formatDateBox('dteUsrm_validtill','dd-MMM-yyyy');
		fillWithCurrentDate('dteUsrm_validtill');
		var url = jQuery('#hiddenUrl').val();		
		var dataString = "?q=1";
		viewGrid(url, dataString);
	
	});

	jQuery("#btnSave").click(function() {		
		var loginId="";
		var jsonArrO="";
		var usrGrdIds = jQuery("#grdUser").jqGrid('getDataIDs');	
		var cm = jQuery("#grdUser").jqGrid("getGridParam", "colModel");
		for(var i=1;i<=usrGrdIds.length;i++)	
		{	
			rowid=usrGrdIds[i-1];
			if(jQuery('#chkBox_'+rowid).is(':checked')){
				
				var empKeyid = jQuery("#grdUser").jqGrid('getCell',rowid,"USERKEYID");
			   // alert(empKeyid);
			    jsonArrO += empKeyid +";";
			}			
	
		}				
		saveForm('frmEmpActiveInactiveGrd','employeeActive_save.eupl?jsonArrO='+jsonArrO);
		
	});

	
	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {
			processGridnew(url, filterString, "grdUser", "grdUserPager","","");
			return true;
		}
	}
	
	function validateFilterSelection(filterString) {
		return true;
	}
	
	function dteUsrm_validtill_onSelect(date){			
		/*var currentDate = getServerDateTime();
        alert(date+""+currentDate);
		if( date <= currentDate)
		{					
			jQuery('#dteUsrm_validtill').datebox('clear');
			showValidationErrorMsg('dteUsrm_validtill','Should Not Exceed Current Date');	
		}
		else
			clearValidationErrorMsg('dteUsrm_validtill');*/
			
			var currentDate = getServerDateTime();
			var ValidTillDate = jQuery('#dteUsrm_validtill').datebox("getValue");
			if(convertStringToDate(ValidTillDate) < currentDate)
			{
				jQuery('#dteUsrm_validtill').datebox('clear');
				showValidationErrorMsg('dteUsrm_validtill','Should Not Exceed Current Date');	
				return false;
			}
			else{
				clearValidationErrorMsg('dteUsrm_validtill');
			}
			
	}
	
	  jQuery("#btnInactive").click(function()
			  {
		  
		  alert('clicked');
		  
		  //LoadPopUp("DivUserInactive","EmployeeInactive_input.appm",true,"1000px","490px","4px","4%", "multiSelectOk_Callback","Employee Inactive");
			//"95%","90%","3%","1%"
		  LoadPopUp("DivUserInactive","EmployeeInactive_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Employee Inactive");
			  });
jQuery("#btnUserInactive").click(function(){
		  
		  LoadPopUp("DivUserInactive","UserAupInactive_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Employee Inactive");
					
			  });
			  
	 jQuery("#btnLocationTransfer").click(function(){
			
		  LoadPopUp("DivLocationTransfer","Locationtransfer_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Location Transfer");			 
		  
	  });
	  
	 jQuery("#btnEmpActive").click(function(){
		 
	  LoadPopUp("DivEmpActive","EmployeeActive_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Employee Active");
		 
	 });
	 
	 jQuery("#btnUserReleaseLoc").click(function(){
		 
		  LoadPopUp("DivUserReleaseLoc","userRealease_input.creat",true,"95%","90%","3%","1%", "multiSelectOk_Callback","User Release Loc");
			 
		 });
		 
	 jQuery("#btnEmployee").click(function(){
		 
		  LoadPopUp("DivEmployee","emp_input.emp",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Create Employee","",true,true);
			 
		 }); 
	 
	 jQuery("#btnUser").click(function(){
		 
		  LoadPopUp("DivUser","userform_input.creat",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Create User","",true,true);
			 
		 }); 
	 
	 jQuery("#btnEmployeeUpload").click(function(){
		 
	  LoadPopUp("DivEmployeeUpload","EmployeeMstUpload_input.eupl",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Employee Upload");
			 
		 }); 
	 
	 jQuery("#btnUniquePosition").click(function(){
		 
	      LoadPopUp("DivUniquePosition","UniquePositionform_input.topi",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Unique Position","",true,true);
					 
				 }); 
		  
	 
	 jQuery("#btnTrade").click(function(){
		 
      LoadPopUp("DivRoleMapping","roleteamall_input.roleteam",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Rol Mapping","",true,true);//"EmployeeTrade_input.appm",
				 
			 }); 
	  
jQuery("#btnKznUpdate").click(function(){

	LoadPopUp("DivKznDateUpdate","KaizenDateChange_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Kaizen Date Change");

});	

/* jQuery("#btnSusaDelete").click(function(){

	LoadPopUp("DivSUSADelete","SusaDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","SUSA Delete");

}); */

jQuery("#btnSusaDelete").click(function(){
	
	LoadPopUp("DivTrainingCalModify","NewTrainingCalenderEditing_input.ntrc?mode=modify",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Training Calendar Modify");    
});


jQuery("#btnTrainingDelete").click(function(){

	LoadPopUp("DivTrainingCalDelete","TrainingCalDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Training Delete");

});	

jQuery("#btnAbnormalityDelete").click(function(){111111

	LoadPopUp("DivAbnormalityDelete","AbnormalityDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Abnormality Delete");

});	

jQuery("#btnSuggestionDelete").click(function(){

	LoadPopUp("DivSuggestionDelete","SuggestionDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Suggestion Delete");

});	

jQuery("#btnKaizenDelete").click(function(){

	LoadPopUp("DivKaizenDelete","KaizenDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Kaizen Delete");

});

jQuery("#btnWhyDelete").click(function(){

	LoadPopUp("DivWhyWhyDelete","WhyWhyDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Why Why Delete");

});


jQuery("#btnLossDelete").click(function(){

	LoadPopUp("DivLossDelete","LossDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Loss Delete");

});

//added by priyanka
jQuery("#btnKaizenApprovalDelete").click(function(){

	//LoadPopUp("DivKaizenApprovalDelete","KaizenApprovalDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Kaizen Approval Delete");
	navigateToNextForm("KaizenApprovalDelete_input.appm?q=2","Revoke Kaizen Work Flow");

});

jQuery("#btnNearMissDelete").click(function(){

	LoadPopUp("DivNearMissDelete","NearMissDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Near Miss Delete");

});

jQuery("#btnActionDelete").click(function(){

	LoadPopUp("DivActionPlanDelete","ActionPlanDelete_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Action Plan Delete");

});

jQuery("#btnAbnClosureUpdate").click(function(){

LoadPopUp("DivAbnClosure","AbnormalityClosure_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Abnormality Closure");

});

jQuery("#btnAPClosureUpdate").click(function(){

	LoadPopUp("DivAPClosure","ActionPlanClosure_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","ActionPlan Closure");

	});
	
jQuery("#btnEmailEnable").click(function(){

	LoadPopUp("DivEmailEnable","employemmailreport_input.emr",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Email Enable","",true,true);

 });

jQuery("#btnMenuRightsAssign").click(function(){

	LoadPopUp("DivMenuRightsAssign","NewMenuRightsAssign_input.eupl",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Menu Rights Assign","",true,true);

 });	
 
jQuery("#btnDocManagerView").click(function(){

	LoadPopUp("DivDocManagerView","DocManagerView_input.eupl",true,"95%","90%","3%","1%", "multiSelectOk_Callback","Doc.Manager View","",true,true);

 });	
 
jQuery("#btnFIProjectDateChange").click(function(){

	LoadPopUp("DivFIProjectdatechange","FIProjectDateChange_input.appm",true,"95%","90%","3%","1%", "multiSelectOk_Callback","FI Project Date Change");

 });
 
      jQuery("#btnAppMainteanceGrid").click(function(){
    
	   navigateToNextForm("ApplicationMainteanceView_input.appm?q=2","");
	     
	  });
	  
	  jQuery("#btnAppMainteancePage").click(function(){
		  
		    navigateToNextForm("ApplicationMaintainance_input.appm?q=2","");
		     
		  });
	  
	  
	  jQuery("#btnJHTransfer").click(function(){
	         LoadPopUp("DivTransfer","AreaTransfer_input.appm",true,"1200px","530px","4px","4%", "multiSelectOk_Callback","JH & DMT Transfer","",true,true);	 
		  }); 

</script>
<style>
light-font .breadcrumb-item + .breadcrumb-item::before {
color: red; }
.light-font .breadcrumb-item.active {
color: #cfd8dc; }
</style>
<form id="frmEmpActiveInactiveGrd">
	<div id="wrapperRpt" style="margin-top: 0px; margin-left: 90px">
<!-- 		<div>
		
			<span style="vertical-align: top;"> 
					<input type="button" class="easyui-button" id="btnSave" name="btnActive" value="Active" style="height: 20px;" />
				</span>
				
				<span style="padding-left: 10px; padding-left: 10px\9; vertical-align: top;"> 
					<input type="button" class="easyui-button" id="btnInactive" name="btnInactive" value="InActive" style="height: 20px;" />
				</span>
				
				<span style="padding-left: 40px; padding-left: 40px\9; vertical-align: top;">
				 <label class="">Validtill Date</label>	 
			   <input type="text" class="easyui-text"  id="dteUsrm_validtill" name="dteUsrm_validtill" maxlength="10"   style=" width : 100px;height:25px; text-align:left;" value=""/>

				</span>
			
		</div>
		<table id="grdUser"></table>
		<div id="grdUserPager"></div> -->
	<!-- 	<ul class="breadcrumbNew" id="header1" style="margin-top:0px;margin-left:0px;width:83%;height:30px;display: none;background-color:#666699;">

<li><b>Employee & User Related</b></li>
</ul> -->
<!-- <div style="background-color:#666699;height:40px;margin-left:-90px;margin-top:0px;width:143%">
		<label style="color:#fff;"><b>Employee & User Related</b></label>
	</div> -->

  <div style="background-color:#666699;height:40px;margin-left:-90px;margin-top:0px;width:200%"><!--143  -->
		<label style="color:#fff;"><b>Employee & User Related</b></label>
		<span style="margin-left:50px;">
		<input type="button" class="easyui-button" value ="Page View" id="btnAppMainteancePage" style="height:40px"/>
		</span>
		<span style="margin-left:10px;">
		<input type="button" class="easyui-button" value ="Grid View" id="btnAppMainteanceGrid" style="height:40px"/>		
		</span>	
	</div>
	
	
	
	<table>

		  <tr>
 <td>
<div style="margin-left:-130px;"><!--150  -->
<img id="btnInactive" name="btnInactive" src="images/menu-icon/UserEmp.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="User And Employee Inactive"/>
</div>
</td>
<!-- <td>
<div style="margin-left:-95px;">
<img id="btnUserInactive" name="btnUserInactive" src="images/menu-icon/UserEmp.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="User  Inactive"/>
</div>
</td> -->
 <td>
<div style="margin-left:-50px;">
<img id="btnLocationTransfer" name="btnLocationTransfer" src="images/menu-icon/LocationTransfer.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Employee Transfer"/>
</div>
</td>
 <td>
<div style="margin-left:-40px;">
<img id="btnEmpActive" name="btnEmpActive" src="images/menu-icon/Active.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Employee Active"/>
</div>
</td>

<td>
<div style="margin-left:-50px;">
<img id="btnUserReleaseLoc" name="btnUserReleaseLoc" src="images/menu-icon/UserReleaseLock.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="User Release Loc"/>
</div>
</td>

<td>
<div style="margin-left:-40px;">
<img id="btnEmployee" name="btnEmployee" src="images/menu-icon/CreateEmployee.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Create Employee"/>
</div>
</td>

<td>
<div style="margin-left:-40px;">
<img id="btnUser" name="btnUser" src="images/menu-icon/CreateUser.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Create User"/>
</div>
</td>

<td>
<div style="margin-left:-50px;">
<img id="btnEmployeeUpload" name="btnEmployeeUpload" src="images/menu-icon/EmployeeUpload.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Employee Upload"/>
</div>
</td>

<td>
<div style="margin-left:-40px;">
<img id="btnTrade" name="btnTrade" src="images/menu-icon/UserRole.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Trade"/>
</div>
</td>

<td>
<div style="margin-left:-40px;">
<img id="btnUniquePosition" name="btnUniquePosition" src="images/menu-icon/UniquePosition.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Unique Position"/>
</div>
</td>

</tr>
</table>	
<div style="background-color:#666633;height:40px;margin-left:-90px;margin-top:0px;width:143%">
<label style="color:#fff;"><b>Transaction Delete</b></label>
	</div>	
	<table>
	<tr>
	<td>
<div style="margin-left:-120px;">
<img id="btnAbnormalityDelete" name="btnAbnormalityDelete" src="images/menu-icon/AbnDelete.png" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Abnormality Delete"/>
</div>
</td>
	<td>
<div style="margin-left:-30px;">
<img id="btnSuggestionDelete" name="btnSuggestionDelete" src="images/menu-icon/Sug.png" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="SUggestion Delete"/>
</div>
</td>
<!-- <td>
<div style="margin-left:-30px;">
<img id="btnSusaDelete" name="btnSusaDelete" src="images/menu-icon/Susa.png" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Susa Delete"/>
</div>
</td> -->
<td>
<div style="margin-left:-30px;">
<img id="btnSusaDelete" name="btnSusaDelete" src="images/menu-icon/TrainingCalEdit.png" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Training Calendar Modify"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnKaizenDelete" name="btnKaizenDelete" src="images/menu-icon/Kaizen.png" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Kaizen Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnActionDelete" name="btnActionDelete" src="images/menu-icon/ActionPlan.png" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="ActionPlan Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnWhyDelete" name="btnWhyDelete" src="images/menu-icon/WHY-WHY.jpg" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Why-Why Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnNearMissDelete" name="btnNearMissDelete" src="images/menu-icon/NearMissDelete.jpg" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="NearMiss Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnTrainingDelete" name="btnTrainingDelete" src="images/menu-icon/Training.jpg" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Training Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnLossDelete" name="btnLossDelete" src="images/menu-icon/LossDelete.jpg" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Loss Delete"/>
</div>
</td>
<!-- added here by priyanka  -->
<td>
<div style="margin-left:-30px;">
<img id="btnKaizenApprovalDelete" name="btnKaizenApprovalDelete" src="images/menu-icon/RevokeKaizenWorkFlown.png" width="130px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Kaizen Approval Delete"/>
</div>
</td>
<!-- delete  -->
</tr>
	
	</table>
	<div style="background-color:#cc9900;height:40px;margin-left:-90px;margin-top:20px;width:143%">
		<label style="color:#fff;"><b>Data Update</b></label>
	</div>	
	<table>
	<tr><td>
	<div style="margin-left:-120px;">
<img id="btnAbnClosureUpdate" name="btnAbnClosureUpdate" src="images/menu-icon/ABC.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="AbnormalityClosure"/>
	</div>
	</td>
	<td>
	<div style="margin-left:-30px;">
<img id="btnAPClosureUpdate" name="btnAPClosureUpdate" src="images/menu-icon/APC.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="ActionPlanClosure"/>
	</div>
	</td>
	<td>
	<div style="margin-left:-30px;">
<img id="btnKznUpdate" name="btnKznUpdate" src="images/menu-icon/Kzn.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Kaizen Date Change"/>
	</div>
	</td>
	
	<td>
	<div style="margin-left:-30px;">
<img id="btnEmailEnable" name="btnEmailEnable" src="images/menu-icon/EmployeeEmailEnable.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Email Enable"/>
	</div>
	</td>
	
 <td>
	<div style="margin-left:-30px;">
<img id="btnMenuRightsAssign" name="btnMenuRightsAssign" src="images/menu-icon/MenuRightsAssign.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Email Enable"/>
	</div>
	</td>
	
  <td>
	<div style="margin-left:-30px;">
  <img id="btnDocManagerView" name="btnDocManagerView" src="images/menu-icon/DocManagerView.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Document Manager View"/>
	</div>
	</td>
	
  <td>
 <div style="margin-left:-30px;">
  <img id="btnFIProjectDateChange" name="btnFIProjectDateChange" src="images/menu-icon/FIProjectDateChange.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="FI Project Date Change"/>
  </div>
 </td>
	
 <td>
	<div style="margin-left:-30px;">
  <img id="btnJHTransfer" name="btnJHTransfer" src="images/menu-icon/JHDMTTransafer.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="JH & DMT Transfer"/>
	</div>
</td>
	
	</tr>
	
	</table>
	</div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
