<script>
jQuery(document).ready(function(){
	initialiseForm('fromSafetyPop');
	var row=jQuery("#hdnRow").val();
	var cell = jQuery("#hdnCell").val();
	jQuery('#chkok').click(function(){
		jQuery('#chknotok').not('#chkok').removeAttr('checked');
		
 		
 	});
	jQuery('#chknotok').click(function(){
		jQuery('#chkok').not('#chknotok').removeAttr('checked');
 	});
 	jQuery("#btnok").click(function(){
 		
 		closePopUpDialoge("checkpoint");
 	 	});
jQuery("#btncancel").click(function(){
 	 	
 		closePopUpDialoge("checkpoint");
 	 	});

	formatDateBox('dteTargetDate','dd-MMM-yyyy');
	fillComboBox("fromSafetyPop","cmbResponsibility","employee.commonFilter");

});
function checkpoint_onClose( ){
	var checked;
		if(jQuery('input:checkbox[name=chkok]').attr('checked') == 'checked'){
			jQuery('input:checkbox[name=chknotok]').attr('checked',false);
	 		checked = true;
	 		jQuery('#chkStaus').val("ok");
	 	}else if(jQuery('input:checkbox[name=chknotok]').attr('checked') == 'checked'){
			jQuery('input:checkbox[name=chkok]').attr('checked',false);
	 		 checked = false;
	 		jQuery('#chkStaus').val("Notok");
	 	 	}
 	 	return true;
}
</script>
<form id="frmSafetyPop">
	<div>
		<table>
			<tr>
				<td>
					<div class="easyui-paddingbfpx"><label>Check Point</label></div>
					<div class="easyui-paddingbfpx">
						<input id="txtCheckPoints" name="txtCheckPoints" class="easyui-text" style="width:255px; height:21px;" value="${requestScope.check }" readonly="readonly"/>
					</div>
					<div style="padding-top:1px;">
						<span>
							<input type="checkbox" id="chkok" name="chkok" value="1"  onchange="checkboxfunction"style=""/><label> Ok </label> 
						</span>
						<span style="padding-left:150px;">
							<input type="checkbox" id="chknotok" name="chknotok" value=0 onchange="checkboxfunction"  style=""/><label> Not Ok</label> 
						</span>
					</div>
					<div class="easyui-paddingbfpx" style="padding-top:1px;"><label>Feedback</label></div>
					<div class="easyui-paddingbfpx">
						<textarea rows="2" cols="80" id="txtFeedback" name="txtFeedback" style="width:255px;height:44px;"></textarea>
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-top:1px;"><label>Responsibility</label></div>
					<div class="easyui-paddingbfpx">
						<input type="text" id="cmbResponsibility" name="cmbResponsibility" class="easyui-combobox"  style="width:250px;"/>
					</div>
					
					<div class="easyui-paddingbfpx" style="padding-top:1px;"><label>Target Date</label></div>
					<div class="easyui-paddingbfpx">
					<input id="dteTargetDate" name="dteTargetDate" class="easyui-text" readonly="readonly"  style="width: 90px;" />&nbsp;

						<span style="padding-left:30px;">
							<input type="button" id="btnok" name="btnok" value="OK" class="easyui-button" style="width:50px;height:21px;"/> 
						</span>
						<span style="padding-left:1px;">
							<input type="button" id="btncancel" name="btncancel" value="Cancel" class="easyui-button" style="width:50px;height:21px;"/> 
						</span>
					</div>			
					
				</td>
			</tr>
		</table>
	</div>
<input type="hidden" id="hdnCell" value="${requestScope.cellid}"/>
<input type="hidden" id="hdnRow" value="${requestScope.rowid}"/>
</form>