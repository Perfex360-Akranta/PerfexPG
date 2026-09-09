<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<script type="text/javascript">	

jQuery(document).ready(function(){
	initialiseForm('frmOrderCreation');	

	jQuery('#submitForm').val('frmOrderCreation');	
	formatDateBox('dteorcShiftdate','dd-MMM-yyyy');
	formatDateBox('dteorcOccurreddate','dd-MMM-yyyy');
	formatDateBox('dteorcReporteddate','dd-MMM-yyyy');
	fillWithCurrentDate('dteorcShiftdate');
	fillWithCurrentDate('dteorcOccurreddate');
	fillWithCurrentDate('dteorcReporteddate');
	spinnerKeyPress('spnorcOccuredtime');
	fillComboBox("frmOrderCreation","cmborcBookedby","employee.commonFilter" );
	fillComboBox("frmOrderCreation","cmborcCostcenterid","costCenter.commonFilter");
	fillComboBox("frmOrderCreation","cmborcAssemblyid","assembly.commonFilter");
	fillComboBox("frmOrderCreation","cmborcMachineid","machineCombo.commonFilter" );
	fillComboBox("frmOrderCreation","cmborcShiftid","combo_shift.brdn" );
	//loadFunctionalLocation("orcFunctionalLoc","functionalLoc.ord","orcFunctionalLocValues","frmOrderCreation");
	processGridnew("OrderCreation_input.ord","?q=1","OrderGrid","orderPager","OrderCreation", "doubleclick");
	var setNew = getFieldValue('hdnordCreateNew');
	if (setNew=="Yes"){
		loadFunctionalLocation( "orcFunctionalLoc","functionalLoc.ord","orcFunctionalLocValues","frmOrderCreation");
		setFieldValue("cmbOrcOrderType","");
		setFieldValue("cmborcCostcenterid","");
		setFieldValue("cmborcBookedby","");
		setFieldValue("cmborcAssemblyid","");
		setFieldValue("cmborcMachineid","");
		//setFieldValue("dteorcShiftdate","");
		setFieldValue("cmborcShiftid","");
		//setFieldValue("dteorcOccurreddate","");
		//setFieldValue("dteorcReporteddate","");
		setFieldValue("cmborcPriority","");
		setFieldValue("txtorcProblem","");
		setFieldValue("txtorcBookingremarks","");
		setFieldValue("spnorcReportedtime","");
		setFieldValue("spnorcOccuredtime","");
	}
	else
	{
		loadFunctionalLocation( "orcFunctionalLoc","functionalLoc.ord","orcFunctionalLocValues","frmOrderCreation","&flid=FNLN00000168");
		setFieldValue("cmbOrcOrderType","B");
		setFieldValue("cmborcBookedby","EMP03346");
		setFieldValue("cmborcAssemblyid","");
		setFieldValue("cmborcMachineid","MCH002061");
		//setFieldValue("dteorcShiftdate","19-Sep-2013");
		setFieldValue("cmborcShiftid","SFT003");
		//setFieldValue("dteorcOccurreddate","19-Sep-2013");
		//setFieldValue("dteorcReporteddate","19-Sep-2013");
		setFieldValue("cmborcPriority","M");
		setFieldValue("txtorcProblem","Belt Change");
		setFieldValue("txtorcBookingremarks","Changed");
		setFieldValue("spnorcReportedtime","00:00");
		setFieldValue("spnorcOccuredtime","00:00");
		setFieldValue("cmborcCostcenterid","CST00015");
	}
});
function frmOrderCreation_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	jQuery("#cmborcMachineid").combobox('clear');
	//jQuery("#cmborcCostcenterid").combobox('clear');
	jQuery("#cmborcAssemblyid").combobox('clear');
	reloadCombo("frmOrderCreation","cmborcMachineid","machineCombo.commonFilter?machId="+keyIds.machId );
	reloadCombo("frmOrderCreation","cmborcCostcenterid","costCenter.commonFilter?cellId="+keyIds.cellId +"&factId="+keyIds.factId );
	reloadCombo("frmOrderCreation","cmborcAssemblyid","assembly.commonFilter?machId="+keyIds.machId);
	setFieldValue('cmborcMachineid',keyIds.machId);	
	setFunctionalLocWidth("frmOrderCreation","500px");
}
function frmOrderCreationcmborcMachineid_onSelect(record)
{
	
	loadFunctionalLocation( "orcFunctionalLoc","functionalLoc.ord","orcFunctionalLocValues","frmOrderCreation","&machId="+record.id);
}
function doubleclick(id){
	 var rowData = jQuery("#OrderGrid").jqGrid('getRowData',id);
	 var OrderType= rowData.OrderType;
	 if( OrderType =="Breakdown" )
		navigateToNextForm('OrderBreakDown_input.ord'+'?q=1',"Breakdown");
	 else if(OrderType =="External Request")
		navigateToNextForm('OrderExternal_input.ord'+'?q=1',"External Service Request");
}

jQuery("#btnOrcCreate").click(function() {
		var OrdType = getFieldValue('cmbOrcOrderType');
		 if( OrdType =="B" )
			navigateToNextForm('OrderBreakDown_input.ord'+'?q=1',"Breakdown");
		 else if(OrdType =="E")
			navigateToNextForm('OrderExternal_input.ord'+'?q=1',"External Service Request");
	});
</script>
<form id="frmOrderCreation" name="frmOrderCreation" >
<div id="wrapper" style="width:100%" >
<div class="main-cntborder" >
	<div>
	<table>
		<tr>
			<td> 
				<div>
					<label>Notification No. </label>
				</div>
				<div>
					<input type="text" class="easyui-text" id = "txtorcKeyid" name="txtorcKeyid"  tabindex = "-1" disabled value="" style="width:265px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;" />
				</div>
			</td>
			<td>
				<div>
					<label>Status</label>
				</div>
				<div>
					<input type="text" class="easyui-text" id = "txtorcStatus" name="txtorcStatus"  tabindex = "-1" disabled value="" style="width:150px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;" />
				</div>
			</td>
			
			
		</tr>
		<tr>
		<td colspan="3">
			<table width="100%">	
				<tr>
					<td>
						<div id="frmOrderCreationFuntKeyIds">
							<input type="hidden" id="company" name="cmborccompany" value=""></input>
							<input type="hidden" id="factory" name="cmborcFactoryid" value=""></input>
							<input type="hidden" id="section" name="cmborcSectionid" value=""></input>
							<input type="hidden" id="cell" name="cmborcCellid" value=""></input>
							<input type="hidden" id="machine" name="cmborcMachineid" value=""></input>
				  		</div>
				 		<div id="orcFunctionalLoc"  style="width: 300px;width: 75%\9;" align="left"></div>		 		
					</td>
						</tr>
			</table>
			</td>
			
			<td>
				<div>
					<div>
						<label id="lblOrderType">Order Type</label>
						<span style="margin-left:95px;"><label id="lblOrderType">Service</label></span>
					</div>
					<div>
						<select id="cmbOrcOrderType" class="easyui-combobox" name="cmbOrcOrderType"  style="height: 22px; width: 150px;" >
								<option value="B"> BREAKDOWN</option>
								<option value="P"> PM</option>
								<option value="J"> JH</option>														
								<option value="J"> Kaizen</option>
						</select> 
						
						<select id="cboService" name="cboService"  style="height: 22px; width: 150px;" >
								<option value="B">External Service</option>
								<option value="P">External Repair</option>								
						</select>
						
					<input type="button" class="easyui-button" value ="Create" name="btnOrcCreate" id="btnOrcCreate" style="height:23px;margin-top:12px"/>
				
					</div>
				</div>
			</td>
			
		</tr>
			<tr>
				<td colspan="2" valign="top">
					<table>
					<tr>
						<td>
							<div>
								<label>CostCentre</label>
							</div>
							<div>
								<input id="cmborcCostcenterid" name="cmborcCostcenterid"  class="easyui-combobox" value="" style="width: 265px;" />
							</div>
						</td>
						<td>
							<div style="margin-left: 10px;">
								<div>
									<label class="mandatory-lbl ">Equipment </label>
								</div>
								<div style="width:265px;">
									<input  id="cmborcMachineid" name="cmborcMachineid" class="easyui-combobox" value="" style="width: 265px;" />					
								</div>
							</div>
						</td>
					</tr>	
					<tr>
						<td>
							<div>	
								<label id="lblAssm">Assembly</label>
							</div>
							<div>
						    	<input  id="cmborcAssemblyid" name="cmborcAssemblyid" class="easyui-combobox"  value=""  style="width: 265px;" />
							</div>
						</td>
						<td>
							<div style="margin-left: 10px;">
								<div>
									<label class="mandatory-lbl">Shift date </label>
									<label style="padding-left: 127px;_padding-left: 126px;">Shift</label>
								</div>
								<div  style="width:265px;">
									<input id="dteorcShiftdate"  name="dteorcShiftdate" clear="false" class="easyui-datebox" value="" style="width: 178px;width: 168px\9;"/>
								    <input  id="cmborcShiftid" name="cmborcShiftid" class="easyui-combobox"  value=""  style="width: 83px;width: 87px\9;"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<label class="mandatory-lbl">Occurred Date Time </label>
							</div>
							<div>
								<input id="dteorcOccurreddate"  name="dteorcOccurreddate" clear="false" class="easyui-datebox" value="" style="width: 180px;width: 168px\9;" />
							    <span class="spinner" ><input  id="spnorcOccuredtime" name="spnorcOccuredtime" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 78px; width: 87px\9;" ></span>
							</div>
						</td>
						<td>
							<div style="margin-left: 10px;">
								<div>
									<label class="mandatory-lbl " style="">Reported Date Time</label>
								</div>
								<div style="width:265px;">
									<input id="dteorcReporteddate"  name="dteorcReporteddate" clear="false" class="easyui-datebox" value=""  style="width: 178px;width: 168px\9;" />
								    	<span class="spinner"><input  id="spnorcReportedtime" name="spnorcReportedtime" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 81px;width: 85px\9;" ></span>
								</div>
							</div>
						</td>
					</tr>	
					<tr>
						<td>
							<div>
								<label class="mandatory-lbl">Booked By</label>
							</div>
							<div>
								<input  id="cmborcBookedby" name="cmborcBookedby" class="easyui-combobox"  value=""  style="width: 265px;"/>
							</div>
						</td>
						<td>
							<div style="margin-left: 10px;">
								<div>
									<label class="mandatory-lbl " >Priority </label>
								</div>
								<div style="width:265px;">
									<select id="cmborcPriority" class="easyui-combobox" name="cmborcPriority"  panelHeight=80px;  style="width:265px;" >
											<option value="L"> LOW</option>
											<option value="M"> MEDIUM</option>
											<option value="H"> HIGH</option>
											<option value="V"> VERY HIGH</option>
										 </select> 
								</div>
							</div>
						</td>
						
					</tr>
					<tr>
						<td>
							<div>
								<label class="mandatory-lbl">Problem</label>
							</div>
							<div>
								<textarea  id="txtorcProblem" name="txtorcProblem" style="resize:none;width:265px;" maxlength="600" ></textarea>
							</div>
						</td>
						<td>
							<div style="margin-left: 10px;">
								<div>
									<label>Remarks</label>
								</div>
								<div>
									<textarea  id="txtorcBookingremarks" name="txtorcBookingremarks" maxlength="600" style="resize:none;width:265px;" ></textarea>
								</div>
							</div>
						</td>
					</tr>
					
				</table>
				</td>
				<td colspan="2">
					<div style="margin-left:10px;">
	<!--					<div class="sub-header" >Order Details</div>-->
						<label style="color:dark brown;font-weight: bold">Double click to view the details</label>
						<div>
							<table id="OrderGrid" ></table> 
						</div>
						<div id="orderPager"></div> 
					</div>
				</td>
			</tr>
		</table>
		</div>
	</div>
	</div>
<input type="hidden" id="hdnordCreateNew" name="hdnordCreateNew" value="${requestScope.CreateNew}"/>
	</form>