  <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
  --%>
  <style>

.equipmentform
{

 width:200px\9;
 
}

</style>
<script>
jQuery(document).ready(function(){
	initialiseForm('frmEquipment');	
	
	jQuery('#grdoperator').attr('readonly', 'readonly');	
	jQuery('#submitForm').val('frmEquipment'); // set the id of form to submit

	fillComboBox("frmEquipment","cmbMchmKeyid","machineCombo.commonFilter?&combokey="+getFieldValue('cmbMchmKeyid')+"&");
	fillComboBox("frmEquipment","cmbMchmSubcellid","Combo_SubSect.eqp");
	fillComboBox("frmEquipment","cmbMchmWorkcenter","Combo_workCenter.eqp");
	fillComboBox("frmEquipment","cmbMchmMachinerank","Combo_MachineRank.eqp");
	fillComboBox("frmEquipment","cmbMchmPurpose","Combo_Purpose.eqp");
	fillComboBox("frmEquipment","cmbMchmSubcategory","Combo_SubCategory.eqp");
	fillComboBox("frmEquipment","cmbMchmJhstep","Combo_JhStep.eqp");
	fillComboBox("frmEquipment","cmbMchmPowersupply","Combo_PowerSupply.eqp");
	fillComboBox("frmEquipment","cmbMchmConnectedload","Combo_ConnectedLoad.eqp");
	
	fillComboBox("frmEquipment","cmbMchmDbno","Combo_DBNo.eqp");
	//below ve to check
	fillComboBox("frmEquipment","cmbMchmSbno","Combo_SBNo.eqp"); ///////////
	fillComboBox("frmEquipment","cmbMchmManufacturerid","Combo_Manufact.eqp");
	fillComboBox("frmEquipment","cmbMchmMake","Combo_Make.eqp");
	fillComboBox("frmEquipment","cmbMchmModel","Combo_Model.eqp");
	fillComboBox("frmEquipment","cmbMchmSupplierid","Combo_Supplier.eqp");
	fillComboBox("frmEquipment","cmbMchmCurrencyid","Combo_Unit.eqp");
	fillComboBox("frmEquipment","cmbMchmAmcvendor","Combo_Provider.eqp");
	
	var factId = jQuery("#frmEquipment input[id='factory']").val();
	var sectionId = jQuery("#frmEquipment input[id='section']").val();
	var cellId = jQuery("#frmEquipment input[id='cell']").val();
	var machId = jQuery("#frmEquipment input[id='machine']").val();	
	var flid = jQuery("#frmEquipment input[id='flid']").val();

	var dataStr = "&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId;
	if(machId != null && machId != '')
	{		
		jQuery("#cmbMchmKeyid").combobox("readonly",true);
		dataStr += "&machId="+machId;		
	}
	if(flid != null && flid != '')
	{		
		
		dataStr += "&flid="+flid;		
	}
		
	loadFunctionalLocation("eqpmfunLocation","functionalLoc.eqp","equipfunLocationValues","frmEquipment",dataStr);
	
	fillComboBox("frmEquipment","cmbMchmEquipmentgroup","equipmentgroup.commonFilter" );
	fillComboBox("frmEquipment","cmbMchmCostcentreid","costCenter.commonFilter");
	fillComboBox("frmEquipment","cmbMchmCategory","Combo_Category.eqp");	
	 
	formatDateBox('dteMchmInstalleddate','dd-MMM-yyyy');
	formatDateBox('dteMchmEffectivedate','dd-MMM-yyyy');
	formatDateBox('dteMchmJhstepdate','dd-MMM-yyyy');
	formatDateBox('dteMchmPodate','dd-MMM-yyyy');
	formatDateBox('dteMchmPurchasedate','dd-MMM-yyyy');
	formatDateBox('dteMchmWarrantydate','dd-MMM-yyyy');
	formatDateBox('dteMchmManufactureddate','dd-MMM-yyyy');
	formatDateBox('dteMchmAmcdate','dd-MMM-yyyy');
	formatDateBox('dteMchmAmcrenewaldate','dd-MMM-yyyy');

	processGridnew("operator_input.eqp","","grdoperator","Pager","Operator1");
	processGridnew("operatorSkill_input.eqp","","operatorSkill","Pager1","OperatorSkill","","","operatorSkill_afterLoad");
	processGridnew("maintaince_input.eqp","","maintaince","Pager2","Maintaince");
	processGridnew("maintainceSkill_input.eqp","","maintainceSkill","Pager3","MaintainceSkill","","","maintainceSkill_afterLoad");
	 
	 processGridnew("SubEquipment_input.eqp","","subEquipment","Pager5","SubEquipment");
	 
	jQuery('#btnImge').css('display',"none");
	jQuery('#btnConrolPanel').css('display',"none");
	 
	 var keyid =jQuery("#keyid").val();
	 jQuery("#tabEquipment").tabs({ onLoad:function(title){ 
		 jQuery('.tabs-panels').css('height','325');
		 }
	 });
 		jQuery("#tabEquipment").tabs({ onSelect:function(title){  
		var eqpParamRowCnt = jQuery("#equipmentParameter").getGridParam('reccount');

 		var mchmKeyid = jQuery("#cmbMchmKeyid").combobox("getValue");
 		var mchmKeyid1 = jQuery("#txtMchmMachineno").val();
 		
 		if(title == "Operators & Skills" ){
 	 		if( jQuery("#grdoperator").getGridParam('reccount') <= 0 && mchmKeyid != null && mchmKeyid.length>0 ){ 
	 			jQuery("#grdoperator").setGridParam({url:'operrecall_getData.eqp?oprRecall='+mchmKeyid,datatype:'json'}).trigger('reloadGrid');
 	 		}	
 	 		if( jQuery("#operatorSkill").getGridParam('reccount') <= 0 && mchmKeyid!=null && mchmKeyid.length >0) {
 				jQuery("#operatorSkill").setGridParam({url:'operatorSkillRecall_getData.eqp?recall='+mchmKeyid,datatype:'json'}).trigger('reloadGrid');
 			}
 		}
 		else if(title == "Maintenance Team Info" ) {
 	 		if(  jQuery("#maintaince").getGridParam('reccount') <= 0 && mchmKeyid!=null && mchmKeyid.length >0  ){
	 			jQuery("#maintaince").setGridParam({url:'maintainceRecall_getData.eqp?module='+maintaince+'&recall='+mchmKeyid,datatype:'json'}).trigger('reloadGrid');

 	 		}	
			if( jQuery("#maintainceSkill").getGridParam('reccount') <= 0 && mchmKeyid!=null && mchmKeyid.length>0){
 				jQuery("#maintainceSkill").setGridParam({url:'maintainceSkillRecall_getData.eqp?recall='+mchmKeyid,datatype:'json'}).trigger('reloadGrid');
			}	
 			
 		}
 		else if(title == "Equipment Parameters" && ( eqpParamRowCnt == undefined || eqpParamRowCnt  <= 0) ){
			 processGridnew("EquipmentParameter_input.eqp","?q=2&eqpId="+mchmKeyid1,"equipmentParameter","Pager4","EquipmentParameter","eqpDoubleClick","","EquipmentParameter_loadComplete_afterLoad");
 		}
 		else if(title == "Sub Equipments" ){		
 			jQuery("#subEquipment").setGridParam({multiselect: ( true )});	
 			 var sect = jQuery("#section").val();	
 			 var dataString = "?q=2&sect="+sect+'&eqpId='+mchmKeyid;		 
 			 processGridnew("SubEquipment_input.eqp",dataString,"subEquipment","Pager5","SubEquipment");	
 			
 		}
 	}	
 });
 		jQuery('.tabs-panels').css('height','325');
 		

 		//---------------check box manitory or not-------------------//
 		jQuery("#rdchk").click(function(){
 			jQuery('#Pro').attr("class","mandatory-lbl");
 		});
 		jQuery("#rdchk1").click(function(){
 			jQuery('#Pro').attr("class","mndlbl");
 		});

 		jQuery("#chkMchmIsunderwarranty").click(function(){
 			jQuery("#rdchk1").attr('checked',true);
 			jQuery('#Pro').attr("class","mndlbl");
 		});
 		jQuery("#Wrdchk1").click(function(){
 			jQuery("#rdchk").attr('checked',true);
 			jQuery('#Pro').attr("class","mandatory-lbl");
 		});

 		
 		 
 		//-----------------Button Formattor remove function--------------------//
 				  jQuery("#btnoperator").click(function(){			
 					  var factId = jQuery("#frmEquipment input[id='factory']").val();			  	
 						multiSelectPop("operatorData_input.eqp","factId="+factId,"grdoperator","","operatorKeyid,employeecode,employeename","true","MultiSelectCancel_CallBack","","Operator Details");	
 				
 				  });

 				  jQuery("#btnoperSkill").click(function(){			
 						multiSelectPop("SkillData_input.eqp","","operatorSkill","","operatorKeyid,skillRequired","true","MultiSelectCancel_CallBack","","Skills Details");	
 				   });

 				  jQuery("#Maintaince").click(function(){			
 						multiSelectPop("MaintainceData_input.eqp","","maintaince","","","true","MultiSelectCancel_CallBack","","Maintenance Team");			
 						  });
 				  jQuery("#MaintSkill").click(function(){			
 						multiSelectPop("MaintSkillData_input.eqp","","maintainceSkill","","txtmplkParameterid,txtmplkDescription","true","MultiSelectCancel_CallBack","","Skills Required");			
 						  });
 		 		
 				 if(jQuery("#relatedToCavityormandrel").val() != null && jQuery("#relatedToCavityormandrel").val() !='')
 			 	   {					
 			 		 	setFieldValue("cmbMchmIscavityormandrel",jQuery("#relatedToCavityormandrel").val().trim(),"frmEquipment");
 			 		 	
 			 	   }
 				var mchKeyid = jQuery("#cmbMchmKeyid").combobox("getValue");
 				processGridnew("MaintSkillData_input.eqp","?q=1","catgGrid","","Catagory","vatg","","catgComplete");		
 				processGridnew('circleFormGrid_input.eqp','?keyid='+mchKeyid+'&q=2',"catgGrid","","","","","","");

 				 
 				fileManagerPopUp(jQuery('#cmbMchmKeyid').combobox('getValue'),"MCH","frmEquipment","btnFilManage","eqpFilemgr");		
});    //close Load Complete



//----------------------------------Grid Editable False for Maintaince & Operator Skill--------------------------
/*for file manager*/

jQuery("#btnFilManage").click(function(){
	
    var documentNo =jQuery('#cmbMchmKeyid').combobox('getValue');
	
	if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"MCH","","","");
	}
	else
		alert("Machine should be selected to view FileManager");
});

function btnFilManage_click(){
        
	    var documentNo =jQuery('#cmbMchmKeyid').combobox('getValue');
		
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"MCH","","","");
		}
		else
			alert("Machine should be selected to view FileManager");
 		}
function maintainceSkill_afterLoad(result)	{	
		jQuery('#maintainceSkill').setGridParam({cellEdit:false});
	}
	
function operatorSkill_afterLoad(result){
	jQuery('#operatorSkill').setGridParam({cellEdit:false});
}
	
function frmEquipment_FuntLocHierarchy_SuccessCallBack(keyIds)
{	
	//getEquipment(keyIds.machId);
	reloadCombo("frmEquipment","cmbMchmCostcentreid","costCenter.commonFilter?cellId="+keyIds.cellId +"&factId="+keyIds.factId );
	reloadCombo("frmEquipment","cmbMchmEquipmentgroup","equipmentgroup.commonFilter");
	
	if(keyIds.machId != null && keyIds.machId !='' && keyIds.machId.length<0){
		
		 setFieldValue('cmbMchmKeyid',keyIds.machId);	
		 getEquipment(keyIds.machId);
	}	
	var hdnMode = jQuery("#hdnMode").val();
	
	if(hdnMode != "update")
		getEquipment(keyIds.machId);
	
	if(hdnMode == "update"){
		
		//disableField("frmEquipment", "txtMchmMachinename");
		//disableField("frmEquipment", "txtMchmMachineno");
		disableField("frmEquipment", "cmbMchmEquipmentgroup");
		
		/* jQuery("#btnfrmEquipmentmainFunLoc").click(function() { 
		 jQuery("#functLocHierarPopupId").dialog('close'); });*/
		 jQuery('#dispFunctionalLoc a').click(function() { jQuery("#functLocHierarPopupId").dialog('close'); });
			
	}
}


function openDLG()
{
	var title=jQuery("#hdnParamGrid").val();
	jQuery( "#eqp_text" ).dialog({
		autoOpen: false,
		modal: true,
		height: 100,
		name:"Equipment Parameter",
		width : 330,
		title : title 
 	});
}
	

function chkbox_subEquipment(id, options, rowObject)
{	
	var rowId = options.rowId;	
	return '<input id="SubEquipment_checkbox" name="SubEquipment_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{
	jQuery("#subEquipment").jqGrid('setCell',rowId,'checkvalue','1');		
}
function chkboxUnCheck(rowId){
	jQuery("#subEquipment").jqGrid('setCell',rowId,'checkvalue','0');	
}
function viewGrid(url,filterString,category)
{
	if(category=="operator")
	{
		processGridnew(url,filterString,"grdoperator","OperatorPager");
	}
	else if(category=="pillar")
	{
		var tableCaption = "";
		processGridnew(url,filterString,"pillarGrid","pillarPager",tableCaption,"","","","selectPillarGridRow");
	}
}

function  frmEquipmentcmbMchmKeyid_onSelect(record){
	getEquipment(record.id);
	
	loadFunctionalLocation("eqpmfunLocation","functionalLoc.eqp","equipfunLocationValues","frmEquipment","&machId="+record.id);
	processGridnew('circleFormGrid_input.eqp','?keyid='+record.id+'&q=2',"catgGrid","","","","","","");
	
}
function getEquipment(keyid){	
	 processAjaxCalls("recall_select.eqp" ,"keyId="+keyid, "frmEquipmentSelect_successsCallback","frmEquipment_errorCallback");
	 clearGridData();
}

function clearGridData(){
	jQuery('#grdoperator').jqGrid('clearGridData');
	jQuery('#operatorSkill').jqGrid('clearGridData');
	jQuery('#maintaince').jqGrid('clearGridData');
	jQuery('#maintainceSkill').jqGrid('clearGridData');
	jQuery('#subEquipment').jqGrid('clearGridData');
	jQuery('#equipmentParameter').jqGrid('clearGridData');	 	
}

function frmEquipment_successsCallback(result){
	clearGridData();
	navigateToPrevForm();
	reloadCombo("frmEquipment","cmbMchmKeyid","machineCombo.commonFilter");
}
function frmEquipmentSelect_successsCallback(result){		
	
			jQuery("#cmbMchmKeyid").combobox("setValue",result.equipmentData.MchmKeyid);				
			jQuery("#cmbMchmCostcentreid").combobox("setValue",result.equipmentData.MchmCostcentreid);			
			jQuery("#cmbMchmCellid").combobox("setValue",result.equipmentData.MchmCellid);						
			jQuery("#cmbMchmEquipmentgroup").combobox("setValue",result.equipmentData.MchmEquipmentgroup);		
			jQuery("#cmbMchmSubcellid").combobox("setValue",result.equipmentData.MchmSubcellid);			
			jQuery("#txtMchmMachinename").val(result.equipmentData.MchmMachinename);
			jQuery("#cmbMchmSubcategory").combobox("setValue",result.equipmentData.MchmSubcategory);					
		 	jQuery("#txtMchmMachineno").val(result.equipmentData.MchmMachineno);
			jQuery("#txtAssetNo").val(result.equipmentData.AssetNo);	
		 	jQuery("#cmbMchmWorkcenter").combobox("setValue",result.equipmentData.MchmWorkcenter);	
		 	jQuery("#dteMchmInstalleddate").datebox("setValue",result.equipmentData.MchmInstalleddate);
		 	jQuery("#dteMchmEffectivedate").datebox("setValue",result.equipmentData.MchmEffectivedate);
		 	jQuery("#cmbMchmMachinerank").combobox("setValue",result.equipmentData.MchmMachinerank);
			jQuery("#cmbMchmCircleid").combobox("setValue",result.equipmentData.MchmCircleid);
			jQuery("#cmbMchmJhstep").combobox("setValue",result.equipmentData.MchmJhstep);
			jQuery("#dteMchmJhstepdate").datebox("setValue",result.equipmentData.MchmJhstepdate);
		 	jQuery("#cmbMchmPurpose").combobox("setValue",result.equipmentData.MchmPurpose);
			jQuery("#cmbMchmCategory").combobox("setValue",result.equipmentData.MchmCategory);
			jQuery("#cmbMchmPowersupply").combobox("setValue",result.equipmentData.MchmPowersupply);
			jQuery("#cmbMchmConnectedload").combobox("setValue",result.equipmentData.MchmConnectedload);
			
			jQuery("#txtMchmPhase").val(result.equipmentData.MchmPhase);
			jQuery("#txtMchmWires").val(result.equipmentData.MchmWires);
			jQuery("#txtMchmIpvolt").val(result.equipmentData.MchmIpvolt);
			jQuery("#txtMchmIpvoltmin").val(result.equipmentData.MchmIpvoltmin);
			jQuery("#txtMchmIpvoltmax").val(result.equipmentData.MchmIpvoltmax);
				
			jQuery("#cmbMchmDbno").combobox("setValue",result.equipmentData.MchmDbno);
			jQuery("#txtMchmIpfreq").val(result.equipmentData.MchmIpfreq);
			jQuery("#txtMchmIpfreqmin").val(result.equipmentData.MchmIpfreqmin);
			jQuery("#txtMchmIpfreqmax").val(result.equipmentData.MchmIpfreqmax);
			jQuery("#cmbMchmSbno").combobox("setValue",result.equipmentData.MchmSbno);
			
			jQuery("#cmbMchmManufacturerid").combobox("setValue",result.equipmentData.MchmManufacturerid);
			jQuery("#cmbMchmMake").combobox("setValue",result.equipmentData.MchmMake);
			jQuery("#cmbMchmModel").combobox("setValue",result.equipmentData.MchmModel);
			
			jQuery("#cmbMchmSupplierid").combobox("setValue",result.equipmentData.MchmSupplierid);
			
			jQuery("#txtMchmPono").val(result.equipmentData.MchmPono);
			jQuery("#txtMchmPurchaseprice").val(result.equipmentData.MchmPurchaseprice);
			jQuery("#txtMchmMfrslno").val(result.equipmentData.MchmMfrslno);
			jQuery("#txtMchmAmcremarks").val(result.equipmentData.MchmAmcremarks);
			jQuery("#txtMchmMfrremarks").val(result.equipmentData.MchmMfrremarks);
			jQuery("#txtMchmSupplierremarks").val(result.equipmentData.MchmSupplierremarks);
			jQuery("#cmbMchmAmcvendor").combobox("setValue",result.equipmentData.MchmAmcvendor);
			
			jQuery("#cmbMchmCurrencyid").combobox("setValue",result.equipmentData.MchmCurrencyid);
			jQuery("#dteMchmPodate").datebox("setValue",result.equipmentData.MchmPodate);
		 	jQuery("#dteMchmManufactureddate").datebox("setValue",result.equipmentData.MchmManufactureddate);
		 	jQuery("#dteMchmPurchasedate").datebox("setValue",result.equipmentData.MchmPurchasedate);
		 	jQuery("#dteMchmWarrantydate").datebox("setValue",result.equipmentData.MchmWarrantydate);
		 	jQuery("#grdoperator").trigger("reloadGrid"); 
		 	jQuery("#dteMchmAmcdate").datebox("setValue",result.equipmentData.MchmAmcdate);
		 	jQuery("#dteMchmAmcrenewaldate").datebox("setValue",result.equipmentData.MchmAmcrenewaldate);
		 	jQuery("#chkMchmIncludeforproduction").val(result.equipmentData.MchmIncludeforproduction);
		 	jQuery("#chkMchmGivesfinaloutput").val(result.equipmentData.MchmGivesfinaloutput);
		 	jQuery("#relatedToCavityormandrel").val(result.equipmentData.MchmIscavityormandrel);
		 	
		 	
		 	 if(jQuery("#relatedToCavityormandrel").val() != null && jQuery("#relatedToCavityormandrel").val() !='')
		 	   {					
		 		 	setFieldValue("cmbMchmIscavityormandrel",jQuery("#relatedToCavityormandrel").val().trim(),"frmEquipment");
		 	   }
		 	
		 	 var eqpGrp = jQuery("#cmbMchmEquipmentgroup").combobox('getValue');
		 	
				if(eqpGrp != null && eqpGrp != '')
				{
					jQuery("#cmbMchmEquipmentgroup").combobox("readonly",true);
				}
				
				
					
}


		
 function BtnFormatterOperator(id, options, rowObject)
 {					
 	var rowId = options.rowId;

 	return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
 }
		
			function removeOperator(rowId){	
				var machineId = getFieldValue('cmbMchmKeyid');
				var empId = getGridCell('grdoperator',rowId,'txtmcemEmployeeid');
				var empName = getGridCell('grdoperator',rowId,'txtmcemEmployeeName');
				
				if( machineId != null && machineId.length > 0  )	
					processAjaxCalls("operator_remove.eqp", "rowId="+rowId +"&machId=" + machineId +"&empId="+empId +'&empName='+empName, 'removeOperator_successCallBack','removeOperator_errorCallBack')	;
				else
					jQuery("#grdoperator").delRowData(rowId);
			}

				function removeOperator_successCallBack(result){
					
					var r=confirm("Do You Want To Delete?");
					if (r==true)
					  {						
						jQuery("#grdoperator").delRowData(result.successData.rowId);	
					  }
					else  {	  }
									}

				function BtnFormatterMaintaince(id, options, rowObject)
				  {					
				  	var rowId = options.rowId;				  
				  	return '<input type="button" id="remov" class="grdButton"  onclick="removeMaintaince(\''+rowId + '\');"/>';
				  }			

				function BtnFormatterOperSkill(id, options, rowObject)
				  {					
				  	var rowId = options.rowId;				  	
				  	return '<input type="button" id="remov" class="grdButton"  onclick="removeOperatorSkill(\''+rowId + '\');"/>';
				  }
		
				function removeOperatorSkill(rowId)
				{					
					var machineId = getFieldValue('cmbMchmKeyid');
					var skillName = getGridCell('operatorSkill',rowId,'txtmskmSkilldescription');
					var grdMachineId = getGridCell('operatorSkill',rowId,'txtmskmMachineid');
					
					if( skillName != null && skillName.length > 0 )	{						
						processAjaxCalls("operatorSkill_remove.eqp", "rowId="+rowId +"&machId=" + machineId +"&skillName="+skillName, 'removeOperatorSkill_successCallBack','removeOperatorSkill_errorCallBack')	;
					}
					else{						
						jQuery("#operatorSkill").delRowData(rowId);
					}
				}

				function removeOperatorSkill_successCallBack(result){
					
					var r=confirm("Do You Want To Delete?");
					if (r==true)
					  {					
						jQuery("#operatorSkill").delRowData(result.successData.rowId);	
					  }
					else {					 
					  }
											
				}

				function BtnFormatterMaintaince(id, options, rowObject)
				  {					
				  	var rowId = options.rowId;				 
				  	return '<input type="button" id="remov" class="grdButton"  onclick="removeMaintaince(\''+rowId + '\');"/>';
				  }

				function removeMaintaince(rowId)
				{
					var machineId = getFieldValue('cmbMchmKeyid');
					var maintTeamId = getGridCell('maintaince',rowId,'txtmcmtMaintenanceteamid');
					var maintTeamCode = getGridCell('maintaince',rowId,'MaintainceCode');
					
					if( machineId != null && machineId.length > 0 )	
						processAjaxCalls("maintaince_remove.eqp", "rowId="+rowId +"&machId=" + machineId +"&maintTeamId="+maintTeamId+'&maintTeam='+maintTeamCode, 'removeMaintainceTeam_successCallBack','removeMaintainceTeam_errorCallBack')	;
					else
						jQuery("#maintaince").delRowData(rowId);
				}

				function removeMaintainceTeam_successCallBack(result){
					
					var r=confirm("Do You Want To Delete?");
					if (r==true)
					  {				
						jQuery("#maintaince").delRowData(result.successData.rowId);	
					  }
					else  {	  }
												
				}
				
				function BtnFormatterMaintainceSkill(id, options, rowObject)
				  {					
				  	var rowId = options.rowId;				 
				  	return '<input type="button" id="remov" class="grdButton"  onclick="removeMaintainceSkill(\''+rowId + '\');"/>';
				  }		
				
				function removeMaintainceSkill(rowId)
				{
					var machineId = getFieldValue('cmbMchmKeyid');
					var maintTeamSkill = getGridCell('maintainceSkill',rowId,'txtmskmSkilldescription');
					var grdMachineId = getGridCell('maintainceSkill',rowId,'txtmskmMachineid');
					
					if( maintTeamSkill != null && maintTeamSkill.length > 0 )	
						processAjaxCalls("maintainceSkill_remove.eqp", "rowId="+rowId +"&machId=" + machineId +"&maintTeamSkill="+maintTeamSkill, 'removeMaintainceTeamSkill_successCallBack','removeMaintainceTeamSkill_errorCallBack')	;
					else
						jQuery("#maintainceSkill").delRowData(rowId);
				}

				function removeMaintainceTeamSkill_successCallBack(result){
					
					var r=confirm("Do You Want To Delete?");
					if (r==true) {					
						jQuery("#maintainceSkill").delRowData(result.successData.rowId);
					  }
					else  {	  }
									
				}			
				
//-------------------- Add Row in grid----------------------------//
				jQuery("#btnoperSkillRowAdd").click(function(id){						
					var rowid = jQuery("#operatorSkill").jqGrid('getDataIDs');					
					addRow("add");					
				 });

				jQuery("#maintSkillRowAdd").click(function(id){		
					var rowid = jQuery("#maintainceSkill").jqGrid('getDataIDs');
					jQuery("#editmodlist").css('height','100px');
					maintAddRow("add");
				} );
				

				 jQuery("#maintedtbtn").click(function(){
						var row_id = jQuery("#maintainceSkill").jqGrid('getGridParam','selrow');					
						 if( row_id != null ){ 
							 maintAddRow("edit",row_id);
						 }
						 else{
							
						}
					});


 //--------------------------Delete Success Call Back-------------------//
function frmEquipment_beforeDelete()
{
	var inactmsg = jQuery("#hdnmsg").val();
	
	/*if(confirm(inactmsg))
		{
		
		checkConfirm();
		}
	else 
		return false;*/
		
}
function frmEquipment_deleteSuccessCallback(result)
{

if(result.successData.msg=="OriginalIdExists")
	{
		var inactmsg = result.successData.Errmsg;
	alert("Reference Exist in Function Location! Delete From Function Location Before You Proceed.");
		/*if(confirm(inactmsg))
		{
			checkConfirm();
		}
		else 
			return false;*/
	}
/*else
	{
	alert(result.successData.msg);
	navigateToPrevForm();
	}*/
	
	
}


function checkConfirm()
{
	var inact = jQuery('#hdnInactive').val("Inactive");	
	deleteRecord("frmEquipment","equipment_delete.eqp?hdnInactive=Inactive");
	
}	

//----------------------Edit button in grid-------------//
				jQuery("#edtbtn").click(function(){
					var row_id = jQuery("#operatorSkill").jqGrid('getGridParam','selrow');
				
					 if( row_id != null ){ 
						 addRow("edit",row_id);
					 }
					 else{
						
					 }
				});
		//-------------------------Grid Save--------------------		
function frmEquipment_beforeSubmit(){
	
		var gridData = '&mchOperators='+JqGridToJsonSelectdRows('grdoperator','remove','txtmcemEmployeeid');
		gridData += '&maintaince='+JqGridToJsonSelectdRows('maintaince','remove','MaintainceCode');
		gridData += '&operatorSkill='+JqGridToJsonSelectdRows('operatorSkill','remove','txtmskmSkilldescription');
		gridData += '&maintainceSkill='+JqGridToJsonSelectdRows('maintainceSkill','remove','txtmskmSkilldescription');		
		gridData += '&equipmentParameter='+JqGridToJsonSelectdRows('equipmentParameter','','txtmplkParameterid');
		gridData += '&subEquipment='+getSelectdRows('subEquipment','SubEquipment_checkbox','checkvalue');
		
		
		return gridData; 			
}		
		
function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];		
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
				jsonArrO += '{';
			for(var colName in row) {
				var cellValue = parseJqGridCellValue(row[colName]);
			
					if(colName == 'txtscmlChildmchid')
						jsonArrO += '"'+colName +'":"' + cellValue+'"';
					
			}
			jsonArrO +=  "},";
			
		}
	} 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
}
			function maintAddRow(para,id){
					  var val=null;
					  var txtmskmSkilldescription ;	
					  jQuery("#editmodmaintainceSkill").css('height','100px');
					  jQuery('#txtmskmSkilldescription').css('text-transform', 'uppercase');	
					  
					  var MaintainceSkill =	jQuery('#txtmskmMachineid').val();				
						 if(para=="edit")
							val=id;							
						 else if(para=="add")					
							val="new";												
						jQuery("#maintainceSkill").jqGrid('editGridRow',val,{
							height:150,
							reloadAfterSubmit:false,							
							top : 70,
							left:20,
							url:"test.eqp"							
					});
						 var rowCount = jQuery("#maintainceSkill").getGridParam("reccount");						
				}

				function addRow(para,id){
					  var val=null;
					  var txtmskmSkilldescription ;	
					  jQuery("#editmodoperatorSkill").css('height','100px');
					  jQuery('#txtmskmSkilldescription').css('text-transform', 'uppercase');	
					  var txtmskmMachineid;					
					  var operatorSkill =	jQuery('#txtmskmMachineid').val();	
					 
						 if(para=="edit")
							val=id;							
						 else if(para=="add")					
							val="new";
												
						jQuery("#operatorSkill").jqGrid('editGridRow',val,{
							height:150,
							reloadAfterSubmit:false,							
							top : 70,
							left:20,
							url:"test.eqp"								
					});

						 var rowCount = jQuery("#operatorSkill").getGridParam("reccount");

				}

//----------------------Equipment Paramter Grid Functions--------------------------//				
				function eqpDoubleClick(id)
				{		
					jQuery("#eqp_text").css('display','block');
					 var rowData = jQuery("#equipmentParameter").jqGrid('getRowData',id);
					jQuery("#hdnParamGrid").val(rowData.txtParameter);
					 var myCellData ="";	
					 var rowVal = rowData.EqpParamMode;
					 					
					  if(rowVal.trim()=="ListText"){
  					      myCellData = rowData.txtmplkDescription;
					  }
					  else if(rowVal.trim()=="ListDropdown"){
						  return cellval;
					  }
					  else if(rowVal.trim()=="ListNumber"){
						  myCellData = rowData.txtmplkTempfield1;
					  }
					  
					 jQuery("#txtdataList").val(myCellData);	
					 openDLG();				
				}
				
function EquipmentParameter_loadComplete_afterLoad(){
	
	var dateBox = jQuery(':input[id^="dteEqpParam_"]');
	jQuery(dateBox).each(function () {
		formatDateBox(this.id,'DD-MMM-YYYY');
	});	
	var combo =jQuery(':input[id^="cmbo_"]');
	jQuery(combo).each(function () {
		
		initialiseComboBox(this.id);
	});
	
	var rowIds = jQuery("#equipmentParameter").getDataIDs();	
	for(var i = 0;i<rowIds.length;i++)
		{
			var colFlag = jQuery("#equipmentParameter").jqGrid('getCell',rowIds[i],4);
			var description = jQuery("#equipmentParameter").jqGrid('getCell',rowIds[i],3);
			
			if(colFlag== 'ListText')
				jQuery("#equipmentParameter").jqGrid('setCell',rowIds[i],"txtmplkTempfield1","",{'background-color':'#D9D7D0','color':'#ffffff'});
			if(colFlag== 'ListNumber'){
				jQuery("#equipmentParameter").jqGrid('setCell',rowIds[i],"txtmplkDescription","",{'background-color':'#D9D7D0','color':'#ffffff'});
			}
			if(colFlag== 'ListDropdown')
				{
				jQuery("#equipmentParameter").jqGrid('setCell',rowIds[i],"txtmplkDescription","",{'background-color':'#D9D7D0','color':'#ffffff'});
				}
		}

		
}		
function EquipmentParameter_loadComplete(){
	jQuery("#equipmentParameter").children().removeClass("ui-jqgrid-sortable");
}
function equip_Formatter(cellval, options, rowObject)
{		
	var colFlag = rowObject[3];	
	if(rowObject[4] == 'ListText')
	{
		return cellval;
	}
	else if( rowObject[4] == 'ListNumber'){	
			 return colFlag;	
	}	
	else if( rowObject[4] == 'ListDropdown'){
		var displayNames = rowObject[5];
		var comboDisp = displayNames.split(",");
		var codeVal =  rowObject[6];
		var comboVal = codeVal.split(",");
		 
		var comboBox = "<select id='combo_"+rowObject[0].replace("/","") +"'  style='width:155px;' name='combo_"+rowObject[0].replace("/","") +"' class='easyui-combobox' value='Y'>";
		for( var i =0; i<comboDisp.length; i++ ){
			
			if(rowObject[3]== comboVal[i])
				comboBox += "<option selected='selected' value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
			else
				comboBox += "<option  value='" + comboVal[i] +"'>" + comboDisp[i] + "</option>"; 
		} 
		comboBox += "</select>";		
		return comboBox;
		
	}else if( rowObject[4] == 'ListDate'){
		return '<input id="dteEqpParam_'+options.rowId+'" width="70px"  class="easyui-datebox" />';
		
	}else if( rowObject[4] == 'ListSelection'){
		return cellval;
	}		
}	 


function equipLong_Formatter(cellval, options, rowObject)
{
	if(rowObject[4] == 'ListText')
	{
		if(cellval == '' || cellval == null)
			cellval = ' ';
		 	
		return cellval;	
	}
	else if( rowObject[4] == 'ListNumber'){
		
		cellval=' ';
		 return cellval;	
	}	
	else if( rowObject[4] == 'ListDropdown'){
		 return ' ';	
	}
	else
		{
			return cellval;
		}
}
//----------------Dialog Save--------------------//	  	
			 jQuery('#save').click(function(){
				 
				 	var rowId = jQuery("#equipmentParameter").jqGrid('getGridParam', 'selrow');							
					var data = jQuery("#txtdataList").val();
					var colIndx="txtmplkDescription";	
					
					var colFlag = jQuery("#equipmentParameter").jqGrid('getCell',rowId, 'EqpParamMode');//"EqpParamMode");
					
					if(colFlag == 'ListNumber'){
						colIndx = "txtmplkTempfield1"; 
							if(!isNaN(data))
				        {
					        jQuery("#"+rowId + " td:eq(2)").html(data); //colIndx = "txtmplkTempfield1";
				        }	
						else			
							alert("Enter Numeric value");
					}
					else
						jQuery("#equipmentParameter").jqGrid('setCell', rowId, colIndx, data);
					
					jQuery( "#eqp_text" ).dialog("close");
					
				});			
			 jQuery('#Cancel').click(function(){

					jQuery( "#eqp_text" ).dialog("close");
				
				});

				
  //-------------------------------On load Success---------------------------//

				function frmEquipmentcmbMchmfact_onSelect(record)
				{		
					jQuery("#cmbSection").combobox('clear');
					jQuery("#cmbMchmCellid").combobox('clear');
		    		jQuery("#cmbMchmSubcellid").combobox('clear');
		    		reloadCombo("frmEquipment","cmbSection","sectionCombo.commonFilter?factId="+record.id);
		    		reloadCombo("frmEquipment","cmbMchmCellid","cellCombo.commonFilter?sectId="+record.id);	
		    		reloadCombo("frmEquipment","cmbMchmSubcellid","Combo_SubSect.eqp?factId="+record.id);
				}
				 function frmEquipmentcmbSection_onSelect(record)
				 {
					 jQuery("#cmbMchmCellid").combobox('clear');
					 reloadCombo("frmEquipment","cmbMchmCellid","cellCombo.commonFilter?sectId="+record.id);				
				}
				 function frmEquipmentcmbMchmCategory_onSelect(record)
				 {			
					 jQuery("#cmbMchmSubcategory").combobox('clear');	
					 reloadCombo("frmEquipment","cmbMchmSubcategory","Combo_SubCategory.eqp?catagory="+record.id);	 					
				}
				 function frmEquipmentcmbMchmCellid_onSelect(record)
				 {
					jQuery("#cmbMchmCostcentreid").combobox('clear');	
					 fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbSection","cmbMchmfact","");
				}

				 function frmEquipmentcmbMchmCostcentreid_onSelect(record)
				 {
				 }
				
					
			 function frmEquipmentcmbMchmKeyid_onLoadSuccess(){
				
			 }
			
			 function frmEquipmentcmbMchmCostcentreid_onLoadSuccess(){
					 
			 }
			 function frmEquipmentcmbMchmEquipmentgroup_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmSubcellid","Combo_SubSect.eqp");
			 }			 
			 function frmEquipmentcmbMchmSubcellid_onLoadSuccess(){
				// fillComboBox("frmEquipment","cmbMchmWorkcenter","Combo_workCenter.eqp");
				 }			
			 function frmEquipmentcmbMchmWorkcenter_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmMachinerank","Combo_MachineRank.eqp");
				 }			
			 function frmEquipmentcmbMchmMachinerank_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmCircleid","Combo_Circle.eqp");
				 }
			 function frmEquipmentcmbMchmCircleid_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmPurpose","Combo_Purpose.eqp");
				 }
			 function frmEquipmentcmbMchmPurpose_onLoadSuccess(){
				 fillComboBox("frmEquipment","cmbMchmCategory","Combo_Category.eqp");					
				 }			
			 function frmEquipmentcmbMchmCategory_onLoadSuccess(){
				// fillComboBox("frmEquipment","cmbMchmSubcategory","Combo_SubCategory.eqp");
				 }
			 function frmEquipmentcmbMchmSubcategory_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmJhstep","Combo_JhStep.eqp");	
					
				 }
				
			 function frmEquipmentcmbMchmJhstep_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmPowersupply","Combo_PowerSupply.eqp");
				 }
			 function frmEquipmentcmbMchmPowersupply_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmConnectedload","Combo_ConnectedLoad.eqp");
					
				 }
			 function frmEquipmentcmbMchmConnectedload_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmDbno","Combo_DBNo.eqp");
					
				 }
			 function frmEquipmentcmbMchmDbno_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmSbno","Combo_SBNo.eqp");
					
				 }
			 function frmEquipmentcmbMchmSbno_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmManufacturerid","Combo_Manufact.eqp");
					
				 }
			 function frmEquipmentcmbMchmManufacturerid_onLoadSuccess(){

				 //fillComboBox("frmEquipment","cmbMchmMake","Combo_Make.eqp");
				 
				 }
			 function frmEquipmentcmbMchmMake_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmModel","Combo_Model.eqp");
					
				 }
			 function frmEquipmentcmbMchmModel_onLoadSuccess(){
				 //fillComboBox("frmEquipment","cmbMchmSupplierid","Combo_Supplier.eqp");
					
					 }
			 function frmEquipmentcmbMchmSupplierid_onLoadSuccess(){
//				 fillComboBox("frmEquipment","cmbMchmCurrencyid","Combo_Unit.eqp");
					
					}
			 function frmEquipmentcmbMchmCurrencyid_onLoadSuccess(){
				// fillComboBox("frmEquipment","cmbMchmAmcvendor","Combo_Provider.eqp");
				 }
			 function frmEquipmentcmbProvider_onLoadSuccess(){}
			 
	
			 jQuery("#imgAddCircle").click(function(){
				 var mchKeyid = jQuery("#cmbMchmKeyid").combobox("getValue");
					
					
					if(mchKeyid != null && mchKeyid != '' && mchKeyid != ' ')
					{
						var dataString = '?q=2';
							dataString += '&keyid='+mchKeyid;
					
						LoadPopUp("divAddCircle","circleGrid_input.eqp"+dataString, true,"40%","78%","0","20%", "ADDCIRCLE_Callback","Circle");
						
					}			
					//else
						//saveForm('frmAccidentIncident','safteyincident_save.accIncRpt?q=2&saveMode=ADDEMP');	
				});
			 
		
			 
			 function divAddCircle_afterClose(){
					jQuery('#catgGrid').trigger("reloadGrid");
					return true;
				}
			 
</script>
<form name="frmEquipment" id="frmEquipment" action="" method="post">
<div id="wrapper">
<div class="main-cntborder" style="height:580px; width:1100px;">
  <input type="hidden" id="hdnInactive" name="hdnInactive"></input>
  <input type="hidden" id="keyid" value="${requestScope.equipmentkey}"></input>
                   
  <div  style="padding-left:2%;" align="left">   
   <div ><label>Equipment</label></div>
   <div class="easyui-paddingbfpx">
   <input id="cmbMchmKeyid" name="cmbMchmKeyid" class="easyui-combobox"  style="width:320px;" value="${requestScope.genTlMachinemst.mchmKeyid}" ${requestScope.EquipmentBean.disableMchmKeyid == true ? ' readonly':''}  > 
   </div>
	</div>	
	
	<div  id="frmAbnormalityFuntKeyIds"  align="left">
	<input type="hidden" id="company" name="cmbcompany" value="${requestScope.EquipmentBean.company}"></input>
	<input type="hidden" id="factory" name="cmbMchmfact" value="${requestScope.EquipmentBean.factory}"></input>
	<input type="hidden" id="section" name="cmbSection" value="${requestScope.EquipmentBean.section}"></input>
	<input type="hidden" id="cell" name="cmbMchmCellid" value="${requestScope.genTlMachinemst.mchmCellid}"  ></input>
	<input type="hidden" id="machine" name="cmbMchmKeyid" value="${requestScope.genTlMachinemst.mchmKeyid}"  ></input>
	<input type="hidden" id="flid" name="cmbMchmFlid" value="${requestScope.genTlMachinemst.mchmFlid}"  ></input>
	
	</div>
	<div id="eqpmfunLocation"  style="width: 116%; padding-left:2%;" align="left"></div>	

	<table width="80%"  border="0" align="center"; style="float:left;">   
	<tr>			
	<td style="padding-left:20px;float:left;">	
	<div ><label> Sub Section</label></div>
          <div class="easyui-paddingbfpx">
          <input id="cmbMchmSubcellid" name="cmbMchmSubcellid" clear="false" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmSubcellid}"  ${requestScope.EquipmentBean.disableMchmSubcellid == true ? ' readonly':''} >
          </div>
	         <div > <label class="mandatory-lbl">Equipment No</label></div>
        <div class="easyui-paddingbfpx""><span>
        <input id="txtMchmMachineno" type="text" class="easyui-text" name="txtMchmMachineno" style="width: 255px; height : 21px;"  value="${requestScope.genTlMachinemst.mchmMachineno}"  ${requestScope.EquipmentBean.disableMchmMachineno == true ? ' readonly':''}></span></div>
	
	</td>
	<td   style="padding-left:2%;" >
            
		  <div > <label class="mandatory-lbl">	 Cost Center</label></div>
        <div class="easyui-paddingbfpx"">
        <input id="cmbMchmCostcentreid" name="cmbMchmCostcentreid" clear="false"  class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmCostcentreid}"  ${requestScope.EquipmentBean.disableMchmCostcentreid == true ? ' readonly':''}> </div>
         		<div  > <label class="mandatory-lbl">Equipment Name</label></div>
           <div class="easyui-paddingbfpx"" >
           <input id="txtMchmMachinename" type="text" class="easyui-text"  name="txtMchmMachinename"  style="width: 255px; height : 21px;" value="${requestScope.genTlMachinemst.mchmMachinename}" ${requestScope.EquipmentBean.disableMchmMachinename == true ? ' readonly':''}>
           <span id="err_txtMchmMachinename" class="tpm-errormsg"></span>   
           </div>
         
         
          </td>
         <td  style="vertical-align:top; padding-left:2%;position:relative; width : 320px;" >
		
		<div ><label style="width:120px;" class="mandatory-lbl">Eqp Main Group</label></div>
        <div class="easyui-paddingbfpx"">
        <input id="cmbMchmEquipmentgroup" name="cmbMchmEquipmentgroup" clear="false" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmEquipmentgroup}" ${requestScope.EquipmentBean.disableMchmEquipmentgroup == true ? ' readonly':''}/>  
		</div>
		
       <div  class="easyui-paddingbfpx" style="padding-top: 10;position:relative;">
		<span id="eqpFilemgr" style="position:absolute;">
		 <!-- <input class="easyui-button" id="btnFilManage" type="button" value="File Manager" style=" height : 21px;"> </input> -->
		</span>
		
        </div> 
       
	  </td>
	     </tr>
       </table>  
       <div style="clear: both;"></div>         
       <div class="easyui-paddingbfpx" style="padding-left:65%;">
       </div>
           <div class="clear"> </div>       
               
			<div  id="tabEquipment" class="easyui-tabs"  style="height:345px;width:930px;padding-left:10px;margin-left:8px; float:left">
				<!--First tab Start-->
				<div title="Basic" style="margin-left:1px;height:315px;">
				<div>
				
				
				<div style="float:left; ">
				
				
				<div style="width:94%;_width:80%;padding-left:-50px;float:left;" class="sub-header" >
				<label  >
				<b>Equipment Details</b></label></div>
				
				<table style="width:%; margin-left:4px;margin-left:-15%\9;padding-left:-100%;" >
				<tr style=" float:left;" >
				<td >
				<div><label> Asset No</label></div>
				<div class="easyui-paddingbfpx" >
				<input id="txtMchmControltype" type="text" class="easyui-text"  name="txtMchmControltype"  style="width: 255px; height : 21px;" ; value="${requestScope.genTlMachinemst.mchmControltype}"  ${requestScope.EquipmentBean.disableMchmControltype == true ? ' readonly':''}></div>
				
				<div > <label>Work Center</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmWorkcenter" clear="false"  name="cmbMchmWorkcenter" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmWorkcenter}"   ${requestScope.EquipmentBean.disableMchmWorkcenter == true ? ' readonly':''}>
				</div>
				
				<div ><label>Equipment Rank</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmMachinerank" clear="false"  name="cmbMchmMachinerank" class="easyui-combobox" clear="false" style="width:255px;"  value="${requestScope.genTlMachinemst.mchmMachinerank}"  ${requestScope.EquipmentBean.disableMchmMachinerank == true ? ' readonly':''} >
				</div>
				
				<!--<div ><label>Circle</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmCircleid" clear="false"  name="cmbMchmCircleid" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmCircleid}"  <c:out value = "${requestScope.EquipmentBean.disableMchmCircleid == true ? ' readonly':''}"/> >
				</div>-->
				
				<div ><label>Purpose</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmPurpose" name="cmbMchmPurpose"  class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmPurpose}"   ${requestScope.EquipmentBean.disableMchmPurpose == true ? ' readonly':''}>
				</div>
								
				<div  class="easyui-paddingbfpx">
	         		<input id="chkMchmIncludeforproduction" name="chkMchmIncludeforproduction" type="checkbox" value="Y" 
	         					
	         					${ requestScope.genTlMachinemst.mchmIncludeforproduction == 'Y' ? ' checked':''}   ${requestScope.EquipmentBean.disableMchmIncludeforproduction == true ? ' readonly':''} /> <label>Include in Production </label>
	            	<span  style="margin-left: 2px;"> 
	                <input id="chkMchmGivesfinaloutput"  name="chkMchmGivesfinaloutput" type="checkbox" value="Y" 
	                			
	                			${ requestScope.genTlMachinemst.mchmGivesfinaloutput == 'Y' ? ' checked':''} ${requestScope.EquipmentBean.disableMchmGivesfinaloutput == true ? ' readonly':''}/> <label>Final Output Machine</label></span>            	
           	    </div> 
					
					<div > <label>Based</label></div>	
				<div>
					<select id="cmbMchmIscavityormandrel" clear="false"  class="easyui-combobox" name="cmbMchmIscavityormandrel"   ${requestScope.EquipmentBean.disableMchmIscavityormandrel == true ? ' readonly':''} style="width:120px;">
								<option value="E"></option>
								<option value="C">Cavity</option>
								<option value="M">Mandrel</option>
				 	</select> 
				 	<input id="relatedToCavityormandrel" name="relatedToCavityormandrel" type="hidden"  value = "${requestScope.genTlMachinemst.mchmIscavityormandrel}"  />
				</div>
				<div ><label>Category</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmCategory" name="cmbMchmCategory"  class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmCategory}"  ${requestScope.EquipmentBean.disableMchmCategory == true ? ' readonly':''} >
				</div>
				
				<div><label> Sub Category</label></div>
				<div  >
				<input id="cmbMchmSubcategory" name="cmbMchmSubcategory"  class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmSubcategory}"   ${requestScope.EquipmentBean.disableMchmSubcategory == true ? ' readonly':''}>
				</div>
				
				<div ><label class="mandatory-lbl">
				Installation Date</label><span style="padding-left:55px;"><label class="mandatory-lbl">Effective Date</label></span>
				</div>
				<div >    
				
				<input  id="dteMchmInstalleddate"  style="width:120px;" clear="false"  name="dteMchmInstalleddate" class="easyui-datebox"   value="${requestScope.genTlMachinemst.mchmInstalleddate}"  ${requestScope.EquipmentBean.disableMchmInstalleddate == true ? ' readonly':''}/>
				
				<span style="padding-left:10px;">
				<input id="dteMchmEffectivedate" name="dteMchmEffectivedate" style="width:120px;" clear="false" class="easyui-datebox" value="${requestScope.genTlMachinemst.mchmEffectivedate}"  ${requestScope.EquipmentBean.disableMchmEffectivedate == true ? ' readonly':''}></input></span>
				
				
				</div>
					<table>
					<tr>
					<td><span id="err_dteMchmInstalleddate" class="tpm-errormsg"></span></td>
					<td style="padding-left:32px;"><span id="err_dteMchmEffectivedate" class="tpm-errormsg"></span></td>
					</tr>
					</table>
				</td>
				
				<td valign="top">
				
				<div class="sub-header" style="text-align: left; height: 20px;position:relative;width:300px;"><span>Circle</span>
										<span id="rolbtn" style="position:absolute;_right: 0px;right: -1px;_top:-2px;height:25px;">										
						<img style="cursor: pointer;z-index:210;margin-top:-3;_margin-top:0; height:25px;" src="images/addbtsub.png" title="Add Role" alt="" id="imgAddCircle">
					</span>
				</div>
				<div >
	 		  	 	<table id="catgGrid" style="width:100%"><tr><td></td></tr></table>
					 <div id="pager"></div>
				</div>
				
				<div  class="sub-header" style="width:300px;" ><b><label class="">JH Step Details</label></b></div>
				
				<div  ><label>
				JH Step</label><span style="padding-left:93px;"> <label>Date</label></span>
				</div>
				<div >
				<input id="cmbMchmJhstep" name="cmbMchmJhstep"  style="width:120px;"  class="easyui-datebox" value="${requestScope.genTlMachinemst.mchmJhstep}"  ${requestScope.EquipmentBean.disableMchmJhstep == true ? ' readonly':''}/>
				<span style="padding-left:10px;">
				<input id="dteMchmJhstepdate" name="dteMchmJhstepdate"  style="width:120px;"clear="false" class="easyui-datebox" value="${requestScope.genTlMachinemst.mchmJhstepdate}"  ${requestScope.EquipmentBean.disableMchmJhstepdate == true ? ' readonly':''}></input></span>
				</div>
				
				</td>
				</tr>
				</table>
				</div>
				</div>
				</div>
				
			    <!--Second tab Start-->
				
				<div title="Power Details" style="padding:10px;">
				<div >
				<div class="sub-header" style="width:68%;float: left;">
					<label  >
				<b>Power Details</b></label></div>
				<table style=" width:75%;float:left; ">
				<tr>
				<td width=25%;>
				<div><label>Power Supply</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmPowersupply" name="cmbMchmPowersupply" clear="false" class="easyui-combobox" clear="false" style="width:255px;" value="${requestScope.genTlMachinemst.mchmPowersupply}"  ${requestScope.EquipmentBean.disableMchmPowersupply == true ? ' readonly':''} >
				</div>
				
				<div><label>Connected Load</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmConnectedload" name="cmbMchmConnectedload" clear="false" class="easyui-combobox" clear="false"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmConnectedload}"   ${requestScope.EquipmentBean.disableMchmConnectedload == true ? ' readonly':''}>
				</div>
				
				<div><label>DBNO</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmDbno" name="cmbMchmDbno" class="easyui-combobox" clear="false"  style="width:255px;" value="${requestScope.genTlMachinemst.mchmDbno}"  ${requestScope.EquipmentBean.disableMchmDbno == true ? ' readonly':''}  >
				</div>
				
				<div><label>SBNO</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbMchmSbno" name="cmbMchmSbno" class="easyui-combobox" clear="false" style="width:255px;" value="${requestScope.genTlMachinemst.mchmSbno}"  ${requestScope.EquipmentBean.disableMchmSbno == true ? ' readonly':''} >
				</div>
				</td>
				
				<td width=50% valign="top" style="margin-top: 0px;">
				
				<div style="padding-left:40px;" ><label style="padding-left:0px;">
				Phase No</label><span style="padding-left:56px;"><label style="padding-left:0px;">Wires</label></span>
				</div>
				<div class="easyui-paddingbfpx""style="padding-left:50px;" >
				<input id="txtMchmPhase" type="text" class="easyui-text" size="10" name="txtMchmPhase" style=" height : 21px;margin-left:40px;" value="${requestScope.genTlMachinemst.mchmPhase}"  ${requestScope.EquipmentBean.disableMchmPhase == true ? ' readonly':''}>
				<span style="padding-left:36px;">
				<input id="txtMchmWires" type="text" class="easyui-text" size="10" name="txtMchmWires"  style=" margin-left:4px;"value="${requestScope.genTlMachinemst.mchmWires}" style=" height : 21px;"  ${requestScope.EquipmentBean.disableMchmWires == true ? ' readonly':''}>
				</span>
				</div>
				
				<div ><label style="padding-left:40px;">Input volt</label><span style="padding-left:57px;"><label style="padding-left:10px;">min</label></span>
				<span style="padding-left:71px;"><label style="padding-left:2px;">max</label></span>
				</div> 
                  <div class="easyui-paddingbfpx" style="padding-left:40px;" >
                    <input id="txtMchmIpvolt" type="text" class="easyui-text" size="10" name="txtMchmIpvolt"  style=" height : 21px;" value="${requestScope.genTlMachinemst.mchmIpvolt}"  ${requestScope.EquipmentBean.disableMchmIpvolt == true ? ' readonly':''}>
                    <span style="padding-left:40px;">
                    <input id="txtMchmIpvoltmin" type="text" class="easyui-text" size="10" name="txtMchmIpvoltmin"  style=" height : 21px;" value="${requestScope.genTlMachinemst.mchmIpvoltmin}"  ${requestScope.EquipmentBean.disableMchmIpvoltmin == true ? ' readonly':''}>
                    </span>
                    <span style="padding-left:30px;">
                    <input id="txtMchmIpvoltmax" type="text" class="easyui-text" size="10" name="txtMchmIpvoltmax" style=" height : 21px;" value="${requestScope.genTlMachinemst.mchmIpvoltmax}"  ${requestScope.EquipmentBean.disableMchmIpvoltmax == true ? ' readonly':''}>
                    </span>
                  </div> 
                 
                 
                 <div><label style="padding-left:40px;">Input Freq</label><span style="padding-left:52px;"><label style="padding-left:10px;">min</label></span>
				<span style="padding-left:72px;"><label style="padding-left:2px;">max</label></span>
				</div> 
                  <div class="easyui-paddingbfpx" style="padding-left:40px;">
                    <input id="txtMchmIpfreq" type="text" class="easyui-text" size="10" name="txtMchmIpfreq" style=" height : 21px;" value="${requestScope.genTlMachinemst.mchmIpfreq}"  ${requestScope.EquipmentBean.disableMchmIpfreq == true ? ' readonly':''}>
                    <span style="padding-left:40px;">
                    <input id="txtMchmIpfreqmin" type="text" class="easyui-text" size="10" name="txtMchmIpfreqmin" style=" height : 21px;" value="${requestScope.genTlMachinemst.mchmIpfreqmin}"  ${requestScope.EquipmentBean.disableMchmIpfreqmin == true ? ' readonly':''}>
                    </span>
                    <span style="padding-left:30px;">
                    <input id="txtMchmIpfreqmax" type="text" class="easyui-text" size="10" name="txtMchmIpfreqmax"  style=" height : 21px;" value="${requestScope.genTlMachinemst.mchmIpfreqmax}"  ${requestScope.EquipmentBean.disableMchmIpfreqmax == true ? ' readonly':''}>
                    </span>
                  </div> 				
				</td>				
				</tr>				
				</table>				
				</div>
				</div>
				<!--        Third Tab Start				-->
				
				<div title="Manufacture (Mfr)/Supplier/MC-info" style="padding:10px;">
				<div style="padding:20px 0 0 2%;">
				<table width="98%"  align="center"> 
           <tr width=50% > 
             <td colspan="" class="sub-header" > <b><label >Manufacture Information</label> </b></td> 
             <td colspan="" class="sub-header" style="padding-left: 10px;" > <b><label class="">Supplier Information</label></b> </td> 
             <td colspan="" class="sub-header" style="padding-left: 10px;"><b><label class="">Maintenance Contract(MC)Information</label></b> </td> 
          </tr> 
           <tr> 
            <td width="34%" valign="top"><div class="Maindiv" style="padding-top:15px;"> 
                <div class="easyui-paddingbfpx"" > <label>Mfr.</label>
                  <div class="easyui-paddingbfpx" >
                    <div style="vertical-align:top; "> 
                     <input id="cmbMchmManufacturerid"   name="cmbMchmManufacturerid" clear="false" class="easyui-combobox" maxlength="10" clear="false" style="width:230px;" value="${requestScope.genTlMachinemst.mchmManufacturerid}"  ${requestScope.EquipmentBean.disableMchmManufacturerid == true ? ' readonly':''}> 
                      </div> 
                  </div> 
                </div> 
                <div class="easyui-paddingbfpx"" ><label> Make</label>
                  <div class="easyui-paddingbfpx"" > 
                    <div style="vertical-align:top; "> 
                      <input id="cmbMchmMake" name="cmbMchmMake" clear="false" class="easyui-combobox" maxlength="30" clear="false"  style="width:230px;" value="${requestScope.genTlMachinemst.mchmMake}"  ${requestScope.EquipmentBean.disableMchmMake == true ? ' readonly':''}> 
                      </div> 
                  </div> 
                </div> 
                <div class="easyui-paddingbfpx"" ><label> Model</label>
                  <div class="easyui-paddingbfpx"" >
                    <div style="vertical-align:top; "> 
                      <input id="cmbMchmModel" name="cmbMchmModel" clear="false" class="easyui-combobox" maxlength="30" clear="false" style="width:230px;" value="${requestScope.genTlMachinemst.mchmModel}"  ${requestScope.EquipmentBean.disableMchmModel == true ? ' readonly':''} > 
                      </div> 
                  </div> 
                </div> 
                <div class="easyui-paddingbfpx"" ><label class="mandatory-lbl"> Mfr.S.No</label>
		  <div class="easyui-paddingbfpx"" >
			<div style="vertical-align:top; "> 
			   <input id="txtMchmMfrslno" type="text" clear="false" class="easyui-text"  name="txtMchmMfrslno" maxlength="30" style=" height : 21px;width:230px;" value="${requestScope.genTlMachinemst.mchmMfrslno}"  ${requestScope.EquipmentBean.disableMchmMfrslno == true ? ' readonly':''}> 
			  </div> 
		  </div> 
    </div>
	
	
	<div class="easyui-paddingbfpx" ><label>Mfr.Dt</label>
		  <div class="easyui-paddingbfpx" >
			<div style="vertical-align:top; "> 
			   <input id="dteMchmManufactureddate"  clear="false" name="dteMchmManufactureddate" class="easyui-datebox" style="width:230px;" value="${requestScope.genTlMachinemst.mchmManufactureddate}"  ${requestScope.EquipmentBean.disableMchmManufactureddate == true ? ' readonly':''} />
			  </div> 
		  </div> 
    </div> 
                <div class="easyui-paddingbfpx" ><label>Remarks</label>
                  <div class="easyui-paddingbfpx" > 
                    <textarea rows="2" style="width:230px;resize:none;"  maxlength="200" cols="" id="txtMchmMfrremarks" name="txtMchmMfrremarks" maxlength="200" ${requestScope.EquipmentBean.disableMchmMfrremarks == true ? ' readonly':''}>${requestScope.genTlMachinemst.mchmMfrremarks}</textarea> 
                  </div> 
                </div> 
              </div></td> 
            <td width="33%" valign="top" style="padding-top:15px; padding-left: 10px;"> 
            <div  ><label>Supplier</label>
                <div class="easyui-paddingbfpx" >
                  <div style="vertical-align:top; "> 
                    <input id="cmbMchmSupplierid" name="cmbMchmSupplierid" maxlength="8" class="easyui-combobox"  style="width:232px;" value="${requestScope.genTlMachinemst.mchmMfrremarks}"  ${requestScope.EquipmentBean.disableMchmSupplierid == true ? ' readonly':''}> 
                    </div> 
                </div> 
              </div> 
               <div ><label>P.O.No</label>
               <span style="padding-left:95px;"><label>Date</label></span>
               <div class="easyui-paddingbfpx" > 
                   <input id="txtMchmPono" type="text" class="easyui-text" maxlength="18"   name="txtMchmPono" value="${requestScope.genTlMachinemst.mchmPono}" style=" height : 21px; width: 123px;"  ${requestScope.EquipmentBean.disableMchmPono == true ? ' readonly':''}> 
                   <span style="padding-left:7px;">  
                   <input id="dteMchmPodate" style="width:100px;_width:100px" name="dteMchmPodate" clear="false" maxlength="18" class="easyui-datebox"  value="${requestScope.genTlMachinemst.mchmPodate}"  ${requestScope.EquipmentBean.disableMchmPodate == true ? ' readonly':''}/> </span>
                 </div> 
              </div> 
               <div > <label>PO Price</label>
               <span style="padding-left:90px;"><label>Unit</label></span> 
                <div class="easyui-paddingbfpx" > 
                   <input id="txtMchmPurchaseprice" type="text" class="easyui-text" maxlength="18"  name="txtMchmPurchaseprice" value="${requestScope.genTlMachinemst.mchmPurchaseprice}" style=" height : 21px; width: 122px;" ${requestScope.EquipmentBean.disableMchmPurchaseprice == true ? ' readonly':''}>                   
                	<span style="padding-left:7px;">  
                	<input id="cmbMchmCurrencyid"   name="cmbMchmCurrencyid" maxlength="12" clear="false" class="easyui-datebox" value="${requestScope.genTlMachinemst.mchmCurrencyid}" style=" height : 21px; width: 100px;"  ${requestScope.EquipmentBean.disableMchmCurrencyid == true ? ' readonly':''}/> 
                   </span> </div> 
              </div> 
               <div  > 
                <div ><span class="lbl"><label>Purchase Date</label></span>
                </div> 
                <div class="easyui-paddingbfpx"> 
                   
                  <input id="dteMchmPurchasedate"  clear="false" name="dteMchmPurchasedate" class="easyui-datebox" style="width:235px;" value="${requestScope.genTlMachinemst.mchmPurchasedate}"   ${requestScope.EquipmentBean.disableMchmPurchasedate == true ? ' readonly':''}/> 
                  
                  </div> </div> 
                  <div>
                    <div ><label>Warranty</label><span style="padding-left:62px;"><label>Warranty End Date</label></span></div> 
                  <div class="easyui-paddingbfpx" > 
                    <input type="radio" value="0" id="chkMchmIsunderwarranty" name="ECK"> 
                   <label> Yes</label>
                     <input type="radio" value="1" id="Wrdchk1" name="ECK"> 
                   <label> No</label>  
                   
                   <span style="padding-left:33px; _padding-left:20px;"> 
                   
                  <input id="dteMchmWarrantydate" clear="false" name="dteMchmWarrantydate" class="easyui-datebox" style="width:123px;" value="${requestScope.genTlMachinemst.mchmWarrantydate}"  ${requestScope.EquipmentBean.disableMchmWarrantydate == true ? ' readonly':''} /> 
                  </span> </div> </div>
               
         
               <div  ><label>Remarks</label>
                <div class="easyui-paddingbfpx"" >
                   <textarea rows="2" style="width: 235px;resize:none;" cols="" id="txtMchmSupplierremarks" maxlength="200" name="txtMchmSupplierremarks" value=""    ${requestScope.EquipmentBean.disableMchmSupplierremarks == true ? ' readonly':''}>${requestScope.genTlMachinemst.mchmSupplierremarks}</textarea> 
                 </div> 
              </div></td> 
            <td width="33%" valign="top"> <div class="Maindiv" style="padding-top:15px; padding-left: 10px;"> 
               <div class="easyui-paddingbfpx" >
                  <div class="easyui-paddingbfpx" ><label>Under MC</label></div> 
                  <div class="easyui-paddingbfpx"" > 
                    <input type="radio" value="0" id="rdchk" name="CK"> 
                   <label> Yes</label>
                     <input type="radio" value="1" id="rdchk1" name="CK"> 
                   <label> No</label> </div> 
                </div> 
                <div  ><label id="Pro">Provider</label>
                  <div class="easyui-paddingbfpx" >
                    <div style="vertical-align:top; "> 
                      <input id="cmbMchmAmcvendor" name="cmbMchmAmcvendor"  maxlength="12" class="easyui-combobox" clear="false" style="width:230px;" value="${requestScope.genTlMachinemst.mchmAmcvendor}"  ${requestScope.EquipmentBean.disableMchmAmcvendor == true ? ' readonly':''} > 
                      </div> 
                  </div> 
                </div> 
                <div >
                  <div  ><label>Contract Date </label>
                  <span style="padding-left:65px;"> <label>Renewal Date</label></span>   </div> 
                  <div class="easyui-paddingbfpx" >         
                                          
                    <input id="dteMchmAmcdate" clear="false" name="dteMchmAmcdate" class="easyui-datebox" style="width: 110px;"  value="${requestScope.genTlMachinemst.mchmAmcdate}"  ${requestScope.EquipmentBean.disableMchmAmcdate == true ? ' readonly':''}/>
                    <span style="padding-left:29px;">
                    <input id="dteMchmAmcrenewaldate" clear="false" name="dteMchmAmcrenewaldate" class="easyui-datebox" style="width: 87px;"  value="${requestScope.genTlMachinemst.mchmAmcrenewaldate}"  ${requestScope.EquipmentBean.disableMchmAmcrenewaldate == true ? ' readonly':''}/></span>
                    <span id="err_dteMchmAmcdate" class="tpm-errormsg"></span>
					<span id="err_dteMchmAmcrenewaldate" class="tpm-errormsg"></span>
                  </div> 
                </div> 
               
                <div  >
                  <div ><label>MC Remarks</label></div> 
                  <div class="easyui-paddingbfpx" >
                    <textarea rows="2" style="width: 230px;resize:none;" cols="" maxlength="200" name="txtMchmAmcremarks" id="txtMchmAmcremarks" value=""    ${requestScope.EquipmentBean.disableMchmAmcremarks == true ? ' readonly':''}>${requestScope.genTlMachinemst.mchmAmcremarks}</textarea> 
                  </div> 
                </div> 
              </div></td> 
          </tr> 
         </table>
				</div>
				</div>
				
			<!--	  Fourth Tab Start			-->
				<div title="Operators & Skills" style="padding:1px;">
				<div style="padding:20px 0 0 20px;">
				<div  style="float: left;margin:10px;" id="grdOpr">
				<div class="sub-header" style="width:400px;_width:420px;position:relative;">
					<label style="padding-right:30%;">
					<b>Operator Details</b></label>
					<span style="position:absolute;right:0px;top:0px;">
						<input type="button" class="easyui-button"  name="btnoperator" id="btnoperator" value="Add" style=" height : 21px; "/>
					</span>
					</div>
					<div ><table id="grdoperator" width="" style="float: left;"></table> </div>
					<div id="Pager"></div> 
					
				</div>
				
				<div  style="float: right;margin:10px;">
				<div class="sub-header" style="width:400px;_width:420px;position:relative;">
					<label style="padding-right:20px;" >
				<b>Skill Details</b></label>
				<span style="position:absolute;right:0px;top:0px;">
					<input type="button" class="easyui-button"  name="btnoperSkill" id="btnoperSkill" value="Add" style=" height : 21px;"/>
					<input type="button" class="easyui-button"  name="btnoperSkillRowAdd" id="btnoperSkillRowAdd" value="AddSkill" style=" height : 21px;"/>
					<input type="button" class="easyui-button"  name="edtbtn" id="edtbtn" value="Edit" style=" height : 21px;"/>
				</span>
				
				</div>
					<div ><table id="operatorSkill" width="" style="float: left;"></table> </div>
					<div id="Pager1"></div> 
				</div>
				
				</div>
				</div>
				<!--	  Fifth Tab Start			-->	
				
				<div title="Maintenance Team Info" style="padding:1px;">
				<div style="padding-left:0px;padding-top:15px;">
				<div  style="float: left;margin:15px; ">
				<div class="sub-header" style="width:400px;_width:420px;position:relative;" >
					<label  style="padding-right:20px;">
				<b>Maintainence Team</b></label>
				<span style="position:absolute;right:0px;top:0px;">
					<input type="button" class="easyui-button"  name="Maintaince" id="Maintaince" value="Add" style=" height : 21px;"/>
					</span>
				</div>
					<div><table id="maintaince" width="380px" style="float: left;"></table> </div>
					<div id="Pager2"></div> 
				</div>
				
				<div  style="float:right;margin:15px;paddin-top:5px;">
				<div class="sub-header" style="width:400px;_width:420px;position:relative;" >
					<label  style="padding-right:10px;">
				<b>Skill Required</b></label>
				<span style="position:absolute;right:0px;top:0px;"><input type="button" class="easyui-button"  name="MaintSkill" id="MaintSkill" value="Add" style=" height : 21px;"/>
					<input type="button" class="easyui-button"  name="maintSkillRowAdd" id="maintSkillRowAdd" value="AddSkill" style=" height : 21px;"/>
					<input type="button" class="easyui-button"  name="maintedtbtn" id="maintedtbtn" value="Edit" style=" height : 21px;"/>
				</span>
				</div>
					<div style="margin-right:0px;"><table id="maintainceSkill" width="380px" style="float: left;"></table> </div>
					<div id="Pager3"></div> 
				</div>				
				</div>
				</div>
					<!--	  Sixth Tab Start			-->
					
				<div title="Equipment Parameters" style="padding:10px;">
				<div>
				<div class="sub-header" style="width:83%;padding-left:-50px;float:left;">
					<label  >
				<b>Equipment Parameters</b></label></div>
				<div  style="float: left;margin:1px;">
				
					<div><table id="equipmentParameter" width="380px" style="float: left;"></table> </div>
					<div id="Pager4"></div> 
				</div>				
				</div>
				</div>
				
					<!--	  Seventh Tab Start			-->
				<div title="Sub Equipments" style="padding:10px;">
				<div >
				<div class="sub-header" style="width:90%;padding-left:-50px;float:left;">
					<label  >
				<b>Sub Equipments</b></label></div>
				<div  style="float: left;margin:1px;">
				
					<div><table id="subEquipment" width="380px" style="float: left;"></table> </div>
					<div id="Pager5"></div> 
				</div>
				</div>
				
				</div>
			
 </div>       
	<div class="clear"></div>  
	<div id='eqp_text' style="display: none;" align="center">
		<div class="easyui-paddingbfpx" style="padding-top: 5px;">
		<input type="text" id="txtdataList" name="txtdataList" class="easyui-text" style="width: 200px;"/></div>
		<div class="easyui-paddingbfpx">
			<input type="submit" id=save name=save value="Ok" class="easyui-button" style="height:20px;"/>
			<input type="button" id="Cancel" name=cancel value="Cancel" class="easyui-button" style="height:20px;"/>
		</div>
	</div>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" />	
	<input type="hidden" id="hdnmsg" name="hdnmsg" value="${requestScope.inactMsg}"/>
	<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.eqpMode}"/>
</div>
</div>
<input type="hidden" id="hdnMachineid"/>
<input type="hidden" id="hdnParamGrid" name="hdnParamGrid" value=""/>
</form>