<script type="text/javascript">

jQuery(document).ready(function(){ 
		initialiseForm("frmSAPInfoBD");	
        //jQuery('#submitForm').val('frmSAPInfoBD');
        var url = jQuery('#hiddenUrl').val();    	
    	//setTimeout(function() {},1250);
    	//fillComboBox("frmSAPInfoBD","cmbSpmoOrdertype","sapOrderType.brdn","",false);
    	//processGridnew("sapInfo_view.brdn","&q=2","sapinfogrid","sapinfopager","","");
        //setTimeout(function() {},1250);

    /*	jQuery("#btnSparesInfo").click(function(){
    		//navigateToNextForm("Sapstackinformation_input.sapinfo","SAP Stock Information");
    		openSAPSpareInfo("");
    	 });*/
    	jQuery('#btnSapsprSave').click(function(){
    		saveForm("frmSAPInfoBD","SAPRelatedData_save.sapinfo");
    	});   
        
     });
     function frmSAPInfoBD_beforeSubmit(){
   	  
     }

</script>
     	
<form name="frmSAPInfoBD" id="frmSAPInfoBD">
	
	<table>
		<tr>
			<td valign="top" >
				 
				<div class="easyui-paddingbfpx">
					<label>Order No</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtOpno" name="txtModtOpno" type="text" class="easyui-text"  maxlength="95"  style="width:300px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtOpno}" />--%>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Duration</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtDuration" name="txtModtDuration" type="text" class="easyui-text"  maxlength="95"  style="width:300px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtDuration}" />--%>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Work Unit</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtWorkunit" name="txtModtWorkunit" type="text" class="easyui-text"  maxlength="3"  style="width:300px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtWorkunit}" />--%>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Checking Tool</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtCheckingtool" name="txtModtCheckingtool" type="text" class="easyui-text"  maxlength="95"  style="width:300px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtCheckingtool}" />--%>
				</div>
				 
			</td>
			<td style="padding-left: 30px;" valign="top">
				 
			<div class="easyui-paddingbfpx">
					<label>Control Key</label>
					<span style="margin-left:30%;"><label>Priority</label></span>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtControlkey" name="txtModtControlkey" type="text" class="easyui-text"  maxlength="95"  style="width:140px;height:21px;" value="${requestScope.sapTlMaintenanceorder.ModtControlkey}" /> 
					<span style='padding-left:12px;'>
						<input id="txtModtPriority" name="txtModtPriority" type="text" class="easyui-text"  maxlength="95"  style="width:140px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtPriority}" />--%>
					</span>
				</div>
				
				<div class="easyui-paddingbfpx">
					<label>CCKey</label>
					<span style="margin-left:39%;"><label>OP Number</label></span>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtCckey" name="txtModtCckey" type="text" class="easyui-text"  maxlength="1"  style="width:140px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtCckey}" /> --%>
					<span style='padding-left:14px;'>
						<input id="txtModtOpno" name="txtModtOpno" type="text" class="easyui-text"  maxlength="2"  style="width:140px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtOpno}" />--%>
					</span>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Duration Unit</label>
					
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtDurationunit" name="txtModtDurationunit" type="text" class="easyui-text"  maxlength="95"  style="width:300px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtDurationunit}" />--%>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Ideal Condition</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtIdealcondn" name="txtModtIdealcondn" type="text" class="easyui-text"  maxlength="95"  style="width:300px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtIdealcondn}" />--%>
				</div>
				<div class="easyui-paddingbfpx">
					<label>WBS Element</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtWbselement" name="txtModtWbselement" type="text" class="easyui-text"  maxlength="8"  style="width:300px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtWbselement}" />--%>
				</div>
			</td>		
			<td style="padding-left: 30px;" valign="top">	
				  
				<div class="easyui-paddingbfpx">
					<label>OP Description</label>
				</div>
				<div class="easyui-paddingbfpx">
					<textarea id="txtModtOpdesc" name="txtModtOpdesc"   maxlength="95"  style="width: 200px; height:38px;" ></textarea>
<%-- 					${requestScope.sapTlMaintenanceorder.ModtOpdesc} 							 --%>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Work</label>
				</div>
				<div class="easyui-paddingbfpx">
<!-- 					<textarea id="txtModtwork" name="txtModtwork"   maxlength="95"  style="width: 200px; height:38px;" ></textarea>									 -->
					<input id="txtModtWork" name="txtModtWork" type="text" class="easyui-text"  maxlength="5"  style="width:200px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtWork}" />
				</div>
				<div class="easyui-paddingbfpx">
					<label>FieldKey</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtFieldkey" name="txtModtFieldkey" type="text" class="easyui-text"  maxlength="7"  style="width:200px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtFieldkey}" />--%>
				</div>
				<div class="easyui-paddingbfpx">
					<label>Actual Condition</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtModtActualcondn" name="txtModtActualcondn" type="text" class="easyui-text"  maxlength="10"  style="width:200px;height:21px;" />  <%-- value="${requestScope.sapTlMaintenanceorder.ModtActualcondn}" />--%>
				</div>	
			</td>
		</tr>
		
		<tr> 
			<td  colspan="2" style="padding-top:20px;" >
				<div class="sub-header" style="width:450px;position:relative;">No Of Spares Count:3     No Of Qty:3 
				<span style="position:absolute;right:-60;top:-5;">
				 <input type="button" class="easyui-button" style=" " id="btnSapsprSave" name="btnSapsprSave" value="Save"/>
				 </span>
				</div>
			</td>
			
		</tr>
	</table>
	
 	<input type="hidden" id="mode"/>
 	<input type="hidden" id="txtRefdocid" name="txtRefdocid" value="${requestScope.refdocId}"/>
 	<input type="hidden" id="txtExistDocNumber" name="txtExistDocNumber" value="${requestScope.existWoId }"/>
 	<input type="hidden" id="txtModtRefdoctype" name="txtModtRefdoctype" value="${requestScope.refDocType}"/>
 	<input type="hidden" id="txtModtMachineid" name="txtMomsMachineid" value="${requestScope.machineId}"/>
 	<input type="hidden" id="txtPriority" name="txtPriority" value="${requestScope.priority}"/>
 	  
 	
</form>