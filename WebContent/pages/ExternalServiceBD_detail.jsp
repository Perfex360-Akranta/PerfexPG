<script>
jQuery(document).ready(function(){
	initialiseForm("frmExterServDetail");
	numericTextBox('txtExtdValue');
	numericTextBox('txtExtdQty');
	numericTextBox('txtCost');
	numericTextBox('txtExtdTotalPrice');
	readOnlyFields('txtExtdTotalPrice');
	fillComboBox("frmExterServDetail","cmbExtdServiceNo","combo_ExternalServiceno.brdn");
	fillComboBox("frmExterServDetail","cmbExtdUom","uomCombo.commonFilter");
	processGridnew("externalService_view.brdn","&q=2&ExtMasterId="+jQuery('#hdnExtmKeyid').val(),"dtlexternalServiceGrd","dtlexternalServicePager","","dtlExtserviceDblClick");
	
	jQuery('#txtExtdCostElement').val('CK02');
	
	jQuery('#btnExtDtlSave').click(function() {
    	saveForm("frmExterServDetail","externalserviceDtl_save.brdn");
    });
	
	jQuery('#btnExtDtlDelete').click(function() {
		var dtlKeyid = jQuery('#hdnExtdKeyid').val();
		if(dtlKeyid.trim().length>0 )
			deleteRecord("frmExterServDetail","externalserviceDtl_Delete.brdn?&ExtdKeyid="+dtlKeyid);
		else
			alert("No Data To Delete");
    });
});

function dtlExtserviceDblClick(rowId) {
	
	 var rowData = jQuery("#dtlexternalServiceGrd").jqGrid('getRowData',rowId);
	 var extMasterId =  rowData.mstkeyid;
     var formMode = jQuery('#hdnExtFormMode').val();
	 var extDtlKeyid = rowData.detKeyid; 	 
    // alert(extDtlKeyid);
	 setFieldValue("hdnExtdExtmKeyid",extMasterId);
	 setFieldValue("hdnExtdKeyid",extDtlKeyid);
	 setFieldValue("cmbExtdServiceNo",rowData.ServKey);
	 setFieldValue("txtExtdServiceText",rowData.SERVICETEXT);
	 setFieldValue("txtExtdQty",rowData.QUANTITY);
	 setFieldValue("cmbExtdUom",rowData.UOMCODE);
	 setFieldValue("txtExtdValue",rowData.GROSSPRICE);
	 setFieldValue("txtExtdCurrency",rowData.CURRENCY);
	 setFieldValue("txtExtdTotalPrice",rowData.TOTALPRICE);
     if(rowData.ServKey.length>0){
    	 readOnlyFields("txtExtdServiceText");
     }
     else{
    	 enableFields("txtExtdServiceText");
     }
	 //alert(rowData.ServKey);
}

function frmExterServDetailcmbExtdServiceNo_onSelect(record) {	
	//alert(record.id);
	processAjaxCalls('getSapServicetext.brdn','?&servmNo='+record.id,"servmSuccess");
}

function servmSuccess(result){
//	alert(result.shortText);
	if(result.shortText.length>1){
		//disableField("frmExterServDetail","txtExtdServiceText");
		jQuery('#txtExtdServiceText').val(result.shortText);
		readOnlyFields("txtExtdServiceText");
		//jQuery('#txtExtdServiceText').attr('disabled',false);
	}
	else{
		jQuery('#txtExtdServiceText').val('');
		enableFields("txtExtdServiceText");
	}
}
function frmExterServDetail_beforeSubmit(){
	if(jQuery('#txtExtdServiceText').val().length==0){
		alert("Enter Service Text");
		return false;
	}
	if(jQuery('#txtExtdQty').val().length==0){
		alert("Enter Quantity");
		return false;
	}
	if(jQuery('#txtExtdValue').val().length==0){
		alert("Enter Gross Price");
		return false;
	}
	if(getFieldValue("cmbExtdUom","frmExterServDetail").length==0){
		alert("Select UOM");
		return false;
	}
	totalFunction();
}
function frmExterServDetail_deleteSuccessCallback(result){
	alert(result.successData.msg);
	jQuery('#dtlexternalServiceGrd').trigger("reloadGrid");
}

function frmExterServDetail_successsCallback(result){
	jQuery('#dtlexternalServiceGrd').trigger("reloadGrid");
	jQuery('#externalServiceGrd').trigger("reloadGrid");
	setFieldValue("hdnExtdKeyid","");
	enableFields("txtExtdServiceText");
	//clear
	setFieldValue("hdnExtdKeyid","");
	 setFieldValue("cmbExtdServiceNo","");
	 setFieldValue("txtExtdServiceText","");
	 setFieldValue("txtExtdQty","");
	 setFieldValue("cmbExtdUom","");
	 setFieldValue("txtExtdValue","");
	 setFieldValue("txtExtdCurrency","");
	 setFieldValue("txtExtdTotalPrice","");
	
}

function loadExtServiceDetail_onClose(){
	// alert("closing..");
	 jQuery('#externalServiceGrd').trigger("reloadGrid");
	 return true;
 }
 function totalFunction(){
	 var qty=jQuery("#txtExtdQty").val();
	 var gross=jQuery("#txtExtdValue").val();
	 if(qty==''){
		 jQuery("#txtExtdQty").val('0');
	 }
	 if(gross==''){
		 jQuery("#txtExtdValue").val('0');
	 }
	 var total;
	 total=qty*gross;
	 jQuery("#txtExtdTotalPrice").val(total);
	 
 }
</script>
<form id="frmExterServDetail" name="frmExterServDetail">
<div  >
<table cellspacing='15'>
	<tr>
		<td>
			<div><label>Service No</label></div>
			<div><input  class="easyui-combobox" style="width:220px;" id="cmbExtdServiceNo" name="cmbExtdServiceNo" value="${requestScope.newSapExternalServiceDtl.extdServiceNo }"/></div>			
		</td>
		<td>
			<div><label class="mandatory-lbl">Service Text</label></div>
			<div>
			<textArea  style="resize:none;width:220px;height:48px;" maxlength="40" id="txtExtdServiceText" name="txtExtdServiceText" value="${requestScope.newSapExternalServiceDtl.extdServiceText}"></textArea>
			
			</div>
		</td>
		<td>
			<div><label class="mandatory-lbl">Quantity</label></div>
			<div><input type="text" class="easyui-text" maxlength="13" style="width:117px;text-align:right;" id="txtExtdQty" name="txtExtdQty" onkeydown="totalFunction()"  value="${requestScope.newSapExternalServiceDtl.extdQty}"/></div>
		</td>
		<td>
			<div><label class="mandatory-lbl">UOM</label></div>
			<div>
			<input  class="easyui-combobox" style="width:220px;" id="cmbExtdUom" name="cmbExtdUom" value="${requestScope.newSapExternalServiceDtl.extdUom}"/>
			<%-- <input type="text" class="easyui-text" style="width:102px;" maxlength="3" id="txtExtdUom" name="txtExtdUom" value="${requestScope.newSapExternalServiceDtl.extdUom}"/> --%>
			</div>
		</td>
	</tr>
	<tr>
		
		
		<td>
			<div><label class="mandatory-lbl">Gross Price</label></div>
			<div><input type="text" class="easyui-text" maxlength="11" style="width:180px;text-align:right;" id="txtExtdValue" name="txtExtdValue" onkeydown="totalFunction()" value="${requestScope.newSapExternalServiceDtl.extdValue}"/></div>			
		</td>
		<td>
			<div><label>Currency</label></div>
			<div><input type="text"  class="easyui-text" maxlength="10" style="width:220px;" id="txtExtdCurrency" name="txtExtdCurrency" value="${requestScope.newSapExternalServiceDtl.extdCurrency}"/></div>
		</td>
		<td>
			<div><label>Total Price</label></div>
			<div><input type="text"  class="easyui-text" maxlength="10" style="width:117px;" id="txtExtdTotalPrice" name="txtExtdTotalPrice" value="${requestScope.newSapExternalServiceDtl.extdTotalPrice}"/></div>
		</td>
		<td colspan="2">
			<div style="margin-top:0px;">
				<input type="button" value='Save' id='btnExtDtlSave' class='easyui-button'/>
				<span style="margin-left:3px;">
				<input type="button" value='Delete' id='btnExtDtlDelete' class='easyui-button'/>
				</span> 
			</div>
		</td>
	</tr>
	<tr>
		<td>
			
			<div><input type="text" class="easyui-text" style="width:220px;display:none;" id="txtExtdCostElement" name="txtExtdCostElement" value="${requestScope.newSapExternalServiceDtl.extdCostElement}" /></div>			
		</td>
			 
	</tr>
	 
</table>
</div>
<div style="float:left;padding-top: 10px;" >
		<table id="dtlexternalServiceGrd"  ></table>
		<div id="dtlexternalServicePager"></div>
	</div>	
<input type='hidden' id='mode'/>
<input type='hidden' id='hdnExtdExtmKeyid' name="hdnExtdExtmKeyid" value='${requestScope.extMasterId }'/>
<input type='hidden' id='hdnExtdKeyid' name="hdnExtdKeyid" value='${requestScope.newSapExternalServiceDtl.extdKeyid }'/>
</form>