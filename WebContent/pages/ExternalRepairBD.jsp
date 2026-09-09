<script type="text/javascript">

jQuery(document).ready(function(){
		initialiseForm("frmExternalRepairBD");	
        var url = jQuery('#hiddenUrl').val();    
        formatDateBox("dteExtmPlanDeliverytime","dd-MMM-yyyy");
        //processGridnew("externalRepair_view.brdn","&q=2","externalRepairgrid","externalRepairpager","","");
    	//processGridnew("externalRepairSub_view.brdn","&q=2","externalSubgrid","externalSubpager","","");
    	var woId;
    	processGridnew("externalRepair_input.brdn","&q=2","externalRepairSubgrid","externalRepairSubpager","","");
    	
    	
		chekSelected();
		numericTextBox('txtPrice');
        numericTextBox('txtCost');
        numericTextBox('txtExtmOperationQty');
        jQuery('#chkExtmType').click(function(){
        	chekSelected();
        });
        jQuery('#btnExternalServiceSave').click(function() {
        	saveForm("frmExternalRepairBD","externalservice_save.brdn");
        });
     });
function chekSelected(){
	
	if(jQuery('#chkExtmType').is(':checked')){ 
		enableForm("frmExternalRepairBD");		
	}
	else{
		disableForm("frmExternalRepairBD");
	}
	setTimeout(function(){
		jQuery('#chkExtmType').attr('disabled',false);
		jQuery('#btnExternalServiceSave').attr('disabled',false);
		},1150);
}
</script>
     	
<form name="frmExternalRepairBD" id="frmExternalRepairBD">
		<div>
		<span  style="margin-left:10px;">
			<input type="checkbox" value="extRepair" id="chkExtmType" name="chkExtmType"    /><label style="margin-left:5px;">External Service Required</label>
		</span>
		<span style="margin-left:15px;">
		  	<input type="button" class='easyui-button' value="Save" id="btnExternalServiceSave"/>
		</span>
	</div>
<table style="padding-left:10px;padding-top:10px;">
	<tr>
	<td><label>Operation Quantity</label>
		<div>
			<input type="text" id="txtExtmOperationQty" name="txtExtmOperationQty" class="easyui-text" style=" width : 200px;text-align:right;" value="${requestScope.sapExternalServiceMst.extmOperationQty} "  />
		</div>
	</td>
	<td style="padding-left:30px"><label>Price</label>
		<div>
			<input type="text" id="txtExtmPrice" name="txtExtmPrice" class="easyui-text" style=" width : 200px;text-align:right;" value="${requestScope.sapExternalServiceMst.extmPrice} ">
		</div>
	</td>
	<td style="padding-left:30px"><label>Material Group</label>
		<div>
			<input type="text" id="txtExtmMaterialGroup" name="txtExtmMaterialGroup" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmMaterialGroup} ">
		</div>
	</td>
	<td style="padding-left:30px"><label>Purchase Group</label>
		<div>
			<input type="text" id="txtExtmPurchaseGroup" name="txtExtmPurchaseGroup" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmPurchaseGroup} ">
		</div>
	</td>
	</tr>
	<tr>
	<td style="padding-top:5px;"><label>Agreement</label>
		<div>
			<input type="text" id="txtExtmAgreement" name="txtExtmAgreement" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmAgreement} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Recipient</label>
		<div>
			<input type="text" id="txtExtmRecipient" name="txtExtmRecipient" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmRecipient} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Requisitioner</label>
		<div>
			<input type="text" id="txtExtmRequisitioner" name="txtExtmRequisitioner" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmRequisitioner} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Planned</label>
		<div>
			<input  id="dteExtmPlanDeliverytime" name="dteExtmPlanDeliverytime" class="easyui-datebox" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmPlanDeliverytime} ">
		</div>
	</td>
	</tr>
	<tr>
	<td style="padding-top:5px;"><label>External</label>
		<div>
			<input type="text" id="txtExtmExtSubContract" name="txtExtmExtSubContract" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmExtSubContract} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Sort</label>
		<div>
			<input type="text" id="txtExtmSortterm" name="txtExtmSortterm" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmSortterm} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Cost</label>
		<div>
			<input type="text" id="txtExtmCostElement" name="txtExtmCostElement" class="easyui-text" style=" width : 200px;text-align:right;" value="${requestScope.sapExternalServiceMst.extmCostElement} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Vendor</label>
		<div>
			<input type="text" id="txtExtmVendor" name="txtExtmVendor" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmVendor} ">
		</div>
	</td>
	</tr>
	<tr>
	<td style="padding-top:5px;"><label>Info</label>
		<div>
			<input type="text" id="txtExtmInfoRecord" name="txtExtmInfoRecord" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmInfoRecord} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Unloading</label>
		<div>
			<input type="text" id="txtExtmUnloadPoint" name="txtExtmUnloadPoint" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmUnloadPoint} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>Tracking</label>
		<div>
			<input type="text" id="txtExtmTrackNo" name="txtExtmTrackNo" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmTrackNo} ">
		</div>
	</td>
	<td style="padding-left:30px;padding-top:5px;"><label>FWOrder</label>
		<div>
			<input type="text" id="txtExtmFwOrder" name="txtExtmFwOrder" class="easyui-text" style=" width : 200px;" value="${requestScope.sapExternalServiceMst.extmFwOrder} ">
		</div>
	</td>
	</tr>
</table>

	<div style="float:left;">
		<table id="externalRepairgrid"></table>
		<div id="externalRepairpager"></div>
	</div>
	<div style="float:left;padding-top: 10px;" >
		<table id="externalRepairSubgrid"  ></table>
		<div id="externalRepairSubpager"></div>
	</div>		
 	<input type="hidden" id="mode"/>
 	<input type="hidden" id="hdnExtmNotificationno" name="hdnExtmNotificationno" value="${requestScope.bdKeyid}">
 	<input type="hidden" id="hdnExtmDate" name="hdnExtmDate" value="${requestScope.date}">
 	<input type="hidden" id="hdnExtmShift" name="hdnExtmShift" value="${requestScope.shift}">
 	<input type="hidden" id="hdnExtmFlid" name="hdnExtmFlid" value="${requestScope.bdflid}">
 	<input type="hidden" id="hdnExtmOrderno" name="hdnExtmOrderno" value="${requestScope.bdKeyid}">
 	<input type="hidden" id="hdnExtmEquipment" name="hdnExtmEquipment" value="${requestScope.mchId}">
 	<input type="hidden" id="hdnExtmKeyid" name="hdnExtmKeyid" value="${requestScope.sapExternalServiceMst.extmKeyid}">	
</form>