<script type="text/javascript">
	jQuery(document).ready(function(){
		var url = jQuery('#hiddenUrl').val();
		var from = jQuery('#hdnFrom').val();
		initialiseForm('frmMachineRnkSkill');
		jQuery('#submitForm').val('frmMachineRnkSkill');
		jQuery('#frmMachineRnkSkill.easyui-text').css('text-transform', 'uppercase');
		jQuery('#frmMachineRnkSkill.easyui-combobox').css('text-transform', 'uppercase');

		fillComboBox("frmMachineRnkSkill","cmbMrskKeyid","mchrnkskill_fillcombo.mchrnkskl" );

		processGridnew(url,"?q=2","tableid","pagerid"," ","doubleClickGrid");
		//if("criticality"!=from)
	});

	function doubleClickGrid(id)
	{
		fillFormValues(id,"doubleClickGrid");
		
	}

	function frmMachineRnkSkill_successsCallback(result)
	{
		jQuery("#tableid").trigger("reloadGrid");
		reloadCombo("frmMachineRnkSkill","cmbMrskKeyid","mchrnkskill_fillcombo.mchrnkskl");
	}

	function frmMachineRnkSkill_deleteSuccessCallback(result)
	{
		alert(result.successData);
		jQuery("#tableid").trigger("reloadGrid");
		reloadCombo("frmMachineRnkSkill","cmbMrskKeyid","mchrnkskill_fillcombo.mchrnkskl");
	}

	function frmMachineRnkSkillcmbMrskKeyid_onSelect(result)
	{
		fillFormValues(result.id,"onSelect");
	}

	function fillFormValues(id,fname)
	{
		var rowData = jQuery("#tableid").jqGrid('getRowData',id);
		
		var name = rowData.NAME;
		var code = rowData.CODE;
		var maxPoint = rowData.MAXIMUMPOINTS;
		
		if(fname=="doubleClickGrid")
		{
			setFieldValue('cmbMrskKeyid',id,'frmMachineRnkSkill');
		}
		
		setFieldValue('txtMrskName',name,'frmMachineRnkSkill');
		setFieldValue('txtMrskCode',code,'frmMachineRnkSkill');
		setFieldValue('txtMrskMaximumpoints',maxPoint,'frmMachineRnkSkill');
	}
	
	function gotFocuse(id) {
		numericTextBox(id);
	}
</script>

<form id="frmMachineRnkSkill">
	<div class="main-cntborder" style="width:750px;  margin-top:20px;">
		<table style="width:80%;height:100px;margin-left:10%;margin-top:1%">
			<tr>
				<td style="width:50%; vertical-align:top">
					<div>
						<div><label>Criteria</label></div>
<!--						<input id="cmbMrskKeyid" type="text" class="easyui-combobox" name="cmbMrskKeyid" value="" style="width:250px;"/>-->
                            <select class="easyui-text" id="cmbMrskKeyid" name="cmbMrskKeyid" style="width:  250px; height: 21px;" >
                            <option value=''></option>
							<option value='CA'>Criteria A</option>
							<option value='CB'>Criteria B</option>
							<option value='CC'>Criteria C</option>
							</select>
					</div>
				</td>
				<td style="width:50%; vertical-align:top">
					<div>
						<div><label class="mandatory-lbl">Name</label></div>
						<input id="txtMrskName" type="text" class="easyui-text" name="txtMrskName" value="${requestScope.GenTlMchrankskillmst.mrskName}" style=" width : 250px;"/>
					</div>
				</td>
			</tr>
			<tr style="margin-top:2px">
				<td style="width:50%; vertical-align:top">
					<div>
						<div><label class="mandatory-lbl">Code</label></div>
						<input id="txtMrskCode" type="text" class="easyui-text" name="txtMrskCode" value="${requestScope.GenTlMchrankskillmst.mrsCode}" style=" width : 250px;" maxlength="15"/>
					</div>
				</td>
				<td style="width:50%; vertical-align:top">
					<div>
					<div><label class="mandatory-lbl">Minimum Points</label><label class="mandatory-lbl" style="padding-left:36px;">Maximum Points</label></div>
					<input id="txtMrskMinimumpoints" onfocus="gotFocuse(this.id)" type="text" class="easyui-text" name="txtMrskMinimumpoints" value="" style=" width : 123px; text-align:right" maxlength="3" />
					
					<span>
					<input id="txtMrskMaximumpoints" onfocus="gotFocuse(this.id)" type="text" class="easyui-text" name="txtMrskMaximumpoints" value="${requestScope.GenTlMchrankskillmst.mrskMaximumpoints}" style=" width : 123px; text-align:right" maxlength="3" />
					</span>
					</div>
				</td>
			</tr>
		</table>
		
		<input type="hidden" id="mode"/>
		<input type="hidden" id="txtMrskKeyid" name="txtMrskKeyid" value="${requestScope.GenTlMchrankskillmst.mrskKeyid}"/>
		
		<div style="margin-top:1%;" >
			<div class="clear"></div>
			<div style="float:left;margin-left:10%">
				<table id ='tableid' >
					<tr><td></td></tr>
				</table>
				<div id ='pagerid'></div>
			</div>
		</div>
	</div>
	<input type="hidden" id="hdnFrom" value="${requestScope.from }">
</form>		