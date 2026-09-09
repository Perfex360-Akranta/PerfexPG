<script>
	jQuery(document).ready(function(){
		var empGrid = jQuery("#hdnEmpGrid").val();
		var pillarCode = jQuery('#hdnPillarId').val();
		var pillarKeyid;
		 if("ET" == pillarCode)
			pillarKeyid ="TGT001";
		else if("JH" == pillarCode)
			pillarKeyid ="TGT002";
		else if("KK" == pillarCode)
			pillarKeyid ="TGT003";
		else if("OTPM" == pillarCode)
			pillarKeyid ="TGT004";
		else if("PM" == pillarCode)
			pillarKeyid ="TGT005";
		else if("QM" == pillarCode)
			pillarKeyid ="TGT006";
		else if("EHS" == pillarCode)
			pillarKeyid ="TGT007";
		else if("EM" == pillarCode)
			pillarKeyid ="TGT008";
		 
		if(empGrid.trim()=='Y')
			jQuery("#LinkempGrid").show();
		else
			jQuery("#LinkempGrid").hide();
		processGridnew("kpiIndicatorgrd_input.kpiActKk","q=2&pillarHdn="+pillarKeyid,"KpiIndicatorgrd","kpilstpager","KPI Indicators","kpilst_doubleClickGrid","","kpilst_loadComplete","kpiLstselectRowFunction");
		processGridnew("pilarWiseEmp_input.base","q=2","empgrd","emplstpager","Employee","emp_doubleClickGrid","","emp_loadComplete"," ");
	});
	function chk_Employee(id, options, rowObject)
	{ 
		var rowId = options.rowId;	
		return '<input id="employee_checkbox" name="employee_checkbox" '+ (rowObject[2]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	}
</script>
<form id="frmempKpi">
<div>
	
	<table cellspacing="20">
	<tr>
		<td colspan="2">
			<div><label style="padding:5px;border:solid 1px #c1c1c1;">FiberLine#1</label>
			<select id="cmbuniqueposition" name="cmbuniqueposition">
				<option> Unique Position 1</option>
				<option> Unique Position 2</option>
				<option> Unique Position 3</option>
				<option> Unique Position 4</option>
				<option> Unique Position 5</option>
			</select>
		 	<span style="margin-left:20px;">	
			<input type="button" value="Apply" class="easyui-button"/>
			</span></div>
		</td>
	</tr>
		<tr>
			<td>
				<div id="LinkempGrid">
					<table id="empgrd">
						<tr><td></td></tr>
					</table>
					<div id="emplstpager"></div>
				</div>
			</td>
			<td>
				<table id="KpiIndicatorgrd">
					<tr><td></td></tr>
				</table>
				<div id="kpilstpager"></div>
			</td>
		</tr>
	</table>
</div>
<input type="hidden" id="hdnEmpGrid" name="hdnEmpGrid" value="${requestScope.EmpGrid}"/>
</form>