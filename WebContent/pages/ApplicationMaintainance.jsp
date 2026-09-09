
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
	
	jQuery("#btnInactive").click(function() {		
		var loginId="";
		var jsonArrO="";
		var usrGrdIds = jQuery("#grdUser").jqGrid('getDataIDs');	
		var cm = jQuery("#grdUser").jqGrid("getGridParam", "colModel");
		var ValidTillDate=jQuery("#dteUsrm_validtill").datebox("getValue");
      //  alert(ValidTillDate);
		for(var i=1;i<=usrGrdIds.length;i++){	
			rowid=usrGrdIds[i-1];
			if(jQuery('#chkBox_'+rowid).is(':checked')){
			 if(ValidTillDate.length==0){
				alert("Select the ValidTill Date");
				return false;
			}
			}
			else{
				var empKeyid = jQuery("#grdUser").jqGrid('getCell',rowid,"USERKEYID");
			//	jsonArrO += empKeyid +";"; 
			//	alert(jsonArrO);
			}
			saveForm('frmEmpActiveInactiveGrd','employeeInActive_save.eupl?empKeyid='+empKeyid+"&ValidTillDate="+ValidTillDate);

		}				
		
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
	
	  jQuery("#btnLocationTransfer").click(function(){
		  //alert("inside");
		//  navigateToNextForm("Locationtransfer_input.appm","KPI View","","");
		  var JH="1";
		  LoadPopUp("DivLocationTransfer","Locationtransfer_input.appm",true,"800px","490px","4px","4%", "multiSelectOk_Callback","Location Transfer");

	//LoadPopUp("Location TransferPopup", "Locationtransfer_input.appm",true,"99%","95%","1%","0%", "empKpiOk_Callback","Location-Transfer",false);
			 
		  
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
<div style="background-color:#666699;height:40px;margin-left:-90px;margin-top:0px;width:143%">
		<label style="color:#fff;"><b>Employee & User Related</b></label>
	</div>
	<table>

		  <tr>
 <td>
<div style="margin-left:-150px;">
<img id="btnInactive" name="btnInactive" src="images/menu-icon/Inactive.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Employee Inactive"/>
</div>
</td>
 <td>
<div style="margin-left:-70px;">
<img id="btnLocationTransfer" name="btnLocationTransfer" src="images/menu-icon/LocationTransfer.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Employee Transfer"/>
</div>
</td>
 <td>
<div style="margin-left:-70px;">
<img id="btnDeptTransfer" name="btnDeptTransfer" src="images/menu-icon/Active.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Employee Active"/>
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
<img id="btnSuggestionDelete" name="btnSuggestionDelete" src="images/menu-icon/Sug.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="SUggestion Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnSusaDelete" name="btnSusaDelete" src="images/menu-icon/Susa.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Susa Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnKaizenDelete" name="btnKaizenDelete" src="images/menu-icon/Kaizen.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Kaizen Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnActionDelete" name="btnActionDelete" src="images/menu-icon/ActionPlan.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="ActionPlan Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnWhyDelete" name="btnWhyDelete" src="images/menu-icon/WHY-WHY.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Why-Why Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnNearMissDelete" name="btnNearMissDelete" src="images/menu-icon/NearMissDelete.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="NearMiss Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnTrainingDelete" name="btnTrainingDelete" src="images/menu-icon/Training.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Training Delete"/>
</div>
</td>
<td>
<div style="margin-left:-30px;">
<img id="btnLossDelete" name="btnLossDelete" src="images/menu-icon/LossDelete.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Loss Delete"/>
</div>
</td>
	</tr>
	
	</table>
	<div style="background-color:#cc9900;height:40px;margin-left:-90px;margin-top:20px;width:143%">
		<label style="color:#fff;"><b>Data Update</b></label>
	</div>	
	<table>
	<tr><td>
	<div style="margin-left:-120px;">
<img id="btnAbnUpdate" name="btnAbnUpdate" src="images/menu-icon/ABC.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="AbnormalityClosure"/>
	</div>
	</td>
	<td>
	<div style="margin-left:-30px;">
<img id="btnAPUpdate" name="btnAPUpdate" src="images/menu-icon/APC.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="ActionPlanClosure"/>
	</div>
	</td>
	<td>
	<div style="margin-left:-30px;">
<img id="btnKznUpdate" name="btnKznUpdate" src="images/menu-icon/Kzn.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Kaizen Date Change"/>
	</div>
	</td>
	</tr>
	
	</table>
	</div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
