<script type="text/javascript">
	jQuery(document).ready(function(){
		var url = jQuery('#hiddenUrl').val();
		var from = jQuery('#hdnFrom').val();
		initialiseForm('frmCriteriaMaster');
		jQuery('#submitForm').val('frmCriteriaMaster');
		jQuery('#frmCriteriaMaster-text').css('text-transform', 'uppercase');
		jQuery('#frmCriteriaMaster-combobox').css('text-transform', 'uppercase');
		fillComboBox("frmCriteriaMaster","cmbCriaTradeid","Combo_Trade.abnForm");
		//fillComboBox("frmCriteriaMaster","cmbCriaKeyid","mchrnkskill_fillcombo.mchrnkskl" );
		
		var factId = jQuery("#frmCriteriaMaster input[id='factory']").val();
		var sectionId = jQuery("#frmCriteriaMaster input[id='section']").val();
		var cellId = jQuery("#frmCriteriaMaster input[id='cell']").val();
		var machId = jQuery("#frmCriteriaMaster input[id='machine']").val();
		var flid = jQuery("#frmCriteriaMaster input[id='flid']").val();

		var dataStr = "&factId=" + factId + "&sectionId="+ sectionId + "&cellId=" + cellId + "&machId="+ machId + "&flid=" +flid;//FNLN00000004
		loadFunctionalLocation("CriafunLocation","functionalLoc.commonFilter","CriafunLocationValues", "frmCriteriaMaster",dataStr);
		
		viewGrid(url,"?q=2");
		jQuery("#txtCriaMaximumpoints").bind("keyup",checkMaxMark);
		//processGridnew(url,"?q=2","criteriagrid","criteriapager"," ","doubleClickGrid");
		//if("criticality"!=from)
	});
	

	function checkMaxMark(){
			var val=getFieldValue("txtCriaMaximumpoints");
			val=val|0;
			if(parseInt(val)>900){
				alert("Maximum Value Should Be 900");
				setFieldValue("txtCriaMaximumpoints","900");
			}
		}
	function frmCriteriaMaster_FuntLocHierarchy_SuccessCallBack(keyIds)
	{  
		setFunctionalLocWidth('frmCriteriaMaster','499px');
		
		var flid = jQuery("#frmCriteriaMaster input[id='flid']").val();
		var url = jQuery('#hiddenUrl').val();
		
			viewGrid(url,"");
	
		
	}
	function viewGrid(url,filterString)
	{  
		var flid = jQuery("#frmCriteriaMaster input[id='flid']").val();
		if(flid == '' || flid == ' ' || flid == undefined)
		{
			
		}
		else
		{
			filterString = "flid="+flid;
			processGridnew(url,filterString,"criteriagrid","criteriapager"," ","doubleClickGrid");
		}
	}
	function doubleClickGrid(id)
	{
		fillFormValues(id,"doubleClickGrid");
		
	}

	function frmCriteriaMaster_successsCallback(result)
	{
		jQuery("#criteriagrid").trigger("reloadGrid");
		reloadCombo("frmCriteriaMaster","cmbCriaKeyid","mchrnkskill_fillcombo.mchrnkskl");
		setFieldValue('txtCriaKeyid','');		
		setFieldValue('cboCriaName','');
		setFieldValue('txtCriaCode','');
		setFieldValue('txtCriaMinimumpoints','');
		setFieldValue('txtCriaMaximumpoints','');
		//setFieldValue('cmbCriaTradeid','');
		
	}

	function frmCriteriaMaster_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);
		jQuery("#criteriagrid").trigger("reloadGrid");
		reloadCombo("frmCriteriaMaster","cmbCriaKeyid","mchrnkskill_fillcombo.mchrnkskl");
	}

	function frmCriteriaMastercmbCriaKeyid_onSelect(result)
	{
		fillFormValues(result.id,"onSelect");
	}

	function fillFormValues(id,fname)
	{
		var rowData = jQuery("#criteriagrid").jqGrid('getRowData',id);
		
		var name = rowData.name;
		//alert(name);
		var code = rowData.code;
		var maxPoint = rowData.maximumpoints;
		var minPoint = rowData.minimumpoints;
		var tradeid = rowData.tradeid;
		var keyId = rowData.keyid;
		///alert("keyId:"+keyId);
		setFieldValue('txtCriaKeyid',keyId,'frmCriteriaMaster');		
		//setFieldValue('cboCriaName',name,'frmCriteriaMaster');
		jQuery("#cboCriaName").val(name);
		setFieldValue('txtCriaCode',code,'frmCriteriaMaster');
		setFieldValue('txtCriaMinimumpoints',minPoint,'frmCriteriaMaster');
		setFieldValue('txtCriaMaximumpoints',maxPoint,'frmCriteriaMaster');
		setFieldValue('cmbCriaTradeid',tradeid,'frmCriteriaMaster');
		
	}
	
	function gotFocuse(id) {
		numericTextBox(id);
	}
</script>

<form id="frmCriteriaMaster">
	<div class="main-cntborder" >
		<table style="width:80%;height:100px;margin-left:5%;margin-top:1%">
			<tr>
				<td style=" vertical-align:top" >
					<div id="frmCriteriaMasterFuntKeyIds">
						<div style="float: left; padding-right: 20px;">
							<input type="hidden" id="factory" name="factory" value=""></input>
							 <input type="hidden" id="section" name="section" value=""></input>
							 <input type="hidden" id="cell" name="cell" value=""></input>
							  <input type="hidden" id="machine" name="machine" value=""></input>
							 <input type="hidden" id="flid" name="cmbCriaFlid" value="${requestScope.flid}"></input>
						</div>
						<div id="CriafunLocation" style="width: 550px;"></div>
					</div>					
				</td>
				<td>
					<div><label id="lblTrade" >Trade </label></div>
						<div id="tradeDiv" class="easyui-paddingbfpx">
						<input id="cmbCriaTradeid" name="cmbCriaTradeid"  class="easyui-combobox"  style="width:250px;" value="${requestScope.PlmTlCriteriamst.criaTradeid}"  /> 
						</div>
				</td>		
			</tr>
			<tr>
				<td style="width:50%; vertical-align:top">
					
					<!--<div>
						<div><label>Criteria</label></div>
						<input id="cmbCriaKeyid" type="text" class="easyui-combobox" name="cmbCriaKeyid" value="" style="width:250px;"/>
                          <select class="easyui-text" id="cmbCriaKeyid" name="cmbCriaKeyid" style="width:  250px; height: 21px;" >
                            <option value=''></option>
							<option value='CA'>Criteria A</option>
							<option value='CB'>Criteria B</option>
							<option value='CC'>Criteria C</option>
							</select>
					</div>
					-->
					<div>
						<div><label class="mandatory-lbl">Code</label></div>
						<input id="txtCriaCode" type="text" class="easyui-text" name="txtCriaCode" value="${requestScope.PlmTlCriteriamst.criaCode}" style=" width : 250px;" maxlength="12"/>
					</div>
				</td>
				<td style="width:50%; vertical-align:top">
					<div>
						<div><label class="mandatory-lbl">Name</label></div>
						<!--  <input id="txtCriaName" type="text" class="easyui-text" name="txtCriaName" value="${requestScope.PlmTlCriteriamst.criaName}" style=" width : 250px;" maxlength="100" />
						-->
							<select id="cboCriaName"  name="cboCriaName"    style="height:22px;width:120px;">
								<option value="CRITICAL">CRITICAL</option>
								<option value="NON CRITICAL">NON CRITICAL</option>							
							</select> 
					</div>
				</td>
			</tr>
			<tr style="margin-top:2px">
				<td style="width:50%; vertical-align:top">					
					<div><label class="mandatory-lbl">Minimum Points</label></div>
					<div>
						<input id="txtCriaMinimumpoints" onfocus="gotFocuse(this.id)" type="text" class="easyui-text" name="txtCriaMinimumpoints" value="" style=" width : 123px; text-align:right" maxlength="5" />
					</div>
				</td>
				<td style="width:50%; vertical-align:top">	
					<div><label class="mandatory-lbl" style="padding-left:0px;">Maximum Points</label></div>				
					<div>
						<input id="txtCriaMaximumpoints" onfocus="gotFocuse(this.id)" type="text" class="easyui-text" name="txtCriaMaximumpoints" value="${requestScope.PlmTlCriteriamst.criaMaximumpoints}" style=" width : 123px; text-align:right" maxlength="5" />
					</div>
				</td>
			<tr style="margin-top:2px">
					
			</tr>
		</table>
		
		<input type="hidden" id="mode"/>
		<input type="hidden" id="txtCriaKeyid" name="txtCriaKeyid" value="${requestScope.PlmTlCriteriamst.criaKeyid}"/>
		
		<div style="margin-top:1%;" >
			<div class="clear"></div>
			<div style="float:left;margin-left:5%">
				<table id ='criteriagrid' >
					<tr><td></td></tr>
				</table>
				<div id ='criteriapager'></div>
			</div>
		</div>
	</div>	
</form>		