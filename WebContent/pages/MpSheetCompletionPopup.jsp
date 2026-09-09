<!-- Author : Siddharth.A -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 
 <script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	

			jQuery('#submitForm').val('frmMpsCompletion'); // set the id of form to submit
			initialiseForm('frmMpsCompletion');

			jQuery('#frmMpsCompletion .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmMpsCompletion textarea').css('text-transform', 'uppercase');
			formatDateBox('dtempsmCompleteddate','dd-MMM-yyyy');
			fillWithCurrentDate('dtempsmCompleteddate');
			
			fillComboBox("frmMpsCompletion","cmbmpsmCompletedby","employee.commonFilter" );
		   
		});

		
		function frmMpsCompletioncmbkhdmMachineid_onLoadSuccess(){
			
		}


		jQuery('#btnCancel').click(function(){
			closePopUpDialoge("divLoadMpsCompletion");
			jQuery('#my_checkbox_'+jQuery('#hdnSelectedid').val()).removeAttr('checked');
		});

		jQuery('#btnSave').click(function(){
			var rowId=jQuery('#hdnSelectedid').val();
			var khdmKeyid=jQuery('#mpsCompleteGrid').getCell(rowId,'kznmKeyid');
	
			saveForm("frmMpsCompletion","MpsUpdateCompleteDtls.mps?hdnisHdReq="+jQuery('#hdnisHdReq').val()+"&khdmKeyid="+khdmKeyid+"&hdnMpsKeyid="+jQuery('#hdnMpsKeyid').val());		
		});	

		function frmMpsCompletion_successsCallback(result)
		{
			closePopUpDialoge("divLoadMpsCompletion");
			jQuery('#my_checkbox_'+jQuery('#hdnSelectedid').val()).removeAttr('checked');
			jQuery('#mpsCompleteGrid').trigger('reloadGrid');	
		}
	
</script>
	
	
	
<form id="frmMpsCompletion">					
		
			
	<table>
		<tr>
			<td style="width:40%;padding-left:50px" valign='top'  class="easyui-paddingbfpx">
			
					<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Completed by</label></div>
					<div class="easyui-paddingbfpx">
						<input id="cmbmpsmCompletedby" name="cmbmpsmCompletedby" class="easyui-combobox"  style="width:305px;" value="${requestScope.mpsTlMst.mpsmCompletedby}"   />
					</div>
	
					<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Completed Date</label></div>
					 <div class="easyui-paddingbfpx">
			 			 <input id="dtempsmCompleteddate" name="dtempsmCompleteddate" class="easyui-datebox" style="width:305px;" value="${requestScope.mpsTlMst.mpsmCompleteddate}"  />  
					</div>
			
					<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Completed Remarks</label></div>
	 				<div class="easyui-paddingbfpx">
						 <textarea id="txtmpsmCompletedremarks" name="txtmpsmCompletedremarks" rows="4" cols="28" style=" width : 305px;" >${requestScope.mpsTlMst.mpsmCompletedremarks}</textarea>
	 				</div>
	 				
	 				<div class="easyui-paddingbfpx" ><label>Opinion</label></div>
	 				<div class="easyui-paddingbfpx">
						 <textarea id="txtmpsmOpinion" name="txtmpsmOpinion" rows="4" cols="28" style=" width : 305px;" >${requestScope.mpsTlMst.mpsmOpinion}</textarea>
	 				</div>
				</td>
			</tr>
		</table>	
				<div class="clear"></div>
					<div align="center" style="padding-left: 0px;">
						<input type="button" class="easyui-button" id="btnSave" name="btnSave" value="Save" />
						<input type="button" class="easyui-button" id="btnCancel" value="Cancel" />
						<input type="hidden" id="hdnisHdReq" value="${requestScope.isHdReq}"/>
						<input type="hidden" id="hdnSelectedid" value="${requestScope.selectedId}"/>
						<input type="hidden" id="hdnMpsKeyid" name="hdnMpsKeyid" value="${requestScope.mpsKeyid}"/>
						 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/>
					</div>	
</form>