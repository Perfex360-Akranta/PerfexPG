<script>
jQuery(document).ready(function() {
	initialiseForm('frmRsrceSkillAssess');
	fillComboBox("frmRsrceSkillAssess","cmbtraining","combo_department.emp" );
	fillComboBox("frmRsrceSkillAssess","cmbdprtmntname","department.rsrsk");
	processGridnew("resourceskill_input.rsrsk", "q=2", "resourceAssess", "pager3","PCM", "", "", "");
	function  frmRsrceSkillAssesscmbtraining_onSelect(record)
	{
		 reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?mouldId="+ record.id ); 
	 }
	if(screen.width <= 1024){
		
		jQuery('#WrapperRpt').css('margin-left','14');
		if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0")
		jQuery('#WrapperRpt').css('margin-left','3\9');
		
        
    	}	
   	else{
   		
   	}
	 var btnName = jQuery("#hdnBtnName").val();
		jQuery("#btnView").val(btnName);
		jQuery("#btnView").click(function()
				{
			//alert("Read From File");
			
			processAjaxCalls("openFile.file?fileName=ResourceSkillIndexAssessment_Sheet.xls", "", "", "", "", "new");					
		});

		 fileManagerPopUp("","ABN","frmRsrceSkillAssess","btnfilemgr","ResFilemgr");  
});

		function btnfilemgr_click()
		{
			//alert("11");
		   // var documentNo =jQuery("#hdnabnkeyID").val();
		   
			if(1 != null && 1 != '')
				{
				fileManagerPopUp(1,"ABN","","","");
			    }
			
		}
</script>
<form id="frmRsrceSkillAssess">
<div id='wrapperRpt'>
<div style="padding-left:74px; margin-top:20px;">
<table>
			<tr>
				<td>
					<div class="easyui-paddingbfpx"><label>Training</label></div>
					<div>
					  <div class="easyui-paddingbfpx">
					<input class="easyui-text" id="cmbtraining" name="cmbtraining" style="width: 265px;" value=" " />
				      </div>
					</div>
				</td>
				<td style="padding-left: 20px;" >

					 <div class="easyui-paddingbfpx"><label >Department Name</label></div>
                         <div>
							<div class="easyui-paddingbfpx">
								<input class="easyui-text" id="cmbdprtmntname" name="cmbdprtmntname" style="width: 265px;" value=" " />
							 
							</div>
							
						</div>
				</td>
				<td>
					<span style="padding-left:250px;padding-left:160px\9;"><input class="easyui-button" type="button" id="btnView" name="btnView"  style='height:24px;' value="View Report"></span>
							 <span  id="ResFilemgr" style="position:absolute;margin-left:12px;margin-left:25px\9;" >
     		                </span>
				</td>
			</tr>
							
</table>
		</div>
			<div id='WrapperRpt' style="margin-top: .3%; padding-left: 36px;";>
			<table id='resourceAssess'>
				<tr>
					<td></td>
				</tr>
				
			</table>
			<div id='pager3'></div>
		</div>
<!--<table >
		<tr>
			<td valign="top">
			<div>
				<label>Training</label>
				<label style="padding-left: 40%">Department Name</label>
			</div>
			<div class="easyui-paddingbfpx">
				<input id="cmbtraining" class="easyui-combobox" name="cmbtraining" style="width: 250px;" />
					<span style="padding-left: 1%"><input id="cmbdprtmntname" class="easyui-combobox" name="cmbdprtmntname" style="width: 250px;" /></span>
			</div>
			<div>
				
			</div>
			<div class="easyui-paddingbfpx">
			
			</div>
			</td>
			</tr>
			<tr>
			<td>
			<div style="float: left; margin-top: -1%; padding-left: 2%;">
						<table id="resourceAssess">
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
			</td>
		</tr>
</table>-->
<input type="hidden" id="hdnBtnName" value="View Report"/>
</div> 
</form>