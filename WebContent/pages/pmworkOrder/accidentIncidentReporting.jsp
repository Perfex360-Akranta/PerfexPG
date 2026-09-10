<script type="text/javascript">	
	jQuery(document).ready(function(){	
		
		setTimeout(function() {fillComboBox("frmAccidentIncident","cmbSftShift","combo_shift.brdn" );},1250);
		setTimeout(function() {fillComboBox("frmAccidentIncident","cmbSftrptShift","combo_shift.brdn" );},1250);
		setTimeout(function() {fillComboBox("frmAccidentIncident","cmbSftDept","combo_department.dfl" );},1250);
		setTimeout(function() { fillComboBox("frmAccidentIncident","cmbRptdBy","employee.commonFilter");},1250);
		setTimeout(function() { fillComboBox("frmAccidentIncident","cmbSftAcctype","IncidentTypeCombo.entbatch");},1250);
		jQuery('#txtrepDteTme').attr('disable');
		loadFunctionalLocation("sftfunLocation","functionalLoc.brdn","","frmAccidentIncident","");
		processGridnew("Accincident_input.accIncRpt","","empdtlsGrid","pager2","","");
	});
	function button_AddEmp(id, options, rowObject)
	{					
		var rowId = options.rowId;
		
		return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
	}
	function AddEmpPopUp()
	{					
		LoadPopUp("divAddEmployee","SheEmployeeAdd_input.accIncRpt", true,"400px","300px","0px","20%", "multiSelectOk_Callback","Incident Employee");
		
	}
</script>

<form id="frmAccidentIncident" >
	<div id="wrapper">
		
			<div class="sub-header"><b><label >When and Where</label></b></div>
			<table   align="center" width="100%">
				<tr>
					<td>
					
						<div class="floatleft">
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Occurred Date Time</label>
							<label>Shift</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<input id="txtoccDteTme" type="text" class="easyui-text" size="15" name="txtoccDteTme" >
								</span>
								
								<span style="padding-left: 30px;">
									<input id="cmbSftShift" name="cmbSftShift" class="easyui-combobox"  style="width:140px;"  /> 
								</span>
							</div>
							
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Accident Type</label></div>
							<span style="padding-left: 30px;">
								<input id="cmbSftAcctype" name="cmbSftAcctype" class="easyui-combobox"  style="width:250px;"  /> 
							</span>
							<div id="sftfunLocation" style="padding-left: 30px;width:300px;"></div>
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Department</label></div>
							<span style="padding-left: 30px;">
								<input id="cmbSftDept" name="cmbSftDept" class="easyui-combobox"  style="width:250px;"   /> 
							</span>
						</div>
						<div style="height:15px;">
							
						</div>
						
					</td>
					<td>
						<div class="floatleft">
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Reported Date Time</label>
							<label>Shift</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<input id="txtrepDteTme" type="text" class="easyui-text" size="15" name="txtrepDteTme" disabled="disabled">
								</span>
								
								<span style="padding-left: 30px;">
									<input id="cmbSftrptShift" name="cmbSftrptShift" class="easyui-combobox"  style="width:140px;" disabled="disabled" /> 
								</span>
							</div>
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Reported By</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<input id="cmbRptdBy" type="text" class="easyui-combobox" name="cmbRptdBy" style="width:250px;">
								</span>
							</div>
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Division</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<input id="txtDivision" type="text" class="easyui-text" name="txtDivision" style="width:250px;">
								</span>
							</div>
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Area</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<input id="txtsftArea" type="text" class="easyui-text" name="txtsftArea" style="width:250px;">
								</span>
							</div>
						</div>
						
					</td>
				</tr>
				<tr>
					<td width="50%">
						<div class="sub-header" ><b><label >Employee Details</label></b></div>
						
						<div  align="left" style="width:100%;">		
							<table align="left" id="empdtlsGrid" >
								<tr><td><td/></tr>
							</table>
							<div id="pager2"></div>
						</div>	
						<div style="padding-left:20px;">
							<span ><input id="btnBatchAdd" name="btnBatchAdd" type="button" class="easyui-button"  value="Add"  style="width:50px; height:25px;" onclick=" AddEmpPopUp();"/></span>	
						</div>
					</td>
					<td width="50%">
						<div class="sub-header" ><b><label >Accident/Incident Details</label></b></div>
						<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Description of Accident/ Incident</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<textarea id="txtaccdesc" name="txtaccdesc"  style="resize:none;width:270px;height:75px;"></textarea>
								</span>
							</div>
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Immediate Action taken by Reporter</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<textarea id="txtActiontkn" name="txtaccdesc"  style="resize:none;width:270px;height:75px;"></textarea>
								</span>
							</div>
							<div  class="easyui-paddingbfpx" style="padding-left: 30px;"><label>Recommendations from Reporter</label></div>
							<div style="padding-bottom:0px; padding-left: 30px;" >	
								<span style="margin-left:2px;">
									<textarea id="txtrecommdtns" name="txtrecommdtns"  style="resize:none;width:270px;height:75px;"></textarea>
								</span>
							</div>
							<div style="height:40px;">
							</div>
					</td>
				</tr>
			</table>
		</div>

	
</form>