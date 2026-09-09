<script>
jQuery(document).ready(function(){
	initialiseForm("frmExterRepairDetail");	 
	numericTextBox('txtExtrRequirementQty');
	 
	processGridnew("externalRepair_view.brdn","&q=2&ExtMasterId="+jQuery('#hdnExtrExtmKeyid').val(),"dtlexternalRepairSubgrid","dtlexternalRepairSubpager","","");
	jQuery('#btnextRepDtlSave').click(function() {
    	saveForm("frmExterRepairDetail","externalRepairDtl_save.brdn");
    });
	jQuery('#btnextRepDtlDelete').click(function() {
		var dtlKeyid = jQuery('#hdnExtrKeyid').val(); 
		if(dtlKeyid.trim().length>0 )
			deleteRecord("frmExterRepairDetail","externalRepairDtl_Delete.brdn?&ExtrKeyid="+dtlKeyid);
		else
			alert("No Data To Delete");
    });
	
});
function frmExterRepairDetail_deleteSuccessCallback(result){ 
	alert(result.successData.msg);
	jQuery('#dtlexternalRepairSubgrid').trigger("reloadGrid");
}
function frmExterRepairDetail_successsCallback(result){ 
	jQuery('#dtlexternalRepairSubgrid').trigger("reloadGrid");	
}
</script>
<form id="frmExterRepairDetail" name="frmExterRepairDetail">
<div >
<table cellspacing='25'>
	<tr>
		<td>
			<div><label>Component No</label></div>
			<div><input type="text" class="easyui-text" style="width:220px;" id="txtExtrComponentNo" name="txtExtrComponentNo" value="${requestScope.newSapExternalRepair.extrComponentNo }" id="txtExtrComponentNo" name="txtExtrComponentNo" /></div>			
		</td>
		<%-- <td>
			<div><label>Part No</label></div>
			<div><input type="text" class="easyui-text" style="width:220px;" id="txtExtrComponentNo" name="txtExtrComponentNo" value="${requestScope.newSapExternalRepair.}" id="txtExtrComponentNo" name="txtExtrComponentNo" value="${requestScope.newSapExternalRepair.}"/></div>
		</td> --%>
		<td>
			<div><label>Requirement Qty</label></div>
			<div><input type="text" class="easyui-text" style="width:220px;text-align:right;" id="txtExtrRequirementQty" name="txtExtrRequirementQty" value="${requestScope.newSapExternalRepair.extrRequirementQty}" id="txtExtrComponentNo" name="txtExtrComponentNo" /></div>
		</td>
	
		<td>
			<div><label>UOM</label></div>
			<div><input type="text" class="easyui-text"   maxlength="10"style="width:220px;" id="txtExtrUom" name="txtExtrUom" value="${requestScope.newSapExternalRepair.extrUom}"/></div>			
		</td>
		 
	</tr>
	<tr>
		<td>
			<div><label>Item Category</label></div>
			<div><input type="text" class="easyui-text" maxlength='10' style="width:220px;" id="txtExtrItemCategory" name="txtExtrItemCategory" value="${requestScope.newSapExternalRepair.extrItemCategory}"/></div>
		</td>
		<td>
			<div><label>Storage Location</label></div>
			<div><input type="text" class="easyui-text" style="width:220px;" id="txtExtrStorageLocation" name="txtExtrStorageLocation" value="${requestScope.newSapExternalRepair.extrStorageLocation}"/></div>
		</td>
		<td>
			<div><label>Material Rework Indicator</label></div>
			<div><input type="text" class="easyui-text" style="width:220px;" id="txtExtrMatReworkIndi" name="txtExtrMatReworkIndi" value="${requestScope.newSapExternalRepair.extrMatReworkIndi}"/></div>
		</td> 
	</tr>
	<tr>
	<td>
		<div><label> Part No</label></div>
		<div><input type="text" class="easyui-text"   maxlength="10"style="width:220px;" id="txtExtrPartno" name="txtExtrPartno" value="${requestScope.newSapExternalRepair.extrPartno}"/></div>
			
	</td>
	<td  colspan='2'>
		<div   style='margin-top:10px;'>
				<input type="button" value='Save' id='btnextRepDtlSave' class='easyui-button'/>
				<span   style='margin-top:10px;margin-left:3px;'>
					<input type="button" value='Delete' id='btnextRepDtlDelete' class='easyui-button'/> 
				</span> 
			</div>
		</td>
	</tr>
</table>
</div>
<div style="float:left;padding-top: 10px;" >
		<table id="dtlexternalRepairSubgrid"  ></table>
		<div id="dtlexternalRepairSubpager"></div>
</div>
<input type='hidden' id='mode'/>
<input type='hidden' id='hdnExtrExtmKeyid' name="hdnExtrExtmKeyid" value='${requestScope.extMasterId}'/>
<input type='hidden' id='hdnExtrKeyid' name="hdnExtrKeyid" value='${requestScope.newSapExternalRepair.extrKeyid }'/>

</form>