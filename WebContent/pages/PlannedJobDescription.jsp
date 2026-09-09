<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
	jQuery(document).ready(function(){
		initialiseForm('frmPlannedJobDesc');
		jQuery('#submitForm').val('frmPlannedJobDesc');
		var Mode=jQuery("#mode").val();
	//	alert("Mode::"+Mode);
    	//jQuery('#chkPjobIsFollowUpY').attr('checked',true);
		//alert("Planned Job Description.jsp");
		//fillComboBox('frmPlannedJobDesc','cmbPjobEmployeeid','employee.commonFilter',"",'',true);
		fillComboBox('frmPlannedJobDesc','cmbPjobEmployeeid','employee.commonFilter');
		fillComboBox('frmPlannedJobDesc','cmbPjobManager','employee.commonFilter');
		fillComboBox('frmPlannedJobDesc','cmbPjobType','PjobType.plnJobD',"",false);
		fillComboBox('frmPlannedJobDesc','cmbPjobNotification','PjobNotification.plnJobD',"",false);
		fillComboBox('frmPlannedJobDesc','cmbPjobReasonid','PjobReason.commonFilter');
		fillComboBox('frmPlannedJobDesc','cmbDepartment','department.commonFilter','','',true);
		fillComboBox('frmPlannedJobDesc','cmbSupDepartment','department.commonFilter');
		fillComboBox('frmPlannedJobDesc','cmbPjobFollowUpDone','employee.commonFilter');
		formatDateBox('dtePjobDate','dd-MMM-yyyy');
		formatDateBox('dtePjobFollowUpDate','dd-MMM-yyyy');
		disableField("frmPlannedJobDesc","txtPjobActionplanId");
		disableField("frmPlannedJobDesc", "cmbDepartment");
		disableField("frmPlannedJobDesc", "cmbSupDepartment");
		var factId=jQuery("#frmPlannedJobDesc input[id='factory']").val();
		//var factId = jQuery("#frmPlannedJobDesc input[id='cell']").val();
		jQuery("#frmPlannedJobDesc .easyui-text ").css('text-transform', 'uppercase');
		jQuery("#frmPlannedJobDesc textarea  ").css('text-transform', 'uppercase');
		var sectionId = jQuery("#frmPlannedJobDesc input[id='section']").val();
		var cellId = jQuery("#frmPlannedJobDesc input[id='cell']").val();
		var machId = jQuery("#frmPlannedJobDesc input[id='machine']").val();
		var flid = jQuery("#frmPlannedJobDesc input[id='flid']").val();
		var pjobKeyid=jQuery("#hdnPjobKeyid").val();
		disableField("frmPlannedJobDesc","txtPjobFollowuptaskobserved");
		disableField("frmPlannedJobDesc", "txtPjobFollowuppjono");
		//alert("pjobKeyid------ :" +pjobKeyid);
		var str = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid ;
	 	loadFunctionalLocation("PlannedJobDescfunlocation","functionalLoc.plnJobD","PlannedJobDescfunlocationValues","frmPlannedJobDesc",str);
	 	
	 
	 	processGridnew("pojEmployeeLink_input.plnJobD", "?pjobKeyid=" +pjobKeyid, "employeeGrd", "employeePager"," ", "empdoubleclickGrid", "", "");
	 //	alert("2");
	 	getEmpValue("cmbPjobEmployeeid");
	 	getSupVis("cmbPjobManager");
		var keyid = jQuery("#hdnPjobKeyid").val();
		if(!(keyid.length>0)){
			jQuery("#ExcelVw").hide();
			fillWithCurrentDate('dtePjobDate');
	 		//checkSingle();
		}else
			jQuery("#ExcelVw").show();
		jQuery("#btnExcelView").click(function(){
			window.open("PlannedJobObser_ExcelView.plnJobD?keyid="+keyid);
		});
		jQuery("#chkPjobIsPropertyDamageY").click(function(){
	 		chkRadio("chkPjobIsPropertyDamageY","chkPjobIsPropertyDamageN");
		 });
	 	jQuery("#chkPjobIsPropertyDamageN").click(function(){
	 		chkRadio("chkPjobIsPropertyDamageN","chkPjobIsPropertyDamageY");
		 });
	 	jQuery("#chkPjobIsEfficientY").click(function(){
	 		chkRadio("chkPjobIsEfficientY","chkPjobIsEfficientN");
		 });
	 	jQuery("#chkPjobIsEfficientN").click(function(){
	 		chkRadio("chkPjobIsEfficientN","chkPjobIsEfficientY");
		 });
		 jQuery("#chkPjobIsComplyY").click(function(){
	 		chkRadio("chkPjobIsComplyY","chkPjobIsComplyN");
		 });
	 	jQuery("#chkPjobIsComplyN").click(function(){
	 		chkRadio("chkPjobIsComplyN","chkPjobIsComplyY");
		 });
		 jQuery("#chkPjobIsDetrimentalY").click(function(){
	 		chkRadio("chkPjobIsDetrimentalY","chkPjobIsDetrimentalN");
		 });
	 	jQuery("#chkPjobIsDetrimentalN").click(function(){
	 		chkRadio("chkPjobIsDetrimentalN","chkPjobIsDetrimentalY");
		 });
		 jQuery("#chkPjobIsComplementedY").click(function(){
	 		chkRadio("chkPjobIsComplementedY","chkPjobIsComplementedN");
		 });
	 	jQuery("#chkPjobIsComplementedN").click(function(){
	 		chkRadio("chkPjobIsComplementedN","chkPjobIsComplementedY");
	 		
		 });
		 jQuery("#chkPjobIsFollowUpY").click(function(){
			jQuery('#FA').addClass('mandatory-lbl');
	 		chkRadio("chkPjobIsFollowUpY","chkPjobIsFollowUpN");
		 });
	 	jQuery("#chkPjobIsFollowUpN").click(function(){
	 		  jQuery('#FA').removeClass('mandatory-lbl');  
	 		chkRadio("chkPjobIsFollowUpN","chkPjobIsFollowUpY");
		 });

	 	clearField('cmbPjobEmployeeid');
	});
	
	jQuery('#btnPJOFollow').click(function(){
		var empkeyid=jQuery("#cmbPjobEmployeeid").combobox("getValue");
		if(empkeyid.length>0){
		       LoadPopUp("divPJOFollow","PJOPopupUpdate_input.plnJobD?q=2&empkeyid="+empkeyid,true,"900px","490px","5px","5%","multiSelectOk_Callback","PJO Update");
		 	}
		 	else{
			    alert("Select Employee");
				return false;
			}	
	});	
	
	jQuery('#btnEmployeeSave').click(function(){
		var employeeid=getFieldValue("cmbPjobEmployeeid");
	//	alert("employeeid :"+employeeid);
	
	var txtjobTempfield5 = jQuery("#txtjobTempfield5").val();
	//alert(txtjobTempfield5)
		if(employeeid.trim().length>0){
		//	alert("(employeeid.trim().length>0) ");
		
			saveForm("frmPlannedJobDesc","PlannedJobObservationEntry_save.plnJobD");	
		}
		else
			alert("Select Employee");
	});
	
	function getEmpValue(fieldId){
		var empId=jQuery("#"+fieldId).val();
	 	if(empId.length>0){
	 		getcmbDepartment(empId);
	 	}
	}
	function getSupVis(fieldId){
		var empId=jQuery("#"+fieldId).val();
	 	if(empId.length>0){
	 		getcmbSupDepartment(empId);
	 	}
	}
	function checkSingle(){
		jQuery("#chkPjobIsPropertyDamageN").attr('checked',true);
		jQuery("#chkPjobIsEfficientN").attr('checked',true);
		jQuery("#chkPjobIsComplyN").attr('checked',true);
		jQuery("#chkPjobIsDetrimentalN").attr('checked',true);
		jQuery("#chkPjobIsComplementedN").attr('checked',true);
		jQuery("#chkPjobIsFollowUpN").attr('checked',true);
	}
	function chkRadio(check,uncheck){
		if(jQuery('#'+check).is(':checked') == true){
			jQuery("#"+uncheck).attr('checked',false);
		}
	}
	function frmPlannedJobDesc_beforeDelete(){
		var keyid = jQuery("#hdnPjobKeyid").val();
		if(keyid.length>0){
			var r=confirm("Are You Sure to Delete?");
			if(r)
				return true;
			else
				return false;
		}else
			return false;
	}
	function frmOplCreationcmboplmMachineid_onClear()
	{
		//loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation","&cellId="+jQuery("#frmOplCreation input[id='cell']").val());
		jQuery("#frmPlannedJobDesc input[id='machine']").val('');
		// setTimeout(function() {jQuery('#cmboplmMachineid').combobox('clear');},1250);
	}
	
	  jQuery("#btnpjoActionpln").click(function(){
			 var pjoKeyid=jQuery("#hdnPjobKeyid").val();
			 if(pjoKeyid.trim().length>0)
		     { 
				var flid=jQuery("#flid").val();
				var mainTask=getFieldValue("txtpjobFollowUpAction");
				var apMode = "create";
				 if(jQuery('#mode').val()=="view")
				 	 apMode = "view";
				 openActionPlan("pjoActionPlan",pjoKeyid,"PJO",flid,mainTask,pjoKeyid,"",apMode);
		     }else 
		     {    
			 saveForm('frmPlannedJobDesc','PlannedJobObservationEntry_save.plnJobD?openactnpln=openactnpln');
		     } 
		  });
	 
	  function pjoActionPlan_onClose(){
			 var pjoKeyid=jQuery("#hdnPjobKeyid").val();
			 var Aplmkeyid=jQuery("#txtAplmKeyid").val();
			 jQuery("#txtPjobActionplanId").val(Aplmkeyid);
			 processAjaxCalls("POJPopup_update.plnJobD","pjoKeyid="+pjoKeyid+"&Aplmkeyid="+Aplmkeyid,"UpdateSuccess","");
			 return true;
	  }
	  
	  
	  function divPJOFollow_onClose(){
		   //   alert("close");
			 var FollowUpKeyid=jQuery("#hdnPjobKeyid").val();
			// alert(FollowUpKeyid);
			 //var Aplmkeyid=jQuery("#txtAplmKeyid").val();
			// jQuery("#txtPjobNo").val(FollowUpKeyid);
			// processAjaxCalls("POJPopup_update.plnJobD","pjoKeyid="+pjoKeyid+"&Aplmkeyid="+Aplmkeyid,"UpdateSuccess","");
			 return true;
	  }
	  
	function frmPlannedJobDesc_beforeSubmit(){
		//
		var cellId = jQuery("#frmPlannedJobDesc input[id='cell']").val();
//		alert("cellId: " +cellId);
	var txtPjobSopNo=jQuery("#txtPjobSopNo").val();
	var txtjobTempfield5=jQuery("#txtjobTempfield5").val();
	//alert(txtjobTempfield5)
	var txtexternalmem=jQuery("#txtexternalmem").val();
	 if(txtPjobSopNo.length==0){
	    	popupCommonErrorMsg("Please Enter the SOPNO ");
	    	return false; 
	 }
		if(cellId =='' ||cellId == null ){
			alert("Select JH");
			return false;
		}
		//var pjobtype=jQuery("#cmbPjobType").val();
		var pjobtype=jQuery("#cmbPjobType").combobox("getValue");

		if(pjobtype=="F")
			{
			
                    // var followup=jQuery("#chkPjobIsFollowUpY").is(":checked");
			      //jQuery('#chkPjobIsFollowUpY').attr('checked',true);
			  var followup=jQuery("#chkPjobIsFollowUpY").attr('checked',true);
				
                   // alert(followup);
           if(followup){
		
	     var FollowUpAction=jQuery("#txtpjobFollowUpAction").val();		
       //  alert(FollowUpAction.length);
         if(FollowUpAction.length==0){
    	popupCommonErrorMsg("Please Enter the FollowUp Action");
    	return false;    
      } 	    
	   
   }
   }
   
   
   
		
	var jqGridId="employeeGrd";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	//alert(" allRows.length---" +allRows.length);
	var empArr1='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		//alert("Rowwwwww: " + row);
		var rowId=parseInt(i)+1;	
		var rowData = jQuery("#employeeGrd").jqGrid('getRowData',rowId);
		// alert("rowData :" +rowData);
		 var employeeIds =rowData.PJEL_PJOB_EMPLOYEEID;
		// alert("employeeId :" +employeeIds);
		 empArr1=empArr1 +"," +employeeIds;
		 var empIds =rowData.EMPM_KEYID;
		// alert("Before submit empId :" +empIds);
	}	
		
		
		//
		var keyid = jQuery("#hdnPjobKeyid").val();
		//alert("Before Submit: keyid =" +keyid);
		
		var empArr="&empIds="+getFieldValue("cmbPjobEmployeeid");
		empArr=empArr+empArr1;
		//alert("Employee Array: " +empArr);	
		
		 var empId = getFieldValue("cmbPjobEmployeeid");
		// alert("Employee Id: --" +empId);
		 if( empId .trim().length > 0){
			 jQuery('#txtIsEmployeeLink').val('Y');
		 }else{
			 jQuery('#txtIsEmployeeLink').val(' ');
		 }

		if(keyid.length>0){
			var r=confirm("Data Changed. Do You Want to Proceed?");
			if(r)
				return empArr;
			else
				return false;
		}
		return empArr;
	}
	
	function frmPlannedJobDesc_beforeRefresh(){
		var keyid = jQuery("#hdnPjobKeyid").val();
		if(!(keyid.length>0)){
			var r=confirm("Are You Sure to Clear?");
			if(r)
				return true;
			else
				return false;
		}
	}
	function frmPlannedJobDesc_successsCallback(result){
		var openactnpln=result.successData.openactnpln;
		var flid=result.successData.flid;
		var keyid=result.successData.Keyid;
		//alert(keyid);
		var facSaved = jQuery('#txtIsEmployeeLink').val( );
		 if( facSaved.trim() == "Y")
		 {
		  clearField('cmbPjobEmployeeid');	
		 }
	   if(openactnpln==true){
		   var mainTask=getFieldValue("txtpjobFollowUpAction");
		   openActionPlan("pjoActionPlan",keyid,"PJO",flid,mainTask,keyid);
		   return false;
	   }
		setFieldValue('txtPjobKeyid',keyid);
	    // navigateToPrevForm();
		 processGridnew("pojEmployeeLink_input.plnJobD", "?pjobKeyid=" +keyid, "employeeGrd", "employeePager"," ", "empdoubleclickGrid", "", "");	
	}
	
	function frmPlannedJobDesc_deleteSuccessCallback(result){
		alert(result.successData.msg);
		navigateToPrevForm();
	}
	function frmPlannedJobDesccmbPjobEmployeeid_onSelect(record){
		getcmbDepartment(record.id);
	}
	function getcmbDepartment(id){
		var EmpId=getFieldValue("cmbPjobEmployeeid");
		processAjaxCalls("PlannedJobObserEntryDep_recall.plnJobD?q=2&EmpId="+EmpId,"","successCallBack_Emp","errorCallBack");
	}
	function successCallBack_Emp(result){
		setFieldValue("cmbDepartment",result[0][0]);
	}
	/* function frmPlannedJobDesc_successsCallback(result){
		navigateToPrevForm();
	} */
	function frmPlannedJobDesccmbPjobManager_onSelect(record){
		getcmbSupDepartment(record.id);
	}
	function getcmbSupDepartment(EmpId){
		processAjaxCalls("PlannedJobObserEntryDep_recall.plnJobD?q=2&EmpId="+EmpId,"","successCallBack_manager","errorCallBack");
	}
	function successCallBack_manager(result){
		setFieldValue("cmbSupDepartment",result[0][0]);
	}
	
	 function empdoubleclickGrid(id){ 
		 var rowData = jQuery("#employeeGrd").jqGrid('getRowData',id);
	// alert("rowData :" +rowData);
		 var employeeId =rowData.PJEL_PJOB_EMPLOYEEID;
		// alert("employeeId :" +employeeId);
		 var empId =rowData.EMPM_KEYID;
		// alert(employeeId);
		// alert("empId :" +empId);
		 jQuery('#txtEmpKeyid').val(empId);
		 setFieldValue("cmbPjobEmployeeid",employeeId,"frmPlannedJobDesc");
	 }
	 
	 function BtnFormatterDelete(id, options, rowObject)
	 {					
	 	var rowId = options.rowId;
	 //	alert("Row Id ---:" + rowId );
	 	var gridId = options.gid;
	 //	alert("Grid Id: --" +gridId);

	 	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
	 }
	 
	 
	 function deleterec(rowid,gridId){
		// alert("Delete Record");
			var pjelPjobEmployeeid='';
			if("employeeGrd"==gridId){
				pjelPjobEmployeeid = getGridCell(gridId,rowid,'PJEL_PJOB_EMPLOYEEID');
			//alert("Delete Key Id:" +pjelPjobEmployeeid);
			}
			
			
			processAjaxCalls('deleteEmployee.plnJobD','&pjelPjobEmployeeid='+pjelPjobEmployeeid+'&gridId='+gridId,'deleteDetail_onsuccessCallBack','deleteDetail_onerrorCallBack');
		}
	 
	 function deleteDetail_onsuccessCallBack(result){ 
		  var r = confirm("Are You Sure To Delete?");
		   
		  if(r){
			  alert(result.msg);
		      jQuery("#"+result.gridid).trigger("reloadGrid");
	
		}
	 }
	   
	 function frmPlannedJobDesccmbPjobEmployeeid_onSelect(record){
		 
		
			var DiscussionType=jQuery("#cmbPjobEmployeeid").combobox("getText");
		//alert("DiscussionType"+DiscussionType)
			
			}	
	
</script>
<form id="frmPlannedJobDesc" name="frmPlannedJobDesc">
	<div>
		<table style="margin-left:15%">
			<tr>
				<td colspan="2">
					<div style="position: relative">
						<div id="frmPlannedJobDescFuntKeyIds"  >
							<div style="padding-right: 20px;">
								<input type="hidden" id="factory" name="factory"></input>			
								<input type="hidden" id="section" name="section"></input>
								<input type="hidden" id="cell" name="cell"></input>
								<input type="hidden" id="machine" name="machine" ></input>
								<input type="hidden" id="flid" name="cmbPjobFlid"  value="${requestScope.sheTlPlannedjobobservation.pjobFlid}"></input>
								<input type="hidden" id="elementId" name="cmbPjobElementid"  value="${requestScope.sheTlPlannedjobobservation.pjobElementid}"></input>
							</div>
							<div id="PlannedJobDescfunlocation"></div>
						</div>
						<span id="ExcelVw" style="position: absolute;top:20px;right:-70px">
							<input type="button" class="easyui-button" id="btnExcelView" name="btnExcelView" value="Excel View" style="height:23px; width:75px;"/>
						</span>
					</div>
				</td>
				<td style="position: absolute;top:10px;right:35px">
					<div>
							<label>Planned Job Keyid</label>
							</div>
						<div>
							<input class="easyui-text" id="txtPjobKeyid" name="txtPjobKeyid" disabled="disabled" style="width:115px;" maxlength="99" value="${requestScope.sheTlPlannedjobobservation.pjobKeyid}"/>
						</div>
				</td>
			</tr>
			
			<tr>
				<td width="40%">
					<div class="easyui-paddingbfpx">
						<div>
							<label class="mandatory-lbl" >1. Employee Name</label>
							
						</div>
						<div>
							<input class="easyui-combobox" id="cmbPjobEmployeeid" name="cmbPjobEmployeeid" style="width:265px;" value="${requestScope.sheTlPlannedjobobservation.pjobEmployeeid}"/>
							<input type="button" class="easyui-button" value="Add" id="btnEmployeeSave"/>
						</div>
					</div>
				</td>
				<td width="70%">
					<div class="easyui-paddingbfpx">
						<div>
							<label>2. Department</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbDepartment" name="cmbDepartment" style="width:255px;"/>
						</div>
					</div>
				</td>
				<td>
			
				<div class="easyui-paddingbfpx" style="margin-top:10px;margin-left:-180px;">
				<div class="easyui-paddingbfpx">
					<div style="margin-top:10px;">
							<label>External Members</label>
						</div>
				
		

			</div>
				</div>
			</td>	
				
				<td>
				<div class="easyui-paddingbfpx" style="margin-top:10px;margin-left:-150px;">
			<input type="button" class="easyui-button" value="Add FollowUp Pjo" id="btnPJOFollow"/>
			</div>
				
			</td>	
				
	
				
				
				
				
			</tr>
			
			
			<tr>
			  <td valign="top" colspan="" width="50%">
	 			<table id="employeeGrd"><tr><td></td></tr></table>
	 			<div id="employeePager"></div>
	 		</td>
	 		
	 		<td>
	 		<div class="easyui-paddingbfpx" style="margin-top:-100px;">
						<div>
							<label class="mandatory-lbl">4. Task Observed(Activity/Procedure)</label>
						</div>
						<div>
							<textarea id="txtPjobTaskObserved" name="txtPjobTaskObserved" style="resize:none;width:400px;height:100px;" maxlength="1000" >${requestScope.sheTlPlannedjobobservation.pjobTaskObserved}</textarea>
						</div>
					</div>
	 		</td>
	 		
			<td>
			<div>
					 <label  class="mandatory-lbl" >Date</label>
					 <span style="padding-left:80px;">
								
				
						
							</span>
						</div>
						<div>
							<input id="dtePjobDate" class="easyui-datebox" name="dtePjobDate" value="${requestScope.sheTlPlannedjobobservation.pjobDate}"/>
							  
						</div>
				
						
						
														
						<div>
							<label>Location</label>
							</div>
						<div>
							<input class="easyui-text" id="txtPjobLocationDescription" name="txtPjobLocationDescription" style="width:115px;" maxlength="99" value="${requestScope.sheTlPlannedjobobservation.pjobLocationDescription}"/>
						</div>
						
						<div>
							<label style="color:green;"><b>FollowUp Pjo</b></label>
						</div>
						<div>
							<input class="easyui-text" id="txtPjobFollowuppjono" name="txtPjobFollowuppjono" style="width:115px;" maxlength="99" value="${requestScope.sheTlPlannedjobobservation.pjobFollowuppjono}"/>
						
						
						</div>
						<div>
							<label class="mandatory-lbl">Sop No</label>
							
						</div>
						<div>
							<input class="easyui-text" id="txtPjobSopNo" name="txtPjobSopNo" style="width:115px;" maxlength="99"  value="${requestScope.sheTlPlannedjobobservation.pjobSopNo}" />
						
						
						</div>
						
						<div class="easyui-paddingbfpx" style="margin-left:-440px;">
						<div>
						<label style="color:green;"><b>Follow UP Task Observed</b></label>
						</div>
						<div>
						<textarea id="txtPjobFollowuptaskobserved" name="txtPjobFollowuptaskobserved" disabled="disabled" style="resize:none; width:400px;height:50px;" maxlength="1000" >${requestScope.sheTlPlannedjobobservation.pjobFollowuptaskobserved}</textarea>
			
			
			</div>
					</div>
						
						 </td>
						 
		   </tr>
			
			
			
			
		
			<tr>
				<td class="easyui-paddingbfpx" colspan="2">
					<div>
						<div>
							<span><label class="mandatory-lbl">5. Type of Observation</label></span>
							<span style="margin-left:84px;"><label class="mandatory-lbl">6. Notification</label></span>
							<span style="margin-left:130px;" class="mandatory-lbl"><label>7. Reason for Observation</label></span>
						</div>
						<div>
							<span><input class="easyui-combobox" id="cmbPjobType" name="cmbPjobType" style="width:175px;" value="${requestScope.sheTlPlannedjobobservation.pjobType}"/></span>
							<span style="margin-left:26px;"><input class="easyui-combobox" id="cmbPjobNotification" name="cmbPjobNotification" style="width:175px;" value="${requestScope.sheTlPlannedjobobservation.pjobNotification}"/></span>
							<span style="margin-left:26px;"><input class="easyui-combobox" id="cmbPjobReasonid" name="cmbPjobReasonid" style="width:175px;" value="${requestScope.sheTlPlannedjobobservation.pjobReasonid}"/></span>
						</div>
					</div>
				</td>
				<!--<td>
					<div class="easyui-paddingbfpx">
						<div>
							<label>Notification</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbPjobNotification" name="cmbPjobNotification" style="width:255px;"/>
						</div>
					</div>
				</td>
				<td>
					<div class="easyui-paddingbfpx">
						<div>
							<label>Reason for Observation</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbPjobReasonid" name="cmbPjobReasonid" style="width:255px;"/>
						</div>
					</div>
				</td>-->
			</tr>
			<tr>
				<td colspan="2">
					<div>
						<span style="margin-top:-2px;width:250px;height:35px" id="err_cmbPjobType" class="tpm-errormsg"></span>
						<span style="margin-top:-30px;margin-left:210px;width:200px;" id="err_cmbPjobNotification" class="tpm-errormsg"></span>
						<span style="margin-top:-20px;margin-left:410px;width:250px;" id="err_cmbPjobReasonid" class="tpm-errormsg"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="margin-top:15px" class="sub-header" >Job Observation</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>8. Could any of the practices or conditions observed result in property damage or personal injury?</label>
						</div>
						<div>
							<span style="padding-left: 8px">
								<span style="padding-left: 10px">
									<input id="chkPjobIsPropertyDamageY"  name="chkPjobIsPropertyDamageY" type="checkbox" value="Y" <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsPropertyDamage == 'Y' ? ' checked':''}"/>/>
									<label style="margin-left:8px">Yes</label>
								</span>
								<span style="padding-left: 10px">
									<input id="chkPjobIsPropertyDamageN"  name="chkPjobIsPropertyDamageN" type="checkbox" value="N"  <c:out value="${requestScope.sheTlPlannedjobobservation.pjobIsPropertyDamage=='N' ? ' checked':''}"/>/>
									<label style="margin-left:8px">No</label>
								</span>
							</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>9. Were the methods & practices observed the most efficient & productive</label>
						</div>
						<div>
							<span style="padding-left: 8px">
								<span style="padding-left: 10px">
									<input id="chkPjobIsEfficientY"  name="chkPjobIsEfficientY" type="checkbox" value="Y"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsEfficient == 'Y' ? ' checked':''}"/>/>
									<label style="margin-left:8px">Yes</label>
								</span>
								<span style="padding-left: 10px">
									<input id="chkPjobIsEfficientN"  name="chkPjobIsEfficientN" type="checkbox" value="N"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsEfficient == 'N' ? ' checked':''}"/>/>
									<label style="margin-left:8px">No</label>
								</span>
							</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>10. Did the practices you observed comply with all of the applicable standards that exist for this task?</label>
						</div>
						<div>
							<span style="padding-left: 8px">
								<span style="padding-left: 10px">
									<input id="chkPjobIsComplyY"  name="chkPjobIsComplyY" type="checkbox" value="Y"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsComply == 'Y' ? ' checked':''}"/>/>
									<label style="margin-left:8px">Yes</label>
								</span>
								<span style="padding-left: 10px">
									<input id="chkPjobIsComplyN"  name="chkPjobIsComplyN" type="checkbox" value="N"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsComply == 'N' ? ' checked':''}"/>/>
									<label style="margin-left:8px">No</label>
								</span>
							</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>11. Could any of the practices observed have a deterimental effect upon the quality of the product?</label>
						</div>
						<div>
							<span style="padding-left: 8px">
								<span style="padding-left: 10px">
									<input id="chkPjobIsDetrimentalY"  name="chkPjobIsDetrimentalY" type="checkbox" value="Y"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsDetrimental == 'Y' ? ' checked':''}"/>/>
									<label  style="margin-left:8px">Yes</label>
								</span>
								<span style="padding-left: 10px">
									<input id="chkPjobIsDetrimentalN"  name="chkPjobIsDetrimentalN" type="checkbox" value="N"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsDetrimental == 'N' ? ' checked':''}"/>/>
									<label style="margin-left:8px">No</label>
								</span>
							</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div class="easyui-paddingbfpx">
							<div>
								<label>12. Describe clearly below any practices or conditions related to items above that deserve complement or correction, with respect to the demand safe work</label>
							</div>
							<div>
								<textarea id="txtPjobCorrections" name="txtPjobCorrections" style="resize:none;width:590px;height:75px;" maxlength="470"  >${requestScope.sheTlPlannedjobobservation.pjobCorrections}</textarea>
							</div>
						</div>
						<div>
							<div style="padding-left: 10px">
								<input id="chkPjobPositioning"  name="chkPjobPositioning" type="checkbox" value="POW"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobPositioning == 'Y' ? ' checked':''}"/>/>
								<label  style="margin-left:8px">Worker had Equipment & supplies well organised, especially in their positioning at the work area.</label>
							</div>
							<div style="padding-left: 10px">
								<input id="chkPjobChecklist"  name="chkPjobChecklist" type="checkbox" value="CKF"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobChecklist == 'Y' ? ' checked':''}"/>/>
								<label style="margin-left:8px">All Steps according to the checklist followed.</label>
							</div>
							<div style="padding-left: 10px">
								<input id="chkPjobAppPPE"  name="chkPjobAppPPE" type="checkbox" value="PPE"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobAppPPE == 'Y' ? ' checked':''}"/>/>
								<label  style="margin-left:8px">Have the workers use all PPEs, appropriate and used in order.</label>
							</div>
							<div style="padding-left: 10px">
								<input id="chkPjobCheckWorker"  name="chkPjobCheckWorker" type="checkbox" value="CKW"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobCheckWorker == 'Y' ? ' checked':''}"/>/>
								<label style="margin-left:8px">Check, since he was aware of being observed, his conscientious manner of work during the </label>
								<div>
									<label style="margin-left:35px">observation appeared to be his natural way of doing the job.</label>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>13. Detail below the deviations from the Safe work practices and any other unsafe conditions / practices</label>
						</div>
						<div>
							<textarea id="txtPjobDeviations" name="txtPjobDeviations" style="resize:none;width:590px;height:75px;" maxlength="470"  >${requestScope.sheTlPlannedjobobservation.pjobDeviations}</textarea>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>14. Have you properly complemented and/or reinstructed the worker on these observations?</label>
						</div>
						<div>
							<span style="padding-left: 8px">
								<span style="padding-left: 10px">
									<input id="chkPjobIsComplementedY"  name="chkPjobIsComplementedY" type="checkbox" value="Y"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsComplemented == 'Y' ? ' checked':''}"/>/>
									<label  style="margin-left:8px">Yes</label>
								</span>
								<span style="padding-left: 10px">
									<input id="chkPjobIsComplementedN"  name="chkPjobIsComplementedN" type="checkbox" value="N"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsComplemented == 'N' ? ' checked':''}"/>/>
									<label style="margin-left:8px">No</label>
								</span>
							</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>15. Should a follow-up observation of this worker or task be made in the near future?</label>
						</div>
						<div>
							<span style="padding-left: 8px">
								<span style="padding-left: 10px">
									<input id="chkPjobIsFollowUpY"  name="chkPjobIsFollowUpY" type="checkbox" value="Y"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsFollowUp == 'Y' ? ' checked':''}"/>/>
									<label  style="margin-left:8px">Yes</label>
								</span>
								<span style="padding-left: 10px">
									<input id="chkPjobIsFollowUpN"  name="chkPjobIsFollowUpN" type="checkbox" value="N"  <c:out value = "${ requestScope.sheTlPlannedjobobservation.pjobIsFollowUp == 'N' ? ' checked':''}"/>/>
									<label style="margin-left:8px">No</label>
								</span>
							</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label>16. Describe any standard procedure? Method or equipment you observed that should be considered for change in the interest of better safety</label>
						</div>
						<div>
							<textarea id="txtPjobStandardProcedure" name="txtPjobStandardProcedure" style="resize:none;width:590px;height:75px;" maxlength="470"  >${requestScope.sheTlPlannedjobobservation.pjobStandardProcedure}</textarea>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="40%">
					<div class="easyui-paddingbfpx">
						<div>
							<label >17. Supervisor</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbPjobManager" name="cmbPjobManager" style="width:255px;" value="${requestScope.sheTlPlannedjobobservation.pjobManager}"/>
						</div>
					</div>
				</td>
				<td width="70%">
					<div class="easyui-paddingbfpx" style="margin-left:-130px;">
						<div>
							<label>18. Department</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbSupDepartment" name="cmbSupDepartment" style="width:255px;"/>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx">
						<div>
							<label  id="FA" style="color: green ;font-weight: bold;">Follow-Up Action</label>
						</div>
						<div>
							<textarea id="txtpjobFollowUpAction" name="txtpjobFollowUpAction" style="resize:none;width:590px;height:75px;" maxlength="470"  >${requestScope.sheTlPlannedjobobservation.pjobFollowUpAction}</textarea>
						</div>
					</div>
				</td>
			</tr>
				<tr>
				<td colspan="2">
				<div class="easyui-paddingbfpx">
				<div style="margin-left:620px;margin-top:-75px;">
				<input type="button" id="btnpjoActionpln" name="btnpjoActionpln" class="easyui-button" style="width:80px;height:22px;" value="Action Plan" />			
					 </div>
					</div>
				</td>
			</tr>
			
				<tr>
				<td colspan="2">
					<div class="easyui-paddingbfpx" style="margin-left:705px;margin-top:-91px;">
						<div>
							<label class="">ActionPlan Id</label>
						</div>
						<div>
					<input class="easyui-text" id="txtPjobActionplanId" name="txtPjobActionplanId" style="width:140px;" maxlength="99" value="${requestScope.sheTlPlannedjobobservation.pjobActionplanId}"/>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="easyui-paddingbfpx">
						<div>
							<label>19. Supervisor/Manager observed the Job/Task</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbPjobFollowUpDone" name="cmbPjobFollowUpDone" style="width:255px;" value="${requestScope.sheTlPlannedjobobservation.pjobFollowUpDone}"/>
						</div>
					</div>
				</td>
				<td>
					<div class="easyui-paddingbfpx" style="margin-left:-89px;">
						<div>
							<label>Date</label>
						</div>
						<div>
							<input id="dtePjobFollowUpDate" class="easyui-datebox" name="dtePjobFollowUpDate" value="${requestScope.sheTlPlannedjobobservation.pjobFollowUpDate}" />
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- <div  style="margin-left:-1095px;margin-top:755px;"><label style="color:green;">Follow UP Task Observed.</label>
	</div> -->
<%-- 	<div style="margin-top:-1088px;margin-left:770px;">
		<textarea id="txtPjobFollowuptaskobserved" name="txtPjobFollowuptaskobserved" disabled="disabled" style="resize:none; width:320px;height:50px;" maxlength="1000" >${requestScope.sheTlPlannedjobobservation.pjobFollowuptaskobserved}</textarea>
						</div>
						<div class="easyui-paddingbfpx" style="margin-top:-98px;margin-left:770px;">
			<input type="button" class="easyui-button" value="Add FollowUp Pjo" id="btnPJOFollow"/>
			</div>
					 --%>
	<input type="hidden" name="mode" id="mode"/>
	<input type="hidden" id="txtIsEmployeeLink" name="txtIsEmployeeLink" value=" "/>
	<input type="hidden" name="hdnPjobKeyid" id="hdnPjobKeyid" value="${requestScope.sheTlPlannedjobobservation.pjobKeyid}"/>
	<input type="hidden" id="txtEmpKeyid" name="txtEmpKeyid" />
</form>