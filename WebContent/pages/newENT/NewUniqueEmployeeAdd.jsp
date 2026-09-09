<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"><!--
	jQuery(document).ready(function() {
		initialiseForm("frmNewUniquePosition");
		jQuery("#submitForm").val("frmNewUniquePosition");
		fillComboBox("frmNewUniquePosition","cmbRole","employeeRole.commonFilter");
		fillComboBox("frmNewUniquePosition","cmbProgsEmployee","employee.commonFilter");
	    fillComboBox("frmNewUniquePosition","cmbProgPillargroup","pillargroup.mom");
		fillComboBox("frmNewUniquePosition", "cmbLocation","companyCombo.commonFilter"); 
		//fillComboBox("frmNewUniquePosition", "cmbspu","");
		var url = jQuery("#hiddenUrl").val();
		var flid=jQuery("#hdnflid").val();
		//alert(jQuery("#hdnuniq").val());
		//alert("The flid"+flid);
	//	alert(jQuery("#hdncalendar").val());
		var keyid=jQuery("#hdncalendar").val();
		//jQuery("#level").combobox('clear');
	  //  alert(keyid);
	    jQuery("#cmbLocation").combobox("disable");
		viewGrid(url, "q=2&flid="+flid);
		fillComboBox("frmNewUniquePosition","cmbTrainingEmployee","employee.commonFilter");
		fillComboBox("frmNewUniquePosition","cmbRole","employeeRole.commonFilter");
		fillComboBox("frmNewUniquePosition","cmbTrainingPillarGrp","pillargroup.mom");
		fillComboBox("frmNewUniquePosition","cmbSession","Sessionlist.ntrc?&calkeyid="+keyid);
		viewGrid(url, "q=2");
		
		
		  jQuery("#btnSave").click(function () {
			    var sessionid = jQuery("#cmbSession").combobox("getValue");
			    var keyid     = jQuery("#hdncalendar").val();

			    if (sessionid && sessionid.length !== 0) {

			        // 1. Get your JSON/array string
			        var paramconvertArr = convertJsonArr(); 
			        // This can be "[]", or JSON string like `[{"selctVal":" ",...}]`

			        // 2. Encode each parameter value for safe use in URL
			        var url =
			            "entAddEmployee_save.ntrc"
			            + "?sessionid="      + encodeURIComponent(sessionid)
			            + "&keyid="          + encodeURIComponent(keyid)
			            + "&paramconvertArr=" + encodeURIComponent(paramconvertArr);

			        // Optional: debug
			        // console.log("Final URL:", url);

			        // 3. Submit
			        saveForm("frmNewUniquePosition", url);

			    } else {
			        alert("Without select Session Unable to Save Employee!!!! ");
			        return false;
			    }
			});



		  // -- chnaging for save vignesh ------------------------//
		  jQuery("#btnClose").click(function(){
			  closePopUpDialoge("divUniquePosAdd");
			  
			  
		  });
		  
		  
			
			jQuery("#btnView").click(function() {

					var url = jQuery('#hiddenUrl').val();
					var ds="q=2";
					var  dataString="q=2";
					var cmbLevelVal=getFieldValue("level");
					var EmployeeType=getFieldValue("cmbEmployeetype");
					//alert("EmployeeType"+EmployeeType)
					var EmployeeGender=getFieldValue("cmbEmpmGender");
					
					
					var flid=jQuery("#hdnflid").val();
				    var fnLocationVal =jQuery("#cmbLocation").combobox('getValue');
				   // alert(jQuery("#cmbLocation").combobox('getValue'));
				 //  alert("fnLocationVal"+fnLocationVal);
				 if (EmployeeType!=null){
						ds+="&EmployeeType="+EmployeeType;
				 }
				 if (EmployeeGender!=null){
						ds+="&EmployeeGender="+EmployeeGender;
				 }
				    if(fnLocationVal!=null && fnLocationVal.length > 0)
				    	{
				    	ds+="&fnln="+fnLocationVal;
				    	}
				    else{
				    	ds+="&flid="+flid;
				    }
				    var keyid = jQuery('#hdncalendar').val();
				    var roleKeyId=jQuery("#cmbRole").combobox('getValue');
				   // alert("roleKeyId"+roleKeyId.length);
					if(roleKeyId.length > 1)
						{
						ds+="&roleKeyId="+roleKeyId;
						}
					
					var pillarId=jQuery("#cmbTrainingPillarGrp").combobox('getValue');
					//alert("The roleKeyId"+pillarId.length);
					
					if(pillarId!=null &&  pillarId.length > 1)
					{
					ds+="&pillarId="+pillarId;
					}
				   // alert("The PillarId::::"+pillarId);
					/* if(isEmpty(fnLocationVal) && isEmpty(keyid) && isEmpty(roleKeyId) ){
					//if(isEmpty(fnLocationVal) && isEmpty(empKeyId) && isEmpty(roleKeyId)  ){
								jQuery('#msg').html('<font color="red">Select Function Location,Employee Role</font>');
					}else{ */
						//jQuery('#msg').html('<font color="red"></font>');
					//	viewGrid(url,dataString +"&fnln="+fnLocationVal+"&TrainingId="+keyid+"&roleKeyId="+roleKeyId+"&pillarId="+pillarId);
						//viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId);
					//}
					ds+="&TrainingId="+keyid;
					//alert("ds string"+ds);
					processGridnew("EmployeeUniqueAdd_input.ntrc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");

				
			});
		
	});		
	
	
	

jQuery("#level").combobox(
		{
			onSelect : function(recordid) {
				jQuery("#cmbLocation").combobox('clear');
				reloadCombo("frmNewUniquePosition", "cmbLocation",
						getComboUrl(recordid.text));
				//jQuery("#cmbLocation").focus();						
			}
		});
     
function getComboUrl(cmbTxt) {
	var cmbUrl = null;
	

	
	var locationId=jQuery("#hdnlocnid").val();
	if (cmbTxt == 'Company')
		cmbUrl = "companyCombo.commonFilter";
	if (cmbTxt == 'Location')
		cmbUrl = "location.funlocn";
	if (cmbTxt == 'SBU')
		{
		cmbUrl = "sbuCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
	if (cmbTxt == 'PBU')
		{
		cmbUrl = "pbuCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
	if (cmbTxt == 'DMT')
		{
		cmbUrl = "sectionCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
	if (cmbTxt == 'JH')
		{
		cmbUrl = "cellCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
    	return cmbUrl;

}
	/*function viewGrid(url, filterString){
      processGridnew("EmployeeUniqueAdd_input.ntrc", filterString,"NewUniqueGrid", "pager", "", "doubleClick");
	}*/
	
	
	jQuery("#btnView").click(function() {

			var url = jQuery('#hiddenUrl').val();
			var ds="q=2";
			var  dataString="q=2";
			var cmbLevelVal=getFieldValue("level");
			var EmployeeType=getFieldValue("cmbEmployeetype");
			//alert("EmployeeType"+EmployeeType)
			var EmployeeGender=getFieldValue("cmbEmpmGender");
			
			
			var flid=jQuery("#hdnflid").val();
		    var fnLocationVal =jQuery("#cmbLocation").combobox('getValue');
		   // alert(jQuery("#cmbLocation").combobox('getValue'));
		 //  alert("fnLocationVal"+fnLocationVal);
		 if (EmployeeType!=null){
				ds+="&EmployeeType="+EmployeeType;
		 }
		 if (EmployeeGender!=null){
				ds+="&EmployeeGender="+EmployeeGender;
		 }
		    if(fnLocationVal!=null && fnLocationVal.length > 0)
		    	{
		    	ds+="&fnln="+fnLocationVal;
		    	}
		    else{
		    	ds+="&flid="+flid;
		    }
		    var keyid = jQuery('#hdncalendar').val();
		    var roleKeyId=jQuery("#cmbRole").combobox('getValue');
		   // alert("roleKeyId"+roleKeyId.length);
			if(roleKeyId.length > 1)
				{
				ds+="&roleKeyId="+roleKeyId;
				}
			
			var pillarId=jQuery("#cmbTrainingPillarGrp").combobox('getValue');
			//alert("The roleKeyId"+pillarId.length);
			
			if(pillarId!=null &&  pillarId.length > 1)
			{
			ds+="&pillarId="+pillarId;
			}
		   // alert("The PillarId::::"+pillarId);
			/* if(isEmpty(fnLocationVal) && isEmpty(keyid) && isEmpty(roleKeyId) ){
			//if(isEmpty(fnLocationVal) && isEmpty(empKeyId) && isEmpty(roleKeyId)  ){
						jQuery('#msg').html('<font color="red">Select Function Location,Employee Role</font>');
			}else{ */
				//jQuery('#msg').html('<font color="red"></font>');
			//	viewGrid(url,dataString +"&fnln="+fnLocationVal+"&TrainingId="+keyid+"&roleKeyId="+roleKeyId+"&pillarId="+pillarId);
				//viewGrid(url,dataString +"&fnLocation="+fnLocationVal +"&empKeyId="+empKeyId+"&roleKeyId="+roleKeyId);
			//}
			ds+="&TrainingId="+keyid;
			//alert("ds string"+ds);
			processGridnew("EmployeeUniqueAdd_input.ntrc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");

		
	});
	
	
	function  frmNewUniquePositioncmbRole_onSelect(record)
	{
		var flid=jQuery("#hdnflid").val();
		var role=record.id;
		var keyid = jQuery('#hdncalendar').val();
		//alert(role);
	  //  processGridnew("EmployeeUniqueAdd_input.ntrc","?q=2&flid="+flid+"&role="+role+"&TrainingId="+keyid,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");	
	}
	function  frmNewUniquePositioncmbTrainingPillarGrp_onSelect(record)
	{
		var flid=jQuery("#hdnflid").val();
		var role=record.id;
		if(role!=null && role.length >1)
			{
			jQuery("#cmbRole").combobox('clear');
			//alert("role="+jQuery("#cmbRole").combobox('getValue'));
			jQuery("#cmbLocation").combobox('clear');
			//alert("Location="+jQuery("#cmbLocation").combobox('getValue'));
			}
		var keyid = jQuery('#hdncalendar').val();
		//alert("keyid"+keyid);
	   // processGridnew("EmployeeUniqueAdd_input.ntrc","?q=2&flid="+flid+"&pillarrole="+role+"&TrainingId="+keyid,"NewUniqueGrid", "pager","","","","SessionEmploadComplete","");	
	}
	
	  function upEmpSave(){
			var Mstkeyid=jQuery("#hdncalendar").val();
			upEmpId=jQuery('#cmbTrainingEmployee').combobox('getValue');
			SessionId=jQuery('#cmbSession').combobox('getValue');
			//alert("The SessionId"+SessionId);
		
			if(SessionId.length!=0&&SessionId!=null)
				{
	   processAjaxCalls('chkEmployee.ntrc','&keyid='+Mstkeyid+"&empid="+upEmpId+"&SessionId="+SessionId,'chkEmployee_onsuccessCallBack','chkEmployee_onerrorCallBack');
				}
			else{
				alert("Without select Session Unable to Save Employee!!!! ");
				return false;
			}
			
		/*	if(SessionId.length!=0&&SessionId!=null)
				{
			var ds="Mstkeyid="+Mstkeyid+"&upEmpId="+upEmpId+"&SessionId="+SessionId;
		   if(upEmpId.trim().length>0 && SessionId.trim().length>0){
			 processAjaxCalls("entAddEmployeeUp_save.ntrc",ds,'employeebySave_successCallBack','');
		  }
		   else{
				  alert("Select the Employee & Session");
			  }
				}
			else{
				alert("Without select Session Unable to Save Employee!!!! ");
				return false;
			}*/
			
			}

	  function chkEmployee_onsuccessCallBack(result)
	  {
		  var empcount=result.empcnt;
		  var Mstkeyid=result.keyid;
		  var empid=result.empid;
		  var sessionid=result.SessionId;
		  if(empcount==0)
			  {
		  var ds="Mstkeyid="+Mstkeyid+"&upEmpId="+empid+"&SessionId="+sessionid;
		   if(empid.trim().length>0 && sessionid.trim().length>0){
			 processAjaxCalls("entAddEmployeeUp_save.ntrc",ds,'employeebySave_successCallBack','');
		   }
			  }
		   else{
			   alert("Already this employee is selected for this session!!!!!");
			   return false;
		   }
			 
	  }	  

	
	  
	  // -- chnaging for save vignesh  newunique------------------------//
	  
// 	  jQuery("#btnSave").click(function(){
// 		  var sessionid=jQuery('#cmbSession').combobox('getValue');
// 		// alert("The sessionid::::"+sessionid);
// 		 var keyid = jQuery('#hdncalendar').val();
// 		 if(sessionid.length!=0 && sessionid!=null)
// 			 {
// 		     var paramconvertArr =convertJsonArr();
// 		//   alert("paramconvertArr"+paramconvertArr);
// 	  		 //var paramconvertArr=paramconvertArr;
// 	  	     saveForm("frmNewUniquePosition","entAddEmployee_save.ntrc?&sessionid="+sessionid+"&keyid"+keyid+"&paramconvertArr="+paramconvertArr);
// 			 }else{
// 				 alert("Without select Session Unable to Save Employee!!!! ");
// 				 return false;
// 			 }
			
// 	  });	 
	  
	  jQuery("#btnSave").click(function () {
		    var sessionid = jQuery("#cmbSession").combobox("getValue");
		    var keyid     = jQuery("#hdncalendar").val();

		    if (sessionid && sessionid.length !== 0) {

		        // 1. Get your JSON/array string
		        var paramconvertArr = convertJsonArr(); 
		        // This can be "[]", or JSON string like `[{"selctVal":" ",...}]`

		        // 2. Encode each parameter value for safe use in URL
		        var url =
		            "entAddEmployee_save.ntrc"
		            + "?sessionid="      + encodeURIComponent(sessionid)
		            + "&keyid="          + encodeURIComponent(keyid)
		            + "&paramconvertArr=" + encodeURIComponent(paramconvertArr);

		        // Optional: debug
		        // console.log("Final URL:", url);

		        // 3. Submit
		        saveForm("frmNewUniquePosition", url);

		    } else {
		        alert("Without select Session Unable to Save Employee!!!! ");
		        return false;
		    }
		});



	  // -- chnaging for save vignesh ------------------------//
	  jQuery("#btnClose").click(function(){
		  closePopUpDialoge("divUniquePosAdd");
		  
		  
	  });
	  
	  
	
		function convertJsonArr(){
			var allrow = jQuery("#NewUniqueGrid").jqGrid('getRowData');
			var jsonArrO = '';
			var val = "";
			for ( var i = 0; i < allrow.length; i++) {
				var row = allrow[i];
				var rowno = parseInt(i) + 1;
				if(jQuery('#jqg_NewUniqueGrid_' + rowno).is(':disabled')==true)
					{
					continue;
					}
	               
	                
				
				if (jQuery('#jqg_NewUniqueGrid_' + rowno).is(':checked')) {
					
					jsonArrO += '{';
					for ( var colName in row) {
						if (row[colName].substring(0, 6) != '<input') {
							jsonArrO += '"' + colName + '":"' + row[colName] + '",';
						} else {
							var x = row[colName].indexOf("id=") + 4;
							var y = row[colName].substring(x);
							var z = y.indexOf('"');
							var cellId = y.substring(0, z);
							if (jQuery("#" + cellId).attr("type") == "checkbox"){
								val = jQuery('#' + cellId).is(':checked') ? 'Y'
										:'N';					  
							} else {
								val = jQuery('#' + cellId).val();
							}
							jsonArrO += '"' + colName + '":"' + val + '",';
						}
					}
					jsonArrO = jsonArrO.slice(0, -1) + '},';
				}
			}
			
			return '[' + jsonArrO.slice(0, -1) + ']';
		}
     
		function EmpChkBoxChk(rowId){
			var MstId = jQuery('#hdncalendar').val();
			//alert("The MstId:::"+MstId);
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'txtetceEtcmKeyid',MstId);
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'selctVal','1');
		}
		function EmpChkBoxUnChk(rowId){
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'txtetceEtcmKeyid'," ");
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'selctVal','0');
		}
		
		
   function NewUniqueGrid_selectRow(id){
	   if(jQuery('#jqg_NewUniqueGrid_'+id).is(':checked'))
	  		EmpChkBoxChk(id);
	  	else
	  		EmpChkBoxUnChk(id);
	  }  
	  
   function viewGrid() {
		var keyid = jQuery('#hdncalendar').val();
		var uniq=jQuery("#hdnuniq").val();
		//alert("The TrainingID::"+keyid);
	    var flid=jQuery("#hdnflid").val();
		//alert("The Flid"+flid);
		var ds ="?q=2&TrainingKeyid="+keyid+"&flid="+flid+"&TrainingId="+keyid+"&uniq="+uniq; 
		processGridnew("EmployeeUniqueAdd_input.ntrc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");
	}  
  
 /*   function SessionEmploadComplete(){
      var row = jQuery("#NewUniqueGrid").jqGrid('getDataIDs');
	  var cm = jQuery("#NewUniqueGrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		   var etcskeyid = jQuery("#NewUniqueGrid").jqGrid('getCell',row[i],"txtEtceEtcsKeyid");	
		  // alert("etcskeyid"+etcskeyid);
		 
		   //jQuery('#jqg_grdbachEmployee_'+row[i])
		   if(etcskeyid.trim().length>0){ 
			  // jQuery('#jqg_grdbachEmployee_'+row[i]).attr('checked',true);
			    jQuery('#NewUniqueGrid').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_NewUniqueGrid_'+row[i]+']').attr('checked',true);
			    jQuery("#NewUniqueGrid").jqGrid('setCell',row[i],'selctVal','1');
			    jQuery('input:checkbox[id=jqg_NewUniqueGrid_'+row[i]+']').attr('disabled',true);
		   }
	 }
   } */
   
   function SessionEmploadComplete(){
	   jQuery("#chkSelectAll").prop('checked', false);
      var row = jQuery("#NewUniqueGrid").jqGrid('getDataIDs');
	  var cm = jQuery("#NewUniqueGrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		   var etcskeyid = jQuery("#NewUniqueGrid").jqGrid('getCell',row[i],"txtEtceEtcsKeyid");	
		  // alert("etcskeyid"+etcskeyid);
		 
		   //jQuery('#jqg_grdbachEmployee_'+row[i])
		   if(etcskeyid.trim().length>0){ 
			  // jQuery('#jqg_grdbachEmployee_'+row[i]).attr('checked',true);
			    jQuery('#NewUniqueGrid').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_NewUniqueGrid_'+row[i]+']').attr('checked',true);
			    jQuery("#NewUniqueGrid").jqGrid('setCell',row[i],'selctVal','1');
			    jQuery('input:checkbox[id=jqg_NewUniqueGrid_'+row[i]+']').attr('disabled',true);
			    
		   }
	 }
   }
   
   function employeebySave_successCallBack(result) {
		if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
			popupCommonErrorMsg(result.tpmException);
		else
			alert(result.successData.msg);
	}
 /*  function viewGrid(url,filterString)
	{ 
		if( validateFilterSelection(filterString))
		{
			var functionalloc=getFilterValue(filterString, "cmbCircle");
			jQuery("#hiddencircle").val(functionalloc);
			var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
			jQuery("#hiddenRemoveBlank").val(removeBlank);
			filterString += '&Flid=f&firstClick=Y';	
            processGridnew(url,filterString,"NewUniqueGrid", "pager", "", "doubleClick");
  return true;
	}
return false;	
	}*/
 /*  function validateFilterSelection(filterString){
    return  true;
}*/
   
function frmNewUniquePosition_FuntLocHierarchy_SuccessCallBack(result){  
		/*var flid =jQuery("#frmNewUniquePosition input[id='flid']").val();
		if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
		{ 
		    reloadCombo("frmNewUniquePosition","cmbTrainingEmployee","employee.commonFilter?&flid="+result.flId);
		}*/
viewGrid();
}


function frmNewUniquePosition_successsCallback(result){
	/* alert("unique Successcallback");
	var compl=getFieldValue("chkEtcmChkCompleted","frmNewTraCal");
	alert("compl"+compl);
	enableFormFields("frmNewTraCal","chkEtcmChkCompleted") ;*/
	var keyid = jQuery('#hdncalendar').val();
	//	alert("The TrainingID::"+keyid);
	    var flid=jQuery("#hdnflid").val();
		//alert("The Flid"+flid);
		//var ds ="?q=2&TrainingKeyid="+keyid+"&flid="+flid+"&TrainingId="+keyid; 
		//processGridnew("EmployeeUniqueAdd_input.ntrc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");
	    jQuery("#NewUniqueGrid").trigger("reloadGrid");
}

function selectAllEmployees(chk) {

    var isChecked = jQuery(chk).is(':checked');
    var allRows = jQuery("#NewUniqueGrid").jqGrid('getDataIDs');

    if(allRows == null || allRows.length == 0) {
        alert("No rows found. Please click View first.");
        jQuery(chk).prop('checked', false);
        return;
    }

    for(var i = 0; i < allRows.length; i++) {

        var rowId = allRows[i];  

        
        var chkBox = jQuery('#jqg_NewUniqueGrid_' + rowId);

       
        if(chkBox.prop('disabled') == true) {
            continue;
        }

        if(isChecked) {
            chkBox.prop('checked', true);
            EmpChkBoxChk(rowId);
        } else {
            chkBox.prop('checked', false);
            EmpChkBoxUnChk(rowId);
        }
    }
}
   
</script>
<form id="frmNewUniquePosition" name="frmNewUniquePosition">
<tr>
       
         <td>
        <div class="easyui-paddingbfpx" style="padding-top: 0px;">
				<label style="padding-left: 10px;">Function Location Level</label>
				<label style="padding-left: 55px;">Functional Location</label>
				
			</div>
			<div>
				<span style="vertical-align: top; padding-left: 8px;"> 
				     <select id="level" class="easyui-combobox" name="level" style="width: 150px; /* height: 20px; */">
						<!-- <option value="cmp">Company</option>
						<option value="lcn">Location</option> -->
						<option value=" "> </option>
						<option value="sbu">SBU</option>
						<option value="subunt">PBU</option>
						<option value="sect">DMT</option>
						<option value="cell">JH</option>

				    </select>
				</span> 
				<span style="padding-left: 35px; padding-left: 45px\9; vertical-align: top;">
					<input id="cmbLocation" name="cmbLocation" class="easyui-combobox" style="width: 200px; /* height: 20px; */" />
				</span>
				<span id="msg" style="padding-left: 10px;vertical-align: top;padding-top: 1px;">
				
					
				</span>
				
			
			</div>
       
      <td>
		 		<div style="margin-left:450px;margin-top:-37px"><label id="" >Role</label></div>
				<div style="margin-left:450px"; class="easyui-paddingbfpx">
				<input class="easyui-combobox" id="cmbRole" name="cmbRole"  style=" width : 260px;"  value=" " />
				</div>
	 		</td>
         
         	<td>
		 		<div style="margin-left:715px;margin-top:-42px"><label id="" >Pillar Group</label></div>
				<div  style="margin-left:715px"; class="easyui-paddingbfpx" >
			<input class="easyui-combobox" id="cmbTrainingPillarGrp" name="cmbTrainingPillarGrp"  style=" width : 185px;"  value=" "/>
		 </div>
	 </td>	
   <td>
  
 
         
	       <td style="margin-left:-1px;" valign="top">
	 	    	<div style="margin-left:10px; margin-top:15px;"><label id="">Employee</label>
		 	    	<span style="margin-left:8px;">
						<input type="checkbox"  id="chkTrainingOthers" name="chkTrainingOthers" value=""> 
			 		<label>Others</label></span>
			 		</div>
				<div class="easyui-paddingbfpx" style=" width : 270px; margin-left:10px;">
				<input id="cmbTrainingEmployee" name="cmbTrainingEmployee" class="easyui-combobox" style="width:386px; value="" />
				
				</div>
	 	    </td>
<td valign="bottom">
					    <input type="checkbox" id="chkSelectAll" name="chkSelectAll" 
					           style="margin-left:10px;" 
					           onclick="selectAllEmployees(this);" />
					    <label for="chkSelectAll" style="font-size:11px; font-weight:normal; cursor:pointer;">
					        Select All
					    </label>
					</td>
	     <td>
         <div style="margin-top:-50px; margin-left:200px;">
         <span style="margin-left:250px;">
		<!-- <label id="">Session</label> -->
		<label id="" class="mandatory-lbl">Session</label>
		</span>
		<div style="margin-left:250px;">
       <input id="cmbSession" name="cmbSession" class="easyui-combobox" value="" style="width:170px;"/>
		</div>
		</div>
		</td>
   
   <td>     
   <div style="margin-left:625px;margin-top:-27px;">
  <input type="button" class="easyui-button" value="Add" id="btnEmpSave" onclick="upEmpSave();"/>
 </div>
  </td>   
  <td>   <div style="margin-top:-38px; margin-left:200px;">
  <label style="padding-left: 500px;">Employee Type</label></div>
  <div style="padding-left: 700px;">
	   <select id="cmbEmployeetype" class="easyui-combobox" name="cmbEmployeetype" style="width: 150px; height: 20px;">
														
										<option value=" "> </option>
									<option value="R"> EMPLOYEE</option>
									<option value="A"> ASSOCIATE</option>
									<option value="C"> CONTRACT</option>
									<option value="M"> MANAGER</option>
									<option value="B"> BADLI</option>
									<option value="T"> TRAINEE</option>
									<option value="E"> EXECUTIVE</option>
									<option value="O"> OTHERS</option>
							</select></div>
				      
   </td> 		
	<td>
		 <div style="margin-left:870px;margin-top:-40px; display:none;"><label class="mandatory-lbl">Gender</label></div> 
						<div class="easyui-paddingbfpx"style="margin-left:870px; display:none;" > 
						<select id="cmbEmpmGender" name="cmbEmpmGender" style="width:100px;  display:none;">
									<option value="M">MALE</option>
									<option value="F">FEMALE</option>
							</select>
				        </div>
	</td>
	  <td>      
   <div style="margin-left:980px;margin-top:-31px;">
   <input type="button" class="easyui-button" value="View" id="btnView"/>
   </div>
   </td>
	 <td>
	 <div style="margin-left:10px;margin-top:20px;">
	 <label><b>Specify Query Criteria and Select Employee to add to Training Program</label></b>	
	 </div>
	 </td>	
	 
	 	<div style="margin-top:8px; margin-left:9px;">
		<table id="NewUniqueGrid">
		</table>
		<div id="pager"></div>
	</div>
  
  
</tr> 


<div style="margin-left:480px;margin-top:10px;">
<input type="button" class="easyui-button" value="Save" id="btnSave"/>
<input type="button" class="easyui-button" value="Close" id="btnClose"/>
 </div>
 


<input type="hidden" id="mode" name="mode" value="create" />
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}" />
<input type="hidden" id="hdnlocnid" name="hdnlocnid" value="${requestScope.locnid}" />
<input type="hidden" id="hdnlocaid" name="hdnlocaid" value="${loginLocnId}" />
<input type="hidden" id="hdncalendar" name="hdncalendar" value="${requestScope.keyid}" />
<input type="hidden" id="hdnuniq" name="hdnuniq" value="${requestScope.uniq}" />
</form>