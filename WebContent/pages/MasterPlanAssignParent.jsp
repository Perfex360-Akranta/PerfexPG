
<script type="text/javascript">	
jQuery.noConflict();
jQuery(document).ready(function(){		
	jQuery('#submitForm').val('frmMPAssignPnt'); 
	initialiseForm("frmMPAssignPnt");
	var cellId = jQuery('#hdnMspiCellid').val();
	var level =jQuery("#frmMPAssignPnt input[id='hdnMspiLevel']").val();// jQuery('#hdnMspiLevel').val();
	
	fillComboBox("frmMPAssignPnt","cmbCategory","categoryCmb.conf?cellId=" +cellId );
	if(level.trim() != "2")
		fillComboBox("frmMPAssignPnt","cmbSubcategory","subCategoryCmb.conf?cellId=" +cellId );	
	else
	   readOnlyFields('cmbSubcategory');			
});
jQuery("#btnAssignPnt").click(function(){
	var category = jQuery('#cmbCategory').combobox('getValue');	
 	if(category != null  &&  category !="" && category !=" " &&category !="null"){	   	
	    jQuery("#preLodDiv").css('display','block');
		jQuery("#preLodDiv").addClass("tpm-loading");
		show_winMask(1);
		saveForm('frmMPAssignPnt','assignParent_save.conf?q=2');		
	}
 	else
 	 	alert('Select Category');
});
function frmMPAssignPntcmbCategory_onSelect(record)
{
	var level =jQuery("#frmMPAssignPnt input[id='hdnMspiLevel']").val();
	if(level.trim() != "2")
	{
		var cellId = jQuery('#hdnMspiCellid').val();
		jQuery('#cmbSubcategory').combobox('clear');
		reloadCombo("frmMPAssignPnt","cmbSubcategory","subCategoryCmb.conf?cellId=" +cellId+"&parentId="+record.id );
	}
}


</script>
<form id="frmMPAssignPnt" name="frmMPAssignPnt">
<div style="padding-left:20px;">
		<div class="sub-header" style="width:260px;">${requestScope.title}</div>
		<div>
			<label class="mandatory-lbl">Category</label>
		</div>
		<div class="easyui-paddingbfpx">
			<input  id="cmbCategory" name="cmbCategory" class="easyui-combobox" style="width:260px;"/ >	
		 </div>	 
		 <div>
			<label>Sub Category</label>
		</div>
		<div class="easyui-paddingbfpx">
			 <input  id="cmbSubcategory" name="cmbSubcategory" class="easyui-combobox" style="width:260px;"/ >	
		 </div>	 
		 <div style="padding-left:25%;margin-top:10px;">
			<input id="btnAssignPnt" class="easyui-button" name="btnAssignPnt"  type="button" value="Assign" style="height:20px;"  />
		</div>
		
		
		<input type="hidden" id="hdnMspiLevel" name="hdnMspiLevel" value="${requestScope.levelNo}"/>		
		<input type="hidden" id="hdnMspiKeyid" name="hdnMspiKeyid" value="${requestScope.nodeId}"/>
		<input type="hidden" id="hdnMspiFactoryid" name="hdnMspiFactoryid" value="${requestScope.factId}"/>
		<input type="hidden" id="hdnMspiSectionid" name="hdnMspiSectionid" value="${requestScope.sectId}"/>
		<input type="hidden" id="hdnMspiCellid" name="hdnMspiCellid" value="${requestScope.cellId}"/>			
</div>
</form>