<script type="text/javascript">

jQuery(document).ready(function(){
		initialiseForm("frmExternalServiceBD");	
		jQuery('#submitForm').val("frmExternalServiceBD");
		var flid=jQuery("#frmWorkOrder input[id='flid']").val();
		formatDateBox("dteExtmPlanDeliverytime","dd-MMM-yyyy");
		fillComboBox("frmExternalServiceBD","cmbExtmMaterialGroup","materialCombo.work" );
		fillComboBox("frmExternalServiceBD","cmbExtmVendor","vendorCombo.work" );
		fillComboBox("frmExternalServiceBD","cmbExtmPurchaseGroup","purchaseGroupCombo.work" );
		fillComboBox("frmExternalServiceBD","cmbExtmPurchaseOrg","purchaseOrgCombo.work" );
		fillComboBox("frmExternalServiceBD","cmbExtmShift","combo_shift.brdn" );
		fillComboBox("frmExternalServiceBD","cmbExtmRequisitioner","requistionsCombo.work?&flid="+flid); 
		//alert(jQuery('#hdnExtmKeyid').val());
       
        var formMode = jQuery('#hdnExtFormMode').val();
        
        	 
	        if("ExtService"==formMode){
	        	jQuery('.sub-header #headin').text('External Service Detail');
	        	var params="&taskId="+jQuery('#hdnExtmTaskId').val()+"&womsId="+jQuery('#hdnExtmOrderno').val();
	        	processGridnew("externalService_view.brdn","&q=2&ExtMasterId="+jQuery('#hdnExtmKeyid').val()+params,"externalServiceGrd","externalServicePager","","extserviceDblClick");
	        }
	        else{ 
	        	//alert("1");
	        	jQuery('.sub-header #headin').text('External Repair Detail');
	        	//processGridnew("externalRepair_view.brdn","&q=2&ExtMasterId="+jQuery('#hdnExtmKeyid').val(),"externalRepairSubgrid","externalRepairSubpager","","extRepairDblClick");
	        	var taskId=jQuery("#hdnExtmTaskId").val();
	        	var worderId=jQuery("#hdnExtmOrderno").val();
	        	var url="sparesExt_input.work";
				var ds = "?&taskId="+taskId+"&womsId="+worderId;
				//alert(ds);
				
	        	processGridnew(url,ds, "extSparesGrid", "pagerExtSpares","","doubleClickSpares","","loadComplete","","");
	        }
       
        //setTimeout(function() {},1250);
        numericTextBox('txtExtmPrice');
        numericTextBox('txtExtmCostElement');
        numericTextBox('txtExtmOperationQty');
        var materialGroup = jQuery('#hdnExtmMaterialGroup').val();
        setFieldValue("cmbExtmMaterialGroup",materialGroup);
        setFieldValue("cmbExtmPurchaseGroup",jQuery('#hdnExtmPurchaseGroup').val(),"frmExternalServiceBD");
        setFieldValue("cmbExtmVendor", jQuery('#hdnExtmVendor').val(),"frmExternalServiceBD");
        setFieldValue("cmbExtmPurchaseOrg", jQuery('#hdnExtmPurchaseOrg').val(),"frmExternalServiceBD");
        setFieldValue("cmbExtmRequisitioner",jQuery('#hdnExtmRequisitioner').val(),"frmExternalServiceBD");
        var url = jQuery('#hiddenUrl').val();
        var task=jQuery('#txtTask').val();
       // alert(task);
        var taskname="Operation:";
    	taskname+=task;
    	jQuery("#lblTask").val(taskname);
    	readOnlyFields("lblTask");	
    	var extContrct=jQuery('#hdnExtmExtSubContract').val();
        if(extContrct=="Y")
        	jQuery('#chkExtSubCont').attr('checked',true);
        else
        	jQuery('#chkExtSubCont').attr('checked',false);
        jQuery('#chkExtSubCont').click(function(){
        	if(jQuery('#chkExtSubCont').is(':checked')==true){
        		jQuery('#hdnExtmExtSubContract').val('Y');
        	}
        	else if(jQuery('#chkExtSubCont').is(':checked')==false){
        		jQuery('#hdnExtmExtSubContract').val('N');
        	}
        });
        
        jQuery('#chkExtmType').click(function(){
        	chekSelected();
        });
        jQuery('#btnExternalServiceSave').click(function() {
        	saveForm("frmExternalServiceBD","externalservice_save.brdn");
        });
        jQuery('#btnExtSerDtl').click(function(){
        	var extMasterId =  jQuery('#hdnExtmKeyid').val();
            var formMode = jQuery('#hdnExtFormMode').val();
            if(extMasterId.trim().length>0){ 
            	jQuery('#btnExternalServiceSave').trigger("click");
            	jQuery('#hdnBtnClick').val("Y");
            	//openLoadPoP(formMode,extMasterId);
            }
            else{ 
            	//var vendor=getFieldValue("cmbExtmVendor");
            	var materialGroup=getFieldValue("cmbExtmMaterialGroup");
            	var purchaseGroup=getFieldValue("cmbExtmPurchaseGroup");
            	var purchaseOrg=getFieldValue("cmbExtmPurchaseOrg");
            	if( materialGroup.trim().length>0 && purchaseGroup.trim().length>0 && purchaseOrg.trim().length>0 ){ 
	            	jQuery('#hdnBtnClick').val("Y");
	            	jQuery('#btnExternalServiceSave').trigger("click");
            	}
            	else{
            		alert("No Data Entered");
            	}
            }
        });
        chekSelected();
     });
     function frmExternalServiceBD_successsCallback(result){
    	 var mastKeyid = result.extMstKeyid;
    	 var btnClicked = jQuery('#hdnBtnClick').val();
    	 var formMode = jQuery('#hdnExtFormMode').val();
    	 jQuery('#hdnExtmKeyid').val(mastKeyid);
    	 if("Y" == btnClicked)
    		 openLoadPoP(formMode,mastKeyid);
     }
     function openLoadPoP(formMode,extMasterId){
    	 //alert(formMode);
    	 if("ExtService"==formMode)
		    LoadPopUp("loadExtServiceDetail", "extServiceDtl_input.brdn?q=2&formMode="+formMode+"&extMasterId="+extMasterId, true,"64%","71%","6%","24%", "extService_successCallBack","External Service Detail");
	     else{
	       //	LoadPopUp("loadExtDetail", "extServiceDtl_input.brdn?q=2&formMode="+formMode+"&extMasterId="+extMasterId, true,"75%","83%","5%","10%", "extService_successCallBack","External Repair Detail");
	    	//alert("1");
	       var taskId=jQuery("#hdnExtmTaskId").val();
	        	var worderId=jQuery("#hdnExtmOrderno").val();
	        	 var task=jQuery('#txtTask').val();
	    //   var url = "sparesDetail_input.work?q=2&woKeyid="+worderId+"&taskid="+taskId+"&task="+task;
	      // alert(1);
	    		//LoadPopUp("divSpares_p", url, true,"77%","400px","10px","40px", "showResult_successCallBack","Spares",true);
	      // LoadPopUp("ivSpares_p", url, true,"64%","71%","6%","24%", "","Spares");
	       var url ="sparesDetail_input.work?&woKeyid="+worderId+"&taskid="+taskId+"&task="+escape(task);
	   	LoadPopUp("divSpares", url, true,"77%","400px","10px","40px", "showResult_successCallBack","Spares",true);

 
	     }
     }
     function extRepairDblClick(rowId){
    	 var rowData = jQuery("#externalRepairSubgrid").jqGrid('getRowData',rowId);
    	 var extMasterId =  jQuery('#hdnExtmKeyid').val();
         var formMode = jQuery('#hdnExtFormMode').val();
    	 var extRepDtlKeyid = rowData.REPAIRID;
    	 LoadPopUp("loadExtRepairDetail", "extServiceDtl_input.brdn?q=2&formMode="+formMode+"&extMasterId="+extMasterId+"&extRepDtlKeyid="+extRepDtlKeyid, true,"75%","83%","5%","10%", "extService_successCallBack","External Service Detail");
     }
     function extserviceDblClick(rowId){ 
    	 var rowData = jQuery("#externalServiceGrd").jqGrid('getRowData',rowId);
    	 var extMasterId =  jQuery('#hdnExtmKeyid').val();
         var formMode = jQuery('#hdnExtFormMode').val();
    	 var extDtlKeyid = rowData.DETKEYID; 	 
    	 LoadPopUp("loadExtServiceDetail", "extServiceDtl_input.brdn?q=2&formMode="+formMode+"&extMasterId="+extMasterId+"&extDtlKeyid="+extDtlKeyid, true,"64%","71%","6%","24%", "extService_successCallBack","External Service Detail");
     }
     function doubleClickSpares(rowId){
    	 var rowData = jQuery("#extSparesGrid").jqGrid('getRowData',rowId);
    	 var extSprKeyid = rowData.keyid;
    	 var taskId=jQuery("#hdnExtmTaskId").val();
     	var worderId=jQuery("#hdnExtmOrderno").val();
     	 var task=jQuery('#txtTask').val();
    	  var url ="sparesDetail_input.work?&woKeyid="+worderId+"&keyId="+extSprKeyid+"&taskid="+taskId+"&task="+escape(task);
  	   	LoadPopUp("divSpares", url, true,"77%","400px","10px","40px", "showResult_successCallBack","Spares",true);
     }
     function loadComplete(){
    	 //alert("Complete");
     }
     function loadExtServiceDetail_onClose(){
    	// alert("closing..");
    	 jQuery('#externalServiceGrd').trigger("reloadGrid");
    	 return true;
     }
     function loadExtDetail_onClose(){
    	 //alert("closing..");
    	 jQuery('#externalRepairSubgrid').trigger("reloadGrid");
    	 return true;
     }
     function divSpares_onClose(){
    	// alert("1");
    	jQuery('#extSparesGrid').trigger("reloadGrid");
    	 return true;
     }
     function divExtService_onClose(){
    	 var isSubContr=jQuery("#hdnExtmExtSubContract").val();
    	 var reworkCnt=jQuery("#hdnReworkCnt").val();
    	 var isExit=jQuery("#hdnClose").val();
    	 //alert(reworkCnt);
    	 if(isSubContr=='Y' && isExit!='Y'){
    		 //alert(isExit);
    		 var womsKey=jQuery("#hdnWomsKeyid").val();
    		 var taskId=jQuery("#hdnExtmTaskId").val();
    			var url="checkSubContract.work";
    			var dataString="?&womsKey="+womsKey+"&taskId="+taskId;
    			processAjaxCalls(url,dataString,"matProvSuccessCall","matProvErrorCall","","","","");
    	 }
    	 else{
    		 if(isExit=='Y')
    		  return true;
    	 }
     }
     function matProvSuccessCall(result){
    	   var subContract=result.subContractCheck;
    	   var reworkCnt=result.reworkCnt;
    	   jQuery("#hdnSubContract").val(subContract);
    	   jQuery("#hdnReworkCnt").val(reworkCnt);
    	   if(subContract=='Y' && reworkCnt==0){
    		   jQuery("#hdnClose").val("N");
    		   alert("Atleast one component should have rework indicator");
      		    var woKey = jQuery("#hdnWomsKeyid").val();	
      			var params="";
      			 var taskId=jQuery("#hdnExtmTaskId").val();
   	        	 var task=jQuery('#txtTask').val();
      			params+="&taskid="+taskId+"&task="+escape(task);
      			var url ="sparesDetail_input.work?&woKeyid="+woKey+params;
      			LoadPopUp("divSpares", url, true,"91%","414px","1px","40px", "showResult_successCallBack","Components",true);
      		 
    	   }
    	   else{
    		   //alert("yes");
    		   jQuery("#hdnClose").val("Y");
    		   closePopUpDialoge("divExtService");
    	   }
    	}
    function chekSelected(){
	
	if(jQuery('#chkExtmType').is(':checked')){ 
		//enableForm("frmExternalServiceBD");
			var inputs = jQuery('#frmExternalServiceBD :input');	
	var controlId ;

	jQuery(inputs).each(function () {
		controlId = this.id;		
		///alert(jQuery("#"+controlId).attr('disabled'));
		jQuery("#"+controlId).removeAttr('disabled');
	});
	}
	else{
		//disableForm("frmExternalServiceBD");
	}
	setTimeout(function(){
		jQuery('#chkExtmType').attr('disabled',false);
		jQuery('#btnExternalServiceSave').attr('disabled',false);
		},1150);
}
</script>
     	
<form name="frmExternalServiceBD" id="frmExternalServiceBD">
<!--

		
    <div style="float:left;">
		<table id="externalServicegrid"></table>
		<div id="externalServicepager"></div>
	</div> 
	
	-->
	<div class="" style="font-size: 20px;font-weight: bold;">
		<textarea id="lblTask" name="lblTask" style="border:1px solid black; font-size:11px ; width:820px;height:25px;color:black;background-color:#fae0aa;font-weight:bold;text-align:left;" disabled="disabled"></textarea>
		<span style="margin-left:15px;">
		  	<input type="button" class='easyui-button' value="Save" id="btnExternalServiceSave"/>
		</span>
		
	</div>
	<div>
		<!--<span  style="margin-left:10px;">
			<input type="checkbox" value="extService" id="chkExtmType" name="chkExtmType" checked   /><label style="margin-left:5px;">External Service Required</label>
		</span>
		-->
	</div>
<table style="padding-left:10px;padding-top:10px;">
	<tr>
	<!--<td><label>Operation Quantity</label>
		<div>
			<input type="text" id="txtExtmOperationQty" name="txtExtmOperationQty" class="easyui-text" style=" width : 200px;text-align:right;" value="${requestScope.sapExternalServiceMst.extmOperationQty} "  />
		</div>
	</td>
	-->
	<td style="padding-left:0px;padding-top:5px;"><label  >Vendor</label>
		<div>
			<!--<input type="text" id="txtExtmVendor" name="txtExtmVendor" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmVendor} " />
			--><input  id="cmbExtmVendor" name="cmbExtmVendor" class="easyui-combobox" style="width:200px;" value=" "/>
		</div>
	</td>
	<td style="padding-left:30px"><label class="mandatory-lbl" >Material Group</label>
		<div>
			<!--<input type="text" id="txtExtmMaterialGroup" name="txtExtmMaterialGroup" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmMaterialGroup} " maxlength='10'>
			--><input  id="cmbExtmMaterialGroup" name="cmbExtmMaterialGroup" class="easyui-combobox" style="width:200px;" value="" />
		</div>
	</td>
	<td style="padding-left:30px"><label class="mandatory-lbl" >Purchase Group</label>
		<div>
			<!--<input type="text" id="txtExtmPurchaseGroup" name="txtExtmPurchaseGroup" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmPurchaseGroup} ">
			--><input  id="cmbExtmPurchaseGroup" name="cmbExtmPurchaseGroup" class="easyui-combobox" style="width:200px;" />
		</div>
	</td>
	<td style="padding-left:30px"><label class="mandatory-lbl" >Purchase Organization</label>
		<div>
			<!--<input type="text" id="txtExtmPurchaseGroup" name="txtExtmPurchaseGroup" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmPurchaseGroup} ">
			--><input  id="cmbExtmPurchaseOrg" name="cmbExtmPurchaseOrg" class="easyui-combobox" style="width:200px;" />
		</div>
	</td>
	</tr>
	<tr>
	<td style="padding-left:0px"><label>Price</label>
		<div>
			<input type="text" id="txtExtmPrice" name="txtExtmPrice" class="easyui-text" style=" width : 120px;text-align:right;" value="${requestScope.sapExternalServiceMst.extmPrice} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label >Recipient</label>
		<div>
			<input  id="txtExtmRecipient" name="txtExtmRecipient" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmRecipient} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label class="mandatory-lbl">Requisitioner</label>
		<div>
			<%-- <input id="cmbExtmRequisitioner" name="cmbExtmRequisitioner" class="easyui-combobox" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmRequisitioner} "> --%>
			<input id="cmbExtmRequisitioner" name="cmbExtmRequisitioner" class="easyui-combobox" style=" width : 200px;" >
		</div>
	</td>
	<td style="padding-top:10px;padding-left:30px;">
		<div>
			<!--<input type="text" id="txtExtmExtSubContract" name="txtExtmExtSubContract" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmExtSubContract} "/>
			-->
			<input type="checkbox"  id="chkExtSubCont" name="chkExtSubCont" />
			<span style=margin-left:3px;>
				<label>External SubContract?</label>
			</span>
		</div>
	</td>
	<!--<td style="padding-left:30px;padding-top:5px;">
	<label>Planned</label>
	<span style='margin-left:58px;'><label>Shift</label></span>
		<div>
			<input  id="dteExtmPlanDeliverytime" name="dteExtmPlanDeliverytime" class="easyui-datebox" style="width:100px;" value="${requestScope.sapExternalServiceMst.extmPlanDeliverytime} " />
			<span style='margin-left:8px;'>
				<input  id="cmbExtmShift" name="cmbExtmShift" class="easyui-combobox" style="width:100px;" value="${requestScope.sapExternalServiceMst.extmShift} "/>
			</span>
		</div>
	</td>
	--></tr>
	<tr>
	
	<!--<td style="padding-left:30px;padding-top:5px;"><label>Sort</label>
		<div>
			<input type="text" id="txtExtmSortterm" name="txtExtmSortterm" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmSortterm} " />
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Cost</label>
		<div>
			<input type="text" id="txtExtmCostElement" name="txtExtmCostElement" class="easyui-text" style=" width : 200px;text-align:right;" value="${requestScope.sapExternalServiceMst.extmCostElement} " />
		</div>
	</td>-->
	
	<td style="padding-top:5px;"><label>Agreement</label>
		<div>
			<input type="text" id="txtExtmAgreement" name="txtExtmAgreement" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmAgreement} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Info</label>
		<div>
			<input type="text" id="txtExtmInfoRecord" name="txtExtmInfoRecord" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmInfoRecord} " />
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Unloading</label>
		<div>
			<input type="text" id="txtExtmUnloadPoint" name="txtExtmUnloadPoint" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmUnloadPoint} " />
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Tracking</label>
		<div>
			<input type="text" id="txtExtmTrackNo" name="txtExtmTrackNo" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmTrackNo} " />
		</div>
	</td>
	</tr>
</table>
	<div class="sub-header"  style="text-align: left;width:920px;height:25px\9; position:relative;margin-left:0px;"><span id="headin" style="position: absolute;"> </span>
		<span id="extServbtn" style="  position:absolute ;right:0px; ">
				<img  class="" style="cursor: pointer;z-index:210;margin-top:-4px;margin-top:-3px\9;" src="images/addbtsub.png" title="Add External Service Detail" alt="" id="btnExtSerDtl">
		</span>
	</div>	
	
	<div style="float:left;padding-top: 10px;" >
		<table id="externalServiceGrd"  ></table>
		<div id="externalServicePager"></div>
	</div>	
	 <div style="float:left;padding-top: 10px;" >
		<table id="extSparesGrid"  ></table>
		<div id="pagerExtSpares"></div>
	</div>	
 	<input type="hidden" id="mode"/>
 	<input type="hidden" id="hdnBtnClick"/>
<%-- <input type="hidden" id="hdnExtmNotificationno" name="hdnExtmNotificationno" value="${requestScope.bdKeyid}" /> --%>
     <input type="hidden" id="hdnExtmNotificationno" name="hdnExtmNotificationno" value="${requestScope.refdocId}" />
 	 <input type="hidden" id="hdnExtmDate" name="hdnExtmDate" value="${requestScope.date}" />
 	 <input type="hidden" id="hdnExtmShift" name="hdnExtmShift" value="${requestScope.shift}"/>
 	 <input type="hidden" id="hdnExtmFlid" name="hdnExtmFlid" value="${requestScope.bdflid}" />
 	 <input type="hidden" id="hdnExtmOrderno" name="hdnExtmOrderno" value="${requestScope.bdKeyid}"/>
 	 <input type="hidden" id="hdnExtmEquipment" name="hdnExtmEquipment" value="${requestScope.mchId}"/>
 	 <input type="hidden" id="hdnExtmKeyid" name="hdnExtmKeyid" value="${requestScope.sapExternalServiceMst.extmKeyid}"/>
 	 <input type="hidden" id="hdnExtmTaskId" name="hdnExtmTaskId" value="${requestScope.taskid}"/>
 	 <input type="hidden" id="hdnExtFormMode" name=" " value="${requestScope.formMode}"/>
 	 <input type="hidden" id="hdnExtmMaterialGroup" name="hdnExtmMaterialGroup" value="${requestScope.sapExternalServiceMst.extmMaterialGroup}"/>
 	 <input type="hidden" id="hdnExtmPurchaseGroup" name="hdnExtmPurchaseGroup" value="${requestScope.sapExternalServiceMst.extmPurchaseGroup}"/>
 	 <input type="hidden" id="hdnExtmVendor" name="hdnExtmVendor" value="${requestScope.sapExternalServiceMst.extmVendor}"/>
 	 <input type="hidden" id="hdnExtmPurchaseOrg" name="hdnExtmPurchaseOrg" value="${requestScope.sapExternalServiceMst.extmPurchaseOrg}"/>
 	 <input type="hidden" id="hdnExtmRequisitioner" name="hdnExtmRequisitioner" value="${requestScope.sapExternalServiceMst.extmRequisitioner}"/>
 	 <input type="hidden" id="hdnRework" name="hdnRework" />
 	 <input type="hidden" id="hdnClose" name="hdnClose" />
 	 <input type="hidden" id="txtTask" name="txtTask" value="${requestScope.task }"/>
 	 <input type="hidden" id="hdnExtmExtSubContract" name="hdnExtmExtSubContract" value="${requestScope.sapExternalServiceMst.extmExtSubContract}"/>
 	 
</form>