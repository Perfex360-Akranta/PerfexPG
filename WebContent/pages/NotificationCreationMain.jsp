<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmNotificationCreation');	
	formatDateBox('dtencShiftdate','dd-MMM-yyyy');
	formatDateBox('dtencOccurreddate','dd-MMM-yyyy');
	formatDateBox('dtencReporteddate','dd-MMM-yyyy');
	fillComboBox("frmNotificationCreation","cmbncBookedby","employee.commonFilter" );
	fillComboBox("frmNotificationCreation","cmbncCostcenterid","costCenter.commonFilter");
	fillComboBox("frmNotificationCreation","cmbncAssemblyid","assembly.commonFilter");
	fillComboBox("frmNotificationCreation","cmbncMachineid","machineCombo.commonFilter" );
	fillComboBox("frmNotificationCreation","cmbncShiftid","combo_shift.brdn" );
	loadFunctionalLocation("ncFunctionalLoc","functionalLoc.SerLevAgr","ncFunctionalLocValues","frmNotificationCreation","");
});
function frmOrderCreation_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	jQuery("#cmbncMachineid").combobox('clear');
	jQuery("#cmbncCostcenterid").combobox('clear');
	jQuery("#cmbncAssemblyid").combobox('clear');
	reloadCombo("frmNotificationCreation","cmbncMachineid","machineCombo.commonFilter?machId="+keyIds.machId );
	reloadCombo("frmNotificationCreation","cmbncCostcenterid","costCenter.commonFilter?cellId="+keyIds.cellId +"&factId="+keyIds.factId );
	reloadCombo("frmNotificationCreation","cmbncAssemblyid","assembly.commonFilter?machId="+keyIds.machId);
	setFieldValue('cmborcMachineid',keyIds.machId);
}
</script>
<form id="frmNotificationCreation" name="frmNotificationCreation" >
<div style="margin-left:4%">
<div>
	<table border="0" align="center" width="100%">	
		<tr>
			<td colspan = "3">
				<label  class="wo-lblWoNo">Work Order No </label>
				<label class="wo-lblStatus">Status</label>
				<div class="easyui-paddingbfpx">
					<span>
						<input type="text" class="easyui-text" id = "txtncKeyid" name="txtncKeyid"  tabindex = "-1" disabled value="" style="width:265px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;margin-left:298px;" />
					</span>
					<span><input type="text" class="easyui-text" id = "txtncStatus" name="txtncStatus"  tabindex = "-1" disabled value="" style="width:150px;background-color:#ece9d8;font-weight:bold;text-align:center;color:#245edc;margin-left:30px;" /></span>
				</div>
			</td>
		</tr>
		<tr><td colspan="3"></td></tr>
		<tr><td colspan="3"></td></tr>
		<tr>
			<td colspan = "3">
				<div id="frmNotificationCreationFuntKeyIds">
					<input type="hidden" id="factory" name="cmbncFactoryid" value=""></input>
					<input type="hidden" id="section" name="cmbncSectionid" value=""></input>
					<input type="hidden" id="cell" name="cmbncCellid" value=""></input>
					<input type="hidden" id="machine" name="cmbncMachineidhdn" value=""></input>
		  		</div>
		 		<div id="ncFunctionalLoc" style=""></div>		 		
			</td>
		</tr>
		<tr>
			<td colspan = "3" >
				<div style=" padding-top:20px;">
					<label id="lblMachine" class="mandatory-lbl ">Machine </label>
				</div>
				<div class="easyui-paddingbfpx" style="margin-bottom:10px;">
					 
					  <span>
							<input  id="cmbncMachineid" name="cmbncMachineid" class="easyui-combobox" value="" style="width: 265px;" />
						</span>						
				</div>
				<div>
					<span id="err_cmbncMachineid"  style="margin-top:-15;padding-left:483;" class="tpm-errormsg"></span>	
				</div>
			</td>
			
		</tr>		
	</table>
	</div>
	<div>
	<table border="0" align="center" width="100%">
		<tr><td colspan="3"></td></tr>
		<tr><td colspan="3"></td></tr>
		<tr>
			<td colspan = "3">
<!--			    <div class="sub-header" style="width:75%;">Request Details</div>-->
				<div style=" padding-top:10px;">
					<label class="mandatory-lbl">Shift date </label>
					<label class="wo-lblShiftid">Shift</label>	
					<label id="lblAssm" class="wo-lblAssembly">Assembly</label>	
					<label style="padding-left:248px; _padding-left:231px;">CostCentre</label>	
					
			   </div>
				<div class="easyui-paddingbfpx">
					<input id="dtencShiftdate"  name="dtencShiftdate" clear="false" class="easyui-datebox" value="" style="width: 180px;"/>
				    <input  id="cmbncShiftid" name="cmbncShiftid" class="easyui-combobox"  value=""  style="width: 80px;"/>
				    <span style=" margin-left:30px; _margin-left:27px;">
				    	<input  id="cmbncAssemblyid" name="cmbncAssemblyid" class="easyui-combobox"  value=""  style="width: 265px;" />
				    </span>
				    <span style="margin-left:21px;"><input id="cmbncCostcenterid" name="cmbncCostcenterid"  class="easyui-combobox" value="" style="width: 265px;" /></span>
									  	
			  </div>
			  	<div class="easyui-paddingbfpx">
			  		<span id="err_dtencShiftdate" class="tpm-errormsg"></span>
				  	<span id="err_cmbncAssemblyid" class="tpm-errormsg" style="padding-left:295px;"></span>
			  	</div>
			</td>
		</tr>
		<tr><td colspan="3"></td></tr>
		<tr><td colspan="3"></td></tr>
		<tr>
			<td colspan = "3">
				<div>
					<label class="mandatory-lbl">Occurred Date Time </label>
					<label class="mandatory-lbl wo-lblReportedTime" style="">Reported Date Time</label>	
					<label class="mandatory-lbl " style="padding-left:175px; _padding-left:179px;">Priority </label>												
					
				</div>
				<div class="easyui-paddingbfpx">
				   <input id="dtencOccurreddate"  name="dtencOccurreddate" clear="false" class="easyui-datebox" value="" style="width: 180px;" />
				    <span class="spinner" ><input  id="spnncOccuredtime" name="spnncOccuredtime" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 77px;" ></span>
					<span style=" margin-left:30px;  _margin-left:27px;">
						<input id="dtencReporteddate"  name="dtencReporteddate" clear="false" class="easyui-datebox" value=""  style="width: 180px;" />
				    	<span class="spinner"><input  id="spnncReportedtime" name="spnncReportedtime" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 80px;" ></span>
					</span>
					<span style="margin-left:21px;">
					    <select id="cmbncPriority" class="easyui-combobox" name="cmbncPriority"  panelHeight="80px"  style="width:265px;" >
							<option value="L"> LOW</option>
							<option value="M"> MEDIUM</option>
							<option value="H"> HIGH</option>
							<option value="V"> VERY HIGH</option>
						 </select> 
						 <input type="hidden" id="hdnPriority" name="hdnPriority"  value="" />
				  </span>
				 
					
					<span id="err_dtencOccurreddate" class="tpm-errormsg"></span>
				  	<span id="err_dtencReporteddate" class="tpm-errormsg" style="padding-left:295px;"></span>
				  	 <span id="err_cmbncPriority" class="tpm-errormsg" style="padding-left:597px"></span>
				  
					
				</div>
			</td>
		</tr>
		<tr>
			<td colspan = "3">
				<div>
					<label class="mandatory-lbl">Booked By</label>				
					<label id="lblForAbn" class="mandatory-lbl" style="padding-left:235px;">Affect Loss</label>
				</div>
				<div class="easyui-paddingbfpx">
						<input  id="cmbncBookedby" name="cmbncBookedby" class="easyui-combobox"  value=""  style="width: 265px;"/>
						
					
					<span style="padding-left:30px;" id="spnLoss">
							<select id="cmbncLoss" class="easyui-combobox" clear="false" name="cmbncLoss" style="height: 22px;width:265px;" panelHeight=60px;  >
								<option value="U"> UNPLANNED MAINTENANCE</option>
								<option value="J"> JH TAG REMOVAL</option>							
								<option value="M"> M AND A</option>							
							</select> 
							<input type="hidden" id="hdnLoss" name="hdnLoss"  value=""/>
						</span>
					<span id="err_cmbncBookedby" class="tpm-errormsg"></span>
					<span id="err_cmbncActivitytype" class="tpm-errormsg" style="padding-left:295px;"></span>
				</div>
			</td>
		</tr>	
		<tr><td colspan="3"></td></tr>
		<tr><td colspan="3"></td></tr>
		<tr>
			<td colspan = "3">
				<div>
					<label class="mandatory-lbl" id="lblWoProblem">Problem</label>
					<label class="wo-lblRemarks">Remarks</label>	
				</div>
				<div class="easyui-paddingbfpx">
				  	  <textarea  id="txtncProblem" name="txtncProblem" style="resize:none;width:265px;" maxlength="600" ></textarea>
					   <span style="margin-left:30px;">
							<textarea  id="txtncBookingremarks" name="txtncBookingremarks" maxlength="600" style="resize:none;width:265px;" ></textarea>
					  </span>
					<span id="err_txtncProblem" class="tpm-errormsg"></span>
					
			 </div>
			</td>
		</tr>
	</table>
</div>
</div>
	</form>