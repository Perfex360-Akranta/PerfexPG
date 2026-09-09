<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
jQuery(document).ready(function(){
	initialiseForm('frmExternalServiceRequest');
	formatDateBox('dteESRBookeddate','dd-MMM-yyyy');
	formatDateBox('dteESRExpecteddate','dd-MMM-yyyy');
	formatDateBox('dteESRReturndate','dd-MMM-yyyy');
	formatDateBox('dteESRReturndate1','dd-MMM-yyyy');
	formatDateBox('dteESRdate','dd-MMM-yyyy');
	formatDateBox('dteESRCompletiondate','dd-MMM-yyyy');
	fillComboBox("frmExternalServiceRequest","cmbESREquipment","equipmentgroup.commonFilter" );
	fillComboBox("frmExternalServiceRequest","cmbESRAssembly","assembly.commonFilter" );
	fillComboBox("frmExternalServiceRequest","cmbESRCostCenter","costCenter.commonFilter" );
	fillComboBox("frmExternalServiceRequest","cmbESRSentBy","employee.commonFilter" );
	fillComboBox("frmExternalServiceRequest","cmbESRSendto","employee.commonFilter" );
	fillComboBox("frmExternalServiceRequest","cmbESRCmpSBy","employee.commonFilter" );
	fillComboBox("frmExternalServiceRequest","cmbESRSpare","spareCombo.commonFilter" );
	processGridnew("ExternalComp_input.ord","?q=1","compDetailGrd","compDetail","ExternalServiceRequestCom", "");
	//loadFunctionalLocation("eqpmfunLocation","functionalLoc.eqp","equipfunLocationValues","frmEquipment",dataStr);
	
	
	//processGridnew("ExternalStatus_input.SerLevAgr","?q=1","ExternalStatusGrid","ExtStatusPager","ExternalServiceRequestStatus", "");
});	
</script>
<form id="frmExternalServiceRequest" name="frmExternalServiceRequest">
<div id="wrapper" >	
	<div  id="frmExternalServiceRequestFuntKeyIds"  align="left">
		<input type="hidden" id="company" name="cmbcompany" value=""></input>
		<input type="hidden" id="factory" name="cmbMchmfact" value=""></input>
		<input type="hidden" id="section" name="cmbSection" value=""></input>
		<input type="hidden" id="cell" name="cmbMchmCellid" value=""></input>
		<input type="hidden" id="machine" name="cmbMchmKeyid" value=""></input>
	</div>
	<div id="ESRfunLocation"  style="width: 116%; padding-left:2%;" align="left"></div>	
	<table>
		<tr>
			<td style=" width : 310px;">
				<div><label>Document No</label>
		            <span style="padding-left:66px;"><label >Date</label></span></div>
		            <div class="easyui-paddingbfpx"  >
		            	<input id="cmbESRKeyid" name="cmbESRKeyid" class="easyui-combobox"  style="width:135px;text-transform:capitalize;"  value="${requestScope.plmTlGenmaintenance.gmntKeyid}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
		            	<span style="padding-left:5px;">
		            	<input class="easyui-datebox" clear = "false" id="dteESRBookeddate" name="dteESRBookeddate" style="width:125px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
		            	 
						</span>
					</div> 
	               
			</td>
			<td >
				<div style=" " align="left">   
			       <div ><label class="mandatory-lbl">Equipment</label></div>
			   	   <div class="easyui-paddingbfpx">
			   			<input id="cmbESREquipment" name="cmbESREquipment" class="easyui-combobox"  style="width:320px;" value=""    > 
			   		</div>
				 </div>
			</td>
		</tr>
		<tr>
			<td style=" width : 310px;">	
				<div  class="easyui-paddingbfpx">
					<label class="mandatory-lbl">Reference No</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="txtESRreferenceNo" name="txtESRreferenceNo" class="easyui-text"  style="width:270px;text-align: right;" value="${requestScope.plmTlGenmaintenance.gmntResponsetime}"  <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>></input> 
				</div>	
			</td>
			<td rowspan="2" valign="top">
				<div style="margin-top:5px;"  class="easyui-paddingbfpx">
          		<label class="mandatory-lbl">Description</label>
          	</div>
          	
          	<div class="easyui-paddingbfpx" >
          		<textarea rows="2" style="height:65px; width : 320px;" cols="" id="txtESRDescription" name="txtESRDescription" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntRootcause}</textarea>
          	</div>
			</td>
		</tr>
		<tr>
			<td style=" width : 310px;">
				<div style=" " align="left">   
			       <div ><label class=" ">Cost Center</label></div>
			   	   <div class="easyui-paddingbfpx">
			   			<input id="cmbESRCostCenter" name="cmbESRCostCenter" class="easyui-combobox"  style="width:270px;" value=""    > 
			   		</div>
				 </div>
			</td>
		</tr>
	</table>
	<div class="clear"> </div>       
    <div  id="tabExternalServiceRequest" class="easyui-tabs"  style="height:345px;width:815px;  float:left">
		<!--First tab Start-->
		<div title="Status" style=" width:506px;height:auto;">
			<table style="margin-left:5%;" width="100%;">
				<tr style=" height : 375px;">
					<td style=" width : 282px;">
						<div class="sub-header" style="width:100%">Requested Details</div>
						 <div class="easyui-paddingbfpx">
						 <label  class="mandatory-lbl">Nature of Work</label>
						 <span style="padding-left:67px;"><label  class="mandatory-lbl">Status</label></span>
						 </div>
						 <div style="padding-bottom: 8px;padding-right:10px; " class="easyui-paddingbfpx"> 
	                		<select id="cmbESRNOW" class="easyui-combobox" name="cmbESRNOW"  style="height:23px;width:120px;" panelHeight="140px" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>>
								<option value="GS">General Service</option>
	   		 					<option value="R">Repair</option>			
	   		 					<option value="O">Overhauling</option>
	   		 					<option value="Re">Reconditioning</option>							
						    </select>
						    <span style="padding-left:35px;">
						    	<select id="cmboplmRelated" class="easyui-combobox" name="cmboplmRelated"  style="height: 22px;width:105px;"<c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>>
								<option value="P">Pending</option>
	   		 					<option value="C">Completed</option>							
						    </select>
						    </span> 
						</div>
						<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Sent By</label></div>
						<div>
						<input id="cmbESRSentBy" name="cmbESRSentBy" class="easyui-combobox"  style="width:277px;" value=""    >
						</div>
						<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Send To</label></div>
						<div>
						<input id="cmbESRSendto" name="cmbESRSendto" class="easyui-combobox"  style="width:277px;" value=""    >
						</div>
						<div class="easyui-paddingbfpx">
						<label>Expected Date</label>
						<span style="padding-left:50px;">
						<label>StandBy ?</label>
						</span>
						</div>
						<div  class="easyui-paddingbfpx">
						<input class="easyui-datebox" clear = "false" id="dteESRExpecteddate" name="dteESRExpecteddate" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
						<span style="padding-left:20px;">
							<input type="checkbox" id="chkESRStandby" name="chkESRStandby" />
						</span>
						</div>
						<div class="easyui-paddingbfpx">
						<label>Reference No</label>
						</div>
						<div  class="easyui-paddingbfpx">
							<input type="text" class="easyui-text" style="width:277px;"/>
						</div>
						<div class="easyui-paddingbfpx">
						<label>Stand By Description</label>
						</div>
						<div  class="easyui-paddingbfpx">
							<textarea rows="2" cols="31"></textarea>
						</div>
						<div class="easyui-paddingbfpx">
						<label>Returned ?</label>
						<span style="padding-left:13px;">
						<label>Returned Date</label>
						</span>
						</div>
						<div class="easyui-paddingbfpx" >
						 <span style="padding-left:5px;">
						 <input type="checkbox" id="chkESRStandby" name="chkESRStandby" />
						 </span>
						<span style="padding-left:55px;">
						<input class="easyui-datebox" clear = "false" id="dteESRReturndate" name="dteESRReturndate" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
						
						</span>
						</div>
					</td>
					<td valign="top" width='50%;' style="padding-left:15px;">
					 <div class="sub-header" style="width:80%">Completion Details</div>
						  <div class="easyui-paddingbfpx"><label class="mandatory-lbl">Sent By</label></div>
						  <div  class="easyui-paddingbfpx">
							<input id="cmbESRCmpSBy" name="cmbESRCmpSBy" class="easyui-combobox"  style="width:270px;" value=""    >
						   </div>
						   <div class="easyui-paddingbfpx"><label class=" ">Date</label></div>
						   <div  class="easyui-paddingbfpx">
						   <span style="">
						      <input class="easyui-datebox" clear = "false" id="dteESRdate" name="dteESRdate" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
						   </span>
						   </div>
						   <div class="easyui-paddingbfpx">
						  	 <label class="">Action Taken</label>
				           </div>
				          	
				          	<div class="easyui-paddingbfpx" >
				          		<textarea rows="2" style="height:65px; width : 270px;" cols="" id="txtESRDescription" name="txtESRDescription" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntRootcause}</textarea>
				          	</div>
				          	 <div class="easyui-paddingbfpx">
						  	 <label class="">Observation</label>
				           </div>
				          	
				          	<div class="easyui-paddingbfpx" >
				          		<textarea rows="2" style="height:65px; width : 277px;" cols="" id="txtESRDescription" name="txtESRDescription" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntRootcause}</textarea>
				          	</div>
				          	 <div class="easyui-paddingbfpx">
				          	 <label class=" ">Completion Date</label>
				          	 <span style="padding-left:50px;"><label class=" ">Service Cost</label></span>
				          	 </div>
						   <div>
						   <span style="">
						      <input class="easyui-datebox" clear = "false" id="dteESRCompletiondate" name="dteESRCompletiondate" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
						   </span>
						   <span style="padding-left:10px;">
						   		<input type="text" class="easyui-text" style="width:135px;"/>
						   </span>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div title="Component Details" style="width:506px;height:auto;">
		<table width="100%" style="margin-left:5%;">
			<tr>
				<td valign="top"> 
					<div class="easyui-paddingbfpx"><label>Assembly </label></div>
					 <div  class="easyui-paddingbfpx">
						<input id="cmbESRAssembly" name="cmbESRAssembly" class="easyui-combobox"  style="width:270px;" value="" >
					  </div> 
					  <div class="easyui-paddingbfpx"><label>Spare </label></div>
					 <div  class="easyui-paddingbfpx">
						<input id="cmbESRSpare" name="cmbESRSpare" class="easyui-combobox"  style="width:270px;" value="" >
					  </div> 
				</td>
				<td>
					<div class="easyui-paddingbfpx">
				  	 <label class="">Problem Description</label>
		           </div>
		          	
		          	<div class="easyui-paddingbfpx" >
		          		<textarea rows="2" style="height:65px; width : 277px;" cols="" id="txtESRDescription" name="txtESRDescription" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntRootcause}</textarea>
		          	</div>
				</td>
				
			</tr>
			<tr>
				<td valign="top">
					 <div class="easyui-paddingbfpx">
						<label>StandBy ?</label>
						<span style="padding-left:15px;">
						<label>Reference No</label>
						</span>
						</div>
						<div class="easyui-paddingbfpx" >
						 <span style="padding-left:5px;">
						 <input type="checkbox" id="chkESRStandby" name="chkESRStandby" />
						 </span>
						<span style="padding-left:55px;">
						<input class="easyui-text"   id="dteESRRefrenceno" name="dteESRRefrenceno" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
						
						</span>
						</div>
						<div class="easyui-paddingbfpx">
						<label>Returned ?</label>
						<span style="padding-left:13px;">
						<label>Returned Date</label>
						</span>
						</div>
						<div class="easyui-paddingbfpx" >
						 <span style="padding-left:5px;">
						 <input type="checkbox" id="chkESRStandby" name="chkESRStandby" />
						 </span>
						<span style="padding-left:55px;">
						<input class="easyui-datebox" clear = "false" id="dteESRReturndate1" name="dteESRReturndate1" style="width:130px;" value="${requestScope.plmTlGenmaintenance.gmntBookeddate}" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>/></input>
						</span>
						</div>
				</td>
				<td>
					<div class="easyui-paddingbfpx">
				  	 <label class="">StandBy Description</label>
		           </div>
		          	
		          	<div class="easyui-paddingbfpx" >
		          		<textarea rows="2" style="height:65px; width : 277px;" cols="" id="txtESRDescription" name="txtESRDescription" maxlength="500" <c:out value = "${requestScope.generalMaintainanceBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlGenmaintenance.gmntRootcause}</textarea>
		          	</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
				<div class="easyui-paddingbfpx">
				  		 <label class="">Quantity</label>
		           		</div>
		          	
		          	<div class="easyui-paddingbfpx" >
		          		<input type="text" class="easyui-text" />
		          	</div>
				
				</td>
			    <td>
					<input id="pinsert" type="button" class="easyui-button" value="Insert" /> 
					<input id="pupdate" type="button" class="easyui-button" value="Update" />
					<input id="pdelete" type="button" class="easyui-button" value="Delete" />
				</td>
				
			</tr>
		</table>
		<div>
			<div class="sub-header" style="width:88%;_width:91%;margin-left:5%;">Component Details</div>
			<div  id="grdDiv" style="_margin-left:5%;">
				<table id="compDetailGrd"><tr><td></td></tr></table>
				<div id="compDetail"></div>
			</div>
			</div>
	</div> 
</div>
</div>
</form>