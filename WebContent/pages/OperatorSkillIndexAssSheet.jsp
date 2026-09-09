<script>
jQuery(document).ready(function() {
	initialiseForm('frmRsrceSkillAssess');
	fillComboBox("frmOperatorSkillAss","cmbtraining","combo_department.emp" );
	fillComboBox("frmOperatorSkillAss","cmbdprtmntname","department.rsrsk");
	processGridnew("OperatorSkillIndex_input.oprSkillInd", "q=2", "OperatorSkill", "pager","PCM", "", "", "");

	 var btnName = jQuery("#hdnBtnName").val();
		jQuery("#btnView").val(btnName);
		jQuery("#btnView").click(function()
				{
			//alert("Read From File");
			
			processAjaxCalls("openFile.file?fileName=OperatorSkillIndexAssessment_Sheet.xls", "", "", "", "", "new");					
		});

	 fileManagerPopUp("","ABN","frmRsrceSkillAssess","btnfilemgr","OpsFilemgr"); 
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
<form id=frmOperatorSkillAss>
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
							  <span style="padding-left:248px;padding-left:160px\9;"><input class="easyui-button" type="button" id="btnView" name="btnView"  style="height:26px;" value="View Report"></span> 
							  <span id="OpsFilemgr" style="position:absolute;margin-left:12px;margin-left:25px\9;" >
     		                  </span> 
							</div>
						</div>
				</td>
			</tr>
							
</table>
</div>
	<div id='WrapperRpt' style="margin-top:.3%; padding-left: 36px;">
			<table id='OperatorSkill'>
				<tr>
					<td></td>
				</tr>
				
			</table>
			<div id='pager'></div>
		</div>
		
		<input type="hidden" id="hdnBtnName" value="View Report"/> 
</form>