<!-- Author : Siddharth.A -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 
 <script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	

			jQuery('#submitForm').val('frmKznCompletion'); // set the id of form to submit
			initialiseForm('frmKznCompletion');

			jQuery('#frmKznCompletion .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmKznCompletion textarea').css('text-transform', 'uppercase');
			formatDateBox('dtekznmDate','dd-MMM-yyyy');
			formatDateBox('dtekznmCompleteddate','dd-MMM-yyyy');
			fillWithCurrentDate('dtekznmCompleteddate');
			
			fillComboBox("frmKznCompletion","cmbkznmKeyid","combo_improvemnetNo.kaizen" );
		    fillComboBox("frmKznCompletion","cmbkznmSectionid","sectionCombo.commonFilter" );
			
			fillComboBox("frmKznCompletion","cmbkhdmCellid","cellCombo.commonFilter");
			fillComboBox("frmKznCompletion","cmbkhdmMachineid","machineCombo.commonFilter" );

		});

		function frmKznCompletioncmbkznmKeyid_onLoadSuccess(){
			//fillComboBox("frmKznCompletion","cmbkznmSectionid","sectionCombo.commonFilter" );
			}
		
		function frmKznCompletioncmbkznmSectionid_onLoadSuccess(){
			fillComboBox("frmKznCompletion","cmbkznmCellid","cellCombo.commonFilter" );
		}
		
		function frmKznCompletioncmbkznmCellid_onLoadSuccess(){
			fillComboBox("frmKznCompletion","cmbkznmMachineid","machineCombo.commonFilter" );
		}
	
		function frmKznCompletioncmbkznmMachineid_onLoadSuccess(){
			fillComboBox("frmKznCompletion","cmbkznmCompletedid","employee.commonFilter" );
		}
		function frmKznCompletioncmbkhdmCellid_onLoadSuccess(){
			//	fillComboBox("frmKznCompletion","cmbkhdmMachineid","machineCombo.commonFilter" );
		}
		function frmKznCompletioncmbkhdmMachineid_onLoadSuccess(){
			
		}


		jQuery('#btnCancel').click(function(){
			closePopUpDialoge("divLoadKznCompletion");
			jQuery('#my_checkbox_'+jQuery('#hdnSelectedid').val()).removeAttr('checked');
		});

		jQuery('#btnSave').click(function(){
			var rowId=jQuery('#hdnSelectedid').val();
			//var selId = jQuery('#kznCompleteGrid').getCell(rowId,'imprvNo');
			var khdmKeyid=jQuery('#kznCompleteGrid').getCell(rowId,'KEYID');
			//alert(khdmKeyid);
	
			saveForm("frmKznCompletion","KznUpdateCompleteDtls.kaizen?hdnisHdReq="+jQuery('#hdnisHdReq').val()+"&khdmKeyid="+khdmKeyid);		
		});	

		function frmKznCompletion_successsCallback(result)
		{
			closePopUpDialoge("divLoadKznCompletion");
			jQuery('#my_checkbox_'+jQuery('#hdnSelectedid').val()).removeAttr('checked');
			jQuery('#kznCompleteGrid').trigger('reloadGrid');	
		}
	
</script>
	
	
	
<form id="frmKznCompletion">					
<table>
<tr>
<td style="width:40%;padding-left:50px" valign='top'  class="easyui-paddingbfpx">

	<div  class="easyui-paddingbfpx" ><label>Improvement No</label> </div>
	<div class="easyui-paddingbfpx">
			<input id="cmbkznmKeyid" name="cmbkznmKeyid" class="easyui-combobox"  style="width:175px;" value="${requestScope.kznTlMst.kznmKeyid}"  disabled="${requestScope.disableImprvNo}" >
							
	</div>
	
	<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Requested Date</label></div>
	<div class="easyui-paddingbfpx">
		  <input id="dtekznmDate" name="dtekznmDate" class="easyui-datebox" style="width:255px;" value="${requestScope.kznTlMst.kznmDate}" <c:out value = "${ requestScope.disableForm == true ? ' disabled':''}"/> />  
	</div>
	
	<div class="easyui-paddingbfpx"><label class="mandatory-lbl">DMT</label></div>
	<div class="easyui-paddingbfpx">
		<input id="cmbkznmSectionid" name="cmbkznmSectionid" class="easyui-combobox"  style="width:255px;" value="${requestScope.kznTlMst.kznmSectionid}"  <c:out value = "${ requestScope.disableForm == true ? ' disabled':''}"/> />
	</div>
	
	<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">JH</label></div>
	 <div class="easyui-paddingbfpx">
		  	<input  id="cmbkznmCellid" name="cmbkznmCellid" class="easyui-combobox"  style="width:255px;" value="${requestScope.kznTlMst.kznmCellid}" <c:out value = "${ requestScope.disableForm == true ? ' disabled':''}"/>/>
	</div>
</td>	
	<td style="width:40%;padding-left:30px" valign='top'  class="easyui-paddingbfpx">
	<div class="easyui-paddingbfpx" ><label >Equipment</label></div>
	 <div class="easyui-paddingbfpx">
		  	<input id="cmbkznmMachineid" name="cmbkznmMachineid" class="easyui-combobox"  style="width:255px;" value="${requestScope.kznTlMst.kznmMachineid}" <c:out value = "${ requestScope.disableForm == true ? ' disabled':''}"/>/>
	</div>
	
	<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Theme</label></div>
	 <div class="easyui-paddingbfpx">
		 <textarea id="txtkznmTheme" name="txtkznmTheme" rows="3" cols="20" style=" width : 255px;" <c:out value = "${ requestScope.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmTheme}</textarea>
	 
	 </div>
	
	 </td>
	 </tr>
	</table>
		
			<div class="sub-header"> Completed  Details </div>
	<table>
		<tr>
			<td style="width:40%;padding-left:50px" valign='top'  class="easyui-paddingbfpx">
			<div id="HDDtls">
				<div  class="easyui-paddingbfpx" ><label>HD Line</label> </div>
					<div class="easyui-paddingbfpx">
						<input id="cmbkhdmCellid" name="cmbkhdmCellid" class="easyui-combobox"  style="width:255px;" value="${requestScope.khdmCellid}" <c:out value = "${ requestScope.disableForm == true ? ' disabled':''}"/>/>
					</div>
	
					<div class="easyui-paddingbfpx"><label >HD Equipment </label></div>
					<div class="easyui-paddingbfpx">
		 				 <input id="cmbkhdmMachineid" name="cmbkhdmMachineid" class="easyui-combobox" style="width:255px;" value="${requestScope.khdmMachineid}" <c:out value = "${ requestScope.disableForm == true ? ' disabled':''}"/> />  
					</div>
			</div>
					<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Completed by</label></div>
					<div class="easyui-paddingbfpx">
						<input id="cmbkznmCompletedid" name="cmbkznmCompletedid" class="easyui-combobox"  style="width:255px;" value="${requestScope.kznTlMst.kznmCompletedid}"   />
					</div>
	
					<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Completed Date</label></div>
					 <div class="easyui-paddingbfpx">
			 			 <input id="dtekznmCompleteddate" name="dtekznmCompleteddate" class="easyui-datebox" style="width:255px;" value="${requestScope.kznTlMst.kznmCompleteddate}"  />  
					</div>
			</td>
				<td style="width:40%;padding-left:20px" valign='top'  class="easyui-paddingbfpx">
					<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Remarks</label></div>
	 				<div class="easyui-paddingbfpx">
						 <textarea id="txtkznmRemarks" name="txtkznmRemarks" rows="4" cols="28" style=" width : 255px;" >${requestScope.kznTlMst.kznmRemarks}</textarea>
	 				</div>
				</td>
			</tr>
		</table>	
				<div class="clear"></div>
					<div align="center" style="padding-left: 60px;margin-top:-15px;">
						<input type="button" class="easyui-button" id="btnSave" name="btnSave" value="Save" />
						<input type="button" class="easyui-button" id="btnCancel" value="Cancel" />
						<input type="hidden" id="hdnisHdReq" value="${requestScope.isHdReq}"/>
						<input type="hidden" id="hdnSelectedid" value="${requestScope.selectedId}"/>
						 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/>
					</div>	
</form>