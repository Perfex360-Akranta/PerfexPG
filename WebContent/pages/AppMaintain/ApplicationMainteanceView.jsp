<script  type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();
jQuery(document).ready(function() {
	 initialiseForm('frmAppView');
    var url = jQuery("#hiddenUrl").val();
	 fillComboBox("frmAppView","cmbType","Combo_AdminMenu.appm");
   // alert(url);
	viewGrid(url,"q=2");	  
	
});


function viewGrid(url,filterString){  
	if( validateFilterSelection(filterString))
	{
		filterString +="&frmMode="+frmMode;
	    processGridnew("ApplicationMainteanceView_input.appm", filterString, "grdtrainingCalendar", "grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}

function doubleclickGrid(id){
      var rowData = jQuery("#grdtrainingCalendar").jqGrid('getRowData',id);	
      var keyid = rowData.AdmmKeyid;
      if(keyid=="ADMM0001"){
		  LoadPopUp("DivEmployeeInactive","EmployeeInactive_input.appm",true,"1000px","490px","4px","4%", "multiSelectOk_Callback","EmpLoyee Inactive");
    	  
      }
      if(keyid=="ADMM0002"){
    	  LoadPopUp("DivLocationTransfer","Locationtransfer_input.appm",true,"1000px","490px","4px","4%", "multiSelectOk_Callback","Location Transfer");  
    	  
      }
      
      if(keyid=="ADMM0003"){
    		LoadPopUp("DivEmployeeInactive","EmployeeInactive_input.appm",true,"1000px","490px","4px","4%", "multiSelectOk_Callback","EmpLoyee Inactive");
    	  
      }
      if(keyid=="ADMM0004"){
		  LoadPopUp("DivUserReleaseLoc","userRealease_input.creat",true,"1200px","530px","4px","4%", "multiSelectOk_Callback","User Release Loc");  	  
    }

    if(keyid=="ADMM0005"){
		  LoadPopUp("DivEmployee","emp_input.emp",true,"1200px","530px","4px","4%", "multiSelectOk_Callback","Create Employee","",true,true);
    }
    
    if(keyid=="ADMM0006"){
		  LoadPopUp("DivUser","userform_input.creat",true,"1200px","530px","4px","4%", "multiSelectOk_Callback","Create User","",true,true);
  }
    
  if(keyid=="ADMM0007"){
  	  LoadPopUp("DivEmployeeUpload","EmployeeMstUpload_input.eupl",true,"1200px","530px","4px","4%", "multiSelectOk_Callback","Employee Upload");
}
    
  if(keyid=="ADMM0008"){
  	//  LoadPopUp("DivEmployeeUpload","EmployeeMstUpload_input.eupl",true,"1200px","530px","4px","4%", "multiSelectOk_Callback","Employee Upload");
}
  
  if(keyid=="ADMM0009"){
	  LoadPopUp("DivUniquePosition","UniquePositionform_input.topi",true,"1200px","530px","4px","4%", "multiSelectOk_Callback","Unique Position","",true,true);
}

  if(keyid=="ADMM0010"){
		LoadPopUp("DivSuggestionDelete","SuggestionDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Suggestion Delete");
} 
  if(keyid=="ADMM0011"){
		LoadPopUp("DivSUSADelete","SusaDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","SUSA Delete");
} 
  
  if(keyid=="ADMM0012"){
		LoadPopUp("DivKaizenDelete","KaizenDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Kaizen Delete");
} 
  if(keyid=="ADMM0013"){
		LoadPopUp("DivActionPlanDelete","ActionPlanDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Action Plan Delete");
} 
  
  if(keyid=="ADMM0014"){
		LoadPopUp("DivWhyWhyDelete","WhyWhyDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Why Why Delete");
} 
  if(keyid=="ADMM0015"){
		LoadPopUp("DivNearMissDelete","NearMissDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","NearMiss Delete");
} 
  if(keyid=="ADMM0016"){
		LoadPopUp("DivTrainingCalDelete","TrainingCalDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Training Delete");
}   
  if(keyid=="ADMM0017"){
		LoadPopUp("DivLossDelete","LossDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Loss Delete");
} 
  
  if(keyid=="ADMM0018"){
	  LoadPopUp("DivAbnClosure","AbnormalityClosure_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Abnormality Closure");
} 
  if(keyid=="ADMM0019"){
		LoadPopUp("DivAPClosure","ActionPlanClosure_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","ActionPlan Closure");
} 
  if(keyid=="ADMM0020"){
		LoadPopUp("DivKznDateUpdate","KaizenDateChange_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Kaizen Date Change");
} 

  if(keyid=="ADMM0021"){
		LoadPopUp("DivEmailEnable","employemmailreport_input.emr",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Email Enable","",true,true);
} 
  
  if(keyid=="ADMM0022"){
		LoadPopUp("DivMenuRightsAssign","NewMenuRightsAssign_input.eupl",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Menu Rights Assign","",true,true);
}
  if(keyid=="ADMM0023"){
		LoadPopUp("DivDocManagerView","DocManagerView_input.eupl",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Doc.Manager View","",true,true);
}
  if(keyid=="ADMM0024"){
		LoadPopUp("DivFIProjectdatechange","FIProjectDateChange_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","FI Project Date Change");
} 
  
  if(keyid=="ADMM0025"){
		LoadPopUp("DivFIProjectdatechange","FIProjectDateChange_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","FI Project Date Change");
}
  if(keyid=="ADMM0026"){
		LoadPopUp("DivAbnormalityDelete","AbnormalityDelete_input.appm",true,"1100px","530px","4px","4%", "multiSelectOk_Callback","Abnormality Delete");
}

}

jQuery("#btnView").click(function(){
	var Type=jQuery("#cmbType").combobox("getValue");
	
	if(Type.length==0){
		alert("Select Type");
		return false;
	}
	else{
    processGridnew("ApplicationMainteanceView_input.appm","?q=2&Type="+Type,"grdtrainingCalendar", "grdtrainingCalendarpager","PCM", "doubleclickGrid", "", "");
	}
	
	});

jQuery("#btnBack").click(function(){
	
    navigateToNextForm("ApplicationMaintainance_input.appm?q=2","");

});




</script>
<form id="frmtrainingCalendar" name="frmtrainingCalendar">
<div id='wrapperRpt'>
<label class="" style="font-weight:bold;font-size:small;color:blue;">Double click On Grid Row To View Data in Popup</label>

<div style="margin-top:10px;">
	<label style="margin-left:1px;margin-bottom: 1px;">Type</label>                       
	 </div> 
         <div>
		<input id="cmbType" name="cmbType" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
				
			</div>



	    <div style="margin-top:-5px;">
	 	<input type="button" class="easyui-button" id="btnView" name="btnView" value="View"  style="width:80px;height:21px;margin-left:230px;margin-top:-15px;"/>
				
			</div>
			
			   <div style="margin-top:-5px;">
	 	<input type="button" class="easyui-button" id="btnBack" name="btnBack" value="Back"  style="width:80px;height:21px;margin-left:330px;margin-top:-15px;"/>
				
			</div>
			
			<table id="grdtrainingCalendar" ></table>
			<div id="grdtrainingCalendarpager" ></div> 				
	
	</div>
	<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
	<input type="hidden" id='hdnProgType' value="${requestScope.frmMode}"/>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
</form>